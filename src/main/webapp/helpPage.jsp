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
        <title>Help Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
    </head>
    <body>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="container" id="bTable">
                <h2><i class="fa fa-question-circle-o fa-2x"></i>&nbsp;Help Page</h2><br>
                <p>To add an author click the "Add" button.</p>
                <p>To edit an author select an author using the radio button on the left and click "Update", you may update the name of the author.</p>
                <p>To delete an author select an author using the radio button on the left and click "Delete", then confirm.</p>
                <br>
                <a class="btn btn-default btn-small cButton" href="AuthorController" role="button">
                    <i class="fa fa-chevron-circle-left" aria-hidden="true"></i>&nbsp;Back to Author List</a>
            </div>
        </div>
        <div class="col-md-2"></div>
        
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>