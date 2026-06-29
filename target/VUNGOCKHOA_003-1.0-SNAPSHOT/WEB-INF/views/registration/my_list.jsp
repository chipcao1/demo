<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>My Registered Topics (Đề tài tôi đã đăng ký)</h2>
    <a href="${pageContext.request.contextPath}/registrations/student/register" class="btn btn-primary btn-sm">Register Another Topic</a>
</div>

<table class="table table-striped table-bordered">
    <thead class="table-dark">
        <tr>
            <th>Topic Name</th>
            <th>Participation Role</th>
            <th>Registration Date</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="r" items="${registrations}">
            <tr>
                <td>${r.topicName}</td>
                <td>${r.participationRole}</td>
                <td>${r.registrationDate}</td>
                <td>
                    <span class="badge ${r.registrationStatus == 'Approved' ? 'bg-success' : (r.registrationStatus == 'Rejected' ? 'bg-danger' : 'bg-warning')}">
                        ${r.registrationStatus}
                    </span>
                </td>
            </tr>
        </c:forEach>
        
        <c:if test="${empty registrations}">
            <tr>
                <td colspan="4" class="text-center text-muted">You have not registered for any topics yet.</td>
            </tr>
        </c:if>
    </tbody>
</table>