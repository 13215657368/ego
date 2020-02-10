<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div id="s_tbar">
    <div class="s_hd">
        <div class="tbar_lft">
            <c:if test="${!empty user}">
                ${user.userName}您好，欢迎来到EGO商城！| <a href="${ctx}/user/logout">安全退出</a>
            </c:if>
            <c:if test="${empty user}">
                您好，欢迎来到EGO商城！| <a href="${ctx}/login">请登录</a> | <a href="${ctx}/register">免费注册</a>
            </c:if>
        </div>
        <div class="tbar_rgt">
            <ul>
                <li class="first"><a href="#">我的订单</a></li>
                <li><a href="#">我的EGO商城</a></li>
                <li><a href="#">帮助中心</a></li>
                <li><a href="#">联系客服</a></li>
                <li><a href="#">加入收藏</a></li>
                <li class="s_tel_str">服务热线：</li>
                <li class="s_tel">400-009-1906</li>
            </ul>
        </div>
    </div>
</div>
