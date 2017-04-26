 alert(2)
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
	alert(22)
	 	var jsObj ={
			fromDate: 01/01/2016,                       
			toDateStr:01/12/2017,  
			deptId:49,
			sourceId:1,
			rangeType:"Day",
			stateId:1
	    }
	    $.ajax({
	    type:'GET',         
	    url: 'getGrievanceReportAction.action',
	    data: {task :JSON.stringify(jsObj)}
	    }).done(function(result){
	    	
	    });
	    
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