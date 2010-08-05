<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ResourceBundle;" %>
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
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script>
var labelResources = { <%		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		String electionType = rb.getString("electionType");
		String state = rb.getString("state");
		String electionYear  = rb.getString("electionYear");
		String electionScope = rb.getString("electionScope");
		String party  = rb.getString("party");
		String dist = rb.getString("dist");
		String viewReport = rb.getString("viewReport");
		String alliances = rb.getString("alliances");
		String inclAlliances = rb.getString("inclAlliances");
		%> }		
		function getElectionScopes(id){
			var jsObj=
				{
						electionTypeId:id,
						task:"getElectionScopes"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/getElectionScopesForECAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}

		function getElectionYears(id)
		{
			var jsObj=
			{
					electionScopeId:id,
					task:"getElectionYears"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsForECAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
		}
		
		function callAjax(rparam, jsObj, url){
			var resultVO;			
			var callback = {			
		               success : function( o ) {
							try {								
									resultVO = YAHOO.lang.JSON.parse(o.responseText);										

									if(jsObj.task == "getElectionScopes")
									{			
										clearOptionsListForSelectElmtId("electionScopeSelect");							
										createOptionsForSelectElmtId("electionScopeSelect",resultVO);
									}else
									if(jsObj.task == "getElectionYears")
									{								
										clearOptionsListForSelectElmtId("electionYearSelect1");
										createOptionsForSelectElmtId("electionYearSelect1",resultVO);		

										clearOptionsListForSelectElmtId("electionYearSelect2");
										createOptionsForSelectElmtId("electionYearSelect2",resultVO);	
									}				
							}catch (e)  {   
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

		function getPartyname(value)
		{
			var elmt = document.getElementById("selectedPartyName");
			if(!elmt)
				return;
			
			elmt.value=value;

		}
		
</script>
</head>
<body>
<div style="margin-top:40px;">
	<s:form action="electionComparisonReportAction.action">
		<table class="CandidateElectionResultsTable" width="300px" border="1">
			<tr>
				<th colspan="2">
					<span style="margin: 0px; text-align: center;">Elections Comparison</span>
				</th>
			</tr>
			<tr>
				<th align="left"><%=electionType%></th>
				<td>
					<select id="electionTypeSelect" onchange = "getElectionScopes(this.options[this.selectedIndex].value)" class = "selectWidth">
						<option value="0">Select </option>
						<option value="1">Parliament</option>
						<option value="2">Assembly</option>
					</select>
				</td>
			</tr>
			<tr>
				<th align="left"><%=electionScope%></th>
				<td>
					<select id="electionScopeSelect" onchange = "getElectionYears(this.options[this.selectedIndex].value)" class = "selectWidth">
						<option value="0">Select </option>
					</select>
				</td>
			</tr>
			<tr>
				<th align="left"><%=electionYear%></th>
				<td>
					<select id="electionYearSelect1" class = "selectWidth" name="electionId1">
						<option value="0">Select </option>
					</select>
					<select id="electionYearSelect2" class = "selectWidth" name="electionId2">
						<option value="0">Select </option>
					</select>
				</td>
			</tr>
			<tr>
			   <th align="left"><%=party%></th>
			   <td  align="left">
					<s:select theme="simple" name="party" id="partyList" onchange="getPartyname(this.options[this.selectedIndex].text)" list="partyList" headerKey="0" headerValue="Select" listKey="id" listValue="name" />
					<input type="hidden" id="selectedPartyName" name="selectedPartyName">
			   </td>
			</tr>
			<tr>
				<th align="left"><%=alliances%></th>
				<td><s:checkbox theme="simple" id="allianceCheck" name="allianceCheck" value="hasAllianceParties"></s:checkbox><%=inclAlliances%></td>
	 		</tr>
	 		<tr>
				<th colspan="2" align="center">
					<s:submit theme="simple" value="View Report"/>
				</th>
	 		</tr>
		</table>
	</s:form>
</div>
</body>
</html>