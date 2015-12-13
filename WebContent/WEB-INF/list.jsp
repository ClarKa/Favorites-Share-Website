<!-- /**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-13 04:06:14
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:56:41
 */ -->
<jsp:include page="template-top.jsp" />

<%@ page import="databeans.FavoriteBean" %>
<p>
	<table>
<%
    for (FavoriteBean f : (FavoriteBean[])request.getAttribute("favList")) {
%>
        <tr>
    		<td valign="top">
                <p><a href="view.do?id=<%=f.getFavoriteId()%>&url=<%=f.getUrl()%>"><%=f.getUrl()%></a></p>
                <p>Comment: <%=f.getComment()%></p>
                <p>Clicks: <%=f.getCount()%></p>
                <hr>
            </td>
        </tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
