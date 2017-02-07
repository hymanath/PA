function getCadreEnrollmentYears(){
		 var jsObj={
		
		       };
			   
		 $.ajax({
			type : "GET",
			url : "getCadreEnrollmentYearsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					$("#tdpCommitteeYearId").append('<option value='+result[i].id+'>'+result[i].electionYear+'</option>');
				}
			}
		});
	}
$(document).on("click","#getDetailsId",function(){
	
});
function getCommitteeDetailsByEnrollement(){
	  var enrollmentIdsArr = new Array();
	  enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
	var jsObj={
			enrollmentIdsArr:enrollmentIdsArr
		};
			   
		 $.ajax({
			type : "GET",
			url : "getCommitteeDetailsByEnrollementIdAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					 var fYear = result[i].fromDate.split('-')[0];
					 var fMonth = result[i].fromDate.split('-')[1];
					 var fDate = result[i].fromDate.split('-')[2];
					 var tYear = result[i].toDate.split('-')[0];
					 var tMonth = result[i].toDate.split('-')[1];
					 var tDate = result[i].toDate.split('-')[2].substring(0,2);
					 
					$('#reportrange').data('daterangepicker').setStartDate(fDate+'/'+fMonth+'/'+fYear);
					$('#reportrange').data('daterangepicker').setEndDate(tDate+'/'+tMonth+'/'+tYear);
				}
				}
			});
	}