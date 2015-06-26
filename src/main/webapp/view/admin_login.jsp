<%@ include file="/view/includePages/tag_direct.jspf" %>
<%@ include file="/view/includePages/page_direct.jspf" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City Transport System</title>
</head>
<body>
<h3>Admin Login page<br></h3>
Use admin admin.
<br>

<form action="admin_cabinet" accept-charset="UTF-8" method="post">
    Username:<input type="text" name="login"><br>
    Password:<input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
<%@ include file="/view/includePages/foot.jspf" %>
</body>
</html>
