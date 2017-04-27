
/* var start = moment().subtract(29, 'days');
var end = moment();

function cb(start, end) {
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

cb(start, end);   */
    
	var callCenterUserFDate=moment().format("DD/MM/YYYY");
	var callCenterUserTDate=moment().format("DD/MM/YYYY");

	   
	
	$("#reportrange").daterangepicker({  
	opens: 'left',
	startDate: callCenterUserFDate,
	endDate: callCenterUserTDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
	'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]            
		
	}
});

$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
	callCenterUserFDate = picker.startDate.format('DD/MM/YYYY');
	callCenterUserTDate = picker.endDate.format('DD/MM/YYYY');
	getTotalLocationWiseGrivenaceReport();
});

	
    $('#table1').DataTable(); 
	 
$(document).on("click",".daterangeClorCls",function(){ 
         $(".daterangeClorCls").removeClass("dateColorCls");
}); 

getGrievanceReport();
 
function getGrievanceReport(){

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
			}
	    });
	    
}	 
function buildGrievanceReport(result) {
	var str='';
		 str+='<table id="table1" class="table table-bordered " cellspacing="0">';
		 str+='<colgroup>'
			str+='</colgroup>'   
			     str+=' <thead>';
                       str+='<tr>';
                       str+=' <th>District</th>';
                       str+=' <th>Total</th>';
                      
                       for(var i in result[0].subList1){       
						    str+=' <th>'+result[0].subList1[i].statusType+'</th>';
					   }
					   for(var j in result[0].subList2){         
						    str+=' <th style="background-color:#ecebd6">'+result[0].subList2[j].day+'</th>';
					   }
                       str+=' </tr>';
                      str+='</thead>';
					   str+='<tbody>';
					   for(var i in result){
						   str+='<tr>'; 
						  str+='<td>  <a class="js-open-modal " href="#" data-toggle="modal" data-target="#myModal2" >'+
						  ' <i class="glyphicon glyphicon-plus-sign"></i>'+result[i].name+'</a></td>';
						  str+='<td>'+result[i].totalAlertCnt+'</td>';
						  for(var j in result[i].subList1){ 
							str+='<td>'+result[i].subList1[j].totalAlertCnt+'</td>';
						  }
						  for(var j in result[i].subList2){ 
							str+='<td>'+result[i].subList2[j].totalAlertCnt+'</td>';
						  }     
						  
							str+='</tr>';   
					    }
	
					   str+='</tbody>';
					   str+='</table>';
					   $('#grivenaceTableId').html(str);
	
}	


getAverageIssuePendingDays();
function getAverageIssuePendingDays(){
	var deptIds=[];
	var sourceIds =[];
	
	deptIds.push(49);
	sourceIds.push(1);
	sourceIds.push(2);
	sourceIds.push(3);
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
		}
		$('#issuePendingCntId').text(str);
    });
    
    }	   	
function getMediaInformation(){
	  
   var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
	 
	 var jobj = {
          fromDate: callCenterUserFDate,                       
		  toDateStr:callCenterUserTDate,  
			deptId:deptId,
			sourceId:sourceId,
			rangeType:"day",
			stateId:1
	 }
	$.ajax({
      type : "GET",
      url  : "getGrievanceReportAction.action",
      dataType: 'json',
      data: {task:JSON.stringify(jobj)},
    }).done(function(result){
		
		
		
	});
}
//on dept change
function getDepartmentInformation(){
	
	 var sourceId=$("#selectMediaId").val();
     var deptId=$("#selecDepartmentId").val();
	
	 var jobj = {
          fromDate: callCenterUserFDate,                       
		  toDateStr:callCenterUserTDate,  
			deptId:deptId,
			sourceId:sourceId,
			rangeType:"day",
			stateId:1
	  }
	  $.ajax({
      type : "GET",
      url  : "getGrievanceReportAction.action",
      dataType: 'json',
      data: {task:JSON.stringify(jobj)},
    }).done(function(result){
		
	});
}
function getTotalLocationWiseGrivenaceReport(){  

    var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
    var jsObj ={
		fromDate:callCenterUserFDate,                         
		toDateStr:callCenterUserTDate,
        sourceId :sourceId,  
        deptId:deptId,
        stateId:1		 
    }
    $.ajax({
    type:'GET',         
    url: 'getGrievanceReportAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		
	});
}
 
 $(document).on("click",".rangeTypeCls",function(){     
	var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val(); 
	var rangeType=$(this).attr("attr_range_val");
	alert(rangeType);   
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
		
	});
});
buildLocationWiseGrivenacereportGraph();
function buildLocationWiseGrivenacereportGraph(){
	
	
	alert(44)
	  Highcharts.chart('barGraph', {
            colors: ['#FFCF2C'],
            chart: {
                backgroundColor: '#3C3D41',

                type: 'column'
            },
            title: {
                text: 'TDP',
                align: 'left'
            },
            xAxis: {

                min: 0,
                lineColor: 'transparent',
                gridLineWidth: 0,
                minorGridLineWidth: 0,
                type: 'category'
            },
            yAxis: {
                title: {
                    text: "aaa",
                    align: "left"
                },
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

            tooltip: {
                enabled: true
            },


            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,

                    }
                }
            },

            plotOptions: {
                series: {
                    borderRadius: 7.5,
                    pointWidth: 15
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
                name: '',
                colorByPoint: false,
                lineWidth: 1,
                data: [{
                    name: 'PENDING',
                    y: 4,

                }, {
                    name: 'NOTIFIED',
                    y: 3.5,

                }, {
                    name: 'ACTION IN PROGRESS',
                    y: 2,

                }, {
                    name: 'COMPLETED',
                    y: 3,

                }, {
                    name: 'UNABLE TO RESOLVE',
                    y: 4,

                }, {
                    name: 'ACTION NOT REQUIRED',
                    y: 1,

                }, {
                    name: 'DUPLICATE',
                    y: 1,

                }, {
                    name: 'WRONGLY MAPPED DESIGNATION',
                    y: 4,

                }, {
                    name: 'WRONGLY MAPPED DEPARTMENT',
                    y: 3,

                }, {
                    name: 'REJOINER',
                    y: 2,

                }, {
                    name: 'REOPEN',
                    y: 3.5,

                }, {
                    name: 'CLOSED',
                    y: 3,

                }, {
                    name: 'PROPOSAL',
                    y: 4,

                }]

            }],

        });
}