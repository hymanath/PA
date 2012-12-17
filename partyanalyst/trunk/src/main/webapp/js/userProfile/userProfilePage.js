var connectStatus = [];
var constituencies = [];
var connetLocationId = '';
var uploadPicStatus = false;
var refreshTime=5;
var uploadResult;
var userType = '';
var startIndex;

$("document").ready(function(){
	

	$(".constituencyHeadingCls").live("click",function(){
		$('.constituencyDiv').slideToggle();	
		
	});
	$(".stateHeadingCls").live("click",function(){
	$('.stateDiv').slideToggle();	
		
	});

	
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
	
	$("#settings").click(function(){
		$(".placeholderCenterDiv").children().remove();
		$("#headerDiv").html('');
		$("#headerDiv").append("<ul id='accountStngs'><li class='btn'><a href='freeUserRegistration.action'><span class='icon-pencil'></span>  Edit Profile</a></li><li class='btn'><a href='javascript:{}' class='changePwdLink'><span class='icon-hand-right'></span> Change Password</a></li><li class='btn'><a href='javascript:{}' class='editPictureLink'><span class='icon-user'></span> Edit Picture</a></li><li class='btn'><a href='javascript:{getUserSettingsDetails();}' class='editSettingsLink'><span class='icon-thumbs-up'></span> Edit View Settings</a></li></ul>");
	});
	
	$(".messagesLink").click(function(){
	    
				
		if($("#headerDiv").find("#Inbox").length<1) {
		$('#headerDiv').prepend("<ul class='nav nav-tabs'><li class='active'><a id='Inbox' >Inbox</a></li><li><a id='SentBox'>Sent</a></li></ul>"); }
		
		$("#Inbox").trigger("click");
				
	});
	 
	$("#Inbox").live("click",function(){
		$(this).closest(".nav-tabs").find("li").removeClass("active");
		$(this).parent().addClass("active");
		
		
		var jsObj ={
			task:"getRequestMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllRequestMessagesForAUserAction.action?"+rparam;	
		
		callAjax1(jsObj,url);
	});

	$('.linkDiv').live("click",function(){
		alert('123');
		$(this).remove();
	});
	
	
	
	$("#SentBox").live("click",function(){
		$(this).closest(".nav-tabs").find("li").removeClass("active");
		$(this).parent().addClass("active");
		
		var jsObj ={
			task:"getSentBoxMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllSentMessagesForAUserAction.action?"+rparam;	
		
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
	


   $("#specialPageLink").click(function(){

		var jsObj ={
			task:"getFavouriteLinks"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getFavouriteLinksAction.action?"+rparam;	
		
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


	$('.assessPoliticianLink').live("click",function(){
		var type = $(this).closest('li').find('.politicalReasTypeVar').val();
		
		getAllPostedReasonsForUser();
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


	$('.assessPoliticianLink1').live("click",function(){
		var type = $(this).closest('li').find('.politicalReasTypeVar').val();
		var linkType = "assessPoliticianLink";
		startIndex = 0;
		getAllPostedReasonsForUser();
		var jsObj ={
				startIndex : startIndex,
				maxIndex   : 10,
				type       : type,
				linkType   : linkType,
				task:"getAllPostedReasons"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getPostedReasonsUserProfileAction.action?"+rparam+"&type="+type+"&sort=candidate&dir=asc";	
	callAjax1(jsObj,url);
	});

$('.PoliticalReaViewMoreLink').live("click",function(){
		var type = $(this).closest('div').find('.politicalReasonViewMoreTypeVar').val();
		var linkType = "PoliticalReaViewMoreLink";
		startIndex = 0;
		getAllPostedReasonsForUser();
		var jsObj ={
				startIndex : startIndex,
				maxIndex   : 10,
				type       : type,
				linkType   : linkType,
				task:"getAllPostedReasons"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getPostedReasonsUserProfileAction.action?"+rparam+"&type="+type+"&sort=candidate&dir=asc";	
	callAjax1(jsObj,url);
	});


	//problems 

	$(".problemsLink").live("click",function(){
		var type = $(this).closest('li').find(".problemTypeVariable").val();
		var linkType = "problemsLink";
		startIndex = 0;
		getAllPostedProblemsForUser();
		var jsObj ={
			startIndex : startIndex,
			maxIndex   : 10,
			type       : type,
			linkType   : linkType,
			task:"getAllPostedProblems"
		 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getPostedProblemsUserProfileAction.action?"+rparam+"&type="+type+"&sort=problemId&dir=desc";
	callAjax1(jsObj,url);
	});

	$(".problemsViewMoreLink").live("click",function(){
		var type = $(this).closest('div').find(".problemViewMoreTypeVar").val();
		var linkType = "problemsViewMoreLink";		
		startIndex = startIndex+10;
		$('.ajaxImg').css("display","block");
		getAllPostedProblemsForUser();
		var jsObj ={
			startIndex : startIndex,
			maxIndex   : 10,
			type       : type,
			linkType   : linkType,
			task:"getAllPostedProblems"
		 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getPostedProblemsUserProfileAction.action?"+rparam+"&type="+type+"&sort=problemId&dir=desc";
	callAjax1(jsObj,url);
	});

	$(".districtPeopleLink").click(function(){
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
		resultsCount:20,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllConnectedUsersInPanel(jsObj,results);
		}
	});
	custom_paginator.initialize();

	});



	$(".connectLink").click(function(){
		
		$("#allConnectedUsersDisplay_main").children().remove();			
		var userId = $(this).closest("div").find(".userId").val();
		var userName = $(this).closest("div").find(".userName").val();
		var constituencyName = $(this).closest("div").find(".constituencyName").val();
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
		div.append('<div style="margin-top: 10px;"><font color="red">*</font> <span>Current Password</span> <input type="password" id="currentPwdId" name="currentPassword"/></div>');
		div.append('<div><font color="red">*</font> <span>New Password</span> <input type="password" id="newPwdId" name="newPassword"/></div>');
		div.append('<div style="margin-bottom: 10px;"><font color="red">*</font> <span>Confirm Password</span> <input type="password" id="confirmPwdId" name="confirmPassword"/></div>');
		div.append('<input class="btn-info btn-small" id="changePWDButton" type="button" value="Change Password"></input>');
		div.append('<input class="btn-info btn-small" id="cancelButtonID" type="button" value="No"></input>');
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
	str += '<td width="40%" valign="top"><input class="btn-info btn-small" type="button" value="Upload" id="uploadPicButton"/>';
	str += '<input class="btn-info btn-small" type="button" value="Cancel" id="cancelPicButton"/>';	
	str += '</td>';
	str += '</tr>';
	str += '</table>';	
	str += '</div>';
	div.append(str);
	elmt.append(div);

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

//problem rating
//setTimeout("applyRaty()",1000);

//subscription

$(".unSubscribedLink").live("click",function(){
	var id = $(this).closest('div').find('.hiddenVarId').val();
	var type = $(this).closest('div').find('.subscripType').val();
	var jsObj=
	{		
            time : new Date().getTime(),	
			id: id,
			task: "unsubscriptionDetails",
			page:type
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
   callAjax1(jsObj,url);
});

$(".subscribedLink").live("click",function(){
	var id = $(this).closest('div').find('.hiddenVarId').val();
	var type = $(this).closest('div').find('.subscripType').val();
	var jsObj=
	{		
            time : new Date().getTime(),	
			id: id,
			task: "subscriptionDetails",
			page:type
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
   callAjax1(jsObj,url);
});

$(".unreadfont").live("click",function(){
 
	var msgid=$(this).find(".msgid").val();
	var jsObj=
	{		
			customMasgId:msgid,
			task:"updateReadMessageInDB"								
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateReadMessageInDBAction.action?"+rparam;					
	callAjax1(jsObj,url);

	$(this).removeClass("unreadfont");
  
	});
	
	$(".readMsg").live("click",function(){
		$(this).closest(".templateMessage").find(".msgbox").toggle('slow');
	});
	
	$(".postProblemLink").live("click",function(){

		var browser1 = window.open("addNewProblemAction.action?requestSrc=4&constituencyId="+constituencyId,"addNewProblemInConstituency","scrollbars=yes,height=600,width=600,left=200,top=200");
		browser1.focus();
	});


});//End ready



/* 
//problem rating

function applyRaty(){
$('.star').each(function(){
var rating=0;
if($(this).next().val()!="null"){rating= $(this).next().val();}
	$(this).raty({
				half       : true,
	     		precision  : true,
				readOnly	: true,
				score		:rating
				});
});
}*/

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
						
					else if(jsObj.task == "getSentBoxMessagesForUser")
						showSentBoxMessagesForAUser(results);
						
					else if(jsObj.task == "updateReadMessageInDB")
						updatedInfo(results);
					
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
					
					else if(jsObj.task == "getAllPostedProblemsByUser")
					{
						showPostedProblems(jsObj,results);
						
					}
					else if(jsObj.task == "getAllPostedReasonsStatusUser")
					{
						showPostedReasons(jsObj,results);
								
					}
					else if(jsObj.task == "unsubscriptionDetails")
					{
						$(".subscriptionsLink").trigger("click");
					}
					else if(jsObj.task == "subscriptionDetails")
					{
						$(".subscriptionsLink").trigger("click");
					}

					else if(jsObj.task == "getAllPostedProblems")
						showAllPostedProblems(jsObj,results);
					else if(jsObj.task == "getAllPostedReasons")
						showAllPostedReasonsForUserProfile(jsObj,results);
					else if(jsObj.task == "getFavouriteLinks")
						buildFavouriteLinks(results);
					else if(jsObj.task =="removeFavouriteLink"){


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



function buildFavouriteLinks(results){

$(".placeholderCenterDiv").children().remove();
$('.constituencyDiv').children().remove();
$('.stateDiv').children().remove();
$('.constituencyDivheading').children().remove();
$('.stateDivheading').children().remove();

 $('.placeholderCenterDiv').children().remove();
 clearAllSubscriptionDivs();

			for(var i in results){

		    if(results[i].favouriteLinkType == "Constituency"){
				if(i ==0)
				{
					
					var div = $('<div class="constituencyHeadingDiv"><a href="javaScript:{}" class="label label-info constituencyHeadingCls">Constituency</a></div>');
					$('.constituencyDivheading').append(div);
				}
				var template = $('.favouriteLinkConstituencyClass');

				var templateClone = template.clone();

				templateClone.removeClass('favouriteLinkConstituencyClass');

				templateClone.attr('id',results[i].userFavoriteLinksId);


				templateClone.find('.imageClass').html("<i class='icon-tags'></i>");
				templateClone.find('.titleClass').html("<b><a class='problemTitle' href="+results[i].favouriteLink+">"+results[i].favouriteLinkTitle+"</a></b>");
				templateClone.find('.removeClass').html("<b><a class='removeLinkButton btn'  href='javaScript:{removeFavouriteLink("+results[i].userFavoriteLinksId+")}'>Remove</a></b>");

				templateClone.appendTo('.constituencyDiv');
			}

			}

			for(var j in results){

				if(j==0)
					$('.stateDivheading').html('<span class="stateHeadingCls">State</span>');
				
		    if(results[j].favouriteLinkType == "State"){

				$('.stateDivheading').html('<a href="javaScript:{}" class="label label-info stateHeadingCls">State</span>');
				
				var template = $('.favouriteLinkConstituencyClass');

				var templateClone = template.clone();

				templateClone.removeClass('favouriteLinkConstituencyClass');

				templateClone.attr('id',results[j].userFavoriteLinksId);

				templateClone.find('.imageClass').html("<i class='icon-tags'></i>");
				templateClone.find('.titleClass').html("<b><a  class='problemTitle' href="+results[j].favouriteLink+">"+results[j].favouriteLinkTitle+"</a></b>");
				templateClone.find('.removeClass').html("<b><a class='removeLinkButton btn'  href='javaScript:{removeFavouriteLink("+results[j].userFavoriteLinksId+")}'>Remove</a></b>");

				templateClone.appendTo('.stateDiv');
			}

			}
			

}



function updatedInfo(results){
	return;
}
function getFriendsListForUser(results)
{
		$(".placeholderCenterDiv").children().remove();

	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.resultStatusForConnectedPeople.resultCode != "0")
	{
		$("#headerDiv").html('<div>Data could not be retrived due to some technical difficulties</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	else if(results.connectedPeople == "")
	{
		$("#headerDiv").html('<div>There are no connections established till now.</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	
	
	$("#headerDiv").html('You have total <span style="color:blue;">'+results.connectedPeople.length+'</span>  connections.');
	
	for(var i in results.connectedPeople)
	{
		
		var imageStr = "pictures/profiles/"+results.connectedPeople[i].image;
		var template = $(".templateDiv");
		var templateClone =  template.clone();
		templateClone.removeClass("templateDiv");
		templateClone.find('.connectedPersonName').html('<a href="publicProfile.action?profileId='+results.connectedPeople[i].id+'">'+results.connectedPeople[i].candidateName+'</a>');
		if(results.connectedPeople[i].image == null)
			templateClone.find('.imgClass').html('<a href="publicProfile.action?profileId='+results.connectedPeople[i].id+'"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/></a>');
		else
			templateClone.find('.imgClass').html('<a href="publicProfile.action?profileId='+results.connectedPeople[i].id+'"><img height="50" width="55" src="'+imageStr+'"/></a>');
		templateClone.find('.constituencyName').html(''+results.connectedPeople[i].constituencyName+'');
		templateClone.find('.districtName').html(''+results.connectedPeople[i].district+'');
		templateClone.find('.stateName').html(''+results.connectedPeople[i].state+'');
		templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" rel="tooltip" title="Send Message" class="btn btn-mini"><i class="icon-envelope opacityFilter-50"></i></a>');
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
		templateClone.find('.connectedPersonName').html('<a href="publicProfile.action?profileId='+results.friendRequest[i].id+'">'+results.friendRequest[i].candidateName+'</a>');
		templateClone.find('.imgClass').html('<a href="publicProfile.action?profileId='+results.friendRequest[i].id+'"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/></a>');
		templateClone.find('.messageCls').html(''+results.friendRequest[i].message+'').css("display","block");
		templateClone.find('.constituencyName').html(''+results.friendRequest[i].constituencyName+'');
		templateClone.find('.districtName').html(''+results.friendRequest[i].district+'');
		templateClone.find('.stateName').html(''+results.friendRequest[i].state+'');
		templateClone.find('.sendMsg').html('<a onclick="acceptRequest('+results.friendRequest[i].id+')" class="acceptButton btn btn-mini" rel="tooltip" title="Confirm"><i class="icon-thumbs-up opacityFilter-50"></i></a>');
		templateClone.find('.connectCls').html('<a onclick="rejectRequest('+results.friendRequest[i].id+')" class="rejectButton btn btn-mini" rel="tooltip" title="Reject" ><i class="icon-thumbs-down opacityFilter-50"></i></a>');
		templateClone.find('.blockPersonBtn').html('<a onclick="blockRequest('+results.friendRequest[i].id+')" class="rejectButton btn btn-mini" rel="tooltip" title="Block This Person"><i class="icon-ban-circle opacityFilter-50"></i></a>').css("display","block");
		templateClone.appendTo(".placeholderCenterDiv");
	}
}


function showRequestedMessagesForAUser(results)
{
		$(".placeholderCenterDiv").children().remove();

	$(".FavoriteLinksDiv").children().remove();
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.resultStatus.resultCode !="0")
	{
		$("#headerDiv").html("Data could not be retrived due to some technical difficulties.");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$("#headerDiv").html("No messages has been sent to you.");
		return;
	}
		
		$("#headerDiv").html('<ul class="nav nav-tabs"><li class="active"><a id="Inbox" >Inbox ( '+results.unreadMsgCount +' )</a></li><li><a id="SentBox">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');
		for(var i in results.candidateVO)
		{
		var template = $(".templateDivMsg");
		var templateClone = template.clone();
		templateClone.removeClass("templateDivMsg");
		templateClone.find(".messageFrom").html(''+results.candidateVO[i].candidateName+'');
		templateClone.find(".message").html(''+results.candidateVO[i].message+'');
		if(results.candidateVO[i].status == "UNREAD"){
	      templateClone.addClass("unreadfont");
		  templateClone.find(".readMsg").removeClass("popover-title");
		}
		templateClone.find(".msgid").val(results.candidateVO[i].costumMessageId);
		
		if(results.candidateVO[i].profileImg!=""){
			var imageStr = "pictures/profiles/"+results.candidateVO[i].profileImg;
			templateClone.find('.imgClass').html('<img height="30" width="30" src='+imageStr+'></img>');
			}
		else{
				templateClone.find('.imgClass').html('<img height="30" width="30" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/>');
			}
			templateClone.find(".dateAndTimeReceived").html(''+results.candidateVO[i].postedDate+'');
			templateClone.find(".reply").html('<a data-placement="top" rel="tooltip" href="#" data-original-title="Reply To This Message" class="btn" style="color:black;" onclick="showMailPopup('+results.candidateVO[i].id+',\''+results.candidateVO[i].candidateName+'\',\'Message\')"><i class=" icon-repeat"></i> Reply</a>');
			
			/*templateClone.find(".reply").html('<a href="javascript:{}" onclick="showMailPopup('+results.candidateVO[i].id+',\''+results.candidateVO[i].candidateName+'\',\'Message\')" class="btn" title="reply">Reply</a>');
			
			templateClone.find(".msgDelete").html('<a href="javascript:{}" class="icon-remove"></a>');*/
		templateClone.appendTo(".placeholderCenterDiv");
		}
}

function showSentBoxMessagesForAUser(results)
{
	$(".placeholderCenterDiv").children().remove();
	$("#headerDiv").html('');
	
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	if(results.resultStatus.resultCode !="0")
	{
		$("#headerDiv").html("Data could not be retrived due to some technical difficulties.");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$("#headerDiv").html("No messages has been sent by you.");
		return;
	}
		
		
		$("#headerDiv").html('<ul class="nav nav-tabs"><li><a id="Inbox" >Inbox</a></li><li class="active"><a id="SentBox">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;" class="pull-right">'+results.totalMsgCount+'</span></h6>');
		for(var i in results.candidateVO)
		{
		var template = $(".templateDivMsg");
		var templateClone = template.clone();
		templateClone.removeClass("templateDivMsg");
		templateClone.find(".reply").remove();
		templateClone.find(".messageFrom").html(''+results.candidateVO[i].candidateName+'');
			templateClone.find(".message").html(''+results.candidateVO[i].message+'');
			//templateClone.find('.imgClass').html('<img height="45" width="45" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/>');
			templateClone.find(".dateAndTimeReceived").html(''+results.candidateVO[i].postedDate+'');
			if(results.candidateVO[i].profileImg!=""){
			var imageStr = "pictures/profiles/"+results.candidateVO[i].profileImg;
			templateClone.find('.imgClass').html('<img height="30" width="30" src='+imageStr+'></img>');
			}
			else{
				templateClone.find('.imgClass').html('<img height="30" width="30" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/>');
			}
			
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
		var button = $("<input class='btn-info btn-small' id='sendMessageButtonId' type='button' value='send' onclick='sendMessageToConnectedUser("+id+",\""+type+"\")'/>");
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
		$(".placeholderCenterDiv").children().remove();

	var elmt = $("#ErrorMsgDivId");
	enableButton("sendMessageButtonId");
    if(results.resultCode == 0)
	{
		$("#connectMessageText").val('');
		$("#ErrorMsgDivId").html('<font color="green">Message Sent Successfully..</font>');
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
		$(".placeholderCenterDiv").children().remove();

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
		templateClone.find(".connectedPersonName").html('<a href="specialPageAction.action?specialPageId='+results[i].specialPageId+'">'+results[i].title+'</a>');
		templateClone.find(".imgClass").html('<img src="'+results[i].eventImagePath+'"/>');
		templateClone.find(".constituencyName").html(''+results[i].title+'');
		templateClone.appendTo(".placeholderCenterDiv");
	}
}

function closeConnectPanel(jsObj,results)
{ 
		$(".placeholderCenterDiv").children().remove();

	var connectUserMsg = $("#connectUserMsg").val('');
	if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{
		$("#errorMsgDiv").html('<font color="green" style="font-weight:bold;"> Requested sent successfully.</font>');
		//var t=setTimeout("closeConnectPopup()",2000);
		//buildConnectUsersContent(results.candidateVO,connectDivId,jsObj.locationType,jsObj.locationId,jsObj.locationName,connectUserLoginStatus,jsObj.userId);		
		return;
	}
	else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
	{
		$("#errorMsgDiv").html('<font color="red" style="font-weight:bold;">Request Cannot be sent due to some technically difficulty.</font>');
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
	
	filterDiv.append("<div style='padding:4px;width:100%;'><label style='width:40%;float:left;'>Search By Name :</label><input id='connectStatusTextBox' type='text' value='' onkeyup='getAllConnectedUsersByFilterView(\""+jsObj.locationType+"\",\""+jsObj.userId+"\") '/></div>");
	if(jsObj.locationType == "DISTRICT")
	{
		
		str +='<div style="padding:4px;width:100%;"><label    style="width:40%;float:left;">By Constituency: </label>';
		str +='<select id="connectConstituencySelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\',\''+jsObj.userId+'\')">';
		str +='<option value="0">All</option>';
		for(var i in constituencies)
			str += '<option value="'+constituencies[i].id+'">'+constituencies[i].name+'</option>';
		str += '</select></div>';
	}

	str +='<div style="padding:4px;width:100%;"><label style="width:40%;float:left;">By Status:</label>';
	str +='<select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\',\''+jsObj.userId+'\')">';
	str +='<option value="0">All</option>';
	for(var i in connectStatus)
	str +='<option id='+connectStatus[i].id+'>'+connectStatus[i].name+'</option>';
	str +='</select></div>';
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
			templateClone.find(".imgClass").html('<img height="45" width="50" src="'+imageStr+'" />');
			templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].candidateName+'');
			templateClone.find(".constituencyName").html(''+results.candidateVO[i].constituencyName.toLowerCase()+'');
			templateClone.find('.stateName').html(''+results.candidateVO[i].state+'');
			templateClone.find('.districtName').html(''+results.candidateVO[i].district+'');
			if(results.candidateVO[i].status != null && results.candidateVO[i].status == "NOT CONNECTED")
				templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\')">Connect</a>');
			else if(results.candidateVO[i].status != null && results.candidateVO[i].status == "PENDING")
				templateClone.find('.connectCls').html('<a rel="tooltip"  href="javascript:{}" title="Pending" class="btn btn-mini" ><i class="icon-adjust opacityFilter-50"></i></a>');
			templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\',\'Message\')" rel="tooltip" class="btn btn-mini" title="Send Message"><i class="icon-envelope opacityFilter-50"></i></a>');
			
			templateClone.appendTo(".placeholderCenterDiv");
			
	}

	var pagination = $('<div class="custom_paginator_class" style="clear: both; margin-top: 0px; padding-top: 10px;"></div>');
	pagination.appendTo('.placeholderCenterDiv');
}

function getAllConnectedUsersByFilterView(locationType,userId)
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
		resultsCount:20,
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
		//$(".templateDiv").html('No people connected in this district with this view..').appendTo(".placeholderCenterDiv");
		$(".placeholderCenterDiv").html('<div class="templateDiv">No people connected in this district with this view..</div>');
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
			templateClone.find(".connectedPersonName").html(''+results.candidateVO[i].candidateName+'');
			templateClone.find(".constituencyName").html(''+results.candidateVO[i].constituencyName.toLowerCase()+'');
			templateClone.find(".districtName").html(''+results.candidateVO[i].district+'');
			templateClone.find('.stateName').html(''+results.candidateVO[i].state+'');

			if(image == null)
				templateClone.find(".imgClass").html('<img width="50" height="45" src="/PartyAnalyst/images/icons/indexPage/human.jpg">');
			else
				templateClone.find(".imgClass").html('<img height="45" width="50" src="'+imageStr+'" />');

			if(results.candidateVO[i].status != null && results.candidateVO[i].status != "CONNECTED")
				  templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\')">Connect</a>');
			templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\',\'Message\')" title="Send Message" rel="tooltip" class="btn btn-mini"><i class="icon-envelope opacityFilter-50"></i></a>');
			templateClone.appendTo(".placeholderCenterDiv");
			
			
		}
		
	 var pagination = $('<div class="custom_paginator_class" style="clear: both; margin-top: 0px; padding-top: 10px;"></div>');
	pagination.appendTo('.placeholderCenterDiv');
	}
}


//total problems

function showAllPostedProblems(jsObj,results)
{
	$(".placeholderCenterDiv").children().remove();
	
	$('.viewMoreDiv').html('');
		
	clearAllSubscriptionDivs();
	
	var problemsData = results.problemsInfo;
	
	if(problemsData == null && jsObj.linkType == "problemsLink")
	{
		$('.problemTemplateDiv').html('Problems Does Not Exists').appendTo('.placeholderCenterDiv');
		return;
	}
	else if(problemsData == null && jsObj.linkType == "problemsViewMoreLink")
	{
		$('.problemsViewMoreLink').css("display","none");
		return;
	}
	
	if(jsObj.linkType == "problemsLink"){
		$(".placeholderCenterDiv").children().remove();
	  	}
	for(var i in problemsData)
	{
		var imageStr = "pictures/profiles/"+problemsData[i].userImageURL;
		var template = $('.problemTemplateDiv');
		var templateClone = template.clone();
		templateClone.removeClass('problemTemplateDiv');
		templateClone.find('.problemReportedDate').html(''+problemsData[i].identifiedDate+'');
		templateClone.find('.problemImg').html('<img height="30" width="30" src='+imageStr+'></img>');
		templateClone.find('.problemTitle').html('<a href="completeProblemDetailsAction.action?problemId='+problemsData[i].problemID+'">'+problemsData[i].definition+'</a>');
		templateClone.find('.problemDescription').html(''+problemsData[i].description+'');
		templateClone.find('.problemFromDate').html('<span style="color:seagreen;">Existing From: </span>'+problemsData[i].existingFrom+'');
		templateClone.find('.commentCls').html('<a href="completeProblemDetailsAction.action?problemId='+problemsData[i].problemID+'">Comment</a>');
		templateClone.find('.shareCls').html('<a href="completeProblemDetailsAction.action?problemId='+problemsData[i].problemID+'">Share</a>');
		//if(problemsData[i].rating != null)
			//templateClone.find('.problemRating').html('<div class="star pull-right"></div><input type="hidden" style="display:none;" value="'+problemsData[i].rating +'" >');
		templateClone.appendTo('.placeholderCenterDiv');
	}
	var viewMore = $('<div class="viewMoreDiv"><span class="problemsViewMoreLink">View More</span><span class="ajaxImg"><img src="images/icons/search.gif"/></span><input type="hidden" value="'+jsObj.type+'" class="problemViewMoreTypeVar"/></div>');
	viewMore.appendTo('.placeholderCenterDiv');
}



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
		$(".placeholderCenterDiv").children().remove();


if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{		
		var msga = $('<font color="green" style="font-weight:bold;"> Request sent to selected users successfully.');
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

function showAllPostedReasonsForUserProfile(jsObj,results)
{
	$(".placeholderCenterDiv").children().remove();
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();

	var data = results.candidateComments;

	if(data == null && jsObj.linkType == "assessPoliticianLink1")
	{
		$(".templateDivMsg").html('<div>No comments.</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	if(data == null && jsObj.linkType == "PoliticalReaViewMoreLink")
	{
		$(".viewMoreDiv").css("display","none");
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
	var viewMore = $('<div class="viewMoreDiv"><span class="PoliticalReaViewMoreLink">View More</span><input type="hidden" value="'+jsObj.type+'" class="politicalReasonViewMoreTypeVar"/></div>');
	viewMore.appendTo('.placeholderCenterDiv');

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

function showPostedReasons(jsObj,results)
{
	$(".placeholderCenterDiv").children().remove();
	$("#headerDiv").html('');
	
	var approvedReasonsCount = 0;
	var rejectedReasonsCount = 0;
	var notConsideredReasonsCount = 0;
	var totalPostedReasonsCount = 0;
	var postedReasonsCountByOtherUsers = 0;
	var postedReasonsByLoggedInUser = 0;
	var connectedUsersReasCount = 0;

	if(results != null)
	{
		approvedReasonsCount = results.approvedReasonsCount;
		rejectedReasonsCount = results.rejectedReasonsCount;
		notConsideredReasonsCount = results.notConsideredReasonsCount;
		totalPostedReasonsCount = results.totalPostedReasonsCount;
		postedReasonsCountByOtherUsers = results.postedReasonsCountByOtherUsers;
		connectedUsersReasCount = results.postedReasonsCountByConnectedUsers;
		postedReasonsByLoggedInUser = approvedReasonsCount + rejectedReasonsCount;
	}

	var div = $('<div class="politicalReasonsInnerDiv"></div>');
	var ul = $('<ul></ul>');
	if(userType != "PartyAnalyst")
	{
			
      if(totalPostedReasonsCount ==0)
		ul.append('<li class="fontStyle">Total Political reasons posted - '+totalPostedReasonsCount+'</li>');
	   else
		ul.append('<li class="fontStyle">Total Political reasons posted - <a href="javascript:{}" class="assessPoliticianLink">'+totalPostedReasonsCount+'</a><input type="hidden" value="Total" class="politicalReasTypeVar" /></li>');
	   if(postedReasonsByLoggedInUser ==0)
			 ul.append('<li class="fontStyle">By You - '+postedReasonsByLoggedInUser+'</li>');
	   else
   		 ul.append('<li class="fontStyle">By You - <a href="javascript:{}" class="assessPoliticianLink">'+connectedUsersReasCount+'</a><input type="hidden" value="LOGGED_USER" class="politicalReasTypeVar" /></li>');
	   
	   if(connectedUsersReasCount ==0)
		 ul.append('<li class="fontStyle">By Friends - '+connectedUsersReasCount+'</li>');
	   else
		ul.append('<li class="fontStyle">By Friends - <a href="javascript:{}" class="assessPoliticianLink">'+connectedUsersReasCount+'</a><input type="hidden" value="ConnectedUserPoliticalReasons" class="politicalReasTypeVar" /></li>');	

	   if(postedReasonsCountByOtherUsers ==0)
		 ul.append('<li class="fontStyle">By Others - '+postedReasonsCountByOtherUsers+'</li>');
	   else
		ul.append('<li class="fontStyle">By Others - <a href="javascript:{}" class="assessPoliticianLink">'+postedReasonsCountByOtherUsers+'</a><input type="hidden" value="OtherUsers" class="politicalReasTypeVar" /></li>');	
	}
	else
	{
		ul.append('<li class="fontStyle"> Total Political reasons posted - <a href="javascript:{}" class="assessPoliticianLink">'+totalPostedReasonsCount+'</a><input type="hidden" value="Total" class="politicalReasTypeVar" /></li>');
		ul.append('<li class="fontStyle">By You - <a href="javascript:{}" class="assessPoliticianLink">'+postedReasonsByLoggedInUser+'</a><input type="hidden" value="LOGGED_USER" class="politicalReasTypeVar" /></li>');
		ul.append('<li class="fontStyle"> By Friends - <a href="javascript:{}" class="assessPoliticianLink">'+connectedUsersReasCount+'</a><input type="hidden" value="ConnectedUserPoliticalReasons" class="politicalReasTypeVar" /></li>');	
		ul.append('<li class="fontStyle"> By Others - <a href="javascript:{}" class="assessPoliticianLink">'+postedReasonsCountByOtherUsers+'</a><input type="hidden" value="OtherUsers" class="politicalReasTypeVar" /></li>');	
	}
		
	/* if(userType != "PartyAnalyst"){		
		div.append('<span class="fontStyle"><a href="javascript:{}" onclick="openAddReasonWindow(\'analyze\')" style="color:#669900;text-decoration: underline;">Add Reasons</a>');
		div.append('<span class="fontStyle"><a href="javascript:{}" onclick="openAddReasonWindow(\'viewResults\')" style="color:#669900;text-decoration: underline;">View Reasons</a>');
	}*/
	
	var label = $('<div style="color:#9E7556;font-weight:bold;padding:5px;font-family:verdana;font-size:13px;"> Reasons Status Details Posted By You </div>');
	
	var ulinner = $('<ul></ul>');
	if(approvedReasonsCount == 0)
	 ulinner.append('<li class="fontStyle">Reasons Approved - '+approvedReasonsCount+'</li>');
	else
	ulinner.append('<li class="fontStyle" style="margin-left:35px;">Reasons Approved - <a class="reasonsCountAnc assessPoliticianLink" href="javascript:{}">'+approvedReasonsCount+'</a> <input type="hidden" value="Approved" class="politicalReasTypeVar" /></li>');
	
	if(rejectedReasonsCount ==0)
		ulinner.append('<li class="fontStyle" style="margin-left:35px;">Reasons Rejected - '+rejectedReasonsCount+'</li>');
	else
	ulinner.append('<li class="fontStyle">Reasons Rejected - <a class="reasonsCountAnc assessPoliticianLink" href="javascript:{}">'+rejectedReasonsCount+'</a> <input type="hidden" value="rejected" class="politicalReasTypeVar" /></li>');
	div.append(ul);
	div.append(label);
	div.append(ulinner);
	$("#headerDiv").append(div);
}

/* -- subscription functions Start -- */

function showAllUserSubScribedSpecialPagesPages(jsObj,results)
{
	$(".placeholderCenterDiv").children().remove();
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userSpecialPageSubscriptionsDiv').children().remove();
	$('#userSpecialPageUnSubscriptionsDiv').children().remove();
	$('#userSpecialPageSubscriptionsDiv').html('');
	$('#userSpecialPageUnSubscriptionsDiv').html('');

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
		  templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">UNSUBSCRIBE</a>');
		  templateClone.find('.hiddenVar').html('<input type="hidden" value="'+specialPages[i].specialPageVOList[i].specialPageId+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="specialPage"/>');
		  templateClone.appendTo('#userSpecialPageSubscriptionsDiv');
		}
		else
		{
		  var template = $(".specialPagSubscrTemplDiv");
		  var templateClone = template.clone();
		  templateClone.removeClass('specialPagSubscrTemplDiv');
		  templateClone.find('.titleCls').html(''+specialPages[i].specialPageVOList[i].title+'');
		  templateClone.find('.imgClass').html('<img height="100" width="95" src="'+specialPages[i].specialPageVOList[i].eventImagePath+'"/>');
		  templateClone.find('.btnClass').html('<a href="javascript:{}" class="subscribedLink">SUBSCRIBE</a>');
		  templateClone.find('.hiddenVar').html('<input type="hidden" value="'+specialPages[i].specialPageVOList[i].specialPageId+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="specialPage"/>');
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
	
	$(".placeholderCenterDiv").children().remove();

	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userCandidateSubscriptionsDiv').children().remove();
	$('#userCandidateSubscriptionsDiv').html('');
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
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">UNSUBSCRIBE</a>');
		else*/
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">UNSUBSCRIBE</a>');
		
		templateClone.find('.hiddenVar').html('<input type="hidden" value="'+politicians[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="candidatePage"/>');
		templateClone.appendTo('#userCandidateSubscriptionsDiv');
	}
}



function showAllUserConstituencySubscriptions(jsObj,results)
{
	$(".placeholderCenterDiv").children().remove();

	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userConstituencySubscriptionsDiv').children().remove();
	$('#userConstituencySubscriptionsDiv').html('');
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
		templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">UNSUBSCRIBE</a>');
		templateClone.find('.hiddenVar').html('<input type="hidden" value="'+constituencies[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="constituencyPage"/>');
		templateClone.appendTo('#userConstituencySubscriptionsDiv');
	}
}

function showAllUserPartySubscriptions(jsObj,results)
{
	$(".placeholderCenterDiv").children().remove();

	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userPartySubscriptionsDiv').children().remove();
	$('#userPartySubscriptionsDiv').html('');
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
		templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink">UNSUBSCRIBE</a>');
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
	$(".placeholderCenterDiv").children().remove();
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


//problem count

function getAllPostedProblemsForUser()
{
	
	var jsObj=
	{
			task:"getAllPostedProblemsByUser"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPostedProblemsByUserAction.action?"+rparam;						
	callAjax1(jsObj,url);
}

function showPostedProblems(jsObj,results)
{
    $(".placeholderCenterDiv").children().remove();
	$('#headerDiv').html('');
	var div = $('<div style="line-height:1.5em;"></div>');
	var ul = $('<ul></ul>');
	if(results.totalPostedProblemsCount == 0)
		ul.append('<li class="fontStyle">Total posted problems - '+results.totalPostedProblemsCount+'</li>');
	else
	  ul.append('<li class="fontStyle">Total posted problems - <a  href="javascript:{}" class="problemsLink">'+results.totalPostedProblemsCount+'</a><input type="hidden" value="Total" class="problemTypeVariable"/></li>');
		
	if(results.postedProblemsCountByLoggedInUsers == 0)
		ul.append('<li class="fontStyle">By You - '+results.postedProblemsCountByLoggedInUsers+'</li>'); 
	else
	  ul.append('<li class="fontStyle">By You - <a href="javascript:{}" class="problemsLink">'+results.postedProblemsCountByLoggedInUsers+'</a><input type="hidden" value="LOGGED_USER" class="problemTypeVariable"/></li>');
		
	if(results.postedProblemsCountByOtherUsers == 0)
		ul.append('<li class="fontStyle">By Others - '+results.postedProblemsCountByOtherUsers+'</li>');
	else
	  ul.append('<li class="fontStyle">By Others - <a href="javascript:{}" class="problemsLink">'+results.postedProblemsCountByOtherUsers+'</a><input type="hidden" value="OtherUsers" class="problemTypeVariable"/></li>');

	if(results.postedProblemsCountByConnectedUsers == 0)
		ul.append('<li class="fontStyle">By Friends - '+results.postedProblemsCountByConnectedUsers+'</li>');
	else 
		ul.append('<li class="fontStyle">By Friends - <a href="javascript:{}" class="problemsLink">'+results.postedProblemsCountByConnectedUsers+'</a><input type="hidden" value="ConnectedUserProblems" class="problemTypeVariable"/></li>');
	
	var label = $('<label class="l1">Problem Status Details Posted By You </label>');
	var ulInner = $('<ul></ul>');

	if(results.approvedProblemsCount ==0)
		ulInner.append('<li class="fontStyle" style="margin-left: 2px;">Problems Approved	- '+results.approvedProblemsCount+'</li>');
	else
	 ulInner.append('<li class="fontStyle" style="margin-left: 35px;">Problems Approved - <a href="javascript:{}" class="problemsLink">'+results.approvedProblemsCount+'</a><input type="hidden" value="approved" class="problemTypeVariable"/></li>');
	if(results.rejectedProblemsCount ==0)
		ulInner.append('<li class="fontStyle" style="margin-left: 35px;">Problems Rejected - '+results.rejectedProblemsCount+'</li>');
	else
	 ulInner.append('<li class="fontStyle" style="margin-left: 35px;">Problems Rejected - <a href="javascript:{}" class="problemsLink">'+results.rejectedProblemsCount+'</a><input type="hidden" value="rejected" class="problemTypeVariable"/></li> ');

	if(userType != "PartyAnalyst")
		ulInner.append('<span><a href="javascript:{}" class="postProblemLink btn btn-success btn-small" style="">Post Problem</a></span>');

	 div.append(ul);
	 div.append(label);
	div.append(ulInner);
	$('#headerDiv').append(div);
}


function removeFavouriteLink(id){

	$('#'+id).hide();

	var jsObj ={
		    linkId:id,
			task:"removeFavouriteLink"
	};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "removeFavouriteLinkAction.action?"+rparam;						
		callAjax1(jsObj,url);

}
