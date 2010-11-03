<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
<title>Influence People here</title>

	<style type="text/css">

		body
		{			
			direction:ltr;
			font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
			font-size:11px;
			margin:0;
			padding:0;
		}

		#influencePeopleData_body
		{
			padding:40px;
			text-align:left;
		}
		
		#influencePeopleData_body .yui-pg-container 
		{
			margin:6px 60px;
			text-align:right;
		}

	</style>


	<script type="text/javascript">
		
		function buildDataTable()
		{
			<c:forEach var="region" items="${influencingPeopleDetailsVO}" varStatus ="status">
				var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("peopleDataTable_${status.index}"));
				resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
				resultsDataSource.responseSchema = {
					fields : [ {
						key : "firstName"
					}, {
						key : "lastName"
					}, {
						key : "email"
					}, {
						key : "mobile",parser:"number"
					}, {
						key : "gender"
					}, {
						key : "cast"
					} , {
						key : "constituencyName"
					}, {
						key : "districtName"
					}, {
						key : "stateName"
					} ]
				};

				var resultsColumnDefs = [ {
					key : "firstName",
					label : "First Name",
					sortable : true
				}, {
					key : "lastName",
					label : "Second Name",
					sortable : true
				}, {
					key : "email",
					label : "Email",
					sortable : true
				}, {
					key : "mobile",
					parser:"number",
					label : "Mobile",
					sortable : true
				}, {
					key : "gender",
					label : "Gender",
					sortable : true
				}, {
					key : "cast",
					label : "Cast",
					sortable : true
				}, {
					key : "constituencyName",
					label : "Constituency",
					sortable : true
				} , {
					key : "districtName",
					label : "District",
					sortable : true
				} , {
					key : "stateName",
					label : "State",
					sortable : true
				} ];

				var myConfigs = {
				paginator : new YAHOO.widget.Paginator({
					rowsPerPage: 50
				})
			};


			var myDataTable = new YAHOO.widget.DataTable("peopleRegion_body_ ${status.index}",resultsColumnDefs, resultsDataSource,myConfigs);  

			</c:forEach>

		}

	</script>
</head>
<body>
	<div id="influencePeopleData_main">

		<div id="influencePeopleData_head">
			
		</div>
		<div id="influencePeopleData_body" class="yui-skin-sam">			
			<c:forEach var="region" items="${influencingPeopleDetailsVO}" varStatus ="status">
				
				<div id="peopleRegion_head_ ${status.index}" class="scopeWise_head">
					<table>
						<tr>
							<td><img src="images/icons/system_grps.png"></td>
							<td>${region.regionName} ${region.regionType}</td>
						</tr>
					</table>
				</div>
				<div id="peopleRegion_body_ ${status.index}">
					<table id="peopleDataTable_${status.index}">						
						<c:forEach var="people" items="${region.influencingPeopleDetails}" varStatus ="status">
							<tr>
								<td>${people.firstName}</td>
								<td>${people.lastName}</td>
								<td>${people.email}</td>
								<td>${people.mobile}</td>
								<td>${people.gender}</td>
								<td>${people.cast}</td>
								<td>${people.constituency}</td>
								<td>${people.district}</td>
								<td>${people.state}</td>
							</tr>							
						</c:forEach>
					</table>
				</div>
			</c:forEach>
		</div>		
	</div>
		
	<script type="text/javascript">
		
		buildDataTable();
	</script>
</body>
</html>