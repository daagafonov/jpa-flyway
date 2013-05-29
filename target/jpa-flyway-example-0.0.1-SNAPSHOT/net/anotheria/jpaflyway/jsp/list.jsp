<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Users list</h1>
<table border="1" cellspacing="0" cellpadding="0">
	<tr>
		<th>ID</th>
		<th>Login</th>
		<th>Gender</th>
		<th>Email</th>
	</tr>
	<c:forEach var="user" items="${users}" varStatus="indx">
		<tr>
			<td>${user.id}</td>
			<td>${user.login}</td>
			<td>${user.gender}</td>
			<td>${user.email}</td>
		</tr>
	</c:forEach>
</table>

<a href="register">Register</a>