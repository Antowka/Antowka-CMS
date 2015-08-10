var CommissionApp = angular.module('CommissionApp', ['ui.bootstrap']);

// Requests

CommissionApp.service('dataService', function($http) {
    this.getData = function(callbackFunc) {
        $http({
            method: 'GET',
            url: 'tickets/get-tickets/?limit=2&orderField=creationDate&order=desc'
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            alert("error");
        });
    }
});

CommissionApp.controller('ShowRequestsCtrl', function ($scope, dataService){
    $scope.data = null;
    dataService.getData(function(dataResponse) {
        $scope.requests = dataResponse;
    });
});

//
// MODALS
//

// Show modal with Request form
CommissionApp.controller('RequestFormCtrl', function ($scope, $modal) {
    $scope.open = function (size) {
        var modalInstance = $modal.open({
            templateUrl: 'requestModal.html',
            controller: 'CloseRequestCtrl',
            size: size
        });
    };
});
CommissionApp.controller('CloseRequestCtrl', function ($scope, $modalInstance) {

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

// Show modal About Us
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