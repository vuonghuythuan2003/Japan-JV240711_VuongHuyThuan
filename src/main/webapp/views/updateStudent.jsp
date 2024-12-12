<%-- Created by IntelliJ IDEA. User: Admin Date: 12/12/2024 Time: 7:46 PM To change this template use File | Settings | File Templates. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cập nhật sinh viên</title>
</head>
<body>
<c:if test="${not empty errorMessage}">
  <p style="color: red;">${errorMessage}</p>
</c:if>
<h1>Cập nhật sinh viên</h1>
<form action="${pageContext.request.contextPath}/studentsController/update" method="post" enctype="multipart/form-data">
  <label for="studentId">Mã sinh viên:</label>
  <input type="text" id="studentId" name="studentId" value="${student.studentId}" readonly>
  <br><br>

  <label for="studentName">Tên sinh viên:</label>
  <input type="text" id="studentName" name="studentName" value="${student.studentName}" required maxlength="100">
  <br><br>

  <label for="phoneNumber">Số điện thoại:</label>
  <input type="text" id="phoneNumber" name="phoneNumber" value="${student.phoneNumber}" required pattern="\d{10,11}">
  <br><br>

  <label for="studentEmail">Email:</label>
  <input type="email" id="studentEmail" name="studentEmail" value="${student.studentEmail}" required maxlength="100">
  <br><br>

  <label for="studentAddress">Địa chỉ:</label>
  <textarea id="studentAddress" name="studentAddress" maxlength="150" required>${student.studentAddress}</textarea>
  <br><br>

  <label for="sex">Giới tính:</label>
  <select id="sex" name="sex" required>
    <option value="true" ${student.sex ? 'selected' : ''}>Nam</option>
    <option value="false" ${!student.sex ? 'selected' : ''}>Nữ</option>
  </select>
  <br><br>

  <label for="avatarFile">Chọn ảnh mới:</label>
  <input type="file" id="avatarFile" name="avatarFile" accept="image/*">
  <br><br>

  <label for="classEntity">Lớp học:</label>
  <select id="classEntity" name="classEntity.classId" required>
    <c:forEach items="${listClasses}" var="classa">
      <option value="${classa.classId}" ${student.classEntity.classId == classa.classId ? 'selected' : ''}>${classa.className}</option>
    </c:forEach>
  </select>
  <br><br>

  <label for="status">Trạng thái:</label>
  <select id="status" name="status" required>
    <option value="0" ${student.status == 0 ? 'selected' : ''}>Đang đi học</option>
    <option value="1" ${student.status == 1 ? 'selected' : ''}>Đã hoàn thành</option>
    <option value="2" ${student.status == 2 ? 'selected' : ''}>Tốt nghiệp</option>
    <option value="3" ${student.status == 3 ? 'selected' : ''}>Bảo lưu</option>
    <option value="4" ${student.status == 4 ? 'selected' : ''}>Buộc thôi học</option>
  </select>
  <br><br>

  <button type="submit">Cập nhật sinh viên</button>
  <a href="${pageContext.request.contextPath}/studentController/findAll">Hủy</a>
</form>
</body>
</html>
