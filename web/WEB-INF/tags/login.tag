<%@attribute name="name"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>

<h1>Remember me login page</h1>
<form action="Login" method="post">
    Username: <input type="text" name="username" value=${usernameEntered}><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Login"><br>
    <input type="checkbox" name="rememberMe" value="remember">Remember me
</form>
${displayMessage}