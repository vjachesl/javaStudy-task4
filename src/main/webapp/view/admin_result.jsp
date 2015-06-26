<%@ include file="/view/includePages/tag_direct.jspf" %>
<%@ include file="/view/includePages/page_direct.jspf" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf" %>
<h3>Querry Execution Result</h3>
<c:choose>
    <c:when test="${not empty message}">
        <h3>Message from server:</h3>

        <h3><c:out value="${message}"></c:out></h3>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
<form action="admin_cabinet" accept-charset="UTF-8" method="post">
    <input type="submit" value="Back to the Cabinet">
</form>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf" %>
</body>
</html>