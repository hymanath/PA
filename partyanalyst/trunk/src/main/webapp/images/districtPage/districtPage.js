

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
			str+='<div class="problemDataDivClass" onclick="showProblemCompleteDetails('+data.problemId+')">';
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
						showProblemData+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Details of the Problem</legend>';
						showProblemData+='<table id="probDetailsTable">';
						showProblemData+='<tr><th>Problem</th>';		
						showProblemData+='<th>Description</th>';
						showProblemData+='<th>IdentifiedDate</th></tr>';
						showProblemData+='<tr><td>'+data.problem+'</td>';
						showProblemData+='<td>'+data.description+'</td>';
						showProblemData+='<td>'+data.postedDate+'</td></tr></table>';
						showProblemData+='</fieldset>';

						showProblemData+='<fieldset>';
						showProblemData+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Complained Person</legend>';		
						showProblemData+='<table id="postedPersonTable">';
						showProblemData+='<tr><th>Name</th>';								
						showProblemData+='<tr><td>'+data.name+'</td></tr></table>';
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
	
	buildConnectUsersContent(connectedPeople,"districtPeopleConnect_body","DISTRICT",districtId,districtName,userLoginStatus,userId);
}

function initializeDistrictPage()
{
	buildDistrictPageLayout();
	buildDistrictLatestNews();
	buildDistrictLevelProblemWindow();
	buildDistrictConnectPeopleWindow();
}
