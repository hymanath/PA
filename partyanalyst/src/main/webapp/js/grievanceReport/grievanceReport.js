var globalUserLevelValues = [0]; 
var globalUserLevelId = 0;
var callCenterUserFDate=moment().startOf('month').format("DD/MM/YYYY");
var callCenterUserTDate=moment().format("DD/MM/YYYY");
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

$('#reportrange').daterangepicker({
	opens: 'right',
	startDate: callCenterUserFDate,
	endDate: callCenterUserTDate,
	locale: {
		  format: 'DD/MM/YYYY'
		},
	ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
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
	var dates= $("#reportrange").val();
	var pickerDates = callCenterUserFDate+' - '+callCenterUserTDate
	if(dates == pickerDates)
	{
		$("#reportrange").val('This Month');
	}
//var callCenterUserFDate=moment().startOf('month').format("DD/MM/YYYY");
//var callCenterUserTDate=moment().format("DD/MM/YYYY");

$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
		callCenterUserFDate = picker.startDate.format('DD/MM/YYYY');
		callCenterUserTDate = picker.endDate.format('DD/MM/YYYY');
		if(picker.chosenLabel == 'All')
		{
			$("#reportrange").val('All');
		}
		
		getTotalLocationWiseGrivenaceReport();
});
$(document).on("click",".daterangeClorCls",function(){ 
    $(".daterangeClorCls").removeClass("dateColorCls");
	$(this).addClass("dateColorCls");
}); 
 onLoadCalls();

function onLoadCalls()
{
	getGrievanceReport();
	getCadreGreivienceEfficiency(2);
	getDistIdAndNameList();
	getTotalAlertGroupByCategoryThenStatus();  
	getGrievanceReportDayWise();  
	getAverageIssuePendingDays();
	
}

function getGrievanceReport(){
$("#grivenaceTableId").html(spinner);
$("#barGraph").html(spinner);
$("#statusWiseAlertCntId").html(spinner);
$("#feedbackWiseAlertCntId").html(spinner);
$("#reopenAlertCntId").html(spinner);
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
				$("#grivenaceTableId").html('');
				$("#barGraph").html('');
				$("#statusWiseAlertCntId").html('');
				$("#feedbackWiseAlertCntId").html('');
				$("#reopenAlertCntId").html('');
			if(result !=null && result.length>0){
				buildGrievanceReport(result);
				buildLocationWiseGrivenacereportGraph(result);
				
		    }else{
				$("#statusWiseAlertCntId").html('No Data Available');
				$("#grivenaceTableId").html('No Data Available');
				$("#totalAlertCountId").html("-");
			}
	}); 
}	
//location wise table     
 function buildGrievanceReport(result) {
	
     var str='';
        str+='<table id="grievanceReportTableId" class="table table-bordered " cellspacing="0">';
	        str+='<thead>';
	        	str+='<tr>';
	        		str+='<th>District</th>';     
	        		str+='<th>Total</th>';
					for(var i in result[0].subList1){       
			           if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "feebbackAlert" || result[0].subList1[i].name == "pendingFeedBack"){	
						str+='<th style="background-color:#ECEBD6">'+result[0].subList1[i].statusType+'</th>';
						}else if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "reopen"){
							str+='<th style="background-color:#C9AC82">'+result[0].subList1[i].statusType+'</th>';
						}else{
							str+='<th>'+result[0].subList1[i].statusType+'</th>';
						}
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
					str+='<td style="cursor:pointer;" attr_group_type="tehsil" attr_location_id="'+result[i].id+'" attr_location_name="'+result[i].name+'"class="bellowLvlLocCls"><i class="glyphicon glyphicon-plus-sign"></i> '+result[i].name+'</td>';         
					str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_type="other" attr_group_type="status" attr_location_id="'+result[i].id+'">'+result[i].totalAlertCnt+'</td>';
					locTotal = parseInt(locTotal) + parseInt(result[i].totalAlertCnt);
					for(var j in result[i].subList1){
						var type = "other";
						if(result[i].subList1[j].name != null){
							type = result[i].subList1[j].name;
						}
						if(result[i].subList1[j].totalAlertCnt != 0){
							str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_type="'+type+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_location_id="'+result[i].id+'" attr_group_type="status">'+result[i].subList1[j].totalAlertCnt+'</td>';
						}else{
							str+='<td>-</td>';
						}      
					}
					for(var j in result[i].subList2){  
						if(result[i].subList2[j].totalAlertCnt != 0){        
							str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_type="other" attr_pattern="'+result[i].subList2[j].day+'" attr_location_id="'+result[i].id+'" attr_group_type="day">'+result[i].subList2[j].totalAlertCnt+'</td>';
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

//abcd
 
 function getAverageIssuePendingDays(){
	var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
	var alertstatusIds = [];
	
 	var deptIds=[];
 	var sourceIds =[];
 	deptIds.push(deptId);  
	if(sourceId==0){
		sourceIds.push(5);
		sourceIds.push(4);             
		sourceIds.push(2);  
		sourceIds.push(3);
	}else if(sourceId==4){   
		sourceIds.push(4);
	}else if(sourceId==2){
		sourceIds.push(2);
	}else if(sourceId==3){
		sourceIds.push(3);
	}else if(sourceId==5){
		sourceIds.push(5);      
	}
    var jobj = {
		deptIds :deptIds,
		sourceIds:sourceIds,  
		alertstatusIds:$("#statusId").val(),
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
	$("#dayWiseGrivenaceTableId").html(spinner);
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
			$("#dayWiseGrivenaceTableId").html('');
			if(result != null && result.length > 0){
				buildGrievanceReportDayWise(result,rangeType);
			}else{
				$("#dayWiseGrivenaceTableId").html('No Data Available.');
				$("#totalAlertCountId").html("-");
			}
	}); 
}	 
//on change media 	
 function getMediaInformation(){
	 $("#sliderValue").remove();
	getSliderDetails();
	 getDistIdAndNameList();
	 getAverageIssuePendingDays();
	 getGrievanceReportDayWise();
	 getTotalAlertGroupByCategoryThenStatus();
	getCadreGreivienceEfficiency(2);	 
 $("#statusWiseAlertCntId").html(spinner);
$("#feedbackWiseAlertCntId").html(spinner);
$("#reopenAlertCntId").html(spinner);
 $("#grivenaceTableId").html(spinner);
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
		 $("#statusWiseAlertCntId").html('');
		 $("#feedbackWiseAlertCntId").html('');
		 $("#reopenAlertCntId").html('');
		$("#grivenaceTableId").html('');
		 if(result != null && result.length > 0){
			buildGrievanceReport(result);
			buildLocationWiseGrivenacereportGraph(result);
		 }else{
			 $("#statusWiseAlertCntId").html('No Data Available');
			 $("#grivenaceTableId").html('No Data Available');
			 $("#totalAlertCountId").html("-");
		 }

 	});
 }

//on dept change
 function getDepartmentInformation(){
	  $("#sliderValue").remove();
	getSliderDetails();
	 getDistIdAndNameList();
	 getAverageIssuePendingDays();
	 getGrievanceReportDayWise();
	 getTotalAlertGroupByCategoryThenStatus(); 
	 getCadreGreivienceEfficiency(2)
	 $("#statusWiseAlertCntId").html(spinner);
	 $("#feedbackWiseAlertCntId").html(spinner);
	$("#reopenAlertCntId").html(spinner);
	 $("#grivenaceTableId").html(spinner);
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
		 $("#statusWiseAlertCntId").html('');
		 $("#feedbackWiseAlertCntId").html('');
		$("#reopenAlertCntId").html('');
		$("#grivenaceTableId").html('');
		if(result !=null && result.length>0){
			buildGrievanceReport(result);
			buildLocationWiseGrivenacereportGraph(result);
		}else{
			$("#statusWiseAlertCntId").html('No Data Available');
			$("#grivenaceTableId").html('No Data Available');
			$("#totalAlertCountId").html("-");
		}
 	});
 }
//on change daterangepicker
 function getTotalLocationWiseGrivenaceReport(){
	  $("#sliderValue").remove();
	 getSliderDetails();
	 getDistIdAndNameList();
	 getAverageIssuePendingDays();
	 getGrievanceReportDayWise();
	 getTotalAlertGroupByCategoryThenStatus(); 
	 getCadreGreivienceEfficiency(2);
 $("#grivenaceTableId").html(spinner);
 $("#statusWiseAlertCntId").html(spinner);
 $("#feedbackWiseAlertCntId").html(spinner);
$("#reopenAlertCntId").html(spinner);
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
		  $("#grivenaceTableId").html('');
		 $("#statusWiseAlertCntId").html('');
		 $("#feedbackWiseAlertCntId").html('');
		$("#reopenAlertCntId").html('');
 		if(result !=null && result.length>0){
			buildGrievanceReport(result);
			buildLocationWiseGrivenacereportGraph(result);
		}else{
			$("#statusWiseAlertCntId").html('No Data Available');
			$("#grivenaceTableId").html('No Data Available');
			$("#totalAlertCountId").html("-");
		}
 	});
 }
 //on click month week day btn
 $(document).on("click",".rangeTypeCls",function(){
	$("#selectDistrictId").val(0);  
	$('#selectDistrictId').trigger("chosen:updated");
	$("#dayWiseGrivenaceTableId").html(spinner);
	$("#grivenaceTableId").html(spinner);
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
		//getTotalAlertGroupByCategoryThenStatus();       
		if(result !=null && result.length>0){
			buildGrievanceReport(result);
			//buildLocationWiseGrivenacereportGraph(result);
		}else{
			$("#statusWiseAlertCntId").html('No Data Available');
			$("#grivenaceTableId").html('No Data Available');
			$("#totalAlertCountId").html("-");
		}
	});
});
//Graph building
 function buildLocationWiseGrivenacereportGraph(result){
	var statusNamesArray =[];
	var statusIdNameArr=[];
	for(var i in result[0].subList1){
		 var type = "other";
			if(result[0].subList1[i].name != "reopen" && result[0].subList1[i].name != "feebbackAlert" && result[0].subList1[i].name != "pendingFeedBack"){
				
				 if(result[0].subList1[i].name != null){
				   type = result[0].subList1[i].name;
				 } 
				 statusNamesArray.push(result[0].subList1[i].statusType);
				 statusIdNameArr.push({"y":result[0].subList1[i].grandTotal,"status":result[0].subList1[i].statusType,"id":result[0].subList1[i].statusTypeId,"type":type});
			}
		}	
				
		buildHighchart("statusWiseAlertCntId",statusNamesArray,statusIdNameArr);
		statusNamesArray =[];
		statusIdNameArr=[];
	     for(var i in result[0].subList1){
			 var type = "other";
			if(result[0].subList1[i].name == "feebbackAlert" || result[0].subList1[i].name == "pendingFeedBack"){
				if(result[0].subList1[i].name != null){
				   type = result[0].subList1[i].name;
				 } 
				 statusNamesArray.push(result[0].subList1[i].statusType);
				 statusIdNameArr.push({"y":result[0].subList1[i].grandTotal,"status":result[0].subList1[i].statusType,"id":result[0].subList1[i].statusTypeId,"type":type});
				 
			}
		}
		buildHighchart("feedbackWiseAlertCntId",statusNamesArray,statusIdNameArr);
		statusNamesArray =[];
		statusIdNameArr=[];
		for(var i in result[0].subList1){
			 var type = "other";
			if(result[0].subList1[i].name == "reopen"){
				if(result[0].subList1[i].name != null){
				   type = result[0].subList1[i].name;
				 } 
				 statusNamesArray.push(result[0].subList1[i].statusType);
				 statusIdNameArr.push({"y":result[0].subList1[i].grandTotal,"status":result[0].subList1[i].statusType,"id":result[0].subList1[i].statusTypeId,"type":type});
			}
		}
		buildHighchart("reopenAlertCntId",statusNamesArray,statusIdNameArr); 
		
 }
 function buildHighchart(divId,statusNamesArray,statusIdNameArr){
	Highcharts.chart(divId, {
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
				 labels: {
						formatter: function() {
							return this.value.toString().substring(0, 8)+'..';
						},
						 style: {
							color: '#F0F8FF'
						}
						
					}
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
                 column: {
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
				 },
				series: {
					cursor: 'pointer',					
					 point: {
						events: {
							 click: function () {
								var type = this.type;
								 if(type!="other"){
								   getStatusWiseFeebbackAlertDtls(this.id,this.type);
								 }else{
								  getAlertStatusWise(this.id,this.status);   
								 }
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
		var type = $(this).attr("attr_type");
		$("#totalAlertDistricTableId").html("");  
		$("#grievanceDtlsModalId").modal("show");
		$("#removeClassModal").removeClass("closeSecondModal")	
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
		if(type!="other"){
			getFeedbackAlert(callCenterUserFDate,callCenterUserTDate,deptId,sourceId,locationId,statusId,rangeType,type);
			return;
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
		var type = $(this).attr("attr_type");
		 if(type != "other"){
			 getFeedbackAlert(fromDate,toDate,deptId,sourceId,locationId,statusId,rangeType,type);
			 return;
		 }
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
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html("<div class='row'><div id='rightSideExpandView'></div></div>");
		var alertId = $(this).attr("attr_alert_id");  
		rightSideExpandView(alertId);
		$(".flipview-btn,.filters-icon").hide();
		
		$("#myModalLabel").html("Alert Status");
		setTimeout(function(){
			$("#alertManagementPopup1 .modal-footer").hide();
			$("[expanded-block='block1']").addClass("col-sm-12").removeClass("col-sm-8 pad_left0");
			$("[expanded-block='block1']").show();
		},750);
		
	});
 
	$(document).on("click",".bellowLvlLocCls",function(){
		$("#bellowLvlLocId").modal("show");
		
		$("#tehsilTableId").html(spinner);
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
			$("#tehsilTableId").html('');
			buildGrievanceReportForBellowLocation(result,locationId,locationName,groupType);  
		});
	});
	$(document).on("click",".panchayatDataCls",function(){
		
		$(this).closest('tr').next('tr').find(".accordian-body").addClass("in");
		$(this).closest('tr').next('tr').find(".accordian-body").removeAttr("style");
		var signValue = $(this).find('i').hasClass("glyphicon-minus-sign");
		
		if(signValue){
			$(this).closest('tr').next('tr.hiddenRow').hide();
		}else{
			$(this).closest('tr').next('tr.hiddenRow').show();
		}
		$(this).find('i').toggleClass("glyphicon-minus-sign");
		var deptId=$("#selecDepartmentId").val();
		var sourceId=$("#selectMediaId").val();
		var locationId = $(this).attr("attr_location_id");
		var groupType = $(this).attr("attr_group_type");  
		var positionValue = $(this).attr("attr_position");  
		 
		$("#demo"+positionValue).html(spinner); 		 
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
			$("#demo"+positionValue).html('');
				
			buildGrievanceReportForPanchayat(result,positionValue,locationId,groupType);         
		});
	});
	$(document).on("click",".bellowLvlCls",function(){
			var type = $(this).attr("attr_type");
			$("#grievanceDtlsModalId").modal("show");
			 $("#removeClassModal").addClass("closeSecondModal")
			$("#totalAlertDistricTableId").html('');     
			$("#grevinceDetailsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');    
			var deptId=$("#selecDepartmentId").val();
			var sourceId=$("#selectMediaId").val();
			var locationId = $(this).attr("attr_location_id");
			var statusId = $(this).attr("attr_status_id"); 
			var areaType = $(this).attr("attr_area_type"); 
			var groupType = $(this).attr("attr_group_type"); 
			var status = $(this).attr("attr_status"); 
			if(type != null && type=="other"){
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
			}else{
				 var jobj = {
				  fromDate: callCenterUserFDate,                          
				  toDateStr:callCenterUserTDate, 
				  deptId:deptId,
				  sourceId:sourceId, 
				  stateId:1,
				  LocationId:locationId,
				  statusId:statusId,
				  areaType:areaType,
				  groupType:groupType ,
				  type:type			  
				}   
				$.ajax({    
				  type : "POST",
				  url  : "getLocationWiseFeebbackAlertAction.action",  
				  dataType: 'json',       
				  data: {task:JSON.stringify(jobj)},        
				}).done(function(result){
					buildAlertStatusWise(result,status);  
				});
			} 
		
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
		
		var type = $(this).attr("attr_type");
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
		
		 if(type != null && type=="other"){
			 getStatusWiseAlert(fromDate,toDate,deptId,sourceId,locationId,statusId,rangeType);
		 }else{
			 getFeedbackAlert(fromDate,toDate,deptId,sourceId,locationId,statusId,rangeType,type);
		 }
		
	});
	function getStatusWiseAlert(fromDate,toDate,deptId,sourceId,locationId,statusId,rangeType){
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
	}
		function getFeedbackAlert(fromDate,toDate,deptId,sourceId,locationId,statusId,rangeType,type){
				var jobj = {
				  fromDate: fromDate,                       
				  toDateStr:toDate,  
				  deptId:deptId,
				  sourceId:sourceId,                                      
				  stateId:1,
				  locationId:locationId,
				  statusId : statusId,      
				  rangeType:rangeType,
                  type:type				  
				  }
				$.ajax({    
				  type : "POST",
				  url  : "getFeedbackAlertAction.action",  
				  dataType: 'json',
				  data: {task:JSON.stringify(jobj)},
				}).done(function(result){
					  buildGrivenceDetailsTableOld(result);
				});
		}
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
		str+='<table class="table table-inr-x table-bordered" style="border-collapse:collapse;" id="dataTableForOuterSec">';
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
						if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "feebbackAlert" || result[0].subList1[i].name == "pendingFeedBack"){	
						str+='<th style="background-color:#ECEBD6">'+result[0].subList1[i].statusType+'</th>';
						}else if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "reopen"){
							str+='<th style="background-color:#C9AC82">'+result[0].subList1[i].statusType+'</th>';
						}else{
							str+='<th>'+result[0].subList1[i].statusType+'</th>';
						}
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
			
				if(result[i].name == "OTHER"){
					str+='<tr>';
					str+='<td>'+result[i].name+'</td>'; 
				}else{
					str+='<tr >';
					str+='<span data-toggle="collapse" data-target="#demo'+i+'" class="accordion-toggle"><td class="panchayatDataCls" attr_position="'+i+'" attr_location_id="'+result[i].id+'" attr_group_type="panchayat"><i class="glyphicon glyphicon-plus-sign"></i> '+result[i].name+'</td></span>'; 
				}
				if(result[i].name == "OTHER"){
					str+='<td class="bellowLvlCls" attr_type="other" attr_area_type="tehsil" attr_group_type="district" attr_location_id="'+locationId+'" attr_status_id="0" attr_status="All Status"><a >'+result[i].totalAlertCnt+'</a></td>';  
				}else{
					str+='<td class="bellowLvlCls" attr_type="other" attr_area_type="tehsil" attr_group_type="tehsil" attr_location_id="'+result[i].id+'" attr_status_id="0" attr_status="All Status"><a >'+result[i].totalAlertCnt+'</a></td>';  
				}
					 
					var len = result[i].subList1.length;
					len=parseInt(len)+2;
					for(var j in result[i].subList1){
						var type = "other";
						if(result[i].subList1[j].name != null){
						  type = result[i].subList1[j].name;	
						}
						if(result[i].subList1[j].totalAlertCnt != 0){        
							if(result[i].name == "OTHER"){
								str+='<td class="bellowLvlCls" attr_type="'+type+'" attr_area_type="tehsil" attr_group_type="district" attr_location_id="'+locationId+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_status="'+result[i].subList1[j].statusType+'"><a href="#">'+result[i].subList1[j].totalAlertCnt+'</a></td>';
							}else{
								str+='<td class="bellowLvlCls" attr_type="'+type+'" attr_area_type="tehsil" attr_group_type="tehsil" attr_location_id="'+result[i].id+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_status="'+result[i].subList1[j].statusType+'"><a href="#">'+result[i].subList1[j].totalAlertCnt+'</a></td>';
							}
						}else{
							str+='<td>-</td>';
						}      
					}
				str+='</tr>';
				
						
				str+='<tr style="display:none;" class="hiddenRow" attr_id="demo'+i+'">';
					str+='<td colspan="'+len+'" >';
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
	str+='<div class="table-responsive">';
	str+='<table class="table table-inr" style="border: 1px solid rgb(72, 72, 72); background: rgb(248, 243, 255) none repeat scroll 0% 0%;">';
		str+='<thead>';
			str+='<tr>';
				if(groupType=="panchayat"){
					str+='<th>Panchayat</th>';  
				}else{
					str+='<th>Location Name</th>';
				}
				str+='<th>Total</th>';
				for(var i in result[0].subList1){       
					if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "feebbackAlert" || result[0].subList1[i].name == "pendingFeedBack"){	
						str+='<th style="background-color:#ECEBD6">'+result[0].subList1[i].statusType+'</th>';
						}else if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "reopen"){
							str+='<th style="background-color:#C9AC82">'+result[0].subList1[i].statusType+'</th>';
						}else{
							str+='<th>'+result[0].subList1[i].statusType+'</th>';
						}
				}
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			
			str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				if(result[i].name == "OTHER"){
					str+='<td class="bellowLvlCls" attr_type="other" attr_area_type="panchayat" attr_group_type="tehsil" attr_location_id="'+locationId+'" attr_status_id="0" attr_status="All Status"><a href="#">'+result[i].totalAlertCnt+'</a></td>';
				}else{
					str+='<td class="bellowLvlCls" attr_type="other" attr_area_type="panchayat" attr_group_type="panchayat" attr_location_id="'+result[i].id+'" attr_status_id="0" attr_status="All Status"><a href="#">'+result[i].totalAlertCnt+'</a></td>';
				}
				
				for(var j in result[i].subList1){
                    var type = "other";					
                      if(result[i].subList1[j].name != null){
						type = result[i].subList1[j].name;  
					  }				
					if(result[i].subList1[j].totalAlertCnt != 0){
						if(result[i].name == "OTHER"){
							str+='<td class="bellowLvlCls" attr_type="'+type+'" attr_area_type="panchayat" attr_group_type="tehsil" attr_location_id="'+locationId+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_status="'+result[i].subList1[j].statusType+'"><a href="#">'+result[i].subList1[j].totalAlertCnt+'</a></td>';
						}else{
							str+='<td class="bellowLvlCls" attr_type="'+type+'" attr_area_type="panchayat" attr_group_type="panchayat" attr_location_id="'+result[i].id+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_status="'+result[i].subList1[j].statusType+'"><a href="#">'+result[i].subList1[j].totalAlertCnt+'</a></td>';
						}
						
					}else{
						str+='<td>-</td>';
					}      
				}
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
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
          if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "feebbackAlert" || result[0].subList1[i].name == "pendingFeedBack"){	
				str+='<th style="background-color:#ECEBD6">'+result[0].subList1[i].statusType+'</th>';
				}else if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "reopen"){
					str+='<th style="background-color:#C9AC82">'+result[0].subList1[i].statusType+'</th>';
				}else{
					str+='<th>'+result[0].subList1[i].statusType+'</th>';
				}
		}
	        str+='<tr>';	 
	        str+='</thead>';	
			str+='<tbody>'
			str+='<tr>';
			str+='<td style="cursor:pointer;"  attr_type="other" class="getAlertDtlsOnLocCls" attr_from_date="'+result[0].fromDateStr+'" attr_to_date="'+result[0].toDateStr+'" attr_status_id="0" attr_location_id="'+result[0].id+'">'+result[0].totalAlertCnt+'</td>';
	
		for( var i in result[0].subList1){
	        var type = "other";			
		    if(result[0].subList1[i].name != null){
				type = result[0].subList1[i].name;
			}
			if(result[0].subList1[i].totalAlertCnt != 0){
				str+='<td  style="cursor:pointer;" class="getAlertDtlsOnLocCls" attr_type="'+type+'" attr_from_date="'+result[0].fromDateStr+'" attr_to_date="'+result[0].toDateStr+'" attr_status_id="'+result[0].subList1[i].statusTypeId+'" attr_location_id="'+result[0].id+'" style="background-color:#ecebd6;">'+result[0].subList1[i].totalAlertCnt+'</td>';
			}else{
				str+='<td>-</td>'; 
			}
		    
       }
			str+='<tr>'; 
	        str+='</tbody>'
			str+='</table>';
		 $("#totalAlertDistricTableId").html(str);
}












//date wise report

function getGrievanceReportDayWise(){
	$("#dayWiseGrivenaceTableId").html(spinner);
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
			$("#dayWiseGrivenaceTableId").html('');
			if(result != null && result.length > 0){
				buildGrievanceReportDayWise(result,rangeType);
			}else{
				$("#dayWiseGrivenaceTableId").html('No Data Available.');
			}
		}); 
}
function buildGrievanceReportDayWise(result,rangeType) {  
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
           if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "feebbackAlert" || result[0].subList1[i].name == "pendingFeedBack"){	
						str+='<th style="background-color:#ECEBD6">'+result[0].subList1[i].statusType+'</th>';
						}else if(result[0].subList1[i].name !=null && result[0].subList1[i].name == "reopen"){
							str+='<th style="background-color:#C9AC82">'+result[0].subList1[i].statusType+'</th>';
						}else{
							str+='<th>'+result[0].subList1[i].statusType+'</th>';
						}
		}
		
        str+=' </tr>';
        str+='</thead>';
		str+='<tbody>';
		var locTotal = 0;
		for(var i in result){  
		 
		   
			str+='<tr>'; 
				str+='<td data-toggle="tooltip" data-placement="top" title="'+result[i].fromDateStr+"-"+result[i].toDateStr+'">'+result[i].day+'</td>';
				if(result[i].totalAlertCnt == 0){
					str+='<td>-</td>';
				}else{
					str+='<td style="cursor:pointer;" class="getAlertDtlsOnDateWise" attr_type="other" attr_from_date="'+result[i].fromDateStr+'" attr_to_date="'+result[i].toDateStr+'" attr_status_id="0" attr_location_id="0">'+result[i].totalAlertCnt+'</td>';
				}
				
				locTotal = parseInt(locTotal) + parseInt(result[i].totalAlertCnt);
			for(var j in result[i].subList1){
				  var alertType = "other";
				if(result[i].subList1[j].totalAlertCnt != 0){
					 if(result[i].subList1[j].name != null && result[i].subList1[j].name.length > 0){
				         alertType = result[i].subList1[j].name;
			           }
					str+='<td style="cursor:pointer;" class="getAlertDtlsOnDateWise"  attr_type="'+alertType+'" attr_from_date="'+result[i].fromDateStr+'" attr_to_date="'+result[i].toDateStr+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_location_id="0">'+result[i].subList1[j].totalAlertCnt+'</td>';
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
		str+='</tbody>';
		str+='</table>';
		$('#CategoryWiseGrivenaceTableId').html(str);  
		 $('#CatWiseGrievanceReportTableId').DataTable({  
			"order": [[ 1, "asc" ]]  
		});
 }

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

			$('#selectDistrictId').chosen();
			$('#selectDistrictId').trigger("chosen:updated");
			
	}); 
} 
//abcd
	
function getCadreGreivienceEfficiency(sliderVal){
	$("#totalHeadingRangeCount").html('');
	getAverageIssuePendingDays();
	$("#efficiencyId").html(spinner);
    var alertstatusIds = [];
	var deptIds=[];
	var sourceIds =[];
	
	var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
	deptIds.push(deptId);  
	if(sourceId==0){
		sourceIds.push(5);       
		sourceIds.push(4);
		sourceIds.push(2);
		sourceIds.push(3);
	}else if(sourceId==4){
		sourceIds.push(4);
	}else if(sourceId==2){
		sourceIds.push(2);
	}else if(sourceId==3){   
		sourceIds.push(3);
	}else if(sourceId==5){   
		sourceIds.push(5);
	}
    var jobj = {
		deptIds :deptIds,
		sourceIds:sourceIds,
		rangeValue:sliderVal,         
		alertstatusIds:$("#statusId").val(),
		fromDate: callCenterUserFDate,                           
		toDateStr:callCenterUserTDate, 
    }
    $.ajax({
      type : "POST",
      url  : "getAlertEfficiencyListAction1.action",
      dataType: 'json',
      data: {task:JSON.stringify(jobj)},
    }).done(function(result){
		$("#efficiencyId").html('');
      if(result!=null){
		  if(result[0] !=null){
			  $("#totalHeadingRangeCount").html(result[0].ttlAlrtss);
		  }else{
			  $("#totalHeadingRangeCount").html(" - ");
		  }
			
				var str = "";
				var str1 = "";
				str+='<div class="col-sm-12">';
				str+='<ul class="list-inline slickSlider">';
					for(var  i in result)
					{
						str+='<li class="col-sm-2">';
							str+='<table class="table table-condensed">';
								str+='<tr>';
									
									str+='<td>'+result[i].name+'</td>';
								str+='</tr>';
								str+='<tr>';
									
									str+='<td class="text-success" style="border-bottom:1px solid #d3d3d3;">'+result[i].effcncyPrcnt+' %</td>';
								str+='</tr>';
								
							str+='</table>';
						str+='</li>';
					}
					str+='</ul>';
					str+='</div>';
					
					str1+='<div class="col-sm-12 m_top20">';
				str1+='<ul class="list-inline slickSliderRange">';
					for(var  i in result)
					{
						str1+='<li class="col-sm-2">';
							str1+='<table class="table table-condensed">';
								str1+='<tr>';
									
									str1+='<td>'+result[i].range+' (days)</td>';
								str1+='</tr>';
								str1+='<tr>';
									
									str1+='<td class="text-success" style="border-bottom:1px solid #d3d3d3;">'+result[i].rangeCount+'</td>';
								str1+='</tr>';
								
							str1+='</table>';
						str1+='</li>';
					}
					str1+='</ul>';
					str1+='</div>';
				
			}
			
			$("#efficiencyId").html(str);
			$('.slickSlider').slick({
						slide: 'li',
						slidesToShow: 6,
						slidesToScroll: 3,
						infinite: false,
						swipe:false,
						touchMove:false,
						variableWidth: false
					});
			$("#efficiencyRangeId").html(str1);
			$('.slickSliderRange').slick({
						slide: 'li',
						slidesToShow: 6,
						slidesToScroll: 3,
						infinite: false,
						swipe:false,
						touchMove:false,
						variableWidth: false
					});
    });
}

$(document).on("change",".grievanceEffOnchange",function(){ 
    getCadreGreivienceEfficiency(sliderVa1);
}); 

function getStatusWiseFeebbackAlertDtls(statusId,type){
       
	   $("#grivenaceModalHeedingId").html("");    
		$("#grievanceDtlsModalId").modal("show"); 
		$("#removeClassModal").removeClass("closeSecondModal")	
		$("#grevinceDetailsId").html(spinner);
	
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
          statusId : statusId,      
          rangeType:rangeType,
          type:type          
          }
        $.ajax({    
          type : "POST",
          url  : "getFeedbackAlertAction.action",  
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
        }).done(function(result){
			$("#grevinceDetailsId").html('');
              buildGrivenceDetailsTableOld(result);
        });
    }