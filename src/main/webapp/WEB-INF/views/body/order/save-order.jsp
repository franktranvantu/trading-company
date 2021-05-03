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
        <label for="staff" class="col-sm-3 col-form-label">Staff</label>
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
        <label for="customer" class="col-sm-3 col-form-label">Customer</label>
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
      <div class="form-group row">
        <label for="quantity" class="col-sm-3 col-form-label">Quantity</label>
        <div class="col-sm">
          <form:input type="number" path="quantity" class="form-control" id="quantity" aria-describedby="quantity"/>
          <div id="quantity" class="invalid-feedback">
            Quantity is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="type" class="col-sm-3 col-form-label">Type</label>
        <div class="col-sm">
          <form:select path="type" class="form-control" id="type" aria-describedby="type">
            <option value="BUY" ${order.type == 'BUY' ? 'selected' : ''}>Buy</option>
            <option value="SALE" ${order.type == 'BUY' ? 'selected' : ''}>Sale</option>
          </form:select>
          <div id="type" class="invalid-feedback">
            Type is mandatory
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
