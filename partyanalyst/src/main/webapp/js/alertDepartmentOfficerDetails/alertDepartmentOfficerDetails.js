var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var start = moment();
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
				buildHighcharForStatusFeedbackAndReopen(result);
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
function buildHighcharForStatusFeedbackAndReopen(result){
	//status wise
	var statusNamesArray =[];
	var statusIdNameArr=[];
	var totalAlerts = 0;
	for(var i in result[0].subList){
			 statusNamesArray.push(result[0].subList[i].name);
			 statusIdNameArr.push({"y":result[0].subList[i].grandTotal,"status":result[0].subList[i].name,"id":result[0].subList[i].id});
			 totalAlerts = parseInt(totalAlerts) + parseInt(result[0].subList[i].grandTotal);
	}
	$("#totalAlertCountId").html(totalAlerts);
	buildHighchart("statusWiseAlertCntId",statusNamesArray,statusIdNameArr);
	//feedback wise
	statusNamesArray =[];
	statusIdNameArr=[];
	for(var i in result[0].subList1){
			 statusNamesArray.push(result[0].subList1[i].name);
			 statusIdNameArr.push({"y":result[0].subList1[i].grandTotal,"status":result[0].subList1[i].name,"id":result[0].subList1[i].id});
	}
	buildHighchart("feedbackWiseAlertCntId",statusNamesArray,statusIdNameArr);
	//reopen alerts     
	statusNamesArray =[];
	statusIdNameArr=[];
	statusNamesArray.push("Reopen");
	var reopenTotalCount = 0;
	for(var i in result){
		 reopenTotalCount = parseInt(reopenTotalCount) + parseInt(result[i].reopenCount);
	}
	statusIdNameArr.push({"y":reopenTotalCount,"status":"Reopen","id":11});
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
							return this.value.toString().substring(0, 5)+'....';
						},
						
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
			           str+='<th>'+result[0].subList[i].name+'</th>';
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
				str+='<tr>'; 
					str+='<td >'+result[i].name+'</td>';         
					str+='<td>'+result[i].totalCount+'</td>';
					for(var j in result[i].subList){
						if(result[i].subList[j].count != 0){
							str+='<td>'+result[i].subList[j].count+'</td>';
						}else{
							str+='<td>-</td>';
						}      
					}
					for(var j in result[i].subList1){  
						if(result[i].subList1[j].count != 0){        
							str+='<td>'+result[i].subList1[j].count+'</td>';
						}else{      
							str+='<td>-</td>';   
						}
					}
					if(result[i].reopenCount != 0){        
						str+='<td>'+result[i].reopenCount+'</td>';
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
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,"statusWise","alert","","count","desc",0,0,"","",locationLevelId,position,"singleCall")
});
//getAlertDetailsForGrievanceReportClick();
function getAlertDetailsForGrievanceReportClick(){      
	 var source = $("#selectMediaId").val();
	 var locationLevelIdArr=[];                                                                            
	 var newspapersGlobalArr=[];    
	 var channelGlobalArr=[];
	 var callCenterGlobalArr=[];
	 
   var jsObj={
		fromDateStr:"01/01/1997",
		toDateStr:"31/12/2027",
		stateId : 1,
		printIdArr : newspapersGlobalArr,
		electronicIdArr : channelGlobalArr,		
		govtDepartmentId : 49,
		parentGovtDepartmentScopeId : 1,
		sortingType :"",
		order :"",
		alertType :"alert",
		group :"status",
		subLevels:locationLevelIdArr,   
		chanelIdArr:callCenterGlobalArr,
		searchType:"statusWise",
		filterParentScopeId :8,
		filterScopeValue:0,
		sourseId:0 ,  
			statusId:2
	}
    $.ajax({
    type:'GET',         
    url: 'getAlertDetailsForGrievanceReportClickAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		
	});
}