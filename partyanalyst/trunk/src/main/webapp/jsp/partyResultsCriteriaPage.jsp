<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script src="http://yui.yahooapis.com/2.7.0/build/yahoo/yahoo-min.js"></script> 
	 
	<!-- Source file --> 
	<script src="http://yui.yahooapis.com/2.7.0/build/json/json-min.js"></script>
	 
  <script type="text/javascript"><!--
  var ELECTIONTYPE;
  var REPORTLEVEL;
  var STATELIST;
  var DISTRICTLIST;
  var CONSTITUENCYLIST;
  var STATELIST=false;
  var RURL="<%=request.getContextPath()%>/partyResultScopeAction.action"

		/*	Function to display the level of report  based on Election type selected	*/
		function getReportLevel(value)
		{
			ELECTIONTYPE=value;
			var labelElmt=document.getElementById("reportLevelLabel");
			labelElmt.innerHTML="<b>Select Level Of Report</b>";

			if(ELECTIONTYPE=="2" || ELECTIONTYPE=="3" || ELECTIONTYPE=="4" || ELECTIONTYPE=="5" || ELECTIONTYPE=="6")
			{
				
				var radioElmt=document.getElementById("reportLevelRadio");
				var str="";
				str+='<input type="radio" name="reportLevel" value="State" onclick="displaySelectBox(this.value)"/>State';
				str+='<input type="radio" name="reportLevel" value="District" onclick="displaySelectBox(this.value)"/>District';
				str+='<input type="radio" name="reportLevel" value="Constituency" onclick="displaySelectBox(this.value)"/>Constituency';
				radioElmt.innerHTML=str;
			}
			else if(ELECTIONTYPE=="1")
			{
				var radioElmt=document.getElementById("reportLevelRadio");
				var str="";
				str+='<input type="radio" name="reportLevel" value="Country" onclick="displaySelectBox(this.value)"/>Country';
				str+='<input type="radio" name="reportLevel" value="State" onclick="displaySelectBox(this.value)"/>State';				
				str+='<input type="radio" name="reportLevel" value="Constituency" onclick="displaySelectBox(this.value)"/>Constituency';
				radioElmt.innerHTML=str;
			}
		}
		
		/* Function which passes the values to the ajaxCall() function which makes an ajax call to get
		 the list of values based on the selected values like:state,district,constituencies etc.,*/
		function getList(value,svalue)
		{			
			var jsObj=
			{
					reportLevel:value,
					selected:svalue
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);			
			var resultObj=ResultsajaxCall(rparam);		
			return resultObj;
		}

		/*Function which makes an ajax call to get the list 
		*/
		function ResultsajaxCall(rparam)
		{			
			var xmlHttp=getXmlHttpObj();			
			xmlHttp.open("post",RURL,false);
			xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xmlHttp.send(rparam);
			var jObj=eval('('+xmlHttp.responseText+')');		
			return jObj.namesList;			
		}

		
		/*	Function to display the selectbox with values(State/District/Constituency) based on
			 level of report selected	*/
		function displaySelectBox(value)
		{				
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
					STATELIST=true;	
					var lstr=getStateList();
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
					var lstr=getStateList();
					stateSelectDivElmt.innerHTML=lstr;							
				}
				var stateSelectElmt = document.getElementById("stateNameSelect");				
				var stateValue = stateSelectElmt.options[stateSelectElmt.selectedIndex].text;				
				var str='';
				str+='<select class="nameSelect" name="districtSelectName" id="DistrictNameSelect">';
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
					var lstr=getStateList();
					stateSelectDivElmt.innerHTML=lstr;							
				}
				var stateSelectElmt = document.getElementById("stateNameSelect");				
				var stateValue = stateSelectElmt.options[stateSelectElmt.selectedIndex].text;	
				var str='';							
				str+='<select class="nameSelect" name="constituencySelectName" id="ConstituencyNameSelect">';
				if(stateValue=="Select State")	
					str+='	<option>Select Constituency</option>';
				else
				{					
					var List=getList(REPORTLEVEL,stateValue);					
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
		function getStateList()
		{
			var List=getList(REPORTLEVEL,"null");
			var sstr='';
			
			sstr+='<select class="nameSelect" name="stateSelectName" id="stateNameSelect" onchange="getValuesList(this.id,this.options[this.selectedIndex].text)">';
			sstr+='	<option>Select State</option>';
			for(var l in List)
			{	
				sstr+='	<option value="' +List[l].id+'">'+List[l].name+'</option>';			
			}		
			sstr+='</select>';

			return sstr;
		}
		function getValuesList(id,value)
		{				
			var elmt=document.getElementById(REPORTLEVEL+"NameSelect");	
			if(REPORTLEVEL=="State")
			{			
				return;
			}
			if(value=="Select State")
			{
				var len=elmt.length;			
				for(i=len-1;i>=0;i--)		
					elmt.remove(i);
				
				var y=document.createElement('option');
				if(REPORTLEVEL=="District")
					y.text="Select District";
				if(REPORTLEVEL=="Constituency")
					y.text="Select Constituency";
				try
			  	{
					elmt.add(y,null); // standards compliant
			  	}
				catch(ex)
			  	{
					elmt.add(y); // IE only
			  	}
				return;
			}

						
			var List=getList(REPORTLEVEL,value);			
					
			var len=elmt.length;			
			for(i=len-1;i>=0;i--)
			{
				elmt.remove(i);
			}	
			
			if(!elmt)
				return;
			for (var l in List)
			{
				var y=document.createElement('option');
				y.value=List[l].id;
				y.text=List[l].name;
				
				try
			  	{
					elmt.add(y,null); // standards compliant
			  	}
				catch(ex)
			  	{
					elmt.add(y); // IE only
			  	}
			}
		}

		function validateData()
		{
			if(REPORTLEVEL=="Country")
				return true;
			var errorDivElmt=document.getElementById("resultsPageErrorDiv");
			var stateSelectElmt=document.getElementById("stateNameSelect");
			var stateVal=stateSelectElmt.options[stateSelectElmt.selectedIndex].text;

			errorDivElmt.innerHTML="";

			if(stateVal=="Select State" || stateVal=="")
			{
				errorDivElmt.innerHTML="*Select State";
				return false;
			}
			else
			{
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

		function setPartyName(){
		var selObj = document.getElementById("partyList");
		val = selObj.options[selObj.selectedIndex].text;
		document.getElementById("selectedPartyShortName").value=val;
		return true;
		}

		function setElectionType(electionType){
			document.getElementById("selectedElectionTypeName").value=electionType;
			
			var selObj = document.getElementById("partyList");
			val = selObj.options[selObj.selectedIndex].text;
			document.getElementById("selectedPartyShortName").value=val;

			return true;
		}
  --></script>
 </HEAD>

 <BODY>
 <s:form name="partyResultsForm" action="partyResultsAction" onsubmit="return validateData()" method="post">
 <input type="hidden" id="selectedPartyShortName" name="selectedPartyShortName">
 <input type="hidden" id="selectedElectionTypeName" name="selectedElectionTypeName">
<table width="800px" style="font-family: sans-serif;">
 <tr>
	<th colspan="3"><H3><U>Select Criteria</U></H3></th>
 </tr>
 <tr>
 	<td width="250px">
 		<div  class="tdLabelDiv">
 			<b>Party</b>
 		</div>
 	</td>
 	<td width="550px" colspan="2">
 		<div  class="tdDataDiv">
 				<select id="partyList" name="partySelectName" onchange='return setPartyName();'>
 				<c:forEach var="party" items="${partyList}">
 						<option value='<c:out value="${party.id}" />'> ${party.name}</option>
 				</c:forEach>
 				
 				</select>
 		</div>
 	</td>
 </tr>
  <tr>
 <td width="250px">
	<div  class="tdLabelDiv">
		<b>Select Election Type</b>
	</div>
 </td>
 <td width="550px" colspan="2">
	<div  class="tdDataDiv">
		<input type="radio" name="electionType" value="2" onclick="getReportLevel(this.value);setElectionType('Assembly');"/>Assembly
		<input type="radio" name="electionType" value="1" onclick="getReportLevel(this.value);setElectionType('Parliament');"/>Parliament	
		<input type="radio" name="electionType" value="3" onclick="getReportLevel(this.value);setElectionType('ZPTC');"/>ZPTC	
		<input type="radio" name="electionType" value="4" onclick="getReportLevel(this.value);setElectionType('MPTC');"/>MPTC	
		<input type="radio" name="electionType" value="5" onclick="getReportLevel(this.value);setElectionType('Municipal');"/>Municipal
		<input type="radio" name="electionType" value="6" onclick="getReportLevel(this.value);setElectionType('Corporation');"/>Corporation	
	</div>
 </td>
 </tr>
 <tr>
	<td width="250px">
		<div class="tdLabelDiv" id="reportLevelLabel"></div>
	</td>
	<td width="550px" colspan="2">
		<div class="tdDataDiv" id="reportLevelRadio"></div>
	</td>
 </tr>
 <tr>
	<td width="250px">
		<div class="tdLabelDiv" id="selectLabel"></div>
	</td>
	<td width="120px">
		<div class="tdStateDataDiv" id="tdStateDataDiv"></div>
	</td>
	<td width="300px">
		<div class="tdDistrictDataDiv" id="tdDistNConstDataDiv"></div>
	</td>

 </tr>
 <tr>
	<td colspan="3">
		<div id="resultsPageErrorDiv"></div>
	</td>
 </tr>
 <tr>
	<td colspan="3">
		<div id="resultsPageButtonDiv"></div>
	</td>
 </tr>
 </table>
 </s:form>

 </BODY>
</HTML>

