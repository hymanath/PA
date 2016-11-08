$(document).on("click",".tourExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".toursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	//$(".cadreBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	
	setTimeout(function(){
		$(".toursHiddenBlock").toggle();      
	},800);
});
$(document).on("click",".moreToursBlocksIcon",function(){
	$(".moreToursBlocks1,.moreToursBlocksDetailed").toggle(); 
	   getDistrictWiseToursSubmitedDetails();		
});
$(document).on("click",".toursDetailedBlock",function(){
	   getDistrictWiseToursSubmitedDetails();		
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
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
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
  //getTopPoorToursLocationDetails();
  function getTopPoorToursLocationDetails()
	{    
		var jsObj ={ 
					 activityMemberId : 2,
					 stateId : globalStateIdForTour,
					 userTypeId:3,
					 fromDate : "27/10/2016",
					 toDate : "29/10/2016"
				  }
		$.ajax({
			type : 'POST',
			url : 'getTopPoorToursLocationDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			console.log(result);
 		});
	}