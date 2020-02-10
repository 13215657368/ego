<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div id="s_cart">
    <ul>
        <li class="nums">
            <a href="javascript:void(0);" id="s_cart_nums1">购物车： <span id="cartSpan">0</span> 件</a>
            <a href="javascript:void(0);" class="btn" id="s_cart_nums2"></a>
        </li>
        <li class="checkout"><a href="javascript:void(0);">去结算>></a></li>
    </ul>
</div>
<script type="text/javascript">
    $(function () {
        getCartNum();
    });

    // 获取购物车数量
    function getCartNum() {
        $.ajax({
            url: '${ctx}/cart/getCartNum',
            type: 'POST',
            dataType: 'JSON',
            success: function (result) {
                $("#cartSpan").text(result);
            },
            error: function (result) {
                layer.alert("亲，系统正在升级中，请稍后再试！");
            }
        });
    }
</script>
