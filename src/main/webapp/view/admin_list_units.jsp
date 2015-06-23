<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf"%>
<h3>List of All Transport Units</h3>
<div id="list_units">
  <table style="margin: auto">
    <tr>
      <th>Unit #</th>
      <th>Unit Name</th>
      <th>Unit Transport Type</th>
      <th>Unit RouteId</th>


    </tr>
    <c:forEach items="${units}" var="unit">
      <tr>
        <td><c:out value="${unit.UNIT_ID()}"></c:out></td>
        <td><c:out value="${unit.MODEL_NAME_EN()}"></c:out></td>
        <td><c:out value="${unit.TRANSPORT_TYPE()}"></c:out></td>
        <td><c:out value="${unit.getRoute()}"></c:out></td>

       </tr>
    </c:forEach>
    <tr><hr></tr>
    <tr></tr>
  </table>
</div>
<hr>
<form action="admin_cabinet" accept-charset="UTF-8" method="post">
  <input type="submit" value="Back to the Cabinet">
</form>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>