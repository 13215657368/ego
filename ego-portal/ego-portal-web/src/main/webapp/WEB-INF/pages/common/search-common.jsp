<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div id="s_search">
    <form action="${ctx}/search/index" method="post">
        <input name="searchStr" type="text" class="search-input"/>
        <input type="image" src="${ctx}/images/btn_search.jpg"/>
    </form>
</div>