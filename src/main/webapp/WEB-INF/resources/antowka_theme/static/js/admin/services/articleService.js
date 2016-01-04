/**
 * Created by Anton Nik on 04.01.16.
 */
adminApp.service('articleService', function($http) {


    this.getArticlesByParams = function(params, callbackFunc) {

        $http({
            method: 'GET',
            url: 'panel/articles/get-articles/?limit=' + params['limit'] + '&offset='+ params['offset'] +'&orderField=' + params['orderField'] + '&order=' + params['order']
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get article preview");
        });
    };
});