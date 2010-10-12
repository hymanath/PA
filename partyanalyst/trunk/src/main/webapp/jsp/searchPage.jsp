<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search  partyanalyst for Politician or Constituency</title>
<style>
#searchBlock
{
	background-color:#94A9C8;
	border:1px solid;
	color:#FFFFFF;
	font-weight:bold;
	margin-top:15px;
	padding:15px;
	width:250px;
}
.adData_head {
	border-bottom:1px solid #ADADAD;
	font-weight:bold;
	padding:5px;
	width: 210px;
	margin: 10px;
}
</style>
</head>
<body>
	<div class="adData_head">Search for Candidate or Constituency</div>
	<div id="searchBlock">
	 <jsp:include page="../jsp/cncSearch.jsp"/>
	</div>
</body>
</html>