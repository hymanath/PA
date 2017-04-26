
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
    
	
    $('#table1').DataTable();
	
    $('#table2').DataTable();

getGrievanceReport();
 
function getGrievanceReport(){
	
	 	var jsObj ={
			fromDate: "01/04/2016",                       
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