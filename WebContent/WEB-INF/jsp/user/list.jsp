<%@ page language="java" import="java.util.List,com.wskj.springmvc.pojo.UserInfo" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>

    <table>
    <c:forEach items="${requestScope.users}" var="user" varStatus="status">
      <tr>
      	<td>${user.userId}</td>
        <td>${user.userName}</td>
        <td>${user.userPass}</td>
        <td>${user.userPhone}</td>
      </tr>
    </c:forEach>
    </table>
</body>
</html>