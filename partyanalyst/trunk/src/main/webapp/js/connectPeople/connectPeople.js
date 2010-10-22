

var connectedPeopleInfo = {
							connectPeopleStatus:{
													resultCode:'',													
													exceptionMsg:'',
													isResultPartial:'',
													exceptionClass:''
												},
							connectPeople:[]
						  };

var commentsInfo = {
							connectPeopleStatus:{
													resultCode:'',													
													exceptionMsg:'',
													isResultPartial:'',
													exceptionClass:''
												},
							comments:[]
						  };

var loginUserId = '';


function initializeTabView()
{
	var myTabs = new YAHOO.widget.TabView();
	var str = '';
	str += '<div id="storyBoard_main">';
	str += '<div id="storyBoard_header_Div"></div>';
	str += '<div id="storyBoard_body_Div"></div>';
	str += '<div id="storyBoard_fotter_Div"></div>';
	str += '</div>';

	str += '<div id="messages_main">';
	str += '<div id="messages_header_Div"></div>';
	str += '<div id="messages_body_Div"></div>';
	str += '<div id="messages_fotter_Div"></div>';
	str += '</div>';
	
	myTabs.addTab( new YAHOO.widget.Tab({
		label: 'Story Board',
		content: str,
		active: true
	}));
	 
	myTabs.addTab( new YAHOO.widget.Tab({
		label: 'Connections',
		content: '<div id="connections_main"></div>'
	}));
 
	myTabs.appendTo("connectPeople_connect_center");
	
	
}

function buildInboxMessagesForUser()
{
	var arrData = commentsInfo.comments;
	var arrStatus = commentsInfo.connectPeopleStatus;

	var elmt = document.getElementById("messages_body_Div");
	
	if(!elmt)
		return;
	
	var str = '';
	if(arrStatus.resultCode != "0")
	{
		str += '<fieldset>';
		str += '<legend>Messages</legend>';
		str += '<div> Data could not be retrived due to some technical difficulties</div>';
		str += '</fieldset>';
		elmt.innerHTML = str;
		return;
	}
	else if(arrData.length == 0)
	{
		str += '<fieldset>';
		str += '<legend>Messages</legend>';
		str += '<div> There are no Messages .</div>';
		str += '</fieldset>';		
		elmt.innerHTML = str;
		return;
	}

	str += '<fieldset>';
	str += '<legend>Messages</legend>';
	str += '<div> There are '+arrData.length+' messages</div>';
	str += '<div>';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th>Name</th>';
	str += '<th>Constituency</th>';
	str += '<th>Message</th>';
	str += '</tr>';
	
	for(var i=0;i<arrData.length;i++)
	{
		str += '<tr>';
		str += '<td align="center">'+arrData[i].candidateName+'</td>';
		str += '<td align="center">'+arrData[i].constituencyName+'</td>';
		str += '<td align="center">'+arrData[i].data+'</td>';
		str += '</tr>';
	}
	
	str += '</table>';
	str += '</div>';
	str += '</fieldset>';

	elmt.innerHTML = str;
}


function buildConnectionsTabContent()
{
	var arrData = connectedPeopleInfo.connectPeople;
	var arrStatus = connectedPeopleInfo.connectPeopleStatus;

	var elmt = document.getElementById("connections_main");

	if(!elmt)
		return;
	
	var str = '';

	if(arrStatus.resultCode != "0")
	{
		str += '<div> Data could not be retrived due to some technical difficulties</div>';
		elmt.innerHTML = str;
		return;
	}
	else if(arrData.length == 0)
	{
		str += '<div> There are no connections established till now.</div>';
		elmt.innerHTML = str;
		return;
	}
	
	str += '<div id="connecttion_main_head">';
	str += '<p>You have '+arrData.length+' connections in total</p>';
	str += '</div>';
	str += '<div>';
	str += '<table width="100%" border="0" cellpadding="0" cellspacing="0">';
	str += '<tr>';
	str += '<td width="30%" valign="top">';
	str += '<div id="connection_main_search">';
	str += '</div>';
	str += '</td>';
	str += '<td width="70%" valign="top">';
	str += '<div id="connection_main_data">';
	for(var i=0; i<arrData.length; i++)
	{
		str += '<div>';
		str += '<table>';
		str += '<tr>';
		str += '<td rowspan="3"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"></td>';
		str += '<td valign="top">'+arrData[i].candidateName+'</td>';
		str += '</tr>';
		str += '<tr>';		
		str += '<td valign="top">'+arrData[i].constituencyName+'</td>';
		str += '</tr>';	
		str += '<tr>';		
		str += '<td valign="top" align="left"><a href="javascript:{}" onclick="showMailPopup(\''+arrData[i].id+'\',\''+arrData[i].candidateName+'\')">mail</a></td>';
		str += '</tr>';	
		str += '</table>';
		str += '</div>';
	}
	str += '</div>';
	str += '</td>';
	str += '</tr>';	
	str += '</table>';
	str += '</div>';
	

	elmt.innerHTML = str;
}

function showMailPopup(id,name)
{
	var str = '';	
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th>Message</th>';
	str += '<td><textarea id="connectMessageText" cols="50" rows="4"></textarea></td>';
	str += '</tr>';
	str += '<tr>';	
	str += '<td colspan="2"><input type="button" name="connectButton" value="Send" onclick="sendMessageToConnectedUser(\''+id+'\')"></td>';
	str += '</tr>';
	str += '</table>';
	str	+= '<div id="connectStatus"></div>';
	str	+= '</div>';
	
	var connectPopupPanel = new YAHOO.widget.Dialog("connectPeopleMessagePopup", {      
				 width:'400px',
                 fixedcenter : true, 
                 visible : true,
                 constraintoviewport : true, 
        		 iframe :false,
        		 modal :false,
        		 hideaftersubmit:true,
        		 close:true,
				 draggable:true
       });	 
	
	connectPopupPanel.setHeader("Send Message To "+name);
    connectPopupPanel.setBody(str);
    connectPopupPanel.render();
}

function sendMessageToConnectedUser(userId)
{	
	var connectTextAreaElmt = document.getElementById("connectMessageText");
	var connectMsg = connectTextAreaElmt.value;
	var jsObj ={
				loginUserId:loginUserId,
				meassage:connectMsg,				
				recipientId:userId,
				task:"sendMessageToConnectUser"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "messageToConnectedUser.action?"+rparam;					
	callAjax(rparam,jsObj,url);
}

function showMessageSentConfirmation(results)
{
	var elmt = document.getElementById("connectStatus");

	if(results.resuktCode != 0)
	{
		elmt.innerHTML = '<blink><font color="green">Message Sent Successfully..</font></blink>';
		var t=setTimeout("closeMessagePopup()",2000);
	}
	else
	{
		elmt.innerHTML = 'Message Cannot Be sent Due To Some Technical Difficulties';
	}
}

function closeMessagePopup()
{
	var elmt = document.getElementById("connectPeopleMessagePopup_main");

	if(elmt)
		elmt.innerHTML = '<div id="connectPeopleMessagePopup"></div>';
}


function callAjax(param,jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
									
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getAllRequestMessagesForUser")
					{													
						if(results.friendRequest!=null){
							showAllRequestMessagesForUser(results);
						}else{
							hideRequestDiv();
						}
					}	
					else if(jsObj.task == "acceptRequest")
					{
						showStatus(results);						
						getAllRequestMessagesForUser();
					}
					else if(jsObj.task == "rejectRequest")
					{
						showStatus(results);
						getAllRequestMessagesForUser();						
					}
					else if(jsObj.task == "blockRequest")
					{
						getAllRequestMessagesForUser();						
					}
					else if(jsObj.task == "sendMessageToConnectUser")
					{
						showMessageSentConfirmation(results);
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

function showStatus(results)
{
	var elmt = document.getElementById("storyBoard_header_Div");

	if(!elmt)
		return;
	
	var str = '';
	str += '<div id="statusMessageDIV">';
	if(results.resultCode==0){
		str += '<span style="color:green;font-weight:bold;">Successfuly Connected</span>';	
	}else if(results.resultCode==1){
		str += '<span style="color:red;font-weight:bold;">There was an error in processing your request</span>';
	}		
	str += '</div>';
	elmt.innerHTML = str;
}

function hideRequestDiv()
{
	var elmt = document.getElementById("storyBoard_body_Div");
	
	if(!elmt)
		return;
	
	var str = '';
	str += '<div id="totalNumberOfRequestsDIV"> You have 0 Requests</div>';
	elmt.innerHTML = str;
	
	var elemt = document.getElementById("storyBoard_header_Div");

	if(!elemt)
		return;
	
	var status = '';	
	elemt.innerHTML = status;
}
function showAllRequestMessagesForUser(results){	
	
	var elmt = document.getElementById("storyBoard_body_Div");
	
	if(!elmt)
		return;
	var list = results.friendRequest;
	var str = '';
	
	str += '<div id="totalNumberOfRequestsDIV"> You have '+results.friendRequest.length+' Requests</div>';
	str += '<table width="60%" style="font-family:Verdana;">';
	for(var i in list){		
		str += '<tr>';
		str+='<td>';
		str+='<div id="userFriendRequestInfo">';
		str+='<img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';
		str += '<td colspan="3">'+results.friendRequest[i].data+'</td>';
		str += '</tr>';
		str += '<tr>';
		str += '<td align="left" class="connectPeopleNames"><span>'+results.friendRequest[i].candidateName+'</span></td>';
		str += '<td align="left"><input type="button" onclick="acceptRequest('+results.friendRequest[i].id+')" value="Accept" class="acceptButton"></input></td>';
		str += '<td align="left"><input type="button" onclick="rejectRequest('+results.friendRequest[i].id+')" value="Decline" class="rejectButton"></input></td>';
		str += '<td align="left"><input type="button" onclick="blockRequest('+results.friendRequest[i].id+')" value="Block this person" class="rejectButton"></input></td>';
		str += '</tr>';		
	}		
	str += '</table>';
	elmt.innerHTML = str;
}

function blockRequest(requestId)
{
	var jsObj=
	{		
			type:"block",
			recepientId:requestId,
			task:"blockRequest"								
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;					
	callAjax(rparam,jsObj,url);
}

function acceptRequest(requestId)
{
	var jsObj=
	{		
			type:"accept",
			recepientId:requestId,
			task:"acceptRequest"								
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;					
	callAjax(rparam,jsObj,url);
}

function rejectRequest(requestId)
{
	var jsObj=
	{		
			type:"reject",
			recepientId:requestId,
			task:"rejectRequest"			
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;					
	callAjax(rparam,jsObj,url);
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
	buildInboxMessagesForUser();
	buildConnectionsTabContent();
}
