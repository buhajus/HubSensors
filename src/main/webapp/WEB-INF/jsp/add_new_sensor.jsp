<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Add sensor</title>
        <jsp:include page="header.jsp"/>
        <jsp:include page="menu.jsp"/>
        <style>
            .error{color:red}
        </style>
    </head>
    <body class="container">
        <h2>Galimos operacijos: sudėti, atimti, dauginti, dalinti</h2>

        <form:form method="post" action="/add-new-sensor" modelAttribute="sensor">
           Sensor name: <form:input type="text" path="sensorName"/>
           Sensor model: <form:input type="text" path="sensorModel"/>
                          <br> <br>
            <input type="submit" value="Add new sensor">
        </form:form>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
