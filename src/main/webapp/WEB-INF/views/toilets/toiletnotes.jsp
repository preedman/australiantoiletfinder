<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Toilet Notes</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <style>
        .note-label { font-weight: bold; width: 200px; }
        .note-content { white-space: pre-wrap; }
    </style>
</head>
<body class="bg-light">
<div class="container py-4">
    <div class="d-flex align-items-center justify-content-between mb-3">
        <h1 class="h3 mb-0">Notes for <c:out value="${toilet.name}"/></h1>
        <c:url var="backUrl" value="/toilets${not empty lat ? '/nearby' : ''}">
            <c:if test="${not empty page}">
                <c:param name="page" value="${page}"/>
            </c:if>
            <c:if test="${not empty size}">
                <c:param name="size" value="${size}"/>
            </c:if>
            <c:if test="${not empty lat}">
                <c:param name="lat" value="${lat}"/>
                <c:param name="lon" value="${lon}"/>
            </c:if>
        </c:url>
        <a href="${backUrl}" class="btn btn-outline-secondary">Back to List</a>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">
            <dl class="row">
                <dt class="col-sm-3 note-label">addressNote</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.addressNote}" default="-"/></dd>

                <dt class="col-sm-3 note-label">parkingNote</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.parkingNote}" default="-"/></dd>

                <dt class="col-sm-3 note-label">accessNote</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.accessNote}" default="-"/></dd>

                <dt class="col-sm-3 note-label">adultChangeNote</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.adultChangeNote}" default="-"/></dd>

                <dt class="col-sm-3 note-label">babyChangeNote</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.babyChangeNote}" default="-"/></dd>

                <dt class="col-sm-3 note-label">dumpPointNote</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.dumpPointNote}" default="-"/></dd>

                <dt class="col-sm-3 note-label">openingHours</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.openingHours}" default="-"/></dd>

                <dt class="col-sm-3 note-label">openingHoursNote</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.openingHoursNote}" default="-"/></dd>

                <dt class="col-sm-3 note-label">toiletNote</dt>
                <dd class="col-sm-9 note-content"><c:out value="${toilet.toiletNote}" default="-"/></dd>
            </dl>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
