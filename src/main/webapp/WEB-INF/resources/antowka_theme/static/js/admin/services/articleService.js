/**
 * Created by Anton Nik on 04.01.16.
 */
adminApp.service('articleService', function($http) {


    this.getArticlesByParams = function(params, callbackFunc) {

        var requestUrl = 'panel/articles/get-articles/?';

        //add params to url if exist in array
        if(typeof params['limit'] !== "undefined"){
            requestUrl += "&limit=" + params['limit'];
        }

        if(typeof params['offset'] !== "undefined"){
            requestUrl += "&offset=" + params['offset'];
        }

        if(typeof params['orderField'] !== "undefined"){
            requestUrl += "&orderField=" + params['orderField'];
        }

        if(typeof params['order'] !== "undefined"){
            requestUrl += "&order=" + params['order'];
        }

        if(typeof params['categoryId'] !== "undefined"){
            requestUrl += "&categoryId=" + params['categoryId'];
        }

        $http({
            method: 'GET',
            url: requestUrl
        }).success(function(data){
            // With the data succesfully returned, call our callback
            callbackFunc(data);
        }).error(function(){
            console.log("error, you can't get article preview");
        });
    };
});