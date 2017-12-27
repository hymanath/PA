var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

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

/* $(document).on('cut copy paste', function (e) {
	e.preventDefault();
}); */

$(document).on("click",".advancedSrchCls",function(){
	$("#advancedSearchVal").val('');
});

$(document).on("change",".clearDataCls",function(){
	$("#representationRequestEntryTable").html('');
});
function clearData(){
	$("#representationRequestEntryTable").html('');
}

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
				str+='<th>Estimation Cost</th>';
				str+='<th>Action</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
			var endorsmentNo='';
			str+='<tr>';
				if (result[i].endorsementNO != null && typeof(result[i].endorsementNO) != "undefined"){
					str+='<td>'+result[i].endorsementNO+'</td>';
					endorsmentNo=result[i].endorsementNO;
				}else
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
				if (result[i].estimationCost != "" && typeof(result[i].estimationCost) != "undefined")
					str+='<td>'+result[i].estimationCost+'</td>';
				else
					str+='<td>-</td>';
				
				str+='<td class="text-center"><i class="fa fa-eye viewBtnCls tooltipCls" aria-hidden="true" attr_enrorsNo="'+endorsmentNo+'" attr_petiotion_id="'+result[i].petitionId+'" style="margin-right: 20px; font-size: 16px;cursor:pointer" data-toggle="tooltip" data-placement="top" title="View Petition"> </i>';
				
				//if(endorsmentNo != null && endorsmentNo != 'undefined' &&  (parseInt(endorsmentNo) ==0 || endorsmentNo=='') )
					str+='<a href="'+wurl+'/representationRequestEdit?petitionId='+result[i].petitionId+'" target="_blank"><i class="tooltipCls fa fa-pencil-square-o" aria-hidden="true" style="font-size: 16px;cursor:pointer" data-toggle="tooltip" data-placement="top" title="Edit Petition"></i></a>';
				
				str+='</td>';
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
	$("#errMsgId").html("");
	$('.clearCls').val('');
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
	     $('#parametersList').show();
	    $("#nameId").html('');
	    $("#mobileId").html('');
	    $("#emailId").html('');
	  $("#endorsmentNoId").html('');
   
	var searchType=$(this).val();
	getDistrictBySearchType(searchType,'districtCandId');
	if(searchType == 'all'){
		$('#parametersList').hide();
		return;
	}
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
			//$("#"+selBoxId).html("<option value='0'>All</option>");
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
		 filterType :searchType,
		 searchLvlVals: distictId
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
 var json = {
		 filterType :searchType,
		 searchLvlVals: consituencyId
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
		  searchLevelValue=districtId;
	  searchLevelId=3;
	 }
	 
if(constituencyId != null && constituencyId.length > 0){
	searchLevelId=4;
	searchLevelValue=constituencyId
}
if(mandalId != null && mandalId.length > 0){
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
       petitionId:petitionId,
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
function setPmRepresenteeDataToResultView(result,endorsNo){
	var str="";
	referralDocs = [];
	workDocs = [];
	//str+='';
	//$("#representeeViewId").html(spinner);
	if(result != null && (result.referDetailsList.length >0 || result.representeeDetailsList.length >0)){
		
		
		var representeeList = [];
		if(result.representationType =="SELF" && result.referDetailsList.length >0){
			representeeList = result.referDetailsList;
		}else if(result.representeeDetailsList.length >0){
			representeeList = result.representeeDetailsList;
		}
	str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="col-sm-6">';
					str+='<h4>REPRESENTEE DETAILS  <span style="margin-left:20px;">';
					if(endorsNo != null && endorsNo.length>0)
						str+='(<b>Endorsement No: </b> '+endorsNo+')</span></h4>';
					str+='<div class="block_padding_10 m_top10">';
						
						str+='<div class="media">';
							str+='<div class="media-left" style="text-align:center">';
							if(representeeList[0].candidatePath != null && representeeList[0].candidatePath.length>0)
								str+='<img style="width: 60px ! important; height: 60px ! important; margin-top: 6px;" src="'+representeeList[0].candidatePath+'" class="imageCss">';
							else
								str+='<i class="fa fa-user-circle fa-5x" aria-hidden="true" style="#EBEBEB"></i>';
							
								str+='<div class="bg_light-Color" style="padding:10px;margin-top:5px;">';
									str+='<p alt="Representation Date">Repr.&nbsp;Date</p>';
									str+='<p><b>'+result.representationdate+'</b></p>';
								str+='</div>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<div class="bg_light-Color" style="padding:10px">';
									//str+='<p><b>Name</b></p>';
									str+='<h4><b>'+representeeList[0].name+'</b></h4>';
									if(representeeList[0].tdpCadreId != null)
									str+='<span><b style="color:orange;">TDP CADRE</b></span>';
									str+='<div class="row" style="margin-top:10px;">';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b>ADDRESS DETAILS:</b></h5>';
											if(result.representationType =="REPRESENTEE" && result.referDetailsList.length >0){
												str+='<p>Mandal: '+(representeeList[0].addressVO.tehsilName != ""?representeeList[0].addressVO.tehsilName:" - ")+'</p>';
												str+='<p>Constituency : '+(representeeList[0].addressVO.assemblyName != ""?representeeList[0].addressVO.assemblyName:" - ")+'</p>';
												str+='<p>District : '+(representeeList[0].addressVO.districtName != ""?representeeList[0].addressVO.districtName:" - ")+'</p>';
											}else if(result.representationType =="SELF" && result.referDetailsList.length >0){
												str+='<p>Mandal: '+(representeeList[0].candidateAddressVO.tehsilName != ""?representeeList[0].candidateAddressVO.tehsilName:" - ")+'</p>';
												str+='<p>Constituency : '+(representeeList[0].candidateAddressVO.assemblyName != ""?representeeList[0].candidateAddressVO.assemblyName:" - ")+'</p>';
												str+='<p>District : '+(representeeList[0].candidateAddressVO.districtName != ""?representeeList[0].candidateAddressVO.districtName:" - ")+'</p>';
											}
											str+='</div>';
										str+='<div class="col-sm-12 col-md-6"  style="margin-top:10px;">';
											str+='<h5><b>CONTACT DETAILS :</b></h5>';
												str+='<p>Email id : '+(representeeList[0].email != ""?representeeList[0].email:" - ")+'</p>';
												str+='<p>Contact No : '+(representeeList[0].mobileNO != ""?representeeList[0].mobileNO:" - ")+'</p>';
											str+='<p>Voter Card No : '+(representeeList[0].voterCardNo != "" && representeeList[0].voterCardNo != undefined?representeeList[0].voterCardNo:" - ")+'</p>';
										str+='</div>';
									str+='</div>';
									
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					coveringDocs = result.coveringLetterPathsList;
					//ara
					str+='<div style=""><p class="viewDivId pull-right docsViewCls" attr_docs="covering" style="cursor:pointer;margin-right: 30px;margin-top: 10px"><i class="fa fa-file-text" aria-hidden="true"></i> VIEW COVERING LETTER </p></div>';
					
				str+='</div>';
				str+='<div class="col-sm-6 ">';
					str+='<h4>REFERRED DETAILS </h4>';
					str+='<div class="block_padding_10 m_top10">';
						for(var i in result.referDetailsList){
						str+='<div class="media">';
							//str+='<div class="media-left" style="text-align:center">';
								//str+='<img class="media-object thumbnail" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;" src="http://mytdp.com/'+result.referDetailsList.candidatePath+'"></img>';								
							//str+='</div>';
							str+='<div class="media-left" >';
												str+='<img style="width: 60px ! important; height: 60px ! important; margin-top: 6px;" src="'+result.referDetailsList[i].candidatePath+'" class="imageCss"></img>';
												str+='<span style="position: relative; top: -62px; left: 39px;"><img src="Assests/images/TDP.PNG" style="width: 20px;" class="smallerImg"></img></span>';
										str+='</div>';
							str+='<div class="media-body">';
								str+='<div class="bg_light-Color" style="padding:10px">';
									//str+='<p><b>Name</b></p>';
									str+='<h4><b>'+result.referDetailsList[i].name+'</b></h4>';
									str+='<span><b>('+result.referDetailsList[i].designation+'),</b>';
									if(result.referDetailsList[i].candidateAddressVO.assemblyName != null && result.referDetailsList[i].candidateAddressVO.assemblyName.length>0)
										str+='<h5> Constityency : '+result.referDetailsList[i].candidateAddressVO.assemblyName+' ,</h5> ';
									if(result.referDetailsList[i].candidateAddressVO.districtName != null && result.referDetailsList[i].candidateAddressVO.districtName.length>0)
										str+='<h5> District : '+result.referDetailsList[i].candidateAddressVO.districtName+'.</h5></span>';
									if(result.referDetailsList[i].candidateAddressVO.stateName != null && result.referDetailsList[i].candidateAddressVO.stateName.length>0)
										str+='<h5> State : '+result.referDetailsList[i].candidateAddressVO.stateName+'.</h5></span>';
									
									str+='<div class="row">';
										str+='<div class="col-sm-12 col-md-6">';
											str+='<h5><b>Party:</b></h5>';
											str+='<p>'+result.referDetailsList[i].partyName+' </p>';
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
					str+='<h5><b>PROJECT DOCUMENTS</b></h5>';
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
				str+='<div class="col-sm-12 m_top20">';
				var workCount = 0;
				str+='<div class="row">';
				for(var j in result.subWorksList){
					
				for(var k in result.subWorksList[j].subWorksList){
					workCount = workCount+1;
					
						str+='<div class="col-sm-6 m_top10">';
							str+='<h5><b>WORK NO '+workCount+'</b></h5>';
							str+='<div class="bg_light-Color block_padding_10 m_top10">';
								str+='<table class="table table-bordered">';
									str+='<tr>';
										str+='<td><b>WORK TYPE </b></br><b>'+result.subWorksList[j].subWorksList[k].workType+'</b>(status:'+result.subWorksList[j].subWorksList[k].status+')</td>';
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
							//str+='<button class="btn pull-right">Select</button>
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