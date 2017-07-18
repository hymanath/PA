<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booth Commitee Dashboard</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
<link href="Assets/css/boothIncharge.less" rel="stylesheet" type="text/less">
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assets/js/less.js"></script>
<style type="text/css">
.footerCls
{
	position:static;
}

</style>
</head>
<body>
        <section class="m_top20">
		  
            <div class="container-fluid">
				<div class="row">
				 <h4 class="text-center"><span style="border: 1px solid rgba(119, 119, 119, 0.467); border-radius: 20px; padding: 10px; background-color: rgb(255, 255, 255); font-weight: 600;">
				 BOOTH COMMITTEE DASHBOARD</span></h4>
					<div class="col-sm-3 pull-right">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
							<input type="text" class="form-control pull-right"  name="daterangePicker" id="daterangePickerId"/>
						</div>
					</div>
				</div>
				<div class="row m_top10">
				  <div id="overAllBoothDlstDivId"></div>
                </div>
            </div>
        </section>
        <section class="m_top20">
            <div class="container-fluid">
                <div class="col-sm-12">
                        <div class="committeeMembers">
                            <div class="subBlock col-sm-12 text-center">
                                
								<div id="overAllSerialRangeWiseVoterDivId"></div>
					        </div>
                        </div>
                    </div>
            </div>
        </section>
		
        <section class="m_top20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default" id="districtAccessLevelId">
							<div class="panel-heading" id="headingOne">
								<div class="row" >
									<div class="col-sm-6">
										<h4 class="panel-title m_top5 text-capital districtParliamentLevleHadingCls">DISTRICT WISE</h4>
									</div>
									<div class="col-sm-5">
										<ul class="nav navbar-nav table-menu pull-right" table-menu="">
											<li class="active districtLevelCls" style="cursor:pointer;" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="districtLevelCls" style="cursor:pointer;" attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1">
										<a class="profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
							   <div id="dstrctParlmntLvlBoothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
        <section class="m_top20">
            <div class="conatiner-fluid">
                  <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default" id="constituencyAccessLevelId">
							<div class="panel-heading" id="headingTwo">
								<div class="row">
									<div class="col-sm-6">
										<h4 class="panel-title m_top5 text-capital">CONSTITUENCY WISE</h4>
									</div>
									<div class="col-sm-5" id="constitencyWiseDistrictParliamentId">
										<ul class="nav navbar-nav table-menu pull-right">
											<li class="active locationLevelTabCls" style="cursor:pointer;" attr_result_level="CONSTITUENCY" attr_result_level_div_id="constituencyLevelBoothDtlsDivId" attr_select_box_id="constituencyLevelDistrictSelectBxId" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="locationLevelTabCls" style="cursor:pointer;" attr_result_level="CONSTITUENCY" attr_result_level_div_id="constituencyLevelBoothDtlsDivId" attr_select_box_id="constituencyLevelDistrictSelectBxId" attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1 collapseDivCls">
										<a class=" profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">
								<div class="row" >
									<div class="col-sm-3" id="constituencyWiseDistrictId">
										<select id="constituencyLevelDistrictSelectBxId" attr_result_level="CONSTITUENCY" attr_result_level_div_id="constituencyLevelBoothDtlsDivId" attr_sub_level_drop_box_id="constituencyLevelConstituenySelectBxId" class="selectBoxCls" attr_level="DISTRICT" attr_sub_level="CONSTITUENCY">
											<option value="0">All DISTRICT</option>
										</select>
									</div>
									<div  class="col-sm-3">
										<select class="selectBoxCls" id="constituencyLevelConstituenySelectBxId" attr_result_level="CONSTITUENCY" attr_result_level_div_id="constituencyLevelBoothDtlsDivId" attr_level="CONSTITUENCY">
											<option value="0">All CONSTITUENCY</option>
										</select>
									</div>
								</div>
							  <div id="constituencyLevelBoothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
        <section class="m_top20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default" id="mandalAccessLevelId">
							<div class="panel-heading" id="headingThree">
								<div class="row">
									<div class="col-sm-6">
										<h4 class="panel-title m_top5 text-capital">MANDAL WISE</h4>
									</div>
									<div class="col-sm-5" id="mandalWiseDisticparlimentIds">
										<ul class="nav navbar-nav table-menu pull-right">
											<li class="active locationLevelTabCls" style="cursor:pointer;" attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_select_box_id="mandalLevelDistrictSelectBxId" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="locationLevelTabCls" style="cursor:pointer;" attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_select_box_id="mandalLevelDistrictSelectBxId" attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1 collapseDivCls">
										<a class=" profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body">
								<div class="row" id="mandalWiseDistrictId" style="cursor:pointer;">
									<div  id="selectConstituencyDistrict">
										<div class="col-sm-3" id="mandalLevelDistrictDivId">
											<select id="mandalLevelDistrictSelectBxId" class="selectBoxCls"  attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_sub_level_drop_box_id="mandalLevelConstituenySelectBxId" attr_level="DISTRICT" attr_sub_level="CONSTITUENCY">
												<option value="0">All DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3" id ="mandalLevelConstituencyDivId">
											<select id="mandalLevelConstituenySelectBxId" class="selectBoxCls"  attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_sub_level_drop_box_id="mandalLevelMandalSelectBxId" attr_level="CONSTITUENCY" attr_sub_level="TEHSIL">
												<option value="0">All CONSTITUENCY</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="mandalLevelMandalSelectBxId"   class="selectBoxCls" attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_level="TEHSIL">
												<option value="0">All MANDAL</option>
											</select>
										</div>
									</div>										
								</div>
								<div id="mandalLevelBoothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
		<section class="m_top20 panchaytBlockCls" style="display:none;">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default" id="panchayatAccessLevelId">
							<div class="panel-heading" id="headingFour">
								<div class="row">
									<div class="col-sm-3">
										<h4 class="panel-title m_top5 text-capital" >PANCHAYAT WISE</h4>
									</div>
									<div class="col-sm-4">
										<ul class="nav navbar-nav table-menu pull-right resultTypeUL">
											<li class="active resultTypeTabCls" style="cursor:pointer;" attr_heading_level="SUMMARY" >SUMMARY</li>
											<li class="resultTypeTabCls" style="cursor:pointer;" attr_heading_level="DETAILS" >BOOTH WISE</li>
										</ul>
									</div>
									<div class="col-sm-4" id = "panchayatWiseDistrictparliamentIds">
										<ul class="nav navbar-nav table-menu pull-right" table-menu="">
											<li class="active locationLevelTabCls" style="cursor:pointer;" attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_select_box_id="panchatLevelDistrictSelectBxId" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="locationLevelTabCls" style="cursor:pointer;" attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_select_box_id="panchatLevelDistrictSelectBxId"  attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1 collapseDivCls1">
										<a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
							<div class="panel-body">
								<div class="row">
									<div id="selectConstituencyDistrict">
										<div class="col-sm-3" id="panchayatWiseDisticDivId">
											<select id="panchatLevelDistrictSelectBxId"  class="selectBoxCls"  attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_sub_level_drop_box_id="panchaytLevelConstituenySelectBxId" attr_level="DISTRICT" attr_sub_level="CONSTITUENCY">
												<option value="0">All DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3" id="panchayatWiseConstituencyDivId">
											<select id="panchaytLevelConstituenySelectBxId" class="selectBoxCls"  attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_sub_level_drop_box_id="panchaytLevelMandalSelectBxId" attr_level="CONSTITUENCY" attr_sub_level="TEHSIL">
												<option value="0">All CONSTITUENCY</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="panchaytLevelMandalSelectBxId" class="selectBoxCls"  attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId"  attr_sub_level_drop_box_id="panchaytLevelPanchaytSelectBxId"  attr_level="TEHSIL" attr_sub_level="PANCHAYAT">
												<option value="0">All MANDAL</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="panchaytLevelPanchaytSelectBxId" class="selectBoxCls"  attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_level="PANCHAYAT">
												<option value="0">All PANCHAYAT</option>
											</select>
										</div>
									</div>										
								</div>
								<div id="panchaytLevelBoothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
        </div>
	</section>
	<section class="m_top20 boothBlckCls">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading" id="headingFour">
								<div class="row">
									<div class="col-sm-3">
										<h4 class="panel-title m_top5 text-capital" >BOOTH WISE</h4>
									</div>
									<div class="col-sm-4" >
										<ul class="nav navbar-nav table-menu pull-right resultTypeUL">
											<li class="resultTypeTabCls boothResultTypeCls" style="cursor:pointer;" attr_heading_level="SUMMARY">SUMMARY</li>
											<li class="active resultTypeTabCls boothResultTypeCls" style="cursor:pointer;" attr_heading_level="DETAILS">BOOTH WISE</li>
										</ul>
									</div>
									<div class="col-sm-4">
										<ul class="nav navbar-nav table-menu pull-right" id="boothLevelDistParTabDivId" table-menu="">
											<li class="active locationLevelTabCls" style="cursor:pointer;" attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_select_box_id="boothBlockDistrictSelectBxId" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="locationLevelTabCls" style="cursor:pointer;" attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_select_box_id="boothBlockDistrictSelectBxId"  attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1">
										<a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
							<div class="panel-body">
								<div class="row">
									<div>
										<div class="col-sm-3" id="boothLevelDistDivId">
											<select id="boothBlockDistrictSelectBxId"  class="boothBlockCls"  attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_sub_level_drop_box_id="boothBlockConstituenySelectBxId" attr_level="DISTRICT" attr_sub_level="CONSTITUENCY">
												<option value="0">All DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3" id="boothLevelConDivId">
											<select id="boothBlockConstituenySelectBxId" class="boothBlockCls"  attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_sub_level_drop_box_id="boothBlockMandalSelectBxId" attr_level="CONSTITUENCY" attr_sub_level="TEHSIL">
												<option value="0">All CONSTITUENCY</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="boothBlockMandalSelectBxId" class="boothBlockCls"  attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId"  attr_sub_level_drop_box_id="boothBlockPanchaytSelectBxId"  attr_level="TEHSIL" attr_sub_level="PANCHAYAT">
												<option value="0">All MANDAL</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="boothBlockPanchaytSelectBxId" class="boothBlockCls"  attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_level="PANCHAYAT">
												<option value="0">All PANCHAYAT</option>
											</select>
										</div>
									</div>										
								</div>
								<div id="boothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
        </div>
	</section>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="Assets/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/newmultiselect/chosen.jquery.js" type="text/javascript"></script>
<script src="Assets/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assets/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/BoothPage/boothInchargeDashboard.js" type="text/javascript"></script>
<script type="text/javascript">
var accessType = '${sessionScope.USER.accessType}';
var accessValue = '${sessionScope.USER.accessValue}';
$(document).ready(function(){
	if(accessType == "MLA"){
		
		$("#districtAccessLevelId").hide();
		$("#constituencyAccessLevelId").hide();
		//block tab heading hiding 
		$("#boothLevelDistParTabDivId").hide();
		$("#mandalWiseDisticparlimentIds").hide();
		$("#mandalLevelDistrictDivId").hide();
		$("#panchayatWiseDistrictparliamentIds").hide();
		//select box hiding 
		$("#mandalLevelConstituencyDivId").hide();
		$("#panchayatWiseDisticDivId").hide(); 
		$("#panchayatWiseConstituencyDivId").hide(); 
		$("#boothLevelDistDivId").hide(); 
		$("#boothLevelConDivId").hide(); 
		/* Adding and removing class dynamically */
		$(".collapseDivCls").removeClass("col-sm-1");
		$(".collapseDivCls").addClass("col-sm-6");
		$(".collapseDivCls1").addClass("col-sm-5");
		//Adding Dynamic Attribute based on user access level 
		$(".resultTypeTabCls").attr("attr_panchayt_result_level","TEHSIL");
		$(".resultTypeTabCls").attr("attr_pnchyt_lvl_parent_select_box_div_id","panchaytLevelMandalSelectBxId");
		$(".boothResultTypeCls").attr("attr_booth_result_level","TEHSIL");
		$(".boothResultTypeCls").attr("attr_boot_level_parent_select_box_div_id","boothBlockMandalSelectBxId");
		//ajax call based on user access level
		getOverAllBoothDetails("STATE","CONSTITUENCY",accessValue);
		getLocationLevelWiseBoothCount("TEHSIL","CONSTITUENCY",accessValue,"mandalLevelBoothDtlsDivId");
		getLocationLevelWiseBoothCount("PANCHAYAT","CONSTITUENCY",accessValue,"panchaytLevelBoothDtlsDivId");
		getLocationBasedOnSelection("TEHSIL","CONSTITUENCY",accessValue,"","CONSTITUENCY","");
	    //setting dynamically user access type and access value based on login user
	    setAccessLevelTypeAndValue("CONSTITUENCY",accessValue);
	  
	}else if(accessType == "MP"){
		//block tab heading hiding 
		$("#panchayatWiseDistrictparliamentIds").hide();
		$("#mandalLevelDistrictDivId").hide();
		$("#mandalWiseDisticparlimentIds").hide();
		$("#boothLevelDistParTabDivId").hide();
		//select box hiding 
		$("#districtAccessLevelId").hide();
		$("#constitencyWiseDistrictParliamentId").hide();
		$("#constituencyWiseDistrictId").hide();
		$("#panchayatWiseDisticDivId").hide();
        $("#boothLevelDistDivId").hide(); 
		//Adding Dynamic Attribute based on user access level 
		$(".resultTypeTabCls").attr("attr_pnchyt_lvl_parent_select_box_div_id","panchaytLevelConstituenySelectBxId");
		$(".resultTypeTabCls").attr("attr_panchayt_result_level","CONSTITUENCY");
		$(".boothResultTypeCls").attr("attr_booth_result_level","CONSTITUENCY");
		$(".boothResultTypeCls").attr("attr_boot_level_parent_select_box_div_id","boothBlockConstituenySelectBxId");
		//setting dynamically user access type and access value based on login user
		setAccessLevelTypeAndValue("PARLIAMENT CONSTITUENCY",accessValue);
        //ajax call based on user access level
		getOverAllBoothDetails("STATE","PARLIAMENT CONSTITUENCY",accessValue);
		getLocationLevelWiseBoothCount("CONSTITUENCY","PARLIAMENT CONSTITUENCY",accessValue,"constituencyLevelBoothDtlsDivId");
		getLocationLevelWiseBoothCount("TEHSIL","PARLIAMENT CONSTITUENCY",accessValue,"mandalLevelBoothDtlsDivId");
		getLocationLevelWiseBoothCount("PANCHAYAT","PARLIAMENT CONSTITUENCY",accessValue,"panchaytLevelBoothDtlsDivId");
		getLocationBasedOnSelection("CONSTITUENCY","PARLIAMENT CONSTITUENCY",accessValue,"","PARLIAMENT CONSTITUENCY","");
	
	}else if(accessType == "DISTRICT"){
			getOverAllBoothDetails("STATE","DISTRICT",accessValue);
			getLocationLevelWiseBoothCount("DISTRICT","DISTRICT",accessValue,"dstrctParlmntLvlBoothDtlsDivId");
			getLocationLevelWiseBoothCount("CONSTITUENCY","DISTRICT",accessValue,"constituencyLevelBoothDtlsDivId");
			getLocationLevelWiseBoothCount("TEHSIL","DISTRICT",accessValue,"mandalLevelBoothDtlsDivId");
			getLocationLevelWiseBoothCount("PANCHAYAT","DISTRICT",accessValue,"panchaytLevelBoothDtlsDivId");
			getLocationBasedOnSelection("DISTRICT","DISTRICT",accessValue,"","All","");
			//setting dynamically user access type and access value based on login user
			setAccessLevelTypeAndValue("DISTRICT",accessValue);
			
	}else if (accessType == "STATE"){
			getOverAllBoothDetails("STATE","STATE",accessValue);
			getLocationLevelWiseBoothCount("DISTRICT","STATE",accessValue,"dstrctParlmntLvlBoothDtlsDivId");
			getLocationLevelWiseBoothCount("CONSTITUENCY","STATE",accessValue,"constituencyLevelBoothDtlsDivId");
			getLocationLevelWiseBoothCount("TEHSIL","STATE",accessValue,"mandalLevelBoothDtlsDivId");
			getLocationLevelWiseBoothCount("PANCHAYAT","STATE",accessValue,"panchaytLevelBoothDtlsDivId");
			getLocationBasedOnSelection("DISTRICT","STATE",accessValue,"","All","");
			//setting dynamically user access type and access value based on login user
			setAccessLevelTypeAndValue("STATE",accessValue);
	} 
	function setAccessLevelTypeAndValue(accessType,accessValue){
		
		   $(".locationLevelTabCls").attr("accessType",accessType);
		   $(".locationLevelTabCls").attr("accessValue",accessValue);
		   $(".districtLevelCls").attr("accessType",accessType);
		   $(".districtLevelCls").attr("accessValue",accessValue);
		   $(".resultTypeTabCls").attr("accessType",accessType);
		   $(".resultTypeTabCls").attr("accessValue",accessValue);
		   $("#daterangePickerId").attr("accessType",accessType);
		   $("#daterangePickerId").attr("accessValue",accessValue);
	}
});
</script>
</body>
</html>