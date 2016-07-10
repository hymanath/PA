
	<div class="row">
		<div class="col-md-12">
			
				<div id="errorDiv1" class="col-md-12" style="font-weight:bold;color:red;font-size:15px;"></div>
				<form id="saveAlertForm" name="saveAlertForm" enctype="multipart/form-data" action="saveAlertAction.action" method="POST">
				<div class="panel-body">
					<div class="row">
					
					
					<div class="col-md-3 advanceSearchCls">
												<label>Search Type</label>
                                                <select class="dropkickClass"  id="advanceSearchTypeId" onchange="showHideBySearchType();buildLevels();">
													<option value="0">Select Search Type</option>
													<option value="1">Name</option>
													<option value="2">Public Representative</option>
													<option value="3">Party Committee</option>
												</select>
											</div>
											
											
											 <div class="col-md-4 pad_0 advanceSearchCls advanceprclsDiv">
                                            	<label class="advanceNameCls">Search By Name<span class="text-danger">*</span></label>
                                                <input type="text" class="form-control advanceNameCls clearCls" id="advanceSearchValueId">
												<label class="advancePRCls">Search Designation</label>
												 <select class="advancePRCls dropkickClass"  id="advanceDesignationId" onchange="getLevelByDesignation();">
													<option value="0">Select Designation</option>
												</select>
												<span id="advanceErrDigitsId" class="full-right" style="color:red;"></span>
						                    </div>
											
											
						
						<div class="col-md-3 levelShowCls" >
							<label>Level</label>
							<select class="dropkickClass" id="alertlevelId" onchange="disableByLevel();" >
							<option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Muncipality</option>
							 <option value="6">Village/Ward</option>
							</select>
						</div>
						<div class="col-md-3 stateShowCls" >
							<label>State</label>
							 <select class="dropkickClass" class="stateCls" id="stateId" onChange="getDistrictsForReferPopup();" name="alertVO.stateId">
							 <option value="0">Select State</option>
							 <option value="1">AP</option>
							 <option value="36">TS</option>
							 </select>
						</div>
				   
						<div class="col-md-3 locationsFilterCls distCls">
							 <label>District</label>
							 <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup();" name="alertVO.districtId">
							 <option value="0">Select District</option></select>
						</div>
						<div class="col-md-3 locationsFilterCls constiCls">
							<label>Assembly</label>
							<select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup();" name="alertVO.constituencyId">
							<option value="0">Select Assembly</option>
							</select>
						</div>
						<div class="col-md-3 locationsFilterCls mandalCls">
							<label>Mandal/ Municipality</label>
							 <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" name="alertVO.tehsilId">
								<option value="0">Select Mandal/ Municipality</option>
							 </select>
						</div>
						<div class="col-md-3 locationsFilterCls panchayatCls">
							<label>Panchayat/Ward</label>
							<select class="dropkickClass" id="referpanchayatId" name="alertVO.panchayatId">
							<option value="0">Select Panchayat/Ward</option>
							</select>
						</div>
						
						
						
						<div>
														<div class="advanceCadreCommittee" style="margin-top:5px;" id="referCommitteeDiv">
														 <div class="col-md-3">
															<label>Select Committee</label>
															<select id="referCommitteeId" class="dropkickClass" >
																<option value="0">All</option>
																<option value="1">Main</option><option value="2">Telugu Yuvatha</option>
																<option value="3">Telugu Mahila</option><option value="4">Telugu Rythu</option>
																<option value="17">Trade</option><option value="6">BC Cell</option><option value="7">SC Cell</option>
																<option value="8">ST Cell</option><option value="9">Minority Cell</option><option value="18">Christian</option>
																<option value="11">TNSF (Student Union)</option><option value="5">TNTUC</option><option value="15">TSNV (Technical Expert Cell)</option>
																<option value="10">Legal Cell</option><option value="16">Doctor Cell</option><option value="20">Kallu Geetha Karmikulu</option>
																<option value="21">Chenetha</option><option value="19">Telugu Rakshana Vedika</option><option value="14">TNUS ( Teachers Union) </option>
																<option value="12">Commercial Cell</option><option value="13">Cultural Cell</option>
															</select>
														  </div>
														</div> 
														<div >
															<div class="col-md-6">
																<select id="cadreCommitteeDiv" multiple class="advanceCadreCommittee" style="width:250px !important;"></select>
																<div id="representativesDiv"></div>
																<div id="referRoleErrorDiv"></div>
															</div>
														</div>
													</div></br>
													
													
									
					
					
																<p id="errorDivId" style="color:red;clear:both;margin-left:5px;"></p>
											<div class="col-md-2">
												<button type="button" class="btn btn-block btn-success m_top20 advancedSearchBtn btnNewCustom1" onclick="handleBySearchType();"  style="margin-top: 25px;">Search Member</button>
											</div>
											<div class="col-md-1">
												<img src="images/search.gif" style="display:none;" id="ajaxImgForAppintId"/>
											</div>
												<div style="margin-top: 50px;"><img id="searchMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
											
											<div class="row m_top25">
											<div class="col-md-12" id="clearSerchDivId">
												<div id="apptmemberDetailsDiv"></div>
											</div>
                                        </div>
											</div>
					<!--<div class="row">
						<div class="col-md-6">
							<label>Search Candidates</label>
							<div class="input-group">
								<input type="text" class="form-control" id="candidateNameId"/>
								<span class="input-group-addon" onclick="getCandidateNameDetails()">
									<i class="glyphicon glyphicon-search" ></i>
								</span>
							</div>
						</div>
						
						<img id="ajaxImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none";/>
						<div class="col-md-3">
							<label>Select Candidates</label>
							<select class="dropkickClass"  id="candidatesNameListId" name="alertVO.candidateId">
							<option value="0">Select Candidate</option>	
							</select>
						</div>-->
						<div class="row">
							<div class="col-md-3">
							<label>Alert Type</label>
							<select class="dropkickClass" name="alertVO.alertTypeId" id="alertTypeId">
								<option value="1">Select Alert</option>
							</select>
						</div>
						
						<div class="col-md-3">
							<label>Alert Severity</label>
							<select class="dropkickClass" name="alertVO.severity">
								<option value="1">High</option>
								<option value="2">Medium</option>
								<option value="3">Low</option>
							</select>
						</div>
						<div class="col-md-3">
							<label>Alert Impact</label>
							<select class="dropkickClass"  id="alertImpactId" name="alertVO.alertImpactId">
							<option value="1">Positive </option>	
							<option value="2">Negative </option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<label>Description</label>
							<textarea class="form-control" id="alertdescriptionId" name="alertVO.desc"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3" style="margin-top:10px">
						<input style="font-weight:bold;" class="btn btn-primary" id="addThisalertId" onclick="createAlert();" type="button" value="CREATE ALERT" ></input>
						<!--<button  type="button" class="btn btn-primary">CREATE ALERT</button>-->
						</div>
					</div>
				</div>
				
				<input type="hidden" class="form-control" id="locationLevelValhidden" name="alertVO.locationValue" />
				<input type="hidden" class="form-control" id="locationLevelIdhidden" name="alertVO.locationLevelId" />
				<input type="hidden" class="form-control" value="1" name="alertVO.userTypeId" />
				
				</form>
			
	</div>
                 
</div>


<script type="text/javascript">
$(".dropkickClass").dropkick()
function getCandidatesByName(){
		var  CandidateName=$("#candidateNameId").val();
		var jsObj =
		        {
			CandidateName : CandidateName
		          }
		//$('#ajaxImage').show();
				$.ajax({
					  type:'GET',
					  url: 'getCandidatesByNameAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#candidatesNameListId').append('<option value="0"> Select Candidate </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#candidatesNameListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#candidatesNameListId").dropkick();
							var select1 = new Dropkick("#candidatesNameListId");
							select1.refresh();
					}
				  
				});
		}
		function getCandidateNameDetails()
		{
			
		 $("#errorDiv").html('');
		 var CandidateName=$("#candidateNameId").val();
		 if(CandidateName.trim().length<=3 || CandidateName ==""){
          $("#errorDiv").html('plz enter minimum 3 characters ');
	      return;
         }	
		 getCandidatesByName();
		}
		
function createAlert()
{
  var  alertType=$("#alertTypeId").val();
  var  level=$("#alertlevelId").val();
  var  state=$("#stateId").val();
  var  district=$("#referdistrictId").val();
  var  assembly=$("#referconstituencyId").val();
  var  mandal=$("#refermandalNameId").val();
  var  panchayat=$("#referpanchayatId").val();
  var  candidate=$("#candidatesNameListId").val();
  var  candidateName=$("#candidateNameId").val();
  var  description=$("#alertdescriptionId").val().trim();
   $("#errorDiv1").html('');
  $("#errorDiv1").css("color","red");
  if(alertType==0)
  {
    $("#errorDiv1").html(" Please select Alert Type ");
        return;
  }
  if(level==0)
  {
     $("#errorDiv1").html(" Please select level ");
  }
  
  if(level==2)
  {
    if(state==0)
      {
      $("#errorDiv1").html(" Please select state ");
          return;
    }
	$("#locationLevelIdhidden").val(2);
	$("#locationLevelValhidden").val(state);
    
  }
  if(level==3)
  {
    if(state==0)
    {
      $("#errorDiv1").html(" Please select state ");
          return;
    }
    if(district==0)
    {
      $("#errorDiv1").html(" Please select District ");
          return;
    }
	$("#locationLevelIdhidden").val(3);
	$("#locationLevelValhidden").val(district);
  }
  
 if(level==4)
  {
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
    if(assembly==0)
    {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
    }
	$("#locationLevelIdhidden").val(4);
	$("#locationLevelValhidden").val(assembly);
  }
  if(level==5)
  {
	  var mandalName = $("#refermandalNameId option:selected").text();
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
    if(assembly==0)
     {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
     }
    
    if(mandal==0)
    {
      $("#errorDiv1").html(" Please select Mandal/ Municipality ");
          return;
    }
	$("#locationLevelValhidden").val(mandal);
		if(mandalName.indexOf('Mandal') == -1)
		$("#locationLevelIdhidden").val(7);
	else
		$("#locationLevelIdhidden").val(5);
		
  }
  if(level==6)
  {
	   var panchayatName = $("#referpanchayatId option:selected").text();
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
      if(assembly==0)
     {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
     }
    
      if(mandal==0)
     {
      $("#errorDiv1").html(" Please select Mandal/ Municipality ");
          return;
     }
     if(panchayat==0)
     {
    $("#errorDiv1").html(" Please select Panchayat ");
        return;
     }
	 $("#locationLevelValhidden").val(panchayat);
		 if(panchayatName.indexOf('WARD') == -1)
			$("#locationLevelIdhidden").val(6);
		else
			$("#locationLevelIdhidden").val(8);
    }
  if(candidate==0)
  {
    $("#errorDiv1").html(" Please select Candidate ");
        return;
  }
 
   if(description.length==0 ||description=='')
  {
    $("#errorDiv1").html(" Please select description ");
        return;
  }

var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					if(uploadResult.indexOf("success") !=-1)
					{
					 $("#errorDiv1").html(" Alert Created Successfully ").css("color","green");	
					}
					return false;
				}
			};

		YAHOO.util.Connect.setForm('saveAlertForm',true);
		YAHOO.util.Connect.asyncRequest('POST','saveAlertAction.action',uploadHandler);
}
function getAlertType(){
		$("#alertTypeId").html('');
		var jsObj =
		        {
			task:""
		          }
				$.ajax({
					  type:'GET',
					  url: 'getAlertTypeAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#alertTypeId').append('<option value="0"> Select Alert Type </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#alertTypeId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#alertTypeId").dropkick();
							var select1 = new Dropkick("#alertTypeId");
							select1.refresh();
					}
				  
				});
		}
		
		
		
//buildLevels();

getAlertType();
//showHideSearch("advanceSearch");
//showHideBySearchType();

</script>
