<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Update Mobile Number</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="govtCo#18A75AashBoard/img/fevicon.png">
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/jquery.filer.css"  type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css"  type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<link href="newCoreDashBoard/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
<link href="alertDepartment/css/customLess.less" rel="stylesheet" type="text/less">
<!--<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">-->
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/less.js/2.7.2/less.min.js"></script>

<style type="text/css">
.eventsheader
{
	display:none;
}
.prev, .next{
	 height: 33px !important;
	 width: 47px !important;
}
.pointerCls{
	cursor:pointer  !important;
}
</style>
</head>
<body>
<div  class="AMS">
	<section class="m_top20">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-8">
									<h4 class="panel-title">
										<img src="newCoreDashBoard/img/Alert_icon.png" class="iconClass"/>
										Update Mobile Number<span class="errorMsgCls" style="margin-left:40px;color:red;">
										</span>
									</h4>
								</div>
								<div class="col-sm-4" >
								  <h4 class="panel-title text-capital">
								  <span>EXISTING MOBILE No : <span style="color:green;" id="existingMobileNoId"></span></span>
								  </h4>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-4">
									<label>ENTER MOBILE NO<span style="color:green;margin-left:20px;" id="referenceId"></span></label>
									<input type="text" class="form-control" placeholder="ENTER MOBILE NO" id="mobileNoTextBoxId"/>
								</div>
								<div class="col-sm-2">
									<input type="button" value="Generate OTP" id="getOTPBtnId" class="btn btn-sm btn-success text-capital" style="margin-top:25px;"/>
								</div>
							</div>
							<div class=" otptextBoxCls" style="display:none;">
								<div class="row m_top10">
									<div class="col-sm-4">
										<label>ENTER OTP</label>
										<input type="text" class="form-control" placeholder="ENTER OTP" id="otpNumberTextboxId"/>
									</div>
									<div class="col-sm-2 optCls">
										<input type="button" value="validate" class="btn btn-sm btn-success text-capital" style="margin-top:25px;" id="validateOtpBtnId">
									</div>
								</div>
							</div>	
							<div class="row submitBtnCls" style="display:none;">
								<div class="col-sm-2 optCls">
								  <input type="button" value="update" class="btn btn-success text-capital" style="margin-top:25px;" id="updateMobileNoBtnId"/>
								</div>
							</div>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<!-- Scripts-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="alertDepartment/js/jquery.hotkeys.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystemNewUpload.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jquery-ui.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jQDateRangeSlider-withRuler-min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="alertDepartment/js/updateMobileNo.js" type="text/javascript"></script>
<!-- Custom Script Files Data Start-->
<!-- Custom Script Files Data End-->
<script type="text/javascript"> 
 var mobileNo = '${officerMobileNo}';
 if(mobileNo != null && mobileNo.trim().length > 0){
	 $("#existingMobileNoId").html(mobileNo);
 } 
 </script>
</body>
</html>