
	function onLoadCalls(){
		getOverAllDataCollectorsCounts()
		getIssueStatusWiseCounts();
		getIssueTypeWiseCounts();
		getDataMonitoringOverViewDetails();
		getDistrictWiseIssueTypesCount();
	}
	function getOverAllDataCollectorsCounts(){
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
				$("#activeDataCollectorsId").html(result.activeUsers);
				$("#passiveDataCollectorsId").html(result.passiveUsers);
			}	
		});
	}
	function getIssueStatusWiseCounts(){
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
			              fromDate : fromDate,    //"2016-10-01",
		                  toDate : toDate,			//"2016-10-18" 
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
			else
			   str +='<li>ACTIVE TEAM MEMBERS<span>'+result[i].inviteeCount+'</span></li>';
			}
			str +='</ul>';
			$("#statusCountDivId").html(str);
		
		}	
		
		
	function getIssueTypeWiseCounts(){
		var openIssuesArr = [];
		var fixedIssuesArr = [];
			var dates = $(".singleDate").val();
	        var dateArr = dates.split("-");
	        var fromDate;
	        var toDate;
	   if(dateArr != null){
		     fromDate = dateArr[0];
		     toDate = dateArr[1];
	       }
			var jsObj = { 
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
					for(var i in result){
						if(result[i].issueTypes != null && result[i].issueTypes.length > 0){
							if(result[i].id == 1){
								for(var j in result[i].issueTypes){
									var dataArr = [];
									dataArr.push(result[i].issueTypes[j].name);
									dataArr.push(parseInt(result[i].issueTypes[j].inviteeCount));
									openIssuesArr.push(dataArr);
								}
							}
							if(result[i].id == 2){
								for(var j in result[i].issueTypes){
									var dataArr = [];
									dataArr.push(result[i].issueTypes[j].name);
									dataArr.push(parseInt(result[i].issueTypes[j].inviteeCount));
									fixedIssuesArr.push(dataArr);
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
							}
						},
						title: {
							text: null
						},
						subtitle: {
							text: null
						},
						legend: {
						  layout: 'vertical',
						  floating: false,
						  align: 'right',
						  verticalAlign: 'middle',
						  symbolPadding: 20,
						  symbolWidth: 10
					  },
						plotOptions: {
						  pie: {
							allowPointSelect: false,
							innerSize: 100,
							depth: 45,
							cursor: 'pointer',
							dataLabels: {
							  enabled: false
							},
							showInLegend: true,

						  },
						  series: {
							point: {
							  events: {
								legendItemClick: function () {
								alert(this.index)
									return false; // <== returning false will cancel the default action
								}
							  }
							}
						  }
						},
						series: [{
							name: 'Count',
							data: openIssuesArr
						}]
					});
					$('#fixedIssues').highcharts({
						chart: {
							type: 'pie',
							options3d: {
								enabled: true,
								alpha: 45
							}
						},
						title: {
							text: null
						},
						subtitle: {
							text: null
						},
						legend: {
						  layout: 'vertical',
						  floating: false,
						  align: 'right',
						  verticalAlign: 'middle',
						  symbolPadding: 20,
						  symbolWidth: 10
					  },
						plotOptions: {
						  pie: {
							allowPointSelect: false,
							innerSize: 100,
							depth: 45,
							cursor: 'pointer',
							dataLabels: {
							  enabled: false
							},
							showInLegend: true,

						  },
						  series: {
							point: {
							  events: {
								legendItemClick: function () {
								alert(this.index)
									return false; // <== returning false will cancel the default action
								}
							  }
							}
						  }
						},
						series: [{
							name: 'Count',
							data: fixedIssuesArr
						}]
					});
				}
			});
		}
		
function getDataMonitoringOverViewDetails(){
 $("#dataMonitoringOverviewTblId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
       var fromDate;
       var toDateDate;
       var date=$(".singleDate").val();
	  if(date != undefined && date.trim().length > 0){
		  fromDate = date.split("-")[0]; 
		  toDateDate = date.split("-")[1]; 
	  }	 
   var jsObj = {
	   
		  fromDate : fromDate,
		  toDate : toDateDate
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
		 str+='<li>';
			str+='active team members';
			str+='<span class="pull-right"> - </span>';
		 str+='</li>';
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
   $(document).on("click",".singleDate",function(){
   
		getOverAllDataCollectorsCounts();
		getIssueStatusWiseCounts();
		getIssueTypeWiseCounts();
		getDataMonitoringOverViewDetails();
   });
   function getDistrictWiseIssueTypesCount(){
		var dates = $(".singleDate").val();
		var dateArr = dates.split("-");
		var fromDate;
		var toDate;
		if(dateArr != null){
			fromDate = dateArr[0];
			toDate = dateArr[1];
		}
		var stateArr = [];
		stateArr.push("1");
		stateArr.push("36");
		
		var jsObj = { 
		  fromDate : fromDate,		//"10/01/2016",
		  toDate : toDate,		 	//"10/18/2016"  
		  issueStatusId : 1,
		  stateIds  : stateArr
		  
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
			for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<h4>'+result[i].name+' - DISTRICT WISE - <b>OPEN ISSUES</b> OVERVIEW</h4>';
			str+='</div>';
				if(result[i].distList !=null && result[i].distList.length >0){
				str+='<div class="col-xs-12 col-sm-12 col-md-12">';
				str+='<ul class="ComparisionPartyWiseSlickApply">';
					for(var j in result[i].distList){
					
					str+='<li><div id="districtWiseissues'+i+''+j+'" class="chartLiD" style="height:300px" ></div></li>';
				
						
					}
					str+='</ul>';
					str+='</div>';
					
				}
			}
		
				
			}
			$("#districtWiseNewsIssuesReport").html(str);						
		
		
		
		if(result != null && result.length > 0){
			for(var i in result){
			
			var districtNamesArray;
			
				if(result[i].distList !=null && result[i].distList.length >0){
				
					for(var j in result[i].distList){
					var mainArr=[];
					districtNamesArray = result[i].distList[j].name;
					if(result[i].distList[j].issueTypes !=null && result[i].distList[j].issueTypes.length >0){
						for(var k in result[i].distList[j].issueTypes){
						if(result[i].distList[j].issueTypes[k].inviteeCount !=0){
							var subArr=[];
							subArr.push(result[i].distList[j].issueTypes[k].name);
							subArr.push(parseInt(result[i].distList[j].issueTypes[k].inviteeCount));
							mainArr.push(subArr);
							}
						}
					}
					 
					 
						$('#districtWiseissues'+i+''+j+'').highcharts({
							chart: {
								type: 'column'
							},
							title: {
								text: districtNamesArray
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
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>'
							},
							series: [{
								name: '',
								colorByPoint: true,
								data: mainArr,
								dataLabels: {
									enabled: true,
									rotation: -90,
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
	