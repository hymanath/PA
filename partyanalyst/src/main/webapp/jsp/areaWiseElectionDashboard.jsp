<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Election Details</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/custom.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/responsive.css" rel="stylesheet" type="text/css">
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="coreApi/css/bootstrap-multiselect.css" type="text/css">
<link rel="stylesheet" href="coreApi/css/bootstrap-multiselect.css" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<script src="https://use.fontawesome.com/e94c241642.js"></script>
</head>
<body>
<div class="background-head"></div>
<header>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<img src="coreApi/img/logo.png" class="img-responsive"/>
			</div>
			<div class="col-sm-6">
				<img src="coreApi/img/right_side_head.png" class="img-responsive pull-right"/>
			</div>
		</div>
	</div>
</header>
<main>
	<div class="container m_top30">
		<section>
			<div class="row">
				<div class="col-sm-12">
					<div class="block boderBlock">
						<div class="scrollHeading">
							<h3 class="theme-title-color text-center" style="text-transform: uppercase !important;"><span id="mianHeadingId" ></span> Level Election Results</h3>
						</div>
						<div id="electionScopeDivIds" class="m_top10"></div>
						<div class="row">
							<div class="col-sm-3 m_top10">
								<label class="radio-inline">
								  <input type="radio" id="mainId" value="MAIN" name="optionsRadios2" class="electionSubTypeCls" checked attr_type="partyTrends">Main Election
								</label>
								<label class="radio-inline">
								  <input type="radio" id="byeId" value="BYE" name="optionsRadios2"  class="electionSubTypeCls" attr_type="partyTrends">By Election
								</label>
							</div>
							<div class="col-sm-2">
								<select class="" id="electionYearId" multiple>
								</select>
							</div>
							<div class="col-sm-2">
								<select class="form-control" id="partyId" multiple>
									<option value="872" selected>TDP</option>
									<option value="1117" selected>YSRC</option>
									<option value="163" selected>BJP</option>
									<option value="362" selected>INC</option>
									<!--<option value="72">AIMIM</option>-->
									<option value="265" selected>CPI</option>
									<option value="269" selected>CPM</option>
									<option value="662" selected>PRP</option>
									<!--<option value="886">TRS</option>-->
								</select>
							</div>
							<div class="col-sm-2" style="margin-left: 10px;">
								<input value="true" type="checkbox" name="allianceParty" id="allaincePartiFieldId" /><span class="f-12">WITH ALLAINCE</span>
							</div>
							<div class="col-sm-2">
								<button class="btn btn-success getDetailsCls buttonClr">Submit</button>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 m_top10">
								<div id="candidatesResultsDivId"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 m_top10">
								<div id="levelWiseCandidatesResultsDivId"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 m_top10">
								<div id="locationWiseCandidatesResultsDivId"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section class="m_top10">
			<div class="row">
				<div class="col-sm-12">
					<div class="block boderBlock">
					
						<label class="radio-inline text-capital f-13" for="electionPCId">
						  <input type="radio" id="electionPCId" value="1" name="electionScope" class="electionTypeWiseStrongCls">Parliament
						</label>
						<label class="radio-inline text-capital f-13" for="electionACId">
						  <input type="radio" id="electionACId" value="2" name="electionScope"  class="electionTypeWiseStrongCls" checked>Assembly
						</label>
						<label class="radio-inline text-capital f-13" for="electionMPTCId">
						  <input type="radio" id="electionMPTCId" value="3" name="electionScope"  class="electionTypeWiseStrongCls">MPTC
						</label>
						<label class="radio-inline text-capital f-13" for="electionZPTCId">
						  <input type="radio" id="electionZPTCId" value="4" name="electionScope"  class="electionTypeWiseStrongCls">ZPTC
						</label>
						
						<div class="row m_top10">
							<div class="col-sm-3 m_top10">
								<label class="radio-inline">
								  <input type="radio" id="mainId1" value="MAIN" name="optionsRadios" class="electionSubTypeCls" checked attr_type="strong">Main Election
								</label>
								<label class="radio-inline">
								  <input type="radio" id="byeId1" value="BYE" name="optionsRadios"  class="electionSubTypeCls" attr_type="strong">By Election
								</label>
							</div>
							<div class="col-sm-2">
								<label>Election Year</label>
								<select class="" id="electionYearForStrongId" multiple>
								</select>
							</div>
							<div class="col-sm-2">
								<label>Party</label>
								<select class="form-control chosen-select" id="partyIdForStrongBlock">
									<option value="872" selected>TDP</option>
									<option value="1117" >YSRC</option>
									<option value="163" >BJP</option>
									<option value="362" >INC</option>
									<!--<option value="72">AIMIM</option>-->
									<option value="265" >CPI</option>
									<option value="269" >CPM</option>
									<option value="662" >PRP</option>
									<!--<option value="886">TRS</option>-->
								</select>
							</div>
							<div class="col-sm-2 searchLevelCls">
								<label>Search Level</label>
								<select class="form-control chosen-select addSearchValCls" id="searchLevelId">
									<option value="district" selected>District</option>
									<option value="constituency">Constituency</option>
									<option value="parliament">Parliament</option>
								</select>
							</div>
							<div class="col-sm-2 m_top20">
								<input value ="true" type="checkbox" name="allianceParty" id="allainceStatusPartyFieldId" class="checkedMainByeType" /><span class="f-12">WITH ALLAINCE</span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-3 pull-right">
								<button class="btn btn-success buttonClr getDetailsForStrongCls pull-right">Submit</button>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div id="locationWiseStrongVsPoor"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section class="m_top10">
			<div class="row">
				<div class="col-sm-12">
					<div class="block boderBlock">
						<div class="row m_top10">
							<div class="col-sm-3 m_top10">
								<label class="radio-inline">
								  <input type="radio" id="mainId2" value="MAIN" name="optionsRadios6" class="electionSubTypeCls" checked attr_type="cross">Main Election
								</label>
								<label class="radio-inline">
								  <input type="radio" id="byeId2" value="BYE" name="optionsRadios6"  class="electionSubTypeCls" attr_type="cross">By Election
								</label>
							</div>
							<div class="col-sm-2">
								<label>Election Year</label>
								<select class="chosen-select" id="electionYearForCrossId">
								</select>
							</div>
							<div class="col-sm-2">
								<label>Party</label>
								<select class="form-control chosen-select" id="partyIdForCrossBlock">
									<option value="872" selected>TDP</option>
									<option value="1117" >YSRC</option>
									<option value="163" >BJP</option>
									<option value="362" >INC</option>
									<!--<option value="72">AIMIM</option>-->
									<option value="265" >CPI</option>
									<option value="269" >CPM</option>
									<option value="662" >PRP</option>
									<!--<option value="886">TRS</option>-->
								</select>
							</div>
							<div class="col-sm-3 m_top20">
								<button class="btn btn-success buttonClr getDetailsForCrossVotingCls">Submit</button>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div id="crossVotingDetailsBlockId"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section class="m_top10">
			<div class="row">
				<div class="col-sm-12">
					<div class="block boderBlock">
						<ul class="switch-btn-New" role="tabSwitch">
							<li class="active activeCls" attr_val="2">Assembly Election</li>
							<li  attr_val="1">Parliament Election</li>
							<!--<li  attr_val="0">Both</li>-->
						</ul>
						<div class="row m_top10">
							<div class="col-sm-3 m_top10">
								<label class="radio-inline">
								  <input type="radio" id="mainIdB" value="MAIN" name="optionsRadiosB" class="electionSubTypeCls" checked attr_type="booth">Main Election
								</label>
								<label class="radio-inline">
								  <input type="radio" id="byeIdB" value="BYE" name="optionsRadiosB"  class="electionSubTypeCls" attr_type="booth">By Election
								</label>
							</div>
							<div class="col-sm-2">
								<label>Election Years</label>
									<select class="chosen-select" id="electionYearBoothWiseId">
									</select>
							</div>
							<div class="col-sm-2">
								<label>Constituency</label>
								<select class="chosen-select" id="assemblyConsBoothWiseId">
									<option value="0">Select Constituency</option>
								</select>
							</div>
							<div class="col-sm-2">
								<label>Party</label>
								
									<select class="form-control" multiple id="partyBoothWiseId">
										<option value="0">All Parties</option>
										<option value="872" selected>TDP</option>
										<option value="1117" selected>YSRC</option>
										<option value="163" selected>BJP</option>
										<option value="362" selected>INC</option>
										<!--<option value="72">AIMIM</option>-->
										<option value="265">CPI</option>
										<option value="269">CPM</option>
										<option value="662">PRP</option>
										<!--<option value="886">TRS</option>-->
									</select>
								
							</div>
							<div class="col-sm-2 m_top20">
								<button class="btn btn-success buttonClr getDetailsForBoothWiseCls">Submit</button>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div id="boothWiseResultsMainBlockId"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 m_top10">
								<div id="hideShowlocationId"></div>
								<div id="locationWiseBoothResultsId"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div id="boothWiseResultsBlockId"></div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</section>
	</div>
</main>
<div class="modal fade" id="openModalDivId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:90%;margin:auto">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#CCCCCC">
				<div class="row" >
					<div class="col-md-8">
						<h3 class="theme-title-color" id="titleId" >Title</h3>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
			</div>
			<div class="modal-body">        
				<div class="row">
					<div class="col-sm-12">
						<div id="votingDetailsSubLevelBlockId"></div>
					</div>  
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script type="text/javascript" src="coreApi/js/bootstrap-multiselect.js"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="coreApi/js/electionConstituencyPage.js" type="text/javascript"></script>
<script type="text/javascript">
var locationLevelId = "${param.locationLevelId}";
var constituencyId = "${param.constituencyId}";
var districtId = "${param.districtId}";
var locationName = "${param.locationName}";
var parliamentId = "${param.parliamentId}";
var locationLevelName = "${param.locationLevelName}";
var userAccessLevelValue = "${param.userAccessLevelValuesArray}";
var userAccessLevelValuesArray =[];
	userAccessLevelValuesArray.push(userAccessLevelValue);
</script>
</body>
</html>