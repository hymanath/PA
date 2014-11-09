<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Registration Amount Report</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
</head>
<body>
	<div class="container m_top10">
			<div class="row-fluid ">
				<div id="errMsgDiv" align="center" ></div>
				<div class = "row" style="margin-top:20px;">
				<table  style="margin-left: 270px;">
					<tr><td><label>From Date :</label>&nbsp;</td><td>&nbsp;&nbsp;<input type="text" readonly="readonly" id="fromDate"/></td></tr>
				   <tr><td><label>To Date   :</label>&nbsp;</td><td>&nbsp;&nbsp;<input type="text" readonly="readonly" id="toDate" /></td></tr>		
				</table>
				</div>
			</div>
			<div class = "row-fluid " style="margin-left: 270px;" >
			<div class = "row">
				<table><tr><td><input type="radio" name = "cadreAmountReport" value = "DateWiseReport" checked = "checked"/></td><td><label>&nbsp;&nbsp;Date Wise Report</label><td></td><td>&nbsp;&nbsp;&nbsp;</td>
				<td><input type="radio" name = "cadreAmountReport" value="CumilativeReport"/></td><td><label>&nbsp;&nbsp;Cumilative Report</label></td></tr></table>
			</div><br/>
			<div class = "span6" style="margin-left:100px;margin-bottom:50px;">
					<input type="Submit" class = "btn" value = "Submit" onClick = "cadreRegAmountReportAction()"/>
			</div>
			</div>
	</div>
	
	<script>
		$("#fromDate").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
	        changeYear: true,
			maxDate: new Date()
		});
		$("#fromDate").datepicker("setDate", new Date());
		$("#toDate").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
	        changeYear: true,
			maxDate: new Date()
		});
		$("#toDate").datepicker("setDate", new Date());
	function cadreRegAmountReportAction(){ 
	  $("#errMsgDiv").html('');
				var startDate = $("#fromDate").val();
				var endDate = $("#toDate").val();
				var reportValue=$('input[name=cadreAmountReport]:checked').val();
				var errMsg="";
				
				if(startDate.trim().length >0 && endDate.trim().length >0)
		{
			
		    var arrr = startDate.split("-");
		    var fromDat=arrr[0];
			var frommonth=arrr[1];
			var fromyear=arrr[2];
	   var arr = endDate.split("-");
			var toDat=arr[0];
			var tomonth=arr[1];
			var toyear=arr[2];
			
			if(fromyear>toyear){
				 errMsg ="From Date should not greater than To Date";		
			}
			 if(frommonth>tomonth){
				 if(fromyear == toyear){
					 errMsg ="From Date should not greater than To Date";		
				}
				
			}
			
			if(fromDat>toDat){	
				if(frommonth == tomonth && fromyear == toyear){			
					errMsg ="From Date should not greater than To Date";		
			   }
			}	
		}
		
			if(errMsg.length > 0){
			  $("#errMsgDiv").html('<div style="font-weight:bold;color:red;margin-bottom:8px;margin-left:400px;margin-top:5px;">'+errMsg+'</div>');
			  return;
			}
			
		$.ajax({
          type:'GET',
          url: 'cadreRegAmountReportAction.action',
          data: {task:"cadreAmountReport",startDate:startDate,endDate:endDate,reportValue:reportValue}
		}).done(function(result){
			
		});
	}
	</script>
</body>
</html>