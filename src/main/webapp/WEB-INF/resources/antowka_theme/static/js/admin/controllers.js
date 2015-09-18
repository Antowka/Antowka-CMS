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

    $scope.openTicket = function (ticketId) {

        $scope.mainAttachment = null;

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
adminApp.controller('commentsCtrl', ['$scope','dataService', '$http', '$modal', 'FileUploader', function($scope, dataService, FileUploader){

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
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
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
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {

    };
    //******************************* End Upload Files ****************************************

}]);