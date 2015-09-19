/**
 * Created by Anton Nik on 08.09.15.
 */

adminApp.service('dataService', function($http) {

    this.getAllTicketsList = function(params, callbackFunc) {

        $http({
            method: 'GET',
            url: 'panel/tickets/get-tickets/?limit=' + params['limit'] + '&offset='+ params['offset'] +'&orderField=' + params['orderField'] + '&order=' + params['order']
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get ticket preview");
        });
    };

    this.getTicketsById = function(ticketId, callbackFunc) {

        $http({
            method: 'GET',
            url: 'panel/tickets/ticket/' + ticketId
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get ticket preview");
        });
    };

    this.publicTicketById = function(ticketId, callbackFunc) {

        $http({
            method: 'GET',
            url: 'panel/tickets/public/' + ticketId
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get ticket preview");
        });
    };

    this.removeTicketById = function(ticketId, callbackFunc) {

        $http({
            method: 'GET',
            url: 'panel/tickets/remove/' + ticketId
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get ticket preview");
        });
    };

    this.createComment = function(formData, callbackFunc){
        $http({
            url: 'panel/comments/create',
            headers: {'Content-Type': 'application/json; charset=utf-8', 'X-CSRF-TOKEN': document.getElementById("csrf").value},
            data: JSON.stringify(formData),
            transformRequest: false,
            method: 'POST'
        })
        .success(function(newComment){
                callbackFunc(newComment);
                console.log("CREATED COMMENT");
        }).error(function(){
            console.log("ERROR IN CREATE COMMENT");
        });
    };

    this.removeCommentById = function(commentId, callbackFunc) {

        $http({
            method: 'GET',
            url: 'panel/comments/remove/' + commentId
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't remove commentID #" + commentId);
        });
    };

    this.getCommentsByTaskId = function(ticketId, callbackFunc) {

        $http({
            method: 'GET',
            url: 'panel/comments/getByTask/' + ticketId
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get comments for Ticket #" + ticketId);
        });
    };

    this.removeAttachment = function(attachmentId, callbackFunc){
        $http({
            method: 'GET',
            url: 'attachments/remove/?attachmentId='+attachmentId
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get ticket");
        });
    }
});