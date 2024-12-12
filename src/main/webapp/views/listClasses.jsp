<%-- Created by IntelliJ IDEA. User: Admin Date: 12/12/2024 Time: 6:52 PM To change this template use File | Settings | File Templates. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách lớp học</title>
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

        .pagination {
            text-align: center;
            margin: 20px 0;
        }

        .pagination a {
            color: #333;
            padding: 8px 16px;
            text-decoration: none;
            margin: 0 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .pagination a.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }

        .pagination a:hover {
            background-color: #ddd;
        }

        .add-class {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 20px;
        }

        .add-class a {
            color: white;
            background-color: #4CAF50;
            padding: 10px 15px;
            text-align: center;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<h1>Danh sách lớp học</h1>

<div class="add-class">
    <a href="<%=request.getContextPath()%>/classesController/initCreate">+ Thêm lớp học</a>
</div>

<c:choose>
    <c:when test="${empty classesList}">
        <p style="color: red; text-align: center;">Danh sách trống!</p>
    </c:when>
    <c:otherwise>
        <table>
            <thead>
            <tr>
                <th>No</th>
                <th>Mã lớp</th>
                <th>Tên lớp</th>
                <th>Chuyên ngành</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${classesList}" var="cls" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${cls.classId}</td>
                    <td>${cls.className}</td>
                    <td>${cls.majors}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/classesController/initUpdate?id=${cls.classId}">Sửa</a>
                        <a href="<%=request.getContextPath()%>/classesController/delete?id=${cls.classId}"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa lớp này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/classesController/findAll?page=${currentPage - 1}&size=${size}">«</a>
            </c:if>

            <c:forEach begin="1" end="${totalPages}" var="page">
                <a href="${pageContext.request.contextPath}/classesController/findAll?page=${page}&size=${size}"
                   class="${page == currentPage ? 'active' : ''}">${page}</a>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="${pageContext.request.contextPath}/classesController/findAll?page=${currentPage + 1}&size=${size}">»</a>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
