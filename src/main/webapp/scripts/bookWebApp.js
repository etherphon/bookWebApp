/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    $('#fActionAdd').click(function (e) {
        //$('#bTable').hide("slide", { direction: "up" }, 1000);
        $("#fAction").val("Add");
        $('#addScreen').show("drop", { direction: "left" }, 500);
    });

    $('#fActionUpd').click(function (e) {
        //$('#bTable').hide("slide", { direction: "up" }, 1000);
        $("#fAction").val("Update");
        $('#updScreen').show("drop", { direction: "left" }, 500);
    });
    
    $('#fActionDel').click(function (e) {
        //$('#bTable').hide("slide", { direction: "up" }, 1000);
        $("#fAction").val("Delete");
        $('#delScreen').show("drop", { direction: "left" }, 500);
    });

    $('#addButton').click(function (e) {
        //$('#cInfoForm').validate();
        $('#addScreen').hide("drop", { direction: "left" }, 500);
        //$('#bTable').show("slide", { direction: "down" }, 1000);
        $("#add-dialog").dialog("open");
    });
    
    $('#updButton').click(function (e) {
        //$('#cInfoForm').validate();
        $('#updScreen').hide("drop", { direction: "left" }, 500);
        //$('#bTable').show("slide", { direction: "down" }, 1000);
        $("#upd-dialog").dialog("open");
        
    });
    
    $('#delButton').click(function (e) {
        //$('#cInfoForm').validate();
        $('#delScreen').hide("drop", { direction: "left" }, 500);
        //$('#bTable').show("slide", { direction: "down" }, 1000);
        $("#del-dialog").dialog("open");
    });
    
    $("#add-dialog").dialog({
        modal: true,
        autoOpen: false,
        buttons: {
            Ok: function() {
                $( this ).dialog( "close" );
            }
        }
    });
    
    $("#upd-dialog").dialog({
        modal: true,
        autoOpen: false,
        buttons: {
            Ok: function() {
                $( this ).dialog( "close" );
            }
        }
    });
    
    $("#del-dialog").dialog({
        modal: true,
        autoOpen: false,
        buttons: {
            Ok: function() {
                $( this ).dialog( "close" );
            }
        }
    });
    

});