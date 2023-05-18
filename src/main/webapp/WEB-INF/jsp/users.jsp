<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <jsp:include page="header.jsp"/>
    <jsp:include page="menu.jsp"/>
</head>
<body class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
            <th>User id</th>
                <th>User name</th>
                <th>User role</th>
                <th>Action</th>
            </tr>

            <!-- iteruoja per visą skaičių sarašą -->
            <c:forEach var="user" items="${users}">

                <!-- konstruoja įrašo atnaujinimo adresą su skaičiaus id -->
                <c:url var="update" value="/update_user">
                    <c:param name="id" value="${user.id}"/>
                </c:url>

                <!-- konstruoja įrašo trynimo adresą su skaičiaus id -->
                <c:url var="delete" value="/delete_user">
                    <c:param name="id" value="${user.id}"/>
                </c:url>

                <!-- konstruoja įrašo peržiūros adresą su skaičiaus id -->
                <c:url var="show" value="/show_user">
                    <c:param name="id" value="${user.id}"/>
                </c:url>

                <tr>
                     <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.authorities}</td>
                    <td>
                        <!-- atvaizduoti atnaujinimo adresą --> <a href="${update}">Keisti</a>
                        | <a href="${delete}"
                             onclick="if (!(confirm('Ar tikrai norite ištrinti šį įrašą?'))) return false">Trinti</a>
                        | <!-- atvaizduoti rodymo adresą --> <a href="${show}">Rodyti</a>
                    </td>

                </tr>

            </c:forEach>

        </table>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
