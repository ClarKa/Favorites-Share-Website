<!-- /**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-13 04:06:14
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:56:41
 */ -->
<html>
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<title>Favorites Sharing Website</title>
	<style>
		.menu-head {font-size: 10pt; font-weight: bold; color: black; }
		.menu-item {font-size: 10pt;  color: black }
    </style>
</head>

<body>
<%@ page import="databeans.UserBean" %>

<table cellpadding="4" cellspacing="0">
    <tr>
	    <!-- Banner row across the top -->
        <td width="130" bgcolor="#99FF99"></td>
        <td bgcolor="#99FF99">&nbsp;  </td>
        <td width="500" bgcolor="#99FF99">
            <p align="center">
<%
	if (request.getAttribute("title") == null) {
%>
		        <font size="7">"Favorites" Sharing Website</font>
<%
    } else {
%>
		        <font size="5"><%=request.getAttribute("title")%></font>
<%
    }
%>
			</p>
		</td>
    </tr>

	<!-- Spacer row -->
	<tr>
		<td bgcolor="#99FF99" style="font-size:5px">&nbsp;</td>
		<td colspan="2" style="font-size:5px">&nbsp;</td>
	</tr>

	<tr>
		<td bgcolor="#99FF99" valign="top" height="500">
			<!-- Navigation bar is one table cell down the left side -->
            <p align="left">
<%
    UserBean user = (UserBean) session.getAttribute("user");
	if (user == null) {
%>
				<span class="menu-item"><a href="login.do">Login</a></span><br/>
				<span class="menu-item"><a href="register.do">Register</a></span><br/>
<%
    } else {
%>
				<span class="menu-head"><%=user.getFirstname()%> <%=user.getLastname()%></span><br/>
				<span class="menu-item"><a href="manage.do">Manage Your Favorites</a></span><br/>
				<span class="menu-item"><a href="changepwd.do">Change Password</a></span><br/>
				<span class="menu-item"><a href="logout.do">Logout</a></span><br/>
				<span class="menu-item">&nbsp;</span><br/>
<%
    }
%>
                <br>
                <span class="menu-head">Favorites From:</span><br/>
<%
    for (UserBean u : (UserBean[])request.getAttribute("userList")) {
%>
    		    <span class="menu-item">
    				<a href="list.do?userId=<%=u.getUserid()%>">
    					<%=u.getFirstname()%> <%=u.getLastname()%>
    				</a>
    			</span>
    			<br/>
<%
	}
%>
			</p>
		</td>

		<td>
			<!-- Padding (blank space) between navbar and content -->
		</td>
		<td  valign="top">
