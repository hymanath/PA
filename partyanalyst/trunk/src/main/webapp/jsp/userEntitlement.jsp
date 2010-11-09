<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
<script type="text/javascript" src="js/UserEntitlement/UserEntitlements.js"></script>
<title>User Entitlement</title>

	<style type="text/css">
		#entitlementsMainDiv
		{
			margin-left:40px;
			margin-top:42px;
			text-align:left;	
		}	
		#groupNameID
		{
			font-weight:bold;
			padding-right:10px;
		}	
		#entitlementNameID
		{
			font-weight:bold;		
		}
		#managingGroupsMainDiv
		{
			text-align:left;
			width:46%;
		}
		.f1
		{
			border:4px solid #CFD6DF;
			margin-bottom:26px;
			padding:10px;
			width: 398px;
		}
		.f2
		{
			border:4px solid #CFD6DF;
			margin-bottom:10px;
			margin-right:42px;
			padding:10px;
			width: 377px;
		}
		.l2 {
			background-color:#567AAF;
			color:#FFFFFF;
			font-size:12px;
			font-weight:bold;
			padding:5px;
		}			
  </style>
 
  <script type="text/javascript">

  var selectedGroup="";
  
  function callAjax(jsObj,url){
		var results;	
		var callback = {			
		    success : function( o ) {
				try {							
					"",					
						results = YAHOO.lang.JSON.parse(o.responseText);		
						if(jsObj.task == "checkForAvailabilityOfGroupName")
						{
							showAvailabilityOfGroups(results);							
						}
						else if(jsObj.task == "createAGroup")
						{
							showResult(results);							
						}
						else if(jsObj.task == "checkForAvailabilityOfAnEntitlement")
						{
							showAvailabilityOfEntitlement(results);							
						}
						else if(jsObj.task == "createAnEntitlement")
						{
							showEntitlementResult(results);							
						}	
						else if(jsObj.task == "getAllUserGroups")
						{
							showAllGroups(results);							
						}
						else if(jsObj.task == "getAllEntitlementsForAUserGroup")
						{
							showCompleteResultsOfAGroup(results);					
						}	
						else if(jsObj.task == "saveUserGroupsRelation")
						{
							showSaveUserGroupsRelation(results);					
						}	
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

  function createAGroup()
	{
		var enteredText = document.getElementById("groupNameId").value;
		if(enteredText!=""){	
			var selectedCheckBoxes = '';
			<c:forEach var="allEntitlements" varStatus="stat" items="${allEntitlements.listOfEntitlements}">
				if(document.getElementById("${allEntitlements.id}").checked==true){
					selectedCheckBoxes+="${allEntitlements.id}"+",";
				}
			</c:forEach>			
			var jsObj=
			{	
					selectedIds:selectedCheckBoxes,
					name:enteredText,	
					type:"createAGroup",			
					task:"createAGroup"						
			};				
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/entitlementUserAction.action?"+rparam;	
			callAjax(jsObj,url);
		}else{
			alert("please enter group name");
		}		
	}
	
  </script>
  
</head>
<body>
 <h2>User Entitlements</h2> 
<div id="entitlementsMainDiv">
<table>
	<tr>
		<td>		
				<div id="managingGroupsMainDiv">
				<fieldset class="f2">
					<legend class="l2">Create Group</legend>
						<table>
							<tr>
								<td>
									<div id="groupAvailabilityID""></div>									
								</td>
							</tr>
						</table>	
						<table>
							<tr>
								<td id="groupNameID">
									Group Name		
								</td>
								<td>
									<input type="text" id="groupNameId"></input>			
								</td>
								<td>
									<input type="button" class="button" value="Check Availability" onclick="checkForAvailabilityOfGroupName()"></input>			
								</td>
							</tr>		
						</table>  
						
						<table id="allEntitlementsDiv" style="display:none;">
							  <c:if test="${allEntitlements.resultStatus != 'null'}">
									<c:forEach var="allEntitlements" varStatus="stat" items="${allEntitlements.listOfEntitlements}">
										<tr>
											<td>
												<input type="checkbox" id="${allEntitlements.id}" ></input>
											</td>
											<td>
													${allEntitlements.name}
											</td>				
										</tr>
									</c:forEach>	
							 </c:if>		
						</table>
							
						<table>
							<tr>
								<td>
									<a onclick="viewAllEntitlements()" class="candidateDetailsStyle"> view all entitlements</a>							
								</td>
								<td>
									<input type="button" class="button" value="Create Group" onclick="createAGroup()"></input>									
								</td>
							</tr>
						</table>
					</fieldset>
				</div>	
		</td>
		<td>
			<div id="managingEntitlementMainDiv">
			<fieldset class="f1">
				<legend class="l2">Create Entitlement</legend>
					<table>
						<tr>
							<td>
								<div id="entitlementAvailabilityID""></div>									
							</td>
						</tr>
					</table>	
					<table>
						<tr>
							<td id="entitlementNameID">
								Entitlement Name		
							</td>
							<td>
								<input type="text" id="entitlementId"></input>			
							</td>							
						</tr>		
					</table>  
					<table>
							<tr>	
								<td>
									<input type="button" class="button" value="Check Availability" onclick="checkForAvailabilityOfEntitlement()"></input>			
								</td>							
								<td>
									<input type="button" class="button" value="Create Entitlement" onclick="createAEntitlement()"></input>									
								</td>
							</tr>
					</table>
			</fieldset>
			</div>			
	</td>
</tr>
 
<tr>
<c:if test="${allEntitlements.listOfUsers != 'null'}">
	<td>
				<div id="managingEntitlementMainDiv">
				<fieldset class="f2">
					<legend class="l2">Assign A Group to Users</legend>
						<table>
							<tr>
								<td>
									<div id="assignEntitlementsId""></div>									
								</td>
							</tr>
						</table>	
						<table>
							<tr>
								<td id="entitlementNameID">
									All Users		
								</td>
								<td>
									<select id="usersId">
										<c:forEach var="allUsers" varStatus="stat" items="${allRegisteredUsersData.listOfUsers}">		
											<option value="${allUsers.id}"> ${allUsers.name} </option>	
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="button" class="button" value="View All Groups" onclick="getAllGroups()"></input>			
								</td>						
							</tr>		
						</table>  
						<table id="groupsInfoDiv">
								<tr>
									<td>
										<div id="allGroupsDiv"></div>
									</td>										
								</tr>								
								<tr>
									<td>
										<div id="saveUserGroupRelation"></div>
									</td>										
								</tr>
						</table>
				</fieldset>
				</div>			
		</td>
</c:if>
</tr>
</table>
</div>
</body>
</html>