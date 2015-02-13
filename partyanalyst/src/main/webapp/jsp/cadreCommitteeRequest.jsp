<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Request</title>
	
    <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->

	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommitteeRequest/cadreCommitteeRequest.js"></script>

	<style>
     	#committeeLocationIdErr,#committeeTypeIdErr,#afflitCommitteeIdErr,#committeePositionIdErr,#maxPositionsErrId
	    {
		 font-weight:bold;
		 color:red;
	   }
	   .table-bordered > thead > tr > th,
	.table-bordered > tbody > tr > th,
	.table-bordered > tfoot > tr > th,
	.table-bordered > thead > tr > td,
	.table-bordered > tbody > tr > td,
	.table-bordered > tfoot > tr > td {	border: 1px solid #C2A932;}
	
.table-yellow-bordered > thead > tr > th,
	.table-yellow-bordered > tbody > tr > th,
	.table-yellow-bordered > tfoot > tr > th,
	.table-yellow-bordered > thead > tr > td,
	.table-yellow-bordered > tbody > tr > td,
	.table-yellow-bordered > tfoot > tr > td {	border: 1px solid #ebd621;}
	</style>
</head>

<body>
		<!--<header style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-bottom:6px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12 text-center">
				<img src="images/cadreCommitee/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
			<div class="col-md-3  col-xs-3 col-sm-3">
				<a href="#" class="dropdown-toggle btn btn-default btn-xs m_top20" data-toggle="dropdown" aria-expanded="false" style="margin-top: 60px;">
				Menu <img src="images/menu_icon.png" />
				</a>
				<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);top: 91px;">
				  <li><a tabindex="-1" href="cadreCommitteeAction.action">Home</a></li>
				  <li><a tabindex="-1" href="cadreCommitteeSummaryAction.action">Summary Report</a></li>
				  <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
				  <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
				</ul>
            </div>
		</header>-->
        <div class="container">
        	<!--Content Start-->
            <div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3">
                    <h3 class="panel-header">REQUEST</h3>
                    <hr style="border-color:#F00;margin-top:10px;" />
                </div>
            </div>
            <!-- First Block Start-->
           <div class="row">
           		<div style="border-bottom: 1px solid rgb(255, 255, 255); padding: 10px;"class="col-md-offset-1 text-center col-md-10">
					<label id="incId" class="radio-inline" style="padding: 10px 10px 10px 30px; background: none repeat scroll 0% 0% rgb(255, 153, 102); margin-left: -3px;"><input type="radio" name="requestType" value="1" id="reqIncPosId" checked="true" onClick="hideDetails('1');clearResult();"/>Request for committee member new position</label>
                    <label id="desgId" class="radio-inline"  style="padding: 10px 10px 10px 30px; background: none repeat scroll 0% 0% rgb(255, 153, 102); margin-left: -3px;"><input type="radio" name="requestType" value="2" id="reqChangeDesgId" onClick="hideDetails('2');clearResult();"/>Request for committee members change position</label>
					<label id="viewId" class="radio-inline"  style="padding: 10px 10px 10px 30px; background: none repeat scroll 0% 0% rgb(255, 153, 102); margin-left: -3px;"><input type="radio" name="requestType" value="3" id="viewRequestId" onClick="hideDetails('3');clearResult();" />View Request Status</label> 
                </div>
            </div>
			
			  <input type="hidden" value="1" id="areaTypeId"/>
			  <!--location radio buttons -->
		<div class="row" id="locationsDivId">
			<div class="row m_top20">
			    <div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 ">
				  <div class="radio pull-right"><label><input type="radio" name="committeeType" onclick="validateSearchType('1');getCommitteeLocations();clearResult();"  value="1" id="villageId" checked="true"> Village / Ward</label></div>
			    </div>
			    <div class="col-md-4 col-sm-6 col-xs-6">
			      <div class="radio">           <label><input type="radio" name="committeeType" onclick="validateSearchType('2');getCommitteeLocations();clearResult();" value="2" id="mndlLvlCommittSelec"/> Mandal / Town / Division </label></div>
			    </div>
			</div> 
	  </div>
	     
		   <!-- locations,commitees,designations drop down boxes list -->
           <div class="row m_top20" id="committeesId">
                  <div class="col-md-3" id="selLocId">Select location<br/>
                	<select id="committeeLocationId" class="form-control" onchange="populateDefaultValue(1);clearResult();"><option value="0">Select Location</option></select>
                    <div id="committeeLocationIdErr"></div>
				  </div>
				
                  <div class="col-md-3" id="selCommId">Committee Type<br/>
                    <select id="committeeTypeId" class="form-control" onchange="getAffiliatedCommitsForALoc();populateDefaultValue(2);getCommitteCadreMembersInfo(1);clearResult();"><option  value="0">Select Committee Type</option><option value="1">Main Committee</option><option value="2">Affiliated Committee</option></select>
                    <div id="committeeTypeIdErr"></div>
				  </div>
			    
                 <div id="committeeMainId" class="col-md-3">Affliated Committee<br/>
                   <select id="afflitCommitteeId" class="form-control" onchange="getCommitteCadreMembersInfo(2);clearResult();"><option>Select Affiliated Committee</option></select>
                   <div id="afflitCommitteeIdErr"></div>
				 </div>
				
                 <div id="designationDivId" class="col-md-3">Committee Designation<br/>
                    <select id="committeePositionId" class="form-control" name="eligibleRoles[0].cadreRoleId" onChange="checkDesgValidation();clearResult();"><option value="0">Select Designation </option></select>
                    <div id="committeePositionIdErr"></div>
				 </div>
		 </div>
		 <div class="row" id="reqSubmitId">
               <div class="col-md-offset-5 col-md-3 m_top20">
                	<button id="submitRequestId" class="btn btn-success btn-lg" onClick="getCommitteMembersInfoRequest()">Submit</button>
               </div>
		</div>
		  
		
		  
			
            <div class="row m_top20" id="posIncreasedId">
            </div>
			
			 <div class="row m_top20" id="changeDesgId">
			   <div class="row" id="desgReqTableDiv"></div>
			   <div class="row" id="desgReqDiv"></div>
             </div>
			 <div class="row m_top20" id="changeDesgnationsErrId">
			  <div> <img src="images/Loading-data.gif" class="offset7"  id="changDesgImageid" style=" margin-left:252px;margin-top: 20px;width:70px;height:60px;display:none;"/></div>
			 </div>
			 <div class="row" id="resultStatusId">
			    <img src='images/Loading-data.gif' class="offset7"  id="processImgId" style=" margin-left:513px;margin-top: 20px;width:70px;height:60px;display:none;"/>
				
				<div id="resultReportId" style="margin-top: 17px;"></div>
             </div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
function clearResult(){
	$("#posIncreasedId").html("");
	$("#desgReqDiv").html("");
    $("#desgReqTableDiv").html("");
}
getCommitteeLocations();
</script>
</body>
</html>
