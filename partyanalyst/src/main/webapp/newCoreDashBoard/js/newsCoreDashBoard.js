	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));
		
	$(document).on("click",".newsIconExpand",function(){
		$(".dateRangePickerClsForNews").toggleClass("hide");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".newsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".newsHiddenBlock,.morenewsBlocksIcon").toggle();
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			getUserTypeWiseNewsCounts(1);
		}
	});
	
	$(document).on("click",".morenewsBlocksIcon",function(){
		$(".newsHiddenMoreBlock").toggle();
		getDetailedPartyMainEditionsOverview();
		getDetailedPartyDistrictEditionsOverview();
		//getDetailedPartyNewsTypeAnalysis();
	});
	
	$(document).on("click","#detailedPartyId",function(){
		getDetailedPartyMainEditionsOverview();
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
		var startDate='08-01-2016',endDate='08-31-2016';
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
					//$("#tdpMainPositivePercent").html(((result[0].positiveCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+'%');
					//$("#tdpMainNegativePercent").html(((result[0].negativCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+'%');
					$("#tdpMainPositivePercent").html(" "+((result[0].positiveCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+" %");
					$("#tdpMainNegativePercent").html(" "+((result[0].negativCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2)+" %");
				}
				
				$("#tdpDistTotal").html(result[0].positiveCountDist+result[0].negativCountDist);				
				$("#tdpDistPositive").html(result[0].positiveCountDist);
				$("#tdpDistNegative").html(result[0].negativCountDist);
				if((result[0].positiveCountDist+result[0].negativCountDist) > 0){
					//$("#tdpDistPositivePercent").html(((result[0].positiveCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+'%');
					//$("#tdpDistNegativePercent").html(((result[0].negativCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+'%');
					$("#tdpDistPositivePercent").html(" "+((result[0].positiveCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+" %");
					$("#tdpDistNegativePercent").html(" "+((result[0].negativCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2)+" %");
				}
				
				var oppTotal=(result[1].positiveCountMain+result[1].negativCountMain)+(result[2].positiveCountMain+result[2].negativCountMain)+(result[3].positiveCountMain+result[3].negativCountMain);
				$("#oppMainTotal").html(oppTotal);
				$("#oppPositiveTotal").html(result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain);
				$("#oppNegativeTotal").html(result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain);
				if(oppTotal > 0){
					//$("#oppPositiveTotalPercent").html((((result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain)/oppTotal)*100+'%').toFixed(2));
					//$("#oppNegativeTotalPercent").html((((result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain)/oppTotal)*100+'%').toFixed(2));
					$("#oppPositiveTotalPercent").html(" "+(((result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain)/oppTotal)*100).toFixed(2)+" %");
					$("#oppNegativeTotalPercent").html(" "+(((result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain)/oppTotal)*100).toFixed(2)+" %");
				}
				
				var ysrcMainTotal = result[1].positiveCountMain+result[1].negativCountMain;
				$("#ysrcMainTotal").html(ysrcMainTotal);
				$("#ysrcMainPositive").html(result[1].positiveCountMain);
				$("#ysrcMainNegative").html(result[1].negativCountMain);
				
				if(ysrcMainTotal > 0){
					//$("#ysrcMainPositivePercent").html(((result[1].positiveCountMain*100)/ysrcMainTotal).toFixed(2)+'%');
					//$("#ysrcMainNegativePercent").html(((result[1].negativCountMain*100)/ysrcMainTotal).toFixed(2)+'%');
					$("#ysrcMainPositivePercent").html(" "+((result[1].positiveCountMain*100)/ysrcMainTotal).toFixed(2)+" %");
					$("#ysrcMainNegativePercent").html(" "+((result[1].negativCountMain*100)/ysrcMainTotal).toFixed(2)+" %");
					
				}
				
				var incMainTotal = result[2].positiveCountMain+result[2].negativCountMain;
				$("#incMainTotal").html(incMainTotal);
				$("#incMainPositive").html(result[2].positiveCountMain);
				$("#incMainNegative").html(result[2].negativCountMain);
				if(incMainTotal > 0){
					//$("#incMainPositivePercent").html(((result[2].positiveCountMain*100)/incMainTotal).toFixed(2)+'%');
					//$("#incMainNegativePercent").html(((result[2].negativCountMain*100)/incMainTotal).toFixed(2)+'%');
					$("#incMainPositivePercent").html(" "+((result[2].positiveCountMain*100)/incMainTotal).toFixed(2)+" %");
					$("#incMainNegativePercent").html(" "+((result[2].negativCountMain*100)/incMainTotal).toFixed(2)+" %");
				}
				 var bjpMainTotal = result[3].positiveCountMain+result[3].negativCountMain;
				 $("#bjpMainTotal").html(bjpMainTotal);
				 $("#bjpMainPositive").html(result[3].positiveCountMain);
				 $("#bjpMainNegative").html(result[3].negativCountMain);
				 
				 if(bjpMainTotal>0){
					 //$("#bjpMainPositivePercent").html(((result[3].positiveCountMain*100)/bjpMainTotal).toFixed(2)+'%');
					 //$("#bjpMainNegativePercent").html(((result[3].negativCountMain*100)/bjpMainTotal).toFixed(2)+'%');
					 $("#bjpMainPositivePercent").html(" "+((result[3].positiveCountMain*100)/bjpMainTotal).toFixed(2)+" %");
					 $("#bjpMainNegativePercent").html(" "+((result[3].negativCountMain*100)/bjpMainTotal).toFixed(2)+" %");
				 }
				 
				 var oppDistTotal=(result[1].positiveCountDist+result[1].negativCountDist)+(result[2].positiveCountDist+result[2].negativCountDist)+(result[3].positiveCountDist+result[3].negativCountDist);
				 $("#oppDistTotal").html(oppDistTotal);
				 
				 $("#oppDistPositive").html(result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist);
				 $("#oppDistNegative").html(result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist);
				 
				 if(oppDistTotal > 0){
					 //$("#oppDistPositivePercent").html((((result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist)*100)/oppDistTotal).toFixed(2)+'%');
					 //$("#oppDistNegativePercent").html((((result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist)*100)/oppDistTotal).toFixed(2)+'%');
					 $("#oppDistPositivePercent").html(" "+(((result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist)*100)/oppDistTotal).toFixed(2)+" %");
					 $("#oppDistNegativePercent").html(" "+(((result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist)*100)/oppDistTotal).toFixed(2)+" %");
				 }
				 
				 var ysrcDistTotal = result[1].positiveCountDist+result[1].negativCountDist;
				$("#ysrcDistTotal").html(ysrcDistTotal);
				$("#ysrcDistPositive").html(result[1].positiveCountDist);
				$("#ysrcDistNegative").html(result[1].negativCountDist);
				
				if(ysrcDistTotal > 0){
					//$("#ysrcDistPositivePercent").html(((result[1].positiveCountDist*100)/ysrcDistTotal).toFixed(2)+'%');
					//$("#ysrcDistNegativePercent").html(((result[1].negativCountDist*100)/ysrcDistTotal).toFixed(2)+'%');
					$("#ysrcDistPositivePercent").html(" "+((result[1].positiveCountDist*100)/ysrcDistTotal).toFixed(2)+" %");
					$("#ysrcDistNegativePercent").html(" "+((result[1].negativCountDist*100)/ysrcDistTotal).toFixed(2)+" %");
					
				}
				
				var incDistTotal = result[2].positiveCountDist+result[2].negativCountDist;
				$("#incDistTotal").html(incDistTotal);
				$("#incDistPositive").html(result[2].positiveCountDist);
				$("#incDistNegative").html(result[2].negativCountDist);
				if(incDistTotal > 0){
					//$("#incDistPositivePercent").html(((result[2].positiveCountDist*100)/incDistTotal).toFixed(2)+'%');
					//$("#incDistNegativePercent").html(((result[2].negativCountDist*100)/incDistTotal).toFixed(2)+'%');
					$("#incDistPositivePercent").html(" "+((result[2].positiveCountDist*100)/incDistTotal).toFixed(2)+" %");
					$("#incDistNegativePercent").html(" "+((result[2].negativCountDist*100)/incDistTotal).toFixed(2)+" %");
				}
				
				var bjpDistTotal = result[3].positiveCountDist+result[3].negativCountDist;
				 $("#bjpDistTotal").html(bjpDistTotal);
				 $("#bjpDistPositive").html(result[3].positiveCountDist);
				 $("#bjpDistNegative").html(result[3].negativCountDist);
				 
				 if(bjpDistTotal>0){
					 //$("#bjpDistPositivePercent").html(((result[3].positiveCountDist*100)/bjpDistTotal).toFixed(2)+'%');
					 //$("#bjpDistNegativePercent").html(((result[3].negativCountDist*100)/bjpDistTotal).toFixed(2)+'%');
					 $("#bjpDistPositivePercent").html(" "+((result[3].positiveCountDist*100)/bjpDistTotal).toFixed(2)+" %");
					 $("#bjpDistNegativePercent").html(" "+((result[3].negativCountDist*100)/bjpDistTotal).toFixed(2)+" %");
				 }
				 
				 var govtMainTotal = result[4].positiveCountMain+result[4].negativCountMain;
				 $("#govtMainTotal").html(govtMainTotal);
				 $("#govtMainPositive").html(result[4].positiveCountMain);
				 $("#govtMainNegative").html(result[4].negativCountMain);
				 if(govtMainTotal > 0){
					 //$("#govtMainPositivePercent").html(((result[4].positiveCountMain*100)/govtMainTotal).toFixed(2)+'%');
					 //$("#govtMainNegativePercent").html(((result[4].negativCountMain*100)/govtMainTotal).toFixed(2)+'%');
					 $("#govtMainPositivePercent").html(" "+((result[4].positiveCountMain*100)/govtMainTotal).toFixed(2)+" %");
					 $("#govtMainNegativePercent").html(" "+((result[4].negativCountMain*100)/govtMainTotal).toFixed(2)+" %");
				 }
				 
				var govtDistTotal = result[4].positiveCountDist+result[4].negativCountDist;
				 $("#govtDistTotal").html(govtDistTotal);
				 $("#govtDistPositive").html(result[4].positiveCountDist);
				 $("#govtDistNegative").html(result[4].negativCountDist);
				 if(govtDistTotal > 0){
					 //$("#govtDistPositivePercent").html(((result[4].positiveCountDist*100)/govtDistTotal).toFixed(2)+'%');
					 //$("#govtDistNegativePercent").html(((result[4].negativCountDist*100)/govtDistTotal).toFixed(2)+'%');
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
				fromDate:'08-01-2016',
				toDate:'08-31-2016',
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
		var startDate="08-01-2016",endDate="08-31-2016";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			$("#mainEditiongraphId").html('');
			buildMainEditionPartieWiseGraph(result);
		});
		
	}
	
	function getDetailedPartyDistrictEditionsOverview(){
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate="08-01-2016",endDate="08-31-2016";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			buildDetailedPartyDistrictEditionsOverview(result);
		});
	}
	
	function getDetailedPartyNewsTypeAnalysis(){
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate="08-01-2015",endDate="08-31-2016";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyNewsTypeAnalysis/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				buildDetailedPartyNewsTypeAnalysis(result);
			}
		});
	}
	
	function getDetailedPartyPartyVsPublications(){
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate="08-01-2015",endDate="08-31-2016";
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyPartyVsPublications/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				
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
						str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
						str+='<div id="newsBlockGenSecStrong'+i+'" style="width:100%;height:100px;"></div>';
					str+='</div>'
						
				}
				
			}
			$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html(str);
			if(result != null && result.length > 0){
				for(var i in result){
					
					var candidateNameAndPositiveCountArray = [];
					var countVar =0;
					
					if(result[i] !=null && result[i].length>0){
						for(var j in result[i]){
							
							 var obj1 = {
									name: result[i][j].name,
									y: result[i][j].positivePercentage
								};
							candidateNameAndPositiveCountArray.push(obj1);
								
							countVar =countVar+1;
							if (countVar === 5) {
								break;
							}
						}
					}
					
						
					if( result[i][j].positivePercentage !=0){
						var getWidth = $("#newsBlockGenSecStrong"+i).parent().width()+'px';
						$("#newsBlockGenSecStrong"+i).width(getWidth);
						$(function () {
							 $("#newsBlockGenSecStrong"+i).highcharts({
								 colors: ['#0066DC'],
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
									headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
									pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
								},

								series: [{
									name: 'Positive',
									data: candidateNameAndPositiveCountArray
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
						str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
						str+='<div id="newsBlockGenSecStrong'+i+'" style="width:100%;height:100px;"></div>';
					str+='</div>'
						
				}
				
			}
			$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html(str);
			if(result != null && result.length > 0){
				for(var i in result){
					
					var candidateNameAndPositiveCountArray = [];
					var countVar =0;
					
					if(result[i] !=null && result[i].length>0){
						for(var j in result[i]){
							
							 var obj1 = {
									name: result[i][j].name,
									y: result[i][j].negativePercentage
								};
							candidateNameAndPositiveCountArray.push(obj1);
								
							countVar =countVar+1;
							if (countVar === 5) {
								break;
							}
						}
					}
					
						
					if( result[i][j].negativePercentage !=0){
						var getWidth = $("#newsBlockGenSecStrong"+i).parent().width()+'px';
						$("#newsBlockGenSecStrong"+i).width(getWidth);
						$(function () {
							 $("#newsBlockGenSecStrong"+i).highcharts({
								 colors: ['#0066DC'],
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
									headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
									pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
								},

								series: [{
									name: 'Positive',
									data: candidateNameAndPositiveCountArray
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
	
	/* function buildgetUserTypeWiseNewsForTopFivePoorResults(result){
		var str='';
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
					str+='<div id="newsBlockGenSecPoor'+i+'" class="m_top20" style="width:100%;height:100px;"></div>';
				str+='</div>';
			}
		}
		$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html(str);
		if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameAndNegativeCountArray =[];
				//var CommitteeCompleteCountArray;
				var countVar = 0;
				
				if(result[i] !=null && result[i].length  >0){
					for(var j = result[i].length -1; j >= 0; j--){
						 var obj1 = {
								name: result[i][j].name,
								y: result[i][j].negativePercentage
							};
						
						candidateNameAndNegativeCountArray.push(obj1);
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
				
					
				//if( result[i][j].completedPerc !=0){
					var getWidth = $("#newsBlockGenSecPoor"+i).parent().width()+'px';
					$("#newsBlockGenSecPoor"+i).width(getWidth);
					$(function () {
						 $("#newsBlockGenSecPoor"+i).highcharts({
							 colors: ['#0066DC'],
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
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
							},

							series: [{
								name: 'Negative',
								data: candidateNameAndNegativeCountArray
							}],
						 
						});
					});
				//}else{
					//$("#genSec1"+i).html("No Data Available");
					//$("#genSec1"+i).css("height","35px");
						
				//} 
				
			}
			
		}else{
			$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html("No Data Available");
		}
		
	} */
	
	$(document).on("click",".newsliCls",function(){
		getUserTypeWiseNewsCounts($(this).attr("attr_value"));
		/* var memberType=$(this).attr("attr_value");
		 if(memberType != null && memberType == "strong"){
			buildgetUserTypeWiseNewsForTopFiveStrongResults(globalUserWiseMemberRslt); 
		 }else if(memberType == "poor"){
			buildgetUserTypeWiseNewsForTopFivePoorResults(globalUserWiseMemberRslt)
		 } */
	});
	

	function buildMainEditionPartieWiseGraph(result){
		if(result != null && result.length > 0){
			var str='';
			str+='<ul class="newsPartyWiseUI">';
			str+='<li>';
			str+='<div class="scroll-div">';
			str+='<ul class="list-inline best-matched-profile ">';
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
			
			$(function () {
				$('#mainEditiongraph'+i+'').highcharts({
					colors: ['#F56800','#53BF8B','#66728C'],
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
		}
	  }
	}
	
	function buildDetailedPartyDistrictEditionsOverview(result){
		$("#districtWiseNewsReport").html('');
		if(result != null && result.length > 0){
			var str='';
			var countVar =0;
			for(var i in result){
				countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				str+=result[i].organization;
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
						$(function () {
							$('#districtWiseNews'+i+'').highcharts({
								colors: ['#F56800','#53BF8B','#66728C'],
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
						str+='<hr/>';
						str+='<div id="newsUpId" style="color:red;"></div>';
                        str+='<label>Create Notes</label>';
                        str+='<textarea class="form-control notesAreaNews"></textarea>';
                        str+='<button class="btn btn-default btnCustomCreateNews btn-sm "  onClick="savingDashboardCommentForNews(5);">create</button>';
			
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
				
				$("#newsUpId").html('update succuss');
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
	$(".mainBuildingDivClass").hide();
	var divId = $(this).attr("attr_div_id");
	$("#"+divId).show();
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
				startDate:'08-01-2016',
				endDate:'08-31-2016'
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
						str+='<div class="col-xs-12">';
						for(var j in result[i].coreDashBoardVOList){
							
							  str+='<span>'+result[i].coreDashBoardVOList[j].organization+' :'+result[i].coreDashBoardVOList[j].positivePerc+'%</span> &nbsp;&nbsp;';
							
						}
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
								colors: ['#FFCC00','#31A973','#34CBFE','#FF9934'],
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
							colors: ['#FFCC00','#31A973','#34CBFE','#FF9934'],
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
								shadow: false
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
