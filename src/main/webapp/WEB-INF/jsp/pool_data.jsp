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
                <th>CL name</th>
                <th>PH location</th>
                <th>Temperature</th>
                <th>Date time</th>

            </tr>

            <!-- iteruoja per visą skaičių sarašą -->
            <c:forEach var="pool" items="${pool_list}">


                <tr>
                    <td>${pool.cl}</td>
                    <td>${pool.ph}</td>
                    <td>${pool.temp}</td>
                    <td>${pool.date_time}</td>



                </tr>

            </c:forEach>

        </table>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
