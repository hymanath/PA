<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Blood Bank Registration</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/BloodBankCustomFiles/custom.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css"/>
<!--<link href="dist/DateRange/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>-->
<link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,300,500italic,400italic,300italic,700,900" rel="stylesheet" type="text/css">
<style>
.mandatoryFieldCls
{
 color:red;	
}
</style>
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
                    	<label>Name<span class="mandatoryFieldCls">*</span></label><span class="nameErrorCls mandatoryFieldCls"></span>
                        <input type="text" id="nameId" class="form-control clearFieldCls" readonly />
				    </div>
                    <div class="col-md-3">
                    	<label>Father/Spouse</label>
                        <input type="text" id="spouseId" class="form-control clearFieldCls"/>
                    </div>
                    <div class="col-md-6">
                    	<div class="row">
                        	<div class="col-md-3">
                            	<label>Sex<span class="mandatoryFieldCls">*</span></label>
 		                        <select id="seltBxSxId" class="form-control">
                                	<option value="M">Male</option>
									<option value="F">Female</option>
                                </select>
                            </div>
							<div class="col-md-3">
                            	<label>Date Of Birth<!--<span class="mandatoryFieldCls">*</span>--></label>
 		                        <input type="text" id="dobId" class="form-control clearFieldCls"/>
								<span class="dobErrorCls mandatoryFieldCls"></span>
                            </div>
                            <div class="col-md-3">
                            	<label>Age<span class="mandatoryFieldCls">*</span></label>
 		                        <input type="number" id="ageId" class="form-control clearFieldCls"/>
								<span class="ageErrorCls mandatoryFieldCls"></span>
                            </div>
                            <div class="col-md-3">
                            	<label>Married<!--<span class="mandatoryFieldCls">*</span>--></label>
 		                        <select id="slctBxMrrdId" class="form-control">
									<option value="0">Select Status</option>
									<option value="Single">Single</option>
                                	<option value="Married">Married</option>	
								</select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 m_top10">
                    	<label>Address</label>
                        <textarea class="form-control clearFieldCls" id="addressId" rows="4"></textarea>
                    </div>
                    <div class="col-md-6 m_top10">
                    	<div class="row">
                        	<div class="col-md-6">
                            	<label>Mobile<span style='color:red'>*</span></label><span class="mobileNoErrorCls mandatoryFieldCls"></span>
                                <input type="tel" id="mobileNoId" class="form-control clearFieldCls"/>
                            </div>
                            <div class="col-md-6">
                            	<label>E-Mail</label>
                                <input type="tel" id="emailId" class="form-control clearFieldCls"/>
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
                        <input type="number" id="noOfTmBldDntnId" class="form-control clearFieldCls"/>
                    </div>
                    <div class="col-md-3">
                    	<label>Other than BB</label>
                    	<select id="slctBxBBId" class="form-control">                        	
							 <option value="No">No</option>
							 <option value="Yes">Yes</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Date Of Last Donation</label>
                    	<input type="text" id="dtfDntnId" class="form-control clearFieldCls"/>
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
							 <option value="No">No</option>
							 <option value="Yes">Yes</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Willing to be Called for Donation</label>
                    	<select id="wllngTBClldFrDntnId"  class="form-control">
							 <option value="No">No</option>
                        	 <option value="Yes">Yes</option>							 
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Remarks</label>
                    	<input type="text" id="rmrksId" class="form-control clearFieldCls"/>
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
                    <div class="col-md-3 m_top10">
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
										<td>Heart Disease/Cardiovascular Diseases
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Addiction to Alcohol / Drugs / Aspirin</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									<tr>
										<td>Fits of Epilepsy	
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Signs suggestive of AIDS / Unexpected weight loss</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									<tr>
										<td>Tuberculosis / Typhoid / malaria
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Evidence of Polycythemia Vera / bleeding disorders</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									<tr>
										<td>Kidney Problem	
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Evidence of Hepatitis B infection / Jaundice</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									<tr>
										<td>Diabetes controlled on Insulin
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Evidence of Leprosy infection</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									<tr>
										<td>Any surgeries / blood transfusions in the last 1 year
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Evidence of Schizophrenia</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									<tr>
										<td>Skin Rash / Tattoos
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Any Vaccination / immunization in last 1 year</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									<tr>
										<td>Bronchial Asthma
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Have taken antibiotics in last one month</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									<tr>
										<td>Liver Disease / Dialysis/Rabies
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Women:</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td>Evidence of Cancer/Malignant disease
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Pregnancy / Lactation / Abortion</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td>Immunoglobulin / endocrine
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Periods / 10 Days</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td>Disorders/chronic nephritis
										</td>
										<td>Yes</td>
										<td>No</td>
										<td>Any Gynecological Disorders</td>
										<td></td>
										<td></td>
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
                                        <input class="form-control" type="text" readonly />
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-2">
                                    	<label>HB</label>
                                        <input class="form-control" type="text" readonly />
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-2">
                                    	<label>BP</label>
                                        <input class="form-control" type="text" readonly />
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-3">
                                    	<label>Pulse</label>
                                        <input class="form-control" type="text" readonly />
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-3">
                                    	<label>Weight</label>
                                        <input class="form-control" type="text" readonly />
                                        <span class="textItalic pull-right">Kgs(&gt;45 Kgs)</span>
                                    </div>
                                    <div class="col-md-6">
                                    	<label>Reasons for Rejecting Doctor</label>
                                        <textarea class="form-control" readonly ></textarea>
                                    </div>
                                    <div class="col-md-6">
                                    	<label>Post Phlebotomy Reaction</label>
                                        <textarea class="form-control" readonly ></textarea>
                                    </div>
                                    <div class="col-md-3 m_top30">
                                    	<textarea class="form-control" disabled></textarea>
                                        <p>Signature Of Medical Officer</p>
                                        <p>Date:___________________</p>
                                    </div>
                                    <div class="col-md-3 col-md-offset-1 m_top30">
                                    	<textarea class="form-control" disabled></textarea>
                                        <p>Signature Of Phlebotomy</p>
                                        <p>Date:___________________</p>
                                    </div>
                                    <div class="col-md-3 m_top30">
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
<div class="modal fade myModalprintView " id="" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-body" id="">
		<div class="row">
			<div class="col-md-12">
				<p>Details are Submitting</p>
				<p class="dtsCls">dou want to get print</p>
			</div>	
			<div class="col-md-12">
				<!--<button type="button" class="m_top20 btn btn-primary printCls" id="printBloddBankId" >PRINT</button>-->
				
				<button type="button" class="btn btn-success m_top20" id="printpriewBtn">Print Preview</button>	
				<button type="button" class="btn btn-default m_top20" data-dismiss="modal">CANCEL</button>
			</div>
		</div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</div>
<div class="modal fade dataSubmittingModal" id="myModal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-body" id="printarea">
		<div class="row">
			<div class="col-md-1 col-xs-1">
			  <img src="dist/BloodBankCustomFiles/logo.png" class="img-responsive" alt="Logo"/>
			</div>
			<div class="col-md-11 col-xs-11">
				<h3 class="text-capitalise">ntr trust blood bank</h3>
				<p class="text-capitalise">ntr trust bhavan, road no-2, banjara hills, hyderabad - 34</p>
				<p>Tel: 040 30799999 | E-Mail:hydbb@ntrtrust.org | Lic No: 01/HD/AP/2008/BB/CP</p>
			</div>
			<div class="col-md-12">
				<hr/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<ul class="printDetails">
					<li>Name: <span class='' id="printnameId"></span></li>
					<li>Age: <span class='' id="printageId"></span></li>
				<!--<li>No Of times blood donated in Blood Bank: <span class='' id="printnoOfTmBldDntnId"></span></li>
					<li>Other than Blood Bank: <span class='' id="printslctBxBBId"></span></li>-->
					<li>Donor Registration Number: ___________<span class='' id=""></span></li>
					<li>Membership No: <span class='' id="printmembershipInputId"></span></li>
					<li>Date Of Birth: <span class=''id="printdobId"></span></li>
					<li>Segment Number:______________ <span class='' id=""></span></li>
					<li>Mobile No: <span class='' id="printmobileNoId"></span></li>
					<li>Sex: <span class='' id="printseltBxSxId"></span></li>
					<li>Bag Type:___________ <span class='' id=""></span></li>
					<li>Father/ Spouse: <span class='' id="printspouseId"></span></li>
			 </ul>
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
								<td>Heart Disease/Cardiovascular Diseases
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Addiction to Alcohol / Drugs / Aspirin</td>
								<td>Yes</td>
								<td>No</td>
							</tr>
							<tr>
								<td>Fits of Epilepsy	
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Signs suggestive of AIDS / Unexpected weight loss</td>
								<td>Yes</td>
								<td>No</td>
							</tr>
							<tr>
								<td>Tuberculosis / Typhoid / malaria
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Evidence of Polycythemia Vera / bleeding disorders</td>
								<td>Yes</td>
								<td>No</td>
							</tr>
							<tr>
								<td>Kidney Problem	
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Evidence of Hepatitis B infection / Jaundice</td>
								<td>Yes</td>
								<td>No</td>
							</tr>
							<tr>
								<td>Diabetes controlled on Insulin
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Evidence of Leprosy infection</td>
								<td>Yes</td>
								<td>No</td>
							</tr>
							<tr>
								<td>Any surgeries / blood transfusions in the last 1 year
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Evidence of Schizophrenia</td>
								<td>Yes</td>
								<td>No</td>
							</tr>
							<tr>
								<td>Skin Rash / Tattoos
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Any Vaccination / immunization in last 1 year</td>
								<td>Yes</td>
								<td>No</td>
							</tr>
							<tr>
								<td>Bronchial Asthma
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Have taken antibiotics in last one month</td>
								<td>Yes</td>
								<td>No</td>
							</tr>
							<tr>
								<td>Liver Disease / Dialysis/Rabies
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Women:</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>Evidence of Cancer/Malignant disease
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Pregnancy / Lactation / Abortion</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>Immunoglobulin / endocrine
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Periods / 10 Days</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>Disorders/chronic nephritis
								</td>
								<td>Yes</td>
								<td>No</td>
								<td>Any Gynecological Disorders</td>
								<td></td>
								<td></td>
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
							<div class="col-md-12">
								<table class="table">
									<tr>
										<td>
											<label>Temp</label>
											<input class="form-control" type="text" readonly />
											<span class="textItalic pull-right">0C(98.40F)</span>
										</td>
										<td>
											<label>HB</label>
											<input class="form-control" type="text" readonly />
											<span class="textItalic pull-right">0C(98.40F)</span>
										</td>
										<td>
											<label>BP</label>
											<input class="form-control" type="text" readonly />
											<span class="textItalic pull-right">0C(98.40F)</span>
										</td>
										<td>
											<label>Pulse</label>
											<input class="form-control" type="text" readonly />
											<span class="textItalic pull-right">0C(98.40F)</span>
										</td>
										<td>
											<label>Weight</label>
											<input class="form-control" type="text" readonly />
											<span class="textItalic pull-right">Kgs(&gt;45 Kgs)</span>
										</td>
									</tr>
								</table>
								<table class="table">
									<tr>
										<td>
											<label>Reasons for Rejecting Doctor</label>
											<textarea class="form-control" rows='3' readonly ></textarea>
										</td>
										<td>
											<label>Post Phlebotomy Reaction</label>
											<textarea class="form-control" rows='3' readonly ></textarea>
										</td>
									</tr>
								</table>
								<table class="table tableSignaturePrint m_top30" >
									<tr>
										<td>
											<p>Signature Of Medical Officer</p>
											<p>Date:___________________</p>
										</td>
										<td>
											<p>Signature Of Phlebotomy</p>
											<p>Date:___________________</p>
										</td>
										<td>
											<p>Signature Of Donor</p>
											<p>Date:___________________</p>
										</td>
									</tr>
								</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<div class="col-md-12">
			<h4 class="text-capitalise">Donor Consent</h4>
			<ul>
				<li>I Have Accurately answered all questions and voluntary willing to donate 350/450 ml of blood</li>
				<li>I understand that my blood will be tested for evidence of infections including HIV(AIDS),HBsAg,HCV and Syphilis</li>
				<li>I understand that test results will be made known to health authorities only. I would like to be informed in case of Sero-Positive result.</li>
			</ul>

			
			<button type="button" class="m_top20 btn btn-primary printCls" id="printBloddBankId" >PRINT</button>
			<button type="button" class="btn btn-default m_top20" id="printBloddBankCancelId" data-dismiss="modal">CANCEL</button>
		</div>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<!--<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>-->
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript">



//$("#dobId").daterangepicker({singleDatePicker:true});
$("#dobId").datetimepicker({format:'MM/DD/YYYY'});
$("#dtfDntnId").datetimepicker({format:'MM/DD/YYYY'});
//$("#dtfDntnId").daterangepicker({singleDatePicker:true});
$("#dtfDntnId").val(" ");
$("#dobId").val(" ");
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

function updatePrintStatus(id){
		var jobj = {
		bloodBankId : id
	}
	$.ajax({
		  type:'GET',
		  url: 'updatePrintstatusAction.action',
		   data : {task:JSON.stringify(jobj)} ,
		}).done(function(result){
			/* if(result != null && result.length>0){
				  for(var i in result){
					$("#occptnSlctBxId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				  }	
			}*/
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
	 //clearing error fields
	 $(".cadreNoErrorCls").html('');
	 $(".nameErrorCls").html(' ');
     $(".ageErrorCls").html(' ');
     $(".mobileNoErrorCls").html(''); 
     $(".dobErrorCls").html(' ');
	 
	 getCadreDetails(membserShipId);
});
$(document).on("click",".printCls",function(){
	 window.print();
	 var value = $(this).attr("value");
	 updatePrintStatus(value);
})
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
				if(result.status!=null && result.status=="not exist"){
				 $(".cadreNoErrorCls").html("This cadre number not available.");
				}else{
				 populateCadreDetails(result);
				}
			}
		});
}
function populateCadreDetails(result){
	   //clearing fields 
		clearFields();
		
	if(result.alreadyDonated!=null && result.alreadyDonated==false){
		populateCommonFields(result);
	}else if(result.alreadyDonated!=null && result.alreadyDonated==true){
		 populateCommonFields(result);
		 
		 if(result.married!=null && result.married.length>0){
			 $("#slctBxMrrdId").val(result.married);
		 }else{
			$("#slctBxMrrdId").val(0);
		 }
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

$(document).on("click","#printpriewBtn",function(){
	$(".myModalprintView").modal("hide");
	setTimeout(function(){
		$("#myModal").modal("show");
	},500)
	
});
function validateFields(){
   
   var name=$("#nameId").val();
     var mobileNo=$("#mobileNoId").val();
     var dob=$("#dobId").val();
	 var membserShipId = $("#membershipInputId").val();
	 
	 if(membserShipId==null || membserShipId==undefined || membserShipId.trim().length==0){
			 $(".cadreNoErrorCls").html("Please Enter Cadre Number.");
		     return;
		 }
	 $(".cadreNoErrorCls").html(" ");
	if(name==null || name==undefined || name.trim().length==0){
	   $(".nameErrorCls").html("Please Enter Name.");
      return	 
    }
    $(".nameErrorCls").html('');
 /*   if(dob==null || dob ==undefined || dob.trim().length==0){
	   $(".dobErrorCls").html("Please Enter DOB.");
	   return;	  
   } */
	 //$(".dobErrorCls").html(' ');
	 if(dob !=null && dob !=undefined && dob.trim() !=""){
		 var dobArr=dob.split("/");	
		  var year=(new Date().getFullYear())-(dobArr[2]);
		  $("#ageId").val(year);
	 }	
	 var age=$("#ageId").val();
	if(age==null && age==undefined || age.trim().length==0){
	  $(".ageErrorCls").html('Please enter age.');
       return;	  
	}
	$(".ageErrorCls").html(' ');
   if(mobileNo==null || mobileNo==undefined || mobileNo.trim().length==0){
	   $(".mobileNoErrorCls").html("Please enter mobile number"); 
	   return;
   }
    var mobileValidateDigits= /^\d{10}$/;
	 if(!mobileValidateDigits.test(mobileNo)) {
		 $(".mobileNoErrorCls").html("Mobile Number Must be 10 digits");  
		  return; 
	 }
	 $(".mobileNoErrorCls").html(''); 
	 return true;
}
 $(document).on("blur","#dobId",function(){
	var dob=$("#dobId").val();
	if(dob!=null && dob!=undefined && dob.trim() !=""){		
	 var dobArr=dob.split("/");	
	 var year=(new Date().getFullYear())-(dobArr[2]);
	 $("#ageId").val(year);
	}
}); 
$(document).on("click","#submitBtnId",function(){
	var status=validateFields();
	  if(status!=null && status!=undefined && status==true){
		printdocumentDetails();
	  }
})
function printdocumentDetails(){
	
	
	$("#printpriewBtn").show();
	$("#printBloddBankId").removeAttr("value");
	//$("#printBloddBankCancelId").removeAttr("value");
	
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
		  cadreDtlsArr.push( $("#membershipInputId").val());
		 
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
		//$("#printpriewBtn").show();
			$("#printnameId").html(''+$("#nameId").val()+'');
			$("#printageId").html(''+$("#ageId").val()+'');
			$("#printnoOfTmBldDntnId").html(''+$("#noOfTmBldDntnId").val()+'');
			$("#printmembershipInputId").html(''+$("#membershipInputId").val()+'');
			$("#printdobId").html(''+$("#dobId").val()+'');
			$("#printseltBxSxId").html(''+$("#seltBxSxId").val()+'');
			$("#printspouseId").html(''+$("#spouseId").val()+'');
			$("#printmobileNoId").html(''+$("#mobileNoId").val()+'');
			$("#printslctBxBBId").html(''+$("#slctBxBBId").val()+'');
		 
		// $("#printaddressId").html(''+$("#addressId").val()+'');
		 // 
		// $("#printemailId").html(''+$("#emailId").val()+'');
		// $("#printedutnQlfctnSlctBxId").html(''+$("#edutnQlfctnSlctBxId").val()+'');
		 // $("#printoccptnSlctBxId").html(''+$("#occptnSlctBxId").val()+'');
		
		 //  $("#printslctBxBBId").html(''+$("#slctBxBBId").val()+'');
		//  $("#printdtfDntnId").html(''+$("#dtfDntnId").val()+'');
		// $("#printdonationId").html(''+$("#donationId").val()+'');
		//  $("#printwllngTdntEmrgncyId").html(''+$("#wllngTdntEmrgncyId").val()+'');
		  //$("#printwllngTBClldFrDntnId").html(''+$("#wllngTBClldFrDntnId").val()+'');
		 //  $("#printrmrksId").html(''+$("#rmrksId").val()+'');
			
					$("#printBloddBankId").attr("value",''+result.resultState+'');
					 $("#statusId").html("<p style='color:green;font-size:15px;'>Saved Successfully</p>'");
					 setTimeout(function(){
					 $("#statusId").html("");
					},2000);
					$(".myModalprintView").modal("show");
					//clearing fields
					 clearFields();
					 $("#membershipInputId").val(' ');
					 
				}else if(result.exceptionMsg=="exist"){
					 	$("#statusId").html("<p  style='color:red;font-size:15px;'>You have already donated blood.Please don't try.</p>'");
				}else{
					$("#statusId").html("<p  style='color:red;font-size:15px;'>Error occured...Try again</p>'");
				}
			}
		});
}
function clearFields(){
	$(".clearFieldCls").val(' ');
	$("#seltBxSxId").val("M");
	$("#slctBxMrrdId").val(0);
	$("#edutnQlfctnSlctBxId").val(0);
	$("#occptnSlctBxId").val(0);
	$("#donationId").val(0);
	$("#slctBxBBId").val("No");
	$("#wllngTdntEmrgncyId").val("No");
	$("#wllngTBClldFrDntnId").val("No");
	
}
</script>

</body>
</html>
