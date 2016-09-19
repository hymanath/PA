	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));
	
	var currentFromDate = moment().format("DD-MM-YYYY");
	var currentToDate = moment().format("DD-MM-YYYY");
	var newsPaperIdsGlob = [1,2,3,8];
	
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
			$("#currentViewing").html(picker.chosenLabel+" ( "+currentFromDate+" to "+currentToDate+" )");
		}
		
		getNewsBasicCounts();
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
			
		
	  
	});
	
	$(document).on("click",".settingsIconNews",function(){
		$(this).closest(".newsBlock").find(".newsBlockDropDown").toggle();
	});
	$(document).on("click",".newsIconExpand",function(){
		$(".dateRangePickerClsForNews").toggleClass("hide");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".newsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".newsHiddenBlock,.morenewsBlocksIcon").toggle();
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			getUserTypeWiseNewsCounts(1);
		}else{
			$(".newsHiddenMoreBlock").hide();
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
		}
	});
	
	$(document).on("click",".morenewsBlocksIcon",function(){
		$(".newsHiddenMoreBlock").toggle();
		//getDetailedPartyMainEditionsOverview();
		getDetailedPartyDistrictEditionsOverview();
		setcolorsForStatus();
		getDetailedPartyNewsTypeAnalysis();
		getDetailedPartyPartyVsPublications("party");
	});
	
	$(document).on("click",".partyDistrictWiseDiv",function(){
		//$("#publicationWiseDetailsDiv").hide();
		//$("#partyWiseDetailsDiv").show();
		getDetailedPartyPartyVsPublications($(this).attr("attr_search_type"));
	});
	
	/* $(document).on("click",".publictionWiseDiv",function(){
		//$("#partyWiseDetailsDiv").hide();
		//$("#publicationWiseDetailsDiv").show();
		getDetailedPartyPartyVsPublications("publication");
	}); */
	
	$(document).on("click","#detailedPartyLiId",function(){
		//getDetailedPartyMainEditionsOverview();
		getDetailedPartyDistrictEditionsOverview();
		getDetailedPartyNewsTypeAnalysis();
		getDetailedPartyPartyVsPublications("party");
	});
	
	function getNewsBasicCounts(){
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		var state = globalState;
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
		}).then(function(result){
			if(result != null && result.length > 0){
				$("#tdpMainTotal").html(result[0].positiveCountMain+result[0].negativCountMain);
				$("#tdpMainPositive").html(result[0].positiveCountMain);
				$("#tdpMainNegative").html(result[0].negativCountMain);
				if((result[0].positiveCountMain+result[0].negativCountMain) > 0){
					$("#tdpMainPositivePercent").html(" "+((result[0].positiveCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+" %");
					$("#tdpMainNegativePercent").html(" "+((result[0].negativCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+" %");
				}
				else{
					$("#tdpMainPositivePercent").html(" 0.0 %");
					$("#tdpMainNegativePercent").html(" 0.0 %");
				}
				
				$("#tdpDistTotal").html(result[0].positiveCountDist+result[0].negativCountDist);				
				$("#tdpDistPositive").html(result[0].positiveCountDist);
				$("#tdpDistNegative").html(result[0].negativCountDist);
				if((result[0].positiveCountDist+result[0].negativCountDist) > 0){
					$("#tdpDistPositivePercent").html(" "+((result[0].positiveCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+" %");
					$("#tdpDistNegativePercent").html(" "+((result[0].negativCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+" %");
				}
				else{
					$("#tdpDistPositivePercent").html(" 0.0 %");
					$("#tdpDistNegativePercent").html(" 0.0 %");
				}
				
				var oppTotal=(result[1].positiveCountMain+result[1].negativCountMain)+(result[2].positiveCountMain+result[2].negativCountMain)+(result[3].positiveCountMain+result[3].negativCountMain);
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
				
				var ysrcMainTotal = result[1].positiveCountMain+result[1].negativCountMain;
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
				
				var incMainTotal = result[2].positiveCountMain+result[2].negativCountMain;
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
				 var bjpMainTotal = result[3].positiveCountMain+result[3].negativCountMain;
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
				 
				 var oppDistTotal=(result[1].positiveCountDist+result[1].negativCountDist)+(result[2].positiveCountDist+result[2].negativCountDist)+(result[3].positiveCountDist+result[3].negativCountDist);
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
				 
				 var ysrcDistTotal = result[1].positiveCountDist+result[1].negativCountDist;
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
				
				var incDistTotal = result[2].positiveCountDist+result[2].negativCountDist;
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
				
				var bjpDistTotal = result[3].positiveCountDist+result[3].negativCountDist;
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
				 
				 var govtMainTotal = result[4].positiveCountMain+result[4].negativCountMain;
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
				 
				var govtDistTotal = result[4].positiveCountDist+result[4].negativCountDist;
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
			}
		});
	}
	
	//var globalUserWiseMemberRslt;
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
				npIdsArr : newsPaperIdsGlob
			}
		
			$.ajax({
				type : 'POST',
				url : 'getUserTypeWiseNewsCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html('');
				buildgetUserTypeWiseNewsForTopFiveStrongResults(result,benefitId);
				//globalUserWiseMemberRslt = result;
			});
	}
	
	
	
	function getDetailedPartyMainEditionsOverview(){
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
			//url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
		}).then(function(result){
			$("#newsTypeAnalysisDiv").html();
			if(result != null && result.length > 0){
				buildDetailedPartyNewsTypeAnalysis(result);
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		//var searchType = "publication";
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr
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
						
						//if(result[i].subList[j].completedPerc !=null && result[i].subList[j].completedPerc >0){
							districtWisePositiveCountArray.push(result[i].coreDashBoardVOList[j].positiveCountMain);
						//}
						//if(result[i].subList[j].startedPerc !=null && result[i].subList[j].startedPerc >0){
							districtWiseNegativeCountArray.push(result[i].coreDashBoardVOList[j].negativCountMain);
						//}
						
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
$(document).on("click",".notesIconNews",function(){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
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

$(document).on("click",".viewsLiClass",function(){
	$(".viewsLiClass").removeClass("active");
	$(this).addClass("active");
	
	$(".mainBuildingDivClass").hide();
	var divId = $(this).attr("attr_div_id");
	$("#"+divId).show();
	$("#"+divId).removeClass("active");
	
});



$(document).on("click","#comparisonPartyLiId",function(){
	$("#partyWiseComparisionBlock").html('');
	setcolorsForStatus();
	getChildUserTypesByItsParentUserType1();
});

$(document).on("click","#comparisonGovernmentLiId",function(){
	$("#ministerSubLevelDetailsDiv").html('');
	getComparisonGovtMinistriesInfo();
});

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

	function buildChildUserTypesByItsParentUserType(result){
		var str ='';
		
		if(result != null && result.length > 0){
			str+='<div class="col-xs-12 col-sm-12 col-md-12">';
				str+='<ul class="detailedPartySubUl">';
				for(var i in result){
					str+='<li attr_usertypeid="'+result[i].shortName+'" class="detailedPartySubLi">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
				}
				str+='</ul>';
			str+='</div>';
			
			getPartyComparisonChildUserTypeMembers(result[0].shortName);
		}
		$("#userTypeStrId").html(str);
		$(".detailedPartySubUl li:first-child").addClass("active");
		
	}	
	
	function getPartyComparisonChildUserTypeMembers(childUserTypeId){
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
				npIdsArr:newsPaperIdsGlob
			}
		
			$.ajax({
				type : 'POST',
				url : 'getPartyComparisonChildUserTypeMembersAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#partyWiseComparisionBlock").html('');
				buildgetPartyCompareSubLevelMemberDetails(result);
			});
		
	}
	
	function buildDetailedPartyNewsTypeAnalysis(result){
		var str='';
		if(result != null && result.length > 0){
			var str='';
				
			for(var i in result){
				
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-ms-12"><h5 class="text-capital">'+result[i].name+'</h5></div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-4" style="border-right:1px solid #ddd">';
						str+='<div id="newsTypeAnalysisPieChart'+i+'" style="height:165px;width:100%;"></div>';
						str+='<div class="row">';
							str+='<div class="col-xs-12 col-sm-12 col-md-12">';
								str+='<ul class="list-inline">';
								for(var j in result[i].coreDashBoardVOList){
									var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
									  str+='<li style="color:'+color+'">'+result[i].coreDashBoardVOList[j].organization+' :'+result[i].coreDashBoardVOList[j].positivePerc+'%('+result[i].coreDashBoardVOList[j].count+')</li> &nbsp;&nbsp;';
									
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
					
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						for (var j in result[i].coreDashBoardVOList){
							 PartyCountPerc = result[i].coreDashBoardVOList[j].count;
							 partyName = result[i].coreDashBoardVOList[j].organization;
							
							var obj = {
								name: partyName,
								y:PartyCountPerc
								
							}
							
							partyNameAndCountArray.push(obj);
						}
					}
				
					if(result[i].coreDashBoardVOList1 !=null && result[i].coreDashBoardVOList1.length >0){
						for (var j in result[i].coreDashBoardVOList1){
							districtNameArray.push(result[i].coreDashBoardVOList1[j].districtName)
							tdpPercArray.push(result[i].coreDashBoardVOList1[j].tdpPerc)
							ysrcPercArray.push(result[i].coreDashBoardVOList1[j].ysrcPerc)
							incPercArray.push(result[i].coreDashBoardVOList1[j].incPerc)
							bjpPercArray.push(result[i].coreDashBoardVOList1[j].bjpPerc)
							
							
						}
					}
						
					$(function () {
						if(partyNameAndCountArray.length !=0){
							$('#newsTypeAnalysisPieChart'+i).highcharts({
								colors: ['#FD9832','#3D9834','#FFCB00','#005DB0'],
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
					}else{
						$('#newsTypeAnalysisBarChart'+i).html("No Data Available");
						$('#newsTypeAnalysisBarChart'+i).css("height","10px")
					}
					});
				}
			}
	}
	
	$(document).on("click",".childUserTypesLiClass",function(){
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid");
		getPartyCompareSubLevelMemberDetails(activityMemberId,userTypeId);//sandeep
	});
	
	function getPartyCompareSubLevelMemberDetails(activityMemberId,userTypeId){
		var jsObj={
				activityMemberId : activityMemberId ,
				userTypeId : userTypeId,
				state:globalState,
				startDate:currentFromDate,
				endDate:currentToDate,
				npIdsArr : newsPaperIdsGlob
			}
		
			$.ajax({
				type : 'POST',
				url : 'getPartyCompareSubLevelMemberDetailsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				
			});
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
			$("#partyWiseComparisionBlock").html('');
			getPartyComparisonChildUserTypeMembers($(this).attr("attr_usertypeid"));
		}else{
			$("#partyWiseComparisionBlock").html('');
		}
		
		
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
	$(document).on("click",".impactCheckCls",function(){
		$(".impactCheckCls").prop('checked', false);
		$(this).prop('checked', true);
	});
    function getRescentArticleTime(){
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getRescentArticleTime/"
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getRescentArticleTime/"
		}).then(function(result){
			if(result != null){
				$("#lastUpdatedId").html("Last updated : "+ result[0].organization);
			}
		});
	}    
	setInterval(function() {
		getRescentArticleTime();
	}, 60 * 1000);
	
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
			 touchMove:false
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
									
									positivePercArray.push(result[i].coreDashBoardVOList[j].positivePerc)
									negativePercArray.push(result[i].coreDashBoardVOList[j].negativePerc)
						
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
	
	function buildgetPartyCompareSubLevelMemberDetails(result){
		$("#partyWiseComparisionBlock").html('');
		var str='';
		if(result !=null && result.length >0){
			
			str+='<ul class="list-inline slickPanelSlider">';
			var rankVar =0;
			for(var i in result){
				rankVar =rankVar+1;
				str+='<li class="childUserTypesLiClass" attr_activitymemberid="'+result[i].activityMemberId+'" attr_usertypeid="'+result[i].userTypeId+'">';
					str+='<div class="panel panel-default panelSlick">';
						str+='<div class="panel-heading" style="background-color: rgb(237, 238, 240);">';
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
														str+='<li class="newsCompBlockAlign" >- +ve</li>';
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
		}else{
			$("#partyWiseComparisionBlock").html("No Data Available");
		}
			$("#partyWiseComparisionBlock").html(str);
			
			$(".slickPanelSlider").slick({
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
						slidesToShow: 2,
						slidesToScroll: 2
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
	}
	
	
	function getAllNewsPapers(){
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getAllNewsPapers/"+globalState
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getAllNewsPapers/"+globalState
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
		
		if(newsPaperIdsGlob == null || newsPaperIdsGlob.length == 0){
			alert("Please Select Atleast One NewsPaper");
			return;
		}else{
			getNewsBasicCounts();
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
	});
	
	
	$(document).on("click","#detailedGovernmentLiId",function(){
		$("#problemsDetailedOverviewSubLevel").html('');
		getDetailedGovtDepartmentWiseDistrictsOverview();
		getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(7,'');
		getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(0,'');
		getDetailedGovernamentTrendingTrackedIssues();
		getDetailedGovernmentDistrictWiseArticleRelatedToProblem();
	});
	
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		//var propertyId=0;
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyId
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisOfActionImmediatelyProblems/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyId
		}).then(function(result){
			if(propertyId == 7){
				$("#problemsDetailedOverview").html('');
			}else if(propertyId == 0){
				$("#overAllAnalysisDetailsBlock").html('');
			}
			buildgetDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(result,propertyId,propertyName);
			
		});
	}
	
	
	
	
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
	
	var startDate=currentFromDate,endDate=currentToDate;
	
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovtDepartmentWiseDistrictsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovtDepartmentWiseDistrictsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
	}).then(function(result){
		$("#districtWiseNewsReportGovtDetailed").html('');
		buildgetDetailedGovtDepartmentWiseDistrictsOverview(result);
	});
}
	
	function buildgetDetailedGovtDepartmentWiseDistrictsOverview(result){
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
				str+='<h4>'+departmentName+'</h4>';
				str+='<div id="districtWiseNewsGovtDetailed'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
									
		}
		$("#districtWiseNewsReportGovtDetailed").html(str);
		
		
	if(result != null && result.length > 0){
		
		for(var i in result){
			
			var govtDetailedDistrictNamesArray =[];
			var govtDetailedDistrictWisePositiveCountArray = [];
			var govtDetailedDistrictWiseNegativeCountArray = [];
			
			if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length > 0){
				
				for(var j in result[i].coreDashBoardVOList){
					
						govtDetailedDistrictNamesArray.push(result[i].coreDashBoardVOList[j].districtName);
						
						//if(result[i].subList[j].completedPerc !=null && result[i].subList[j].completedPerc >0){
							govtDetailedDistrictWisePositiveCountArray.push(result[i].coreDashBoardVOList[j].positiveCountDist);
						//}
						//if(result[i].subList[j].startedPerc !=null && result[i].subList[j].startedPerc >0){
							govtDetailedDistrictWiseNegativeCountArray.push(result[i].coreDashBoardVOList[j].negativCountDist);
						//}
						
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovernamentTrendingTrackedIssues/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovernamentTrendingTrackedIssues/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr
		}).then(function(result){
			buildDetailedGovernamentTrendingTrackedIssues(result);
		});
	}
	
	
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
									str+='<li >'+result[i].name+'  <span class="pull-right">'+result[i].count+'</span></li>';
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
								str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
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
							str+='<div class="col-md-5 col-xs-12 col-sm-4">';
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
					problemDeptPostivePercArray.push(result[i].positivePerc)
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
										str+='<li >'+result[i].name+'  <span class="pull-right">'+result[i].count+'</span></li>';
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
	
	function getComparisonGovtMinistriesInfo(){
		$("#comparisonGovtMinistriesInfo").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getComparisonGovtMinistriesInfo/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonGovtMinistriesInfo/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+""
		}).then(function(result){
			//$("#comparisonGovtMinistriesInfo").html('');
			buildComparisonGovtMinistriesInfo(result);
		});
	}
	
	function getComparisonGovernamentTrendingTrackedIssues(){
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getComparisonGovernamentTrendingTrackedIssues/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr+"/"+candidateId+"/"+departmentIdsStr
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisonGovernamentTrendingTrackedIssues/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+candidateId+"/"+departmentIdsStr
		}).then(function(result){
		
		});
	}
	function getAllDepartmentEditionsWiseDetails(departmentIdsStr,ministerName){
		$("#ministerSubLevelDetailsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
		var startDate='01-01-2016',endDate='31-09-2016';
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getAllDepartmentEditionsWiseDetails/"+startDate+"/"+endDate+"/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+newsPaperIdsStr+"/"+departmentIdsStr+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getAllDepartmentEditionsWiseDetails/"+startDate+"/"+endDate+"/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+newsPaperIdsStr+"/"+departmentIdsStr+""
		}).then(function(result){
			buildgetAllDepartmentEditionsWiseDetails(result,ministerName);
		});
	}
	
	function buildDetailedGovernamentTrendingTrackedIssues(result)
	{
		$("#topTrendingTracked").html(' ');
		if(result != null && result.length > 0){
			var str='';
			var countVar =0;
			for(var i in result){
				countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				var totalMainCnt = result[i].positiveCountMain + result[i].negativCountMain
				var totalDistCnt = result[i].positiveCountDist + result[i].negativCountDist
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<h4 class="panel-title">'+result[i].name+'</h4>';
						str+='<div class="row m_top10">';
							str+='<div class="col-md-4 col-xs-12 col-sm-6">';
								str+='<table class="table table-condensed tableNews ">';
									str+='<tbody>';
									str+='<tr class="bg_ED">';
										str+='<td>';
											str+='<p class="text-capital">Main Edition</p>';
											str+='<p>'+totalMainCnt+'</p>';
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
											str+='<p>'+totalDistCnt+'</p>';
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
		
		var str = ' ';
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
				str+='';
				
				for(var j in result[i].coreDashBoardVOList){
					
						districtNamesArray.push(result[i].coreDashBoardVOList[j].name);
						districtWisePositiveCountArray.push(result[i].coreDashBoardVOList[j].positiveCountDist);
						districtWiseNegativeCountArray.push(result[i].coreDashBoardVOList[j].negativCountDist);
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
	
	
	function buildComparisonGovtMinistriesInfo(result)
	{
		$("#comparisonGovtMinistriesInfo").html('');
		var str='';
		if(result !=null && result.length >0){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<ul class="list-inline slickPanelSliderGovtCom">';
				for(var i in result){
					str+='<li class="comparisonGovtMinistriesInfoSubLevel" id="comparisonGovtMinistriesInfoSubLevel'+i+'" style="cursor:pointer;">';
						str+='<div class="panel panel-default panelSlick">';
							str+='<div class="panel-heading">';
								str+='<h4 class="panel-title">'+result[i].name+'</h4>';
								str+='<span class="count">'+(parseInt(i)+1)+'</span>';
							str+='</div>';
							str+='<div class="panel-body" style="background-color:#fff;">';
								str+='<h4 class="text-capital">MINISTER</h4>';
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
		
		$(".slickPanelSliderGovtCom").slick({
			 slide: 'li',
			 slidesToShow: 3,
			 slidesToScroll: 3,
			 infinite: false
				 
		});
	}
	
	$(document).on("click",".comparisonGovtMinistriesInfoSubLevel",function(){
		getAllDepartmentEditionsWiseDetails($(this).attr("attr_department_idsstr"),$(this).attr("attr_ministername"));
	});
		
	function buildgetAllDepartmentEditionsWiseDetails(result,ministerName){
		$("#ministerSubLevelDetailsDiv").html('');
		var str='';
		if(result !=null && result.length >0){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_15">';
					str+='<h4><span  class="text-capital">'+ministerName+'</span></h4>';
					str+='<table class="table table-condensed tableHoverLevels m_top20">';
						str+='<thead class="bg_D8 text-capital">';
							str+='<tr>';
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
							 str+='<tr>';
								str+='<td>01</td>';
								str+='<td>'+result[i].organization+'</td>';
								
								str+='<td>'+result[i].neutralCountMain+'</td>';
								str+='<td>'+result[i].positiveCountMain+'</td>';
								str+='<td>'+result[i].positivePerc+'</td>';
								str+='<td>'+result[i].negativCountMain+'</td>';
								str+='<td>'+result[i].negativePerc+'</td>';
								
								str+='<td>'+result[i].neutralCountDist+'</td>';
								str+='<td>'+result[i].positiveCountDist+'</td>';
								str+='<td>'+result[i].positiveDistPerc+'</td>';
								str+='<td>'+result[i].negativCountDist+'</td>';
								str+='<td>'+result[i].negativeDistPerc+'</td>';
							str+='</tr>'; 
						}
							
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		}
		
		$("#ministerSubLevelDetailsDiv").html(str);
	}
	
	function getCompareGovernamentDistrictWiseArticleRelatedToProblem(){
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
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getCompareGovernamentDistrictWiseArticleRelatedToProblem/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+newPaperIdArr+"/"+state+"/"+startDate+"/"+endDate+"/"+status+"/"+organizationIdStr+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getCompareGovernamentDistrictWiseArticleRelatedToProblem/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+newPaperIdArr+"/"+state+"/"+startDate+"/"+endDate+"/"+status+"/"+organizationIdStr+""
		}).then(function(result){
			
		});
	}
	
	function getCompareGovtCandidateDepartmentsWiseDistrictOverview(){
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getCompareGovtCandidateDepartmentsWiseDistrictOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+orgIdStr
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getCompareGovtCandidateDepartmentsWiseDistrictOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+orgIdStr
		}).then(function(result){
			
		});
	}
	function getDetailedGovernmentDistrictWiseArticleRelatedToProblem()
	{
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
		if(globalUserAccessLevelId==2){
			$("#districtWiseArticleRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
			$.ajax({
				//url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other"
				url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other"      
			}).then(function(result){
				buildDistrictWiseArticleRelatedToProblem(result)
			});
			
			$("#stateWiseArticleRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
			$.ajax({
				//url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/state" 
				url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/state"      
			}).then(function(result){
				buildStateWiseArticleRelatedToProblem(result);
			});
			
		}else{     
			$("#districtWiseArticleRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
			$.ajax({
				//url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other" 
				url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWiseArticleRelatedToProblem/"+globalUserAccessLevelId+"/"+temp+"/"+newsPaperIdsStr+"/"+state+"/"+startDate+"/"+endDate+"/other"    
			}).then(function(result){
				buildDistrictWiseArticleRelatedToProblem(result);
			});
			
		}
		
		
	}
	function buildStateWiseArticleRelatedToProblem(result)
	{
		var str='';
		str+='<div class="row">';
			str+='<div class="col-md-3 col-xs-6 col-sm-3 text-capital"><div class="pad_15 bg_ED">main edition - '+result.total+'</div></div>';
			str+='<div class="col-md-3 col-xs-6 col-sm-3 bg_ED text-capital"><div class="pad_15 bg_ED">dist edition - '+result.percent+'</div></div>';
		str+='</div>';
		$("#stateWiseArticleRelatedToProblem").html(str)
	}
	function buildDistrictWiseArticleRelatedToProblem(result)
	{
		var str='';
		var distWiseArticlesRelated = [];
		str+='<div id="districtWiseArticle" style="height:150px;"></div>';
		for(var i in result){
			var obj1 = {
				name: result[i].name,
				y: result[i].posPercent
			};
			distWiseArticlesRelated.push(obj1);
		}
		$("#districtWiseArticleRelatedToProblem").html(str)
		$(function () {
			 $("#districtWiseArticle").highcharts({
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
	function getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems(propertyId,deptIdsStr){
		if(propertyId == 7){
			$("#problemsDetailedOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}else if(propertyId == 0){
				$("#problemsDetailedOverview22").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		//var propertyId=0;
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyId+"/"+deptIdsStr+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisionGovtOverAllAnalysisOfActionImmediatelyProblems/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyId+"/"+deptIdsStr+""
		}).then(function(result){
			if(propertyId == 7){
				$("#problemsDetailedOverview").html('');
			}else if(propertyId == 0){
				$("#problemsDetailedOverview22").html('');
			}
			buildgetDetailedGovtOverAllAnalysisOfActionImmediatelyProblems(result,propertyId);
			
		});
	}
	function getComparisionPartyDistrictEditionsOverview(deptIdsStr){//Teja
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getComparisionPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr"/"+deptIdsStr+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisionPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr"/"+deptIdsStr+""
		}).then(function(result){
			$("#districtWiseNewsReport").html();
			buildComparisionPartyDistrictEditionsOverview(result);
		});
	}
	function buildComparisionPartyDistrictEditionsOverview(result){
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
						
						//if(result[i].subList[j].completedPerc !=null && result[i].subList[j].completedPerc >0){
							districtWisePositiveCountArray.push(result[i].coreDashBoardVOList[j].positiveCountMain);
						//}
						//if(result[i].subList[j].startedPerc !=null && result[i].subList[j].startedPerc >0){
							districtWiseNegativeCountArray.push(result[i].coreDashBoardVOList[j].negativCountMain);
						//}
						
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
	function getComparisionPartyNewsTypeAnalysis(deptIdsStr){//Teja1
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getComparisionPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr"/"+deptIdsStr+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisionPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr"/"+deptIdsStr+""
		}).then(function(result){
			$("#newsTypeAnalysisDiv").html();
			if(result != null && result.length > 0){
				buildComparisionPartyNewsTypeAnalysis(result);
			}
		});
	}
	function buildComparisionPartyNewsTypeAnalysis(result){
		var str='';
		if(result != null && result.length > 0){
			var str='';
				
			for(var i in result){
				
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-ms-12"><h5 class="text-capital">'+result[i].name+'</h5></div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-4" style="border-right:1px solid #ddd">';
						str+='<div id="newsTypeAnalysisPieChart'+i+'" style="height:165px;width:100%;"></div>';
						str+='<div class="row">';
							str+='<div class="col-xs-12 col-sm-12 col-md-12">';
								str+='<ul class="list-inline">';
								for(var j in result[i].coreDashBoardVOList){
									var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
									  str+='<li style="color:'+color+'">'+result[i].coreDashBoardVOList[j].organization+' :'+result[i].coreDashBoardVOList[j].positivePerc+'%('+result[i].coreDashBoardVOList[j].count+')</li> &nbsp;&nbsp;';
									
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
					
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						for (var j in result[i].coreDashBoardVOList){
							 PartyCountPerc = result[i].coreDashBoardVOList[j].count;
							 partyName = result[i].coreDashBoardVOList[j].organization;
							
							var obj = {
								name: partyName,
								y:PartyCountPerc
								
							}
							
							partyNameAndCountArray.push(obj);
						}
					}
				
					if(result[i].coreDashBoardVOList1 !=null && result[i].coreDashBoardVOList1.length >0){
						for (var j in result[i].coreDashBoardVOList1){
							districtNameArray.push(result[i].coreDashBoardVOList1[j].districtName)
							tdpPercArray.push(result[i].coreDashBoardVOList1[j].tdpPerc)
							ysrcPercArray.push(result[i].coreDashBoardVOList1[j].ysrcPerc)
							incPercArray.push(result[i].coreDashBoardVOList1[j].incPerc)
							bjpPercArray.push(result[i].coreDashBoardVOList1[j].bjpPerc)
							
							
						}
					}
						
					$(function () {
						if(partyNameAndCountArray.length !=0){
							$('#newsTypeAnalysisPieChart'+i).highcharts({
								colors: ['#FD9832','#3D9834','#FFCB00','#005DB0'],
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
					}else{
						$('#newsTypeAnalysisBarChart'+i).html("No Data Available");
						$('#newsTypeAnalysisBarChart'+i).css("height","10px")
					}
					});
				}
			}
	}
	function getComparisionPartyPartyVsPublications(searchType,deptIdsStr){//Teja2
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
		
		var startDate=currentFromDate,endDate=currentToDate;
		//var searchType = "publication";
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getComparisionPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr"/"+deptIdsStr+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getComparisionPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType+"/"+newsPaperIdsStr"/"+deptIdsStr+""
		}).then(function(result){
			
			
			if(result != null && result.length > 0 && searchType == "party"){
				//$("#partyWiseDetailsDiv").html();
				buildgetDetailedPartyWiseDetailes(result);
			}else{
				//$("#publicationWiseDetailsDiv").html();
				buildgetComparisionPublicationsWiseDetails(result);
			}
		});
	}
	function buildgetComparisionPublicationsWiseDetails(result){
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
									
									positivePercArray.push(result[i].coreDashBoardVOList[j].positivePerc)
									negativePercArray.push(result[i].coreDashBoardVOList[j].negativePerc)
						
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