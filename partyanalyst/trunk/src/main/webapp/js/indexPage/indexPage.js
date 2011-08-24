var smsDialog, newEventDialog, newDateDialog,eventDateDialog,mainEventCalendar,dateCalendar,cadreDataTable,cadreAnim,jsonStr;
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
													viewAnc:'<a href="partyPerformanceMain.action" class="viewReportAnc">View</a>'

												},
												{
													header:'Election Comparison Report',
													body:'Elections Comparison Report gives a glance of compared election results for a party participated any two elections in a detailed view.This report mainly provides a overview  for a user to know wheather the party improved/lost its performance in selected present year when compared to selected previous year.',
													img:'<img src="images/icons/indexPage/partyanalysis/report2.png"/>',
													viewAnc:'<a href="electionComparisonAction.action" class="viewReportAnc">View</a>'

												},
												{
													header:'Party Results Report',
													body:'Party Results Report gives overall picture for a party in different types of elections like assembly/parliament/zptc/mptc/municipality in different party participated years in a single glance.The results can be classified and viewed in three different views like statewise or districtwise or constituencywise.',
													img:'<img src="images/icons/indexPage/partyanalysis/report3.png"/>',
													viewAnc:'<a href="partyResultsCriteriaAction.action" class="viewReportAnc">View</a>'
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
													viewAnc:'<a href="mandalPageSDetailAction.action" class="viewReportAnc">View</a>'
												},
												{
													header:'Cross Voting Report',
													body:'Cross voting report helps to analyze (Assembly Constituency wise, Mandal wise, Booth wise) difference of votes percentage for a parliament candidate and an assembly candidates in a Parliament Constituency for the same party. ',
													img:'<img src="images/icons/indexPage/politicianAnalysis/report2.png"/>',
													viewAnc:'<a href="crossVotingReportInputAction.action" class="viewReportAnc">View</a>'
												},
												{
													header:'Constituency Booth Results Report',
													body:"This report helps a politician/party to analyze a candidate's Boothwise Performance in a constituency for a selected assembly or parliament election.This report helps to analyze a candidate in which booth/mandal he got less votes%, in which booth/mandal he got highest votes percentage.",
													img:'<img src="images/icons/indexPage/politicianAnalysis/report3.png"/>',
													viewAnc:'<a href="partyBoothResultAction.action" class="viewReportAnc">View</a>'
												}											 
											 ]
					};

function buildSMSPopup()
{
	var remainingSms = "${remainingSms}";
	if(remainingSms==0){
		smsRenewalMessage();
		return;
	}
	
	var str = '';	
	str +='	<form action="cadreRegisterAction" method="POST" name="smsForm">';
	str +='	<table>';
	str +='	<tr>';
	str +='		<th align="left">SMS Type</th>';
	str +='		<td align="left">';
	str +='			<input type="radio" name="sms_type" value="locationWise" onclick="getUserLocationData(this.value,\'sms\')"/> Location Wise	';			
	str +='			<input type="radio" name="sms_type" value="cadreLevelWise" onclick="getUsersCadreLevelData(this.value,\'sms\')"/> Cadre Level Wise';
	str +=' 	</td>';		
	str +='	</tr>';
	str +='	<tr>';
	str +='		<th align="left" colspan="2"><div id="region_type_Alert" class="errorMessage"></div></th>';			
	str +='	</tr>';
	str +='	<tr>';
	str +='		<th align="left"><div id="region_type_Label"></div></th>';
	str +='		<td align="left"><div id="region_type_Data"></div></td>	';			
	str +='	</tr>';
	str +='	<tr>';
	str +='		<th align="left">';
	str +='			<div id="region_select_Label">	</div>';
	str +='		</th>';
	str +='		<td align="left">';
	str +='			<div id="region_select_Data">  </div>';					
	str +='		</td>';
	str +='	</tr>';
	str +='	<tr>';
	str +='		<th align="left"><div id="sms_cadre_name_include_label"></div></th>';
	str +='		<td align="left"><div id="sms_cadre_name_include_value"></div></td>';				
	str +='	</tr>';
	str +=' <tr>';
	str +='		<th align="left" colspan="2"><div id="sms_text_Alert" class="errorMessage"></div></th>';
	str +='	</tr>';
	str +=' <tr>';
	str +='		<th align="left"><div id="sms_text_Label"></div></th>';
	str +='		<td align="left"><div id="sms_text_Data"></div></td>';				
	str +='	</tr>';
	str +='	<tr>';
	str +='		<th align="left"><div id="sms_user_name_include_label"></div></th>';
	str +='		<td align="left"><div id="sms_user_name_include_value"></div></td>';				
	str +='	</tr>';
	str +='	<tr>';
	str +='		<td align="left" colspan="2">';
	str +='		<div id="successDiv" style="color:green"></div>';
	str +='		</td>';
	str +='	</tr>';
	str +='	<tr>';
	str +='		<td align="center" colspan="2"><div id="button_div"></div></td>	';			
	str +='	</tr>';		
	str +='	</table>';
	str +='	<form>';
	

	smsDialog = new YAHOO.widget.Dialog("myDialog",
			{ width : "650px", 
              fixedcenter : true, 
              visible : true,  
              constraintoviewport : true, 
			  iframe :true,
			  modal :true	              
             } ); 

	smsDialog.setHeader("Cadre SMS...");
	smsDialog.setBody(str);

	smsDialog.render(); 
	
}

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
		item2Str+='<li><a href="districtPageAction.action?districtId=20&districtName=Cuddapah">Cuddapah</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=14&districtName=Adilabad">East Godavari</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=17&districtName=Guntur">Guntur</a></li>';
		item2Str+='<li><a href="districtPageAction.action?districtId=5&districtName=Hyderabad">Hyderabad</a></li>';
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
			height: 0
		},
		closable: false
		});
	 
		var item4Str='';
		item4Str+='';
		item4.set( "bodyContent",item4Str);
		accordion.addItem( item4 );

	 });

	 //var oButtonGroup1 = new YAHOO.widget.ButtonGroup("constituencyCategoryRadio") ;
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


function closeCadresInfoDiv(id,type)
{
	/*var ancElmt = document.getElementById(id);
	if(ancElmt)
		ancElmt.style.display = 'none';*/
	var elmtId = id.substring(0,(id.lastIndexOf('_')));

	if(!elmtId)
		alert("No ID present to close container");
			
	var myAnim = new YAHOO.util.Anim(elmtId, {
		height: {
			to: 30 
		} 
	}, 1, YAHOO.util.Easing.easeIn);

	myAnim.animate();

	cleanCadresInfoDiv(type);

	var animElmt = document.getElementById(elmtId);
	if(animElmt)
		animElmt.style.display = 'none';
}
function showEventForCadres(results,jsObj)
{	
	var taskType = jsObj.displayType;
	var eventsHeadElmt = document.getElementById(taskType+"CadreDivHead");
	var eventsBodyElmt = document.getElementById(taskType+"CadreDivBody");
	
	if(eventsHeadElmt)
		eventsHeadElmt.innerHTML="Select Cadres";
	if(results.length == 0)
	{
		eventsBodyElmt.innerHTML="No Cadres Present";
		return;
	}
	

	var dtSource = new Array();

/*	var str='';
	str+='<table>';
	for(var i in results)
	{		
		str+='<tr>';			
		if(jsObj.cadreLevel == 'locationLevel')
		{
			str+='<td><input type="checkbox" name="eventCadreCheck" id="'+taskType+'_'+results[i].id+'" value="'+results[i].name+'"/></td>';
			str+='<td><span id="'+results[i].id+'_span">'+results[i].name+'</span></td>';
		}
		else if(jsObj.cadreLevel == 'cadreLevel')
		{
			str+='<td><input type="checkbox" name="eventCadreCheck" id="'+taskType+'_'+results[i].cadreID+'" value="'+results[i].firstName+' '+results[i].middleName+' '+results[i].lastName+'"/></td>';
			str+='<td><span id="'+results[i].cadreId+'_span">'+results[i].firstName+' '+results[i].middleName+' '+results[i].lastName+'</span></td>';			
		}
		str+='</tr>';
	}
	str+='<tr>';
	str+='<td colspan="2" align="center"><input type="button" onclick="addCadresToEvent(\''+taskType+'\')" value="Select Cadres"></td>';
	str+='<tr>';
	str+='</table>';

	if(eventsBodyElmt)
		eventsBodyElmt.innerHTML=str;*/
	
	
	

	for(var i in results)
	{
		if(jsObj.cadreLevel == 'locationLevel')
		{
			var obj={
						check:'<input type="checkbox" name="eventCadreCheck" id="'+taskType+'_'+results[i].id+'" value="'+results[i].name+'"/>',
						name:results[i].name
					};
		}
		else if(jsObj.cadreLevel == 'cadreLevel')
		{
			var obj={
						check:'<input type="checkbox" name="eventCadreCheck" id="'+taskType+'_'+results[i].cadreID+'" value="'+results[i].firstName+' '+results[i].middleName+' '+results[i].lastName+'"/>',
						name:results[i].firstName+' '+results[i].middleName+' '+results[i].lastName
					};
		}
		dtSource.push(obj);
	}

	var myColumnDefs = [ 
		{key:"check",label:"Select",resizeable:true}, 
		{key:"name", sortable:true,label:"Name",resizeable:true}	            
	]; 
	
	var myDataSource = new YAHOO.util.DataSource(dtSource); 
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	myDataSource.responseSchema = { 
		fields: ["check","name"] 
	}; 
	var myConfigs = {
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage: 10
		})
	};
			
	cadreDataTable = new YAHOO.widget.DataTable(taskType+"CadreDivBody",myColumnDefs, myDataSource,myConfigs); 
	
	var cElmt = document.createElement('div');
	var str='';
	str+='<input type="button" onclick="selectCadresToEvent(\''+taskType+'\')" value="Select All">';	
	str+='<input type="button" onclick="deselectCadresToEvent(\''+taskType+'\')" value="Deselect All">';
	str+='<input type="button" onclick="addCadresToEvent(\''+taskType+'\')" value="Add Organizers">';
	cElmt.innerHTML=str;

	var parentElmt = document.getElementById(taskType+"CadreDivBody");
	parentElmt.appendChild(cElmt);
}

function selectCadresToEvent(type)
{
	var elements = document.getElementsByTagName('input'); 
	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="checkbox" && elements[i].name=="eventCadreCheck")  
		{
			elements[i].checked=true;				
		}
	}
}
function deselectCadresToEvent(type)
{
	var elements = document.getElementsByTagName('input'); 
	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="checkbox" && elements[i].name=="eventCadreCheck")  
		{
			elements[i].checked=false;				
		}
	}
}

function addCadresToEvent(type)
{			
	var elements = document.getElementsByTagName('input'); 		
	
	var length;
	var array = new Array();
	if(type == 'event' || type == 'Editevent')
	{
		length = eventCadresArray.length;
		array  = eventCadresArray;
	}
	else if(type == 'action')
	{
		length = actionCadresArray.length;
		array = actionCadresArray;
	}
	
	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="checkbox" && elements[i].name=="eventCadreCheck" && elements[i].checked==true)
		{				
			var eid = elements[i].id.substring((elements[i].id.indexOf('_'))+1,elements[i].id.length);					
			var eventCadresObj ={
									id:eid,
									name:elements[i].value								
								};
			if((type == 'event' || type == 'Editevent')&& hasObjectInArray(array,eventCadresObj.id))
				eventCadresArray.push(eventCadresObj);
			else if(type == 'action' && hasObjectInArray(array,eventCadresObj.id))
				actionCadresArray.push(eventCadresObj);
		}			
	}
	

	if((type == 'event' && eventCadresArray.length == 0) ||(type == 'action' && actionCadresArray.length == 0))
	{
		alert("No cadres has been selected");
		return;
	}	
	else
	{			
		addCadresToEventPanel(type);
	}
}

function hasObjectInArray(array,cId)
{
	var status=true;
	for(var i=0; i<array.length;i++ )
	{ 
		if(array[i].id==cId)
			status=false; 
	}	
	return status;
}

function addCadresToEventPanel(type)
{		
	var cadreArray = new Array();

	if(type == 'Editevent' || type == 'Editaction')
	{
		addCadresToEditEventPanel(type);
	}

	var divElmt;
	if(type == 'event')
	{
		cadreArray = eventCadresArray;
		divElmt = document.getElementById("eventCadresDiv");
		if(cadreArray.length == 0 || !divElmt)
		return;

		var str = '';
		str+='<table class="selectedCadresDateEvent" width="100%">';			
		for(var i in cadreArray)
		{
			str+='<tr>';					
			str+='<td>';
			str+='<div id="cadreNameDiv_'+cadreArray[i].id+'" class="cadresDivForPanel"';
			//str+='onmouseover="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'block\';}"';
			//str+='onmouseout="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'none\';}">';
			str+='<span id="cadreSpan_'+cadreArray[i].id+'" class="cadresCloseSpan" onclick="closeEventCadrePanelDiv(this.id,\''+type+'\')"> X </span>';			
			str+=cadreArray[i].name;
			str+='</div>';			
			str+='</td>';
			str+='</tr>';
		}
		str+='</table>';
		if(divElmt)
			divElmt.innerHTML=str;
	}
	else if(type == 'action')
	{
		cadreArray = actionCadresArray;
		divElmt_Label = document.getElementById("cadresForActionPlanDiv_Label");
		divElmt_Body = document.getElementById("cadresForActionPlanDiv_Body");

		if(cadreArray.length == 0 || !divElmt_Label || !divElmt_Body)
		return;

		var labelstr='';
		labelstr+='Action Organizers';
		
		var str = '';	
		str+='<div>';	
		for(var i in cadreArray)
		{				
			str+='<div id="cadreNameDiv_'+cadreArray[i].id+'" class="cadresDivForPanel"';
			//str+='onmouseover="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'block\';}" ';
			//str+='onmouseout="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'none\';}">';
			str+='<span id="cadreSpan_'+cadreArray[i].id+'" class="cadresCloseSpan" onclick="closeEventCadrePanelDiv(this.id,\''+type+'\')"> X </span>';			
			str+=cadreArray[i].name;
			str+='</div>';							
		}
		str+= '</div>';	

		if(divElmt_Label)
			divElmt_Label.innerHTML=labelstr;
		if(divElmt_Body)
			divElmt_Body.innerHTML=str;		
	}	
	
	cleanCadresInfoDiv(type);
	closeCadresInfoDiv("cadreLevelDivId_"+type+"_anc");
}

function cleanCadresInfoDiv(type)
{
	var actionRegionSelectLabel = document.getElementById(type+'_region_select_Label');
	var actionRegionSelectData = document.getElementById(type+'_region_select_Data');
	var actionRegionSubmit = document.getElementById(type+'_region_submit');
	var actionRegionTypeLabel = document.getElementById(type+'_region_type_Label');
	var actionRegionTypeData = document.getElementById(type+'_region_type_Data');
	var eventCadreDivHeadElmt = document.getElementById(type+"CadreDivHead");
	var eventCadreDivBodyElmt = document.getElementById(type+"CadreDivBody");


	if(actionRegionSelectLabel && actionRegionSelectData && actionRegionSubmit && actionRegionTypeLabel && actionRegionTypeData && eventCadreDivHeadElmt && eventCadreDivBodyElmt)
	{
		actionRegionSelectLabel.innerHTML="";
		actionRegionSelectData.innerHTML="";
		actionRegionSubmit.innerHTML="";
		actionRegionTypeLabel.innerHTML="";
		actionRegionTypeData.innerHTML="";
		eventCadreDivHeadElmt.innerHTML="";
		eventCadreDivBodyElmt.innerHTML="";
	}
	/*if(type == 'action')
	{
		var actionPlanDivLabel = document.getElementById("cadresForActionPlanDiv_Label");
		var actionPlanDivBody = document.getElementById("cadresForActionPlanDiv_Body");
		if(actionPlanDivLabel && actionPlanDivBody)
		{
			actionPlanDivLabel.innerHTML='';
			actionPlanDivBody.innerHTML='';
		}
	}*/
}

function closeEventCadrePanelDiv(id,type)
{
	var cadreArr,divElmt;

	if(type == 'event')
	{
		divElmt = document.getElementById("eventCadresDiv");			
		for(var i=0; i<eventCadresArray.length;i++ )
		{ 
			if(eventCadresArray[i].id==id.substring(id.indexOf('_')+1,id.length))
				eventCadresArray.splice(i,1); 
		} 	
		if(eventCadresArray.length == 0)
		divElmt.innerHTML='<span style="color:#CFCFCF">No Organizers For Event</span>';
	}
	else if(type == 'action')
	{
		divElmt = document.getElementById("actionPlansDiv");			
		for(var i=0; i<actionCadresArray.length;i++ )
		{ 
			if(actionCadresArray[i].id==id.substring(id.indexOf('_')+1,id.length))
				actionCadresArray.splice(i,1); 
		} 	
		if(actionCadresArray.length == 0)
		divElmt.innerHTML='<span style="color:#CFCFCF">No Organizers For Action Plan</span>';
	}
	
	var elmt = document.getElementById("cadreNameDiv_"+id.substring(id.indexOf('_')+1,id.length));

	if(elmt)
	elmt.style.display='none';
}

function addActionPlanForEditablePanel(type)
{		
	actionPlansElmt = document.getElementById("actionPlanDiv_Body");
	var actionDivElmt = document.getElementById("actionDetailsDiv");

	var actionPlanValue = document.getElementById('actionPlanText').value;
	var targetDateValue = document.getElementById('actionTargetDateText').value;
	//var organisersSelectElmt = document.getElementById('actionOrganisersSelect');
	//var organisersValue = organisersSelectElmt.options[organisersSelectElmt.selectedIndex].value;
	
	
	/*var actDemoArray = new Array();
	for(var a=0;a<actionPlanArray;a++)
	{
		var targetDateObj = getDateTime(actionPlanArray[a].targetDate);
		var actObj={
						action:actionPlanArray[a].action,
						targetDate:targetDateObj.day+'/'+targetDateObj.month+'/'+targetDateObj.year,
						actionPlanOrganizers:actionPlanArray[a].actionPlanOrganizers	
				   }
		actDemoArray.push(actObj);
	}*/
	
	var actionObj = {
						action:actionPlanValue,
						targetDate:targetDateValue,
						actionPlanOrganizers:actionCadresArray			
					};
	actionPlanArray.push(actionObj);

	
	
	var divStr='';
	divStr+='<div id="newImpDateDiv">';
	divStr+='<table class="selectedDateEvent" width="100%">';		
	divStr+='<tr>';		
	divStr+='<td>';		
	for(var i=0;i<actionPlanArray.length;i++)
	{
		divStr+='<div class="ImpDateDetailDiv" id="actionPlanDiv_'+i+'">';
		divStr+='<div id="actionPlanDiv_'+i+'_head" class="ImpDateDetailDivHead">';			
		divStr+='<span id="actionPlanClose_'+actionPlanArray[i].action+'" class="cadresCloseSpan" onclick="deleteActionPlanFormEventPanel(this.id,\''+type+'\')"> X </span>';	
		divStr+='<span id="actionPlanDiv_'+i+'_head" class="cadresCloseSpan" onclick="showCreatedActionPlan(this.id,\''+type+'\')"> Edit </span>';	
		divStr+=actionPlanArray[i].action+' - '+actionPlanArray[i].targetDate+'</div>';
		divStr+='<div id="actionPlanDiv_'+i+'_body" class="ImpDateDetailDivBody"></div>';
		divStr+='</div>';
	}

	divStr+='</td>';
	divStr+='</tr>';
	divStr+='</table>';
	divStr+='</div>';

	if(actionPlansElmt)
		actionPlansElmt.innerHTML = divStr;
}
function addActionPlanToEvent(type)
{	
	var actionPlansElmt; 
	if(type == 'eventAction')
	{			
		actionPlansElmt = document.getElementById("actionPlanDiv_Body");
	}
	else if(type == 'EditactionPlan')
	{
		//actionPlansElmt = document.getElementById("actionPlanDiv_Body");
		addActionPlanForEditablePanel(type);
		return;
	}

	
	var actionDivElmt = document.getElementById("actionDetailsDiv");

	var actionPlanValue = document.getElementById('actionPlanText').value;
	var targetDateValue = document.getElementById('actionTargetDateText').value;
	//var organisersSelectElmt = document.getElementById('actionOrganisersSelect');
	//var organisersValue = organisersSelectElmt.options[organisersSelectElmt.selectedIndex].value;
	
	var actionPlanArrLength = actionPlanArray.length;		
	var actionObj = {
						action:actionPlanValue,
						targetDate:targetDateValue,
						actionPlanOrganizers:actionCadresArray			
					};
	actionPlanArray.push(actionObj);

	var divStr='';
	divStr+='<div id="newImpDateDiv">';
	divStr+='<table class="selectedDateEvent" width="100%">';
	divStr+='<tr>';		
	divStr+='<td>';		
	for(var i=0;i<actionPlanArray.length;i++)
	{
		divStr+='<div class="ImpDateDetailDiv" id="actionPlanDiv_'+i+'">';
		divStr+='<div id="actionPlanDiv_'+i+'_head" class="ImpDateDetailDivHead">';			
		divStr+='<span id="actionPlanClose_'+actionPlanArray[i].action+'" class="cadresCloseSpan" onclick="deleteActionPlanFormEventPanel(this.id,\''+type+'\')"> X </span>';	
		divStr+='<span id="actionPlanDiv_'+i+'_head" class="cadresCloseSpan" onclick="showCreatedActionPlan(this.id,\''+type+'\')"> Edit </span>';	
		divStr+=actionPlanArray[i].action+' - '+actionPlanArray[i].targetDate+'</div>';
		divStr+='<div id="actionPlanDiv_'+i+'_body" class="ImpDateDetailDivBody"></div>';
		divStr+='</div>';
	}

	divStr+='</td>';
	divStr+='</tr>';
	divStr+='</table>';
	divStr+='</div>';

	if(actionPlansElmt)
		actionPlansElmt.innerHTML = divStr;
   
	if(actionDivElmt)
		actionDivElmt.innerHTML="";
	
	document.getElementById('actionPlanText').value = '';
	document.getElementById('actionTargetDateText').value = '';
	if(document.getElementById("cadresForActionPlanDiv_Label"))
		document.getElementById("cadresForActionPlanDiv_Label").innerHTML='';
	if(document.getElementById("cadresForActionPlanDiv_Body"))
		document.getElementById("cadresForActionPlanDiv_Body").innerHTML='';
	

	cleanCadresInfoDiv('action');
	closeCadresInfoDiv('cadreLevelDivId_'+type+'_anc');

}

function deleteActionPlanFormEventPanel(id,type)
{			
	var actPlan = id.substring(id.indexOf('_')+1,id.length);
	var actionPlansDiv = document.getElementById("actionPlanDiv_Body");

	for(var i=0;i<actionPlanArray.length;i++)
	{
		if(actionPlanArray[i].actionPlan == actPlan)
			actionPlanArray.splice(i,1);
	}
	
	var elmt = document.getElementById(id);
	var parent = elmt.parentNode.parentNode;
	if(parent)
	{
		parent.innerHTML='';
		parent.style.display='none';
	}

	if(actionPlanArray.length == 0 && actionPlansDiv)
		actionPlansDiv.innerHTML='<span style="color:#CFCFCF">No Action Plans For Event</span>';	
}
function showCreatedActionPlan(divId,type)
{
	var score = divId.indexOf('_');	
	var lastScore = divId.lastIndexOf('_');	
	var index = divId.slice(score+1,lastScore);
	var lastDiv = divId.substring(lastScore+1);	
	var firstDiv = divId.substring(0,lastScore);	

	for(var i in actionPlanArray)
	{
		var arrayElmt = document.getElementById("actionPlanDiv_"+i+"_body");
		arrayElmt.style.display = 'none';
	}

	var bodyElmt = document.getElementById(firstDiv+'_body');
	bodyElmt.style.display = 'block';
	
	var str='';
	str+='<table>';
	str = getActionPlanString(actionPlanArray[index],"true",index,"");
	str+='<tr>';
	str+='<td align="right"> <input type="button" value="Update" onclick="upDateActionPlanToEvent(\''+index+'\',\''+type+'\')"/></td>';
	str+='<td align="left"> <input type="button" value="cancel" onclick="hideActionBodyDiv(\''+firstDiv+'\')"/></td>';
	str+='</tr>';
	str+='</table>';

	if(bodyElmt)
		bodyElmt.innerHTML=str;
	
}

function upDateActionPlanToEvent(index,type)
{
	var actionPlanValue = document.getElementById('actionPlanText_'+index).value;
	var targetDateValue = document.getElementById('actionTargetDateText_'+index).value;
	var organisersSelectElmt = document.getElementById('actionOrganisersSelect_'+index);
	var organisersValue = organisersSelectElmt.options[organisersSelectElmt.selectedIndex].value;
	
	var actionPlanObj= actionPlanArray[index];		
	
	actionPlanArray[index].actionPlan=actionPlanValue;
	actionPlanArray[index].targetDate=targetDateValue;
	actionPlanArray[index].organisers=[];
	
	var headElmt = document.getElementById('actionPlanDiv_'+index+'_head');
	var bodyElmt = document.getElementById('actionPlanDiv_'+index+'_body');
	
	var divStr='';
	divStr+='<span id="actionPlanClose_'+actionPlanArray[index].actionPlan+'" class="cadresCloseSpan" onclick="deleteActionPlanFormEventPanel(this.id,\''+type+'\')"> X </span>';	
	divStr+='<span id="actionPlanDiv_'+index+'_head" class="cadresCloseSpan" onclick="showCreatedActionPlan(this.id)"> Edit </span>';	
	divStr+=actionPlanValue+' - '+targetDateValue+'</div>';

	if(headElmt)
		headElmt.innerHTML=divStr;
	if(bodyElmt)
	{
		bodyElmt.innerHTML="";
		bodyElmt.style.display = 'none';
	}
}
function hideActionBodyDiv(div)
{		
	var elmt = document.getElementById(div+'_body');
	elmt.innerHTML='';
	elmt.style.display = 'none';
}
function showNewEventPopup()
{
	newEventDialog.show();		
}

function handleSubmit()
{
	var eventNameVal = document.getElementById("eventNameText").value;
	var startDateVal = document.getElementById("startDateText_new").value;
	var endDateVal = document.getElementById("endDateText_new").value;
	
	var startTimeHrs = document.getElementById("startTimeHrs");
	var startTimeHrsVal = startTimeHrs.options[startTimeHrs.selectedIndex].text;
	var startTimeMin = document.getElementById("startTimeMin");
	var startTimeMinVal = startTimeMin.options[startTimeMin.selectedIndex].text;
	
	var endTimeHrs = document.getElementById("endTimeHrs");		
	var endTimeHrsVal = endTimeHrs.options[endTimeHrs.selectedIndex].text;
	var endTimeMin = document.getElementById("endTimeMin");
	var endTimeMinVal = endTimeMin.options[endTimeMin.selectedIndex].text;

	var descVal = document.getElementById("descTextArea").value;
	descVal = removeEnterStrokeForString(descVal);

	var loctionLevelFieldElmt = document.getElementById("cadreLevelField");
	locationLevelFieldval = loctionLevelFieldElmt.options[loctionLevelFieldElmt.selectedIndex].text.toUpperCase();		
	
	var locationValueElmt;
	if(locationLevelFieldval == "STATE")
		locationValueElmt = document.getElementById("cadreLevelState");
	else if(locationLevelFieldval == "DISTRICT")
		locationValueElmt = document.getElementById("cadreLevelDistrict");
	else if(locationLevelFieldval == "CONSTITUENCY")
		locationValueElmt = document.getElementById("cadreLevelConstituency");
	else if(locationLevelFieldval == "MANDAL")
		locationValueElmt = document.getElementById("cadreLevelMandal");
	else if(locationLevelFieldval == "VILLAGE")
		locationValueElmt = document.getElementById("cadreLevelVillage");

	var locationValue = locationValueElmt.options[locationValueElmt.selectedIndex].value;
	

	//var organisersVal = document.getElementById("organisersText").value;

	var actionPlansVal='';
	
	selectedEventObj.userEventsId="";
	selectedEventObj.eventName=eventNameVal;
	selectedEventObj.startDate=startDateVal;
	selectedEventObj.endDate=endDateVal;
	selectedEventObj.startTimeHrs=startTimeHrsVal;
	selectedEventObj.startTimeMin=startTimeMinVal;					
	selectedEventObj.endTimeHrs=endTimeHrsVal;
	selectedEventObj.endTimeMin=endTimeMinVal;
	selectedEventObj.locationType=locationLevelFieldval;
	selectedEventObj.locationId=locationValue;
	selectedEventObj.desc=descVal;
	selectedEventObj.organizers=eventCadresArray;
	selectedEventObj.actionPlans=actionPlanArray;
	selectedEventObj.isDeleted="NO";
	selectedEventObj.task="createEvent";

	var rparam ="task="+YAHOO.lang.JSON.stringify(selectedEventObj);
	var url = "<%=request.getContextPath()%>/createEventAction.action?"+rparam;		

	callAjax(selectedEventObj,url);
}

function handleCancel()
{
	this.cancel();
}	

function removeElementsArray(arr)
{	
	if(!arr && arr.length == 0)
		return;
	
	for(var i=0;arr.length!=0;i++)
		arr.pop();
	
}
function buildNewEventPopup()
{		
	
/*	removeElementsArray(eventCadresArray);
	removeElementsArray(actionCadresArray);
	removeElementsArray(actionPlanArray);
	*/
	
	var elmt = document.getElementById('cadreManagementMainDiv');
	var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
			
	var divChild = document.createElement('div');
	divChild.setAttribute('id','newEventDiv');
	divChild.setAttribute('class','yui-skin-sam');
	
	var eventStr='';
	eventStr+='<div class="hd">Enter New Event Details...</div> ';
	eventStr+='<div class="bd">'; 
	eventStr+='<div id="eventDetailsDiv"><table class="selectedDateEvent">';
	eventStr+='<tr>';
	eventStr+='<th>Event Name</th>';
	eventStr+='<td colspan="3"><input type="text" size="50" id="eventNameText" name="eventNameText"/></td>';
	eventStr+='</tr>';

	eventStr+='<tr>';
	eventStr+='<th>Start Date</th>';
	eventStr+='<td>';
	eventStr+='<div><input type="text" id="startDateText_new" readonly="readonly" name="startDateText" value="'+date+'" onfocus="showDateCal(\'startDateText_new_Div\',this.value)"/></div>';
	eventStr+='<div id="startDateText_new_Div" class="tinyDateCal"></div>';
	eventStr+='</td>';
	eventStr+='<th>End Date</th>';
	eventStr+='<td><div><input type="text" id="endDateText_new" readonly="readonly" name="endDateText" value="'+date+'" onfocus="showDateCal(\'endDateText_new_Div\',this.value)"/></div>';
	eventStr+='<div id="endDateText_new_Div" class="tinyDateCal"></div></td>';
	eventStr+='</tr>';

	eventStr+='<tr>';
	eventStr+='<th>Start Time</th>';
	eventStr+='<td>';
	eventStr+='<select id="startTimeHrs" name="startTimeText" class="timeSelect">';		
	for(var i=0;i<=23;i++)
	{
		if(i==9)
			eventStr+='<option selected="selected">'+i+'</option>';
		eventStr+='<option>'+i+'</option>';
	}
	eventStr+='</select>';

	eventStr+='<select id="startTimeMin" name="startTimeText" class="timeSelect">';
	eventStr+='<option>00</option>';		
	eventStr+='<option>15</option>';
	eventStr+='<option selected="selected">30</option>';
	eventStr+='<option>45</option>';		
	eventStr+='</select>';
	eventStr+='</td>';
	eventStr+='<th>End Time</th>';
	eventStr+='<td>';
	eventStr+='<select id="endTimeHrs" name="endTimeText" class="timeSelect">';
	for(var i=0;i<=23;i++)
	{
		if(i==17)
			eventStr+='<option selected="selected">'+i+'</option>';
		eventStr+='<option>'+i+'</option>';
	}
	eventStr+='</select>';
	
	eventStr+='<select id="endTimeMin" name="startTimeText" class="timeSelect">';
	eventStr+='<option>00</option>';		
	eventStr+='<option>15</option>';
	eventStr+='<option selected="selected">30</option>';
	eventStr+='<option>45</option>';		
	eventStr+='</select>';

	eventStr+='</td>';
	eventStr+='</tr>';
			
	
	eventStr+='<tr>';
	eventStr+='<th>Location Level</th>';
	eventStr+='<td colspan="3"><select id="cadreLevelField" name="cadreLevel" onchange="getStateList()">';
	eventStr+='	<option	 value="0">Select Level</option>';		
	eventStr+='	<option  value="2">State</option>';	
	eventStr+='	<option  value="3">District</option>';
	eventStr+='	<option  value="4">Constituency</option>';	
	eventStr+='	<option  value="5">Mandal</option>';		
	eventStr+='	<option  value="6">Village</option>	';				
	eventStr+=' </select> <input type="hidden" name="cadreLevelValue" id="cadreLevelValue"></td>';
	eventStr+='</tr>';
	
	eventStr+='<tr>';
	eventStr+='<th>Location</th>';
	eventStr+='<td colspan="3">';
	eventStr+='	<select id="cadreLevelState" name="cadreLevelState" disabled = "true" class="cadreLevelSelect" onchange="setCadreValue(this.options[this.selectedIndex].value);										getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
	eventStr+='	<option> </option>';					
	eventStr+='	</select>'; 

		eventStr+='	<select id="cadreLevelDistrict" class="cadreLevelSelect" name="cadreLevelDistrict" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);							getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
	eventStr+='	<option></option>';					
	eventStr+='	</select>'; 
			
	eventStr+='	<select id="cadreLevelConstituency" class="cadreLevelSelect" name="cadreLevelConstituency" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);					getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
	eventStr+='	<option></option>';					
	eventStr+='	</select> ';
	
	eventStr+='	<select id="cadreLevelMandal" class="cadreLevelSelect" name="cadreLevelMandal" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);								getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
	eventStr+='	<option></option>';					
	eventStr+='	</select> ';
	
	eventStr+='	<select id="cadreLevelVillage" class="cadreLevelSelect" name="cadreLevelVillage" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value)">';
	eventStr+=' 	<option></option>';					
	eventStr+='	</select>';
	eventStr+='</td>';
	eventStr+='</tr>';

	eventStr+='<tr>';
	eventStr+='<th>Description</th>';
	eventStr+='<td colspan="3"><textarea rows="5" cols="50" id="descTextArea" name="descTextArea"></textarea></td>';
	eventStr+='</tr>';
	eventStr+='</table>';
	
	eventStr+='<table class="selectedDateEvent" width="100%">';
	eventStr+='<tr>';
	eventStr+='<th>Organizers</th>';
	eventStr+='<td><div id="eventCadresDiv"><span style="color:#CFCFCF">No Organizers For Event</span></div></td>';		
	eventStr+='</tr>';
	eventStr+='<tr>';
	eventStr+='<td colspan="2" align="right"><span class="buttonSpan" onclick="javascript:{document.getElementById(\'cadreLevelDivId_event\').style.display=\'block\';}">Add Organizers</span></td>';		
	eventStr+='</tr>';
	eventStr+='</table>';
	
	eventStr+=getOrganisersString("event");
	
	eventStr+='<table class="selectedDateEvent" width="100%">';
	eventStr+='<tr>';
	eventStr+='<th><div id="actionPlanDiv_Label">Action Plans</div></th>';
	eventStr+='<td><div id="actionPlanDiv_Body"><span style="color:#CFCFCF">No Action Plans For Event</span></div></td>';		
	eventStr+='</tr>';
	eventStr+='<tr>';
	eventStr+='<td colspan="2" align="right"><span class="buttonSpan" onclick="javascript:{document.getElementById(\'cadreLevelDivId_eventAction\').style.display=\'block\';removeElementsArray(actionCadresArray);}">Add Action Plan</span></td>';		
	eventStr+='</tr>';
	eventStr+='</table>';
	eventStr+='<div id="actionPlansDiv"></div>';
	eventStr+=createActionPlan("eventAction");
			

	/*eventStr+='<table class="cadreLevelDivClass">';
	eventStr+='<tr>';
	eventStr+='<th><div id="actionPlanLabelDiv"></div></th>';
	eventStr+='<td colspan="3"><div id="actionPlanDataDiv"></div></td>';		
	eventStr+='</tr>';
	eventStr+='</table>';*/


	/*eventStr+='<tr>';
	eventStr+='<td colspan="4" align="right"><a href="javascript:{}" onclick="createActionPlan()"> Create Action Plan</a></td>';		
	eventStr+='</tr>';
	eventStr+='<div id="actionDetailsDiv"></div>';*/
	
	eventStr+='</div></div>';

	divChild.innerHTML=eventStr;
	elmt.appendChild(divChild);
	
	if(newEventDialog)
		newEventDialog.destroy();

	newEventDialog = new YAHOO.widget.Dialog("newEventDiv",
			{ width : "800px", 
              fixedcenter : false, 
              visible : true,  
              constraintoviewport : true, 
			  iframe :true,
			  modal :true,
			  x:200,
			  y:100,
			  hideaftersubmit:true,
	          buttons : [ { text:"Create Event", handler:handleSubmit, isDefault:true }, 
                          { text:"Cancel", handler:handleCancel } ]
             } ); 
	newEventDialog.render();
} 
function getOrganisersString(regTask)
{
	var eventStr='';
	eventStr+='<div id="cadreLevelDivId_'+regTask+'" class="cadreLevelDivClass">';
	eventStr+='<div id="cadreLevelDivId_'+regTask+'_inner" class="cadreLevelDivClassInner">';
	eventStr+='<table class="selectedDateEvent" width="100%">';
	eventStr+='<tr>';
	eventStr+='<th>Select Organizers</th>';		
	eventStr+='<td colspan="2">';
	eventStr+='<input type="radio" name="sms_type" value="locationWise" onclick="javascript:{getUserLocationData(this.value,\''+regTask+'\')}"/> Location Wise';	
	eventStr+='<input type="radio" name="sms_type" value="cadreLevelWise" onclick="getUsersCadreLevelData(this.value,\''+regTask+'\')"/> Cadre Level Wise';
	eventStr+='</td>';
	eventStr+='<td  align="right">';			
	eventStr+='<a id="cadreLevelDivId_'+regTask+'_anc" href="javascript:{}" onclick="clearActionPlanDetails(this.id,\''+regTask+'\')">Close</a>';
	eventStr+='</td>';			
	eventStr+='</tr>';		
	eventStr+='<tr>';		
	eventStr+='<th align="left"><div id="'+regTask+'_region_type_Label"></div></th>';
	eventStr+='<td align="left"><div id="'+regTask+'_region_type_Data"></div></td>';				
	eventStr+='</tr><tr>';		
	eventStr+='	<th align="left"><div id="'+regTask+'_region_select_Label">	</div></th>';
	eventStr+='	<td align="left"><div id="'+regTask+'_region_select_Data">	</div>';
	eventStr+=' <div id="'+regTask+'_region_submit"></div></td>';
	eventStr+='</tr>';
	eventStr+='<tr>';		
	eventStr+='<th><div id="'+regTask+'CadreDivHead"></div></th>';
	eventStr+='<td colspan="3"><div id="'+regTask+'CadreDivBody"></div></td>';		
	eventStr+='</tr>';		
	eventStr+='</table>';		
	eventStr+='</div></div>';

	return eventStr;

}

function clearActionPlanDetails(id,type)
{
	if(document.getElementById('actionPlanText'))
		document.getElementById('actionPlanText').value = '';
	if(document.getElementById('actionTargetDateText'))
		document.getElementById('actionTargetDateText').value = '';
	
	if(document.getElementById("cadresForActionPlanDiv_Label"))
		document.getElementById("cadresForActionPlanDiv_Label").innerHTML='';
	if(document.getElementById("cadresForActionPlanDiv_Body"))
		document.getElementById("cadresForActionPlanDiv_Body").innerHTML='';
			
	removeElementsArray(actionCadresArray);
	removeElementsArray(actionPlanArray);

	closeCadresInfoDiv(id,type);
}

function buildNewImpDatePopup()
{
	var elmt = document.getElementById('cadreManagementMainDiv');
	var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();

			
	var divChild = document.createElement('div');
	divChild.setAttribute('id','newImpDateDiv');
	divChild.setAttribute('class','yui-skin-sam');
	
	var eventStr='';
	eventStr+='<div class="hd">New Date</div> ';
	eventStr+='<div class="bd">'; 
	eventStr+='<table>';
	eventStr+='<tr>';
	eventStr+='<th>Important Date Title</th>';
	eventStr+='<td colspan="3"><input type="text" size="50" id="ImpeventNameText" name="ImpeventNameText"/></td>';
	eventStr+='</tr>';

	eventStr+='<tr>';
	eventStr+='<th>Important Date</th>';
	eventStr+='<td>';
	eventStr+='<div><input type="text" id="ImpStartDateText_new" value="'+date+'" name="ImpStartDateText" readonly="readonly" onfocus="showDateCal(\'ImpStartDateText_new_Div\')"/></div>';
	eventStr+='<div id="ImpStartDateText_new_Div" class="tinyDateCal"></div>';
	eventStr+='</td>';		
	eventStr+='</tr>';

	eventStr+='<tr>';
	eventStr+='<th>Description</th>';
	eventStr+='<td colspan="3"><textarea rows="5" cols="50" id="ImpdescTextArea" name="ImpdescTextArea"></textarea></td>';
	eventStr+='</tr>';		

	eventStr+='<tr>';
	eventStr+='<th>Repeat Frequency</th>';
	eventStr+='<td>';
	eventStr+='<select id="repeatFreqSelect" class="timeSelect" onchange="showEndDateText(this.options[this.selectedIndex].text)">';
	eventStr+='<option value="No Repeat">No Repeat</option>';
	eventStr+='<option value="Yearly">Yearly</option><option value="Monthly">Monthly</option><option value="Weekly">Weekly</option></select></td>';
	eventStr+='<th>Repeat Until</th>';
	eventStr+='<td>';
	eventStr+='<div><input type="text" id="ImpEndDateText_new" readonly="readonly" value="'+date+'" name="ImpEndDateText" disabled="true" onfocus="showDateCal(\'ImpEndDateText_new_Div\')"/></div>';
	eventStr+='<div id="ImpEndDateText_new_Div" class="tinyDateCal"></div>';
	eventStr+='</td>';
	eventStr+='</tr>';		

	eventStr+='</table>';
	eventStr+='</div>';

	divChild.innerHTML=eventStr;
	elmt.appendChild(divChild);

	if(newDateDialog)
		newDateDialog.destroy();
	
	newDateDialog = new YAHOO.widget.Dialog("newImpDateDiv",
			{ width : "600px", 
              fixedcenter : false, 
              visible : true,  
              constraintoviewport : true, 
			  iframe :true,
			  modal :true,
			  x:200,
			  y:700,
			  hideaftersubmit:true,
	          buttons : [ { text:"Create New Date", handler:handleImpDateSubmit, isDefault:true }, 
                          { text:"Cancel", handler:handleImpDateCancel } ]
             } ); 
	newDateDialog .render(); 
}

function openSubUserRegPopup()
{
	var browser = window.open("subUserRegPageAction.action?registrationType=subUser","registerPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	browser.focus();
}
function openNewAnnouncementPopup()
{
	var newAnnouncementBrowser = window.open("newAnnouncementAction.action?windowTask=new","addNewAnnouncement","scrollbars=yes,height=600,width=600,left=200,top=200");
	newAnnouncementBrowser.focus();
}
function openEditAnnouncement()
{
	var editAnnouncementbrowser = window.open("openEditAnnouncementAction.action","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
	editAnnouncementbrowser.focus();
}
