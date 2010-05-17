
var indexPageMainObj =	{
						mlaConstituencies:[],
						mpContituencies:[]
					};

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

function displaySelectedHeaderForElmt(type)
{
	var elmt = document.getElementById(type+"_div");
	
	var elements = YAHOO.util.Dom.getElementsByClassName('reportGroupClassSelected');

	if(elements)
	{
		for(var i in elements)
		{			
			if(YAHOO.util.Dom.hasClass(elements[i], "reportGroupClassSelected"))
				YAHOO.util.Dom.removeClass(elements[i], 'reportGroupClassSelected'); 
		}
	}
	
	YAHOO.util.Dom.addClass(type+'_div', 'reportGroupClassSelected'); 
}

function showReportsInCarousel(type)
{
	displaySelectedHeaderForElmt(type);
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

function showRegionStaticData()
{
	var elmt = document.getElementById("staticDataView_body");

	 YUI().use( 'gallery-accordion', function(Y) {
		
		var accordion = new Y.Accordion( {
		contentBox: "#staticDataView_body",
		useAnimation: true,
		collapseOthersOnExpand: true
		});
	 
		accordion.render();

		var item1, item2, item3, item4;
		
		/* -- Item 1 --*/
		item1 = new Y.AccordionItem( {
		label: "View Your State",
		expanded: false,
		contentHeight: {
			method: "fixed",
			height: 50
		},
		closable: false
		});
	 
		var item1str='';
		item1str+='<ul class="regionsList">';
		item1str+='<li><a href="statePageAction.action?stateId=1">Andhra Pradesh</a></li>';
		item1str+='</ul>';
		item1.set( "bodyContent",item1str);
		accordion.addItem( item1 );
		
		/* -- Item 2 --*/

		item2 = new Y.AccordionItem( {
		label: "View Your District",
		expanded: false,
		contentHeight: {
			method: "fixed",
			height: 150
		},
		closable: false
		});
	 
		var item2Str='';
		item2Str+='<div>';
		//item2Str+='<div style="cursor:pointer;"><img style="position:absolute;left:50px;" src="images/icons/plusNew.png"/></div>';
		item2Str+='<div class="regionsListDiv">';
		item2Str+='<ul class="regionsList">';
		item2Str+='<li><a href="districtPageAction.action?districtId=1&districtName=Adilabad">Adilabad</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=22&districtName=Anantapur">Anantapur</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=23&districtName=Chittoor">Chittoor</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=14&districtName=Adilabad">East Godavari</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=17&districtName=Guntur">Guntur</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=5&districtName=Hyderabad">Hyderabad</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=20&districtName=Cuddapah">Cuddapah</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=3&districtName=Karimnagar">Karimnagar</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=10&districtName=Khammam">Khammam</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=16&districtName=Krishna">Krishna</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=21&districtName=Kurnool">Kurnool</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=7&districtName=Mahbubnagar">Mahbubnagar</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=4&districtName=Medak">Medak</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=8&districtName=Nalgonda">Nalgonda</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=19&districtName=Nellore">Nellore</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=2&districtName=Nizamabad">Nizamabad</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=18&districtName=Prakasam">Prakasam</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=6&districtName=Rangareddy">Rangareddy</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=11&districtName=Srikakulam">Srikakulam</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=13&districtName=Visakhapatnam">Visakhapatnam</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=12&districtName=Vizianagaram">Vizianagaram</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=15&districtName=West Godavari">West Godavari</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=9&districtName=Warangal">Warangal</a></li>';
		item2Str+='</ul>';
		item2Str+='</div>';
		//item2Str+='<div style="cursor:pointer;"><img onclick="animateScrollDiv(\'regionsListDiv\',\'down\')" style="position:absolute;left:50px;" src="images/icons/plusNew.png"/></div>';
		item2Str+='</div>';		
		item2.set( "bodyContent",item2Str);
		accordion.addItem( item2 );
		
		/* -- Item 3 --*/

		item3 = new Y.AccordionItem( {
		label: "View Your Constituency",
		expanded: false,		
		contentHeight: {
			method: "fixed",
			height: 150
		},
		closable: false
		});
	 
		var item3Str='';
		item3Str+='<div id="constituencyCategoryRadio" style="text-align:center;">';
		item3Str+='<input type="radio" name="regionListCategoryRadio" value="mla" checked="checked" onclick="getconstituenciesByCategory(this.value)"/> MLA';	
		item3Str+='<input type="radio" name="regionListCategoryRadio" value="mp" onclick="getconstituenciesByCategory(this.value)"/> MP';	
		item3Str+='</div>';
		item3Str+='<div class="regionsListDiv" id="constituencyRegionListDivId">';
		item3Str+='<ul class="regionsList">';
		for(var i in indexPageMainObj.mlaConstituencies)
			item3Str+='<li><a href="constituencyPageAction.action?constituencyId='+indexPageMainObj.mlaConstituencies[i].id+'">'+indexPageMainObj.mlaConstituencies[i].name+'</a></li>';		
		item3Str+='</ul>';
		item3Str+='</div>';

		item3.set( "bodyContent",item3Str);
		accordion.addItem( item3 );
		
		/* -- Item 4 --*/

		item4 = new Y.AccordionItem( {
		label: "View Your Mandal",
		expanded: false,
		contentHeight: {
			method: "fixed",
			height: 100
		},
		closable: false
		});
	 
		var item4Str='';
		item4Str+='body';
		item4.set( "bodyContent",item4Str);
		accordion.addItem( item4 );

	 });
}


function getconstituenciesByCategory(category)
{	
	var elmt = document.getElementById("constituencyRegionListDivId");

	var str = '';

	if(!elmt)
		return;

	if(category == "mla")
	{
		str+='<ul class="regionsList">';
		for(var i in indexPageMainObj.mlaConstituencies)
			str+='<li><a href="constituencyPageAction.action?constituencyId='+indexPageMainObj.mlaConstituencies[i].id+'">'+indexPageMainObj.mlaConstituencies[i].name+'</a></li>';		
		str+='</ul>';	
	}
	else if(category == "mp")
	{
		str+='<ul class="regionsList">';
		for(var i in indexPageMainObj.mpContituencies)
			str+='<li><a href="constituencyPageAction.action?constituencyId='+indexPageMainObj.mpContituencies[i].id+'">'+indexPageMainObj.mpContituencies[i].name+'</a></li>';		
		str+='</ul>';	
	}
	
	elmt.innerHTML = str;
}
function animateScrollDiv(divId,direction)
{
	var element = document.getElementById(divId);
	/*var myAnim = new YAHOO.util.Scroll(element, {
		scroll: {    
			to: [ 500, divId.scrollTop ]
		} 
	});
	myAnim.animate();*/

	var myAnim = new YAHOO.util.Scroll(element, { 
		scroll: {
					to: [0, 800]
				}
	}, 1, YAHOO.util.Easing.easeOut);
	myAnim.animate();
}

function initializeIndexPage()
{	
	buildIndexPageLayout();
	showCurrentDateTime();
	buildReportsCarousel();
	showRegionStaticData();
}