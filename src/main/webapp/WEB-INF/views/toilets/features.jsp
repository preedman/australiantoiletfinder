<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Toilet Features</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <style>
        .feature-label { min-width: 240px; }
    </style>
</head>
<body class="bg-light">
<div class="container py-4">

    <div class="d-flex align-items-center justify-content-between mb-3">
        <div>
            <h1 class="h4 mb-1">Features checklist</h1>
            <div class="text-muted">
                Toilet ID: <c:out value="${toilet.id}"/>
                <c:if test="${not empty toilet.name}">
                    — <c:out value="${toilet.name}"/>
                </c:if>
            </div>
        </div>

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
        <a class="btn btn-outline-secondary"
           href="${backUrl}">
            Back to list
        </a>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">

            <c:choose>
                <c:when test="${feature == null}">
                    <div class="alert alert-warning mb-0">
                        No feature record found for this toilet.
                    </div>
                </c:when>

                <c:otherwise>

                    <div class="row g-3 mb-3">
                        <div class="col-md-4">
                            <div class="small text-muted">Feature record id</div>
                            <div class="fw-semibold"><c:out value="${feature.id}"/></div>
                        </div>
                        <div class="col-md-4">
                            <div class="small text-muted">Start date</div>
                            <div class="fw-semibold"><c:out value="${feature.startDate}"/></div>
                        </div>
                        <div class="col-md-4">
                            <div class="small text-muted">End date</div>
                            <div class="fw-semibold">
                                <c:choose>
                                    <c:when test="${feature.endDate != null}">
                                        <c:out value="${feature.endDate}"/>
                                    </c:when>
                                    <c:otherwise>Active</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-sm table-striped align-middle mb-0">
                            <thead class="table-dark">
                            <tr>
                                <th class="feature-label">Feature</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>

                            <!-- Helper pattern: true => green, false => gray, null => yellow -->
                            <tr>
                                <td class="feature-label">Accessible</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.accessible == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.accessible == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Ambulant</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.ambulant == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.ambulant == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Left-hand transfer</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.lhtransfer == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.lhtransfer == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Right-hand transfer</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.rhtransfer == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.rhtransfer == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Baby change</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.babyChange == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.babyChange == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Baby care room</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.babyCareRoom == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.babyCareRoom == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Adult change</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.adultChange == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.adultChange == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Changing Places</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.changingPlaces == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.changingPlaces == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Shower</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.shower == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.shower == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Parking</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.parking == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.parking == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Parking accessible</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.parkingAccessible == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.parkingAccessible == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Key required</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.keyRequired == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.keyRequired == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Payment required</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.paymentRequired == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.paymentRequired == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">MLAK 24</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.mlak24 == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.mlak24 == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">MLAK after hours</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.mlakafterhours == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.mlakafterhours == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Male</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.male == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.male == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Female</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.female == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.female == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">All gender</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.allgender == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.allgender == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Drinking water</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.drinkingWater == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.drinkingWater == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Sharp disposal</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.sharpDisposal == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.sharpDisposal == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Sanitary disposal</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.sanitaryDisposal == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.sanitaryDisposal == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Men's pad disposal</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.mensPadDisposal == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.mensPadDisposal == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Dump point</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.dumpPoint == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.dumpPoint == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Dump point washout</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.dpwashout == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.dpwashout == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Dump point after hours</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.dpafterhours == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.dpafterhours == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Assisted shower (acshower)</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.acshower == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.acshower == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Assisted MLAK (acmlak)</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.acmlak == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.acmlak == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <td class="feature-label">Bring your own sling (byosling)</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${feature.byosling == true}">
                                            <span class="badge text-bg-success">Yes</span>
                                        </c:when>
                                        <c:when test="${feature.byosling == false}">
                                            <span class="badge text-bg-secondary">No</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge text-bg-warning">Unknown</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>

                </c:otherwise>
            </c:choose>

        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
