
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div align="center">
    Application page
</div>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<div align="right">
    <a href="FrontController?command=INDEX">
        <p style="text-align: right">
            <input class="btn account-info" type="button" value="Logout">
        </p>
    </a>
</div>
<section>
<div>
    <form action="FrontController" id="form-page-application">
        <fieldset class="account-info">
        <input type="hidden" name="command" value="application">
            <label>
                Order a room
        <input type="text" id="roomsize" name="roomsize" size="20" maxlength="25" placeholder="number of guests: 1-4">
        <input type="text" id="roomclass" name="roomclass" size="20" maxlength="25" placeholder="room class: A, B, C, D">
        <input type="date" id="datefrom" name="datefrom" size="20" placeholder="date from">
        <input type="date" id="dateto" name="dateto" size="20" placeholder="date to">
            </label>
        </fieldset>
        <fieldset class="account-action">
        <input class="btn" type="submit" id="finish_button" value="Rent a room">
        </fieldset>
    </form>
</div>
</section>
<aside>
<fieldset class="account-action">
    <a href="FrontController?command=VIEW_ROOM">
        <p style="text-align: center">
            <input class="btn" type="button" value="View Rooms">
        </p>
    </a>
<a href="FrontController?command=VIEW_APPLICATION">
    <p style="text-align: center">
        <input class="btn" type="button" value="View my Applications">
    </p>
</a>
</fieldset>
</aside>
</body>
</html>
