<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ taglib prefix="s" uri="/struts-tags" %>


</head>
<body>
    <s:form name="messagePosting" action="PostMessage" method="POST">
        <table>
            <tr>
				<td colspan="4">Select Constituency Type</td>
			</tr>	
			<tr>
				<td colspan="2"><input type="radio" checked="checked" name="a_radio"  value="assembly" onclick="hideUnhideSelectBox(this.id, 'constituency')"/>assembly</td>
				<td><input type="radio" name="a_radio"  onclick="hideUnhideSelectBox(this.id,'constituency')"/>parliament</td>
			</tr>
			<tr>
				<td style="color:#004078">Select State</td>
			</tr>
			<tr>
				<input type="text" name="state" />
			</tr>
			
			<tr>
				<td style="color:#004078">Select Your Constituency</td>
			</tr>		
			<tr>
				<input type="text" name="constituency" />
			</tr>
			<tr>
				<td style="color:#004078">Enter the message to display</td>
			</tr>
			<tr>
				<input type="text" name="message" />
			</tr>
			<tr>	
				<td align="right">
					<s:submit value="Post Message" align="center"/>
				</td>
			</tr>	
							
		</table>
    </s:form>
</body>
</html>