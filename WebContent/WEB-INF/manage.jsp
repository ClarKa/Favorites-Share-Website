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
	Add a new favorite:
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="post" action="add.do">
		<table>
			<tr>
				<td>Url: </td>
				<td colspan="2"><input type="text" name="url" value="${fomr.url}"/></td>
			</tr>
			<tr>
				<td>Comment: </td>
				<td><input type="text" name="comment" value="${form.comment}"/></td>
				<td>(optional)</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" name="button" value="add to favorites"/>
				</td>
			</tr>
		</table>
	</form>
</p>
<hr/>
<%@ page import="databeans.FavoriteBean" %>
<p>
	<table>
<%
        FavoriteBean[] favs = (FavoriteBean[])request.getAttribute("favList");
        for (int i=0; i<favs.length; i++) {
%>
        <tr>
            <td valign="top">
                <form method="POST" action="remove.do">
                    <input type="hidden" name="id" value="<%=favs[i].getFavoriteId()%>"/>
                    <input type="submit" value="X"/>
                </form>
            </td>
            <td valign="top">
                <p><a href="view.do?id=<%=favs[i].getFavoriteId()%>&url=<%=favs[i].getUrl()%>"><%=favs[i].getUrl()%></a></p>
                <p>Comment: <%=favs[i].getComment()%></p>
                <p>Clicks: <%=favs[i].getCount()%></p>
                <hr>
            </td>
        </tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
