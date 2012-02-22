

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
var userLoginStatus = "";
var userId = "";
var userType="";
var userLogIn="";
var forwardTask = "";

function buildDistrictPageLayout()
{
	var cadreReportPageLayout = new YAHOO.widget.Layout('districtPageLayout_main', { 
	height:1060,
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
	str+='<fieldset id="problemViewingFieldSet" style="width:285px;">';
	str+='<legend style="font-weight:bold;-moz-border-radius:3px;background-color:#3897A5;"> View Your District Problems</legend>';
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
			str+='<table>';
			str+='	<tr>';
			str+='		<td width="80%">';
			str+='			<span><img height="10" width="10" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png" style="margin-right:5px;"></img></span>';
			str+='			<span>';
			str+='				<a class="districtAnc" href="problemCompleteDetailsAction.action?problemHistoryId='+data.problemHistoryId+'">'+data.problem+'</a></span>';
			str+='		</td>';
			str+='		<td width="10%"><img width="20" height="20" title="Accepted Votes" src="images/icons/accept.png"/></td><td style="font-weight:bold;font-size:13px">'+data.acceptedCount+'</td>';	
			str+='      <td><img width="20" height="20" src="images/icons/reject.png" title="Rejected Votes"/></td><td style="font-weight:bold;font-size:13px">'+data.rejectedCount+'</td>';		
			str+='	</tr>';
			str+='</table>';
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

function  showProblemCompleteDetails(selectedProblemId)
{
	for(var i in problemsInfo)
		{
			var data = problemsInfo[i];			
			if(data.problemId==selectedProblemId){
						var elmt = document.getElementById('districtProblemsMgmtBodyDiv');
						var divChild = document.createElement('div');
						divChild.setAttribute('id','createDiv');
						var problemName = data.problem;
						data.problem.name = problemName[0].toUpperCase();
						elmt.appendChild(divChild);	

						var showProblemData='';		
						showProblemData+='<div align="center"><h3>Complete Report of <span style="color:green">'+data.problem+'</span> </h3></div>';
						showProblemData+='<fieldset>';  		
						showProblemData+='	<legend style="font-family:arial,helvetica,clean,sans-serif;">Details of the Problem</legend>';
						showProblemData+='		<table id="probDetailsTable">';						
						showProblemData+='			<tr>';
						showProblemData+='				<td><b> Problem :</b></td>';
						showProblemData+='				<td>'+data.problem+'</td>';
						showProblemData+='			</tr>';
						showProblemData+='			<tr>';
						showProblemData+='				<td><b> Description :</b></td>';
						showProblemData+='				<td>'+data.description+'</td>';
						showProblemData+='			</tr>';
						showProblemData+='			<tr>';
						showProblemData+='				<td><b> Complained Person Name :</b></td>';
						showProblemData+='				<td>'+data.name+'</td>';
						showProblemData+='			</tr>';
						showProblemData+='			<tr>';
						showProblemData+='				<td><b> PostedDate  :</b></td>';
						showProblemData+='				<td>'+data.postedDate+'</td>';
						showProblemData+='			</tr>';
						showProblemData+='		</table>';						
						showProblemData+='</fieldset>';				
						
						showProblemData+='<div id="showProblems" class="yui-skin-sam" align="center"></div>';

						if(createGroupDialog)
							createGroupDialog.destroy();
						createGroupDialog = new YAHOO.widget.Dialog("createDiv",
								{ width : "600px", 		
								  fixedcenter : false, 
								  visible : true,  
								  constraintoviewport : true, 
								  iframe :false,
								  modal :false,
								  hideaftersubmit:true,
								  close:true,
								  x:400,
								  y:300,				  
								  buttons : [ { text:"Ok", handler: handleSubmit, isDefault:true}, 
											  { text:"Cancel", handler: handleCancel}]
								 } );


					
						
						createGroupDialog.setBody(showProblemData);
						
						createGroupDialog.render();
			}
		}
}
function handleSubmit()
{
	createGroupDialog.hide();			
}

function handleCancel()
{
	this.cancel();
}

/*
function openAddNewProblemWindow()
{	
	var browser1 = window.open("addNewProblemAction.action?requestSrc=3&districtId="+districtId+'&districtName='+districtName,"addNewProblemInConstituency","scrollbars=yes,height=600,width=600,left=200,top=200");				 
	browser1.focus();
}*/

function buildProblemPostingWindowForDistrict()
{
	
		
	var headElmt = document.getElementById('problemPostingDiv_Head');
	var bodyElmt = document.getElementById('problemPostingDiv_Body');
	var str='';
	str+='<fieldset id="ProblemPostingFieldSet" style="width:265px;">';
	str+='<legend style="font-weight:bold;-moz-border-radius:3px;background-color:#3897A5;"> Post Your District Problem</legend>';
	str+='<div id="ProblemPostingContentDiv" class="problemPostingContentDivClass">';	
	str+='<div>Post your district problem and bring it to the all people notice.</div>';	
	if(userLoginStatus=="true"){		
		str+='<div id="problemPostingButtonDiv" class="view-all"><a type="button" id="postButton" value = "Post" style="-moz-border-radius:3px;" onclick="openAddNewProblemWindow()">POST</a></div>';	
	}else{
		str+='<div id="problemPostingButtonDiv" class="view-all"><a href="problemPostControlAction.action?redirectLoc=DISTRICT&districtId='+districtId+'&districtName='+districtName+'">POST</a></div>';	
	}
	str+='</div>';
	str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;

	/*var postButton = new YAHOO.widget.Button("postButton");
	postButton.on("click", openAddNewProblemWindow); */
}
function buildDistrictConnectPeopleWindow()
{	
debugger;

	var bodyElmt = document.getElementById('districtPeopleConnectData_body');
	
	if(connectedPeople.length == 0 && userLoginStatus == "false")
	{
		var errorStr = '';
		errorStr += '<div class="errorStr"> No people have been connected.</div>';
		errorStr += '<div class="errorStr">Register to connect to your area.</div>';
		errorStr += '<div class="errorStr">Connect functionality provides the user to connect to his/her area and share information, group certain people, sending messages etc..,</div>';
		errorStr += '<div class="errorStr">To connect to your district people <a href="freeUserRegistration.action">Register</a></div>';
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
		errorStr += '<div class="errorStr">To connect to your district people <a href="freeUserRegistration.action">Register</a></div>';		
		bodyElmt.innerHTML = errorStr;
		return;
	}
	
	buildConnectUsersContent(connectedPeople,"districtPeopleConnectData_body","DISTRICT",districtId,districtName,userLoginStatus,userId);
}

/*function initializeDistrictPage()
{
	buildProblemPostingWindowForDistrict();
	buildDistrictPageLayout();
	buildDistrictLatestNews();
	buildDistrictLevelProblemWindow();
	buildDistrictConnectPeopleWindow();
	
}
*/