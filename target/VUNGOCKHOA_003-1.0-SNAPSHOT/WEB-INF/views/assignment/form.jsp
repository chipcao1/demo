<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>${assignment.assignmentID == 0 ? 'Assign Lecturer to Topic' : 'Update Assignment'}</h2>

<form:form action="${pageContext.request.contextPath}/assignments/save" method="post" modelAttribute="assignment" class="mt-3">
    <form:hidden path="assignmentID" />

    <div class="mb-3">
        <label class="form-label">Select Topic (Chọn đề tài)</label>
        <form:select path="topicID" class="form-select" required="required">
            <form:option value="0" label="-- Choose Topic --"/>
            <c:forEach var="t" items="${topics}">
                <form:option value="${t.topicID}">${t.topicCode} - ${t.topicName}</form:option>
            </c:forEach>
        </form:select>
        <form:errors path="topicID" cssClass="text-danger small d-block mt-1" />
    </div>

    <div class="mb-3">
        <label class="form-label">Select Supervising Lecturer (Chọn giảng viên hướng dẫn)</label>
        <form:select path="lecturerID" class="form-select" required="required">
            <form:option value="0" label="-- Choose Lecturer --"/>
            <c:forEach var="l" items="${lecturers}">
                <form:option value="${l.lecturerID}">${l.lecturerCode} - ${l.fullName}</form:option>
            </c:forEach>
        </form:select>
        <form:errors path="lecturerID" cssClass="text-danger small d-block mt-1" />
    </div>

    <button type="submit" class="btn btn-primary">Save Assignment</button>
    <a href="${pageContext.request.contextPath}/assignments" class="btn btn-secondary">Cancel</a>
</form:form>