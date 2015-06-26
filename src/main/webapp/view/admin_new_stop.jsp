<%@ include file="/view/includePages/tag_direct.jspf" %>
<%@ include file="/view/includePages/page_direct.jspf" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf" %>
<h3>Add new Stop</h3>

<p>Beware! You can't add STOP with existing number and also all fields needs to be filled before pressing button
    "Add"</p>

<div id="new_stop">
    <form action="admin_new_stop" accept-charset="UTF-8" method="post">
        <table>
            <tr>
                <td> Stop #:</td>
                <td><input type="text" name="stop_id"></td>
            </tr>
            <tr>
                <td>Stop Name (English):</td>
                <td><input type="text" name="stop_name_en"></td>
            </tr>
            <tr>
                <td> Stop Name (Russian):</td>
                <td><input type="text" name="stop_name_ru"></td>
            </tr>
            <tr>
                <td> Stop Latitude :</td>
                <td><input type="double" name="stop_lat"></td>
            </tr>
            <tr>
                <td> Stop Longtude :</td>
                <td><input type="double" name="stop_long"></td>
            </tr>
            <tr><input type="submit" value="Add"></tr>
        </table>
    </form>
</div>
<hr>
<form action="admin_cabinet" accept-charset="UTF-8" method="post">
    <input type="submit" value="Back to the Cabinet">
</form>
<br>
<%@ include file="/view/includePages/foot.jspf" %>
</body>
</html>