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
    $scope.formatDate = function(date){
        var dateOut = new Date(date);
        return dateOut;
    };
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
CommissionApp.controller('TicketViewCtrl', function ($scope, $modal) {
    $scope.open = function (ticket) {
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
    };
});
CommissionApp.controller('CloseTicketViewCtrl', function ($scope, $modalInstance) {
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});


// Save form to the data

CommissionApp.controller('sendFormCtrl', ['$scope', function($scope){
    var formData = {};

    $scope.processForm = function() {
        formData = {
            "firstName": $scope.name,
            "lastName": $scope.surname,
            "phone": $scope.phone,
            "address": $scope.address,
            "title":  $scope.title,
            "description":  $scope.description
        };
        console.log(formData);
    };
}]);

// FORM VALIDATION

// Name and Surname validation
CommissionApp.directive('checkname', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(checkname) {
                if (/([a-zA-zА-яЁё])+/.test(checkname)) {
                    ctrl.$setValidity('checkname', true);
                    return checkname;
                } else {
                    ctrl.$setValidity('checkname', false);
                    return undefined;
                }
            });
        }
    };
});
// Email validation
CommissionApp.directive('checkemail', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(checkemail) {
                if (/^([a-zA-Z0-9])+([a-zA-Z0-9._%+-])+@([a-zA-Z0-9_.-])+\.(([a-zA-Z]){2,6})$/.test(checkemail)) {
                    ctrl.$setValidity('checkemail', true);
                    return checkemail;
                } else {
                    ctrl.$setValidity('checkemail', false);
                    return undefined;
                }
            });
        }
    };
});
// Phone validation
CommissionApp.directive('checkphone', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(checkphone) {
                if (/^[+\(\)0-9-\s]{5,18}$/.test(checkphone)) {
                    ctrl.$setValidity('checkphone', true);
                    return checkphone;
                } else {
                    ctrl.$setValidity('checkphone', false);
                    return undefined;
                }
            });
        }
    };
});
// File validation
CommissionApp.directive('checkfile', function () {

    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ngModel) {

            elem.bind('change', function () {
                validate(elem[0].files);
            });

            function validate(files) {
                var filePattern = new RegExp("^([a-zA-Z0-9А-яЁё\s_\\.\-:])+\.(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF|doc|DOC|pdf|PDF)$");

                // Check file type and size of each file
                if (filePattern.test(files[0].name) && files[0] && files[0].size < 10485760) {
                    ngModel.$setValidity('checkfile', true);
                } else {
                    ngModel.$setValidity('checkfile', false);
                    return undefined;
                }

            }
        }
    };

    function clearFileInput(ctrl) {
        try {
            ctrl.value = null;
        } catch(ex) { }
        if (ctrl.value) {
            ctrl.parentNode.replaceChild(ctrl.cloneNode(true), ctrl);
        }
    }
});