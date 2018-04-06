<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area Incharge</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="D2D_Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="D2D_Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="D2D_Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<script src="D2D_Assests/Plugins/Less/less.js"></script>
<link href="D2D_Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style type="text/css">
.panel
{
	box-shadow:0 5px 8px 2px rgba(0, 0, 0, 0.3);
}

body{
	background-color:#fff !important;
}
.popover{
    max-width: 500px;
}
#menu_multilevelpushmenu{box-shadow: none;}
</style>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-12 m_top10">
				<div class="panel panel-default" style="box-shadow: none;">
					<div class="panel-heading" role="tab" style="background-color: #ddd;padding: 12px;">
						<h4 class="panel-title text-capital text-center">Area Incharges</h4>
						<h5 class="panel-title text-capital text-center font_weight m_top5" id="locationNameId"></h5>
					</div>
					<div class="panel-body">
						<div id="mainOverViewConstituencyBlockDivId"></div>
						<div class="row">
							<div class="col-sm-3 m_top10 constituencySelectBoxCls">
								<label>Constituency</label>
								<select class="form-control chosen-select" id="constituencyId">
								</select>
							</div>
						</div>
						<div id="mainOverDistrictViewBlockDivId" class="m_top10"></div>	
						
						<div id="alreadyAreaInchargeDetailsDivId"></div>
						
						<div class="row">
							<div class="col-sm-5 pull-right m_top10">
								<button class="btn btn-success btn-sm pull-right showHideAddAreaIncharge" style="width: 150px;">ADD</button>
							</div>
						</div>
						<div class="row addAssignedBlockCls" style="display:none;">
							<div class="col-sm-12">
								<h4 class="text-capital" style="text-align: center;font-size: 22px;">ADD Incharge And Assigned Booths</h4>
							</div>
							<div class="col-sm-12 m_top10">
								<div class="row">
									<div class="col-sm-12">
										<label class="radio-inline">
										  <input type="radio"  checked="true" name="inlineRadioOptions" id="inlineRadio1" value="membershipId"  class="getSelectedVal">Membership Id
										</label>
										<label class="radio-inline">
										  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="voterId" class="getSelectedVal" >Voter Id
										</label>
										<label class="radio-inline">
										  <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="mobileNo" class="getSelectedVal" >Mobile No
										</label>
									</div>
								</div>
							</div>
							<div id="searchErrDiv" style ="color:red"></div>
							<div class="col-sm-12 m_top10">
								<div class="row">
									<div class="col-sm-8">
										<input type="text"  id="searchValId" class="form-control" placeholder="Enter Membership/Voter/MobileNo" style="height: 50px;border-radius: 0px;">
									</div>
									<div class="col-sm-4">
										<button class="btn btn-success btn-sm getSearchDetailsCls" style="padding: 15px;border-radius: 0px;">SEARCH</button>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 showHideCls">
								<div id="areaInchargeSearchDetailsDivId"></div>
							</div>
						</div>
						<div class="row assignedUnAssignedDivCls m_top10" style="display:none;">
							<div id="areaInchargeassignedBoothsDivId"></div>
						</div>
						<div class="row assignedUnAssignedDivCls" style="display:none;">
							<div id="areaInchargeUnAssignedboothsDivId"></div>
						</div>
					</div>
				</div>
					<div class="row">
						<div class="col-sm-1 pull-right">
							<img src="D2D_Assests/images/spinner.gif" style="width:40px;height:40px;display:none;" class="spinnerDivId"></img>
						</div>
						<div class="col-sm-5 pull-right">
							<button class="btn btn-success btn-sm pull-right submitBtnCls" style="width: 150px;display:none;">Submit</button>
						</div>
					</div>
				</div>
		</div>
	</div>

<div id="popupViewAreaIncDetModal" class="modal fade" role="dialog" style="z-index:9999">
  <div class="modal-dialog" style="width:80%;">
	 <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title"> Edit Details </h4>
        </div>
		  <div class="modal-body">
				<div id="editAreaInchargeDetailsDivId"></div>
		  </div>
			</div>
		</div>
</div>
<div id="popupSuccessModalDivId" class="modal fade" role="dialog" style="z-index:9999">
  <div class="modal-dialog" style="width:40%;">
	 <div class="modal-content">
		  <div class="modal-body" style="padding: 35px;">
				<div class="row">
					<div class="col-sm-12">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="font-size:22px;color:red;"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12"><div id="successMsgDivId"></div></div>
				</div>
				
		  </div>
			</div>
		</div>
</div>
<script src="D2D_Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="D2D_Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="D2D_Assests/js/areaInchargeDashboard.js" type="text/javascript"></script>
<script type="text/javascript">
	/* var globalLevelId="${sessionScope.levelId}";
	var globalLevelValue="${sessionScope.levelValue}";
	var globalLevelValueStored="${sessionScope.levelValue}";
	var globalLocationName="${sessionScope.locationName}";
	var globalLevelType=''; */
	var globalLevelId='${levelId}';
	var globalLevelValue="${levelValue}";
	var globalLevelValueStored="${levelValue}";
	var globalLocationName="${locationName}";
	var globalLevelType='';
		
</script>
</body>
</html>