<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Danh sách sinh viên</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }

    h1 {
      text-align: center;
      color: #4CAF50;
    }

    a {
      text-decoration: none;
      color: white;
      background-color: #4CAF50;
      padding: 5px 10px;
      border-radius: 4px;
      margin: 5px;
    }

    a:hover {
      background-color: #45a049;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin: 20px 0;
    }

    table th, table td {
      border: 1px solid #ddd;
      text-align: center;
      padding: 10px;
    }

    table th {
      background-color: #f4f4f4;
      color: #333;
    }

    table tr:nth-child(even) {
      background-color: #f9f9f9;
    }

    table tr:hover {
      background-color: #f1f1f1;
    }

    .add-student {
      display: flex;
      justify-content: flex-end;
      margin-bottom: 20px;
    }

    .add-student a {
      color: white;
      background-color: #4CAF50;
      padding: 10px 15px;
      text-align: center;
      border-radius: 5px;
    }

    .image-cell img {
      max-width: 100px;
      max-height: 100px;
      border-radius: 5px;
    }

    .status {
      font-weight: bold;
      color: #4CAF50;
    }

    .status.inactive {
      color: red;
    }
  </style>
</head>
<body>
<h1>Danh sách sinh viên</h1>

<div class="add-student">
  <a href="<%=request.getContextPath()%>/studentsController/initCreate">+ Thêm sinh viên</a>
</div>

<c:choose>
  <c:when test="${empty listStudents}">
    <p style="color: red; text-align: center;">Danh sách trống!</p>
  </c:when>
  <c:otherwise>
    <table>
      <thead>
      <tr>
        <th>No</th>
        <th>Mã sinh viên</th>
        <th>Tên sinh viên</th>
        <th>Số điện thoại</th>
        <th>Email</th>
        <th>Địa chỉ</th>
        <th>Giới tính</th>
        <th>Lớp học</th>
        <th>Ảnh</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${listStudents}" var="student" varStatus="loop">
        <tr>
          <td>${loop.index + 1}</td>
          <td>${student.studentId}</td>
          <td>${student.studentName}</td>
          <td>${student.phoneNumber}</td>
          <td>${student.studentEmail}</td>
          <td>${student.studentAddress}</td>
          <td>${student.sex ? "Nam" : "Nữ"}</td>
          <td>${student.classEntity.className}</td>
          <td class="image-cell">
            <c:choose>
              <c:when test="${empty student.imageUrl}">
                Không có hình ảnh
              </c:when>
              <c:otherwise>
                <img src="${student.imageUrl}" alt="${student.studentName}" />
              </c:otherwise>
            </c:choose>
          </td>
          <td class="status ${student.status == 4 ? 'inactive' : ''}">
            <c:choose>
              <c:when test="${student.status == 0}">Đang đi học</c:when>
              <c:when test="${student.status == 1}">Đã hoàn thành</c:when>
              <c:when test="${student.status == 2}">Tốt nghiệp</c:when>
              <c:when test="${student.status == 3}">Bảo lưu</c:when>
              <c:when test="${student.status == 4}">Buộc thôi học</c:when>
            </c:choose>
          </td>
          <td>
            <a href="<%=request.getContextPath()%>/studentsController/initUpdate?id=${student.studentId}">Sửa</a>
            <a href="<%=request.getContextPath()%>/studentsController/delete?id=${student.studentId}" onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên này?');">Xóa</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:otherwise>
</c:choose>
</body>
</html>
