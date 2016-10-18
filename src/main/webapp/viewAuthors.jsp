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


<html>
    <head>
        <script src="https://use.fontawesome.com/19de87925f.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script src="scripts/bookWebApp.js" type="text/javascript"></script>
        <link href="style/bookStyle.css" rel="stylesheet" type="text/css"/>
        <title>Author List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
    </head>
    <body>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form role="form" action="AuthorController" method="POST" name="authorForm" id="authorForm">
            <div class="container" id="bTable">
                <h2><i class="fa fa-list-alt"></i>&nbsp;Author List</h2><br>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th class="tableHead">Author ID</th>
                            <th class="tableHead">Author Name</th>
                            <th class="tableHead">Date Added</th>
                        </tr>
                    </thead>
                    <c:forEach var="author" items="${authorList}" varStatus="rowCount">
                    <tbody>
                        <tr class="tRow" id="${author.authorId}">
                            <td><span class="radiogroup"><input type="radio" name="authorPk" id="authorPk" value="${author.authorId}">${author.authorId}</span></td>
                            <td>${author.authorName}</td>
                            <td><fmt:formatDate type="date" value="${author.dateAdded}" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="col-md-6">
                    <input class="btn btn-default btn-sm cButton" type="button" name="fActionAdd" id="fActionAdd" value="Add">
                    <input class="btn btn-default btn-sm cButton" type="button" name="fActionUpd" id="fActionUpd" value="Update">
                    <input class="btn btn-default btn-sm cButton" type="button" name="fActionDel" id="fActionDel" value="Delete">
                    <input class="btn btn-default btn-sm cButton" type="button" name="fActionHelp" id="fActionHelp" value="Help">
                </div>
                
                
                <div class="col-md-6">
                    <div id="addScreen" hidden>
                        <div class="input-group">
                            <input type="text" class="form-control input-sm" name="newAuthor" placeholder="Author name..." id="newAuthor">
                            <!--<input type="hidden" name="fAction" id="fAction" value="Add">-->
                            <span class="input-group-btn">
                                <input type="submit" class="btn btn-secondary btn-sm btn-info" name="fActionAdd" id="addButton" value="Add">
                            </span>    
                        </div>
                    </div>
                    <div id="updScreen" hidden>
                        <div class="input-group">
                            <input type="text" class="form-control input-sm" name="updAuthor" placeholder="Author name.." id="updAuthor">
                            <!--<input type="hidden" name="fAction" id="fAction" value="Update">-->
                            <span class="input-group-btn">
                                <input type="submit" class="btn btn-secondary btn-sm btn-info" name="fActionUpd" id="updButton" value="Update">
                            </span>    
                        </div>
                    </div>
                    <div id="delScreen" hidden>
                        <span id="delalert">Are you sure?&nbsp&nbsp;</span><input class="btn btn-default btn-sm btn-warning" type="submit" name="fActionDel" id="delButton" value="Delete">
                    </div>    
                </div>
                <div class="col-md-12" id="actionCount">
                    <span class="counts">Added: ${numAdded}</span><span class="counts">Updated: ${numUpdated}</span><span class="counts">Deleted: ${numDeleted}</span>
                </div>
            </div>
            <input type="hidden" name="fAction" id="fAction" value="">
            </form>
                <div class="col-md-6">${dateAndTime}</div>
                <div class="col-md-6" id="webmaster"><a href="mailto:${webmaster}">${webmaster}</a></div>
        </div>
        <div class="col-md-2"></div>
        
        <!-- ADD DETAILS SCREEN HERE -->
        
        <div id="add-dialog" title="Author Added">
            <p>
            <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                Author added to the database.
            </p>
        </div>
        <div id="upd-dialog" title="Author Updated">
            <p>
            <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                Author information has been updated.
            </p>
        </div>
        <div id="del-dialog" title="Author Deleted">
            <p>
            <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                Author has been removed from the database.
            </p>
        </div>
        
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- ${applicationScope['attributeNames']} -->
    </body>
</html>
