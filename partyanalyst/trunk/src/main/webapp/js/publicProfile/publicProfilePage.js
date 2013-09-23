
var friendsInPP = [];
var startIndex;
var fromDate;
$("document").ready(function(){
	
	$('.friendsInPP').click(function(){
		$(".placeholderCenterDiv").children().remove();
		$(".placeholderCenterDiv").css('display','inline-block');
	    $(".placeholderCenterDiv").html('<h4 class=" breadcrumb" style="width:593px;">Friends :</h4>');                           
		$("#problemsDiv").children().remove();

	if(friendsInPP.length > 0 && friendsInPP != 'null'){
			for(var i in friendsInPP)
			{
			   var name = friendsInPP[i].firstName+" "+friendsInPP[i].lastName;
			   var imageStr = "pictures/profiles/"+friendsInPP[i].profilePic;
			   var template = $(".friendListTemplate");
			   var templateClone =  template.clone();
				   templateClone.removeClass("friendListTemplate");
				   templateClone.find('.frndName').html('<a href="userProfile.action?profileId='+friendsInPP[i].id+'">'+name+'</a>');
			   
			   if(friendsInPP[i].profilePic == null)
				  templateClone.find('.frndImg').html('<a href="userProfile.action?profileId='+friendsInPP[i].id+'"><img height="50" width="55" src="pictures/profiles/member.jpg" /></a>');
			   else
				  templateClone.find('.frndImg').html('<a href="userProfile.action?profileId='+friendsInPP[i].id+'"><img height="50" width="55" src="'+imageStr+'" style="height: 60px;"/></a>');
			 templateClone.appendTo(".placeholderCenterDiv");
			}
	}
	else{
			$(".placeholderCenterDiv").children().remove();
			$(".placeholderCenterDiv").css('display','inline-block'); 
		  $(".placeholderCenterDiv").html('<h4 class=" breadcrumb" style="width:593px;">Friends :</h4><b><font style="text-transform: capitalize;">'+profileUserName+' </font> dont have any Friends<b>'); 
	  }
	});

	 $(".problemLink").click(function(){
		var linkType = "problemLink";
		startIndex = 0;
	
		 var jsObj ={
				startIndex   : startIndex,
				maxIndex     : 10,
				linkType     : linkType,
				task:"getProblemDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getProblemDetailsForPublicProfileAction.action?"+rparam;	
		callAjaxForProfilePage(jsObj,url);
	});



	$(".sendMessageLinkInPP").click(function(){
		
		var id = profileId;
		var name = profileUserName;
		var type="Message";
		
		$("#allConnectedUsersDisplay_main").children().remove();
		$( "#connectPeoplePopup").dialog({
			title:"Send Message to "+name,
			autoOpen: true,
			show: "blind",
			width: 430,
			height:"auto",
			modal: true,
			hide: "explode"
		});
		
		var div = $("<div class='connectPeoplePopupInnerDiv'></div>");
		var errorDiv = $("<div id='ErrorMsgDivId'></div>");
		var label = $("<label class='messageLabel'></label>");
		
		var button = $("<div id='remainChars'><span id='maxcount'>300 </span> <span>chars remaining..</span>&nbsp;&nbsp;<span>Should not exceed 300 chars</span></div><input class='btn-info btn-small' style='margin-left:15px;' id='sendMessageButtonId' type='button' value='send' onclick='sendMessageToConnectedUser("+id+",\""+type+"\")'/>");
		var textarea = $("<textarea id='connectMessageText' placeholder='Enter Your Message Here..'  maxlength='300' style='width:357px;height:60px;'></textarea><br>");
		
		div.append(errorDiv);
		div.append(label);
		div.append(textarea);
		div.append(button);
		$('#allConnectedUsersDisplay_main').append(div);
		
	});
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
$('#connectUserMsg').live("keyup",function() {

	var limitFieldElmt = document.getElementById('connectUserMsg');
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
$(".connectLinkInPP").click(function(){
	
	$("#allConnectedUsersDisplay_main").children().remove();
	
	

		var userId = profileId;
		var userName = profileUserName;
		var userLoginId = loginUserId;
		
		$( "#connectPeoplePopup" ).dialog({
			title:"Connect To  "+userName,
			autoOpen: true,
			show: "blind",
			width: 500,
			minHeight:250,
			modal: true,
			hide: "explode"
		});

		var div = $("<div class='connectPeoplePopupInnerDiv'></div>");
		//var Name=$("<label>"+userName+"</label>");
		//var message = $("<label class='messageLabel'>Message</label>");
		var connectBtn = $('<div id="remainChars"><span id="maxcount">300 </span> <span>chars remaining..</span>&nbsp;&nbsp;<span>Should not exceed 300 chars</span></div><input type="button" value="Connect" class="btn-info btn-small" id="connectMessageLinkInPP"  style="float:right;"/>');
		var textArea = $("<textarea id='connectUserMsg' placeholder='Enter Your Message Here..'  style='width:339px;height:82px;resize:none;'></textarea>");
		var image = $('<img height="100" width="95" src="images/icons/indexPage/human.jpg">');
		
		var connectedPersonId = $('<input type="hidden" value='+userId+' id="connectedPersonId"/>');
		var errorDiv = $("<div id='errorMsgDiv'></div>")
		div.append(errorDiv);
		//div.append(Name);
		//div.append(message);
		div.append(textArea);
		div.append(image);
		div.append(connectBtn);
		div.append(connectedPersonId);
		$('#allConnectedUsersDisplay_main').append(div);

});


$("#connectMessageLinkInPP").live("click",function(){

		var connectUserId = $('#connectedPersonId').val();
		var connectMsg = $.trim($("#connectUserMsg").val());
		var errorMsgDiv = $("#errorMsgDiv");
		$("#errorMsgDiv").html('');
		var connectUserLoginId = loginUserId;
		var users = new Array();
		users.push(connectUserId);
		if(connectUserMsg.length > 200)
		{
			$("#errorMsgDiv").html('<font style="color:red">Message Should be lessthan 200 characters.</font>');
			return;
		}
		
		disableButton("connectMessageLinkInPP");
		$("#connectPeoplePopup").dialog('close');
		
		var jsObj ={
											
				connectUserIds:users,
				connectMessage:connectMsg,
				userId:connectUserLoginId,
				task:"connectToUserInPP"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "connectToUserInPublicProfileAction.action?"+rparam;					
	callAjaxForProfilePage(jsObj,url);
	
});



	$(".acceptRequestLink").click(function(){
		
		var jsObj=
	{		
			type:"accept",
			recepientId:profileId,
			task:"acceptRequest"								
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;					
	callAjaxForProfilePage(jsObj,url);
	});

	$(".rejectRequestLink").click(function(){
		
		var jsObj=
	{		
			type:"reject",
			recepientId:profileId,
			task:"rejectRequest"			
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;					
	callAjaxForProfilePage(jsObj,url);
	});

	$(".blockButtonLink").click(function(){
		
		var jsObj=
	{		
			type:"block",
			recepientId:profileId,
			task:"blockRequest"								
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateUserStatusAction.action?"+rparam;						
	callAjaxForProfilePage(jsObj,url);
	});


});//End ready


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
		
	$("#ErrorMsgDivId").html('<img src="images/icons/search.gif" class="searchImgCls"/>');
	disableButton("sendMessageButtonId");
	connectMsg = replaceSpecialCharacters(connectMsg);

	var jsObj ={
				loginUserId:loginUserId,
				message:connectMsg,				
				recipientId:userId,
				type:type,
				task:"sendMessageToConnectUser"
			 };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "messageToConnectedUser.action?"+rparam;					
	callAjaxForProfilePage(jsObj,url);
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

function callAjaxForProfilePage(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
					
					results = YAHOO.lang.JSON.parse(o.responseText);
					
					if(jsObj.task == "getStreamingData" || jsObj.task == "getViewMoreStreamingData")
					{
						
						showStreamingData(results,jsObj);
					}
						

					else if(jsObj.task == "getProblemDetails")
						showProblemDetails(results,jsObj);
					 
					 else if(jsObj.task == "sendMessageToConnectUser")
					{
						if(jsObj.type=="Connect"){
							location.reload(true);//For refreshing the page...
						}else{
							showMessageSentConfirmation(results);
						}
					}
					else if(jsObj.task == "connectToUserInPP")
					{
						showConnectStatus(results);
					}
					
					else if(jsObj.task == "acceptRequest" || jsObj.task == "rejectRequest" || jsObj.task == "blockRequest")
					{
						$(".requestsDivCls").children().remove();
						$(".requestsDivCls").html('');
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

function showProblemDetails(results,jsObj)
{
	$("#problemsDiv").children().remove();
	if(results == null || results.length == 0)
	{

	}
	for(var i in results)
	{
	  var problemList = results[i].problemBeanVOList;
	  var postedDate = results[i].postDate;
		
		for(var j in problemList)
		{
		  var template = $(".problemTemplate");
		  var templateClone = template.clone();
		  templateClone.removeClass("problemTemplate");
		  templateClone.find('.problemTitle').html(''+problemList[j].problem+'');
		  templateClone.find('.problemDescription').html(''+problemList[j].description+'');
		  templateClone.appendTo('.placeholderCenterDiv');
		}
	}
}

function executeOnload()
{

	//startIndex = 0;
	
	var jsObj ={
		//startIndex   : startIndex,
		//maxIndex     : 10,
		profileId    :profileId,
		task:"getStreamingData"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStreamingDataForProfileUserAction.action?"+rparam;	
	callAjaxForProfilePage(jsObj,url);
}
function viewMoreStreeming()
{
	$('#moreButtonDiv').html('');
	var jsObj ={
		//startIndex   : startIndex,
		//maxIndex     : 10,
		fromDate     : fromDate,
		profileId    :profileId,
		task:"getViewMoreStreamingData"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStreamingDataForProfileUserAction.action?"+rparam;	
	callAjaxForProfilePage(jsObj,url);
}

/* function showStreamingData(results,jsObj)
{
	 $("#problemsDiv").children().remove();
	var status;
	if(results != null && results.length > 0)
	{		
		$(".placeholderCenterDiv").html('<h4 class=" breadcrumb" style="width:593px;"><span style="margin-left: -10px;"> Updates : </span></h4>');		
		for(var i in results)
		{	
			var postedDate = $('<div></div>');	
			postedDate.append('<span>'+results[i].postedDate+'</span>');
			postedDate.appendTo('.placeholderCenterDiv');
			var list = results[i].problemBeanVOList;
			for(var j in list)
			{
							
				if(list[j].responseType == "Problems")
				{
					var template = $('.problemTemplate');
					var templateClone = template.clone();
					templateClone.removeClass('problemTemplate');
					templateClone.find('.problemImg').html('<img src="pictures/profiles/'+list[j].userImageURL+'" class="thumbnail" height="40" width="40"/>');
					templateClone.find('.postedPersonName').html(''+list[j].postedPersonName+' Posted');
					templateClone.find('.problemTitle').html(''+list[j].problem+'');
					templateClone.find('.problemDescription').html(''+list[j].description+'');
					templateClone.find('.problemFromDate').html('Existing From: '+list[j].existingFrom+'');					
					templateClone.appendTo('.placeholderCenterDiv');
				}
				if(list[j].responseType == "Comments")
				{										
					var newDate = list[j].postDate;
					var yearStr = newDate.substring(0,4);
					var monStr = newDate.substring(5,7);
					var dayStr = newDate.substring(8,10);		
					var timeHrs = newDate.substring(11,13);	
					var timeMin = newDate.substring(14,16);
					var timesec = newDate.substring(17,19);
					var UserpostDate = (yearStr+"-"+monStr+"-"+dayStr+"  "+"Time: "+timeHrs+":"+timeMin+":"+timesec);
					var imageStr = "images/candidates/"+list[j].name;
					var template = $('.politicalReasonsTemplate');
					var templateClone = template.clone();
					templateClone.removeClass('politicalReasonsTemplate');
					templateClone.find('.postedBy').html('<img src="pictures/profiles/'+list[j].userImageURL+'" class="thumbnail" height="40" width="40"/>');
					
					if(list[j].problemImpactLevelId == 1)
						status = "winning";
					else
						status = "losing";
					templateClone.find('.headingCls').html('<h5>'+list[j].postedPersonName+'</h5> Posted a Political Reason for '+list[j].name+' '+status+' '+list[j].constituency+' '+list[j].problemType+' constituency');
					templateClone.find('.candidateImg').html('<img src="'+imageStr+'.jpg" onerror="setDefaultImage(this)" style="width:100px;height:100px;vertical-align:middle;"></img>');
					templateClone.find('.politicalReaCls').html('<b>Political Reason:</b> '+list[j].problem+'');
					templateClone.find('.politicalDescCls').html('<b>Description:</b> '+list[j].description+'');
					templateClone.find('.polReaPostedDate').html('Posted On: '+UserpostDate+'');
					templateClone.appendTo('.placeholderCenterDiv');
				}
			}
		}
	}
	else{
		$(".placeholderCenterDiv").children().remove();
		$(".placeholderCenterDiv").css('display','inline-block'); 
		$(".placeholderCenterDiv").html('<h4 class=" breadcrumb" style="width:593px;"><span style="margin-left: -10px;"> Updates : </span></h4><b> There are no Updates from <font style="text-transform: capitalize;">'+profileUserName+'  </font> <b>');	
	} 
} */
function showStreamingData(results,jsObj)
{
	
	var str = '';
	$("#headingDiv").html('<h4 class=" breadcrumb" style="width:593px;"><span style="margin-left: -10px;">Recent Updates : </span></h4>');
	if(results != null && results.length > 0)
	{
		for(var i in results)
		{
			fromDate = results[0].date;
			if(results[i].title == 'Problem')
			{
				
				str += '<div id="streemDiv'+i+'" class="row breadcrumb">';
				if(results[i].userImg != "")
				{
					str += '<img src="pictures/profiles/'+results[i].userImg+'" class="thumbnail span1" height="25" width="25" onerror="setDefaultImage(this)"/>';
				}
				else
				{
					str += '<img src="pictures/profiles/human.jpg" class="thumbnail span1" height="25" width="25" />';
				}
				str += '<div class="span11"><b style="color:royalblue"><span>'+results[i].commentedBy+'</span></b>';
				str += '<span style="float: right;"> '+results[i].date+'</span></div>';
				str += '<div class="span11 offset1">';
				str += '<a href="'+results[i].url+'" target="_blank"><b style="color:royalblue"><span>'+results[i].politicalDescription+'</span></b></a>';
				str += '<div class=""><b>Problem Title : </b> <span>'+results[i].problemTitle+' </span></br>';
				str += '<b>Description : </b> ';
				str += '<span>'+results[i].description+'    </span></br></div>';
				str += ' <b style="float:right;">Existing From: ';
				str += '<span> '+results[i].existinFrom+'</span></b>';
				
				str += '</div></div>';
			}
			if(results[i].title == 'Asses Politician')
			{
				
				str += '<div id="streemDiv'+i+'" class="row breadcrumb">';
				if(results[i].userImg != "")
				{
					str += '<img src="pictures/profiles/'+results[i].userImg+'" class="thumbnail span1" height="25" width="25" onerror="setDefaultImage(this)"/>';
				}
				else
				{
					str += '<img src="pictures/profiles/human.jpg" class="thumbnail span1" height="25" width="25" />';
				}
				str += '<div><b style="color:royalblue"><span>'+results[i].commentedBy+'</span></b>';
				str += '<span style="float: right;"> '+results[i].date+'</span></div>';
				str += '';
				str += '<div class="offset1 span11 "><b style="color:royalblue">'+results[i].description+'  </b></div>';
				str += '<div class="span11 offset1">';
				if(results[i].img != '')
				{
					str+= '<span><img src="images/candidates/'+results[i].img+'" class="thumbnail" height="40" width="40" onerror="setDefaultImage(this)"/></span>';
				}
				else
				{
					str+= '<span><img src="pictures/profiles/human.jpg" class="thumbnail" height="40" width="40"/></span>';
				}
				str += '<div  style="float: left; margin-left: 81px; margin-top: -50px;"><b>Political Reason : </b> <span>'+results[i].politicalReasion+' </span></br>';
				str += '<b>Description : </b> ';
				str += '<span>'+results[i].politicalDescription+'</span></div></br>';
				str += ' <b style="float:right;">Posted On: ';
				str += '<span style="float: right;"> '+results[i].date+'</span></b>';
				
				str += '</div></div>';
			}
			
			if(results[i].title == 'Subscriptions')
			{
				
				str += '<div id="streemDiv'+i+'" class="row breadcrumb" >';
				str += '<div class="row-fluid">';
				if(results[i].userImg != "")
				{
					str += '<img src="pictures/profiles/'+results[i].userImg+'" class="thumbnail span1" height="25" width="25" onerror="setDefaultImage(this)" />';
				}
				else
				{
					str += '<img src="pictures/profiles/human.jpg" class="thumbnail span1" height="25" width="25" />';
				}
				str += '<div class="span11"><b style="color:royalblue"><span>'+results[i].commentedBy+'</span></b>';
				str += '<span style="float: right;">'+results[i].date+'</span></div></div>';
				str += '<div class="span11 offset1">';
				str += '<div class="row-fluid">';
				if(results[i].subTitle == "Party")
				{
					if(results[i].img != "")
					{
						str += '<a href="'+results[i].url+'" target="_blank"><img src="images/party_flags/'+results[i].img+'" class="thumbnail span1" height="40" width="40" onerror="setDefaultImage(this)"/></a>';
					}
					else
					{
						str += '<a href="'+results[i].url+'" target="_blank"><img src="pictures/profiles/human.jpg" class="thumbnail span1" height="40" width="40" /></a>';
					}
				}
				else if(results[i].subTitle == "Special Page")
				{
					if(results[i].img != "")
					{
						str += '<a href="'+results[i].url+'" target="_blank"><img src="'+results[i].img+'" class="thumbnail span1" height="40" width="40" onerror="setDefaultImage(this)"/></a>';
					}
					else
					{
						str += '<a href="'+results[i].url+'" target="_blank"><img src="pictures/profiles/human.jpg" class="thumbnail span1" height="40" width="40" /></a>';
					}
				}
				else if(results[i].subTitle == "Politician")
				{
					if(results[i].img != "")
					{
						str += '<a href="'+results[i].url+'" target="_blank"><img src="images/candidates/'+results[i].img+'" class="thumbnail span1" height="40" width="40" onerror="setDefaultImage(this)"/></a>';
					}
					else
					{
						str += '<a href="'+results[i].url+'" target="_blank"><img src="pictures/profiles/human.jpg" class="thumbnail span1" height="40" width="40" /></a>';
					}
				}
				str += '<div class="span11"><p><a href="'+results[i].url+'" target="_blank">'+results[i].description+'</a></p></div></div></div></div>';
			}
			if(results[i].title == 'Favirote Links')
			{
				
				str += '<div id="streemDiv'+i+'" class="row  breadcrumb">';
				str += '<div class="row-fluid">';
				if(results[i].userImg != "")
				{
					str += '<img src="pictures/profiles/'+results[i].userImg+'" class="thumbnail span1" height="25" width="25" onerror="setDefaultImage(this)"/>';
				}
				else
				{
					str += '<img src="pictures/profiles/human.jpg" class="thumbnail span1" height="25" width="25" />';
				}
				str += '<div class="span11"><b style="color:royalblue"><span>'+results[i].commentedBy+'</span></b>';
				str += '<span style="float: right;">'+results[i].date+'</span></div></div>';
				str += '<div class="span11 offset1">';
				str += '<div class="row-fluid">';
				str += '<a href="'+results[i].url+'" target="_blank"><p>'+results[i].description+'</a></p></div></div></div>';
			}
			if(results[i].title == 'Friends')
			{
				str += '<div id="streemDiv'+i+'" class="row  breadcrumb">';
				str += '<div class="row-fluid">';
				if(results[i].userImg != "")
				{
					str += '<img width="25" height="25" src="images/candidates/human.jpg" class="thumbnail span1" onerror="setDefaultImage(this)">';
				}
				else
				{
					str += '<img width="25" height="25" src="pictures/profiles/human.jpg" class="thumbnail span1" />';
				}
				str += '<div class="span11"><b style="color:royalblue"><span>'+results[i].name+'</span></b>';
				str += '<span style="float: right;">'+results[i].date+'</span></div></div>';
				str += '<div class="span11 offset1">';
				str += '<div class="row-fluid">';
				if(results[i].img != "")
				{
					str += '<a href="'+results[i].url+'" target="_blank"><img src="pictures/profiles/'+results[i].img+'" class="thumbnail span1" height="40" width="40" onerror="setDefaultImage(this)"/></a>';
				}
				else
				{
					str += '<a href="'+results[i].url+'" target="_blank"><img src="pictures/profiles/human.jpg" class="thumbnail span1" height="40" width="40" /></a>';
				}
				
				str += '<div class="span11"><p><a href="'+results[i].url+'" target="_blank">'+results[i].description+'</a></p></div></div></div></div>';
			}
			
		}
		
	}
	else{
		//$(".placeholderCenterDiv").children().remove();
		//$(".placeholderCenterDiv").css('display','inline-block'); 
		str += '<h4 class=" " style="width:593px;"><span style="margin-left: -10px;"></span></h4><b>No more updates from <font style="text-transform: capitalize;">'+profileUserName+'  </font> <b>';	
		
	} 
	$(".placeholderCenterDiv").append(str);
	if(results.length > 0)
	{
		var btnStr = '';
		btnStr += '<div id="viewMoreDiv"><input  type="button" value = "View More" class="btn-info" onClick="viewMoreStreeming();"  style="width: 100px; height: 27px; border-radius: 4px 4px 4px 4px;"></input></div>';
		$('#moreButtonDiv').html(btnStr);
	}
	
	
}
function setDefaultImage(img)
{
		img.src = "images/candidates/human.jpg";
}

function showConnectStatus(results)
{
	if(results.resultCode == 0)
	{
		$(".connectStatusSpan").html('<span class="connectStatusSpan"><a class="btn-mini" href="javascript:{}" rel="tooltip" title="Pending"><i class="icon-adjust opacityFilter-50"></i>Request Pending</a></span>');
		return;
		
	}
	

}