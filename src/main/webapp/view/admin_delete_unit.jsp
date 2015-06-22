<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf"%>
<h3>Delete transport unit</h3>
<p>Beware! you want to delete unit from system</p>
<c:choose>
  <c:when test="${not empty message}">
    <hr>
    <h3>Message from server: <c:out value="${message}"></c:out></h3>
    <hr>
  </c:when>
  <c:otherwise>
  </c:otherwise>
</c:choose>
<div id="delete unit">
  <form action="admin_del_unit" accept-charset="UTF-8" method="post">
    Please provide valid unit #:<input type="text" name="unit_id"><br>
    <input type="submit" value="Delete Unit">
  </form>
</div>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>