<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
 <HEAD>
	<TITLE>Add Rebel Candidates to Party Page</TITLE>

	<!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<!-- OPTIONAL: JSON (enables JSON validation) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
  	<!-- Dependencies --> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript">	
		
	
 	function callAjax(param, url){
 		var myResults;
 		url = url + param;
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 
								processResponse(param, myResults);
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
 	
    function processResponse(param, response){
		if(param.substring(0, 4) == "type"){
	    	fillDropDown(document.getElementById("stateList"), response.states);
		 	fillDropDown(document.getElementById("yearList"), response.years);
		 	fillDropDown(document.getElementById("partyList"), response.parties);
		} if(param.substring(0, 8) == "alliance"){
			if(response == true) {
				document.getElementById("allianceRow").style.display="table-row";
			} else {
				document.getElementById("allianceRow").checked = false;
				document.getElementById("allianceRow").style.display="none";
			}
		}else {
	 		fillDropDown(document.getElementById("districtList"), response.districts);			
	 		document.getElementById("districtList").disabled= false;  	 			 		
		}
    }
    
    function fillDropDown(selectbox, data){
          selectbox.options.length = 0;
          for( var counter = 0 ; counter < data.length ; counter++ ) {
        	  if(data[counter].id == undefined){
                  addOption(selectbox, data[counter], data[counter]);
              } else {
          		  addOption(selectbox, data[counter].name, data[counter].id);
              }
     	}
    }
    
 	function addOption(selectbox,text,value){
 			var optn = document.createElement("OPTION");
 			optn.text = text;
 			optn.value = value;
 			selectbox.options.add(optn);
 	}

 	function doAjax(param){
 		var url = "<%=request.getContextPath()%>/partyPerformanceAjax.action?";
 		callAjax("type="+param, url);
 	}

 	function getDistricts(level){
 	 	if(level == 2){
	 	 	var index = document.getElementById("stateList").selectedIndex;
	 	 	var stateId = document.getElementById("stateList").options[index].value;
	 	 	var url = "<%=request.getContextPath()%>/partyPerformanceAjax.action?";
			callAjax("stateId="+stateId, url);
 	 	} else {
 	 		document.getElementById("districtList").disabled= true;  
 	 	}
 	}

 	function fetchDistricts(){
 	 	var reportLevels = document.getElementsByName("1");
 	 	for(var i=0; i < reportLevels.length; i++){
 	 	 	if(reportLevels[i].checked){
 	 	 		getDistricts(i+1);
 	 	 	}
 	 	}
 	}

 	function hasAllianceParties(){
 		var index = document.getElementById("partyList").selectedIndex;
 	 	var partyId = document.getElementById("partyList").options[index].value;
 	 	index = document.getElementById("yearList").selectedIndex;
 	 	var year = document.getElementById("yearList").options[index].value;
 	 	var url = "<%=request.getContextPath()%>/partyPerformanceAllianceAjax.action?";
 	 	callAjax("allianceWith="+partyId+"&year="+year, url);
 	}

 	function ajaxCall2(param)
	{		
		var url2 = "<%=request.getContextPath()%>/ajaxSearchAction.action?"; 
		var xmlHttp=getXmlHttpObj2();		
		xmlHttp.open("post",url2,true);
		xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xmlHttp.send(param);
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState==4 && xmlHttp.status == 200)
			{
				var jObj=eval('('+xmlHttp.responseText+')');				
				buildAutoSuggest2(jObj.namesList);	
				var candidateSelect = document.getElementById("candidate");
				fillDropDown(candidateSelect, jObj.candidatesList);	
			}			
		}
	} 
 	function getXmlHttpObj2()
 	{	
 		var xmlHttp;
 		// Firefox, Opera 8.0+, Safari
 		try
 		{	
 			xmlHttp=new XMLHttpRequest();
 		}
 		// Internet Explorer
 		catch (e)
 		{
 			try
 			{
 				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
 				
 			}
 			catch (e)
 			{
 				try
 				{
 					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
 					
 				}
 				catch (e){return null;}
 			}
 		}
 		return xmlHttp;	
 	}
function buildAutoSuggest2(value)
{
	var datastore= value;
	
	var txtDivElmt=document.getElementById("candFldDiv");
	/*if(navigator.appName=="Microsoft Internet Explorer")
	{		
		var txtstr='<input id="myInput2" type="text" name="searchText" style="padding-bottom:23px;padding-left:10px;color:black;position:relative;z-index:1;"/>';
		txtstr+='<div id="suggestDiv2" style="padding-bottom:23px;padding-left:10px;color:black;position:relative;z-index:1;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}
	else
	{
		var txtstr='<input id="myInput2" type="text" name="searchText" style="padding-bottom:23px;padding-left:10px;color:black;position:relative;z-index:1;"/>';
		txtstr+='<div id="suggestDiv2" style="padding-bottom:23px;padding-left:10px;color:black;position:relative;z-index:1;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}*/
	var dsLocalArray = new YAHOO.util.LocalDataSource(datastore); 
	var myAutoComp = new YAHOO.widget.AutoComplete("myInput2","myContainer",dsLocalArray);
	myAutoComp.prehighlightClassName = "yui-ac-prehighlight"; 
    myAutoComp.useShadow = true;
	myAutoComp.useIFrame = true;
	
	//myAutoComp.textboxKeyEvent.subscribe(getCandidates); 
}
function getCandidates()
 {
	var jsObj=
	{
		searchCriteria:"Candidate"
	}
	
	//var param = "task="+JSON.stringify(jsObj);
	var param ="task="+YAHOO.lang.JSON.stringify(jsObj); 
	ajaxCall2(param);
 }
	
function getAutoCandidate()
{
		var datastore="";
		var txtDivElmt=document.getElementById("candFldDiv");
		/*if(navigator.appName=="Microsoft Internet Explorer")
		{				
			var txtstr='<input id="myInput2" type="text" name="searchText" onKeyup="getCandidates();" style="position:absolute;top:2px;"/>';
			txtstr+='<div id="suggestDiv2" style="position:absolute;z-index:50000000;font-size:10px;"></div>';
			txtDivElmt.innerHTML=txtstr;
		}
		else
		{
			var txtstr='<input id="myInput2" type="text" name="searchText" onKeyup="getCandidates();"  style="padding: 3px 0px 2px 0px; font-size: 10px; font-family: arial;"/>';
			txtstr+='<div id="suggestDiv2" style="position:absolute;z-index:50000000;font-size:10px;"></div>';
			txtDivElmt.innerHTML=txtstr;
		}*/
		
		var dsLocalArray = new YAHOO.util.LocalDataSource(datastore); 
		var myAutoComp = new YAHOO.widget.AutoComplete("myInput2","myContainer",dsLocalArray);
		myAutoComp.prehighlightClassName = "yui-ac-prehighlight"; 
	   
		myAutoComp.alwaysShowContainer = true;
		myAutoComp.allowBrowserAutocomplete = false;
		myAutoComp.autoSnapContainer = true;
}

function addRebel()
{
	
	var index = document.getElementById("myInput2").selectedIndex;
	 	var candidateId = document.getElementById("myInput2").options[index].value;
	 	var candidateName = document.getElementById("myInput2").options[index].text;
	 	alert(candidateName);
	/* var data=
	 		{
	 			"id":candidateId,
	 			"name":candidateName
	 		}
	fillDropDown(document.getElementById("rebelCandidates"), data);*/
}
	</script>
		<script type="text/javascript" src="js/cncSearch.js"></script>
	<link href="styles/partyPerformance.css" rel="stylesheet" type="text/css" /> 
	
</head> 
<body >
<s:form name="addRebels" action="#" onsubmit="javascript:{}" method="post">
<s:hidden name="country" value="1" id="country"/>
<table class="partyPerformanceCriteriaTable">
	<tr>
		<th colspan="2">
			<span style="margin: 0px; text-align: center;">Party Performance</span>
		</th>
	</tr>
	<tr>
		<th>Election Type</th>
		<td>
			<input type="radio" name="electionType" value="2" checked="checked" onclick="doAjax(this.value);"/>Assembly
			<input type="radio" name="electionType" value="1" onclick="doAjax(this.value);"/>Parliament
		</td>
	</tr>
	<tr>
		<th> State</th>
		<td>
			<s:select theme="simple" label="State" name="state" id="stateList" list="states" listKey="id" listValue="name" />
		</td>
	</tr>
	<tr>
		<th> Year</th>
		<td><s:select theme="simple" label="Year" name="year" id="yearList" list="years" /></td>
	</tr>
	<tr>
		<th>Party</th>
		<td><s:select theme="simple"  label="Party" name="party" id="partyList" list="parties" listKey="id" listValue="name" /></td>
	</tr>
	<tr><th></th>
		<td>
			<s:select theme="simple" id="candidate" name="candidate"  list="candidates" cssStyle="width:100px" />
		</td>
	</tr>
	<tr>
		<th>Candidate</th>
		<td><div id="myAutoComplete">   
       <input id="myInput2" type="text" onkeyup="getCandidates()"> <div id="myContainer" class="yui-ac-container" ></div>   
         <button type="button" name="add" onclick="addRebel()">Add</button> 	
    
 </div>   
</td>
	</tr>
	<tr>
		<th>Rebels</th>
		<td><s:select cssStyle="width:100px" theme="simple" multiple="true"  name="rebels" list="rebelCandidates" /></td>
	</tr>
</table>
</s:form>
</body>
</html> 