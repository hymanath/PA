	
	function onLoadCalls(){
		getOverAllDataCollectorsCounts(1);
		getIssueStatusWiseCounts(1);
		getIssueTypeWiseCounts(1);
		getDistricts();
		getConstituencies(0);
		getApMandalMuncipalityNotStartedCount(1);
	}
	var totalDataCollectors = 0;
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
          stateId :state,	
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
				$("#todayActId").html(result.todayActiveUsers);
				$("#lastOneHrId").html(result.lastOneHrActUsers);
				$("#passOneHrId").html(result.passiveUsers);
				$("#notYetStartedId").html(result.notYetStartedUsers);
				totalDataCollectors = result.totalDataCollectors;
			}	
		});
	}
	function getIssueStatusWiseCounts(state){
			$("#statusCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			var dates = $(".singleDate").val();
	        var dateArr = dates.split("-");
	        var fromDate;
	        var toDate;
	      if(dateArr != null){
		    fromDate = dateArr[0];
		    toDate = dateArr[1];
	       }

			var jsObj = { 
			              stateId : state,
			              fromDate : fromDate,    //"2016-10-01",
		                  toDate : toDate,			//"2016-10-18"
						  task   : "fieldMonitoringDashboard"						  
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
			str +='<div class="row">';
			str +='<div class="col-md-8 col-xs-12 col-sm-10">';
			str +='<ul class="dashedB">';
			for(var i in result){
			   str +='<li>'+result[i].name+' issues<span><a attr_id='+result[i].id+' attr_statusStr="'+result[i].name+' Issues"  attr_count='+result[i].inviteeCount+' class="issueTypeMonitoringCls" style="cursor:pointer;">'+result[i].inviteeCount+'</a></span></li>';
			}
			str +='</ul>';
			str +='</div>';
			str +='</div>';
			$("#statusCountDivId").html(str);
		
		}	
		
	$(document).on("click",".issueTypeMonitoringCls",function(){		
		var issueStatusId = $(this).attr("attr_id");
		var statusStr = $(this).attr("attr_statusStr");
		var count = $(this).attr("attr_count");
		getStatusWiseIssuesDetails(statusStr,issueStatusId,count,"issueStatus");		
	});
		
		
	function getIssueTypeWiseCounts(state){
		var openIssuesArr = [];
		var fixedIssuesArr = [];
		var closedIssuesArr  = [];
		
			var dates = $(".singleDate").val();
	        var dateArr = dates.split("-");
	        var fromDate;
	        var toDate;
	   if(dateArr != null){
		     fromDate = dateArr[0];
		     toDate = dateArr[1];
	       }
			var jsObj = { 
			    stateId :state,
				fromDate : fromDate, //"10/01/2016",
				toDate : toDate      //"10/20/2016",
			}
			$.ajax({
				type : 'GET',
				url : 'getIssueTypeWiseCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
				if(result != null && result.length >0){
					var totalIssuesCount = result.length;
					for(var i in result){
						if(result[i].issueTypes != null && result[i].issueTypes.length > 0){
							var openIssueCount =0;
							if(result[i].id == 1){
								for(var j in result[i].issueTypes){
										openIssueCount = openIssueCount+result[i].issueTypes[j].inviteeCount;
									 $("#openIssuesCountId span").text("-"  +openIssueCount);
									var dataArr = [];
									dataArr.push(result[i].issueTypes[j].name+"-"+parseInt(result[i].issueTypes[j].id));
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
									dataArr.push(result[i].issueTypes[j].name+"-"+parseInt(result[i].issueTypes[j].id));
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
									dataArr.push(result[i].issueTypes[j].name+"-"+parseInt(result[i].issueTypes[j].id));
									dataArr.push(parseInt(result[i].issueTypes[j].inviteeCount));
									closedIssuesArr.push(dataArr);
								}
							}
						}
					}
					$("#openIssues").css("height","(totalIssuesCount*40)px");
					 $('#openIssues').highcharts({
						chart: {
							type: 'pie',
							options3d: {
								enabled: true,
								alpha: 45
							},
							spacingTop: -(totalIssuesCount*50),
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
									getStatusWiseIssuesDetails(this.name,1,this.y);
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
								distance: -50,
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
				    $("#fixedIssues").css("height","(totalIssuesCount*40)px");
					$('#fixedIssues').highcharts({
						chart: {
							type: 'pie',
							options3d: {
								enabled: true,
								alpha: 45
							},
							spacingTop: -(totalIssuesCount*50),
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
						  symbolPadding: 10,
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
									getStatusWiseIssuesDetails(this.name,2,this.y);
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
					$("#closedIssues").css("height","(totalIssuesCount*40)px");
					$('#closedIssues').highcharts({
						chart: {
							type: 'pie',
							options3d: {
								enabled: true,
								alpha: 45
							},
							spacingTop: -(totalIssuesCount*50),
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
									getStatusWiseIssuesDetails(this.name,3,this.y);
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
		
function getStatusWiseIssuesDetails(issueTypeStr,issueStatus,count,type){
	$(".districtDiv").hide();
	$("#dataCollectorsDiv").hide();
	$("#dataCollectorsDivId").html('');
	$("#dataCollectorsImgId").hide();
	$("#statusWiseDetailsDivId").html('');
	$("#statusWiseDetailsImgId").show();
	$("#dtatusDivId").show();

	var issueType = 0;
	if(type != null && type != undefined && type.trim() =="issueStatus"){
		$("#issueTypeHeadingId").html(issueTypeStr+" - "+count);
		issueType=0;
	}else{
		$("#issueTypeHeadingId").html(issueTypeStr.split("-")[0]+" - "+count);
		issueType = issueTypeStr.split("-")[1];
	}
	
	$("#hiddenIssueStatusId").val(issueStatus);
	
	
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
	var jsObj = { 
	  fromDate : fromDate,    //"10/18/2016",
	  toDate : toDate,  		  //"10/20/2016",		
	  issueType : issueType,
	  issueStatus : issueStatus,
	  stateId  : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getStatusWiseIssuesDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildStatusWiseDetails(result,issueType);
		}
		else{
			$("#statusWiseDetailsImgId").hide();
			$("#statusWiseDetailsDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
		}
	});
	$.ajax({
		type : 'GET',
		url : 'getConstituencyIssueWiseOverAllDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildLocationWiseOverAllDetails(result,issueType);
		}
		else{
			//$("#statusWiseDetailsImgId").hide();
			$("#statusWiseDetailsOverAllDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
		}
	});
}

function buildStatusWiseDetails(result,issueTypeId){
	var str = '';
	
	str+='<span class="btn btn-info excelId form-inline pull-right btn-sm btn-xs" style="float:left;margin-top: 10px" onclick="exportToExcel(\'issueStatusTableId\')"><b> Export To Excel </b></span>';
	str+='<span class="btn btn-success excelId form-inline pull-right btn-sm btn-xs" style="float:left;margin-top: 10px;margin-right: 10px;" onclick="exportToExcel(\'locationWiseTableId\')"><b> Over All Export To Excel </b></span>';
	
	str+='<table class="table b_1" id="issueStatusTableId">';
		str+='<thead class="text-capitalize">';
			//str+='<th>User Id</th>';
			str+='<th>state</th>';
			str+='<th>district</th>';
			str+='<th>constituency</th>';
			str+='<th>User Id</th>';
			str+='<th>user name</th>';
			str+='<th>user contact number</th>';
			str+='<th>First Record</th>';
			str+='<th>Last Record</th>';
			str+='<th>Last Hour</th>';
			str+='<th>Total Registrations</th>';
			//str+='<th>leader name</th>';
			str+='<th>open issues</th>';
			str+='<th>fixed issues</th>';
			str+='<th>closed issues</th>';
			if(issueTypeId == 1){
			str+='<th>Leader Name</th>';
			str+='<th>Mobile</th>';
			str+='<th>Location</th>';
			}
			str+='<th></th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				//str+='<td>'+result[i].userName+'</td>';
				if(result[i].stateName != null)
					str+='<td>'+result[i].stateName+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].districtName != null)
					str+='<td>'+result[i].districtName+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].constituencyName != null)
					str+='<td>'+result[i].constituencyName+'</td>';
				else
					str+='<td> - </td>';
				str+='<td>'+result[i].userName+'</td>';
				str+='<td>'+result[i].tabUserName+'</td>';
				str+='<td>'+result[i].mobileNo+'</td>';
				
				if(result[i].firstRecord != null)
					str+='<td>'+result[i].firstRecord+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].recentRecord != null)
					str+='<td>'+result[i].recentRecord+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].lastHourCount != null)
					str+='<td>'+result[i].lastHourCount+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].totalCount != null)
					str+='<td>'+result[i].totalCount+'</td>';
				else
					str+='<td> - </td>';
				//str+='<td> - </td>';
				if(result[i].openIssues != null)
					str+='<td>'+result[i].openIssues+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].fixedIssues != null)
					str+='<td>'+result[i].fixedIssues+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].closedIssues != null)
					str+='<td>'+result[i].closedIssues+'</td>';
				else
					str+='<td> - </td>';
				if(issueTypeId == 1){
				if(result[i].leaderName != "" && result[i].leaderName != null)
					str+='<td>'+result[i].leaderName+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].leadreMobile != "" && result[i].leadreMobile != null)
					str+='<td>'+result[i].leadreMobile+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].leaderMandal != "" && result[i].leaderMandal != null)
					str+='<td>'+result[i].leaderMandal+'</td>';
				else
					str+='<td> - </td>';
				}
				str+='<td><i class="glyphicon glyphicon-cog issuesBtn" attr_cadre_survey_user_id="'+result[i].cadreSurveyUserId+'" attr_tab_user_info_id="'+result[i].tabUserId+'" attr_cadre_survey_userName="'+result[i].userName+'" attr_tab_userName="'+result[i].tabUserName+'" attr_vendor_Id ="'+result[i].vendorId+'" attr_constistuency_Id = "'+result[i].constituencyId+'" attr_mobileNo="'+result[i].mobileNo+'" title="Click Here to Manage Issues" style="cursor:pointer;"></i></td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#statusWiseDetailsImgId").hide();
	$("#statusWiseDetailsDivId").html(str);
	$("#issueStatusTableId").dataTable({
			"aaSorting": [[ 0, "asc" ]],
	         "iDisplayLength": 20,
	         "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
	});
	$('html,body').animate({scrollTop: $("#statusWiseDetailsDivId").offset().top}, 'slow');
}

function buildLocationWiseOverAllDetails(result,issueTypeId){
	var str = '';
	
	str+='<table class="table b_1" id="locationWiseTableId">';
		str+='<thead class="text-capitalize">';
			str+='<th>state</th>';
			str+='<th>district</th>';
			str+='<th>No.Of Issues</th>';
			str+='<th>constituency</th>';
			str+='<th>No.Of Issues</th>';
			str+='<th>FM user</th>';
			str+='<th>User Id</th>';
			str+='<th>user name</th>';
			str+='<th>user contact number</th>';
			str+='<th>Issue Type</th>';
			str+='<th>Issue Status</th>';
			str+='<th>Description</th>';
			str+='<th>Inserted Time</th>';
			if(issueTypeId == 1){
			str+='<th>Leader Name</th>';
			str+='<th>Mobile</th>';
			str+='<th>Location</th>';
			}
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			if(result[i].districtCount != null && result[i].districtCount > 0){
				str+='<tr>';
					if(result[i].stateName != null)
						str+='<td>'+result[i].stateName+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].districtName != null)
						str+='<td>'+result[i].districtName+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].districtCount != null)
						str+='<td>'+result[i].districtCount+'</td>';
					else
						str+='<td> - </td>';
				str+='</tr>';
			}
			if(result[i].constituencyCount != null && result[i].constituencyCount > 0){
				str+='<tr>';
					if(result[i].stateName != null)
						str+='<td>'+result[i].stateName+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].districtName != null)
						str+='<td>'+result[i].districtName+'</td>';
					else
						str+='<td> - </td>';
					/*if(result[i].districtCount != null)
						str+='<td>'+result[i].districtCount+'</td>';
					else*/
						str+='<td> - </td>';
					if(result[i].constituencyName != null)
						str+='<td>'+result[i].constituencyName+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].constituencyCount != null)
						str+='<td>'+result[i].constituencyCount+'</td>';
					else
						str+='<td> - </td>';
				str+='</tr>';
			}
			str+='<tr>';
				if(result[i].stateName != null)
					str+='<td>'+result[i].stateName+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].districtName != null)
					str+='<td>'+result[i].districtName+'</td>';
				else
					str+='<td> - </td>';
				/*if(result[i].districtCount != null)
					str+='<td>'+result[i].districtCount+'</td>';
				else*/
					str+='<td> - </td>';
				if(result[i].constituencyName != null)
					str+='<td>'+result[i].constituencyName+'</td>';
				else
					str+='<td> - </td>';
				/*if(result[i].constituencyCount != null)
					str+='<td>'+result[i].constituencyCount+'</td>';
				else*/
					str+='<td> - </td>';
				str+='<td>'+result[i].fieldMonitrngName+'</td>';
				str+='<td>'+result[i].userName+'</td>';
				str+='<td>'+result[i].tabUserName+'</td>';
				str+='<td>'+result[i].mobileNo+'</td>';
				str+='<td>'+result[i].issueType+'</td>';
				str+='<td>'+result[i].issueStatus+'</td>';
				str+='<td>'+result[i].description+'</td>';
				str+='<td>'+result[i].issueTime+'</td>';
				if(issueTypeId == 1){
				if(result[i].leaderName != null)
					str+='<td>'+result[i].leaderName+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].leadreMobile != null)
					str+='<td>'+result[i].leadreMobile+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].leaderMandal != null)
					str+='<td>'+result[i].leaderMandal+'</td>';
				else
					str+='<td> - </td>';
				}
				/*if(result[i].openIssues != null)
					str+='<td>'+result[i].openIssues+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].fixedIssues != null)
					str+='<td>'+result[i].fixedIssues+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].closedIssues != null)
					str+='<td>'+result[i].closedIssues+'</td>';
				else
					str+='<td> - </td>';*/
				//str+='<td><i class="glyphicon glyphicon-cog issuesBtn" attr_cadre_survey_user_id="'+result[i].cadreSurveyUserId+'" attr_tab_user_info_id="'+result[i].tabUserId+'" attr_cadre_survey_userName="'+result[i].userName+'" attr_tab_userName="'+result[i].tabUserName+'" attr_vendor_Id ="'+result[i].vendorId+'" attr_constistuency_Id = "'+result[i].constituencyId+'" attr_mobileNo="'+result[i].mobileNo+'" title="Click Here to Manage Issues" style="cursor:pointer;"></i></td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#statusWiseDetailsOverAllDivId").html(str);
	//$("#issueStatusTableId").dataTable();
	//$('html,body').animate({scrollTop: $("#statusWiseDetailsDivId").offset().top}, 'slow');
}

$(document).on("click",".issuesBtn",function(){
    clearErrorFields();	
    $("#issueTypeDivId").hide();
    var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
    var tabUserInfoId = $(this).attr("attr_tab_user_info_id");
	var cadreSurveyUserName = $(this).attr("attr_cadre_survey_userName");
	var tabUserName = $(this).attr("attr_tab_userName");
	var mobileNo = $(this).attr("attr_mobileNo");
	var vendorId =$(this).attr("attr_vendor_Id");
    var constituencyId =$(this).attr("attr_constistuency_Id");
	
	$("#submitId").attr("attr_cadre_survey_user_id",cadreSurveyUserId);
    $("#submitId").attr("attr_tab_user_info_id",tabUserInfoId);
	
	$(".modalCadreUserName").html(cadreSurveyUserName);
	$(".tabUserMblDetailsId").html(tabUserName+" - "+mobileNo);
	$("#hiddenCadreSurveyUserId").val(cadreSurveyUserId);
	$("#hiddenTabUserInfoId").val(tabUserInfoId);
	$("#hiddenVendorId").val(vendorId);
	$("#hiddenConstituencyId").val(constituencyId);
	var issueStatus = $("#hiddenIssueStatusId").val();
	
	getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,issueStatus);
	getIssuesCountsForATabUser(cadreSurveyUserId,tabUserInfoId);
	//getConstituencyByVendor();
    $("#issuesModal").modal('show');
  });
  function getIssuesCountsForATabUser(cadreSurveyUserId,tabUserInfoId){
	   $("#openIssuesId").html(0);
	   $("#fixedIssuesId").html(0);
	   $("#closedIssuesId").html(0);
	   $("#totalIssuesId").html(0);
	   
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
	 var jsObj =
     {				
		cadreSurveyUserId : cadreSurveyUserId,
		tabUserInfoId : tabUserInfoId,
		fromDate : fromDate,   
		toDate : toDate,
		stateId : stateId
	}
    $.ajax({
          type:'GET',
          url: 'getIssuesCountsForATabUserNewAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   for(var i in result){
			   if(result[i].issueStatusId != null && result[i].issueStatusId == 1)
				   $("#openIssuesId").html(result[i].count);
			   else if(result[i].issueStatusId != null && result[i].issueStatusId == 2)
				   $("#fixedIssuesId").html(result[i].count);
			   else if(result[i].issueStatusId != null && result[i].issueStatusId == 3)
				   $("#closedIssuesId").html(result[i].count);
			   else
				  $("#totalIssuesId").html(result[i].count);
			}
		}
   });
  }
  
$(document).on("click",".issueTypeCls",function(){
	var issueType = $(this).attr("attr_val");
	var cadreUserId = $("#hiddenCadreSurveyUserId").val();
	var tabUserId = $("#hiddenTabUserInfoId").val();
	
	getIssuesForATabUserByStatus(cadreUserId,tabUserId,issueType);
});

   $(document).on("click","#addNewIssueId",function(){ 
	 $("#issueTypeDivId").show();
	 $("#leaderIssueDivId").hide();
   });
   function getCadreRegIssueType(){
	 
    $.ajax({
          type:'GET',
          url: 'getCadreRegIssueTypeAction.action',
          dataType: 'json',
		  data: {}
   }).done(function(result){
	   $("#issueTypeId").append('<option value="0"> Select IssueType</option>');
     for(var i in result){
	      $("#issueTypeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
	 $("#issueTypeId").trigger("chosen:updated");
   });
  }
  function getConstituencyByVendor(){
	  clearConstituencies('issueConstituencyId');
	  var fieldVendorId = $("#hiddenVendorId").val();
	  var jsObj = { fieldVendorId : fieldVendorId }
	 
    $.ajax({
          type:'GET',
          url: 'getConstituencyByVendorAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
       for(var i in result){
	      $("#issueConstituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 $("#issueConstituencyId").trigger("chosen:updated");
   });
  }
  function clearConstituencies(selectBoxId){
			$('#'+selectBoxId).find('option').remove();
            $('#'+selectBoxId).append('<option value="0">Select Constituency</option>');
			refreshSelectBox(selectBoxId);
		}
  function refreshSelectBox(selectBoxId){
			$("#"+selectBoxId).trigger("chosen:updated");
		}
function getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,issueStatusId){
		$("#issueDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	 
		$("#hiddenIssueStatusId").val(issueStatusId);
		
		var constituencyId = $("#hiddenConstituencyId").val();
		var dates = $(".singleDate").val();
		var dateArr = dates.split("-");
		var fromDate;
		var toDate;
		if(dateArr != null){
			fromDate = dateArr[0];
			toDate = dateArr[1];
		}
		var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
	
	 var jsObj =
     {				
		cadreSurveyUserId : cadreSurveyUserId,
		tabUserInfoId : tabUserInfoId,
		fromDate : fromDate,   
		toDate : toDate,
        issueStatusId : issueStatusId,
		stateId  : stateId
	 }
    $.ajax({
          type:'GET',
          url: 'getIssuesForATabUserByStatusNewAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0)
		buildIssuesForATabUserByStatus(result);
		else
			$("#issueDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
   });
  }
  function buildIssuesForATabUserByStatus(result) {	
  
	var str = '';

		str+='<table id="datatableId" class="table table-condensed">';
		str+='<thead>';
		str+='<th></th>';
		str+='</thead>';
		str+='<tbody>';
		for( var i in result) {
		str+='<tr>';
		str+='<td>';
		str += '<h5 class="text-capital"><b>Issue Type :</b> '+ result[i].issueType	+ '</h5>';
			str+='<button class="btn btn-success editBtn pull-right btn-sm" attr_value="'+i+'" attr_issueStatus="'+result[i].issueStatus+'">edit</button>';
			str+='<button class="btn btn-success pull-right btn-sm trackingIssueCls" type="button" attr_cadre_reg_issue_id="'+result[i].cadreRegIssueId+'" style="margin-right: 10px;">ISSUE TRACK</button>';
			str +='<div class="descriptionCls">';
                //str+='<h4> Description </h4>';			
				str += '<p><b>Description : </b><span  class="issueDesc'+i+'">' + result[i].description + '</span></p>';
				str += '<p class="m_top10">';
				if(result[i].issueStatus == 'open')
				{
				str += '<span class="text-danger"><i>Issue Status :<span class="statusUpdate'+i+'">'
						+ result[i].issueStatus + '</span></i></span>';
				}else{
				str += '<span class="text-success"><i>Issue Status :<span class="statusUpdate'+i+'">'
						+ result[i].issueStatus + '-'+ result[i].updatedTime +'</span></i></span>';
				}
				str += '<span class="pull-right text-muted"><i>Informed Time:<span class="updatedTime'+i+'">'
						+ result[i].dateStr + '</span></i></span>';
				str += '</p>';
			str +='</div>';		
			str += '<div class="row descriptionEditCls" style="display:none;">';
				str += '<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str += '<textarea class="form-control issueDescEdit'+i+'" cols="120"></textarea>';
				str += '</div>';
				str += '<div class="col-md-4 col-xs-12 col-sm-4">';
					str += '<label>Change Issue Status</label>';
					str += '<select class="select" id="changeIssueStatusId'+i+'">';
					str += '</select>';
				str += '</div>';
				str += '<div class="col-md-4 col-xs-12 col-sm-4">';
				str+='<div class="row">'
					str +='<button class="btn btn-success m_top20 updateCls"  attr_value="'+i+'" attr_cadre_reg_issue_id="'+result[i].cadreRegIssueId+'">UPDATE</button>';
					str +='<button class="btn btn-default cancelUpdate m_top20 cancelCls" style="margin-left:10px;">CANCEL</button>';
					str+='</div>'
					str +='<span id="updateDivIdImg'+i+'" style="display:none;"><img src="images/search.gif"/></span>';
				    str +='<div id="updateStatusId'+i+'"></div>';
					
				str += '</div>';
			str += '</div>';
		str+='</td>';
		str+='</tr>';
		
		}
		str+='</tbody>';
		str+='</table>';
	
	$("#issueDivId").html(str);
	
	 $('#datatableId').dataTable({
        "aaSorting": []
    }); 
	
  }
   $(document).on("click","#submitId",function(){ 
      saveFieldIssue();
  });
  function saveFieldIssue(){  
	  var constituencyId =$("#hiddenConstituencyId").val();  
	  var issueTypeId =$("#issueTypeId option:selected").val();
      var description =$("#descriptionId").val();
      var cadreSurveyUserId= $("#submitId").attr("attr_cadre_survey_user_id");
	  var tabUserInfoId= $("#submitId").attr("attr_tab_user_info_id");
	   var mandalId = $("#mndlOrmunpltyId").val();
	  var nameId = $("#fmNameId").val();
	  var mobileNumber = $("#fmMbNoId").val();
	  //var issueStsId = $("#hiddenIssueStatusId").val();
	  if(issueTypeId == 0)
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Select Issue Type</span>");
		   return;
	   }else{
		    $("#submitButId").html("");
	   }
	   if(description.trim() == '' && description.length == 0)
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Enter description</span>");
		   return;
	   }else{
		    $("#submitButId").html("");
	   }
	   if(constituencyId == 0)       
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Select Constituency</span>");
		   return;
	   }else{
		    $("#submitButId").html("");
	   }
	   if(mandalId == ""){
		   mandalId =="";
	   }
	   if(nameId == ""){
		   nameId == "";
	   }
	   if(mobileNumber == ""){
		   mobileNumber == "";
	   }
	  $("#savingDivIdImg").show();
	 var jsObj=
     {				
		issueTypeId :issueTypeId,
		cadreSurveyUserId : cadreSurveyUserId,
		tabUserInfoId :tabUserInfoId,
		description :description,
		constituencyId : constituencyId,
		mandal : mandalId,
		name : nameId,
		mobileNumber : mobileNumber
	 }
    $.ajax({
          type:'GET',
          url: 'saveFieldIssueAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    $("#savingDivIdImg").hide();
	   if(result.message == 'success' && result.resultCode == 1)
	   {
		    $("#submitButId").html("<span style='color: green;font-size:18px;'> Saved Successfully...</span>");
			setTimeout(function(){
				$("#issueTypeDivId").hide();
				clearErrorFields();
				getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,0);
				getIssuesCountsForATabUser(cadreSurveyUserId,tabUserInfoId);
				//$("#issuesModal").modal('hide');
                //getStatusWiseIssuesDetails(issueTypeStr,issueStatus,count);
			}, 2000);
	   }else{
		   $("#submitButId").html("<span style='color: red;font-size:18px;'>Saved Failed.Please try Again.</span>");
		   setTimeout(function(){
				$("#issueTypeDivId").hide();
				clearErrorFields();
			}, 2000);
	   }
   });
  }
  function clearErrorFields()
  {        
	$("#issueTypeId").val(0).trigger('chosen:updated');
    $("#descriptionId").val('');	
	$("#issueConstituencyId").val(0).trigger('chosen:updated');
	$("#submitButId").html('');
	$("#savingDivIdImg").hide();
  }
  $(document).on("click",".updateCls",function(){
	var value = $(this).attr("attr_value");
	var cadreRegIssueId = $(this).attr("attr_cadre_reg_issue_id");
	var description = $(".issueDescEdit"+value).val();
	var  newStatusId = $('#changeIssueStatusId'+value).val();
	
	updateStatusToACadreRegIssue(value,cadreRegIssueId,description,newStatusId);
 });
  function updateStatusToACadreRegIssue(value,cadreRegIssueId,description,newStatusId){
	   var cadreSurveyUserId= $("#submitId").attr("attr_cadre_survey_user_id");
	   $(".issueDesc"+value).text(description);
	   var subValue = $("#changeIssueStatusId"+value+" option:selected").text();
	   $(".statusUpdate"+value).text(subValue);
	   
	   var CadreSrvId = $("#hiddenCadreSurveyUserId").val();
	   var tabUserId = $("#hiddenTabUserInfoId").val();
	   var issueStsId = $("#hiddenIssueStatusId").val();
	   
		if(description.trim() == '' && description.length == 0)
		{
			 $("#updateStatusId"+value).html("<span style='color: red;font-size:12px;'> Enter description</span>");
			 return;
		}else
		{
			$("#updateStatusId"+value).html('');
		}
		if(newStatusId == 0)
		{
			$("#updateStatusId"+value).html("<span style='color: red;font-size:12px;'> Select IssueStatus</span>");
			return;
		}else
		{
			$("#updateStatusId"+value).html('');
		}
		$("#updateDivIdImg"+value).show();
		
	   var jsObj =
      {				
		cadreRegIssueId :cadreRegIssueId,
		description : description,
		newStatusId : newStatusId
	  }
	   $.ajax({
          type:'POST',
          url: 'updateStatusToACadreRegIssueAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		    $("#updateDivIdImg"+value).hide();
		   if(result.message == 'success' && result.resultCode == 1)
		   {
			   $("#updateStatusId"+value).html("<span style='color: green;font-size:18px;'> update Successfully...</span>");
			   setTimeout(function(){
			   $(".editBtn").closest("td").find(".descriptionCls").show();
	           $(".editBtn").closest("td").find(".descriptionEditCls").hide();
			    $(".editBtn").closest("td").find(".trackingIssueCls").show();	
				getIssuesForATabUserByStatus(CadreSrvId,tabUserId,issueStsId);
				getIssuesCountsForATabUser(CadreSrvId,tabUserId);
			}, 2000);
		   }else{
			    $("#updateStatusId").html("<span style='color: red;font-size:18px;'> update Failed.Please try Again..</span>");
		   }
		  var presentTime = moment().format("YYYY-MM-DD hh:mm A");
		  $(".updatedTime"+value).html(presentTime)
		  
	   });
  }
  //trackingRegIssueByRegIssueId();
  function trackingRegIssueByRegIssueId(value,cadreRegIssueId){
	  $("#issueTrackingBodyId").html("");
	  $("#issueTrackingModal").modal("show");
	  $("#issueTrackingImgId").show();
	  var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
	   var jsObj = { cadreRegIssueId :cadreRegIssueId,stateId : stateId}
	  
	   $.ajax({
          type:'POST',
          url: 'trackingRegIssueByRegIssueIdAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null && result.length > 0)
			   buildIssueTrackingDetails(result);
			else{
				 $("#issueTrackingImgId").hide();
				 $("#issueTrackingBodyId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
			}
	   });
  }
  
  function buildIssueTrackingDetails(result){
	  var str = '';
	  
	  str+='<table class="table b_1 m_top10" id="detailsTable">';
			str+='<thead class="text-capitalize">';
				str+='<th>Issue Type</th>';
				str+='<th>Description</th>';
				str+='<th>Status</th>';
				str+='<th>UserName</th>';
				str+='<th>Updated Time</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].issueType+'</td>';
					str+='<td>'+result[i].description+'</td>';
					str+='<td>'+result[i].issueStatus+'</td>';
					str+='<td>'+result[i].userName+'</td>';
					str+='<td>'+result[i].dateStr+'</td>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		$("#issueTrackingImgId").hide();
		$("#issueTrackingBodyId").html(str);
  }
  $(document).on("click",".trackingIssueCls",function(){
	var value = $(this).attr("attr_value");
	var cadreRegIssueId = $(this).attr("attr_cadre_reg_issue_id");
	trackingRegIssueByRegIssueId(value,cadreRegIssueId);
});	
  $(document).on("click",".cancelCls",function(){
	 $(this).closest("td").find(".trackingIssueCls").show();	
 });
  $(document).on("click",".editBtn",function(){
	var value = $(this).attr("attr_value");
	var issueStatus = $(this).attr("attr_issueStatus");
	$(this).closest("td").find(".descriptionCls").hide();
	$(this).closest("td").find(".descriptionEditCls").show();
    $(this).closest("td").find(".trackingIssueCls").show();	
	getCadreRegIssueStatusType(value,issueStatus);
	var desc = $(".issueDesc"+value).text();          
	//$(".issueDescEdit"+value).val(desc);
	$("#updateStatusId"+value).html('');

});
 function getCadreRegIssueStatusType(value,issueStatus){
	 $("#changeIssueStatusId"+value).empty('').trigger('chosen:updated');
	$("#changeIssueStatusId"+value).chosen({width:'100%'});
    $.ajax({
          type:'GET',
          url: 'getCadreRegIssueStatusTypeAction.action',
          dataType: 'json',
		  data: {}
   }).done(function(result){
	   $("#changeIssueStatusId"+value).append('<option value="0"> Select ChangeIssueStatus</option>');
     for(var i in result){
		  if(result[i].name == issueStatus)
		  {
	      $("#changeIssueStatusId"+value).append('<option selected value='+result[i].id+' attr_text="'+result[i].name+'">'+result[i].name+'</option>');
		  }else{
			  $("#changeIssueStatusId"+value).append('<option value='+result[i].id+' attr_text="'+result[i].name+'">'+result[i].name+'</option>');
		  }
	 }
	 $("#changeIssueStatusId"+value).trigger("chosen:updated");
   });
  }

  function getDistricts(){
  $(".districtDiv").show();
  var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
	$('#districtId').find('option').remove();
	var jsObj = { 
	stateId : 1,
	stateTypeId : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictByStateIdAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#districtId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#districtId").trigger("chosen:updated");		
	});
}

function getConstituencies(districtId){
  $(".districtDiv").show();
  var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
	$('#constituencyId').find('option').remove();
	var jsObj = { 
	stateId : 1,
	stateTypeId : stateId,
	districtId : districtId
	}
	$.ajax({
		type : 'GET',
		url : 'getConstituenciesByStateForStateTypeIdAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#constituencyId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#constituencyId").trigger("chosen:updated");		
	});
}
  var globalDataCollectorsAscendingArr;
  var globalDataCollectorsDecendingArr;
  function getDataCollectorsPerformanceDetails(){
  $(".districtDiv").show();
  var dates = $(".singleDate").val();
		var dateArr = dates.split("-");
		var fromDate;
		var toDate;
		if(dateArr != null){
			fromDate = dateArr[0];
			toDate = dateArr[1];
		}
	 var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
	$("#statusWiseDetailsDivId").html('');
	$("#statusWiseDetailsImgId").hide();
	$("#dtatusDivId").hide();
	$("#dataCollectorsDiv").show();
	$("#dataCollectorsDivId").html('');
	$("#dataCollectorsImgId").show();
	$("#issueTypeHeadingId").html("");
	
	$('html,body').animate({scrollTop: $("#dataCollectorsDivId").offset().top}, 'slow');
	
	 var jsObj=
     {				
		stateId : stateId,
		districtId : $("#districtId").val(),
		constituencyId : $("#constituencyId").val(),
		cadreSurveyUserId : 0,
		startDate :fromDate,
		endDate :toDate
	 }
    $.ajax({
          type:'GET',
          url: 'getDataCollectorsPerformanceDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  if(result != null){
		  buildDataCollectorsPerformanceDetails(result.subList);
		}
		movingDataToArrays(result.subList);
   });
}

function exportToExcel(constiTableId)
{
tableToExcel(''+constiTableId+'', 'Constituency Wise Committees');
	
}

var tableToExcel = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()

function buildDataCollectorsPerformanceDetails(result){

	if(result != null && result.length > 0){
		var str = '';
		
        //str+='<h4 class="text-capital">total data collectors - <span id="totalDataCollectorsId">'+result.length+'</span></h4>';
        $("#totalDataCollectorsId span").text("-"  +result.length);
		str+='<span class="btn btn-info excelId form-inline pull-right btn-sm btn-xs" style="float:left;margin-top: 10px" onclick="exportToExcel(\'detailsTable\')"><b> Export To Excel </b></span>';
		str+='<table class="table b_1 m_top10 " id="detailsTable">';
			str+='<thead class="text-capitalize">';
				str+='<th>district</th>';
				str+='<th>constituency</th>';
				str+='<th>User Id</th>';
				str+='<th>DC Name</th>';
				str+='<th>DC Contact number</th>';
				str+='<th>FM User Id</th>';
				str+='<th>first record</th>';
				str+='<th>recent record</th>';
				str+='<th>last hour</th>';
				str+='<th>Completed Registrations</th>';
				//str+='<th>Is Slow Performer?</th>';
				//str+='<th>today target</th>';
				str+='<th>open issues</th>';
				str+='<th>fixed issues</th>';
				str+='<th>closed issues</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				if(result[i].slowPerformer != null && result[i].slowPerformer == 'true' && result[i].betterPerformer != null && result[i].betterPerformer == 'true')
					str+='<tr style="background: lightblue;">';
				else if(result[i].slowPerformer != null && result[i].slowPerformer == 'true')
					str+='<tr style="background: lightpink;">';
				else if(result[i].betterPerformer != null && result[i].betterPerformer == 'true')
					str+='<tr style="background: lightgreen;">';
				else
					str+='<tr>';
				
					if(result[i].lastHourCount != null && result[i].lastHourCount > 0){
						if(result[i].districtName != null)
							str+='<td class="issueCmpltd">'+result[i].districtName+'</td>';
						else
							str+='<td> - </td>';
					}
					else{
						if(result[i].districtName != null)
							str+='<td class="issuePending">'+result[i].districtName+'</td>';
						else
							str+='<td> - </td>';
					}
					if(result[i].constituencyName != null)
						str+='<td>'+result[i].constituencyName+'</td>';
					else
						str+='<td> - </td>';
					/*if(result[i].lastHourCount != null && result[i].lastHourCount > 0)
						str+='<td class="issueCmpltd" title="UserId : '+result[i].userName+'" id="'+result[i].tabUserId+'">'+result[i].tabUserName+'</td>';
					else
						str+='<td class="issuePending" title="UserId : '+result[i].userName+'" id="'+result[i].tabUserId+'">'+result[i].tabUserName+'</td>';
						//str+='<td class="issuePending">'+result[i].userName+'</td>';*/
					if(result[i].userName != null){
						str+='<td id="'+result[i].cadreSurveyUserId+'">'+result[i].userName+'<span class="glyphicon glyphicon-map-marker mapClass"  attr_id="'+result[i].cadreSurveyUserId+'" attr_userName="'+result[i].userName+'" title="Click here For User Tracking" style="cursor:pointer;"></span>&nbsp&nbsp';
						if((result[i].slowPerformer != null && result[i].slowPerformer == 'true') || (result[i].betterPerformer != null && result[i].betterPerformer == 'true')){
							str+='<i class="glyphicon glyphicon-eye-open viewPerformanceCls" attr_cadre_survey_user_id="'+result[i].cadreSurveyUserId+'" attr_user_name="'+result[i].userName+'" title="Click Here to View Performance Details" style="cursor:pointer;"></i></td>';
						}else{
							str+='</td>';
						}
					}
					else
						str+='<td> - </td>';
						
					if(result[i].tabUserName != null)
						str+='<td title="UserId : '+result[i].userName+'" id="'+result[i].tabUserId+'">'+result[i].tabUserName+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].mobileNo != null)
						str+='<td>'+result[i].mobileNo+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].fieldMonitrngName != null)
						str+='<td>'+result[i].fieldMonitrngName+'</td>';
					else
						str+='<td> - </td>';
					/*if(result[i].districtName != null)
						str+='<td>'+result[i].districtName+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].constituencyName != null)
						str+='<td>'+result[i].constituencyName+'</td>';
					else
						str+='<td> - </td>';*/
					if(result[i].firstRecord != null)
						str+='<td>'+result[i].firstRecord+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].recentRecord != null)
						str+='<td>'+result[i].recentRecord+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].lastHourCount != null)
						str+='<td>'+result[i].lastHourCount+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].totalCount != null)
						str+='<td>'+result[i].totalCount+'</td>';
					else
						str+='<td> - </td>';
					/*if(result[i].performanceType != null && result[i].performanceType == 'SLOW')
						str+='<td> YES </td>';
					else if(result[i].performanceType != null && result[i].performanceType == 'BETTER')
						str+='<td> NO </td>';
					else
						str+='<td> - </td>';*/
					/*if(result[i].todayTarget != null)
						str+='<td>'+result[i].todayTarget+'</td>';
					else
						str+='<td> - </td>';*/
					if(result[i].openIssues != null && result[i].openIssues > 0)
						str+='<td>'+result[i].openIssues+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].fixedIssues != null && result[i].fixedIssues > 0)
						str+='<td>'+result[i].fixedIssues+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].closedIssues != null && result[i].closedIssues > 0)
						str+='<td>'+result[i].closedIssues+'</td>';
					else
						str+='<td> - </td>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		
		$("#dataCollectorsDivId").html(str);
		$(".viewPerformanceCls").tooltip();
		$(".mapClass").tooltip();
		$("#dataCollectorsImgId").hide();
		$('#detailsTable').dataTable({
	         "aaSorting": [[ 0, "asc" ]],
	         "iDisplayLength": 20,
	         "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
	    });
		//$('html,body').animate({scrollTop: $("#dataCollectorsDivId").offset().top}, 'slow');
	}
	else{
			$("#dataCollectorsImgId").hide();
			$("#dataCollectorsDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
	}
}

$(document).on("click",".completedRegistrationsSorting",function(){
	var resultType=$(this).attr("attr_value");
	 if(resultType != null && resultType == "All"){
		buildDataCollectorsPerformanceDetails(globalDataCollectorsDecendingArr); 
	 }else if(resultType == "verygood"){
		buildDataCollectorsPerformanceDetails(globalVeryGoodDataCollectors);
	 }else if(resultType == "good"){
		buildDataCollectorsPerformanceDetails(globalGoodDataCollectors);
	 }else if(resultType == "poor"){
		buildDataCollectorsPerformanceDetails(globalPoorDataCollectors);
	 }else if(resultType == "verypoor"){
		buildDataCollectorsPerformanceDetails(globalVeryPoorDataCollectors);
	 }else if(resultType == "notstarted"){
		buildDataCollectorsPerformanceDetails(globalNotAtStartedDataCollectors);
	 }
});
var globalVeryGoodDataCollectors;
var globalGoodDataCollectors;
var globalPoorDataCollectors;
var globalVeryPoorDataCollectors;
var globalNotAtStartedDataCollectors;

function movingDataToArrays(result){
globalDataCollectorsDecendingArr = [];
globalVeryGoodDataCollectors = [];
globalGoodDataCollectors = [];
globalPoorDataCollectors = [];
globalVeryPoorDataCollectors = [];
globalNotAtStartedDataCollectors = [];
if(result != null){
	for(var i in result){
		globalDataCollectorsDecendingArr.push(result[i]);
		if(result[i].countPerc > 100.00){
			globalVeryGoodDataCollectors.push(result[i]);
		}
		if(result[i].countPerc > 80.00 && result[i].countPerc<=100.00){
			globalGoodDataCollectors.push(result[i]);
		}
		if(result[i].countPerc > 50.00 && result[i].countPerc<=80.00){
			globalPoorDataCollectors.push(result[i]);
		}
		if(result[i].countPerc > 1.00 && result[i].countPerc<=50.00){
			globalVeryPoorDataCollectors.push(result[i]);
		}
		if(result[i].totalCount == 0){
			globalNotAtStartedDataCollectors.push(result[i]);
		}
	}
}
}
function getIssues(){
	if($("#issueTypeId").val() == 1){
		 $("#leaderIssueDivId").show();
	}else{
		$("#showIssueTypeDivId").show();
		 $("#leaderIssueDivId").hide();
	} 
}

//getApMandalMuncipalityNotStartedCount();
function getApMandalMuncipalityNotStartedCount(state){
	
/*var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});*/
	var stateId = state;
	var jsObj={
		stateId : stateId
	}
	$.ajax({          
			type : 'GET',       
			url : 'getStateWiseMandalMuncipalityNotStartedCountAction.action',       
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
				if(result != null){
					buildMandalMuncipaltyCount(result,stateId);  
				}
		});
}

function buildMandalMuncipaltyCount(result,stateId){
	var str1='';
		str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
			str1+='<div class="row">';
				str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id='+stateId+' attr_location_ids='+result.locationIdsList1+' class="getManalMuncipalityCls m_top10">'+result.todayNotStartedMandalMuncipalityCnt+'</h5>';
				str1+='<h5>YET TO START<br>MANDALS/MUNCIPALITIES</h5>';
			str1+='</div>';
          str1+='</div>';
	$("#mandalMuncipltyCountId").html(str1);  
}
$(document).on("click",".getManalMuncipalityCls",function(){
	    $("#cadreExcelExpBtnId").hide();
		//$("#cadreExcelExpBtnId").attr("attr_tab_user_type","location");
		var stateId = $(this).attr("attr_state_id");
		var mandalMuncipalityIds = $(this).attr("attr_location_ids");
		//var reportType="Started";
		var mandalMuncipalityIdsArr=[];
		 if(mandalMuncipalityIds != null && mandalMuncipalityIds != 0){
			mandalMuncipalityIdsArr = mandalMuncipalityIds.split(",");
			//reportType="NotStarted";
		} 
		//var locationType = "mandalMuncipality";
		 getMndlMncpalityNotStartedDetails(stateId,mandalMuncipalityIdsArr);
	});
function getMndlMncpalityNotStartedDetails(stateId,mandalMuncipalityIdsArr){
		$("#locationWiseCadreReportHeadingId").html("MANDAL/MUNCIPALITY WISE REPORT");
		$("#locationWiseCadreReportModalId").modal("show");
		$("#locationWiseCadreReportDivId").html(' ');
		$("#locationWiseProcessImgReport").show();
	
		var jsObj={  
			stateId : stateId,
			locationIdsArr : mandalMuncipalityIdsArr
		};
		$.ajax({          
			type : 'POST',       
			url : 'getMndlMncpalityTodayStatedNotStartedDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#locationWiseProcessImgReport").hide();
		  if(result != null && result.length > 0){
			 buildLocationWiseReport(result);  
		  }else{
			 $("#locationWiseCadreReportDivId").html("NO DATA AVAILABLE."); 
		  }
		});
	} 	
	
function buildLocationWiseReport(result){
		$("#cadreExcelExpBtnId").show();
	   var str='';
	   str+='<div class="table-responsive">';
	 	str+='<table style="border:1px solid #ddd" class="table table-bordered" id="constituencyDtlsDataTblId">';   
	   str+='<thead>';
             str+='<th>District Name</th>';
			 str+='<th>Constituency Id</th>';
			 str+='<th>Constituency Name</th>'	 
			// if(locationType == "mandalMuncipality"){
			 str+='<th>Mandal/Muncipality</th>';
			 //}
			 /*if(reportType=="Started"){
			  str+='<th>Total Registrations </th>';	 
			 }
			 */
		 str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
				str+='<tr>';
				 if(result[i].name != null && result[i].name.length > 0){
					str+='<td>'+result[i].name+'</td>';      
				  }else{
					str+='<td> - </td>';  
				  }
				  if(result[i].locationId != null && result[i].locationId > 0){
					str+='<td>'+result[i].locationId+'</td>';  
				  }else{
				  str+='<td> - </td>';  
				  }
				  if(result[i].locationName != null && result[i].locationName.length > 0){
					str+='<td>'+result[i].locationName+'</td>';  
				  }else{
				  str+='<td> - </td>';  
				  }
				  //if(locationType == "mandalMuncipality"){
					  if(result[i].locationName2 != null && result[i].locationName2.length > 0){
							str+='<td>'+result[i].locationName2+'</td>';  
						  }else{
						  str+='<td> - </td>';  
						  }
			          //}
				   /*if(reportType=="Started"){
			 	 if(result[i].total2016CadreCnt != null && result[i].total2016CadreCnt > 0){
						 str+='<td>'+result[i].total2016CadreCnt+'</td>';       
					  }else{
						str+='<td> - </td>';  
					  }			  
			        }*/
				str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
			 str+='</div>';
		 $("#locationWiseCadreReportDivId").html(str);
		//if(locationType =="mandalMuncipality"){
			 //if(locationType == "mandalMuncipality"){
				//if(reportType=="Started"){
					 /*$("#constituencyDtlsDataTblId").dataTable({
					 "aaSorting": [[ 4, "asc" ]], 
					"iDisplayLength" : 10,
					"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
				 }); */
				 $('#constituencyDtlsDataTblId').dataTable({
	         "aaSorting": [[ 0, "asc" ]],
	         "iDisplayLength": 20,
	         "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
	    });
			  //}
			 /* else{
			  $("#constituencyDtlsDataTblId").dataTable({
			    "aaSorting": [[ 2, "asc" ]], 
			    "iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]
		      });    
			 }*/
		  //}
		  /*else if(locationType == "Constituency"){
			   if(reportType=="Started"){
				  $("#constituencyDtlsDataTblId").dataTable({
					 "aaSorting": [[ 3, "desc" ]], 
					"iDisplayLength" : 10,
					"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
				 });   
		      }else{
				$("#constituencyDtlsDataTblId").dataTable({
			    "aaSorting": [[ 2, "asc" ]], 
			    "iDisplayLength" : 10,
                "aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]				
		        });    
			 }
		  }*/
		//}
	}
	
	
	
 $(document).on("click","#cadreExcelExpBtnId",function(){
	 generateExcelReportForCadre();	
 });
 
 function generateExcelReportForCadre(){
	tableToExcel("constituencyDtlsDataTblId", 'Location Wise Registrations Report');
}


$(document).on("click",".stateWiseCls",function(){
	$("#mandalMuncipltyCountId").html('');
		stateId = $(":radio:checked").val();
		getApMandalMuncipalityNotStartedCount(stateId);
});

$(document).on("click",".viewPerformanceCls",function(){
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
	var userName= $(this).attr("attr_user_name");
	getPerfomanceDetialsList(cadreSurveyUserId);
	$("#performanceDetailsModalDivId").modal("show");
	
	$("#hiddenUserId").val(userName);
});

function getPerfomanceDetialsList(cadreSurveyUserId){
	$("#pefromanceTableDivId").html('');
	$("#userNameDivId").html('');
	$("#performnaceErrDivId").html('');
	var jsObj ={
		cadreSurveyId : cadreSurveyUserId
	}
	$.ajax({
			type : 'GET',
			url : 'getcadrePerfrmanceDetailsListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result != null && result.length > 0){
				buildingPerformanceType(result);
			}else{
				$("#userNameDivId").html('<b>'+$("#hiddenUserId").val()+'</b>'+' - <span>USER PERFORMANCE DETAILS</span>');
				$("#performnaceErrDivId").html('<span style="color:red;">NO DATA AVAILABLE</span>');
			}
		});
}

function buildingPerformanceType(result){
	var str='';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered" id="pefromanceTableDataDivId">';   
				str+='<thead>';
					str+='<th>CATEGORY TYPE</th>';
					str+='<th>COMMENTS</th>';
				str+='</thead>';
		 str+='<tbody>';
		 for(var i in result){
			 str+='<tr>';
		 if(result[i].name != null && result[i].name.length > 0)
				str+='<td>'+result[i].name+'</td>';
			else
				str+='<td> - </td>';
		 if(result[i].mobileNumber != null && result[i].mobileNumber.length > 0)
			  str+='<td>'+result[i].mobileNumber+'</td>';
		  else
			  str+='<td> - </td>';
		  
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
$("#userNameDivId").html('<b>'+$("#hiddenUserId").val()+'</b>'+' - <span>USER PERFORMANCE DETAILS</span>');
$("#pefromanceTableDivId").html(str);
$('#pefromanceTableDataDivId').dataTable({
	         "aaSorting": [[ 0, "asc" ]],
	         "iDisplayLength": 20,
	         "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
	    });
}
	
$(document).on("click",".mapClass",function(){
	var cadreUserId = $(this).attr("attr_id");
	var userName = $(this).attr("attr_userName");
	var urlStr = "tdpUserWiseMapAction.action?userId="+cadreUserId+"&username="+userName;
	var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
});