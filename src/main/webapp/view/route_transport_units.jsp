<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/loc_login.jspf"%>
<div id="routs-transport-units">
  <c:choose>
    <c:when test="${empty transportUnits}">
      <h3>Sorry, can't find any transport due internal DB problem. </h3>
      <h3>Try to go to the route page and repite request. </h3>
    </c:when>
    <c:otherwise>
      <h3>ROUTE # <c:out value="${routeId}"/></h3>
    <form action="routes" accept-charset="UTF-8" method="post">
        <table style="margin: auto">
          <tr>
            <th>Transport Unit #</th>
            <th> </th>
            <th>Model</th>
            <th> </th>
            <th>Transport Type</th>
            <th> </th>
          </tr>
          <c:forEach items="${transportUnits}" var="unit">
            <tr>
              <td><c:out value="${unit.UNIT_ID()}"></c:out></td>
              <td> </td>
              <td><c:out value="${language eq 'ru' ? unit.MODEL_NAME_RU() : unit.MODEL_NAME_EN()}"></c:out></td>
              <td> </td>
              <td><c:out value="${language eq 'ru' ? unit.TRANSPORT_TYPE().getNameRu() : unit.TRANSPORT_TYPE().getNameEn()}"></c:out></td>
              <td> </td>
             </tr>
          </c:forEach>
        </table>
      <input type="submit" class="button-accept" name="${route}" value="Back To Routes List"/>
    </form>
     </c:otherwise>
  </c:choose>
</div>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>
