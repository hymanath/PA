var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

var startDate = moment().subtract(7,"year").format("DD-MM-YYYY");
var endDate = moment().add(38,"year").format("DD-MM-YYYY");

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
	   'All'	: [moment().subtract(7, 'year').startOf('year'), moment().add(38, 'year').endOf('year')]
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
});
$(".chosen-select").chosen();

$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});

$(document).on("click",".advancedSrchCls",function(){
	$("#advancedSearchVal").val('');
});

function representationRequestEntryTable(result){
	var str='';
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
				str+='<th>No&nbsp;of&nbsp;Works</th>';
				str+='<th>Action</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
			str+='<tr>';
				if (result[i].endorsementNO != null && typeof(result[i].endorsementNO) != "undefined")
					str+='<td>'+result[i].endorsementNO+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].raisedDate != null && typeof(result[i].raisedDate) != "undefined")
					str+='<td>'+result[i].raisedDate+'</td>';
				else
					str+='<td> - </td>';
				
				if (result[i].endorsmentDate != null && typeof(result[i].endorsmentDate) != "undefined")
					str+='<td>'+result[i].endorsmentDate+'</td>';
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
				if (result[i].workName != null && typeof(result[i].workName) != "undefined")
					str+='<td>'+result[i].workName+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].noOfWorks != null && typeof(result[i].noOfWorks) != "undefined")
					str+='<td>'+result[i].noOfWorks+'</td>';
				else
					str+='<td> - </td>';
				str+='<td><a href="/representationRequestEntry" target="_blank" class="btn btn-xs viewEditCss"> View/Edit </a></td>';
			str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
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

$(document).on("change","#locationSelId",function(){
	$("#departMentsDiv").hide();
	$("#designationDiv").hide()//inputSearchDivid
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
	     
	    $("#nameId").html('');
	    $("#mobileId").html('');
	    $("#emailId").html('');
	  $("#endorsmentNoId").html('');
   
	var searchType=$(this).val();
	getDistrictBySearchType(searchType,'districtCandId');//
	if(searchType == 'referrelDesignation' || searchType =='representeeDesignation'){
		$("#designationDiv").show();
		getDesignationsBySearchType(searchType,"designationsId");
	}
	if(searchType == 'department'){
		$("#departMentsDiv").show();
		getDepartmentsBySearchType(searchType,"departmentId");
	}
	if(searchType == 'name'){
		$("#nameDivid").show();
	}else if(searchType == 'mobile'){
		$("#mobileDivid").show();
	}else if(searchType == 'email'){
		$("#emailDivid").show();
	}else if(searchType == 'endorsmentNO'){
		$("#endorsmentNoDivid").show();
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

function getDistrictBySearchType(searchType,selBoxId){
	$("#"+selBoxId).html("");
 var json = {
		 searchType :searchType
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
			$("#"+selBoxId).html("<option value='0'>Select District</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}
function getConstituenciesBySearchTypeAndDistrict(searchType,distictId,selBoxId){
	$("#"+selBoxId).html("");
 var json = {
		 searchType :searchType,
		 locationId: distictId
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
			$("#"+selBoxId).html("<option value='0'>All</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}
function getMandalsBySearchTypeAndConstituency(searchType,consituencyId,selBoxId){
	$("#"+selBoxId).html("<option value='0'>Select Mandal</option>");
 var json = {
		 searchType :searchType,
		 locationId: consituencyId
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
		if(result !=null && result.length >0){
			$("#"+selBoxId).html("<option value='0'>All</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}

function getDesignationsBySearchType(searchType,selBoxId){
	
 var json = {
		 searchType :searchType
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
		if(result !=null && result.length >0){
			$("#"+selBoxId).html("<option value='0'>Select Designation</option>");
			for(var i in result){
				$("#"+selBoxId).append("<option value='"+result[i].key+"'>"+result[i].value+"</option>");
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}

function getDepartmentsBySearchType(searchType,selBoxId){
 var json = {
		 searchType :searchType
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
		if(result !=null && result.length >0){
			$("#"+selBoxId).html("<option value='0'>Select Department</option>");
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
	 if(locationType == null || locationType =='all'){
		 $("#locationErrDivlId").html('<h5>Please select location </h5>');
		 isError=true;
	 }
	  if(districtId == null || districtId ==0){
		 $("#districtCandErrDiv").html('<h5>Please select district </h5>');
		 isError=true;
	 }
/*	
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
 
getRepresentativeSearchDetails1();
function getRepresentativeSearchDetails1(){
  
   var filterType=$("#locationSelId").val();
    var filterValue="";
   if(filterType == 'referrelDesignation' || filterType == 'representeeDesignation'){
	   filterValue=$("#designationsId").val();//
   }else if(filterType == 'department'){
	    filterValue=$("#departmentId").val();
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
	 var searchLevelValue=districtId;
	 var searchLevelId=3;
if(constituencyId > 0){
	searchLevelId=4;
	searchLevelValue=constituencyId
}
if(mandalId > 0){
	searchLevelId=5;
	searchLevelValue=mandalId
}
  var json = {
    filterType :filterType,//mobileno/department/name/email
    filterValue:filterValue,
    searchLevelId:searchLevelId,
    searchLevelValue:searchLevelValue,
    fromDate:startDate,
    toDate:endDate,
    fromRange:1,
    toRange:9,
    minVal:0,
    //maxVal:4,
    startValue:1,
    endValue:2
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
    if(result != null && result.length>0){
      representationRequestEntryTable(result);
    }else{
      $("#representationRequestEntryTable").html("NO DATA AVAILABLE");
    }
  }); 
}

