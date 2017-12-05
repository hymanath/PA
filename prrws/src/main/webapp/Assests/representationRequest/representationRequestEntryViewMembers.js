var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//getRepresentativeSearchWiseDetails(typeVal);
$(".chosen-select").chosen();
//
//getPetitionDepartmentList()
getPetitionDesignationList();

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
		}else if(searchType=="departmentId"){
			searchVal = $('#departmentId').val();
		}
		getRepresentativeSearchDetails(searchVal,locationScopeId,lcoationId);
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
	str+='<table class="table table_customRep table-bordered" id="workDetailsTab">';
		str+='<thead>';
			str+='<tr>';
				str+='<th>ENDORSEMENT ID</th>';
				str+='<th>REPRESENTEE NAME</th>';
				str+='<th>REPRESENTEE DESIGNATION</th>';
				str+='<th>REPRESENTEE MOBILE&nbsp;NO</th>';
				str+='<th>WORK NAME</th>';
				str+='<th>NO OF WORKS</th>';
				str+='<th>SUBJECT</th>';
				str+='<th>ACTION</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].refCode+'</td>';
				str+='<td>'+result[i].candidateName+'</td>';
				str+='<td>-</td>';
				str+='<td>'+result[i].mobileNo+'</td>';
				str+='<td>'+result[i].workName+'</td>';
				str+='<td>'+result[i].noOfWorks+'</td>';
				str+='<td>'+result[i].subject+'</td>';
				str+='<td><button type="button" class="btn viewEditCss">View/Edit</button></td>';
			str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	$("#representationRequestEntryTable").html(str);
	
	$("#workDetailsTab").dataTable({
		"paging":   true,
		"info":     false,
		"searching": true,
		"autoWidth": true,
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 20,
		"aaSorting": [],
		"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
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