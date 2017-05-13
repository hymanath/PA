getDepartmentDetailsByDepartmentId(49);
function getDepartmentDetailsByDepartmentId(departmentId){ 
   var jsObj ={
		departmentId:departmentId,
		designationType:"levelWiseOfficer"
    }
    $.ajax({
    type:'GET',                        
    url: 'getDepartmentDetailsByDepartmentAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		getLevelWiseAlerts(result,departmentId);
    }); 
}
function getLevelWiseAlerts(result,departmentId){
	if(result[0].subList1 != null && result[0].subList1.length > 0){
		for(var i in result[0].subList1){
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,result[0].subList1[i].id,"statusWise","alert","","count","desc",0,0,"","",0,i);
			//alert(result[0].subList1[i].id);   
		}
	}
}      
//getStateThenGovtDeptScopeWiseAlertCount(49,1,"statusWise","alert","","count","desc",0,0,"","",0,0);  
function getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentGovtDepartmentScopeId,searchType,alertType,divId,sortingType,orderType,filterParentScopeId,filterScopeValue,actionType,selectionType,locationLevelId,position){      
	 
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
		filterScopeValue:filterScopeValue
	}
    $.ajax({
    type:'GET',         
    url: 'getOfficerLocationWiseDepartmentOverviewAlertCountAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(position == 0){
			//build highchart for status, feedback and reopen
			buildHighcharForStatusFeedbackAndReopen(result);
		}
		buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType,locationLevelId);
	});
} 
function buildHighcharForStatusFeedbackAndReopen(result){
	var statusNamesArray =[];
	var statusIdNameArr=[];
	for(var i in result[0].subList){
			 statusNamesArray.push(result[0].subList[i].name);
			 statusIdNameArr.push({"y":result[0].subList[i].grandTotal,"status":result[0].subList[i].name,"id":result[0].subList[i].id});
	}
	buildHighchart("statusWiseAlertCntId",statusNamesArray,statusIdNameArr);
	statusNamesArray =[];
	statusIdNameArr=[];
	for(var i in result[0].subList1){
			 statusNamesArray.push(result[0].subList1[i].name);
			 statusIdNameArr.push({"y":result[0].subList1[i].grandTotal,"status":result[0].subList1[i].name,"id":result[0].subList1[i].id});
	}
	buildHighchart("feedbackWiseAlertCntId",statusNamesArray,statusIdNameArr);
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
							return this.value.toString().substring(0, 7)+'....';
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