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

                <h2>Create new category</h2>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form>
                    <input type="text" ng-model="title" placeholder="Category Name" value="123">
                    <textarea ng-model="description" placeholder="Category Description"></textarea>
                    <button ng-click="crateCategory()">Create Category</button>
                </form>
            </div>

        </article>
    </script>
</div>



