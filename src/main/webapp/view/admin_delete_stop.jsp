<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf"%>
<h3>Delete stop</h3>
<p>Beware! you want to delete stop</p>
<c:choose>
  <c:when test="${not empty message}">
    <hr>
    <h3>Message from server: <c:out value="${message}"></c:out></h3>
    <hr>
  </c:when>
  <c:otherwise>
  </c:otherwise>
</c:choose>
<div id="delete stop">
  <form action="admin_del_stop" accept-charset="UTF-8" method="post">
    Please provide valid stop #:<input type="text" name="stop_id"><br>
    <input type="submit" value="Delete Stop">
  </form>
</div>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>