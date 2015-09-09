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
});