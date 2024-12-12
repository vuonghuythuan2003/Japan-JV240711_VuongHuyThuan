<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/12/2024
  Time: 7:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thêm sinh viên</title>
</head>
<body>
<c:if test="${not empty errorMessage}">
  <p style="color: red;">${errorMessage}</p>
</c:if>

<h1>Thêm sinh viên</h1>
<form action="${pageContext.request.contextPath}/studentsController/create" method="post" enctype="multipart/form-data">
  <label for="studentName">Tên sinh viên:</label>
  <input type="text" id="studentName" name="studentName" placeholder="Nhập tên sinh viên" required maxlength="100">
  <br><br>

  <label for="phoneNumber">Số điện thoại:</label>
  <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Nhập số điện thoại" required pattern="\d{10,11}">
  <br><br>

  <label for="studentEmail">Email:</label>
  <input type="email" id="studentEmail" name="studentEmail" placeholder="Nhập email" required maxlength="100">
  <br><br>

  <label for="studentAddress">Địa chỉ:</label>
  <textarea id="studentAddress" name="studentAddress" placeholder="Nhập địa chỉ" maxlength="150" required></textarea>
  <br><br>

  <label for="sex">Giới tính:</label>
  <select id="sex" name="sex" required>
    <option value="true">Nam</option>
    <option value="false">Nữ</option>
  </select>
  <br><br>

  <label for="avatarFile">Chọn ảnh sản phẩm:</label>
  <input type="file" id="avatarFile" name="avatarFile" accept="image/*">
  <br><br>

  <label for="classEntity">Lớp học:</label>
  <select id="classEntity" name="classEntity.classId" required>
    <c:forEach items="${listClasses}" var="classa">
      <option value="${classa.classId}">${classa.className}</option>
    </c:forEach>
  </select>
  <br><br>

  <label for="status">Trạng thái:</label>
  <select id="status" name="status" required>
    <option value="0">Đang đi học</option>
    <option value="1">Đã hoàn thành</option>
    <option value="2">Tốt nghiệp</option>
    <option value="3">Bảo lưu</option>
    <option value="4">Buộc thôi học</option>
  </select>
  <br><br>

  <button type="submit">Thêm sinh viên</button>
  <a href="${pageContext.request.contextPath}/studentController/findAll">Hủy</a>
</form>
</body>
</html>
