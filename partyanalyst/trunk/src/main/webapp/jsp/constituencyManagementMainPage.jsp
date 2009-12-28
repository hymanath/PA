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
	<!-- TreeView source file -->  
	<script src="http://yui.yahooapis.com/2.8.0r4/build/treeview/treeview-min.js" ></script>  
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 

	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/button/assets/skins/sam/button.css"> 
	<!-- Required CSS --> 
	<link type="text/css" rel="stylesheet" href="http://yui.yahooapis.com/2.8.0r4/build/treeview/assets/skins/sam/treeview.css"> 	
	
	

	
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
		.selectWidth
		{
			width:120px;
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

	var locationDetails={
							stateArr:[],
							districtArr:[],
							constituencyArr:[],
							mandalArr:[],
							villageArr:[]
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
	/*
	function buildConstituencyTabLayout()
	{	
		var layoutEl = new YAHOO.widget.Layout('constituencyMgmtTabContentDiv', { 
			units: [	
					{
						position: 'left',
						body: 'constMgmtTabCenterDiv',
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: false,						
						animate: false,		
						width: '200',
						height:'100'
						
					},
					{ 
						position: 'center',
						body: 'constMgmtTabLeftDiv',							
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: false,						
						animate: false
						
					}
					
	    ] 
		}); 
		
		layoutEl.render();
	}*/
	function buildSelectOption(results)
	{
		var taskValue= YAHOO.lang.JSON.parse(results.task);

		var selectedValue=taskValue.reportLevel;
		var selectedElmt;
		if(selectedValue=="state")
		{
			selectedElmt=document.getElementById("districtField");
		}	
		else if(selectedValue=="district")
		{
			selectedElmt=document.getElementById("constituencyField");
		}

		var len=selectedElmt.length;			
		for(i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}	
		for(var val in results.namesList)
		{			
			var opElmt=document.createElement('option');
			opElmt.value=results.namesList[val].id;
			opElmt.text=results.namesList[val].name;
			
			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}			
		}
	}
	
	function callAjax(param){
		var myResults;
 		var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+param;			
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);								
								buildSelectOption(myResults);									
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
	
	function getnextList(name,value,choice)
	{

		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:name,
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			callAjax(rparam);
	}

	function getConstituencyList(name,value,choice)
	{
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:"constituency",
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			callAjax(rparam);
	}
	
	function buildOuterTabView()
	{
		outerTab = new YAHOO.widget.TabView();		
		var constTabContent ='<div id="constituencyMgmtTabContentDiv">';		
		constTabContent+='<div id="constMgmtTabContentDiv_head" align="left">';
		constTabContent+='<table width="100%">';
		constTabContent+='<tr>';
		constTabContent+='<td>State</td>';
		constTabContent+='<td><select id="stateField" class="selectWidth" name="state" onchange="getnextList(this.name,this.options[this.selectedIndex].value,false)" width="10">';
		for(var i in locationDetails.stateArr)
		{
			constTabContent+='<option value='+locationDetails.stateArr[i].id+'>'+locationDetails.stateArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		//constTabContent+='</tr>';
		//constTabContent+='<tr>';
		constTabContent+='<td>District</td>';
		constTabContent+='<td><select id="districtField" class="selectWidth" name="district"  onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.districtArr)
		{
			constTabContent+='<option value='+locationDetails.districtArr[i].id+'>'+locationDetails.districtArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		//constTabContent+='</tr>';
		//constTabContent+='<tr>';
		constTabContent+='<td>Constituency</td>';
		constTabContent+='<td><select id="constituencyField" class="selectWidth">';
		for(var i in locationDetails.constituencyArr)
		{
			constTabContent+='<option value='+locationDetails.constituencyArr[i].id+'>'+locationDetails.constituencyArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='</tr>';
		constTabContent+='<tr>';
		constTabContent+='<td>Mandal</td>';
		constTabContent+='<td><select class="selectWidth">';
		for(var i in locationDetails.mandalArr)
		{
			constTabContent+='<option value='+locationDetails.mandalArr[i].id+'>'+locationDetails.mandalArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		//constTabContent+='</tr>';
		//constTabContent+='<tr>';
		constTabContent+='<td>Village</td>';
		constTabContent+='<td><select class="selectWidth">';
		for(var i in locationDetails.villageArr)
		{
			constTabContent+='<option value='+locationDetails.villageArr[i].id+'>'+locationDetails.villageArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='</tr>';
		constTabContent+='</table>';				
		constTabContent+='</div>';
		constTabContent+='<br>';
		constTabContent+='<div id="constMgmtTabContentDiv_body"></div>';
		constTabContent+='<div id="constMgmtTabContentDiv_footer"></div>';
		constTabContent+='</div>';
		outerTab.addTab( new YAHOO.widget.Tab({ 
	    label: 'Constituency Management', 
	    content:constTabContent, 
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
			content: '<div id="recomLettTabContent">Recommendation Letters Content</div>' 
		})); 

		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: 'District E Papers', 
			content: '<div id="distEPapersTabContent">District E Papers Content</div>' 
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
	    label: 'New Issues', 
	    content:newTabContent, 
	    active: true 
		})); 
		
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Classified Issues', 
			content: '<div id="classifiedTabContentDiv"><p>Content for Classified Issues</p></div>' 
		 
		})); 
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Assigned Issues', 
			content: '<div id="categorizedTabContentDiv"></div>' 
		 
		})); 
		 
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Progress', 
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

	function buildContentMgmtTabView()
	{	
		contentMgmtTabs = new YAHOO.widget.TabView(); 

		contentMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Local Leaders',
			content: '<div id="localLeadersTabContent">Local Leaders Content</div>',
			active: true
		}));

		contentMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Local Problems',
			content: '<div id="localProblemsTabContent">Local Problems Content</div>'
			
		}));

		contentMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Local Cast Statistics',
			content: '<div id="localCastTabContent">Local Cast Statistical Data</div>'
			
		}));

		contentMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Local Polictical Changess',
			content: '<div id="localPoliticalChangesTabContent">Local Political Changes Content</div>'
			
		}));

		contentMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Voters By Location/Booth',
			content: '<div id="votersByLocationTabContent">Voters By Location Content</div>'
			
		}));

		contentMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: ' Impacted Voters ',
			content: '<div id="impactedVoters">Impacted Voters</div>'
			
		}));
				
		contentMgmtTabs.appendTo('constMgmtTabContentDiv_body');
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
	<!-- 
	<div id="constituencyMgmtTabContentDiv"></div>
	<div id="constMgmtTabLeftDiv"><p>Left Content</p></div>
	<div id="constMgmtTabCenterDiv"><p>Center Content</p></div>
		-->	
</div>



<script type="text/javascript">

<c:forEach var="state"  items="${stateList}" >
var ob={
			id:'${state.id}',
			value:'${state.name}'
		};
locationDetails.stateArr.push(ob);	
</c:forEach>


<c:forEach var="district"  items="${districtList}" >
var ob={
			id:'${district.id}',
			value:'${district.name}'
		};
locationDetails.districtArr.push(ob);	
</c:forEach>


<c:forEach var="constituency"  items="${constituencyList}" >
var ob={
			id:'${constituency.id}',
			value:'${constituency.name}'
		};
locationDetails.constituencyArr.push(ob);	
</c:forEach>
<c:forEach var="mandal"  items="${mandalList}" >
var ob={
			id:'${mandal.id}',
			value:'${mandal.name}'
		};
locationDetails.mandalArr.push(ob);	
</c:forEach>
<c:forEach var="village"  items="${villageList}" >
var ob={
			id:'${village.id}',
			value:'${village.name}'
		};
locationDetails.villageArr.push(ob);	
</c:forEach>
buildConstituencyLayout();
buildOuterTabView();
//buildConstituencyTabLayout();
buildProblemMgmtTabView();
buildContentMgmtTabView();



	
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