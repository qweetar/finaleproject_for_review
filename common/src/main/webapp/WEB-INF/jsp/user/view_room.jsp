
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
<div align="right">
    <a href="FrontController?command=INDEX">
        <p style="text-align: right">
            <input class="btn account-info" type="button" value="Logout">
        </p>
    </a>
</div>
<div align="center">
    <label>
    List of Rooms
    </label>
</div>

<table align="center">
    <thead>
    <tr>
        <th scope="col">
            <p>Room ID</p>
        </th>
        <th scope="col">
            <p>Room Number</p>
        </th>
        <th scope="col">
            <p>Number of bedrooms</p>
        </th>
        <th scope="col">
            <p>Room Class</p>
        </th>
        <th scope="col">
            <p>Price per day</p>
        </th>
        <th scope="col">
            <p>Status</p>
        </th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${roomList}" var="room">
    <tr>
        <td>
            <p class="item-stock">${room.roomId}</p>
        </td>
        <td>
            <p class="item-stock">${room.roomNum}</p>
        </td>
        <td>
            <p class="item-stock">${room.roomSize}</p>
        </td>
        <td>
            <p class="item-stock">${room.roomClass}</p>
        </td>
        <td>
            <p class="item-stock">${room.roomPricePerDay}</p>
        </td>
        <td>
            <p class="item-stock">${room.roomStatus}</p>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>