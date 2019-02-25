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

<div align="right">
    <a href="FrontController?command=INDEX">
        <p style="text-align: right">
            <input class="btn account-info" type="button" value="Logout">
        </p>
    </a>
</div>
<fieldset class="account-info">
    <label>
     Thank you, for Application! We'll call you soon.
    </label>
</fieldset>

<fieldset class="account-action">
    <a href="FrontController?command=VIEW_APPLICATION">
        <p style="text-align: center">
            <input class="btn" type="button" value="View my Applications">
        </p>
    </a>
</fieldset>
</body>
</html>
