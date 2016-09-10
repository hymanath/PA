	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));
	
	var currentFromDate = moment().format("DD-MM-YYYY");
	var currentToDate = moment().format("DD-MM-YYYY");
	
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
					}
				});
			} 
			
		
	  
	});
	
	$(document).on("click",".settingsIconNews",function(){
		$(this).closest(".newsBlock").find(".basicCommitteesBlockDropDown").toggle();
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
			$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon").hide();
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
		$("#publicationWiseDetailsDiv").hide();
		$("#partyWiseDetailsDiv").show();
		getDetailedPartyPartyVsPublications("party");
	});
	$(document).on("click",".publictionWiseDiv",function(){
		$("#partyWiseDetailsDiv").hide();
		$("#publicationWiseDetailsDiv").show();
		getDetailedPartyPartyVsPublications("publication");
	});
	
	$(document).on("click","#detailedPartyLiId",function(){
		//getDetailedPartyMainEditionsOverview();
		getDetailedPartyDistrictEditionsOverview();
		getDetailedPartyNewsTypeAnalysis();
		getDetailedPartyPartyVsPublications();
	});
	
	function getNewsBasicCounts(){
	
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		var state = globalState;
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				$("#tdpMainTotal").html(result[0].positiveCountMain+result[0].negativCountMain);
				$("#tdpMainPositive").html(result[0].positiveCountMain);
				$("#tdpMainNegative").html(result[0].negativCountMain);
				if((result[0].positiveCountMain+result[0].negativCountMain) > 0){
					$("#tdpMainPositivePercent").html(" "+((result[0].positiveCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+" %");
					$("#tdpMainNegativePercent").html(" "+((result[0].negativCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+" %");
				}
				
				$("#tdpDistTotal").html(result[0].positiveCountDist+result[0].negativCountDist);				
				$("#tdpDistPositive").html(result[0].positiveCountDist);
				$("#tdpDistNegative").html(result[0].negativCountDist);
				if((result[0].positiveCountDist+result[0].negativCountDist) > 0){
					$("#tdpDistPositivePercent").html(" "+((result[0].positiveCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+" %");
					$("#tdpDistNegativePercent").html(" "+((result[0].negativCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+" %");
				}
				
				var oppTotal=(result[1].positiveCountMain+result[1].negativCountMain)+(result[2].positiveCountMain+result[2].negativCountMain)+(result[3].positiveCountMain+result[3].negativCountMain);
				$("#oppMainTotal").html(oppTotal);
				$("#oppPositiveTotal").html(result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain);
				$("#oppNegativeTotal").html(result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain);
				if(oppTotal > 0){
					$("#oppPositiveTotalPercent").html(" "+(((result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain)/oppTotal)*100).toFixed(2)+" %");
					$("#oppNegativeTotalPercent").html(" "+(((result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain)/oppTotal)*100).toFixed(2)+" %");
				}
				
				var ysrcMainTotal = result[1].positiveCountMain+result[1].negativCountMain;
				$("#ysrcMainTotal").html(ysrcMainTotal);
				$("#ysrcMainPositive").html(result[1].positiveCountMain);
				$("#ysrcMainNegative").html(result[1].negativCountMain);
				
				if(ysrcMainTotal > 0){
					$("#ysrcMainPositivePercent").html(" "+((result[1].positiveCountMain*100)/ysrcMainTotal).toFixed(2)+" %");
					$("#ysrcMainNegativePercent").html(" "+((result[1].negativCountMain*100)/ysrcMainTotal).toFixed(2)+" %");
					
				}
				
				var incMainTotal = result[2].positiveCountMain+result[2].negativCountMain;
				$("#incMainTotal").html(incMainTotal);
				$("#incMainPositive").html(result[2].positiveCountMain);
				$("#incMainNegative").html(result[2].negativCountMain);
				if(incMainTotal > 0){
					$("#incMainPositivePercent").html(" "+((result[2].positiveCountMain*100)/incMainTotal).toFixed(2)+" %");
					$("#incMainNegativePercent").html(" "+((result[2].negativCountMain*100)/incMainTotal).toFixed(2)+" %");
				}
				 var bjpMainTotal = result[3].positiveCountMain+result[3].negativCountMain;
				 $("#bjpMainTotal").html(bjpMainTotal);
				 $("#bjpMainPositive").html(result[3].positiveCountMain);
				 $("#bjpMainNegative").html(result[3].negativCountMain);
				 
				 if(bjpMainTotal>0){
					$("#bjpMainPositivePercent").html(" "+((result[3].positiveCountMain*100)/bjpMainTotal).toFixed(2)+" %");
					$("#bjpMainNegativePercent").html(" "+((result[3].negativCountMain*100)/bjpMainTotal).toFixed(2)+" %");
				 }
				 
				 var oppDistTotal=(result[1].positiveCountDist+result[1].negativCountDist)+(result[2].positiveCountDist+result[2].negativCountDist)+(result[3].positiveCountDist+result[3].negativCountDist);
				 $("#oppDistTotal").html(oppDistTotal);
				 
				 $("#oppDistPositive").html(result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist);
				 $("#oppDistNegative").html(result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist);
				 
				 if(oppDistTotal > 0){
					$("#oppDistPositivePercent").html(" "+(((result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist)*100)/oppDistTotal).toFixed(2)+" %");
					$("#oppDistNegativePercent").html(" "+(((result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist)*100)/oppDistTotal).toFixed(2)+" %");
				 }
				 
				 var ysrcDistTotal = result[1].positiveCountDist+result[1].negativCountDist;
				$("#ysrcDistTotal").html(ysrcDistTotal);
				$("#ysrcDistPositive").html(result[1].positiveCountDist);
				$("#ysrcDistNegative").html(result[1].negativCountDist);
				
				if(ysrcDistTotal > 0){
					$("#ysrcDistPositivePercent").html(" "+((result[1].positiveCountDist*100)/ysrcDistTotal).toFixed(2)+" %");
					$("#ysrcDistNegativePercent").html(" "+((result[1].negativCountDist*100)/ysrcDistTotal).toFixed(2)+" %");
				}
				
				var incDistTotal = result[2].positiveCountDist+result[2].negativCountDist;
				$("#incDistTotal").html(incDistTotal);
				$("#incDistPositive").html(result[2].positiveCountDist);
				$("#incDistNegative").html(result[2].negativCountDist);
				if(incDistTotal > 0){
					$("#incDistPositivePercent").html(" "+((result[2].positiveCountDist*100)/incDistTotal).toFixed(2)+" %");
					$("#incDistNegativePercent").html(" "+((result[2].negativCountDist*100)/incDistTotal).toFixed(2)+" %");
				}
				
				var bjpDistTotal = result[3].positiveCountDist+result[3].negativCountDist;
				 $("#bjpDistTotal").html(bjpDistTotal);
				 $("#bjpDistPositive").html(result[3].positiveCountDist);
				 $("#bjpDistNegative").html(result[3].negativCountDist);
				 
				 if(bjpDistTotal>0){
					$("#bjpDistPositivePercent").html(" "+((result[3].positiveCountDist*100)/bjpDistTotal).toFixed(2)+" %");
					$("#bjpDistNegativePercent").html(" "+((result[3].negativCountDist*100)/bjpDistTotal).toFixed(2)+" %");
				 }
				 
				 var govtMainTotal = result[4].positiveCountMain+result[4].negativCountMain;
				 $("#govtMainTotal").html(govtMainTotal);
				 $("#govtMainPositive").html(result[4].positiveCountMain);
				 $("#govtMainNegative").html(result[4].negativCountMain);
				 if(govtMainTotal > 0){
					$("#govtMainPositivePercent").html(" "+((result[4].positiveCountMain*100)/govtMainTotal).toFixed(2)+" %");
					$("#govtMainNegativePercent").html(" "+((result[4].negativCountMain*100)/govtMainTotal).toFixed(2)+" %");
				 }
				 
				var govtDistTotal = result[4].positiveCountDist+result[4].negativCountDist;
				 $("#govtDistTotal").html(govtDistTotal);
				 $("#govtDistPositive").html(result[4].positiveCountDist);
				 $("#govtDistNegative").html(result[4].negativCountDist);
				 if(govtDistTotal > 0){
					$("#govtDistPositivePercent").html(" "+((result[4].positiveCountDist*100)/govtDistTotal).toFixed(2)+" %");
					$("#govtDistNegativePercent").html(" "+((result[4].negativCountDist*100)/govtDistTotal).toFixed(2)+" %");
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
				benefitId:benefitId
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
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
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
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
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
		var startDate=currentFromDate,endDate=currentToDate;
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			$("#newsTypeAnalysisDiv").html();
			if(result != null && result.length > 0){
				buildDetailedPartyNewsTypeAnalysis(result);
			}
		});
	}
	
	function getDetailedPartyPartyVsPublications(searchType){
		if(searchType == "party"){
			$("#partyWiseDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}else{
			$("#publicationWiseDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate=currentFromDate,endDate=currentToDate;
		//var searchType = "publication";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyPartyVsPublications/"+1+"/"+1+"/"+globalState+"/"+startDate+"/"+endDate+"/"+searchType
		}).then(function(result){
			
			
			if(result != null && result.length > 0 && searchType == "party"){
				$("#partyWiseDetailsDiv").html();
				buildgetDetailedPartyWiseDetailes(result);
			}else{
				$("#publicationWiseDetailsDiv").html();
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
							PositiveCountArray.push(result[i][j].positivePercentage)
							NegativeCountArray.push(result[i][j].negativePercentage)
								
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
													return Highcharts.numberFormat(this.y,1) + '%';
												}
											}
										  
										}
									}
								},

								tooltip: {
									//headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br>',
									shared:true
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
							PositiveCountArray.push(result[i][j].positivePercentage)
							NegativeCountArray.push(result[i][j].negativePercentage)
								
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
													return Highcharts.numberFormat(this.y,1) + '%';
												}
											}
										  
										}
									}
								},

								tooltip: {
									//headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br>',
									shared:true
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
					 
				str+='<img src="newCoreDashBoard/img/'+result[i].organization+'.png" style="width:25px;" alt="tdp icon"/> &nbsp;&nbsp;&nbsp;'+result[i].organization+'';
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
			var districtWisePositivePercArray = [];
			var districtWiseNegativePercArray = [];
			
			if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length > 0){
				
				for(var j in result[i].coreDashBoardVOList){
					
						districtNamesArray.push(result[i].coreDashBoardVOList[j].districtName);
						
						//if(result[i].subList[j].completedPerc !=null && result[i].subList[j].completedPerc >0){
							districtWisePositivePercArray.push(result[i].coreDashBoardVOList[j].positivePerc);
						//}
						//if(result[i].subList[j].startedPerc !=null && result[i].subList[j].startedPerc >0){
							districtWiseNegativePercArray.push(result[i].coreDashBoardVOList[j].negativePerc);
						//}
						
					}
			}	
					if(districtWisePositivePercArray.length !=0 && districtWiseNegativePercArray.length !=0){
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
									headerFormat: '<b>{point.x}</b><br/>',
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
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
													return Highcharts.numberFormat(this.y,1);
												}
											}
										  
										}
									}
								},
								series: [{
									name: 'Positive',
									data: districtWisePositivePercArray
								}, {
									name: 'Negative',
									data: districtWiseNegativePercArray
								}]
							});
						});
					}else{
						$('#districtWiseNews'+i+'').html("No Data Available");
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
	getChildUserTypesByItsParentUserType1();
});
function getChildUserTypesByItsParentUserType1(){
		var jsObj = { parentUserTypeId : globalUserTypeId }
		$.ajax({
			type : 'POST',
			url : 'getChildUserTypesByItsParentUserTypeAction.action',
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
					str+='<li attr_usertypeid="'+result[i].userTypeId+'" class="detailedPartySubLi">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
				}
				str+='</ul>';
			str+='</div>';
			
			getPartyComparisonChildUserTypeMembers(result[0].userTypeId);
		}
		$("#userTypeStrId").html(str);
		$(".detailedPartySubUl li:first-child").addClass("active");
		
	}	
	
	function getPartyComparisonChildUserTypeMembers(childUserTypeId){
		
		var jsObj={
				parentActivityMemberId : globalActivityMemberId ,
				childUserTypeId : childUserTypeId,
				state:globalState,
				startDate:currentFromDate,
				endDate:currentToDate
			}
		
			$.ajax({
				type : 'POST',
				url : 'getPartyComparisonChildUserTypeMembersAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				
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
									  str+='<li style="color:'+color+'">'+result[i].coreDashBoardVOList[j].organization+' :'+result[i].coreDashBoardVOList[j].positivePerc+'%</li> &nbsp;&nbsp;';
									
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
							 PartyCountPerc = result[i].coreDashBoardVOList[j].positivePerc;
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
										headerFormat: '<b>{point.x}</b>',
										pointFormat: '<span style="color:{series.color}">{point.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
										shared: true
									},
								plotOptions: {
									pie: {
										innerSize: 85,
										depth: 10,
										dataLabels:{
											enabled: false,
											 /* formatter: function() {
													if (this.y === 0) {
														return null;
													} else {
														return Highcharts.numberFormat(this.y,1)+ '%';
													}
												} */
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
								headerFormat: '<b>{point.x}</b><br/>',
								pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b><br/>',
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
		//getPartyCompareSubLevelMemberDetails(activityMemberId,userTypeId);//sandeep
	});
	
	function getPartyCompareSubLevelMemberDetails(activityMemberId,userTypeId){
		var jsObj={
				activityMemberId : activityMemberId ,
				userTypeId : userTypeId,
				state:globalState,
				startDate:currentFromDate,
				endDate:currentToDate
			}
		
			$.ajax({
				type : 'POST',
				url : 'getPartyCompareSubLevelMemberDetailsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				
			});
	}

$(document).on("click",".detailedPartySubLi",function(){
		getPartyComparisonChildUserTypeMembers($(this).attr("attr_usertypeid"));
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
			url: wurl+"/CommunityNewsPortal/webservice/getRescentArticleTime/"
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getRescentArticleTime/"
		}).then(function(result){
			if(result != null && result.length > 0){
				
			}
		});
	}    
	
	function buildgetDetailedPartyWiseDetailes(result){
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
								str+='<ul class="villageWardUlffff">';
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
									positivePercArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].positivePerc)
									negativePercArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativePerc)
									
									
								}
							}
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
										pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
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
						}
						
					}
					
					
					
				}
			}
			$(".villageWardUlffff").slick({
				 slide: 'li',
				 slidesToShow: 4,
				 slidesToScroll: 1,
				 infinite: false,
				 swipeToSlide:false,
				 swipe:false,
				 touchMove:false
			}); 
		
	}
	
	function buildgetDetailedPublicationsWiseDetails(result){
		var str ='';
		if(result !=null && result.length >0){
			for(var i in result){
				var partyname;
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						str+='<div class="col-md-12 col-xs-12 col-ms-12">';
							str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
						for(var j in result[i].coreDashBoardVOList){
							partyname = result[i].coreDashBoardVOList[0].name;
						}
						str+='<h5  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;">'+partyname+'</h5>';
							str+='</div>';
							str+='<div class="col-xs-11 col-sm-10 col-md-11">';
								str+='<ul class="villageWardUlddd">';
						for(var j in result[i].coreDashBoardVOList){
							str+='<li><div id="publicationwisegraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
						}
							
						
							str+='</ul>';
						str+='</div>';
					str+='</div>';
					}
					
					
					
			}
			
		}
		
		
		$("#publicationWiseDetailsDiv").html(str);
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
							
						}
					}
						
				}
			}
		$(".villageWardUlddd").slick({
				 slide: 'li',
				 slidesToShow: 4,
				 slidesToScroll: 1,
				 infinite: false,
				 swipeToSlide:false,
				 swipe:false,
				 touchMove:false
			}); 
	}
	