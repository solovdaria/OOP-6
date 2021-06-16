<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>agencies</title>
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
                <td>Shop Name</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="agencies" type="java.util.List"--%>
            <c:forEach var="agency" items="${agencies}">
                <tr>
                    <td>${agency.getId()}</td>
                    <td>${agency.getAgencyName()}</td>
                    <c:if test="${admin}">
                        <td><a href="?delete=${agency.getId()}">delete</a></td>
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
        <form action="<c:url value="/agency"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="agency_name">Shop Name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="agency_name" type="text" name="agency_name"
                           placeholder="Shop name...">
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
