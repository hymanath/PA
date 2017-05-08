var globalUserLevelValues = [0]; 
var globalUserLevelId = 0;
var start = moment().subtract(29, 'days');
var end = moment();
function cb(start, end){
	$('#reportrange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
}

$('#reportrange').daterangepicker({
	startDate: start,
	endDate: end,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
}, cb);
 
var callCenterUserFDate=moment().format("DD/MM/YYYY");
var callCenterUserTDate=moment().format("DD/MM/YYYY");

$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
	callCenterUserFDate = picker.startDate.format('DD/MM/YYYY');
	callCenterUserTDate = picker.endDate.format('DD/MM/YYYY');
	getTotalLocationWiseGrivenaceReport();
});
$(document).on("click",".daterangeClorCls",function(){ 
    $(".daterangeClorCls").removeClass("dateColorCls");
}); 
   
getGrievanceReport();
function getGrievanceReport(){
$("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
$("#barGraph").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
    var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
    var jsObj ={
		fromDate: callCenterUserFDate,                       
		toDateStr:callCenterUserTDate,  
		deptId:deptId,
		sourceId:sourceId,       
		rangeType:"day",     
		stateId:1
	}
	$.ajax({
			type:'GET',         
			url: 'getGrievanceReportAction.action',
			data: {task :JSON.stringify(jsObj)}
			}).done(function(result){
			if(result !=null && result.length>0){
				buildGrievanceReport(result);
				buildLocationWiseGrivenacereportGraph(result);
				
		    }else{
				$("#statusWiseAlertCntId").html('No Data Available');
				$("#grivenaceTableId").html('No Data Available');
			}
	}); 
}	
//location wise table     
 function buildGrievanceReport(result) {
	$("#statusWiseAlertCntId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	$("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var str='';
        str+='<table id="grievanceReportTableId" class="table table-bordered " cellspacing="0">';
	        str+='<thead>';
	        	str+='<tr>';
	        		str+='<th>District</th>';     
	        		str+='<th>Total</th>';
					for(var i in result[0].subList1){       
			           str+='<th>'+result[0].subList1[i].statusType+'</th>';
					}
					for(var j in result[0].subList2){         
						str+='<th style="background-color:#ecebd6">'+result[0].subList2[j].day+'</th>';
					}
				str+=' </tr>';
			str+='</thead>';
			str+='<tbody>';
			var locTotal = 0;
			for(var i in result){
				str+='<tr>'; 
					str+='<td style="cursor:pointer;" attr_group_type="tehsil" attr_location_id="'+result[i].id+'" attr_location_name="'+result[i].name+'"class="bellowLvlLocCls"><i class="glyphicon glyphicon-plus-sign"></i>'+result[i].name+'</td>';         
					str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_group_type="status" attr_location_id="'+result[i].id+'">'+result[i].totalAlertCnt+'</td>';
					locTotal = parseInt(locTotal) + parseInt(result[i].totalAlertCnt);
					for(var j in result[i].subList1){
						if(result[i].subList1[j].totalAlertCnt != 0){
							str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_location_id="'+result[i].id+'" attr_group_type="status">'+result[i].subList1[j].totalAlertCnt+'</td>';
						}else{
							str+='<td>-</td>';
						}      
					}
					for(var j in result[i].subList2){  
						if(result[i].subList2[j].totalAlertCnt != 0){        
							str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_pattern="'+result[i].subList2[j].day+'" attr_location_id="'+result[i].id+'" attr_group_type="day">'+result[i].subList2[j].totalAlertCnt+'</td>';
						}else{      
							str+='<td>-</td>';
						}
					} 
				str+='</tr>';
			}  
	 			str+='<tr>';
	 				str+='<td>Total</td>';
	 				str+='<td>'+locTotal+'</td>';
	 				for(var i in result[0].subList1){
						if(result[0].subList1[i].grandTotal == 0){
							str+='<td>-</td>';
						}else{
							str+='<td>'+result[0].subList1[i].grandTotal+'</td>';
						}
	 				}
	 				for(var i in result[0].subList2){
						if(result[0].subList2[i].grandTotal == 0){
							str+='<td>-</td>';
						}else{
							str+='<td>'+result[0].subList2[i].grandTotal+'</td>';
						}  
	 				}   
	 		   str+='</tr>';
	 		str+='</tbody>';
 		 str+='</table>';
	 $('#grivenaceTableId').html(str);
	 $("#totalAlertCountId").html(locTotal);
	 $('#grievanceReportTableId').DataTable({
		 "order": [[ 1, "asc" ]]  
	 });  
}


 getAverageIssuePendingDays();
 function getAverageIssuePendingDays(){
	var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
	
 	var deptIds=[];
 	var sourceIds =[];
 	deptIds.push(deptId);  
	if(sourceId==0){
		sourceIds.push(1);
		sourceIds.push(2);
		sourceIds.push(3);
	}else if(sourceId==1){
		sourceIds.push(1);
	}else if(sourceId==2){
		sourceIds.push(2);
	}else if(sourceId==3){
		sourceIds.push(3);
	}
     var jobj = {
       deptIds :deptIds,
 	  sourceIds:sourceIds,
 	  fromDate : callCenterUserFDate,//2016-11-01
 	  toDate:callCenterUserTDate//2017-05-01
     }
     $.ajax({
       type : "POST",
       url  : "getAverageIssuePendingDaysAction.action",
       dataType: 'json',
       data: {task:JSON.stringify(jobj)},
     }).done(function(result){
     	var str ='';
 		if(result != null){
 			str +=''+result.count+'Days '+result.totalCount+'Hours';
 			 $("#averageIssueId").html(str);
 		}
     });
     
     }
function getDistrintInformation(){
	$("#dayWiseGrivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var rangeType=$("#dateRangeId").attr("value");
    var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
	var locationId=$("#selectDistrictId").val();
    var jsObj ={
		fromDate: callCenterUserFDate,                       
		toDateStr:callCenterUserTDate,  
		deptId:deptId,
		sourceId:sourceId,
		locationId:locationId,    
		rangeType:rangeType,             
		stateId:1
	}
	$.ajax({
		type:'GET',         
		url: 'getGrievanceReportDayWiseAction.action',
		data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildGrievanceReportDayWise(result,rangeType);
			}else{
				$("#dayWiseGrivenaceTableId").html('No Data Available.');
			}
	}); 
}	 
//on change media 	
 function getMediaInformation(){
	 getDistIdAndNameList();
	 getAverageIssuePendingDays();
	 getGrievanceReportDayWise();
	 getTotalAlertGroupByCategoryThenStatus();
	getCadreGreivienceEfficiency();	 
 $("#statusWiseAlertCntId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
 $("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var sourceId=$("#selectMediaId").val();
     var deptId=$("#selecDepartmentId").val();
	 var rangeType=$("#dateRangeId").attr("value");
 	var jobj = {
            fromDate: callCenterUserFDate,                       
 		   toDateStr:callCenterUserTDate,  
 		   deptId:deptId,
 		   sourceId:sourceId,
 		   rangeType:rangeType,
 		   stateId:1
     }
 	$.ajax({
 		 type : "GET",
 		 url  : "getGrievanceReportAction.action",
 		 dataType: 'json',
 		 data: {task:JSON.stringify(jobj)},
     }).done(function(result){
		 if(result != null && result.length > 0){
			buildGrievanceReport(result);
			buildLocationWiseGrivenacereportGraph(result);
		 }else{
			 $("#statusWiseAlertCntId").html('No Data Available');
			 $("#grivenaceTableId").html('No Data Available');
		 }

 	});
 }

//on dept change
 function getDepartmentInformation(){
	 getDistIdAndNameList();
	 getAverageIssuePendingDays();
	 getGrievanceReportDayWise();
	 getTotalAlertGroupByCategoryThenStatus(); 
	 getCadreGreivienceEfficiency()
	 $("#statusWiseAlertCntId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	 $("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var sourceId=$("#selectMediaId").val();
     var deptId=$("#selecDepartmentId").val();
	 var rangeType=$("#dateRangeId").attr("value");
	 var jobj = {
           fromDate: callCenterUserFDate,                       
 		  toDateStr:callCenterUserTDate,  
 		  deptId:deptId,
 		  sourceId:sourceId,
 		  rangeType:rangeType,   
 		  stateId:1
 	}
 	$.ajax({
        type : "GET",
        url  : "getGrievanceReportAction.action",
        dataType: 'json',
        data: {task:JSON.stringify(jobj)},
     }).done(function(result){
		if(result !=null && result.length>0){
			buildGrievanceReport(result);
			buildLocationWiseGrivenacereportGraph(result);
		}else{
			$("#statusWiseAlertCntId").html('No Data Available');
			$("#grivenaceTableId").html('No Data Available');
		}
 	});
 }
//on change daterangepicker
 function getTotalLocationWiseGrivenaceReport(){
	 getDistIdAndNameList();
	 getAverageIssuePendingDays();
	 getGrievanceReportDayWise();
	 getTotalAlertGroupByCategoryThenStatus(); 
	 getCadreGreivienceEfficiency();
 $("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
 $("#statusWiseAlertCntId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var sourceId=$("#selectMediaId").val();
     var deptId=$("#selecDepartmentId").val();
	 var rangeType=$("#dateRangeId").attr("value");
     var jsObj ={
 		fromDate:callCenterUserFDate,                         
 		toDateStr:callCenterUserTDate,
         sourceId :sourceId,  
         deptId:deptId,
 		rangeType:rangeType,
         stateId:1		 
     }
     $.ajax({
     type:'GET',         
     url: 'getGrievanceReportAction.action',
     data: {task :JSON.stringify(jsObj)}
     }).done(function(result){
 		if(result !=null && result.length>0){
			buildGrievanceReport(result);
			buildLocationWiseGrivenacereportGraph(result);
		}else{
			$("#statusWiseAlertCntId").html('No Data Available');
			$("#grivenaceTableId").html('No Data Available');
		}
 	});
 }
 //on click month week day btn
 $(document).on("click",".rangeTypeCls",function(){
	$("#selectDistrictId").val(0);  
	$("#dayWiseGrivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	$("#statusWiseAlertCntId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	$("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val(); 
	var rangeType=$(this).attr("attr_range_val");
	     
    var jsObj ={
		fromDate:callCenterUserFDate,                         
		toDateStr:callCenterUserTDate,
        sourceId :sourceId,  
        deptId:deptId,
		rangeType:rangeType,
        stateId:1                     
	}
	$.ajax({
		type:'GET',         
		url: 'getGrievanceReportAction.action',
		data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		getGrievanceReportDayWise();
		getTotalAlertGroupByCategoryThenStatus(); 
		if(result !=null && result.length>0){
			buildGrievanceReport(result);
			buildLocationWiseGrivenacereportGraph(result);
		}else{
			$("#statusWiseAlertCntId").html('No Data Available');
			$("#grivenaceTableId").html('No Data Available');
		}
	});
});
//Graph building
 function buildLocationWiseGrivenacereportGraph(result){
	var statusNamesArray =[];
	var statusIdNameArr=[];
 	    for(var i in result[0].subList1){
				 statusNamesArray.push(result[0].subList1[i].statusType);
				 statusIdNameArr.push({"y":result[0].subList1[i].grandTotal,"status":result[0].subList1[i].statusType,"id":result[0].subList1[i].statusTypeId});
 	    }
 Highcharts.chart('statusWiseAlertCntId', {
             colors: ['#FFCF2C'],
             chart: {
                 backgroundColor:'transparent',
                 type: 'column'
             },
             title:{
                 text: null,
                 align: 'left'
             },
             xAxis: {
                 min: 0,
                 gridLineWidth: 0,
                 minorGridLineWidth: 0,         
                 categories: statusNamesArray,
             },
             yAxis: {
                 lineWidth: 0,
                 gridLineWidth: 0,
                 minorGridLineWidth: 0,
                 allowDecimals: true,
                 title: {
                     enabled: false
                 },
                 stackLabels: {
                     enabled: false,
                     style: {
                         fontWeight: 'bold',
                         color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                     }
                 }
             },

             tooltip:{
                 enabled: true,
 				useHtml:true,
 				pointFormat: '<span>{point.y}</span>'
             },
             legend: {
                 enabled: false
             },
             plotOptions: {
                 series: {
                     borderWidth: 0,
                     dataLabels: {
                         enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return this.y;
								}
							}

                     },
					 point: {
						events: {
							 click: function () {
								getAlertStatusWise(this.id,this.status);
							 }
						 }
					 }
                 }
             },
             exporting: {
                 buttons: {
                     contextButton: {
                         enabled: false
                     },  
                 }
             },
             series: [{
 				colorByPoint: false,
                 lineWidth: 1,
                 data:statusIdNameArr     
             }],
         });
 }
 function getAlertStatusWise(statusId,status){
	$("#totalAlertDistricTableId").html("");   
	$("#grievanceDtlsModalId").modal("show");     
	$("#grevinceDetailsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');    
	var sourceId=$("#selectMediaId").val();
	var deptId=$("#selecDepartmentId").val();
	var rangeType=$("#dateRangeId").attr("value");  
	var jobj = {
	  fromDate: callCenterUserFDate,                       
	  toDateStr:callCenterUserTDate,  
	  deptId:deptId,
	  sourceId:sourceId,                                      
	  stateId:1,
	  locationId:0,
	  statusId : statusId      
	  }
	$.ajax({    
	  type : "POST",
	  url  : "getGrievanceReportBasedOnLocationAndStatus.action",  
	  dataType: 'json',
	  data: {task:JSON.stringify(jobj)},
	}).done(function(result){
		buildAlertStatusWise(result,status);      
	});
 }
 function buildAlertStatusWise(result,status){
	$("#grivancHeadinId").html('<h3>Grievance Details</h3>');
	$("#grivenaceModalHeedingId").html('<h4 class="modal-title" >'+status+'</h4>');
	var str='';  
	
	str+='<table id="alertIdListTableId" class="table  table-bordered" cellspacing="0" width="100%">';
                    str+='<thead>';
                     str+='<tr>';
				     str+=' <th>Complaint Id</th>';
					 str+=' <th>Date</th>';
				     str+=' <th>Location</th>';
				     str+=' <th>Title</th>';
					 str+=' <th>Related to</th>';
				     str+=' <th>Problem</th>';
					 str+='<th>Status</th>';
                     str+='</tr>';
                   str+=' </thead>';
                   str+='<tbody>';
				   
				   for(var i in result){       
					 str+='<tr>';
					 str+='<td style="cursor:pointer;" class="getAlertDtls" attr_alert_id="'+result[i].id+'" attr_alert_status="'+result[i].status+'">'+result[i].id+'</td>';
					 str+='<td>'+result[i].createdDate+'</td>';
					 if(result[i].location != null && result[i].location.length >0){
						str+='<td>'+result[i].location+'</td>';
					 }else{
						 str+='<td>-</td>';
					 }
					 if(result[i].title != null &&result[i].title.length >0){
						str+='<td>'+result[i].title+'</td>';
					 }else{
						str+='<td>-</td>'; 
					 }
					  if(result[i].relatedTo != null &&result[i].relatedTo.length >0){
					   str+='<td>'+result[i].relatedTo+'</td>';
					  }else{
						  str+='<td>-</td>'; 
					  }
					   if(result[i].problem != null &&result[i].problem.length >0){
					 str+='<td>'+result[i].problem+'</td>';
					   }else{
						    str+='<td>-</td>'; 
					   }
					    if(result[i].status != null &&result[i].status.length >0){
					 str+='<td>'+result[i].status+'</td>';
						}else{
							    str+='<td>-</td>'; 
						}
					 str+='</tr>';
				    }
				   str+='</tbody>';    
				   str+='</table>';
				   
				   $("#grevinceDetailsId").html(str);
				   $("#alertIdListTableId").dataTable();
}
onLoadInitialisations();        
function onLoadInitialisations(){                     
	$(document).on("click",".getAlertDtlsCls",function(){
		$("#totalAlertDistricTableId").html("");  
		$("#grievanceDtlsModalId").modal("show");     
		$("#grevinceDetailsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
		var rangeType=$("#dateRangeId").attr("value");
		var locationId = $(this).attr("attr_location_id");
		var group = $(this).attr("attr_group_type");
		var statusId = $(this).attr("attr_status_id");
		var pattern = $(this).attr("attr_pattern");
		 
		var sourceId=$("#selectMediaId").val();
		var deptId=$("#selecDepartmentId").val(); 
		//alert(locationId+":"+statusId+":"+group+":"+pattern+":"+rangeType);
		if(locationId==undefined){
		   locationId=0;      
		}
		if(statusId==undefined){  
		   statusId=0;      
		}
		if(group==undefined){
		   group="";
		}
		if(pattern==undefined){
		   pattern="";     
		}
		 
		var jobj = {
		  fromDate: callCenterUserFDate,                       
		  toDateStr:callCenterUserTDate,  
		  deptId:deptId,
		  sourceId:sourceId,                                      
		  stateId:1,
		  locationId:locationId,
		  statusId : statusId,      
		  group:group,
		  pattern:pattern,
		  rangeType:rangeType        
		  
		}
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportBasedOnLocationAction.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},
		}).done(function(result){
		  var str ='';
		  if(result != null){
				if(group=="day"){ 
				buildTotalAlertDistrictTable(result);
				}
				buildGrivenceDetailsTable(result,group);
		  }
		});
     
	});
	
	$(document).on("click",".rangeTypeCls",function(){
		$("#dateRangeId").attr("value",$(this).attr("attr_range_val"));
	});
	$(document).on("click",".getAlertDtlsOnLocCls",function(){
		$("#grevinceDetailsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');  
		var locationId = $(this).attr("attr_location_id");
		var statusId = $(this).attr("attr_status_id");
		var fromDate = $(this).attr("attr_from_date");
		var toDate = $(this).attr("attr_to_date");  
		var sourceId=$("#selectMediaId").val();
        var deptId=$("#selecDepartmentId").val();
	    var rangeType=$("#dateRangeId").attr("value");
		var jobj = {
		  fromDate: fromDate,                       
		  toDateStr:toDate,  
		  deptId:deptId,
		  sourceId:sourceId,                                      
		  stateId:1,
		  locationId:locationId,
		  statusId : statusId,      
		  rangeType:rangeType        
		  }
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportBasedOnLocationAndStatus.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			buildGrivenceDetailsTableOld(result);    
		});
	});
	$(document).on("click",".getAlertDtls",function(){
		var alertId = $(this).attr("attr_alert_id");  
		rightSideExpandView(alertId);  
	});
 
	$(document).on("click",".bellowLvlLocCls",function(){
		$("#bellowLvlLocId").modal("show");
		var deptId=$("#selecDepartmentId").val();
		var sourceId=$("#selectMediaId").val();
		var locationId = $(this).attr("attr_location_id");
		var locationName = $(this).attr("attr_location_name");
		var groupType = $(this).attr("attr_group_type");  
		$("#bellowLvlLocationId").html(locationName);    
		var jobj = {
		  fromDate: callCenterUserFDate,                          
		  toDateStr:callCenterUserTDate, 
		  deptId:deptId,
		  sourceId:sourceId,
		  rangeType:"",		  
		  stateId:1,
		  LocationId:locationId,        
		  groupType : groupType   
		  }
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportForBellowLocationAction.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},    
		}).done(function(result){
			buildGrievanceReportForBellowLocation(result,locationId,locationName,groupType);  
		});
	});
	$(document).on("click",".panchayatDataCls",function(){
		var deptId=$("#selecDepartmentId").val();
		var sourceId=$("#selectMediaId").val();
		var locationId = $(this).attr("attr_location_id");
		var groupType = $(this).attr("attr_group_type");  
		var positionValue = $(this).attr("attr_position");  
		   
		var jobj = {
		  fromDate: callCenterUserFDate,                          
		  toDateStr:callCenterUserTDate, 
		  deptId:deptId,
		  sourceId:sourceId,
		  rangeType:"",		  
		  stateId:1,
		  LocationId:locationId,        
		  groupType : groupType   
		  }   
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportForBellowLocationAction.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},    
		}).done(function(result){
			buildGrievanceReportForPanchayat(result,positionValue,locationId,groupType);         
		});
	});
	$(document).on("click",".bellowLvlCls",function(){
		$("#grievanceDtlsModalId").modal("show");
		
		$("#totalAlertDistricTableId").html('');     
		$("#grevinceDetailsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');    
		var deptId=$("#selecDepartmentId").val();
		var sourceId=$("#selectMediaId").val();
		var locationId = $(this).attr("attr_location_id");
		var statusId = $(this).attr("attr_status_id"); 
		var areaType = $(this).attr("attr_area_type"); 
		var groupType = $(this).attr("attr_group_type"); 
		var status = $(this).attr("attr_status"); 
		var jobj = {
		  fromDate: callCenterUserFDate,                          
		  toDateStr:callCenterUserTDate, 
		  deptId:deptId,
		  sourceId:sourceId, 
		  stateId:1,
		  LocationId:locationId,
		  statusId:statusId,
		  areaType:areaType,
		  groupType:groupType   
		}   
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportDtlsForBellowLocationAction.action",  
		  dataType: 'json',       
		  data: {task:JSON.stringify(jobj)},    
		}).done(function(result){
			buildAlertStatusWise(result,status);  
		});
	});
	$(document).on("click","#statusId",function(){
		var alertId = $(this).attr("attr_alert_id");
		getAlertStatusHistory(alertId);
	});
	$(document).on("click",".articleDetailsCls",function(){
		var articleId= $(this).attr("attr_articleId");
		$("#alertManagementPopup1").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'  
		});
		getTotalArticledetails(articleId);  
	});
	$(document).on("click",".closeSecondModal",function(){
		setTimeout(function(){
			$("body").addClass("modal-open");
		},1000);             
	});
	$(document).on("click",".dtlsCloseCls",function(){
		setTimeout(function(){
			$("body").addClass("modal-open");
		},1000);                    
	});
	$(document).on("click","#totalAlertCountId",function(){
		getAlertStatusWise(0,"All Status");      
	});
	$(document).on("click",".getAlertDtlsOnDateWise",function(){
		$("#totalAlertDistricTableId").html("");  
		$("#grivenaceModalHeedingId").html("");    
		$("#grievanceDtlsModalId").modal("show");     
		$("#grevinceDetailsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
		var locationId = $("#selectDistrictId").val();
		var statusId = $(this).attr("attr_status_id");
		var fromDate = $(this).attr("attr_from_date");
		var toDate = $(this).attr("attr_to_date");  
		var sourceId=$("#selectMediaId").val();
        var deptId=$("#selecDepartmentId").val();
	    var rangeType=$("#dateRangeId").attr("value");
		var jobj = {
		  fromDate: fromDate,                       
		  toDateStr:toDate,  
		  deptId:deptId,
		  sourceId:sourceId,                                      
		  stateId:1,
		  locationId:locationId,
		  statusId : statusId,      
		  rangeType:rangeType        
		  }
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportBasedOnLocationAndStatus.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			  buildGrivenceDetailsTableOld(result);
		});
	});
	$(document).on("click",".getAlertDtlsOnCategoryWise",function(){
		$("#totalAlertDistricTableId").html("");  
		$("#grivenaceModalHeedingId").html("");
		$("#grevinceDetailsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
		$("#grievanceDtlsModalId").modal("show");     
		var locationId = $(this).attr("attr_location_id");
		var statusId = $(this).attr("attr_status_id");
		var sourceId=$(this).attr("attr_source_id");
        var deptId=$("#selecDepartmentId").val();
	    var rangeType=$("#dateRangeId").attr("value");
		var jobj = {
		  fromDate: callCenterUserFDate,                       
		  toDateStr:callCenterUserTDate,  
		  deptId:deptId,
		  sourceId:sourceId,                                      
		  stateId:1,
		  locationId:locationId,
		  statusId : statusId,      
		  rangeType:rangeType        
		  }
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportBasedOnLocationAndStatus.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			  buildGrivenceDetailsTableOld(result);
		});
	});
}
//swadhin   
function buildGrievanceReportForBellowLocation(result,locationId,locationName,groupType){
	var str = '';
	str+='<div class="table-responsive">';
		str+='<table class="table table-inr-x table-bordered" style="border-collapse:collapse;">';
			str+='<thead>';
				str+='<tr>';
					if(groupType=="tehsil"){
						str+='<th>Mandal</th>';
					}else if(groupType=="panchayat"){
						str+='<th>Panchayat</th>';  
					}else{
						str+='<th>Location Name</th>';
					}
					
					str+='<th>Total</th>';
					for(var i in result[0].subList1){       
						str+='<th>'+result[0].subList1[i].statusType+'</th>';
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				if(result[i].name == "OTHER"){
					str+='<tr>';
					str+='<td>'+result[i].name+'</td>'; 
				}else{
					str+='<tr data-toggle="collapse" data-target="#demo'+i+'" class="accordion-toggle">';
					str+='<td><button class="btn btn-default btn-xs panchayatDataCls" attr_position="'+i+'" attr_location_id="'+result[i].id+'" attr_group_type="panchayat"><span class="glyphicon glyphicon-plus-sign " ></span></button>'+result[i].name+'</td>'; 
				}
				if(result[i].name == "OTHER"){
					str+='<td class="bellowLvlCls" attr_area_type="tehsil" attr_group_type="district" attr_location_id="'+locationId+'" attr_status_id="0" attr_status="All Status"><a href="#">'+result[i].totalAlertCnt+'</a></td>';  
				}else{
					str+='<td class="bellowLvlCls" attr_area_type="tehsil" attr_group_type="tehsil" attr_location_id="'+result[i].id+'" attr_status_id="0" attr_status="All Status"><a href="#">'+result[i].totalAlertCnt+'</a></td>';  
				}
					 
					var len = result[i].subList1.length;
					len=parseInt(len)+2;
					for(var j in result[i].subList1){
						if(result[i].subList1[j].totalAlertCnt != 0){        
							if(result[i].name == "OTHER"){
								str+='<td class="bellowLvlCls" attr_area_type="tehsil" attr_group_type="district" attr_location_id="'+locationId+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_status="'+result[i].subList1[j].statusType+'"><a href="#">'+result[i].subList1[j].totalAlertCnt+'</a></td>';
							}else{
								str+='<td class="bellowLvlCls" attr_area_type="tehsil" attr_group_type="tehsil" attr_location_id="'+result[i].id+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_status="'+result[i].subList1[j].statusType+'"><a href="#">'+result[i].subList1[j].totalAlertCnt+'</a></td>';
							}
						}else{
							str+='<td>-</td>';
						}      
					}
				str+='</tr>';
				str+='<tr>';
					str+='<td colspan="'+len+'" class="hiddenRow">';
						str+='<div class="accordian-body collapse" id="demo'+i+'">';
						str+='</div>';
					str+='</td>';
				str+='</tr>';
			}	
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#tehsilTableId").html(str);
	  
}
function buildGrievanceReportForPanchayat(result,positionValue,locationId,groupType){
	var str = '';
	str+='<table class="table table-inr">';
		str+='<thead>';
			str+='<tr>';
				if(groupType=="panchayat"){
					str+='<th>Panchayat</th>';  
				}else{
					str+='<th>Location Name</th>';
				}
				str+='<th>Total</th>';
				for(var i in result[0].subList1){       
					str+='<th>'+result[0].subList1[i].statusType+'</th>';
				}
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				if(result[i].name == "OTHER"){
					str+='<td class="bellowLvlCls" attr_area_type="panchayat" attr_group_type="tehsil" attr_location_id="'+locationId+'" attr_status_id="0" attr_status="All Status"><a href="#">'+result[i].totalAlertCnt+'</a></td>';
				}else{
					str+='<td class="bellowLvlCls" attr_area_type="panchayat" attr_group_type="panchayat" attr_location_id="'+result[i].id+'" attr_status_id="0" attr_status="All Status"><a href="#">'+result[i].totalAlertCnt+'</a></td>';
				}
				
				for(var j in result[i].subList1){           
					if(result[i].subList1[j].totalAlertCnt != 0){
						if(result[i].name == "OTHER"){
							str+='<td class="bellowLvlCls" attr_area_type="panchayat" attr_group_type="tehsil" attr_location_id="'+locationId+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_status="'+result[i].subList1[j].statusType+'"><a href="#">'+result[i].subList1[j].totalAlertCnt+'</a></td>';
						}else{
							str+='<td class="bellowLvlCls" attr_area_type="panchayat" attr_group_type="panchayat" attr_location_id="'+result[i].id+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_status="'+result[i].subList1[j].statusType+'"><a href="#">'+result[i].subList1[j].totalAlertCnt+'</a></td>';
						}
						
					}else{
						str+='<td>-</td>';
					}      
				}
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	$("#demo"+positionValue).html(str);          
}

function buildAlertData(result){
		var docName = '';
		var extName =[];
		$("#tourDocHeadingId").html("<h5 style='color:#FFFFFF;font-size:14px;'>ALERT TITLE</h5><h5 class='text-capital m_top10' style='color:#000'>"+result[0].title+"</h5>");
		$("#cdrModelId").html("<h5 class='text-muted headingColorStyling'>ALERT DESCRIPTION</h5>");
		$("#alertDestId").html("<p style='border: 1px solid rgb(211, 211, 211); padding: 6px;'>"+result[0].desc+"</p>");
		$("#sourceHeadingId").html("<h5 class='text-muted headingColorStyling'>ALERT SOURCE</h5>");
		$("#headingNameId").html("<p style='border: 1px solid rgb(211, 211, 211); padding: 10px;'>"+result[0].alertSource+"</p>");
		
		if(result[0].documentList != null && result[0].documentList.length >= 1){
			$("#alertDocHeadingId").html("<h5  class='text-muted headingColorStyling'>ALERT DOCUMENTS</h5>");
			var docStr = '';
			docStr+='<ul>';
			for(var i in result[0].documentList){
				docName = result[0].documentList[i];
				extName = docName.split(".");
				if(result[0].documentNameList[i].search('#') != -1 || result[0].documentNameList[i].search('u0') != -1){
					var randumNum = result[0].documentList[i].substring(result[0].documentList[i].indexOf("/")+1,result[0].documentList[i].lastIndexOf("."));      
					docStr+='<li id="document0'+i+'"><a href="/Reports/'+result[0].documentList[i]+'" target="_blank">'+randumNum+'.'+extName[1]+'</a></li>';  
				}else{
					docStr+='<li id="document0'+i+'"><a href="/Reports/'+result[0].documentList[i]+'" target="_blank">'+result[0].documentNameList[i]+'.'+extName[1]+'</a></li>';  
				}
				
			}
			docStr+='</ul>';
			$("#alertDocId").html(docStr);    
		}
		if(result[0].imageUrl != null && result[0].imageUrl.length > 1){    
			$("#alertAttachTitId").html("<h5  class='text-muted headingColorStyling'>ALERT ATTACHMENTS</h5>");
			var imgStr = '';
			imgStr+='<ul class="list-inline imageUrlUlCls" style="border: 1px solid rgb(211, 211, 211); padding:5px;">';
			imgStr+='<li><img src="http://mytdp.com/NewsReaderImages/'+result[0].imageUrl+'" style="width: 90px; height: 90px;cursor:pointer;" class="articleImgDetailsCls" attr_articleId="'+result[0].alertCategoryTypeId+'"></img></li>';
			imgStr+='</ul> '; 
			$("#alertAttachImgId").html(imgStr);  
		}
		var str='';
		var invCandCnt = 0;
		if(result[0].subList.length > 0){
			for(var i in result){
				for(var j in result[i].subList){
					if(result[i].subList[j].name != null && result[i].subList[j].name.length > 1){    
						invCandCnt+=1;
					}
				}    
			}
			str+='<h5 class="text-muted text-capital headingColorStyling">Involved Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;'+invCandCnt+'</h5>';           
			str+='<ul class="list-inline assignedCandidatesUl1">';     
			for(var i in result){
				for(var j in result[i].subList){   
					if(result[i].subList[j].name != null && result[i].subList[j].name.length > 1){
						str+='<li>';      
							str+='<p style="color: rgb(0, 0, 0);"><b>'+result[i].subList[j].name+'</b></p>';
							if(result[i].subList[j].mobileNo.length <= 1  || result[i].subList[j].mobileNo == null){
							}else{
								str+='<p><i> - </i>'+result[i].subList[j].mobileNo+'</p>';      
							}  
							if(result[i].subList[j].committeePosition != null){
								str+='<p><i> - </i>'+result[i].subList[j].committeePosition+'</p>';  
							}     
						str+='</li>';      
					}
				}    
			}
			str+='</ul>';      
			
			$("#alertInvolvedCandidates").html(str);       
		}else{
			str+='<h5 class="text-muted text-capital headingColorStyling">Involved Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>'; 
			$("#alertInvolvedCandidates").html(str);        
		}
		$(".assignedCandidatesUl1").slick({          
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,    
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 5,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 2,
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
				
			  ]  
		}); 
	}
	function getAlertAssignedCandidates(alertId){
		GlobalAlertData = [];
		var jsObj ={
			alertId  : alertId,    
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertAssignedCandidatesAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){   
				buildAlertAssignedCandidates(result);  
			}else{
				var str = '';
				str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>';  
				$("#alertAssignedCandidates").html(str);    
			}
		});
	}
	
	function buildAlertAssignedCandidates(result)
	{
	var str='';
	if(result[0].subList.length > 0){  
		str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;'+result[0].subList.length+'</h5>';
		str+='<ul class="list-inline assignedCandidatesUl">';
		for(var i in result)
		{
			for(var j in result[i].subList)
			{
				str+='<li>';
					str+='<p style="color:#000"><b>'+result[i].subList[j].name+'</b></p>';
					if(result[i].subList[j].committeePosition == null || result[i].subList[j].committeePosition.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].committeePosition+'</i></p>';
					}
					if(result[i].subList[j].mobileNo == null || result[i].subList[j].mobileNo.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].mobileNo+'</i></p>';
					}
					if(result[i].subList[j].locationVO.districtName == null || result[i].subList[j].locationVO.districtName.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].locationVO.districtName+'</i></p>';
					}  
				str+='</li>';
			}
		}
		str+='</ul>';
		
		$("#alertAssignedCandidates").html(str);
	}else{
		str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>';  
		$("#alertAssignedCandidates").html(str);                    
	}
	
	$(".assignedCandidatesUl").slick({
		 slide: 'li',
		 slidesToShow: 4,
		 slidesToScroll: 3,
		 infinite: false,
		  responsive: [
			{
			  breakpoint: 1024,
			  settings: {
				slidesToShow: 5,
				slidesToScroll: 3,
				infinite: false,
				dots: false
			  }
			},
			{
			  breakpoint: 800,
			  settings: {
				slidesToShow: 3,
				slidesToScroll: 2
			  }
			},
			{
			  breakpoint: 600,
			  settings: {
				slidesToShow: 2,
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
			
		  ]
	});  
}
function getAlertStatusCommentsTrackingDetails(alertId,alertStatus){  
		var jsObj={
			alertId:alertId,
			task:""
		}
		$.ajax({  
			type : 'GET',
			url : 'getAlertStatusCommentsTrackingDetails.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result != null)           
				buildAlertStatusCommentsTrackingDetails(result,alertStatus);    
		});
	}
	
function buildAlertStatusCommentsTrackingDetails(result,alertStatus)
{
	var docName = '';
	var extName = [];
	$("#alertStatusDiv").html("<h4 class='text-muted headingColorStyling' style='font-size:15px;'>ALERT STATUS</h4>");          
	if(result != null && result.length > 0){  
		var length = result.length;
		length = length - 1;
		var str='';  
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';  
			for(var i in result)
			{
			   if(result[i].currentSts == result[i].status)  
			   {  
					str+='<li class="m_top10" role="presentation"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-hourglass pull-right" style="font-size: 22px;color: #777 !important;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'</span></a></li>';
				}else{
					str+='<li role="presentation" class="active m_top10"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-ok pull-right" style="font-size: 22px;color: #777 !important;margin-left: 15px;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'<span></a></li>';
				}        
			}
			str+='</ul>';
			str+='<div class="tab-content alertComment">';
				for(var i in result)
				{
				   if(result[i].currentSts == result[i].status)  
					{
						str+='<div role="tabpanel" class="tab-pane active" id="commentStatus'+i+'">';
					}else{
						str+='<div role="tabpanel" class="tab-pane " id="commentStatus'+i+'">';
					}
					for(var j in result[i].sublist2)
					{
						str+='<div class="row m_top10">';
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
										str+='<div class="arrow_box_left" style="background: #f5f3f8 none repeat scroll 0 0 !important;">';
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE&nbsp;&nbsp;:&nbsp;</span>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span> <span>'+result[i].sublist2[j].sublist[k][l].cadreName+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 18px;">|</span>';
												}   
												str+='&nbsp;&nbsp;&nbsp;&nbsp; <small style="font-size:11px">'+result[i].sublist2[j].sublist[k][0].timeString+'</small>';
												str+='</p>';  
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p class="m_top10">'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
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
												str+='<hr style="margin-top:20px;border-color:#a792d2 -moz-use-text-color -moz-use-text-color;"/>';
											str+='</div>';   
										}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					}           
				str+='</div>';
				}
			str+='</div>';
		str+='</div>';
		$("#alertCommentsDiv").html(str);
	}else{
		var str = '';
		var statusArr = {"1":"Pending","2":"Notified","3":"Action In Progess","4":"Completed","5":"Unable To Resolve","6":"Action Not Required","7":"Duplicate"};            
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';
		for(var i = 1 ; i <= 7 ; i++){
			if(alertStatus == statusArr[i]){
				str+='<li class="m_top10" role="presentation" style="pointer-events: none;"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+statusArr[i]+'<span class="glyphicon glyphicon-ok"></span><br/></a></li>';
			}else{
				str+='<li class="m_top10" role="presentation" style="pointer-events: none;"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+statusArr[i]+'<br/></a></li>';
			}
		}
		str+='</ul>';       
		str+='<div class="tab-content alertComment">';    
		$("#alertCommentsDiv").html(str);       
	}//glyphicon glyphicon-ok
	//alertStatus
}
function getVerificationDtls(alertId){  
		var jsObj={
			alertId:alertId
		}
        $.ajax({
        type : 'POST',
        url : 'getAlertVerificationDetailsAction.action',
        dataType : 'json',
        data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#converSationDtlsDivId").html(' ');
			buildAlertVerificationStatusRlst(result);
		});
	}
	function buildAlertVerificationStatusRlst(result){
		var str = '';
		if(result.conversationList != null && result.conversationList.length > 0){
			$("#alertVerificationDiv").html("<h4 class='text-muted verifyHeadingColorStyling' style='font-size:15px;'>VERIFICATION STATUS-"+result.actionTypeStatus+"</h4>");  
			for(var i in result.conversationList){
				str+='<p class="text-capital panelTitleFont m_top20 verifyHeadingColorStyling" style="font-size:12px;">'+result.conversationList[i].heading+'</p>';  
				if(result.conversationList[i].comments != null && result.conversationList[i].comments.length > 0){
					str+='<p style="border: 1px solid rgb(211, 211, 211); padding: 6px;">'+result.conversationList[i].comments+'</p>';     
				}
				var documentList = result.conversationList[i].documentList;
				if(documentList != null && documentList.length > 0){
					str+='<p style="font-weight:bold;font-size:12px;" class="text-capital m_top10 panelTitleFont headingColorStyling">Attachments</p>';
					str+='<ul class="attachmentsBlock">';
					var order = 0;
					for(var k in documentList){
						order = order+1;
						var fullName = documentList[k];
						var nameArr = fullName.split(".");
						var type = nameArr[1];  
						var orderStr='';
						if(k<9){
							orderStr ="0"+order;
						}else{
							orderStr = order;  
						}
						var attachment = orderStr+'&nbspAttachment.'+type;
						str+='<li id="showAlertVerificationPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;"><i class="glyphicon glyphicon-paperclip"></i><span class="border"> '+attachment+' </span></li>';
					}
					str+='</ul>';
				}
				if(result.conversationList[i].name != null && result.conversationList[i].name.length > 0){
					str+='<p class="text-right" style="color:#7155D6;font-size:12px;">Created By:'+result.conversationList[i].name+'('+result.conversationList[i].updateTime+'&nbsp'+result.conversationList[i].time+')</p>';     
				}  
			}
			str+='<hr class="m_top10" style="border-top: 1px solid #ccc;">';
			$("#alertVerificationDtlsDiv").html(str);
		}
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

$(document).on("click",".articleImgDetailsCls",function(){
	var articleId= $(this).attr("attr_articleId");
	getTotalArticledetails(articleId);
});
function getTotalArticledetails(articleId){
	
	$("#alertManagementPopupBody1,#alertManagementPopupHeading").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		 // url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
	}).then(function(results){
		var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","Muncipality/Corporation/GHMC/GVMC","Ward"];
		var result = results[0];
		var str = '';
		var heading = '';
		heading+='<h4 class="modal-title" id="myModalLabel">';
			heading+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
			heading+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
		heading+='</h4>';
		str+='<div class="row">';
			str+='<div class="col-md-12">';
				str+='<img class="mainImage"  src="http://mytdp.com/NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;width:100%;" alt="Img Title"/>';
			str+='</div>';
			str+='<div class="col-md-12 m_top10">';
				str+='<h4 class="panel-title text-success">Description</h4>';
				str+='<p class="m_0 f_14">'+result.description+'</p>';
			str+='</div>';
			str+='<div class="col-md-12">';
			if( result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
				/* Candidate*/
				str+='<div class="row ">';
					str+='<div class="col-md-6">';
						str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
								str+='<h4 class="panel-title">FROM WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* From Table*/
								if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
									for( var j in result.subList[i].fromList){
										str+='<table class="table table-bordered m_top10">';
											str+='<tr>';
												if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
												str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
												}
												str+='<td><img class="img-circle" src="images/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td colspan="2">';
												var candidataExist = false;
												if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
												candidataExist = true; 
												str+=''+result.subList[i].fromList[j].candidateName;
												}
												if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
												candidataExist = true; 
												str+=' ('+result.subList[i].fromList[j].designation + ")";
												}
												if(!candidataExist){
												str+=' - ';
												}
												str+='</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td colspan="2">';
													if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
														str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
													}else{ 
														str+='<p class="m_0">Impact Level : - </p>';	
													}
													if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
														str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
													}else{ 
														str+='<p class="m_0">Category : - </p>';	
													}
													if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
														str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
													}else{ 
														str+='<p class="m_0">News Activity : - </p>';	
													}
													if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
														str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
													}else{ 
														str+='<p class="m_0">News type : - </p>';	
													}
													if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
													if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
														str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
													}else{ 
														str+='<p class="m_0">News Related : - </p>';	
													}
													if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
														str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
													}else{ 
														str+='<p class="m_0">Priority : - </p>';	
													}
													if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
														str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
													}else{ 
														str+='<p class="m_0">Solution : - </p>';	
													}
													}
												str+='</td>';
											str+='</tr>';
										str+='</table>';
									}
								}
							str+='</div>';//panel-body
						str+='</div>';//panel
					str+='</div>';//colmd6
					str+='<div class="col-md-6">';
						str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
								str+='<h4 class="panel-title">TO WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
							/* TO Table*/
							if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
								for( var j in result.subList[i].toList){
									str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
											if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
												str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
											}else{
												str+='<td> - </td>';
											}
												str+='<td><img class="img-circle" src="images/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
											str+='<td colspan="2">';
											var candidataExist = false;
											if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
											candidataExist = true; 
												str+=''+result.subList[i].toList[j].candidateName;
											}
											if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
											candidataExist = true; 
												str+=' ('+result.subList[i].toList[j].designation + ")";
											}
											if(!candidataExist){
												str+=' - ';
											}
											str+='</td>';
										str+='</tr>';
										str+='<tr>';
											str+='<td colspan="2">';

												if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
													str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
												}else{ 
													str+='<p class="m_0">Impact Level : - </p>';	
												}

												if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
													str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
												}else{ 
													str+='<p class="m_0">Category : - </p>';	
												}
												if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
													str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
												}else{ 
													str+='<p class="m_0">News Activity : - </p>';	
												}
												if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
													str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
												}else{ 
													str+='<p class="m_0">News type : - </p>';	
												}
												if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){

												if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
													str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
												}else{ 
													str+='<p class="m_0">News Related : - </p>';	
												}
												if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
													str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
												}else{ 
													str+='<p class="m_0">Priority : - </p>';	
												}
												if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
													str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
												}else{ 
													str+='<p class="m_0">Solution : - </p>';	
												}
												}
											str+='</td>';
										str+='</tr>';
									str+='</table>';
								}
							}

							str+='</div>';//panelbody
						str+='</div>';//panel
					str+='</div>';//colmd6

				str+='</div>';//row
				}
			}

			str+='</div>';//colmd12
		str+='</div>';//row
		/* Article Scope Location */
		str+='<div class="row">';
			str+='<div class="col-md-12">';
				str+='<div class="panel panel-default panelArticleGroup">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<table class="table table-condensed">';
							str+='<tr>';
								str+='<td>Impact Scope : </td>';
								if(result.impactScopeId!=null){
									str+='<td>'+obj[result.impactScopeId]+'</td>';
								}else{
									str+='<td> - </td>';
								}
							str+='</tr>';
							str+='<tr>';
								str+='<td>Location : </td>';
								if(result.scopeLocation!=null){
									str+='<td>'+result.scopeLocation+'</td>';
								}else{
									str+='<td> - </td>';
								}
							str+='</tr>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="row">';
			/*Lnking*/
			str+='<div class="col-md-6">';
				str+='<div class="panel panel-default panelArticleGroup">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
					str+='</div>';
					str+='<div class="panel-body">';
						if( result.linkedList != null && result.linkedList.length > 1){
							str+='<div class="row">';
								for( var i in result.linkedList){
									if(result.linkedList[i].articleId !=articleId ){
										str+='<div class="col-md-4" style="margin-top:5px;">';
											str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="http://mytdp.com/NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;cursor:pointer"/>';
										str+='</div>';
									}
								}
							str+='</div>';
						}else{
							str+="<h5> No Linked Articles Available </h5>";
						}

					str+='</div>';
				str+='</div>';
			str+='</div>'; 
		str+='</div>';

		$("#alertManagementPopupBody1").html(str);
		$("#alertManagementPopupHeading").html(heading)
	});    
}

function buildGrivenceDetailsTable(result,group){
	 $("#grivancHeadinId").html('<h3>Grievance Details</h3>');
	 if(group=="day"){
		
		$("#grivenaceModalHeedingId").html('<h4 class="modal-title" >'+result[0].name+'</h4><span>'+result[0].fromDateStr+'-'+result[0].toDateStr+'</span>');
	 }else{
		 $("#grivenaceModalHeedingId").html('<h4 class="modal-title" >'+result[0].name+'</h4>');
	 }
  
	var str='';
	
	str+='<table id="alertIdListTableId" class="table  table-bordered" cellspacing="0" width="100%">';
                    str+='<thead>';
                     str+='<tr>';
				     str+=' <th>Complaint Id</th>';
					 str+=' <th>Date</th>';
				     str+=' <th>Location</th>';
				     str+=' <th>Title</th>';
					 str+=' <th>Related to</th>';
				     str+=' <th>Problem</th>';
					 str+='<th>Status</th>';
                     str+='</tr>';
                   str+=' </thead>';
                   str+='<tbody>';
				   
				   for(var i in result[0].alertCoreDashBoardVOs){
					 str+='<tr>';
					 str+='<td style="cursor:pointer;" class="getAlertDtls" attr_alert_id="'+result[0].alertCoreDashBoardVOs[i].id+'" attr_alert_status="'+result[0].alertCoreDashBoardVOs[i].status+'">'+result[0].alertCoreDashBoardVOs[i].id+'</td>';
					 str+='<td>'+result[0].alertCoreDashBoardVOs[i].createdDate+'</td>';
					 if(result[0].alertCoreDashBoardVOs[i].location != null && result[0].alertCoreDashBoardVOs[i].location.length >0){
						str+='<td>'+result[0].alertCoreDashBoardVOs[i].location+'</td>';
					 }else{
						 str+='<td>-</td>';
					 }
					 if(result[0].alertCoreDashBoardVOs[i].title != null &&result[0].alertCoreDashBoardVOs[i].title.length >0){
						str+='<td>'+result[0].alertCoreDashBoardVOs[i].title+'</td>';
					 }else{
						str+='<td>-</td>'; 
					 }
					  if(result[0].alertCoreDashBoardVOs[i].relatedTo != null &&result[0].alertCoreDashBoardVOs[i].relatedTo.length >0){
					   str+='<td>'+result[0].alertCoreDashBoardVOs[i].relatedTo+'</td>';
					  }else{
						  str+='<td>-</td>'; 
					  }
					   if(result[0].alertCoreDashBoardVOs[i].problem != null &&result[0].alertCoreDashBoardVOs[i].problem.length >0){
					 str+='<td>'+result[0].alertCoreDashBoardVOs[i].problem+'</td>';
					   }else{
						    str+='<td>-</td>'; 
					   }
					    if(result[0].alertCoreDashBoardVOs[i].status != null &&result[0].alertCoreDashBoardVOs[i].status.length >0){
					 str+='<td>'+result[0].alertCoreDashBoardVOs[i].status+'</td>';
						}else{
							    str+='<td>-</td>'; 
						}
					 str+='</tr>';
				    }
				   str+='</tbody>';    
				   str+='</table>';
				   
				   $("#grevinceDetailsId").html(str);
				   $("#alertIdListTableId").dataTable();
}
function buildGrivenceDetailsTableOld(result){
	 $("#grivancHeadinId").html('<h3>Grievance Details</h3>');
	 var str='';
	
	str+='<table id="alertIdListTableId" class="table  table-bordered" cellspacing="0" width="100%">';
                    str+='<thead>';
                     str+='<tr>';
				     str+=' <th>Complaint Id</th>';
					 str+=' <th>Date</th>';      
				     str+=' <th>Location</th>';
				     str+=' <th>Title</th>';
					 str+=' <th>Related to</th>';
				     str+=' <th>Problem</th>';
					 str+='<th>Status</th>';
                     str+='</tr>';
                   str+=' </thead>';
                   str+='<tbody>';
				   
				   for(var i in result){
					 str+='<tr>';
					 str+='<td style="cursor:pointer;" class="getAlertDtls" attr_alert_id="'+result[i].id+'" attr_alert_status="'+result[i].status+'">'+result[i].id+'</td>';
					 str+='<td>'+result[i].createdDate+'</td>';
					 if(result[i].location != null && result[i].location.length >0){
						str+='<td>'+result[i].location+'</td>';
					 }else{
						 str+='<td>-</td>';
					 }
					 if(result[i].title != null && result[i].title.length >0){
						str+='<td>'+result[i].title+'</td>';
					 }else{
						str+='<td>-</td>'; 
					 }
					  if(result[i].relatedTo != null && result[i].relatedTo.length >0){
					   str+='<td>'+result[i].relatedTo+'</td>';
					  }else{
						  str+='<td>-</td>'; 
					  }
					   if(result[i].problem != null && result[i].problem.length >0){
					 str+='<td>'+result[i].problem+'</td>';
					   }else{
						    str+='<td>-</td>'; 
					   }
					    if(result[i].status != null && result[i].status.length >0){
					 str+='<td>'+result[i].status+'</td>';
						}else{
							    str+='<td>-</td>';       
						}
					 str+='</tr>';
				    }
				   str+='</tbody>';    
				   str+='</table>';
				   
				   $("#grevinceDetailsId").html(str);
				   $("#alertIdListTableId").dataTable();
}
function buildTotalAlertDistrictTable(result){
     var str='';
	    str+='<table class=" table table-bordered">';
	    str+='<thead>';
        str+='<tr>'; 
		str+='<th>Total</th>';
	    for(var i in result[0].subList1){       
           str+='<th  style="background-color:#ecebd6;">'+result[0].subList1[i].statusType+'</th>';
		}
	        str+='<tr>';	 
	        str+='</thead>';	
			str+='<tbody>'
			str+='<tr>';
			str+='<td style="cursor:pointer;" class="getAlertDtlsOnLocCls" attr_from_date="'+result[0].fromDateStr+'" attr_to_date="'+result[0].toDateStr+'" attr_status_id="0" attr_location_id="'+result[0].id+'">'+result[0].totalAlertCnt+'</td>';
		for( var i in result[0].subList1){   
			if(result[0].subList1[i].totalAlertCnt != 0){
				str+='<td  style="cursor:pointer;" class="getAlertDtlsOnLocCls" attr_from_date="'+result[0].fromDateStr+'" attr_to_date="'+result[0].toDateStr+'" attr_status_id="'+result[0].subList1[i].statusTypeId+'" attr_location_id="'+result[0].id+'" style="background-color:#ecebd6;">'+result[0].subList1[i].totalAlertCnt+'</td>';
			}else{
				str+='<td>-</td>'; 
			}
		    
       }
			str+='<tr>'; 
	        str+='</tbody>'
			str+='</table>';
		 $("#totalAlertDistricTableId").html(str);
}
function rightSideExpandView(alertId){
	$("#cdrModelDivId").modal("show");     
    $("#rightSideExpandView").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var str='';
	str+='<div class="col-sm-12 pad_left0" expanded-block="block1">';        
		str+='<div class="panel-right">';
			str+='<div style="box-shadow:0px 0px 2px 2px rgba(0,0,0,0.2)">';  
				str+='<i class=""  expanded-close="block1"></i>';
				str+='<div class="panel panel-default">';
				
					str+='<div class="panel-heading" id="mainBlockStates">';
						str+='<div class="row">';
							str+='<div class="col-sm-4">';
								str+='<div id="assignedUser"></div>';
							str+='</div>';
							str+='<div class="col-sm-8 pull-right" style="">';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									
									str+='<li status-icon-block="alertStatus" attr_alert_id="'+alertId+'" subAlertId=""  data-toggle="tooltip" data-placement="top" title="alert status" id="displayStatusId"> ';
										str+='<span class="status-icon arrow-icon" id="statusIdColor"></span><span attr_alert_id="'+alertId+'" id="statusId">Pending</span>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="panel-heading" id="subBlockStates" style="display:none;">';
						str+='<div class="row">';
							str+='<div class="col-sm-4">';
								str+='<div id="assignedUser1"></div>';
							str+='</div>';
							str+='<div class="col-sm-8  pull-right" >';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									
									str+='<li status-icon-block="alertStatus1" attr_alert_id="'+alertId+'" subAlertId=""  data-toggle="tooltip" data-placement="top" title="alert status" id="displayStatusId1" style="" > ';
										str+='<span class="status-icon arrow-icon" id="statusId1Color"></span><span id="statusId1">Pending</span>';
									str+='</li>';
									
									str+='<li id="displayDueDate3"  style="display:none;"  class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="Due date">';
										str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date1">Due date</span>';
									str+='</li>';
									
									str+='<li id="displayDueDate4"  style="display:none;"  class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="Due date">';
										str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date">Due date</span>';
									str+='</li>';
									  
									str+='<li id="historyId1" style="display:none;" status-icon-block="alertHistory" attr_alert_id="'+alertId+'">';
										str+='<i class="fa fa-road" data-toggle="tooltip" data-placement="top" title="Alert History"></i>';
									str+='</li>';
									str+='<li id="docAttachmentId1" status-icon-block="attachment" attr_alert_id="'+alertId+'" style="display:none;" subAlertId="" >';
										str+='<i class="glyphicon glyphicon-paperclip" data-toggle="tooltip" data-placement="top" title="Attachments"></i>';
										str+='<form name="uploadAttachment1" method="post" id="uploadAttachment1">';
										str+='<div class="alert-status-attachment arrow_box_top" style="display:none;">';
										str+='<i attr_class="alert-status-attachment" class="glyphicon glyphicon-remove pull-right closeCls" ></i>';
											str+='<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/>';
											str+='<input type="hidden" name="subTaskId" value="'+alertId+'" subAlertId=""  id="alertHiddenId"/>';
											str+='<button class="btn btn-primary btn-sm text-capital uploadBtnIdCls " attr_alert_id="'+alertId+'" type="button" id="uploadBtnId" subAlertId=""  >upload</button>';
											str+='<span id="ErrorMsg1"></span>';
											str+='<span id="imagesUploadSpinner" class="imagesUploadSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
										str+='</form>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					str+='<div id="main_alert_block">';
						str+='<div class="panel-body" >';
							str+='<p><i class="fa fa-fire"></i> Impact Level : <span id="impactLevel"></span>';
								str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH</span></span>';
							str+='</p>';
							str+='<div id="callerDetailsDIv"></div>';
							str+='<div id="statusDtlsDiv"></div>';
							str+='<div id="alertDetails"></div>';
							str+='<div id="articleAttachment"></div>';
							str+='<div id="alertCategory"></div>';
							str+='<div id="alertSubtask"></div>';
							//str+='<div id="alertComments"></div>';
							str+='<div id="alertGeneralComments"></div>';
							str+='<div status-body="task" class="m_top20"></div>';
							str+='<div status-body="subTask" class="m_top20"></div>';
						str+='</div>';
					str+='</div>';
				
				
				str+='<div id="sub_tasls_View_alert_block" style="display:none">';
						str+='<div id="mainAlertTitle"></div>';
						str+='<div class="panel-body">';
							//str+='<p><i class="fa fa-fire"></i> Impact Level : <span id="impactLevel"></span>';
								//str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH </span></span>';
							str+='</p>';
							str+='<div id="subAlertDetails"></div>';
							str+='<div id="subArticleAttachment"></div>';
							str+='<div id="subAlertCategory"></div>';
							str+='<div id="subAlertSubtask"></div>';
							//str+='<div id="subAlertComments"></div>';
							str+='<div id="subAlertGeneralComments"></div>';
							//str+='<div status-body="task" class="m_top20"></div>';
							//str+='<div status-body="subTask" class="m_top20"></div>';
						str+='</div>';
						str+='<div class="panel-footer">';
							str+='<div class="row">';
								str+='<div class="col-sm-1 text-center">';
									str+='<span class="icon-name icon-primary">Ra</span>';
								str+='</div>';
								str+='<div class="col-sm-11">';
									str+='<div class="panel panel-default panel-border-white">';
										str+='<div class="panel-heading">';
											str+='<p>(Press Alt+t toggle between Telugu & English)</p>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<div class="comment-area">Comment Here</div>';
											str+='<textarea class="form-control comment-area" id="alertCommentId2" placeholder="Comment here..."></textarea>';
										str+='</div>';
										str+='<div class="panel-footer text-right">';
											str+='<button class="btn btn-primary comment-btn commentChangeCls" attr_alert_id="'+alertId+'"   subAlertId=""  id="commentChangeId">Save  </button>';
											str+='<span id="commentPostingSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div id="sub_task_block"  class="sub_task_block" style="display:none;" >';
						str+='<i attr_class="sub_task_block" class="glyphicon glyphicon-remove pull-right closeCls" ></i>';
						str+='<div class="panel-body panel-heading ">';
							str+='<div class="row">';
								str+='<div style="margin-left:25px"><i class="fa fa-level-down fa-2x" aria-hidden="true"></i> <label style="font-size:20px;margin-left:10px"> ASSIGN SUB TASK </label></div>';
							str+=' </div>';
							str+='<div class="col-sm-12">';
							  str+='<ul class="assign-subtask-list m_top20">';
							  str+='<form id="subTaskAlertAssign" name="subTaslAlertAssignForm">';
								str+='<li class="new">';
								  str+='<div class="row">';
									str+='<div class="col-sm-1">';
									  str+='<i class="glyphicon glyphicon-plus"></i>';
									str+='</div>';
									str+='<div class="col-sm-7">';
									  str+='<input type="text" id="subTaskTitleId" class="form-control subTaskTitle" placeholder="Enter Title Here" name="alertAssigningVO.title"/>';
									str+='</div>';
									str+='<div class="col-sm-4">';
									str+='<ul class="list-icons list-inline pull-right">';
										str+='<li class="list-icons-calendar" style="display:table-cell">';
											str+=' <i class="glyphicon glyphicon-calendar"></i><span  data-toggle="tooltip" data-placement="top" title="due date" class="modal-date2 subTaskDueDate" style="" > Due Date </span>';
											str+='<input id="hiddenDueDate1" type="hidden" value="" name="alertAssigningVO.dueDate" />';
										str+='</li>';
										str+='<li style="display:table-cell">';
											str+='<div class="assign-user">';
											str+='<div class="departmentsForSubTask" attr_alert_id="'+alertId+'" onclick="getAssignUIAttributes('+alertId+');"><i class="glyphicon glyphicon-user pointerCls assigningCls"></i> </div></div><div id="assignUIAttributesId" > </div>';
										str+='</li>';
									str+='</ul>';
									str+='</div>';
								  str+='</div>';
								str+='</li>';
								str+='</form>';
							  str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#rightSideExpandView").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	$(".chosenSelect").chosen({width:'100%'});
	assignedOfficersDetailsForAlert(alertId);
	departmentsByAlert(alertId);
	getAlertData(alertId);
	getStatusCompletionInfo(alertId);  
	//getGovtAllDepartmentDetails();
	buildAssignUIAttributes(alertId);  
	
}
function assignedOfficersDetailsForAlert(alertId)
{
	var jsObj = {
		alertId : alertId
	}
	$.ajax({
		type:'GET',
		url: 'getAssignedOfficersDetailsAction.action',
	data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			buildAssignedOfficersDetailsForAlert(result);
		}else{
			assignUser(alertId);
		}
		
	});
}
function buildAssignedOfficersDetailsForAlert(result)
{
	var str='';
	var splitNameArr = result[0].name.split(" ");
	var value = "";
	if(splitNameArr != null && splitNameArr.length>1)
		value = splitNameArr[1];
	else
		value = splitNameArr[0];  
	
	str+='<div class="media">';
		str+='<div class="media-body">';
			
			str+='<p> ASSIGN TO: <i class="fa fa-level-down "></i></p> ';
			str+='<p>'+result[0].designation+' <br> <i class="glyphicon glyphicon-phone"></i> : '+result[0].mobileNo+'</p>';
			str+='<p>Location :  '+result[0].source+'</p>';			
			str+='<p>Dept : '+result[0].department+'</p>'; 
			
			str+='<p></p>';
		str+='</div>';
	str+='</div>';
	$("#assignedUser").html(str);
	$(".assign-user").hide();
}
function assignUser(alertId)
{
	var str='';
	str+='<div class="assign-user">';
		str+='<ul class="list-icons list-inline">';
		str+='</ul>';
		str+='<div class="assign-user-body" style="display:none">';
			str+='<form id="alertAssign" name="alertAssignForm">';
				str+='<div class="arrow_box_top">';
					str+='<div>';
						str+='<div class="row">';  
							str+='<div class="col-sm-12">';
								str+='<div id="assignErrorDivId"></div>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
								str+='<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">	';
									str+='<option value="0">Select Department</option>';
									//str+='<option value="49">RWS</option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
								str+='<select  class="chosenSelect" id="locationLevelSelectId" name="alertAssigningVO.levelId">	';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
							str+='<div id="parentLevelDivId"> </div>';
							
							str+='<div class="col-sm-6">';
								str+='<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId"></span></label>';
								str+='<select name="alertAssigningVO.designationId" id="designationsId" class="chosenSelect">';
									str+='<option></option>	';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgOffcrId"></span></label>';
								str+='<select name="alertAssigningVO.govtOfficerId" id="officerNamesId" class="chosenSelect">';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
						str+='</div>';
						str+='<input type="hidden" id="hiddenAlertId" value="'+alertId+'" name="alertAssigningVO.alertId"/>';
					str+='</div>';
				str+='</div>';
			str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
				str+='<button class="btn btn-primary btn-sm text-capital" id="assignOfficerId" type="button">assign</button>';
				str+='<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg">';
				str+='<span class="text-success" id="assignSuccess"></span>';
			str+='</div>';
			str+='</form>';
		str+='</div>';
	str+='</div>';
	$("#assignedUser").html(str);
	$(".chosenSelect").chosen({width:'100%'});
	getDepartmentDetailsOfAlert(alertId);
}
function getDepartmentDetailsOfAlert(alertId)
{
	
	var jsObj={
		alertId : alertId
	}
	$.ajax({   
		type:'GET',
		url:'getDepartmentDetailsOfAlertAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		for(var i in result){
			
			if(result[i].name !=null && result[i].name.length>0){
				var newStr='';		
				newStr+='<option value="0">Select Department</option>';
				newStr+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				$("#departmentsId").html(newStr);
				$("#departmentsId").trigger("chosen:updated");
			}
		}	
	});
}
var globalSubTaskAlertId=0;
function departmentsByAlert(alertId){
	var jsObj = {
		alertId : alertId
	}
	$.ajax({
		type:'GET',
		url: 'getDepartmentsByAlertAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		str+='<p class="m_top20">';
			for(var i in result)
			{
				str+='<span class="label label-default label-category">'+result[i].name+'</span>';
				globalSubTaskAlertId=result[i].id;
			}
		str+='</p>';
		$("#alertDetails").append(str);
	});
}
function getAlertData(alertId)
{
	$("#alertDetails").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var jsObj =
	{
		alertId  :alertId,
		task : ""
	}
	$.ajax({
		type:'GET',
		url: 'getAlertsDataAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#alertDetails").html('');
		getAlertCategortByAlert(alertId);
		//getInvolvedMembersDetilas(alertId);
		getSubTaskInfoForAlert(alertId);
		getCommentsForAlert(alertId);
		getDocumentsForAlert(alertId);
		if(result != null && result.length > 0){
			buildAlertDataNew(result)
			if(result[0].categoryId == 2)
			{
				getGroupedArticlesInfo(result[0].alertCategoryTypeId)
			}
		}else{
			$("#alertDetails").html("NO DATA AVAILABLE...");
		}
	});
}
function getAlertCategortByAlert(alertId){
	$("#categoryId").html('');
	$("#alertCategory").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var jsObj =
	{
		alertId  :alertId
	}
	$.ajax({
	  type:'GET',
	  url: 'getAlertCategoryByAlertAction.action',
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
			$("#alertCategory").html('');
		if(result != null && result.length > 0)
		{
			var str='';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-1 text-center body-icons">';
					str+='<i class="fa fa-tags fa-2x"></i>';
				str+='</div>';
				str+='<div class="col-sm-11">';
					str+='<h4 class="text-muted text-capital">category</h4>';
					str+='<p class="m_top20"><span class="label label-default label-category">'+result+'</span></p>';
				str+='</div>';
			str+='</div>';
			
			$("#alertCategory").append(str);
		}
		
	});
}
function getSubTaskInfoForAlert(alertId){
	$("#alertSubtask").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var jsObj ={
		alertId  :alertId
	}
	$.ajax({
		type:'GET',
		url: 'getSubTaskInfoForAlertAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#alertSubtask").html('');
		if(result != null && result.length > 0)
		{
			buildSubTaskInfoForAlert(result,alertId);
		}
	});
}
function buildSubTaskInfoForAlert(result,alertId)
{
	var str='';
	
		
		for(var i in result)
		{     
			if(result[i].attachementsList != null && result[i].attachementsList.length>0){
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons">';
						str+='<i class="fa fa-level-down fa-2x"></i>';
					str+='</div>';
					
					str+='<div class="col-sm-11">';
						str+='<h4 class="text-muted text-capital"> My Sub Tasks : </h4>';
					str+='</div>';
					str+='<div class="row col-sm-12">';
						str+='<ul class="assign-subtask-list m_top20">';
						for(var k in result[i].attachementsList){
							str+='<li class="assigned subTaskCls " style="cursor:pointer;margin-left: 5px" attr_sub_alert_Id="'+result[i].attachementsList[k].alertId+'" attr_alert_id="'+alertId+'">';
									str+='<div class="row">';
										str+='<div class="col-sm-1">';
											str+='<i class="glyphicon glyphicon-ok"></i>';
										str+='</div>';
										str+='<div class="col-sm-10" >';
											str+='<p>'+result[i].attachementsList[k].title+' ';
											
											str+='</p>';
										str+='</div>';
										str+='<div class="col-sm-1">';
											str+='<ul class="list-icons list-inline">';
												str+='<li> <span class="status-icon arrow-icon" id="statusIdColor" style="background-color: '+result[i].attachementsList[k].color+'" title="'+result[i].attachementsList[k].status+'"></span> </li>';
											str+='</ul>';
										
										str+='</div>';
									str+='</div>';
							str+='</li>';
						}
						str+='</ul>';
					str+='</div>';
				
			}
			if(result[i].commentList != null && result[i].commentList.length>0){
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons">';
						str+='<i class="fa fa-level-down fa-2x"></i>';
					str+='</div>';
				str+='<div class="col-sm-11 ">';
					str+='<h4 class="text-muted text-capital"> Others Sub Tasks : </h4>';
					str+='</div>';
					str+='<div class="col-sm-11 col-sm-offset-1">';
						str+='<ul class="assign-subtask-list m_top20">';
						for(var k in result[i].commentList){
							str+='<li class="assigned subTaskCls " style="cursor:pointer;" attr_sub_alert_Id="'+result[i].commentList[k].alertId+'" attr_alert_id="'+alertId+'">';
									str+='<div class="row">';
										str+='<div class="col-sm-1">';
											str+='<i class="glyphicon glyphicon-ok"></i>';
										str+='</div>';
										str+='<div class="col-sm-10" >';
											str+='<p>'+result[i].commentList[k].title+' ';
											
											str+='</p>';
										str+='</div>';
										str+='<div class="col-sm-1">';
											str+='<span class="icon-name icon-primary" id="statusIdColor" style="background-color: '+result[i].commentList[k].color+'"  title="'+result[i].commentList[k].status+'"></span>';
											
										str+='</div>';
									str+='</div>';
							str+='</li>';
						}
						str+='</ul>';
					str+='</div>';
				
			}
		}	
	str+='</div>';	

	$("#alertSubtask").html(str);
}
function getCommentsForAlert(alertId){
	//$("#alertGeneralComments").html(spinner);  
	$("#alertGeneralComments").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var jsObj ={  
		alertId  :alertId  
	}
	$.ajax({
		type:'GET',
		url: 'getCommentsForAlertAction.action',  
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#alertGeneralComments").html('');
		if(result != null && result.length > 0)
		{
			buildCommentsForAlert(result);
		}else{
			$("#alertGeneralComments").html("");
		}
	});
}
function buildCommentsForAlert(result)
{
	var str='';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-comments-o fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital">comments</h4>';
			for(var i in result)
			{
				str+='<div class="media">';
					str+='<div class="media-left">';
						if(result[i].userName != null && result[i].userName.length > 0)
						{
							str+='<span class="icon-name icon-primary text-capital">'+result[i].userName.substring(0,2)+'</span>';
						}else{
							str+='<span class="icon-name icon-primary">ME</span>';
						}
					str+='</div>';
					str+='<div class="media-body">';
						if(result[i].comment != null && result[i].comment.length > 0)
						{
							str+='<p class="m_top5">'+result[i].comment+'</p>';
						}
						if(result[i].attachementsList != null && result[i].attachementsList.length > 0)
						{
							str+='<p class="m_top5">Attachments : '+result[i].attachementsList+'</p>';
						}
						if(result[i].date != null && result[i].date.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].date+'</p>';
						}
						if(result[i].userName != null && result[i].userName.length > 0)
						{
							str+='<p class="m_top5"> Updated By: '+result[i].userName+'</p>';
						}
						if(result[i].deptName != null && result[i].deptName.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon "></i> Dept Name: '+result[i].deptName+'</p>';
						}
						if(result[i].designation != null && result[i].designation.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon"></i> Designation :'+result[i].designation+'</p>';
						}
						/*if(result[i].mobileNO != null && result[i].mobileNO.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].mobileNO+'</p>';
						}
						*/
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	$("#alertGeneralComments").html(str);
}
function getDocumentsForAlert(alertId){
	$("#existingDocsDivId").html("");
	$("#existingDocsDivId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var jsObj ={
		alertId:alertId 
    }
    $.ajax({
    type:'GET',         
    url: 'getDocumentsForAlertsAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#existingDocsDivId").html('');
		if(result != null && result.length > 0){
			var str='';
			str+='<h4 class="text-muted text-capital">alert attachment</h4>';
			str+='<ul class="list-inline imageShowOpen">';
			for(var i in result){
				str+='<li class="" style="margin-top:25px;" attr_doc_id="'+result[i].id+'"  attr_path="'+result[i].name+'" id="imageAttachmentOpen'+result[i].id+'" >';
					str+='<img src="http://www.mytdp.com/images/'+result[i].name+'" style="width: 100px; height: 100px;cursor:pointer" />';
				str+='</li>';
			}
			str+='</ul>';
			$("#existingDocsDivId").html(str);
		}
    });
}
function buildAlertDataNew(result)
{
	var str='';
	var str1='';
	$("#statusId").html(result[0].status);
	$("#statusId").attr("attr_status_id",result[0].statusId);
	$("#impactLevel").html(result[0].regionScope);
	if(result[0].severity != null)
	{
		$("#priorityBodyId").html(result[0].severity);
	}
	$("#statusIdColor").css("background-color",result[0].statusColor);
	if(result[0].dueDate != null && result[0].dueDate.length>0)
	{
		$('.modal-date').data('daterangepicker').setStartDate(result[0].dueDate);
		$('.modal-date').data('daterangepicker').setEndDate(result[0].dueDate);
		if(result[0].dueDate != null && result[0].dueDate.length>0){
			$('.modal-date').html(result[0].dueDate);
			$('.modal-date1').html(result[0].dueDate);
		}
	}else{
			$('#displayDueDate2').hide();
			$('#displayDueDate1').hide();
		}
	
	//priorityRadioCls
	if(result[0].severityId != null && result[0].severityId > 0){
		$("input[name=alert-status-change-list][value='"+result[0].severityId+"']").prop("checked",true);
	}
	
	str+='<div class="row m_top20">';
		for(var i in result)
		{
			str+='<div class="col-sm-1 text-center body-icons">';
				str+='<i class="fa fa-check fa-2x"></i>';
			str+='</div>';
			str+='<div class="col-sm-11">';
				str+='<h3>'+result[i].title+'</h3>';
				str+='<p class="m_top10">'+result[i].desc+'</p>';
				str+='<p class="m_top10"><small> <i class="fa fa-map-marker"></i> '+result[i].locationVO.state+'(S),'+result[i].locationVO.districtName+'(D),'+result[i].locationVO.constituencyName+'(C),'+result[i].locationVO.tehsilName+'(M)'+result[i].locationVO.wardName+','+result[i].locationVO.villageName+'(P),'+result[i].locationVO.hamletName+'(H)</small></p>';
				str+='<p class="m_top10"><small> <i class="fa fa-calendar"></i> Created : '+result[i].date+'</small></p>';
			str+='</div>';
		}
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-paperclip fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			for(var i in result.documentList)
			{
				str+='<img class="articleDetailsCls img-responsive m_top20" src="http://mytdp.com/Reports/'+result.documentList[i]+'" style="width: 150px; height: 150px;cursor:pointer"/>';
			}
		str+='</div>';
	str+='</div>';
	str1+='<div class="row m_top20">';
	
		if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
			str1+='<div class="col-sm-1 text-center body-icons">';
				str1+='<i class="fa fa-paperclip fa-2x"></i>';
			str1+='</div>';
			if(result[i].imageUrl != null){
				str1+='<div class="col-sm-4">';
					str1+='<h4 class="text-muted text-capital">article attachment</h4>';
					str1+='<img class="articleDetailsCls img-responsive m_top20" attr_articleId='+result[i].alertCategoryTypeId+' src="http://mytdp.com/NewsReaderImages/'+result[i].imageUrl+'" style="width: 150px; height: 150px;cursor:pointer"/>';
				str1+='</div>';
				str1+='<div class="col-sm-7" id="existingDocsDivId"></div>';
			}else{
				str1+='<div class="col-sm-11" id="existingDocsDivId"></div>';
			}
		}else{
			str1+='<div class="col-sm-1 text-center body-icons">';
				str1+='<i class="fa fa-paperclip fa-2x"></i>';
			str1+='</div>';
			str1+='<div class="col-sm-11" id="existingDocsDivId"></div>';
		}
		
	str1+='</div>';
	$("#alertDetails").html(str);
	$("#articleAttachment").html(str1);
	
}
function getGroupedArticlesInfo(articleId)  
{
	$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
		 // url: "http://localhost:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
	}).then(function(result){
		console.log(result);
		//$("#alertDetails").append(str);
	});
}
var isAdmin = "";
var globalUserType = "";
var globalStatusId = 0;
var isStatusAvailable=true;
function getStatusCompletionInfo(alertId){
	isStatusAvailable=true;
	$("#updateStatusChangeBody").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	$("#statusDtlsDiv").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var jsObj ={
		alertId : alertId,
		levelValue : globalUserLevelValues[0],
		designationId : 0,
		levelId : globalUserLevelId
	}
	$.ajax({
		type:'GET',
		url: 'getStatusCompletionInfoAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#updateStatusChangeBody").html('');
		$("#statusDtlsDiv").html('');
		$('#displayStatusId,#displaySubTasksliId,#displayDueDate1,#displayDueDate2,#displayPriority,#historyId').hide();
		$('#displayStatusId').attr('status-icon-block','alertStatus');
		$('#docAttachmentId').hide();  	
		
		if(result != null && result.length>0){
			if(result.length  == 1)
				isStatusAvailable=false;
			
			if(result[0].callerName != null && result[0].callerName.length > 0 && result[0].mobileNo != null && result[0].mobileNo.length > 0)
			{
				var str='';
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons"><i class="fa fa-volume-control-phone fa-2x"></i></div>';
					str+='<div class="col-sm-11">';
						str+='<h3>Caller Details </h3>';
						str+='<p class="m_top10">Name : '+result[0].callerName+' </p>';
						str+='<p> Mobile No : '+result[0].mobileNo+' </p>';
						str+='<p> Caller : '+result[0].userType+'</p>';
					str+='</div>';
				str+='</div>';
				$("#callerDetailsDIv").html(str);
			}
			
			var buildTypeStr = result[0].applicationStatus.split('-')[0].trim();
		
			globalUserType = buildTypeStr;
			var sttatusId = result[0].applicationStatus.split('-')[1].trim();
			globalStatusId = sttatusId; 
			
			$('#historyId').show();
			if(result[0].dueDateStr != null && result[0].dueDateStr.trim().length>0){
				$('.modal-date').html(result[0].dueDateStr)
				$('.modal-date1').html(result[0].dueDateStr)
			}else{
				$('#displayDueDate2').hide();
				$('#displayDueDate1').hide();
			}
			
			if(buildTypeStr=='own'){  
				$('#displayStatusId,#displaySubTaskli,#displaySubTasksliId').show();	
				$('#docAttachmentId').show();	
				$('#displayDueDate1').show();
				$('#displayDueDate2').hide(); 
				
				if(globalStatusId == 12 ){ // closed
					isStatusAvailable=false;
					$('#displaySubTasksliId,#docAttachmentId').hide();
				}else {
					isStatusAvailable=true;
				}				
			}
			else if(buildTypeStr=='subUser'){	
				$('#displaySubTasksliId').hide();		
				$('#displayDueDate1').hide();
				$('#displayStatusId').show();
				$('#displayStatusId').removeAttr('status-icon-block');
				
				$('#displayDueDate2,#displayPriority').show();
				
				// closed-12, completed-4, reopen-11
				if( globalStatusId == 12 || globalStatusId == 4 || globalStatusId == 11){
					isStatusAvailable=true;
				}
				if(globalStatusId != 12){ // for not closed status alerts 
					$('#displaySubTasksliId,#docAttachmentId').show();					
				}
			}else if(buildTypeStr=='same'){ 
				$('#displaySubTasksliId,#docAttachmentId,#displayPriority').show();
				$('#displayStatusId').show();       
				$('#displayDueDate1').show(); 
				isStatusAvailable=false;
			}
			else if(buildTypeStr=='other'){
				$('#displaySubTasksliId').hide();				
				$('#displayDueDate1').hide();				
				$('#displayDueDate2').hide();				
				$('#displayStatusId').show();
				isStatusAvailable=false;				
			}
			if((sttatusId == 1  || sttatusId == 8 || sttatusId==9) && result[0].userStatus != null && result[0].userStatus =='admin'){
				
				$('#docAttachmentId').show();	
				 assignUser(alertId);
				 
			}
		
			if(result[0].userStatus != null && result[0].userStatus =="admin"){
				isAdmin = "true";
				//$('#displayStatusId').attr('status-icon-block','');
				$('#displaySubTasksliId,#displayDueDate1,#displayDueDate2,#displayPriority').hide(); 
				if(sttatusId !=1)
					$('#docAttachmentId').show(); 
			}else{
				$('#displayStatusId').attr('status-icon-block','alertStatus');
				isAdmin = "false";
			}
			
			if(isAdmin=='false'){				
				$('#displayStatusId').attr('status-icon-block','alertStatus');
			}
			alertStatus(result,alertId);	

			if(globalStatusId == 12 ){ // closed
				$('#displaySubTasksliId,#docAttachmentId,#displayPriority,#displayDueDate2').hide();
				$('#displayDueDate1').show();
			}
			
			//alert(" isStatusAvailable :"+isStatusAvailable);
		}else{
			
			$('#displayStatusId').show();
			$('#displaySubTasksliId').hide();  
			$('#docAttachmentId').hide();  
		}	
		setTimeout(function(){
			$("body").addClass("modal-open");
		},1000);
	});
}
var glStr='';
function alertStatus(result,alertId)
{
	glStr='';
	var str1='';
	 
		str1+='<div class="panel panel-default panel-white m_top20 alert-status-change-body">';
			str1+='<div class="panel-heading" style="margin-left: 20px;">';
				str1+='<div class="row">';
				for(var i in result)
				{
					
					str1+='<div class="col-sm-4">';
						str1+='<div class="radioStyling">';
							if(globalStatusId == parseInt(result[i].id))
							{
								str1+='<input class="alertStatusCls" attr_id="'+result[i].id+'" type="radio" name="group1" id="radio-'+i+'">';
							}else
							{
								str1+='<input class="alertStatusCls" attr_id="'+result[i].id+'" type="radio" name="group1" id="radio-'+i+'">';
							}
							str1+='<label for="radio-'+i+'"><span class="radio" >'+result[i].name+'</span></label>';
						str1+='</div>';
					str1+='</div>';
				}				
				str1+='</div>';
			str1+='</div>';
			str1+='<div class="panel-body pad_0">';
				str1+='<textarea class="form-control" id="updateStatusChangeComment" placeholder="Comment.."></textarea>';
			str1+='</div>';
		str1+='</div>';
	
	str1+='<button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" subTaskId="" id="updateStatusChangeId">update</button>';
	str1+='<span id="updateStatusChangeAjaxSymbol"></span>';
	str1+='<span id="updateStatusChangeMsg"></span>';
	glStr=str1;
	//$("#updateStatusChangeBody").html(str1);
}
function buildAssignUIAttributes(alertId){
	var str='';
	str+='<div class="assign-subtask" style="width:600px;position:absolute;right:0;top:33px">';
		str+='<div class="arrow_box_top" >';
			str+='<div>';
				str+='<div class="row">';  
					str+='<div class="col-sm-12">';
						str+='<i attr_class="assign-subtask" class="glyphicon glyphicon-remove pull-right closeCls" ></i>';
						str+='<div id="assignErrorDivId1" style="color:red;"></div>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId1"></span></label>';
						str+='<select class="chosenSelect" id="departmentsId1" name="alertAssigningVO.departmentId">	';
						str+='</select>'; 
					str+='</div>';
					
					str+='<div class="col-sm-6">';
						str+='<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId1"></span></label>';
						str+='<select  class="chosenSelect" id="locationLevelSelectId1" name="alertAssigningVO.levelId">	';
							str+='<option></option>';
						str+='</select>';
					str+='</div>';
					
					str+='<div id="parentLevelDivId1"> </div>';
					
					str+='<div class="col-sm-6">';
						str+='<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId1"></span></label>';
						str+='<select name="alertAssigningVO.designationId" id="designationsId1" class="chosenSelect">';
							str+='<option></option>	';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						str+='<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgOffcrId1"></span></label>';
						str+='<select name="alertAssigningVO.govtOfficerId" id="officerNamesId1" class="chosenSelect">';
							str+='<option></option>';
						str+='</select>';
					str+='</div>';
				str+='</div>';
				str+='<input type="hidden" id="hiddenAlertId1" value="'+alertId+'" name="alertAssigningVO.alertId"/>';
			str+='</div>';
		str+='</div>';
		str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
			str+='<button class="btn btn-primary btn-sm text-capital" id="subTaskassignOfficerId" type="button" onclick="saveSubTask('+alertId+');">assign</button>';
			str+='<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg1">';
			str+='<span class="text-success" id="assignSuccess"></span>';
		str+='</div>';
	str+='</div>';
$('#assignUIAttributesId').html(str);

$('[data-toggle="tooltip"]').tooltip();
	$(".chosenSelect").chosen({width:'100%'});
	
	$('.modal-date2').data('daterangepicker');
	
	$(function() {
		var start = moment();
		
		function cb(start) {
			$('.modal-date2').html(start.format('DD/MM/YYYY'));
		}
       
		$('.modal-date2').daterangepicker({
			startDate: start,
			singleDatePicker:true,
			locale:{
				format:"DD/MM/YYYY"
			}
			
		}, cb);		
	});
}
function getAlertStatusHistory(alertId){
	$("#alertManagementPopup1").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
	var jsObj ={
		alertId : alertId
	}
	$.ajax({
		type:'GET',
		url: 'getAlertStatusHistoryAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		alertStatusHistory(result,alertId);
	});
}
function alertStatusHistory(result,alertId)
{

	var str='';
	var str1='';
	
	
	if(result != null && result.length>0)
	{
		str+='<table class="table border_1">';
			str+='<thead class="text-capitalize">';
				str+='<th>Date</th>';
				str+='<th>Status</th>';
				str+='<th>Updated By</th>';
				str+='<th>Comments</th>';
			str+='</thead>';
			for(var i in result)  
			{
				str+='<tr>';
					str+='<td>'+result[i].date+'</td>';
					str+='<td>'+result[i].status+'</td>';
					str+='<td>';
						str+='<p class="text-primary text-capitalize">Updated By: <span style="color:black;">'+result[i].userName+' </span></p>';
						str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;"> '+result[i].deptName+' </span></p>';						
						str+='<p class="text-primary text-capitalize"><u> Designation:  <span style="color:black;"> '+result[i].designation+' </span></u></p>';
						//str+='<p class="text-primary text-capitalize">Dept Name: '+result[i].mobileNO+'</p>';
					str+='</td>';
					str+='<td>'+result[i].comment+'</td>';
				str+='</tr>';
			}  
			
		str+='</table>';
		
		
		$("#alertManagementPopup1 .modal-footer").show();
	
		
		$("#alertManagementPopup1 .modal-dialog").css("width","60%")
		$("#alertManagementPopupBody1").html(str);
	}else{  
		$("#alertManagementPopupBody1").html(" NO HISTORY AVAILABLE...")  
	}
	$("#alertManagementPopup1 .modal-footer").html(' ');
}
//date wise report
getGrievanceReportDayWise();  
function getGrievanceReportDayWise(){
	$("#dayWiseGrivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var rangeType=$("#dateRangeId").attr("value");
    var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
    var jsObj ={
		fromDate: callCenterUserFDate,                       
		toDateStr:callCenterUserTDate,  
		deptId:deptId,
		sourceId:sourceId,
		locationId:0,  
		rangeType:rangeType,             
		stateId:1
	}
	$.ajax({
		type:'GET',         
		url: 'getGrievanceReportDayWiseAction.action',
		data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildGrievanceReportDayWise(result,rangeType);
			}else{
				$("#dayWiseGrivenaceTableId").html('No Data Available.');
			}
		}); 
}
function buildGrievanceReportDayWise(result,rangeType) {  
	$("#dayWiseGrivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var str='';
        str+='<table id="dayWiseGrievanceReportTableId" class="table table-bordered " cellspacing="0">';
        str+='<thead>';
        str+='<tr>';
		if(rangeType=="day"){
			str+='<th>Day</th>'; 
		}else if(rangeType=="week"){
			str+='<th>Week</th>'; 
		}else if(rangeType=="month"){
			str+='<th>Month</th>';   
		}else{
			str+='<th>Category</th>'; 
		}
            
        str+='<th>Total</th>';
		for(var i in result[0].subList1){       
           str+='<th>'+result[0].subList1[i].statusType+'</th>';
		}
        str+=' </tr>';
        str+='</thead>';
		str+='<tbody>';
		var locTotal = 0;
		for(var i in result){
			str+='<tr>'; 
				str+='<td>'+result[i].day+'</td>';           
				str+='<td style="cursor:pointer;" class="getAlertDtlsOnDateWise" attr_from_date="'+result[i].fromDateStr+'" attr_to_date="'+result[i].toDateStr+'" attr_status_id="0" attr_location_id="0">'+result[i].totalAlertCnt+'</td>';
				locTotal = parseInt(locTotal) + parseInt(result[i].totalAlertCnt);
			for(var j in result[i].subList1){
				if(result[i].subList1[j].totalAlertCnt != 0){
					str+='<td style="cursor:pointer;" class="getAlertDtlsOnDateWise" attr_from_date="'+result[i].fromDateStr+'" attr_to_date="'+result[i].toDateStr+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_location_id="0">'+result[i].subList1[j].totalAlertCnt+'</td>';
				}else{
					str+='<td>-</td>';
				}      
			}
         
			str+='</tr>';
		}  
 		str+='<tr>';
 			str+='<td>Grand Total</td>';
 			str+='<td>'+locTotal+'</td>';
			for(var i in result[0].subList1){
				if(result[0].subList1[i].grandTotal == 0){
					str+='<td>-</td>';
				}else{
					str+='<td>'+result[0].subList1[i].grandTotal+'</td>';
				}
			}
		str+='</tr>';
		str+='</tbody>';
		str+='</table>';
		$('#dayWiseGrivenaceTableId').html(str);  
		 $('#dayWiseGrievanceReportTableId').DataTable({
			"order": [[ 1, "asc" ]] 
		});
 }
//category wise count
getTotalAlertGroupByCategoryThenStatus();  
function getTotalAlertGroupByCategoryThenStatus(){
	$("#CategoryWiseGrivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var rangeType=$("#dateRangeId").attr("value");
    var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
    var jsObj ={
		fromDate: callCenterUserFDate,                       
		toDateStr:callCenterUserTDate,  
		deptId:deptId,
		sourceId:sourceId,
		LocationId:0,
		statusId:0,		
		stateId:1
	}
	$.ajax({
		type:'GET',         
		url: 'getTotalAlertGroupByCategoryThenStatusAction.action',
		data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildTotalAlertGroupByCategoryThenStatus(result);
			}else{
				$("#CategoryWiseGrivenaceTableId").html('No Data Available.');
			}
		}); 
}
function buildTotalAlertGroupByCategoryThenStatus(result) {  
	$("#CategoryWiseGrivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var str='';
        str+='<table id="CatWiseGrievanceReportTableId" class="table table-bordered " cellspacing="0">';
        str+='<thead>';
        str+='<tr>';
		
		str+='<th>Category</th>';
            
        str+='<th>Total</th>';
		for(var i in result[0].subList1){       
           str+='<th>'+result[0].subList1[i].statusType+'</th>';
		}
        str+=' </tr>';
        str+='</thead>';
		str+='<tbody>';
		var locTotal = 0;
		for(var i in result){
			str+='<tr>'; 
				str+='<td>'+result[i].name+'</td>';             
				str+='<td style="cursor:pointer;" class="getAlertDtlsOnCategoryWise" attr_status_id="0" attr_location_id="0" attr_source_id="'+result[i].id+'">'+result[i].totalAlertCnt+'</td>';
				locTotal = parseInt(locTotal) + parseInt(result[i].totalAlertCnt);
			for(var j in result[i].subList1){
				if(result[i].subList1[j].totalAlertCnt != 0){
					str+='<td style="cursor:pointer;" class="getAlertDtlsOnCategoryWise" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_location_id="0" attr_source_id="'+result[i].id+'">'+result[i].subList1[j].totalAlertCnt+'</td>';
				}else{
					str+='<td>-</td>';
				}      
			}
         
			str+='</tr>';
		}  
 		/* str+='<tr>';
 			str+='<td>Grand Total</td>';
 			str+='<td>'+locTotal+'</td>';
			for(var i in result[0].subList1){
				str+='<td>'+result[0].subList1[i].grandTotal+'</td>';//  result[0].subList1[i].statusType  
			}
		str+='</tr>'; */
		str+='</tbody>';
		str+='</table>';
		$('#CategoryWiseGrivenaceTableId').html(str);  
		 $('#CatWiseGrievanceReportTableId').DataTable({  
			"order": [[ 1, "asc" ]]  
		});
 }
getDistIdAndNameList();
function getDistIdAndNameList(){
	var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
    var jsObj ={
		fromDate: callCenterUserFDate,                       
		toDateStr:callCenterUserTDate,  
		deptId:deptId,
		sourceId:sourceId,
		stateId:1
	}
	$.ajax({
		type:'GET',         
		url: 'getDistIdAndNameListAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			$('#selectDistrictId').find('option').remove();
			$('#selectDistrictId').append('<option value="0">All</option>');
			$.each(result,function(index,value){
				$('#selectDistrictId').append('<option value="'+value.id+'">'+value.name+'</option>');
			});
		}else{
			$('#selectDistrictId').find('option').remove();
			$('#selectDistrictId').append('<option value="-1">No Data</option>');
		}         
	}); 
} 

	getCadreGreivienceEfficiency();
function getCadreGreivienceEfficiency(){
	$("#efficiencyId").html("");
	$("#efficiencyId").html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
    var alertstatusIds = [];
	var deptIds=[];
	var sourceIds =[];
	var includeProposal = $("#proposalId").prop('checked');;
	
	var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
	deptIds.push(deptId);  
	if(sourceId==0){
		sourceIds.push(1);
		sourceIds.push(2);
		sourceIds.push(3);
	}else if(sourceId==1){
		sourceIds.push(1);
	}else if(sourceId==2){
		sourceIds.push(2);
	}else if(sourceId==3){
		sourceIds.push(3);
	}
    var jobj = {
      
      daysArr : [5,10,30,60,90,180,365],
	  deptIds :deptIds,
	  sourceIds:sourceIds,
      includeProposal : includeProposal,
	  alertstatusIds:$("#statusId").val()
    }
    $.ajax({
      type : "POST",
      url  : "getAlertEfficiencyListAction.action",
      dataType: 'json',
      data: {task:JSON.stringify(jobj)},
    }).done(function(result){
      if(result!=null){
				var str = "";
				str +="<table class='table table-bordered bg-white' style='margin-bottom:20px;'>";
					str +="<tbody>";
						str +="<tr>";
							for(var i in result){
								str+="<td>"+result[i].effcncyType+"</td>";
							}
							str +="</tr>";
							str +="<tr>";
							for(var i in result){
								if(result[i].clrFrEffcncy=="red"){
									str+="<td class='text-danger'>"+result[i].effcncyPrcnt+" %</td>";
								}else{
									str+="<td class='text-success'>"+result[i].effcncyPrcnt+" %</td>";
								}
							}
						str +="</tr>";
					str +="</tbody>";
				str +="</table>";
			}
			
			$("#efficiencyId").html(str);
    });
}
