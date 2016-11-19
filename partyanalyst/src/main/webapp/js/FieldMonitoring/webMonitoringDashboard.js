	var customStartDate = moment().format("MM/DD/YYYY");
	var customEndDate = moment().format("MM/DD/YYYY");
	function onLoadCalls(){
		getOverAllDataCollectorsCounts(1)
		getIssueStatusWiseCounts(1);
		getIssueTypeWiseCounts(1);
		getDataMonitoringOverViewDetails(1);
		getDistrictWiseIssueTypesCount(1);
		//getLocationWiseOverAllDetails("state",1,"districtWiseOverviewDetailsId");
	}
	function getOverAllDataCollectorsCounts(state){
		var dates = $(".singleDate").val();
		var dateArr = dates.split("-");
		var fromDate;
		var toDate;
		var district=0;
		var constituency=0;
		var user=0;
		if(dateArr != null){
			fromDate = dateArr[0];
			toDate = dateArr[1];
		}
		var jsObj = { 
		  stateId : state,
		  districtId : district,
		  constituencyId : constituency,
		  userId : user,
		  fromDate : fromDate,		//"10/01/2016",
		  toDate : toDate		 	//"10/18/2016"  
		}
		$.ajax({
			type : 'GET',
			url : 'getOverAllDataCollectorsDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result != null){
				$("#totalDataCollectorsId").html(result.totalDataCollectors);
				$("#totalRegisteredId").html(result.todayRegCount);
				$("#todayActMembersId").html(result.todayActiveUsers);
				$("#lastHrActiveId").html(result.lastOneHrActUsers);
				$("#passiveHrId").html(result.passiveUsers);
				$("#notYetStartId").html(result.notYetStartedUsers);
				
			}	
		});
	}
	function getIssueStatusWiseCounts(state){
			$("#statusCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			
			var jsObj = { 
			              stateId : state,
			              fromDate : customStartDate,    //"2016-10-01",
		                  toDate : customEndDate,			//"2016-10-18" 
						  task   : "dataMonitoringDashboard"
						}
			$.ajax({
				type : 'GET',
				url : 'getIssueStatusWiseCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
			if(result != null && result.length >0){
				buildIssueStatusWiseCounts(result);
			}	
			});
	}
	function buildIssueStatusWiseCounts(result){
			var str='';
			
			str +='<h4 class="text-capital panel-title"><b>field monitoring system</b></h4>';
			str +='<ul class="dashedB m_top10">';
			for(var i in result){
			if(result[i].name != "DataMoniDashBrd")
			   str +='<li>'+result[i].name+' issues<span>'+result[i].inviteeCount+'</span></li>';
			//else
			   //str +='<li>ACTIVE TEAM MEMBERS<span>'+result[i].inviteeCount+'</span></li>';
			}
			str +='</ul>';
			$("#statusCountDivId").html(str);
		
		}	
		
		
	function getIssueTypeWiseCounts(state){
		var openIssuesArr = [];
		var fixedIssuesArr = [];
		var closedIssuesArr = [];
			var jsObj = { 
				fromDate : customStartDate, //"10/01/2016",
				toDate : customEndDate ,     //"10/20/2016",
				stateId : state
			}
			$.ajax({
				type : 'GET',
				url : 'getIssueTypeWiseCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
				if(result != null && result.length >0){
					for(var i in result){
						if(result[i].issueTypes != null && result[i].issueTypes.length > 0){
							var openIssueCount =0;
							if(result[i].id == 1){
								for(var j in result[i].issueTypes){
									openIssueCount = openIssueCount+result[i].issueTypes[j].inviteeCount;
									$("#openIssuesCountId span").text("-"  +openIssueCount);
									var dataArr = [];
									dataArr.push(result[i].issueTypes[j].name);
									dataArr.push(parseInt(result[i].issueTypes[j].inviteeCount));
									openIssuesArr.push(dataArr);
								}
							}
							var fixedIssuecount= 0;
							if(result[i].id == 2){
								for(var j in result[i].issueTypes){
									fixedIssuecount = fixedIssuecount+ result[i].issueTypes[j].inviteeCount;
									$("#fixedIsuuesCountId span").text("-"  +fixedIssuecount);
									var dataArr = [];
									dataArr.push(result[i].issueTypes[j].name);
									dataArr.push(parseInt(result[i].issueTypes[j].inviteeCount));
									fixedIssuesArr.push(dataArr);
								}
							}
							var closedIssueCount= 0;
							if(result[i].id == 3){
								for(var j in result[i].issueTypes){
									 closedIssueCount = closedIssueCount+ result[i].issueTypes[j].inviteeCount;
									$("#closedIssuesCountId span").text("-"  +closedIssueCount);
									var dataArr = [];
									dataArr.push(result[i].issueTypes[j].name);
									dataArr.push(parseInt(result[i].issueTypes[j].inviteeCount));
									closedIssuesArr.push(dataArr);
								}
							}
						}
					}
					  $('#openIssues').highcharts({
						chart: {
						  type: 'pie',
						  options3d: {
							enabled: true,
							alpha: 45
						  },
						  spacingTop: -350,
						},
						title: {
							text: null
						},
						subtitle: {
							text: null
						},
						legend: {
						  layout: 'horizontal',
						  floating: false,
						  align: 'bottom',
						  verticalAlign: 'bottom',
						  symbolPadding: 20,
						  symbolWidth: 10,
							labelFormatter: function() {
							  return '<span class=\"' + this.name + '-arrow\"><span style="font-family: \'Advent Pro\', sans-serif; font-size:16px">' + this.name.split("-")[0] +'</span></span><br/><span style="font-size:15px; color:#ababaa">(Count: ' + this.y + ') - ' +
												Highcharts.numberFormat(this.percentage,2)+'%';
						}
					  },
						plotOptions: {
						  pie: {
							  size: 160,
							  allowPointSelect: false,
							  innerSize: 80,
							  depth: 50,
							  cursor: 'pointer',
							  
							  showInLegend: true

							  },
						  series: {
							point: {
							  events: {
								legendItemClick: function () {
									//getStatusWiseIssuesDetails(this.name,1,this.y);
									return false;
								}
							  }
							}
						  }
						},
						series: [{
							name: 'Count',
							data: openIssuesArr,
							dataLabels:{
								enabled: true,
								 distance: -20,
								  formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.percentage,2)+ '%';
										}
									} 
							},
						}]
					});
					
					
					$('#fixedIssues').highcharts({
						chart: {
						  type: 'pie',
						  options3d: {
							enabled: true,
							alpha: 45
						  },
						  spacingTop: -350,
						},
						title: {
							text: null
						},
						subtitle: {
							text: null
						},
						legend: {
						  layout: 'horizontal',
						  floating: false,
						  align: 'bottom',
						  verticalAlign: 'bottom',
						  symbolPadding: 20,
						  symbolWidth: 10,
							labelFormatter: function() {
							  return '<div class="' + this.name + '-arrow"></div><span style="font-family: \'Advent Pro\', sans-serif; font-size:16px">' + this.name.split("-")[0] +'</span><br/><span style="font-size:15px; color:#ababaa">(Count: ' + this.y + ') - ' +
												Highcharts.numberFormat(this.percentage,2)+'%';
						}
					  },
						plotOptions: {
						  pie: {
							  size: 160,
							  allowPointSelect: false,
							  innerSize: 80,
							  depth: 50,
							  cursor: 'pointer',
							  
							  showInLegend: true

							  },
						  series: {
							point: {
							  events: {
								legendItemClick: function () {
									//getStatusWiseIssuesDetails(this.name,2,this.y);
									return false;
								}
							  }
							}
						  }
						},
						series: [{
							name: 'Count',
							data: fixedIssuesArr,
							dataLabels:{
								enabled: true,
								 distance: -20,
								  formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.percentage,2)+ '%';
										}
									} 
							},
						}]
					});
					
					$('#closedIssues').highcharts({
						chart: {
						  type: 'pie',
						  options3d: {
							enabled: true,
							alpha: 45
						  },
						  spacingTop: -350,
						},
						title: {
							text: null
						},
						subtitle: {
							text: null
						},
						legend: {
						  layout: 'horizontal',
						  floating: false,
						  align: 'bottom',
						  verticalAlign: 'bottom',
						  symbolPadding: 20,
						  symbolWidth: 10,
							labelFormatter: function() {
							  return '<div class="' + this.name + '-arrow"></div><span style="font-family: \'Advent Pro\', sans-serif; font-size:16px">' + this.name.split("-")[0] +'</span><br/><span style="font-size:15px; color:#ababaa">(Count: ' + this.y + ') - ' +
												Highcharts.numberFormat(this.percentage,2)+'%';
						}
					  },
						plotOptions: {
						  pie: {
							  size: 160,
							  allowPointSelect: false,
							  innerSize: 80,
							  depth: 50,
							  cursor: 'pointer',
							  
							  showInLegend: true

							  },
						  series: {
							point: {
							  events: {
								legendItemClick: function () {
									//getStatusWiseIssuesDetails(this.name,2,this.y);
									return false;
								}
							  }
							}
						  }
						},
						series: [{
							name: 'Count',
							data: closedIssuesArr,
							dataLabels:{
								enabled: true,
								 distance: -20,
								  formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.percentage,2)+ '%';
										}
									} 
							},
						}]
					});
				}
			});
		}
		
function getDataMonitoringOverViewDetails(state){
 $("#dataMonitoringOverviewTblId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
     var jsObj = {
	      stateId :state,
		  fromDate : customStartDate,
		  toDate : customEndDate
   }
   $.ajax({
     type : 'GET',    
      url : 'getDataMonitoringOverViewDetailsAction.action',  
      dataType : 'json',
      data : {task:JSON.stringify(jsObj)} 
   }).done(function(result){
	    $("#dataMonitoringOverviewTblId").html(' ');
	    if(result != null ){
		   buildDataMonitoringOverViewRslt(result);
		}else{
		  $("#dataMonitoringOverviewTblId").html("NO DATA AVAILABLE.");	
		}
   });
   }
   function buildDataMonitoringOverViewRslt(result){
	   var str='';
        str+='<ul class="dashedB text-capital">';
		 /*str+='<li>';
			str+='active team members';
			str+='<span class="pull-right"> - </span>';
		 str+='</li>';*/
		  str+='<li>';
			str+='total registrations';
			if(result.totalRegCnt > 0){
			  str+='<span class="pull-right">'+result.totalRegCnt+'</span>';
			}else{
			  str+='<span class="pull-right"> - </span>';
			}
		 str+='</li>';
		  str+='<li>';
			str+='verified-passed';
			if(result.totalVerifyRegCnt > 0){
			   str+='<span class="pull-right">'+result.totalVerifyRegCnt+'</span>';
			}else{
				str+='<span class="pull-right"> - </span>';
			}
		str+='</li>';
		  str+='<li>';
			str+='verified- junk/rejected';
				if(result.totalVerifyRejectCnt > 0){
				   str+='<span class="pull-right">'+result.totalVerifyRejectCnt+'</span>';
				}else{
					str+='<span class="pull-right"> - </span>';
				}
		   str+='</li>'; 
		   str+='<li>';
			str+='pending';
			if(result.totalPendingCnt > 0){
				   str+='<span class="pull-right">'+result.totalPendingCnt+'</span>';
				}else{
					str+='<span class="pull-right"> - </span>';
				}
		   str+='</li>';
		str+='</ul>';
	  $("#dataMonitoringOverviewTblId").html(str); 
   }
   $('.singleDate').on("apply.daterangepicker",function(ev,picker){
		customStartDate = picker.startDate.format("MM/DD/YYYY");
		customEndDate = picker.endDate.format("MM/DD/YYYY");
		var state = '';
       $('.stateWiseCls').each(function (){
        state = $(":radio:checked").val();
        });
		getOverAllDataCollectorsCounts(state);
		getIssueStatusWiseCounts(state);
		getIssueTypeWiseCounts(state);
		getDataMonitoringOverViewDetails(state);
		getDistrictWiseIssueTypesCount(state);
   });
   function getDistrictWiseIssueTypesCount(state){
		$("#districtWiseNewsIssuesReport").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		
		var jsObj = { 
		  stateId : state,
		  fromDate : customStartDate,		//"10/01/2016",
		  toDate : customEndDate,		 	//"10/18/2016"  
		  issueStatusId : 1 
		}
		$.ajax({
			type : 'GET',
			url : 'getDistrictWiseIssueTypesCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			buildDistrictWIseIssueTypes(result);
		});
	}
	
	function buildDistrictWIseIssueTypes(result){
		$("#districtWiseNewsIssuesReport").html(' ');
		var str='';
		
		if(result != null && result.length > 0){
			var chartColors = ['#4C7BA7','#93B18B','#9F536F','#EF6072','#E65425','#D5AB59','#D8AB58','#495762'];
			for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" style="margin-bottom: 30px;">';
			str+='<h4>'+result[i].name+' - DISTRICT WISE - <b>OPEN ISSUES</b> OVERVIEW</h4>';
			str+='</div>';
				if(result[i].distList !=null && result[i].distList.length >0){
				str+='<div class="col-xs-12 col-sm-12 col-md-12">';
					str+='<ul class="ComparisionPartyWiseSlickApply">';
						for(var j in result[i].distList){
							str+='<li><h4 class="panel-title">'+result[i].distList[j].name+'</h4><div id="districtWiseissues'+i+''+j+'" class="chartLiD" style="height:300px" ></div></li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-xs-12 col-sm-12 col-md-12">';
					str+='<ul class="list-inline text-center">';
					for(var k in result[0].distList[0].issueTypes){
					  str+='<li><span style="height:10px;width:10px;display:inline-block;border-radius:50%;margin-right:5px;background-color:'+chartColors[k]+'"></span>'+result[0].distList[0].issueTypes[k].name+'</h4></li>';
					}
					str+='</ul>';
				str+='</div>';
				}
			}
		}
			$("#districtWiseNewsIssuesReport").html(str);						

		if(result != null && result.length > 0){
			for(var i in result){
			//	var districtNamesArray;
			
				if(result[i].distList !=null && result[i].distList.length >0){
				
					for(var j in result[i].distList){
					var mainArr=[];
					
					//districtNamesArray = result[i].distList[j].name;
					var isDataAvail = false;
					if(result[i].distList[j].issueTypes !=null && result[i].distList[j].issueTypes.length >0){
						for(var k in result[i].distList[j].issueTypes){
							if(result[i].distList[j].issueTypes[k].inviteeCount !=0){
							isDataAvail = true;
								var subArr=[];
								subArr.push(result[i].distList[j].issueTypes[k].name);
								subArr.push(parseInt(result[i].distList[j].issueTypes[k].inviteeCount));
								mainArr.push(subArr);
							}
						}
					}
					if(isDataAvail == true){
						$('#districtWiseissues'+i+''+j+'').highcharts({
							colors:['#4C7BA7','#93B18B','#9F536F','#EF6072','#E65425','#D5AB59','#D8AB58','#495762'],
							chart: {
								type: 'column'
							},
							title: {
								text: null
							},
							
							xAxis: {
							 min: 0,
							 gridLineWidth: 0,
							 minorGridLineWidth: 0,
								type: 'category',
								labels: {
									enabled:false,
									rotation: -45,
									style: {
										fontSize: '13px',
										fontFamily: 'Verdana, sans-serif'
									}
								}
							},
							xAxis: {
							 min: 0,
							 gridLineWidth: 0,
							 minorGridLineWidth: 0,
								type: 'category',
								labels: {
									enabled:false,
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
								}
							},
							legend: {
								enabled: false
							},
							tooltip: {
								//headerFormat: '<span style="font-size:11px"></span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>'
							},
							series: [{
								//name: 'Count',
								colorByPoint: true,
								data: mainArr,
								dataLabels: {
									enabled: true,
									rotation: 1,
									color: '#FFFFFF',
									align: 'right',
									format: '{point.y}', // one decimal
									y: 10, // 10 pixels down from the top
									style: {
										fontSize: '13px',
										fontFamily: 'Verdana, sans-serif'
									}
								}
							}]
						});
						}else{
							$('#districtWiseissues'+i+''+j+'').html("<h4 style='transform:rotate(-50deg);'>NO DATA AVAILABLE</h4>")
							$('#districtWiseissues'+i+''+j+'').css("height","80px;");
							$('#districtWiseissues'+i+''+j+'').css("margin-top","70px")
						}
					}
				}
			}
			
			$(".ComparisionPartyWiseSlickApply").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false
		}); 
		
		}
	}
	
function getLocationWiseOverAllDetails(locationType,locationVal,divId){
	$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	
	var jsObj = { 
	  fromDate : fromDate,		//"10/01/2016",
	  toDate : toDate,		 	//"10/18/2016"  
	  locationType : locationType,
	  locationVal  : locationVal
	  
	}
	$.ajax({
		type : 'GET',
		url : 'getLocationWiseDetailedOverViewDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildLocationWiseDetails(result,locationType,divId);
		}
		else{
			$("#"+divId).html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
		}
	});
}

function buildLocationWiseDetails(result,locationType,divId){
	var str='';
	
	str+='<table class="table table-condensed b_1">';
		str+='<thead>';
			if(locationType == 'state')
				str+='<th>District</th>';
			else if(locationType == 'district')
				str+='<th>Constituency</th>';
			else if(locationType == 'constituency')
				str+='<th>Mandal/Muncipality</th>';
			else if(locationType == 'mandal')
				str+='<th>Village</th>';
			else if(locationType == 'muncipality')
				str+='<th>Ward</th>';
			str+='<th>Registered</th>';
			str+='<th>Data Verified</th>';
			str+='<th>Data Verification Pending</th>';
			str+='<th>Open Issues</th>';
			str+='<th>Fixed Issues</th>';
			str+='<th>Closed Issues</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				if(locationType == 'state'){
					str+='<td class="locationWiseCls" attr_locationVal="'+result[i].id+'" attr_locationType="district" attr_divId="districtWiseOverAllDetailsId'+result[i].id+''+i+'" attr_td_id="districtWiseOverAlltdId'+result[i].id+''+i+'" attr_location_name="'+result[i].name+'" attr_heading="districtNameid'+result[i].id+''+i+'"><a style="cursor:pointer;">'+result[i].name+'<a/></td>';
				}
				else if(locationType == 'district'){
					str+='<td class="locationWiseCls" attr_locationVal="'+result[i].id+'" attr_locationType="constituency" attr_divId="constituencyWiseOverAllDetailsId'+result[i].id+''+i+'" attr_td_id="constituencyWiseOverAlltdId'+result[i].id+''+i+'" attr_location_name="'+result[i].name+'" attr_heading="constNameid'+result[i].id+''+i+'"><a style="cursor:pointer;">'+result[i].name+'<a/></td>';
				}
				else if(locationType == 'constituency'){
					if(result[i].name.split(" ")[1] == 'Muncipality')
						str+='<td class="locationWiseCls" attr_locationVal="'+result[i].id+'" attr_locationType="muncipality" attr_divId="mandalWiseOverAllDetailsId'+result[i].id+''+i+'" attr_td_id="mandalWiseOverAlltdId'+result[i].id+''+i+'" attr_location_name="'+result[i].name+'" attr_heading="mandalNameid'+result[i].id+''+i+'"><a style="cursor:pointer;">'+result[i].name+'<a/></td>';
					else
						str+='<td class="locationWiseCls" attr_locationVal="'+result[i].id+'" attr_locationType="mandal" attr_divId="mandalWiseOverAllDetailsId'+result[i].id+''+i+'" attr_td_id="mandalWiseOverAlltdId'+result[i].id+''+i+'" attr_location_name="'+result[i].name+'" attr_heading="mandalNameid'+result[i].id+''+i+'"><a style="cursor:pointer;">'+result[i].name+'<a/></td>';
				}
				else if(locationType == 'mandal')
					str+='<td>'+result[i].name+'</td>';
				else if(locationType == 'muncipality')
					str+='<td>'+result[i].name+'</td>';
				
				str+='<td>'+result[i].count+'</td>';
				str+='<td>'+result[i].verifiedCount+'</td>';
				str+='<td>'+result[i].notVerifiedCount+'</td>';
				str+='<td>'+result[i].openIssues+'</td>';
				str+='<td>'+result[i].fixedIssues+'</td>';
				str+='<td>'+result[i].closedIssues+'</td>';
			str+='</tr>';
			if(locationType == 'state'){
				str+='<tr>';
					str+='<td class="locationtdCls" colspan="7" id="districtWiseOverAlltdId'+result[i].id+''+i+'" style="display:none;">';
						str+='<h4 class="panel-title text-capital"><span id="districtNameid'+result[i].id+''+i+'"></span>&nbsp;&nbsp;district - constituency wise detailed overview</h4>';
						str+='<i class="glyphicon glyphicon-remove pull-right removecls" style="cursor:pointer;"></i>';
						str+='<div class="table-responsive m_top20" id="districtWiseOverAllDetailsId'+result[i].id+''+i+'"></div>';
					str+='</td>';
				str+='</tr>';
			}
			else if(locationType == 'district'){
				str+='<tr>';
					str+='<td class="locationtdCls" colspan="7" id="constituencyWiseOverAlltdId'+result[i].id+''+i+'" style="display:none;">';
						str+='<h4 class="panel-title text-capital"><span id="constNameid'+result[i].id+''+i+'"></span>&nbsp;&nbsp;constituency - mandal/muncipality wise detailed overview</h4>';
						str+='<i class="glyphicon glyphicon-remove pull-right removecls" style="cursor:pointer;"></i>';
						str+='<div class="table-responsive m_top20" id="constituencyWiseOverAllDetailsId'+result[i].id+''+i+'">';
						str+='</div>';
					str+='</td>';
				str+='</tr>';
			}
			else if(locationType == 'constituency'){
				str+='<tr>';
					str+='<td class="locationtdCls" colspan="7" id="mandalWiseOverAlltdId'+result[i].id+''+i+'" style="display:none;">';
						str+='<h4 class="panel-title text-capital"><span id="mandalNameid'+result[i].id+''+i+'"></span>&nbsp;&nbsp; - village/ward wise detailed overview</h4>';
						str+='<i class="glyphicon glyphicon-remove pull-right removecls" style="cursor:pointer;"></i>';
						str+='<div class="table-responsive m_top20" id="mandalWiseOverAllDetailsId'+result[i].id+''+i+'">';
						str+='</div>';
					str+='</td>';
				str+='</tr>';
			}
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#"+divId).html(str);
}

$(document).on("click",".locationWiseCls",function(){
	var locationType = $(this).attr("attr_locationType");
	var locationVal = $(this).attr("attr_locationVal");
	var tdId = $(this).attr("attr_td_id");
	var divId = $(this).attr("attr_divId");
	var locationName = $(this).attr("attr_location_name");
	var headingId = $(this).attr("attr_heading");
	
	$("#"+headingId).html(locationName);
	$("#"+tdId).show();
	getLocationWiseOverAllDetails(locationType,locationVal,divId);
});

$(document).on("click",".removecls",function(){
	var divId = $(this).parent().attr("id");
	$("#"+divId).hide();
});

