<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Student Management</h3>
    <a href="${pageContext.request.contextPath}/students/add" class="btn btn-primary btn-sm">Add New Student</a>
</div>

<table class="table table-striped table-bordered">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Student Code</th>
            <th>Full Name</th>
            <th>Major</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="s" items="${students}">
            <tr>
                <td>${s.studentID}</td>
                <td>${s.studentCode}</td>
                <td>${s.fullName}</td>
                <td>${s.major}</td>
                <td>${s.email}</td>
                <td>${s.phoneNumber}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/students/edit/${s.studentID}" class="btn btn-sm btn-warning">Edit</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty students}">
            <tr>
                <td colspan="7" class="text-center text-muted">No students found.</td>
            </tr>
        </c:if>
    </tbody>
</table>