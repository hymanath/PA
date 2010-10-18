

var districtPageMainObj = {
							mandalsList:[]
					  };

var districtId;
var districtName;

var constituencies = new Array();
var districtMlas = new Array();
var districtMps = new Array();
var problemsInfo = new Array();
var connectedPeople = new Array();
var userLoginStatus = '';
var userId = '';
function buildDistrictPageLayout()
{
	var cadreReportPageLayout = new YAHOO.widget.Layout('districtPageLayout_main', { 
	height:1000,
	units: [
			{ 
				position: 'right', 
				width: 350,
				header:false,
				body: 'districtPageLayout_right',
				resize: false,
				gutter: '0px',
				collapse: false,
				scroll: true,						
				animate: true 
			}, 					 
			{ 
				position: 'center',						
				body: 'districtPageLayout_center',
				resize: false,
				gutter: '0px',
				collapse: true,
				scroll: true,						
				animate: true
			} 
		 ] 
		
	});
	cadreReportPageLayout.render(); 
}


function buildMandalsListCarousel()
{
	//<a href="mandalPageElectionInfoAction.action?MANDAL_ID=${mandalsBeforeDelimitationConstituency.id}&MANDAL_NAME=${mandalsBeforeDelimitationConstituency.name}" class="districtAnc">${mandalsBeforeDelimitationConstituency.name}
	//		</a>

	var mandalListCaroousel = new YAHOO.widget.Carousel(divId,
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 3,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});

	mandalListCaroousel.render(); 
	mandalListCaroousel.show();
}

function buildDistrictLatestNews()
{	
	var options = {
    "format" : "300x250",
	"queryList" : [
          {
            "title" : districtName,
            "q" : districtName+","+stateName+", India"
          }
     ],
	"linkTarget" : "_blank"
  }

  var content = document.getElementById('district_Politician_news');
  var newsShow = new google.elements.NewsShow(content, options);
}

function buildDistrictLeadersNews()
{
	var mlaCandidate = "";
	var mpCandidate = "";

	if(districtMlas.length > 0 && districtMps.length > 0)
	{
		mlaCandidate = districtMlas[0];
		mpCandidate = districtMps[0];
	}


	var options = {
    "format" : "300x250",
	"queryList" : [
          {
            "title" : districtName,
            "q" : mlaCandidate+", "+mpCandidate+", "+districtName+","+stateName+", India"
          }
     ],
	"linkTarget" : "_blank"
  }

  var content = document.getElementById('district_Politician_news');
  var newsShow = new google.elements.NewsShow(content, options);
}

function buildDistrictLevelProblemWindow()
{		
	var headElmt = document.getElementById('problemViewingDiv_Head');
	var bodyElmt = document.getElementById('problemViewingDiv_Body');
	
	

	var str='';
	str+='<fieldset id="problemViewingFieldSet" style="width:292px">';
	str+='<legend> View Your District Problems</legend>';
	str+='<div id="problemViewingContentDiv" class="problemPostingContentDivClass">';	
	str+='<marquee direction="up" scrolldelay="200" onmouseover="this.stop();" onmouseout="this.start();">';

	if(problemsInfo.length == 0)
	{
		str+='<div class="problemDataDivClass" onclick="javascript:{}">';
		str+='<span><img height="10" width="10" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png"></img></span>';
		str+='<span> No problems has been posted </span>';
		str+='</div>';
	}
	else
	{
		for(var i in problemsInfo)
		{
			var data = problemsInfo[i];			
			str+='<div class="problemDataDivClass">';
			str+='<span><img height="10" width="10" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png"></img></span>';
			str+='<span> '+data.problem+' </span>';
			str+='</div>';
			str+='<div id="constituencyMgmtBodyDiv" class="yui-skin-sam"><div id="moreDetailsPanelDiv"></div></div>';
		}
	}
	
	str+='</marquee>';
	str+='</div>';
	str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;
}

function buildDistrictConnectPeopleWindow()
{	
	var bodyElmt = document.getElementById('districtPeopleConnect_body');
	
	if(connectedPeople.length == 0 && userLoginStatus == "false")
	{
		var errorStr = '';
		errorStr += '<div class="errorStr"> No people have been connected.</div>';
		errorStr += '<div class="errorStr">Register to connect to your area.</div>';
		errorStr += '<div class="errorStr">Connect functionality provides the user to connect to his/her area and share information, group certain people, sending messages etc..,</div>';
		errorStr += '<div class="errorStr">To connect to your district people <a href="anonymousUserAction.action">Register</a></div>';
		errorStr += '<div class="errorStr">Already Have an account? <a href="connectPeopleAction.action?redirectLoc=DISTRICT&districtId='+districtId+'&districtName='+districtName+'">Login</a></div>';
		
		bodyElmt.innerHTML = errorStr;
		return;
	}
	else if(connectedPeople.length == 0 && userLoginStatus == "true")
	{
		var errorStr = '';
		errorStr += '<div class="errorStr"> No people have been connected.</div>';
		errorStr += '<div class="errorStr">Register to connect to your area.</div>';
		errorStr += '<div class="errorStr">Connect functionality provides the user to connect to his/her area and share information, group certain people, sending messages etc..,</div>';
		errorStr += '<div class="errorStr">To connect to your district people <a href="anonymousUserAction.action">Register</a></div>';		
		bodyElmt.innerHTML = errorStr;
		return;
	}
	
	buildConnectUsersContent(connectedPeople);
}

function buildConnectUsersContent(connectedPeopleData)
{
	var bodyElmt = document.getElementById('districtPeopleConnect_body');

	var bodyStr='';
	bodyStr+='<div id="connectedNumberDiv"> ';
	bodyStr+='<span><img height="20" width="25" src="/PartyAnalyst/images/icons/constituencyPage/groups.png"></img></span>';
	bodyStr+='<span style="position:relative;left:5px;top:-5px;"> '+connectedPeopleData.length+' people connected to this district </span>';
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
		bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';
		bodyStr+='<td align="left"><span class="groupPersonNameSpanClass">'+connectedPeopleData[i].candidateName+'</span></td>';
		bodyStr+='</tr>';
		bodyStr+='<tr>';	
		bodyStr+='<td align="right"><span class="groupPersonMessageSpanClass">';
		if(userLoginStatus == "false")
			bodyStr+='<a href="connectPeopleAction.action?redirectLoc=DISTRICT&districtId='+districtId+'&districtName='+districtName+'">Connect</a>';
		else
		{
			if(connectedPeopleData[i].status == "NOTCONNECTED")
			{
				bodyStr+='<font color="#7F5A22" style="padding-right:10px;">Not Connected</font>';
				bodyStr+=' - <a href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+connectedPeopleData[i].id+'\',\''+connectedPeopleData[i].candidateName+'\',\''+connectedPeopleData[i].constituencyName+'\')">Connect</a>';
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
		bodyStr+='<span class="connectAncSpan" style="padding-right:10px;font-weight:bold;"> <a href="connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&districtId='+districtId+'&districtName='+districtName+'" class="connectAnc">Login</a> </span>';
		bodyStr+='<span class="connectAncSpan" style="padding-right:10px;font-weight:bold;"> <a href="anonymousUserAction.action" class="connectAnc">Register</a> </span>';
		bodyStr+='<span class="connectAncSpan" style="padding-right:10px;font-weight:bold;"> <a href="connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&districtId='+districtId+'&districtName='+districtName+'" class="connectAnc">View All</a> </span>';
	}
	else
	{
		bodyStr+='<span class="connectAncSpan"> <a href="connectPeopleAction.action" class="connectAnc">Redirect To User Page</a> </span>';
		bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" onclick="showAllConnectPeopleWindow()" class="connectAnc">View All</a> </span>';
	}
	//bodyStr+='<span class="connectAncSpan"> | </span>';
	//bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" class="connectAnc"> Connect </a> </span>';
	bodyStr+='</td>';
	bodyStr+='</tr></table>';
	bodyStr+='</div>';

	if(bodyElmt)
		bodyElmt.innerHTML=bodyStr;

}

function showAllConnectPeopleWindow()
{
	var str = '';
	str += '<div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div>';
	
	var connectPopupPanel = new YAHOO.widget.Dialog("connectPeoplePopup", {      
				 width:'450px',
                 fixedcenter : true, 
                 visible : true,
                 constraintoviewport : true, 
        		 iframe :false,
        		 modal :false,
        		 hideaftersubmit:true,
        		 close:true,
				 draggable:true
       });	 
	
	connectPopupPanel.setHeader("People Connected to "+districtName+" district");   
	connectPopupPanel.setBody(str);
    connectPopupPanel.render();

	getAllConnectedUserInDistrict();
}

function getAllConnectedUserInDistrict()
{
	var jsObj ={
				districtId:districtId,
				districtName:districtName,				
				userId:userId,
				task:"getAllConnectedUsers"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllConnectedUserAction.action?"+rparam;					
	callAjax(rparam,jsObj,url);
}

function showAllConnectedUsersInPanel(jsObj,results)
{	
	var str = '';	
	var elmt = document.getElementById("allConnectedUsersDisplay_main");

	var users = results.candidateVO;

	if(results.resultStatus.exceptionEncountered != null || results.resultStatus.resultCode !=0)
	{
		str	+= '<div> Data Not Found Due To Some Exeption</div>';
		connectPopupPanel.setBody(str);
		return
	}
	else if(users.length == 0)
	{
		str	+= '<div> No People Connected In this District</div>';
		connectPopupPanel.setBody(str);
		return
	}

	str	+= '<div id="allConnectPeople_Head">';
	str += '<div>Total People Connected to '+districtName+' district - '+users.length+'</div>';
	str += '<div>';
	str += '<table>';
	str += '<tr>';
	str += '<th>View People By :</th>';
	str += '<th>Constituency</th>';
	str += '<th><select>';
	str += '<option>All</option>';
	for(var consti=0; consti<constituencies.length;consti++)
		str += '<option>'+constituencies[consti]+'</option>';
	str += '</select></th>';
	str += '<tr>';
	str += '</table>';
	
	str += '</div>';	
	str += '</div>';
	str	+= '<div id="allConnectPeople_body">';	
	for(var i=0; i<users.length; i++)
	{
		str += '<div class="allConnectPeopleDataDiv_main">';
		str += '<table width="100%">';
		str += '<tr>';
		str += '<td rowspan="2" width="20%"><img height="50" width="55" src="/PartyAnalyst/images/icons/indexPage/human.jpg"></td>';
		str += '<td width="80%"><div class="connectPeople_body_name">'+users[i].candidateName+'</div></td>';				
		if(users[i].status == "NOTCONNECTED")
			str += '<td rowspan="2" width="10%"><input type="checkbox" name="connectUserCheck" value="'+users[i].id+'"/></td>';
		else
			str += '<td rowspan="2" width="10%">&nbsp</td>';		
		str += '</tr>';
		str += '<tr>';
		str += '<td width="80%">';
		str += '<table width="100%">';
		str += '<tr>';
		str += '<td width="50%" align="left"><span class="connectPeople_body_constituency">'+users[i].constituencyName+'</span></td>';
		str += '<td width="50%" align="right"><span class="connectPeople_body_status">'+users[i].status+'</span></td>';
		str += '</tr>';
		str += '</table>';
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
	}	
	str	+= '</div>';
	str	+= '<div id="allConnectPeople_footer">';
	str	+= '<table width="100%">';
	str	+= '<tr>';
	str	+= '<td width="30%" align="center" style="color:#FFFFFF;background-color:#306397;">Message</td>';
	str	+= '<td width="60%" align="left"><textarea id="AllConnectUserMsg" onkeyup="limitText(\'connectUserMsg\',\'maxcount\',200)" rows="3" cols="40"></textarea></td>';
	str	+= '<td width="10%" align="center"><div id="allConnectPeopleButtonDiv"><input type="button" class="connectButton" onclick="connectUserSetPeople()" value="Connect"/></div></td>';
	str	+= '</tr>';
	str += '<tr>';
	str += '<td colspan="3" align="center"><div id="allConnectPeopleStatusTextDiv"></div></td>';
	str += '</tr>';
	str	+= '</table>';
	str	+= '</div>';
	
	elmt.innerHTML = str;
}

function connectUserSetPeople()
{
	var elements = document.getElementsByName("connectUserCheck");
	var statusElmt = document.getElementById("allConnectPeopleStatusTextDiv");

	var users = new Array();;
	
	if(elements.length == 0)
		return;
	
	for(var i=0; i<elements.length; i++)
	{
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].checked==true)
				users.push(elements[i].value);
		}
	}
	
	if(users.length == 0)
	{
		statusElmt.innerHTML = '<font color="red">Atleast One User Has To Selected To Connect</font>';
		return;
	}
	else
		statusElmt.innerHTML = '';

	var connectTextAreaElmt = document.getElementById("AllConnectUserMsg");
	var connectMsg = connectTextAreaElmt.value;
	var jsObj ={
				districtId:districtId,
				districtName:districtName,
				connectUserIds:users,
				connectMessage:connectMsg,
				userId:userId,
				task:"connectUserSet"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "connectToUserSetAction.action?"+rparam;					
	callAjax(rparam,jsObj,url);
}

function showAllConnectedUsersStatus(jsObj,results)
{
	var elmt = document.getElementById("allConnectPeopleStatusTextDiv");

	if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{		
		elmt.innerHTML = '<blink><font color="green" style="font-weight:bold;"> Requested sent to selected users successfully.</font></blink>';
		showAllConnectedUsersInPanel(jsObj,results);		
		buildConnectUsersContent(results.candidateVO);		
	}
	else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
		elmt.innerHTML = '<font color="red" style="font-weight:bold;"><blink> Request Cannot be sent to selected users due to some technically difficulty.</blink></font>';
}

function showConnectConfirmDialogBox(userId,userName,constituency)
{
	var str = '';	
	str += '<table width="100%">';
	str += '<tr>';
	str += '<td width="75%">';
	str += '<div class="connectPeople_body_name">'+userName+'</div><div class="connectPeople_body_constituency">'+constituency+'</div>';
	str += '</td>';
	str += '<td rowspan="2" align="center" width="25%">';
	str += '<img height="100" width="95" src="/PartyAnalyst/images/icons/indexPage/human.jpg">';
	str	+= '<div id="connectButtonDiv"><input type="button" class="connectButton" onclick="doConnectPeople(\''+userId+'\',\''+userName+'\')" value="Connect"/></div>';
	str += '</td>';
	str += '</tr>';
	str += '<tr>';
	str += '<td width="75%"><fieldset id="connectUserMsgFieldSet"><legend>Message</legend>';
	str += '<textarea id="connectUserMsg" onkeyup="limitText(\'connectUserMsg\',\'maxcount\',200)" rows="3" cols="38"></textarea>';
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
	
	var connectPopupPanel = new YAHOO.widget.Dialog("connectPeoplePopup", {      
				 width:'450px',
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
    connectPopupPanel.render();
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

function doConnectPeople(connectUserId,connectUserName)
{
	var connectTextAreaElmt = document.getElementById("connectUserMsg");
	var connectMsg = connectTextAreaElmt.value;
	var jsObj ={
				districtId:districtId,
				districtName:districtName,
				connectUserId:connectUserId,
				connectUserName:connectUserName,
				connectMessage:connectMsg,
				userId:userId,
				task:"connectToUser"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "connectToUserAction.action?"+rparam;					
	callAjax(rparam,jsObj,url);
}

function closeConnectPanel(jsObj,results)
{
	var elmt = document.getElementById("connectStatus");
	var buttonElmt = document.getElementById("connectButtonDiv");
	
	if(results.resultStatus.resultCode == 0 || results.resultStatus.exceptionEncountered == null)
	{
		buttonElmt.innerHTML = '';
		elmt.innerHTML = '<blink><font color="green" style="font-weight:bold;"> Requested sent to '+jsObj.connectUserName+" successfully.</font></blink>";
		buildConnectUsersContent(results.candidateVO);		
		var t=setTimeout("closeConnectPopup()",2000);
	}
	else if(results.resultStatus.resultCode == 1 || results.resultStatus.exceptionEncountered != null)
		elmt.innerHTML = '<font color="red" style="font-weight:bold;"><blink> Request Cannot be sent to '+jsObj.connectUserName+" due to some technically difficulty.</blink></font>";
		
}

function closeConnectPopup()
{
	var elmt = document.getElementById("connectPeoplePopup_outer");
	if(!elmt)
		return;
	var str = '<div id="connectPeoplePopup"></div>';

	elmt.innerHTML = str;
}

function initializeDistrictPage()
{
	buildDistrictPageLayout();
	buildDistrictLatestNews();
	buildDistrictLevelProblemWindow();
	buildDistrictConnectPeopleWindow();
}
