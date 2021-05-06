<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>${action} Product</title>
</head>
<body>
  <c:if test="${not empty result}">
    <div class="alert alert-danger ml-auto position-absolute message" role="alert">
        ${result.message}
    </div>
  </c:if>

  <div class="container w-25">
    <h1 class="text-center mt-4 mb-4">${action} product</h1>
    <form:form action="${contextPath}/product/save-product" method="post" modelAttribute="product" id="save-product">
      <form:hidden path="id"/>
      <div class="form-group row">
        <label for="name" class="col-sm-4 col-form-label">Name <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:input path="name" class="form-control" id="name" aria-describedby="name"/>
          <div id="name" class="invalid-feedback">
            Name is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="model" class="col-sm-4 col-form-label">Model <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:input path="model" class="form-control" id="model" aria-describedby="model"/>
          <div id="name" class="invalid-feedback">
            Model is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="brand" class="col-sm-4 col-form-label">Brand <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:input path="brand" class="form-control" id="brand" aria-describedby="brand"/>
          <div id="brand" class="invalid-feedback">
            Brand is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="description" class="col-sm-4 col-form-label">Description</label>
        <div class="col-sm">
          <form:input path="description" class="form-control" id="description" aria-describedby="description"/>
          <div id="description" class="invalid-feedback">
            Description is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="provider" class="col-sm-4 col-form-label">Provider <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:select path="provider" class="form-control" id="provider" aria-describedby="provider">
            <c:forEach var="provider" items="${providers}">
              <option value="${provider.id}" ${product.provider.id == provider.id ? 'selected' : ''}>${provider.name}</option>
            </c:forEach>
          </form:select>
          <div id="course" class="invalid-feedback">
            Provider is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="category" class="col-sm-4 col-form-label">Category <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:select path="category" class="form-control" id="category" aria-describedby="category">
            <c:forEach var="category" items="${categories}">
              <option value="${category.id}" ${product.category.id == category.id ? 'selected' : ''}>${category.name}</option>
            </c:forEach>
          </form:select>
          <div id="category" class="invalid-feedback">
            Category is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="buy" class="col-sm-4 col-form-label">Buying Price <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:input type="number" path="buyingPrice" class="form-control" id="buy" aria-describedby="buy"/>
          <div id="buy" class="invalid-feedback buy-mandatory">
            Buying Price is mandatory
          </div>
          <div id="buy" class="invalid-feedback buy-exceed">
            Buying Price must be between 1 and 5000
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="sale" class="col-sm-4 col-form-label">Selling Price <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:input type="number" path="sellingPrice" class="form-control" id="sale" aria-describedby="sale"/>
          <div id="sale" class="invalid-feedback sale-mandatory">
            Selling Price is mandatory
          </div>
          <div id="sale" class="invalid-feedback sale-exceed">
            Selling Price is invalid
          </div>
        </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-12 text-right">
          <button type="button" class="btn btn-secondary" id="back">Back</button>
          <button type="submit" class="btn btn-primary">${action}</button>
        </div>
      </div>
    </form:form>
  </div>
</body>
</html>
