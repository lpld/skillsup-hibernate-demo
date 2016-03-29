<%--
  Created by IntelliJ IDEA.
  User: leopold
  Date: 29/03/16
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Book</title>
</head>
<body>

<form:form modelAttribute="book" action="/books/${book.id}">
    <table>
        <tr>
            <td>Title:</td>
            <td><form:input path="title"/></td>
        </tr>
        <tr>
            <td>Author:</td>
            <td><form:input path="author.name"/></td>
        </tr>
        <tr>
            <td>Abstract:</td>
            <td><form:input path="summary"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Save Changes"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
