<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Groups</title>
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

 

<script type="text/javascript">
function callAjax(param,jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);
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
		min-height:560px;
		height:560px;
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
</style>
<script type="text/javascript">
	function showSearchOptions()
	 	{
			var divEl = document.getElementById("optionsRadio");
			if(divEl.style.display !='block')
			divEl.style.display='block';
			else divEl.style.display='none';	 	
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
		<p id="systemGroups" class="link"><a href="#" onclick="getSystemGroupsForUser()"><b>System Groups</b></a></p>
		<p id="myGroups" class="link"><a href="#" onclick="getGroupsCreatedByUser()"><b>My Groups</b></a></p>		
		<p id="createNewGrpDiv" class="link"><a href="#" onclick="buildCreateGroupPopup()"><b>Create New Group</b></a></p>
		<p id="manageGrpDiv" class="link"><a href="#" onclick="buildCreateGroupPopup()"><b>Manage Groups</b></a></p>
		</div>
		
		<div id="groupsList">
		<fieldset>
			<legend>Group Details</legend>
			<img src="images/usergroups/user_groups_details.jpg" border="none" height="200px" width="260px"/>			
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
buildLayout();
</script>
</body>
</html>

