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
	</style>	
</head>
<body>
	
	<div class="container">
		<span class="btn btn-info pull-right exprtToExcel" style="display:none;">Export To Excel</span>
    	<div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 ">
                    <h3 class="panel-header">COMMITTEE MEMBERS PERFORMANCE</h3>
                    <hr style="border-color:#F00;margin:0px 0px 10px 0px;" />
                </div>
        </div>
		<div class="row" style="text-align:center;">
			<div id="cadrePerfId"><img style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></div>
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
		var locationId = "${distId}";
		var locationTypeId = 11;
		gettingCadreDetailsPerformance(locationTypeId,locationId);
		function gettingCadreDetailsPerformance(lctnTypeId,lctnId){
			var jsObj={
					 locationId:lctnId,locationTypeId:lctnTypeId
				   };
				   
			 $.ajax({
				type : "GET",
				url : "gettingPerformanceOfCadreAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildCadrePerformanceDetails(result);
			});
		}
		
		function buildCadrePerformanceDetails(result){
			var str = "";
			$("#cadrePerfId").html('<img style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/>');
			if(result!=null){
				
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
				
				
				$(".exprtToExcel").show();
				str+="<table id='cadrePerfTableId' class='table'>";
					str+="<thead>";
					str+="<tr>";
						str+="<th>NAME</th>";
						str+="<th>VOTER CARD NO</th>";
						str+="<th>AGE</th>";
						str+="<th>CONSTITUENCY</th>";
						str+="<th>COMMITTEE TYPE</th>";
						str+="<th>CASTE CATEGORY</th>";
						str+="<th>CASTE</th>";
						str+="<th>MOBILE</th>";
						str+="<th>OWN CONSTITUENCY %</th>";
						str+="<th>OWN MANDAL/MUNCIPALITY %</th>";
						str+="<th>OWN PANCHAYAT %</th>";
						str+="<th>OWN BOOTH %</th>";
					str+="</tr>";
					str+="</thead>";
					str+="<tbody>";
						for(var i in result){
							if(result[i].lowPerformance){
								str += "<tr class='lowPerf'>";
							}else{
								str+="<tr>";
							}
								str+="<td>"+result[i].name+" ( "+result[i].role+" )</td>";
								str+="<td>"+result[i].voterCardNo+"</td>";
								str+="<td>"+result[i].age+"</td>";
								str+="<td>"+result[i].constituencyName+"</td>";
								str+="<td>"+result[i].committe+"</td>";
								str+="<td>"+result[i].casteGroupName+"</td>";
								str+="<td>"+result[i].casteName+"</td>";
								str+="<td>"+result[i].mobileNo+"</td>";
								
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
						
						<!--str+="<th>ROLE</th>";-->
						<!--str+="<th>OWN BOOTH NO</th>";-->
						<!--str+="<th>OWN MUNCIPALITY %</th>";->
						<!--str+="<th>OWN WARD %</th>";-->
						
					str+="</tr>";
					str+="</thead>";
					str+="<tbody>";
						for(var i in result){
							str+="<tr>";
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
								
							
								<!--str+="<td>"+result[i].role+"</td>";-->
								<!--str+="<td>"+result[i].partNo+"</td>";-->
								
								/*if(result[i].ownMunciPerc!=null){
									str+="<td>"+result[i].ownMunciPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}*/
								
								/*if(result[i].ownWardPerc!=null){
									str+="<td>"+result[i].ownWardPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}*/
								
							str+="</tr>";
						}
					str+="<tbody>";
				str+="</table>";
				str+="</div>";
			}else{
				$(".exprtToExcel").hide();
				str+="<h4>No Data Available</h4>";
			}
			
			$("#cadrePerfId").html(str);
			$("#cadrePerfTableId").dataTable({
				"iDisplayLength": 20,
				"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			});
		}
		
		$(".exprtToExcel").click(function(){
			 tableToExcel('cadrePerfTableIdToExport', 'Committee Members Performance');
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
	</script>
	
	
</body>
</html>
