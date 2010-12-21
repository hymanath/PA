

var connectedPeopleInfo = {
							connectPeopleStatus:{
													resultCode:'',													
													exceptionMsg:'',
													isResultPartial:'',
													exceptionClass:''
												},
							connectPeople:[]
						  };

var peopleYouMayKnowInfo = {
							connectPeopleStatus:{
													resultCode:'',													
													exceptionMsg:'',
													isResultPartial:'',
													exceptionClass:''
												},
							peopleYouMayKnow:[]
						  };

var commentsInfo = {
							connectPeopleStatus:{
													resultCode:'',													
													exceptionMsg:'',
													isResultPartial:'',
													exceptionClass:''
												},
							comments:[],
							totalMsgCount:'',
							unreadMsgCount:''
						  };

var loginUserId = '';
var stateId = '';
var stateName = '';
var districtId = '';
var districtName = '';
var constituencyId = '';
var constituencyName = '';

var districtConnectCount = '';
var constituencyConnectCount = '';

var postedApprovedReasons = [];
var postedRejectedReasons = [];
var postedNotConsideredReasons = [];

var postedApprovedProblems = [];
var postedRejectedProblems = [];
var postedNotConsideredProblems = [];

var selectedmessage = null;

var connectStatus = [];
var constituencies = [];

function initializeTabView()
{
	var myTabs = new YAHOO.widget.TabView();

	myTabs.addTab( new YAHOO.widget.Tab({
		label: 'Posted Reasons/Problems',
		content: '<div id="postedDiv_main"><div id="postedReasons_main"></div><div id="postedProblems_main"></div></div>',
		active: true
	}));

	var str = '';
	str += '<div id="storyBoard_main">';
	str += '<div id="storyBoard_header_Div"></div>';
	str += '<div id="storyBoard_body_Div"></div>';
	str += '<div id="storyBoard_fotter_Div"></div>';
	str += '</div>';

	myTabs.addTab( new YAHOO.widget.Tab({
		label: 'Story Board',
		content: str,
		active: false
	}));
	
	var tabLable = 'Inbox';
	if(commentsInfo.unreadMsgCount > 0)
		tabLable = tabLable+' ('+commentsInfo.unreadMsgCount+')';
	
	myTabs.addTab( new YAHOO.widget.Tab({
		label: tabLable,
		content: '<div id="inboxMessages_main"></div>'
	}));
	

	myTabs.appendTo("connectPeople_messages_center");
	
	
}

function buildInboxMessagesForUser()
{
	var arrData = commentsInfo.comments;
	var arrStatus = commentsInfo.connectPeopleStatus;

	var elmt = document.getElementById("inboxMessages_main");
	
	if(!elmt)
		return;
	
	var str = '';
	if(arrStatus.resultCode != "0")
	{
		str += '<div id="messagesCountMessage">';
		str += '<table><tr>';
		str += '<td width="35px"><img height="25" width="25" src="images/icons/candidatePage/contact.png"></td>';
		str += '<td>There are 0 messages.</td>';
		str += '</tr></table>';
		str += '</div>';		
		str += '<div id="messagesContent">';
		str += 'Data could not be retrived due to some technical difficulties.';
		str += '</div>';	
		elmt.innerHTML = str;
		return;
		
	}
	else if(arrData.length == 0)
	{
		str += '<div id="messagesCountMessage">';
		str += '<table><tr>';
		str += '<td width="35px"><img height="25" width="25" src="images/icons/candidatePage/contact.png"></td>';
		str += '<td>There are 0 messages.</td>';
		str += '</tr></table>';
		str += '</div>';		
		str += '<div id="messagesContent">';
		str += 'No messages has been sent to you.';
		str += '</div>';	
		elmt.innerHTML = str;
		return;
	}
	
	str += '<div id="messagesCountMessage">';
	str += '<table><tr>';
	str += '<td width="35px"><img height="25" width="25" src="images/icons/candidatePage/contact.png"></td>';
	str += '<td>Unread: '+commentsInfo.unreadMsgCount+'</TD><TD> Total Messages:'+commentsInfo.totalMsgCount+'</td>';
	str += '</tr></table>';
	str += '</div>';
	str += '<div id="messagesContent">';
	
	for(var i=0;i<arrData.length;i++)
	{
		str += '<div class="msgDiv_main">';
		if(arrData[i].status == "UNREAD")
			str += '<div id="msgDivHead_'+i+'" onclick="getMessageContent(\''+i+'\',\''+arrData[i].status+'\',\''+arrData[i].customMsgId+'\')" style="font-weight:bold;" class="msgDiv_head">';
		else
			str += '<div id="msgDivHead_'+i+'" onclick="getMessageContent(\''+i+'\',\''+arrData[i].status+'\',\''+arrData[i].customMsgId+'\')" class="msgDiv_head">';
		
		str += '<table width="100%" id="messagesContentTable" cellpadding="0" cellspacing="0">';
		str += '<tr>';
		str += '<td width="25%" align="left">'+arrData[i].candidateName+'</td>';	
		str += '<td width="50%" align="left">'+arrData[i].data+'</td>';
		str += '<td width="25%" align="left">'+arrData[i].postedDate+'</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '<div id="msgDivBody_'+i+'" class="msgDiv_body" style="display:none;">';
		str += '<div>'+arrData[i].msg+'</div>';
		str += '<div style="text-align:right;" id="replyDiv_'+i+'"><a href="javascript:{}" onclick="showMailPopup(\''+arrData[i].recepientId+'\',\''+arrData[i].candidateName+'\',\'Message\')">Reply</a></div>';
		str += '</div>';
		str += '</div>';
	}
	
	str += '</div>';

	elmt.innerHTML = str;
}

function buildReplyPanel(id, index){
	var str = '';
	str += '<textarea>';
	str += '</textarea>';
	var divEle = document.getElementById("replyDiv_"+index);
	divEle.innerHTML = str;
}

function getMessageContent(index,status,msgId)
{	
	var headElmt = document.getElementById("msgDivHead_"+index);
	var bodyElmt = document.getElementById("msgDivBody_"+index);
	
	if(selectedmessage != null && selectedmessage != "msgDivBody_"+index)
	{
		var displayElmt = document.getElementById(selectedmessage);	
		if(displayElmt && displayElmt.style.display == "block")
			displayElmt.style.display = "none";
	}
	
	selectedmessage = "msgDivBody_"+index;
	
	if(headElmt.style.fontWeight == 'bold')
		headElmt.style.fontWeight = 'normal';

	
	if(bodyElmt.style.display == "none")	
		$("#msgDivBody_"+index).slideDown();	
	else if(bodyElmt.style.display == "block")
		$("#msgDivBody_"+index).slideUp();
	
	if(status == "UNREAD")
		markMessageAsRead(msgId);
}

function markMessageAsRead(msgId)
{
	var jsObj=
	{		
			customMasgId:msgId,
			task:"updateReadMessageInDB"								
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateReadMessageInDBAction.action?"+rparam;					
	callAjax(jsObj,url);
}

function buildConnectionsContentForUser()
{
	var arrData = connectedPeopleInfo.connectPeople;
	var arrStatus = connectedPeopleInfo.connectPeopleStatus;

	var elmt = document.getElementById("connectPeople_connect_center");

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
	
	str += '<div id="connection_main_head">';
	str += '<table>';
	str += '<tr>';
	str += '<td width="40px"><img src="images/icons/indexPage/group_icon.png"></td>';
	str += '<td>You have total '+arrData.length+' connections.</td>';
	str += '</tr>';
	str += '</table>';	
	str += '</div>';
	str += '<div id="connecttion_main_body">';
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
		str += '<td valign="top" align="left"><a href="javascript:{}" onclick="showMailPopup(\''+arrData[i].id+'\',\''+arrData[i].candidateName+'\',\'Message\')">Send a Message</a></td>';
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

function showMailPopup(id,name,type)
{
	var str = '';	
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th>Message</th>';
	str += '<td><textarea id="connectMessageText" cols="35" rows="4"></textarea></td>';
	str += '</tr>';
	str += '<tr>';	
	str += '<td colspan="2"><input type="button" name="connectButton" value="Send" onclick="sendMessageToConnectedUser(\''+id+'\',\''+type+'\')"></td>';
	str += '</tr>';
	str += '</table>';
	str	+= '<div id="connectStatus"></div>';
	str	+= '</div>';
	
	/*var connectPopupPanel = new YAHOO.widget.Dialog("connectPeopleMessagePopup", {      
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
    connectPopupPanel.render();*/

	$( "#connectPeoplePopup" ).dialog({
			title:"Send Message to "+name,
			autoOpen: true,
			show: "blind",
			width: 400,
			minHeight:300,
			modal: true,
			hide: "explode"
		});
	
	var elmt = document.getElementById("allConnectedUsersDisplay_main");
	if(elmt)
		elmt.innerHTML = str;
}

function sendMessageToConnectedUser(userId,type)
{	
	var connectTextAreaElmt = document.getElementById("connectMessageText");
	var connectMsg = connectTextAreaElmt.value;
	var jsObj ={
				loginUserId:loginUserId,
				message:connectMsg,				
				recipientId:userId,
				type:type,
				task:"sendMessageToConnectUser"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "messageToConnectedUser.action?"+rparam;					
	callAjax(jsObj,url);
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


function callAjax(jsObj,url){
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
						if(jsObj.type=="Connect"){
							location.reload(true);//For refreshing the page...
						}else{
							showMessageSentConfirmation(results);
						}
					}else if(jsObj.task == "updateReadMessageInDB"){
						updatedInfo(results);
					}
					else if(jsObj.task == "getAllPostedReasonsStatusUser")
					{
						showPostedReasons(jsObj,results);
					}
					else if(jsObj.task == "getAllPostedProblemsByUser")
					{
						showPostedProblems(jsObj,results);
					}
					else if(jsObj.task == "getAllConnectedUsers")
					{	
						showAllConnectedUsersInPanel(jsObj,results);
					}
					else if(jsObj.task == "getAllConnectedUsersByFilterView")
					{
						showAllConnectedUsersInPanelByFilterView(jsObj,results);
					}
					else if(jsObj.task == "connectUserSet")
					{
						showAllConnectedUsersStatus(jsObj,results);
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

function updatedInfo(results){
	return;
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
	callAjax(jsObj,url);
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
	callAjax(jsObj,url);
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
	callAjax(jsObj,url);
}

function getAllRequestMessagesForUser(){
	
	var jsObj=
	{
			task:"getAllRequestMessagesForUser"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getRequestMessagesForUserAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function buildQuickRegionAccessContent()
{	
	var profElmt = document.getElementById("connectPeople_editProfile_center");
	var qElmt = document.getElementById("connectPeople_quickAccess_center");
	var connectCountElmt = document.getElementById("connectPeople_count_center");
	
	if(!profElmt || !qElmt || !connectCountElmt)
		return;

	var pStr = '<div style="text-align:right;margin-right:15px;"><a style="color:#73787E;font-weight:bold;text-decoration:none;" href="anonymousUserAction.action?userId='+loginUserId+'">Edit Profile</a><img style="border: 0px none ; text-decoration: none;" src="images/icons/edit.png"></div>';
	profElmt.innerHTML = pStr;

	var str = '';
	str += '<div id="regionAccessDiv_main">';
	str += '<div id="regionAccessDiv_head">';
	str += '<table cellspacing="0" cellpadding="0" border="0" style="width: 100%;">';
	str += '<tr>';
	str += '	<td width="10px"><img height="36" width="30" src="images/icons/districtPage/header_left.gif"></td>';
	str += '	<td width="125px"><div style="padding: 11px; width: 255px;" class="districtPageRoundedHeaders_center"><span>View Your Location Details </span></div></td>';
	str += '	<td><img height="36" width="5" src="images/icons/districtPage/header_right.gif"></td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';
	str += '<div id="regionAccessDiv_body" class="regionBodyClass">';
	str += '<table id="regionAccessTable">';
	str += '<tr>';
	str += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th>View Your State</th>';
	str += '<td> - </td>';
	str += '<td><a href="statePageAction.action?stateId='+stateId+'">'+stateName+'</a></td>';
	str += '</tr>';
	str += '<tr>';
	str += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th>View Your District</th>';
	str += '<td> - </td>';
	str += '<td><a href="districtPageAction.action?districtId='+districtId+'&districtName='+districtName+'">'+districtName+'</a></td>';
	str += '</tr>';
	str += '<tr>';
	str += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th>View Your Constituency</th>';
	str += '<td> - </td>';
	str += '<td><a href="constituencyPageAction.action?districtId='+districtId+'&constituencyId='+constituencyId+'">'+constituencyName+'</a></td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';
	str += '</div>';

	qElmt.innerHTML = str;

	var cStr = '';
	cStr += '<div id="connectCountDiv_main">';
	cStr += '<div id="connectCountDiv_head">';
	cStr += '	<table cellspacing="0" cellpadding="0" border="0" style="width: 100%;">';
	cStr += '	<tr>';
	cStr += '		<td width="10px"><img height="36" width="30" src="images/icons/districtPage/header_left.gif"></td>';
	cStr += '		<td width="125px"><div style="padding: 11px; width: 255px;" class="districtPageRoundedHeaders_center"><span>View connected people </span></div></td>';
	cStr += '		<td><img height="36" width="5" src="images/icons/districtPage/header_right.gif"></td>';
	cStr += '	</tr>';
	cStr += '	</table>';
	cStr += '</div>';
	cStr += '<div id="connectCountDiv_body" class="regionBodyClass">';	
	cStr += '<table id="regionAccessTable">';
	cStr += '<tr>';
	cStr += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	cStr += '<th>People Registered To '+constituencyName+' Constituency</th>';
	cStr += '<td> - </td>';
	cStr += '<td><a href="javascript:{}" onclick="showAllConnectPeopleWindow(\''+constituencyId+'\',\''+constituencyName+'\',\''+loginUserId+'\',\'CONSTITUENCY\')">'+constituencyConnectCount+'</a></td>';
	cStr += '</tr>';
	cStr += '<tr>';
	cStr += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	cStr += '<th>People Registered To '+districtName+' District</th>';
	cStr += '<td> - </td>';
	cStr += '<td><a href="javascript:{}" onclick="showAllConnectPeopleWindow(\''+districtId+'\',\''+districtName+'\',\''+loginUserId+'\',\'DISTRICT\')">'+districtConnectCount+'</a></td>';
	cStr += '</tr>';	
	cStr += '</table>';
	cStr += '</div>';
	cStr += '</div>';

	connectCountElmt.innerHTML = cStr;
}

function buildPeopleYouMayKnowContent()
{
	
	var elmt = document.getElementById("connectPeople_PeopleMayKnow_center");

	if(!elmt)
		return;
	
	var str = '';	
	str += '<div id="peopleMayKnow_main">';
	str += '<div id="peopleMayKnow_head"> People You may Know &nbsp;&nbsp;&nbsp;&nbsp;</div>';
	str += '<div id="peopleMayKnow_body">';
	str += '	<div class="peopleMayKnow_data">';
	str += '	<table width="100%" class="peopleMayKnow_data_table">';
	
	var arrData = peopleYouMayKnowInfo.peopleYouMayKnow;
	for(var i=0; i<arrData.length; i++)
	{
		str += '	<tr>';
		str += '	<td rowspan="3"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"></td>';
		str += '	<td colspan="2" style="font-weight:bold;">'+arrData[i].candidateName+'</td>';
		str += '	</tr>';
		str += '	<tr>';	
		str += '	<td colspan="2">'+arrData[i].constituencyName+'</td>';
		str += '	</tr>';	
		str += '	<tr>';	
		str += '	<td>'+arrData[i].state+'</td>';
		str += '	<td>';
		str += '		<table>';
		str += '				<tr>';
		str += '					<td>';
		str += '						<img height="15" width="15" src="images/icons/plusNew.png">';
		str += '					</td>';
		str += '					<td align="right">';
		str += '<a class="peopleConnectAnc"  href="javascript:{}" onclick="showMailPopup(\''+arrData[i].id+'\',\''+arrData[i].candidateName+'\',\'Connect\')">Connect</a>';		
		str += '					</td>';
		str += '				</tr>';
		str += '		</table>';
		str += '	</td>';
		str += '	</tr>';		
	}
	
	str += '	</table>';
	str += '	</div>';
	if(arrData.length!=0){
		str += '	<div id="peopleMayKnow_buttonDiv">';
		str += '	<a href="javascript:{}"> View All </a>';
		str += '	</div>';	
	}else{
		str += '	<div style="text-align:left;padding:5px 20px 5px 5px;">';
		str += '	Right now there are no friend suggestion for ';
		str += '	we will get back with more suggesstions as soon as possible.. ';
		str += '	</div>';	
	}	
	str += '</div>';
	str += '</div>';

	elmt.innerHTML = str;
}

function showPostedProblems(jsObj,results)
{
	var elmt = document.getElementById("postedProblems_main");
	
	if(results == null || results.length == 0)
	{
		results = [];
	}		
	else
	{
		for(var i=0; i<results.length; i++)
		{
			if(results[i].isApproved == "true")
				postedApprovedProblems.push(results[i]);
			else if(results[i].isApproved == "rejected")
				postedRejectedProblems.push(results[i]);
			else if(results[i].isApproved == "false")
				postedNotConsideredProblems.push(results[i]);
		}
	}

	var str = '';
	str += '<div class="tabContainerHeading">Total posted problems - '+results.length+'</div>';
	str += '<div style="padding:5px;">';
	str += '<table id="reasonsCountTable">';
	str += '<tr>';
	str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th align="left">Problems Approved</th>';
	str += '<td> - </td>';
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfProblems(\'approved\')">'+postedApprovedProblems.length+'</a> </td>';
	str += '</tr>';

	str += '<tr>';
	str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th align="left">Problems Rejected</th>';
	str += '<td> - </td>';
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfProblems(\'rejected\')">'+postedRejectedProblems.length+'</a> </td>';
	str += '</tr>';

	str += '<tr>';
	str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th align="left">Problems Not Considered</th>';
	str += '<td> - </td>';
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfProblems(\'notConsidered\')">'+postedNotConsideredProblems.length+' </a></td>';
	str += '</tr>';

	str += '</table>';
	str += '</div>';	

	elmt.innerHTML = str;
}

function openDialogOfProblems(type)
{
	var reasons = new Array();
	var title = '';
	if(type == "approved")
	{
		reasons = postedApprovedProblems;
		title = 'Approved Reasons';
	}
	else if(type == "rejected")
	{
		reasons = postedRejectedProblems;
		title = 'Rejected Reasons';
	}
	else if(type == "notConsidered")
	{
		reasons = postedNotConsideredProblems;
		title = 'Not Considered Reasons';
	}
	
	title = 'Total '+type+' problems';
	$( "#jQueryPopup" ).dialog({
			title:title,
			autoOpen: true,
			show: "blind",
			width: 900,
			minHeight:400,
			modal: true,
			hide: "explode"
		});

	buildProblemsDatatable(reasons,"reasonsDataTable");
}

function buildProblemsDatatable(commentsArray,divId)
{
	var resultsDataSource = new YAHOO.util.DataSource(commentsArray);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "definition"
		}, {
			key : "description"
		}, {
			key : "existingFrom"
		}, {
			key : "identifiedDate"
		}, {
			key : "location"
		} , {
			key : "locationType"
		} ]
	};

	var resultsColumnDefs = [ {
		key : "definition",
		label : "Problem",
		sortable : true
	}, {
		key : "description",
		label : "Description",
		sortable : true
	}, {
		key : "existingFrom",
		label : "Existing From",
		sortable : true
	}, {
		key : "identifiedDate",
		label : "Posted On",
		sortable : true
	}, {
		key : "location",
		label : "Location",
		sortable : true
	}, {
		key : "locationType",
		label : "Location Type",
		sortable : true
	} 
	];

    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,		        
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
				rowsPerPageOptions: [20,40,60], 
			    pageLinks: 20
			    }) 
				};	
	var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,myConfigs);  
}

function showPostedReasons(jsObj,results)
{
	var elmt = document.getElementById("postedReasons_main");
	
	if(results == null || results.length == 0)
	{
		results = [];
	}		
	else
	{
		for(var i=0; i<results.length; i++)
		{
			if(results[i].isApproved == "true")
				postedApprovedReasons.push(results[i]);
			else if(results[i].isApproved == "false")
				postedRejectedReasons.push(results[i]);
			else if(results[i].isApproved == null)
				postedNotConsideredReasons.push(results[i]);
		}
	}
	var str = '';
	str += '<div class="tabContainerHeading">Total posted reasons - '+results.length+'</div>';
	str += '<div style="padding:5px;">';
	str += '<table id="reasonsCountTable">';
	str += '<tr>';
	str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th align="left">Reasons Approved</th>';
	str += '<td> - </td>';
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfReasons(\'approved\')">'+postedApprovedReasons.length+'</a> </td>';
	str += '</tr>';

	str += '<tr>';
	str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th align="left">Reasons Rejected</th>';
	str += '<td> - </td>';
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfReasons(\'rejected\')">'+postedRejectedReasons.length+'</a> </td>';
	str += '</tr>';

	str += '<tr>';
	str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th align="left">Reasons Not Considered</th>';
	str += '<td> - </td>';
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfReasons(\'notConsidered\')">'+postedNotConsideredReasons.length+' </a></td>';
	str += '</tr>';

	str += '</table>';
	str += '</div>';	

	elmt.innerHTML = str;
}

function openDialogOfReasons(type)
{
	var reasons = new Array();
	var title = '';
	if(type == "approved")
	{
		reasons = postedApprovedReasons;
		title = 'Approved Reasons';
	}
	else if(type == "rejected")
	{
		reasons = postedRejectedReasons;
		title = 'Rejected Reasons';
	}
	else if(type == "notConsidered")
	{
		reasons = postedNotConsideredReasons;
		title = 'Not Considered Reasons';
	}
	
	title = 'Total '+type+' reasons';
	$( "#jQueryPopup" ).dialog({
			title:title,
			autoOpen: true,
			show: "blind",
			width: 900,
			minHeight:400,
			modal: true,
			hide: "explode"
		});

	buildCommentsDatatable(reasons,"reasonsDataTable");
}

function buildCommentsDatatable(commentsArray,divId)
{
	var resultsDataSource = new YAHOO.util.DataSource(commentsArray);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "candidate"
		}, {
			key : "partyName"
		}, {
			key : "electionYear",parser:"number"
		}, {
			key : "rank"
		}, {
			key : "commentDesc"
		} , {
			key : "commentedBy"
		} , {
			key : "commentedOn"
		} , {
			key : "constituencyName"
		},  {
			key : "electionType"
		}]
	};

	var resultsColumnDefs = [ {
		key : "candidate",
		label : "Candidate",
		sortable : true
	}, {
		key : "partyName",
		label : "Party",
		sortable : true
	}, {
		key : "electionYear",
		label : "Year",
		sortable : true
	}, {
		key : "rank",
		label : "Status",
		sortable : true
	}, {
		key : "commentDesc",
		label : "Reason",
		sortable : true
	}, {
		key : "commentedBy",
		label : "Posted By",
		sortable : true
	} , {
		key : "commentedOn",
		label : "Posted On",
		sortable : true
	} , {
		key : "constituencyName",
		label : "Constituency",
		sortable : true
	} , {
		key : "electionType",
		label : "Election",
		sortable: true
		
	} ];

    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,		        
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
				rowsPerPageOptions: [20,40,60], 
			    pageLinks: 20
			    }) 
				};	
	var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,myConfigs);  
}

function getAllPostedReasonsForUser()
{	
	var jsObj=
	{
			task:"getAllPostedReasonsStatusUser"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPostedReasonsStatusUserAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getAllPostedProblemsForUser()
{
	var jsObj=
	{
			task:"getAllPostedProblemsByUser"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPostedProblemsByUserAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function initializeConnectPeople()
{
	initializeTabView();	
	getAllRequestMessagesForUser();
	getAllPostedReasonsForUser();
	getAllPostedProblemsForUser();
	buildInboxMessagesForUser();
	buildConnectionsContentForUser();
	buildPeopleYouMayKnowContent();
	buildQuickRegionAccessContent();
}
