<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> VIEW PETITIONS DETAILS </title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<!--<link href="Assests/Plugins/sliderbar/bootstrap-slider.css" rel="stylesheet" type="text/css"/>-->
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>

 <link rel="stylesheet" type="text/css" href="Assests/SimplePagination/simplePagination.css"/>
 <style>
	.bg_light-Color{
		background-color: #EBEBEB;
	}
	.block_padding_10{
		padding: 10px;
		border: 1px solid lightgrey;
	}
	.viewDivId{
		background-color:#455963;
		color: #fff;
		font-weight:bold;
		padding: 10px;
		
	}
	.error_colorCls{
   color: red;
}
	
 </style>
</head>
<body>

<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="bg_backGroundMain">
					<div class="container-fluid">
						<div class="row">
							<div class="text-center col-sm-11">
								<img src="Assests/images/Group 2.png" class="" alt="logo">
							</div>
							<div class="col-sm-1 m_top20">
								<i class="glyphicon glyphicon-th menu-cls pull-right" style="top:8px;"></i>
							
								<div class="menu-data-cls">
									<div class="arrow_box_top">
										<div class="row">
											<div style="padding:10px;">
												<div class="col-sm-6">
													<h4><a target="_blank" href="representationRequestEntry">ADD PETITION</a></h4>
												</div>
												<div class="col-sm-6">
													<h4 class=""><a target="_blank" href="representationRequestEntry">VIEW PETITION</a></h4>
												</div>
											</div>
										</div>
										<div class="row">
											<div style="padding:10px;">
											<div class="col-sm-12">
												<a class="btn btn-primary btnSearch m_top5 pull-right" href="petitionsLogout" style="display:inline-block" style="cursor:pointer;">LOGOUT</a>
												
											</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
</header>
<main>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel-group">
						<div class="panel panel-default panel-lightGreen">
							<div class="panel-heading">
								<h4 class="panel-title f_22" style="font-weight:normal !important;">REPRESENTATION REQUEST</h4>
							</div>
								<div class="panel-body">								
									<div class="row m_top20">
										<div class="col-sm-12">
									<!--	<div class="col-sm-9">	
											<label class="radio-inline ">
												<input  class="searchCls m_top0" type="radio" name="optradio" value="designation" checked> <span title="Refferer / Representee Designation wise"> REF./REPR. DESIGNATION WISE</span>
											</label>											
											<label class="radio-inline">
												<input  class="searchCls m_top0" type="radio" name="optradio" value="department" >DEPARTMENT WISE
											</label>
											<label class="radio-inline ">
												<input class="searchCls m_top0" type="radio" name="optradio" value="refLocation" >REFFERRE LOCATION WISE
											</label>
											<label class="radio-inline ">
												<input class="searchCls m_top0"  type="radio" name="optradio" value="workLocation" >WORK LOCATION WISE
											</label>
											<label class="radio-inline ">
												<input class="searchCls m_top0"  type="radio" name="optradio" value="advanceBtnId" >ADVANCED SEARCH 
											</label>
										</div>  -->
										<div class="col-sm-3 pull-right">	
											<div class="input-group inline-block">
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
												</span>
												<input type="text"  class="form-control" id="dateRangePicker"/>
											</div>
										</div>
										<div class="col-sm-12 m_top20" style="border:1px solid lightgrey;padding-top:10px;padding-bottom:10px">
										<div class="error_colorCls" id="errMsgId"></div>
											<div class="col-sm-3" id="locationDivlId">	
												<label> Search By</label>
												<select class="form-control chosen-select clearDataCls" id="locationSelId" >
													<option value="all">All</option>
													<option value="work"> Work Location wise</option>
													<option value="representee"> Representee Location wise </option>
													<option value="referral"> Referral Location wise</option>
													<option value="referrelDesignation"> Referral Designation wise </option>
													<option value="representeeDesignation"> Representee Designation wise </option>
													<option value="department">Department wise </option>
													<option value="name"> Name</option>
													<option value="mobile"> Mobile No</option>
													<option value="email"> Email</option>
													<option value="endorsmentNO"> Endorsment No </option>
																
												</select>
												<div class="error_colorCls" id="locationErrDivlId"></div>
											</div>
											<div class="col-sm-3" id="designationDiv" style="display:none;">
												<label> Designation </label>
												<select class="form-control chosen-select v" id="designationsId">
													<option value="0">Select Designation</option>
												</select>
												<div class="error_colorCls" id="designationErrDiv"></div>
											</div>
											<div class="col-sm-3" id="departMentsDiv" style="display:none;">
											<label> Department</label>
												<select class="form-control chosen-select clearDataCls" id="departmentId">
													<option value="0">Select Department</option>
												</select>
												<div class="error_colorCls" id="departMentsErrDiv"></div>
											</div>	
											<div class="col-sm-3" id="nameDivid" style="display:none;">
											<label> Name</label>
												<input type="text" class="form-control clearCls" style="text-align:center;height:40px" onkeyUp="clearData()" id="nameId" placeholder="Please enter Name">
												<div class="error_colorCls" id="nameErrDivId"></div>
											</div>	
											<div class="col-sm-3" id="mobileDivid" style="display:none;">
											<label>Mobile Number</label>
												<input type="text" class="form-control  clearCls " style="text-align:center;height:40px"  onkeyUp="clearData()" id="mobileId" placeholder="Please enter mobile number">
												<div class="error_colorCls" id="mobileErrDivId"></div>
											</div>	
											<div class="col-sm-3" id="emailDivid"  onkeyUp="clearData()" style="display:none;">
											<label> Email</label>
												<input type="text" class="form-control  clearCls" style="text-align:center;height:40px" id="emailId" placeholder="Please enter email">
												<div class="error_colorCls" id="emailErrDivId"></div>
											</div>	
											<div class="col-sm-3" id="endorsmentNoDivid" style="display:none;">
											<label> Endorsment Number</label>
												<input type="text"  onkeyUp="clearData()" class="form-control  clearCls" style="text-align:center;height:40px" id="endorsmentNoId" placeholder="Please enter endorsment number">
												<div class="error_colorCls" id="endorsmentNoErrDivId"></div>
											</div>
											<div class="col-sm-3" id="districtCandDiv">
												<label>District</label>
												<select class="form-control chosen-select clearDataCls" id="districtCandId">
													<option value="0">Select District</option>
												</select>
												<div class="error_colorCls" id="districtCandErrDiv"></div>
											</div>
											<div class="col-sm-3" id="constituencyCanDiv">
											<label>Constituency</label>
												<select class="form-control chosen-select clearDataCls" id="constituencyCanId">
													<option value="0">Select Constituency</option>
												</select>
												<div class="error_colorCls" id="constituencyCanErrDiv"></div>
											</div>
											<div class="col-sm-3" id="mandalCanDiv">
											<label>Mandal</label>
												<select class="form-control chosen-select clearDataCls" id="mandalCanId">
													<option value="0">Select Mandal</option>
												</select>
												<div class="error_colorCls" id="mandalCanErrDiv"></div>
											</div>
											<div class="col-sm-3" id="statusDiv">
											<label>Status</label>
												<select class="form-control chosen-select clearDataCls" id="statusId" multiple>
													<option value="0">Select Status</option>
												</select>
											</div>											
										</div>
										
										</div>
									</div>
									<!--<div class="m_top20" id="normalSearchDivId"  >
										<div class="row m_top20">
												
										</div>	
										<div class="row m_top20">
											
										</div>
							    	</div>-->
									<!--<div class="row m_top20" id="advancedSearchDivId" style="display:none;">
										<div class="col-sm-6">	
											<div style="border:1px solid #ddd;padding:10px;box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.2);">
												<h4 class="font_weight f_18">ADVANCED SEARCH WISE : </h4>
												<div class="m_top10">
													<label class="radio-inline m_top5">
														<input type="radio"  class="advancedSrchCls"  name="optradio1" value="name" checked> NAME
													</label>
													<label class="radio-inline m_top5">
														<input type="radio" class="advancedSrchCls"  name="optradio1" value="mobileNo"> MOBILE NO
													</label>
													<label class="radio-inline m_top5">
														<input type="radio" class="advancedSrchCls"  name="optradio1" value="refCode"> ENDORSMENT ID
													</label>
													<label class="radio-inline m_top5">
														<input type="radio" class="advancedSrchCls"  name="optradio1" value="emailId"> EMAIL-ID
													</label>
												</div>
												<div class="row m_top10">
													<div class="col-sm-12">
														<input type="text" class="form-control" style="text-align:center;height:55px" id="advancedSearchVal">
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row m_top10">
										<div class="col-sm-4">
											<div class="noofWorksSlider" style="border:1px solid lightgrey;padding:10px;">
												<h5><b>No.Of Works</b></h5>
												<p class="m_top10">
													<b class="font_12 cntLeft">0</b><input class="col-sm-12" id="noofWorksSlider" data-slider-id='noofWorksSlider' type="text" data-slider-min="0" data-slider-max="150" data-slider-step="0.5" data-slider-value="0"/><b class="pull-right font_12 cntright">150</b>
												</p>
												
											</div>
										</div>
										<div class="col-sm-4">
											<div class="noofWorksSlider" style="border:1px solid lightgrey;padding:10px">
												<h5><b>Estimated Amount</b></h5>
												<p class="m_top10">
												 <b class="font_12 cntLeft">1 Cr</b><input id="estimatedAmountSlider" type="text" class="span2" value="" data-slider-min="1" data-slider-max="150" data-slider-step="0.5" data-slider-value="[35,130]"/> <b class="pull-right font_12 cntright">150 Cr</b>
												</p>
												 
												
											</div>
										</div>
										<div class="col-sm-4">
											<div class="noofWorksSlider" style="border:1px solid lightgrey;padding:10px">
												<h5><b>Pending Days</b></h5>
												<p class="m_top10">
													<b class="font_12 cntLeft">0</b><input id="pendingDaysSlider" data-slider-id='pendingDaysSlider' type="text" data-slider-min="0" data-slider-max="150" data-slider-step="0.5" data-slider-value="0"/><b class="pull-right font_12 cntright">150</b>
												</p>
												
												
											</div>
										</div>
									</div>-->
									<div class="row m_top10">
										<div class="col-sm-12">						
											<button type ="button" class="btn btn-lg btn-success" id="advanceSearchId" style="cursor: pointer; font-weight: bold; border-radius: 0px;">SEARCH</button>
										</div> 
									</div>
									
									
									<div class="row m_top10">
										<div class="col-sm-12">
											<div id="representationRequestEntryTable"></div>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<div class="modal fade" id="representeeDetailsModelDivId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:90%;margin:auto">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close modalCloseCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<div class="col-sm-11">
			<h4 class="modal-title text-capital" id="representeeDetailsHeadingId" style="text-align:center;font-weight: bold"></h4> 
		</div>
		
	    
		 </div>
      <div class="modal-body">
        <div class="row" id="representeeViewId">
			
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default modalCloseCls" data-dismiss="modal">Close</button>
      </div>
   
  </div>
</div>
</div>



<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<!--<script src="Assests/Plugins/sliderbar/bootstrap-slider.js" type="text/javascript"></script>-->
<script src="Assests/SimplePagination/simplePagination3.js" type="text/javascript"></script>

<!--<script src="Assests/ruralWaterSupply/custom.js" type="text/javascript"></script>-->
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>

<script src="Assests/representationRequest/representationRequestEntryViewMembers.js" type="text/javascript"></script>
<script type="text/javascript">

var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/representationRequestEntryViewMembers")));
//wurl = wurl.replace("/PRRWS-1.0","");
	/* var worksSlider;
	var slider = new Slider('#noofWorksSlider', {
		formatter: function(value) {
			worksSlider=value;
			$("#noOfWoksValue").text(value);
			return 'Current value: ' + value;
		}
	});
	//estimatedAmountSlider
	var esimatedSliderCount;
	var slider = new Slider('#estimatedAmountSlider', {
		formatter: function(value) {
			esimatedSliderCount=value;
			$("#estimatedValue").text(value);
			return 'Current value: ' + value;
		}
	});
	//pendingDaysSlider
	var pendingSliderCount;
	var slider = new Slider('#pendingDaysSlider', {
		formatter: function(value) {
			pendingSliderCount=value;
			$("#pendingDaysValue").text(value);
			return 'Current value: ' + value;
		}
	}); */
</script>
</body>
</html>