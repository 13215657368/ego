package com.ego.controller;

import com.ego.pojo.Brand;
import com.ego.pojo.Goods;
import com.ego.pojo.GoodsCategory;
import com.ego.pojo.GoodsImages;
import com.ego.result.BaseResult;
import com.ego.result.FileResult;
import com.ego.service.*;
import com.ego.service.impl.GoodsImagesServiceImpl;
import com.ego.vo.GoodsCategoryVo;
import com.sun.org.apache.xml.internal.serialize.Method;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.security.pkcs11.Secmod;

import java.io.IOException;
import java.util.List;

/**
 * Created by jick on 2019/3/25.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private GoodsCategoryServiceI goodsCategoryService;

    @Autowired
    private FileUploadServiceI fileUploadService;

    @Autowired
    private BrandServiceI   brandService;

    @Autowired
    private GoodsServiceI  goodsService;


    @Autowired
    private GoodsImagesServiceI  goodsImagesService;

    /*商品列表详情*/
    @RequestMapping("/category/list")
    public String categoryList(Model model) {
        model.addAttribute("goodsCategoryList", goodsCategoryService.selectCategoryListFroView());
        return "product/category/category-list";
    }


    /*商品添加详情*/
    @RequestMapping("/category/add")
    public String categoryAdd(Model model) {
        List<GoodsCategory> gclist = goodsCategoryService.selectTopCategory();
        model.addAttribute("gcList", gclist);
        return "product/category/category-add";
    }

    //商品分类
    @RequestMapping("/category/getByParentId")
    @ResponseBody
    public List<GoodsCategory> categoryGetByParentId(Short parentId) {
        return goodsCategoryService.selectCategoryGetByParentId(parentId);
    }


    //商品分类保存
    @RequestMapping("/category/save")
    @ResponseBody
    public BaseResult categotySave(GoodsCategory goodsCategory) {
        return goodsCategoryService.categotySave(goodsCategory);
    }


    //商品删除
    @RequestMapping("/category/del")
    @ResponseBody
    public BaseResult categoryDelete(Short id) {
        return    categoryDeleteMajorizationBefore(id);
    }


    public BaseResult categoryDeleteMajorizationBefore(Short id) {
        GoodsCategory goodsCategory = goodsCategoryService.categoryGetById(id);

        if (goodsCategory == null) {
            return BaseResult.error();
        }

        //级联删除下级分类
        List<GoodsCategory> gcSecondList = null;
        List<GoodsCategory> gcThirdList = null;
        BaseResult result = null;

        //删除该一级分类的所有二级分类以及三级分类
        if (goodsCategory.getLevel() == 1) {
            gcSecondList = goodsCategoryService.selectCategoryGetByParentId(id);
            if (gcSecondList != null && gcSecondList.size() > 0) {
                Short[] secondIds = new Short[gcSecondList.size()];

                for (int i = 0; i < gcSecondList.size(); i++) {
                    secondIds[i] = gcSecondList.get(i).getId();

                    //获取三级分类
                    gcThirdList = goodsCategoryService.selectCategoryGetByParentId(gcSecondList.get(i).getId());

                    if (gcThirdList != null && gcThirdList.size() > 0) {
                        Short[] thirdIds = new Short[gcThirdList.size()];

                        for (int j = 0; j < gcSecondList.size(); j++) {
                            thirdIds[j] = gcSecondList.get(i).getId();
                        }

                        //删除三级分类
                        result = goodsCategoryService.categoryListDelete(thirdIds);


                        //  数据库删除成功再删除 ftp 服务器文件
                        if (200 == result.getCode()) {
                            for (int j = 0; j < gcThirdList.size(); j++) {

                                if (gcThirdList.get(j).getImage() != null && gcThirdList.get(j).getImage().length() > 0) {
                                    fileUploadService.fileDelete(gcSecondList.get(i).getImage());
                                }


                            }
                        }


                    }


                }

                //删除二级分类

                result = goodsCategoryService.categoryListDelete(secondIds);

                if (200 == result.getCode()) {
                    for (int i = 0; i < gcSecondList.size(); i++) {
                        if (gcSecondList.get(i).getImage() != null && gcSecondList.get(i).getImage().length() > 0) {
                            fileUploadService.fileDelete(gcSecondList.get(i).getImage());
                        }
                    }

                }


            }
        }


        //删除该二级分类的所有三级分类

        if (goodsCategory.getLevel() == 2) {
            //获取三级分类
            gcThirdList = goodsCategoryService.selectCategoryGetByParentId(id);

            if (gcThirdList != null && gcThirdList.size() > 0) {
                Short[] thirdIds = new Short[gcThirdList.size()];
                for (int j = 0; j < gcThirdList.size(); j++) {
                    thirdIds[j] = gcThirdList.get(j).getId();
                }
//  删除三级分类
                result = goodsCategoryService.categoryListDelete(thirdIds);
//  数据库删除成功再删除 ftp 服务器文件
                if (200 == result.getCode()) {
                    for (int j = 0; j < gcThirdList.size(); j++) {
                        if (gcThirdList.get(j).getImage() != null &&
                                gcThirdList.get(j).getImage().length() > 0) {
                            fileUploadService.fileDelete(gcThirdList.get(j).getImage());
                        }
                    }
                }
            }
        }
        result = goodsCategoryService.categoryDelete(id);
         //  数据库删除成功再删除 ftp 服务器文件
        if (200 == result.getCode()) {
            if (goodsCategory.getImage() != null && goodsCategory.getImage().length() > 0) {
                fileUploadService.fileDelete(goodsCategory.getImage());
            }
        }
        return result;
    }


    @RequestMapping(value="/category/edit/{id}", method = RequestMethod.GET)
    public String   categoryEdit(@PathVariable("id") Short id,  Model  model){

        /**
         * 思路：
         * 首先通过id查询分类信息，
         * 查询所有的一级类别。
         * 判断等级
         * 如果level为2 ，说明是二级分类
         * 然后查询它的父id，通过父id查询类型列表，这需要在前台回显
         *
         *
         * 如果level为3，说明是三级分类
         * 然后通过id一级一级查询，同时在前台展示
         */

        // 根据主键查询商品分类信息返显至页面
        GoodsCategory   gc  =      goodsCategoryService.categoryGetById(id);
        model.addAttribute("gc",gc);
        model.addAttribute("gcList1",goodsCategoryService.selectTopCategory());


        //返回级联菜单的parentId标识
        if(2==gc.getLevel()){
            //二级菜单列表
       model.addAttribute("gcList2",   goodsCategoryService.selectCategoryGetByParentId(gc.getParentId()));
       model.addAttribute("gcParentId1",gc.getParentId());

        }

        if(3==gc.getLevel()){
            //三级菜单查询二级菜单
            GoodsCategory  gc2 =  goodsCategoryService.categoryGetById(gc.getParentId());

            //二级菜单列表
            model.addAttribute("gcList2",goodsCategoryService.selectCategoryGetByParentId(gc2.getParentId()));
            model.addAttribute("gcParentId1",gc2.getParentId());
            model.addAttribute("gcParentId2",gc.getParentId());
        }
        return   "product/category/category-edit";


    }

/*
    *//**
     * 根据父id查询下级分类
     *//*
    @RequestMapping("/category/byParentId")
    @ResponseBody
    public List<GoodsCategory> categoryByParentId(Short parentId) {
        return goodsCategoryService.selectCategoryByParentId(parentId);
    }*/



    /**
     *  商品分类 - 编辑分类 - 保存
     *
     * @param goodsCategoryVo
     * @return
     */
    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    @ResponseBody
    public   BaseResult   categoryUpdate(GoodsCategoryVo goodsCategoryVo){
         GoodsCategory   gc =   new GoodsCategory();
         //拷贝属性
        BeanUtils.copyProperties( goodsCategoryVo,gc);

        //判断是否上传了新的图片，删除旧的图片
        if(null!=goodsCategoryVo.getUpdateImage()&&goodsCategoryVo.getUpdateImage().length()>0){
            gc.setImage(goodsCategoryVo.getUpdateImage());

            //修改
            BaseResult   result  =  goodsCategoryService.categoryUpdate(gc);

            //删除旧的图片
            if(200==result.getCode()){
                fileUploadService.fileDelete(goodsCategoryVo.getImage());
            }

            return  result;
        }
        //修改
        return   goodsCategoryService.categoryUpdate(gc);

    }

    @RequestMapping("/list")
    public  String   productList(Model model){

        // 查询所有分类
        model.addAttribute("gcList", goodsCategoryService.selectCategoryListForGoodsList());
        // 查询所有品牌
        model.addAttribute("brandList", brandService.selectBrandList());

        return  "product/product-list";
    }



    /**
     * 分页查询所有商品列表
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/list/page")
    @ResponseBody
    public BaseResult producyListPage(Goods goods, Integer pageNum, Integer pageSize) {
        return goodsService.selectGoodsList(goods, pageNum, pageSize);
    }



    @RequestMapping("/add")
    public  String    productAdd(Model model){
        //查询所有商品的分类
        List<GoodsCategory>  gcList =  goodsCategoryService.selectTopCategory();

        model.addAttribute("gcList",gcList);

        //查询所有商品的品牌
        List<Brand>   brandList =  brandService.selectBrandList();
        model.addAttribute("brandList",brandList);
        return "product/product-add";

    }


    @RequestMapping("/save")
    @ResponseBody
    public   BaseResult  productSave(Goods  goods){
        return  goodsService.productSave(goods);
    }



    /**
     *  商品列表 - 新增商品 - 商品相册 - 保存 ( 多张图片实际上是多次调用了该方法 )
     *
     * @param goodsImages
     * @param goodsId
     * @return
     */
    @RequestMapping("/goodsImages/save")
    @ResponseBody

    public   BaseResult goodsImagesSave(MultipartFile goodsImages,Integer goodsId){
        /*
          1、首先判断信息是否为空，非空继续操作
        * 2、调用图片上函数方法，返回图片在服务上的url
        * 3、url是否为空，不为空调用接口方法插叙到数据库中
        * 4、通过接口判断是否成功，成功，返回success
        * */

        BaseResult  result  = null;
        if(goodsImages !=null && goodsId !=null){
            try {
             FileResult  fr  =  fileUploadService.fileUpload(goodsImages.getOriginalFilename(),goodsImages.getInputStream());

             if("success".equals(fr.getSuccess())){
               result=  goodsImagesService.goodsImagesSave(fr.getFileUrl(),goodsId);
             }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return    result;
    }



}



