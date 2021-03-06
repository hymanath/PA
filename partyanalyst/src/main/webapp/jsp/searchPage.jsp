<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search  partyanalyst for Politician or Constituency</title>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
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
 function Data(){
$(".eventsheader").find(".span2").removeClass("span2")
$(".eventsheader").find(".span1").removeClass("span1")
$(".eventsheader").find(".span3").removeClass("span3")
$(".eventsheader").find(".span5").removeClass("span5") 
}
	 </script>
<style>
	.viewReportButtonSpan {
		background-image:url("images/icons/electionResultsAnalysisReport/2.png");
		cursor:pointer;
		height:30px;
		position:relative;
		width:80px;
		border:1px solid ; 
		color:#FFFFFF;
		font-size:12px;
		font-weight:bold;		
		position:relative;
		top:6px;
		margin-bottom: 5px;

	}



	.yui-skin-sam .yui-pg-container 
	{
	    display: block;
	    font-size: 13px;
	    margin: 4px 0;
	    text-align: center;
	    white-space: nowrap;
	}

	#contenttable{padding-bottom:50px}


	/* Big box with list of options */
	#ajax_listOfOptions{
		position:absolute;	/* Never change this one */
		width:185px;	/* Width of box */
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
#searchBlock
{
	background-color:#94A9C8;
	border:1px solid;
	color:#FFFFFF;
	font-weight:bold;
	margin-top:15px;
	padding:15px;
	width:450px;
}
.adData_head {
	border-bottom:1px solid #ADADAD;
	font-weight:bold;
	padding:5px;
	width: 210px;
	margin: 10px;
}
table.searchCriteria{
	border-collapse:collapse;
	color:#333333;
	font-family:verdana,arial,sans-serif;
	font-size:11px;
}
#tablerDetails {
	border-bottom:1px solid #DBDCDB;
	border-left:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	margin-bottom:10px;
	margin-top:10px;
	width: 475px;
}
#searchHead {
	background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
	color:#4B74C6;
	font-size:12px;
	font-weight:bold;
	height:20px;
	margin:0;
	padding:5px;
	text-align:left;
	width:460px;
	font-family:arial;
}
.head {
	background-color:#EBEBEB;
	border-bottom:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	padding-top: 3px;
	color:#163447;
}
.middle {
	background-color:#EAEAE9;
	border-bottom:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	color:#474B51;
	padding:5px;
	text-align:left;
	font-family:arial;
}
.edge {
	background-color:#EAEAE9;
	border-bottom:1px solid #DBDCDB;
	color:#474B51;
	padding:5px;
	font-family:arial;
}

</style>
</head>
<body>
	
	<div id="tablerDetails" style="margin-left:auto;margin-right:auto;float:none;">
	<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>
		<TD><H3 id="searchHead">Search for Politician or Constituency</H3></TD>
		<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>
		</TR>
	</TABLE>
	 <s:form theme="simple" name="SearchNameFormName" action="cncSearchResultsAction" onsubmit="return validateTextField()" method="post">
	<table cellpadding="0" cellspacing="0" width="475px">
	<tr>
		<th align="left" colspan="2" class="middle" style="font-family:arial;">
			Search Criteria
		</th>	
		<td align="left" class="middle">		
			<input id="candidateRadio" type="radio"  name="searchName" value="Candidate" onclick="resetConstTypeOptions()" checked="checked"/>&nbsp;&nbsp;Politician
		</td>
		<td align="left" class="edge">	
			<input id="constituencyRadio" type="radio"  name="searchName" value="Constituency" onclick="resetConstTypeOptions()"/>&nbsp;&nbsp;Constituency	
		</td>
	</tr>
	<tr id="row2">
		<th colspan="2" align="left" class="middle">
			Constituency Type
		</th>	
		<td align="left" class="middle">		
			<input id="mlaRadio" type="radio"  name="constType" value="MLA" onclick="resetStateSelect();" checked="checked"/>&nbsp;&nbsp;MLA
		</td>
		<td align="left" class="edge">	
			<input id="mpRadio" type="radio"  name="constType" value="MP" onclick="resetStateSelect();"/>&nbsp;&nbsp;MP	
		</td>		
	</tr>
	<tr>
		<th align="left" colspan="2" class="middle">		
			State	
		</th>	
		<td colspan="2" class="middle">
			<table>
			<tr>
			<td>
				<s:select list="{}" listKey="id" cssStyle="width:177px;" listValue="name" name="state" id="stateSelect" onchange="getParser(this.options[this.selectedIndex].value)"/>
			</td>
			<td>
				<img  id="ajaxLoaderimg" height="16" width="16" src="<%=request.getContextPath()%>/images/icons/ajaxLoader.gif" style="display:none;">
			</td>
			</tr>
			</table>
		</td>	
	</tr>		
	<tr id="row5">	
		<th  align="left" class="middle" colspan="2">
			<div id="labelDiv">Enter Search String</div>
		</th>
		<td colspan="2" class="edge">		
			<div id="textFldDiv" style="display:table;"></div>				
		</td>
	</tr>
	<tr>
	
	    <td style="text-align:center" class="middle">
			<!--<a class="viewReportButtonSpan" style = "padding: 9px 10px 6px 10px; -->
			<a class="btn btn-inverse" href="searchInPartyAnalyst.action">
               User Google Custom Search
            </a>
		</td>
		<td colspan="4" style="text-align:center" class="middle">
			<!--<input type="submit" name="searchButton" class="viewReportButtonSpan" value="Search"/>	-->
			<input type="submit" name="searchButton" class="btn btn-inverse" value="Search"/>	
		</td>
		
	</tr>		
	</table>
	<div id="errorDiv" style="font-size: 10px; color: red;text-align:center;font-weight:bold;"></div>
	

</s:form>
	</div>
	<script type="text/javascript">
getStates(1,'statesInCountry','siteSearch','stateSelect','current','null');
Data();
</script>
</body>
</html>