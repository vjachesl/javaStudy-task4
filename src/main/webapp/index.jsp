<%@ include file="/view/includePages/tag_direct.jspf"%>
<%@ include file="/view/includePages/page_direct.jspf"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>City Transport System</title>
</head>
<body>
<%@ include file="/view/includePages/loc_login.jspf"%>


<h3><fmt:message key="welcome_jsp.label.greeting"></fmt:message><br>
  <fmt:message key="welcome_jsp.label.options"></fmt:message></h3>

<a href="<c:url value="/pages/routes"/>"><fmt:message key="welcome_jsp.label.routes_list"></fmt:message></a>
<br>
<br>
<%@ include file="/view/includePages/foot.jspf"%>
</body>
</html>