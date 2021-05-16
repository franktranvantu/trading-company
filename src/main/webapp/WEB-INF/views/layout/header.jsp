<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${contextPath}/order">Trading Company</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/provider">Provider</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/customer">Customer</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/category">Category</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/product">Product</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/staff">Staff</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/inventory">Inventory</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/revenue">Revenue</a>
            </li>
        </ul>
    </div>
</nav>
