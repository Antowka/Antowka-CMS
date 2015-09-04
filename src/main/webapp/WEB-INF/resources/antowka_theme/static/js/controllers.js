// Get data from Service
CommissionApp.controller('ShowTicketsCtrl', function ($scope, dataService){
    $scope.data = null;
    var limit = 12;
    $scope.offset = limit;
    $scope.showMoreBtn = true;

    dataService.getTickets(0, function(dataResponse) {
        $scope.tickets = dataResponse;
        console.log(dataResponse);
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
CommissionApp.controller('CloseTicketFormCtrl', function ($scope, $modalInstance, dataService) {

    $scope.attachments = [];

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {

        console.log($scope.attachments);
        $scope.attachments.forEach(function(attachment){

            dataService.removeAttachment(attachment.attachmentId, function(data){

                //remove no save attachment from server
                if(data.code != 1) {
                    console.log("Wrong remove file");
                }
            });
        });

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

    $scope.mainAttachment = null;

    $scope.open = function (ticket) {

        dataService.getTicket(ticket.ticketId, function(ticket){

            var modalInstance = $modal.open({
                templateUrl: 'ticketViewModal.html',
                controller: 'CloseTicketViewCtrl',
                scope: $scope,
                resolve: {
                    ticket: function () {
                        $scope.ticket = ticket;

                        ticket.attachments.forEach(function(attachment){
                            if(attachment.mimeType.indexOf("image/") > -1){
                                $scope.mainAttachment = attachment;
                                return false;
                            }
                        });

                        console.log(ticket);
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
CommissionApp.controller('sendFormCtrl', ['$scope','dataService', '$http', 'FileUploader', '$modal', function($scope, dataService, $http, FileUploader, $modal){

    $scope.data = null;
    $scope.attachments = [];

    dataService.getCategories(function(dataResponse) {
        $scope.categories = dataResponse;
    });

    dataService.getRegions(function(dataResponse) {

        var regions = [];

        //recursive
        var getChildRegions = function(childRegions) {

            childRegions.forEach(function (regionServer) {

                regions.push(regionServer);

                var childRegions = regionServer.childRegions;

                if(childRegions.length > 0) {
                    getChildRegions(childRegions);
                }
            });
        };

        //start recursion
        getChildRegions(dataResponse);

        $scope.regions = regions;
        console.log($scope.regions);
    });

    $scope.openSuccess = function (size) {
        var modalInstance = $modal.open({
            templateUrl: 'SuccessModal.html',
            controller: 'CloseSuccessCtrl',
            size: size,
            windowClass: 'success-modal'
        });
    };

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
            "description":  $scope.description,
            "attachments": $scope.attachments
        };

        //todo - replace to service
        $http({
            url: 'tickets/create-ticket',
            headers: {'Content-Type': 'application/json; charset=utf-8'},
            data: JSON.stringify(formData),
            transformRequest: false,
            method: 'POST'
        })
        .success(function(){
                $scope.openSuccess();
        }).error(function(){

        });
    };

    //********************** Upload File ******************************
            var uploader = $scope.uploader = new FileUploader({
                url: '/attachments/upload',
                alias: 'files',
                autoUpload: true,
                isHTML5:true
            });

            // FILTERS
            uploader.filters.push({
                name: 'customFilter',
                fn: function(item /*{File|FileLikeObject}*/, options) {
                    return this.queue.length < 10;
                }
            });



            // CALLBACKS
            uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
                //console.info('onWhenAddingFileFailed', item, filter, options);
            };
            uploader.onAfterAddingFile = function(fileItem) {
                //console.info('onAfterAddingFile', fileItem);
            };
            uploader.onAfterAddingAll = function(addedFileItems) {
                //console.info('onAfterAddingAll', addedFileItems);
            };
            uploader.onBeforeUploadItem = function(item) {
                //console.info('onBeforeUploadItem', item);
            };
            uploader.onProgressItem = function(fileItem, progress) {
                //console.info('onProgressItem', fileItem, progress);
            };
            uploader.onProgressAll = function(progress) {
                //console.info('onProgressAll', progress);
            };
            uploader.onSuccessItem = function(fileItem, response, status, headers) {

                fileItem.remove = function(){

                    //remove attachment from server
                    dataService.removeAttachment(fileItem.attachmentId, function(data){
                        if(data.code == 1) {

                            //remove attachment from $scope.attachments
                            $scope.attachments.slice(fileItem.attachmentIndex);
                            $scope.$parent.attachments = $scope.attachments;

                            //remove file from queue
                            uploader.removeFromQueue(fileItem);
                        }else{
                            //todo - show error about remove attachment
                            alert("Need will make error: Problem remove file");
                        }
                    });

                }

                //save position file in queue
                if(response.code == 1) {

                    fileItem.attachmentIndex = $scope.attachments.push(response.params[0])-1;
                    fileItem.attachmentId = response.params[0].attachmentId;
                    $scope.$parent.attachments = $scope.attachments;
                }
            };
            uploader.onErrorItem = function(fileItem, response, status, headers) {
                //console.info('onErrorItem', fileItem, response, status, headers);
            };
            uploader.onCancelItem = function(fileItem, response, status, headers) {
                //console.info('onCancelItem', fileItem, response, status, headers);
            };
            uploader.onCompleteItem = function(fileItem, response, status, headers) {
                //console.info('onCompleteItem', fileItem, response, status, headers);
            };
            uploader.onCompleteAll = function() {
                console.log($scope.attachments);
            };
    //******************************* End Upload Files ****************************************
}]);


CommissionApp.controller('CloseSuccessCtrl', function ($scope, $modalInstance) {
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});
