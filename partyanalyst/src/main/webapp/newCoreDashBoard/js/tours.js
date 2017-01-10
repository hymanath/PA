var customStartDateTours = moment().startOf('month').format('DD/MM/YYYY')
var customEndDateTours = moment().format('DD/MM/YYYY');
	$("#tourDateRangePickerId").daterangepicker({
		opens: 'left',
	     startDate: moment().subtract(1, 'month').startOf('month'),
         endDate:moment().subtract(1, 'month').endOf('month'),  
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
     $("#toursHeadingId").html("Last Month( "+dates+" )");
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
			$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents").hide();
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
		}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
			$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert").hide();
				$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".NewTourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".NewTourExpandCls,.NewToursHiddenBlock,.moreNewToursBlocksDetailed").hide();
				$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".emnIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".emnIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".moreBlockEMN ,.newEmnHideCls,.dateRangePickerClsForEmn,.newsComparisonUl").hide();
				$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}
	setTimeout(function(){
		$(".toursHiddenBlock").toggle();      
	},800);
});
/* $(document).on("click",".tourExpand",function(){
	if( $(this).find("i").hasClass( "glyphicon glyphicon-fullscreen" )){
		$(".moreToursBlocks1").hide();     
		$(".tourExpandCls").hide();     
		$(".moreToursBlocksDetailed").hide();     
		$(".comparisonBlockTours").hide();  
		$(".hideShowToursDateRangeCls").hide();	
	}    
}); */
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
			 str+='<h4 class="panel-title">OVERALL</h4>';
			 str+='<div class="table-responsive">';
		      str+='<table class="table tableTraining bg_ED m_XsTop10">';
			  str+='<tbody><tr>';
				  str+='<td>';
				     if(overAllResult.noOfLeaderCnt > 0){
						str+='<h4 id="overallTourLdrDsgntnId" attr_designation_name="OVERALL"  attr_tour_submitted_type="All" class="overAllToursCls panel-title" style="cursor:pointer;color:rgb(51, 122, 183)">'+overAllResult.noOfLeaderCnt+'</h4>';
				     }else{
					   str+='<h4 class="panel-title">0</h4>';
					 }
					  str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
				  str+='</td>';
				  str+='<td>';
				     if(overAllResult.submitedLeaderCnt > 0){
						str+='<h4 id="submitedTourLdrDsgntnId" attr_designation_name="OVERALL" attr_tour_submitted_type="Yes" class="overAllToursCls panel-title" style="cursor:pointer;color:rgb(51, 122, 183)">'+overAllResult.submitedLeaderCnt+'<span class="font-10 text-success"> '+overAllResult.submitedCandidateTourPer+'%</span></h4>';
				      }else{
						str+='<h4 class="panel-title">0<span class="font-10 text-success">0.0%</span></h4>';
					 }
					  str+='<p class="text-muted text-capital">Submited<br>Leaders</p>';  
				  str+='</td>';
				  str+='<td>';
				  if(overAllResult.notSubmitedLeaserCnt > 0){
					str+='<h4 id="notSubmitedTourLdrDsgntnCntId" attr_tour_submitted_type="No" attr_designation_name="OVERALL" class="overAllToursCls panel-title" style="cursor:pointer;color:rgb(51, 122, 183)">'+overAllResult.notSubmitedLeaserCnt+'<span class="font-10 text-danger"> '+overAllResult.notsubmitedCandidateTourPer+'%</span></h4>';
			        }else{
					  str+='<h4 class="panel-title">0<span class="font-10 text-danger">0.0%</span></h4>';
				  }
					str+='<p class="text-muted text-capital">Not Submited<br>Leaders</p>';  
				  str+='</td>';
				  str+='<td>';
					  str+='<h4 class="panel-title">'+overAllResult.totalSubmittedToursCnt+'</h4>';
						str+='<p class="text-muted text-capital">Total<br>Tours</p>';
				   str+='</td>';
				    str+='<td>';
						 str+='<h4 class="panel-title">'+overAllResult.averageTours.toFixed(2)+'</h4>';
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
				str+='<h4 class="panel-title">'+designationWiseRlst[i].designation+'<i style="cursor: pointer; font-size: 16px; margin-left: 30px;" class="glyphicon glyphicon-info-sign tourDocCls" attr_desig_id="'+strIds+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Get Tour Details"></i></h4>';        
				  str+='<div class="table-responsive">';  
				  str+='<table class="table tableTraining bg_ED m_XsTop10">';
				  str+='<tbody><tr>';
					  str+='<td>';
					      str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
					      if(designationWiseRlst[i].noOfLeaderCnt > 0){
						    str+='<h4 attr_dsgntn_ids='+strIds+' attr_tour_submitted_type="All" attr_designation_name="'+designationWiseRlst[i].designation+'" class="overAllToursCls panel-title" style="cursor:pointer;color:rgb(51, 122, 183)" >'+designationWiseRlst[i].noOfLeaderCnt+'</h4>';
			 			  }else{
						    str+='<h4 class="panel-title">0</h4>';
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
		$("#tourDocumentDivModalId").modal("show");
		$("#tourDcHeadingId").html("Leaders Submitted Documents");
		$("#tourDocumentDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
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
			$("#tourDocumentDivId").html('');     
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
		$("#tourDocumentDivId").html(str);    
		$("#tourDocumentTblid").dataTable({
			"aaSorting": [[ 1, "desc" ]], 
			"iDisplayLength" : 10,
			"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
		}); 
	}
	 $(document).on("click",".closeShowPdfCls",function(){
		setTimeout(function(){
		$('body').addClass("modal-open");
		}, 500);                     
	}); 
//Closing model when esc key has been pressed
  $('body').keypress(function(e){
    if(e.keyCode == 27){
          var isModalOpened = $("#tourNewDocumentId").attr("isModalOpened");
		   if(isModalOpened == "true"){
			   setTimeout(function(){
				 $('body').addClass("modal-open");
			   }, 500);      
			   $("#tourNewDocumentId").attr("isModalOpened","false");
           }
	    var selectLevel = $(".tourIndividualCls").attr("attr_type");
		 if(selectLevel == "subLevel"){
			setTimeout(function(){
			$('body').addClass("modal-open");
			}, 500);       
		  $(".tourIndividualCls").attr("attr_type","subLevel");			
		 }
    }
  }); 
	$(document).on('click','#showPdfId',function(){
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
			//window.open(wurl+'/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			// window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}      
	});
	
	
	
/* New Tours Ajax Call Based on New Screen */
	

var customStartToursDate = moment().startOf('month').format('DD/MM/YYYY')
var customEndToursDate = moment().format('DD/MM/YYYY');
	$("#tourNewDateRangePickerId").daterangepicker({
		opens: 'left',
	     startDate: moment().subtract(1, 'month').startOf('month'),
         endDate:moment().subtract(1, 'month').endOf('month'),  
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment().endOf('Year')],
		  // 'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	});
	function getDay(){
		var date = new Date();
		var dd = date.getDate(); 
		return dd;
	}
	   var globalFormTourDate;
	   var glovalToTourDate;
	   var dates=$("#tourNewDateRangePickerId").val();
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			globalFormTourDate = datesArr[0]; 
			glovalToTourDate = datesArr[1]; 
		}
      $("#toursNewHeadingId").html("Last Month( "+dates+" )");
      $('#tourNewDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
	   var dates= $("#tourNewDateRangePickerId").val();
	   $("#toursNewHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
	   	if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			globalFormTourDate = datesArr[0]; 
			glovalToTourDate = datesArr[1]; 
		}
		 $(".tourNewComplainceFilterCls li").removeClass("active");
		 $(".tourNewComplainceFilterCls li:first-child").addClass("active");
		 getToursBasicOverviewDtls();
		 getDesignationWiseMembersDtls();
		var isFilterApply = "No";
		var filterType = "";
		var desgnatnIdsLst = [];
	   getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,0,0,0,0,0,0,"");
	 });
	 
	
	$(document).on("click",".NewTourExpand",function(){
		$(this).attr("isExpand","true");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".NewToursBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		
		$(".NewTourExpandCls").show();
		
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			getDesignationWiseMembersDtls();
			$(".NewToursHiddenBlock").show();
	        $(".hideShowNewToursDateRangeCls").show();			
		}else{
			$(this).attr("isExpand","false");
			$(".NewTourExpandCls").hide();
			$(".moreNewToursBlocksIcon").show();
			$(".NewToursHiddenBlock").hide();
			$(".moreNewToursBlocksDetailed").hide(); 
			$(".hideShowNewToursDateRangeCls").hide();
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
			$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents").hide();
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
		}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
			$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert").hide();
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
	});
	$(document).on("click",".moreNewToursBlocksIcon",function(){	
		$(".moreNewToursBlocks,.moreNewToursBlocksDetailed").toggle(); 
		//$(".moreNewToursBlocksDetailed").toggle(); 
		var isFilterApply = "No";
		var filterType = "";
		var desgnatnIdsLst = [];
	   getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,0,0,0,0,0,0,"");
		
	});

	$(document).on("click",".showHideFiltersToursSec",function(){
		$(".sliderCategoryTypeCls").attr("checked",true);
		var dropBlockId = $(this).attr("attr_id");
		$("#toursSessionDropDown"+dropBlockId).toggle(); 
		
   });
   $(document).on("click",".toursBlockCloseCls",function(){
	var dropBlockId = $(this).attr("attr_id");
	$("#toursSessionDropDown"+dropBlockId).toggle(); 
	
   });
	function isPageExpand(){
		var isExpand = $(".NewTourExpand").attr("isExpand");
		 if(isExpand=="true"){
			getDesignationWiseMembersDtls();
			var isFilterApply = "No";
			var filterType = "";
			var desgnatnIdsLst = [];
		    getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,0,0,0,0,0,0,"");
		 }
	}
	
	
	var globalDesignationSelectBoxString="";
	var globalDesignationComplainceSelectBoxString="";
	var globalDesignationNonCmplncSlctBxStrng="";
	var globalDesignationNotSubmttedSlctBxStrng="";
	var globalDesignationSubmttedSlctBxStrng="";
	
	function getToursBasicOverviewDtls()
	{    globalDesignationSelectBoxString="";
	     globalDesignationComplainceSelectBoxString="";
		 globalDesignationNonCmplncSlctBxStrng="";
		 globalDesignationNotSubmttedSlctBxStrng="";
		 globalDesignationSubmttedSlctBxStrng="";
		 isPageExpand();
		$("#tourOverviewNewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#tourOverviewNewDivId").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : globalFormTourDate,
					 toDate : glovalToTourDate,
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
	   var tursDesgntnIdsString;
		var str='';
		 if(overViewRslt != null){
				   str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4><span class="headingColor text-capital">Overall&nbsp Leaders</span></h4>';
					str+='<div id="" class="m_top10">';
						str+='<div class="pad_10 bg_ED">';
							str+='<div class="row">';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
									    if(overViewRslt.noOfLeaderCnt > 0){
										  str+='<h3 class="tourOverViewCls overAllTourCls"  attr_designation_name="Overall"  style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="all">'+overViewRslt.noOfLeaderCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<p class="text-muted text-capital">total leaders</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
									   if(overViewRslt.submitedLeaderCnt > 0){
										  str+='<h3 class="tourOverViewCls overAllTourCls"  attr_designation_name="Overall"  style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="submitted" >'+overViewRslt.submitedLeaderCnt+'&nbsp<small class="text-success" style="font-size:13px">'+overViewRslt.submitedCandidateTourPer+'%</small></h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										//str+='<small class="text-success" style="font-size:13px">'+overViewRslt.submitedCandidateTourPer+'%</small></h3>';
										str+='<p class="text-muted text-capital">Submitted leaders</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4">';
									str+='<div class="pad_10">';
									    if(overViewRslt.notSubmitedLeaserCnt > 0){
										  str+='<h3 class="tourOverViewCls overAllTourCls" attr_designation_name="Overall"  style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="notSubmitteed">'+overViewRslt.notSubmitedLeaserCnt+'&nbsp<small class="text-success" style="font-size:13px">'+overViewRslt.notsubmitedCandidateTourPer+'%</small></h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										//str+='<small class="text-success" style="font-size:13px">'+overViewRslt.notsubmitedCandidateTourPer+'%</small></h3>';
										str+='<p class="text-muted text-capital">Not Submitted leaders</p>';
									str+='</div>';
								str+='</div>';
								if(overViewRslt.submitedLeaderCnt > 0){
										str+='<div class="col-md-4 col-xs-12 col-sm-4">';
										str+='<div class="pad_10">';
										   if(overViewRslt.submitedLeaderCnt > 0){
											  str+='<h3 class="tourOverViewCls overAllTourCls" attr_designation_name="Overall"  style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="submitted">'+overViewRslt.submitedLeaderCnt+'</h3>';	
											}else{
											  str+='<h3>0</h3>';	
											}
											str+='<p class="text-muted text-capital">Submitted leaders</p>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-md-4 col-xs-12 col-sm-4">';
										str+='<div class="pad_10">';
										  if(overViewRslt.complainceCnt > 0){
											  str+='<h3 class="tourOverViewCls overAllTourCls" attr_designation_name="Overall" style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="Complaince">'+overViewRslt.complainceCnt+'</h3>';	
											}else{
											  str+='<h3>0</h3>';	
											}
											str+='<p class="text-muted text-capital">Complaince</p>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-md-4 col-xs-12 col-sm-4">';
										str+='<div class="pad_10">';
											if(overViewRslt.nonComplainceCnt > 0){
											  str+='<h3 class="tourOverViewCls overAllTourCls"  attr_designation_name="Overall" style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="nonComplaince">'+overViewRslt.nonComplainceCnt+'</h3>';	
											}else{
											  str+='<h3>0</h3>';	
											}
											str+='<p class="text-muted text-capital">Non-Complaince</p>';
										str+='</div>';
									str+='</div>';	
								}
						
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';	
		    }
		
		    if(designationWiseList != null && designationWiseList.length > 0){
				for(var i in designationWiseList){
				  var designationids;
				   if(designationWiseList[i].id==4){//organization SECRETARY and SECRETARY
					  designationids='4,5'; 
				   }else{
					designationids=designationWiseList[i].id;  
				   }
				   
				     globalDesignationSelectBoxString+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';
					 
				   if(designationWiseList[i].complainceCnt > 0){
					 globalDesignationComplainceSelectBoxString+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';	
					}
				    if(designationWiseList[i].nonComplainceCnt > 0){
				      globalDesignationNonCmplncSlctBxStrng+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';	
					}
					if(designationWiseList[i].submitedLeaderCnt > 0){
					  globalDesignationSubmttedSlctBxStrng+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';		
					}
					if(designationWiseList[i].notSubmitedLeaserCnt > 0){
					 globalDesignationNotSubmttedSlctBxStrng+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';	
					} 
					
					
					if(i== 0){
					 tursDesgntnIdsString = designationids;	
					 }else{
					  tursDesgntnIdsString = tursDesgntnIdsString+','+designationids;	
					}
				   str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
							str+='<h4><span class="text-capital">'+designationWiseList[i].name+'</span></h4>';
							str+='<div class="dropup">';
							str+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -16px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
								str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;">';
									str+='<p><span style="font-size: 20px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 17px;">Tours Days Target Per month</i></p>';
									str+='<table class="table">';
									 var monthWiseTarget = designationWiseList[i].subList; 
											 if(monthWiseTarget != null && monthWiseTarget.length > 0){
												 for(var k in monthWiseTarget){
												    str+='<tr>';
													if(monthWiseTarget[k].name != null && monthWiseTarget[k].name.length > 0){
													str+='<td style="border-top:none !important">'+monthWiseTarget[k].name+'</td>';	
													}else{
													str+='<td style="border-top:none !important"> - </td>';	
													}
													str+='<td>'+monthWiseTarget[k].targetDays+'</td>';
												   str+='</tr>'; 
												 }
											 }
									str+='</table>';
								str+='</div>';
							str+='</div>';
							
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
													if(designationWiseList[i].noOfLeaderCnt > 0){
													str+='<td><h4 attr_dsgntn_ids='+designationids+' attr_tour_filter_type="all" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)"  class="responsiveFont tourOverViewCls">'+designationWiseList[i].noOfLeaderCnt+'</h4></td>';	
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
													if(designationWiseList[i].notSubmitedLeaserCnt > 0){
													str+='<td><h4 class="responsiveFont tourOverViewCls" attr_dsgntn_ids='+designationids+' attr_tour_filter_type="notSubmitteed" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseList[i].notSubmitedLeaserCnt+'&nbsp<small class="text-success">'+designationWiseList[i].notsubmitedCandidateTourPer+'%</small></h4></td>';
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
													if(designationWiseList[i].submitedLeaderCnt > 0){
													str+='<td><h4 class="responsiveFont tourOverViewCls" attr_dsgntn_ids='+designationids+' attr_tour_filter_type="submitted" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseList[i].submitedLeaderCnt+'&nbsp<small class="text-success">'+designationWiseList[i].submitedCandidateTourPer+'%</small></h4></td>';
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
													if(designationWiseList[i].complainceCnt > 0){
													str+='<td><h4 class="responsiveFont tourOverViewCls" attr_dsgntn_ids='+designationids+' attr_tour_filter_type="Complaince" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseList[i].complainceCnt+'</h4></td>';
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
													if(designationWiseList[i].nonComplainceCnt > 0){
													str+='<td><h4 class="responsiveFont tourOverViewCls" attr_dsgntn_ids='+designationids+' attr_tour_filter_type="nonComplaince" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseList[i].nonComplainceCnt+'</h4></td>';
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
												str+='</tr>';
											str+='</table>';
										str+='</div>';
										str+='<hr style="margin: 10px 0 0;border-top: 1px solid #ccc"/>';
									str+='</div>';
									if(designationWiseList[i].submitedLeaderCnt > 0){
													str+='<div class="col-md-12 col-xs-12 col-sm-12">';
										str+='<div class="table-responsive">';
										str+='<table class="table tableEMN ">';
											str+='<thead>';
												str+='<th style="border-bottom:none !important"></th>';
												str+='<th class="text-muted f_14">Submitted</th>';
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
													str+='<td class="bg_D8 text-center">'+categoryList[j].submitedLeaderCnt+'</td>';
													str+='<td class="bg_D8 text-center">'+categoryList[j].complainceCnt+'</td>';
													str+='<td class="bg_D8 text-center">'+categoryList[j].nonComplainceCnt+'</td>';
												   str+='</tr>'; 
												 }
											 }
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
									str+='</div>';	
								}
								str+='</div>';
							str+='</div>';
						str+='</div>';	
				}
			}
		    $("#tourOverviewNewDivId").html(str);  
		   $(".overAllTourCls").attr("attr_dsgntn_ids",tursDesgntnIdsString);  
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
	function getDesignationWiseMembersDtls()
	{  
 		$("#buildgDesignationWiseToursTopFiveComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#buildgDesignationWiseToursTopFiveComplainceDivId").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : globalFormTourDate,
					 toDate : glovalToTourDate,
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
			str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
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
			  str+='<div id="designationWiseComplainceTour'+i+'" style="height:180px;"></div>';
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
						totalComplainceArr.push({"y":result[i][j].complaincePer,"extra":result[i][j].id+"-"+result[i][j].name+"-"+result[i][j].designation});
						
						if (countVar === 5) {
							break;
						}
					}
				}
		if(result[i][0].complaincePer!=0){
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
					},
					series: {
						cursor: 'pointer',
							point: {
								events: {
								click: function () {
									getIndividualPersonTourDetails(this.extra);
								}
							}
						}
					},
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
				str+='<div id="designationWiseComplainceTour'+i+'" style="height:180px;"></div>';
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
						  totalComplainceArr.push({"y":result[i][j].complaincePer,"extra":result[i][j].id+"-"+result[i][j].name+"-"+result[i][j].designation});
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
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: candidateNameArray,
					title: {
						text: null
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
					},
					series: {
						cursor: 'pointer',
							point: {
								events: {
								click: function () {
									getIndividualPersonTourDetails(this.extra);
								}
							}
						}
					},
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
/* 	$(document).on("click",".complainceDaysCls",function(){
		var monthList = $(this).attr("attr_month_list");
		console.log(monthList);
	}); */
	
	$(document).on("click",".tourFilterCls",function(){
		var designationStr = $(this).attr("attr_designation_id");
		var divId = $(this).attr("attr_div_id");
		var filterType = $(this).attr("attr_result_type");
		var isFilterApply = "Yes";
		var desgnatnIdsLst = designationStr.split(",");
		 $(".toursSessionDropDownCls").hide();	
		getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,0,0,0,0,0,0,divId);
		//$(".tourFilteCheckBoxCls").trigger("click");
	});
	
	 $(document).on("click",".tourFilteCheckBoxCls",function(){
		$(".tourFilteCheckBoxCls").prop('checked', false);
		$(this).prop('checked', true);
		/* var sliderName = $(this).closest(".tab-pane").find(".tourSearchBtnCls").attr("attr_slider_name");
		var mainSliderName = $(this).closest(".tab-pane").find(".tourSearchBtnCls").attr("attr_main_slider_name");
		var designationStr = $(this).attr("attr_designation_id");
		var sliderNameArr = sliderName.split(",");
		if($(this).is(":checked")){
			console.log("checked");
			var sliderType = $(this).attr("attr_slider_type");
			  if(sliderType=="tourCategory"){
				  for(var i in sliderNameArr){
					 var className = sliderNameArr[i]+''+designationStr+"cls";
					 if(designationStr != null && designationStr.length > 1){
						var secOrgIds = designationStr.split(",");
						 className = sliderNameArr[i]+''+secOrgIds[0]+secOrgIds[1]+"cls";
					 }
					  // $('.'+className).slider('setAttribute', 'max', 0);
					  // $('.'+className).slider('setAttribute', 'min', 0);
					  // $('.'+className).slider('refresh');
					  console.log("class name  "+className)
					    var strMin=0;
						var strMax=0;

						 $('.'+className).slider( "option", "max", 0);
						 $('.'+className).slider( "option", "min", 0);

					//$('.'+className).attr("data-slider-min",0);
					// $('.'+className).attr("data-slider-max",0);
					// var value = $slider.data('slider').getValue();
				 	//$('.'+className).slider('disable');
				  } 
			  }else if(sliderType=="main"){
				    var mainSliderCls = mainSliderName+''+designationStr+"cls";
					 if(designationStr != null && designationStr.length > 1){
						var secOrgIds = designationStr.split(",");
						 mainSliderCls = mainSliderName+''+secOrgIds[0]+secOrgIds[1]+"cls";
					 }
					 // $('.'+mainSliderCls).slider('disable');
					  $('.'+mainSliderCls).attr("data-slider-min",0);
					  $('.'+mainSliderCls).attr("data-slider-max",0);
			 }
		}  */
	}); 
	
	$(document).on("click",".tourSearchBtnCls",function(){
		  var designationStr = $(this).attr("attr_designation_id");
		  var divId = $(this).attr("attr_div_id");
		  var sliderName = $(this).attr("attr_slider_name");
		  var mainSliderName = $(this).attr("attr_main_slider_name");
		  var desgnatnIdsLst = designationStr.split(",");
		  var sliderNameArr = sliderName.split(",");
		  var isFilterApply="Yes";
		  var filterType=" ";
		  var ownDistValue = 0;
		 var ownCnsttuncyValue = 0;
		 var incharegeConstituencyValue = 0;
		 var ichargeDistrictValue = 0;
		 var govtWorkValue = 0;
		 var complainceValue = 0; 
		 var tourCategorySliderType = $(this).closest(".tab-pane").find('.sliderCategoryTypeCls:checkbox:checked').attr("attr_slider_type");
		 var mainSlider = $(this).closest(".tab-pane").find('.mainSliderCls:checkbox:checked').attr("attr_slider_type");
		 if(tourCategorySliderType == undefined && mainSlider==undefined){
			 $(this).closest(".tab-pane").find(".errorCls").html("Please Select Filter Type.");
			 return;
		 }
		 $(this).closest(".tab-pane").find(".errorCls").html(' ');
		 
		 if(tourCategorySliderType != undefined && tourCategorySliderType=="tourCategory"){
			 if(sliderNameArr != null && sliderNameArr.length > 0){
					 for(var i in sliderNameArr){
						 
						 var className = sliderNameArr[i]+''+designationStr+"cls";
						 
						 if(designationStr != null && designationStr.length > 1){
							 
							var secOrgIds = designationStr.split(",");
							 className = sliderNameArr[i]+''+secOrgIds[0]+secOrgIds[1]+"cls";
							
						 }
					   if(sliderNameArr[i]=="InchargeDistrict"){
						   
							var InchargeDistrictSliderValue = $("."+className).val();
							ichargeDistrictValue = InchargeDistrictSliderValue;
							
						 }else if(sliderNameArr[i]=="OwnDistrict"){
							 
							var ownDistrictSliderValue = $("."+className).val();
							ownDistValue = ownDistrictSliderValue;
						 
						 }else if(sliderNameArr[i]=="InchargeConstituency"){
							 
						var  InchargeConstituencySliderValue = $("."+className).val();
							incharegeConstituencyValue = InchargeConstituencySliderValue;
							
						 }else if(sliderNameArr[i]=="OwnConstituency"){
							 
							var ownConstituencySliderValue = $("."+className).val();
							  ownCnsttuncyValue = ownConstituencySliderValue;
							  
						 }else if(sliderNameArr[i]=="Govt"){
							 
							var govtSliderValue = $("."+className).val();
							govtWorkValue = govtSliderValue;
						
						} 
					 }
				 }	 
		 }
	      if(mainSlider != null && mainSlider=="main"){
			 var mainSlderCls = mainSliderName+''+designationStr+"cls";
			  if(designationStr != null && designationStr.length > 1){
				var secOrgIds = designationStr.split(",");
				 mainSlderCls = mainSliderName+''+secOrgIds[0]+secOrgIds[1]+"cls";
			  }
			   var mainSliderValue = $("."+mainSlderCls).val();
			   complainceValue = mainSliderValue;  
		  }
		  getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,ownDistValue,ownCnsttuncyValue,ichargeDistrictValue,incharegeConstituencyValue,govtWorkValue,complainceValue,divId);
		  $(".toursSessionDropDownCls").hide();	
    });
	
	function getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,ownDistValue,ownCnsttuncyValue,ichargeDistrictValue,incharegeConstituencyValue,govtWorkValue,complainceValue,divId){
    	 if(isFilterApply=="No"){
		   $("#toursPerformanceDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 }else{
		  $("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 }
	 	if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#toursPerformanceDivId").html(' ');
			 return;
		} 
		
		var jsObj ={ 
					 activityMemberId :globalActivityMemberId ,
					 stateId : globalStateIdForTour,
					 fromDate : globalFormTourDate, 
					 toDate : glovalToTourDate,
					 userTypeId : globalUserTypeId,
					 designationIds : desgnatnIdsLst,
					 isFilterApply :isFilterApply,
					 filterType :filterType,
					 ownDistValue :ownDistValue,
					 ownCnsttuncyValue :ownCnsttuncyValue,
					 ichargeDistrictValue :ichargeDistrictValue,
					 incharegeConstituencyValue :incharegeConstituencyValue,
					 govtWorkValue :govtWorkValue,
					 complainceValue :complainceValue
				
				  }
		$.ajax({
			type : 'POST',
			url : 'getDesignationWiseAverageTourPerformanceDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			   if(isFilterApply != null && isFilterApply=="No"){
			    buildDesignationWiseAverageTourPerformanceDtls(result);		   
			   }else if(isFilterApply != null && isFilterApply=="Yes"){
				buildFilterWiseRslt(result,divId);   
			   }
		});
	}
	function buildDesignationWiseAverageTourPerformanceDtls(result){
		
		if(result != null && result.length > 0){
			var str1='';
		  for(var i in result){
			str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str1+='<div class="panel panel-default panelNew">';
					str1+='<div class="panel-heading">';
						str1+='<div class="row">';
							str1+='<div class="col-md-5 col-xs-12 col-sm-6">';
									str1+='<h4 class="panel-title"><span class="headingColor">'+result[i].name+'</span></h4>';
							str1+='</div>';
							str1+='<div class="col-md-7 col-xs-12 col-sm-6">';
								str1+='<ul class="list-inline pull-right activeUlCls">';
								 if(result[i].id==4){//organization SECRETARY and SECRETARY
									str1+='<li class="active tourFilterCls" attr_result_type="all" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id="4,5" style="margin-right: 20px;">All</li>';
									str1+='<li class="tourFilterCls" attr_result_type="complaince" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id="4,5" style="margin-right: 20px;">COMPLAINCE</li>';
									str1+='<li class="tourFilterCls" attr_result_type="nonComplaince" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id="4,5" style="margin-right: 20px;">NON-COMPLAINCE</li>';
									str1+='<li class="showHideFiltersToursSec text-capital" attr_id="'+result[i].id+'">SHOW/HIDE FILTERS</li>';
                         		 }else{
								  	str1+='<li class="active tourFilterCls" attr_result_type="all" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id='+result[i].id+' style="margin-right: 20px;">All</li>';
									str1+='<li class="tourFilterCls" attr_result_type="complaince" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id='+result[i].id+' style="margin-right: 20px;">COMPLAINCE</li>';
									str1+='<li class="tourFilterCls" attr_result_type="nonComplaince" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id='+result[i].id+' style="margin-right: 20px;">NON-COMPLAINCE</li>';
									str1+='<li class="showHideFiltersToursSec " attr_id="'+result[i].id+'">SHOW/HIDE FILTERS</li>';
                          	 }
                            str1+='</ul>';
							str1+='</div>';
						str1+='</div>';
						 str1+='<div class="toursSessionDropDownCls col-md-7 col-xs-12 col-sm-6" id="toursSessionDropDown'+result[i].id+'" style="right:13px;top:30px;display:none;z-index:999">';  
										str1+='<i class="glyphicon glyphicon-remove pull-right toursBlockCloseCls" attr_id="'+result[i].id+'" style="cursor:pointer;"></i>';
											str1+='<div role="tabpanel" class="tab-pane" id="">';
												//str1+='<h4 class="text-capital" style="color:#99A0A5;">Select Impact Scope</h4>';
												//str1+='<hr style ="margin-bottom:0px;" />';
												str1+='<div class="checkbox">';
												  str1+='<label>';
												
												   if(result[i].id==4){//organization SECRETARY and SECRETARY
													str1+='<input type="checkbox" value="" attr_designation_id="4,5"  class="tourFilteCheckBoxCls sliderCategoryTypeCls" attr_slider_type="tourCategory" checked style="margin-top: 3px;">';   
												   }else{
												     str1+='<input type="checkbox" value="" attr_designation_id='+result[i].id+'  class="tourFilteCheckBoxCls sliderCategoryTypeCls" attr_slider_type="tourCategory" checked style="margin-top: 3px;">';	   
												   }
													
													str1+='<h4 class="text-muted" style="font-size: 17px;">COMPLAINCE</h4><span class="errorCls" style="color:red"></span>';
												  str1+='</label>';
												str1+='</div>';
												//str1+='<hr style ="margin-bottom:0px;margin-top: 0px;" />';
												    var sliderName='';
													for(var k in result[i].subList3[0].subList3){
														var sliderNameStrWithoutSpace='';
														var strSliderName = result[i].subList3[0].subList3[k].name;
														sliderNameStrWithoutSpace = strSliderName.replace(/\s+/g, '');
														if(k == 0){
															   sliderName = sliderNameStrWithoutSpace;	 
														}else{
															   sliderName = sliderName+ "," + sliderNameStrWithoutSpace;	 
														}
														str1+='<div class="row" style="border-top:1px solid #d3d3d3;margin:0px;">';
															str1+='<div class="col-md-3 col-xs-12 col-sm-3 m_top10">';
																str1+='<p>'+result[i].subList3[0].subList3[k].name+'</p>';
															str1+='</div>';
															str1+='<div class="col-md-9 col-xs-12 col-sm-3 m_top10">';
																 if(result[i].id==4){//organization SECRETARY and SECRETARY
																	str1+='<input class="'+sliderNameStrWithoutSpace+'45'+'cls" id="ownDisConsSlider'+i+''+k+'"  type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1" data-slider-enabled="true" />';	 
																 }else{
																	str1+='<input class="'+sliderNameStrWithoutSpace+''+result[i].id+''+'cls" id="ownDisConsSlider'+i+''+k+'" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1" data-slider-enabled="true" />';	 
																 }
																
																	str1+='<span>0%</span><span class="col-xs-offset-8">100%</span>';
															str1+='</div>';
															
														str1+='</div>';
														
													}
												str1+='<div class="row" style="border-top:1px solid #d3d3d3;padding:10px;">';
												str1+='<p class="underlineLineCss" style="text-align: center;">(OR)</p>';
												str1+='<div class="col-md-3 col-xs-12 col-sm-3">';
													str1+='<div class="checkbox">';
														  str1+='<label>';
														    if(result[i].id==4){
															str1+='<input type="checkbox" attr_slider_type="main" attr_designation_id="4,5" class="tourFilteCheckBoxCls mainSliderCls" value="" style="margin-top: 3px;">';		
															}else{
															str1+='<input type="checkbox" attr_slider_type="main" attr_designation_id="'+result[i].id+'" class="tourFilteCheckBoxCls mainSliderCls" value="" style="margin-top: 3px;">';	
															}
															str1+='<h4 class="text-muted" style="font-size: 17px;">COMPLAINCE</h4>';
														  str1+='</label>';
														str1+='</div>';
												str1+='</div>';
												 var mainSliderNameWithoutSpace='';
												  var mainSliderName ='';
												  if(result[i].name != null && result[i].name.length > 0){
													  mainSliderName = result[i].name;
												  }
												   mainSliderNameWithoutSpace = mainSliderName.replace(/\s+/g, '');
												str1+='<div class="col-md-9 col-xs-12 col-sm-3 m_top10">';
												 if(result[i].id==4){//organization SECRETARY and SECRETARY
												  mainSliderNameWithoutSpace="orgSecAndSec";
												    str1+='<input id="mainSlider'+i+'" class="'+mainSliderNameWithoutSpace+'45'+'cls" data-slider-id="mainSlider'+i+'" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1" data-slider-enabled="true"/>';	 
												 }else{
												    str1+='<input id="mainSlider'+i+'" class="'+mainSliderNameWithoutSpace+''+result[i].id+''+'cls" data-slider-id="mainSlider'+i+'" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1" data-slider-enabled="true"/>';	 
												 }
												str1+='</div>';
												str1+='<span style="margin-left: 10px;">0%</span><span class="col-xs-offset-6">100%</span>';
												str1+='</div>';
													 if(result[i].id==4){//organization SECRETARY and SECRETARY
													  str1+='<button type="button" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id="4,5" attr_slider_name="'+sliderName+'" attr_main_slider_name='+mainSliderNameWithoutSpace+' class="btn btn-success tourSearchBtnCls btn-sm pull-right">Get Details</button>'; 	 
													 }else{
													  str1+='<button type="button" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id='+result[i].id+' attr_slider_name="'+sliderName+'" attr_main_slider_name='+mainSliderNameWithoutSpace+' class="btn btn-success tourSearchBtnCls btn-sm pull-right">Get Details</button>'; 
													 }
											
										   str1+='</div>';
									   str1+='</div>';
					str1+='</div>';
					str1+='<div class="panel-body">';
						str1+='<div class="row">';
							str1+='<div id="toursPerformanceBlocks'+i+'"></div>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';
		  }
		  $("#toursPerformanceDivId").html(str1);
		}else{
		 $("#toursPerformanceDivId").html("NO DATA AVAILABLE.");	
		}
		
		if(result != null && result.length > 0){
			for(var i in result){
				var str='';
				var length = result
				if($(window).width() < 500)
				{  
					str+='<div class="table-responsive">'
				}
					str+='<table class="table table-bordered borderedWeight" id="dataTableApplyAveragePerf'+i+'">';
					str+='<thead class="bg_D8">';
						str+='<tr>';
			
								var maxLengthForSpan = 0;
								if(result[i].subList3 != null && result[i].subList3.length > 0){
									for(var j in result[i].subList3){
										if(result[i].subList3[j].subList3 != null && result[i].subList3[j].subList3.length > 0)
											maxLengthForSpan = result[i].subList3[j].subList3.length;
									}
								}
								str+='<th rowspan="2" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
								/* if(maxLengthForSpan >=3){
									str+='<th rowspan="'+(maxLengthForSpan)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
								}else{
									str+='<th rowspan="'+(maxLengthForSpan+1)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
								}
								 */
								str+='<th colspan="'+(maxLengthForSpan+1)+'" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">COMPLAINCE RATIO</th>';
								
								for(var k in result[i].subList3[0].subList3){
									str+='<th colspan="2" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
								}
							
							str+='</tr>';
							str+='<tr>';
								
								str+='<th class="text-capital text-center" style="vertical-align: middle;">over all</th>';
										for(var k in result[i].subList3[0].subList3){
											str+='<th class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
											
										}
								for(var k in result[i].subList3[0].subList3){
								str+='<th class="text-capital text-center" style="vertical-align: middle;">Target</th>';
								str+='<th class="text-capital text-center" style="vertical-align: middle;">Toured</th>';
								}
								
							str+='</tr>';
								str+='</thead>';
								str+='<tbody>';
								//str+='<td></td>';
										for(var j in result[i].subList3){
											str+='<tr>';
												str+='<td style="cursor:pointer;color:rgb(51, 122, 183)" attr_type="direct" class="candiateCls text-capital" attr_candiate_id="'+result[i].subList3[j].id+'" attr_candiate_name="'+result[i].subList3[j].name+'" attr_designation_name="'+result[i].subList3[j].designation+'"><a>'+result[i].subList3[j].name+'</a></td>';
												str+='<td class="text-center">'+result[i].subList3[j].complaincePer+'%</td>';

												   for(var k in result[i].subList3[j].subList3){
													   
														str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complaincePer+'%</td>';
													}												
											for(var k in result[i].subList3[j].subList3){
												var monthList = result[i].subList3[j].subList3[k].monthList;
												str+='<td class="text-center">'+result[i].subList3[j].subList3[k].targetDays+'</td>';
												str+='<td class="text-center complainceDaysCls">'+result[i].subList3[j].subList3[k].complainceDays+'';
												
												str+='<div class="dropup">';
													str+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -16px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
														str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;">';
															str+='<table class="table table-bordered">';
															str+='<thead>';
															str+='<th>Month</th>';
															str+='<th>Target</th>';
															str+='<th>Tour</th>';
															str+='<th>Complaince</th>';
															str+='</thead>';
															str+='<tbody>';
																	 if(monthList != null && monthList.length > 0){
																		 for(var l in monthList){
																			str+='<tr>';
																			if(monthList[l].name != null && monthList[l].name.length > 0){
																			str+='<td style="border-top:none !important">'+monthList[l].name+'</td>';	
																			}else{
																			str+='<td style="border-top:none !important"> - </td>';	
																			}
																			str+='<td class="text-center;">'+monthList[l].targetDays+'</td>';
																			if(monthList[l].complainceDays > 0){
																			str+='<td class="text-center;">'+monthList[l].complainceDays+'</td>';	
																			}else{
																			str+='<td> - </td>';	
																			}
																			if(monthList[l].complainceDays == 0){
																			str+='<td>-</td>';	
																			}else if(monthList[l].complainceDays>=monthList[l].targetDays){
																			str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#3DBC93;color:#fff;margin-left:7px;" class="glyphicon glyphicon-ok"></i></small>&nbsp&nbspYES<small></td>';		
																			}else if(monthList[l].complainceDays<monthList[l].targetDays){
																			 str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#E35B69;color:#fff;margin-left:7px;" class="glyphicon glyphicon-remove"></i></small>&nbsp&nbspNO<small></td>';			
																			}
																		   str+='</tr>'; 
																		 }
																	 }
															str+='</tbody>';
															str+='</table>';
														str+='</div>';
													str+='</div>';
													
												'</td>';
											}
											str+='</tr>';
										}
									
								str+='</tbody>';
								
					str+='</table>';
				if($(window).length < 500)
				{
					str+='</div>';
				}
				
				$("#toursPerformanceBlocks"+i).html(str);
					       var stateSliderval;
							for(var k in result[i].subList3[0].subList3){
									var slider = new Slider('#ownDisConsSlider'+i+''+k+'', {
								   formatter: function(value) {
								   stateSliderval=value;
								  // $("#stateSliderValue").text(value);
								 return 'Current value: ' + value;
							     }
							  });
							}
				
				//alert(stateSliderval);
				   var mainSliderVa1;
				    var slider = new Slider('#mainSlider'+i+'', {
				   formatter: function(value) {
					  // $("#constituencySliderValue").text(value);
					   mainSliderVa1=value;
					 return 'Current value: ' + value;
				   }
				});
					//alert(mainSliderVa1);
				$("#dataTableApplyAveragePerf"+i).dataTable({
					"aaSorting": [],
					"iDisplayLength" : 10	
				 });
				$('#dataTableApplyAveragePerf'+i).removeClass("dataTable");
			}
		}
	}

	function  buildFilterWiseRslt(result,divId){
        $("#"+divId).html(' ');
			if(result != null && result.length > 0){
					for(var i in result){
						if(result[i].subList3 == null || result[i].subList3.length == 0){
						   $("#"+divId).html('NO DATA AVAILABLE');
                            return;			
						}
						var str='';
						var length = result
							if($(window).width() < 500)
							{
								str+='<div class="table-responsive">';
							}
							str+='<table class="table table-bordered borderedWeight" id="dataTable'+divId+'">';
							str+='<thead class="bg_D8">';
								str+='<tr>';
					
										var maxLengthForSpan = 0;
										if(result[i].subList3 != null && result[i].subList3.length > 0){
											for(var j in result[i].subList3){
												if(result[i].subList3[j].subList3 != null && result[i].subList3[j].subList3.length > 0)
													maxLengthForSpan = result[i].subList3[j].subList3.length;
											}
										}
										str+='<th  class="text-capital text-center" style="vertical-align:middle;" rowspan="2" >Leaders Name</th>';
										/* if(maxLengthForSpan >=3){
											str+='<td rowspan="'+(maxLengthForSpan)+'" class="">Leaders Name</td>';
										}else{
											str+='<td rowspan="'+(maxLengthForSpan+1)+'" class="">Leaders Name</td>';
										} */
										
										str+='<th class="text-capital text-center" style="vertical-align:middle;" colspan="'+(maxLengthForSpan+1)+'" rowspan="1" class="">COMPLAINCE RATIO</th>';
										
										for(var k in result[i].subList3[0].subList3){
											str+='<th class="text-capital text-center" style="vertical-align:middle;" colspan="2" rowspan="1">'+result[i].subList3[0].subList3[k].name+'</th>';
										}
									
									str+='</tr>';
									str+='<tr>';
										
										str+='<th class="text-capital text-center" style="vertical-align:middle;" >over all</th>';
												for(var k in result[i].subList3[0].subList3){
													str+='<th class="text-capital text-center" style="vertical-align:middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
													
												}
										for(var k in result[i].subList3[0].subList3){
										str+='<th class="text-capital text-center" style="vertical-align:middle;">Target</th>';
										str+='<th class="text-capital text-center" style="vertical-align:middle;">Toured</th>';
										}
										
									str+='</tr>';
										str+='</thead>';
										str+='<tbody>';
										//str+='<td></td>';
												for(var j in result[i].subList3){
													str+='<tr>';
														str+='<td style="cursor:pointer;color:rgb(51, 122, 183)" attr_type="direct" class="candiateCls text-capital" attr_candiate_id="'+result[i].subList3[j].id+'" attr_candiate_name="'+result[i].subList3[j].name+'" attr_designation_name="'+result[i].subList3[j].designation+'"><a>'+result[i].subList3[j].name+'</a></td>';
														str+='<td class="text-center">'+result[i].subList3[j].complaincePer+'%</td>';

														   for(var k in result[i].subList3[j].subList3){
															   
																str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complaincePer+'%</td>';
															}												
													for(var k in result[i].subList3[j].subList3){
														var monthList = result[i].subList3[j].subList3[k].monthList;
														str+='<td class="text-center">'+result[i].subList3[j].subList3[k].targetDays+'</td>';
														str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complainceDays+'';
														
														
														str+='<div class="dropup">';
													    str+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -16px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
														str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;">';
															str+='<table class="table table-bordered">';
															str+='<thead>';
															str+='<th>Month</th>';
															str+='<th>Target</th>';
															str+='<th>Tour</th>';
															str+='<th>Complaince</th>';
															str+='</thead>';
															str+='<tbody>';
																	 if(monthList != null && monthList.length > 0){
																		 for(var l in monthList){
																			str+='<tr>';
																			if(monthList[l].name != null && monthList[l].name.length > 0){
																			str+='<td style="border-top:none !important">'+monthList[l].name+'</td>';	
																			}else{
																			str+='<td style="border-top:none !important"> - </td>';	
																			}
																			str+='<td class="text-center;">'+monthList[l].targetDays+'</td>';
																			if(monthList[l].complainceDays > 0){
																			str+='<td class="text-center;">'+monthList[l].complainceDays+'</td>';	
																			}else{
																			str+='<td> - </td>';	
																			}
																			if(monthList[l].complainceDays == 0){
																			str+='<td>-</td>';	
																			}else if(monthList[l].complainceDays>=monthList[l].targetDays){
																			str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#3DBC93;color:#fff;margin-left:7px;" class="glyphicon glyphicon-ok"></i></small>&nbsp&nbspYES<small></td>';		
																			}else if(monthList[l].complainceDays<monthList[l].targetDays){
																			 str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#E35B69;color:#fff;margin-left:7px;" class="glyphicon glyphicon-remove"></i></small>&nbsp&nbspNO<small></td>';			
																			}
																		   str+='</tr>'; 
																		 }
																	 }
															str+='</tbody>';
															str+='</table>';
														str+='</div>';
													str+='</div>';
														
														
														str+='</td>';
														
														
													}
													str+='</tr>';
												}
											
										str+='</tbody>';
										
							str+='</table>';
						  if($(window).width() < 500)
							{
								str+='</div>';
							}
						$("#"+divId).html(str);
					}
				}else{
				$("#"+divId).html("NO DATA AVAILABLE.");	
				}	
			  $("#dataTable"+divId).dataTable({
				"aaSorting": [],
				"iDisplayLength" : 10	
			 });
			 $("#dataTable"+divId).removeClass("dataTable"); 
		} 
		
	function getIndividualPersonTourDetails(value)
	{          
	   //select Slider Year
		var selectedDate = globalFormTourDate.split("/");
	    $("#dateRangeSliderYear").val(parseInt(selectedDate[2]));
		
		var temp = value.split("-");
		var candiateId = temp[0];
		var topFivecandidateName = temp[1];
		var topFivedesignationName = temp[2];
	     $("#subMitBtn").attr("attr_candidate_id",candiateId);
		 
		$("#tourIndividualPerformanceDivId").modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
		 var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		var fromDate = globalFormTourDate.split("/")
		var toDate = glovalToTourDate.split("/")
				
				$("#tourSlider").dateRangeSlider({
					    bounds: {min: new Date(fromDate[2], 0, 1), max: new Date(toDate[2], 11, 31)},
						//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
						defaultValues: {min: new Date(fromDate[2], fromDate[1]-1,fromDate[0]), max: new Date(toDate[2],toDate[1],toDate[0])},
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
				
		
	    //var selectedDate = $("#toursNewHeadingId").html();
		//&nbsp&nbsp<small style='color:green;'>"+selectedDate+"</small>&nbsp<small>DETAILS</small>
		$(".tourIndividualCls").attr("attr_type","direct");
		$("#nameOfMemberHeadingId").html('');
		$("#nameOfMemberHeadingId").html("<h4 class='modal-title text-capital'>"+topFivecandidateName+" - <small style='color:#4A5863'>"+topFivedesignationName+"</small></h4>");
		$("#tourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#tourIndividualDetailsTableBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#monthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :globalFormTourDate ,
					 toDate : glovalToTourDate 
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html('');
			buildIndividualPersonTourDetails(result);
		});
	}
	   $(document).on("change","#tourDesignationSelectBoxId",function(){
		  var designationId = $(this).val(); 
		  var filterType = $(this).attr("attr_tour_filter_type");
		   var designationIds=[];
		    if(designationId == 4){
				designationIds.push(4);
				designationIds.push(5);
			}else{
			designationIds.push(designationId)	
			}
			getTourLeaderDtlsBasedOnSelectionType(designationIds,filterType);
	   });
	  $(document).on("click",".tourOverViewCls",function(){
			
		var filterType = $(this).attr("attr_tour_filter_type");
		var designationIdsStr = $(this).attr("attr_dsgntn_ids");
		var designationName = $(this).attr("attr_designation_name");
		 $("#tourDetailsModalId").modal("show");
		 var designationIds=[];
	    if(designationName.trim()=="Overall"){
			$(".designationSelectBoxCls").show();
			$("#tourDesignationSelectBoxId").html(" ");
		
				if(filterType=="Complaince"){
				$("#tourDesignationSelectBoxId").append(globalDesignationComplainceSelectBoxString);				
				}else if(filterType=="nonComplaince"){
				$("#tourDesignationSelectBoxId").append(globalDesignationNonCmplncSlctBxStrng);		
				}else if(filterType=="submitted"){
				$("#tourDesignationSelectBoxId").append(globalDesignationSubmttedSlctBxStrng);		
				}else if(filterType=="notSubmitteed"){
				$("#tourDesignationSelectBoxId").append(globalDesignationNotSubmttedSlctBxStrng);		
				}else{
				$("#tourDesignationSelectBoxId").append(globalDesignationSelectBoxString);		
				}
			
			
			
			var firstOption = $("#tourDesignationSelectBoxId option:first").val();
			$("#tourDesignationSelectBoxId").val(firstOption);
			if(firstOption == 4){
			  designationIds.push(4);
			  designationIds.push(5);
			}else{
			  designationIds.push(firstOption);	
			}
			$("#tourDesignationSelectBoxId").attr("attr_tour_filter_type",filterType);
		}else{
		   $(".designationSelectBoxCls").hide();
			if(designationIdsStr != null && designationIdsStr != undefined){
			  designationIds=designationIdsStr.split(",");	
			}		   
		}
		  getTourLeaderDtlsBasedOnSelectionType(designationIds,filterType);
	});

	function getTourLeaderDtlsBasedOnSelectionType(designationIds,filterType)
	{    
		  var selectedDate = $("#toursNewHeadingId").html();
		  $("#tourLeadrDtlsHeadingId").html('Leaders Detailed Report&nbsp&nbsp<small  style="color:green;">'+selectedDate+'</small>');
	       $("#tourDetailsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 	var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate :globalFormTourDate ,
					 toDate : glovalToTourDate,
					 userTypeId:globalUserTypeId,
					 designationIds : designationIds,
					 filterType :filterType
				  }
		$.ajax({
			type : 'POST',
			url : 'getTourLeaderDtlsBasedOnSelectionTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildTourMemberDetails(result);
			}else{
			 $("#tourDetailsDivId").html("NO DATA AVAILABLE.");	
			}
		});
	}
	
function buildTourMemberDetails(result){
    
    if(result != null && result.length > 0){
      var str1='';
      for(var i in result){
      str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
        str1+='<div class="panel panel-default panelNew">';
          str1+='<div class="panel-heading">';
            str1+='<div class="row">';
              str1+='<div class="col-md-8 col-xs-12 col-sm-6">';
                str1+='<h4 class="panel-title"><span class="headingColor text-capital">'+result[i].name+'&nbsp&nbspTours Submitted Report</span></h4>';
              str1+='</div>';
          str1+='</div>';
          str1+='<div class="panel-body">';
            str1+='<div class="row">';
              str1+='<div id="tourMemberDtls'+i+'"></div>';
            str1+='</div>';
          str1+='</div>';
        str1+='</div>';
      str1+='</div>';
      }
      $("#tourDetailsDivId").html(str1);
    }
    if(result != null && result.length > 0){
      for(var i in result){
	  if(result[i].subList3 == null || result[i].subList3.length == 0){
	    $("#tourDetailsDivId").html('NO DATA AVAILABLE');
		 return;			
	  }
        var str='';
        var length = result
	    if($(window).width() < 500)
		{
			str+='<div class="table-responsive">';
		}
          str+='<table class="table table-bordered borderedWeight" id="tourDetailsDataTabelId">';
          str+='<thead class="bg_D8">';
            str+='<tr>';
                var maxLengthForSpan = 0;
                if(result[i].subList3 != null && result[i].subList3.length > 0){
                  for(var j in result[i].subList3){
                    if(result[i].subList3[j].subList3 != null && result[i].subList3[j].subList3.length > 0)
                      maxLengthForSpan = result[i].subList3[j].subList3.length;
                  }
                }
				str+='<th rowspan="2" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
                /* if(maxLengthForSpan >=3){
                  str+='<th rowspan="'+(maxLengthForSpan)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
                }else{
                  str+='<th rowspan="'+(maxLengthForSpan+1)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
                } */
                
                str+='<th colspan="'+(maxLengthForSpan+1)+'" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">Complaince RATIO</th>';
                
                for(var k in result[i].subList3[0].subList3){
                  str+='<th colspan="2" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
                }
              
              str+='</tr>';
              str+='<tr>';
                
                str+='<th class="text-capital text-center" style="vertical-align: middle;">over all</th>';
                    for(var k in result[i].subList3[0].subList3){
                      str+='<th class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
                      
                    }
                for(var k in result[i].subList3[0].subList3){
                str+='<th class="text-capital text-center" style="vertical-align: middle;">Target</th>';
                str+='<th class="text-capital text-center" style="vertical-align: middle;">Toured</th>';
                }
              str+='</tr>';
                str+='</thead>';
                str+='<tbody>';
                //str+='<td></td>';
                    for(var j in result[i].subList3){
                      str+='<tr>';
                        str+='<td style="cursor:pointer;color:rgb(51, 122, 183)" attr_type="subLevel" class="candiateCls text-capital" attr_candiate_id="'+result[i].subList3[j].id+'" attr_candiate_name="'+result[i].subList3[j].name+'" attr_designation_name="'+result[i].subList3[j].designation+'"><a>'+result[i].subList3[j].name+'</a></td>';//santosh
                        str+='<td class="text-center">'+result[i].subList3[j].complaincePer+'%</td>';

							for(var k in result[i].subList3[j].subList3){
                             
                            str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complaincePer+'%</td>';
                          }                        
                      for(var k in result[i].subList3[j].subList3){
                        var monthList = result[i].subList3[j].subList3[k].monthList;
                        str+='<td class="text-center">'+result[i].subList3[j].subList3[k].targetDays+'</td>';
                        str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complainceDays+'';
								str+='<div class="dropup">';
								str+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -16px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
								str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;">';
									str+='<table class="table table-bordered">';
									str+='<thead>';
									str+='<th>Month</th>';
									str+='<th>Target</th>';
									str+='<th>Tour</th>';
									str+='<th>Complaince</th>';
									str+='</thead>';
									str+='<tbody>';
											 if(monthList != null && monthList.length > 0){
												 for(var l in monthList){
													str+='<tr>';
													if(monthList[l].name != null && monthList[l].name.length > 0){
													str+='<td style="border-top:none !important">'+monthList[l].name+'</td>';	
													}else{
													str+='<td style="border-top:none !important"> - </td>';	
													}
													str+='<td class="text-center;">'+monthList[l].targetDays+'</td>';
													if(monthList[l].complainceDays > 0){
													str+='<td class="text-center;">'+monthList[l].complainceDays+'</td>';	
													}else{
													str+='<td> - </td>';	
													}
													if(monthList[l].complainceDays == 0){
													str+='<td>-</td>';	
													}else if(monthList[l].complainceDays>=monthList[l].targetDays){
													str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#3DBC93;color:#fff;margin-left:7px;" class="glyphicon glyphicon-ok"></i></small>&nbsp&nbspYES<small></td>';		
													}else if(monthList[l].complainceDays<monthList[l].targetDays){
													 str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#E35B69;color:#fff;margin-left:7px;" class="glyphicon glyphicon-remove"></i></small>&nbsp&nbspNO<small></td>';			
													}
												   str+='</tr>'; 
												 }
											 }
									str+='</tbody>';
									str+='</table>';
								str+='</div>';
							str+='</div>';
						
						str+='</td>';
                      }
                      str+='</tr>';
                    }
                  
                str+='</tbody>';
                
          str+='</table>';
              if($(window).width() < 500)
			{
				str+='</div">';
			 }
        $("#tourMemberDtls"+i).html(str);
	 	$("#tourDetailsDataTabelId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	
		 });
		$('#tourDetailsDataTabelId').removeClass("dataTable");
		}
    }
  }
	
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
	var selectedDate = globalFormTourDate.split("/");
	$("#dateRangeSliderYear").val(parseInt(selectedDate[2]));
   getCandiateWiseTourDetails(candiateId,designationName,candiateName);
});

function getCandiateWiseTourDetails(candiateId,designationName,candiateName)
	{ 
		$("#tourIndividualPerformanceDivId").modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
		 var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		var fromDate = globalFormTourDate.split("/")
		var toDate = glovalToTourDate.split("/")
				
		$("#tourSlider").dateRangeSlider({
				bounds: {min: new Date(fromDate[2], 0, 1), max: new Date(toDate[2], 11, 31)},
				//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
				defaultValues: {min: new Date(fromDate[2], fromDate[1]-1,fromDate[0]), max: new Date(toDate[2],toDate[1],toDate[0])},
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
		//var selectedDate = $("#toursNewHeadingId").html();
		//&nbsp&nbsp<small style='color:green;'>"+selectedDate+"</small>&nbsp<small>DETAILS</small>
		$("#nameOfMemberHeadingId").html('');
		$("#nameOfMemberHeadingId").html("<h4 class='modal-title text-capital'>"+candiateName+" - <small style='color:#4A5863'>"+designationName+"</small></h4>");
		$("#tourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#tourIndividualDetailsTableBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#monthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :globalFormTourDate ,
					 toDate : glovalToTourDate
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
	}
	
   function buildIndividualPersonTourDetails(result){
	  
	   $("#tourIndividualDetailsBlock").html('');
	 	if(result !=null && result.subList != null && result.subList.length > 0){
			 var str='';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<div class="row">';
					
						str+='<div class="col-md-4 col-xs-12 col-md-12">';
							str+='<h4 class="text-capital">TOTAL COMPLAINCE OVERVIEW</h4>';
							str+='<div id="overAllComplainsGraph" class="" style="height:150px" ></div>';
						str+='</div>';
						str+='<div class="col-md-8 col-xs-12 col-md-12">';
							str+='<div class="row">';
						for(var i in result.subList){
							str+='<div class="col-md-4 col-xs-12 col-md-12">';
							str+='<h4 class="text-capital">'+result.subList[i].name+'</h4>';
							str+='<div id="individualComplainsGraph'+i+'" class="" style="height:150px" ></div>';
							str+='</div>';
						
						}
					str+='</div>';
					str+='</div>';
					str+='</div>';
					
			str+='</div>';
			$("#tourIndividualDetailsBlock").html(str);
		}else{
			$("#tourIndividualDetailsBlock").html("NO DATA AVAILABLE.");
		}
		
		if(result !=null && result.subList != null && result.subList.length > 0){
			
			var mainArrNma=[];
			var individualPerfArr=[];
			var nameArr;
			var jsonObj=[];	
			mainArrNma.push("All")
			jsonObj.push(result.complaincePer);
			for(var i in result.subList){
				jsonObj.push(result.subList[i].complaincePer);
				mainArrNma.push(result.subList[i].name);
				nameArr = result.subList[i].name;
			}
		
		 $(function () {
			  $('#overAllComplainsGraph').highcharts({
				colors: ['#80F6F8'],
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
					categories: mainArrNma,
					title: {
						text: null
					},
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
					return '<b>'+this.x+' : '+ Highcharts.numberFormat(this.y, 2) +'%</b>';
				}      
				},
				plotOptions: {
					column: {  
						//stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,2) +"%"; 
								}
							}
						  
						},
					},
					
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
				credits: {
					enabled: false
				},
				
				series:  [{
					name: nameArr,
					data: jsonObj
				}]
			});
		});
	}
	
	if(result !=null && result.subList != null && result.subList.length > 0){
			for(var i in result.subList){
				var performanceArr =[];
				performanceArr.push(result.subList[i].targetDays);
				performanceArr.push(result.subList[i].complainceDays);
				//performanceArr.push(result.subList[i].yetToTourCnt);
		if(performanceArr != 0 && performanceArr.length > 0){		
		 $(function () {
			  $('#individualComplainsGraph'+i+'').highcharts({
				colors: ['#7F7037','#80F6F8'],
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
					categories: ["Target","Toured"],
					title: {
						text: null
					},
					labels: {
							formatter: function() {
								return this.value.toString().substring(0, 5)+'...';
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
					return '<b>'+this.x+' : '+ (this.y) +'</b>';
					
				}      
				},
				plotOptions: {
					column: {  
						//stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						  
						},
					},
					
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
				credits: {
					enabled: false
				},
				
				series:  [{
					name: '',
					data: performanceArr,
					colorByPoint:true
				}]
			});
		});
		}else{
			 $('#individualComplainsGraph'+i+'').html("No Data Availble");
		}
	}	
	}
	
	if(result !=null && result.monthList != null && result.monthList.length > 0){
		  var str2='';
		  
		  	 str2+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				str2+='<h4 class="text-capital">MONTH WISE COMPLIANCE OVERVIEW</h4>';
					 str2+='<div class="slickApplyTourCls" >';	
				for(var i in result.monthList){
					
						//str2+='<li>';
						if(i == 0 || i%3 == 0){
							str2+='<div class="row">';
						}
					   if($(window).width() < 500)
						{
							str2+='<div class="table-responsive">';
						}
						str2+='<div class="col-md-4 col-xs-12 col-sm-12 m_top10">';
							str2+='<table class="table table-bordered">';
							 var categoryList = result.monthList[i].subList;
							 
							 if(categoryList != null && categoryList.length > 0){
									var moxCategoryLength = categoryList.length;
									var categoryVO = result.monthList[i].subList[0];
								str2+='<tr>';
									str2+='<td rowspan='+(moxCategoryLength+1)+' style="font-size:22px;background-color:#EDECE7">'+result.monthList[i].name+'<br>'+result.monthList[i].year+'</td>';
									str2+='<td style="background-color:#EDECE7"><p>'+categoryVO.name+'('+categoryVO.complainceDays+')';
									   if(categoryVO.complainceDays >= categoryVO.targetDays){
											str2+='<small style="text-align: center;"><i  style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-success "></i></small>';
										   }else{
											str2+='<small style="text-align: center;"><i  style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
										   } 
									  str2+='<div class="dropup">';
									str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
										str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;z-index:999;width:220px">';
											str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per month</i></p>';
											str2+='<table class="table">';
												str2+='<tr><td style="background-color:#EDECE7">'+categoryVO.name+' - '+categoryVO.targetDays+'</td></tr>';
											str2+='</table>';
										str2+='</div>';
									str2+='</div></p>';  
										  str2+='</td>';  
								        if(result.monthList[i].isComplaince != null && result.monthList[i].isComplaince.trim()=="True"){
										   str2+='<td style="background-color:#3DBC93;text-align:center;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-ok" style="font-size:55px;display:block;color:#068057;"></i><small style="color:#fff;">Compliance</small></td>';
										}else{
										   str2+='<td style="background-color:#E35B69;text-align:center;width:50px;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-remove" style="font-size:55px;display:block;color:#bf3646;"></i><small style="color:#fff;">Non Compliance</small></td>';
										} 	 
								  str2+='</tr>';
								
									for(var k in categoryList){
										if(k==0)
										continue;
										 str2+='<tr>';
										if(categoryList[k].name == null || categoryList[k].name == ""){
											str2+='<td style="background-color:#EDECE7;"> - </td>';
										}else{
										   str2+='<td style="background-color:#EDECE7"><p>'+categoryList[k].name+'('+categoryList[k].complainceDays+')';
											   if(categoryList[k].complainceDays >= categoryList[k].targetDays){
												str2+='<small style="text-align: center;"><i style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-danger"></i></small>';
											   }else{
													str2+='<small style="text-align: center;"><i style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
											   } 
											 str2+='<div class="dropup">';
											str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
												str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu3" style="padding:10px;z-index:999;width:220px !important;">';
													str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per month</i></p>';
													str2+='<table class="table">';
														str2+='<tr style="background-color:#EDECE7"><td>'+categoryList[k].name+' - '+categoryList[k].targetDays+'</td></tr>';
													str2+='</table>';
												str2+='</div>';
											str2+='</div></p>';  
										   str2+='</td>';
										}
									str2+='</tr>';
								  }
							 }
							str2+='</table>';
							str2+='</div>';
							//str2+='</li>';
						   if(i == 2 || i%3 == 2){
								str2+='</div>';
							}
						 if($(window).width() < 500)
						{
							str2+='</div>';
						}
						
				}
				str2+='</div>';
				str2+='</div>';
			
			
		$("#monthWiseComplainceDivId").html(str2);
		/* $('.slickApplyTourCls').slick({
			//slide: 'li',
		 	 slidesToShow: 3,
			 slidesToScroll: 3,
			  infinite: false,
			 variableWidth: false
			 
		}); */
	}
	
	if(result !=null && result.subList2 != null && result.subList2.length > 0){
		var str1='';
		str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
		str1='<h4 class="text-capital">MONTH WISE COMPLIANCE COMMENTS & ATTACHMENTS</h4>';
				str1+='<div class="m_top20">';
					 if($(window).width() < 500)
					{
						str1+='<div class="table-responsive">';
					}
					str1+='<table class="table table-bordered borderedWeight">';
						str1+='<thead class="bg_D8">';
							str1+='<tr>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Month & Date</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Category</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Type</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">NO OF Days</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">ATTACHMENT</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Comment</th>';
							str1+='</tr>';
							str1+='<tbody>';
								for(var i in result.subList2){
									str1+='<tr>';
										str1+='<td>'+result.subList2[i].tourDate+'</td>';
										if(result.subList2[i].tourCategory == null || result.subList2[i].tourCategory == ""){
											str1+='<td> - </td>';
										}else{
											str1+='<td>'+result.subList2[i].tourCategory+'</td>';
										}
										if(result.subList2[i].tourType == null || result.subList2[i].tourType == ""){
											str1+='<td> - </td>';
										}else{
											str1+='<td>'+result.subList2[i].tourType+'</td>';
										}
										if(result.subList2[i].count == null || result.subList2[i].count==0){
											str1+='<td> - </td>';
										}else{
											str1+='<td class="text-center">'+result.subList2[i].count+'</td>';
										}
									  if(result.subList2[i].filePath != null && result.subList2[i].filePath.length > 0){
										var fullName = result.subList2[i].filePath;
										var nameArr = fullName.split(".");
										var type = nameArr[1];
										if(type=="pdf" || type=="PDF"){
											str1+='<td id="showTourPdfId" attr_filePath="'+result.subList2[i].filePath+'" style="cursor:pointer;"><span><img src="images/pdf.jpg" class="media-object" alt="" style="width:30px;"/></td>';
										}else if(type=="xls" ||type=="xlsx"){  
											str1+='<td id="showTourPdfId"  attr_filePath="'+result.subList2[i].filePath+'" style="cursor:pointer;"><span><img src="images/excel.jpg" class="media-object" alt="" style="width:30px;"/></td>';       
										}else if(type=="doc" || type=="docx"){
											str1+='<td id="showTourPdfId"  attr_filePath="'+result.subList2[i].filePath+'" style="cursor:pointer;"><span><img src="images/word.jpg" class="media-object" alt="" style="width:30px;"/></td>';         
										}else if(type != null){  
											str1+='<td id="showTourPdfId"  attr_filePath="'+result.subList2[i].filePath+'" style="cursor:pointer;"><span><img src="images/fileImage.png" class="media-object" alt="" style="width:30px;"/></td>';         
										}           
										}else{    
											str1+='<td> - </td>';  
										} 
									if(result.subList2[i].comment != null && result.subList2[i].comment.length > 0){
											if(result.subList2[i].comment.length > 15){
											 str1+='<td style="cursor:pointer;" title="'+result.subList2[i].comment+'">'+result.subList2[i].comment.substring(0,30)+'...</td>';	
											}else{
											 str1+='<td>'+result.subList2[i].comment+'</td>';	
											}
										}else{
										  str1+='<td> - </td>';	
										}
									str1+='</tr>';
								}
							str1+='</tbody>';
						str1+='</thead>';
					str1+='</table>';	
					if($(window).width() < 500)
					{
						str1+='</div>';
					}
				str1+='</div>';
			str1+='</div>';
		$("#tourIndividualDetailsTableBlock").html(str1);
	}
}
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

function getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat){
	
	$("#tourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#tourIndividualDetailsTableBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#monthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :frmDateInRequiredFormat ,
					 toDate : toDateInRequiredFormat
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
}