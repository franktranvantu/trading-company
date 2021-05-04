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
      <div class="card-header bg-transparent d-flex justify-content-between align-items-center">
        <h4 class="card-title mb-0">All Orders</h4>
        <a href="${contextPath}/order/create-order" class="btn btn-primary"><i class="fas fa-user-plus"></i> Add new</a>
      </div>
      <div class="card-body">
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
