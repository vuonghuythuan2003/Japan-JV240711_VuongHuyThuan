<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/12/2024
  Time: 7:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Cập nhật lớp học</title>
</head>
<h1>Cập nhật lớp học</h1>
<body>
<c:if test="${not empty errorMessage}">
  <p style="color: red;">${errorMessage}</p>
</c:if>
<form action="<%=request.getContextPath()%>/classesController/update" method="post">
  <label for="classId">Mã lớp học:</label><br>
  <input type="text" id="classId" name="classId" value="${classes.classId}" readonly><br><br>

  <label for="className">Tên lớp học:</label><br>
  <input type="text" id="className" name="className" value="${classes.className}" required maxlength="100"><br><br>

  <label for="majors">Chuyên ngành:</label><br>
  <input type="text" id="majors" name="majors" value="${classes.majors}" required maxlength="255"><br><br>

  <button type="submit">Cập nhật lớp học</button>
  <a href="<%=request.getContextPath()%>/classesController/findAll">Hủy</a>
</form>
</body>
</html>
