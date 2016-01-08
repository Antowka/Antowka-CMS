/**
 * Created by Anton Nik on 04.01.16.
 */

/**
 *Article controller
 */
adminApp.controller('articleCtrl', function($scope, $uibModal, articleService){

    //Get articles by params
    $scope.getAllArticles = function(params) {
        articleService.getArticlesByParams(params, function($articles){
            $scope.articles = $articles;
        });
    };

    //get default array with articles
    $scope.getAllArticles([]);


    //************ FUNCTIONALITY ************

    //Open modal for create article
    $scope.openCreateArticleModal = function() {

        $scope.uibModalInstance = $uibModal.open({
            templateUrl: 'createArticleModal.html',
            controller: 'createArticleViewCtrl',
            scope: $scope,
            resolve: {
                category: function () {
                    $scope.operationType = "create";
                    $scope.titleModal = "Create article";

                    if(typeof $scope.updateCategory != "undefined"){
                        delete $scope.updateCategory;
                    }
                }
            }
        });
    }

    //create new Article
    $scope.createArticle = function(){
        //todo - заполнить сохранение
        console.log("Created Article");
    };
});