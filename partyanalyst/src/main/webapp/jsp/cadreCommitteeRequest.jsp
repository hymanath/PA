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
	</style>
</head>

<body>
		<header style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-bottom:6px solid rgba(19,167,81,0.8);display:flex">
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
				  <li><a tabindex="-1" href="cadreCommitteeRequestAction.action">Request For Positions Increase</a></li>
				  <li><a tabindex="-1" href="committeeDashBoardAction.action">Committee DashBoard</a></li>
				  <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
				  <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
				</ul>
            </div>
		</header>
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
           		<div class="col-md-offset-2 col-md-10">
						<label class="radio-inline"><input type="radio" name="requestType" value="1" id="reqIncPosId" checked="true" onClick="hideDetails('1')"/>Request for committee member new position</label>
                        <label class="radio-inline"><input type="radio" name="requestType" value="2" id="reqChangeDesgId" onClick="hideDetails('2')"/>Request for committee members change position</label>
						<label class="radio-inline"><input type="radio" name="requestType" value="3" id="viewRequestId" onClick="hideDetails('3')" />View Request Status</label> 
                </div>
            </div>
			
			  <input type="hidden" value="1" id="areaTypeId"/>
			  <!--location radio buttons -->
		<div class="row" id="locationsDivId">
			<div class="row m_top20">
			    <div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 ">
				  <div class="radio pull-right"><label><input type="radio" name="committeeType" onclick="validateSearchType('1');getCommitteeLocations();"  value="1" id="villageId" checked="true"> Village / Ward</label></div>
			    </div>
			    <div class="col-md-4 col-sm-6 col-xs-6">
			      <div class="radio">           <label><input type="radio" name="committeeType" onclick="validateSearchType('2');getCommitteeLocations();" value="2" id="mndlLvlCommittSelec"/> Mandal / Town / Division </label></div>
			    </div>
			</div> 
	  </div>
		   <!-- locations,commitees,designations drop down boxes list -->
           <div class="row m_top20" id="committeesId">
                  <div class="col-md-3">Select location<br/>
                	<select id="committeeLocationId" class="form-control" onchange="populateDefaultValue(1);"><option value="0">Select Location</option></select>
                    <div id="committeeLocationIdErr"></div>
				  </div>
				
                  <div class="col-md-3">Committee Type<br/>
                    <select id="committeeTypeId" class="form-control" onchange="getAffiliatedCommitsForALoc();populateDefaultValue(2);getCommitteCadreMembersInfo(1)"><option  value="0">Select Committee Type</option><option value="1">Main Committee</option><option value="2">Affiliated Committee</option></select>
                    <div id="committeeTypeIdErr"></div>
				  </div>
			    
                 <div id="committeeMainId" class="col-md-3">Affliated Committee<br/>
                   <select id="afflitCommitteeId" class="form-control" onchange="getCommitteCadreMembersInfo(2)"><option>Select Affiliated Committee</option></select>
                   <div id="afflitCommitteeIdErr"></div>
				 </div>
				
                 <div id="designationDivId" class="col-md-3">Committee Designation<br/>
                    <select id="committeePositionId" class="form-control" name="eligibleRoles[0].cadreRoleId" onChange="checkDesgValidation()"><option value="0">Select Designation </option></select>
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
             </div>
			 <div class="row" id="resultStatusId">
            	
           </div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
getCommitteeLocations();
</script>
</body>
</html>
