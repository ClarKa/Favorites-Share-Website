<!-- /**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-13 04:06:14
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:56:41
 */ -->
<%
    java.util.List<String> errors = (java.util.List) request.getAttribute("errors");
	if (errors != null && errors.size() > 0) {
		out.println("<p style=\"font-size:medium; color:red\">");
		for (String error : errors) {
			out.println(error+"<br/>");
		}
		out.println("</p>");
	}
%>
