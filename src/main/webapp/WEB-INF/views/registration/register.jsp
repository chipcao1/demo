<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<h2>Submit Topic Registration</h2>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/registrations/student/submit" method="post" class="mt-3">
    <div class="mb-3">
        <label class="form-label">Select Student (Simulated Login)</label>
        <select name="studentID" class="form-select" required="required">
            <c:forEach var="s" items="${students}">
                <option value="${s.studentID}">${s.studentCode} - ${s.fullName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Select Available Topic</label>
        <select name="topicID" class="form-select" required="required">
            <option value="">-- Choose Topic --</option>
            <c:forEach var="t" items="${topics}">
                <option value="${t.topicID}">${t.topicCode} - ${t.topicName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Participation Role</label>
        <select name="role" class="form-select">
            <option value="Team Member">Team Member</option>
            <option value="Team Leader">Team Leader</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Register Topic</button>
    <a href="${pageContext.request.contextPath}/topics" class="btn btn-secondary">Back to Topics</a>
</form>