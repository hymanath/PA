<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML>
 <HEAD>
  <TITLE>Constituency Search</TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="">
  <META NAME="Description" CONTENT="">

	<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js" ></script>	
	<script type="text/javascript" src="js/yahoo/datasource-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/get-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/connection-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/animation-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/json-min.js" ></script>	  
	<script type="text/javascript" src="js/yahoo/autocomplete-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>

	<link href="styles/yuiStyles/autocomplete.css" rel="stylesheet" type="text/css" />

	<!-- YUI Dependency files (End) -->

	
 <script type="text/javascript">
 	//URL="http://localhost:8080/PartyAnalyst/action/ajaxSearchAction"; 	
 	URL="<%=request.getContextPath()%>/ajaxSearchAction.action?"
 	
function buildAutoSuggest(value)
{
	var datastore= value;		
	var txtDivElmt=document.getElementById("textFldDiv");
	if(navigator.appName=="Microsoft Internet Explorer")
	{		
		var txtstr='<input id="myInput" type="text" name="searchText" style="position:absolute;top:2px;"/>';
		txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;top:28px;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}
	else
	{
		var txtstr='<input id="myInput" type="text" name="searchText" style="padding: 3px 0px 2px 0px; font-size: 10px; font-family: arial;"/>';
		txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;top:22px;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}
	
	var dsLocalArray = new YAHOO.util.LocalDataSource(datastore); 
	var myAutoComp = new YAHOO.widget.AutoComplete("myInput","suggestDiv",dsLocalArray);
	myAutoComp.prehighlightClassName = "yui-ac-prehighlight"; 
    myAutoComp.useShadow = true;
	myAutoComp.useIFrame = true;
	myAutoComp.textboxKeyEvent.subscribe(validateRadio); 
}
function getXmlHttpObj()
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
function ajaxCall(param)
{
	
	
	var xmlHttp=getXmlHttpObj();
	
	xmlHttp.open("post",URL,true);
	xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	xmlHttp.send(param);
	
	xmlHttp.onreadystatechange = function()
	{
		if(xmlHttp.readyState==4 && xmlHttp.status == 200)
		{
			var jObj=eval('('+xmlHttp.responseText+')');
			
			buildAutoSuggest(jObj.namesList);		
		}
		
	}
}
 function getParser(val)
 {
	//var JSON;
	var errDivElmt=document.getElementById("errorDiv");
	errDivElmt.innerHTML="";

	SEARCHFOR=val;
	var jsObj=
	{
		searchCriteria:val
	}
	
	//var param = "task="+JSON.stringify(jsObj);
	var param ="task="+YAHOO.lang.JSON.stringify(jsObj); 
	
	ajaxCall(param);
 }

function validateTextField()
{
	var txtElmt=document.getElementById("myInput");
	var errDivElmt=document.getElementById("errorDiv");
	var cand=document.getElementById("candidateRadio");
	var cons=document.getElementById("constituencyRadio");

	if(!txtElmt)
		return false;
	if(!errDivElmt)
		return false;	
	
	if(!cand.checked && !cons.checked)
	{
		errDivElmt.innerHTML="Select search criteria... ";
		return false;
	}

	var value=txtElmt.value;		
	if(value=="")
	{		
		errDivElmt.innerHTML="* Search text cannot be empty";
		return false;	
	}
	else
	{
		errDivElmt.innerHTML="";
		return true;
	}
	
}
function getAutoComplete()
{
		var datastore="";
		var txtDivElmt=document.getElementById("textFldDiv");
		
		if(navigator.appName=="Microsoft Internet Explorer")
		{				
			var txtstr='<input id="myInput" type="text" name="searchText" onKeyup="validateRadio()" style="position:absolute;top:2px;"/>';
			txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;"></div>';
			txtDivElmt.innerHTML=txtstr;
		}
		else
		{
			var txtstr='<input id="myInput" type="text" name="searchText" onKeyup="validateRadio()"/>';
			txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;"></div>';
			txtDivElmt.innerHTML=txtstr;
		}

		var dsLocalArray = new YAHOO.util.LocalDataSource(datastore); 
		var myAutoComp = new YAHOO.widget.AutoComplete("myInput","suggestDiv",dsLocalArray);
		myAutoComp.prehighlightClassName = "yui-ac-prehighlight"; 
	    myAutoComp.useShadow = true;
		myAutoComp.useIFrame = true; 
}
function validateRadio()
{
	var cand=document.getElementById("candidateRadio");
	var cons=document.getElementById("constituencyRadio");
	
	var errElmt=document.getElementById("errorDiv");
	if(!cand.checked && !cons.checked)	
		errElmt.innerHTML="Select search criteria... ";		
	else
		errElmt.innerHTML="";
}
 </script>

 </HEAD>
 <BODY onLoad="getAutoComplete()">
<s:form name="SearchNameFormName" action="cncSearchResultsAction" onsubmit="return validateTextField()" method="post">

<table class="yui-skin-sam" style="width:360px;" border="0">
<tr>
    <td width="85px" style="padding-top: 3px; font-weight: bold;color:#FBAD2B;">
		Search Criteria
	</td>
	<td width="220px" colspan="2" align="left" style="font-weight: bold;">		
		<input id="candidateRadio" type="radio"  name="searchName" value="Candidate" onclick="getParser(this.value)"/>Candidate
		<input id="constituencyRadio" type="radio"  name="searchName" value="Constituency" onclick="getParser(this.value)"/>Constituency	
	</td>
</tr>
<tr>
	<td colspan="3">
		<div id="errorDiv" style="font-size: 10px; color: red;text-align:center;"></div>
	</td>
</tr>
<tr>	
	<td width="75px">
		<div id="labelDiv" style="padding-top: 2px; font-weight: bold;color:#FBAD2B;">Search Text</div>
	</td>
	<td width="140px" style="padding-bottom:23px;padding-left:10px;color:black;position:relative;z-index:1;">		
		<div id="textFldDiv"></div>				
	</td>
	<td width="50px">
		<div id="buttonDiv" style="margin-left: 3px;"><input type="submit" name="searchButton" value="Search" /></div>		
	</td>
	
</tr>
</table>
</s:form>
 </BODY>
</HTML>
