
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div align="center">
    <div>
        <form action="FrontController" id="form-page-registration">
            <fieldset class="account-info">
                <label>
                    Registration form
            <input type="hidden" name="command" value="registration">
            <input type="text" id="Login" name="login" size="20" maxlength="25" placeholder="Login">
            <input type="password" id="Password" name="password" size="20" maxlength="25" placeholder="Password">
            <input type="text" id="Name" name="name" size="20" maxlength="25" placeholder="Name">
            <input type="text" id="Surname" name="surname" size="20" maxlength="25" placeholder="Surname">
            <input type="text" id="Phone" name="phone" size="20" maxlength="25" placeholder="Phone">
            <input type="text" id="Email" name="email" size="20" maxlength="50" placeholder="Email">
                </label>
            </fieldset>
            <fieldset class="account-action">
            <input class="btn" type="submit" id="finish_button" value="Register">
            </fieldset>
        </form>
    </div>
</div>
