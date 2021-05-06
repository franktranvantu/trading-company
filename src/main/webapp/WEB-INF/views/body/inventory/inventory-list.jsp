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
      <div class="card-header bg-transparent">
        <div class="mt-2 mb-4 d-flex">
          <h4 class="card-title mb-0">Search inventories</h4>
        </div>
        <form action="${contextPath}/inventory" method="get">
          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="name" class="form-label">Name</label>
                <input type="input" name="name" class="form-control" id="name">
              </div>
            </div>
            <div class="col">
              <div class="form-group">
                <label for="address" class="form-label">Address</label>
                <input type="input" name="address" class="form-control" id="address">
              </div>
            </div>
            <div class="col">
              <div class="form-group">
                <label for="product" class="form-label">Product</label>
                <input type="input" name="product" class="form-control" id="product">
              </div>
            </div>
            <div class="col-auto d-flex align-items-end justify-content-end">
              <div class="form-group">
                <button type="submit" class="btn btn-success"><i class="fas fa-search"></i> Search</button>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="card-body">
        <div class="mt-4 mb-4 d-flex justify-content-between align-items-center">
          <h4 class="card-title mb-0">All Inventories</h4>
          <a href="${contextPath}/inventory/create-inventory" class="btn btn-primary"><i class="fas fa-user-plus"></i> Add new</a>
        </div>
        <table id="inventory" class="table">
          <thead class="thead-dark">
          <tr class="text-center">
            <th>Id</th>
            <th>Name</th>
            <th>Address</th>
            <th>Product</th>
            <th>Quantity</th>
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
                <c:forEach var="orderDetails" items="${inventory.orderDetails}">
                  <div class="pt-1 pb-1">${orderDetails.product.name}</div>
                </c:forEach>
              </td>
              <td class="text-center">
                <c:forEach var="orderDetails" items="${inventory.orderDetails}">
                  <div class="pt-1 pb-1">${orderDetails.quantity}</div>
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
