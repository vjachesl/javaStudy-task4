<%@ include file="/view/includePages/tag_direct.jspf" %>
<%@ include file="/view/includePages/page_direct.jspf" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf" %>
<h3>List of All Stops</h3>
<br>
<c:choose>
    <c:when test="${not empty admin}">
        <div id="list_stops">
            <table style="margin: auto">
                <tr>
                    <th>Stop #</th>
                    <th>Stop Name</th>
                    <th>Stop Geopoint</th>

                </tr>
                <c:forEach items="${stops}" var="stop">
                    <tr>
                        <td><c:out value="${stop.STOP_ID()}"></c:out></td>
                        <td><c:out value="${stop.getNameEn()}"></c:out></td>
                        <td><c:out value="${stop.getGeoPoint().getLat()}"></c:out></td>
                        <td><c:out value="${stop.getGeoPoint().getLng()}"></c:out></td>

                    </tr>
                </c:forEach>
                <tr>
                    <hr>
                </tr>
                <tr></tr>
            </table>
        </div>
        <form action="admin_cabinet" accept-charset="UTF-8" method="post">
            <input type="submit" value="Back to the Cabinet">
        </form>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf" %>
</body>
</html>