var CommissionApp = angular.module('CommissionApp', ['ui.bootstrap',  'angularFileUpload']);

// Requests

CommissionApp.service('dataService', function($http) {

    this.getTickets = function(offset, callbackFunc) {
        $http({
            method: 'GET',
            url: 'tickets/get-tickets/?limit=12&offset='+offset+'&orderField=creationDate&order=desc'
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get ticket preview");
        });
    }

    this.getCategories = function(callbackFunc) {
        $http({
            method: 'GET',
            url: 'tickets/get-categories'
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get categories");
        });
    }

    this.getTicket = function(ticketId, callbackFunc){
        $http({
            method: 'GET',
            url: 'tickets/get-ticket/?ticketId='+ticketId
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get ticket");
        });
    }

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