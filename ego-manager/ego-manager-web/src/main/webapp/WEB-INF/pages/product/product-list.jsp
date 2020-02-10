<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <!-- jsp静态导入 -->
    <%@ include file="../head.jsp" %>
    <script type="text/javascript">
    function delfunc(obj){
    	layer.confirm('确认删除？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
   				$.ajax({
   					type : 'post',
   					url : $(obj).attr('data-url'),
   					data : {act:'del',del_id:$(obj).attr('data-id')},
   					dataType : 'json',
   					success : function(data){
   						if(data==1){
   							layer.msg('操作成功', {icon: 1});
   							$(obj).parent().parent().remove();
   						}else{
   							layer.msg(data, {icon: 2,time: 2000});
   						}
   						layer.closeAll();
   					}
   				})
    		}, function(index){
    			layer.close(index);
    			return false;// 取消
    		}
    	);
    }
    
    //全选
    function selectAll(name,obj){
    	$('input[name*='+name+']').prop('checked', $(obj).checked);
    }   
    
    function get_help(obj){
        layer.open({
            type: 2,
            title: '帮助手册',
            shadeClose: true,
            shade: 0.3,
            area: ['90%', '90%'],
            content: $(obj).attr('data-url'), 
        });
    }
    
    function delAll(obj,name){
    	var a = [];
    	$('input[name*='+name+']').each(function(i,o){
    		if($(o).is(':checked')){
    			a.push($(o).val());
    		}
    	})
    	if(a.length == 0){
    		layer.alert('请选择删除项', {icon: 2});
    		return;
    	}
    	layer.confirm('确认删除？', {btn: ['确定','取消'] }, function(){
    			$.ajax({
    				type : 'get',
    				url : $(obj).attr('data-url'),
    				data : {act:'del',del_id:a},
    				dataType : 'json',
    				success : function(data){
    					if(data == 1){
    						layer.msg('操作成功', {icon: 1});
    						$('input[name*='+name+']').each(function(i,o){
    							if($(o).is(':checked')){
    								$(o).parent().parent().remove();
    							}
    						})
    					}else{
    						layer.msg(data, {icon: 2,time: 2000});
    					}
    					layer.closeAll();
    				}
    			})
    		}, function(index){
    			layer.close(index);
    			return false;// 取消
    		}
    	);	
    }
    </script>        
  <meta name="__hash__" content="934c3c704c4bed5cb4da6cec6353613a_2e4eb53d2afc41d11040df3ef57fa1ca"></head>
  <body style="background-color:#ecf0f5;">
 

<div class="wrapper">
 <div class="breadcrumbs" id="breadcrumbs">
	<ol class="breadcrumb">
	<li><a href="javascript:void();"><i class="fa fa-home"></i>&nbsp;&nbsp;后台首页</a></li>
	        
	        <li><a href="javascript:void();">商品管理</a></li>    
	        <li><a href="javascript:void();">商品列表</a></li>          
	</ol>
</div>

 <style>#search-form > .form-group{margin-left: 10px;}</style>
  <!-- Main content -->
  <section class="content">
    <div class="container-fluid">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><i class="fa fa-list"></i> 商品列表</h3>
        </div>
        <div class="panel-body">
          <div class="navbar navbar-default">
              <form action="" id="search-form2" class="navbar-form form-inline" method="post" onsubmit="return false">
                <div class="form-group">
                  <select name="catId" id="cat_id" class="form-control" onchange="ajax_get_table(1);">
                    <option value="">所有分类</option>
                      <c:forEach var="gc" items="${gcList}">
                          <option value="${gc.id}">${gc.name}</option>
                      </c:forEach>
                  </select>
                </div>
                <div class="form-group">
                  <select name="brandId" id="brand_id" class="form-control"  onchange="ajax_get_table(1);" >
                    <option value="">所有品牌</option>
                      <c:forEach var="b" items="${brandList}">
                          <option value="${b.id}">${b.name}</option>
                      </c:forEach>
                  </select>
                </div>                

                <div class="form-group">
                    <label class="control-label" for="input-order-id">每页显示</label>
                    <select name="pageSize" id="pageSize" onchange="ajax_get_table(1);"
                            class="form-control">
                        <option value="10">10</option>
                        <option value="20">20</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>
                    <label class="control-label" for="input-order-id">条</label>
                </div>                

				<br>
				<br>
                <div class="form-group">
                  <label class="control-label" for="input-order-id">关键词</label>
                  <div class="input-group">
                    <input name="goodsName" value="" placeholder="搜索词" id="input-order-id" class="form-control" type="text">
                  </div>
                </div>                  
                <!--排序规则-->
                <input name="orderby1" value="goods_id" type="hidden">
                <input name="orderby2" value="desc" type="hidden">
                <button   onclick="ajax_get_table(1);" id="button-filter search-order" class="btn btn-primary"><i class="fa fa-search"></i> 筛选</button>
                <button type="button" onclick="location.href='${ctx}/product/add'" class="btn btn-primary pull-right"><i class="fa fa-plus"></i>添加新商品</button>
              <input name="__hash__" value="934c3c704c4bed5cb4da6cec6353613a_2e4eb53d2afc41d11040df3ef57fa1ca" type="hidden"></form>
          </div>
          <div id="ajax_return"><form method="post" enctype="multipart/form-data" target="_blank" id="form-order">
    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <td style="width: 1px;" class="text-center">
                <!--
                    <input type="checkbox" onclick="$('input[name*=\'selected\']').prop('checked', this.checked);">
                -->    
                </td>                
                <td class="text-right">
                    <a href="javascript:sort('goods_id');">ID</a>
                </td>
                <td class="text-left">
                    <a href="javascript:sort('goods_name');">商品名称</a>
                </td>
                <td class="text-left">
                    <a href="javascript:sort('goods_sn');">货号</a>
                </td>                                
                <td class="text-left">
                    <a href="javascript:sort('cat_id');">分类</a>
                </td>                
                <td class="text-left">
                    <a href="javascript:sort('shop_price');">价格</a>
                </td>
                <td class="text-left">
                    <a href="javascript:void(0);">库存</a>
                </td>                
                <td class="text-center">
                    <a href="javascript:sort('is_on_sale');">上架</a>
                </td>
                <td class="text-center">
                    <a href="javascript:sort('is_recommend');">推荐</a>
                </td>
                <td class="text-center">
                    <a href="javascript:sort('is_new');">新品</a>
                </td>   
                <td class="text-center">
                    <a href="javascript:sort('is_hot');">热卖</a>
                </td>                   
                <td class="text-center">
                    <a href="javascript:sort('sort');">排序</a>
                </td>                   
                <td class="text-center" style="width:135px;">操作</td>
            </tr>
            </thead>
              <tbody id="goodsContent">

                </tbody>
        </table>
    </div>
<input name="__hash__" value="00ea0d70ce1e0760a8bf5d90b5e30971_699560bd02bf6cad1be4e51b170eb190" type="hidden"></form>
<div class="row">
    <div class="col-sm-3 text-left"></div>
    <div class="col-sm-9 text-right">
        <div class="dataTables_paginate paging_simple_numbers">
            <ul class="pagination" id="pageContent">
            </ul>
        </div>
    </div>
</div>


<script>
    // 点击分页触发的事件
    $(".pagination  a").click(function(){
        cur_page = $(this).data('p');
        ajax_get_table('search-form2',cur_page);
    });
	
    /*
     * 清除静态页面缓存
     */
    function ClearGoodsHtml(goods_id)
    {
    	$.ajax({
				type:'GET',
				url:"/index/Admin/System/ClearGoodsHtml",
				data:{goods_id:goods_id},
				dataType:'json',
				success:function(data){
					layer.alert(data.msg, {icon: 2});								 
				}
		});
    }
    /*
     * 清除商品缩列图缓存
     */
    function ClearGoodsThumb(goods_id)
    {
    	$.ajax({
				type:'GET',
				url:"/index/Admin/System/ClearGoodsThumb",
				data:{goods_id:goods_id},
				dataType:'json',
				success:function(data){
					layer.alert(data.msg, {icon: 2});								 
				}
		});
    }		
</script></div>
        </div>
      </div>
    </div>
    <!-- /.row --> 
  </section>
  <!-- /.content --> 
</div>
<!-- /.content-wrapper -->

<!-- 1.导入文件 -->
<script src="${ctx}/Public/js/doT.min.js"></script>
<!-- 2.编写模板 -->
<script type="template" id="goodsTemp">
    {{ for(var i = 0; i < it.length; i++){ }}
    <tr>
        <td class="text-center">
            <input name="shipping_code[]" value="flat.flat" type="hidden">
        </td>
        <td class="text-right">{{=it[i].goodsId}}</td>
        <td class="text-left">{{=it[i].goodsName}}</td>
        <td class="text-left">{{=it[i].goodsSn}}</td>
        <td class="text-left">{{=it[i].catId}}</td>
        <td class="text-left">{{=it[i].marketPrice}}</td>
        <td class="text-left">
            <input onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"
                   onpaste="this.value=this.value.replace(/[^\d.]/g,'')"
                   onchange="ajaxUpdateField(this);" name="store_count" size="4"
                   data-table="goods" data-id="143" value="{{=it[i].storeCount}}" type="text">
        </td>
        <td class="text-center">
            <img src="${ctx}/Public/images/yes.png"
                 onclick="changeTableVal('goods','goods_id','143','is_on_sale',this)"
                 width="20" height="20">
        </td>
        <td class="text-center">
            <img src="${ctx}/Public/images/yes.png"
                 onclick="changeTableVal('goods','goods_id','143','is_recommend',this)"
                 width="20" height="20">
        </td>
        <td class="text-center">
            <img src="${ctx}/Public/images/cancel.png"
                 onclick="changeTableVal('goods','goods_id','143','is_new',this)"
                 width="20" height="20">
        </td>
        <td class="text-center">
            <img src="${ctx}/Public/images/yes.png"
                 onclick="changeTableVal('goods','goods_id','143','is_hot',this)"
                 width="20" height="20">
        </td>
        <td class="text-center">
            <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                   onpaste="this.value=this.value.replace(/[^\d]/g,'')"
                   onchange="updateSort('goods','goods_id','143','sort',this)" size="4"
                   value="{{=it[i].sort}}" type="text">
        </td>
        <td class="text-center">
            <a target="_blank" href="/index/Home/Goods/goodsInfo/id/143"
               class="btn btn-info" title="查看详情"><i class="fa fa-eye"></i></a>
            <a href="商品列表-添加新商品.html" class="btn btn-primary" title="编辑"><i
                    class="fa fa-pencil"></i></a>
            <a href="javascript:void(0);" onclick="del('143')" class="btn btn-danger"
               title="删除"><i class="fa fa-trash-o"></i></a>
        </td>
    </tr>
    {{ } }}
</script>


<script type="template" id="pageTemp">
    {{ if(it.hasPreviousPage){ }}
    <li class="paginate_button previous">
        <a class="next" href="javascript:void(0);" onclick="ajax_get_table('{{=it.pageNum-1}}');">上一页</a>
    </li>
    {{ } }}
    {{ for(var i = 1; i <= it.pages; i++){ }}
    <li class="paginate_button
        {{ if(i == it.pageNum){ }}
        active
        {{ } }}">
        <a href="javascript:void(0);" onclick="ajax_get_table('{{=i}}');">{{=i}}</a>
    </li>
    {{ } }}
    {{ if(it.hasNextPage){ }}
    <li class="paginate_button next">
        <a class="next" href="javascript:void(0);" onclick="ajax_get_table('{{=it.pageNum+1}}');">下一页</a>
    </li>
    {{ } }}
</script>





<script>
  //页面加载完毕
  $(function () {
      ajax_get_table(1);
  });

//根据页面获取商品列表
  function ajax_get_table(pageNum) {
      $.ajax({
          url: "${ctx}/product/list/page",
          type: "POST",
          data: {
              catId: $("#cat_id").val(),
              brandId: $("#brand_id").val(),
              goodsName: $("#input-order-id").val(),
              pageNum: pageNum,
              pageSize: $("#pageSize").val()
          },
          dataType: "JSON",
          success: function (result) {
              if (200 == result.code) {
                 console.log(result)
                  // 3.调用模板<script type="template" id="goodsTemp">
                  var goodsTemp = doT.template($("#goodsTemp").text());
                  $("#goodsContent").html(goodsTemp(result.pageInfo.list));

                  //<script type="template" id="pageTemp">
                  var pageTemp = doT.template($("#pageTemp").text());
                  $("#pageContent").html(pageTemp(result.pageInfo));
              } else {
                  layer.msg("该分类或品牌下无商品信息！");
              }
          },
          error: function (result) {
              layer.alert("亲，系统正在升级中，请稍后再试！");
          }
      });
  }

      
        // 点击排序
        function sort(field)
        {
           $("input[name='orderby1']").val(field);
           var v = $("input[name='orderby2']").val() == 'desc' ? 'asc' : 'desc';             
           $("input[name='orderby2']").val(v);
           ajax_get_table('search-form2',cur_page);
        }
        
        // 删除操作
        function del(id)
        {
            if(!confirm('确定要删除吗?'))
                return false;
		$.ajax({
			url:"/index?m=Admin&c=goods&a=delGoods&id="+id,
			success: function(v){	
                                var v =  eval('('+v+')');                                 
                                if(v.hasOwnProperty('status') && (v.status == 1))
                                        ajax_get_table('search-form2',cur_page);                                                      
                                else
                                        layer.msg(v.msg, {icon: 2,time: 1000}); //alert(v.msg);
			}
		}); 
               return false;
          }
</script> 

</body></html>