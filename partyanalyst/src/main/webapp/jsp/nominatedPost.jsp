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
                </div>
                <div class="row">
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
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-3 col-lg-3 pad_left0">
                                    <button class="btn btn-success btn-block btnSearch" id="searchbtn" onclick="getNominatedPostApplication(0)">SEARCH</button>
                                </div>
                            </div>
                        </div>
                     </div>
                 </div>
                 <div class="row">
				 <img style="margin-left: 400px; margin-top: 20px; width: 200px; height: 150px; display: none;" id="searchDataImg" class="offset7" src="images/icons/cadreSearch.gif">
                    <div class="col-md-12 col-xs-12 col-sm-12">
                    		<!--<h4 class="m_0 text-success">APPLICANT PROFILE DETAILS</h4>-->
                        	<div id="searchData"></div>
                        	<div class="scroll-div">
                                <ul class="list-inline best-matched-profile " id="cadreSearchDtls">
                                </ul>
                             </div>
                             <p class="text-muted"><small>Note: Please select matches profile</small></p>
	                    </div>
                    <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<div class="bg_ff pad_10">
                        	<h4 class="panel-title">APPLIED POSTS FOR THE SELECTED PROFILE</h4>
                            <div class="row">
                            	<div class="col-md-6 col-xs-12 col-sm-6 col-lg-6">
                                	<div class="panel panel-default panelPost">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">Nominated Post</h4>
                                        </div>
                                        <div class="panel-body">
                                        	<ul class="ulPost">
                                            	<li>
                                                	<p class="labelStatus shortlisted">Shortlisted</p>
                                                	State Level → Backward Class Welfare → BC Commision Chairman:POSITION</li>
                                                <li>
                                                	<p class="labelStatus finalised">Finalised</p>
                                                	State Level → Backward Class Welfare → BC Commision Chairman:POSITION</li>
                                                <li>
                                                	<p class="labelStatus completed">GO Passed</p>
                                                	State Level → Backward Class Welfare → BC Commision Chairman:POSITION</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-xs-12 col-sm-6 col-lg-6">
                                	<div class="panel panel-default panelPost">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">Nominated Post</h4>
                                        </div>
                                        <div class="panel-body">
                                        	<ul class="ulPost">
                                            	<li>State Level Backward Class Welfare</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<div class="panel panel-default panelNewDepartments">
                        	<div class="panel-heading">
                            	<h4 class="panel-title">DO YOU WANT TO CHANGE YOUR ADDRESS OR PHONE NUMBER <input type="checkbox"/></h4>
                            </div>
                            <div class="panel-body bg_ff pad_10">
                            	<div class="row">
                                   	<div class="col-md-4 col-lg-4 col-sm-5 col-xs-11">
                                    	<label>CHANGE PHONE NUMBER</label>
                                    	<div class="input-group">
                                        	<input type="text" class="form-control"/>
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
                                        <input type="text" class="form-control"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Address Lane 1</label>
                                        <input type="text" class="form-control"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Address Lane 2</label>
                                        <input type="text" class="form-control"/>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>State</label>
                                        <select class="chosenSelect">
                                        	<option>State</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                	<div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>District</label>
                                        <select class="chosenSelect">
                                        	<option>District</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Constituency</label>
                                        <select class="chosenSelect">
                                        	<option>Constituency </option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Village</label>
                                        <select class="chosenSelect">
                                        	<option>Village Name</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 col-lg-3 m_top10">
                                    	<label>Pincode</label>
                                        <input type="text" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<div class="bg_ff pad_10">
                        	<div class="row">
                            	<div class="col-md-4 col-sm-4 col-xs-12 m_top10 col-lg-4">
                                	<h4 class="panel-title">PLEASE SELECT APPLYING POST TYPE</h4>
                                </div>
                                <div class="col-md-5 col-sm-5 col-xs-12 col-lg-5">
                                	<div class="btn-group">
                                      <button type="button" class="btn btnClassChange btnNewCustom btnActive">Nominated Post</button>
                                      <button type="button" class="btn btnClassChange btnNewCustom">Party Post</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top20">
                    	<h4 class="m_0 text-success">NOMINATED LEVEL AND LOCATION 
							<span class="pull-right f_14" style="cursor:pointer"  id="addOneMore">
								Add One More 
								<i class="glyphicon glyphicon-plus-sign"></i>
							</span>
						</h4>
                    </div>
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
						<div class="addBlockNew cloneBlockDiv">
							<div class="row">
								<span class="iconClose" style="display:none;cursor:pointer;">
									<i class="glyphicon glyphicon-remove"></i>
								</span>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Nominated Post Level</label>
									<select class="chosenSelect boardLvlCls" id="boardLvlId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>State Name</label>
									<select class="chosenSelect nominatedStaeCls" onchange="getDistrictsForStates(this.value,this.id,'');" id="nominatedStaeId">
										<option value="0">Select State</option>
										<option value="1">Andhra Pradesh</option>
										<option value="36">Telangana</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>District</label>
									<select class="chosenSelect nominatedDistCls" onchange="getConstituenciesForDistricts(this.value,this.id,'');" id=
									"nominatedDistId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Constituency</label>
									<select class="chosenSelect nominatdConstCls" onchange="getMandalCorporationsByConstituency('',this.id);" id="nominatdConstId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Mandal/ Muncipality / Corporation</label>
									<select class="chosenSelect nominatedMandlCls" onchange="getPanchayatWardByMandal('',this.id);" id="nominatedMandlId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Panchayat/ Ward / Division</label>
									<select class="chosenSelect nominatedPanchayatCls" id="nominatedPanchayatId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Department Name</label>
									<select class="chosenSelect depmtsCls"  id="depmtsId" onchange="getDepartmentBoards('');">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Corporation/Board Name</label>
									<select class="chosenSelect deptBoardCls" id="deptBoardId" onchange="getDepartmentBoardPositions('');">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Position Name</label>
									<select class="chosenSelect deptBoardPostnCls" id="deptBoardPostnId">
										
									</select>
								</div>
							</div>
						</div>
						<div class="addBlockNew cloneBlockDiv" id="cloneDivBlock" style="display:none;">
							<div class="row">
								<span class="iconClose" style="display:none;cursor:pointer;">
									<i class="glyphicon glyphicon-remove"></i>
								</span>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Nominated Post Level</label>
									<select class="boardLvlCls" id="boardLvlId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>State Name</label>
									<select class="nominatedStaeCls" onchange="getDistrictsForStates(this.value,this.id,'');" id="nominatedStaeId">
										<option value="0">Select State</option>
										<option value="1">Andhra Pradesh</option>
										<option value="36">Telangana</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>District</label>
									<select class="nominatedDistCls" onchange="getConstituenciesForDistricts(this.value,this.id,'');" id=
									"nominatedDistId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Constituency</label>
									<select class="nominatdConstCls" onchange="getMandalCorporationsByConstituency('',this.id);" id="nominatdConstId">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Mandal/ Muncipality / Corporation</label>
									<select class="nominatedMandlCls" onchange="getPanchayatWardByMandal('',this.id);" id="nominatedMandlId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Panchayat/ Ward / Division</label>
									<select class="nominatedPanchayatCls" id="nominatedPanchayatId">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Department Name</label>
									<select class="depmtsCls"  id="depmtsId" onchange="getDepartmentBoards('');">
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Corporation/Board Name</label>
									<select class="deptBoardCls" id="deptBoardId" onchange="getDepartmentBoardPositions('');">
										
									</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6 col-lg-4 m_top10">
									<label>Position Name</label>
									<select class="deptBoardPostnCls" id="deptBoardPostnId">
										
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
						<div id="addOneMoreBlock"></div>
					</div>
                </div>
                <div class="row">
                     	<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                        	<p>UPLOAD SCAN COPY</p>
                            <input type="file" class="form-control"/>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12 col-lg-4 col-lg-offset-4 m_top20">
                        	<button class="btn btn-success btn-block">SUBMIT APPLICATION</button>
                        </div>
                    </div>
              </div>
      	  </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/nominatedPosts/nominatedPost.js"></script>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript">
$('.chosenSelect').chosen();
$(document).on("click",".btnClassChange",function(){
	$(".btnClassChange").removeClass("btnActive");
	$(this).addClass("btnActive");
});
$(document).on("click",".iconClose",function(){
	$(this).closest(".addBlockNew").remove()
});
var cloneCount=0;

$(document).on("click","#addOneMore",function(){
  var e = $("#cloneDivBlock").clone();
  e.removeClass("cloneBlockDiv")
  e.attr("id",'block'+cloneCount);
  e.attr("attr_count",cloneCount);
  e.show();
  e.find(".iconClose").show();

  e.find(".boardLvlCls").attr("id","boardLvlId"+cloneCount);
  getBoardLevels("boardLvlId"+cloneCount);
  
  e.find(".nominatedStaeCls").attr("id","nominatedStaeId"+cloneCount);
  e.find(".nominatedStaeCls").attr("onChange",'getDistrictsForStates("",nominatedStaeId'+cloneCount+','+cloneCount+');');
  
  e.find(".nominatedDistCls").attr("id","nominatedDistId"+cloneCount);
  e.find(".nominatedDistCls").attr("onChange",'getConstituenciesForDistricts("",nominatedDistId'+cloneCount+','+cloneCount+');');
  
  e.find(".nominatdConstCls").attr("id","nominatdConstId"+cloneCount);
  e.find(".nominatdConstCls").attr("onChange",'getMandalCorporationsByConstituency('+cloneCount+',nominatdConstId'+cloneCount+');');
  
  e.find(".nominatedMandlCls").attr("id","nominatedMandlId"+cloneCount);
  e.find(".nominatedMandlCls").attr("onChange",'getPanchayatWardByMandal('+cloneCount+',nominatedMandlId'+cloneCount+');');
   
  e.find(".nominatedPanchayatCls").attr("id","nominatedPanchayatId"+cloneCount);
  //e.find(".nominatedPanchayatCls").attr("onChange",'getMandalCorporationsByConstituency('+cloneCount+',nominatedPanchayatId'+cloneCount+');');
   
  e.find(".depmtsCls").attr("id","depmtsId"+cloneCount);
  getDepartments("depmtsId"+cloneCount);
  e.find(".depmtsCls").attr("onChange",'getDepartmentBoards('+cloneCount+');');
  
  e.find(".deptBoardCls").attr("id","deptBoardId"+cloneCount);
  e.find(".deptBoardCls").attr("onChange",'getDepartmentBoardPositions('+cloneCount+');');
  
  e.find(".deptBoardPostnCls").attr("id","deptBoardPostnId"+cloneCount);
  $("#addOneMoreBlock").append(e);
  
  var boardlvl= "boardLvlId"+cloneCount;
  $("#"+boardlvl).chosen();
  
  var nominatedState= "nominatedStaeId"+cloneCount;
  $("#"+nominatedState).chosen();
  
  var nomintdDist= "nominatedDistId"+cloneCount;
  $("#"+nomintdDist).chosen();
  
  var nomintedConst= "nominatdConstId"+cloneCount;
  $("#"+nomintedConst).chosen();
   
  var nomintdMandl= "nominatedMandlId"+cloneCount;
  $("#"+nomintdMandl).chosen();
  
  var nomintdPanchyt= "nominatedPanchayatId"+cloneCount;
  $("#"+nomintdPanchyt).chosen();
  
  var depts= "depmtsId"+cloneCount;
  $("#"+depts).chosen();
  
  var deptBrd= "deptBoardId"+cloneCount;
  $("#"+deptBrd).chosen();
  
  var deptBrdPostn= "deptBoardPostnId"+cloneCount;
  $("#"+deptBrdPostn).chosen();
  
  cloneCount=cloneCount+1;
});
</script>
</body>
</html>
