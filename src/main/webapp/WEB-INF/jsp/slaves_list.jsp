<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Slaves</title>
    <jsp:include page="header.jsp"/>
    <jsp:include page="menu.jsp"/>
</head>
<body class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Device name</th>
                <th>Slave port</th>
                <th>Slave ID</th>
                <th>Slave start address</th>
                <th>Number of registers</th>
                <th>Address to read</th>
                <th>Action</th>
            </tr>

            <!-- iteruoja per visą  sarašą -->
            <c:forEach var="slave" items="${slaves}">

                <!-- konstruoja įrašo atnaujinimo adresą su skaičiaus id -->
                <c:url var="update" value="/update">
                    <c:param name="id" value="${slave.id}"/>
                </c:url>

                <!-- konstruoja įrašo trynimo adresą su skaičiaus id -->
                <c:url var="delete" value="/delete">
                    <c:param name="id" value="${slave.id}"/>
                </c:url>

                <!-- konstruoja įrašo peržiūros adresą su skaičiaus id -->
                <c:url var="show" value="/show">
                    <c:param name="id" value="${slave.id}"/>
                </c:url>

                <tr>
                    <td>${slave.deviceName}</td>
                    <td>${slave.portName}</td>
                    <td>${slave.slaveId}</td>
                    <td>${slave.startAddress}</td>
                    <td>${slave.numRegisters}</td>
                    <td>${slave.addresses}</td>
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
    <a type="button" href="/add_new_slave" class="btn btn-info">Add new slave</a>
    <jsp:include page="footer.jsp"/>
</body>
</html>
