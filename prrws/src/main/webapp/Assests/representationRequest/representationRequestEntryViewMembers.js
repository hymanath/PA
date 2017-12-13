var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//getRepresentativeSearchWiseDetails(typeVal);
$(".chosen-select").chosen();
//
//getPetitionDepartmentList()
getPetitionDesignationList();
$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});
$(document).on("click",".searchCls",function(){
	var value=$(this).val();
	if(value =="designation"){
		$("#normalSearchDivId").show();
		$('#designationDiv').show();
		$('#departMentsDiv').hide();
		getPetitionDesignationList();
		//getAllDistrictsInState('all');		
		$("#advancedSearchDivId").hide();
	}else if(value =="department"){
		$("#normalSearchDivId").show();
		getPetitionDepartmentList();
		//getAllDistrictsInState('all');
		$('#designationDiv').hide();
		$('#departMentsDiv').show();
		$("#advancedSearchDivId").hide();
	}else if(value =="refLocation" || value =="workLocation"){
		$("#normalSearchDivId").show();
		$('#designationDiv').hide();
		$('#departMentsDiv').hide();
		$("#advancedSearchDivId").hide();
		getAllDistrictsInState(value,0);
	}else if(value =="advanceBtnId"){
		$('#advancedSearchDivId').show();
		$("#normalSearchDivId").hide();
	}
});
$(document).on("click",".advancedSrchCls",function(){
	$("#advancedSearchVal").val('');
});

$(document).on("click","#advanceSearchId",function(){
	var searchType='';
	$(".searchCls").each(function(){
		if($(this).is(":checked")){
			searchType = $(this).val();
		}
	});
	
	if(searchType == "advanceBtnId"){
		var searchVal = $("#advancedSearchVal").val();
		getRepresentativeSearchWiseDetails(searchVal);
	}else{
		var searchVal =""
		var locationScopeId=4;
		var lcoationId=0;
		
		var districtId = $('#districtCandId').val();
		var constituencyId = $('#constituencyCanId').val();
		if(parseInt(districtId)==0){
			locationScopeId = 2;
			lcoationId=1;
		}else if(parseInt(constituencyId)==0){
			locationScopeId = 3;
			lcoationId=districtId;
		}else{
			locationScopeId = 4;
			lcoationId=constituencyId;
		}
		if(searchType=="designation"){
			searchVal = $('#designationsId').val();
		}else if(searchType=="department"){
			searchVal = $('#departmentId').val();
		}
		//getRepresentativeSearchDetails(searchVal,locationScopeId,lcoationId);
	}
});

$(document).on("change","#designationsId",function(){
	var designatinId = $(this).val();	
	getAllDistrictsInState('designation',designatinId);
});
$(document).on("change","#departmentId",function(){
	var departId = $(this).val();	
	getAllDistrictsInState('dept',departId);
});

//Advanced Search Call
function getRepresentativeSearchWiseDetails(searchVal){
	$("#errorId").html("");
	var radioValue = $("input[name='optradio1']:checked"). val();
	if(searchVal == ""){
		if(radioValue =="name"){
		$("#errorId").html("<h5>Please Enter Name</h5>");
			return;
		}else if(radioValue =="mobileNo"){
		$("#errorId").html("<h5>Please Enter Mobile No</h5>");
			return;
		}else if(radioValue =="refCode"){
		$("#errorId").html("<h5>Please Enter Ref Code</h5>");
			return;
		}else if(radioValue =="emailId"){
		$("#errorId").html("<h5>Please Enter EmailId</h5>");
			return;
		}
	}
	$("#representationRequestEntryTable").html(spinner);
    var json = {
    filterType :radioValue,
	filterValue:searchVal,
	searchLevelId:2,
	searchLevelValue:1,
	fromDate:"01-01-2010",
	toDate:"01-01-2018"
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

//Normal Search Call
function getRepresentativeSearchDetails(searchVal,locationScopeId,lcoationId){
	$("#errorId").html("");
	var radioValue = $("input[name='optradio']:checked"). val();
	$("#representationRequestEntryTable").html(spinner);
	
	$(".searchCls").each(function(){
		if($(this).is(":checked")){
			searchType = $(this).val();
		}
	});
	if(searchType == "advanceBtnId"){
		var searchVal = $("#advancedSearchVal").val(); 
	}
	
	
    var json = {
		filterType :radioValue,
		filterValue:searchVal,
		searchLevelId:locationScopeId,
		searchLevelValue:lcoationId,
		fromDate:"01-01-2010",
		toDate:"01-01-2055"
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
function representationRequestEntryTable(result){
	var str='';
	str+='<div class="table-responsive">';
	str+='<table class="table table_customRep table-bordered" id="workDetailsTab">';
		str+='<thead>';
			str+='<tr>';
				str+='<th>ENDORSEMENT&nbsp;ID</th>';
				str+='<th>MEMBER&nbsp;TYPE</th>';
				str+='<th>DISTRICT</th>';
				str+='<th>CONSTITUENCY</th>';
				str+='<th>RAISED&nbsp;DATE</th>';
				str+='<th>REF. DESIGNATION</th>';				
				str+='<th style="min-width:180px !important;">REPR. NAME</th>';
				str+='<th>REPR. DESIGNATION</th>';
				str+='<th>REPR. MOBILE&nbsp;NO</th>';
				str+='<th style="min-width:200px !important;">WORK NAME</th>';
				str+='<th>NO OF WORKS</th>';
				str+='<th>DEPT</th>';
				str+='<th>SUBJECT</th>';
				
				str+='<th>SUB SUBJECT</th>';
				str+='<th style="min-width:100px !important;">LEAD</th>';
				str+='<th style="min-width:100px !important;">BRIEF LEAD</th>';
				str+='<th>GRANT</th>';
				str+='<th>STATUS</th>';
				str+='<th style="min-width:100px !important;">REMARKS</th>';
				
				str+='<th>ACTION</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
			str+='<tr>';
				if (result[i].refCode != null && typeof(result[i].refCode) != "undefined")
					str+='<td>'+result[i].refCode+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].representeeType != null && typeof(result[i].representeeType) != "undefined")
					str+='<td>'+result[i].representeeType+'</td>';
				else
					str+='<td> - </td>';
				
				if (result[i].district != null && typeof(result[i].district) != "undefined")
					str+='<td>'+result[i].district+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].constituency != null && typeof(result[i].constituency) != "undefined")
					str+='<td>'+result[i].constituency+'</td>';
				else
					str+='<td> - </td>';
				
				if (result[i].date != null && typeof(result[i].date) != "undefined")
					str+='<td>'+result[i].date+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].designation != null && typeof(result[i].designation) != "undefined")
					str+='<td>'+result[i].designation+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].candidateName != null && typeof(result[i].candidateName) != "undefined")
					str+='<td>'+result[i].candidateName+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].designation != null && typeof(result[i].designation) != "undefined")
					str+='<td>'+result[i].designation+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].mobileNo != null && typeof(result[i].mobileNo) != "undefined")
					str+='<td>'+result[i].mobileNo+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].workName != null && typeof(result[i].workName) != "undefined"){
					if(result[i].workName !=null && result[i].workName.length>25){
						str+='<td><span class="tooltipCls" data-toogle="tooltip" data-placement="right" title="'+result[i].workName+'">'+result[i].workName.substring(0,25)+'...</span></td>';
					}else{
						str+='<td>'+result[i].workName+'</td>';
					}
					
				}else{
					str+='<td> - </td>';
				}
					
				
				if (result[i].noOfWorks != null && typeof(result[i].noOfWorks) != "undefined")
					str+='<td>'+result[i].noOfWorks+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].departrment != null && typeof(result[i].departrment) != "undefined")
					str+='<td>'+result[i].departrment+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].subject != null && typeof(result[i].subject) != "undefined")
					str+='<td>'+result[i].subject+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].subSubject != null && typeof(result[i].subSubject) != "undefined")
					str+='<td>'+result[i].subSubject+'</td>';	
				else
					str+='<td> - </td>';			
				if (result[i].lead != null && typeof(result[i].lead) != "undefined"){
					if(result[i].lead !=null && result[i].lead.length>10){
						str+='<td><span class="tooltipCls" data-toogle="tooltip" data-placement="right" title="'+result[i].lead+'">'+result[i].lead.substring(0,10)+'...</span></td>';
					}else{
						str+='<td>'+result[i].lead+'</td>';
					}
					
				}else{
					str+='<td> - </td>';
				}
				
				if (result[i].briefLead != null && typeof(result[i].briefLead) != "undefined"){
					if(result[i].briefLead !=null && result[i].briefLead.length>10){
						str+='<td><span class="tooltipCls" data-toogle="tooltip" data-placement="right" title="'+result[i].briefLead+'">'+result[i].briefLead.substring(0,10)+'...</span></td>';
					}else{
						str+='<td>'+result[i].briefLead+'</td>';	
					}
					
				}else{
					str+='<td> - </td>';
				}
				
				if (result[i].grant != null && typeof(result[i].grant) != "undefined")
					str+='<td>'+result[i].grant+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].status != null && typeof(result[i].status) != "undefined")
					str+='<td>'+result[i].status+'</td>';
				else
					str+='<td> - </td>';
				if (result[i].remarks != null && typeof(result[i].remarks) != "undefined"){
					if(result[i].remarks !=null && result[i].remarks.length>10){
						str+='<td><span class="tooltipCls" data-toogle="tooltip" data-placement="right" title="'+result[i].remarks+'">'+result[i].remarks.substring(0,10)+'</span></td>';	
					}else{
						str+='<td>'+result[i].remarks+'</td>';	
					}
					
				}else{
					str+='<td> - </td>';
				}
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
function getPetitionDepartmentList(){
    $("#departmentId").html('');
	$("#departmentId").append('<option value="0">All Departments</option>');
	$("#departmentId").trigger('chosen:updated');
	  var json = { 
		  searchType:"petitionGivenDepts" // all/petitionGivenDepts
	  };          
	$.ajax({              
		type:'POST',    
		url: 'getPetitionDepartmentList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			for(var i in result){
				 $("#departmentId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		 $("#departmentId").trigger('chosen:updated');
	});	
}

function getPetitionDesignationList(){
    $("#designationsId").html('');
	$("#designationsId").append('<option value="0">All Designations </option>');
	$("#designationsId").trigger('chosen:updated');
	  var json = { 
		  searchType:"petitionGivenRefCandidateDesignations" // all/refCandidateDesignations/petitionGivenRefCandidateDesignations
	  };
	$.ajax({              
		type:'POST',    
		url: 'getPetitionDesignationList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){			 
			for(var i in result){
				$("#designationsId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#designationsId").trigger('chosen:updated');
	});	
}


function getAllDistrictsInState(type,id){
	$("#districtCandId").html('');
	$("#districtCandId").append('<option value="0">All</option>');
	$("#districtCandId").trigger('chosen:updated');
	var json = {
		  stateId:"1",
		  searchType:type,
		  searchId:id
		}
	$.ajax({                
		type:'POST',    
		url: 'getAllDistrictsInState',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){			
			for(var i in result){				
				$("#districtCandId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
			}
		}
			$("#districtCandId").trigger('chosen:updated');
	});	
}

function getConstituencyNamesByDistrictId(levelVal){
	  $("#constituencyCanId").html('');
	   $("#constituencyCanId").append('<option value="0">All</option>');
	  var searchVal = 0;
		var searchType='';
		$(".searchCls").each(function(){
			if($(this).is(":checked")){
				searchType = $(this).val();
			}
		});
		if(searchType=="designation"){
			searchVal = $('#designationsId').val();
		}else if(searchType=="department"){
			searchVal = $('#departmentId').val();
			searchType="dept";
		}
		
	  var json = {
		  districtId:levelVal,
		  searchType:searchType,
		  searchId:searchVal
		}
	$.ajax({                
		type:'POST',    
		url: 'getConstituencyNamesByDistrictId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			for(var i in result){
				$("#constituencyCanId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
			}
		}
		$("#constituencyCanId").trigger('chosen:updated');
	});	
}

function getTehsilsAndLocalElectionBodyForConstituencyId(levelVal){
		$("#mandalrepresent").html('');
		 $("#mandalrepresent").append('<option value="0">Select Mandal</option>');
		 $("#mandalrepresent").trigger('chosen:updated');
	  var json = {
		  constituencyId:levelVal
		}        
	$.ajax({                
		type:'POST',    
		url: 'getTehsilsAndLocalElectionBodyForConstituencyId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			for(var i in result){
				$("#mandalrepresent").append('<option value="'+levelId+'">'+result[i].value+'</option>');
			}
		}
		$("#mandalrepresent").trigger('chosen:updated');
	});	
}
//getRepresentativeSearchDetails1();
function getRepresentativeSearchDetails1(){
	/* $("#errorId").html("");
	var radioValue = $("input[name='optradio']:checked"). val();
	$("#representationRequestEntryTable").html(spinner);
	
	$(".searchCls").each(function(){
		if($(this).is(":checked")){
			searchType = $(this).val();
		}
	});
	if(searchType == "advanceBtnId"){
		var searchVal = $("#advancedSearchVal").val(); 
	}
	 */
	var json = {
		filterType :"referral",//mobileno/department/name/email
		filterValue:"",
		searchLevelId:3,
		searchLevelValue:11,
		fromDate:"01-01-2010",
		toDate:"01-01-2055",
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