<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NOMINATED POST PROFILE CREATION</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Dropzone/basic.css" rel="stylesheet" type="text/css"/>
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />
<link href="dist/Plugins/Datatables/datatables.css" rel="stylesheet" type="text/css"/>
<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/Plugins/Datatables/datatables.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominatedPost.js?v=1.0.5"></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>

<style type="text/css">
.jFiler-input-dragDrop
{
	width:100%;
}
.panel-footer
{
	background:#ccc;
	cursor:pointer;
}
.createAppointmentSearch {
    padding: 0;
}
.createAppointmentSearch li {
    border: 1px solid #ddd;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.4);
    list-style: outside none none;
    margin-top: 8px;
    padding: 8px;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12">
       	  <div class="panel panel-default">
        	  <div class="panel-heading">
        	    <h3 class="panel-title">NOMINATED POST PROFILE CREATION</h3>
	      	  </div>
        	  <div class="panel-body bg_ef">
              	<div class="row">
                	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<p class="text-success">SEARCH APPLICANT</p>
                    </div>
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top10">
                    	<label class="checkbox-inline">
                        	<input value="Cadre" type="radio" class="cadreCheckCls" name="checkBoxName" id="cadreSearchId" checked="true" onclick="refreshExistingDetails();" />Cadre
                        </label>
                        <label class="checkbox-inline" style="padding-left: 10px;">
                        	<input value="Not Cadre" type="radio" class="cadreCheckCls" name="checkBoxName"/>Not Cadre
                        </label>
						<span style="margin-left:8px;cursor:pointer" class="addMemberModal">
							<i class="glyphicon glyphicon-plus-sign"></i>
							Add Member
						</span>
                    </div>
					</div>
					
          <div id="searchMemberDiv">
                    <div class="col-md-2 col-xs-12 col-sm-6 col-lg-2 m_top10" id="statedisplaydivid">
                    	<label>State</label>
                        <select id="statesDivId"  onchange="getDistrictsForStates(this.value,this.id,'');" class="chosenSelect">
                        	<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="36">Telangana</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-xs-12 col-sm-6 col-lg-2 m_top10" id="districtDiv">
                    	<label>District</label>
                        <select id="districtId" onchange="getConstituenciesForDistricts(this.value,this.id,'');" class="chosenSelect" >
                        
                        </select>
                    </div>
                    <div class="col-md-2 col-xs-12 col-sm-6 col-lg-2 m_top10" id="constitunecyDiv">
                    	<label>Constituency</label>
                        <select id="constituencyId" onchange="getMandalCorporationsByConstituency('',this.id);" class="chosenSelect" >
                        </select>
                    </div>
                    <div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10" id="mandalDiv">
                    	<label>Mandal/Muncipality/Corporation</label>
                        <select id="mandalList" onchange="getPanchayatWardByMandal('',this.id);"  class="chosenSelect">
							<option value="0"> Select Mandal/Municipality </option>
                        </select>
                    </div>
                    <div class="col-md-3 col-xs-12 col-sm-6 col-lg-3 m_top10" id="panchayatDiv">
                    	<label>Panchayat/Ward/Division</label>
                        <select id="panchaytList"  onchange="getAllCadreInPanchayat();" class="chosenSelect">
							<option value="0"> Select Panchayat </option>
                        </select>
                    </div>
                
                <div class="row" class="searchMemberCls">
                	<div class="col-md-8 col-xs-12 col-sm-12 col-lg-12">
					<label class="text-capitalize">Search member by voter id/membership no/mobile number/Name</label>
                        <div class="searchDiv">
                            <div class="row">
                                <div class="col-md-9 col-sm-9 col-xs-12 col-lg-9 pad_right0">
                                	<div class="pad_5 bg_ff">
									<label class="radio-inline">
                                            <input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1"/>Membership No
                                        </label>
                                        <label class="radio-inline">
                                        <input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" />Voter ID
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio"  name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"/>Mobile Number
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  onclick="refreshExistingDetails();"  value="4"/>Name
                                        </label>
										<input type="hidden" id="cadreSearchType" value="membershipId" />
                                    </div>
                                    <input type="text" class="form-control" id="searchBy"/>
									<div id="searchErrDiv" style="color:red;"></div>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-3 col-lg-3 pad_left0">
                                    <button class="btn btn-success btn-block btnSearch" id="searchbtn">SEARCH</button>
                                </div>
                            </div>
                        </div>
                     </div>
                 </div>
				 </div>
				 <div id="cadreById" style="display:none;">
				 <div id="notCadreErrMsg" style="color:red;"></div>
				 <div class="row" class="searchMemberCls">
                	<div class="col-md-8 col-xs-12 col-sm-12 col-lg-12">
					<label class="text-capitalize">Search member by voter id/membership no/mobile number/Name</label>
                        <div class="searchDiv">
                            <div class="row">
                                <div class="col-md-9 col-sm-9 col-xs-12 col-lg-9 pad_right0">
                                	<div class="pad_5 bg_ff">
									    <label class="radio-inline">
											<input type="radio" name="radioGroup" class="" id="voterId"  onclick="refreshExistingDetails();"  value="2" />Voter ID
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio"  name="radioGroup" class="" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"/>Mobile Number
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="radioGroup" class="" id="name"  onclick="refreshExistingDetails();"  value="4"/>Name
                                        </label>
										<input type="hidden" id="cadreSearchType" value="membershipId" />
                                    </div>
                                    <input type="text" class="form-control" id="searchById"/>
									<div id="searchErrDiv1" style="color:red;"></div>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-3 col-lg-3 pad_left0">
                                    <button class="btn btn-success btn-block btnSearch" id="searchbtn">SEARCH</button>
                                </div>
                            </div>
                        </div>
                     </div>
                 </div>
				 </div>
				 <form name="submitApplication" id="submitApplication"  method="post" enctype="multipart/form-data">
                 <div class="row">
				 <img style="margin-left: 400px; margin-top: 20px; width: 200px; height: 150px; display: none;" id="searchDataImg" class="offset7" src="images/icons/cadreSearch.gif">
                    <div class="col-md-12 col-xs-12 col-sm-12" id="searchDivId">
                    		<!--<h4 class="m_0 text-success">APPLICANT PROFILE DETAILS</h4>-->
                        	<div id="searchData"></div>
                        	<div class="scroll-div" id="scrollDivId" style="display:none;">
                                <ul class="list-inline best-matched-profile " id="cadreSearchDtls" >
                                </ul>
                             </div>
                             <p class="text-muted" id="textId" style="display:none;"><small>Note: Please select matches profile</small></p>
	                    </div>
						<div id="appliedPostForSelectedId"></div>
                    <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<div class="panel panel-default panelNewDepartments">
                        	<div class="panel-heading">
                            	<h4 class="panel-title">DO YOU WANT TO CHANGE YOUR ADDRESS OR PHONE NUMBER <input type="checkbox" id="addressCheckId"/></h4>
                            </div>
                            <div class="panel-body bg_ff pad_10" style="display:none;" id="changePhoneNumberDiv">
                            	<div class="row">
									<div id="errorMsg" style="color: red;margin-left:25px;"></div>
                                   	<div class="col-md-4 col-lg-4 col-sm-5 col-xs-11">
                                    	<label>CHANGE PHONE NUMBER</label>
                                    	<div class="input-group">
                                        	<input type="text" class="form-control" id="phoneNumId"/>
                                            <span class="input-group-addon bg_ff">
                                            	<i class="glyphicon glyphicon-plus-sign"></i>
                                                <i class="glyphicon glyphicon-minus-sign"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top10">
	                                	<h4 class="panel-title">ADDRESS CHANGE</h4>
                                    </div>
                                	<div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>H No</label>
                                        <input type="text" class="form-control" id="houseNumberId"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Address Lane 1</label>
                                        <input type="text" class="form-control" id="addressLane1Id"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Address Lane 2</label>
                                        <input type="text" class="form-control" id="addressLane2Id"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>State</label>
                                        <select class="chosenSelect" id="addStateId">
										<option value="0">Select State</option>
                                        	<option value="1">AndhraPradesh</option>
											<option value="36">Telangana</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                	<div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>District</label>
                                        <select class="chosenSelect" id="addDistrictId">
                                        	<option>District</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Constituency</label>
                                        <select class="chosenSelect" id="addConstituencyId">
                                        	<option>Constituency </option>
                                        </select>
                                    </div>
									  <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Mandals/Municipality</label>
                                        <select class="chosenSelect" id="addMandalsId">
                                        	<option>Mandals/Municipality </option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Village</label>
                                        <select class="chosenSelect" id="addVillageId">
                                        	<option>Village Name</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Pincode</label>
                                        <input type="text" class="form-control" id="addPincodeId"/>
                                    </div>
									 <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10 col-md-offset-6">
                                    	<button type="button" class="btn btn-primary btn-sm pull-right" id="updateButnId" onclick="savechangeAddressForNominatedPost()" style="margin-top:25px;">UPDATE</button>
                                    </div>
								</div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<!--<div class="bg_ff pad_10">
                        	
                        </div>-->
                    </div>
                	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<h4 class="m_0 text-success">NOMINATED LEVEL AND LOCATION 
							<!--<span class="pull-right f_14" style="cursor:pointer" >
								Add One More 
								<i class="glyphicon glyphicon-plus-sign"></i>
							</span>-->
						</h4>
                    </div>
					
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
						<div class="addBlockNew cloneBlockDiv">
							<div class="row">
                            	<div class="col-md-4 col-sm-4 col-xs-12 m_top10 col-lg-4">
                                	<h4 class="panel-title">PLEASE SELECT APPLYING POST TYPE</h4>
                                </div>
                                <div class="col-md-5 col-sm-5 col-xs-12 col-lg-5">
                                	<div class="btn-group">
                                      <button type="button" id="nomintdPostId" class="btn btnClassChange btnNewCustom btnActive btn-xs" onclick="getDepartments('',1);">Nominated Post</button>
                                      <button type="button" id="partyPostId" class="btn btnClassChange btnNewCustom btn-xs" onclick="getDepartments('',2);">Party Post</button>
                                    </div>
                                </div>
                            </div>
							<div class="row">
							<div class="errorMsgCls" style="color:red;"></div>
								<span class="iconClose" style="display:none;cursor:pointer;">
									<i class="glyphicon glyphicon-remove"></i>
								</span>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Nominated Post Level</label>
									<select class="chosenSelect boardLvlCls" id="boardLvlId" name="nominatedPostVO.nominatdList[0].boardLevelId" onchange="showHideByNominatedPost('');" attr_no="">
									<option value="0">Select Board Level</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 stateShowCls" id="statesShowDivId">
									<label>State Name</label>
									<select class="chosenSelect nominatedStaeCls" onchange="getDistrictsForStates(this.value,this.id,'');" id="nominatedStaeId" name="nominatedPostVO.nominatdList[0].stateId" attr_no="">
										<option value="0">Select State</option>
										<option value="1">Andhra Pradesh</option>
										<option value="36">Telangana</option>
									</select>
								</div>
						        <div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 districtShowCls" id="districtShowDivId">
									<label>District</label>
									<select class="chosenSelect nominatedDistCls" onchange="getConstituenciesForDistricts(this.value,this.id,'');" id=
									"nominatedDistId" name="nominatedPostVO.nominatdList[0].districtId" attr_no="">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 constituencyShowCls" id="constituencyshowDivId">
									<label>Constituency</label>
									<select attr_no="" class="chosenSelect nominatdConstCls" onchange="getMandalCorporationsByConstituency('',this.id);" id="nominatdConstId" name="nominatedPostVO.nominatdList[0].ConstituencyId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 mandalShowCls" id="mondalShowDivId">
									<label>Mandal/ Muncipality / Corporation</label>
									<select attr_no="" class="chosenSelect nominatedMandlCls" onchange="getPanchayatWardByMandal('',this.id);" id="nominatedMandlId" name="nominatedPostVO.nominatdList[0].mandalId">
									
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 panchayatShowCls" id="panchayatShowDivId">
									<label>Panchayat/ Ward / Division</label>
									<select attr_no="" class="chosenSelect nominatedPanchayatCls" id="nominatedPanchayatId" name="nominatedPostVO.nominatdList[0].panchayatId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Department Name</label>
									<select attr_no="" class="chosenSelect depmtsCls"  id="depmtsId" onchange="getDepartmentBoards('');" name="nominatedPostVO.nominatdList[0].deptId">
									<option value="0">Select Department</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Corporation/Board Name</label>
									<select attr_no="" class="chosenSelect deptBoardCls" id="deptBoardId" onchange="getDepartmentBoardPositions('');" name="nominatedPostVO.nominatdList[0].deptBoardId">
									<option value="0">Select Department Board</option>	
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Position Name</label>
									<select attr_no="" multiple class="chosenSelect deptBoardPostnCls" id="deptBoardPostnId" name="nominatedPostVO.nominatdList[0].positions">
									<option value="0">Select Board Position</option>
									</select>
								</div>
								<input type="hidden" class="tdpCadreId" name="nominatedPostVO.id">
								<input type="hidden" class="tdpCadreName" name="nominatedPostVO.name">
								<input type="hidden" class="cadreMobileNo" name="nominatedPostVO.mobileNo">
								<input type="hidden" class="cadreVoterId" name="nominatedPostVO.voterCardNo">
								<input type="hidden" class="referCadreIds" name="nominatedPostVO.refferCadreIds">
								<input type="hidden" class="nominatedCandId" name="nominatedPostVO.nominatedCandId">
							</div>
							
						</div>
						<div class="addBlockNew cloneBlockDiv" id="cloneDivBlock" style="display:none;">
							<div class="row">
                            	<div class="col-md-4 col-sm-4 col-xs-12 m_top10 col-lg-4">
                                	<h4 class="panel-title">PLEASE SELECT APPLYING POST TYPE</h4>
                                </div>
                                <div class="col-md-5 col-sm-5 col-xs-12 col-lg-5">
                                	<div class="btn-group">
                                      <button type="button" id="nomintdPostId" class="btn btnClassChange btnNewCustom btnActive btn-xs nominatdPostSelCls" >Nominated Post</button>
                                      <button type="button" id="partyPostId" class="btn btnClassChange btnNewCustom btn-xs partyPostSelCls" >Party Post</button>
                                    </div>
                                </div>
                            </div>
							<div class="row">
							<div class="errorMsgCls" style="color:red;"></div>
								<span class="iconClose" style="display:none;cursor:pointer;">
									<i class="glyphicon glyphicon-remove"></i>
								</span>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Nominated Post Level</label>
									<select class="boardLvlCls" id="boardLvlId" onchange="showHideByNominatedPost('');" >
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 stateShowCls" id="statesShowDivId">
									<label>State Name</label>
									<select  class="nominatedStaeCls" onchange="getDistrictsForStates(this.value,this.id,'');" id="nominatedStaeId">
										<option value="0">Select State</option>
										<option value="1">Andhra Pradesh</option>
										<option value="36">Telangana</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 districtShowCls" id="districtShowDivId">
									<label>District</label>
									<select  class="nominatedDistCls" onchange="getConstituenciesForDistricts(this.value,this.id,'');" id=
									"nominatedDistId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 constituencyShowCls" id="constituencyshowDivId">
									<label>Constituency</label>
									<select class="nominatdConstCls" onchange="getMandalCorporationsByConstituency('',this.id);" id="nominatdConstId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 mandalShowCls" id="mondalShowDivId">
									<label>Mandal/ Muncipality / Corporation</label>
									<select  class="nominatedMandlCls" onchange="getPanchayatWardByMandal('',this.id);" id="nominatedMandlId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 panchayatShowCls" id="panchayatShowDivId">
									<label>Panchayat/ Ward / Division</label>
									<select  class="nominatedPanchayatCls" id="nominatedPanchayatId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Department Name</label>
									<select  class="depmtsCls"  id="depmtsId" onchange="getDepartmentBoards('');">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Corporation/Board Name</label>
									<select  class="deptBoardCls" id="deptBoardId" onchange="getDepartmentBoardPositions('');">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Position Name</label>
									<select  class="deptBoardPostnCls" id="deptBoardPostnId">
									</select>
								</div>
								
							</div>
							
						</div>
						
					</div>
					
					
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
						<div id="addOneMoreBlock"></div>
						<div class="panel-footer m_top10"  id="addOneMore">
							<p class="text-center text-capital" >+ Click to add more nominations</p>
						</div>
					</div>
					
                </div>
                 <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
                        <h4 class="panel-title text-success">REFERRAL</h4>
                        <label>Do you have reference</label><br/>
						
                        <label class="radio-inline">
                        	<input type="radio" class="referenceModal" name="radio"/>Yes
                        </label>
                        <label class="radio-inline">
                        	<input type="radio" checked="true" name="radio"/>No
                        </label>
                    </div>
					<div class="row" id="uploadFlDivId" >
                     	<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                        	<p>UPLOAD SCAN COPY</p>
								<input type="file" id="filer_input3" multiple="multiple"  name="fileImage"/>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12 col-lg-4 col-lg-offset-4 m_top20">
                        	<button class="btn btn-success btn-block" onclick="savingApplication();" type="button">SUBMIT APPLICATION</button>
                        </div>
						<div style="margin-top:12px;"><img id="savingAjaxImg" src="images/icons/loading.gif" style="display:none;"/></div>
						<div class="col-md-6 m_top25" id="savingStatusDivId"></div>
                    </div>
					
					</form>
              </div>
      	  </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:85%" role="document">
    <div class="modal-content">
      <div class="modal-body" style="padding:0px;">
	  <jsp:include page="commonCadreSearch.jsp" flush="true"/>
       
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="addMemberModalBlock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:85%;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">ADDING NEW CANDIDATE</h4>
      </div>
	  <form name="saveNotCadre" id="saveNotCadre"  method="post" enctype="multipart/form-data">
      <div class="modal-body">
		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">voter Id</label>
				<input type="text"  id="voterId" class="form-control" name="addNotcadreRegistrationVO.voterId"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">name</label>
				<input type="text" id="nameId" class="form-control"  name="addNotcadreRegistrationVO.name"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">mobile no</label>
				<input type="text" id="mobilenoId" class="form-control" name="addNotcadreRegistrationVO.mobileno"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">house no</label>
				<input type="text" id="HouseId" class="form-control" name="addNotcadreRegistrationVO.houseno"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">gender</label>
				<select class="chosenSelect" id="genderId" name="addNotcadreRegistrationVO.gender">
					<option value="0">Select Gender</option>
					<option value="Male">Male</option>
					<option value="Female">female</option>
				</select>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">age</label>
				<input type="text"  id="ageId" class="form-control" name="addNotcadreRegistrationVO.age"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">date of birth</label>
				<div class="input-group">
					<input type="text"  id="DOBId" class="form-control" name="addNotcadreRegistrationVO.dob" />
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
				</div>
				
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">relative name</label>
				<input type="text" id="relativenamebyId" class="form-control" name="addNotcadreRegistrationVO. relativename"/>
			</div>
	<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">relation type</label>
				<select class="chosenSelect" id="relativetypeId" name="addNotcadreRegistrationVO. relativetype">
					<option></option>
				</select>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">image</label>
				<input type="file" id="imageurlId" class="form-control" name="file"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">caste</label>
				<!--<input type="text" id="casteId" class="form-control" name="addNotcadreRegistrationVO. castestateId"/> -->
				 <select id="casteId" class="chosenSelect" name="addNotcadreRegistrationVO.castestateId">
                        	
                        </select>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">address1</label>
				<input type="text" id="address1Id" class="form-control" name="addNotcadreRegistrationVO.address1"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
				<label class="text-capitalize">address2</label>
				<input type="text" id="address2Id" class="form-control" name="addNotcadreRegistrationVO.address2"/>
			</div>
			
			<div id="searchMemberDiv">
                    <div class="col-md-2 col-xs-12 col-sm-6 col-lg-2 m_top10">
                    	<label>State</label>
                        <select id="statesDivId"  onchange="getDistrictsForStatesForNotCadre(this.value);" class="chosenSelect" name="addNotcadreRegistrationVO.stateId">
                        	<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="36">Telangana</option>
                        </select>
                    </div>
					
			<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 districtShowCls">
									<label>District</label>
									<select class="chosenSelect " onchange="getConstituenciesForDistrictsForNotcadre(this.value);" id=
									"notCadreDistId" name="addNotcadreRegistrationVO.districtId">
								</select>
						</div>
					
					<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 constituencyShowCls">
									<label>Constituency</label>
									<select class="chosenSelect" onchange="getMandalCorporationsByConstituencyForNotcadre(this.value);" id="notCadreConstId"  name="addNotcadreRegistrationVO.ConstituencyId">
									</select>
						</div>
					<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 mandalShowCls">
									<label>Mandal/ Muncipality / Corporation</label>
									<select  class="chosenSelect" onchange="getPanchayatWardByMandalForNotcadre(this.value);" id="notCadreMandlId" name="addNotcadreRegistrationVO.mandalId">
									
									</select>
							</div>
					<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10 panchayatShowCls">
									<label>Panchayat/ Ward / Division/City</label>
									<select  class="chosenSelect" id="notCadrePanchayatId" name="addNotcadreRegistrationVO.panchayatId">
										
									</select>
								</div>
					
					
					
		<div class="col-md-3 col-xs-12 col-sm-3">
				<label class="text-capitalize">pincode</label>
				<input class="form-control" id="pincodeId" type="text" name="addNotcadreRegistrationVO.pincode"/>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="saveID" onclick="saveNotCadreDetails();">Add Candidate</button>
		<div id="notCadreSavId"></div>
      </div>
    </div>
	</form>
  </div>
</div>
<script>
var globalCadreId=0;
</script>
<script type="text/javascript" src="js/nominatedPosts/nominatedPost.js"></script>
<script type="text/javascript" src="js/nominatedPosts/nominatedPost1.js"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(".dropkickClass").dropkick();
$("#DOBId").daterangepicker({singleDatePicker:true});
$("#DOBId").val(moment().format('MM/DD/YYYY'));

$(document).on("click",".addMemberModal",function(){
	$("#addMemberModalBlock").modal('show');
})
$('.chosenSelect').chosen();
$(document).on("click",".btnClassChange",function(){
	$(this).parent().find(".btnActive").removeClass("btnActive");
	$(this).addClass("btnActive");
});
$(".referenceModal").click(function(){
	commontdpCadreIds = [];
	clearAssignFields();
	$("#myModal").modal('show')
});
$("#membersDetailsTable").dataTable();
$(document).on("change","#addStateId",function(){
  getDistrictsForReferPopup();
});
$(document).on("change","#addDistrictId",function(){
  getConstituenciesForDistrict();
});
$(document).on("change","#addConstituencyId",function(){
  getMandalsByConstituencyForReferPopup();
});
$(document).on("change","#addMandalsId",function(){
  getPanchayatsForMandal();
});
$(".changHeading").html("SELECT REFER DETAILS<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>")

</script>
</body>
</html>
