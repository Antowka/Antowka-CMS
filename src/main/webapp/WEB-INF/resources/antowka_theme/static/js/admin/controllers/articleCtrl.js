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
});