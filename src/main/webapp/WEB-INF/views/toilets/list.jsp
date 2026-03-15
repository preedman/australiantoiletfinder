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
        .wrap {
            max-width: 260px;
            white-space: normal;
            word-wrap: break-word;
        }
        th, td { vertical-align: top; }
    </style>
</head>
<body class="bg-light">
<div class="container-fluid py-4">
    <div class="d-flex align-items-center justify-content-between mb-3">
        <h1 class="h3 mb-0">Toilets</h1>
        <div class="d-flex align-items-center gap-3">
            <c:if test="${not empty lat}">
                <a href="${pageContext.request.contextPath}/toilets" class="btn btn-outline-secondary btn-sm">Refresh</a>
            </c:if>
            <button id="findNearbyBtn" class="btn btn-primary btn-sm">Find Nearby</button>
            <span class="text-muted">
                Total: <c:out value="${toiletPage.totalElements}"/>
            </span>
            <form action="${pageContext.request.contextPath}/logout" method="post" class="mb-0">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-outline-danger btn-sm">Logout</button>
            </form>
        </div>
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
                                <a class="btn btn-outline-info btn-sm"
                                   href="${pageContext.request.contextPath}/toilets/${t.id}/notes">
                                    Notes
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

                            <td><c:out value="${t.startDate}"/></td>
                            <td><c:out value="${t.endDate}"/></td>
                        </tr>
                    </c:forEach>

                    <c:if test="${toiletPage.totalElements == 0}">
                        <tr>
                            <td colspan="10" class="text-center text-muted py-4">
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
                            <c:url var="prevUrl" value="/toilets${not empty lat ? '/nearby' : ''}">
                                <c:param name="page" value="${toiletPage.number - 1}"/>
                                <c:param name="size" value="${toiletPage.size}"/>
                                <c:if test="${not empty lat}">
                                    <c:param name="lat" value="${lat}"/>
                                    <c:param name="lon" value="${lon}"/>
                                </c:if>
                            </c:url>
                            <a class="page-link" href="${prevUrl}">
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
                                <c:url var="pUrl" value="/toilets${not empty lat ? '/nearby' : ''}">
                                    <c:param name="page" value="${i}"/>
                                    <c:param name="size" value="${toiletPage.size}"/>
                                    <c:if test="${not empty lat}">
                                        <c:param name="lat" value="${lat}"/>
                                        <c:param name="lon" value="${lon}"/>
                                    </c:if>
                                </c:url>
                                <a class="page-link" href="${pUrl}">
                                    <c:out value="${i + 1}"/>
                                </a>
                            </li>
                        </c:forEach>

                        <li class="page-item <c:out value='${toiletPage.last ? "disabled" : ""}'/>">
                            <c:url var="nextUrl" value="/toilets${not empty lat ? '/nearby' : ''}">
                                <c:param name="page" value="${toiletPage.number + 1}"/>
                                <c:param name="size" value="${toiletPage.size}"/>
                                <c:if test="${not empty lat}">
                                    <c:param name="lat" value="${lat}"/>
                                    <c:param name="lon" value="${lon}"/>
                                </c:if>
                            </c:url>
                            <a class="page-link" href="${nextUrl}">
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
<script>
    document.getElementById('findNearbyBtn').addEventListener('click', function () {
        if (!navigator.geolocation) {
            alert('Geolocation is not supported by your browser');
            return;
        }

        const btn = this;
        const originalText = btn.textContent;
        btn.disabled = true;
        btn.textContent = 'Locating...';

        navigator.geolocation.getCurrentPosition(
            function (position) {
                const lat = position.coords.latitude;
                const lon = position.coords.longitude;
                // Redirect to a nearby search endpoint (to be implemented)
                window.location.href = '${pageContext.request.contextPath}/toilets/nearby?lat=' + lat + '&lon=' + lon;
            },
            function (error) {
                btn.disabled = false;
                btn.textContent = originalText;
                let errorMsg = 'Unable to retrieve your location';
                switch (error.code) {
                    case error.PERMISSION_DENIED:
                        errorMsg = "User denied the request for Geolocation.";
                        break;
                    case error.POSITION_UNAVAILABLE:
                        errorMsg = "Location information is unavailable.";
                        break;
                    case error.TIMEOUT:
                        errorMsg = "The request to get user location timed out.";
                        break;
                }
                alert(errorMsg);
            }
        );
    });
</script>
</body>
</html>
