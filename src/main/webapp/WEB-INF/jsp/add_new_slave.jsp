<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Add slave</title>
        <jsp:include page="header.jsp"/>
        <jsp:include page="menu.jsp"/>
        <style>
            .error{color:red}
        </style>
    </head>
    <body class="container">
        <h2>Add new slave</h2>

        <form:form method="post" action="/add-new-slave" modelAttribute="slave">

                 <span> Device name : <form:input type="text" path="deviceName"/>
                            <form:errors path="deviceName" cssClass="error"/></span><p>
                  <span> Slave port : <form:input type="text" path="portName"/>
                    <form:errors path="portName" cssClass="error"/></span><p>

                 <span>Slave ID: <form:input type="number" path="slaveId"/>
                    <form:errors path="slaveId" cssClass="error"/></span><p>

                 <span>Slave start address: <form:input type="number" path="startAddress"/>
                     <form:errors path="startAddress" cssClass="error"/></span><p>

                <span>Number of registers: <form:input type="number" path="numRegisters"/>
                      <form:errors path="numRegisters" cssClass="error"/></span><p>

                 <span>Address to read: <form:input type="number" path="addresses"/>
                  <form:errors path="addresses" cssClass="error"/></span>

                                   <br>
               <input type="submit" value="Add new slave" class="btn btn-info">


        </form:form>


    </body>
    <jsp:include page="footer.jsp"/>
</html>
