var connectStatus = [];
var constituencies = [];
var connetLocationId = '';
var uploadPicStatus = false;
var refreshTime=5;
var uploadResult;
var userType = '';
var startIndex;
var jcrop_api;
var flag=false;
var flag1=false;

$("document").ready(function(){
	$('.custom_paginator_class a').live("click",function() {
        $('.custom_paginator_class a').removeClass('pagenationStyle');
		$(this).addClass('pagenationStyle');
      });
	$('#announcementsDiv').hide();
$('#connectMessageText').live("keyup",function() {

	var limitFieldElmt = document.getElementById('connectMessageText');
	var limitCountElmt = document.getElementById('maxcount');
	var limitNum = 300;

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
	}
});


	var startIndex=0;
	var maxIndex=5;
	var resultsCount = 5;
	var clickid = null;

	setInterval("callForEveryFiveMins()", 10*60*60*5);
    $("#subscriptionsStreamingMore").live("click",function(){
	  getSubscriptionDetails("old");
	});
	$(".constituencyHeadingCls").live("click",function(){
		$('.constituencyDiv').slideToggle();	
		
	});
	$(".stateHeadingCls").live("click",function(){
	$('.stateDiv').slideToggle();	
		
	});

	$(".districtHeadingCls").live("click",function(){
	$('.districtDiv').slideToggle();	
		
	});

	$(".specialPageHeadingCls").live("click",function(){
	$('.specialPageDiv').slideToggle();	
		
	});

	
	$("#friendsLink").click(function(){
		var jsObj ={
			task:"getLatestFriendsList"
	};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getLatestFriendsListAction.action?"+rparam;
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
		callAjax1(jsObj,url);
		
	});

	$("#requestLink").click(function(){
		var jsObj ={
			task:"getAllRequestMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getReqMessagesForUserAction.action?"+rparam;	
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();		
		callAjax1(jsObj,url);
		
	});
	$(".ImportantDates").click(function(){
		clearAllSubscriptionDivs();
		clearAllFavoriteLinkDivs();
		buildCalendarControl();
		showInitialImpEventsAndDates(impDates,'impDates',"");
		renderStack();
		$("#impdatesDiv").show();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
		$(".placeholderCenterDiv").children().remove();
		var date = new Date();
		var month = date.getMonth();
		var nameOfMonth = monthname[month];
		var year = date.getFullYear();	
		$('#headerDiv').html('<b><font color="blue">'+year+' '+nameOfMonth+'</font> Month Important Dates</b>');
	});
	$("#announcements").click(function(){
		clearAllSubscriptionDivs();
		clearAllFavoriteLinkDivs();
		$("#announcementsDiv").show();
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$(".placeholderCenterDiv").children().remove();
		$('#headerDiv').html('<b>Announcements</b>');
	});
	$(".ImportantEvents").click(function(){
		clearAllSubscriptionDivs();
		clearAllFavoriteLinkDivs();
		$("#announcementsDiv").hide();
		$("#impdatesDiv").hide();
		$('#impEvents').show();
		$(".placeholderCenterDiv").children().remove();
		getUserImpEvents();
	});
	$("#settings").click(function(){
	     $("#subscriptionsStreamingMoreDiv").hide();
		$(".placeholderCenterDiv").children().remove();
		clearAllFavoriteLinkDivs();
		clearAllSubscriptionDivs();
		$("#headerDiv").html('');
		  var str='<table class="table table-hover"><thead><tr><td><b>Account Settings</b></td></tr></thead>';
              str+='<tbody><tr><td><a href="freeUserRegistration.action"><span class="icon-pencil"></span>  Edit Profile</a></td></tr>';
              str+='  <tr><td><a href="javascript:{}" class="changePwdLink"><span class="icon-hand-right"></span>  Change Password</a></td></tr>';
              str+='   <tr><td><a href="javascript:{}" class="editPictureLink"><span class="icon-user"></span>  Edit Picture</a></td></tr>';
              str+='   <tr><td><a href="javascript:{getUserSettingsDetails();}" class="editSettingsLink"><span class="icon-thumbs-up"></span>  Edit View Settings</a></td></tr>';
             // str+='   <tr><td><a href="javascript:{}" class="editCoverImgLink"><span class="icon-user"></span>  Upload CoverImage</a></td></tr>';
			  str+='  </tbody>';
              str+='</table>';
		//$("#headerDiv").append("<ul id='accountStngs'><li><span style='font-weight:bold;'>Settings</span></li><li class='btn'><a href='freeUserRegistration.action'><span class='icon-pencil'></span>  Edit Profile</a></li><li class='btn'><a href='javascript:{}' class='changePwdLink'><span class='icon-hand-right'></span> Change Password</a></li><li class='btn'><a href='javascript:{}' class='editPictureLink'><span class='icon-user'></span> Edit Picture</a></li><li class='btn'><a href='javascript:{getUserSettingsDetails();}' class='editSettingsLink'><span class='icon-thumbs-up'></span> Edit View Settings</a></li><li class='btn'><a href='javascript:{}' class='editCoverImgLink'><span class='icon-user'></span> Upload CoverImage</a></li></ul>");
		$("#headerDiv").append(str);
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
	});
	
	$(".messagesLink").click(function(){
	    
				
		if($("#headerDiv").find("#Inbox").length<1) {
		$('#headerDiv').prepend("<ul class='nav nav-tabs'><li class='active' ><a id='Inbox' href='' >Inbox</a></li><li><a id='SentBox' href=''>Sent</a></li></ul>"); }
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
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
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
		

		custom_paginator1.paginator({
		   startIndex:0,
		   resultsCount:10,
		   jsObj:jsObj,
		   ajaxCallURL:url,
		   paginatorElmt:"custom_paginator_class",
		   callBackFunction:function(){
		 
	          buildProblemDetailsByStatus(jsObj,results);
		   }
	     });	
	   
	   custom_paginator1.initialize();	
	});

	$('.linkDiv').live("click",function(){
		$(this).remove();
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
	});
	
	
	
	$("#SentBox").live("click",function(){
		$(this).closest(".nav-tabs").find("li").removeClass("active");
		$(this).parent().addClass("active");
		
		var jsObj ={
			task:"getSentBoxMessagesForUser"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllSentMessagesForAUserAction.action?"+rparam;	
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
		
		custom_paginator1.paginator({
		   startIndex:0,
		   resultsCount:10,
		   jsObj:jsObj,
		   ajaxCallURL:url,
		   paginatorElmt:"custom_paginator_class",
		   callBackFunction:function(){
		 
	          buildSentDetailsByStatus(jsObj,results);
		   }
	     });	
	   
	   custom_paginator1.initialize();	
	});
	
	
	$("#specialPageLink").click(function(){
		var jsObj ={
			task:"getSpecialPages"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSpecialPageAction.action?"+rparam;	
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
		callAjax1(jsObj,url);
	});
	


   $("#FavouriteLinks").click(function(){
   
	 var jsObj ={
			task:"getFavouriteLinks"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getFavouriteLinksAction.action?"+rparam;	
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
		callAjax1(jsObj,url);
	});


	//subscriptions

	 $(".subscriptionsLink").click(function(){
		 $("#subscriptionsStreamingMoreDiv").hide();
		  $("#subscriptionsStreamingData").html('');
		var jsObj ={
			task:"getUserScriptions"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getUserSubScriptionsAction.action?"+rparam;	
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
		callAjax1(jsObj,url);

	});


	$('.assessPoliticianLink').live("click",function(){
	    $("#subscriptionsStreamingMoreDiv").hide();
		$("#subscriptionsStreamingData").html('');
		var type = $(this).parent().find('.politicalReasTypeVar').val();
		var linkType = "assessPoliticianLink";
		startIndex = 0;
		getAllPostedReasonsForUser();
		var jsObj ={
				startIndex   : startIndex,
				maxIndex     : 10,
				type         : type,
				linkType     : linkType,
				task:"getAllPostedReasons"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getPostedReasonsUserProfileAction.action?"+rparam+"&type="+type+"&sort=candidate&dir=asc";
	$("#impdatesDiv").hide();
	$("#impEvents").hide();
	$("#announcementsDiv").hide();	
	callAjax1(jsObj,url);
	});

$('.PoliticalReaViewMoreLink').live("click",function(){
		var type = $(this).closest('div').find('.politicalReasonViewMoreTypeVar').val();
		var linkType = "PoliticalReaViewMoreLink";
		getAllPostedReasonsForUser();
		startIndex = startIndex+10;
		var jsObj ={
				startIndex     : startIndex,
				maxIndex       : 10,
				type           : type,
				linkType       : linkType,
				task:"getAllPostedReasons"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getPostedReasonsUserProfileAction.action?"+rparam+"&type="+type+"&sort=candidate&dir=asc";
	$("#impdatesDiv").hide();
	$("#impEvents").hide();
	$("#announcementsDiv").hide();	
	callAjax1(jsObj,url);
	});


	//problems 

	$(".problemsLink").live("click",function(){
		var type = $(this).parent().find(".problemTypeVariable").val();
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
	$("#impdatesDiv").hide();
	$("#impEvents").hide();
	$("#announcementsDiv").hide();
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
	$("#impdatesDiv").hide();
	$("#impEvents").hide();
	$("#announcementsDiv").hide();
	callAjax1(jsObj,url);
	});

	//flag value created by Srishailam
	$(".districtPeopleLink").click(function(){	
	flag=false;
	if(!flag1){
	flag1=true;
	getAllCconnectedUserDetails();
	setTimeout("getDetailsForallDistricts()",1000);	
	//setTimeout("getDetailsForallConstituencies(districtId)",750);		
	//setTimeout("getAllConnectedUsersByFilterView()",1000);
	}
	});


	$(".connectLink").live("click",function(){
		$("#allConnectedUsersDisplay_main").children().remove();			
		var userId = $(this).closest("div").find(".userId").val();
		var userName = $(this).closest("div").find(".userName").val();
		var constituencyName = $(this).closest("div").find(".constituencyName").val();
		var userLoginId = loginUserId;
		var locationId = constituencyId;
		var locationType = 'CONSTITUENCY';
		var locationName = constituencyName;
		
		$( "#connectPeoplePopup" ).dialog({
			title:"Connect To  "+userName+"("+constituencyName+")",
			autoOpen: true,
			show: "blind",
			width: 500,
			minHeight:250,
			modal: true,
			hide: "explode"
		});

		var div = $("<div class='connectPeoplePopupInnerDiv'></div>");
		var Name=$(userName);
		var constituencyName = $(constituencyName);
		var message = $("<label class='messageLabel'></label>");
		var textArea = $("<textarea id='connectUserMsg' placeholder='Enter Your Message Here..' style='width: 327px; height:102px;'></textarea>");
		var image = $('<img height="80" width="80" src="images/icons/indexPage/human.jpg" style="clear: both; margin-left: 26px; margin-top: -30px;">');
		var connectBtn = $('<input type="button" value="Send Request" id="connectPeopleLink" class="btn btn-info btn-mini" style="margin-right:0px;"/>');
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
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
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
		//$("#connectPeoplePopup").dialog('close');
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
	$("#impdatesDiv").hide();
	$("#impEvents").hide();
	$("#announcementsDiv").hide();	
		
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


		 if(connectMsg.length > 200)
		{
			$("#errorMsgDiv").html('<font style="color:red">Message Should be lessthan 200 characters.</font>');
			return;
		}
		
		disableButton("connectDistrictPeopleLink");
		$("#connectPeoplePopup").dialog("close");
		
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
	$("#impdatesDiv").hide();
	$("#impEvents").hide();
	$("#announcementsDiv").hide();
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
		$("#impdatesDiv").hide();
		$("#impEvents").hide();
		$("#announcementsDiv").hide();
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

//edit cover Image

$(".editCoverImgLink").live("click",function(){
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
	str += '<td width="40%" valign="top"><input class="btn-info btn-small" type="button" value="Upload" id="uploadCoverPicButton"/>';
	str += '<input class="btn-info btn-small" type="button" value="Cancel" id="cancelPicButton"/>';	
	str += '</td>';
	str += '</tr>';
	str += '</table>';	
	str += '</div>';
	div.append(str);
	elmt.append(div);
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
	str += '	<div style="width:100px;height:100px;overflow:hidden;"><img width="90" height="100" id="Imgpreview" class="jcrop-preview" src="images/icons/indexPage/human.jpg"></div>';
	str += '</td>';
	str += '</tr>';

	str += '<tr>';
	str += '<td>';
	str += '<input type="file" size="45" id="photoUploadElmt" name="upload" onchange="previewImg()" style="width:430px;"/>';
	str += '</td>';
	str += '</tr>';
	
	str += ' <input type="hidden" size="4" value=""  id="xcoardinate"  name="xcoardinate"   value="0" />'; 
     str += ' <input type="hidden" size="4"  value="" id="ycoardinate"  name="ycoardinate"   value="0" />';  
     str += ' <input type="hidden" size="4" value=""  id="width"        name="width" />';  
     str += ' <input type="hidden" size="4"  value="" id="height"       name="height" />'; 
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
	str += '<div style="width:300px;height:300px;" id="Imgpreview2"><img style="width:300px;height:300px;" width="300" height="300" id="Imgpreview1"  src=""></div>';
	div.append(str);
	elmt.append(div);
	
	oPushButton1 = new YAHOO.widget.Button("uploadPicButton");  
	oPushButton2 = new YAHOO.widget.Button("cancelPicButton");
	
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

	oPushButton2.on("click",function(){
		$("#uploadPic_window").dialog("destroy");
	});

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

$("#uploadCoverPicButton").live("click",function(){
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
			 getUploadCoverpic();
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
	
	$(".toggleDiv").live("click",function(){
		
		$(this).closest(".templateMessage").find(".msgbox").toggle('slow');
	});
	
	$(".postProblemLink").live("click",function(){

		var browser1 = window.open("addNewProblemAction.action?requestSrc=4&constituencyId="+constituencyId,"addNewProblemInConstituency","scrollbars=yes,height=600,width=600,left=200,top=200");
		browser1.focus();
	});

	$(".constituencyunSubscrBtn").live("click",function(){
	var id = $(this).closest('div').find('.hiddenVarId').val();
	var timeST = new Date().getTime();
	
	var jsObj=
	{		
            time : timeST,	
			id: id,
			task: "constituencyUnsubscriptionDetails",
			
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "constituencyEmailAlertsForUserAction.action?"+rparam;						
   callAjax1(jsObj,url);
  
		
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
  var img1 = document.getElementById("Imgpreview1");
//  img.src = evt.target.result;
    createCropImage(img,img1,evt);
    // img1.src = evt.target.result;
  evt=null;
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

function getUploadCoverpic()
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
	YAHOO.util.Connect.asyncRequest('POST', 'uploadPicAction.action?coverImg=true', uploadHandler);
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


					/*else if(jsObj.task == "getRequestMessagesForUser")
						showRequestedMessagesForAUser(results);*/
						
					/*else if(jsObj.task == "getSentBoxMessagesForUser")
						showSentBoxMessagesForAUser(results);*/
						
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
						getPeopleYouMayKnowDetails();
					}
					else if(jsObj.task =="connectUserSet")
					{
						showAllConnectedUsersStatus(jsObj,results);
					}
					else if(jsObj.task =="getUserScriptions")
					{
						clearAllFavoriteLinkDivs();
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
					else if(jsObj.task == "unsubscriptionDetails" || jsObj.task == "constituencyUnsubscriptionDetails"|| jsObj.task == "subscriptionDetails")
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
						$('#FavouriteLinks').click();
					}
					else if(jsObj.task == "allsubscriptions" || jsObj.task == "oldersubscriptions"){
					   $("#subscriptionsStreamingAjaxImg").hide();
					   if(results == "sessionExpired"){
						  openDialogForLoginWindow();
					   }
					   else{
					     buildAllSubscriptions(results,"end");
					   }
					}
					else if(jsObj.task == "latestsubscriptions"){
					   $("#subscriptionsStreamingAjaxImg").hide();
					   if(results == "sessionExpired"){
						  openDialogForLoginWindow();
					   }
					   else{
					     buildAllSubscriptions(results,"start");
					   }
					}
					else if(jsObj.task == "getFriendsList")
					{
						buildPeopleYouMayKnowBlock(results);
					}
					else if(jsObj.task== "saveFavouriteLink"){
						openModal("Link added successfully","msg");
						$("#FavouriteLinks").trigger('click');//to rebuild the favourite link section
						setTimeout(hello,1000);
					}
					else if(jsObj.task =="getStatesAjaxAction"){
						buildStatesIntoDropDown(results,'stateList');
					}
					else if(jsObj.task =="findDistrictsForAState"){
						buildStatesIntoDropDown(results,'districtList');
					}
					else if(jsObj.task =="getConstituencies"){
						buildStatesIntoDropDown(results,'constituencyList');
					}
					else if(jsObj.task =="getAllParliamentConstituencies"){
						buildStatesIntoDropDown(results,'constituencyList');
					}
					else if(jsObj.task =="forAllFavLinks"){
						buildAvailFavIds(results);
					}
					else if(jsObj.task == "getSpecialPagesIdTtl")
					{
						showSelectBoxForSpecialPages(results);
					}
					else if(jsObj.task == 'getDistrictNames')
							{
								iterateDistrictNames(results);
							}
				 else if(jsObj.task == 'getUserImportantEvents')
						    {
								buildUserImpEvents(results);
						    }							
					else if(jsObj.task == 'getConstituencyNames')
							{
								iterateDetailsNames(results);
							}
							else if(jsObj.task == 'deleteMessageFromInbox')
							{
								showDeleteMessagesStatus(results,jsObj);
							}
							else if(jsObj.task == "getCadreInfo")
							{
								buildCadreInfoTable(results);
							}
			}catch (e) {  
                  $("#subscriptionsStreamingAjaxImg").hide();			
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

function hello(){
	$('#districtList').find('option').remove().end().append('<option value="0">select</option>').val('0');
	$('#constituencyList').find('option').remove().end().append('<option value="0">select</option>').val('0');
	$('#specialPageList').find('option').remove().end().append('<option value="0">select</option>').val('0');
	
	rebuildId=$('input:radio[name=radioForFavourite]:checked').attr('id');
	if(rebuildId=='Special Page'){
		getSpecialPagesInfo();
	}
	else if(rebuildId=='Constituency'){
		if($('input:radio[name=typeRadio]:checked').attr('id')=='Parliament'){$('#Parliament').trigger('click');}
		else
		$('#'+rebuildId).trigger('click');
	}
	else if(rebuildId=='state'){
		$('#'+rebuildId).trigger('click');
	}
	else
	$('#'+rebuildId).trigger('click');
	
//constituencyList
}

//created by sasi
var conList = new Array();
var disList = new Array();
var stateList = new Array();
var spclList = new Array();
function buildAvailFavIds(results){
	conList.length=0;
	disList.length=0;
	stateList.length=0;
	spclList.length=0;

	if(results[0].setFavIdMap.constituencies.length>0){
	for(var i in results[0].setFavIdMap.constituencies){
		 conList.push(results[0].setFavIdMap.constituencies[i]);
		 }
	}
	
	if(results[0].setFavIdMap.district.length>0){
	for(var i in results[0].setFavIdMap.district){
		 disList.push(results[0].setFavIdMap.district[i]);
		 }
	}
	
	if(results[0].setFavIdMap.specialPages.length>0){
	for(var i in results[0].setFavIdMap.specialPages){
		 spclList.push(results[0].setFavIdMap.specialPages[i]);
		 }
	}
	
	if(results[0].setFavIdMap.states.length>0){
	for(var i in results[0].setFavIdMap.states){
		 stateList.push(results[0].setFavIdMap.states[i]);
		 }
	}
}


function buildFavouriteLinks(results){

$(".placeholderCenterDiv").children().remove();
$("#headerDiv").html('');
$(".specialPageDivInnerFav").children().remove();
$('.constituencyDivInnerFav').children().remove();
$('.districtDivInnerFav').children().remove();
$('.stateDivInnerFav').children().remove();
$('.constituencyDivheading').children().remove();
$('.stateDivheading').children().remove();
$('.districtDivheading').children().remove();
$('.specialPageDivheading').children().remove();

 clearAllSubscriptionDivs();

var constituency = false;
var state = false;
var district = false;
var specialpage=false;
if(results == null || results.lenght == 0)
{
	$("#headerDiv").html('<h5>No Favourite Links has been added.</h5><br>');
	$("#headerDiv").append('<h5 style="font-weight: bold; font-family: &quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif; font-size: 14px;margin-top: -14px;"><small style="font-size: 14px; color: rgb(74, 90, 85);">Add Your Interested</small> State/District/Constituency/Special Pages</h5><input type="button" style="margin-right:5px; margin-top: -31px;" class="btn btn-primary pull-right" value="Add" id="Add" onclick="openPopupForFavouriteLinks()"/>');
	return;
}

//$("#headerDiv").html('<span btn="info" onClick="savefavouriteLink()">Add</span> <br> <select id="specialPageSelectBox" name="specialPage"></select>');
$("#headerDiv").html('<h5 style="font-weight: bold; font-family: &quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif; font-size: 14px;"><small style="font-size: 14px; color: rgb(74, 90, 85);">Add Your Interested</small> State/District/Constituency/Special Pages</h5><input type="button" style="margin-right:5px; margin-top: -31px;" class="btn btn-primary pull-right" value="Add" id="Add" onclick="openPopupForFavouriteLinks()"/>');

 for(var i in results){
	 if(results[i].favouriteLinkType == "constituency")
		 constituency = true;
	 else if(results[i].favouriteLinkType == "state")
		 state = true;
	 else if(results[i].favouriteLinkType == "district")
		 district = true;
	 else
		 specialpage = true;

 }

			for(var i in results){

				if(i ==0 && constituency == true)
				{
					
					var div = $('<div class="constituencyHeadingDiv"><a href="javaScript:{}" class="label label-info constituencyHeadingCls" style="font-size: 16px;padding: 6px;">Constituency</a></div>');
					$('.constituencyDivheading').append(div);
				}


		    if(results[i].favouriteLinkType == "constituency"){
				
				var template = $('.favouriteLinkConstituencyClass');

				var templateClone = template.clone();

				templateClone.removeClass('favouriteLinkConstituencyClass');

				templateClone.attr('id',results[i].userFavoriteLinksId);


				templateClone.find('.imageClass').html("<i class='icon-tags'></i>");
				//templateClone.find('.titleClass').html("<b><a class='problemTitle' href="+results[i].favouriteLink+">"+results[i].favouriteLinkTitle+"</a></b>");
				var flag =results[i].name.search(" Assembly");
				
				if(flag !=-1)
				{
				templateClone.find('.titleClass').html("<b><a  class='problemTitle' title='"+results[i].favouriteLinkTitle+"' href="+results[i].favouriteLink+">"+results[i].name+"</a></b>");
				templateClone.find('.removeClass').html("<b><a class='removeLinkButton btn'  href='javaScript:{removeFavouriteLink("+results[i].userFavoriteLinksId+")}'>Remove</a></b>");

				templateClone.appendTo('.constituencyDivInnerFav');
				}
				else
				{
				templateClone.find('.titleClass').html("<b><a  class='problemTitle' title='"+results[i].favouriteLinkTitle+"' href="+results[i].favouriteLink+">"+results[i].name+"</a></b>");
				templateClone.find('.removeClass').html("<b><a class='removeLinkButton btn'  href='javaScript:{removeFavouriteLink("+results[i].userFavoriteLinksId+")}'>Remove</a></b>");

				templateClone.appendTo('.constituencyDivInnerFav1');
				}
			}

			}

			for(var j in results){

				if(j==0 && state == true)
					$('.stateDivheading').html('<span class="stateHeadingCls">State</span>');
				
		    if(results[j].favouriteLinkType == "state"){

				$('.stateDivheading').html('<a href="javaScript:{}" class="label label-info stateHeadingCls" style="font-size: 16px;padding: 6px;">State</span>');
				
				var template = $('.favouriteLinkConstituencyClass');

				var templateClone = template.clone();

				templateClone.removeClass('favouriteLinkConstituencyClass');

				templateClone.attr('id',results[j].userFavoriteLinksId);

				templateClone.find('.imageClass').html("<i class='icon-tags'></i>");
				//templateClone.find('.titleClass').html("<b><a  class='problemTitle' href="+results[j].favouriteLink+">"+results[j].favouriteLinkTitle+"</a></b>");

				templateClone.find('.titleClass').html("<b><a  class='problemTitle' title='"+results[j].favouriteLinkTitle+"' href="+results[j].favouriteLink+">"+results[j].name+"</a></b>");

				templateClone.find('.removeClass').html("<b><a class='removeLinkButton btn'  href='javaScript:{removeFavouriteLink("+results[j].userFavoriteLinksId+")}'>Remove</a></b>");

				templateClone.appendTo('.stateDivInnerFav');
			}

			}
			

			for(var k in results){

				if(k==0 && district == true)
					$('.districtDivheading').html('<span class="stateHeadingCls">District</span>');
				
		    if(results[k].favouriteLinkType == "district"){


				$('.districtDivheading').html('<a href="javaScript:{}" class="label label-info stateHeadingCls" style="font-size: 16px;padding: 6px;">District</span>');
				
				var template = $('.favouriteLinkConstituencyClass');

				var templateClone = template.clone();

				templateClone.removeClass('favouriteLinkConstituencyClass');

				templateClone.attr('id',results[k].userFavoriteLinksId);

				templateClone.find('.imageClass').html("<i class='icon-tags'></i>");
				//templateClone.find('.titleClass').html("<b><a  class='problemTitle' href="+results[j].favouriteLink+">"+results[k].favouriteLinkTitle+"</a></b>");
			templateClone.find('.titleClass').html("<b><a  class='problemTitle' title='"+results[k].favouriteLinkTitle+"' href="+results[k].favouriteLink+">"+results[k].name+"</a></b>");


				templateClone.find('.removeClass').html("<b><a class='removeLinkButton btn'  href='javaScript:{removeFavouriteLink("+results[k].userFavoriteLinksId+")}'>Remove</a></b>");

				templateClone.appendTo('.districtDivInnerFav');
			}

			}


			for(var m in results){

				if(m == 0 && specialpage == true)
					$('.specialPageDivheading').html('<span class="specialPageHeadingCls">SpecialPage</span>');
				
		    if(results[m].favouriteLinkType == "specialpage"){


				$('.specialPageDivheading').html('<a href="javaScript:{}" class="label label-info specialPageHeadingCls" style="font-size: 16px; padding: 6px;">SpecialPage</span>');
				
				var template = $('.favouriteLinkConstituencyClass');

				var templateClone = template.clone();

				templateClone.removeClass('favouriteLinkConstituencyClass');

				templateClone.attr('id',results[m].userFavoriteLinksId);

				templateClone.find('.imageClass').html("<i class='icon-tags'></i>");
				//templateClone.find('.titleClass').html("<b><a  class='problemTitle' href="+results[m].favouriteLink+">"+results[m].favouriteLinkTitle+"</a></b>");
				templateClone.find('.titleClass').html("<b><a  title='"+results[m].favouriteLinkTitle+"' class='problemTitle' href="+results[m].favouriteLink+">"+results[m].name+"</a></b>");
				templateClone.find('.removeClass').html("<b><a class='removeLinkButton btn'  title='Remove this link' href='javaScript:{removeFavouriteLink("+results[m].userFavoriteLinksId+")}'>Remove</a></b>");

				templateClone.appendTo('.specialPageDivInnerFav');
			}

		}
	getAllFavLinksOfUser();
}


function getStatesInPs()
	{	
		var jsObj=
		{		
				electionType :"Assembly",		
				task:"getStatesAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getStatesAjaxAction.action?"+rparam;						
		callAjax1(jsObj,url);
	}


function updatedInfo(results){
$("#Inbox").trigger("click");

	return;
}
function getFriendsListForUser(results)
{
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
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
		templateClone.find('.connectedPersonName').html('<a href="userProfile.action?profileId='+results.connectedPeople[i].id+'">'+results.connectedPeople[i].candidateName+'</a>');
		/*if(results.connectedPeople[i].image == null)
			templateClone.find('.imgClass').html('<a onClick="openNewWindow('+results.candidateVO[i].id+');"><img height="50" width="55" src="images/icons/indexPage/human.jpg"/></a>');
		else
			templateClone.find('.imgClass').html('<a  onClick="openNewWindow('+results.candidateVO[i].id+');"><img height="50" width="55" src="'+imageStr+'"/></a>');*/

		
			if(results.connectedPeople[i].image == null)
			templateClone.find('.imgClass').html('<a onClick="openNewWindow('+results.connectedPeople[i].id+');"><img height="50" width="55" src="images/icons/indexPage/human.jpg"/></a>');
		else
			templateClone.find('.imgClass').html('<a  onClick="openNewWindow('+results.connectedPeople[i].id+');"><img height="50" width="55" src="'+imageStr+'"/></a>');
		templateClone.find('.constituencyName').html(''+results.connectedPeople[i].constituencyName+'');
		templateClone.find('.districtName').html(''+results.connectedPeople[i].district+'');
		templateClone.find('.stateName').html(''+results.connectedPeople[i].state+'');
		templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.connectedPeople[i].id+'\',\''+results.connectedPeople[i].candidateName+'\',\'Message\')" rel="tooltip" title="Send Message To '+results.connectedPeople[i].candidateName+'" class="btn btn-mini"><i class="icon-envelope opacityFilter-50"></i></a>');
		templateClone.appendTo(".placeholderCenterDiv");
	}

}

function buildAllSubscriptions(results,place){
   if(results != null && results.length > 0){
	   $(".subscrStreamingMoreCls").css("display","block");
    for(var i in results)
	{
		var template = $(".subscriptionsMainTemplate");
		var templateClone =  template.clone();
		templateClone.removeClass("subscriptionsMainTemplate");
		templateClone.find('.subscriptionsHeaderTitle').html(results[i].headerTitle+'');
		templateClone.find('.subscriptionsDateDiff').html(''+results[i].dateDiff);
		templateClone.find('.subscriptionsDescription').html(''+results[i].description);
		templateClone.find('.subscriptionsFileImgDiv').html("<a href='"+results[i].fileLink+"'><img src='"+results[i].imageUrl+"' style='width:100px;height:100px;vertical-align:middle;' ></img></a>");
		templateClone.find('.activity-title').html("<a href='"+results[i].fileLink+"'>"+results[i].title+"</a>");
		if(place == "end")
		  templateClone.appendTo("#subscriptionsStreamingData");
		else
		  templateClone.prependTo("#subscriptionsStreamingData");		 
	}
  }
  else
  {
	$(subscriptionsStreamingMore).hide();
	$("#headerDiv").html('<b style="color:blue">No Subscriptions Are Avalible Please Select Subscriptions</b>');
  }
}

function callForEveryFiveMins(){
  if(document.getElementById("subscriptionsStreamingMoreDiv").style.display == "block"){
     getSubscriptionDetails("new");
  }
}

function getInitialUpdates(){
  $("#subscriptionsStreamingData").children().remove();
  $(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
  $("#headerDiv").html('');
  getSubscriptionDetails("main");
}
function getSubscriptionDetails(type){
   $("#subscriptionsStreamingMoreDiv").show();
   var task ="";
   if(type == "main")
     task = "allsubscriptions";
   else if(type == "old")
     task = "oldersubscriptions";
   else if(type == "new")
     task = "latestsubscriptions";
   else 
     return;
	 
   var jsObj ={
				task:task,
				type:task
			 };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllSubscriptionsAction.action?"+rparam;					
	callAjax1(jsObj,url);
	$("#subscriptionsStreamingAjaxImg").show();
}

function showAllRequestMessagesForUser(results,jsObj){
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
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
		/*templateClone.find('.connectedPersonName').html('<a style="cursor: pointer;" onClick="openNewWindow('+results.candidateVO[i].id+');">'+results.friendRequest[i].candidateName+'</a>');
		templateClone.find('.imgClass').html('<a  onClick="openNewWindow('+results.candidateVO[i].id+');"><img height="50" width="55" src="images/icons/indexPage/human.jpg"/></a>');*/

		templateClone.find('.connectedPersonName').html('<a style="cursor: pointer;" onClick="openNewWindow('+results.friendRequest[i].id+');">'+results.friendRequest[i].candidateName+'</a>');
		templateClone.find('.imgClass').html('<a  onClick="openNewWindow('+results.friendRequest[i].id+');"><img height="50" width="55" src="images/icons/indexPage/human.jpg"/></a>');
		templateClone.find('.messageCls').html(''+results.friendRequest[i].message+'').css("display","none");
		templateClone.find('.constituencyName').html(''+results.friendRequest[i].constituencyName+'');
		templateClone.find('.districtName').html(''+results.friendRequest[i].district+'');
		templateClone.find('.stateName').html(''+results.friendRequest[i].state+'');
		templateClone.find('.sendMsg').html('<a id="xv"  onclick="acceptRequest('+results.friendRequest[i].id+',\''+results.friendRequest[i].candidateName+'\')" class="acceptButton btn btn-mini" rel="tooltip" title="Confirm"><i  class="icon-thumbs-up opacityFilter-50"></i></a>');
		templateClone.find('.connectCls').html('<a onclick="rejectRequest('+results.friendRequest[i].id+',\''+results.friendRequest[i].candidateName+'\')" class="rejectButton btn btn-mini" rel="tooltip" title="Reject" ><i class="icon-thumbs-down opacityFilter-50"></i></a>');
		templateClone.find('.blockPersonBtn').html('<a onclick="blockRequest('+results.friendRequest[i].id+',\''+results.friendRequest[i].candidateName+'\')" class="rejectButton btn btn-mini" rel="tooltip" title="Block This Person"><i class="icon-ban-circle opacityFilter-50"></i></a>').css("display","block");
		templateClone.appendTo(".placeholderCenterDiv");
	}
}

var inboxCount=0;
function showRequestedMessagesForAUser(results)
{
	inboxCount=results.unreadMsgCount;
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	if(results.resultStatus.resultCode !="0")
	{
		$("#headerDiv").html("Data could not be retrived due to some technical difficulties.");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$("#headerDiv").html('<ul class="nav nav-tabs"><li class="active"><a id="Inbox" style="cursor:pointer">Inbox ( '+inboxCount +' )</a></li><li><a id="SentBox" style="cursor:pointer">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');
		$(".placeholderCenterDiv").html("No messages has been sent to you.");
		return;
	}
		
		$("#headerDiv").html('<ul class="nav nav-tabs"><li class="active"><a id="Inbox" style="cursor:pointer">Inbox ( '+inboxCount +' )</a></li><li><a id="SentBox" style="cursor:pointer">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');
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
				templateClone.find('.imgClass').html('<img height="30" width="30" src="images/icons/indexPage/human.jpg"/>');
			}
			
			
			templateClone.find(".dateAndTimeReceived").html(''+results.candidateVO[i].postedDate+'');
			//templateClone.find(".delete").html('<a data-placement="top" rel="tooltip" href="#" class="btn" style="color:black;" name="Inbox" onclick="deleteMail('+results.candidateVO[i].id+',\'Message\',\'Inbox\','+results.candidateVO[i].costumMessageId+')">Delete</a>');

			templateClone.find(".delete").html('<a data-placement="top" rel="tooltip" data-original-title="remove Message" name="Inbox" onclick="deleteMail('+results.candidateVO[i].id+',\'Message\',\'Inbox\','+results.candidateVO[i].costumMessageId+')"><img class="deleteImg" id="deleteImg_1" src="images/icons/delete.png" alt="deleteImg" width="13" height="13"></a>');
			templateClone.find(".reply").html('<a data-placement="top" rel="tooltip" href="#" data-original-title="Reply To This Message" class="btn" style="color:black;" onclick="showMailPopup('+results.candidateVO[i].id+',\''+results.candidateVO[i].candidateName+'\',\'Message\')"> Reply</a>');
			
			/*templateClone.find(".reply").html('<a href="javascript:{}" onclick="showMailPopup('+results.candidateVO[i].id+',\''+results.candidateVO[i].candidateName+'\',\'Message\')" class="btn" title="reply">Reply</a>');
			
			templateClone.find(".msgDelete").html('<a href="javascript:{}" class="icon-remove"></a>');*/
		templateClone.appendTo(".placeholderCenterDiv");
		}
}
function openNewWindow(id)
{
window.open('userProfile.action?profileId='+id,'_blank');
}
function showSentBoxMessagesForAUser(results)
{
	
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	if(results.resultStatus.resultCode !="0")
	{
		$("#headerDiv").html("Data could not be retrived due to some technical difficulties.");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$("#headerDiv").html('<ul class="nav nav-tabs"><li><a id="Inbox" >Inbox ( '+inboxCount +' )</a></li><li class="active"><a id="SentBox">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');
		$(".placeholderCenterDiv").html("No messages has been sent by you.");
		return;
	}
		
		
		$("#headerDiv").html('<ul class="nav nav-tabs"><li><a id="Inbox" >Inbox ( '+inboxCount +' )</a></li><li class="active"><a id="SentBox">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');
		for(var i in results.candidateVO)
		{
		var template = $(".templateDivMsg");
		var templateClone = template.clone();
		templateClone.removeClass("templateDivMsg");
		templateClone.find(".reply").remove();
		templateClone.find(".messageFrom").html(''+results.candidateVO[i].candidateName+'');
			templateClone.find(".message").html(''+results.candidateVO[i].message+'');
			//templateClone.find('.imgClass').html('<img height="45" width="45" src="images/icons/indexPage/human.jpg"/>');
			//templateClone.find(".delete").html('<a data-placement="top" rel="tooltip" href="#" name ="sentBox" class="btn" style="color:black;" onclick="deleteMail('+results.candidateVO[i].id+',\'Message\',\'sentBox\','+results.candidateVO[i].costumMessageId+')">Delete</a>');
			templateClone.find(".delete").html('<a data-placement="top" rel="tooltip" data-original-title="remove Message" name="sentBox" onclick="deleteMail('+results.candidateVO[i].id+',\'Message\',\'sentBox\','+results.candidateVO[i].costumMessageId+')"><img class="deleteImg" id="deleteImg_1" src="images/icons/delete.png" alt="deleteImg" width="13" height="13"></a>');
			templateClone.find(".dateAndTimeReceived").html(''+results.candidateVO[i].postedDate+'');
			if(results.candidateVO[i].profileImg!=""){
			var imageStr = "pictures/profiles/"+results.candidateVO[i].profileImg;
			templateClone.find('.imgClass').html('<img height="30" width="30" src='+imageStr+'></img>');
			}
			else{
				templateClone.find('.imgClass').html('<img height="30" width="30" src="images/icons/indexPage/human.jpg"/>');
			}
			
		templateClone.appendTo(".placeholderCenterDiv");
		}
}

function deleteMail(senderId,type,btnName,customMessageId)
{
if (confirm("Are you sure you wato to Delete"))
	{
var jsObj ={
				loginUserId:loginUserId,
				senderId:senderId,
				type:type,
				customMessageId:customMessageId,
				btnName:btnName,
				task:"deleteMessageFromInbox"
			 };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteMessageFromInboxAction.action?"+rparam;					
	callAjax1(jsObj,url);
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
			height: 180,
			//minHeight:50,
			modal: true,
			hide: "explode"
		});
		
		var div = $("<div class='connectPeoplePopupInnerDiv'></div>");
		var errorDiv = $("<div id='ErrorMsgDivId'></div>");
		var label = $("<label class='messageLabel'></label>");
		
		var button = $("<div id='remainChars'><span id='maxcount'>300 </span> <span>chars remaining..</span>&nbsp;&nbsp;<span>Should not exceed 300 chars</span></div><input class='btn-info btn-small' id='sendMessageButtonId' type='button' value='send' onclick='sendMessageToConnectedUser("+id+",\""+type+"\")'/>");
		//var textarea = $("<textarea id='connectMessageText' placeholder='Enter Your Message Here..' onkeyup='limitText('connectMessageText','maxcount',200)'></textarea><br>");
		var textarea = $("<textarea id='connectMessageText' placeholder='Enter Your Message Here..' ></textarea><br>");
		div.append(errorDiv);
		div.append(label);
		div.append(textarea);
		div.append(button);
		$('#allConnectedUsersDisplay_main').append(div);

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
		
	/*$("#ErrorMsgDivId").html('<img src="images/icons/search.gif" class="searchImgCls"/>');*/
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
		$("#ErrorMsgDivId").html('<font color="green">Message Sent Successfully..</font>');
		//setTimeout('self.close();',2000);
		setTimeout("$('#connectPeoplePopup').dialog('close')",800);
		
	}
	else
	  $("#ErrorMsgDivId").html('<font color="red">Message Cannot Be sent Due To Some Technical Difficulties<font>');
	
}

function blockRequest(requestId,requestName)
{
	var jsObj=
	{		
			type:"block",
			recepientId:requestId,
			task:"blockRequest"								
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;		
	alert(requestName+" request is blocked.");
	callAjax1(jsObj,url);
}

function acceptRequest(requestId,requestName)
{
	var jsObj=
	{		
			type:"accept",
			recepientId:requestId,
			task:"acceptRequest"								
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;		
	alert(requestName+" request is successfully accepted.");
	callAjax1(jsObj,url);
}

function rejectRequest(requestId,requestName)
{
	var jsObj=
	{		
			type:"reject",
			recepientId:requestId,
			task:"rejectRequest"			
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;	
	alert(requestName+" request is rejected.");
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
	clearAllFavoriteLinkDivs();
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
	clearAllFavoriteLinkDivs();
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
		str +='<div style="padding:4px;width:100%;"><label   style="width:40%;float:left;">By District : </label>';
		str +='<select id="connectDistrictSelect" onchange="getDetailsForallConstituencies(this.value);getAllConnectedUsersByDistrictWiseFilterView(\''+jsObj.locationType+'\',\''+jsObj.userId+'\')">';
		str += '</select></div>';
		str +='<div style="padding:4px;width:100%;"><label    style="width:40%;float:left;">By Constituency : </label>';
		str +='<select id="connectConstituencySelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\',\''+jsObj.userId+'\')">';
		str += '</select></div>';
	str +='<div style="padding:4px;width:100%;"><label style="width:40%;float:left;">By Status :</label>';
	str +='<select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\',\''+jsObj.userId+'\')">';
	str +='<option value="0">All</option>';
	for(var i in connectStatus)
	str +='<option id='+connectStatus[i].id+'>'+connectStatus[i].name+'</option>';
	str +='</select></div>';
	filterDiv.append(str);
	$("#headerDiv").append(filterDiv);
	if(flag)
	{
	 for(var i in results.candidateVO)
	{
		if(results.candidateVO[i].status != null && results.candidateVO[i].status != "LOGGED_USER")
		{
		var imageStr = "pictures/profiles/"+results.candidateVO[i].image;
		var image = results.candidateVO[i].image;
		var template = $(".templateDiv");
		var templateClone = template.clone();
		templateClone.removeClass("templateDiv");
		
		if(image == null)
			templateClone.find(".imgClass").html('<a href="javascript:{}" onClick="openNewWindow('+results.candidateVO[i].id+');"><img width="50" height="45" src="images/icons/indexPage/human.jpg"></img></a>');
		else
			templateClone.find(".imgClass").html('<a href="javascript:{}"" onClick="openNewWindow('+results.candidateVO[i].id+');"><img height="45" width="50" src="'+imageStr+'" onerror="setDefaultImage(this);"/></a>');
			templateClone.find(".connectedPersonName").html('<a style="cursor: pointer;" onClick="openNewWindow('+results.candidateVO[i].id+');">'+results.candidateVO[i].candidateName+'</a>');
			templateClone.find(".constituencyName").html(''+results.candidateVO[i].constituencyName.toLowerCase()+'');
			templateClone.find('.stateName').html(''+results.candidateVO[i].state+'');
			templateClone.find('.districtName').html(''+results.candidateVO[i].district+'');
			if(results.candidateVO[i].status != null && results.candidateVO[i].status == "NOT CONNECTED")
				templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\');" rel="tooltip" title="Connect" class="btn btn-mini"><i class="icon-plus-sign opacityFilter-50"></i></a>');
			else if(results.candidateVO[i].status != null && results.candidateVO[i].status == "DISCONNECTED")
				templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\');" rel="tooltip" title="Connect" class="btn btn-mini"><i class="icon-plus-sign opacityFilter-50"></i></a>');
			else if(results.candidateVO[i].status != null && results.candidateVO[i].status == "PENDING")
				templateClone.find('.connectCls').html('<a rel="tooltip"  href="javascript:{}" title="Pending" class="btn btn-mini" ><i class="icon-adjust opacityFilter-50"></i></a>');
			
			templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\',\'Message\')" rel="tooltip" class="btn btn-mini" title="Send Message To '+results.candidateVO[i].candidateName+'"><i class="icon-envelope opacityFilter-50"></i></a>');
			
			templateClone.appendTo(".placeholderCenterDiv");
		}	
	}

	var pagination = $('<div class="custom_paginator_class" style="clear: both; margin-top: 0px; padding-top: 10px;"></div>');
	pagination.appendTo('.placeholderCenterDiv');
	}
	flag1=false;
}

function getAllCconnectedUserDetails(){
	var locationId = districtId;	
	var locationType= "DISTRICT";
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
	$("#impdatesDiv").hide();
	$("#announcementsDiv").hide();
	
}
function selectedStatusValue(){
	var connectedStatus = $('#connectStatusSelect option:selected').val();
	if(connectedStatus == 'ALL'){
    connectedStatus = 'ALL';
	}
	if(connectedStatus == 'CONNECTED'){
    connectedStatus = 'CONNECTED';
	}
	else if(connectedStatus == 'PENDING'){
    connectedStatus = 'PENDING';
	}
	else if(connectedStatus == 'NOT CONNECTED'){
    connectedStatus = 'NOT CONNECTED';
	}
	return connectedStatus;
}
 function showAllConnectedUsersInPanelByFilterView(jsObj,results)
{

	var connectedStatuss=selectedStatusValue();
	var users = results.candidateVO;
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	
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
		if(results.candidateVO[i].status != null && results.candidateVO[i].status != "LOGGED_USER"){		
				
			var template = $(".templateDiv");
			var imageStr = "pictures/profiles/"+results.candidateVO[i].image;
			var image = results.candidateVO[i].image;
			var templateClone = template.clone();
			templateClone.removeClass("templateDiv");
			templateClone.find(".connectedPersonName").html('<a style="cursor: pointer;" onClick="openNewWindow('+results.candidateVO[i].id+');">'+results.candidateVO[i].candidateName+'</a>');
			templateClone.find(".constituencyName").html(''+results.candidateVO[i].constituencyName.toLowerCase()+'');
			templateClone.find(".districtName").html(''+results.candidateVO[i].district+'');
			templateClone.find('.stateName').html(''+results.candidateVO[i].state+'');

			if(image == null)
				templateClone.find(".imgClass").html('<a  onClick="openNewWindow('+results.candidateVO[i].id+');"><img width="50" height="45" src="images/icons/indexPage/human.jpg"></img></a>');
			else
				templateClone.find(".imgClass").html('<a  onClick="openNewWindow('+results.candidateVO[i].id+');"><img height="45" width="50" src="'+imageStr+'" /></a>');
			
			if(connectedStatuss == "NOT CONNECTED" ){		
				if(results.candidateVO[i].status != null && (results.candidateVO[i].status == "DISCONNECTED" || results.candidateVO[i].status == "NOT CONNECTED")){	
				templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\')" class="btn btn-mini"><i class="icon-plus-sign opacityFilter-50"></i></a>');			
				}				
			}	
			if(connectedStatuss == "PENDING" ){
				if(results.candidateVO[i].status != null && results.candidateVO[i].status == "PENDING"){
					templateClone.find('.connectCls').html('<a rel="tooltip"  href="javascript:{}" title="Pending" class="btn btn-mini" ><i class="icon-adjust opacityFilter-50"></i></a>');
				}
			}
			if(connectedStatuss == '0' ){			
			if(results.candidateVO[i].status != null && results.candidateVO[i].status == "NOT CONNECTED"){
				templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\')" class="btn btn-mini"><i class="icon-plus-sign opacityFilter-50"></i></a>')			
				}
				if(results.candidateVO[i].status != null && results.candidateVO[i].status == "DISCONNECTED"){
				templateClone.find('.connectCls').html('<a href="javascript:{}" onclick="connectToSelectedPerson(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\')" class="btn btn-mini"><i class="icon-plus-sign opacityFilter-50"></i></a>')			
				}
				if(results.candidateVO[i].status != null && results.candidateVO[i].status == "PENDING"){
				templateClone.find('.connectCls').html('<a rel="tooltip"  href="javascript:{}" title="Pending" class="btn btn-mini" ><i class="icon-adjust opacityFilter-50"></i></a>');			
				}
				
			}
			templateClone.find('.sendMsg').html('<a href="javascript:{}" onclick="showMailPopup(\''+results.candidateVO[i].id+'\',\''+results.candidateVO[i].candidateName+'\',\'Message\')" title="Send Message To '+results.candidateVO[i].candidateName+'" rel="tooltip" class="btn btn-mini"><i class="icon-envelope opacityFilter-50"></i></a>');	

			templateClone.appendTo(".placeholderCenterDiv");						
		}
	}
		 var pagination = $('<div class="custom_paginator_class" style="clear: both; margin-top: 0px; padding-top: 10px;"></div>');

		pagination.appendTo('.placeholderCenterDiv');
	}
	flag1=false;
}

function getAllConnectedUsersByFilterView(locationType,userId)
{
		var connectConstiSelectElmtValue = '';
		var connectConstiArray = new Array();	
		var allConstituencies=$('#connectConstituencySelect').val();
		var allDistricts = $('#connectDistrictSelect').val();
		
		if( allDistricts != 0 && allConstituencies == '0'){
		getAllConnectedDistrictWise1UsersByFilterView(locationType,userId);
		return false;
		}		
		if(allConstituencies == '0'){
			getAllConnectedUsersByDistrictWiseFilterView(locationType,userId);
			return false;
		}
		if(locationType == "DISTRICT")
		{
			var connectConstiSelectElmt = document.getElementById("connectConstituencySelect");
			connectedStatus=connectConstiSelectElmt;
			if(connectConstiSelectElmt)
				connectConstiSelectElmtValue = connectConstiSelectElmt.options[connectConstiSelectElmt.selectedIndex].value					
			if(connectConstiSelectElmtValue == 0)
			{
			for(var i in constituencies)
				connectConstiArray.push(constituencies[i].id);		
			}
			else{
				connectConstiArray.push(connectConstiSelectElmtValue);
				}
		}
		else
		{	
			connectConstiArray.push(allConstituencies);
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

//total problems

function showAllPostedProblems(jsObj,results)
{
	var i=0;
	$('.viewMoreDiv').html('');
		
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
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
	for( i in problemsData)
	{
	
		var imageStr = "pictures/profiles/"+problemsData[i].userImageURL;
		var template = $('.problemTemplateDiv');
		var templateClone = template.clone();
		templateClone.removeClass('problemTemplateDiv');
		templateClone.find('.problemReportedDate').html('Posted On: '+problemsData[i].identifiedDate+'');
		templateClone.find('.problemImg').html('<a href="userProfile.action?profileId='+problemsData[i].userId+'"><img height="40" width="40"  class="thumbnail" src='+imageStr+'></img></a>');
		templateClone.find('.postedPersonName').html('<h5 style="color:#273241">'+problemsData[i].firstName+ ' Posted</h5>');
		templateClone.find('.problemTitle').html('<a href="completeProblemDetailsAction.action?problemId='+problemsData[i].problemID+'">'+problemsData[i].definition+'</a>');
		templateClone.find('.problemDescription').html(''+problemsData[i].description+'');
		templateClone.find('.problemFromDate').html('<span>Existing From: </span>'+problemsData[i].existingFrom+'');
		templateClone.find('.location').html('Location: '+problemsData[i].location+'')
		templateClone.find('.commentCls').html('<a href="completeProblemDetailsAction.action?problemId='+problemsData[i].problemID+'">Comment</a>');
		templateClone.find('.shareCls').html('<a href="completeProblemDetailsAction.action?problemId='+problemsData[i].problemID+'">Share</a>');
		//if(problemsData[i].rating != null)
			//templateClone.find('.problemRating').html('<div class="star pull-right"></div><input type="hidden" style="display:none;" value="'+problemsData[i].rating +'" >');
		templateClone.appendTo('.placeholderCenterDiv');
	}
	var totalCount = results.totalResultsCount;
	var startIndex = jsObj.startIndex;
	var maxIndex   = jsObj.maxIndex;
	var count      = startIndex + maxIndex;
	var value      = totalCount - count;
	if(value > 0)
	{
		var viewMore = $('<div class="viewMoreDiv"><span class="problemsViewMoreLink btn">View More</span><span class="ajaxImg"><img src="images/icons/search.gif"/></span><input type="hidden" value="'+jsObj.type+'" class="problemViewMoreTypeVar"/></div>');
		viewMore.appendTo('.placeholderCenterDiv');
	}
	
}
//changed for alignment
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
			minHeight:235,
			modal: true,
			hide: "explode"
		});
		
		var div = $("<div class='connectPeoplePopupInnerDiv'></div>");
		/*var Name=$("<label>"+userName+"</label>");
		var message = $("<label class='messageLabel'>Message</label>");*/
		var textArea = $("<textarea id='connectUserMsg' placeholder='Enter Your Message Here..' style='width: 354px; height: 74px;'></textarea>");
		var image = $('<img height="100" width="95" style="margin-bottom: 11px;" src="images/icons/indexPage/human.jpg">');
		var connectBtn = $('<input type="button" class="btn" name="connectDistrictPeopleLink" style="float: right" value="Connect" id="connectDistrictPeopleLink"/>');
		var connectedPersonId = $('<input type="hidden" value='+userId+' id="connectedPersonId"/>');
		var errorDiv = $("<div id='errorMsgDiv'></div>")
		div.append(errorDiv);
		/*div.append(Name);
		div.append(message);*/
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
	//$(".districtPeopleLink").trigger("click");
	getAllConnectedUsersByFilterView();


if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{		
		var msga = $('<font color="green" style="font-weight:bold;"> Request sent to selected users successfully.');
		if(jsObj.locationType=="DISTRICT"){
		getAllConnectedUsersByFilterView();
			//$("#districtPeopleLink").trigger("click");
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


function showAllPostedReasonsForUserProfile(jsObj,results)
{
	var i=0;	
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	var data = results.candidateComments;
	$('.viewMoreDiv').html('');
	if(data == null && jsObj.linkType == "assessPoliticianLink")
	{
		$(".templateDivMsg").html('<div>No comments.</div>').appendTo(".placeholderCenterDiv");;
			return;
	}
	if(data == null && jsObj.linkType == "PoliticalReaViewMoreLink")
	{
		$(".viewMoreDiv").css("display","none");
			return;
	}
	if(jsObj.linkType == "assessPoliticianLink"){
		$(".placeholderCenterDiv").children().remove();
	  	}

	for(i in data)
	{
		var status;
		var imageStr = "images/candidates/"+data[i].candidate;
		var postedByImg="pictures/profiles/"+data[i].imgURL;
		
		var template = $('.politicalReasonsTemplate');
		var templateClone = template.clone();
		templateClone.removeClass('politicalReasonsTemplate');
		
		if(data[i].rank == 1)
			status = "winning";
		else
			status = "losing";

		templateClone.find('.headingCls').html('<h5>'+data[i].commentedBy+'</h5> Posted a Political Reason for '+data[i].candidate+' '+status+' '+data[i].constituencyName+' '+data[i].electionType+' constituency');
		templateClone.find('.candidateImg').html('<img src="'+imageStr+'.jpg" onerror="setDefaultImage(this)" style="width:100px;height:100px;vertical-align:middle;"></img>');
		templateClone.find('.politicalReaCls').html('<b>Political Reason:</b> '+data[i].commentCategory+'');
		
		if(data[i].imgURL == null)
			templateClone.find('.postedBy').html('<a href="userProfile.action?profileId='+data[i].userId+'"><img height="50" width="55" src="images/icons/indexPage/member.jpg"/></a>');
		else
			templateClone.find('.postedBy').html('<a href="userProfile.action?profileId='+data[i].userId+'"><img height="30" width="30"  class="thumbnail" src="'+postedByImg+'"></img></a>');
			
		
		templateClone.find('.politicalDescCls').html('<b>Description:</b> '+data[i].commentDesc+'');
		templateClone.find('.polReaPostedDate').html('Posted On: '+data[i].commentedOn+'');
		/*templateClone.find('.polReaPostedPerName').html('Posted By: '+data[i].commentedBy+'');*/
		templateClone.appendTo('.placeholderCenterDiv');
	}
	var totalCount = results.totalResultsCount;
	var startIndex = jsObj.startIndex ;
	var maxIndex = jsObj.maxIndex;
	var count = startIndex + maxIndex;
	var value = totalCount - count;
	if(value > 0)
	{
	var viewMore = $('<div class="viewMoreDiv"><span class="PoliticalReaViewMoreLink btn">View More</span><input type="hidden" value="'+jsObj.type+'" class="politicalReasonViewMoreTypeVar"/></div>');
	viewMore.appendTo('.placeholderCenterDiv');
	}
}
function setDefaultImage(img)
{
		img.src = "images/candidates/human.jpg";
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
	var ul = $('<div class="prblmsHeader"></div>');
	if(userType != "PartyAnalyst")
	{
			
      if(totalPostedReasonsCount ==0)
		ul.append('<span><h4>Total Political reasons posted - '+totalPostedReasonsCount+'</h4></span>');
	   else
		ul.append('<span><h4>Total Political reasons posted - <a href="javascript:{}" class="assessPoliticianLink">'+totalPostedReasonsCount+'</a><input type="hidden" value="Total" class="politicalReasTypeVar" /></h4></span>');
	   if(postedReasonsByLoggedInUser ==0)
			 ul.append('<span class="APSpan">By You - '+postedReasonsByLoggedInUser+'</span>');
	   else
   		 ul.append('<span class="APSpan">By You - <a href="javascript:{}" class="assessPoliticianLink">'+postedReasonsByLoggedInUser+'</a><input type="hidden" value="LOGGED_USER" class="politicalReasTypeVar" /></span>');
	   
	   if(connectedUsersReasCount ==0)
		 ul.append('<span class="APSpan">By Friends - '+connectedUsersReasCount+'</span>');
	   else
		ul.append('<span class="APSpan">By Friends - <a href="javascript:{}" class="assessPoliticianLink">'+connectedUsersReasCount+'</a><input type="hidden" value="ConnectedUserPoliticalReasons" class="politicalReasTypeVar" /></span>');	

	   if(postedReasonsCountByOtherUsers ==0)
		 ul.append('<span class="APSpan">By Others - '+postedReasonsCountByOtherUsers+'</span>');
	   else
		ul.append('<span class="APSpan">By Others - <a href="javascript:{}" class="assessPoliticianLink">'+postedReasonsCountByOtherUsers+'</a><input type="hidden" value="OtherUsers" class="politicalReasTypeVar" /></span>');	
	}
	else
	{
		ul.append('<span > Total Political reasons posted - <a href="javascript:{}" class="assessPoliticianLink">'+totalPostedReasonsCount+'</a><input type="hidden" value="Total" class="politicalReasTypeVar" /></span>');
		ul.append('<span class="APSpan">By You - <a href="javascript:{}" class="assessPoliticianLink">'+postedReasonsByLoggedInUser+'</a><input type="hidden" value="LOGGED_USER" class="politicalReasTypeVar" /></span>');
		ul.append('<span class="APSpan"> By Friends - <a href="javascript:{}" class="assessPoliticianLink">'+connectedUsersReasCount+'</a><input type="hidden" value="ConnectedUserPoliticalReasons" class="politicalReasTypeVar" /></span>');	
		ul.append('<span class="APSpan"> By Others - <a href="javascript:{}" class="assessPoliticianLink">'+postedReasonsCountByOtherUsers+'</a><input type="hidden" value="OtherUsers" class="politicalReasTypeVar" /></span>');	
	}
		
	var label = $('<span><h4> Reasons Status Details Posted By You </h4></span>');
	
	var ulinner = $('<div class="prblmsHeader"></div>');
	if(approvedReasonsCount == 0)
	 ulinner.append('<span class="APSpan"> Approved - '+approvedReasonsCount+'</span>');
	else
	ulinner.append('<span class="APSpan"> Approved - <a class="reasonsCountAnc assessPoliticianLink" href="javascript:{}">'+approvedReasonsCount+'</a> <input type="hidden" value="Approved" class="politicalReasTypeVar" /></span>');
	
	if(rejectedReasonsCount ==0)
		ulinner.append('<span class="APSpan">Rejected - '+rejectedReasonsCount+'</span>');
	else
		ulinner.append('<span class="APSpan">Rejected - <a class="reasonsCountAnc assessPoliticianLink" href="javascript:{}">'+rejectedReasonsCount+'</a> <input type="hidden" value="rejected" class="politicalReasTypeVar" /></span>');

	if(userType != "PartyAnalyst"){		
		ulinner.append('<h5 style="clear:both;float:left;margin-right:14px;" ><a href="javascript:{}" onclick="openAddReasonWindow(\'analyze\')" class="btn btn-success btn-small ">Add Reasons</a></h5>');
		ulinner.append('<h5 style="float:left;"><a href="javascript:{}" onclick="openAddReasonWindow(\'viewResults\')" class="btn btn-success btn-small" >View Reasons</a></h5>');
	}

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
	//div.append(btns);
	$('#userSpecialPageSubscriptionsDiv').append(div);
	for(var i in specialPages)
	{
		if(specialPages[i].subscribed)
		{
		  var template = $(".specialPagSubscrTemplDiv");
		  var templateClone = template.clone();
		  templateClone.removeClass('specialPagSubscrTemplDiv');
		  templateClone.find('.titleCls').html(''+specialPages[i].specialPageVO.title+'');
		  templateClone.find('.imgClass').html('<a href="specialPageAction.action?specialPageId='+specialPages[i].specialPageVO.specialPageId+'"><img height="100" width="95" src="'+specialPages[i].specialPageVO.eventImagePath+'"/></a>');
		  templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink" >UNSUBSCRIBE</a>');
		  templateClone.find('.hiddenVar').html('<input type="hidden" value="'+specialPages[i].specialPageVO.specialPageId+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="specialPage"/>');
		  templateClone.appendTo('#userSpecialPageSubscriptionsDiv');
		}
		else
		{
		  var template = $(".specialPagSubscrTemplDiv");
		  var templateClone = template.clone();
		  templateClone.removeClass('specialPagSubscrTemplDiv');
		  templateClone.find('.titleCls').html(''+specialPages[i].specialPageVO.title+'');
		  templateClone.find('.imgClass').html('<a href="specialPageAction.action?specialPageId='+specialPages[i].specialPageVO.specialPageId+'"><img height="100" width="95" src="'+specialPages[i].specialPageVO.eventImagePath+'"/></a>');
		  templateClone.find('.btnClass').html('<a href="javascript:{}" class="subscribedLink"  style="color:red; font-weight:bold;">SUBSCRIBE</a>');
		  templateClone.find('.hiddenVar').html('<input type="hidden" value="'+specialPages[i].specialPageVO.specialPageId+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="specialPage"/>');
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
	//div.append(btns);
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
	//div.append(btns);
	$('#userConstituencySubscriptionsDiv').append(div);
	for(var i in constituencies)
	{
		var template = $(".specialPagSubscrTemplDiv");
		var templateClone = template.clone();
		templateClone.removeClass('specialPagSubscrTemplDiv');
		templateClone.find('.titleCls').html('<a href="constituencyPageAction.action?constituencyId='+constituencies[i].id+'">'+constituencies[i].name+'</a>');
		templateClone.find('.btnClass').html('<a href="javascript:{}" class="constituencyunSubscrBtn">UNSUBSCRIBE</a>');
		templateClone.find('.hiddenVar').html('<input type="hidden" value="'+constituencies[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="constituencyPage"/>');
		templateClone.appendTo('#userConstituencySubscriptionsDiv');
	}
}


function showAllUserPartySubscriptions(jsObj,results)
{
	
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	$('#userPartySubscriptionsDiv').children().remove();
	$('#userPartySubscriptionsDiv').html('');
	$('#userPartyUnSubscriptionsDiv').children().remove();
	$('#userPartyUnSubscriptionsDiv').html('');

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
	//div.append(btns);
	$('#userPartySubscriptionsDiv').append(div);
	for(var i in partySubscriptions)
	{
		
		if(partySubscriptions[i].subscribed)
		{
			var template = $(".specialPagSubscrTemplDiv");
			var templateClone = template.clone();
			templateClone.removeClass('specialPagSubscrTemplDiv');
			templateClone.find('.titleCls').html('<br>'+partySubscriptions[i].name+'');
			templateClone.find('.imgClass').html('<a href="partyPageAction.action?partyId='+partySubscriptions[i].id+'"><img height="100" width="95" src="images/party_flags/'+partySubscriptions[i].imageURL+'.png"/></a>');
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="unSubscribedLink" >UNSUBSCRIBE</a>');
			templateClone.find('.hiddenVar').html('<input type="hidden" value="'+partySubscriptions[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="partyPage"/>');
			templateClone.appendTo('#userPartySubscriptionsDiv');
		}
		else
		{
			
			var template = $(".specialPagSubscrTemplDiv");
			var templateClone = template.clone();
			templateClone.removeClass('specialPagSubscrTemplDiv');
			templateClone.find('.titleCls').html('<br>'+partySubscriptions[i].name+'');
			templateClone.find('.imgClass').html('<a href="partyPageAction.action?partyId='+partySubscriptions[i].id+'"><img height="100" width="95" src="images/party_flags/'+partySubscriptions[i].imageURL+'.png"/></a>');
			templateClone.find('.btnClass').html('<a href="javascript:{}" class="subscribedLink">SUBSCRIBE</a>');
			templateClone.find('.hiddenVar').html('<input type="hidden" value="'+partySubscriptions[i].id+'" class="hiddenVarId" /><input type="hidden" class="subscripType" value="partyPage"/>');
			templateClone.appendTo('#userPartyUnSubscriptionsDiv');

			
		}
	}
}

function clearAllSubscriptionDivs()
{
    $("#subscriptionsStreamingMoreDiv").hide();
	$("#userSpecialPageSubscriptionsDiv").html('');
	$("#userSpecialPageUnSubscriptionsDiv").html('');
	$("#userCandidateSubscriptionsDiv").html('');
	$("#userPartySubscriptionsDiv").html('');
	$("#userConstituencySubscriptionsDiv").html('');
    $("#subscriptionsStreamingData").html('');
	$("#userSpecialPageSubscriptionsDiv").children().remove();
	$("#userSpecialPageUnSubscriptionsDiv").children().remove();
	$("#userCandidateSubscriptionsDiv").children().remove();
	$("#userPartySubscriptionsDiv").children().remove();
	$("#userConstituencySubscriptionsDiv").children().remove();
	$("#userPartyUnSubscriptionsDiv").children().remove();
	$("#userPartyUnSubscriptionsDiv").html('');
	$("#caderInfo").html('');
	$("#caderInfo").children().remove();
	$("#impEvents").html('');
	$("#impEvents").children().remove();
	//$("#announcementsDiv").html('');
	//$("#announcementsDiv").children().remove();

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
	$('#headerDiv').html('');
	var div = $('<div style="line-height:1.5em;"></div>');
	var ul = $('<div class="prblmsHeader"></div>');
	if(results.totalPostedProblemsCount == 0)
		ul.append('<li class="fontStyle"><h4>Total posted problems - '+results.totalPostedProblemsCount+'</h4></li>');
	else
	  ul.append('<li class="fontStyle"><h4>Total posted problems - <a  href="javascript:{}" class="problemsLink">'+results.totalPostedProblemsCount+'</a><input type="hidden" value="Total" class="problemTypeVariable"/></h4></li>');
		
	if(results.postedProblemsCountByLoggedInUsers == 0)
		ul.append('<span class="APSpan">By You - '+results.postedProblemsCountByLoggedInUsers+'</span>'); 
	else
	  ul.append('<span class="APSpan">By You - <a href="javascript:{}" class="problemsLink">'+results.postedProblemsCountByLoggedInUsers+'</a><input type="hidden" value="LOGGED_USER" class="problemTypeVariable"/></span>');
		
	if(results.postedProblemsCountByOtherUsers == 0)
		ul.append('<span class="APSpan">By Others - '+results.postedProblemsCountByOtherUsers+'</span>');
	else
	  ul.append('<span class="APSpan">By Others - <a href="javascript:{}" class="problemsLink">'+results.postedProblemsCountByOtherUsers+'</a><input type="hidden" value="OtherUsers" class="problemTypeVariable"/></span>');

	if(results.postedProblemsCountByConnectedUsers == 0)
		ul.append('<span class="APSpan">By Friends - '+results.postedProblemsCountByConnectedUsers+'</span>');
	else 
		ul.append('<span class="APSpan">By Friends - <a href="javascript:{}" class="problemsLink">'+results.postedProblemsCountByConnectedUsers+'</a><input type="hidden" value="ConnectedUserProblems" class="problemTypeVariable"/></span>');
	
	var label = $('<span><h4 style="color:black;clear:both;">Problem Status Details Posted By You </h4></span>');
	var ulInner = $('<div class="prblmsHeader"></div>');

	if(results.approvedProblemsCount ==0)
		ulInner.append('<span class="APSpan">Approved	- '+results.approvedProblemsCount+'</span>');
	else
	 ulInner.append('<span class="APSpan">Approved - <a href="javascript:{}" class="problemsLink">'+results.approvedProblemsCount+'</a><input type="hidden" value="approved" class="problemTypeVariable"/></span>');
	if(results.rejectedProblemsCount ==0)
		ulInner.append('<span class="APSpan">Rejected - '+results.rejectedProblemsCount+'</span>');
	else
	 ulInner.append('<span class="APSpan">Rejected - <a href="javascript:{}" class="problemsLink">'+results.rejectedProblemsCount+'</a><input type="hidden" value="rejected" class="problemTypeVariable"/></span> ');

	if(userType != "PartyAnalyst")
		ulInner.append('<h5 style="clear:both;"><a href="javascript:{}" class="postProblemLink btn btn-success btn-small">Post Problem</a></h5>');

	 div.append(ul);
	 div.append(label);
	div.append(ulInner);
	$('#headerDiv').append(div);
}

function openAddReasonWindow(taskType)
{
	var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=ANALYZECONSTITUENCYPOPUP&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstId+"&parliamentConstiName="+parliamentConstName+"&constituencyName="+constituencyName+"&userId="+loginUserId+"&taskType="+taskType,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");				 
	browser1.focus();
}

function removeFavouriteLink(id){

 var r=confirm("Are you sure to remove the Favourite link");
 if (r==true)
  {
  $('#'+id).hide();
  var jsObj ={
		    linkId:id,
			task:"removeFavouriteLink"
	};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "removeFavouriteLinkAction.action?"+rparam;						
		callAjax1(jsObj,url);
}
 else
  {
	$('#'+id).show();
  }
}


function createCropImage(imgpreview,imgtarget,event)
{     
      //alert(event.target.result); 
	  
   var a=document.getElementById("Imgpreview").src = event.target.result;

   if(jcrop_api){
   
   	
	
	
    jcrop_api.destroy();
	 $('#Imgpreview1').removeAttr('style'); 
	document.getElementById("Imgpreview1").src = event.target.result;
  
  

}
else{
 document.getElementById("Imgpreview1").src = event.target.result;
  $('#xcoardinate').val("0");
  $('#ycoardinate').val("0");
   $('#width').val("300");
   $('#height').val("300");
   
}
    
    // document.getElementById("Imgpreview1").src = event.target.result;
      // Create variables (in this scope) to hold the API and image size
      var  boundx, boundy;
      
      $('#Imgpreview1').Jcrop({
        onChange: updatePreview,
        onSelect: updatePreview,
		minSize:[100,100],
        aspectRatio: 1
      },function(){
        // Use the API to get the real image size
        
		var bounds = this.getBounds();
        boundx = bounds[0];
        boundy = bounds[1];
        // Store the API in the jcrop_api variable
        jcrop_api = this;
		jcrop_api.animateTo([100,100,200,300]);
		 
		
      });

      function updatePreview(c)
      {
  
	 
        if (parseInt(c.w) > 0)
        {
			   $('#xcoardinate').val( Math.round(c.x));
      $('#ycoardinate').val( Math.round(c.y));
      
      $('#width').val( Math.round(c.w));
      $('#height').val( Math.round(c.h));
          var rx = 100 / c.w;
          var ry = 100 / c.h;

          $('#Imgpreview').css({
            width: Math.round(rx * boundx) + 'px',
            height: Math.round(ry * boundy) + 'px',
            marginLeft: '-' + Math.round(rx * c.x) + 'px',
            marginTop: '-' + Math.round(ry * c.y) + 'px'
          });
        }
      };
	 
}

function clearAllFavoriteLinkDivs()
{
	$(".constituencyDivheading").children().remove();
	$(".constituencyDivInnerFav").children().remove();
	$(".stateDivheading").children().remove();
	$(".stateDivInnerFav").children().remove();
	$(".districtDivheading").children().remove();
	$(".districtDivInnerFav").children().remove();
	$(".specialPageDivheading").children().remove();
	$(".specialPageDivInnerFav").children().remove();
}

function getPeopleYouMayKnowDetails()
{
	var jsObj ={
			task:"getFriendsList"
	};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getFriendsListAction.action?"+rparam;
		callAjax1(jsObj,url);
		
}

function buildPeopleYouMayKnowBlock(results)
{
	
	$(".peopleYouMayKnowULClass").children().remove();
	$(".peopleYouMayKnowInnerDiv").html('');

	var friendsList = results.peopleYouMayKnow;

	if(friendsList == null || friendsList.length == 0)
	{
		$(".peopleYouMayKnowInnerDiv").html('Right now there are no friend suggestion for you.	We will get back with more suggesstions as soon as possible..');
		return;
	}

	for(var i=0;i<3;i++)
	{
		var template = $(".connectPeopleTemplateDiv");
		var templateClone = template.clone();
		templateClone.removeClass("connectPeopleTemplateDiv");
		if(friendsList[i].image == null || friendsList[i].image == '')
			templateClone.find(".imageDIv").html('<a href="userProfile.action?profileId='+friendsList[i].id+'"><img height="50" width="55" src="pictures/profiles/human.jpg" /></a>');
		else
			templateClone.find(".imageDIv").html('<a href="userProfile.action?profileId='+friendsList[i].id+'"><img height="50" width="55" src="pictures/profiles/'+friendsList[i].image+'" /></a>');
		
		templateClone.find(".nameCls").html('<a href="userProfile.action?profileId='+friendsList[i].id+'">'+friendsList[i].candidateName+'</a>');
		templateClone.find(".constituencyNameCls").html(''+friendsList[i].constituencyName.toLowerCase()+'');
		templateClone.find(".sendMsgClass").html('<a rel="tooltip" href="javascript:{}" title="Send A Message" onclick="showMailPopup(\''+friendsList[i].id+'\',\''+friendsList[i].candidateName+'\',\'Message\')"><i class="icon-envelope opacityFilter-50"></i></a>');
		templateClone.find(".connectLinkCls").html('<a rel="tooltip" href="javascript:{}" class="connectLink" title="Connect"><i class="icon-plus-sign opacityFilter-50"></i></a>');
		templateClone.find(".userIdhiddenVar").html('<input type="hidden" value="'+friendsList[i].id+'" class="userId" />');
		templateClone.find(".usernamehiddenVar").html('<input type="hidden" value="'+friendsList[i].candidateName+'" class="userName" />');
		templateClone.find(".constituencyNamehiddenVar").html('<input type="hidden" value="'+friendsList[i].constituencyName+'" class="constituencyName" />');
		templateClone.appendTo(".peopleYouMayKnowULClass");
		
	}
}


function savefavouriteLink(){
	var id='';
	var name='';
	var pageTitle='';
	var queryString='';
	var link='';
	
	var areaSelected=$('input:radio[name=radioForFavourite]:checked').val();
	if(areaSelected=='Constituency'){
		var constType=$('input:radio[name=typeRadio]:checked').val();
		
		if(constType=='Assembly'){
			if($("#stateList option:selected").val()!=0){
				var id=	$("#constituencyList option:selected").val();
				if(id!=0){
					var name=$("#constituencyList option:selected").text();
					pageTitle=name+' Assembly Constituency Page - News, Details, Mandals, Parties Performance, Voting Trendz, Election Results,MLA, MP,MPTC, ZPTC Election Results';
					link='constituencyPageAction';
					queryString ='constituencyId='+id+',';
				}
				else{
					openModal('Please Select Assembly Constituency',"alert");
					return;
				}
			}
			else{
				openModal('Please Select State','"alert"');
				return;
			}
		}
		
		else if(constType=='Parliament'){
			var id=	$("#constituencyList option:selected").val();
			if(id!=0){
				var name=$("#constituencyList option:selected").text();
				pageTitle=name+' Parliament Constituency Page - News, Details, Mandals, Parties Performance, Voting Trendz, Election Results,MLA, MP,MPTC, ZPTC Election Results';
				link='constituencyPageAction';
				queryString ='constituencyId='+id+',';	
			}
			else{
				openModal('Please Select Parliament Constituency',"alert");
				return;
			}
		}
		else{
			openModal('Please Select Constituency Type',"alert");
		}
	}
	else if(areaSelected=='State'){
		var id=	$("#stateList option:selected").val();
		if(id!=0){
			var name=$("#stateList option:selected").text();
			pageTitle=name+' News,Elections, districts,Constituencies,Census, Election Results';
			link='statePageAction';
			queryString ='stateId='+id+',';
		}
		else{
			openModal('Please Select the State',"alert");
			return;
		}
		
	}
	else if(areaSelected=='District'){
		if($("#stateList option:selected").val()!=0){
			var id=	$("#districtList option:selected").val();
			if(id!=0){
				var name=$("#districtList option:selected").text();
				pageTitle=name+' District News,Constituencies,MLA, MP,Details,  Elections Results,Parties Performance,MPTC, ZPTC, Municipality, Corporation Election Results';
				link='districtPageAction';
				queryString='districtName='+name+',districtId='+id+',';
			}
			else{
				openModal('Please Select District',"alert");
				return;
			}
		}
		else{
			openModal('Please Select State',"alert");
			return;
		}
	
	}
	else if(areaSelected=='Special Page'){
		var id=	$("#specialPageList option:selected").val();
		if(id!=0){
			var name=$("#specialPageList option:selected").text();
			pageTitle=name+' Exit Polls, Live Result Analysis, Latest Updates - News, Videos, Photos,Parties Performace';
			link='specialPageAction';
			queryString ='specialPageId='+id+',';
		}
		else{
			openModal('Please Select Special Page',"alert");
			return;
		}
	}
	
	else {
		openModal('Please Select Any Option',"alert");
	}
	var jObj = {
				link: link,
				queryString:queryString,
				pageTitle:pageTitle,
				environment:environment,
				task: 'saveFavouriteLink'
				
			};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "saveUserFavouriteLink.action?"+rparam;
	callAjax1(jObj,url);

}
	function getSpecialPagesInfo(){
		var jsObj ={
			task:"getSpecialPagesIdTtl"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSpecialPageAction.action?"+rparam;	
		callAjax1(jsObj,url);
	}
	
	function buildStatesIntoDropDown(results,id){
		//getAllFavLinksOfUser();
		var elmt = document.getElementById(id);
		var area=$('input:radio[name=radioForFavourite]:checked').val();
		if(results[0].id == 0)
		{
			delete results[0];
		}  
		
		if( !elmt || results == null)
		return;
	
		$('#'+id).find('option').remove();
		
		var option = document.createElement('option');
		option.value="0";
		option.text="Select";
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
		if(area=='State'){
			for(var i in results)
			{
				var stExist=$.inArray(results[i].id, stateList)
				if(stExist==-1){
					var option = document.createElement('option');
					option.value=results[i].id;
					option.text=results[i].name;
					try
					{
						elmt.add(option,null); // standards compliant
					}
					catch(ex)
					{
						elmt.add(option); // IE only
					}
				}
		   }
		}
		else if(area=='District'){
			if($('#stateList option').length<=1){
			  for(var i in results)
			  {
				var option = document.createElement('option');
					option.value=results[i].id;
					option.text=results[i].name;
					try
					{
						elmt.add(option,null); // standards compliant
					}
					catch(ex)
					{
						elmt.add(option); // IE only
					}
			  }
			}
			else{
			for(var i in results)
			{
				var distExist=$.inArray(results[i].id, disList)
				if(distExist==-1){
					var option = document.createElement('option');
					option.value=results[i].id;
					option.text=results[i].name;
					try
					{
						elmt.add(option,null); // standards compliant
					}
					catch(ex)
					{
						elmt.add(option); // IE only
					}
				}
		   }
		   }
		}
		else if(area=='Constituency'||area=='Assembly'){
			if($('#stateList option').length<=1){
			  for(var i in results)
			  {
				var option = document.createElement('option');
					option.value=results[i].id;
					option.text=results[i].name;
					try
					{
						elmt.add(option,null); // standards compliant
					}
					catch(ex)
					{
						elmt.add(option); // IE only
					}
			  }
			}
			else{
			for(var i in results)
			{
				var consExist=$.inArray(results[i].id, conList)
				if(consExist==-1){
					var option = document.createElement('option');
					option.value=results[i].id;
					option.text=results[i].name;
					try
					{
						elmt.add(option,null); // standards compliant
					}
					catch(ex)
					{
						elmt.add(option); // IE only
					}
				}
		   }
		   }
		}
		else if(area=='Parliament'){
			for(var i in results)
			{
				var consExist=$.inArray(results[i].id, conList)
				if(consExist==-1){
					var option = document.createElement('option');
					option.value=results[i].id;
					option.text=results[i].name;
					try
					{
						elmt.add(option,null); // standards compliant
					}
					catch(ex)
					{
						elmt.add(option); // IE only
					}
				}
		   }
		}
		
	}
	
	
//modified from gayathri

function displayLocationScope(id){
	
	if(id == 'state'){
		$("#Statediv").css("display","block");
		$("#specialDiv").css("display","none");
		$("#Districtdiv").css("display","none");
		$("#electionTypes").css("display","none");
		$("#Constituencydiv").css("display","none");
		
		getStatesInPs();
	}
	if(id == 'District'){
	$("#Statediv").css("display","block");
	$("#Districtdiv").css("display","block");
	$("#specialDiv").css("display","none");
	$("#electionTypes").css("display","none");
	$("#Constituencydiv").css("display","none");
	getStatesInPs();
	}
	
	if(id == 'Constituency'){
		getStatesInPs();
		$('#Assembly').attr('checked',true);
		$("#electionTypes").css("display","block");
		$("#specialDiv").css("display","none");
		$("#Statediv").css("display","block");
		$("#Districtdiv").css("display","none");
		$("#Constituencydiv").css("display","block");
		
	}
	if(id == 'SpecialPage'){
		$("#specialDiv").css("display","block");
		$("#Statediv").css("display","none");
		$("#Districtdiv").css("display","none");
		$("#electionTypes").css("display","none");
		$("#Constituencydiv").css("display","none");
		
		getSpecialPagesInfo();

	}
}
$('#Assembly').live('click',function(){

	$("#Statediv").css("display","block");
	$("#Districtdiv").css("display","none");
	$("#Constituencydiv").css("display","block");
	$("#specialDiv").css("display","none"); 
	$('#constituencyList').find('option').remove().append('<option value="0">Select</option>');
	getStatesInPs();
	
			
});
$('#Parliament').live('click',function(){
	
	$("#Constituencydiv").css("display","block");
	$("#specialDiv").css("display","none");
	$("#Statediv").css("display","none");
	$("#Districtdiv").css("display","none");
	getParliamentsInState();
});

function openPopupForFavouriteLinks(){

var str=''; 
str+='<label class="" style="display:inline-block;margin-left: 10px;font-size: 16px;"><input type="radio" id="state" name="radioForFavourite" onclick="displayLocationScope(this.id)" value="State" style="margin-top:1px;"/><span style="font-family: Helvetica; font-size: 14px; margin-left: 5px;">State</span></label>';
str+=' <label class="" style="display:inline-block;margin-left: 10px;font-size: 16px;"><input type="radio" id="District" name="radioForFavourite" onclick="displayLocationScope(this.id)" value="District" style="margin-top:1px;"/><span style="font-family: Helvetica; font-size: 14px; margin-left: 5px;">District</span> </label>';
str+='  <label class="" style="display:inline-block;margin-left: 10px;font-size: 16px;"><input type="radio" id="Constituency" name="radioForFavourite" onclick="displayLocationScope(this.id)" value="Constituency" style="margin-top:1px;"/> <span style="font-family: Helvetica; font-size: 14px; margin-left: 5px;">Constituency</span> </label>';
str+=' <label class="" style="display:inline-block;margin-left: 10px;font-size: 16px;"><input type="radio" id="SpecialPage" name="radioForFavourite" onclick="displayLocationScope(this.id)" value="Special Page" style="margin-top:1px;"/> <span style="font-family: Helvetica; font-size: 14px; margin-left: 5px;">Special Page</span></label>';
str+='<hr style="margin-top: 0px; border: 1px solid rgb(212, 212, 212);">';
str+='  <div id="electionTypes" style="display: none;text-align: center;"><label class="" style="display:inline-block;margin-left: 10px;font-size: 16px;"><input type="radio" id="Assembly" name="typeRadio"  value="Assembly" checked="checked" style="margin-top:1px;"/><span style="font-family: Helvetica; font-size: 14px; margin-left: 5px;"> Assembly </span></label>';
str+='  <label class="" style="display:inline-block;margin-left: 10px;font-size: 16px;"><input type="radio" id="Parliament"  name="typeRadio" value="Parliament" style="margin-top:1px;"/><span style="font-family: Helvetica; font-size: 14px; margin-left: 5px;"> Parliament</span></label></div>';
str+='<form class="form-horizontal">';
str+='<div id="Statediv" class="control-group" style="display:none;"> <label class="control-label">Select State:</label><div class="controls"><select id="stateList" name="stateList" onChange="getLocalitiesUnderState()";> <option value="0"> Select State</option> </select></div></div>';
str+=' <div id="Districtdiv" class="control-group" style="display: none;"> <label class="control-label">Select District:</label><div class="controls"><select id="districtList" name="districtList"> <option value="0"> Select District </option> </select></div> </div>';
str+='<div id="Constituencydiv" class="control-group" style="display: none;"><label class="control-label">	Select Constituency:</label><div class="controls"><select id="constituencyList" name="constituencyList"></select></div> </div>';
str+='<div id="specialDiv" class="control-group" style="display: none;"><label class="control-label">Select Special Page:</label><div class="controls"><select id="specialPageList" name="specialPageList"></select></div></div>';
str+='</form>';
str+='<span btn="info" onClick="savefavouriteLink()" id="addFav" style="display:none;">Add</span>';

$("#addPopupForFavouriteLinks").html(str);
	
	$("#addPopupForFavouriteLinks").dialog({
			title:"Add Your FavouriteLinks ",
			autoOpen: true,
			show: "blind",
			width: 450,
			minHeight:250,
			modal: true,
			hide: "explode",
			buttons: { "Add": function() { $('#addFav').trigger('click'); } } 
		});

}	
function getLocalitiesUnderState(){
var id=$('#stateList').val();
getDistrictsInState(id);
getConstituencyInState(id);
}

function getConstituencyInState(value)
{
	var jsObj=
	{				
			electionTypeId:2,
			stateId: value,
			task: "getConstituencies",
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllConstituenciesInState.action?"+rparam;						
callAjax1(jsObj,url);
}

function getParliamentsInState(value)
{
	
	var jsObj=
	{				
			task: "getAllParliamentConstituencies",
			elmtId: 'constituency' 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllParliamentConstInCountry.action?"+rparam;						
callAjax1(jsObj,url);
}

function getDistrictsInState(value){
var jsObj=
		{				
				stateId:value,
				task:"findDistrictsForAState",
				taskType:"getRegions"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDistrictsForAStateAjaxAction.action?"+rparam;						
	callAjax1(jsObj,url);
}

function showSelectBoxForSpecialPages(results){
	
	var elmt = document.getElementById("specialPageList");
	
	if( !elmt || results == null)
		return;
	
	$('#specialPageList').find('option').remove();
	
	var option = document.createElement('option');
	option.value="0";
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}
	for(var i in results)
	{
		var eleExist=$.inArray(results[i].specialPageId, spclList)
		if(eleExist==-1){
		var option = document.createElement('option');
		option.value=results[i].specialPageId;
		option.text=results[i].title;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	}
}
function getAllFavLinksOfUser(){
	 var jsObj ={
			task:"forAllFavLinks"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getFavouriteLinksAction.action?"+rparam;	
		callAjax1(jsObj,url);
}

// starting ----------You May Know Module (See All) updated by Srishailam------

function  getDetailsForallDistricts(){
var userStateId=stateId;
var jsObj=
		{						
				stateId:userStateId,
				task:"getDistrictNames"
		}	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendUpdatesByemailsAction.action?"+rparam;						
	callAjax1(jsObj,url);
}
function getDetailsForallConstituencies(district){
	$('#connectStatusSelect').val(0);
	$('#connectStatusTextBox').val('');
	var districts = $('#connectDistrictSelect').val();
		if( districts == '0' ){
		$(".templateDiv").html('<div> Please Select District</div>').appendTo(".placeholderCenterDiv");
		return false;
		}
var jsObj=
		{						
				districtId:district,
				task:"getConstituencyNames"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendUpdatesByemailsAction.action?"+rparam;						
	callAjax1(jsObj,url);
}
function iterateDistrictNames(result)
{
	var elmt = document.getElementById('connectDistrictSelect');
	clearOptionsListForSelectElmtId('connectDistrictSelect');
		
	for(var i in result)
	{		
		var option=document.createElement('option');		
		option.value=result[i].id;
		option.text=result[i].name;
	try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
	$('#connectDistrictSelect').val(districtId);
	getDetailsForallConstituencies(districtId);
}
function iterateDetailsNames(result){
	var elmt = document.getElementById('connectConstituencySelect');
	var option = document.createElement('option');
	clearOptionsListForSelectElmtId('connectConstituencySelect');
	option.value="0";
	option.text="All";
	try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in result)
	{		
		var option=document.createElement('option');		
		option.value=result[i].id;
		option.text=result[i].name;		
	try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
	if(!flag){
	flag=true;
	$('#connectConstituencySelect').val(constituencyId);	
	}
	getAllConnectedUsersByFilterView();
}
function clearOptionsListForSelectElmtId(elmtId)
{
	var elmt = document.getElementById(elmtId);
	if(!elmt)
		return;	
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}

function getAllConnectedUsersByDistrictWiseFilterView(locationType,userId)
{	
	var myDistrictId= $('#connectDistrictSelect').val();
	var mylocationId = myDistrictId;	
	var locationType= "DISTRICT";
	var locationName = districtName;
	var userLoginId = loginUserId;
	var jsObj ={
				locationId:mylocationId,							
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
				showAllConnectedUsersInPanelByFilterView(jsObj,results);
			}
		});
		custom_paginator.initialize();	
}

function getAllConnectedDistrictWise1UsersByFilterView(locationType,userId)
{
		var connectConstiSelectElmtValue = '';
		var connectConstiArray = new Array();	
		var allConstituencies=$('#connectConstituencySelect').val();		
		
		var connectStatusSelectElmt = $("#connectStatusSelect").val();
		var statusText = $('#connectStatusSelect option:selected' ).text();
		var textValue = $.trim($("#connectStatusTextBox").val());
		
		$('#connectConstituencySelect option').each(function(){
		var value=$(this).val();
		if(value!=0)
		connectConstiArray.push(value);		
		});
		
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

function showDeleteMessagesStatus(results,jsObj)
{

if(jsObj.btnName == 'Inbox')
$("#Inbox").trigger("click");
	else
$("#SentBox").trigger("click");
}



// ending ----------You May Know Module (See All) updated by Srishailam------

function getCadresInfo()
{
		$('#announcementsDiv').hide();
		$("#impdatesDiv").hide();
	  var jsObj = 
		{
			task:"getCadreInfo"
		};
		var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getDashBoardInformationAction.action?"+rparam;
		callAjax1(jsObj,url);
}

function buildCadreInfoTable(results)
{	
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	$(".placeholderCenterDiv").children().remove();
	$('#placeholderCenterDivId').html('');
	$('#headerDiv').html('');
	$('.FavoriteLinksDiv').html('');
	$('#headerDiv').html('<b>Cadre Information</b>');
	var str='';
	
	//$("#cadresDiv_main").css("style","display");
	//$("#cadresDiv_main").css("box-shadow","0 0 1px rgba(0, 0, 0, 0.5), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.09)");

    str+='<div id="cadresDiv_head">';
    str+='	<table><tr>';
	str+='		<td><img src="images/icons/indexPage/group_icon.png"/></span></td>';
	str+='		<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Cadres Info</span></td>';
	str+='	</tr></table>';
	str+='</div>';
	str+='<div id="cadresDiv_body" style="margin-bottom: -14px;">';
	str+='	<span class="dashBoardCenterContentBody" style="color:#4B74C6"></span>';
	if(results.cadresByCadreLevel !=null)
	{
	  $.each(results.cadresByCadreLevel,function(key,value)
		{
			str+='	<ul class="dashBoardContentList">';
	
			str+='		<li>'+key+'   Level Cadres - '+value+' </li>';
		
			str+='	</ul>';
		});
	
	str+='</div> ';
	str+='<div id="cadresDiv_footer" style="text-align:right;margin: 10px;margin-right: 94px;">';
	str+='	<span class="dashBoardLinks">';
	str+='		<a href="cadreManagementAction.action#regionLevelCadreDivHead" class="indexPageAnc" style="font-size: 13px;" target="_blank">View All</a>';
	str+='	</span>';
	
    str+='</div>';
	}
	else
	{
		$("#caderInfo").css("padding","20px");
		$("#cadresDiv_body").css("margin","0px");
		str+= '<div id="div_body">';
		str+= '	<span class="dashBoardCenterContentBody" style="color:#4B74C6;margin-left: 14px;">You have  0  Cadres</span>';
	}
	 str+='</div>';
	$("#caderInfo").html(str);
}
function openNewAnnouncementPopup()
{
	var newAnnouncementBrowser = window.open("newAnnouncementAction.action?windowTask=new","addNewAnnouncement","scrollbars=yes,height=550,width=500,left=200,top=200");
	newAnnouncementBrowser.focus();
}
function openEditAnnouncement()
{
	var editAnnouncementbrowser = window.open("openEditAnnouncementAction.action","addNewProblem","scrollbars=yes,height=750,width=1000,left=10,top=10");
	editAnnouncementbrowser.focus();
}

function getUserImpEvents()
{
 
		var jsObj = {
			task:"getUserImportantEvents"
		};
		var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getDashBoardInformationAction.action?"+rparam;
		callAjax1(jsObj ,url);
}



function buildUserImpEvents(results)
{
	var str='';
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	$(".placeholderCenterDiv").children().remove();
	$('#placeholderCenterDivId').html('');
	$('#headerDiv').html('');
	$('.FavoriteLinksDiv').html('');
	$('#headerDiv').html('<b>Important Events</b>');
	str+='<div id="impEventsDiv_head">';
	str+='<table><tr>';
	str+='<td><img src="images/icons/indexPage/cal.png"/></span></td>';
	str+='<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Today \'s Event</span></td>';
	str+='</tr></table>';
	str+='</div>';
	
	if(results.userEvents.length!=0)
	{
	  for(var i in results.userEvents)
	   {
		str+='	<ul class="dashBoardContentList">';
	    	str+='<li>'+results.userEvents[i].eventDisplayTitle+'</li>	';
		str+='</ul>';
	  }
		
	
	str+='</div>';
	str+='<div id="impEventsDiv_footer" style="text-align:right">';
	str+='	<span class="dashBoardLinks">';
	str+='		<a href="cadreManagementAction.action#yui-gen3" class="indexPageAnc" target="_blank">View All</a>';
	str+='	</span>';
	str+='</div>';
	}
	else
	{
		$("#impEvents").css("padding","20px");
		str+='<div id="impEventsDiv_body">';
		str+='<span class="dashBoardCenterContentBody" style="color:#4B74C6;margin-left: 14px;">You have  '+results.userEvents.length+'  event(s) scheduled today</span>';
		str+='	<span class="dashBoardLinks" >';
		str+='		<a href="cadreManagementAction.action?eventScope=createEvent" title="Click Here To Create Important Events" class="indexPageAnc" onclick="" style="margin-left: 61px;font-size: 14px;" target="_blank">Create</a>';
		str+='	</span>';
	}
	str+='</div>';
	$("#impEvents").html(str);
}


function clearDivs()
{
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	$('#headerDiv').html('');
	$("#impdatesDiv").hide();
	$("#impEvents").hide();
	$("#announcementsDiv").hide();
	$('#placeholderCenterDivId').html('')
	
}


function buildProblemDetailsByStatus(jsObj,results)
{

	inboxCount=results.unreadMsgCount;
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	if(results.resultStatus.resultCode !="0")
	{
		$("#headerDiv").html("Data could not be retrived due to some technical difficulties.");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$("#headerDiv").html('<ul class="nav nav-tabs"><li class="active"><a id="Inbox" style="cursor:pointer">Inbox ( '+inboxCount +' )</a></li><li><a id="SentBox" style="cursor:pointer">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');
		$(".placeholderCenterDiv").html("No messages has been sent to you.");
		return;
	}
		
		$("#headerDiv").html('<ul class="nav nav-tabs"><li class="active"><a id="Inbox" style="cursor:pointer">Inbox ( '+inboxCount +' )</a></li><li><a id="SentBox" style="cursor:pointer">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');


	var elmtBody = document.getElementById("placeholderCenterDivId");
	elmtBody.innerHTML = '';
	var newProblems = results.candidateVO;
	 if(newProblems == null || newProblems.length == 0)
		{
		  elmtBody.innerHTML = '<div class="newProblemData_main" style="width:95%"><center><b>No Records Found<center></b> </div>';
		  return;
		}

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
				templateClone.find('.imgClass').html('<img height="30" width="30" src="images/icons/indexPage/human.jpg"/>');
			}
			
			
			templateClone.find(".dateAndTimeReceived").html(''+results.candidateVO[i].postedDate+'');
			

			templateClone.find(".delete").html('<a data-placement="top" rel="tooltip" data-original-title="remove Message" name="Inbox" onclick="deleteMail('+results.candidateVO[i].id+',\'Message\',\'Inbox\','+results.candidateVO[i].costumMessageId+')"><img class="deleteImg" id="deleteImg_1" src="images/icons/delete.png" alt="deleteImg" width="13" height="13"></a>');
			templateClone.find(".reply").html('<a data-placement="top" rel="tooltip" href="#" data-original-title="Reply To This Message" class="btn" style="color:black;" onclick="showMailPopup('+results.candidateVO[i].id+',\''+results.candidateVO[i].candidateName+'\',\'Message\')"> Reply</a>');
			
			
		templateClone.appendTo(".placeholderCenterDiv");
		}

		var viewMore = $('<div style="padding: 5px;" class="custom_paginator_class" id="custom_paginator_class"></div>');
		viewMore.appendTo('.placeholderCenterDiv');

}
function buildSentDetailsByStatus(jsObj,results)
{
debugger;
	$("#headerDiv").html('');
	$(".placeholderCenterDiv").html('');
	$(".placeholderCenterDiv").children().remove();
	clearAllSubscriptionDivs();
	clearAllFavoriteLinkDivs();
	if(results.resultStatus.resultCode !="0")
	{
		$("#headerDiv").html("Data could not be retrived due to some technical difficulties.");
		return;
	}
	else if(results.candidateVO == null || results.candidateVO.length == 0)
	{
		$("#headerDiv").html('<ul class="nav nav-tabs"><li><a id="Inbox" >Inbox ( '+inboxCount +' )</a></li><li class="active"><a id="SentBox">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');
		$(".placeholderCenterDiv").html("No messages has been sent by you.");
		return;
	}
		
		
		$("#headerDiv").html('<ul class="nav nav-tabs"><li><a id="Inbox" >Inbox ( '+inboxCount +' )</a></li><li class="active"><a id="SentBox">Sent</a></li></ul><h6 class="pull-right" style="margin-top:-10px;">Total Messages: <span style="color:blue;">'+results.totalMsgCount+'</span></h6>');
		for(var i in results.candidateVO)
		{
		var template = $(".templateDivMsg");
		var templateClone = template.clone();
		templateClone.removeClass("templateDivMsg");
		templateClone.find(".reply").remove();
		templateClone.find(".messageFrom").html(''+results.candidateVO[i].candidateName+'');
			templateClone.find(".message").html(''+results.candidateVO[i].message+'');
			//templateClone.find('.imgClass').html('<img height="45" width="45" src="images/icons/indexPage/human.jpg"/>');
			//templateClone.find(".delete").html('<a data-placement="top" rel="tooltip" href="#" name ="sentBox" class="btn" style="color:black;" onclick="deleteMail('+results.candidateVO[i].id+',\'Message\',\'sentBox\','+results.candidateVO[i].costumMessageId+')">Delete</a>');
			templateClone.find(".delete").html('<a data-placement="top" rel="tooltip" data-original-title="remove Message" name="sentBox" onclick="deleteMail('+results.candidateVO[i].id+',\'Message\',\'sentBox\','+results.candidateVO[i].costumMessageId+')"><img class="deleteImg" id="deleteImg_1" src="images/icons/delete.png" alt="deleteImg" width="13" height="13"></a>');
			templateClone.find(".dateAndTimeReceived").html(''+results.candidateVO[i].postedDate+'');
			if(results.candidateVO[i].profileImg!=""){
			var imageStr = "pictures/profiles/"+results.candidateVO[i].profileImg;
			templateClone.find('.imgClass').html('<img height="30" width="30" src='+imageStr+'></img>');
			}
			else{
				templateClone.find('.imgClass').html('<img height="30" width="30" src="images/icons/indexPage/human.jpg"/>');
			}
			
		templateClone.appendTo(".placeholderCenterDiv");
		}
		var viewMore = $('<div style="padding: 5px;" class="custom_paginator_class" id="custom_paginator_class"></div>');
		viewMore.appendTo('.placeholderCenterDiv');
}
function copyId(id)
{
  clickid = id;
}

var custom_paginator1 = {
	maxIndex:"",
	startIndex:"",
	ajaxCallURL:"",
	initialParams:"",
	resultsShown:"",
	callBackFunction:"",
	jObj:{},
	results:{},
	totalRecords:"",
	paginatorElmt:"",		
	paginator:function(obj){
		this.startIndex = obj.startIndex;
		this.ajaxCallURL = obj.ajaxCallURL;
		this.jObj = obj.jObj;
		this.callBackFunction = obj.callBackFunction;
		this.paginatorElmt = obj.paginatorElmt;
		this.maxIndex = obj.maxIndex;
        this.resultsCount = obj.resultsCount;
		
	},	
	doAjaxCall:function(start,eleIdClicked){
		var url = this.ajaxCallURL+"&startIndex="+start+"&resultsCount="+this.resultsCount;
		
		var callback = {	
		
	    success : function( o ) {
			try 
			{				
				results = YAHOO.lang.JSON.parse(o.responseText);
				console.log(results);
				if(results != null)
				    this.totalRecords = results.totalMsgCount;	
				else
                     this.totalRecords = 0;
					
				this.callBackFunction();
				 this.buildPaginator(eleIdClicked);
			}
			catch (e)
			{   		
				//alert("Invalid JSON result" + e);   
			}  
		},
		scope : this,
		failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				  }
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	},
	initialize:function (){	
		this.doAjaxCall(this.startIndex,0);
	},
	buildPaginator:function(eleIdClicked)
	{
		var paginatorElmt = document.createElement('Div');
		paginatorElmt.setAttribute("class","paginatorElmtClass");
		var iteration = Math.ceil(this.totalRecords/this.resultsCount);		
		var countIndex = this.startIndex;
		var str = '';
		

		if(iteration > 1)
		{
			for(var i=1; i<=iteration; i++)
			{		
				var eleid="a"+i;
				str += '<a href="javascript:{}" id="a'+i+'"   onclick="copyId(this.id);getReqData('+countIndex+',\''+eleid+'\')" style="padding: 5px;">'+i+'</a>';
				countIndex+=this.resultsCount;
			}
		}
		
		if(document.getElementById("custom_paginator_class")!=null)	
		document.getElementById("custom_paginator_class").innerHTML = str;

		if(eleIdClicked==0){
		$("#custom_paginator_class a:first").addClass("pagenationStyle");
		}
		else {
			$("#"+eleIdClicked).addClass("pagenationStyle");
			}
		
		if(clickid != null)
		{
		 $("#"+clickid).addClass('pagenationStyle');
		}
		else
		{
		  $("#custom_paginator_class").addClass('pagenationStyle');
		}
	}
};
function getReqData(countIndex,eleid){
custom_paginator1.doAjaxCall(countIndex,eleid);
}