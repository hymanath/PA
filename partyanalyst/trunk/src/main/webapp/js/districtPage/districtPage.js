

var districtPageMainObj = {
							mandalsList:[]
					  };

var districtMlas = new Array();
var districtMps = new Array();
var problemsInfo = new Array();

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
	str+='<legend> View Your constituency Problems</legend>';
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

function initializeDistrictPage()
{
	buildDistrictPageLayout();
	buildDistrictLatestNews();
	buildDistrictLevelProblemWindow();
}
