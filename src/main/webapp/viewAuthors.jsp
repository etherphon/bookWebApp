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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans" rel="stylesheet">
        <link href="style/bookStyle.css" rel="stylesheet" type="text/css"/>
        <title>Author List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="container" id="bTable">
                <h2>Author List</h2><br>
                <table class="table table-hover">
                    <tr>
                        <th class="tableHead">Author ID</th>
                        <th class="tableHead">Author Name</th>
                        <th class="tableHead">Date Added</th>
                    </tr>
                    <c:forEach var="author" items="${authorList}" varStatus="rowCount">
                    <tr>
                        <td>${author.authorId}</td>
                        <td>${author.authorName}</td>
                        <td>${author.dateAdded}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="col-md-2"></div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
