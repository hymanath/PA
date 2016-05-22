<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Blood Bank Registration</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/BloodBankCustomFiles/custom.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css"/>
<link href="dist/DateRange/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,300,500italic,400italic,300italic,700,900" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
        	<div class="block">
            	<div class="row">
                	<div class="col-md-1 col-xs-1">
                  	  <img src="dist/BloodBankCustomFiles/logo.png" class="img-responsive" alt="Logo"/>
                    </div>
                    <div class="col-md-11 col-xs-11">
                    	<h3 class="text-capitalise">ntr trust blood bank</h3>
                        <p class="text-capitalise">ntr trust bhavan, road no-2, banjara hills, hyderabad - 34</p>
                        <p>Tel: 040 30799999 | E-Mail:hydbb@ntrtrust.org | Lic No: 01/HD/AP/2008/BB/CP</p>
                        
                    </div>
                </div>
                <div class="row">
                	<div class="col-md-12">
                    	<hr style="border-color:#333;"/>
                    </div>
                    <div class="col-md-6">
                    	<label>Enter Cadre Number To Prepopulate Details<span class="cadreNoErrorCls" style='color:red'></span></label>
                        <div class="input-group inputWithButton">
                        	<input placeholder="Ex: 38324292" type="text" class="form-control" id="membershipInputId"/>
                            <span class="input-group-addon">
                            	<button class="btn btn-success" type="button" id="cadreDetailsId">POPULATE DETAILS</button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m_top20">
                    	<h4>SHOWING DETAILS</h4>
                    </div>
                </div>
                <div class="row m_top20">
                    <div class="col-md-3">
                    	<label>Name</label>
                        <input type="text" id="nameId" class="form-control"/>
                    </div>
                    <div class="col-md-3">
                    	<label>Father/Spouse</label>
                        <input type="text" id="spouseId" class="form-control"/>
                    </div>
                    <div class="col-md-6">
                    	<div class="row">
                        	<div class="col-md-3">
                            	<label>Sex</label>
 		                        <select id="seltBxSxId" class="form-control">
                                	<option value="M">Male</option>
									<option value="F">FeMale</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                            	<label>Age</label>
 		                        <input type="number" id="ageId" class="form-control"/>
                            </div>
                            <div class="col-md-3">
                            	<label>Date Of Birth</label>
 		                        <input type="text" id="dobId" class="form-control"/>
                            </div>
                            <div class="col-md-3">
                            	<label>Married</label>
 		                        <select id="slctBxMrrdId" class="form-control">
                                	<option value="Married">Married</option>
									<option value="UnMarried">UnMarried</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                    	<label>Address</label>
                        <textarea class="form-control" id="addressId" rows="4"></textarea>
                    </div>
                    <div class="col-md-6">
                    	<div class="row">
                        	<div class="col-md-6">
                            	<label>Mobile</label>
                                <input type="tel" id="mobileNoId" class="form-control"/>
                            </div>
                            <div class="col-md-6">
                            	<label>E-Mail</label>
                                <input type="tel" id="emailId" class="form-control"/>
                            </div>
                            <div class="col-md-6">
                            	<label>Education</label>
                                <select class="selectBoxCls form-control" id="edutnQlfctnSlctBxId">
                                	<option value="0">Select Education</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                            	<label>Occupation</label>
                                <select class="selectBoxCls form-control" id="occptnSlctBxId">
                                	<option value="0">Select Occupation</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row m_top10">
                    <div class="col-md-3">
                    	<label>No.of times Blood Donation in BB</label>
                        <input type="number" id="noOfTmBldDntnId" class="form-control"/>
                    </div>
                    <div class="col-md-3">
                    	<label>Other than BB</label>
                    	<select id="slctBxBBId" class="form-control">
                        	 <option value="Yes">Yes</option>
							 <option value="No">No</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Date Of Last Donation</label>
                    	<input type="text" id="dtfDntnId" class="form-control"/>
                    </div>
                    <div class="col-md-3">
                    	<label>Donation</label>
                   	<select id="donationId"  class="form-control">
                        	 <option value="0">Select Donation Type</option>
					    </select>
                    </div>
                </div>
                <div class="row m_top10">
                    <div class="col-md-3">
                    	<label>Willing to Donate in Emergency</label>
                    	<select id="wllngTdntEmrgncyId"  class="form-control">
                        	 <option value="Yes">Yes</option>
							 <option value="No">No</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Willing to be Called for Donation</label>
                    	<select id="wllngTBClldFrDntnId"  class="form-control">
                        	 <option value="Yes">Yes</option>
							 <option value="No">No</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Remarks</label>
                    	<input type="text" id="rmrksId" class="form-control"/>
                    </div>
                    <!--<div class="col-md-3">
                    	<label>Category</label>
                    	<select id="slctBxCtgryId" class="form-control">
                        	<option value="Yes">Yes</option>
							 <option value="No">No</option>
                        </select>
                    </div>-->
                </div>
                <div class="row">
                    <div class="col-md-1 m_top10">
                    	<button class="btn btn-success" id="submitBtnId">SUBMIT</button>
					     	
                    </div>
					<div class="col-xs-3  m_top10">
					<span id="statusId"></span>
					</div>
                </div>
                <div class="row m_top20">
                	<div class="col-md-12">
                    	<div class="panel panel-default">
                        	<div class="panel-heading bg_cc">
                            	<h4 class="panel-title">HISTORY OF HEALTH</h4>
                            </div>
                            <div class="panel-body pad_0">
                            	<table class="table tableHealthHistory">
                                    <tr>
                                        <td>Heart Disease/Cadriovascular Diseases
                                        </td>
                                        <td>Yes</td>
                                        <td>No</td>
                                        <td>Addiction to Alcohol/Drugs/Aspirin</td>
                                        <td>Yes</td>
                                        <td>No</td>
                                    </tr>
                                    <tr>
                                        <td>Heart Disease/Cadriovascular Diseases
                                        </td>
                                        <td>Yes</td>
                                        <td>No</td>
                                        <td>Addiction to Alcohol/Drugs/Aspirin</td>
                                        <td>Yes</td>
                                        <td>No</td>
                                    </tr>
                                    <tr>
                                        <td>Heart Disease/Cadriovascular Diseases
                                        </td>
                                        <td>Yes</td>
                                        <td>No</td>
                                        <td>Addiction to Alcohol/Drugs/Aspirin</td>
                                        <td>Yes</td>
                                        <td>No</td>
                                    </tr>
                                    <tr>
                                        <td>Heart Disease/Cadriovascular Diseases
                                        </td>
                                        <td>Yes</td>
                                        <td>No</td>
                                        <td>Addiction to Alcohol/Drugs/Aspirin</td>
                                        <td>Yes</td>
                                        <td>No</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                	<div class="col-md-12">
                    	<div class="panel panel-default">
                        	<div class="panel-heading bg_cc">
                            	<h4 class="panel-title">MEDICAL CHECKUP</h4>
                            </div>
                            <div class="panel-body">
                            	<div class="row">
                                	<div class="col-md-2">
                                    	<label>Temp</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-2">
                                    	<label>HB</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-2">
                                    	<label>BP</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-3">
                                    	<label>Pulse</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-3">
                                    	<label>Weight</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">Kgs(&gt;45 Kgs)</span>
                                    </div>
                                    <div class="col-md-6">
                                    	<label>Reasons for Rejecting Doctor</label>
                                        <textarea class="form-control"></textarea>
                                    </div>
                                    <div class="col-md-6">
                                    	<label>Post Phlebotomy Reaction</label>
                                        <textarea class="form-control"></textarea>
                                    </div>
                                    <div class="col-md-3 m_top10">
                                    	<textarea class="form-control" disabled></textarea>
                                        <p>Signature Of Medical Officer</p>
                                        <p>Date:___________________</p>
                                    </div>
                                    <div class="col-md-3 col-md-offset-1 m_top10">
                                    	<textarea class="form-control" disabled></textarea>
                                        <p>Signature Of Phlebotomy</p>
                                        <p>Date:___________________</p>
                                    </div>
                                    <div class="col-md-3 m_top10">
                                    	<textarea class="form-control" disabled></textarea>
                                        <p>Signature Of Donor</p>
                                        <p>Date:___________________</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr/>
                <h4 class="text-capitalise">Donor Consent</h4>
                <ul>
                	<li>I Have Accurately answered all questions and voluntary willing to donate 350/450 ml of blood</li>
                    <li>I understand that my blood will be tested for evidence of infections including HIV(AIDS),HBsAg,HCV and Syphilis</li>
                    <li>I understand that test results will be made known to health authorities only. I would like to be informed in case of Sero-Positive result.</li>
                </ul>
 	        </div>
        </div>
    </div>
</div>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>
<div class="modal fade dataSubmittingModal" id="myModal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-body">
        <p class="text-capitalise">details are submitting</p>
        <p>dou want to get print</p>
        <button type="button" class="m_top20 btn btn-primary">PRINT</button>
        <button type="button" class="btn btn-default m_top20" data-dismiss="modal">CANCEL</button>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript">
$("#dobId").daterangepicker({singleDatePicker:true});
$("#dtfDntnId").daterangepicker({singleDatePicker:true});
//$('select').dropkick();
</script>
<script type="text/javascript">
$(document).ready(function(){
	 
});
	 getOccuations();
	 getBloodComponents();
	 getEducationQualifications();
function getOccuations(){
	$.ajax({
		  type:'GET',
		  url: 'getOccuationsAction.action',
		  data : {} ,
		}).done(function(result){
			 if(result != null && result.length>0){
				  for(var i in result){
					$("#occptnSlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				  }	
			}
		});
}
function getEducationQualifications(){
	
		$.ajax({
		  type:'GET',
		  url: 'getEducationQualificationsAction.action',
		  data : {} ,
		}).done(function(result){
			if(result != null && result.length>0){
				  for(var i in result){
					$("#edutnQlfctnSlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				  }	
			}
		});
}
 function getBloodComponents(){
	   var jObj = {
		 }
		$.ajax({
		  type:'GET',
		  url: 'getBloodComponentsAction.action',
		  data : {} ,
		}).done(function(result){
				if(result != null && result.length>0){
				  for(var i in result){
					$("#donationId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				  }	
			}
		});
}
$(document).on("click","#cadreDetailsId",function(){
	
	
	var membserShipId = $("#membershipInputId").val();
	 if(membserShipId==null || membserShipId==undefined || membserShipId.trim().length==0){
		 $(".cadreNoErrorCls").html("Please Enter Cadre Number.");
		 return;
	 }else if(membserShipId.trim().length!=8){
		  $(".cadreNoErrorCls").html("Cadre Number Must Be 8 Digits.");
		  return;
	 }
	 $(".cadreNoErrorCls").html('');
	 getCadreDetails(membserShipId);
});

 function getCadreDetails(membserShipId){
	   
	   
	   var jObj = {
		   memberShipNo:membserShipId
		}
		$.ajax({
		  type:'GET',
		  url: 'getCadreDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			if(result != null){
				populateCadreDetails(result);
			}
		});
}
function populateCadreDetails(result){
	if(result.alreadyDonated!=null && result.alreadyDonated==false){
		populateCommonFields(result);
	}else if(result.alreadyDonated!=null && result.alreadyDonated==true){
		 populateCommonFields(result);
		 if(result.donationsInBloodBank!=null && result.donationsInBloodBank>0){
			$("#noOfTmBldDntnId").val(result.donationsInBloodBank);
		}
		if(result.donationsInOtherPlaces!=null && result.donationsInOtherPlaces.length>0){
			$("#slctBxBBId").val(result.donationsInOtherPlaces);
		}
		if(result.lastDonation!=null && result.lastDonation.length>0){
			$("#dtfDntnId").val(result.lastDonation);
		}
		if(result.bloodComponentId!=null && result.bloodComponentId>0){
			$("#donationId").val(result.bloodComponentId);
		}
		 if(result.willingEmergencyDonation!=null && result.willingEmergencyDonation.length>0){
			$("#wllngTdntEmrgncyId").val(result.willingEmergencyDonation);
		}
		if(result.willingToCallDonation!=null && result.willingToCallDonation.length>0){
			$("#wllngTBClldFrDntnId").val(result.willingToCallDonation);
		}
		if(result.remarks!=null && result.remarks.length>0){
			$("#rmrksId").val(result.remarks);
		}   
	}
}
function populateCommonFields(result){
	if(result.name!=null && result.name.length>0){
			$("#nameId").val(result.name);
		}
		if(result.relativeName!=null && result.relativeName.length>0){
			$("#spouseId").val(result.relativeName);
		}
		if(result.sex !=null && result.sex.length>0){
			$("#seltBxSxId").val(result.sex);
		}
		if(result.age!=null && result.age>0){
			$("#ageId").val(result.age);
		}
		if(result.dob!=null && result.dob.length>0){
			$("#dobId").val(result.dob);
		}
		if(result.address!=null && result.address.length>0){
			$("#addressId").val(result.address);
		}
		if(result.mobile!=null && result.mobile.length>0){
			$("#mobileNoId").val(result.mobile);
		}
		if(result.email!=null && result.email.length>0){
			$("#emailId").val(result.email);
		}
		if(result.educationId!=null && result.educationId>0){
			$("#edutnQlfctnSlctBxId").val(result.educationId);
	    }
		if(result.occupationId!=null && result.occupationId>0){
			$("#occptnSlctBxId").val(result.occupationId);
		}
}
$(document).on("click","#submitBtnId",function(){
	
		var membserShipId = $("#membershipInputId").val();
		 if(membserShipId==null || membserShipId==undefined || membserShipId.trim().length==0){
			 $(".cadreNoErrorCls").html("Please Enter Cadre Number.");
		     return;
		 }
        var cadreDtlsArr=[];
		  cadreDtlsArr.push($("#nameId").val());
		  cadreDtlsArr.push($("#spouseId").val());
		  cadreDtlsArr.push($("#seltBxSxId").val());
		  cadreDtlsArr.push($("#ageId").val());
		  cadreDtlsArr.push($("#dobId").val());
		  cadreDtlsArr.push($("#slctBxMrrdId").val());
		  cadreDtlsArr.push($("#addressId").val());
		  cadreDtlsArr.push($("#mobileNoId").val());
		  cadreDtlsArr.push($("#emailId").val());
		  cadreDtlsArr.push($("#edutnQlfctnSlctBxId").val());
		  cadreDtlsArr.push($("#occptnSlctBxId").val());
		  cadreDtlsArr.push($("#noOfTmBldDntnId").val());
		  cadreDtlsArr.push($("#slctBxBBId").val());
		  cadreDtlsArr.push($("#dtfDntnId").val());
		  cadreDtlsArr.push($("#donationId").val());
		  cadreDtlsArr.push($("#wllngTdntEmrgncyId").val());
		  cadreDtlsArr.push($("#wllngTBClldFrDntnId").val());
		  cadreDtlsArr.push($("#rmrksId").val());
		  cadreDtlsArr.push(membserShipId);
	   var jObj = {
		   cadreDtlsArr:cadreDtlsArr
		}
		$.ajax({
		  type:'POST',
		  url: 'saveCadreDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			if(result!=null){
				if(result.exceptionMsg=="success"){
					 $("#statusId").html("<p style='color:green;font-size:22px;'>Saved Successfully</p>'");
					 setTimeout(function(){
					 $("#statusId").html("");
					},2000);
				}else{
					 $("#statusId").html("<p  style='color:red;font-size:22px;'>Error occured...Try again</p>'");
				}
			}
		});
});
</script>

</body>
</html>
