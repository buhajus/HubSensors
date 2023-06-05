<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>GPIO configuration</title>
    <jsp:include page="header.jsp"/>
    <jsp:include page="menu.jsp"/>
       <style>
                .error{color:red}
            </style>
</head>
<body class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>id</th>
                <th>GPIO pin</th>
                 <th>Action</th>
            </tr>

            <!-- iteruoja per visą  sarašą -->
            <c:forEach var="gpio" items="${gpio}">

                <!-- konstruoja įrašo atnaujinimo adresą su skaičiaus id -->
                <c:url var="update" value="/update_gpio">
                    <c:param name="id" value="${gpio.id}"/>


                </c:url>

                <!-- konstruoja įrašo trynimo adresą su skaičiaus id -->
                <c:url var="delete" value="/delete_gpio">
                    <c:param name="id" value="${gpio.id}"/>
                </c:url>

                <!-- konstruoja įrašo peržiūros adresą su skaičiaus id -->
                <c:url var="show" value="/show_gpio">
                    <c:param name="id" value="${gpio.id}"/>
                </c:url>

                <tr>
                    <td>${gpio.id}</td>
                    <td>${gpio.gpio}</td>

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
    <a type="button" href="/add_new_gpio" class="btn btn-info">Add new sensor</a>
    <jsp:include page="footer.jsp"/>
</body>
</html>
