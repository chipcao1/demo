<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<h2>Manage Student Registrations</h2>

<form action="${pageContext.request.contextPath}/registrations/admin" method="get" class="row g-3 mb-3">
    <div class="col-auto">
        <input type="text" name="search" class="form-control" value="${param.search}" placeholder="Search student or topic...">
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-secondary">Search</button>
    </div>
</form>

<table class="table table-bordered table-striped">
    <thead class="table-dark">
        <tr>
            <th>Student Code</th>
            <th>Student Name</th>
            <th>Topic Registered</th>
            <th>Role</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="r" items="${registrations}">
            <tr>
                <td>${r.studentCode}</td>
                <td>${r.studentName}</td>
                <td>${r.topicName}</td>
                <td>${r.participationRole}</td>
                <td>${r.registrationDate}</td>
                <td>
                    <span class="badge ${r.registrationStatus == 'Approved' ? 'bg-success' : (r.registrationStatus == 'Rejected' ? 'bg-danger' : 'bg-warning')}">
                        ${r.registrationStatus}
                    </span>
                </td>
                <td>
                    <c:if test="${r.registrationStatus == 'Pending'}">
                        <a href="${pageContext.request.contextPath}/registrations/admin/approve/${r.registrationID}" class="btn btn-sm btn-success">Approve</a>
                        <a href="${pageContext.request.contextPath}/registrations/admin/reject/${r.registrationID}" class="btn btn-sm btn-danger">Reject</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty registrations}">
            <tr>
                <td colspan="7" class="text-center text-muted">No registration records found.</td>
            </tr>
        </c:if>
    </tbody>
</table>