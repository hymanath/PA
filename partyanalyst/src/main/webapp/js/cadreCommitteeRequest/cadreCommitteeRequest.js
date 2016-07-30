//changing requests.
function hideDetails(val){
if(val=='1'){
getCommitteeLocations();
$('#posIncreasedId').hide();
$('#changeDesgId').hide();
$("#committeePositionId").val("0");
$("#locationsDivId").show(); 
$("#committeesId").show(); 
$("#reqSubmitId").show(); 
$("#resultStatusId").hide();
$("#designationDivId").show();
$("#changeDesgnationsErrId").hide();


$('#incId').css({"background":"#DBBD2B"});
$('#desgId').css({"background":"#FF9966"});
$('#viewId').css({"background":"#FF9966"});

 $('#selLocId').removeClass('col-md-4').addClass('col-md-3');
  $('#selCommId').removeClass('col-md-4').addClass('col-md-3');
  $('#committeeMainId').removeClass('col-md-4').addClass('col-md-3');
   $('#designationDivId').removeClass('col-md-4').addClass('col-md-3');
 }
if(val=='2'){
getCommitteeLocations();
$("#locationsDivId").show(); 
$("#committeesId").show(); 
$("#reqSubmitId").show(); 

$('#posIncreasedId').hide();
$('#changeDesgId').hide();
$("#committeePositionId").val("0");
$("#resultStatusId").hide();
$("#designationDivId").hide();
    $('#selLocId').removeClass('col-md-3').addClass('col-md-4');
  $('#selCommId').removeClass('col-md-3').addClass('col-md-4');
  $('#committeeMainId').removeClass('col-md-3').addClass('col-md-4');
   $('#designationDivId').removeClass('col-md-3').addClass('col-md-4');
   
$('#incId').css({"background":"#FF9966"});
$('#desgId').css({"background":"#DBBD2B"});
$('#viewId').css({"background":"#FF9966"});
}
if(val=='3'){
  $("#resultStatusId").show();
  
  $("#posIncreasedId").hide(); 
  $("#changeDesgId").hide(); 
  $("#locationsDivId").hide(); 
  $("#committeesId").hide(); 
  $("#reqSubmitId").hide(); 
  $("#changeDesgnationsErrId").hide();
  
 $('#incId').css({"background":"#FF9966"});
$('#desgId').css({"background":"#FF9966"});
$('#viewId').css({"background":"#DBBD2B"});


  gettingRequestsDetailsForAUser("positionsIncreased");
}
}

//(getting locations on load,on change of locations radio buttons)allur,nellore...
function getCommitteeLocations(){
		$('#incId').css({"background":"#DBBD2B"});
        $('#desgId').css({"background":"#FF9966"});
        $('#viewId').css({"background":"#FF9966"});
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
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
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
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
			
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
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
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
	//global variables used in change designations.
	  var globalReqLocationType="";
	  var globalReqLocationValue="";
      var globalReqCommitteeType="";
	  
	//By click submit button for 1st task.
	function getCommitteMembersInfoRequest(){
	     $("#changeDesgnationsErrId").hide();
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
		 
		 if(reqType=='1'){
		    if(designationId== 0){
			  $("#committeePositionIdErr").html("Please Select Designation");
			  return;
		    }
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
		
		globalReqLocationType= reqLocationType;
		globalReqLocationValue =reqLocationValue;
		globalReqCommitteeType=reqCommitteeType;
		  $.ajax({
				type : "POST",
				url : "getCommitteMembersInfoRequestAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue,committeeType:reqCommitteeType} ,
			}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
			
			   if(result!=null){
			     if(reqType=='1'){
			        if(result.electionYear=='Y'){
					 $('#posIncreasedId').show();
				      var str="";
					    str+='<div class="col-md-offset-2 col-md-8 pad1" style="background-color:rgba(0,0,0,0.1);">';
						str+= '<span style="font-weight: bold;"><span style="color:green">'+committeeLocation+ '</span> &nbsp'+committeeType+ '&nbsp '+committeePosition+' Designation</style>';
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
							  if(result.result[i].population != 0){
								  str+='<div class="col-md-offset-2 col-md-8 pad1" style="background-color:rgba(0,0,0,0.1);">';
								   //committeeLocation committeeType committeePosition
								  str+= '<span style="font-weight: bold;">'+committeeLocation+ ' &nbsp'+committeeType+ '&nbsp '+committeePosition+' Designation</style>';
								  str+='</div>';
								  str+='<div class="col-md-offset-2 col-md-8 pad1" style="background-color:rgba(0,0,0,0.1);margin-top:2px;">';
								  str+='Current Max Positions';
								  str+='<input class="form-control" id="currentMaxPositionId" disabled="disabled" value='+result.result[i].population+' /> ';
								  str+=' Request Max Positions';
								  str+='<input class="form-control" id="requestMaxPositionId" /> ';
								  str+='<br/><button id="positionIncreased" class="btn btn-success" onClick="cadreCommitteeIncPositionsOrChangeDesg(\'positionsIncreased\')">Send Request</button>';
								  str+='<div> <img src="images/Loading-data.gif" class="offset7"  id="posIncImageId" style=" margin-left:252px;margin-top: 20px;width:70px;height:60px;display:none;"/></div>';
								  str+='<div id="maxPositionsErrId"></div>';
								  str+='</div>';
							  }else{
								  str+='<div class="col-md-offset-2 col-md-8 pad1" style="background-color:rgba(0,0,0,0.1);">';
								  str+=' This Designation Already Have Max Positions!. You Can Add Any Number Of Members.'
								  str+='</div>';
								  
							  }
							  $("#posIncreasedId").html(str);
							 }
					       }
					     }
			         }
			     }//inc pos.
				 else if(reqType=='2'){
				   if(result.electionYear=='Y'){
				    
				      $('#changeDesgId').show(); 
					
					  if(result.hamletsOfTownship.length>0 && result.hamletsOfTownship!=null){
					   
				      var str='';
					  str+='<div class="col-md-offset-1 col-md-10 pad1" style="background-color:rgba(0,0,0,0.1);">';
					  str+='<span style="font-weight: bold;"><span style="color:green">'+committeeLocation+ '</span> &nbsp'+committeeType+'</style>';
					  str+='<span style="font-weight: bold;color:red; margin-left: 116px;" id="errorChangeDesg"></span>';
					  str+='</div>';
					    
					  str+='<div class="col-md-offset-1 col-md-10" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#fff;margin-top:2px;padding:0px; " >';
					  str+='<table class="table table-bordered text-left" >';
					  str+='<thead style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.2); color:#000">';
					 
                      str+='<th width="10%">Member Image</th>';
                      str+='<th width="20%">Committee Member Name</th>';
                      str+='<th width="18%">Membership No</th>';
					  str+='<th width="20%">Committee Member Current Position</th>';
                      str+='<th width="15%">Select Change Position</th>';
					  str+='</thead>';
					  str+='<tbody style="color:#000">';
					 
					    for(var i=0;i<result.hamletsOfTownship.length;i++){
						  str+='<tr>';
						  str+='<td><img width="32"  height="32" src="https://www.mytdp.com/images/cadre_images/'+result.hamletsOfTownship[i].url+'" onerror="setDefaultImage(this);"/></td>';

						  //str+='<td>'+result.hamletsOfTownship[i].value+'</td>';
						  str+='<td id="CommMember'+i+'" data-attr="'+result.hamletsOfTownship[i].orderId+'" data-attr-tdpCadreId="'+result.hamletsOfTownship[i].mainAccountId+'" >'+result.hamletsOfTownship[i].name+'</td>';
						  str+='<td>'+result.hamletsOfTownship[i].type+'</td>';
						   str+='<td id="currPosition'+i+'" data-attr="'+result.hamletsOfTownship[i].id+'" >'+result.hamletsOfTownship[i].value+'</td>';
						  str+='<td>';
						     str+='<form class="form-inline form-group-sm">';
							   str+='<input type="checkbox" class="changeDesgCheck" id='+i+' onClick="activatePositions(this)"/>';
							  
							 
						       str+='<select class="form-control" id="reqPosition'+i+'" disabled="true">';
                                 str+='<option value="0">Select Position</option>';
								 
								 if(committeePositionaArray != null && committeePositionaArray.length>0)
								 {
									for(var k in committeePositionaArray)									
									{  
									    if(result.hamletsOfTownship[i].id==committeePositionaArray[k].id)
										  str+='<option value="'+committeePositionaArray[k].id+'" selected="selected" >'+committeePositionaArray[k].name+'</option>';
										else
										 str+='<option value="'+committeePositionaArray[k].id+'" >'+committeePositionaArray[k].name+'</option>';
									}
								 }
                               str+='</select>';
							 str+='</form>';
						  str+='</td>';
						  str+='</tr>';
						}
					  
					  str+='</tbody ';
					  str+='</table>';
					
					  str+='</div>'; 
					 
					
					  var str1="";
					  str1+='<div class="col-md-offset-1 col-md-10" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#fff;" >';
					  str1+='<button class="btn btn-success col-md-offset-4" onClick="cadreCommitteeIncPositionsOrChangeDesg(\'changeDesignations\')">Submit Request For Change Position</button>';
					  str1+='</div>';
					  
					  $("#desgReqTableDiv").html(str);
					  $("#desgReqDiv").html(str1);
					 }
					 else{
					    var str='';
					  str+='<div class="col-md-offset-1 col-md-10 pad1" style="background-color:rgba(0,0,0,0.1);">';
					  str+='<span style="font-weight: bold;"><span style="color:green">'+committeeLocation+ '</span> &nbsp'+committeeType+'</style>';
					  str+='<span style="font-weight: bold;color:red; margin-left: 116px;" id="errorChangeDesg"></span>';
					  str+='<div style="font-weight:bold;text-align:center;  margin-left:-183px; margin-top: 8px;"> No Data Available...</div>';
					  str+='</div>';
						
					    $("#desgReqTableDiv").html(str);
						 $("#desgReqDiv").hide();
					 
					 }
				}
					 else{
					     					  

					   // $("#changeDesgId").html("");
					    $("#changeDesgId").hide();
					    $('#posIncreasedId').show();
				        var str="";
					    str+='<div class="col-md-offset-2 col-md-8 pad1" style="background-color:rgba(0,0,0,0.1);">';
						str+= '<span style="font-weight: bold;"><span style="color:green">'+committeeLocation+ '</span> &nbsp'+committeeType+'</style>';
					    str+='</div>';
						str+='<div class="col-md-offset-2 col-md-8 pad1 alert alert-danger"><div style="">This Commitee is Not Confirmed.Use The Following Link To ADD OR UPDATE Details <a href="cadreCommitteeSummaryAction.action">COMMITTEE SUMMARY</a></div></div>';
				      $("#posIncreasedId").html(str);
					 }
				 
				 }//chan desg.
			   }
		    });
	}
	
	 function activatePositions(type){
		 var id=$(type).attr('id');
		 if($(type).is(':checked'))
		   $('#reqPosition'+id).prop("disabled", false);
		 else
		   $('#reqPosition'+id).prop("disabled", true);
	 }
							   
	function cadreCommitteeIncPositionsOrChangeDesg(type)
	{
	 
	  $("#changeDesgnationsErrId").html("");
	  $("#errorChangeDesg").html("");
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
		  if(parseInt(requestedMaxPositions) <= parseInt(currentmaxPositions)){
		   $('#maxPositionsErrId').html("<span>Requested Maximum Positions must be greater than Current Maximum Positions.</span>");
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
	 var sameRoleId=false;
	 if(type=="changeDesignations"){
	   
	   $('.changeDesgCheck').each(function()
       {
		   if($(this).is(':checked'))
		   { 
				var jsobj =new Object();
				var id=$(this).attr('id');
				
				
				jsobj.tdpCommitteeMemberId=$('#CommMember'+id).attr("data-attr");
				jsobj.tdpCadreId=$('#CommMember'+id).attr("data-attr-tdpCadreId");
				jsobj.currentRole=$('#currPosition'+id).attr("data-attr");
				jsobj.newRole=$('#reqPosition'+id+' option:selected').val();
				if(jsobj.newRole!='0'){
				  if(jsobj.currentRole==jsobj.newRole){
				     sameRoleId=true;
				  }
				  else
				   requestArray.push(jsobj);
				}
				
			}
       });
	   if(sameRoleId==true){
	    $("#errorChangeDesg").html("Requested Position Is Not As Same As Current Position.");
		sameRoleId=false;
	    return;
	   }
	   if(requestArray.length==0){
	    $("#errorChangeDesg").html("Please Check Atleast One Change Position");
	    return;
	   }
	   var affId = $("#afflitCommitteeId").val();
	   
	   jsObj.type=type;
	   jsObj.requestArray=requestArray;
	   
	   //extra parameters
	    jsObj.globalReqLocationType= globalReqLocationType;
		jsObj.globalReqLocationValue =globalReqLocationValue;
		jsObj.globalReqCommitteeType=globalReqCommitteeType;
	 }
	  if(type=="positionsIncreased"){
	   $("#posIncImageId").show();
	  }
	 
	
	   $.ajax({
				type : "GET",
				url : "cadreCommitteeIncPositionsOrChangeDesgAction.action",
				data: {task:JSON.stringify(jsObj),globalReqLocationValue:affId}
			}).done(function(result){
			    
	            $("#posIncImageId").hide();
	            if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}  
			 
			   if(type=="positionsIncreased"){
			     if(result.resultCode=='1')
			       $('#maxPositionsErrId').html("<span style='color:green;margin-left: 204px;font-size: 20px'>Request Sent Successfully.</span>");
			     else
			      $('#maxPositionsErrId').html("<span style='color:red;margin-left: 204px;font-size: 20px'>Sending Request Failed.</span>");
				}
				else if(type="changeDesignations"){
				$("#changeDesgnationsErrId").show();
				  if(result.resultCode=='2'){
				    
					 var msgArray=[];
					 var msgStr="";
					 var desgMsg="";
					 
					 var message=result.message; 
					 msgArray = message.split(","); 
					
			         if(msgArray!=null && msgArray.length>0){
					   if(msgArray.length==1){
					     msgStr=msgArray[0];
						 desgMsg="Designation";
					   }
					   else{
					      for(var i in msgArray){
					        if(msgStr!="")
					         msgStr=msgStr+","+msgArray[i];
						    else
						     msgStr=msgStr;
					      }
						  desgMsg="Designations";
					    } 
					 }
					 
					
				     $('#changeDesgnationsErrId').html("<span style='color:red;margin-left: 221px;;font-size:17px'>Request Failed. The "+desgMsg+" "+msgStr+"  Is Exceeding Max Count.</span>");
				   }
				  else if(result.resultCode=='1')
				    $('#changeDesgnationsErrId').html("<span style='color:green;margin-left: 423px;;font-size: 20px'>Request Sent Successfully.</span>");
				  else if(result.resultCode=='0')
				   $('#changeDesgnationsErrId').html("<span style='color:green;margin-left: 204px;font-size: 20px'>Sending Request Failed.</span>");
				}
			});
	}
	  //desg on change validation.
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
	 


	function  gettingRequestsDetailsForAUser(type){
	 $("#processImgId").show();
	
	 $.ajax({
				type : "GET",
				url : "gettingRequestsDetailsForAUserAction.action",
				data: {type:type}
			}).done(function(result){
			    $("#processImgId").hide();
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
			   if(result!=null){
			    
			     var str='';
			     str+='<div class="col-md-10 col-md-offset-1 m_top20" style="background-color:rgba(0,0,0,0.1);overflow:scroll;height:600px;">';
                	str+='<table class="table table-yellow-bordered table-condensed">';
					    str+=' <div style="padding:8px;padding-left:2px;padding-right:2px;" class="col-md-offset-0 text-center col-md-10">';
						str+='<label  class="radio-inline"><input id="requestRadioId1" type="radio" name="requestRadio" value="1"  onClick="gettingRequestsDetailsForAUser(\'positionsIncreased\');"/><span class="text-success" style="font-size:1.2em">INCREASE POSITION REQUEST STATUS</span></label>';
						str+='<label  class="radio-inline"  style="margin-left: 20px;"><input id="requestRadioId2" type="radio" name="requestRadio" value="2" onClick="gettingRequestsDetailsForAUser(\'changeDesignations\');"/><span class="text-success" style="font-size:1.2em">CHANGE POSITION REQUEST STATUS</span></label>';
						str+='</div>';
						
						if(type=="positionsIncreased"){
						str+='<thead>';
						    str+='<th width="10%" style="padding-left: 23px;">DATE</th>';
                        	str+='<th width="17%"  style="padding-left: 31px;">LOCATION</th>';
                            str+='<th width="20%" style="padding-left: 10px;">COMMITTEE NAME</th>';
                            str+='<th width="18%" style="padding-left: 21px;">POSITION NAME</th>';
                            str+='<th width="13%"><small>CURRENT MAX POSITONS</small></th>';
                            str+='<th width="11%"><small>REQUESTED MAX POSITIONS</small></th>';
                            str+='<th width="10%" style="padding-left: 13px;">STATUS</th>';
                            str+='<th width="13%" style="padding-left: 17px;">Ref-No</th>';
                        str+='</thead>';
                         str+='<tbody>';
						   for(var i in result){//loc comm pre cur req sta
						     str+='<tr>';
							 str+='<td>'+result[i].dateString+'</td>';
                        	 str+='<td style="padding-left: 14px;">'+result[i].location+' '+result[i].locationType+'</td>';
                             str+='<td style="padding-left: 25px;">'+result[i].committeeName+'</td>';
                             str+='<td style="padding-left: 21px;">'+result[i].role+'</td>';
                             str+='<td style="padding-left: 42px;">'+result[i].currentPosCount+'</td>';
                             str+='<td style="padding-left: 42px;">'+result[i].requestdPosCount+'</td>';
                             str+='<td style="padding-left: 12px;">'+result[i].status+'</td>';
                             str+='<td>'+result[i].refNo+'</td>';
							 str+='</tr>';
							}
                        str+='</tbody>';
                    str+='</table>';
					
				  }
				  else if(type="changeDesignations"){
				  str+='<thead>';
                        str+='<th style="padding-left: 22px;">DATE</th>';
                        str+='<th>LOCATION</th>';
						str+='<th>COMMITTEE NAME</th>';
		                str+='<th>MEMBER IMAGE</th>';
						str+='<th style="padding-left: 20px;">COMMITTEE MEMBER NAME</th>';
                        str+='<th>MEMBERSHIP NO</th>';
                        str+='<th style="padding-left: 18px;">CURRENT ROLE</th>';
						str+='<th style="padding-left: 17px;">REQUESTED ROLE</th>';
                        str+='<th>STATUS</th>';
                        str+='<th style="padding-left: 12px;">REF-NO</th>';
                  str+='</thead>';
				  str+='<tbody>';
				  for(var i in result){
				   var rowcount = result[i].locationsList.length;
				   var paddingTop=(rowcount*20);
				  
				   str+='<tr>';
				   str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].dateString+'</td>';
				   str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].location+' '+result[i].locationType+'</td>';
				   str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].committeeName+'</td>';
				   
				    for(var j in result[i].locationsList)
					{					
						 str+='<td ><img data-attr-tdpCommitteememberId="'+result[i].locationsList[j].tdpCommitteeMemberId+'" style="margin-top: 26px; margin-left: 12px;" width="35"  height="35" src="https://www.mytdp.com/images/cadre_images/'+result[i].locationsList[j].positionId+'" onerror="setDefaultImage(this);"/></td>';
						 str+='<td style="padding-top:'+paddingTop+'px;">'+result[i].locationsList[j].position+'</td>';
						 str+='<td style="padding-top:'+paddingTop+'px;">'+result[i].locationsList[j].memberShipNo+'</td>';
						 str+='<td data-attr-currentRoleId="'+result[i].locationsList[j].currentRoleId+'" style="padding-top:'+paddingTop+'px; padding-left: 18px;">'+result[i].locationsList[j].currentRole+'</td>';
						 str+='<td data-attr-newRoleId="'+result[i].locationsList[j].newRoleId+'" style="padding-top:'+paddingTop+'px; padding-left: 18px;">'+result[i].locationsList[j].newRole+'</td>';
						 if(j == 0) 
						 {
							 str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].status+'</td>';
							 str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].refNo+'</td>';
						 }
						 str+='</tr>';
						 
				    }
				  				   
				  }
				  str+='</tbody>';
				  str+='</table>';
				  }
                str+='</div>';
				
		     $('#resultReportId').html(str);
			}
			else{
			     var str='';
			     str+=' <div style="padding:8px;padding-left:2px;padding-right:2px;" class="col-md-offset-0 text-center col-md-10">';
				 str+='<label  class="radio-inline"><input id="requestRadioId1" type="radio" name="requestRadio" value="1"  onClick="gettingRequestsDetailsForAUser(\'positionsIncreased\');"/><span class="text-success" style="font-size:1.2em">INCREASE POSITION REQUEST STATUS</span></label>';
				 str+='<label  class="radio-inline"  style="margin-left: 20px;"><input id="requestRadioId2" type="radio" name="requestRadio" value="2" onClick="gettingRequestsDetailsForAUser(\'changeDesignations\');"/><span class="text-success" style="font-size:1.2em">CHANGE POSITION REQUEST STATUS</span></label>';
				 str+='</div>';
				 str+='<span style="font-weight:bold;text-align:center; margin-left: 471px;"> No Data Available...</span>';
				$('#resultReportId').html(str);
			}
			 
			  if(type=="positionsIncreased"){
				$("#requestRadioId1").prop("checked","checked");
			  }
				else if(type="changeDesignations"){
				$("#requestRadioId2").prop("checked","checked");
				}
			  
			   
			});

	}
	function setDefaultImage(img)
	{
		img.src = "images/cadreCommitee/Member_thamb_image.png";
	}