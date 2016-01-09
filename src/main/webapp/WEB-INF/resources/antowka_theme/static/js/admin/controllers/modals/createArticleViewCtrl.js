/**
 * Created by anton on 05.01.16.
 */
/**
 *Article Modal view controller
 */
adminApp.controller('createArticleViewCtrl', function($scope, $uibModal, dataService){

    //upload full category list
    $scope.getArticleCategoriesList = function(){
        dataService.getAllArticleCategories(function (data) {

            var articleCategories = [];

            //recursive
            var getChildCategories = function (childCategories) {

                childCategories.forEach(function (categoryServer) {

                    articleCategories.push(categoryServer);

                    var childCategories = categoryServer.childArticleCategories;

                    if (childCategories.length > 0) {
                        getChildCategories(childCategories);
                    }
                });
            };

            //start recursion
            getChildCategories(data);

            $scope.articleCategories = articleCategories;
        });
    };
    $scope.getArticleCategoriesList();


    $scope.titleModal = $scope.$parent.titleModal;

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