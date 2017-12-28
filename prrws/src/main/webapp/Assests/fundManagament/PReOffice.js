var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
getEOfcDepartWiseOverviewDetails();


function getEOfcDepartWiseOverviewDetails(){
	$("#eOfficeDeparmentsOverViewBlock").html(spinner);
	
	var json = {
		//fromDate:"",	
		//toDate:""	
	}
	$.ajax({                
	
		type:'POST',    
		url: 'getEOfcPrAndRdDepartsOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		/* eOfcDeptResult = result;
		//getEofficeDesignationWiseDetails()
		if(type == 'onload')
		{
			for(var i in result){
				if(result[i].departmentName != null && result[i].departmentName == "ITE & C")
					$("#itcDeptWiseCount").html(result[i].totalCount+'/<small style="color:#fff;font-size:14px;top:0px;">'+result[i].created+'</small>');
			}
		}else {*/
			buildEOfcDepartWiseOverviewDetails(result);
		//}
	});		
}

function buildEOfcDepartWiseOverviewDetails(result){
	var str = '';
	$("#lastUpdatedTimeDivId").html(result[0].lastUpdatedTime);
	str+='<div class="">';
	str+='<h5 class="text-center"><b>PANCHAYAT RAJ, RURAL DEVELOPMENT & RURAL WATER SUPPLY DEPARTMENTS</b></h5>';
		str+='<div class="table-responsive m_top10">';
			str+='<table class="table table-bordered table_ITC" id="dataTableITCDepartment">';
				str+='<thead style="background-color:#CCCCCC">';
					str+='<tr>';
						str+='<th>Departments</th>';
						str+='<th>Total</th>';
						str+='<th>Action</th>';
						str+='<th>Total Pendency</th>';
						str+='<th>%</th>';
						str+='<th>0 - 7 days</th>';
						str+='<th>8 - 15 days</th>';
						str+='<th>16 - 30 days</th>';
						str+='<th>31 - 60 days</th>';
						str+='<th> > 60 days</th>';
					str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						if(result[i].departmentName != "ITE & C"){
							str+='<tr>';
								str+='<td style="cursor:pointer;"><i class="fa fa-external-link departmentDetailsCls" aria-hidden="true" class="" attr_department_id="'+result[i].departmentId+'" attr_department_name="'+result[i].departmentName+'"></i><b> '+result[i].departmentName+'</b></td>';
								str+='<td >'+result[i].created+'</td>';
								str+='<td >'+result[i].actionFiles+'</td>';
								str+='<td >'+result[i].totalCount+'</td>';
								if(result[i].percentage < 10){
									str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
								}else if(result[i].percentage >= 10 && result[i].percentage < 20){
									str+='<td style="background-color:#FFBA00;color:#fff">'+result[i].percentage+'</td>';
								}else if(result[i].percentage >= 20){
									str+='<td style="background-color:#ff0000;color:#fff">'+result[i].percentage+'</td>';
								}
								str+='<td >'+result[i].zeroToSeven+'</td>';
								str+='<td >'+result[i].eightToFifteen+'</td>';
								str+='<td >'+result[i].sixteenToThirty+'</td>';
								str+='<td >'+result[i].thirtyoneToSixty+'</td>';
								str+='<td >'+result[i].aboveSixty+'</td>';
							str+='</tr>';
						}
					}
					str+='</tbody>';
					for(var i in result){
						if(result[i].departmentName == "ITE & C"){
							str+='<tr>';
								str+='<td style="text-align: right;" class="font_weight">GRAND TOTAL</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].created+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].actionFiles+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].totalCount+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].percentage+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].zeroToSeven+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].eightToFifteen+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].sixteenToThirty+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].thirtyoneToSixty+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].aboveSixty+'</td>';
							str+='</tr>';
						}
					}
					
			str+='</table>';
		str+='</div>';
	str+='</div>';
	str+='<div class="m_top20">';
		str+='<h5 class="text-center"><b>HEAD OF THE DEPARTMENTS</b></h5>';
		str+='<div class="table-responsive m_top10">';
			str+='<table class="table table-bordered table_ITC" id="dataTableITCHODS">';
				str+='<thead style="background-color:#CCCCCC">';
					str+='<tr>';
						//str+='<th style="color:#A349A4;text-align:center">HODS</th>';
						str+='<th>Post</th>';
						str+='<th>Department</th>';
						str+='<th>Total</th>';
						str+='<th>Action</th>';
						str+='<th>Total Pendency</th>';
						str+='<th>%</th>';
						str+='<th>0 - 7 days</th>';
						str+='<th>8 - 15 days</th>';
						str+='<th>16 - 30 days</th>';
						str+='<th>31 - 60 days</th>';
						str+='<th> > 60 days</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result[0].subList){
						if(result[0].subList[i] != null){
							str+='<tr>';
								str+='<td>'+result[0].subList[i].postName+'</td>';
								console.log(result[0].subList[i].postName)
								str+='<td>'+result[0].subList[i].departmentName+'</a></td>';
								str+='<td>'+result[0].subList[i].created+'</td>';
								str+='<td>'+result[0].subList[i].actionFiles+'</td>';
								str+='<td>'+result[0].subList[i].totalCount+'</td>';
								if(result[0].subList[i].percentage < 10){
									str+='<td style="background-color:#00AF50;color:#fff">'+result[0].subList[i].percentage+'</td>';
								}else if(result[0].subList[i].percentage >= 10 && result[0].subList[i].percentage < 20){
									str+='<td style="background-color:#FFBA00;color:#fff">'+result[0].subList[i].percentage+'</td>';
								}else if(result[0].subList[i].percentage >= 20){
									str+='<td style="background-color:#FF0000;color:#fff">'+result[0].subList[i].percentage+'</td>';
								}
								str+='<td >'+result[0].subList[i].zeroToSeven+'</td>';
								str+='<td >'+result[0].subList[i].eightToFifteen+'</td>';
								str+='<td >'+result[0].subList[i].sixteenToThirty+'</td>';
								str+='<td >'+result[0].subList[i].thirtyoneToSixty+'</td>';
								str+='<td >'+result[0].subList[i].aboveSixty+'</td>';
							str+='</tr>';
						}
					}
					str+='<tbody>';
			str+='</table>';
		str+='</div>';
	str+='</div>';
	
$("#eOfficeDeparmentsOverViewBlock").html(str);
$("#dataTableITCDepartment").dataTable({
		"paging":   false,
		"info":     false,
		"searching": false,
		"autoWidth": true
});
$("#dataTableITCHODS").dataTable({
	"paging":   false,
	"info":     false,
	"searching": false,
	"autoWidth": true
});
}

$(document).on("click",".departmentDetailsCls",function(){	
	var departmentId =  $(this).attr("attr_department_id")
	var departmentName =  $(this).attr("attr_department_name")
	$("#departmentModalId").modal("show");
	$("#headingTitle").html("<b>"+departmentName+ "  DETAILS</b>")
	getEofficeDesginationDetailsByDepartment(departmentId);
});

function getEofficeDesginationDetailsByDepartment(departmentId){
	$("#departmentDetailsDivId").html(spinner);
	var json = {
		departmentId:departmentId,	
		//fromDate:"2017-11-01",	
		//toDate:"2017-12-31"		
	}
	$.ajax({                
		type:'POST',    
		url: 'getEofficePrAndRdDeptDesginationDetails',  
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildEofficeDesginationDetailsByDepartment(result)
		}else{
			$("#departmentDetailsDivId").html("No Data Available");
		}
	});		
}
function buildEofficeDesginationDetailsByDepartment(result){
	var str='';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table_ITC" id="dataTableDepartmentId">';
						str+='<thead style="background-color:#CCCCCC">';
						str+='<tr>';
							str+='<th class="text-center">POST NAME</th>';
							str+='<th class="text-center">EMPLOYEE</th>';
							str+='<th>Total</th>';
							str+='<th>Action</th>';
							str+='<th>Total Pendency</th>';
							str+='<th>%</th>';
							str+='<th>0 - 7 days</th>';
							str+='<th>8 - 15 days</th>';
							str+='<th>16 - 30 days</th>';
							str+='<th>31 - 60 days</th>';
							str+='<th> > 60 days</th>';
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								str+='<td>'+result[i].designation+'</td>';
								str+='<td>'+result[i].employeeName+'</br><small> ( '+result[i].orgName+' )</small> </td>';
								str+='<td>'+result[i].created+'</td>';
								str+='<td>'+result[i].actionFiles+'</td>';
								str+='<td>'+result[i].totalCount+'</td>';
								if(result[i].percentage < 10){
									str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
								}else if(result[i].percentage>= 10 && result[i].percentage < 20){
									str+='<td style="background-color:#FFBA00;color:#fff">'+result[i].percentage+'</td>';
								}else if(result[i].percentage >= 20){
									str+='<td style="background-color:#FF0000;color:#fff">'+result[i].percentage+'</td>';
								}
								//str+='<td>'+result[i].percentage+'</td>';
								str+='<td>'+result[i].zeroToSeven+'</td>';
								str+='<td >'+result[i].eightToFifteen+'</td>';
								str+='<td>'+result[i].sixteenToThirty+'</td>';
								str+='<td >'+result[i].thirtyoneToSixty+'</td>';
								str+='<td>'+result[i].aboveSixty+'</td>';
							str+='</tr>';
						}
						str+='</tbody>';
				str+='</table>';
			str+='</div>';
		$("#departmentDetailsDivId").html(str);
		$("#dataTableDepartmentId").dataTable({
			"paging":   true,
			"info":     false,
			"searching": true,
			"autoWidth": true,
			"iDisplayLength": 13,
			"aaSorting": [], 
			"aLengthMenu": [[13, 15, 20, -1], [13, 15, 20, "All"]]
		});
}