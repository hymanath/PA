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
		.headingID
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
		
		.button {
			background	:none repeat scroll 0 0 #335291;
			color		:#FFFFFF;
			margin-bottom:5px;
			margin-top	:5px;
			padding		:5px;
			width		:125px;
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
						else if(jsObj.task == "getAllEntitlementsForAUserGroup")
						{
							showCompleteResultsOfAGroup(results);					
						}								
						else if(jsObj.task == "getAllGroups")
						{
							showAllCommonGroups(results);						
						}
						else if(jsObj.task == "getAllUserGroups")
						{						
							showAllUserGroups(results);	
						}
						else if(jsObj.task == "getAllEntitlementGroups")
						{						
							showAllEntitlementGroups(results);	
						}	
						else if(jsObj.task == "getAllEntitlementGroupsBasedOnUserGroup")
						{						
							getAllEntitlementGroupsBasedOnUserGroup(results);	
						}
						else if(jsObj.task == "getAllEntitlementsBasedOnEntitlementGroup")
						{						
							getAllEntitlementsBasedOnEntitlementGroup(results);	
						}
						else if(jsObj.task == "getEntitlements")
						{						
							showEntitlements(results);	
						}
						else if(jsObj.task == "checkForUserGroupNameAvailability")
						{						
							showAvailabilityOfUserGroup(results);
						}
						else if(jsObj.task == "createAnUserGroup")
						{						
							showUserGroupResult(results);
						}
						else if(jsObj.task == "saveRelationBetweenEntitlementsGroupsAndUserGroupId")
						{						
							saveRelationBetweenEntitlementsGroupsAndUserGroupId(results);
						}
						else if(jsObj.task == "saveRelationBetweenEntitlementGroupAndEntitlement")
						{						
							saveRelationBetweenEntitlementGroupAndEntitlement(results);
						}
						else if(jsObj.task == "saveUserGroupsRelation")
						{						
							saveUserGroupsRelation(results);
						}
				}catch (e) {   		
				   	alert("Invalid JSON result" + e);   
				}  
		    },
		    scope : this,
		    failure : function( o ) {
		     		//	alert( "Failed to load result" + o.status + " " + o.statusText);
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
			var text = document.getElementById("groupAvailabilityID");
			var str='';
			text.innerHTML = str;
			
			var str2='';
			str2+='<b style="color:red">please enter group name';
			text.innerHTML = str2;
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
					<legend class="l2">Create An Entitlement Group</legend>
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
				<legend class="l2">Create An Entitlement</legend>
					<table>
						<tr>
							<td>
								<div id="entitlementAvailabilityID""></div>									
							</td>
						</tr>
					</table>	
					<table>
						<tr>
							<td class="headingID">
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
	<td>
		<div id="managingUserGroupMainDiv">
			<fieldset class="f1">
				<legend class="l2">Create An User Group</legend>
					<table>
						<tr>
							<td>
								<div id="userGroupAvailabilityID""></div>									
							</td>
						</tr>
					</table>	
					<table>
						<tr>
							<td class="headingID">
								User Group Name		
							</td>
							<td>
								<input type="text" id="userGroupID"></input>			
							</td>							
						</tr>		
					</table>  
					<table>
							<tr>	
								<td>
									<input type="button" class="button" value="Check Availability" onclick="checkForAvailabilityOfUserGroup()"></input>			
								</td>							
								<td>
									<input type="button" class="button" value="Create User Group" onclick="createAnUserGroup()"></input>									
								</td>
							</tr>
					</table>
			</fieldset>
			</div>			
</td>
<c:if test="${allEntitlements.listOfUsers != 'null'}">
	<td>
				<div id="managingEntitlementMainDiv">
				<fieldset class="f2">
					<legend class="l2">Assign  UserGroups to User</legend>
						<table>
							<tr>
								<td>
									<div id="assignEntitlementsId""></div>									
								</td>
							</tr>
						</table>	
						<table>
							<tr>
								<td class="headingID">
									All Users		
								</td>
								<td>
									<select id="usersId" onchange="showGroupsForAUser()" style="width:150px;">
										<c:forEach var="allUsers" varStatus="stat" items="${allRegisteredUsersData.listOfUsers}">		
											<option value="${allUsers.id}"> ${allUsers.name} </option>	
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="button" class="button" value="Manage All Groups" onclick="getAllGroups()"></input>			
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
<tr>
<td>
				<div id="userGroupEntitlemntGroupMainDiv">
				<fieldset class="f2">
					<legend class="l2">Assign EntitlementGroups to UserGroup</legend>
						<table>
							<tr>
								<td>
									<div id="userGroupEntitlemntGroupId""></div>									
								</td>
							</tr>
						</table>	
						<table>
							<tr>
								<td class="headingID">
									All User Groups		
								</td>
								<td>
									<select id="userGroupsId" onchange="showUserGroupsForAUser()">
										<c:forEach var="allUsers" varStatus="stat" items="${allUserGroups.setOfGroups}">		
											<option value="${allUsers.id}"> ${allUsers.name} </option>	
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="button" class="button" style="width:175px;" value="Manage All User Groups" onclick="getAllEntitlementGroups()"></input>			
								</td>						
							</tr>		
						</table>  
						<table id="userGroupsInfoDiv">
								<tr>
									<td>
										<div id="allUserGroupsDiv"></div>
									</td>										
								</tr>								
								<tr>
									<td>
										<div id="saveEntitlementUserGroupRelation"></div>
									</td>										
								</tr>
						</table>
				</fieldset>
				</div>			
		</td>

<td>
				<div id="entitlementGroupEntitlemntMainDiv">
				<fieldset class="f2">
					<legend class="l2">Assign Entitlements to EntitlementGroup</legend>
						<table>
							<tr>
								<td>
									<div id="entitlemntGroupId""></div>									
								</td>
							</tr>
						</table>	
						<table>
							<tr>
								<td class="headingID">
									 Entitlement Groups		
								</td>
								<td>
									<select id="entitlementGroupId" onchange="showEntitlementsForAEntitlementGroup()" style="width:162px;">
										<c:forEach var="allUsers" varStatus="stat" items="${allGroups.setOfGroups}">		
											<option value="${allUsers.id}"> ${allUsers.name} </option>	
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="button" class="button" style="width:190px;" value="Manage All Entitlement Groups" onclick="getAllEntitlements()"></input>			
								</td>						
							</tr>		
						</table>  
						<table id="entitlementsInfoDIV">
								<tr>
									<td>
										<div id="allEntitlementsGroupsDIV"></div>
									</td>										
								</tr>								
								<tr>
									<td>
										<div id="saveEntitlementAndEntitlementGroupRelation"></div>
									</td>										
								</tr>
						</table>
				</fieldset>
				</div>			
		</td>
</tr>
</table>
</div>
</body>
</html>