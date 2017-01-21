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
<title>QA Verification Dashboard</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/dropkick/dropkick.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/css/dataTables.css" rel="stylesheet" type="text/css">

<style>
.img{
	margin-top: 28px; width: 20px; height: 19px;
}
.text-capital{
	text-transform : uppercase;
}
.border-line{
	border-right: 2px solid rgb(0, 0, 0);
}
.tablePrinting thead th{ font-size:14px !important}

#boxesSummaryTableId thead th , #boxesSummaryTableId tbody tr td{
	 text-align: center;
    vertical-align: middle;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h3 class="panel-title text-capital">DISPATCH OF SMART CARD</h3>
					</div>
					<div class="panel-body">
						<div class="text-capital" id="errorDivId" style="color:red"></div>
						<div class="row">
						
							<div class="col-md-3 col-xs-12 col-sm-4">
							  <div class="row">
							   <div class="col-md-9 col-xs-12 col-sm-4">
									<label>Select Vendor</label>
									<s:select theme="simple" cssClass="select" name="vendorTypes" id="vendorId" list="basicVOList" listKey="id" listValue="name" onchange="getDistrictList(this.value)"/>
							   </div>
							    <div class="col-md-3 col-xs-12 col-sm-4">
								     <span id="distImgId"  style="display:none;"><img class="img" src="images/search.gif"/></span>
								</div>
							 </div>
							</div>
							
							<div class="col-md-3 col-xs-12 col-sm-4">
								<div class="row">
									<div class="col-md-9 col-xs-12 col-sm-4">
										<label>Select District</label>
										<select class="select" id="districtListId" onchange="getConstituenciesForDistrict()">
											<option value="0">Select District</option>
										</select>
									</div>
									 <div class="col-md-3 col-xs-12 col-sm-4">
										<span id="constImgId"  style="display:none;"><img class="img" src="images/search.gif"/></span>
									 </div>
								</div>
							</div>
							
							<div class="col-md-3 col-xs-12 col-sm-4">
								<label>Select Constituency</label>
								<select class="select" id="constencyListId">
									<option value="0">Select Constituency</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-4" style="margin-top: 30px;">
								<button class="btn btn-success btn-xs text-capital" style="padding: 6px 12px;" onclick="getDispatchDetails();"><b>SUBMIT</b></button>
							</div>
						</div>
						
						<div class="row m_top10 text-center" >
						   <span id="dispatchDivIdImg" style="display:none;" ><img style="width: 30px; height: 30px" src="images/search.gif"/></span>
						</div>
						
						<div class="row  m_top20">
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<div id="summaryDivId">
							    </div>
						    </div>
						</div>
						
						<div class="row" id="boxWiseDivId" style="display:none;">
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<div id="dispatchingDiv"></div>
							</div>
						</div>
						
						<div class = "row" >
						  <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
						    <div id="boxesSummaryId"> </div>
						  </div>
						</div>
						
						<!-- QA STATUS UPDATION -->
						<div class = "row" id="qaStatusUpdationDivId" style="display:none">
						 <div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
							  <div class="panel-heading">
								<h3 class="panel-title"> QA STATUS UPDATION </h3>
							  </div>
							  <div class="panel-body">
							 
							 <div>
								  <div class="col-md-3 col-xs-12 col-sm-6">
										<label>Select Status</label><span class="text-danger">*</span>
										<select class="form-control" id="statusId">
											<option value="0">Select Status</option>
											<option value="6" >QA Passed</option>
											<option value="7">QA Failed</option>
										</select>
								  </div>
								 <div class="col-md-9 text-capital" style="color:red;font-size:18px;" id="statusErrorId"></div>
							  </div>
								<div class="col-md-11 col-sm-12 col-xs-12">
									<label>Remarks</label><span class="text-danger">*</span>
									<textarea class="form-control" rows="3" id="remarksId"></textarea>
								</div>
								<div class="col-md-10 col-sm-6 col-xs-12 m_top10">
								<input class="buttonCls btn btn-success" value="Update" type="button" onClick="qaStatusUpdation();">
								<span class="col-md-offset-3" style="font-size:20px;" id="successMsgId"></span>
								<!--<div class="row">
									<div class="col-md-3 col-sm-6 col-xs-6">
										<input class="buttonCls btn btn-success" value="Update" id="" type="button">
									</div>
									<div class="col-md-8 col-sm-6 col-xs-6">
										<h3>Successfully Updated</h3>
									</div>
								</div>-->
									
								</div>
							</div>
						</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		<!--
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
		-->
	</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/dropkick/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script src="js/CardPrint/cardPrintDispatch.js" type="text/javascript"></script>
<script src="dist/js/dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
$(".select").dropkick();
if($(window).width() < 500)
{
	$("table").wrap( "<div class='table-responsive'></div>" );
}
</script>
</body>
</html>
