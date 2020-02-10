package com.ego.vo;

import java.io.Serializable;
import java.util.List;

public class GoodsCategoryVo implements Serializable {
//  列表子级
private List<GoodsCategoryVo> children;

//新图片
    private   String updateImage;
/**
*  商品分类 id id
**/
private Short id;
/**
*  商品分类名称 name
**/
private String name;
/**
*  手机端显示的商品分类名 mobile_name
**/
private String mobileName;
/**
*  父 id parent_id
**/
private Short parentId;
/**
*  家族图谱 parent_id_path
**/
private String parentIdPath;
/**
*  等级 level
**/
private Byte level;
/**
*  顺序排序 sort_order
**/
private Byte sortOrder;
/**
*  是否显示 is_show
**/
private Byte isShow;
/**
*  分类图片 image
**/
private String image;
/**
*  是否推荐为热门分类 is_hot
**/
private Byte isHot;
/**
*  分类分组默认 0 cat_group
**/
private Byte catGroup;
/**
*  分佣比例 commission_rate
**/
private Byte commissionRate;
/**
* tableName: t_goods_category
**/
private static final long serialVersionUID = 1L;
/**
*  商品分类 id id
**/
public Short getId() {
return id;
}
/**
*  商品分类 id id
**/
public void setId(Short id) {
this.id = id;
}
/**
*  商品分类名称 name
**/
public String getName() {
return name;
}
/**
*  商品分类名称 name
**/
public void setName(String name) {
this.name = name == null ? null : name.trim();
}
/**
*  手机端显示的商品分类名 mobile_name
**/
public String getMobileName() {
return mobileName;
}
/**
*  手机端显示的商品分类名 mobile_name
**/
public void setMobileName(String mobileName) {
this.mobileName = mobileName == null ? null :
mobileName.trim();
}
/**
*  父 id parent_id
**/
public Short getParentId() {
return parentId;
}
/**
*  父 id parent_id
**/
public void setParentId(Short parentId) {
this.parentId = parentId;
}
/**
*  家族图谱 parent_id_path
**/
public String getParentIdPath() {
return parentIdPath;
}
/**
*  家族图谱 parent_id_path
**/
public void setParentIdPath(String parentIdPath) {
this.parentIdPath = parentIdPath == null ? null :
parentIdPath.trim();
}
/**
*  等级 level
**/
public Byte getLevel() {
return level;
}
/**
*  等级 level
**/
public void setLevel(Byte level) {
this.level = level;
}
/**
*  顺序排序 sort_order
**/
public Byte getSortOrder() {
return sortOrder;
}
/**
*  顺序排序 sort_order
**/
public void setSortOrder(Byte sortOrder) {
this.sortOrder = sortOrder;
}
/**
*  是否显示 is_show
**/
public Byte getIsShow() {
return isShow;
}
/**
*  是否显示 is_show
**/
public void setIsShow(Byte isShow) {
this.isShow = isShow;
}
/**
*  分类图片 image
**/
public String getImage() {
return image;
}
/**
*  分类图片 image
**/
public void setImage(String image) {
this.image = image == null ? null : image.trim();
}
/**
*  是否推荐为热门分类 is_hot
**/
public Byte getIsHot() {
return isHot;
}
/**
*  是否推荐为热门分类 is_hot
**/
public void setIsHot(Byte isHot) {
this.isHot = isHot;
}
/**
*  分类分组默认 0 cat_group
**/
public Byte getCatGroup() {
return catGroup;
}
/**
*  分类分组默认 0 cat_group
**/
public void setCatGroup(Byte catGroup) {
this.catGroup = catGroup;
}
/**
*  分佣比例 commission_rate
**/
public Byte getCommissionRate() {
return commissionRate;
}
/**
*  分佣比例 commission_rate
**/
public void setCommissionRate(Byte commissionRate) {
this.commissionRate = commissionRate;
}
public List<GoodsCategoryVo> getChildren() {
return children;
}
public void setChildren(List<GoodsCategoryVo> children) {
this.children = children;
}


    public String getUpdateImage() {
        return updateImage;
    }

    public void setUpdateImage(String updateImage) {
        this.updateImage = updateImage;
    }

    @Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
sb.append(", id=").append(id);
sb.append(", name=").append(name);
sb.append(", mobileName=").append(mobileName);
sb.append(", parentId=").append(parentId);
sb.append(", parentIdPath=").append(parentIdPath);
sb.append(", level=").append(level);
sb.append(", sortOrder=").append(sortOrder);
sb.append(", isShow=").append(isShow);
sb.append(", image=").append(image);
sb.append(", isHot=").append(isHot);
sb.append(", catGroup=").append(catGroup);
sb.append(", commissionRate=").append(commissionRate);
sb.append("]");
return sb.toString();
}
}