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

<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
<script type="text/javascript" src="js/exportexcel.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-center{text-align: center;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.height-320{height: 300px; overflow: auto;width: 440px;}
	.f-16{font-size: 16px;}
	body {
    color: #333333;
    font-size: 14px;
    line-height: 20px;
    margin: 0;
    }
	p {
    color: #333;
    font-size: 14px;
   }
   .background {
    background: none repeat scroll 0 0 #e5e5e5;
   }
   .text-right {
    text-align: right;
   }
   .imgStyle{
      margin-left: 75px;
      margin-top: 30px;
	}

  #userStatusDialogDIV,#locationStatusDialogDIV{
    padding-top: 35px;
  }
	.survey_nav{height:38px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:38px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 12px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
	.highlight {
        cursor: pointer;
    }
	
	#usersStatusReportTab  thead th, #locationWiseReportTab  thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 20px !important;
			}
			
			#statedisplaydivid,#distdisplaydivid,#constdisplaydivid{display:none;}
			#statedisplaydivid1,#distdisplaydivid1,#constdisplaydivid1{display:none;}
			
	</style>	
</head>
<body>
	<!---->
	<div class="container m_top10">
	
	 <div class="row" style="margin-top:10px;">
		  <div class="span12 m_top20 survey_nav">
			<ul class="inline unstyled">
				<li><a onclick="showHideTabs(this.id);" id="reconsilationTab" class="highlight selected">Reconciliation</a></li>
				<li><a onclick="showHideTabs(this.id);" id="getSummaryTab" class="">Get Summary</a></li>
				<!--<li><a onclick="showHideTabs(this.id);" id="uploadTab" class=""> Upload Amount </a></li>-->
			</ul>
		  </div>
	</div>
	
			
		
	<div class="show-grid well well-small border-radius-0 mb-10 form-inline" id="reconsilationTabDiv">
			<!--<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">Reconciliation Report</h3>
				</div>
			</div>-->
			
			<div class="row-fluid " >
				<h3 style="text-align:center;margin:10px;"> CADRE REGISTRATION AMOUNT DETAILS REPORT</h3>
				<div style="margin-left:685px;">
					<a href="cadreRegAmountUpload.action" class="btn btn-success btn-mini"><b>Upload File</b></a>
				</div>
				<div id="errMsgDiv" align="center" ></div>
				<div class = "row" style="margin-top:20px;">
				<table  style="margin-left: 270px;">
					<tr><td><label>From Date :</label>&nbsp;</td><td>&nbsp;&nbsp;<input type="text" readonly="readonly" id="fromDate"/></td></tr>
				   <tr><td><label>To Date   :</label>&nbsp;</td><td>&nbsp;&nbsp;<input type="text" readonly="readonly" id="toDate" /></td></tr>		
				</table>
				</div>
			</div>
			
			<div class = "row-fluid " style="margin-left: 270px;" >
		
				<table><tr><td><input type="radio" name = "cadreAmountReport" value = "DateWiseReport" checked = "checked"/></td><td><label style="margin-top: 7px;">&nbsp;&nbsp;Date Wise Report</label><td></td><td>&nbsp;&nbsp;&nbsp;</td>
				<td><input type="radio" name = "cadreAmountReport" value="CumilativeReport"/></td><td><label style="margin-top: 7px;">&nbsp;&nbsp;Cumilative Report</label></td></tr></table>
			</br>
			<div class = "row-fluid " >
					<input type="Submit" class = "btn" value = "Submit" onClick = "cadreRegAmountReportAction()"/>
					<input type="button"    class="btn" onclick="generateExcel('seachDetalsTab2');" value="Click Here To Generate Excel"/>
					<img id="ajaxImgStyle" style="display:none" src="images/icons/search.gif"/>
			</div>
			</div>
			
			<div class="row-fluid ">
				<div id="usersDetails" style="overflow: scroll;"></div>
			</div>
			
	</div>
	
	<div class="show-grid well well-small border-radius-0 mb-10 form-inline" id="getSummaryTabDiv" style="display:none;">
		<div class="row-fluid span8" style="padding:10px;margin-left:250px;">
			<div style="margin:10px;float:left;">
				<label style="margin-top: 7px;font-weight:bold;"> SOURCE TYPE </label>	
			</div>
			
			<div style="margin:10px;float:left;">
				<input type="radio" name = "sourceType" value="all" id="all" checked = "checked"/>
				<label style="margin-top: 7px;font-weight:bold;"> Tab & Web </label>	
			</div>
			
			<div style="margin:10px;float:left;">
				<input type="radio" name = "sourceType" value = "tab" />
				<label style="margin-top: 7px;font-weight:bold;"> Tab </label>
			</div>
			
			<div style="margin:10px;float:left;">
				<input type="radio" name = "sourceType" value="web"/>
				<label style="margin-top: 7px;font-weight:bold;"> Web </label>	
			</div>
		</div>
				
		<div class="row-fluid ">
			<img id="summaryDetailsAjxImg" src="images/icons/search.gif" style="display:none;margin-left:400px;">
			<div id="summaryDetails"></div>
		</div>
	</div>
	
	<div class="show-grid well well-small border-radius-0 mb-10 form-inline" id="uploadTabDiv" style="display:none;">
		<div class="row-fluid ">
			
		</div>
	</div>
	
	
	</div>
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>></script>

	<script>
	
	  function showHideTabs(id){
    
		 if(id == "reconsilationTab"){
			$("#reconsilationTab").addClass("selected");
			$("#getSummaryTab").removeClass("selected");
			$("#uploadTab").removeClass("selected");
			
			$("#getSummaryTabDiv").hide();
			$("#reconsilationTabDiv").show();
			$("#uploadTabDiv").hide();
		 }
		 if(id == "getSummaryTab"){
			$("#reconsilationTab").removeClass("selected");
			$("#getSummaryTab").addClass("selected");
			$("#uploadTab").removeClass("selected");
			
			$("#getSummaryTabDiv").show();
			$("#reconsilationTabDiv").hide();
			$("#uploadTabDiv").hide();
		 }
		 if(id == "uploadTab"){
			$("#reconsilationTab").removeClass("selected");
			$("#getSummaryTab").removeClass("selected");
			$("#uploadTab").addClass("selected");
			
			$("#getSummaryTabDiv").hide();
			$("#reconsilationTabDiv").hide();
			$("#uploadTabDiv").show();
		 }
	}
	
	function generateExcel(reqId){
     tableToExcel(reqId, 'Users Working Status');
   }
		$("#fromDate").datepicker({
			dateFormat: "yy-mm-dd",
			changeMonth: true,
	        changeYear: true,
			maxDate: new Date()
		});
		$("#fromDate").datepicker("setDate", new Date());
		$("#toDate").datepicker({
			dateFormat: "yy-mm-dd",
			changeMonth: true,
	        changeYear: true,
			maxDate: new Date()
		});
		$("#toDate").datepicker("setDate", new Date());
	function cadreRegAmountReportAction(){ 
	
	$('#usersDetails').html('');
		$('#ajaxImgStyle').show();
	  $("#errMsgDiv").html('');
				var startDate = $("#fromDate").val();
				var endDate = $("#toDate").val();
				var reportValue=$('input[name=cadreAmountReport]:checked').val();
				//var reportValue="CumilativeReport";
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
			if(result != null && result.length > 0){
				if(reportValue == 'CumilativeReport'){
					buildUserData(result);
				}
				else{
					buildUserDataForDayWise(result);
				}
			}else{
				$('#usersDetails').html('<h4 style="text-align:center;color:red;margin:5px;"> Data Not Available </h4>');
			}
			$('#ajaxImgStyle').hide();
		});
	}
	
	function buildUserDataForDayWise(result)
	{
		$("#usersDetails").html("");
		var str = '';
			str +='<table class="table table-bordered table-striped table-hover"  id="seachDetalsTab2">';
				str +='<thead>';
					str +='<tr>';
						str +='<th rowspan = "2" class="text-align1">CONSTITUENCY</th>';
						str +='<th  rowspan = "2"  class="text-align1">USERNAME</th>';
						str +='<th  rowspan = "2"  class="text-align1">NAME</th>';
						str +='<th  rowspan = "2" class="text-align1">MOBILE</th>';
						str +='<th  rowspan = "2" class="text-align1">USER TYPE</th>';
						for(var i in result[0].infoList)
						{
							str+='<th colspan="4">'+result[0].infoList[i].date+'</th>';
						}
						
						str +='</tr>';
						str +='<tr>';
						if(result != null)
						{
							for(var i in result[0].infoList)
							{
								str +='<th class="text-align1">TOTAL RECORDS</th>';
								str +='<th class="text-align1">AMOUNT</th>';
								str +='<th class="text-align1">PAID</th>';
								str +='<th class="text-align1">DIFFERENCE</th>';
							}
						}
					str +='</tr>';
				str +='</thead>';
				str +='<tbody>';
				for(var i in result){
					if(result[i].constituency != null)
					{
						str +='<tr>';
						str +='<td class="text-align1">'+result[i].constituency+'</td>';
						str +='<td class="text-align1">'+result[i].userName+'</td>';
						str +='<td class="text-align1">'+result[i].name+'</td>';
						str +='<td class="text-align1">'+result[i].mobileNo+'</td>';
						str +='<td class="text-align1">'+result[i].userType+'</td>';
						for(var j in result[i].infoList)
						{
							str +='<td class="text-align1">'+result[i].infoList[j].totalCount+'</td>';
							str +='<td class="text-align1">'+result[i].infoList[j].totalAmount+'</td>';
							str +='<td class="text-align1">'+result[i].infoList[j].paidAmount+'</td>';
							str +='<td class="text-align1">'+result[i].infoList[j].difference+'</td>';
						}
						
						
						str +='</tr>';
					}
					
				}
				str +='</tbody>';
			str +='</table>';
			
			$('#usersDetails').html(str);
			
			$('#seachDetalsTab2').dataTable({
				"iDisplayLength": -1,
				"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
			});
			
			$('#ajaxImgStyle').hide();
	}
	function buildUserData(results){
		$("#usersDetails").html("");
		var str = '';
			str +='<table class="table table-bordered table-striped table-hover"  id="seachDetalsTab2">';
				str +='<thead>';
					str +='<tr>';
						str +='<th class="text-align1">CONSTITUENCY</th>';
						str +='<th class="text-align1">USERNAME</th>';
						str +='<th class="text-align1">NAME</th>';
						str +='<th class="text-align1">MOBILE</th>';
						str +='<th class="text-align1">USER TYPE</th>';
						str +='<th class="text-align1">TOTAL RECORDS</th>';
						str +='<th class="text-align1">AMOUNT</th>';
						str +='<th class="text-align1">PAID</th>';
						str +='<th class="text-align1">DIFFERENCE</th>';
					str +='</tr>';
				str +='</thead>';
				str +='<tbody>';
				for(var i in results){
					if(results[i].constituency != null)
					{
						str +='<tr>';
						str +='<td class="text-align1">'+results[i].constituency+'</td>';
						str +='<td class="text-align1">'+results[i].userName+'</td>';
						str +='<td class="text-align1">'+results[i].name+'</td>';
						str +='<td class="text-align1">'+results[i].mobileNo+'</td>';
						str +='<td class="text-align1">'+results[i].userType+'</td>';
						str +='<td class="text-align1">'+results[i].totalCount+'</td>';
						str +='<td class="text-align1">'+results[i].totalAmount+'</td>';
						str +='<td class="text-align1">'+results[i].paidAmount+'</td>';
						str +='<td class="text-align1">'+results[i].difference+'</td>';
						str +='</tr>';
					}
					
				}
				str +='</tbody>';
			str +='</table>';
			
			$('#usersDetails').html(str);
			
		 
			$('#seachDetalsTab2').dataTable({
				"iDisplayLength": -1,
				"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
			});
			
			$('#ajaxImgStyle').hide();
	}
	
	$("input:radio[name=sourceType]").click(function() {
		var sourceType = $(this).val();
		getSummaryAmounts(sourceType);
	});
	
	
	function getSummaryAmounts(srcType){
		$("#summaryDetails").html("");
		$("#summaryDetailsAjxImg").show();
		var sourceType = srcType;
		$.ajax({
          type:'GET',
          url: 'getCadreRegAmountSummaryAction.action',
          data: {task:"cadreAmountReportSummary",sourceType :sourceType}
		}).done(function(result){
			buildSummary(result);
		});
	}
	
	function buildSummary(results){
		$("#summaryDetails").html("");
		var str = '';
			str +='<h4 style="text-align:center;"> CADRE AMOUNT SUMMARY DETAILS </h4>';
			str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="summaryTable">';
				str +='<thead>';
					str +='<tr>';
						str +='<th class="text-align1">DATE</th>';
						str +='<th class="text-align1">RECORDS</th>';
						str +='<th class="text-align1">TOTAL AMOUNT</th>';
						str +='<th class="text-align1">PAID AMOUNT</th>';
						str +='<th class="text-align1">DIFFERENCE</th>';
					str +='</tr>';
				str +='</thead>';
				str +='<tbody>';
				for(var i in results){
					str +='<tr>';
						str +='<th class="text-align1">'+results[i].date+'</th>';
						str +='<th class="text-align1">'+results[i].totalCount+'</th>';
						str +='<th class="text-align1">'+results[i].totalAmount+'</th>';
						str +='<th class="text-align1">'+results[i].paidAmount+'</th>';
						str +='<th class="text-align1">'+results[i].difference+'</th>';
					str +='</tr>';
				}
				str +='</tbody>';
			str +='</table>';
			
			$('#summaryDetails').html(str);
			
			$("#summaryDetailsAjxImg").hide();
			
			$('#summaryTable').dataTable({
				"iDisplayLength": -1,
				"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
			});
	}
	
	$("#getSummaryTab").click(function(){
		$("#all").prop("checked", true)
		getSummaryAmounts("all");
	});
	</script>
</body>
</html>