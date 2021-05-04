<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>Inventory Management</title>
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
        <h4 class="card-title mb-0">All Inventories</h4>
        <a href="${contextPath}/inventory/create-inventory" class="btn btn-primary"><i class="fas fa-user-plus"></i> Add new</a>
      </div>
      <div class="card-body">
        <table id="inventory" class="table">
          <thead class="thead-dark">
          <tr class="text-center">
            <th>Id</th>
            <th>Name</th>
            <th>Address</th>
            <th>Products</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="inventory" items="${inventories}">
            <tr>
              <td class="text-center">${inventory.id}</td>
              <td>${inventory.name}</td>
              <td>${inventory.address}</td>
              <td>
                <c:forEach var="product" items="${inventory.products}">
                  <div class="pt-1 pb-1">${product.name}</div>
                </c:forEach>
              </td>
              <td class="text-center">
                <form class="mb-0" action="${contextPath}/inventory/update-inventory" method="post">
                  <input type="hidden" name="id" value="${inventory.id}"/>
                  <button class="btn btn-sm btn-primary"><i class="fas fa-pencil-alt"></i></button>
                  <a href="" class="btn btn-sm btn-danger delete-inventory-button" data-id="${inventory.id}"><i
                      class="fas fa-trash"></i></a>
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Delete Inventory Modal -->
    <div class="modal fade" id="delete-inventory-modal" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Delete Inventory</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete inventory permanently?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-danger" id="confirm-delete-inventory">Delete</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
