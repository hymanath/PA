var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalUserLevelId=0;
var globalUserLevelValues = [];	
var start=moment();
var end=moment();
function cb(start, end){
	$('#reportrange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
}

$('#reportrange').daterangepicker({
	opens: 'right',
	startDate: start,
	endDate: end,
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
}, cb);
 
var callCenterUserFDate=moment().startOf('month').format("DD/MM/YYYY");
var callCenterUserTDate=moment().format("DD/MM/YYYY");

$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
	callCenterUserFDate = picker.startDate.format('DD/MM/YYYY');
	callCenterUserTDate = picker.endDate.format('DD/MM/YYYY');
	$("#statusWiseAlertCntId").html(spinner);
	$("#feedbackWiseAlertCntId").html(spinner);
	$("#reopenAlertCntId").html(spinner);
	getDepartmentDetailsByDepartmentId();  
});
function getMediaInformation(){
	$("#statusWiseAlertCntId").html(spinner);
	$("#feedbackWiseAlertCntId").html(spinner);
	$("#reopenAlertCntId").html(spinner);
	getDepartmentDetailsByDepartmentId();  
}
function getDepartmentInformation(){
	$("#statusWiseAlertCntId").html(spinner);
	$("#feedbackWiseAlertCntId").html(spinner);
	$("#reopenAlertCntId").html(spinner);
	getDepartmentDetailsByDepartmentId();  
} 
getDepartmentDetailsByDepartmentId();     
function getDepartmentDetailsByDepartmentId(){

	var departmentId = $("#selecDepartmentId").val();
	var jsObj ={
		departmentId:departmentId,
		designationType:"levelWiseOfficer"
    }
    $.ajax({
    type:'GET',                        
    url: 'getDepartmentDetailsByDepartmentAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result != null && result.length > 0){
			getLevelWiseAlerts(result,departmentId);
		}else{
			$("#statusWiseAlertCntId").html("No Data Available.");
			$("#feedbackWiseAlertCntId").html("No Data Available.");
			$("#reopenAlertCntId").html("No Data Available.");   
			$("#allLocationLevelDivId").html("No Data Available.");
			$("#totalAlertCountId").html("-");
		}
    }); 
}
function getLevelWiseAlerts(result,departmentId){
	if(result[0].subList1 != null && result[0].subList1.length > 0){
		buildTableDivId(result);
		for(var i in result[0].subList1){
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,result[0].subList1[i].id,"statusWise","alert","","count","desc",0,0,"","",0,i,"overall");
		}
	}
}      
function getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentGovtDepartmentScopeId,searchType,alertType,divId,sortingType,orderType,filterParentScopeId,filterScopeValue,actionType,selectionType,locationLevelId,position,callType){      
	 var source = $("#selectMediaId").val();
	
	 var locationLevelIdArr=[];
	 
	 if(locationLevelId == null || locationLevelId == 0){
		 locationLevelIdArr =[];
	 }else{
		 locationLevelIdArr.push(locationLevelId);
	 }
	 
	 var newspapersGlobalArr=[];    
	 var channelGlobalArr=[];
	 var callCenterGlobalArr=[];
	 
   var jsObj={
		fromDateStr:callCenterUserFDate,
		toDateStr:callCenterUserTDate,   
		stateId : 1,
		printIdArr : newspapersGlobalArr,
		electronicIdArr : channelGlobalArr,		
		govtDepartmentId : departmentId,
		parentGovtDepartmentScopeId : parentGovtDepartmentScopeId,
		sortingType :sortingType,
		order :orderType,
		alertType :alertType,
		group :"status",
		subLevels:locationLevelIdArr,   
		chanelIdArr:callCenterGlobalArr,
		searchType:searchType,
		filterParentScopeId :filterParentScopeId,
		filterScopeValue:filterScopeValue,
		source:source     
	}
    $.ajax({
    type:'GET',         
    url: 'getOfficerLocationWiseDepartmentOverviewAlertCountAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result != null && result.length > 0){
			if(position == 0){
				//build highchart for status, feedback and reopen
				buildHighcharForStatusFeedbackAndReopen(result,parentGovtDepartmentScopeId);
			}
			buildOfficerLocationWiseDepartmentOverviewAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType,locationLevelId,position);
		}else{
			if(callType == "singleCall"){
				$("#locationLevelWiseTableId"+position).html("No Data Available.");   
			}else{
				$("#locationLevelWiseTableId"+position).html("No Data Available.");
				$("#statusWiseAlertCntId").html("No Data Available.");
				$("#feedbackWiseAlertCntId").html("No Data Available.");
				$("#reopenAlertCntId").html("No Data Available.");
				$("#totalAlertCountId").html("-");
			}
		}
		
	});
} 
function buildHighcharForStatusFeedbackAndReopen(result,parentGovtDepartmentScopeId){
	//status wise
	var statusNamesArray =[];
	var statusIdNameArr=[];
	var totalAlerts = 0;
	for(var i in result[0].subList){
			 statusNamesArray.push(result[0].subList[i].name);
			 statusIdNameArr.push({"y":result[0].subList[i].grandTotal,"extra":"alert-"+result[0].subList[i].name+"-"+result[0].subList[i].id+"-"+result[0].subList[i].grandTotal});
			 totalAlerts = parseInt(totalAlerts) + parseInt(result[0].subList[i].grandTotal);
	}
	$("#totalAlertCountId").html(totalAlerts);
	buildHighchart("statusWiseAlertCntId",statusNamesArray,statusIdNameArr,parentGovtDepartmentScopeId);
	//feedback wise
	statusNamesArray =[];
	statusIdNameArr=[];
	for(var i in result[0].subList1){
			 statusNamesArray.push(result[0].subList1[i].name);
			 statusIdNameArr.push({"y":result[0].subList1[i].grandTotal,"extra":"feedback-"+result[0].subList1[i].name+"-"+result[0].subList1[i].id+"-"+result[0].subList1[i].grandTotal});
	}
	buildHighchart("feedbackWiseAlertCntId",statusNamesArray,statusIdNameArr,parentGovtDepartmentScopeId);
	//reopen alerts     
	statusNamesArray =[];
	statusIdNameArr=[];
	statusNamesArray.push("Reopen");
	var reopenTotalCount = 0;
	for(var i in result){
		 reopenTotalCount = parseInt(reopenTotalCount) + parseInt(result[i].reopenCount);
	}
	statusIdNameArr.push({"y":reopenTotalCount,"extra":"reopen-Reopen-0-"+reopenTotalCount});
	buildHighchart("reopenAlertCntId",statusNamesArray,statusIdNameArr,parentGovtDepartmentScopeId);
} 
function buildHighchart(divId,statusNamesArray,statusIdNameArr,parentGovtDepartmentScopeId){
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
								var value = (this.extra).split("-");
								if(parentGovtDepartmentScopeId == 1){
									var alertType = value[0];
									var locationValId =0;
									var filterParentScopeId =0;
									var filterScopeValue =0;
									var statusName = value[1];
									var statusId = value[2];
									var totalCount = value[3];
								}
								getAlertDetailsForGrievanceReportClick(parentGovtDepartmentScopeId,alertType,locationValId,filterParentScopeId,filterScopeValue,statusId,statusName,totalCount)
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

function buildTableDivId(result){
	var str='';
	for(var i in result[0].subList1){
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading headingColor">';
				str+='<div class="row">';
					str+='<div class="col-md-8 m_top5">';   
						str+='<h4 class="panel-title text-capital fontColor">'+result[0].subList1[i].name+' Grievance Report </h4>';  
					str+='</div>';
					if(result[0].subList1[i].idnameList !=null && result[0].subList1[i].idnameList.length>0){
						str+='<div class="col-sm-4 col-xs-12 col-md-2">';
							str+='<select class="form-control locationLevelWiseOnChange" id="locationLevelNamesId'+result[0].id+''+result[0].subList1[i].id+'" attr_department_id="'+result[0].id+'" attr_parent_id="'+result[0].subList1[i].id+'" attr_postion_val="'+i+'">';
								str+='<option value="0">ALL</option>';
								for(var l in result[0].subList1[i].idnameList){
									str+='<option value="'+result[0].subList1[i].idnameList[l].id+'">'+result[0].subList1[i].idnameList[l].name+' </option>';
								}	
							str+='</select>';
						str+='</div>';
					}
					str+='<div class="col-md-2">';
						str+='<button class="btn btn-success btn-sm" onclick="generateExcel3();">';
							str+='<i class="glyphicon glyphicon-download-alt"></i> Download';
						str+='</button>';   
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<div  class="row">';  
					str+='<div class="col-md-12">';
						str+='<div class="table-responsive m_top20" id="locationLevelWiseTableId'+i+'"></div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	$("#allLocationLevelDivId").html(str);
	
	$("#statusWiseAlertCntId").html(spinner);
	$("#feedbackWiseAlertCntId").html(spinner);
	$("#reopenAlertCntId").html(spinner);
	
	for(var i in result[0].subList1){
		$("#locationLevelWiseTableId"+i).html(spinner);
	}
}
function buildOfficerLocationWiseDepartmentOverviewAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType,locationLevelId,position){
	var str='';
        str+='<table id="grievanceReportTableId'+position+'" class="table table-bordered " cellspacing="0">';
	        str+='<thead>';
	        	str+='<tr>';
					str+='<th>Location</th>';
	        		str+='<th>Total</th>';
					for(var i in result[0].subList){       
			           str+='<th >'+result[0].subList[i].name+'</th>';
					}
					for(var j in result[0].subList1){         
						str+='<th style="background-color:#ecebd6">'+result[0].subList1[j].name+'</th>';
					}
					str+='<th style="background-color:#C9AC82">Reopen</th>';  
				str+=' </tr>';
			str+='</thead>';
			str+='<tbody>';
			var locTotal = 0;
			for(var i in result){
				var filterScopeValue ='';
				var filterParentScopeId='';
				var locationId='';
				if(parentGovtDepartmentScopeId == 1){
					filterScopeValue =0;
					filterParentScopeId = result[i].id;
					locationId = result[i].id;
				}else{
					filterScopeValue = result[i].id;
					filterParentScopeId = parentGovtDepartmentScopeId;
					locationId = locationLevelId;
				}
				str+='<tr>'; 
					str+='<td >'+result[i].name+'</td>';         
					str+='<td class="getAlertDetailsCls" attr_parent_id = "'+parentGovtDepartmentScopeId+'" attr_type="alert" attr_location_val="'+locationId+'" attr_parent_scope_id="'+filterParentScopeId+'" attr_parent_scope_value="'+filterScopeValue+'" attr_status_id="0" attr_status_name="Total" attr_count="'+result[i].totalCount+'" style="cursor:pointer;color: #337ab7;">'+result[i].totalCount+'</td>';
					for(var j in result[i].subList){
						if(result[i].subList[j].count != 0){
							str+='<td class="getAlertDetailsCls" attr_parent_id = "'+parentGovtDepartmentScopeId+'" attr_type="alert" attr_location_val="'+locationId+'" attr_parent_scope_id="'+filterParentScopeId+'" attr_parent_scope_value="'+filterScopeValue+'" attr_status_id="'+result[i].subList[j].id+'" attr_status_name="'+result[i].subList[j].name+'" attr_count="'+result[i].subList[j].count+'" style="cursor:pointer;color: #337ab7;">'+result[i].subList[j].count+'</td>';
						}else{
							str+='<td>-</td>';
						}      
					}
					for(var j in result[i].subList1){  
						if(result[i].subList1[j].count != 0){
        
							str+='<td class="getAlertDetailsCls" attr_parent_id = "'+parentGovtDepartmentScopeId+'" attr_type="feedback" attr_location_val="'+locationId+'" attr_parent_scope_id="'+filterParentScopeId+'" attr_parent_scope_value="'+filterScopeValue+'" attr_status_id="'+result[i].subList1[j].id+'" attr_status_name="'+result[i].subList1[j].name+'" attr_count="'+result[i].subList1[j].count+'" style="cursor:pointer;color: #337ab7;">'+result[i].subList1[j].count+'</td>';
						}else{      
							str+='<td>-</td>';   
						}
					}
					if(result[i].reopenCount != 0){        
						str+='<td class="getAlertDetailsCls" attr_parent_id = "'+parentGovtDepartmentScopeId+'" attr_type="reopen" attr_location_val="'+locationId+'" attr_parent_scope_id="'+filterParentScopeId+'" attr_parent_scope_value="'+filterScopeValue+'" attr_status_id="0" attr_status_name="Reopen" attr_count="'+result[i].reopenCount+'" style="cursor:pointer;color: #337ab7;">'+result[i].reopenCount+'</td>';
					}else{      
						str+='<td>-</td>';
					}
				str+='</tr>';
			}
	 		str+='</tbody>';
 		 str+='</table>';
	 $('#locationLevelWiseTableId'+position).html(str);
	 $('#grievanceReportTableId'+position).DataTable({
		 "order": [[ 1, "asc" ]]  
	 });  
}

$(document).on("change",".locationLevelWiseOnChange",function(){
		var departmentId = $(this).attr("attr_department_id");
		var parentId = $(this).attr("attr_parent_id");
		var position = $(this).attr("attr_postion_val");
		var locationLevelId = $(this).val();
		$("#locationLevelWiseTableId"+position).html(spinner);
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,"statusWise","alert","","count","desc",0,0,"","",locationLevelId,position,"singleCall");
		
});
$(document).on("click",".getAlertDetailsCls",function(){
		
		var parentId = $(this).attr("attr_parent_id");
		var alertType = $(this).attr("attr_type");
		var locationValId = $(this).attr("attr_location_val");
		var filterParentScopeId = $(this).attr("attr_parent_scope_id");
		var filterScopeValue = $(this).attr("attr_parent_scope_value");
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var totalCount = $(this).attr("attr_count");
		
		
		getAlertDetailsForGrievanceReportClick(parentId,alertType,locationValId,filterParentScopeId,filterScopeValue,statusId,statusName,totalCount)
		
});
function getAlertDetailsForGrievanceReportClick(parentId,alertType,locationValId,filterParentScopeId,filterScopeValue,statusId,statusName,totalCount){      
	var source = $("#selectMediaId").val();
	var newspapersGlobalArr =[];
	var channelGlobalArr =[];
	var callCenterGlobalArr =[];
	
	 var locationLevelIdArr=[];
	 
	 if(locationValId == null || locationValId == 0){
		 locationLevelIdArr =[];
	 }else{
		 locationLevelIdArr.push(locationValId);
	 }
	 
	 var departmentId = $("#selecDepartmentId").val();   
		$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
   var jsObj={
		fromDateStr:callCenterUserFDate,
		toDateStr:callCenterUserTDate,
		stateId : 1,
		printIdArr : newspapersGlobalArr,
		electronicIdArr : channelGlobalArr,		
		govtDepartmentId : departmentId,
		parentGovtDepartmentScopeId : parentId,//result.sublist[0]
		sortingType :"",
		order :"",
		alertType :alertType,//alert-graph,feedback,reopen
		group :"status",//status
		subLevels:locationLevelIdArr,   
		chanelIdArr:callCenterGlobalArr,
		searchType:"statusWise",//statusWise
		filterParentScopeId :filterParentScopeId,//graphblock=0
		filterScopeValue:filterScopeValue,//stateblock,graph=0
		sourseId:source , //categeries 
		statusId:statusId//alert=alertStatusId,feedback=alert_feedback_status_id,reopen=0
	}
    $.ajax({
    type:'GET',         
    url: 'getAlertDetailsForGrievanceReportClickAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result != null && result.length > 0){
			$("#totalAlertsModalTabId").html('');
			buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});
}