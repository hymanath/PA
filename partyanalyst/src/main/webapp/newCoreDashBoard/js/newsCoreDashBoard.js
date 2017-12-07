	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));
	
	var currentFromDate = moment().format("DD-MM-YYYY");
	var currentToDate = moment().format("DD-MM-YYYY");
	var newsPaperIdsGlob = [1,2,3,10,11,12];
	var impactScopeIdsGlob = [1,2,3,4,5,6,8];
	function globalPrintMediaCalls(type)
	{
		if(type == "default"){
			$('#dateRangeIdForNews').data('daterangepicker').setStartDate(moment());
			$('#dateRangeIdForNews').data('daterangepicker').setEndDate(moment());
			currentFromDate = moment().format("DD-MM-YYYY")
			currentToDate = moment().format("DD-MM-YYYY")
			$("#currentViewing").html("TODAY"+" ( "+moment().format("DD-MM-YYYY")+"-"+moment().format("DD-MM-YYYY")+" )");
		}else if(type == "currentMonth"){
			$('#dateRangeIdForNews').data('daterangepicker').setStartDate(moment().startOf("month"));
			$('#dateRangeIdForNews').data('daterangepicker').setEndDate(moment().endOf("month"));
			currentFromDate = moment().startOf("month").format("DD-MM-YYYY")
			currentToDate = moment().endOf("month").format("DD-MM-YYYY")
			$("#currentViewing").html("THIS MONTH"+" ( "+moment().startOf("month").format("DD-MM-YYYY")+"-"+moment().endOf("month").format("DD-MM-YYYY")+" )");
		}else if(type == "lastMonth"){
			$('#dateRangeIdForNews').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
			$('#dateRangeIdForNews').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
			currentFromDate = moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")
			currentToDate = moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")
			$("#currentViewing").html("LAST MONTH"+" ( "+moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")+"-"+moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")+" )");
		}
		$("#dateRangeIdForNews").val(currentFromDate+" - "+currentToDate);
		commonNewsBasicCalls();
	}
	
	$(document).on("click",".newsRefresh",function(){
		globalPrintMediaCalls('');
	});
	$(document).ready(function(){
		$("#dateRangeIdForNews").daterangepicker({
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
	});	
	
	$('#dateRangeIdForNews').on('apply.daterangepicker', function(ev, picker) {
		
		currentFromDate = picker.startDate.format('DD-MM-YYYY');
		currentToDate = picker.endDate.format('DD-MM-YYYY');
		//getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems()
		if(picker.chosenLabel == "Today"){
			$("#currentViewing").html(" TODAY ( "+currentFromDate+" )");
		}else{
			$("#currentViewing").html(picker.chosenLabel+"("+currentFromDate+" to "+currentToDate+")");
		}
		
		commonNewsBasicCalls();
		/* getNewsBasicCounts();
		 if($(".newsIconExpand i").attr("class").split(" ")[1]=="glyphicon-resize-small"){
			$(".newsliCls").each(function(){
				if($(this).hasClass("active")){
					getUserTypeWiseNewsCounts($(this).attr("attr_value"));
				}
			});
		}
			if($('.newsHiddenMoreBlock').css('display') != 'none'){
				$(".viewsLiClass").each(function(){
					if($(this).hasClass("active")){
						var id = $(this).attr("id");
						$( "#"+id).trigger( "click" );
						
						$(".partyDistrictWiseDiv").removeClass("active");
						$(".partyDistrictWiseDiv").each(function(index){
							if(index==0){
								$(this).addClass("active");
							}
						});
					}
				});
			} */ 
			
		
	  
	});
	
	$(document).on("click",".settingsIconNews",function(e){
		$(this).closest(".newsBlock").find(".newsBlockDropDown").toggle();
		e.stopPropagation();
	});
	$(document).on("click",".newsSetClose",function(){
		$(this).closest(".newsBlockDropDown").hide();
	});
	
	/* $(document).on("click",".newsIconExpand",function(){
		$(".dateRangePickerClsForNews").toggleClass("hide");
		$(".newsHead").toggleClass('col-md-9 col-sm-9').toggleClass('col-md-8 col-sm-8');
		$(".newsHead1").toggleClass('col-md-3 col-sm-3').toggleClass('col-md-4 col-sm-4');
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".newsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".newsHiddenBlock,.morenewsBlocksIcon").toggle();
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".editionWiseBlock").show();
			getUserTypeWiseNewsCounts(1);
			setTimeout(function(){
				$('html,body').animate({
					scrollTop: $(".newsBlock").offset().top},
				'slow');
			},500);
		}else{
			$(".newsHiddenMoreBlock").hide();
			$(".editionWiseBlock").hide();
		}
		if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".dateRangePickerClsForTraining").addClass("hide");
			$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
			$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
			$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
			$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
			$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
			$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerCls").toggleClass("hide");
			$(".moreBlocksIcon").removeClass("unExpandBlock");
		}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForMeetings").toggleClass("hide");
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
		}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents,.comparisonBlockActivities ").hide();
			$(".panelBlockCollapseIcon").addClass("collapsed");
			$(".activitesExpandIcon").parent().parent().parent().parent().find(".collapse").removeClass("in").addClass("collapsed");
			$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
			$(".eventsListExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
			$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForEvents").toggleClass("hide");
		}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
			$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
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
		}else if( $(".emnIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".emnIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".moreBlockEMN ,.newEmnHideCls,.dateRangePickerClsForEmn,.newsComparisonUl").hide();
				$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}
	
	}); */
	
	//More Block Icon Click
	$(document).on("click",".morenewsBlocksIcon",function(){
		$(".newsHiddenMoreBlock").toggle();
		//getDetailedPartyMainEditionsOverview();
		getDetailedPartyDistrictEditionsOverview();
		setcolorsForStatus();
		getDetailedPartyNewsTypeAnalysis();
		getDetailedPartyPartyVsPublications("party");
		getDetailedPartyCategoryActivities("location","N");
	});
	
	$(document).on("click",".viewsLiClass",function(){
	$(".viewsLiClass").removeClass("active");
	$(this).addClass("active");
	
	$(".mainBuildingDivClass").hide();
	var divId = $(this).attr("attr_div_id");
	$("#"+divId).show();
	$("#"+divId).removeClass("active");
	
});
	//Detailed Block Click Action Party And Govternment Start
	$(document).on("click","#detailedPartyLiId",function(){
		//getDetailedPartyMainEditionsOverview();
		getDetailedPartyDistrictEditionsOverview();
		getDetailedPartyNewsTypeAnalysis();
		getDetailedPartyPartyVsPublications("party");
		getDetailedPartyCategoryActivities("location","N");
	});
	
	$(document).on("click","#detailedGovernmentLiId",function(){
		$("#problemsDetailedOverviewSubLevel").html('');
		getDetailedGovtDepartmentWiseDistrictsOverview();
		getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(7,'');
		getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(0,'');
		getDetailedGovernamentTrendingTrackedIssues();
		getDetailedGovernmentDistrictWiseArticleRelatedToProblem();
		getGovernmentPartyCategoryActivities("location","Y");
	});
	
	$(document).on("click",".partyDistrictWiseDiv",function(){
		getDetailedPartyPartyVsPublications($(this).attr("attr_search_type"));
	});
	$(document).on("click",".categoryDistrictWiseDiv",function(){
		getDetailedPartyCategoryActivities($(this).attr("attr_search_type"),"N");
	});
	$(document).on("click",".govtcategoryDistrictWiseDiv",function(){
		getGovernmentPartyCategoryActivities($(this).attr("attr_search_type"),"Y");
	});
	//Detailed Block Click Action Party And Govternment End
	
	//Comparision Block Click Action For Party And Government Start
	
	$(document).on("click","#comparisonPartyLiId",function(){
		$("#partyWiseComparisionBlock").html('');
		$("#partyComparisionSubLevelMemberDetailsDiv").html('');
		$("#PartyComparisionNewsTypeAnalysisDiv").html('');
		$("#ComparisionPartyDistrictWiseNewsReport").html('');
		$("#partyAndPublication").html('');
		$("#CompaisionPartyAndPublicationWiseDetailsDiv").html('');
		$("#CompaisionPartyAndPublicationWiseDetailsDiv").html('');
		setcolorsForStatus();
		getChildUserTypesByItsParentUserType1();
		
	});

	$(document).on("click","#comparisonGovernmentLiId",function(){
		$("#ministerSubLevelDetailsDiv").html('');
		getComparisonGovtMinistriesInfo();
	});
	
	//Comparision Block Click Action For Party And Government End
	
	//Main Block Ajax Calls And Build Start
	function getNewsBasicPartyCounts(){
		$("#spinnerStatic").css("display","inline-block");
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var startDate=currentFromDate,endDate=currentToDate;
		var state = globalState;
		$("#newsBlockMainId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getNewsBasicPartyCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBasicPartyCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			mainNewsBlock(result);
			/*if(result != null && result.length > 0){
				$("#tdpMainTotal").html(result[0].totalCount);
				$("#tdpMainPositive").html(result[0].positiveCountMain);
				$("#tdpMainNegative").html(result[0].negativCountMain);
				if((result[0].totalCount) > 0){
					$("#tdpMainPositivePercent").html(" "+((result[0].positiveCountMain*100)/(totalCount)).toFixed(2)+" %");
					$("#tdpMainNegativePercent").html(" "+((result[0].negativCountMain*100)/(totalCount)).toFixed(2)+" %");
				}
				else{
					$("#tdpMainPositivePercent").html(" 0.0 %");
					$("#tdpMainNegativePercent").html(" 0.0 %");
				}
				
				$("#tdpDistTotal").html(result[0].count);				
				$("#tdpDistPositive").html(result[0].positiveCountDist);
				$("#tdpDistNegative").html(result[0].negativCountDist);
				if(result[0].count > 0){
					$("#tdpDistPositivePercent").html(" "+((result[0].positiveCountDist*100)/(count)).toFixed(2)+" %");
					$("#tdpDistNegativePercent").html(" "+((result[0].negativCountDist*100)/(count)).toFixed(2)+" %");
				}
				else{
					$("#tdpDistPositivePercent").html(" 0.0 %");
					$("#tdpDistNegativePercent").html(" 0.0 %");
				}
				
				var oppTotal=(result[1].totalCount);
				$("#oppMainTotal").html(oppTotal);
				$("#oppPositiveTotal").html(result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain);
				$("#oppNegativeTotal").html(result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain);
				if(oppTotal > 0){
					$("#oppPositiveTotalPercent").html(" "+(((result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain)/oppTotal)*100).toFixed(2)+" %");
					$("#oppNegativeTotalPercent").html(" "+(((result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain)/oppTotal)*100).toFixed(2)+" %");
				}
				else{
					$("#oppPositiveTotalPercent").html(" 0.0 %");
					$("#oppNegativeTotalPercent").html(" 0.0 %");
				}
				
				var ysrcMainTotal = result[1].totalCount;
				$("#ysrcMainTotal").html(ysrcMainTotal);
				$("#ysrcMainPositive").html(result[1].positiveCountMain);
				$("#ysrcMainNegative").html(result[1].negativCountMain);
				
				if(ysrcMainTotal > 0){
					$("#ysrcMainPositivePercent").html(" "+((result[1].positiveCountMain*100)/ysrcMainTotal).toFixed(2)+" %");
					$("#ysrcMainNegativePercent").html(" "+((result[1].negativCountMain*100)/ysrcMainTotal).toFixed(2)+" %");	
				}
				else{
					$("#ysrcMainPositivePercent").html(" 0.0 %");
					$("#ysrcMainNegativePercent").html(" 0.0 %");
				}
				
				var incMainTotal = result[2].totalCount;
				$("#incMainTotal").html(incMainTotal);
				$("#incMainPositive").html(result[2].positiveCountMain);
				$("#incMainNegative").html(result[2].negativCountMain);
				if(incMainTotal > 0){
					$("#incMainPositivePercent").html(" "+((result[2].positiveCountMain*100)/incMainTotal).toFixed(2)+" %");
					$("#incMainNegativePercent").html(" "+((result[2].negativCountMain*100)/incMainTotal).toFixed(2)+" %");
				}
				else{
					$("#incMainPositivePercent").html(" 0.0 %");
					$("#incMainNegativePercent").html(" 0.0 %");
				}
				 var bjpMainTotal = result[3].totalCount;
				 $("#bjpMainTotal").html(bjpMainTotal);
				 $("#bjpMainPositive").html(result[3].positiveCountMain);
				 $("#bjpMainNegative").html(result[3].negativCountMain);
				 
				 if(bjpMainTotal>0){
					$("#bjpMainPositivePercent").html(" "+((result[3].positiveCountMain*100)/bjpMainTotal).toFixed(2)+" %");
					$("#bjpMainNegativePercent").html(" "+((result[3].negativCountMain*100)/bjpMainTotal).toFixed(2)+" %");
				 }
				 else{
					$("#bjpMainPositivePercent").html(" 0.0 %");
					$("#bjpMainNegativePercent").html(" 0.0 %"); 
				 }
				 
				 var oppDistTotal=(result[1].count);
				 $("#oppDistTotal").html(oppDistTotal);
				 
				 $("#oppDistPositive").html(result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist);
				 $("#oppDistNegative").html(result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist);
				 
				 if(oppDistTotal > 0){
					$("#oppDistPositivePercent").html(" "+(((result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist)*100)/oppDistTotal).toFixed(2)+" %");
					$("#oppDistNegativePercent").html(" "+(((result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist)*100)/oppDistTotal).toFixed(2)+" %");
				 }
				 else{
					$("#oppDistPositivePercent").html(" 0.0 %");
					$("#oppDistNegativePercent").html(" 0.0 %"); 
				 }
				 
				 var ysrcDistTotal = result[1].count;
				$("#ysrcDistTotal").html(ysrcDistTotal);
				$("#ysrcDistPositive").html(result[1].positiveCountDist);
				$("#ysrcDistNegative").html(result[1].negativCountDist);
				
				if(ysrcDistTotal > 0){
					$("#ysrcDistPositivePercent").html(" "+((result[1].positiveCountDist*100)/ysrcDistTotal).toFixed(2)+" %");
					$("#ysrcDistNegativePercent").html(" "+((result[1].negativCountDist*100)/ysrcDistTotal).toFixed(2)+" %");
				}
				else{
					$("#ysrcDistPositivePercent").html(" 0.0 %");
					$("#ysrcDistNegativePercent").html(" 0.0 %");
				}
				
				var incDistTotal = result[2].count;
				$("#incDistTotal").html(incDistTotal);
				$("#incDistPositive").html(result[2].positiveCountDist);
				$("#incDistNegative").html(result[2].negativCountDist);
				if(incDistTotal > 0){
					$("#incDistPositivePercent").html(" "+((result[2].positiveCountDist*100)/incDistTotal).toFixed(2)+" %");
					$("#incDistNegativePercent").html(" "+((result[2].negativCountDist*100)/incDistTotal).toFixed(2)+" %");
				}
				else{
					$("#incDistPositivePercent").html(" 0.0 %");
					$("#incDistNegativePercent").html(" 0.0 %");
				}
				
				var bjpDistTotal = result[3].count;
				 $("#bjpDistTotal").html(bjpDistTotal);
				 $("#bjpDistPositive").html(result[3].positiveCountDist);
				 $("#bjpDistNegative").html(result[3].negativCountDist);
				 
				 if(bjpDistTotal>0){
					$("#bjpDistPositivePercent").html(" "+((result[3].positiveCountDist*100)/bjpDistTotal).toFixed(2)+" %");
					$("#bjpDistNegativePercent").html(" "+((result[3].negativCountDist*100)/bjpDistTotal).toFixed(2)+" %");
				 }
				 else{
					$("#bjpDistPositivePercent").html(" 0.0 %");
					$("#bjpDistNegativePercent").html(" 0.0 %"); 
				 }
				 
				 var govtMainTotal = result[4].totalCount;
				 $("#govtMainTotal").html(govtMainTotal);
				 $("#govtMainPositive").html(result[4].positiveCountMain);
				 $("#govtMainNegative").html(result[4].negativCountMain);
				 if(govtMainTotal > 0){
					$("#govtMainPositivePercent").html(" "+((result[4].positiveCountMain*100)/govtMainTotal).toFixed(2)+" %");
					$("#govtMainNegativePercent").html(" "+((result[4].negativCountMain*100)/govtMainTotal).toFixed(2)+" %");
				 }
				 else{
					$("#govtMainPositivePercent").html(" 0.0 %");
					$("#govtMainNegativePercent").html(" 0.0 %"); 
				 }
				 
				var govtDistTotal = result[4].count;
				 $("#govtDistTotal").html(govtDistTotal);
				 $("#govtDistPositive").html(result[4].positiveCountDist);
				 $("#govtDistNegative").html(result[4].negativCountDist);
				 if(govtDistTotal > 0){
					$("#govtDistPositivePercent").html(" "+((result[4].positiveCountDist*100)/govtDistTotal).toFixed(2)+" %");
					$("#govtDistNegativePercent").html(" "+((result[4].negativCountDist*100)/govtDistTotal).toFixed(2)+" %");
				 }
				  else{
					$("#govtDistPositivePercent").html(" 0.0 %");
					$("#govtDistNegativePercent").html(" 0.0 %");	
				 }
			}*/
			
		$("#spinnerStatic").hide();
		});
	}
	function mainNewsBlock(result)
	{
		var str='';
		str+='';
		str+='<h4 class="text-capital"><span class="headingColor" style="margin-right:5px"><img src="newCoreDashBoard/img/TDP.png" alt="tdp icon" class="newsIcon"/>Telugu Desam Party</span></h4>';
		str+='<div class="row">';
			str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
				str+='<table class="table table-condensed tableNews bg_ED">';
					str+='<tr>';
						str+='<td>';
							str+='<p class="text-capital responsiveFont">Main Edition</p>';
							str+='<p><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid =" " attr_isdepartment = "N" attr_partyids="872">'+((result[0].positiveCountMain)+(result[0].negativCountMain))+'<small>('+result[0].totalCount+')</small></a></p>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
							str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="1" attr_isdepartment = "N" attr_partyids="872">'+result[0].positiveCountMain+'</a></span>';
							if(result[0].totalCount > 0){
								str+='<small id="" class="text-success"> '+((result[0].positiveCountMain*100)/((result[0].positiveCountMain)+(result[0].negativCountMain))).toFixed(2)+' %</small>';
							}else{
								str+='<small id="" class="text-success"> 0.0 %</small>';
							}
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
							str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="2" attr_isdepartment = "N" attr_partyids="872">'+result[0].negativCountMain+'</a></span>';
							if(result[0].totalCount > 0){
								str+='<small id="" class="text-danger"> '+((result[0].negativCountMain*100)/((result[0].positiveCountMain)+(result[0].negativCountMain))).toFixed(2)+' %</small>';
							}else{
								str+='<small id="" class="text-danger"> 0.0 %</small>';
							}
								
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
			str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0">';
				str+='<table class="table table-condensed tableNews bg_ED">';
					str+='<tr>';
						str+='<td>';
							str+='<p class="text-capital">Dist edition</p>';
							str+='<p><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid =" " attr_isdepartment = "N" attr_partyids="872">'+((result[0].positiveCountDist)+(result[0].negativCountDist))+'<small>('+result[0].count+')</small></a></p>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted">Positive</p>';
							str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid ="1" attr_isdepartment = "N" attr_partyids="872">'+result[0].positiveCountDist+'</a></span>';
							if(result[0].count > 0){
								str+='<small class="text-success" id=""> '+((result[0].positiveCountDist*100)/((result[0].positiveCountDist)+(result[0].negativCountDist))).toFixed(2)+' %</small>';
							}else{
								str+='<small class="text-success" id=""> 0.0 %</small>';
							}
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital text-muted">Negative</p>';
							str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid ="2" attr_isdepartment = "N" attr_partyids="872">'+result[0].negativCountDist+'</a></span>';
							if(result[0].count > 0){
								str+='<small class="text-danger" id=""> '+((result[0].negativCountDist*100)/((result[0].positiveCountDist)+(result[0].negativCountDist))).toFixed(2)+' %</small>';
							}else{
								str+='<small class="text-danger" id=""> 0.0 %</small>';
							}
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
		
		/* Opposition Parties*/
		str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/opp.png" style="width:25px;" alt="tdp icon" class="debatesPartyIcon"/>Other Parties</span></h4>';
		
			str+='<div class="row">';
				str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
					str+='<table class="table table-condensed tableNews ">';
						str+='<tr class="bg_ED">';
							str+='<td>';
							var mTot=0,mPos=0,mNeg=0,dTot=0,dPos=0,dNeg=0;
							//for(var i=1;i<(result.length-1);i++){
								mTot = result[1].totalCount;
								dTot = result[1].count;
								mPos = result[1].positiveCountMain;
								dPos = result[1].positiveCountDist;
								mNeg = result[1].negativCountMain;
								dNeg = result[1].negativCountDist;
								mFTot = ((result[1].positiveCountMain)+(result[1].negativCountMain))
								dFTot = ((result[1].positiveCountDist)+(result[1].negativCountDist))
							//}
						
								str+='<p class="text-capital">Main Edition</p>';
								str+='<p><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid =" " attr_isdepartment = "N" attr_partyids="1117,362,163">'+mFTot+'<small>('+mTot+')</small></a></p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Positive</p>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="1" attr_isdepartment = "N" attr_partyids="1117,362,163">'+mPos+'</a></span>';
								if(mTot>0)
									str+='<small id="" class="text-success"> '+((mPos*100)/mFTot).toFixed(2)+' %</small>';
								else
									str+='<small id="" class="text-success"> 0.0 %</small>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Negative</p>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="2" attr_isdepartment = "N" attr_partyids="1117,362,163">'+mNeg+'</a></span>';
								if(mTot > 0)
									str+='<small class="text-danger" id=""> '+((mNeg*100)/mFTot).toFixed(2)+' %</small>';
								else
									str+='<small class="text-danger" id=""> 0.0 %</small>';
							str+='</td>';
						str+='</tr>';
					for(var i=2;i<(result.length-1);i++)
					{
						if(result[i].organization !="GOVT" || result[i].organization !=""){
							str+='<tr>';
							str+='<td>';
								str+='<img src="newCoreDashBoard/img/'+result[i].organization+'.png" alt="cong logo" class="debatesPartyIcon"/><span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid =" " attr_isdepartment = "N" attr_partyids="'+result[i].organizationId+'">'+((result[i].positiveCountMain)+(result[i].negativCountMain))+'<small>('+result[i].totalCount+')</small></a></span>';
							str+='</td>';
							str+='<td>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="1" attr_isdepartment = "N" attr_partyids="'+result[i].organizationId+'">'+result[i].positiveCountMain+'</a></span>';
								if(result[i].totalCount > 0){
									str+='<small class="text-success"> '+((result[i].positiveCountMain*100)/((result[i].positiveCountMain)+(result[i].negativCountMain))).toFixed(2)+' %</small>';
								}else{
									str+='<small class="text-success"> 0.0 %</small>';
								}
							str+='</td>';
							str+='<td>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="2" attr_isdepartment = "N" attr_partyids="'+result[i].organizationId+'">'+result[i].negativCountMain+'</a></span>';
								if(result[i].totalCount > 0){
									str+='<small class="text-danger"> '+((result[i].negativCountMain*100)/((result[i].positiveCountMain)+(result[i].negativCountMain))).toFixed(2)+' %</small>';
								}else{
									str+='<small class="text-danger"> 0.0 %</small>';
								}
							str+='</td>';
						str+='</tr>';
						}
						
					}
					str+='</table>';
				str+='</div>';
				str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0">';
					str+='<table class="table table-condensed tableNews ">';
						str+='<tr class="bg_ED">';
							str+='<td>';
								str+='<p class="text-capital">Dist Edition</p>';
								str+='<p><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid =" " attr_isdepartment = "N" attr_partyids="1117,362,163">'+dFTot+'<small>('+dTot+')</small></a></p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Positive</p>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid ="1" attr_isdepartment = "N" attr_partyids="1117,362,163">'+dPos+'</a></span>';
								if(dTot > 0)
									str+='<small class="text-success" id=""> '+((dPos*100)/dFTot).toFixed(2)+' %</small>';
								else
									str+='<small class="text-success" id=""> 0.0 %</small>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Negative</p>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid ="2" attr_isdepartment = "N" attr_partyids="1117,362,163">'+dNeg+'</a></span>';
								if(dTot > 0)
									str+='<small class="text-danger" id=""> '+((dNeg*100)/dFTot).toFixed(2)+' %</small>';
								else
									str+='<small class="text-danger" id=""> 0.0 %</small>';
							str+='</td>';
						str+='</tr>';
						
					for(var i=2;i<(result.length-1);i++)
					{
						if(result[i].organization !="GOVT" || result[i].organization !=""){
							str+='<tr>';
								str+='<td>';
									str+='<img src="newCoreDashBoard/img/'+result[i].organization+'.png" alt="cong logo" class="debatesPartyIcon"/><span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid =" " attr_isdepartment = "N" attr_partyids="'+result[i].organizationId+'">'+((result[i].positiveCountDist)+(result[i].negativCountDist))+'<small>('+result[i].count+')</small></a></span>';
								str+='</td>';
								str+='<td>';
									str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid ="1" attr_isdepartment = "N" attr_partyids="'+result[i].organizationId+'">'+result[i].positiveCountDist+'</a></span>';
									if(result[i].count > 0){
										str+='<small class="text-success"> '+((result[i].positiveCountDist*100)/((result[i].positiveCountDist)+(result[i].negativCountDist))).toFixed(2)+' %</small>';
									}else{
										str+='<small class="text-success"> 0.0 %</small>';
									}
								str+='</td>';
								str+='<td>';
									str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid ="2" attr_isdepartment = "N" attr_partyids="'+result[i].organizationId+'">'+result[i].negativCountDist+'</a></span>';
									if(result[i].count > 0){
										str+='<small class="text-danger" id=""> '+((result[i].negativCountDist*100)/((result[i].positiveCountDist)+(result[i].negativCountDist))).toFixed(2)+' %</small>';
									}else{
										str+='<small class="text-danger" id=""> 0.0 %</small>';
									}
								str+='</td>';
							str+='</tr>';
						}
					}
					str+='</table>';
					
				str+='</div>';
			str+='</div>';
			
			//Govt block building
			var t = result.length-1;
			str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/GOVT.png" style="width:25px;" alt="government icon" class="newsIcon"/>Government</span></h4>';
			str+='<div class="row">';
				str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
					str+='<table class="table table-condensed tableNews bg_ED">';
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-capital">Main Edition</p>';
								str+='<p><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid =" " attr_isdepartment = "Y" attr_partyids=" ">'+((result[t].positiveCountMain)+(result[t].negativCountMain))+'<small>('+result[t].totalCount+')</small></a></p>';
								str+='</td>';
								str+='<td>';
								str+='<p class="text-capital text-muted">Positive</p>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="1" attr_isdepartment = "Y" attr_partyids=" ">'+result[t].positiveCountMain+'</a></span>';
									if(result[t].totalCount>0){
										str+='<small class="text-success" id=""> '+((result[t].positiveCountMain*100)/((result[t].positiveCountMain)+(result[t].negativCountMain))).toFixed(2)+' %</small>';
									}else{
										str+='<small class="text-success" id=""> 0.0  %</small>';
									}
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Negative</p>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="2" attr_isdepartment = "Y" attr_partyids=" ">'+result[t].negativCountMain+'</a></span>';
								if(result[t].totalCount>0){
									str+='<small class="text-danger" id=""> '+((result[t].negativCountMain*100)/((result[t].positiveCountMain)+(result[t].negativCountMain))).toFixed(2)+'  %</small>';
								}else{
									str+='<small class="text-danger" id=""> 0.0 %</small>';
								}
							str+='</td>';
						str+='</tr>';
					str+='</table>';
				str+='</div>';
				str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0">';
					str+='<table class="table table-condensed tableNews bg_ED">';
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-capital">Dist Edition</p>';
								str+='<p><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid =" " attr_isdepartment = "Y" attr_partyids=" ">'+((result[t].positiveCountDist)+(result[t].negativCountDist))+'<small>('+result[t].count+')</sma;;></a></p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">positive</p>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid ="1" attr_isdepartment = "Y" attr_partyids=" ">'+result[t].positiveCountDist+'</a></span>';
								if(result[t].count > 0){
									str+='<small class="text-success" id=""> '+((result[t].positiveCountDist*100)/((result[t].positiveCountDist)+(result[t].negativCountDist))).toFixed(2)+' %</small>';
								}else{
									str+='<small class="text-success" id=""> 0.0 %</small>';
								}
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">negative</p>';
								str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="2,3" attr_benefitid ="2" attr_isdepartment = "Y" attr_partyids=" ">'+result[t].negativCountDist+'</a></span>';
								if(result[t].count > 0){
									str+='<small class="text-danger" id=""> '+((result[t].negativCountDist*100)/((result[t].positiveCountDist)+(result[t].negativCountDist))).toFixed(2)+' %</small>';
								}else{
									str+='<small class="text-danger" id=""> 0.0 %</small>';
								}
							str+='</td>';
						str+='</tr>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
	
		
		$("#newsBlockMainId").html(str);
	}
	//Main Block Ajax Calls And Build End
	
	// Top Five Positive And Negative Block Ajax Call
	function getUserTypeWiseNewsCounts(benefitId){
		$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var startDate,endDate;
		var jsObj={
				activityMemberId : globalActivityMemberId ,
				userTypeId : globalUserTypeId,
				state:globalState,
				fromDate:currentFromDate,
				toDate:currentToDate,
				benefitId:benefitId,
				npIdsArr : newsPaperIdsGlob,
				impactScopeIdsArr : impactScopeIdsGlob
			}
		
			$.ajax({
				type : 'POST',
				url : 'getUserTypeWiseNewsCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html('');
				buildgetUserTypeWiseNewsForTopFiveStrongResults(result,benefitId);
			});
	}
	
	// Top Five Positive And Negative Block Build Start
	function buildgetUserTypeWiseNewsForTopFiveStrongResults(result,benefitId){
		if(benefitId == 1){
			var str='';
			if(result != null && result.length > 0){
			
				var str='';
				
				for(var i in result){
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						if(result[i][0].userTypeId == 4 || result[i][0].userTypeId == 11){
							str+='<h5 class="text-capital">ORGANIZING SECRETARY / SECRETARY</h5>';
						}else{
							str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
						}
						str+='<div id="newsBlockGenSecStrong'+i+'" style="height:130px;"></div>';
					str+='</div>'
						
				}
				
			}
			$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html(str);
			if(result != null && result.length > 0){
				for(var i in result){
					
					var PositiveCountArray = [];
					var NegativeCountArray = [];
					var candidateNameArray=[];
					var countVar =0;
					
					if(result[i] !=null && result[i].length>0){
						for(var j in result[i]){
							candidateNameArray.push(result[i][j].name)
							PositiveCountArray.push(result[i][j].positiveCount)
							NegativeCountArray.push(result[i][j].negativeCount)
								
							countVar =countVar+1;
							if (countVar === 5) {
								break;
							}
						}
					}
					
						
					if( PositiveCountArray.length !=0 && NegativeCountArray.length !=0){
						$(function () {
							 $("#newsBlockGenSecStrong"+i).highcharts({
								  colors: ['#D33E39','#64C664'],
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
										stacking: 'percent',
										dataLabels: {
											enabled: true,
											 formatter: function() {
												if (this.y === 0) {
													return null;
												} else {
													return Highcharts.numberFormat(this.percentage,1) + '%';
												}
											}
										  
										}
									}
								},

								 tooltip: {
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

								series: [{
									name: 'Negative',
									data: NegativeCountArray,
									
								},{
									name: 'Positive',
									data: PositiveCountArray,
									
								}],
							 
							});
						});
					}else{
						$("#newsBlockGenSecStrong"+i).html("No Data Available");
						$("#newsBlockGenSecStrong"+i).css("height","35px");
							
					} 
					
				}
				
			}else{
				$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html("No Data Available");
			}
		}else if(benefitId == 2){
			var str='';
			if(result != null && result.length > 0){
			
				var str='';
				
				for(var i in result){
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						if(result[i][0].userTypeId == 4 || result[i][0].userTypeId == 11){
							str+='<h5 class="text-capital">ORGANIZING SECRETARY / SECRETARY</h5>';
						}else{
							str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
						}
						str+='<div id="newsBlockGenSecStrong'+i+'" style="width:100%;height:130px;"></div>';
					str+='</div>'
						
				}
				
			}
			$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html(str);
			if(result != null && result.length > 0){
				for(var i in result){
					
					var PositiveCountArray = [];
					var NegativeCountArray = [];
					var candidateNameArray=[];
					var countVar =0;
					
					if(result[i] !=null && result[i].length>0){
						for(var j in result[i]){
							candidateNameArray.push(result[i][j].name)
							PositiveCountArray.push(result[i][j].positiveCount)
							NegativeCountArray.push(result[i][j].negativeCount)
								
							countVar =countVar+1;
							if (countVar === 5) {
								break;
							}
						}
					}
					
						
					if( PositiveCountArray.length !=0 && NegativeCountArray.length !=0){
						var getWidth = $("#newsBlockGenSecStrong"+i).parent().width()+'px';
						$("#newsBlockGenSecStrong"+i).width(getWidth);
						$(function () {
							 $("#newsBlockGenSecStrong"+i).highcharts({
								 colors: ['#D33E39','#64C664'],
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
										stacking: 'percent',
										dataLabels: {
											enabled: true,
											 formatter: function() {
												if (this.y === 0) {
													return null;
												} else {
													return Highcharts.numberFormat(this.percentage,1) + '%';
												}
											}
										  
										}
									}
								},

								tooltip: {
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

								series: [{
									name: 'Negative',
									data: NegativeCountArray
								},{
									name: 'Positive',
									data: PositiveCountArray
								}],
								
							});
						});
					}else{
						$("#newsBlockGenSecStrong"+i).html("No Data Available");
						$("#newsBlockGenSecStrong"+i).css("height","35px");
							
					} 
					
				}
				
			}else{
				$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html("No Data Available");
			}
		}
	}
	
	$(document).on("click",".newsliCls",function(){
		getUserTypeWiseNewsCounts($(this).attr("attr_value"));
	});
	// Top Five Positive And Negative Block Build End
	
	
	//Detailed Block Ajax Calls
	//For Party Ajax call Start
	function getDetailedPartyMainEditionsOverview(){//not using 
		$("#mainEditiongraphId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
		}).then(function(result){
			$("#mainEditiongraphId").html('');
			buildMainEditionPartieWiseGraph(result);
		});
	}
	function getDetailedPartyDistrictEditionsOverview(){
			$("#districtWiseNewsReport").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			$("#districtWiseNewsReport").html();
			buildDetailedPartyDistrictEditionsOverview(result);
		});
	}
	function getDetailedPartyNewsTypeAnalysis(){
		$("#newsTypeAnalysisDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			$("#newsTypeAnalysisDiv").html();
			if(result != null && result.length > 0){
				buildDetailedPartyNewsTypeAnalysis(result,temp);
			}
		});
	}
	
	function getDetailedPartyPartyVsPublications(searchType){
		$("#partyWiseDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		/* if(searchType == "party"){
			$("#partyWiseDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}else{
			$("#publicationWiseDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		} */
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		//var searchType = "publication";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			
			
			if(result != null && result.length > 0 && searchType == "party"){
				//$("#partyWiseDetailsDiv").html();
				buildgetDetailedPartyWiseDetailes(result);
			}else{
				//$("#publicationWiseDetailsDiv").html();
				buildgetDetailedPublicationsWiseDetails(result);
			}
		});
	}
	//For Party Ajax call End
	
	//For Government Ajax Call Start
	function getDetailedGovtDepartmentWiseDistrictsOverview(){
		$("#districtWiseNewsReportGovtDetailed").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovtDepartmentWiseDistrictsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovtDepartmentWiseDistrictsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			$("#districtWiseNewsReportGovtDetailed").html('');
			buildgetDetailedGovtDepartmentWiseDistrictsOverview(result,temp);
		});
	}
	function getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(propertyId,propertyName){
		if(propertyId == 7){
			$("#problemsDetailedOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}else if(propertyId == 0){
				$("#overAllAnalysisDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}
		

		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var npIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				npIdsStr=i==0?newsPaperIdsGlob[i]:npIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		
		//var propertyId=0;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyId+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyId+"/"+impactScopeIdsStr
		}).then(function(result){
			if(propertyId == 7){
				$("#problemsDetailedOverview").html('');
			}else if(propertyId == 0){
				$("#overAllAnalysisDetailsBlock").html('');
			}
			buildgetDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(result,propertyId,propertyName);
			
		});
	}
	function getDetailedGovernamentTrendingTrackedIssues(){
		$("#topTrendingTracked").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovernamentTrendingTrackedIssues/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovernamentTrendingTrackedIssues/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			buildDetailedGovernamentTrendingTrackedIssues(result);
		});
	}
	function getDetailedGovernmentDistrictWiseArticleRelatedToProblem(){
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var state = globalState;
		var startDate=currentFromDate,endDate=currentToDate;
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		if(globalUserAccessLevelId==2){
			$("#districtWiseArticleRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
			$.ajax({
				url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other/"+impactScopeIdsStr
				//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other/"+impactScopeIdsStr      
			}).then(function(result){
				buildDistrictWiseArticleRelatedToProblem(result)
			});
			
			$("#stateWiseArticleRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
			$.ajax({
				url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/state/"+impactScopeIdsStr 
				//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/state/"+impactScopeIdsStr      
			}).then(function(result){
				buildStateWiseArticleRelatedToProblem(result);
			});
			
		}else{     
			$("#districtWiseArticleRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
			$.ajax({
				url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other/"+impactScopeIdsStr 
				//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other/"+impactScopeIdsStr    
			}).then(function(result){
				buildDistrictWiseArticleRelatedToProblem(result);
			});
			
		}
	}
	//For Government Ajax Call End
	
	//Comaprision Block Ajax Call Details
	// Party Ajax Call Start
	function getChildUserTypesByItsParentUserType1(){
		var jsObj = { parentUserTypeId : globalUserTypeId }
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){	
			buildChildUserTypesByItsParentUserType(result);
		});			 
	}
	function getPartyComparisonChildUserTypeMembers(childUserTypeId,childUserType){
		$("#partyWiseComparisionBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var childUserTypeIdArr = [];
		var temp = childUserTypeId.split(",");
		for(var i in temp){
			childUserTypeIdArr.push(temp[i]);
		}
		
		var jsObj={
				parentActivityMemberId : globalActivityMemberId ,
				childUserTypeIdArr : childUserTypeIdArr,
				state:globalState,
				startDate:currentFromDate,
				endDate:currentToDate,
				npIdsArr:newsPaperIdsGlob,
				impactScopeIdsArr:impactScopeIdsGlob
			}
		
			$.ajax({
				type : 'POST',
				url : 'getPartyComparisonChildUserTypeMembersAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#partyWiseComparisionBlock").html('');
				buildgetPartyComparisonChildUserTypeMembers(result,childUserTypeId,childUserType);
			});
		
	}
	function getPartyCompareSubLevelMemberDetails(NewsActivityMemberId,NewsUserTypeId,NewsSelectedMemberName,NewsSelectedUserType,NewsChildActivityMemberId){
		$("#"+NewsChildActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
				activityMemberId : NewsActivityMemberId,
				userTypeId : NewsUserTypeId,
				state:globalState,
				startDate:currentFromDate,
				endDate:currentToDate,
				npIdsArr : newsPaperIdsGlob,
				impactScopeIdsArr: impactScopeIdsGlob
				
			}
		
			$.ajax({
				type : 'POST',
				url : 'getPartyCompareSubLevelMemberDetailsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildgetPartyCompareSubLevelMemberDetails(result,NewsSelectedMemberName,NewsSelectedUserType,NewsChildActivityMemberId);
			});
	}
	
	function getComparisionPartyNewsTypeAnalysis(firstLocationLevelId,temp22,NewsSelectedMemberName,NewsSelectedUserType){//Teja1
	
		$("#PartyComparisionNewsTypeAnalysisDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}    
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getComparisionPartyNewsTypeAnalysis/"+firstLocationLevelId+"/"+temp22+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisionPartyNewsTypeAnalysis/"+firstLocationLevelId+"/"+temp22+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			if(result != null && result.length > 0){
				buildgetComparisionPartyNewsTypeAnalysis(result,NewsSelectedMemberName,NewsSelectedUserType);
			}
		});
	}
	function getComparisionPartyDistrictEditionsOverview(firstLocationLevelId,temp22,NewsSelectedMemberName,NewsSelectedUserType){//Teja
		$("#ComparisionPartyDistrictWiseNewsReport").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getComparisionPartyDistrictEditionsOverview/"+firstLocationLevelId+"/"+temp22+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisionPartyDistrictEditionsOverview/"+firstLocationLevelId+"/"+temp22+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			
			buildgetComparisionPartyDistrictEditionsOverview(result,NewsSelectedMemberName,NewsSelectedUserType);
		});
	}
	
	function getComparisionPartyPartyVsPublications(searchType,firstLocationLevelId,temp22,NewsSelectedMemberName,NewsSelectedUserType){
		$("#CompaisionPartyAndPublicationWiseDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
			var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		//var searchType = "publication";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getComparisionPartyPartyVsPublications/"+firstLocationLevelId+"/"+temp22+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisionPartyPartyVsPublications/"+firstLocationLevelId+"/"+temp22+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			var str='';
			
			//str+='<div class="col-md-12 col-xs-12 col-sm-12" >';
			str+='<div class="pull-right">';
				str+='<ul class="list-inline  activeUlCls" id="comparePartyUl">';
					str+='<li class="ComPartyDistrictWiseDiv" attr_search_type="party" attr_location_level_id ="'+firstLocationLevelId+'" attr_locationvalueset_idsStr ="'+temp22+'" attr_newsselectedmembername = "'+NewsSelectedMemberName+'" attr_newsselectedusertype = "'+NewsSelectedUserType+'">Parties district wise</li>';
					str+='<li class="ComPartyDistrictWiseDiv" style="margin-left:6px" attr_search_type="publication" attr_location_level_id ="'+firstLocationLevelId+'" attr_locationvalueset_idsStr ="'+temp22+'" attr_newsselectedmembername = "'+NewsSelectedMemberName+'" attr_newsselectedusertype = "'+NewsSelectedUserType+'">Publication Wise</li>';
				str+='</ul>';
			str+='</div>';
			//str+='</div>';
			//$("#partyAndPublication").html(str);
			$("#partiesWiseSelection").html(str);
			if(searchType == "party")
			{
				$("#comparePartyUl li:nth-child(1)").addClass("active")
				$("#comparePartyUl li:nth-child(2)").removeClass("active")
			}else{
				$("#comparePartyUl li:nth-child(2)").addClass("active")
				$("#comparePartyUl li:nth-child(1)").removeClass("active")
			}
			
			if(result != null && result.length > 0 && searchType == "party"){
				buildgetComparisionPartyWiseDetailes(result,NewsSelectedMemberName,NewsSelectedUserType);
			}else{
				buildgetComparisionPublicationsWiseDetails(result,NewsSelectedMemberName,NewsSelectedUserType);
			}
		});
	}
	 $(document).on("click",".ComPartyDistrictWiseDiv",function(){
		 var searchType = $(this).attr("attr_search_type");
		 
		 if(searchType == "party"){
			
			 var locationLevelId = $(this).attr("attr_location_level_id");
			 var locationvaluesIds = $(this).attr("attr_locationvalueset_idsStr");
			 var NewsSelectedMemberName = $(this).attr("attr_newsselectedmembername");
			 var NewsSelectedUserType = $(this).attr("attr_newsselectedusertype");
			 getComparisionPartyPartyVsPublications(searchType,locationLevelId,locationvaluesIds,NewsSelectedMemberName,NewsSelectedUserType);
		 }else{
			 var locationLevelId = $(this).attr("attr_location_level_id");
			 var locationvaluesIds = $(this).attr("attr_locationvalueset_idsStr");
			  var NewsSelectedMemberName = $(this).attr("attr_newsselectedmembername");
			 var NewsSelectedUserType = $(this).attr("attr_newsselectedusertype");
			 getComparisionPartyPartyVsPublications(searchType,locationLevelId,locationvaluesIds,NewsSelectedMemberName,NewsSelectedUserType);
		 }
		 
		
	}); 
	// Party Ajax Call End
	
	//Government Ajax Call Start
	
	function getComparisonGovtMinistriesInfo(){
		$("#comparisonGovtMinistriesInfo").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var type;
		$(".radioTypeCls").each(function(){
			if($(this).is(":checked")){
				type = $(this).val();
			}
		});
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var state = globalState;
		var startDate=currentFromDate,endDate=currentToDate;
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getComparisonGovtMinistriesInfo/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr+"/"+type
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonGovtMinistriesInfo/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr+"/"+type
		}).then(function(result){
			//$("#comparisonGovtMinistriesInfo").html('');
			buildComparisonGovtMinistriesInfo(result);
		});
	}
	function getAllDepartmentEditionsWiseDetails(departmentIdsStr,ministerName,ministerSubLevelId){
		$("#"+ministerSubLevelId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var state = globalState;
		var startDate=currentFromDate,endDate=currentToDate;
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getAllDepartmentEditionsWiseDetails/"+startDate+"/"+endDate+"/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+newsPaperIdsStr+"/"+departmentIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getAllDepartmentEditionsWiseDetails/"+startDate+"/"+endDate+"/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+newsPaperIdsStr+"/"+departmentIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			buildgetAllDepartmentEditionsWiseDetails(result,ministerName,ministerSubLevelId);
		});
	}
	function getComparisonGovernamentTrendingTrackedIssues(departmentIdsStr){
		$("#comparisonGovtTopTrend").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getComparisonGovernamentTrendingTrackedIssues/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+departmentIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonGovernamentTrendingTrackedIssues/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+departmentIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			buildComparisonGovernamentTrendingTrackedIssues(result);
		});
	}
	
	function getCompareGovtCandidateDepartmentsWiseDistrictOverview(orgIdStr)
	{
		$("#comparisonGovtDeptDOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getCompareGovtCandidateDepartmentsWiseDistrictOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+orgIdStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getCompareGovtCandidateDepartmentsWiseDistrictOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+orgIdStr+"/"+impactScopeIdsStr
		}).then(function(result){
			buildCompareGovtCandidateDepartmentsWiseDistrictOverview(result);
		});
	}
	
	function getCompareGovernamentDistrictWiseArticleRelatedToProblem(organizationIdStr){
		$("#compareDistrictWiseArticleRelatedToProblem").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var state = globalState;
		var startDate=currentFromDate,endDate=currentToDate;
		if(globalUserAccessLevelId==2){
			$("#compareDistrictWiseArticleRelatedToProblem").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			$.ajax({
				url: wurl+"/CommunityNewsPortal/webservice/getCompareGovernamentDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other/"+organizationIdStr+"/"+impactScopeIdsStr
				//url: "http://localhost:8080/CommunityNewsPortal/webservice/getCompareGovernamentDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other/"+organizationIdStr+"/"+impactScopeIdsStr  
			}).then(function(result){
				buildCompareDistrictWiseArticleRelatedToProblem(result)
			});
			$("#compareStateWiseArticleRelatedToProblem").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			$.ajax({
				url: wurl+"/CommunityNewsPortal/webservice/getCompareGovernamentDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/state/"+organizationIdStr+"/"+impactScopeIdsStr
				//url: "http://localhost:8080/CommunityNewsPortal/webservice/getCompareGovernamentDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/state/"+organizationIdStr+"/"+impactScopeIdsStr    
			}).then(function(result){
				buildCompareStateWiseArticleRelatedToProblem(result)
			});
			
		}else{   
			$("#compareDistrictWiseArticleRelatedToProblem").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');		
			$.ajax({
				url: wurl+"/CommunityNewsPortal/webservice/getCompareGovernamentDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other/"+organizationIdStr+"/"+impactScopeIdsStr 
				//url: "http://localhost:8080/CommunityNewsPortal/webservice/getCompareGovernamentDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other/"+organizationIdStr+"/"+impactScopeIdsStr    
			}).then(function(result){
				buildCompareDistrictWiseArticleRelatedToProblem(result);
			});
			
		}
		
	}
	
	function getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems(propertyId,deptIdsStr,propertyName){
		if(propertyId == 7){
			$("#comparisonGovtProblemsDistrictLevelOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}else if(propertyId == 0){
			$("#comparisonGovtProblemsOverAllAnalysis").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}
		

		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var npIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				npIdsStr=i==0?newsPaperIdsGlob[i]:npIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		
		//var propertyId=0;
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyId+"/"+deptIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyId+"/"+deptIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			if(propertyId == 7){
				$("#comparisonGovtProblemsDistrictLevelOverview").html('');
			}else if(propertyId == 0){
				$("#comparisonGovtProblemsOverAllAnalysis").html('');
			}
			buildComparisionGovtOverAllAnalysisOfActionImmediatelyProblems(result,propertyId,deptIdsStr,propertyName);
			
		});
	}
	//Government Ajax Call End
	
	//Detailed Block Building Blocks
	//party Wise Building Blocks
	function getProperName(levelName){
		var properName = "";
		if(levelName == "<10L"){
			properName = "below 10 Lakhs ";
		}else if(levelName == ">10L"){
			properName = "Above 10 Lakhs";
		}else{
			properName = levelName;
		}
		return properName;
	}
	
	function buildMainEditionPartieWiseGraph(result){
		if(result != null && result.length > 0){
			var str='';
			str+='<ul class="newsPartyWiseUI">';
			str+='<li>';
			str+='<div class="">';
			str+='<ul class="list-inline ">';
			var countVar =0;
			for(var i in result){
				countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
					str+='<li><div id="mainEditiongraph'+i+'" class="chartLi"></div></li>';
		    }
			str+='</ul>';
			str+='</div>';
			str+='</li>';
			str+='</ul>';
				
		}
		$("#mainEditiongraphId").html(str);
	  
	  if(result != null && result.length > 0){
		  var countVar =0;
		 
		 
			for(var i in result){
				countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				 var positiveCountArray =[];
				var negativeCountArray =[];
				var organizationName = result[i].organization;
				positiveCountArray.push(result[i].positivePerc);
				negativeCountArray.push(result[i].negativePerc);
			if( positiveCountArray.length !=0 && negativeCountArray.length !=0){
				$(function () {
					$('#mainEditiongraph'+i+'').highcharts({
						 colors: ['#64C664','#D33E39'],
						chart: {
							type: 'column',
							
						},
						title: {
							 useHTML: true,
							text: '<img src="newCoreDashBoard/img/'+organizationName+'.png" style="width:25px;" alt="tdp icon"/> &nbsp;&nbsp;&nbsp;'+organizationName+'',
							style: {
									fontSize: '16px',
									fontFamily: '"Helvetica Neue",Helvetica,Arial,sans-serif',
									textTransform: "uppercase"
									
							}
						},
						subtitle: {
							text: null
						},
						 xAxis: {
							 min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
							categories: null,
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
							stackLabels: {
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
								}
							}
						},
						tooltip: {
							headerFormat: '<b>{point.x}</b><br/>',
							pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b><br/>',
							
						},
						legend: {
							enabled: true,
							align: 'left'
						
						},
						plotOptions: {
							column: {
								dataLabels:{
									enabled: true,
									formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.y,1) + '%';
										}
									}
								},
								
							},
						},
						 series: [{
							name: 'Positive',
							data: positiveCountArray 
						}, {
							name: 'Negative',
							data: negativeCountArray
						}]
					});
				});	
			}else{
				$('#mainEditiongraph'+i+'').html("<b>"+organizationName+"</b> (<span style='text-align:center'>No Data Available</span>)");
			}
			
		}
	  }else{
		  $("#mainEditiongraphId").html("No Data Available");
	  }
	}
	
	function buildDetailedPartyDistrictEditionsOverview(result){
		$("#districtWiseNewsReport").html(' ');
		if(result != null && result.length > 0){
			var str='';
			var countVar =0;
			for(var i in result){
				countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
					 
				str+='<img src="newCoreDashBoard/img/'+result[i].organization+'.png" style="width:25px;" alt="tdp icon" class=" m_top10"/> &nbsp;&nbsp;&nbsp;'+result[i].organization+'';
				str+='<div id="districtWiseNews'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
									
		}
		$("#districtWiseNewsReport").html(str);
		/* $("#districtWiseNewsReport").each(function(){
			var scrollengthDiv = $(this).find(".chartLiD").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBar").mCustomScrollbar({setHeight:'560px'})
				
			}else{
				$(".verticalScrollBar").css("height","auto");
			
			}
		}); */
		
		
	if(result != null && result.length > 0){
		var countVar =0;
		for(var i in result){
			countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
			var districtNamesArray =[];
			var districtWisePositiveCountArray = [];
			var districtWiseNegativeCountArray = [];
			
			if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length > 0){
				
				for(var j in result[i].coreDashBoardVOList){
					
						districtNamesArray.push(result[i].coreDashBoardVOList[j].districtName);
						districtWisePositiveCountArray.push({"y":result[i].coreDashBoardVOList[j].positiveCountMain,"extra":result[i].coreDashBoardVOList[j].id+"-1"+"-"+result[i].organizationId});
						districtWiseNegativeCountArray.push({"y":result[i].coreDashBoardVOList[j].negativCountMain,"extra":result[i].coreDashBoardVOList[j].id+"-2"+"-"+result[i].organizationId});
						
					}
			}	
					if(districtWisePositiveCountArray.length !=0 && districtWiseNegativeCountArray.length !=0){
						$(function () {
							$('#districtWiseNews'+i+'').highcharts({
								 colors: ['#64C664','#D33E39'],
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
										categories: districtNamesArray,
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
										text: ''
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
									/* //align: 'right',
									x: -40,
									y: 30,
									verticalAlign: 'top',
									//y: -32,
									floating: true, */
									backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
									borderColor: '#CCC',
									borderWidth: 1,
									shadow: false
								},
								tooltip: {
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
													return Highcharts.numberFormat(this.percentage,1) +'%';
												}
											}
										  
										}
									},
									series: {
										cursor: 'pointer',
										point: {
											events: {
												click: function () {
													getArticlesForPartyDetailedDistEdiPartiesOverView(this.extra);
													//alert(this.extra);
												}
											}
										}
									}
								},
								series: [{
									name: 'Positive',
									data: districtWisePositiveCountArray
								}, {
									name: 'Negative',
									data: districtWiseNegativeCountArray
								}]
							});
						});
					}else{
						$('#districtWiseNews'+i+'').html("No Data Available");
						$('#districtWiseNews'+i+'').css("height","10px");
					}
			}
		}else{
			$("#districtWiseNewsReport").html("No Data Available")
		}	
	}
	
	function getArticlesForPartyDetailedDistEdiPartiesOverView(val){
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		} 
		
		var t = val.split("-");
		
		window.open('showArticlesAction.action?levelId='+globalUserAccessLevelId+'&temp='+temp+'&state='+globalState+'&sdat='+currentFromDate+'&edat='+currentToDate+'&bfIdStr='+t[1]+'&scops='+impactScopeIdsStr+'&orgIdStr='+t[2]+'&orgType=N&edTypeIdStr=0&npsStr='+newsPaperIdsStr+'&ediDistIdsStr='+t[0]+'&callFrom=dpdepok&stIdx=0&edIdx=6','_blank');//san
		
	}
	
	function buildDetailedPartyNewsTypeAnalysis(result,temp){
		var str='';
		if(result != null && result.length > 0){
			var str='';
				
			for(var i in result){
				
				if(result[i].coreDashBoardVOList != null && result[i].coreDashBoardVOList.length > 0 && result[i].coreDashBoardVOList1 != null && result[i].coreDashBoardVOList1.length > 0){
					str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-ms-12"><h5 class="text-capital">'+result[i].name+'</h5></div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-4" style="border-right:1px solid #ddd">';
						str+='<div id="newsTypeAnalysisPieChart'+i+'" style="height:165px;width:100%;"></div>';
						str+='<div class="row">';
							str+='<div class="col-xs-12 col-sm-12 col-md-12">';
								str+='<ul class="list-inline">';
								for(var j in result[i].coreDashBoardVOList){
									var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
									  str+='<li style="color:'+color+'" class="newsTypeAnalysisDetailsDiv cursorPo" attr_orgIdStr="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_propIdsStr = "'+result[i].id+'">'+result[i].coreDashBoardVOList[j].organization+' :'+result[i].coreDashBoardVOList[j].positivePerc+'%('+result[i].coreDashBoardVOList[j].count+')</li> &nbsp;&nbsp;';
									
								}
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-md-10 col-xs-12 col-sm-8">';
						str+='<div id="newsTypeAnalysisBarChart'+i+'" class="chartLiD" style="height:200px"></div>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-ms-12"><hr/></div>';
					str+='</div>';
				}
			}
			
		}
		$("#newsTypeAnalysisDiv").html(str);
		
			if(result != null && result.length > 0){
				for(var i in result){
					var PartyCountPerc;
					var partyName;
					var partyNameAndCountArray =[];
					var districtNameArray =[];
					var tdpPercArray = [];
					var ysrcPercArray =[];
					var incPercArray = [];
					var bjpPercArray = [];
					
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0 && result[i].coreDashBoardVOList1 !=null && result[i].coreDashBoardVOList1.length >0){
						for (var j in result[i].coreDashBoardVOList){
							 PartyCountPerc = result[i].coreDashBoardVOList[j].count;
							 partyName = result[i].coreDashBoardVOList[j].organization;
							 var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
							
							var obj = {
								name: partyName,
								y:PartyCountPerc,
								color:color
								
							}
							
							partyNameAndCountArray.push(obj);
						}
					}
				
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0 && result[i].coreDashBoardVOList1 !=null && result[i].coreDashBoardVOList1.length >0){
						for (var j in result[i].coreDashBoardVOList1){
							districtNameArray.push(result[i].coreDashBoardVOList1[j].districtName)
							
							if(globalUserAccessLevelId == 2 && j > 0){
								tdpPercArray.push({"y":result[i].coreDashBoardVOList1[j].tdpCount,"extra":"3-"+result[i].coreDashBoardVOList1[j].id+"-872"+"-"+result[i].id+""});
								ysrcPercArray.push({"y":result[i].coreDashBoardVOList1[j].ysrcCount,"extra":"3-"+result[i].coreDashBoardVOList1[j].id+"-1117"+"-"+result[i].id+""});
								incPercArray.push({"y":result[i].coreDashBoardVOList1[j].incCount,"extra":"3-"+result[i].coreDashBoardVOList1[j].id+"-362"+"-"+result[i].id+""});
								bjpPercArray.push({"y":result[i].coreDashBoardVOList1[j].bjpCount,"extra":"3-"+result[i].coreDashBoardVOList1[j].id+"-163"+"-"+result[i].id+""});
							}else{
								tdpPercArray.push({"y":result[i].coreDashBoardVOList1[j].tdpCount,"extra":globalUserAccessLevelId+"-"+temp+"-872"+"-"+result[i].id+""});
								ysrcPercArray.push({"y":result[i].coreDashBoardVOList1[j].ysrcCount,"extra":globalUserAccessLevelId+"-"+temp+"-1117"+"-"+result[i].id+""});
								incPercArray.push({"y":result[i].coreDashBoardVOList1[j].incCount,"extra":globalUserAccessLevelId+"-"+temp+"-362"+"-"+result[i].id+""});
								bjpPercArray.push({"y":result[i].coreDashBoardVOList1[j].bjpCount,"extra":globalUserAccessLevelId+"-"+temp+"-163"+"-"+result[i].id+""});
							}
						}
					}
						
					$(function () {
						if(partyNameAndCountArray.length !=0){
							$('#newsTypeAnalysisPieChart'+i).highcharts({
								//colors: ['#FD9832','#3D9834','#FFCB00','#005DB0'],
								chart: {
									type: 'pie',
									options3d: {
										enabled: true,
										alpha: 25
									}
								},
								title: {
									text: null
								},
								subtitle: {
									text: null
								},
								 tooltip: {
									enabled: false,
									shared: true
								}, 
								plotOptions: {
									pie: {
										innerSize: 65,
										depth: 10,
										dataLabels:{
											enabled: false,
											  formatter: function() {
													if (this.y === 0) {
														return null;
													} else {
														return Highcharts.numberFormat(this.percentage,1)+ '%';
													}
												} 
										},
										showInLegend: false
									},
									
									
								},
								series: [{
									data: partyNameAndCountArray
									
								}]
							});
						}else{
							$('#newsTypeAnalysisPieChart'+i).html("No Data Available")
							$('#newsTypeAnalysisPieChart'+i).css("height","10px")
						}
						
					});
					
					$(function () {
						if(districtNameArray.length != 0 && tdpPercArray.length !=0 && ysrcPercArray.length !=0 && incPercArray.length !=0 && bjpPercArray.length !=0){
						$('#newsTypeAnalysisBarChart'+i).highcharts({
							colors: ['#FFCB00','#005DB0','#3D9834','#FD9832'],
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
									categories: districtNameArray,
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
									text: ''
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
								//align: 'center',
								//x: -40,
								//y: 23,
								//verticalAlign: 'top',
								//floating: true, 
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false,
								
							},
							tooltip: {
									formatter: function () {
										var s = '<b>' + this.x + '</b>';

										$.each(this.points, function () {
											s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
												Highcharts.numberFormat(this.percentage,2)+'%' +' - ' +
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
										enabled: false,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return Highcharts.numberFormat(this.percentage,2)+'%';
											}
										}
									  
									}
								},
								series: {
										cursor: 'pointer',
										point: {
											events: {
												click: function () {
													getArticlesForDetailedPartyNewsTypeAnalysisOverView(this.extra);
													
												}
											}
										}
									}
							},
							series: [{
								name: 'TDP',
								data: tdpPercArray
							}, {
								name: 'YSRC',
								data: ysrcPercArray
							},{
								name: 'INC',
								data: incPercArray
							},{
								name: 'BJP',
								data: bjpPercArray
							}]
						});
					}else{
						$('#newsTypeAnalysisBarChart'+i).html("No Data Available");
						$('#newsTypeAnalysisBarChart'+i).css("height","10px")
					}
					});
				}
			}
	}
	
	function getArticlesForDetailedPartyNewsTypeAnalysisOverView(val){
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		} 
		
	
		
		var t = val.split("-");
		window.open('showArticlesAction.action?levelId='+t[0]+'&temp='+t[1]+'&state='+globalState+'&sdat='+currentFromDate+'&edat='+currentToDate+'&scops='+impactScopeIdsStr+'&orgIdStr='+t[2]+'&orgType=N&npsStr='+newsPaperIdsStr+'&propIdsStr='+t[3]+'&callFrom=dpnta&stIdx=0&edIdx=6','_blank');//san
		
		
	}
	
	$(document).on("click",".newsTypeAnalysisDetailsDiv",function(){
		
		 var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		} 
		
		
		window.open('showArticlesAction.action?levelId='+globalUserAccessLevelId+'&temp='+temp+'&state='+globalState+'&sdat='+currentFromDate+'&edat='+currentToDate+'&scops='+impactScopeIdsStr+'&orgIdStr='+$(this).attr("attr_orgIdStr")+'&orgType=N&npsStr='+newsPaperIdsStr+'&propIdsStr='+$(this).attr("attr_propIdsStr")+'&callFrom=dpnta&stIdx=0&edIdx=6','_blank');//san
		
	});
	
	
	function buildgetDetailedPartyWiseDetailes(result){
		$("#partyWiseDetailsDiv").html();
		var str ='';
		if(result !=null && result.length >0){
			for(var i in result){
				var partyname;
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						str+='<div class="col-md-12 col-xs-12 col-ms-12">';
							str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
						for(var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for(var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									partyname = result[i].coreDashBoardVOList[j].coreDashBoardVOList[0].organization
								}
							}
						}
						str+='<h4  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;"><img src="newCoreDashBoard/img/'+partyname+'.png" class="debatesPartyIcon" />'+partyname+'</h4>';
							str+='</div>';
							str+='<div class="col-xs-11 col-sm-10 col-md-11">';
								str+='<ul class="partyWiseSlickApply">';
						for(var j in result[i].coreDashBoardVOList){
							str+='<li><div id="partywisegraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
						}
							str+='</ul>';
						str+='</div>';
					str+='</div>';	
					}
					
					
			}
			
		}
		
		
		$("#partyWiseDetailsDiv").html(str);
			if(result !=null && result.length >0){
				for(var i in result){
					
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						for(var j in result[i].coreDashBoardVOList){
							
							var paperNamesArray =[];
							var positivePercArray =[];
							var negativePercArray =[];
							var districtName;
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								
								for(var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									districtName = result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].districtName;
									paperNamesArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].name)
									positivePercArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].positiveCountDist)
									negativePercArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountDist)
									
									
								}
							}
							if(paperNamesArray.length !=0 && positivePercArray.length !=0 &&  negativePercArray.length !=0 &&  negativePercArray.length !=0 && districtName !=0 && districtName !=null){
								$(function () {
									$('#partywisegraph'+i+''+j+'').highcharts({
										 colors: ['#64C664','#D33E39'],
										chart: {
											type: 'column'
										},
										title: {
											text: districtName
										},
									   
										xAxis: {
											 min: 0,
												 gridLineWidth: 0,
												 minorGridLineWidth: 0,
												 categories: paperNamesArray,
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
											data: positivePercArray
										}, {
											name: 'Negative',
											data: negativePercArray
										}]
									});
								});
							}else{
								$('#partywisegraph'+i+''+j+'').html("No Data Available");
								$('#partywisegraph'+i+''+j+'').css("height","20px");
							}
							
						}
						
					}
					
					
					
				}
			}
				
			else{
				$("#partyWiseDetailsDiv").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
			
		$(".partyWiseSlickApply").slick({
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
	
	function buildgetDetailedPublicationsWiseDetails(result){
		$("#partyWiseDetailsDiv").html();
		var str ='';
		if(result !=null && result.length >0){
			for(var i in result){
				var papername;
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						str+='<div class="col-md-12 col-xs-12 col-ms-12">';
							str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
						for(var j in result[i].coreDashBoardVOList){
							papername = result[i].coreDashBoardVOList[0].name;
						}
						str+='<h5  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;"><img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+papername+'.png" style="width:60px;" alt="tdp icon"/></h5>';
							str+='</div>';
							str+='<div class="col-xs-11 col-sm-10 col-md-11">';
								str+='<ul class="publicationWiseSlickApply">';
						for(var j in result[i].coreDashBoardVOList){
							str+='<li><div id="publicationwisegraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
						}
							
						
							str+='</ul>';
						str+='</div>';
					str+='</div>';
					}
					
					
					
			}
			
		}
		
		
		//$("#publicationWiseDetailsDiv").html(str);
		$("#partyWiseDetailsDiv").html(str);
		if(result !=null && result.length >0){
				for(var i in result){
					
							
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						for(var j in result[i].coreDashBoardVOList){
							var partyName = [];
							var positivePercArray =[];
							var negativePercArray =[];
							
									partyName.push(result[i].coreDashBoardVOList[j].organization);
									
									positivePercArray.push({"y":result[i].coreDashBoardVOList[j].positivePerc,"count":result[i].coreDashBoardVOList[j].positiveCountDist})
									negativePercArray.push({"y":result[i].coreDashBoardVOList[j].negativePerc,"count":result[i].coreDashBoardVOList[j].negativCountDist})
						
							if(partyName.length !=0 && positivePercArray.length !=0 && negativePercArray.length !=0){
								$(function () {
									$('#publicationwisegraph'+i+''+j+'').highcharts({
										 colors: ['#64C664','#D33E39'],
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
												 categories: partyName,
											labels: {
													//rotation: -45,
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
											pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}% - {point.count}</b> <br/>',
											shared: true
										},
										legend: {
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												//stacking: 'percent',
												dataLabels:{
													enabled: true,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.y,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											name: 'Positive',
											data: positivePercArray
										}, {
											name: 'Negative',
											data: negativePercArray
										}]
									});
								});
							}else{
								$('#publicationwisegraph'+i+''+j+'').html("No Data Available");
								$('#publicationwisegraph'+i+''+j+'').css("height","20px");
							}
							
							
						}
					}
						
				}
			}
				
			else{
				$("#partyWiseDetailsDiv").html("No Data Available");
			}
			$(".publicationWiseSlickApply").slick({
					 slide: 'li',
					 slidesToShow: 4,
					 slidesToScroll: 3,
					 infinite: false,
					 swipeToSlide:false,
					 swipe:false,
					 touchMove:false
				}); 
		
	}
	
	//party Building Blocks End
	
	//Government Building Blocks Start
	
	function buildgetDetailedGovtDepartmentWiseDistrictsOverview(result,temp){
		$("#districtWiseNewsReportGovtDetailed").html(' ');
		if(result != null && result.length > 0){
			var str='';
			
			for(var i in result){
				var departmentName;
				if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length > 0){
					for(var j in result[i].coreDashBoardVOList){
						departmentName = result[i].coreDashBoardVOList[j].organization
					}
				}
				if(result[i].name !=null && $.trim(result[i].name.length) > 0){
					str+='<span style="font-size:15px;">'+departmentName+' - <small><i>'+result[i].name+'</i></small></span>';
				}else{
					str+='<span style="font-size:15px;">'+departmentName+'</span>';
				}
				str+='<p>Total - <i>'+result[i].count+'</i> : Positive Perc - <span title="'+result[i].positiveCountDist+'" style="color:#7DDF7D" data-toggle="tooltip" data-placement="top" ><i>'+result[i].positivePerc+' %</i></span> : Negative Prec - <span title="'+result[i].negativCountDist+'"  style="color:#EC5752" data-toggle="tooltip" data-placement="top"><i>'+result[i].negativePerc+' %</i></span></p>';
				str+='<div id="districtWiseNewsGovtDetailed'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
									
		}
		$("#districtWiseNewsReportGovtDetailed").html(str);
		$('[data-toggle="tooltip"]').tooltip();
		
	if(result != null && result.length > 0){
		
		for(var i in result){
			
			var govtDetailedDistrictNamesArray =[];
			var govtDetailedDistrictWisePositiveCountArray = [];
			var govtDetailedDistrictWiseNegativeCountArray = [];
			
			if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length > 0){
				
				for(var j in result[i].coreDashBoardVOList){
					
							govtDetailedDistrictNamesArray.push(result[i].coreDashBoardVOList[j].districtName);
							  if(globalUserAccessLevelId == 2 && j > 0){
								govtDetailedDistrictWisePositiveCountArray.push({"y":result[i].coreDashBoardVOList[j].positiveCountDist,"extra":"3-"+result[i].coreDashBoardVOList[j].districtId+"-"+result[i].coreDashBoardVOList[j].organizationId+"-1"});
								
								govtDetailedDistrictWiseNegativeCountArray.push({"y":result[i].coreDashBoardVOList[j].negativCountDist,"extra":"3-"+result[i].coreDashBoardVOList[j].districtId+"-"+result[i].coreDashBoardVOList[j].organizationId+"-2"});
							  }else{
								govtDetailedDistrictWisePositiveCountArray.push({"y":result[i].coreDashBoardVOList[j].positiveCountDist,"extra":globalUserAccessLevelId+"-"+temp+"-"+result[i].coreDashBoardVOList[j].organizationId+"-1"});
								
								govtDetailedDistrictWiseNegativeCountArray.push({"y":result[i].coreDashBoardVOList[j].negativCountDist,"extra":globalUserAccessLevelId+"-"+temp+"-"+result[i].coreDashBoardVOList[j].organizationId+"-2"});
							  }
					}
			}	
					if(govtDetailedDistrictWisePositiveCountArray.length !=0 && govtDetailedDistrictWiseNegativeCountArray.length !=0 && govtDetailedDistrictNamesArray.length !=0){
						$(function () {
							$('#districtWiseNewsGovtDetailed'+i+'').highcharts({
								 colors: ['#64C664','#D33E39'],
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
										categories: govtDetailedDistrictNamesArray,
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
										text: ''
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
									/* //align: 'right',
									x: -40,
									y: 30,
									verticalAlign: 'top',
									//y: -32,
									floating: true, */
									backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
									borderColor: '#CCC',
									borderWidth: 1,
									shadow: false
								},
								tooltip: {
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
													return Highcharts.numberFormat(this.percentage,1) +'%';
												}
											}
										  
										},
									},
									series: {
										cursor: 'pointer',
										point: {
											events: {
												click: function () {
													getArticlesForgetDetailedGovtDepartmentWiseDistrictsOverview(this.extra);
												}
											}
										}
									}
								},
								series: [{
									name: 'Positive',
									data: govtDetailedDistrictWisePositiveCountArray
								}, {
									name: 'Negative',
									data: govtDetailedDistrictWiseNegativeCountArray
								}]
							});
						});
					}else{
						$('#districtWiseNewsGovtDetailed'+i+'').html("No Data Available");
						$('#districtWiseNewsGovtDetailed'+i+'').css("height","10px");
					}
			}
		}else{
			$("#districtWiseNewsReportGovtDetailed").html("No Data Available")
		}	
		
		$("#districtWiseNewsReportGovtDetailed").each(function(){
			var scrollengthDiv = $(this).find(".chartLiD").length;
			if(scrollengthDiv >= 2){
				$(".verticalScrollBarPM").mCustomScrollbar({setHeight:'560px'})
				
			}else{
				$(".verticalScrollBarPM").css("height","auto");
			
			}
		});
		
	}
	
	function getArticlesForgetDetailedGovtDepartmentWiseDistrictsOverview(val){
		
		/* var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		} */
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		} 
		
		var t = val.split("-");
		
		window.open('showArticlesAction.action?levelId='+t[0]+'&temp='+t[1]+'&state='+globalState+'&sdat='+currentFromDate+'&edat='+currentToDate+'&scops='+impactScopeIdsStr+'&orgIdStr='+t[2]+'&orgType=Y&npsStr='+newsPaperIdsStr+'&bfIdStr='+t[3]+'&callFrom=govdepwisedistoverview&stIdx=0&edIdx=6','_blank');//Ara
		
	}
	
	function buildgetDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(result,propertyId,propertyName){
		var locationLevelNameArray =[];
		
			if(globalUserAccessLevelId == 2){
				if(result != null && result.length > 0){
				var str='';
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
						str+='<h4 class="m_top20">DEPARTMENTS WISE</h4>';
							str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
							str+='<ul style="list-style:none;" class="textAlignDepartment dynamicHeightApply">';
							for(var i in result){
								if(result[i].name !=null && result[i].name.length>55){
									str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,55)+'...</span> <span class="pull-right">'+result[i].count+'</span></li>';
								}else{
									str+='<li >'+result[i].name+'  <span class="pull-right">'+result[i].count+'</span></li>';
								}
								
							}
							str+='</ul>';
						str+='</div>';
						str+='<div class="col-md-5 col-xs-12 col-sm-4">';
						str+='<div id="problemsRelatedGraphState" style="width:300px;"></div>';
						
						str+='</div>';
					str+='</div>';
				str+='</div>';
			
			}
		
			$("#problemsDetailedOverview1").html(str);
			$('[data-toggle="tooltip"]').tooltip();
			
			var dynamicHeight;
				$(".dynamicHeightApply").each(function(){
					dynamicHeight = $(this).find("li").length;
					dynamicHeight = (dynamicHeight*36)+"px";
				});
			$("#problemsRelatedGraphState").css("height",dynamicHeight);
			
			if(result != null && result.length > 0){
				
				var mainArray = [];
					for(var i in result){
					var problemDeptPostivePercAndNameArray = [];
						problemDeptPostivePercAndNameArray.push(result[i].name)
						problemDeptPostivePercAndNameArray.push(result[i].positivePerc)
						mainArray.push(problemDeptPostivePercAndNameArray)
					}	
					
				$(function () {
					$('#problemsRelatedGraphState').highcharts({
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
							pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.2f}%</b><br/>'
						},
						plotOptions: {
							bar: {
								dataLabels: {
									enabled: true,
									formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.y,2) + '%';
										}
									}
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
							 data: mainArray
						}]
					});
				});
			
			}
			}
			if(propertyId == 7){
				if(result != null && result.length > 0){
				var str='';
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
						str+='<h4 class="m_top20">DEPARTMENTS WISE</h4>';
							str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
							str+='<ul style="list-style:none;" class="textAlignDepartment dynamicHeightApply">';
							for(var i in result){
								if(result[i].name !=null && result[i].name.length>55){
									str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,55)+'...</span> <span class="pull-right">'+result[i].count+'</span></li>';
								}else{
									str+='<li>'+result[i].name+'  <span class="pull-right">'+result[i].count+'</span></li>';
								}
							}
							str+='</ul>';
						str+='</div>';
						str+='<div class="col-md-5 col-xs-12 col-sm-4">';
						str+='<div id="problemsRelatedGraph" style="width:300px;"></div>';
						
						str+='</div>';
					str+='</div>';
				str+='</div>';
			
			}
		
			$("#problemsDetailedOverview").html(str);
			$('[data-toggle="tooltip"]').tooltip();
			
			var dynamicHeight;
				$(".dynamicHeightApply").each(function(){
					dynamicHeight = $(this).find("li").length;
					dynamicHeight = (dynamicHeight*36)+"px";
						
				});
					
			$("#problemsRelatedGraph").css("height",dynamicHeight);
			 
			if(result != null && result.length > 0){
				var mainArray = [];
					for(var i in result){
					var problemDeptPostivePercAndNameArray = [];
						problemDeptPostivePercAndNameArray.push(result[i].name)
						problemDeptPostivePercAndNameArray.push(result[i].positivePerc)
						mainArray.push(problemDeptPostivePercAndNameArray)
					}	
				$(function () {
					$('#problemsRelatedGraph').highcharts({
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
							data: mainArray
						}]
					});
				});
			}
			
		}else if(propertyId == 0){
			
			if(result != null && result.length > 0){
					var str='';
					str+='<div class="row">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
							str+='<h4>OVERALL ANALYSIS OF ACTION IMMEDIATELY PROBLEMS</h4>';
								str+='<div class="col-md-7 col-xs-12 col-sm-6 m_top10">';
								str+='<ul style="list-style:none;" class="textAlignDepartment">';
								for(var i in result){
									var properName = getProperName(result[i].name);
									if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
										locationLevelNameArray.push(properName);
										str+='<li class="heightDyna" style="text-transform: uppercase;">'+properName+'  <span class="pull-right ovarAllAnalysisSubLevel" attr_propertyid ="'+result[i].id+'" attr_property_name="'+properName+'" style="cursor:pointer" data-toggle="tooltip" data-placement="top" title="Get Detailed View">'+result[i].count+'</span></li>';
									}
									
								}
								
								
								str+='</ul>';
							str+='</div>';
							str+='<div class="col-md-5 col-xs-12 col-sm-6">';
								str+='<div id="overAllAnalysisPieChart" style="width:300px;height:200px;"></div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				
			}
			
			$("#overAllAnalysisDetailsBlock").html(str);
			$('[data-toggle="tooltip"]').tooltip();
				 
			if(result != null && result.length > 0){
				
				var problemDeptPostivePercArray =[];
				for(var i in result){
					var problemRelatedPieChartArray =[];
					problemRelatedPieChartArray.push(result[i].name);
					problemRelatedPieChartArray.push(result[i].positivePerc);
					problemDeptPostivePercArray.push(problemRelatedPieChartArray);
				}
				
				$(function () {
						if(problemDeptPostivePercArray.length !=0){
							$('#overAllAnalysisPieChart').highcharts({
								colors: ['#E5D355','#8D4654','#F25C81','#8085E9'],
								chart: {
									type: 'pie',
									options3d: {
										enabled: true,
										alpha: 25
									}
								},
								title: {
									text: null
								},
								subtitle: {
									text: null
								},
								
								plotOptions: {
									pie: {
										innerSize: 90,
										depth: 10,
										
										showInLegend: false
									},
									
									
								},
								tooltip: {
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b><br/>',
									
								},
								series: [{
									name: '',
									 colorByPoint: true,
									data: problemDeptPostivePercArray,
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
							$('#overAllAnalysisPieChart').html("No Data Available")
							$('#overAllAnalysisPieChart').css("height","10px")
						}
						
					});
				}
			}else if(propertyId !=7){
				
					if(result != null && result.length > 0){
					var str='';
					str+='<div class="row">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
							str+='<h4 class="m_top20" style="text-transform:uppercase;">PROBLEM CAN BE SOLVED '+propertyName+'</h4>';
								str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
								str+='<ul style="list-style:none;" class="textAlignDepartment dynamicHeightApply2">';
								for(var i in result){
									if(result[i].name !=null && result[i].name.length>55){
										str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,55)+'...</span> <span class="pull-right">'+result[i].count+'</span></li>';
									}else{
										str+='<li>'+result[i].name+'  <span attr_propIdsStr="'+propertyId+'" attr_orgIdStr = "'+result[i].id+'" class="problemsRelatedDetailedGovtArticles pull-right cursorPo">'+result[i].count+'</span></li>';
									}
								}
								str+='</ul>';
							str+='</div>';
							str+='<div class="col-md-5 col-xs-12 col-sm-4">';
							str+='<div id="problemsRelatedGraphSubLevel" style="width:300px;"></div>';
							
							str+='</div>';
						str+='</div>';
					str+='</div>';
				
				}
			
				$("#problemsDetailedOverviewSubLevel").html(str);
				$('[data-toggle="tooltip"]').tooltip();
				
				var dynamicHeight;
					$(".dynamicHeightApply2").each(function(){
						dynamicHeight = $(this).find("li").length;
						dynamicHeight = (dynamicHeight*36)+"px";
							
					});
						
				$("#problemsRelatedGraphSubLevel").css("height",dynamicHeight);
				 
				if(result != null && result.length > 0){
					var mainArray = [];
						for(var i in result){
						var problemDeptPostivePercAndNameArray = [];
							problemDeptPostivePercAndNameArray.push(result[i].name)
							problemDeptPostivePercAndNameArray.push(result[i].positivePerc)
							mainArray.push(problemDeptPostivePercAndNameArray)
						}	
					$(function () {
						$('#problemsRelatedGraphSubLevel').highcharts({
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
								data: mainArray
							}]
						});
					});
				}
			
			}
		
	}
	
	$(document).on("click",".ovarAllAnalysisSubLevel",function(){
		//$("#problemsDetailedOverviewSubLevel").html('');
		$("#problemsDetailedOverviewSubLevel").show();
		$("#problemsDetailedOverviewSubLevel").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var propertyId = $(this).attr("attr_propertyid");
		var propertyName = $(this).attr("attr_property_name");
		getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(propertyId,propertyName);
		
	});
	
	$(document).on("click",".problemsRelatedDetailedGovtArticles",function(){
		
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		} 
		
		window.open('showArticlesAction.action?levelId='+globalUserAccessLevelId+'&temp='+temp+'&state='+globalState+'&sdat='+currentFromDate+'&edat='+currentToDate+'&scops='+impactScopeIdsStr+'&orgIdStr='+$(this).attr("attr_orgIdStr")+'&propIdsStr='+$(this).attr("attr_propIdsStr")+'&orgType=Y&npsStr='+newsPaperIdsStr+'&callFrom=detailgovtimmedproblems&stIdx=0&edIdx=6','_blank');//san
	
	});	
	function buildDetailedGovernamentTrendingTrackedIssues(result){
		$("#topTrendingTracked").html(' ');
		if(result != null && result.length > 0){
			var str='';
			var countVar =0;
			for(var i in result){
				countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				//var totalMainCnt = result[i].positiveCountMain + result[i].negativCountMain
				//var totalDistCnt = result[i].positiveCountDist + result[i].negativCountDist
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<h4 class="panel-title">'+result[i].organization+'</h4>';
						str+='<div class="row m_top10">';
							str+='<div class="col-md-4 col-xs-12 col-sm-6">';
								str+='<table class="table table-condensed tableNews ">';
									str+='<tbody>';
									str+='<tr class="bg_ED">';
										str+='<td>';
											str+='<p class="text-capital">Main Edition</p>';
											str+='<p>'+result[i].count+'</p>';
										str+='</td>';
										str+='<td>';
											str+='<p class="text-capital text-muted">Positive</p>';
											str+='<p>'+result[i].positiveCountMain+'</p>';
										str+='</td>';
										str+='<td>';
											str+='<p class="text-capital text-muted">Negative</p>';
											str+='<p id="oppNegativeTotal">'+result[i].negativCountMain+'</p>';
										str+='</td>';
									str+='</tr>';
									str+='</tbody>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-4 col-xs-12 col-sm-6">';
								str+='<table class="table table-condensed tableNews ">';
									str+='<tbody>';
									str+='<tr class="bg_ED">';
										str+='<td>';
											str+='<p class="text-capital">District Edition</p>';
											str+='<p>'+result[i].totalCount+'</p>';
										str+='</td>';
										str+='<td>';
											str+='<p class="text-capital text-muted">Positive</p>';
											str+='<p id="oppPositiveTotal">'+result[i].positiveCountDist+'</p>';
										str+='</td>';
										str+='<td>';
											str+='<p class="text-capital text-muted">Negative</p>';
											str+='<p>'+result[i].negativCountDist+'</p>';
										str+='</td>';
									str+='</tr>';
									str+='</tbody>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
						str+='<div id="topTrendingTrackedIssues'+i+'" style="height:200px" class="m_top20" ></div>';
					str+='</div>';
				str+='</div>';
				
				
				
			}
			$("#topTrendingTracked").html(str);					
		}else{
			$("#topTrendingTracked").html('NO DATA AVAILABLE');
		}
		
		if(result != null && result.length > 0){
			var countVar =0;
		for(var i in result){
			countVar =countVar+1;
			if (countVar === 5) {
				break;
			}
			var districtNamesArray =[];
			var districtWisePositiveCountArray = [];
			var districtWiseNegativeCountArray = [];
			
			if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length > 0){
				for(var j in result[i].coreDashBoardVOList){
					var totalPositive = result[i].coreDashBoardVOList[j].positiveCountDist + result[i].coreDashBoardVOList[j].positiveCountMain;  
					var totalNegative = result[i].coreDashBoardVOList[j].negativCountDist + result[i].coreDashBoardVOList[j].negativCountMain;  
					districtNamesArray.push(result[i].coreDashBoardVOList[j].organization);
					districtWisePositiveCountArray.push(totalPositive);
					districtWiseNegativeCountArray.push(totalNegative);
				}
			}	
				if(districtWisePositiveCountArray.length !=0 && districtWiseNegativeCountArray.length !=0){
					$(function () {
						$('#topTrendingTrackedIssues'+i+'').highcharts({
							 colors: ['#64C664','#D33E39'],
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
									categories: districtNamesArray,
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
									text: ''
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
								/* //align: 'right',
								x: -40,
								y: 30,
								verticalAlign: 'top',
								//y: -32,
								floating: true, */
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false
							},
							tooltip: {
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
												return Highcharts.numberFormat(this.percentage,1) +'%';
											}
										}
									  
									}
								}
							},
							series: [{
								name: 'Positive',
								data: districtWisePositiveCountArray
							}, {
								name: 'Negative',
								data: districtWiseNegativeCountArray
							}]
						});
					});
				}else{
					$('#topTrendingTrackedIssues'+i+'').html("No Data Available");
					$('#topTrendingTrackedIssues'+i+'').css("height","10px");
				}
			}
		}
	}
	
	function buildStateWiseArticleRelatedToProblem(result){
		var str='';
		str+='<div class="row">';
			str+='<div class="col-md-3 col-xs-6 col-sm-4 text-capital"><div class="pad_15 bg_ED">main edition - '+result.total+'</div></div>';
			str+='<div class="col-md-3 col-xs-6 col-sm-4 bg_ED text-capital"><div class="pad_15 bg_ED">dist edition - '+result.percent+'</div></div>';
		str+='</div>';
		$("#stateWiseArticleRelatedToProblem").html(str)
	}
	function buildDistrictWiseArticleRelatedToProblem(result){
		var str='';
		var distWiseArticlesRelated = [];
		str+='<div id="comaprisonDistrictWiseArticle" style="height:150px;"></div>';
		for(var i in result){
			var obj1 = {
				name: result[i].name,
				y: result[i].posPercent,
				extra:result[i].posCount
			};
			distWiseArticlesRelated.push(obj1);
		}
		$("#districtWiseArticleRelatedToProblem").html(str)
		$(function () {
			 $("#comaprisonDistrictWiseArticle").highcharts({
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
	//Government Building Block End
	
	//Comaprision Building Block Details
	//For Party Building Blocks
	
	function buildChildUserTypesByItsParentUserType(result){
		var str ='';
		
		if(result != null && result.length > 0){
			str+='<ul class="detailedPartySubUl">';
			for(var i in result){
				str+='<li attr_usertypeid="'+result[i].shortName+'" attr_usertype="'+result[i].userType+'" class="detailedPartySubLi">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			}
			str+='</ul>';
			
			getPartyComparisonChildUserTypeMembers(result[0].shortName,result[0].userType);
		}
		$("#userTypeStrId").html(str);
		$(".detailedPartySubUl li:first-child").addClass("active");
		
	}
	
	$(document).on("click",".detailedPartySubUl li",function(){
		if($(this).hasClass("active") == true)
		{
			$(this).removeClass("active");
		}else{
			$(".detailedPartySubUl li").removeClass("active");
			$(this).addClass("active");
		}
	});
	
	
	$(document).on("click",".detailedPartySubLi",function(){
		if($(this).hasClass("active")){
			$(".newsOnLoadShow").hide();
			getPartyComparisonChildUserTypeMembers($(this).attr("attr_usertypeid"),$(this).attr("attr_usertype"));
		}else{
			$("#partyWiseComparisionBlock").html('');
		}
		
	});	
	
	function buildgetPartyComparisonChildUserTypeMembers(result,childUserTypeId,childUserType){
		$("#partyWiseComparisionBlock").html('');
		var str='';
		if(result !=null && result.length >0){
			var firstNewsActivityMemberId;
			var firstNewsUserTypeId;
			var firstNewsChildActivityMemberId = "partyComparisionSubLevelMemberDetailsDiv";
			var firstNewsUserType;
			var firstNewsUserMemberName;
			var firstLocationLevelId;
			firstNewsActivityMemberId = result[0].activityMemberId;
			firstNewsUserTypeId = result[0].userTypeId;
			firstNewsUserType = result[0].usertType;
			firstNewsUserMemberName = result[0].name;
			firstLocationLevelId = result[0].locationLevelId;
			var temp22="";
			
			if(result[0].locationValueSet != null){
				for(var i in result[0].locationValueSet){
					temp22=i==0?result[0].locationValueSet[i]:temp22+","+result[0].locationValueSet[i];
				}
			}
			
			if(childUserType !=null && childUserType.trim() == "MLA" || childUserType.trim() == "CONSTITUENCY INCHARGE" || childUserType.trim() == "MLA/CI"){
				str+='<table class="table table-condensed tableHoverLevels m_top20" id="partyWiseMemberDataTblId">';
						str+='<thead class="bg_D8 text-capital">';
							str+='<tr>';
								str+='<th rowspan="2" style="border-right: 1px solid #c3c3c3 !important;">Rank</th>';
								str+='<th rowspan="2" style="border-right: 1px solid #c3c3c3 !important;">Designation</th>';
								str+='<th rowspan="2" style="border-right: 1px solid #c3c3c3 !important;">Name</th>';
								str+='<th colspan="5" class="text-center" style="border-right: 1px solid #c3c3c3 !important;">Main Edition</th>';
								str+='<th colspan="5" class="text-center">District Edition</th>';
							str+='</tr>';
							str+='<tr>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">Total</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve%</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve%</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">Total</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve%</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve%</div></th>';
							str+='</tr>';
							
						str+='</thead>';
						str+='<tbody>';
						
						for(var i in result){
							var totalPositiveCountMainAdd=0;
							var totalNegativeCountMainAdd=0;
							var totalMainCount = 0;
							var totalPositivepercMainAdd = 0;
							var totalNegativepercMainAdd=0;
							
							
							var totalPositiveCountDistAdd=0;
							var totalNegativeCountDistAdd=0;
							var totalDistCount = 0;
							var totalPositivepercDistAdd = 0;
							var totalNegativepercDistAdd=0;
							
							if(result[i].childUserTypeVOList1 !=null  && result[i].childUserTypeVOList1.length >0){
								for(var j in result[i].childUserTypeVOList1){
									totalPositiveCountMainAdd = totalPositiveCountMainAdd + result[i].childUserTypeVOList1[j].positiveCountMain;
									totalNegativeCountMainAdd = totalNegativeCountMainAdd + result[i].childUserTypeVOList1[j].negativeCountMain;
									totalMainCount = totalPositiveCountMainAdd+totalNegativeCountMainAdd;
									totalPositivepercMainAdd  = ((totalPositiveCountMainAdd*100)/(totalPositiveCountMainAdd+totalNegativeCountMainAdd)).toFixed(2);
									totalNegativepercMainAdd  = ((totalNegativepercMainAdd*100)/(totalPositiveCountMainAdd+totalNegativeCountMainAdd)).toFixed(2);
									
									
									totalPositiveCountDistAdd = totalPositiveCountDistAdd + result[i].childUserTypeVOList1[j].positiveCountDist;
									totalNegativeCountDistAdd = totalNegativeCountDistAdd + result[i].childUserTypeVOList1[j].negativeCountDist;
									totalDistCount = totalPositiveCountDistAdd+totalNegativeCountDistAdd;
									totalPositivepercMainAdd  = ((totalPositiveCountDistAdd*100)/(totalPositiveCountDistAdd+totalNegativeCountDistAdd)).toFixed(2);
									totalNegativepercMainAdd  = ((totalNegativeCountDistAdd*100)/(totalPositiveCountDistAdd+totalNegativeCountDistAdd)).toFixed(2);
									
								}
							}
							str+='<tr>';
							str+='<td>'+(parseInt(i)+1)+'</td>';
							str+='<td>'+result[i].usertType+'</td>';
							str+='<td>'+result[i].name+'</td>';
							str+='<td>'+totalMainCount+'</td>';
							str+='<td>'+totalPositiveCountMainAdd+'</td>';
							str+='<td>'+totalPositivepercMainAdd+'%</td>';
							str+='<td>'+totalNegativeCountMainAdd+'</td>';
							str+='<td>'+totalNegativepercMainAdd+'%</td>';
							str+='<td>'+totalDistCount+'</td>';
							str+='<td>'+totalPositiveCountDistAdd+'</td>';
							str+='<td>'+totalPositivepercDistAdd+'%</td>';
							str+='<td>'+totalNegativeCountDistAdd+'</td>';
							str+='<td>'+totalNegativepercDistAdd+'%</td>';
							str+='</tr>';
							
						}
						
						str+='</tbody>';
					str+='</table>';
					$("#partyWiseComparisionBlock").html(str);
					
					
			}else{
				str+='<ul class="list-inline NewsSlickPanelSlider">';
					var rankVar =0;
					for(var i in result){
						rankVar =rankVar+1;
						str+='<li  style="cursor:pointer;" class="childUserTypesLiClass" attr_id ="partyComparisionSubLevelMemberDetailsDiv" attr_newsselectedmembername="'+result[i].name+'" attr_newsselectedusertype="'+result[i].usertType+'" attr_newsactivitymemberid='+result[i].activityMemberId+'  attr_newsusertypeid='+result[i].userTypeId+' attr_location_level_id = "'+result[i].locationLevelId+'" id="newsTypeAnalysisLocationValueSet'+i+'">';
					
								str+='<div class="panel panel-default panelSlick">';
								str+='<div class="panel-heading" style="background-color: #c3c3c3 !important;">';
								str+='<h4 class="panel-title">'+result[i].name+'</h4>';
									str+='<span class="count">'+rankVar+'</span>';
								str+='</div>';
								str+='<div class="panel-body" style="background-color:#fff;">';
									str+='<h4 class="text-capital">'+result[i].usertType+'</h4>';
									str+='<div class="row">';
										str+='<div class="col-xs-12 col-md-12 col-xs-12">';
										str+='<div class="row">';
											str+='<div class="col-md-4 col-xs-12 col-sm-6 newsComparisionBorder" >';
												str+='<div id="partiesComparisionGraph'+i+'" style="height:150px;width:100%;margin-left: -9px; margin-top: 30px;"></div>';
											str+='</div>';
											str+='<div class="col-md-4 col-xs-12 col-sm-6 newsComparisionBorder scrollableDiv" >';
												str+='<p>Main Edition</p>';
												if(result[i].childUserTypeVOList1 !=null && result[i].childUserTypeVOList1.length >0){
													for(var j in result[i].childUserTypeVOList1){
														str+='<p><img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+result[i].childUserTypeVOList1[j].organization+'.png" style="width:50px;" alt="tdp icon"/></p>';
														str+='<ul class="list-inline">';
															if(result[i].childUserTypeVOList1[j].positiveCountMainPerc !=null && result[i].childUserTypeVOList1[j].positiveCountMainPerc >0){
																str+='<li class="newsCompBlockAlign" >'+result[i].childUserTypeVOList1[j].positiveCountMainPerc.toFixed(0)+'% +ve</li>';
															}else{
																str+='<li class="newsCompBlockAlign" >- +ve</li>';
															}
															if(result[i].childUserTypeVOList1[j].negativeCountMainperc !=null && result[i].childUserTypeVOList1[j].negativeCountMainperc >0){
																str+='<li class="newsCompBlockAlign" >'+result[i].childUserTypeVOList1[j].negativeCountMainperc.toFixed(0)+'% -ve</li>';
															}else{
																str+='<li class="newsCompBlockAlign" >- -ve</li>';
															}
															str+='</ul>';
														str+='<hr  class="newshrAlignment" >';
													}
												}else{
													str+='<p>No Data Available</p>';
												}
												str+='</div>';
												
												str+='<div class="col-md-4 col-xs-12 col-sm-6 newsComparisionBorder scrollableDiv">';
													str+='<p>District Edition</p>';
												if(result[i].childUserTypeVOList1 !=null && result[i].childUserTypeVOList1.length >0){
													for(var k in result[i].childUserTypeVOList1){
														str+='<p><img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+result[i].childUserTypeVOList1[k].organization+'.png" style="width:50px;" alt="tdp icon"/></p>';
															str+='<ul class="list-inline">';
															if(result[i].childUserTypeVOList1[k].positiveCountDistPerc !=null && result[i].childUserTypeVOList1[k].positiveCountDistPerc >0){
																str+='<li class="newsCompBlockAlign" >'+result[i].childUserTypeVOList1[k].positiveCountDistPerc.toFixed(0)+'% +ve</li>';
															}else{
																str+='<li class="newsCompBlockAlign" >- +ve</li>';
															}
															if(result[i].childUserTypeVOList1[k].negativeCountDistPerc !=null && result[i].childUserTypeVOList1[k].negativeCountDistPerc >0){
																str+='<li class="newsCompBlockAlign" >'+result[i].childUserTypeVOList1[k].negativeCountDistPerc.toFixed(0)+'% -ve</li>';
															}else{
																str+='<li class="newsCompBlockAlign" >- -ve</li>';
															}
															str+='</ul>';
															str+='<hr class="newshrAlignment">';
													}
												}else{
													str+='<p>No Data Available</p>';
												}
														
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</li>';
					}
					str+='</ul>';
			}
			
			$("#partyWiseComparisionBlock").html(str);
			
			$(".NewsSlickPanelSlider").slick({
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
				
			$(".scrollableDiv").each(function(){
				var length = $(this).find("ul").length;
				if(length > 3){
					$(".scrollableDiv").mCustomScrollbar({setHeight:'200px'})
				}
			});
			
		}else{
			$("#partyWiseComparisionBlock").html("No Data Available");
		}		
			
			$("#partyWiseMemberDataTblId").dataTable({
						"aaSorting": [],
						"iDisplayLength" : 5	
			});
			
			
			if(result !=null && result.length >0){
				for(var i in result){
					var ComparisionPartyNameAndPostivePercArray =[];
					if(result[i].childUserTypeVOList !=null && result[i].childUserTypeVOList.length >0){
						for(var j in result[i].childUserTypeVOList){
							var partyName;
							var positivepercArray =[] ;
							partyName = result[i].childUserTypeVOList[j].organization;
							positivepercArray.push(result[i].childUserTypeVOList[j].positiveCountMainPerc)
							var color = getColorCodeByStatus(result[i].childUserTypeVOList[j].organization);
							var obj = {
									name: partyName,
									data:positivepercArray,
									color:color
								}
								
								ComparisionPartyNameAndPostivePercArray.push(obj);
							
						}
					}
					if(ComparisionPartyNameAndPostivePercArray.length !=0){
						$(function () {
							$('#partiesComparisionGraph'+i).highcharts({
								//colors: ['#FD9832','#3D9834','#FFCB00','#005DB0'],
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
									categories: ['Parties'],
									labels: {
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
										text: ''
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
										enabled: false,
															
										},
								plotOptions: {
									column: {
										//stacking: 'normal'
									}
								},

								series: ComparisionPartyNameAndPostivePercArray
							});
						});
					}else{
						$('#partiesComparisionGraph'+i).html("No Data Available")
					}
					
				}
				
			}
			
			
				
			
			for(var i in result){
				var locationValueSetIds="";
					if(result[i].locationValueSet !=null && result[i].locationValueSet.length >0){	
						for(var j in result[i].locationValueSet){
							locationValueSetIds=j==0?result[i].locationValueSet[j]:locationValueSetIds+","+result[i].locationValueSet[j];
						}
					}
				$("#newsTypeAnalysisLocationValueSet"+i).attr("attr_locationvalueset_idsStr",locationValueSetIds);
			
			}
		$(".newsOnLoadShow").show();	
		getPartyCompareSubLevelMemberDetails(firstNewsActivityMemberId,firstNewsUserTypeId,firstNewsUserType,firstNewsUserMemberName,firstNewsChildActivityMemberId);
		getComparisionPartyNewsTypeAnalysis(firstLocationLevelId,temp22,firstNewsUserType,firstNewsUserMemberName);
		getComparisionPartyDistrictEditionsOverview(firstLocationLevelId,temp22,firstNewsUserType,firstNewsUserMemberName);
		getComparisonPartyWisePoorLocations(firstLocationLevelId,temp22,firstNewsUserType,firstNewsUserMemberName);
		getComparisionPartyPartyVsPublications("party",firstLocationLevelId,temp22,firstNewsUserType,firstNewsUserMemberName);
		
	}
	
	$(document).on("click",".childUserTypesLiClass",function(){
		
		$(".NewsSlickPanelSlider").find("li").removeClass("panelActiveSlick");
		$(this).addClass("panelActiveSlick");
		var NewsActivityMemberId = $(this).attr("attr_newsactivitymemberid");  
		var NewsUserTypeId = $(this).attr("attr_newsusertypeid");
		var NewsSelectedMemberName = $(this).attr("attr_newsselectedmembername");  
		var NewsSelectedUserType = $(this).attr("attr_newsselectedusertype");
		var NewsChildActivityMemberId = $(this).attr("attr_id");
		var NewslocationValueId = $(this).attr("attr_location_level_id");
		var NewsLocationValueSetIds = $(this).attr("attr_locationvalueset_idsStr");
		$(".newsOnLoadShow").show();
		getPartyCompareSubLevelMemberDetails(NewsActivityMemberId,NewsUserTypeId,NewsSelectedMemberName,NewsSelectedUserType,NewsChildActivityMemberId);
		getComparisionPartyNewsTypeAnalysis(NewslocationValueId,NewsLocationValueSetIds,NewsSelectedMemberName,NewsSelectedUserType);
		getComparisionPartyDistrictEditionsOverview(NewslocationValueId,NewsLocationValueSetIds,NewsSelectedMemberName,NewsSelectedUserType);
		getComparisonPartyWisePoorLocations(NewslocationValueId,NewsLocationValueSetIds,NewsSelectedMemberName,NewsSelectedUserType);
		getComparisionPartyPartyVsPublications("party",NewslocationValueId,NewsLocationValueSetIds,NewsSelectedMemberName,NewsSelectedUserType);
	});
	
	function buildgetPartyCompareSubLevelMemberDetails(result,NewsSelectedMemberName,NewsSelectedUserType,NewsChildActivityMemberId){
		
		$("#"+NewsChildActivityMemberId).html('');
		var str='';
		if(result !=null && result.length >0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4><span  class="text-capital">'+NewsSelectedMemberName+'</span> - <span class="text-capitalize">'+NewsSelectedUserType+'</span></h4>';
					if(NewsChildActivityMemberId != "partyComparisionSubLevelMemberDetailsDiv"){
						str+='<span class="removeSelecUserTypeNews pull-right" attr_removeSelecUserType = "'+NewsChildActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
					}
						if(NewsChildActivityMemberId != "partyComparisionSubLevelMemberDetailsDiv")
						{
							str+='<table class="table table-condensed tableLevels m_top20">';
						}else{
							str+='<table class="table table-condensed tableHoverLevels m_top20">';
						}
						str+='<thead class="bg_D8 text-capital">';
							str+='<tr>';
								str+='<th rowspan="2" style="border-right: 1px solid #c3c3c3 !important;">Rank</th>';
								str+='<th rowspan="2" style="border-right: 1px solid #c3c3c3 !important;">Designation</th>';
								str+='<th rowspan="2" style="border-right: 1px solid #c3c3c3 !important;">Name</th>';
								str+='<th colspan="5" class="text-center" style="border-right: 1px solid #c3c3c3 !important;">Main Edition</th>';
								str+='<th colspan="5" class="text-center">District Edition</th>';
							str+='</tr>';
							str+='<tr>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">Total</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve %</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve %</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">Total</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve %</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve %</div></th>';
							str+='</tr>';
							
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							 str+='<tr class="compareLowLevelActivityMemberCls1"  attr_newsactivitymemberid = "'+result[i].activityMemberId+'" attr_newsusertypeid = "'+result[i].userTypeId+'" attr_newsselectedmembername = "'+result[i].name+'" attr_newsselectedusertype = "'+result[i].usertType+'" id="newsTypeAnalysisLowLevelLocationValueSet'+i+'" attr_lowlevellocationlevelid="'+result[i].locationLevelId+'">';
								str+='<td>'+(parseInt(i)+1)+'</td>';
								str+='<td>'+result[i].usertType+'</td>';
								str+='<td>'+result[i].name+'</td>';
								
								str+='<td>'+result[i].neutralCountMain+'</td>';
								str+='<td>'+result[i].positiveCountMain+'</td>';
								str+='<td>'+result[i].positiveCountMainPerc+'</td>';
								str+='<td>'+result[i].negativeCountMain+'</td>';
								str+='<td>'+result[i].negativeCountMainperc+'</td>';
								
								str+='<td>'+result[i].neutralCountDist+'</td>';
								str+='<td>'+result[i].positiveCountDist+'</td>';
								str+='<td>'+result[i].positiveCountDistPerc+'</td>';
								str+='<td>'+result[i].negativeCountDist+'</td>';
								str+='<td>'+result[i].negativeCountDistPerc+'</td>';
							str+='</tr>'; 
							str+='<tr class="showHideTr" style="display:none" attr_id = "districtpositionId1'+result[i].userTypeId+''+i+'">';
							
							str+='<td colspan="13"  id="districtpositionId1'+result[i].userTypeId+''+i+'">';
							
							str+='</td>';
						str+='</tr>';
						}
							
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			
		}
		$("#"+NewsChildActivityMemberId).html(str);
		
			for(var i in result){
				var LowLevellocationValueSetIds="";
					if(result[i].locationValueSet !=null && result[i].locationValueSet.length >0){	
						for(var j in result[i].locationValueSet){
							LowLevellocationValueSetIds=j==0?result[i].locationValueSet[j]:LowLevellocationValueSetIds+","+result[i].locationValueSet[j];
						}
					}
				$("#newsTypeAnalysisLowLevelLocationValueSet"+i).attr("attr_lowlevellocationvalueset_idsStr",LowLevellocationValueSetIds);
				
			}
		}
	
	 $(document).on("click",".compareLowLevelActivityMemberCls1",function(){
		  $("#partyAndPublication").html('');
		$(this).closest('tr').next('tr.showHideTr').show(); 
		var NewsActivityMemberId = $(this).attr("attr_newsactivitymemberid");  
		var NewsUserTypeId = $(this).attr("attr_newsusertypeid"); 
		var NewsSelectedMemberName = $(this).attr("attr_newsselectedmembername");  
		var NewsSelectedUserType = $(this).attr("attr_newsselectedusertype");  
		var NewsChildActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id"); 
		var  LowLevelLocationlevelId= $(this).attr("attr_lowlevellocationlevelid");  
		var  LowLevelLocationlevelSetIds= $(this).attr("attr_lowlevellocationvalueset_idsStr");  
		
		
		getPartyCompareSubLevelMemberDetails(NewsActivityMemberId,NewsUserTypeId,NewsSelectedMemberName,NewsSelectedUserType,NewsChildActivityMemberId);
		getComparisionPartyNewsTypeAnalysis(LowLevelLocationlevelId,LowLevelLocationlevelSetIds,NewsSelectedMemberName,NewsSelectedUserType);
		getComparisionPartyDistrictEditionsOverview(LowLevelLocationlevelId,LowLevelLocationlevelSetIds,NewsSelectedMemberName,NewsSelectedUserType);
		getComparisonPartyWisePoorLocations(LowLevelLocationlevelId,LowLevelLocationlevelSetIds,NewsSelectedMemberName,NewsSelectedUserType);
		getComparisionPartyPartyVsPublications("party",LowLevelLocationlevelId,LowLevelLocationlevelSetIds,NewsSelectedMemberName,NewsSelectedUserType);
		
	});
	$(document).on("click",".removeSelecUserTypeNews",function(){
		 
		 var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		 $("#"+removeSelected).remove();
		 
	});
	
	//Government Building End
	
	//Comaprision Building Blocks Details
	//For Party Building Blocks
	
	function buildComparisonGovtMinistriesInfo(result){
		$("#comparisonGovtMinistriesInfo").html('');
		var str='';
		if(result !=null && result.length >0){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				/* for(var i in result){
					str+='<h4 class="panel-title">'+result[i].name+'</h4>';
				} */
				str+='<ul class="list-inline slickPanelSliderGovtCom">';
				for(var i in result){
					str+='<li class="comparisonGovtMinistriesInfoSubLevel" id="comparisonGovtMinistriesInfoSubLevel'+i+'" style="cursor:pointer;">';
						str+='<div class="panel panel-default panelSlick panelSlickHeightSet">';
							str+='<div class="panel-heading">';
								str+='<h4 class="panel-title">'+result[i].name+'</h4>';
								str+='<span class="count" title='+result[i].count+' data-placement="bottom">'+(parseInt(i)+1)+'</span>';
							str+='</div>';
							str+='<div class="panel-body" style="background-color:#fff;">';
								str+='<img  src="https://mytdp.com/images/cadre_images/'+result[i].imageUrl+'" style="height:35px;width:40px;" onerror="setDefaultImage(this);" alt="Image" class="img-responsive thumbnailSearch"/>';
								if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
									str+='<ul class="ministersUl m_top10">';
									for(var j in result[i].coreDashBoardVOList){
										str+='<li attr_departmentids="'+result[i].coreDashBoardVOList[j].organizationId+'">'+result[i].coreDashBoardVOList[j].organization+'</li>';
									}
									str+='</ul>';
								}
							str+='</div>';
						str+='</div>';
					str+='</li>';
				}
					
			str+='</div>';
		}
		$("#comparisonGovtMinistriesInfo").html(str);
		for(var i in result){
			var idsStr="";
			var ministerName = result[i].name;
			if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
				for(var j in result[i].coreDashBoardVOList){
					idsStr = j==0?result[i].coreDashBoardVOList[j].organizationId:idsStr+","+result[i].coreDashBoardVOList[j].organizationId;
				}
			}
			$("#comparisonGovtMinistriesInfoSubLevel"+i).attr("attr_department_idsStr",idsStr);
			$("#comparisonGovtMinistriesInfoSubLevel"+i).attr("attr_ministername",ministerName);
		}
		$(".count").tooltip();
		$(".slickPanelSliderGovtCom").slick({
			 slide: 'li',
			 slidesToShow: 3,
			 slidesToScroll: 3,
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
			  ]
		});
		
		var maxHeight = 0;
		$(".panelSlickHeightSet").each(function(){
		   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
		});
		$(".panelSlickHeightSet").height(maxHeight);

	}
	
	$(document).on("click",".comparisonGovtMinistriesInfoSubLevel",function(){
		$("#comparisonGovtProblemsOverAllSubLevelAnalysis").html('');
		$(".moreDetailsBlockNews").show();
		var ministerSubLevelId = "ministerSubLevelDetailsDiv";
		
		getAllDepartmentEditionsWiseDetails($(this).attr("attr_department_idsstr"),$(this).attr("attr_ministername"),ministerSubLevelId);
		getCompareGovtCandidateDepartmentsWiseDistrictOverview($(this).attr("attr_department_idsstr"));
		getComparisonGovernamentTrendingTrackedIssues($(this).attr("attr_department_idsstr"),$(this).attr("attr_ministername"));
		getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems(7,$(this).attr("attr_department_idsstr"),'');
		getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems(0,$(this).attr("attr_department_idsstr"),'');
		getCompareGovernamentDistrictWiseArticleRelatedToProblem($(this).attr("attr_department_idsstr"));
	});
	
	function buildgetAllDepartmentEditionsWiseDetails(result,ministerName,ministerSubLevelId){
		$("#"+ministerSubLevelId).html('');
		var str='';
		if(result !=null && result.length >0){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h4><span  class="text-capital">'+ministerName+'</span></h4>';
				if(ministerSubLevelId != "ministerSubLevelDetailsDiv"){
				str+='<span class="removeSelectedMinister pull-right" attr_removeSelectedminister = "'+ministerSubLevelId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
			}
				str+='<div class="table-responsive">';
					if(ministerSubLevelId != "ministerSubLevelDetailsDiv")
					{
						str+='<table class="table table-condensed tableLevels m_top20">';
					}else{
						str+='<table class="table table-condensed tableHoverLevels m_top20">';
					}
						str+='<thead class="bg_D8 text-capital">';
							str+='<tr >';
								str+='<th rowspan="2" style="border-right: 1px solid #c3c3c3 !important;">Rank</th>';
								str+='<th rowspan="2" style="border-right: 1px solid #c3c3c3 !important;">Department</th>';
								str+='<th colspan="5" class="text-center" style="border-right: 1px solid #c3c3c3 !important;">Main Edition</th>';
								str+='<th colspan="5" class="text-center">District Edition</th>';
							str+='</tr>';
							str+='<tr>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">Total</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve %</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve %</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">Total</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">+ve %</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve</div></th>';
								str+='<th><div class="bg_ED text-center" style="padding:2px 3px">-ve %</div></th>';
							str+='</tr>';
							
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							if(ministerSubLevelId != "ministerSubLevelDetailsDiv"){
								str+='<tr style="cursor:auto;">';
							}else{
								str+='<tr class="organizationCls" attr_organization_id="'+result[i].organizationId+'" attr_ministernamelowlevel="'+ministerName+'">';
							}
							 
								str+='<td>'+(parseInt(i)+1)+'</td>';
								str+='<td>'+result[i].organization+'</td>';
								
								str+='<td>'+result[i].totalCount+'</td>';
								str+='<td>'+result[i].positiveCountMain+'</td>';
								str+='<td>'+result[i].positivePerc+'</td>';
								str+='<td>'+result[i].negativCountMain+'</td>';
								str+='<td>'+result[i].negativePerc+'</td>';
								
								str+='<td>'+result[i].count+'</td>';
								str+='<td>'+result[i].positiveCountDist+'</td>';
								str+='<td>'+result[i].positiveDistPerc+'</td>';
								str+='<td>'+result[i].negativCountDist+'</td>';
								str+='<td>'+result[i].negativeDistPerc+'</td>';
							str+='</tr>'; 
							if(ministerSubLevelId == "ministerSubLevelDetailsDiv"){
								str+='<tr class="ministerShowHideTr" style="display:none" attr_id = "ministerLowLevelpositionId'+result[i].organizationId+''+i+'">';
							
								str+='<td colspan="12"  id="ministerLowLevelpositionId'+result[i].organizationId+''+i+'" style="border: medium none ! important;">';
								
								str+='</td>';
							str+='</tr>';
							}
							
						
						}
							
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		}
		
		$("#"+ministerSubLevelId).html(str);
	}
	
	function buildComparisonGovernamentTrendingTrackedIssues(result){
		$("#comparisonGovtTopTrend").html(' ');
		if(result != null && result.length > 0){
			var str='';
			var countVar =0;
			for(var i in result){
				countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				//var totalMainCnt = result[i].positiveCountMain + result[i].negativCountMain
				//var totalDistCnt = result[i].positiveCountDist + result[i].negativCountDist
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<h4 class="panel-title">'+result[i].organization+'</h4>';
						str+='<div class="row m_top10">';
							str+='<div class="col-md-4 col-xs-12 col-sm-6">';
								str+='<table class="table table-condensed tableNews ">';
									str+='<tbody>';
									str+='<tr class="bg_ED">';
										str+='<td>';
											str+='<p class="text-capital">Main Edition</p>';
											str+='<p>'+result[i].count+'</p>';
										str+='</td>';
										str+='<td>';
											str+='<p class="text-capital text-muted">Positive</p>';
											str+='<p>'+result[i].positiveCountMain+'</p>';
										str+='</td>';
										str+='<td>';
											str+='<p class="text-capital text-muted">Negative</p>';
											str+='<p id="oppNegativeTotal">'+result[i].negativCountMain+'</p>';
										str+='</td>';
									str+='</tr>';
									str+='</tbody>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-4 col-xs-12 col-sm-6">';
								str+='<table class="table table-condensed tableNews ">';
									str+='<tbody>';
									str+='<tr class="bg_ED">';
										str+='<td>';
											str+='<p class="text-capital">District Edition</p>';
											str+='<p>'+result[i].totalCount+'</p>';
										str+='</td>';
										str+='<td>';
											str+='<p class="text-capital text-muted">Positive</p>';
											str+='<p id="oppPositiveTotal">'+result[i].positiveCountDist+'</p>';
										str+='</td>';
										str+='<td>';
											str+='<p class="text-capital text-muted">Negative</p>';
											str+='<p>'+result[i].negativCountDist+'</p>';
										str+='</td>';
									str+='</tr>';
									str+='</tbody>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
						str+='<div id="comparisonTrendingTrackedIssues'+i+'" style="height:200px" class="m_top20" ></div>';
					str+='</div>';
				str+='</div>';
				
				
				
			}
			$("#comparisonGovtTopTrend").html(str);					
		}else{
			$("#comparisonGovtTopTrend").html('NO DATA AVAILABLE');
		}
		
		if(result != null && result.length > 0){
			var countVar =0;
		for(var i in result){
			countVar =countVar+1;
			if (countVar === 5) {
				break;
			}
			var districtNamesArray =[];
			var districtWisePositiveCountArray = [];
			var districtWiseNegativeCountArray = [];
			
			if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length > 0){
				
				for(var j in result[i].coreDashBoardVOList){
					totalPositive = result[i].coreDashBoardVOList[j].positiveCountDist + result[i].coreDashBoardVOList[j].positiveCountMain;  
					totalNegative = result[i].coreDashBoardVOList[j].negativCountDist + result[i].coreDashBoardVOList[j].negativCountMain;  
					districtNamesArray.push(result[i].coreDashBoardVOList[j].organization);
					districtWisePositiveCountArray.push(totalPositive);
					districtWiseNegativeCountArray.push(totalNegative);
				}
			}	
				if(districtWisePositiveCountArray.length !=0 && districtWiseNegativeCountArray.length !=0){
					$(function () {
						$('#comparisonTrendingTrackedIssues'+i+'').highcharts({
							 colors: ['#64C664','#D33E39'],
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
									categories: districtNamesArray,
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
									text: ''
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
								/* //align: 'right',
								x: -40,
								y: 30,
								verticalAlign: 'top',
								//y: -32,
								floating: true, */
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false
							},
							tooltip: {
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
												return Highcharts.numberFormat(this.percentage,1) +'%';
											}
										}
									  
									}
								}
							},
							series: [{
								name: 'Positive',
								data: districtWisePositiveCountArray
							}, {
								name: 'Negative',
								data: districtWiseNegativeCountArray
							}]
						});
					});
				}else{
					$('#comparisonTrendingTrackedIssues'+i+'').html("No Data Available");
					$('#comparisonTrendingTrackedIssues'+i+'').css("height","10px");
				} 
			}
		}
	}
	
	function buildCompareGovtCandidateDepartmentsWiseDistrictOverview(result){
		var str=' ';
		for(var i in result)
		{
			str+='<h4 class="panel-title">'+result[i].coreDashBoardVOList[0].organization+'</h4>';
			str+='<div id="departmentWiseCompare'+i+'" style="height:120px"></div>';
		}
		$("#comparisonGovtDeptDOverview").html(str)
		if(result != null && result.length > 0){
				for(var i in result){
					
					var PositivePercArray = [];
					var NegativePercArray = [];
					var candidateNameArray=[];
					
						for(var j in result[i].coreDashBoardVOList){
							candidateNameArray.push(result[i].coreDashBoardVOList[j].districtName)
							PositivePercArray.push(result[i].coreDashBoardVOList[j].positiveCountDist)
							NegativePercArray.push(result[i].coreDashBoardVOList[j].negativCountDist)
								
						}
					
						
						$(function () {
							 $("#departmentWiseCompare"+i).highcharts({
								  colors: ['#D33E39','#64C664'],
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
								
								 tooltip: {
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
													return Highcharts.numberFormat(this.percentage,1) + '%';
												}
											}
										  
										}
									}
								},

								series: [{
									name: 'Negative',
									data: NegativePercArray,
									
								},{
									name: 'Positive',
									data: PositivePercArray,
									
								}],
							 
							});
						});
					
				}
			}else{
				$("#comparisonGovtDeptDOverview").html("No Data Available");
			}
	}
	
	function buildCompareDistrictWiseArticleRelatedToProblem(result){
		var str='';
		var distWiseArticlesRelated = [];
		str+='<div id="comaprisonDistrictWiseArticleProblem" style="height:150px;"></div>';
		for(var i in result){
			var obj1 = {
				name: result[i].name,
				y: result[i].posPercent
			};
			distWiseArticlesRelated.push(obj1);
		}
		$("#compareDistrictWiseArticleRelatedToProblem").html(str)
		$(function () {
			 $("#comaprisonDistrictWiseArticleProblem").highcharts({
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
					}
				},

				tooltip: {
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
				},

				series: [{
					name: 'Completed',
					data: distWiseArticlesRelated
				}],
			 
			});
		});
	
	}
	function buildCompareStateWiseArticleRelatedToProblem(result){
		var str='';
		
		if(result.total > 0 || result.percent > 0)
		{
			str+='<div class="row">';
				str+='<div class="col-md-3 col-xs-6 col-sm-4 text-capital"><div class="pad_15 bg_ED">main edition - '+result.total+'</div></div>';
				str+='<div class="col-md-3 col-xs-6 col-sm-4 bg_ED text-capital"><div class="pad_15 bg_ED">dist edition - '+result.percent+'</div></div>';
			str+='</div>';
		}else{
			str+='<div class="row">';
				str+='<div class="col-md-3 col-xs-6 col-sm-3 text-capital">NO DATA AVAILABLE</div>';
			str+='</div>';
		}
		$("#compareStateWiseArticleRelatedToProblem").html(str)
	}
	
	function buildComparisionGovtOverAllAnalysisOfActionImmediatelyProblems(result,propertyId,deptIdsStr,propertyName){
		var locationLevelNameArray =[];
		
			if(globalUserAccessLevelId == 2){
				if(result != null && result.length > 0){
				var str='';
			str+='<div class="row">';
				str+='<div class="col-md-7 col-xs-12 col-sm-6 m_top10">';
					str+='<h4 class="m_top20">DEPARTMENTS WISE</h4>';
				str+='</div>';
				str+='<div class="col-md-7 col-xs-12 col-sm-12 m_top10">';
					str+='<ul style="list-style:none;padding-left:0px;" class="textAlignDepartment dynamicHeightApply">';
					for(var i in result){
						if(result[i].name !=null && result[i].name.length>55){
							str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,55)+'...</span> <span class="pull-right">'+result[i].count+'</span></li>';
						}else{
							str+='<li >'+result[i].name+'  <span class="pull-right">'+result[i].count+'</span></li>';
						}
						
					}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-md-5 col-xs-12 col-sm-4">';
					str+='<div id="comparisonProblemsRelatedStateLevel" style="width:300px;height:180px"></div>';
				str+='</div>';
			str+='</div>';
								
			}
		
			$("#comparisonGovtProblemsStateLevelOverview").html(str);
			$('[data-toggle="tooltip"]').tooltip();
			
			/* var dynamicHeight;
				$(".dynamicHeightApply").each(function(){
					dynamicHeight = $(this).find("li").length;
					dynamicHeight = (dynamicHeight*36);
				});
			if(dynamicHeight > 150)
			{
				$("#comparisonProblemsRelated").css("height","150px");
			}else{
				$("#comparisonProblemsRelated").css("height",dynamicHeight+"px");
			} */
			
			
			if(result != null && result.length > 0){
				
				var mainArray = [];
					for(var i in result){
					var problemDeptPostivePercAndNameArray = [];
						problemDeptPostivePercAndNameArray.push(result[i].name)
						problemDeptPostivePercAndNameArray.push(result[i].positivePerc)
						mainArray.push(problemDeptPostivePercAndNameArray)
					}	
					
				$(function () {
					$('#comparisonProblemsRelatedStateLevel').highcharts({
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
							pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.2f}%</b><br/>'
						},
						plotOptions: {
							bar: {
								dataLabels: {
									enabled: true,
									formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.y,2) + '%';
										}
									}
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
							 data: mainArray
						}]
					});
				});
			
			}
			}
			if(propertyId == 7){
				if(result != null && result.length > 0){
				var str='';
				
								str+='<div class="row">';
									str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
										str+='<h4 class="m_top20">DEPARTMENTS WISE</h4>';
									str+='</div>';
									str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
										str+='<ul style="list-style:none;padding-left:0px;" class="textAlignDepartment dynamicHeightApplyCom">';
										for(var i in result){
											if(result[i].name !=null && result[i].name.length>55){
												str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,55)+'...</span> <span class="pull-right">'+result[i].count+'</span></li>';
											}else{
												str+='<li >'+result[i].name+'  <span class="pull-right">'+result[i].count+'</span></li>';
											}
											
										}
										str+='</ul>';
									str+='</div>';
									str+='<div class="col-md-5 col-xs-12 col-sm-4">';
										str+='<div id="comparisonProblemsRelatedDistrictLevel" style="width:300px;"></div>';
									str+='</div>';
								str+='</div>';
					
			}
		
			$("#comparisonGovtProblemsDistrictLevelOverview").html(str);
			$('[data-toggle="tooltip"]').tooltip();
			
			var dynamicHeight;
				$(".dynamicHeightApplyCom").each(function(){
					dynamicHeight = $(this).find("li").length;
					dynamicHeight = (dynamicHeight*36)+"px";
						
				});
					
			$("#comparisonProblemsRelatedDistrictLevel").css("height",dynamicHeight);
			 
			if(result != null && result.length > 0){
				var mainArray = [];
					for(var i in result){
					var problemDeptPostivePercAndNameArray = [];
						problemDeptPostivePercAndNameArray.push(result[i].name)
						problemDeptPostivePercAndNameArray.push(result[i].positivePerc)
						mainArray.push(problemDeptPostivePercAndNameArray)
					}	
				$(function () {
					$('#comparisonProblemsRelatedDistrictLevel').highcharts({
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
							data: mainArray
						}]
					});
				});
			}
			
		}else if(propertyId == 0){
			
			if(result != null && result.length > 0){
					var str='';
					
							str+='<div class="row">';
								str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
									str+='<h4>OVERALL ANALYSIS OF ACTION IMMEDIATELY PROBLEMS</h4>';
										str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
										str+='<ul style="list-style:none;" class="textAlignDepartment">';
										for(var i in result){
											var properName = getProperName(result[i].name);
											if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
												locationLevelNameArray.push(properName);
												str+='<li class="heightDyna" style="text-transform: uppercase;">'+properName+'  <span class="pull-right ovarAllAnalysisSubLevelCom" attr_propertyid ="'+result[i].id+'" attr_property_name="'+properName+'" attr_departmentids="'+deptIdsStr+'" style="cursor:pointer" data-toggle="tooltip" data-placement="top" title="Get Detailed View">'+result[i].count+'</span></li>';
											}
											
										}
										
										
										str+='</ul>';
									str+='</div>';
									str+='<div class="col-md-5 col-xs-12 col-sm-4">';
										str+='<div id="comparisonProblemsRelatedOverAllAnalysis" style="width:300px;height:200px;"></div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						
			}
			
			$("#comparisonGovtProblemsOverAllAnalysis").html(str);
			$('[data-toggle="tooltip"]').tooltip();
				 
			if(result != null && result.length > 0){
				
				var problemDeptPostivePercArray =[];
				for(var i in result){
					var problemRelatedPieChartArray =[];
					problemRelatedPieChartArray.push(result[i].name);
					problemRelatedPieChartArray.push(result[i].positivePerc);
					problemDeptPostivePercArray.push(problemRelatedPieChartArray);
				}
				
				$(function () {
						if(problemDeptPostivePercArray.length !=0){
							$('#comparisonProblemsRelatedOverAllAnalysis').highcharts({
								colors: ['#E5D355','#8D4654','#F25C81','#8085E9'],
								chart: {
									type: 'pie',
									options3d: {
										enabled: true,
										alpha: 25
									}
								},
								title: {
									text: null
								},
								subtitle: {
									text: null
								},
								
								plotOptions: {
									pie: {
										innerSize: 90,
										depth: 10,
										
										showInLegend: false
									},
									
									
								},
								tooltip: {
										pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b><br/>',
										
									},
									
								series: [{
									name: '',
									 colorByPoint: true,
									data: problemDeptPostivePercArray,
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
							$('#comparisonProblemsRelatedOverAllAnalysis').html("No Data Available")
							$('#comparisonProblemsRelatedOverAllAnalysis').css("height","10px")
						}
						
					});
				}
			}else if(propertyId !=7){
				
					if(result != null && result.length > 0){
					var str='';
					str+='<div class="row">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
							str+='<h4 class="m_top20" style="text-transform:uppercase;">PROBLEM CAN BE SOLVED '+propertyName+'</h4>';
								str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
								str+='<ul style="list-style:none;" class="textAlignDepartment dynamicHeightApply2Com">';
								for(var i in result){
									if(result[i].name !=null && result[i].name.length>55){
										str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,55)+'...</span> <span class="pull-right">'+result[i].count+'</span></li>';
									}else{
										str+='<li >'+result[i].name+'  <span class="pull-right">'+result[i].count+'</span></li>';
									}
								}
								str+='</ul>';
							str+='</div>';
							str+='<div class="col-md-5 col-xs-12 col-sm-4">';
							str+='<div id="comparisonProblemsRelatedSublevel" style="width:300px;"></div>';
							
							str+='</div>';
						str+='</div>';
					str+='</div>';
				
				}
			
				$("#comparisonGovtProblemsOverAllSubLevelAnalysis").html(str);
				$('[data-toggle="tooltip"]').tooltip();
				
				var dynamicHeight;
					$(".dynamicHeightApply2Com").each(function(){
						dynamicHeight = $(this).find("li").length;
						dynamicHeight = (dynamicHeight*36)+"px";
							
					});
						
				$("#comparisonProblemsRelatedSublevel").css("height",dynamicHeight);
				 
				if(result != null && result.length > 0){
					var mainArray = [];
						for(var i in result){
						var problemDeptPostivePercAndNameArray = [];
							problemDeptPostivePercAndNameArray.push(result[i].name)
							problemDeptPostivePercAndNameArray.push(result[i].positivePerc)
							mainArray.push(problemDeptPostivePercAndNameArray)
						}	
					$(function () {
						$('#comparisonProblemsRelatedSublevel').highcharts({
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
								data: mainArray
							}]
						});
					});
				}
			
			}
		
	}
	$(document).on("click",".ovarAllAnalysisSubLevelCom",function(){
		$("#comparisonGovtProblemsOverAllSubLevelAnalysis").show();
		$("#comparisonGovtProblemsOverAllSubLevelAnalysis").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var propertyId = $(this).attr("attr_propertyid");
		var propertyName = $(this).attr("attr_property_name");
		var deptIdsStr = $(this).attr("attr_departmentids");
		getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems(propertyId,deptIdsStr,propertyName);
		
	});
	
	//Governmnt Building End
	
	//Notes Functionality
	
	/*Notes Functionality*/
	function displayDashboardCommentsForNews(dashBoardComponentId){
	var jsObj={
		dashBoardComponentId:dashBoardComponentId
	}	
	$.ajax({
	 type: "POST",
	 url: "displayDashboardCommentsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length >0){
		 var str=''; 
      		 
	     str+='<ul class="notesUlDebates m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
            	     
					for(var i in result){ 
                        str+='<li style="margin-top:3px;">'; 
                        str+='<span class="notesTextNews" id="editTextNewsId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
					    str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesnews" attr_cmt_id="editTextNewsId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
                        str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesNews" attr_cmt_id="editTextNewsId'+i+'" attr_comment="'+result[i].comment+'"></i>';
                        str+='</li>';
					}
                        str+='</ul>';
						/*str+='<hr/>';
						str+='<div id="newsUpId" style="color:red;"></div>';
                        str+='<label>Create Notes</label>';
                        str+='<textarea class="form-control notesAreaNews"></textarea>';
                        str+='<button class="btn btn-default btnCustomCreateNews btn-sm "  onClick="savingDashboardCommentForNews(5);">create</button>';*/
			
			$("#notesNewsId").html(str);	 
		}
	});
}
function deleteDashBoardcomments(dashboardCommentId)
{
	var jsObj={
		dashboardCommentId : dashboardCommentId
	}	
	$.ajax({
	 type: "POST",
	 url: "deleteDashBoardcommentsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){	
			if(result.message == "success"){
				
				
			}
		}
			
	});
	
}

function savingDashboardCommentForNews(dashboardComponentId){  
  var comment=$(".notesAreaNews").val();
  if(comment.trim() ==""){
		  $("#newsUpId").html("Notes Required.");
		  return;
	  }
	var editId = $("#cmtNewsId").val();
	//$("#"+editId).parent().html(' ');
	$("#"+editId).html(comment);
	 var dashboardCommentId=0;
	 if($(".notesAreaNews").attr("attr_commentid")>0)
	 {
		dashboardCommentId=$(".notesAreaNews").attr("attr_commentid");		
	 }
	
	var jsObj={
		comment:comment,
		dashboardComponentId: dashboardComponentId,
		dashboardCommentId : dashboardCommentId
	}	
	$.ajax({
	 type: "POST",
	 url: "savingDashboardCommentAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){	
			if(result.message == "success"){
				
				//$("#newsUpId").html('update succuss');
				displayDashboardCommentsForNews(5);
			}
		}			
	});
}
$(document).on("click",".notesIconNews",function(e){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
	e.stopPropagation();
});
$(document).on("click",".deleteNotesNews",function(){
	$(this).closest("li").remove();
});
$(document).on("click",".editNotesNews",function(){ 
	var commentId = $(this).attr("attr_cmt_id");
	var commentId1 = $(this).parent().find(".notesTextNews").attr("attr_commentid");
	var notesHtml = $("#"+commentId).html();
	$(".notesAreaNews").val(notesHtml);  
	$(".notesAreaNews").attr("attr_commentid",commentId1);  
	$("#cmtId").val(commentId);
	//$("#cmtId").val();
	$("#newsUpId").html('');		
});

$(document).on("click",".btnCustomCreateNews",function(){
	var getNewNotes = $(".notesAreaNews").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var cmtId = $("#cmtId").val();
	var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesNews"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
	if(cmtId>0)
	$(".notesUlDebates").append("<li>"+commentText+"</li>");
	$(".notesAreaNews").val('');		
});

	$(document).on("click","#selectAllId",function(){
		 if ($(this).prop('checked')) {
			$(".pubCheckCls").prop('checked', true);
		} else {
			$(".pubCheckCls").prop('checked', false);
		}
	});	
	
	$(document).on("click",".pubCheckCls",function(){
		var checkAll = false;
		$(".pubCheckCls").each(function(){
			if (!$(this).prop('checked')) {
				checkAll = true;
			}
		});
		
		if(checkAll){
			$("#selectAllId").prop('checked', false);
		}else{
			$("#selectAllId").prop('checked', true);
		}
		
	});
	
	$(document).on("click","#impactSelectAllId",function(){
		 if ($(this).prop('checked')) {
			$(".impactCheckCls").prop('checked', true);
		} else {
			$(".impactCheckCls").prop('checked', false);
		}
	});
	
	
	$(document).on("click",".impactCheckCls",function(){
		var checkAll = false;
		$(".impactCheckCls").each(function(){
			if (!$(this).prop('checked')) {
				checkAll = true;
			}
		});
		
		if(checkAll){
			$("#impactSelectAllId").prop('checked', false);
		}else{
			$("#impactSelectAllId").prop('checked', true);
		}
		
	});
	
    function getRescentArticleTime(){
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getRescentArticleTime/"
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getRescentArticleTime/"
		}).then(function(result){
			if(result != null){
				$("#lastUpdatedId").html("Last Updated : "+ result[0].benefit);
			}
		});
	}    
	setInterval(function() {
		getRescentArticleTime();
	}, 60 * 1000);
	
	function getAllNewsPapers(){
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getAllNewsPapers/"+globalState
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getAllNewsPapers/"+globalState
		}).then(function(result){
			$("#newsPapersUlId").html("");
			if(result != null && result.length > 0){
				var str="";
				str+='<li>';
				str+='<label class="checkbox-inline">';
				str+='<input id="selectAllId" type="checkbox" value="0" class="">';
				str+='<div style="margin-top: 3px;"><h5 style="color:#54616C;" class="text-capital">Select All</h5></div>';
				str+='</label>';
				str+='</li>';
				for(var i in result){
					str+='<li>';
					str+='<label class="checkbox-inline">';//alert(jQuery.inArray(result[i].paperId, newsPaperIdsGlob));
					if($.inArray(result[i].paperId, newsPaperIdsGlob) != -1){
						str+='<input type="checkbox" checked="checked" value="'+result[i].paperId+'" class="pubCheckCls">';
					}else{
						str+='<input type="checkbox"  value="'+result[i].paperId+'" class="pubCheckCls">';
					}
					
					str+='<div style="margin-top: 3px;"><h5 style="color:#54616C;" class="text-capital">'+result[i].paperName+'</h5></div>';
					str+='</label>';
					str+='</li>';
				}
				$("#newsPapersUlId").html(str);
				var newspaperlenght = $("#newsPapersUlId").find("li").length;
				if(newspaperlenght >= 7){
				$(".settingsUl").mCustomScrollbar({setHeight:'245'})
				
				}else{
					$(".settingsUl").css("height","auto");
				
				}
			}
		});
	}	
	
	$(document).on("click",".filtersSubmitDivId",function(){
		newsPaperIdsGlob=[];
		$(".pubCheckCls").each(function(){
			if($(this).is(":checked")){
				newsPaperIdsGlob.push($(this).val());
			}
		});
		
		impactScopeIdsGlob = [];
		$(".impactCheckCls").each(function(){
			if($(this).is(":checked")){
				impactScopeIdsGlob.push($(this).val());
			}
		});
		
		if(newsPaperIdsGlob == null || newsPaperIdsGlob.length == 0){
			alert("Please Select Atleast One NewsPaper");
			return;
		}else if(impactScopeIdsGlob == null || impactScopeIdsGlob.length == 0){
			alert("Please Select Impact Scope");
			return;
		}else{
			commonNewsBasicCalls();
			/* getNewsBasicCounts();
			if($(".newsIconExpand i").attr("class").split(" ")[1]=="glyphicon-resize-small"){
				$(".newsliCls").each(function(){
					if($(this).hasClass("active")){
						getUserTypeWiseNewsCounts($(this).attr("attr_value"));
					}
				});
			}
			
			if($('.newsHiddenMoreBlock').css('display') != 'none'){
				$(".viewsLiClass").each(function(){
					if($(this).hasClass("active")){
						var id = $(this).attr("id");
						$( "#"+id).trigger( "click" );
						
						$(".partyDistrictWiseDiv").removeClass("active");
						$(".partyDistrictWiseDiv").each(function(index){
							if(index==0){
								$(this).addClass("active");
							}
						});
					}
				});
			} */
		}
	});
	
	function getComparisonPartyWisePoorLocations(firstLocationLevelId,temp22,NewsSelectedMemberName,NewsSelectedUserType){
		$("#partyComparisonPartyWisePoorL").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
			
		var npIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				npIdsStr=i==0?newsPaperIdsGlob[i]:npIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
	
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getComparisonPartyWisePoorLocations/"+firstLocationLevelId+"/"+temp22+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonPartyWisePoorLocations/"+firstLocationLevelId+"/"+temp22+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			buildComparisonPartyWisePoorLocations(result,NewsSelectedMemberName,NewsSelectedUserType);
		});
	}

	function buildgetComparisionPartyNewsTypeAnalysis(result,NewsSelectedMemberName,NewsSelectedUserType){
		$("#PartyComparisionNewsTypeAnalysisDiv").html('');
		if(result != null && result.length > 0){
			var str1='';
			/* str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
				//str1+='<h4>NEWS TYPE ANALYSIS</h4>';
				str1+='<p><span>'+NewsSelectedMemberName+'</span> - <span>'+NewsSelectedUserType+'</span></p>';
			str1+='</div>'; */
			$("#selectedMemberNameNews").html(NewsSelectedMemberName);
			$("#selectedUserTypeNews").html(NewsSelectedUserType);
			$("#PartyComparisionNewsTypeAnalysisDiv").html(str1);
		}
			if(result != null && result.length > 0){
				for(var i in result){
					var PartyCountPerc;
					var partyName;
					var partyNameAndCountArray =[];
					var districtNameArray =[];
					var tdpPercArray = [];
					var ysrcPercArray =[];
					var incPercArray = [];
					var bjpPercArray = [];
					
					if(result[i].coreDashBoardVOList1 !=null && result[i].coreDashBoardVOList1.length >0){
						for (var j in result[i].coreDashBoardVOList1){
							districtNameArray.push(result[i].coreDashBoardVOList1[j].districtName);
							tdpPercArray.push(result[i].coreDashBoardVOList1[j].tdpPerc);
							ysrcPercArray.push(result[i].coreDashBoardVOList1[j].ysrcPerc);
							incPercArray.push(result[i].coreDashBoardVOList1[j].incPerc);
							bjpPercArray.push(result[i].coreDashBoardVOList1[j].bjpPerc);
							
							
						}
					}
						
					if(districtNameArray.length != 0 && tdpPercArray.length !=0 && ysrcPercArray.length !=0 && incPercArray.length !=0 && bjpPercArray.length !=0){
					$(function () {
						var str='';
						str+='<div class="col-md-4 col-xs-12 col-ms-8 m_top10"><h5 class="text-capital">'+result[i].name+'</h5>';
						str+='<div id="PartyComparisionNewsTypeAnalysisBarChart'+i+'" class="chartLiD" style="height:200px;width:250px;"></div></div>';
						$("#PartyComparisionNewsTypeAnalysisDiv").append(str);
						$('#PartyComparisionNewsTypeAnalysisBarChart'+i).highcharts({
							colors: ['#FFCB00','#005DB0','#3D9834','#FD9832'],
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
									categories: districtNameArray,
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
									text: ''
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
								enabled: false,
								//align: 'center',
								//x: -40,
								//y: 23,
								//verticalAlign: 'top',
								//floating: true, 
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false,
								
							},
							tooltip: {
								//headerFormat: '<b>{point.x}</b>',
								//pointFormat: '<span style="color:{series.color}">: <b>'+{point.percentage:.1f}+'% '+({point.y})+'</b></span><br>',
								formatter: function () {
									var s = '<b>' + this.x + '</b>';

									$.each(this.points, function () {
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :' +
											(this.y)+'%';
									});

									return s;
								},
								shared: true
							},
							plotOptions: {
								column: {
									 
									dataLabels: {
										enabled: false,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return Highcharts.numberFormat(this.y,0)+'%';
											}
										}
									  
									}
								}
							},
							series: [{
								name: 'TDP',
								data: tdpPercArray
							}, {
								name: 'YSRC',
								data: ysrcPercArray
							},{
								name: 'INC',
								data: incPercArray
							},{
								name: 'BJP',
								data: bjpPercArray
							}]
						});
					});
					}
					
				}
			}
	}
	
	function buildgetComparisionPartyDistrictEditionsOverview(result,NewsSelectedMemberName,NewsSelectedUserType){
		$("#ComparisionPartyDistrictWiseNewsReport").html(' ');
		if(result != null && result.length > 0){
			var str1='';
			str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str1+='<h4>DISTRICT WISE PARTIES NEWS</h4>';
				str1+='<p><span>'+NewsSelectedMemberName+'</span> - <span>'+NewsSelectedUserType+'</span></p>';
			str1+='</div>';
			
			$("#ComparisionPartyDistrictWiseNewsReport").html(str1);			
		}
		
		
	if(result != null && result.length > 0){
		var countVar =0;
		for(var i in result){
				countVar =countVar+1;
				if (countVar === 5) {
					break;
				}
					
					var districtNamesArray =[];
					var districtWisePositiveCountArray = [];
					var districtWiseNegativeCountArray = [];
					
				if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length > 0){
					for(var j in result[i].coreDashBoardVOList){
						districtNamesArray.push(result[i].coreDashBoardVOList[j].districtName);
						districtWisePositiveCountArray.push(result[i].coreDashBoardVOList[j].positiveCountMain);
						districtWiseNegativeCountArray.push(result[i].coreDashBoardVOList[j].negativCountMain);
							
					}
				}	
				if(districtWisePositiveCountArray.length !=0 && districtWiseNegativeCountArray.length !=0 && districtNamesArray.length !=0){
					var str='';
					str+='<div class="col-md-4 col-xs-12 col-ms-8 m_top10">';
					str+='<img src="newCoreDashBoard/img/'+result[i].organization+'.png" style="width:25px;" alt="tdp icon" class=" m_top10"/> &nbsp;&nbsp;&nbsp;'+result[i].organization+'';
					str+='<div id="ComparisionpartyDistrictWiseNews'+i+'" class="chartLiD" style="height:230px" ></div>';
					str+='</div>';
				$("#ComparisionPartyDistrictWiseNewsReport").append(str);			
					$(function () {
						$('#ComparisionpartyDistrictWiseNews'+i+'').highcharts({
							 colors: ['#64C664','#D33E39'],
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
									categories: districtNamesArray,
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
									text: ''
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
								enabled: false,
								/* //align: 'right',
								x: -40,
								y: 30,
								verticalAlign: 'top',
								//y: -32,
								floating: true, */
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false
							},
							tooltip: {
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
								pointPadding: 0.2,
								borderWidth: 2,
								groupPadding: 0.2,
								column: {
									stacking: 'percent',
									dataLabels: {
										enabled: false,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return Highcharts.numberFormat(this.percentage,1) +'%';
											}
										}
									  
									}
								}
							},
							series: [{
								name: 'Positive',
								data: districtWisePositiveCountArray
							}, {
								name: 'Negative',
								data: districtWiseNegativeCountArray
							}]
						});
					});
				}
			}
		}
	}
	
	function buildComparisonPartyWisePoorLocations(result,NewsSelectedMemberName,NewsSelectedUserType)
	{
		var str=' ';
		str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top20">';
		str+='<h4 class="text-capital">tdp party top five poor location (<span>'+NewsSelectedMemberName+'</span> - <span>'+NewsSelectedUserType+'</span>)</h4>';
			str+='<p class="text-capital">districts</p>';
			str+='<table class="table tableCumulative">';
				var order=1;
				var BGColor = 1;
				for(var i in result)
				{
					str+='<tr>';
						str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+order+'</span></td>';
						str+='<td>'+result[i].name+'</td>';
						str+='<td>';
							str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result[i].count+'">';
								str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].negativePerc+'" aria-valuemin="0" aria-valuemax="100" style="width:'+result[i].negativePerc+'%;">';
									str+='<span class="sr-only">'+result[i].negativePerc+'</span>';
							  str+='</div>';
							str+='</div>';
						str+='</td>';
						str+='<td class="text-danger">'+result[i].negativePerc+'%</td>';
					str+='</tr>';
					order=order+1;
					if(order==6)
						break;
					BGColor = BGColor - 0.2;
				}
			str+='</table>';
		str+='</div>';
		$("#partyComparisonPartyWisePoorL").html(str);
		$('.progressCustom').tooltip();
	}
	
		function buildgetComparisionPartyWiseDetailes(result,NewsSelectedMemberName,NewsSelectedUserType){
		$("#CompaisionPartyAndPublicationWiseDetailsDiv").html('');
		var str ='';
		if(result !=null && result.length >0){
			//str+='<p><span>'+NewsSelectedMemberName+'</span> - <span>'+NewsSelectedUserType+'</span></p>';
			$("#selectedMemberNameNewsParty").html(NewsSelectedMemberName)
			$("#selectedUserTypeNewsParty").html(NewsSelectedUserType)
			for(var i in result){
				var partyname;
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						str+='<div class="col-md-12 col-xs-12 col-ms-12">';
							str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
						for(var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for(var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									partyname = result[i].coreDashBoardVOList[j].coreDashBoardVOList[0].organization
								}
							}
						}
						str+='<h4  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;"><img src="newCoreDashBoard/img/'+partyname+'.png" class="debatesPartyIcon" />'+partyname+'</h4>';
							str+='</div>';
							str+='<div class="col-xs-11 col-sm-10 col-md-11">';
								str+='<ul class="ComparisionPartyWiseSlickApply">';
						for(var j in result[i].coreDashBoardVOList){
							str+='<li><div id="CompaisionPartywisegraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
						}
							str+='</ul>';
						str+='</div>';
					str+='</div>';	
					}	
					
					
			}
			
		}
		
		
		$("#CompaisionPartyAndPublicationWiseDetailsDiv").html(str);
			if(result !=null && result.length >0){
				for(var i in result){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						for(var j in result[i].coreDashBoardVOList){
							
							var paperNamesArray =[];
							var positivePercArray =[];
							var negativePercArray =[];
							var districtName;
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								
								for(var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									districtName = result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].districtName;
									paperNamesArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].name)
									positivePercArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].positiveCountDist)
									negativePercArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountDist)
									
									
								}
							}
							if(paperNamesArray.length !=0 && positivePercArray.length !=0 &&  negativePercArray.length !=0 &&  negativePercArray.length !=0 && districtName !=0 && districtName !=null){
								$(function () {
									$('#CompaisionPartywisegraph'+i+''+j+'').highcharts({
										 colors: ['#64C664','#D33E39'],
										chart: {
											type: 'column'
										},
										title: {
											text: districtName
										},
									   
										xAxis: {
											 min: 0,
												 gridLineWidth: 0,
												 minorGridLineWidth: 0,
												 categories: paperNamesArray,
											labels: {
													useHTML: true,
													formatter: function() {
														return this.value;
														//+'<img src="https://www.mytdp.com/CommunityNewsPortal/dist/images/'+this.value+'.png" style="width: 45px; vertical-align: middle" />';
													},
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
											data: positivePercArray
										}, {
											name: 'Negative',
											data: negativePercArray
										}]
									});
								});
							}else{
								$('#CompaisionPartywisegraph'+i+''+j+'').html("No Data Available");
								$('#CompaisionPartywisegraph'+i+''+j+'').css("height","20px");
							}
							
						}
						
					}
					
					
					
				}
			}
				
			else{
				$("#CompaisionPartyAndPublicationWiseDetailsDiv").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
			
		$(".ComparisionPartyWiseSlickApply").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false
		}); 
	}
	
	function buildgetComparisionPublicationsWiseDetails(result,NewsSelectedMemberName,NewsSelectedUserType){
		$("#CompaisionPartyAndPublicationWiseDetailsDiv").html();
		var str ='';
		if(result !=null && result.length >0){
			str+='<p><span>'+NewsSelectedMemberName+'</span> - <span>'+NewsSelectedUserType+'</span></p>';
			for(var i in result){
				var papername;
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						str+='<div class="col-md-12 col-xs-12 col-ms-12">';
							str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
						for(var j in result[i].coreDashBoardVOList){
							papername = result[i].coreDashBoardVOList[0].name;
						}
						str+='<h5  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;"><img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+papername+'.png" style="width:60px;" alt="tdp icon"/></h5>';
							str+='</div>';
							str+='<div class="col-xs-11 col-sm-10 col-md-11">';
								str+='<ul class="ComparisionPublicationWiseSlickApply">';
						for(var j in result[i].coreDashBoardVOList){
							str+='<li><div id="ComparisionPublicationwisegraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
						}
							
						
							str+='</ul>';
						str+='</div>';
					str+='</div>';
					}
					
					
					
			}
			
		}
		
		
		$("#CompaisionPartyAndPublicationWiseDetailsDiv").html(str);
		if(result !=null && result.length >0){
				for(var i in result){
					
							
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						for(var j in result[i].coreDashBoardVOList){
							var partyName = [];
							var positivePercArray =[];
							var negativePercArray =[];
							
									partyName.push(result[i].coreDashBoardVOList[j].organization);
									
									positivePercArray.push(result[i].coreDashBoardVOList[j].positivePerc)
									negativePercArray.push(result[i].coreDashBoardVOList[j].negativePerc)
						
							if(partyName.length !=0 && positivePercArray.length !=0 && negativePercArray.length !=0){
								$(function () {
									$('#ComparisionPublicationwisegraph'+i+''+j+'').highcharts({
										 colors: ['#64C664','#D33E39'],
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
												 categories: partyName,
											labels: {
													//rotation: -45,
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
											pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b> <br/>',
											shared: true
										},
										legend: {
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												//stacking: 'percent',
												dataLabels:{
													enabled: true,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.y,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											name: 'Positive',
											data: positivePercArray
										}, {
											name: 'Negative',
											data: negativePercArray
										}]
									});
								});
							}else{
								$('#ComparisionPublicationwisegraph'+i+''+j+'').html("No Data Available");
								$('#ComparisionPublicationwisegraph'+i+''+j+'').css("height","20px");
							}
							
							
						}
					}
						
				}
			}
				
			else{
				$("#CompaisionPartyAndPublicationWiseDetailsDiv").html("No Data Available");
			}
			$(".ComparisionPublicationWiseSlickApply").slick({
					 slide: 'li',
					 slidesToShow: 4,
					 slidesToScroll: 3,
					 infinite: false,
					 swipeToSlide:false,
					 swipe:false,
					 touchMove:false
				}); 
		
	}
	function setDefaultImage(img){
		img.src = "images/User.png";
	}
	function commonNewsBasicCalls(){
		//getNewsBasicCounts();
		getNewsBasicPartyCounts();
		getPaperWiseNewsBasicCounts();
		 if($(".newsIconExpand i").attr("class").split(" ")[1]=="glyphicon-resize-small"){
			$(".newsliCls").each(function(){
				if($(this).hasClass("active")){
					getUserTypeWiseNewsCounts($(this).attr("attr_value"));
				}
			});
		}
			if($('.newsHiddenMoreBlock').css('display') != 'none'){
				$(".viewsLiClass").each(function(){
					if($(this).hasClass("active")){
						var id = $(this).attr("id");
						$( "#"+id).trigger( "click" );
						
						$(".partyDistrictWiseDiv").removeClass("active");
						$(".partyDistrictWiseDiv").each(function(index){
							if(index==0){
								$(this).addClass("active");
							}
						});
					}
				});
			} 
	}
	$(document).on("click",".radioTypeCls",function(){
		getComparisonGovtMinistriesInfo();
	});
	function getAllSubDepartmentEditionsWiseDetails(departmentIdsStr,ministerName,ministerSubLevelId){
		$("#"+ministerSubLevelId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var state = globalState;
		var startDate=currentFromDate,endDate=currentToDate;
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getAllSubDepartmentEditionsWiseDetails/"+startDate+"/"+endDate+"/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+newsPaperIdsStr+"/"+departmentIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getAllSubDepartmentEditionsWiseDetails/"+startDate+"/"+endDate+"/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+newsPaperIdsStr+"/"+departmentIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			buildgetAllDepartmentEditionsWiseDetails(result,ministerName,ministerSubLevelId);
		});
	}
	$(document).on("click",".organizationCls",function(){
		 $(this).closest('tr').next('tr.ministerShowHideTr').show(); 
		 var ministerSubLevelId = $(this).closest('tr').next('tr.ministerShowHideTr').attr("attr_id");  
		 
		
		getAllSubDepartmentEditionsWiseDetails($(this).attr("attr_organization_id"),$(this).attr("attr_ministernamelowlevel"),ministerSubLevelId)		
	});
	$(document).on("click",".removeSelectedMinister",function(){
		  var removeSelected = $(this).attr("attr_removeSelectedminister"); 
		  $("#"+removeSelected).html(' ');
		 $("#"+removeSelected).closest('.ministerShowHideTr').hide();
	});
	function getPaperWiseNewsBasicCounts(){
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		} 
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var startDate=currentFromDate,endDate=currentToDate;
		var state = globalState;
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getPaperWiseNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getPaperWiseNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr
		}).then(function(result){
			buildPaperWiseNewsBasicCounts(result);
		});
	}
	function buildPaperWiseNewsBasicCounts(result){
		var str='';
		var str1='';
		var str2='';
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		} 
		
		if(result != null && result.length > 0){
			str2+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
			str2+='<table class="table table-condensed tableNews bg_ED">';
			str2+='<tr>';
			str2+='<td>';
			str2+='<p class="text-capital responsiveFont">Main Count</p>';
			str2+='<p><a id="totalUniqueMainTotal" class="newsBasicCountDetailsDivEd" attr_editiontype="1" attr_benefitid =" " attr_isdepartment = " " attr_npids="'+newsPaperIdsStr+'" attr_partyids=" ">'+((result[0].positiveCountMain)+(result[0].negativCountMain))+'<small>('+result[0].editionUniqueCountMain+')</small></a></p>';
			str2+='</td>';
			str2+='<td>';
			str2+='<p class="text-capital text-muted responsiveFont">Positive</p>';
			str2+='<span><a id="totalUniqueMainPositive" class="newsBasicCountDetailsDivEd" attr_editiontype="1" attr_benefitid ="1" attr_isdepartment = " " attr_npids="'+newsPaperIdsStr+'" attr_partyids=" ">'+result[0].positiveCountMain+'</a></span><small id="totalUniqueMainPositivePercent" class="text-success"> 0.0 %</small>';
			str2+='</td>';
			str2+='<td>';
			str2+='<p class="text-capital text-muted responsiveFont">Negative</p>';
			str2+='<span><a id="totalUniqueMainNegative" class="newsBasicCountDetailsDivEd" attr_editiontype="1" attr_benefitid ="2" attr_isdepartment = " " attr_partyids=" " attr_npids="'+newsPaperIdsStr+'">'+result[0].negativCountMain+'</a></span><small id="totalUniqueMainNegativePercent" class="text-danger"> 0.0 %</small>';
			str2+='</td>';
			str2+='</tr>';
			str2+='</table>';
			str2+='</div>';
			
			str2+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0" id="distEditionWiseCountsDivId">';
			str2+='<table class="table table-condensed tableNews bg_ED">';
			str2+='<tr>';
			str2+='<td>';
			str2+='<p class="text-capital">Dist Count</p>';
			str2+='<p><a id="totalUniqueDistTotal" class="newsBasicCountDetailsDivEd" attr_editiontype="2,3" attr_benefitid =" " attr_isdepartment = " " attr_partyids=" " attr_npids="'+newsPaperIdsStr+'">'+result[0].editionUniqueCountDist+'</a></p>';
			str2+='</td>';
			str2+='<td>';
			str2+='<p class="text-capital text-muted">Positive</p>';
			str2+='<span><a id="totalUniqueDistPositive" class="newsBasicCountDetailsDivEd" attr_editiontype="2,3" attr_benefitid ="1" attr_isdepartment = " " attr_partyids=" " attr_npids="'+newsPaperIdsStr+'">'+result[0].positiveCountDist+'</a></span><small class="text-success" id="totalUniqueDistPositivePercent"> 0.0 %</small>';
			str2+='</td>';
			str2+='<td>';
			str2+='<p class="text-capital text-muted">Negative</p>';
			str2+='<span><a id="totalUniqueDistNegative" class="newsBasicCountDetailsDivEd" attr_editiontype="2,3" attr_benefitid ="2" attr_isdepartment = " " attr_partyids=" " attr_npids="'+newsPaperIdsStr+'">'+result[0].negativCountDist+'</a></span><small class="text-danger" id="totalUniqueDistNegativePercent"> 0.0 %</small>';
			str2+='</td>';
			str2+='</tr>';
			str2+='</table>';
			str2+='</div>';
									
			$("#PaperWiseBlockInDivId").html(str2);
			
			if((result[0].positiveCountMain+result[0].negativCountMain) > 0){
				$("#totalUniqueMainPositivePercent").html(" "+((result[0].positiveCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+" %");
				$("#totalUniqueMainNegativePercent").html(" "+((result[0].negativCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+" %");
			}
			
			if((result[0].positiveCountDist+result[0].negativCountDist) > 0){
				$("#totalUniqueDistPositivePercent").html(" "+((result[0].positiveCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+" %");
				$("#totalUniqueDistNegativePercent").html(" "+((result[0].negativCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+" %");
			}
			
			for(var i=1;i<result.length;i++){
				
				str+='<tr>';
					str+='<td>';
							str+='<img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+result[i].organization+'.png" alt="cong logo" class="debatesPartyIcon"/><span><a class="newsBasicCountDetailsDivEd" attr_editiontype="1" attr_benefitid =" " attr_isdepartment = " "attr_partyids=" " attr_npids="'+result[i].organizationId+'">'+((result[i].positiveCountMain)+(result[i].negativCountMain))+'<small>('+result[i].totalCount+')</small></a></span>';
						str+='</td>';
						str+='<td>';
							if(result[i].totalCount > 0)
								str+='<span><a class="newsBasicCountDetailsDivEd" attr_editiontype="1" attr_benefitid ="1" attr_isdepartment = " " attr_partyids=" " attr_npids="'+result[i].organizationId+'">'+result[i].positiveCountMain+'</a></span><small class="text-success"> '+((result[i].positiveCountMain/((result[i].positiveCountMain)+(result[i].negativCountMain)))*100).toFixed(2)+' %</small>';
							else
								str+='<span>'+result[i].positiveCountMain+'</span><small class="text-success"> 0 %</small>';
						str+='</td>';
						str+='<td>';
							if(result[i].totalCount > 0)
								str+='<span><a class="newsBasicCountDetailsDivEd" attr_editiontype="1" attr_benefitid ="2" attr_isdepartment = " " attr_partyids=" " attr_npids="'+result[i].organizationId+'">'+result[i].negativCountMain+'</a></span><small class="text-danger"> '+((result[i].negativCountMain/result[i].totalCount)*100).toFixed(2)+' %</small>';
							else
								str+='<span>'+result[i].negativCountMain+'</span><small class="text-danger"> 0 %</small>';
						str+='</td>';
					str+='</tr>';
					
					str1+='<tr>';
					str1+='<td>';
							str1+='<img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+result[i].organization+'.png" alt="cong logo" class="debatesPartyIcon"/><span><a class="newsBasicCountDetailsDivEd" attr_editiontype="2,3" attr_benefitid =" " attr_isdepartment = " " attr_partyids=" " attr_npids="'+result[i].organizationId+'">'+((result[i].positiveCountDist)+(result[i].negativCountDist))+'<small>('+result[i].count+')</small></a></span>';
						str1+='</td>';
						str1+='<td>';
							if(result[i].count > 0)
								str1+='<span><a class="newsBasicCountDetailsDivEd" attr_editiontype="2,3" attr_benefitid ="1" attr_isdepartment = " " attr_partyids="'+result[i].organizationId+'">'+result[i].positiveCountDist+'</a></span><small class="text-success"> '+((result[i].positiveCountDist/result[i].count)*100).toFixed(2)+' %</small>';
							else
								str1+='<span>'+result[i].positiveCountDist+'</span><small class="text-success"> 0 %</small>';
						str1+='</td>';
						str1+='<td>';
							if(result[i].count > 0)
								str1+='<span><a class="newsBasicCountDetailsDivEd" attr_editiontype="2,3" attr_benefitid ="2" attr_isdepartment = " " attr_partyids=" " attr_npids="'+result[i].organizationId+'">'+result[i].negativCountDist+'</a></span><small class="text-danger"> '+((result[i].negativCountDist/result[i].count)*100).toFixed(2)+' %</small>';
							else
								str1+='<span>'+result[i].negativCountDist+'</span><small class="text-danger"> 0 %</small>';
						str1+='</td>';
					str1+='</tr>';
			}
		}
		$("#mainPaperDivId").html(str);
		$("#distPaperDivId").html(str1);
	}
	
	
	$(document).on("click",".newsBasicCountDetailsDiv",function(e){
		//var edTypeIdStr = $(this).attr("attr_editiontype");
		//var bfIdStr   = $(this).attr("attr_benefitid");
		//var orgType = $(this).attr("attr_isdepartment");
		//var orgIdStr = $(this).attr("attr_partyids");
		
		 var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		} 
		
		window.open('showArticlesAction.action?edTypeIdStr='+$(this).attr("attr_editiontype")+'&bfIdStr='+$(this).attr("attr_benefitid")+'&orgType='+$(this).attr("attr_isdepartment")+'&orgIdStr='+$(this).attr("attr_partyids")+'&callFrom=fblk&stIdx=0&edIdx=6&levelId='+globalUserAccessLevelId+'&temp='+temp+'&state='+globalState+'&sdat='+currentFromDate+'&edat='+currentToDate+'&scops='+impactScopeIdsStr+'&npsStr='+newsPaperIdsStr+'','_blank');
		
		//getArticlesOfNewsBasicCounts(edTypeIdStr,bfIdStr,orgType,orgIdStr);
		//
		
	});
	
	$(document).on("click",".newsBasicCountDetailsDivEd",function(e){
		//var edTypeIdStr = $(this).attr("attr_editiontype");
		//var bfIdStr   = $(this).attr("attr_benefitid");
		//var orgType = $(this).attr("attr_isdepartment");
		//var orgIdStr = $(this).attr("attr_partyids");
		
		 var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		
		
		window.open('showArticlesAction.action?edTypeIdStr='+$(this).attr("attr_editiontype")+'&bfIdStr='+$(this).attr("attr_benefitid")+'&orgType='+$(this).attr("attr_isdepartment")+'&orgIdStr='+$(this).attr("attr_partyids")+'&callFrom=fblk&stIdx=0&edIdx=6&levelId='+globalUserAccessLevelId+'&temp='+temp+'&state='+globalState+'&sdat='+currentFromDate+'&edat='+currentToDate+'&scops='+impactScopeIdsStr+'&npsStr='+$(this).attr("attr_npids")+'','_blank');
		
		//getArticlesOfNewsBasicCounts(edTypeIdStr,bfIdStr,orgType,orgIdStr);
		//
		
	});	
		
	
	function getDetailedPartyCategoryActivities(searchType,isDepartmentValue){
		$("#categoryActiviesDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		//var searchType = "publication";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyCategoryActivities/"+startDate+"/"+endDate+"/"+globalState+"/"+globalUserAccessLevelId+"/"+temp+"/"+impactScopeIdsStr+"/"+searchType+"/"+newsPaperIdsStr+"/"+isDepartmentValue
			
			
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyCategoryActivities/"+startDate+"/"+endDate+"/"+globalState+"/"+globalUserAccessLevelId+"/"+temp+"/"+impactScopeIdsStr+"/"+searchType+"/"+newsPaperIdsStr+"/"+isDepartmentValue
			
		}).then(function(result){
			
			
			if(result != null && result.length > 0 && searchType == "location"){
				buildgetDetailedPartyCategoryLocationWiseDetailes(result);
			}else{
				buildgetDetailedPartyCategoryPublicationsWiseDetails(result);
			}
		});
	}
	
	function getGovernmentPartyCategoryActivities(searchType,isDepartmentValue){
		$("#govtCategoryActiviesDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		
		var newsPaperIdsStr="";
		if(newsPaperIdsGlob != null && newsPaperIdsGlob.length){
			for(var i in newsPaperIdsGlob){
				newsPaperIdsStr=i==0?newsPaperIdsGlob[i]:newsPaperIdsStr+","+newsPaperIdsGlob[i];
			}
		}
		var impactScopeIdsStr="";
		if(impactScopeIdsGlob != null && impactScopeIdsGlob.length){
			for(var i in impactScopeIdsGlob){
				impactScopeIdsStr=i==0?impactScopeIdsGlob[i]:impactScopeIdsStr+","+impactScopeIdsGlob[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		//var searchType = "publication";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyCategoryActivities/"+startDate+"/"+endDate+"/"+globalState+"/"+globalUserAccessLevelId+"/"+temp+"/"+impactScopeIdsStr+"/"+searchType+"/"+newsPaperIdsStr+"/"+isDepartmentValue
			
			
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyCategoryActivities/"+startDate+"/"+endDate+"/"+globalState+"/"+globalUserAccessLevelId+"/"+temp+"/"+impactScopeIdsStr+"/"+searchType+"/"+newsPaperIdsStr+"/"+isDepartmentValue
			
		}).then(function(result){
			
			
			if(result != null && result.length > 0 && searchType == "location"){
				buildGovernmentPartyCategoryLocationWiseDetailes(result);
			}else{
				buildGovernmentPartyCategoryPublicationsWiseDetails(result);
			}
		});
	}
	
	
	function buildgetDetailedPartyCategoryLocationWiseDetailes(result){
		$("#categoryActiviesDetailsDiv").html();
		var str ='';
		if(result !=null && result.length >0){
			var totalCount =0;
			var totalpositiveCount = 0;
			var totalnegativeCount = 0;
			var totalposprec =0;
			var totalnegPerc =0;
			for(var i in result){
				if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
					for(var j in result[i].coreDashBoardVOList){
						categoryName = result[i].coreDashBoardVOList[0].name;
							totalCount = totalCount+result[i].coreDashBoardVOList[j].count;
							totalpositiveCount = totalpositiveCount+result[i].coreDashBoardVOList[j].positiveCountDist;
							totalnegativeCount = totalnegativeCount+result[i].coreDashBoardVOList[j].negativCountDist;
							totalposprec = ((totalpositiveCount*100)/(totalCount)).toFixed(2);
							totalnegPerc = ((totalnegativeCount*100)/(totalCount)).toFixed(2)
						}
					}
						str+='<h5>'+result[i].coreDashBoardVOList[0].name+'</h5>';
						str+='<p>Total - <i>'+totalCount+'</i>: Positive Perc - <span title="'+totalpositiveCount+'" style="color:#7DDF7D" data-toggle="tooltip" data-placement="top" ><i>'+totalposprec+' %</i></span> : Negative Prec - <span title="'+totalnegativeCount+'"  style="color:#EC5752" data-toggle="tooltip" data-placement="top"><i>'+totalnegPerc+' %</i></span></p>';
						
				str+='<div id="categoryLocationwisegraph'+i+'" class="chartLiD" style="height:300px" ></div>';
			
			
		}
		
		}
		$("#categoryActiviesDetailsDiv").html(str);
			if(result !=null && result.length >0){
				for(var i in result){
					
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						var catagoryDistrictName=[];
						var categoryPostivePercArray = [];
						var categoryNegativePercArray = [];
							
						for(var j in result[i].coreDashBoardVOList){
							
							catagoryDistrictName.push(result[i].coreDashBoardVOList[j].organization);
							
							
							categoryPostivePercArray.push({"y":result[i].coreDashBoardVOList[j].positivePerc,"count":result[i].coreDashBoardVOList[j].positiveCountDist});
							categoryNegativePercArray.push({"y":result[i].coreDashBoardVOList[j].negativePerc,"count":result[i].coreDashBoardVOList[j].negativCountDist});
						}
							if(catagoryDistrictName.length !=0 && categoryPostivePercArray.length !=0 &&  categoryNegativePercArray.length !=0){
								$(function () {
									$('#categoryLocationwisegraph'+i+'').highcharts({
										 colors: ['#64C664','#D33E39'],
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
												 categories: catagoryDistrictName,
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
											pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}% - {point.count}</b> <br/>',
											shared: true
										},
										legend: {
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												stacking: 'percent',
												dataLabels:{
													enabled: true,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.y,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											name: 'Positive',
											data: categoryPostivePercArray
										}, {
											name: 'Negative',
											data: categoryNegativePercArray
										}]
									});
								});
							}else{
								$('#categoryLocationwisegraph'+i+''+j+'').html("No Data Available");
								$('#categoryLocationwisegraph'+i+''+j+'').css("height","20px");
							}
						}
					
				}
			}
				
			else{
				$("#categoryActiviesDetailsDiv").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
	}
	
	function buildgetDetailedPartyCategoryPublicationsWiseDetails(result){
		$("#categoryActiviesDetailsDiv").html();
		var str ='';
		if(result !=null && result.length >0){
			var totalCount =0;
			var totalpositiveCount = 0;
			var totalnegativeCount = 0;
			var totalposprec =0;
			var totalnegPerc =0;
			for(var i in result){
				var categoryName;
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						str+='<div class="col-md-12 col-xs-12 col-ms-12">';
							
						for(var j in result[i].coreDashBoardVOList){
							categoryName = result[i].coreDashBoardVOList[0].name;
							totalCount = totalCount+result[i].coreDashBoardVOList[j].count;
							totalpositiveCount = totalpositiveCount+result[i].coreDashBoardVOList[j].positiveCountDist;
							totalnegativeCount = totalnegativeCount+result[i].coreDashBoardVOList[j].negativCountDist;
							totalposprec = ((totalpositiveCount*100)/(totalCount)).toFixed(2);
							totalnegPerc = ((totalnegativeCount*100)/(totalCount)).toFixed(2)
						}
						str+='<h5>'+categoryName+'</h5>';
						str+='<p>Total - <i>'+totalCount+'</i>: Positive Perc - <span title="'+totalpositiveCount+'" style="color:#7DDF7D" data-toggle="tooltip" data-placement="top" ><i>'+totalposprec+' %</i></span> : Negative Prec - <span title="'+totalnegativeCount+'"  style="color:#EC5752" data-toggle="tooltip" data-placement="top"><i>'+totalnegPerc+' %</i></span></p>';
				
						str+='<ul class="categoryPublicationWiseSlickApply">';
						for(var j in result[i].coreDashBoardVOList){
							str+='<li><div id="categoryPublicationwisegraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
						}
						str+='</ul>';
						str+='</div>';
					}
			}
		}
		$("#categoryActiviesDetailsDiv").html(str);
		if(result !=null && result.length >0){
				for(var i in result){
					
							
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						for(var j in result[i].coreDashBoardVOList){
							var partyName = [];
							var positivePercArray =[];
							var negativePercArray =[];
							
							
									partyName.push(result[i].coreDashBoardVOList[j].organization);
									var papername = result[i].coreDashBoardVOList[j].organization;
									positivePercArray.push({"y":result[i].coreDashBoardVOList[j].positivePerc,"count":result[i].coreDashBoardVOList[j].positiveCountDist})
									negativePercArray.push({"y":result[i].coreDashBoardVOList[j].negativePerc,"count":result[i].coreDashBoardVOList[j].negativCountDist})
						
							if(partyName.length !=0 && positivePercArray.length !=0 && negativePercArray.length !=0){
								$(function () {
									$('#categoryPublicationwisegraph'+i+''+j+'').highcharts({
										 colors: ['#64C664','#D33E39'],
										chart: {
											type: 'column'
										},
										title: {
											 useHTML: true,
											text: '<img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+papername+'.png" style="width:52px;" alt="tdp icon"/>',
											style: {
													fontSize: '16px',
													fontFamily: '"Helvetica Neue",Helvetica,Arial,sans-serif',
													textTransform: "uppercase"
													
											}
										},
									   
										xAxis: {
											 min: 0,
												 gridLineWidth: 0,
												 minorGridLineWidth: 0,
												 categories: partyName,
											labels: {
													//rotation: -45,
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
											pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}% - {point.count}</b> <br/>',
											shared: true
										},
										legend: {
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												//stacking: 'percent',
												dataLabels:{
													enabled: true,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.y,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											name: 'Positive',
											data: positivePercArray
										}, {
											name: 'Negative',
											data: negativePercArray
										}]
									});
								});
							}else{
								$('#categoryPublicationwisegraph'+i+''+j+'').html("No Data Available");
								$('#categoryPublicationwisegraph'+i+''+j+'').css("height","20px");
							}
							
							
						}
					}
						
				}
			}
				
			else{
				$("#categoryActiviesDetailsDiv").html("No Data Available");
			}
			$(".categoryPublicationWiseSlickApply").slick({
					 slide: 'li',
					 slidesToShow: 4,
					 slidesToScroll: 3,
					 infinite: false,
					 swipeToSlide:false,
					 swipe:false,
					 touchMove:false
				}); 
		
	}
	
	function buildGovernmentPartyCategoryLocationWiseDetailes(result){
		$("#govtCategoryActiviesDetailsDiv").html();
		var str ='';
		if(result !=null && result.length >0){
			var totalCount =0;
			var totalpositiveCount = 0;
			var totalnegativeCount = 0;
			var totalposprec =0;
			var totalnegPerc =0;
			for(var i in result){
				str+='<h5>'+result[i].coreDashBoardVOList[0].name+'</h5>';
				if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
					for(var j in result[i].coreDashBoardVOList){
						categoryName = result[i].coreDashBoardVOList[0].name;
							totalCount = totalCount+result[i].coreDashBoardVOList[j].count;
							totalpositiveCount = totalpositiveCount+result[i].coreDashBoardVOList[j].positiveCountDist;
							totalnegativeCount = totalnegativeCount+result[i].coreDashBoardVOList[j].negativCountDist;
							totalposprec = ((totalpositiveCount*100)/(totalCount)).toFixed(2);
							totalnegPerc = ((totalnegativeCount*100)/(totalCount)).toFixed(2)
					}
				}
						
						str+='<p>Total - <i>'+totalCount+'</i>: Positive Perc - <span title="'+totalpositiveCount+'" style="color:#7DDF7D" data-toggle="tooltip" data-placement="top" ><i>'+totalposprec+' %</i></span> : Negative Prec - <span title="'+totalnegativeCount+'"  style="color:#EC5752" data-toggle="tooltip" data-placement="top"><i>'+totalnegPerc+' %</i></span></p>';
						
				str+='<div id="govtCategoryLocationwisegraph'+i+'" class="chartLiD" style="height:300px" ></div>';
			
			
		}
		
		}
		$("#govtCategoryActiviesDetailsDiv").html(str);
			if(result !=null && result.length >0){
				for(var i in result){
					
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						var catagoryDistrictName=[];
						var categoryPostivePercArray = [];
						var categoryNegativePercArray = [];
							
						for(var j in result[i].coreDashBoardVOList){
							
							catagoryDistrictName.push(result[i].coreDashBoardVOList[j].organization);
							
							
							categoryPostivePercArray.push({"y":result[i].coreDashBoardVOList[j].positivePerc,"count":result[i].coreDashBoardVOList[j].positiveCountDist});
							categoryNegativePercArray.push({"y":result[i].coreDashBoardVOList[j].negativePerc,"count":result[i].coreDashBoardVOList[j].negativCountDist});
						}
							if(catagoryDistrictName.length !=0 && categoryPostivePercArray.length !=0 &&  categoryNegativePercArray.length !=0){
								$(function () {
									$('#govtCategoryLocationwisegraph'+i+'').highcharts({
										 colors: ['#64C664','#D33E39'],
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
												 categories: catagoryDistrictName,
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
											pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}% - {point.count}</b> <br/>',
											shared: true
										},
										legend: {
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												stacking: 'percent',
												dataLabels:{
													enabled: true,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.y,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											name: 'Positive',
											data: categoryPostivePercArray
										}, {
											name: 'Negative',
											data: categoryNegativePercArray
										}]
									});
								});
							}else{
								$('#govtCategoryLocationwisegraph'+i+''+j+'').html("No Data Available");
								$('#govtCategoryLocationwisegraph'+i+''+j+'').css("height","20px");
							}
						}
					
				}
			}
				
			else{
				$("#govtCategoryActiviesDetailsDiv").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
	}
	
	function buildGovernmentPartyCategoryPublicationsWiseDetails(result){
		$("#govtCategoryActiviesDetailsDiv").html();
		var str ='';
		if(result !=null && result.length >0){
			var totalCount =0;
			var totalpositiveCount = 0;
			var totalnegativeCount = 0;
			var totalposprec =0;
			var totalnegPerc =0;
			for(var i in result){
				var categoryName;
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						str+='<div class="col-md-12 col-xs-12 col-ms-12">';
							
						for(var j in result[i].coreDashBoardVOList){
							categoryName = result[i].coreDashBoardVOList[0].name;
							totalCount = totalCount+result[i].coreDashBoardVOList[j].count;
							totalpositiveCount = totalpositiveCount+result[i].coreDashBoardVOList[j].positiveCountDist;
							totalnegativeCount = totalnegativeCount+result[i].coreDashBoardVOList[j].negativCountDist;
							totalposprec = ((totalpositiveCount*100)/(totalCount)).toFixed(2);
							totalnegPerc = ((totalnegativeCount*100)/(totalCount)).toFixed(2)
						}
						str+='<h5>'+categoryName+'</h5>';
						str+='<p>Total - <i>'+totalCount+'</i>: Positive Perc - <span title="'+totalpositiveCount+'" style="color:#7DDF7D" data-toggle="tooltip" data-placement="top" ><i>'+totalposprec+' %</i></span> : Negative Prec - <span title="'+totalnegativeCount+'"  style="color:#EC5752" data-toggle="tooltip" data-placement="top"><i>'+totalnegPerc+' %</i></span></p>';
				
						str+='<ul class="categoryPublicationWiseSlickApply">';
						for(var j in result[i].coreDashBoardVOList){
							str+='<li><div id="govtCategoryPublicationwisegraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
						}
						str+='</ul>';
						str+='</div>';
					}
			}
		}
		$("#govtCategoryActiviesDetailsDiv").html(str);
		if(result !=null && result.length >0){
				for(var i in result){
					
							
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						for(var j in result[i].coreDashBoardVOList){
							var partyName = [];
							var positivePercArray =[];
							var negativePercArray =[];
							
							
									partyName.push(result[i].coreDashBoardVOList[j].organization);
									var papername = result[i].coreDashBoardVOList[j].organization;
									positivePercArray.push({"y":result[i].coreDashBoardVOList[j].positivePerc,"count":result[i].coreDashBoardVOList[j].positiveCountDist})
									negativePercArray.push({"y":result[i].coreDashBoardVOList[j].negativePerc,"count":result[i].coreDashBoardVOList[j].negativCountDist})
						
							if(partyName.length !=0 && positivePercArray.length !=0 && negativePercArray.length !=0){
								$(function () {
									$('#govtCategoryPublicationwisegraph'+i+''+j+'').highcharts({
										 colors: ['#64C664','#D33E39'],
										chart: {
											type: 'column'
										},
										title: {
											 useHTML: true,
											text: '<img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+papername+'.png" style="width:52px;" alt="tdp icon"/>',
											style: {
													fontSize: '16px',
													fontFamily: '"Helvetica Neue",Helvetica,Arial,sans-serif',
													textTransform: "uppercase"
													
											}
										},
									   
										xAxis: {
											 min: 0,
												 gridLineWidth: 0,
												 minorGridLineWidth: 0,
												 categories: partyName,
											labels: {
													//rotation: -45,
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
											pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}% - {point.count}</b> <br/>',
											shared: true
										},
										legend: {
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												//stacking: 'percent',
												dataLabels:{
													enabled: true,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.y,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											name: 'Positive',
											data: positivePercArray
										}, {
											name: 'Negative',
											data: negativePercArray
										}]
									});
								});
							}else{
								$('#govtCategoryPublicationwisegraph'+i+''+j+'').html("No Data Available");
								$('#govtCategoryPublicationwisegraph'+i+''+j+'').css("height","20px");
							}
							
							
						}
					}
						
				}
			}
				
			else{
				$("#govtCategoryActiviesDetailsDiv").html("No Data Available");
			}
			$(".categoryPublicationWiseSlickApply").slick({
					 slide: 'li',
					 slidesToShow: 4,
					 slidesToScroll: 3,
					 infinite: false,
					 swipeToSlide:false,
					 swipe:false,
					 touchMove:false
				}); 
		
	}
	
	  $(document).on("click",".datesClass",function(){
		var type = $(this).attr("attr_type");
		if(type == "currentMonth"){
			currentFromDate = moment().format('DD-MM-YYYY');
			currentToDate = moment().format('DD-MM-YYYY');
			$("#currentViewing").html(" TODAY ( "+currentFromDate+" )");
			commonNewsBasicCalls();
		}else if(type == "lastMonth"){
			currentFromDate = moment().subtract(1, 'month').startOf('month').format('DD-MM-YYYY');
			currentToDate = moment().subtract(1, 'month').endOf('month').format('DD-MM-YYYY');
			$("#currentViewing").html("Last Month("+currentFromDate+" to "+currentToDate+")");
			commonNewsBasicCalls();
		}
		
		$("#dateRangeIdForNews").daterangepicker({
			opens: 'left',
			startDate: currentFromDate,
			endDate: currentToDate,
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
		$('#dateRangeIdForNews').on('apply.daterangepicker', function(ev, picker) {
			currentFromDate = picker.startDate.format('DD-MM-YYYY');
			currentToDate = picker.endDate.format('DD-MM-YYYY');
			if(picker.chosenLabel == "Today"){
				$("#currentViewing").html(" TODAY ( "+currentFromDate+" )");
			}else{
				$("#currentViewing").html(picker.chosenLabel+"("+currentFromDate+" to "+currentToDate+")");
			}
			commonNewsBasicCalls();
			
		});
	
		
	});
	//getOverAllCommitteeWiseMembersCounts();
	function getOverAllCommitteeWiseMembersCounts(){
		
		var jsObj={
			activityMemberId : 44 ,
				userTypeId : 2,
				stateId:1
		}	
		$.ajax({
		 type: "POST",
		 url: "getUserTypeWiseKaizalaCommitteeMemberDetailsCntAction.action",
		 data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	}