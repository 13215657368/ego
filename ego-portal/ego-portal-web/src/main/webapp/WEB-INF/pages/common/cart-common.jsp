<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div id="s_cart">
    <ul>
        <li class="nums">
            <a href="${ctx}/cart/getCartList" id="s_cart_nums1">购物车： <span id="cartSpan">0</span> 件</a>
            <a href="${ctx}/cart/getCartList" class="btn" id="s_cart_nums2"></a>
        </li>
        <li class="checkout"><a href="${ctx}/cart/getCartList">去结算>></a></li>
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

    // 加入购物车
    function addToCart(goodsId, goodsName, marketPrice, originalImg) {
        $.ajax({
            url: '${ctx}/cart/addToCart',
            type: 'POST',
            dataType: 'JSON',
            data: {
                goodsId: goodsId,
                goodsName: goodsName,
                originalImg: originalImg,
                marketPrice: marketPrice,
                goodsNum: 1
            },
            success: function (result) {
                if (200 == result.code) {
                    layer.msg("添加购物车成功！");
                    var num = parseInt($("#cartSpan").text()) + 1;
                    $("#cartSpan").text(num);
                } else {
                    layer.alert("亲，系统正在升级中，请稍后再试！");
                }
            },
            error: function (result) {
                if (200 == result.status && "OK" == result.statusText) {
                    window.location.href = "${ctx}/login";
                } else {
                    layer.alert("亲，系统正在升级中，请稍后再试！");
                }
            }
        });
    }
</script>
