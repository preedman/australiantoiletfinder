<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Toilets</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <style>
        .truncate {
            max-width: 260px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        th, td { vertical-align: top; }
    </style>
</head>
<body class="bg-light">
<div class="container-fluid py-4">
    <div class="d-flex align-items-center justify-content-between mb-3">
        <h1 class="h3 mb-0">Toilets</h1>
        <span class="text-muted">
            Total: <c:out value="${toiletPage.totalElements}"/>
        </span>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover table-sm align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Actions</th>

                        <th>id</th>
                        <th>facilityid</th>
                        <th>url</th>
                        <th>name</th>
                        <th>address1</th>
                        <th>town</th>
                        <th>state</th>
                        <th>addressNote</th>
                        <th>latitude</th>
                        <th>longitude</th>
                        <th>parkingNote</th>
                        <th>accessNote</th>
                        <th>adultChangeNote</th>
                        <th>babyChangeNote</th>
                        <th>dumpPointNote</th>
                        <th>openingHours</th>
                        <th>openingHoursNote</th>
                        <th>toiletNote</th>
                        <th>startDate</th>
                        <th>endDate</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${toiletPage.content}" var="t">
                        <tr>
                            <td class="text-nowrap">
                                <a class="btn btn-outline-primary btn-sm"
                                   href="${pageContext.request.contextPath}/toilets/${t.id}/features">
                                    Features
                                </a>
                            </td>

                            <td><c:out value="${t.id}"/></td>
                            <td><c:out value="${t.facilityid}"/></td>

                            <td class="truncate">
                                <c:choose>
                                    <c:when test="${not empty t.url}">
                                        <a href="<c:out value='${t.url}'/>" target="_blank" rel="noopener">
                                            <c:out value="${t.url}"/>
                                        </a>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>

                            <td class="truncate"><c:out value="${t.name}"/></td>
                            <td class="truncate"><c:out value="${t.address1}"/></td>
                            <td><c:out value="${t.town}"/></td>
                            <td><c:out value="${t.state}"/></td>

                            <td class="truncate"><c:out value="${t.addressNote}"/></td>
                            <td><c:out value="${t.latitude}"/></td>
                            <td><c:out value="${t.longitude}"/></td>

                            <td class="truncate"><c:out value="${t.parkingNote}"/></td>
                            <td class="truncate"><c:out value="${t.accessNote}"/></td>
                            <td class="truncate"><c:out value="${t.adultChangeNote}"/></td>
                            <td class="truncate"><c:out value="${t.babyChangeNote}"/></td>
                            <td class="truncate"><c:out value="${t.dumpPointNote}"/></td>

                            <td class="truncate"><c:out value="${t.openingHours}"/></td>
                            <td class="truncate"><c:out value="${t.openingHoursNote}"/></td>
                            <td class="truncate"><c:out value="${t.toiletNote}"/></td>

                            <td><c:out value="${t.startDate}"/></td>
                            <td><c:out value="${t.endDate}"/></td>
                        </tr>
                    </c:forEach>

                    <c:if test="${toiletPage.totalElements == 0}">
                        <tr>
                            <td colspan="21" class="text-center text-muted py-4">
                                No toilets found.
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>

            <!-- Paging -->
            <c:if test="${toiletPage.totalPages > 1}">
                <nav aria-label="Toilets pagination">
                    <ul class="pagination justify-content-center mb-0">

                        <li class="page-item <c:out value='${toiletPage.first ? "disabled" : ""}'/>">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/toilets?page=${toiletPage.number - 1}&size=${toiletPage.size}">
                                Previous
                            </a>
                        </li>

                        <c:set var="current" value="${toiletPage.number}"/>
                        <c:set var="total" value="${toiletPage.totalPages}"/>

                        <!-- show a simple window of pages around current -->
                        <c:set var="start" value="${current - 2}"/>
                        <c:if test="${start < 0}">
                            <c:set var="start" value="0"/>
                        </c:if>

                        <c:set var="end" value="${current + 2}"/>
                        <c:if test="${end > total - 1}">
                            <c:set var="end" value="${total - 1}"/>
                        </c:if>

                        <c:forEach var="i" begin="${start}" end="${end}">
                            <li class="page-item <c:out value='${i == current ? "active" : ""}'/>">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/toilets?page=${i}&size=${toiletPage.size}">
                                    <c:out value="${i + 1}"/>
                                </a>
                            </li>
                        </c:forEach>

                        <li class="page-item <c:out value='${toiletPage.last ? "disabled" : ""}'/>">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/toilets?page=${toiletPage.number + 1}&size=${toiletPage.size}">
                                Next
                            </a>
                        </li>

                    </ul>
                </nav>
            </c:if>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
