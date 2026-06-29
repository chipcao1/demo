<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Lecturer Assignments</h3>
    <a href="${pageContext.request.contextPath}/assignments/add" class="btn btn-primary btn-sm">Assign Lecturer</a>
</div>

<table class="table table-striped table-bordered">
    <thead class="table-dark">
        <tr>
            <th>Assignment ID</th>
            <th>Topic Code</th>
            <th>Topic Name</th>
            <th>Lecturer Name</th>
            <th>Assignment Date</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="a" items="${assignments}">
            <tr>
                <td>${a.assignmentID}</td>
                <td><c:out value="${a.topicCode}"/></td>
                <td><c:out value="${a.topicName}"/></td>
                <td><c:out value="${a.lecturerName}"/></td>
                <td>${a.assignmentDate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/assignments/edit/${a.assignmentID}" class="btn btn-sm btn-warning">Update Assignment</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty assignments}">
            <tr>
                <td colspan="6" class="text-center text-muted">No assignments found.</td>
            </tr>
        </c:if>
    </tbody>
</table>