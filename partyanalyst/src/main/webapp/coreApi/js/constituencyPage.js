 //ajaxcalls through newconstituencypage.js
 	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));	
// please do not try to edit these options which may cause the entire page to stop working.	
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//var spinner = '<div class="row"><div class="col-sm-12"><div class="square-to-circle"></div></div></div>';
var blockSpinner = '<div class="row"><div class="col-sm-12"><div class="block m_top10"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div></div>';
//var blockSpinner = '<div class="row"><div class="col-sm-12"><div class="block m_top10"><div class="square-to-circle"></div></div></div></div>';
var noData = "<div class='col-sm-12'><h5>NO DATA AVAILABLE</h5></div>";
var globalCasteColorObj = {"BC":"#867DC0","Minority":"#99CC67","SC":"#65A7E1","ST":"#7DC1C2","OC":"#E58D45"};
var globalColorNews = {"Without Money":"#F36E8F","<10L":"#0C9516",">10L":"#8E4654","State Wide Issue":"#FE9900"};
var globalFromDate = moment().subtract(3,'year').format("DD/MM/YYYY");
var globalToDate = moment().format("DD/MM/YYYY");
var propertyIdGlobalStr=[26,28,29,32];
var stateId = $("#getMenuLocations").attr("menu-location-state");
var districtId = $("#getMenuLocations").attr("menu-location-district");
var parliamentId = $("#getMenuLocations").attr("menu-location-parliament");
var constituencyId = $("#getMenuLocations").attr("menu-location-constituency");
var mandalId = $("#getMenuLocations").attr("menu-location-mandal");
var panchayatId = $("#getMenuLocations").attr("menu-location-panchayat");
var locationLevelName = $("#getMenuLocations").attr("menu-location-levelName");
var locationLevelId = $("#getMenuLocations").attr("menu-location-levelId");
var locationName='';
locationName = $("#getMenuLocations").attr("menu-location-name");
var userAccessLevelValuesArray=[];
var commitessArr=["mandalLevelGraph","villageLevelGraph","affMandalLevelGraph","affVillageLevelGraph"];
var grivanceIdsArr=["grivanceId","trustId"];
var grivanceColorObj={"APPROVED":"#2DCC70","COMPLETED":"#449C43","IN PROGRESS":"#FFB84F","NOT ELIGIBLE":"#C0392B","NOT POSSIBLE":"#EF8379","NOT VERIFIED":"#31708F"}
var insuranceColorObj={"Waiting For Documents":"#2DCC70","Documents Submitted In Party":"#449C43","Forwarded to Insurance":"#FFB84F","Closed at Insurance":"#8F43AF","Closed at Party":"#9B88B3","Approved - Compensated":"#2BCD72","Closed Letters":"#32708F","Account Rejected":"#65CBCC"}
var globalColorPartyNames={"CPI":"#E05B5B","CPM":"#DD0000","INC":"#74C0F3","AIMIM":"#61C692","BJP":"#FA9F4B","TDP":"#FDD503","OTHERS":"#E5E5E5","YSRC":"#017e8e","TRS":"#FF5F12","Congress Kutami":"#0710ba","TDP & BJP":"#99ba06","MahaKutami":"#6C8CBD","PRP & ManaParty":"#F15C80","TDP + BJP":"#696969","PRP":"#009900","UPA":"#8085E9","TF":"#4584E9","NDA":"#8565E4"}
var electionTypeVal = [1,2,3,4];
var defaultAlertCategoryIds=[1];
var electionYrVal = [];
var electionSubTypeArr=["MAIN"];
var electionYearsSubTypeArr=["MAIN","BYE"];
function getDay(){
	var date = new Date();
	var dd = date.getDate(); 
	return dd;
}
//Tours And Meetings And Alerts Dates Start 
var customStartMeetingsDate = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
var customEndMeetingsDate = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
var customStartATMDate = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
var customEndATMDate = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');


var customStartGrivanceDate = moment().subtract(3, 'years').startOf('year').format('DD/MM/YYYY')
var customEndGrivanceDate = moment().format('DD/MM/YYYY');

var customStartInsuranceDate = moment().subtract(3, 'years').startOf('year').format('DD/MM/YYYY')
var customEndInsuranceDate = moment().format('DD/MM/YYYY');

var customStartNtrTrustDate = moment().subtract(3, 'years').startOf('year').format('DD/MM/YYYY')
var customEndNtrTrustDate = moment().format('DD/MM/YYYY');

var globalboardLevelId='';
//Tours And Meetings And Alerts Dates End 
// please do not try to edit these options which may cause the entire page to stop working. //end
/* location Values Start*/
function onLoadLocValue()
{
	userAccessLevelValuesArray = [];
	locationLevelVal = '';
	globalboardLevelId='';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId ;
		userAccessLevelValuesArray.push(stateId)
		globalboardLevelId =0;
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId;
		userAccessLevelValuesArray.push(districtId)
		globalboardLevelId =3;
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
		locationLevelVal = parliamentId 
		globalboardLevelId =4;
	}else if(locationLevelId == '4' || locationLevelId == '11' )
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId;
		userAccessLevelValuesArray.push(constituencyId)
		globalboardLevelId =4;
	}else if(locationLevelId == '5' || locationLevelId == '12' )
	{
		locationLevelId = '5'
		locationLevelVal = mandalId.substring(1,mandalId.length); 		
		userAccessLevelValuesArray.push(mandalId.substring(1,mandalId.length));
		globalboardLevelId =5;
	}else if(locationLevelId == '6' || locationLevelId == '13' )
	{
		locationLevelId = '6'
		locationLevelVal = panchayatId.substring(1,panchayatId.length); 	
		userAccessLevelValuesArray.push(panchayatId.substring(1,panchayatId.length))
		globalboardLevelId =7;
	}else if(locationLevelId == '7')
	{
		locationLevelVal = mandalId.substring(1,mandalId.length); 	
		userAccessLevelValuesArray.push(mandalId.substring(1,mandalId.length))
		globalboardLevelId =5;
	}else if(locationLevelId == '8')
	{
		locationLevelVal = panchayatId.substring(1,panchayatId.length); 	
		userAccessLevelValuesArray.push(panchayatId.substring(1,panchayatId.length))
		globalboardLevelId =7;
	}
}
/* location Values End*/
onLoadLocValue()
onLoadClicks();
onLoadInitialisations();
onLoadAjaxCalls();

function onLoadInitialisations()
{
	minimise('.right-nav-list li',6);
	rightNav();
	collapseClick();
	responsiveTabs();
	//Menu Calls
	menuCalls(2,'1','');	
	menuCalls(9,'1','');
	$(window).scroll(function(){
		var windowScrollTop = $(window).scrollTop();
		var header = $('.black-border')
		if (windowScrollTop>50) {
			header.addClass("header-fixed");
		} else {
			header.removeClass("header-fixed");
		}
		scrollAnimation();
	});
	function scrollAnimation(){
		var winScroll = $(window).scrollTop(),
            winHeight = $(window).outerHeight();
        $('.scroll-animation.scroll-animate').each(function(){
            if (winScroll > $(this).offset().top - winHeight + 200) {
                $(this).removeClass("scroll-animate");
            }
        });
        $('.dotted-path').each(function(){
        	if (winScroll > $(this).offset().top - winHeight + 200) {
        		$(this).removeClass("hidden-path");
        	}
        });
	}
	//Meetings	Start
	$("#dateRangeIdForMeetings").daterangepicker({
		opens: 'left',
		startDate: customStartMeetingsDate,
        endDate: customEndMeetingsDate,
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		}
	});
	//Tours Start
	$("#tourNewDateRangePickerId").daterangepicker({
		opens: 'left',
	    startDate: customStartATMDate,
        endDate:customEndATMDate,
		locale: {
			format: 'DD/MM/YYYY'
		},
		ranges: { ////moment().endOf('Year')
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(1, 'years').startOf('year'), moment()],
        }
	});
	//Grievance
	$("#grievanceNewDateRangePickerId").daterangepicker({
		opens: 'left',
	    startDate: customStartGrivanceDate,
        endDate:customEndGrivanceDate,
		locale: {
			format: 'DD/MM/YYYY'
		},
		ranges: { ////moment().endOf('Year')
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(3, 'years').startOf('year'), moment()],
        }
	});
	//Insurance
	$("#insuranceNewDateRangePickerId").daterangepicker({
		opens: 'left',
	    startDate: customStartInsuranceDate,
        endDate:customEndInsuranceDate,
		locale: {
			format: 'DD/MM/YYYY'
		},
		ranges: { ////moment().endOf('Year')
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(3, 'years').startOf('year'), moment()],
        }
	});
	//Ntr Trust
	$("#ntrTrustNewDateRangePickerId").daterangepicker({
		opens: 'left',
	    startDate: customStartNtrTrustDate,
        endDate:customEndNtrTrustDate,
		locale: {
			format: 'DD/MM/YYYY'
		},
		ranges: { ////moment().endOf('Year')
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(3, 'years').startOf('year'), moment()],
        }
	});
	//Alert Start
	$("#alertNewDateRangePickerId").daterangepicker({
		opens: 'left',
	    startDate: customStartATMDate,
        endDate:customEndATMDate,
		locale: {
			format: 'DD/MM/YYYY'
		},
		ranges: { ////moment().endOf('Year')
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(1, 'years').startOf('year'), moment()],
        }
	});
}
function onLoadAjaxCalls()
{	
	//Assembly Block
	getElectionTypes("onload");
	if(locationLevelId == '4'){
		$(".assemblyElectionBlockCls").show();
		getDetailedElectionInformaction(2,constituencyId);
	}else if(locationLevelId == '10'){
		$(".assemblyElectionBlockCls").show();
		getDetailedElectionInformaction(1,parliamentId);
	}else{
		$(".assemblyElectionBlockCls").hide();
	}
	
	/* $("#enrolmentYears").chosen();
	getEnrollmentIds();//Enrolment Years
	getPublications();//Publications	
	getLocationWiseCommitteesCount(2);//Committee
	getLocationWiseMeetingsCount();	//Meetings
	getLocationWiseMeetingsCountDetails(1);
	getLocationWiseMeetingsCountDetails(2);
	//getLocationWiseMeetingsCountDetails(3);
	getLocationWiseSpecialMeetingsMeetingsExpanction(3,0);
	 //candidate Profiles 1st block
	if(locationLevelId == "2"){
		getPartyWiseMPandMLACandidatesCounts();
	}else{
		 $("#statelevelMPMLAId").html(" ");
	}
	getCandidateAndPartyInfoForConstituency();
	 //Second Block
	if(locationLevelId == "2"){
		$("#levelWiseCountDivId").html("");
		getCountsForStateLevel();
	}else{
		 $("#statelevelWiseCountDivId").html("");
		 getCountsForConstituency();
	}
	//Assembly Block
	getElectionTypes("onload");
	if(locationLevelId == '4'){
		$(".assemblyElectionBlockCls").show();
		getDetailedElectionInformaction(2,constituencyId);
	}else if(locationLevelId == '10'){
		$(".assemblyElectionBlockCls").show();
		getDetailedElectionInformaction(1,parliamentId);
	}else{
		$(".assemblyElectionBlockCls").hide();
	}
	getVotersAndcadreAgeWiseCount(22,4);//Constituency Voter Information
	//caste information
	getCasteGroupNAgeWiseVoterNCadreCounts(0,"onload","All",22,4,"desending")
	getVotersCastGroupWiseCount(22,4)
	getActivityStatusList();
	//cadre Information Block
	getLocationTypeWiseCadreCount("");
	getAgeRangeGenerAndCasteGroupByCadreCount(4);
	//Tours 
	getLocationWiseTourMembersComplainceDtls();
	// Benefits
	getGovtSchemeWiseBenefitMembersCount();
	//Grievance And Insurance
	getGrivenceOverviewDtls(""); 
	getInsuranceOverviewDetails("");
 	getTrustEducationOverviewDetails("");
	// Nominated Posts
	getPositionWiseMemberCount();
	getNominatedPostApplicationDetails();
	getNominatedPostStatusWiseCount();
	getLevelWisePostsOverView(); 
	//Alerts
	getTotalAlertDetailsForConstituencyInfo(defaultAlertCategoryIds);
	getDesignationWiseAlertsOverview(defaultAlertCategoryIds); */
	/* setTimeout(function(){ 
		//News Block
		getPrintMediaCountsForConstituencyPage()
		getLeadersInNewsForConstituencyPage();
		//Problems
		getDetailedGovtOverAllAnalysisProblemsForConstituencyPage("overAll")
		for(var i in propertyIdGlobalStr){
			getDetailedGovtOverAllAnalysisProblemsForConstituencyPage(propertyIdGlobalStr[i]) 
		}
	}, 2000); */
}			
function onLoadClicks()
{
	$(document).on("click","[detailed-block]",function(){
		var blockName = $(this).attr("detailed-block");
		if(blockName == 'grievance')
		{ 
			window.open('areaWiseGrievanceDashboardAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'&locationLevelName='+locationLevelName+'&customStartGrivanceDate='+customStartGrivanceDate+'&customEndGrivanceDate='+customEndGrivanceDate+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
		}else if(blockName == 'nominatedPosts'){
			window.open('areaWiseDashboardDetailedViewAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
		}else if(blockName == 'election'){
			window.open('areaWiseElectionDashboardAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'&constituencyId='+constituencyId+'&locationLevelName='+locationLevelName+'&parliamentId='+parliamentId+'&locationName='+locationName+'&districtId='+districtId+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
		}else if(blockName == 'benefits'){
			window.open('areaWiseBenefitsViewAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'&locationLevelName='+locationLevelName+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
		}else if(blockName == 'insurance'){
			window.open('areaWiseInsuranceViewAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'&locationLevelName='+locationLevelName+'&customStartInsuranceDate='+customStartInsuranceDate+'&customEndInsuranceDate='+customEndInsuranceDate+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
		}else if(blockName == 'ntrTrust'){
			window.open('areaWiseNtrTrustViewAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'&locationLevelName='+locationLevelName+'&customStartNtrTrustDate='+customStartNtrTrustDate+'&customEndNtrTrustDate='+customEndNtrTrustDate+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
		}
		
	});
	$(document).on("click",".hideShowBtn",function(){
		var blockType = $(this).html();
		if(blockType == '+')
		{
			$(this).html("-");
		}else{
			$(this).html("+");
		}
		$(".castesWiseVoterBlock").toggle();
	});
	$(document).on("click","[menu-click]",function(){
		var length = $(this).text().length;
		var levelId = $(this).attr("levelId");
		var locationId = $(this).attr("menu-click");
		var levelName = $(this).attr("menu-levelname");
		var levelType = $(this).attr("menu-type");
		$(".casteGroupTypeDiv li").removeClass("active")
		$(".casteGroupTypeDiv li:nth-child(1)").addClass("active");
		if(levelName == 'parliament')
		{
			$("[menu-name="+levelName+"]").closest("li").show();
			$("[menu-name='district']").closest("li").hide();
		}else if(levelName == 'district'){
			$("[menu-name="+levelName+"]").closest("li").show();
			$("[menu-name='parliament']").closest("li").hide();
		}
		$("[menu-name="+levelName+"]").html($(this).html()+"("+$(this).attr("short-name")+")");
		$("[menu-name="+levelName+"]").attr("menu-type",$(this).attr("menu-type"));
		//$("[menu-name="+levelName+"]").width(((length)*(10)));
		$("[levelId="+levelId+"]").removeClass("active");
		if(levelId == 3 || levelId == 10)
		{
		  $("[menu-type='District'],[menu-type='Parliment']").removeClass("active");
		}
		$(this).addClass("active");
		$("[menu-name="+levelName+"]").closest("li").show();
		//$("[menu-name="+levelName+"]").addClass("active");
		$("#getMenuLocations").attr("menu-location-"+levelName+"",locationId);
		$("#getMenuLocations").attr("menu-location-levelName",levelName);
		$("#getMenuLocations").attr("menu-location-name",$(this).html()+"("+$(this).attr("short-name")+")");
		if(levelType == 'Municipality')
		{
			locationLevelId = 7;
			$("#getMenuLocations").attr("menu-location-levelId","7");
			$("[menu-name="+levelName+"]").attr("levelid",7);
		}else if(levelType == 'Ward'){
			locationLevelId = 8;
			$("#getMenuLocations").attr("menu-location-levelId","8");
		}else{
			locationLevelId = ((levelId)-(1));
			$("#getMenuLocations").attr("menu-location-levelId",((levelId)));
		}
		locationLevelName = levelName;
		if(levelId == 10)
		{
			$("#getMenuLocations").attr("menu-location-district","");
		}
		if(levelId == 3 || levelId == 10)
		{
			districtId = locationId;
			$("#getMenuLocations").attr("menu-location-constituency","");
			$("#getMenuLocations").attr("menu-location-mandal","");
			$("#getMenuLocations").attr("menu-location-panchayat","");
			$("[menu-name='constituency'],[menu-name='mandal'],[menu-name='panchayat']").closest("li").hide();
		}else if(levelId == 4 || levelId == 11)
		{
			constituencyId = locationId;
			$("#getMenuLocations").attr("menu-location-mandal","");
			$("#getMenuLocations").attr("menu-location-panchayat","");
			$("[menu-name='mandal'],[menu-name='panchayat']").closest("li").hide();
		}else if(levelId == 5 || levelId == 12)
		{
			mandalId = locationId;
			$("#getMenuLocations").attr("menu-location-panchayat","");
			$("[menu-name='panchayat']").closest("li").hide();
		}else if(levelId == 6 || levelId == 13)
		{
			panchayatId = locationId
		}
		/* if(levelId != '5' || levelId != 5){menuCalls(levelId,locationId,'')}else{ */
			menuCalls(levelId,locationId,$("#constituencyMenu li.active").attr("menu-click"))
		//}	
	});
	//Meetings
	$('#dateRangeIdForMeetings').on('apply.daterangepicker', function(ev, picker) {
		customStartMeetingsDate = picker.startDate.format('DD/MM/YYYY');
		customEndMeetingsDate = picker.endDate.format('DD/MM/YYYY');
		getLocationWiseMeetingsCount();	//Meetings
		getLocationWiseMeetingsCountDetails(1);	
		getLocationWiseMeetingsCountDetails(2);
		//getLocationWiseMeetingsCountDetails(3);
		getLocationWiseSpecialMeetingsMeetingsExpanction(3,0);
	});
	//Tours
	$('#tourNewDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		customStartATMDate = picker.startDate.format('DD/MM/YYYY');
		customEndATMDate = picker.endDate.format('DD/MM/YYYY');
		getLocationWiseTourMembersComplainceDtls();
	});
	//Alert
	$('#alertNewDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		customStartATMDate = picker.startDate.format('DD/MM/YYYY');
		customEndATMDate = picker.endDate.format('DD/MM/YYYY');
		$(".alertCategoryWiseCls li").removeClass("active");
		$(".alertCategoryWiseCls li:nth-child(1)").addClass("active");
		getTotalAlertDetailsForConstituencyInfo(defaultAlertCategoryIds);
		getDesignationWiseAlertsOverview(defaultAlertCategoryIds);
	});
	$('#grievanceNewDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		
		customStartGrivanceDate = picker.startDate.format('DD/MM/YYYY');
		customEndGrivanceDate = picker.endDate.format('DD/MM/YYYY');
		
		getGrivenceOverviewDtls(""); 
		
	});
	$('#insuranceNewDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		
		customStartInsuranceDate = picker.startDate.format('DD/MM/YYYY');
		customEndInsuranceDate = picker.endDate.format('DD/MM/YYYY');
		
		getInsuranceOverviewDetails("");
	});
	$('#ntrTrustNewDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		
		customStartNtrTrustDate = picker.startDate.format('DD/MM/YYYY');
		customEndNtrTrustDate = picker.endDate.format('DD/MM/YYYY');
		getTrustEducationOverviewDetails("");
	});
	$(document).on("click","[active-type] li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var name = $(this).closest("ul").attr("active-type");
		var assendingType = $(this).attr("type")
		var publicationId = $("#publicationCasteId").val();
		var enrollmentId = $("#enrollmentCasteId").val();
		if(name == "casteSorting")
		{
			$("#totalCastesAndCadrePercBarGraph").html(spinner);
			$("#totalCastesAndCadrePercBarGraph").closest(".scollerDiv").mCustomScrollbar("destroy");
			getCasteGroupNAgeWiseVoterNCadreCounts(0,"sortingType","All",publicationId,enrollmentId,assendingType)			
		}
	});
	$(document).on("click",".alertCategoryWiseCls li",function(){
		$(this).parent("ul").find("li").removeClass("active");
		$(this).addClass("active");
		defaultAlertCategoryIds=[];
		var ids=$(this).attr("attr_type").split(',');
		defaultAlertCategoryIds = ids.map(Number);
		getTotalAlertDetailsForConstituencyInfo(defaultAlertCategoryIds);
		getDesignationWiseAlertsOverview(defaultAlertCategoryIds);
	});
	$(document).keydown(function(event){
		if(event.keyCode==123){
			alert("Hoo no! don't try to expose me");
			return false;
		}
		else if(event.ctrlKey && event.shiftKey && event.keyCode==73){        
			alert("Hoo no! don't try to expose me");
			return false;  //Prevent from ctrl+shift+i
		}
	});
	$(document).on("contextmenu",function(){
		alert("Sorry! We have prevented right click usage");
		return false;
    }); 
	$(document).on("change","#enrolmentYears",function(){
		getLocationWiseCommitteesCount($(this).val());
	});
	$(document).on("click","#menuHeaderId",function(e){
		e.stopPropagation();
		$(".menu-dropdown").toggle();
	});
	$(document).on("click",".menu-dropdown",function(e){
		e.stopPropagation();
	});
	$("#resetMenuOptions").click(function(){
		$(".districtMenuName,.parliamentMenuName,.constituencyMenuName,.mandalsMenuName,.panchayatMenuName").closest("li").hide();
		$("#constituencyMenu,#mandalMenu,#panchayatMenu").html(" ");
		$("[menu-type]").removeClass("active");
		$("#getMenuLocations").attr("menu-location-state","1").attr("menu-location-district","").attr("menu-location-constituency","").attr("menu-location-mandal","").attr("menu-location-panchayat","").attr("menu-location-levelid","2").attr("menu-location-parliament","").attr("menu-location-levelname","state");
		onLoadLocValue();
	});
	$(document).on("click",function(e){
		$(".menu-dropdown").hide();
		$("[menu-name]").removeClass("active");
	});
	$(document).on("click",'.menu-close',function(e){
		$(".menu-dropdown").hide();
	});
	$(document).on("change","[role='tabListMobile']",function(){
		var id = $('option:selected', this).attr('tab_id');
		$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
		$("#"+id).addClass("active");
	});
	$(document).on("click","[menu-name]",function(){
		locationLevelId = $(this).attr("levelid");
		locationLevelName = $(this).attr("menu-name");
		menuLevelType = $(this).attr("menu-type")
		if(locationLevelId == 5 && menuLevelType == 'Municipality' || locationLevelId == 12 && menuLevelType == 'Municipality')
		{
			locationLevelId = 7
		}else if(locationLevelId == 6 && menuLevelType == 'Ward' || locationLevelId == 13 && menuLevelType == 'Ward')
		{
			locationLevelId = 8
		}else if(locationLevelId == 6 && menuLevelType == 'Village' || locationLevelId == 13 && menuLevelType == 'Village')
		{
			locationLevelId = 6
		}
		$(this).closest("li").nextAll("li").hide();
		onLoadLocValue();
		onLoadAjaxCalls();
	});
	$(document).on("click","#getLocationDetails",function(){	
		$(".menu-dropdown").hide();
		var name = '';
		if(locationLevelId == 2)
		{
			name = $("#districtMenu").find("li.active").html();
			$("#selectedMenuName").html(name+' '+locationLevelName);
		}else if(locationLevelId == 3 || locationLevelId == 10)
		{
			name = $("#constituencyMenu").find("li.active").html();
			$("#selectedMenuName").html(name+' '+locationLevelName);
		}else if(locationLevelId == 4 || locationLevelId == 7 || locationLevelId == 11)
		{
			name = $("#mandalMenu").find("li.active").html();
			$("#selectedMenuName").html(name);
		}else if(locationLevelId == 5 || locationLevelId == 8 || locationLevelId == 12)
		{
			name = $("#panchayatMenu").find("li.active").html();
			if(locationLevelId ==5){
				$("#selectedMenuName").html(name+' '+locationLevelName);
			}else if(locationLevelId == 8){
				$("#selectedMenuName").html(name);
			}else if(locationLevelId == 12){
				$("#selectedMenuName").html(name+' '+locationLevelName);
			}
		}else if(locationLevelId == 9){
			name = $("#parliamentMenu").find("li.active").html();
			$("#selectedMenuName").html(name+' '+locationLevelName);
		}
		$(".casteGroupTypeDiv li").removeClass("active")
		$(".casteGroupTypeDiv li:nth-child(1)").addClass("active");
		
		stateId = $("#getMenuLocations").attr("menu-location-state");
		districtId = $("#getMenuLocations").attr("menu-location-district");
		parliamentId = $("#getMenuLocations").attr("menu-location-parliament");
		constituencyId = $("#getMenuLocations").attr("menu-location-constituency");
		mandalId = $("#getMenuLocations").attr("menu-location-mandal");
		panchayatId = $("#getMenuLocations").attr("menu-location-panchayat");
		locationLevelName = $("#getMenuLocations").attr("menu-location-levelName");
		locationLevelId = $("#getMenuLocations").attr("menu-location-levelId");
		locationName = $("#getMenuLocations").attr("menu-location-name");
		onLoadLocValue();
		onLoadAjaxCalls();
	});
	$(document).on("click","[refresh]",function(){	
		var blockName = $(this).attr("refresh");
		$(".casteGroupTypeDiv li").removeClass("active")
		$(".casteGroupTypeDiv li:nth-child(1)").addClass("active");
		if(blockName == 'casteInfo')
		{
			getCasteGroupNAgeWiseVoterNCadreCounts(0,"onload","All",22,4,$("[active-type='casteSorting'] li.active").attr("type"))
			getVotersCastGroupWiseCount(22,4)
		}else if(blockName == 'cadreInfor')
		{
			getLocationTypeWiseCadreCount("");
			getAgeRangeGenerAndCasteGroupByCadreCount(4);
		}else if(blockName == 'grievance')
		{
			getGrivenceOverviewDtls(""); 
			
		}else if(blockName == 'insurance')
		{
			getInsuranceOverviewDetails("");
		}else if(blockName == 'ntrTrust')
		{
			getTrustEducationOverviewDetails("");
		}else if(blockName == 'meetings')
		{
			getLocationWiseMeetingsCountDetails(1);
			getLocationWiseMeetingsCountDetails(2);
			//getLocationWiseMeetingsCountDetails(3);
			getLocationWiseSpecialMeetingsMeetingsExpanction(3,0);
		}else if(blockName == 'tours')
		{
			getLocationWiseTourMembersComplainceDtls();
		}else if(blockName == 'committees')
		{
			getLocationWiseCommitteesCount($("#enrolmentYears").val());
		}else if(blockName == 'nominatedPosts')
		{
			// Nominated Posts
			getPositionWiseMemberCount();
			getNominatedPostApplicationDetails();
			getNominatedPostStatusWiseCount();
			getLevelWisePostsOverView();
		}else if(blockName == 'alerts')
		{
			getTotalAlertDetailsForConstituencyInfo(defaultAlertCategoryIds);
			getDesignationWiseAlertsOverview(defaultAlertCategoryIds);
		}else if(blockName == 'activities')
		{
			getActivityStatusList();
		}else if(blockName == 'benefits')
		{
			getGovtSchemeWiseBenefitMembersCount();
		}else if(blockName == 'news')
		{
			getPrintMediaCountsForConstituencyPage()
			getLeadersInNewsForConstituencyPage();
		}else if(blockName == 'problems')
		{
			getDetailedGovtOverAllAnalysisProblemsForConstituencyPage("overAll")
			for(var i in propertyIdGlobalStr){
				getDetailedGovtOverAllAnalysisProblemsForConstituencyPage(propertyIdGlobalStr[i])
			}
		}else if(blockName == 'constituencyVoters')
		{
			getVotersAndcadreAgeWiseCount(22,4);
		}else if(blockName == 'election')
		{
			getElectionTypes("onload");
			//getElectionYears();
			/* var partyIds = [872,362,1117,886,72,269,265,163,1887];
			getElectionInformationLocationWise(electionTypeVal,"voteShare",partyIds,electionSubTypeArr,electionYrVal); */
			
			if(locationLevelId == '4'){
				$(".assemblyElectionBlockCls").show();
				getDetailedElectionInformaction(2,constituencyId);
			}else if(locationLevelId == '10'){
				$(".assemblyElectionBlockCls").show();
				getDetailedElectionInformaction(1,parliamentId);
			}else{
				$(".assemblyElectionBlockCls").hide();
			}
		}
	});
	
	$(document).on("click",".electionDetailsCls",function(){
		var checkedTypeVal="";
			 $(".checkedType").each(function(){
				if ($(this).is(':checked')){
					checkedTypeVal =$(this).val();
				}
			});
		var i = 0;	
		 electionTypeVal = [];
		 $(".electionTypeWiseCls").each(function(){
			if ($(this).is(':checked')){
				 electionTypeVal[i++] = $(this).val();
			}
		});
		var j = 0;	
		electionSubTypeArr = [];
		$(".checkedMainByeType").each(function(){
			if ($(this).is(':checked')){
				 electionSubTypeArr[j++] = $(this).val();
			}
		});
		var partyIds = $("#elctionBlockPartysId").val();
		electionYrVal=[];
		electionYrVal = $("#elctionYearsBlockId").val();
		getElectionInformationLocationWise(electionTypeVal,checkedTypeVal,partyIds,electionSubTypeArr,electionYrVal);
	});
	$(document).on("click",".checkedMainByeType",function(){
			var j = 0;	
			 electionSubTypeArr = [];
			 $(".checkedMainByeType").each(function(){
				if ($(this).is(':checked')){
					 electionSubTypeArr[j++] = $(this).val();
				}
			});
			getElectionYears("change");
		/* if($(this).is(':checked')){
			var j = 0;	
			 electionYearsSubTypeArr = [];
			 $(".checkedMainByeType").each(function(){
				if ($(this).is(':checked')){
					 electionYearsSubTypeArr[j++] = $(this).val();
				}
			});
			getElectionYears();
		} */
	});		
	$(document).on("click",".electionTypeWiseCls",function(){
		var value = $(this).val();
		if(value != 0){
			$(".checkUncheckCls").prop("checked",false);
			getElectionYears('change');
		}else if(value == 0){
			if ($(this).is(':checked')){
				$(".checkUncheckCls").prop("checked",true);
				$(".electionTypeWiseCls").prop("checked",true);
				getElectionYears('change');
			}else{
				$(".checkUncheckCls").prop("checked",false);
				$(".electionTypeWiseCls").prop("checked",false);
			}
		}
	});		
	$(document).on("click",".yearCadreDetails",function(){
		$(".yearCadreDetails").removeClass("cadreBorderStyle");
		$(this).addClass("cadreBorderStyle");
		var yearId = $(this).attr("attr_enrollmentId");
		getAgeRangeGenerAndCasteGroupByCadreCount(yearId)
	});
	$(document).on("click","[navTabs-click]",function(){
		var id = $(this).attr("navTabs-click");
		getMandalWiseBenefitMembersCount(id)
	});
	$(document).on("change","#publicationChangeId",function(){
		var pubId = $("#publicationChangeId").val();
		var enrollmentId = $("#enrollmentvoterId").val();
		getVotersAndcadreAgeWiseCount(pubId,enrollmentId);
	});
	$(document).on("change","#enrollmentvoterId,#publicationChangeId",function(){
		var pubId = $("#publicationChangeId").val();
		//var pubId ="0";
		var enrollmentId = $("#enrollmentvoterId").val();
		/* if(enrollmentId == 3){
			pubId = "11";
		}else if(enrollmentId == 4){
			pubId = "22";
		 }*/
		getVotersAndcadreAgeWiseCount(pubId,enrollmentId);
	});
	$(document).on("click",".assembly-view",function(){
		if($(this).text() == 'Click to more')
		{
			$(this).text("Click for less");
			$(".assembly-members-view").addClass("active");
		}else{
			$(this).text("Click to more");
			$(".assembly-members-view").removeClass("active");
		}
	});
	$(document).on("click",".assembly-viewRes",function(){
		if($(this).text() == 'Click to more')
		{
			$(this).text("Click for less");
			$(".assembly-members-viewRes").addClass("active");
		}else{
			$(this).text("Click to more");
			$(".assembly-members-viewRes").removeClass("active");
		}
	});
	$(document).on("click",".casteGroupWiseClickCls",function(){
		var casteGroupId =  $(this).attr("attr_id");
		var casteName =  $(this).attr("attr_name");
		var publicationId = $("#publicationCasteId").val();
		var enrollmentId = $("#enrollmentCasteId").val();
		getCasteGroupNAgeWiseVoterNCadreCounts(casteGroupId,"tabClick",casteName,22,enrollmentId,$("[active-type='casteSorting'] li.active").attr("type"));
	});
	$(document).on("change","#publicationCasteId",function(){
		var publicationId =  $(this).val();
		var enrollmentId = $("#enrollmentCasteId").val();
		
		getCasteGroupNAgeWiseVoterNCadreCounts(0,"onload","All",publicationId,enrollmentId,$("[active-type='casteSorting'] li.active").attr("type"));
		getVotersCastGroupWiseCount(publicationId,enrollmentId);
	});
	$(document).on("change","#enrollmentCasteId",function(){
		var publicationId ="0";
		var enrollmentId =  $(this).val();
		
		if(enrollmentId == 3){
			publicationId = "11";
		}else if(enrollmentId == 4){
			publicationId = "22";
		}
		
		getCasteGroupNAgeWiseVoterNCadreCounts(0,"onload","All",publicationId,enrollmentId,$("[active-type='casteSorting'] li.active").attr("type"));
		getVotersCastGroupWiseCount(publicationId,enrollmentId);
	});
	$(document).on("click",".casteCategoryGroupWiseClickCls",function(){
		$("#casteCategoryModal").modal("show");
		
		var casteGroupId =  $(this).attr("attr_caste_group_id");
		var casteId =  $(this).attr("attr_caste_id");
		var casteName =  $(this).attr("attr_caste_name");
		var enrollmentId =  $("#enrollmentCasteId").val();
		var publicationId = $("#publicationCasteId").val();
		getCasteNAgeWiseVoterNCadreCounts(casteGroupId,casteId,enrollmentId,22,casteName)
	});
	$(document).on("change","#enrollmentCadreId",function(){
		var enrollmentId =  $(this).val();
		getLocationTypeWiseCadreCount("");
		getAgeRangeGenerAndCasteGroupByCadreCount(enrollmentId);
	});
	$(document).on('click','.cadreRedirectPage',function(){ 
		var cadreId = $(this).attr("attr_cadre_id")
		var redirectWindow = window.open('cadreDetailsAction.action?cadreId='+cadreId+'','_blank');  
	});
	$(document).on('click','.candidateRedirectPage',function(){ 
		var candidateId = $(this).attr("attr_candidate_id")
		var redirectWindow = window.open('candidateElectionResultsAction.action?candidateId='+candidateId+'','_blank');  
	});
	//Tours Click Start
	$(document).on("click",".toursCompNonCompClickCls",function(){
		var designationId =  $(this).attr("attr_designation_id");
		var filterType =  $(this).attr("attr_tour_filter_type");
		 $("#tourDetailsModalId").modal("show");
		getTourLeaderDtlsBasedOnSelectionType (designationId,filterType);
	});
	$(document).on("click",".tourIndividualCls",function(){
		$("#tourSlider").dateRangeSlider("destroy");
		 var selectLevel = $(this).attr("attr_type");
		if(selectLevel == "subLevel"){
			setTimeout(function(){
				$('body').addClass("modal-open");
			}, 500);                     
		} 
	}); 
	$(document).on("click",".candiateCls",function(){
		var candiateId = $(this).attr("attr_candiate_id");
		 $("#subMitBtn").attr("attr_candidate_id",candiateId);
		var designationName = $(this).attr("attr_designation_name");
		var candiateName = $(this).attr("attr_candiate_name");
		var selectedLevel = $(this).attr("attr_type");
		$(".tourIndividualCls").attr("attr_type",selectedLevel);
		//var selectedDate = globalFormTourDate.split("/");
		$("#dateRangeSliderYear").val(0);
		getCandiateWiseTourDetails(candiateId,designationName,candiateName)
	});
	$(document).on('click','#showTourPdfId',function(){
		//$("#cdrModelId").modal("show");
		var dbFilePath = $(this).attr("attr_filePath");         
		var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
			$("#tourNewDocumentId").modal("hide");
			window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open('http://ieee802.org/secmail/docIZSEwEqHFr.doc','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}else{
			if(extName.trim()=="pdf" || extName.trim()=="PDF"){
				$("#tourNewDocumentId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}
			if(extName.trim()=="jpg"){  
				$("#tourNewDocumentId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}              
			if(extName.trim()=="doc" || extName.trim()=="docx"){
				$("#tourNewDocumentId").modal("show");
				str += '<iframe src="https://docs.google.com/gview?url=http://mytdp.com/Reports/tour_documents/'+dbFilePath+'&embedded=true" frameborder="0" style="width: 100%; height: 500px;">';
				str += '</iframe>';
			}
			if(extName.trim()=="xls" || extName.trim()=="xlsx"){      
				window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			}            
			$("#tourNewDocumentBodyId").html(str);
			$("#tourNewDocumentId").attr("isModalOpened","true");
			//window.open(wurl+'/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			// window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}      
	});
	$(document).on("click",".closeShowPdfCls",function(){
		setTimeout(function(){
		$('body').addClass("modal-open");
		}, 500);                     
	});	
	$(document).on("change","#dateRangeSliderYear",function(){
		var getYear = $(this).val();
		$("#tourSlider").dateRangeSlider("destroy");
		var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		$("#tourSlider").dateRangeSlider({
			  bounds: {min: new Date(getYear, 0, 1), max: new Date(getYear, 11, 31)},
			//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
			defaultValues: {min: new Date(getYear, 0,1), max: new Date(getYear,2,30)},
			scales: [{
			  first: function(value){ return value; },
			  end: function(value) {return value; },
			  next: function(value){
				var next = new Date(value);
				return new Date(next.setMonth(value.getMonth() + 1));
			  },
			  label: function(value){
				return months[value.getMonth()];
			  },
			  format: function(tickContainer, tickStart, tickEnd){
				tickContainer.addClass("myCustomClass");
			  }
			 }] 
		});
	});	
	$(document).on("click","#subMitBtn",function(){
		var candiateId = $(this).attr("attr_candidate_id");
		var fromDateDate = $(".ui-rangeSlider-leftLabel").find(".ui-rangeSlider-label-value").html(); 
		var toDateDate = $(".ui-rangeSlider-rightLabel").find(".ui-rangeSlider-label-value").html(); 
		var frmDateInRequiredFormat;
		var toDateInRequiredFormat;
		if(fromDateDate != null && fromDateDate.length > 0){
			var fromDateArr = fromDateDate.split("-");
			frmDateInRequiredFormat =fromDateArr[2].trim()+"/"+fromDateArr[1].trim()+"/"+fromDateArr[0].trim();
		}
		if(toDateDate != null && toDateDate.length > 0){
			var toDateArr = toDateDate.split("-");
			toDateInRequiredFormat =toDateArr[2].trim()+"/"+toDateArr[1].trim()+"/"+toDateArr[0].trim();
		}
		getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat);
	});
	//Tours Click End
	$(document).on("click",".popUpDetailsClickCls",function(){
		var deptId =  $(this).attr("attr_department_id");
		var boardLevelId =  $(this).attr("attr_boardLevelId");
		var type =  $(this).attr("attr_type");
		var departmentName =  $(this).attr("attr_department_name");
		var statusIds =  $(this).attr("attr_board_statusIds");
		if(type == "open"){
			$("#openModalDiv").modal("show");
			$("#openModalDiv .modal-dialog").css("width","95%");
			$("#TitleId").html(departmentName+  "  Open Posts Details");
			$("#subTitleId").html("");
			$(".paginationCls").html("");
			getDepartmentWisePostAndApplicationDetails(deptId,boardLevelId,type);
		}else if(type == "goIssued"){
			$("#openModalDiv").modal("show");
			$("#openModalDiv .modal-dialog").css("width","95%");
			$("#TitleId").html(departmentName + "  G.O Issued Positions");
			$("#subTitleId").html("");
			getLevelWiseGoIssuedPostions(boardLevelId,statusIds,0,10);
		}else if(type == "department"){
			$("#departmentPostModal").modal("show");
			$("#openModalDiv .modal-dialog").css("width","95%");
			$("#departmentDetailsModalDivId").html(spinner);
			$("#deptHeadingId").html(departmentName+" Details");
			$("#subTitleId").html("");
			$(".paginationCls").html("");
			getDepartmentWisePostAndApplicationDetails(deptId,boardLevelId,type);
		}else if(type == "positionLevel"){
			$("#openModalDiv").modal("show");
			$("#openModalDiv .modal-dialog").css("width","95%");
			$("#TitleId").html(departmentName+" Level Details");
			$("#subTitleId").html("");
			$(".paginationCls").html("");
			getNominatedPositionWiseCandidates(boardLevelId,departmentName);
		}else if (type == "alert"){
			var alertTypeIdsStr = $(this).attr("attr_alertTypeIdsStr");
			var statusIds = $(this).attr("attr_statusIds");
			var designationId = $(this).attr("attr_designationId");
			var alertCategeryId = $(this).attr("attr_alertCategeryId");
			var alert_type = $(this).attr("attr_alert_type");
			var otherCategory = $(this).attr("attr_otherCategory");
			var impactIdsArr = $(this).attr("attr_impactIdsArr");
			var status_name = $(this).attr("attr_status_name");
			var total_count = $(this).attr("attr_total_count");
			$("#openModalDiv").modal("show");
			$("#openModalDiv .modal-dialog").css("width","95%");
			$(".paginationCls").html("");
			if(designationId !=0){
				$("#TitleId").html(status_name + " Designation Wise Alerts Details  -  Total "+total_count+" ");
				$("#subTitleId").html("");
			}else{
				$("#TitleId").html(status_name + " Alerts Details  -  Total "+total_count+" ");
				$("#subTitleId").html("");
			}
			getAlertOverviewClick(alertTypeIdsStr,statusIds,designationId,alertCategeryId,alert_type,otherCategory,impactIdsArr,status_name,total_count);
		}else if(type == "MPMLA"){
			var electionScopeId = $(this).attr("attr_election_scopeId");
			var partyId = $(this).attr("attr_partyId");
			var electionId = $(this).attr("attr_electionId");
			if(electionId == null || electionScopeId == 1){
				electionId = 260;
			}else if(electionId == null || electionScopeId == 2){
				electionId = 258;
			}
			var designation;
			if(electionScopeId == 1){
				designation="MP";
			}else if(electionScopeId == 2){
				designation="MLA";
			}
			$("#openModalDiv").modal("show");
			$(".paginationCls").html("");
			$("#openModalDiv .modal-dialog").css("width","70%");
			$("#TitleId").html("Party Wise"+" "+designation+" "+"Candidates Details");
			$("#subTitleId").html("");
			getPartyWiseMPandMLACandidatesCountDetials(electionScopeId,partyId,electionId)
		}else if(type == "meeting_type"){
			$("#openModalDiv").modal("show");
			$("#TitleId").html("Committee Meetings - District Level");
			$("#subTitleId").html("(Every month : 22nd/23rd/24th)");
			$(".paginationCls").html("");
			getLocationWiseMeetingStatusDetailsAction();
		}else if(type == "electionResults"){
			var constituencyId = $(this).attr("attr_constituencyId");
			var electionYear = $(this).attr("attr_election_year");
			var electionType = $(this).attr("attr_election_type");
			var electionTypeId = $(this).attr("attr_election_typeId");
			var name = $(this).attr("attr_name");
			$("#openModalDiv").modal("show");
			$("#openModalDiv .modal-dialog").css("width","95%");
			$(".paginationCls").html("");
			if(electionType == "Assembly"){
				$("#TitleId").html(name+"  Election Result");
			}else{
				$("#TitleId").html(name+"  Election Result");
			}
			$("#subTitleId").html("");
			getDetailedElectionResults(constituencyId,electionYear,"table",electionTypeId)
			
		}else if(type == "meeting_type_special"){
			var partyMeetingMainTypeId = $(this).attr("attr_partyMeetingMainTypeId");
			var partyMeetingTypeId = $(this).attr("attr_partyMeetingTypeId");
			var name = $(this).attr("attr_name");
			$("#openModalDiv").modal("show");
			$("#openModalDiv .modal-dialog").css("width","95%");
			$(".paginationCls").html("");
			$("#TitleId").html("Special Meeting -"+name+" - Details");
			$("#subTitleId").html("");
			getLocationWiseSpecialMeetingsMeetingsExpanction(partyMeetingMainTypeId,partyMeetingTypeId);
		}else if(type == "meeting_type_level"){
			var meeting_levelId = $(this).attr("attr_meeting_levelId");
			var partyMeetingTypeId = $(this).attr("attr_partyMeetingTypeId");
			$("#openModalDiv").modal("show");
			$("#openModalDiv .modal-dialog").css("width","95%");
			$(".paginationCls").html("");
			$("#TitleId").html("Committee Meetings");
			$("#subTitleId").html("");
			buildlevelWiseBlockDetails(meeting_levelId,partyMeetingTypeId,"meeting")
		}	
	});
	$(document).on("click",".descAlertCls",function(){
		$("#cdrModelDivId").find(".close").addClass("modalClose");
		$("#cdrModelDivId").find(".modal-footer .btn").addClass("modalClose");
		$("#tourDocHeadingId,#cdrModelId,#alertDestId,#sourceHeadingId,#headingNameId,#alertAttachTitId,#alertAttachImgId,#alertInvolvedCandidates,#alertAssignedCandidates,#alertCommentsDiv,#alertVerificationDiv,#alertVerificationDtlsDiv,#alertDocHeadingId,#alertDocId").html("");
		$("#tourDocHeadingId").html("ALERT TITLE <br>");
		$("#cdrModelDivId").modal("show");
		var alertId = $(this).attr("attr_alert_id");
		var alertStatus = $(this).attr("attr_alert_status");
		getAlertData(alertId);
		getAlertAssignedCandidates(alertId);    
		getAlertStatusCommentsTrackingDetails(alertId,alertStatus);
		getVerificationDtls(alertId);
	});
	$(window,document).on('resize', function(){
		responsiveTabs();
	});
}
function menuCalls(levelId,levelValue,higherLevelVal)
{
	if(levelId == '2')
	{
		$("#districtMenu").html(spinner);
		$("#constituencyMenu,#mandalMenu,#panchayatMenu").html(' ');
		$("[menu-name='constituency'],[menu-name='mandal'],[menu-name='panchayat']").closest("li").hide();
		var jsObj={
			"stateId":levelValue
		}
		$.ajax({
			type : "GET",
			url : "getAllDistrictsForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResult('district',result);
		});
	}else if(levelId == '9')
	{
		$("#parliamentMenu").html(spinner);
		$("#constituencyMenu,#mandalMenu,#panchayatMenu").html(' ');
		$("[menu-name='constituency'],[menu-name='mandal'],[menu-name='panchayat']").closest("li").hide();
		var jsObj={
			"stateId":levelValue
		}
		$.ajax({
			type : "GET",
			url : "getParlimentConstituenciesForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResult('parliament',result);
		});
	}else if(levelId == '3')
	{
		$("#constituencyMenu").html(spinner);
		$("#mandalMenu,#panchayatMenu").html(' ');
		$("[menu-name='mandal'],[menu-name='panchayat']").closest("li").hide();
		var jsObj={
			"districtId":levelValue
		}
		$.ajax({
			type : "GET",
			url : "getConstituenciesByDistrictForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResult('constituency',result);
		});
	}else if(levelId == '10')
	{
		$("#constituencyMenu").html(spinner);
		$("#mandalMenu,#panchayatMenu").html(' ');
		$("[menu-name='mandal'],[menu-name='panchayat']").closest("li").hide();
		var jsObj={
			"parlimentId":levelValue
		}
		$.ajax({
			type : "GET",
			url : "getAllConstituencyByParlimentIdForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResult('constituency',result);
		});
	}else if(levelId == '4' || levelId == '11')
	{
		$("#mandalMenu").html(spinner);
		$("#panchayatMenu").html(' ');
		$("[menu-name='panchayat']").closest("li").hide();
		var jsObj={
			"constituencyId":levelValue
		}
		$.ajax({
			type : "GET",
			url:"getMandalsForConstituenciesForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResult('mandal',result);
		});
	}else if(levelId == '5'  || levelId == '12')
	{
		$("#panchayatMenu").html(spinner);
		$("[menu-name='panchayat']").closest("li").hide();
		var jsObj={
			"mandalId":levelValue,
			"constituencyId":higherLevelVal
		}
		$.ajax({
			type : "GET",
			url:"getPanchayatWardByMandalForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResult('panchayat',result);
		});
	}
	function buildResult(divId,result)
	{
		levelId = parseInt(levelId) + 1;
		var shortName = '';
		var menu='';
		menu+='<h4 class="panel-title text-capitalize"><b>'+divId+'</b></h4>';
		menu+='<div class="scroller'+divId+'">';
			menu+='<ul>';
				for(var i in result)
				{
					if(levelId == 3)
					{
						shortName = 'D'
					}else if(levelId == 4 || levelId == 11)
					{
						shortName = 'AC'
					}else if(levelId == 10)
					{
						shortName = 'PC'
					}else if(levelId == 5 && result[i].name == 'Municipality' || levelId == 12 && result[i].name == 'Municipality')
					{
						shortName = 'MUN'
					}else if(levelId == 5 && result[i].name == 'Mandal' || levelId == 12 && result[i].name == 'Mandal')
					{
						shortName = 'M'
					}else if(levelId == 6 && result[i].name == 'Ward' || levelId == 13 && result[i].name == 'Ward')
					{
						shortName = 'W'
					}else if(levelId == 6 && result[i].name == 'Village' || levelId == 13 && result[i].name == 'Village')
					{
						shortName = 'V'
					}
					if(levelId != '6' || levelId != 6)
					{
						menu+='<li menu-type="'+result[i].name+'" short-name="'+shortName+'" menu-click="'+result[i].locationId+'" menu-levelname="'+divId+'" levelId="'+levelId+'" class="text-capitalize">'+result[i].locationName+'</li>';
					}else{
						menu+='<li menu-type="'+result[i].name+'" short-name="'+shortName+'" constituencyId="'+$("#constituencyMenu li.active").attr("menu-click")+'" class="text-capitalize" menu-levelname="'+divId+'" menu-click="'+result[i].locationId+'" levelId="'+levelId+'">'+result[i].locationName+'</li>';
					}
				}
			menu+='</ul>';
		menu+='</div>';
		$("#"+divId+"Menu").html(menu);
		if(levelId == '3' || levelId == '10')
		{
			$(".scroller"+divId).mCustomScrollbar({setHeight:'140px'})
		}else if(result.length > 9){
			$(".scroller"+divId).mCustomScrollbar({setHeight:'300px'})
		}		
	}	
}
function subStr(name,noOfChar){
	ellipsetext=".."
	var showChar = noOfChar;
	var content = name;
	if(content != null){
		if(content.length > showChar) {
			var c = content.substr(0, showChar);
			var html = c + ellipsetext;
			return html;
		}
	}
	return name;
}
function minimise(Id,count)
{
	var id = Id;
	var minimized_elements = $(id);
	minimized_elements.each(function(){    
		var t = $(this).text();        
		if(t.length < count) return;
		$(this).html(
			'<span class="less">'+t.slice(0,count)+'..</span>'+
			'<span style="display:none;" class="more text-capitalize">'+t+'</span>'
		);

	}); 
	$(document).on("mouseover",id,function(){
		$(this).find('span').hide();
		$(this).find('span.more').show();
	});
	$(document).on("mouseout",id,function(){
		$(this).find('span.less').show();
		$(this).find('span.more').hide();
	});
}
function rightNav()
{
	var indexValue = '';
	var navBar = $(".right-nav-list li");
	navBar.click(function(){
		navBar.removeClass("active");
		$(this).addClass("active");
		indexValue = $(this).attr("index");
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $('[navbar-index='+indexValue+']').offset().top},
			'slow');
		},300);
	});
	//navbar-index="16"
}
function collapseClick()
{
	'use strict';
	var expandId = '';
	$(document).on("click","[collapseid]",function(){
		var $this = $(this);
		expandId = $this.attr("collapseid");
		if($this.hasClass('glyphicon-minus'))
		{
			$this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
			$('[collapseBodyId='+expandId+']').hide();
		}else{
			$(".td-expand-body").hide();
			$(".td-expand-icon").removeClass('glyphicon-minus').addClass('glyphicon-plus');
			$this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
			$('[collapseBodyId='+expandId+']').show();
		}
	});
	$(document).on("click",".td-expand-hide",function(){
		$('[collapseBodyId='+expandId+']').hide();
		$('[collapseid='+expandId+']').removeClass('glyphicon-minus').addClass('glyphicon-plus');
	});
}
function responsiveTabs()
{
	var $this = $(this);
	var $windowWidth = $(window).width();
	if($windowWidth < 768)
	{
		$('[role="tabListMobile"]').show();
		$('[role="tablist"]').hide();
	}else{
		$('[role="tabListMobile"]').hide();
		$('[role="tablist"]').show();
	}
}
function getCandidateAndPartyInfoForConstituency(){
	$("#parliamentMemberId,#assemblyMemberId,#representativeMembersId,#statelevelMembersId").html(blockSpinner);
	if(locationLevelId == '8'){
		$("#parliamentMemberId,#assemblyMemberId,#representativeMembersId,#statelevelMembersId").hide();
		return;
	}else{
		$("#parliamentMemberId,#assemblyMemberId,#representativeMembersId,#statelevelMembersId").show();
	}
	var roleIds=[];
	var committeeIds=[];
	var basicCommoteeId='0';
	var enrollmentId='0';
	var enrollmentYears=[];
	var representativeTypeIds = [];
	if(locationLevelId == 2){
		representativeTypeIds =[36];
		//roleIds.push(1,10);
		roleIds.push(1);
		committeeIds.push(10,12);
		basicCommoteeId="1";
		enrollmentId="2";
		enrollmentYears.push(2014);
		representativeTypeIds =[36];
	}	
	else if(locationLevelId == 3){
		representativeTypeIds =[2,1,20,31];
	}else if(locationLevelId == 4){
		representativeTypeIds =[2,1,20,31,21];
	} else if(locationLevelId == 10){
		representativeTypeIds =[20,31];
	}else if(locationLevelId == 5){
		representativeTypeIds =[2,1,20,31,3,4];
	}else if(locationLevelId == 6){
		representativeTypeIds =[2,1,20,31,3,4];
	}  
	var jsObj={
    	locationTypeId	:locationLevelId,
		locationValue	:locationLevelVal,
		representativeTypeIds:representativeTypeIds ,
		roleIds :roleIds,
		committeeIds:committeeIds,
		basicCommoteeId:basicCommoteeId,
		enrollmentId:enrollmentId,
		enrollmentYears:enrollmentYears
    }
    $.ajax({
      type : "GET",
      url : "getCandidateAndPartyInfoForConstituencyAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			/*if(result[0].list !=null && result[0].list.length>0){
				buildRepresentativeCandidates(result);
			}else{
				$("#representativeMembersId").html("");
			}*/
			$("#representativeMembersId").html("");
			if(locationLevelId == '3' || locationLevelId == '10')
			{
				return buildCandidateAndPartyInfoForDistrict(result);
			}else if(locationLevelId == '2'){
				return buildCandidateAndPartyForStateLevel(result);
			}else{
				return buildCandidateAndPartyInfoForConstituency(result);
			}
		}else{
			$("#parliamentMemberId,#assemblyMemberId,#statelevelMembersId").html(noData);
		}
	});
	function buildCandidateAndPartyInfoForDistrict(result){
		var parliament = '';
		var assembly = '';
		parliament+='<div class="col-sm-12">';
			parliament+='<div class="panel panel-default">';
				parliament+='<div class="panel-body">';
					parliament+='<h4 class="panel-title theme-title-color">Member Of Parliament</h4>';
					parliament+='<div class="row">';
						for(var i in result[0].subList1) 
						{
							parliament+='<div class="col-sm-6 m_top10">';
								parliament+='<div class="media media-profile">';
									parliament+='<span id="mlaSpinnerId"></span>';
									parliament+='<div class="media-left">';
										if(result[0].subList1[i].education !=null && result[0].subList1[i].education.trim().length>0){
											parliament+='<img src="https://mytdp.com/images/cadre_images/'+result[0].subList1[i].education+'" class="media-object profile-image img-border" alt="profile" onerror="setDefaultImage(this);"/>';
										}else{
											parliament+='<img src="https://mytdp.com/images/candidates/'+result[0].subList1[i].candidateId+'.jpg" class="media-object profile-image img-border" alt="profile" onerror="setDefaultImage(this);"/>';
										}
										parliament+='<span class="border-image img-border">';
											if(result[0].subList1[i].party == "TDP" || result[0].subList1[i].party == "YSRC"){
												parliament+='<img class="" onerror="setDefaultImage(this);" src="images/party_flags/'+result[0].subList1[i].party+'_01.PNG" alt="'+result[0].subList1[i].party+'" style="height:25px;width:25px;"></img>';
											}else{
												parliament+='<img class="" onerror="setDefaultImage(this);" src="images/party_flags/'+result[0].subList1[i].party+'.png" alt="'+result[0].subList1[i].party+'" style="height:25px;width:25px;"></img>';
											}
										parliament+='</span>';
									parliament+='</div>';
									parliament+='<div class="media-body">';
										parliament+='<h4 class="text-success text-capital">'+result[0].subList1[i].candidateName+'';
										if(result[0].subList1[i].migrateCandidate == "true"){
											parliament+='<span class="m_left5" style="font-size: 12px; top: 15px; position: absolute;">';
												  parliament+='<i class="fa fa-star" aria-hidden="true" ></i>';
											parliament+='</span>';
										}
										parliament+='</h4>';
										parliament+='<p class="text-muted text-capital m_left5 f_12" style="margin-bottom: 5px;">'+result[0].subList1[i].constituencyName+' (PC)</p>';
										if(result[0].subList1[i].tdpCadreId !=null && result[0].subList1[i].tdpCadreId>0){
											parliament+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[0].subList1[i].tdpCadreId+'">View Candidate Profile</span> <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_constituencyId="'+result[0].subList1[i].constituencyId+'" attr_election_year="'+result[0].subList1[i].latestElecYear+'" attr_election_type="Parliament" attr_election_typeId="1" attr_name="'+result[0].subList1[i].constituencyName+'">View Results</span>';
										}else{
											parliament+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[0].subList1[i].candidateId+'">View Candidate Profile</span> <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_election_typeId="1" attr_constituencyId="'+result[0].subList1[i].constituencyId+'" attr_election_year="'+result[0].subList1[i].latestElecYear+'" attr_election_type="Parliament" attr_name="'+result[0].subList1[i].constituencyName+'">View Results</span>';
										}
									parliament+='</div>';
								parliament+='</div>';
							parliament+='</div>';
						}
					parliament+='</div>';
				parliament+='</div>';
			parliament+='</div>';
		parliament+='</div>';
		$("#parliamentMemberId").html(parliament);
		assembly+='<div class="col-sm-12">';
			assembly+='<div class="panel panel-default">';
				assembly+='<div class="panel-body">';
					assembly+='<h4 class="panel-title theme-title-color">Member Of Legislative Assembly (MLA)</h4>';
					if(result.length > 4)
					{
						assembly+='<div class="block  assembly-members-view">';
					}else{
						assembly+='<div class="block">';
					}
						assembly+='<div class="row">';
							for(var i in result)
							{
								assembly+='<div class="col-sm-6 m_top10">';
									assembly+='<div class="media media-profile">';
										assembly+='<span id="mlaSpinnerId"></span>';
										assembly+='<div class="media-left">';
											if(result[i].assemblyCandidateInfo[0].education !=null && result[i].assemblyCandidateInfo[0].education.trim().length>0){
												assembly+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/cadre_images/'+result[i].assemblyCandidateInfo[0].education+'" class="media-object profile-image img-border" alt="profile"/>';
											}else{
												assembly+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/candidates/'+result[i].assemblyCandidateInfo[0].candidateId+'.jpg" class="media-object profile-image img-border" alt="profile"/>';
											}
											
											
											assembly+='<span class="border-image img-border">';
												if(result[i].assemblyCandidateInfo[0].party == "TDP" || result[i].assemblyCandidateInfo[0].party == "YSRC"){
													assembly+='<img class="" onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].assemblyCandidateInfo[0].party+'_01.PNG" alt="'+result[i].assemblyCandidateInfo[0].party+'" style="height:25px;width:25px;"></img>';
												}else{
													assembly+='<img class="" onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].assemblyCandidateInfo[0].party+'.png" alt="'+result[i].assemblyCandidateInfo[0].party+'" style="height:25px;width:25px;"></img>';
												}
											assembly+='</span>';
										assembly+='</div>';
										assembly+='<div class="media-body">';
											assembly+='<h4 class="text-success text-capital">'+result[i].assemblyCandidateInfo[0].candidateName+'';
												if(result[i].migrateCandidate == "true"){
													assembly+='<span class="m_left5" style="font-size: 12px; top: 15px; position: absolute;">';
														  assembly+='<i class="fa fa-star" aria-hidden="true" ></i>';
													assembly+='</span>';
												}
											assembly+='</h4>';
											 assembly+='<p class="text-muted text-capital m_left5 f_12" style="margin-bottom:5px"><span>AC : '+result[i].assemblyCandidateInfo[0].constituencyName+'</span> <span>PC : '+result[i].assemblyCandidateInfo[0].parliamnerName+'</span></p>';
											if(result[i].assemblyCandidateInfo[0].tdpCadreId !=null && result[i].assemblyCandidateInfo[0].tdpCadreId>0){
												assembly+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[i].assemblyCandidateInfo[0].tdpCadreId+'">View Candidate Profile</span> <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_election_typeId="2" attr_constituencyId="'+result[i].assemblyCandidateInfo[0].constituencyId+'" attr_election_year="'+result[i].assemblyCandidateInfo[0].latestElecYear+'" attr_election_type="Assembly" attr_name="'+result[i].assemblyCandidateInfo[0].constituencyName+'">View Results</span>';
											}else{
												assembly+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[i].assemblyCandidateInfo[0].candidateId+'">View Candidate Profile</span> <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_election_typeId="2" attr_constituencyId="'+result[i].assemblyCandidateInfo[0].constituencyId+'" attr_election_year="'+result[i].assemblyCandidateInfo[0].latestElecYear+'" attr_election_type="Assembly" attr_name="'+result[i].assemblyCandidateInfo[0].constituencyName+'">View Results</span>';
											}
										assembly+='</div>';
									assembly+='</div>';
								assembly+='</div>';
							}
						assembly+='</div>';
					assembly+='</div>';
					assembly+='<p class="text-center assembly-view" style="text-decoration:underline">Click to more</p>';
				assembly+='</div>';
			assembly+='</div>';
		assembly+='</div>';
		$("#assemblyMemberId").html(assembly);
		$("#statelevelMembersId").html("");
	}
	function buildCandidateAndPartyInfoForConstituency(result)
	{
		var parliament = '';
		parliament+='<div class="col-sm-12">';
			parliament+='<div class="panel panel-default">';
				parliament+='<div class="panel-body">';
					parliament+='<h4 class="panel-title theme-title-color">Members</h4>';
					parliament+='<div class="row">';
						parliament+='<div class="col-sm-6 m_top10">';
							parliament+='<div class="media media-profile">';
								parliament+='<span id="mlaSpinnerId"></span>';
								parliament+='<div class="media-left">';
									if(result[0].subList1[0].education != null && result[0].subList1[0].education.trim().length > 0){
										parliament+='<img src="https://mytdp.com/images/cadre_images/'+result[0].subList1[0].education+'" class="media-object profile-image img-border" alt="profile"  onerror="setDefaultImage(this);"/>';
									}else{
										  parliament+='<img src="https://mytdp.com/images/candidates/'+result[0].subList1[0].candidateId+'.jpg" class="media-object profile-image img-border" alt="profile"  onerror="setDefaultImage(this);"/>';
									 }
										parliament+='<span class="border-image img-border">';
										if(result[0].subList1[0].party == "TDP" || result[0].subList1[0].party == "YSRC"){
											parliament+='<img class="" onerror="setDefaultImage(this);" src="images/party_flags/'+result[0].subList1[0].party+'_01.PNG" alt="'+result[0].subList1[0].party+'" style="height:25px;width:25px;"></img>';
										}else{
											parliament+='<img class="" onerror="setDefaultImage(this);" src="images/party_flags/'+result[0].subList1[0].party+'.png" alt="'+result[0].subList1[0].party+'" style="height:25px;width:25px;"></img>';
										}
									parliament+='</span>';
								parliament+='</div>';
								parliament+='<div class="media-body">';
									parliament+='<h4 class="text-success text-capital">'+result[0].subList1[0].candidateName+'';
										if(result[0].subList1[0].migrateCandidate == "true"){
											parliament+='<span class="m_left5" style="font-size: 12px; top: 15px; position: absolute;">';
												  parliament+='<i class="fa fa-star" aria-hidden="true" ></i>';
											parliament+='</span>';
										}
									parliament+='</h4>';
									parliament+='<p class="text-muted text-capital m_left5 f_12" style="margin-bottom:5px;">MP ('+result[0].subList1[0].constituencyName+')</p>';
									if(result[0].subList1[0].tdpCadreId !=null && result[0].subList1[0].tdpCadreId>0){
										parliament+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[0].subList1[0].tdpCadreId+'">View Candidate Profile</span> <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_election_typeId="1" attr_constituencyId="'+result[0].subList1[0].constituencyId+'" attr_election_year="'+result[0].subList1[0].latestElecYear+'" attr_election_type="Parliament" attr_name="'+result[0].subList1[0].constituencyName+'">View Results</span>';
									}else{
										parliament+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[0].subList1[0].candidateId+'">View Candidate Profile</span> <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_election_typeId="1" attr_constituencyId="'+result[0].subList1[0].constituencyId+'" attr_election_year="'+result[0].subList1[0].latestElecYear+'" attr_election_type="Parliament" attr_name="'+result[0].subList1[0].constituencyName+'">View Results</span>';
									}
								parliament+='</div>';
							parliament+='</div>';
						parliament+='</div>';			
						for(var i in result)
						{
							parliament+='<div class="col-sm-6 m_top10">';
								parliament+='<div class="media media-profile">';
									parliament+='<span id="mlaSpinnerId"></span>';
									parliament+='<div class="media-left">';
										if(result[i].assemblyCandidateInfo[0].education !=null && result[i].assemblyCandidateInfo[0].education.trim().length>0){
											parliament+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/cadre_images/'+result[i].assemblyCandidateInfo[0].education+'" class="media-object profile-image img-border" alt="profile"/>';
										}else{
											parliament+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/candidates/'+result[i].assemblyCandidateInfo[0].candidateId+'.jpg" class="media-object profile-image img-border" alt="profile"/>';
										}
										parliament+='<span class="border-image img-border">';
											if(result[i].assemblyCandidateInfo[0].party == "TDP" || result[i].assemblyCandidateInfo[0].party == "YSRC"){
												parliament+='<img class="" onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].assemblyCandidateInfo[0].party+'_01.PNG" alt="'+result[i].assemblyCandidateInfo[0].party+'" style="height:25px;width:25px;"></img>';
											}else{
												parliament+='<img class="" onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].assemblyCandidateInfo[0].party+'.png" alt="'+result[i].assemblyCandidateInfo[0].party+'" style="height:25px;width:25px;"></img>';
											}
										parliament+='</span>';
									parliament+='</div>';
									parliament+='<div class="media-body">';
										parliament+='<h4 class="text-success text-capital m_left5">'+result[i].assemblyCandidateInfo[0].candidateName+'';
											if(result[i].migrateCandidate == "true"){
												parliament+='<span class="m_left5" style="font-size: 12px; top: 15px; position: absolute;">';
													  parliament+='<i class="fa fa-star" aria-hidden="true" ></i>';
												parliament+='</span>';
											}
										parliament+='</h4>';
										 parliament+='<p class="text-muted text-capital m_left5 f_12" style="margin-bottom:5px"><span>AC : '+result[i].assemblyCandidateInfo[0].constituencyName+'</span> <span>PC : '+result[i].assemblyCandidateInfo[0].parliamnerName+'</span></p>';
										if(result[i].assemblyCandidateInfo[0].tdpCadreId !=null && result[i].assemblyCandidateInfo[0].tdpCadreId>0){
											parliament+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[i].assemblyCandidateInfo[0].tdpCadreId+'">View Candidate Profile</span> <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_election_typeId="2" attr_constituencyId="'+result[i].assemblyCandidateInfo[0].constituencyId+'" attr_election_year="'+result[i].assemblyCandidateInfo[0].latestElecYear+'" attr_election_type="Assembly" attr_name="'+result[i].assemblyCandidateInfo[0].constituencyName+'">View Results</span>';
										}else{
											parliament+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[i].assemblyCandidateInfo[0].candidateId+'">View Candidate Profile</span> <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_election_typeId="2" attr_constituencyId="'+result[i].assemblyCandidateInfo[0].constituencyId+'" attr_election_year="'+result[i].assemblyCandidateInfo[0].latestElecYear+'" attr_election_type="Assembly" attr_name="'+result[i].assemblyCandidateInfo[0].constituencyName+'">View Results</span>';
										}
									parliament+='</div>';
								parliament+='</div>';
							parliament+='</div>';
						}	
					parliament+='</div>';
				parliament+='</div>';
			parliament+='</div>';
		parliament+='</div>';
		$("#parliamentMemberId").html(parliament);
		$("#assemblyMemberId").html("");
		$("#statelevelMembersId").html("");
	}
	function buildCandidateAndPartyForStateLevel(result){
		var stateLevel='';
			stateLevel+='<div class="col-sm-12">';
				stateLevel+='<div class="panel panel-default">';
					stateLevel+='<div class="panel-body">';
							stateLevel+='<div class="row">';
							var length=0;
								for(var i in result)
								{
									length = result.length;
									
										stateLevel+='<div class="col-sm-6 m_top10">';
										if(length == 1){
											stateLevel+='<div class="media media-profile" style="border:none;">';
										}else{
											stateLevel+='<div class="media media-profile">';
										}
											
												stateLevel+='<span id="mlaSpinnerId"></span>';
												stateLevel+='<div class="media-left">';
												if(result[i].cadreImage !=null && result[i].cadreImage.trim().length>0){
													stateLevel+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/cadre_images/'+result[i].cadreImage+'" class="media-object profile-image img-border" alt="profile"/>';
												}else{
													stateLevel+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/candidates/'+result[i].condidateId+'.jpg" class="media-object profile-image img-border" alt="profile"/>';
												}
													
													stateLevel+='<span class="border-image img-border">';
													
													
													if(result[i].party !=null){
														if(result[i].party == "TDP" || result[i].party == "YSRC"){
															stateLevel+='<img class="" src="images/party_flags/'+result[i].party+'_01.PNG" alt="'+result[i].party+'" style="height:25px;width:25px;"></img>';
														}else{
															stateLevel+='<img class="" src="images/party_flags/'+result[i].party+'.png" alt="'+result[i].party+'" style="height:25px;width:25px;"></img>';
														}
													}else{
														stateLevel+='<img  src="images/User.png" alt="party" style="width:20px;"/>';
													}
														
													stateLevel+='</span>';
												stateLevel+='</div>';
												stateLevel+='<div class="media-body">';
													stateLevel+='<h4 class="text-success text-capital m_left5">'+result[i].candidateName+'';
													stateLevel+='</h4>';
													stateLevel+='<p class="text-muted text-capital m_left7 f_12" style="margin-bottom:5px">'+result[i].committeLevel+'</p>';
													
													if(result[i].cadreId !=null && result[i].cadreId>0){
														if(result[i].candidateName == "NARA CHANDRABABU NAIDU"){
															stateLevel+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[i].cadreId+'">View Candidate Profile</span>  <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_constituencyId="'+result[i].constituencyId+'" attr_election_year="2014" attr_election_type="Assembly" attr_election_typeId="2" attr_name="">View Results</span>';
														}else{
															stateLevel+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[i].cadreId+'">View Candidate Profile</span>';
														}
														
													}else{
														if(result[i].candidateName == "NARA CHANDRABABU NAIDU"){
															stateLevel+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[i].condidateId+'">View Candidate Profile</span>  <span class="text-success text-capital viewPageCls popUpDetailsClickCls"  attr_type="electionResults" attr_election_typeId="2" attr_constituencyId="'+result[i].constituencyId+'" attr_election_year="2014" attr_election_type="Assembly" attr_name="">View Results</span>';
														}else{
															stateLevel+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[i].condidateId+'">View Candidate Profile</span>';
														}
														
													}
												stateLevel+='</div>';
											stateLevel+='</div>';
										stateLevel+='</div>';
								}
						stateLevel+='</div>';
					stateLevel+='</div>';
				stateLevel+='</div>';
			stateLevel+='</div>';
		$("#statelevelMembersId").html(stateLevel);
		$("#parliamentMemberId").html("");
		$("#assemblyMemberId").html("");
		$("#representativeMembersId").html("");
	}
}	
	function buildRepresentativeCandidates(result){
		var representative='';
		representative+='<div class="col-sm-12">';
			representative+='<div class="panel panel-default">';
				representative+='<div class="panel-body">';
					representative+='<h4 class="panel-title theme-title-color">Important Leaders </h4>';
					var length=0;
					for(var i in result[0].list)
					{
						length =result[0].list[i].length;
					}
					if(length > 4)
					{
						representative+='<div class="block  assembly-members-viewRes">';
					}else{
						representative+='<div class="block">';
					}
					
						representative+='<div class="row m_top10">';
							for(var i in result[0].list)
							{
								representative+='<div class="col-sm-12 m_top10">';
									representative+='<h4 class="panel-title">'+result[0].list[i].party+'</h4>';
								representative+='</div>';
								for(var j in result[0].list[i].list){
									representative+='<div class="col-sm-6 m_top10">';
										representative+='<div class="media media-profile">';
											representative+='<span id="mlaSpinnerId"></span>';
											representative+='<div class="media-left">';
											if(result[0].list[i].list[j].education !=null && result[0].list[i].list[j].education.trim().length>0){
												representative+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/cadre_images/'+result[0].list[i].list[j].education+'" class="media-object profile-image img-border" alt="profile"/>';
											}else{
												representative+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/candidates/'+result[0].list[i].list[j].candidateId+'.jpg" class="media-object profile-image img-border" alt="profile"/>';
											}
												
												
												representative+='<span class="border-image img-border">';
												if(result[0].list[i].list[j].partyFlag !=null){
													
													representative+='<img onerror="setDefaultImage(this);" src="images/party_flags/'+result[0].list[i].list[j].partyFlag+'" alt="party" style="height:25px;width:25px;"/>';
												}else{
													representative+='<img  src="images/User.png" alt="party" style="width:20px;"/>';
												}
													
												representative+='</span>';
											representative+='</div>';
											representative+='<div class="media-body">';
												representative+='<h4 class="text-success text-capital m_left5">'+result[0].list[i].list[j].candidateName+'';
													if(result[0].list[i].list[j].migrateCandidate == "true"){
														representative+='<span class="m_left5" style="font-size: 12px; top: 15px; position: absolute;">';
															  representative+='<i class="fa fa-star" aria-hidden="true" ></i>';
															  representative+='<i class="fa fa-star" aria-hidden="true" ></i>';
															 representative+=' <i class="fa fa-star" aria-hidden="true" ></i>';
														representative+='</span>';
													}
												representative+='</h4>';
												representative+='<p class="text-muted text-capital m_left7 f_12" style="margin-bottom:5px">'+result[0].list[i].list[j].party+' : '+result[0].list[i].list[j].constituencyName+'</p>';
												
												if(result[0].list[i].list[j].tdpCadreId !=null && result[0].list[i].list[j].tdpCadreId>0){
													representative+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[0].list[i].list[j].tdpCadreId+'">View Candidate Profile</span>';
												}else{
													representative+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[0].list[i].list[j].candidateId+'">View Candidate Profile</span>';
												}
											representative+='</div>';
										representative+='</div>';
									representative+='</div>';
								}
								
							}
						representative+='</div>';
					representative+='</div>';
					if(length > 4)
					{
						representative+='<p class="text-center assembly-viewRes" style="text-decoration:underline">Click to more</p>';
					}
					
				representative+='</div>';
			representative+='</div>';
		representative+='</div>';
	$("#representativeMembersId").html(representative);
	}
function getCountsForConstituency(){
	$("#levelWiseCountDivId").html(blockSpinner);
	var jsObj={
			levelId		  		:locationLevelId,
			levelValues		 	:userAccessLevelValuesArray,
			publicationDateId 	:22	
			
		}
	 $.ajax({
      type : "POST",
      url : "getAllLocationWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			return buildCountsForConstituency(result);
		}else{
			$("#levelWiseCountDivId").html(noData);
		}
	});	
	function buildCountsForConstituency(results){
		var str='';
		str+='<div class="block">';
			
			if(locationLevelId == 2){
				str+='<h4 class="panel-title theme-title-color">Andhra Pradesh</h4>';
			}
			 else if(locationLevelId == 3)
			{
				str+='<h4 class="panel-title theme-title-color">'+$(".districtMenuName").text()+' DISTRICT level Wise Details</h4>';
			}else if(locationLevelId == 4 || locationLevelId == 11)
			{
				str+='<h4 class="panel-title theme-title-color">'+$(".constituencyMenuName").text()+' Constituency level Wise Details</h4>';
			}else if(locationLevelId == 10)
			{
				str+='<h4 class="panel-title theme-title-color">'+$(".parliamentMenuName").text()+' Parliament level Wise Details</h4>';
			}else if(locationLevelId == 5)
			{
				str+='<h4 class="panel-title theme-title-color">'+$(".mandalsMenuName").text()+' level Wise Details</h4>';
			}else if(locationLevelId == 7)
			{
				str+='<h4 class="panel-title theme-title-color">'+$(".mandalsMenuName").text()+' level Wise Details</h4>';
			}else if(locationLevelId == 6)
			{
				str+='<h4 class="panel-title theme-title-color">'+$(".panchayatMenuName").text()+' Panchayat level Wise Details</h4>';
			}else if(locationLevelId == 8)
			{
				str+='<h4 class="panel-title theme-title-color">'+$(".panchayatMenuName").text()+' level Wise Details</h4>';
			} 
			str+='<table class="table table-bordered m_top15">';
				str+='<tr>';
					if(results.constituencyCount != null && results.constituencyCount >0){
						if(results.constituencyCount !=null && results.constituencyCount>0){
							str+='<td>';
								str+='<h4 class="text-capitalize text-muted">Constituencies</h4>';
								str+='<h2>'+results.constituencyCount+'</h2>';
								//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
							str+='</td>';
						}
					}
						if(results.tehsilCount !=null && results.tehsilCount>0){
							str+='<td>';
								str+='<h4 class="text-capitalize text-muted">Mandals</h4>';
								str+='<h2>'+results.tehsilCount+'</h2>';
								//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
							str+='</td>';
						}
						if(results.municipalityCount !=null && results.municipalityCount>0){
							str+='<td>';
								str+='<h4 class="text-capitalize text-muted">Municipalities</h4>';
								str+='<h2>'+results.municipalityCount+'</h2>';
								//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
							str+='</td>';
						}
						if(results.villageIdCount !=null && results.villageIdCount>0){
							str+='<td>';
								str+='<h4 class="text-capitalize text-muted">Panchayats</h4>';	
								str+='<h2>'+results.villageIdCount+'</h2>';
								//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
							str+='</td>';
						}
						if(results.totalNoOfWards !=null && results.totalNoOfWards>0){
							str+='<td>';
								str+='<h4 class="text-capitalize text-muted">Wards</h4>';
								str+='<h2>'+results.totalNoOfWards+'</h2>';
								//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
							str+='</td>';
						}
						if(results.boothCount !=null && results.boothCount>0){
							str+='<td>';
								str+='<h4 class="text-capitalize text-muted">Booths</h4>';
								str+='<h2>'+results.boothCount+'</h2>';
								//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
							str+='</td>';
						}
						if(results.hamletCount !=null && results.hamletCount>0){
							str+='<td>';
								str+='<h4 class="text-capitalize text-muted">Hamlets</h4>';
								str+='<h2>'+results.hamletCount+'</h2>';
								//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
							str+='</td>';
						}
				str+='</tr>';
			str+='</table>';
		str+='</div>';
		$("#levelWiseCountDivId").html(str);
	}
}
function getCountsForStateLevel(){
	var str='';
	str+='<h4 class="panel-title theme-title-color">Andhra Pradesh</h4>';
	str+='<div class="block m_top10">';
		str+='<table class="table table-bordered table-block-responsive">';
			str+='<tr>';
					str+='<td>';
						str+='<h4 class="text-capitalize text-muted">Constituencies</h4>';
						str+='<h2>175</h2>';
						//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str+='</td>';
					str+='<td>';
						str+='<h4 class="text-capitalize text-muted">Mandals</h4>';
						str+='<h2>673</h2>';
						//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str+='</td>';
					str+='<td>';
						str+='<h4 class="text-capitalize text-muted">Municipalities</h4>';
						str+='<h2>131</h2>';
						//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str+='</td>';
					str+='<td>';
						str+='<h4 class="text-capitalize text-muted">Panchayats</h4>';	
						str+='<h2>12975</h2>';
						//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str+='</td>';
					str+='<td>';
						str+='<h4 class="text-capitalize text-muted">Wards</h4>';
						str+='<h2>3494</h2>';
						//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str+='</td>';
					str+='<td>';
						str+='<h4 class="text-capitalize text-muted">Booths</h4>';
						str+='<h2>42399</h2>';
						//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str+='</td>';
					str+='<td>';
						str+='<h4 class="text-capitalize text-muted">Hamlets</h4>';
						str+='<h2>50372</h2>';
						//str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str+='</td>';
			str+='</tr>';
		str+='</table>';
	str+='</div>';
	$("#statelevelWiseCountDivId").html(str);
}
function getVotersAndcadreAgeWiseCount(pubId,enrollmentId){
	$("#constituencyVoterInfo").html(spinner);
	var jsObj={
		locationTypeId		: locationLevelId,
		locationValue		: locationLevelVal,
		publicationDateId	: pubId,
		enrollmentYearId	: enrollmentId
	}
    $.ajax({
      type : "GET",
      url : "getVotersAndcadreAgeWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildVotersAndcadreAgeWiseCount(result);
		}else{
			$("#constituencyVoterInfo").html(noData);
		}
  });
}
function buildVotersAndcadreAgeWiseCount(result){
	if(result !=null && result.length>0){
		var theadArr=['','total Voters','total Cadre','Male Voter','Male Cadre','Female Voter','Female Cadre'];
		var totalVotersCount =0;
		var totalCadresCount =0;
		var totalMaleVoterCount=0;
		var totalMaleCadreCount=0;
		var totalFemaleVoterCount=0;
		var totalFemaleCadreCount=0;
		var countVar1 =0;
		var countVar =0;
		var str='';
		for(var i in result){
			totalVotersCount =totalVotersCount+result[i].totalVoters;
			totalCadresCount =totalCadresCount+result[i].totalCadres;
			totalMaleVoterCount =totalMaleVoterCount+result[i].maleVoters;
			totalMaleCadreCount =totalMaleCadreCount+result[i].maleCadres;
			totalFemaleVoterCount =totalFemaleVoterCount+result[i].femaleVoters;
			totalFemaleCadreCount =totalFemaleCadreCount+result[i].femaleCadres;
			countVar1 =countVar1+1;
			if (countVar1 === 6) {
				break;
			}
		}
		str+='<div class="table-responsive">';
			str+='<table class="table tableVoters">';
				str+='<thead>';
				for(var i in theadArr){
					str+='<th>';
						if(theadArr[i] == "total Cadre" || theadArr[i] == "Male Cadre" || theadArr[i] =="Female Cadre"){
							str+='<img src="coreApi/img/tableHead1.png" alt="cadres"/>';
						}else{
							str+='<img src="coreApi/img/tableHead.png" alt="voters"/>';
						}
						str+='<h4 class="text-capitalize">'+theadArr[i]+'</h4>';
						if(theadArr[i] == ""){
							//str+='<h3></h3>';
						}else if(theadArr[i] == "total Voters"){
							str+='<h3>'+totalVotersCount+'</h3>';
						}else if(theadArr[i] == "total Cadre"){
							str+='<h3>'+totalCadresCount+'</h3>';
						}else if(theadArr[i] == "Male Voter"){
							str+='<h3>'+totalMaleVoterCount+'</h3>';
						}else if(theadArr[i] == "Male Cadre"){
							str+='<h3>'+totalMaleCadreCount+'</h3>';
						}else if(theadArr[i] == "Female Voter"){
							str+='<h3>'+totalFemaleVoterCount+'</h3>';
						}else if(theadArr[i] == "Female Cadre"){
							str+='<h3>'+totalFemaleCadreCount+'</h3>';
						}
					str+='</th>';
				}
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					if(i>0 && i<6){
						str+='<tr>';
							str+='<td>';
								str+='<b>'+result[i].ageRange+'</b>';
								/* str+='2000';
								str+='<span class="pull-right text-success">';
									str+='2%';
								str+='</span>'; */
							str+='</td>';
							str+='<td>';
								str+=''+result[i].totalVoters+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].totalVotersPerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].totalCadres+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].totalCadrePerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].maleVoters+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].maleVotersPerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].maleCadres+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].maleCadrePerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].femaleVoters+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].femaleVotersPerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].femaleCadres+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].femaleCadrePerc+'';
								str+='</span>';
							str+='</td>';
						str+='</tr>';
						countVar =countVar+1;
						if (countVar === 6) {
							break;
						}
					}
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		$("#constituencyVoterInfo").html(str);
	}
	
}
function highcharts(id,type,data,plotOptions,title,tooltip,legend){
	'use strict';
	$('#'+id).highcharts({
		colors:['#FF9900','#8D4553','#CCCCCC','#F25C81','#0D9615'],
		chart: type,
		title: title,
		tooltip:tooltip,
		subtitle: {
			text: null
		},
		plotOptions: plotOptions,
		legend:legend,
		series: data
	});
}
function getVotersCastGroupWiseCount(publicationId,enrollmentId){
	var jsObj={
      locationTypeId    :locationLevelId,
      locationValue    	:locationLevelVal,
      publicationDateId :publicationId,
      enrollmentyearId 	:enrollmentId
    }
	$.ajax({
      type : "POST",
      url : "getVotersCastGroupWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildVotersCastGroupWiseCount(result);
		}
	});  
}
function buildVotersCastGroupWiseCount(result){
	var str='';
	str+='<div class="col-sm-12">';
		str+='<h5 class="text-capital" style="color:#777">Caste Group Wise Voters Information</h5>';
	str+='</div>';
	str+='<div class="col-sm-6">';
		str+='<div id="casteInfoGraphBar" style="height:270px;"></div>';
		str+='<div class="m_top10">';
			str+='<table class="table table-bordered table-condensed" id="dataTableOverViewCastGroupId">';
				str+='<thead class="bg-DD">';
					str+='<tr>';
					str+='<th>Caste Group</th>';
					str+='<th>Total Voter</th>';
					str+='<th>%</th>';
					str+='<th>Total Cadre</th>';
					str+='<th>%</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							str+='<td>'+result[i].ageRange+'</td>';
							str+='<td>'+result[i].totalVoters+'</td>';
							str+='<td>'+parseFloat(result[i].totalVotersPerc).toFixed(2);+'</td>';
							str+='<td>'+result[i].totalCadres+'</td>';
							str+='<td>'+parseFloat(result[i].totalCadrePerc).toFixed(2);+'</td>';
						str+='</tr>';	
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-6">';
		str+='<h5 class="text-capital" style="color:#777">Top 15 Castes</h5>';
		str+='<div id="topTenCastesDivId" class="m_top10 table-responsive"></div>';
	str+='</div>';
	str+='<div class="col-sm-12 m_top20">';
		str+='<h5 class="text-capital" style="color:#777">Total Castes Wise Voters % & Cadres % <span class="hideShowBtn">+</span></h5>';
		str+='<div class="castesWiseVoterBlock" style="display:none;">';
			str+='<ul class="list-inline active-type-sorting m_top10" active-type="casteSorting">';
				str+='<li class="active" type="desending"><i class="glyphicon glyphicon-sort-by-attributes"></i></li>';
				str+='<li type="assending"><i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i></li>';
				str+='<li type="atozSorting">A-Z</li>';
				str+='<li type="ztoaSorting">Z-A</li>';
			str+='</ul>';
			str+='<ul class="list-inline m_top10">';
				str+='<li><span style="background-color:#95CEFF;height:15px;width:15px;display:inline-block;margin-right:3px;"></span>Voter</li>';
				str+='<li><span style="background-color:#434348;height:15px;width:15px;display:inline-block;margin-right:3px;"></span>Cadre </li>';
			str+='</ul>';
			str+='<div class="scollerDiv">';
				str+='<div id="totalCastesAndCadrePercBarGraph" class="m_top10"></div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-12 m_top10">';
			str+='<hr class="m_0" style="border-color:#d3d3d3"/>';
	str+='</div>';
	str+='<div class="col-sm-12 m_top10">';
		str+='<select class="form-control" role="tabListMobile">';
			str+='<option tab_id="0">ALL CASTE GROUPS</option>';
			for(var i in result){
				str+='<option tab_id="'+result[i].ageRange+'">'+result[i].ageRange+'</option>';
			} 
			str+='</select>';
			str+=' <ul class="nav nav-tabs" role="tablist">';
			str+='<li role="presentation" class="active casteGroupWiseClickCls" attr_id="0" attr_name="AllCaste"><a href="#AllCaste" aria-controls="AllCaste" role="tab" data-toggle="tab">ALL CASTE GROUPS</a></li>';
			for(var i in result){
				str+='<li role="presentation" class="casteGroupWiseClickCls" attr_id="'+result[i].ageRangeId+'" attr_name="'+result[i].ageRange+'"><a href="#'+result[i].ageRange+'" aria-controls="'+result[i].ageRange+'" role="tab" data-toggle="tab">'+result[i].ageRange+'</a></li>';
					
			}
			str+='</ul>';
			str+='<div class="tab-content">';
				str+='<div role="tabpanel" class="tab-pane active pad_10" id="AllCaste">';
					str+='<div id="AllCasteDivId"></div>';
				str+='</div>';
				for(var i in result){
					str+='<div role="tabpanel" class="tab-pane  pad_10" id="'+result[i].ageRange+'">';
						str+='<div id="'+result[i].ageRange+'CasteDivId"></div>';
					str+='</div>';
				}
			str+='</div>';
	str+='</div>';
	$("#casteOverViewDivId").html(str);
	$("#topTenCastesDivId,#totalCastesAndCadrePercBarGraph").html(spinner);
	$("#dataTableOverViewCastGroupId").dataTable({
		"paging":   false,
		"info":     false,
		"searching": false,
		"autoWidth": true,
		"sDom": '<"top"iflp>rt<"bottom"><"clear">'
	});
	responsiveTabs();
	var casteNameCategoryArr=[];
	var casteVoterCountArr=[];
	var casteCadreCountArr=[];
	if(result !=null && result.length>0){
		for(var i in result){
			casteNameCategoryArr.push(result[i].ageRange);
			casteVoterCountArr.push(result[i].totalVoters)
			casteCadreCountArr.push(result[i].totalCadres)
		}
	}
	var mainArr=[];
	if(result !=null && result.length>0){
		for(var i in result){
			var casteName = result[i].ageRange;
			var count=0;
				count = result[i].totalVoters
			var colorsId = globalCasteColorObj[result[i].ageRange.trim()];
			var obj = {
				name: casteName,
				y:count,
				color:colorsId
			}
			mainArr.push(obj);
		}
	}
	var id = 'casteInfoGraphBar';
	var type = {
		type: 'pie',
		backgroundColor:'transparent',
		options3d: {
			enabled: true,
			alpha: 25
		}
	};
	var title = {
		text: ''
	};
	var tooltip = {
		useHTML: true,
		backgroundColor: '#FCFFC5', 
		formatter: function() {
			var cnt = this.point.count;
			return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+Highcharts.numberFormat(this.percentage,1)+"%</b>";
		}  
	}; 
	var plotOptions ={ 
		pie: {
			innerSize: 90,
			depth: 80,
			dataLabels:{
				useHTML: true,
				enabled: false,
				formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return "<b style='color:"+this.point.color+"'>"+this.point.name+"<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
						}
					} 
			},
			showInLegend: true
		},
	};
	var legend = {
		enabled: true,
		layout: 'horizontal',
		align: 'left',
		verticalAlign: 'bottom',
		useHTML: true,
		
		labelFormatter: function() {
			return '<div><span style="color:'+this.color+'">'+this.name + '-'+Highcharts.numberFormat(this.percentage,1)+'%</span></div>';
		}
	};
	var data = [{
		name: '',
		data: mainArr
	}];
	highcharts(id,type,data,plotOptions,title,tooltip,legend);
}
function getCasteGroupNAgeWiseVoterNCadreCounts(casteGroupId,type,casteName,publicationId,enrollmentId,assendingType){
	if(type != "sortingType"){
		$("#"+casteName+"CasteDivId").html(spinner);
	}
	var jsObj={
		locationTypeId		:locationLevelId,
		locationValue		:locationLevelVal,
		publicationDateId	:publicationId,
		casteGroupId		:casteGroupId,
		enrollmentYearId	:enrollmentId,
		assendingType		:assendingType
	}
	$.ajax({
		type : "GET",
		url : "getVotersAndCadreCasteWiseCountAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
		if(result !=null && result.length>0){
			if(type == "onload"){
				buildCasteGroupNAgeWiseVoterNCadreCounts(result,casteGroupId);
				buildCasteGroupWiseDetailsCounts(result,casteGroupId,casteName);
			}else if(type == "sortingType"){
				buildGraphForCaste(result);
			}else{
				buildCasteGroupWiseDetailsCounts(result,casteGroupId,casteName);
			}
		}
	});	
}
function buildCasteGroupNAgeWiseVoterNCadreCounts(result,casteGroupId){
	buildGraphForCaste(result);
	var str='';
	var countVar =0;
	str+='<table class="table table-noborder" id="dataTableTopTenCastGroupId">';
		str+='<thead class="bg-DD">';
			str+='<tr>';
			str+='<th>Caste Name</th>';
			str+='<th>Voters</th>';
			str+='<th>%</th>';
			str+='<th>Cadres</th>';
			str+='<th>%</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			//var k=0;
			for(var i in result){
				//k=k+1;
				str+='<tr>';
				str+='<td class="casteCategoryGroupWiseClickCls" attr_caste_id="'+result[i].ageRangeId+'" attr_caste_group_id="'+casteGroupId+'" attr_caste_name="'+result[i].ageRange+'" style="color:#337ab7;cursor:pointer;">'+result[i].ageRange+'</td>';
				str+='<td>'+result[i].totalVoters+'</td>';
				str+='<td>'+result[i].totalVotersPerc+'</td>';
				str+='<td>'+result[i].totalCadres+'</td>';
				str+='<td>'+result[i].totalCadrePerc+'</td>';
				str+='</tr>';
				countVar =countVar+1;
				if (countVar === 15) {
					break;
				}	
			}
		str+='</tbody>';
	str+='</table>';
	$("#topTenCastesDivId").html(str);
	$("#dataTableTopTenCastGroupId").dataTable({
		"paging":   false,
		"info":     false,
		"searching": false,
		"autoWidth": true,
		"sDom": '<"top"iflp>rt<"bottom"><"clear">'
	});
}
function buildGraphForCaste(result)
{
	var totalCasteNameCategoryArr=[];
	var totalCasteVoterCountArr=[];
	var totalCasteCadreCountArr=[];
	if(result !=null && result.length>0){
		for(var i in result){
			totalCasteNameCategoryArr.push(result[i].ageRange);
			//totalCasteVoterCountArr.push(result[i].totalVoters);
			//totalCasteCadreCountArr.push(result[i].totalCadres);
			totalCasteVoterCountArr.push({"y":result[i].totalVoters,"extra":result[i].totalVotersPerc});
			totalCasteCadreCountArr.push({"y":result[i].totalCadres,"extra":result[i].totalCadrePerc});
		}
	}
		var heightOfDiv = totalCasteNameCategoryArr.length;
		if(heightOfDiv >20){
			heightOfDiv = heightOfDiv * 60;
			$("#totalCastesAndCadrePercBarGraph").css("height",heightOfDiv);
			$(".scollerDiv").mCustomScrollbar({setHeight:'460px'})
		}else{
			$("#totalCastesAndCadrePercBarGraph").css("height","auto");
			$(".scollerDiv").removeAttr('style')
			$(".scollerDiv").mCustomScrollbar('destroy');
		}
		$("#totalCastesAndCadrePercBarGraph").highcharts({
			chart: {
				type: 'bar'
			},
			title: {
				text: ''
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: totalCasteNameCategoryArr,
				type: 'category',
				labels: {
					formatter: function() {
						return this.value.toString().substring(0, 15)+'';
						
					},
				}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
			},
			tooltip: {
				formatter: function () {
					var s = '<b>' + this.x + '</b>';
						$.each(this.points, function () {
						if(this.series.name != "Series 1")  
						s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
							this.y
					});

					return s;
				},
				shared: true
			},
			plotOptions: {
				bar: {
					pointWidth: 17,
					gridLineWidth: 12,
					dataLabels: {
						enabled: true,
						formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return this.y+"("+this.point.extra+")";
							}
						}
					}
				}
			},
			legend: {
				enabled: false
			},
			credits: {
				enabled: false
			},
			series: [{
				name: 'Voter',
				data: totalCasteVoterCountArr
			}, {
				name: 'Cadre',
				data: totalCasteCadreCountArr
			}]
		});
}
function buildCasteGroupWiseDetailsCounts(result,casteGroupId,casteName){
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-noborder" id="dataTable'+casteName+'">';
			str+='<thead class="text-capitalize">';
				str+='<th>Caste Name</th>';
					str+='<th>voters</th>';
					str+='<th>%</th>';
					
					str+='<th>cadres </th>';
					str+='<th>%</th>';
					
					str+='<th>Male(V) </th>';
					str+='<th>%</th>';
					
					str+='<th>Male(C) </th>';
					str+='<th>%</th>';
					
					str+='<th>FeMale(V) </th>';
					str+='<th>%</th>';
					
					str+='<th>FeMale(C) </th>';
					str+='<th>%</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td class="casteCategoryGroupWiseClickCls" attr_caste_id="'+result[i].ageRangeId+'" attr_caste_group_id="'+casteGroupId+'" attr_caste_name="'+result[i].ageRange+'" style="color:#337ab7;cursor:pointer;">'+result[i].ageRange+'</td>';
					str+='<td>'+result[i].totalVoters+'</td>';
					str+='<td class="text-success">'+result[i].totalVotersPerc+'</td>';
					
					str+='<td>'+result[i].totalCadres+'</td>';
					str+='<td class="text-success">'+result[i].totalCadrePerc+'</td>';
					
					str+='<td>'+result[i].maleVoters+'</td>';
					str+='<td class="text-success">'+result[i].maleVotersPerc+'</td>';
					
					str+='<td>'+result[i].maleCadres+'</td>';
					str+='<td class="text-success">'+result[i].maleCadrePerc+'</td>';
					
					str+='<td>'+result[i].femaleVoters+'</td>';
					str+='<td class="text-success">'+result[i].femaleVotersPerc+'</td>';
					
					str+='<td>'+result[i].femaleCadres+'</td>';
					str+='<td class="text-success">'+result[i].femaleCadrePerc+'</td>';
					
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#"+casteName+"CasteDivId").html(str);
	$("#dataTable"+casteName).dataTable({
		"iDisplayLength": 15,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
}
function getCasteNAgeWiseVoterNCadreCounts(casteGroupId,casteId,enrollmentId,publicationId,casteName){
	$("#casteCategoryModalDivId").html(spinner);
	jsObj={
		locationTypeId		:locationLevelId,
		locationValue		:locationLevelVal,
		casteGroupId		:casteGroupId,
		casteId				:casteId,
		publicationDateId	:publicationId,
		enrollmentYearId	:enrollmentId
    }
	$.ajax({
		type : "GET",
		url : "getCasteNAgeWiseVoterNCadreCountsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
		if(result != null && result.length > 0){
			var str='';
			$("#headingTitle").html('<h4 class="panel-title" style="padding: 5px; font-weight: 600;"><span class="text-capital">'+casteName+' Caste</span> - <span class="text-capitalize">voter and cadre information b/w age group</span></h4>')
			str+='<p class="text-muted text-right">';
				str+='<span class="f-11"><i class="glyphicon glyphicon-info-sign"></i> _(C) = Cadres ; _(V) = Voter</span>';
			str+='</p>';
			str+='<table class="table table-bordered table-condensed table-striped table-hover m_top10" id="dataTablecasteCategoryModal">';
				str+='<thead class="text-capitalize">';
					str+='<th>Age Range</th>';
					str+='<th>Voters</th>';
					str+='<th>%</th>';
					str+='<th>cadres </th>';
					str+='<th>%</th>';
					str+='<th>Male(V) </th>';
					str+='<th>%</th>';
					str+='<th>Male(C) </th>';
					str+='<th>%</th>';
					str+='<th>FeMale(V)</th>';
					str+='<th>%</th>';
					str+='<th>FeMale(C)</th>';
					str+='<th>%</th>';
				str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].ageRange+'</td>';
					str+='<td>'+result[i].totalVoters+'</td>';
					str+='<td class="text-success">'+result[i].totalVotersPerc+' </td>';
					
					str+='<td>'+result[i].totalCadres+'</td>';
					str+='<td class="text-success">'+result[i].totalCadrePerc+' </td>';
					
					str+='<td>'+result[i].maleVoters+'</td>';
					str+='<td class="text-success">'+result[i].maleVotersPerc+' </td>';
					
					str+='<td>'+result[i].maleCadres+'</td>';
					str+='<td class="text-success">'+result[i].maleCadrePerc+' </td>';
					
					str+='<td>'+result[i].femaleVoters+'</td>';
					str+='<td class="text-success">'+result[i].femaleVotersPerc+' </td>';
					
					str+='<td>'+result[i].femaleCadres+'</td>';
					str+='<td class="text-success">'+result[i].femaleCadrePerc+' </td>';
					str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			$("#casteCategoryModalDivId").html(str);
			$("#dataTablecasteCategoryModal").dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}else{
			$("#casteCategoryModalDivId").html("No Data Available.");
		}
	});	
}

function getActivityStatusList(){
	$("#activitesId").html(spinner);
	var userAccessLevelId=locationLevelId;
	var jsObj={
			fromDate : globalFromDate,
			toDate: globalToDate,
			year:"",
			locationValues : userAccessLevelValuesArray,
			locationId : userAccessLevelId
		}
	 $.ajax({
      type : "POST",
      url : "getActivityStatusListAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result!=null && result.length>0){
			return buildTable(result);
		}else{
			$("#activitesId").html(noData);
		}
	});	
	function buildTable(result)
	{
		var str = '';
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
				str+='<div class="col-sm-8">';
					str+='<div id="activitiesBarGraphDivId" style="height:200px;"></div>';
				str+='</div>';
			for(var i in result){
				str+='<div class="col-sm-4">';
					str+='<h4>'+result[i].locationsList[0].description+'</h4>';
						str+='<table class="table table-noborder f-12 m_top5">';
							str+='<thead class="text-capitalize bg-DD">';
								str+='<th>Activity</th>';
								str+='<th>Total</th>';
								str+='<th>Done</th>';
							str+='</thead>';
							str+='<tbody>';
								for(var j in result[i].locationsList){
									str+='<tr>';
										str+='<td>'+result[i].locationsList[j].name+'</td>';
										if(result[i].locationsList[j].totalVoters !=null && result[i].locationsList[j].totalVoters>0){
											str+='<td>'+result[i].locationsList[j].totalVoters+'</td>';
										}else{
											str+='<td> - </td>';
										}
										if(result[i].locationsList[j].totalResult !=null && result[i].locationsList[j].totalResult>0){
											str+='<td>'+result[i].locationsList[j].totalResult+'</td>';
										}else{
											str+='<td> - </td>';
										}
										
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					
				str+='</div>';
			}
			str+='</div>';
		str+='</div>';
		$("#activitesId").html(str);
		var levelNamesArr=[];
		var levelWiseCountArr = [];
		var countArr = [];	
		if(result[0].constituencyList !=null && result[0].constituencyList.length>0){
			for(var i in result[0].constituencyList){
				levelNamesArr.push(result[0].constituencyList[i].name);
				var uniqCnt = {"y":result[0].constituencyList[i].totalVoters,color:"#D3D3D3"};
				levelWiseCountArr.push({"y":result[0].constituencyList[i].totalResult});
				countArr.push(uniqCnt);
			}
		}
		$('#activitiesBarGraphDivId').highcharts({
			chart: {
				type: 'column'
			},
			title: {
				text: ''
			},
			xAxis: {
				 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,
				 categories:levelNamesArr,
					labels: {
						formatter: function() {
							return this.value.toString().substring(0, 20)+'...';
							
						},
						
					}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
			},
			tooltip: {
				formatter: function () {
					var s = '<b>' + this.x + '</b><br/><b>Total:'+this.y+'</b>';
							
						$.each(this.points, function () {
						if(this.series.name != "Series 1")  
						s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
							this.y
					});

					return s;
				},
				shared: true
			},
			legend:{   
				enabled: false,				
				},				
			plotOptions: {
				column: {
					stacking: 'percent',  
					dataLabels:{
						enabled: false
					},
					
				},
				
			},
			series: [{
				data: countArr
			}, {
				name: "Completed",
				data: levelWiseCountArr,
				colorByPoint: true
			}]
		}); 
	}
}
function getLocationTypeWiseCadreCount(enrollmentId){
	$("#cadreInfoGraphDivId").html(spinner);	
	
	jsObj={
		locationTypeId:		locationLevelId,
		locationValuesArr:	userAccessLevelValuesArray,
		year:enrollmentId
	}
	$.ajax({
		type : "POST",
		url : "getLocationTypeWiseCadreCountAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result !=null && result.length>0){
			return buildLocationTypeWiseCadreCount(result,enrollmentId);
		}else{
			$("#cadreInfoGraphDivId").html(noData);
		}
	});	
	function buildLocationTypeWiseCadreCount(result){
			var str='';
			str+='<ul class="list-inline cadre-info-list">';
			for(var i in result)
			{
				if(i==0){
					str+='<li class="bottom-arrow1 yearCadreDetails cadreBorderStyle" attr_enrollmentId="'+result[i].enrollmentYearId+'">';
				}else{
					str+='<li class="bottom-arrow1 yearCadreDetails" attr_enrollmentId="'+result[i].enrollmentYearId+'">';
				}
				
						str+='<h5 class="text-capital">'+result[i].enrollmentYear+' cadre</h5>';
						str+='<div class="col-sm-5">';
							str+='<div id="cadreInfoGraph'+i+'" style="height:130px;"></div>';
						str+='</div>';
						str+='<div class="col-sm-7">';
							str+='<table class="table table-noborder tableStyledCadre">';
								str+='<tr>';
									str+='<td>'+result[i].newCaderCount+' New <span class="colorNew"></span></td>';
									str+='<td>+</td>';
									str+='<td>'+result[i].renewalCadreCount+' Renewal <span class="colorRenewal"></span></td>';
								str+='</tr>';
								str+='<tr style="text-align:center;" class="totalCountCadre">';
									str+='<td>'+result[i].toalCadreCount+' Total <span class=""></span></td>';
								str+='</tr>';
							str+='</table>';
						str+='</div>';
				str+='</li>';
			}
			str+='</ul>';
			$("#cadreInfoGraphDivId").html(str);	
			$('.cadre-info-list').slick({
				slide: 'li',
				slidesToShow: 3,
				slidesToScroll: 1,
				infinite: false,
				swipe:false,
				touchMove:false,
				variableWidth: false,
				responsive: [
					{
						breakpoint: 1024,
						settings: {
							slidesToShow: 3,
							slidesToScroll: 3,
						}
					},
					{
						breakpoint: 600,
						settings: {
							slidesToShow: 2,
							slidesToScroll: 2
						}
					},
					{
						breakpoint: 480,
						settings: {
							slidesToShow: 1,
							slidesToScroll: 1
						}
					}
				]
			});
			for(var i in result){
				var newCount = result[i].newCaderCount;
				var renewalCount = result[i].renewalCadreCount;
				
				var id = 'cadreInfoGraph'+i+'';
				var type = {
					type: 'pie',
					backgroundColor:'transparent',
					options3d: {
						enabled: true,
						alpha: 25
					}
				};
				var title = {
					text: ''
				};
				var tooltip = {
					useHTML: true,
					backgroundColor: '#FCFFC5', 
					formatter: function() {
						var cnt = this.point.count;
						return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
					}  
				}; 
				var plotOptions ={
					pie: {
						innerSize: 50,
						depth: 20,
						dataLabels: {
							enabled: false,
							formatter: function() {
								return (this.y) + ' %';
							},
							distance: -10,
							color:'#333'
						},
						showInLegend: false
					},
				};
				var legend={enabled: false};
				var data = [{
					name: '',
					data: [
						{
						  name: 'New',
						  y: newCount,
						  color:"#8D4653"
						},
						{
						  name: 'Renewal',
						  y: renewalCount,
						  color:"#FF9900"
						}
					]
				}];
				highcharts(id,type,data,plotOptions,title,tooltip,legend);
			}
	}
}



function getAgeRangeGenerAndCasteGroupByCadreCount(enrollmentId){
	$("#cadreInfoGraphBar,#cadreInfoTableView").html(spinner);
	jsObj={
		locationTypeId	 :locationLevelId,
		locationValue	 :locationLevelVal,
		enrollmentYearId :enrollmentId
	}
	$.ajax({
      type : "POST",
      url : "getAgeRangeGenerAndCasteGroupByCadreCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			$("#cadreInfoGraphBar").css("height","200px");
			return buildAgeRangeGenerAndCasteGroupByCadreCount(result,enrollmentId);
		}else{
			$("#cadreInfoGraphBar").html('');
			$("#cadreInfoTableView").html('');
			$("#cadreInfoGraphBar").removeAttr("style");
		}
	});	
	function buildAgeRangeGenerAndCasteGroupByCadreCount(result,enrollmentId){
		if(result !=null && result.length>0){
			var str='';
			var totalCount=0;
			var totalPerc=0;
			str+='<div class="table-responsive">';
				str+='<table class="table table-noborder table-noborder-hover m_top10" id="dataTableCadreBlockId">';
					str+='<thead class="bg-DD">';
						str+='<th ></th>';
						str+='<th class="text-center">Total</th>';
						//str+='<th><span class="text-success">%</span></th>';
						if(result[0].casteGroupList !=null && result[0].casteGroupList.length>0){
							str+='<th class="text-center">Male</th>';
							str+='<th class="text-center"><span class="text-success">%</span></th>';
							str+='<th class="text-center">Female</th>';
							str+='<th class="text-center"><span class="text-success">%</span></th>';
							for(var i in result[0].casteGroupList){
								str+='<th class="text-center">'+result[0].casteGroupList[i].name+'</th>';
								str+='<th class="text-center"><span class="text-success">%</span></th>';
							}
						}
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							str+='<td class="text-center">'+result[i].name+'</td>';
							str+='<td class="text-center">'+result[i].toalCadreCount+'</td>';
							str+='<td class="text-center">'+result[i].maleCount+'</td>';
							str+='<td class="text-center">'+result[i].malePercentage+'</td>';
							str+='<td class="text-center">'+result[i].femaleCount+'</td>';
							str+='<td class="text-center">'+result[i].femalePercentage+'</td>';
							if(result[i].casteGroupList !=null && result[i].casteGroupList.length>0){
								for(var j in result[i].casteGroupList){
									str+='<td class="text-center">'+result[i].casteGroupList[j].toalCadreCount+'</td>';
									str+='<td class="text-center">'+result[i].casteGroupList[j].percentage+'</td>';
								}		
							}
						str+='</tr>';
					}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			$("#cadreInfoTableView").html(str);
			$("#dataTableCadreBlockId").dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}
			var ageRangeNameArr =[];
			var cadreCntArr = [];
			var count = [];
			var totalCadreCount = 0;
		if(result != null && result.length > 0){
				for(var i in result){
					totalCadreCount =totalCadreCount+result[i].toalCadreCount;
					ageRangeNameArr.push(result[i].name);
					cadreCntArr.push({y:result[i].toalCadreCount,color:"#C69C6C"});
					var uniqCnt = {y:parseInt(totalCadreCount)-parseInt(result[i].toalCadreCount),color:"#D3D3D3"};
					count.push(uniqCnt);
				}
				$("#cadreInfoGraphBar").highcharts({
					//colors: ['#53BF8B'],     
					chart: {
						type: 'column'
					},
					title: {
						text: ''
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: ageRangeNameArr,
						type: 'category',
					},
					yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						title: {
							text: ''
						},
					},
					tooltip: {
						formatter: function () {
							var s = '<b>' + this.x + '</b>';
							$.each(this.points, function () {
								if(this.series.name != "Series 1")  
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									this.y/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
							});
							return s;
						},
						shared: true
					},
					legend: {   
							enabled: false,				
					},				
					plotOptions: {
							column: {
								stacking: 'percent',  
								dataLabels:{
									enabled: false,
									formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return (this.y);
										}
									}
								},
								
							},
						},
					series: [{
						data: count    
					}, {
						name: "Number of CadreCounts",
						data: cadreCntArr,
						//colorByPoint: true,
						stackLabels: {
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							},
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							},
					}
					}]
				});
		}
	}
}

function getPrintMediaCountsForConstituencyPage(){
	$("#newsMainBlockDivId").html(spinner);
	var userAccessLevelValuesNewsArray=[];
	var locationLevelNewsVal='';
	if(locationLevelId == '2')
	{
		locationLevelNewsVal ='2';
		userAccessLevelValuesNewsArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		locationLevelNewsVal ='3';
		userAccessLevelValuesNewsArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		locationLevelNewsVal = '4';
		userAccessLevelValuesNewsArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelNewsVal = '5';
		userAccessLevelValuesNewsArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelNewsVal = '6';
		userAccessLevelValuesNewsArray.push(mandalId.substring(1,mandalId.length))
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelNewsVal = '8';
		userAccessLevelValuesNewsArray.push(panchayatId.substring(1,panchayatId.length))
	}else if(locationLevelId == '7')
	{
		locationLevelNewsVal = '7';	
		userAccessLevelValuesNewsArray.push(mandalId.substring(1,mandalId.length))
	}else if(locationLevelId == '8')
	{
		locationLevelNewsVal = '9';		
		userAccessLevelValuesNewsArray.push(panchayatId.substring(1,panchayatId.length))
	}
	
	var state="ap";
	var date1 = globalFromDate.split('/');
	var date2 = globalToDate.split('/');
	var startDate = date1[0]+'-'+date1[1]+'-'+date1[2]
	var endDate = date2[0]+'-'+date2[1]+'-'+date2[2]
	var newsPaperIdsStr=[1,2,3,10,11,12];
    var impactScopeIdsStr=[1,2,3,4,5,6,8];
    var benefitIdsStr=[1,2];
    var orgIdsStr=[163,265,269,362,514,872,1117,1853];
    var type="Print";
    var isDept="N";
	
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaCountsForConstituencyPage/"+locationLevelNewsVal+"/"+userAccessLevelValuesNewsArray+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+type+"/"+benefitIdsStr+"/"+isDept
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaCountsForConstituencyPage/"+locationLevelNewsVal+"/"+userAccessLevelValuesNewsArray+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+type+"/"+benefitIdsStr+"/"+isDept
	}).then(function(result){
		if(result !=null){
			return buildPrintMediaCountsForConstituencyPage(result);
		}else{
			$("#newsMainBlockDivId").html(noData);
		}
	});
	function buildPrintMediaCountsForConstituencyPage(result){
		if(result !=null){
			var str='';
			str+='<div class="row">';
				str+='<div class="col-sm-6 pad_right0">';
					str+='<div class="pad_5 blockHeights">';
						str+='<h4 class="text-capitalize"><img src="coreApi/img/news.png" style="width:30px;"/>&nbsp;&nbsp;&nbsp; print media</h4>';
						str+='<table class="table table-noborder m_top10">';
							str+='<thead class="text-capitalize f-12 bg-DD">';
								str+='<tr>';
									str+='<th>total articles</th>';
									str+='<th>positive</th>';
									str+='<th>negative</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
							if(result.coreDashBoardVOList !=null && result.coreDashBoardVOList.length>0){
								for(var i in result.coreDashBoardVOList){
									str+='<tr>';
									if(result.coreDashBoardVOList[i].organization == "TDP" || result.coreDashBoardVOList[i].organization == "YSRC"){
										str+='<td><p><img  onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList[i].organization+'_01.PNG" alt="party" style="height:25px;width:25px;"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList[i].count+'</span></p></td>';
									}else if(result.coreDashBoardVOList[i].organization == "JANASENA"){
										str+='<td><p><img  onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList[i].organization.trim()+'.PNG" alt="party" style="height:25px;width:25px;"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList[i].count+'</span></p></td>';
									}else{
										str+='<td><p><img onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList[i].organization+'.png" alt="party" style="height:25px;width:25px;"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList[i].count+'</span></p></td>';
									}
										str+='<td>'+result.coreDashBoardVOList[i].positiveCountMain+' <small class="m_left5 text-success">'+result.coreDashBoardVOList[i].positivePerc+'%</small></td>';
										str+='<td>'+result.coreDashBoardVOList[i].negativCountMain+' <small class="m_left5 text-danger">'+result.coreDashBoardVOList[i].negativePerc+'%</small></td>';
									str+='</tr>';
								}
							}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-6 pad_left0">';
					str+='<div class="pad_5 blockHeights">';
						str+='<h4 class="text-capitalize"><img src="coreApi/img/electronic.png" style="width:30px;"/>&nbsp;&nbsp;&nbsp; electronic media</h4>';
						str+='<table class="table table-noborder m_top10">';
							str+='<thead class="text-capitalize f-12 bg-DD">';
								str+='<tr>';
									str+='<th>total articles</th>';
									str+='<th>positive</th>';
									str+='<th>negative</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
							if(result.coreDashBoardVOList1 !=null && result.coreDashBoardVOList1.length>0){
								for(var i in result.coreDashBoardVOList1){
									str+='<tr>';
										if(result.coreDashBoardVOList1[i].organization == "TDP" || result.coreDashBoardVOList1[i].organization == "YSRC"){
											str+='<td><p><img onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList1[i].organization+'_01.PNG" alt="party" style="height:25px;width:25px;"/>&nbsp;&nbsp;&nbsp;<span>'+result.coreDashBoardVOList1[i].count+'</span></p></td>';
										}else if(result.coreDashBoardVOList1[i].organization == "JANASENA"){
											str+='<td><p><img onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList1[i].organization.trim()+'.PNG" alt="party" style="height:25px;width:25px;"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList1[i].count+'</span></p></td>';
										}else{
											str+='<td><p><img onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList1[i].organization+'.png" alt="party" style="height:25px;width:25px;"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList1[i].count+'</span></p></td>';
										}
										str+='<td>'+result.coreDashBoardVOList1[i].positiveCountMain+' <small class="m_left5 text-success">'+result.coreDashBoardVOList1[i].positivePerc+'%</small></td>';
										str+='<td>'+result.coreDashBoardVOList1[i].negativCountMain+' <small class="m_left5 text-danger">'+result.coreDashBoardVOList1[i].negativePerc+'%</small></td>';
									str+='</tr>';
								}
							}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			$("#newsMainBlockDivId").html(str);
			/* var maxHeight = 0;
			 $(".blockHeights").each(function(){
			   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
			});
			$(".blockHeights").height(maxHeight); */
		}
	}
}


function getLeadersInNewsForConstituencyPage(){
	$("#leadersMainBlockDivId").html(spinner);
	var userAccessLevelValuesNewsArray=[];
	var locationLevelNewsVal='';
	if(locationLevelId == '2')
	{
		locationLevelNewsVal ='2';
		userAccessLevelValuesNewsArray.push(1)
	}else if(locationLevelId == '3')
	{
		locationLevelNewsVal ='3';
		userAccessLevelValuesNewsArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		locationLevelNewsVal = '4';
		userAccessLevelValuesNewsArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelNewsVal = '5';
		userAccessLevelValuesNewsArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelNewsVal = '6';
		userAccessLevelValuesNewsArray.push(mandalId.substring(1,mandalId.length))
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelNewsVal = '8';
		userAccessLevelValuesNewsArray.push(panchayatId.substring(1,panchayatId.length))
	}else if(locationLevelId == '7')
	{
		locationLevelNewsVal = '7';	
		userAccessLevelValuesNewsArray.push(mandalId.substring(1,mandalId.length))
	}else if(locationLevelId == '8')
	{
		locationLevelNewsVal = '9';		
		userAccessLevelValuesNewsArray.push(panchayatId.substring(1,panchayatId.length))
	}
	
	var stateId=1;
	var date1 = globalFromDate.split('/');
	var date2 = globalToDate.split('/');
	var startDate = date1[0]+'-'+date1[1]+'-'+date1[2]
	var endDate = date2[0]+'-'+date2[1]+'-'+date2[2]
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getLeadersInNewsForConstituencyPage/"+locationLevelNewsVal+"/"+userAccessLevelValuesNewsArray+"/"+startDate+"/"+endDate+"/"+stateId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getLeadersInNewsForConstituencyPage/"+locationLevelNewsVal+"/"+userAccessLevelValuesNewsArray+"/"+startDate+"/"+endDate+"/"+stateId
		
	}).then(function(result){
		if(result !=null && result.length>0){
			return buildLeadersInNewsForConstituencyPage(result)
		}else{
			$("#leadersMainBlockDivId").html(noData);
		}
	});
	function buildLeadersInNewsForConstituencyPage(result){
		var str='';
			str+='<h4 class="panel-title">leaders in news</h4>';
			str+='<div class="scrollerNewsDiv">';
				str+='<div class="panel-body pad_0">';
					str+='<table class="table table-noborder f-12">';
						str+='<thead class="bg-DD text-capitalize">';
							str+='<th>leader name</th>';
							str+='<th>designation</th>';
							str+='<th>positive</th>';
							str+='<th>negative</th>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								str+='<td>';
									str+='<img onerror="setDefaultImage(this);" src="images/candidates/'+result[i].name+'.jpg" class="img-responsive img-circle" style="height: 30px;width: 30px;display: inline-block"/>'+result[i].name+'';
								str+='</td>';
								str+='<td>'+result[i].isNewsBulletin+'</td>';
								str+='<td class="text-center">'+result[i].positiveCount+'</td>';
								str+='<td class="text-center">'+result[i].negativeCount+'</td>';
							str+='</tr>';
						}
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		$("#leadersMainBlockDivId").html(str);
		if(result.length > 20)
		{
			$(".scrollerNewsDiv").mCustomScrollbar({setHeight: '290px'});
		}
	}
}	
function getDetailedGovtOverAllAnalysisProblemsForConstituencyPage(typeValue){
	$("#overAllAnalysisProbDivId").html(spinner);
	var userAccessLevelValuesNewsArray=[];
	var locationLevelNewsVal='';
	if(locationLevelId == '2')
	{
		locationLevelNewsVal ='2';
		userAccessLevelValuesNewsArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		locationLevelNewsVal ='3';
		userAccessLevelValuesNewsArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		locationLevelNewsVal = '4';
		userAccessLevelValuesNewsArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelNewsVal = '5';
		userAccessLevelValuesNewsArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelNewsVal = '6';
		userAccessLevelValuesNewsArray.push(mandalId.substring(1,mandalId.length))
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelNewsVal = '8';
		userAccessLevelValuesNewsArray.push(panchayatId.substring(1,panchayatId.length))
	}else if(locationLevelId == '7')
	{
		locationLevelNewsVal = '7';	
		userAccessLevelValuesNewsArray.push(mandalId.substring(1,mandalId.length))
	}else if(locationLevelId == '8')
	{
		locationLevelNewsVal = '9';		
		userAccessLevelValuesNewsArray.push(panchayatId.substring(1,panchayatId.length))
	}
	var state="ap";
	var date1 = globalFromDate.split('/');
	var date2 = globalToDate.split('/');
	var startDate = date1[0]+'-'+date1[1]+'-'+date1[2]
	var endDate = date2[0]+'-'+date2[1]+'-'+date2[2]
	var npIdsStr=" ";
	
	var propertyIdStr=" ";
	if(typeValue != "overAll"){
		propertyIdStr=typeValue;
	}
	var impactScopeIdsStr=[1,2,3,4,5,6,7,8]
	var orgIdsStr="";
	var isDept="N";
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisProblemsForConstituencyPage/"+locationLevelNewsVal+"/"+userAccessLevelValuesNewsArray+"/"+state+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyIdStr+"/"+impactScopeIdsStr
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisProblemsForConstituencyPage/"+locationLevelNewsVal+"/"+userAccessLevelValuesNewsArray+"/"+state+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyIdStr+"/"+impactScopeIdsStr
	}).then(function(result){
		if(result !=null && result.length>0){
			buildDetailedGovtOverAllAnalysisProblemsForView(result,typeValue);
		}else{
			$("#overAllAnalysisProbDivId").html(noData);
		}
	});
	function buildDetailedGovtOverAllAnalysisProblemsForView(result,typeValue){
		if(typeValue == "overAll"){
			if(result !=null && result.length>0){
				var str='';
				str+='<div class="row">';
					str+='<div class="col-sm-12">';
						str+='<table class="table table-noborder f-12">';
							str+='<thead class="bg-DD text-capitalize">';
								str+='<th>status</th>';
								str+='<th>total</th>';
								str+='<th>%</th>';
							str+='</thead>';
							str+='<tbody>';
							for(var i in result){
								str+='<tr>';
									str+='<td><p><span class="overAllProbClr" style="background-color:'+globalColorNews[result[i].name]+'"></span>&nbsp;&nbsp;&nbsp; <span style="margin-left: 10px;">'+result[i].name+'</span></p></td>';
									str+='<td>'+result[i].count+'</td>';
									str+='<td>'+result[i].positivePerc+' %</td>';
								str+='</tr>';
							}
								
							str+='</tbody>';
						
						str+='</table>';
					
					str+='</div>';
					str+='<div class="col-sm-6 text-center">';
						str+='<div id="problemsDetailedGraph" style="height:200px;"></div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="panel-group m_top10" id="problemsCollapse" role="tablist" aria-multiselectable="true">';
				for(var i in result){
					str+='<div class="panel panel-default">';
						str+='<div class="panel-heading bg-fff" role="tab" id="heading'+i+'">';
						if(i == 0){
							str+='<a role="button"  data-toggle="collapse" class="collapseIcon" data-parent="#problemsCollapse" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
								str+='<h4 class="panel-title text-capital"><b>Problem Can be Solved'+result[i].name+'</b></h4>';
							str+='</a>';
						}else{
							str+='<a role="button" class="collapsed collapseIcon" data-toggle="collapse"  data-parent="#problemsCollapse" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
								str+='<h4 class="panel-title text-capital"><b>Probem Can be Solved '+result[i].name+'</b></h4>';
							str+='</a>';
						}
							
							
						str+='</div>';
					
						if(i == 0){
							str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
						}else{
							str+='<div id="collapse'+i+'" class="panel-collapse collapse " role="tabpanel" aria-labelledby="heading'+i+'">';
						}
						
							str+='<div class="panel-body">';
								str+='<div class="row">';
									str+='<div class="col-sm-12">';
										str+='<div class="scrollerProblemsDiv'+result[i].id+'">';
											str+='<div id="problemsContsDivId'+result[i].id+'"></div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+=' </div>';
					str+=' </div>';
				}
				str+='</div>';
				$("#overAllAnalysisProbDivId").html(str);
				var overAllAnalysisMainArr=[];
				for(var i in result){
					var name = result[i].name;
					var Perc=result[i].positivePerc;
					var colorsId = globalColorNews[result[i].name.trim()];
					var obj = {
						name: name,
						y:Perc,
						color:colorsId
					}
					overAllAnalysisMainArr.push(obj);
				}
				var id = 'problemsDetailedGraph';
				var type = {
					type: 'pie',
					backgroundColor:'transparent',
					options3d: {
						enabled: true,
						alpha: 25
					}
				};
				var title = {
					text: ''
				};
				var tooltip={};
				var plotOptions ={ 
					pie: {
						innerSize: 90,
						depth: 70,
						dataLabels: {
							enabled: false,
							formatter: function() {
								return (this.y) + ' %';
							},
							distance: -20,
							color:'#333'
						},
						showInLegend: false
					},
				};
				var legend={enabled: false};
				var data = [{
					name: '',
					data: overAllAnalysisMainArr
				}];
				highcharts(id,type,data,plotOptions,title,tooltip,legend);
			}else{
				$("#overAllAnalysisProbDivId").html("NO DATA");
			}
		}else{
			var collapse='';
			if(result !=null && result.length>0){
				collapse+='<table class="table table-noborder f-12">';
					for(var i in result){
						collapse+='<tr>';
							collapse+='<td>'+result[i].name+'</td>';
							collapse+='<td>'+result[i].count+'</td>';
						collapse+='</tr>';
					}
				collapse+='</table>';
				for(var i in propertyIdGlobalStr){
					$("#problemsContsDivId"+propertyIdGlobalStr[i]).html(collapse);
					$(".scrollerProblemsDiv"+propertyIdGlobalStr[i]).mCustomScrollbar({setHeight:'320px'})
				}
			}else{
				for(var i in propertyIdGlobalStr){
					$("#problemsContsDivId"+propertyIdGlobalStr[i]).html("NO DATA");
				}
			}
		}
	}
}
function getLocationWiseCommitteesCount(yearId){
	$("#committesTableDivId").html(spinner);
	for(var i in commitessArr)
	{
		$("#"+commitessArr[i]).html(spinner);
	}
	if(locationLevelId == '5' || locationLevelId == '7' || locationLevelId == '12' )
	{
		var locationMandalVal = mandalId;
		var priorVal = mandalId.substring(0,1);
		if(priorVal == 2){
			locationLevelId = '5';
		}else if(priorVal == 1){
			locationLevelName = "municipality";
		}
        locationLevelVal = locationMandalVal.substring(1,mandalId.length);	

	}else if(locationLevelId == '6' || locationLevelId == '8' || locationLevelId == '13' )
	{
		var locationPanchayatVal = panchayatId;
		var priorVal = panchayatId.substring(0,1);
		if(priorVal == 2){
			locationLevelName = "wards"
		}else if(priorVal == 1){
			locationLevelId = '6';
		}
 		locationLevelVal = locationPanchayatVal.substring(1,panchayatId.length);
	}
	var jsObj={
			locationType   : locationLevelName,
			locationId 	   :locationLevelVal,
			enrollmentId   : yearId
		}
	 $.ajax({
      type : "POST",
      url : "getLocationWiseCommitteesCountAction1.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	if(result !=null){
			return buildLocationWiseCommitteesCount(result);
		}else{
			$("#committesTableDivId").html(noData);
			for(var i in commitessArr)
			{
				$("#"+commitessArr[i]).html(noData);
			}
		}
	});	
	function buildLocationWiseCommitteesCount(result){
		
		var str='';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered">';
				str+='<thead>';
					str+='<tr class="text-capital">';
						str+='<th rowspan="2"></th>';
						str+='<th colspan="4">main committee</th>';
						str+='<th colspan="4"> affliated committee</th>';
					str+='</tr>';
					str+='<tr class="text-capitalize bg-DD">';
						str+='<th>total</th>';
						str+='<th>started</th>';
						str+='<th>completed</th>';
						str+='<th>Not Started</th>';
						
						str+='<th>total</th>';
						str+='<th>started</th>';
						str+='<th>completed</th>';
						str+='<th>Not Started</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody class="text-capitalize">';
					str+='<tr>';
						str+='<td>mandal/town/division</td>';
						str+='<td>'+result.mainMandalTotal+'</td>';
						str+='<td>'+result.mainMandalStartedCount+'</td>';
						str+='<td>'+result.mainMandalCompletedCount+'</td>';
						str+='<td>'+result.mainMandalNotYetStartedCount+'</td>';
						
						str+='<td>'+result.affMandalTotal+'</td>';
						str+='<td>'+result.affliatedMandalStartedCount+'</td>';
						str+='<td>'+result.affliatedMandalCompletedCount+'</td>';
						str+='<td>'+result.affiCommMandalNotStarted+'</td>';
					str+='</tr>';
					str+='<tr>';
						str+='<td>village/ward</td>';
						str+='<td>'+result.mainVillageTotal+'</td>';
						str+='<td>'+result.mainVillageStartedCount+'</td>';
						str+='<td>'+result.mainvillageCompletedCount+'</td>';
						str+='<td>'+result.mainVillageNotYetStartedCount+'</td>';
						
						str+='<td>'+result.affVillageTotal+'</td>';
						str+='<td>'+result.affliatedVillageStartedCount+'</td>';
						str+='<td>'+result.affliatedVillageCompletedCount+'</td>';
						str+='<td>'+result.affiCommVillageNotStarted+'</td>';
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
			
		$("#committesTableDivId").html(str);		
		var completedCount=0;
		var startedCount=0;
		var notStartedCount=0;
		for(var i in commitessArr){
			if(commitessArr[i] == "mandalLevelGraph"){
				completedCount=result.mainMandalCompletePer
				startedCount=result.mainMandalStartPer
				notStartedCount=result.mainMandalNotPer
			}else if(commitessArr[i] == "villageLevelGraph"){
				completedCount=result.mainVillageCompletePer
				startedCount=result.mainVillageStartPer
				notStartedCount=result.mainVillageNotPer
			}else if(commitessArr[i] == "affMandalLevelGraph"){
				completedCount=result.affMandalCompletePer
				startedCount=result.affMandalStartPer
				notStartedCount=result.affMandalNotPer
			}else if(commitessArr[i] == "affVillageLevelGraph"){
				completedCount=result.affVillageCompletePer
				startedCount=result.affVillageStartPer
				notStartedCount=result.affVillageNotPer
			}
			var id = commitessArr[i];
			var type = {
				type: 'pie',
				backgroundColor:'transparent',
				options3d: {
					enabled: true,
					alpha: 25
				}
			};
			var title = {
				text: ''
			};
			var tooltip = {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}; 
			var plotOptions ={
				pie: {
					innerSize: 90,
					depth: 70,
					dataLabels: {
						enabled: false,
						formatter: function() {
							return (Highcharts.numberFormat(this.percentage,1)) + ' %';
						},
						distance: -20,
						color:'#333'
					},
					showInLegend: true
				},
			};
			var legend={enabled: true};
			var data = [{
				name: '',
				data: [
					{
					  name: 'Completed',
					  y: completedCount,
					  color:"#8D4653"
					},
					{
					  name: 'Started',
					  y: startedCount,
					  color:"#FF9900"
					},
					{
					  name: 'Not Started',
					  y: notStartedCount,
					  color:"#CCCCCC"
					}
				]
			}];
			highcharts(id,type,data,plotOptions,title,tooltip,legend);
		}	
	}
}


function getLocationWiseMeetingsCount(){
	$("#locationWiseMeetingsCount").html(spinner);
	
	jsObj={
		locationTypeId:	locationLevelId,
		locationValues:	userAccessLevelValuesArray,
		fromDate :customStartMeetingsDate,
		toDate :customEndMeetingsDate
	}
	$.ajax({
		type : "GET",
		url : "getLocationWiseMeetingsCountAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result!=null && result.length>0){
			return buildTable(result);
		}else{
			$("#locationWiseMeetingsCount").html(noData);
		}
	});	
	function buildTable(result)
	{
		var str='';
		
		var xindex = 0;
		for(var i in result){
			if(i > 0)
			{
				if( xindex == 0)
				{
					str+='<div class="row">';
				}
				str+='<div class="col-sm-6">';
					str+='<p class="text-capitalize"><b>'+result[i].ageRange+'</b></p>';
					str+='<div class="row">';
						str+='<div class="col-sm-6">';
							str+='<div id="meetingsGraphId'+i+'" style="width:250px;height:170px;"></div>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<h6>'+result[i].ageRange+'</h6>';
							str+='<h4 class="m_top5">'+result[i].totalCadres+'</h4>';
							str+='<div class="meetingLevelScroll'+i+'">';
								str+='<table class="table table-noborder f-12 m_top10">';
									str+='<thead class="bg-DD">';
										str+='<tr>';
											str+='<th>Level</th>';
											str+='<th>Total</th>';
											str+='<th>Done</th>';
										str+='</tr>';
									str+='</thead>';
									str+='<tbody>';
										for(var j in result[i].locationVotersVOList){
											str+='<tr>';
												str+='<td>'+result[i].locationVotersVOList[j].castgroup+'</td>';
												str+='<td>'+result[i].locationVotersVOList[j].totalCadres+'</td>';
												str+='<td>'+result[i].locationVotersVOList[j].maleCadres+'</td>';
											str+='</tr>';
										}
									str+='</tbody>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				xindex++;
				if(result.length-1 == i){
					if(xindex % 2 == 1){
						str+='</div>';
					}
				}
				if( xindex == 2){
					str+='</div>';
					xindex = 0;
				} 
			}
		}
		str+='</div>';
			
		$("#locationWiseMeetingsCount").html(str);
		for(var i in result)
		{
			$(".meetingLevelScroll"+i).mCustomScrollbar({setHeight:'130px'});
		}
		
		var alertCnt = [];
		var count = [];	
		var levelNamesArr=[];
		for(var i in result){
				alertCnt = [];
				count = [];
			for(var j in result[i].locationVotersVOList){
				levelNamesArr.push(result[i].locationVotersVOList[j].castgroup);
				 alertCnt.push({"y":result[i].locationVotersVOList[j].maleCadres});
				 var uniqCnt = {"y":result[i].locationVotersVOList[j].totalCadres,color:"#D3D3D3"};
				count.push(uniqCnt);
			}
			$('#meetingsGraphId'+i+'').highcharts({
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					 min: 0,
					 gridLineWidth: 0,
					 minorGridLineWidth: 0,
					 categories:levelNamesArr,
					labels: {
						enabled: false
					}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					},
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b><br/><b>Total:'+this.y+'</b>';
								
							$.each(this.points, function () {
							if(this.series.name != "Series 1")  
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								this.y/* +' - ' +
								(Highcharts.numberFormat(this.percentage,1)+'%'); */
						});

						return s;
					},
					shared: true
				},
				legend:{   
					enabled: false,				
					},				
				plotOptions: {
					column: {
						stacking: 'percent',  
						dataLabels:{
							enabled: false
						},
						
					},
				},
				series: [{
					data: count
				}, {
					name: "Completed Meetings",
					data: alertCnt,
					colorByPoint: true
				}]
			});
		}
	}
}
function getLocationWiseTourMembersComplainceDtls(){
	$("#locationWiseTourMembersComplainceDtls").html(spinner);
	jsObj={
		locationTypeId		:locationLevelId,
		locationValuesArr	:userAccessLevelValuesArray,
		fromDate			:customStartATMDate,
		toDate				:customEndATMDate,
		year				:"",
		stateId 			: 1
	}
	 $.ajax({
      type : "POST",
      url : "getLocationWiseTourMembersComplainceDtlsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result!=null && result.length>0){
			return buildTable(result);
		}else{
			$("#locationWiseTourMembersComplainceDtls").html("NO DATA AVAILABLE");
		}
	});	
	function buildTable(result)
	{
		var tableView = '';
		var totalCamplains=0;
		var totalNonCamplains=0;
		var tottalCount=0;
		var totalSubmitted=0;
		var submittedCount = 0;
		var totalNotSubmitted=0;
		var totalSubNotSubmmitted=0;
		var totalSubmittedLeaderCnt = 0;
		
		  for(var i in result){
				submittedCount = submittedCount+result[i].submitedLeaderCnt;
				totalNotSubmitted = totalNotSubmitted+result[i].notSubmitedLeaserCnt;
				totalSubmitted =totalSubmitted+result[i].totalSubmittedToursCnt;	
				tottalCount = tottalCount + result[i].count;//total leader
				totalCamplains = totalCamplains + result[i].complainceCnt;
				totalSubmittedLeaderCnt = totalSubmittedLeaderCnt + result[i].noOfLeaderCnt;
			}
		totalNonCamplains = totalSubmittedLeaderCnt - totalCamplains;
		tableView+='<div class="row">';
			tableView+='<div class="col-sm-4" style="border-right:1px solid #d3d3d3;">';
			var headingStr = "";
			if (result != null && result[0].toursMonthId>1) {
				headingStr = "Months";
			} else {
				headingStr = "Month"
			}
			
				tableView+='<p>Data Showing:<span>'+result[0].toursMonthId+''+headingStr+'</span></p>';
				tableView+='<table class="table table-bordered m_top10">';
					tableView+='<tbody style="background-color: #F1F2F2;">';
					tableView+='<tr>';
						tableView+='<td style="text-align:center;padding:20px">';
							tableView+='<h6>Total&nbsp;('+tottalCount+'*'+result[0].toursMonthId+'M)</h6>';
							tableView+='<p class="m_top10">'+totalSubmitted+'</p>';
						tableView+='</td>';
						tableView+='<td style="text-align:center;padding:20px">';
							tableView+='<h6>Submitted</h6>';
							tableView+='<p class="m_top20">'+submittedCount+'</p>';
						tableView+='</td>';	
						tableView+='<td style="text-align:center;padding:20px">';
							tableView+='<h6>Not Submitted</h6>';
							tableView+='<p class="m_top10">'+totalNotSubmitted+'</p>';
						tableView+='</td>';	
					tableView+='</tr>';
					tableView+='</tbody>';
				tableView+='</table>';
				tableView+='<h5 class="m_top10">TOTAL LEADERS : '+tottalCount+'</h5>';
				tableView+='<div id="toursGraphDivId" style="height:300px;"></div>';
			tableView+='</div>';
			tableView+='<div class="col-sm-8">';
				tableView+='<p class="pull-right f_12"><i class="glyphicon glyphicon-ok" style="color:#3DBC93;"></i> Complaince<i class="glyphicon glyphicon-remove" style="color:#FF0000;margin-left:10px;"></i> Non Complaince</p>';
				
				tableView+='<div class="col-sm-12">';
					tableView+='<div class="toursDesigCss">';
						tableView+='<ul class="toursSlickSlider list-inline m_0">';
						
								for(var i in result){
									var totalCnt= result[i].count;
									tableView+='<li style="border-right:1px solid #d3d3d3;">';
										tableView+='<div class="media">';
												tableView+='<div class="media-left">';
												if(result[i].count !=null && result[i].count>0){
													tableView+='<h4 class="roundedCss toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="all" style="color: #337ab7;">'+result[i].count+'</h4>';
												}else{
													tableView+='<h4 class="roundedCss"> - </h4>';
												}
													
												tableView+='</div>';
												tableView+='<div class="media-body media_width">';
													if(result[i].designation !=null && result[i].designation.length>13){
														tableView+='<p class="f-12 text-capital toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="all"><b>'+result[i].designation+'</b></p>';
													}else{
														tableView+='<p class="f-12 text-capital m_top10 toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="all"><b>'+result[i].designation+'</b></p>';
													}
													
												tableView+='</div>';
												tableView+='<div class="m_top5">';
													tableView+='<table class="table m_top10 tableborderNomiPost">';	
														tableView+='<tbody>';
														tableView+='<tr>';
																tableView+='<td>';
																	tableView+='<p class="f-12">Total<b>('+totalCnt+' * '+result[0].toursMonthId+'M)</b></p>';
																tableView+='</td>';
																tableView+='<td class="pull-right">'; 
																   if (result[i].totalSubmittedToursCnt != null && result[i].totalSubmittedToursCnt>0) {
																	   tableView+='<span  class="toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="all" style="color: #337ab7;">'+result[i].totalSubmittedToursCnt+'</span>';
																   } else {
																	   tableView+='<span>0</span>';
																   }
																	
																tableView+='</td>';
															tableView+='</tr>';
															tableView+='<tr>';
																tableView+='<td >';
																	tableView+='<p class="f-12">Submited</p>';
																tableView+='</td>';
																tableView+='<td class="pull-right">';
																    if (result[i].submitedLeaderCnt != null && result[i].submitedLeaderCnt > 0) {
																		tableView+='<span class="toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="submitted" style="color: #337ab7;">'+result[i].submitedLeaderCnt+'</span>';
																	} else {
																		 tableView+='<span">0</span>';
																	}
																	
																tableView+='</td>';
															tableView+='</tr>';
															
															tableView+='<tr>';
																tableView+='<td>';
																	tableView+='<p class="f-12">Not Submited</p>';
																tableView+='</td>';
																tableView+='<td class="pull-right">';
																 if (result[i].notSubmitedLeaserCnt != null && result[i].notSubmitedLeaserCnt > 0) {
																			tableView+='<span class="toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="notSubmitteed" style="color: #337ab7;">'+result[i].notSubmitedLeaserCnt+'</span>';
																 } else {
																		 tableView+='<span">0</span>';
																 }
																tableView+='</td>';
															tableView+='</tr>';
															
															tableView+='<tr>';
																tableView+='<td>';
																	tableView+='<p class="f-12">Complaince</p>';
																tableView+='</td>';
																tableView+='<td class="pull-right">';
																  if (result[i].complainceCnt != null && result[i].complainceCnt>0) {
																	  tableView+='<span class="toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="Complaince" style="color: #337ab7;">'+result[i].complainceCnt+'</span>';
																  } else {
																	  tableView+='<span">0</span>';
																  }
																tableView+='</td>';
															tableView+='</tr>';
															
															tableView+='<tr>';
																tableView+='<td>';
																	tableView+='<p class="f-12">Non-Complaince</p>';
																tableView+='</td>';
																tableView+='<td class="pull-right">';
																var nonComplainceCnt = result[i].noOfLeaderCnt-result[i].complainceCnt; // noOfLeaderCnt key contains tour submitedLeaderCnt
																   if (nonComplainceCnt != null && nonComplainceCnt>0) {
																	  		tableView+='<span class="toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="nonComplaince" style="color: #337ab7;">'+nonComplainceCnt+'</span>';
																   } else {
																	  		tableView+='<span">0</span>';
																   }
																tableView+='</td>';
															tableView+='</tr>';
														tableView+='</tbody>';	
														tableView+='</table>';	
													/* tableView+='<p class="toursCompNonComp">';
														if(result[i].complainceCnt !=null && result[i].complainceCnt>0){
															tableView+='<span><i class="glyphicon glyphicon-ok" style="color:#3DBC93;"></i> | <span class="toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="Complaince" style="color: #337ab7;">'+result[i].complainceCnt+'</span></span>';
														}else{
															tableView+='<span><i class="glyphicon glyphicon-ok" style="color:#3DBC93;"></i> | - </span>';
														}
														
														if(result[i].nonComplainceCnt !=null && result[i].nonComplainceCnt>0){
															tableView+='<span style="margin-left: 15px;"><i class="glyphicon glyphicon-remove" style="color:#FF0000;"></i> | <span class="toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="nonComplaince" style="color: #337ab7;">'+result[i].nonComplainceCnt+'</span></span>';
														}else{
															tableView+='<span style="margin-left: 15px;"><i class="glyphicon glyphicon-remove" style="color:#FF0000;"></i> | - </span>';
														}
														
													tableView+='</p>'; */
												tableView+='</div>';
										tableView+='</li>';
								}
							tableView+='</div>';	
								tableView+='</ul>';
							tableView+='</div>';
						tableView+='<div class="col-sm-12 m_top5">';
							tableView+='<div class="table-responsive">';
								tableView+='<table class="table table-hover tableStyledTour table-condensed" id="toursTableId">';
									var length = result[0].subList[0].monthList.length;
									tableView+='<thead class="text-capitalize bg-E9">';
											tableView+='<tr>';
												tableView+='<th rowspan="2">Leader Name</th>';
												tableView+='<th rowspan="2">Designation</th>';
												
												tableView+='<th colspan="'+length+'" class="text-center">Submitted</th>';
												tableView+='<th colspan="'+length+'" class="text-center">Complaince</th>';
											tableView+='</tr>';
											tableView+='<tr>';
												 for(var i in result[0].subList[0].monthList){
													tableView+='<th>'+result[0].subList[0].monthList[i].name+'</th>';
												} 
												 for(var i in result[0].subList[0].monthList){
													tableView+='<th>'+result[0].subList[0].monthList[i].name+'</th>';
												} 
											tableView+='</tr>';
										tableView+='</thead>';
									tableView+='<tbody>';
										for(var i in result)
										{
										 if (result[i].subList != null && result[i].subList.length > 0) {
											for(var j in result[i].subList)
											{
												 if (result[i].subList[j].monthList != null && result[i].subList[j].monthList.length > 0) {
													 
													  var  candidateName = result[i].subList[j].name;
														var designationName = result[i].subList[j].designation;
														var  candiateId = result[i].subList[j].id;
														tableView+='<tr>';	
														
														if(candidateName !=null && candidateName.length>17){
															
															tableView+='<td ><span style="cursor:pointer;color:#337ab7" attr_type="mainLevel" class="candiateCls tooltipTourCls" attr_candiate_id="'+candiateId+'" attr_candiate_name="'+candidateName+'" attr_designation_name="'+designationName+'"  data-toggle="tooltip" data-placement="right" title="'+candidateName+'">'+candidateName.substring(0,17)+'...</span></td>';
														}else{
															tableView+='<td style="cursor:pointer;color:#337ab7" attr_type="mainLevel" class="candiateCls" attr_candiate_id="'+candiateId+'" attr_candiate_name="'+candidateName+'" attr_designation_name="'+designationName+'">'+candidateName+'</td>';
														}
														if(designationName !=null && designationName.length>17){
															tableView+='<td><span class="tooltipTourCls" style="cursor:pointer;"  data-toggle="tooltip" data-placement="top" title="'+designationName+'">'+designationName.substring(0,17)+'...</span></td>'; 
														}else{
															tableView+='<td>'+designationName+'</td>'; 
														}
														 for(var k in result[i].subList[j].monthList){
															tableView+='<td>'+result[i].subList[j].monthList[k].isTourSubmitted+'</td>';
														 } 
														 for(var k in result[i].subList[j].monthList){
																if (result[i].subList[j].monthList[k].isComplaince != null && result[i].subList[j].monthList[k].isComplaince == "No Target") {
																	tableView+='<td>-</td>';
																} else {
																	if(result[i].subList[j].monthList[k].complaincePer>=100){
																	  tableView+='<td><i class="glyphicon glyphicon-ok" style="color:#3DBC93;"></i></td>';
																	}else{
																		if (result[i].subList[j].monthList[k].isTourSubmitted=='N') {
																			tableView+='<td>-</td>';
																		} else {
																			tableView+='<td><i class="glyphicon glyphicon-remove" style="color:#FF0000;"></i></td>';	
																		}
																	}
																}
															} 
													  tableView+='</tr>';	
												 }
											}	 
										 }
										}
									tableView+='</tbody>';
								tableView+='</table>';
							tableView+='</div>';
						tableView+='</div>';
					tableView+='</div>';
				tableView+='</div>';
		$("#locationWiseTourMembersComplainceDtls").html(tableView);
		$(".tooltipTourCls").tooltip();
		$('.toursSlickSlider').slick({
				slide: 'li',
				slidesToShow: 4,
				slidesToScroll: 1,
				infinite: false,
				swipe:false,
				touchMove:false,
				variableWidth: false,
				responsive: [
					{
						breakpoint: 1024,
						settings: {
							slidesToShow: 3,
							slidesToScroll: 3,
						}
					},
					{
						breakpoint: 600,
						settings: {
							slidesToShow: 2,
							slidesToScroll: 2
						}
					},
					{
						breakpoint: 480,
						settings: {
							slidesToShow: 1,
							slidesToScroll: 1
						}
					}
				]
			});
		$("#toursTableId").dataTable({
			"iDisplayLength": 5,
			"aaSorting": [],
			"aLengthMenu": [[5,10, 15, 20, -1], [5,10, 15, 20, "All"]]
		});
		var id = "toursGraphDivId";
		var type = {
			type: 'pie',
			backgroundColor:'transparent',
			options3d: {
				enabled: true,
				alpha: 25
			}
		};
		var title = {
			text: ''
		};
		var tooltip = {
			useHTML: true,
			backgroundColor: '#FCFFC5', 
			formatter: function() {
				var cnt = this.point.count;
				return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+this.y+")</b>";
			}  
		}; 
		var plotOptions ={
			pie: {
				innerSize: 140,
				depth: 110,
				dataLabels: {
					enabled: false,
					formatter: function() {
						return (Highcharts.numberFormat(this.percentage,1)) + ' %';
					},
					distance: -20,
					color:'#333'
				},
				showInLegend: true
			},
		};
		var legend = {
			enabled: true,
			layout: 'vertical',
			align: 'center',
			verticalAlign: 'bottom',
			useHTML: true,
			
			labelFormatter: function() {
				return '<div><span style="color:'+this.color+'">'+this.name + '- <b>' + this.y + ' - '+(Highcharts.numberFormat(this.percentage,1)) + ' %'+'</b></span></div>';
			}
		};
		var data = [{
			name: '',
			data: [
				{
				  name: 'Total Complaince',
				  y: totalCamplains,
				  color:"#FF3A00"
				},
				{
				  name: 'Total Non Complaince',
				  y: totalNonCamplains,
				  color:"#3BB878"
				}
			]
		}];
		highcharts(id,type,data,plotOptions,title,tooltip,legend);
	}
}
function getGovtSchemeWiseBenefitMembersCount(){
	$("#benefitsBlockId").html(spinner);
	
	
	jsObj={
		locationScopeId	:locationLevelId,
		locationValue	:locationLevelVal
	}
	 $.ajax({
      type : "POST",
      url : "getLocationwiseSchemesOverviewAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result !=null && result.list !=null && result.list.length>0 && result.subList1 !=null && result.subList1.length>0){
			$("#detailedBenefitBlockCls").show();
			return buildTabs(result,locationLevelId,locationLevelVal);
		}else{
			$("#benefitsBlockId").html(noData);
			$("#detailedBenefitBlockCls").hide();
		}
	});	
	function buildTabs(result,locationLevelId,locationLevelVal)
	{
		var str='';
		var totalBenefitsMemberesCount=0;
		var totalBenefitsAmountCount=0;
		var totalBenefitAmountCnt=0;
		var mainArr=[];
		var percentage=0;
		var totalSchemesCount=0;
		var totalBenefitsCount=0;
		if(result !=null && result.list !=null){
				totalBenefitsMemberesCount =result.list[0].earnedVote;
				totalBenefitsAmountCount =result.list[0].range;
				totalBenefitAmountCnt = result.list[0].status1;
		}
		if(result !=null && result.subList1 !=null && result.subList1.length>0){
			totalSchemesCount =totalSchemesCount+result.subList1.length;
			 for(var i in result.subList1){
				 if(result.subList1[i].totalSeatsCount !=null && result.subList1[i].totalSeatsCount != 0){
					totalBenefitsCount = totalBenefitsCount+1;
				} 
			}
		}
		mainArr.push(totalSchemesCount)
		mainArr.push(totalBenefitsCount)
		percentage = parseFloat((totalBenefitsCount*100)/totalSchemesCount);
		
		str+='<div class="col-sm-5" style="border-right: 1px solid rgb(204, 204, 204);">';
			str+='<div class="block">';
				str+='<div id="benefitMainGraphId" style="height:180px;"></div>';
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-6">';
						str+='<div class="media media_padding">';
							str+='<div class="media-left">';
								str+='<img src="coreApi/img/group.png" alt="Group"/></img>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6>Benefited Members</h6>';
								if(totalBenefitsMemberesCount !=null && totalBenefitsMemberesCount>0){
									str+='<h5 class="m_top10">'+totalBenefitsMemberesCount+'</h5>';
								}else{
									str+='<h5 class="m_top10">'+totalBenefitsMemberesCount+'</h5>';
								}
								
							str+=' </div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						str+='<div class="media media_padding">';
							str+='<div class="media-left">';
								str+='<i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:28px"></i>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6 class="m_top5">Benefited Amount</h6>';
								 if(totalBenefitsAmountCount !=null && totalBenefitsAmountCount>0){
									str+='<h5 class="m_top10"><span class="tooltipBenefitCls" data-toggle="tooltip" title="Amount in crores('+totalBenefitAmountCnt+')">'+totalBenefitsAmountCount+'</span></h5>';
								}else{ 
									str+='<h5 class="m_top10"><span class="tooltipBenefitCls" data-toggle="tooltip" title="Amount in crores('+totalBenefitAmountCnt+')">'+totalBenefitsAmountCount+'</span></h5>';
								}
								
							str+=' </div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<h5 class="m_top10">Location Wise Overview</h5>';
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table table_benefits" id="locationBenefitDT">';
					str+='<thead class="bg-E9">';
						str+='<tr>';
							str+='<th>Name</th>';
							str+='<th>Benefite Schemes</th>';
							str+='<th>Memberes</th>';
							str+='<th>Amount</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					if(result !=null && result.list !=null && result.list.length>0){
						for(var i in result.list){
							str+='<tr>';
								str+='<td>'+result.list[i].name+'</td>';
								if(result.list[i].wonSeatsCount !=null && result.list[i].wonSeatsCount>0){
									str+='<td>'+result.list[i].wonSeatsCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.list[i].totalSeatsCount1 !=null && result.list[i].totalSeatsCount1 !=""){
									str+='<td>'+result.list[i].totalSeatsCount1+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.list[i].participatedSeatsCount1 !=null && result.list[i].participatedSeatsCount1 !=""){
									str+='<td><span class="tooltipBenefitCls" data-toggle="tooltip" data-placement="left" title="Amount in crores('+result.list[i].participatedSeatsCount+')">'+result.list[i].participatedSeatsCount1+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
							str+='</tr>';
						}
						
					}else{
						str+='<tr colspan="4">';
							str+='<td>No Data Available</td>';
						str+='</tr>';
					}
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-7">';
			str+='<h5>Schemes Overview</h5>';
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table_benefits" id="benefitSchemeDT" style="width:100%">';
					str+='<thead class="">';
						str+='<tr>';
							str+='<th></th>';
							str+='<th></th>';
							str+='<th><img src="coreApi/img/group.png" alt="Group" style="width:25px;height:25px;"/></img></th>';
							str+='<th><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:18px"></i></th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					if(result !=null && result.subList1 !=null && result.subList1.length>0){
						for(var i in result.subList1){
							str+='<tr>';
								str+='<td>'+result.subList1[i].partyName+'</td>';
								str+='<td style="width:40%"><div id="schemesGraphId'+i+'" style="height:37px;"></div></td>';
								if(result.subList1[i].totalSeatsCount1 !=null && result.subList1[i].totalSeatsCount1 !=""){
									str+='<td>'+result.subList1[i].totalSeatsCount1+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result.subList1[i].participatedSeatsCount1 !=null && result.subList1[i].participatedSeatsCount1 !=""){
									str+='<td><span class="tooltipBenefitCls" data-toggle="tooltip" data-placement="left" title="Amount in crores('+result.subList1[i].participatedSeatsCount+')">'+result.subList1[i].participatedSeatsCount1+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								
							str+='</tr>';
						}
						
					}else{
						str+='<tr colspan="4">';
							str+='<td>No Data Available</td>';
						str+='</tr>';
					}
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
		
		$("#benefitsBlockId").html(str);
		$(".tooltipBenefitCls").tooltip();
		$("#benefitSchemeDT").dataTable({
			"paging":   true,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"iDisplayLength": 13,
			 "aaSorting": [[ 3, "desc" ]], 
			"aLengthMenu": [[13, 15, 20, -1], [13, 15, 20, "All"]]
		});
		$("#locationBenefitDT").dataTable({
			"paging":   true,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"iDisplayLength": 10,
			 "aaSorting": [[ 3, "desc" ]], 
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
		$("#benefitSchemeDT_length").remove();
		$("#locationBenefitDT_length").remove();
		$("#benefitMainGraphId").highcharts({
			colors:["#339900","#3BB878"],
			chart: {
				type: 'column',
				backgroundColor:'transparent'
			},
			title: {
				text: '',
				style: {
				 color: '#000',
				 font: 'bold 13px "Lato", sans-serif'
			  }
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,	
				type: 'category',
				categories: ['Total Schemes','Benefite Schemes'],
				
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				}
			},
			legend: {
				enabled: false
			},
			tooltip: {
				useHTML:true,	
				formatter: function () {
					if(this.x !="Total Schemes"){
						var pcnt = percentage;
						return '<b>' + this.x + '</b><br/>' +
						this.y+"-"+((Highcharts.numberFormat(pcnt)))+'%';
					}else{
						return '<b>' + this.x + '</b><br/>' +
						this.y+'';
					}
					
				}
			},
			plotOptions: { 
				column: {
					pointWidth: 30,
					gridLineWidth: 25,
					colorByPoint: true
				}
			},
			series: [{
				name: 'Count',
				data: mainArr,
				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'center',
					formatter: function() {
						if(this.x !="Total Schemes"){
							var pcnt = percentage;
							return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
						}else{
							return '<span>'+this.y+'</span>';
						}
						
					}
				}
			}]
		});
		if(result !=null && result.subList1 !=null && result.subList1.length>0){
			for(var i in result.subList1){
				var dataArr = [];
				var tempArr=[];
				var statusNamesArr=[];
				statusNamesArr.push(result.subList1[i].partyName)
				tempArr.push(result.subList1[i].percentage)
				dataArr.push(tempArr)
				$('#schemesGraphId'+i).highcharts({
					colors:['#3BB878'],
					chart: {
						type: 'bar'
					},
					title: {
						text: ''
					},
					
					xAxis: {
						type: 'category',
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						tickLength: 0,
						categories: statusNamesArr,
						labels: {
							enabled: false
						}
					},
					yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						tickLength: 0,
						title: {
							text: ''
						},
						labels: {
							enabled: false
						}
					},
					legend: {
						enabled: false
					},
					tooltip : {
						enabled: false,
						useHTML:true,
						formatter: function () {
							return '<b>' + this.x + '</b><br/>' +
								this.y+'%';
						}
					},
					plotOptions : { 
						bar: {
							pointWidth:15,
							gridLineWidth:10,
							colorByPoint: true,
							dataLabels: {
								enabled: true,
								formatter: function() {
									return '<span>'+this.y+' %</span>';
								} 
							}
							
						}
					},
					series: [{
						name: 'Memberes',
						data: dataArr,
						
					}]
				});
			}
		}
		
	}
}

function getMandalWiseBenefitMembersCount(id){
	$("#benefits"+id).html(spinner);
	
	jsObj={
		locationTypeId:locationLevelId,
		locationValue:locationLevelVal,
		govtSchemeId:id,
		publicationDateId:22
	}
	$.ajax({
	  type : "POST",
	  url : "getMandalWiseBenefitMembersCountAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result!=null && result.length>0){
			return buildTabsBody(result,id,locationLevelId);
		}else{
			$("#benefits"+id).html(noData);
		}
	});
	
	function buildTabsBody(result,id,locationLevelId)
	{
		var totalPopulation = 0;
		var totalBenefited = 0;
	
		for(var i in result)
		{
			totalPopulation = totalPopulation + result[i].totalPopulation;
			totalBenefited = totalBenefited + result[i].totalCount;
		}
		var navTabBody = '';
		navTabBody+='<div class="row">';
			navTabBody+='<div class="col-sm-6">';
				navTabBody+='<div id="benefitsGraph'+id+'" style="height:200px"></div>';
			navTabBody+='</div>';
			navTabBody+='<div class="col-sm-6">';
				navTabBody+='<table class="table table-noborder">';
					navTabBody+='<thead class="text-capitalize bg-DD">';
						navTabBody+='<th></th>';
						navTabBody+='<th>Total</th>';
						navTabBody+='<th>%</th>';
					navTabBody+='</thead>';
					navTabBody+='<tbody class="text-capitalize">';
						navTabBody+='<tr>';
							//navTabBody+='<td><span class="chart-legend-color" style="background-color:#fc8db7"></span>Population</td>';
							navTabBody+='<td><span class="chart-legend-color" style="background-color:#fc8db7"></span>Total voters</td>';
							navTabBody+='<td>'+totalPopulation+'</td>';
						navTabBody+='</tr>';
						navTabBody+='<tr>';
							navTabBody+='<td><span class="chart-legend-color" style="background-color:#27b720"></span>Benefited</td>';
							navTabBody+='<td>'+totalBenefited+'</td>';
						navTabBody+='</tr>';
					navTabBody+='</tbody>';
				navTabBody+='</table>';
			navTabBody+='</div>';
			navTabBody+='<div class="col-sm-12 m_top20">';
				navTabBody+='<div class="table-responsive">';
					navTabBody+='<table class="table table-noborder">';
						navTabBody+='<thead class="text-capitalize bg-DD">';
						if(locationLevelId == 2 ){
							navTabBody+='<th>District Name</th>';
						}else if(locationLevelId == 3 || locationLevelId == 10){
							navTabBody+='<th>Constituency Name</th>';
						}else if(locationLevelId == 4){
							navTabBody+='<th>Mandal Name</th>';
						}else if(locationLevelId == 5 || locationLevelId == 6){
							navTabBody+='<th>Panchayat Name</th>';
						}else if(locationLevelId == 7){
							navTabBody+='<th>Muncipality/Corporation Name</th>';
						}else if(locationLevelId == 8){
							navTabBody+='<th>Ward Name</th>';
						}
							//navTabBody+='<th>Population</th>';
							navTabBody+='<th>Total voters</th>';
							navTabBody+='<th>Benefited</th>';
							navTabBody+='<th>%</th>';
						navTabBody+='</thead>';
						navTabBody+='<tbody class="text-capitalize">';
							for(var i in result)
							{
								navTabBody+='<tr>';
									if(result[i].name != null && result[i].name != "")
										navTabBody+='<td>'+result[i].name+'</td>';
									else
										navTabBody+='<td>Others</td>';
									if(result[i].totalPopulation != null)
										navTabBody+='<td>'+result[i].totalPopulation+'</td>';
									else
										navTabBody+='<td>-</td>';
									navTabBody+='<td>'+result[i].totalCount+'</td>';
									if(result[i].totalPopulation != null)
										navTabBody+='<td>'+(((result[i].totalCount)/(result[i].totalPopulation))*100).toFixed(2)+'%</td>';
									else
										navTabBody+='<td>-</td>';
								navTabBody+='</tr>';
							}
						navTabBody+='</tbody>';
					navTabBody+='</table>';
				navTabBody+='</div>';
			navTabBody+='</div>';
		navTabBody+='</div>';
		$("#benefits"+id).html(navTabBody);
		
		var id = 'benefitsGraph'+id;
			var type = {
				type: 'pie',
				backgroundColor:'transparent',
				options3d: {
					enabled: true,
					alpha: 25
				}
			};
			var title = {
				text: ''
			};
			var tooltip = {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}; 
			var plotOptions ={
				pie: {
					innerSize: 90,
					depth: 70,
					dataLabels: {
						enabled: true,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -20,
						color:'#333'
					},
					showInLegend: false
				},
			};
			var legend={enabled: false};
			var data = [{
				name: '',
				data: [
					{
					  name: 'Population',
					  y: totalPopulation,
					  color:"#fc8db7"
					},
					{
					  name: 'Benfited',
					  y: totalBenefited,
					  color:"#27b720"
					}
				]
			}];
			highcharts(id,type,data,plotOptions,title,tooltip,legend);
	}
}
function getPositionWiseMemberCount(){
	$("#positionsWiseMemberCount").html(spinner);
	
	
	var jsObj={
			"fromDateStr" : "",
			"toDateStr":"",
			"locationValuesArr":userAccessLevelValuesArray,
			"locationTypeId":locationLevelId,
			"year":""
			
		}
	$.ajax({
		type : "POST",
		url : "getPositionWiseMemberCountAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result!=null && result.length>0){
			return build(result);
		}else{
			$("#positionsWiseMemberCount").html(noData);
		}
		function build(result)
		{
			var str='';
			str+='<ul class="list-border list-border-responsive">';
			for(var i in result){
				str+='<li>';
				if(result[i].name =="Panchayat/Ward/Division"){
					if(locationLevelId ==2 || locationLevelId ==3 || locationLevelId ==10){
						str+='<h3 class="" attr_boardLevelId="'+result[i].id+'" attr_department_name="'+result[i].name+'">'+result[i].count+'</h3>';
					str+='<p class="text-capitalize">Village/Ward</p>';
					}else{
						if(result[i].count !=null && result[i].count>0){
							str+='<h3 class="popUpDetailsClickCls" attr_boardLevelId="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_type="positionLevel">'+result[i].count+'</h3>';
						}else{
							str+='<h3 class=""> - </h3>';
						}
						
						str+='<p class="text-capitalize">Village/Ward</p>';
					}
					
				}else if(result[i].name =="Mandal/Muncipality/Corporation"){
					if(locationLevelId ==2 || locationLevelId ==3 || locationLevelId ==10){
						str+='<h3 class="" attr_boardLevelId="'+result[i].id+'" attr_department_name="'+result[i].name+'">'+result[i].count+'</h3>';
					str+='<p class="text-capitalize">'+result[i].name+'</p>';
					}else{
						if(result[i].count !=null && result[i].count>0){
							str+='<h3 class="popUpDetailsClickCls" attr_boardLevelId="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_type="positionLevel">'+result[i].count+'</h3>';
						}else{
							str+='<h3 class=""> - </h3>';
						}
						
					str+='<p class="text-capitalize">'+result[i].name+'</p>';
					}
					
				}else{
					if(result[i].count !=null && result[i].count>0){
						str+='<h3 class="popUpDetailsClickCls" attr_boardLevelId="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_type="positionLevel">'+result[i].count+'</h3>';
					}else{
						str+='<h3 class=""> - </h3>';
					}
					
					str+='<p class="text-capitalize">'+result[i].name+'</p>';
				}
					
				str+='</li>';
			}
			str+='</ul>';
			$("#positionsWiseMemberCount").html(str);
		}
	});	
}
function getNominatedPostApplicationDetails(){
	$("#nominatedPostApplicationDetails").html(spinner);
	
	var jsObj={
			"year":"",
			"fromDateStr" :"",
			"toDateStr":"",
			"locationTypeId":locationLevelId,
			"locationValues":userAccessLevelValuesArray
		}
	$.ajax({
		type : "POST",
		url : "getNominatedPostApplicationStatusWiseCountAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result!=null && result.length>0){
			return buildGraph(result);
		}else{
			$("#nominatedPostApplicationDetails").html(noData);
		}
		function buildGraph(result)
		{
			var str='';
			var colors = ['#FFDA00','#EF8925','#EB5D52','#56A3E7','#63CCB9']
			var colorsArr = ['#FFF18B','#F8D1AF','#F9BEBA','#BDDBF6','#559A8E'];
			
			str+='<div class="row">';
				str+='<div class="col-sm-6 ">';
					str+='<div id="nominatedPostApplicationGraphDetails"></div>';
				str+='</div>';
				str+='<div class="col-sm-6 m_top20">';
					str+='<ul class="graph-legend">';
					for(var i in result){
						str+='<li style="background-color:'+colorsArr[i]+'" class="f-12"><span class="statusBox" style="background-color:'+colors[i]+'"></span>'+result[i].name+'<span class="count"><b>'+result[i].count+'</b></span></li>';
						
					}
					str+='</ul>';
				str+='</div>';
			str+='</div>';
			
		$("#nominatedPostApplicationDetails").html(str);
			
			var mainArr = [];
			for(var i in result){
				var subArr = [];
				var name = "";
				name = result[i].name;
				subArr={name:result[i].name,y:parseInt(result[i].count)};
				mainArr.push(subArr);			
			}
			
			$('#nominatedPostApplicationGraphDetails').highcharts({
				colors:['#FEEE99','#F4D1B5','#F2C2BE','#C1DBF3','#BDABA7'],
				chart: {
					type: 'column',
					height:150,
					backgroundColor: 'transparent',
					
				},
				title: {
					text: null
				},
				subtitle: {
					text: ''
				},
				plotOptions: {	
					column: {
						pointWidth: 30,
						gridLineWidth: 15,
						colorByPoint: true
					}, 
				},
				legend:{
					enabled:false
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,  
					minorGridLineWidth: 0,
					labels: {
						enabled: false
					}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					labels: {
						enabled: false
					}
				},
				tooltip: {
					formatter: function () {
						return '<b>'+this.point.name+' : '+(this.y) +'</b>';
					},
				},
				series:[{
					name: 'Application Status Count',
					data: mainArr
				}]
			});
		
		
			/* $('#nominatedPostApplicationDetails').highcharts({
				colors:['#FEEE99','#F4D1B5','#F2C2BE','#C1DBF3','#BDABA7'],
				chart: {
					height:'180',
					type: 'pie',
					backgroundColor: 'transparent',
					options3d: {
						enabled: false,
						alpha: 45
					}
				},	
				title: {
					text: null
				},
				subtitle: {
					text: ''
				},
				plotOptions: {	
					pie: {
						innerSize: 90,
						depth: 70,
						showInLegend: true,
						dataLabels: {
							enabled: false,
						}
					}, 
				},
				legend: {
					itemStyle: {
						fontWeight: 'normal',
						'font-family':'roboto',
						'font-size':'14px'
					},
					enabled: true,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'middle',
					useHTML: true,
					symbolHeight:'0',
					labelFormatter: function() {
						return '<ul class="graph-legend" style="width:240px"><li style="background-color:'+this.color+';"><span class="statusBox" style="background-color:'+this.extra+';"></span>'+this.name + '<span style="margin-left:8px;position: absolute;right: 10px;">' + this.y + '</span></li></ul>';
						//return '<div style="padding:5px;background-color:'+this.color+'"><span style="color:#fff">'+this.name + '-' + this.y + '</span></div>';
					}
				},
				series:[{
					name : 'Count',
					data: mainArr
				}]
			}); */
		}
		
	});	
}
function getNominatedPostStatusWiseCount(){
	$("#nominatedPostStatusWiseCount").html(spinner);
	
	var jsObj={
			"year":"",
			"fromDateStr" : "",
			"toDateStr":"",
			"locationTypeId":locationLevelId,
			"locationValues":userAccessLevelValuesArray
		}
	 $.ajax({
      type : "POST",
      url : "getNominatedPostStatusWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result!=null && result.length>0){
			return buildGraph(result);
		}else{
			$("#nominatedPostStatusWiseCount").html(noData);
		}
	});	
	function buildGraph(result)
	{
		var str='';
		var subArr = [];
		var colors = ['#E58D45','#DD675D','#7CB4E5']
		var colorsArr = ['#F4D1B5','#F2C2BE','#C2DCF3']
		var mainArr =[];
		var totArr=[];
		var totalCount =0;
		for(var i in result){
			totalCount=totalCount+result[i].count;
		}
		str+='<div class="row">';
			str+='<div class="col-sm-6 ">';
				str+='<div id="nominatedPostStatusWiseCountGraph"></div>';
			str+='</div>';
			str+='<div class="col-sm-6 m_top20">';
				str+='<ul class="graph-legend">';
				str+='<li style="background-color:#FEEE99" class="f-12"><span class="statusBox" style="background-color:#FED501"></span>TOTAL POSTS<span class="count"><b>'+totalCount+'</b></span></li>';
				for(var i in result){
					if(result[i].name == "GO ISSUED"){
						str+='<li style="background-color:'+colorsArr[i]+'" class="f-12"><span class="statusBox" style="background-color:'+colors[i]+'" ></span>COMPLETED/G.O ISSUED<span class="count popUpDetailsClickCls" attr_boardLevelId="0" attr_type="goIssued" attr_department_name="overAll" attr_board_statusIds="0">'+result[i].count+'</span></li>';
					}else{
						if(result[i].name == "OPEN"){
							str+='<li style="background-color:'+colorsArr[i]+'" class="f-12"><span class="statusBox" style="background-color:'+colors[i]+'"></span>'+result[i].name+' POSTS<span class="count popUpDetailsClickCls" attr_department_id="0" attr_boardLevelId="0" attr_type="open" attr_department_name="">'+result[i].count+'</span></li>';
						}else{
							str+='<li style="background-color:'+colorsArr[i]+'" class="f-12"><span class="statusBox" style="background-color:'+colors[i]+'"></span>'+result[i].name+' POSTS<span class="count"><b>'+result[i].count+'</b></span></li>';
						}
						
					}
					
				}
				str+='</ul>';
			str+='</div>';
		str+='</div>';
		$("#nominatedPostStatusWiseCount").html(str);
		totArr={name:'Total',y:totalCount,color:"#FDD501"};	
		mainArr.push(totArr);
		for(var i in result){
			var subArr = [];
			subArr={name:result[i].name,y:result[i].count};
			mainArr.push(subArr);
		}
		
		$('#nominatedPostStatusWiseCountGraph').highcharts({
			chart: {
				type: 'column',
				height:150,
				backgroundColor: 'transparent',
				
			},
			colors: colors,
			title: {
				text: null
			},
			subtitle: {
				text: ''
			},
			plotOptions: {	
				column: {
					pointWidth: 30,
					gridLineWidth: 15,
					colorByPoint: true
				}, 
			},
			legend:{
				enabled:false
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,  
				minorGridLineWidth: 0,
				labels: {
					enabled: false
				}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				},
				labels: {
					enabled: false
				}
			},
			tooltip: {
				formatter: function () {
					return '<b>'+this.point.name+' : '+(this.y) +'</b>';
				},
			},
			series:[{
				name: 'Posts Count',
				data: mainArr,
				dataLabels: {
					enabled: false,
					rotation: -90,
					color: '#FFFFFF',
					align: 'right',
					format: '{point.y}', 
					y: 10, 
					style: {
						fontSize: '13px',
						fontFamily: 'Verdana, sans-serif'
					}
				}
			}]
		});
	}
}
function getPublications(){
	var jsObj={
			
	}
	 $.ajax({
      type : "GET",
      url : "getPublicationsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	if(result !=null && result.length>0){
			return buildPublications(result);
		}
	});	
	function buildPublications(result){
		var str='';
		str+='<select class="chosen-select publicationChangeCls" id="publicationChangeId">';
		str+='<option value="0">Select Publication</option>';
		for(var i in result){
			if(result[i].id == "22"){
				str+='<option value="'+result[i].id+'" selected>'+result[i].name+'</option>';
			}else{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			
		}
		str+='</select>';
		
		$("#publicationsDivId").html(str);
		$("#publicationCasteId").html(str);
		$(".chosen-select").chosen();
		
	}
}

function getDetailedElectionInformaction(id,typeId){
	$("#assemblyElectionGraphDetails,#assemblyElectionDetails").html(spinner);
	var electionScopeIdsArr=[];
	electionScopeIdsArr.push(id);
	jsObj={
	  	constituencyId: typeId,
		electionScopeIdsArr:electionScopeIdsArr
    }
    $.ajax({
      type : "GET",
      url : "getDetailedElectionInformactionAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildAssemblyResultsTable(result,id)
		}else{
			$("#assemblyElectionGraphDetails,#assemblyElectionDetails").html(noData);
		}
	});
	
	function buildAssemblyResultsTable(result,id)
	{
		var str='';
		
		var mainArr=[];
		var electionYearArr = [];
		if(result[0].subList[0].subList !=null && result[0].subList[0].subList.length>0){
			for(var i in result[0].subList[0].subList){
				electionYearArr.push(result[0].subList[0].subList[i].id+" "+result[0].subList[0].subList[i].name)
			}
		}
		if(result[0].subList !=null && result[0].subList.length>0){
			for(var j in result[0].subList){
				if(result[0].subList[j].subList !=null && result[0].subList[j].subList.length>0){
					var partyWiseCounts=[];
					for(var k in result[0].subList[j].subList){
						partyWiseCounts.push(parseFloat(result[0].subList[j].subList[k].votingPercentage));
					}
				}
				var obj ={
						name: result[0].subList[j].name,
						data: partyWiseCounts
					}
					mainArr.push(obj)
					
			}
			
		}
		if(id == 2){
			str+='<h4 class="panel-title theme-title-color">Assembly Election Details</h4>';
		}else{
			str+='<h4 class="panel-title theme-title-color">Parliament Election Details</h4>';
		}
			
		str+='<table class="table table-noborder table_election_results tableHoverLevels" id="dataTableAssemblyElecBlock">';
			str+='<thead class="bg-DD text-capitalize">';
				str+='<th style="vertical-align: middle;">Year</th>';
				str+='<th style="vertical-align: middle;">Party</th>';
				str+='<th style="vertical-align: middle;"><img src="coreApi/img/winner_icon.png" style="width:25px;height:25px;" alt="green-hand"/> Won Candidate</th>';
				str+='<th style="vertical-align: middle;">Polled Votes</th>';
				str+='<th style="vertical-align: middle;border:1px solid #008855 !important;" >Margin Votes</th>';
				str+='<th style="vertical-align: middle;border:1px solid #008855 !important;" >%</th>';
				str+='<th style="vertical-align: middle;">Party</th>';
				str+='<th style="vertical-align: middle;"><img src="coreApi/img/loser_icon.png" style="width:25px;height:25px;" alt="red-hand"/> Lost Candidate</th>';
				str+='<th style="vertical-align: middle;">Polled Votes</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				if(id == 2){
					str+='<tr class="popUpDetailsClickCls" attr_type="electionResults" attr_constituencyId="'+constituencyId+'" attr_election_year="'+result[i].electionYear+'" attr_election_type="Assembly" attr_election_typeId="2" attr_name="'+locationName+'" style="text-decoration:none;font-weight:normal;">';
				}else{
					str+='<tr class="popUpDetailsClickCls" attr_type="electionResults" attr_constituencyId="'+parliamentId+'" attr_election_year="'+result[i].electionYear+'" attr_election_type="parliament" attr_election_typeId="1" attr_name="'+locationName+'" style="text-decoration:none;font-weight:normal;">';
				}
				
				
					str+='<td>'+result[i].electionYear+'</td>';
					if(result[i].candidateResultsVO.partyShortName == "TDP" || result[i].candidateResultsVO.partyShortName == "YSRC"){
						str+='<td><img style="height:25px;width:25px;" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'_01.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'"/></td>';
					}else{
						str+='<td><img style="height:25px;width:25px;" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'"/></td>';
					}
					
					str+='<td>'+result[i].candidateResultsVO.candidateName+'</td>';
					str+='<td>'+result[i].candidateResultsVO.votesEarned+'</td>';
					
					if(result[i].candidateResultsVO.votesMargin !=null && result[i].candidateResultsVO.votesMargin>0){
						str+='<td style="border:1px solid #008855 !important;text-align:center;">'+result[i].candidateResultsVO.votesMargin+'</td>';
					}else{
						str+='<td style="border:1px solid #008855 !important;text-align:center;"> - </td>';
					}
					if(result[i].candidateResultsVO.votesPercentMargin !=null && result[i].candidateResultsVO.votesPercentMargin>0){
						str+='<td style="border:1px solid #008855 !important;text-align:center;">'+result[i].candidateResultsVO.votesPercentMargin+'</td>';
					}else{
						str+='<td style="border:1px solid #008855 !important;text-align:center;"> - </td>';
					}
					
					if(result[i].candidateOppositionList[0].partyShortName == "TDP" || result[i].candidateOppositionList[0].partyShortName == "YSRC"){
						str+='<td><img style="height:25px;width:25px;" src="images/party_flags/'+result[i].candidateOppositionList[0].partyShortName+'_01.PNG" alt="'+result[i].candidateOppositionList[0].partyShortName+'"/></td>';
					}else{
						str+='<td><img style="height:25px;width:25px;" src="images/party_flags/'+result[i].candidateOppositionList[0].partyShortName+'.png" alt="'+result[i].candidateOppositionList[0].partyShortName+'"/></td>';
					}
					str+='<td>'+result[i].candidateOppositionList[0].candidateName+'</td>';
					str+='<td>'+result[i].candidateOppositionList[0].votesEarned+'</td>';
					
				str+='</tr>';
			}
				
			str+='</tbody>';
		str+='</table>';
		$("#assemblyElectionDetails").html(str);
		$("#dataTableAssemblyElecBlock").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"sDom": '<"top"iflp>rt<"bottom"><"clear">',
			"order": [ 0, 'desc' ]
		});
		$('#assemblyElectionGraphDetails').highcharts({
			title: {
				text: ''
			},

			subtitle: {
				text: ''
			},
			xAxis: {
				min: 0,
				categories: electionYearArr,
			},
			yAxis: {
				min: 0,
				title: {
					text: ''
				}
			},
			legend: {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle'
			},
			tooltip: {
				shared:true
			},
			plotOptions: {
				series: {
					//pointStart: 2010
				}
			},
			series: mainArr
		});
	}
}
function getTotalAlertDetailsForConstituencyInfo(defaultAlertCategoryIds){
	$("#alertsBlockDivId").html(spinner);
	var jsObj={
			fromDateStr 	  	:"",
			toDateStr		  	:"",
			locationValuesArr 	:userAccessLevelValuesArray,
			alertTypeIdsStr 	:defaultAlertCategoryIds,
			locationTypeId		:locationLevelId,
			year  				:""

		}
	 $.ajax({
      type : "POST",
      url : "getTotalAlertDetailsForConstituencyInfoAction1.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){	
			return buildAlertsTable(result,defaultAlertCategoryIds);
		}else{
			$("#alertsBlockDivId").html(noData);
		}
	});	
	function buildAlertsTable(result,defaultAlertCategoryIds){
		var str='';
		str+='<h4>Alerts Overview</h4>';
		str+='<div class="table-responsive m_top10">';
			str+='<table class="table-alerts">';
				str+='<tr>';
					str+='<td colspan="3" style="background-color:#F6F6F6;"><div class="media"><div class="media-left"><img src="coreApi/img/total_alerts.png" style="width:50px;"/></div><div class="media-body"><h4>Total <span class="text-danger">Alerts</span></h4><h2 class="m_top10 popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="0" attr_alertCategeryId="" attr_alert_type="" attr_type="alert" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="Total" attr_total_count="'+result.totalAlertCount+'">'+result.totalAlertCount+'</h2></div></div></td>';
					if(result.subList !=null && result.subList.length>0){
						for(var i in result.subList)
						{
							str+='<td colspan="3"><h4>'+result.subList[i].status+'<br/>Alerts</h4></td>';
							
						}
					}
				str+='</tr>';
				str+='<tr>';
					str+='<td colspan="3">';
					if(result.alertTypeList !=null && result.alertTypeList.length>0){
						for(var i in result.alertTypeList){
							str+='<div class="media">';
								str+='<div class="media-left">';
								if(result.alertTypeList[i].status == "Party"){
									str+='<img class="media-object" src="coreApi/img/TDP_icon.png" alt="group">';
								}else if(result.alertTypeList[i].status == "Govt"){
									str+='<img class="media-object" src="coreApi/img/aplogo_icon.png" alt="group">';
								}
								str+='</div>';
								str+='<div class="media-body">';
									str+='<p>'+result.alertTypeList[i].status+' Alerts</p>';
									if(result.alertTypeList[i].count !=null && result.alertTypeList[i].count>0){
										
										str+='<h3 class="m_top5 popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="0" attr_alertCategeryId=""  attr_alert_type="" attr_type="alert" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result.alertTypeList[i].status+'" attr_total_count="'+result.alertTypeList[i].count+'">'+result.alertTypeList[i].count+'&nbsp;&nbsp;<small style="font-size:10px;" class="text-success text_bold">'+result.alertTypeList[i].percentage+'%</small></h3>';
									}else{
										str+='<h3 class="m_top5"> - </h3>';
									}
									
									
								str+='</div>';
							str+='</div>';
						}
					}	
					str+='</td>';
					if(result.subList !=null && result.subList.length>0){
						for(var i in result.subList)
						{
							if(result.subList[i].status == "Others"){
								if(result.subList[i].count !=null && result.subList[i].count>0){
									str+='<td colspan="3"><h4 class="popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="0" attr_alertCategeryId="'+result.subList[i].id+'" attr_alert_type="" attr_type="alert"  attr_otherCategory="categoryOthers" attr_impactIdsArr="" attr_status_name="'+result.subList[i].status+'" attr_total_count="'+result.subList[i].count+'">'+result.subList[i].count+'<small class="pull-right text-success text_bold m_top5">'+result.subList[i].percentage+' %</small></h4></td>';
								}else{
									str+='<td colspan="3"><h4> - </h4></td>';
								}
								
							}else{
								if(result.subList[i].count !=null && result.subList[i].count>0){
									str+='<td colspan="3"><h4 class="popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="0" attr_alertCategeryId="'+result.subList[i].id+'" attr_type="alert" attr_alert_type="" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result.subList[i].status+'" attr_total_count="'+result.subList[i].count+'">'+result.subList[i].count+'<small class="pull-right text-success text_bold m_top5">'+result.subList[i].percentage+' %</small></h4></td>';
								}else{
									str+='<td colspan="3"><h4> - </h4></td>';
								}
								
							}
							
						}
					}
				str+='</tr>';
				str+='<tr>';
					str+='<td colspan="3">';
						str+='<div class="row" style="width:220px">';
							str+='<div class="col-sm-12">';
							if(result.subList1 !=null && result.subList1.length>0){
									for(var i in result.subList1)
									{
										str+='<div style="padding:5px;font-size:12px;background-color:'+result.subList1[i].colour+'">';
											str+='<div class="row">';
												str+='<div class="col-sm-5">'+result.subList1[i].status+'</div>';
												if(result.subList1[i].status == "OTHERS"){
													if(result.subList1[i].count !=null && result.subList1[i].count>0){
														str+='<div class="col-sm-2 text_bold popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result.subList1[i].id+'" attr_designationId="0" attr_alertCategeryId="" attr_type="alert"  attr_alert_type="categoryOthers" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result.subList1[i].status+'" attr_total_count="'+result.subList1[i].count+'">'+result.subList1[i].count+'</div>';
													}else{
														str+='<div class="col-sm-2 text_bold"> - </div>';
													}
												}else{
													if(result.subList1[i].count !=null && result.subList1[i].count>0){
														str+='<div class="col-sm-2 text_bold popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result.subList1[i].id+'" attr_designationId="0" attr_alertCategeryId="" attr_type="alert" attr_alert_type="" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result.subList1[i].status+'" attr_total_count="'+result.subList1[i].count+'">'+result.subList1[i].count+'</div>';
													}else{
														str+='<div class="col-sm-2 text_bold"> - </div>';
													}
												}
												
												
												
												if(result.subList1[i].percentage !=null && result.subList1[i].percentage>0){
													str+='<div class="col-sm-5 text_bold"><p class="pull-right">'+result.subList1[i].percentage.toFixed(1)+' %</p></div>';
												}else{
													str+='<div class="col-sm-5 text_bold"> - </div>';
												}
												
											str+='</div>';
										str+='</div>';
									}
									
							}
							str+='</div>';
						str+='</div>';
					str+='</td>';
					if(result.subList !=null && result.subList.length>0){
						for(var i in result.subList)
						{
							str+='<td colspan="3">';
								str+='<div class="row" style="width:220px">';
									str+='<div class="col-sm-12">';
											for(var j in result.subList[i].subList)
											{
												str+='<div style="padding:5px;font-size:12px;background-color:'+result.subList[i].subList[j].colour+'">';
													str+='<div class="row">';
														str+='<div class="col-sm-5">'+result.subList[i].subList[j].status+'</div>';
														
														if(result.subList[i].status == "Others"){
															if(result.subList[i].subList[j].status =="OTHERS"){
																if(result.subList[i].subList[j].count !=null && result.subList[i].subList[j].count>0){
																	str+='<div class="col-sm-2 text_bold popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result.subList[i].subList[j].id+'" attr_designationId="0" attr_alertCategeryId="'+result.subList[i].id+'" attr_type="alert"  attr_alert_type="categoryOthers" attr_otherCategory="categoryOthers" attr_impactIdsArr="" attr_status_name="'+result.subList[i].subList[j].status+'" attr_total_count="'+result.subList[i].subList[j].count+'">'+result.subList[i].subList[j].count+'</div>';
																}else{
																	str+='<div class="col-sm-2"> -</div>';
																}
															}else{
																if(result.subList[i].subList[j].count !=null && result.subList[i].subList[j].count>0){
																	str+='<div class="col-sm-2 text_bold popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result.subList[i].subList[j].id+'" attr_designationId="0" attr_alertCategeryId="'+result.subList[i].id+'" attr_type="alert" attr_alert_type="" attr_otherCategory="categoryOthers" attr_impactIdsArr="" attr_status_name="'+result.subList[i].subList[j].status+'" attr_total_count="'+result.subList[i].subList[j].count+'">'+result.subList[i].subList[j].count+'</div>';
																}else{
																	str+='<div class="col-sm-2"> -</div>';
																}
															}
															
														}else{
															if(result.subList[i].subList[j].status =="OTHERS"){
																
																if(result.subList[i].subList[j].count !=null && result.subList[i].subList[j].count>0){
																	str+='<div class="col-sm-2 text_bold popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result.subList[i].subList[j].id+'" attr_designationId="0" attr_alertCategeryId="'+result.subList[i].id+'" attr_type="alert" attr_alert_type="categoryOthers" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result.subList[i].subList[j].status+'" attr_total_count="'+result.subList[i].subList[j].count+'">'+result.subList[i].subList[j].count+'</div>';
																}else{
																	str+='<div class="col-sm-2"> -</div>';
																}
															}else{									
																if(result.subList[i].subList[j].count !=null && result.subList[i].subList[j].count>0){
																	str+='<div class="col-sm-2 text_bold popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result.subList[i].subList[j].id+'" attr_designationId="0" attr_alertCategeryId="'+result.subList[i].id+'" attr_type="alert" attr_alert_type="" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result.subList[i].subList[j].status+'" attr_total_count="'+result.subList[i].subList[j].count+'">'+result.subList[i].subList[j].count+'</div>';
																}else{
																	str+='<div class="col-sm-2"> -</div>';
																}
															}
															
														}
														
														
														if(result.subList[i].subList[j].percentage !=null && result.subList[i].subList[j].percentage>0){
															str+='<div class="col-sm-5 text_bold"><p class="pull-right">'+result.subList[i].subList[j].percentage.toFixed(1)+' %</p></div>';
														}else{
															str+='<div class="col-sm-5"> - </div>';
														}
														
													str+='</div>';
												str+='</div>';
											}
									str+='</div>';
								str+='</div>';
							str+='</td>';
						}
					}
				str+='</tr>';
			str+='</table>';
		str+='</div>';
		if(result.impactScopeList !=null && result.impactScopeList.length>0){
			str+='<h4 class="m_top20">Impact level Overview</h4>';
			str+='<ul class="alerts-status-list m_top10">';
				for(var i in result.impactScopeList)
				{
					str+='<li>';
						str+='<h4 class="panel-title">'+result.impactScopeList[i].status+'</h4>';
						if(result.impactScopeList[i].status =="Others"){
							if(result.impactScopeList[i].count !=null && result.impactScopeList[i].count>0){
								str+='<h5 class="popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="0" attr_alertCategeryId="" attr_type="alert" attr_alert_type="" attr_otherCategory="impactOthers" attr_impactIdsArr="" attr_status_name="'+result.impactScopeList[i].status+'" attr_total_count="'+result.impactScopeList[i].count+'">'+result.impactScopeList[i].count+'&nbsp;&nbsp;<small class="f-10"> '+result.impactScopeList[i].percentage+'%</small></h5>';
							}else{
								str+='<h5 > - </h5>';
							}
							
						}else{
							if(result.impactScopeList[i].count !=null && result.impactScopeList[i].count>0){
								str+='<h5 class="popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="0" attr_alertCategeryId="" attr_type="alert" attr_alert_type="" attr_otherCategory="" attr_impactIdsArr="'+result.impactScopeList[i].id+'" attr_status_name="'+result.impactScopeList[i].status+'" attr_total_count="'+result.impactScopeList[i].count+'">'+result.impactScopeList[i].count+'&nbsp;&nbsp;<small class="f-10"> '+result.impactScopeList[i].percentage+'%</small></h5>';
							}else{
								str+='<h5 > - </h5>';
							}
							
						}
						
						
						for(var j in result.impactScopeList[i].subList){
							str+='<hr style="margin-top:8px;margin-bottom:8px"/>';
							if(result.impactScopeList[i].status =="Other"){
								
								if(result.impactScopeList[i].subList[j].count !=null && result.impactScopeList[i].subList[j].count>0){
									
									str+='<h6><span class="stausWiseColor" style="background-color:'+result.impactScopeList[i].subList[j].colour+';"></span><span style="margin-left: 5px;">'+result.impactScopeList[i].subList[j].status+' &nbsp;&nbsp;</span><span class="pull-right popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="0" attr_alertCategeryId="" attr_type="alert" attr_alert_type="impactOthers" attr_otherCategory="impactOthers" attr_impactIdsArr="" attr_status_name="'+result.impactScopeList[i].subList[j].status+'" attr_total_count="'+result.impactScopeList[i].subList[j].count+'">'+result.impactScopeList[i].subList[j].count+'</span></h6>';
								}else{
									str+='<h6><span class="stausWiseColor" style="background-color:'+result.impactScopeList[i].subList[j].colour+';"></span><span style="margin-left: 5px;">'+result.impactScopeList[i].subList[j].status+' &nbsp;&nbsp;</span> -</h6>';
								}
								
							}else{
								if(result.impactScopeList[i].subList[j].count !=null && result.impactScopeList[i].subList[j].count>0){
									if(result.impactScopeList[i].subList[j].status == "OTHERS"){
										str+='<h6><span class="stausWiseColor" style="background-color:'+result.impactScopeList[i].subList[j].colour+';"></span><span style="margin-left: 5px;">'+result.impactScopeList[i].subList[j].status+' &nbsp;&nbsp;</span><span class="pull-right popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result.impactScopeList[i].subList[j].id+'" attr_designationId="0" attr_alertCategeryId="" attr_type="alert" attr_alert_type="impactOthers" attr_otherCategory="" attr_impactIdsArr="'+result.impactScopeList[i].id+'" attr_status_name="'+result.impactScopeList[i].subList[j].status+'" attr_total_count="'+result.impactScopeList[i].subList[j].count+'">'+result.impactScopeList[i].subList[j].count+'</span></h6>';
									}else{
										str+='<h6><span class="stausWiseColor" style="background-color:'+result.impactScopeList[i].subList[j].colour+';"></span><span style="margin-left: 5px;">'+result.impactScopeList[i].subList[j].status+' &nbsp;&nbsp;</span><span class="pull-right popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result.impactScopeList[i].subList[j].id+'" attr_designationId="0" attr_alertCategeryId="" attr_type="alert" attr_alert_type="" attr_otherCategory="" attr_impactIdsArr="'+result.impactScopeList[i].id+'" attr_status_name="'+result.impactScopeList[i].subList[j].status+'" attr_total_count="'+result.impactScopeList[i].subList[j].count+'">'+result.impactScopeList[i].subList[j].count+'</span></h6>';
									}
								}else{
									str+='<h6><span class="stausWiseColor" style="background-color:'+result.impactScopeList[i].subList[j].colour+';"></span><span style="margin-left: 5px;">'+result.impactScopeList[i].subList[j].status+' &nbsp;&nbsp;</span> -</h6>';
								}
							}
							
							
						}
					str+='</li>';
				}
			str+='</ul>';
		}
		$("#alertsBlockDivId").html(str);
	}
}

function getEnrollmentIds(){
	var publicationDateIds = [11,22]
	var jsObj={
			publicationDateIdsLst : publicationDateIds
	}
	 $.ajax({
      type : "GET",
      url : "getEnrollmentYearsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var selectBox = '';
		for(var i in result)
		{
			if(result[i].id == "4"){
				selectBox+='<option value="'+result[i].id+'" selected>'+result[i].description+'</option>';
			}else{
				selectBox+='<option value="'+result[i].id+'">'+result[i].description+'</option>';
			}
			
		}
		//$("#enrolmentYears").html(selectBox);
		$("#enrollmentCasteId").html(selectBox);
		$("#enrollmentvoterId").html(selectBox);
		//$("#enrolmentYears").trigger("chosen:updated");
		$("#enrollmentCasteId").trigger("chosen:updated");
		$("#enrollmentvoterId").trigger("chosen:updated");
	});	
}
function setDefaultImage(img){
	img.src = "images/User.png";
}
function getElectionTypes(type){
	$("#electionTypeValuesId").html(spinner);
	$('#electionDetailsGraphWiseId').html('');
	$('#electionDetailsTableWiseId').html('');
	var jsObj={
			
	}
	$.ajax({
		type : "GET",	
		url : "getElectionTypesAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	if(result !=null && result.length>0){
			var str='';
			str+='<div class="row m_top10">';
				str+='<div class="col-sm-12">';
					str+='<label class="text-capital m_left5" style="margin-right: 10px;">';
						str+='<input value="0" type="checkbox" class="electionTypeWiseCls checkUncheckCls" /><span class="f-12">All</span>';
					str+='</label>';
					for(var i in result){
						str+='<label class="text-capital m_left5" style="margin-right: 10px;">';
						if(result[i].id != "5" && result[i].id != "6" && result[i].id != "7" && result[i].id != "8" && result[i].id != "9"){
							if(result[i].id == "1" || result[i].id == "2" || result[i].id == "3" ||result[i].id == "4"){
								if(locationLevelId == 4){
									if(result[i].id != "1"){
										str+='<input value="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
									}
								}else{
									str+='<input value="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
								}
								
							}
						}
						
										
						str+='</label>';
					}
				str+='</div>';
				str+='</div>';
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-2">';
						str+='<select class="" id="elctionBlockPartysId" multiple>';
							//str+='<option value="0" selected>All Parties</option>';
							for(var i in result[0].selectedCasteDetails){
								if(result[0].selectedCasteDetails[i].id == 163 || result[0].selectedCasteDetails[i].id == 265 || result[0].selectedCasteDetails[i].id == 269 || result[0].selectedCasteDetails[i].id == 872 || result[0].selectedCasteDetails[i].id == 1117  || result[0].selectedCasteDetails[i].id == 362 || result[0].selectedCasteDetails[i].id == 662  ){
									str+='<option value="'+result[0].selectedCasteDetails[i].id+'" selected>'+result[0].selectedCasteDetails[i].name+'</option>';
								}
							}
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-2">';
						str+='<select class="" id="elctionYearsBlockId" multiple></select>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<span id="electionTypeSpinnerId" style="display:none;"><img  src="coreApi/img/search.gif" alt="search"  class="m_top10" /></span>';
						str+='<label class="checkbox-inline">';
							str+='<input value="MAIN" type="checkbox" name="optionsRadios1" class="checkedMainByeType" checked/>Main Election';
						str+='</label>';
						str+='<label class="checkbox-inline">';
							str+='<input value="BYE" type="checkbox" name="optionsRadios1"  class="checkedMainByeType" />By Election';
						str+='</label>';
						str+='<label class="checkbox-inline">';
							str+='<input value="true" type="checkbox" name="allianceParty" id="allaincePartyFieldId" class="" />With Alliance';
						str+='</label>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<label class="radio-inline">';
							str+='<input value="wonSeat" type="radio" name="optionsRadios" class="checkedType" />Won Seats';
						str+='</label>';
						str+='<label class="radio-inline">';
							str+='<input value="voteShare" type="radio" name="optionsRadios"  class="checkedType" checked />Vote Share %';
						str+='</label>';
						str+='<button class="btn btn-primary btn-xs pull-right electionDetailsCls" >Submit</button>';
					str+='</div>';
				str+='</div>';
			$("#electionTypeValuesId").html(str);
			getElectionYears(type);
		}else{
			$("#electionTypeValuesId").html(noData);
		}
		
		$('#elctionBlockPartysId').multiselect({
			enableFiltering: true,
			includeSelectAllOption: true,
			selectAllText: 'All Parties',
			maxHeight: 300,
			buttonWidth: '100%',
			dropDown: true,
			selectAllName: false,
			allSelectedText: 'All Parties selected'
		});
	});	
}

function getElectionInformationLocationWise(electionVal,type,partyIds,electionSubTypeArr,electionYrVal){
	$('#electionDetailsGraphWiseId').html(spinner);
	$('#electionDetailsTableWiseId').html(spinner);
	/* if(locationLevelId == '8' || locationLevelId == '6'){
		$("#electionDetailsGraphWiseId,#electionDetailsTableWiseId").html(noData);
		return;
	}else{
		$("#electionDetailsGraphWiseId,#electionDetailsTableWiseId").show();
	} */
	/* for(var i in electionVal){
		if(electionVal[i] == 0){
			electionVal=[];
		}
	} */
	
	for(var i in electionYrVal){
		if(electionYrVal[i] == 0){
			electionYrVal=[];
		}
	}
	for(var i in partyIds){
		if(partyIds[i] == 0){
			partyIds=[];
		}
	}
	
	var withAllance="false";
	if($('#allaincePartyFieldId').is(':checked')){
		withAllance="true";
	}
	var jsObj={
			fromDate 	  	:"",
			toDate		  	:"",
			locationId 		:locationLevelId,
			locationValue 	:locationLevelVal,
			electionScopeIds:electionVal,
			partyIds		:partyIds,
			electionYrs     :electionYrVal,
			electionSubTypeArr :electionSubTypeArr,
			withAllaince:withAllance
			
	}
	 $.ajax({
      type : "GET",
      url : "getElectionInformationLocationWiseVoterShareAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	if(result !=null && result.length>0){
			buildElectionInformationLocationWise(result,type);
		}else{
			$('#electionDetailsGraphWiseId').html(noData);
			$('#electionDetailsTableWiseId').html(noData);
		}
	});	
}

function buildElectionInformationLocationWise(result,type){
	
	if(result !=null && result.length>0){
		 var str='';
		
		//str+='<div class="col-sm-12">';
		str+='<div class="table-responsive">';
		//str+='<h5 style="color:#2B908F;" class="pull-right"><b>Vote Share %</b></h5>';	
				str+='<table class="table table-condensed table-hover m_top10  table-bordered table-striped table_font" id="dataTableElecBlock">';
					str+='<thead>';
						str+='<tr>';
							str+='<th rowspan="2">Party</th>';
								for(var j in result[0].list){
									if(result[0].list[j].electionType == "Assembly"){
										str+='<th colspan="2">'+result[0].list[j].electionYear+'   AC</th>';
									}else if(result[0].list[j].electionType == "Parliament"){
										str+='<th colspan="2">'+result[0].list[j].electionYear+'   PC</th>';
									}else{
										str+='<th colspan="2">'+result[0].list[j].electionYear+'   '+result[0].list[j].electionType+'</th>';
									}
									
								}
						str+='</tr>';
						str+='<tr>';
						for(var j in result[0].list){
							str+='<th>Won Count</th>';
							str+='<th>Vote Share&nbsp;%</th>';
						}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
							for(var i in result){
								str+='<tr>';
								if(result[i].name !=null && result[i].name.trim().length>0)
									str+='<td title="'+result[i].name+'"> '+result[i].partyName+'</td>';
								else									
									str+='<td> '+result[i].partyName+'</td>';
									for(var j in result[i].list){
										if(result[i].list[j].wonSeatsCount !=null && result[i].list[j].wonSeatsCount>0){
											str+='<td>'+result[i].list[j].wonSeatsCount+'  </td>';
										}else{
											str+='<td> - </td>';
										}
										if(result[i].list[j].locationName !=null && result[i].list[j].locationName>0){
											str+='<td><small style="color:#2B908F;"><b>'+result[i].list[j].locationName+' %</b></small></td>';
										}else{
											str+='<td> - </td>';
										}
									}
								str+='</tr>';
							}
					str+='</tbody>';
				str+='</table>';
			//str+='</div>';
		str+='</div>';
		$("#electionDetailsTableWiseId").html(str);
		$("#dataTableElecBlock").dataTable({
			"iDisplayLength": 20,
			 "aaSorting": [[ 2, "desc" ]], 
			"aLengthMenu": [[20, 30, 50, -1], [20, 30, 50, "All"]]
		});
		var mainDataArr=[];
		var electionYearArr = [];
		
			for(var i in result){
				var wonSeatsCountArr=[];
				var partiesName='';
				for(var j in result[i].list){
					if((result[i].list[j].wonSeatsCount !=null && result[i].list[j].wonSeatsCount>0) || (result[i].list[j].locationName !=null && result[i].list[j].locationName>0)){
						partiesName = result[i].partyName;
					}
					
					electionYearArr.push(result[i].list[j].electionYear+'  '+result[i].list[j].electionType);
					if(type == "wonSeat"){
						if(result[i].list[j].wonSeatsCount !=null && result[i].list[j].wonSeatsCount>0){
							wonSeatsCountArr.push(parseFloat(result[i].list[j].wonSeatsCount));
						}
					}else{
						if(result[i].list[j].locationName !=null && result[i].list[j].locationName>0){
							wonSeatsCountArr.push(parseFloat(result[i].list[j].locationName));
						}
						
					}
					
				}
				var obj ={
						name: partiesName,
						data: wonSeatsCountArr,
						color:globalColorPartyNames[result[i].partyName.trim()]
					}
					mainDataArr.push(obj)
			}
			
		$(".electionDetailsGraphHeight").css("height","500px !important;");
		$('#electionDetailsGraphWiseId').highcharts({
			chart: {
				type: 'area'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				categories: electionYearArr,
				tickmarkPlacement: 'on',
				title: {
					enabled: false
				}
			},
			yAxis: {
				title: {
					text: ''
				}
			},
			tooltip: {
				formatter: function () {
					var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
						if(type == "wonSeat"){
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
							this.y+" Won"
						}else{
							if(this.y == "undefined" || this.y == 0 || this.y === undefined){
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								0+" %"
							}else{
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								this.y+" %"
							}
							
						}  
						
					});

					return s;
				},
				shared: true
			},
			plotOptions: {
				area: {
					stacking: 'normal',
					lineColor: '#666666',
					lineWidth: 1,
					marker: {
						lineWidth: 1,
						lineColor: '#666666'
					}
				}
			},
			series: mainDataArr
		});
	}
}

function getPartyWiseMPandMLACandidatesCounts(){
	$("#statelevelMPMLAId").html(spinner);
	var jsObj={
       electionIds    		:[258,260],
       loctionValue   		:1,
       loactionTypeId   	:2,
       electionScopeIds 	:[2,1]
    }
   $.ajax({
      type : "POST",
      url : "getPartyWiseMPandMLACandidatesCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			return buildCandidateAndPartyForStateLevel(result);
		}else{
			 $("#statelevelMPMLAId").html(noData);
		}
		
  }); 
  
  function buildCandidateAndPartyForStateLevel(result){
	  
	  var str='';
	  str+='<div class="col-sm-12">';
		str+='<div class="block">';
			str+='<table class="table table-block-responsive">';
				str+='<tr>';
					str+='<td>';
					str+='<div class="media">';
							str+='<div class="media-left">';
								str+='<h4 class="mpMlaRoundedCss">MP</h4>';
							str+='</div>';
							str+='<div class="media-body media_width">';
								str+='<p class="m_top10 f-12">Member Of<br/> Parliament</p>';
							str+='</div>';
						str+='</div>';
					str+='</td>';
					if(result.subList !=null && result.subList.length>0){
						for(var i in result.subList){
							str+='<td>';
								str+='<div class="media">';
								str+='<div class="media-left">';//ara
									str+='<h4 class="mpMlaRoundedCss popUpDetailsClickCls" attr_type="MPMLA" attr_election_scopeId="1" attr_partyId="'+result.subList[i].partyId+'" attr_electionId="'+result.subList[i].electionId+'">'+result.subList[i].condidateCount+'</h4>';
								str+='</div>';
									str+='<div class="media-body media_width">';
										if(result.subList[i].party == "BJP"){
											str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList[i].party+'.png"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/> '+result.subList[i].party+'</p><p class="f-12">'+result.subList[i].candidateName+'</p>';
										}else if(result.subList[i].party == "OTHERS"){
											str+='<p class="m_top10">'+result.subList[i].party+'</p>';
										}else{
											if(result.subList[i].party == "TDP" || result.subList[i].party == "YSRC"){
												str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList[i].party+'_01.PNG"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/> '+result.subList[i].party+'</p><p class="f-12">'+result.subList[i].candidateName+'</p>';
											}else{
												str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList[i].party+'.PNG"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/> '+result.subList[i].party+'</p><p class="f-12">'+result.subList[i].candidateName+'</p>';
											}
											
										}
										//str+='<p class="f-10">Telugu Desam Party</p>';
									str+='</div>';
								str+='</div>';
							str+='</td>';
						}
						
					}
				str+='</tr>';
				str+='<tr>';
					str+='<td>';
						str+='<div class="media">';
							str+='<div class="media-left">';
								str+='<h4 class="mpMlaRoundedCss">MLA</h4>';
							str+='</div>';
							str+='<div class="media-body media_width">';
								str+='<p class="m_top10 f-12">Member Of <br/>Legislative Assembly</p>';
							str+='</div>';
						str+='</div>';
					str+='</td>';
					
					if(result.subList2 !=null && result.subList2.length>0){
						for(var i in result.subList2){
							str+='<td>';
								str+='<div class="media">';
								str+='<div class="media-left">';
									str+='<h4 class="mpMlaRoundedCss popUpDetailsClickCls"attr_election_scopeId="2" attr_type="MPMLA" attr_election_scopeId="'+result.subList2[i].scopeId+'" attr_partyId="'+result.subList2[i].partyId+'" attr_electionId="'+result.subList2[i].electionId+'">'+result.subList2[i].condidateCount+'</h4>';
								str+='</div>';
									str+='<div class="media-body media_width">';
									if(result.subList2[i].party == "BJP"){
										str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList2[i].party+'.png"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/> '+result.subList2[i].party+'</p><p class="f-12">'+result.subList2[i].candidateName+'</p>';
									}else if(result.subList2[i].party == "OTHERS"){
										str+='<p class="m_top10">'+result.subList2[i].party+'</p>';
									}else{
										if(result.subList2[i].party == "TDP" || result.subList2[i].party == "YSRC"){
											str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList2[i].party+'_01.PNG"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/> '+result.subList2[i].party+'</p><p class="f-12">'+result.subList2[i].candidateName+'</p>';
										}else{
											str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList2[i].party+'.PNG"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/> '+result.subList2[i].party+'</p><p class="f-12">'+result.subList2[i].candidateName+'</p>';
										}
										
									}
										
										//str+='<p class="f-10">Telugu Desam Party</p>';
									str+='</div>';
								str+='</div>';
							str+='</td>';
						}
						
					}
				str+='</tr>';
			str+='</table>';
		str+='</div>';
	  str+='</div>';
	  $("#statelevelMPMLAId").html(str);
  }
}

function getElectionYears(type){
	$('#elctionYearsBlockId').html('');
	$('#electionTypeSpinnerId').show();
	var i = 0;	
	electionTypeVal = [];
	$(".electionTypeWiseCls").each(function(){
		if ($(this).is(':checked') && $(this).val() != 0){
			electionTypeVal[i++] = $(this).val();
		}
	});
	var jsObj={
		electionScopeIds:electionTypeVal,
		electionSubTypeArr:electionSubTypeArr
	}
    $.ajax({   
      type:'POST',
      url:'getElectionYearAndPartiesAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		 $('#electionTypeSpinnerId').hide();
		if(result !=null && result.length>0){
				var str='';
				for(var i in result){
					str+='<option value="'+result[i].electionYear+'" selected>'+result[i].electionYear+'</option>';
				} 
				$("#elctionYearsBlockId").multiselect("destroy");
				$('#elctionYearsBlockId').html(str);
				$('#elctionYearsBlockId').multiselect({
					enableFiltering: true,
					includeSelectAllOption: true,
					selectAllText: 'All Election Years',
					maxHeight: 300,
					buttonWidth: '100%',
					dropDown: true,
					selectAllNumber: true,
					allSelectedText: 'All Election Years selected'
				});
			}
			if(type == "onload"){
				electionYrVal=[];
				electionYrVal = $("#elctionYearsBlockId").val();
				
				var partyIds = [872,362,1117,72,269,265,163,1887];
				getElectionInformationLocationWise(electionTypeVal,"voteShare",partyIds,electionSubTypeArr,electionYrVal);
			}
			
	    });
  }
  function getCommitteeCount(){
	
	var jsObj={
			constituencyId 	  	:232,
			levelIds		  	:locationLevelId,
			levelValues		 	:userAccessLevelValuesArray,
			basicCommittesIds 	:[1],
			cmiteEnrlmntYearIds : [1]
		}
	 $.ajax({
      type : "POST",
      url : "getCommitteeCountDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		
	});	
}
  function getNominatedPositionWiseCandidates(boardLevelId,name){
	  $("#openPostDetailsModalDivId").html(spinner);
	var jsObj={
      "fromDateStr" 		:"",
      "toDateStr"			:"",
      "locationValuesArr"	:userAccessLevelValuesArray,
      "locationTypeId"		:locationLevelId,
      "year"				:"",
      "boardLevelId"		:boardLevelId,
	  startIndex			:0,
	  endIndex				:5000
    }
    $.ajax({   
      type:'GET',
      url:'getNominatedPositionWiseCandidatesAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
      if(result !=null && result.length>0){
		  return buildNominatedPositionWiseCandidates(result);
	  }
    });
	
	function buildNominatedPositionWiseCandidates(result){
		var str='';
		var str='';
		str+='<div class="table-responsive">';
			str+='<table class="table table-condensed tableStyledGoIssued" id="dataTablePositionLevelId">';
				str+='<thead class="bg-E9">';
					str+='<tr>';
						str+='<th class="">Department</th>';
						str+='<th class="">Board/ Corporation</th>';
						str+='<th class="">Position Level</th>';
						str+='<th>Image</th>';
						str+='<th class="">Name</th>';
						str+='<th class="">Status</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							str+='<td>'+result[i].department+'</td>';
							str+='<td>'+result[i].board+'</td>';
							str+='<td>'+result[i].position+'</td>';
							str+='<td><img src="https://mytdp.com/images/cadre_images/'+result[i].image+'" class="img-border" alt="profile" onerror="setDefaultImage(this);" style="width:50px;height:50px;"/></td>';
							str+='<td>'+result[i].candidateName+'</td>';
							str+='<td>'+result[i].status+'</td>';
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		$("#openPostDetailsModalDivId").html(str);
			$("#dataTablePositionLevelId").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}
  }
  
function getLevelWisePostsOverView(){
	
	$("#levelWiseNominatedPosts").html(spinner);
	var jsObj={
			"fromDateStr" : "",
			"toDateStr":"",
			"locationTypeId":locationLevelId,
			"locationValuesArr":userAccessLevelValuesArray,
			"boardLevelId":globalboardLevelId
		}
	 $.ajax({
      type : "POST",
      url : "getLevelWisePostsOverViewAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildLevelWisePostsOverView(result);
		}else{
			$("#levelWiseNominatedPosts").html("NO DATA AVAILABLE ");
		}
	});	
	
	function buildLevelWisePostsOverView(result){
		var str='';
		str+='<div class="levelWiseNominatedCss">';
			str+='<div class="">';
				var colorsArr = ['#63CCB9','#994100','#ACCC63','#63CCB9','#994100','#ACCC63','#63CCB9','#994100','#ACCC63']
				str+='<ul class="list-inline m_0 nominatedLevelWisePostsSlick">';
				for(var i in result){
					str+='<li class="col-sm-4 m_top20">';
						str+='<h5>'+result[i].board+' Level Posts</h5>';
						str+='<table class="table m_top10 tableborderNomiPost">';
							str+='<tr>';
								str+='<td class="text-right">';
									str+='<p class="f-12">Received Applications</p>';
								str+='</td>';
								str+='<td>';
									str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result[i].totalRecePer+' %">';
										  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].recivedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result[i].totalRecePer+'%;background-color:'+colorsArr[i]+'">';
										  str+='</div>';
									str+='</div>';
								str+='</td>';
								str+='<td>';
									str+='<span>'+result[i].recivedCount+'</span>';
								str+='</td>';
								str+='</tr>';
							str+='<tr>';
								str+='<td class="text-right">';
									str+='<p class="f-12">Total Posts</p>';
								str+='</td>';
								str+='<td>';
									str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result[i].totalPer+' %">';
										str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].totalPosts+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result[i].totalPer+'%;background-color:'+colorsArr[i]+'">';
										str+='</div>';
									str+='</div>';
								str+='</td>';
								str+='<td>';
									str+='<span>'+result[i].totalPosts+'</span>';
								str+='</td>';
							str+='</tr>';
							str+='<tr>';
								str+='<td class="text-right">';
									str+='<p class="f-12">G.O Issued</p>';
								str+='</td>';
								str+='<td>';
									str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result[i].goIssuedPer+' %">';
										  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].goIsuuedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result[i].goIssuedPer+'%;background-color:'+colorsArr[i]+'">';
										  str+='</div>';
									str+='</div>';
								str+='</td>';
								str+='<td>';
								if(result[i].goIsuuedCount !=null && result[i].goIsuuedCount>0){
									str+='<span class="popUpDetailsClickCls" attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="goIssued" attr_department_name="'+result[i].board+' Level" attr_department_id="0" attr_board_statusIds="0">'+result[i].goIsuuedCount+'</span>';
								}else{
									str+='<span class="" attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="goIssued" attr_department_name="'+result[i].board+' Level" attr_department_id="0" attr_board_statusIds="0">'+result[i].goIsuuedCount+'</span>';
								}
									
								str+='</td>';
							str+='</tr>';
							str+='<tr>';
								str+='<td class="text-right">';
									str+='<p class="f-12">Open Posts</p>';
								str+='</td>';
								str+='<td>';
									str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result[i].openPostPer+' %">';
										  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].openCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result[i].openPostPer+'%;background-color:'+colorsArr[i]+'">';
										  str+='</div>';
									str+='</div>';
								str+='</td>';
								str+='<td>';
								if(result[i].openCount !=null && result[i].openCount>0){
									str+='<span class="popUpDetailsClickCls" attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="open" attr_department_name="'+result[i].board+' Level" attr_department_id="0">'+result[i].openCount+'</span>';
								}else{
									str+='<span class="" attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="open" attr_department_name="'+result[i].board+' Level" attr_department_id="0">'+result[i].openCount+'</span>';
								}
									
								str+='</td>';
							str+='</tr>';
						str+='</table>';
					str+='</li>';
				}
				str+='</div>';
			
		str+='</div>';
		$("#levelWiseNominatedPosts").html(str);
		$('.nominatedLevelWisePostsSlick').slick({
			slide: 'li',
			slidesToShow: 3,
			slidesToScroll: 1,
			infinite: false,
			swipe:false,
			touchMove:false,
			variableWidth: false,
			responsive: [
				{
					breakpoint: 1024,
					settings: {
						slidesToShow: 3,
						slidesToScroll: 3,
					}
				},
				{
					breakpoint: 600,
					settings: {
						slidesToShow: 2,
						slidesToScroll: 2
					}
				},
				{
					breakpoint: 480,
					settings: {
						slidesToShow: 1,
						slidesToScroll: 1
					}
				}
			]
		});
		$('.progressCustom').tooltip()
	}
  }
function getDepartmentWisePostAndApplicationDetails(deptId,boardLevelId,type){
	if(type == "open"){
		$("#openPostDetailsModalDivId").html(spinner);
	}
	
	if(boardLevelId == 2){
		boardLevelId = 2;
	}
	var jsObj={
	  "fromDateStr" 		:"",
      "toDateStr"			:"",
      "locationValuesArr"	:userAccessLevelValuesArray,
      "locationTypeId"		:locationLevelId,
      "year"				:"",
      "boardLevelId"		:boardLevelId,
	  deptId				:parseInt(deptId)
	  }
    $.ajax({   
      type:'GET',
      url:'getDepartmentWisePostAndApplicationDetailsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildDepartmentWisePostAndApplicationDetails(result,type);
		}
    });
	
	function buildDepartmentWisePostAndApplicationDetails(result,type){
		
		var str='';
		
		str+='<div class="table-responsive">';
			if(type == "open"){
				str+='<table class="table table-condensed tableOpenPostCss" id="dataTableOpenPostId">';
			}else{
				str+='<table class="table table-condensed tableOpenPostCss" id="dataTabledeptPostId">';
			}
			
				str+='<thead>';
					str+='<tr>';
						if(type == "open"){
							str+='<th rowspan="2" class="openPostDeptColor" style="vertical-align:middle;">Department</th>';
						}else{
							str+='<th rowspan="2" class="openPostDeptColor" style="vertical-align:middle;">Board/ Corporation</th>';
						}
						
						str+='<th colspan="3" class="openPostColor text-center">Posts</th>';
						str+='<th colspan="3" class="openPostAppColor text-center">Applications</th>';
					str+='</tr>';
					str+='<tr>';
						str+='<th class="openPostColor text-center">Total</th>';
						str+='<th class="openPostColor text-center">Finalized/ G.O Issueed</th>';
						str+='<th class="openPostColor text-center">Open</th>';
						
						str+='<th class="openPostAppColor text-center">Recieved</th>';
						str+='<th class="openPostAppColor text-center">Shortlisted</th>';
						str+='<th class="openPostAppColor text-center">Ready for Final Review</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							if(type =="open"){
								str+='<td attr_department_name="'+result[i].name+'" attr_department_id="'+result[i].id+'" attr_boardLevelId="'+globalboardLevelId+'" class="popUpDetailsClickCls" attr_type="department" style="color: #337ab7;font-weight:normaltext-decoration:none;">'+result[i].name+'</td>';
							}else{
								str+='<td>'+result[i].name+'</td>';
							}
							
							
							if(result[i].totalCount !=null && result[i].totalCount>0){
								str+='<td class="openPostColor text-center">'+result[i].totalCount+'</td>';
							}else{
								str+='<td class="openPostColor text-center"> - </td>';
							}
							if(result[i].finalOrGOCnt !=null && result[i].finalOrGOCnt>0){
								str+='<td class="openPostColor text-center">'+result[i].finalOrGOCnt+'</td>';
							}else{
								str+='<td class="openPostColor text-center"> - </td>';
							}
							
							if(result[i].openCnt !=null && result[i].openCnt>0){
								str+='<td class="openPostColor text-center">'+result[i].openCnt+'</td>';
							}else{
								str+='<td class="openPostColor text-center"> - </td>';
							}
							
							if(result[i].receivedCnt !=null && result[i].receivedCnt>0){
								str+='<td class="openPostAppColor text-center">'+result[i].receivedCnt+'</td>';
							}else{
								str+='<td class="openPostAppColor text-center"> - </td>';
							}
							if(result[i].shorlistedCnt !=null && result[i].shorlistedCnt>0){
								str+='<td class="openPostAppColor text-center">'+result[i].shorlistedCnt+'</td>';
							}else{
								str+='<td class="openPostAppColor text-center"> - </td>';
							}
							
							if(result[i].readyToFinalCnt !=null && result[i].readyToFinalCnt>0){
								str+='<td class="openPostAppColor text-center">'+result[i].readyToFinalCnt+'</td>';
							}else{
								str+='<td class="openPostAppColor text-center"> - </td>';
							}
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		if(type == "open"){
			$("#openPostDetailsModalDivId").html(str);
				$("#dataTableOpenPostId").dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}else{
			$("#departmentDetailsModalDivId").html(str);
			$("#dataTabledeptPostId").dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}
		
	}
  }
  
  function getLevelWiseGoIssuedPostions(boardLevelId,statusId,startIndex,endIndex){
	  $("#openPostDetailsModalDivId").html(spinner)
	  
	  var statusIds=[];
	  if(statusId == 0){
		  statusIds.push(3,4)
	  }
	 var totalPosCount = 0; 
	var jsObj={
      fromDateStr 		:"",
      toDateStr			:"",
      locationValuesArr	:userAccessLevelValuesArray,
      locationTypeId	:locationLevelId,
      year				:"",
      boardLevelId		:boardLevelId, 
	  statusIds			:statusIds, // 3-complered 4 goIsuued
	  startIndex:startIndex,
	  endIndex:endIndex
     
    }
    $.ajax({   
      type:'GET',
      url:'getLevelWiseGoIssuedPostionsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return LevelWiseGoIssuedPostions(result,totalPosCount,startIndex,boardLevelId,statusId);
		}else{
			$(".paginationId").html("");
			$("#openPostDetailsModalDivId").html("No Data Available");
		}
		
    });
	function LevelWiseGoIssuedPostions(result,totalPosCount,startIndex,boardLevelId,statusId){
		if(startIndex == 0){
			totalPosCount=result[0].postCount;
		}
		var str='';
		str+='<div class="table-responsive">';
			str+='<table class="table table-condensed tableStyledGoIssued" id="dataTablegoIssuedPostId">';
				str+='<thead class="bg-E9">';
					str+='<tr>';
						str+='<th class="text-center">Department</th>';
						str+='<th class="text-center">Board/ Corporation</th>';
						str+='<th class="text-center">Position Level</th>';
						str+='<th class="text-center">Image</th>';
						str+='<th class="text-center">Name</th>';
						str+='<th class="text-center">Gender</th>';
						str+='<th class="text-center">Caste Category</th>';
						str+='<th class="text-center">G.O Validity</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							str+='<td>'+result[i].department+'</td>';
							str+='<td>'+result[i].board+'</td>';
							str+='<td>'+result[i].position+'</td>';
							str+='<td><img src="https://mytdp.com/images/cadre_images/'+result[i].image+'" class="img-border" alt="profile" onerror="setDefaultImage(this);" style="width:50px;height:50px;"/></td>';
							str+='<td>'+result[i].candidateName+'</td>';
							str+='<td>'+result[i].gender+'</td>';
							str+='<td>'+result[i].casteCategory+'</td>';
							if(result[i].date !=null && result[i].date.length>0){
								str+='<td>'+result[i].date+'</td>';
							}else{
								str+='<td> - </td>';
							}
							
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		if(startIndex == 0 && totalPosCount > 10){
			$(".paginationId").pagination({
				items: totalPosCount,
				itemsOnPage: 10,
				cssStyle: 'light-theme',
				hrefTextPrefix: '#pages-',
				onPageClick: function(pageNumber) { 
					var num=(pageNumber-1)*10;
					getLevelWiseGoIssuedPostions(boardLevelId,statusId,num,10)
				}
				
			});
		}
		$("#openPostDetailsModalDivId").html(str);		
	}
  }
  function getDesignationWiseAlertsOverview(defaultAlertCategoryIds){
	$("#alertsDeignBlockDivId").html(spinner);
	var jsObj={
			fromDateStr 	  	:"",
			toDateStr		  	:"",
			locationValuesArr 	:userAccessLevelValuesArray,
			alertTypeIdsStr 	:defaultAlertCategoryIds,
			locationTypeId		:locationLevelId,
			year  				:""

		}
	 $.ajax({
      type : "POST",
      url : "getDesignationWiseAlertsOverviewAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length >0){
		return buildDesignationWiseAlertsOverview(result,defaultAlertCategoryIds);
		}else {
			$("#alertsDeignBlockDivId").html("No Data Available");
		}
	});	
	
		function buildDesignationWiseAlertsOverview(result,defaultAlertCategoryIds){
			var str='';
			
			str+='<h4>Designation wise Alerts Overview</h4>';
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table table-condensed tableAlignment table-bordered" id="dataTableDesigAlerts">';
					str+='<thead>';
					
						str+='<tr>';
							str+='<th rowspan="2">Designation</th>';
							str+='<th rowspan="2">Total</th>';
							str+='<th colspan="8" class="text-center">Involved</th>';
							str+='<th colspan="8" class="text-center">Assigned</th>';
						str+='</tr>';
						str+='<tr>';
							str+='<th>Total</th>';
							str+='<th>%</th>';
							str+='<th>Inprogress</th>';
							str+='<th>%</th>';
							str+='<th>Completed</th>';
							str+='<th>%</th>';
							str+='<th>Others</th>';
							str+='<th>%</th>';
							
							str+='<th>Total</th>';
							str+='<th>%</th>';
							str+='<th>Inprogress</th>';
							str+='<th>%</th>';
							str+='<th>Completed</th>';
							str+='<th>%</th>';
							str+='<th>Others</th>';
							str+='<th>%</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						
						if(result != null && result.length >0){
							for(var i in result){
								str+='<tr>';
								str+='<td id='+result[i].id+'>'+result[i].status+'</td>';
								if(result[i].totalAlertCount !=null && result[i].totalAlertCount>0){
									str+='<td class="popUpDetailsClickCls" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="'+result[i].id+'" attr_alertCategeryId="" attr_type="alert" attr_alert_type="total" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result[i].status+'" attr_total_count="'+result[i].totalAlertCount+'">'+result[i].totalAlertCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result[i].count !=null && result[i].count>0){
									str+='<td class="popUpDetailsClickCls text-center" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="'+result[i].id+'" attr_alertCategeryId="" attr_type="alert" attr_alert_type="involved" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result[i].status+'" attr_total_count="'+result[i].count+'">'+result[i].count+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								str+='<td>'+result[i].percentage1+'%</td>';
								for(var j in result[i].subList1){
									if(result[i].subList1[j].count !=null && result[i].subList1[j].count>0){
										if(result[i].subList1[j].status == "OTHERS"){
											str+='<td class="popUpDetailsClickCls text-center" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="'+result[i].id+'" attr_alertCategeryId="" attr_type="alert" attr_alert_type="involved" attr_otherCategory="candidateInvolvedOthers" attr_impactIdsArr="" attr_status_name="'+result[i].status+' ('+result[i].subList1[j].status+')" attr_total_count="'+result[i].subList1[j].count+'">'+result[i].subList1[j].count+'</td>';
										}else{
											str+='<td class="popUpDetailsClickCls text-center" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result[i].subList1[j].id+'" attr_designationId="'+result[i].id+'" attr_alertCategeryId="" attr_type="alert" attr_alert_type="involved" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result[i].status+' ('+result[i].subList1[j].status+')" attr_total_count="'+result[i].subList1[j].count+'">'+result[i].subList1[j].count+'</td>';
										}
										
									}else{
										str+='<td> - </td>';
									}
									
									str+='<td>'+result[i].subList1[j].percentage+'%</td>';
								}
								
								if(result[i].alertCount !=null && result[i].alertCount>0){
									str+='<td class="popUpDetailsClickCls text-center" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="'+result[i].id+'" attr_alertCategeryId="" attr_type="alert" attr_alert_type="assigned" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result[i].status+'" attr_total_count="'+result[i].alertCount+'">'+result[i].alertCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								str+='<td>'+result[i].percentage+'</td>';
								for(var k in result[i].subList){
									if(result[i].subList[k].count !=null && result[i].subList[k].count>0){
										if(result[i].subList[k].status =="OTHERS"){
											str+='<td class="popUpDetailsClickCls text-center" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="" attr_designationId="'+result[i].id+'" attr_alertCategeryId="" attr_type="alert" attr_alert_type="assigned" attr_otherCategory="candidateAssignedOthers" attr_impactIdsArr="" attr_status_name="'+result[i].status+' ('+result[i].subList[k].status+')" attr_total_count="'+result[i].subList[k].count+'">'+result[i].subList[k].count+'</td>';
										}else{
											str+='<td class="popUpDetailsClickCls text-center" attr_alertTypeIdsStr="'+defaultAlertCategoryIds+'" attr_statusIds="'+result[i].subList[k].id+'" attr_designationId="'+result[i].id+'" attr_alertCategeryId="" attr_type="alert" attr_alert_type="assigned" attr_otherCategory="" attr_impactIdsArr="" attr_status_name="'+result[i].status+' ('+result[i].subList[k].status+')" attr_total_count="'+result[i].subList[k].count+'">'+result[i].subList[k].count+'</td>';
										}
										
									}else{
										str+='<td> - </td>';
									}
									
									str+='<td>'+result[i].subList[k].percentage+'%</td>';
								}
								str+='</tr>';
							}
							
						}
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			$("#alertsDeignBlockDivId").html(str);
			$("#dataTableDesigAlerts").dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
		}
  }
function getAlertOverviewClick(alertTypeIds,statusIds,designationId,alertCategeryId,alert_type,otherCategory,impactIds,status_name,total_count){
		$("#openPostDetailsModalDivId").html(spinner);
		var statusIdsArr=[];
		var alertCategeryIdsArr=[];
		var impactIdsArr=[];
		var impactIdsArr=[];
		var alertTypeIdsStr=[];
		
		if(statusIds == "" || statusIds == 0){
			statusIdsArr=[];
		}else{
			statusIdsArr.push(statusIds)
		}
		
		if(alertCategeryId == "" || alertCategeryId == 0){
			alertCategeryIdsArr=[];
		}else{
			alertCategeryIdsArr.push(alertCategeryId)
		}
		
		if(impactIds == "" || impactIds == 0){
			impactIdsArr=[];
		}else{
			impactIdsArr.push(impactIds)
		}
		var myStr = alertTypeIds;
        var strArray = myStr.split(",");
        for(var i = 0; i < strArray.length; i++){
           alertTypeIdsStr.push(strArray[i]); 
        }
		
		var jsObj={
				fromDateStr 	  	:"",
				toDateStr		  	:"",
				locationValuesArr 	:userAccessLevelValuesArray,
				alertTypeIdsStr 	:alertTypeIdsStr,
				locationTypeId		:locationLevelId,
				year  				:"",
				statusIdsArr 		:statusIdsArr,
				designationId 		:designationId,
				alertCategeryIdsArr :alertCategeryIdsArr,
				type 				:alert_type,//involved,assigned,categoryOthers,impactOthers
				otherCategory		:otherCategory,//categoryOthers,impactOthers,candidateAssignedOthers,candidateInvolvedOthers,
				impactIdsArr 		:impactIdsArr
			}
		 $.ajax({
	      type : "POST",
	      url : "getAlertOverviewClickAction.action",
	      dataType : 'json',
	      data : {task :JSON.stringify(jsObj)}
	    }).done(function(result){
			if(result != null && result.length > 0){
				return buildAlertDtls(result);   
			}else{
				$("#openPostDetailsModalDivId").html(noData);
			}
		});
	function buildAlertDtls(result){
				var str='';
				str+='<div class="table-responsive">';
				if($(window).width() < 800)
				{
					str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed tableAlignment" id="alertDtlsTabId">';   
				}else{
					str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed tableAlignment" id="alertDtlsTabId">'; 
				}  
				str+='<thead>';
					str+='<tr>';
					 str+='<th>Alert Source</th>';
					 str+='<th>Title</th>';
					 str+='<th>Created Date</th>';
					 str+='<th>Last Updated Date</th>';
					 str+='<th>Current Status</th>'	 
					 str+='<th>LAG Days</th>';
					 str+='<th>Alert Impact Level</th>';
					 str+='<th>Location</th>';
					 str+='</tr>';
				 str+='</thead>';
				 str+='<tbody>';
				 for(var i in result){
					str+='<tr>';
					if(result[i].source != null && result[i].source.length > 0){
						str+='<td>'+result[i].source+'</td>';         
					}else{
						str+='<td> - </td>';     
					}
					if(result[i].title != null && result[i].title.length > 0){
						str+='<td class="descAlertCls" style="cursor:pointer;" attr_alert_status="'+result[i].status+'" attr_alert_id="'+result[i].id+'"><strong><u>'+result[i].title+'</u></strong></td>';         
					}else{
						str+='<td> - </td>';     
					}
					if(result[i].createdDate != null && result[i].createdDate.length > 0){
						str+='<td>'+result[i].createdDate+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].updatedDate != null && result[i].updatedDate.length > 0){
						str+='<td>'+result[i].updatedDate+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].status != null && result[i].status.length > 0){
						str+='<td>'+result[i].status+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].interval != null){
						str+='<td>'+(parseInt(result[i].interval)-parseInt(1))+'</td>';            
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].alertLevel != null && result[i].alertLevel.length > 0){
						str+='<td>'+result[i].alertLevel+'</td>';               
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].location != null && result[i].location.length > 0){
						str+='<td>'+result[i].location+'</td>';      
					}else{
						str+='<td> - </td>';        
					}
					//str+='<td><button type="button" class="btn btn-default btn-success descAlertCls" attr_alert_id="'+result[i].id+'">Alert Details</button></td>';  
					
					str+='</tr>';
					}
					 str+='</tbody>';
					 str+='</table>';
					 str+='</div>';
				 $("#openPostDetailsModalDivId").html(str);          
				  $("#alertDtlsTabId").dataTable({  
						 "aaSorting": [[ 4, "desc" ]], 
						"iDisplayLength" : 10,
						"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
					 }); 
			}
	  
}
function getLocationWiseMeetingsCountDetails(partyMeetingMainTypeId){
	if(partyMeetingMainTypeId == '1' || partyMeetingMainTypeId == 1)
	{
		$("#committeeMeetingsBlockId").html(spinner).show();
	}else if(partyMeetingMainTypeId == '2' || partyMeetingMainTypeId == 2)
	{
		$("#stateMeetingsBlockId").html(spinner).show();
	}else if(partyMeetingMainTypeId == '3' || partyMeetingMainTypeId == 3)
	{
		$("#specialMeetingsBlockId").html(spinner).show();
	}
	if(locationLevelId == 2)
	{
		$("#stateMeetingsBlockId").show();
		$("#committeeMeetingsBLock").addClass("col-sm-8").removeClass("col-sm-12");
	}else{
		$("#stateMeetingsBlockId").hide();
	}
	jsObj={
		locationTypeId			:locationLevelId,
		locationValues			:userAccessLevelValuesArray,
		fromDate 				:customStartMeetingsDate,
		toDate 					:customEndMeetingsDate,
		partyMeetingMainTypeId	:partyMeetingMainTypeId
	}
	$.ajax({
		type : "GET",
		url : "getLocationWiseMeetingsCountDetailsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result.levelList != null && result.levelList.length > 0)
		{
			return buildGraph(result);
		}else{
			if(partyMeetingMainTypeId == '1' || partyMeetingMainTypeId == 1)
			{
				$("#committeeMeetingsBlockId").hide();
			}else if(partyMeetingMainTypeId == '2' || partyMeetingMainTypeId == 2)
			{
				$("#stateMeetingsBlockId").hide();
				$("#committeeMeetingsBLock").addClass("col-sm-12").removeClass("col-sm-8");
			}else if(partyMeetingMainTypeId == '3' || partyMeetingMainTypeId == 3)
			{
				$("#specialMeetingsBlockId").hide();
			}
		}
	});
	function buildGraph(result)
	{
		var str='';
		if(partyMeetingMainTypeId == '1' || partyMeetingMainTypeId == 1)
		{
			str+='<div class="row">';
				for(var i in result.levelList)
				{
					str+='<div class="col-sm-6">';
						str+='<div class="block m_top10">';
							str+='<h4 class="panel-title">'+result.levelList[i].name+'</h4>';
							if(result.levelList[i].name == "DISTRICT")
							{
								str+='<p>Every month : 22nd/23rd/24th</p>';
							}else if(result.levelList[i].name == "CONSTITUENCY")
							{
								str+='<p>Every month : 18th/19th/20th</p>';
							}else if(result.levelList[i].name == "MANDAL/DIVISION/TOWN")
							{
								str+='<p>Every month : 14th/15th/16th</p>';
							}else if(result.levelList[i].name == "VILLAGE/WARD")
							{
								str+='<p>Every month : 9th/10th/11th</p>';
							}
							str+='<div style="height:150px;" id="meetingsGraphBlock'+result.levelList[i].name.substr(0,6)+'Id"></div>';
							if(result.levelList[i].name == "DISTRICT"){
								str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-24 popUpDetailsClickCls" attr_type="meeting_type" style="margin-top:-16px;cursor:pointer;text-decoration:none;"></i>';
							}else{
								str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-24 popUpDetailsClickCls" attr_type="meeting_type_level" attr_meeting_levelId="'+result.levelList[i].id+'" attr_partyMeetingTypeId="'+result.levelList[i].partyMeetingId+'" style="margin-top:-16px;cursor:pointer;text-decoration:none;"></i>';
								
							}
							
						str+='</div>';
					str+='</div>';
				}
			str+='</div>';
			$("#committeeMeetingsBlockId").html(str);
			var colorsArr = ['#D7AD06','#63CCB9','#994100','#AFCE69'];
			for(var i in result.levelList)
			{
				var mainArr = [{"y":result.levelList[i].totalMeetings,"extra":result.levelList[i].totalPerc},{"y":result.levelList[i].conductedMeetings,"extra":result.levelList[i].yesPerc},{"y":result.levelList[i].notConductedMeetings,"extra":result.levelList[i].noPerc},{"y":result.levelList[i].maybeeMeetings,"extra":result.levelList[i].mayBePerc},{"y":result.levelList[i].notUpdatedCount,"extra":result.levelList[i].notUpdatePerc}];
				var	count = [{"y":result.levelList[i].totalMeetings,color:"#D3D3D3"},{"y":result.levelList[i].totalMeetings,color:"#D3D3D3"},{"y":result.levelList[i].totalMeetings,color:"#D3D3D3"},{"y":result.levelList[i].totalMeetings,color:"#D3D3D3"},{"y":result.levelList[i].totalMeetings,color:"#D3D3D3"}];
				
				var categoriesArr = [];
					categoriesArr.push('Total','Yes','No','May Be','Not Updated')
				
				$("#meetingsGraphBlock"+result.levelList[i].name.substr(0,6)+"Id").highcharts({
					colors : [colorsArr[i]],
					chart: {
						type: 'bar'
					},
					title: {
						text: ''
					},
				   
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: categoriesArr,
						type: 'category',
						
					},
					yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						title: {
							text: ''
						},
						labels: {
							enabled: false,
						},
						stackLabels: {
							enabled: true,
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							},
							formatter: function() {
								//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
								//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
								return (this.total);
							},
						}
					},
					tooltip: {
						formatter: function () {
							var s = '<b>' + this.x + '</b>';

								$.each(this.points, function () {
								if(this.series.name != "Series 1")  
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									this.y/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
							});
							return s;
						},
						shared: true
					},
					legend: {   
							enabled: false,				
						},				
						plotOptions: {
							bar: {
								stacking: 'stacking',  
								dataLabels:{
									enabled: false,
									formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return (this.point.extra+"%");
										}
									}
								},
							},
						},
					series: [{
						name: "count",
						data: mainArr,
						colorByPoint: true
					}]
				});
			}
		}else if(partyMeetingMainTypeId == '2' || partyMeetingMainTypeId == 2)
		{
			var str='';
			str+='<div class="col-sm-4">';
				str+='<h3 class="theme-title-color">State Meetings</h3>';
				str+='<div class="block">';
					str+='<div id="meetingsGraphBlockStateIdMain"></div>';
				str+='</div>';
			str+='</div>';
			$("#stateMeetingsBlockId").html(str);
			var finalArr = [];
			var colorsArr = ['#06D7A7','#0888DE','#994100','#AFCE69']
			for(var i in result.levelList)
			{
				var mainArr = [{"y":result.levelList[i].totalMeetings,"color":colorsArr[i]},{"y":result.levelList[i].invitedCount,"color":colorsArr[i]},{"y":result.levelList[i].imagesCnt,"color":colorsArr[i]}];
				
				var categoriesArr = [];
					categoriesArr.push('Total','Invited','Images')
				finalArr.push({name: result.levelList[i].name,data: mainArr})
				$("#meetingsGraphBlockStateIdMain").highcharts({
					chart: {
						type: 'bar'
					},
					title: {
						text: ''
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: categoriesArr,
						type: 'category',
						
					},
					yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						title: {
							text: ''
						},
						labels: {
							enabled: false,
						},
						stackLabels: {
							enabled: true,
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							},formatter: function() {
								//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
								//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
								return (this.total);
							},
						}
					},
					tooltip: {
						formatter: function () {
							var s = '<b>' + this.x + '</b>';
								$.each(this.points, function () {
								if(this.series.name != "Series 1")  
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									this.y/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
							});
							return s;
						},
						shared: true
					},
					legend: {   
							enabled: false,				
						},				
						plotOptions: {
							bar: {
								stacking: 'normal',  
								dataLabels:{
									enabled: false,
									formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return (this.y);
										}
									}
								},
							},
						},
					series: finalArr
				});
			}
		}
	}
}
function getPartyWiseMPandMLACandidatesCountDetials(electionScopeId,partyId,electionId){
	$("#openPostDetailsModalDivId").html(spinner);
	var electionIds=[];
	var electionScopeIds=[];
	electionIds.push(electionId)
	electionScopeIds.push(electionScopeId)
	var jsObj={
       electionIds    		:electionIds,
       loctionValue   		:1,
       loactionTypeId   	:2,
       electionScopeIds 	:electionScopeIds,
	   partyId :partyId
    }
   $.ajax({
		type : "POST",
		url : "getPartyWiseMPandMLACandidatesCountDetialsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildPartyWiseMPandMLACandidatesCountDetials(result,electionScopeId);
		}else{
			$("#openPostDetailsModalDivId").html(noData);
		}
	}); 
	function buildPartyWiseMPandMLACandidatesCountDetials(result,electionScopeId){
		var str='';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table-condensed table_custom" id="candidateDataTableId">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>PARTY </th>';
						str+='<th>PHOTO</th>';
						str+='<th>CANDIDATE NAME </th>';						
						str+='<th>LOCATION NAME </th>';
						str+='<th>Margin Votes</th>';
						str+='<th>Margin Vote %</th>';
					 str+='</tr>';
				 str+='</thead>';
                 str+='<tbody>';
				 for(var i in result){
					str+='<tr>';
						if(result[i].partyFlag != null ){
							if(result[i].party != null && result[i].cadreId != null && result[i].partyId !=872){
								if(result[i].party == "TDP" || result[i].party == "YSRC"){
									str+='<td style="vertical-align: middle; text-align: center;"><img src="images/party_flags/'+result[i].party+'_01.PNG"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/><i class="fa fa-star" aria-hidden="true" ></i></td>'; 
								}else{
									str+='<td style="vertical-align: middle; text-align: center;"><img src="images/party_flags/'+result[i].partyFlag+'"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/><i class="fa fa-star" aria-hidden="true" ></i></td>'; 
								}
								
							}else{
							    //str+='<td>'+result[i].partyFlag+'</td>'; 
								if(result[i].party == "TDP" || result[i].party == "YSRC"){
									str+='<td style="vertical-align: middle; text-align: center;"><img src="images/party_flags/'+result[i].party+'_01.PNG"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/></td>';
								}else{
									str+='<td style="vertical-align: middle; text-align: center;"><img src="images/party_flags/'+result[i].partyFlag+'"  onerror="setDefaultImage(this);" alt="party" style="height:25px;width:25px;"/></td>';
								}
						       
							}							
						}else{
							str+='<td style="vertical-align: middle; text-align: center;"> - </td>';     
						}						
						if(result[i].cadreId != null && result[i].image !=null){
							if(result[i].image != null && result[i].image.length>0)
								str+='<td style="vertical-align: middle; text-align: center;"><img src="https://mytdp.com/images/cadre_images/'+result[i].image+'" style="height:50px;width:50px;border-radius:50%" class="profile-image img-border" alt="profile" onerror="setDefaultImage(this);"/></td>';
							else
								str+='<td style="vertical-align: middle; text-align: center;"><img src="https://mytdp.com/images/candidates/'+result[i].condidateId+'.jpg" style="height:50px;width:50px;border-radius:50%" class="profile-image img-border" alt="profile" onerror="setDefaultImage(this);"/></td>';
						}else{
							str+='<td style="vertical-align: middle; text-align: center;"><img src="https://mytdp.com/images/candidates/'+result[i].condidateId+'.jpg" style="height:50px;width:50px;border-radius:50%" class="profile-image img-border" alt="profile" onerror="setDefaultImage(this);"/></td>';
						}
						if(result[i].candidateName != null ){
							str+='<td>'+result[i].candidateName+'</td>';         
						}else{
							str+='<td> - </td>';     
						}
						if(result[i].constituencyName != null ){
							str+='<td>'+result[i].constituencyName+'</td>';
						}else{
							str+='<td> - </td>';     
						}
						if(result[i].marginVotes !=null && result[i].marginVotes>0){
							str+='<td>'+parseInt(result[i].marginVotes)+'</td>';
						}else{
							str+='<td> - </td>';
						}
						if(result[i].marginVotesPercentage !=null && result[i].marginVotesPercentage>0){
							str+='<td>'+result[i].marginVotesPercentage+'</td>';
						}else{
							str+='<td> - </td>';
						}
					str+='</tr>';
				}
					 str+='</tbody>';				 
				str+='<table>';
			str+='<div>';
		$("#openPostDetailsModalDivId").html(str);
			$("#candidateDataTableId").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [[ 5, "desc" ]],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	} 
  }
  function getLocationWiseMeetingStatusDetailsAction(){
	  $("#openPostDetailsModalDivId").html(spinner);
	  var jsObj={
		searchLocationId	:  locationLevelId,
		locationValuesArr	:  userAccessLevelValuesArray,
		fromDateStr 		:customStartMeetingsDate,
		toDateStr 			:customEndMeetingsDate,
		meetingTypeId		:1, 	//CommitteeId
		partyMeetinLevelId 	:2 //districtId
	  }
	   $.ajax({
		  type : "GET",
		  url : "getLocationWiseMeetingStatusDetailsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){  
			if(result !=null && result.length>0){
				return buildLocationWiseMeetingStatusDetailsAction(result);
			}
		});
		
	function buildLocationWiseMeetingStatusDetailsAction(result){
		
		var str='';
		var overAllTotalCount=0;
		var overAllYesCount=0;
		var overAllNoCount=0;
		var overAllMayBeCount=0;
		var overAllNotUpdatedCount=0;
		var monthtotalCount=0;
		
		str+='<h4>Month Overview</h4>';
		str+='<div class="table-responsive">';
			str+='<table class="table-meetings-month">';
				str+='<thead>';
					str+='<tr>';
						str+='<th rowspan="2" style="min-width: 120px ! important;">Total Meetings Every Month</th>';
						str+='<th colspan="5">OVERALL</th>';
						if(result[0].levelList !=null && result[0].levelList.length>0){
							for(var i in result[0].levelList){
								str+='<th colspan="5">'+result[0].levelList[i].month+'</th>';
							}
						}
						
					str+='</tr>';
					str+='<tr>';
						str+='<th>Total</th>';
						str+='<th>Yes</th>';
						str+='<th>No</th>';
						str+='<th>Maybe</th>';
						str+='<th>Not Updated</th>';
						if(result[0].levelList !=null && result[0].levelList.length>0){
							for(var i in result[0].levelList){
								overAllYesCount = overAllYesCount+result[0].levelList[i].yesCount;
								overAllNoCount = overAllNoCount+result[0].levelList[i].noCount;
								overAllMayBeCount = overAllMayBeCount+result[0].levelList[i].maybeCount;
								overAllNotUpdatedCount =overAllNotUpdatedCount+result[0].levelList[i].notUpdatedCount;
								overAllTotalCount = overAllTotalCount+result[0].levelList[i].yesCount+result[0].levelList[i].noCount+result[0].levelList[i].maybeCount+result[0].levelList[i].notUpdatedCount;
								str+='<th>Total</th>';
								str+='<th>Yes</th>';
								str+='<th>No</th>';
								str+='<th>Maybe</th>';
								str+='<th>Not Updated</th>';
							}
						}
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						str+='<td>14</td>';
						str+='<td>'+overAllTotalCount+'</td>';
						str+='<td>'+overAllYesCount+'</td>';
						str+='<td>'+overAllNoCount+'</td>';
						str+='<td>'+overAllMayBeCount+'</td>';
						str+='<td>'+overAllNotUpdatedCount+'</td>';
						for(var i in result[0].levelList){
							var totalMonthCount=0;
							totalMonthCount =result[0].levelList[i].yesCount+result[0].levelList[i].noCount+result[0].levelList[i].maybeCount+result[0].levelList[i].notUpdatedCount;
							str+='<td>'+totalMonthCount+'</td>';
							str+='<td>'+result[0].levelList[i].yesCount+'</td>';
							str+='<td>'+result[0].levelList[i].noCount+'</td>';
							str+='<td>'+result[0].levelList[i].maybeCount+'</td>';
							str+='<td>'+result[0].levelList[i].notUpdatedCount+'</td>';
						}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		str+='<h4 class="m_top10 text-capitalize">District wise Meeting Conducted Details</h4>';
		str+='<div class="table-responsive">';
			str+='<table class="table-meetings-month-dist table-election table">';
				str+='<thead>';
					str+='<tr>';
						str+='<th style="background-color:#E9F0FC">Location Name</th>';
						for(var i in result[0].datesList){
							str+='<th class="text-center" style="background-color:#E9F0FC">'+result[0].datesList[i].month+'</th>';
						}
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					
						for(var i in result){
							str+='<tr>';
							str+='<td>'+result[i].name+'</td>';
							for(var j in result[i].datesList){
								if(result[i].datesList[j].momStatus == "Y"){
									str+='<td class="text-center" style="color:#53BC7C">Yes ('+result[i].datesList[j].conductedDate+')</td>';
								}else if(result[i].datesList[j].momStatus == "N"){
									str+='<td class="text-center" style="color:#F89394">No</td>';
								}else if(result[i].datesList[j].momStatus == "M"){
									str+='<td class="text-center" style="color:#99410F">May Be</td>';
								}else if(result[i].datesList[j].momStatus == "NU"){
									str+='<td class="text-center" class="text-danger">Not Updated</td>';
								}else{
									str+='<td class="text-center"> - </td>';
								}
								
							}
							str+='</tr>';
						}
					
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		$("#openPostDetailsModalDivId").html(str);
	}
  }
 function getDetailedElectionResults(constituencyId,electionYear,type,electionTypeId){
	$("#openPostDetailsModalDivId").html(spinner);
	var electionScopeIdsArr=[];
	electionScopeIdsArr.push(electionTypeId)
	jsObj={
	  	constituencyId: constituencyId,
		electionScopeIdsArr:electionScopeIdsArr
    }
    $.ajax({
      type : "GET",
      url : "getDetailedElectionInformactionAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildDetailedElectionInformaction(result,constituencyId,electionYear,type);
		}
	});
	
	function buildDetailedElectionInformaction(result,constituencyId,electionYear,type){
		
		var navTabs = '';
		navTabs+='<div class="block">';
			//navTabs+='<h4 class="theme-title-color">Assembly Election Year Wise Details</h4>';
		navTabs+='<div class="row">';
			navTabs+='<div class="col-sm-5" style="padding-right: 10px;">';
				navTabs+='<div class="panel panel-default panel-border">';
					navTabs+='<div class="panel-heading winnersColor" >';
						navTabs+='<div class="media">';
							navTabs+='<div class="media-left">';
								navTabs+='<img src="coreApi/img/winner_icon.png" alt="winner_icon"></img>';
							navTabs+='</div>';
							navTabs+='<div class="media-body">';
								navTabs+='<h3 class="m_top20">Winners</h3>';
							navTabs+=' </div>';
						navTabs+='</div>';
					navTabs+='</div>';
					navTabs+='<div class="panel-body">';
						navTabs+='<ul class="nav nav-tabs nav-tabs-horizontal_Elec" role="tablist">';
							for(var i in result){
								/* if(electionYear == result[i].electionYear){
									alert(1)
									$('.nav-tabs-horizontal_Elec a[href="#candidatesResult'+i+'"]').trigger('click');
								} */
								
								if(electionYear == result[i].electionYear){
									navTabs+='<li class="active">';
										navTabs+='<a href="#candidatesResult'+i+'" navTabs-click="" aria-controls="candidatesResult'+i+'" role="tab" data-toggle="tab">';
											navTabs+='<div class="media">';
												navTabs+='<div class="media-left">';
													navTabs+='<div class="row">';
															navTabs+='<div class="col-sm-5">';
																navTabs+='<h4 class="m_top10">'+result[i].electionYear+'</h4>';
															navTabs+=' </div>';
															navTabs+='<div class="col-sm-5">';
															if(result[i].candidateResultsVO.partyShortName == "TDP" || result[i].candidateResultsVO.partyShortName == "YSRC"){
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'_01.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'" style="height:25px;width:25px;"></img>';
															}else{
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'" style="height:25px;width:25px;"></img>';
															}
																navTabs+='<p class="">'+result[i].candidateResultsVO.partyShortName+'</p>';
															navTabs+=' </div>';
													navTabs+='</div>';
													
												navTabs+='</div>';
													navTabs+='<div class="media_body_custom">';
														navTabs+='<h5>'+result[i].candidateResultsVO.candidateName+'</h5>';
															navTabs+='<div class="row m_top5">';
																navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Votes&nbsp;Earned</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesEarned+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentage+'%</span></h6>';
															navTabs+='</div>';
															navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Margin&nbsp;Gained</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesMargin+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentMargin+'%</span></h6>';
															navTabs+='</div>';
														navTabs+=' </div>';
													navTabs+=' </div>';
											navTabs+='</div>';
										navTabs+='</a>';
									navTabs+='</li>'; 
								}else{
									navTabs+='<li class="">';
										navTabs+='<a href="#candidatesResult'+i+'" navTabs-click="" aria-controls="candidatesResult'+i+'" role="tab" data-toggle="tab">';
											navTabs+='<div class="media">';
												navTabs+='<div class="media-left">';
													navTabs+='<div class="row">';
															navTabs+='<div class="col-sm-5">';
																navTabs+='<h4 class="m_top10">'+result[i].electionYear+'</h4>';
															navTabs+=' </div>';
															navTabs+='<div class="col-sm-5">';
															if(result[i].candidateResultsVO.partyShortName == "TDP" || result[i].candidateResultsVO.partyShortName == "YSRC"){
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'_01.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'" style="height:25px;width:25px;"></img>';
															}else{
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'" style="height:25px;width:25px;"></img>';
															}
																navTabs+='<p class="">'+result[i].candidateResultsVO.partyShortName+'</p>';
															navTabs+=' </div>';
													navTabs+='</div>';
													
												navTabs+='</div>';
													navTabs+='<div class="media_body_custom">';
														navTabs+='<h5>'+result[i].candidateResultsVO.candidateName+'</h5>';
															navTabs+='<div class="row m_top5">';
																navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Votes&nbsp;Earned</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesEarned+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentage+'%</span></h6>';
															navTabs+='</div>';
															navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Margin&nbsp;Gained</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesMargin+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentMargin+'%</span></h6>';
															navTabs+='</div>';
														navTabs+=' </div>';
													navTabs+=' </div>';
											navTabs+='</div>';
										navTabs+='</a>';
									navTabs+='</li>';
								}
							}
							
							
						navTabs+='</ul>';
					navTabs+='</div>';
				navTabs+='</div>';
			navTabs+='</div>';
			
				
			
			navTabs+='<div class="col-sm-7 pad_left0">';
				navTabs+='<div class="panel panel-default panel-border">';
					navTabs+='<div class="panel-heading looserColor" >';
					
						navTabs+='<div class="media">';
							navTabs+='<div class="media-left">';
								navTabs+='<img src="coreApi/img/loser_icon.png" alt="loser_icon"></img>';
							navTabs+='</div>';
							navTabs+='<div class="media-body">';
								navTabs+='<h3 class="m_top20">Losers</h3>';
							navTabs+=' </div>';
						navTabs+='</div>';
						
					navTabs+='</div>';
					navTabs+='<div class="panel-body panel_body_custom">';
						navTabs+='<div class="tab-content">';
								for(var i in result){
									if(electionYear == result[i].electionYear){
										navTabs+='<div role="tabpanel" class="tab-pane active pad_10 block" id="candidatesResult'+i+'" style="border-radius: 10px;">';
									}else{
										navTabs+='<div role="tabpanel" class="tab-pane pad_10 block" id="candidatesResult'+i+'" style="border-radius: 10px;">';
									}
										navTabs+='<div class="table-responsive">';
										navTabs+='<table class="table table-condensed table_padding" id="candidatesResultsDT">';
											navTabs+='<thead>';
												navTabs+='<tr>';
													navTabs+='<th>Candidate Name</th>';
													navTabs+='<th>Party</th>';
													navTabs+='<th>Party Flag</th>';
													navTabs+='<th>Votes Earned</th>';
													navTabs+='<th>Earned %</th>';
													navTabs+='<th>Rank</th>';
												navTabs+='</tr>';
											navTabs+='</thead>';
											
											navTabs+='<tbody>';
												for(var j in result[i].candidateOppositionList){
													navTabs+='<tr>';
														if(result[i].candidateOppositionList[j].candidateName !=null && result[i].candidateOppositionList[j].candidateName.length>10){
															navTabs+='<td><span class="candidate_rounded">'+result[i].candidateOppositionList[j].candidateName.charAt(0)+'</span><span class="tooltipCls" style="cursor:pointer; data-toggle="tooltip" data-placement="right" title="'+result[i].candidateOppositionList[j].candidateName+'">'+result[i].candidateOppositionList[j].candidateName.substring(0,10)+'...</span></td>';
														}else{
															navTabs+='<td><span class="candidate_rounded">'+result[i].candidateOppositionList[j].candidateName.charAt(0)+'</span>'+result[i].candidateOppositionList[j].candidateName+'</td>';
														}
														
														
														navTabs+='<td>'+result[i].candidateOppositionList[j].partyShortName+'</td>';
														
														if(result[i].candidateOppositionList[j].partyShortName == "TDP" || result[i].candidateOppositionList[j].partyShortName == "YSRC"){
															navTabs+='<td><img class="" src="images/party_flags/'+result[i].candidateOppositionList[j].partyShortName+'_01.PNG" alt="'+result[i].candidateOppositionList[j].partyShortName+'" style="height:25px;width:25px;"></img></td>';
														}else{
															navTabs+='<td><img class="" src="images/party_flags/'+result[i].candidateOppositionList[j].partyShortName+'.png" alt="'+result[i].candidateOppositionList[j].partyShortName+'" style="height:25px;width:25px;"></img></td>';
														}
														
														navTabs+='<td>'+result[i].candidateOppositionList[j].votesEarned+'</td>';
														navTabs+='<td>'+result[i].candidateOppositionList[j].votesPercentage+'</td>';
														navTabs+='<td>'+result[i].candidateOppositionList[j].rank+'</td>';
													navTabs+='</tr>';
												}
											navTabs+='</tbody>';
										navTabs+='</table>';
										navTabs+='</div>';
									navTabs+='</div>';
								}	
								
							
						navTabs+='</div>'; 
					navTabs+='</div>';
				navTabs+='</div>';
			navTabs+='</div>';
		navTabs+='</div>';
		navTabs+='</div>';
		$("#openPostDetailsModalDivId").html(navTabs);
		$(".tooltipCls").tooltip();
		$("#candidatesResultsDT").dataTable({
			"paging":   true,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}
}

function getGrivenceOverviewDtls(yearId){
	$("#grievanceMainBlockId").html(spinner);
	var jsObj={
			"fromDate" 			:customStartGrivanceDate,
			"toDate"			:customEndGrivanceDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getGrivenceOverviewDtlsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.subList !=null && result.subList.length>0 && result.subList1 !=null && result.subList1.length>0 && result.subList2 !=null && result.subList2.length>0){
			buildGrivenceOverviewDtls(result,"grievance");
		}else{
			$("#grievanceMainBlockId").html("No Data Available");
		}
	});	
}
function getInsuranceOverviewDetails(yearId){
	$("#insuranceMainBlockId").html(spinner);
	var jsObj={
			"fromDate" 			:customStartInsuranceDate,
			"toDate"			:customEndInsuranceDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getInsuranceOverviewDetailsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.subList !=null && result.subList.length>0 && result.subList1 !=null && result.subList1.length>0){
			buildGrivenceOverviewDtls(result,"insurance");
		}else{
			$("#insuranceMainBlockId").html("No Data Available");
		}
	});	
}
function getTrustEducationOverviewDetails(yearId){
	$("#ntrTrustMainBlockId").html(spinner);
	var jsObj={
			"fromDate" 			:customStartNtrTrustDate,
			"toDate"			:customEndNtrTrustDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getTrustEducationOverviewDetailsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.subList !=null && result.subList.length>0 && result.subList1 !=null && result.subList1.length>0 && result.subList2 !=null && result.subList2.length>0){
			buildGrivenceOverviewDtls(result,"ntrTrust");
		}else{
			$("#ntrTrustMainBlockId").html("No Data Available");
		}
	});	
}
	
	function buildGrivenceOverviewDtls(result,type){
		var str='';
		var totalCount=0;
		if(result !=null && result.subList !=null && result.subList.length>0){
			for(var i in result.subList){
				totalCount =totalCount+result.subList[i].count;
			}
		}
			if(type == "grievance"){
				str+='<div class="col-sm-3">';
			}else if(type == "insurance"){
				str+='<div class="col-sm-4">';
			}else if(type == "ntrTrust"){
				str+='<div class="col-sm-4">';
			}
			str+='<div class="black_block">';
				str+='<h5>Overview</h5>';
				if(totalCount !=null && totalCount>0){
					str+='<h3 class="m_top20">Total - '+totalCount+'</h3>';
				}else{
					str+='<h3 class="m_top20">Total - 0</h3>';
				}
				
				str+='<ul class="list_style_css m_top20">';
				if(result !=null && result.subList !=null && result.subList.length>0){
					for(var i in result.subList){
						str+='<li>';
							if(type == "grievance"){
								if(result.subList[i].count !=null && result.subList[i].count>0){
									str+='<h6><span class="squareCss" style="background-color:'+grivanceColorObj[result.subList[i].name.trim()]+'"></span>  '+result.subList[i].name+' <span class="pull-right">'+result.subList[i].count+'</span></h6>';
								}else{
									str+='<h6><span class="squareCss" style="background-color:'+grivanceColorObj[result.subList[i].name.trim()]+'"></span>  '+result.subList[i].name+' <span class="pull-right"> - </span></h6>';
								}
								
								
							}else if(type == "insurance"){
								if(result.subList[i].count !=null && result.subList[i].count>0){
									str+='<h6><span class="squareCss" style="background-color:'+insuranceColorObj[result.subList[i].name.trim()]+'"></span>  '+result.subList[i].name+' <span class="pull-right">'+result.subList[i].count+'</span></h6>';
								}else{
									str+='<h6><span class="squareCss" style="background-color:'+insuranceColorObj[result.subList[i].name.trim()]+'"></span>  '+result.subList[i].name+' <span class="pull-right"> - </span></h6>';
								}
								
							}else if(type == "ntrTrust"){
								if(result.subList[i].count !=null && result.subList[i].count>0){
									str+='<h6><span class="squareCss" style="background-color:'+grivanceColorObj[result.subList[i].name.trim()]+'"></span>  '+result.subList[i].name+' <span class="pull-right">'+result.subList[i].count+'</span></h6>';
								}else{
									str+='<h6><span class="squareCss" style="background-color:'+grivanceColorObj[result.subList[i].name.trim()]+'"></span>  '+result.subList[i].name+' <span class="pull-right"> - </span></h6>';
								}
								
							}
						
						str+='</li>';
					}
				}else{
					str+='<li> - </li>';
					
				}
					
				str+='</ul>';
					
			str+='</div>';
		str+='</div>';
		if(type =="grievance" || type == "insurance" || type == "ntrTrust"){
			if(result !=null && result.subList1 !=null && result.subList1.length>0){
				for(var i in result.subList1){
						if(type == "grievance"){
							str+='<div class="col-sm-3">';
						}else if(type == "insurance"){
							str+='<div class="col-sm-4">';
						} else if(type =="ntrTrust"){
							str+='<div class="col-sm-4">';
						}
						str+='<div class="block">';
							str+='<h5>'+result.subList1[i].name+'</h5>';
								if(type == "grievance"){
									str+='<div id="'+type+'MainBlockId'+i+'" style="height:200px;" class="m_top5"></div>';
								}else if(type == "insurance"){
									str+='<div id="'+type+'MainBlockId'+i+'" style="height:300px;" class="m_top5"></div>';
								} else if (type = "ntrTrust") {
									str+='<div id="'+type+'MainBlockId'+i+'" style="height:300px;" class="m_top5"></div>';
								}
								
								str+='<div id="'+type+'StatusMainBlockId'+i+'"></div>';
						str+='</div>';
					str+='</div>';
				}
			}
		}
		$("#"+type+"MainBlockId").html(str);
		if(type =="grievance" || type == "insurance" || type == "ntrTrust"){
			if(result !=null && result.subList2 !=null && result.subList2.length>0){
				for(var i in result.subList2){
					var str1='';
					if(type == "ntrTrust")
					{
						str1+='<ul class="list_style_css1 m_top20" style="min-height:90px">';
					}else{
						str1+='<ul class="list_style_css1 m_top20" style="min-height:230px">';
					}
					
					for(var j in result.subList2[i].subList){
						str1+='<li>';
							str1+='<div class="dropup">';
								str1+='<h6>'+result.subList2[i].subList[j].name+'';
								if(result.subList2[i].subList[j].count !=null && result.subList2[i].subList[j].count>0){
									str1+='<span class="pull-right dropdown-toggle" style="cursor:pointer;text-decoration:underline"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="dropdownMenu'+i+''+j+'" >'+result.subList2[i].subList[j].count+'</span>';
								}else{
									str1+='<span class="pull-right"> - </span>';
								}
								
								
								str1+='<div class="dropdown-menu pull-right arrow_box_bottom" aria-labelledby="dropdownMenu'+i+''+j+'" style="padding:0px;min-width:200px;">';
								var totalcountDp=0;
								for(var k in result.subList2[i].subList[j].subList){
									totalcountDp =totalcountDp+result.subList2[i].subList[j].subList[k].count;
								}	
									str1+='<div class="panel panel-default">';
										  str1+='<div class="panel-heading" style="background-color: #f4f5f5 !important; border-bottom: 1px solid #dddddd !important;text-align: center;">';
											str1+='<h3 class="panel-title">Total - '+totalcountDp+'</h3>';
										  str1+='</div>';
										  str1+='<div class="panel-body">';
											str1+='<ul class="list_style_css2">';
											for(var k in result.subList2[i].subList[j].subList){
												str1+='<li>';
													str1+='<h6><span class="squareCss" style="background-color:'+grivanceColorObj[result.subList2[i].subList[j].subList[k].name.trim()]+'"></span>  '+result.subList2[i].subList[j].subList[k].name+' <span class="pull-right">'+result.subList2[i].subList[j].subList[k].count+'</span></h6>';
												str1+='</li>';
											}
											str1+='</ul>';
										  str1+='</div>';
									str1+='</div>';
								
									
								str1+='</div>';
								str+='</h6>';
							str1+='</div>';
						str1+='</li>';
					}
					
					str1+='</ul>';
					
					$("#"+type+"StatusMainBlockId"+i).html(str1);
				}
			}
		}
	    if(result !=null && result.subList1 !=null && result.subList1.length>0){
			var totalCount=0;
			for(var i in result.subList1){
				var categoriesArr=[];
				var mainArr=[];
				totalCount = result.subList1[i].count;
				if(result.subList1[i].subList !=null && result.subList1[i].subList.length>0){
					for(var j in result.subList1[i].subList){
						
						if(type == "grievance"){
							if(result.subList1[i].name == "GOVT"){
								categoriesArr.push(result.subList1[i].subList[j].name);
							}else if(result.subList1[i].name == "PARTY"){
								categoriesArr.push(result.subList1[i].subList[j].name);
							}else if(result.subList1[i].name == "WELFARE"){
								categoriesArr.push(result.subList1[i].subList[j].name);
							}
							mainArr.push({"y":result.subList1[i].subList[j].count,color:grivanceColorObj[result.subList1[i].subList[j].name.trim()]})
						}else if(type == "insurance"){
							categoriesArr.push(result.subList1[i].subList[j].name);
							mainArr.push({"y":result.subList1[i].subList[j].count,color:insuranceColorObj[result.subList1[i].subList[j].name.trim()]})
						}else if(type == "ntrTrust"){
							categoriesArr.push(result.subList1[i].subList[j].name);
							mainArr.push({"y":result.subList1[i].subList[j].count,color:grivanceColorObj[result.subList1[i].subList[j].name.trim()]})
						}
						
					}
				}
				
				$("#"+type+"MainBlockId"+i).highcharts({
					chart: {
						backgroundColor: '#F4F4F5',
						borderColor: '#D4D6D7',
						borderWidth: 1,
						type: 'column'
					},
					title: {
						text: 'TOTAL - '+totalCount+'',
						align:'left',
						style: {
						 color: '#000',
						 font: 'bold 16px "Lato", sans-serif'
					  },
					},
				   
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: categoriesArr,
						type: 'category',
						
					},
					yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						title: {
							text: ''
						},
						labels: {
							enabled: false,
						},
						stackLabels: {
							enabled: true,
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							},
							formatter: function() {
								//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
								//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
								return (this.total);
							},
						}
					},
					tooltip: {
						formatter: function () {
							var s = '<b>' + this.x + '</b>';

								$.each(this.points, function () {
								if(this.series.name != "Series 1")  
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									this.y/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
							});
							return s;
						},
						shared: true
					},
					legend: {   
							enabled: false,				
						},				
						plotOptions: {
							column: {
								pointWidth: 20,
								gridLineWidth: 15,
								stacking: 'stacking',  
								dataLabels:{
									enabled: false,
									formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return (this.y);
										}
									}
								},
							},
						},
					series: [{
						name: "count",
						data: mainArr,
						colorByPoint: true
					}]
				});
			}
		}
	}
/*$(document).on('click','.electionTypeWiseCls',function(){
	var  electionScopeIds = $(this).attr("value");
	var electionSubTypeArr = $("input[name='optionsRadios1']:checked").val();
	alert(electionScopeIds);
	alert(electionSubTypeArr);
});*/

function getLocationWiseElectionDetails(){
	var jsObj = {
			
			locationType 	: locationLevelName	
		}
	 $.ajax({
	  type : "POST",
	  url : "getLocationBasedElectionDetailsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		console.log(result);
	});	
}
function getLocationWiseSpecialMeetingsMeetingsExpanction(partyMeetingMainTypeId,partyMeetingTypeId){
	if(partyMeetingTypeId == 0)
	{
		$("#specialMeetingsBlockId").html(spinner);
	}else{
		$("#openPostDetailsModalDivId").html(spinner);
	}
	jsObj={
		locationTypeId			:locationLevelId,
		locationValues			:userAccessLevelValuesArray,
		fromDate 				:customStartMeetingsDate,
		toDate 					:customEndMeetingsDate,
		partyMeetingMainTypeId	:partyMeetingMainTypeId,
		partyMeetingTypeId:partyMeetingTypeId
	}
	$.ajax({
		type : "GET",
		url : "getLocationWiseSpecialMeetingsMeetingsExpanctionAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){ 
		if(result !=null && result.levelList !=null && result.levelList.length>0 && partyMeetingTypeId > 0){
			return buildLocationWiseSpecialMeetingsMeetingsExpanction(result,partyMeetingMainTypeId,partyMeetingTypeId)
		}
		if(partyMeetingTypeId == 0)
		{
			return buildData(result);
		}
		
	});
	function buildData(result)
	{
		var str='';
		str+='<div class="col-sm-12">';
			str+='<h3 class="theme-title-color">Special Meetings</h3>';
			str+='<small>NOTE: Showing Information "Participantes" from this location Level Only</small>';
			str+='<div class="scrollListMeeting">';
				str+='<div class="row">';
					for(var i in result.levelList)
					{
						str+='<div class="col-sm-6 m_top10">';
							str+='<div class="block">';
								str+='<h4 class="panel-title text-capital">'+result.levelList[i].name+'</h4>';
								str+='<div class="row m_top10">';
									str+='<div class="col-sm-3">';
										str+='<div id="specialTotalMeetings'+i+'" style="height:120px;"></div>';
										str+='<p>Total Meetings</p>';
									str+='</div>';
									str+='<div class="col-sm-5" style="border-right:2px dashed #ddd;border-left:2px dashed #ddd">';
										str+='<div id="specialInviteesMeetings'+i+'" style="height:150px;"></div>';
									str+='</div>';
									str+='<div class="col-sm-4">';
										str+='<p>Attended</p>';
										str+='<div id="specialAttendedMeetings'+i+'" style="height:150px;"></div>';
									str+='</div>';
								str+='</div>';
								var imagesCnt = 0;
									for(var l in result.levelList[i].levelList[0].datesList)
									{
										imagesCnt = imagesCnt + result.levelList[i].levelList[0].datesList[l].recentImagesCnt;
									}
								if(imagesCnt > 0){
									
									str+='<p class="m_top15">Recent Meeting on '+result.levelList[i].levelList[0].conductedDate+'  ( Total Inviees : <b>'+result.levelList[i].levelList[0].recentMeetingInviteesCnt+'</b> Images : <b>'+imagesCnt+'</b>  )</p>';
								}else{
									str+='<p class="m_top15">Recent Meeting on '+result.levelList[i].levelList[0].conductedDate+'  ( Total Inviees : <b>'+result.levelList[i].levelList[0].recentMeetingInviteesCnt+'</b>  )</p>';
								}
								
								str+='<div class="table-responsive" style="height:121px">';
									str+='<table class="table m_top10">';
										str+='<thead class="bg-E9">';
											str+='<th></th>';
											str+='<th>Attended</th>';
											str+='<th>Late</th>';
											str+='<th>Absent</th>';
											str+='<th>NI</th>';
										str+='</thead>';
										if(result.levelList[i].levelList[0].datesList !=null && result.levelList[i].levelList[0].datesList.length>0){
										for(var k in result.levelList[i].levelList[0].datesList){
											str+='<tr>';
												str+='<td>'+result.levelList[i].levelList[0].datesList[k].name+'</td>';
												str+='<td><span class="" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_partyMeetingMainTypeId="'+partyMeetingMainTypeId+'" attr_partyMeetingTypeId="'+result.levelList[i].id+'" attr_party_meeting_id="'+result.levelList[i].levelList[0].id+'" attr_non_invitee="false" attr_click_type="attendee" attr_session_typeId="'+result.levelList[i].levelList[0].datesList[k].id+'">'+result.levelList[i].levelList[0].datesList[k].recentInviteeAttended+'</span>&nbsp;&nbsp;<small style="color:green;text-decoration:none!important;">'+result.levelList[i].levelList[0].datesList[k].attendedPerc+' %</small></td>';
												
												str+='<td>'+result.levelList[i].levelList[0].datesList[k].recentLate+'&nbsp;&nbsp;<small style="color:green;text-decoration:none!important;">'+result.levelList[i].levelList[0].datesList[k].latePerc+' %</small></td>';
												
												str+='<td><span class="" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_partyMeetingMainTypeId="'+partyMeetingMainTypeId+'" attr_partyMeetingTypeId="'+partyMeetingTypeId+'" attr_party_meeting_id="'+result.levelList[i].levelList[0].id+'" attr_non_invitee="false" attr_click_type="abscent" attr_session_typeId="'+result.levelList[i].levelList[0].datesList[k].id+'">'+result.levelList[i].levelList[0].datesList[k].recentAbcent+'</span>&nbsp;&nbsp;<small style="color:green;text-decoration:none!important;">'+result.levelList[i].levelList[0].datesList[k].abcentPerc+' %</small></td>';
												
												str+='<td>'+result.levelList[i].levelList[0].datesList[k].recentNonInvitee+'</td>';
											str+='</tr>';
										}
									}else{
										str+='<tr>';
												str+='<td> - </td>';
												str+='<td> - </td>';
												str+='<td> - </td>';
												str+='<td> - </td>';
												str+='<td> - </td>';
											str+='</tr>';
									}
									str+='</table>';
								str+='</div>';
								str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-24 popUpDetailsClickCls" attr_type="meeting_type_special" attr_partyMeetingMainTypeId="3" attr_partyMeetingTypeId="'+result.levelList[i].id+'" attr_name="'+result.levelList[i].name+'" style="margin-top:-16px;cursor:pointer;text-decoration:none;"></i>';
							str+='</div>';
						str+='</div>';
					}
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#specialMeetingsBlockId").html(str);
		/* if(result.levelList.length > 2)
		{
			$(".scrollListMeeting").mCustomScrollbar({setHeight:"600px"})
		} */
		var colorsArr = ['#06D7A7','#0888DE','#994100','#AFCE69']
		for(var i in result.levelList)
		{
			$("#specialTotalMeetings"+i).highcharts({
				colors:['#3BB878'],
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: 0,
					plotShadow: false
				},
				title: {
					text: ''+result.levelList[i].totalMeetings+'',
					align: 'center',
					verticalAlign: 'middle',
					y: 10,
					style: {
						color: '#000',
						font: 'bold 14px "Lato", sans-serif',
					}
				},
				tooltip: {
					enabled: false,
				},
				plotOptions: {
					pie: {
						dataLabels: {
							enabled: false,
							distance: -10,
							style: {
								fontWeight: 'bold',
								color: 'white'
							}
						},
						size:"170%",
						startAngle: -90,
						endAngle: 90,
						center: ['50%', '80%']
					}
				},
				series: [{
					type: 'pie',
					name: 'Total Meetings',
					innerSize: '50%',
					data: [
						['Total Meetings',   result.levelList[i].totalMeetings],
					]
				}]
			});
			var categoriesArr = ['Invitees','Late','Absent','Non-invitee'];
			var mainArr = [{"y":result.levelList[i].inviteeAttendedCount},{"y":result.levelList[i].lateCount},{"y":result.levelList[i].absentCount},{"y":result.levelList[i].nonInviteesCount}];
			$("#specialAttendedMeetings"+i).highcharts({
				colors:['#3BB878','#E69C64','#ED5C5C','#259381'],
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: categoriesArr,
					type: 'category',
					labels: {
						formatter: function() {
							return this.value.toString().substring(0, 5)+'';
							
						},
					},
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					labels: {
						enabled:true
					},
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						},
					}
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';
							$.each(this.points, function () {
							if(this.series.name != "Series 1")  
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								this.y/* +' - ' +
								(Highcharts.numberFormat(this.percentage,1)+'%'); */
						});
						return s;
					},
					shared: true
				},
				legend: {   
						enabled: false,				
					},				
					plotOptions: {
						column: {
							stacking: 'normal',  
							dataLabels:{
								enabled: false,
								formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return (this.y);
									}
								}
							},
						},
					},
				series: [{
					name: "count",
					data: mainArr,
					colorByPoint: true
				}]
			});
			var specialInviteesMeetingsCatArr = ['Total Invited','Total Attended'];
			var specialInviteesMeetingsArr = [{"y":result.levelList[i].invitedCount},{"y":result.levelList[i].inviteeAttendedCount}];
			$("#specialInviteesMeetings"+i).highcharts({
				colors:['#D7AD06'],
				chart: {
					type: 'bar'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: specialInviteesMeetingsCatArr,
					type: 'category',
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					labels: {
						enabled:false
					},
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						},
					}
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';
							$.each(this.points, function () {
							if(this.series.name != "Series 1")  
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								this.y/* +' - ' +
								(Highcharts.numberFormat(this.percentage,1)+'%'); */
						});
						return s;
					},
					shared: true
				},
				legend: {   
						enabled: false,				
					},				
					plotOptions: {
						bar: {
							stacking: 'normal',  
							dataLabels:{
								enabled: false,
								formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return (this.y);
									}
								}
							},
						},
					},
				series: [{
					name: "count",
					data: specialInviteesMeetingsArr,
					colorByPoint: true
				}]
			});
		}
	
	}
	function buildLocationWiseSpecialMeetingsMeetingsExpanction(result,partyMeetingMainTypeId,partyMeetingTypeId){
		var str='';
			for(var i in result.levelList){		
				str+='<div class="block">';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-3">';
							str+='<div id="specialTotalMeetingsExpand'+i+'" style="height:120px;"></div>';
							str+='<p>Total Meetings</p>';
						str+='</div>';
						str+='<div class="col-sm-5" style="border-right:2px dashed #ddd;border-left:2px dashed #ddd">';
							str+='<div id="specialInviteesMeetingsExpand'+i+'" style="height:150px;"></div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<p>Attended</p>';
							str+='<div id="specialAttendedMeetingsExpand'+i+'" style="height:150px;"></div>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="table-responsive">';
						str+='<table class="table m_top10 table-bordered">';
							str+='<thead class="bg-E9">';
								str+='<th>Meeting Date</th>';
								str+='<th>Title</th>';
								str+='<th>Attendance</th>';
								str+='<th>Total Invitees</th>';
								str+='<th>Attended</th>';
								str+='<th>Late</th>';
								str+='<th>Absent</th>';
								str+='<th>NI</th>';
								str+='<th>Images</th>';
							str+='</thead>';
							for(var j in result.levelList[i].levelList){
								if(result.levelList[i].levelList[j].datesList !=null && result.levelList[i].levelList[j].datesList.length>0){
									var length = result.levelList[i].levelList[j].datesList.length+1;
									
									str+='<tr>';
										str+='<td rowspan="'+length+'">'+result.levelList[i].levelList[j].conductedDate+'</td>';
										str+='<td rowspan="'+length+'">'+result.levelList[i].levelList[j].name+'</td>';
									str+='</tr>';
									if(result.levelList[i].levelList[j].datesList !=null && result.levelList[i].levelList[j].datesList.length>0){
										for(var k in result.levelList[i].levelList[j].datesList){
											str+='<tr>';
												str+='<td>'+result.levelList[i].levelList[j].datesList[k].name+'</td>';
												
												str+='<td class="getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_partyMeetingMainTypeId="'+partyMeetingMainTypeId+'" attr_partyMeetingTypeId="'+partyMeetingTypeId+'" attr_party_meeting_id="'+result.levelList[i].levelList[j].id+'" attr_non_invitee="false" attr_click_type="invitee" attr_session_typeId="0">'+result.levelList[i].levelList[j].recentMeetingInviteesCnt+'</td>';
												
												str+='<td><span class="getCmtMemDtls" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_partyMeetingMainTypeId="'+partyMeetingMainTypeId+'" attr_partyMeetingTypeId="'+partyMeetingTypeId+'" attr_party_meeting_id="'+result.levelList[i].levelList[j].id+'" attr_non_invitee="false" attr_click_type="attendee" attr_session_typeId="'+result.levelList[i].levelList[j].datesList[k].id+'">'+result.levelList[i].levelList[j].datesList[k].recentInviteeAttended+'</span>&nbsp;&nbsp;<small style="color:green;text-decoration:none!important;">'+result.levelList[i].levelList[j].datesList[k].attendedPerc+' %</small></td>';
												
												str+='<td>'+result.levelList[i].levelList[j].datesList[k].recentLate+'&nbsp;&nbsp;<small style="color:green;text-decoration:none!important;">'+result.levelList[i].levelList[j].datesList[k].latePerc+' %</small></td>';
												
												str+='<td><span class="" attr_search="notrequired" style="cursor:pointer;" attr_position="overview" attr_status="attended" attr_partyMeetingMainTypeId="'+partyMeetingMainTypeId+'" attr_partyMeetingTypeId="'+partyMeetingTypeId+'" attr_party_meeting_id="'+result.levelList[i].levelList[j].id+'" attr_non_invitee="false" attr_click_type="abscent" attr_session_typeId="'+result.levelList[i].levelList[j].datesList[k].id+'">'+result.levelList[i].levelList[j].datesList[k].recentAbcent+'</span>&nbsp;&nbsp;<small style="color:green;text-decoration:none!important;">'+result.levelList[i].levelList[j].datesList[k].abcentPerc+' %</small></td>';
												
												str+='<td>'+result.levelList[i].levelList[j].datesList[k].recentNonInvitee+'</td>';
												
												str+='<td>'+result.levelList[i].levelList[j].datesList[k].recentImagesCnt+'</td>';
											str+='</tr>';
										}
									}else{
										str+='<tr>';
												str+='<td> - </td>';
												str+='<td> - </td>';
												str+='<td> - </td>';
												str+='<td> - </td>';
												str+='<td> - </td>';
												str+='<td> - </td>';
												str+='<td> - </td>';
											str+='</tr>';
									}
								}	
							}
						str+='</table>';
					str+='</div>';
				str+='</div>';
			}
	
	$("#openPostDetailsModalDivId").html(str);
	var colorsArr = ['#06D7A7','#0888DE','#994100','#AFCE69']
		for(var i in result.levelList)
		{
			$("#specialTotalMeetingsExpand"+i).highcharts({
				colors:['#3BB878'],
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: 0,
					plotShadow: false
				},
				title: {
					text: ''+result.levelList[i].totalMeetings+'',
					align: 'center',
					verticalAlign: 'middle',
					y: 10,
					style: {
						color: '#000',
						font: 'bold 14px "Lato", sans-serif',
					}
				},
				tooltip: {
					enabled: false,
				},
				plotOptions: {
					pie: {
						dataLabels: {
							enabled: false,
							distance: -10,
							style: {
								fontWeight: 'bold',
								color: 'white'
							}
						},
						size:"170%",
						startAngle: -90,
						endAngle: 90,
						center: ['50%', '80%']
					}
				},
				series: [{
					type: 'pie',
					name: 'Total Meetings',
					innerSize: '50%',
					data: [
						['Total Meetings',   result.levelList[i].totalMeetings],
					]
				}]
			});
			var categoriesArr = ['Invitees','Late','Absent','Non-invitee'];
			var mainArr = [{"y":result.levelList[i].inviteeAttendedCount},{"y":result.levelList[i].lateCount},{"y":result.levelList[i].absentCount},{"y":result.levelList[i].nonInviteesCount}];
			$("#specialAttendedMeetingsExpand"+i).highcharts({
				colors:['#3BB878','#E69C64','#ED5C5C','#259381'],
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: categoriesArr,
					type: 'category',
					labels: {
						formatter: function() {
							return this.value.toString().substring(0, 5)+'';
							
						},
					},
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					labels: {
						enabled:true
					},
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						},
					}
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';
							$.each(this.points, function () {
							if(this.series.name != "Series 1")  
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								this.y/* +' - ' +
								(Highcharts.numberFormat(this.percentage,1)+'%'); */
						});
						return s;
					},
					shared: true
				},
				legend: {   
						enabled: false,				
					},				
					plotOptions: {
						column: {
							stacking: 'normal',  
							dataLabels:{
								enabled: false,
								formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return (this.y);
									}
								}
							},
						},
					},
				series: [{
					name: "count",
					data: mainArr,
					colorByPoint: true
				}]
			});
			var specialInviteesMeetingsCatArr = ['Total Invited','Total Attended'];
			var specialInviteesMeetingsArr = [{"y":result.levelList[i].invitedCount},{"y":result.levelList[i].inviteeAttendedCount}];
			$("#specialInviteesMeetingsExpand"+i).highcharts({
				colors:['#D7AD06'],
				chart: {
					type: 'bar'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: specialInviteesMeetingsCatArr,
					type: 'category',
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					labels: {
						enabled:false
					},
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						},
					}
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';
							$.each(this.points, function () {
							if(this.series.name != "Series 1")  
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								this.y/* +' - ' +
								(Highcharts.numberFormat(this.percentage,1)+'%'); */
						});
						return s;
					},
					shared: true
				},
				legend: {   
						enabled: false,				
					},				
					plotOptions: {
						bar: {
							stacking: 'normal',  
							dataLabels:{
								enabled: false,
								formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return (this.y);
									}
								}
							},
						},
					},
				series: [{
					name: "count",
					data: specialInviteesMeetingsArr,
					colorByPoint: true
				}]
			});
		}
	}
}
var globalMeetingMembersResult = '';
function getLocationWiseMeetingInviteeMembers(partyMeetingMainTypeId,partyMeetingTypeId,partyMeetingId,isNonInvitee,desgSearchRequired,searchDesignation,position,status,sessionTypeId){
	
	$("#meetingMemDetailsBodyId").html(spinner);
	jsObj={
		
		locationTypeId			:locationLevelId,
		locationValues			:userAccessLevelValuesArray,
		fromDate 				:customStartMeetingsDate,
		toDate 					:customEndMeetingsDate,
		partyMeetingMainTypeId	:partyMeetingMainTypeId,
		partyMeetingTypeId		:partyMeetingTypeId,
		includePastMeetings		:"",
		partyMeetingId			:partyMeetingId,
		status:status,
		sessionTypeId:sessionTypeId
	}
	$.ajax({
		type : "GET",
		url : "getLocationWiseMeetingInviteeMembersAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			globalMeetingMembersResult = result;
			buildMeetingMemberDtls(globalMeetingMembersResult,partyMeetingMainTypeId,partyMeetingTypeId,partyMeetingId,isNonInvitee,desgSearchRequired,searchDesignation,position);	
		}else{
			$("#meetingMemDetailsBodyId").html("No Data Available");
		}
			
	});
}
	$(document).on("click",".getCmtMemDtls,.getCmtMemDtlsDesgClick",function(){
		
		var desgSearchRequired = $(this).attr("attr_search"); 
		var position = $(this).attr("attr_position"); 
		var partyMeetingMainTypeId = $(this).attr("attr_partyMeetingMainTypeId"); 
		var partyMeetingTypeId = $(this).attr("attr_partyMeetingTypeId"); 
		var partyMeetingId = $(this).attr("attr_party_meeting_id"); 
		var status =$(this).attr("attr_click_type");
		var sessionTypeId = $(this).attr("attr_session_typeId"); 
		
		var searchDesignation ="";
		if(desgSearchRequired == "required"){
			searchDesignation = $(this).attr("attr_desg_name"); 
		}else{
			globalMeetingMembersResult = '';
		}
		var isNonInvitee = $(this).attr("attr_non_invitee");
		
		$("#meetingMemDetailsId").modal("show");     
		$("#meetingMemDetailsBodyId").html(spinner);
		
		if(desgSearchRequired == "notrequired"){
			$(".memberMeetingCls li").removeClass("active");
			getLocationWiseMeetingInviteeMembers(partyMeetingMainTypeId,partyMeetingTypeId,partyMeetingId,isNonInvitee,desgSearchRequired,searchDesignation,position,status,sessionTypeId)
		}else{
			$(".memberMeetingCls li").removeClass("active");
			$(this).parent("li").addClass("active");
			buildMeetingMemberDtls(globalMeetingMembersResult,partyMeetingMainTypeId,partyMeetingTypeId,partyMeetingId,isNonInvitee,desgSearchRequired,searchDesignation,position);
		}
	});

	
	function buildMeetingMemberDtls(result,partyMeetingMainTypeId,partyMeetingTypeId,partyMeetingId,isNonInvitee,desgSearchRequired,searchDesignation,position){	
		var str = '';
		//Building Summary
		if(position == "overview"){
			if(result[0].publicRepDesgList != null && result[0].publicRepDesgList.length > 0 ){
				str+='<div class="col-sm-12">';
				str+='<h3 class="panel-title">DESIGNATIONS SUMMARY</h3>';
					str+='<div class="scrollerMeetingDiv">';
						str+='<ul class="m_top10 list-inline memberMeetingCls">';
							if(result[0].publicRepDesgList != null && result[0].publicRepDesgList.length > 0){
								 for(var i in result[0].publicRepDesgList){
									str+='<li class="text-capital m_top10" style="margin-left:10px;">';
										str+='<h5><span class ="getCmtMemDtlsDesgClick" attr_search="required" attr_position="'+position+'" attr_non_invitee="'+isNonInvitee+'"  attr_desg_name="'+result[0].publicRepDesgList[i].name+'" style="cursor:pointer;">'+result[0].publicRepDesgList[i].count+'</span>&nbsp;<span class="f-12">'+result[0].publicRepDesgList[i].name+'</span></h5>';
									str+='</li>';
								 }
							}
						str+='</ul>';
					 str+='</div>';
					 str+='</div>';
	      }
		}
		
		
	 //BULDING MEMBERS
		str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
		str+='<h3 class="panel-title">Details</h3>';
		  str+='<div class="table-responsive">';
		  str+='<table class="table table_custom table-bordered m_top10" id="cmtMemberDtlsTableId">';
			str+='<thead>';
				str+='<tr>';
					str+='<th rowspan="3" style="vertical-align:middle;">District</th>';
					str+='<th rowspan="3" style="vertical-align:middle;">Leader Name</th>';
					str+='<th rowspan="3" style="vertical-align:middle;">Designation</th>';
					str+='<th rowspan="3" style="vertical-align:middle;">Mobile No</th>';
					str+='<th rowspan="3" style="vertical-align:middle;">Total Invitations</th>';
					str+='<th rowspan="3" style="vertical-align:middle;">Total Present</th>';
					str+='<th rowspan="3" style="vertical-align:middle;">Total Absent</th>';
					str+='<th rowspan="3" style="vertical-align:middle;">Total Non-Invitee</th>';
					for(var i in result[0].subList1){
						if(i == 0){
							str+='<th colspan="'+result[0].subList1[i].idnameList.length+'" style="vertical-align:middle;">Recent Meeting</th>';
						}else{
							str+='<th colspan="'+result[0].subList1[i].idnameList.length+'" style="vertical-align:middle;">Past Meetings</th>';
						}
					}
				str+='</tr>';
				str+='<tr>';
					for(var i in result[0].subList1){
						if(i == 0){
							str+='<th colspan="'+result[0].subList1[i].idnameList.length+'" style="vertical-align:middle;">'+(result[0].subList1[i].dateStr).substring(0,10)+'</th>';
						}else{
							str+='<th colspan="'+result[0].subList1[i].idnameList.length+'" style="vertical-align:middle;">'+(result[0].subList1[i].dateStr).substring(0,10)+'</th>';
						}
					}
				str+='</tr>';
				str+='<tr>';
					for(var i in result[0].subList1){
						for(var j in result[0].subList1[i].idnameList){
							str+='<th style="vertical-align:middle;">'+result[0].subList1[i].idnameList[j].name+'</th>';
						}
						
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			
			for(var i in result){
				var build = false;
				if(desgSearchRequired == "required"){
				  if(result[i].subList != null && result[i].subList.length > 0){
						for( var j in result[i].subList){
							if(result[i].subList[j].trim().toUpperCase() == searchDesignation.trim().toUpperCase()){
								build = true;
								break;
							}
						}
					}
			    }else{
					build = true;
				}
				if(build){
				  //block
				if(result[i].subList1 !=null && result[i].subList1.length>0){
						str+='<tr>';
						if(result[i].districtName != null && result[i].districtName.length > 0){
							str+='<td>'+result[i].districtName+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].name != null && result[i].name.length > 0){
							str+='<td>'+result[i].name+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].status != null && result[i].status.length > 0){
							str+='<td>'+result[i].status+'</td>';
						}else{
							str+='<td>-</td>';   
						}
						if(result[i].mobileNo != null && result[i].mobileNo.length > 0){
							str+='<td>'+result[i].mobileNo+'</td>';
						}else{
							str+='<td>-</td>';  
						}
						str+='<td>'+result[i].inviteeAttnd+'</td>'; 
						str+='<td>'+result[i].actualCount+'</td>'; 
						str+='<td>'+result[i].availableCount+'</td>'; 
						if(result[i].actualCount>result[i].inviteeAttnd){
							var nonInviteesCount = result[i].actualCount - result[i].inviteeAttnd;
							str+='<td>'+nonInviteesCount+'</td>'; 
						}else{
							str+='<td>0</td>'; 
						}
						if(result[i].subList1 !=null && result[i].subList1.length>0){
							for(var j in result[i].subList1){
								if(result[i].subList1[j].idnameList !=null && result[i].subList1[j].idnameList.length>0){
									for(var k in result[i].subList1[j].idnameList){
										if(result[i].subList1[j].idnameList[k].status == "intime"){
											str+='<td class="text-success">Y('+(result[i].subList1[j].idnameList[k].dateStr).substring(11,16)+')</td>';
										}else if(result[i].subList1[j].idnameList[k].status == "late"){
											str+='<td class="text-danger">Y('+(result[i].subList1[j].idnameList[k].dateStr).substring(11,16)+')</td>';
										}else if(result[i].subList1[j].idnameList[k].status == "abscent"){  
											str+='<td>N</td>';  
										}else{  
											str+='<td>'+result[i].subList1[j].idnameList[k].status+'</td>';             
										}
									}
								 }else{
									str+='<td> - </td>';
								} 
							}
						}
				  
				  str+='</tr>';	
				}				  
				}
			}
		   str+='</tbody>';
		   str+='</table>';
		str+='</div>';
		str+='</div>';
		
		if(position == "overview"){
			$("#meetingMemDetailsBodyId").html(str);
			$("#cmtMemberDtlsTableId").dataTable();   
		}
		if(result[0].publicRepDesgList.length > 20){
			$(".scrollerMeetingDiv").mCustomScrollbar({setHeight: '290px'});
		}
	   
	}

	//getCategoryWiseGenderCount();
	function getCategoryWiseGenderCount(){
		var locationScopeId=4;
		var locationValuesArr=[];
		locationValuesArr.push(4);
		var enrollmentYearIdsArr=[];
			enrollmentYearIdsArr.push(1);
			enrollmentYearIdsArr.push(2);
		    enrollmentYearIdsArr.push(4);
		jsObj={
			locationScopeId: locationScopeId,
			locationValuesArr:	locationValuesArr,
			enrollmentYearIdsArr:enrollmentYearIdsArr
		}
		$.ajax({
			type : "POST",
			url : "getCategoryWiseGenderCountAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
	    }).done(function(result){ 
			console.log(result);
		});	
	}
function buildlevelWiseBlockDetails(meeting_levelId,partyMeetingTypeId,divId){
	$("#openPostDetailsModalDivId").html(spinner);
	var levelArr=[];
	if(locationLevelId == 2){
		if(meeting_levelId == "3"){
			levelArr=["district","parliament","constituency"]
		}else if(meeting_levelId == "4"){
			levelArr=["district","parliament","constituency","mandal"]
		}else if(meeting_levelId == "7"){
			levelArr=["district","parliament","constituency","mandal","panchayat"]
		}
	}else if(locationLevelId == 3){
		if(meeting_levelId == "3"){
			levelArr=["district","parliament","constituency"]
		}else if(meeting_levelId == "4"){
			levelArr=["district","parliament","constituency","mandal"]
		}else if(meeting_levelId == "7"){
			levelArr=["district","parliament","constituency","mandal","panchayat"]
		}
	}else if(locationLevelId == 4){
		if(meeting_levelId == "3"){
			levelArr=["constituency"]
		}else if(meeting_levelId == "4"){
			levelArr=["constituency","mandal"]
		}else if(meeting_levelId == "7"){
			levelArr=["constituency","mandal","panchayat"]
		}
	}else if(locationLevelId == 5){
		if(meeting_levelId == "4"){
			levelArr=["mandal"]
		}else if(meeting_levelId == "7"){
			levelArr=["mandal","panchayat"]
		}
	}else if(locationLevelId == 6){
		if(meeting_levelId == "7"){
			levelArr=["panchayat"]
		}
	}else if(locationLevelId == 10){
		if(meeting_levelId == "3"){
			levelArr=["constituency"]
		}else if(meeting_levelId == "4"){
			levelArr=["constituency","mandal"]
		}else if(meeting_levelId == "7"){
			levelArr=["constituency","mandal","panchayat"]
		}
	}
	
	var collapse='';
	collapse+='<section>';
		collapse+='<div class="row">';
		collapse+='<div class="col-sm-12">';
			for(var i in levelArr)
			{
				collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelArr[i]+'" role="tablist" aria-multiselectable="true">';
					collapse+='<div class="panel panel-default panel-black">';
						collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelArr[i]+'">';
							if(i == 0)
							{
								collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelArr[i]+'">';
							}else{
								collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelArr[i]+'">';
							}
							collapse+='<h4 class="panel-title text-capital" style="color:#fff;">'+levelArr[i]+' level</h4>';
								
							collapse+='</a>';
						collapse+='</div>';
						if(i == 0)
						{
							collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelArr[i]+'">';
						}else{
							collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelArr[i]+'">';
						}
						
							collapse+='<div class="panel-body">';
							/* if(levelArr[i] == "district"){
								collapse+='<ul class="switch-btn-New" role="tabSwitch">';
									collapse+='<li class="active activeCls" attr_val="district" attr_meeting_levelId="4" attr_partyMeetingTypeId="'+partyMeetingTypeId+'">District</li>';
									collapse+='<li  attr_val="parliament" attr_meeting_levelId="4" attr_partyMeetingTypeId="'+partyMeetingTypeId+'">Parliament</li>';
								collapse+='</ul>';
							} */
								collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelArr[i]+'" class="m_top10"></div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			}
		collapse+='</div>';
		collapse+='</div>';
		collapse+='</section>';
	
	$("#openPostDetailsModalDivId").html(collapse);
	for(var i in levelArr){
		getAreaWisePartyMeetingsDetailsAction(levelArr[i],meeting_levelId,partyMeetingTypeId,divId);
	}	
}
$(document).on("click","[role='tabSwitch'] li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");	
	var meeting_levelId = $(this).attr("attr_meeting_levelId");
	var partyMeetingTypeId = $(this).attr("attr_partyMeetingTypeId");
	var searchType = $(this).attr("attr_val");
	getAreaWisePartyMeetingsDetailsAction(searchType,meeting_levelId,partyMeetingTypeId,"meeting");
});
function getAreaWisePartyMeetingsDetailsAction(searchType,meeting_levelId,partyMeetingTypeId,divId){
	
	$("#"+divId+searchType).html(spinner);
 	
	 
	var date1 = customStartMeetingsDate.split('/');
	var date2 = customEndMeetingsDate.split('/');
	var startDate = date1[0]+'-'+date1[1]+'-'+date1[2]
	var endDate = date2[0]+'-'+date2[1]+'-'+date2[2]
		
	  var jsObj={
		"locationScopeId"	:locationLevelId,
 		"locationValue"		:userAccessLevelValuesArray,
 		"startDate"			:startDate,
 		"endDate"			:endDate,
 		"meetingLevelId"	:meeting_levelId,
 		"meetingTypeId"		:0,
 		"meetingMainTypeId"	:1,
		"searchFor"			:searchType
	  }
 	   $.ajax({
 		  type : "POST",
 		  url : "getAreaWisePartyMeetingsDetailsAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			if(result !=null && result.length>0){
				buildlevelWiseMeetingDetails(result,searchType,meeting_levelId,partyMeetingTypeId,divId);
			}else{
				$("#"+divId+searchType).html("<p>No data available</p>");
			}
 		});
  }
  
function buildlevelWiseMeetingDetails(result,searchType,meeting_levelId,partyMeetingTypeId,divId){
	
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table_custom" id="dataTable'+searchType+'" style="width:100%">';
			str+='<thead>';
			str+='<tr>';
				if(searchType == "district"){
					str+='<th rowspan="2">District Name</th>';
				}else if(searchType == "constituency"){
					str+='<th rowspan="2">Constituency</th>';
				}else if(searchType == "mandal"){
					str+='<th rowspan="2">Mandal</th>';
				}else if(searchType == "panchayat"){
					str+='<th rowspan="2">Panchayat</th>';
				}else if(searchType == "parliament"){
					str+='<th rowspan="2">Parliament</th>';
				}
				
				str+='<th rowspan="2">Total Meetings<br/> Every Month</th>';
				
				for(var i in result[0].yearWiseMeetingsCount){
					if(i==0){
						str+='<th colspan="5">Overall</th>';
					}else{
						str+='<th colspan="4">'+result[0].yearWiseMeetingsCount[i].monthName+' '+result[0].yearWiseMeetingsCount[i].year+'</th>';
					}
					
				}
			str+='</tr>';
			str+='<tr>';
				
				for(var i in result[0].yearWiseMeetingsCount){
					if(i==0){
						str+='<th>Total</th>';
						str+='<th>Yes</th>';
						str+='<th>No</th>';
						str+='<th>Maybe</th>';
						str+='<th>Not Updated</th>';
					}else{
						str+='<th>Total</th>';
						str+='<th>Yes</th>';
						str+='<th>No</th>';
						str+='<th>Maybe</th>';
						str+='<th>Not Updated</th>';
					}
					
				}	
			str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			
				for(var i in result){
					
					str+='<tr>';
						str+='<td>'+result[i].locationName+'</td>';
						str+='<td>'+result[i].total+'</td>';
						for(var j in result[i].yearWiseMeetingsCount){
							str+='<td>'+result[i].yearWiseMeetingsCount[j].total+'</td>';
							str+='<td>'+result[i].yearWiseMeetingsCount[j].yesCount+'</td>';
							str+='<td>'+result[i].yearWiseMeetingsCount[j].noCount+'</td>';
							str+='<td>'+result[i].yearWiseMeetingsCount[j].mayBeCount+'</td>';
							str+='<td>'+result[i].yearWiseMeetingsCount[j].notUpDatedCount+'</td>';
						}
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#"+divId+searchType).html(str);
	$("#dataTable"+searchType).dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
}