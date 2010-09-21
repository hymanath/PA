

var districtPageMainObj = {
							mandalsList:[]
					  };

var districtMlas = new Array();
var districtMps = new Array();

function buildDistrictPageLayout()
{
	var cadreReportPageLayout = new YAHOO.widget.Layout('districtPageLayout_main', { 
	height:950,
	units: [
			{ 
				position: 'right', 
				width: 220,
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
    "format" : "800x90",
	"queryList" : [
          {
            "title" : districtName,
            "q" : districtName+","+stateName+", India"
          }
     ]
  }

  var content = document.getElementById('districtNewsDiv');
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
     ]
  }

  var content = document.getElementById('district_Politician_news');
  var newsShow = new google.elements.NewsShow(content, options);
}

function initializeDistrictPage()
{
	buildDistrictPageLayout();
	buildDistrictLatestNews();
	buildDistrictLeadersNews();
	//buildMandalsListCarousel();
}
