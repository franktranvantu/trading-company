<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>Product Management</title>
</head>
<body>
  <c:if test="${not empty result}">
    <div class="alert alert-${result.status == 'SUCCESS' ? 'success' : 'danger'} ml-auto position-absolute message"
         role="alert">
        ${result.message}
    </div>
  </c:if>

  <div class="container">
    <div class="card border-0">
      <div class="card-header bg-transparent d-flex justify-content-between align-items-center">
        <h4 class="card-title mb-0">All Products</h4>
        <a href="${contextPath}/product/create-product" class="btn btn-primary"><i class="fas fa-user-plus"></i> Add new</a>
      </div>
      <div class="card-body">
        <table id="product" class="table">
          <thead class="thead-dark">
          <tr class="text-center">
            <th>Id</th>
            <th>Name</th>
            <th>Model</th>
            <th>Brand</th>
            <th>Description</th>
            <th>Provider</th>
            <th>Category</th>
            <th>Buying Price</th>
            <th>Selling Price</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="product" items="${products}">
            <tr>
              <td class="text-center">${product.id}</td>
              <td>${product.name}</td>
              <td>${product.model}</td>
              <td>${product.brand}</td>
              <td>${product.description}</td>
              <td>${product.provider.name}</td>
              <td>${product.category.name}</td>
              <fmt:setLocale value="en_US"/>
              <td class="text-success">$<fmt:formatNumber value="${product.buyingPrice}"/></td>
              <td class="text-danger">$<fmt:formatNumber value="${product.sellingPrice}"/></td>
              <td class="text-center">
                <form class="mb-0" action="${contextPath}/product/update-product" method="post">
                  <input type="hidden" name="id" value="${product.id}"/>
                  <button class="btn btn-sm btn-primary"><i class="fas fa-pencil-alt"></i></button>
                  <a href="" class="btn btn-sm btn-danger delete-product-button" data-id="${product.id}"><i
                      class="fas fa-trash"></i></a>
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Delete Product Modal -->
    <div class="modal fade" id="delete-product-modal" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Delete Product</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete product permanently?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-danger" id="confirm-delete-product">Delete</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
