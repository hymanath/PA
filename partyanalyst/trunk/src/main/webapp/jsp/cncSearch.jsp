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
	
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
	<script type="text/javascript" src="js/cncSearch.js"></script>	
	<script type="text/javascript" src="js/ajaxSearch/ajax.js" ></script>
	<script type="text/javascript" src="js/ajaxSearch/ajax-dynamic-list.js" ></script>


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
					buildAutoSuggest(jObj);		
				}			
			}
		} 
	 </script>
	 <style>
	 .yui-skin-sam .yui-ac-input {
		width:175px;
		position:relative;
	}
	.searchBtn
	{
		width:25px;
		background-image:url(images/usergroups/search.gif);
	}
	.viewReportButtonSpan {
		background-image:url("images/icons/constituencyManagement/btn_cm.png");
		cursor:pointer;
		height:30px;
		position:relative;
		width:80px;
		border-right:0px;
		border-bottom:0px;
		color:#FFFFFF;
		font-size:12px;
		font-weight:bold;		
		position:relative;
		top:6px;

	}
	/* Big box with list of options */
	#ajax_listOfOptions{
		position:absolute;	/* Never change this one */
		width:175px;	/* Width of box */
		height:200px;	/* Height of box */
		overflow:auto;	/* Scrolling features */
		border:1px solid #317082;	/* Dark green border */
		background-color:#FFF;	/* White background color */
		text-align:left;
		font-size:0.9em;
		z-index:100;
	}
	#ajax_listOfOptions div{	/* General rule for both .optionDiv and .optionDivSelected */
		margin:1px;		
		padding:1px;
		cursor:pointer;
		font-size:0.9em;
	}
	#ajax_listOfOptions .optionDiv{	/* Div for each item in list */
		
	}
	#ajax_listOfOptions .optionDivSelected{ /* Selected item in the list */
		background-color:#317082;
		color:#FFF;
	}
	#ajax_listOfOptions_iframe{
		background-color:#F00;
		position:absolute;
		z-index:5;
	}
	 </style>
 </HEAD>
 <BODY>
 <div>
<s:form theme="simple" name="SearchNameFormName" action="cncSearchResultsAction" onsubmit="return validateTextField()" method="post">


	<table class="yui-skin-sam">
	<tr>
		<th align="left" colspan="2" style="padding-top: 3px;color:#163447;">
			Select	Search Criteria
		</th>
	</tr>
	<tr>	
		<th align="left" style="color:#163447;">		
			<input id="candidateRadio" type="radio"  name="searchName" value="Candidate" onclick="resetConstTypeOptions()" checked="checked"/>Politician
		</th>
		<th align="left" style="color:#163447;">	
			<input id="constituencyRadio" type="radio"  name="searchName" value="Constituency" onclick="resetConstTypeOptions()"/>Constituency	
		</th>
	</tr>
	<tr id="row1">
		<th colspan="2" align="left" style="padding-top: 3px;color:#163447;">
			Select Constituency Type
		</th>
	</tr>
	<tr id="row2">	
		<th align="left" style="color:#163447;">		
			<input id="mlaRadio" type="radio"  name="constType" value="MLA" onclick="resetStateSelect()" checked="checked"/>MLA
		</th>
		<th align="left" style="color:#163447;">	
			<input id="mpRadio" type="radio"  name="constType" value="MP" onclick="resetStateSelect()"/>MP	
		</th>		
	</tr>
	<tr id="row3">	
		<th align="left" colspan="2" style="color:#163447;">		
			Select State	
		</th>
	</tr>
	<tr>	
		<td colspan="2">
			<table>
			<tr>
			<td>
				<s:select list="{}" listKey="id" cssStyle="width:177px;" listValue="name" name="state" id="stateSelect" onchange="getAutoComplete();getParser(this.options[this.selectedIndex].value)"/>
			</td>
			<td>
				<img  id="ajaxLoaderimg" height="16" width="16" src="<%=request.getContextPath()%>/images/icons/ajaxLoader.gif" style="display:none;">
			</td>
			</tr>
			</table>
		</td>	
	</tr>		
	<tr id="row4">	
		<th colspan="2" align="left">
			<div id="labelDiv" style="padding-top: 2px;color:#163447;">Enter Search String</div>
		</th>
	</tr>
	<tr id="row5">	
		<td colspan="2" style="color:black;position:relative;z-index:1;">		
			<div id="textFldDiv"></div>				
		</td>
	</tr>		
	</table>
	<div id="errorDiv" style="font-size: 10px; color: red;text-align:center;"></div>
	<div id="buttonDiv" style="text-align:right;"><input type="submit" name="searchButton" class="viewReportButtonSpan" value="Search"/></div>

</s:form>
</div>
<script type="text/javascript">


getStates(1,'statesInCountry','siteSearch','stateSelect','current','null');

</script>
 </BODY>
</HTML>
