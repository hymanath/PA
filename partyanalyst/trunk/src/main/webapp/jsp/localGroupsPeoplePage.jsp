<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Local Groups Details</title>

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
<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

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
    
<!-- YUI Dependency files (End) -->


<link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">	
<style type="text/css">

	body
	{			
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}

	#localGroupsPeopleData_main
	{
		padding:40px;
		text-align:left;
	}
	
	#influencePeopleData_body .yui-pg-container 
	{
		margin:6px 60px;
		text-align:right;
	}

	.peopleDataMain
	{
		border:1px solid #DBD8D4;
		margin-top:20px;
	}

	.peopleRegionOverviewTable th
	{
		background-color:#A8937C;
		color:#FFFFFF;
		padding:5px;
	}

	.peopleRegionOverviewTable td
	{
		background-color:#F6F1EB;
		padding:5px;
	}

	#localGroupsPeopleData_head
	{
		color:#764916;
		font-size:17px;
		font-weight:bold;
		padding:5px;
		text-decoration:underline;
	}
	
	.selectButtonsDiv
	{
		/*text-align:right;*/
	}

	#smsStatus
	{
		text-align:center;
	}
	
	.groupHeadingAnc
	{
		color:#77471D;
		margin:0px 10px 0px 5px;
		text-decoration:none;
	}
	
	.groupHeadingAnc:hover
	{
		text-decoration:underline;
	}
	.elbutton {
		background-color:#483D8B;
		color:#FFFAF0;
		font:bold small 'trebuchet ms',helvetica,sans-serif;
	}
</style>

<script type="text/javascript">
	<c:forEach var="group" items="${localGroupsPeople}" varStatus ="status">
	</c:forEach>
	function buildLocalGroupsPeopleDataTable()
	{

		<c:forEach var="group" items="${localGroupsPeople}" varStatus ="status">
				var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("peopleDataTable_${status.index}"));
				resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
				resultsDataSource.responseSchema = {
					fields : [{
						key : ""
					}, {
						key : "name"
					}, {
						key : "mobileNumber",parser:"number"
					}, {
						key : "address"
					}, {
						key : "location"
					}, {
						key : "designation"
					} , {
						key : "emailId"
					},{
						key : "edit"
					},{
						key : "delete"
					}  ]
				};

				var resultsColumnDefs = [ {
					key : "",
					label : "<input type='checkbox' name='regionHeaderCheckBox' onclick='selectAllPeopleInRegion(${group.localUserGroupId})'></input>",
					sortable : false
				},{
					key : "name",
					label : "First Name",
					sortable : true
				},{
					key : "mobileNumber",
					parser:"number",
					label : "Mobile",
					sortable : true
				}, {
					key : "address",
					label : "Address",
					sortable : true
				}, {
					key : "location",
					label : "Location",
					sortable : true
				}, {
					key : "designation",
					label : "Designation",
					sortable : true
				}, {
					key : "emailId",
					label : "Email",
					sortable : true
				},{
					key : "edit",
					label : "Edit",
					sortable : false
				},{
					key : "delete",
					label : "Delete",
					sortable : false
				}];

				var myConfigs = {
				paginator : new YAHOO.widget.Paginator({
					rowsPerPage: 50
				})
			};


			var myDataTable = new YAHOO.widget.DataTable("peopleRegionData_ ${status.index}",resultsColumnDefs, resultsDataSource,myConfigs);  

			</c:forEach>
		
	}

	function selectAllPeople()
	{
		var elmts = document.getElementsByTagName('input');

		if(elmts.length == 0)
			return;

		for(var i=0; i<elmts.length; i++)
		{
			if(elmts[i].type == "checkbox" && !elmts[i].checked)
				elmts[i].checked = true;
		}
	}

	function DeSelectAllPeople()
	{
		var elmts = document.getElementsByTagName('input');

		if(elmts.length == 0)
			return;

		for(var i=0; i<elmts.length; i++)
		{
			if(elmts[i].type == "checkbox" && elmts[i].checked)
				elmts[i].checked = false;
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
			limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
		}
	}
	
	function sendSMSToSelectedPeople()
	{
			var str = '';	
			str += '<table width="100%">';
			str += '<tr>';
			str += '<th>SMS Text :</th>';
			str += '<td>';
			str += '<textarea id="localGroupPeopleMsg" onkeyup="limitText(\'localGroupPeopleMsg\',\'maxcount\',200)" rows="3" cols="38"></textarea>';
			str +='<div id="limitDiv">';
			str +='<table style="width:100%;"><tr>';
			str +='<td align="left" style="width:50%;color:#4B4242;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
			str +='<td align="right" style="width:50%;color:#4B4242;"><div>Max 200 chars</div></td>';
			str +='</tr></table>';
			str +='</div>';	
			str += '</td>';
			str += '</tr>';

			str += '<tr>';
			str += '<td></td>';
			str += '<td><input id="smsIncludeUserName" type="checkbox" name="smsIncludeUserName"></input> Include User Name</td>';				
			str += '</tr>';
			
			str += '<tr>';
			str += '<td></td>';
			str += '<td><input id="smsIncludeSenderName" type="checkbox" onclick="enableSenderName()" name="smsIncludeUserName"></input> Include Sender Name ';
			str += '<input type="text" id="senderNameText" disabled="disabled" value="${sessionScope.UserName}" />';
			str += '</td>';				
			str += '</tr>';		

			str += '</table>';
			str	+= '<div id="smsStatus"></div>';
			
			var connectPopupPanel = new YAHOO.widget.Dialog("messageBox", {      
						 width:'500px',
						 fixedcenter : true, 
						 visible : true,
						 constraintoviewport : true, 
						 iframe :true,
						 modal :true,
						 hideaftersubmit:true,
						 close:true,
						 draggable:true,
						 buttons: [ { text:"Send", handler:handleYes, isDefault:true }] 
			   });	 
			
			connectPopupPanel.setHeader("Send Message");
			connectPopupPanel.setBody(str);
			connectPopupPanel.render();
	}


  function deleteLocalUserGroup(localUserGroupIdId){

		var ask = confirm("Do You want to delete");
			if (ask ==  true)
		 {
			var jsObj= 
			{		
				localUserGroupIdId :localUserGroupIdId,		  			
				task: "deleteLocalUserGroup"		
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "deleteLocalUserGroupAjaxAction.action?"+param;
			callAjax(jsObj,url);
		}	
		
		else
		  {
		  		return;	
		  }	
		}		
	function enableSenderName()
	{
		var elmt = document.getElementById("senderNameText");
		if(elmt.disabled == true)
			elmt.disabled = false;
		else
			elmt.disabled = true;
	}

	function handleYes()
	{
		var messageElmt = document.getElementById("localGroupPeopleMsg");
		var statusElmt = document.getElementById("smsStatus");
		var includeElmt = document.getElementById("smsIncludeUserName");
		var includeSenderElmtCheck = document.getElementById("smsIncludeSenderName");
		var senderNameTextElmt = document.getElementById("senderNameText");

		var message = messageElmt.value;
		var includeName = false;
		var senderName = '';

		if(includeElmt.checked == true)
			includeName = true;

		if(includeSenderElmtCheck.checked == true)
			senderName = senderNameTextElmt.value;

		if(message == "")
		{
			statusElmt.innerHTML = '<font color="red"> Message Content Is Empty .. </font>';
			return;
		}
		else
			statusElmt.innerHTML = '';

	
		var elmts = document.getElementsByTagName('input');
		
		var checkedIds = '';

		if(elmts.length == 0)
			return;
		
		for(var i=0; i<elmts.length; i++)
		{
			if(elmts[i].type == "checkbox" && elmts[i].name != "regionHeaderCheckBox" && elmts[i].name != "smsIncludeUserName" && elmts[i].checked)
			{
				checkedIds += elmts[i].value;
				checkedIds += ',';
			}
		}

		checkedIds = checkedIds.substring(0,checkedIds.length-1);
		
		if(checkedIds == '' || checkedIds == null)
		{
			statusElmt.innerHTML = '<font color="red"> No People Has Been Selected To Send Message .. </font>';
			return;
		}
		else
			statusElmt.innerHTML = '';
		
		var jsObj= 
		{	
			checkedIdString : checkedIds,
			smsMessage : message,
			includeName : includeName,
			senderName : senderName,
			task: "sendSMSToLocalGroupPeople"				
		};
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "sendSMSToInfluencePeopleAction.action?"+param;
		
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
										
								if(jsObj.task == "sendSMSToLocalGroupPeople")
									{
										var statusElmt = document.getElementById("smsStatus");

										if(myResults.resultStatus == null)
											statusElmt.innerHTML = '<font color="green">SMS has been sent to '+myResults.totalSmsSent+' people successfully .. </font>';
										else
											statusElmt.innerHTML = '<font color="red">SMS cannot been sent to selected people due to some techniccal difficulty.. </font>';
									}
									if(jsObj.task == "deleteLocalUserGroup"){
											alert("Succesfully Deleted");
											windwRefresh();	
									}
									
								}
							catch (e)
								{   
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

	function selectAllPeopleInRegion(id)
	{
		var name = "localGroupPeopleCheck_"+id;
		var elmts = document.getElementsByName(name);

		if(elmts.length == 0)
			return;

		for(var i=0; i<elmts.length; i++)
		{
			if(!elmts[i].checked)
				elmts[i].checked = true;
			else
				elmts[i].checked = false;
		}	

	}
	
	function getPersonDetails(id)
	{
		
	}
	
	function editPersonDetails(id)
	{
			var browser2 = window.open("<s:url action="editLocalGroupAction.action"/>?userGroupId="+id,"editLocalGroupAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	y}

	function deletePersonDetails(id)
	{
		var ask = confirm("Do You want to delete");
			if (ask ==  true)
		 {
			var jsObj= 
			{		
				localUserMemberId :id,		  			
				task: "deleteLocalUserMember"		
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "editGropLocalAction.action?"+param;
			callAjax(jsObj,url);
			alert("Succesfully Deleted");
			windwRefresh();
		}	
		
		else
		  {
		  		return;	
		  }	
	}

	function windwRefresh()
	{
			window.location.reload();
	}
	
function redirectToNewLocalGroupCreation(windowTask,categoryId,localUserGroupId)
		{
			var createGroupBrowser = window.open("<s:url action="createLocalGroupAction.action"/>?windowTask="+windowTask+"&grCategoryId="+categoryId+"&localUserGroupId="+localUserGroupId,"createLocalGroup","scrollbars=yes,height=600,width=600,left=200,top=200");										 
		    createGroupBrowser.focus();
		}
	
	function redirectToNewLocalGroupMember(categoryId,groupId)
	{

		var createGroupMemberBrowser = window.open("<s:url action="createLocalGroupMemberPopupAction.action"/>?groupCategoryId="+categoryId+"&groupId="+groupId,"createLocalGroupMember","scrollbars=yes,height=600,width=600,left=200,top=200");										 
		createGroupMemberBrowser.focus();
	}

</script>


</head>
<body>
	<div id="messageBox_outer" class="yui-skin-sam"><div id="messageBox"></div></div>
	<div id="localGroupsPeopleData_main">
		<div id="localGroupsPeopleData_head">
			<center>${regionTitle} Details In ${regionName} ${regionType}</center>
		</div>
		<div id="localGroupsPeopleData_body" class="yui-skin-sam">	
		
		<div id="selectButtonsDiv" class="selectButtonsDiv">
			<table width="100%">
				<tr>
					<td align="left">
						<input class="elbutton" type="button" value="Create New ${regionTitle}" onclick="redirectToNewLocalGroupCreation('new',<%= request.getParameter("regionTitleId")%>,0)"></input>
					</td>
					<td align="right">
						<input class="elbutton" type="button" value="select All" onclick="selectAllPeople()"></input>
						<input class="elbutton" type="button" value="DeSelect All" onclick="DeSelectAllPeople()"></input>
						<input class="elbutton" type="button" value="Send SMS" onclick="sendSMSToSelectedPeople()"></input>
					</td>
				</tr>
			</table>
			
			
		</div>

			<c:forEach var="group" items="${localGroupsPeople}" varStatus ="status">
				<div id="peopleRegion_main_ ${status.index}" class="peopleDataMain">
					<div id="peopleRegion_head_ ${status.index}" class="scopeWise_head" style="padding:5px;">
						<table width="100%">
							<tr>
								<td width="2%" align="left"><img src="images/icons/system_grps.png"></td>
								<td width="55%" align="left">${group.localUserGroupName} - ${group.groupMembersCount}</td>

								<td width="2%"><img height="18" height="18" src="images/icons/cadreReport/addCadre.png"></td>
								<td>
									<a class="groupHeadingAnc" href="javascript:{}" onclick="redirectToNewLocalGroupMember(${group.groupCategoryId},${group.localUserGroupId})">Add Members To ${group.localUserGroupName}</a>
								</td>
								<td width="2%"><img src="images/icons/edit.png" style="text-decoration: none; border: 0px none;"></td>
								<td>
									<a class="groupHeadingAnc" href="javascript:{}" onclick="redirectToNewLocalGroupCreation('edit',<%= request.getParameter("regionTitleId")%>,${group.localUserGroupId})">Edit ${group.localUserGroupName}</a>
								</td>

								<td width="2%"><img src="images/icons/delete.png" style="text-decoration: none; border: 0px none;"></td>
								<td>
									<a class="groupHeadingAnc" href="javascript:{}" onclick="deleteLocalUserGroup(${group.localUserGroupId})">Delete ${group.localUserGroupName}</a>
								</td>
																
							</tr>
						</table>
					</div>
					<div id="peopleRegion_body_ ${status.index}" class="yui-skin-sam" style="padding: 10px;">
						<div id="peopleRegionOverview_ ${status.index}">
							<table class="peopleRegionOverviewTable" width="100%">
								<tr>
									<th>Category</th>
									<td>${group.groupCategoryType}</td>
									<th>Location</th>
									<td>${group.groupLocation}</td>
									<th>Members</th>
									<td>${group.groupMembersCount}</td>									
								</tr>
								<tr>
									<th>State</th>
									<td>${group.stateName}</td>
									<th>District</th>
									<td>${group.districtName}</td>
									<th>Constituency</th>
									<td>${group.constituencyName}</td>									
								</tr>
								<tr>
									<th>Mandal/Muncipal/Corp</th>
									<td>${group.mandalName}</td>
									<th>Village/Ward</th>
									<td>${group.villageOrWardName}</td>
									<th>Booth</th>
									<td>${group.boothName}</td>									
								</tr>
								<tr>
									<th>Group Scope</th>
									<td>${group.groupScopeId}</td>
									<th>Group Scope Location</th>
									<td colspan="3" align="left">${group.groupScopeRange}</td>
								</tr>
								<tr>
									<th valign="top">Description</th>
									<td valign="top" colspan="5" align="left">${group.groupDesc}</td>
								</tr>
							</table>
						</div>
						<div id="peopleRegionData_ ${status.index}">
							<table id="peopleDataTable_${status.index}">						
								<c:forEach var="people" items="${group.groupMemberDetails}" varStatus ="status">
									<tr>
										<td><input type="checkbox" name="localGroupPeopleCheck_${group.localUserGroupId}" value="${people.groupMemberId}"></input></td>
										<td>${people.name}</td>
										<td>${people.mobileNumber}</td>
										<td>${people.address}</td>
										<td>${people.location}</td>
										<td>${people.designation}</td>
										<td>${people.emailId}</td>												
										<td>
											<a href="javascript:{}" onclick="editPersonDetails(${people.groupMemberId})">
												<img style="text-decoration: none; border: 0px none;" src="images/icons/edit.png">
											</a>
										</td>
										<td>
											<a href="javascript:{}" onclick="deletePersonDetails(${people.groupMemberId})">
												<img style="text-decoration: none; border: 0px none;" src="images/icons/delete.png">
											</a>
										</td>
									</tr>							
								</c:forEach>
							</table>
						</div>
					</div>	
				</div>
			</c:forEach>
		</div>		
	</div>

	<script type="text/javascript">		
		buildLocalGroupsPeopleDataTable();
	</script>

</body>
</html>