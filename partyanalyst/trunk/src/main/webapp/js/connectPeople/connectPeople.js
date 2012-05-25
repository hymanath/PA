

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
var loginUserName = '';
var loginUserProfilePic = '';
var userType = '';
var stateId = '';
var stateName = '';
var districtId = '';
var districtName = '';
var constituencyId = '';
var constituencyName = '';
var parliamentConstId = '';
var parliamentConstName = '';

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

var uploadPicStatus = false;
var refreshTime=5;
var uploadResult;
var oPushButton1;
var oPushButton2;

function initializeTabView()
{
	var myTabs = new YAHOO.widget.TabView();

	myTabs.addTab( new YAHOO.widget.Tab({
		label: 'Posted Reasons/Problems',
		content: '<div id="postedDiv_main" style="text-align:left;"></div>',
		active: true
	}));

	var str = '';
	str += '<div id="storyBoard_main" style="text-align:left;">';
	str += '<div id="storyBoard_header_Div"></div>';
	str += '<div id="storyBoard_body_Div"></div>';
	str += '<div id="storyBoard_fotter_Div"></div>';
	str += '</div>';

	myTabs.addTab( new YAHOO.widget.Tab({
		label: 'Pending Requests',
		content: str,
		active: false
	}));
	
	var tabLable = 'Inbox';
	if(commentsInfo.unreadMsgCount > 0)
		tabLable = tabLable+' ('+commentsInfo.unreadMsgCount+')';
	
	var str = '';
	str +='<div id="inboxMessages_main" style="text-align:left;"></div>';
	myTabs.addTab( new YAHOO.widget.Tab({
		label: tabLable,
		content: str
	}));

	myTabs.addTab( new YAHOO.widget.Tab({
		label: "Connections",
		content: '<div id="connectPeople_connect_center" style="text-align:left;font-family:verdana;"></div>'
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
		str += '</tr></table>';
		str += '</div>';		
		str += '<div id="messagesContent" class="messageStyles">';
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
		str += '<div style="text-align:right;" id="replyDiv_'+i+'"><a href="javascript:{}" onclick="showMailPopup(\''+arrData[i].id+'\',\''+arrData[i].candidateName+'\',\'Message\')">Reply</a></div>';
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

function getFriendsListForUser(results)
{
	
	var elmt = document.getElementById("connectPeople_connect_center");

	if(!elmt)
		return;
	
	var str = '';

	if(results.resultStatusForConnectedPeople.resultCode != "0")
	{
		str += '<div> Data could not be retrived due to some technical difficulties</div>';
		elmt.innerHTML = str;
		return;
	}
	else if(results.connectedPeople == "")
	{
		str += '<div>There are no connections established till now.</div>';
		elmt.innerHTML = str;
		return;
	}
	
	str += '<div id="connection_main_head">';
	str += '<table>';
	str += '<tr>';
	str += '<td width="40px"><img src="images/icons/indexPage/group_icon.png"></td>';
	str += '<td>You have total '+results.connectedPeople.length+' connections.</td>';
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
	for(var i=0; i<results.connectedPeople.length; i++)
	{
		str += '<div>';
		str += '<table style="Verdana,Arial,sans-serif;font-size:12px;">';
		str += '<tr>';
		str += '<td rowspan="3"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"></td>';
		str += '<td valign="top">'+results.connectedPeople[i].candidateName+'</td>';
		str += '</tr>';
		str += '<tr>';		
		str += '<td valign="top">'+results.connectedPeople[i].constituencyName+'</td>';
		str += '</tr>';	
		str += '<tr>';		
		str += '<td valign="top" align="left"><a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a></td>';
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
		str += '<div class="messageStyles"> Data could not be retrived due to some technical difficulties</div>';
		elmt.innerHTML = str;
		return;
	}
	else if(arrData.length == 0)
	{
		str += '<div class="messageStyles"> There are no connections established till now.</div>';
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
	str +='<table width="100%" style="Verdana,Arial,sans-serif;">';

	for(var i=0;i<arrData.length;i++)
	{
		if(i%3==0)
				
		str +='<tr>';
		str +='<td>';
		str +='<table>';
		str +='<span class="memberCardViewAll" style="height:142px;width:188px;">';
		str +='<h3 id="connectDiv"><div style="float:left;width:75px;height:75px;">';
		//str +='<div style="float:right;width:100px;height:30px;margin-left:5px;">';
		
		
		
		str += '<img height="50" width="55" src="/PartyAnalyst/pictures/profiles/'+arrData[i].id+'.jpg" onerror="setImage(this)" height="65" width="60"></div>';
		
		//str += '<td rowspan="3"><img height="50" width="55" src="/PartyAnalyst/pictures/profiles/human.jpg"></td>';
		str +='<div style="float:right;width:100px;height:30px;margin-left:5px;">';
		str +='<span style="margin-bottom:5px;">'+arrData[i].candidateName+'</span>';
		
		str +='</div></h3>';

		
		
		
		str += '<span class="sociallinks" href="#">Constituency:';
		str +='<b>'+arrData[i].constituencyName+'</b>';
		str +='</span>';
		str +='<br>';
				
		str += '<a class="sociallinks" href="javascript:{}" onclick="showMailPopup(\''+arrData[i].id+'\',\''+arrData[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a>';
		
		str +='</span>';
		str +='</table>';
		if((i%3)+1==0)
		str +='</tr>';
	}
	str +='</table>';
	/*str += '</td>';
	str += '<td width="70%" valign="top">';
	str += '<div id="connection_main_data">';
	for(var i=0; i<arrData.length; i++)
	{
		str += '<div>';
		str += '<table style="Verdana,Arial,sans-serif;font-size:12px;">';
		str += '<tr>';
	
		str += '<td rowspan="3"><img height="50" width="55" src="/PartyAnalyst/pictures/profiles/'+arrData[i].id+'.jpg" onerror="setImage(this)" height="65" width="60"></td>';
	
		//str += '<td rowspan="3"><img height="50" width="55" src="/PartyAnalyst/pictures/profiles/human.jpg"></td>';
		str += '<td valign="top">'+arrData[i].candidateName+'</td>';
		str += '</tr>';
		str += '<tr>';		
		str += '<td valign="top">'+arrData[i].constituencyName+'</td>';
		str += '</tr>';	
		str += '<tr>';		
		str += '<td valign="top" align="left"><a href="javascript:{}" onclick="showMailPopup(\''+arrData[i].id+'\',\''+arrData[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a></td>';
		str += '</tr>';	
		str += '</table>';
		str += '</div>';
	}
	str += '</div>';*/
	str += '</td>';
	str += '</tr>';	
	str += '</table>';
	str += '</div>';
	

	elmt.innerHTML = str;
}
function setImage(img)
{
		img.src = "/PartyAnalyst/pictures/profiles/human.jpg";
}
function showMailPopup(id,name,type)
{
	var str = '';	
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th>Message</th>';
	str += '<td><textarea id="connectMessageText" cols="35" rows="4" style="background:#ffffff;"></textarea></td>';
	str += '</tr>';
	str += '<tr>';	
	str += '<td colspan="2"><input type="button" name="connectButton" value="Send" id="sendMessageButtonId" onclick="sendMessageToConnectedUser(\''+id+'\',\''+type+'\')"></td>';
	str += '</tr>';
	str += '</table>';
	str	+= '<div id="connectStatus"></div>';
	str	+= '</div>';

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

	enableButton("sendMessageButtonId");
    if(results.resultCode == 0)
	{
		 document.getElementById("connectMessageText").value = '';
		
		
		elmt.innerHTML = '<blink><font color="green">Message Sent Successfully..</font></blink>';
		var t=setTimeout("closeMessagePopup()",2000);
	}
	else
	{
		elmt.innerHTML = '<font color="red">Message Cannot Be sent Due To Some Technical Difficulties<font>';
	}
}

function closeMessagePopup()
{
	var elmt = document.getElementById("connectPeopleMessagePopup_main");

	if(elmt)
		elmt.innerHTML = '<div id="connectPeopleMessagePopup"></div>';
}

function getLatestFriendsList()
{	
	var jsObj ={	
			task:"getLatestFriendsList"
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLatestFriendsList.action?"+rparam;						
	callAjax(jsObj,url);
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
						getLatestFriendsList();						
					}
					else if(jsObj.task == "getLatestFriendsList")
					{
						getFriendsListForUser(results);				
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
								hidepostedDivImg();
					}
					else if(jsObj.task == "getAllPostedProblemsByUser")
					{
						showPostedProblems(jsObj,results);
						hidepostedDivImg();
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
						//showAllConnectedUsersStatus(jsObj,results);
					}
					else if(jsObj.task == "connectToUser")
					{
						closeConnectPanel(jsObj,results);
					}
					else if(jsObj.task == "changePassword")
					{
						showresults(results);
					}

			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);
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

function uploadUserPic()
{
	$("#uploadPic_window").dialog({
		resizable:false,
		width: 582,
		minHeight:200,
		show:'slide',
		modal:true
	});
	
	
	$(".ui-dialog-titlebar").hide();

	var elmt = document.getElementById("uploadPic_window_inner");
	if(!elmt)
		return;

	var str = '';
	str += '<div id="uploadPic_window_head">Upload Your Picture</div>';
	str += '<div id="uploadPic_window_body">';
	str += '<form name="uploadForm" action="uploadPicAction.action" enctype="multipart/form-data" method="post" id="uploadPicForm">';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th valign="top">';
	str += '	<table width="100%" class="uploadPic_string_table">';
	str += '	<tr>';
	str += '	<td width="7px"><img width="7" height="5" src="images/icons/districtPage/listIcon.png"></td>';
	str += '	<td>Select a image from your computer.</td>';
	str += '	</tr>';

	str += '	<tr>';
	str += '	<td width="7px"><img width="7" height="5" src="images/icons/districtPage/listIcon.png"></td>';
	str += '	<td>Image size should be less than 2MB.</td>';
	str += '	</tr>';

	str += '	<tr>';
	str += '	<td width="7px"><img width="7" height="5" src="images/icons/districtPage/listIcon.png"></td>';
	str += '	<td>Image should be .jpg or.png or .gif formats only.</td>';
	str += '	</tr>';

	str += '	</table>';	
	str += '</th>';
	str += '<td rowspan="3">';
	str += '	<div style="border:1px solid #ADADAD;"><img width="90" height="100" id="Imgpreview" src="images/icons/indexPage/human.jpg"></div>';
	str += '</td>';
	str += '</tr>';

	str += '<tr>';
	str += '<td>';
	str += '<input type="file" size="45" id="photoUploadElmt" name="upload" onchange="previewImg()"/>';
	str += '</td>';
	str += '</tr>';
	
	str += '</table>';	
	str += '</form>';
	str += '</div>';
	str += '<div id="uploadPic_window_footer" class="yui-skin-sam">';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th width="60%" valign="top"><div id="uploadPic_window_status"></div></th>';
	str += '<td width="40%" valign="top"><input type="button" value="Upload" id="uploadPicButton"/>';
	str += '<input type="button" value="Cancel" id="cancelPicButton"/>';	
	str += '</td>';
	str += '</tr>';
	str += '</table>';	
	str += '</div>';

	elmt.innerHTML = str;

	oPushButton1 = new YAHOO.widget.Button("uploadPicButton");  
	oPushButton2 = new YAHOO.widget.Button("cancelPicButton");
	
	oPushButton1.on("click",function(){
		var photoStatusElmt = document.getElementById("uploadPic_window_status");
		photoStatusElmt.innerHTML = 'Uploading Image. Please Wait... &nbsp<img width="16" height="11" src="images/icons/partypositions.gif"/>'
		
		getUploadpic();
	});

	oPushButton2.on("click",function(){
		$("#uploadPic_window").dialog("destroy");
	});
}

function changeExistingPassword()
{

$("#password_change_window").dialog({
			resizable:false,
			width: 600,
			minHeight:225,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("password_change_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Change Password</div>';
		str += '<div id="feedback_window_body">';
		str += '<table>';
		str += '<tr><td><img src="images/icons/infoicon.png"/>';
		str += 'Fields marked with (<font color="red">*</font>) are mandatory</td></tr><tr><td class="tdStyle"><b style="color:red">*</b>Current Password</td><td>  <input type="password" name="currentPassword"  id="currentPwdId" cssClass="textFieldStyle" /></td></tr>';
		str += '<tr><td class="tdStyle"><b style="color:red">*</b>New Password</td><td><input type="password" name="newPassword"  id="newPwdId" cssClass="textFieldStyle" />';
	    str += '</td></tr>';
		str += '<tr><td class="tdStyle"><b style="color:red">*</b>Confirm Password</td><td>  <input type="password" name="confirmPassword"  id="confirmPwdId" cssClass="textFieldStyle"/>';
        str += '</td></tr></table>';
        str += '	</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="50%" align="left"><div id="password_window_errorMsg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '		<input id="changeButton" type="button" value="Change Password"></input>';
		str += '		<input style="width:52px; text-align:center;" id="cancelButton" type="button" value="No"></input>';
		str += '	</td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton1 = new YAHOO.widget.Button("changeButton");  
		var oPushButton2 = new YAHOO.widget.Button("cancelButton");

		oPushButton1.on("click",function(){
			changePassword();
		});

		oPushButton2.on("click",function(){
			$("#password_change_window").dialog("destroy");
		});
	
}
	
function changePassword()
	{
	var cpwd = document.getElementById("currentPwdId").value;
	var npwd = document.getElementById("newPwdId").value;
	var cfmpwd = document.getElementById("confirmPwdId").value;
	var resultDIVEle = document.getElementById("password_window_errorMsg");
	resultDIVEle.innerHTML = "";
	if(cpwd.length > 0 &&cpwd.length < 6){
		resultDIVEle.innerHTML = "<font color='red'>Current Password Minimum Of 6 Characters.</font>";
	        return;
	}
	if(npwd.length > 0 &&npwd.length < 6){
		resultDIVEle.innerHTML = "<font color='red'>New Password Minimum Of 6 Characters.</font>";
	        return;
	}
	if(cpwd.value=="")
     resultDIVEle.innerHTML="<font color='red'>Please Enter Password.</font>";	
     if(npwd.length > 0 && cfmpwd.length > 0 && npwd != cfmpwd){
 		resultDIVEle.innerHTML = "<font color='red'>Passwords Do Not Match.</font>";
         return
	}
	if(cpwd==''){
     resultDIVEle.innerHTML = "<font color='red'>Please Enter Current Password.</font>";
	return;
	}
	if(npwd==''){
     resultDIVEle.innerHTML = "<font color='red'>Please Enter New Password.</font>";
	return;
	}
	if(cfmpwd==''){
	resultDIVEle.innerHTML = "<font color='red'>Please Enter Confirm Password.</font>";
	return;
	}
	if(cpwd == npwd)
		{
		resultDIVEle.innerHTML = "<font color='green'>Your new passward is same as Existing one.</font>";
		setTimeout("closewdw()",3000);
		return;
		}
	if(cpwd!='')
		{
		str = '<font color="#000000">Sending Your Request.Please wait</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'
       	var jsObj={
          crntPassword:cpwd,
          newPassword:npwd,
		  task:"changePassword"	 
	};
        var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "changeUserPasswordAction.action?"+rparam;						
		callAjax(jsObj,url);
		}
}
function closewdw()
{
	$("#password_change_window").dialog("destroy");
}
function showresults(results){


		if(results==121){
			var result = document.getElementById("password_window_errorMsg");
		result.innerHTML ='<div style="color:green">Password changed successfully, Window Closing...</div>';
        
		setTimeout("closewdw()",3000);
		}	
		else if(results==0){
			var result = document.getElementById("password_window_errorMsg");
		var str='';
			str+='<div style="color:red"> Invalid Current Password</div>';	
		result.innerHTML = str;

		}

	
}

function previewImg()
{
	var photoElmt = document.getElementById("photoUploadElmt");
	var photoStatusElmt = document.getElementById("uploadPic_window_status");
	var fileLimit = 1048576; //1024*1024 = 1048576 bytes (2MB photo limit)
	
	var file = photoElmt.files[0];
	
	var fileType = file.type;
	var fileImgType = fileType.substring(fileType.indexOf('/')+1,fileType.length);
	

	if(fileImgType == "jpeg" || fileImgType == "png" || fileImgType == "gif")
	{
		var fileSize = file.fileSize/fileLimit;
		if(fileSize > 2)
		{
			photoStatusElmt.innerHTML = '<span class="errorStatusMsg">The Image size should be < 2MB.</span>';
		}
		else
		{
			var reader = new FileReader();
           //  init the reader event handlers
			reader.onloadend = handleReaderLoadEnd;
			 
			// begin the read operation
			reader.readAsDataURL(file);
			
			photoStatusElmt.innerHTML = '';
			var previewElmt = document.getElementById("Imgpreview");
			//previewElmt.src = file.getAsDataURL();
			uploadPicStatus = true;
		}
	}
	else
	{
		photoStatusElmt.innerHTML = '<span class="errorStatusMsg">The Image is not of the type specified.</span>';
	}

	

}
function handleReaderLoadEnd(evt) {
  var img = document.getElementById("Imgpreview");
  img.src = evt.target.result;
}
function getUploadpic()
{
	if(!uploadPicStatus)
		return;
		
	oPushButton1.set('disabled', true)	
	uploadPicStatus = false;

	var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				showUploadStatus();				
			}
		};

	
	YAHOO.util.Connect.setForm('uploadPicForm',true);
	YAHOO.util.Connect.asyncRequest('POST', 'uploadPicAction.action', uploadHandler);
}



function showUploadStatus()
{
	if(refreshTime > 0)
	{
		var photoStatusElmt = document.getElementById("uploadPic_window_status");
		photoStatusElmt.innerHTML = "<font color='#473E37'>"+uploadResult+" Page refreshing in "+refreshTime+" seconds.Please wait..</font>";
		refreshTime=refreshTime-1;
		t=setTimeout("showUploadStatus()",1000);
	}
	else
	{
		$("#uploadPic_window").dialog("destroy");
		window.location.reload();
	}
}


function buildQuickRegionAccessContent()
{	
	var profElmt = document.getElementById("connectPeople_editProfile_center");
	var qElmt = document.getElementById("connectPeople_quickAccess_center");
	var connectCountElmt = document.getElementById("connectPeople_count_center");
	
	if(!qElmt || !connectCountElmt)
		return;

	var pStr = '<div style="margin-right:15px;margin-top:9px;"><a style="color:#73787E;font-weight:bold;text-decoration:none;" href="freeUserRegistration.action">Edit Profile</a></div>';
		pStr += '<div style="margin-right:15px;margin-top:9px;"><a style="color:#73787E;font-weight:bold;text-decoration:none;" href="javascript:{}" onclick="uploadUserPic()">Edit picture</a></div>';
		pStr += '<div style="margin-right:15px;margin-top:9px;"><a style="color:#73787E;font-weight:bold;text-decoration:none;" href="javascript:{}" onclick="changeExistingPassword()">Change Password</a></div>';
		profElmt.innerHTML = pStr;

	var str = '';
	str += '<div id="regionAccessDiv_main">';

	str += '<div id="regionAccessDiv_body" >';
	str += '<table id="regionAccessTable" style="font-size:12px;">';
	str += '<tr>';
	str += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th>State</th>';
	str += '<td> - </td>';
	str += '<td><a href="statePageAction.action?stateId='+stateId+'">'+stateName+'</a></td>';
	str += '</tr>';
	str += '<tr>';
	str += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th>District</th>';
	str += '<td> - </td>';
	str += '<td><a href="districtPageAction.action?districtId='+districtId+'&districtName='+districtName+'">'+districtName+'</a></td>';	
	str += '</tr>';
	str += '<tr>';
	str += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th>Constituency</th>';
	str += '<td> - </td>';
	str += '<td><a href="constituencyPageAction.action?districtId='+districtId+'&constituencyId='+constituencyId+'">'+constituencyName+'</a></td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';
	str += '</div>';

	qElmt.innerHTML = str;

	var cStr = '';
	cStr += '<div id="connectCountDiv_main">';
	
	cStr += '<div id="connectCountDiv_body" >';	
	cStr += '<table id="regionAccessTable" style="font-size:12px;">';	
	cStr += '<tr>';
	cStr += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	cStr += '<th style="font-size:12px;">From '+districtName+' District</th>';
	cStr += '<td> - </td>';
	cStr += '<td><a id="connCnt" href="javascript:{}" onclick="showAllConnectPeopleWindowOfDistrict(\''+districtId+'\',\''+districtName+'\',\''+loginUserId+'\',\'DISTRICT\')">'+districtConnectCount+'</a></td>';
	cStr += '<td><img width="25" height="20" src="/PartyAnalyst/images/icons/constituencyPage/groups.png"></td>';
	cStr += '</tr>';	
	cStr += '<tr>';
	cStr += '<td><img height="5" width="7" src="images/icons/districtPage/listIcon.png"></td>';
	cStr += '<th style="font-size:12px;">From '+constituencyName+' Constituency</th>';
	cStr += '<td> - </td>';
	cStr += '<td><a href="javascript:{}" onclick="showAllConnectPeopleWindow(\''+constituencyId+'\',\''+constituencyName+'\',\''+loginUserId+'\',\'CONSTITUENCY\')">'+constituencyConnectCount+'</a></td>';
	cStr += '<td><img width="25" height="20" src="/PartyAnalyst/images/icons/constituencyPage/groups.png"></td>';
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
	//str += '<div id="peopleMayKnow_head"> People You may Know &nbsp;&nbsp;&nbsp;&nbsp;</div>';
	str += '<div id="peopleMayKnow_body">';
	str += '	<div class="peopleMayKnow_data">';
	str += '	<table width="100%" class="peopleMayKnow_data_table" style="font-size:12px;background:window;">';
	
	var arrData = peopleYouMayKnowInfo.peopleYouMayKnow;

	for(var i=0; i<arrData.length && i<3; i++)
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
		str += '<a class="peopleConnectAnc"  href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+arrData[i].id+'\',\''+arrData[i].candidateName+'\',\''+arrData[i].constituencyName+'\',\''+loginUserId+'\',\''+constituencyId+'\',\'CONSTITUENCY\',\''+constituencyName+'\')">Connect</a>';		
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
		str += '	<a href="javascript:{}" style="font-size:12px;" onclick="showAllConnectPeopleWindow(\''+districtId+'\',\''+districtName+'\',\''+loginUserId+'\',\'DISTRICT\')""> View All </a>';
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

function openAddNewProblemWindowForDashBoard()
{	
	var browser1 = window.open("addNewProblemAction.action?requestSrc=4&constituencyId="+constituencyId,"addNewProblemInConstituency","scrollbars=yes,height=600,width=600,left=200,top=200");				 
	browser1.focus();
}

function showPostedProblems(jsObj,results)
{	
	var elmt = document.getElementById("postedDiv_body_countInfo");
	
	/*if(results == null || results.length == 0)
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
	}*/

	var str = '';
	str += '<div class="tabContainerHeading">';
	str += '<table width="100%">';
	str += '<tr>';
	if(results.totalPostedProblemsCount == 0)
		str += '<td width="180px">Total posted problems - '+results.totalPostedProblemsCount+'</td>';
	else
		str += '<td width="180px">Total posted problems - <a href="javascript:{}" style="color:#669900;" onclick="openDialogOfProblems(\'Total\')">'+results.totalPostedProblemsCount+'</a></td>';

	if(results.postedProblemsCountByLoggedInUsers == 0)
		str += '<td width="90px">By You - '+results.postedProblemsCountByLoggedInUsers+'</td>';
	else
		str += '<td width="90px">By You - <a href="javascript:{}" onclick="openDialogOfProblems(\'LOGGED_USER\')" style="color:#669900;">'+results.postedProblemsCountByLoggedInUsers+'</a></td>';

	if(results.postedProblemsCountByOtherUsers == 0)
		str += '<td width="90px">By Others - '+results.postedProblemsCountByOtherUsers+'</td>';
	else
		str += '<td width="90px">By Others - <a href="javascript:{}" onclick="openDialogOfProblems(\'OtherUsers\')" style="color:#669900;">'+results.postedProblemsCountByOtherUsers+'</a></td>';

	if(userType != "PartyAnalyst")
		str += '<td align="right"><a href="javascript:{}" onclick="openAddNewProblemWindowForDashBoard()" style="color:#669900;text-decoration: underline;">Post Problem</a></td>';

	str += '</tr>';
	str += '</table>';
	str += '</div>';
	str += '<div style="padding:3px;">';
	str += '<div style="color:#9E7556;font-weight:bold;padding:5px;font-family:verdana;font-size:13px;"> Problem Status Details Posted By You </div>';
	str += '<table id="reasonsCountTable">';
	str += '<tr>';
	str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th align="left">Problems Approved</th>';
	str += '<td> - </td>';
	if(results.approvedProblemsCount ==0)
		str+='<td>'+results.approvedProblemsCount+'<td>';
	else
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfProblems(\'approved\')">'+results.approvedProblemsCount+'</a> </td>';
	
	str += '<td></td>';
	str += '<th align="left">Problems Rejected</th>';
	str += '<td> - </td>';
	if(results.rejectedProblemsCount ==0)
		str+='<td>'+results.rejectedProblemsCount+'<td>';
	else
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfProblems(\'rejected\')">'+results.rejectedProblemsCount+'</a> </td>';
	
	str += '<td></td>';
	str += '<th align="left">Problems Not Considered</th>';
	str += '<td> - </td>';
	if(results.notConsideredProblemsCount ==0)
		str+='<td>'+results.notConsideredProblemsCount+'</td>';
	else
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfProblems(\'notConsidered\')">'+results.notConsideredProblemsCount+' </a></td>';
	str += '</tr>';

	str += '</table>';
	str += '</div>';	

	elmt.innerHTML = str;
}

function openDialogOfProblems(type)
{
	/*var reasons = new Array();
	var title = '';
	if(type == "Total")
	{
		reasons = postedApprovedProblems;
		title = 'Total Posted Problems';
	}
	else if(type == "LOGGED_USER")
	{
		reasons = postedRejectedProblems;
		title = 'Problems Posted By User';
	}
	else if(type == "OtherUsers")
	{
		reasons = postedNotConsideredProblems;
		title = 'Problems Posted By Other Users';
	}
	else
		title = 'Total '+type+' problems';

	$( "#jQueryPopup" ).dialog({
			title:title,
			autoOpen: true,
			show: "blind",
			width: 900,
			minHeight:400,
			modal: true,
			hide: "explode"
		});*/

	//buildProblemsDatatable(type);

	var jsObj ={				
			task:"getAllPostedProblems_paginator"
		 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getAllPostedProblemsDataAction.action?"+rparam+"&type="+type+"&sort=problemId&dir=desc";

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:3,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllPostedProblems_paginator(jsObj,results);
		}
	});
	custom_paginator.initialize();
}

function showAllPostedProblems_paginator(jsObj,results)
{
	var elmt = document.getElementById("postedDiv_body_dataInfo");
		
	var str = '';
	if(results.problemsInfo==null){
	return;
	}

	for(var i=0; i<results.problemsInfo.length; i++)
	{
		var data = results.problemsInfo[i];
		
		str += '<div class="postedDiv_dataInfo_main">';
		str += '<div class="postedDiv_dataInfo_head"><a class="postedDiv_dataInfo_head" href="problemCompleteInfoAction.action?problemHistoryId='+data.problemHistoryId+'">'+data.definition+'</a></div>';
		str += '<div class="postedDiv_dataInfo_body">';
		str += '<table class="postedDiv_dataInfo_table" width="100%" style="font-family:Verdana,Arial,sans-serif;font-size:13px;">';
		str += '<tr>';
		str += '<th style="width:20%;">Description </th>';
		str += '<td style="width:2%;">:</td>';
		str += '<td style="width:78%;">'+data.description+'</td>';
		str += '</tr>';
		str += '<tr>';
		str += '<th style="width:20%;">Location </th>';
		str += '<td style="width:2%;">:</td>';
		str += '<td style="width:78%;">'+data.location+'</td>';
		str += '</tr>';
		str += '<tr>';
		str += '<th style="width:20%;">Existing From </th>';
		str += '<td style="width:2%;">:</td>';
		str += '<td style="width:78%;">'+data.existingFrom+'</td>';
		str += '</tr>';
		str += '<th style="width:20%;">Identified Date </th>';
		str += '<td style="width:2%;">:</td>';
		str += '<td style="width:78%;">'+data.identifiedDate+'</td>';
		str += '</tr>';		
		str += '</table>';
		str += '</div>';
		str += '</div>';
	}
	str += '<div class="custom_paginator_class" style="padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:10px;text-align:right;"></div>';

	elmt.innerHTML = str;
}

function openProblemLocationWindow(problemId)
{
	$( "#jQueryPopup" ).dialog("close");
	window.location.replace("problemCompleteDetailsAction.action?problemHistoryId="+problemId);
}

function buildProblemsDatatable(type)
{
	var resultsColumnDefs = [ {
		key : "problemHistoryId",
		label : "Problem Id",
		hidden:true
	}, {
		key : "isApproved",
		label : "Approved Status",
		hidden:true
	}, {
		key : "definition",
		label : "Problem",
		formatter:"myCustom",
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
	}  ];
	
	 this.myCustomFormatter = function(elLiner, oRecord, oColumn, oData) {
		 if(oRecord.getData("isApproved") == "true") { 
			elLiner.innerHTML = '<a href="javascript:{}" onclick="openProblemLocationWindow('+oRecord.getData("problemHistoryId")+')">'+oRecord.getData("definition")+'</a>'; 		 
		 }
		 else
			elLiner.innerHTML = ''+oRecord.getData("definition")+''; 
		
	}; 

	YAHOO.widget.DataTable.Formatter.myCustom = this.myCustomFormatter;

	var dataSource = new YAHOO.util.DataSource("getAllPostedProblemsDataAction.action?type="+type+"&"); 
	dataSource.responseType = YAHOO.util.DataSource.TYPE_JSON; 
	dataSource.responseSchema = { 
		resultsList: "problemsInfo", 
		fields : [ {
			key : "problemHistoryId"
		},{
			key : "isApproved"
		},{
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
		}],
		metaFields: {
			totalRecords: "problemsCount" // Access to value in the server response
		}         
	};

	var myConfigs = {
				initialRequest: "sort=definition&dir=asc&startIndex=0&resultsCount=20", // Initial request for first page of data
				dynamicData: true, // Enables dynamic server-driven data
				sortedBy : {key:"definition", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
				paginator: new YAHOO.widget.Paginator({ rowsPerPage:20 }) // Enables pagination 
	};
	
	var votersByLocBoothDataTable =  new YAHOO.widget.DataTable("reasonsDataTable", 
			resultsColumnDefs, dataSource, myConfigs);
	
	votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {		
			oPayload.totalRecords = oResponse.meta.totalRecords;
			return oPayload;
	}	

	return {
		oDS: dataSource,
		oDT: votersByLocBoothDataTable
	};


	/*var resultsDataSource = new YAHOO.util.DataSource(commentsArray);
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
	var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,myConfigs);  */
}

function openAddReasonWindow(taskType)
{
	var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=ANALYZECONSTITUENCYPOPUP&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstId+"&parliamentConstiName="+parliamentConstName+"&constituencyName="+constituencyName+"&userId="+loginUserId+"&taskType="+taskType,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");				 
	browser1.focus();
}

function showPostedReasons(jsObj,results)
{
	var elmt = document.getElementById("postedDiv_body_countInfo");
	var approvedReasonsCount = 0;
	var rejectedReasonsCount = 0;
	var notConsideredReasonsCount = 0;
	var totalPostedReasonsCount = 0;
	var postedReasonsCountByOtherUsers = 0;
	var postedReasonsByLoggedInUser = 0;

	
	if(results != null)
	{
		approvedReasonsCount = results.approvedReasonsCount;
		rejectedReasonsCount = results.rejectedReasonsCount;
		notConsideredReasonsCount = results.notConsideredReasonsCount;
		totalPostedReasonsCount = results.totalPostedReasonsCount;
		postedReasonsCountByOtherUsers = results.postedReasonsCountByOtherUsers;
		postedReasonsByLoggedInUser = totalPostedReasonsCount - postedReasonsCountByOtherUsers;
	}		
	/*else
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
	}*/
	var str = '';
	str += '<div class="tabContainerHeading">';
	str += '<table width="100%">';
	str += '<tr>';
	
	if(userType != "PartyAnalyst"){
        str += '<td align="left" width="194px"> Total Political reasons posted - </td>';
		  
		  if(totalPostedReasonsCount ==0)
			  str+='<td>'+totalPostedReasonsCount+'</td>';
		 
		  else
		   str+='<td> <a href="javascript:{}" onclick="openDialogOfReasons(\'Total\')" style="color:#669900;">'+totalPostedReasonsCount+'</a></td>';
		
		   str += '<td align="left" width="67px"> By User - </td>';
		
		if(postedReasonsByLoggedInUser ==0)
			  str+='<td>'+postedReasonsByLoggedInUser+'</td>';
		  else
		str+='<td><a href="javascript:{}" onclick="openDialogOfReasons(\'LOGGED_USER\')" style="color:#669900;">'+postedReasonsByLoggedInUser+'</a></td>';
		
		str += '<td align="left" width="87px"> By Others - </td>';
		
		if(postedReasonsCountByOtherUsers ==0)
			  str+='<td>'+postedReasonsCountByOtherUsers+'</td>';
		  else
		str +='<td><a href="javascript:{}" onclick="openDialogOfReasons(\'OtherUsers\')" style="color:#669900;">'+postedReasonsCountByOtherUsers+'</a></td>';	
	}else{
		str += '<td align="left" width="211px" style="color:#669900;"> Total Political reasons posted - '+totalPostedReasonsCount+'</td>';
		str += '<td align="left" width="90px" style="color:#669900;"> By You - '+postedReasonsByLoggedInUser+'</td>';
		str += '<td align="left" width="90px" style="color:#669900;"> By Others - '+postedReasonsCountByOtherUsers+'</td>';	
	}
	
	str += '<td align="right">';

	if(userType != "PartyAnalyst"){		
		str += '	<a href="javascript:{}" onclick="openAddReasonWindow(\'analyze\')" style="color:#669900;text-decoration: underline;">Add Reasons</a>';
		str += '	<a href="javascript:{}" onclick="openAddReasonWindow(\'viewResults\')" style="color:#669900;text-decoration: underline;">View Reasons</a>';
	}
	
	str += '</td>';
	str += '</tr>';
	str += '</table>'; 
	str += '</div>';
	str += '<div style="padding:5px;">';
	str += '<div style="color:#9E7556;font-weight:bold;padding:5px;font-family:verdana;font-size:13px;"> Reasons Status Details Posted By You </div>';
	str += '<table id="reasonsCountTable">';
	str += '<tr>';
	str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
	str += '<th align="left">Reasons Approved</th>';
	str += '<td> - </td>';
	if(approvedReasonsCount == 0)
	 str+='<td>'+approvedReasonsCount+'</td>';
	else
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfReasons(\'Approved\')">'+approvedReasonsCount+'</a> </td>';
	
	str += '<td></td>';
	str += '<th align="left">Reasons Rejected</th>';
	str += '<td> - </td>';
	if(rejectedReasonsCount ==0)
		str+='<td>'+rejectedReasonsCount+'</td>';
	else
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfReasons(\'rejected\')">'+rejectedReasonsCount+'</a> </td>';
	
	str += '<td></td>';
	str += '<th align="left">Reasons Not Considered</th>';
	str += '<td> - </td>';
	if(notConsideredReasonsCount == 0)
		str+='<td>'+notConsideredReasonsCount+'<td>';
	else
	str += '<td> <a class="reasonsCountAnc" href="javascript:{}" onclick="openDialogOfReasons(\'NotConsidered\')">'+notConsideredReasonsCount+' </a></td>';
	str += '</tr>';

	str += '</table>';
	str += '</div>';	

	elmt.innerHTML = str;
}

function openDialogOfReasons(type)
{
	/*var reasons = new Array();
	var title = '';
	if(type == "Total")
	{
		reasons = postedApprovedReasons;
		title = 'Reasons Posted By All Users';
	}
	else if(type == "LOGGED_USER")
	{
		reasons = postedRejectedReasons;
		title = 'Reasons Posted By '+loginUserName;
	}
	else if(type == "OtherUsers")
	{
		reasons = postedNotConsideredReasons;
		title = 'Reasons Posted By Other Users';
	}
	else 
		title = 'Total '+type+' reasons';

	$( "#jQueryPopup" ).dialog({
			title:title,
			autoOpen: true,
			show: "blind",
			width: 900,
			minHeight:400,
			modal: true,
			hide: "explode"
		});*/

	//buildCommentsDatatable(reasons,"reasonsDataTable");
	var jsObj ={				
				task:"getAllPostedReasons_paginator"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getAllPostedReasonsDataAction.action?"+rparam+"&type="+type+"&sort=candidate&dir=asc";	

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:3,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllPostedReasons_paginator(jsObj,results);
		}
	});
	custom_paginator.initialize();
	
	//buildCommentsDatatable(type);
}

function showAllPostedReasons_paginator(jsObj,results)
{
	
	var elmt = document.getElementById("postedDiv_body_dataInfo");
	if(results.candidateComments == null){
	return;
	}
	var str = '';

	for(var i=0; i<results.candidateComments.length; i++)
	{
		var data = results.candidateComments[i];
		var status;
		if(data.rank == 1)
			status = "winning";
		else 
			status = "losing";
		str += '<div class="postedDiv_dataInfo_main">';
		str += '<div class="postedDiv_dataInfo_head">Political Reason for '+data.candidate+' '+status+' in '+data.constituencyName+' '+data.electionType+' constituency</div>';
		str += '<div class="postedDiv_dataInfo_body">';
		str += '<table class="postedDiv_dataInfo_table" width="100%" style="font-family:Verdana,Arial,sans-serif;font-size:13px;">';
		str += '<tr>';
		str += '<th style="width:20%;">Political Reason </th>';
		str += '<td style="width:2%;">:</td>';
		str += '<td style="width:78%;">'+data.commentCategory+'</td>';
		str += '</tr>';
		str += '<tr>';
		str += '<th style="width:20%;">Description </th>';
		str += '<td style="width:2%;">:</td>';
		str += '<td style="width:78%;">'+data.commentDesc+'</td>';
		str += '</tr>';
		str += '<tr>';
		str += '<th style="width:20%;">Posted On </th>';
		str += '<td style="width:2%;">:</td>';
		str += '<td style="width:78%;">'+data.commentedOn+'</td>';
		str += '</tr>';
		str += '<tr>';
		str += '<th style="width:20%;">Posted By </th>';
		str += '<td style="width:2%;">:</td>';
		str += '<td style="width:78%;">'+data.commentedBy+'</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '</div>';
	}
	str += '<div class="custom_paginator_class"></div>';

	elmt.innerHTML = str;
}


function buildCommentsDatatable(type)
{
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
	
	var dataSource = new YAHOO.util.DataSource("getAllPostedReasonsDataAction.action?type="+type+"&"); 
	dataSource.responseType = YAHOO.util.DataSource.TYPE_JSON; 
	dataSource.responseSchema = { 
		resultsList: "candidateComments", 
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
		}],
		metaFields: {
			totalRecords: "commentsCount" // Access to value in the server response
		}         
	};

	var myConfigs = {
				initialRequest: "sort=candidate&dir=asc&startIndex=0&resultsCount=20", // Initial request for first page of data
				dynamicData: true, // Enables dynamic server-driven data
				sortedBy : {key:"candidate", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
				paginator: new YAHOO.widget.Paginator({ rowsPerPage:20 }) // Enables pagination 
	};
	
	var votersByLocBoothDataTable =  new YAHOO.widget.DataTable("postedDiv_body_dataInfo", 
			resultsColumnDefs, dataSource, myConfigs);
	
	votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {		
			oPayload.totalRecords = oResponse.meta.totalRecords;
			return oPayload;
	}	

	return {
		oDS: dataSource,
		oDT: votersByLocBoothDataTable
	};
}
/*function buildCommentsDatatable(commentsArray,divId)
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
}*/

function getAllPostedReasonsForUser()
{	

		showpostedDivImg();
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
	showpostedDivImg();
	var jsObj=
	{
			task:"getAllPostedProblemsByUser"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPostedProblemsByUserAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function buildReasonsProblemsTabContent()
{
	var elmt = document.getElementById("postedDiv_main");

	var str = '';
	str += '<div id="postedDiv_head">';	
	str += '<img id="postedDivImg" style="margin-left:360px;" src="images/icons/search.gif"></img>';
	str += '<input id="radio1" type="radio" name="radiofield1" value="Political Reasons" >';
	str += '<input id="radio2" type="radio" name="radiofield1" value="Problems" checked="checked">';	
	str += '</div>';
	str += '<div id="postedDiv_body">';
	str += '<div id="postedDiv_body_countInfo"></div>';
	str += '<div id="postedDiv_body_dataInfo"></div>';
	str += '</div>';

	elmt.innerHTML = str;
	
	var oButtonGroup1 = new YAHOO.widget.ButtonGroup("postedDiv_head");
	var onButtonClick = function(e)
	{
		if(e.target.innerHTML == "Problems")
		{
			getAllPostedProblemsForUser();
			openDialogOfProblems('Total');
		}
		else if(e.target.innerHTML == "Political Reasons")
		{
			getAllPostedReasonsForUser();			
			openDialogOfReasons('Total');
		}
	};
	oButtonGroup1.on("click", onButtonClick);

	//getAllPostedReasonsForUser();
	//openDialogOfReasons('Total');

	getAllPostedProblemsForUser();
	openDialogOfProblems('Total');
}

function initializeConnectPeople()
{	
	if(loginUserProfilePic == "")
	{
		var imgElmt = document.getElementById("userProfileImg");
		imgElmt.src = "pictures/profiles/human.jpg";
	}
	else
	{
		var imgElmt = document.getElementById("userProfileImg");
		imgElmt.src = "pictures/profiles/"+loginUserProfilePic;
	}

	initializeTabView();	
	getAllRequestMessagesForUser();
	buildReasonsProblemsTabContent();
	
	buildInboxMessagesForUser();
	buildConnectionsContentForUser();
	buildPeopleYouMayKnowContent();
	buildQuickRegionAccessContent();
	buildPolls();
	buildleadersNews();
	buildTopStoriesNews();
	buildPartiesNews();
	
	$( "#connectedPeopleAccordian" ).accordion();
}

function showpostedDivImg()
	{
		var ajaxImageDivEle = document.getElementById("postedDivImg");
		ajaxImageDivEle.style.display = 'block';
	}
	
	function hidepostedDivImg()
	{
		var ajaxImageDivEle = document.getElementById("postedDivImg");
		if(ajaxImageDivEle)
		ajaxImageDivEle.style.display = 'none';
	}
	
	function openBrowserForSocialNetworking(id)
	{
	    var browser1 = window.open("socialAuthenticationAction.action?id="+id,"Refer friends From "+id,"scrollbars=yes,height=600,width=600,left=200,top=200");				 
		browser1.focus();
	}


	function UserInfo()
	{
		var str='';
		
		var divEle = document.getElementById('contactDiv');
		document.getElementById('contactDiv').style.display='block';
		str +='<ul>'
		str +='<li><a href="javascript:{}" onclick="changeExistingPassword()">change Password</a></li>';
		str +='<li><a href="#">edit Picture</a></li>';
		str +='</ul>';
	
		divEle.innerHTML = str;
	}

	function mouseoutUserInfo()
	{
	document.getElementById('contactDiv').style.display='none';
		
	}

	