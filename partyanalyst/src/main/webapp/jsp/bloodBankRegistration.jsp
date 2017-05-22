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
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

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
                    	<h3 class="text-capitalise"> ntr trust blood bank</h3>
                        <p class="text-capitalise">  NTR Trust Blood Bank,  D. No. 10-50-58/1, Raghavendra Towers,</p>
                         <p class="text-capitalise"> Waltair, Waltair main road, Visakhapatnam-530002 . </p>
                        <p>Tel: 0891 2565858, 2555858 | E-Mail: info@ntrtrust.org | Lic No: 03/VSP/AP/2013/BB/G </p>
                    </div>
                </div>
                <div class="row">
                	<div class="col-md-12">
                    	<hr style="border-color:#333;"/>
                    </div>
					<button class="btn btn-warning pull-right interestedPeopleId " style="margin-right:15px" type="button" id="interestedPeopleId">INTERESTED PEOPLE</button>
                  <!-- <div class="col-md-6">
                    	<label>Enter Cadre Number To Prepopulate Details<span class="cadreNoErrorCls" style='color:red'></span></label>
                        <div class="input-group inputWithButton">
                        	<input placeholder="Ex: 38324292" type="text" class="form-control" id="membershipInputId"/>
                            <span class="input-group-addon">
                            	<button class="btn btn-success" type="button" id="cadreDetailsId">POPULATE DETAILS</button>
                            	<!--<button class="btn btn-success" type="button" id="interestedPeopleId">INTERESTED PEOPLE</button>
                            </span>							
                        </div>
						
                    </div>-->
					<div  class="searchMemberCls" >
                	<div class="col-md-6">
					<label class="text-capitalize">Search member by membership no/Name
						<!--<span class="btn btn-success btn-xs"><input type="checkbox" id="advanceSearchBtnId"/>Advanced Search</span>-->
					</label>
					
					<div id="searchErrDiv" style="color:red;"></div>
                        <div class="searchDiv">
                            <div class="row">
                                <div class="col-md-9 col-sm-9 col-xs-12 col-lg-9 pad_right0">
									<div class="pad_5 bg_ff" id="advancSrchDivId" style="display:none;padding-top: 15px; padding-bottom: 15px;"">
										<label class="radio-inline">
                                            <input type="radio" name="advncdSearch" checked="true" class="advancedSearchCls" id="genderId" value="1"/>Gender
                                        </label>
										<label class="radio-inline">
                                            <input type="radio" name="advncdSearch" class="advancedSearchCls" id="casteId" value="2"/>Caste
                                        </label>
										<label class="radio-inline">
                                            <input type="radio" name="advncdSearch" class="advancedSearchCls" id="advanceAgeId" value="3"/>Age
                                        </label>
										<label class="radio-inline">
                                            <input type="radio" name="advncdSearch" class="advancedSearchCls" id="casteGroupId" value="4"/>Caste Group
                                        </label>
										<label class="radio-inline">
                                            <input type="radio" name="advncdSearch" class="advancedSearchCls" id="educationId" value="5"/>Education
                                        </label>
										<div class="radio-inline" style="width:200px;">
											<select class="chosenSelect" id="advancSearchSelectId">
												<option value="0">Select Gender</option>
												<option value="M">Male</option>
												<option value="F">Female</option>
											</select>
										</div>
									</div>
                                	<div class="pad_5 bg_ff" id="normalSearchDivId">
										<label class="radio-inline">
                                            <input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1"/>Membership No
                                        </label>
                                       <!-- <label class="radio-inline">
                                        <input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" />Voter ID
                                        </label>-->
                                        <label class="radio-inline">
                                            <input type="radio"  name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"/>Mobile Number
                                        </label>
                                        <!--<label class="radio-inline">
                                            <input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  onclick="refreshExistingDetails();"  value="4"/>Name
                                        </label>-->
										<input type="hidden" id="cadreSearchType" value="membershipId" />
                                    </div>
                                    <input type="text" class="form-control" id="searchBy"  placeholder="Ex: 38324292/XYZ"/>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-12 col-lg-3 m_top20">
                                    <button class="btn btn-success btn-block btnSearch" id="searchbtn" onClick="getNominatedPostApplication(0)">SEARCH</button>
                                </div>
								
                            </div>
                        </div>
                     </div>
                 </div>
				 </div>
				<div class="row">
				 <div class="col-md-12 col-xs-12 col-sm-12 m_top20" id="searchDivId" style="display:none;">
						<div class="m_top10"><p class="m_0 text-success font_weight font_16">CADRE PROFILE DETAILS</p></div>
						<div id="membersCountId"></div>
						<div id="searchData"></div>
						<div class="" id="scrollDivId" style="display:none;">
							<div  id="cadreSearchDtls" ></div>
							<div class="" id="loadingSmblDivId"></div>
						 </div>							
						 <p class="text-muted m_top10" id="textId" style="display:none;"><small>Note: Please select matches profile</small></p>
	                </div>
	              </div>
				  <img id="cdrDtlsSrchPrcssngImgId" src="images/Loading-data.gif" style="width: 40px; height: 40px; margin-top: 22px;display:none;"/>
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
								    <option value="0">Select Gender</option>
                                	<option value="M">Male</option>
									<option value="F">Female</option>
                                </select>
							<span class="genderErrorCls mandatoryFieldCls"></span>
						    </div>
							<div class="col-md-3">
                            	<label>Date Of Birth<!--<span class="mandatoryFieldCls">*</span>--></label>
 		                        <input type="text" id="dobId" class="form-control clearFieldCls"/>
								<span class="dobErrorCls mandatoryFieldCls"></span>
                            </div>
                            <div class="col-md-3">
                            	<label>Age<span class="mandatoryFieldCls">*</span></label>
 		                        <input type="text" id="ageId" class="form-control clearFieldCls"/>
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
                    <div class="col-md-6 m_top20">
                    	<label>Address</label>
                        <textarea class="form-control clearFieldCls" id="addressId" rows="4"></textarea>
                    </div>
                    <div class="col-md-6 m_top20">
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
                        <input type="text" id="noOfTmBldDntnId" class="form-control clearFieldCls"/>
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
						<button class="btn btn-success" id="printBtnId" style="display:none;" onclick="printCadre();">PRINT</button>
						<img id="cdrDtlsSavingPrcssngImgId" src="images/Loading-data.gif" style="width: 40px; height: 40px; margin-top: 0px;display:none;"/>
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
	<!-- hidden Variables -->
	<div><input type="hidden" id="hiddenCampId" value="2"></div>
</div>
<div class="modal fade myModalprintView " id="" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-body" id="">
		<div class="row">
			<div class="col-md-12">
				<p>Details Are Submitting</p>
				<p class="dtsCls">Do You Want Print</p>
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
</div><!-- /.modal -->

<div class="modal fade" id="interestedmodalId" role="dialog" style="text-transform: uppercase;">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content" style="">
    <div class="modal-body ">
      <button type="button" style="" class="close modalCloseAndShow" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <div class="media " style="">
        <div class="media-left">
        </div>
		<h4 style="text-align:center;margin-bottom: 20px">  Interested People for Blood Donation <img title="Here  we are showing  not blood donated people, who are registered through Public Blood Bank App.  " src="img/info.png" height="15px/"> <a class="btn btn-xs btn-mini btn-success pull-right" href="javascript:{exportToExcel(\'rangeWiseExportBoothReport\')}"> Export Excel</a></h4>
		
		<div class="col-xs-12">
			<div class="col-xs-12" style="margin-bottom:10px;">
				<label class="checkbox-inline" style="margin-left: 700px;margin-bottom:-22px;">
					<input type="checkbox" id="attendedCheckId">Attended
				</label>
			</div>	
			<div class="col-md-3" style="margin-bottom: 10px;">			
				<select id="donatedMembersId" style="margin-bottom:10px;">
					<option value="all">All members</option>
					<option value="yes">Donated Members</option>
					<option value="no" selected>Not Donated Members</option>
				</select>
			</div>
		</div>
        <div class="media-body" id="interestedPeopleModalBody">
        </div>
      </div>
    </div>
  </div>
  </div>
</div>

<input type="hidden" id="hidenMemShipId"></input>

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<!--<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>-->
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript">



//$("#dobId").daterangepicker({singleDatePicker:true});
$("#dobId").datetimepicker({format:'MM/DD/YYYY',maxDate : 'now'});
$("#dtfDntnId").datetimepicker({format:'MM/DD/YYYY',maxDate : 'now'});

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
	
	
	/* var membserShipId = $("#membershipInputId").val();
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
     $(".dobErrorCls").html(' '); */
	  getCadreDetails();
});
$(document).on("click",".printCls",function(){
	 window.print();
	 var value = $(this).attr("value");
	 updatePrintStatus(value);
})
var GbloodDonationresult;
var GmembershipNo;
 function getCadreDetails(mebmerShipId){
	 
	   var membserShipId = mebmerShipId;
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
	 $(".genderErrorCls").html(' ');
	 $("#statusId").html(' ');
	 $("#printBtnId").hide();
	 $("#submitBtnId").prop('disabled', false);
	 $("#cdrDtlsSrchPrcssngImgId").show();
	 
	/* var jObj = {
		   memberShipNo:membserShipId
		}
	*/
	var jObj = {
		locationLevel:2,
		locationValue:0,
		mobileNo:"",
		memberShipNo:membserShipId,
		voterCardNo:"",
		startIndex:0,
		maxIndex:5000,
		removedStatus:false,
		enrollmentId : 0,
		task:"tdpCadreSearchForBloodBank",
		searchType:"memberShipNo",//mobileNo, votercardNo, trNo,
		searchValue : membserShipId
	}
	
		GmembershipNo = membserShipId;
		$.ajax({
		  type:'GET',
		  url: 'getCadreBloodBankSearchDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			 $("#cdrDtlsSrchPrcssngImgId").hide();
			if(result != null){
				if(result.status!=null && result.status=="not exist"){
				 $(".cadreNoErrorCls").html("This cadre number not available.");
				   clearFields();
				}else{
				 GbloodDonationresult = result;
				 populateCadreDetails(result,jObj.memberShipNo);
				}
			}
		});
}
function printCadre()
{
	
	
	if(GbloodDonationresult.bloodDonationId!=null && GbloodDonationresult.bloodDonationId > 0){
	
			$(".myModalprintView").modal("show");
			$("#printnameId").html(''+GbloodDonationresult.name+'');
			$("#printageId").html(''+GbloodDonationresult.age+'');
			$("#printnoOfTmBldDntnId").html(''+GbloodDonationresult.donationsInBloodBank+'');
			$("#printmembershipInputId").html(''+GmembershipNo+'');
			$("#printdobId").html(''+GbloodDonationresult.dob+'');
			$("#printseltBxSxId").html(''+GbloodDonationresult.sex+'');
			$("#printspouseId").html(''+GbloodDonationresult.relativeName+'');
			$("#printmobileNoId").html(''+GbloodDonationresult.mobile+'');
			$("#printslctBxBBId").html(''+GbloodDonationresult.donationsInOtherPlaces+'');
			$("#printBloddBankId").attr("value",''+GbloodDonationresult.bloodDonationId+'');
		}		
}
function populateCadreDetails(result,memberShipNo){
	   //clearing fields 
		clearFields();
		
	if(result.alreadyDonated!=null && result.alreadyDonated==false){
		populateCommonFields(result);
	}else if(result.alreadyDonated!=null && result.alreadyDonated==true){
		$("#printBtnId").show();
		$("#submitBtnId").prop('disabled', true);
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
		}else{
			$("#seltBxSxId").val(0);
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
	 var gender=$("#seltBxSxId").val();
	 var membserShipId = $("#membershipId").val();
	 var serchMobileNo = $("#mobileNo").val();
	 
	 if(membserShipId==null || membserShipId==undefined || membserShipId.trim().length==0){
			 $(".searchTypeCls").html("Please Enter Cadre Number.");
		     return;
		 }
	 $(".searchTypeCls").html(" ");
	if(name==null || name==undefined || name.trim().length==0){
	   $(".nameErrorCls").html("Please Enter Name.");
      return	 
    }
    $(".nameErrorCls").html('');
	if(gender==null || gender==0){
	 $(".genderErrorCls").html("Select Gender.");
	 return;
	}
	$(".genderErrorCls").html(' ');
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
		  cadreDtlsArr.push($("#hidenMemShipId").val());
		  cadreDtlsArr.push($("#hiddenCampId").val());
		  
		 $("#cdrDtlsSavingPrcssngImgId").show();
		 
	   var jObj = {
		   cadreDtlsArr:cadreDtlsArr
		}
		
		$.ajax({
		  type:'POST',
		  url: 'saveCadreDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			 $("#cdrDtlsSavingPrcssngImgId").hide();
			if(result!=null){
				if(result.exceptionMsg=="success"){
		//$("#printpriewBtn").show();
			$("#printnameId").html(''+$("#nameId").val()+'');
			$("#printageId").html(''+$("#ageId").val()+'');
			$("#printnoOfTmBldDntnId").html(''+$("#noOfTmBldDntnId").val()+'');
			$("#printmembershipInputId").html(''+$("#hidenMemShipId").val()+'');
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
	$("#seltBxSxId").val(0);
	$("#slctBxMrrdId").val(0);
	$("#edutnQlfctnSlctBxId").val(0);
	$("#occptnSlctBxId").val(0);
	$("#donationId").val(0);
	$("#slctBxBBId").val("No");
	$("#wllngTdntEmrgncyId").val("No");
	$("#wllngTBClldFrDntnId").val("No");
	
}
	$(document).keypress(function(e) {
				if(e.keyCode==13){
					getCadreDetails();
				}
		  });
$(document).on("click",".interestedPeopleId",function(){
	$("#interestedmodalId").modal("show");
	getAllBloodDonateRegiCandidate("false");
});
function getAllBloodDonateRegiCandidate(attendedType){
	jObj = {
		type:"no",
		attendedType:attendedType
	}
		$.ajax({
		  type:'GET',
		  url: 'getAllBloodDonateRegiCandidateDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAllBloodDonateRegiCandidateDetails(result);
			}else{
				$("#interestedPeopleModalBody").html("No Data Available.");
			}
		});
}
function exportToExcel(tableId)
{
	tableToExcel(''+tableId+'', 'Interested People for Blood Donation ');
}

function buildAllBloodDonateRegiCandidateDetails(result){
	var str ='';
	//str +='<h4 style="text-align:center;margin-bottom: 20px">  Interested People for Blood Donation <img title="Here  we are showing  not blood donated people, who are registered through Public Blood Bank App.  " src="img/info.png" height="15px/"> <a class="btn btn-xs btn-mini btn-success pull-right" href="javascript:{exportToExcel(\'rangeWiseExportBoothReport\')}"> Export Excel</a></h4>';
	str +='<table class="table table-bordered m_top20" style="" id="rangeWiseExportBoothReport">';
        str +='<thead>';
            str +='<th> DONAR NAME  </th>';
            str +='<th> MOBILE NO </th>';
            str +='<th> INTERESTED TIME  </th>';
            str +='<th> MEMBERSHIP NO </th>';
        str +='</thead>';
        str+='<tbody>';
        for(var i in result){
          str +='<tr>';
              str +='<td  style="text-transform: uppercase;" >'+result[i].name+'</td>';
              str +='<td  style="text-transform: uppercase;" >'+result[i].mobileNumber+'</td>';
              str +='<td  style="text-transform: uppercase;" >'+result[i].startTime+'</td>';
              str +='<td  style="text-transform: uppercase;">'+result[i].membershipNo+'</td>';
          str +='</tr>';  
          }  
      str+='</tbody>';
      str +='</table>';
	  $("#interestedPeopleModalBody").html(str);
	 $("#rangeWiseExportBoothReport").dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
}
var isFree =true;
function getNominatedPostApplication(startIndex)
		{
		
		var locationLevel = 0;
		var locationValue = 0;
		var searchName = '';
		var mobileNo = '';
		var casteCategory = '';
		var casteStateId = 0;
		var fromAge = 0;
		var toAge = 0;
		var memberShipCardNo = '';
		var trNumber = '';
		var voterCardNo = '';
		var gender = '';
		var houseNo = '';
		var membershipAndMobileNo = '';

		
			//$('#cadreSearchDtls').html(' <img style="margin-left: 400px; margin-top: 20px; width: 200px; height: 150px;" id="" class="offset7" src="images/icons/cadreSearch.gif">');
	
	$("#scrollDivId").show();
	 $("#textId").hide();
	   
	if(startIndex == 0)
	{
		$(".paginationDivId").html('');
	}
		$(".paginationDivId").hide();	
		
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		//$("#cadreSearchDtls").show();
		
		var cadreSearchTypeStr =$('.cadreCheckCls:checked').val();
		
		
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType =$('.searchTypeCls:checked').val();
		var parentLocation = 0;
		var panchayatId = $("#panchaytList").val();
		var mandalId = $("#mandalList").val();
		var constituencyId = $("#constituencyId").val();
		var districtId = $("#districtId").val();
		var stateId = $("#statesDivId").val();
		if(searchRadioType ==1)
				searchRadioType ="membershipId";
		else if(searchRadioType ==2)
				searchRadioType ="voterId";
		else if(searchRadioType ==3)
				searchRadioType ="mobileNo";
		else if(searchRadioType ==4)
				searchRadioType ="name";
			
			//alert(searchRadioType);
			
		/* if(panchayatId !=0 && panchayatId>0)
		{
			if(panchayatId.substr(0,1) == 1){
				  locationLevel = 6;
			}
			else if(panchayatId.substr(0,1) == 2){
				 locationLevel = 8;
				 
			}								
			locationValue = panchayatId.substr(1);
		}
		else if(mandalId !=0 && mandalId>0)
		{
			if(mandalId.substr(0,1) == 1){
				 locationLevel = 7;
			}
			else if(mandalId.substr(0,1) == 2){
				 locationLevel = 5;
			}
			else if(mandalId.substr(0,1) == 3){
				 locationLevel = 8;
			}
			locationValue = mandalId.substr(1);
		}
		
		else if(constituencyId != 0 && constituencyId>0)
		{
			locationValue = constituencyId;
			locationLevel = 4;	
		}
		else if(districtId != 0 && districtId>0)
		{
			locationValue = districtId;
			locationLevel = 3;
		}
		else if(stateId !=0 && stateId>0){
			locationValue = stateId;
			locationLevel = 2;
		} */
		if(searchRadioType == 'membershipId')
		{
			memberShipCardNo = $('#searchBy').val().trim();
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Membership Card No.');
				return;
			}
			if(searchBy.trim().length != 8||searchBy.trim().length > 8)
			{
				$('#searchErrDiv').html('Invalid memberShipCardNo.');
				return;	
			}
			else if(searchRadioType=="membershipId"){
					
					var numericExpression = /^[0-9]+$/;
					if(!$('#searchBy').val().trim().match(numericExpression)){
						$('#searchErrDiv').html('Enter  Number Digits Only.');
						return;
					}else{
						$('#searchErrDiv').html(' ');
					}
			}
			/*else if(memberShipCardNo.trim().length != 8)
			{
				alert(3);
				$('#searchErrDiv').html('Invalid memberShipCardNo No.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}*/	
		}			
		if(searchRadioType == 'voterId')
		{
			if(stateId ==0){
			$('#searchErrDiv').html('Please Select State.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}		
			voterCardNo = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 || searchBy.trim() == null )
			{
				$('#searchErrDiv').html('Please enter Voter Card No.');
				return;
			}/*else if(searchBy.trim().length > 12)
			{
				$('#searchErrDiv').html('Invalid voter No.');
				return;
			}*/
		}
		if(searchRadioType == 'mobileNo')
		{	
			mobileNo = $('#searchBy').val().trim();
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Mobile No.');
				return;
			}
			if(searchBy.trim().length != 10 ||searchBy.trim().length > 10)
			{
				$('#searchErrDiv').html('Invalid Mobile No.');
				return;
			}
			else if(searchRadioType=="mobileNo"){
					var numericExpression = /^[0-9]+$/;
					if(!$('#searchBy').val().match(numericExpression)){
						$('#searchErrDiv').html('Enter Number Digits Only.');
						return;
					}
			}
			/*else if(mobileNo.trim().length != 10)
			{
				alert(2);
				$('#searchErrDiv').html('Invalid Mobile No.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}*/	
			
		}
	if(searchRadioType == 'name')
		{
			if(stateId ==0){
			$('#searchErrDiv').html('Please Select State.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}	
			if( districtId==0){
			$('#searchErrDiv').html('Please Select District.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}
			if(constituencyId ==0){
			$('#searchErrDiv').html('Please Select Constituency.');
				return;				
			}else{
				$('#searchErrDiv').html(' ');
			}
			searchName = $('#searchBy').val().trim();
		  
		        var numericExpression =  /^[a-z,A-Z," "]+$/;
					if(!$('#searchBy').val().match(numericExpression)){
						$('#searchErrDiv').html('Enter characters Only.');
						return;
					}
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Name.');
				return;
			}
			else if(searchBy.trim().length < 3)
			{
				$('#searchErrDiv').html('Please enter Minimum 3 Characters.');
				return;
			}
			
		}
		if(searchRadioType == 'trNo')
		{
			trNumber = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter trNo.');
				return;
			}
			
		}
		
		var removedStatus =false;
		if($('#onlyCandidatesId').is(':checked')){
			 removedStatus = true;
		 }
		
		$("#searchDataImg").show();

		if(locationValue == null)
			locationValue =0;
		$('#cadreSearchDtls').html(' <img style="height: 150px;" id="" class="col-md-4 col-md-offset-2 col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3" src="images/icons/cadreSearch.gif">');
		if(isFree){
			 isFree =false;
			 $("#searchDivId").show();
		var jsObj =
		{
			locationLevel :locationLevel,
			locationValue:locationValue,
			searchName : searchName,
			mobileNo: mobileNo,
			casteCategory : casteCategory,
			fromAge : fromAge,
			toAge : toAge,
			memberShipCardNo: memberShipCardNo,
			casteStateId : casteStateId,
			trNumber : trNumber,
			voterCardNo:voterCardNo,
			gender:gender,
			houseNo:houseNo,
			startIndex:startIndex,
			maxIndex : 50,
			removedStatus:removedStatus,
			enrollmentId : 3,
			task:"NominatedPostSearch"
		}
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				globalSelectedMemberIdsArr = []; // Clearing Array 
				$("#textId").hide();
				 isFree =true;
				  $("#cadreSearchDtls").html('');
			$(".paginationDivId").show();
				 if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				$("#searchDataImg").hide();
				$('#cadreDetailsDiv').show();
				if(result != null && result.previousRoles != null && result.previousRoles.length>0)
				{
					$('#membersCountId').show();
					buildCadreDetails(result.previousRoles,"cadre"); 
				}
				 else
				{
					$('#cadreSearchDtls').html("");
					  $('#textId').html("");
					$('#cadreSearchDtls').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				} 
			});  
		}
	}
	function buildCadreDetails(result,status){ 
	
	    $("#cadreSearchDtls").html('');
         $("#textId").hide();
		$("#cadreSearchDtls").show();
		$("#scrollDivId").show();
		var str='';
		var str1='';
		

		
		str1+='<h4 class="m_0 text-success">CADRE PROFILE DETAILS : </h4>';
		str1+='<p>Search Results: <b><u id="cadreSearchSize">'+result.length+'</u></b> Members</p>';
		$("#searchData1").html(str1);
		
			
		if(result != null && result.length >0){
			str +='<ul class="list-inline best-matched-profile ">';
                                
		for(var i in result)
			{	
				if(result[i].nominatedPostCandidateId != null && result[i].nominatedPostCandidateId >0 )
					str +='<li  style="background:lightgrey;height: 200px;margin-left: 40px;">';
				else
					str +='<li style="height: 200px;">';
				
				str +='<div class="img-center">';
				
				
				//str+='<img src="dist/img/profile.png" class="img-responsive img-circle" alt="Profile"/>';
				//str +='</div>';
				//console.log(result[i].id);
				if(result[i].id != null && result[i].id >= 0){ // no cadre search  candidate id
				
					str +='<img style="width: 60px;height:60px;border:1px solid #ddd;margin-left:49px;" src="https://mytdp.com/images/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
						str +='</div>';
					str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" sri attr_nominated_post_candidate_id="'+result[i].tdpCadreId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
				}else {
						str +='<img style="width:60px;height:60px;border:1px solid #ddd;margin-left:49px;" src="https://mytdp.com/images/cadre_images/'+result[i].imageURL+'" class="img-responsive img-circle" alt="Profile"/>';
							str +='</div>';
						// cadre search  candidate id
						str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].nominatedPostCandidateId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						
						/*if(result[i].nominatedPostCandidateId == null){
							result[i].nominatedPostCandidateId = 0;
						}
						if(result[i].nominatedPostCandidateId != null && result[i].nominatedPostCandidateId >0 ){
							str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].nominatedPostCandidateId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						}else{
							str +='<input type="checkbox" attr_cadreId="'+result[i].tdpCadreId+'" class="cadreCls checkboxCls hideShowDivCls" name="checkbox" style="margin:auto;display:block;" id="appProfCheckBoxId" attr_nominated_post_candidate_id="'+result[i].tdpCadreId+'" attr_membership_id="'+result[i].memberShipCardId+'" />';
						}
						*/
				}
			   // str +='<input type="checkbox" style="margin:auto;display:block;" class="" />';
				str +='<p class="m_0 m_top5 text-center cadreName" value='+result[i].cadreName+'><b>'+result[i].cadreName+'</b></p>';
				str +='<p class="m_0 m_top5 text-center cadreVotrCardId" value="'+result[i].voterCardNo+'"><b>VOTERID : </b> '+result[i].voterCardNo+'</p>';
				if(result[i].memberShipCardId != null && result[i].memberShipCardId != "")
				str +='<p class="m_0 text-center cadreMembrShpNo" value="'+result[i].memberShipCardId+'"><b> MID : </b> '+result[i].memberShipCardId+'</p>';
				str +='<p class="m_0 text-center cadreMobilNo" value="'+result[i].mobileNo+'"><b>MOBILE : </b> '+result[i].mobileNo+'</p>';
				str +='<input type="hidden" class="tdpCadreIdCls" value="'+result[i].tdpCadreId+'" attr_nominated_post_candidate_id="'+result[i].nominatedPostCandidateId+'"/>';
				
					if(result[i].addressVO != null && result[i].addressVO.constituencyName != null && result[i].addressVO.constituencyName.length > 0)
					{
						str +='<p class="text-center m_0"><b> ASSEMBLY : </b><span style="white-space: pre-wrap;"> '+result[i].addressVO.constituencyName+'</span></p>';
						
					}else if(result[i].constituency != null && result[i].constituency.length > 0){
						 str +='<p class="text-center m_0">'+result[i].constituency+'</p>';
						
					}else{
						str +='<p class="text-center m_0">&nbsp;</p>';
						
					}
					/*if(status == "cadre"){
			 str+='<ul class="enrolled-mem" id="">';
					//$("#familyMembersDiv").html(result[i].enrollmentYear);
					if(result[i].enrollmentYears != null && result[i].enrollmentYears.trim().length > 0)
					{
						var years = result[i].enrollmentYears.split(", ");	

						if(years.indexOf("2016") > -1)
							str+='<li class="yes" style="width: 49px;margin :0px;">2016<span></span></li>&nbsp;';
						else
							str+='<li class="no" style="width: 49px;margin :0px;">2016<span></span></li>&nbsp;';
						if(years.indexOf("2014") > -1)
							str+='<li class="yes" style="width: 49px;margin :0px;">2014<span></span></li>&nbsp;';
						else
							str+='<li class="no" style="width: 49px;margin :0px;">2014<span></span></li>&nbsp;';
						if(years.indexOf("2012") > -1)
							str+='<li class="yes" style="width: 49px;margin :0px;">2012<span></span></li>&nbsp;';
						else
							str+='<li class="no" style="width: 49px;margin :0px;">2012<span></span></li>&nbsp;';
						if(years.indexOf("2010") > -1)
							str+='<li class="yes" style="width: 49px;margin :0px;">2010<span></span></li>&nbsp;';
						else
							str+='<li class="no" style="width: 49px;margin :0px;">2010<span></span></li>&nbsp;';
					}
					else
					{
						str+='<li class="no" style="width: 49px;margin :0px;">2016<span></span></li>&nbsp;';
						str+='<li class="no" style="width: 49px;margin :0px;">2014<span></span></li>&nbsp;';
						str+='<li class="no" style="width: 49px;margin :0px;">2012<span></span></li>&nbsp;';
						str+='<li class="no" style="width: 49px;margin :0px;">2010<span></span></li>&nbsp;';
					}
		   			str+='</ul>	';
			}*/
					str +='</li>';
				 
			}
			
 		str +='</ul>';	
			$("#cadreSearchDtls").html(str);
			var length = $("#cadreSearchDtls").find("li").length;
			$("#membersCountId").html("<p id='memberCountSpanId'>Search Results <span class='font_weight'>"+length+"</span> Members</p>")
			if(result.length>3)
			{
			$(".best-matched-profile").slick({
				slide: 'li',
				slidesToShow: 4,
			   infinite: false
			   });
			   $(".slick-next").css("margin-right","10px;")
			   $(".slick-prev").css("margin-left","10px;")
			}
			$("#textId").show();
		}else{
				str+='No Data Available';
				$("#cadreSearchDtls").html(str);
				$("#textId").hide();
				//$("#cadreSearchDtls").html(No Data Available);			
		}
	    
	}
	function refreshExistingDetails(){ 	
		hideDetails();    
		$("#searchErrDiv").html(""); 
		$("#notCadreErrMsg").html("");
		$("#searchById").val("");
		/*$("#searchBy").val("");
		//$("#cadreDetailsDiv").html("");
		$(".paginationDivId").html('');
		//$("#cadreDetailsDiv").hide();
		$("#searchErrDiv").html("");
		$("#membrshipAndMobileNoDiv").html("");
		$("#cadreSearchSize").hide();
		//$("#cadreSearchDtls").html("");
		$("#searchData").html("");
		$("#searchData1").html("");
		$("#notCadreNameId").val("");
		$("#notCadreVoterId").val("");
		$("#notCadreMobilNoId").val("");
		$("#notCadreErrMsg").html("");  
		$("#searchById").val("");
		$("#searchErrDiv1").html("");*/
	}
function hideDetails(){
	$('.ramakrishnaCls').hide();
	$('#searchDivId').hide();
	$("#uploadFlDivId").hide();
	$("#submitBtnId").hide();
	$("#addedRefferalsDiv").hide();
}
$( document ).on("click",".cadreCls",function(){
 
	    involvedCadreIds = [];
	    globalSelectedMemberIdsArr = [];
	    $("#involvedMembers").html('(0 - Members added)');
 
		/*$('#nameId').val('');
		$('#spouseId').val('');
		$('#addressId').val('');
		$('#mobileNoId').val('');
		$('#seltBxSxId').val(0);
		$('#occptnSlctBxId').val(0);
		$('#edutnQlfctnSlctBxId').val(0);
		$('#slctBxBBId').val(0);
		$('#changePanchyatId').val(0);
		$('#noOfTmBldDntnId').val('');
		
		$('.membersBlock').html('');
		$("#appliedPostId").html('');
		$('.jFiler-row').html('');
		
		$('.donationId').val(0);
		$('.wllngTdntEmrgncyId').val(0);
		$('.wllngTBClldFrDntnId').val(0);
		
		$('.rmrksId').val('');
		$('.tdpCadreName').val('');
		$('.cadreVoterId').val('');
		$('.cadreMobileNo').val('');
		$('.referCadreIds').val('');
		$('.nominatedCandId').val('');
		$('#candidateTypeId').val('');
		
		$('.chosenSelect').val(0);
		$(".chosenSelect").trigger("chosen:updated");
		if($("#addressCheckId").is(':checked')){ 
			$("#addressCheckId").trigger("click");
		}*/
		
	if($(this).is(':checked')){ 
	   $(".cadreCls").prop("checked" ,false);
	   $(this).prop( "checked" ,true);
	 //$("#showAndHideDivId").show();
		 //globalCadreId = $(this).attr("attr_cadreId"); 
		//var candiId = $(this).attr("attr_nominated_post_candidate_id"); 
		//globalNPCandiId = $(this).attr("attr_nominated_post_candidate_id"); 
		var mebmerShipId = $(this).attr("attr_membership_id"); 
		$("#hidenMemShipId").val(mebmerShipId);
		  if($(this).is(':checked')){
			$(".ramakrishnaCls").show();
			$("#addedRefferalsDiv").show();
			$("#uploadFlDivId").show();
			$("#submitBtnId").show();
			 /* if(globallevelId > 0){
			   $('#boardLvlId').val(globallevelId).trigger('chosen:updated');
		   }
		   if(globallevelId == 1){
			  getDepartments(0);
		   }
		   if(globallevelId > 1){
			   getLocationByDepartment();
				getOpenedPostionsStates('nominatedStaeId','');
				showHideByNominatedPost('');
		   } */
			getCadreDetails(mebmerShipId);
		  }
		
	}else{
			$(".ramakrishnaCls").hide();
			$("#addedRefferalsDiv").hide();
			$("#uploadFlDivId").hide();
			$("#submitBtnId").hide();
		}
});
$(document).on("change","#donatedMembersId",function(){
	if($("#attendedCheckId").is(":checked"))
  {
   getAllBloodDonateRegiCandidateDetails("true");
  }else{
    getAllBloodDonateRegiCandidateDetails("false");
  }
});
function getAllBloodDonateRegiCandidateDetails(attendedType){
	$("#rangeWiseExportBoothReport").html("");
	jObj = {
		type:$("#donatedMembersId").val(),
		attendedType:attendedType
	}
		$.ajax({
		  type:'GET',
		  url: 'getAllBloodDonateRegiCandidateDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAllBloodDonateRegiCandidateDetails(result);
			}else{
				$("#interestedPeopleModalBody").html("No Data Available.");
			}
		});
}
$(document).on("click","#attendedCheckId",function(){
  if($(this).is(":checked"))
  {
   getAllBloodDonateRegiCandidateDetails("true");
  }else{
    getAllBloodDonateRegiCandidateDetails("false");
  }
});
</script>
<script>
var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>

</body>
</html>
