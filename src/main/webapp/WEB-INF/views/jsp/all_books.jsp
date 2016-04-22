<%--
  Created by IntelliJ IDEA.
  User: leopold
  Date: 29/03/16
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Book recommendations</title>
    <jsp:include page="../includes.jsp"/>
</head>
<body>

<jsp:include page="../header.jsp">
    <jsp:param name="activePage" value="ALL"/>
</jsp:include>

<div class="container">

    <div class="row">
        <h2>All available books</h2>
    </div>

    <div>
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Published on</th>
            </tr>
            <tbody>
            <c:forEach var="publication" items="${publications}">
                <tr>
                    <td>
                        <a href="/books/${publication.book.id}"> ${publication.title} </a>
                    </td>
                    <td>${publication.book.author.name}</td>
                    <td>${publication.date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
