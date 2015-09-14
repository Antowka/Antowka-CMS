/**
 * Show tickets list
 */
adminApp.controller('ticketsCtrl', function ($scope, dataService){

    $scope.updateTicketlist = function(params) {
        dataService.getAllTicketsList(params, function (tickets) {
            $scope.tickets = tickets;
        });
    };

    $scope.params = {
        limit:500,
        offset:0,
        orderField:"status",
        order:"asc"
    };

    $scope.updateTicketlist($scope.params);

    $scope.sortingListTicket = function(orderField, order, offset){

        if(offset) {
            $scope.params.offset = offset;
        }

        if(orderField) {
            $scope.params.orderField = orderField;
        }

        if(order) {

            if($scope.params.order == "asc"){
                $scope.params.order = "desc";
            }else{
                $scope.params.order = "asc";
            }
        }

        $scope.updateTicketlist($scope.params);
    };

    $scope.publicTicket = function(ticketId){
        dataService.publicTicketById(ticketId, function($result){
           console.log($result);
            $scope.updateTicketlist($scope.params);
        });
    };

    $scope.removeTicket = function(ticketId){
        dataService.removeTicketById(ticketId, function($result){
            console.log($result);
            $scope.updateTicketlist($scope.params);
        });
    };
});

/**
 * Open ticket modal
 */
adminApp.controller('ticketViewCtrl', function ($scope, $modal, dataService, $filter){
    $scope.mainAttachment = null;

    $scope.openTicket = function (ticketId) {

        dataService.getTicketsById(ticketId, function(ticket){

            var modalInstance = $modal.open({
                templateUrl: 'ticketViewModal.html',
                controller: 'CloseTicketViewCtrl',
                scope: $scope,
                resolve: {
                    ticket: function () {


                        ticket.attachments.forEach(function(attachment){

                            if(attachment.mimeType.indexOf("image/") > -1){

                                //Main Preview
                                if($scope.mainAttachment == null) {
                                    $scope.mainAttachment = attachment;
                                }

                                attachment.isImage = true;
                            }else{
                                attachment.isImage = false;
                            }
                        });

                        //change date and time format
                        ticket.comments.forEach(function(comment){
                            comment.creationDate = $filter('date')(new Date(comment.creationDate), 'dd-MM-yyyy H:m:s');
                        });

                        $scope.ticket = ticket;

                        return ticket;
                    }
                }
            });
        });
    };
});

adminApp.controller('CloseTicketViewCtrl', function ($scope, $modalInstance) {
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});


/**
 * Comments
 */

// Save form to the data
adminApp.controller('commentsCtrl', ['$scope','dataService', '$http', '$modal', function($scope, dataService){

    $scope.updateCommentslist = function(ticketId) {
        dataService.getCommentsByTaskId(ticketId, function (comments) {
            $scope.comments = comments;
        });
    };

    $scope.updateCommentslist($scope.$parent.$parent.ticket.ticketId);

    //Create new comment for ticket
    $scope.createComment = function(ticketId) {

        var dataForComment = {
            "title":            $scope.title,
            "description":      $scope.description,
            "ticket": {
                "ticketId": ticketId
            }
        };

        dataService.createComment(dataForComment, function(newComment){
            $scope.updateCommentslist(ticketId);
        });
    };

    //Remove Comment
    $scope.removeComment = function(ticketId, commentId){

        dataService.removeCommentById(commentId, function(data){
            $scope.updateCommentslist(ticketId);
        });
    }
}]);