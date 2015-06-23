<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/logout_cabinet.jspf"%>
<h3>Admin Cabinet<br></h3>
  <div id="routs-result">
    <c:choose>
      <c:when test="${empty routes}">
        <h3><fmt:message key="routes_jsp.label.error"></fmt:message></h3>
      </c:when>
      <c:otherwise>
        <h3>ROUTES</h3>
        <form action="admin_cabinet" accept-charset="UTF-8" method="post">
          <table style="margin: auto">
            <tr>
              <th><fmt:message key="routes_jsp.label.route_number"></fmt:message></th>
              <th><fmt:message key="routes_jsp.label.route_name"></fmt:message></th>

            </tr>
            <c:forEach items="${routes}" var="route">
              <tr>
                <td><c:out value="${route.ROUTE_ID()}"></c:out></td>
                <td><c:out value="${route.ROUTE_NAME_EN()}"/></td>
                <td><input type="submit" class="button-accept" name="admin_del_rout:${route.ROUTE_ID()}" value="Delete Route"></td>
                <td><input type="submit" class="button-accept" name="admin_ass_stop:${route.ROUTE_ID()}" value="Add Stop to Route"></td>
                <td><input type="submit" class="button-accept" name="admin_rem_stop:${route.ROUTE_ID()}" value="Remove Stop from Route"></td>
                <td><input type="submit" class="button-accept" name="admin_ass_unit:${route.ROUTE_ID()}" value="Add Unit to Route"></td>
                <td><input type="submit" class="button-accept" name="admin_rem_unit:${route.ROUTE_ID()}" value="Remove Unit from Route"></td>
              </tr>
            </c:forEach>
            <tr><hr></tr>
            <tr></tr>
          </table>
          <hr>
          <table>
          <tr>
            <td></td>
            <td>General operations</td>
            <td><input type="submit" class="button-accept" name="admin_new_route" value="Add new Route"></td>
            <td><input type="submit" class="button-accept" name="admin_new_stop" value="Add new stop"></td>
            <td><input type="submit" class="button-accept" name="admin_del_stop" value="Delete Stop"></td>
            <td><input type="submit" class="button-accept" name="admin_new_unit" value="Add new Unit"></td>
            <td><input type="submit" class="button-accept" name="admin_del_unit" value="Delete unit"></td>
            <td><input type="submit" class="button-accept" name="admin_list_units" value="List of all Units"></td>
            <td><input type="submit" class="button-accept" name="admin_list_stops" value="List of all stops"></td>
          </tr>
          </table>
        </form>
      </c:otherwise>
    </c:choose>
  </div>
  <br>
  <br>
  <%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>