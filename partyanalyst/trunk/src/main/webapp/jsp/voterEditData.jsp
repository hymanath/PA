<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
<script type="text/javascript" language="javascript" src="js/jQuery.min.js"></script>
<!--<script type="text/javascript" language="javascript" src="js/datatable.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> -->
<script src="js/sendUpdatesBySMS.js"></script>
<script src="js/jquery.mask.min.js"></script>

<style type="text/css">
select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 150px;
	}
	select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input{
		border-radius: 4px 4px 4px 4px;
		color: #000000;
		display: inline-block;
		font-size: 13px;
		line-height: 18px;
		padding: 4px;
	}
	input, button, select {
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		 border: 1px solid lightBlue;
	}

	#voterDetailsDiv table{border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;
	width:100%;background:#EdF5FF;padding:8px;padding-left:10px;font-weight:normal;
	font:small-caption;color: #676A67;}
	
	#voterDetailsDiv table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}

		#voterDetailsDiv table tr:nth-child(even){background:#EdF5FF;}

		#voterDetailsDiv table th a{
			color:#333333;

		}
		
		#voterDetailsDiv{
		font-family : arial;
		font-size: 13px;
		margin-top: 20px;
		padding: 10px 10px 10px 15px;
		
		}
		
			#voterDetailsDiv table th{
				padding:5px !important;

			}

			#voterDetailsDiv{padding-left: 2px !important; }

			#voterDetailsDiv table td{ padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
			
			#voterDetailsDiv table td{padding-right:3px !important;} 

			#requiredValue{
			color:red;
			font-size:large;
			}
			
			span {
			font-size: 13px;
			
			}

			#mainDIV{
		border: 1px lightBlue solid ;
		width: 982px; 
		height: auto;
		margin-bottom: 15px;
		margin-left:auto;
		margin-right:auto;
		background-color:white;
		font-weight:bold;
		border-radius: 5px 5px 5px 5px;
		}	

		.textClass{
			width: 100px; 
			height: 11px;
		
		}

		.textClass1{
			width: 44px; 
			height: 11px;
		
		}

</style>
<script>
$(document).ready(function(){
	$('.genderClass').live("keyup",function() {
		$("#statusMsgDiv").html('');
		var tal = $(this).val();
		if(isNaN(tal)) {
		}
		else
		{
			$("#statusMsgDiv").html('Gender must be Character only');
			return false;
		}
	});

	$(".numericClass").live('keydown',function (event) {
    var num = event.keyCode;
    if ((num > 95 && num < 106) || (num > 36 && num < 41) || num == 9) {
        return;
    }
    if (event.shiftKey || event.ctrlKey || event.altKey) {
        event.preventDefault();
    } else if (num != 46 && num != 8) {
        if (isNaN(parseInt(String.fromCharCode(event.which)))) {
            event.preventDefault();
        }
    }
});
});
</script>
</head>
	
<div id="mainDIV">

<h4 align="center" style="height: 30px;margin-top: 10px;"> Voters Details Updation </h4>

<div id="msgDiv" style="font-weight: bold; color: red; float: left; width: 815px;margin-left: 200px;"></div>

<div id="constituencyDiv" style="height: 45px; padding-top: 36px;margin-left: 200px;">
<span>Assembly Constituency :<font id="requiredValue" class="requiredFont">*</font></span>
<span><s:select id="userAccessConstituencyList"  name="userAccessConstituencyList" theme="simple"
list="userAccessConstituencyList" listKey="id" listValue="name"  onchange="getMandalList(this.options[this.selectedIndex].value);getPublicationDate();" style="margin-left: 40px;"></s:select></span>
<div style="display:inline-block; margin-right: -18px;display:none;" id="mandalInfoAjaxImg"><img src="./images/icons/search.gif"></div>
<span style="margin-left: 30px;">Publication Date:<font id="requiredValue" class="requiredFont">*</font></span>
<span><select id="publicationDateList" style="margin-left: 20px;"  name="publicationDateList" class="dateClass" onchange=""></select></span>
</div>

<!--<div id="dateDiv" style="height: 45px;">
<span>Publication Date:<font id="requiredValue" class="requiredFont">*</font></span>
<span><select id="publicationDateList"  name="publicationDateList" class="dateClass" onchange="" style="margin-left: 96px;"></select></span>
</div>-->

<div id="mmcorpDiv" style="height: 45px;margin-left: 200px;">
<span>Mandal / Muncipality / Corp:<font id="requiredValue" class="requiredFont">*</font></span>
<span><select id="mandalList"  name="mandalList" class="mandalClass" onchange="getPanchayatListForVoter('panchayat','panchayatField');" style="margin-left: 15px;"></select></span>
<div style="display:inline-block; margin-right: -18px;display:none;" id="wardInfoAjaxImg"><img src="./images/icons/search.gif"></div>
</div>

<div id="panWardDiv" style="height: 45px;margin-left: 200px;">
<span>Panchayat / Ward:<font id="requiredValue" class="requiredFont">*</font></span>
<span><select id="panchayatList"  name="panchayatList" class="pListClass"  onchange="getBoothInfo(this.options[this.selectedIndex].value);" style="margin-left: 78px;"></select>
</span>
<div style="display:inline-block; margin-right: -18px;display:none;" id="boothInfoAjaxImg"><img src="./images/icons/search.gif"></div>
</div>

<div id="boothDiv" style="height: 45px;margin-left: 200px;">
<span>Booth:<font id="requiredValue" class="requiredFont">*</font></span>
<span><select id="boothList"  name="boothList" class = "boothClass" style="margin-left: 153px;"></select>

</span>
</div>

<div id="rangeDiv" style="margin-left: 200px;">
<span>Starting Serial No:<font id="requiredValue" class="requiredFont">*</font></span><input type="text" id="startIndex" name="startIndex" class="numericClass"
style="margin-left: 5px; margin-right: 16px; width: 60px;"/>
<span>Ending Serial No:<font id="requiredValue" class="requiredFont">*</font></span><input type="text" style="width: 60px;margin-left: 5px;" id="endIndex" name="endIndex" class="numericClass"/>
</div>

<div style="height: 45px;margin-left: 240px;">
<input style="margin-left: 203px; margin-top: 20px;" type="button" id="search" class="btn-info btn-small" value = "Search" onclick = "getVotersInfoByBoothId()"/>
</div>

<div id="statusMsgDiv" style="text-align: center;font-weight: bold; color: blue; float: left; width: 450px;margin-left: 240px;"></div>

<div id="savediv" style="display: none; float: right; width: 90px; margin-top: 40px;"> <input type="button" value="Save" id="save"  class="btn-info btn-small" onclick="updateVoterDetails()"/></div>

<div>
<form id="voterBasicDetailsForm" method="post" action="getVoterDetailsForEditAction.action" name="voterBasicDetailsForm">
		<input type="hidden" name="task" id="voterBasicDetailsFormValues" />
<div id="voterDetailsDiv"></div>
</form>
</div>

<div>
<div id="savediv1"style="display: none;float: right; width: 88px; margin-top: 10px;"> <input type="button" value="Save" id="save"  class="btn-info btn-small" onclick="updateVoterDetails()"/></div>
<div style="float: right; margin-right: -70px; margin-top: 12px;display:none;" id="saveAjaxImg"><img src="./images/icons/search.gif"></div>
</div>

<div id="successDiv" style="text-align: center;margin-top: 15px;margin-left: 240px;width:450px;"></div>

</div>
</html>