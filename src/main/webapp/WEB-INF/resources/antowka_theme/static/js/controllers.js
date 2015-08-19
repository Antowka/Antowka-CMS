// Get data from Service
CommissionApp.controller('ShowTicketsCtrl', function ($scope, dataService){
    $scope.data = null;
    var limit = 12;
    $scope.offset = limit;
    $scope.showMoreBtn = true;

    dataService.getTickets(0, function(dataResponse) {
        $scope.tickets = dataResponse;
        if(dataResponse.length < limit ){
            $scope.showMoreBtn = false;
        }
    });
    // format dates
    $scope.formatDate = function(date){
        var dateOut = new Date(date);
        return dateOut;
    };

    //show more functionality
    $scope.showMore = function() {
        dataService.getTickets($scope.offset, function (dataResponse) {
            if(!dataResponse.length){
                $scope.showMoreBtn = false;
            } else {
                for(var i=0; i<dataResponse.length; i++){
                    $scope.tickets.push(dataResponse[i]);
                }
                $scope.offset+= limit;
            }
        });
    }

});

// MODALS

// Controllers for Request Form Modal
CommissionApp.controller('TicketFormCtrl', function ($scope, $modal) {
    $scope.open = function (size) {
        var modalInstance = $modal.open({
            templateUrl: 'ticketFormModal.html',
            controller: 'CloseTicketFormCtrl',
            size: size
        });
    };
});
CommissionApp.controller('CloseTicketFormCtrl', function ($scope, $modalInstance) {

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

// Controllers for About Us Modal
CommissionApp.controller('AboutUsCtrl', function ($scope, $modal) {
    $scope.open = function (size) {
        var modalInstance = $modal.open({
            templateUrl: 'AboutUsModal.html',
            controller: 'CloseAboutUsCtrl',
            size: size
        });
    };
});
CommissionApp.controller('CloseAboutUsCtrl', function ($scope, $modalInstance) {
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

// Controllers for Request View Modal
CommissionApp.controller('TicketViewCtrl', function ($scope, $modal, dataService) {
    $scope.open = function (ticket) {

        dataService.getTicket(ticket.ticketId, function(ticket){
            var modalInstance = $modal.open({
                templateUrl: 'ticketViewModal.html',
                controller: 'CloseTicketViewCtrl',
                scope: $scope,
                resolve: {
                    ticket: function () {
                        return ticket;
                    }
                }
            });
        });
    };
});
CommissionApp.controller('CloseTicketViewCtrl', function ($scope, $modalInstance) {
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});


// Save form to the data

CommissionApp.controller('sendFormCtrl', ['$scope','dataService', '$http', function($scope, dataService, $http){
    $scope.data = null;

    dataService.getCategories(function(dataResponse) {
        $scope.categories = dataResponse;
    });

    var formData = {};

    $scope.processForm = function() {
        formData = {
            "firstName": $scope.name,
            "lastName": $scope.surname,
            "email": $scope.email,
            "title":  $scope.title,
            "phone": $scope.phone,
            "address": $scope.address,
            "categories": [{
                "ticketCategoryId": $scope.category.ticketCategoryId,
                "parentCategoryId": $scope.category.parentCategoryId
            }],
            "description":  $scope.description
        };

        console.log(formData);

        $http({
            url: 'tickets/create-ticket',
            headers: {'Content-Type': 'application/json; charset=utf-8'},
            data: JSON.stringify(formData),
            transformRequest: false,
            method: 'POST'
        })
            .success(function(){

            }).error(function(){

            });
    };
}]);