<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">   
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TELUGUNADU GRADUATES FEDERATION MEMBER ENROLLMENT</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/GraduatesSearch/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
	<div class="row">
		<div style="position: fixed; right: 0px; bottom: 40%; color: rgb(255, 255, 255); border-top-left-radius: 50%; border-bottom-left-radius: 50%; padding: 10px; font-size: 36px; background: rgb(149, 149, 149) none repeat scroll 0px 0px;cursor:pointer;">
		  <i aria-hidden="true" class="fa fa-commenting" id="submitQuerreisId" class="submitQuerriesCls pull-right"  title=" Click here for Querries / Suggestions "></i>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-body bgImage" style="padding:30px;padding-bottom:140px;">
					<div class="row">
						<div class="col-md-7 col-md-offset-5 col-xs-12 col-sm-offset-4 col-sm-8 changeDiv">
							<div class="row">
								<div class="col-md-3 imgDiv">
									<img src="dist/GraduatesSearch/Logo.png" class="img-responsive"/>
								</div>
								<div class="col-md-9 m_top20" style="border-left:1px solid #ddd">
									<p style="font-size:16px"><span style="font-size:20px"><b>T</b></span>ELUGU <b>N</b>ADU<span style="font-size:20px"><b> G</b></span>RADUATES <span style="font-size:20px"><b>F</b></span>EDERATION <br/><b>M</b>EMBER<b> E</b>NROLLMENT</p>
								</div>
							</div>
							<div class="row m_top20">
								<div class="col-md-12">
								<div class="panel panel-default">
            	<div class="panel-body">
                	<div class="row">
				
                    	<div class="col-md-12">  
                        	<div class="form-group">
                            	<label>Are you registered as TDP Cadre in 2014-2016 &nbsp;</label>
								<label class="radio-inline">
									<input type="radio" name="tdpCadreRadio" value="yes" class="searchCls" checked />Yes
								</label>
								<label class="radio-inline">
									<input type="radio" name="tdpCadreRadio" value="no" class="searchCls"/>No
								</label>  
                            </div>
                        </div>
						<div class="inputChoice col-md-8 col-sm-12 col-xs-12">
							<div class="form-inline btn btn-default">
								<div class="radio">
									<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" id="membershipId" value="1">  MEMBERSHIP ID    &nbsp;&nbsp;</label>
								 
									<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  value="2" >  VOTER ID    &nbsp;&nbsp;</label>
								        
									<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"    value="3"> MOBILE NO &nbsp;&nbsp;</label><br>
									<input type="hidden" id="cadreSearchType" value="membershipId" />
								</div>				  
							</div>
						</div>
					</div>    
					<div class="row">
						<div class="col-md-6 m_top10">
							<label>Constituency</label>
							<!--<select class="dropkickClass" id="constituencyId">
								<option value="0">Select Constituency</option>
							</select>-->
							<s:select theme="simple" cssClass="form-control" id="constituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue="ALL"/>
						</div>
					</div>
					<div class="row">  
                        <div class="col-md-6 m_top20">    
                        	<!--<label class="memberCls">Search By Membership Id/Mobile No/Voter Id</label>
							<label class="nonMemberCls" style="display:none;">Search By VoterCard Number</label>-->
							
                            <input type="text" id="searchBy" class="form-control onlyDigit" placeholder="Search By Membership Id/Mobile No/Voter Id"/>
							<div id="searchErrDiv" style="color:red;"></div>
                        </div>   
						<div class="col-md-2">
							<button class="btn btn-success m_top25" id="searchId" style="padding-bottom: 3px; padding-top: 6px; border-bottom-width: 1px; margin-top: 20px;">SEARCH</button>
						</div>
						
						<input type="hidden" value="" id="hiddenCadreId"/>
						<div class="col-md-12">
							<img src='images/icons/cadreSearch.gif' class="offset3"  id="searchDataImg" class="col-md-4 col-md-offset-2" style="height: 250px; padding-bottom: 26px;display:none;"/>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20 cadreMemberListCls">
								<div id="topPaginationDivId" class="paginationDivId"></div>
								<div style="overflow:scroll:900px;display:none;" id="cadreDetailsDiv"></div>
								<div id="paginationDivId"  class="paginationDivId"></div>

							</div>
						</div>	
						
						<div class="col-md-12">
							<div id="getOtpId" style="color:red;"></div>  
							<div class="col-md-4">
							<!--	<div class="success" id="otpSuccessDiv"></div>-->
								<button id="generateOtpId" class="btn btn-success m_top20" style="display:none;" onclick="generateOTPForMobileNo(this);">GENERATE OTP</button>
								
							</div>
							<div class="col-md-3">
								<div class="input-group m_top20">
									<input type="text" class="form-control onlyDigit" maxLength="6" id="otpId" style="display:none;"/>
									<span id="otpSpanId" class="input-group-addon" style="background:#fff;display:none;">
										<i class="glyphicon glyphicon-ok text-success" style="display:none" id="success"></i>
										<i class="glyphicon glyphicon-remove text-danger" style="display:none;" id="fail"></i>
									</span>
								</div>
								
								<img src="images/ajaxImg2.gif" style="display:none;" class="img-responsive" id="validateOTPImg"/>
								<div id="otpErr" style="color:red"></div>
								
							</div>      
							<div class="col-md-2">  
							<button class="btn btn-success m_top25 " id="submitId" style="padding-bottom: 3px; padding-top: 6px; border-bottom-width: 1px; margin-top: 20px;display:none;">SUBMIT</button>  
							</div>
							<div class="col-md-2">
							<button class="btn btn-success m_top25" id="nextStepId" style="padding-bottom: 3px; padding-top: 6px; border-bottom-width: 1px; margin-top: 20px;display:none;">NEXT</button>
							</div>
							<div id="errChkDivId" style="color:red;"></div> 
							
							
						</div>
						
						<input type="hidden" id="randomRefNo"></input>
                    </div>
                </div>
            </div>
								</div>
							</div>
						</div>
						
						<!--<a href="#" id="submitQuerreisId" class="submitQuerriesCls  pull-right">Submit Querries/Suggestions </a> -->

					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

   <div class="modal fade" id="modalSubmitQuriesId" role="dialog">
	<div class="modal-dialog">
	
	  <!-- Modal content-->
	  <div class="modal-content">
		<div class="modal-header">
		  <button type="button" class="close" data-dismiss="modal">&times;</button>
		  <h4 class="modal-title text-center" style="text-transform:uppercase">Query/Suggestion Form</h4>
		</div>
		
		
		<div class="modal-body">
			<div class="row">
				<div class="col-md-8">
					<label>Name&nbsp;<span class="queryNameErrCls" style="color:red"></span>&nbsp;<span class="text-danger">*</span></label>
					<input type="text" name="name" id="modalNameId" class="form-control">
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<label>Mobile No&nbsp;<span class="queryMobileNOErrCls" style="color:red"></span>&nbsp;<span class="text-danger">*</span></label>
					<input type="text" name="mobile No" id="modalMobileNoId" class="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<label>Email&nbsp;<span class="queryEmailErrCls" style="color:red"></span>&nbsp;<span class="text-danger">*</span></label>
					<input type="email" name="email" id="emailId" class="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<label>Querries/Suggestions&nbsp;<span class="querySuggErrCls" style="color:red">&nbsp;</span><span class="text-danger">*</span></label>
					<textarea class="form-control" rows="4" cols="50"  maxlength="500" name="querriesSuggestions" id="qurySuggstinsId" ></textarea>
				</div>
			</div>
				  
			</div>  
		 
		<div class="modal-footer">
		  <button type="button" class="btn btn-success submitButnCls" id="submitButnId" onclick="validateFieldUnionRegistrion();">submit</button>
		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>  
	  </div>
	</div>
  </div>
 

<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="js/tdpCadreAjax/tdpCadreAjax.js" type="text/javascript"></script>
<script src="js/unionSearchRegiststration/unionSearchRegiststration.js" type="text/javascript"></script>
<script src="js/mobileOperation/mobileValidation.js" type="text/javascript"></script>
<script src="js/smsTransactions/smsTransactions.js" type="text/javascript"></script>
<script src="js/locationSearch/locationSearch.js" type="text/javascript"></script>
<script src="js/locationSearch/locationSearchAjax.js" type="text/javascript"></script>

<script>
$(document).ready(function(){
	$("#trigger").hide();
	$("#loginId").hide();
	$(".adminCls").hide();
});

</script>
</body>
</html>
