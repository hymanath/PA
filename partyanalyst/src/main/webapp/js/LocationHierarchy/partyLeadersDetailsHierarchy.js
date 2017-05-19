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
	getDistrictsForStates(globalstateId);
	
	if(stateId =='36' || stateId =='1'){
	$('#committeeTypeDivId').hide();
	$('#committeeLevelDivId').hide();
	$("#errorMessegeId").html('');
	  $("#constituencyId").html('');
	}
	
});

function getDistrictsForStates(globalstateId){
   var jsObj=
   {
		stateId:globalstateId						
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
  	
$(document).on('change','#leaderTypeId',function(){
	var leadrId = $("#leaderTypeId").val();
	 if(leadrId == 0){
		getPublicRepresentsDetails();
		getCommitteeRoles();
		getCommitteeLevelDetails();
		getCommitteeTypeDetails();
       $('#committeeTypeDivId').show();
		$('#committeeLevelDivId').show();
		$('#leadersDetailsDiv').hide();
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
		$('#leadersDetailsDiv').hide();
		}
});
	  
function getCommitteeRoles(callTypeId){
		 var jsObj={
				task:"roles"
				}
    $.ajax({
    	type:'GET',
    	url: 'getAllCommitteesAction.action',
    	data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(callTypeId == 0){
			 $("#designationId").find('option').remove();
			 $(".chosenClass").trigger("chosen:updated");
			 getPublicRepresentsDetails(0)
		}
		var str ='';
		if(result != null){
			for(var i in result)
			{
			$("#designationId").append('<option value="1'+result[i].id+'">'+result[i].name+'</option>');
			}
		   $(".chosenClass").trigger("chosen:updated");
		}
   });		
}
function getPublicRepresentsDetails(callTypeId){
    	var jsObj={
    	  task:"publicRepresentatives"
    		}
    	$.ajax({
    	  type:'GET',
    	  url: 'getPublicRepresentativeTypes.action',
    	  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
			if(result != null){
				for(var i in result)
				{
				  $("#designationId").append('<option value="2'+result[i].id+'">'+result[i].name+'</option>');
				}
				$(".chosenClass").trigger("chosen:updated");
			}
   });	
 }

 var excelUrl="";
function getLeadersDetasils(searchType){
	var tempType = searchType;
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
	
	 var errMsg="";	
	 
	 if(districtIds == null || districtIds.length==0){
		errMsg=errMsg+"Please select atleast one district.<br/>";
	} 
	
	if(enrollmentIdsArr == null || enrollmentIdsArr.length==0){
		errMsg=errMsg+"Please select atleast one enrollment year . <br/>";
	}
	
	if(designationIdArr == null || designationIdArr.length==0){
		errMsg=errMsg+"Please select atleast one designation. <br/>";
	}	  
	
	 if(representativeTypeId == 2 || representativeTypeId == 0){	
		if(pageType == 'District'){	 
			if(districtIds == null || districtIds==0){
				errMsg=errMsg+"Please select atleast one district. <br/>";
			}	
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
	
	if(errMsg.length>0){
		$("#errorMessegeId").html(errMsg);
		return;
	}else{
		$("#errorMessegeId").html('');
	}
	
	$('#leadersDetailsDiv').show();
	
	if(pageType == 'Constituency'){
		levelId = 4;
	 }else if(pageType == 'District'){
		 levelId = 3;
	 }
	 
	 var tempIds =[];
	if(panchayatIds != null){
		levelId = 6;
		if(panchayatIds.length>0){
			for(var i in panchayatIds){
				if(parseInt(panchayatIds[i])==0){
					tempIds = panchayatArr;
				}
				locationArr.push(parseInt(panchayatIds[i]));
			}
		}
		if(tempIds.length>0)
			locationArr = tempIds;
	}else if(mandalIds != null){
		levelId = 5;		
		if(mandalIds.length>0){
			for(var i in mandalIds){
				if(parseInt(mandalIds[i])==0){
					tempIds = mandalArr;
				}
				locationArr.push(parseInt(mandalIds[i]));
			}
		}
		if(tempIds.length>0)
			locationArr = tempIds;
		
	}else if(constituencyIds != null){
		levelId = 4;
		
		if(constituencyIds.length>0){
			for(var i in constituencyIds){
				if(parseInt(constituencyIds[i])==0){
					tempIds = constiArr;
				}
				locationArr.push(parseInt(constituencyIds[i]));
			}
		}
		if(tempIds.length>0)
			locationArr = tempIds;
		
	}else if(districtIds != null){
		levelId = 3;
		if(districtIds.length>0){
			for(var i in districtIds){
				if(parseInt(districtIds[i])==0){
					tempIds = districtsArr;
				}
				locationArr.push(parseInt(districtIds[i]));
			}
		}
		if(tempIds.length>0)
			locationArr = tempIds;
	}else{
		levelId = 2;
		locationArr = globalstateId;
	}
	
	if(pageType == 'Constituency'){
		if(panchayatIds != null && parseInt(panchayatIds)>0){
			levelId = 6;
			locationArr = panchayatIds;
		}else if(mandalIds != null && parseInt(mandalIds)>0){
			levelId = 5;
			locationArr = mandalIds;
		}else{
			locationArr =locationIdsArr;
			levelId = 4;
		}		
	}
	else if(pageType == 'District'){
		if(panchayatIds != null && parseInt(panchayatIds)>0){
			levelId = 6;
			locationArr = panchayatIds;
		}else if(mandalIds != null && parseInt(mandalIds)>0){
			levelId = 5;
			locationArr = mandalIds;
		}else if(constituencyIds != null && parseInt(constituencyIds)>0){
			levelId = 4;
			locationArr = constituencyIds;
			//locationArr.push(constituencyIds);
		}else{
			locationArr =locationIdsArr;
			levelId = 3;
		}
	}
		 var firstIndex=0;
		  var maxIndex=1000;
		  if(searchType == 'EXPORTEXCEL'){
			firstIndex=0;
			maxIndex=1500;
			searchType = searchType+"::"+excelUrl;
			$("#exportId").html('<img src="images/Loading-data.gif" style="width:50px;height:50px;">');
		  }else{
			   $('#excelBtn').hide();
			  $("#partyLeaderDetailsId").html('<img src="images/Loading-data.gif" style="margin-left: 550px;">');
		  }
		 var jsObj =
		  {
			levelId:levelId,
			representativeTypeId :representativeTypeId,
			locationIds:locationArr,
			designationIds:designationIdArr,
			firstIndex:firstIndex,
			maxIndex:maxIndex,
			committeeLevelIdsArr:committeeLevelIdsArr,
			enrollmentId:enrollmentId,
			stateId:globalstateId,
			committeeTypeIdsArr:committeeTypeIdsArr,
			enrollmentIdsArr:enrollmentIdsArr,
			reportType:searchType
		  };
		  
		  $.ajax({  
			type:'GET',            
			url: 'getPartyLeadersDeatailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		  }).done(function(result){
			  $("#exportId").html('');
			  if(tempType == 'EXPORTEXCEL' && result != null && result.length>0){
				  window.open('http://www.mytdp.com/'+result[0].totalImagePathStr+'');
			  }
			  else{
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
										  str +='<th>Committe&nbspType </th>';	
									 str +='<th>Designation</th>';									
									 str +='<th> Mobile&nbspNo  </th>';
								 str +='</tr>';
							 str +='</thead>';
					      str +='<tbody>';
								
								for(var i in result){
									
									if(i==0)
										excelUrl =result[i].aadharNo;
									
									str +='<tr>';
									str +='<td>'+result[i].district+'</td>';
									
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
									
									if(result[i].tehsil != null && result[i].tehsil.trim().length>0)
										str +='<td>'+result[i].tehsil+'</td>';
									else
										str +='<td style="text-align:center;"> - </td>';
									if(result[i].panchayat != null  && result[i].panchayat.trim().length>0 )
										str +='<td>'+result[i].panchayat+'</td>';
									else
										str +='<td style="text-align:center;"> - </td>';
									
									str +='<td>'+result[i].cadreName+'</td>';
									if(result[i].status != null )
										str +='<td>'+result[i].status+' Committee </td>';
									else
										str +='<td style="text-align:center;"> - </td>';
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
			  }
		  });   			
}

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
	  
function exportToExcel(buildType)
{
	getLeadersDetasils(buildType);
	//tableToExcel('leadersDetailsTab', 'Party Leaders Details');
//New Ajax call
}

$(document).on("change","#districtId",function(){
	$("#constituencyId").html('');
	$("#mandalId").html('');
	$("#panchayatDivId").html('');
	
	var districtIdsLst = $("#districtId").val();
	var tempIds=[];
	if(districtIdsLst == null || districtIdsLst.length ==0)
			districtIdsLst=[];
		
		if(districtIdsLst.length>0){
			for(var i in districtIdsLst){
				if(parseInt(districtIdsLst[i])==0){
					tempIds = districtsArr;
				}
			}
		}
		if(tempIds.length>0){
			districtIdsLst=tempIds;
		}
		$('#leadersDetailsDiv').hide();
		var jsObj=
        {				
		  districtIds:districtIdsLst						
	    }
		 constiArr =[];
    $.ajax({
          type:'GET',
          url: 'getMultplConstituencesByDistctIdsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   $("#constituencyId").html('');
        for(var i in result){
			constiArr.push(result[i].id);
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
	$("#mandalId").html('');
	$("#panchayatDivId").html('');
	var constituencyIdsLst = $('#constituencyId').val();
	
		$('#leadersDetailsDiv').hide();
		var tempIds=[];
		if(constituencyIdsLst == null || constituencyIdsLst.length ==0)
			constituencyIdsLst=[];
		
		if(constituencyIdsLst.length>0){
			for(var i in constituencyIdsLst){
				if(parseInt(constituencyIdsLst[i])==0){
					tempIds = constiArr;
				}
			}
		}
		if(tempIds.length>0){
			constituencyIdsLst=tempIds;
		}
		
		var jsObj ={					
			constituencyIds:constituencyIdsLst
		}
		mandalArr=[]
		$.ajax({
			type : "GET",
			url : "getMultpleMandalsByConstituencyIdsAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			if(result != null)
			{
			 $("#mandalId").html('');
			for(var i in result)
			{
				mandalArr.push(result[i].locationId);
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
    $("#panchayatDivId").html('');
    var mandalId = $('#mandalId').val();
	var constituencyId = $('#constituencyId').val();
	$('#leadersDetailsDiv').hide();
	if(mandalId == null || mandalId.length ==0)
		mandalId=[];
	 var jsObj ={					
			mandalIds : mandalId,
			constituencyIds :constituencyId
			}
			panchayatArr=[];
       	 $.ajax({
			type : "GET",
			url : "getMultplePanchayatWardByMandalIdsAction.action",
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