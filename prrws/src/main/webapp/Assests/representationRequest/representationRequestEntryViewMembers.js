var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//getRepresentativeSearchWiseDetails(typeVal);
function getRepresentativeSearchWiseDetails(typeVal){
	$("#errorId").html("");
	var searchValue = typeVal;
	var radioValue = $("input[name='optradio']:checked"). val();
	if(typeVal == ""){
		if(radioValue =="name"){
		$("#errorId").html("<h5>Please Enter Name</h5>");
			return;
		}else if(radioValue =="mobileNo"){
		$("#errorId").html("<h5>Please Enter Mobileno</h5>");
			return;
		}else if(radioValue =="refCode"){
		$("#errorId").html("<h5>Please Enter Refcode</h5>");
			return;
		}else if(radioValue =="emailId"){
		$("#errorId").html("<h5>Please Enter EmailId</h5>");
			return;
		}
	}
	$("#representationRequestEntryTable").html(spinner);
    var json = {
    filterType :radioValue,
	filterValue:searchValue
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
$(document).on("click","#advancedSearchId",function(){
	var typeVal = $("#advancedSearchVal").val();
	getRepresentativeSearchWiseDetails(typeVal);
});

$(document).on("click","#advanceSearchId",function(){
	var typeVal =""
	var locationScopeId=4;
	var lcoationId=0;
	
	$('.searchCls').each(function(){
		if($(this).is(':checked')) {
			typeVal = $(this).val();
		}		
	});
	var districtId = $('#districtCandId').val();
	var constituencyId = $('#districtCandId').val();
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
	if(typeVal=="designation"){
		typeVal = $('#designationsId').val();
	}else if(typeVal=="departmentId"){
		typeVal = $('#designationsId').val();
	}
	getRepresentativeSearchDetails(typeVal,locationScopeId,lcoationId);
});


function getRepresentativeSearchDetails(typeVal,locationScopeId,lcoationId){
	$("#errorId").html("");
	var searchValue = typeVal;
	var radioValue = $("input[name='optradio']:checked"). val();
	$("#representationRequestEntryTable").html(spinner);
    var json = {
		filterType :radioValue,
		filterValue:searchValue,
		searchLevelId:locationScopeId,
		searchLevelValue:lcoationId
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

$(document).on("click",".searchCls",function(){
	var value=$(this).val();
	$('#advancedSearchVal').val('');
	$('#advancedSearchDivId').hide();
	$('#locationsDivId').show();
	$('#districtCandId').val(0);
	$('#constituencyCanId').val(0);
	if(value =="designation"){
		$('#designationDiv').show();
		$('#departMentsDiv').hide();
		getPetitionDepartmentList();
	}else if(value =="department"){
		getPetitionDesignationList();
		$('#designationDiv').hide();
		$('#departMentsDiv').show();
	}else if(value =="refLocation" || value =="workLocation"){
		$('#designationDiv').hide();
		$('#departMentsDiv').hide();
	}
});
$(document).on("click","#advanceBtnId",function(){
	$('#advancedSearchDivId').show();
	$('#locationsDivId').hide();
	$('#designationDiv').hide();
	$('#departMentsDiv').hide();
});
function representationRequestEntryTable(result){
	var str='';
	str+='<table class="table table-bordered">';
		str+='<thead>';
			str+='<tr>';
				str+='<th>ENDT ID</th>';
				str+='<th>REPRESENTEE NAME</th>';
				str+='<th>REPRESENTEE DESIGNATION</th>';
				str+='<th>REPRESENTEE MOBILE NO</th>';
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
				str+='<td><button type="button" class="btn">View/Edit</button></td>';
			str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	$("#representationRequestEntryTable").html(str);
}


function getPetitionDepartmentList(){
    $("#departmentId").html('');
	  var json = {
		
		}           
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
			  $("#departmentId").append('<option value="0">All Departments</option>');
			 
			for(var i in result){
				 $("#departmentId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		 $("#departmentId").trigger('chosen:updated');
	});	
}

function getPetitionDesignationList(){
    $("#designationsId").html('');
	  var json = {};
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
			 $("#designationsId").append('<option value="0">All Designations </option>');
			for(var i in result){
				$("#designationsId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#designationsId").trigger('chosen:updated');
	});	
}


function getAllDistrictsInState(){
	$("#districtCandId").html('');
	var json = {
		  stateId:"1"
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
			$("#districtCandId").append('<option value="0">All</option>');
			for(var i in result){				
				$("#districtCandId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
			}
		}
			$("#districtCandId").trigger('chosen:updated');
	});	
}

function getConstituencyNamesByDistrictId(levelVa){
	  $("#constituencyCanId").html('');
	  var json = {
		  districtId:levelVa
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
			 $("#constituencyCanId").append('<option value="0">All</option>');
			for(var i in result){
				$("#constituencyCanId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
			}
		}
		$("#constituencyCanId").trigger('chosen:updated');
	});	
}

function getTehsilsAndLocalElectionBodyForConstituencyId(levelVal){
		$("#mandalrepresent").html('');
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
			 $("#mandalrepresent").append('<option value="0">Select Mandal</option>');
			for(var i in result){
				$("#mandalrepresent").append('<option value="'+levelId+'">'+result[i].value+'</option>');
			}
		}
		$("#mandalrepresent").trigger('chosen:updated');
	});	
}
  
getAllDistrictsInState();
getPetitionDepartmentList()
getPetitionDesignationList();