<%-- 
    Document   : viewAuthors
    Created on : Sep 19, 2016, 1:31:42 PM
    Author     : etherdesign
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
    Object obj = request.getAttribute("authorList");
    if (obj == null) {
        response.sendRedirect("AuthorController");
    }
%>

<html>
    <head>
        <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="style/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/bootstrap.min.js" type="text/javascript"></script>
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans" rel="stylesheet">
        <link href="style/bookStyle.css" rel="stylesheet" type="text/css"/>
        <title>Author List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h2>Author List</h2><br>
        <table class="table table-hover">
            <tr>
                <th>Author ID</th>
                <th>Author Name</th>
                <th>Date Added</th>
            </tr>
            <c:forEach var="author" items="${authorList}" varStatus="rowCount">
                <tr>
                    <td>${author.authorId}</td>
                    <td>${author.authorName}</td>
                    <td>${author.dateAdded}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
