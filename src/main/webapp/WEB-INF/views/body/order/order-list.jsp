<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>Order Management</title>
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
          <h4 class="card-title mb-0">Search orders</h4>
        </div>
        <form action="${contextPath}/order" method="get">
          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="staff" class="form-label">Staff</label>
                <select class="form-control" name="staff" id="staff">
                  <option value="">All</option>
                  <c:forEach var="staff" items="${staffs}">
                    <option value="${staff.id}" ${staff.id == selectedStaff.id ? 'selected' : ''}>${staff.name}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="col">
              <div class="form-group">
                <label for="product" class="form-label">Product</label>
                <select class="form-control" name="product" id="product">
                  <option value="">All</option>
                  <c:forEach var="product" items="${products}">
                    <option value="${product.id}" ${product.id == selectedProduct.id ? 'selected' : ''}>${product.name}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="col">
              <div class="form-group">
                <label for="provider" class="form-label">Provider</label>
                <select class="form-control" name="provider" id="provider">
                  <option value="">All</option>
                  <c:forEach var="provider" items="${providers}">
                    <option value="${provider.id}" ${provider.id == selectedProvider.id ? 'selected' : ''}>${provider.name}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="col">
              <div class="form-group">
                <label for="customer" class="form-label">Customer</label>
                <select class="form-control" name="customer" id="customer">
                  <option value="">All</option>
                  <c:forEach var="customer" items="${customers}">
                    <option value="${customer.id}" ${customer.id == selectedCustomer.id ? 'selected' : ''}>${customer.name}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-3">
              <div class="form-group">
                <label for="inventory" class="form-label">Inventory</label>
                <select class="form-control" name="inventory" id="inventory">
                  <option value="">All</option>
                  <c:forEach var="inventory" items="${inventories}">
                    <option value="${inventory.id}" ${inventory.id == selectedInventory.id ? 'selected' : ''}>${inventory.name}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="col-4">
              <div class="form-group">
                <label for="date" class="form-label">Date Time Range</label>
                <c:set var="selectedDate" value="" />
                <c:if test="${not empty date}">
                  <c:set var="selectedDate"><spring:eval expression="date" /></c:set>
                </c:if>
                <input type="input" name="date" class="form-control" id="date" value="${selectedDate}">
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
          <h4 class="card-title mb-0">All Orders</h4>
          <a href="${contextPath}/order/create-order" class="btn btn-primary"><i class="fas fa-user-plus"></i> Add new</a>
        </div>
        <table id="order" class="table">
          <thead class="thead-dark">
          <tr class="text-center">
            <th>Id</th>
            <th>Date Time</th>
            <th>Staff</th>
            <th>Product</th>
            <th>Provider/Customer</th>
            <th>Inventory</th>
            <th>Quantity</th>
            <th>Type</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="order" items="${orders}">
            <tr>
              <td class="text-center">${order.id}</td>
              <td class="text-center"><spring:eval expression="order.dateTime"/></td>
              <td>${order.staff.name}</td>
              <td>${order.product.name}</td>
              <td>
                <c:choose>
                  <c:when test="${order.type == 'BUY'}">
                    <div class="text-success">${order.product.provider.name}</div>
                  </c:when>
                  <c:when test="${order.type == 'SALE'}">
                    <div class="text-danger">${order.customer.name}</div>
                  </c:when>
                </c:choose>
              </td>
              <td>${order.inventory.name}</td>
              <td>${order.quantity}</td>
              <td class="text-center"><span class="badge badge-${order.type == 'BUY' ? 'success' : 'danger'}">${order.type}</span></td>
              <td class="text-center">
                <form class="mb-0" action="${contextPath}/order/update-order" method="post">
                  <input type="hidden" name="id" value="${order.id}"/>
                  <button class="btn btn-sm btn-primary"><i class="fas fa-pencil-alt"></i></button>
                  <a href="" class="btn btn-sm btn-danger delete-order-button" data-id="${order.id}"><i
                      class="fas fa-trash"></i></a>
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Delete Order Modal -->
    <div class="modal fade" id="delete-order-modal" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Delete Order</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete order permanently?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-danger" id="confirm-delete-order">Delete</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
