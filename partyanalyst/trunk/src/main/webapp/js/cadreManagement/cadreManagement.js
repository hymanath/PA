

/*===========================================
	
	cadreManagement.js  is to provide the provide the functionality
	of displaying Cadre Management functionality.This JS file contains code of creating 
	layout and managing the cadre management.

=============================================*/



/*========================================================================
***************** Start Of CadreManagement Object *******************
=========================================================================*/


var cadreMgmtMain = {};


/*========================================================================
***************** End Of CadreManagement Object *******************
=========================================================================*/


/*
	******** Function to build the basic layout for the cadre management page.
*/
function buildCadreManagementLayout()
{
	var layoutEl = new YAHOO.widget.Layout('cadreManagementMainDiv', { 
		units: [ 	        
				{ 
					position: 'right',
					header:false,
					width: 300,	
					height:525,						
					resize: false,
					gutter: '5px',
					collapse: false,
					scroll: true,
					body: 'cadreTeamsMainDiv',
					animate: true
				},
				{ 
					position: 'bottom', 
					height: 525,
					header: 'Important Dates And Event Management', 
					body: 'cadreEventsCalMainDiv',
					resize: false,
					gutter: '5px',
					collapse: true,
					scroll: true,						
					animate: true }, 
				{
					position: 'left',
					header:false,
					width: 270,
					height:525,						
					body: 'cadreSMSGroupsMainDiv',
					resize: false, 
					gutter: '5px',
					collapse: false,
					scroll: true,						
					animate: true  
				}, 
				{ 
					position: 'center',						
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


/*
	*****************
	Function to build the Accordian pane for building associate gorups. 
	*****************
*/
function buildAssociateGroupsAccrodian()
{
	
}

/*
	*****************
	Function to build the Accordian pane for Cadre Teams. 
	*****************
*/

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
			height: 305
		},
		closable: false
	} );
		
	item1.set( "bodyContent","Campaign Teams");

	accordion.addItem( item1 );


	item2 = new Y.AccordionItem( 
	{
		label: " Booth Level Teams",
		expanded: false,
		contentBox: "dynamicContentBox1",
		contentHeight: 
		{
			method: "fixed",
			height: 305
		},
		closable: false
	} );
		
	item2.set( "bodyContent","Booth Level Teams");

	accordion.addItem( item2 );


	item3 = new Y.AccordionItem( 
	{
		label: " Feed Back Teams",
		expanded: false,
		contentBox: "dynamicContentBox1",
		contentHeight: 
		{
			method: "fixed",
			height: 305
		},
		closable: false
	} );
		
	item3.set( "bodyContent","Feed Back Teams");

	accordion.addItem( item3 );
	 });
}
function initializeCadreManagement()
{
	buildCadreManagementLayout();
	buildAssociateGroupsAccrodian();
	buildCadreTeamsAccrodian();
}