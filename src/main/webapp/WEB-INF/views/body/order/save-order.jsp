<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>${action} Order</title>
</head>
<body>
  <c:if test="${not empty result}">
    <div class="alert alert-danger ml-auto position-absolute message" role="alert">
        ${result.message}
    </div>
  </c:if>

  <div class="container w-25">
    <h1 class="text-center mt-4 mb-4">${action} Order</h1>
    <form:form action="${contextPath}/order/save-order" method="post" modelAttribute="order" id="save-order">
      <form:hidden path="id"/>
      <div class="form-group row">
        <label for="staff" class="col-sm-3 col-form-label">Staff <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:select path="staff" class="form-control" id="staff" aria-describedby="staff">
            <c:forEach var="staff" items="${staffs}">
              <option value="${staff.id}" ${order.staff.id == staff.id ? 'selected' : ''}>${staff.name}</option>
            </c:forEach>
          </form:select>
          <div id="staff" class="invalid-feedback">
            Staff is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="type" class="col-sm-3 col-form-label">Type <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:select path="type" class="form-control" id="type" aria-describedby="type">
            <option value="BUY" ${order.type == 'BUY' ? 'selected' : ''}>Buy</option>
            <option value="SALE" ${order.type == 'SALE' ? 'selected' : ''}>Sale</option>
          </form:select>
          <div id="type" class="invalid-feedback">
            Type is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row" id="customer-row">
        <label for="customer" class="col-sm-3 col-form-label">Customer <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:select path="customer" class="form-control" id="customer" aria-describedby="customer">
            <c:forEach var="customer" items="${customers}">
              <option value="${customer.id}" ${order.customer.id == customer.id ? 'selected' : ''}>${customer.name}</option>
            </c:forEach>
          </form:select>
          <div id="customer" class="invalid-feedback">
            Customer is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row" id="provider-row">
        <label for="provider" class="col-sm-3 col-form-label">Provider <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:select path="product.provider" class="form-control" id="provider" aria-describedby="provider">
            <c:forEach var="provider" items="${providers}">
              <option value="${provider.id}" ${order.product.provider.id == provider.id ? 'selected' : ''}>${provider.name}</option>
            </c:forEach>
          </form:select>
          <div id="provider" class="invalid-feedback">
            Provider is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="product" class="col-sm-3 col-form-label">Product <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:select path="product" class="form-control" id="product" aria-describedby="product">
            <c:forEach var="product" items="${products}">
              <option value="${product.id}" ${order.product.id == product.id ? 'selected' : ''}>${product.name}</option>
            </c:forEach>
          </form:select>
          <div id="product" class="invalid-feedback">
            Product is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="inventory" class="col-sm-3 col-form-label">Inventory <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:select path="inventory" class="form-control" id="inventory" aria-describedby="inventory">
            <c:forEach var="inventory" items="${inventories}">
              <option value="${inventory.id}" ${order.inventory.id == inventory.id ? 'selected' : ''}>${inventory.name}</option>
            </c:forEach>
          </form:select>
          <div id="inventory" class="invalid-feedback">
            Inventory is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="quantity" class="col-sm-3 col-form-label">Quantity <span class="text-danger">*</span></label>
        <div class="col-sm">
          <form:input type="number" path="quantity" class="form-control" id="quantity" aria-describedby="quantity"/>
          <div id="quantity" class="invalid-feedback mandatory">
            Quantity is mandatory
          </div>
          <div id="quantity" class="invalid-feedback exceed">
            Quantity is must be between 1 and 1000
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
