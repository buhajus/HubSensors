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
                 <span> Sensor name: <form:input type="text" path="sensorName"/>
                   <form:errors path="sensorName" cssClass="error"/></span><p>

                 <span>   Sensor model: <form:input type="text" path="sensorModel"/>
                         <form:errors path="sensorModel" cssClass="error"/></span>

                         <span> GPIO of raspberry: <form:input type="number" path="gpio"/>
                             <form:errors path="gpio" cssClass="error"/></span>
                 <br>
               <input type="submit" value="Add new sensor" class="btn btn-info">


        </form:form>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
