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
var userAccessLevelValuesArray=[];
var commitessArr=["mandalLevelGraph","villageLevelGraph","affMandalLevelGraph","affVillageLevelGraph"];
var grivanceIdsArr=["grivanceId","trustId"];
var grivanceColorObj={"APPROVED":"#2DCC70","COMPLETED":"#449C43","IN PROGRESS":"#FFB84F","NOT ELIGIBLE":"#C0392B","NOT POSSIBLE":"#EF8379","NOT VERIFIED":"#31708F"}
var insuranceColorObj={"Waiting For Documents":"#2DCC70","Documents Submitted In Party":"#449C43","Forwarded to Insurance":"#FFB84F","Closed at Insurance":"#8F43AF","Closed at Party":"#9B88B3","Approved - Compensated":"#2BCD72","Closed Letters":"#32708F","Account Rejected":"#65CBCC"}
var electionTypeVal = [1,2,3,4];
var defaultAlertCategoryIds=[1];
var electionYrVal = [];
var electionSubTypeArr=["MAIN"];
var electionYearsSubTypeArr=["MAIN","BYE"];
//Tours And Meetings And Alerts Dates Start 
var customStartATMDate = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
var customEndATMDate = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
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
		globalboardLevelId =2;
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
		startDate: customStartATMDate,
        endDate: customEndATMDate,
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	});
	//Meetings End
	
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
	function getDay(){
		var date = new Date();
		var dd = date.getDate(); 
		return dd;
	}
	
	//Tours End
	
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
	//Alert End
	
}
function onLoadAjaxCalls()
{	
	$("#enrolmentYears").chosen();
	//Enrolment Years
	getEnrollmentIds();
	//Publications	
	getPublications();
	
	//Committee
	getLocationWiseCommitteesCount(2);
	//Meetings
	getLocationWiseMeetingsCount();
	//getLocationWiseMeetingsCountDetails(1);
	//getLocationWiseMeetingsCountDetails(2);
	 //candidate Profiles 1st block
	if(locationLevelId == "2"){
		getPartyWiseMPandMLACandidatesCounts();
	}else{
		 $("#statelevelMPMLAId").html(" ");
	}
	getCandidateAndPartyInfoForConstituency();
	
	 //Second Block
	if(locationLevelId == "2"){
		getCountsForStateLevel();
	}else{
		 $("#statelevelWiseCountDivId").html(" ");
		 getCountsForConstituency();
	}
	
	//Assembly Block
	getElectionTypes("onload");
	
	if(locationLevelId == '4'){
		$(".assemblyElectionBlockCls").show();
		getDetailedElectionInformaction();
	}else{
		$(".assemblyElectionBlockCls").hide();
	}
	
	//Constituency Voter Information
	getVotersAndcadreAgeWiseCount(22,4);
	
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
	getLocationWiseGrivanceTrustStatusCounts("3");
	getLocationWiseInsuranceStatusCount("3");
	
	// Nominated Posts
	getPositionWiseMemberCount();
	getNominatedPostApplicationDetails();
	getNominatedPostStatusWiseCount();
	getLevelWisePostsOverView(); 
	
	//Alerts
	getTotalAlertDetailsForConstituencyInfo(defaultAlertCategoryIds);
	getDesignationWiseAlertsOverview(defaultAlertCategoryIds);
	//getAlertOverviewClick(defaultAlertCategoryIds);
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
			window.open('areaWiseGrievanceDashboardAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
		}else if(blockName == 'nominatedPosts'){
			window.open('areaWiseDashboardDetailedViewAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
		}else if(blockName == 'election'){
			window.open('areaWiseElectionDashboardAction.action?locationLevelId='+locationLevelId+'&userAccessLevelValuesArray='+userAccessLevelValuesArray+'&constituencyId='+constituencyId+'','constituency','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,resizable=yes,scrollbars=yes,top=600,left=600,width=1000,height=800');
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
		if(levelName == 'parliament'){
			$("[menu-name="+levelName+"]").html($(this).html()+" "+("(PC)"));
		}else{
			$("[menu-name="+levelName+"]").html($(this).html());
		}
		
		//$("[menu-name="+levelName+"]").width(((length)*(10)));
		$("[levelId="+levelId+"]").removeClass("active");
		$(this).addClass("active");
		$("[menu-name="+levelName+"]").closest("li").show();
		//$("[menu-name="+levelName+"]").addClass("active");
		$("#getMenuLocations").attr("menu-location-"+levelName+"",locationId);
		
		$("#getMenuLocations").attr("menu-location-levelName",levelName);
		if(levelType == 'Municipality')
		{
			locationLevelId = 7;
			$("#getMenuLocations").attr("menu-location-levelId","7");
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
		/* if(levelId != '5' || levelId != 5)
		{
			menuCalls(levelId,locationId,'')
		}else{ */
			menuCalls(levelId,locationId,$("#constituencyMenu li.active").attr("menu-click"))
		//}	
	});
	//Meetings
	$('#dateRangeIdForMeetings').on('apply.daterangepicker', function(ev, picker) {
		customStartATMDate = picker.startDate.format('DD/MM/YYYY');
		customEndATMDate = picker.endDate.format('DD/MM/YYYY');
		getLocationWiseMeetingsCount();
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
	$(document).on("change","#enrolmentYearsGrievance",function(){
		getLocationWiseGrivanceTrustStatusCounts($(this).val());
		getLocationWiseInsuranceStatusCount($(this).val());
	});
	$(document).on("click","[menu-name]",function(e){
		e.stopPropagation();
		$(".menu-dropdown").show();
		//$("[menu-name]").removeClass("active");
		//$(this).addClass("active");
	});
	$(document).on("click",".menu-dropdown",function(e){
		e.stopPropagation();
	});
	$("#resetMenuOptions").click(function(){
		$(".districtMenuName,.parliamentMenuName,.constituencyMenuName,.mandalsMenuName,.panchayatMenuName").closest("li").hide();
		$("#constituencyMenu,#mandalMenu,#panchayatMenu").html(" ");
		$("[menu-type]").removeClass("active");
		$("#getMenuLocations").attr("menu-location-state","1")
							.attr("menu-location-district","")
							.attr("menu-location-constituency","")
							.attr("menu-location-mandal","")
							.attr("menu-location-panchayat","")
							.attr("menu-location-levelid","2")
							.attr("menu-location-parliament","")
							.attr("menu-location-levelname","state");
		onLoadLocValue();
	});
	$(document).on("click",function(e){
		$(".menu-dropdown").hide();
		$("[menu-name]").removeClass("active");
	});
	$(document).on("change","[role='tabListMobile']",function(){
		var id = $('option:selected', this).attr('tab_id');
		$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
		$("#"+id).addClass("active");
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
			getLocationWiseGrivanceTrustStatusCounts($("#enrolmentYearsGrievance").val());
			getLocationWiseInsuranceStatusCount($("#enrolmentYearsGrievance").val());
		}else if(blockName == 'meetings')
		{
			getLocationWiseMeetingsCount();
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
			getElectionTypes();
			//getElectionYears();
			/* var partyIds = [872,362,1117,886,72,269,265,163,1887];
			getElectionInformationLocationWise(electionTypeVal,"voteShare",partyIds,electionSubTypeArr,electionYrVal); */
			if(locationLevelId == '4'){
				$(".assemblyElectionBlockCls").show();
				getDetailedElectionInformaction();
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
			 electionYearsSubTypeArr = [];
			 $(".checkedMainByeType").each(function(){
				if ($(this).is(':checked')){
					 electionYearsSubTypeArr[j++] = $(this).val();
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
			}else if(value == 0){
				if ($(this).is(':checked')){
					$(".checkUncheckCls").prop("checked",true);
					$(".electionTypeWiseCls").prop("checked",true);
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
	$(document).on("change","#enrollmentvoterId",function(){
		var pubId = $("#publicationChangeId").val();
		var enrollmentId = $("#enrollmentvoterId").val();
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
		
		getCasteGroupNAgeWiseVoterNCadreCounts(casteGroupId,"tabClick",casteName,publicationId,enrollmentId,$("[active-type='casteSorting'] li.active").attr("type"));
	});
	$(document).on("change","#publicationCasteId",function(){
		var publicationId =  $(this).val();
		var enrollmentId = $("#enrollmentCasteId").val();
		
		getCasteGroupNAgeWiseVoterNCadreCounts(0,"onload","All",publicationId,enrollmentId,$("[active-type='casteSorting'] li.active").attr("type"));
		getVotersCastGroupWiseCount(publicationId,enrollmentId);
	});
	$(document).on("change","#enrollmentCasteId",function(){
		var enrollmentId =  $(this).val();
		var publicationId = $("#publicationCasteId").val();
		
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
		getCasteNAgeWiseVoterNCadreCounts(casteGroupId,casteId,enrollmentId,publicationId,casteName)
		
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
	
	$(document).on("click",".openPostClickCls",function(){
		var deptId =  $(this).attr("attr_department_id");
		var boardLevelId =  $(this).attr("attr_boardLevelId");
		var type =  $(this).attr("attr_type");
		var departmentName =  $(this).attr("attr_department_name");
		var statusIds =  $(this).attr("attr_board_statusIds");
		if(type == "open"){
			$("#openPostModal").modal("show");
			$("#openPostTitleId").html(departmentName+  "  Open Posts Details");
			getDepartmentWisePostAndApplicationDetails(deptId,boardLevelId,type);
		}else if(type == "goIssued"){
			$("#openPostModal").modal("show");
			$("#openPostTitleId").html(departmentName + "  G.O Issued Positions");
			getLevelWiseGoIssuedPostions(boardLevelId,statusIds);
			
		}else if(type == "department"){
			$("#departmentPostModal").modal("show");
			$("#departmentDetailsModalDivId").html(spinner);
			$("#deptHeadingId").html(departmentName+" Details");
			getDepartmentWisePostAndApplicationDetails(deptId,boardLevelId,type);
		}	
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
		//if(levelId != '9' || levelId != 9)
		//{
			levelId = parseInt(levelId) + 1;
		//}
		var menu='';
		menu+='<h4 class="panel-title text-capitalize"><b>'+divId+'</b></h4>';
		menu+='<div class="scroller'+divId+'">';
			menu+='<ul>';
				for(var i in result)
				{
					if(levelId != '6' || levelId != 6)
					{
						menu+='<li menu-type="'+result[i].name+'" menu-click="'+result[i].locationId+'" menu-levelname="'+divId+'" levelId="'+levelId+'" class="text-capitalize">'+result[i].locationName+'</li>';
					}else{
						menu+='<li menu-type="'+result[i].name+'" constituencyId="'+$("#constituencyMenu li.active").attr("menu-click")+'" class="text-capitalize" menu-levelname="'+divId+'" menu-click="'+result[i].locationId+'" levelId="'+levelId+'">'+result[i].locationName+'</li>';
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
$(window,document).on('resize', function(){
		responsiveTabs();
});
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
										parliament+='<img src="https://mytdp.com/images/cadre_images/'+result[0].subList1[i].education+'" class="media-object profile-image img-border" alt="profile" onerror="setDefaultImage(this);"/>';
										parliament+='<span class="border-image img-border">';
											parliament+='<img src="images/party_flags/'+result[0].subList1[i].partyFlag+'"  onerror="setDefaultImage(this);" alt="party"/>';
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
											parliament+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[0].subList1[i].tdpCadreId+'">View Candidate Profile</span>';
										}else{
											parliament+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[0].subList1[i].candidateId+'">View Candidate Profile</span>';
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
											assembly+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/cadre_images/'+result[i].assemblyCandidateInfo[0].education+'" class="media-object profile-image img-border" alt="profile"/>';
											assembly+='<span class="border-image img-border">';
												assembly+='<img onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].assemblyCandidateInfo[0].partyFlag+'" alt="party"/>';
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
												assembly+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[i].assemblyCandidateInfo[0].tdpCadreId+'">View Candidate Profile</span>';
											}else{
												assembly+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[i].assemblyCandidateInfo[0].candidateId+'">View Candidate Profile</span>';
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
									parliament+='<img src="https://mytdp.com/images/cadre_images/'+result[0].subList1[0].education+'" class="media-object profile-image img-border" alt="profile"  onerror="setDefaultImage(this);"/>';
									parliament+='<span class="border-image img-border">';
										parliament+='<img src="images/party_flags/'+result[0].subList1[0].partyFlag+'"  onerror="setDefaultImage(this);" alt="party"/>';
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
										parliament+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[0].subList1[0].tdpCadreId+'">View Candidate Profile</span>';
									}else{
										parliament+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[0].subList1[0].candidateId+'">View Candidate Profile</span>';
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
										parliament+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/cadre_images/'+result[i].assemblyCandidateInfo[0].education+'" class="media-object profile-image img-border" alt="profile"/>';
										parliament+='<span class="border-image img-border">';
											parliament+='<img  onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].assemblyCandidateInfo[0].partyFlag+'" alt="party"/>';
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
											parliament+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[i].assemblyCandidateInfo[0].tdpCadreId+'">View Candidate Profile</span>';
										}else{
											parliament+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[i].assemblyCandidateInfo[0].candidateId+'">View Candidate Profile</span>';
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
													stateLevel+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/cadre_images/'+result[i].cadreImage+'" class="media-object profile-image img-border" alt="profile"/>';
													stateLevel+='<span class="border-image img-border">';
													if(result[i].partyFlag !=null){
														
														stateLevel+='<img onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].partyFlag+'" alt="party"/>';
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
														stateLevel+='<span class="text-success text-capital cadreRedirectPage viewPageCls" attr_cadre_id="'+result[i].cadreId+'">View Candidate Profile</span>';
													}else{
														stateLevel+='<span class="text-success text-capital candidateRedirectPage viewPageCls" attr_candidate_id="'+result[i].condidateId+'">View Candidate Profile</span>';
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
												representative+='<img  onerror="setDefaultImage(this);" src="https://mytdp.com/images/cadre_images/'+result[0].list[i].list[j].education+'" class="media-object profile-image img-border" alt="profile"/>';
												representative+='<span class="border-image img-border">';
												if(result[0].list[i].list[j].partyFlag !=null){
													
													representative+='<img onerror="setDefaultImage(this);" src="images/party_flags/'+result[0].list[i].list[j].partyFlag+'" alt="party"/>';
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
							/* if(theadArr[i] != "total population"){
								
							} */
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
				buildCasteGroupNAgeWiseVoterNCadreCounts(result);
				buildCasteGroupWiseDetailsCounts(result,casteGroupId,casteName);
			}else if(type == "sortingType"){
				buildGraphForCaste(result);
			}else{
				buildCasteGroupWiseDetailsCounts(result,casteGroupId,casteName);
			}
			
		}
	});	
}
function buildCasteGroupNAgeWiseVoterNCadreCounts(result){
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
				str+='<td>'+result[i].ageRange+'</td>';
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
							str+='<div id="cadreInfoGraph'+i+'" style = "height:130px;"></div>';
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
				

				//var getWidth = $("#cadreInfoGraphBar").width();
			   // $("#cadreInfoGraphBar").css("width",getWidth);	
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
										str+='<td><p><img  onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList[i].organization+'.PNG" alt="party"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList[i].count+'</span></p></td>';
									}else if(result.coreDashBoardVOList[i].organization == "JANASENA"){
										str+='<td><p><img  onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList[i].organization.trim()+'.PNG" alt="party" />&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList[i].count+'</span></p></td>';
									}else{
										str+='<td><p><img onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList[i].organization+'.png" alt="party"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList[i].count+'</span></p></td>';
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
											str+='<td><p><img onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList1[i].organization+'.PNG" alt="party"/>&nbsp;&nbsp;&nbsp;<span>'+result.coreDashBoardVOList1[i].count+'</span></p></td>';
										}else if(result.coreDashBoardVOList1[i].organization == "JANASENA"){
											str+='<td><p><img onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList1[i].organization.trim()+'.PNG" alt="party" style="width: 30px; height: 30px;"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList1[i].count+'</span></p></td>';
										}else{
											str+='<td><p><img onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList1[i].organization+'.png" alt="party"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList1[i].count+'</span></p></td>';
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
							str+='<a role="button" class = "collapsed collapseIcon" data-toggle="collapse"  data-parent="#problemsCollapse" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
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
		fromDate :customStartATMDate,
		toDate :customEndATMDate
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
		
		for(var i in result){
				submittedCount = submittedCount+result[i].submitedLeaderCnt;
				totalNotSubmitted = totalNotSubmitted+result[i].notSubmitedLeaserCnt;
				totalSubmitted =totalSubmitted+result[i].totalSubmittedToursCnt;	
					
				for(var j in result[i].subList)
				{
						if(result[i].subList[j].isComplaince == "False"){
						totalNonCamplains = totalNonCamplains+1;
					}else if(result[i].subList[j].isComplaince == "True"){
						totalCamplains =totalCamplains+1;
					}
				}	
			}
		tottalCount =totalCamplains+totalNonCamplains;
		
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
				tableView+='<p class="pull-right f_12"><i class="glyphicon glyphicon-ok" style="color:#3DBC93;"></i> Complains<i class="glyphicon glyphicon-remove" style="color:#FF0000;margin-left:10px;"></i> Non Complains</p>';
				
				tableView+='<div class="col-sm-12">';
					tableView+='<div class="toursDesigCss">';
						tableView+='<ul class="toursSlickSlider list-inline m_0">';
						
								for(var i in result){
									var totalCnt= result[i].complainceCnt+result[i].nonComplainceCnt;
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
																	tableView+='<p class="f-12">Total('+totalCnt+' * '+result[0].toursMonthId+'M)</p>';
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
																   if (result[i].nonComplainceCnt != null && result[i].nonComplainceCnt>0) {
																	  		tableView+='<span class="toursCompNonCompClickCls" attr_designation_id="'+result[i].designationId+'" attr_tour_filter_type="nonComplaince" style="color: #337ab7;">'+result[i].nonComplainceCnt+'</span>';
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
																		tableView+='<td><i class="glyphicon glyphicon-remove" style="color:#FF0000;"></i></td>';
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
		locationTypeId	:locationLevelId,
		locationValue	:locationLevelVal,
		publicationDateId:22
	}
	 $.ajax({
      type : "POST",
      url : "getGovtSchemeWiseBenefitMembersCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result!=null && result.length>0){
			return buildTabs(result,locationLevelId,locationLevelVal);
		}else{
			$("#benefitsBlockId").html(noData);
		}
	});	
	function buildTabs(result,locationLevelId,locationLevelVal)
	{
		var navTabs = '';
		navTabs+='';
		navTabs+='<div class="col-sm-3 pad_right0">';
			navTabs+='<select class="form-control" role="tabListMobile">';
				for(var i in result)
				{
					navTabs+='<option tab_id="benefits'+result[i].id+'">'+result[i].name+' ('+result[i].totalCount+')</option>';
				}
				
			navTabs+='</select>';
			navTabs+='<ul class="nav nav-tabs nav-tabs-horizontal" role="tablist">';
				for(var i in result)
				{
					if(i == 0)
					{
						if(result[i].name !=null && result[i].name.length>20){
							navTabs+='<li class="active tooltipBeneCls"  data-toogle="tooltip" title="'+result[i].name+'" style="cursor:pointer;" data-placement="top" style="font-size:13px;cursor:pointer;"><a href="#benefits'+result[i].id+'" navTabs-click="'+result[i].id+'" aria-controls="benefits'+result[i].id+'" role="tab" data-toggle="tab" >'+result[i].name.substr(0,20)+'...<span class="pull-right">'+result[i].totalCount+'</span></a></li>';
						}else{
							navTabs+='<li class="active" style="font-size:13px;"><a href="#benefits'+result[i].id+'" navTabs-click="'+result[i].id+'" aria-controls="benefits'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+'<span class="pull-right">'+result[i].totalCount+'</span></a></li>';
						}
						
					}else{
						if(result[i].name !=null && result[i].name.length>20){
							navTabs+='<li class="tooltipBeneCls"  data-toogle="tooltip" title="'+result[i].name+'" style="cursor:pointer;" data-placement="top" style="font-size:13px;cursor:pointer;"><a href="#benefits'+result[i].id+'" navTabs-click="'+result[i].id+'" aria-controls="benefits'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name.substr(0,20)+'...<span class="pull-right">'+result[i].totalCount+'</span></a></li>';
						}else{
							navTabs+='<li class="tooltipBeneCls" style="font-size:13px;"><a href="#benefits'+result[i].id+'" navTabs-click="'+result[i].id+'" aria-controls="benefits'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+' <span class="pull-right">'+result[i].totalCount+'</span></a></li>';
						}
						
						
					}
				}
			navTabs+='</ul>';
		navTabs+='</div>';
		navTabs+='<div class="col-sm-9 pad_left0">';
			navTabs+='<div class="tab-content">';
				for(var i in result)
				{
					if(i == 0)
					{
						navTabs+='<div role="tabpanel" class="tab-pane active pad_10" id="benefits'+result[i].id+'"></div>';
					}else{
						navTabs+='<div role="tabpanel" class="tab-pane pad_10" id="benefits'+result[i].id+'"></div>';
					}
				}
			navTabs+='</div>';
		navTabs+='</div>';
		$("#benefitsBlockId").html(navTabs);
		$(".tooltipBeneCls").tooltip();
		responsiveTabs();
		getMandalWiseBenefitMembersCount(result[0].id)
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
						str+='<h3 class="" attr_boardLevelId="'+result[i].id+'" attr_name="'+result[i].name+'">'+result[i].count+'</h3>';
					str+='<p class="text-capitalize">Village/Ward</p>';
					}else{
						str+='<h3 class="positionLevelModalClick" attr_boardLevelId="'+result[i].id+'" attr_name="'+result[i].name+'">'+result[i].count+'</h3>';
					str+='<p class="text-capitalize">Village/Ward</p>';
					}
					
				}else if(result[i].name =="Mandal/Muncipality/Corporation"){
					if(locationLevelId ==2 || locationLevelId ==3 || locationLevelId ==10){
						str+='<h3 class="" attr_boardLevelId="'+result[i].id+'" attr_name="'+result[i].name+'">'+result[i].count+'</h3>';
					str+='<p class="text-capitalize">'+result[i].name+'</p>';
					}else{
						str+='<h3 class="positionLevelModalClick" attr_boardLevelId="'+result[i].id+'" attr_name="'+result[i].name+'">'+result[i].count+'</h3>';
					str+='<p class="text-capitalize">'+result[i].name+'</p>';
					}
					
				}else{
					str+='<h3 class="positionLevelModalClick" attr_boardLevelId="'+result[i].id+'" attr_name="'+result[i].name+'">'+result[i].count+'</h3>';
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
						str+='<li style="background-color:'+colorsArr[i]+'" class="f-12"><span class="statusBox" style="background-color:'+colors[i]+'" ></span>COMPLETED/G.O ISSUED<span class="count openPostClickCls" attr_boardLevelId="'+globalboardLevelId+'" attr_type="goIssued" attr_department_name = "overAll" attr_board_statusIds="0">'+result[i].count+'</span></li>';
					}else{
						if(result[i].name == "OPEN"){
							str+='<li style="background-color:'+colorsArr[i]+'" class="f-12"><span class="statusBox" style="background-color:'+colors[i]+'"></span>'+result[i].name+' POSTS<span class="count openPostClickCls" attr_department_id="0" attr_boardLevelId="'+globalboardLevelId+'" attr_type="open" attr_department_name = "">'+result[i].count+'</span></li>';
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
function getLocationWiseInsuranceStatusCount(yearId){
	$("#insuranceDetails").html(spinner);
	$("#insuranceTotalCount").html("Total Count - ");
	var jsObj={
			"fromDate" 			: "",
			"toDate"			: "",
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId
		}
	 $.ajax({
      type : "POST",
      url : "getLocationWiseInsuranceStatusCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	if(result != null){
    		$("#insuranceDetails").css("height","300px");
			return buildGraph(result);
		}else{
			$("#insuranceDetails").html("<h4 class='text-center'>NO DATA AVAILABLE</h4>");
			$("#insuranceDetails").removeAttr(style);
		}
	});	
	function buildGraph(result){
		if(result !=null){
				var mainArr=[];
				var insuranceTotalCount = 0;
				for(var j in result.subList){
					var colorsId = insuranceColorObj[result.subList[j].name.trim()];
					var obj1 = {
						name: result.subList[j].name,
						y:result.subList[j].count,
						color:colorsId
					}
					mainArr.push(obj1);
					insuranceTotalCount = insuranceTotalCount + result.subList[j].count;
				}
				$("#insuranceTotalCount").html("Total Count - "+insuranceTotalCount);
				var id = 'insuranceDetails';
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
						innerSize: 70,
						depth: 50,
						dataLabels: {
							enabled: false,
							formatter: function() {
								return (this.y) + ' %';
							},
							distance: -20,
							color:'#333'
						},
						showInLegend: legend
					},
				};
				var legend = {
					enabled: true,
					align: 'left',
					verticalAlign: 'bottom',
					useHTML: true,
					labelFormatter: function() {
						return '<span style="color:'+this.color+'">'+this.name + '-' + this.y + '';
					}
				};
				var data = [{
					name: '',
					data: mainArr
				}];
				highcharts(id,type,data,plotOptions,title,tooltip,legend);
			
		}
	}
}
function getLocationWiseGrivanceTrustStatusCounts(yearId){
	$("#grivanceId0,#grivanceId1").html(spinner);
	$("#totalGrievanceCount,#NtrTrustTotalCount").html('Total Count - ');
	var jsObj={
			"fromDate" : "",
			"toDate":"",
			"locationTypeId" : locationLevelId,
			"locationValuesArr" :userAccessLevelValuesArray,
			"year": yearId
		}
	$.ajax({
		type : "POST",
		url : "getLocationWiseGrivanceTrustStatusCountsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			$("#grivanceId0,#grivanceId1").css("height","300px");
			return buildGraph(result);
		}else{
			if(result !=null && result.length>0){
				for(var i in result){
					if(typeof result[i].grivenceType == "undefined" || typeof result[i].grivenceType === undefined){
						$("#grivanceId0,#grivanceId1").html(noData);
						$("#grivanceId0,#grivanceId1").removeAttr("style");
					}
				}
			}else{
				$("#grivanceId0,#grivanceId1").html(noData);
				$("#grivanceId0,#grivanceId1").removeAttr("style");
			}
			
		}
	});

	function buildGraph(result){
		if(result !=null && result.length>0){
			for(var i in result){
				var mainArr=[];
				if(result[i].grivenceType == "Grivence"){
					var totalGrievanceCount = 0;
					for(var j in result[i].subList){
						var colorsId = grivanceColorObj[result[i].subList[j].name.trim()];
						var obj = {
							name: result[i].subList[j].name,
							y:result[i].subList[j].count,
							color:colorsId
						}
						mainArr.push(obj);
						totalGrievanceCount = totalGrievanceCount + result[i].subList[j].count;
					}
					$("#totalGrievanceCount").html('Total Count - '+totalGrievanceCount);
				}else if(result[i].grivenceType == "NTR Trust"){
					var NtrTrustTotalCount = 0;
					for(var j in result[i].subList){
						var colorsId = grivanceColorObj[result[i].subList[j].name.trim()];
						var obj1 = {
							name: result[i].subList[j].name,
							y:result[i].subList[j].count,
							color:colorsId
						}
						mainArr.push(obj1);
						NtrTrustTotalCount = NtrTrustTotalCount + result[i].subList[j].count;
					}
					$("#NtrTrustTotalCount").html('Total Count - '+NtrTrustTotalCount);
				}
				
				var id = 'grivanceId'+i;
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
						innerSize: 70,
						depth: 50,
						dataLabels: {
							enabled: false,
							formatter: function() {
								return (this.y) + ' %';
							},
							distance: -10,
							color:'#333'
						},
						showInLegend: legend
					},
				};
				var legend = {
					enabled: true,
					layout: 'vertical',
					align: 'center',
					verticalAlign: 'bottom',
					useHTML: true,
					
					labelFormatter: function() {
						return '<div><span style="color:'+this.color+'">'+this.name + '-' + this.y + '</span></div>';
					}
				};
				var data = [{
					name: '',
					data: mainArr
				}];
				highcharts(id,type,data,plotOptions,title,tooltip,legend);
			}
		}
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
		str+='<option value = "0">Select Publication</option>';
		for(var i in result){
			if(result[i].id == "22"){
				str+='<option value = "'+result[i].id+'" selected>'+result[i].name+'</option>';
			}else{
				str+='<option value = "'+result[i].id+'">'+result[i].name+'</option>';
			}
			
		}
		str+='</select>';
		
		$("#publicationsDivId").html(str);
		$("#publicationCasteId").html(str);
		$(".chosen-select").chosen();
		
	}
}

function getDetailedElectionInformaction(){
	$("#assemblyElectionGraphDetails,#assemblyElectionDetails").html(spinner);
	jsObj={
	  	constituencyId: constituencyId
    }
    $.ajax({
      type : "GET",
      url : "getDetailedElectionInformactionAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildAssemblyResultsTable(result)
		}else{
			$("#assemblyElectionGraphDetails,#assemblyElectionDetails").html(noData);
		}
	});
	
	function buildAssemblyResultsTable(result)
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
		str+='<table class="table table-noborder" id="dataTableAssemblyElecBlock">';
			str+='<thead class="bg-DD text-capitalize">';
				str+='<th style="vertical-align: middle;">Year</th>';
				str+='<th style="vertical-align: middle;"> <img src="images/constituencyPage/green-hand.png" alt="green-hand"/></th>';
				str+='<th style="vertical-align: middle;">Won Candidate</th>';
				str+='<th style="vertical-align: middle;">Majority Of Votes</th>';
				str+='<th style="vertical-align: middle;"><img src="images/constituencyPage/red-hand.png" alt="red-hand"/></th>';
				str+='<th style="vertical-align: middle;">Lost Candidate</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].electionYear+'</td>';
					if(result[i].candidateResultsVO.partyShortName == "TDP" || result[i].candidateResultsVO.partyShortName == "YSRC"){
						str+='<td><img style="height:25px;width:25px;" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'"/></td>';
					}else{
						str+='<td><img style="height:25px;width:25px;" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'"/></td>';
					}
					
					str+='<td>'+result[i].candidateResultsVO.candidateName+'</td>';
					str+='<td>'+result[i].candidateResultsVO.votesEarned+'</td>';
					if(result[i].candidateOppositionList[0].partyShortName == "TDP" || result[i].candidateOppositionList[0].partyShortName == "YSRC"){
						str+='<td><img style="height:25px;width:25px;" src="images/party_flags/'+result[i].candidateOppositionList[0].partyShortName+'.PNG" alt="'+result[i].candidateOppositionList[0].partyShortName+'"/></td>';
					}else{
						str+='<td><img style="height:25px;width:25px;" src="images/party_flags/'+result[i].candidateOppositionList[0].partyShortName+'.png" alt="'+result[i].candidateOppositionList[0].partyShortName+'"/></td>';
					}
					str+='<td>'+result[i].candidateOppositionList[0].candidateName+'</td>';
					
					
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
			"sDom": '<"top"iflp>rt<"bottom"><"clear">'
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
			return buildAlertsTable(result);
		}else{
			$("#alertsBlockDivId").html(noData);
		}
	});	
	function buildAlertsTable(result){
		var str='';
		
		str+='<div class="table-responsive">';
			str+='<table class="table-alerts">';
				str+='<tr>';
					str+='<td colspan="3" style="background-color:#F6F6F6;"><div class="media"><div class="media-left"><img src="coreApi/img/total_alerts.png" style="width:40px;"/></div><div class="media-body"><p>Total <span class="text-danger">Alerts</span></p><h4>'+result.totalAlertCount+'</h4></div></div></td>';
					if(result.subList !=null && result.subList.length>0){
						for(var i in result.subList)
						{
							str+='<td colspan="3"><h3>'+result.subList[i].status+'</h3></td>';
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
										str+='<h4 class="m_top5">'+result.alertTypeList[i].count+'&nbsp;&nbsp;<small style="font-size:10px;" class="text-success">'+result.alertTypeList[i].percentage+'%</small></h4>';
									}else{
										str+='<h4 class="m_top5"> - </h4>';
									}
									
									
								str+='</div>';
							str+='</div>';
						}
					}	
					str+='</td>';
					if(result.subList !=null && result.subList.length>0){
						for(var i in result.subList)
						{
							str+='<td colspan="3">'+result.subList[i].count+'<small class="pull-right text-success">'+result.subList[i].percentage+'</small></td>';
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
											str+='<div class="col-sm-6">'+result.subList1[i].status+'</div>';
											str+='<div class="col-sm-2">'+result.subList1[i].count+'</div>';
											str+='<div class="col-sm-4">'+result.subList1[i].percentage+'</div>';
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
												str+='<div class="col-sm-6">'+result.subList[i].subList[j].status+'</div>';
												str+='<div class="col-sm-2">'+result.subList[i].subList[j].count+'</div>';
												str+='<div class="col-sm-4">'+result.subList[i].subList[j].percentage+'</div>';
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
			str+='<ul class="alerts-status-list">';
				for(var i in result.impactScopeList)
				{
					str+='<li>';
						str+='<h4 class="panel-title">'+result.impactScopeList[i].status+'</h4>';
						str+='<p>'+result.impactScopeList[i].count+'&nbsp;&nbsp;<small class="f-10"> '+result.impactScopeList[i].percentage+'%</small></p>';
						for(var j in result.impactScopeList[i].subList){
							str+='<hr style="margin-top:8px;margin-bottom:8px"/>';
							str+='<p style="color:'+result.impactScopeList[i].subList[j].colour+';">'+result.impactScopeList[i].subList[j].status+' &nbsp;&nbsp;<span class="pull-right">'+result.impactScopeList[i].subList[j].count+'</span></p>';
						}
					str+='</li>';
				}
			str+='</ul>';
		}
		$("#alertsBlockDivId").html(str);
	}
}

function getEnrollmentIds(){
	var jsObj={
			
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
						if(result[i].id == "1" || result[i].id == "2" || result[i].id == "3" ||result[i].id == "4"){
							if(result[i].name == "Pancahat_Ward"){
								str+='<input value ="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">Ward</span>';
							}else{
								str+='<input value ="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
							}	
						}else{
							if(result[i].name == "Pancahat_Ward"){
								str+='<input value ="'+result[i].id+'" type="checkbox"  class="electionTypeWiseCls" /><span class="f-12">Ward</span>';
							}else{
								str+='<input value ="'+result[i].id+'" type="checkbox"  class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
							}	
						}
										
						str+='</label>';
					}
				str+='</div>';
				str+='</div>';
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-12">';
							str+='<div class="col-sm-2">';
								str+='<select class="" id="elctionBlockPartysId" multiple>';
									//str+='<option value="0" selected>All Parties</option>';
									for(var i in result[0].selectedCasteDetails){
										if(result[0].selectedCasteDetails[i].id == 163 || result[0].selectedCasteDetails[i].id == 265 || result[0].selectedCasteDetails[i].id == 269 || result[0].selectedCasteDetails[i].id == 662 || result[0].selectedCasteDetails[i].id == 872 || result[0].selectedCasteDetails[i].id == 886 || result[0].selectedCasteDetails[i].id == 1117  || result[0].selectedCasteDetails[i].id == 362 || result[0].selectedCasteDetails[i].id == 72 ){
											str+='<option value="'+result[0].selectedCasteDetails[i].id+'" selected>'+result[0].selectedCasteDetails[i].name+'</option>';
										}
										
									}
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-3" style="margin-left: 20px;">';
								str+='<select class="" id="elctionYearsBlockId" multiple>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<span id="electionTypeSpinnerId" style="margin-left:-22px;display:none;"><img  src="coreApi/img/search.gif" alt="search"  class="m_top10" /></span>';
								str+='<label class="pull-right">';
									str+='<input value ="MAIN" type="checkbox" name="optionsRadios1" class="checkedMainByeType" checked/><span class="f-12">Main Election</span>';
									str+='<input value ="BYE" type="checkbox" name="optionsRadios1"  class="checkedMainByeType f-12" style="margin-left: 10px;"  /><span class="f-12">Bye Election</span>';
								str+='</label>';
							str+='</div>';
							
							str+='<div class="col-sm-3">';
								str+='<label class="">';
									str+='<input value ="wonSeat" type="radio" name="optionsRadios" class="checkedType" /><span class="f-12">Won Seats</span>';
									str+='<input value ="voteShare" type="radio" name="optionsRadios"  class="checkedType" style="margin-left: 10px;" checked /><span class="f-12">Vote Share %</span>';
								str+='</label>';
							str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-12">';
						str+='<button class="btn btn-primary btn-xs electionDetailsCls pull-right" >Submit</button>';
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
			maxWidth: 300,
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
	for(var i in electionVal){
		if(electionVal[i] == 0){
			electionVal=[];
		}
	}
	
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
	
	var jsObj={
			fromDate 	  	:"",
			toDate		  	:"",
			locationId 		:locationLevelId,
			locationValue 	:locationLevelVal,
			electionScopeIds:electionVal,
			partyIds		:partyIds,
			electionYrs     :electionYrVal,
			electionSubTypeArr :electionSubTypeArr
			
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
		str+='<h5 style="color:#2B908F;" class="pull-right"><b>Vote Share %</b></h5>';	
				str+='<table class="table table-condensed table-hover m_top10  table-bordered table-striped" id="dataTableElecBlock">';
					str+='<thead>';
						str+='<tr>';
							str+='<th>Party</th>';
								for(var j in result[0].list){
									if(result[0].list[j].electionType == "Assembly"){
										str+='<th>'+result[0].list[j].electionYear+'   AC</th>';
									}else if(result[0].list[j].electionType == "Parliament"){
										str+='<th>'+result[0].list[j].electionYear+'   PC</th>';
									}else{
										str+='<th>'+result[0].list[j].electionYear+'   '+result[0].list[j].electionType+'</th>';
									}
									
								}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
							for(var i in result){
								str+='<tr>';
									str+='<td>'+result[i].partyName+'</td>';
									for(var j in result[i].list){
										if(result[i].list[j].locationName !=null && result[i].list[j].locationName>0){
											str+='<td>'+result[i].list[j].wonSeatsCount+'  <small style="color:#2B908F;"><b>'+result[i].list[j].locationName+' %</b></small></td>';
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
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
		var mainDataArr=[];
		var electionYearArr = [];
		
			for(var i in result){
				var wonSeatsCountArr=[];
				for(var j in result[i].list){
					electionYearArr.push(result[i].list[j].electionYear+'  '+result[i].list[j].electionType);
					if(type == "wonSeat"){
						wonSeatsCountArr.push(parseFloat(result[i].list[j].wonSeatsCount));
					}else{
						wonSeatsCountArr.push(parseFloat(result[i].list[j].locationName));
					}
					
				}
				var obj ={
						name: result[i].partyName,
						data: wonSeatsCountArr
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
								str+='<div class="media-left">';
									str+='<h4 class="mpMlaRoundedCss">'+result.subList[i].condidateCount+'</h4>';
								str+='</div>';
									str+='<div class="media-body media_width">';
										if(result.subList[i].party == "BJP"){
											str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList[i].party+'.png"  onerror="setDefaultImage(this);" alt="party"/> '+result.subList[i].party+'</p><p class="f-12">'+result.subList[i].candidateName+'</p>';
										}else if(result.subList[i].party == "OTHERS"){
											str+='<p class="m_top10">'+result.subList[i].party+'</p>';
										}else{
											str+='<p class="m_top10"><img class = "logoRoundedCss" src="images/party_flags/'+result.subList[i].party+'.PNG"  onerror="setDefaultImage(this);" alt="party"/> '+result.subList[i].party+'</p><p class="f-12">'+result.subList[i].candidateName+'</p>';
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
									str+='<h4 class="mpMlaRoundedCss">'+result.subList2[i].condidateCount+'</h4>';
								str+='</div>';
									str+='<div class="media-body media_width">';
									if(result.subList2[i].party == "BJP"){
										str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList2[i].party+'.png"  onerror="setDefaultImage(this);" alt="party"/> '+result.subList2[i].party+'</p><p class="f-12">'+result.subList2[i].candidateName+'</p>';
									}else if(result.subList2[i].party == "OTHERS"){
										str+='<p class="m_top10">'+result.subList2[i].party+'</p>';
									}else{
										str+='<p class="m_top10"><img class="logoRoundedCss" src="images/party_flags/'+result.subList2[i].party+'.PNG"  onerror="setDefaultImage(this);" alt="party" /> '+result.subList2[i].party+'</p><p class="f-12">'+result.subList2[i].candidateName+'</p>';
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
	 
  var jsObj={
      electionSubTypeArr:electionYearsSubTypeArr
    }
    $.ajax({   
      type:'GET',
      url:'getElectionYearsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		 $('#electionTypeSpinnerId').hide();
		if(result !=null && result.imageList !=null && result.imageList.length>0){
			var str='';
			for(var i in result.imageList){
				str+='<option value="'+result.imageList[i]+'" selected>'+result.imageList[i]+'</option>';
					
			} 
			$('#elctionYearsBlockId').html(str);
			$('#elctionYearsBlockId').multiselect({
				enableFiltering: true,
				includeSelectAllOption: true,
				selectAllText: 'All Election Years',
				maxHeight: 300,
				maxWidth: 300,
				dropDown: true,
				selectAllNumber: true,
				allSelectedText: 'All Election Years selected'
			});
			$("#elctionYearsBlockId").multiselect("refresh");
			
		}
		
		if(type == "onload"){
			electionYrVal=[];
			electionYrVal = $("#elctionYearsBlockId").val();
			
			var partyIds = [872,362,1117,886,72,269,265,163,1887];
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


	$(document).on("click",".positionLevelModalClick",function(){
		var boardLevelId =  $(this).attr("attr_boardLevelId");
		var name =  $(this).attr("attr_name");
		$("#positionLevelModal").modal("show");
		$("#postionHeadingId").html(name+"  Level Details");
		getNominatedPositionWiseCandidates(boardLevelId,name);
	});
	
  function getNominatedPositionWiseCandidates(boardLevelId,name){
	  $("#positionLevelDetailsId").html(spinner);
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
		$("#positionLevelDetailsId").html(str);
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
									str+='<span class="openPostClickCls" attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="goIssued" attr_department_name = "'+result[i].board+' Level" attr_department_id="0" attr_board_statusIds="4">'+result[i].goIsuuedCount+'</span>';
								}else{
									str+='<span class="" attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="goIssued" attr_department_name = "'+result[i].board+' Level" attr_department_id="0" attr_board_statusIds="4">'+result[i].goIsuuedCount+'</span>';
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
									str+='<span class="openPostClickCls" attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="open" attr_department_name = "'+result[i].board+' Level" attr_department_id="0">'+result[i].openCount+'</span>';
								}else{
									str+='<span class="" attr_boardLevelId="'+result[i].boardLevelId+'" attr_type="open" attr_department_name = "'+result[i].board+' Level" attr_department_id="0">'+result[i].openCount+'</span>';
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
								str+='<td attr_department_name = "'+result[i].name+'" attr_department_id="'+result[i].id+'" attr_boardLevelId="'+globalboardLevelId+'" class="openPostClickCls" attr_type="department" style="color: #337ab7;font-weight:normaltext-decoration:none;">'+result[i].name+'</td>';
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
  
  function getLevelWiseGoIssuedPostions(boardLevelId,statusId){
	  $("#openPostDetailsModalDivId").html(spinner)
	  
	  var statusIds=[];
	  if(statusId == 0){
		  statusIds.push(3,4)
	  }else{
		  statusIds.push(statusId)
	  }
	  
	var jsObj={
      fromDateStr 		:"",
      toDateStr			:"",
      locationValuesArr	:userAccessLevelValuesArray,
      locationTypeId	:locationLevelId,
      year				:"",
      boardLevelId		:boardLevelId, 
	  statusIds			:statusIds // 3-complered 4 goIsuued
     
    }
    $.ajax({   
      type:'GET',
      url:'getLevelWiseGoIssuedPostionsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return LevelWiseGoIssuedPostions(result);
		}
		
    });
	function LevelWiseGoIssuedPostions(result){
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
		$("#openPostDetailsModalDivId").html(str);
			$("#dataTablegoIssuedPostId").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
		
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
		return buildDesignationWiseAlertsOverview(result);
		}else {
			$("#alertsDeignBlockDivId").html("No Data Available");
		}
	});	
	
		function buildDesignationWiseAlertsOverview(result){
			var str='';
			
			str+='<h4>Designation wise Alerts Overview</h4>';
			str+='<div class="table-responsive">';
				str+='<table class="table" id="dataTableDesigAlerts">';
					str+='<thead>';
					
						str+='<tr>';
							str+='<th rowspan="2">Designation</th>';
							str+='<th rowspan="2">Total</th>';
							str+='<th colspan="8" class="text-center">Involved</th>';
							str+='<th colspan="8" class="text-center">Assigned</th>';
						str+='</tr>';
						str+='<tr>';
							str+='<th>Total</th>';
							str+='<th>Percentage</th>';
							str+='<th>Inprogress</th>';
							str+='<th>Percentage</th>';
							str+='<th>Completed</th>';
							str+='<th>Percentage</th>';
							str+='<th>Others</th>';
							str+='<th>Percentage</th>';
							str+='<th>Total</th>';
							str+='<th>Percentage</th>';
							str+='<th>Inprogress</th>';
							str+='<th>Percentage</th>';
							str+='<th>Completed</th>';
							str+='<th>Percentage</th>';
							str+='<th>Others</th>';
							str+='<th>Percentage</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						
						if(result != null && result.length >0){
							for(var i in result){
								str+='<tr>';
								str+='<td id='+result[i].id+'>'+result[i].status+'</td>';
								str+='<td>'+result[i].totalAlertCount+'</td>';
								str+='<td>'+result[i].alertCount+'</td>';
								str+='<td>'+result[i].percentage1+'%</td>';
								for(var j in result[i].subList){
									str+='<td>'+result[i].subList[j].count+'</td>';
									str+='<td>'+result[i].subList[j].percentage+'%</td>';
								}
								str+='<td>'+result[i].count+'</td>';
								str+='<td>'+result[i].percentage+'</td>';
								for(var k in result[i].subList1){
									str+='<td>'+result[i].subList1[k].count+'</td>';
									str+='<td>'+result[i].subList1[k].percentage+'%</td>';
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
  function getAlertOverviewClick(defaultAlertCategoryIds){
		var jsObj={
				fromDateStr 	  	:"",
				toDateStr		  	:"",
				locationValuesArr 	:[1],
				alertTypeIdsStr 	:defaultAlertCategoryIds,
				locationTypeId		:2,
				year  				:"",
				statusIdsArr :[],
				designationId : 0,
				alertCategeryIdsArr :[],
				type : "",//involved,assigned,categoryOthers,impactOthers
				otherCategory: "",//categoryOthers,impactOthers,candidateAssignedOthers,candidateInvolvedOthers,
				impactIdsArr :[]
			}
		 $.ajax({
	      type : "POST",
	      url : "getAlertOverviewClickAction.action",
	      dataType : 'json',
	      data : {task :JSON.stringify(jsObj)}
	    }).done(function(result){
			
		});	
	  }
  function getLocationWiseMeetingsCountDetails(partyMeetingMainTypeId){
	$("#locationWiseMeetingsCount").html(spinner);
	
	jsObj={
		locationTypeId:	locationLevelId,
		locationValues:	userAccessLevelValuesArray,
		fromDate :"",
		toDate :"",
		partyMeetingMainTypeId:partyMeetingMainTypeId
	}
	 $.ajax({
      type : "GET",
      url : "getLocationWiseMeetingsCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		
	});
  }
  