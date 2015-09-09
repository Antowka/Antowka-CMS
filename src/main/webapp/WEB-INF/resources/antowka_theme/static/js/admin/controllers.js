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
adminApp.controller('ticketViewCtrl', function ($scope, $modal, dataService){
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