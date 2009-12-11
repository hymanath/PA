<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Management </title>

	<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js" ></script>	
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/element-min.js" ></script>		
	<script type="text/javascript" src="js/yahoo/animation-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/resize-min.js" ></script>	  
	<script type="text/javascript" src="js/yahoo/layout-min.js" ></script>	

	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/connection/connection-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/button/button-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/container/container-min.js"></script> 

	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/dom/dom-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/yahoo/yahoo-min.js"></script> 

	<link href="styles/yuiStyles/resize.css" rel="stylesheet" type="text/css" />
	<link href="styles/yuiStyles/layout.css" rel="stylesheet" type="text/css" /> 

	
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/assets/skins/sam/resize.css"> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/assets/skins/sam/layout.css">
	
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/button/assets/skins/sam/button.css"> 

	<!-- Combo-handled YUI CSS files: -->

	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2009.11.09-19/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">	

	<script type="text/javascript" src="http://yui.yahooapis.com/3.0.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/gallery-2009.11.09-19/build/gallery-accordion/gallery-accordion-min.js"></script>

	<!--CSS file (default YUI Sam Skin) --> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/calendar/assets/skins/sam/calendar.css"> 
	<!-- Source file --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/calendar/calendar-min.js"></script> 

	<!-- YUI Dependency files (End) -->

<style type="text/css">

	#CadreManagementHeaderMain
	{
		color:#1C487A;
		font-size:17px;
		font-weight:bold;
		text-decoration:underline;
	}
	#cadreManagementMainDiv
	{
		background-color:#EFEFEF;
		height:900px;
		margin-left:25px;
		margin-right:25px;
		text-align:left;
	}

	#cadreSMSGroupsMainDiv
	{
		height:100%;
		width:100%;
		background-color:#CBDCE8;		
	}
	
	#cadreDetailsMainDiv
	{
		height:100%;
		width:100%;
		background-color:#CBDCE8;
	}	

	#cadreTeamsMainDiv
	{
		height:99%;		
		width:99%;
	}

	#cadreEventsCalMainDiv
	{
		height:100%;
		width:100%;
		background-color:#CBDCE8;
	}

	#cadreDatesYUICalDiv
	{
		width:97%;
	}

	#cadreImpDatesDiv
	{
		float:left;
		height:200px;
		width:100%;
		background-color:#F9FCFF;
		overflow:auto;
	}
	#cadreImpEventsDiv
	{
		float:right;
		height:200px;
		width:100%;
		background-color:#F9FCFF;	
	}

	#cadreImpDatesHeadDiv, #cadreImpEventsHeadDiv, #cadreSMSHeadDiv, #cadreGroupsHeadDiv
	{
		padding:5px;
		text-align:center;
		font-weight:bold;
		background-color:#839DB7;
		height:20px;
	}

	#cadreImpDatesBodyDiv, #cadreImpEventsBodyDiv
	{
		height:170px;
		overflow:auto;		
	}
	
	#cadreSMSMainDiv, #cadreGroupsMainDiv
	{
		height:48%;
		background-color:#F9FCFF;		
		margin-bottom:5px;
		border:2px solid #95A6B7;
	}
	
	#cadreSMSBodyDiv
	{
		height:100px;
	}
	#cadreSMSFooterDiv
	{
		padding:5px;
		text-align:right;		
	}
	
	.yui-skin-sam .yui-calendar
	{
		width:100%;
	}
	#cadreEventsCalMainDiv .yui-calcontainer 
	{
		float:none;
	}


</style>

<script type="text/javascript">
	var smsDialog;

	function buildLayout()
	{
		var layoutEl = new YAHOO.widget.Layout('cadreManagementMainDiv', { 
			units: [ 	        
					{ 
						position: 'right',
						width: 300,	
						height:500,
						header: 'Cadre Teams',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,
						body: 'cadreTeamsMainDiv',
						animate: true
					},
					{ 
						position: 'bottom', 
						height: 500,
						header: 'Cadre Events', 
						body: 'cadreEventsCalMainDiv',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true }, 
					{
						position: 'left',
						width: 200,
						height:500,
						header: 'Cadre SMS',
						body: 'cadreSMSGroupsMainDiv',
						resize: false, 
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true  
					}, 
					{ 
						position: 'center',
						header: 'Cadre Details',
						body: 'cadreDetailsMainDiv',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true
					} 
	    ] 
		}); 
		layoutEl.render(); 
	}

	function buildCadreTeamsAccrodian()
	{
		//----------------------
		 YUI().use( 'gallery-accordion', function(Y) {
		
		var accordion = new Y.Accordion( {
		contentBox: "#cadreTeamsAccordianDiv",
		useAnimation: true,
		collapseOthersOnExpand: true
		});
	 
		accordion.render();

		var item1, item2, item3;
		 
		item1 = new Y.AccordionItem( 
		{
			label: " Campaign Teams",
			expanded: true,
			contentBox: "dynamicContentBox1",
			contentHeight: 
			{
				method: "fixed",
				height: 250
			},
			closable: false
		} );
	 		
		item1.set( "bodyContent","campaign Teams");

		accordion.addItem( item1 );


		item2 = new Y.AccordionItem( 
		{
			label: " Booth Level Teams",
			expanded: false,
			contentBox: "dynamicContentBox1",
			contentHeight: 
			{
				method: "fixed",
				height: 250
			},
			closable: false
		} );
	 		
		item2.set( "bodyContent","boothLevel Teams");

		accordion.addItem( item2 );


		item3 = new Y.AccordionItem( 
		{
			label: " Feed Back Teams",
			expanded: false,
			contentBox: "dynamicContentBox1",
			contentHeight: 
			{
				method: "fixed",
				height: 250
			},
			closable: false
		} );
	 		
		item3.set( "bodyContent","Feed Back Teams");

		accordion.addItem( item3 );
		 });
	}

	function dateToLocaleString(dt, cal)
	{ 
		var wStr = cal.cfg.getProperty("WEEKDAYS_LONG")[dt.getDay()]; 
		var dStr = dt.getDate(); 
		var mStr = cal.cfg.getProperty("MONTHS_LONG")[dt.getMonth()]; 
		var yStr = dt.getFullYear(); 
		return (wStr + ", " + dStr + " " + mStr + " " + yStr); 
	} 
	 
	function mySelectHandler(type,args,obj)
	{ 
		var selected = args[0]; 
		var selDate = this.toDate(selected[0]); 

		var calDateResult = dateToLocaleString(selDate, this);

		var divElmt = document.createElement('div');
		divElmt.setAttribute('id',calDateResult);
		divElmt.innerHTML="SELECTED: " + calDateResult;
	
		var elmt = document.getElementById("cadreImpEventsBodyDiv");
		if(elmt)
		{
			elmt.appendChild(divElmt);
		}
	     
	}; 

	function buildCalendarControl()
	{
		var navConfig = { 
	      strings : { 
	          month: "Choose Month", 
	          year: "Enter Year", 
	          submit: "OK", 
	          cancel: "Cancel", 
	          invalidYear: "Please enter a valid year" 
	      }, 
	      monthFormat: YAHOO.widget.Calendar.SHORT, 
	      initialFocus: "year" 
	}; 

		var cal1 = new YAHOO.widget.Calendar("cadreDatesYUICalDiv", {navigator:navConfig}); 
		cal1.selectEvent.subscribe(mySelectHandler, cal1, true); 
		cal1.render(); 
	}
	
	function buildSMSPopup()
	{
		smsDialog = new YAHOO.widget.Dialog("myDialog",  
	            { width : "300px", 
	              fixedcenter : true, 
	              visible : false,  
	              constraintoviewport : true, 
	              buttons : [ { text:"Submit", handler:handleSubmit, isDefault:true }, 
	                          { text:"Cancel", handler:handleCancel } ] 
	             } );
		
	}

	function showSendSMSPopup()
	{
		// Instantiate the Dialog 
		
		smsDialog.show(); 
	}
	
	var handleSubmit = function() { 
	    alert("submit"); 
	}; 
	var handleCancel = function() { 
	    alert("Cancel"); 
	}; 
</script>
</head>
<body>
	
	<div id="CadreManagementHeaderMain">
		Cadre Management
	</div>
	<div id="cadreManagementMainDiv" class="yui-skin-sam">		
	</div>
	
	<!--<div id="myDialog" class="yui-skin-sam"> 
	    <div class="hd">Please enter your information</div> 
	    <div class="bd"> 
	        <form name="dlgForm" method="POST" action="php/post.php"> 
	            <p>Please enter your personal contact information:</p> 
	            <label for="firstname">First Name:</label><input type="text" name="firstname" /> 
	            <label for="lastname">Last Name:</label><input type="text" name="lastname" /> 
	        </form> 
	    </div> 
	</div> -->

	<div id="cadreSMSGroupsMainDiv">
		<div id="cadreSMSMainDiv">
			<div id="cadreSMSHeadDiv">Cadre SMS</div>
			<div id="cadreSMSBodyDiv">Cadre SMS feature enables the user to send SMS to the cadres based on the location and cadre level</div>
			<div id="cadreSMSFooterDiv">
				<!--<a href="javascript:{}" onclick="showSendSMSPopup()">Send SMS</a>-->
				<a href="cadreSMSAction.action">Send SMS</a>
			</div>
		</div>
		<div id="cadreGroupsMainDiv">
			<div id="cadreGroupsHeadDiv">Cadre Groups</div>
			<div id="cadreGroupsBodyDiv"></div>
		</div>
	</div>
	<div id="cadreDetailsMainDiv">Cadre Details</div>		
	<div id="cadreTeamsMainDiv">
		<div id="cadreTeamsAccordianDiv"></div>
	</div>
	<div id="cadreEventsCalMainDiv">
		<div id="cadreDatesYUICalDiv">
		
		</div>
		<div id="cadreEventsDatesMain">
			<table width="100%">
				<tr>
					<td width="50%">
						<div id="cadreImpDatesDiv">
							<div id="cadreImpDatesHeadDiv"> Imp Dates Header</div>
							<div id="cadreImpDatesBodyDiv"> Imp Dates Content </div>
						</div>
					</td>
					<td width="50%">
						<div id="cadreImpEventsDiv">
							<div id="cadreImpEventsHeadDiv"> Imp Events Header</div>
							<div id="cadreImpEventsBodyDiv"> Imp Events Content</div>
						</div>
					</td>
				</tr>
			</table>
		</div>		
	</div>
	
	

	<script type="text/javascript">
		buildLayout();
		buildCadreTeamsAccrodian();
		buildCalendarControl();	
		//buildSMSPopup();
	</script>

	
</body>
</html>