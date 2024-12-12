<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/12/2024
  Time: 7:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm lớp học</title>
</head>
<h1>Thêm lớp học</h1>
<body>
<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>

<form action="<%=request.getContextPath()%>/classesController/create" method="post">
    <label for="className">Tên lớp học:</label><br>
    <input type="text" id="className" name="className" required maxlength="100"><br><br>

    <label for="majors">Chuyên ngành:</label><br>
    <input type="text" id="majors" name="majors" required maxlength="255"><br><br>

    <button type="submit">Thêm lớp học</button>
    <button type="reset">Reset</button>
</form>
</body>
</html>

