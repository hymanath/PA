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
<title>Search</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<style type="text/css">

h1,h2,h3,h4,h5,h6,p,ul   
{
	margin:0px;
}
label
{
	font-weight:400
}
.registeredMem
{
	padding:0px;
	margin-top:20px;
}
.registeredMem li
{
	list-style:none;
	border:1px solid #ddd;
	padding:4px;
}
.registeredMem li img.profile
{
	height:60px;
	width:40px;
}
.registeredMem li .media .media-object
{
	margin:0px;
}
.m_top20
{
	margin-top:20px;
}
.m_top25
{
	margin-top:25px;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-8 col-md-offset-2">
        	<div class="panel panel-default">
            	<div class="panel-heading">
               		<h4 class="panel-title">TELUGU GRADUATES CELL ENROLLEMENT</h4>
                </div>
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
						<div class="col-md-6">
							<label>Constituency<span style="color:red;">*</span></label>
							<!--<select class="dropkickClass" id="constituencyId">
								<option value="0">Select Constituency</option>
							</select>-->
							<s:select theme="simple" cssClass="form-control" id="constituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue="Select Constituency"/>
							<div id="constErrId" style="color:red;"></div>
						</div>
					</div>
					<div class="row">
                        <div class="col-md-6 m_top20">  
                        	<!--<label class="memberCls">Search By Membership Id/Mobile No/Voter Id</label>
							<label class="nonMemberCls" style="display:none;">Search By VoterCard Number</label>-->
							
                            <input type="text" id="searchBy" class="form-control" placeholder="Search By Membership Id/Mobile No/Voter Id"/>
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
								<div class="well well-sm" style="border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;display:none;" id="cadreDetailsDiv"></div>
								<div id="paginationDivId"  class="paginationDivId"></div>

							</div>
						</div>	
						
						<div class="col-md-12">
						<div id="getOtpId" style="color:red;"></div>
							<div class="col-md-4">
							<!--	<div class="success" id="otpSuccessDiv"></div>-->
								<button id="generateOtpId" class="btn btn-success m_top20" style="display:none;" onclick="generateOTPForMobileNo(this);">GENERATE OTP</button>
							</div>
							<div class="col-md-2">
								<input type="text" class="form-control m_top20" maxLength="6" id="otpId" style="display:none;"/>
							</div> 
							<div class="col-md-2">
							<button class="btn btn-success m_top25" id="nextStepId" style="padding-bottom: 3px; padding-top: 6px; border-bottom-width: 1px; margin-top: 20px;display:none;">NEXT</button>
							</div>
							<div id="errChkDivId" style="color:red;"></div>
							<div id="success" class="col-md-2 m_top25" style="display:none;">
							<i class="glyphicon glyphicon-ok" style="color:green;margin-left: -145px;"></i>
							</div>
							<div id="fail" class="col-md-2 m_top25" style="display:none;">
							<i class="glyphicon glyphicon-remove" style="color:red;margin-left: -145px;"></i>
							</div>
						</div>
						<input type="hidden" id="randomRefNo"></input>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>	
	
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="js/tdpCadreAjax/tdpCadreAjax.js" type="text/javascript"></script>
<script src="js/unionSearchRegiststration/unionSearchRegiststration.js" type="text/javascript"></script>
<script src="js/smsTransactions/smsTransactions.js" type="text/javascript"></script>
<script src="js/locationSearch/locationSearch.js" type="text/javascript"></script>
<script src="js/locationSearch/locationSearchAjax.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	$("#trigger").hide();
	$("#loginId").hide();
});

</script>
</body>
</html>
