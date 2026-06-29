<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>${student.studentID == 0 ? 'Add Student' : 'Edit Student'}</h3>

<form:form action="${pageContext.request.contextPath}/students/save" method="post" modelAttribute="student" class="mt-3">
    <form:hidden path="studentID" />
    
    <div class="mb-3">
        <label class="form-label">Student Code</label>
        <form:input path="studentCode" class="form-control" />
        <form:errors path="studentCode" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Full Name</label>
        <form:input path="fullName" class="form-control" />
        <form:errors path="fullName" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Major</label>
        <form:input path="major" class="form-control" />
        <form:errors path="major" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Email</label>
        <form:input path="email" type="email" class="form-control" />
        <form:errors path="email" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Phone Number</label>
        <form:input path="phoneNumber" class="form-control" />
        <form:errors path="phoneNumber" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <button type="submit" class="btn btn-success">Save</button>
    <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">Cancel</a>
</form:form>