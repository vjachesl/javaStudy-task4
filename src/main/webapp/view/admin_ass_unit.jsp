<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf"%>
<h3>Assign Units from Route # <c:out value="${route_id}"></c:out></h3>
<br>
<c:choose>
<c:when test="${not empty admin}">
<div id="list_units">
  <form action="admin_ass_unit" accept-charset="UTF-8" method="post">
  <table style="margin: auto">
    <tr>
      <th>Unit #</th>
      <th>Unit Model</th>
      <th>Unit Transport Type</th>
      <th>Action</th>
    </tr>
    <c:forEach items="${zero_units}" var="unit">
      <tr>
        <td><c:out value="${unit.UNIT_ID()}"></c:out></td>
        <td><c:out value="${unit.MODEL_NAME_EN()}"></c:out></td>
        <td><c:out value="${unit.TRANSPORT_TYPE()}"></c:out></td>
        <td><input type="submit" class="button-accept" name="${unit.UNIT_ID()}" value="Assign unit for route"></td>
       </tr>
    </c:forEach>
    <tr><hr></tr>
    <tr></tr>
  </table>
</form>
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
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>