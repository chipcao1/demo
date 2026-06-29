<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Graduation Project Topics</h2>
    <a href="${pageContext.request.contextPath}/topics/add" class="btn btn-primary">Add New Topic</a>
</div>

<form action="${pageContext.request.contextPath}/topics" method="get" class="row g-3 mb-3">
    <div class="col-auto">
        <input type="text" name="search" class="form-control" value="${param.search}" placeholder="Search code or name...">
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-secondary">Search</button>
    </div>
</form>

<table class="table table-striped table-bordered">
    <thead class="table-dark">
        <tr>
            <th>Code</th>
            <th>Topic Name</th>
            <th>Research Field</th>
            <th>Duration</th>
            <th>Max Students</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="t" items="${topics}">
            <tr>
                <td>${t.topicCode}</td>
                <td>${t.topicName}</td>
                <td>${t.researchField}</td>
                <td>${t.startYear} - ${t.endYear}</td>
                <td>${t.maxStudents}</td>
                <td><span class="badge ${t.status == 'Open' ? 'bg-success' : 'bg-danger'}">${t.status}</span></td>
                <td>
                    <a href="${pageContext.request.contextPath}/topics/edit/${t.topicID}" class="btn btn-sm btn-warning">Edit</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty topics}">
            <tr>
                <td colspan="7" class="text-center text-muted">No topics found.</td>
            </tr>
        </c:if>
    </tbody>
</table>