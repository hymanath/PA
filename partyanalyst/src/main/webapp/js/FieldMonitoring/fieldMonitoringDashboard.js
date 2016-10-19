	
	function onLoadCalls(){
		getOverAllDataCollectorsCounts();
		getIssueStatusWiseCounts();
		getIssueTypeWiseCounts();
		getStatusWiseIssuesDetails();
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
		                  toDate : toDate			//"2016-10-18" 
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
			   str +='<li>'+result[i].name+' issues<span>'+result[i].inviteeCount+'</span></li>';
			}
			str +='</ul>';
			str +='</div>';
			str +='</div>';
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
		
function getStatusWiseIssuesDetails(){
	$("#statusWiseDetailsDivId").html('');
	$("#statusWiseDetailsImgId").show();
	$("#dtatusDivId").show();
	
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	
	var jsObj = { 
	  fromDate : fromDate,    //"10/18/2016",
	  toDate : toDate,  		  //"10/20/2016",		
	  issueType : 2,
	  issueStatus : 1
	}
	$.ajax({
		type : 'GET',
		url : 'getStatusWiseIssuesDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildStatusWiseDetails(result);
		}
		else{
			$("#statusWiseDetailsImgId").hide();
			$("#statusWiseDetailsDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
		}
	});
}

function buildStatusWiseDetails(result){
	var str = '';
	
	//str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		//str+='<div class="block">';
			str+='<h3 class="panel-title text-capital">apk issue - 20</h3>';
			str+='<table class="table b_1">';
				str+='<thead class="text-capitalize">';
					str+='<th>User Id</th>';
					str+='<th>user name</th>';
					str+='<th>user contact number</th>';
					str+='<th>state</th>';
					str+='<th>district</th>';
					str+='<th>constituency</th>';
					str+='<th>vendor name</th>';
					str+='<th>leader name</th>';
					str+='<th>open issues</th>';
					str+='<th>fixed issues</th>';
					str+='<th></th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td class="issueCmpltd">'+result[i].userName+'</td>';
						str+='<td>'+result[i].tabUserName+'</td>';
						str+='<td>'+result[i].mobileNo+'</td>';
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
						if(result[i].vendorName != null)
							str+='<td>'+result[i].vendorName+'</td>';
						else
							str+='<td> - </td>';
						str+='<td> - </td>';
						if(result[i].openIssues != null)
							str+='<td>'+result[i].openIssues+'</td>';
						else
							str+='<td> - </td>';
						if(result[i].fixedIssues != null)
							str+='<td>'+result[i].fixedIssues+'</td>';
						else
							str+='<td> - </td>';
						str+='<td><button class="btn btn-success text-capitalize issuesBtn">manage issues</button></td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		//str+='</div>';
	//str+='</div>';
	
	$("#statusWiseDetailsImgId").hide();
	$("#statusWiseDetailsDivId").html(str);
}