/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    $('#fActionAdd').click(function (e) {
        $('#bTable').hide("slide", { direction: "up" }, 1000);
        $('#addScreen').show("slide", { direction: "down" }, 1000);
    });

    $('#fActionUpd').click(function (e) {
        $('#bTable').hide("slide", { direction: "up" }, 1000);
        $('#updScreen').show("slide", { direction: "down" }, 1000);
    });
    
    $('#fActionDel').click(function (e) {
        $('#bTable').hide("slide", { direction: "up" }, 1000);
        $('#delScreen').show("slide", { direction: "down" }, 1000);
    });

    $('#addButton').click(function (e) {
        //$('#cInfoForm').validate();
        $('#addScreen').hide("slide", { direction: "up" }, 1000);
        $('#bTable').show("slide", { direction: "down" }, 1000);
    });
    
    $('#updButton').click(function (e) {
        //$('#cInfoForm').validate();
        $('#updScreen').hide("slide", { direction: "up" }, 1000);
        $('#bTable').show("slide", { direction: "down" }, 1000);
    });
    
    $('#delButton').click(function (e) {
        //$('#cInfoForm').validate();
        $('#delScreen').hide("slide", { direction: "up" }, 1000);
        $('#bTable').show("slide", { direction: "down" }, 1000);
    });

});