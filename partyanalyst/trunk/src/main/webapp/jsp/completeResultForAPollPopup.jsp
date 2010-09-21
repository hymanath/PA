<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complete Result</title>

<style type="text/css">
	.heading
	{
		font-weight:bold;
		font-family:verdana;
		color:#003399;
		padding-top:4px;
		padding-left:10px;
		padding-right:10px;
		padding-top:4px;
		padding-bottom:4px;
	}
	#pollsTable
	{
		margin-top:40px;
		margin-left:40px;
	}
	.cellStyle
	{
		padding-left:10px;
		padding-right:10px;
		padding-top:4px;
		padding-bottom:4px;
	}
	
</style>

</head>
<body>
	<table>
		<tr>
			<td valign="top" align="left">
				<table id="pollsTable"  border="1">
					<tr>
						<td class="heading">Option</td>
						<td class="heading">Percentage</td>
					</tr>
					 <c:forEach var="polls" varStatus="stat" items="${questionsOptionsVO.options}">
						 <tr>
      						<td class="cellStyle">
      		 	  				${polls.option}
      						</td>
      						<td class="cellStyle">
      		 	  				${polls.percentage} %
      						</td>
      					</tr>
					 </c:forEach>
				</table>
			</td>	
			<td align="right">
				<table>
						<tr>
							<td>
									<img src="charts/${questionsOptionsVO.imagePath}"></img>				
							</td>
						</tr>
				</table>
			</td>		
		</tr>
</table>		
</body>
</html>