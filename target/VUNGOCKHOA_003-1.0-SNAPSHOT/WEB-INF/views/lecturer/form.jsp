<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>${lecturer.lecturerID == 0 ? 'Add Lecturer' : 'Edit Lecturer'}</h3>

<form:form action="${pageContext.request.contextPath}/lecturers/save" method="post" modelAttribute="lecturer" class="mt-3">
    <form:hidden path="lecturerID" />
    
    <div class="mb-3">
        <label class="form-label">Lecturer Code</label>
        <form:input path="lecturerCode" class="form-control" />
        <form:errors path="lecturerCode" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Full Name</label>
        <form:input path="fullName" class="form-control" />
        <form:errors path="fullName" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Faculty</label>
        <form:input path="faculty" class="form-control" />
        <form:errors path="faculty" cssClass="text-danger small d-block mt-1" />
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
    <a href="${pageContext.request.contextPath}/lecturers" class="btn btn-secondary">Cancel</a>
</form:form>