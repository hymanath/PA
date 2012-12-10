var connectStatus = [];
var constituencies = [];
var connetLocationId = '';
var uploadPicStatus = false;
var refreshTime=5;
var uploadResult;

$("document").ready(function(){
	
	 $("#friendsLink").click(function(){
		var jsObj ={
			task:"getLatestFriendsList"
	};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getLatestFriendsListAction.action?"+rparam;						
		callAjax1(jsObj,url);
		
	});

	$("#requestLink").click(function(){
		var jsObj ={
			task:"getAllRequestMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getReqMessagesForUserAction.action?"+rparam;						
		callAjax1(jsObj,url);
		
	});

	 
	$(".messagesLink").click(function(){
		
		var jsObj ={
			task:"getRequestMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllRequestMessagesForAUserAction.action?"+rparam;	
		
		callAjax1(jsObj,url);
	});
	
	$("#specialPageLink").click(function(){
		
		var jsObj ={
			task:"getSpecialPages"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSpecialPageAction.action?"+rparam;	
		
		callAjax1(jsObj,url);
	});

	//subscriptions

	 $(".subscriptionsLink").click(function(){
		
		var jsObj ={
			task:"getUserScriptions"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getUserSubScriptionsAction.action?"+rparam;	
		
		callAjax1(jsObj,url);

	});


	$('.assessPoliticianLink').click(function(){
		getAllPostedReasonsForUser();
		var type = "Total";
		var jsObj ={
				task:"getAllPostedReasons_paginator"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getAllPostedReasonsAction.action?"+rparam+"&type="+type+"&sort=candidate&dir=asc";	

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:8,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllPostedReasons_paginator(jsObj,results);
		}
	});
	custom_paginator.initialize();
	

	});

	//problems 

	$(".problemsLink").click(function(){
		var type = 'Total';
		var jsObj ={
			task:"getAllPostedProblems_paginator"
		 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getAllPostedProblemsAction.action?"+rparam+"&type="+type+"&sort=problemId&dir=desc";

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:18,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllPostedProblems_paginator(jsObj,results);
		}
	});
	custom_paginator.initialize();
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
		var userId = $(".userId").val();
		var userName = $(".userName").val();
		var constituencyName = $(".constituencyName").val();
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
		var Name=$("<label style='color:#094776;font-family: Verdana;font-size: 13px;'>"+userName+"</label>");
		var constituencyName = $("<label style='color: #754815;font-family: verdana;font-size: 12px;'>"+constituencyName+"</label>");
		var message = $("<label class='messageLabel'>Message</label>");
		var textArea = $("<textarea id='connectUserMsg'></textarea>");
		var image = $('<img height="100" width="95" src="/PartyAnalyst/images/icons/indexPage/human.jpg" style="clear: both; margin-left: 26px; margin-top: -45px;">');
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
	callAjax1(jsObj,url);
		
		
	});


	$("#connectDistrictPeopleLink").live("click",function(){
		
		$("#errorMsgDiv").html('');
		var connectUserId = $(this).closest(".connectPeoplePopupInnerDiv").find('#connectedPersonId').val();
		var connectMsg = $.trim($("#connectUserMsg").val());
		var errorMsgDiv = $("#errorMsgDiv");
		var connetLocationType = 'DISTRICT';
		var distId = districtId;
		var locationName = districtName;
		var connectUserLoginId = loginUserId;

		var users = new Array();
		users.push(connectUserId);

		 if(connectUserMsg.length > 200)
		{
			$("#errorMsgDiv").html('<font style="color:red">Message Should be lessthan 200 characters.</font>');
			return;
		}
		
		disableButton("connectDistrictPeopleLink");
		$("#connectPeoplePopup").dialog('close');
		
		var jsObj ={
			
				connetLocationId:distId,				
				connectUserIds:users,
				connectMessage:connectMsg,
				userId:connectUserLoginId,
				locationType:connetLocationType,
				task:"connectUserSet"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "connectToUserSetAction.action?"+rparam;					
	callAjax1(jsObj,url);			
	
	});
	

	//subscribed


	//unsubscribed
	/* $('.unSubscribedLink').live("click",function(){
		
		var id = $(this).closest("div").find('.hiddenVarId').val();
		var type = $(this).closest("div").find('.subscripType').val();
			
		var jsObj=
		{		
            time : timeST,	
			id: id,
			task: "unsubscriptionDetails",
			page:"type"
		}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
   callAjax(jsObj,url);
		
	});*/


//change Password



$(".changePwdLink").live("click",function(){
	
	$("#allConnectedUsersDisplay_main").children().remove();
	
	$("#connectPeoplePopup").dialog({
			resizable:false,
			width: 600,
			minHeight:225,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();
		$(".ui-widget-overlay").css("width","1000px");

		var elmt = $("#allConnectedUsersDisplay_main");
		var div = $("<div class='changePwdDiv'></div>");
		div.append('<div id="password_window" style=" background-color: #7898BC; color: #FFFFFF;font-weight: bold;padding: 5px;">Change Password</div>');
		div.append('<div id="password_window_errorMsg"></div>');
		div.append('<img src="images/icons/infoicon.png" />');
		div.append('<span>Fields marked with (<font color="red">*</font>) are mandatory</span>');
		div.append('<div><font color="red">*</font> <span>Current Password</span> <input type="password" id="currentPwdId" name="currentPassword"/></div>');
		div.append('<div><font color="red">*</font> <span>New Password</span> <input type="password" id="newPwdId" name="newPassword"/></div>');
		div.append('<div><font color="red">*</font> <span>Confirm Password</span> <input type="password" id="confirmPwdId" name="confirmPassword"/></div>');
		div.append('<input id="changePWDButton" type="button" value="Change Password"></input>');
		div.append('<input id="cancelButtonID" type="button" value="No"></input>');
		elmt.append(div);
		
  });

   $("#cancelButtonID").live("click",function(){
	 $("#connectPeoplePopup").dialog("destroy");
   });

   $("#changePWDButton").live("click",function(){

		var cpwd = $.trim($("#currentPwdId").val());
		var npwd = $.trim($("#newPwdId").val());
		var cfmpwd = $.trim($("#confirmPwdId").val());
		var errorDiv = $("#password_window_errorMsg");
		errorDiv.html('');		
		if(cpwd.length > 0 &&cpwd.length < 6)
		{
		  errorDiv.html("<font color='red'>Current Password Minimum Of 6 Characters.</font>");
	      return;
		}
	if(npwd.length > 0 &&npwd.length < 6)
	{
	  errorDiv.html("<font color='red'>New Password Minimum Of 6 Characters.</font>");
	  return;
	}
	if(cpwd=="")
	{
     errorDiv.html("<font color='red'>Please enter password.</font>");	
	 return;
	}
     if(npwd.length > 0 && cfmpwd.length > 0 && npwd != cfmpwd)
	{
 	  errorDiv.html("<font color='red'>Passwords do not match.</font>");
       return
	}
	if(cpwd=='')
	{
     errorDiv.html("<font color='red'>Please enter current password.</font>");
	 return;
	}
	if(npwd=='')
	{
      errorDiv.html("<font color='red'>Please enter new password.</font>");
	   return;
	}
	if(cfmpwd=='')
	{
	   errorDiv.html("<font color='red'>Please enter confirm password.</font>");
	   return;
	}
	if(cpwd == npwd)
	{
	  errorDiv.html("<font color='green'>Your new password is same as existing one.</font>");
	  setTimeout("closewdw()",3000);
	  return;
	}
	if(cpwd!='')
	{
	  errorDiv.html("Sending Your Request.Please wait</font>");
	  errorDiv.html('<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">');

      var jsObj={
      crntPassword:cpwd,
      newPassword:npwd,
	  task:"changePassword"	 
	 };
     var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeUserPasswordAction.action?"+rparam;						
	callAjax1(jsObj,url);
	}
  });


//edit picture

$(".editPictureLink").live("click",function(){

	
$("#allConnectedUsersDisplay_main").children().remove();
	
	$("#connectPeoplePopup").dialog({
		resizable:false,
		width: 582,
		minHeight:200,
		show:'slide',
		modal:true
	});
	
	
	$(".ui-dialog-titlebar").hide();

	var elmt = $("#allConnectedUsersDisplay_main");
	var div =$("<div></div>");
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
	str += '<input type="file" size="45" id="photoUploadElmt" name="upload" onchange="previewImg()" style="width:430px;"/>';
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
	div.append(str);
	elmt.append(div);

	/* 
	

	oPushButton1 = new YAHOO.widget.Button("uploadPicButton");  
	
	
	oPushButton1.on("click",function(){
	   
		var uploadPhotoId = document.getElementById("photoUploadElmt").value;
		var str = '<font color="red">';
		if(uploadPhotoId.length == 0)
	     {   
		     str += ' Please Select a image .<br>';
		     document.getElementById("uploadPic_window_status").innerHTML = str;
	     }
		 else{
		 var photoStatusElmt = document.getElementById("uploadPic_window_status");
		 photoStatusElmt.innerHTML = 'Uploading Image. Please Wait... &nbsp<img width="16" height="11" src="images/icons/partypositions.gif"/>'
		
		 getUploadpic();
		}
	});

	*/


	
});

$("#cancelPicButton").live("click",function(){
	$("#connectPeoplePopup").dialog("destroy");
});

$("#uploadPicButton").live("click",function(){
	var uploadPhotoId = $.trim($("#photoUploadElmt").val());
		var str = '<font color="red">';
		if(uploadPhotoId.length == 0)
	     {   
	       $("#uploadPic_window_status").html('Please Select a image .<br>');
		   return;
	     }
		 else
		 {
		   $("#uploadPic_window_status").html('Uploading Image. Please Wait... &nbsp<img width="16" height="11" src="images/icons/partypositions.gif"/>');
			 getUploadpic();
		}
});
});//End ready

function previewImg()
{
	var photoElmt = document.getElementById("photoUploadElmt");
	var photoStatusElmt = $("#uploadPic_window_status");
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
		
	//oPushButton1.set('disabled', true)	
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
		var photoStatusElmt = $("#uploadPic_window_status");
		photoStatusElmt.html("<font color='#473E37'>"+uploadResult+" Page refreshing in "+refreshTime+" seconds.Please wait..</font>");
		refreshTime=refreshTime-1;
		t=setTimeout("showUploadStatus()",1000);
	}
	else
	{
		$("#connectPeoplePopup").dialog("destroy");
		window.location.reload();
	}
}

function callAjax1(jsObj,url){
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
					else if(jsObj.task =="connectUserSet")
					{
						showAllConnectedUsersStatus(jsObj,results);
					}
					else if(jsObj.task =="getUserScriptions")
					{
						showAllUserSubScribedSpecialPagesPages(jsObj,results);
						showAllUserCandidateSubscriptions(jsObj,results);
						showAllUserConstituencySubscriptions(jsObj,results);
						showAllUserPartySubscriptions(jsObj,results);
						
					}
					else if(jsObj.task =="changePassword")
					{
						showChangePwdStatus(results);
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
	clearAllSubscriptionDivs();
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
	
	
	
	$("#headerDiv").html('You have total <span style="color:blue;">'+results.connectedPeople.length+'</span>  connections.');
	
	for(var i in results.connectedPeople)
	{
		
		var template = $(".templateDiv");
		var templateClone =  template.clone();
		templateClone.removeClass("templateDiv");
		templateClone.find('.connectedPersonName').html('<a href="publicProfile.action?profileId='+results.connectedPeople[i].id+'">'+results.connectedPeople[i].candidateName+'</a>');
		templateClone.find('.imgClass').html('<a href="publicProfile.action?profileId='+results.connectedPeople[i].id+'"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/></a>');
		templateClone.find('.constituencyName').html(''+results.connectedPeople[i].constituencyName+'');
		templateClone.find('.districtName').html(''+results.connectedPeople[i].district+'');
		templateClone.find('.stateName').html(''+results.connectedPeople[i].state+'');
		templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a>');
		templateClone.appendTo(".placeholderCenterDiv");
	}

}


function showAllRequestMessagesForUser(results,jsObj){

	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.friendRequest ==null)
	{
		$("#headerDiv").html('You have 0 Requests');
		return;
	}
	
	$("#headerDiv").html('You have <span style="color:blue;">'+results.friendRequest.length+'</span> Requests');
	for(var i in results.friendRequest){
		
		var template = $(".templateDiv");
		var templateClone =  template.clone();
		templateClone.removeClass("templateDiv");
		templateClone.find('.connectedPersonName').html(''+results.friendRequest[i].candidateName+'');
		templateClone.find('.imgClass').html('<img height="50" width="55" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/>');
		templateClone.find('.constituencyName').html(''+results.friendRequest[i].message+'');
		templateClone.find('.districtName').html('<a class="btn-mini btn" onclick="acceptRequest('+results.friendRequest[i].id+')" class="acceptButton">Accept</a>');
		templateClone.find('.stateName').html('<a class="btn-mini btn" onclick="rejectRequest('+results.friendRequest[i].id+')" class="rejectButton">Decline</a>');
		templateClone.find('.sendMsg').html('<a class="btn-mini btn" onclick="blockRequest('+results.friendRequest[i].id+')" class="rejectButton">Block this person</a>');
		templateClone.appendTo(".placeholderCenterDiv");
	}
}


function showRequestedMessagesForAUser(results)
{
	
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.resultStatus.resultCode !="0")
	{
		$(".templateDivMsg").html("Data could not be retrived due to some technical difficulties.").appendTo(".placeholderCenterDiv");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$(".templateDivMsg").html("No messages has been sent to you.").appendTo(".placeholderCenterDiv");
		return;
	}
		
	$(".placeholderCenterDiv").children().remove();
	
		$("#headerDiv").html('Unread: <span style="color:blue;">'+ results.unreadMsgCount +' </span> Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span>');
		for(var i in results.candidateVO)
		{
		var template = $(".templateDivMsg");
		var templateClone = template.clone();
		templateClone.removeClass("templateDivMsg");
		templateClone.find(".messageFrom").html(''+results.candidateVO[i].candidateName+'');
		if(results.candidateVO[i].status == "UNREAD")
			templateClone.find(".message").html(''+results.candidateVO[i].message+'').css("font-weight","bold");
		else
			templateClone.find(".message").html(''+results.candidateVO[i].message+'');
			templateClone.find('.imgClass').html('<img height="45" width="45" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/>');
			templateClone.find(".dateAndTimeReceived").html(''+results.candidateVO[i].postedDate+'');
			templateClone.find(".reply").html('<a href="javascript:{}" onclick="showMailPopup('+results.candidateVO[i].id+',\''+results.candidateVO[i].candidateName+'\',\'Message\')" >REPLY</a>');
			templateClone.find(".msgDelete").html('<a href="javascript:{}">DELETE</a>');
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
		var label = $("<label class='messageLabel'>Message</label>");
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
	callAjax1(jsObj,url);
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
	callAjax1(jsObj,url);
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
	callAjax1(jsObj,url);
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
	callAjax1(jsObj,url);
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
	clearAllSubscriptionDivs();
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
		templateClone.find(".constituencyName").html(''+results[i].title+'');
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
	clearAllSubscriptionDivs();
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
	
	filterDiv.append("<div style='padding:4px;width:100%;'><label style='width:40%;float:left;'>Search By Name :</label><input id='connectStatusTextBox' type='text' value='' onkeyup='getAllConnectedUsersByFilterView(\""+jsObj.locationType+"\") '/></div>");
	if(jsObj.locationType == "DISTRICT")
	{
		
		str +='<div style="padding:4px;width:100%;"><label    style="width:40%;float:left;">By Constituency: </label>';
		str +='<select id="connectConstituencySelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\')">';
		str +='<option value="0">All</option>';
		for(var i in constituencies)
			str += '<option value="'+constituencies[i].id+'">'+constituencies[i].name+'</option>';
		str += '</select></div>';
	}

	str +='<div style="padding:4px;width:100%;"><label style="width:40%;float:left;">By Status:</label>';
	str +='<select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\')">';
	str +='<option value="0">All</option>';
	for(var i in connectStatus)
	str +='<option id='+connectStatus[i].id+'>'+connectStatus[i].name+'</option>';
	str +='</select></div>';
	filterDiv.append(str);
	$("#headerDiv").append(filterDiv);
	 for(var i in results.candidateVO)
	{
		 //alert(results.candidateVO[i].district);
		var imageStr = "pictures/profiles/"+results.candidateVO[i].image;
		var image = results.candidateVO[i].image;
		var template = $(".templateDiv");
		var templateClone = template.clone();
		templateClone.removeClass("templateDiv");
		if(image == null)
			templateClone.find(".imgClass").html('<img width="50" height="45" src="/PartyAnalyst/images/icons/indexPage/human.jpg">');
		else
			templateClone.find(".imgClass").html('<img height="45" width="50" src="'+imageStr+'" />');
			templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].candidateName+'');
			templateClone.find(".constituencyName").html(''+results.candidateVO[i].constituencyName.toLowerCase()+'');
			templateClone.find('.stateName').html(''+results.candidateVO[i].state+'');
			templateClone.find('.districtName').html(''+results.candidateVO[i].district+'');
			if(results.candidateVO[i].status != null && results.candidateVO[i].status == "NOT CONNECTED")
				templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\')">Connect</a>');
			else if(results.candidateVO[i].status != null && results.candidateVO[i].status == "PENDING")
				templateClone.find('.connectCls').html('Pending');
			templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\',\'Message\')">Send a Message</a>');
			
			templateClone.appendTo(".placeholderCenterDiv");
			
	}

	var pagination = $('<div class="custom_paginator_class" style="clear: both; margin-top: 0px; padding-top: 10px;"></div>');
	pagination.appendTo('.placeholderCenterDiv');
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
	
	var users = results.candidateVO;
	
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.resultStatus.exceptionEncountered != null || results.resultStatus.resultCode !="0")
	{
		$("#headerDiv").html('');
		$(".templateDiv").html('Data Not Found Due To Some Exeption').appendTo(".placeholderCenterDiv");
		return;
	}
	else if(users.length == 0)
	{
		$(".templateDiv").html('No people connected in this district with this view..').appendTo(".placeholderCenterDiv");
		return;
	}
	else
	{
		for(var i in results.candidateVO)
		{
			var template = $(".templateDiv");
			var imageStr = "pictures/profiles/"+results.candidateVO[i].image;
			var image = results.candidateVO[i].image;
			var templateClone = template.clone();
			templateClone.removeClass("templateDiv");
			if(image == null)
				templateClone.find(".imgClass").html('<img width="50" height="45" src="/PartyAnalyst/images/icons/indexPage/human.jpg">');
			else
				templateClone.find(".imgClass").html('<img height="45" width="50" src="'+imageStr+'" />');
				templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].candidateName+'');
				templateClone.find(".constituencyName").html(''+results.candidateVO[i].constituencyName.toLowerCase()+'');
				templateClone.find(".districtName").html(''+results.candidateVO[i].district+'');
				templateClone.find('.stateName').html(''+results.candidateVO[i].state+'');
				if(results.candidateVO[i].status != null && results.candidateVO[i].status != "CONNECTED")
				  templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\')">Connect</a>');
				templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" style="color:#669900;">Send a Message</a>');
				templateClone.appendTo(".placeholderCenterDiv");
			
		}
	}
}


//total problems

function showAllPostedProblems_paginator(jsObj,results)
{
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	
	var problemsData = results.problemsInfo;

	if(problemsData == null)
	{
		$('.problemTemplateDiv').html('Problems Does Not Exists').appendTo('.placeholderCenterDiv');
		return;
	}
	
	for(var i in problemsData)
	{
		var template = $('.problemTemplateDiv');
		var templateClone = template.clone();
		templateClone.removeClass('problemTemplateDiv');
		templateClone.find('.problemReportedDate').html(''+problemsData[i].identifiedDate+'');
		templateClone.find('.problemTitle').html('<a href="completeProblemDetailsAction.action?problemId='+problemsData[i].problemID+'">'+problemsData[i].definition+'</a>');
		templateClone.find('.problemDescription').html(''+problemsData[i].description+'');
		templateClone.find('.problemFromDate').html('<span style="color:seagreen;">Existing From: </span>'+problemsData[i].existingFrom+'');
		templateClone.find('.likeCls').html('Like');
		templateClone.find('.commentCls').html('Comment');
		templateClone.find('.shareCls').html('Share');
		templateClone.appendTo('.placeholderCenterDiv');
	}

	var pagination = $('<div class="custom_paginator_class" style="clear: both; margin-top: 0px; padding-top: 10px;"></div>');
	pagination.appendTo('.placeholderCenterDiv');
}


/*function showAllPostedProblems_paginator(jsObj,results)
{
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	
	//var problemsData = results.problemsInfo;

	if(results == null)
	{
		$('.problemTemplateDiv').html('Problems Does Not Exists').appendTo('.placeholderCenterDiv');
		return;
	}
	
	for(var i in results)
	{
		
		var problemsData = results[i].problemsInfo;
		
		var existingfromDate = $("<div class='existingFromDiv'></div>"); 
		existingfromDate.append('<span>'+results[i].postedDate+'</span>');
		existingfromDate.appendTo('.placeholderCenterDiv');

		for(var j in problemsData)
		{
		
		var template = $('.problemTemplateDiv');
		var templateClone = template.clone();
		templateClone.removeClass('problemTemplateDiv');
		alert(problemsData[j].definition);
		if(problemsData[j].definition != null)
		templateClone.find('.problemTitle').html(''+problemsData[j].definition+'');
		if(problemsData[j].description != null)
		templateClone.find('.problemDescription').html(''+problemsData[j].description+'');
		if(problemsData[j].existingFrom != null)
		templateClone.find('.problemFromDate').html('<span>Existing From: </span>'+problemsData[j].existingFrom+'');
		templateClone.find('.likeCls').html('Like');
		templateClone.find('.commentCls').html('Comment');
		templateClone.find('.shareCls').html('Share');
		
		templateClone.appendTo('.placeholderCenterDiv');
		}
		
		
	}
	

	//var pagination = $('<div class="custom_paginator_class" style="clear: both; margin-top: 0px; padding-top: 10px;"></div>');
	//pagination.appendTo('.placeholderCenterDiv');
}

*/

function connectToSelectedPerson(id,name)
{
	$("#allConnectedUsersDisplay_main").children().remove();			
		var userId = id;
		var userName = name;
		var userLoginId = loginUserId;
		var locationId = districtId;
		var locationType = 'DISTRICT';
		var locationName = districtName;
		
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
		var message = $("<label class='messageLabel'>Message</label>");
		var textArea = $("<textarea id='connectUserMsg'></textarea>");
		var image = $('<img height="100" width="95" src="/PartyAnalyst/images/icons/indexPage/human.jpg">');
		var connectBtn = $('<input type="button" value="Connect" id="connectDistrictPeopleLink"/>');
		var connectedPersonId = $('<input type="hidden" value='+userId+' id="connectedPersonId"/>');
		var errorDiv = $("<div id='errorMsgDiv'></div>")
		div.append(errorDiv);
		div.append(Name);
		div.append(message);
		div.append(textArea);
		div.append(image);
		div.append(connectBtn);
		div.append(connectedPersonId);
		$('#allConnectedUsersDisplay_main').append(div);
		
	

}

//people  you may know "connect" response fun

function showAllConnectedUsersStatus(jsObj,results)
{

if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{		
		var msga = $('<blink><font color="green" style="font-weight:bold;"> Request sent to selected users successfully.</font></blink>');
		if(jsObj.locationType=="DISTRICT"){
			$("#districtPeopleLink").trigger("click");
			//showAllConnectedUsersInPanelOfDistrict(jsObj,results);		
			//buildConnectUsersContentOfDistrict(results.candidateVO,connectDivId,connetLocationType,connetLocationId,connetLocationName,connectUserLoginStatus,connectUserLoginId, results.totalResultsCount);
		}
		else{
			//showAllConnectedUsersInPanel(jsObj,results);		
			//buildConnectUsersContent(results.candidateVO,connectDivId,connetLocationType,connetLocationId,connetLocationName,connectUserLoginStatus,connectUserLoginId);
		}
	}
	//else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
		//elmt.innerHTML = '<font color="red" style="font-weight:bold;"><blink> Request cannot be sent to the selected users due to some technical difficulty.</blink></font>';
}


//politial Reasons 


function showAllPostedReasons_paginator(jsObj,results)
{
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();

	var data = results.candidateComments;

	if(data == null)
	{
		$(".templateDivMsg").html('<div>No comments.</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	for(var i in data)
	{
		var status;
		var template = $('.politicalReasonsTemplate');
		var templateClone = template.clone();
		templateClone.removeClass('politicalReasonsTemplate');
		
		if(data[i].rank == 1)
			status = "winning";
		else
			status = "losing";

		templateClone.find('.headingCls').html('Political Reason for'+data[i].candidate+' '+status+' '+data[i].constituencyName+' '+data[i].electionType+' constituency');
		templateClone.find('.politicalReaCls').html('Political Reason: '+data[i].commentCategory+'');
		templateClone.find('.politicalDescCls').html('Description: '+data[i].commentDesc+'');
		templateClone.find('.polReaPostedDate').html('Posted On: '+data[i].commentedOn+'');
		templateClone.find('.polReaPostedPerName').html('Posted By: '+data[i].commentedBy+'');
		templateClone.appendTo('.placeholderCenterDiv');
	}
	var pagination = $('<div class="custom_paginator_class" style="clear: both; margin-top: 0px; padding-top: 10px;"></div>');
	pagination.appendTo('.placeholderCenterDiv');

}

//political reasons Count 


function getAllPostedReasonsForUser()
{

	var jsObj=
	{
			task:"getAllPostedReasonsStatusUser"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPostedReasonsStatusUserAction.action?"+rparam;						
	callAjax1(jsObj,url);
}


/* -- subscription functions Start -- */

function showAllUserSubScribedSpecialPagesPages(jsObj,results)
{
	
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userSpecialPageSubscriptionsDiv').children().remove();

	var specialPages = results.userSpecialPageSubscriptions;
	if(specialPages == null || specialPages.length == 0)
	{
		$('#userSpecialPageSubscriptionsDiv').html("Special page subscriptions are not available.");
		return;
	}
	
	var div = $('<div class="subscriptionHeaderDiv"></div>');
	var label = $('<span class="subscriptionType">Special Pages</span>');
	var btns = $('<span>Sunscribe All</span><span class="unSubscriptionBtn">Un Subscribe All</span>');
	div.append(label);
	div.append(btns);
	$('#userSpecialPageSubscriptionsDiv').append(div);
	for(var i in specialPages)
	{
		if(specialPages[i].subscribed)
		{
		  var template = $(".specialPagSubscrTemplDiv");
		  var templateClone = template.clone();
		  templateClone.removeClass('specialPagSubscrTemplDiv');
		  templateClone.find('.titleCls').html(''+specialPages[i].specialPageVOList[i].title+'');
		  templateClone.find('.imgClass').html('<img height="100" width="95" src="'+specialPages[i].specialPageVOList[i].eventImagePath+'"/>');
		  templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">Un Subscribed</a>');
		  templateClone.find('.hiddenVar').html('<input type="hidden" value="'+specialPages[i].specialPageVOList[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="specialPage"/>');
		  templateClone.appendTo('#userSpecialPageSubscriptionsDiv');
		}
		else
		{
		  var template = $(".specialPagSubscrTemplDiv");
		  var templateClone = template.clone();
		  templateClone.removeClass('specialPagSubscrTemplDiv');
		  templateClone.find('.titleCls').html(''+specialPages[i].specialPageVOList[i].title+'');
		  templateClone.find('.imgClass').html('<img height="100" width="95" src="'+specialPages[i].specialPageVOList[i].eventImagePath+'"/>');
		  templateClone.find('.btnClass').html('<a href="javascript:{}" class="subscribedLink">Subscribed</a>');
		  templateClone.find('.hiddenVar').html('<input type="hidden" value="'+specialPages[i].specialPageVOList[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="specialPage"/>');
		  templateClone.appendTo('#userSpecialPageUnSubscriptionsDiv');
		
		}
	}
	
}


function buildSubscribeButtons(id,userSubscribedPages)
{
	
	if(userSubscribedPages != null)
	  if(id == userSubscribedPages.id)
		return true
		else 
	return true;

}


function showAllUserCandidateSubscriptions(jsObj,results)
{
	
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userCandidateSubscriptionsDiv').children().remove();
	var politicians = results.userCandidateSubscriptions;

	if(politicians == null || politicians.length == 0)
	{
		$('#userCandidateSubscriptionsDiv').html("Politician subscriptions are not available.");
		return;
	}
	
	var div = $('<div class="subscriptionHeaderDiv"></div>');
	var label = $('<span class="subscriptionType">Politicians</span>');
	var btns = $('<span>Sunscribe All</span><span class="unSubscriptionBtn">Un Subscribe All</span>');
	div.append(label);
	div.append(btns);
	$('#userCandidateSubscriptionsDiv').append(div);
	
	for(var i in politicians)
	{
		
		var template = $(".specialPagSubscrTemplDiv");
		var templateClone = template.clone();
		templateClone.removeClass('specialPagSubscrTemplDiv');
		templateClone.find('.titleCls').html(''+politicians[i].name+'');
		templateClone.find('.imgClass').html('<img height="100" width="95" src="images/candidates/'+politicians[i].name+'.jpg"/>');
		/*if(buildSubscribeButtons(results.profileCandidateSubscriptions[i].id,results.userCandidateSubscriptions))
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">Un Subscribed</a>');
		else*/
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="subscribedLink">Un Subscribed</a>');
		
		templateClone.find('.hiddenVar').html('<input type="hidden" value="'+politicians[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="candidatePage"/>');
		templateClone.appendTo('#userCandidateSubscriptionsDiv');
	}
}



function showAllUserConstituencySubscriptions(jsObj,results)
{
	
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userConstituencySubscriptionsDiv').children().remove();
	var constituencies = results.userConstituencySubscriptions;

	if(constituencies == null || constituencies.length == 0)
	{
		$('#userConstituencySubscriptionsDiv').html("Constituency subscriptions are not available.");
		return;
	}
	
	var div = $('<div class="subscriptionHeaderDiv"></div>');
	var label = $('<span class="subscriptionType">Constituencies</span>');
	var btns = $('<span>Sunscribe All</span><span class="unSubscriptionBtn">Un Subscribe All</span>');
	div.append(label);
	div.append(btns);
	$('#userConstituencySubscriptionsDiv').append(div);
	for(var i in constituencies)
	{
		var template = $(".specialPagSubscrTemplDiv");
		var templateClone = template.clone();
		templateClone.removeClass('specialPagSubscrTemplDiv');
		templateClone.find('.titleCls').html(''+constituencies[i].name+'');
		//templateClone.find('.imgClass').html('<img height="100" width="95" src="images/party_flags/'+results.profilePartySubscriptions[i].name+'.png"/>');
		/* if(buildSubscribeButtons(results.profileConstituencySubscriptions[i].id,results.userConstituencySubscriptions))
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">Un Subscribed</a>');
		else*/
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="subscribedLink">Un Subscribed</a>');
		templateClone.find('.hiddenVar').html('<input type="hidden" value="'+constituencies[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="constituencyPage"/>');
		templateClone.appendTo('#userConstituencySubscriptionsDiv');
	}
}

function showAllUserPartySubscriptions(jsObj,results)
{
	
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userPartySubscriptionsDiv').children().remove();
	var partySubscriptions = results.userPartySubscriptions;

	if(partySubscriptions == null || partySubscriptions.length == 0)
	{
		$('#userPartySubscriptionsDiv').html("Party subscriptions are not available.");
		return;
	}
	
	var div = $('<div class="subscriptionHeaderDiv"></div>');
	var label = $('<span class="subscriptionType">Parties</span>');
	var btns = $('<span>Sunscribe All</span><span class="unSubscriptionBtn">Un Subscribe All</span>');
	div.append(label);
	div.append(btns);
	$('#userPartySubscriptionsDiv').append(div);
	for(var i in partySubscriptions)
	{
		var template = $(".specialPagSubscrTemplDiv");
		var templateClone = template.clone();
		templateClone.removeClass('specialPagSubscrTemplDiv');
		templateClone.find('.titleCls').html(''+partySubscriptions[i].title+'');
		templateClone.find('.imgClass').html('<img height="100" width="95" src="images/party_flags/'+results.userPartySubscriptions[i].name+'.png"/>');
		/* if(buildSubscribeButtons(results.profilePartySubscriptions[i].id,results.userPartySubscriptions))
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">Un Subscribed</a>');
		else*/
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="subscribedLink">Un Subscribed</a>');
		templateClone.find('.hiddenVar').html('<input type="hidden" value="'+partySubscriptions[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="partyPage"/>');
		templateClone.appendTo('#userPartySubscriptionsDiv');
	}
}

function clearAllSubscriptionDivs()
{
	$("#userSpecialPageSubscriptionsDiv").html('');
	$("#userSpecialPageUnSubscriptionsDiv").html('');
	$("#userCandidateSubscriptionsDiv").html('');
	$("#userPartySubscriptionsDiv").html('');
	$("#userConstituencySubscriptionsDiv").html('');

	$("#userSpecialPageSubscriptionsDiv").children().remove();
	$("#userSpecialPageUnSubscriptionsDiv").children().remove();
	$("#userCandidateSubscriptionsDiv").children().remove();
	$("#userPartySubscriptionsDiv").children().remove();
	$("#userConstituencySubscriptionsDiv").children().remove();

}

/* -- subscription functions End -- */


function showChangePwdStatus(results)
{
	$("#password_window_errorMsg").html('');
	var result = $("#password_window_errorMsg");
	if(results==121)
	{
	  result.html('<div style="color:green">Password changed successfully, Window Closing...</div>');
      setTimeout("closewdw()",3000);
	  return;
	}	
    else if(results==0)
	{
	 result.html('<div style="color:red"> Invalid Current Password</div>');	
	 return;
	}
}

function closewdw()
{
	$("#connectPeoplePopup").dialog("destroy");
}

<!--OPINION POLL SCRIPTS START-->

function buildPolls()
{	
	var jsObj=
	{
			task:"getAllPolls"					
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPolls.action?"+rparam;						
	homePageAjaxCall(rparam,jsObj,url);
}

function homePageAjaxCall(param,jsObj,url){
	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);								

								if(jsObj.task == "getRecentElectionsInState")
								{									
									showResults(myResults);
								} 
								else if(jsObj.task == "getAllPolls")
								{			
									
								    
									if(myResults.description==null){
										
										//showVotesObtainedForOptions(myResults.questionsOptionsVO);
										if(myResults.questionsOptionsVO != null)
										displayCurrentPollResult(myResults.questionsOptionsVO.questionId);

									}else{										
										//buildNewPoll(myResults);
										buildNewPoll1(myResults);
									}
								}
								else if(jsObj.task == "saveSelectedPoll")
								{	
									
									showVotesObtainedForOptions(myResults);
								} 
								else if(jsObj.task == "getLocalBodiesForState")
								{
									buildLocalBodiesForAState(jsObj,myResults);
								}
								else if(jsObj.task == "getLocalBodiesSelectElmtForState")
								{
									buildLocalBodiesSelectElmt(jsObj,myResults)
								}
								
						}
						catch (e)
							{   
							  	//alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            	  			// alert( "Failed to load result" + o.status + " " + o.statusText);
			                         }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
	function displayCurrentPollResult(questionId){

	

	callAjaxToGetQuestionsDetails("",questionId);
}

function callAjaxToGetQuestionsDetails(voteStatus,questionId){

		

	var jsObj =
			{				
			   questionId:questionId,
				task:"getPollDetails"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getQuestionAndPercentageOfVotesForChoices.action?"+rparam;						
		callAjaxToGetPollDetails(voteStatus,jsObj,url); 

}

function callAjaxToGetPollDetails(voteStatus,jsObj,url){


	var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

			
			if(jsObj.task == "getPollDetails"){					
				
				buildResultForPoll(voteStatus,myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResultForPoll(voteStatus,result){

	var elmt = document.getElementById("pollsWidgetBody");

	var str='';
  
	str+='<div class="opinionpoll">';
	str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:1px 0px 2px #888;margin-bottom:5px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion polls</span></h3>';

	str+='<div class="breadcrumb"><p style="padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;"><b>'+result.question+'</b></p>';

	str+='<p class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	str+='<ul style="padding:0px ;margin-bottom:5px;">';
	for(var i=0;i<result.options.length;i++){ 
		
	 str+='<li>';
	   str+='<h5>'+result.options[i].option+'</h5>';

		str+='<div>';
		str+='<div class="span2 pull-left" style="margin-left:0px;">';

			str+='<div class="progress" style="margin:0px;">';
			  str+='<div id="option1" class="bar" style="width:'+result.options[i].percentage+'%"></div>';
			str+='</div>';							
			str+='</div>	';

			str+='<span class="label  label-info" style="margin-left:6px;">'+result.options[i].percentage+'% </span>';
	  str+= '</div>';
	   str+='</li>';
	}  
  str+='</ul>';	
	 if(voteStatus == "vote")

        str+='<a href="javaScript:{buildNewPoll1(result1)}");" class="btn btn-primary" title="Click Here To Vote" style="margin:10px 0px 0px 88px;">Vote Now</a>';
  	    str+='</div>';
		
	    str+='<div class="pager">';
		str+='<a class="btn" style="margin-left:-11px;" href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn" style="float:left;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		str+='<a class="btn" href="getAllPollsAction.action" class="btn" style="float:right;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		

		str+='</div>';

	  elmt.innerHTML=str;


}

function getCompletePollResult(questionId){  
	var browser1 = window.open("completeResultForAPollAction.action?questionId="+questionId,"completeResultForAPoll","scrollbars=yes,height=350,width=450,left=200,top=200");
	browser1.focus();
}

function getAllPollsResult(){ 
	var browser1 = window.open("getAllPollsAction.action?","allPollResults","scrollbars=yes,height=600,width=650,left=200,top=200");
	browser1.focus();
}

function buildNewPoll(result){
	
	if(result.quesitons!=null){
		var elmt = document.getElementById("pollsWidgetBody");
		if(!elmt)
			return;
		
		var questionId = '';
		var str = '';
		for(var i=0; i<1;i++)
		{
			questionId = result.quesitons[i].questionId;
			str += '<div id="pollQuestionDiv">';
			str += result.quesitons[i].question;
			str += '</div>';
			str += '<div id="pollOptionsDiv">';
			str += '<table>';
			for(var j=0 ; j<result.quesitons[i].options.length; j++){
				if(j==0){
					str += '<tr><td><input type="radio" name="pollradio" checked=checked value="'+result.quesitons[i].options[j].optionId+'">&nbsp;&nbsp;';
				}else{
					str += '<tr><td><input type="radio" name="pollradio" value="'+result.quesitons[i].options[j].optionId+'">&nbsp&nbsp;';
				}				
				str += result.quesitons[i].options[j].option;
				str += '</td></tr>';			
			}
			str += '</table>';
			str += '</div>';
		}
		
		
		str += '<div id="pollSubmitDiv">';
		str += '<div onclick="savePollResult(\''+questionId+'\')" class="viewReportButtonSpan" style="left:">';
		str += '	<span class="viewReportButtonLabel"  style="left:20px;top:5px;">Vote</span>';		
		str += '</div>';
		str += '</div>';
		
		
		str += '<table><tr><td>';
		str += '<div id="viewPollResDiv">';
		str += '<table><tr>';
		str += '<td><a href="completeResultForAPollAction.action?questionId='+questionId+'" style="text-decoration:underline;cursor:pointer;padding-right:43px;color:#3d3d3d;"> View Result</a>';
		str += '</td>';
		str += '<td><a href="getAllPollsAction.action?.action" style="text-align:right;text-decoration:underline;cursor:pointer;color:#3d3d3d;"> View Previous Polls</a>';
		str += '</td>';	
		str += '</tr></table>';
		str += '</div>';
		str += '</tr></table>';

		elmt.innerHTML = str;

	}
}

function savePollResult(questionId){

	var elmts = document.getElementsByName("pollradio");
	var checkedElmtId = '';
	
	for(var i=0; i<elmts.length;i++)
	{
		if(elmts[i].checked == true)
			checkedElmtId = elmts[i].value;
	}
	var jsObj=
	{
			questionId:questionId,
			selectedPollId:checkedElmtId,
			task:"saveSelectedPoll"					
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSelectedPoll.action?"+rparam;						
	homePageAjaxCall(rparam,jsObj,url);	
}

function buildNewPoll1(result){

	result1=result;  

	if(result.quesitons!=null){

		var elmt = document.getElementById("pollsWidgetBody");
		if(!elmt)
			return;

	}


    var str='';


     str+='<div class="opinionpoll">';

		<!--str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:3px 0px 2px #888;margin-bottom:5px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion Poll</span></h3>';-->

		for(var i=0; i<1;i++){

		questionId = result.quesitons[i].questionId;

		str+='<div class="breadcrumb"><p class="question"><b>'+result.quesitons[i].question+'</b></p>';	
	
		str+='<div id="qstnDiv1" style="margin:0px-20px;margin-top:3px">';	

		str+='<div class="control-group form-horizontal "><p class="answer">';
		for(var j=0 ; j<result.quesitons[i].options.length; j++){ 

			if(j==0){      

				str +='<label><input type="radio" class="radio" name="pollradio" value="'+result.quesitons[i].options[j].optionId+'" checked="true">';
				str +=result.quesitons[i].options[j].option+"</label>"; 

				
			}else{
				str +='<label><input type="radio" class="radio"  name="pollradio" value="'+result.quesitons[i].options[j].optionId+'">';
				str+=result.quesitons[i].options[j].option+"</label>";
			}
			
		}
		}
		str+='</p><a href="javaScript:saveCurrentPollResult(\''+questionId+'\');" style="margin:0px auto;display:block;width:75px;" class="btn btn-primary votebtn" title="Click Here To Vote">Vote</a>';
		
		str+='<p class="resultdisplay"><a  style="float:left;" class="previouslink" href="javaScript:{callAjaxToGetQuestionsDetails(\'vote\',\''+questionId+'\')}" title="Click Here To See This Poll Result">View Results</a>';

		    
		str+=' <a class="nextlink" style="float:right;" href="completeResultForAPollAction.action?questionId='+questionId+'&comments=getComments" title="Click Here To See Comments On This Poll" >View Comments</a></p>';
		str+=' </div></div>';

		
		str+='</div>';
		str+='<div class="widget-block" style="padding:2px;height:30px;border:0px;">';
   
        str+='<a href="completeResultForAPollAction.action?questionId='+questionId+'&comments=getComments"  class="btn btn-mini" title="Post Your Comment On This Poll"  style="float:left;margin-left:3px;">Post Your Comment</a>';
 
  
    str+='<a   style="float:right;margin-right:3px;"" class="btn btn-mini" href="getAllPollsAction.action"  title="Click Here To View Rececnt Poll Details" >View Recent Polls </a>';
   
    str+='</div>';
	elmt.innerHTML = str;	

	}
	
	function saveCurrentPollResult(questionId){

	var elmts = document.getElementsByName("pollradio");
	var checkedElmtId = '';
	
	for(var i=0; i<elmts.length;i++)
	{
		if(elmts[i].checked == true)
			checkedElmtId = elmts[i].value;
	}
	var jsObj=
	{
			questionId:questionId,
			selectedPollId:checkedElmtId,
			task:"saveSelectedPoll"					
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSelectedPoll.action?"+rparam;						
	callAjaxToSaveSelectedPollDetails(rparam,jsObj,url,questionId);	
}


function callAjaxToSaveSelectedPollDetails(param,jsObj,url,questionId){

	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								 
								 if(myResults.availability == true){

									 var cssObj = {    
										'font-weight' : 'bold',
										'color' : 'green'
								      }
									   $('#alreadyVotedDivId').text("You are already voted.").css(cssObj).show().delay(2000).fadeOut(400);
										
								 }
							     displayCurrentPollResult(questionId);						
													
								
						}
						catch (e)
							{   
							  	//alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function displayCurrentPollResult(questionId){

	

	callAjaxToGetQuestionsDetails("",questionId);
}

function callAjaxToGetQuestionsDetails(voteStatus,questionId){

		

	var jsObj =
			{				
			   questionId:questionId,
				task:"getPollDetails"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getQuestionAndPercentageOfVotesForChoices.action?"+rparam;						
		callAjaxToGetPollDetails(voteStatus,jsObj,url); 

}

function callAjaxToGetPollDetails(voteStatus,jsObj,url){


	var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

			
			if(jsObj.task == "getPollDetails"){					
				
				buildResultForPoll(voteStatus,myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResultForPoll(voteStatus,result){


	var elmt = document.getElementById("pollsWidgetBody");

	var str='';
  
	str+='<div class="opinionpoll">';
	<!--str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:1px 0px 2px #888;margin-bottom:5px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion polls</span></h3>';-->

	str+='<div class="breadcrumb"><p style="padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;"><b>'+result.question+'</b></p>';

	str+='<p class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	str+='<ul style="padding:0px ;margin-bottom:5px;">';
	for(var i=0;i<result.options.length;i++){ 
		
	 str+='<li style="width: 100%; clear: both;">';
	   str+='<h5>'+result.options[i].option+'</h5>';

		str+='<div>';
		str+='<div class="span8 pull-left" style="margin-left:0px;">';

			str+='<div class="progress" style="margin:0px;">';
			  str+='<div id="option1" class="bar" style="width:'+result.options[i].percentage+'%" ></div>';
			str+='</div>';							
			str+='</div>	';

			str+='<span class="label  label-info" style="margin-left:6px;">'+result.options[i].percentage+'% </span>';
	  str+= '</div>';
	   str+='</li>';
	}  
  str+='</ul>';	
	 if(voteStatus == "vote")

        str+='<a href="javaScript:{buildNewPoll1(result1)}");" class="btn btn-primary" title="Click Here To Vote" style="margin:10px 0px 0px 65px;">Vote Now</a>';
  	    str+='</div>';
		
	    str+='<div class="widget-block" style="padding:2px;height:30px;border:0px;">';
		str+='<a class="btn btn-mini" style="float:left;margin-left:3px;" href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn" style="float:left;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		str+='<a class="btn btn-mini" href="getAllPollsAction.action" class="btn" style="float:right;margin-right:3px;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		

		str+='</div>';

	  elmt.innerHTML=str;


}
function displayPollResult(questionId){
	callAjaxToGetQuestionsDetails("",questionId);
}

function afterRefreshOpinionPOll()
{
var elmt = document.getElementById("pollsWidgetBody");
	var str = '';
	str += '<table><tr><td>';
	str += '<div id="pollQuestionDiv" > Q) '  +'${opinionPollVO.questionsOptionsVO.question}';
	str += '</div>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	//str += '<img src="charts/'+myResults.imagePath+'"></img>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	str += '<div id="viewPollResDiv">';
	str += '<table style="margin-left: 26px;"><tr>';
	str += '<td><div style="width:157px;"><a href="completeResultForAPollAction.action?questionId=${opinionPollVO.questionsOptionsVO.questionId}" style="cursor: pointer; color:#0C67AC; font-weight: bold; text-decoration: none; background:#e3e3e3; padding: 4px; border-radius: 5px; margin: 2px;"> View Current Poll Result</a></div>';
	str += '</td>';
	str += '<td><div style="width:94px;"><a href="getAllPollsAction.action?.action" style="text-align: right; cursor: pointer; color:#0C67AC; font-weight: bold; text-decoration: none; background:#e3e3e3; padding: 4px; border-radius: 4px;"> View All Polls</a></div>';
	str += '</td>';	
	str += '</tr></table>';
	str += '</div>';
	str += '</tr></table>';
	

	str+='<div id="pollsChart" style=" height: auto;width: 324px; overflow: hidden;"></div>';
	elmt.innerHTML = str;


var arrData = pollStatus;

	var data = new google.visualization.DataTable();
	data.addColumn('string','option');
	data.addColumn('number','votesObtained');
		
	data.addRows(arrData.length);

		for(var j=0; j<arrData.length; j++)
		{
			
			data.setValue(j,0,arrData[j].option);
			data.setValue(j,1,arrData[j].votesObtained);
			
		}
			var chart = new google.visualization.LineChart(document.getElementById('pollsChart'));
	
	chart.draw(data,{width: 300, height: 280,legend:'right', 
	legendTextStyle:{fontSize:12},title:'${opinionPollVO.questionsOptionsVO.title}',titleTextStyle:{fontName:'verdana',fontSize:9}});
	
}

function callAjax(voteStatus,jsObj,url)
{
	
		var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

		    
			if(jsObj.task == "getPollDetails"){				
				buildResult(voteStatus,myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResult(voteStatus,result){

	var elmt = document.getElementById("pollsWidgetBody");

	var str='';
  
	str+='<div class="opinionpoll">';

	/*str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:3px 0px 2px #888;margin-bottom:5px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion Poll</span></h3>';*/

	str+='<div class="breadcrumb"><p class="question"><b>'+result.question+'</b></p>';	


	//str+='<p class="" style="margin-bottom:3px;"><b>'+result.question+'</b></p>'; 

	str+='<div id="qstnDiv1" style="margin-top:3px;">';	


	str+='<p style="margin-bottom: 0px; padding-bottom: 0px;" class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	
	str+='<ul style="margin:0px;width:100%;">';
	for(var i=0;i<result.options.length;i++){   
		
          str+='<li style="width:100%;clear:both;">';
		str+='<h6 style="margin:1px;">'+result.options[i].option+'</h6>';

		str+='<div>';
		str+='<div class="span8 pull-left">';

			str+='<div class="progress" style="margin:0px;">';
			  str+='<div id="option1" class="bar" style="width:'+result.options[i].percentage+'%"></div>';
			str+='</div>';							
			str+='</div>	';

			str+='<span class="label label-info m-left5">'+result.options[i].percentage+'% </span>';
	  str+= '</div>';
	   str+='</li>';
		
	}
	str+='</ul></div>';
	
	 if(voteStatus == "vote")

        str+='<a class="btn btn-primary" href="javaScript:{showVotesObtainedForOpinionPoll()}");" class="btn btn-primary" title="Click Here To Vote" style="margin:10px 0px 0px 104px;">Vote Now</a>';

		str+='</div>';
  
		//str+='</div>';

  
		
	    str+='<div class="widget-block" style="padding:2px;height:30px;border:0px;">';
		str+='<a href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn btn-mini" style="float:left;margin-left:3px;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		


		str+='<a href="getAllPollsAction.action" class="btn btn-mini" style="float:right;margin-right:3px;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		str+='</div>';
	
	

	elmt.innerHTML=str;

}
<!--OPINION POLL SCRIPTS END-->