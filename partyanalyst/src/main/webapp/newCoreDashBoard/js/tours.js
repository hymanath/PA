$(document).on("click",".tourExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".toursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	//$(".cadreBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$(".tourExpandCls").show();
	getDesigWiseMemberDtls();  
	setTimeout(function(){
		$(".toursHiddenBlock").toggle();      
	},800);
});
$(document).on("click",".moreToursBlocksIcon",function(){
	$(".moreToursBlocks1,.moreToursBlocksDetailed").toggle(); 
	   getDistrictWiseToursSubmitedDetails();		
});
$(document).on("click",".toursDetailedBlock",function(){
		$(".moreToursBlocksDetailed").show();
		$(".comparisonBlockTours").hide();
	   getDistrictWiseToursSubmitedDetails();		
});
$(document).on("click",".toursComparisionBlock",function(){
	$(".moreToursBlocksDetailed").hide();
	$(".comparisonBlockTours").show();
	 var selectedUserName = "RAMMANAIDU NIMMALA";
	 var userType = "GENERAL SECRETARY";
	 var userTypeId = 3;
	 
	 getTopPoorToursLocationDetails(userTypeId,selectedUserName,userType);
});

var globalStateIdForTour=1; //for ap
function getToursBasicOverviewCountDetails()
	{    
		$("#tourOverviewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : "27/10/2016",
					 toDate : "29/10/2016"
				  }
		$.ajax({
			type : 'POST',
			url : 'getToursBasicOverviewCountDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			    $("#tourOverviewDivId").html(' ');
				buildToursBasicOverviewRslt(result);
		});
	}
	 function buildToursBasicOverviewRslt(result){
		var overAllResult= result.overAllDetailsVO;
		var designationWiseRlst = result.subList;
		 var str='';
		  str+'<div class="row">';
		  if(overAllResult != null){
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >';
			 str+='<h4>Leaders</h4>';
		      str+='<table class="table tableTraining bg_ED">';
			  str+='<tbody><tr>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.noOfLeaderCnt+'</h4>';
					  str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.submitedLeaderCnt+'<span class="font-10 text-success"> '+overAllResult.submitedCandidateTourPer+'%</span></h4>';
						str+='<p class="text-muted text-capital">Submited<br>Leaders</p>';  
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.notSubmitedLeaserCnt+'<span class="font-10 text-danger"> '+overAllResult.notsubmitedCandidateTourPer+'%</span></h4>';
					str+='<p class="text-muted text-capital">Not Submited<br>Leaders</p>';  
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.totalSubmittedToursCnt+'</h4>';
						str+='<p class="text-muted text-capital">Total<br>Tours</p>';
				   str+='</td>';
				    str+='<td>';
						 str+='<h4>'+overAllResult.averageTours.toFixed(2)+'</h4>';
						str+='<p class="text-muted text-capital">Average<br>Tours</p>';  
				str+='</td>';
			  str+='</tr>';
	       str+='</tbody></table>';
		  str+='<hr class="m_0">';
		  str+='</div>';  
		  }
		 if(designationWiseRlst != null && designationWiseRlst.length > 0){
		   for(var i in designationWiseRlst){
				  str+='<h4>'+designationWiseRlst[i].designation+'</h4>';
				  str+='<table class="table tableTraining bg_ED">';
				  str+='<tbody><tr>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
						  str+='<h4>'+designationWiseRlst[i].noOfLeaderCnt+'</h4>';
					  str+='</td>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Submited<br>Leaders</p>';  
						  str+='<h4>'+designationWiseRlst[i].submitedLeaderCnt+'<span class="font-10 text-success"> '+designationWiseRlst[i].submitedCandidateTourPer.toFixed(2)+'%</span></h4>';
					  str+='</td>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Not Submited<br>Leaders</p>';  
						  str+='<h4>'+designationWiseRlst[i].notSubmitedLeaserCnt+'<span class="font-10 text-danger"> '+designationWiseRlst[i].notsubmitedCandidateTourPer.toFixed(2)+'%</span></h4>';
					  str+='</td>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Total<br>Tours</p>';
						  str+='<h4>'+designationWiseRlst[i].totalSubmittedToursCnt+'</h4>';
					   str+='</td>';
						str+='<td>';
							 str+='<p class="text-muted text-capital">Average<br>Tours</p>';  
							 str+='<h4>'+designationWiseRlst[i].averageTours.toFixed(2)+'</h4>';
					str+='</td>';
				  str+='</tr>';
			   str+='</tbody></table>';
			  str+='<hr class="m_0">';
			  str+='</div>';  
			}
		  }else{
		  str+='No Data Available';	  
		  }
	  $("#tourOverviewDivId").html(str);  
	 }
	 
	 function getDistrictWiseToursSubmitedDetails()
		{   
			$("#districtWiseLeaderDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			var jsObj ={ 
						 activityMemberId : globalActivityMemberId,
						 stateId : globalStateIdForTour,
						 fromDate : "27/10/2016",
						 toDate : "29/10/2016",
						 userTypeId : globalUserTypeId
					  }
			$.ajax({
				type : 'POST',
				url : 'getDistrictWiseToursSubmitedDetailsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
			   buildDistrictWiseToursSubmitedDetails(result);
			});
		}
		
	function buildDistrictWiseToursSubmitedDetails(result){
		$("#districtWiseLeaderDiv").html('');
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+=result[i].designation;
				str+='<div id="designationWiseCandidate'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
									
		}
		$("#districtWiseLeaderDiv").html(str);
	 if(result != null && result.length > 0){
		    for(var i in result){
					var locationNameArr =[];
					var averageToursArr = [];
					if(result[i].subList !=null && result[i].subList.length > 0){
						for(var j in result[i].subList){
									locationNameArr.push(result[i].subList[j].name);
									averageToursArr.push(result[i].subList[j].averageTours);
						}
					}
					$(function () {
							$('#designationWiseCandidate'+i+'').highcharts({
								colors: ['#53BF8B'],
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
									headerFormat: '<b>{point.x}</b><br/>',
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.2f}</b><br/>',
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
													return Highcharts.numberFormat(this.y,2) +"";
												}
											}
										  
										}
									}
								},
								series: [{
									name: 'Average',
									data: averageToursArr
								}]
							});
						});
		   }
	}else{
		$("#districtWiseLeaderDiv").html("No Data Available.")
	}	
  }
  function getTopPoorToursLocationDetails(userTypeId,selectedUserName,userType)
	{   $("#topPoorLocationsToursDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
					 activityMemberId : 2,
					 stateId : globalStateIdForTour,
					 userTypeId:userTypeId,
					 fromDate : "27/10/2016",
					 toDate : "29/10/2016"
				  }
		$.ajax({
			type : 'POST',
			url : 'getTopPoorToursLocationDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		$("#topPoorLocationsToursDivId").html(' ');
			buildToursPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType);	
 		}); 
	}
	function buildToursPoorPerformanceLocationRslt(result,userTypeId,selectedUserName,userType){
		
	 var resultListFirst;
	var resultListSecond;
    var str='';
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
			str+='<b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">TOP POOR </span> TOUR LOCATIONS &nbsp&nbsp('+selectedUserName+" - "+userType+')</span></b>';
		str+='</div>';
	   str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	  if(userTypeId!= null && userTypeId==3 || userTypeId==2 || userTypeId==5 ||  userTypeId==11 || userTypeId==4){
		str+='<p class="text-capital">districts<span style="margin-left:320px">Average Tours</span></p>';  
		resultListFirst = result.subList;
		resultListSecond = result.subList2;
	  }
	  if(userTypeId!= null &&  userTypeId==6){
		 str+='<p class="text-capital">Parliament Constituency <span style="margin-left:320px">Average Tours</span></p>';  
		resultListFirst = result.subList;
		resultListSecond = result.subList2;  
	  }
	   if(userTypeId!= null && userTypeId==7 || userTypeId==8 || userTypeId==9){
		 str+='<p class="text-capital">Constituencies<span style="margin-left:320px">Average Tours</span></p>';  
		resultListFirst = result.subList2;
	  }
	  
      str+='<table class="table tableCumulative">';
      if(resultListFirst != null && resultListFirst.length > 0){
		  var order=1;
		  var BGColor = 1;
		  for(var i in resultListFirst){
			
			str+='<tr>';
			str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+order+'</span></td>';
			str+='<td>'+resultListFirst[i].name+'</td>';
			str+='<td>';
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListFirst[i].averageTours.toFixed(2)+'">';
			str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListFirst[i].averageTours.toFixed(2)+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListFirst[i].averageTours+';">';
					str+='<span class="sr-only">'+resultListFirst[i].averageTours.toFixed(2)+'</span>';
				  str+='</div>';
				str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListFirst[i].averageTours.toFixed(2)+'</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
			str+='</table>';
	  }else{
		  str+='No Data Available';
	  }	  
	  if(resultListSecond == null || resultListSecond.length == 0){
		   $("#topPoorLocationsToursDivId").html(str);	
	        $('.progressCustom').tooltip();	
		  return;
	  }
	 // str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';
	  if(userTypeId!= null && userTypeId==3 || userTypeId==2 || userTypeId==5 ||  userTypeId==11 || userTypeId==4 || userTypeId==6){
		str+='<p class="text-capital m_top20">Constituencies<span style="margin-left:270px">Average Tours</span></p>';  
	  }
	  str+='<table class="table tableCumulative">';
      if(resultListSecond != null && resultListSecond.length > 0){
		  var order=1;
		  var BGColor = 1;
		  for(var i in resultListSecond){
			str+='<tr>';
			str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+order+'</span></td>';
			str+='<td>'+resultListSecond[i].name+'</td>';
			str+='<td>';
				str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+resultListSecond[i].averageTours.toFixed(2)+'">';
			str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+resultListSecond[i].averageTours.toFixed(2)+'" aria-valuemin="0" aria-valuemax="100" style="width:'+resultListSecond[i].averageTours.toFixed(2)+';">';
			str+='<span class="sr-only">'+resultListSecond[i].averageTours.toFixed(2)+'</span>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='<td class="text-danger">'+resultListSecond[i].averageTours.toFixed(2)+'</td>';
			str+='</tr>';
			order=order+1;
			if(order==6)
				break;
			BGColor = BGColor - 0.2;
			}
			str+='</table>';
			}else{
			 str+='No Data Available';	
			}
	      //  str+='</div>';
	str+='</div>';
	 $("#topPoorLocationsToursDivId").html(str);	
	 $('.progressCustom').tooltip();	
	}
	var globalUserTypeWiseTourCountRslt;
	function getDesigWiseMemberDtls(){ 
	$("#userTypeWiseTopFiveStrongAndPoorToursMemsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"> <div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
			activityMemberId : 44,        
			stateId : globalStateIdForTour,
			fromDate : "27/10/2016",              
			toDate : "29/10/2016"  
		}
		$.ajax({
			type : 'POST',
			url : 'getDesigWiseMemberDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}         
			}).done(function(result){
				$("#userTypeWiseTopFiveStrongAndPoorToursMemsDivId").html(' ');
				buildgUserTypeWiseToursTopFiveStrongRslt(result);
				globalUserTypeWiseTourCountRslt = result;
			});
	}  
	
  $(document).on("click",".toursCls",function(){
	var resultType=$(this).attr("attr_value");
	 if(resultType != null && resultType == "strong"){
	  buildgUserTypeWiseToursTopFiveStrongRslt(globalUserTypeWiseTourCountRslt); 
	 }else if(resultType == "poor"){
	  buildgUserTypeWiseToursTopFivePoorRslt(globalUserTypeWiseTourCountRslt);
	 }
  });
	 
	function buildgUserTypeWiseToursTopFiveStrongRslt(result){
		var str='';
		if(result != null && result.length > 0){
		  var str='';
		  for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				 if(result[i][0].designationId==4 || result[i][0].designationId==11){
				  if(result[i][0].designationId==4){
				  if(result[i][0].totalTour!=0){
					  str+='<h5 class="text-capital">'+result[i][0].designation+' / SECRETARY </h5>';      
				  }
				  }
				  if(result[i][0].designationId==11){
				   if(result[i][0].totalTour!=0){
					 str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].designation+'</h5>';      
				   }
			     }
			   }else{
				 if(result[i][0].totalTour!=0){
					str+='<h5 class="text-capital">'+result[i][0].designation+'</h5>'; 
				 }
		      }
			  str+='<div id="genSecTour'+i+'" style="width:100%;height:80px;"></div>';
			str+='</div>'
		  }
		}
		$("#userTypeWiseTopFiveStrongAndPoorToursMemsDivId").html(str);
	   if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameArray = [];
				var totalTourArr = [];
				var countVar =0;
			  if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						countVar =countVar+1;
						candidateNameArray.push(result[i][j].name);
						totalTourArr.push(result[i][j].totalTour);
						if (countVar === 5) {
							break;
						}
					}
				}
		if(result[i][0].totalTour!=0){
				var getWidth = $("#genSecTour"+i).parent().width()+'px';
				$("#genSecTour"+i).width(getWidth);
		     $(function () {
			$('#genSecTour'+i).highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: candidateNameArray,
					title: {
						text: null
					},
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
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>'
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
									return Highcharts.numberFormat(this.y) +"";
								}
							}
						  
						}
					}
				},
				legend: {
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
				credits: {
					enabled: false
				},
				
				series: [{
					name: 'Number Of Tours',
					data: totalTourArr
				}]
			});
		});
		}else{
		$("#genSecTour"+i).html("No Data Available");
		$("#genSecTour"+i).css("height","35px");
		$("#genSecTour"+i).hide();
		} 
	}
	}else{
    $("#userTypeWiseTopFiveStrongAndPoorToursMemsDivId").html('NO DATA AVAILABLE.');
	}
	}
	
	function buildgUserTypeWiseToursTopFivePoorRslt(result){
		var str='';
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				 if(result[i][0].designationId==4 || result[i][0].designationId==11){
				  if(result[i][0].designationId==4){
				   str+='<h5 class="text-capital">'+result[i][0].designation+' / SECRETARY </h5>';      
				  }
				  if(result[i][0].designationId==11){
				   str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].designation+'</h5>';      
				  }
			   }else{
				str+='<h5 class="text-capital">'+result[i][0].designation+'</h5>'; 
			   }
				str+='<div id="genSecTour'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#userTypeWiseTopFiveStrongAndPoorToursMemsDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var totalTourArr = [];
				var countVar = 0;
				if(result[i] != null && result[i].length > 0){
					var length = result[i].length - 1;
					for(var j = length; j >= 0; j--){
						candidateNameArray.push(result[i][j].name);
						  totalTourArr.push(result[i][j].totalTour);
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}	
				}
			//if( result[i][j].totalTour!=0){
		//if(totalTourArr.length > 0){	
			var getWidth = $("#genSecTour"+i).parent().width()+'px';
				$("#genSecTour"+i).width(getWidth);
				$(function () {
			   $('#genSecTour'+i).highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					categories: candidateNameArray,
					title: {
						text: null
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}%</b>'
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
									return Highcharts.numberFormat(this.y,2) +"%";
								}
							}
						  
						}
					}
				},
				legend: {
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
				credits: {
					enabled: false
				},
				series: [{
					name: 'Number Of Tours',
					data: totalTourArr
				}]
			});
		});
		//}
		/* }else{
		$("#genSecTour"+i).html("No Data Available");
		$("#genSecTour"+i).css("height","35px");	
		} */
		}
	}else{
	 $("#userTypeWiseTopFiveStrongAndPoorToursMemsDivId").html('NO DATA AVAILABLE.');
	}
	} 