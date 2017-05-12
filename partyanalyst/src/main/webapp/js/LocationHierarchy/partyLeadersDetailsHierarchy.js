var districtsArr =[];
var constiArr =[];
var mandalArr =[];
var panchayatArr =[];
var globalstateId=1;
$(document).on("click",".switch-btn li",function(){
    $(this).closest("ul").find("li").removeClass("active");
    $(this).addClass("active");
	var stateId = $(this).attr("attr_type");
	globalstateId=stateId;
	getDistrictsForStates(stateId);
});

function getDistrictsForStates(stateId){
   var jsObj=
   {
		stateId:stateId						
	}
	districtsArr =[];
    $.ajax({
          type:'GET',
          url: 'getDistrictsListForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $('#districtId').val(0);
	   $('#constituencyId').val(0);
	   $('#mandalId').val(0);
	   $('#panchayatDivId').val(0);
	   $('#leaderTypeId').val(1);
	   $('#enrollmentId').val('');
	   $('#designationId').val('');
	   $('#committeeLevelId').val('');
	   $('#committeeTypeId').val('');
	   $('#leadersDetailsDiv').hide();
	   if(result != null){
		   $("#districtId").html('');
			 for(var i in result){
				 districtsArr.push(result[i].id);
			   if(result[i].id == 0){
				  $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }
		$(".chosenClass").trigger("chosen:updated");
	  }
   });
  }
  
$(document).on("change","#districtId",function(){
	var districtId = $("#districtId").val();
		$('#leadersDetailsDiv').hide();
		var jsObj=
        {				
		  districtId:districtId						
	    }
		 constiArr =[];
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   $("#constituencyId").html('');
        for(var i in result){
			panchayatArr.push(result[i].id);
			 if(i == 0)
				 $("#constituencyId").append('<option value="0">ALL</option>');
			else
				$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	   $(".chosenClass").trigger("chosen:updated");
	 }
   });		
});
   
$(document).on("change","#constituencyId",function(){
	var constituencyId = $('#constituencyId').val();
		$('#leadersDetailsDiv').hide();
		var jsObj ={					
			constituencyId:constituencyId
		};
		
		mandalArr =[];
		$.ajax({
			type : "GET",
			url : "getMandalDetailsByConstituencyAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			if(result != null)
			{
			 $("#mandalId").html('');
			for(var i in result)
			{
				panchayatArr.push(result[i].locationId);
				if(i == 0)
				  $("#mandalId").append('<option value="0">ALL</option>');
				else
				 $("#mandalId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			}	
			$(".chosenClass").trigger("chosen:updated");
		}				
	});		
});
		
$(document).on("change","#mandalId",function(){	
    var mandalId = $('#mandalId').val();
	var constituencyId = $('#constituencyId').val();
	$('#leadersDetailsDiv').hide();
	 var jsObj ={					
			mandalId:mandalId,
			constituencyId :constituencyId
			};
			
			panchayatArr =[];
       	 $.ajax({
			type : "GET",
			url : "getPanchayatWardByMandalAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){			
				if(result != null)
				{
					$("#panchayatDivId").html('');
				 for(var i in result)
				  {
					  panchayatArr.push(result[i].locationId);
					  if(i == 0)
						$("#panchayatDivId").append('<option value="0">ALL</option>');
					else
						$("#panchayatDivId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
				  }	
				  $(".chosenClass").trigger("chosen:updated");
				}				
		});
});
	
$(document).on('change','#leaderTypeId',function(){
	var leadrId = $("#leaderTypeId").val();
	 if(leadrId == 0){
		getPublicRepresentsDetails();
		getCommitteeRoles();
        $("#committeeLevelDivId").hide();
		$("#committeeTypeDivId").hide();
	 }else if(leadrId == 2){
		getCommitteeRoles();
		getCommitteeLevelDetails();
		getCommitteeTypeDetails();
		$('#committeeTypeDivId').show();
		$('#committeeLevelDivId').show();
		$("#designationId").html('');
		$("#designationId").append('<option value="0"> All </option>');
	}else{
	    getPublicRepresentsDetails();
		$('#committeeTypeDivId').hide();
		$('#committeeLevelDivId').hide();
		$("#designationId").html('');
		$("#designationId").append('<option value="0"> All </option>');
		}
});
	  
function getCommitteeRoles(){
		 var jsObj={
				task:"roles"
				}
    $.ajax({
    	type:'GET',
    	url: 'getAllCommitteesAction.action',
    	data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		var str ='';
		if(result != null){
			for(var i in result)
			{
			$("#designationId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		   $(".chosenClass").trigger("chosen:updated");
		}
   });		
}
function getPublicRepresentsDetails(){
    	var jsObj={
    	  task:"publicRepresentatives"
    		}
    	$.ajax({
    	  type:'GET',
    	  url: 'getPublicRepresentativeTypes.action',
    	  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		     var str ='';
    	if(result != null){
			for(var i in result)
			{ 
			  $("#designationId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
			$(".chosenClass").trigger("chosen:updated");
		}
   });	
 }
	  
$(document).on("click","#btnId",function(){
	var representativeTypeId =$('#leaderTypeId').val();
	var locationArr=[];
	var designationIdArr=$('#designationId').val();
	var levelId = 2;
	var panchayatIds = $('#panchayatDivId').val();
	var mandalIds = $('#mandalId').val();
	var constituencyIds = $('#constituencyId').val();
	var districtIds = $('#districtId').val();
		
	var committeeLevelIdsArr = $('#committeeLevelId').val();
	var committeeTypeIdsArr = $('#committeeTypeId').val();
	var enrollmentIdsArr = $('#enrollmentId').val();
	var enrollmentId = $('#enrollmentId').val();
		
	if(committeeLevelIdsArr == null )
		committeeLevelIdsArr=[];
	if(committeeTypeIdsArr == null )
		committeeTypeIdsArr=[];	
	
	  errMsg="";		
	 if(representativeTypeId == 2){			
		if(districtIds == null || districtIds==0){
			errMsg=errMsg+"Please select atleast one district. <br/>";
		}			
	   if(committeeLevelIdsArr == null || committeeLevelIdsArr.length==0){
			errMsg=errMsg+"Please select atleast one Committee Level. <br/>";
		}
	   if(committeeTypeIdsArr == null || committeeTypeIdsArr.length==0){
			errMsg=errMsg+"Please select atleast one Committee type. <br/>";
		}
		if(errMsg.length>0){
			$("#errorMessegeId").html(errMsg);
		}else{
			$("#errorMessegeId").html('');
		 }
}
		
		
	if(enrollmentIdsArr == null || enrollmentIdsArr.length==0){
		errMsg=errMsg+"Please select atleast one enrollment year . <br/>";
	}
	if(errMsg.length>0){
		$("#errorMessegeId").html(errMsg);
	}else{
		$("#errorMessegeId").html('');
	}
	
	if(designationIdArr == null || designationIdArr.length==0){
		errMsg=errMsg+"Please select atleast one designation. <br/>";
	}
	if(errMsg.length>0){
		$("#errorMessegeId").html(errMsg);
		return;
	}else{
		$("#errorMessegeId").html('');
	}
	$('#leadersDetailsDiv').show();
	if(panchayatIds != null && parseInt(panchayatIds)>0){
		levelId = 6;
		locationArr.push(panchayatIds);
	}else if(mandalIds != null && parseInt(mandalIds)>0){
		levelId = 5;
		locationArr.push(mandalIds);
	}else if(constituencyIds != null && parseInt(constituencyIds)>0){
		levelId = 4;
		locationArr.push(constituencyIds);
	}else if(districtIds != null && parseInt(districtIds)>0 ){
		levelId = 3;
		locationArr.push(districtIds);
	}else{
		levelId = 2;
		locationArr.push(globalstateId);
	}
	$("#partyLeaderDetailsId").html('<img src="images/Loading-data.gif" style="margin-left: 550px;">');
		 $('#excelBtn').hide();
		 var jsObj =
		  {
			levelId:levelId,
			representativeTypeId :representativeTypeId,
			locationIds:locationArr,
			designationIds:designationIdArr,
			firstIndex:0,
			maxIndex:1000,
			committeeLevelIdsArr:committeeLevelIdsArr,
			enrollmentId:enrollmentId,
			stateId:globalstateId,
			committeeTypeIdsArr:committeeTypeIdsArr,
			enrollmentIdsArr:enrollmentIdsArr,
			enrollmentId:0
		  };
		  
		  $.ajax({  
			type:'GET',            
			url: 'getPartyLeadersDeatailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		  }).done(function(result){
			  
               if(result != null){				
					 var str = '';
					  str +='<table class="table table-bordered" id="leadersDetailsTab">';
							 str +='<thead>';
								 str +='<tr>';
									 str +='<th>District</th>';
									 str +='<th>PC_NAME</th>';
									 str +='<th>AC_NO</th>';
									 str +='<th>AC_NAME</th>';
									 str +='<th>Mandal/Town/Division</th>';
									 str +='<th>Village/Ward</th>';
									 str +='<th>Name</th>';
									 if(representativeTypeId == 2)
										  str +='<th>Committe Type </th>';	
									 str +='<th>Designation</th>';									
									 str +='<th> Mobile&nbspNo  </th>';
								 str +='</tr>';
							 str +='</thead>';
					      str +='<tbody>';
								
								for(var i in result){
									str +='<tr>';
									str +='<td>'+result[i].district+'</td>';
									
									
									if(result[i].designation == 'MP'){
										if(result[i].constituency != null  && result[i].constituency.trim().length>0 )
											str +='<td>'+result[i].constituency+'</td>';
										else
											str +='<td style="text-align:center;"> - </td>';
										
										str +='<td style="text-align:center;"> - </td>';
									}
									else{
										if(result[i].parliament != null  && result[i].parliament.trim().length>0 )
											str +='<td>'+result[i].parliament+'</td>';
										else
											str +='<td style="text-align:center;"> - </td>';
										if(result[i].constituencyNo != null)
											str +='<td>'+result[i].constituencyNo+'</td>';
										else
											str +='<td style="text-align:center;"> - </td>';
										if(result[i].constituency != null  && result[i].constituency.trim().length>0 )
											str +='<td>'+result[i].constituency+'</td>';
										else
											str +='<td style="text-align:center;"> - </td>';
									}
									if(result[i].tehsil != null && result[i].tehsil.trim().length>0)
										str +='<td>'+result[i].tehsil+'</td>';
									else
										str +='<td style="text-align:center;"> - </td>';
									if(result[i].panchayat != null  && result[i].panchayat.trim().length>0 )
										str +='<td>'+result[i].panchayat+'</td>';
									else
										str +='<td style="text-align:center;"> - </td>';
									
									str +='<td>'+result[i].cadreName+'</td>';
									if(representativeTypeId == 2)
										str +='<td>'+result[i].status+' Committee </td>';
									str +='<td>'+result[i].designation+'</td>';
									str +='<td>'+result[i].mobileNo+'</td>';									
									str +='</tr>';
								}
							str +='</tbody>';
							str +='</table>';
						$("#partyLeaderDetailsId").html(str);
						$("#leadersDetailsTab").dataTable({
						  "aaSorting": [[ 0, "asc" ]],
						  "iDisplayLength": 50,
						  "aLengthMenu": [[ 50, 100, 500, -1], [ 50, 100, 500, "All"]]
						});
						$('#excelBtn').show();
				   }else{
					   $("#partyLeaderDetailsId").html(' <center> No Data available... </center>');
				   }
		  });   			
});

function getCommitteeLevelDetails(){
   var jsObj=
   {
	   
	}
    $.ajax({
          type:'GET',
          url: 'getCommitteeLevelDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
			 for(var i in result){
			   if(result[i].id == 0){
				  $("#committeeLevelId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#committeeLevelId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }
		$(".chosenClass").trigger("chosen:updated");
	  }  
   });
  }
	  
function getCommitteeTypeDetails(){
   var jsObj=
   {
							
   }
    $.ajax({
          type:'GET',
          url: 'getCommitteeTypeDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
			 for(var i in result){
			   if(result[i].id == 0){
				  $("#committeeTypeId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#committeeTypeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }
		$(".chosenClass").trigger("chosen:updated");
	  }
   });
  }	  
function exportToExcel()
{
	tableToExcel('leadersDetailsTab', 'Party Leaders Details');
}

$(document).on("change","#panchayatDivId",function(){
		$('#leadersDetailsDiv').hide();
});

$(document).on("change","#enrollmentId",function(){
		$('#leadersDetailsDiv').hide();
});

$(document).on("change","#designationId",function(){
		$('#leadersDetailsDiv').hide();
});

$(document).on("change","#committeeLevelId",function(){
		$('#leadersDetailsDiv').hide();
});

$(document).on("change","#committeeTypeId",function(){
		$('#leadersDetailsDiv').hide();
});
