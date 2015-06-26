<%@ include file="/view/includePages/tag_direct.jspf" %>
<%@ include file="/view/includePages/page_direct.jspf" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/loc_login.jspf" %>
<h3><fmt:message key="routes_jsp.label.routes_list"></fmt:message></h3>

<div id="routs-result">
    <c:choose>
        <c:when test="${empty routes}">
            <h3><fmt:message key="routes_jsp.label.error"></fmt:message></h3>
        </c:when>
        <c:otherwise>
            <form action="route_detailes" accept-charset="UTF-8" method="post">
                <table style="margin: auto">
                    <tr>
                        <th><fmt:message key="routes_jsp.label.route_number"></fmt:message></th>
                        <th></th>
                        <th><fmt:message key="routes_jsp.label.route_name"></fmt:message></th>
                        <th></th>
                        <th><fmt:message key="routes_jsp.label.route_transport_type"></fmt:message></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${routes}" var="route">
                        <tr>
                            <td><c:out value="${route.ROUTE_ID()}"></c:out></td>
                            <td></td>
                            <td><c:out
                                    value="${language eq 'ru' ? route.ROUTE_NAME_RU() : route.ROUTE_NAME_EN()}"></c:out></td>
                            <td></td>
                            <td><c:out
                                    value="${language eq 'ru' ? route.ROUTE_TRANSPORT_TYPE().getNameRu() : route.ROUTE_TRANSPORT_TYPE().getNameEn()}"></c:out></td>
                            <td><input type="submit" class="button-accept" name="${route.ROUTE_ID()}" value=<fmt:message
                                    key="routes_jsp.button.detailes"></fmt:message>></td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
            <p><fmt:message key="routes_jsp.label.using"></fmt:message></p>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="/view/includePages/foot.jspf" %>
</body>
</html>
