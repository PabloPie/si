<%@ page isErrorPage="true" import="java.io.*" contentType="text/plain"%>

Ha ocurrido un error interno.
Message:
<%=exception.getMessage()%>

StackTrace:
<%
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	exception.printStackTrace(printWriter);
	out.println(stringWriter);
	printWriter.close();
	stringWriter.close();
%>