
var start = moment().subtract(29, 'days');
var end = moment();

function cb(start, end) {
	$('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
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

cb(start, end); 
    
	
    
	
    $('#table2').DataTable();

getGrievanceReport();      
 
function getGrievanceReport(){
	 	var jsObj ={
			fromDate: "01/01/2017",                       
			toDateStr:"01/04/2017",  
			deptId:49,
			sourceId:0,
			rangeType:"week",                                         
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
				str+='<td>  <a class="js-open-modal " href="#" data-toggle="modal" data-target="#myModal2" >'+
					 ' <i class="glyphicon glyphicon-plus-sign"></i>'+result[i].name+'</a></td>';
				str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_location_id="'+result[i].id+'">'+result[i].totalAlertCnt+'</td>';
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
				str+='<td>'+result[0].subList1[i].grandTotal+'</td>';    
			}
			for(var i in result[0].subList2){
				str+='<td>'+result[0].subList2[i].grandTotal+'</td>';    
			}   
		str+='</tr>';
		str+='</tbody>';
	str+='</table>';
	$('#grivenaceTableId').html(str);
	$('#grievanceReportTableId').DataTable();  
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
	  fromDate : "01/11/2016",//2016-11-01
	  toDate:"01/05/2017"//2017-05-01
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
onLoadInitialisations();  	  
function onLoadInitialisations(){                     
	$(document).on("click",".getAlertDtlsCls",function(){
		 var locationId = $(this).attr("attr_location_id");
		 var group = $(this).attr("attr_group_type");
		 var statusId = $(this).attr("attr_status_id");
		 var pattern = $(this).attr("attr_pattern");
		 alert(locationId+":"+statusId+":"+group+":"+pattern);
		 var jobj = {
			fromDate: "01/01/2017",                       
			toDateStr:"01/04/2017",  
			deptId:49,
			sourceId:0,                                      
			stateId:1,
			locationId:locationId,
			statusId:statusId,
			group:group,
			pattern:pattern
			
		}
		$.ajax({
			type : "POST",
			url  : ".action",
			dataType: 'json',
			data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			var str ='';
			if(result != null){
				
			}
		});
		 
	});        
}     