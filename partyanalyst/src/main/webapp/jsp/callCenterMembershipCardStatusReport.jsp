<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
  <head>
	 <!-- Bootstrap -->
    <link href="js/cardsDashBoard/css2.3.2/bootstrap.min.css" rel="stylesheet">	
	<!-- Custom Styles-->
    <link href="js/cardsDashBoard/css2.3.2/style.css" rel="stylesheet">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	
	<script>
		var mobileNumber = '${mobileNumber}';
		var trNo = '${trNo}';
		var membership = '${membership}';
	</script>
	<style>
		.widget{background:#f9f9f9; border:1px solid #ddd; margin-bottom:15px;}
		.widget-heading{border-bottom:1px solid #c1c1c1; background:#ddd; padding:1px 10px;}
		.widget-body{padding:10px;}
		.widget-footer{border-top:1px solid #ddd;  padding:0px 10px;}
		.widget-table{overflow:auto;}
		.border-radius-0{border-radius:0px;}
		.width-50{width:50px!important;}
		
		.pad-0{padding:0px !important;}
		.pad-0-10{padding:0px 10px;}
		.margin-0{margin:0px;}
		.demo-flot-chart {width: 100%;  height: 250px;}
		.donut-label { font-size: 12px; color: #FFF; background: rgba(0, 0, 0, 0.5); text-align: center;  padding: 1px;line-height:15px;}
		
		.text-uppercase{text-transform: uppercase;}
		.f-bold{ font-weight: bold;;}
	</style>
	
  </head>
  <body>
	<div class="container">
		<div class="row">
			<div class="span12 widget text-center">
				<h2>CADRE CARD STATUS OVERVIEW</h2>
					<h4><span style="background:#f9f9f9; padding:0px 10px;">SEARCH BY</h4>
					<div style="border-top:1px solid #ccc; margin:-20px 100px 20px 100px; "></div>
					<div id="errorDiv" style=""></div>
					<form class="bs-docs-example form-inline">	
						<label class="radio">
							<input type="radio" class="radioCls" name="searchType" value="membership" checked> Membership Number &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /or/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</label>					
						<label class="radio">
							<input type="radio" class="radioCls" name="searchType" value="mobile">Mobile Number &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /or/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</label>
						
						<label class="radio">
							<input type="radio" class="radioCls" name="searchType" value="tr"> T/R Number &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</label>
						<br/>
						<input  type="text" placeholder="Enter Number"  id="searchedValue" class="input-xlarge m_top10 border-radius-0 required">
						<input class="btn btn-success border-radius-0 m_top10" type="button" value="Search" onClick="getCadreDetailsBySearchCriteria();"></input>
						<div align="center"><img style="width:70px;height:60px;display:none;" id="ajaxImage" class="" src="images/Loading-data.gif"></div>
						
					</form>
			</div>
		</div>
		
		
		
		<div class="row" id="tableDataDiv"></div>
			
		<div class="row" id="cardMainDiv" style="display:none">
		<!-----CARD--------->
			<div class="span7"  >
				<div class="widget" id="cardDisplayId" style="height:339px;"></div>
			</div>
		<!------CARD End-------->
			<div class="span5" >
				<div class="widget">					
					<div class="widget-body" style="padding-top: 0px;height:20px;">
						<h5 style="margin-bottom:0px;"><span style="margin-top:0px;" class="label label-success">Status</span> &nbsp; &nbsp;  &nbsp;<span id="statusDiv"> </span></h5>	
					</div>
				</div>
				<div class="widget">
					<div class="widget-body" style="height:262px;">
						<div id="errorDiv1" style="margin-bottom: -8px; margin-top: -9px;"></div>
						<h5 style="margin-top:6px;margin-bottom:2px;">Feedback</h5>	
						<div id="feedbackId">
						</div>
						<h5 style="margin-bottom:2px;margin-top:2px;"> 
							Remarks</h5>						
							<textarea rows="1" id="remarksId" class="input-block-level"></textarea>
						<button type="button" class="btn btn-success btn-block border-radius-0 m-top10" onclick="saveFeedbackDetials();">SUBMIT</button>
					</div>				
				</div>
			</div>
		</div>
	</div>
		
	<script>
	
	function getCadreDetailsBySearchCriteria()
	{
		
		var searchTypeValue = $('input:radio[name=searchType]:checked').val();
		var mobileNo =	"";
		var trNumber = "";
		var membershipNo ="";
		
		var value = $('#searchedValue').val();		
		if(value == null || value.trim().length == 0)
		{
				$('#errorDiv').html('Please Enter the Number').css("color","red");	
				return false;
		}
		if(searchTypeValue == "mobile")
		{
		  mobileNo = $('#searchedValue').val();
		}
		if(searchTypeValue == "membership")
		{
		  membershipNo = $('#searchedValue').val();
		}
		if(searchTypeValue == "tr")
		{
		  trNumber = $('#searchedValue').val();
		}
		
		$('#errorDiv').html('');	
		$("#ajaxImage").show();
		var jsObj = {
			mobileNo:mobileNo,
			membershipNo:membershipNo,
			trNumber:trNumber,			
			task:"getDetailsForCallCenter"            
		}
  
		$.ajax({
			type : "POST",
			url : "getCadreDetailsForCallCenterAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#ajaxImage").hide();
			if(result != null && result.length == 1)
			{
				$("#tableDataDiv").html("");
				$("#cardMainDiv").show();
				buildCadreInfo(result);
			}
			else if(result != null && result.length > 1)
			{
				$("#cardMainDiv").hide();
				buildTableCadreInfo(result);
			}
			else
			{
				$("#tableDataDiv").html("");
				$("#cardMainDiv").show();
				$('#cardDisplayId').html(' <span style="font-weight:bold;"> No Data Available... </span>');
			}
		
		});
		getfeedbackDetails();
	}
	var cadreId = 0;	
	function buildCadreInfo(result)
	{
		$('#cardDisplayId').html('');
		var str='';	
		if(result != null && result.length>0)
		{
			for(var i in result)
			{
				cadreId = result[i].cadreId;
				str+='<div class="widget-body"     style="background:url(\'images/cadre_images/Membership_Card_Final_without_variables.jpg\')  no-repeat;width:518px;height:279px; padding-left:53px; padding-top:50px;">';
					
				
				str+='<div style="float: left;">';
				str+='<img src="http://www.mytdp.com/images/cadre_images/'+result[i].imageBase64String+'" style=" width:90px;	height:auto;" />';
				str+='</div>';
				str+='<b style="margin-left:20px;">'+result[i].previousEnrollmentNumber+'</b><br>';
				str+='<b style="margin-left:18px;">2014-2016</b><br>';
				if(result[i].voterName != null)
				str+='<b style="margin-left:8px;">'+result[i].voterName+'</b><br>';
				else
				str+='<b style="margin-left:8px;"></b><br>';
				if(result[i].panchayatId != null)
				{
					str+='<b style="margin-left:8px;"> '+result[i].panchayatId+'</b>';
				}
				if(result[i].mandalId != null)
				{
					str+='<b style="margin-left:10px;"> '+result[i].mandalId+'</b>';
				}				
				if(result[i].muncipalityId != null)
				{
					str+='<b style="margin-left:10px;"> '+result[i].muncipalityId+'</b>';
				}
				if(result[i].constituencyId != null)
				{
					str+='<br><b style="margin-left:8px;"> '+result[i].constituencyId+'</b> ';
				}
				if(result[i].address != null)
				{
					str+='<b style="margin-left:10px;">'+result[i].address+'</b>';
				}
				str+='</div>';
				str+='</div>';
				if(result[i].shipAddress != null)
				$("#statusDiv").html(result[i].shipAddress);
				else
				$("#statusDiv").html("");
			}
		}
	$('#cardDisplayId').html(str);
	}
	
	function buildTableCadreInfo(result){
		var str="";	
		str+='<div class="span12 widget" class="span7">';
			
		str+=' <table class="table table-bordered"  id="tableId">';
		str+=' <thead>';
		str+=' <tr>';
		str+=' <th class="alert-success"> Cadre Name  </th>';
		str+=' <th class="alert-info" > Membership Number  </th>';
		str+=' <th class="alert-info" > Mobile Number </th>';
		
		str+=' </tr>';
		str+=' </thead>';
		str+=' <tbody>';
	
		for(var i in result){
			str+=' <tr>';
			
			str+=' <td><a href="javascript:{};" style="cursor:pointer;" onclick="getCardDetails(\''+result[i].previousEnrollmentNumber+'\');">'+result[i].nameType+'</a></td>';
			str+=' <td>'+result[i].previousEnrollmentNumber+'</td>';
			str+=' <td>'+result[i].mobileNumber+'</td>';
			str+=' </tr>';
		}
		str+='</tbody></table></div>';
		$("#tableDataDiv").html(str);
		$("#tableId").dataTable();
	}
	
	function getCardDetails(membershipNo1){
		
		var jsObj = {
			mobileNo:"",
			membershipNo:membershipNo1,
			trNumber:"",			
			task:"getDetailsForCallCenter"            
		}
  
		$.ajax({
			type : "POST",
			url : "getCadreDetailsForCallCenterAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			
			if(result != null)
			{
				$("#cardMainDiv").show();
				buildCadreInfo(result);
			}
			else
			{
				$("#tableDataDiv").html("");
				$("#cardMainDiv").show();
				$('#cardDisplayId').html(' <span style="font-weight:bold;"> No Data Available... </span>');
			}
		
		});
	
	}
	
	
	function saveFeedbackDetials(){
		
		 var checkCount = $('input[name=feedbackCheck]:checked').length  	
		 var remarks=  $.trim($('#remarksId').val());
		 if(cadreId == 0){
			$('#errorDiv1').html('No Cadre Selected').css("color","red");
			return false;
		 }		 
		 else if(checkCount == 0){
			if(remarks == ''){
				$('#errorDiv1').html('Enter The Feedback').css("color","red");
				return false ;
				}
		 }
		var comments = "";
		$('#errorDiv1').html("");
		var selectedValues = $("input[name=feedbackCheck]:checked").map(function() {
			return this.value;
		}).get();
		for(var i in selectedValues){
			comments = comments+""+selectedValues[i]+",";
		}

		var jsObj = {
			tdpCadreId:cadreId,
			remarks:remarks,
			comments:comments,
			name:"",
			task:"saveFeedback"            
		}
  
		$.ajax({
			type : "POST",
			url : "saveCallCenterFeedbackAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null)
					{
						if(result.resultCode == 0)
						{
							$('#errorDiv1').html('<span style="color:green;">Feedback Submitted</span>');
							setTimeout(function(){$("#errorDiv1").html("");},2000)
							$('#remarksId').val('');
						}
						else
						{
							$('#errorDiv1').html('<span style="color:red;">Error Occured</span>');
						}
					}
		
		});
	
	}
	
	function getCadreDetailsBySearchCriteria1()
	{
		$("#ajaxImage").show();
		var jsObj = {
			mobileNo:mobileNumber,
			membershipNo:membership,
			trNumber:trNo,			
			task:"getDetailsForCallCenter"            
		}
  
		$.ajax({
			type : "POST",
			url : "getCadreDetailsForCallCenterAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#ajaxImage").hide();
			if(result != null && result.length == 1)
			{
				$("#tableDataDiv").html("");
				$("#cardMainDiv").show();
				buildCadreInfo(result);
			}
			else if(result != null && result.length > 1)
			{
				$("#cardMainDiv").hide();
				buildTableCadreInfo(result);
			}
			else
			{
				$("#tableDataDiv").html("");
				$("#cardMainDiv").show();
				$('#cardDisplayId').html(' <span style="font-weight:bold;"> No Data Available... </span>');
			}
		
		});
	}
	
	
	function getfeedbackDetails()
	{	
		var jsObj = {		
			task:"feedbackDetails"            
		}
  
		$.ajax({
			type : "POST",
			url : "getFeedbackDataAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			var str='';
			for(var i in result){
			str+='<label class="checkbox"> ';
			str+='<input type="checkbox" class="feedbackCls" name="feedbackCheck" value="'+result[i].id+'"> '+result[i].name+'</label>';
			}
			$("#feedbackId").html(str);
		});
	}
	if(mobileNumber != '' || membership != '' || trNo != ''){
		getCadreDetailsBySearchCriteria1();
		getfeedbackDetails();
	}
	</script>
	
  </body>
 </html>