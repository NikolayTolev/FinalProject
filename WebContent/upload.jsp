<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="app.js"></script>


</head>
<body>
	<form method="post" action="upload" enctype="multipart/form-data">
		<input type="text" placeholder="username" name="username"><br>
		<input type="file" accept="image/*" name="file"><br>
		<input type="submit">
	</form>
</body>
</html>