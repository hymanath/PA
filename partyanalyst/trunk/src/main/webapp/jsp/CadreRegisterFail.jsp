<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Registration Exception Page</title>
<script type="text/javascript">
var exception = '${rs.exceptionEncountered}';
<%

ResourceBundle rb = ResourceBundle.getBundle("global_ErrorMessages");
String exceptionMsg = rb.getString("exceptionMsg");

%>
window.history.forward(1);
</script>
</head>
<body>
<P><div style="color:#C0566F;font-size:12px;"><%=exceptionMsg%></div></P>
<s:form name="cadrereport" action="cadreRegisterPageAction" method="get" theme="simple">
<input type="hidden" id="hiddenVal" name="cadreId" value="0"/>
<input type="hidden" id="hiddenValue" name="windowTask" value="new"/>
<input type="submit" id="registerCadreSubmit" name="registersubmit" value="Try Again!"/>
</s:form>

</body>
</html>