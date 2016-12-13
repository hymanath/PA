<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cards Print Dispatch</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/custom.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/dropkick.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h3 class="panel-title text-capital">dispatch of smart card</h3>
					</div>
					<div class="panel-body">
						<div class="text-capital" id="errorDivId" style="color:red"></div>
						<div class="row">
							<div class="col-md-3 col-xs-12 col-sm-4">
								<label>Select Vendor</label>
								<s:select theme="simple" cssClass="select" name="vendorTypes" id="vendorId" list="vendorList" listKey="id" listValue="name" onchange="getDistrictList(this.value)"/>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-4">
								<label>Select District</label>
								<select class="select" id="districtListId" onchange="getConstituenciesForDistrict()">
									<option value="0">Select District</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-4">
								<label>Select Constituency</label>
								<select class="select" id="constencyListId">
									<option value="0">Select Constituency</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-4" style="margin-top: 30px;">
								<button class="btn btn-success btn-xs text-capital" style="padding: 6px 12px;" onclick="getDispatchDetails();"><b>submit</b></button>
							</div>
						</div>
						<div class="row" id="boxWiseDivId" style="display:none;">
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<div id="dispatchingDiv"></div>
								<span id="dispatchDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
								<button class="btn btn-success btn-lg text-capital">update & generate tracking agent</button>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h3 class="panel-title text-capital"><b>kavali mandal tracking sheet</b><span class="pull-right">X</span></h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4 col-xs-12 col-sm-4">
								<caption class="text-capital"><b>heading</b></caption>
								<table class="table table-bordered text-capital">
									<tr>
										<td>box tracking id</td>
										<td>bt2249</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script src="js/CardPrint/cardPrintDispatch.js" type="text/javascript"></script>
<script type="text/javascript">
$(".select").dropkick();
if($(window).width() < 500)
{
	$("table").wrap( "<div class='table-responsive'></div>" );
}
</script>
</body>
</html>
