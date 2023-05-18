<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


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
        <h2>Add new sensor</h2>

        <form:form method="post" action="/add-new-sensor" modelAttribute="sensor">
            <div class="form-group ${error != null ? 'has-error' : ''}">
               <span>${message}</span>
                    Sensor name: <form:input type="text" path="sensorName"/>
                    Sensor model: <form:input type="text" path="sensorModel"/>
                <span>${error}</span> <br> <br>
               <input type="submit" value="Add new sensor" class="btn btn-info">

            </div>
        </form:form>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
