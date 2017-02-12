function getCadreEnrollmentYears(id){
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
					if(id == 1)
						$("#tdpCommitteeYearId").append('<option value='+result[i].id+'>'+result[i].electionYear+'</option>');
					else if(id == 2)
						$("#tdpCommitteeYearId1").append('<option value='+result[i].id+'>'+result[i].electionYear+'</option>');
				}
			}
			if(id == 1){
				getCommitteeDetailsByEnrollement(1);
			}
			else if(id == 2){
				getCommitteeDetailsByEnrollement(2);
			}
		});
	}

function getCommitteeDetailsByEnrollement(id){
	  var enrollmentIdsArr = new Array();
	  if(id == 1)
		enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
	  else if(id == 2) 
		enrollmentIdsArr.push($("#tdpCommitteeYearId1").val());
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
					 
					 if(id == 1 ){
						 $('#reportrange').data('daterangepicker').setStartDate(fMonth+'/'+fDate+'/'+fYear);
						 $('#reportrange').data('daterangepicker').setEndDate(tMonth+'/'+tDate+'/'+tYear);
					 }else if(id == 2 ){
						$('#reportrange1').data('daterangepicker').setStartDate(fMonth+'/'+fDate+'/'+fYear);
						$('#reportrange1').data('daterangepicker').setEndDate(tMonth+'/'+tDate+'/'+tYear);
					 }else{
						$('#reportrange').data('daterangepicker').setStartDate(fDate+'/'+fMonth+'/'+fYear);
						$('#reportrange').data('daterangepicker').setEndDate(tDate+'/'+tMonth+'/'+tYear);
					 }
				}
				}
				
				if(id==1)
					onLoadcimmitteeDashboardCalls();
			});
	}
	