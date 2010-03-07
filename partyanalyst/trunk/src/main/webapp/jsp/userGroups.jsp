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
<!-- YUI Skin Sam -->
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
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
		height:975,
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
			font-family:verdana,arial,helvetica,sans-serif;
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
			font-family:verdana;
			font-weight:bold;	
			border-width:1px 3px 3px 1px;
	}
	#userGroupsLeftDiv, #userGroupsCenterDiv {
		background-color:white;	
	}
	#UserGrpsMainDiv
	{
		
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
			font-family:verdana;			
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
		font-family:verdana;
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
		font-family:verdana;
		font-weight:bold;				
	}
	legend {
		background-color:#9696C0;
		color:#FFFFFF;
		font-family:verdana;
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
	}
	#userGroupsCenterDiv
	{
		border-top:1px solid #96B4D3;
		border-left:1px solid #96B4D3;
		border-right:3px solid #96B4D3;
		border-bottom:3px solid #96B4D3;
		min-height:580px;
		height:580px;
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
	.centerNavLinks {		
		border-right:1px dotted #000000;
		margin:0 0 0.5em;
		padding:0.4em;
		color:#247CD4;
		text-decoration:bold;
		font-family:verdana;				
	}
	#centerNavLinksDiv {			
		background-color:#EBE4F2;
		border:1px dotted #DEDEDE;
		width:"575px";		
		padding:15px;				
	}
	#centerNavLinksDiv a{
		border-right:1px solid #247CD4;
		color:#247CD4;
		text-decoration:none;
		font-weight:bold;
		font-family:verdana;
		padding: 0 6px;		
	}
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
		background-image:url(images/usergroups/group2.jpg);
		width:260px;
		height:200px;		
	}
	.link  {
			color:#247CD4;
			text-decoration:none;
			font-size:15px;
			font-family:verdana;
			font-weight:bold;			
		}
	.link1  {
			color:#115C06;
			text-decoration:none;
			font-size:15px;
			font-family:verdana;
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
			text-align:left;
			}
		#groupDetailsHead h4
		{
			color:#356784;
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
</style>
<script type="text/javascript">
var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String groupName = rb.getString("groupName");
		String description = rb.getString("description"); 
		String createNewGrp = rb.getString("createNewGrp");
		String addToStaticGrpAsSubGrpRadio = rb.getString("addToStaticGrpAsSubGrpRadio");
		String addToGrpAsSubGrpRadio = rb.getString("addToGrpAsSubGrpRadio");
%> }
var createGroupDialog;
var userGrpsObj={
		showGrpMembersArr:[],
		systemGroupsListBoxArr:[],
		myGroupsListBoxArr:[]
};
	function showSubGrpsCountInSystemGrps(results)
	{	
		var subGrpsCountInSystemGrps = results.groupsDetailsForUser;
		var groupDetailsDivEl = document.getElementById("groupsCountDiv");
		var myGroupsLinksDivContent = '';
		myGroupsLinksDivContent+='<ul id="myGroupsList">';
		for(var i in subGrpsCountInSystemGrps)
		{
		myGroupsLinksDivContent+='<li>';	
		myGroupsLinksDivContent+='<a href="#" class="link1" onclick="showUserGroupsDetails()">'+subGrpsCountInSystemGrps[i].staticGroupName+' - '+subGrpsCountInSystemGrps[i].numberOfGroups+'</a>';
		myGroupsLinksDivContent+='</li>';
		myGroupsLinksDivContent+='<div id="'+subGrpsCountInSystemGrps[i].staticGroupId+'"></div>';
		
		}
		myGroupsLinksDivContent+='</ul>';
		groupDetailsDivEl.innerHTML =  myGroupsLinksDivContent;
	}

	function showSubGrpsCountInMyGrps(results)
	{
		var subGrpsCountInMyGrps = results.groupsDetailsForUser;
		var groupDetailsDivEl = document.getElementById("groupsCountDiv");
		var myGroupsLinksDivContent = '';
		myGroupsLinksDivContent+='<ul id="myGroupsList">';
		for(var i in subGrpsCountInMyGrps)
		{
		myGroupsLinksDivContent+='<li type="square">';	
		myGroupsLinksDivContent+='<a href="#" class="link1" onclick="showUserGroupsDetails()">'+subGrpsCountInMyGrps[i].staticGroupName+' - '+subGrpsCountInMyGrps[i].numberOfGroups+'</a>';
		myGroupsLinksDivContent+='</li>';
		myGroupsLinksDivContent+='<div id="'+subGrpsCountInMyGrps[i].staticGroupId+'"></div>';
		
		}
		myGroupsLinksDivContent+='</ul>';
		groupDetailsDivEl.innerHTML =  myGroupsLinksDivContent;
	}

	function showUserGroupsDetails(id)
	{
		var divEl = document.getElementById("moreDetailsDiv");
		divEl.style.display='block';
		var linksEl = document.getElementById("centerNavLinksDiv");

		var linksStr='';
		linksStr+='<a href="#" onclick="showMoreDescription()">View Members</a>';
		linksStr+='<a href="#" onclick="showMoreDescription()">Add Memebers</a>';
		linksStr+='<a href="#" onclick="showMoreDescription()">Send SMS</a>';
		linksStr+='<a href="#" onclick="showMoreDescription()">View SubGroups</a>';

		linksEl.innerHTML = linksStr;
		
		var str='';
		str+='<div id="groupDetailsHead">';
		str+='<table>';
		str+='<tr><td rowspan="2">';
		str+='<img src="images/usergroups/group-default.png" border="none" />';
		str+='</td>';
		str+='<td>';
		str+='<h4>Sponsors Group</h4>';
		str+='<p>This group contains sub groups which contain my sponsors by region level.</p>';
		str+='<p>Created Date:06/March/2009</p>';
		str+='<p>Members In Group:5</p>';
		str+='<Sub Group>';
		str+='<ul>';
		str+='<li type="square"><b>Districtwise Sponsors:</b>&nbsp;this group contains district level sponsors</li>';
		str+='</ul>';
		str+='</td>';
		str+='<td>';
		str+='<div class="promoImage">';
		str+='<img src="images/usergroups/profile-default.png" border="none" class="promoImage" border="0">';
		str+='</div>';
		str+='<div class="promoNumber">5</div>';
		str+='<div class="promoText">Members</div>';		
		str+='</td>';
		str+='</tr>';
		str+='</table>';			
		str+='</div>';

		divEl.innerHTML=str;
		
				
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
		var createGroupContentStr='';
		createGroupContentStr+='<div class="hd" align="left">Create New User Group</div>';
		createGroupContentStr+='<div class="bd" align="left">';
		createGroupContentStr+='<div id="userGroupDetailsDivBody">';
		createGroupContentStr+='<div class="section_title">Group Info</div><br>';
		createGroupContentStr+='<div id="userGroupsDialogText">To create a new community group, complete the fields below. Be sure to include a description that will let other community members know just what your group is about!</div><br>';
		createGroupContentStr+='<table class="createGroupTable">';
		//createGroupContentStr+='<tr></tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<th><%=groupName%></th>'; 
		createGroupContentStr+='<td style="padding-left: 15px;"><input type="text" style="width:400px;" id="groupNameText"/></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<th><%=description%></th>';
		createGroupContentStr+='<td style="padding-left: 15px;"><textarea style="width:400px;" id="descTextArea"></textarea></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='</table>';
		createGroupContentStr+='<br>';
		createGroupContentStr+='<fieldset>';
		createGroupContentStr+='<legend>Create Group As</legend>';
		createGroupContentStr+='<table class="createGroupTable">';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td><input type="radio" id="createNewGrpRadio" name="createGroup" value="Create New Group" onClick="hideGroupSelectionListBoxes()"/><%=createNewGrp%></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td><input type="radio" name="createGroup" id="addToGrpAsSubGrpRadio" value="Add To Static Group As Sub Group" onClick="showStaticGroupSelectionBox()"/><%=addToStaticGrpAsSubGrpRadio%></td>';
		createGroupContentStr+='<td><select class="selectWidth" id="staticGrpSelectBox" name="systemGroups" style="display:none;">';
		for(var i in userGrpsObj.systemGroupsListBoxArr)
		{
			createGroupContentStr+='<option value='+userGrpsObj.systemGroupsListBoxArr[i].id+'>'+userGrpsObj.systemGroupsListBoxArr[i].value+'</option>';
		}
		createGroupContentStr+='</select></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='<tr>';
		createGroupContentStr+='<td><input type="radio" name="createGroup" id="addToGrpAsSubGrpRadio" value="Add To My Group As Sub Group" onClick="showMyGroupSelectionBox()"/><%=addToGrpAsSubGrpRadio%></td>';
		createGroupContentStr+='<td><select class="selectWidth" id="myGrpSelectBox" name="myGroups" style="display:none;">';
		for(var j in userGrpsObj.myGroupsListBoxArr)
		{
			createGroupContentStr+='<option value='+userGrpsObj.myGroupsListBoxArr[j].id+'>'+userGrpsObj.myGroupsListBoxArr[j].value+'</option>';
		}
		createGroupContentStr+='</select></td>';
		createGroupContentStr+='</tr>';
		createGroupContentStr+='</table>';
		createGroupContentStr+='</fieldset>';		
		createGroupContentStr+='</div>';
		createGroupContentStr+='</div>';
		createGroupContentStr+='</div>'; 
		divChild.innerHTML=createGroupContentStr;
		
		elmt.appendChild(divChild);	
		if(createGroupDialog)
			createGroupDialog.destroy();
		createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
				{ width : "600px", 
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:400,
				  y:300,				  
				  buttons : [ { text:"Create", handler: handleCreateGroupSubmit, isDefault:true}, 
	                          { text:"Cancel", handler: handleCreateGroupCancel}]
	             }); 
		createGroupDialog.render();
	}
	
	function hideGroupSelectionListBoxes()
	{
		var staticGrpSelectEl = document.getElementById("staticGrpSelectBox");
		staticGrpSelectEl.style.display = 'none';

		var myGrpSelectEl = document.getElementById("myGrpSelectBox");
		myGrpSelectEl.style.display = 'none';		
	}
	function showStaticGroupSelectionBox()
	{
		var staticGrpSelectEl = document.getElementById("staticGrpSelectBox");
		staticGrpSelectEl.style.display = 'block';

		var myGrpSelectEl = document.getElementById("myGrpSelectBox");
		myGrpSelectEl.style.display = 'none';	
	
	}
	
	function showMyGroupSelectionBox()
	{
		var staticGrpSelectEl = document.getElementById("staticGrpSelectBox");
		staticGrpSelectEl.style.display = 'none';

		var myGrpSelectEl = document.getElementById("myGrpSelectBox");
		myGrpSelectEl.style.display = 'block';
		
		
	}
	function getSubGroupsCountInSystemGrpsForUser()
	{
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
		var jsObj= 
		{
			task:"subGrpsCountInMyGrpsForUser"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/userGroupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}
	function handleCreateGroupSubmit()
	{
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
			
			
		} else if(groupCreationOption == "Add To Static Group As Sub Group")
		{
			staticGrpSelectBoxElVal = staticGrpSelectBoxEl.options[staticGrpSelectBoxEl.selectedIndex].value; 
			myGrpSelectBoxElVal = null;
			status = "2";
			categoryType = "1";
			parentGroupId = null;
			myGrpSelectBoxElVal = null;
		} else if(groupCreationOption == "Add To My Group As Sub Group")
		{
			staticGrpSelectBoxElVal = null; 
			myGrpSelectBoxElVal = myGrpSelectBoxEl.options[myGrpSelectBoxEl.selectedIndex].value;
			status = "2";
			categoryType = "2";
			parentGroupId = null;
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
		
		createGroupDialog.hide();			
	}
	
	function handleCreateGroupCancel()
	{
		this.cancel();
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
		<div id="leftNavLinksDiv">
		<p id="systemGroups" class="link"><a href="#" onclick="getSubGroupsCountInSystemGrpsForUser()"><b>System Groups</b></a></p>
		<p id="myGroups" class="link"><a href="#" onclick="getSubGroupsCountInMyGroupsForUser()"><b>My Groups</b></a></p>		
		<p id="createNewGrpDiv" class="link"><a href="#" onclick="buildCreateGroupPopup()"><b>Create New Group</b></a></p>
		<p id="manageGrpDiv" class="link"><a href="#" onclick=""><b>Manage Groups</b></a></p>
		</div>
		
		<div id="groupsList">
		<fieldset>
			<legend>Group Details</legend>
			<div id="groupsCountDiv"></div>						
		</fieldset>
		</div>
		
		<div id="myProfile">
			<h4>My Profile</h4>
			<table>
			<tr><td><div id="profileImage"><img src="images/usergroups/profile-default.png" border="none" width="80px"/></div></td>
			<td><div id="profileHeader">Raghav</div></td></tr></table>	
		</div>
	</div>
	<div id="userGroupsCenterDiv">
	<div id="centerNavLinksDiv">
	
	<a href="#" onclick="showMoreDescription()">About</a>
	</div>
	<div id="summaryTextDiv">
	<p>User Groups feature is mainly used to group your important people in a group according to a category.This application has two types of User Groups.They are</p> 
	<ul>
	<li type="square">System Groups</li>
	<li type="square">My Groups</li>
	</ul>
	<p>You can create any number of sub groups in User Groups</p>
	<p>User Groups SMS feature allows you to send SMS to the members in the groups</p>
	</div>
	<div id="moreDetailsDiv" style="display:none;">
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
	<div id="userGroupsFooter">
	</div>
</div>
</div>
<script type="text/javascript">
function showMoreDescription(){
	{
		var divEl = document.getElementById("moreDetailsDiv");
		divEl.style.display='block';
 	
	}
	
}
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

