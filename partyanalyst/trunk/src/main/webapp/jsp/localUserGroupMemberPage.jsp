<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Local User Group Member</title>
<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js">
	</script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
    <link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">

<!-- YUI Dependency files (End) -->

<script type="text/javascript" src="js/jQuery/development-bundle/jquery-1.4.2.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

<script type="text/javascript">
	
	function showAddDesignation()
	{
		var elmt = document.getElementById("designationDiv");
		if(!elmt)
			return;
		
		var str = '';
		str += '<table width="100%" class="localGroupMemberTable">';
		str += '<tr>';
		str += '<th> <font color="red"> * </font> Designation </th>';
		str += '<td><input id="newDesigName" type="text" name="newDesigName"></input> </td>';
		str += '</tr>';
		str += '<tr>';
		str += '<th>Description </th>';
		str += '<td><textarea id="newDesigDesc"></textarea> </td>';
		str += '</tr>';

		str += '<tr>';	
		str += '<td colspan="2" align="left"><div id="addDesignationErrorDiv"></div></td>';
		str += '</tr>';		

		str += '<tr>';		
		str += '<td colspan="2" style="text-align:right">';		
		str += '	<input type="button" value="Add Designation" onclick="addDesigantionAjaxCall()"></input>';
		str += '</td>';
		str += '</tr>';
		str += '</table>';

		elmt.innerHTML = str;

		$("#designationDiv").slideToggle();
	}

	function addDesigantionAjaxCall()
	{
		var errorDiv = document.getElementById("addDesignationErrorDiv");

		var categoryElmt = document.getElementById("groupCategory");
		var categoryId = '';
		if(categoryElmt)
			categoryId = categoryElmt.options[categoryElmt.selectedIndex].value;

		var newDesigNameElmt = document.getElementById("newDesigName");
		var newDesigNamevalue = newDesigNameElmt.value;

		var newDesigDescElmt = document.getElementById("newDesigDesc");
		var newDesigDescvalue = newDesigDescElmt.value;

		if(newDesigNamevalue == "" && errorDiv)
		{
			errorDiv.innerHTML = '<font style="font-size:10px;color:red"> Enter Designation Title </font>';
			return;
		}
		else if(categoryId == '0')
		{
			errorDiv.innerHTML = '<font style="font-size:10px;color:red"> Select Category Type </font>';
			return;
		}
		else
		{
			errorDiv.innerHTML = '';
		}

		var jsObj = {
						categoryId:categoryId,
						desigName:newDesigNamevalue,
						desigDesc:newDesigDescvalue,
						task:'addNewDesignation'
					};

		var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "addNewDesignationAction.action?"+param;

		callAjax(jsObj,url);
	}

	function callAjax(jsObj,url)
	{
		var myResults;
					
		var callback = {			
					   success : function( o ) 
						  {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
										
									if(jsObj.task == "addNewDesignation")
									{
										clearOptionsListForSelectElmtId("designations");
										createOptionsForSelectElmtId("designations",myResults);
										$("#designationDiv").slideToggle();
									}
									else if(jsObj.task == "getGroupNamesByCategory")
									{
										clearOptionsListForSelectElmtId("groupName");
										createOptionsForSelectElmtId("groupName",myResults);
										getDesignationsByCategoryName(jsObj.categoryId);
									} 
									else if(jsObj.task == "getDesigantiosByGroupName")
									{
										clearOptionsListForSelectElmtId("designations");
										createOptionsForSelectElmtId("designations",myResults);										
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

	function getGroupNamesByCategory(elmt)
	{
		var categoryId = elmt.options[elmt.selectedIndex].value;

		var jsObj = {
						categoryId:categoryId,						
						task:'getGroupNamesByCategory'
					};
		
		var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getGroupNamesByCategoryAction.action?"+param;

		callAjax(jsObj,url);
	}

	function getDesignationsByCategoryName(categoryId)
	{

		var jsObj = {
						categoryId:categoryId,						
						task:'getDesigantiosByGroupName'
					};

		var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getDesignationsByCategoryAction.action?"+param;

		callAjax(jsObj,url);
	}

	function checkSavedStatus()
	{
		var elmt = document.getElementById("statusMessageDiv"); 
		if(!elmt)
			return;
		<c:if test="${savedStatusMsg !=  '' && savedStatus == true}">
			elmt.innerHTML = "${savedStatusMsg}";
			window.opener.location.reload();			
			//window.close();
		</c:if>		
	}

</script>

<style type="text/css">
	
	body
	{	
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
	}

	#localGroupMember_head
	{
		color:#8E320A;
		font-size:17px;
		font-weight:bold;
		text-align:center;
		text-decoration:underline;
		padding:5px;
		margin-top:20px;
		margin-bottom:20px;
	}

	#localGroupMember_body
	{
		text-align:center;
	}
	
		
	.localGroupMemberTable th
	{
		color:#652D2D;
		padding:4px;
		text-align:left;
	}

	.localGroupMemberTable td
	{
		color:#31383C;
		padding:4px;
		text-align:left;
	}

</style>

</head>
<body onload="checkSavedStatus()">
		
<s:form action="saveLocalGroupMembersAction" method="GET" theme="simple" name="form">
	<div id="localGroupMember_main">
		<div id="localGroupMember_head"> Add Local Group Member</div>
		<div id="localGroupMember_body">
			<center><table class="localGroupMemberTable">			
				<tr>
					<td colspan="3"><font style="font-size:10px;text-decoration:underline;" color="red"> Fields marked with * are complusory</font></td>
				</tr>
				<tr>
					<td colspan="2">
						<div style="color: red;">
							<s:actionerror />
							<s:fielderror />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:if test="${savedStatusMsg !=  '' && savedStatus == true}">
							<DIV theme="simple" id="statusMessageDiv" style="color:green;font-weight:bold"></DIV>			
						</c:if>						
					</td>
				</tr>
				<tr>
					<th><font color="red"> * </font> Name</th>
					<td colspan="2">						
						<s:textfield id="name" name="name" size="25"/>
					</td>
				</tr>

				<tr>
					<th><font color="red"> * </font> Mobile</th>
					<td colspan="2">
						<s:textfield id="mobile" name="mobile" size="25"/>
					</td>
				</tr>

				<tr>
					<th>Email</th>
					<td colspan="2">
						<s:textfield id="email" name="email" size="25"/>
					</td>
				</tr>

				<tr>
					<th>Address</th>
					<td colspan="2">
						<s:textfield id="address" name="address" size="25"/>
					</td>
				</tr>

				<tr>
					<th>City</th>
					<td colspan="2">
						<s:textfield id="city" name="city" size="25"/>
					</td>
				</tr>

				<tr>
					<th> <font color="red"> * </font> Select Category</th>
					<td colspan="2"> <s:select id="groupCategory" name="groupCategory" onchange="getGroupNamesByCategory(this)" cssStyle="width:150px;" list="#session.groupCategories" listKey="id" listValue="name">
					</s:select>
					</td>
				</tr>


				<tr>
					<th> <font color="red"> * </font> Select Group</th>
					<td colspan="2"><s:select id="groupName" name="groupName" cssStyle="width:150px;" list="#session.groupNames" listKey="id" listValue="name"></s:select></td>
				</tr>

				<tr>
					<th> <font color="red"> * </font> Designation</th>
					<td> <s:select id="designations" name="designations" cssStyle="width:150px;" list="#session.designations" listKey="id" listValue="name"></s:select></td>
					<td><input type="button" value="Add New Designation" onclick="showAddDesignation()"></input></td>
				</tr>

				<tr style="background-color:#ADADAD">			
					<td colspan="3" style="padding:0px;"><div id="designationDiv" style="display:none;padding:10px;"></div></td>
				</tr>	
				<tr>
					<td align="center" style="margin-top:22px">
					<s:submit cssClass="button" value="Save Group Member" name="Save"></s:submit></td>
					<td><input type="button" class="button"  value="Exit" onClick="refreshParent()"></td>
				</tr>
			</table>
			</center>
		</div>
	</div>
</s:form>
</body>
</html>