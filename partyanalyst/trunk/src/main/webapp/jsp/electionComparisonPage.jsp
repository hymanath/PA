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
	font-size:13px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.CandidateElectionResultsTable th {
	border-width: 1px;
	padding: 11px;
	border-style: solid;
	border-color: #666666;
	background-color: #94A9C8;
}
table.CandidateElectionResultsTable td {
	border-width: 1px;
	padding: 11px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
#errorMessage
{
	color:red;
	font-weight:bold;
	padding-top:12px;
}
.selectWidth
{
	width:200px;
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

var Localization = { <%
		
		ResourceBundle rbs = ResourceBundle.getBundle("global_ErrorMessages");
		String electionTypeMsg = rbs.getString("electionTypeMsg");
		String electionYearMsg = rbs.getString("electionYearMsg");
		String electionScopeMsg = rbs.getString("electionScopeMsg");
		String partyNameMsg = rbs.getString("partyNameMsg");
		String oneElectionMsg = rbs.getString("oneElectionMsg");
			%> }

var selectedElectionScopeId,selectedParty;	
var yearsPopulation={
		allYearsArray:[],
		remainingYearsArray:[]
	}; 

		function getElectionScopes(elmt,id)
		{	
		   removeErrorMessage();
		   if(id==0)
			errorMsg(elmt);
		
		   if(id!=0){	
				showBusyImgWithId(elmt.id);
				var jsObj=
				{
						elmtId:elmt.id,
						electionTypeId:id,
						task:"getElectionScopes"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/getElectionScopesForECAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
			}				
		}
		function removeErrorMessage(){
			if(document.getElementById("errorMessage")){
				document.getElementById("errorMessage").innerHTML="";
			}
		}
        function errorMsg(elmt){
          	if(elmt.id=="electionTypeSelect"){
              document.getElementById("errorMessage").innerHTML='<h3><%=electionTypeMsg%></h3>';
                clearOptionsListForSelectElmtId("electionScopeSelect");
				createSelectOptionsForSelectElmtId("electionScopeSelect");	
				clearOptionsListForSelectElmtId("partyList");
                createSelectOptionsForSelectElmtId("partyList");
				clearOptionsListForSelectElmtId("electionYearSelect1");	
                clearOptionsListForSelectElmtId("electionYearSelect2");
				createSelectOptionsForSelectElmtId("electionYearSelect1");
                createSelectOptionsForSelectElmtId("electionYearSelect2");
			}
			if(elmt.id == "electionScopeSelect"){
			   document.getElementById("errorMessage").innerHTML='<h3><%=electionScopeMsg%></h3>';
			    clearOptionsListForSelectElmtId("partyList");
                createSelectOptionsForSelectElmtId("partyList");
				clearOptionsListForSelectElmtId("electionYearSelect1");	
                clearOptionsListForSelectElmtId("electionYearSelect2");
				createSelectOptionsForSelectElmtId("electionYearSelect1");
                createSelectOptionsForSelectElmtId("electionYearSelect2");
			}
			if(elmt.id == "partyList")
			{
                document.getElementById("errorMessage").innerHTML='<h3><%=partyNameMsg%></h3>';
				clearOptionsListForSelectElmtId("electionYearSelect1");	
                clearOptionsListForSelectElmtId("electionYearSelect2");
				createSelectOptionsForSelectElmtId("electionYearSelect1");
                createSelectOptionsForSelectElmtId("electionYearSelect2");
			}
             if(elmt.id == "electionYearSelect1")
			{
				
              document.getElementById("errorMessage").innerHTML='<h3><%=electionYearMsg%></h3>';
			   clearOptionsListForSelectElmtId("electionYearSelect2");
			   createSelectOptionsForSelectElmtId("electionYearSelect2");
			}
		}

		function getElectionYears(elmt,id,name)
		{	

			removeErrorMessage();
            if(id==0)
				 errorMsg(elmt);
			
			if(id!=0){	
			showBusyImgWithId(elmt.id);
			
			document.getElementById("selectedParty").value = name; 
			selectedParty = name;
			var jsObj=
			{
					elmtId:elmt.id,
					electionScopeId:selectedElectionScopeId,
					partyId:id,
					task:"getElectionYears"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsForECAction.action?"+rparam;						
			}
			callAjax(rparam,jsObj,url);
		}

		function getPartiesInState(elmtId)
		{
			removeErrorMessage();
			var jsObj=
			{
					elmtId:elmtId,
					stateId: selectedElectionScopeId,
					task:"getPartiesInState"						
			};
			
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getPartiesForState.action?"+rparam;						
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
                                    if(jsObj.task == "getPartiesInState")
									{			
										clearOptionsListForSelectElmtId("partyList");															
										createOptionsForSelectElmtIdWithSelectOption("partyList",resultVO);
										hideBusyImgWithId(jsObj.elmtId);
									}
									if(jsObj.task == "getElectionScopes")
									{			
										clearOptionsListForSelectElmtId("electionScopeSelect");															
										createOptionsForSelectElmtIdWithSelectOption("electionScopeSelect",resultVO);
										hideBusyImgWithId(jsObj.elmtId);
									}else
									if(jsObj.task == "getElectionYears")
									{								
										var emptyArr = new Array();							   
									    yearsPopulation.allYearsArray = emptyArr;
									    yearsPopulation.remainingYearsArray = emptyArr;
									    	
										clearOptionsListForSelectElmtId("electionYearSelect1");
										createOptionsForSelectElmtIdWithSelectOption("electionYearSelect1",resultVO);		
										clearOptionsListForSelectElmtId("electionYearSelect2");	
										storeYears(resultVO);
										hideBusyImgWithId(jsObj.elmtId);
									}				
							}catch (e)  {   
							   	alert("Invalid JSON result" + e);   
							}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };
		
			YAHOO.util.Connect.asyncRequest('GET', url, callback);			
		}
		
		function hideBusyImgWithId(elmtId)
		{
			var spanElmt = document.getElementById(elmtId+"_ImgSpan");
			if(spanElmt)
				spanElmt.style.display = "none";
		}

		function showBusyImgWithId(elmtId)
		{
			var spanElmt = document.getElementById(elmtId+"_ImgSpan");
			if(spanElmt)
				spanElmt.style.display = "";
		}

		function getPartyname(value)
		{
			var elmt = document.getElementById("selectedPartyName");
			if(!elmt)
				return;
			
			elmt.value=value;
		}

		function getSelectedElectionScope(elmt,id)
		{	
			removeErrorMessage();			
			
             if(id==0)
				 errorMsg(elmt);
			 if(id!=0){	
			showBusyImgWithId(elmt.id);
			selectedElectionScopeId = id;
			getPartiesInState(elmt.id);
			}
		}
		function populateElectionYearsForSecondElectionYearsSelectBox(value)
		{	
			var elmt = document.getElementById("electionYearSelect1");
			if(value == "Select")
			errorMsg(elmt);
			else{

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
				
		}
		function validateAndForwardToAction(elmt)
		{
		    var errorFlag=0;
			var message="";	
			var electionFlag=0;				
			if(yearsPopulation.allYearsArray.length==1)
			{		
				//message+=selectedParty;
				message+=" ";
				message+='<%=oneElectionMsg %>';
				message+='<br/>';		
				errorFlag=1;						
			}
			if(document.getElementById("electionTypeSelect").value==0){
				message+='<%=electionTypeMsg %>';
				message+='<br/>';
				errorFlag=1;
			}
			if(document.getElementById("electionScopeSelect").value==0){
				message+='<%=electionScopeMsg %>';
				message+='<br/>';
				errorFlag=1;
			}
			if(document.getElementById("partyList").value==0){
				message+='<%=partyNameMsg %>';
				message+='<br/>';
				errorFlag=1;
			} 
			if(document.getElementById("electionYearSelect1").value==0){
				message+='<%=electionYearMsg %>';
				message+='<br/>';
				errorFlag=1;
				electionFlag=1;
			}
			if(errorFlag==1){
				document.getElementById("errorMessage").innerHTML = message;
				return false;
			}else{
				document.electionComparisionForm.action="electionComparisonReportAction.action";
				document.electionComparisionForm.method="get"
				document.electionComparisionForm.submit();
				return true;							
			}	
		}

		function refresh()
		   {
			window.location.reload(true);
		    }
		window.history.forward(1);	
</script>
</head>
<body onload = "refresh()">
<div id="electionComparisionMainDiv" style="margin-left:auto;margin-right:auto;float:none;height:500px;width:400px;">

<div style="margin-top:40px;">
	<form name="electionComparisionForm">
	<table style="margin-top: -15px;">
			<tr>
	 			<td align="left">
	 				<div id="errorMessage"></div>
	 			</td>
	 		</tr>
		</table>
		<table class="CandidateElectionResultsTable" width="400px;" border="1">
			<tr>
				<th colspan="2">
					<span style="margin: 0px; text-align: center;">Elections Comparison</span>
				</th>
			</tr>
			<tr>
				<th align="left"><%=electionType%></th>
				<td align="left">
					<select id="electionTypeSelect"  onchange = "getElectionScopes(this,this.options[this.selectedIndex].value)" class = "selectWidth">
						<option value="0">Select Election Type</option>
						<option value="2">Assembly</option>
						<option value="1">Parliament</option>
					</select>
					<span id="electionTypeSelect_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif"></span>
				</td>
			</tr>
			<tr>
				<th align="left"><%=electionScope%></th>
				<td align="left">
					<select id="electionScopeSelect" onchange = "getSelectedElectionScope(this,this.options[this.selectedIndex].value)" class = "selectWidth" >
						<option value="0">Select Election Scope </option>
					</select>
					<span id="electionScopeSelect_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif"></span>
				</td>
			</tr>
			<tr>
			   <th align="left"><%=party%></th>
			   <td  align="left">
					<s:select theme="simple" name="party" id="partyList" cssClass = "selectWidth"  onchange = "getElectionYears(this,this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)" list="{}" headerKey="0" headerValue="Select Party" listKey="id" listValue="name" />
					<input type="hidden" id="selectedParty" name="selectedPartyName">
					<span id="partyList_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif"></span>
			   </td>
			</tr>
			<tr>
				<th align="left"><%=electionYear%></th>
				<td align="left">
					<select id="electionYearSelect1" style="width:98px;" onchange = "populateElectionYearsForSecondElectionYearsSelectBox(this.options[this.selectedIndex].text)"  name="electionId1">
						<option value="0">Select Year</option>
					</select>
					<select id="electionYearSelect2" style="width:98px;" name="electionId2">
						<option value="0">Select Year </option>
					</select>
				</td>
			</tr>
			<tr>
				<th align="left"><%=alliances%></th>
				<td align="left"><s:checkbox theme="simple" id="allianceCheck" name="allianceCheck" value="hasAllianceParties"></s:checkbox><%=inclAlliances%></td>
	 		</tr>
	 		<tr>
				<th colspan="2" align="center">
					<input id="viewReportButton" type="button" onClick="return validateAndForwardToAction(this)"  value="View Report"/>
					<span id="viewReportButton_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif"></span>
				</th>
	 		</tr>	 		
		</table>
		
	</form>
</div>

</div>

</body>
</html>

