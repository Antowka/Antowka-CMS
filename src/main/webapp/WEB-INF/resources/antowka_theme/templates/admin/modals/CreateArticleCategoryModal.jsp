<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 12.12.15
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div ng-controller="createArticleCategoryViewCtrl">
    <script  type="text/ng-template" id="createArticleCategoryModal.html">
        <article class="article-category-view">

            <!-- Modal header -->
            <div class="modal-header">
                <button type="button" class="close pull-right" aria-label="Close" ng-click="cancel()">
                    <span aria-hidden="true">&#10007;</span>
                    <span class="sr-only">Close</span>
                </button>

                <h2>{{titleModal}}</h2>
            </div>

            <!-- Modal body -->
            <div class="modal-body" ng-if="isCreateModal()">
                <form>
                    <input type="text" ng-model="$parent.title" placeholder="Category Name">
                    <textarea ng-model="$parent.description" placeholder="Category Description"></textarea>
                    <button ng-click="createCategory()">Create</button>
                </form>
            </div>

            <div class="modal-body" ng-if="isUpdateModal()">
                <form>

                    <select ng-model="$parent.parentCategory" ng-options="category.articleCategoryId as category.title for category in parentCategories"></select>

                    <input type="text" ng-model="$parent.title">
                    <textarea ng-model="$parent.description"></textarea>
                    <button ng-click="saveCategory()">Save</button>
                </form>
            </div>
        </article>
    </script>
</div>



