<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=8; IE=EDGE">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login Form</title>    
    	<link rel="stylesheet" type="text/css" href="StyleLog.css"> 

</head>
<body>
		
		<div class="login">
  			<h1>Login</h1>
    		<form id="login" class="prompt" action="LoginController" method="post" name="f" autocomplete="off">
      			<input type="text" name="employeeID" placeholder="Employee ID" required="required" />
        		<input type="password" name="password" placeholder="Password" required="required" />
        		<button type="submit" class="btn btn-primary btn-block btn-large">Login</button>
    		</form>
		</div>

</body>
</html>