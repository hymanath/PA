<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Comparison</title>
<style type="text/css">
table.CandidateElectionResultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.CandidateElectionResultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #94A9C8;
}
table.CandidateElectionResultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

</style>
<script>
	function displayYearList()
	{
		var electionTypeElmt=document.getElementById("electionTypeList");
		var electionTypevalue=electionTypeElmt.options[electionTypeElmt.selectedIndex].value;

		var statesListElmt=document.getElementById("statesList");
		var statesListvalue=statesListElmt.options[statesListElmt.selectedIndex].value;

		var partyListElmt=document.getElementById("partyList");
		var partyListvalue=partyListElmt.options[partyListElmt.selectedIndex].value;

		if(electionTypevalue==0 || statesListvalue==0 || partyListvalue==0)
			return;

		var jsObj=
		{
			electionId:electionTypevalue,
			stateId:statesListvalue,
			partyId:partyListvalue
		}		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);

		callAjax(rparam);
	}
	function callAjax(rparam)
	{
		
		var url = "<%=request.getContextPath()%>/electionComparisonAjaxAction.action?"+rparam;		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 
								
								buildYearSelect(myResults.yearsList);								
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

	function buildYearSelect(value)
	{
		var rowElmt=document.getElementById("yearRow");
		var str='';
		str+='<td><b>Years </b></td>';
		str+='<td>';
		str+='<select id="yearStartSelect" name="startYear">';
		str+='<option value="0">Start</option>';
		for (var item in value)
		{
			str+='<option value='+value[item].id+'>'+value[item].name+'</option>';
		}
		str+='</select>';
	
		str+='<select id="yearEndSelect" name="endYear" style="margin-left:10px;">';
		str+='<option value="0">End</option>';
		for (var item in value)
		{
			str+='<option value='+value[item].id+'>'+value[item].name+'</option>';
		}
		str+='</select>';
		str+='</td>';
		rowElmt.innerHTML=str;
	}
</script>
</head>
<body>
<s:form action="electionComparisonReportAction.action">

	<table class="CandidateElectionResultsTable" width="300px" border="1">
	<tr>
		<th colspan="2">
				<span style="margin: 0px; text-align: center;">Elections Comparison Report</span>
		</th>
	</tr>
	<tr>
	   <th>Election Type</th>
	   <td >
			<s:select theme="simple" name="electionType" id="electionTypeList" list="electionType" headerKey="0" headerValue="Select" listKey="id" listValue="name" />
		</td>
	 </tr>
	 <tr>
	   <th >State</th>
	   <td >
			<s:select theme="simple" name="states" id="statesList" list="statesList" headerKey="0" headerValue="Select" listKey="id" listValue="name" />
		</td>
	 </tr>
	
	 <tr >
	   <th>Party</th>
	   <td >
			<s:select theme="simple" name="party" id="partyList" list="partyList" headerKey="0" headerValue="Select" listKey="id" listValue="name" />
		</td>
	 </tr>
	 <tr id="yearRow">
		
	 </tr>
	 
	 <tr>
	   <th>Elections Years</th>
	   <td colspan="2" >
			<s:select theme="simple" name="electionYears1" id="yearsList1" list="yearsList" headerKey="0" headerValue="Select" listKey="name" listValue="name" />
		
			<s:select theme="simple" name="electionYears2" id="yearsList2" list="yearsList" headerKey="0" headerValue="Select" listKey="name" listValue="name" />
		</td>
	 </tr>
	 <tr id="yearRow">
		
	 </tr>
	 <tr>
		<th colspan="2" align="center">
			<s:submit theme="simple" value="display Results"/>
		</th>
	 </tr>
	 
   </table>

   </s:form>
</body>
</html>