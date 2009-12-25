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

	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/button/assets/skins/sam/button.css"> 
	
	
	

	
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
			margin-bottom:20px;
		}
		#constituencyMgmtMainDiv
		{
			/*background-color:#cacaca;*/
		}
			
		#constituencyMgmtBodyDiv
		{
			height:900px;
			/*background-color:#cacaca;*/
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

		#statisticalDataMainDiv
		{
			background-color:white;
			height:100%;
			width:100%;
			text-align:left;
		}
		#statisticalDataHeadDiv
		{
			padding:5px;
			background-color:#EFEFEF;
		}
		#statisticalDataBodyDiv
		{
			padding:5px;
		}
		#newProblemTabContentDiv_footer,#newProblemTabContentDiv_head
		{
			margin-right:20px;
			text-align:right;
			padding:5px;
		}
		#problemMgmtMainDiv
		{
			height:700px;
		}
		
	</style>

	<script type="text/javascript">

	var outerTab,problemMgmtTabs;
	var problemsMainObj={
							newProblemsArr:[],
							categorizedProblemsArr:[],
							assignedProblemsArr:[],
							fixedProblemsArr:[]
						};


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
						animate: false
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
		outerTab = new YAHOO.widget.TabView();
		
		outerTab.addTab( new YAHOO.widget.Tab({ 
	    label: 'Constituency Management', 
	    content: 'Constituency Management', 
	    active: true 
		})); 

		outerTab.addTab( new YAHOO.widget.Tab({ 
	    label: 'Problem Management', 
	    content: '<div id="problemMgmtTabContentDiv"></div>'	   
		})); 
		
		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: 'User Groups', 
			content: '<div id="userGroupsTabContent">User Groups Content</div>' 
		 
		})); 
		 
		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: 'Recommendation Letters', 
			content: '<div id="recomLettTabContent">Recommendation Letters Content<div>' 
		})); 

		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: 'District E Papers', 
			content: '<div id="distEPapersTabContent">District E Papers Content' 
		})); 

		outerTab.appendTo('problemMgmtMainDiv'); 
	}
	function buildProblemMgmtTabView()
	{	
		problemMgmtTabs = new YAHOO.widget.TabView(); 
		
		var newTabContent='<div id="newProblemTabContentDiv">';
		newTabContent+='<div id="newProblemTabContentDiv_head"></div>';
		newTabContent+='<div id="newProblemTabContentDiv_body"></div>';
		newTabContent+='<div id="newProblemTabContentDiv_footer"></div>';
		newTabContent+='</div>';

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
	    label: 'Classified Issues', 
	    content: newTabContent, 
	    active: true 
		})); 
	 
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Assigned Issues', 
			content: '<div id="categorizedTabContentDiv"></div>' 
		 
		})); 
		 
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Progress Issues', 
			content: '<div id="assignedIssuesContentDiv"></div>' 
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Pending Issues', 
			content: '<div id="pendingIssuesContentDiv"></div>' 
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Fixed Issues', 
			content: '<div id="fixedIssuesContentDiv"></div>' 
		})); 

		problemMgmtTabs.appendTo('problemMgmtTabContentDiv'); 
	      
			var oButton = new YAHOO.widget.Button({ 
	                                            id: "mybuttonid",  
	                                            type: "button",  
	                                            label: "Categorize",  
	                                            container: "newProblemTabContentDiv_footer"  
	                                        }); 

			var oButton = new YAHOO.widget.Button({ 
												id: "reportNewProblem",  
												type: "link",  
												label: "Add New Problem",
												href:"problemManagementAction.action",
												container: "newProblemTabContentDiv_head"  
												}); 
	}
	
	YAHOO.example.Data = { 
	    
	        problems: [ 
	        {SNo:"<input type='checkbox' id='check_1'></input>", Title:"Impurified Water Supply", Description: "Polluted water is being supplied since two weeks",IdentifiedDate:new Date(2009, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}, 
			{SNo:"<input type='checkbox' id='check_1'></input>", Title:"No Bus Service", Description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", IdentifiedDate:new Date("January 3, 2009"),
			Location:"NagaVaram",Source:"Victim",Status:"Assigned"}, 
	        {SNo:"<input type='checkbox' id='check_1'></input>", Title:"No White Ration Cards issued in Hamlet", Description: "White Ration card is not at all issued to eligible families even after the preliminary process", IdentifiedDate:new Date(1978, 11, 12),Location:"Eluru",Source:"Call Centre",Status:"Categorized"}, 
	        {SNo:"<input type='checkbox' id='check_1'></input>", Title:"AarogyaSri",Description: "Delay for Cardiac Surgery with AarogyaSri Scheme", IdentifiedDate:new Date("March 11,2009") , Location:"Eluru",Source:"User",Status:"Categorized"}, 
			{SNo:"<input type='checkbox' id='check_1'></input>", Title:"Delay in payment of Exgratia", Description: "An activist named Ravi died while participating in the in the Rally conducted by the ruling party, but no remuneration is paid to his family from the party ",IdentifiedDate:new Date(1980, 2, 4), Location:"MadanaPalle",Source:"Party Analyst",Status:"New"}		
	    ] 
	         
	    
	} 

	
	function buildNewProblemsDataTable()
	{
		
		var myColumnDefs = [ 
	            {key:"SNo"}, 
	            {key:"Title", sortable:true}, 
	            {key:"Description"}, 
				{key:"IdentifiedDate", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"Location", sortable:true},	
				{key:"Source"},
				{key:"Scope", editor: new YAHOO.widget.DropdownCellEditor({multiple:true,dropdownOptions:["Village","Mandal","District","State","Country"]})},
				{key:"Problem Type", editor: new YAHOO.widget.CheckboxCellEditor({checkboxOptions:["Categorize"]})}
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["SNo","Title","Description","IdentifiedDate","Location","Source", "Status"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("newProblemTabContentDiv_body", myColumnDefs, myDataSource,myConfigs); 
	                 
	        
			problemMgmtTabs.getTab(0).addListener("click", function() {myDataTable.onShow()});         
						
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
	            oDT: myDataTable 
	           
	      }; 
	    
	}
	
	
	function buildcategorizedDataTable()
	{
			var myColumnDefs = [ 
	            {key:"SNo"}, 
	            {key:"Title", sortable:true}, 
	            {key:"Description"}, 
				{key:"IdentifiedDate", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"Location", sortable:true},	
				{key:"Source"},
				{key:"Scope", editor: new YAHOO.widget.DropdownCellEditor({multiple:true,dropdownOptions:["Village","Mandal","District","State","Country"]})},
				{key:"Problem Type", editor: new YAHOO.widget.CheckboxCellEditor({checkboxOptions:["Assign"]})}
	        ]; 
	 
	   	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["SNo","Title","Description","IdentifiedDate","Location","Source", "Status"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("categorizedTabContentDiv", myColumnDefs, myDataSource,myConfigs); 
	                 
	       
	        problemMgmtTabs.getTab(1).addListener("click", function() {myDataTable.onShow()});         
	 
	        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable 	            
	      }; 
	   
	}

	function buildAssignedIssuesdataTable()
	{
			var myColumnDefs = [ 
	            {key:"SNo"}, 
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
	            new YAHOO.widget.DataTable("assignedIssuesContentDiv", myColumnDefs, myDataSource,myConfigs); 
	                 
	       
	       problemMgmtTabs.getTab(2).addListener("click", function() {myDataTable.onShow()});         
	 
	        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable	           
	      }; 
	   
	}

</script>


</head>
<body>
<div id="constituencyMgmtHeaderDiv">
			Constituency Management
</div>

<div id="constituencyMgmtMainDiv">	
	
	<div id="constituencyMgmtBodyDiv" class="yui-skin-sam"></div>
	<div id="statisticalDataMainDiv">
		<div id="statisticalDataHeadDiv"> Statistical Data </div>
		<div id="statisticalDataBodyDiv"> Statistical Data Content</div>
	</div>
	<div id="problemMgmtMainDiv">		
	</div>
</div>



<script type="text/javascript">

buildConstituencyLayout();
buildOuterTabView();
buildProblemMgmtTabView();


<c:forEach var="problem"  items="${constituencyManagementVO.problemManagementVO.problemDetails}" >	
	var newProblemObj=	{
							SNo:'<input type="text"></input>',
							title:'${problem.definition}',
							description:'${problem.description}',
							identifiedDate:new Date('${problem.identifiedDate}'),
							problemLocation:'${problem.location}',
							source:'${problem.source}'
						};
	problemsMainObj.newProblemsArr.push(newProblemObj);											
</c:forEach>

buildNewProblemsDataTable();
buildcategorizedDataTable();
buildAssignedIssuesdataTable();	
		

</script>
</body>
</html>