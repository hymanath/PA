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

<style type="text/css">
	
</style>

<script type="text/javascript">
	
	function showAddDesignation()
	{
		var elmt = document.getElementById("designationDiv");
		if(!elmt)
			return;
		
		var str = '';
		str += '<table>';
		str += '<tr>';
		str += '<th>Designation </th>';
		str += '<td><input id="newDesigName" type="text" name="newDesigName"></input> </td>';
		str += '</tr>';
		str += '<tr>';
		str += '<th>Description </th>';
		str += '<td><textarea id="newDesigDesc"></textarea> </td>';
		str += '</tr>';
		str += '<tr>';
		str += '<td colspan="2"><input type="button" value="Add Designation" onclick="addDesigantionAjaxCall()"></td>';
		str += '</tr>';
		str += '</table>';

		elmt.innerHTML = str;
	}

	function addDesigantionAjaxCall()
	{
		var newDesigNameElmt = document.getElementById("newDesigName");
		var newDesigNamevalue = newDesigNameElmt.value;

		var newDesigDescElmt = document.getElementById("newDesigDesc");
		var newDesigDescvalue = newDesigDescElmt.value;

		var jsObj = {
						desigName:newDesigNamevalue,
						desigDesc:newDesigDescvalue,
						task:'addNewDesignation'
					};

		var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "addNewDesignationAction.action"+param;

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
										
									}
									else if(jsObj.task == "getGroupNamesByCategory")
									{
										
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

	function getGroupNamesByCategory(elmt)
	{
		var categoryId = elmt.options[elmt.selectedIndex].value;

		var jsObj = {
						categoryId:categoryId,						
						task:'getGroupNamesByCategory'
					};

		var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getGroupNamesByCategoryAction.action"+param;

		callAjax(jsObj,url);
	}

	function getDesignationsByCategoryName()
	{
		var groupId = elmt.options[elmt.selectedIndex].value;

		var jsObj = {
						groupId:groupId,						
						task:'getDesigantiosByGroupName'
					};

		var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getDesignationsByCategoryAction.action"+param;

		callAjax(jsObj,url);
	}

</script>

</head>
<body>
		
<s:form action="createLocalGroupMemberAction.action" method="GET" theme="simple" name="form" onsubmit="return validateClientSide()">
	<div id="local">
	<table>
		<tr>
			<th>Name</th>
			<td colspan="2"><input type="text" name="name"></input></td>
		</tr>

		<tr>
			<th>Mobile</th>
			<td colspan="2"><input type="text" name="mobile"></input></td>
		</tr>

		<tr>
			<th>Email</th>
			<td colspan="2"><input type="text" name="email"></input></td>
		</tr>

		<tr>
			<th>Address</th>
			<td colspan="2"><input type="text" name="address"></input></td>
		</tr>

		<tr>
			<th>City</th>
			<td colspan="2"><input type="text" name="city"></input></td>
		</tr>

		<tr>
			<th>Select Category</th>
			<td colspan="2"> <s:select id="groupCategory" name="groupCategory" onchange="getGroupNamesByCategory(this)" cssStyle="width:150px;" list="groupCategories" listKey="id" listValue="name"></s:select></td>
		</tr>


		<tr>
			<th>Select Group</th>
			<td colspan="2"><s:select id="groupName" name="groupName" onchange="getDesignationsByCategoryName(this)" cssStyle="width:150px;" list="groupNames" listKey="id" listValue="name"></s:select></td>
		</tr>


		<tr>
			<th>Designation</th>
			<td> <s:select id="designations" name="designations" cssStyle="width:150px;" list="designations" listKey="id" listValue="name"></s:select></td>
			<td><input type="button" value="Add New Designation" onclick="showAddDesignation()"></input></td>
		</tr>

		<tr>			
			<td colspan="3"><div id="designationDiv"></div></td>
		</tr>
		
	</table>
</s:form>
</body>
</html>