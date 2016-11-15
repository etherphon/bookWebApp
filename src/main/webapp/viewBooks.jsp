<%-- 
    Document   : viewBooks
    Created on : Nov 3, 2016, 1:31:42 PM
    Author     : etherdesign
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>


<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script src="https://use.fontawesome.com/19de87925f.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans" rel="stylesheet">
        <script src="scripts/bootstrap-table.min.js" type="text/javascript"></script>
        <link href="scripts/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/bookWebApp.js" type="text/javascript"></script>
        <link href="style/bookStyle.css" rel="stylesheet" type="text/css"/>
        <title>Book List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
    </head>
    <body>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form role="form" action="<%= response.encodeURL("BookController")%>" method="POST" name="bookForm" id="bookForm">
            <div class="container" id="bTable">
                <h2><i class="fa fa-list-alt"></i>&nbsp;Book List</h2><br>
                <table id="booktable" name="booktable" data-toggle="table" data-click-to-select="true">
                    <thead>
                        <tr>
                            <th data-field="bookid" class="tableHead" data-radio="true">Book ID</th>
                            <th data-field="booktitle" class="tableHead">Title</th>
                            <th data-field="bookisbn" class="tableHead">ISBN</th>
                            <th data-field="bookauthor" class="tableHead">Author</th>
                        </tr>
                    </thead>
                    <c:forEach var="book" items="${bookList}" varStatus="rowCount">
                    <tbody>
                        <tr class="tRow" id="${book.bookId}">
                            <td><span class="radiogroup"><input type="radio" name="bookPk" id="bookPk" value="${book.bookId}">${book.bookId}</span></td>
                            <td>${book.title}</td>
                            <td>${book.isbn}</td>
                            <td>${book.authorId.authorName}</td>
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
                            <input type="text" class="form-control input-sm" name="newBookId" id="newBookId" readOnly="true">
                            <input type="text" class="form-control input-sm" name="newBookName" placeholder="Book name..." id="newBookName">
                            <input type="text" class="form-control input-sm" name="newBookIsbn" placeholder="Isbn..." id="newBookIsbn">
                            <select class="form-control input-sm" id="newBookAuthor" name="newBookAuthor">
                                <c:forEach var="author" items="${authorList}" varStatus="rowCount">
                                    <option value="${author.authorId}">${author.authorName}</option>
                                </c:forEach>
                            <!--<input type="hidden" name="fAction" id="fAction" value="Add">-->
                            </select>
                            <input type="submit" class="btn btn-secondary btn-sm btn-info" name="fActionAdd" id="addButton" value="Add">
                    </div>
                    <div id="updScreen" hidden>
                            <input type="text" class="form-control input-sm" name="updBookId" id="updBookId" readOnly="true">
                            <input type="text" class="form-control input-sm" name="updBookName" placeholder="Book name..." id="updBookName">
                            <input type="text" class="form-control input-sm" name="updBookIsbn" placeholder="Isbn..." id="updBookIsbn">
                            <select class="form-control input-sm" id="updBookAuthor" name="updBookAuthor">
                                <c:forEach var="author" items="${authorList}" varStatus="rowCount">
                                    <option value="${author.authorId}">${author.authorName}</option>
                                </c:forEach>
                            <!--<input type="hidden" name="fAction" id="fAction" value="Add">-->
                            </select>
                            <!--<input type="hidden" name="fAction" id="fAction" value="Update">-->
                            <input type="submit" class="btn btn-secondary btn-sm btn-info" name="fActionUpd" id="updButton" value="Update">
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
                    <jsp:include page="footer.jsp" />
        </div>
        <div class="col-md-2"></div>
        
        <!-- ADD DETAILS SCREEN HERE -->
        
        <div id="add-dialog" title="Book Added">
            <p>
            <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                Book added to the database.
            </p>
        </div>
        <div id="upd-dialog" title="Book Updated">
            <p>
            <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                Book information has been updated.
            </p>
        </div>
        <div id="del-dialog" title="Book Deleted">
            <p>
            <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                Book has been removed from the database.
            </p>
        </div>
        
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- ${applicationScope['attributeNames']} -->
    </body>    
</html>

<script>
    
    $(function() {
        
        var index = $('[name="bookPk"]:checked').parents('tr').index();
        $('#table').bootstrapTable().bootstrapTable('check', index);
        //var checkedRows = [];

//        $('#table').on('check.bs.table', function (e, row) {
//            //checkedRows.
//            checkedRows.push({id: row.bookid, name: row.bookname, isbn: row.bookisbn, author: row.bookauthor});
//            console.log(checkedRows);
//            $("#updBookId").val(row.bookid);
//            $("#updBookName").val(row.bookname);
//            $("#updBookIsbn").val(row.bookisbn);
//            $("#updBookAuthor").val(row.bookauthor);
//        });
//
//        $('#table').on('uncheck.bs.table', function (e, row) {
//            $.each(checkedRows, function(index, value) {
//                if (value.id === row.id) {
//                    checkedRows.splice(index,1);
//                }
//            });
//        console.log(checkedRows);
//        });

    $("#fActionUpd").click(function() {
        //$("#output").empty();
        var bookdata = [];
        bookdata = $("#booktable").bootstrapTable('getSelections');
        $("#updBookId").val(bookdata[0]);
        $("#updBookName").val(bookdata[1]);
        $("#updBookIsbn").val(bookdata[2]);
        $("#updBookAuthor").val(bookdata[3]);
        //console.log($('#table').bootstrapTable('getSelections')[0]);
        alert(JSON.stringify(bookdata));
    });
    
    });
    var getTableData = function() {
        // save the selected index
        //var index = $('[name="bookPk"]:checked').parents('tr').index();
    
        // init table and check the index row
        //$('#table').bootstrapTable().bootstrapTable('check', index);
        $("#updBookId").val();
        $("#updBookName").val();
        $("#updBookIsbn").val();
        $("#updBookAuthor").val();
    };
</script>