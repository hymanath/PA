var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
 var customStartToursDate = moment().subtract(parseInt(91)+parseInt(getDay()), 'days').format('DD/MM/YYYY')
 var customEndToursDate = moment().subtract(parseInt(getDay()), 'days').format('DD/MM/YYYY');
onloadTourCalls();
 function onloadTourCalls(){
	  getDesignationWiseTourSubmittedOverviewDtls();
	  getNotSubmittedCandidateDetailsByFilter(2);
 }
 $("#noofMonthsId").html('');
 $("#noofMonthsId").append("<option value='1'>1</option>"); 
 $("#noofMonthsId").append("<option value='2' selected>2</option>");
 $("#noofMonthsId").chosen();
 $("#noofMonthsId").trigger("chosen:updated");
 $("#tourNewExDateRangePickerId").daterangepicker({
		opens: 'left',
	     startDate: customStartToursDate,
         endDate:customEndToursDate,  
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: { ////moment().endOf('Year')
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(1, 'years').startOf('year'), moment()],
        }
	});
	function getDay(){
		var date = new Date();
		var dd = date.getDate(); 
		return dd;
	}
	
	$('#tourNewExDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
	   customStartToursDate = picker.startDate.format('DD/MM/YYYY');
	   customEndToursDate = picker.endDate.format('DD/MM/YYYY');
	   
	    var spiltStartDate = picker.startDate.format('YYYY/MM/DD');
	    var spiltEndDate =  picker.endDate.format('YYYY/MM/DD');
		
		var fromDate =new Date(spiltStartDate); // Date picker (text fields)
		var toDate = new Date(spiltEndDate);
		var months=0;
			months = (toDate.getFullYear() - fromDate.getFullYear()) * 12;
			months -= fromDate.getMonth();
			months += toDate.getMonth();
		if (toDate.getDate() < fromDate.getDate()){
			months--;
		}
		 $("#noofMonthsId").html('');
		for(var i=1;i<months;i++){
			$("#noofMonthsId").append("<option value='"+i+"'>"+i+"</option>"); 
		}
		 $("#noofMonthsId").chosen();
		$("#noofMonthsId").trigger("chosen:updated");
		 getNotSubmittedCandidateDetailsByFilter(months);
		 getDesignationWiseTourSubmittedOverviewDtls();
	 });
 function getDesignationWiseTourSubmittedOverviewDtls()
	{    
		$("#overAllTourDetailsDivId").html(spinner);
	 	var jsObj = { 
	 				 stateId : "1",
					 fromDate : customStartToursDate,
					 toDate : customEndToursDate
				   }
		$.ajax({
			type : 'POST',
			url : 'getDesignationWiseTourSubmittedOverviewDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		      if(result !=null){
				  buildDesignationWiseTourSubmittedOverviewDtls(result);
			  }else{
				$("#overAllTourDetailsDivId").html("No Data Available");
			  }
		});
	}
 
 function  buildDesignationWiseTourSubmittedOverviewDtls(result){
	 var str='';
	 str+='<div class="row">';
			str+='<div class="col-sm-12 m_top10">';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Total</th>';
								str+='<th>Submitted</th>';
								str+='<th>Submitted<br/>(Before 15th Date)</th>';
								str+='<th>Submitted<br/>(After 15th Date)</th>';
								str+='<th>Not Submitted</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							str+='<tr>';
								str+='<td>'+result.totalCandiateCount+'</td>';
								str+='<td>'+result.submittedCandiateCount+'</td>';
								str+='<td>'+result.before15thDateTourSubmittedCoun+'</td>';
								str+='<td>'+result.after15thDateTourSubmittedCoun+'</td>';
								str+='<td>'+result.notSubmittedCandidateCount+'</td>';
							str+='</tr>';
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top10">';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Designation</th>';
								str+='<th>Total</th>';
								str+='<th>Submitted</th>';
								str+='<th>Submitted<br/>(Before 15th Date)</th>';
								str+='<th>Submitted<br/>(After 15th Date)</th>';
								str+='<th>Not Submitted</th>';
								str+='<th>%</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						if(result.subList2 !=null && result.subList2.length>0){
							for(var i in result.subList2){
								str+='<tr>';
									str+='<td>'+result.subList2[i].designation+'</td>';
									str+='<td>'+result.subList2[i].totalCandiateCount+'</td>';
									str+='<td>'+result.subList2[i].submittedCandiateCount+'</td>';
									str+='<td>'+result.subList2[i].before15thDateTourSubmittedCoun+'</td>';
									str+='<td>'+result.subList2[i].after15thDateTourSubmittedCoun+'</td>';
									str+='<td>'+result.subList2[i].notSubmittedCandidateCount+'</td>';
									str+='<td>'+result.subList2[i].notSubmittedPer+'</td>';
								str+='</tr>';
							}
						}else{
							str+='<tr>';
								str+='<td colspan="7">No Data Available</td>';
							str+='</tr>';
						}
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	 $("#overAllTourDetailsDivId").html(str);
 }
 function getNotSubmittedCandidateDetailsByFilter(months)
	{    
		$("#toursSubmittedNoOfMonthsId").html(spinner);
	 	var jsObj = { 
	 				 stateId : "1",
					 fromDate : customStartToursDate,
					 toDate : customEndToursDate,
					 noOfMonth:months
				   }
		$.ajax({
			type : 'POST',
			url : 'getNotSubmittedCandidateDetailsByFilterAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		    if(result !=null && result.length>0){
				buildNotSubmittedCandidateDetailsByFilter(result);
			}else{
				$("#toursSubmittedNoOfMonthsId").html("No Data Available");
			}
		});
	}
	
	function buildNotSubmittedCandidateDetailsByFilter(result){
		var str='';
		
			str+='<div class="row">';
				str+='<div class="col-sm-12 m_top10">';
					str+='<h5 class="text_bold text-capital" >Tours Not Submitted Candidates List</h5>';
					str+='<div class="table-responsive m_top10">';
						str+='<table class="table details-overview">';
							str+='<thead>';
								str+='<tr>';
									str+='<th>Parliament</th>';
									str+='<th>Constituency</th>';
									str+='<th>Name</th>';
									str+='<th>Designation</th>';
									str+='<th>Not Submitted Months</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result){
									str+='<tr>';
										if(result[i].addressVO.parliamentName !=null && result[i].addressVO.parliamentName.trim().length>0){
											str+='<td>'+result[i].addressVO.parliamentName+'</td>';
										}else{
											str+='<td> - </td>';
										}
										if(result[i].addressVO.constituencyName !=null && result[i].addressVO.constituencyName.trim().length>0){
											str+='<td>'+result[i].addressVO.constituencyName+'</td>';
										}else{
											str+='<td> - </td>';
										}
										if(result[i].name !=null && result[i].name.trim().length>0){
											str+='<td>'+result[i].name+'</td>';
										}else{
											str+='<td> - </td>';
										}
										if(result[i].designation !=null && result[i].designation.trim().length>0){
											str+='<td>'+result[i].designation+'</td>';
										}else{
											str+='<td> - </td>';
										}
										if(result[i].notSubmittedNoOfMonth !=null && result[i].notSubmittedNoOfMonth.length>0){
											str+='<td>'+result[i].notSubmittedNoOfMonth+'</td>';
										}else{
											str+='<td> - </td>';
										}
									str+='</tr>';
								}
							
							str+='</tbody>';
						str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		$("#toursSubmittedNoOfMonthsId").html(str);
	}
 
$(document).on("change","#noofMonthsId",function(){
	var noofMonth = $(this).val();
	getNotSubmittedCandidateDetailsByFilter(noofMonth);
});