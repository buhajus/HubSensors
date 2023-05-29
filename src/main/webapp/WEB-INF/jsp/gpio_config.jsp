<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>GPIO configuration</title>
    <jsp:include page="header.jsp"/>
    <jsp:include page="menu.jsp"/>
</head>
<body class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
            <th>id</th>
                <th>GPIO pin</th>
                <th>Sensor name</th>

                <th>Action</th>
            </tr>

            <!-- iteruoja per visą  sarašą -->
            <c:forEach var="gpio" items="${sensors}">

                <!-- konstruoja įrašo atnaujinimo adresą su skaičiaus id -->
                <c:url var="update" value="/update">
                    <c:param name="id" value="${gpio.id}"/>
                </c:url>

                <!-- konstruoja įrašo trynimo adresą su skaičiaus id -->
                <c:url var="delete" value="/delete">
                    <c:param name="id" value="${gpio.id}"/>
                </c:url>

                <!-- konstruoja įrašo peržiūros adresą su skaičiaus id -->
                <c:url var="show" value="/show">
                    <c:param name="id" value="${gpio.id}"/>
                </c:url>

                <tr>
                    <td>${gpio.id}</td>
                    <td>${gpio.pin}</td>
                    <td>${gpio.sensorName}</td>

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
    <a type="button" href="/add_new_sensor" class="btn btn-info">Add new sensor</a>
    <jsp:include page="footer.jsp"/>
</body>
</html>