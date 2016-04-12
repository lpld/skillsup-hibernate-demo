<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form:form modelAttribute="book" action="/admin/books/${book.id}">
    <table>
        <tr>
            <td>Title:</td>
            <td><form:input path="title" readonly="${param.readonly}"/></td>
        </tr>
        <tr>
            <td>Author:</td>
            <td><form:input path="author.name" readonly="${param.readonly}"/></td>
        </tr>
        <tr>
            <td>Abstract:</td>
            <td><form:input path="summary" readonly="${param.readonly}"/></td>
        </tr>

        <c:choose>
            <c:when test="${param.readonly}">
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="/admin/books/${book.id}">Edit book</a>
                </sec:authorize>
            </c:when>
            <c:when test="${!param.readonly}">
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save Changes"/>
                    </td>
                </tr>
            </c:when>
        </c:choose>
    </table>
</form:form>