

function initializeTabView()
{
	var myTabs = new YAHOO.widget.TabView();

	myTabs.addTab( new YAHOO.widget.Tab({
		label: 'Story Board',
		content: '<div id="storyBoard_main"></div>',
		active: true
	}));
	 
	myTabs.addTab( new YAHOO.widget.Tab({
		label: 'Connections',
		content: '<div id="connections_main"></div>'
	}));
 
	myTabs.appendTo("connectPeople_connect_center");
	
	buildConnectionsTabContent();
}

function buildConnectionsTabContent()
{
	var elmt = document.getElementById("connections_main");

	if(!elmt)
		return;

	var str = '';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<td colspan="2"><p>You have 12 connections in total</p></td>';
	str += '</tr>';	
	str += '<tr>';
	str += '<td></td>';
	str += '<td></td>';
	str += '</tr>';
	str += '</table>';

	elmt.innerHTML = str;
}

function initializeConnectPeople()
{
	initializeTabView();	
}
