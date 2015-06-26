<%@ include file="/view/includePages/tag_direct.jspf" %>
<%@ include file="/view/includePages/page_direct.jspf" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf" %>
<h3>Delete Route from the system</h3>

<p>Beware! you want to delete route</p>

<div id="delete route">
    <form action="admin_del_rout" accept-charset="UTF-8" method="post">
        <table>
            <tr>
                <td> Route #:</td>
                <td><c:out value="${route_id}"></c:out></td>
            </tr>
            <input type="hidden" name="route_id" value="${route_id}"/>
        </table>
        <input type="submit" value="Delete Route">
    </form>
</div>
<hr>
<form action="admin_cabinet" accept-charset="UTF-8" method="post">
    <input type="submit" value="Back to the Cabinet">
</form>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf" %>
</body>
</html>