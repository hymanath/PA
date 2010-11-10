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

</style>

<script type="text/javascript">
	
	function buildLocalGroupsPeopleDataTable()
	{

		<c:forEach var="group" items="${localGroupsPeople}" varStatus ="status">
				var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("peopleDataTable_${status.index}"));
				resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
				resultsDataSource.responseSchema = {
					fields : [ {
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
					} ]
				};

				var resultsColumnDefs = [ {
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
				}];

				var myConfigs = {
				paginator : new YAHOO.widget.Paginator({
					rowsPerPage: 50
				})
			};


			var myDataTable = new YAHOO.widget.DataTable("peopleRegionData_ ${status.index}",resultsColumnDefs, resultsDataSource,myConfigs);  

			</c:forEach>

		
	}

</script>


</head>
<body>

	<div id="localGroupsPeopleData_main">
		<div id="localGroupsPeopleData_head">
			<center>${regionTitle} Details In ${regionName} ${regionType}</center>
		</div>
		<div id="localGroupsPeopleData_body" class="yui-skin-sam">			
			<c:forEach var="group" items="${localGroupsPeople}" varStatus ="status">
				<div id="peopleRegion_main_ ${status.index}" class="peopleDataMain">
					<div id="peopleRegion_head_ ${status.index}" class="scopeWise_head" style="padding:5px;">
						<table>
							<tr>
								<td><img src="images/icons/system_grps.png"></td>
								<td>${group.localUserGroupName} - ${group.groupMembersCount}</td>
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
									<th valign="top">Description</th>
									<td valign="top" colspan="5" align="left">${group.groupDesc}</td>
								</tr>
							</table>
						</div>
						<div id="peopleRegionData_ ${status.index}">
							<table id="peopleDataTable_${status.index}">						
								<c:forEach var="people" items="${group.groupMemberDetails}" varStatus ="status">
									<tr>
										<td>${people.name}</td>
										<td>${people.mobileNumber}</td>
										<td>${people.address}</td>
										<td>${people.location}</td>
										<td>${people.designation}</td>
										<td>${people.emailId}</td>										
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