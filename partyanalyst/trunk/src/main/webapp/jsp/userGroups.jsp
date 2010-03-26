<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.ResourceBundle;" %>    
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Groups</title>
<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<!-- YUI Skin Sam -->
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/paginator.css">

<script type="text/javascript">

function callAjax(param,jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									if(jsObj.task == "subGrpsCountInSystemGrpsForUser")
									{										
										showSubGrpsCountInSystemGrps(myResults)										
									} else if(jsObj.task == "subGrpsCountInMyGrpsForUser")
									{										
										showSubGrpsCountInMyGrps(myResults)										
									} else if(jsObj.task == "getSelectedMyGroupCompleteDetails")
									{										
										showMyGroupCompleteDetails(myResults)									
									} else if(jsObj.task == "getSelectedStaticGroupCompleteDetails")
									{										
										showSystemGroupCompleteDetails(myResults)											
									}else if(jsObj.task == "getSelectedGroupMembersDetails")
									{
										showGroupMembersList(myResults);										
									} else if(jsObj.task == "getSubGroupsListInSystemGroups")
									{
										showSysSubGroupsListInCreateGroupDialog(myResults,jsObj);										
									} else if(jsObj.task == "getSubGroupsListInMyGroups")
									{
										showMySubGroupsListInCreateGroupDialog(myResults,jsObj);										
									} else if(jsObj.task == "getSubGroupsOfStaticGroupParents")
									{
										showSysSubGroupsListInCreateGroupDialog(myResults,jsObj);										
									} else if(jsObj.task == "createNewGroup")
									{
										showGroupCreationConfirmation(myResults);										
									} else if(jsObj.task == "checkAvailability")
									{
										showExistingGroupAlert(myResults, jsObj);										
									}else if(jsObj.task == "addMemberToAGroup")
									{
										showAddedMbrConfirm(myResults);										
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

function buildLayout()
{	
		var layoutEl = new YAHOO.widget.Layout('userGroupsMainDiv', { 
		height:1100,
		units: [	 
				{ 
					position: 'top',
					body: 'userGroupsTopDiv',							
					resize: false,
					collapse: false,
					scroll: true,						
					animate: false,
					height:278
				},
				{
					position: 'left',
					body: 'userGroupsLeftDiv',
					resize: false,
					collapse: false,
					scroll: true,
					gutter: '5px',						
					animate: false,		
					width:300
				},
				{ 
					position: 'center',
					body: 'userGroupsCenterDiv',							
					resize: false,
					gutter: '5px',
					collapse: false,
					scroll: true,						
					animate: false
				},
				{
					position: 'bottom',
					body: 'userGroupsFooter',
					resize: false,
					gutter: '0px',
					collapse: false,
					scroll: true,						
					animate: false,		
					height: '75'
				}
				
    ] 
	}); 
	layoutEl.render(); 
}
</script>
<style>
.yui-skin-sam .yui-layout .yui-layout-unit div.yui-layout-bd {
		border-style:none;
		text-align:left;
		background:white;		
	}
	.yui-layout-unit .yui-layout-unit-left yui-layout-scroll{
		background:white;
		
	}
	.yui-skin-sam .yui-layout 
	{
		background-color:#FFFFFF;	
	}	
	.userGrp-intro {
			background:#96B1D2  none repeat scroll 0 0;
			border:1px solid #889D5B;
			margin-bottom:13px;
			position:relative;
	}
	#grpsDescTdDiv{
			border:1px solid #96B4D3;
			border-width:1px 3px 3px 1px;
			color:#628C2A;
			font-weight:bold;
			text-align:left;		
	}
	.hdTable h3{
			color:#628C2A;
			font-size:182%;
			font-weight:bold !important;
			line-height:100%;
	}
	.uLEleStyle {
			font-family:arial,helvetica,sans-serif;
			font-size:74%;
			font-size-adjust:none;
			font-style:normal;
			font-variant:normal;
			font-weight:normal;
			line-height:normal;
			color:#000000;
	}
	#searchBoxDiv{
			background-color:#EFF3F7;
			border:1px solid #96B4D3;
			padding:10px;
			width:96%;
			color:#628C2A;			
			font-weight:bold;	
			border-width:1px 3px 3px 1px;
	}
	#userGroupsLeftDiv {
		background-color:white;	
	}	
	#myGroups
		{		
			margin-left:20px;		
			background:transparent url(images/usergroups/icon_groups.png) no-repeat scroll left bottom;
		}
	#systemGroups
		{
			margin-left:20px;
			background:transparent url(images/usergroups/system_grps.png) no-repeat scroll left bottom;
		}		
	#createNewGrpDiv
		{
			margin-left:20px;
			background:transparent url(images/usergroups/plus.png) no-repeat scroll left bottom;
		}
	#manageGrpDiv
	{
		margin-left:20px;
		background:transparent url(images/usergroups/seetings.jpg) no-repeat scroll left bottom;
	}	
	.link a {
			color:#247CD4;
			text-decoration:none;
			margin-left:21px;
			font-size:15px;						
		}
	#leftNavLinksDiv {
		border-top:1px solid #96B4D3;
		border-left:1px solid #96B4D3;
		border-right:3px solid #96B4D3;
		border-bottom:3px solid #96B4D3;
		background-color:#EFF3F7;
		margin-left:0px;
		margin-top:0px;
		margin-bottom:5px;
		padding:4px;
	}
	#leftNavLinksDiv .corner-top, .corner-left, .corner-bottom {
		background-image:url(images/usergroups/cornerimage1.gif);
	}
	#searchHelp{
		width:100%;
		text-align: center;
		color:#96B1D2;
		font-weight:bold;
		font-size:17px;
	}
	.searchBoxLabels
	{
		color:#96B4D3;
	}
	#myProfile{
		background-color:#EFF3F7;
		border-top:1px solid #96B4D3;
		border-left:1px solid #96B4D3;
		border-right:3px solid #96B4D3;
		border-bottom:3px solid #96B4D3;
		padding:10px;
		color:#628C2A;		
		font-weight:bold;				
	}
	legend {
		background-color:#EBE4F2;
	color:#115C06;
	font-size:12px;
	font-weight:bold;
	padding:5px;
    }
    
	fieldset {
		border-top:1px solid #96B4D3;
		border-left:1px solid #96B4D3;
		border-right:3px solid #96B4D3;
		border-bottom:3px solid #96B4D3;
		margin-bottom:5px;
		color:#404040;
		background:#EFF3F7;				
	}
	#userGroupsCenterDiv
	{
		border-top:1px solid #96B4D3;
		border-left:1px solid #96B4D3;
		border-right:3px solid #96B4D3;
		border-bottom:3px solid #96B4D3;
		min-height:700px;
		height:700px;
		background-color:white;	
	}
	h4{
		align:left;	
	}/*
	#userGroupsFooter{
		border-top:1px solid #96B4D3;
		border-left:1px solid #96B4D3;
		border-right:3px solid #96B4D3;
		border-bottom:3px solid #96B4D3;
		background:#96B1D2;		
	}*/	
	#centerNavLinksDiv {			
		background-color:#EBE4F2;
		border:1px solid #DEDEDE;
		color:#247CD4;
		font-weight:bold;
		padding:10px;	}	
	#summaryTextDiv {
		color:#606060;
		font-family:veradana;
		font-size:14px;
		margin-left:5px;
	}
	#moreDetailsDiv {
		color:#606060;
		font-family:veradana;
		font-size:14px;
		margin-left:5px;		
		}
	#groupsCountDiv
	{
		background-image:url(images/usergroups/group_blur.png);
		width:260px;
		height:200px;	
		overflow-y:auto;
		/*height:expression(document.body.clientWidth > 200? "200px": "auto" );
		min-height:1000px;
	min-width:250px;
	max-width:260px;
	width:expression(document.body.clientWidth > 260? "260px": "auto" );*/	
	}
	.link  {
			color:#247CD4;
			text-decoration:none;
			font-size:15px;
			font-weight:bold;			
		}
	.link1  {
			color:#115C06;
			text-decoration:none;
			font-size:15px;			
			font-weight:bold;			
		}
		#groupDetailsHead {
			background-color:#EFF3F7;
			background-image:none;
			background-position:center top;
			background-repeat:repeat;
			border:1px solid;
			color:CadetBlue;
			font-weight:normal;
			margin-right:3px;
			margin-top:4px;
			text-align:left;
			}
		#groupDetailsHead h4
		{
			color:#356784;
			margin-bottom:0;
			margin-top:0;
			padding:0;
			text-align:left;
			text-decoration:underline;			
		}
		.promoImage {
			float:left;
			margin-right:10px;
			width:40px;
		}
		.promoNumber {
			color:#888888;
			float:left;
			font-size:2em;
			font-weight:bold;
			height:40px;
			line-height:40px;
			width:50px;
		}
		.promoText {
			clear:left;
			color:#555555;
			font-size:1.3em;
			margin-left:40px;
			margin-top:0;
			text-transform:lowercase;
		}
		#sendSmsDiv
		{
			background-color:#EBE4F2;
			border:1px dotted #DEDEDE;
		}
		#smsTextArea
		{
			border-color:#7C7C7C #C3C3C3 #DDDDDD;
			border-style:solid;
			border-width:1px;
			color:#414141;
			font-family:Verdana,Arial,Helvetica,sans-serif;
			font-size:12px;
			height:60px;
			overflow:auto;
			padding:4px;
			width:200px;
		}
		.button {
			background:#9696C0 none repeat scroll 0 0;
			border:2px solid #008BD1;
			color:#FFFFFF;
			font-family:Arial,Helvetica,sans-serif;
			font-weight:bold;
			height:29px;
			padding:5px;
			width:74px;
		}
		#smsDiv
		{
			background:#EBE4F2 none repeat scroll 0 0;
			border:2px solid #008BD1;
			height:200px;
			margin-left:0;
			margin-top:1px;
			padding-left:5px;
			padding-right:5px;	
		}
		#subGroupsListDiv
		{
			background:#FFFFFF none repeat scroll 0 0;
			border:2px solid #008BD1;
			height:200px;
			margin-left:0;
			padding-left:5px;
			padding-right:5px;
			overflow-y: auto;
		}
		#headingDiv{
			background:#9696C0 none repeat scroll 0 0;
			border:1px solid #9696C0;
			color:#FFFFFF;
			font-size:12px;
			margin-top:0;
			padding:6px;
			font-weight:bold;
			}
		.subGroupLink{
			color:#247CD4;
			text-decoration:none;
			font-size:12px;
			font-weight:bold;	
		}
		.smalltype {
		-x-system-font:none;
		font-family:verdana,arial,helvetica,sans-serif;
		font-size:74%;
		font-size-adjust:none;
		font-stretch:normal;
		font-style:normal;
		font-variant:normal;
		font-weight:normal;
		line-height:normal;
		color:#115C06;
		}
		.subGroupsTable td {
		
		border-bottom:1px dotted #9696C0;
		padding:5px;
		text-align:left;
		}
		.selectedLink a
		{
			color:#115c06;
			text-decoration:none;
			margin-left:21px;
			font-size:15px;		
		}
		.navLinksButton
		{
			background:none;
			border-color:#247CD4;
			border-style:none solid none none;
			border-width:medium 1px medium medium;
			color:#247CD4;
			font-weight:bold;
			padding:0 6px;
		}
		.groupsCountTable
		{
			width:100%;
			margin-top:10px;
		}
		.head
		{
			font-size:11px;
			color:#404040;
		}
		.width
		{
			width:70%;
			color:#247CD4;	
		}
		.width1
		{
			color:#247CD4;
			text-align:center;
			width:30%;	
		}
		.membersLinks
		{
			color:green;
			font-family:Trebuchet MS;
			font-size:13px;
			font-weight:bold;
			text-decoration:none;
		}
		.navLinks
		{
			color:green;
			font-size:13px;
			font-weight:bold;
			margin-left:5px;
			margin-right:5px;
			margin-top:5px;
			text-decoration:none;
		}
		#subGrpsListInCreateGrpDialog,#subGrpsListInCreateGrpDialog1
		{
			background:#FFFFFF none repeat scroll 0 0;
			border:2px solid #008BD1;
			height:200px;
			margin-left:0;
			padding-left:5px;
			padding-right:5px;
			overflow-y: auto;
		}
		.button1 {
			background:#9696C0 none repeat scroll 0 0;
			border:2px solid #008BD1;
			color:#FFFFFF;
			font-family:Arial,Helvetica,sans-serif;
			font-weight:bold;
			height:29px;
			padding:5px;						
		}				
		.confirmMsg{
			width:80%;
			color:green;
			font-weight:bold;
			font-size:13px;			
		}
		.exitBtnTd
		{
			width:20%;
		}
		#groupExistsAlert,#confirmAddMember
		{
			font-weight:bold;
			font-size: 13px;
			text-align: left;
		}
		.yui-skin-sam .yui-dt caption {
			background:#9696C0 none repeat scroll 0 0;
			color:#FFFFFF;
			font-size:12px;
			font-style:bold;
			font-weight:bold;
			line-height:1;
			padding:6px;
			text-align:left;
			font-style:normal;
		}
		.yui-skin-sam .yui-pg-container {
			display:block;
			margin:6px 0;
			text-align:center;
			white-space:nowrap;
		}
		.yui-skin-sam .yui-dt table {
			border-collapse:separate;
			border-spacing:0;
			color:#115C06;
			font-family:arial;
			font-size:inherit;
		}
		.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a {
			color:#115C06;
			font-weight:bold;
			text-decoration:none;
		}
		#showGrpMembers
		{
			height:200px;
			scroll-y:auto;
		}
		#navigationLink{
			background:#EFF3F7 none repeat scroll 0 0;
			border:1px solid #96B4D3;
			color:green;
			font-weight:bold;
			margin-left:5px;
			margin-right:3px;
			margin-top:5px;
			padding:5px;
		}
									
</style>
<script type="text/javascript">
var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String groupName = rb.getString("groupName");
		String description = rb.getString("description"); 
		String createNewGrp = rb.getString("createNewGrp");
		String addToStaticGrpAsSubGrpRadio = rb.getString("addToStaticGrpAsSubGrpRadio");
		String addToGrpAsSubGrpRadio = rb.getString("addToGrpAsSubGrpRadio");
		String mobile  = rb.getString("mobile");
		String address  = rb.getString("address");
		String location  = rb.getString("location");
		String name = rb.getString("name");
		String email = rb.getString("email");
		String telephoneNo = rb.getString("telephoneNo");
		String designation = rb.getString("designation"); 
		
%> }
var createGroupDialog,addGrpMbrsDialog, grpMbrsDetailsDataTable,numbersArray,confirmation;
var userName= "${sessionScope.UserName}";

var userGrpsObj={
		showGrpMembersArr:[],
		systemGroupsListBoxArr:[],
		myGroupsListBoxArr:[]
};	
	function showSubGrpsCountInSystemGrps(results)
	{	
		var subGrpsCountInSystemGrps = results.groupsDetailsForUser;
		var groupDetailsDivEl = document.getElementById("groupsCountDiv");
		var type = "USER_GROUP_CATEGORY_PARENT";
		var groupDetailsLegendEl = document.getElementById("groupDetailsLegend");
		groupDetailsLegendEl.innerHTML = 'Sub Groups Details Of <font color="#247CD4">-*System Groups*-</font>';
	
		var myGroupsLinksDivContent = '';
		myGroupsLinksDivContent+='<table class="groupsCountTable" cellpadding="3">';
		myGroupsLinksDivContent+='<tr class="head">';
		myGroupsLinksDivContent+='<th class="width">Category </th>';
		myGroupsLinksDivContent+='<th class="width1">Sub Groups</th>';
		myGroupsLinksDivContent+='</tr>';
		for(var i in subGrpsCountInSystemGrps)
		{
			myGroupsLinksDivContent+='<tr class="data">';
			myGroupsLinksDivContent+='<td class="width"><a href="javascript:{}" class="link1" onclick="getSystemGroupsDetails('+subGrpsCountInSystemGrps[i].staticGroupId+',\''+type+'\',\'new\',\''+subGrpsCountInSystemGrps[i].staticGroupName+'\')">'+subGrpsCountInSystemGrps[i].staticGroupName+ '</a>';
			myGroupsLinksDivContent+='<td class="width1">'+subGrpsCountInSystemGrps[i].numberOfGroups+'<td>';
			myGroupsLinksDivContent+='</tr>';				
		}
		myGroupsLinksDivContent+='<table>';
		groupDetailsDivEl.innerHTML =  myGroupsLinksDivContent;
	}

	function showSubGrpsCountInMyGrps(results)
	{
		var subGrpsCountInMyGrps = results.groupsDetailsForUser;
		var groupDetailsDivEl = document.getElementById("groupsCountDiv");
		var groupDetailsLegendEl = document.getElementById("groupDetailsLegend");
		groupDetailsLegendEl.innerHTML = 'Sub Groups Details Of <font color="#115C06">-*My Groups*-</font>';
		
		var myGroupsLinksDivContent = '';
		myGroupsLinksDivContent+='<table class="groupsCountTable">';
		myGroupsLinksDivContent+='<tr class="head">';
		myGroupsLinksDivContent+='<th class="width">Group</th>';
		myGroupsLinksDivContent+='<th class="width1">Sub Groups</th>';
		myGroupsLinksDivContent+='</tr>';
		for(var i in subGrpsCountInMyGrps)
		{
			myGroupsLinksDivContent+='<tr>';
			myGroupsLinksDivContent+='<td class="width"><a href="javascript:{}" class="link1" onclick="getMyGroupsDetails('+subGrpsCountInMyGrps[i].staticGroupId+',\'new\',\''+subGrpsCountInMyGrps[i].staticGroupName+'\')">'+subGrpsCountInMyGrps[i].staticGroupName+'</a></td>';
			myGroupsLinksDivContent+='<td class="width1">'+subGrpsCountInMyGrps[i].numberOfGroups+'</td>';
			myGroupsLinksDivContent+='</tr>'
			
		myGroupsLinksDivContent+='<div id="'+subGrpsCountInMyGrps[i].staticGroupId+'"></div>';		
		}
		myGroupsLinksDivContent+='</table>';		
		groupDetailsDivEl.innerHTML =  myGroupsLinksDivContent;
	}

	function getSystemGroupsDetails(id,type,flag,name){
		/*
		if(grpMbrsDetailsDataTable)
		{
			grpMbrsDetailsDataTable.destroy();
		}*/

		var showGrpMembersEl = document.getElementById("showGrpMembers");
		showGrpMembersEl.innerHTML = '';
				
		var jsObj= 
		{
			task:"getSelectedStaticGroupCompleteDetails",
			categoryType:"1",
			mainType:type,
			id:id,
			flag:flag,
			name: name	
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupBasicInfoAction.action?"+param;
		callAjax(param,jsObj,url);
		
		}

	function getMyGroupsDetails(id,flag,name)
	{
		var showGrpMembersEl = document.getElementById("showGrpMembers");
		showGrpMembersEl.innerHTML = '';
		
		var jsObj= 
		{
			task:"getSelectedMyGroupCompleteDetails",
			categoryType:"2",
			id:id,
			flag: flag,
			name: name
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupBasicInfoAction.action?"+param;
		callAjax(param,jsObj,url);
		}
	
	function showSystemGroupCompleteDetails(results)
	{
		var myGroupBasicInfo = results.groupBasicDetails;
		var desc = myGroupBasicInfo.desc;
		var groupName = myGroupBasicInfo.groupName;
		var groupId = myGroupBasicInfo.groupId;
		var createdDate = myGroupBasicInfo.createdDate;
		var membersCount = myGroupBasicInfo.membersCount;
		var category = myGroupBasicInfo.category;
		var subGroupsList = results.subGroupDetails;
		var type = "USER_GROUP_CATEGORY_CHILD";
		var membersCount = myGroupBasicInfo.membersCount;
		var divEl = document.getElementById("summaryTextDiv");
		divEl.style.display='block';
		var linksEl = document.getElementById("centerNavLinksDiv");
		var tableEl = document.getElementById("subGroupsListSendSMSTable");
		tableEl.style.display='block';
		numbersArray = results.membersMobileNos;
		var navigationEl = document.getElementById("navigationLink");
		navigationEl.style.display='block';
		var sendSMSButEl = document.getElementById("sendSMSBut");
		sendSMSButEl.setAttribute("onclick","sendSMS()")
		var breadCrumb = results.systemGroupsBCList; 
		var BCString = '';
		for (var i in breadCrumb)
		{
			if(i==0)
			{
				BCString+=''+breadCrumb[i].name+'<img src="images/usergroups/right-arrow.gif" border="none"/>';
			}else
				BCString+='<a href="javascript:{}" id='+breadCrumb[i].id+' onclick="getSystemGroupsDetails('+breadCrumb[i].id+',\''+type+'\',\'remove\',\''+breadCrumb[i].name+'\')" class="navLinks">'+breadCrumb[i].name+'</a><img src="images/usergroups/right-arrow.gif" border="none"/>'; 
		}
		navigationEl.innerHTML = BCString;
		
		if(groupName == 'Media' || groupName == 'Officials' ||groupName == 'NGOs')
		{		
			linksEl.innerHTML = myGroupBasicInfo.groupName + ' Category Details';									
			
		} else {
			linksEl.innerHTML = myGroupBasicInfo.groupName + ' Group Details';			
		}				
		var subGroupsListDivEl = document.getElementById("subGroupsListDiv");
		var subGroupsListContentStr = '';
		subGroupsListContentStr+='<div id="headingDiv">Sub Group Details</div>';
		subGroupsListContentStr+='<table width="100%" class="subGroupsTable">';
		if(subGroupsList == 0)
		{
			subGroupsListContentStr+='<tr>';
			subGroupsListContentStr+='<td><font color="#115C06"><b>No further sub groups!</b></font></td>';
			subGroupsListContentStr+='</tr>';			
		} else {			
		for(var i in subGroupsList)
		{
			subGroupsListContentStr+='<tr>';
			subGroupsListContentStr+='<td>';
			subGroupsListContentStr+='<a href="javascript:{}" class="subGroupLink" onclick="getSystemGroupsDetails('+subGroupsList[i].groupId+',\''+type+'\',\'add\',\''+subGroupsList[i].groupName+'\')">'+subGroupsList[i].groupName+'</a><span class="smalltype" style="margin-left:5px;">'+subGroupsList[i].membersCount+' members, '+subGroupsList[i].subGroupsCount+' sub groups</span>';
			subGroupsListContentStr+='<div class="smalltype">'+subGroupsList[i].desc+'</div>';
			subGroupsListContentStr+='</td>';
			subGroupsListContentStr+='</tr>';
		}		
		}
		subGroupsListContentStr+='</table>';		
		subGroupsListDivEl.innerHTML = subGroupsListContentStr;
		var str='';
		str+='<div id="groupDetailsHead">';
		str+='<table width="100%">';
		str+='<tr><td rowspan="2" width="15%">';
		str+='<img src="images/usergroups/group-default.png" border="none" />';
		str+='</td>';
		str+='<td width="50%">';
		str+='<h4>'+groupName+'</h4>';
		str+='<p>'+desc+'</p>';
		str+='<p>Created Date:'+createdDate+'</p>';
		str+='</td>';

		if(groupName == 'Media' || groupName == 'Officials' ||groupName == 'NGOs')
		{
			str+='<td width="25%" style="color:red;">You can not add members in to System Groups.</td>';
			str+='</tr>';			
				
		} else {
			str+='<td width="25%" >';
			str+='<div class="promoImage">';
			str+='<img src="images/usergroups/profile-default.png" border="none" class="promoImage" border="0">';
			str+='</div>';
			str+='<div class="promoNumber">'+membersCount+'</div>';
			str+='<div class="promoText">Members</div>';		
			str+='</td>';
			str+='</tr>';	
			str+='<tr>';
			str+='<td align="right" colspan="3"><a href="javascript:{}" class="membersLinks" style="" id="viewMembersAnchor" onclick="showGroupMembers('+groupId+')">View Members</a>';
			str+='&nbsp;&nbsp;<a href="#" class="membersLinks" style="" id="addMembersAnchor" onclick="addGrpMembersDialog('+groupId+')">Add Members</a></td>';
			str+='</tr>';							
			}		
		str+='</table>';			
		str+='</div>';
		divEl.innerHTML=str;						
	}

	function showMyGroupCompleteDetails(results)
	{
		var myGroupBasicInfo = results.groupBasicDetails;
		var desc = myGroupBasicInfo.desc;
		var groupName = myGroupBasicInfo.groupName;
		var groupId = myGroupBasicInfo.groupId;
		var createdDate = myGroupBasicInfo.createdDate;
		var membersCount = myGroupBasicInfo.membersCount;
		var category = myGroupBasicInfo.category;
		var subGroupsList = results.subGroupDetails;
		var type = "USER_GROUP_CATEGORY_CHILD";
		var membersCount = myGroupBasicInfo.membersCount;
		var divEl = document.getElementById("summaryTextDiv");
		divEl.style.display='block';
		var linksEl = document.getElementById("centerNavLinksDiv");
		var tableEl = document.getElementById("subGroupsListSendSMSTable");
		tableEl.style.display='block';
		var navigationEl = document.getElementById("navigationLink");
		navigationEl.style.display='block';
		numbersArray = results.membersMobileNos;

		var sendSMSButEl = document.getElementById("sendSMSBut");
		sendSMSButEl.setAttribute("onclick","sendSMS()")
		
		linksEl.innerHTML = myGroupBasicInfo.groupName + ' Group Details';
		var breadCrumb = results.myGroupsBCList; 
		var BCString = '';
		for (var i in breadCrumb)
		{
			BCString+='<a href="javascript:{}" id='+breadCrumb[i].id+' onclick="getMyGroupsDetails('+breadCrumb[i].id+',\'remove\',\''+breadCrumb[i].name+'\')" class="navLinks">'+breadCrumb[i].name+'</a><img src="images/usergroups/right-arrow.gif" border="none"/>'; 
		}
		navigationEl.innerHTML = BCString;						
		var subGroupsListDivEl = document.getElementById("subGroupsListDiv");
		var subGroupsListContentStr = '';
		subGroupsListContentStr+='<div id="headingDiv">Sub Group Details</div>';
		subGroupsListContentStr+='<table width="100%" class="subGroupsTable">';

		if(subGroupsList == 0)
		{
			subGroupsListContentStr+='<tr>';
			subGroupsListContentStr+='<td><font color="#115C06"><b>No further sub groups!</b></font></td>';
			subGroupsListContentStr+='</tr>';
			
		} else {
		for(var i in subGroupsList)
		{
			subGroupsListContentStr+='<tr>';
			subGroupsListContentStr+='<td>';
			subGroupsListContentStr+='<a href="javascript:{}" class="subGroupLink" onclick="getMyGroupsDetails('+subGroupsList[i].groupId+',\'add\',\''+subGroupsList[i].groupName+'\')">'+subGroupsList[i].groupName+'</a><span class="smalltype" style="margin-left:5px;">'+subGroupsList[i].membersCount+' members, '+subGroupsList[i].subGroupsCount+' sub groups</span>';
			subGroupsListContentStr+='<div class="smalltype">'+subGroupsList[i].desc+'</div>';
			subGroupsListContentStr+='</td>';
			subGroupsListContentStr+='</tr>';
		}				
		}
		subGroupsListContentStr+='</table>';
		subGroupsListDivEl.innerHTML = subGroupsListContentStr;
		var str='';
		str+='<div id="groupDetailsHead">';
		str+='<table width="100%">';
		str+='<tr><td rowspan="2" width="15%">';
		str+='<img src="images/usergroups/group-default.png" border="none" />';
		str+='</td>';
		str+='<td width="50%">';
		str+='<h4>'+groupName+'</h4>';
		str+='<p>'+desc+'</p>';
		str+='<p>Created Date:'+createdDate+'</p>';
		str+='</td>';
		str+='<td width="25%">';
		str+='<div class="promoImage">';
		str+='<img src="images/usergroups/profile-default.png" border="none" class="promoImage" border="0">';
		str+='</div>';
		str+='<div class="promoNumber">'+membersCount+'</div>';
		str+='<div class="promoText">Members</div>';		
		str+='</td>';		
		str+='</tr>';
		str+='<tr>';
		str+='<td align="right" colspan="3"><a href="javascript:{}" class="membersLinks" style="" id="viewMembersAnchor" onclick="showGroupMembers('+groupId+')">View Members</a>';
		str+='&nbsp;&nbsp;<a href="javascript:{}" class="membersLinks" style="" id="addMembersAnchor" onclick="addGrpMembersDialog('+groupId+')">Add Members</a></td>';
		str+='</tr>';	
		str+='</table>';			
		str+='</div>';
		divEl.innerHTML=str;
	}
	
	function showGroupMembersList(results)
	{
		assignToGroupMembersArray = new Array();
		var groupMbrsList = results.userGroupMembersList;
		var emptyArray = new Array();
		for(var i in groupMbrsList)
		{
			var memberDetailsObj = { 
			 name: groupMbrsList[i].name, 
			 designation: groupMbrsList[i].designation, 
			 address: groupMbrsList[i].address, 
			 mobile: groupMbrsList[i].mobileNumber,
			 telephoneNo: groupMbrsList[i].phoneNumber, 
			 email: groupMbrsList[i].emailId, 
			 location: groupMbrsList[i].location 
		};
			assignToGroupMembersArray.push(memberDetailsObj);
			userGrpsObj.showGrpMembersArr = assignToGroupMembersArray;
		}
		if(groupMbrsList.length == 0)
		{	
			userGrpsObj.showGrpMembersArr = emptyArray;				
		}
		showGrpMembersDataTable();	
	}

	
	function showSysSubGroupsListInCreateGroupDialog(result,jsObj)
	{		
		var breadCrumb = result.systemGroupsBCList;
		var categoryName = jsObj.name;
		var categoryId = jsObj.categoryId;
		var typeFromReq = jsObj.type;
		var subGroupName =  jsObj.subGrpname
		var subGroupId = jsObj.id;
		var subGroupsList = result.systemGroupsList;
		var subGroupsListDivEl = document.getElementById("subGrpsListInCreateGrpDialog");
		subGroupsListDivEl.style.display = 'block';	
		var type = "USER_GROUP_CATEGORY_CHILD";		
		var navigationDivInDialogEl = document.getElementById("navigationDivInDialog");
		var imgEl = document.createElement("img");

		var BCString = '';
		for (var i in breadCrumb)
		{
			if(i==0)
			{
				BCString+=''+breadCrumb[i].name+'<img src="images/usergroups/right-arrow.gif" border="none"/>';
			}else
				BCString+='<a href="javascript:{}" id='+breadCrumb[i].id+' onclick="getSubGroupsOfStaticGroupParents('+breadCrumb[i].id+',\''+type+'\',\''+breadCrumb[i].name+'\',\'remove\')" class="navLinks">'+breadCrumb[i].name+'</a><img src="images/usergroups/right-arrow.gif" border="none"/>'; 
		}
		navigationDivInDialogEl.innerHTML = BCString;
		var subGroupsListContentStr = '';
		if(categoryName != null && categoryId != null)
		{
		subGroupsListContentStr+='<div id="headingDiv">';
		subGroupsListContentStr+='<table width="100%">';
		subGroupsListContentStr+='<tr>';
		subGroupsListContentStr+='<td width="50%">Sub Groups in '+categoryName+'</td>';
		subGroupsListContentStr+='<td width="50%" align="right"><input id="'+categoryId+'" type="button" value="Create Group in '+categoryName+'" onclick="handleCreateGroupSubmit(null)"/></td>';
		subGroupsListContentStr+='</tr>';
		subGroupsListContentStr+='</table>';
		subGroupsListContentStr+='</div>';			
		} else
		{
			subGroupsListContentStr+='<div id="headingDiv">';
			subGroupsListContentStr+='<table width="100%">';
			subGroupsListContentStr+='<tr>';
			subGroupsListContentStr+='<td width="50%">Sub Groups in '+subGroupName+'</td>';
			subGroupsListContentStr+='<td width="50%" align="right"><input id="'+subGroupId+'" type="button" value="Create Group in '+subGroupName+'" onclick="handleCreateGroupSubmit(this.id)" /></td>';
			subGroupsListContentStr+='</tr>';
			subGroupsListContentStr+='</table>';
			subGroupsListContentStr+='</div>';			
		}	
		subGroupsListContentStr+='<table width="100%" class="subGroupsTable">';
		if(subGroupsList == 0)
		{
			subGroupsListContentStr+='<tr>';
			subGroupsListContentStr+='<td><font color="#115C06"><b>No further sub groups!</b></font></td>';
			subGroupsListContentStr+='</tr>';
			
		} else {
					
				for(var i in subGroupsList)
				{
					subGroupsListContentStr+='<tr>';
					subGroupsListContentStr+='<td>';
					subGroupsListContentStr+='<a href="javascript:{}" class="subGroupLink" onclick="getSubGroupsOfStaticGroupParents('+subGroupsList[i].id+',\''+type+'\',\''+subGroupsList[i].name+'\',\'Add\')">'+subGroupsList[i].name+'</a>';
					subGroupsListContentStr+='</td>';
					subGroupsListContentStr+='</tr>';
				}				
		}
		subGroupsListContentStr+='</table>';
		subGroupsListDivEl.innerHTML = subGroupsListContentStr;
	}
	
	function showMySubGroupsListInCreateGroupDialog(result, jsObj)
	{
		var breadCrumb = result.myGroupsBCList;
		var groupId = jsObj.id;
		var groupName = jsObj.name;
		var subGroupsList = result.myGroupsList;
		var navigationDivInDialogEl = document.getElementById("navigationDivInDialog1");
		var subGroupsListDivEl = document.getElementById("subGrpsListInCreateGrpDialog1");
		subGroupsListDivEl.style.display = 'block';	
		
		var BCString = '';
		for (var i in breadCrumb)
		{
			BCString+='<a href="javascript:{}" id='+breadCrumb[i].id+' onclick="getSubGroupsListInMyGroups(\''+breadCrumb[i].name+'\','+breadCrumb[i].id+',\'remove\')" class="navLinks">'+breadCrumb[i].name+'</a><img src="images/usergroups/right-arrow.gif" border="none"/>'; 
		}
		navigationDivInDialogEl.innerHTML = BCString;
		
		
		var subGroupsListContentStr = '';
			subGroupsListContentStr+='<div id="headingDiv">';
			subGroupsListContentStr+='<table width="100%">';
			subGroupsListContentStr+='<tr>';
			subGroupsListContentStr+='<td width="50%">Sub Groups in '+groupName+'</td>';
			subGroupsListContentStr+='<td width="50%" align="right"><input id="'+groupId+'" type="button" value="Create Group in '+groupName+'" onclick="handleCreateGroupSubmit(this.id)"/></td>';
			subGroupsListContentStr+='</tr>';
			subGroupsListContentStr+='</table>';
			subGroupsListContentStr+='</div>';
			subGroupsListContentStr+='<table width="100%" class="subGroupsTable">';			
		if(subGroupsList == 0)
		{
			subGroupsListContentStr+='<tr>';
			subGroupsListContentStr+='<td><font color="#115C06"><b>No Sub Groups!</b></font></td>';
			subGroupsListContentStr+='</tr>';
			
		} else {
					
				for(var i in subGroupsList)
				{
					subGroupsListContentStr+='<tr>';
					subGroupsListContentStr+='<td>';
					subGroupsListContentStr+='<a href="javascript:{}" class="subGroupLink" onclick="getSubGroupsListInMyGroups(\''+subGroupsList[i].name+'\','+subGroupsList[i].id+',\'add\')">'+subGroupsList[i].name+'</a>';
					subGroupsListContentStr+='</td>';
					subGroupsListContentStr+='</tr>';
				}				
		}
		subGroupsListContentStr+='</table>';
		subGroupsListDivEl.innerHTML = subGroupsListContentStr;
	}

	function getSubGroupsOfStaticGroupParents(id, type, name,flag){
		
		var jsObj= 
		{
			id: id,
			type: type,
			subGrpname: name,
			flag: flag,
			task:"getSubGroupsOfStaticGroupParents"
			
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);		
		
		}
	
	function sendSMS()
	{
		var msgTextElmt = document.getElementById("smsTextArea");
		var message = msgTextElmt.value;
		var jsObj= 
		{
			numbers: numbersArray,
			message:message,	
			task:"sendSMS"
			
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}
	
	function showGrpMembersDataTable()
	{	
		var grpMbrsDetailsColumnDefs = [
									{key: "name", label: "<%=name%>", sortable:true},		
									{key: "designation", label: "<%=designation%>", sortable:true},	
			              	 	    {key: "mobile", label: "<%=mobile%>"},
			              	 	 	//{key: "telephoneNo", label: ""},
			              	 	 	{key: "address", label: "<%=address%>", sortable:true},
			              	 	 	{key: "location", label:"<%=location%>", sortable: true},
			              	 	 	{key: "email", label:"<%=email%>", sortable: true}
			              	 	    ];                	 	    

			var grpMbrsDetailsDataSource = new YAHOO.util.DataSource(userGrpsObj.showGrpMembersArr); 
			grpMbrsDetailsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			grpMbrsDetailsDataSource.responseSchema = {
	                fields: ["name", "designation", "address", "mobile", "email", "location" ] 
	        		};

			var myConfigs = { 
				    paginator : new YAHOO.widget.Paginator({ 
			        rowsPerPage    : 10			        
				    }),
				    caption:"Group Members Details" 
					};
    		
			grpMbrsDetailsDataTable = new YAHOO.widget.DataTable("showGrpMembers", grpMbrsDetailsColumnDefs, grpMbrsDetailsDataSource,myConfigs);
						
	        return { 
	            oDS: grpMbrsDetailsDataSource, 
	            oDT: grpMbrsDetailsDataTable 
	           
	      };	     	
		
	}
	
	function showGroupMembers(id)
	{
		
		var jsObj= 
		{
			task:"getSelectedGroupMembersDetails",
			id:id
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}
	
	function showSearchOptions()
	 	{
			var divEl = document.getElementById("optionsRadio");
			if(divEl.style.display !='block')
			divEl.style.display='block';
			else divEl.style.display='none';	 	
		}

	function buildCreateGroupPopup()
	{
		
		var elmt = document.getElementById('userGroupsMainDiv');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','createGroupmDiv');
		//var breadcrumbArr = new Array();
		var createGroupContentStr='';
		createGroupContentStr+='<div class="hd" align="left">Create New User Group</div>';
		createGroupContentStr+='<div class="bd" align="left">';
		createGroupContentStr+='<div id="userGroupDetailsDivBody">';
		createGroupContentStr+='<div class="section_title">Group Info</div><br>';
		createGroupContentStr+='<div id="userGroupsDialogText">To create a new community group, complete the fields below. Be sure to include a description that will let other community members know just what your group is about!</div><br>';
		createGroupContentStr+='<table class="createGroupTable">';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<th><%=groupName%></th>'; 
		createGroupContentStr+='<td style="padding-left: 15px;"><input type="text" style="width:400px;" id="groupNameText" onkeypress="showAvailabilityBtn()"/></td>';
		createGroupContentStr+='<td style="padding-left: 15px;"><input type="button" id="checkAvailabilityBtn" value="Check Availability!" onclick="checkAvailability(document.getElementById(\'groupNameText\').value)" style="display:none;" /></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td colspan="3"><div id="groupExistsAlert"></div></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<th><%=description%></th>';
		createGroupContentStr+='<td style="padding-left: 15px;" colspan="2"><textarea style="width:400px;" id="descTextArea"></textarea></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='</table>';
		createGroupContentStr+='<br>';
		createGroupContentStr+='<fieldset>';
		createGroupContentStr+='<legend>Create Group As</legend>';
		createGroupContentStr+='<table class="createGroupTable">';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td><input type="radio" id="createNewGrpRadio" name="createGroup" value="Create New Group" onClick="hideGroupSelectionListBoxes()"/><%=createNewGrp%></td>';
		createGroupContentStr+='<td><input type="button" id="addNewGRpButton" value="Add New Group" style="display:none;" disabled="true" onclick="handleCreateGroupSubmit(null)" /></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td><input type="radio" name="createGroup" id="addToGrpAsSubGrpRadio" value="Add To Static Group As Sub Group" onClick="showStaticGroupSelectionBox()"/><%=addToStaticGrpAsSubGrpRadio%></td>';
		createGroupContentStr+='<td><select class="selectWidth" id="staticGrpSelectBox"  disabled="true" name="systemGroups" style="display:none;" onchange="getSubGroupsListInSystemGroups(this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		for(var i in userGrpsObj.systemGroupsListBoxArr)
		{
			createGroupContentStr+='<option value='+userGrpsObj.systemGroupsListBoxArr[i].id+'>'+userGrpsObj.systemGroupsListBoxArr[i].value+'</option>';
		}
		createGroupContentStr+='</select></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td colspan="2">';
		createGroupContentStr+='<div id="navigationDivInDialog"></div>';
		createGroupContentStr+='</td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td colspan="2">';
		createGroupContentStr+='<div id="subGrpsListInCreateGrpDialog" style="display:none;"></div>';
		createGroupContentStr+='</td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td><input type="radio" name="createGroup" id="addToGrpAsSubGrpRadio" value="Add To My Group As Sub Group" onClick="showMyGroupSelectionBox()"/><%=addToGrpAsSubGrpRadio%></td>';
		createGroupContentStr+='<td><select class="selectWidth" id="myGrpSelectBox" name="myGroups" disabled="true" style="display:none;" onchange="getSubGroupsListInMyGroups(this.options[this.selectedIndex].text,this.options[this.selectedIndex].value,\'new\')">';
		for(var j in userGrpsObj.myGroupsListBoxArr)
		{
			createGroupContentStr+='<option value='+userGrpsObj.myGroupsListBoxArr[j].id+'>'+userGrpsObj.myGroupsListBoxArr[j].value+'</option>';
		}
		createGroupContentStr+='</select></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td colspan="2">';
		createGroupContentStr+='<div id="navigationDivInDialog1"></div>';
		createGroupContentStr+='</td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td colspan="2">';
		createGroupContentStr+='<div id="subGrpsListInCreateGrpDialog1" style="display:none;"></div>';
		createGroupContentStr+='</td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='</table>';
		createGroupContentStr+='</fieldset>';		
		createGroupContentStr+='<div id="confirmMsg" class="confirmMsg"></div>';				
		createGroupContentStr+='</div>';
		createGroupContentStr+='</div>';
		createGroupContentStr+='</div>';
		createGroupContentStr+= 
		divChild.innerHTML=createGroupContentStr;		
		elmt.appendChild(divChild);	
		if(createGroupDialog)
			createGroupDialog.destroy();
		createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
				{ width : "750px", 
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:400,
				  y:300,
				  buttons : [ { text:"Exit", handler:handleCreateGroupCancel }]				 
	             }); 
		createGroupDialog.render();
	}
	function checkAvailability(groupName)
	{
		var jsObj= 
		{	
			groupName: groupName,
			task: "checkAvailability"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
		
	}
	function showAvailabilityBtn()
	{
		var checkAvailabilityBtnEl = document.getElementById("checkAvailabilityBtn");
		checkAvailabilityBtnEl.style.display = 'block';		
		}
	
	function getSubGroupsListInSystemGroups(name,id)
	{
		
		var jsObj= 
		{	
			categoryId: id,
			name: name,
			task: "getSubGroupsListInSystemGroups"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}

	function getSubGroupsListInMyGroups(name,id,flag)
	{
		var jsObj=			
		{	
			name: name,
			id: id,
			flag: flag,
			task: "getSubGroupsListInMyGroups"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}
	function hideGroupSelectionListBoxes()
	{
		var addNewGRpButtonEl =  document.getElementById("addNewGRpButton");
		addNewGRpButtonEl.style.display = 'block';
		
		var staticGrpSelectEl = document.getElementById("staticGrpSelectBox");
		staticGrpSelectEl.style.display = 'none';

		var myGrpSelectEl = document.getElementById("myGrpSelectBox");
		myGrpSelectEl.style.display = 'none';	

		var myGrpListSelectEl = document.getElementById("subGrpsListInCreateGrpDialog1");
		myGrpListSelectEl.style.display = 'none';

		var subGrpListSelectEl = document.getElementById("subGrpsListInCreateGrpDialog");
		subGrpListSelectEl.style.display = 'none';

		var navigationDivInDialogEl = document.getElementById("navigationDivInDialog");
		navigationDivInDialogEl.innerHTML = ''; 

		var navigationDivInDialogEl1 = document.getElementById("navigationDivInDialog1");
		navigationDivInDialogEl1.innerHTML = '';
			
	}
	function showStaticGroupSelectionBox()
	{
		var staticGrpSelectEl = document.getElementById("staticGrpSelectBox");
		staticGrpSelectEl.style.display = 'block';

		var myGrpSelectEl = document.getElementById("myGrpSelectBox");
		myGrpSelectEl.style.display = 'none';	

		var sysGrpListSelectEl = document.getElementById("subGrpsListInCreateGrpDialog1");
		sysGrpListSelectEl.style.display = 'none';

		var navigationDivInDialogEl = document.getElementById("navigationDivInDialog");
		navigationDivInDialogEl.innerHTML = '';

		var addNewGRpButtonEl =  document.getElementById("addNewGRpButton");
		addNewGRpButtonEl.style.display = 'none';

		var navigationDivInDialogEl1 = document.getElementById("navigationDivInDialog1");
		navigationDivInDialogEl1.innerHTML = '';
	}
	
	function showMyGroupSelectionBox()
	{
		var staticGrpSelectEl = document.getElementById("staticGrpSelectBox");
		staticGrpSelectEl.style.display = 'none';

		var myGrpSelectEl = document.getElementById("myGrpSelectBox");
		myGrpSelectEl.style.display = 'block';

		var subGrpListSelectEl = document.getElementById("subGrpsListInCreateGrpDialog");
		subGrpListSelectEl.style.display = 'none';
		
		var navigationDivInDialogEl = document.getElementById("navigationDivInDialog");
		navigationDivInDialogEl.innerHTML = '';

		var addNewGRpButtonEl =  document.getElementById("addNewGRpButton");
		addNewGRpButtonEl.style.display = 'none';
		
	}
	function getSubGroupsCountInSystemGrpsForUser()
	{

		var systemGroupsLinkEl = document.getElementById("systemGroups");
		var systemGroupsLinkStyle = systemGroupsLinkEl.getAttribute("class");

		var myGroupsLinkEl = document.getElementById("myGroups");
		var myGroupsLinkStyle = myGroupsLinkEl.getAttribute("class"); 

		if(systemGroupsLinkStyle == "link")
		{
			systemGroupsLinkEl.setAttribute("class","selectedLink");
		}
		if(myGroupsLinkStyle =="selectedLink")
		{
			myGroupsLinkEl.setAttribute("class","link");
		}		
		
		var jsObj= 
		{
			task:"subGrpsCountInSystemGrpsForUser"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}

	function getSubGroupsCountInMyGroupsForUser()
	{

		var systemGroupsLinkEl = document.getElementById("systemGroups");
		var systemGroupsLinkStyle = systemGroupsLinkEl.getAttribute("class");

		var myGroupsLinkEl = document.getElementById("myGroups");
		var myGroupsLinkStyle = myGroupsLinkEl.getAttribute("class"); 

		if(systemGroupsLinkStyle == "selectedLink")
		{
			systemGroupsLinkEl.setAttribute("class","link");
		}
		if(myGroupsLinkStyle =="link")
		{
			myGroupsLinkEl.setAttribute("class","selectedLink");
		}		
		
		var jsObj= 
		{
			task:"subGrpsCountInMyGrpsForUser"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}
	function handleCreateGroupSubmit(id)
	{
		var parentId = id;
			
		var groupNameTextVal = document.getElementById("groupNameText").value;
		var descTextAreaVal = document.getElementById("descTextArea").value;
		var groupCreationOption;
		var staticGrpSelectBoxEl = document.getElementById("staticGrpSelectBox");
		var	staticGrpSelectBoxElVal;
		var myGrpSelectBoxEl = document.getElementById("myGrpSelectBox");
		var	myGrpSelectBoxElVal; 
		var status;
		var categoryType;		
		var parentGroupId;
		var elements = document.getElementsByTagName('input');
		
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="radio" && elements[i].name=="createGroup" && elements[i].checked==true)
				groupCreationOption = elements[i].value;
		}
		if(groupCreationOption == null)
		{
		alert("Please Select Group Creation Option!");
		return;
		} else if(groupCreationOption == "Create New Group")
		{
			staticGrpSelectBoxElVal = null;
			myGrpSelectBoxElVal = null;
			status = "1";
			categoryType = "2";
			parentGroupId = null;
			
			
		} else if(groupCreationOption == "Add To Static Group As Sub Group" && parentId == null)
		{
			staticGrpSelectBoxElVal = staticGrpSelectBoxEl.options[staticGrpSelectBoxEl.selectedIndex].value; 
			myGrpSelectBoxElVal = null;
			status = "2";
			categoryType = "1";
			parentGroupId = null;
			myGrpSelectBoxElVal = null;
		} else if(groupCreationOption == "Add To Static Group As Sub Group" && parentId != null)
		{			
			staticGrpSelectBoxElVal = staticGrpSelectBoxEl.options[staticGrpSelectBoxEl.selectedIndex].value; 
			myGrpSelectBoxElVal = null;
			status = "2";
			categoryType = "1";
			parentGroupId = id;
			myGrpSelectBoxElVal = null;			
		}
		 else if(groupCreationOption == "Add To My Group As Sub Group" && parentId != null)
		{
			staticGrpSelectBoxElVal = null; 
			myGrpSelectBoxElVal = myGrpSelectBoxEl.options[myGrpSelectBoxEl.selectedIndex].value;
			status = "2";
			categoryType = "2";
			parentGroupId = id;
			myGrpSelectBoxElVal = myGrpSelectBoxEl.options[myGrpSelectBoxEl.selectedIndex].value; 
		}
		
		var jsObj={
				groupName: groupNameTextVal,
				groupdDesc: descTextAreaVal,
				staticGroupId: staticGrpSelectBoxElVal,					
				task:"createNewGroup",
				statusVal: status,
				categoryType: categoryType,
				parentGroupId: parentGroupId,
				myGroupId: myGrpSelectBoxElVal	
			  }
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+rparam;		
	callAjax(rparam,jsObj,url);
		
		//createGroupDialog.hide();			
	}
	
	function handleCreateGroupCancel()
	{
		createGroupDialog.hide();
	}

	function showGroupCreationConfirmation(results)
	{
		
		var groupName= results.userGroupDetailsVO.groupName;
		var confirnDivEl = document.getElementById("confirmMsg");
		confirnDivEl.innerHTML = groupName+"is succesfully created" ; 
	}

	function showExistingGroupAlert(results, jsObj)	
	{
		confirmation = results.groupAlreadyExists;
		var groupName = jsObj.groupName;
		var groupExistsAlertEl = document.getElementById("groupExistsAlert");
		var addNewGRpButtonEl = document.getElementById("addNewGRpButton");
		var staticGrpSelectBoxEl = document.getElementById("staticGrpSelectBox");
		var myGrpSelectBoxEl = document.getElementById("myGrpSelectBox");		
		if(confirmation == true)
		{
			groupExistsAlertEl.innerHTML = groupName+" already Exists!Please give another GroupName!";
			groupExistsAlertEl.style.color = "red";
			addNewGRpButtonEl.disabled = true;
			staticGrpSelectBoxEl.disabled = true;
			myGrpSelectBoxEl.disabled = true;			
			
		} else if (confirmation == false)
		{
			groupExistsAlertEl.innerHTML = groupName+" is available!";
			groupExistsAlertEl.style.color = "green";
			addNewGRpButtonEl.disabled = false;
			staticGrpSelectBoxEl.disabled = false;
			myGrpSelectBoxEl.disabled = false;
		}		
	}
		
	function addGrpMembersDialog(id,groupName)
	{
		var elmt = document.getElementById('userGroupsMainDiv');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','addGroupMbrsDiv');
		var addGrpMbrsContent = '';
		addGrpMbrsContent+='<div class="hd" align="left">Add Members To A Group</div>';
		addGrpMbrsContent+='<div class="bd" align="left">';
		addGrpMbrsContent+='<div id="addGroupMbrsDivBody">';
		addGrpMbrsContent+='<table>';
		addGrpMbrsContent+='<tr>';
		addGrpMbrsContent+='<td><%=name%></td>';
		addGrpMbrsContent+='<td style="padding-left: 15px;"><input type="text" size="53" id="grpMbrNameText" name="grpMbrNameText"/></td>';
		addGrpMbrsContent+='</tr>';
		addGrpMbrsContent+='<tr>';
		addGrpMbrsContent+='<td><%=mobile%></td>';
		addGrpMbrsContent+='<td style="padding-left: 15px;"><input type="text" size="53" id="groupMbrMobileText" name="groupMbrMobileText"/></td>';
		addGrpMbrsContent+='</tr>';
		addGrpMbrsContent+='<tr>';
		addGrpMbrsContent+='<td><%=address%></td>'
		addGrpMbrsContent+='<td style="padding-left: 15px;"><input type="text" size="53" id="groupMbrAdrsText" name="groupMbrAdrsText"/></td>';
		addGrpMbrsContent+='</tr>';
		addGrpMbrsContent+='<tr>';
		addGrpMbrsContent+='<td><%=location%></td>'
		addGrpMbrsContent+='<td style="padding-left: 15px;"><input type="text" size="53" id="groupMbrLocText" name="groupMbrLocText"/></td>';
		addGrpMbrsContent+='</tr>';
		addGrpMbrsContent+='<tr>';
		addGrpMbrsContent+='<td><%=email%></td>'
		addGrpMbrsContent+='<td style="padding-left: 15px;"><input type="text" size="53" id="eMailText" name="groupMbrLocText" /></td>';
		addGrpMbrsContent+='</tr>';
		addGrpMbrsContent+='<tr>';
		addGrpMbrsContent+='<td><%=designation%></td>'
		addGrpMbrsContent+='<td style="padding-left: 15px;"><input type="text" size="53" id="groupMbrDesignationText" name="groupMbrLocText"/></td>';
		addGrpMbrsContent+='</tr>';
		addGrpMbrsContent+='<tr>';
		addGrpMbrsContent+='<td style="padding-left: 15px;"><input type="hidden" size="53" id="groupIdText" name="groupMbrLocText" value="'+id+'"/></td>';
		addGrpMbrsContent+='</tr>';
		addGrpMbrsContent+='<tr>';
		addGrpMbrsContent+='<td colspan="2"><div id="confirmAddMember"></div></td>';
		addGrpMbrsContent+='</tr>';
		
		addGrpMbrsContent+='</table>';
		addGrpMbrsContent+='</div>';
		addGrpMbrsContent+='</div>';
		addGrpMbrsContent+='</div>';
		divChild.innerHTML=addGrpMbrsContent;
		elmt.appendChild(divChild);	
		if(addGrpMbrsDialog)
			addGrpMbrsDialog.destroy();
		addGrpMbrsDialog = new YAHOO.widget.Dialog("addGroupMbrsDiv",
				{ width : "600px", 
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:400,
				  y:400,				  
				  buttons : [ { text:"Add", handler: handleAddGrpMbrSubmit, isDefault:true}, 
	                          { text:"Cancel", handler: handleAddGrpMbrCancel}]
	             } ); 
		addGrpMbrsDialog.render();		
	}
	
	function handleAddGrpMbrSubmit()
	{
		var name = document.getElementById("grpMbrNameText").value;
		var mobile = document.getElementById("groupMbrMobileText").value;
		var address = document.getElementById("groupMbrAdrsText").value;
		var location = document.getElementById("groupMbrLocText").value;
		var eMailText = document.getElementById("eMailText").value;
		var designation = document.getElementById("groupMbrDesignationText").value;
		var groupId = document.getElementById("groupIdText").value;
		var jsObj=
		{
				name :name,
				mobile :mobile,
				address :address,
				location :location,
				eMailText :eMailText,
				designation :designation,
				task:"addMemberToAGroup",
				groupId:groupId						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
		
		
	}

	function handleAddGrpMbrCancel()
	{
		this.cancel();
	}
	function showAddedMbrConfirm(results)
	{
		var groupName = results.userGroupMembersVO.groupName;
		var memberName = results.userGroupMembersVO.name;
		var confirmAddMemberEl = document.getElementById("confirmAddMember");
		confirmAddMemberEl.innerHTML = memberName+" is succesfully added in to "+groupName;
		confirmAddMemberEl.style.color='green';

		var grpMbrNameTextEl = document.getElementById("grpMbrNameText");
		grpMbrNameTextEl.value ="";
		var groupMbrMobileTextEl = document.getElementById("groupMbrMobileText");
		groupMbrMobileTextEl.value ="";
		var groupMbrAdrsTextEl = document.getElementById("groupMbrAdrsText");
		groupMbrAdrsTextEl.value ="";
		var groupMbrLocTextEl = document.getElementById("groupMbrLocText");
		groupMbrLocTextEl.value ="";
		var eMailTextEl = document.getElementById("eMailText");
		eMailTextEl.value ="";
		var groupMbrDesignationTextEl = document.getElementById("groupMbrDesignationText");
		groupMbrDesignationTextEl.value ="";
		
				
	}		
</script>
</head>
<body class="yui-skin-sam">
<div id="UserGrpsMainDiv" class="yui-skin-sam">
	
	<div id="userGroupsMainDiv">	
	</div>
	<div id="userGroupsTopDiv">
		<table class="hdTable">
		<tr>
		<td><img src="images/usergroups/group-of-people.jpg" height="200px" width="320px" style="border:1px solid #96B4D3;border-width:1px 3px 3px 1px;"></td>
		<td id="grpsDescTdDiv">
			<h3>User Groups</h3>
			<p>User Groups is a consistent interaction for creating, managing groups
			 and it helps the users for maintaining relationship with people</p>
			&nbsp;Identifying and gathering sets of people within the system to:
			<ul class="uLEleStyle">
				<li type="square">&nbsp;Collaborate</li> 
				<li type="square">&nbsp;Communicate</li>
				<li type="square">&nbsp;Define what activities the group can do together</li>
			</ul>
		</td>
		</tr>
		<tr>
		<td>
			<div id="searchHelp">FIND A GROUP OR GROUP MEMBER !</div>
		</td>
		<td>
			<div id="searchBoxDiv">	
				<s:label for="searchText" id="searchGrpLabel"  value="%{getText('groupSearch')}" theme="simple" styleClass="searchBoxLabels"/>
				<s:textfield id="SearchText" name="lastName" theme="simple" />
				<a href="#" title="Go Search" ><img src="images/usergroups/search.gif" border="none"/></a>
				&nbsp;
				<s:label for="searchText" id="searchMbrsLabel"  value="%{getText('memberSearch')}" theme="simple"/>
				<s:textfield id="SearchText" name="lastName" theme="simple"/>
				<a href="#" title="Go Search" ><img src="images/usergroups/search.gif" border="none" /></a>
				&nbsp;&nbsp;
				<a href="#" title="Go Search" class="uLEleStyle" style="color:#247CD4" onclick="showSearchOptions()">Options</a>
				<div id="optionsRadio" class="uLEleStyle" align="center" style="font-family:verdana; color:#628C2A;display:none">
					Search In
					<input type="radio" value="All" name="groups"/>All Groups
					<input type="radio" value="System Groups" name="groups"/>System Groups
					<input type="radio" value="My Groups" name="groups"/>My Groups					
				</div>				
			</div>	
		</td>
		</tr>
	</table>
	</div>
	<div id="userGroupsLeftDiv">
		<div id="leftNavLinksDiv"><p id="systemGroups" class="link"><a href="#groupDetails" onclick="getSubGroupsCountInSystemGrpsForUser()"><b>System Groups</b></a></p>
		<p id="myGroups" class="link"><a href="#groupDetails" onclick="getSubGroupsCountInMyGroupsForUser()"><b>My Groups</b></a></p>		
		<p id="createNewGrpDiv" class="link"><a href="javascript:{}" onclick="buildCreateGroupPopup()"><b>Create New Group</b></a></p>
		<p id="manageGrpDiv" class="link"><a href="javascript:{}" onclick=""><b>Manage Groups</b></a></p>
		</div>
		<div id="groupsList"><a name="groupDetails"></a>
		<fieldset>
			<legend id="groupDetailsLegend">Group Details</legend>
			<div id="groupsCountDiv"><img src="images/usergroups/group2.jpg" height="200px" width="250px" border="none"></div>						
		</fieldset>
		</div>
		<div id="myProfile">
			<h4>My Profile</h4>
			<table>
			<tr><td><div id="profileImage"><img src="images/usergroups/profile-default.png" border="none" width="80px"/></div></td>
			<td><div id="profileHeader">${sessionScope.UserName}</div></td></tr></table>
				
		</div>
	</div>
	<div id="userGroupsCenterDiv"><a name="userGroupsCenterDiv" class="yui-skin-sam"></a>
	<div id="centerNavLinksDiv"></div>
	<a name="centerLayoutTop"></a>
	<div id="navigationLink" style="display:none;"></div>	
	<div id="summaryTextDiv">
	<p>User Groups feature is mainly used to group your important people in a group according to a category.This application has two types of User Groups.They are</p> 
	<ul>
	<li type="square">System Groups</li>
	<li type="square">My Groups</li>
	</ul>
	<p>You can create any number of sub groups in User Groups</p>
	<p>User Groups SMS feature allows you to send SMS to the members in the groups</p>
	<div id="moreDetailsDiv"><a name="moreDetails"></a>
	<h4>System Groups</h4>
	<p>This application provides some default categories under System Groups.They are</p>
	<ul>
	<li type="square"><b>Media:</b>&nbsp;You can create sub groups of people who are working in Print/Electronic Media in different regional level.Ex: State Level Electronic Media, District Level Print Media etc.</li>
	<li type="square"><b>Officials</b>&nbsp;You can create sub groups of people who are working in several government departments in different regional level</li>
	<li type="square"><b>NGOs</b>&nbsp;You can create sub groups of people who are working in/running a Non Government Organization</li>
	</ul>
	<h4>My Groups</h4>
	<p>You can create your own categories in MyGroups.For Example your sponseror's group, local leaders group etc. </p> 
	</div>
	</div>	
	<div id="subGroupsListSendSMSDiv">
		<table id="subGroupsListSendSMSTable" width="100%" style="display:none;">
		<tr>
		<td width="75%"><div id="subGroupsListDiv"></div></td>
		<td width="25%">	
		<div id="smsDiv">
		<div id="headingDiv">Send SMS</div>
		<p><img src="images/usergroups/icon_mail.png" boder="none"/style="margin-right:2px;">Message:</p>
		<p><textarea id="smsTextArea" cols="10" rows="5"></textarea></p>
		<p align="right"><input id="sendSMSBut" type="submit" class="button" value="Send Sms" name="Submit"/></p>
		</div>
		</td>
		</tr></table>
		
	</div>
	<div id="showGrpMembers"></div>	
</div>
<div id="userGroupsFooter"></div>
</div>
<script type="text/javascript">
<c:forEach var="systemGroups"  items="${staticGroupsListboxOptions}" >
var ob={
			id:'${systemGroups.id}',
			value:'${systemGroups.name}'
		};
userGrpsObj.systemGroupsListBoxArr.push(ob);	
</c:forEach>
<c:forEach var="myGroups"  items="${myGroupsListboxOptions}">
var ob={
			id:'${myGroups.id}',
			value:'${myGroups.name}'
		};
userGrpsObj.myGroupsListBoxArr.push(ob);	
</c:forEach>
buildLayout();

</script>
</body>
</html>

