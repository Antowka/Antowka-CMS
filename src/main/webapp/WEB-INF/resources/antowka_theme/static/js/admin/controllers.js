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
        if(confirm("!!!ВНИМАНИЕ!!! Удалить жалобу?")) {
            dataService.removeTicketById(ticketId, function ($result) {
                console.log($result);
                $scope.updateTicketlist($scope.params);
            });
        }
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

                        $scope.ticket = ticket;

                        return ticket;
                    }
                }
            });
        });
    };
});

adminApp.controller('CloseTicketViewCtrl', function ($scope, $modalInstance, dataService) {

    $scope.attachments = [];

    $scope.cancel = function () {

        if($scope.attachments.length > 0) {

            $scope.attachments.forEach(function (attachment) {

                dataService.removeAttachment(attachment.attachmentId, function (data) {

                    //remove no save attachment from server
                    if (data.code != 1) {
                        console.log("Wrong remove file");
                    }
                });
            });
        }

        $modalInstance.dismiss('cancel');
    };
});


/**
 * Comments
 */

// Save form to the data
adminApp.controller('commentsCtrl', ['$scope','dataService', '$http', 'FileUploader', '$filter', function($scope, dataService, $http, FileUploader, $filter){


    $scope.attachments = [];


    $scope.updateCommentslist = function(ticketId) {
        dataService.getCommentsByTaskId(ticketId, function (comments) {

            //set status IMAGE OR NOT on comment attachment
            comments.forEach(function(comment){
                comment.attachments.forEach(function(attachment){
                    attachment.isImage = attachment.mimeType.indexOf("image/") > -1;
                });
            });

            //change date and time format
            comments.forEach(function(comment){
                comment.creationDate = $filter('date')(new Date(comment.creationDate), 'dd-MM-yyyy H:m:s');
            });

            $scope.comments = comments;
        });
    };

    $scope.updateCommentslist($scope.$parent.$parent.ticket.ticketId);

    //Create new comment for ticket
    $scope.createComment = function(ticketId) {

        var dataForComment = {
            "title":       $scope.title,
            "description": $scope.description,
            "attachments": $scope.attachments,
            "ticket": {
                "ticketId": ticketId
            }
        };

        dataService.createComment(dataForComment, function(newComment){
            $scope.updateCommentslist(ticketId);
            $scope.$parent.attachments = [];
        });
    };

    //Remove Comment
    $scope.removeComment = function(ticketId, commentId){
        if(confirm("!!!ВНИМАНИЕ!!! Удалить коментарий?")) {
            dataService.removeCommentById(commentId, function (data) {
                $scope.updateCommentslist(ticketId);
            });
        }
    }

    //remove commentAttachment
    $scope.removeCommentAttachment = function(attachmentId){
        if(confirm("!!!ВНИМАНИЕ!!! Удалить файл?")) {
            dataService.removeAttachment(attachmentId, function (result) {
                $scope.updateCommentslist($scope.$parent.$parent.ticket.ticketId);
            });
        }
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

//Main tabs in admin-panel
adminApp.controller('mainTabsCtrl', function ($scope, $window) {

});

/**
 *ArticleCategories controller
 */
adminApp.controller('articleCategoryCtrl', function($scope, $uibModal, dataService){

    //Methods
    $scope.updateArticleCategoryList = function() {
        dataService.getAllArticleCategories(function (data) {


            var articleCategories = [];

            //recursive
            var getChildCategories = function (childCategories, pointer, space) {

                childCategories.forEach(function (categoryServer) {

                    if (categoryServer.level != 0) {
                        categoryServer.pretitle = space + "|" + pointer;
                    }

                    articleCategories.push(categoryServer);

                    var childCategories = categoryServer.childArticleCategories;

                    if (childCategories.length > 0) {
                        getChildCategories(childCategories, pointer + pointer, space + space);
                    }
                });


            };

            //start recursion
            getChildCategories(data, "_", "  ");

            $scope.articleCategories = articleCategories;
        });
    };

    //Start
    $scope.updateArticleCategoryList();

    //Open creation modal
    $scope.openCreateCategoryModal = function (parentCategoryId) {
        $scope.uibModalInstance = $uibModal.open({
            templateUrl: 'createArticleCategoryModal.html',
            controller: 'createArticleCategoryViewCtrl',
            scope: $scope,
            resolve: {
                category: function () {
                    $scope.parentCategoryId = parentCategoryId;
                    $scope.operationType = "create";
                    $scope.titleModal = "Create category";

                    if(typeof $scope.updateCategory != "undefined"){
                        delete $scope.updateCategory;
                    }
                }
            }
        });
    };

    //Open edit modal
    $scope.openEditCategoryModal = function (categoryId) {
        $scope.uibModalInstance = $uibModal.open({
            templateUrl: 'createArticleCategoryModal.html',
            controller: 'createArticleCategoryViewCtrl',
            scope: $scope,
            resolve: {
                category: function () {

                    for(var i = 0; $scope.articleCategories.length > i; i++){

                        var category = $scope.articleCategories[i];

                        console.log("%s == %s", category.articleCategoryId, categoryId);

                        if(category.articleCategoryId == categoryId){

                            $scope.updateCategory = category;
                            $scope.operationType = "update";
                            $scope.titleModal = "Edit category";
                            break;
                        }
                    }
                }
            }
        });
    };

    //Remove category
    $scope.removeCategory = function(categoryId){
        console.log("REMOVE CATEGORY: " + categoryId);
        dataService.removeCategory(categoryId, function(data){
            console.log(data);
            //update category list
            $scope.updateArticleCategoryList();
        });
    }
});

/**
 * Open ArticleCategories modal for create category
 */
adminApp.controller('createArticleCategoryViewCtrl', function ($scope, $uibModal, dataService, $filter){

    //add data to local scope for update modal
    if(typeof $scope.$parent.updateCategory != "undefined") {

        $scope.title = $scope.$parent.updateCategory.title;
        $scope.description = $scope.$parent.updateCategory.description;
        $scope.articleCategoryId = $scope.$parent.updateCategory.articleCategoryId;

        $scope.parentCategories = [];
        for(var i = 0; $scope.$parent.articleCategories.length > i; i++){
            //delete current category from list-selectors
            if($scope.$parent.articleCategories[i].articleCategoryId != $scope.$parent.updateCategory.articleCategoryId){
                $scope.parentCategories.push($scope.$parent.articleCategories[i]);
            }
        }
    }

    $scope.titleModal = $scope.$parent.titleModal;

    //create category
    $scope.createCategory = function() {

        var newArticleCategory = {
            parentCategoryId: $scope.$parent.parentCategoryId,
            title: $scope.title,
            description: $scope.description
        };

        dataService.createNewCategory(newArticleCategory, function (response) {
            $scope.$parent.updateArticleCategoryList();
            $scope.$parent.uibModalInstance.dismiss('cancel');
        });
    };

    //update category
    $scope.saveCategory = function(){

        var updateData = {
            articleCategoryId:$scope.articleCategoryId,
            parentCategoryId:$scope.parentCategory,
            title: $scope.title,
            description: $scope.description
        };

        //send data for update category
        dataService.updateCategory(updateData, function(response){

            $scope.$parent.updateArticleCategoryList();
            $scope.$parent.uibModalInstance.dismiss('cancel');
        });
    };

    //check on type Modal
    $scope.isCreateModal = function(){
        return $scope.$parent.operationType == "create";
    };
    $scope.isUpdateModal = function(){
        return $scope.$parent.operationType == "update";
    };

    //close modal
    $scope.cancel = function () {
        $scope.$parent.uibModalInstance.dismiss('cancel');
    };
});