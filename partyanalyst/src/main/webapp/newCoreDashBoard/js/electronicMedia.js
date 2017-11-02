var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
   if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));   

var newsChannelsIdsGlbl = [1,2,3,4,5,6,7];
var impactScopeIds = [1,2,3,4,5,6,7,8,9];
var partyIdsGlob=[872,1117,163,362];
//var currentFromDateEmn = moment().startOf("year").format("DD-MM-YYYY");
var currentFromDateEmn = moment().format("DD-MM-YYYY");
var currentToDateEmn = moment().format("DD-MM-YYYY");

var locationLevelIdGlb = 2;
var locationValueArrGlb=[1];

function globalElectronicMediaCalls(type)
{
	if(type == "default"){
		$('#dateRangeIdForEmn').data('daterangepicker').setStartDate(moment());
		$('#dateRangeIdForEmn').data('daterangepicker').setEndDate(moment());
		currentFromDateEmn = moment().format("DD-MM-YYYY")
		currentToDateEmn = moment().format("DD-MM-YYYY")
		$("#emnHeadDate").html("TODAY"+" ( "+moment().format("DD-MM-YYYY")+"-"+moment().format("DD-MM-YYYY")+" )");
	}else if(type == "currentMonth"){
		$('#dateRangeIdForEmn').data('daterangepicker').setStartDate(moment().startOf("month"));
		$('#dateRangeIdForEmn').data('daterangepicker').setEndDate(moment().endOf("month"));
		currentFromDateEmn = moment().startOf("month").format("DD-MM-YYYY")
		currentToDateEmn = moment().endOf("month").format("DD-MM-YYYY")
		$("#emnHeadDate").html("THIS MONTH"+" ( "+moment().startOf("month").format("DD-MM-YYYY")+"-"+moment().endOf("month").format("DD-MM-YYYY")+" )");
	}else if(type == "lastMonth"){
		$('#dateRangeIdForEmn').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
		$('#dateRangeIdForEmn').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
		currentFromDateEmn = moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")
		currentToDateEmn = moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")
		$("#emnHeadDate").html("LAST MONTH"+" ( "+moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")+"-"+moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")+" )");
	}
	$("#dateRangeIdForEmn").val(currentFromDateEmn+" - "+currentToDateEmn);
	commoncalls()
	if($('.selectEmnCate').css('display') != 'none'){
		$(".viewsLiClassEmn").each(function(){
			if($(this).hasClass("active")){
				var id = $(this).attr("id");
				$( "#"+id).trigger( "click" );
			}
		});
	} 
}

	$(document).on("click",".emnRefresh",function(){
		globalElectronicMediaCalls('');
	});

$(document).ready(function(){
	locationLevelIdGlb = globalUserAccessLevelId;
	
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				locationValueArrGlb=i==0?globalUserAccessLevelValues[i]:locationValueArrGlb+","+globalUserAccessLevelValues[i];
			}
		}
});

/* 
$(document).on("click",".emnIconExpand",function(){
	$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".electronicMediaBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$(".emnHead").toggleClass('col-md-9 col-sm-9').toggleClass('col-md-8 col-sm-8');
	$(".emnHead1").toggleClass('col-md-3 col-sm-3').toggleClass('col-md-4 col-sm-4');
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		//before expand
		$(".dateRangePickerClsForEmn").removeClass("hide");
		$(".moreBlockEMN,.moreEmnBlocksIcon").show();
		getEMMDetailedPartyMediaProgramsOnPartyProgramsWise("top","program");
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $(".electronicMediaBlock").offset().top},
			'slow');
		},500);
	}else{
		//after expand	
		$(".dateRangePickerClsForEmn").addClass("hide");
		$(".moreBlockEMN,.selectEmnCate,.newEmnHideCls").hide();
	}
	
	if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
	}else if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForMeetings").toggleClass("hide");
		$(".moreMeetingsBlocks1").hide();
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
	}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForNews").toggleClass("hide");
	}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents,.comparisonBlockActivities ").hide();
		$(".panelBlockCollapseIcon").addClass("collapsed");
		$(".activitesExpandIcon").parent().parent().parent().parent().find(".collapse").removeClass("in").addClass("collapsed");
		$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".eventsListExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForEvents").toggleClass("hide");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".attendaceIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".attendaceIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".attendanceBlockMore,.moreAttBlocks,.moreAttBlocksIcon").hide();
		$(".dateRangePickerClsForAttendance").toggleClass('hide');
		$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".NewTourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".NewTourExpandCls,.NewToursHiddenBlock,.moreNewToursBlocksDetailed").hide();
			$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert,.alertComparisonblock").hide();
			$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".tourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".tourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".tourExpandCls ,.toursHiddenBlock,.moreToursBlocks1,.moreToursBlocksDetailed ,.comparisonBlockTours ,.toursDateRangePickerCls").hide();
				$(".toursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}
}); */
$(document).on("click",".moreEmnBlocksIcon",function(){
	
	//$(".newEmnHideCls").hide();
	//$(".detailedPartyEmn,.selectEmnCate").show();
	if($(this).hasClass("showHideEmn"))
	{
		$(this).removeClass("showHideEmn");
		$(".newEmnHideCls").hide();
		$(".detailedPartyEmn,.selectEmnCate").show();
		getEMMDetailedPartyDistrictWiseProgramsOverview("","");
		getEMMDetailedPartiesVsChannelsPartiesDistrictWise("party");
		getEMMDetailedPartyStateWiseProgramsOverview();	
		$("#detailedPartyLiIdEmn").addClass("active");	
	}else{
		$(".newEmnHideCls").hide();
		$(".detailedPartyEmn,.selectEmnCate").hide();
		setTimeout(function(){
			$(".moreEmnBlocksIcon").addClass("showHideEmn");
		},200);
	}
});

$(document).on("click","#detailedPartyLiIdEmn",function(){
	$(this).closest(".selectEmnCate").find(".viewsLiClassEmn").removeClass("active");
	$(this).addClass("active")
	$(".newEmnHideCls").hide();
	$(".detailedPartyEmn").show();
	getEMMDetailedPartyStateWiseProgramsOverview();
	getEMMDetailedPartiesVsChannelsPartiesDistrictWise("party");
	getEMMDetailedPartyDistrictWiseProgramsOverview("","");
});
$(document).on("click","#comparisonPartyLiIdEmn",function(){
	$(this).closest(".selectEmnCate").find(".viewsLiClassEmn").removeClass("active");
	$(this).addClass("active")
	$(".newEmnHideCls").hide();
	$(".compPartyEmn").show();
	$("#partyRankWiseDetailsOfChannelSub,#partyRankWiseCompDistWiseOvervw").html(' ');
	comparisonPartyRankWiseDetailsOfChannel();
});
$(document).on("click","#comparisonGovernmentLiIdEmn",function(){
	$(this).closest(".selectEmnCate").find(".viewsLiClassEmn").removeClass("active");
	$(this).addClass("active");
	$(".newEmnHideCls").hide();
	$(".compGovtEmn").show();
	$("#districtWiseEMNRelatedToProblemGovt,#emmCompGovtDistDetailedOverview,#govtRankWiseDetailsOfChannelSub").html(" ")
	comparisonGovtRankWiseDetailsOfChannel();
});

$(document).on("click","#detailedGovernmentLiIdEmn",function(){
	$(this).closest(".selectEmnCate").find(".viewsLiClassEmn").removeClass("active");
	$(this).addClass("active");
	$(".newEmnHideCls").hide();
	$(".detailedGovtEmn").show();
	getEMMDetailedGovtStateWiseProgramsOverview();
	if(locationLevelIdGlb ==2){
		getProblemsDetailedOverView();
	}	
	getEMMDetailedGovtProblemsDetailedOverview();
});

$(document).on("click",".emnSetIcon",function(e){
	$(this).closest(".electronicMediaBlock").find(".emnBlockDropDown").toggle();
	e.stopPropagation();
});
$(document).on("click",".emnSetClose",function(){
	$(this).closest(".emnBlockDropDown").hide();
});

$(document).on("click","#impactSelectAllIdEmn",function(){
	 if ($(this).prop('checked')) {
		$(".impactCheckClsEmn").prop('checked', true);
	} else {
		$(".impactCheckClsEmn").prop('checked', false);
	}
});
$(document).on("click","#newsChannelSelectAllIdEmn",function(){
	 if ($(this).prop('checked')) {
		$(".newsChannelSelectAllClsEmn").prop('checked', true);
	} else {
		$(".newsChannelSelectAllClsEmn").prop('checked', false);
	}
});

$(document).on("click",".impactCheckClsEmn",function(){
	var checkAll = false;
	$(".impactCheckClsEmn").each(function(){
		if (!$(this).prop('checked')) {
			checkAll = true;
		}
	});
	
	if(checkAll){
		$("#impactSelectAllIdEmn").prop('checked', false);
	}else{
		$("#impactSelectAllIdEmn").prop('checked', true);
	}
	
});
$(document).on("click",".filtersSubmitDivIdEmn",function(){
	$(this).closest(".emnBlockDropDown").hide();
	newsChannelsIdsGlbl=[];
	$(".newsChannelSelectAllClsEmn").each(function(){
		if($(this).is(":checked")){
			newsChannelsIdsGlbl.push($(this).val());
		}
	});
	
	impactScopeIds = [];
	$(".impactCheckClsEmn").each(function(){
		if($(this).is(":checked")){
			impactScopeIds.push($(this).val());
		}
	});
	
	if(newsChannelsIdsGlbl == null || newsChannelsIdsGlbl.length == 0){
		alert("Please Select Atleast One NewsPaper");
		return;
	}else if(impactScopeIds == null || impactScopeIds.length == 0){
		alert("Please Select Impact Scope");
		return;
	}else{
		console.log("commonBasic")
		//commonNewsBasicCalls();
	}
	
	getMediaProgramsOnParty(locationLevelIdGlb,locationValueArrGlb);
	if($(".emnIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		var searchType = $(".emnMediaPrograms .active").attr("attr_searchType");
		getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,"program");
	}
	var activeUlId = $(".viewsLiClassEmn.active").attr('id')
	$( "#"+activeUlId).trigger("click");
});


//$("#emnHeadDate").html("This Month("+currentFromDateEmn+ " to " +currentToDateEmn+")");  
$("#emnHeadDate").html("Today("+currentFromDateEmn+")");    
$("#dateRangeIdForEmn").daterangepicker({          
	
	opens: 'left',
	startDate: moment(),
	endDate: moment(),
	locale: {
	  format: 'DD-MM-YYYY'
	},
	ranges: {
		'Today' : [moment(), moment()],
		'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		'This Month': [moment().startOf('month'), moment()],
		'This Year': [moment().startOf('Year'), moment()]
	}
});
$('.dateRangePickerClsForEmn').on('apply.daterangepicker', function(ev, picker) {
	currentFromDateEmn = picker.startDate.format('DD-MM-YYYY');
	currentToDateEmn = picker.endDate.format('DD-MM-YYYY');
	$("#emnHeadDate").html("("+picker.startDate.format("DD/MM/YY")+" to "+picker.endDate.format("DD/MM/YY")+")");	
	
	var activeUlId = $(".viewsLiClassEmn.active").attr('id')
	$( "#"+activeUlId).trigger("click");
	commoncalls();
});

function commoncalls(){
	var searchType = $(".emnMediaPrograms .active").attr("attr_searchType");
	//var type = $('input[name=emnSearchTypeName]:checked').val();
	var type = "program";
	getMediaProgramsOnParty(locationLevelIdGlb,locationValueArrGlb);
	getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,type);
}

function getAllTvChannels()
{

  $.ajax({
    url: wurl+"/CommunityNewsPortal/webservice/getAllTvChannels"
    //url: "http://localhost:8080/CommunityNewsPortal/webservice/getAllTvChannels"
  }).then(function(result){
	  $("#emnNewsChannelsUlId").html("");
		if(result != null && result.length > 0){
			var str="";
			str+='<li>';
			str+='<label class="checkbox-inline">';
			str+='<input id="newsChannelSelectAllIdEmn" type="checkbox" value="0" class="">';
			str+='<div style="margin-top: 3px;"><h5 style="color:#54616C;" class="text-capital">Select All</h5></div>';
			str+='</label>';
			str+='</li>';
			for(var i in result){
				str+='<li>';
				str+='<label class="checkbox-inline">';//alert(jQuery.inArray(result[i].id, newsPaperIdsGlob));
				//str+='<input type="checkbox"  value="'+result[i].id+'" class="newsChannelSelectAllClsEmn">';
				if($.inArray(result[i].id, newsChannelsIdsGlbl) != -1){
					str+='<input type="checkbox"  value="'+result[i].id+'" class="newsChannelSelectAllClsEmn" checked="checked">';
				}else{
					str+='<input type="checkbox"  value="'+result[i].id+'" class="newsChannelSelectAllClsEmn">';
				}
				str+='<div style="margin-top: 3px;"><h5 style="color:#54616C;" class="text-capital">'+result[i].name+'</h5></div>';
				str+='</label>';
				str+='</li>';
			}
			$("#emnNewsChannelsUlId").html(str);
			var newspaperlenght = $("#emnNewsChannelsUlId").find("li").length;
			if(newspaperlenght >= 7){
				$(".settingsUlEmn").mCustomScrollbar({setHeight:'245'})			
			}else{
				$(".settingsUlEmn").css("height","auto");			
			}
		}
  });
}
$(document).on("click",".electronicMediaOthrPartiesBtn",function(){
	if($(this).html() == '+')
	{
		$(this).html("-")
	}else{
		$(this).html("+")
	}
	$(".electronicMediaOthrParties").toggle();
});
function getMediaProgramsOnParty(locationLevelIdGlb,locationValueArrGlb)
{
	$("#electronicMediaChannelCountId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getNewsBulletinPointBasicDetails/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBulletinPointBasicDetails/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N"
	}).then(function(result){
		getpartyWiseChannelCounts(result);
	});
}


function getpartyWiseChannelCounts(result){
	var str='';
	if(result != null && result.length > 0)
	{
		str+='<h4 class="text-capital"><span class="headingColor" style="margin-right:5px"><img src="newCoreDashBoard/img/TDP.png" alt="tdp icon" class="newsIcon"/>Telugu Desam Party</span></h4>';
		if($(window).width() < 500)
		{
			str+='<div class="table-responsive m_XsTop10">';
		}
		str+='<table class="table tableEMN m_top10">';
			str+='<tr>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Total</h5>';
					str+='<h4><a class="basicBulletinsCls" attr_orgId='+result[0].organizationId+' attr_categoryId="1,2" attr_benefitId="1,2" attr_orgtype="N" attr_searchType="category" style="cursor:pointer">'+result[0].categoryCount+'</a></h4>';
					str+='<h5	 class="text-capitalize m_top20">total time</h5>';
					str+='<h4>'+result[0].description != ""?result[0].description:"00:00"+'</h4>';
				str+='</td>';
				for(var j in result[0].tvNewsDetailsVOList)
				{
					str+='<td>';
						str+='<h5 class="text-capitalize">'+result[0].tvNewsDetailsVOList[j].organization+'</h5>';
						
						if(result[0].tvNewsDetailsVOList[j].title=="category"){
							str+='<h4><a class="basicBulletinsCls" attr_orgId='+result[0].organizationId+' attr_categoryId='+result[0].tvNewsDetailsVOList[j].organizationId+' attr_benefitId=" " attr_orgtype="N" attr_searchType="category" style="cursor:pointer">'+result[0].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
						}else if(result[0].tvNewsDetailsVOList[j].title=="benefit"){
							str+='<h4><a class="basicBulletinsCls" attr_orgId='+result[0].organizationId+' attr_categoryId=" " attr_benefitId="'+result[0].tvNewsDetailsVOList[j].organizationId+'" attr_orgtype="N" attr_searchType="benefit" style="cursor:pointer">'+result[0].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
						}
						
						
						str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
						//str+='<h4>'+result[0].tvNewsDetailsVOList[j].description+'</h4>';
						if(result[0].tvNewsDetailsVOList[j].description!= ""){
							str+='<h4>'+result[0].tvNewsDetailsVOList[j].description+'</h4>';
						}else{
							str+='<h4>00:00</h4>';
						}
					str+='</td>';
				}
			str+='</tr>';
		str+='</table>';
		if($(window).width() < 500)
		{
			str+='</div>';
		}
		str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/opp.png" style="width:25px;" alt="tdp icon" class="debatesPartyIcon"/>Other Parties</span><span class="pull-right electronicMediaOthrPartiesBtn" style="cursor: pointer;padding: 0px 5px;font-size: 15px;border: 1px solid #333;">+</span></h4>';
		str+='<div class="electronicMediaOthrParties" style="display:none">';
		for(var i=1;i<(result.length-1);i++)
		{
			str+='<h4 class="panel-title m_top10"><img src="newCoreDashBoard/img/'+result[i].organization+'.png" class="debatesPartyIcon"/>'+result[i].organization+'</h4>';
			if($(window).width() < 500)
			{
				str+='<div class="table-responsive m_XsTop10">';
			}
			str+='<table class="table tableEMN m_top10">';
				str+='<tr>';
					str+='<td>';
						str+='<h5 class="text-capitalize">Total</h5>';
						str+='<h4><a class="basicBulletinsCls" attr_orgId='+result[i].organizationId+' attr_categoryId="1,2" attr_benefitId="1,2" attr_orgtype="N" attr_searchType="category" style="cursor:pointer">'+result[i].categoryCount+'</a></h4>';
						str+='<h5 class="text-capitalize m_top20">total time</h5>';
						str+='<h4>'+result[i].description != ""?result[i].description:"00:00"+'</h4>';
					str+='</td>';
					for(var j in result[i].tvNewsDetailsVOList)
					{
						str+='<td>';
							str+='<h5 class="text-capitalize">'+result[i].tvNewsDetailsVOList[j].organization+'</h5>';
							
							if(result[i].tvNewsDetailsVOList[j].title=="category"){
								str+='<h4><a class="basicBulletinsCls" attr_orgId='+result[i].organizationId+' attr_categoryId='+result[i].tvNewsDetailsVOList[j].organizationId+' attr_benefitId=" " attr_orgtype="N" attr_searchType="category" style="cursor:pointer">'+result[i].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
							}else if(result[i].tvNewsDetailsVOList[j].title=="benefit"){
								str+='<h4><a class="basicBulletinsCls" attr_orgId='+result[i].organizationId+' attr_categoryId=" " attr_benefitId='+result[i].tvNewsDetailsVOList[j].organizationId+' attr_orgtype="N" attr_searchType="benefit" style="cursor:pointer">'+result[i].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
							}
							str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
							str+='<h4>'+result[i].tvNewsDetailsVOList[j].description != ""?result[i].tvNewsDetailsVOList[j].description:"00:00"+'</h4>';
							
							
						str+='</td>';
					}
				str+='</tr>';
			str+='</table>';
			if($(window).width() < 500)
			{
				str+='</div>';
			}
		}
		str+='</div>';
		str+='<div style="margin-top:20px;">';
		str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/GOVT.png" style="width:25px;" alt="government icon" class="newsIcon"/>Government</span></h4>';
		var EmnG = result.length-1;
		if($(window).width() < 500)
		{
			str+='<div class="table-responsive m_XsTop10">';
		}
		str+='<table class="table tableEMN m_top10">';
			str+='<tr>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Total</h5>';
					str+='<h4><a class="basicBulletinsCls" attr_orgId=" " attr_categoryId="1,2" attr_benefitId="1,2" attr_orgtype="Y" attr_searchType="category" style="cursor:pointer">'+result[EmnG].categoryCount+'</a></h4>';
					str+='<h5 class="text-capitalize m_top20">total time</h5>';
					str+='<h4>'+result[EmnG].description != ""?result[EmnG].description:"00:00"+'</h4>';
				str+='</td>';
				for(var j in result[EmnG].tvNewsDetailsVOList)
				{
					str+='<td>';
						str+='<h5 class="text-capitalize">'+result[EmnG].tvNewsDetailsVOList[j].organization+'</h5>';
						if(result[EmnG].tvNewsDetailsVOList[j].title =="category"){
							str+='<h4><a class="basicBulletinsCls" attr_orgId=" " attr_categoryId='+result[EmnG].tvNewsDetailsVOList[j].organizationId+' attr_benefitId=" " attr_orgtype="Y" attr_searchType="category" style="cursor:pointer">'+result[EmnG].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
						}else if(result[EmnG].tvNewsDetailsVOList[j].title =="benefit"){
							str+='<h4><a class="basicBulletinsCls" attr_orgId=" " attr_categoryId=" " attr_benefitId='+result[EmnG].tvNewsDetailsVOList[j].organizationId+' attr_orgtype="Y" attr_searchType="benefit" style="cursor:pointer">'+result[EmnG].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
						}
						
						str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
						str+='<h4>'+result[EmnG].tvNewsDetailsVOList[j].description+'</h4>';
					str+='</td>';
				}
			str+='</tr>';
		str+='</table>';
		str+='</div>';
		if($(window).width() < 500)
		{
			str+='</div>';
		}
	}else{
		str+='<h4 class="panel-title m_top10">NO DATA AVAILABLE</h4>';
	}
	$("#electronicMediaChannelCountId").html(str);
	$("#electronicMediaChannelCountId").mCustomScrollbar({setHeight:'345spx'});
}
function getEMMDetailedPartyDistrictWiseProgramsOverview(channelArr,type)
{
	if(type== "comparison")
	{
		$("#partyRankWiseCompDistWiseOvervw").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+channelArr+"/"+impactScopeIds+"/"+partyIdsGlob+"/N"
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+channelArr+"/"+impactScopeIds+"/"+partyIdsGlob+"/N"
		}).then(function(result){
			buildEMMComparisonPartyDistrictWiseProgramsOverview(result);
		});
	}else{
		$("#districtWiseProgramsOvrViewEMN").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N"
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N"
		}).then(function(result){
			buildEMMDetailedPartyDistrictWiseProgramsOverview(result);
		});
	}
	
}
function buildEMMDetailedPartyDistrictWiseProgramsOverview(result)
{
	var str='';
				
	for(var i in result){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h4 class="text-capital"><img src="newCoreDashBoard/img/'+result[i].organization+'.png" style="width:25px;" alt="'+result[i].organization+' icon" class="debatesPartyIcon">'+result[i].organization+'</h4>';
		str+='<div id="EMNDistrictWiseGraph'+i+'" style="height:300px"></div>';
		str+='</div>'
	}
	$("#districtWiseProgramsOvrViewEMN").html(str);
	for(var i in result){
		var locationNameArr = [];
		var PositiveCntArr = [];
		var NegativeCntArr = [];
		
		for(var j in result[i].tvNewsDetailsVOList1){
			locationNameArr.push(result[i].tvNewsDetailsVOList1[j].organization)
			if(locationLevelIdGlb == 2){
				PositiveCntArr.push({"y":result[i].tvNewsDetailsVOList1[j].positiveTime,"extra":"3-"+result[i].tvNewsDetailsVOList1[j].organizationId+"-"+result[i].organizationId+"-1"});
				NegativeCntArr.push({"y":result[i].tvNewsDetailsVOList1[j].negativeTime,"extra":"3-"+result[i].tvNewsDetailsVOList1[j].organizationId+"-"+result[i].organizationId+"-2"});
			}else{
				PositiveCntArr.push({"y":result[i].tvNewsDetailsVOList1[j].positiveTime,"extra":locationLevelIdGlb+"-"+locationValueArrGlb+"-"+result[i].organizationId+"-1"});
				NegativeCntArr.push({"y":result[i].tvNewsDetailsVOList1[j].negativeTime,"extra":locationLevelIdGlb+"-"+locationValueArrGlb+"-"+result[i].organizationId+"-2"});
			}
		}
		
		$("#EMNDistrictWiseGraph"+i).highcharts({
			colors: ['#64C664','#D33E39'],
			chart: {
				type: 'column'
			},
			title: {
				text: null
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: locationNameArr,
				labels: {
						rotation: -45,
						style: {
							fontSize: '13px',
							fontFamily: 'Verdana, sans-serif'
						}
					}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			legend: {
				enabled: true,
				backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
				borderColor: '#CCC',
				borderWidth: 1,
				shadow: false
			},
			tooltip: {
				enabled:false,
				formatter: function () {
					var s = '<b>' + this.x + '</b>';

					$.each(this.points, function () {
						s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
							(this.y);
					});

					return s;
				},
				shared: true
			},
			
			plotOptions: {
				column: {
					stacking: 'percent',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y);
							}
						}
					  
					}
					
				},
				series: {
					cursor: 'pointer',
					point: {
						events: {
							click: function () {
								getEMMDetailedPartyDistrictWiseProgramsBulletinDetailsOverview(this.extra);
							}
						}
					}
				},
			},
			series: [{
				name: 'Positive',
				data: PositiveCntArr,
				stack: 'programs'
			}, {
				name: 'Negative',
				data: NegativeCntArr,
				stack: 'programs'
			}]
		});

	}
}

function getEMMDetailedPartyDistrictWiseProgramsBulletinDetailsOverview(val){
	 var t = val.split("-");
	  
	  window.open('showElectronicBulletinsAction.action?levelId='+t[0]+'&temp='+t[1]+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&npsStr='+newsChannelsIdsGlbl+'&scops='+impactScopeIds+'&orgIdStr='+t[2]+'&orgType=N&bfIds='+t[3]+'&stIdx=0&edIdx=6&callFrom=dpdpd','_blank');
	
}
function getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,type)
{
	$("#electronicMediaPrograms").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	if(type == 'program')
	{
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyProgramsWise/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/"+searchType+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyProgramsWise/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/"+searchType+""
		}).then(function(result){
			buildEmmDetailedPartyMediaProgramsOnParty(result);
		});
	}else{
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyTimeWise/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/"+searchType+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyTimeWise/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/"+searchType+""
		}).then(function(result){
			buildEmmDetailedPartyMediaProgramsOnPartyTimeWise(result);
		});
	}
	
}

function buildEmmDetailedPartyMediaProgramsOnParty(result)
{
	var str='';
	str+='<div class="row">';
	for(var i in result){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<h5 class="text-capital">'+result[i].organization+'</h5>';
			str+='<div id="emNewsBlockGenSecStrong'+i+'" style="height:130px;"></div>';
		str+='</div>'
	}
	str+='</div>'
	$("#electronicMediaPrograms").html(str);
	for(var i in result){
		var PositiveCountArray = [];
		var NegativeCountArray = [];
		var candidateNameArray=[];
		var countVar =0;
		
		
		for(var j in result[i].tvNewsDetailsVOList1){
			candidateNameArray.push(result[i].tvNewsDetailsVOList1[j].channelName)
			//PositiveCountArray.push(result[i].tvNewsDetailsVOList1[j].count)
			 PositiveCountArray.push({"y":result[i].tvNewsDetailsVOList1[j].count,"extra":result[i].organizationId+"-"+result[i].tvNewsDetailsVOList1[j].channelId+"-"+result[i].organization});

				
			countVar =countVar+1;
			if (countVar === 5) {
				break;
			}
		}
		
		$("#emNewsBlockGenSecStrong"+i).highcharts({
			colors: ['#339A99'],
			chart: {
				type: 'column'
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
				categories: candidateNameArray,
				type: 'category',
				labels: {
						formatter: function() {
							return this.value;
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
				labels: {
					enabled:false
				}
			},
			legend: {
				enabled: false
			},
			
					
			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y);
							}
						}
					  
					}
				},
				series: {
					cursor: 'pointer',
					point: {
					  events: {
						click: function () {
						  getArticlesForPartyDetailedDistEdiPartiesOverViewElectronic(this.extra);
						}
					  }
					}
				}	
			},

			 tooltip: {
				enabled:false,
				formatter: function () {
					var s = '<b>' + this.x + '</b>';

					$.each(this.points, function () {
						s += '<br/>'+(this.y);
					});

					return s;
				},
				shared: true
			},

			series: [{
				name: 'Negative',
				data: PositiveCountArray,
				
			}],
		 
		});
	}
	
}
function getArticlesForPartyDetailedDistEdiPartiesOverViewElectronic(val){
	  var t = val.split("-");
	  var orgId =" ";
	  if(t[0] !=null && t[0] !=0){
		  orgId = t[0];
	  }
	  var orgType="";
	  if(t[2] == "GOVT"){
		  orgType = "Y";
	  }else{
		   orgType = "N";
	  }
	  
	  window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&scops='+impactScopeIds+'&orgIdStr='+orgId+'&orgType='+orgType+'&npsStr='+t[1]+'&stIdx=0&edIdx=6&callFrom=pds','_blank');
	      
	}

function buildEmmDetailedPartyMediaProgramsOnPartyTimeWise(result)
{
	var str='';
	str+='<div class="row">';
	for(var i in result){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<h5 class="text-capital">'+result[i].organization+'</h5>';
			str+='<div id="emNewsBlockGenSecStrong'+i+'" style="height:130px;"></div>';
		str+='</div>'
	}
	str+='</div>'
	$("#electronicMediaPrograms").html(str);
	for(var i in result){
		var PositiveCountArray = [];
		var NegativeCountArray = [];
		var candidateNameArray=[];
		var timeCounts=[];
		var countVar =0;
		
		
		for(var j in result[i].tvNewsDetailsVOList1){
			candidateNameArray.push(result[i].tvNewsDetailsVOList1[j].channelName);
			PositiveCountArray.push(result[i].tvNewsDetailsVOList1[j].coveredTime);
			timeCounts.push(result[i].tvNewsDetailsVOList1[j].description);
			countVar =countVar+1;
			if (countVar === 5) {
				break;
			}
		}
		
		$("#emNewsBlockGenSecStrong"+i).highcharts({
			colors: ['#339A99'],
			chart: {
				type: 'column'
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
				categories: candidateNameArray,
				type: 'category',
				labels: {
						formatter: function() {
							return this.value;
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
				labels: {
					enabled:false
				}
			},
			legend: {
				enabled: false
			},
			
					
			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: false,
						 formatter: function() {
							var k = '<b>' + this.x + '</b>';
							var i = 0;
							$.each(this.points, function () {
								k += '<br/>'+timeCounts;
							});
						}
					  
					}
				}
			},

			 tooltip: {
				formatter: function () {
					var s = '<b>' + this.x + '</b>';

					$.each(this.points, function () {
						s += '<br/>'+(this.y);
					});

					return s;
				},
				shared: true
			},

			series: [{
				name: 'Negative',
				data: PositiveCountArray,				
			}],
		 
		});
	}
	
}
$(document).on("click",".emnMediaPrograms li",function(){
	var searchType = $(this).attr("attr_searchType");
	var type = $('input[name=emnSearchTypeName]:checked').val();

	getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,"program");
});

$(document).on("click",".emnSearchTypeCls",function(){
	var searchType = '';
	if($('.emnMediaPrograms li.active').attr("attr_searchType") == "top")
	{
		searchType="top";
	}else{
		searchType="poor";
	}
	var type = $('input[name=emnSearchTypeName]:checked').val();
	getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,"program");
});
$(document).on("click",".distWiseProgramOvrView li",function(){
	var type = $(this).attr("attr_type");
	if(type == 'party')
	{
		getEMMDetailedPartiesVsChannelsPartiesDistrictWise(type);
	}else{
		getEMMDetailedPartiesVsChannelsPartiesDistrictWise(type);
	}
	
});

function getEMMDetailedPartiesVsChannelsPartiesDistrictWise(type)
{
	
	$("#partiesVsChannelsEMN").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	if(type == 'party')
	{
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsPartiesDistrictWise/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/"
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsPartiesDistrictWise/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/"
		}).then(function(result){
			buildEMMDetailedPartiesVsChannelsPartiesDistrictWise(result);
		});
	}else{
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsTvChannelWise/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/"
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsTvChannelWise/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/"
		}).then(function(result){
			buildgetEMMDetailedPartiesVsChannelsTvChannelWise(result);
		});
	}
	
}
function buildEMMDetailedPartiesVsChannelsPartiesDistrictWise(result)
{
	var str = '';
	if(result !=null && result.length >0){
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-ms-12">';
				str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
					str+='<h4  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;"><img src="newCoreDashBoard/img/'+result[i].organization+'.png" class="debatesPartyIcon" />'+result[i].organization+'</h4>';
				str+='</div>';
				str+='<div class="col-xs-11 col-sm-10 col-md-11">';
					str+='<ul class="partyVsChannelSlickApplyEmn" style="padding:0px;">';
					for(var j in result[i].tvNewsDetailsVOList)
					{
						str+='<li><h4 class="panel-title">'+result[i].tvNewsDetailsVOList[j].organization+'</h4><div id="partyVsChannelGraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
					}
					str+='</ul>';
				str+='</div>';
			str+='</div>';	
		}
	}else{
		str+='<h4>NO DATA AVAILABLE</h4>';
	}
	$("#partiesVsChannelsEMN").html(str);
	
	for(var i in result)
	{
		for(var j in result[i].tvNewsDetailsVOList)
		{
			var positivePercArrayEmn = []
			var negativePercArrayEmn = []
			var paperNamesArrayEmn = []
			for(var k in result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList)
			{
				if(locationLevelIdGlb == 2){
					
				positivePercArrayEmn.push({"y":result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList[k].positivePerc,"extra":"3-"+result[i].tvNewsDetailsVOList[j].organizationId+"-"+result[i].organizationId+"-1"});
				negativePercArrayEmn.push({"y":result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList[k].negativePerc,"extra":"3-"+result[i].tvNewsDetailsVOList[j].organizationId+"-"+result[i].organizationId+"-2"});
				
				}else{
					
					positivePercArrayEmn.push({"y":result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList[k].positivePerc,"extra":locationLevelIdGlb+"-"+locationValueArrGlb+"-"+result[i].organizationId+"-1"});
					negativePercArrayEmn.push({"y":result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList[k].negativePerc,"extra":locationLevelIdGlb+"-"+locationValueArrGlb+"-"+result[i].organizationId+"-2"});
				}
					paperNamesArrayEmn.push(result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList[k].organization)
			}
			$('#partyVsChannelGraph'+i+''+j+'').highcharts({
				colors: ['#64C664','#D33E39'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: paperNamesArrayEmn,
					labels: {
							rotation: -45,
							style: {
								fontSize: '13px',
								fontFamily: 'Verdana, sans-serif'
							}
						}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					stackLabels: {
						enabled: false,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					enabled:false,
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								(this.y)+' %';
						});

						return s;
					},
					shared: true
				},
				
				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y)+'%';
								}
							}
						  
						}
					},
					series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function () {
									getEMMDetailedPartiesDistrictWiseOverview(this.extra);
								}
							}
						}
					}
				},
				series: [{
					name: 'Positive',
					data: positivePercArrayEmn,
					stack: 'programs'
				}, {
					name: 'Negative',
					data: negativePercArrayEmn,
					stack: 'programs'
				}]
			});
			/* $('#partyVsChannelGraph'+i+''+j+'').highcharts({
				 colors: ['#64C664','#D33E39'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
			   
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: paperNamesArrayEmn,
					labels: {
							rotation: -45,
							style: {
								fontSize: '13px',
								fontFamily: 'Verdana, sans-serif'
							},
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
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								(this.y)+' %';
						});

						return s;
					},
					shared: true
				},
				legend: {
										
						enabled: false,				
										
					},				
				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'percent',
						dataLabels:{
							enabled: false,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							}
						},
						
					},
				},
				series: [{
					name: 'Positive',
					data: positivePercArrayEmn,
					stack: 'TvNews'
				}, {
					name: 'Negative',
					data: negativePercArrayEmn,
					stack: 'TvNews'
				}]
			}); */
		}
	}
	$(".partyVsChannelSlickApplyEmn").slick({
		 slide: 'li',
		 slidesToShow: 4,
		 slidesToScroll: 3,
		 infinite: false,
		 swipeToSlide:false,
		 swipe:false,
		 touchMove:false,
		 responsive: [
			{
			  breakpoint: 1024,
			  settings: {
				slidesToShow: 3,
				slidesToScroll: 3
			  }
			},
			{
			  breakpoint: 768,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			},
			{
			  breakpoint: 600,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			},
			{
			  breakpoint: 480,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			}
			// You can unslick at a given breakpoint now by adding:
			// settings: "unslick"
			// instead of a settings object
		  ]
	}); 
	
}
function getEMMDetailedPartiesDistrictWiseOverview(val){
	 var t = val.split("-");
	  
	  window.open('showElectronicBulletinsAction.action?levelId='+t[0]+'&temp='+t[1]+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&npsStr='+newsChannelsIdsGlbl+'&scops='+impactScopeIds+'&orgIdStr='+t[2]+'&orgType=N&bfIds='+t[3]+'&stIdx=0&edIdx=6&callFrom=dpdwd','_blank');
	
}
function buildgetEMMDetailedPartiesVsChannelsTvChannelWise(result)
{
	var str = '';
	if(result !=null && result.length >0){
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-ms-12">';
				str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
					str+='<h4  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;"><img src="newCoreDashBoard/img/'+result[i].channelName+'.png" class="debatesPartyIcon" />'+result[i].channelName+'</h4>';
				str+='</div>';
				str+='<div class="col-xs-11 col-sm-10 col-md-11">';
					str+='<ul class="partyVsChannelSlickApplyEmn">';
					for(var j in result[i].tvNewsDetailsVOList1)
					{
						str+='<li><div id="partyVsChannelGraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
					}
					str+='</ul>';
				str+='</div>';
			str+='</div>';	
		}
	}else{
		str+='<h4>NO DATA AVAILABLE</h4>';
	}
	$("#partiesVsChannelsEMN").html(str);
	
	for(var i in result)
	{
		//channelNameEmn.push(result[i].channelName	)
		for(var j in result[i].tvNewsDetailsVOList1)
		{
			var channelNameEmn =[]
			var positivePercArrayEmn = []
			var negativePercArrayEmn = []
			var paperNamesArrayEmn = []
				
				positivePercArrayEmn.push({"y":result[i].tvNewsDetailsVOList1[j].positivePerc,"extra":result[i].tvNewsDetailsVOList1[j].organizationId+"-"+result[i].channelId+"-1"});
				negativePercArrayEmn.push({"y":result[i].tvNewsDetailsVOList1[j].negativePerc,"extra":result[i].tvNewsDetailsVOList1[j].organizationId+"-"+result[i].channelId+"-2"});
				
				paperNamesArrayEmn.push(result[i].tvNewsDetailsVOList1[j].organization)
		
			$('#partyVsChannelGraph'+i+''+j+'').highcharts({
				 colors: ['#64C664','#D33E39'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
			   
				xAxis: {
					 min: 0,
						 gridLineWidth: 0,
						 minorGridLineWidth: 0,
						 categories: paperNamesArrayEmn,
					labels: {
							rotation: -45,
							style: {
								fontSize: '13px',
								fontFamily: 'Verdana, sans-serif'
							},
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
				tooltip: {
					enabled:false,
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
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
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y)+'%';
								}
							}
						  
						}
					},
					series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function () {
									getEMMDetailedChannelsPartiesDistrictWiseOverview(this.extra);
								}
							}
						}
					}
				},
				series: [{
					name: 'Positive',
					data: positivePercArrayEmn,
					stack: 'TvNewsPrograms'
				}, {
					name: 'Negative',
					data: negativePercArrayEmn,
					stack: 'TvNewsPrograms'
				}]
			});
		}
	}
	$(".partyVsChannelSlickApplyEmn").slick({
		 slide: 'li',
		 slidesToShow: 4,
		 slidesToScroll: 3,
		 infinite: false,
		 swipeToSlide:false,
		 swipe:false,
		 touchMove:false,
		 responsive: [
			{
			  breakpoint: 1024,
			  settings: {
				slidesToShow: 3,
				slidesToScroll: 3
			  }
			},
			{
			  breakpoint: 768,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			},
			{
			  breakpoint: 600,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			},
			{
			  breakpoint: 480,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			}
			// You can unslick at a given breakpoint now by adding:
			// settings: "unslick"
			// instead of a settings object
		  ]
	}); 
	
}
function getEMMDetailedChannelsPartiesDistrictWiseOverview(val){
	
	 var t = val.split("-");
	  
	  window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&npsStr='+t[1]+'&scops='+impactScopeIds+'&orgIdStr='+t[0]+'&orgType=N&bfIds='+t[2]+'&stIdx=0&edIdx=6&callFrom=dpcwd','_blank');
	
	
}
/*detailed Govt */
function getProblemsDetailedOverView()
{
	$("#stateWiseEMNRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getProblemsDetailedOverView/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getProblemsDetailedOverView/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/"
	}).then(function(result){
		buildProblemsDetailedOverView(result);
		buildDistrictWiseEMNRelatedToProblem(result);
	});
}
function getEMMDetailedGovtProblemsDetailedOverview()
{
	$("#districtWiseEMNRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#emmDetailedGovtDistDetailedOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/"
	}).then(function(result){
		buildEMMDetailedGovtDistDetailedOverview(result);
		buildEMMDetailedGovtProblemsDetailedOverview(result);
	});
}
function buildEMMDetailedGovtDistDetailedOverview(result)
{
	var str='';
	if(result !=null){
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h3 class="m_top20">DISTRICT WISE</h3>';
				str+='<div id="comaprisonDistrictWiseArticleEmn" class="m_top20" style="height:150px;"></div>';
			str+='</div>';
		str+='</div>';
		
		var distWiseArticlesRelated = [];
		if(result.tvNewsDetailsVOList1 !=null && result.tvNewsDetailsVOList1.length>0){
			for(var i in result.tvNewsDetailsVOList1){
				if(locationLevelIdGlb == 2){
						var obj1 = {
							name: result.tvNewsDetailsVOList1[i].organization,
							y: result.tvNewsDetailsVOList1[i].positivePerc,
							extra:result.tvNewsDetailsVOList1[i].count,
							extra1:"3-"+result.tvNewsDetailsVOList1[i].organizationId
						};
				}else{
					var obj1 = {
							name: result.tvNewsDetailsVOList1[i].organization,
							y: result.tvNewsDetailsVOList1[i].positivePerc,
							extra:result.tvNewsDetailsVOList1[i].count,
							extra1:locationLevelIdGlb+"-"+locationValueArrGlb
						};
				}		
				distWiseArticlesRelated.push(obj1);
			}
				
			
		}
		$("#emmDetailedGovtDistDetailedOverview").html(str);
		if(distWiseArticlesRelated !=null && distWiseArticlesRelated.length>0){
			$(function () {
				 $("#comaprisonDistrictWiseArticleEmn").highcharts({
					colors: ['#AA3732'],
					chart: {
						type: 'column'
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
						
						type: 'category',
						labels: {
									formatter: function() {
										return this.value.toString().substring(0, 10)+'...';
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
						labels: {
							enabled:false
						}
					},
					legend: {
						enabled: false
					},
					
							
					plotOptions: {
						column: {
							stacking: 'normal',
							dataLabels: {
								enabled: true,
								 formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y,1) + '%';
									}
								}
							  
							}
						},
						series: {
							cursor: 'pointer',
							point: {
								events: {
									click: function () {
										getEMMDetailedGovtDistDetailedBulletinDetailsOverview(this.extra1);
									}
								}
							}
						},
					},

					tooltip: {
						headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						pointFormat: '<span style="color:{point.color}">{point.name}</span>: - {point.extra}</b>'
					},

					series: [{						
						data: distWiseArticlesRelated
					}],
				 
				});
			});
		}
	}
}
function getEMMDetailedGovtDistDetailedBulletinDetailsOverview(val){
	 var t = val.split("-");
	 
	 window.open('showElectronicBulletinsAction.action?levelId='+t[0]+'&temp='+t[1]+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&npsStr='+newsChannelsIdsGlbl+'&scops='+impactScopeIds+'&orgIdStr= &orgType=Y&stIdx=0&edIdx=6&callFrom=dgpdiswd');
}
function buildEMMDetailedGovtProblemsDetailedOverview(result)
{
	var str='';
	if(result !=null){
		var deptWiseEMNRelatedGraph = [];
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				str+='<h4>DEPARTMENTS WISE</h4>';
					str+='<div class="col-md-7 col-xs-12 col-sm-6 m_top10">';
					str+='<ul style="list-style:none;padding-left:0px;" id="getLiLength" class="textAlignDepartment">';
					if(result.tvNewsDetailsDeptVOList !=null && result.tvNewsDetailsDeptVOList.length>0){
						for(var i in result.tvNewsDetailsDeptVOList)
						{
							str+='<li>'+result.tvNewsDetailsDeptVOList[i].organization+' <a style="cursor:pointer"><span class="pull-right showDepartmentWiseProblemForState" attr_orgId ="'+result.tvNewsDetailsDeptVOList[i].organizationId+'" attr_channelIds="'+newsChannelsIdsGlbl+'">'+result.tvNewsDetailsDeptVOList[i].count+'</span></a></li>';
						}
					}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-md-5 col-xs-12 col-sm-6">';
					str+='<div id="districtWiseEMNRelatedToProblemGraph" class="dynHeight"></div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		if(result.tvNewsDetailsDeptVOList !=null && result.tvNewsDetailsDeptVOList.length>0){
			for(var i in result.tvNewsDetailsDeptVOList){
				var problemRelatedPieChartArray =[];
				problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].organization);
				problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].positivePerc);
				deptWiseEMNRelatedGraph.push(problemRelatedPieChartArray);
			}
		}
		$("#districtWiseEMNRelatedToProblem").html(str);
		if(result.tvNewsDetailsDeptVOList !=null && result.tvNewsDetailsDeptVOList.length>0){
			var getLiLength = result.tvNewsDetailsDeptVOList.length;
			getLiLength = (getLiLength*36)+"px";
		
		$("#districtWiseEMNRelatedToProblemGraph").attr("style","height:"+getLiLength+"")
		$(".emnChannelWiseSlickDist").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false,
			 responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				  }
				},
				{
				  breakpoint: 768,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
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
		if(result.tvNewsDetailsDeptVOList.length > 0)
		{
			$('#districtWiseEMNRelatedToProblemGraph').highcharts({
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
					categories: '',
					labels: {
					enabled: false,
						
					}
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
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					enabled: false,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				
				series: [{
					name: '',
					colorByPoint: true,
					data: deptWiseEMNRelatedGraph,
					dataLabels:{
							enabled: true,
							 distance: -20,
							  formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y,1)+ '%';
									}
								} 
						},
				}]
			});
		}else{
			str+='<h4>NO DATA AVAILABLE</h4>';
		}
	  }
	}
}
function buildProblemsDetailedOverView(result)
{
	var str='';
	if(result !=null){
			str+='<div class="row">';
				str+='<div class="col-md-6 col-xs-12 col-sm-6">';
					str+='<h3>STATE WISE</h3>';
					str+='<table class="table tableEMN m_top20">';
						str+='<tr>';
						if(result.tvNewsDetailsVOList !=null && result.tvNewsDetailsVOList.length>0){
							str+='<td>';
							str+='<h5 class="text-capitalize">Total</h5>';
							str+='<h4>'+result.tvNewsDetailsVOList[0].categoryCount != ""?'<a style="cursor:pointer"><span class="showStateWiseBulletinDetails" attr_catIdStr = "1,2"  attr_newsprogramname="Total">'+result.tvNewsDetailsVOList[0].categoryCount+'</span></a>':"00:00"+'</h4>';
							str+='<h5 class="text-capitalize m_top20">Total Time</h5>';
							str+='<h4>'+result.tvNewsDetailsVOList[0].description != ""?result.tvNewsDetailsVOList[0].description:"00:00"+'</h4>';
						str+='</td>';
							for(var i in result.tvNewsDetailsVOList[0].tvNewsDetailsVOList)
							{
								str+='<td>';
									str+='<h5 class="text-capitalize">'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].organization+'</h5>';
									if(result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].categoryCount == null || result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].categoryCount == 0){
										str+='<h4>'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].categoryCount+'</h4>';
									}else{
										str+='<a style="cursor:pointer"><h4 class="showStateWiseBulletinDetails" attr_catIdStr ="'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].organizationId+'"  attr_newsprogramname="'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].organization+'">'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].categoryCount+'</h4></a>';
									}
									
									//str+='<h5 class="text-capitalize m_top20">'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].title+'</h5>';
									str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
									if(result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].description != ""){
										str+='<h4>'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].description+'</h4>';
									}else{
										str+='<h4>00:00</h4>';
									}
								str+='</td>';
							}
						}
						
						str+='</tr>';
					str+='</table>'; 
				str+='</div>';
			str+='</div>';
	}
	$("#problemsDetailedOvrViewEMN").html(str);
}

function buildDistrictWiseEMNRelatedToProblem(result){
		var str='';
		if(result !=null){
			str+='<div class="row">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="f_16">CHANNELS WISE</h4>';
				str+='</div>';
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<ul class="emnChannelWiseSlick">';
					if(result.tvNewsDetailsChnlVOList !=null && result.tvNewsDetailsChnlVOList.length>0){
						for(var i in result.tvNewsDetailsChnlVOList)
						{
							str+='<li>';
								str+='<img src="newCoreDashBoard/img/'+result.tvNewsDetailsChnlVOList[i].channelName+'.png" class="debatesPartyIcon"/>'+result.tvNewsDetailsChnlVOList[i].channelName+' News';
								str+='<p class="m_top10 text-muted">Total</p>';
								str+='<a style="cursor:pointer"><p class="showChannelWiseProblemsinGovtDetailed" attr_channel_id = "'+result.tvNewsDetailsChnlVOList[i].channelId+'">'+result.tvNewsDetailsChnlVOList[i].count+'</p></a>';
							str+='</li>';
						}
					}
					str+='</ul>';
				str+='</div>';
			str+='</div>';
		
		
			str+='<div class="row">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="f_16">DEPARTMENTS WISE</h4>';
						str+='<div class="col-md-7 col-xs-12 col-sm-6 m_top10">';
						str+='<ul style="list-style:none;padding-left:0px;" id="getLiLength" class="textAlignDepartment">';
						if(result.tvNewsDetailsDeptVOList !=null && result.tvNewsDetailsDeptVOList.length>0){
							for(var i in result.tvNewsDetailsDeptVOList)
							{
								str+='<li>'+result.tvNewsDetailsDeptVOList[i].organization+' <a style="cursor:pointer"><span class="pull-right showDepartmentWiseProblemForState" attr_orgId ="'+result.tvNewsDetailsDeptVOList[i].organizationId+'" attr_channelIds="'+newsChannelsIdsGlbl+'">'+result.tvNewsDetailsDeptVOList[i].count+'</span></a></li>';
							}
						}
						str+='</ul>';
					str+='</div>';
					str+='<div class="col-md-5 col-xs-12 col-sm-6">';
						str+='<div id="comaprisonStateWiseArticle" class="dynHeight"></div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		
			var deptWiseEMNRelated = [];
			if(result.tvNewsDetailsDeptVOList !=null && result.tvNewsDetailsDeptVOList.length>0){
				for(var i in result.tvNewsDetailsDeptVOList){
					var problemRelatedPieChartArray =[];
					problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].organization);
					problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].positivePerc);
					deptWiseEMNRelated.push(problemRelatedPieChartArray);
				}
			}
		$("#stateWiseEMNRelatedToProblem").html(str);
	if(result.tvNewsDetailsDeptVOList !=null && result.tvNewsDetailsDeptVOList.length>0){
		var liLength = result.tvNewsDetailsDeptVOList.length;
		liLength = (liLength*36)+"px";
		$("#comaprisonStateWiseArticle").css("height",liLength);
		$(".emnChannelWiseSlick").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false,
			 responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				  }
				},
				{
				  breakpoint: 768,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		}); 
		$(function () {
			$('#comaprisonStateWiseArticle').highcharts({
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
					categories: '',
					labels: {
					enabled: false,
						
					}
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
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					enabled: false,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				
				series: [{
						name: '',
						colorByPoint: true,
						data: deptWiseEMNRelated,
						dataLabels:{
								enabled: true,
								 distance: -20,
								  formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.y,1)+ '%';
										}
									} 
							},
					}]
			});
		});
	 }
    }
}
	

function getEMMDetailedPartyStateWiseProgramsOverview()
{
	$("#stateWiseProgramsOvrViewEMN").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyStateWiseProgramsOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/party"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyStateWiseProgramsOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/party"
	}).then(function(result){
		buildEMMDetailedPartyStateWiseProgramsOverview(result)
	});
}

function buildEMMDetailedPartyStateWiseProgramsOverview(result)
{
	var str='';
	
	var graphIdMainA = 0;
	for(var i in result)
	{
		graphIdMainA = graphIdMainA+1;
		str+='<div class="row">';
			str+='<div class="col-md-3 col-xs-12 col-sm-3" style="border-right:1px solid #333">';
				var graphIdSub = 0;
				str+='<h4 class="panel-title"><img src="newCoreDashBoard/img/'+result[i].name+'.png"/>'+result[i].name+'</h4>';
				str+='<div class="pad_5 bg_ED">';
					str+='<div id="stateWiseMainGraph'+i+'" style="height:150px"></div>';
				str+='</div>';
				str+='<h4 class="panel-title">Total - <a style="cursor:pointer" class="stateWiseTotalsCls" attr_party_id="'+result[i].id+'" attr_category_id="1,2">'+result[i].categoryCount+'</a></h4>';
				str+='<div class="row">';
					str+='<div class="col-md-6 col-xs-12 col-sm-6">';
						str+='<div id="newsChannels'+i+'" style="height:100px;"></div>';
					str+='</div>';
					str+='<div class="col-md-6 col-xs-12 col-sm-6">';
						str+='<p class="text-muted">Positive Time</p>';
						str+='<p>'+result[i].positiveCoveredTime+' <small class="text-success">'+result[i].positiveTimePerc+'%</small></p>';
						str+='<p class="text-muted">Negative Time</p>';
						str+='<p>'+result[i].negativeCoveredTime+' <small class="text-danger">'+result[i].negativeTimePerc+'%</small></p>';
					str+='</div>';
				str+='</div>';
				var newsChannelCatArr = []
				
				for(var j in result[i].tvNewsDetailsVOList)
				{
					graphIdSub = graphIdSub + 1;
					str+='<h4 class="panel-title">'+result[i].tvNewsDetailsVOList[j].categoryName+' - <a class="stateWiseTotalsCls" style="cursor:pointer" attr_party_id="'+result[i].id+'" attr_category_id="'+result[i].tvNewsDetailsVOList[j].categoryId+'">'+result[i].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
					str+='<div class="row">';
						str+='<div class="col-md-6 col-xs-12 col-sm-6">';
							str+='<div id="newsChannels'+i+j+graphIdSub+'" style="height:100px;"></div>';
						str+='</div>';
						str+='<div class="col-md-6 col-xs-12 col-sm-6">';
							str+='<p class="text-muted">Positive Time</p>';
							str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCoveredTime+' <small class="text-success">'+result[i].tvNewsDetailsVOList[j].positiveTimePerc+'%</small></p>';
							str+='<p class="text-muted">Negative Time</p>';
							str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCoveredTime+' <small class="text-danger">'+result[i].tvNewsDetailsVOList[j].negativeTimePerc+'%</small></p>';
						str+='</div>';
					str+='</div>';
					newsChannelCatArr.push(result[i].tvNewsDetailsVOList[j].categoryName.split(" ")[1]);
					
					//newsChannelCatCountArr.push(result[i].tvNewsDetailsVOList[j].categoryCount);
					
				}
			str+='</div>';
			str+='<div class="col-md-9 col-xs-12 col-sm-9">';
				var graphIdMain = 0;
					for(var k in result[i].tvNewsDetailsVOList1)
					{
						var graphId = 0;
						graphIdMain = graphIdMain + 1;
						
						if(result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList !=null && 
						result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList.length>0){
							
							if(result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName !=null){
								str+='<h4 style="background-color:#ddd;padding:5px;"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName+'.png" class="newsIcon"/>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName+'</h4>';
							}else if(result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[1].channelName !=null){
								str+='<h4 style="background-color:#ddd;padding:5px;"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[1].channelName+'.png" class="newsIcon"/>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[1].channelName+'</h4>';
							}else{
								str+='<h4 style="background-color:#ddd;padding:5px;">No Image</h4>';
							}
						}
						
						
						str+='<div class="row">';
						str+='<div class="col-md-4 col-xs-12 col-sm-4">';
							str+='<h4 class="panel-title">Total - <a style="cursor:pointer" class="stateWiseIndividualCls" attr_channel_id="'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelId+'" attr_party_id="'+result[i].id+'" attr_category_id="1,2">'+result[i].tvNewsDetailsVOList1[k].categoryCount+'<a></h4>';
							str+='<div class="row">';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<div id="newsChannelsProgramsTot'+i+k+graphIdMain+'" style="height:100px;"></div>';
								str+='</div>';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<p class="text-muted">Positive Time</p>';
									str+='<p>'+result[i].tvNewsDetailsVOList1[k].positiveCoveredTime+' <small class="text-success">'+result[i].tvNewsDetailsVOList1[k].positiveTimePerc+'%</small></p>';
									str+='<p class="text-muted">Negative Time</p>';
									str+='<p>'+result[i].tvNewsDetailsVOList1[k].negativeCoveredTime+' <small class="text-danger">'+result[i].tvNewsDetailsVOList1[k].negativeTimePerc+'%</small></p>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						var catSubTotArr = [];
						
						for(var l in result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList)
						{
							graphId = graphId+1;
							str+='<div class="col-md-4 col-xs-12 col-sm-4">';
								str+='<h4 class="panel-title">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryName+' - <a style="cursor:pointer" class="stateWiseIndividualCls" attr_channel_id="'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelId+'" attr_party_id="'+result[i].id+'" attr_category_id="'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryId+'">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryCount+'</a></h4>';
								str+='<div class="row">';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<div id="newsChannelsPrograms'+i+k+graphId+'" style="height:100px;"></div>';
									str+='</div>';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<p class="text-muted">Positive Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveCoveredTime+' <small class="text-success">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveTimePerc+'%</small></p>';
										str+='<p class="text-muted">Negative Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].negativeCoveredTime+' <small class="text-danger">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].negativeTimePerc+'%</small></p>';
										
									str+='</div>';
								str+='</div>';
							str+='</div>';
							catSubTotArr.push(result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryName.split(" ")[1]);
							
						}
						str+='</div>';
					}					
			str+='</div>';
		str+='</div>';
		str+='<hr style="border-color:#555"/>';
	}
	
	$("#stateWiseProgramsOvrViewEMN").html(str);
	var graphIdMainA = 0;
	var graphIdMain = 0;
	
	for(var i in result)
	{
		var newsChannelCatCountArr = [];
		var mainChannelPercArr = [];
		mainChannelPercArr.push({"y":result[i].positivePerc,"color":'#64C664'});
		mainChannelPercArr.push({"y":result[i].negativePerc,"color":'#D33E39'});
		$("#stateWiseMainGraph"+i).highcharts({
			chart: {
				type: 'column',
				backgroundColor:'transparent'
			},

			title: {
				text: null
			},

			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: ['+ve','-ve']
			},

			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
					legend:{
				enabled:false
			},
			tooltip: {
				enabled:false,
				formatter: function () {
					return '<b>' + this.x + '</b>' +
						this.series.name + ': ' + this.y + '<br/>'
				}
			},

			plotOptions: {
				pointPadding: 0.2,
				borderWidth: 2,
				groupPadding: 0.2,
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y)+'%';
							}
						}
					  
					},
				}
			},

			series: [{
				name: ' ',
				data: mainChannelPercArr
			}]
		});
		var graphIdSub = 0;
		graphIdMainA = graphIdMainA + 1;
		
		newsChannelCatCountArr.push({"y":result[i].tvNewsDetailsVOList[0].categoryCount,"color":'#9A00FD'});
		newsChannelCatCountArr.push({"y":result[i].tvNewsDetailsVOList[1].categoryCount,"color":'#340097'});
		$("#newsChannels"+i).highcharts({
			chart: {
				type: 'column',
				backgroundColor:'transparent'
			},

			title: {
				text: null
			},

			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: newsChannelCatArr
			},

			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
					legend:{
				enabled:false
			},
			tooltip: {
				enabled:false,
				formatter: function () {
					return '<b>' + this.x + '</b><br/>' +
						this.series.name + ': ' + this.y + '<br/>' +
						'Total: ' + this.point.stackTotal;
				}
			},

			plotOptions: {
				pointPadding: 0.2,
				borderWidth: 2,
				groupPadding: 0.2,
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y);
							}
						}
					  
					},
				}
			},

			series: [{
				name: ' ',
				data: newsChannelCatCountArr
			}]
		});
		for(var j in result[i].tvNewsDetailsVOList)
		{
			var partyProgramsArr = []	
			graphIdSub = graphIdSub + 1;
			str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCount+'</p>';
			str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCount+'</p>';
			var partyProgramsSubArr = [];
			partyProgramsSubArr.push({"y":result[i].tvNewsDetailsVOList[j].positiveCount,"color":'#64C664'});
			partyProgramsSubArr.push({"y":result[i].tvNewsDetailsVOList[j].negativeCount,"color":'#D33E39'});
		
			$("#newsChannels"+i+j+graphIdSub).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['+ve', '-ve']
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					min: 0,
					title: {
						text: null
					}
				},
						legend:{
					enabled:false
				},
				tooltip: {
					enabled:false,
					formatter: function () {
						return '<b>' + this.x + '</b>' +
							this.series.name + ': ' + this.y + '<br/>'
					}
				},

				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						},
					}
				},

				series: [{
					name: ' ',
					data: partyProgramsSubArr
				}]
			});
		}
		var graphIdMain = 0;
		for(var k in result[i].tvNewsDetailsVOList1)
		{
			var graphId = 0;
			var resultArr = [];
			var resultArrColors = ['#333','#555'];
			
			
			graphIdMain = graphIdMain + 1;
			//resultSubTotArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveCount,"color":'#64C664'});
			var resultSubTotArr = [];
			resultSubTotArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].categoryCount,"color":'#9A01F9'});
			resultSubTotArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[1].categoryCount,"color":'#30009B'});
			$("#newsChannelsProgramsTot"+i+k+graphIdMain).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: catSubTotArr
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					min: 0,
					title: {
						text: null
					}
				},
						legend:{
					enabled:false
				},
				tooltip: {
					formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y + '<br/>' +
							'Total: ' + this.point.stackTotal;
					}
				},

				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						},
					}
				},

				series: [{
					name: ' ',
					data: resultSubTotArr
				}]
			});
			for(var l in result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList)
			{
				var resultSubArr = [];
				graphId = graphId + 1;
				resultSubArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveCount,"color":'#64C664'});
				resultSubArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].negativeCount,"color":'#D33E39'});
				
				$("#newsChannelsPrograms"+i+k+graphId).highcharts({
					chart: {
						type: 'column',
						backgroundColor:'transparent'
					},

					title: {
						text: null
					},

					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: ['+ve', '-ve']
					},

					yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						allowDecimals: false,
						min: 0,
						title: {
							text: null
						}
					},
							legend:{
						enabled:false
					},
					tooltip: {
						enabled:false,
						formatter: function () {
							return '<b>' + this.x + '</b><br/>' +
								this.series.name + ': ' + this.y + '<br/>' +
								'Total: ' + this.point.stackTotal;
						}
					},

					plotOptions: {
						pointPadding: 0.2,
						borderWidth: 2,
						groupPadding: 0.2,
						column: {
							stacking: 'normal',
							dataLabels: {
								enabled: true,
								 formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return (this.y);
									}
								}
							  
							},
						}
					},

					series: [{
						name: ' ',
						data: resultSubArr
					}]
				});
			}
		}
	}
}

function comparisonPartyRankWiseDetailsOfChannel()
{
	$("#partyRankWiseDetailsOfChannel").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getComparisonPartyRankWiseDetailsOfChannel/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/channel"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonPartyRankWiseDetailsOfChannel/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/channel"
	}).then(function(result){
		buildComparisonPartyRankWiseDetailsOfChannel(result)
	});
}
function buildComparisonPartyRankWiseDetailsOfChannel(result)
{
	var str='';
	var rankVar =0;
	str+='<ul class="list-inline NewsSlickPanelSliderEmn">';
	for(var i in result)
	{
		rankVar =rankVar+1;
		str+='<li class="NewsSlickPanelSliderLiEmnCls" attr_newsChannel='+result[i].tvNewsDetailsVOList[0].organizationId+'  attr_channelName='+result[i].tvNewsDetailsVOList[0].organization+'  style="cursor:pointer;">';
			str+='<div class="panel panel-default panelSlick">';
				str+='<div class="panel-heading" style="padding:8px;background-color: #ededed !important;">';
					str+='<h4 class="panel-title"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList[0].organization+'.png" class="debatesPartyIcon"/>'+result[i].tvNewsDetailsVOList[0].organization+'';
					str+='<span class="count">'+rankVar+'</span></h4>';
				str+='</div>';
				str+='<div class="panel-body" style="background-color:#fff;">';
					str+='<div class="row">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12">';
							str+='<div class="bg_ED">';
								str+='<table class="table">';
									str+='<tr>';
										str+='<td><p class="text-muted">Total</p>';
											str+='<a style="cursor:pointer;" class="compPartyFstBlkCls" attr_channel_id="'+result[i].tvNewsDetailsVOList[0].organizationId+'" attr_category_id="1,2">'+result[i].categoryCount+'</a>';
										str+='</td>';
										str+='<td><p class="text-muted">Total Time</p>';
											str+=''+result[i].totalCoveredTime+'';
										str+='</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
						for(var j in result[i].tvNewsDetailsVOList)
						{
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								str+='<h4 class="panel-title">'+result[i].tvNewsDetailsVOList[j].categoryName+' - <a style="cursor:pointer;" class="compPartyFstBlkCls" attr_channel_id="'+result[i].tvNewsDetailsVOList[j].organizationId+'" attr_category_id="'+result[i].tvNewsDetailsVOList[j].categoryId+'">'+result[i].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
							str+='</div>';
							
							str+='<div class="col-md-8 col-xs-12 col-sm-8">';
								str+='<div id="rankWiseDetailsOfChannel'+i+''+j+'" style="height:100px;"></div>';
							str+='</div>';
							str+='<div class="col-md-4 col-xs-12 col-sm-4">';
								str+='<p class="text-muted">Positive Time</p>';
								str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCoveredTime+'<small class="text-success">&nbsp;&nbsp;&nbsp;'+result[i].tvNewsDetailsVOList[j].positiveTimePerc+'%</small></p>';
								str+='<p class="text-muted">Negative Time</p>';
								str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCoveredTime+'<small class="text-danger">&nbsp;&nbsp;&nbsp;'+result[i].tvNewsDetailsVOList[j].negativeTimePerc+'%</small></p>';
							str+='</div>';
						}
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</li>';
	}
	str+='</ul>';
	$("#partyRankWiseDetailsOfChannel").html(str);
	$("#partyRankWiseDetailsOfChannel li:first-child").trigger("click");
	$(".NewsSlickPanelSliderEmn").slick({
	 slide: 'li',
	 slidesToShow: 2,
	 slidesToScroll: 2,
	 infinite: false,
	 responsive: [
		{
		  breakpoint: 1024,
		  settings: {
			slidesToShow: 3,
			slidesToScroll: 3
		  }
		},
		{
		  breakpoint: 768,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		},
		{
		  breakpoint: 600,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		},
		{
		  breakpoint: 480,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		}
		// You can unslick at a given breakpoint now by adding:
		// settings: "unslick"
		// instead of a settings object
	  ]
	});
	for(var i in result)
	{
		for(var j in result[i].tvNewsDetailsVOList)
		{
			var rankWiseDetailsOfChannelArr = [];
			rankWiseDetailsOfChannelArr.push({"y":result[i].tvNewsDetailsVOList[j].positiveCount,"color":'#64C664'});
			rankWiseDetailsOfChannelArr.push({"y":result[i].tvNewsDetailsVOList[j].negativeCount,"color":'#D33E39'});
			$("#rankWiseDetailsOfChannel"+i+j).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['+ve', '-ve']
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					min: 0,
					title: {
						text: null
					}
				},
						legend:{
					enabled:false
				},
				tooltip: {
					enabled:false,
					formatter: function () {
						return '<b>' + this.x + '</b>' +
							this.series.name + ': ' + this.y + '<br/>'
					}
				},

				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						},
					}
				},

				series: [{
					name: ' ',
					data: rankWiseDetailsOfChannelArr
				}]
			});
		}
	}
	
}
$(document).on("click",".NewsSlickPanelSliderLiEmnCls",function(){
	$(".NewsSlickPanelSliderLiEmnCls").find("li").find(".panelSlick").removeClass("panelActiveSlick");
	$(this).find(".panelSlick").addClass("panelActiveSlick");
	var channelIdArr = [];
	var channelId = $(this).attr("attr_newsChannel");
	var channelName = $(this).attr("attr_channelName");
	channelIdArr.push(channelId);
	comparisonPartyRankWiseDetailsOfChannelSub(channelIdArr,channelName);
	getEMMDetailedPartyDistrictWiseProgramsOverview(channelIdArr,"comparison")
});
function comparisonPartyRankWiseDetailsOfChannelSub(channelIdArr,channelName)
{
	var channelId = channelIdArr[0];
	$("#partyRankWiseDetailsOfChannelSub").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getComparisonPartyRankWiseDetailsOfChannel/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+channelIdArr+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/party"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonPartyRankWiseDetailsOfChannel/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+channelIdArr+"/"+impactScopeIds+"/"+partyIdsGlob+"/N/party"
	}).then(function(result){
		buildComparisonPartyRankWiseDetailsOfChannelSub(result,channelName,channelId)
	});
}
function buildComparisonPartyRankWiseDetailsOfChannelSub(result,channelName,channelId)
{
	var str='';
	str+='<div class="pad_15 bg_ED">';
		str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12"><h4><img src="newCoreDashBoard/img/'+channelName+'.png"/>'+channelName+'</h4></div>';
			var graphId = 0;
			var totGraphId = 0;
			var categoriesCompParty = [];
			for(var i in result)
			{
				totGraphId = totGraphId+1;
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="pad_5 bg_CC"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList[0].organization+'.png"/>'+result[i].tvNewsDetailsVOList[0].organization+'</h4>';
					str+='<div class="row">';
						str+='<div class="col-md-4 col-xs-12 col-sm-4">';
							str+='<div class="row">';
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<h4 class="panel-title text-capital">Total - <a style="cursor:pointer;" class="stateWiseIndividualCls" attr_category_id="1,2" attr_channel_id="'+channelId+'" attr_party_id="'+result[i].tvNewsDetailsVOList[0].organizationId+'">'+result[i].categoryCount+'</a></h4>';
								str+='</div>';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<div id="RankWiseDetailsOfChannelSub'+i+'A'+totGraphId+'" style="height:100px"></div>';
								str+='</div>';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<p class="text-muted">Positive Time</p>';
									str+='<p>'+result[i].positiveCoveredTime+' <small class="text-success">'+result[i].positiveTimePerc+'%</small></p>';
									str+='<p class="text-muted m_top10">Negative Time</p>';
									str+='<p>'+result[i].negativeCoveredTime+' <small class="text-danger">'+result[i].negativeTimePerc+'%</small></p>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						for(var j in result[i].tvNewsDetailsVOList)
						{
							graphId = graphId+1;
							str+='<div class="col-md-4 col-xs-12 col-sm-4">';
								str+='<div class="row">';
									str+='<div class="col-md-12 col-xs-12 col-sm-12">';
										str+='<h4 class="panel-title text-capital"> '+result[i].tvNewsDetailsVOList[j].categoryName+'- <a style="cursor:pointer;" class="stateWiseIndividualCls" attr_category_id="'+result[i].tvNewsDetailsVOList[j].categoryId+'" attr_channel_id="'+channelId+'" attr_party_id="'+result[i].tvNewsDetailsVOList[j].organizationId+'">'+result[i].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
									str+='</div>';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<div id="RankWiseDetailsOfChannelSub'+i+''+graphId+'" style="height:100px"></div>';
									str+='</div>';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<p class="text-muted">Positive Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCoveredTime+' <small class="text-success">'+result[i].tvNewsDetailsVOList[j].positiveTimePerc+'%</small></p>';
										str+='<p class="text-muted m_top10">Negative Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCoveredTime+' <small class="text-danger">'+result[i].tvNewsDetailsVOList[j].negativeTimePerc+'%</small></p>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							categoriesCompParty.push(result[i].tvNewsDetailsVOList[j].categoryName.split(" ")[1])
						}
					str+='</div>';
				str+='</div>';
				
			}
		str+='</div>';
	str+='</div>';
	$("#partyRankWiseDetailsOfChannelSub").html(str);
	var graphId = 0;
	var totGraphId = 0;
	for(var i in result)
	{
		totGraphId = totGraphId+1;
		
		var RankWiseDetailsOfChannelSubArrMain = [];
		
		//categoriesCompParty.push()
		
		RankWiseDetailsOfChannelSubArrMain.push({"y":result[i].tvNewsDetailsVOList[0].categoryCount,"color":'#9A01F9'});
		RankWiseDetailsOfChannelSubArrMain.push({"y":result[i].tvNewsDetailsVOList[1].categoryCount,"color":'#30009B'});
		$("#RankWiseDetailsOfChannelSub"+i+'A'+totGraphId).highcharts({
			chart: {
				type: 'column',
				backgroundColor:'transparent'
			},

			title: {
				text: null
			},

			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: categoriesCompParty,
			},

			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
			legend:{
				enabled:false
			},
			tooltip: {
				enabled:false,
				formatter: function () {
					return '<b>' + this.x + '</b>' +
						this.series.name + ': ' + this.y + '<br/>'
				}
			},

			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y);
							}
						}
					  
					},
				}
			},

			series: [{
				name: ' ',
				data: RankWiseDetailsOfChannelSubArrMain
			}]
		});
		for(var j in result[i].tvNewsDetailsVOList)
		{
			graphId = graphId+1;
			var locationNameArr = [];
			var PositiveCntArr = [];
			var NegativeCntArr = [];
			
			var RankWiseDetailsOfChannelSubArr = [];
			RankWiseDetailsOfChannelSubArr.push({"y":result[i].tvNewsDetailsVOList[j].positiveCount,"color":'#64C664'});
			RankWiseDetailsOfChannelSubArr.push({"y":result[i].tvNewsDetailsVOList[j].negativeCount,"color":'#D33E39'});
			$("#RankWiseDetailsOfChannelSub"+i+graphId).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['+ve', '-ve']
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					min: 0,
					title: {
						text: null
					}
				},
				legend:{
					enabled:false
				},
				tooltip: {
					enabled:false,
					formatter: function () {
						return '<b>' + this.x + '</b>' +
							this.series.name + ': ' + this.y + '<br/>'
					}
				},

				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						},
					}
				},

				series: [{
					name: ' ',
					data: RankWiseDetailsOfChannelSubArr
				}]
			});
		}
		
	}
}
function buildEMMComparisonPartyDistrictWiseProgramsOverview(result)
{
	var str='';
	if(result !=null && result.length>0){
		str+='<div class="pad_15 bg_ED">';
			str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h4 class="text-capital"><span class="pad_5 bg_CC">district wise - program overview</span></h4>'
			str+='</div>'
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<h4 class="text-capital"><img src="newCoreDashBoard/img/'+result[i].organization+'.png" style="width:25px;" alt="'+result[i].organization+' icon" class="debatesPartyIcon">'+result[i].organization+'</h4>';
				str+='<div id="partyRankWiseCompDistWise'+i+'" style="height:300px"></div>';
				str+='</div>'
			}
			str+='</div>'
		str+='</div>'
	$("#partyRankWiseCompDistWiseOvervw").html(str);
	for(var i in result){
		var locationNameArr = [];
		var PositiveCntArr = [];
		var NegativeCntArr = [];
		
		for(var j in result[i].tvNewsDetailsVOList1){
			locationNameArr.push(result[i].tvNewsDetailsVOList1[j].organization)
			PositiveCntArr.push(result[i].tvNewsDetailsVOList1[j].positiveTime)
			NegativeCntArr.push(result[i].tvNewsDetailsVOList1[j].negativeTime)
		}
		
		$("#partyRankWiseCompDistWise"+i).highcharts({
			colors: ['#64C664','#D33E39'],
			chart: {
				type: 'column',
				backgroundColor:'transparent'
			},
			title: {
				text: null
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: locationNameArr,
				labels: {
						rotation: -45,
						style: {
							fontSize: '13px',
							fontFamily: 'Verdana, sans-serif'
						}
					}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			legend: {
				enabled: true,
				backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
				borderColor: '#CCC',
				borderWidth: 1,
				shadow: false
			},
			tooltip: {
				enabled:false,
				formatter: function () {
					var s = '<b>' + this.x + '</b>';

					$.each(this.points, function () {
						s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
							Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
							(this.y);
					});

					return s;
				},
				shared: true
			},
			
			plotOptions: {
				column: {
					stacking: 'percent',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y);
							}
						}
					  
					},
				}
			},
			series: [{
				name: 'Positive',
				data: PositiveCntArr,
				stack: 'programs'
			}, {
				name: 'Negative',
				data: NegativeCntArr,
				stack: 'programs'
			}]
		});

		}
	}
}

function comparisonGovtRankWiseDetailsOfChannel()
{
	$("#govtRankWiseDetailsOfChannel").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getComparisonPartyRankWiseDetailsOfChannel/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/channel"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonPartyRankWiseDetailsOfChannel/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/channel"
	}).then(function(result){
		buildComparisonGovtRankWiseDetailsOfChannel(result)
	});
}
function buildComparisonGovtRankWiseDetailsOfChannel(result)
{
	var str='';
	var rankVar =0;
	str+='<ul class="list-inline NewsSlickPanelSliderGovtEmn">';
	for(var i in result)
	{
		rankVar =rankVar+1;
		str+='<li class="NewsSlickPanelSliderLiEmnClsGovt" attr_newsChannel="'+result[i].tvNewsDetailsVOList[0].organizationId+'" attr_channelName="'+result[i].tvNewsDetailsVOList[0].organization+'" style="cursor:pointer;">';
			str+='<div class="panel panel-default panelSlick">';
				str+='<div class="panel-heading" style="padding:8px;background-color: #ededed !important;">';
					str+='<h4 class="panel-title"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList[0].organization+'.png" class="debatesPartyIcon"/>'+result[i].tvNewsDetailsVOList[0].organization+'';
					str+='<span class="count">'+rankVar+'</span></h4>';
				str+='</div>';
				str+='<div class="panel-body" style="background-color:#fff;">';
					str+='<div class="row">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12">';
							str+='<div class="bg_ED">';
								str+='<table class="table">';
									str+='<tr>';
										str+='<td><p class="text-muted">Total</p>';
											str+='<a style="cursor:pointer;" class="compGovtFstBlkCls" attr_category_id="1,2" attr_channel_id="'+result[i].tvNewsDetailsVOList[0].organizationId+'">'+result[i].categoryCount+'</a>';
										str+='</td>';
										str+='<td><p class="text-muted">Total Time</p>';
											str+=''+result[i].totalCoveredTime+'';
										str+='</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
						for(var j in result[i].tvNewsDetailsVOList)
						{
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								str+='<h4 class="panel-title">'+result[i].tvNewsDetailsVOList[j].categoryName+' - <a style="cursor:pointer;" class="compGovtFstBlkCls" attr_category_id="'+result[i].tvNewsDetailsVOList[j].categoryId+'" attr_channel_id="'+result[i].tvNewsDetailsVOList[j].organizationId+'">'+result[i].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
							str+='</div>';
							
							str+='<div class="col-md-8 col-xs-12 col-sm-8">';
								str+='<div id="rankWiseDetailsOfChannelGovt'+i+''+j+'" style="height:100px;"></div>';
							str+='</div>';
							str+='<div class="col-md-4 col-xs-12 col-sm-4">';
								str+='<p class="text-muted">Positive Time</p>';
								str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCoveredTime+'<small class="text-success">&nbsp;&nbsp;&nbsp;'+result[i].tvNewsDetailsVOList[j].positiveTimePerc+'%</small></p>';
								str+='<p class="text-muted">Negative Time</p>';
								str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCoveredTime+'<small class="text-danger">&nbsp;&nbsp;&nbsp;'+result[i].tvNewsDetailsVOList[j].negativeTimePerc+'%</small></p>';
							str+='</div>';
						}
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</li>';
	}
	str+='</ul>';
	$("#govtRankWiseDetailsOfChannel").html(str);
	$("#govtRankWiseDetailsOfChannel li:first-child").trigger("click")
	$(".NewsSlickPanelSliderGovtEmn").slick({
	 slide: 'li',
	 slidesToShow: 2,
	 slidesToScroll: 2,
	 infinite: false,
	 responsive: [
		{
		  breakpoint: 1024,
		  settings: {
			slidesToShow: 3,
			slidesToScroll: 3
		  }
		},
		{
		  breakpoint: 768,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		},
		{
		  breakpoint: 600,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		},
		{
		  breakpoint: 480,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		}
		// You can unslick at a given breakpoint now by adding:
		// settings: "unslick"
		// instead of a settings object
	  ]
	});
	for(var i in result)
	{
		for(var j in result[i].tvNewsDetailsVOList)
		{
			var rankWiseDetailsOfChannelGovtArr = [];
			rankWiseDetailsOfChannelGovtArr.push({"y":result[i].tvNewsDetailsVOList[j].positiveCount,"color":'#64C664'});
			rankWiseDetailsOfChannelGovtArr.push({"y":result[i].tvNewsDetailsVOList[j].negativeCount,"color":'#D33E39'});
			$("#rankWiseDetailsOfChannelGovt"+i+j).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['+ve', '-ve']
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					min: 0,
					title: {
						text: null
					}
				},
						legend:{
					enabled:false
				},
				tooltip: {
					formatter: function () {
						return '<b>' + this.x + '</b>' +
							this.series.name + ': ' + this.y + '<br/>'
					}
				},

				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						},
					}
				},

				series: [{
					name: ' ',
					data: rankWiseDetailsOfChannelGovtArr
				}]
			});
		}
	}
	
}
$(document).on("click",".NewsSlickPanelSliderLiEmnClsGovt",function(){
	$(".NewsSlickPanelSliderLiEmnClsGovt").find("li").find(".panelSlick").removeClass("panelActiveSlick");
	$(this).find(".panelSlick").addClass("panelActiveSlick");
	var channelIdArr = [];
	var channelId = $(this).attr("attr_newsChannel");
	var channelName = $(this).attr("attr_channelName");
	channelIdArr.push(channelId);
	comparisonGovtRankWiseDetailsOfChannelSub(channelIdArr,channelName);
	getEMMCompGovtProblemsDetailedOverview(channelIdArr);
});

function comparisonGovtRankWiseDetailsOfChannelSub(channelIdArr,channelName)
{
	$("#govtRankWiseDetailsOfChannelSub").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getComparisonPartyRankWiseDetailsOfChannel/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+channelIdArr+"/"+impactScopeIds+"/ /Y/party"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonPartyRankWiseDetailsOfChannel/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+channelIdArr+"/"+impactScopeIds+"/ /Y/party"
	}).then(function(result){
		buildComparisonGovtRankWiseDetailsOfChannelSub(result,channelName,channelIdArr[0])
	});
}
function buildComparisonGovtRankWiseDetailsOfChannelSub(result,channelName,channelId)
{
	var str='';
	str+='<div class="pad_15 bg_ED">';
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12"><h4><img src="newCoreDashBoard/img/'+channelName+'.png" style="width: 30px; height: 30px;" />'+channelName+'</h4></div>';
			var graphId = 0;
			var totGraphId = 0;
			var categoriesCompParty = [];
			for(var i in result)
			{
				totGraphId = totGraphId+1;
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="pad_5 bg_CC"><span class="govtCount">'+totGraphId+'</span>'+result[i].tvNewsDetailsVOList[0].organization+'</h4>';
					str+='<div class="row">';
						str+='<div class="col-md-4 col-xs-12 col-sm-4">';
							str+='<div class="row">';
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<h4 class="panel-title text-capital">Total - <a style="cursor:pointer;" class="stateWiseGovtIndividualCls" attr_category_id="1,2" attr_channel_id="'+channelId+'" attr_party_id="'+result[i].tvNewsDetailsVOList[0].organizationId+'">'+result[i].categoryCount+'</a></h4>';
								str+='</div>';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<div id="RankWiseDetailsOfChannelSubGovt'+i+'A'+totGraphId+'" style="height:100px"></div>';
								str+='</div>';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<p class="text-muted">Positive Time</p>';
									str+='<p>'+result[i].positiveCoveredTime+'<small class="text-success">'+result[i].positiveTimePerc+'%</small></p>';
									str+='<p class="text-muted m_top10">Negative Time</p>';
									str+='<p>'+result[i].negativeCoveredTime+'<small class="text-danger">'+result[i].negativeTimePerc+'%</small></p>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						for(var j in result[i].tvNewsDetailsVOList)
						{
							graphId = graphId+1;
							str+='<div class="col-md-4 col-xs-12 col-sm-4">';
								str+='<div class="row">';
									str+='<div class="col-md-12 col-xs-12 col-sm-12">';
										str+='<h4 class="panel-title text-capital"> '+result[i].tvNewsDetailsVOList[j].categoryName+'- <a style="cursor:pointer;" class="stateWiseGovtIndividualCls" attr_category_id="'+result[i].tvNewsDetailsVOList[j].categoryId+'" attr_channel_id="'+channelId+'" attr_party_id="'+result[i].tvNewsDetailsVOList[j].organizationId+'">'+result[i].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
									str+='</div>';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<div id="RankWiseDetailsOfChannelSubGovt'+i+''+graphId+'" style="height:100px"></div>';
									str+='</div>';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<p class="text-muted">Positive Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCoveredTime+'<small class="text-success">'+result[i].tvNewsDetailsVOList[j].positiveTimePerc+'%</small></p>';
										str+='<p class="text-muted m_top10">Negative Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCoveredTime+'<small class="text-danger">'+result[i].tvNewsDetailsVOList[j].negativeTimePerc+'%</small></p>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							categoriesCompParty.push(result[i].tvNewsDetailsVOList[j].categoryName)
						}
					str+='</div>';
				str+='</div>';
				
			}
		str+='</div>';
	str+='</div>';
	$("#govtRankWiseDetailsOfChannelSub").html(str);
	var graphId = 0;
	var totGraphId = 0;
	for(var i in result)
	{
		totGraphId = totGraphId+1;
		
		var govtRankWiseDetailsOfChannelSubMainArr = [];
		
		//categoriesCompParty.push()
		
		govtRankWiseDetailsOfChannelSubMainArr.push({"y":result[i].tvNewsDetailsVOList[0].categoryCount,"color":'#9A01F9'});
		govtRankWiseDetailsOfChannelSubMainArr.push({"y":result[i].tvNewsDetailsVOList[1].categoryCount,"color":'#30009B'});
		$("#RankWiseDetailsOfChannelSubGovt"+i+'A'+totGraphId).highcharts({
			chart: {
				type: 'column',
				backgroundColor:'transparent'
			},

			title: {
				text: null
			},

			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: categoriesCompParty,
			},

			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
			legend:{
				enabled:false
			},
			tooltip: {
				formatter: function () {
					return '<b>' + this.x + '</b>' +
						this.series.name + ': ' + this.y + '<br/>'
				}
			},

			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y);
							}
						}
					  
					},
				}
			},

			series: [{
				name: ' ',
				data: govtRankWiseDetailsOfChannelSubMainArr
			}]
		});
		for(var j in result[i].tvNewsDetailsVOList)
		{
			graphId = graphId+1;
			var locationNameArr = [];
			var PositiveCntArr = [];
			var NegativeCntArr = [];
			
			var govtRankWiseDetailsOfChannelSubArr = [];
			govtRankWiseDetailsOfChannelSubArr.push({"y":result[i].tvNewsDetailsVOList[j].positiveCount,"color":'#64C664'});
			govtRankWiseDetailsOfChannelSubArr.push({"y":result[i].tvNewsDetailsVOList[j].negativeCount,"color":'#D33E39'});
			$("#RankWiseDetailsOfChannelSubGovt"+i+graphId).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['+ve', '-ve']
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					min: 0,
					title: {
						text: null
					}
				},
				legend:{
					enabled:false
				},
				tooltip: {
					formatter: function () {
						return '<b>' + this.x + '</b>' +
							this.series.name + ': ' + this.y + '<br/>'
					}
				},

				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						},
					}
				},

				series: [{
					name: ' ',
					data: govtRankWiseDetailsOfChannelSubArr
				}]
			});
		}
		
	}
}
function getEMMCompGovtProblemsDetailedOverview(channelIdArr)
{
	$("#districtWiseEMNRelatedToProblemGovt").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#emmCompGovtDistDetailedOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+channelIdArr+"/"+impactScopeIds+"/ /Y/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+channelIdArr+"/"+impactScopeIds+"/ /Y/"
	}).then(function(result){
		buildEMMCompGovtDistDetailedOverview(result,channelIdArr);
		buildEMMCompGovtProblemsDetailedOverview(result,channelIdArr);
	});
}
function buildEMMCompGovtProblemsDetailedOverview(result,channelIdArr)
{
	var str='';
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h3 class="m_top20">DISTRICT WISE</h3>';
				str+='<div id="comaprisonDistrictWiseArticleEmnGovt" class="m_top20" style="height:150px;"></div>';
			str+='</div>';
		str+='</div>';
		
		var distWiseArticlesRelated = [];
		for(var i in result.tvNewsDetailsVOList1){
			if(locationLevelIdGlb == 2){
				var obj1 = {
					name: result.tvNewsDetailsVOList1[i].organization,
					y: result.tvNewsDetailsVOList1[i].positivePerc,
					extra:result.tvNewsDetailsVOList1[i].count,
					extra1:"3-"+result.tvNewsDetailsVOList1[i].organizationId+"-"+channelIdArr
				};
			}else{
				var obj1 = {
					name: result.tvNewsDetailsVOList1[i].organization,
					y: result.tvNewsDetailsVOList1[i].positivePerc,
					extra:result.tvNewsDetailsVOList1[i].count,
					extra1:locationLevelIdGlb+"-"+locationValueArrGlb+"-"+channelIdArr
				};
				
			}
			distWiseArticlesRelated.push(obj1);
		}
		$("#emmCompGovtDistDetailedOverview").html(str)
		$(function () {
			 $("#comaprisonDistrictWiseArticleEmnGovt").highcharts({
				colors: ['#AA3732'],
				chart: {
					type: 'column'
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
					
					type: 'category',
					labels: {
								formatter: function() {
									return this.value.toString().substring(0, 10)+'...';
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
					labels: {
						enabled:false
					}
				},
				legend: {
					enabled: false
				},
				
						
				plotOptions: {
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,1) + '%';
								}
							}
						  
						}
					},
					series: {
					cursor: 'pointer',
						point: {
							events: {
								click: function () {
									getEMMCompGovtBulletinProblemsDetailedOverview(this.extra1);
								}
							}
						}
					}
				},

				tooltip: {
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}% - {point.extra}</b>'
				},

				series: [{
					name: 'Completed',
					data: distWiseArticlesRelated
				}],
			 
			});
		});
		
}
function getEMMCompGovtBulletinProblemsDetailedOverview(val){
	 var t = val.split("-");
	 
	 window.open('showElectronicBulletinsAction.action?levelId='+t[0]+'&temp='+t[1]+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&npsStr='+t[2]+'&scops='+impactScopeIds+'&orgIdStr= &orgType=Y&stIdx=0&edIdx=6&callFrom=dgpdiswd');
}
function buildEMMCompGovtDistDetailedOverview(result,channelIdArr)
{
	var str='';
		var deptWiseEMNRelatedGraph = [];
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				str+='<h4 class="f_16">DEPARTMENTS WISE</h4>';
					str+='<div class="col-md-7 col-xs-12 col-sm-6 m_top10">';
					str+='<ul style="list-style:none;padding-left:0px;" id="getLiLengthGovt" class="textAlignDepartment">';
						for(var i in result.tvNewsDetailsDeptVOList)
						{
							str+='<li>'+result.tvNewsDetailsDeptVOList[i].organization+' <a style="cursor:pointer"><span class="pull-right showDepartmentWiseProblemForState" attr_orgId ="'+result.tvNewsDetailsDeptVOList[i].organizationId+'" attr_channelIds="'+channelIdArr+'">'+result.tvNewsDetailsDeptVOList[i].count+'</span></a></li>';
							
						}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-md-5 col-xs-12 col-sm-6">';
					str+='<div id="districtWiseEMNRelatedToProblemGraphGovt" class="dynHeight"></div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		var problemRelatedPieChartArray =[];
			for(var i in result.tvNewsDetailsDeptVOList){
				problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].organization);
				problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].positivePerc);
				deptWiseEMNRelatedGraph.push(problemRelatedPieChartArray);
			}
		
		$("#districtWiseEMNRelatedToProblemGovt").html(str);
		var getLiLengthGovt = result.tvNewsDetailsDeptVOList.length;
		getLiLengthGovt = (getLiLengthGovt*36)+"px";
		$("#districtWiseEMNRelatedToProblemGraphGovt").css("height",getLiLengthGovt)
		$(".emnChannelWiseSlickDistGovt").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false,
			 responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				  }
				},
				{
				  breakpoint: 768,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
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
		if(result.tvNewsDetailsDeptVOList.length > 0)
		{
			$('#districtWiseEMNRelatedToProblemGraphGovt').highcharts({
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
					categories: '',
					labels: {
					enabled: false,
						
					}
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
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					enabled: false,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				
				series: [{
					name: '',
					colorByPoint: true,
					data: deptWiseEMNRelatedGraph,
					dataLabels:{
							enabled: true,
							 distance: -20,
							  formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y,2)+ '%';
									}
								} 
						},
				}]
			});
		}else{
			str+='<h4>NO DATA AVAILABLE</h4>';
		}
}


function getEMMDetailedGovtStateWiseProgramsOverview()
{
	$("#stateWiseProgramsOvrViewGovtEMN").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyStateWiseProgramsOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/party"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyStateWiseProgramsOverview/"+locationLevelIdGlb+"/"+locationValueArrGlb+"/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/party"
	}).then(function(result){
		buildEMMDetailedGovtStateWiseProgramsOverview(result)
	});
}

function buildEMMDetailedGovtStateWiseProgramsOverview(result)
{
	var str='';
	
	var graphIdMainA = 0;
	for(var i in result)
	{
		graphIdMainA = graphIdMainA+1;
		str+='<div class="row">';
			str+='<div class="col-md-3 col-xs-12 col-sm-3" style="border-right:1px solid #333">';
				var graphIdSub = 0;
				str+='<h4 class="panel-title">'+result[i].name+'</h4>';
				str+='<div class="pad_5 bg_ED">';
					str+='<div id="stateWiseMainGraphGovt'+i+'" style="height:150px"></div>';
				str+='</div>';
				str+='<h4 class="panel-title">Total - <a class="stateWiseGovtTotalsCls" style="cursor:pointer;" attr_party_id="'+result[i].id+'" attr_category_id="1,2">'+result[i].categoryCount+'</a></h4>';
				str+='<div class="row">';
					str+='<div class="col-md-6 col-xs-12 col-sm-6">';
						str+='<div id="newsChannelsGovt'+i+'" style="height:100px;"></div>';
					str+='</div>';
					str+='<div class="col-md-6 col-xs-12 col-sm-6">';
						str+='<p class="text-muted">Positive Time</p>';
						str+='<p>'+result[i].positiveCoveredTime+' <small class="text-success">'+result[i].positiveTimePerc+'%</small></p>';
						str+='<p class="text-muted">Negative Time</p>';
						str+='<p>'+result[i].negativeCoveredTime+' <small class="text-danger">'+result[i].negativeTimePerc+'%</small></p>';
					str+='</div>';
				str+='</div>';
				var newsChannelCatArr = []
				
				for(var j in result[i].tvNewsDetailsVOList)
				{
					graphIdSub = graphIdSub + 1;
					str+='<h4 class="panel-title">'+result[i].tvNewsDetailsVOList[j].categoryName+' - <a class="stateWiseGovtTotalsCls" style="cursor:pointer;" attr_party_id="'+result[i].id+'" attr_category_id="'+result[i].tvNewsDetailsVOList[j].categoryId+'">'+result[i].tvNewsDetailsVOList[j].categoryCount+'</a></h4>';
					str+='<div class="row">';
						str+='<div class="col-md-6 col-xs-12 col-sm-6">';
							str+='<div id="newsChannelsGovt'+i+j+graphIdSub+'" style="height:100px;"></div>';
						str+='</div>';
						str+='<div class="col-md-6 col-xs-12 col-sm-6">';
							str+='<p class="text-muted">Positive Time</p>';
							str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCoveredTime+' <small class="text-success">'+result[i].tvNewsDetailsVOList[j].positiveTimePerc+'%</small></p>';
							str+='<p class="text-muted">Negative Time</p>';
							str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCoveredTime+' <small class="text-danger">'+result[i].tvNewsDetailsVOList[j].negativeTimePerc+'%</small></p>';
						str+='</div>';
					str+='</div>';
					newsChannelCatArr.push(result[i].tvNewsDetailsVOList[j].categoryName.split(" ")[1]);
					//newsChannelCatCountArr.push(result[i].tvNewsDetailsVOList[j].categoryCount);
					
				}
			str+='</div>';
			str+='<div class="col-md-9 col-xs-12 col-sm-9">';
				var graphIdMain = 0;
					for(var k in result[i].tvNewsDetailsVOList1)
					{
						var graphId = 0;
						graphIdMain = graphIdMain + 1;
						
						 if(result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName !=null){
							str+='<h4 style="background-color:#ddd;padding:5px;"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName+'.png" class="newsIcon"/>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName+'</h4>';
						}else if(result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[1].channelName !=null){
							str+='<h4 style="background-color:#ddd;padding:5px;"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[1].channelName+'.png" class="newsIcon"/>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[1].channelName+'</h4>';
						}else{
							str+='<h4 style="background-color:#ddd;padding:5px;">No Image</h4>';
						}
						
						/* str+='<h4 style="background-color:#ddd;padding:5px;"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName+'.png" class="newsIcon"/>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName+'</h4>'; */
						
						str+='<div class="row">';
						str+='<div class="col-md-4 col-xs-12 col-sm-4">';
							str+='<h4 class="panel-title">Total - <a style="cursor:pointer" class="stateWiseGovtIndividualCls" attr_category_id="1,2" attr_channel_id="'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelId+'" attr_party_id="'+result[i].id+'">'+result[i].tvNewsDetailsVOList1[k].categoryCount+'</a></h4>';
							str+='<div class="row">';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<div id="newsChannelsProgramsTotGovt'+i+k+graphIdMain+'" style="height:100px;"></div>';
								str+='</div>';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<p class="text-muted">Positive Time</p>';
									str+='<p>'+result[i].tvNewsDetailsVOList1[k].positiveCoveredTime+'<small class="text-success"> '+result[i].tvNewsDetailsVOList1[k].positiveTimePerc+'%</small></p>';
									str+='<p class="text-muted">Negative Time</p>';
									str+='<p>'+result[i].tvNewsDetailsVOList1[k].negativeCoveredTime+'<small class="text-danger"> '+result[i].tvNewsDetailsVOList1[k].negativeTimePerc+'%</small></p>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						var catSubTotArr = [];
						
						for(var l in result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList)
						{
							graphId = graphId+1;
							str+='<div class="col-md-4 col-xs-12 col-sm-4">';
								str+='<h4 class="panel-title">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryName+' - <a class="stateWiseGovtIndividualCls" style="cursor:pointer;" attr_category_id="'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryId+'" attr_channel_id="'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].channelId+'" attr_party_id="'+result[i].id+'">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryCount+'</a></h4>';
								str+='<div class="row">';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<div id="newsChannelsProgramsGovt'+i+k+graphId+'" style="height:100px;"></div>';
									str+='</div>';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<p class="text-muted">Positive Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveCoveredTime+' <small class="text-success">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveTimePerc+'%</small></p>';
										str+='<p class="text-muted">Negative Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].negativeCoveredTime+' <small class="text-danger">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].negativeTimePerc+'%</small></p>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							catSubTotArr.push(result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryName.split(" ")[1]);
							
						}
						str+='</div>';
					}					
			str+='</div>';
		str+='</div>';
		str+='<hr style="border-color:#555"/>';
	}
	
	$("#stateWiseProgramsOvrViewGovtEMN").html(str);
	var graphIdMainA = 0;
	var graphIdMain = 0;
	
	for(var i in result)
	{
		var newsChannelCatCountArr = [];
		var mainChannelPercArr = [];
		mainChannelPercArr.push({"y":result[i].positivePerc,"color":'#64C664'});
		mainChannelPercArr.push({"y":result[i].negativePerc,"color":'#D33E39'});
		$("#stateWiseMainGraphGovt"+i).highcharts({
			chart: {
				type: 'column',
				backgroundColor:'transparent'
			},

			title: {
				text: null
			},

			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: ['+ve', '-ve']
			},

			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
					legend:{
				enabled:false
			},
			tooltip: {
				enabled:false,
				formatter: function () {
					return '<b>' + this.x + '</b>' +
						this.series.name + ': ' + this.y + '<br/>'
				}
			},

			plotOptions: {
				pointPadding: 0.2,
				borderWidth: 2,
				groupPadding: 0.2,
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y)+'%';
							}
						}
					  
					},
				}
			},

			series: [{
				name: ' ',
				data: mainChannelPercArr
			}]
		});
		var graphIdSub = 0;
		graphIdMainA = graphIdMainA + 1;
		
		newsChannelCatCountArr.push({"y":result[i].tvNewsDetailsVOList[0].categoryCount,"color":'#64C664'});
		newsChannelCatCountArr.push({"y":result[i].tvNewsDetailsVOList[1].categoryCount,"color":'#D33E39'});
		$("#newsChannelsGovt"+i).highcharts({
			chart: {
				type: 'column',
				backgroundColor:'transparent'
			},

			title: {
				text: null
			},

			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: newsChannelCatArr
			},

			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
					legend:{
				enabled:false
			},
			tooltip: {
				enabled:false,
				formatter: function () {
					return '<b>' + this.x + '</b>' +
						this.series.name + ': ' + this.y + '<br/>'
				}
			},

			plotOptions: {
				pointPadding: 0.2,
				borderWidth: 2,
				groupPadding: 0.2,
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y);
							}
						}
					  
					},
				}
			},

			series: [{
				name: ' ',
				data: newsChannelCatCountArr
			}]
		});
		for(var j in result[i].tvNewsDetailsVOList)
		{
			var partyProgramsArr = []	
			graphIdSub = graphIdSub + 1;
			str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCount+'</p>';
			str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCount+'</p>';
			var partyProgramsSubArr = [];
			partyProgramsSubArr.push({"y":result[i].tvNewsDetailsVOList[j].positiveCount,"color":'#64C664'});
			partyProgramsSubArr.push({"y":result[i].tvNewsDetailsVOList[j].negativeCount,"color":'#D33E39'});
		
			$("#newsChannelsGovt"+i+j+graphIdSub).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['+ve', '-ve']
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					min: 0,
					title: {
						text: null
					}
				},
				legend:{
					enabled:false
				},
				tooltip: {
					formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y + '<br/>' +
							'Total: ' + this.point.stackTotal;
					}
				},

				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						},
					}
				},
				series: [{
					name: ' ',
					data: partyProgramsSubArr
				}]
			});
		}
		var graphIdMain = 0;
		for(var k in result[i].tvNewsDetailsVOList1)
		{
			var graphId = 0;
			var resultArr = [];
			var resultArrColors = ['#333','#555'];
			
			
			graphIdMain = graphIdMain + 1;
			//resultSubTotArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveCount,"color":'#64C664'});
			var resultSubTotArr = [];
			resultSubTotArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].categoryCount,"color":'#64C664'});
			resultSubTotArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[1].categoryCount,"color":'#D33E39'});
			$("#newsChannelsProgramsTotGovt"+i+k+graphIdMain).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: catSubTotArr
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					min: 0,
					title: {
						text: null
					}
				},
				legend:{
					enabled:false
				},
				tooltip: {
					enabled:false,
					formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y + '<br/>' +
							'Total: ' + this.point.stackTotal;
					}
				},

				plotOptions: {
					pointPadding: 0.2,
					borderWidth: 2,
					groupPadding: 0.2,
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						},
					}
				},

				series: [{
					name: ' ',
					data: resultSubTotArr
				}]
			});
			for(var l in result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList)
			{
				var resultSubArr = [];
				graphId = graphId + 1;
				resultSubArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveCount,"color":'#64C664'});
				resultSubArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].negativeCount,"color":'#D33E39'});
				
				$("#newsChannelsProgramsGovt"+i+k+graphId).highcharts({
					chart: {
						type: 'column',
						backgroundColor:'transparent'
					},

					title: {
						text: null
					},

					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: ['+ve', '-ve']
					},

					yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						allowDecimals: false,
						min: 0,
						title: {
							text: null
						}
					},
					legend:{
						enabled:false
					},
					tooltip: {
						enabled:false,
						formatter: function () {
							return '<b>' + this.x + '</b><br/>' +
								this.series.name + ': ' + this.y + '<br/>' +
								'Total: ' + this.point.stackTotal;
						}
					},

					plotOptions: {
						pointPadding: 0.2,
						borderWidth: 2,
						groupPadding: 0.2,
						column: {
							stacking: 'normal',
							dataLabels: {
								enabled: true,
								 formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return (this.y);
									}
								}
							  
							},
						}
					},

					series: [{
						name: ' ',
						data: resultSubArr
					}]
				});
			}
		}
	}
}
    function getRescentNewsBulletinTime(){
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getRescentNewsBulletinTime"
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getRescentNewsBulletinTime"
		}).then(function(result){
			if(result != null){
				$("#lastUpdatedTimeElecId").html("Last Updated : "+ result[0].categoryName);
			}
		});
	}    
	setInterval(function() {
		getRescentNewsBulletinTime();
	}, 60 * 1000);
	
	$(document).on("click",".basicBulletinsCls",function(){
		var categoryIds = $(this).attr("attr_categoryId");
		var benefitIds = $(this).attr("attr_benefitId");
		var orgId = $(this).attr("attr_orgId");
		var orgType = $(this).attr("attr_orgtype");
		var searchType = $(this).attr("attr_searchType");
		
		
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&scops='+impactScopeIds+'&orgIdStr='+orgId+'&orgType='+orgType+'&bfIds='+benefitIds+'&ediDistIdsStr='+categoryIds+'&npsStr='+newsChannelsIdsGlbl+'&status='+searchType+'&stIdx=0&edIdx=6&callFrom=bad','_blank');

	});
	$(document).on("click",".datesClass",function(){  
		var type = $(this).attr("attr_type");
		if(type == "currentMonth"){
			currentFromDateEmn = moment().format('DD-MM-YYYY');
			currentToDateEmn = moment().format('DD-MM-YYYY');
			$("#emnHeadDate").html(" TODAY ( "+currentFromDateEmn+" )");      
			getMediaProgramsOnParty(globalUserAccessLevelId,globalUserAccessLevelValues);   
		}else if(type == "lastMonth"){
			currentFromDateEmn = moment().subtract(1, 'month').startOf('month').format('DD-MM-YYYY');
			currentToDateEmn = moment().subtract(1, 'month').endOf('month').format('DD-MM-YYYY');
			$("#emnHeadDate").html("Last Month("+currentFromDateEmn+" to "+currentToDateEmn+")");
			getMediaProgramsOnParty(globalUserAccessLevelId,globalUserAccessLevelValues);
		}
	
		$("#dateRangeIdForEmn").daterangepicker({            
		
			opens: 'left',
			startDate: currentFromDateEmn,  
			endDate: currentToDateEmn,
			locale: {
				format: 'DD-MM-YYYY'
			},
			ranges: {
				'Today' : [moment(), moment()],
				'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
				'Last 30 Days': [moment().subtract(29, 'days'), moment()],
				'Last 3 Months': [moment().subtract(3, 'month'), moment()],
				'Last 6 Months': [moment().subtract(6, 'month'), moment()],
				'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
				'This Month': [moment().startOf('month'), moment()],
				'This Year': [moment().startOf('Year'), moment()]
			}  
		});
	});
	$(document).on("click",".stateWiseTotalsCls",function(){
		var categoryIds = $(this).attr("attr_category_id");
		var partyId = $(this).attr("attr_party_id");
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&scops='+impactScopeIds+'&orgIdStr='+partyId+'&orgType=N&bfIds=1,2&ediDistIdsStr='+categoryIds+'&npsStr='+newsChannelsIdsGlbl+'&status=category&stIdx=0&edIdx=6&callFrom=bad');
	});
	
	$(document).on("click",".stateWiseIndividualCls",function(){
		var categoryId = $(this).attr("attr_category_id");
		var channelId = $(this).attr("attr_channel_id");
		var partyId = $(this).attr("attr_party_id");
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&scops='+impactScopeIds+'&orgIdStr='+partyId+'&orgType=N&bfIds=1,2&ediDistIdsStr='+categoryId+'&npsStr='+channelId+'&status=category&stIdx=0&edIdx=6&callFrom=bad');
	});
	
	$(document).on("click",".stateWiseGovtTotalsCls",function(){
		var partyId = $(this).attr("attr_party_id");
		var categoryId = $(this).attr("attr_category_id");
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&scops='+impactScopeIds+'&orgIdStr='+partyId+'&orgType=Y&bfIds=1,2&ediDistIdsStr='+categoryId+'&npsStr='+newsChannelsIdsGlbl+'&status=category&stIdx=0&edIdx=6&callFrom=bad');
	});
	
	$(document).on("click",".stateWiseGovtIndividualCls",function(){
		var categoryId = $(this).attr("attr_category_id");
		var channelId = $(this).attr("attr_channel_id");
		var partyId = $(this).attr("attr_party_id");
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&scops='+impactScopeIds+'&orgIdStr='+partyId+'&orgType=Y&bfIds=1,2&ediDistIdsStr='+categoryId+'&npsStr='+channelId+'&status=category&stIdx=0&edIdx=6&callFrom=bad');
	});
	
	$(document).on("click",".compPartyFstBlkCls",function(){
		var channelId = $(this).attr("attr_channel_id");
		var categoryId = $(this).attr("attr_category_id");
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&scops='+impactScopeIds+'&orgIdStr= &orgType=N&bfIds=1,2&ediDistIdsStr='+categoryId+'&npsStr='+channelId+'&status=category&stIdx=0&edIdx=6&callFrom=bad');
	});
	
	$(document).on("click",".compGovtFstBlkCls",function(){
		var channelId = $(this).attr("attr_channel_id");
		var categoryId = $(this).attr("attr_category_id");
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&scops='+impactScopeIds+'&orgIdStr= &orgType=Y&bfIds=1,2&ediDistIdsStr='+categoryId+'&npsStr='+channelId+'&status=category&stIdx=0&edIdx=6&callFrom=bad');
	});
	
	$(document).on("click",".showStateWiseBulletinDetails",function(){
		var programName = $(this).attr("attr_newsprogramname");
		var bfIdsStr="";
		var categoryId="";
		
		if(programName == "Total" ){
			bfIdsStr = " ";
			categoryId=$(this).attr("attr_catIdStr");
		}
		if(programName == "News Bulletin" ){
			bfIdsStr = " ";
			categoryId=$(this).attr("attr_catIdStr");
		}
		if(programName == "News Program"){
			bfIdsStr = " ";
			categoryId=$(this).attr("attr_catIdStr");
		}
		if(programName == "positive"){
			bfIdsStr = "1";
			categoryId= " ";
		}
		if(programName == "negative"){
			bfIdsStr = "2";
			categoryId= " ";
		}
		
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&npsStr='+newsChannelsIdsGlbl+'&scops='+impactScopeIds+'&orgIdStr= &bfIds='+bfIdsStr+'&catIdStr='+categoryId+'&orgType=Y&stIdx=0&edIdx=6&callFrom=dgpdsb');
	});
	
	$(document).on("click",".showChannelWiseProblemsinGovtDetailed",function(){
		var channelId = $(this).attr("attr_channel_id");
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&npsStr='+channelId+'&scops='+impactScopeIds+'&orgType=Y&stIdx=0&edIdx=6&callFrom=dgpdcb');
	});
	
	$(document).on("click",".showDepartmentWiseProblemForState",function(){
		var orgId = $(this).attr("attr_orgId");
		var channelIds = $(this).attr("attr_channelIds");
		
		window.open('showElectronicBulletinsAction.action?levelId='+locationLevelIdGlb+'&temp='+locationValueArrGlb+'&state='+globalState+'&sdat='+currentFromDateEmn+'&edat='+currentToDateEmn+'&npsStr='+channelIds+'&scops='+impactScopeIds+'&orgIdStr='+orgId+'&orgType=Y&stIdx=0&edIdx=6&callFrom=dgpdwp');
	});	