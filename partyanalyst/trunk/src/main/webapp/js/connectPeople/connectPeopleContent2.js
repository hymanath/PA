
var connectDivId = '';
var connectUserLoginStatus = '';
var connetLocationId = '';
var connetLocationName = '';
var connetLocationType = '';
var connectUserLoginId = '';
var connectRequestedUserId = '';
var locationIdLabel;
var locationNameLabel;
var locationId;
var locationName;
var locationType;
var userLoginStatus;

function buildConnectUsersContent(connectedPeopleData,divId,locationType,locationId,locationName,userLoginStatus,userLoginId)
{	
	
	connectDivId = divId;
	connectUserLoginStatus = userLoginStatus;
	connetLocationId = locationId;
	connetLocationName = locationName;
	connetLocationType = locationType;
	connectUserLoginId = userLoginId;

	var bodyElmt = document.getElementById(divId);
	

	if(locationType == "DISTRICT")
	{
		locationIdLabel = 'districtId';
		locationNameLabel = 'districtName';
	}
	else if(locationType == "CONSTITUENCY")
	{
		locationIdLabel = 'constituencyId';
		locationNameLabel = 'constituencyName';
	}


	var bodyStr='';
	bodyStr+='<div id="connectedNumberDiv"> ';
//	bodyStr+='<span><img height="20" width="25" src="/PartyAnalyst/images/constituencyPage/cp-thumb.jpg"></img></span>';
	bodyStr+='<span style="position:relative;left:5px;top:-5px;"> '+connectedPeopleData.length+' people connected from this district </span>';
	bodyStr+='</div>';
	bodyStr+='<div id="connectedPersonsDiv">';
	bodyStr+='<table width="100%">';
	for(var i =0; i<connectedPeopleData.length; i++)
	{
		if(i == 3)
			break;
		bodyStr+='<tr>';
		bodyStr+='<td>';
		bodyStr+='<table width="100%">';
		bodyStr+='<tr>';
		/*bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';*/
		if(connectedPeopleData[i].image == null || connectedPeopleData[i].image == '')
			bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="/PartyAnalyst/images/constituencyPage/cp-thumb.jpg"/></span></td>';
		else
			bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="pictures/profiles/'+connectedPeopleData[i].image+'"/></span></td>';
		bodyStr+='<td align="left"><span class="cp-fields-sec">'+connectedPeopleData[i].candidateName+'</span></td>';
		bodyStr+='</tr>';
		bodyStr+='<tr>';	
		bodyStr+='<td align="right"><span class="view-all">';
		if(userLoginStatus == "false")
			bodyStr+='<a href="connectPeopleAction.action?redirectLoc='+locationType+'&'+locationIdLabel+'='+locationId+'&'+locationNameLabel+'='+locationName+'">Connect</a>';
		else
		{
			if(connectedPeopleData[i].status == "LOGGED_USER")
				bodyStr+='<font color="#4A610B" style="padding-right:10px;">Logged User</font>';
			else if(connectedPeopleData[i].status == "NOT CONNECTED")
			{
				bodyStr+='<font color="#7F5A22" style="padding-right:10px;">Not Connected</font>';
				bodyStr+=' - <a href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+connectedPeopleData[i].id+'\',\''+connectedPeopleData[i].candidateName+'\',\''+connectedPeopleData[i].constituencyName+'\',\''+userLoginId+'\',\''+locationId+'\',\''+locationType+'\',\''+locationName+'\')">Connect</a>';
			}
			else if(connectedPeopleData[i].status == "CONNECTED")
				bodyStr+='<font color="#4A610B" style="padding-right:10px;">Connected</font>';
			else if(connectedPeopleData[i].status == "PENDING")
				bodyStr+='<font color="#4A610B" style="padding-right:10px;">Request Pending</font>';
		}

		bodyStr+='</span></td>';
		bodyStr+='</tr>';
		bodyStr+='</table>';
		bodyStr+='</td>';
		bodyStr+='</tr>';

		bodyStr+='<tr>';
		bodyStr+='<td><div style="border:1px solid #F1F3F5;margin:0px 5px 0px 5px"></div></td>';
		bodyStr+='</tr>';
	}

	bodyStr+='</table>';
	bodyStr+='</div>';

	bodyStr+='<div id="viewAllPersonConnectDiv">';
	bodyStr+='<table width = "100%"><tr>';
	bodyStr+='<td align="right">';
	if(userLoginStatus == "false")
	{
		bodyStr+='<span class="view-all" style="padding-right:10px;font-weight:bold;"> <a href="connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&'+locationIdLabel+'='+locationId+'&'+locationNameLabel+'='+locationName+'" class="view-all">Login</a> </span>';
		bodyStr+='<span class="view-all" style="padding-right:10px;font-weight:bold;"> <a href="freeUserRegistration.action">Register</a> </span>';
	}
	loginUserId=userLoginId;
	//bodyStr+='<span class="connectAncSpan"> <a href="connectPeopleAction.action" class="connectAnc">Redirect To User Page</a> </span>';
	bodyStr+='<span class="view-all"> <a href="javascript:{}" style="color:#514830;cursor:pointer;" onclick="showAllConnectPeopleWindow(\''+locationId+'\',\''+locationName+'\',\''+userLoginId+'\',\''+locationType+'\')">View All</a> </span>';
	//bodyStr+='<span class="connectAncSpan"> | </span>';
	//bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" class="connectAnc"> Connect </a> </span>';
	bodyStr+='</td>';
	bodyStr+='</tr></table>';
	bodyStr+='</div>';

	if(bodyElmt)
		bodyElmt.innerHTML=bodyStr;

}

function showConnectConfirmDialogBox(userId,userName,constituency,userLoginId,locationId,locationType,locationName)
{	
	var str = '';	
	str += '<table width="100%">';
	str += '<tr>';
	str += '<td width="75%">';
	str += '<div class="connectPeople_body_name">'+userName+'</div><div class="connectPeople_body_constituency">'+constituency+'</div>';
	str += '</td>';
	str += '<td rowspan="2" align="center" width="25%">';
	str += '<img height="100" width="95" src="/PartyAnalyst/images/icons/indexPage/human.jpg">';
	str	+= '<div id="connectButtonDiv"><input type="button" class="connectButton" onclick="doConnectPeople(\''+userId+'\',\''+userLoginId+'\',\''+locationId+'\',\''+locationType+'\',\''+locationName+'\')" value="Connect"/></div>';
	str += '</td>';
	str += '</tr>';
	str += '<tr>';
	str += '<td width="75%"><fieldset id="connectUserMsgFieldSet"><legend>Message</legend>';
	str += '<textarea id="connectUserMsg" onkeyup="limitText(\'connectUserMsg\',\'maxcount\',200)" rows="3" cols="38" style="background-color:#ffffff;"></textarea>';
	str +='<div id="limitDiv">';
	str +='<table style="width:100%;"><tr>';
	str +='<td align="left" style="width:50%;color:#4B4242;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
	str +='<td align="right" style="width:50%;color:#4B4242;"><div>Max 200 chars</div></td>';
	str +='</tr></table>';
	str +='</div>';	
	str += '</fieldset></td>';
	str += '</tr>';
	str += '</table>';
	str	+= '<div id="connectStatus"></div>';
	
	//str	+= '<input type="button" class="cancelButton" onclick="closeConnectPeoplePopup()" value="Cancel"/>';
	str	+= '</div>';
	
	/*var connectPopupPanel = new YAHOO.widget.Dialog("connectPeoplePopup", {      
				 width:'500px',
                 fixedcenter : true, 
                 visible : true,
                 constraintoviewport : true, 
        		 iframe :false,
        		 modal :false,
        		 hideaftersubmit:true,
        		 close:true,
				 draggable:true
       });	 
	
	connectPopupPanel.setHeader("Connect To "+userName);
    connectPopupPanel.setBody(str);
    connectPopupPanel.render();*/

	$( "#connectPeoplePopup" ).dialog({
			title:"Connect To "+userName,
			autoOpen: true,
			show: "blind",
			width: 500,
			minHeight:300,
			modal: true,
			hide: "explode"
		});
	
	var elmt = document.getElementById("allConnectedUsersDisplay_main");
	if(elmt)
		elmt.innerHTML = str;

}

function doConnectPeople(connectUserId,userLoginId,locationId,locationType,locationName)
{	
	var connectTextAreaElmt = document.getElementById("connectUserMsg");
	var connectMsg = connectTextAreaElmt.value;
	var jsObj ={
				locationId:locationId,			
				locationName:locationName,
				connectUserId:connectUserId,
				connectMessage:connectMsg,
				userId:userLoginId,
				locationType:locationType,
				task:"connectToUser"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "connectToUserAction.action?"+rparam;					
	callAjax(jsObj,url);
}

function closeConnectPanel(jsObj,results)
{ 
	var elmt = document.getElementById("connectStatus");
	var buttonElmt = document.getElementById("connectButtonDiv");
	
	if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{
		buttonElmt.innerHTML = '';
		elmt.innerHTML = '<blink><font color="green" style="font-weight:bold;"> Requested sent successfully.</font></blink>';
		buildConnectUsersContent(results.candidateVO,connectDivId,jsObj.locationType,jsObj.locationId,jsObj.locationName,connectUserLoginStatus,jsObj.userId);		
		var t=setTimeout("closeConnectPopup()",2000);
	}
	else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
		elmt.innerHTML = '<font color="red" style="font-weight:bold;"><blink> Request Cannot be sent due to some technically difficulty.</blink></font>';
		
}

function closeConnectPopup()
{
	var elmt = document.getElementById("connectPeoplePopup_outer");
	if(!elmt)
		return;
	var str = '<div id="connectPeoplePopup"></div>';

	elmt.innerHTML = str;
}

function showAllConnectPeopleWindow(locationId,locationName,userLoginId,locationType)
{	
	
	connetLocationType = locationType;	

	var str = '';
	str += '<div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div>';
	
	/*var connectPopupPanel = new YAHOO.widget.Dialog("connectPeoplePopup", {      
				 width:'500px',
                 fixedcenter : true, 
                 visible : true,
                 constraintoviewport : true, 
        		 iframe :false,
        		 modal :false,
        		 hideaftersubmit:true,
        		 close:true,
				 draggable:true
       });	 
	
	connectPopupPanel.setHeader("People Connected to "+locationName);   
	connectPopupPanel.setBody(str);
    connectPopupPanel.render();*/

	$( "#connectPeoplePopup" ).dialog({
			title:"People Connected to "+locationName+" "+locationType,
			autoOpen: true,
			show: "blind",
			width: 700,
			minHeight:400,
			modal: true,
			hide: "explode"
		});

	//------

	var jsObj ={
				locationId:locationId,							
				locationName:locationName,
				userId:userLoginId,
				locationType:locationType,
				task:"getAllConnectedUsers"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllConnectedUserAction.action?"+rparam;	

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:6,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllConnectedUsersInPanel(jsObj,results);
		}
	});
	custom_paginator.initialize();

	//-------

	//getAllConnectedUserInLocation(locationId,locationName,userLoginId,locationType);
}

/*function getAllConnectedUserInLocation(locationId,locationName,userLoginId,locationType)
{	
	var jsObj ={
				locationId:locationId,							
				locationName:locationName,
				userId:userLoginId,
				locationType:locationType,
				task:"getAllConnectedUsers"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllConnectedUserAction.action?"+rparam;					
	callAjax(jsObj,url);
}*/

function showAllConnectedUsersInPanel(jsObj,results)
{	
	
	var str = '';	
	var elmt = document.getElementById("allConnectedUsersDisplay_main");
	var totalResultsCount = results.totalResultsCount;
	var connectedPeopleCount = results.connectedPeopleCount;
	var notConnectedPeopleCount = (results.totalResultsCount - results.connectedPeopleCount);

	var users = results.candidateVO;

	if(results.resultStatus.exceptionEncountered != null || results.resultStatus.resultCode !=0)
	{
		str	+= '<div> Data Not Found Due To Some Exeption</div>';
		elmt.innerHTML = str;
		return
	}
	else if(users.length == 0)
	{
		str	+= '<div> No People Connected In '+jsObj.locationName+' '+jsObj.locationType+'</div>';
		elmt.innerHTML = str;
		return
	}

	str	+= '<div id="allConnectPeople_Head">';
	str += '<div class="allConnectPeople_Head_content">';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<td align="left">Total People - '+totalResultsCount+'</td>';
	str += '<td align="left">Connected - '+connectedPeopleCount+'</td>';
	str += '<td align="left">Not Connected - '+notConnectedPeopleCount+'</td>';
	str += '<td align="right">';
	str += '<table border="0" cellpadding="0" cellspacing="0">';
	str += '<tr>';
	str += '<td><img height="20px" src="images/icons/cadreReport/bg_left.png"/></td>';
	str += '<td><div class="allConnectPeople_Head_botton_center_div"><span class="allConnectPeople_Head_botton_center_span" onclick="showsearchFilter()">Options</span></div></td>';
	str += '<td><img height="20px" src="images/icons/cadreReport/bg_right.png"/></td>';
	str += '</tr>';
	str += '</table>';
	str += '</td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';
	str += '<div class="allConnectPeople_Head_content" style="display:none;" id="filterViewContent">';
	str += '<table width="100%">';
	str += '<tr>';
	if(jsObj.locationType == "DISTRICT")
	{
		str += '<th>By Constituency:</th>';
		str += '<th><select id="connectConstituencySelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\')">';
		str += '<option value="0">All</option>';
		for(var consti=0; consti<constituencies.length;consti++)
			str += '<option value="'+constituencies[consti].id+'">'+constituencies[consti].name+'</option>';
		str += '</select></th>';
	}
	str += '<th>By Status:</th>';
	str += '<th><select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\')">';
	str += '<option value="0">All</option>';
	for(var status=0; status<connectStatus.length;status++)
		str += '<option value="'+connectStatus[status].id+'">'+connectStatus[status].name+'</option>';
	str += '</select></th>';
	str += '<th>By Name</th>'; 
	str += '<td><input type="text" id="connectStatusTextBox" onkeyup="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\')"></td>';
	str += '</tr>';
	str += '</table>';
	
	str += '</div>';	
	str += '</div>';
	str	+= '<div id="allConnectPeople_body">';	
	str += buildAllConnectUserString(users);
	str	+= '</div>';
	
	str	+= '<div id="allConnectPeople_footer" style="display:none;">';
	str	+= '<table width="100%">';
	str	+= '<tr>';	
	str += '<td width="80%" style="color:#754815"> Do you Want to connect to <span id="allConnectPeople_footer_span"></span> ?</td>';
	str += '<td width="20%" align="right">';
	str += '<div class="ConnectStatusButtonClass" onclick="connectUserSetPeople()">';
	str += '<table>';
	str += '<tr>';
	str += '<td> Connect Now </td>';
	str += '<td><a style="margin: 0px 10px;" href="javascript:{}"><img style="border:none;" src="images/icons/accept.png"></img></a></td>';	
	str += '</tr>';
	str += '</table>';
	str += '</div>';
	str += '</td>';
	str += '</tr>';	
	str += '<tr>';
	str += '<td width="80%">';
	str += '<div id="connectMessageDiv">';
	str += '<div style="color:#595B3E">Add Custom Message</div>';
	str += '<div><textarea id="AllConnectUserMsg" onkeyup="limitText(\'AllConnectUserMsg\',\'maxcount\',200)" rows="3" cols="40"></textarea></div>';
	str +='	<div id="limitDiv">';
	str +='	<table style="width:100%;"><tr>';
	str +='	<td align="left" style="width:50%;color:#4B4242;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
	str +='	<td align="right" style="width:50%;color:#4B4242;"><div>Max 200 chars</div></td>';
	str +='	</tr></table>';
	str +='	</div>';	
	str += '</td>';
	str += '<td width="20%" align="right" valign="top">';
	str += '<div class="ConnectStatusButtonClass" onclick="hideConfirmDiv()">';
	str += '<table>';
	str += '<tr>';
	str += '<td>Connect Later </td>';
	str += '<td><a style="margin: 0px 10px;" href="javascript:{}"><img style="border:none;" src="images/icons/reject.png"></img></a></td>';
	str += '</tr>';
	str += '</table>';
	str += '<div>';
	str += '</td>';

	/*str	+= '<td width="30%" align="center" style="color:#FFFFFF;background-color:#306397;">Message</td>';
	str	+= '<td width="60%" align="left">';
	str += '<textarea id="AllConnectUserMsg" onkeyup="limitText(\'AllConnectUserMsg\',\'maxcount\',200)" rows="3" cols="40"></textarea>';
	str +='<div id="limitDiv">';
	str +='<table style="width:100%;"><tr>';
	str +='<td align="left" style="width:50%;color:#4B4242;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
	str +='<td align="right" style="width:50%;color:#4B4242;"><div>Max 200 chars</div></td>';
	str +='</tr></table>';
	str +='</div>';	
	str += '</td>';
	str	+= '<td width="10%" align="center"><div id="allConnectPeopleButtonDiv"><input type="button" class="connectButton" onclick="connectUserSetPeople()" value="Connect"/></div></td>';
	str	+= '</tr>';
	str += '<tr>';
	str += '<td colspan="3" align="center"><div id="allConnectPeopleStatusTextDiv"></div></td>';*/
	str += '</tr>';
	str += '<tr>';
	str += '<td >';
	str += '<div id="allConnectPeopleStatusTextDiv"></div>';
	str += '</td>';
	str += '<td></td>';
	str += '</tr>';
	str	+= '</table>';
	str	+= '</div>';
	
	elmt.innerHTML = str;
}

function connectUserSetPeople()
{
	
	//var elements = document.getElementsByName("connectUserCheck");
	var statusElmt = document.getElementById("allConnectPeopleStatusTextDiv");

	var users = new Array();

	if(connectRequestedUserId == "")
		return;
	/*
	if(elements.length == 0)
		return;
	
	for(var i=0; i<elements.length; i++)
	{
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].checked==true)
				users.push(elements[i].value);
		}
	}*/
	
	users.push(connectRequestedUserId);
	if(users.length == 0)
	{
		statusElmt.innerHTML = '<font color="red">Atleast One User Has To Selected To Connect</font>';
		return;
	}
	else
		statusElmt.innerHTML = '';

	var connectTextAreaElmt = document.getElementById("AllConnectUserMsg");
	var connectMsg = connectTextAreaElmt.value;
	
	if(connetLocationType=="DISTRICT"){
		var jsObj ={
				connetLocationId:districtId,				
				connectUserIds:users,
				connectMessage:connectMsg,
				userId:connectUserLoginId,
				locationType:connetLocationType,
				task:"connectUserSet"
			 };
	}else{
		var jsObj ={
				connetLocationId:connetLocationId,				
				connectUserIds:users,
				connectMessage:connectMsg,
				userId:connectUserLoginId,
				locationType:connetLocationType,
				task:"connectUserSet"
			 };
	}
	
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "connectToUserSetAction.action?"+rparam;					
	callAjax(jsObj,url);
}

function hideConfirmDiv()
{
	var elmt = document.getElementById("allConnectPeople_footer");

	if(elmt)
		$("#allConnectPeople_footer").slideUp();
}

function showConfirmDiv(name,id)
{
	var elmt = document.getElementById("allConnectPeople_footer");
	var spanElmt = document.getElementById("allConnectPeople_footer_span");

	if(elmt)
		$("#allConnectPeople_footer").slideDown();
	connectRequestedUserId = id;
	if(spanElmt)
		spanElmt.innerHTML = name; 
}

function buildAllConnectUserString(users)
{
	var str = '';
	for(var i=0; i<users.length; i++)
	{
		str += '<div class="allConnectPeopleDataDiv_main">';
		str += '<table width="100%">';
		str += '<tr>';

		var imageStr = "pictures/profiles/"+users[i].image;
      
		if(users[i].image == null)
			str += '<td valign="top" width="15%"><img height="45" width="50" src="/PartyAnalyst/images/icons/indexPage/human.jpg"></td>';
		else
			str += '<td valign="top" width="15%"><img height="45" width="50" src="'+imageStr+'"></td>';		
		str += '<td valign="top" width="55%">';
		str += '<div class="connectPeople_body_name">'+users[i].candidateName+'</div>';
		str += '<div><span class="connectPeople_body_constituency">'+users[i].constituencyName.toLowerCase()+'</span></div>';			
		str += '</td>';
		str += '<td valign="middle" width="30%" align="right">';
		if(users[i].status == "NOT CONNECTED")
		{
			str += '<table border="0" cellpadding="0" cellspacing="0">';
			str += '<tr>';
			str += '<td><img height="20px" src="images/icons/cadreReport/bg_left.png"/></td>';
			if(userLoginStatus == "false"){
				str += '<td><div class="allConnectPeople_Head_botton_center_div"><a style="color:white;" class="allConnectPeople_Head_botton_center_span" href=connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&'+locationIdLabel+'='+locationId+'&'+locationNameLabel+'='+locationName+'">Connect</a></div></td>';
			}
			else{
			str += '<td><div class="allConnectPeople_Head_botton_center_div"><span class="allConnectPeople_Head_botton_center_span" onclick="showConfirmDiv(\''+users[i].candidateName+'\',\''+users[i].id+'\')">Connect</span></div></td>';
			}
			str += '<td><img height="20px" src="images/icons/cadreReport/bg_right.png"/></td>';
			str += '</tr>';
			str += '</table>';
		}
		else if(users[i].status == "PENDING" || users[i].status == "LOGGED_USER")
		{
			str += '<div>'+users[i].status+'</div>';
		}
		else 
		{
			str += '<span class="connectPeople_body_status">';
			str += '<a href="javascript:{}" onclick="showMailPopup(\''+users[i].id+'\',\''+users[i].candidateName+'\',\'Message\')">';
			str += '	<img title="Send Message" width="22" height="20" style="border:none;" src="images/icons/candidatePage/contact.png">';
			str += '</a>';
			//str += users[i].status;
			str += '</span>';
			str += '<div id="connectPeopleMessagePopup_main" class="yui-skin-sam"><div id="connectPeopleMessagePopup"></div></div>';
		}
		str += '</td>';
		str += '</tr>';




		/*str += '<td rowspan="2" width="20%"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"></td>';
		str += '<td width="80%"><div class="connectPeople_body_name">'+users[i].candidateName+'</div></td>';				
		if(users[i].status == "NOT CONNECTED")
			str += '<td rowspan="2" width="10%"><input type="checkbox" name="connectUserCheck" value="'+users[i].id+'"/></td>';
		else
			str += '<td rowspan="2" width="10%">&nbsp</td>';		
		str += '</tr>';
		str += '<tr>';
		str += '<td width="80%">';
		str += '<table width="100%">';
		str += '<tr>';
		str += '<td width="50%" align="left"><span class="connectPeople_body_constituency">'+users[i].constituencyName+'</span></td>';
		if(users[i].status == "NOT CONNECTED" || users[i].status == "PENDING" || users[i].status == "LOGGED_USER")
		{
			str += '<td width="50%" align="right"><span class="connectPeople_body_status">'+users[i].status+'</span></td>';
		}else
		{
			str += '<td width="50%" align="right"><span class="connectPeople_body_status"> <a href="javascript:{}" onclick="showMailPopup(\''+users[i].id+'\',\''+users[i].candidateName+'\',\'Message\')">Send a Message</a> CONNECTED </span> <div id="connectPeopleMessagePopup_main" class="yui-skin-sam"><div id="connectPeopleMessagePopup"></div></div></td>';
		}
		str += '</tr>';*/
		str += '</table>';
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
	}	
	str += '<div class="custom_paginator_class"></div>';
	return str;
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
	str += '<td><input type="button" name="connectButton" value="Send" onclick="sendMessageToConnectedUser(\''+id+'\',\''+type+'\')"></td>';
	str += '</tr>';	
	str += '</table>';
	str += '<table style="text-align:center;width:100%;"><tr><td><div id="confirmationMsg"></div></td></tr></table>';
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

function showMessageConfirmation(results)
{
	var elmt = document.getElementById("confirmationMsg");
	var str = '';
	if(results.resultCode == 0){
		str+='<blink><font color="green">Message Sent Successfully..</font></blink>';
	}else{
		str+='<div style="color:red;">There was an error in processing your request</div>';
	}
	elmt.innerHTML = str;
}


function showAllConnectedUsersStatus(jsObj,results)
{
	
	var elmt = document.getElementById("allConnectPeopleStatusTextDiv");

	if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{		
		elmt.innerHTML = '<blink><font color="green" style="font-weight:bold;"> Requested sent to selected users successfully.</font></blink>';
		showAllConnectedUsersInPanel(jsObj,results);		
		buildConnectUsersContent(results.candidateVO,connectDivId,connetLocationType,connetLocationId,connetLocationName,connectUserLoginStatus,connectUserLoginId);
	}
	else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
		elmt.innerHTML = '<font color="red" style="font-weight:bold;"><blink> Request Cannot be sent to selected users due to some technically difficulty.</blink></font>';
}


function getAllConnectedUsersByFilterView(locationType)
{
	
	var connectConstiSelectElmtValue = '';
	var connectConstiArray = new Array();	
	
	if(locationType == "DISTRICT")
	{
		var connectConstiSelectElmt = document.getElementById("connectConstituencySelect");
		if(connectConstiSelectElmt)
			connectConstiSelectElmtValue = connectConstiSelectElmt.options[connectConstiSelectElmt.selectedIndex].value;
		
		if(connectConstiSelectElmtValue == 0)
		{
			for(var consti=0; consti<constituencies.length;consti++)
				connectConstiArray.push(constituencies[consti].id);		
		}
		else
			connectConstiArray.push(connectConstiSelectElmtValue);
	}
	else
	{
		connectConstiArray.push(connetLocationId);
	}
	
	var connectStatusSelectElmt = document.getElementById("connectStatusSelect");
	var connectStatusSelectElmtValue = connectStatusSelectElmt.options[connectStatusSelectElmt.selectedIndex].value;
	var connectStatusSelectElmtText  = connectStatusSelectElmt.options[connectStatusSelectElmt.selectedIndex].text;
	
	var textElmt = document.getElementById("connectStatusTextBox");
	var textValue = textElmt.value;	

	var jsObj ={
				constituencyIds:connectConstiArray,				
				statusText:connectStatusSelectElmtText,
				nameString:textValue,
				task:"getAllConnectedUsersByFilterView"
			 };
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllConnectedUsersByFilterViewAction.action?"+rparam;					
	/*callAjax(jsObj,url);*/

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:5,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllConnectedUsersInPanelByFilterView(jsObj,results);
		}
	});
	custom_paginator.initialize();
	
}

function showAllConnectedUsersInPanelByFilterView(jsObj,results)
{
	var str = '';	
	var elmt = document.getElementById("allConnectPeople_body");
	var elmtFooter = document.getElementById("allConnectPeople_footer");

	var users = results.candidateVO;

	if(results.resultStatus.exceptionEncountered != null || results.resultStatus.resultCode !=0)
	{
		str	+= '<div> Data Not Found Due To Some Exeption</div>';
		elmt.innerHTML = str;
		return
	}
	else if(users.length == 0)
	{
		str	+= '<div> No people connected in this district with this view..</div>';
		elmt.innerHTML = str;
		return
	}
	else
	{
		str += buildAllConnectUserString(users);			
		elmt.innerHTML = str;
	}
}

function showsearchFilter()
{
	 $("#filterViewContent").slideToggle("slow");
}

function limitText(limitField, limitCount, limitNum)
{		
	var limitFieldElmt = document.getElementById(limitField);
	var limitCountElmt = document.getElementById(limitCount);

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
	}
}
