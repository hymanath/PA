 //ajaxcalls through newconstituencypage.js
 	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));		
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var blockSpinner = '<div class="row"><div class="col-sm-12"><div class="block m_top10"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div></div>';
var globalCasteColorObj = {"BC":"#867DC0","Minority":"#99CC67","SC":"#65A7E1","ST":"#7DC1C2","OC":"#E58D45"};
var globalColorNews = {"Without Money":"#F36E8F","<10L":"#0C9516",">10L":"#8E4654","State Wide Issue":"#FE9900"};
var globalFromDate = moment().subtract(1,'year').format("DD/MM/YYYY");
var globalToDate = moment().format("DD/MM/YYYY");
var propertyIdGlobalStr=[26,28,29,32];
var stateId = $("#getMenuLocations").attr("menu-location-state");
var districtId = $("#getMenuLocations").attr("menu-location-district");
var parliamentId = $("#getMenuLocations").attr("menu-location-parliament");;
var constituencyId = $("#getMenuLocations").attr("menu-location-constituency");
var mandalId = $("#getMenuLocations").attr("menu-location-mandal");
var panchayatId = $("#getMenuLocations").attr("menu-location-panchayat");
var locationLevelName = $("#getMenuLocations").attr("menu-location-levelName");
var locationLevelId = $("#getMenuLocations").attr("menu-location-levelId");
var publicationId = '22';
var userAccessLevelValuesArray=[];
var commitessArr=["mandalLevelGraph","villageLevelGraph","affMandalLevelGraph","affVillageLevelGraph"];
var grivanceIdsArr=["grivanceId","trustId"];
var grivanceColorObj={"APPROVED":"#2DCC70","COMPLETED":"#449C43","IN PROGRESS":"#FFB84F","NOT ELIGIBLE":"#C0392B","NOT POSSIBLE":"#EF8379","NOT VERIFIED":"#31708F"}
var insuranceColorObj={"Waiting For Documents":"#2DCC70","Documents Submitted In Party":"#449C43","Forwarded to Insurance":"#FFB84F","Closed at Insurance":"#8F43AF","Closed at Party":"#9B88B3","Approved - Compensated":"#2BCD72","Closed Letters":"#32708F","Account Rejected":"#65CBCC"}	
onLoadClicks();
onLoadInitialisations();
onLoadAjaxCalls();
function onLoadInitialisations()
{
	minimise('.right-nav-list li',8);
	rightNav();
	collapseClick();
	responsiveTabs();
	//Menu Calls
	menuCalls(2,'1','');	
	menuCalls(9,'1','');	
}
function onLoadAjaxCalls()
{
	//Enrolment Years
	getEnrollmentIds();
	//candidate Profiles 1st block
	getCandidateAndPartyInfoForConstituency();
	//Second Block
	getCountsForConstituency();
	//Constituency Voter Information
	getPublications();
	getVotersAndcadreAgeWiseCount(22);
	//Assembly Block
	getElectionTypes();
	getElectionInformationLocationWise(0);
	getDetailedElectionInformaction();
	//caste information
	getCasteGroupNAgeWiseVoterNCadreCounts("voter")
	getActivityStatusList();
	
	//cadre Information Block
	getLocationTypeWiseCadreCount("");
	getAgeRangeGenerAndCasteGroupByCadreCount(1);
	
	
	//News Block
	getPrintMediaCountsForConstituencyPage()
	getLeadersInNewsForConstituencyPage();
	
	//Problems
	getDetailedGovtOverAllAnalysisProblemsForConstituencyPage("overAll")
	for(var i in propertyIdGlobalStr){
		getDetailedGovtOverAllAnalysisProblemsForConstituencyPage(propertyIdGlobalStr[i]) 
	}
	
	//Committee
	getLocationWiseCommitteesCount(1);
	//Meetings
	getLocationWiseMeetingsCount();
	
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
	
	//Alerts
	getTotalAlertDetailsForConstituencyInfo();
}
function onLoadClicks()
{
	$(document).on("click","[menu-click]",function(){
		var length = $(this).text().length;
		var levelId = $(this).attr("levelId");
		var locationId = $(this).attr("menu-click");
		var levelName = $(this).attr("menu-levelname");
		if(levelName == 'parliament')
		{
			$("[menu-name="+levelName+"]").closest("li").show();
			$("[menu-name='district']").closest("li").hide();
		}else if(levelName == 'district'){
			$("[menu-name="+levelName+"]").closest("li").show();
			$("[menu-name='parliament']").closest("li").hide();
		}
		$("[menu-name="+levelName+"]").html($(this).html());
		//$("[menu-name="+levelName+"]").width(((length)*(10)));
		$("[levelId="+levelId+"],[menu-name]").removeClass("active");
		$(this).addClass("active");
		$("[menu-name="+levelName+"]").closest("li").show();
		$("[menu-name="+levelName+"]").addClass("active");
		$("#getMenuLocations").attr("menu-location-"+levelName+"",locationId);
		$("#getMenuLocations").attr("menu-location-levelId",((levelId)));
		$("#getMenuLocations").attr("menu-location-levelName",levelName);
		locationLevelId = ((levelId)-(1));
		locationLevelName = levelName;
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
		$("[menu-name]").removeClass("active");
		$(this).addClass("active");
	});
	$(document).on("click",".menu-dropdown",function(e){
		e.stopPropagation();
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
		}else if(locationLevelId == 3)
		{
			name = $("#constituencyMenu").find("li.active").html();
			$("#selectedMenuName").html(name+' '+locationLevelName);
		}
		stateId = $("#getMenuLocations").attr("menu-location-state");
		districtId = $("#getMenuLocations").attr("menu-location-district");
		parliamentId = $("#getMenuLocations").attr("menu-location-parliament");
		constituencyId = $("#getMenuLocations").attr("menu-location-constituency");
		mandalId = $("#getMenuLocations").attr("menu-location-mandal");
		panchayatId = $("#getMenuLocations").attr("menu-location-panchayat");
		locationLevelName = $("#getMenuLocations").attr("menu-location-levelName");
		locationLevelId = $("#getMenuLocations").attr("menu-location-levelId");
		onLoadAjaxCalls();
	});
	$(document).on("click","[refresh]",function(){	
		var blockName = $(this).attr("refresh");
		if(blockName == 'casteInfo')
		{
			getCasteGroupNAgeWiseVoterNCadreCounts("voter")
		}else if(blockName == 'cadreInfor')
		{
			getLocationTypeWiseCadreCount("");
			getAgeRangeGenerAndCasteGroupByCadreCount(1);
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
			getPositionWiseMemberCount();
			getNominatedPostApplicationDetails();
			getNominatedPostStatusWiseCount();
		}else if(blockName == 'alerts')
		{
			getTotalAlertDetailsForConstituencyInfo();
		}else if(blockName == 'activities')
		{
			getActivityStatusList();
		}else if(blockName == 'benefits')
		{
			getGovtSchemeWiseBenefitMembersCount();
		}
	});
	$(document).on("click","[role='casteGrouplist'] li",function(){	
		$(this).parent("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var groupType = $(this).attr("attr_type");
		$(".casteGroupTypeDiv li").removeClass("active")
		$(".casteGroupTypeDiv li:nth-child(1)").addClass("active");
		buildCasteGroupNAgeWiseVoterNCadreCounts(globalCasteWiseResult,groupType);
		buildCasteGroupNAgeWiseGraphView(globalCasteWiseResult,groupType,"BC")
	});

	$(document).on("click",".casteGroupTypeDiv li",function(){
		$(this).parent("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var casteType = $(this).attr("attr_type");
		var groupType="";
		 $("[role='casteGrouplist'] li").each(function(){
			if($(this).hasClass("active")){
				groupType =$(this).attr("attr_type"); 
			}
		});
		buildCasteGroupNAgeWiseGraphView(globalCasteWiseResult,groupType,casteType);
	});
	$(document).on("click",".electionTypeWiseCls",function(){
		var electionVal = $(this).val();
		if ($(this).is(':checked')){
			 $('.electionTypeWiseCls').not(this).removeAttr('checked');
			getElectionInformationLocationWise(electionVal);
		}
		
	});		
	$(document).on("click",".expandCasteIconCls",function(){
		var casteGroupId = $(this).attr("attr_caste_group_id");
		var casteId = $(this).attr("attr_caste_id");
		var casteName = $(this).attr("attr_caste_name");
		getCasteNAgeWiseVoterNCadreCounts(casteGroupId,casteId,casteName);
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
		getVotersAndcadreAgeWiseCount(pubId);
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
						menu+='<li menu-click="'+result[i].locationId+'" menu-levelname="'+divId+'" levelId="'+levelId+'" class="text-capitalize">'+result[i].locationName+'</li>';
					}else{
						menu+='<li constituencyId="'+$("#constituencyMenu li.active").attr("menu-click")+'" class="text-capitalize" menu-levelname="'+divId+'" menu-click="'+result[i].locationId+'" levelId="'+levelId+'">'+result[i].locationName+'</li>';
					}
				}
			menu+='</ul>';
		menu+='</div>';
		$("#"+divId+"Menu").html(menu);
		if(levelId == '3' || levelId == '10')
		{
			$(".scroller"+divId).mCustomScrollbar({setHeight:'140px'})
		}else if(result.length > 14){
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
	$("#parliamentMemberId,#assemblyMemberId").html(blockSpinner);
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11' )
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12' )
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13' )
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	var jsObj={
    	locationTypeId	: locationLevelId,
		locationValue	: locationLevelVal
    }
    $.ajax({
      type : "GET",
      url : "getCandidateAndPartyInfoForConstituencyAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			if(locationLevelId == '3' || locationLevelId == '10')
			{
				return buildCandidateAndPartyInfoForDistrict(result);	
			}else{
				return buildCandidateAndPartyInfoForConstituency(result);	
			}
			
		}	
		
	});
	function buildCandidateAndPartyInfoForDistrict(result){
		var parliament = '';
		var assembly = '';
		parliament+='<div class="col-sm-12 m_top20">';
			parliament+='<h3>Member Of Parliament</h3>';
			parliament+='<div class="block">';
				parliament+='<div class="row">';
					for(var i in result[0].subList)
					{
						parliament+='<div class="col-sm-6 m_top10">';
							parliament+='<div class="media media-profile">';
								parliament+='<span id="mlaSpinnerId"></span>';
								parliament+='<div class="media-left">';
									parliament+='<img src="images/candidates/'+result[0].subList[i].parliamentCandidateInfo.candidateName+'.jpg" class="media-object profile-image img-border" alt="profile" onerror="setDefaultImage(this);"/>';
									parliament+='<span class="border-image img-border">';
										parliament+='<img src="images/party_flags/'+result[0].subList[i].parliamentCandidateInfo.partyFlag+'"  onerror="setDefaultImage(this);" alt="party"/>';
									parliament+='</span>';
								parliament+='</div>';
								parliament+='<div class="media-body">';
									parliament+='<h4 class="m_top20 text-success text-capital">'+result[0].subList[i].parliamentCandidateInfo.candidateName+'</h4>';
									parliament+='<p class="text-muted">Constituency : '+result[0].subList[i].parliamentCandidateInfo.constituencyName+'</p>';
								parliament+='</div>';
							parliament+='</div>';
						parliament+='</div>';
					}
				parliament+='</div>';
			parliament+='</div>';
		parliament+='</div>';
		$("#parliamentMemberId").html(parliament);
		assembly+='<div class="col-sm-12 m_top20">';
			assembly+='<h3>Member Of Legislative Assembly (MLA)</h3>';
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
									assembly+='<img  onerror="setDefaultImage(this);" src="images/candidates/'+result[i].assemblyCandidateInfo[0].candidateName+'.jpg" class="media-object profile-image img-border" alt="profile"/>';
									assembly+='<span class="border-image img-border">';
										assembly+='<img onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].assemblyCandidateInfo[0].partyFlag+'" alt="party"/>';
									assembly+='</span>';
								assembly+='</div>';
								assembly+='<div class="media-body">';
									assembly+='<h4 class="m_top20 text-success text-capital">'+result[i].assemblyCandidateInfo[0].candidateName+'</h4>';
									assembly+='<p class="text-muted">Constituency : '+result[i].assemblyCandidateInfo[0].constituencyName+'</p>';
								assembly+='</div>';
							assembly+='</div>';
						assembly+='</div>';
					}
				assembly+='</div>';
			assembly+='</div>';
		assembly+='</div>';
		
		$("#assemblyMemberId").html(assembly);
		
	}
	function buildCandidateAndPartyInfoForConstituency(result)
	{
		var parliament = '';
		parliament+='<div class="col-sm-12 m_top20">';
			parliament+='<div class="block">';
				parliament+='<div class="row">';
					parliament+='<div class="col-sm-6 m_top10">';
						parliament+='<div class="media media-profile">';
							parliament+='<span id="mlaSpinnerId"></span>';
							parliament+='<div class="media-left">';
								parliament+='<img src="images/candidates/'+result[0].subList[0].parliamentCandidateInfo.candidateName+'.jpg" class="media-object profile-image img-border" alt="profile"  onerror="setDefaultImage(this);"/>';
								parliament+='<span class="border-image img-border">';
									parliament+='<img src="images/party_flags/'+result[0].subList[0].parliamentCandidateInfo.partyFlag+'"  onerror="setDefaultImage(this);" alt="party"/>';
								parliament+='</span>';
							parliament+='</div>';
							parliament+='<div class="media-body">';
								parliament+='<h4 class="m_top20 text-success text-capital">'+result[0].subList[0].parliamentCandidateInfo.candidateName+'</h4>';
								parliament+='<p class="text-muted">Member Of Parliament ('+result[0].subList[0].parliamentCandidateInfo.constituencyName+')</p>';
							parliament+='</div>';
						parliament+='</div>';
					parliament+='</div>';			
					for(var i in result)
					{
						parliament+='<div class="col-sm-6 m_top10">';
							parliament+='<div class="media media-profile">';
								parliament+='<span id="mlaSpinnerId"></span>';
								parliament+='<div class="media-left">';
									parliament+='<img  onerror="setDefaultImage(this);" src="images/candidates/'+result[i].assemblyCandidateInfo[0].candidateName+'.jpg" class="media-object profile-image img-border" alt="profile"/>';
									parliament+='<span class="border-image img-border">';
										parliament+='<img  onerror="setDefaultImage(this);" src="images/party_flags/'+result[i].assemblyCandidateInfo[0].partyFlag+'" alt="party"/>';
									parliament+='</span>';
								parliament+='</div>';
								parliament+='<div class="media-body">';
									parliament+='<h4 class="m_top20 text-success text-capital">'+result[i].assemblyCandidateInfo[0].candidateName+'</h4>';
									parliament+='<p class="text-muted">Member Of Legislative Assembly ('+result[i].assemblyCandidateInfo[0].constituencyName+')</p>';
								parliament+='</div>';
							parliament+='</div>';
						parliament+='</div>';
					}	
				parliament+='</div>';
			parliament+='</div>';
		parliament+='</div>';
		$("#parliamentMemberId").html(parliament);
		$("#assemblyMemberId").html("");
	}

}


function getCountsForConstituency(){
	$("#levelWiseCountDivId").html(blockSpinner);
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}

	var userAccessLevelId=locationLevelId;
	
	var jsObj={
			levelId		  		:userAccessLevelId,
			levelValues		 	:userAccessLevelValuesArray,
			publicationDateId 	:24	
			
		}
	 $.ajax({
      type : "POST",
      url : "getAllLocationWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildCountsForConstituency(result);
		}
	});	
		 			
}
function buildCountsForConstituency(results){
	var str='';
	
	str+='<table class="table table-bordered block">';
		str+='<tr>';
			if(results.constituencyCount != null && results.constituencyCount >0){
				str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Constituencies</h4>';
				if(results.constituencyCount !=null && results.constituencyCount>0){
					str+='<h2>'+results.constituencyCount+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			}
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Mandals</h4>';
				if(results.tehsilCount !=null && results.tehsilCount>0){
					str+='<h2>'+results.tehsilCount+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Municipalities</h4>';
				if(results.municipalityCount !=null && results.municipalityCount>0){
					str+='<h2>'+results.municipalityCount+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Panchayats</h4>';
				if(results.villageIdCount !=null && results.villageIdCount>0){
					str+='<h2>'+results.villageIdCount+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Wards</h4>';
				if(results.totalNoOfWards !=null && results.totalNoOfWards>0){
					str+='<h2>'+results.totalNoOfWards+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Booths</h4>';
				if(results.boothCount !=null && results.boothCount>0){
					str+='<h2>'+results.boothCount+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Hamlets</h4>';
				if(results.hamletCount !=null && results.hamletCount>0){
					str+='<h2>'+results.hamletCount+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
		str+='</tr>';
	str+='</table>';
	
	$("#levelWiseCountDivId").html(str);
}

function getVotersAndcadreAgeWiseCount(pubId){
	$("#constituencyVoterInfo").html(spinner);
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	jsObj={
		locationTypeId		: locationLevelId,
		locationValue		: locationLevelVal,
		publicationDateId	: pubId
    }
    $.ajax({
      type : "GET",
      url : "getVotersAndcadreAgeWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildVotersAndcadreAgeWiseCount(result);
		}
		
  });
}
function buildVotersAndcadreAgeWiseCount(result){
	
	if(result !=null && result.length>0){
		var theadArr=['total population','total Voters','total Cadre','Male Voter','Male Cadre','Female Voter','Female Cadre'];
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
			str+='<div class="block">';
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
							if(theadArr[i] == "total population"){
								str+='<h3>12000</h3>';
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
var globalCasteWiseResult="";
function getCasteGroupNAgeWiseVoterNCadreCounts(groupType){
	$("#leftSideCasteGroupWiseDivId,#casteGroupVoters,#rightSideCasreGroupDtsDivId").html(spinner);
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11' )
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12' )
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13' )
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	jsObj={
		type			:groupType,
		locationTypeId	:locationLevelId,
		locationValue	:locationLevelVal,
		publicationDateId:publicationId
    }
	$.ajax({
		type : "GET",
		url : "getVotersAndCadreCasteWiseCountAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
		if(result !=null && result.length>0){
			buildCasteGroupNAgeWiseVoterNCadreCounts(result,groupType);
			buildCasteGroupDetailsViewBlock(result);
			globalCasteWiseResult = result; 
		}
	});	
}
function getCasteNAgeWiseVoterNCadreCounts(casteGroupId,casteId,casteName){
	$("#expandCaste"+casteId).html(spinner);
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11' )
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12' )
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13' )
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	
	jsObj={
		locationTypeId		:locationLevelId,
		locationValue		:locationLevelVal,
		casteGroupId		:casteGroupId,
		casteId				:casteId,
		publicationDateId	:publicationId
    }
	 $.ajax({
      type : "GET",
      url : "getCasteNAgeWiseVoterNCadreCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
		if(result != null && result.length > 0){
			var str='';
			str+='<h4 class="panel-title" style="padding: 5px; font-weight: 600;"><span class="text-capital">'+casteName+'</span> - <span class="text-capitalize">voter and cadre information b/w age group</span></h4>';
			str+='<p class="text-muted text-right">';
				str+='<span class="f-11"><i class="glyphicon glyphicon-info-sign"></i> _(C) = Cadres ; _(V) = Voter</span>';
			str+='</p>';
			str+='<table class="table table-noborder m_top10">';
				str+='<thead class="text-capitalize">';
					str+='<th></th>';
					str+='<th>Voters</th>';
					str+='<th>cadres </th>';
					str+='<th>Male(V) </th>';
					str+='<th>Male(C) </th>';
					str+='<th>FeMale(V)</th>';
					str+='<th>FeMale(C)</th>';
				str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].ageRange+'</td>';
					str+='<td>'+result[i].totalVoters+' <small class="text-success">'+result[i].totalVotersPerc+'%</small></td>';
					str+='<td>'+result[i].totalCadres+' <small class="text-success">'+result[i].totalCadrePerc+'</small></td>';
					str+='<td>'+result[i].maleVoters+' <small class="text-success">'+result[i].maleVotersPerc+'</small></td>';
					str+='<td>'+result[i].maleCadres+' <small class="text-success">'+result[i].maleCadrePerc+'</small></td>';
					str+='<td>'+result[i].femaleVoters+' <small class="text-success">'+result[i].femaleVotersPerc+'</small></td>';
					str+='<td>'+result[i].femaleCadres+' <small class="text-success">'+result[i].femaleCadrePerc+'</small></td>';
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			$("#expandCaste"+casteId).html(str);
		}else{
			$("#expandCaste"+casteId).html("No Data Available.");
		}
	});	
}


function buildCasteGroupNAgeWiseVoterNCadreCounts(result,groupType){
	
	var str='';
	str+='<table class="table table-bordered bg-E9">';
		str+='<thead>';
			str+='<tr>';
			for(var i in result){
				str+='<th>'+result[i].ageRange+'</th>';
			}
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			str+='<tr>';
			for(var i in result){
				if(groupType == "voter"){
					str+='<td>'+result[i].totalVoters+'</td>';
				}else{
					str+='<td>'+result[i].totalCadres+'</td>';
				}
					
			}
			str+='</tr>';	
		str+='</tbody>';
	str+='</table>';
	
	str+='<h4 class="m_top10"><b>Caste Wise Voters</b></h4>';
	str+='<ul class="switch-btn-caste m_top10 casteGroupTypeDiv">';
	for(var i in result){
		if(result[i].ageRange == "BC"){
			str+='<li class="active" attr_type ="'+result[i].ageRange+'">'+result[i].ageRange+'</li>';
		}else{
			str+='<li attr_type ="'+result[i].ageRange+'">'+result[i].ageRange+'</li>';
		}
		
	}
	str+='</ul>';
	str+='<div class="scollerDiv">';
		str+='<div class="m_top20" id="casteWiseVoters"></div>';
	str+='</div>';
	$("#leftSideCasteGroupWiseDivId").html(str);
		
	var mainArr=[];
	for(var i in result){
		var casteName = result[i].ageRange;
		var count=0;
		if(groupType == "voter"){
			count = result[i].totalVoters
		}else{
			count = result[i].totalCadres
		}
		var colorsId = globalCasteColorObj[result[i].ageRange.trim()];
		
		var obj = {
			name: casteName,
			y:count,
			color:colorsId
		}
		mainArr.push(obj);
	}

	var id = 'casteGroupVoters';
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
			innerSize: 115,
			depth: 225,
			dataLabels:{
				useHTML: true,
				enabled: true,
				  formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return "<b style='color:"+this.point.color+"'>"+this.point.name+"<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
						}
					} 
			},
			showInLegend: false
		},
	};
	var legend = {enabled: false};
	var data = [{
		name: '',
		data: mainArr
	}];
	highcharts(id,type,data,plotOptions,title,tooltip,legend);
	buildCasteGroupNAgeWiseGraphView(result,groupType,"BC");
}
function buildCasteGroupNAgeWiseGraphView(result,groupType,casteType){
	var dataArr = [];
	var casteNamesArr=[];
	for(var i in result){
		if(result[i].locationVotersVOList !=null && result[i].locationVotersVOList.length>0){
			for(var j in result[i].locationVotersVOList){
				
				var tempArr = [];
				if(result[i].ageRange == casteType){
					if(groupType == "voter"){
						if(result[i].locationVotersVOList[j].totalVoters !=null && result[i].locationVotersVOList[j].totalVoters>0){
							casteNamesArr.push(result[i].locationVotersVOList[j].ageRange)
							tempArr.push(result[i].locationVotersVOList[j].totalVoters);
							dataArr.push(tempArr);
						}
						
					}else{
						if(result[i].locationVotersVOList[j].totalCadres !=null && result[i].locationVotersVOList[j].totalCadres>0){
							casteNamesArr.push(result[i].locationVotersVOList[j].ageRange)
							tempArr.push(result[i].locationVotersVOList[j].totalCadres);
							dataArr.push(tempArr);
						}
						
					}
					
				}
			}
		}
		
	}
	var heightOfDiv = casteNamesArr.length;
	if(heightOfDiv >10){
		heightOfDiv = heightOfDiv * 30;
		$("#casteWiseVoters").css("height",heightOfDiv);
		$(".scollerDiv").mCustomScrollbar({setHeight:'300px'})
	}else{
		$("#casteWiseVoters").css("height","auto");
		$(".scollerDiv").removeAttr('style')
		$(".scollerDiv").mCustomScrollbar('destroy');
	}
	$('#casteWiseVoters').highcharts({
		chart: {
			type: 'bar'
		},
		title: {
			text: ''
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: casteNamesArr,
			labels: {
					useHTML:true,
					formatter: function() {
						
						if(this.value.toString().length >= 5){
							return '<p>'+this.value.toString().substring(0, 5)+'..'+'</p>';
						}else{
							return '<p>'+this.value+'</p>';
						}
						
						
					},
					style: {
					 color: '#000',
					 font: 'bold 13px "Lato", sans-serif'
				  } 
					
				}
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			}
		},
		legend: {
			enabled: false
		},
		tooltip: {
			pointFormat: ''
		},
		series: [{
			name: '',
			data: dataArr,
			dataLabels: {
				enabled: true,
				color: '#000',
				align: 'center',
			}
		}]
	});	
}

function buildCasteGroupDetailsViewBlock(result){
	if(result !=null && result.length>0){
		var str='';
		str+='<select class="form-control" role="tabListMobile">';
		for(var i in result){
			str+='<option tab_id="'+result[i].ageRange+'">'+result[i].ageRange+'</option>';
		} 
		str+='</select>';
		str+=' <ul class="nav nav-tabs" role="tablist">';
		for(var i in result){
			if(i==0){
				str+='<li role="presentation" class="active"><a href="#'+result[i].ageRange+'" aria-controls="'+result[i].ageRange+'" role="tab" data-toggle="tab">'+result[i].ageRange+'</a></li>';
			}else{
				str+='<li role="presentation" class=""><a href="#'+result[i].ageRange+'" aria-controls="'+result[i].ageRange+'" role="tab" data-toggle="tab">'+result[i].ageRange+'</a></li>';
			}
				
		}
		str+='</ul>';
		str+='<div class="tab-content">';
		for(var i in result){
			if(i==0){
				str+='<div role="tabpanel" class="tab-pane active pad_10" id="'+result[i].ageRange+'">';
			}else{
				str+='<div role="tabpanel" class="tab-pane  pad_10" id="'+result[i].ageRange+'">';
			}
				
				
			str+='<h4 class="panel-title text-capitalize">Caste Wise - Voter & Cadre Information b/w age group</h4>';
				str+='<div class="scroller'+result[i].ageRange+'">';
					str+='<table class="table table-noborder">';
						str+='<thead class="text-capitalize">';
							str+='<th>Caste Name</th>';
							str+='<th>voters</th>';
							str+='<th>cadres </th>';
							str+='<th>Male(V) </th>';
							str+='<th>Male(C) </th>';
							str+='<th>FeMale(V) </th>';
							str+='<th>FeMale(C) </th>';
						str+='</thead>';
						str+='<tbody>';
						for(var j in result[i].locationVotersVOList){
							str+='<tr>';
								str+='<td><i class="glyphicon glyphicon-plus td-expand-icon expandCasteIconCls" collapseid="td-expand-'+result[i].locationVotersVOList[j].ageRangeId+'" attr_caste_name="'+result[i].locationVotersVOList[j].ageRange+'" attr_caste_group_id="'+result[i].ageRangeId+'" attr_caste_id="'+result[i].locationVotersVOList[j].ageRangeId+'"></i> '+result[i].locationVotersVOList[j].ageRange+'</td>';
								str+='<td>'+result[i].locationVotersVOList[j].totalVoters+' <small class="text-success">'+result[i].locationVotersVOList[j].totalVotersPerc+'%</small></td>';
								str+='<td>'+result[i].locationVotersVOList[j].totalCadres+' <small class="text-success">'+result[i].locationVotersVOList[j].totalCadrePerc+'</small></td>';
								str+='<td>'+result[i].locationVotersVOList[j].maleVoters+' <small class="text-success">'+result[i].locationVotersVOList[j].maleVotersPerc+'</small></td>';
								str+='<td>'+result[i].locationVotersVOList[j].maleCadres+' <small class="text-success">'+result[i].locationVotersVOList[j].maleCadrePerc+'</small></td>';
								str+='<td>'+result[i].locationVotersVOList[j].femaleVoters+' <small class="text-success">'+result[i].locationVotersVOList[j].femaleVoters+'</small></td>';
								str+='<td>'+result[i].locationVotersVOList[j].femaleCadres+' <small class="text-success">'+result[i].locationVotersVOList[j].femaleCadrePerc+'</small></td>';
							str+='</tr>';
							str+='<tr class="td-expand-body" collapseBodyId="td-expand-'+result[i].locationVotersVOList[j].ageRangeId+'">';
							str+='<td colspan="7" class="top-arrow" id="expandCaste'+result[i].locationVotersVOList[j].ageRangeId+'"></td></tr>';
						}
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
		$("#rightSideCasreGroupDtsDivId").html(str);		 
		responsiveTabs();
		for(var i in result)
		{
			if(result[i].locationVotersVOList.length > 20)
			{
				$(".scroller"+result[i].ageRange).mCustomScrollbar({setHeight: '700px'});
			}
		}
	}	
}


function getActivityStatusList(){
	$("#activitesId").html(spinner);
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}

	
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
    	var str = '';
		var per='%';
		str+='<div class="pad_10">'
		str+='<table class="table table-striped">';
		str+='<thead class="text-capitalize bg-E9">';
		str+='<th>Activity Name</th>';
		str+='<th>level</th>';
		str+='<th>status</th>';
		str+='</thead>';
		str+='<tbody>';
		if(result!=null && result.length>0){
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td>'+result[i].description+'</td>';
					str+='<td>'+result[i].perc+''+per+'</td>';
				str+='</tr>';
			}
			
		}
		str+='</tbody>';
		str+='</table>';
		str+='</div>'
		$("#activitesId").html(str);
	});	
		
		
}
function getLocationTypeWiseCadreCount(yearId){
	$("#cadreInfoGraphDivId").html(spinner);	
	var userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	jsObj={
		locationTypeId:		locationLevelId,
		locationValuesArr:	userAccessLevelValuesArray,
		year:yearId
	}
	 $.ajax({
      type : "POST",
      url : "getLocationTypeWiseCadreCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result !=null){
			return buildLocationTypeWiseCadreCount(result);
		}else{
			$("#cadreInfoGraphDivId").html("NO DATA AVAILABLE");
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
							str+='<table class="table table-noborder">';
								str+='<tr>';
									str+='<td>'+result[i].newCaderCount+' New <span class="colorNew"></span></td>';
									str+='<td>+</td>';
									str+='<td>'+result[i].renewalCadreCount+' Renewal <span class="colorRenewal"></span></td>';
									str+='<td>=</td>';
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
			  variableWidth: false
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



function getAgeRangeGenerAndCasteGroupByCadreCount(yearId){
	$("#cadreInfoGraphBar,#cadreInfoTableView").html(spinner);
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	jsObj={
		locationTypeId	 :locationLevelId,
		locationValue	 :locationLevelVal,
		enrollmentYearId :yearId
	}
	 $.ajax({
      type : "POST",
      url : "getAgeRangeGenerAndCasteGroupByCadreCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildAgeRangeGenerAndCasteGroupByCadreCount(result,yearId);
		}
			
	});	
	function buildAgeRangeGenerAndCasteGroupByCadreCount(result,yearId){
		if(result !=null && result.length>0){
			var str='';
			var totalCount=0;
			var totalPerc=0;
			str+='<table class="table table-noborder table-noborder-hover m_top10">';
				str+='<thead class="bg-E9">';
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
			$("#cadreInfoTableView").html(str);
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
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push("5")
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push("6")
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push("8")
	}
	
	var userAccessLevelId=locationLevelId;
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
		//url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaCountsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+type+"/"+benefitIdsStr+"/"+isDept
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaCountsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+type+"/"+benefitIdsStr+"/"+isDept
	}).then(function(result){
		if(result !=null){
			return buildPrintMediaCountsForConstituencyPage(result);
		}
	});
	function buildPrintMediaCountsForConstituencyPage(result){
		if(result !=null){
			var str='';
			str+='<div class="row">';
				str+='<div class="col-sm-6 pad_right0">';
					str+='<div class="block pad_5 blockHeights">';
						str+='<h4 class="text-capitalize"><img src="coreApi/img/news.png" style="width:30px;"/>&nbsp;&nbsp;&nbsp; print media</h4>';
						str+='<table class="table table-noborder m_top10">';
							str+='<thead class="text-capitalize f-12 bg-E9">';
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
										str+='<td><p><img  onerror="setDefaultImage(this);" src="images/party_flags/'+result.coreDashBoardVOList[i].organization.trim()+'.PNG" alt="party" style="width: 30px; height: 30px;"/>&nbsp;&nbsp;&nbsp; <span>'+result.coreDashBoardVOList[i].count+'</span></p></td>';
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
					str+='<div class="block pad_5 blockHeights">';
						str+='<h4 class="text-capitalize"><img src="coreApi/img/electronic.png" style="width:30px;"/>&nbsp;&nbsp;&nbsp; electronic media</h4>';
						str+='<table class="table table-noborder m_top10">';
							str+='<thead class="text-capitalize f-12 bg-E9">';
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
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push("2")
	}else if(locationLevelId == '3' || locationLevelId == '10')
	{
		userAccessLevelValuesArray.push("3")
	}else if(locationLevelId == '4')
	{
		userAccessLevelValuesArray.push("5")
	}else if(locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push("4")
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push("6")
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push("8")
	}
	
	var userAccessLevelId=locationLevelId;
	var stateId=1;
	var date1 = globalFromDate.split('/');
	var date2 = globalToDate.split('/');
	var startDate = date1[0]+'-'+date1[1]+'-'+date1[2]
	var endDate = date2[0]+'-'+date2[1]+'-'+date2[2]
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getLeadersInNewsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+startDate+"/"+endDate+"/"+stateId
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getLeadersInNewsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+startDate+"/"+endDate+"/"+stateId
		
	}).then(function(result){
		if(result !=null && result.length>0){
			return buildLeadersInNewsForConstituencyPage(result)
		}else{
			$("#leadersMainBlockDivId").html("NO DATA");
		}
	});
	function buildLeadersInNewsForConstituencyPage(result){
		var str='';
		
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading bg-fff">';
				str+='<h4 class="panel-title">leaders in news</h4>';
			str+='</div>';
			str+='<div class="scrollerNewsDiv">';
			str+='<div class="panel-body pad_0">';
				str+='<table class="table table-noborder f-12">';
					str+='<thead class="bg-E9 text-capitalize">';
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
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push("2")
	}else if(locationLevelId == '3' || locationLevelId == '10')
	{
		locationLevelId = '3';
		userAccessLevelValuesArray.push("3")
	}else if(locationLevelId == '4')
	{
		locationLevelId = '4'
		userAccessLevelValuesArray.push("5")
	}else if(locationLevelId == '11')
	{
		locationLevelId = '4'
		userAccessLevelValuesArray.push("4")
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5'
		userAccessLevelValuesArray.push("6")
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6'
		userAccessLevelValuesArray.push("8")
	}
	
	var userAccessLevelId=locationLevelId;
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
	var impactScopeIdsStr=[1,2];
	var orgIdsStr="";
	var isDept="N";
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisProblemsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+npIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+0+"/"+12
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisProblemsForConstituencyPage/"+locationLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyIdStr+"/"+impactScopeIdsStr
	}).then(function(result){
		if(result !=null && result.length>0){
			buildDetailedGovtOverAllAnalysisProblemsForView(result,typeValue);
		}else{
			$("#overAllAnalysisProbDivId").html("NO DATA AVAILABLE");
		}
	});
	function buildDetailedGovtOverAllAnalysisProblemsForView(result,typeValue){
		if(typeValue == "overAll"){
			if(result !=null && result.length>0){
				var str='';
				str+='<div class="row">';
					str+='<div class="col-md-6 col-xs-12 col-sm-12">';
						str+='<table class="table table-noborder f-12">';
							str+='<thead class="bg-E9 text-capitalize">';
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
					str+='<div class="col-md-6 col-xs-12 col-sm-12 text-center">';
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
							enabled: true,
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
							collapse+='<td><p><span class="overAllProbClr" style="background-color:'+globalColorNews[result[i].name]+'"></span>&nbsp;&nbsp;&nbsp; <span style="margin-left: 10px;">'+result[i].name+'</span></p></td>';
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
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11' )
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12' )
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13' )
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	var jsObj={
			locationType : locationLevelName,
			locationId 	   : locationLevelVal,
			enrollmentId   : yearId
		}
	 $.ajax({
      type : "POST",
      url : "getLocationWiseCommitteesCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	if(result !=null){
			return buildLocationWiseCommitteesCount(result);
		}else{
			$("#committesTableDivId").html("NO DATA AVAILABLE");
			for(var i in commitessArr)
			{
				$("#"+commitessArr[i]).html("NO DATA AVAILABLE");
			}
		}
	});	
	function buildLocationWiseCommitteesCount(result){
		
		var str='';
		str+='<table class="table table-bordered">';
			str+='<thead>';
				str+='<tr class="text-capital">';
					str+='<th rowspan="2"></th>';
					str+='<th colspan="4">main committee</th>';
					str+='<th colspan="4"> affliated committee</th>';
				str+='</tr>';
				str+='<tr class="text-capitalize bg-E9">';
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
					str+='<td>'+result.mainCommStartedCount+'</td>';
					str+='<td>'+result.mainCommCompletedCount+'</td>';
					str+='<td>'+result.mainCommNotYetStarted+'</td>';
					
					str+='<td>'+result.affMandalTotal+'</td>';
					str+='<td>'+result.affiCommStartedCount+'</td>';
					str+='<td>'+result.affiCommCompletedCount+'</td>';
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
	var userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	jsObj={
		locationTypeId:	locationLevelId,
		locationValues:	userAccessLevelValuesArray
	}
	 $.ajax({
      type : "GET",
      url : "getLocationWiseMeetingsCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result!=null){
			return buildTable(result);
		}else{
			$("#locationWiseMeetingsCount").html("NO DATA");
		}
	});	
	function buildTable(result)
	{
		var tableView = '';
		tableView+='<table class="table table-bordered">';
			tableView+='<thead class="text-capitalize">';
				tableView+='<th></th>';
				for(var i in result[0].locationVotersVOList)
				{
						tableView+='<th class="bg-E9">'+result[0].locationVotersVOList[i].ageRange+'</th>';
					
				}
			tableView+='</thead>';
			tableView+='<tbody>';
				for(var i in result)
				{
					tableView+='<tr>';
						tableView+='<td>'+result[i].ageRange+'</td>';
						for(var j in result[i].locationVotersVOList)
						{
							tableView+='<td>'+result[i].locationVotersVOList[j].totalVoters+'</td>';
						}
					tableView+='</tr>';
				}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#locationWiseMeetingsCount").html(tableView);
	}
}
function getLocationWiseTourMembersComplainceDtls(){
	$("#locationWiseTourMembersComplainceDtls").html(spinner);
	var userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	
	var userAccessLevelId=locationLevelId;
	jsObj={
		locationTypeId:userAccessLevelId,
		locationValuesArr:userAccessLevelValuesArray,
		fromDate:globalFromDate,
		toDate:globalToDate,
		year:""
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
			$("#locationWiseTourMembersComplainceDtls").html("NO DATA");
		}
	});	
	function buildTable(result)
	{
		var tableView = '';
		tableView+='<table class="table table-hover">';
			tableView+='<thead class="text-capitalize bg-E9">';
				tableView+='<th>Designation</th>';
				tableView+='<th>status</th>';
			tableView+='</thead>';
			tableView+='<tbody>';
				for(var i in result)
				{
					tableView+='<tr>';
						tableView+='<td>'+result[i].designation+'</td>';
						if(result[i].isComplaince=="False"){
							tableView+='<td><span class="text-danger">Non-Complaince</span></td>';	
						}else{
							tableView+='<td><span class="text-success">Complaince</span></td>';	
						}
					tableView+='</tr>';
				}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#locationWiseTourMembersComplainceDtls").html(tableView);
	}
}
function getGovtSchemeWiseBenefitMembersCount(){
	$("#benefitsBlockId").html(spinner);
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11' )
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12' )
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13' )
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	
	jsObj={
		locationTypeId	:locationLevelId,
		locationValue	:locationLevelVal
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
			$("#benefitsBlockId").html("NO DATA");
		}
	});	
	function buildTabs(result,locationLevelId,locationLevelVal)
	{
		var navTabs = '';
		navTabs+='';
		navTabs+='<div class="col-md-3 col-xs-12 col-sm-3 pad_right0">';
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
						navTabs+='<li class="active"><a href="#benefits'+result[i].id+'" navTabs-click="'+result[i].id+'" aria-controls="benefits'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+' <span class="pull-right">'+result[i].totalCount+'</span></a></li>';
					}else{
						navTabs+='<li><a href="#benefits'+result[i].id+'" navTabs-click="'+result[i].id+'" aria-controls="benefits'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+' <span class="pull-right">'+result[i].totalCount+'</span></a></li>';
					}
				}
			navTabs+='</ul>';
		navTabs+='</div>';
		navTabs+='<div class="col-md-9 col-xs-12 col-sm-9 pad_left0">';
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
		responsiveTabs();
		getMandalWiseBenefitMembersCount(result[0].id)
	}
}

function getMandalWiseBenefitMembersCount(id){
	$("#benefits"+id).html(spinner);
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11' )
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12' )
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13' )
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	jsObj={
		locationTypeId:locationLevelId,
		locationValue:locationLevelVal,
		govtSchemeId:id
	}
	$.ajax({
	  type : "POST",
	  url : "getMandalWiseBenefitMembersCountAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result!=null && result.length>0){
			return buildTabsBody(result,id);
		}else{
			$("#benefits"+id).html("NO DATA");
		}
	});
	
	function buildTabsBody(result,id)
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
			navTabBody+='<div class="col-md-6 col-xs-12 col-sm-6">';
				navTabBody+='<div id="benefitsGraph'+id+'" style="height:200px"></div>';
			navTabBody+='</div>';
			navTabBody+='<div class="col-md-6 col-xs-12 col-sm-6">';
				navTabBody+='<table class="table table-noborder">';
					navTabBody+='<thead class="text-capitalize bg-E9">';
						navTabBody+='<th></th>';
						navTabBody+='<th>Total</th>';
						navTabBody+='<th>%</th>';
					navTabBody+='</thead>';
					navTabBody+='<tbody class="text-capitalize">';
						navTabBody+='<tr>';
							navTabBody+='<td><span class="chart-legend-color" style="background-color:#fc8db7"></span>Population</td>';
							navTabBody+='<td>'+totalPopulation+'</td>';
						navTabBody+='</tr>';
						navTabBody+='<tr>';
							navTabBody+='<td><span class="chart-legend-color" style="background-color:#27b720"></span>Benefited</td>';
							navTabBody+='<td>'+totalBenefited+'</td>';
						navTabBody+='</tr>';
					navTabBody+='</tbody>';
				navTabBody+='</table>';
			navTabBody+='</div>';
			navTabBody+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				navTabBody+='<table class="table table-noborder">';
					navTabBody+='<thead class="text-capitalize bg-E9">';
						navTabBody+='<th>Mandal Name</th>';
						navTabBody+='<th>Population</th>';
						navTabBody+='<th>Benefited</th>';
						navTabBody+='<th>%</th>';
					navTabBody+='</thead>';
					navTabBody+='<tbody class="text-capitalize">';
						for(var i in result)
						{
							navTabBody+='<tr>';
								navTabBody+='<td>'+result[i].name+'</td>';
								navTabBody+='<td>'+result[i].totalPopulation+'</td>';
								navTabBody+='<td>'+result[i].totalCount+'</td>';
								navTabBody+='<td>'+(((result[i].totalCount)/(result[i].totalPopulation))*100).toFixed(2)+'%</td>';
							navTabBody+='</tr>';
						}
					navTabBody+='</tbody>';
				navTabBody+='</table>';
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
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	
	var userAccessLevelId=locationLevelId;
	var jsObj={
			"fromDateStr" : globalFromDate,
			"toDateStr":globalToDate,
			"locationValuesArr":userAccessLevelValuesArray,
			"locationTypeId":userAccessLevelId,
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
			$("#positionsWiseMemberCount").html("NO DATA");
		}
		function build(result)
		{
			var str='';
			str+='<ul class="list-border">';
			for(var i in result){
				str+='<li>';
					str+='<h3>'+result[i].count+'</h3>';
					str+='<p class="text-capitalize">'+result[i].name+'</p>';
				str+='</li>';
			}
			str+='</ul>';
			$("#positionsWiseMemberCount").html(str);
		}
	});	
}
function getNominatedPostApplicationDetails(){
	$("#nominatedPostApplicationDetails").html(spinner);
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	
	var userAccessLevelId=locationLevelId;
	var jsObj={
			"year":"",
			"fromDateStr" :globalFromDate,
			"toDateStr":globalToDate,
			"locationTypeId":userAccessLevelId,
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
			$("#nominatedPostApplicationDetails").html("NO DATA");
		}
		function buildGraph(result)
		{
			var mainArr = [];
			for(var i in result){
				var subArr = [];
				var name = "",
				name = result[i].name;
				subArr={name:result[i].name,y:parseInt(result[i].count)};
				mainArr.push(subArr);			
			}
			$('#nominatedPostApplicationDetails').highcharts({
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
						return '<ul class="graph-legend" style="width:240px"><li style="background-color:'+this.color+';color:#fff"><span class="statusBox" style="background-color:'+this.color+';box-shadow:0px 0px 1px 1px rgba(0,0,0,0.4)"></span>'+this.name + '<span style="margin-left:8px;position: absolute;right: 10px;">' + this.y + '</span></li></ul>';
						//return '<div style="padding:5px;background-color:'+this.color+'"><span style="color:#fff">'+this.name + '-' + this.y + '</span></div>';
					}
				},
				series:[{
					name : 'Count',
					data: mainArr
				}]
			});
		}
		
	});	
}
function getNominatedPostStatusWiseCount(){
	$("#nominatedPostStatusWiseCount").html(spinner);
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	
	var userAccessLevelId=locationLevelId;
	var jsObj={
			"year":"",
			"fromDateStr" : globalFromDate,
			"toDateStr":globalToDate,
			"locationTypeId":userAccessLevelId,
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
			$("#nominatedPostStatusWiseCount").html("NO DATA");
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
			str+='<div class="col-md-6 col-xs-12 col-sm-6 ">';
				str+='<div id="nominatedPostStatusWiseCountGraph"></div>';
			str+='</div>';
			str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top40">';
				str+='<ul class="graph-legend">';
				str+='<li style="background-color:#FEEE99"><span class="statusBox" style="background-color:#FED501"></span>TOTAL POSTS<span class="count">'+totalCount+'</span></li>';
				for(var i in result){
					str+='<li style="background-color:'+colorsArr[i]+'"><span class="statusBox" style="background-color:'+colors[i]+'"></span>'+result[i].name+' POSTS<span class="count">'+result[i].count+'</span></li>';
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
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	
	var userAccessLevelId=locationLevelId;
	var jsObj={
			"fromDate" 			: globalFromDate,
			"toDate"			: globalToDate,
			"locationTypeId" 	: userAccessLevelId,
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
			return buildGraph(result);
		}else{
			$("#insuranceDetails").html("<h4 class='text-center'>NO DATA AVAILABLE</h4>");
		}
	});	
	function buildGraph(result){
		if(result !=null){
				var mainArr=[];
				for(var j in result.subList){
					var colorsId = insuranceColorObj[result.subList[j].name.trim()];
					var obj1 = {
						name: result.subList[j].name,
						y:result.subList[j].count,
						color:colorsId
					}
					mainArr.push(obj1);
				}
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
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	
	var userAccessLevelId=locationLevelId;
	var jsObj={
			"fromDate" : globalFromDate,
			"toDate":globalToDate,
			"locationTypeId" : userAccessLevelId,
			"locationValuesArr" :userAccessLevelValuesArray,
			"year": yearId
		}
	$.ajax({
		type : "POST",
		url : "getLocationWiseGrivanceTrustStatusCountsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result!=null && result.length>0){
			return buildGraph(result);
		}else{
			$("#grivanceId0,#grivanceId1").html("NO DATA");
		}
	});

	function buildGraph(result){
		if(result !=null && result.length>0){
			for(var i in result){
				var mainArr=[];
				if(result[i].grivenceType == "Grivence"){
					for(var j in result[i].subList){
						var colorsId = grivanceColorObj[result[i].subList[j].name.trim()];
						var obj = {
							name: result[i].subList[j].name,
							y:result[i].subList[j].count,
							color:colorsId
						}
						mainArr.push(obj);
					}
				}else if(result[i].grivenceType == "NTR Trust"){
					for(var j in result[i].subList){
						var colorsId = grivanceColorObj[result[i].subList[j].name.trim()];
						var obj1 = {
							name: result[i].subList[j].name,
							y:result[i].subList[j].count,
							color:colorsId
						}
						mainArr.push(obj1);
					}
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
			if(result[i].date == "2016-01-01"){
				str+='<option value = "'+result[i].id+'" selected>'+result[i].date+'</option>';
			}else{
				str+='<option value = "'+result[i].id+'">'+result[i].date+'</option>';
			}
			
		}
		str+='</select>';
		
		$("#publicationsDivId").html(str);
		$(".chosen-select").chosen();
		
	}
}

function getDetailedElectionInformaction(){
	$("#assemblyElectionGraphDetails,#assemblyElectionDetails").html(spinner);
	jsObj={
    	constituencyId:constituencyId
    }
    $.ajax({
      type : "GET",
      url : "getDetailedElectionInformactionAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		return buildAssemblyResultsTable(result)
	});
	
	function buildAssemblyResultsTable(result)
	{
			
			
		if(result !=null && result.length>0){
			var str='';
			var electionYearArr = [];
			var mainArr=[];
			for(var i in result){
				electionYearArr.push(result[i].electionYear);
				if(result[i].subList !=null && result[i].subList.length>0){
					for(var j in result[i].subList){
						var partyWiseCounts=[];
						if(result[i].subList[j].subList !=null && result[i].subList[j].subList.length>0){
							for(var k in result[i].subList[j].subList){
								partyWiseCounts.push(parseFloat(result[i].subList[j].subList[k].votingPercentage));
							}
						}
						var obj ={
							name: result[i].subList[j].name,
							data: partyWiseCounts
						}
						mainArr.push(obj)
					}
				}
			}
			
			str+='<table class="table table-noborder">';
				str+='<thead class="bg-E9 text-capitalize">';
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
							str+='<td><img src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.PNG" alt="party"/></td>';
						}else{
							str+='<td><img src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="party"/></td>';
						}
						
						str+='<td>'+result[i].candidateResultsVO.candidateName+'</td>';
						str+='<td>'+result[i].candidateResultsVO.votesEarned+'</td>';
						if(result[i].candidateOppositionList[0].partyShortName == "TDP" || result[i].candidateOppositionList[0].partyShortName == "YSRC"){
							str+='<td><img src="images/party_flags/'+result[i].candidateOppositionList[0].partyShortName+'.PNG" alt="party"/></td>';
						}else{
							str+='<td><img src="images/party_flags/'+result[i].candidateOppositionList[0].partyShortName+'.png" alt="party"/></td>';
						}
						str+='<td>'+result[i].candidateOppositionList[0].candidateName+'</td>';
						
						
					str+='</tr>';
				}
					
				str+='</tbody>';
			str+='</table>';
			$("#assemblyElectionDetails").html(str);
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

				plotOptions: {
					series: {
						//pointStart: 2010
					}
				},

				series: mainArr

			});
		}
	}
}
function getTotalAlertDetailsForConstituencyInfo(){
	$("#alertsBlockDivId").html(spinner);
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	var userAccessLevelId=locationLevelId;
	var jsObj={
			fromDateStr 	  	:globalFromDate,
			toDateStr		  	:globalToDate,
			locationValuesArr 	:userAccessLevelValuesArray,
			alertTypeIdsStr 	:[1,2,3],
			locationTypeId		:userAccessLevelId,
			year  				:""

		}
	 $.ajax({
      type : "POST",
      url : "getTotalAlertDetailsForConstituencyInfoAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){	
			return buildAlertsTable(result);
		}else{
			$("#alertsBlockDivId").html("NO DATA AVAILABLE");
		}
	});	
	function buildAlertsTable(result){
		var str='';
		var totalCount=0;
		if(result !=null){
			var locationNamesArr={'DISTRICT':'#FE6603','CONSTITUENCY':'#FFA522','Mandal/Municipality':'#8E4552','Village/Ward':'#F16283'};
			str+='<div class="block">';
				str+='<div class="row">';
					str+='<div class="col-md-6 col-xs-12 col-sm-6">';
						str+='<div class="block">';
							str+='<ul class="list-inline list-border">';
								str+='<li>';
									str+='<h4>'+result.totalAlertCount+'</h4>';
									str+='<p>Total Alerts</p>';
								str+='</li>';
								str+='<li>';
									str+='<h4>'+result.involveMemberCount+'</h4>';
									str+='<p>Involved Memberes</p>';
								str+='</li>';
								str+='<li>';
									str+='<h4>'+result.assignedMemberCount+'</h4>';
									str+='<p>Assigned Memberes</p>';
								str+='</li>';
							str+='</ul>';
						str+='</div>';
						str+='<div class="row m_top15">';
							str+='<div class="col-md-5 col-xs-12 col-sm-5">';
								str+='<div id="overallAlerts" style="height:200px"></div>';
							str+='</div>';
							str+='<div class="col-md-7 col-xs-12 col-sm-7">';
							  str+='<select class="form-control" role="tabListMobile">';
							  if(result !=null && result.subList !=null && result.subList.length>0){
								for(var i in result.subList){
									str+='<option tab_id="alerts'+i+'">'+result.subList[i].locationName+'</option>';
								}
							  }
							  str+='</select>';
							  str+='<ul class="nav nav-tabs nav-tabs-horizontal" role="tablist">';
							  if(result !=null && result.subList !=null && result.subList.length>0){
								for(var i in result.subList){
									totalCount = totalCount+result.subList[i].count;
									var constituencyPercCount=0;var mandalPercCount=0;var panchayatPercCount=0;
									if(result.subList[i].locationName == "CONSTITUENCY"){
										var Count = result.subList[i].count;
										constituencyPercCount = (Count/totalCount*100).toFixed(2);
									}else if(result.subList[i].locationName == "Mandal/Municipality"){
										var Count  = result.subList[i].count;
										mandalPercCount = (Count/totalCount*100).toFixed(2);
									}else if(result.subList[i].locationName == "Village/Ward"){
										var Count  = result.subList[i].count;
										panchayatPercCount = (Count/totalCount*100).toFixed(2);
									}
									var locationNamesPerc={'CONSTITUENCY':constituencyPercCount,'Mandal/Municipality':mandalPercCount,'Village/Ward':panchayatPercCount};
									if(i==0){
										str+='<li role="presentation" class="active"><a class="alertsColorCls text-capital"  href="#alerts'+i+'" aria-controls="alerts1" role="tab" data-toggle="tab"><span class = "ColorView" style="background-color:'+locationNamesArr[result.subList[i].locationName]+'"></span>'+result.subList[i].locationName+' <p class="pull-right"><span>'+result.subList[i].count+'</span>';
										//str+='<span>'+locationNamesPerc[result.subList[i].locationName]+' %</span>';
										str+='<p></a></li>';
									}else{
										str+='<li role="presentation"><a class="alertsColorCls text-capital" href="#alerts'+i+'" aria-controls="alerts1" role="tab" data-toggle="tab" ><span class = "ColorView" style="background-color:'+locationNamesArr[result.subList[i].locationName]+'"></span>'+result.subList[i].locationName+'<p class="pull-right"><span>'+result.subList[i].count+'</span>';
										//str+='<span>'+locationNamesPerc[result.subList[i].locationName]+' %</span>';
										str+='<p></a></li>';
									}
									
								}
							  }
							 str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-md-6 col-xs-12 col-sm-6 pad_left0">';
						  str+='<div class="tab-content">';
						   if(result !=null && result.subList !=null && result.subList.length>0){
								for(var i in result.subList){
									if(i == 0){
										str+='<div role="tabpanel" class="tab-pane active pad_10" id="alerts'+i+'">';
									}else{
										str+='<div role="tabpanel" class="tab-pane pad_10" id="alerts'+i+'">';
									}
									
										str+='<table class="table table-noborder">';
											str+='<thead class="text-capitalize bg-E9">';
												str+='<th>alert status</th>';
												str+='<th>total</th>';
												for(var k in result.subList[0].subList[0].subList){
													str+='<th>'+result.subList[0].subList[0].subList[k].alertType+'</th>';
												}
												
											str+='</thead>';
											str+='<tbody>';
											for(var j in result.subList[i].subList){
												str+='<tr>';
												str+='<td>'+result.subList[i].subList[j].status+'</td>';
												str+='<td>'+result.subList[i].subList[j].count+'</td>';
												for(var k in result.subList[i].subList[j].subList){
													str+='<td>'+result.subList[i].subList[j].subList[k].count+'</td>';
												}	
												str+='</tr>';
											}
												
													
												
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
								}
						   }
						 str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		
		
		$("#alertsBlockDivId").html(str);
		responsiveTabs();
		
			var overAllAlertsMainArr=[];
				for(var i in result.subList){
					var name = result.subList[i].locationName;
					var Perc=result.subList[i].count;
					
					var colorsId = locationNamesArr[result.subList[i].locationName.trim()];
					
					var obj = {
						name: name,
						y:Perc,
						color:colorsId
					}
					overAllAlertsMainArr.push(obj);
				}
			
			
			var id = 'overallAlerts';
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
					innerSize: 110,
					depth: 80,
					dataLabels:{
						useHTML: true,
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return "<b style='color:"+this.point.color+"'>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
								}
							} 
					},
					showInLegend: false
				},
			};
			var legend={enabled: false};
			var data = [{
				name: '',
				data: overAllAlertsMainArr
			}];
				
			highcharts(id,type,data,plotOptions,title,tooltip,legend);
		}
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
			selectBox+='<option value="'+result[i].id+'">'+result[i].description+'</option>';
		}
		$("#enrolmentYears").html(selectBox);
		$("#enrolmentYears").chosen();
	});	
}
function setDefaultImage(img){
	img.src = "images/User.png";
}
function getElectionTypes(){
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
			 str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			  str+='<label class="text-capital m_left5" style="margin-right: 10px;">';
					str+='<input value="0" type="checkbox" checked class="electionTypeWiseCls checkUncheckCls" /><span>All</span>';
				 str+='</label>';
			 for(var i in result){
				 str+='<label class="text-capital m_left5" style="margin-right: 10px;">';
					if(result[i].name == "Pancahat_Ward"){
						str+='<input value ="'+result[i].id+'" type="checkbox" class="electionTypeWiseCls" /><span>Ward</span>';
					}else{
						str+='<input value ="'+result[i].id+'" type="checkbox" class="electionTypeWiseCls" /><span>'+result[i].name+'</span>';
					}
					
				 str+='</label>';
			 }
			str+=' </div>';
			$("#electionTypeValuesId").html(str);
		}
	});	
}

function getElectionInformationLocationWise(electionVal){
	$('#electionDetailsGraphWiseId').html(spinner);
	var locationLevelVal = '';
	if(locationLevelId == '2')
	{
		locationLevelVal = stateId 
	}else if(locationLevelId == '3')
	{
		locationLevelVal = districtId 
	}else if(locationLevelId == '10')
	{
		locationLevelVal = parliamentId 
	}else if(locationLevelId == '4' || locationLevelId == '11' )
	{
		locationLevelId = '4';
		locationLevelVal = constituencyId 		
	}else if(locationLevelId == '5' || locationLevelId == '12' )
	{
		locationLevelId = '5';
		locationLevelVal = mandalId 		
	}else if(locationLevelId == '6' || locationLevelId == '13' )
	{
		locationLevelId = '6';
		locationLevelVal = panchayatId 		
	}
	var electionScopeIds=[];
	if(electionVal == 0){
		electionScopeIds =[];
	}else{
		electionScopeIds.push(electionVal);
	}
	
	var jsObj={
			fromDate 	  	:"",
			toDate		  	:"",
			locationId 		:locationLevelId,
			locationValue 	:locationLevelVal,
			electionScopeIds:electionScopeIds,
			partyId			:0
			
	}
	 $.ajax({
      type : "GET",
      url : "getElectionInformationLocationWiseAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	if(result !=null && result.length>0){
			buildElectionInformationLocationWise(result);
		}
	});	
}

function buildElectionInformationLocationWise(result){
	
	if(result !=null && result.length>0){
		/* var str='';
			
		str+='<div class="col-sm-12">';
		str+='<div class="table-responsive">';
				str+='<table class="table table-condensed table-hover table-striped m_top10">';
					str+='<thead>';
						str+='<tr>';
							str+='<th>Party</th>';
							for(var i in result){
								str+='<th>'+result[i].electionYear+'</th>';
							}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
							for(var i in result){
								str+='<tr>';
									str+='<td>'+result[i].partyName+'</td>';
									str+='<td>'+result[i].wonSeatsCount+'</td>';
								str+='</tr>';
							}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
		$("#electionDetailsTableWiseId").html(str); */
		var electionYearWiseArr =[];
		var mainDataArr=[];
			for(var i in result){
				electionYearWiseArr.push(result[i].electionYear+'  '+result[i].electionType);
				var wonSeatsCountArr=[];
				if(result[i].list !=null && result[i].list.length>0){
					for(var j in result[i].list){
						wonSeatsCountArr.push(parseFloat(result[i].list[j].wonSeatsCount));
						
						var obj ={
							name: result[0].list[j].partyName,
							data: wonSeatsCountArr
						}
						mainDataArr.push(obj)
					}
				}
			}
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
				categories: electionYearWiseArr,
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
				split: true,
				valueSuffix: ' Won',
				shared:true
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
//getCommitteeCount();
function getCommitteeCount(){
	userAccessLevelValuesArray=[];
	if(locationLevelId == '2')
	{
		userAccessLevelValuesArray.push(stateId)
	}else if(locationLevelId == '3')
	{
		userAccessLevelValuesArray.push(districtId)
	}else if(locationLevelId == '10')
	{
		userAccessLevelValuesArray.push(parliamentId)
	}else if(locationLevelId == '4' || locationLevelId == '11')
	{
		locationLevelId = '4';
		userAccessLevelValuesArray.push(constituencyId)
	}else if(locationLevelId == '5' || locationLevelId == '12')
	{
		locationLevelId = '5';
		userAccessLevelValuesArray.push(mandalId)
	}else if(locationLevelId == '6' || locationLevelId == '13')
	{
		locationLevelId = '6';
		userAccessLevelValuesArray.push(panchayatId)
	}
	
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