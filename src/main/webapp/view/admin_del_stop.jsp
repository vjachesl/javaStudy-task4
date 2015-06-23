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
<p>Beware! you want to delete stop(delete_stop)</p>
<div id="delete stop">
  <form action="admin_del_stop" accept-charset="UTF-8" method="post">
    Please provide valid stop #:<input type="text" name="stop_id"><br>
    <input type="submit" value="Delete Stop">
  </form>
</div>
<hr>
<form action="admin_cabinet" accept-charset="UTF-8" method="post">
  <input type="submit" value="Back to the Cabinet">
</form>
<br>
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>