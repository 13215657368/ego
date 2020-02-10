package com.ego.result;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;

public class BaseResult implements Serializable {//
    //  状态编码
    private Integer code;
    //  状态描述
    private String message;
    //  分页对象 ( 商品列表需要 -pom.xml 添加依赖 )
    PageInfo<?> pageInfo;

    // 成功返回的对象
    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.setCode(BaseResultEnum.SUCESS.getCode());
        result.setMessage(BaseResultEnum.SUCESS.getMessage());
        return result;
    }

    //成功返回的对象（分页操作使用）
    public static BaseResult success(PageInfo<?> pageInfo) {
        BaseResult result = new BaseResult();
        result.setCode(BaseResultEnum.SUCESS.getCode());
        result.setMessage(BaseResultEnum.SUCESS.getMessage());
        result.setPageInfo(pageInfo);
        return result;
    }



    // 失败返回的对象
    public static BaseResult error() {
        BaseResult result = new BaseResult();
        result.setCode(BaseResultEnum.ERROR.getCode());
        result.setMessage(BaseResultEnum.ERROR.getMessage());
        return result;
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageInfo<?> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<?> pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "BaseResult [code=" + code + ", message=" + message +
                "]";
    }
}