<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Agents</title>
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
                <td>Administrator Name</td>
                <td>Shop Id</td>

            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="agents" type="java.util.List"--%>
            <c:forEach var="user" items="${agents}">
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getAgentName()}</td>
                    <td>${user.getAgencyId()}</td>
                    <c:if test="${admin}">
                        <td><a href="?delete=${user.getId()}">delete</a></td>
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
        <form action="<c:url value="/agent"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="agent_name">Administrator name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="agent_name" type="text" name="agent_name"
                           placeholder="Administrator name...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="agency_id">Shop id:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="agency_id" type="text" name="agency_id"
                           placeholder="Shop id...">
                </div>
            </div>

            <div class="uk-margin">
                <button class="uk-button uk-button-default" type="submit">Submit</button>
            </div>
        </form>
    </div>
    </c:if>

    <div class="uk-container">
        <a href="index.jsp">Home</a>
    </div>
</section>

</body>
</html>
