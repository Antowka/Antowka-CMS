/**
 * Created by anton on 05.01.16.
 */
/**
 *Article Modal view controller
 */
adminApp.controller('createArticleViewCtrl', function($scope, $uibModal, articleService){


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