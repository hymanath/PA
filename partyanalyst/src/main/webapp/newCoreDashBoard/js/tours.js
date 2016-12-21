var customStartDateTours = moment().startOf('month').format('DD/MM/YYYY')

var customEndDateTours = moment().format('DD/MM/YYYY');
	$("#tourDateRangePickerId").daterangepicker({
		opens: 'left',
	     startDate: moment().startOf('month'),
         endDate:moment,  
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
     $("#toursHeadingId").html("This Month( "+dates+" )");
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
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#tourOverviewDivId").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : globalTourFormDate,
					 toDate : glovalTourToDate,
					 userTypeId : globalUserTypeId
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
		var toursDesignationIdsString;
		 var str='';
		  str+'<div class="row">';
		  if(overAllResult != null){
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >';
			 str+='<h4>OVERALL</h4>';
			 str+='<div class="table-responsive">';
		      str+='<table class="table tableTraining bg_ED m_XsTop10">';
			  str+='<tbody><tr>';
				  str+='<td>';
				     if(overAllResult.noOfLeaderCnt > 0){
						str+='<h4 id="overallTourLdrDsgntnId" attr_designation_name="OVERALL"  attr_tour_submitted_type="All" class="overAllToursCls" style="cursor:pointer;color:rgb(51, 122, 183)">'+overAllResult.noOfLeaderCnt+'</h4>';
				     }else{
					   str+='<h4>0</h4>';
					 }
					  str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
				  str+='</td>';
				  str+='<td>';
				     if(overAllResult.submitedLeaderCnt > 0){
						str+='<h4 id="submitedTourLdrDsgntnId" attr_designation_name="OVERALL" attr_tour_submitted_type="Yes" class="overAllToursCls" style="cursor:pointer;color:rgb(51, 122, 183)">'+overAllResult.submitedLeaderCnt+'<span class="font-10 text-success"> '+overAllResult.submitedCandidateTourPer+'%</span></h4>';
				      }else{
						str+='<h4>0<span class="font-10 text-success">0.0%</span></h4>';
					 }
					  str+='<p class="text-muted text-capital">Submited<br>Leaders</p>';  
				  str+='</td>';
				  str+='<td>';
				  if(overAllResult.notSubmitedLeaserCnt > 0){
					str+='<h4 id="notSubmitedTourLdrDsgntnCntId" attr_tour_submitted_type="No" attr_designation_name="OVERALL" class="overAllToursCls" style="cursor:pointer;color:rgb(51, 122, 183)">'+overAllResult.notSubmitedLeaserCnt+'<span class="font-10 text-danger"> '+overAllResult.notsubmitedCandidateTourPer+'%</span></h4>';
			        }else{
					  str+='<h4>0<span class="font-10 text-danger">0.0%</span></h4>';
				  }
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
			var strIds='';
		   for(var i in designationWiseRlst){
			   if(designationWiseRlst[i].id==4){
				  strIds='4,5'; 
			   }else{
				strIds=designationWiseRlst[i].id;  
			   }
				if(i== 0){
				 toursDesignationIdsString = strIds;	
				 }else{
				  toursDesignationIdsString = toursDesignationIdsString+','+strIds;	
				}
			    str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >';
				str+='<h4>'+designationWiseRlst[i].designation+'<i style="cursor: pointer; font-size: 16px; margin-left: 30px;" class="glyphicon glyphicon-info-sign tourDocCls" attr_desig_id="'+strIds+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Get Tour Details"></i></h4>';        
				  str+='<div class="table-responsive">';  
				  str+='<table class="table tableTraining bg_ED m_XsTop10">';
				  str+='<tbody><tr>';
					  str+='<td>';
					      str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
					      if(designationWiseRlst[i].noOfLeaderCnt > 0){
						    str+='<h4 attr_dsgntn_ids='+strIds+' attr_tour_submitted_type="All" attr_designation_name="'+designationWiseRlst[i].designation+'" class="overAllToursCls" style="cursor:pointer;color:rgb(51, 122, 183)" >'+designationWiseRlst[i].noOfLeaderCnt+'</h4>';
			 			  }else{
						    str+='<h4>0</h4>';
			  		      }
					  str+='</td>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Submited<br>Leaders</p>';  
						  if(designationWiseRlst[i].submitedLeaderCnt > 0){
								  str+='<h4 attr_dsgntn_ids='+strIds+' attr_tour_submitted_type="Yes" attr_designation_name="'+designationWiseRlst[i].designation+'"  class="overAllToursCls" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseRlst[i].submitedLeaderCnt+'<span class="font-10 text-success"> '+designationWiseRlst[i].submitedCandidateTourPer.toFixed(2)+'%</span></h4>';
					      }else{
								 str+='<h4>0<span class="font-10 text-success">0.0%</span></h4>';
				          }
					  str+='</td>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Not Submited<br>Leaders</p>';  
						  if(designationWiseRlst[i].notSubmitedLeaserCnt > 0){
								  str+='<h4 attr_dsgntn_ids='+strIds+' attr_tour_submitted_type="No" attr_designation_name="'+designationWiseRlst[i].designation+'"  class="overAllToursCls" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseRlst[i].notSubmitedLeaserCnt+'<span class="font-10 text-danger"> '+designationWiseRlst[i].notsubmitedCandidateTourPer.toFixed(2)+'%</span></h4>';
					      }else{
							    str+='<h4>0<span class="font-10 text-danger">0.0%</span></h4>';
						   }
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
		  str+='<h4>No Data Available</h4>';	  
		  }
	     $("#tourOverviewDivId").html(str);
		 $('[data-toggle="tooltip"]').tooltip();      
		 $("#overallTourLdrDsgntnId").attr("attr_dsgntn_ids",toursDesignationIdsString);  
		 $("#submitedTourLdrDsgntnId").attr("attr_dsgntn_ids",toursDesignationIdsString);  
		 $("#notSubmitedTourLdrDsgntnCntId").attr("attr_dsgntn_ids",toursDesignationIdsString);  

	 } 
	 function getDistrictWiseToursSubmitedDetails()
		{   
			$("#districtWiseLeaderDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
			{
				$("#districtWiseLeaderDiv").html(' ');
				 return;
			}
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
		$("#districtWiseLeaderDiv").html("<h4>No Data Available</h4>");
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
			toDate :  glovalTourToDate,
			globalUserTypeId : globalUserTypeId,
			level : ""  
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
				tooltip: {formatter: function(){
					return '<b>Total Tour:'+ Highcharts.numberFormat(this.y, 0) +'</b><br/>'+
                    'Name: '+ this.x;   
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
				tooltip: {formatter: function(){
					return '<b>Total Tour:'+ Highcharts.numberFormat(this.y, 0) +'</b><br/>'+
                    'Name: '+ this.x; 
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
									return Highcharts.numberFormat(this.y,0);
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
			globalUserTypeId : globalUserTypeId
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
						if(result.totalTour == 0){
							var average = 0;        
						}else{  
							var average = result.totalTour / result.inchargerToursCnt;        
						}    
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
			toDate : glovalTourToDate,
			outPutType : ""           
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
								str+='<tr class="candidateCls lowLevelActivityMemberClsForTour" attr_desig_id="'+result[i].designationId+'" attr_activity_mem_id="'+result[i].activityMemberId+'" attr_candiate_id="'+result[i].id+'" attr_cand_name="'+result[i].name+'" attr_cand_desig="'+result[i].designation+'">'; 
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
								str+='<tr class="showHideTr" style="display:none" attr_id = "subLevelMemDtslId'+result[i].designationId+''+i+'">';
								str+='<td colspan="5"  id="subLevelMemDtslId'+result[i].designationId+''+i+'">';  
								str+='</td>';
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
		//$("#tourLeaderDtlsId").dataTable();              
		getLeaderAverageToursBasedOnAccessLevel(candidateId,candidateName,gesignation);
	}
	$(document).on("click",".lowLevelActivityMemberClsForTour",function(){ 
	    $(this).next('tr.showHideTr').show(); 
		var activityMemberId = $(this).attr("attr_activity_mem_id");  
		var userTypeId = $(this).attr("attr_desig_id"); 
		var selectedMemberName = $(this).attr("attr_cand_name");  
		var selectedUserType = $(this).attr("attr_cand_desig");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
		if(selectedUserType != null && selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){ 
		}else{
			getDirectChildActivityTourMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		}	
	});
	function getDirectChildActivityTourMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
		$("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
			activityMemberId : activityMemberId,        
			stateId : globalStateIdForTour,
			fromDate : globalTourFormDate,              
			toDate :  glovalTourToDate,
			globalUserTypeId : userTypeId,
			level : "bellow"    
		}
		$.ajax({
			type : 'POST',         
			url : 'getDesigWiseMemberDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}         
			}).done(function(result){
				$("#"+childActivityMemberId).html('');
				if(result != null && result.length > 0){
					buildChildActivityMembersDetailsForTour(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId);
				}else{
					if(childActivityMemberId == "userTypeWiseChildDtlsTabId"){
						$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
					}
				}
			});
	}
	function buildChildActivityMembersDetailsForTour(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId){
		
		var str = '';
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		/* if($(window).width() < 768){
			str+='<div class="table-responsive">';
		} */ 
		if(childActivityMemberId != "userTypeWiseChildDtlsTabId"){
				str+='<span class="remveSlcUsrType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
		 if(childActivityMemberId != "userTypeWiseChildDtlsTabId"){         
			 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
		 }else{
			str+='<table class="table table-condensed tableHoverLevels m_top20">';  
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
		var candidateId = result[0][0].id;
		var candidateName = result[0][0].name;
		var gesignation = result[0][0].designation;
		for(var i in result){
			for(var j in result[i]){
				comment  = "";
				if(result[i][j].totalTour > 0){       
					str+='<tr class="candidateCls" attr_desig_id="'+result[i][j].designationId+'" attr_activity_mem_id="'+result[i][j].activityMemberId+'" attr_candiate_id="'+result[i][j].id+'" attr_cand_name="'+result[i][j].name+'" attr_cand_desig="'+result[i][j].designation+'">'; 
					k = parseInt(k) + 1;      
					str+='<td>';
						str+='<span class="tableCount">'+k+'</span>';
					str+='</td>';
					str+='<td>'+result[i][j].designation+'</td>';    
					str+='<td>'+result[i][j].name+'</td>';
					str+='<td>'+result[i][j].totalTour+'</td>';
					if(result[i][j].remarkList.length > 0 && result[i][j].remarkList != null){  
						for(var k in result[i][j].remarkList){
							if(result[i][j].remarkList[k] != null && result[i][j].remarkList[k].length > 2){ 
								comment = comment + result[i][j].remarkList[k];            
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
					str+='<tr class="showHideTr" style="display:none" attr_id = "subLevelMemDtslId'+result[i][j].designationId+''+i+'">';
					str+='<td colspan="5"  id="subLevelMemDtslId'+result[i][j].designationId+''+i+'">';  
					str+='</td>';
					str+='</tr>';
				}
			}
		}     			
		str+='</tbody>';  
		str+='</table>';  
		/* if($(window).width() < 768){
			str+='</div>';
		} */
		$("#"+childActivityMemberId).html(str);
		//$("#tourLeaderDtlsId").dataTable();              
		//getLeaderAverageToursBasedOnAccessLevel(candidateId,candidateName,gesignation);
	}
	$(document).on("click",".remveSlcUsrType",function(){
		 var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		 $("#"+removeSelected).html(' ');
		 $("#"+removeSelected).closest('.showHideTr').hide();
	});
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
					 toDate :  glovalTourToDate,
					 userTypeId : globalUserTypeId
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
	$(document).on("click",".overAllToursCls",function(){
		var tourSubmittedType = $(this).attr("attr_tour_submitted_type");
		var designationIdsStr = $(this).attr("attr_dsgntn_ids");
		var designationName = $(this).attr("attr_designation_name");
		$("#cadreExcelExpBtnId").attr("attr_tab_user_type","Tour");
		$("#cadreExcelExpBtnId").hide();
		var designationIds;
		if(designationIdsStr != null && designationIdsStr != undefined){
		  designationIds=designationIdsStr.split(",");	
		}
		 getTourSubmittedLeadersDetails(tourSubmittedType,designationIds,designationName);
	});
   function getTourSubmittedLeadersDetails(tourSubmittedType,designationIds,designationName)
	{   
		$("#locationWiseCadreReportHeadingId").html("<b>"+designationName+"</b> - Tours Submitted Details Report");
		$("#locationWiseCadreReportModalId").modal("show");
		$("#locationWiseCadreReportDivId").html(' ');
		$("#locationWiseProcessImgReport").show();
	 	var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 designationIds : designationIds, 
					 isTourSubmitted : tourSubmittedType,
					 fromDate : globalTourFormDate,
					 toDate :  glovalTourToDate,
					 stateId : globalStateIdForTour,
					 userTypeId : globalUserTypeId
				  }
		$.ajax({
			type : 'POST',
			url : 'getTourSubmittedLeadersDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#locationWiseProcessImgReport").hide();
		   if(result != null && result.length > 0){
			   buildToursSubmittedDtls(result,tourSubmittedType);
		   }else{
			 $("#locationWiseCadreReportDivId").html("NO DATA AVAILABLE.");  
		   }
 		}); 
	}
	function buildToursSubmittedDtls(result,tourSubmittedType){
		$("#cadreExcelExpBtnId").show();
	 var str='';
	 str+='<div class="table-responsive">';
	 	str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed " id="tourSubmittedDtlsDataTblid">';   
	   str+='<thead>';
             str+='<th>Name</th>';
			 str+='<th>Designation</th>';
			 str+='<th>Tour Submitted</th>'
             if(tourSubmittedType!="No"){
			  str+='<th>No Of Tours</th>';	 
			 }			 
		 str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
				str+='<tr>';
				 if(result[i].name != null && result[i].name.length > 0){
					str+='<td>'+result[i].name+'</td>';      
				  }else{
					str+='<td> - </td>';  
				  }
				  if(result[i].designation != null && result[i].designation.length > 0){
					str+='<td>'+result[i].designation+'</td>';  
				  }else{
				    str+='<td> - </td>';  
				  }
				  if(result[i].isTourSubmitted != null && result[i].isTourSubmitted.length > 0){
							str+='<td>'+result[i].isTourSubmitted+'</td>';  
					  }else{
						  str+='<td> - </td>';  
					  }
				if(tourSubmittedType!="No"){
					if(result[i].totalSubmittedToursCnt != null && result[i].totalSubmittedToursCnt > 0){
						str+='<td>'+result[i].totalSubmittedToursCnt+'</td>';  
					  }else{
					  str+='<td> - </td>';  
					  }
					str+='</tr>';
				}
			}
			 str+='</tbody>';
			 str+='</table>';
			  str+='</div>';
		 $("#locationWiseCadreReportDivId").html(str);
		 $("#tourSubmittedDtlsDataTblid").dataTable({
				 "aaSorting": [[ 1, "desc" ]], 
				"iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
		 }); 
	}
		
	function generateExcelReportForToursDetails(){
		tableToExcel(tourSubmittedDtlsDataTblid, 'Tours Submitted Details Report');
	}
	$(document).on('click','.tourDocCls',function(){
		$("#cdrModelDivId").modal("show");
		$("#tourDocHeadingId").html("Leaders Submitted Documents");
		$("#cdrModelId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		var desigId = $(this).attr("attr_desig_id");
		
		var desigIds =desigId.split(",");
		var desigIdArr = [];
		for(var i in desigIds){ 
			desigIdArr.push(desigIds[i]);    
		}  
		var jsObj ={ 
			activityMemberId : globalActivityMemberId,
			designationIds : desigIdArr,                  
			stateId : globalStateIdForTour,      
			fromDate : globalTourFormDate,                
			toDate : glovalTourToDate,  
			outPutType : "document"
		}
		$.ajax({   
			type : 'POST',    
			url : 'getMemberDtlsForADesignationAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}         
		}).done(function(result){
			$("#cdrModelId").html('');     
			if(result != null && result.length > 0){ 
				buildDocumentDtls(result);
			}        
		});
	});
	function buildDocumentDtls(result){
		
		var str='';
		str+='<div class="table-responsive">';
	 	str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed " id="tourDocumentTblid">';     
		str+='<thead>';
             str+='<th>Name</th>';
			 str+='<th>Designation</th>';
			 str+='<th>Year</th>';
			 str+='<th>Month</th>';
			 str+='<th>comment</th>';
			 str+='<th>document</th>';  
			 str+='<th>Own Tour</th>'
			 str+='<th>Incharge Tour</th>'  
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
			if(result[i].name != null && result[i].name.length > 0){
				str+='<td>'+result[i].name+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].designation != null && result[i].designation.length > 0){
				str+='<td>'+result[i].designation+'</td>';  
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].year != null){
				str+='<td>'+result[i].year+'</td>';  
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].month != null && result[i].month.length > 0){
				str+='<td>'+result[i].month+'</td>';  
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].comment != null && result[i].comment.length > 0){
				str+='<td>'+result[i].comment+'</td>';  
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].filePath != null && result[i].filePath.length > 0){
				var fullName = result[i].filePath;
				var nameArr = fullName.split(".");
				var type = nameArr[1];
				if(type=="pdf" || type=="PDF"){
					str+='<td id="showPdfId" attr_filePath="'+result[i].filePath+'" style="cursor:pointer;"><span><img src="images/pdf.jpg" class="media-object" alt="" style="width:30px;"/></td>';
				}else if(type=="xls" ||type=="xlsx"){  
					str+='<td id="showPdfId" attr_filePath="'+result[i].filePath+'" style="cursor:pointer;"><span><img src="images/excel.jpg" class="media-object" alt="" style="width:30px;"/></td>';       
				}else if(type=="doc" || type=="docx"){
					str+='<td id="showPdfId" attr_filePath="'+result[i].filePath+'" style="cursor:pointer;"><span><img src="images/word.jpg" class="media-object" alt="" style="width:30px;"/></td>';         
				}else if(type != null){  
					str+='<td id="showPdfId" attr_filePath="'+result[i].filePath+'" style="cursor:pointer;"><span><img src="images/fileImage.png" class="media-object" alt="" style="width:30px;"/></td>';         
				}           
			}else{    
				str+='<td> - </td>';  
			}
			if(result[i].ownTours != null){
				str+='<td>'+result[i].ownTours+'</td>';  
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].inchargerTours != null){
				str+='<td>'+result[i].inchargerTours+'</td>';  
			}else{
				str+='<td> - </td>';  
			}
			str+='</tr>';
		}
		 str+='</tbody>';
		 str+='</table>';
		str+='</div>';
		$("#cdrModelId").html(str);    
		$("#tourDocumentTblid").dataTable({
			"aaSorting": [[ 1, "desc" ]], 
			"iDisplayLength" : 10,
			"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
		}); 
	}
	$(document).on('click','#showPdfId',function(){
		var dbFilePath = $(this).attr("attr_filePath");         
		var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
			$("#tourDocumentId").modal("hide");
			window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open('http://ieee802.org/secmail/docIZSEwEqHFr.doc','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}else{
			
			if(extName.trim()=="pdf" || extName.trim()=="PDF"){
				$("#tourDocumentId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}
			if(extName.trim()=="jpg"){  
				$("#tourDocumentId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}              
			if(extName.trim()=="doc" || extName.trim()=="docx"){
				$("#tourDocumentId").modal("show");
				str += '<iframe src="https://docs.google.com/gview?url=http://mytdp.com/Reports/tour_documents/'+dbFilePath+'&embedded=true" frameborder="0" style="width: 100%; height: 500px;">';
				str += '</iframe>';
			}
			if(extName.trim()=="xls" || extName.trim()=="xlsx"){      
				window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			}            
			$("#tourDocumentBodyId").html(str);
			//window.open(wurl+'/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			// window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}      
	});
	
	
	
	/* New Tours Ajax Call Based on New Screen */
	
	$(document).on("click",".newTourExpandIcon",function(){
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".tourNewBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".tourNewBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	});
	
	//getToursBasicOverviewDtls();
	function getToursBasicOverviewDtls()
	{    
	  var globalUserTypeId = 2;

		$("#tourOverviewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#tourOverviewDivId").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : 44,
					 stateId : globalStateIdForTour,
					 fromDate : "",
					 toDate : "",
					 userTypeId : globalUserTypeId
				  }
		$.ajax({
			type : 'POST',
			url : 'getToursBasicOverviewDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null ){
			  buildTourOverviewRslt(result);	
			}else{
			 $("#tourOverviewNewDivId").html("NO DATA AVAILABLE.");	
			}
		});
	}
	function buildTourOverviewRslt(result){
		var overViewRslt = result.overAllDetailsVO;
		var designationWiseList = result.subList;
		var str='';
		 if(overViewRslt != null){
				   str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4><span class="headingColor text-capital">Leaders</span></h4>';
					str+='<div id="" class="m_top10">';
						str+='<div class="pad_10 bg_ED">';
							str+='<div class="row">';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
									    if(overViewRslt.noOfLeaderCnt > 0){
										  str+='<h3 class="tourOverViewCls">'+overViewRslt.noOfLeaderCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<p class="text-muted text-capital">total leaders</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
									   if(overViewRslt.submitedLeaderCnt > 0){
										  str+='<h3 class="tourOverViewCls">'+overViewRslt.submitedLeaderCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<small class="text-success" style="font-size:13px">'+overViewRslt.submitedCandidateTourPer+'%</small></h3>';
										str+='<p class="text-muted text-capital">Submitted leaders</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
									    if(overViewRslt.notSubmitedLeaserCnt > 0){
										  str+='<h3 class="tourOverViewCls">'+overViewRslt.notSubmitedLeaserCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<small class="text-success" style="font-size:13px">'+overViewRslt.notsubmitedCandidateTourPer+'%</small></h3>';
										str+='<p class="text-muted text-capital">Not Submitted leaders</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
									   if(overViewRslt.submitedLeaderCnt > 0){
										  str+='<h3 class="tourOverViewCls">'+overViewRslt.submitedLeaderCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<p class="text-muted text-capital">Submitted leaders</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
								 	  if(overViewRslt.complainceCnt > 0){
										  str+='<h3 class="tourOverViewCls">'+overViewRslt.complainceCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<p class="text-muted text-capital">Complaince</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
										if(overViewRslt.nonComplainceCnt > 0){
										  str+='<h3 class="tourOverViewCls">'+overViewRslt.nonComplainceCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<p class="text-muted text-capital">Non-Complaince</p>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';	
		    }
		    if(designationWiseList != null && designationWiseList.length > 0){
				for(var i in designationWiseList){
				   str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
							str+='<h4><span class="text-capital">'+designationWiseList[i].name+'</span></h4>';
							str+='<div class="bg_ED m_top10">';
								str+='<div class="row">';
									str+='<div class="col-md-12 col-xs-12 col-sm-12">';
										str+='<div class="table-responsive">';
											str+='<table class="table border_top_none">';
												str+='<tr>';
													str+='<td class="text-muted f_14">Total Leaders</td>';
													str+='<td class="text-muted f_14">Not Submitted</td>';
													str+='<td class="text-muted f_14">Submitted</td>';
													str+='<td class="text-muted f_14">Complaince</td>';
													str+='<td class="text-muted f_14">Non-Complaince</td>';
												str+='</tr>';
												str+='<tr>';
													str+='<td><h4 class="responsiveFont">'+designationWiseList[i].noOfLeaderCnt+'</h4></td>';
													str+='<td><h4 class="responsiveFont">'+designationWiseList[i].notSubmitedLeaserCnt+'<small class="text-success">'+designationWiseList[i].notsubmitedCandidateTourPer+'%</small></h4></td>';
													str+='<td><h4 class="responsiveFont">'+designationWiseList[i].submitedLeaderCnt+'<small class="text-success">'+designationWiseList[i].submitedCandidateTourPer+'%</small></h4></td>';
													str+='<td><h4 class="responsiveFont">'+designationWiseList[i].complainceCnt+'</h4></td>';
													str+='<td><h4 class="responsiveFont">'+designationWiseList[i].nonComplainceCnt+'</h4></td>';
												str+='</tr>';
											str+='</table>';
										str+='</div>';
										str+='<hr style="margin: 10px 0 0;border-top: 1px solid #ccc"/>';
									str+='</div>';
									str+='<div class="col-md-12 col-xs-12 col-sm-12">';
										str+='<div class="table-responsive">';
										str+='<table class="table tableEMN ">';
											str+='<thead>';
												str+='<th style="border-bottom:none !important"></th>';
												str+='<th class="text-muted f_14">Submited</th>';
												str+='<th class="text-muted f_14">Compliance</th>';
												str+='<th class="text-muted f_14">Non-Compliance</th>';
											str+='</thead>';
											str+='<tbody>';
											 var categoryList = designationWiseList[i].subList3; 
											 if(categoryList != null && categoryList.length > 0){
												 for(var j in categoryList){
												    str+='<tr>';
													if(categoryList[j].name != null && categoryList[j].name.length > 0){
													str+='<td style="border-top:none !important">'+categoryList[j].name+'</td>';	
													}else{
													str+='<td style="border-top:none !important"> - </td>';	
													}
													str+='<td class="bg_D8">'+categoryList[j].submitedLeaderCnt+'</td>';
													str+='<td class="bg_D8">'+categoryList[j].complainceCnt+'</td>';
													str+='<td class="bg_D8">'+categoryList[j].nonComplainceCnt+'</td>';
												   str+='</tr>'; 
												 }
											 }
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';	
				}
			}
		    $("#tourOverviewNewDivId").html(str);       
	}
	
	
	$(document).on("click",".tourComplainceCls",function(){
		  var resultType=$(this).attr("attr_value");
		 if(resultType != null && resultType == "strong"){
		  buildgDesignationWiseToursTopFiveComplainceRslt(globalUserTypeWiseTourComplainceRslt); 
		 }else if(resultType == "poor"){
		  buildgDesignationWiseToursPoorFiveComplainceRslt(globalUserTypeWiseTourComplainceRslt);
		 }
    });
	
	
    var globalUserTypeWiseTourComplainceRslt;  
	 //getDesignationWiseMembersDtls();
	function getDesignationWiseMembersDtls()
	{  
 	     var globalUserTypeId = 2;
		$("#tourOverviewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#tourOverviewDivId").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : 44,
					 stateId : globalStateIdForTour,
					 fromDate : "",
					 toDate : "",
					 userTypeId : globalUserTypeId
				  }
		$.ajax({
			type : 'POST',
			url : 'getDesignationWiseMembersDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#buildgDesignationWiseToursTopFiveComplainceDivId").html(' ');
			 buildgDesignationWiseToursTopFiveComplainceRslt(result);
			 globalUserTypeWiseTourComplainceRslt = result;
		});
	}
	
	function buildgDesignationWiseToursTopFiveComplainceRslt(result){
		var str='';
		if(result != null && result.length > 0){
		  var str='';
		  for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				 if(result[i][0].designationId==4 || result[i][0].designationId==5){
				  if(result[i][0].designationId==4){
				  if(result[i][0].complaincePer!=0){
					  str+='<h5 class="text-capital">'+result[i][0].designation+' / SECRETARY</h5>';      
				  }
				  }
				  if(result[i][0].designationId==5){
				   if(result[i][0].complaincePer!=0){
					 str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].designation+'</h5>';      
				   }
			     }
			   }else{ 
				 if(result[i][0].complaincePer!=0){
					str+='<h5 class="text-capital">'+result[i][0].designation+'</h5>'; 
				 }
		      }
			  str+='<div id="designationWiseComplainceTour'+i+'" style="height:80px;"></div>';
			str+='</div>'
		  }
		}
		$("#buildgDesignationWiseToursTopFiveComplainceDivId").html(str);
	   if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameArray = [];
				var totalComplainceArr = [];
				var countVar =0;
			  if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						countVar =countVar+1;
						candidateNameArray.push(result[i][j].name);
						totalComplainceArr.push(result[i][j].complaincePer);
						if (countVar === 5) {
							break;
						}
					}
				}
		if(result[i][0].complaincePer!=0){
			var getWidth = $("#designationWiseComplainceTour"+i).parent().width()+'px';
			$("#designationWiseComplainceTour"+i).width(getWidth);
		    $(function () {
			  $('#designationWiseComplainceTour'+i).highcharts({
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
				tooltip: {formatter: function(){
					return '<b>Tour Complaince:'+ Highcharts.numberFormat(this.y, 2) +'%</b><br/>'+
                    'Name: '+ this.x;   
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
					name: 'Complaince',
					data: totalComplainceArr
				}]
			});
		});
		}else{
		$("#designationWiseComplainceTour"+i).html("No Data Available");
		$("#designationWiseComplainceTour"+i).css("height","35px");
		$("#designationWiseComplainceTour"+i).hide();
		} 
	}
	}else{
    $("#buildgDesignationWiseToursTopFiveComplainceDivId").html('NO DATA AVAILABLE.');
	}
	}
	
	function buildgDesignationWiseToursPoorFiveComplainceRslt(result){
		
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
				str+='<div id="designationWiseComplainceTour'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#buildgDesignationWiseToursTopFiveComplainceDivId").html(str);
	  if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var totalComplainceArr = [];
				var countVar = 0;
				if(result[i] != null && result[i].length > 0){
					var length = result[i].length - 1;
					for(var j = length; j >= 0; j--){
						candidateNameArray.push(result[i][j].name);
						  totalComplainceArr.push(result[i][j].complaincePer);
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}	
				}
			//if( result[i][j].complaincePer!=0){
		//if(totalComplainceArr.length > 0){	
			var getWidth = $("#designationWiseComplainceTour"+i).parent().width()+'px';
				$("#designationWiseComplainceTour"+i).width(getWidth);
				$(function () {
			   $('#designationWiseComplainceTour'+i).highcharts({
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
				tooltip: {formatter: function(){
					return '<b>Total Complaince:'+ Highcharts.numberFormat(this.y, 2) +'%</b><br/>'+
                    'Name: '+ this.x; 
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
									return Highcharts.numberFormat(this.y,2)+"%";
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
					name: 'Complaince',
					data: totalComplainceArr
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
	 $("#buildgDesignationWiseToursTopFiveComplainceDivId").html('NO DATA AVAILABLE.');
	}
	} 
	
	//getDesignationWiseAverageTourPerformanceDtls();
	function getDesignationWiseAverageTourPerformanceDtls()
	{     var globalUserTypeId = 2;
		$("#tourOverviewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#tourOverviewDivId").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : 44,
					 stateId : globalStateIdForTour,
					 fromDate : "",
					 toDate : "",
					 userTypeId : globalUserTypeId
				  }
		$.ajax({
			type : 'POST',
			url : 'getDesignationWiseAverageTourPerformanceDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			console.log(result);
		});
	}
	
	