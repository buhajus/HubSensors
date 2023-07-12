<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pool data </title>
    <jsp:include page="header.jsp"/>
    <jsp:include page="menu.jsp"/>
</head>
<body class="container">

    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>CL</th>
                <th>PH</th>
                <th>Temperature</th>
                <th>Date time</th>
                <th>Device name</th>
                <th>Alarm status</th>

            </tr>

            <!-- iteruoja per visą skaičių sarašą -->
            <c:forEach var="pool" items="${pool_list}">

                <tr>
                    <td>${pool.chloride}</td>
                    <td>${pool.ph}</td>
                    <td>${pool.temp}</td>
                    <td>${pool.dateTime}</td>
                     <td>${pool.deviceName}</td>

                     <c:if test="${pool.alarm == false}"><td class="good-temp"> Temp gera </td></c:if>
                     <c:if test="${pool.alarm == true}"> <td class="has-error"> Šalta </td></c:if>

                </tr>
            </c:forEach>

        </table>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
