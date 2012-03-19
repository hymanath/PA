<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<HTML>
 <HEAD>
 <TITLE> Result Criteria</TITLE>
 <link rel="stylesheet" type="text/css" href="<s:url value='/styles/electionResultCriteria.css'/>"/>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <META NAME="Generator" CONTENT="EditPlus">
 <META NAME="Author" CONTENT="">
 <META NAME="Keywords" CONTENT="">
 <META NAME="Description" CONTENT="">
    
  <!-- Dependencies --> 
<script src="styles/yuiStyles/yahoo-min.js"></script> 
<!-- Source file --> 
<script src="js/json/json-min.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<style type="text/css">
.PartyResultsReportInputSelection
{
	 border-collapse: collapse;
    border-width: 1px;
    font-size: 13px;
 }
table.PartyResultsReportInputSelection th {
    background-color: #6380BA;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
    color: #FFFFFF;
    padding: 8px;
}
table.PartyResultsReportInputSelection td {
    background-color: #FFFFFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
    padding: 8px;
}
.buttonStyle {

  background: none repeat scroll 0 0 #6380BA;
    border: medium none;
    border-radius: 4px 6px 4px 6px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 15px;
    font-weight: bold;
    padding:3px;
    margin-top: 22px;
    width: 80px;
}
table.PartyResultsReportInputSelection {
    border-collapse: collapse;
    font-size: 12px;
}

</style>
<script type="text/javascript">
  var ELECTIONTYPE;
  var REPORTLEVEL;
  var STATELIST;
  var DISTRICTLIST;
  var CONSTITUENCYLIST;
  var STATELIST=false;
  var selectionType="";
  var RURL="<%=request.getContextPath()%>/partyResultScopeAction.action"

/*	Function to display the level of report  based on Election type selected	*/

		var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("global_ErrorMessages");
		String selectStateMsg = rb.getString("selectStateMsg");
				%> }

		
		function ShowImage()
		{
			var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
        	ajaxImgElmt.style.display = "block";
			return;
		}


		function getReportLevel(value)
		{	

			var errorDataDiv = document.getElementById("errorDataDiv");
            if(errorDataDiv)
			{
				var str = '';
                errorDataDiv.innerHTML = str;
			}

			var radioElmt=document.getElementById("reportLevelRadio");
			radioElmt.innerHTML='';
			
			var row1Elmt=document.getElementById("row1");
			row1Elmt.style.display = "";

			var row2Elmt=document.getElementById("row2");
			row2Elmt.style.display = "none";
			var row3Elmt=document.getElementById("row3");
			row3Elmt.style.display = "none";			
			var row4Elmt=document.getElementById("row4");
			row4Elmt.style.display = "none";
			
			ELECTIONTYPE=value;
			
			var labelElmt=document.getElementById("reportLevelLabel");
			
			labelElmt.innerHTML="<b>Select Level Of Report</b>";

			if(ELECTIONTYPE=="2" || ELECTIONTYPE=="3" || ELECTIONTYPE=="4")
			{				
				var str="";
				str+='<input type="radio" name="reportLevel" value="State" onclick="displaySelectOptions(this.value, ELECTIONTYPE)" style="margin:5px;"/>State';
				str+='<input type="radio" name="reportLevel" value="District" onclick="displaySelectOptions(this.value, ELECTIONTYPE)" style="margin:5px;"/>District';
				str+='<input type="radio" name="reportLevel" value="Constituency" onclick="displaySelectOptions(this.value, ELECTIONTYPE)" style="margin:5px;"/>Constituency';
				radioElmt.innerHTML=str;
			}else if(ELECTIONTYPE=="5")
			{
				var str="";
				str+='<input type="radio" name="reportLevel" value="State" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>State';
				str+='<input type="radio" name="reportLevel" value="District" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>District';
				str+='<input type="radio" name="reportLevel" value="Municipality" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>Municipality';
				radioElmt.innerHTML=str;
			}else if(ELECTIONTYPE=="6")
			{
				var str="";
				str+='<input type="radio" name="reportLevel" value="State" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>State';
				str+='<input type="radio" name="reportLevel" value="District" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>District';
				str+='<input type="radio" name="reportLevel" value="Corporation" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>Corporation';
				radioElmt.innerHTML=str;
			} 
			else if(ELECTIONTYPE=="1")
			{
				
				var str="";
				//str+='<input type="radio" name="reportLevel" value="Country"/>Country';
				str+='<input type="radio" name="reportLevel" value="State" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>State';				
				str+='<input type="radio" name="reportLevel" value="Constituency" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>Constituency';
				radioElmt.innerHTML=str;
			}
		}
		
	function hideSubmitDiv()
	{
		var row4Elmt=document.getElementById("row4");
		row4Elmt.style.display = "none";
	}	

	function hidePartyDiv()
	{
		var row3Elmt=document.getElementById("row3");
		row3Elmt.style.display = "none";
	}	
	
	function displaySelectOptions(value, elecType)
	{		
		var row2Elmt=document.getElementById("row2");
		row2Elmt.style.display = "";

		var row3Elmt=document.getElementById("row3");
		row3Elmt.style.display = "none";
		var row4Elmt=document.getElementById("row4");
		row4Elmt.style.display = "none";
		
		REPORTLEVEL=value;	

		var labelDivElmt=document.getElementById("selectLabel");
		var stateSelectDivElmt=document.getElementById("tdStateDataDiv");
		var distNConstSelectDivElmt=document.getElementById("tdDistNConstDataDiv");		
		
		if(!labelDivElmt)
			return;			
		if(!stateSelectDivElmt)		
			return;			
		if(!distNConstSelectDivElmt)			
			return;			
		
		if(REPORTLEVEL=="State")
		{				
			labelDivElmt.innerHTML="";			
			labelDivElmt.innerHTML="<b>Select State</b>";				
			stateSelectDivElmt.innerHTML = '';
			distNConstSelectDivElmt.innerHTML = '';
			var sstr='';
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\');locationName(this.id)">';
			stateSelectDivElmt.innerHTML=sstr;							

			getParticipatedStatesForAnElection(elecType);			
		}
		else if(REPORTLEVEL=="District")
		{					
			labelDivElmt.innerHTML="";				
			labelDivElmt.innerHTML="<b>Select State and District</b>";
			//build state select box first
			var sstr='';
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="hideSubmitDiv();hidePartyDiv();locationName(this.id);getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\')">';
			stateSelectDivElmt.innerHTML=sstr;
			// fill it with states							
			getParticipatedStatesForAnElection(elecType);
			//build district select box
			var str='';
			str+='<select class="nameSelect" name="districtSelectName" id="DistrictNameSelect" onchange="locationName(this.id);getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\');">';
			str+='	<option>Select District</option>';		
			str+='</select>';
			distNConstSelectDivElmt.innerHTML=str;			
		}
		else if(REPORTLEVEL=="Constituency")
		{				
			labelDivElmt.innerHTML="";		
			distNConstSelectDivElmt.innerHTML="";
						
			labelDivElmt.innerHTML="<b>Select State and Constituency</b>";
			//build state select box first
			var sstr='';
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="hideSubmitDiv();hidePartyDiv();locationName(this.id);getAllConstituenciesInStateByType('+elecType+',this.options[this.selectedIndex].value,\'ConstituencyNameSelect\')">';
			stateSelectDivElmt.innerHTML=sstr;
			// fill it with states							
			getParticipatedStatesForAnElection(elecType);

			//build constituencies select box
			var str='';							
			str+='<select class="nameSelect" name="constituencySelectName" id="ConstituencyNameSelect" onchange="locationName(this.id);getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\')">';
			str+='	<option>Select Constituency</option>';
			str+='</select>';
			distNConstSelectDivElmt.innerHTML=str;
		}
		else if(REPORTLEVEL=="Municipality")
		{				
			labelDivElmt.innerHTML="";		
			distNConstSelectDivElmt.innerHTML="";
						
			labelDivElmt.innerHTML="<b>Select State and Municipality</b>";
			//build state select box first
			var sstr='';
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="hideSubmitDiv();hidePartyDiv();locationName(this.id);getAllConstituenciesInStateByType('+elecType+',this.options[this.selectedIndex].value,\'municipalityNameSelect\')">';
			stateSelectDivElmt.innerHTML=sstr;
			// fill it with states							
			getParticipatedStatesForAnElection(elecType);

			//build constituencies select box
			var str='';							
			str+='<select class="nameSelect" name="municipalitySelectName" id="municipalityNameSelect" onchange="locationName(this.id);getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\')">';
			str+='	<option>Select Minicipality</option>';
			str+='</select>';
			distNConstSelectDivElmt.innerHTML=str;
		}
		else if(REPORTLEVEL=="Corporation")
		{				
			labelDivElmt.innerHTML="";		
			distNConstSelectDivElmt.innerHTML="";
						
			labelDivElmt.innerHTML="<b>Select State and Municipality</b>";
			//build state select box first
			var sstr='';
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="hideSubmitDiv();hidePartyDiv();locationName(this.id);getAllConstituenciesInStateByType('+elecType+',this.options[this.selectedIndex].value,\'corporationNameSelect\')">';
			stateSelectDivElmt.innerHTML=sstr;
			// fill it with states							
			getParticipatedStatesForAnElection(elecType);

			//build constituencies select box
			var str='';							
			str+='<select class="nameSelect" name="corporationSelectName" id="corporationNameSelect" onchange="locationName(this.id);getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\')">';
			str+='	<option>Select Minicipality</option>';
			str+='</select>';
			distNConstSelectDivElmt.innerHTML=str;
		}		
				
				
	}
			
	function getAllConstsForState(id,element, electionTypeId)
	{
		var jsObj=
		{				
				task: "getConstituenciesByElectionScope",
				elmtId: element,
				stateId:id,
				electionType: electionTypeId				 	
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getConstituenciesByElectionScope.action?"+rparam;
		callAjax1(rparam,jsObj,url);
	}

	function getPartiesForAState(id)
	{		
		if(id == 0){		
			var row3Elmt=document.getElementById("row3");	
			row3Elmt.style.display = "none";
			return;
		}else{		
		
				var stateSelectElmt = document.getElementById("stateNameSelect");				
				var stateValue = stateSelectElmt.options[stateSelectElmt.selectedIndex].value;
			var jsObj= 
			{		
				stateId: stateValue,
				task:"getStaticParties"		
			}
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/partiesAjaxAction.action?"+param;
			callAjax1(param,jsObj,url);
			var row3Elmt=document.getElementById("row3");
			row3Elmt.style.display = "";
			var row4Elmt=document.getElementById("row4");
			row4Elmt.style.display = "none";

			}
		}
		
		/*	Function to display the selectbox with values(State/District/Constituency) based on
			 level of report selected	*/
		function displaySelectBox(value, elecType)
		{	
			
			var row2Elmt=document.getElementById("row2");
			row2Elmt.style.display = "";
			
			REPORTLEVEL=value;		
			
			var labelDivElmt=document.getElementById("selectLabel");
			var stateSelectDivElmt=document.getElementById("tdStateDataDiv");
			var distNConstSelectDivElmt=document.getElementById("tdDistNConstDataDiv");		
			
			
			if(!labelDivElmt)
				return;			
			if(!stateSelectDivElmt)		
				return;			
			if(!distNConstSelectDivElmt)			
				return;			
			
					
			if(REPORTLEVEL=="State")
			{				
				labelDivElmt.innerHTML="";			
				distNConstSelectDivElmt.innerHTML="";						
				
				labelDivElmt.innerHTML="<b>Select State</b>";				
				if(!STATELIST)
				{
					stateSelectDivElmt.innerHTML = '';
					STATELIST=true;	
					var lstr=getStateList(elecType);
					stateSelectDivElmt.innerHTML=lstr;							
				}
			}
			else if(REPORTLEVEL=="District")
			{					
				labelDivElmt.innerHTML="";				
				labelDivElmt.innerHTML="<b>Select State and District</b>";
				if(!STATELIST)
				{
					STATELIST=true;	
					var lstr=getStateList(elecType);
					stateSelectDivElmt.innerHTML=lstr;							
				}
				var stateSelectElmt = document.getElementById("stateNameSelect");				
				var stateValue = stateSelectElmt.options[stateSelectElmt.selectedIndex].text;				
				var str='';
				str+='<select class="nameSelect" name="districtSelectName" id="DistrictNameSelect" onchange="locationName(this.id)">';
				if(stateValue=="Select State")					
					str+='	<option>Select District</option>';		
				else
				{								
					var List=getList(REPORTLEVEL,stateValue);
					for(var v in List)
						str+='	<option value="' +List[v].id+'">'+List[v].name+'</option>';
						
				}			
				str+='</select>';
				distNConstSelectDivElmt.innerHTML=str;
			}
			else if(REPORTLEVEL=="Constituency")
			{				
				labelDivElmt.innerHTML="";		
				distNConstSelectDivElmt.innerHTML="";
							
				labelDivElmt.innerHTML="<b>Select State and Constituency</b>";

				if(!STATELIST)
				{
					STATELIST=true;	
					var lstr=getStateList(elecType);
					stateSelectDivElmt.innerHTML=lstr;							
				}
				var stateSelectElmt = document.getElementById("stateNameSelect");
				var item = document.forms["partyResultsForm"].electionType;
                for (var i=0; i < item.length; i++)
                {
                  if (item[i].checked)
                  {
                    var elecTypeId = item[i].value;
                   }
                }
				var stateValue = stateSelectElmt.options[stateSelectElmt.selectedIndex].text;	
				var str='';							
				str+='<select class="nameSelect" name="constituencySelectName" id="ConstituencyNameSelect" onchange="locationName(this.id)">';
				if(stateValue=="Select State")	
					str+='	<option>Select Constituency</option>';
				else
				{					
					var List=getList(REPORTLEVEL,elecTypeId);					
					for(var v in List)
						str+='	<option value="' +List[v].id+'">'+List[v].name+'</option>';
				}				
				str+='</select>';
				distNConstSelectDivElmt.innerHTML=str;
			}
			
		}
		
		function getParticipatedStatesForAnElection(value, element)
		{
			var jsObj = {
					
					electionType: value,
					task: "getStates"
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
				var url = "getParticipatedStatesForAnElection.action?"+rparam; 
				callAjax1(rparam,jsObj,url);
		}

		function callAjax1(param,jsObj,url){
			var myResults;
				
				var callback = {			
				               success : function( o ) 
							  {
								try {			
										if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
										if(jsObj.task == "getStates")
										{									
											clearOptionsListForSelectElmtId('stateNameSelect');
											createOptionsForSelectElmtIdWithSelectOption('stateNameSelect',myResults);
										}
										if(jsObj.task == "getStaticParties")
										{									
											clearOptionsListForSelectElmtId('partyList');
											createOptionsForSelectElmtId('partyList',myResults);
										}
										if(jsObj.task == "getConstituenciesByElectionScope")
										{									
											clearOptionsListForSelectElmtId(jsObj.elmtId);
											createOptionsForSelectElmtId(jsObj.elmtId,myResults);
										}
										
								}
								catch (e)
									{   
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
		
		
		function validateData()
		{
			var errorDivElmt=document.getElementById("resultsPageErrorDiv");
			var stateSelectElmt=document.getElementById("stateNameSelect");
			var stateVal=stateSelectElmt.options[stateSelectElmt.selectedIndex].text;
			var partySelectElmt= document.getElementById("partyList");
			var partySelectNameVal=partySelectElmt.options[partySelectElmt.selectedIndex].text;
			var str = '';
			var errorFlag=0;
			if(partySelectNameVal=="Select Party" || partySelectNameVal=="")
			{
				str+='<b style="color:bold;">Please Select a Party</b>'
				errorFlag=1;
			}
			if(stateVal=="Select State" || stateVal=="")
			{
				str+='<b style="color:bold;">Please Select a State</b>';				
				errorFlag=1;	
			}
			if(REPORTLEVEL=="State"){
				var stateSelectElmt=document.getElementById("stateNameSelect");
				var stateVal=stateSelectElmt.options[stateSelectElmt.selectedIndex].text;
				if(stateVal=="Select State" || districtVal=="")
				{
					errorDivElmt.innerHTML="*Select State";
					errorFlag=1;
				}		
			}else if(REPORTLEVEL=="District")
			{
				var districtSelectElmt=document.getElementById("DistrictNameSelect");			
				var districtVal=districtSelectElmt.options[districtSelectElmt.selectedIndex].text;
				if(districtVal=="Select District" || districtVal=="")
				{
					errorDivElmt.innerHTML="*Select District";
					errorFlag=1;
				}										
			}else if(REPORTLEVEL=="Constituency")
			{
				var constituencySelectElmt=document.getElementById("ConstituencyNameSelect");
				var constituencyVal=constituencySelectElmt.options[constituencySelectElmt.selectedIndex].text;
					if(constituencyVal=="Select Constituency" || constituencyVal=="")
					{
						errorDivElmt.innerHTML="*Select Constituency";
						errorFlag=1;
					}					
			}
		   if(errorFlag==1){
				document.getElementById("errorMessage").innerHTML = message;
				return false;
			}else{
				ShowImage();
				return true;
			}
		}		

		function locationName(id){
			var selectObj = document.getElementById(id);
			val = selectObj.options[selectObj.selectedIndex].text;
			document.getElementById("selectedLocationName").value=val;
		}
		
		function setPartyName(id, name){
			var selObj = document.getElementById("partyList");
			var val = selObj.options[selObj.selectedIndex].text;
			document.getElementById("selectedPartyShortName").value=val;
			var id = selObj.options[selObj.selectedIndex].value;
			document.getElementById("selectedPartyId").value = id;
			var row4DivElmt=document.getElementById("row4");
			var buttonDivElmt=document.getElementById("resultsPageButtonDiv");
			var bstr='';	
			if(val!="Select Party"){		
				row4DivElmt.style.display="block";
				buttonDivElmt.style.display="block";			
				bstr+='<input type="submit" name="submitButton" onclick="return validateData()" value="Submit" class="buttonStyle"/>';		
				buttonDivElmt.innerHTML=bstr;			
			}else{
				row4DivElmt.style.display="none";
				buttonDivElmt.style.display="none";
			}
		}

		function setElectionType(electionType){
			document.getElementById("selectedElectionTypeName").value=electionType;	
		}
		window.history.forward(1);
  </script>
</head>
<body>
<center>
<div id="partyResultCriteriaMainDiv" style="height: 500px;">
<H3 style="margin-top: 50px; font-weight: bold; color: #FFFFFF; width: 300px; border-radius: 5px 5px 5px 5px; background:#9966CC; padding: 3px 0px; text-align: center;">Party Results Report</H3>
<div id="resultsPageErrorDiv"></div>
<div id="partyResultsMainDiv" style="margin-top: 30px;">
		<table class="errorDIV">
			<tr>
				<td colspan="2">
					<div id="errorDataDiv" style="color: red;">
						<s:actionerror />
						<s:fielderror />
						<s:actionmessage/>						
					</div>
				</td>
			</tr>
		</table>
<s:form name="partyResultsForm" action="partyResultsAction" method="GET">
<input type="hidden" id="selectedPartyShortName" name="selectedPartyShortName">
<input type="hidden" id="selectedPartyId" name="selectedPartyId">
<input type="hidden" id="selectedElectionTypeName" name="selectedElectionTypeName">
<input type="hidden" id="selectedLocationName" name="selectedLocationName">

<table align="center" class="PartyResultsReportInputSelection" width="830px" border="1" style="background:#FFFFFF;text-align: left;"">
 
  <tr>
 <th width="150px">
	<div  class="tdLabelDiv">
		<b>Select Election Type</b>
	</div>
 </th>
 <td width="550px" colspan="2">
	<div  class="tdDataDiv">
		<input type="radio" name="electionType" value="2" onclick="getReportLevel(this.value);setElectionType('Assembly');" style="margin:5px;"/>Assembly
		<input type="radio" name="electionType" value="1" onclick="getReportLevel(this.value);setElectionType('Parliament');" style=" margin:5px;"/>Parliament	
		<input type="radio" name="electionType" value="4" onclick="getReportLevel(this.value);setElectionType('ZPTC');" style=" margin: 5px;"/>ZPTC	
		<input type="radio" name="electionType" value="3" onclick="getReportLevel(this.value);setElectionType('MPTC');" style=" margin: 5px;"/>MPTC	
	</div>
 </td>
 </tr>

 <tr id="row1" style="display:none;">
	<th width="150px">
		<div class="tdLabelDiv" id="reportLevelLabel"></div>
	</th>
	<td width="550px" colspan="2">
		<div class="tdDataDiv" id="reportLevelRadio"></div>
	</td>
 </tr>

 <tr id="row2" style="display:none;">
	<th width="173px">
		<div class="tdLabelDiv" id="selectLabel"></div>
	</th>
	<td width="150px">
		<div class="tdStateDataDiv" id="tdStateDataDiv"></div>
	</td>
	<td width="300px">
	<div class="tdDistrictDataDiv" id="tdDistNConstDataDiv"></div>
</td>

 </tr>
 <tr id="row3" style="display:none;">
 	<th width="150px">
 		<div  class="tdLabelDiv">
 			<b>Party</b>
 		</div>
 	</th>
 	<td width="550px" colspan="2">

	<div  class="tdDataDiv">
	 <select id="partyList" name="partySelectName" onchange='return setPartyName();'>				
	  </select> 					
	 </div>					
 		
 	</td>
 </tr>
 
 <tr id="allianceRow">
		<th>Alliance Parties</th>
		<td>
			<s:checkbox theme="simple" id="alliances" disabled="false" name="alliances" value="hasAllianceParties"></s:checkbox> Include in the Report
		</td>
	</tr>
 </table>

<center> 
 <table>
	 <tr id="row4" style="display:none;">
		<td colspan="3" align="center">
			<div id="resultsPageButtonDiv"></div>
		</td>
	 </tr>
 </table>
</center>

 </s:form>
 <table>
	<tr>
 		<td align="left">
	 		<div id="errorMessage"></div>
		</td>
	</tr>
 </table>
</div>

<div id="ajaxLoadDiv" style="display:none;padding-top:20px;">
	<span><b>Processing Request ...</b> </span>
	<img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/>
</div>

</div>
</center>

</body>
</html>