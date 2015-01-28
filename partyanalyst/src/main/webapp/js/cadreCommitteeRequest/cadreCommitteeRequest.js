function hideDetails(val){
if(val=='1'){
getCommitteeLocations();
$('#posIncreasedId').hide();
$('#changeDesgId').hide();
$("#committeePositionId").val("0");
}
if(val=='2'){
getCommitteeLocations();
$('#posIncreasedId').hide();
$('#changeDesgId').hide();
$("#committeePositionId").val("0");
}
if(val=='3'){
  $("#posIncreasedId").hide(); 
  $("#changeDesgId").hide(); 
  $("#locationsDivId").hide(); 
  $("#committeesId").hide(); 
  $("#reqSubmitId").hide(); 

}
}

//(getting locations on load,on change of locations radio buttons)allur,nellore...
function getCommitteeLocations(){
		
		$("#committeeTypeId").val(0);
		//$("#committeeMainId").hide();
		$("#afflitCommitteeId").prop("disabled", true);
		 
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		
		var reqLocationType ="";
		
		if($("#mndlLvlCommittSelec").is(':checked')){
		  reqLocationType ="mandal";
		}
		$.ajax({
			type : "POST",
			url : "getCommitteLocationsAction.action",
			data : {locationType:reqLocationType} ,
		}).done(function(result){
			$("#committeeLocationId  option").remove();
			$("#committeeLocationId").append('<option value="0">Select Location</option>');
			var reqNewLocationType ="";
			if($("#mndlLvlCommittSelec").is(':checked')){
			  reqNewLocationType ="mandal";
			}
			if(reqNewLocationType == reqLocationType){
				for(var i in result){
				   $("#committeeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				 }
			 }
		});
	}
	//on change of location radio buttons.
	function validateSearchType(areaTypeId)
	{
		$("#afflitCommitteeId  option").remove();
		$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
		
		$("#committeePositionId  option").remove();
		$("#committeePositionId").append('<option value="0">Select Designation</option>');
	
		if(areaTypeId == 1) //  Village / Ward / Division
		{
			$('#areaTypeId').val(areaTypeId);
		}
		
		else if(areaTypeId == 2) // Mandal / Town / GHMC 
		{				
			$('#areaTypeId').val(areaTypeId);
		}
	}
	
	

	//OnChange of location,onchange of committee.
	function populateDefaultValue(level){
		if(level == 1){
		  $("#committeeLocationIdErr").html("");
		  $("#committeeTypeId").val(0);
		}
		$("#afflitCommitteeId  option").remove();
		$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
	}
	
	//(On change of commitees)affl comm ,ex yuth,bc cell...
	function getAffiliatedCommitsForALoc(){
		
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		
		var locId = $("#committeeLocationId").val();
		if( locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		$("#afflitCommitteeId").prop("disabled", false);
		$("#afflitCommitteeId  option").remove();
		$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
		if($("#committeeTypeId").val() == 2){
			$("#committeeMainId").show();
			var reqLocationType = "";
			var reqLocationValue = "";
			if($("#mndlLvlCommittSelec").is(':checked')){
			  reqLocationType ="mandal";
			}
			reqLocationValue=$("#committeeLocationId").val();
			$.ajax({
				type : "POST",
				url : "getAllAffiliatedCommittiesAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue} ,
			}).done(function(result){	
				if(result != null)
				{
					for(var i in result){
					   $("#afflitCommitteeId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
				}
				
			});
					
		}else{
              $("#committeeMainId").show();
			  $("#afflitCommitteeId").prop("disabled", true);
			  }
	   }
	   
	var committeePositionaArray=[];   
	   //(on change of commitees.)presi,vp...
	function getCommitteCadreMembersInfo(type){
		committeePositionaArray=[];
		var areaType = $('#areaTypeId').val();
		
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		
		var locId = $("#committeeLocationId").val();
		var locVal = $("#afflitCommitteeId").val();
		if(locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		
		
		 var reqCommitteeType = "main";
		 var reqLocationType = "";
		 var title ="MAIN COMMITTEE";
		 if($("#committeeTypeId").val() == 2){
			 reqCommitteeType = "affiliated";
			 title =$.trim($("#afflitCommitteeId option:selected").text())+" COMMITTEE";
		 }
		 $("#affComitteeMainTitle").html(title.toUpperCase());
		 if(reqCommitteeType == "main"){
		   if(areaType == 2){
		     reqLocationType ="mandal";
		   }
		   reqLocationValue=$("#committeeLocationId").val();
		 }else{
			 reqLocationValue=$("#afflitCommitteeId").val();
		 }
		 $("#committeePositionId  option").remove();
		 $("#committeePositionId").append('<option value="0">Select Designation</option>');
		  $.ajax({
				type : "POST",
				url : "getCommitteMembersInfoAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue,committeeType:reqCommitteeType} ,
			}).done(function(result){

				if(result != null)				
				{
					if(result.result != null && result.result.length>0)
					{
						for(var i in result.result)
						{
							$("#committeePositionId").append('<option value="'+result.result[i].locationId+'">'+result.result[i].locationName+'</option>');
							var jsobj=new Object();
							jsobj.id=result.result[i].locationId;
							jsobj.name=result.result[i].locationName;
							committeePositionaArray.push(jsobj);
						}
					}
				}
		   });
	}
	
	//By click submit button for 1st task.
	function getCommitteMembersInfoRequest(){
	     $('#posIncreasedId').hide();
		 var committeeLocation= $("#committeeLocationId option:selected").text();
		 var committeeType=$("#committeeTypeId option:selected").text();
		 var committeePosition=$("#committeePositionId option:selected").text();
		
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		
		var locId = $("#committeeLocationId").val();
		var locVal = $("#afflitCommitteeId").val();
		var designationId=$("#committeePositionId").val();
		var reqType=$('input[name="requestType"]:radio:checked').val();
		
	
		if(locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		if($("#committeeTypeId").val() == 2){
			 if(locVal == null || locVal == 0){
				$("#afflitCommitteeIdErr").html("Please Select Affiliated Committee");
				return;
			}
		 }
		 if(designationId== 0){
			$("#committeePositionIdErr").html("Please Select Designation");
			return;
		}
		 $("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		$("#committeePositionIdErr").html("");
		
		 //$("#committeeDetailsDiv").show();
	     //$("#commitMembrsCountDiv").html('<center><img src="images/icons/loading.gif"  /></center>');
		 //$("#committeeMmbrsMainDiv").html("");
		 
		 var reqCommitteeType = "main";
		 var reqLocationType = "";
		 var title ="MAIN COMMITTEE";
		 if($("#committeeTypeId").val() == 2){
			 reqCommitteeType = "affiliated";
			 title =$.trim($("#afflitCommitteeId option:selected").text())+" COMMITTEE";
		 }
		 $("#affComitteeMainTitle").html(title.toUpperCase());
		 if(reqCommitteeType == "main"){
		   if($("#mndlLvlCommittSelec").is(':checked')){
		     reqLocationType ="mandal";
		   }
		   reqLocationValue=$("#committeeLocationId").val();
		 }else{
			 reqLocationValue=$("#afflitCommitteeId").val();
		 }
		// $("#viewMembrsBtn").attr("disabled","disabled");
		  $.ajax({
				type : "POST",
				url : "getCommitteMembersInfoRequestAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue,committeeType:reqCommitteeType,designation:designationId} ,
			}).done(function(result){
			   if(result!=null){
			     if(reqType=='1'){
			        if(result.electionYear=='Y'){
					 $('#posIncreasedId').show();
				      var str="";
					    str+='<div class="col-md-offset-2 col-md-8 pad1" style="background-color:rgba(0,0,0,0.1);">';
						str+= '<span style="font-weight: bold;">'+committeeLocation+ ' &nbsp'+committeeType+ '&nbsp '+committeePosition+' Designation</style>';
					    str+='</div>';
						str+='<div class="col-md-offset-2 col-md-8 pad1 alert alert-danger"><div style="">This Commitee is Already Confirmed.You Cannot Increase Designations..</div></div>';
				      $("#posIncreasedId").html(str);
				     }
				    else{
				       if(result.result.length>0 && result.result!=null){
					      $('#posIncreasedId').show();
					      for(var i=0;i<result.result.length;i++){
					        if(designationId==result.result[i].locationId){
							  var str='';
							  str+='<div class="col-md-offset-2 col-md-8 pad1" style="background-color:rgba(0,0,0,0.1);">';
							   //committeeLocation committeeType committeePosition
							  str+= '<span style="font-weight: bold;">'+committeeLocation+ ' &nbsp'+committeeType+ '&nbsp '+committeePosition+' Designation</style>';
							  str+='</div>';
							  str+='<div class="col-md-offset-2 col-md-8 pad1" style="background-color:rgba(0,0,0,0.1);margin-top:2px;">';
							  str+='Current Max Positions';
							  str+='<input class="form-control" id="currentMaxPositionId" disabled="disabled" value='+result.result[i].population+' /> ';
							  str+=' Request Max Positions';
							  str+='<input class="form-control" id="requestMaxPositionId" /> ';
							  str+='<button id="positionIncreased" class="btn btn-success" onClick="cadreCommitteeIncPositionsOrChangeDesg(\'positionsIncreased\')">Send Request</button>';
							  str+='<div id="maxPositionsErrId"></div>';
							  str+='</div>';
							  $("#posIncreasedId").html(str);
							 }
					       }
					     }
			         }
			     }//inc pos.
				 else if(reqType=='2'){
				   if(result.electionYear=='Y'){
				      var str='';
					  str+='<div class="col-md-offset-1 col-md-10 pad1" style="background-color:rgba(0,0,0,0.1);">';
					  str+='Kavali mandal Main Committee <u>current vice president</u> postion';
					  str+='</div>';
					  str+='<div class="col-md-offset-1 col-md-10" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#fff;margin-top:2px;" >';
					  str+='<table class="table table-bordered text-left" >';
					  str+='<thead style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.2); color:#000">';
					  str+='<th width="20%">Committee Member Position</th>';
                      str+='<th width="9%">Member Image</th>';
                      str+='<th width="20%">Committee Member Name</th>';
                      str+='<th width="20%">Membership No</th>';
                      str+='<th>Select Change Position</th>';
					  str+='</thead>';
					  str+='<tbody style="color:#000">';
					  if(result.hamletsOfTownship.length>0 && result.hamletsOfTownship!=null){
					    for(var i=0;i<result.hamletsOfTownship.length;i++){
						  str+='<tr>';
						  str+='<td id="currPosition'+i+'" data-attr="'+result.hamletsOfTownship[i].id+'" >'+result.hamletsOfTownship[i].value+'</td>';
						  str+='<td>'+result.hamletsOfTownship[i].value+'</td>';
						  str+='<td id="CommMember'+i+'" data-attr="'+result.hamletsOfTownship[i].orderId+'">'+result.hamletsOfTownship[i].name+'</td>';
						  str+='<td>'+result.hamletsOfTownship[i].type+'</td>';
						  str+='<td>';
						     str+='<form class="form-inline form-group-sm">';
							   str+='<input type="checkbox" class="changeDesgCheck" id='+i+' />';
						       str+='<select class="form-control" id="reqPosition'+i+'">';
                                 str+='<option value="0">Select Position</option>';
								 
                               str+='</select>';
							 str+='</form>';
						  str+='</td>';
						  str+='</tr>';
						}
					  }
					  str+='</tbody ';
					  str+='</table>';
					  str+='<button class="btn btn-success col-md-offset-4" onClick="cadreCommitteeIncPositionsOrChangeDesg(\'changeDesignations\')">Submit Request For Change Position</button>';
					  str+='</div>'; 
					  $("#changeDesgId").html(str);
				     }
				 
				 }//chan desg.
			   }
		    });
	}
	
	function cadreCommitteeIncPositionsOrChangeDesg(type)
	{
	 
	  var jsObj =new Object();
	  
	  if(type=="positionsIncreased"){
	     var currentmaxPositions=$("#currentMaxPositionId").val().trim();
	     var requestedMaxPositions=$("#requestMaxPositionId").val().trim();
	     var tdpCommitteeRoleId=$("#committeePositionId").val().trim();
		
		 
		 if(requestedMaxPositions==''){
		   $('#maxPositionsErrId').html("<span>Requested Maximum Positions must not be empty.</span>");
		   return;
		 }
		 if(requestedMaxPositions=='0'){
		   $('#maxPositionsErrId').html("<span>Requested Maximum Positions  must not be zero.</span>");
		   return;
		 }
		  if(requestedMaxPositions <= currentmaxPositions){
		   $('#maxPositionsErrId').html("<span>Requested Maximum Positions must be greater than or equal to Current Maximum Positions.</span>");
		   return;
		 }
		 if(isNaN(requestedMaxPositions)){
		   $('#maxPositionsErrId').html("<span>Requested Maximum Positions must be Number</span>");
		   return;
		 }
		 
		 
		 jsObj.currentmaxPositions=currentmaxPositions;
		 jsObj.requestedMaxPositions=requestedMaxPositions;
		 jsObj.tdpCommitteeRoleId=tdpCommitteeRoleId;
		 jsObj.type=type;
	  }
	
	  
     var requestArray=[];
	 if(type=="changeDesignations"){
	   $('.changeDesgCheck').each(function()
       {
		   if($(this).is(':checked'))
		   { 
				var jsobj =new Object();
				var id=$(this).attr('id');
				
				
				jsobj.tdpCommitteeMemberId=$('#CommMember'+id).attr("data-attr");
				jsobj.currentRole=$('#currPosition'+id).attr("data-attr");
				jsobj.newRole=$('#reqPosition'+id+' option:selected').val();
				requestArray.push(jsobj);
			}
       });
	   
	   jsObj.type=type;
	   jsObj.requestArray=requestArray;
	 }
	
	   $.ajax({
				type : "GET",
				url : "cadreCommitteeIncPositionsOrChangeDesgAction.action",
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
			   if(result.resultCode=='1')
			       $('#maxPositionsErrId').html("<span style='color:green;margin-left: 204px;font-size: 20px'>Request Sent Successfully.</span>");
			   else
			      $('#maxPositionsErrId').html("<span style='color:green'>Sending Request Failed.</span>");
			});
	}
	
	  function checkDesgValidation(){
		    var committeePositionId= $("#committeePositionId").val();
			if(committeePositionId == null || committeePositionId == 0){
			  $("#committeePositionIdErr").html("Please Select Designation");
			  return;
		    }
		   if($("#committeePositionId").val() == 0){
			 $("#committeePositionIdErr").html("Please Select Designation");
			 return;
		   }
		  $("#committeePositionIdErr").html("");
	 }