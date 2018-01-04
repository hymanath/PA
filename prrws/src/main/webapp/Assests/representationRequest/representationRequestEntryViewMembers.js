var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var workIdsArr=[];
var selectedWorkIdsArr=[];
var selectedDeptIdsArr=[];
var startDate = moment().subtract(7,"year").format("DD-MM-YYYY");
var endDate = moment().add(38,"year").format("DD-MM-YYYY");
//getting Dynamic Browser URL
var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/cadreDetailsAction")));
wurl = wurl.replace("/PartyAnalyst","");


$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});

function locationLevelRefresh(){
	
	$("#districtCandId").html('<option value="0">Select District</option>');
	$("#districtCandId").trigger('chosen:updated');
	
	$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	$("#constituencyCanId").trigger('chosen:updated');
	
	$("#mandalCanId").html('<option value="0">Select Mandal</option>');
	$("#mandalCanId").trigger('chosen:updated');

	$("#designationsId").html('<option value="0">Select Designation</option>');
	$("#designationsId").trigger('chosen:updated');
	
	$("#departmentId").html('<option value="0">Select Department</option>');
	$("#departmentId").trigger('chosen:updated');
	
	
	//$("#statusId").html('<option value="0">Select Status</option>');
	//$("#statusId").trigger('chosen:updated');
	
	$("#locationSelId").val('all');
	$("#locationSelId").trigger('chosen:updated');
	
	$("#nameId").val(' ');
	$("#mobileId").val(' ');
	$("#emailId").val(' ');
	$("#endorsmentNoId").val(' ');
	$("#summaryId").html("");
	$("#representationRequestEntryTable").html('');
	hideAndShowSelectBox();
}
function hideAndShowSelectBox(){
	
	$("#districtConsMandDivId").hide();
	$("#designationDiv").hide();
	$("#departMentsDiv").hide();
	$("#nameDivid").hide();
	$("#mobileDivid").hide();
	$("#emailDivid").hide();
	$("#endorsmentNoDivid").hide();	
}
$("#dateRangePicker").daterangepicker({
	opens:'left',
	startDate: startDate,
	endDate: endDate,
	locale: {
        format: "DD-MM-YYYY",
	},
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'All'	: [moment().subtract(7, 'year').startOf('year'), moment().add(38, 'year').endOf('year')],

	}
 
});
var dateRange=startDate+" - "+endDate
var inputDateRangVal=$("#dateRangePicker").val();
if(dateRange == inputDateRangVal){
	$("#dateRangePicker").val("All");
}
$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	startDate = picker.startDate.format("DD-MM-YYYY");
	endDate = picker.endDate.format("DD-MM-YYYY");
	 if(picker.chosenLabel == 'All')
    {
      $("#dateRangePicker").val('All');
    }
	locationLevelRefresh();
	
});
$(".chosen-select").chosen();

/* $(document).on('cut copy paste', function (e) {
	e.preventDefault();
}); */

$(document).on("click",".advancedSrchCls",function(){
	$("#advancedSearchVal").val('');
});

$(document).on("click",".selectedCls",function(){
	selectedWorkIdsArr=[];
	var isSelected=$(this).attr('isSeleted');
	var worksId=$(this).attr('attr_worksId');
	if(isSelected =='true'){
		if(worksId == 0){	
			$(".selectedCls").each(function(){
				$(".selectedCls").attr('isSeleted','false');
				$(".selectedCls").removeClass("btn-success");
				$(".selectedCls").addClass("btn-info");
			}); 
		}else{
			$(this).attr('isSeleted','false');
			$(this).removeClass("btn-success");
			$(this).addClass("btn-info");
			
			$(".selectedCls").each(function(){
				var innerIsSelected=$(this).attr('isSeleted');
				var innerWorkId=$(this).attr('attr_worksId');
				if(innerIsSelected =='true' && innerWorkId == 0){
					$(this).attr('isSeleted','false');
					$(this).removeClass("btn-success");
					$(this).addClass("btn-info");
				}
			});
		}
	}else if(isSelected =='false'){
		if(worksId == 0){	
			$(".selectedCls").each(function(){
				$(".selectedCls").attr('isSeleted','true');
				$(".selectedCls").addClass("btn-success");
				$(".selectedCls").removeClass("btn-info");
			});
		}else{
			$(this).attr('isSeleted','true');
			$(this).addClass("btn-success");
			$(this).removeClass("btn-info");
			
			var totalWorks =0;
			$(".selectedCls").each(function(){
				var innerIsSelected=$(this).attr('isSeleted');
				var innerWorkId=$(this).attr('attr_worksId');
				if(innerIsSelected =='true' && innerWorkId > 0){
					totalWorks=totalWorks+1;
				}
			});
			
			
			if(totalWorks == workIdsArr.length){
				$(".selectedCls").each(function(){
					var innerWorkId=$(this).attr('attr_worksId');
					if(innerWorkId == 0){
						$(this).attr('isSeleted','true');
						$(this).addClass("btn-success");
						$(this).removeClass("btn-info");
					}
				});
			
			}
		}
	}
	
	$(".selectedCls").each(function(){
		var innerWorkId=$(this).attr('attr_worksId');
		var workId=$(this).attr('attr_worksId');
		var deptId=$(this).attr('attr_worksId');
		var isseleted=$(this).attr('isSeleted');
		if(innerWorkId > 0 && isseleted=='true'){
			selectedWorkIdsArr.push(workId);
			selectedDeptIdsArr.push(deptId);
		}
	});
});

$(document).on("change",".clearDataCls",function(){
	$("#representationRequestEntryTable").html('');
	$("#summaryId").html("");
});
function clearData(){
	$("#summaryId").html("");
	$("#representationRequestEntryTable").html('');
}

function representationRequestEntryTable(result){
	var str='';
	str+='<div class="panel panel-default" style="margin-top:-8px;">';
		str+='<div class="panel-heading" style="background-color:#344650; color:#fff;" data-toggle="collapse">';
			str+='<h4 class="panel-title">';
			str+='<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">SUBWORK PETITION DETAILS </a></h4>';
		str+='</div>';
		str+='<div id="collapseTwo" class="panel-collapse collapse in">';
		str+='<div class="panel-body">';
		str+='<div class="table-responsive">';
			str+='<table class="table table_customRep table-bordered" id="workDetailsTab">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Entd&nbsp;ID</th>';
						str+='<th>RAISED&nbsp;DATE</th>';
						str+='<th>Entd&nbsp;Date</th>';
						str+='<th>Representee&nbsp;Name</th>';
						str+='<th>Referrer&nbsp;Name</th>';
						str+='<th>Referrer&nbsp;Designation</th>';				
						str+='<th style="min-width:200px !important;">Work Name</th>';
						//str+='<th>No&nbsp;of&nbsp;Works</th>';
						str+='<th>Estimation Cost</th>';
						str+='<th>Status</th>';
						str+='<th>Action</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						for(var j in result[i].subList){
					var endorsmentNo='';
					str+='<tr>';
						if (result[i].subList[j].endorsementNO != null && result[i].subList[j].endorsementNO != 0){
							str+='<td>'+result[i].subList[j].endorsementNO+'</td>';
							endorsmentNo=result[i].subList[j].endorsementNO;
						}else
							str+='<td> - </td>';
						if (result[i].raisedDate != null && typeof(result[i].raisedDate) != "undefined")
							str+='<td>'+result[i].raisedDate+'</td>';
						else
							str+='<td> - </td>';
						
						if (result[i].subList[j].endorsmentDate != null && result[i].subList[j].endorsmentDate != "")
							str+='<td>'+result[i].subList[j].endorsmentDate+'</td>';
						else
							str+='<td> - </td>';
						if (result[i].name != null && typeof(result[i].name) != "undefined")
							str+='<td>'+result[i].name+'</td>';
						else
							str+='<td> - </td>';
						
						if (result[i].referrerName != null && typeof(result[i].referrerName) != "undefined")
							str+='<td>'+result[i].referrerName+'</td>';
						else
							str+='<td> - </td>';
						if (result[i].desigName != null && typeof(result[i].desigName) != "undefined")
							str+='<td>'+result[i].desigName+'</td>';
						else
							str+='<td> - </td>';
						if (result[i].subList[j].workName != null && result[i].subList[j].workName != "")
							str+='<td>'+result[i].subList[j].workName+'</td>';
						else
							str+='<td> - </td>';
						/* if (result[i].noOfWorks != null && typeof(result[i].noOfWorks) != "undefined")
							str+='<td>'+result[i].noOfWorks+'</td>';
						else
							str+='<td> - </td>'; */
						if (result[i].subList[j].estimationCost != "" && result[i].subList[j].estimationCost != "0")
							str+='<td>'+result[i].subList[j].estimationCost+'</td>';
						else
							str+='<td>-</td>';
						if (result[i].subList[j].statusType != "" && typeof(result[i].subList[j].statusType) != "undefined")
							str+='<td>'+result[i].subList[j].statusType+'</td>';
						else
							str+='<td>-</td>';
						str+='<td class="text-center"><i class="fa fa-eye viewBtnCls tooltipCls" aria-hidden="true" attr_enrorsNo="'+endorsmentNo+'" attr_petiotion_id="'+result[i].petitionId+'" attr_sub_work_id="'+result[i].subList[j].id+'" style="margin-right: 20px; font-size: 16px;cursor:pointer" data-toggle="tooltip" data-placement="top" title="View Petition"> </i>';
						
						//if(endorsmentNo != null && endorsmentNo != 'undefined' &&  (parseInt(endorsmentNo) ==0 || endorsmentNo=='') )
							str+='<a href="'+wurl+'/representationRequestEdit?petitionId='+result[i].petitionId+'" target="_blank"><i class="tooltipCls fa fa-pencil-square-o" aria-hidden="true" style="font-size: 16px;cursor:pointer" data-toggle="tooltip" data-placement="top" title="Edit Petition"></i></a>';
						
						str+='</td>';
					str+='</tr>';
					}
					}
				str+='</tbody>';
			str+='</table>';
			str+='</div>';
			str+='</div>';
		str+='</div>';	
	str+='</div>';
	$("#representationRequestEntryTable").html(str);
	$(".tooltipCls").tooltip();
	
	$("#workDetailsTab").dataTable({
		"paging":   true,
		"info":     false,
		"searching": true,
		"autoWidth": true,
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]]
	});
}
$('#advanceSearchBtnId').prop("checked",true);
if($('#advanceSearchBtnId').prop( "checked")){
	$("input[type='checkbox']").prop({disabled: true});
}
$("#advancedSearchButtonDivId").show();
$(document).on("change","#locationSelId",function(){
	$("#referralNameId").html('<option value="0">Select Referral Name</option>');
	$("#referralNameId").trigger('chosen:updated');
	$("#errMsgId").html("");
	$('.clearCls').val('');
	$("#departMentsDiv").hide();
	$("#designationDiv").hide()//inputSearchDivid
	$("#referralNameDiv").hide()//inputSearchDivid
	$("#nameDivid").hide();
	$("#mobileDivid").hide();
	$("#emailDivid").hide();
	$("#endorsmentNoDivid").hide();
	$("#districtCandId").html('<option value="0">Select District</option>');
	$("#districtCandId").trigger('chosen:updated');
	$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	$("#constituencyCanId").trigger('chosen:updated');
	$("#mandalCanId").html('<option value="0">Select Mandal</option>');
	$("#mandalCanId").trigger('chosen:updated');
	$("#designationsId").html('<option value="0">Select Designation</option>');
	$("#designationsId").trigger('chosen:updated');
	//$("#statusId").html('<option value="0">Select Status</option>');
	//$("#statusId").trigger('chosen:updated');
	$("#departmentId").html('<option value="0">Select Department</option>');
	$("#departmentId").trigger('chosen:updated');
	  $("#locationErrDivlId").html('');
	  $("#districtCandErrDiv").html('');
	  $("#constituencyCanErrDiv").html('');
	  $("#mandalCanErrDiv").html('');
	  $("#designationErrDiv").html('');
	  $("#departMentsErrDiv").html('');
	   $("#nameErrDivId").html('');
	   $("#mobileErrDivId").html('');
	   $("#emailErrDivId").html('');
	   $("#endorsmentNoErrDivId").html('');
	     $('#parametersList').show();
	    $("#nameId").html('');
	    $("#mobileId").html('');
	    $("#emailId").html('');
	  $("#endorsmentNoId").html('');
   $('#advanceSearchBtnId').prop("checked",false); 
	var searchType=$(this).val();
	var dateRangeStr =$("#dateRangePicker").val();
	getDistrictBySearchType(searchType,'districtCandId',dateRangeStr);
	if(searchType == 'all'){
		//$('#parametersList').hide();
		$("#districtConsMandDivId").hide();
		$('#advanceSearchBtnId').prop("checked",true);
		$("#advancedSearchButtonDivId").show();
		$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	    $("#constituencyCanId").trigger('chosen:updated');
        $("#mandalCanId").html('<option value="0">Select Mandal</option>');
	    $("#mandalCanId").trigger('chosen:updated');
		if($('#advanceSearchBtnId').prop( "checked")){
				$("input[type='checkbox']").prop({disabled: true});
			}
			
		return;
	}
	
	if(searchType == 'work' || searchType =='representee' || searchType =='referral'){
			$("#districtCandId").html('<option value="0">Select District</option>');
			$("#districtCandId").trigger('chosen:updated');
	
			$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	        $("#constituencyCanId").trigger('chosen:updated');
			$("#mandalCanId").html('<option value="0">Select Mandal</option>');
	        $("#mandalCanId").trigger('chosen:updated');
			$("#designationsId").html('<option value="0">Select Designation</option>');
	        $("#designationsId").trigger('chosen:updated');
			$("#nameId").val(' ');
			$("#mobileId").val(' ');
			$("#emailId").val(' ');
			$("#endorsmentNoId").val(' ');
		    $('#advanceSearchBtnId').prop("checked",true);
			$("#districtConsMandDivId").show();
			$("#advancedSearchButtonDivId").show();
			if($('#advanceSearchBtnId').prop( "checked")){
				$("input[type='checkbox']").prop({disabled: true});
			}
			
	}else if(searchType == 'referrelDesignation' || searchType == 'representeeDesignation'){
	
	$("#districtCandId").html('<option value="0">Select District</option>');
	$("#districtCandId").trigger('chosen:updated');
	
	$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	$("#constituencyCanId").trigger('chosen:updated');
	
	$("#mandalCanId").html('<option value="0">Select Mandal</option>');
	$("#mandalCanId").trigger('chosen:updated');
			$("#nameId").val(' ');
			$("#mobileId").val(' ');
			$("#emailId").val(' ');
			$("#endorsmentNoId").val(' ');
			if(searchType == 'referrelDesignation'){
		//if($('#advanceSearchBtnId').prop( "checked")){
				$("input[type='checkbox']").prop({disabled: true});
			//}
			//$("input[type='checkbox']").prop({disabled: false});
		/*  $('input[type="checkbox"]').click(function(){
           if($(this).prop("checked") == true){
		   $("#districtConsMandDivId").show();
	    }else if($(this).prop("checked") == false){
			 $("#districtConsMandDivId").hide();
		} 
			 
		 }); */
			}else{
				if($('#advanceSearchBtnId').prop( "checked")){
				$("input[type='checkbox']").prop({disabled: true});
			}
			$("input[type='checkbox']").prop({disabled: false});
		  $('input[type="checkbox"]').click(function(){
           if($(this).prop("checked") == true){
		   $("#districtConsMandDivId").show();
	    }else if($(this).prop("checked") == false){
			 $("#districtConsMandDivId").hide();
		} 
			 
		 }); 
			}
		  getDesignationsBySearchType(searchType,"designationsId");
		$("#designationDiv").show();
		if(searchType == 'referrelDesignation')
		$("#referralNameDiv").show();
		$("#districtConsMandDivId").hide();
		$("#advancedSearchButtonDivId").show();
		
	}else if(searchType == 'department'){
	$("#districtCandId").html('<option value="0">Select District</option>');
	$("#districtCandId").trigger('chosen:updated');
	
	$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	$("#constituencyCanId").trigger('chosen:updated');
	
	$("#mandalCanId").html('<option value="0">Select Mandal</option>');
	$("#mandalCanId").trigger('chosen:updated');
	        $("#nameId").val(' ');
			$("#mobileId").val(' ');
			$("#emailId").val(' ');
			$("#endorsmentNoId").val(' ');
	
	   $('#advanceSearchBtnId').prop("checked",false);
	getDepartmentsBySearchType(searchType,"departmentId");
		$("#departMentsDiv").show();
		$("#districtConsMandDivId").hide();
		$("#advancedSearchButtonDivId").show();
		$("input[type='checkbox']").prop({disabled: false});
	$('input[type="checkbox"]').click(function(){
           if($(this).prop("checked") == true){
		   $("#districtConsMandDivId").show()
	    }else if($(this).prop("checked") == false){
			 $("#districtConsMandDivId").hide();
		} 
			 
		 });
	
   }else if(searchType == 'name' ||  searchType =='mobile' ||   searchType =='email' || searchType =='endorsmentNO'){
		var searchType1=$(this).val();
		if(searchType1 == 'name'){
			$('#advanceSearchBtnId').prop("checked",false);
		    $("#nameDivid").show();
			 $("#districtConsMandDivId").hide();
			 $("#advancedSearchButtonDivId").show();
			 
				$("#districtCandId").html('<option value="0">Select District</option>');
				$("#districtCandId").trigger('chosen:updated');
				
				$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
				$("#constituencyCanId").trigger('chosen:updated');
				
				$("#mandalCanId").html('<option value="0">Select Mandal</option>');
				$("#mandalCanId").trigger('chosen:updated');
				
				$("#designationsId").html('<option value="0">Select Designation</option>');
				$("#designationsId").trigger('chosen:updated');
				$("#mobileId").val(' ');
				$("#emailId").val(' ');
				$("#endorsmentNoId").val(' ');
	
			 $("input[type='checkbox']").prop({disabled: false});
			 
		}else if(searchType1 == 'mobile'){
			$('#advanceSearchBtnId').prop("checked",false);
			$("#mobileDivid").show();
			 $("#districtConsMandDivId").hide();
			 $("#advancedSearchButtonDivId").show();
			 
			 $("#districtCandId").html('<option value="0">Select District</option>');
	         $("#districtCandId").trigger('chosen:updated');
	
	         $("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	         $("#constituencyCanId").trigger('chosen:updated');
	
	         $("#mandalCanId").html('<option value="0">Select Mandal</option>');
	         $("#mandalCanId").trigger('chosen:updated');
			 $("#designationsId").html('<option value="0">Select Designation</option>');
	         $("#designationsId").trigger('chosen:updated');
			 $("#nameId").val(' ');
	         $("#emailId").val(' ');
	         $("#endorsmentNoId").val(' ');
	        
	
			$("input[type='checkbox']").prop({disabled: false});
		}else if(searchType1 == 'email'){
			$('#advanceSearchBtnId').prop("checked",false);
			$("#emailDivid").show();
			$("#districtConsMandDivId").hide();
			$("#advancedSearchButtonDivId").show();
			
	        $("#districtCandId").html('<option value="0">Select District</option>');
	         $("#districtCandId").trigger('chosen:updated');
	
	         $("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	         $("#constituencyCanId").trigger('chosen:updated');
	
	         $("#mandalCanId").html('<option value="0">Select Mandal</option>');
	         $("#mandalCanId").trigger('chosen:updated');
			  $("#designationsId").html('<option value="0">Select Designation</option>');
	         $("#designationsId").trigger('chosen:updated');
			  $("#nameId").val(' ');
			  $("#mobileId").val(' ');
			  $("#endorsmentNoId").val(' ');
	         
			$("input[type='checkbox']").prop({disabled: false});
		}else if(searchType1 == 'endorsmentNO'){
			$('#advanceSearchBtnId').prop("checked",false);
			$("#endorsmentNoDivid").show();
			$("#districtConsMandDivId").hide();
			
	         $("#districtCandId").html('<option value="0">Select District</option>');
	         $("#districtCandId").trigger('chosen:updated');
	
	         $("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	         $("#constituencyCanId").trigger('chosen:updated');
	
	         $("#mandalCanId").html('<option value="0">Select Mandal</option>');
	         $("#mandalCanId").trigger('chosen:updated');
			  $("#designationsId").html('<option value="0">Select Designation</option>');
	          $("#designationsId").trigger('chosen:updated');
			  $("#nameId").val(' ');
			  $("#mobileId").val(' ');
			  $("#emailId").val(' ');
	         
			$("#advancedSearchButtonDivId").show();
			$("input[type='checkbox']").prop({disabled: false});
		}
	
		 $('input[type="checkbox"]').click(function(){
           if($(this).prop("checked") == true){
            $("#districtConsMandDivId").show();

	    }else if($(this).prop("checked") == false){
			  $("#districtConsMandDivId").hide();
		} 
			 
		 });
	}		 
});
$(document).on("change","#districtCandId",function(){
	$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	$("#constituencyCanId").trigger('chosen:updated');
	$("#mandalCanId").html('<option value="0">Select Mandal</option>');
	$("#mandalCanId").trigger('chosen:updated');
	var searchType=$("#locationSelId").val();
	var distictId=$(this).val();
	getConstituenciesBySearchTypeAndDistrict(searchType,distictId,'constituencyCanId');
});
$(document).on("change","#constituencyCanId",function(){
	$("#mandalCanId").html('<option value="0">Select Mandal</option>');
	$("#mandalCanId").trigger('chosen:updated');
	var searchType=$("#locationSelId").val();
	var consituencyId=$(this).val();
	getMandalsBySearchTypeAndConstituency(searchType,consituencyId,'mandalCanId');
});
$(document).on("change","#designationsId",function(){
	$("#referralNameId").html('<option value="0">Select Referral Name</option>');
	$("#referralNameId").trigger('chosen:updated');
	var searchType=$("#locationSelId").val();
	var dateRangeStr =$("#dateRangePicker").val();
	getDistrictBySearchType(searchType,'districtCandId',dateRangeStr);
	var desigIds= [];
	var desig = $(this).val();
	if(desig != null || desig !=0){
			desigIds =  desig;
			getPetitionReferredMemberDetails(desigIds);
	}
	
});
$(document).on("change","#departmentId",function(){
	var searchType=$("#locationSelId").val();
	var dateRangeStr =$("#dateRangePicker").val();
	getDistrictBySearchType(searchType,'districtCandId',dateRangeStr);
});
function getDistrictBySearchType(searchType,selBoxId,dateRangeStr){
	
	$("#"+selBoxId).html("");
	$("#"+selBoxId).trigger('chosen:updated');
	var desigIds= [];
	var deptIds = [];
	var desigType='';
	
	  var desig=$("#designationsId").val();//
	  if(desig != null || desig !=0){
			desigIds =  desig;
		var filterType=$("#locationSelId").val();
		if(filterType == 'referrelDesignation'){
			desigType="referral";
		}else if(filterType == 'representeeDesignation'){
			desigType="representee";
		}
	  }
	var depts =$("#departmentId").val();
	if(depts != null || depts !=0){
		deptIds=depts;
	}
 var json = {
		 filterType :searchType,
		 fromDate :startDate,
		 toDate : endDate,
		 deptIdsList:deptIds,
		 designationIds:desigIds,
		 pType:desigType
		}           
	$.ajax({              
		type:'POST',    
		url: 'getDistrictBySearchType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length >0){
			//$("#"+selBoxId).html("<option value='0'>All</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}
function getConstituenciesBySearchTypeAndDistrict(searchType,distictId,selBoxId){
	$("#selBoxId").html('<option value="0">Select Constituency</option>');
	$("#selBoxId").trigger('chosen:updated');
	$("#"+selBoxId).html("");
	var desigIds= [];
 var deptIds = [];
 var desigType='';
 
 var desig=$("#designationsId").val();//
 if(desig != null || desig !=0){
 desigIds = desig;
 var filterType=$("#locationSelId").val();
 if(filterType == 'referrelDesignation'){
 desigType="referral";
 }else if(filterType == 'representeeDesignation'){
 desigType="representee";
 }
 }
 var depts =$("#departmentId").val();
 if(depts != null || depts !=0){
 deptIds=depts;
 }
 var json = {
		 filterType :searchType,
		 searchLvlVals: distictId,
		 fromDate :startDate,
		 toDate : endDate,
		 deptIdsList:deptIds,
		 designationIds:desigIds,
		 type:desigType
		}           
	$.ajax({              
		type:'POST',    
		url: 'getConstituenciesBySearchTypeAndDistrict',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length >0){
			//$("#"+selBoxId).html("<option value='0'>All</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}
function getMandalsBySearchTypeAndConstituency(searchType,consituencyId,selBoxId){
	$("#"+selBoxId).html("<option value='0'>Select Mandal</option>");
	$("#"+selBoxId).trigger('chosen:updated');
	var desigIds= [];
	var deptIds = [];
	var desigType='';
	
	  var desig=$("#designationsId").val();//
	  if(desig != null || desig !=0){
			desigIds =  desig;
		var filterType=$("#locationSelId").val();
		if(filterType == 'referrelDesignation'){
			desigType="referral";
		}else if(filterType == 'representeeDesignation'){
			desigType="representee";
		}
	  }
	var depts =$("#departmentId").val();
	if(depts != null || depts !=0){
		deptIds=depts;
	}
 var json = {
		 filterType :searchType,
		 searchLvlVals: consituencyId,
		 fromDate :startDate,
		 toDate : endDate,
		 deptIdsList:deptIds,
		 designationIds:desigIds,
		 pType:desigType
		}           
	$.ajax({              
		type:'POST',    
		url: 'getMandalsBySearchTypeAndConstituency',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#"+selBoxId).empty();
		if(result !=null && result.length >0){
			//$("#"+selBoxId).html("<option value='0'>All</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}

function getDesignationsBySearchType(searchType,selBoxId){
	$("#referralNameId").html('<option value="0">Select Referral Name</option>');
	$("#referralNameId").trigger('chosen:updated');
 var json = {
		 searchType :searchType,
		 fromDate :startDate,
		 toDate : endDate
		}            
	$.ajax({              
		type:'POST',    
		url: 'getDesignationsBySearchType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#"+selBoxId).empty();
		if(result !=null && result.length >0){
			//$("#"+selBoxId).html("<option value='0'>Select Designation</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}

function getDepartmentsBySearchType(searchType,selBoxId){
 var json = {
		 searchType :searchType,
		 fromDate :startDate,
		 toDate : endDate
		}           
	$.ajax({              
		type:'POST',    
		url: 'getDepartmentsBySearchType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#"+selBoxId).empty();
		if(result !=null && result.length >0){
			//$("#"+selBoxId).html("<option value='0'>Select Department</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}
 function searchValidations(){
	 var isError=false;
	  $("#locationErrDivlId").html('');
	  $("#districtCandErrDiv").html('');
	  $("#constituencyCanErrDiv").html('');
	  $("#mandalCanErrDiv").html('');
	  $("#designationErrDiv").html('');
	  $("#departMentsErrDiv").html('');
	   $("#nameErrDivId").html('');
	   $("#mobileErrDivId").html('');
	   $("#emailErrDivId").html('');
	   $("#endorsmentNoErrDivId").html('');
	 var locationType = $("#locationSelId").val();
	 var districtId=$("#districtCandId").val();
	  var constituencyId=$("#constituencyCanId").val();
	   var mandalId=$("#mandalCanId").val();
	    var emialRegExp =/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		var phonePattern= /^\d{10}$/;
	 /*if(locationType == null || locationType =='all'){
		 $("#locationErrDivlId").html('<h5>Please select location </h5>');
		 isError=true;
	 }
	  if(districtId == null || districtId ==0){
		 $("#districtCandErrDiv").html('<h5>Please select district </h5>');
		 isError=true;
	 }
	
  if(constituencyId == null || constituencyId ==0){
		 $("#constituencyCanErrDiv").html('<h5>Please select Constituency </h5>');
		 isError=true;
	 }
	 if(mandalId == null || mandalId ==0){
		 $("#mandalCanErrDiv").html('<h5>Please select mandal </h5>');
		 isError=true;
	 }
	 */
	 if($("#designationDiv").is(':visible')){
		  var designationsId=$("#designationsId").val();
		  if(designationsId == null || designationsId ==0){
			 $("#designationErrDiv").html('<h5>Please select designation </h5>');
			 isError=true;
			}
	}
	/* if($("#referralNameDiv").is(':visible')){
		  var referralNameId=$("#referralNameId").val();
		  if(referralNameId == null || referralNameId ==0){
			 $("#referralNameErrDiv").html('<h5>Please select designation </h5>');
			 isError=true;
			}
	} */
	if($("#departMentsDiv").is(':visible')){
		  var deptId=$("#departmentId").val();
		  if(deptId == null || deptId ==0){
			 $("#departMentsErrDiv").html('<h5>Please select department </h5>');
			 isError=true;
			}
	}
	if($("#nameDivid").is(':visible')){
		  var nameVal=$("#nameId").val();
		  if(nameVal == null || nameVal.trim().length ==0 ){
			 $("#nameErrDivId").html('<h5>Please enter name </h5>');
			 isError=true;
			}
	}
	if($("#mobileDivid").is(':visible')){
		  var mobileVal=$("#mobileId").val();
		  if(mobileVal == null || mobileVal.trim().length ==0 ){
			 $("#mobileErrDivId").html('<h5>Please enter mobile number </h5>');
			 isError=true;
			}else if(mobileVal.length != 10 || !phonePattern.test(mobileVal)){
				 $("#mobileErrDivId").html('<h5>Please enter valid mobile number </h5>');
				 isError=true;
			}
	}
	if($("#emailDivid").is(':visible')){
		  var emailVal=$("#emailId").val();
		  if(emailVal == null || emailVal.trim().length ==0 ){
			 $("#emailErrDivId").html('<h5>Please enter email </h5>');
			 isError=true;
			}else if(!emialRegExp.test(emailVal)){
				 $("#emailErrDivId").html('<h5>Please enter valid email </h5>');
				 isError=true;
			}
	}
	if($("#endorsmentNoDivid").is(':visible')){
		  var endorsmentNo=$("#endorsmentNoId").val();
		  if(endorsmentNo == null || endorsmentNo ==0){
			 $("#endorsmentNoErrDivId").html('<h5>Please select endorsment number </h5>');
			 isError=true;
			}
	}
	 if(isError){
		 return true;
	 }
 }
 $(document).on("click","#advanceSearchId",function(){
  var isErr= searchValidations();
  if(isErr)
  return;

   getRepresentativeSearchDetails1();
 });

$(document).on("click",".viewBtnCls",function(){
	var petionId = $(this).attr("attr_petiotion_id");
	var endorsNo = $(this).attr("attr_enrorsNo");
	$("#representeeViewId").html("");
	$("#representeeDetailsModelDivId").modal("show");
   getPetitionDetails(petionId,endorsNo);
 });
function getRepresentativeSearchDetails1(){
	$("#errMsgId").html("");
 
   var filterType=$("#locationSelId").val();
    var filterValue="";
   if(filterType == 'referrelDesignation' || filterType == 'representeeDesignation'){
	  var desig=$("#designationsId").val();//
	   for(var i in desig){
			filterValue = filterValue+desig[i]+",";
		}
	    if($("#designationDiv").is(':visible')){
		  if(filterValue == null || filterValue ==0){
			 $("#errMsgId").html('<h5>Please select designation </h5>');
			 return;
			}
	}
	if(filterType == 'referrelDesignation'){
	 var refrName=$("#referralNameId").val();	
	 if(refrName != null && refrName != 0){
		 filterValue='';
		 filterType = "referralName";
		 for(var i in refrName){
			filterValue = filterValue+refrName[i]+",";
		}
	 }
	}
   }else if(filterType == 'department'){
	    var depts =$("#departmentId").val();
		for(var i in depts){
			filterValue = filterValue+depts[i]+",";
		}
		if($("#departMentsDiv").is(':visible')){
		  if(filterValue == null || filterValue ==0){
			 $("#errMsgId").html('<h5>Please select department </h5>');
			 return;
			}
	}
   }else if(filterType == 'name'){
	    filterValue=$("#nameId").val();
   }else if(filterType == 'mobile'){
	    filterValue=$("#mobileId").val();
   }else if(filterType == 'email'){
	    filterValue=$("#emailId").val();
   }else if(filterType == 'endorsmentNO'){
	    filterValue=$("#endorsmentNoId").val();
   }
   
	var districtId=$("#districtCandId").val();
	 var constituencyId=$("#constituencyCanId").val();
	 var mandalId=$("#mandalCanId").val();
	 var searchLevelValue = [];
	 var searchLevelId;
	 if(districtId != null && districtId.length > 0){
		 searchLevelValue = [];
		  searchLevelValue=districtId;
	  searchLevelId=3;
	 }
	 
if(constituencyId != null && constituencyId.length > 0){
	searchLevelValue = [];
	searchLevelId=4;
	searchLevelValue=constituencyId
}
if(mandalId != null && mandalId.length > 0){
	searchLevelValue = [];
	searchLevelId=5;
	searchLevelValue=mandalId
}
var stausIds ;
if($("#statusId").val() != 0){
	 stausIds = $("#statusId").val();
}
 $("#representationRequestEntryTable").html(spinner);
var json = {
    filterType :filterType,//mobileno/department/name/email
    filterValue:filterValue,
    searchLevelId:searchLevelId,
    searchLvlVals:searchLevelValue,
    fromDate:startDate,
    toDate:endDate,
   // fromRange:0,
   // toRange:0,
   // minVal:0,
    //maxVal:4,
    //startValue:0,
    //endValue:0,
	statusIds:stausIds
    }
  
  $.ajax({                
    type:'POST',    
    url: 'getRepresentativeSearchWiseDetails',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
	  $("#summaryId").html('');
    if(result != null && result.length>0){
		buildSummeryDetails(result);
      representationRequestEntryTable(result);
    }else{
      $("#representationRequestEntryTable").html("NO DATA AVAILABLE");
    }
  }); 
}
function buildSummeryDetails(result){

	var str=""; 
	str+='<div class="panel panel-default" style="margin:15px;">';
		str+='<div class="panel-heading" style="background-color:#344650; color:#fff;">';
			str+='<h4 class="panel-title"> STATUS SUMMERY DETAILS </h4>';
		str+='</div>';
		str+='<div class="panel-body">';
			str+='<div class="col-sm-12">';
				str+='<div class="table-responsive">';
					str+='<table class="table" style="border: 1px solid lightgreen; text-align:center;">';
						str+='<thead>';
							str+='<tr>';
							for(var i in result[0].statusList){
								str+='<th style="background-color:#B3B3B3; border:1px solid #DDDDDD; font-size:12px;">'+result[0].statusList[i].name.toUpperCase()+'</th>';
							}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							str+='<tr>';
							for(var i in result[0].statusList){
								if(result[0].statusList[i].petitionIds.length >0){
									str+='<td style="border:1px solid #DDDDDD;"><span title="Total Representations">'+result[0].statusList[i].petitionIds.length+'</span>/<span title="Total Works">'+result[0].statusList[i].subWorkIds.length+'</span></td>';
								}else{
									str+='<td data-toggle="tooltip" title="representations"  style="border:1px solid #DDDDDD;">-</td>';
								}
							}
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';	
			str+='</div>';	
	str+='</div>';
	$("#summaryId").html(str);
}
getStatusList();
function getStatusList(){
	
  $.ajax({                
    type:'POST',    
    url: 'getStatusList',
    dataType: 'json',
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
    $("#statusId").empty();
		if(result !=null && result.length >0){
			//$("#statusId").html("<option value='0'>All</option>");
			for(var i in result){
				$("#statusId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
			}
		}
		$("#statusId").trigger('chosen:updated');
  }); 
}
//getPetitionDetails(1778,'');
function getPetitionDetails(petitionId,endorsNo){
	$("#representeeViewId").html(spinner);
   var json = {
       petitionId:petitionId,//38,//100031
	   pageType:"viewPage"
    };
  $.ajax({              
    type:'POST',    
    url: 'setPmRepresenteeDataToResultView',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
    console.log(result);
	if(result != null){
		setPmRepresenteeDataToResultView(result,endorsNo);
	}else{
		$("#representeeViewId").html("NO DATA AVAILABLE");
	}
  });
}
var referralDocs = [];
var workDocs = [];
var coveringDocs=[];
var petitionId ='';
function setPmRepresenteeDataToResultView(result,endorsNo){
	var str="";
	petitionId =''
	referralDocs = [];
	workDocs = [];
	//str+='';
	//$("#representeeViewId").html(spinner);
	if(result != null && (result.referDetailsList.length >0 || result.representeeDetailsList.length >0)){
		
		petitionId =result.petitionId;
		var representeeList = [];
		if(result.representationType =="SELF" && result.referDetailsList.length >0){
			representeeList = result.referDetailsList;
		}else if(result.representeeDetailsList.length >0){
			representeeList = result.representeeDetailsList;
		}
	str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="col-sm-6">';
					str+='<h4>REPRESENTEE DETAILS  <span style="margin-left:20px;">';
					/*if(endorsNo != null && endorsNo.length>0)
						str+='(<b>Endorsement No: </b> '+endorsNo+')</span></h4>';
					*/
					
					if(result.representationdate != null && result.representationdate.length>0)
						str+='<b>REPRESENTATION DATE : </b> '+result.representationdate+'</span></h4>';
					
					str+='<div class="block_padding_10 m_top10">';
						
						str+='<div class="media">';
							str+='<div class="media-left" style="text-align:center">';
							if(representeeList[0].candidatePath != null && representeeList[0].candidatePath.length>0)
								str+='<img style="width: 60px ! important; height: 60px ! important; margin-top: 6px;" src="'+representeeList[0].candidatePath+'" class="imageCss">';
							else
								str+='<i class="fa fa-user-circle fa-5x" aria-hidden="true" style="#EBEBEB"></i>';
							
							/*
								str+='<div class="bg_light-Color" style="padding:10px;margin-top:5px;">';
									str+='<p alt="Representation Date">Repr.&nbsp;Date</p>';
									str+='<p><b>'+result.representationdate+'</b></p>';
								str+='</div>';
							*/
							
							str+='</div>';
							str+='<div class="media-body">';
								str+='<div class="bg_light-Color" style="padding:10px">';
									//str+='<p><b>Name</b></p>';
									str+='<h4><b>'+representeeList[0].name+'</b></h4>';
									if(representeeList[0].tdpCadreId != null){
										if(representeeList[0].designation != null && representeeList[0].designation !='')
											str+='<span><b>('+representeeList[0].designation+'),</b>';
										else
											str+='<span><b style="color:orange;">TDP CADRE</b></span>';
									}
										
									str+='<div class="row" style="margin-top:10px;">';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b> NATIVE ADDRESS : </b></h5>';
											if(result.representationType =="REPRESENTEE" && result.referDetailsList.length >0){
												str+='<p>Mandal: '+(representeeList[0].addressVO.tehsilName != ""?representeeList[0].addressVO.tehsilName:" - ")+'</p>';
												str+='<p>Constituency : '+(representeeList[0].addressVO.assemblyName != ""?representeeList[0].addressVO.assemblyName:" - ")+'</p>';
												str+='<p>District : '+(representeeList[0].addressVO.districtName != ""?representeeList[0].addressVO.districtName:" - ")+'</p>';
											}else if(result.representationType =="SELF" && result.referDetailsList.length >0){
												str+='<p>Mandal: '+(representeeList[0].candidateNativeAddressVO.tehsilName != ""?representeeList[0].candidateNativeAddressVO.tehsilName:" - ")+'</p>';
												str+='<p>Constituency : '+(representeeList[0].candidateNativeAddressVO.assemblyName != ""?representeeList[0].candidateNativeAddressVO.assemblyName:" - ")+'</p>';
												str+='<p>District : '+(representeeList[0].candidateNativeAddressVO.districtName != ""?representeeList[0].candidateNativeAddressVO.districtName:" - ")+'</p>';
											}
											str+='</div>';
											if(representeeList[0].email != "" || representeeList[0].mobileNO != "" || representeeList[0].voterCardNo != "" || representeeList[0].voterCardNo != undefined ){
												str+='<div class="col-sm-12 col-md-6"  style="margin-top:10px;">';
													str+='<h5><b>CONTACT DETAILS :</b></h5>';
														if(representeeList[0].email != "")
															str+='<p>Email id : '+(representeeList[0].email != ""?representeeList[0].email:" - ")+'</p>';
														if(representeeList[0].mobileNO != "")
															str+='<p>Contact No : '+(representeeList[0].mobileNO != ""?representeeList[0].mobileNO:" - ")+'</p>';
														if(representeeList[0].voterCardNo != "" && representeeList[0].voterCardNo != undefined)
															str+='<p>Voter Card No : '+(representeeList[0].voterCardNo != "" && representeeList[0].voterCardNo != undefined?representeeList[0].voterCardNo:" - ")+'</p>';
												str+='</div>';
											}
											
									str+='</div>';
									
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					coveringDocs = result.coveringLetterPathsList;
					//ara
					if(coveringDocs != null && coveringDocs.length>0)
						;//str+='<div style=""><p class="viewDivId pull-right docsViewCls" attr_docs="covering" style="cursor:pointer;margin-right: 30px;margin-top: 10px"><i class="fa fa-file-text" aria-hidden="true"></i> VIEW COVERING LETTER </p></div>';
					
				str+='</div>';
				str+='<div class="col-sm-6 ">';
					str+='<h4> REFERRED MEMBERS DETAILS </h4>';
					str+='<div class="block_padding_10 m_top10">';
						for(var i in result.referDetailsList){
						str+='<div class="media">';
							//str+='<div class="media-left" style="text-align:center">';
								//str+='<img class="media-object thumbnail" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;" src="http://mytdp.com/'+result.referDetailsList.candidatePath+'"></img>';								
							//str+='</div>';
							str+='<div class="media-left" >';
								if(result.referDetailsList[i].candidatePath != null && result.referDetailsList[i].candidatePath.length>0){
										str+='<img style="width: 60px ! important; height: 60px ! important; margin-top: 6px;" src="'+result.referDetailsList[i].candidatePath+'" class="imageCss"></img>';
								}else{
										str+='<i class="fa fa-user-circle fa-5x" aria-hidden="true" style="#EBEBEB"></i>';
										str+='<span style="position: relative; top: -62px; left: 39px;">';
										if(result.referDetailsList[i].partyName != null && result.referDetailsList[i].partyName =='TDP')
											str+='<img src="Assests/images/TDP.PNG" style="width: 20px;" class="smallerImg"></img></span>';
								}
							str+='</div>';
							str+='<div class="media-body">';
								str+='<div class="bg_light-Color" style="padding:10px">';
									//str+='<p><b>Name</b></p>';
									str+='<h4><b>'+result.referDetailsList[i].name+'</b></h4>';
									str+='<span><b>('+result.referDetailsList[i].designation+'),</b>';
									str+='<h5><b> PUBLIC REPRESENTATIVE ADDRESS : </b></h5>';
									if(result.referDetailsList[i].candidateAddressVO.assemblyName != null && result.referDetailsList[i].candidateAddressVO.assemblyName.length>0)
										str+='<h5> Constityency : '+result.referDetailsList[i].candidateAddressVO.assemblyName+' ,</h5> ';
									if(result.referDetailsList[i].candidateAddressVO.districtName != null && result.referDetailsList[i].candidateAddressVO.districtName.length>0)
										str+='<h5> District : '+result.referDetailsList[i].candidateAddressVO.districtName+'.</h5></span>';
									if(result.referDetailsList[i].candidateAddressVO.stateName != null && result.referDetailsList[i].candidateAddressVO.stateName.length>0)
										str+='<h5> State : '+result.referDetailsList[i].candidateAddressVO.stateName+'.</h5></span>';
									
									str+='<div class="row">';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b>Party:</b>';
											str+=''+result.referDetailsList[i].partyName+' </h5>';
										str+='</div>';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b>CONTACT DETAILS :</b></h5>';
											if(result.referDetailsList[i].email != "")
												str+='<p>Email id : '+result.referDetailsList[i].email+'</p>';
											if(result.referDetailsList[i].mobileNO != "")
												str+='<p>Contact No : '+result.referDetailsList[i].mobileNO+'</p>';
										str+='</div>';
										if(result.referDetailsList[i].fileNamesList.length >0){
										referralDocs.push(result.referDetailsList[i]);
											str+='<div style=""><p class="viewDivId pull-right docsViewCls" attr_docs="referral" attr_candidate_id="'+result.referDetailsList[i].id+'" style="cursor:pointer;margin-right: 30px;margin-top: 10px"><i class="fa fa-file-text" aria-hidden="true"></i> VIEW REFERRAL LETTER </p></div>';
										}
									str+='</div>';
									
								str+='</div>';
							str+='</div>';
						str+='</div>';
						}
					str+='</div>';
					
				str+='</div>';
				/* str+='<div class="col-sm-12 m_top20">';
					str+='<h5><b>CANDIDATE PREVIOUS WORKS</b></h5>';
					str+='<table class="table table-bordered">';
						str+='<thead>';
							str+='<th>NAME OF WORK</th>';
							str+='<th>LOCATION</th>';
							str+='<th>WORKS</th>';
							str+='<th>EST COST</th>';
						str+='</thead>';
						str+='<tbody>';
							str+='<tr>';
								str+='<td>Special repairs to CPWS Scheme to Raiwada & other habitations</td>';
								str+='<td>Srikakulam</td>';
								str+='<td>12</td>';
								str+='<td>210.00</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
				str+='<div class="clearfix"></div>'; */
				//for(var j in result.worksList){
				str+='<div class="col-sm-10 m_top20">';
					str+='<h5><b>WORK TYPE DETAILS</b></h5>';

					str+='<table class="table table-bordered" cellpadding="100" style="border:1px solid grey;margin-top:10px;">';
						str+='<tbody>';
							str+='<tr>';
								str+='<td style="background-color:lightgrey;text-align: center;font-weight: bold;padding: 17px" > NAME OF THE WORK </td>';
								str+='<td style="background-color:#D1AB66;text-align: center;font-weight: bold;padding: 17px;">NO OF WORKS </td>';
								str+='<td style="background-color:#D1AB66;text-align: center;font-weight: bold;padding: 17px">WORK IN COST </td>';
							str+='</tr>';
							str+='<tr>';
								str+='<td>'+result.workName+'</td>';
								str+='<td style="background-color:#D1AB66;text-align: center;font-weight: bold;padding: 17px">'+result.noOfWorks+'</td>';
								str+='<td style="background-color:#D1AB66;text-align: center;font-weight: bold;padding: 17px">'+result.estimateCost+'</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';

				str+='</div>';
				if(result.fileList.length >0){
				str+='<div class="col-sm-2 m_top20">';
					str+='<h5><b>OTHER DOCUMENTS</b></h5>';
					workDocs = result.fileList;
						str+='<div style=""><p class="viewDivId pull-right docsViewCls" attr_docs="workDocs" style="cursor:pointer;margin-right: 30px;margin-top: 10px;"><i class="fa fa-file-text" aria-hidden="true"></i> VIEW DOCUMENT</p></div>';
				str+='</div>';
				}
				str+='<div class="clearfix"></div>';
				str+='<div class="col-sm-12 m_top20" style="border-bottom:5px solid #EBEBEB;border-top:5px solid #EBEBEB;">';
					str+='<table class="table">';
						str+='<td style="font-weight: bold;"> NO OF WORKS - '+result.noOfWorks+'</td>';
						/* str+='<td style="padding:15px"><i class="fa fa-check-circle-o" aria-hidden="true" style="padding-right:10px;color:#01A64E;font-size:15px"></i>Endorse</td>';
						str+='<td style="padding:15px"><i class="fa fa-times-circle-o" aria-hidden="true" style="padding-right:10px;color:#EC2027;font-size:15px"></i>Rejected</td>';
						str+='<td><button class="btn">Select All</button></td>';
						str+='<td><button class="btn btn-success">Final Approval</button></td>';
						str+='<td><button class="btn btn-danger">Not Possible</button></td>';
						str+='<td><button class="btn btn-danger">Reject</button></td>'; */
					str+='</table>';
				str+='</div>';
				
				if(result.statusList != null && result.statusList.length>0){
					str+='<div class="col-sm-12 m_top20" style="border-bottom:5px solid #EBEBEB;border-top:5px solid #EBEBEB;">';
					str+='<table class="table table-condensed table-bordered  m_top20" style="margin-bottom:20px;">';
						str+='<thead>';
						for(var s in result.statusList){
							str+='<th  style="text-align:center">'+result.statusList[s].value.toUpperCase()+'</th>';
						}						
						str+='</thead>';
						str+='<tbody>';
						for(var s in result.statusList){
							if(result.statusList[s].count == 0)
								str+='<td  style="text-align:center"> - </td>';
							else
								str+='<td  style="text-align:center">'+result.statusList[s].count+'</td>';
						}						
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
				
				}
				var isAllEndorsed=true;
				for(var j in result.subWorksList){	
					for(var k in result.subWorksList[j].subWorksList){
						workIdsArr.push(result.subWorksList[j].subWorksList[k].workId);
						var leadName = result.subWorksList[j].subWorksList[k].leadName;
						if(leadName == null || leadName.length ==0){
							isAllEndorsed=false;
						}
					}
				}
				if(result.statusList != null && result.statusList.length>0){
					var accessStatusList = result.statusList[0].subList;
					str+='<div class="col-sm-12 m_top20 pull-right" style="border-bottom:5px solid #EBEBEB;border-top:5px solid #EBEBEB;">';
					
					if(accessStatusList != null && accessStatusList.length>0){
							str+='<div class="row ">';
							str+='<div class="col-sm-6  m_top10">';
							str+='</div>';
							str+='<div class="col-sm-2 m_top10 ">';
							
								str+='<button class="btn btn-info selectedCls" isSeleted="false" attr_worksId="0" attr_dept_id="0" style="margin-bottom:10px;" > SELECT ALL  </button>';
							str+='</div>';
							for(var s in accessStatusList){
								str+='<div class="col-sm-2 m_top10 ">';
								if(accessStatusList[s].key == 1){
									if(!isAllEndorsed)
										str+='<button class="statusCls endorseCls btn btn-success" attr_statusId="'+accessStatusList[s].key+'"  style="margin-bottom:10px;" attr_type="endosePopup" attr_petition_id="'+result.petitionId+'"> ENDORSE </button>';
								}
								if(accessStatusList[s].key == 5){
										str+='<button class="statusCls btn btn-danger" attr_statusId="'+accessStatusList[s].key+'"  style="margin-bottom:10px;" attr_type="notPossiblePopup" attr_petition_id="'+result.petitionId+'"> NOT POSSIBLE </button>';
								}
								str+='</div>';
							}
							str+='</div>';
					}
					str+='</div>';
				}
				
				str+='<div class="col-sm-12 m_top20">';
				var workCount = 0;
				str+='<div class="row">';
				for(var j in result.subWorksList){
					
				for(var k in result.subWorksList[j].subWorksList){
					var leadName = result.subWorksList[j].subWorksList[k].leadName;
					
					workCount = workCount+1;
					
						str+='<div class="col-sm-6 m_top10">';
							str+='<h5><b>WORK NO '+workCount+'</b></h5>';
							str+='<div class="bg_light-Color block_padding_10 m_top10">';
								str+='<table class="table table-bordered">';
									str+='<tr>';
										str+='<td><b> WORK TYPE </b></br><b>'+result.subWorksList[j].subWorksList[k].workType+'</b> </td>';
										//str+='(status:'+result.subWorksList[j].subWorksList[k].status+')';
										str+='<td colspan="2">';
											str+='<p><b>LOCATION</b></p>';
											str+='<span style="display:inline-block;padding:10px"><b>District</b></br>'+result.subWorksList[j].subWorksList[k].addressVO.districtName+'</span>';
											if(result.subWorksList[j].subWorksList[k].addressVO.assemblyName != "")
											str+='<span style="display:inline-block;padding:10px" class="text-capitalized"><b>Constituency</b></br>'+result.subWorksList[j].subWorksList[k].addressVO.assemblyName+'</span>';
											if(result.subWorksList[j].subWorksList[k].addressVO.tehsilName.trim() != "")
											str+='<span style="display:inline-block;padding:10px">Mandal</br><b>'+result.subWorksList[j].subWorksList[k].addressVO.tehsilName+'</b></span>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td><b>SUBJECT: </b>'+result.subWorksList[j].subWorksList[k].subject+'</td>';
										str+='<td><b>SUB-SUBJECT:  </b>'+result.subWorksList[j].subWorksList[k].subSubject+'</td>';
										str+='<td><b>DEPARTMENT : </b>'+result.subWorksList[j].subWorksList[k].deptName+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
						
						str+='<div class="col-sm-6 m_top10">';
							str+='<h5><b>WORK DESCRIPTION</b> ';
							/*if(result.subWorksList[j].subWorksList[k].statusId == 1)
								str+=' <span class="pull-right" style="color:orange" > <b style="color:#000"> STATUS: </b><b> '+result.subWorksList[j].subWorksList[k].status.toUpperCase()+'  </b> </span> ';
							else if(result.subWorksList[j].subWorksList[k].statusId == 8 )
								str+=' <span class="pull-right" style="color:green;" > <b style="color:#000"> STATUS: </b><b> '+result.subWorksList[j].subWorksList[k].status.toUpperCase()+'  </b> </span> ';
							else
								str+='<span class="pull-right" > <b style="color:#000"> STATUS:</b><b>'+result.subWorksList[j].subWorksList[k].status.toUpperCase()+'  </b> </span> ';*/
							
							//if(leadName == null || leadName.length ==0)
								str+=' <span class=""  style="margin-bottom:10px;margin-left: 320px"> <button class="btn btn-info selectedCls" attr_work_id="'+result.subWorksList[j].subWorksList[k].workId+'"  isSeleted="false" attr_dept_id="'+result.subWorksList[j].subWorksList[k].deptName+'" attr_worksId="'+result.subWorksList[j].subWorksList[k].workId+'"  > SELECT </button> </span> ';
							
							str+='</h5>';
							str+='<div class=" block_padding_3 m_top10">';
								str+='<p style="font-size:12px;border: 1px solid lightgray; width: 570px;height: 150px;padding: 10px;">'+result.subWorksList[j].subWorksList[k].workName+'</p>';
							str+='</div>';
						str+='</div>';
						
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="bg_light-Color block_padding_3 m_top10">';
						str+='<table class="table">';
							str+='<tbody>';
								str+='<td><b>Lead Details :</b> <span>'+result.subWorksList[j].subWorksList[k].leadName+'</span></td>';
								str+='<td><b>Brief Lead:</b><span>'+result.subWorksList[j].subWorksList[k].briefLeadName+'</span></td>';
								str+='<td><b>Grant Under :</b><span>'+result.subWorksList[j].subWorksList[k].grantName+'</span></td>';
								str+='<td><b>Est Budget :</b><span>'+result.subWorksList[j].subWorksList[k].estimateCost+'</span></td>';
								str+='<td><b>eOffice ID :</b><span>'+result.subWorksList[j].subWorksList[k].eOfficeId+'</span></td>';
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				
				}
				
			}
			str+='</div>';
		str+='</div>';
		/* str+='<div class="col-sm-12 m_top20" style="border-bottom:5px solid #EBEBEB"></div>';
				str+='<div class="col-sm-12 m_top20">';
					str+='<button class="btn btn-success pull-right">SUBMIT</button>';
				str+='</div>';
			str+='</div>'; */
	
	$("#representeeViewId").html(str);
}else{
	$("#representeeViewId").html("No Data Available");
}
}
	function setDefaultImage(img){
			  img.src = "http://www.mytdp.com/images/User.png";
	}
$(document).on("click",".docsViewCls",function(){
	$("#docsModalDivId").modal("show");
	var docsList = [];
	var str="";
	if($(this).attr("attr_docs") == "referral"){
		$("#viewDocumentHeading").html("Referral Documents")
		 for(var i = 0; i<referralDocs.length; i++){
			if(referralDocs[i].id == $(this).attr("attr_candidate_id")){
				docsList  = referralDocs[i].fileNamesList;
			 }
		 }
	}else if($(this).attr("attr_docs") == "workDocs"){
		$("#viewDocumentHeading").html("Work Documents")
		docsList = workDocs;
	}else if($(this).attr("attr_docs") == "covering"){
		$("#viewDocumentHeading").html("Covering Letter")
		docsList = coveringDocs;
	}
	
	if(docsList != null && docsList.length >0){
			for(var j in docsList){
				var scanCopySpl = docsList[j].value.split("."); 
				var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
					str+='<div class="col-sm-6">';
						
						str+='<div class="viewImageCss">';
						if(scanCopyExt =="pdf"){
							str+='<a class="fancyboxView" href="#inline'+j+'">';
							str+='<div class="mouse-over">Expand</div>';
								str+='<object data="'+docsList[j].value+'" type="application/pdf" width="100%"height="300px;"></object>';
							str+='</a>';
							str+='<div id="inline'+j+'" style="width:100%;display: none;">';
								str+='<object data="'+docsList[j].value+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
							str+='</div>';
							
						}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
							str+='<a class="fancyboxView" href="#inline'+j+'">';
								str+='<img src="'+docsList[j].value+'"  width="100%" height="300px;"></img>';
							str+='</a>';
							str+='<div id="inline'+j+'" style="width:100%;display: none;">';
								str+='<img src="'+docsList[j].value+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
							str+='</div>';
						}else{
							str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+docsList[j].value+'\')">Here</a> To View Document</b>';
						}
			
				str+='</div>';
			str+='</div>';
	
		}
	}

		$("#docsViewModalId").html(str);
		$(".fancyboxView").fancybox();
});

function openDoc(docmnt){
	 window.open(docmnt);
}

function getPetitionReferredMemberDetails(desigIds){
     var json = {
		 designationIds:desigIds,
		 reprType :"referralView"
		}           
	$.ajax({              
		type:'POST',    
		url: 'getPetitionReferredMemberDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#referralNameId").empty();
		if(result !=null && result.length >0){
			//$("#"+selBoxId).html("<option value='0'>Select Designation</option>");
			for(var i in result){
				$("#referralNameId").append("<option value='"+result[i].referrerCandidateId+"'>"+result[i].petitionMemberVO.name+"-"+result[i].petitionMemberVO.memberType+"-"+result[i].candidateAddressVO.assemblyName+"</option>");
			}
		}
		$("#referralNameId").trigger('chosen:updated');
	});	
}
function updatePetitionStatusDetails(){
	 
	   var petitionIdsList = [1,2,3,4];
	   var subWorkIdsList = [1,2,3,4];
	   var json = {
		   statusId : 1,
	       remarks:"",
		   petitionIdsList:petitionIdsList,
		   subWorkIdsList:subWorkIdsList
	    };
	  $.ajax({              
	    type:'POST',    
	    url: 'updatePetitionStatusDetails',
	    dataType: 'json',
	    data : JSON.stringify(json),
	    beforeSend :   function(xhr){
	      xhr.setRequestHeader("Accept", "application/json");
	      xhr.setRequestHeader("Content-Type", "application/json");
	    }
	  }).done(function(result){
	    console.log(result);
		if(result != null){
			
		}else{
			
		}
	  });
	}
$(document).on("click",".endorseCls",function(){
	$("#endorseMentModalDivId").modal("show");
	 initializeSingleUploadDocument("uploadEndorsementDocId");
	  getPmBriefLeadList();
	  getPmGrantList();
	  getLoginUserAccessSubDeptDesignationDetail(selectedDeptIdsArr);
	
 });
 
 $(document).on("click",".closeSecondModal",function(){
    setTimeout(function(){
      $("body").addClass("modal-open")
    },1000);
  });
 function endorsingSubWorksAndAssigningToOfficer(){
	   var formData = new FormData();
	   $('#endorsingSubWorksId input').each(
		  function(){			  
			var input = $(this);
			var text =input.attr('type');
			var id = input.attr('id');
			//debugger;
			if (typeof id !== typeof undefined && id !== false) {
				if(text=='text' || text=='hidden'){
					var name = $('#'+id+'').attr('name');
					formData.append(name, $('#'+id+'').val());
				}else if(text=='radio'){
					if($('#'+id+'').is(':checked')){
						var name = $('#'+id+'').attr('name');
						formData.append(name, $('#'+id+'').val());
					}
				}else if(text=='file'){
					if(this.files !=null && this.files.length>0){
						for(var i = 0; i < this.files.length; i++){
								formData.append("filesList["+i+"]", this.files[i]);
						}
					}
				}
			}			
		}
	);
	
	$('#endorsingSubWorksId textarea').each(
		  function(){			  
			var input = $(this);
				var id = input.attr('id');
				if (typeof id !== typeof undefined && id !== false) {
				var name = $('#'+id+'').attr('name');
				formData.append(name, $('#'+id+'').val());
			}
		}
	);
	
	$('#endorsingSubWorksId select').each(
		  function(){			  
				var input = $(this);
				var id = input.attr('id');
				if (typeof id !== typeof undefined && id !== false) {
					var name = $('#'+id+'').attr('name');
					formData.append(name, $('#'+id+'').val());
			}
		}
	);
	if(selectedWorkIdsArr !=null && selectedWorkIdsArr.length>0){
							for(var i = 0; i < selectedWorkIdsArr.length; i++){
								formData.append("statusType", "COVERING LETTER");
								formData.append("workIds["+i+"]", selectedWorkIdsArr[i]);
						}
	}
	formData.append("petitionId", petitionId);
	
$.ajax({
			url: $("#endorsingSubWorksId").attr("action"),
			data: formData,
			type: "POST",               
			processData: false,
			contentType: false,
			success: function(result) {
				$("#savingDetailsSpinner").html('')
				/* if(result!=null){
				  if(result.responseCode == "0"){
					   $("#statusMsgAppntReqt").html("<center><h3 style='color: green;margin-top:-25px;'>Application Saved Successfully</h3></center>").fadeOut(4000);
					  
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); 
						window.location.reload(); 
						$(".defaultCheckCls").prop("checked",true)},6000);
						 
				  }else{
					  $('#saveButtonId').show();
					  $("#statusMsgAppntReqt").html("<center><h3 style='color: red;margin-top:-25px;'>Application Failed..Try Later</h3></center>").fadeOut(4000);
					  setTimeout(function () {
						 
						}, 500);
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				  }
				}else{
					  $('#saveButtonId').show();
					setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h3 style='color: red;margin-top:-25px;'>Application Failed..Try Later</h3></center>").fadeOut(4000);
						}, 500);
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				 } */
				 
				
			},
			error: function(request,error) { 
				$("#savingDetailsSpinner").html('');
				//console.log(request);
				//console.log(error);
				alert("Error occured while updating details.Pelase check once any required data missing to fill.Then try again.");	
				$('#saveButtonId').show();				
			}
     });
 }	
 
 function getPmBriefLeadList(){
	 $("#leadId").html('');
	 $("#leadId").html('<option value="0">Select Brief Lead</option>');
   var json = {
      
    };
  $.ajax({              
    type:'POST',    
    url: 'getPmBriefLeadList',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
	  if(result !=null && result.length>0){
			 $("#leadId").html('<option value="0">Select Brief Lead</option>');
			for(var i in result){
				$("#leadId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#leadId").trigger('chosen:updated');
	});	 
}

function getPmGrantList(){
	 $("#grantId").html('');
	 $("#grantId").html('<option value="0">Select Grant Under</option>');
   var json = {
      
    };
  $.ajax({              
    type:'POST',    
    url: 'getPmGrantList',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
	  if(result !=null && result.length>0){
			 $("#grantId").html('<option value="0">Select Grant Under</option>');
			for(var i in result){
				$("#grantId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#grantId").trigger('chosen:updated');
	});	 
}

function getLoginUserAccessSubDeptDesignationDetail(selectedDeptIdsArr){
	 $("#assignToId").html('');
	 $("#assignToId").html('<option value="0">Select Assign To</option>');
	
 var json = {
	 
	 deptIdsList : selectedDeptIdsArr
	}           
$.ajax({              
	type:'POST',    
	url: 'getLoginUserAccessSubDeptDesignationDetail',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
}).done(function(result){
	if(result !=null && result.length>0){
		$("#assignToId").html('<option value ="0">Select Assign To</option>');
		for(var i in result){
				$("#assignToId").append('<option value ="'+result[i].key+'">'+result[i].value+'</option>');
		}
	}
	$("#assignToId").trigger('chosen:updated');
});	
}

$(document).on('change','.popUpChangesCls',function(){
	var onChangeValue = $(this).val();
	getDeptDesignationOfficerDetail(onChangeValue)
})

function getDeptDesignationOfficerDetail(onChangeValue){
	$("#officerId").html('');
	$("#officerId").html('<option value ="0">Select Officer Name</option>');
	var deptDesignationId = onChangeValue;
 var json = {
	 
	deptDesignationId : deptDesignationId
	}           
$.ajax({              
	type:'POST',    
	url: 'getDeptDesignationOfficerDetail',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
}).done(function(result){
	if(result != null && result.length >0){
		$("#officerId").html('<option value ="0">Select Officer Name</option>');
		for(var i in result){
			$("#officerId").append('<option value ="'+result[i].id+'">'+result[i].value+'</option>');
		}
	}
	$("#officerId").trigger('chosen:updated');
});	
}
