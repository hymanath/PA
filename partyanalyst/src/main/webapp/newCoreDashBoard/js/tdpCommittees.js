	function getCommitteesBasicCountReport(){
		
		$("#basicCommitteeCountsDiv").html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
	   var state ='AP';
	   
	   var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   basicCommitteeIdsArray.push(4);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={  userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  state:state,
					  basicCommitteeIdsArray : basicCommitteeIdsArray,
					  startDateString : startDateString,
 			          endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getCommitteesBasicCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#basicCommitteeCountsDiv").html('');
			buildgetCommitteesBasicCountReport(result);
			
		});
	}
	function getUserTypeWiseCommitteesCompletedCounts(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={  
			          activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId,
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  
					  state:state,
					  basicCommitteeIdsArray : basicCommitteeIdsArray,
					  startDateString : startDateString,
 			          endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	function getUserTypeWiseCommitteesCompletedCounts1(){
		$("#userTypeWiseCommitteesForTopFiveStrongDiv").html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
		$("#userTypeWiseCommitteesForTopFivePoorDiv").html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
	   var state ='AP';
	   var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={  
			          activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId,
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  
					  state:state,
					  basicCommitteeIdsArray : basicCommitteeIdsArray,
					  startDateString : startDateString,
 			          endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#userTypeWiseCommitteesForTopFiveStrongDiv").html('');
			$("#userTypeWiseCommitteesForTopFivePoorDiv").html('');
			buildgetUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(result);
			buildgetUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(result);
			
		});
	}
	function getLevelWiseBasicCommitteesCountReport(){
		
		$("#levelWiseBasicCommittees").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   basicCommitteeIdsArray.push(4);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={ userAccessLevelId:globalUserAccessLevelId,
					userAccessLevelValuesArray:globalUserAccessLevelValues,
					state:state,
					basicCommitteeIdsArray : basicCommitteeIdsArray,
					startDateString : startDateString,
 			        endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLevelWiseBasicCommitteesCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#levelWiseBasicCommittees").html('');
			buildgetLevelWiseBasicCommitteesCountReport(result);
			
		});
	}
	function getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray){
		
		
		$("#districtWiseCommitteesReport").html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
		var basicCommitteeIdsArray= [];
		basicCommitteeIdsArray.push(1);
		basicCommitteeIdsArray.push(2);
		basicCommitteeIdsArray.push(3);
		
		var state = globalState;
		
		var userLocationLevelId = globalUserAccessLevelId;
		var userLocationLevelValuesArray = globalUserAccessLevelValues;
		
		var groupingLocationConsider = 'subLevel';
		var groupingLocationsArray = [];
		if(groupingLocationConsider == "self"){
           if(userLocationLevelId == 2 ){//user_level table
			   groupingLocationsArray.push("State");
		   }else if(userLocationLevelId == 3){
			   groupingLocationsArray.push("District");
		   }else if(userLocationLevelId == 4){
			   groupingLocationsArray.push("Parliament");
		   }else if(userLocationLevelId == 5){
			   groupingLocationsArray.push("Constituency");
		   }else if(userLocationLevelId == 6){
			   groupingLocationsArraypush("Mandal");
			   groupingLocationsArraypush("LocalElectionbody");
		   }
		}else if( groupingLocationConsider == "subLevel"){
			if(userLocationLevelId == 2 ){//user_level table
			   groupingLocationsArray.push("District");
		   }else if(userLocationLevelId == 3){
			   groupingLocationsArray.push("Constituency");
		   }else if(userLocationLevelId == 4){
			   groupingLocationsArray.push("Constituency");
		   }else if(userLocationLevelId == 5){
			  groupingLocationsArraypush("Mandal");
			   groupingLocationsArraypush("LocalElectionbody");
		   }else if(userLocationLevelId == 6){
			   groupingLocationsArraypush("Village");
			   groupingLocationsArraypush("Ward");
		   }
		}
		
		var committeeStatus = 'all';
	
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={tdpCommitteeLevelIdsClickedArray:tdpCommitteeLevelIdsClickedArray,
					basicCommitteeIdsArray : basicCommitteeIdsArray,
					committeeStatus:committeeStatus,
					userLocationLevelId:userLocationLevelId,
					userLocationLevelValuesArray:userLocationLevelValuesArray,
					groupingLocationsListArray:groupingLocationsArray,
					startDateString : startDateString,
 			        endDateString :   endDateString,
					state:state
					}
		
		$.ajax({
			type : 'POST',
			url : 'committeesPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#districtWiseCommitteesReport").html('');
			buildCommitteesPerformanceCohort(result);
			
		});
	}
	
	function getChildUserTypesByItsParentUserType(){
		
		var jsObj = { parentUserTypeId : globalUserTypeId }
		$.ajax({
			type : 'POST',
			url : 'getChildUserTypesByItsParentUserTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildgetChildUserTypesByItsParentUserType(result)
		});			 
	}
	
	
	function getSelectedChildUserTypeMembers(childUserTypeId){
	$("#SelectedUserTypeDetailsDiv").html('<div ><center ><img  src="images/icons/loading.gif"></center></div>');
     var parentActivityMemberId = globalActivityMemberId;
	 var childUserTypeId = childUserTypeId;
	 var date = $("#dateRangeId").val();
	   
	  var state = globalState;
  	  var basicCommitteeIdsArray= [];
	  basicCommitteeIdsArray.push(1);
	  basicCommitteeIdsArray.push(2);
	  basicCommitteeIdsArray.push(3);
	  
	  var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeId : childUserTypeId,
				   dateString : date,
				   state:state,
				   basicCommitteeIdsArray:basicCommitteeIdsArray
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildUserTypeMembersAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#SelectedUserTypeDetailsDiv").html('');
			buildgetSelectedChildUserTypeMembers(result);
		});
	}
	
	function buildgetCommitteesBasicCountReport(result){
		$("#basicCommitteeCountsDiv").html('');
		var str='';
		var locationLevelNameArray =[];
		str+='<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 committeesBlock">';
				str+='<ul class="committesBlockUl">';
					str+='<li>';
						str+='<h4 class="text-capital bg_49 pad_custom">main committees</h4>';
						
						str+='<table class="table table-condensed">';
						   str+='<tr>';
								str+='<td>';
								if(result.mainVO.totalCount !=null && result.mainVO.totalCount >0){
									str+='<h3>'+result.mainVO.totalCount+'</h3>';
								}else{
									str+='<h3> - </h3>';
								}
									str+='<h5 class="text-muted text-capitalize">total</h5>';
								str+='</td>';
								str+='<td>';
								if(result.mainVO.startedCount !=null && result.mainVO.startedCount >0){
									str+='<h3>'+result.mainVO.startedCount+'</h3>';
									str+='<h5 class="text-muted text-capitalize">started</h5>';
									str+='<small class="text-success">'+result.mainVO.startedPerc+'%</small>';
									
								}else{
									str+='<h3> - </h3>';
									str+='<h5 class="text-muted text-capitalize">started</h5>';
								}
								str+='</td>';
								str+='<td>';
								if(result.mainVO.completedCount !=null && result.mainVO.completedCount  >0){
									str+='<h3>'+result.mainVO.completedCount+'</h3>';
									str+='<h5 class="text-muted">Completed</h5>';
									str+='<small class="text-success">'+result.mainVO.completedPerc+'%</small>';
									
								}else{
									str+='<h3> - </h3>';
									str+='<h5 class="text-muted">Completed</h5>';
								}
								str+='</td>';
							str+='</tr>';
						str+='</table>';
					str+='</li>';
					str+='<li>';
						str+='<h4 class="text-capital bg_49 pad_custom">affliated committees</h4>';
						str+='<table class="table table-condensed">';
							str+='<tr>';
								str+='<td>';
								if(result.affliatedVO.startedCount !=null && result.affliatedVO.startedCount  >0){
									str+='<h3>'+result.affliatedVO.startedCount+'</h3>';
								}else{
									str+='<h3> - </h3>';
								}
									str+='<h5 class="text-muted text-capitalize">Started</h5>';
								str+='</td>';
								str+='<td>';
								if(result.affliatedVO.completedCount !=null && result.affliatedVO.completedCount >0){
									str+='<h3>'+result.affliatedVO.completedCount+'</h3>';
								}else{
									str+='<h3> - </h3>';
								}
								str+='<h5 class="text-muted text-capitalize">Completed</h5>';
								str+='</td>';
						   str+='</tr>';
						str+='</table>';
					str+='</li>';
					str+='<hr style="margin:0px;">';
					if(result.subList != null && result.subList.length >0){
						var length = result.subList.length - 1;
							for(var i = length; i >= 0; i--){
								str+='<li>';
										var properName = getProperLocationLevelName(result.subList[i].name);
										if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
											locationLevelNameArray.push(properName);
											str+='<h4 class="text-capitalize m_top10" style="color:#c9c0cc">'+properName+' Level</h4>';
										}
										str+='<table class="table table-condensed bg_ED">';
											str+='<tr>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Total</h5>';
													if(result.subList[i].mainVO.totalCount !=null && result.subList[i].mainVO.totalCount >0){
														str+='<p>'+result.subList[i].mainVO.totalCount+'</p>';
													}else{
															str+='<p> - </p>';
													}
													
											   str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Started</h5>';
													if(result.subList[i].mainVO.startedCount !=null && result.subList[i].mainVO.startedCount >0){
														str+='<p>'+result.subList[i].mainVO.startedCount+' <small class="text-success"> '+result.subList[i].mainVO.startedPerc+'%</small></p>';
													}else{
														str+='<p> - </p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Completed</h5>';
													if(result.subList[i].mainVO.completedCount !=null && result.subList[i].mainVO.completedCount >0){
														str+='<p>'+result.subList[i].mainVO.completedCount+'<small class="text-success"> '+result.subList[i].mainVO.completedPerc+'%</small></p>';
													}else{
														str+='<p> - </p>';
													}
													
												str+='</td>';
											str+='</tr>';
										str+='</table>';
									str+='</li>';
									str+='<li>';
										str+='<table class="table table-condensed bg_ED" style="margin-top:30px !important;">';
											str+='<tr>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Started</h5>';
													if(result.subList[i].affliatedVO.startedCount !=null && result.subList[i].affliatedVO.startedCount >0){
														str+='<p>'+result.subList[i].affliatedVO.startedCount+'</p>';
													}else{
														str+='<p> - </p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Completed</h5>';
													if(result.subList[i].affliatedVO.completedCount !=null && result.subList[i].affliatedVO.completedCount >0){
														str+='<p>'+result.subList[i].affliatedVO.completedCount+'</p>';
													}else{
														str+='<p> - </p>';
													}
													
												str+='</td>';
											str+='</tr>';
										str+='</table>';
									str+='</li>';
							}
							
					}
					
				str+='</ul>';
        str+='</div>';
		
		$("#basicCommitteeCountsDiv").html(str);
	}
	
	function getProperLocationLevelName(levelName){
		var properName = "";
		if(levelName == "Village"){
			properName = "Village / Ward ";
		}else if(levelName == "Mandal"){
			properName = "Mandal / Town / Division ";
		}else{
			properName = levelName;
		}
		return properName;
	}
	
	function buildgetLevelWiseBasicCommitteesCountReport(result)
	{
		$("#levelWiseBasicCommittees").html('');
		
		var locationLevelNameArray =[];
		//var categories = [ "specialword", "word1", "word2" ],
        //found = $.inArray('specialword1', categories);
		
		if(result != null && result.length > 0){
			var str='';
			str+='<ul class="villageWardUl">';
			var length = result.length - 1;
			for(var i = length; i >= 0; i--){
				
				if(result[i].subList !=null && result[i].subList.length > 0){
					for(var j in result[i].subList){
						
						str+='<li>';
						var properName = getProperLocationLevelName(result[i].name);
						if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
							locationLevelNameArray.push(properName);
							str+='<h4>'+properName+' Level</h4>';
						}
						str+='<div id="mainCommittees'+i+''+j+'" class="chartLi" ></div>';
						str+='</li>';
					}
				}
										
            }
			str+='<ul>';
			
		}
	$("#levelWiseBasicCommittees").html(str);
		if(result != null && result.length > 0){
			var length = result.length - 1;
			for(var i = length; i >= 0; i--){
				
				if(result[i].subList !=null && result[i].subList.length > 0){
					for(var j in result[i].subList){
						var committeeName = result[i].subList[j].name;
						var levelWiseBasicCompletedPercArray = [];
						var levelWiseBasicStartedPercArray = [];
						var levelWiseBasicNotStartedPercArray = [];
						if(result[i].subList[j].completedPerc !=null && result[i].subList[j].completedPerc >0){
							levelWiseBasicCompletedPercArray.push(result[i].subList[j].completedPerc);
						}else{
							levelWiseBasicCompletedPercArray.push(" ");
						}
						if(result[i].subList[j].startedPerc !=null && result[i].subList[j].startedPerc >0){
							levelWiseBasicStartedPercArray.push(result[i].subList[j].startedPerc);
						}else{
							levelWiseBasicStartedPercArray.push(" ");
						}
						if(result[i].subList[j].notStartedPerc !=null && result[i].subList[j].notStartedPerc >0){
							levelWiseBasicNotStartedPercArray.push(result[i].subList[j].notStartedPerc); 
						}else{
							levelWiseBasicNotStartedPercArray.push(" ");
						}
						
						//if(committeeName == "Main")
						
						
						$(function () {
							$('#mainCommittees'+i+''+j+'').highcharts({
								colors: ['#F56800','#53BF8B','#66728C'],
								chart: {
									type: 'column',
									
								},
								title: {
									text: committeeName,
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
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}%</b><br/>',
									shared: true
								},
								legend: {
									enabled: true,
									align: 'left'
							
								},
								plotOptions: {
									column: {
										  stacking: 'percent',
										dataLabels:{
											enabled: true,
											formatter: function () {
												if (this.y > 0) return this.y + '%';
												else return '';
											}
										},
										
									},
								},
								 series: [{
									name: 'Started',
									data: levelWiseBasicStartedPercArray 
								}, {
									name: 'Completed',
									data: levelWiseBasicCompletedPercArray
								}, {
									name: 'Yet To Start',
									data: levelWiseBasicNotStartedPercArray
								}]
							});
						});	
						
					}
				}
				
			}
		}else{
			$("#levelWiseBasicCommittees").html("No Data Available");
		}
		
		
		$(".villageWardUl").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 4,
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 4,
					slidesToScroll: 4,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 2
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
	
	
	
	function buildCommitteesPerformanceCohort(result){
		$("#districtWiseCommitteesReport").html('');
		if(result != null && result.length > 0){
			var str='';
			
			for(var i in result){
				str+=result[i].name;
				str+='<div id="mainCommittees'+i+'" class="chartLiD" ></div>';
			}
			
		}
		$("#districtWiseCommitteesReport").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
			var districtNamesArray =[];
			var districtWiseCompletedPercArray = [];
			var districtWiseStartedPercArray = [];
			var districtWiseNotStartedPercArray = [];
			if(result[i].subList !=null && result[i].subList.length > 0){
				for(var j in result[i].subList){
						districtNamesArray.push(result[i].subList[j].name);
						
						if(result[i].subList[j].completedPerc !=null && result[i].subList[j].completedPerc >0){
							districtWiseCompletedPercArray.push(result[i].subList[j].completedPerc);
						}else{
							districtWiseCompletedPercArray.push(" ");
						}
						if(result[i].subList[j].startedPerc !=null && result[i].subList[j].startedPerc >0){
							districtWiseStartedPercArray.push(result[i].subList[j].startedPerc);
						}else{
							districtWiseStartedPercArray.push(" ");
						}
						if(result[i].subList[j].notStartedPerc !=null && result[i].subList[j].notStartedPerc >0){
							districtWiseNotStartedPercArray.push(result[i].subList[j].notStartedPerc);
						}else{
							districtWiseNotStartedPercArray.push("  ");
						}
					}
			}
						$(function () {
							$('#mainCommittees'+i+'').highcharts({
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
										categories: districtNamesArray
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
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.0f}%</b><br/>',
									shared: true
								},
								plotOptions: {
									column: {
										stacking: 'percent',
										dataLabels: {
											enabled: true,
											formatter: function(){
												return Highcharts.numberFormat(this.y,0) + '%';
											}
										  
										}
									}
								},
								series: [{
									name: 'Completed',
									data: districtWiseCompletedPercArray
								}, {
									name: 'Started',
									data: districtWiseStartedPercArray
								}, {
									name: 'Yet To Started',
									data: districtWiseNotStartedPercArray
								}]
							});
						});
				
			
		}
	}else{
		$("#districtWiseCommitteesReport").html("No Data Available")
	}	
}
	
	$("#levelWiseBasicCommittees").on("click",".slick-next,.slick-prev",function(){
		var currentSliderLevel = $(".slick-current").find("h4").html();
		
		var tdpCommitteeLevelIdsClickedArray = [];
		
		if(currentSliderLevel.toLowerCase().indexOf("mandal") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(5);
			tdpCommitteeLevelIdsClickedArray.push(7);
			tdpCommitteeLevelIdsClickedArray.push(9);
		}else if(currentSliderLevel.toLowerCase().indexOf("village") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(6);
			tdpCommitteeLevelIdsClickedArray.push(8);
		}else if(currentSliderLevel.toLowerCase().indexOf("district") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(11);
		}else if(currentSliderLevel.toLowerCase().indexOf("state") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(10);
		}
		
		getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray);
		
	});
	
	function buildgetChildUserTypesByItsParentUserType(result){
		$("#childUserTypeDetailsDiv").html('');
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeId;
		 
		 if(result !=null && result.length >0){
			 firstChildUserTypeId = result[0].userTypeId;
			 for(var i in result){
				 str+='<li attr_userTypeId="'+result[i].userTypeId+'" class="childUserTypeCls">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDiv").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildUserTypeMembers(firstChildUserTypeId);
	}
	
	
	function buildgetSelectedChildUserTypeMembers(result){
		$("#SelectedUserTypeDetailsDiv").html('');
	var str='';
	if(result !=null && result.length >0){
		str+='<ul class="list-inline slickPanelSlider">';
		for(var i in result){
			str+='<li  style="cursor:pointer;" class="compareActivityMemberCls" attr_id ="directChildActivityMemberDiv" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' >';
			
				str+='<div class="panel panel-default panelSlick">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title"  >'+result[i].name+'</h4>';
						str+='<span class="count">01</span>';
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<h4 class="text-capital">'+result[i].userType+'</h4>';
						str+='<table class="table table-condensed">';
							str+='<thead>';
								str+='<th>Total</th>';
								str+='<th>Started</th>';
								str+='<th>Completed</th>';
								str+='<th>%</th>';
							str+='</thead>';
							str+='<tr>';
							if(result[i].totalCount !=null && result[i].totalCount >0){
								str+='<td>'+result[i].totalCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].startedCount !=null && result[i].startedCount >0){
								str+='<td>'+result[i].startedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].completedCount !=null && result[i].completedCount >0){
								str+='<td>'+result[i].completedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].completedPerc !=null && result[i].completedPerc >0){
								str+='<td>'+result[i].completedPerc+'</td>';
							}else{
								str+='<td> - </td>';
							}
							str+='</tr>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
		
		}
		str+='</ul>';
		
		$("#SelectedUserTypeDetailsDiv").html(str);
			$(".slickPanelSlider").slick({
				 slide: 'li',
				 slidesToShow: 3,
				 slidesToScroll: 3,
				 infinite: false,
				  responsive: [
					{
					  breakpoint: 1024,
					  settings: {
						slidesToShow: 3,
						slidesToScroll: 3,
						infinite: false,
						dots: false
					  }
					},
					{
					  breakpoint: 800,
					  settings: {
						slidesToShow: 2,
						slidesToScroll: 2
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
	}else{
		$("#SelectedUserTypeDetailsDiv").html("No Data Available");
	}
		
		
	}
	
	$(document).on("click",".childUserTypeCls",function(){
		$("#directChildActivityMemberDiv").html('');
		var childUserTypeId = $(this).attr("attr_userTypeId");
		
		getSelectedChildUserTypeMembers(childUserTypeId);
	});
	
	function buildgetUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(result){
		var str='';
		if(result != null && result.length > 0){
		
			var str='';
			
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4 class="text-capital">'+result[i][0].userType+'</h4>';
					str+='<div id="genSec'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
					
			}
			
		}
		$("#userTypeWiseCommitteesForTopFiveStrongDiv").html(str);
		if(result != null && result.length > 0){
			for(var i in result){
				var length1 = result[i].length ;
				var candidateNameArray = [];
				var CommitteeCompleteCountArray = [];
				var countVar =0;
				for(var j = 0; j <= length1; j++){
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				
					candidateNameArray.push(result[i][j].name);
					CommitteeCompleteCountArray.push(result[i][j].completedCount);
					
				}
					
				
				var getWidth = $("#genSec"+i).parent().width()+'px';
				$("#genSec"+i).width(getWidth);
				$(function () {
					$("#genSec"+i).highcharts({
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
								overflow: 'justify'
							}
						},
						tooltip: {
							headerFormat: '<b>{point.x}</b><br/>',
							pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.0f}%</b><br/>',
							shared: true
						},
						plotOptions: {
							bar: {
								dataLabels: {
									enabled: true,
									formatter: function(){
										return Highcharts.numberFormat(this.y,0) + '%';
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
							name: 'Completed',
							data: CommitteeCompleteCountArray
						}]
					});
				});
			}
			
		}
		
	}
	
	function buildgetUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(result){
		var str='';
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4 class="text-capital">'+result[i][0].userType+'</h4>';
					str+='<div id="genSec1'+i+'" style="width:100%;height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#userTypeWiseCommitteesForTopFivePoorDiv").html(str);
		if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameArray = [];
				var CommitteeCompleteCountArray = [];
				var countVar = 0;
				var length = result[i].length - 1;
				for(var j = length; j >= 0; j--){
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
					candidateNameArray.push(result[i][j].name);
					CommitteeCompleteCountArray.push(result[i][j].completedCount);
					
				}
					
				
				var getWidth = $("#genSec1"+i).parent().width()+'px';
				$("#genSec1"+i).width(getWidth);
				$(function () {
					$("#genSec1"+i).highcharts({
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
								overflow: 'justify'
							}
						},
						tooltip: {
							headerFormat: '<b>{point.x}</b><br/>',
							pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.0f}%</b><br/>',
							shared: true
						},
						plotOptions: {
							bar: {
								dataLabels: {
									enabled: true,
									formatter: function(){
										return Highcharts.numberFormat(this.y,0) + '%';
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
							name: 'Completed',
							data: CommitteeCompleteCountArray
						}]
					});
				});
			}
			
		}
		
	}
	$(document).on("click",".topFivePoorResults",function(){
		$("#userTypeWiseCommitteesForTopFiveStrongDiv").hide();
		$("#userTypeWiseCommitteesForTopFivePoorDiv").show();
		getUserTypeWiseCommitteesCompletedCounts1();
	})
	$(document).on("click",".topFiveStrongResults",function(){
		$("#userTypeWiseCommitteesForTopFiveStrongDiv").show();
		$("#userTypeWiseCommitteesForTopFivePoorDiv").hide();
		getUserTypeWiseCommitteesCompletedCounts1();
	})
	
	
	$(document).on("click",".compareActivityMemberCls",function(){
		$(".slickPanelSlider").find("li").removeClass("active")
		$(this).addClass("active")
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).attr("attr_id");  
		$(".showChildBlockAndTopPoorBlock").show();
		
		getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		getTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		getTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
	})
	function getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
	   $("#"+childActivityMemberId).html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
	   var state ='AP';
	   var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   var dateString = $('#dateRangeId').val();
	   
	   var jsObj ={  activityMemberId : activityMemberId,
			         userTypeId : userTypeId,
					 state:state,
					 basicCommitteeIdsArray : basicCommitteeIdsArray,
 			         dateString :   dateString
				  }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildActivityMemberCommitteeDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#"+childActivityMemberId).html('');
			buildgetDirectChildActivityMemberCommitteeDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId);
		});
	}
	function getTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType){
	   $("#topPoorPerformanceDiv").html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
	   var state ='AP';
	   var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   var dateString = $('#dateRangeId').val();
	   
	   var jsObj ={  activityMemberId : activityMemberId,
					 state:state,
					 basicCommitteeIdsArray : basicCommitteeIdsArray,
 			         dateString :   dateString
				  }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getTopPoorPerformancecommitteesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#topPoorPerformanceDiv").html('');
			buildgetTopPoorPerformancecommittees(result,selectedMemberName,selectedUserType);
			
		});
	}
	function getTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType){
	   $("#topPoorLocationsDiv").html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
	   var state ='AP';
	   var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   var dateString = $('#dateRangeId').val();
	   
	   var jsObj ={  activityMemberId : activityMemberId,
					 state:state,
					 basicCommitteeIdsArray : basicCommitteeIdsArray,
 			         dateString :   dateString
				  }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getTopPoorCommitteeLocationsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#topPoorLocationsDiv").html('');
			buildTopPoorCommitteeLocations(result,selectedMemberName,selectedUserType);
			
		});
	}
	function buildgetDirectChildActivityMemberCommitteeDetails(result,selectedMemberName,selectedUserType,childActivityMemberId){
		$("#"+childActivityMemberId).html('');
		var str ='';
		
		if(result != null && result.length >0){
			
			str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
			if(childActivityMemberId != "directChildActivityMemberDiv"){
				str+='<span class="removeSelecUserType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
			}
			
				if(childActivityMemberId != "directChildActivityMemberDiv")
				{
					str+='<table class="table table-condensed tableLevels m_top20">';
				}else{
					str+='<table class="table table-condensed tableHoverLevels m_top20">';
				}
				
					str+='<thead class="bg_D8 text-capital">';
						str+='<th>% Rank</th>';
						str+='<th>Designation</th>';
						str+='<th>Name</th>';
						str+='<th>total</th>';
						str+='<th>yet to start</th>';
						str+='<th>started</th>';
						str+='<th>completed</th>';
						str+='<th>%</th>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						 var yourValues = result[i].locationName;
						str+='<tr class="compareLowLevelActivityMemberCls"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
							str+='<td>';
								str+='<span class="tableCount">01</span>';
							str+='</td>';
							if( yourValues.indexOf(',') == -1){
								str+='<td>'+result[i].userType+' (<b>'+result[i].locationName+'</b>)</td>';
							}else{
								str+='<td>'+result[i].userType+'</td>';
							}
							if( result[i].name != null && $.trim(result[i].name).length > 0 ){
									str+='<td>'+result[i].name+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].totalCount != null && result[i].totalCount >0){
									str+='<td>'+result[i].totalCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].notStartedCount != null && result[i].notStartedCount >0){
									str+='<td>'+result[i].notStartedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].startedCount != null && result[i].startedCount >0){
									str+='<td>'+result[i].startedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].completedCount != null && result[i].completedCount >0){
									str+='<td>'+result[i].completedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].completedPerc != null && result[i].completedPerc >0){
									str+='<td>'+result[i].completedPerc+'</td>';
							}else{
								str+='<td> - </td>';
							}
						str+='</tr>';
						
						str+='<tr class="showHideTr" style="display:none" attr_id = "districtpositionId'+result[i].userTypeId+''+i+'">';
							
							str+='<td colspan="8"  id="districtpositionId'+result[i].userTypeId+''+i+'">';
							
							str+='</td>';
						str+='</tr>';
					
			}
			str+='</tbody>';
				str+='</table>';
				$("#"+childActivityMemberId).html(str);
		}else{
			$("#"+childActivityMemberId).html("No Data Available");
			
		}
	
	}
	 
	 $(document).on("click",".compareLowLevelActivityMemberCls",function(){
		 $(this).closest('tr').next('tr.showHideTr').show(); 
		 
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
		getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		getTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		getTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
	})
	
	$(document).on("click",".removeSelecUserType",function(){
		 
		 var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		 $("#"+removeSelected).remove();
		 
	});
	
	function buildgetTopPoorPerformancecommittees(result,selectedMemberName,selectedUserType){
		$("#topPoorPerformanceDiv").html('');
		var str='';
		 str+='<b><span class="color_333 pad_5 bg_CC text-capital">top <span class="text-danger">poor</span> performance affliated committees - (<span style="font-size:11px;"><i> '+selectedMemberName+' - '+selectedUserType+'</i></span>)</span></b>';
			str+='<div class="row m_top20">';
			
		if(result.subList1 != null && result.subList1.length >0){
			str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<p class="text-capital"><b>all levels cumulative</b></p>';
			str+='<table class="table tableCumulative">';
			var number =0;
				for(var i in result.subList1){
					str+='<tr>';
					number = number+1;
					str+='<td><span class="count" style="background-color:rgba(237, 29, 38,1)">'+number+'</span></td>';
					str+='<td>'+result.subList1[i].name+'</td>';
					str+='<td>';
						str+='<div class="progress progressCustom">';
						if(result.subList1[i].completedCount !=null && result.subList1[i].completedCount >0){
							str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result.subList1[i].completedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result.subList1[i].completedPerc+'%;">';
							str+='<span class="sr-only">'+result.subList1[i].completedPerc+'% Complete</span>';
							str+='</div>';
							str+='</div>';
							str+='</td>';
							str+='<td class="text-danger">'+result.subList1[i].completedCount+'</td>';
						}else{
							str+='<td class="text-danger"> - </td>';
						}
				str+='</tr>';
				}
				
				
			str+='</table>';
		str+='</div>';
		}
		if(result.subList != null && result.subList.length >0){
			var locationLevelNameArray =[];
			for(var i in result.subList){
				str+='<div class="col-md-6 col-xs-12 col-sm-6 m_top20">';
				var properName = getProperLocationLevelName(result.subList[i].name);
					if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
						locationLevelNameArray.push(properName);
						str+='<p class="text-capital"><b>'+properName+'</b></p>';
					}
					var number =0;
				str+='<table class="table tableCumulative">';
					for(var j in result.subList[i].subList){
						str+='<tr>';
						number = number+1;
						str+='<td><span class="count" style="background-color:rgba(237, 29, 38,1)">'+number+'</span></td>';
						str+='<td>'+result.subList[i].subList[j].name+'</td>';
						str+='<td>';
						if(result.subList[i].subList[j].completedCount !=null && result.subList[i].subList[j].completedCount >0){
							str+='<div class="progress progressCustom">';
							  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result.subList[i].subList[j].completedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result.subList[i].subList[j].completedPerc+'%;">';
								str+='<span class="sr-only">'+result.subList[i].subList[j].completedPerc+'% Complete</span>';
							  str+='</div>';
							str+='</div>';
							str+='</td>';
							str+='<td class="text-danger">'+result.subList[i].subList[j].completedCount+'</td>';
						}else{
							str+='<td class="text-danger"> - </td>';
						}
							
					str+='</tr>';
					}
				str+='</table>';
				str+='</div>';
			}
		
		}
		str+='</div>';
		$("#topPoorPerformanceDiv").html(str);
		
	}
	
	function buildTopPoorCommitteeLocations(result,selectedMemberName,selectedUserType){
		var str ='';
		
		if(result !=null && result.length >0){
			str+='<b><span class="color_333 pad_5 bg_CC text-capital">top <span class="text-danger">poor</span> locations - (<span style="font-size:11px;"><i> '+selectedMemberName+' - '+selectedUserType+'</i></span>)</span></b>';
			str+='<div class="row m_top20">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<p class="text-capital"><b>'+result[0].requiredName+'</b></p>';
					str+='<table class="table tableCumulative">';
			var countVar =0;
			var length1 = result.length ;
				for(var i = 0; i <= length1; i++){
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
						str+='<tr>';
							str+='<td><span class="count" style="background-color:rgba(237, 29, 38,1)">'+countVar+'</span></td>';
							str+='<td>'+result[i].name+'</td>';
							str+='<td>';
							if(result[i].completedCount !=null && result[i].completedCount >0){
								str+='<div class="progress progressCustom">';
								  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].completedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result[i].completedPerc+'%;">';
									str+='<span class="sr-only">'+result[i].completedPerc+'% Complete</span>';
								  str+='</div>';
								str+='</div>';
							str+='</td>';
							str+='<td class="text-danger">'+result[i].completedCount+'</td>';
							}else{
								str+='<td class="text-danger"> - </td>';
							}
								
						str+='</tr>';
				}
				str+='</table>';
				str+='</div>';
			str+='</div>';
			$("#topPoorLocationsDiv").html(str);
		}else{
			$("#topPoorLocationsDiv").html("No Data Available");
		}			
			
	}