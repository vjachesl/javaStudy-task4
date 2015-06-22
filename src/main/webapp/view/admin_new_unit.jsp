<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf"%>
<h3>Add new Unit</h3>
<p>Beware! You can't add UNIT with existing number and also all fields needs to be filled before pressing button "Add"</p>
<c:choose>
  <c:when test="${not empty message}">
    <hr>
    <h3>>Message from server: <c:out value="${message}"></c:out></h3>
    <hr>
  </c:when>
  <c:otherwise>
  </c:otherwise>
</c:choose>
<div id="new_unit">
  <form action="admin_new_unit" accept-charset="UTF-8" method="post">
    <table>
      <tr> <td> Unit #:</td><td><input type="text" name="unit_id"></td> </tr>
      <tr><td>Unit Model Name (English):</td><td><input type="text" name="unit_model_name_en"></td> </tr>
      <tr><td>Unit Model Name (Russian):</td><td> <input type="text" name="unit_model_name_ru"></td> </tr>
      <tr><td>Unit Transport Type(one of the next values: tram, trolleybus, bus):</td><td> <input type="text" name="unit_transp_type"></td></tr>
      <tr><input type="submit" value="Add"></tr>
    </table>
  </form>
</div>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>