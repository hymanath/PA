var connectStatus = [];
var constituencies = [];
var connetLocationId = '';

$("document").ready(function(){
	
	 $("#friendsLink").click(function(){
		var jsObj ={	
			task:"getLatestFriendsList"
	};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getLatestFriendsList.action?"+rparam;						
		callAjax(jsObj,url);
		
	});

	$("#requestLink").click(function(){
		var jsObj ={
			task:"getAllRequestMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getRequestMessagesForUserAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	});

	 
	$("#messagesLink").click(function(){
		
		var jsObj ={
			task:"getRequestMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllRequestMessagesForAUserAction.action?"+rparam;	
		
		callAjax(jsObj,url);
	});

	$("#specialPageLink").click(function(){
		
		var jsObj ={
			task:"getSpecialPages"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSpecialPageAction.action?"+rparam;	
		
		callAjax(jsObj,url);

	});

	$("#districtPeopleLink").click(function(){
	
	var locationId = districtId;
	var locationType = "DISTRICT";
	var locationName = districtName;
	var userLoginId = loginUserId;
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




	});



	$(".connectLink").each(function(){
		
		$(this).click(function(){
		$("#allConnectedUsersDisplay_main").children().remove();			
		var userId = $(this).closest(".connectPeopleDiv").find(".userId").val();
		var userName = $(this).closest(".connectPeopleDiv").find(".userName").val();
		var constituencyName = $(this).closest(".connectPeopleDiv").find(".constituencyName").val();
		var userLoginId = loginUserId;
		var locationId = constituencyId;
		var locationType = 'CONSTITUENCY';
		var locationName = constituencyName;
		
		$( "#connectPeoplePopup" ).dialog({
			title:"Connect To  "+userName,
			autoOpen: true,
			show: "blind",
			width: 500,
			minHeight:300,
			modal: true,
			hide: "explode"
		});

		var div = $("<div class='connectPeoplePopupInnerDiv'></div>");
		var Name=$("<label>"+userName+"</label>");
		var constituencyName = $("<label>"+constituencyName+"</label>");
		var message = $("<label>Message</label>");
		var textArea = $("<textarea id='connectUserMsg'></textarea>");
		var image = $('<img height="100" width="95" src="/PartyAnalyst/images/icons/indexPage/human.jpg">');
		var connectBtn = $('<input type="button" value="Connect" id="connectPeopleLink"/>');
		var connectedPersonId = $('<input type="hidden" value='+userId+' id="connectedPersonId"/>');
		var errorDiv = $("<div id='errorMsgDiv'></div>")
		div.append(errorDiv);
		div.append(Name);
		div.append(constituencyName);
		div.append(message);
		div.append(textArea);
		div.append(image);
		div.append(connectBtn);
		div.append(connectedPersonId);
		$('#allConnectedUsersDisplay_main').append(div);
		});
	});



	$("#connectPeopleLink").live("click",function(){
		$("#errorMsgDiv").html('');
		var connectUserId = $(this).closest(".connectPeoplePopupInnerDiv").find('#connectedPersonId').val();
		var connectUserMsg = $.trim($("#connectUserMsg").val());
		var errorMsgDiv = $("#errorMsgDiv");
		var locationType = 'CONSTITUENCY';
		var locationId = constituencyId;
		var locationName = constituencyName;
		var userId = loginUserId;
		 if(connectUserMsg.length > 200)
		{
			$("#errorMsgDiv").html('<font style="color:red">Message Should be lessthan 200 characters.</font>');
			return;
		}
		
		disableButton("connectPeopleLink");
		$("#connectPeoplePopup").dialog('close');
		var jsObj ={
				locationId:locationId,			
				locationName:locationName,
				connectUserId:connectUserId,
				connectMessage:connectUserMsg,
				userId:loginUserId,
				locationType:locationType,
				task:"connectToUser"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "connectToUserAction.action?"+rparam;					
	callAjax(jsObj,url);
		
		
	});


	


});//End ready



function callAjax(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
					
					results = YAHOO.lang.JSON.parse(o.responseText);
					
					if(jsObj.task == "getAllRequestMessagesForUser")
						showAllRequestMessagesForUser(results,jsObj);
										
					else if(jsObj.task == "getLatestFriendsList")
						getFriendsListForUser(results);				
					

					else if(jsObj.task == "getRequestMessagesForUser")
						showRequestedMessagesForAUser(results);
					
					else if(jsObj.task == "sendMessageToConnectUser")
					{
						if(jsObj.type=="Connect"){
							location.reload(true);//For refreshing the page...
						}else{
							showMessageSentConfirmation(results);
						}
					}
					
					else if(jsObj.task == "acceptRequest")
					{
						$('#requestLink').trigger('click');
						showStatus(results,jsObj);						
						getLatestFriendsList();						
					}
					
					else if(jsObj.task == "rejectRequest")
					{
						$('#requestLink').trigger('click');
						showStatus(results,jsObj);
												
					}
					else if(jsObj.task == "blockRequest")
					{
						$('#requestLink').trigger('click');
												
					}
					else if(jsObj.task == "getSpecialPages")
					{
						showSpecialPages(results);
					}
					else if(jsObj.task == "connectToUser")
					{
						closeConnectPanel(jsObj,results);
					}
					
			}catch (e) {   		
			   	//alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}



function getFriendsListForUser(results)
{
	$(".placeholderCenterDiv").children().remove();
	if(results.resultStatusForConnectedPeople.resultCode != "0")
	{
		$(".templateDiv").html('<div>Data could not be retrived due to some technical difficulties</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	else if(results.connectedPeople == "")
	{
		$(".templateDiv").html('<div>There are no connections established till now.</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	
	$("#headerDiv").html('You have total '+results.connectedPeople.length+' connections.');
	for(var i in results.connectedPeople)
	{
		
		var template = $(".templateDiv");
		var templateClone =  template.clone();
		templateClone.removeClass("templateDiv");
		templateClone.find('.connectedPersonName').html(''+results.connectedPeople[i].candidateName+'');
		templateClone.find('.imgClass').html('<img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/>');
		templateClone.find('.constituencyName').html(''+results.connectedPeople[i].constituencyName+'');
		templateClone.find('.districtName').html(''+results.connectedPeople[i].district+'');
		templateClone.find('.stateName').html(''+results.connectedPeople[i].state+'');
		templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a>');
		templateClone.appendTo(".placeholderCenterDiv");
	}

}


function showAllRequestMessagesForUser(results,jsObj){

	$(".placeholderCenterDiv").children().remove();
	if(results.friendRequest ==null)
	{
		$("#headerDiv").html('You have 0 Requests');
		return;
	}
	
	$("#headerDiv").html('You have '+results.friendRequest.length+' Requests');
	for(var i in results.friendRequest){
		var template = $(".templateDiv");
		var templateClone =  template.clone();

		templateClone.removeClass("templateDiv");

		templateClone.find('.connectedPersonName').html(''+results.friendRequest[i].candidateName+'');
		templateClone.find('.imgClass').html('<img height="50" width="55" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/>');
		templateClone.find('.constituencyName').html(''+results.friendRequest[i].message+'');
		templateClone.find('.districtName').html('<input type="button" onclick="acceptRequest('+results.friendRequest[i].id+')" value="Accept" class="acceptButton"></input>');
		templateClone.find('.stateName').html('<input type="button" onclick="rejectRequest('+results.friendRequest[i].id+')" value="Decline" class="rejectButton"></input>');
		templateClone.find('.sendMsg').html('<input type="button" onclick="blockRequest('+results.friendRequest[i].id+')" value="Block this person" class="rejectButton"></input>');
		templateClone.appendTo(".placeholderCenterDiv");
	}
}


function showRequestedMessagesForAUser(results)
{

	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	if(results.resultStatus.resultCode !="0")
	{
		$(".templateDiv").html("Data could not be retrived due to some technical difficulties.").appendTo(".placeholderCenterDiv");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$(".templateDiv").html("No messages has been sent to you.").appendTo(".placeholderCenterDiv");
		return;
	}
		
		$("#headerDiv").html('Unread: '+results.unreadMsgCount+'  Total Messages:'+results.totalMsgCount+'');
		for(var i in results.candidateVO)
		{
		var template = $(".templateDiv");
		var templateClone = template.clone();
		templateClone.removeClass("templateDiv");
		templateClone.find(".imgClass").html(''+results.candidateVO[i].candidateName+'');
		if(results.candidateVO[i].status == "UNREAD")
			templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].message+'');
		else
			templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].message+'');
			templateClone.find(".constituencyName").html(''+results.candidateVO[i].postedDate+'');
			templateClone.find(".stateName").html('<a href="javascript:{}" onclick="showMailPopup('+results.candidateVO[i].id+',\''+results.candidateVO[i].candidateName+'\',\'Message\')">reply</a>');
		templateClone.appendTo(".placeholderCenterDiv");
		}
}


function showMailPopup(id,name,type)
{
	$("#allConnectedUsersDisplay_main").children().remove();
	$( "#connectPeoplePopup").dialog({
			title:"Send Message to "+name,
			autoOpen: true,
			show: "blind",
			width: 400,
			minHeight:300,
			modal: true,
			hide: "explode"
		});
		
		var div = $("<div class='connectPeoplePopupInnerDiv'></div>");
		var errorDiv = $("<div id='ErrorMsgDivId'></div>");
		var label = $("<label>Message</label>");
		var textarea = $("<textarea id='connectMessageText'></textarea><br>");
		var button = $("<input id='sendMessageButtonId' type='button' value='send' onclick='sendMessageToConnectedUser("+id+",\""+type+"\")'/>");
		div.append(errorDiv);
		div.append(label);
		div.append(textarea);
		div.append(button);
		
		$('#allConnectedUsersDisplay_main').append(div);


}

function sendMessageToConnectedUser(userId,type)
{	
	var connectMsg = $.trim($("#connectMessageText").val());
	if(connectMsg == "")	
	{
	  $("#ErrorMsgDivId").html('<font style="color:red">Please Enter Message.</font>');
	  return;
	}
	
	else if(connectMsg.length > 300)
	{
	  $("#ErrorMsgDivId").html('<font style="color:red">Message Should be lessthan 300 characters.</font>');
	  return;
	}
		
	disableButton("sendMessageButtonId");
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
	var elmt = $("#ErrorMsgDivId");
	enableButton("sendMessageButtonId");
    if(results.resultCode == 0)
	{
		$("#connectMessageText").val('');
		$("#ErrorMsgDivId").html('<blink><font color="green">Message Sent Successfully..</font></blink>');
		setTimeout('self.close();',2000);
		
	}
	else
	  $("#ErrorMsgDivId").html('<font color="red">Message Cannot Be sent Due To Some Technical Difficulties<font>');
	
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
 function showStatus(results,jsObj)
{
	
	/*if(results.resultCode==0){
		
		if(jsObj.type == "accept")
			str += '<span style="color:green;font-weight:bold;">Successfuly Accepted</span>';	
		else if(jsObj.type == "reject")
			str += '<span style="color:green;font-weight:bold;">Successfuly Rejected</span>';	
	}else if(results.resultCode==1){
		str += '<span style="color:red;font-weight:bold;">There was an error in processing your request</span>';
	}		
	str += '</div>';
	elmt.innerHTML = str;*/
}

function showSpecialPages(results)
{
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	if(results.length == 0)
	{
		$(".templateDiv").html("Special Pages Are Not Available.");
		return;
	}
	for(var i in results)
	{
		var template = $(".templateDiv");
		var templateClone = template.clone();
		templateClone.removeClass('templateDiv');
		templateClone.find(".connectedPersonName").html(''+results[i].title+'');
		templateClone.find(".imgClass").html('<img src="'+results[i].eventImagePath+'"/>');
		templateClone.find(".constituencyName").html(''+results[i].description+'');
		templateClone.appendTo(".placeholderCenterDiv");
	}
}

function closeConnectPanel(jsObj,results)
{ 
	var connectUserMsg = $("#connectUserMsg").val('');
	if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{
		$("#errorMsgDiv").html('<blink><font color="green" style="font-weight:bold;"> Requested sent successfully.</font></blink>');
		//var t=setTimeout("closeConnectPopup()",2000);
		//buildConnectUsersContent(results.candidateVO,connectDivId,jsObj.locationType,jsObj.locationId,jsObj.locationName,connectUserLoginStatus,jsObj.userId);		
		return;
	}
	else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
	{
		$("#errorMsgDiv").html('<font color="red" style="font-weight:bold;"><blink> Request Cannot be sent due to some technically difficulty.</blink></font>');
		return;
	}
	
}


function showAllConnectedUsersInPanel(jsObj,results)
{
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();	
	var totalResultsCount = results.totalResultsCount;
	var connectedPeopleCount = results.connectedPeopleCount;
	var notConnectedPeopleCount = (results.totalResultsCount - results.connectedPeopleCount);
	var users = results.candidateVO;
	var str = '';
	var filterDiv = $("<div id='filterDiv'></div>");

	if(results.resultStatus.exceptionEncountered != null || results.resultStatus.resultCode !=0)
	{
		$(".templateDiv").html('<div> Data Not Found Due To Some Exeption</div>').appendTo(".placeholderCenterDiv");
		return
	}
	else if(users.length == 0)
	{
		$(".templateDiv").html('<div> No People Connected In '+jsObj.locationName+' '+jsObj.locationType+'</div>').appendTo(".placeholderCenterDiv");
		return
	}
	/* filterDiv.append("<span>Total People - "+totalResultsCount+"</span>");
	filterDiv.append("<span>Connected - "+connectedPeopleCount+"</span>");
	filterDiv.append("<span>Not Connected - "+notConnectedPeopleCount+"</span>");*/

	filterDiv.append("<p><span>People You May Know</span><input id='connectStatusTextBox' type='text' value='' onkeyup='getAllConnectedUsersByFilterView(\""+jsObj.locationType+"\") '/></p>");
	if(jsObj.locationType == "DISTRICT")
	{
		str +='<span>By Constituency: </span>';
		str +='<select id="connectConstituencySelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\')">';
		str +='<option value="0">All</option>';
		for(var i in constituencies)
			str += '<option value="'+constituencies[i].id+'">'+constituencies[i].name+'</option>';
		str += '</select>';
	}

	str +='<span>By Status: </span>';
	str +='<select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\')">';
	for(var i in connectStatus)
	str +='<option id='+connectStatus[i].id+'>'+connectStatus[i].name+'</option>';
	str +='</select>';
	filterDiv.append(str);
	$("#headerDiv").append(filterDiv);
	 for(var i in results.candidateVO)
	{
		var imageStr = "pictures/profiles/"+results.candidateVO[i].image;
		var image = results.candidateVO[i].image;
		var template = $(".templateDiv");
		var templateClone = template.clone();
		templateClone.removeClass("templateDiv");
		if(image == null)
			templateClone.find(".imgClass").html('<img width="50" height="45" src="/PartyAnalyst/images/icons/indexPage/human.jpg">');
		else
			templateClone.find(".imgClass").html('<img height="45" width="50" src="pictures/profiles/"'+results.candidateVO[i].image+'" />');
			templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].candidateName+'');
			templateClone.find(".constituencyName").html(''+results.candidateVO[i].constituencyName.toLowerCase()+'');
			//templateClone.find(".districtName").html(''+results.candidateVO[i].candidateName+'');
			//templateClone.find('.stateName').html(''+results.connectedPeople[i].state+'');
			//templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a>');
			templateClone.appendTo(".placeholderCenterDiv");
			
	}

	
}

function getAllConnectedUsersByFilterView(locationType)
{
	
	var connectConstiSelectElmtValue = '';
	var connectConstiArray = new Array();	
	
	if(locationType == "DISTRICT")
	{
		var connectConstiSelectElmtValue = $("#connectConstituencySelect").val();
		
		if(connectConstiSelectElmtValue == 0)
		{
			for(var i in constituencies)
				connectConstiArray.push(constituencies[i].id);		
		}
		else
			connectConstiArray.push(connectConstiSelectElmtValue);
	}
	else
	{
		connectConstiArray.push(connetLocationId);
	}
	var connectStatusSelectElmt = $("#connectStatusSelect").val();
	var statusText = $('#connectStatusSelect option:selected' ).text();
	var textValue = $.trim($("#connectStatusTextBox").val());
	
	var jsObj ={
				constituencyIds:connectConstiArray,				
				statusText:statusText,
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

/* function showAllConnectedUsersInPanelByFilterView(jsObj,results)
{
	var users = results.candidateVO;
	
	$(".placeholderCenterDiv").children().remove();
	if(results.resultStatus.exceptionEncountered != null || results.resultStatus.resultCode !="0")
	{
		$("#headerDiv").html('');
		$(".templateDiv").html('<div> Data Not Found Due To Some Exeption</div>').appendTo(".placeholderCenterDiv");
		return;
	}
	else if(users.length == 0)
	{
		$("#headerDiv").html('');
		$(".templateDiv").html('<div> Data Not Found Due To Some Exeption</div>').appendTo(".placeholderCenterDiv");
		return;
	}
	else
	{
		for(var i in results.candidateVO)
		{
			var imageStr = "pictures/profiles/"+results.candidateVO[i].image;
			var image = results.candidateVO[i].image;
			var template = $(".templateDiv");
			var templateClone = template.clone();
			templateClone.removeClass("templateDiv");
			if(image == null)
				templateClone.find(".imgClass").html('<img width="50" height="45" src="/PartyAnalyst/images/icons/indexPage/human.jpg">');
			else
				templateClone.find(".imgClass").html('<img height="45" width="50" src="pictures/profiles/"'+results.candidateVO[i].image+'" />');
				templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].candidateName+'');
				templateClone.find(".constituencyName").html(''+results.candidateVO[i].constituencyName.toLowerCase()+'');
				//templateClone.find(".districtName").html(''+results.candidateVO[i].candidateName+'');
				//templateClone.find('.stateName').html(''+results.connectedPeople[i].state+'');
				//templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a>');
				templateClone.appendTo(".placeholderCenterDiv");
			
		}
	}
}*/
