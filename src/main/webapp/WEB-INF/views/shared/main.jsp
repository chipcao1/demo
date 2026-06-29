<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Graduation Project Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="#">GP System</a>
            <div class="navbar-nav">
                <a class="nav-link" href="${pageContext.request.contextPath}/topics">Topics</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/lecturers">Lecturers</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/students">Students</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/assignments">Assignments</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/registrations/student/register">Topic Registration</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/registrations/admin">Manage Registrations</a>
            </div>
        </div>
    </nav>
    <div class="container bg-white p-4 rounded shadow-sm">
        <jsp:include page="${body}" />
    </div>
</body>
</html>