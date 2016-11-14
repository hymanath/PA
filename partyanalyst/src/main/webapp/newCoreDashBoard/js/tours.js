var customStartDateTours = moment().startOf('month').format('DD/MM/YYYY')

var customEndDateTours = moment().format('DD/MM/YYYY');
	$("#tourDateRangePickerId").daterangepicker({
		opens: 'left',
	     startDate: moment().subtract(parseInt(91)+parseInt(getDay()), 'days'),
         endDate: moment().subtract(parseInt(getDay()), 'days'),   //moment().endOf('month'),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	});
	function getDay(){
		var date = new Date();
		var dd = date.getDate(); 
		return dd;
	}
	   var globalTourFormDate;
	   var glovalTourToDate;
	   var dates=$("#tourDateRangePickerId").val();
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			globalTourFormDate = datesArr[0]; 
			glovalTourToDate = datesArr[1]; 
		}
     $("#toursHeadingId").html("Last 3 Months( "+dates+" )");
     $('#tourDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
	   var dates= $("#tourDateRangePickerId").val();
	   $("#toursHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
	   	if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			globalTourFormDate = datesArr[0]; 
			glovalTourToDate = datesArr[1]; 
		}
	    getToursBasicOverviewCountDetails();
		getDesigWiseMemberDtls();
		getDesigListForTour();    
	 });
	
$(document).on("click",".tourExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".toursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
    $(".hideShowToursDateRangeCls").show();	
	$(".tourExpandCls").show();
	getDesigWiseMemberDtls();  
	setTimeout(function(){
		$(".toursHiddenBlock").toggle();      
	},800);
});
$(document).on("click",".tourExpand",function(){
	if( $(this).find("i").hasClass( "glyphicon glyphicon-fullscreen" )){
		$(".moreToursBlocks1").hide();     
		$(".tourExpandCls").hide();     
		$(".moreToursBlocksDetailed").hide();     
		$(".comparisonBlockTours").hide();  
		$(".hideShowToursDateRangeCls").hide();	
	}    
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
//swadhin
$(document).on("click",".toursComparisionBlock",function(){
	$(".moreToursBlocksDetailed").hide();
	$(".comparisonBlockTours").show();
	// getTopPoorToursLocationDetails(userTypeId,selectedUserName,userType);
	   getDesigListForTour();
});
var globalStateIdForTour=1; //for 
function getToursBasicOverviewCountDetails()
	{    
		$("#tourOverviewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : globalTourFormDate,
					 toDate : glovalTourToDate                      
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
			 str+='<div class="table-responsive">';
			 str+='<h4>OVERALL</h4>';
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
		   str+='</div>';
		  str+='<hr class="m_0">';
		  str+='</div>';  
		  }else{
			    str+='<h4>No Data Available</h4>';	 
		  }
		 if(designationWiseRlst != null && designationWiseRlst.length > 0){
		   for(var i in designationWiseRlst){
			    str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >';
				  str+='<div class="table-responsive">';
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
			   str+='</div>';
			  str+='<hr class="m_0">';
			  str+='</div>';  
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
						 fromDate : globalTourFormDate,
					     toDate :  glovalTourToDate,
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
									name: 'Average Tours',
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
					 activityMemberId : globalActivityMemberId, 
					 stateId : globalStateIdForTour,
					 userTypeId:userTypeId,
					 fromDate : globalTourFormDate,
					 toDate :  glovalTourToDate
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
	  if(userTypeId!= null && userTypeId==1 || userTypeId==2 || userTypeId==3 ||  userTypeId==4 || userTypeId==5){
		str+='<p class="text-capital">districts<span style="margin-left:320px">Average Tours</span></p>';  
		resultListFirst = result.subList;
		//resultListSecond = result.subList2;
	  }
	  if(userTypeId!= null &&  userTypeId==6){
		 str+='<p class="text-capital">Parliament Constituency <span style="margin-left:320px">Average Tours</span></p>';  
		resultListFirst = result.subList;
		//resultListSecond = result.subList2;  
	  }
	   if(userTypeId!= null && userTypeId==7){
		 str+='<p class="text-capital">Constituencies<span style="margin-left:320px">Average Tours</span></p>';  
		resultListFirst = result.subList;
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
			activityMemberId : globalActivityMemberId,        
			stateId : globalStateIdForTour,
			fromDate : globalTourFormDate,              
			toDate :  glovalTourToDate      
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
				 if(result[i][0].designationId==4 || result[i][0].designationId==5){
				  if(result[i][0].designationId==4){
				  if(result[i][0].totalTour!=0){
					  str+='<h5 class="text-capital">'+result[i][0].designation+' / SECRETARY</h5>';      
				  }
				  }
				  if(result[i][0].designationId==5){
				   if(result[i][0].totalTour!=0){
					 str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].designation+'</h5>';      
				   }
			     }
			   }else{ 
				 if(result[i][0].totalTour!=0){
					str+='<h5 class="text-capital">'+result[i][0].designation+'</h5>'; 
				 }
		      }
			  str+='<div id="genSecTour'+i+'" style="height:80px;"></div>';
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
									return Highcharts.numberFormat(this.y, 0) +""; 
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
				 if(result[i][0].designationId==4 || result[i][0].designationId==5){
				  if(result[i][0].designationId==4){
				   str+='<h5 class="text-capital">'+result[i][0].designation+' / SECRETARY </h5>';      
				  }
				  if(result[i][0].designationId==5){
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
	
	function getDesigListForTour(){
		$("#designationListForTourId").html('<div class="col-md-12 col-xs-12 col-sm-12"> <div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#totalOverviewOfDesigId").html('<div class="col-md-12 col-xs-12 col-sm-12"> <div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#directChildMemberForToursDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"> <div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var jsObj ={ 
			activityMemberId : globalActivityMemberId,
		}
		$.ajax({
			type : 'POST',
			url : 'getDesignationLabelListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}         
			}).done(function(result){
				$("#designationListForTourId").html('');     
				if(result != null && result.length > 0){
					buildDesignationLabel(result);
				}       
			}); 
	}
	function buildDesignationLabel(result){  
		var str = '';
		if(result != null && result.length > 0){
			str += '<ul class="comparisonSelect">';
			for(var i in result){
				str += '<li class="singleDesigTourCls " id="singleDesigTourId'+i+'" attr_desig_ids="'+result[i].comment+'">'+result[i].name+'<span class="closeIconComparison"></span></li>';
			}
			str += '</ul>';    
		}
		$("#designationListForTourId").html(str);       
		$("#singleDesigTourId0").trigger("click"); 
	}
	$(document).on('click','#singleDesigTourId0',function(){
		var desigIs = $(this).attr("attr_desig_ids");
		var desigIdArr = [];
		desigIdArr = desigIs.split(",");
		getDesignationDtls(desigIdArr);
		getMemberDtlsForADesignation(desigIdArr);
		
	});  
	$(document).on('click','.singleDesigTourCls',function(){
		var desigIs = $(this).attr("attr_desig_ids");
		var desigIdArr = [];
		desigIdArr = desigIs.split(",");
		getDesignationDtls(desigIdArr);
		getMemberDtlsForADesignation(desigIdArr);
	});
	function getDesignationDtls(desigIdArr){  
		$("#totalOverviewOfDesigId").html('<div class="col-md-12 col-xs-12 col-sm-12"> <div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#directChildMemberForToursDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"> <div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj = { 
			 desigIds : desigIdArr,    
			 activityMemberId : globalActivityMemberId,  
			 startDateStr : globalTourFormDate,
			 endDateStr : glovalTourToDate  
			}
		$.ajax({  
			type : 'POST',
			url : 'getDesignationDtlsOfCandidateAction.action',  
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#totalOverviewOfDesigId").html('');
			if(result != null){
				buildDesignationDtls(result);  
			}
		});
	}
	function buildDesignationDtls(result){
		var str = '';
		if($(window).width() < 500)
		{
			str+='<div class="table-responsive">';
		}
		str+='<table class="table tableTraining bg_ED">';
			str+='<tbody>';
				str+='<tr>';
					str+='<td>';
						str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
						str+='<h4>'+result.candidateCount+'</h4>';
					str+='</td>';
					str+='<td>';
					var submitPercent = parseInt(result.selectedCandCount)*(100/parseInt(result.candidateCount));
						str+='<p class="text-muted text-capital">Submited<br>Leaders</p>';
						str+='<h4>'+result.selectedCandCount+'<span class="font-10 text-success">'+submitPercent.toFixed(2)+'%</span></h4>';
					str+='</td>';
					str+='<td>';
					var notSelect = parseInt(result.candidateCount) - parseInt(result.selectedCandCount);
					var notSelectPer = parseFloat(100.00)-parseFloat(submitPercent)
						str+='<p class="text-muted text-capital">Not Submited<br>Leaders</p>';
						str+='<h4>'+notSelect+'<span class="font-10 text-danger"> '+notSelectPer.toFixed(2)+'%</span></h4>';
					str+='</td>';
					str+='<td>';
						str+='<p class="text-muted text-capital">Total<br>Tours</p>';      
						str+='<h4>'+result.totalTour+'</h4>';  
					str+='</td>';
					str+='<td>';
					var average = result.totalTour / result.inchargerToursCnt;         
						str+='<p class="text-muted text-capital">Average<br>Tours</p>';
						str+='<h4>'+average.toFixed(2)+'</h4>';  
					str+='</td>';
				str+='</tr>';
			str+='</tbody>';
		str+='</table>';
		if($(window).width() < 500)
		{
			str+='</div>';
		}
		$("#totalOverviewOfDesigId").html(str);
	}
	function getMemberDtlsForADesignation(desigIdArr){ 
		$("#topPoorLocationsToursDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"> <div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');  
		$("#directChildMemberForToursDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"> <div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
			activityMemberId : globalActivityMemberId,
			designationIds : desigIdArr,                  
			stateId : globalStateIdForTour,      
			fromDate : globalTourFormDate,                
			toDate : glovalTourToDate 
		}
		$.ajax({
			type : 'POST',
			url : 'getMemberDtlsForADesignationAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}         
			}).done(function(result){
				$("#directChildMemberForToursDivId").html('');
				if(result != null && result.length > 0){
					buildMemberDtlsForADesignation(result);
				}        
			});
	}    
	function buildMemberDtlsForADesignation(result){
		var str = '';
		
			if($(window).width() < 768)
			{
				str+='<div class="table-responsive">';
			}
			
				str+='<table id="tourLeaderDtlsId" class="table table-condensed tableHoverLevels m_top20 bg_ED">';
					str+='<thead>';
						str+='<th>%RANK</th>';
						str+='<th>DESIGNATION</th>';
						str+='<th class="text-capital">NAME</th>';      
						str+='<th>TOTAL</th>';
						str+='<th>COMMENT</th>';
					str+='</thead>';
					str+='<tbody>';
						var comment  = "";
						var k = 0;
						var candidateId = result[0].id;
						var candidateName = result[0].name;
						var gesignation = result[0].designation;
						for(var i in result){
							comment  = "";
							if(result[i].totalTour > 0){         
								str+='<tr class="candidateCls" attr_candiate_id="'+result[i].id+'" attr_cand_name="'+result[i].name+'" attr_cand_desig="'+result[i].designation+'">'; 
									k = parseInt(k) + 1;      
									str+='<td>';
										str+='<span class="tableCount">'+k+'</span>';
									str+='</td>';
									str+='<td>'+result[i].designation+'</td>';    
									str+='<td>'+result[i].name+'</td>';
									str+='<td>'+result[i].totalTour+'</td>';
									if(result[i].remarkList.length > 0 && result[i].remarkList != null){  
										for(var j in result[i].remarkList){
											if(result[i].remarkList[j] != null && result[i].remarkList[j].length > 2){ 
												comment = comment + result[i].remarkList[j];            
												comment = comment + "</br>"
											}
										}
									} 
									if(comment.length > 2 && comment != null){       
										str+='<td>'+comment+'</td>';  
									}else{
										str+='<td>-</td>';
									}  
								str+='</tr>';   
							}
						}  
						
					str+='</tbody>';  
				str+='</table>';  
			if($(window).width() < 768)
			{
				str+='</div>';
			}
		
		$("#directChildMemberForToursDivId").html(str);
		$("#tourLeaderDtlsId").dataTable();              
		getLeaderAverageToursBasedOnAccessLevel(candidateId,candidateName,gesignation);
	}	 
  	$(document).on("click",".candidateCls",function(){
		var candiateId = $(this).attr("attr_candiate_id");
		var candidateName = $(this).attr("attr_cand_name");
		var gesignation = $(this).attr("attr_cand_desig");
		
		getLeaderAverageToursBasedOnAccessLevel(candiateId,candidateName,gesignation);
		
	});
	  function getLeaderAverageToursBasedOnAccessLevel(candidateId,candidateName,gesignation)
	{   $("#topPoorLocationsToursDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
					 candidateId : candidateId, 
					 stateId : globalStateIdForTour,
					 fromDate : globalTourFormDate,
					 toDate :  glovalTourToDate
				  }
		$.ajax({
			type : 'POST',
			url : 'getLeaderAverageToursBasedOnAccessLevelAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   buildTourDtlsLocationWiseReport(result,candidateName,gesignation);
 		}); 
	}
	function buildTourDtlsLocationWiseReport(result,candidateName,gesignation){
	 var str='';
	if(result != null){
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';  
			if(result.name != null){
				str+='<b><span class="color_333 pad_5 bg_CC text-capital"><span class="text-danger">'+result.name+'</span></span></b>';//'['+candidateName+'-'+gesignation+']
			}
		str+='</div>';
	  str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top10">';      
	  str+='<table class="table tableCumulative bg_ED">';
      if(result.subList != null && result.subList.length > 0){
		  var order=1;
		  var BGColor = 1;
		   var resultListFirst = result.subList;
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
	str+='</div>';
	}else{
	  str+='No Data Available';	
	}
	 $("#topPoorLocationsToursDivId").html(str);    
	 $('.progressCustom').tooltip();	
	}
	