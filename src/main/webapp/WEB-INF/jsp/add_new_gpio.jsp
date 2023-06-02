<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Add new GPIO pin</title>
        <jsp:include page="header.jsp"/>
        <jsp:include page="menu.jsp"/>
        <style>
            .error{color:red}
        </style>
    </head>
    <body class="container">
        <h2>Add new GPIO pin</h2>

        <form:form method="post" action="/add-new-gpio" modelAttribute="gpio">
                 <span> GPIO PIN: <form:input type="number" path="gpio"/>

                 <br>
               <input type="submit" value="Add new GPIO PIN" class="btn btn-info">


        </form:form>


    </body>
    <jsp:include page="footer.jsp"/>
</html>
