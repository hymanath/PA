

var districtPageMainObj = {
							mandalsList:[]
					  };

var districtId;
var districtName;

var districtMlas = new Array();
var districtMps = new Array();
var problemsInfo = new Array();
var connectedPeople = new Array();
var userLoginStatus = '';
var userId = '';
var connectPopupPanel = '';

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
	
	if(connectedPeople.length == 0)
	{
		bodyElmt = 'No People Connect Connected';
		return;
	}
	
	var bodyStr='';
	bodyStr+='<div id="connectedNumberDiv"> ';
	bodyStr+='<span><img height="20" width="25" src="/PartyAnalyst/images/icons/constituencyPage/groups.png"></img></span>';
	bodyStr+='<span style="position:relative;left:5px;top:-5px;"> '+connectedPeople.length+' people connected to this district </span>';
	bodyStr+='</div>';
	bodyStr+='<div id="connectedPersonsDiv">';
	bodyStr+='<table width="100%">';
	for(var i =0; i<connectedPeople.length; i++)
	{
		if(i == 3)
			break;
		bodyStr+='<tr>';
		bodyStr+='<td>';
		bodyStr+='<table width="100%">';
		bodyStr+='<tr>';
		bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';
		bodyStr+='<td align="left"><span class="groupPersonNameSpanClass">'+connectedPeople[i].userName+'</span></td>';
		bodyStr+='</tr>';
		bodyStr+='<tr>';	
		bodyStr+='<td align="right"><span class="groupPersonMessageSpanClass">';
		if(userLoginStatus == "false")
			bodyStr+='<a href="connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&districtId='+districtId+'&districtName='+districtName+'&connectUserId='+connectedPeople[i].userId+'">Connect</a>';
		else
			bodyStr+='<a href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+connectedPeople[i].userId+'\',\''+connectedPeople[i].userName+'\')">Connect</a>';

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
	bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" class="connectAnc">View All</a> </span>';
	//bodyStr+='<span class="connectAncSpan"> | </span>';
	//bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" class="connectAnc"> Connect </a> </span>';
	bodyStr+='</td>';
	bodyStr+='</tr></table>';
	bodyStr+='</div>';

	if(bodyElmt)
		bodyElmt.innerHTML=bodyStr;

	var connectButton = new YAHOO.widget.Button("connectButton");

}

function showConnectConfirmDialogBox(userId,userName)
{
	var str = '';	
	str	+= '<div id="connectPeople_Head"> Do You Want To Connect To '+userName+' ?</div>';
	str	+= '<div id="connectPeople_body">';
	str	+= '<div id="connectStatus"></div>';
	str	+= '<input type="button" class="connectButton" onclick="doConnectPeople(\''+userId+'\',\''+userName+'\')" value="Connect"/>';
	str	+= '<input type="button" class="cancelButton" onclick="closeConnectPeoplePopup()" value="Cancel"/>';
	str	+= '</div>';
	

	connectPopupPanel = new YAHOO.widget.Dialog("connectPeoplePopup", {      
				 width:'350px',
                 fixedcenter : true, 
                 visible : true,
                 constraintoviewport : true, 
        		 iframe :false,
        		 modal :false,
        		 hideaftersubmit:true,
        		 close:false,
				 draggable:false
       });	 
	
    connectPopupPanel.setBody(str);
    connectPopupPanel.render();
}

function doConnectPeople(connectUserId,connectUserName)
{
	var jsObj ={
				districtId:districtId,
				districtName:districtName,
				connectUserId:connectUserId,
				connectUserName:connectUserName,
				userId:userId,
				task:"connectToUser"
			 };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "connectToUserAction.action?"+rparam;					
	callAjax(rparam,jsObj,url);
}

function closeConnectPeoplePopup()
{
	connectPopupPanel.hide();
}

function closeConnectPanel(jsObj,results)
{
	var elmt = document.getElementById("connectStatus");
	elmt.innerHTML = '<font color="green" style="font-weight:bold;"><blink>'+jsObj.connectUserName+" is successfully connected</blink></font>";

	var t=setTimeout("closeConnectPeoplePopup()",3000);
}

function initializeDistrictPage()
{
	buildDistrictPageLayout();
	buildDistrictLatestNews();
	buildDistrictLevelProblemWindow();
	buildDistrictConnectPeopleWindow();
}
