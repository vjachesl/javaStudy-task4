<%--
  Created by IntelliJ IDEA.
  User: viacheslav
  Date: 16.06.15
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List of transport units on the route</title>
</head>
<body>
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
              <td><c:out value="${unit.MODEL_NAME_EN()}"/></td>
              <td> </td>
              <td><c:out value="${route.TRANSPORT_TYPE()}"/></td>
              <td> </td>
              <td><input type="submit" class="button-accept" name="${route}" value="Back To Routes List"/></td>
            </tr>
          </c:forEach>
        </table>
    </form>
     </c:otherwise>
  </c:choose>
</div>
</body>
</html>
