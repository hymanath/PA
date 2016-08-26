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
			buildgetUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(result);
			buildgetUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(result);
			
		});
	}
	function getLevelWiseBasicCommitteesCountReport(){
		
		$("#levelWiseBasicCommittees").html('<div ><center ><img  src="images/icons/loading.gif" id="commulativeEnlargeLoadingId"></center></div>');
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
	$("#SelectedUserTypeDetailsDiv").html('<div ><center ><img  src="images/icons/loading.gif" id="commulativeEnlargeLoadingId"></center></div>');
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
								if(result.mainVO.totalCount == null || result.mainVO.totalCount == 0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.mainVO.totalCount+'</h3>';
								}
									str+='<h5 class="text-muted text-capitalize">total</h5>';
								str+='</td>';
								str+='<td>';
								if(result.mainVO.startedCount == null || result.mainVO.startedCount == 0){
									str+='<h3> - </h3>';
									str+='<h5 class="text-muted text-capitalize">started</h5>';
								}else{
									str+='<h3>'+result.mainVO.startedCount+'</h3>';
									str+='<h5 class="text-muted text-capitalize">started</h5>';
									str+='<small class="text-success">'+result.mainVO.startedPerc+'%</small>';
								}
								str+='</td>';
								str+='<td>';
								if(result.mainVO.completedCount == null || result.mainVO.completedCount == 0){
									str+='<h3> - </h3>';
									str+='<h5 class="text-muted">Completed</h5>';
								}else{
									str+='<h3>'+result.mainVO.completedCount+'</h3>';
									str+='<h5 class="text-muted">Completed</h5>';
									str+='<small class="text-success">'+result.mainVO.completedPerc+'%</small>';
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
								if(result.affliatedVO.startedCount == null || result.affliatedVO.startedCount == 0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.affliatedVO.startedCount+'</h3>';
								}
									str+='<h5 class="text-muted text-capitalize">Started</h5>';
								str+='</td>';
								str+='<td>';
								if(result.affliatedVO.completedCount == null || result.affliatedVO.completedCount ==0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.affliatedVO.completedCount+'</h3>';
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
													if(result.subList[i].mainVO.totalCount == null || result.subList[i].mainVO.totalCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].mainVO.totalCount+'</p>';
													}
													
											   str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Started</h5>';
													if(result.subList[i].mainVO.startedCount == null || result.subList[i].mainVO.startedCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].mainVO.startedCount+' <small class="text-success"> '+result.subList[i].mainVO.startedPerc+'%</small></p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Completed</h5>';
													if(result.subList[i].mainVO.completedCount == null || result.subList[i].mainVO.completedCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].mainVO.completedCount+'<small class="text-success"> '+result.subList[i].mainVO.completedPerc+'%</small></p>';
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
													if(result.subList[i].affliatedVO.startedCount == null || result.subList[i].affliatedVO.startedCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].affliatedVO.startedCount+'</p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<h5 class="text-muted text-capitalize">Completed</h5>';
													if(result.subList[i].affliatedVO.completedCount == null || result.subList[i].affliatedVO.completedCount == 0){
														str+='<p> - </p>';
													}else{
														str+='<p>'+result.subList[i].affliatedVO.completedCount+'</p>';
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
						var completedPerc = [];
						var startedPerc = [];
						var notStartedPerc = [];
						if(result[i].subList[j].completedPerc == null || result[i].subList[j].completedPerc == 0){
							completedPerc.push(" - ")
						}else{
							completedPerc.push(result[i].subList[j].completedPerc)
						}
						if(result[i].subList[j].startedPerc == null || result[i].subList[j].startedPerc == 0){
							startedPerc.push(" - ")
						}else{
							startedPerc.push(result[i].subList[j].startedPerc)
						}
						if(result[i].subList[j].notStartedPerc == null || result[i].subList[j].notStartedPerc == 0){
							notStartedPerc.push(" - ")
						}else{
							notStartedPerc.push(result[i].subList[j].notStartedPerc) 
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
									data: startedPerc 
								}, {
									name: 'Completed',
									data: completedPerc
								}, {
									name: 'Yet To Start',
									data: notStartedPerc
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
			var completedPercArray = [];
			var startedPercArray = [];
			var notStartedPercArray = [];
			if(result[i].subList !=null && result[i].subList.length > 0){
				for(var j in result[i].subList){
						districtNamesArray.push(result[i].subList[j].name);
						
						if(result[i].subList[j].completedPerc == null || result[i].subList[j].completedPerc == 0){
							completedPercArray.push(" - ")
						}else{
							completedPercArray.push(result[i].subList[j].completedPerc)
						}
						if(result[i].subList[j].startedPerc == null || result[i].subList[j].startedPerc == 0){
							startedPercArray.push(" - ")
						}else{
							startedPercArray.push(result[i].subList[j].startedPerc)
						}
						if(result[i].subList[j].notStartedPerc == null || result[i].subList[j].notStartedPerc == 0){
							notStartedPercArray.push(" - ")
						}else{
							notStartedPercArray.push(result[i].subList[j].notStartedPerc) 
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
									data: completedPercArray
								}, {
									name: 'Started',
									data: startedPercArray
								}, {
									name: 'Yet To Started',
									data: notStartedPercArray
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
			str+='<li>';
				str+='<div class="panel panel-default panelSlick">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">'+result[i].name+'</h4>';
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
								str+='<td>'+result[i].totalCount+'</td>';
								str+='<td>'+result[i].startedCount+'</td>';
								str+='<td>'+result[i].completedCount+'</td>';
								str+='<td>'+result[i].completedPerc+'</td>';
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
	
	function initialiseGraph()
	{
		var getWidth = $("#genSec").parent().width()+'px';
		$("#genSec").width(getWidth);
		$(function () {
			$('#genSec').highcharts({
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
					categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
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
					name: 'Year 1800',
					data: [107, 31, 635, 203, 2]
				}]
			});
		});
		$(function () {
			$('#Secretary').highcharts({
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
					categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
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
					name: 'Year 1800',
					data: [107, 31, 635, 203, 2]
				}]
			});
		});
		$(function () {
			$('#memOfParliament').highcharts({
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
					categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
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
					name: 'Year 1800',
					data: [107, 31, 635, 203, 2]
				}]
			});
		});
		$(function () {
			$('#disIncharges').highcharts({
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
					categories: ['Parthasarathi', 'Satyanarayana Murthy', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
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
					name: 'Year 1800',
					data: [107, 31, 635, 203, 2]
				}]
			});
		});
	}
	
	
	