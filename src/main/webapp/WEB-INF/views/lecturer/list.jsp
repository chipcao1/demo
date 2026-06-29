<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Lecturer Management</h3>
    <a href="${pageContext.request.contextPath}/lecturers/add" class="btn btn-primary btn-sm">Add New Lecturer</a>
</div>

<table class="table table-striped table-bordered">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Lecturer Code</th>
            <th>Full Name</th>
            <th>Faculty</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="l" items="${lecturers}">
            <tr>
                <td>${l.lecturerID}</td>
                <td>${l.lecturerCode}</td>
                <td>${l.fullName}</td>
                <td>${l.faculty}</td>
                <td>${l.email}</td>
                <td>${l.phoneNumber}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/lecturers/edit/${l.lecturerID}" class="btn btn-sm btn-warning">Edit</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty lecturers}">
            <tr>
                <td colspan="7" class="text-center text-muted">No lecturers found.</td>
            </tr>
        </c:if>
    </tbody>
</table>