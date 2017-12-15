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
				str+='<td class="text-center"><a class="btn btn-xs viewEditCss"> View</a><a href="/representationRequestEntry" target="_blank" class="btn btn-xs viewEditCss m_top10"> Edit </a></td>';
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
		$("#"+selBoxId).empty();
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
		$("#"+selBoxId).empty();
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
		$("#"+selBoxId).empty();
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
 $(document).on("click","#advanceSearchId",function(){
  //var isErr= searchValidations();
  //if(isErr)
 // return;
$("#representeeDetailsModelDivId").modal("show");
   getRepresentativeSearchDetails1();
 });

function getRepresentativeSearchDetails1(){
	$("#designationErrDiv").html("");
	$("#departMentsErrDiv").html("");
  $("#representationRequestEntryTable").html(spinner);
   var filterType=$("#locationSelId").val();
    var filterValue="";
   if(filterType == 'referrelDesignation' || filterType == 'representeeDesignation'){
	   filterValue=$("#designationsId").val();//
	    if($("#designationDiv").is(':visible')){
		  if(filterValue == null || filterValue ==0){
			 $("#designationErrDiv").html('<h5>Please select designation </h5>');
			 isError=true;
			}
	}
   }else if(filterType == 'department'){
	    filterValue=$("#departmentId").val();
		if($("#departMentsDiv").is(':visible')){
		  if(filterValue == null || filterValue ==0){
			 $("#departMentsErrDiv").html('<h5>Please select department </h5>');
			 isError=true;
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
var stausIds ;
if($("#statusId").val() != 0){
	 stausIds = $("#statusId").val();
}
var json = {
    filterType :filterType,//mobileno/department/name/email
    filterValue:filterValue,
    searchLevelId:searchLevelId,
    searchLevelValue:searchLevelValue,
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
    if(result != null && result.length>0){
      representationRequestEntryTable(result);
    }else{
      $("#representationRequestEntryTable").html("NO DATA AVAILABLE");
    }
  }); 
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
			$("#statusId").html("<option value='0'>All</option>");
			for(var i in result){
				$("#statusId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
			}
		}
		$("#statusId").trigger('chosen:updated');
  }); 
}
//getPetitionDetails(1);
function getPetitionDetails(petitionId){
   var json = {
       petitionId:petitionId
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
	setPmRepresenteeDataToResultView(result)
  });
}

function setPmRepresenteeDataToResultView(result){
	var str="";
	//str+='';
	if(result != null){
		$("#representeeDetailsModelDivId").modal("show");
		$("#representeeViewId").html(spinner)
	str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="col-sm-6">';
					str+='<h4>REPRESENTEE DETAILS</h4>';
					str+='<div class="block_padding_10">';
						
						str+='<div class="media">';
							str+='<div class="media-left" style="text-align:center">';
								str+='<i class="fa fa-user-circle fa-5x" aria-hidden="true" style="#EBEBEB"></i>';
								str+='<div class="bg_light-Color" style="padding:10px;margin-top:5px;">';
									str+='<p>Representation Date</p>';
									str+='<p><b>'+result.representationdate+'</b></p>';
								str+='</div>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<div class="bg_light-Color" style="padding:10px">';
									str+='<p><b>Name</b></p>';
									str+='<h4><b>'+result.name+'</b></h4>';
									str+='<span><b>TDP Cadre</b></span>';
									str+='<div class="row">';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b>Address Details:</b></h5>';
											//str+='<p>Village : Sangadigunta</p>';
											str+='<p>Mandal: '+result.addressVO.tehsilName+'</p>';
											str+='<p>Constituency : '+result.addressVO.assemblyName+'</p>';
											str+='<p>District : '+result.addressVO.districtName+'</p>';
										str+='</div>';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b>Contact Details:</b></h5>';
											str+='<p>Email id : '+result.email+'</p>';
											str+='<p>Contact No : '+result.mobileNO+'</p>';
											str+='<p>Voter Id : '+result.voterCardNo+'</p>';
										str+='</div>';
									str+='</div>';
									
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<h4>REFERRED BY</h4>';
					str+='<div class="block_padding_10">';
						for(var i in result.referList){
						str+='<div class="media">';
							str+='<div class="media-left" style="text-align:center">';
								str+='<i class="fa fa-user-circle fa-5x" aria-hidden="true" style="#EBEBEB"></i>';
								
							str+='</div>';
							str+='<div class="media-body">';
								str+='<div class="bg_light-Color" style="padding:10px">';
									str+='<p><b>Name</b></p>';
									str+='<h4><b>'+result.referList[i].name+'</b></h4>';
									str+='<span><b>('+result.referList[i].designation+'), '+result.referList[i].addressVO.assemblyName+' Constituency, '+result.referList[i].addressVO.districtName+' District.</b></span>';
									str+='<div class="row">';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b>Party:</b></h5>';
											str+='<p>'+result.referList[i].partyName+' '+result.referList[i].addressVO.districtName+'</p>';
										str+='</div>';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b>Contact Details:</b></h5>';
											str+='<p>Email id : '+result.referList[i].email+'</p>';
											str+='<p>Contact No : '+result.referList[i].mobileNO+'</p>';
										str+='</div>';
										str+='<div style=""><p class="viewDivId pull-right"><i class="fa fa-file-text" aria-hidden="true"></i> VIEW REFERRAL LETTER</p></div>';
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
				for(var j in result.worksList){
				str+='<div class="col-sm-10 m_top20">';
					str+='<h5><b>WORK TYPE DETAILS</b></h5>';
					str+='<table class="table table-bordered" cellpadding="100" style="border:1px solid grey">';
						str+='<tbody>';
							str+='<tr>';
								str+='<td>Name of the Work</td>';
								str+='<td style="background-color:#D1AB66">No of Works</td>';
								str+='<td style="background-color:#D1AB66">Work Cost (Est. Cost in Lakh)</td>';
							str+='</tr>';
							str+='<tr>';
								str+='<td>'+result.worksList[j].workName+'</td>';
								str+='<td style="background-color:#D1AB66">'+result.worksList[j].noOfWorks+'</td>';
								str+='<td style="background-color:#D1AB66">'+result.worksList[j].estimateCost+'</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
				str+='<div class="col-sm-2 m_top20">';
					str+='<h5><b>PROJECT DOCUMENTS</b></h5>';
					str+='<div style=""><p class="viewDivId pull-right"><i class="fa fa-file-text" aria-hidden="true"></i> VIEW DOCUMENT</p></div>';
				str+='</div>';
				
				str+='<div class="clearfix"></div>';
				str+='<div class="col-sm-12 m_top20" style="border-bottom:5px solid #EBEBEB;border-top:5px solid #EBEBEB;">';
					str+='<table class="table">';
						str+='<td>NO OF WORKS - '+result.worksList[j].noOfWorks+'</td>';
						/* str+='<td style="padding:15px"><i class="fa fa-check-circle-o" aria-hidden="true" style="padding-right:10px;color:#01A64E;font-size:15px"></i>Endorse</td>';
						str+='<td style="padding:15px"><i class="fa fa-times-circle-o" aria-hidden="true" style="padding-right:10px;color:#EC2027;font-size:15px"></i>Rejected</td>';
						str+='<td><button class="btn">Select All</button></td>';
						str+='<td><button class="btn btn-success">Final Approval</button></td>';
						str+='<td><button class="btn btn-danger">Not Possible</button></td>';
						str+='<td><button class="btn btn-danger">Reject</button></td>'; */
					str+='</table>';
				str+='</div>';
				str+='<div class="col-sm-12 m_top20">';
				for(var k in result.worksList[j].subWorksList){
					var workCount = k+1;
					str+='<div class="row">';
						str+='<div class="col-sm-6">';
							str+='<h5><b>WORK No '+workCount+'</b></h5>';
							str+='<div class="bg_light-Color block_padding_10 m_top10">';
								str+='<table class="table table-bordered">';
									str+='<tr>';
										str+='<td>Work Type</br><b>'+result.worksList[j].subWorksList[k].workType+'</b>(status:'+result.worksList[j].subWorksList[k].status+')</td>';
										str+='<td colspan="2">';
											str+='<p>LOCATION</p>';
											str+='<span style="display:inline-block;padding:10px">District</br><b>'+result.worksList[j].subWorksList[k].addressVO.districtName+'</b></span>';
											str+='<span style="display:inline-block;padding:10px">Constituency</br><b>'+result.worksList[j].subWorksList[k].addressVO.assemblyName+'</b></span>';
											str+='<span style="display:inline-block;padding:10px">mandal</br><b>'+result.worksList[j].subWorksList[k].addressVO.tehsilName+'</b></span>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>Subject <b>'+result.worksList[j].subWorksList[k].subject+')</b></td>';
										str+='<td>Sub-Subject <b>'+result.worksList[j].subWorksList[k].subSubject+')</b></td>';
										str+='<td>Department <b>'+result.worksList[j].subWorksList[k].deptName+'</b></td>';
										
									str+='</tr>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
						
						str+='<div class="col-sm-6">';
							str+='<h5><b>WORK DISCRIPTION</b> ';
							//str+='<button class="btn pull-right">Select</button>
							str+='</h5>';
							str+='<div class=" block_padding_10 m_top10">';
								str+='<p style="font-size:12px">'+result.worksList[j].subWorksList[k].workName+'</p>';
							str+='</div>';
						str+='</div>';
						
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="bg_light-Color block_padding_10 m_top10">';
						str+='<table class="table">';
							str+='<tbody>';
								str+='<td><b>Lead Details :</b> <span>'+result.worksList[j].subWorksList[k].leadName+'</span></td>';
								str+='<td><b>Brief Lead:</b><span>'+result.worksList[j].subWorksList[k].briefLeadName+'</span></td>';
								str+='<td><b>Grant Under :</b><span>'+result.worksList[j].subWorksList[k].grantName+'</span></td>';
								str+='<td><b>Est Budget :</b><span>'+result.worksList[j].subWorksList[k].estimateCost+'</span></td>';
								str+='<td><b>eOffice ID :</b><span>'+result.worksList[j].subWorksList[k].eOfficeId+'</span></td>';
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
	}
				str+='<div class="col-sm-12 m_top20" style="border-bottom:5px solid #EBEBEB"></div>';
				str+='<div class="col-sm-12 m_top20">';
					str+='<button class="btn btn-success pull-right">SUBMIT</button>';
				str+='</div>';
			str+='</div>';
	}
	$("#representeeViewId").html(str);
}
}