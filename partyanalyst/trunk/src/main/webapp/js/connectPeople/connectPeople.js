

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
function callAjax(param,jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getAllRequestMessagesForUser")
					{
							showAllRequestMessagesForUser(results);
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

function showAllRequestMessagesForUser(results){
	console.log(results);
	var elmt = document.getElementById("storyBoard_main");

	if(!elmt)
		return;
	var list = results.friendRequest;
	var str = '';
	str += '<div id="totalNumberOfRequestsDIV"> You have '+results.friendRequest.length+' Requests</div>';
	for(var i in list){
		str += '<table width="50%" border=1>';
		str += '<tr>';
		str+='<td>';
		str+='<div id="userFriendRequestInfo">';
		str+='<img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';
		str += '<td colspan="2">'+results.friendRequest[i].data+'</td>';
		str += '</tr>';
		str += '<tr>';
		str += '<td align="left" class="connectPeopleNames"><span>'+results.friendRequest[i].candidateName+'</span></td>';
		str += '<td align="left"><input type="button" value="Accept" class="acceptButton"></input></td>';
		str += '<td align="left"><input type="button" value="Reject"></input></td>';
		str += '</tr>';
		str += '</table>';
	}		

	elmt.innerHTML = str;
}

function getAllRequestMessagesForUser(){
	
	var jsObj=
	{
			task:"getAllRequestMessagesForUser"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getRequestMessagesForUserAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}
function initializeConnectPeople()
{
	initializeTabView();	
	getAllRequestMessagesForUser();
}
