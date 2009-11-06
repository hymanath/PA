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

	<script type="text/javascript" src="js/cncSearch.js"></script>
	 <script type="text/javascript"> 		
		URL="<%=request.getContextPath()%>/ajaxSearchAction.action?" 	
	
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
	 </script>
 </HEAD>
 <BODY onLoad="getAutoComplete()">
 <div style="float: right; margin-top: 18px;border:2px solid #7C7979;height:auto;">
<s:form name="SearchNameFormName" action="cncSearchResultsAction" onsubmit="return validateTextField()" method="post">


	<table class="yui-skin-sam" style="width:310px;" border="0">
	<tr>
		<td width="80px" style="padding-top: 3px;color:#FBAD2B;">
			Search Criteria
		</td>
		<td width="160px" align="left">		
			<input id="candidateRadio" type="radio"  name="searchName" value="Candidate" onclick="getParser(this.value)"/>Candidate
			<input id="constituencyRadio" type="radio"  name="searchName" value="Constituency" onclick="getParser(this.value)"/>Constituency	
		</td>
		<td width="40px">
			<img  id="ajaxLoaderimg" height="16" width="16" src="<%=request.getContextPath()%>/images/icons/ajaxLoader.gif" style="display:none;">
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<div id="errorDiv" style="font-size: 10px; color: red;text-align:center;"></div>
		</td>
	</tr>
	<tr>	
		<td width="80px">
			<div id="labelDiv" style="padding-top: 2px;color:#FBAD2B;">Search Text</div>
		</td>
		<td width="160px" style="padding-bottom:23px;padding-left:10px;color:black;position:relative;z-index:1;">		
			<div id="textFldDiv"></div>				
		</td>
		<td width="40px">
			<div id="buttonDiv" style="margin-left: 3px;"><input type="submit" name="searchButton" value="Search" /></div>		
		</td>
		
	</tr>
	</table>

</s:form>
</div>
 </BODY>
</HTML>
