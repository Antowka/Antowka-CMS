/**
 * Created by Anton Nik on 04.01.16.
 */
adminApp.service('articleService', function($http) {

    /**
     * Method get articles list by params
     *
     * @param params
     * @param callbackFunc
     */
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

    /**
     * Method for create article
     *
     * @param formData - article object
     * @param callbackFunc
     */
    this.createArticle = function(formData, callbackFunc){

        $http({
            url: 'panel/articles/create',
            headers: {'Content-Type': 'application/json; charset=utf-8', 'X-CSRF-TOKEN': document.getElementById("csrf").value},
            data: JSON.stringify(formData),
            transformRequest: false,
            method: 'POST'
        })
        .success(function(newComment){
            callbackFunc(newComment);
            console.log("CREATED ARTICLE");
        })
        .error(function(){
            console.log("ERROR IN CREATE ARTICLE");
        });
    }
});