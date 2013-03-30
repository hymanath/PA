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
    width: 250px;
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
		margin-left: 151px;
		margin-bottom: 15px;
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
	
<div id="mainDIV" align="center">

<h4 align="center" style="height: 30px;margin-top: 10px;"> Voters Details Updation </h4>

<div id="msgDiv" style="font-weight: bold; color: red; float: left; width: 815px;"></div>

<div id="constituencyDiv" style="height: 45px; padding-top: 36px;">
<span>Assembly Constituency :<font id="requiredValue" class="requiredFont">*</font></span>
<span><s:select id="userAccessConstituencyList"  name="userAccessConstituencyList" theme="simple"
list="userAccessConstituencyList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Constituency"  onchange="getMandalList(this.options[this.selectedIndex].value);" style="margin-left: 53px;"></s:select></span>
<div style="display:inline-block; margin-right: -18px;display:none;" id="mandalInfoAjaxImg"><img src="./images/icons/search.gif"></div>
</div>


<div id="mmcorpDiv" style="height: 45px;">
<span>Mandal / Muncipality / Corp:<font id="requiredValue" class="requiredFont">*</font></span>
<span><select id="mandalList"  name="mandalList" class="mandalClass" onchange="getPanchayatList('panchayat','panchayatField');" style="margin-left: 26px;"></select></span>
<div style="display:inline-block; margin-right: -18px;display:none;" id="wardInfoAjaxImg"><img src="./images/icons/search.gif"></div>
</div>

<div id="panWardDiv" style="height: 45px;">
<span>Panchayat / Ward:<font id="requiredValue" class="requiredFont">*</font></span>
<span><select id="panchayatList"  name="panchayatList" class="pListClass"  onchange="getBoothInfo(this.options[this.selectedIndex].value);" style="margin-left: 88px;"></select>
</span>
<div style="display:inline-block; margin-right: -18px;display:none;" id="boothInfoAjaxImg"><img src="./images/icons/search.gif"></div>
</div>

<div id="boothDiv" style="height: 45px;">
<span>Booth:<font id="requiredValue" class="requiredFont">*</font></span>
<span><select id="boothList"  name="boothList" class = "boothClass" style="margin-left: 164px;"></select>

</span>
</div>

<div>
<span style="font-weight: bold; color: blue; float: left; width: 981px; padding-bottom: 6px;">Select serial No Ranges with Difference atmost 20</span>
</div>

<div id="rangeDiv">
<span style="padding-left: 26px;">Starting Serial No:<font id="requiredValue" class="requiredFont">*</font></span><input type="text" id="startIndex" name="startIndex" class="numericClass"
style="padding-left: 11px; margin-left: 4px; margin-right: 16px;"/>
<span>Ending Serial No:<font id="requiredValue" class="requiredFont">*</font></span><input type="text" id="endIndex" name="endIndex" class="numericClass"/>
</div>

<div style="height: 45px;">
<input type="button" id="search" class="btn-info btn-small" value = "Search" onclick = "getVotersInfoByBoothId()"/>
</div>

<div id="statusMsgDiv" style="font-weight: bold; color: blue; float: left; width: 983px;"></div>

<div id="savediv" style="display:none;"> <input type="button" value="Save" id="save"  class="btn-info btn-small" onclick="updateVoterDetails()"/></div>

<div id="voterDetailsDiv"></div>

<div id="savediv1" style="display:none;"> <input type="button" value="Save" id="save"  class="btn-info btn-small" onclick="updateVoterDetails()"/></div>

</div>
</html>