<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 12.12.15
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div ng-controller="createArticleViewCtrl">
    <script  type="text/ng-template" id="createArticleModal.html">
        <form class="article-category-view">
            <select ng-model="$parent.articleCategory" ng-options="category.articleCategoryId as category.title for category in articleCategories"></select>
            <input ng-model="$parent.title" type="text" placeholder="Title">
            <textarea ng-model="$parent.shortDescription" placeholder="Short Description"></textarea>
            <textarea ng-model="$parent.description" placeholder="Full Description"></textarea>
            <button ng-click="createArticle()">Create</button>
            <button ng-click="cancel()">Cancel</button>
        </form>
    </script>
</div>