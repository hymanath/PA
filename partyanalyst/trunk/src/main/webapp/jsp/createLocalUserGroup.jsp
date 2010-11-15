<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User Group</title>
<SCRIPT type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>
<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	
<style type="text/css">

  .formTableStyle {
	font-family:arial;
	font-size:14px;
	font-weight:bold;
  }

  .labelStyle {
	font-family:arial;
	font-size:22px;
  }
  .btn_crtCategory {
	  margin:5px 0 8px;
	  width:120px;
  }
   .btn_sbtCategory {
	  margin:5px 0 8px;
	  width:50px;
  }

</style>
<script type="text/javascript">

var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String problemLabel = rb.getString("problem");		
		
	  %> }

function showHideCategoryData(status)
{
	var catgryNameId = document.getElementById("catgrNameRow");
	var catgryDescId = document.getElementById("catgrDescRow");

    if(catgryNameId)
	{
		if(status == 1)
		 catgryNameId.style.display = '';
		if(status == 0)
         catgryNameId.style.display = 'none';
		var categoryElmt = document.getElementById("categoryName");
		categoryElmt.focus();
	}
    if(catgryDescId)
	{
		if(status == 1)
		  catgryDescId.style.display = '';
		if(status == 0)
		  catgryDescId.style.display = 'none';
	}

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
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+" ";
	}
}	
</script>
</head>

<body onload="" class="bodyStyle">

<s:form action="" method="GET" theme="simple" name="form" onsubmit="return validateClientSide()">

 <div id="wrapper" style="width:550px;">
   <table cellspacing="0" cellpadding="0" border="0" align="center" width="100%">
    <tbody>
	 
	 <tr height="45" valign="bottom">
	   <td>
	     <br>
		  <img hspace="0" title="partyanalyst.com" src="images/icons/homePage_new/logo.png">
	   </td>
	   <td></td>
	 </tr>
	 
	 <tr>
	   <td height="10"></td>
	 </tr>

	 <tr>
	   <td height="3" bgcolor="#e5e5e5" colspan="2"></td>
	 </tr>

	 <tr>
	   <td height="10"></td>
	 </tr>

    </tbody>
   </table>

   <table class="formTableStyle" height="64" cellpadding="0" cellspacing="0" border="0" align="center" width="660">
    <tbody>

	  <tr>
	    <td class="labelStyle" colspan="5">Create a Group</td>
	  </tr>
	  <tr>
	    <td height="17"></td>
	  </tr>
	  <tr>
	    <td width="100">Select Group Category</td>
		<td width="14">
		  <b> : </b>
		</td>
		<td width="185">
		  <s:select id="categorysId" name="categorys" cssStyle="width:150px;" list="{}" listKey="id" listValue="name"></s:select>
		</td>
	  </tr>
	  <tr>
	    <td width="100"></td>
		<td width="14">
		  <b> </b>
		</td>
		<td width="185">
		 <input class="btn_crtCategory" type="button" onclick="showHideCategoryData(1)" value="Create Category">
		</td>
	  </tr>
	  <tr id="catgrNameRow" style="display:none;">
	    <td width="100"></td>
		<td width="14">
		  <b> </b>
		</td>
		<td width="185">
		 Category &nbsp;&nbsp;&nbsp;:  <input id="categoryName" type="text" maxlength="61" style="width:150px;" value="" name="catgryName">
		</td>
	  </tr>
	  <tr id="catgrDescRow" style="display:none;">
	    <td width="100"></td>
		<td width="14">
		  <b> </b>
		</td>
		<td width="185">
		Desciption :  <input id="categoryDesc" type="text" maxlength="61" style="width:150px;" value="" name="catgryDesc">
		  <input class="btn_sbtCategory" type="button" onclick="" value="Create">
		  <a href="javascript:{}" onclick="showHideCategoryData(0)">Hide</a>
		</td>
	  </tr>

	  <tr>
	    <td width="100">Enter Group Name</td>
		<td width="14">
		  <b> : </b>
		</td>
		<td width="185">
		  <input id="localGroupName" type="text" maxlength="61" style="width:150px;" value="" name="localGroupName">
		</td>
	  </tr>

      <tr><td>&nbsp;</td></tr>
	   <tr>
	    <td width="100">Enter Group Description</td>
		<td width="14">
		  <b> : </b>
		</td>
		<td width="185">
		  <textarea id="localGroupDesc" cols="10" rows="5" style="width:150px;" name="localGroupDesc" onkeyup='limitText("localGroupDesc","maxcount",200)'>
		  </textarea>
		</td>
	  </tr>

      <tr><td>&nbsp;</td></tr>
	  <tr>
	    <td width="100">Select Group Scope</td>
		<td width="14">
		  <b> : </b>
		</td>
		<td width="185">
		  <s:select id="groupScopeId" name="groupScope" cssStyle="width:150px;" list="{}" listKey="id" listValue="name"></s:select>
		</td>
	  </tr>

	</tbody>
   </table>
 </div>
</s:form>

<script type="text/javascript">

</script>

</body>
</html>