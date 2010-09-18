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
	table.PartyResultsReportInputSelection{
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
	table.PartyResultsReportInputSelection th {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #6380BA;
		color:#FFFFFF;
	}
	table.PartyResultsReportInputSelection td {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #ffffff;
	}
	#resultsPageErrorDiv
	{
		color:red;
		font-weight:bold;
		padding-top:12px;
	}
	.selectWidth
	{
		width:70px;
	}
	#partyResultsMainDiv
		{
			text-align:left;
			margin-left: 50px;
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
			var radioElmt=document.getElementById("reportLevelRadio");
			radioElmt.innerHTML='';
			
			var row1Elmt=document.getElementById("row1");
			row1Elmt.style.display = "";
									
			ELECTIONTYPE=value;
			
			var labelElmt=document.getElementById("reportLevelLabel");
			
			labelElmt.innerHTML="<b>Select Level Of Report</b>";

			if(ELECTIONTYPE=="2" || ELECTIONTYPE=="3" || ELECTIONTYPE=="4")
			{				
				var str="";
				str+='<input type="radio" name="reportLevel" value="State" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>State';
				str+='<input type="radio" name="reportLevel" value="District" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>District';
				str+='<input type="radio" name="reportLevel" value="Constituency" onclick="displaySelectOptions(this.value, ELECTIONTYPE)"/>Constituency';
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
		
		
		
	function displaySelectOptions(value, elecType)
	{
		
		var row2Elmt=document.getElementById("row2");
		row2Elmt.style.display = "";
		
		REPORTLEVEL=value;	

		var labelDivElmt=document.getElementById("selectLabel");
		var stateSelectDivElmt=document.getElementById("tdStateDataDiv");
		var distNConstSelectDivElmt=document.getElementById("tdDistNConstDataDiv");		
		var buttonDivElmt=document.getElementById("resultsPageButtonDiv");
		
		if(!labelDivElmt)
			return;			
		if(!stateSelectDivElmt)		
			return;			
		if(!distNConstSelectDivElmt)			
			return;			
		if(!buttonDivElmt)		
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
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="locationName(this.id);getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\');getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\')">';
			stateSelectDivElmt.innerHTML=sstr;
			// fill it with states							
			getParticipatedStatesForAnElection(elecType);
			//build district select box
			var str='';
			str+='<select class="nameSelect" name="districtSelectName" id="DistrictNameSelect" onchange="locationName(this.id)">';
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
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="locationName(this.id);getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\');getAllConstituenciesInStateByType('+elecType+',this.options[this.selectedIndex].value,\'ConstituencyNameSelect\')">';
			stateSelectDivElmt.innerHTML=sstr;
			// fill it with states							
			getParticipatedStatesForAnElection(elecType);

			//build constituencies select box
			var str='';							
			str+='<select class="nameSelect" name="constituencySelectName" id="ConstituencyNameSelect" onchange="locationName(this.id)">';
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
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="locationName(this.id);getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\');getAllConstituenciesInStateByType('+elecType+',this.options[this.selectedIndex].value,\'municipalityNameSelect\')">';
			stateSelectDivElmt.innerHTML=sstr;
			// fill it with states							
			getParticipatedStatesForAnElection(elecType);

			//build constituencies select box
			var str='';							
			str+='<select class="nameSelect" name="municipalitySelectName" id="municipalityNameSelect" onchange="locationName(this.id)">';
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
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="locationName(this.id);getPartiesForAState(this.options[this.selectedIndex].value,\'DistrictNameSelect\');getAllConstituenciesInStateByType('+elecType+',this.options[this.selectedIndex].value,\'corporationNameSelect\')">';
			stateSelectDivElmt.innerHTML=sstr;
			// fill it with states							
			getParticipatedStatesForAnElection(elecType);

			//build constituencies select box
			var str='';							
			str+='<select class="nameSelect" name="corporationSelectName" id="corporationNameSelect" onchange="locationName(this.id)">';
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
	//if()	
		var url = "getConstituenciesByElectionScope.action?"+rparam;
							
	callAjax1(rparam,jsObj,url);
	}

	function getPartiesForAState(id)
	{
		if(id == 0)
			return;
		var row3Elmt=document.getElementById("row3");
		var buttonDivElmt=document.getElementById("resultsPageButtonDiv");
		row3Elmt.style.display = "";
		var jsObj= 
		{		
			stateId: id,
			task:"getStaticParties"		
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/partiesAjaxAction.action?"+param;
		callAjax1(param,jsObj,url);
		var row4Elmt=document.getElementById("row4");
		row4Elmt.style.display = "";
		
		var bstr='';		
		bstr+='<input type="submit" name="submitButton" value="Submit"/>';		
		buttonDivElmt.innerHTML=bstr;
		
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
			var buttonDivElmt=document.getElementById("resultsPageButtonDiv");
			
			if(!labelDivElmt)
				return;			
			if(!stateSelectDivElmt)		
				return;			
			if(!distNConstSelectDivElmt)			
				return;			
			if(!buttonDivElmt)		
				return;		
			
			if(REPORTLEVEL=="Country")
			{				
				var bstr='';
				bstr+='<input type="submit" name="submitButton" value="Submit"/>';			
				buttonDivElmt.innerHTML=bstr;
				return;
			}
						
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
			var bstr='';		
			bstr+='<input type="submit" name="submitButton" value="Submit"/>';		
			buttonDivElmt.innerHTML=bstr;
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
					                			alert( "Failed to load result" + o.status + " " + o.statusText);
					                         }
					               };

					YAHOO.util.Connect.asyncRequest('GET', url, callback);
			}
		
		
		function validateData()
		{
			/*if(REPORTLEVEL=="Country")
				return true;*/
			var errorDivElmt=document.getElementById("resultsPageErrorDiv");
			var stateSelectElmt=document.getElementById("stateNameSelect");
			var stateVal=stateSelectElmt.options[stateSelectElmt.selectedIndex].text;

			errorDivElmt.innerHTML="";

			if(stateVal=="Select State" || stateVal=="")
			{
				errorDivElmt.innerHTML='<%=selectStateMsg %>';
				return false;
			}
			else
			{
				ShowImage();

				if(REPORTLEVEL=="District")
				{
					var districtSelectElmt=document.getElementById("districtNameSelect");
					var districtVal=districtSelectElmt.options[districtSelectElmt.selectedIndex].text;
						if(districtVal=="Select District" || districtVal=="")
						{
							errorDivElmt.innerHTML="*Select District";
							return false;
						}											
				}
				else if(REPORTLEVEL=="Constituency")
				{
					var constituencySelectElmt=document.getElementById("constituencyNameSelect");
					var constituencyVal=constituencySelectElmt.options[constituencySelectElmt.selectedIndex].text;
						if(constituencyVal=="Select Constituency" || constituencyVal=="")
						{
							errorDivElmt.innerHTML="*Select Constituency";
							return false;
						}					
				}
			}
		}		

		function locationName(id){
			var selectObj = document.getElementById(id);
			val = selectObj.options[selectObj.selectedIndex].text;
			document.getElementById("selectedLocationName").value=val;
			return true;
		}
		
		function setPartyName(id, name){
		var selObj = document.getElementById("partyList");
		val = selObj.options[selObj.selectedIndex].text;
		document.getElementById("selectedPartyShortName").value=val;
		
		return true;
		}

		function setElectionType(electionType){
			document.getElementById("selectedElectionTypeName").value=electionType;			
			
			return true;
		}
  </script>
   </HEAD>
	
<BODY align="center" style="font-family: verdana,arial,sans-serif;"><br>
<H3 style="color:#444444;"><U>Party Results Report</U></H3>
<div id="resultsPageErrorDiv"></div>
<div id="partyResultsMainDiv">

<s:form name="partyResultsForm" action="partyResultsAction" onsubmit="return validateData()" method="post">
<input type="hidden" id="selectedPartyShortName" name="selectedPartyShortName">
<input type="hidden" id="selectedElectionTypeName" name="selectedElectionTypeName">
<input type="hidden" id="selectedLocationName" name="selectedLocationName">

<table align="center" class="PartyResultsReportInputSelection" width="830px" border="1">
 
  <tr>
 <th width="150px">
	<div  class="tdLabelDiv">
		<b>Select Election Type</b>
	</div>
 </th>
 <td width="550px" colspan="2">
	<div  class="tdDataDiv">
		<input type="radio" name="electionType" value="2" onclick="getReportLevel(this.value);setElectionType('Assembly');"/>Assembly
		<input type="radio" name="electionType" value="1" onclick="getReportLevel(this.value);setElectionType('Parliament');"/>Parliament	
		<input type="radio" name="electionType" value="4" onclick="getReportLevel(this.value);setElectionType('ZPTC');"/>ZPTC	
		<input type="radio" name="electionType" value="3" onclick="getReportLevel(this.value);setElectionType('MPTC');"/>MPTC	
		<input type="radio" name="electionType" value="5" onclick="getReportLevel(this.value);setElectionType('Municipal');"/>Municipal
		<input type="radio" name="electionType" value="6" onclick="getReportLevel(this.value);setElectionType('Corporation');"/>Corporation	
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
	<th width="150px">
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
 <tr id="row4" style="display:none;">
	<td colspan="3" align="center">
		<div id="resultsPageButtonDiv"></div>
	</td>
 </tr>

 </table>
 </s:form>
</div>

<div id="ajaxLoadDiv" style="display:none;padding-top:20px;">
	<span><b>Processing Request ...</b> </span>
	<img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/>
</div>


</BODY>
</HTML>