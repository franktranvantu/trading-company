<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>${action} Staff</title>
</head>
<body>
  <c:if test="${not empty result}">
    <div class="alert alert-danger ml-auto position-absolute message" role="alert">
        ${result.message}
    </div>
  </c:if>

  <div class="container w-25">
    <h1 class="text-center mt-4 mb-4">${action} staff</h1>
    <form:form action="${contextPath}/staff/save-staff" method="post" modelAttribute="staff" id="save-staff">
      <form:hidden path="id"/>
      <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
          <form:input path="name" class="form-control" id="name" aria-describedby="name"/>
          <div id="name" class="invalid-feedback">
            Name is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="address" class="col-sm-2 col-form-label">Address</label>
        <div class="col-sm-10">
          <form:input path="address" class="form-control" id="address" aria-describedby="address"/>
          <div id="name" class="invalid-feedback">
            Address is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="phone" class="col-sm-2 col-form-label">Phone</label>
        <div class="col-sm-10">
          <form:input path="phone" class="form-control" id="phone" aria-describedby="phone"/>
          <div id="name" class="invalid-feedback">
            Phone is mandatory
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="email" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10">
          <form:input type="email" path="email" class="form-control" id="email" aria-describedby="email"/>
          <div id="email" class="invalid-feedback">
            Email is mandatory
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
