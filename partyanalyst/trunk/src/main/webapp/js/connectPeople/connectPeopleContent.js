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
var connectedPeople = new Array();
var friendsCount=0;
var pendingCount=0;
var notConnectedCount=0;

function setDefaultImg(img){
	img.src="images/icons/connectPeople/member.png";
}

function setMemberImg(img){
	img.src="images/icons/constituencyPage/human1.png";
}

function buildConnectUsersContent(connectedPeopleData,divId,locationType,locationId,locationName,userLoginStatus,userLoginId){
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
	//bodyStr+='<span><img height="20" width="25" src="images/icons/constituencyPage/groups.png" style="margin-top: -15px;"></img></span>';
	bodyStr+='<h4 style="font-weight:normal; text-align:center;font-size:16px;padding:5px;"> '+connectedPeopleData.length+' people connected from <br/> '+locationName+' Constituency</h4>';
	bodyStr+='</div>';
	bodyStr+='<div id="connectedPersonsDiv"  class="btn" style="font-size:13px;font-family:verdana;text-transform:capitalize;">';
	bodyStr+='<table width="100%">';
	for(var i =0; i<connectedPeopleData.length; i++)
	{
		if(i == 3)
			break;
		bodyStr+='<tr>';
		bodyStr+='<td>';
		bodyStr+='<table width="100%" class="btn">';
		bodyStr+='<tr>';
		
		if(connectedPeopleData[i].image == null || connectedPeopleData[i].image == '')
			bodyStr+='<td rowspan="2" width="25%"><span><a href="userProfile.action?profileId='+connectedPeopleData[i].id+'"  target="_blank"><img height="40" width="35" src="pictures/profiles/'+connectedPeopleData[i].id+'.jpeg"  onerror="setMemberImg(this)"/></a></span></td>';
		else
			bodyStr+='<td rowspan="2" width="25%"><span><a href="userProfile.action?profileId='+connectedPeopleData[i].id+'"  target="_blank"><img height="40" width="35" src="pictures/profiles/'+connectedPeopleData[i].id+'.jpeg" onerror="setDefaultImg(this)"/></a></span></td>';
		bodyStr+='<td align="left"><span class="groupPersonNameSpanClass"><a href="userProfile.action?profileId='+connectedPeopleData[i].id+'"  target="_blank" style="text-transform: capitalize;">'+connectedPeopleData[i].candidateName+'</a></span></td>';
		bodyStr+='</tr>';
		bodyStr+='<tr>';	
		bodyStr+='<td align="right"><span class="groupPersonMessageSpanClass" style="margin-right:94px;">';
		if(userLoginStatus == "false")
			bodyStr+='<a href="connectPeopleAction.action?redirectLoc='+locationType+'&'+locationIdLabel+'='+locationId+'&'+locationNameLabel+'='+locationName+'"><i class="icon-plus-sign opacityFilter-50"></i></a>';
		else
		{
			if(connectedPeopleData[i].status == "LOGGED_USER")
				bodyStr+='<font color="#4A610B" style="padding-right:10px;">Logged User</font>';
			else if(connectedPeopleData[i].status == "NOT CONNECTED")
			{
				bodyStr+='<font color="#7F5A22" style="padding-right:10px;">Not Connected</font>';
				bodyStr+='- <a href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+connectedPeopleData[i].id+'\',\''+connectedPeopleData[i].candidateName+'\',\''+connectedPeopleData[i].constituencyName+'\',\''+userLoginId+'\',\''+locationId+'\',\''+locationType+'\',\''+locationName+'\')" style="margin-left:auto;"><i class="icon-plus-sign opacityFilter-50" title="Connect Now" rel="tooltip"></i></a>';
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
		bodyStr+='<span class="connectAncSpan" style="padding-right:10px;font-weight:bold;"> <a href="connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&'+locationIdLabel+'='+locationId+'&'+locationNameLabel+'='+locationName+'" class="connectAnc">Login</a> </span>';
		bodyStr+='<span class="connectAncSpan" style="padding-right:10px;font-weight:bold;"> <a href="freeUserRegistration.action" class="connectAnc">Register</a> </span>';
	}
	loginUserId=userLoginId;

	bodyStr+='<span class="connectAncSpan" style="font-weight:bold;"> <a href="javascript:{}" style="" onclick="showAllConnectPeopleWindow(\''+locationId+'\',\''+locationName+'\',\''+userLoginId+'\',\''+locationType+'\')" class="connectAnc btn">View All People</a> </span>';

	bodyStr+='</td>';
	bodyStr+='</tr></table>';
	bodyStr+='</div>';

	if(bodyElmt)
		bodyElmt.innerHTML=bodyStr;
}

function buildConnectUsersContentOfDistrict(connectedPeopleData,divId,locationType,locationId,locationName,userLoginStatus,userLoginId, totalCount)
{
	friendsCount=0;
	pendingCount=0;
	notConnectedCount=0;
	for(var p=0;p<connectedPeopleData.length;p++){
		if(connectedPeopleData[p].status == "CONNECTED")
			++friendsCount;
		if(connectedPeopleData[p].status == "PENDING")
			++pendingCount;
		if(connectedPeopleData[p].status == "NOT CONNECTED")
			++notConnectedCount;
	}	
	connectedPeople=connectedPeopleData;
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
	if(userLoginStatus == "false")
		bodyStr=$("#districtPeopleConnectData_body").html();
	
	//Connect Member Start
	bodyStr+='<div id="connectMember" class="Connect-Member">';
	bodyStr+='<div style="float:left;"><h4>'+totalCount+' people connected from '+locationName+' District</h4></div>';

	bodyStr+='<div class="memberfilter">';		
	
	bodyStr+='<a id="connTab" href="javascript:{}" onclick="membersList(\''+locationType+'\', \''+locationId+'\', \''+locationName+'\', \''+userLoginStatus+'\', \''+userLoginId+'\', \'all\', this.id)" class="activefilter">Connected</a>';
	
	if(userLoginStatus == "true"){
			bodyStr+='<a id="friendTab" href="javascript:{}" onclick="membersList(\''+locationType+'\', \''+locationId+'\', \''+locationName+'\', \''+userLoginStatus+'\', \''+userLoginId+'\', \'connected\', this.id);">Friends</a>';
			bodyStr+='<a id="pendingTab" href="javascript:{}" onclick="membersList(\''+locationType+'\', \''+locationId+'\', \''+locationName+'\', \''+userLoginStatus+'\', \''+userLoginId+'\', \'pending\', this.id)">Pending</a>';
			bodyStr+='<a id="notConnTab" href="javascript:{}" onclick="membersList(\''+locationType+'\', \''+locationId+'\', \''+locationName+'\',\''+userLoginStatus+'\', \''+userLoginId+'\', \'notConnected\', this.id)">Not Connected</a>';
	}	
	bodyStr+='<a id="viewAllTab" href="javascript:{}" onclick="showAllConnectPeopleWindowOfDistrict(\''+locationId+'\',\''+locationName+'\',\''+userLoginId+'\',\''+locationType+'\', this.id);">View All</a>';
	bodyStr+='</div>';	

	bodyStr+='<div id="membersList">';
	bodyStr+='<ul class="memberslist">';
	for(var i =0; i<connectedPeopleData.length; i++){
		if(i == 5)
			break;
		bodyStr+='<div style="float: left; word-wrap: break-word; width: 105px;">';
		bodyStr+='<li>';
	
			if(connectedPeopleData[i].image == null || connectedPeopleData[i].image == '')
				bodyStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeopleData[i].id+'"><img src="images/icons/connectPeople/member.png" /></a>';
			else
				bodyStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeopleData[i].id+'"><img src="pictures/profiles/'+connectedPeopleData[i].image+'" style="width:75px;height:75px;" onerror="setDefaultImg(this)"/></a>';
			
			bodyStr+='<span class="membercard"><h3 id="connectDiv" style="height:30px;background:none repeat scroll 0 0 inactivecaptiontext;"><a target="_blank" href="userProfile.action?profileId='+connectedPeopleData[i].id+'">'+connectedPeopleData[i].candidateName+'</a></span></h3>';
					
			bodyStr+='<a href=# class="sociallinks">Friends (<b>'+connectedPeopleData[i].noOfFriends+'</b>)</a>';
			bodyStr+='<a href=# class="sociallinks">Problems Posted(<b>'+connectedPeopleData[i].noOfPosts+'</b>)</a>';
		if(userLoginStatus == "false"){
			bodyStr+='<a href="connectPeopleAction.action?redirectLoc='+locationType+'&'+locationIdLabel+'='+locationId+'&'+locationNameLabel+'='+locationName+'" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connect Now</a>';
		}
		else{		
			if(connectedPeopleData[i].status == "LOGGED_USER")
				bodyStr+='<a href="#" class="ConnectNow">Logged User</a>';
			else if(connectedPeopleData[i].status == "NOT CONNECTED"){
				bodyStr+='<a href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+connectedPeopleData[i].id+'\',\''+connectedPeopleData[i].candidateName+'\',\''+connectedPeopleData[i].constituencyName+'\',\''+userLoginId+'\',\''+locationId+'\',\''+locationType+'\',\''+locationName+'\')" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connect Now</a>';
			}
			else if(connectedPeopleData[i].status == "CONNECTED")
				bodyStr+='<a href="#" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connected</a>';
			else if(connectedPeopleData[i].status == "PENDING")
					bodyStr+='<a href="#" class="ConnectNow"><label class="connectsymbol">&infin;</label>Request Pending</a>';
		}		
		bodyStr+='</li>';
		bodyStr+='<span style="clear: left;text-transform: capitalize; font-size: 12px; text-align: center; font-family: arial; font-weight: bold; color: #272582;"><a target="_blank" href="userProfile.action?profileId='+connectedPeopleData[i].id+'">'+connectedPeopleData[i].candidateName+'</a></span>';
		bodyStr+='</div>';
	}
	if(connectedPeopleData.length>5)
		bodyStr+='<div style="float: right; clear:left;font-size: 15px; margin-bottom: 5px; margin-top: 10px; font-family: serif;"><a href="javascript:{}" style="padding: 3px; text-decoration: none; color: white; border-radius: 5px 5px 5px 5px; background: #CC4F6E;" onclick="showAllConnectPeopleWindowOfDistrict(\''+locationId+'\',\''+locationName+'\',\''+userLoginId+'\',\''+locationType+'\', this.id);">View More</a></div>';
	bodyStr+='</ul>';
	
	bodyStr+='</div>';

	bodyStr+='</div>';
	//Connect Member End

	loginUserId=userLoginId;	
		
	if(bodyElmt)
		bodyElmt.innerHTML=bodyStr;
		
	if(totalCount==0){
		$("#connectMember").css("font-weight", "bold");
		$("#connectMember").css("font-size", "14px");
		if(userLoginStatus=="false")
			$("#connectMember").html('No people connected from this district yet..Please register and invite your friends too..');
		else
			$("#connectMember").html('No people connected from this district yet..Please invite your friends to register..');		
	}

}

function membersList(locationType,locationId,locationName,userLoginStatus,userLoginId, userRelation, elemId){
	$("a").removeClass("activefilter");
	$("#"+elemId).addClass("activefilter");
		var memberStr='';	
		if(userRelation=="all"){
			memberStr+='<ul class="memberslist">';
			for(var i =0; i<connectedPeople.length; i++){
				if(i == 5)
					break;
				memberStr+='<div style="float: left; word-wrap: break-word; width: 105px;">';		
				memberStr+='<li>';
				if(connectedPeople[i].image == null || connectedPeople[i].image == '')
					memberStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'"><img src="images/icons/connectPeople/member.png" /></a>';
				else
					memberStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'"><img src="pictures/profiles/'+connectedPeople[i].image+'" style="width:75px;height:75px;" onerror="setDefaultImg(this)"/></a>';

				memberStr+='<span class="membercard"><h3 id="connectDiv" style="height:30px;background:none repeat scroll 0 0 inactivecaptiontext;"><a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'">'+connectedPeople[i].candidateName+'</a></span></h3>';
						
				memberStr+='<a href=# class="sociallinks">Friends (<b>'+connectedPeople[i].noOfFriends+'</b>)</a>';
				memberStr+='<a href=# class="sociallinks">Problems Posted(<b>'+connectedPeople[i].noOfPosts+'</b>)</a>';

				if(userLoginStatus=="false"){
					memberStr+='<a href="connectPeopleAction.action?redirectLoc='+locationType+'&'+locationIdLabel+'='+locationId+'&'+locationNameLabel+'='+locationName+'" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connect Now</a>';
				}
				else{
					if(connectedPeople[i].status == "LOGGED_USER")
						memberStr+='<a href="#" class="ConnectNow">Logged User</a>';
					else if(connectedPeople[i].status == "NOT CONNECTED"){
						memberStr+='<a href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+connectedPeople[i].id+'\',\''+connectedPeople[i].candidateName+'\',\''+connectedPeople[i].constituencyName+'\',\''+userLoginId+'\',\''+locationId+'\',\''+locationType+'\',\''+locationName+'\')" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connect Now</a>';
					}
					else if(connectedPeople[i].status == "CONNECTED")
						memberStr+='<a href="#" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connected</a>';
					else if(connectedPeople[i].status == "PENDING")
						memberStr+='<a href="#" class="ConnectNow"><label class="connectsymbol">&infin;</label>Request Pending</a>';
				}
				memberStr+='</li>';
				memberStr+='<span style="clear: left;text-transform: capitalize; font-size: 12px; text-align: center; font-family: arial; font-weight: bold; color: #272582;"><a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'">'+connectedPeople[i].candidateName+'</a></span>';
				memberStr+='</div>';
			}
			if(connectedPeople.length>5)
				memberStr+='<div style="float: right; clear:left;font-size: 15px; margin-bottom: 5px; margin-top: 10px; font-family: serif;"><a href="javascript:{}" style="padding: 3px; text-decoration: none; color: white; border-radius: 5px 5px 5px 5px; background: #CC4F6E;" onclick="showAllConnectPeopleWindowOfDistrict(\''+locationId+'\',\''+locationName+'\',\''+userLoginId+'\',\''+locationType+'\', this.id);">View More</a></div>';
			memberStr+='</ul>';			
		}
		
		if(userRelation=="connected"){			
			var count=0;
			memberStr+='<ul class="memberslist">';
			for(var i =0; i<connectedPeople.length; i++){
				if(count == 5)
					break;
				
				if(connectedPeople[i].status == "CONNECTED"){
					memberStr+='<div style="float: left; word-wrap: break-word; width: 105px;">';
					memberStr+='<li>';
					++count;
					if(connectedPeople[i].image == null || connectedPeople[i].image == '')
						memberStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'"><img src="images/icons/connectPeople/member.png" /></a>';
					else
						memberStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'"><img src="pictures/profiles/'+connectedPeople[i].image+'" style="width:75px;height:75px;" onerror="setDefaultImg(this)"/></a>';

					memberStr+='<span class="membercard"><h3 id="connectDiv" style="height:30px;background:none repeat scroll 0 0 inactivecaptiontext;"><a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'">'+connectedPeople[i].candidateName+'</a></span></h3>';
							
					memberStr+='<a href=# class="sociallinks">Friends (<b>'+connectedPeople[i].noOfFriends+'</b>)</a>';
					memberStr+='<a href=# class="sociallinks">Problems Posted(<b>'+connectedPeople[i].noOfPosts+'</b>)</a>';
					memberStr+='<a href="#" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connected</a>';
					memberStr+='</li>';
					memberStr+='<span style="clear: left;text-transform: capitalize; font-size: 12px; text-align: center; font-family: arial; font-weight: bold; color: #272582;"><a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'">'+connectedPeople[i].candidateName+'</a></span>';
					memberStr+='</div>';
				}
				
			}
			if(friendsCount>5)
				memberStr+='<div style="float: right; clear:left;font-size: 15px; margin-bottom: 5px; margin-top: 10px; font-family: serif;"><a href="javascript:{}" style="padding: 3px; text-decoration: none; color: white; border-radius: 5px 5px 5px 5px; background: #CC4F6E;" onclick="showAllConnectPeopleWindowOfDistrict(\''+locationId+'\',\''+locationName+'\',\''+userLoginId+'\',\''+locationType+'\', \'friends\');">View More</a></div>';
			memberStr+='</ul>';	
			if(count==0){
				memberStr='<ul class="memberslist">';
				memberStr+='You haven\'t yet connected to anyone from this district';
				memberStr+='</ul>';
			}
		}
		if(userRelation=="pending"){
			var count=0;
			memberStr+='<ul class="memberslist">';
			for(var i =0; i<connectedPeople.length; i++){
				if(count == 5)
					break;
				if(connectedPeople[i].status == "PENDING"){
					memberStr+='<div style="float: left; word-wrap: break-word; width: 105px;">';
					memberStr+='<li>';
					++count;
					if(connectedPeople[i].image == null || connectedPeople[i].image == '')
						memberStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'"><img src="images/icons/connectPeople/member.png" /></a>';
					else
						memberStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'"><img src="pictures/profiles/'+connectedPeople[i].image+'" style="width:75px;height:75px;" onerror="setDefaultImg(this)"/></a>';

					memberStr+='<span class="membercard"><h3 id="connectDiv" style="height:30px;background:none repeat scroll 0 0 inactivecaptiontext;"><a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'">'+connectedPeople[i].candidateName+'</a></span></h3>';
							
					memberStr+='<a href=# class="sociallinks">Friends (<b>'+connectedPeople[i].noOfFriends+'</b>)</a>';
					memberStr+='<a href=# class="sociallinks">Problems Posted(<b>'+connectedPeople[i].noOfPosts+'</b>)</a>';
					memberStr+='<a href="#" class="ConnectNow"><label class="connectsymbol">&infin;</label>Request Pending</a>';
					memberStr+='</li>';
					memberStr+='<span style="clear: left;text-transform: capitalize; font-size: 12px; text-align: center; font-family: arial; font-weight: bold; color: #272582;"><a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'">'+connectedPeople[i].candidateName+'</a></span>';
					memberStr+='</div>';
				}
			}
			if(pendingCount>5)
				memberStr+='<div style="float: right;clear:left; font-size: 15px; margin-bottom: 5px; margin-top: 10px; font-family: serif;"><a href="javascript:{}" style="padding: 3px; text-decoration: none; color: white; border-radius: 5px 5px 5px 5px; background: #CC4F6E;" onclick="showAllConnectPeopleWindowOfDistrict(\''+locationId+'\',\''+locationName+'\',\''+userLoginId+'\',\''+locationType+'\', \'pending\');">View More</a></div>';
			memberStr+='</ul>';		
			if(count==0){
				memberStr='<ul class="memberslist">';
				memberStr+='You haven\'t yet connected to anyone from this district';
				memberStr+='</ul>';
			}
		}
		if(userRelation=="notConnected"){
			var count=0;
			memberStr+='<ul class="memberslist">';
			for(var i =0; i<connectedPeople.length; i++){
				if(count == 5)
					break;
				if(connectedPeople[i].status == "NOT CONNECTED"){
					memberStr+='<div style="float: left; word-wrap: break-word; width: 105px;">';
					memberStr+='<li>';
					++count;
					if(connectedPeople[i].image == null || connectedPeople[i].image == '')
						memberStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'"><img src="images/icons/connectPeople/member.png" /></a>';
					else
						memberStr+='<a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'"><img src="pictures/profiles/'+connectedPeople[i].image+'" style="width:75px;height:75px;" onerror="setDefaultImg(this)"/></a>';

					memberStr+='<span class="membercard"><h3 id="connectDiv" style="height:30px;background:none repeat scroll 0 0 inactivecaptiontext;"><a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'">'+connectedPeople[i].candidateName+'</a></span></h3>';
							
					memberStr+='<a href=# class="sociallinks">Friends (<b>'+connectedPeople[i].noOfFriends+'</b>)</a>';
					memberStr+='<a href=# class="sociallinks">Problems Posted(<b>'+connectedPeople[i].noOfPosts+'</b>)</a>';
					memberStr+='<a href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+connectedPeople[i].id+'\',\''+connectedPeople[i].candidateName+'\',\''+connectedPeople[i].constituencyName+'\',\''+userLoginId+'\',\''+locationId+'\',\''+locationType+'\',\''+locationName+'\')" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connect Now</a>';
					memberStr+='</li>';
					memberStr+='<span style="clear: left;text-transform: capitalize; font-size: 12px; text-align: center; font-family: arial; font-weight: bold; color: #272582;"><a target="_blank" href="userProfile.action?profileId='+connectedPeople[i].id+'">'+connectedPeople[i].candidateName+'</a></span>';
					memberStr+='</div>';
				}
			}
			if(notConnectedCount>5)
				memberStr+='<div style="float: right; clear:left; font-size: 15px; margin-bottom: 5px; margin-top: 10px; font-family: serif;"><a href="javascript:{}" style="padding: 3px; text-decoration: none; color: white; border-radius: 5px 5px 5px 5px; background: #CC4F6E;" onclick="showAllConnectPeopleWindowOfDistrict(\''+locationId+'\',\''+locationName+'\',\''+userLoginId+'\',\''+locationType+'\', \'notConnected\');">View More</a></div>';
			memberStr+='</ul>';		
			if(count==0){
				memberStr='<ul class="memberslist">';
				memberStr+='You haven\'t yet connected to anyone from this district';
				memberStr+='</ul>';
			}
		}	
	$("#membersList").html(memberStr);
}

function showConnectConfirmDialogBox(userId,userName,constituency,userLoginId,locationId,locationType,locationName)
{
	if(userLoginId != null && userLoginId.length > 0)
	{
		var str = '';	
		str += '<table width="100%">';
		str += '<tr>';
		str += '<td width="75%">';
		str += '<div class="connectPeople_body_name">'+userName+'</div><div class="connectPeople_body_constituency">'+constituency+'</div>';
		str += '</td>';
		str += '<td rowspan="2" align="center" width="25%">';
		str += '<img height="100" width="95" src="images/icons/indexPage/human.jpg">';
		str	+= '<div id="connectButtonDiv"><input type="button" class="connectButton" onclick="doConnectPeople(\''+userId+'\',\''+userLoginId+'\',\''+locationId+'\',\''+locationType+'\',\''+locationName+'\')" value="Connect"/></div>';
		str += '</td>';
		str += '</tr>';
		str += '<tr>';
		str += '<td width="75%"><fieldset id="connectUserMsgFieldSet"><legend>Message</legend>';
		str += '<textarea id="connectUserMsg" onkeyup="limitText(\'connectUserMsg\',\'maxcount\',200)" rows="3" cols="38" style="background:none;"></textarea>';
		str +='<div id="limitDiv">';
		str +='<table style="width:100%;"><tr>';
		str +='<td align="left" style="width:50%;color:#4B4242;"><div id="remainChars" style="margin-top: 4px;"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
		str +='<td align="right" style="width:50%;color:#4B4242;"><div>Max 200 chars</div></td>';
		str +='</tr></table>';
		str +='</div>';	
		str += '</fieldset></td>';
		str += '</tr>';
		str += '</table>';
		str	+= '<div id="connectStatus"></div>';	
		str	+= '</div>';
		
		$( "#connectPeoplePopup" ).dialog({
				title:"Connect To "+userName,
				autoOpen: false,
				show: "blind",
				width: 500,
				minHeight:300,
				modal: true,
				hide: "explode"
			});
		
		var elmt = document.getElementById("allConnectedUsersDisplay_main");
		if(elmt)
			elmt.innerHTML = str;
		$( "#connectPeoplePopup" ).dialog("open");
	}
	else{
	ifNotLogin(userName);
	}
}

function ifNotLogin(userName){
$('#Not_connectPeople_body_name').css("display","block");
		$('#Not_connectPeople_body_name').dialog({
				title:"<span style='text-transform: capitalize;'>Connect To "+userName+"</span>",
				show: "blind",
				width: 500,
				minHeight:200,
				modal: true
		});
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
	if(jsObj.locationType=="DISTRICT")
		callAjax(jsObj, url);
	else
		callAjaxConnect(jsObj,url);
}
function callAjaxConnect(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);	
				
					if(jsObj.task == "connectToUser")
					{
						closeConnectPanel(jsObj,results);
					}
					if(jsObj.task == "connectUserSet")
					{
						showAllConnectedUsersStatus(jsObj,results);
					}
					
			}catch (e) {   		
			// alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     		//	alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
function closeConnectPanel(jsObj,results)
{ 
	var elmt = document.getElementById("connectStatus");
	var buttonElmt = document.getElementById("connectButtonDiv");
	
	if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{
		buttonElmt.innerHTML = '';
		elmt.innerHTML = '<blink><font color="green" style="font-weight:bold;"> Request sent successfully..</font></blink>';

		if(jsObj.locationType=="DISTRICT"){
			buildConnectUsersContentOfDistrict(results.candidateVO,connectDivId,jsObj.locationType,jsObj.locationId,jsObj.locationName,connectUserLoginStatus,jsObj.userId, results.totalResultsCount);	
		}
		else{
			buildConnectUsersContent(results.candidateVO,connectDivId,jsObj.locationType,jsObj.locationId,jsObj.locationName,connectUserLoginStatus,jsObj.userId);	
		}
			
			var t=setTimeout("closeConnectPopup()",2000);
	}
	else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
		elmt.innerHTML = '<font color="red" style="font-weight:bold;"><blink> Request cannot be sent due to some technical difficulty.</blink></font>';
		
}

function closeConnectPopup()
{
	var elmt = document.getElementById("connectPeoplePopup_outer");
	if(!elmt)
		return;
	var str = '<div id="connectPeoplePopup"></div>';

	elmt.innerHTML = str;
}
var connectedConstId;
function showAllConnectPeopleWindow(locationId,locationName,userLoginId,locationType)
{	
	connectedConstId = locationId;
	connetLocationType = locationType;	
	var str = '';
	str += '<div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div>';
		
	$( "#connectPeoplePopup" ).dialog({
			title:"People Connected to "+locationName+" "+locationType,
			autoOpen: false,
			show: "blind",
			width: 510,
			minHeight:400,
			modal: true,
			hide: "explode"
		});

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
		resultsCount:9,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllConnectedUsersInPanel(jsObj,results);
		}
	});
	custom_paginator.initialize();
}


function showAllConnectPeopleWindowOfDistrict(locationId,locationName,userLoginId,locationType, elemId)
{
	if(elemId == "viewAllTab"){
		$("a").removeClass("activefilter");
		$("#"+elemId).addClass("activefilter");
	}
	
	connetLocationType = locationType;	

	var str = '';
	str += '<div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div>';
		
	$( "#connectPeoplePopup" ).dialog({
			title:"People Connected to "+locationName+" "+locationType,
			autoOpen: false,
			show: "blind",
			width: 810,
			minHeight:500,
			modal: true,
			hide: "explode"
		});
		
	var connectConstiArray = new Array();	
	
	if(locationType == "DISTRICT"){		
		for(var consti=0; consti<constituencies.length;consti++)
			connectConstiArray.push(constituencies[consti].id);			
	}
	
	var textValue = "";	
	var connectStatusSelectElmtText = null;

	if(elemId == "friends")	
		connectStatusSelectElmtText = 'connected';
	else if(elemId=="pending")
		connectStatusSelectElmtText = 'pending';
	else if(elemId=="notConnected")
		connectStatusSelectElmtText = 'not connected';
	else
		connectStatusSelectElmtText = 'All'; 


	if(elemId!="" && elemId!="viewAllTab"){
		var jsObj ={
			constituencyIds:connectConstiArray,				
			statusText:connectStatusSelectElmtText,
			nameString:textValue,
			locationName:locationName,
			userId:userLoginId,
			locationType:locationType,			
			task:"getAllConnectedUsersByFilterView"
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllConnectedUserByFilterViewAction.action?"+rparam;
	}	
	else{
		var jsObj ={
			locationId:locationId,		
			locationName:locationName,
			userId:userLoginId,
			locationType:locationType,
			task:"getAllConnectedUsers"
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllConnectedUserAction.action?"+rparam;
	}

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:9,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showAllConnectedUsersInPanelOfDistrict(jsObj,results);
		}
	});
	custom_paginator.initialize();
}

function showAllConnectedUsersInPanel(jsObj, results){
	var str = '';	
	var elmt = document.getElementById("allConnectedUsersDisplay_main");
	var totalResultsCount = results.constituencyUsersCount;
	var connectedPeopleCount = results.connectedPeopleCount;
	var notConnectedPeopleCount;	
	if(loginconstiId != connectedConstId)
		notConnectedPeopleCount = totalResultsCount - connectedPeopleCount;		
	else 
		notConnectedPeopleCount = (totalResultsCount - connectedPeopleCount-1);

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
		$( "#connectPeoplePopup" ).dialog("open");
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
		str += '<th><select id="connectConstituencySelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\', \''+jsObj.userId+'\')">';
		str += '<option value="0">All</option>';
		for(var consti=0; consti<constituencies.length;consti++)
			str += '<option value="'+constituencies[consti].id+'">'+constituencies[consti].name+'</option>';
		str += '</select></th>';
		
	if(jsObj.userId != ""){
		str += '<th>By Status:</th>';
		str += '<th><select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\', \''+jsObj.userId+'\')">';
		str += '<option value="0">All</option>';
		for(var status=0; status<connectStatus.length;status++)
			str += '<option value="'+connectStatus[status].id+'">'+connectStatus[status].name+'</option>';
		str += '</select></th>';
	}
	}
	if(jsObj.locationType == "CONSTITUENCY"){
	str += '<th>By Status:</th>';
		str += '<th><select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\', \''+jsObj.userId+'\')">';
		str += '<option value="0"> All </option>';
		if(jsObj.userId != ""){
			str += '<option value="1"> CONNECTED </option>';
			str += '<option value="3"> PENDING </option>';
			str += '<option value="9"> Not CONNECTED</option>';
		}
		str += '</select></th>';	
	}
	
	str += '<th>By Name</th>'; 
	str += '<td><input type="text" id="connectStatusTextBox" onkeyup="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\', \''+jsObj.userId+'\')"></td>';
	str += '</tr>';
	str += '</table>';
	
	str += '</div>';	
	str += '</div>';

	str	+= '<div id="allConnectPeople_body">';	
	str += buildAllConnectUserString(users);
	str	+= '</div>';
	
	str	+= '<div id="allConnectPeople_footer" style="display:none;font: 13px trebuchet MS;">';
	str	+= '<table width="100%" style="margin-top: 9px;">';
	str	+= '<tr>';	
	str += '<td width="80%" style="color:#754815"> Do you want to connect to <span id="allConnectPeople_footer_span"></span> ?</td>';
	str += '<td width="20%" align="right">';
	str += '<div class="ConnectStatusButtonClass" style="width: 144px;margin-bottom: 5px;" onclick="connectUserSetPeople()">';
	str += '<table style="margin-top: 11px;">';
	str += '<tr>';
	str += '<td><span style="margin-right:5px">Connect Now </span></td>';
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
	str += '<div style="margin-top: 7px;"><textarea style="background:white;" id="AllConnectUserMsg" onkeyup="limitText(\'AllConnectUserMsg\',\'maxcount\',200)" rows="3" cols="40"></textarea></div>';
	str +='	<div id="limitDiv">';
	str +='	<table style="width:100%;"><tr>';
	str +='	<td align="left" style="width:38%;color:#4B4242;"><div id="remainChars" style="margin-top: 4px;"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
	str +='	<td style="width:50%;color:#4B4242;"><div>Max 200 chars</div></td>';
	str +='	</tr></table>';
	str +='	</div>';	
	str += '</td>';
	str += '<td width="20%" align="right" valign="top">';
	str += '<div class="ConnectStatusButtonClass" onclick="hideConfirmDiv()" style="width: 144px;margin-bottom: 5px;">';
	str += '<table>';
	str += '<tr>';
	str += '<td>Connect Later </td>';
	str += '<td><a style="margin: 0px 10px;" href="javascript:{}"><img style="border:none;" src="images/icons/reject.png"></img></a></td>';
	str += '</tr>';
	str += '</table>';
	str += '<div>';
	str += '</td>';
	
	str += '</tr>';
	str += '<tr>';
	str += '<td >';
	str += '	<div id="allConnectPeopleStatusTextDiv"></div>';
	str += '</td>';
	str += '<td></td>';
	str += '</tr>';
	str	+= '</table>';
	str	+= '</div>';
	
	elmt.innerHTML = str;
	$( "#connectPeoplePopup" ).dialog("open");
}

function showAllConnectedUsersInPanelOfDistrict(jsObj, results)
{
	var str = '';	
	var elmt = document.getElementById("allConnectedUsersDisplay_main");
	var totalResultsCount = results.constituencyUsersCount;
	var connectedPeopleCount = results.connectedPeopleCount;
	var notConnectedPeopleCount ='';
		if (userDistrictId == districtId)
			notConnectedPeopleCount = (totalResultsCount - connectedPeopleCount)-1;
		else
			notConnectedPeopleCount = (totalResultsCount - connectedPeopleCount);

	var users = results.candidateVO;

	if(results.resultStatus.exceptionEncountered != null || results.resultStatus.resultCode !=0)
	{
		str	+= '<div> Data Not Found Due To Some Exception</div>';
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
	str += '<div class="allConnectPeople_Head_content" style="width:790px;">';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<td align="left">Total People - '+totalResultsCount+'</td>';

	if(jsObj.userId != ""){
		str += '<td align="left">Friends - '+connectedPeopleCount+'</td>';
		str += '<td align="left">Not Connected - '+notConnectedPeopleCount+'</td>';
	}

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
		str += '<th><select id="connectConstituencySelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\', \''+jsObj.userId+'\')">';
		str += '<option value="0">All</option>';
		for(var consti=0; consti<constituencies.length;consti++)
			str += '<option value="'+constituencies[consti].id+'">'+constituencies[consti].name+'</option>';
		str += '</select></th>';
	}
	if(jsObj.userId != ""){
		str += '<th>By Status:</th>';
		str += '<th><select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\', \''+jsObj.userId+'\')">';
		str += '<option value="0">All</option>';
		for(var status=0; status<connectStatus.length;status++)
			str += '<option value="'+connectStatus[status].id+'">'+connectStatus[status].name+'</option>';
		str += '</select></th>';
	}
	str += '<th>By Name</th>'; 
	str += '<td><input type="text" id="connectStatusTextBox" onkeyup="getAllConnectedUsersByFilterView(\''+jsObj.locationType+'\', \''+jsObj.userId+'\')"></td>';
	str += '</tr>';
	str += '</table>';
	
	str += '</div>';	
	str += '</div>';
	str	+= '<div id="allConnectPeople_body" style="width:795px;">';	
		str += buildAllConnectUserStringOfDistrict(users);
	str	+= '</div>';
	
	str	+= '<div id="allConnectPeople_footer" style="display:none;font: 13px trebuchet MS;">';

	str	+= '<table width="100%" style="margin-top: 9px;">';
	str	+= '<tr>';	
	str += '<td width="80%" style="color:#754815"> Do you want to connect to <span id="allConnectPeople_footer_span"></span> ?  <span id="searchAjaxImageSpan" style="display:none;margin-left: 294px; margin-top: -17px;clear: both;"><img src="images/icons/search.gif"  width="18px" height="18px;"></img></span></td>';
	str += '<td width="20%" align="right">';
	str += '<div class="ConnectStatusButtonClass" onclick="connectUserSetPeople()" style="width: 144px;margin-bottom: 5px;">';
	str += '<table>';
	str += '<tr>';
	str += '<td><span style="margin-right:5px">Connect Now </span></td>';
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
	str += '<div style="margin-top: 7px;"><textarea style="background:white;" id="AllConnectUserMsg" onkeyup="limitText(\'AllConnectUserMsg\',\'maxcount\',200)" rows="3" cols="40"></textarea></div>';
	str +='	<div id="limitDiv">';
	str +='	<table style="width:100%;"><tr>';
	str +='	<td align="left" style="width:50%;color:#4B4242;"><div id="remainChars" style="margin-top: 4px;"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
	str +='	<td align="right" style="width:50%;color:#4B4242;"><div>Max 200 chars</div></td>';
	str +='	</tr></table>';
	str +='	</div>';	
	str += '</td>';
	str += '<td width="20%" align="right" valign="top">';
	str += '<div class="ConnectStatusButtonClass" onclick="hideConfirmDiv()" style="width: 144px;margin-bottom: 5px;">';
	str += '<table>';
	str += '<tr>';
	str += '<td>Connect Later </td>';
	str += '<td><a style="margin: 0px 10px;" href="javascript:{}"><img style="border:none;" src="images/icons/reject.png"></img></a></td>';
	str += '</tr>';
	str += '</table>';
	str += '<div>';
	str += '</td>';
	
	str += '</tr>';
	str += '<tr>';
	str += '<td >';
	str += '	<div id="allConnectPeopleStatusTextDiv"></div>';
	str += '</td>';
	str += '<td></td>';
	str += '</tr>';
	str	+= '</table>';
	str	+= '</div>';
	
	elmt.innerHTML = str;
	
	if(jsObj.statusText){
		var index=0;
		if(jsObj.statusText=='connected')
			index=1;
		if(jsObj.statusText=='pending')
			index=2;
		if(jsObj.statusText=='not connected')
			index=3;
		$('#connectStatusSelect option').eq(index).attr('selected', 'selected');
		$("#filterViewContent").slideToggle();		
	}

	$("#connectPeoplePopup").dialog("open");
}

function connectUserSetPeople(){
	
	var statusElmt = document.getElementById("allConnectPeopleStatusTextDiv");

	var users = new Array();

	if(connectRequestedUserId == "")
		return;
	
	users.push(connectRequestedUserId);

	var divId = "connectPeopleButtonDivId_"+connectRequestedUserId;
	var divLeftId = "connectPeopleButtonLeftDivId_"+connectRequestedUserId;
	var divRightId = "connectPeopleButtonRightDivId_"+connectRequestedUserId;

	if(users.length == 0)
	{
		statusElmt.innerHTML = '<font color="red">Atleast One User Has To Selected To Connect</font>';
		return;
	}
	else
		statusElmt.innerHTML = '';

	var connectTextAreaElmt = document.getElementById("AllConnectUserMsg");
	var connectMsg = connectTextAreaElmt.value;

	document.getElementById("allConnectPeople_footer").style.display="none";
	
	document.getElementById(divLeftId).innerHTML = "";
	document.getElementById(divId).innerHTML = "PENDING";
	document.getElementById(divRightId).innerHTML = "";
	
	if(connetLocationType=="DISTRICT"){
		var jsObj ={
				connetLocationId:districtId,				
				connectUserIds:users,
				connectMessage:connectMsg,
				userId:connectUserLoginId,
				locationType:connetLocationType,
				task:"connectUserSet"
			 };
	}
	else{
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
	
	callAjaxConnect(jsObj,url);
}

function hideConfirmDiv()
{
	var elmt = document.getElementById("allConnectPeople_footer");

	if(elmt)
		$("#allConnectPeople_footer").slideUp();
}

function showConfirmDiv(name,id)
{
	if(LoginUserID !=null && LoginUserID != '' && LoginUserID.length >0)
	{
		var elmt = document.getElementById("allConnectPeople_footer");
		var spanElmt = document.getElementById("allConnectPeople_footer_span");

		if(elmt)
			$("#allConnectPeople_footer").slideDown();
		connectRequestedUserId = id;
		if(spanElmt)
			spanElmt.innerHTML = name; 
	}
	else{
	ifNotLogin(name);
	}
}


function buildAllConnectUserString(users){
var str = '';
	for(var i=0; i<users.length; i++)
	{
		str += '<div class="allConnectPeopleDataDiv_main">';
		str += '<table width="100%">';
		str += '<tr>';

		var imageStr = "pictures/profiles/"+users[i].image;
		if(users[i].image == null)
			str += '<td valign="top" width="15%"><a href="userProfile.action?profileId='+users[i].id+'" target="_blank"><img height="45" width="50" src="images/icons/indexPage/human.jpg"></a></td>';
		else
			str += '<td valign="top" width="15%"><a href="userProfile.action?profileId='+users[i].id+'" target="_blank"><img height="45" width="50" src="'+imageStr+'" onerror="setDefaultImg(this)"></a></td>';		
		str += '<td valign="top" width="55%">';
		str += '<div class="connectPeople_body_name"><a href="userProfile.action?profileId='+users[i].id+'" target="_blank" style="text-transform: capitalize;">'+users[i].candidateName+'</a></div>';
		str += '<div><span class="connectPeople_body_constituency" style="text-transform:capitalize;font-size:15px;font-family:arial;">'+users[i].constituencyName.toLowerCase()+'</span></div>';			
		str += '</td>';
		str += '<td valign="middle" width="30%" align="center">';
		if(users[i].status == "NOT CONNECTED")
		{
			str += '<table border="0" cellpadding="0" cellspacing="0">';
			str += '<tr>';
			//str += '<td><div id="connectPeopleButtonLeftDivId_'+users[i].id+'"><img height="20px" src="images/icons/cadreReport/bg_left.png"/></div></td>';
			str += '<td><div id="connectPeopleButtonLeftDivId_'+users[i].id+'"></div></td>';
			if(userLoginStatus == "false"){
				str += '<td ><div class="allConnectPeople_Head_botton_center_div" style="background:none;margin-right:25px;"><a style="color:white;" class="allConnectPeople_Head_botton_center_span" href=connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&'+locationIdLabel+'='+locationId+'&'+locationNameLabel+'='+locationName+'"><i class="icon-plus-sign opacityFilter-50"></i></a></div></td>';
			}
			else{
				str += '<td ><div id="connectPeopleButtonDivId_'+users[i].id+'" class="allConnectPeople_Head_botton_center_div" style="background:none;margin-right:25px;"><span class="allConnectPeople_Head_botton_center_span" title="Connect Now" onclick="showConfirmDiv(\''+users[i].candidateName+'\',\''+users[i].id+'\')"><i class="icon-plus-sign opacityFilter-50"></i></span></div></td>';
			}
			//str += '<td><div id="connectPeopleButtonRightDivId_'+users[i].id+'"><img height="20px" src="images/icons/cadreReport/bg_right.png"/></div></td>';
			str += '<td><div id="connectPeopleButtonRightDivId_'+users[i].id+'"></div></td>';
			str += '</tr>';
			str += '</table>';
		}
		else if(users[i].status == "PENDING" || users[i].status == "LOGGED_USER")
		{
		if(users[i].status == "PENDING"){
			str += '<div title="Request Pending"><i class="icon-adjust opacityFilter-50" style="margin-right:5px;"></i></div>';
		}
		else{
			str += '<div>'+users[i].status+'</div>';
			}
		}
		else if(users[i].status == "COMMENTS"){
		str += '<div title="Request Pending"><i class="icon-adjust opacityFilter-50" style="margin-right:5px;"></i></div>';
		}
		else 
		{
			str += '<span class="connectPeople_body_status">';
			str += '<a style="margin-right:5px;" title="Send Message" href="javascript:{}" onclick="showMailPopup(\''+users[i].id+'\',\''+users[i].candidateName+'\',\'Message\')">';
			//str += '	<img title="Send Message" width="22" height="20" style="border:none;" src="images/icons/candidatePage/contact.png">';
			str += '<i class="icon-envelope opacityFilter-50"></i></a>';
			str += '</span>';
			str += '<div id="connectPeopleMessagePopup_main" class="yui-skin-sam"><div id="connectPeopleMessagePopup"></div></div>';
		}
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
	}	
	str += '<div class="custom_paginator_class"></div>';
	return str;
}

function buildAllConnectUserStringOfDistrict(users)
{  
	var str = '';
	for(var i=0; i<users.length; i++){
			var imageStr = "pictures/profiles/"+users[i].image;
			str+='<span class="memberCardViewAll" style=" width:235px;">';
			str+='<h3 id="connectDiv" style="height:105px;"><div style="float:left;width:60px;height:60px;">';
			if(users[i].image == null)
				str+='<a target="_blank" href="userProfile.action?profileId='+users[i].id+'"><img src="images/icons/connectPeople/member.png" /></a>';
			else
				str+='<a target="_blank" href="userProfile.action?profileId='+users[i].id+'"><img  src="'+imageStr+'" style="width:60px;height:60px;" onerror="setDefaultImg(this)"/></a>';		
			
			str+='</div><div style="float:right;width:100px;height:30px;margin-left:5px;"><span style="text-transform:capitalize;width: 120px; font-size: 15px; margin-top: 0px; padding-right: 40px;"><a target="_blank" href="userProfile.action?profileId='+users[i].id+'">'+users[i].candidateName+'</a></span>';		
			
			str+='</div></h3>';
		if(users[i].status == "CONNECTED"){
				str += '<a href="javascript:{}" onclick="showMailPopup(\''+users[i].id+'\',\''+users[i].candidateName+'\',\'Message\')" style="float:right;margin:-40px 8px 0px 0px;background:white;padding:3px;">';
				str += '	<img title="Send Message" width="22" height="20" style="border:none;" src="images/icons/candidatePage/contact.png">';
				str += '</a>';
			 }
			str+='<a href=# class="sociallinks">Constituency: <b style="text-transform:capitalize">'+users[i].constituencyName.toLowerCase()+'</b></a><br/>';					
			str+='<a href=# class="sociallinks">Friends (<b>'+users[i].noOfFriends+'</b>)</a>';
			str+='<a href=# class="sociallinks">Problems Posted(<b>'+users[i].noOfPosts+'</b>)</a>';
		
		if(userLoginStatus == "false"){
			str+='<a href="connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&'+locationIdLabel+'='+connetLocationId+'&'+locationNameLabel+'='+connetLocationName+'" class="ConnectNow"><label class="connectsymbol">&infin;</label>Connect Now</a>';
		}
		else{		
			if(users[i].status == "LOGGED_USER")
				str+='<a href="#" class="ConnectNow" style="color: green;font-weight:bold;width:110px;">Logged User</a>';
			else if(users[i].status == "NOT CONNECTED"){
				str+='<a href="javascript:{}" onclick="showConfirmDiv(\''+users[i].candidateName+'\',\''+users[i].id+'\')" class="ConnectNow"><label class="connectsymbol">&infin;</label><span id="connectPeopleButtonDivId_'+users[i].id+'" style="color: red;font-weight:bold;width:110px;">Connect Now</span></a>';
				str+='<div id="connectPeopleButtonLeftDivId_'+users[i].id+'"></div><div id="connectPeopleButtonRightDivId_'+users[i].id+'"></div>';
			}			
			else if(users[i].status == "PENDING")
					str+='<a href="#" class="ConnectNow" style="color: peru;font-weight:bold;width:110px;"><label class="connectsymbol">&infin;</label>Request Pending</a>';
			else if(users[i].status == "CONNECTED"){
				str+='<a href="#" class="ConnectNow" style="color: blue;font-weight:bold;width:110px;"><label class="connectsymbol">&infin;</label>Connected</a>';
				
				str += '<div id="connectPeopleMessagePopup_main" class="yui-skin-sam"><div id="connectPeopleMessagePopup"></div></div>';
			}
		}
		str+='</span>';
	}	
	str += '<div class="custom_paginator_class"></div>';	
	return str;
}

function showMailPopup(id,name,type)
{
	
	var str = '';	
	str += '<div id="ErrorMsgDivId" style="margin-left: 85px; margin-bottom: 8px; margin-top: 5px;"></div>';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th>Message</th>';
	str += '<td><textarea id="connectMessageText" cols="35" rows="4" style="background:#ffffff;"></textarea></td>';
	str += '</tr>';
	str += '<tr>';	
	str += '<td><input type="button" name="connectButton" value="Send" id="sendMessageButtonId" onclick="sendMessageToConnectedUser(\''+id+'\',\''+type+'\')"></td>';
	str += '</tr>';	
	str += '</table>';
	str += '<table style="text-align:center;width:100%;"><tr><td><div id="confirmationMsg"></div></td></tr></table>';
	str	+= '<div id="connectStatus"></div>';
	str	+= '</div>';

	$( "#connectPeoplePopup" ).dialog({
			title:"Send Message to "+name,
			autoOpen: true,
			show: "blind",
			width: "auto",
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
	
	var eflag = false;
	var errorDivEle = document.getElementById("ErrorMsgDivId");
	var str='';
	
	var connectTextAreaElmt = document.getElementById("connectMessageText");
	var connectTextAreaElmtValue = document.getElementById("connectMessageText").value;
	
	if(connectTextAreaElmt.value == "")
	{
		str +='<font style="color:red">Please Enter Message.</font>';
		eflag = true;
	}
	
	else if(connectTextAreaElmtValue.length > 300)
	{
		str +='<font style="color:red">Message Should be lessthan 300 characters.</font>';
		eflag = true;
	}
	errorDivEle.innerHTML = str;

	if(eflag)
	return;
	
	
	var connectMsg = connectTextAreaElmt.value;
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
		elmt.innerHTML = '<blink><font color="green" style="font-weight:bold;"> Request sent to selected users successfully.</font></blink>';
		if(jsObj.locationType=="DISTRICT"){
			showAllConnectedUsersInPanelOfDistrict(jsObj,results);		
			buildConnectUsersContentOfDistrict(results.candidateVO,connectDivId,connetLocationType,connetLocationId,connetLocationName,connectUserLoginStatus,connectUserLoginId, results.totalResultsCount);
		}
		else{
			showAllConnectedUsersInPanel(jsObj,results);		
			buildConnectUsersContent(results.candidateVO,connectDivId,connetLocationType,connetLocationId,connetLocationName,connectUserLoginStatus,connectUserLoginId);
		}
	}
	else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
		elmt.innerHTML = '<font color="red" style="font-weight:bold;"><blink> Request cannot be sent to the selected users due to some technical difficulty.</blink></font>';
}


function getAllConnectedUsersByFilterView(locationType, userId)
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
	
	var connectStatusSelectElmtText='all';
	if(userId!=''){
		var connectStatusSelectElmt = document.getElementById("connectStatusSelect");
		var connectStatusSelectElmtValue = connectStatusSelectElmt.options[connectStatusSelectElmt.selectedIndex].value;
		connectStatusSelectElmtText  = connectStatusSelectElmt.options[connectStatusSelectElmt.selectedIndex].text;
	}
	
	var textElmt = document.getElementById("connectStatusTextBox");
	var textValue = textElmt.value;	

	var jsObj ={
				constituencyIds:connectConstiArray,				
				statusText:connectStatusSelectElmtText,
				nameString:textValue,
				task:"getAllConnectedUsersByFilterView"
			 };
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllConnectedUserByFilterViewAction.action?"+rparam;					

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:9,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",		
		callBackFunction:function(){
				showAllConnectedUsersInPanelByFilterView(jsObj,results, locationType);				
		}
	});
	custom_paginator.initialize();
}

function showAllConnectedUsersInPanelByFilterView(jsObj,results, locationType)
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
		if(locationType=="DISTRICT")
			str += buildAllConnectUserStringOfDistrict(users);			
		else
			str+=buildAllConnectUserString(users);
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