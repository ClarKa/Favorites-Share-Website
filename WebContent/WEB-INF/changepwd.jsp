<!-- /**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-13 04:06:14
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:56:41
 */ -->


<jsp:include page="template-top.jsp" />

<p style="font-size:medium">
	Enter your new password
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="POST" action="changepwd.do">
		<table>
			<tr>
				<td> New Password: </td>
				<td><input type="password" name="newPassword" value=""/></td>
			</tr>
			<tr>
				<td> Confirm New Password: </td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Change Password"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
