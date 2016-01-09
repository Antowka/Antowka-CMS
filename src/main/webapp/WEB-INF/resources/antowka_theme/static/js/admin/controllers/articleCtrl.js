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

                    if(typeof $scope.updateArticle != "undefined"){
                        delete $scope.updateArticle;
                    }
                }
            }
        });
    };

    //create new Article
    $scope.createArticle = function(){

        var categories = [];

        //todo - insert category array
        categories.push({articleCategoryId:$scope.articleCategory});

        var newArtticle = {
            title: $scope.title,
            shortDescription: $scope.shortDescription,
            description: $scope.description,
            categories:categories
        };

        articleService.createArticle(newArtticle, function(result){
            $scope.getAllArticles([]);
        });
    };
});