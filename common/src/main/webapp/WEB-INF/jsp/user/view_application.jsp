<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <meta charset="UTF-8">
    <title>Application</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<header>
<div align="center">
    Applications
</div>
<div align="right">
    <a href="FrontController?command=INDEX">
        <p style="text-align: right">
            <input class="btn account-info" type="button" value="Logout">
        </p>
    </a>
</div>
</header>
<section>
<div>
    <form action="FrontController" id="form-page-update_application">
        <fieldset class="account-info">
        <input type="hidden" name="command" value="update_application">
            <label>
                Change your Application
        <input type="text" id="idapplication-update" name="idapplication" size="20" maxlength="25" placeholder="application id">
        <input type="text" id="roomsize" name="roomsize" size="20" maxlength="25" placeholder="number of guests: 1-4">
        <input type="text" id="roomclass" name="roomclass" size="20" maxlength="25" placeholder="room class: A, B, C, D">
        <input type="date" id="datefrom" name="datefrom" size="20" placeholder="date from">
        <input type="date" id="dateto" name="dateto" size="20" placeholder="date to">
            </label>
        </fieldset>
        <fieldset class="account-action">
        <input class="btn" type="submit" id="finish_update_button" value="Change application">
        </fieldset>
    </form>
</div>
</section>

<section>
<div>
    <form action="FrontController" id="form-page-delete_application">
        <fieldset class="account-info">
            <input type="hidden" name="command" value="delete_application">
            <label>
                Delete your application
        <input type="text" id="idapplication-delete" name="idapplication" size="20" maxlength="25" placeholder="application id">
            </label>
        </fieldset>
        <fieldset class="account-action">
        <input class="btn" type="submit" id="finish_delete_button" value="Delete application">
        </fieldset>
    </form>
</div>
</section>

<aside>
<table>
    <thead>
    <tr>
        <th scope="col">
            <p>Application ID</p>
        </th>
        <th scope="col">
            <p>Room Class</p>
        </th>
        <th scope="col">
            <p>Number of guests</p>
        </th>
        <th scope="col">
            <p>Date from</p>
        </th>
        <th scope="col">
            <p>Date to</p>
        </th>
        <th scope="col">
            <p>User ID</p>
        </th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${applicationList}" var="application">
    <tr>
        <td>
            <p class="item-stock">${application.applicationId}</p>
        </td>
        <td>
            <p class="item-stock">${application.roomClass}</p>
        </td>
        <td>
            <p class="item-stock">${application.roomSize}</p>
        </td>
        <td>
            <p class="item-stock">${application.dateFrom}</p>
        </td>
        <td>
            <p class="item-stock">${application.dateTo}</p>
        </td>
        <td>
            <p class="item-stock">${application.userId}</p>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</aside>
</body>
</html>
