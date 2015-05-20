<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Committee Dashboard</title>
	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/css/style.css" rel="stylesheet"/>
    	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<style>
		tr.lowPerf{background:#FFA500 !important;}
		tr.lowPerf td{background:#FFA500 !important;}
		#cadrePerfTableId  thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 15px !important;
			}
		#cadrePerfTableId th{text-align:center !important;}
	#tableId tr.odd td.sorting_1{
    background-color: #d3d3d3 !important;
}
#tableId tr.even td.sorting_1 {
    background-color: #fafafa !important;
}
#tableId tr.odd {
    background-color: #E5E5E5 !important;
}

	</style>	
</head>
<body>
	
	<div class="container">
		<span class="btn btn-info pull-right exprtToExcel" style="display:none;">Export To Excel</span>
		<div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3 m_top10 " style="height: 49px;padding: 10px;background-color:height: 49px;padding: 10px;background-color:#B1BDC9;border-radius: 5px;">
					<label>Select District</label>
						<select id="districtid" class="selectBoxWidth" name="select district" style="margin-left:12px;margin-bottom:5px;width: 325px;height: 30px" onchange="gettingCadreDetailsPerformance(11,this.value)">
						<option value="0">Select District</option>
						</select>
			</div>
        </div>
		
    	<div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 ">
                    <h4 class="panel-header" style="text-transform: uppercase;" id="distName"></h4>
                    <hr style="border-color:#F00;margin:0px 0px 10px 0px;" />
                </div>
        </div>
		<div class="row" style="text-align:center;">
			<div id="cadrePerfId"><img style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></div>
			<div id="cadreTablePerfId" style="overflow:scroll;display:none;"></div>
		</div>

		<div id="membersDisplayDialog" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					 <div class="modal-header">
					  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
						<h4 class="panel-header text-center"></h4>
					  </div>
						<div id="memberDetailsDiv" style="margin-top:15px;padding:15px;margin-bottom:20px;"></div>
				</div>
			</div>
    </div>
	
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
    <script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
    <!--Jquery Sparkline-->
    <script src="js/cadreCommittee/js/jquery.sparkline.js" type="text/javascript"></script>
    <!-- Custom JS File-->
    <script src="js/cadreCommittee/js/custom.js" type="text/javascript"></script>
    <!--Hover Menu-->
    <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<script>
		var detailsArr;
		var locationId = "${distId}";
		var locationTypeId = 11;
		gettingCadreDetailsPerformance(locationTypeId,locationId);
		function gettingCadreDetailsPerformance(lctnTypeId,lctnId){
			$("#cadreTablePerfId").hide();
			$("#cadrePerfId").show();
			$("#cadrePerfId").html('<img style="width:80px;height:50px;display:block;margin-left:auto;margin-right:auto;"  src="./images/Loading-data.gif" alt="Processing Image"/>');
			var distNameStr = $('#districtid option:selected').text();
			$('#distName').html(''+distNameStr+' COMMITTEE MEMBERS BOOTH INFLUENCE');
			var jsObj={
					 locationId:lctnId,locationTypeId:lctnTypeId
				   };
				   
			 $.ajax({
				type : "GET",
				url : "gettingPerformanceOfCadreAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#cadreTablePerfId").show();
				buildCadrePerformanceDetails(result);
			});
		}
		
		function buildCadrePerformanceDetails(result){
			detailsArr = new Array();
			detailsArr = result;
			var str = "";
			//$("#cadrePerfId").html('<img style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/>');
			if(result!=null && result.length>0){
				
			str +='<table id="summaryTable" class="table table-bordered table-striped" style="width:50%;margin-left:auto;margin-right:auto;">';
			str+='<tr>';
			str+='<td style="text-align:left;">TOTAL MEMBERS </td>';
			if(result[0].totalMembs!=null){
				str+='<td>'+result[0].totalMembs+'</td>';
			}else{
				str+='<td> - </td>';
			}
			str+='</tr>';
			str+='<tr>';
			str+='<td style="text-align:left;">MAIN COMMITTEE MEMBERS</td>';	
			if(result[0].mainCmmtteeMembs!=null && result[0].mainCmmtteeMembs > 0){			
				//str+='<td>'+result[0].mainCmmtteeMembs+'</td>';
				
				str += '<td style="text-align:center" ><a href="javascript:{getMemberDetails(\'false\',\'Main\');}">'+result[0].mainCmmtteeMembs+'</a></td>';
				
				
			}else{
				str+='<td> 0 </td>';
			}
			str+='</tr>';
			str+='<tr>';
			str+='<td style="text-align:left;">MAIN COMMITTEE LOW PERFORMANCE MEMBERS</td>';	
			if(result[0].lowPerfMainCmmtteeMembs!=null && result[0].lowPerfMainCmmtteeMembs > 0){
				//str+='<td>'+result[0].lowPerfMainCmmtteeMembs+'</td>';
				str += '<td style="text-align:center" ><a href="javascript:{getMemberDetails(\'true\',\'Main\');}">'+result[0].lowPerfMainCmmtteeMembs+'</a></td>';
				
				
			}else{
				str+='<td> 0 </td>';
			}
			str+='</tr>';
			
			str+='<tr>';
			str+='<td style="text-align:left;">AFFLIATED COMMITTEE MEMBERS</td>';
			if(result[0].afflCmmtteeMembs!=null && result[0].afflCmmtteeMembs > 0 ){
				//str+='<td>'+result[0].afflCmmtteeMembs+'</td>';
				str += '<td style="text-align:center" ><a href="javascript:{getMemberDetails(\'false\',\'affl\');}">'+result[0].afflCmmtteeMembs+'</a></td>';
			}else{
				str+='<td> 0 </td>';
			}
			str+='</tr>';
			
			str+='<tr>';
			str+='<td style="text-align:left;">AFFLIATED COMMITTEE LOW PERFORMANCE MEMBERS</td>';
			if(result[0].lowPerfAfflCmmtteeMembs!=null && result[0].lowPerfAfflCmmtteeMembs > 0){
				//str+='<td>'+result[0].lowPerfAfflCmmtteeMembs+'</td>';
				
				str += '<td style="text-align:center" ><a href="javascript:{getMemberDetails(\'true\',\'affl\');}">'+result[0].lowPerfAfflCmmtteeMembs+'</a></td>';
			}else{
				str+='<td> 0 </td>';
			}
			str+='</tr>';
			str+='</table>';
				
				

				$("#cadrePerfId").html(str);
				
				str='';
				$(".exprtToExcel").show();
				str+="<table id='cadrePerfTableId' class='table table-bordered'>";
					str+="<thead>";
					str+="<tr>";
						str+="<th>CONSTITUENCY</th>";					
						str+="<th>NAME</th>";
						str+="<th>CASTE</th>";
						str+="<th>CASTE CATEGORY</th>";
						str+="<th>COMMITTEE TYPE</th>";
						str+="<th>DESIGNATION</th>";
						str+="<th>MOBILE</th>";
						str+="<th>BOOTH INFLUENCE</th>";
						str+="<th>PARTY JOINING DATE </th>";
						str+="<th>VOTER CARD NO</th>";
						str+="<th>BOOTH NO</th>";
						str+="<th>OWN CONSTITUENCY %</th>";
						str+="<th>OWN MANDAL/MUNCIPALITY %</th>";
						str+="<th>OWN PANCHAYAT %</th>";
						str+="<th>OWN BOOTH %</th>";
						str+="<th>MEMBERSHIP NO %</th>";
						
						str+="<th>GENDER</th>";
						str+="<th>AGE</th>";
						
						
					str+="</tr>";
					str+="</thead>";
					str+="<tbody>";
						for(var i in result){
							if(result[i].lowPerformance){
								str+="<tr class='lowPerf'>";
							}else{
								str+="<tr>";
							}
								str+="<td>"+result[i].constituencyName+"</td>";
								str+="<td>"+result[i].name+"</td>";
								
								str+="<td>"+result[i].casteName+"</td>";
								str+="<td>"+result[i].casteGroupName+"</td>";
								str+="<td>"+result[i].committe+"</td>";
								str+="<td>"+result[i].role+"</td>";
								str+="<td>"+result[i].mobileNo+"</td>";
								str+="<td>"+parseFloat(result[i].mandalBoothCmpr).toFixed(2)+"</td>";
								if(result[i].fromDate!=null){
									str+="<td>"+result[i].fromDate+"</td>";								
								}
								else{
									str+="<td> - </td>";	
								}
								str+="<td>"+result[i].voterCardNo+"</td>";
								str+="<td>"+result[i].partNo+"</td>";
								if(result[i].ownConstiPerc!=null){
									str+="<td>"+result[i].ownConstiPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								if(result[i].ownMandalPerc!=null){
									str+="<td>"+result[i].ownMandalPerc+"</td>";
								}else if(result[i].ownMunciPerc!=null){
									str+="<td>"+result[i].ownMunciPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								if(result[i].ownPanchPerc!=null){
									str+="<td>"+result[i].ownPanchPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								if(result[i].ownBoothPerc!=null){
									str+="<td>"+result[i].ownBoothPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								
								str+="<td>"+result[i].membershipNo+"</td>";
								str+="<td>"+result[i].gender+"</td>";
								str+="<td>"+result[i].age+"</td>";
								
							str+="</tr>";
						}
					str+="<tbody>";
				str+="</table>";
				
				str +="<div id='cadrePerfTableIdToExport' style='display:none;'>";
				
				str +='<table id="summaryTableDup" class="table table-bordered table-striped" style="width:50%;margin-left:auto;margin-right:auto;">';
				str+='<tr>';
				str+='<td style="text-align:left;">TOTAL MEMBERS </td>';
				if(result[0].totalMembs!=null){
					str+='<td>'+result[0].totalMembs+'</td>';
				}else{
					str+='<td> - </td>';
				}
				str+='</tr>';
				str+='<tr>';
				str+='<td style="text-align:left;">MAIN COMMITTEE MEMBERS</td>';	
				if(result[0].mainCmmtteeMembs!=null){			
					str+='<td>'+result[0].mainCmmtteeMembs+'</td>';
				}else{
					str+='<td> - </td>';
				}
				str+='</tr>';
				str+='<tr>';
				str+='<td style="text-align:left;">MAIN COMMITTEE LOW PERFORMANCE MEMBERS</td>';	
				if(result[0].lowPerfMainCmmtteeMembs!=null){
					str+='<td>'+result[0].lowPerfMainCmmtteeMembs+'</td>';
				}else{
					str+='<td> - </td>';
				}
				str+='</tr>';
				
				str+='<tr>';
				str+='<td style="text-align:left;">AFFLIATED COMMITTEE MEMBERS</td>';
				if(result[0].afflCmmtteeMembs!=null){
					str+='<td>'+result[0].afflCmmtteeMembs+'</td>';
				}else{
					str+='<td> - </td>';
				}
				str+='</tr>';
				
				str+='<tr>';
				str+='<td style="text-align:left;">AFFLIATED COMMITTEE LOW PERFORMANCE MEMBERS</td>';
				if(result[0].lowPerfAfflCmmtteeMembs!=null){
					str+='<td>'+result[0].lowPerfAfflCmmtteeMembs+'</td>';
				}else{
					str+='<td> - </td>';
				}
				str+='</tr>';
				str+='</table>';
				
				str +="<table class='table table-bordered' style='display:none;'>";
					str+="<thead>";
					str+="<tr>";
						str+="<th>CONSTITUENCY</th>";					
						str+="<th>NAME</th>";
						str+="<th>CASTE</th>";
						str+="<th>CASTE CATEGORY</th>";
						str+="<th>COMMITTEE TYPE</th>";
						str+="<th>DESIGNATION</th>";
						str+="<th>MOBILE</th>";
						str+="<th>PARTY JOINING DATE </th>";
						str+="<th>VOTER CARD NO</th>";
						str+="<th>BOOTH NO</th>";
						str+="<th>OWN CONSTITUENCY %</th>";
						str+="<th>OWN MANDAL/MUNCIPALITY %</th>";
						str+="<th>OWN PANCHAYAT %</th>";
						str+="<th>OWN BOOTH %</th>";
						str+="<th>MEMBERSHIP NO %</th>";
						
						str+="<th>GENDER</th>";
						str+="<th>AGE</th>";
						
						
					str+="</tr>";
					str+="</thead>";
					str+="<tbody>";
						for(var i in result){
							if(result[i].lowPerformance){
								str+="<tr class='lowPerf'>";
							}else{
								str+="<tr>";
							}
								str+="<td>"+result[i].constituencyName+"</td>";
								str+="<td>"+result[i].name+"</td>";
								str+="<td>"+result[i].casteName+"</td>";
								str+="<td>"+result[i].casteGroupName+"</td>";
								str+="<td>"+result[i].committe+"</td>";
								str+="<td>"+result[i].role+"</td>";
								str+="<td>"+result[i].mobileNo+"</td>";
								if(result[i].fromDate!=null){
									str+="<td>"+result[i].fromDate+"</td>";								
								}
								else{
									str+="<td> - </td>";	
								}
								str+="<td>"+result[i].voterCardNo+"</td>";
								str+="<td>"+result[i].partNo+"</td>";
								if(result[i].ownConstiPerc!=null){
									str+="<td>"+result[i].ownConstiPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								if(result[i].ownMandalPerc!=null){
									str+="<td>"+result[i].ownMandalPerc+"</td>";
								}else if(result[i].ownMunciPerc!=null){
									str+="<td>"+result[i].ownMunciPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								if(result[i].ownPanchPerc!=null){
									str+="<td>"+result[i].ownPanchPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								if(result[i].ownBoothPerc!=null){
									str+="<td>"+result[i].ownBoothPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								
								str+="<td>"+result[i].membershipNo+"</td>";
								str+="<td>"+result[i].gender+"</td>";
								str+="<td>"+result[i].age+"</td>";
								
							str+="</tr>";
						}
					str+="<tbody>";
				str+="</table>";
				str+="</div>";
				
				$("#cadreTablePerfId").html(str);
				$("#cadrePerfTableId").dataTable({
					"iDisplayLength": 15,
					"aLengthMenu": [[15, 50, 100, -1], [15, 50, 100, "All"]]
				});
			}else{
				$(".exprtToExcel").hide();
				str+="<h4>No Data Available</h4>";
				$("#cadreTablePerfId").html(str);
				$("#cadrePerfId").hide();
			}
			
			
			
		}
		
		$(".exprtToExcel").click(function(){
			var distNameStr = $('#districtid option:selected').text();
			 tableToExcel('cadrePerfTableIdToExport', ''+distNameStr.toUpperCase()+' DISTRICT Committee Members Performance');
		});
		
		var tableToExcel = (function() {
			var uri = 'data:application/vnd.ms-excel;base64,'
			, template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
			, base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
			, format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
		  return function(table, name) {
			if (!table.nodeType) table = document.getElementById(table)
			var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
			window.location.href = uri + base64(format(template, ctx))
		  }
		})()
		getDistrictNamesIds();
		function getDistrictNamesIds(){
			var jsObj={};
			 $.ajax({
				type : "GET",
				url : "getDistrictNamesIdsAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result!=null){
					for(var i in result){
						if(locationId==result[i].id){
							$('#districtid').append('<option value="'+result[i].id+'" selected>'+result[i].name+'</option>');
							$("#distName").html(result[i].name+" COMMITTEE MEMBERS BOOTH INFLUENCE");
						}else{
							$('#districtid').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}
					}
					
				}

			});
		}
		function getMemberDetails(type,committee){
		
			var str="";
			str+="<div>";
			str+="<table class='table table-bordered' id='tableId'><thead>";
			str+="<tr>";
			str+="<th>CONSTITUENCY</th>";					
			str+="<th>NAME</th>";
			str+="<th>COMMITTEE TYPE</th>";
			str+="<th>DESIGNATION</th>";
			str+="<th>MOBILE</th>";
			str+="</tr>";
			str+="</thead>";
			str+="<tbody>";
			for(var i in detailsArr){
		
				if(committee == detailsArr[i].committe ){
					if(type =="true"){
						if(type.indexOf(detailsArr[i].lowPerformance) == 0 ){
							str+="<tr>";
							str+="<td>"+detailsArr[i].constituencyName+"</td>";
							str+="<td>"+detailsArr[i].name+"</td>";
							str+="<td>"+detailsArr[i].committe+"</td>";
							str+="<td>"+detailsArr[i].role+"</td>";
							str+="<td>"+detailsArr[i].mobileNo+"</td>";
							str+="</tr>";
						}
					}	
					else {
							str+="<tr>";
							str+="<td>"+detailsArr[i].constituencyName+"</td>";
							str+="<td>"+detailsArr[i].name+"</td>";
							str+="<td>"+detailsArr[i].committe+"</td>";
							str+="<td>"+detailsArr[i].role+"</td>";
							str+="<td>"+detailsArr[i].mobileNo+"</td>";
							str+="</tr>";
					}					
				}
				else{
					if(type =="true"){
						if( type.indexOf(detailsArr[i].lowPerformance) < 0 ){
							str+="<tr>";
							str+="<td>"+detailsArr[i].constituencyName+"</td>";
							str+="<td>"+detailsArr[i].name+"</td>";
							str+="<td>"+detailsArr[i].committe+"</td>";
							str+="<td>"+detailsArr[i].role+"</td>";
							str+="<td>"+detailsArr[i].mobileNo+"</td>";
							str+="</tr>";
						}
					}
					else {
							str+="<tr>";
							str+="<td>"+detailsArr[i].constituencyName+"</td>";
							str+="<td>"+detailsArr[i].name+"</td>";
							str+="<td>"+detailsArr[i].committe+"</td>";
							str+="<td>"+detailsArr[i].role+"</td>";
							str+="<td>"+detailsArr[i].mobileNo+"</td>";
							str+="</tr>";
					}
				}			
			}
			str+="<tbody>";
			str+="</table>";	
			str+='</div>';
			$("#memberDetailsDiv").html(str);
			$("#tableId").dataTable({
				"iDisplayLength": 15,
				"aLengthMenu": [[15, 50, 100, -1], [15, 50, 100, "All"]]
			});
			if(type =="true"){
				$('#membersDisplayDialog').find('h4').html('<span class="text-uppercase">'+ committee +' COMMITTEE LOW PERFORMANCE MEMBERS </span>');		 
			}else{		 
				$('#membersDisplayDialog').find('h4').html('<span class="text-uppercase">'+ committee +'  COMMITTEE MEMBERS </span>');
			}
			$("#membersDisplayDialog").modal("show");
		}
		
	</script>
	
	
</body>
</html>
