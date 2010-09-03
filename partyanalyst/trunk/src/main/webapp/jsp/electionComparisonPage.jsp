<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Comparison Report</title>
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
#errorMessage
{
	color:red;
	font-weight:bold;
	padding-top:30px;
}
.selectWidth
{
	width:70px;
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
var selectedElectionScopeId,selectedParty;	
var yearsPopulation={
		allYearsArray:[],
		remainingYearsArray:[]
	}; 

		function getElectionScopes(id){
			removeErrorMessage();
						
			if(id!=0){				
				var jsObj=
				{
						electionTypeId:id,
						task:"getElectionScopes"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/getElectionScopesForECAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
			}else{
				clearOptionsListForSelectElmtId("electionScopeSelect");
				createSelectOptionsForSelectElmtId("electionScopeSelect");	
			}						
		}
		function removeErrorMessage(){
			if(document.getElementById("errorMessage")){
				document.getElementById("errorMessage").innerHTML="";
			}
		}

		function getElectionYears(id,name)
		{
			removeErrorMessage();
			document.getElementById("selectedParty").value = name; 
			selectedParty = name;
			var jsObj=
			{
					electionScopeId:selectedElectionScopeId,
					partyId:id,
					task:"getElectionYears"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsForECAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
		}
		function storeYears(results){
			yearsArray = new Array();

					for(var i in results)
					{
						var yearsObj= {
								 electionId:results[i].id,
								 years:results[i].name													
							};
						yearsArray.push(yearsObj);
						yearsPopulation.allYearsArray=yearsArray;	
					}									
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
										createOptionsForSelectElmtIdWithSelectOption("electionScopeSelect",resultVO);
									}else
									if(jsObj.task == "getElectionYears")
									{								
										var emptyArr = new Array();							   
									    yearsPopulation.allYearsArray = emptyArr;
									    yearsPopulation.remainingYearsArray = emptyArr;
									    	
										clearOptionsListForSelectElmtId("electionYearSelect1");
										createOptionsForSelectElmtId("electionYearSelect1",resultVO);		
										clearOptionsListForSelectElmtId("electionYearSelect2");	
										storeYears(resultVO);										
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

		function getSelectedElectionScope(id)
		{
			removeErrorMessage();
			selectedElectionScopeId = id;
		}
		function populateElectionYearsForSecondElectionYearsSelectBox(value)
		{
			clearOptionsListForSelectElmtId("electionYearSelect2");	
			yearsArray = new Array();
			for(var i in yearsPopulation.allYearsArray)
			{				
				if(yearsPopulation.allYearsArray[i].years!=value){
					var yearsObj= {
							 id:yearsPopulation.allYearsArray[i].electionId,
							 name:yearsPopulation.allYearsArray[i].years													
						};
					yearsArray.push(yearsObj);
					yearsPopulation.remainingYearsArray=yearsArray;
				}									
			}
			

			createOptionsForSelectElmtId("electionYearSelect2",yearsPopulation.remainingYearsArray);	
		}
		function validateAndForwardToAction()
		{			
			var errorFlag=0;
			var message="";	
			var electionFlag=0;				
			if(yearsPopulation.allYearsArray.length==1)
			{		
				message+=selectedParty;
				message+=' has participated in only one election.';
				message+='<br/>';		
				errorFlag=1;						
			}
			if(document.getElementById("electionTypeSelect").value==0){
				message+='Please select Election Type.';
				message+='<br/>';
				errorFlag=1;
			}
			if(document.getElementById("electionScopeSelect").value==0){
				message+='Please select Election Scope.';
				message+='<br/>';
				errorFlag=1;
			}
			if(document.getElementById("partyList").value==0){
				message+='Please select party.';
				message+='<br/>';
				errorFlag=1;
			} 
			if(document.getElementById("electionYearSelect1").value==0){
				message+='Please select Election Years.';
				message+='<br/>';
				errorFlag=1;
				electionFlag=1;
			}
			if(document.getElementById("electionYearSelect2").value==0){
				if(electionFlag!=1){
					message+='Please select Election Years.';
					message+='<br/>';						
				}
				errorFlag=1;
			}
			if(errorFlag==1){
				document.getElementById("errorMessage").innerHTML = message;
				return false;
			}else{
				document.electionComparisionForm.action="electionComparisonReportAction.action";
				document.electionComparisionForm.method="post"
				document.electionComparisionForm.submit();
				return true;							
			}	
		}
</script>
</head>
<body>
<div style="margin-top:40px;">
	<form name="electionComparisionForm">
		<table class="CandidateElectionResultsTable" width="300px" border="1">
			<tr>
				<th colspan="2">
					<span style="margin: 0px; text-align: center;">Elections Comparison</span>
				</th>
			</tr>
			<tr>
				<th align="left"><%=electionType%></th>
				<td align="left">
					<select id="electionTypeSelect" onchange = "getElectionScopes(this.options[this.selectedIndex].value)" class = "selectWidth">
						<option value="0">Select </option>
						<option value="1">Parliament</option>
						<option value="2">Assembly</option>
					</select>
				</td>
			</tr>
			<tr>
				<th align="left"><%=electionScope%></th>
				<td align="left">
					<select id="electionScopeSelect" onchange = "getSelectedElectionScope(this.options[this.selectedIndex].value)" style="width:105px;">
						<option value="0">Select </option>
					</select>
				</td>
			</tr>
			<tr>
			   <th align="left"><%=party%></th>
			   <td  align="left">
					<s:select theme="simple" name="party" id="partyList" style="width:70px;" onchange = "getElectionYears(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)" list="partyList" headerKey="0" headerValue="Select" listKey="id" listValue="name" />
					<input type="hidden" id="selectedParty" name="selectedPartyName">
			   </td>
			</tr>
			<tr>
				<th align="left"><%=electionYear%></th>
				<td align="left">
					<select id="electionYearSelect1" onchange = "populateElectionYearsForSecondElectionYearsSelectBox(this.options[this.selectedIndex].text)" class = "selectWidth" name="electionId1">
						<option value="0">Select </option>
					</select>
					<select id="electionYearSelect2" class = "selectWidth" name="electionId2">
						<option value="0">Select </option>
					</select>
				</td>
			</tr>
			<tr>
				<th align="left"><%=alliances%></th>
				<td align="left"><s:checkbox theme="simple" id="allianceCheck" name="allianceCheck" value="hasAllianceParties"></s:checkbox><%=inclAlliances%></td>
	 		</tr>
	 		<tr>
				<th colspan="2" align="center">
					<input type="button" onClick="return validateAndForwardToAction()"  value="View Report"/>
				</th>
	 		</tr>	 		
		</table>
		<table>
			<tr>
	 			<td align="left">
	 				<div id="errorMessage"></div>
	 			</td>
	 		</tr>
		</table>
	</form>
</div>
</body>
</html>