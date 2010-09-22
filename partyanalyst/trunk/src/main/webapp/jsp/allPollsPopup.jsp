<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Polls</title>
<style type="text/css">
	
	#pollsDiv
	{
		text-align:left;		
		margin-top:40px;
		margin-left:40px;
		font-family:verdana;
		font-weight:bold;
		color:#0C67AC;
		text-align:left;
	}
</style>
<script type="text/javascript">
	function getCompletePollResult(questionId){  
		var browser1 = window.open("completeResultForAPollAction.action?questionId="+questionId,"completeResultForAPoll","scrollbars=yes,height=600,width=650,left=200,top=200");
		browser1.focus();
	}
</script>
</head>
<body>
<div id="pollsDiv">
	<table>
      <c:forEach var="polls" varStatus="stat" items="${allPolls}">
      	<tr>
      		<td style="padding-right:40px;">
      		 	  ${polls.name}
      		</td>
      		<td>
      			<a href="javascript:{}" onclick="getCompletePollResult(${polls.id})" style="text-align:right;margin-top:15px;text-decoration:underline;"> view more details</div>
      		</td>
      	</tr>		
      </c:forEach>
     </table>
</div>
</body>
</html>