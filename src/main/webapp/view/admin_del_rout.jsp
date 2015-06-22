<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf"%>
<h3>Delete Route from the system</h3>
<p>Beware! you want to delete route</p>
<c:choose>
  <c:when test="${not empty message}">
    <h3>Message from server:</h3>
    <h3><c:out value="${message}"></c:out></h3>
  </c:when>
  <c:otherwise>
  </c:otherwise>
</c:choose>
<div id="delete route">
  <form action="admin_del_rout" accept-charset="UTF-8" method="post">
    <table>
      <tr><td> Route #:</td><td><c:out value="${route_id}"></c:out></td> </tr>
      <input type="hidden" name="route_id" value="${route_id}" />
    </table>
    <input type="submit" value="Delete Route">
  </form>
</div>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>