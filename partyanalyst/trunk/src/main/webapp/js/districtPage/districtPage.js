

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
var connectStatus = new Array();
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



function showAllConnectPeopleWindow()
{
	var str = '';
	str += '<div id="allConnectedUsersDisplay_main"><img src="images/icons/barloader.gif"/></div>';
	
	var connectPopupPanel = new YAHOO.widget.Dialog("connectPeoplePopup", {      
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

function showsearchFilter()
{
	 $("#filterViewContent").slideToggle("slow");
}

function getAllConnectedUsersByFilterView()
{
	var connectConstiSelectElmt = document.getElementById("connectConstituencySelect");
	var connectConstiSelectElmtValue = connectConstiSelectElmt.options[connectConstiSelectElmt.selectedIndex].value;
	var connectConstiArray = new Array();

	var connectStatusSelectElmt = document.getElementById("connectStatusSelect");
	var connectStatusSelectElmtValue = connectStatusSelectElmt.options[connectStatusSelectElmt.selectedIndex].value;
	var connectStatusSelectElmtText  = connectStatusSelectElmt.options[connectStatusSelectElmt.selectedIndex].text;
	
	if(connectConstiSelectElmtValue == 0)
	{
		for(var consti=0; consti<constituencies.length;consti++)
			connectConstiArray.push(constituencies[consti].id);		
	}
	else
		connectConstiArray.push(connectConstiSelectElmtValue);

	var jsObj ={
				districtId:districtId,
				districtName:districtName,				
				userId:userId,
				constituencyIds:connectConstiArray,				
				statusText:connectStatusSelectElmtText,
				task:"getAllConnectedUsersByFilterView"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllConnectedUsersByFilterViewAction.action?"+rparam;					
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
		elmt.innerHTML = str;
		return
	}
	else if(users.length == 0)
	{
		str	+= '<div> No People Connected In this District</div>';
		elmt.innerHTML = str;
		return
	}

	str	+= '<div id="allConnectPeople_Head">';
	str += '<div class="allConnectPeople_Head_content">';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<td align="left">Total People Connected to '+districtName+' district - '+users.length+'</td>';
	str += '<td align="right">';
	str += '<table border="0" cellpadding="0" cellspacing="0">';
	str += '<tr>';
	str += '<td><img height="20px" src="images/icons/cadreReport/bg_left.png"/></td>';
	str += '<td><div class="allConnectPeople_Head_botton_center_div"><span class="allConnectPeople_Head_botton_center_span" onclick="showsearchFilter()">Filter View</span></div></td>';
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
	str += '<th>By Constituency:</th>';
	str += '<th><select id="connectConstituencySelect" onchange="getAllConnectedUsersByFilterView()">';
	str += '<option value="0">All</option>';
	for(var consti=0; consti<constituencies.length;consti++)
		str += '<option value="'+constituencies[consti].id+'">'+constituencies[consti].name+'</option>';
	str += '</select></th>';
	str += '<th>By Status:</th>';
	str += '<th><select id="connectStatusSelect" onchange="getAllConnectedUsersByFilterView()">';
	str += '<option value="0">All</option>';
	for(var status=0; status<connectStatus.length;status++)
		str += '<option value="'+connectStatus[status].id+'">'+connectStatus[status].name+'</option>';
	str += '</select></th>';
	str += '<tr>';
	str += '</table>';
	
	str += '</div>';	
	str += '</div>';
	str	+= '<div id="allConnectPeople_body">';	
	str += buildAllConnectUserString(users);
	str	+= '</div>';
	str	+= '<div id="allConnectPeople_footer">';
	str	+= '<table width="100%">';
	str	+= '<tr>';
	str	+= '<td width="30%" align="center" style="color:#FFFFFF;background-color:#306397;">Message</td>';
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
	str += '<td colspan="3" align="center"><div id="allConnectPeopleStatusTextDiv"></div></td>';
	str += '</tr>';
	str	+= '</table>';
	str	+= '</div>';
	
	elmt.innerHTML = str;
}

function buildAllConnectUserString(users)
{
	var str = '';
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

	return str;
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
