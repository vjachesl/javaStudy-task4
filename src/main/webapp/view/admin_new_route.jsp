<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf"%>
<h3>Add new Route to the system</h3>
<p>Beware! You can't add ROUTE with existing number and also all fields needs to be filled before pressing button "Add"</p>
<div id="new_route">
  <form action="admin_new_route" accept-charset="UTF-8" method="post">
    <table>
      <tr> <td> Route #:</td><td><input type="text" name="route_id"></td> </tr>
      <tr><td>Route Name (English):</td><td><input type="text" name="route_name_en"></td> </tr>
      <tr><td> Route Name (Russian):</td><td> <input type="text" name="route_name_ru"></td> </tr>
      <tr><td>Route Transport Type(one of the next values: tram, trolleybus, bus):</td><td> <input type="text" name="route_transp_type"></td> </tr>
      <tr><td></td><td><input type="submit" value="Add"></td></tr>
    </table>
  </form>
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