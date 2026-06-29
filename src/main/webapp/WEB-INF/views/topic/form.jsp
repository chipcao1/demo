<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>${topic.topicID == 0 ? 'Add Topic' : 'Edit Topic'}</h2>

<form:form action="${pageContext.request.contextPath}/topics/save" method="post" modelAttribute="topic" class="mt-3">
    <form:hidden path="topicID" />
    
    <div class="mb-3">
        <label class="form-label">Topic Code</label>
        <form:input path="topicCode" class="form-control" required="required" />
        <form:errors path="topicCode" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Topic Name</label>
        <form:input path="topicName" class="form-control" required="required" />
        <form:errors path="topicName" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Research Field</label>
        <form:input path="researchField" class="form-control" required="required" />
        <form:errors path="researchField" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Description</label>
        <form:textarea path="description" class="form-control" rows="3" />
    </div>
    
    <div class="row">
        <div class="col mb-3">
            <label class="form-label">Start Year</label>
            <form:input path="startYear" type="number" class="form-control" required="required" />
            <form:errors path="startYear" cssClass="text-danger small d-block mt-1" />
        </div>
        <div class="col mb-3">
            <label class="form-label">End Year</label>
            <form:input path="endYear" type="number" class="form-control" required="required" />
            <form:errors path="endYear" cssClass="text-danger small d-block mt-1" />
        </div>
    </div>
    
    <div class="mb-3">
        <label class="form-label">Max Students</label>
        <form:input path="maxStudents" type="number" class="form-control" required="required" />
        <form:errors path="maxStudents" cssClass="text-danger small d-block mt-1" />
    </div>
    
    <div class="mb-3">
        <label class="form-label">Status</label>
        <form:select path="status" class="form-select">
            <form:option value="Open">Open</form:option>
            <form:option value="Closed">Closed</form:option>
        </form:select>
    </div>
    
    <button type="submit" class="btn btn-success">Save Topic</button>
    <a href="${pageContext.request.contextPath}/topics" class="btn btn-secondary">Cancel</a>
</form:form>