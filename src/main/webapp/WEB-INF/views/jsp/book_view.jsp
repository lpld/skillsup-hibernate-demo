<%--
  Created by IntelliJ IDEA.
  User: leopold
  Date: 29/03/16
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<h3>View book</h3>

<a href="<c:url value='/logout' />">
    Logout
    <sec:authentication property="principal.username" />
</a>


<jsp:include page="book.jsp">
    <jsp:param name="readonly" value="true"/>
</jsp:include>

</body>
</html>
