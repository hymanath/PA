<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Constituency Management</title>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<!-- verify here in case of problem -->	
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script> 
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 

	
	<!-- Dependencies --> 
	<!-- Sam Skin CSS for TabView --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css"> 
	 
	<!-- JavaScript Dependencies for Tabview:  -->
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	 
	<!-- OPTIONAL: Connection (required for dynamic loading of data) --> 
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	 
	<!-- Source file for TabView --> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	
	<!--CSS file (default YUI Sam Skin) --> 
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css"> 
	 
	<!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	 
	<!-- OPTIONAL: JSON Utility (for DataSource) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
	 
	<!-- OPTIONAL: Connection Manager (enables XHR for DataSource) --> 
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	 
	<!-- OPTIONAL: Get Utility (enables dynamic script nodes for DataSource) --> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	 
	<!-- OPTIONAL: Drag Drop (enables resizeable or reorderable columns) --> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	 
	<!-- OPTIONAL: Calendar (enables calendar editors) --> 
	<script type="text/javascript" src="js/yahoo/calendar-min.js"></script> 
	 
	<!-- Source files --> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	
	<!-- Core + Skin CSS --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css"> 
	 
	<!-- Dependencies -->  
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	 
	<!-- Source File --> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script> 
	<!-- YUI Dependency files (End) -->

	

	<style type="text/css">
		#constituencyMgmtHeaderDiv
		{
			color:#1C487A;
			font-size:17px;
			font-weight:bold;
			text-decoration:underline;
		}
		#constituencyMgmtMainDiv
		{
			background-color:#cacaca;
		}
			
		#constituencyMgmtBodyDiv
		{
			height:900px;
			background-color:#cacaca;
		}
		#userGroupsHeadDiv
		{
			padding:5px;
			font-weight:bold;
			background-color:#839DB7;
			height:20px;
			text-align:left;
		}
		#userGroupsMainDiv
		{
			height:100%;
			background-color:#F9FCFF;		
			margin-bottom:5px;
			border:2px solid #95A6B7;
		}
		#userGroupsBodyDiv
		{
			text-align:left;
		}
		#recommLettersHeadDiv ,#districtNewsLinks
		{
			padding:5px;
			font-weight:bold;
			background-color:#839DB7;
			height:20px;
			text-align:left;
		}
		#recommLettersBodyDiv
		{
			text-align:left;
		}
		#problemMgmtHeadDiv
		{	
			padding:5px;
			text-align:left;
			font-weight:bold;
			background-color:#839DB7;
			text-align:left;
		}
		#problemMgmtBodyDiv
		{
			text-align:left;
		}
		.yui-skin-sam .yui-navset .yui-nav, .yui-skin-sam .yui-navset .yui-navset-top .yui-nav 
		{
			text-align:left;
			font-weight:bold;

		}
		.yui-skin-sam .yui-dt th
		{
			background-image:url(images/YUI-images/sprite.png)
		}
		
		#yui-dt0-th-Categorize
		{
			background-color:blue;
		}
	</style>

	<script type="text/javascript">
	function buildConstituencyLayout()
	{
			var layoutEl = new YAHOO.widget.Layout('constituencyMgmtBodyDiv', { 
			units: [	 
					{ 
						position: 'center',
						body: 'problemMgmtMainDiv',							
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: false,						
						animate: false,
						
						

					},
					{
						position: 'bottom',
						body: 'statisticalDataMainDiv',
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: false,						
						animate: false,		
						height: '150'
					}
	    ] 
		}); 
		layoutEl.render();
	}
	
	function buildOuterTabView()
	{
		var outerTab = new YAHOO.widget.TabView("constituencyMgmtTabDiv");
	}
	function buildTabView()
	{	
		var myTabs = new YAHOO.widget.TabView("problemMgmtTabDiv"); 
	        
	}
		
	YAHOO.example.Data = { 
	    
	        problems: [ 
	        {SNo:"1", Title:"Impurified Water Supply", Description: "Polluted water is being supplied since two weeks",IdentifiedDate:new Date(2009, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"2", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 3, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"3", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(1978, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"4", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"}, 
			{SNo:"5", Title:"Delay in payment of Exgratia", Description: "An activist named Ravi died while participating in the in the Rally conducted by the ruling party, but no remuneration is paid to his family from the party ",IdentifiedDate:new Date(1980, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"6", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 13, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"7", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(2009, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"8", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"},
			{SNo:"9", Title:"Polluted Water Supply", Description: "Polluted water is being supplied since two weeks",IdentifiedDate:new Date(2009, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"10", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 3, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"11", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(2009, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"12", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"}, 
			{SNo:"13", Title:"Delay in payment of Exgratia", Description: "An activist named Ravi died while participating in the in the Rally conducted by the ruling party, but no remuneration is paid to his family from the party ",IdentifiedDate:new Date(2009, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"14", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 3, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"15", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(2009, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"16", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"},
			{SNo:"17", Title:"Polluted Water Supply", Description: "Polluted water is being supplied since two weeks",IdentifiedDate:new Date(2009, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"18", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 3, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"19", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(2009, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"20", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"}, 
			{SNo:"21", Title:"Delay in payment of Exgratia", Description: "An activist named Ravi died while participating in the in the Rally conducted by the ruling party, but no remuneration is paid to his family from the party ",IdentifiedDate:new Date(2009, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"22", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 3, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"23", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(2009, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"24", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"},
			{SNo:"25", Title:"Polluted Water Supply", Description: "Polluted water is being supplied since two weeks",IdentifiedDate:new Date(2009, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"26", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 3, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"27", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(2009, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"28", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"}, 
			{SNo:"29", Title:"Delay in payment of Exgratia", Description: "An activist named Ravi died while participating in the in the Rally conducted by the ruling party, but no remuneration is paid to his family from the party ",IdentifiedDate:new Date(2009, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"30", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 3, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"31", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(2009, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"32", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"},
	    ] 
	         
	    
	} 
	/*  Data Table for New Tab*/
	YAHOO.util.Event.addListener(window, "load", function() { 
	 YAHOO.example.TabView = function() { 
	 
			var myColumnDefs = [ 
	            {key:"SNo", formatter:"number", sortable:true}, 
	            {key:"Title", sortable:true}, 
	            {key:"Description"}, 
				{key:"IdentifiedDate", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"Location", sortable:true},	
				{key:"Source"},
				{key:"Scope", editor: new YAHOO.widget.DropdownCellEditor({multiple:true,dropdownOptions:["Village","Mandal","District","State","Country"]})},
				{key:"Problem Type", editor: new YAHOO.widget.CheckboxCellEditor({checkboxOptions:["Categorize"]}),}
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: [{key:"SNo",parser:"number"},"Title","Description","IdentifiedDate","Location","Source", "Status"] 
	        }; 
			/*
			
			var pag = new YAHOO.widget.Paginator({ 
		    rowsPerPage  : 5, 
		    totalRecords : myDataSource.length, 
			containers   : [problemMgmtBodyDiv] // or idStr or elem or [ elem, elem ] 
			}); 
			pag.render(); 
	         */
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("dtcontainer", myColumnDefs, myDataSource,myConfigs); 
	                 
	        var myTabView = new YAHOO.widget.TabView("constituencyMgmtTabDiv"); 
	        myTabView.getTab(0).addListener("click", function() {myDataTable.onShow()});         
			
						
			var highlightEditableCell = function(oArgs) {   
             var elCell = oArgs.target;   
             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
                 this.highlightCell(elCell);   
             } 
			  }; 

			var myTooltip = new YAHOO.widget.Tooltip("myTooltip", { context:"myDataTable", text:"Some tooltip text" } );

			myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);  			
			myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);
			myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
			
	        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable, 
	            oTV: myTabView 
	      }; 
	    }(); 
	}); 

	/*  Data Table for Classfication Tab*/
	YAHOO.util.Event.addListener(window, "load", function() { 
	 YAHOO.example.TabView = function() { 
	 
			var myColumnDefs = [ 
	            {key:"SNo",formatter:"number", sortable:true}, 
	            {key:"Title", sortable:true}, 
	            {key:"Scope"},
				{key:"Concerned Department",},
				{key:"Assign"}
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: [{key:"SNo",parser:"number"},"Title","Scope","Concerned Department","Assign"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("dtcontainer1", myColumnDefs, myDataSource,myConfigs); 
	                 
	        var myTabView = new YAHOO.widget.TabView("problemMgmtBodyDiv"); 
	        myTabView.getTab(0).addListener("click", function() {myDataTable.onShow()});         
	 
	        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable, 
	            oTV: myTabView 
	      }; 
	    }(); 
	}); 

	/*  Data Table for Assign Tab*/
	
	YAHOO.util.Event.addListener(window, "load", function() { 
	 YAHOO.example.TabView = function() { 
	 
			var myColumnDefs = [ 
	            {key:"SNo", sortable:true}, 
	            {key:"Title", sortable:true}, 
	            {key:"Concerned Department",sortable:true},
				{key:"Assigned Official"},	
				{key:"Contact Number"},
				{key:"Progress"},
				{key:"Fix"}
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["SNo","Title","Concerned Department","Progress"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 5 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("dtcontainer2", myColumnDefs, myDataSource,myConfigs); 
	                 
	        var myTabView = new YAHOO.widget.TabView("problemMgmtBodyDiv"); 
	        myTabView.getTab(0).addListener("click", function() {myDataTable.onShow()});         
	 
	        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable, 
	            oTV: myTabView 
	      }; 
	    }(); 
	});
	/*  Data Table for Fix Tab*/
	YAHOO.util.Event.addListener(window, "load", function() { 
	 YAHOO.example.TabView = function() { 
	 
			var myColumnDefs = [ 
	            {key:"SNo", sortable:true}, 
	            {key:"Title", sortable:true}, 
	            {key:"Concerned Department",sortable:true},
				{key:"Assigned Official"},	
				//{key:"Contact Number"},
				{key:"Assigned Date"},
				{key:"Time Taken "}
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["SNo","Title","Concerned Department","Assigned Official","Assigned Date","Time Taken"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 5 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("dtcontainer3", myColumnDefs, myDataSource,myConfigs); 
	                 
	        var myTabView = new YAHOO.widget.TabView("problemMgmtTabDiv"); 
	        myTabView.getTab(0).addListener("click", function() {myDataTable.onShow()});         
	 
	        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable, 
	            oTV: myTabView 
	      }; 
	    }(); 
	});


/* 
{scrollable:true,width:"100%"},
*/


</script>


</head>
<body>
<div id="constituencyMgmtHeaderDiv">
			Constituency Management
</div><br>

<div id="constituencyMgmtMainDiv">	
	
	<div id="constituencyMgmtBodyDiv" class="yui-skin-sam">
		

	
	<div id="statisticalDataMainDiv">
	<div id="statisticalDataHeadDiv"> Statistical data</div>
	</div>

	</div>
</div>

<!--
<div id ="userGroupsMainDiv">
	<div id="userGroupsHeadDiv"><a href="javascript:{}" id="sendSMSAnc" onclick="javascript:{}">User Groups</a></div>
	<div id="recommLettersHeadDiv"><a href="javascript:{}" id="sendSMSAnc" onclick="javascript:{}">Recommendation Letters</a></div>
	<div id="districtNewsLinks"><a href="javascript:{}" id="sendSMSAnc" onclick="javascript:{}">District E-Papers</a></div>
</div>-->


<div id="problemMgmtMainDiv">
			<div id="constituencyMgmtTabDiv" class="yui-navset">
				<ul class="yui-nav"> 
				<li class="selected"><a href="#tab1"><em>Problem Management</em></a></li> 
				<li><a href="#tab2"><em>User Groups</em></a></li> 
				<li><a href="#tab3"><em>Recommendation Letters</em></a></li>
				<li><a href="#tab3"><em>District E-Papers</em></a></li>
		 		</ul>             
				<div class="yui-content"> 
					
					<div id="problemMgmtTabDiv">
						<ul class="yui-nav"> 
						<li class="selected"><a href="#tab1"><em>New</em></a></li> 
						<li><a href="#tab2"><em>Categorized Issues</em></a></li> 
						<li><a href="#tab3"><em>Assigned Issues</em></a></li>
						<li><a href="#tab3"><em>Fixed Issues</em></a></li>
						</ul>             
					<div class="yui-content"> 
						<div id="dtcontainer"></div> 
						<div id="dtcontainer1"></div> 
						<div id="dtcontainer2"></div>
						<div id="dtcontainer3"></div>
					</div>
				</div> 
				
				<div><P>Content for User Groups</P><P></div> 
				<div><P>Content for Recommendation Letters</P></div>
				<div><P>Content for E-Papers</P></div>
		</div>
	<!--
	<div id="problemMgmtHeadDiv">Problem Management</div><BR>
	<div id="problemMgmtBodyDiv" class="yui-navset">
			<ul class="yui-nav"> 
				<li class="selected"><a href="#tab1"><em>New</em></a></li> 
				<li><a href="#tab2"><em>Categorized Issues</em></a></li> 
				<li><a href="#tab3"><em>Assigned Issues</em></a></li>
				<li><a href="#tab3"><em>Fixed Issues</em></a></li>
		 
			</ul>             
			<div class="yui-content"> 
				<div id="dtcontainer">
				<input type="button" label="Categorize"/>
				</div> 
				<div id="dtcontainer1"></div> 
				<div id="dtcontainer2"></div>
				<div id="dtcontainer3"></div>
			</div>
	</div>--> 
</div>
<!--
<div id="statisticalDataMainDiv">
<div id="statisticalDataHeadDiv">Statistical Data</div>
</div>
-->


<script type="text/javascript">
buildConstituencyLayout();
buildOuterTabView();
buildTabView();
var regionLevelCadres = new Array();
		
		/*<c:forEach var="pd1" items="${constituencyManagementVO.problemManagementVO.problemDetails}" >	
											
		</c:forEach>*/
		
//buildTable();
</script>
</body>
</html>