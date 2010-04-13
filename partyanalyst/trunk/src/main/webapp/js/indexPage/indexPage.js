
var indexPageMain = {
						reportsCarousel:'',
						partyAnalysisReports:[
												{
													header:'Party Performance Report',
													body:'Party Performance Report gives a detailed election results analysis for a party on its performance in an election.			This report mainly focus on complete party election results of won/lost details in different positions, which			include first,second,third upto Nth position dtails and election results in those positions.',
													img:'<img src="images/icons/indexPage/partyanalysis/report1.png"/>',
													viewAnc:'<a href="javascript:{}" class="viewReportAnc">View</a>'
												},
												{
													header:'Election Comparison Report',
													body:'Elections Comparison Report gives a glance of compared election results for a party participated any two elections in a detailed view.This report mainly provides a overview  for a user to know wheather the party improved/lost its performance in selected present year when compared to selected previous year.',
													img:'<img src="images/icons/indexPage/partyanalysis/report2.png"/>',
													viewAnc:'<a href="javascript:{}" class="viewReportAnc">View</a>'
												},
												{
													header:'Party Results Report',
													body:'Party Results Report gives overall picture for a party in different types of elections like assembly/parliament/zptc/mptc/municipality in different party participated years in a single glance.The results can be classified and viewed in three different views like statewise or districtwise or constituencywise.',
													img:'<img src="images/icons/indexPage/partyanalysis/report3.png"/>',
													viewAnc:'<a href="javascript:{}" class="viewReportAnc">View</a>'
												},
												{
													header:'Party Influence Report',
													body:'Party Influence Report compares the election results among one party and newly establish party.The resuilts are compared among all the districts wise and the mandals wise.',
													img:'<img src="images/icons/indexPage/partyanalysis/report4.png"/>',
													viewAnc:'<a href="javascript:{}" class="viewReportAnc">View</a>'
												}
											 ],
						politicianAnalysisReports:[
												{
													header:'Mandal Voting Report',
													body:'Mandal voting report  is a kind of report where we can go for results in different elections for a selected party in a particular mandal.It gives a complete glance of election results for a mandal in different election years participated by the party which provides better analysis for results. ',
													img:'<img src="images/icons/indexPage/politicianAnalysis/report1.png"/>',
													viewAnc:'<a href="javascript:{}" class="viewReportAnc">View</a>'
												},
												{
													header:'Cross Voting Report',
													body:'Cross voting report helps to analyze (Assembly Constituency wise, Mandal wise, Booth wise) difference of votes percentage for a parliament candidate and an assembly candidates in a Parliament Constituency for the same party. ',
													img:'<img src="images/icons/indexPage/politicianAnalysis/report2.png"/>',
													viewAnc:'<a href="javascript:{}" class="viewReportAnc">View</a>'
												},
												{
													header:'Constituency Booth Results Report',
													body:"This report helps a politician/party to analyze a candidate's Boothwise Performance in a constituency for a selected assembly or parliament election.This report helps to analyze a candidate in which booth/mandal he got less votes%, in which booth/mandal he got highest votes percentage.",
													img:'<img src="images/icons/indexPage/politicianAnalysis/report3.png"/>',
													viewAnc:'<a href="javascript:{}" class="viewReportAnc">View</a>'
												}											 
											 ]
					};


function buildIndexPageLayout()
{ 	 
	var candidatePageLayout = new YAHOO.widget.Layout('dashboard_layout_main', { 
	height:480,
	units: [			
			{ 
				position: 'left', 
				width: 200,
				header:false,
				body: 'dashBoardLeftlayoutDiv',
				resize: false,
				gutter: '2px',
				collapse: false,
				scroll: true,						
				animate: true 
			}, 					 
			{ 
				position: 'center',						
				body: 'dashBoardCenterlayoutDiv',
				resize: false,
				gutter: '2px',
				collapse: true,
				scroll: true,						
				animate: true
			} 
		 ] 
		
	});
	candidatePageLayout.render(); 
}


function buildReportsCarousel()
{
	reportsCarousel = new YAHOO.widget.Carousel("pa_reports_carousel",
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 3,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});

		reportsCarousel.render(); 
		reportsCarousel.show();
}

function showCurrentDateTime()
{
	var elmt = document.getElementById("todayDate");
	var today=new Date();

	var str = "";
	str+="Today : ";
	str+=getDayOfWeek(today.getDay())+",";
	str+=today.getDate()+"-";
	str+=getMonth(today.getMonth())+"-";	
	str+=today.getFullYear()+" , ";
	str+='<span id="currentTimeSpan"></span>';
	elmt.innerHTML=str;	
	
	showCurrentTime();
}

function showCurrentTime()
{
	var elmt = document.getElementById("currentTimeSpan");
		var today=new Date();

	var h=today.getHours();
	var m=today.getMinutes();
	var s=today.getSeconds();
	// add a zero in front of numbers<10
	m=checkTime(m);
	s=checkTime(s);
	elmt.innerHTML=h+":"+m+":"+s;
	t=setTimeout('showCurrentDateTime()',500);
}

function checkTime(i)
{
	if (i<10)
	  {
	  i="0" + i;
	  }
	return i;
}

function showReportsInCarousel(type)
{
	var arr = '';
	if(type == "partyAnalysis")
		arr = indexPageMain.partyAnalysisReports;
	else if(type == "politicianAnalysis")
		arr = indexPageMain.politicianAnalysisReports;
	

	reportsCarousel.clearItems();

	for(var i in arr)
	{		
		var str = '';
		str+='<div class="reports_carousel_div_class">';
		str+='<div class="pa_reports_head">'+arr[i].header+'</div>';
		str+='	<div class="pa_reports_body">';		
		str+='		<div style="margin-top:10px">'+arr[i].img+'</div>';
		str+='		<div style="height:120px">'+arr[i].body+'</div>';
		str+='		<div style="float:right;padding-top:4px;">'+arr[i].viewAnc+'</div>';
		str+='	</div>';									
		str+='</div> ';

		reportsCarousel.addItem(str);
	}
	
}

function initializeIndexPage()
{
	buildIndexPageLayout();
	showCurrentDateTime();
	buildReportsCarousel();

}