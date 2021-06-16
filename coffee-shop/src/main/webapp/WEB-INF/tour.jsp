<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Tours</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/uikit.min.css"/>
    <script src="js/uikit.min.js"></script>
    <script src="js/uikit-icons.min.js"></script>
</head>
<body>

<section class="uk-section">
    <div class="uk-container">
        <table class="uk-table uk-table-hover uk-table-divider uk-table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>Coffee Name</td>
                <td>Coffee ingredients</td>
                <td>Coffee shop id</td>
                <td>Coffee cost</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="tours" type="java.util.List"--%>
            <c:forEach var="tour" items="${tours}">
                <tr>
                    <td>${tour.getId()}</td>
                    <td>${tour.getName()}</td>
                    <td>${tour.getDescription()}</td>
                    <td>${tour.getShop().getId()}</td>
                    <td>${tour.getCost()}</td>
                    <c:if test="${admin}">
                        <td><a href="?delete=${tour.getId()}">delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <c:if test="${admin}">
    <div class="uk-container">
        <form action="<c:url value="/tour"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="tour_name">Coffee name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="tour_name" type="text" name="tour_name"
                           placeholder="Coffee name...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="tour_description">Coffee ingredients:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="tour_description" type="text" name="tour_description"
                           placeholder="Coffee ingredients...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="tour_agency">Coffee shop id:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="tour_agency" type="text" name="tour_agency"
                           placeholder="Coffee shop id...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="tour_cost">Coffee cost:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="tour_cost" type="text" name="tour_cost"
                           placeholder="Coffee cost...">
                </div>
            </div>

            <div class="uk-margin">
                <button class="uk-button uk-button-default" type="submit">Submit</button>
            </div>
        </form>
    </div>
    </c:if>

    <div class="uk-container">
        <a href="/">Home</a>
    </div>
</section>

</body>
</html>
