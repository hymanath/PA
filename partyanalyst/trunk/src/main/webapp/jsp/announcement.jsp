<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
   <TITLE>
  <c:if test="${sessionScope.windowTask == 'new' || sessionScope.windowTask == 'NEW'}">Add </c:if>
  <c:if test="${sessionScope.windowTask == 'update_existing'}">Update </c:if>
  Announcements
  </TITLE>
  
	<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	

	<!-- YUI Skin Sam -->

	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
	<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
	<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>

	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>
	
	<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">


<!-- JQuery files (End) -->
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>	
<link rel="stylesheet" type="text/css" href="styles/jQuery/jquery.datepick.css">

<script type="text/javascript" src="js/jQuery/jquery.datepick.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.datepick-en-GB.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"></script>

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>

<script type="text/javascript">
var windowTask = '${sessionScope.windowTask}';

function validateForm()
{
	var constiSelectEle  = document.getElementById("constituency");
	var constSelectElVal = constiSelectEle.options[constiSelectEle.selectedIndex].value ;
	var title			 = document.getElementById("title").value;
	var announcement	 = document.getElementById("announcement").value;
	var fromDate		 = document.getElementById("fromDateField").value;
	var toDate			 = document.getElementById("toDateField").value;
	var errMsgDivEle	 = document.getElementById("errorMsgDivId");
	var str = '';
	var flag = false;
	
	var fromDateS = Date.parse(fromDate);
	var toDateS = Date.parse(toDate);
		
	if(constSelectElVal == 0)
	{
		str += 'Constituency is Required<BR>';
		flag = true;
	}
	if(title.trim().length == 0)
	{
		str += 'Title is Required<BR>';
		flag = true;
	}
	
	if(announcement.trim().length == 0)
	{
		str += 'Announcement is Required<BR>';
		flag = true;
	}
		
	if(fromDate.length == 0)
	{
		str += 'From Date is Required<BR>';
		flag = true;
	}
	if(toDate.length == 0)
	{
		str += 'To Date is Required<BR>';
		flag = true;
	}
	
	if(fromDate.length > 0 && toDate.length > 0 && (toDateS < fromDateS))
	{
		str += 'To Date Must be Greater than From Date<BR>';
		flag = true;
	}
	
	errMsgDivEle.innerHTML = str;
	if(flag)
		return false;
	
	return true;
}

function getAllConstituenciesInState()
{
	var stateSelectEl = document.getElementById("stateList_c");
	var stateId = stateSelectEl.options[stateSelectEl.selectedIndex].value;
	getAllConstituenciesInStateByType(2,stateId,'constituency');
}

function showStateSelect()
{
	document.getElementById("stateSelectDiv").style.display = 'block';
}

function hideStateSelect()
{
	document.getElementById("stateSelectDiv").style.display = 'none';
}

function executeOnLoad()
{
	if(windowTask =='update_existing')
	{
      if('${announcementVO.type}' == 'Parliament')
	  { 
		 document.getElementById("stateSelectDiv").style.display = 'none';
         document.getElementById("p_radio").checked = true;		
	  }
	 
	  if('${announcementVO.type}' == 'Assembly')
	  {  
	     document.getElementById("stateSelectDiv").style.display = 'block';
         document.getElementById("a_radio").checked = true;
	  }
   }
}


function callAjax(jsObj,url)
{
	var myResults;	
	
	var callback = {			
	
	success : function( o )
	{
		try {
			if(o.responseText)
				myResults =YAHOO.lang.JSON.parse(o.responseText);	
			
			if(jsObj.task == "updateannouncement")
			{
				
			}
			else if(jsObj.task == "getConstituencies" || 
				jsObj.task == "getAllParliamentConstituencies")
			{
		
		clearOptionsListForSelectElmtId(jsObj.elmtId);
		createOptionsForSelectElmtIdWithSelectOption(jsObj.elmtId,myResults);
			}

		}catch (e) {   		
		   	//alert("Invalid JSON result" + e);   
		}  
    },
    scope : this,
    failure : function( o ) {
     	//alert( "Failed to load result" + o.status + " " + o.statusText);
         }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);	
}

function limitText(limitField, limitCount, limitNum)
{		
	var limitFieldElmt = document.getElementById(limitField);
	var limitCountElmt = document.getElementById(limitCount);

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
	}
}

function getAllConstituenciesInStateByType(electionType, stateId, element)
{
	clearOptionsListForSelectElmtId(element);
	showBusyImgWithId(element);
	var jsObj=
	{				
			electionTypeId: electionType,
			stateId: stateId,
			task: "getConstituencies",
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllConstituenciesInState.action?"+rparam;						
callAjax(jsObj,url);
}

function createOptionsForSelectElmtIdWithSelectOption(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
}

function clearOptionsListForSelectElmtId(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}

function getAllParliamentConstInCountry(element)
{
	var jsObj=
	{				
			task: "getAllParliamentConstituencies",
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllParliamentConstInCountry.action?"+rparam;						
callAjax(jsObj,url);
}

</script>
<style type="text/css">

.announcementHeader 
{
	background-image:url("images/icons/cadreReport/bg_center.png");
	background-repeat:repeat-x;
	color:#FFFFFF;
	font-size:14px;
	font-weight:bold;
	height:24px;
	padding-top:1px;
	text-align:center;
	width:185px;
}

.requiredFont {
	color: red;
	margin-left: 5px;
}

.bodyStyle {
  background-color:#F2F2F2;
  font-family:verdana;
  font-size:12px;
}

.annSelect {
	padding:2px;
	width:175px;
}

.tdClass
{
	width : 150px;
}

.button {
	background-attachment:scroll;
	background-color:#335291;
	background-image:none;
	background-position:0 0;
	background-repeat:repeat;
	color:#FFFFFF;
	float:left;
	width:50px;
	font-weight:bold;
	height:28px;
}
.errorDiv{
	color:red;
	margin-left:10px;
	margin-bottom : 5px;
}

</style>

</head>
<body class="bodyStyle">

<CENTER>
<TABLE border="0" cellpadding="0" cellspacing="0" style="margin-top:15px;margin-bottom:10px;">
	<TR>
		<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
		<TD>
			<div class="announcementHeader"><span style="margin-top:2px;">
			<c:if test="${sessionScope.windowTask == 'new'}">Add </c:if>
			<c:if test="${sessionScope.windowTask == 'update_existing'}">Update </c:if> 
			Announcement</span></div>
		</TD>
		<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>
	</TR>
</TABLE>
</CENTER>

<DIV style="margin-left:10px;"><P>Fields marked with <font class="requiredFont"> * </font> are mandatory</P></DIV>

<s:form name="announcementForm" action="announcementSaveAction" onsubmit="return validateForm()" method="post">
<DIV id="errorMsgDivId" class="errorDiv"></DIV>

<table>
<tr>
	<td colspan="2">
		<div style="color: red;">
			<s:actionerror />
			<s:fielderror />
		</div>
	</td>
</tr>
</table>	

<c:if test="${resultVO != null && resultVO.resultStatus.resultCode == 0}">
	<DIV id="alertMessage" style="color:green;font-weight:bold;margin:5px;">Announcement 
	<c:if test="${resultVO.type == 'new' || resultVO.type == 'NEW'}">	Added </c:if>
	<c:if test="${resultVO.type == 'update_existing'}"> Updated </c:if>
	Successfully </DIV>
</c:if>

<DIV style="width:480px;">
<FIELDSET style="margin-left:10px;">
	<LEGEND><b>Announcement Details</b></LEGEND>
<Table>
	<TR>
		<TD><input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="getAllConstituenciesInState();showStateSelect()"/>Assembly</TD>
	
		<TD><input type="radio" name="a_radio" id="p_radio" onclick="getAllParliamentConstInCountry('constituency');hideStateSelect()"/>Parliament</TD>
	</TR>
</Table>

<DIV id="stateSelectDiv">
<Table>            
	<TR>
		<TD class="tdClass">Select State <font class="requiredFont"> * </font></TD>
		<TD><s:select theme="simple" name="state" cssClass="annSelect" id="stateList_c" list="#session.statesList" listKey="id" listValue="name" onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/></TD>
    </TR>
</Table>
</DIV>

<Table>
	<TR>
        <TD class="tdClass">Select Constituency <font class="requiredFont"> * </font></TD>
		<TD><s:select theme="simple" cssClass="annSelect" name="constituency" id="constituency" list="#session.constituenciesList" listKey="id" listValue="name"/></TD>
    </TR>     
 </Table>

 <Table style="margin-top:15px;">
	<TR>
		<TD class="tdClass">Title <font class="requiredFont"> * </font></TD>
		<TD><s:textfield id="title" name="title" theme="simple" maxlength="100" size="39" /></TD>
    </TR>   
	
    <TR>
		<TD class="tdClass">Announcement <font class="requiredFont"> * </font></TD>
        <TD><s:textarea cols="30" rows="3" id="announcement" onkeyup="limitText('announcement','maxcount',500)"  name="message" theme="simple"/>
						
		<div id="limitDiv">
		<table style="width:100%;">
		<tr>
			<td><div id="remainChars"><span id="maxcount">500 </span> <span>chars remaining..</span></div></td>
		</tr></table>
		</div></td>
	</TR>
</Table>

<Table style="margin-top:15px;">
    <TR>
		<TD class="tdClass">From Date <font class="requiredFont"> * </font> </TD>       
        <TD><s:textfield id="fromDateField"  name="fromDate" readonly="true" onfocus="showCalendar(this.id)" theme="simple" size="20"/></TD>
    </TR>
	 
    <TR>
		<TD class="tdClass">To Date <font class="requiredFont"> * </font></TD>
		<TD><s:textfield id="toDateField"  name="toDate" size="20"  readonly="true" onfocus="showCalendar(this.id)" theme="simple"/></TD>
    </TR>
</Table>


<Table style="margin-top:10px;">
<tr>
	<td><div style="margin-left:154px;"><s:submit name="Save" value="Save" cssClass="button" theme="simple"></s:submit></div></td>
	<td><input type="button" value="Exit" class="button" onclick="window.close()"/></td>
</tr>
</Table>

<input type="hidden" id="windowTaskId" name="windowTask" value="${sessionScope.windowTask}"/>
<input type="hidden" id="announcementId" name="announcementId" value="${announcementId}"/>
</s:form>	     		

</FIELDSET>
</Div>
<script>
executeOnLoad();
$(function()
{
	$("#fromDateField").datepicker();
	$("#toDateField").datepicker();
});

if(windowTask == 'new')
{
	getAllConstituenciesInStateByType(2,1,'constituency');
}
</script>
</body>
</html>