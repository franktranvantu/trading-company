<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>Staff Management</title>
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
        <h4 class="card-title mb-0">All Staffs</h4>
        <a href="${contextPath}/staff/create-staff" class="btn btn-primary"><i class="fas fa-user-plus"></i> Add new</a>
      </div>
      <div class="card-body">
        <table id="staff" class="table">
          <thead class="thead-dark">
          <tr class="text-center">
            <th>Id</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="staff" items="${staffs}">
            <tr>
              <td class="text-center">${staff.id}</td>
              <td>${staff.name}</td>
              <td>${staff.address}</td>
              <td>${staff.phone}</td>
              <td>${staff.email}</td>
              <td class="text-center">
                <form class="mb-0" action="${contextPath}/staff/update-staff" method="post">
                  <input type="hidden" name="id" value="${staff.id}"/>
                  <button class="btn btn-sm btn-primary"><i class="fas fa-pencil-alt"></i></button>
                  <a href="" class="btn btn-sm btn-danger delete-staff-button" data-id="${staff.id}"><i
                      class="fas fa-trash"></i></a>
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Delete Staff Modal -->
    <div class="modal fade" id="delete-staff-modal" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Delete Staff</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete staff permanently?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-danger" id="confirm-delete-staff">Delete</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
