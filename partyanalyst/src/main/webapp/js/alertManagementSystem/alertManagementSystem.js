/*global Function and variables Start*/
var currentFromDate = moment().format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");
var globalStateId = 36;
$("#dateRangePicker").daterangepicker({
	opens: 'left',
	startDate: currentFromDate,
	endDate: currentToDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
		'Today' : [moment(), moment()],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
});
$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	currentFromDate = picker.startDate.format('DD/MM/YYYY');
	currentToDate = picker.endDate.format('DD/MM/YYYY');
	totalAlertGroupByStatusForGovt()
	totalAlertGroupByStatusThenDepartment()
});
onLoadCalls();
function onLoadCalls()
{
	totalAlertGroupByStatusForGovt()
	totalAlertGroupByStatusThenDepartment()
}
/*global Function and variables End*/
/* Status OverView Start*/
function totalAlertGroupByStatusForGovt()
{
	$("#statusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var deptIdArr = [1,2,3,4];
    var paperIdArr = [68,78,78,93,103,106,147,147,147];
    var chanelIdArr = [1,2,3,4,5,6,7];
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroupByStatusForGovtAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildTotalAlertGroupByStatusForGovt(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
}
function buildTotalAlertGroupByStatusForGovt(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div id="totalAlertGroupByStatusForGovt"></div>';
			str+='<div id="statusOverViewTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].count;
						str+='<tr>';
							str+='<td>'+result[i].status+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsCls" attr_status_id="'+result[i].statusId+'">'+result[i].count+'</td>';
							str+='<td>'+result[i].statusPercent+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#statusOverview").html(str);
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].statusPercent;
		statusName = result[i].status;
		var cnt = result[i].count;
		var stsId = result[i].statusId;
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		var chart = new Highcharts.Chart({
			colors: ['#FE9900','#0B9614','#8E4654','#F25C81'],
			chart: {
				renderTo: 'totalAlertGroupByStatusForGovt',
				type: 'pie',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			 tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "Total Alerts - "+cnt+"";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: true,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 100,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusOverviewArr
			}]
		},
	
		function(chart) { // on complete
			var textX = chart.plotLeft + (chart.plotWidth  * 0.5);
			var textY = chart.plotTop  + (chart.plotHeight * 0.5);

			var span = '<span id="pieChartInfoText" style="position:absolute; text-align:center;">';
			span += '<span style="font-size: 18px">TOTAL</span><br>';
			span += '<span style="font-size: 14px;cursor:pointer;" class="getDtlsCls" attr_status_id="0">'+totalAlert+'</span>';
			span += '</span>';

			$("#statusOverViewTotal").append(span);
			span = $('#pieChartInfoText');
			span.css('left', textX + (span.width() * -0.5));
			span.css('top', textY + (span.height() * -0.5));
		});
	});
}
/* Status OverView End*/
/* DEPARTMENT WISE STATUS OVERVIEW START*/
function totalAlertGroupByStatusThenDepartment()
{
	$("#departmentWiseStatusOvrVw").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
    var deptIdArr = [1,2,3,4];
    var paperIdArr = [68,78,78,93,103,106,147,147,147];
    var chanelIdArr = [1,2,3,4,5,6,7];
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroupByStatusThenDepartmentAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildtotalAlertGroupByStatusThenDepartment(result);   
		}else{
			$("#departmentWiseStatusOvrVw").html('NO DATA AVAILABLE')
		}
    });
}
function buildtotalAlertGroupByStatusThenDepartment(result)
{
	var str='';
	str+='<ul class="nav nav-tabs" role="tablist">';
	for(var i in result)
	{
		if(i == 0)
		{
			str+='<li role="presentation" class="active text-capital"><a href="#'+result[i].status+'" aria-controls="'+result[i].status+'" role="tab" data-toggle="tab">'+result[i].status+'</a></li>';
		}else{
			str+='<li role="presentation" class="text-capital"><a href="#'+result[i].status+'" aria-controls="'+result[i].status+'" role="tab" data-toggle="tab">'+result[i].status+'</a></li>';
		}
	}
	str+='</ul>';
	str+='<div class="tab-content">';
	for(var i in result)
	{
		if(i == 0)
		{
			str+='<div role="tabpanel" class="tab-pane active" id="'+result[i].status+'">';
		}else{
			str+='<div role="tabpanel" class="tab-pane" id="'+result[i].status+'">';
		}
		
			str+='<div class="row">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="m_top20">DEPARTMENTS WISE</h4>';
					str+='<div class="row">';
						str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
							str+='<ul style="list-style:none;" class="textAlignDepartment dynamicHeightApply'+i+'">';
							for(var j in result[i].subList1)
							{
								if(result[i].subList1[j].category !=null && result[i].subList1[j].category.length > 40){
									str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subList1[j].category+'">'+result[i].subList1[j].category.substring(0,40)+'...</span> <span style="cursor:pointer;" class="pull-right getDtlsCls" attr_status_id="'+result[i].statusId+'" attr_dept_id="'+result[i].subList1[j].categoryId+'">'+result[i].subList1[j].categoryCount+'</span></li>';  
								}else{//swadhin
									str+='<li>'+result[i].subList1[j].category+'  <span style="cursor:pointer;" class="pull-right getDtlsCls" attr_status_id="'+result[i].statusId+'" attr_dept_id="'+result[i].subList1[j].categoryId+'">'+result[i].subList1[j].categoryCount+'</span></li>';
								}
								
							}//class="getDtlsCls" attr_status_id="'+result[i].statusId+'"
							str+='</ul>';
						str+='</div>';
						str+='<div class="col-md-5 col-xs-12 col-sm-4">';
							str+='<div id="departmentStatusGraph'+i+'"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';	
	}
	str+='</div>';
	$("#departmentWiseStatusOvrVw").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	
	for(var i in result)
	{
		var dynamicHeight;
		$(".dynamicHeightApply"+i).each(function(){
			dynamicHeight = $(this).find("li").length;
			dynamicHeight = (dynamicHeight*40)+"px";
		});
		$("#departmentStatusGraph"+i).css("height",dynamicHeight);
		var dynamicWidth = $("#departmentStatusGraph0").parent().width();
		$("#departmentStatusGraph"+i).css("width",dynamicWidth)
		var departmentStatusOvrVwArr = [];
		for(var j in result[i].subList1)
		{
			var departmentStatus = [];
			departmentStatus.push(result[i].subList1[j].category)
			departmentStatus.push(result[i].subList1[j].statusPercent)
			departmentStatusOvrVwArr.push(departmentStatus)
		}
		$('#departmentStatusGraph'+i).highcharts({
			chart: {
				type: 'bar'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
			 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,
				categories: '',
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
				labels: {
					enabled: false,
						
					},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			tooltip: {
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.2f}%</b><br/>'
			},
			plotOptions: {
				bar: {
					dataLabels: {
						enabled: true,
						formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return Highcharts.numberFormat(this.y,2) + '%';
							}
						}
					}
				}
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
				 name: '',
				 colorByPoint: true,
				 data: departmentStatusOvrVwArr
			}]
		});
	}
	
}

/* DEPARTMENT WISE STATUS OVERVIEW END*/

/* Total Alerts Modal Start CLick Based*/
$(document).on("click",".getDtlsCls",function(){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#totalAlertsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	var deptIdArr = [];
	var alertStatusId = $(this).attr("attr_status_id");
	var deptId = $(this).attr("attr_dept_id");
	if(deptId != null){
		deptIdArr.push(deptId);  
	}else{
		deptIdArr = [1,2,3,4];
	}
	
    var paperIdArr = [68,78,78,93,103,106,147,147,147];
    var chanelIdArr = [1,2,3,4,5,6,7];
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
	  statusId : alertStatusId       
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertByStatusAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildtotalAlertsModalTabId(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
});
function getData(count, alertStatusId){ 
	$("#totalAlertsModalTabId").html('');
	$("#totalAlertsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'  
	});
	var deptIdArr = [];
	var deptId = $(this).attr("attr_dept_id");
	if(deptId != null){
		deptIdArr.push(deptId);  
	}else{
		deptIdArr = [1,2,3,4];
	}
	
    var paperIdArr = [68,78,78,93,103,106,147,147,147];
    var chanelIdArr = [1,2,3,4,5,6,7];
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
	  statusId : alertStatusId       
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertByStatusAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildtotalAlertsModalTabId(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
}	
function buildtotalAlertsModalTabId(result){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var str='';
	if($(window).width() < 500)
	{
		str+='<div class="table-responsive" >';
	}
		str+='<table class="table table-condensed" style="border:1px solid #ddd" id="dataTableTotalAlerts">';
			str+='<thead>';
				str+='<th>Alert Source</th>';
				str+='<th>Alert Title</th>';
				str+='<th>Created Date</th>';
				str+='<th>Last Updated Date</th>';
				str+='<th>Current Status</th>'	 
				str+='<th>LAG Days</th>';
				str+='<th>Alert Level</th>';
				str+='<th>Location</th>';
				str+='<th></th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					if(result[i].source != null && result[i].source.length > 0){
						str+='<td><strong>'+result[i].source+'</strong></td>';         
					}else{
						str+='<td> - </td>';     
					}
					if(result[i].title != null && result[i].title.length > 0){
						str+='<td><strong>'+result[i].title+'</strong></td>';         
					}else{
						str+='<td> - </td>';     
					}
					if(result[i].createdDate != null && result[i].createdDate.length > 0){
						str+='<td>'+result[i].createdDate+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].updatedDate != null && result[i].updatedDate.length > 0){
						str+='<td>'+result[i].updatedDate+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].status != null && result[i].status.length > 0){
						str+='<td>'+result[i].status+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].interval != null){
						str+='<td>'+(parseInt(result[i].interval)-parseInt(1))+'</td>';            
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].alertLevel != null && result[i].alertLevel.length > 0){
						str+='<td>'+result[i].alertLevel+'</td>';               
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].location != null && result[i].location.length > 0){
						str+='<td>'+result[i].location+'</td>';        
					}else{
						str+='<td> - </td>';        
					}
					str+='<td><button class="btn btn-success alertDetailsModalCls" attr_alert_Id="'+result[i].id+'">Alert Details</button></td>';      
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
	if($(window).width() < 500)
	{
		str+='</div>';
	}
	$("#totalAlertsModalTabId").html(str);
	$("#dataTableTotalAlerts").dataTable();
}
/* Total Alerts Modal End CLick Based*/

/* Alert Details Modal Start*/
$(document).on("click",".alertDetailsModalCls",function(){
	$("#alertDetailsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	
	var alertId = $(this).attr("attr_alertId");
	getAlertData();
	getAlertStatusCommentsTrackingDetails()
});
function getAlertData()
{
	$("#alertCandidateDataId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj =
	{
		alertId  :3725,
		task : ""
	}
	$.ajax({
			  type:'GET',
			  url: 'getAlertsDataAction.action',
			  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null)
		{
			buildAlertData(result);
		}
	});
}
function buildAlertData(result)
{
	
	for(var i in result)
	{
		var severityTdId = result[i].categoryId;
		
		$("#typeId").html('<b>'+result[i].alertType+'</b>');
		$("#createdDate").html('<b>'+result[i].date+'</b>');
		$("#alertStatus").html('<b>'+result[i].status+'</b>');
		if(severityTdId ==1){
			$("#severityTdId").show();
		}else{
			$("#severityTdId").hide();
		}
		$(".severityIdColorCls").addClass('<b>'+result[i].severity+'</b>');
		$("#severityId").html('<b>'+result[i].severity+'</b>');
		$("#levelId").html('<b>'+result[i].regionScope+'</b>');
		
		var location ='';
			
		if(result[i].locationVO.stateId !=null){
			location +='<b>'+result[i].locationVO.state+' , ';
		}
		if(result[i].locationVO.districtId !=null){
			location +=''+result[i].locationVO.districtName+' District, ';
		}
		if(result[i].locationVO.constituencyId !=null){
			location +=''+result[i].locationVO.constituencyName+' Constituency, ';
		}
		
		if(result[i].locationVO.localEleBodyName !=null && result[i].locationVO.localEleBodyName.length>0){
			location +=''+result[i].locationVO.localEleBodyName+' Municipality, ';
		}
		if(result[i].locationVO.tehsilName !=null && result[i].locationVO.tehsilName.length>0){
			location +=''+result[i].locationVO.tehsilName+' Mandal, ';
		}
		if(result[i].locationVO.wardName !=null && result[i].locationVO.wardName.length>0){
			location +=''+result[i].locationVO.wardName+' Ward, ';
		}
		if(result[i].locationVO.villageName !=null && result[i].locationVO.villageName.length>0){
			location +=''+result[i].locationVO.villageName+' Panchayat </b>';
		}
		
		$("#LocationId").html(''+location+'');
		
		$("#titleId,#mainTitleId").html('<b>'+result[i].title+'</b>');
		
		$("#descriptionId").html('<b>'+result[i].desc+'</b>');
		
		if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
			$(".imageUrlUlCls").html("<li class='articleDetailsCls' attr_articleId="+result[i].alertCategoryTypeId+" style='cursor:pointer'><img src='http://mytdp.com/NewsReaderImages/"+result[i].imageUrl+"' style='width: 150px; height: 150px;'></img></li>");
			$("#imageUrlTrId").show();
		}else{
			$(".imageUrlUlCls").html("");
			$("#imageUrlTrId").hide();
		}
		
		buildAlertCandidateData(result[i].subList,result[i].categoryId);
	}
}
function buildAlertCandidateData(result,categoryId)
{

	var str='';
	
	if(result == null || result.length == 0)
	{
		$("#alertCandidateDataId").html('No Involved Members..');
		return;
	}
	
	if(categoryId !=null && categoryId>1){
		for(var i in result)
		{
			
			str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10" style="padding: 3px;">';
				str+='<div class="media" style="border:1px solid #ddd;height:100px">';
					str+='<div class="media-left">';						
					if(result[i].image !=null && result[i].image.length>0){
						str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}else{
						str+=' <img src="dist/Appointment/img/thumb.jpg"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}					   					  
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
						//str+='  <p>'+result[i].committeeName+' Committee '+result[i].committeePosition+' </p>';
					str+='  <p><b class="text-capital text-danger">Designation : </b>'+result[i].committeePosition+' </p>';
					 // str+='  <p>'+result[i].mobileNo+'</p>';
					 // str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success" style="margin-top: 7px;">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger" style="margin-top: 7px;">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral" style="margin-top: 7px;">N</span>';
					  }
					  
					  if(result[i].organization !=null){
						  str+='<p><b class="text-capital text-danger">Organization</b> : '+result[i].organization+'</p>';
					  }
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><b class="text-capital text-danger">Membership No </b> : '+result[i].membershipNo+'</p>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </div>';

		}
	}else{
		for(var i in result)
		{
			str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10" style="padding: 3px;">';
				str+='<div class="media" style="border:1px solid #ddd;height:100px">';
					str+='<div class="media-left">';
					   str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
							str+='<b><p class="text-capital">'+result[i].electionType+" "+result[i].committeeName+' Committee '+result[i].committeePosition+'</b></p>';
						if(result[i].designation != null && result[i].designation != "")
							str+='<b><p class="text-capital">'+result[i].designation+'</p></b>';
					  str+='  <p>'+result[i].mobileNo+'</p>';
					  str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><a>'+result[i].membershipNo+'</a></p>';
					  }
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success" style="margin-top: 7px;">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger" style="margin-top: 7px;">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral" style="margin-top: 7px;">N</span>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </div>';
		}
	}
	$("#involvedCandidatesCnt").html(result.length);	
	$("#alertCandidateDataId").html(str);
}
function getAlertStatusCommentsTrackingDetails()
{
	$("#alertCommentsDivIdNew").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj={
				alertId:3725,
				task:""
			}
	$.ajax({
	  type : 'GET',
	  url : 'getAlertStatusCommentsTrackingDetails.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
		alertComments(result);		
	});
	
}
function alertComments(result)
{
	var docName = '';
	var extName = [];
	var statusId = 0;
	var length = result.length;
	length = length - 1;
	var str = '';
	if(status == 'false'){  
		$(".cadreAlertCommentsDivId").show();
		str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true" style="margin-bottom:0px;">';
	}
	str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
		statusId = result[i].statusId;
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
			if(length == i)  
			{
				str+='<a role="button" class="alertCommentColapse" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
			}else{
				str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="false" aria-controls="collapse'+i+'">';
			}
			if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';    
				str+='</h4>';
			}else{
				str+='<h4 class="panel-title">'+result[i].status+'';
					str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';
				str+='</h4>';
			} 
				str+='</a>';  
			str+='</div>';
			if(length == i)  
			{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
			}else{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
			}
				str+='<div class="panel-body" style="padding:5px;">';
					str+='<div class="row">';
						for(var j in result[i].sublist2){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].sublist2[j].date
								var dateArr = date.split("-");
								var year = dateArr[0];
								var month = dateArr[1];
								var day = dateArr[2];
								str+='<table class="table tableCalendar">';
									str+='<tr>';
										str+='<td colspan="2">';
											str+='<h3>'+day+'</h3>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>'+getMonth(month)+'</td>';        
										str+='<td>'+year+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-10 col-xs-12 col-sm-10" style="padding-left:0px;">';
								str+='<ul class="alertStatusTracking">';
									str+='<li>';
										str+='<div class="arrow_box_left">';
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:'+result[i].sublist2[j].sublist[k][0].timeString+'</span><br>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='<img src="dist/Appointment/img/thumb.jpg" style="width:10px;display:inline-block"/> '+result[i].sublist2[j].sublist[k][l].cadreName+'<br>';
												}
												str+='</p>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p>'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
												if(result[i].sublist2[j].sublist[k][0].docList != null && result[i].sublist2[j].sublist[k][0].docList.length > 0){
													str+='<p><span style="color:#A286C0;font-size:13px;">DOCUMENTS:</span><br>';
													str+='<ul>';
													for(var t in result[i].sublist2[j].sublist[k][0].docList){
														docName = result[i].sublist2[j].sublist[k][0].docList[t].name;
														extName = docName.split("/");
														str+='<li id="document'+result[i].id+'"><a href="/Reports/'+result[i].sublist2[j].sublist[k][0].docList[t].name+'" target="_blank">'+extName[1]+'</a></li>';          
													}
													str+='</ul>';              
												}
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].sublist2[j].sublist[k][0].userName+'</span></p>';
												str+='<hr style="margin-top:20px;"/>';
											str+='</div>';   
										}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						}           
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	str+='</div>';
	$("#alertCommentsDivIdNew").html(str)
}
function getMonth(month){
	if(month=="01"){
		return "Jan"
	}else if(month=="02"){
		return "Feb"
	}else if(month=="03"){
		return "Mar"
	}else if(month=="04"){
		return "Apr"
	}else if(month=="05"){
		return "May"
	}else if(month=="06"){
		return "Jun"
	}else if(month=="07"){
		return "Jul"
	}else if(month=="08"){
		return "Aug"
	}else if(month=="09"){
		return "Sep"
	}else if(month=="10"){
		return "Oct"
	}else if(month=="11"){
		return "Nov"
	}else if(month=="12"){  
		return "Dec"
	}  
}
$(document).on("click",".alertDetailsModalClose",function(){
	setTimeout(function(){
		$("body").addClass("modal-open")
	},500);
});
/* Alert Details Modal End*/

/* Departments Complete Overview Start*/
function buildDistrictWiseArticleRelatedToProblem(result){
	var str='';
	var distWiseArticlesRelated = [];
	str+='<div id="comaprisonDistrictWiseArticle" style="height:150px;"></div>';
	for(var i in result){
		var obj1 = {
			name: result[i].name,
			y: result[i].posPercent,
			extra:result[i].posCount
		};
		distWiseArticlesRelated.push(obj1);
	}
	$("#districtWiseArticleRelatedToProblem").html(str)
	$(function () {
		 $("#comaprisonDistrictWiseArticle").highcharts({
			colors: ['#AA3732'],
			chart: {
				type: 'column'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				
				type: 'category',
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
					text: ''
				},
				labels: {
					enabled:false
				}
			},
			legend: {
				enabled: false
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
								return Highcharts.numberFormat(this.y,1) + '%';
							}
						}
					  
					}
				}
			},

			tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}% - {point.extra}</b>'
			},

			series: [{
				name: 'Completed',
				data: distWiseArticlesRelated
			}],
		 
		});
	});

}
/* Departments Complete Overview End*/