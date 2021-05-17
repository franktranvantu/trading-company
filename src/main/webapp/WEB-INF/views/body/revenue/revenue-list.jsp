<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>Revenue Report</title>
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
          <h4 class="card-title mb-0">Search revenues</h4>
        </div>
        <form action="${contextPath}/revenue" method="get">
          <div class="row">
            <div class="col-3">
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
            <div class="col-3">
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
            <div class="col-4">
              <div class="form-group">
                <label for="date" class="form-label">Date Range</label>
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
          <h4 class="card-title mb-0">All Revenues</h4>
        </div>
        <table id="revenue" class="table">
          <thead class="thead-dark">
          <tr class="text-center">
            <th>No</th>
            <th>Product</th>
            <th>Buy</th>
            <th>Sale</th>
            <th>Balance</th>
          </tr>
          </thead>
          <tbody>
          <c:set var="no" value="0" />
          <c:forEach var="revenue" items="${revenues}">
            <c:set var="no" value="${no + 1}" />
            <tr>
              <td>${no}</td>
              <td>${revenue.product.name}</td>
              <fmt:setLocale value="en_US"/>
              <td class="text-success">${revenue.buyQuantity}</td>
              <td class="text-danger">${revenue.saleQuantity}</td>
              <td class="text-primary">$<fmt:formatNumber value="${revenue.balance}"/></td>
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
