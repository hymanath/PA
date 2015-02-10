<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Constituency Committee Summary </title>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Bootstrap -->
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
   <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>

    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	
    <!-- Include all compiled plugins (below), or include individual files as needed -->

	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommitteeRequest/cadreCommitteeRequest.js"></script>
	
	
</head>
<body>
<style>
	.locationName{text-transform: uppercase;}
</style>
<header style="align:center;background-color:#ef4036; display:flex;border-bottom:4px solid #13a751;">
		 	<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3 text-center">
				<img src="images/cadreCommitee/Committees_2014_logo.png" class="m_top10" title="Committee Logo" alt="committee" />
			</div>
			<div class="col-md-3  col-xs-3 col-sm-3">
               
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top20" data-toggle="dropdown" aria-expanded="false" style="margin-top: 60px;">
                    Menu <img src="images/cadreCommitee/menu_icon.png" />
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);top: 91px;">
                    
						<c:if test="${sessionScope.USER.isAdmin == 'true'}">
						   <li><a tabindex="-1" href="committeeDashBoardAction.action">Home</a></li>
						    <li><a tabindex="-1" href="committeeUpdateApproveAction.action">Approval Requests</a></li>
					    </c:if>
						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' )}">
							<li><a tabindex="-1" href="committeeDashBoardAction.action">Home</a></li>
							<li><a tabindex="-1" href="committeeUpdateApproveAction.action">Approval Requests</a></li>
						</c:if>
						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_COMMITTEE_MANAGEMENT' )}">
							<li><a tabindex="-1" href="cadreCommitteeAction.action">Home</a></li>
							<li><a tabindex="-1" href="cadreCommitteeSummaryAction.action">Summary Report</a></li>
				            <li><a tabindex="-1" href="cadreCommitteeRequestAction.action">Request For Positions Increase</a></li>
						</c:if>
                      <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
                     <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
                    </ul>
                 
            </div>
	</header>
<div class="container">
    <div class="row m_top20 locationCls">
  	   <div class="col-md-4 col-md-offset-2 col-sm-6 col-xs-6">Select District:<select id="districtsId" class="form-control" onChange="getAllConstituencysForADistrict()"><option value="0">Select District</option></select> </div>
       <div class="col-md-4  col-sm-6 col-xs-6">Select Constituency:<select id="constituencysId" class="form-control"><option value="0">Select Constituency</option></select> </div>
    </div>
	<div class="row m_top20">
		<div class="col-md-4 col-md-offset-3"><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=1 name="reportType" checked="true"> VILLAGE / WARD</label></div>
		<div class="col-md-4 "><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=2 name="reportType">MANDAL / MUNCIPALITY / DIVISON</label></div>
	</div>
	
	<span class="btn btn-info pull-right exportToExcel" onclick="exportToExcel()" style="display:none;"> Export To Excel </span>
	
	<div id="constSummary" style="display:none;">
		<img id="summaryAjax" src="./images/Loading-data.gif" class="col-sm-2 col-sm-offset-4" alt="Processing Image"/>
	</div>
	
	
</div>

<div id="dialogSummary" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg">
<div class="modal-content">
    
	<div class="row" style="text-align:center;">
<div class="col-md-6 col-md-offset-3">
<h3 class="panel-header">Constituency Committee Summary</h3>
<hr style="border-color:#F00;margin-top:10px;" />
</div>

<span aria-hidden="true" data-dismiss="modal" class="pull-right" style="font-size: 30px; cursor: pointer; margin-left: -18px; margin-right: 24px; margin-top: -2px;" type="button">X</span>

</div>


    <div id="cadreDetailsDiv"></div>


</div>
</div>
</div>
<script type="text/javascript">
var accessConstituency = '${accessConstituency}';
var accessConstituencyId = '${accessConstituencyId}';


if(accessConstituencyId!='null' && accessConstituencyId!=""){
	$(".locationCls").hide();
	getConstituencySummary();
	$("#constSummary").show();
}else{
	getAllDistricts();
}


function getAllDistricts()
{

 $.ajax({
			type : "GET",
			url : "getAllDistrictsAction.action",
			data : {} ,
		}).done(function(result){
			$("#districtsId  option").remove();
			$("#districtsId").append('<option value="0">Select District</option>');
			if(result!=null){
			for(var i in result){
				   $("#districtsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				 }
			}	 
		});
}

$("#constituencysId").change(function(){
	$("#constSummary").show();
	$(".exportToExcel").hide();
	getConstituencySummary();
});
$(".reportTypeCls").click(function(){
	getConstituencySummary();
	
});
	
function getAllConstituencysForADistrict()
{
    var districtId = $("#districtsId").val();
	$("#constituencysId  option").remove();
	$("#constituencysId").append('<option value="0">Select Constituency</option>');
	if(districtId==0){
	  return;	 
	}
	
	$("#constSummary").html('');
 
    $.ajax({
			type : "GET",
			url : "getAllConstituencysForADistrictAction.action",
			data : {districtId:districtId} ,
		}).done(function(result){
			if(result!=null){
			   for(var i in result){
				   $("#constituencysId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			    }
			}	 
		});
}

function getConstituencySummary(){
	$("#constSummary").html('<img id="summaryAjax" src="./images/Loading-data.gif" class="col-sm-2 col-sm-offset-4" alt="Processing Image"/>');
	var constiId = "";
	if(accessConstituencyId!=null && accessConstituencyId!=""){
		constiId = accessConstituencyId;
	}else{
		constiId = $("#constituencysId").val();
	}
	
	if(constiId==0){
		$("#constSummary").html('');
		return;	 
	}
	var reportType = $("input[type='radio'][name='reportType']:checked").val();
	
	var jsObj ={
		 constituencyId:constiId,reportType :reportType            
	}	
	$.ajax({
		type : "POST",
		url : "getConstituencyCommitteSummary.action",
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		$(".exportToExcel").show();
		buildConstituencySummary(result,jsObj);
	});
	
}

function buildConstituencySummary(results,jsObj){
	var repType = jsObj.reportType;
	
	$("#constSummary").html("");
		var str = '';
		
		if(repType==1){
			for(var i in results.mandalsList){
				var rest = results.mandalsList[i]
				str += '<h4 class="locationName">'+rest.locationName+'</h4>'
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; background-color:rgba(0,0,0,0.1);">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th  rowspan=2> Location </th>';
						for(var k in results.resultList){
							str+='<th colspan=2>'+results.resultList[k].basicCommitteeName+'</th>';
						}
						
					str+='</tr>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						for(var k in results.resultList){
							str+='<th> Members </th>';
							str+='<th> Electrols </th>';
						}
						
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var j in rest.locationsList){
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								str+='<td>'+rest.locationsList[j].locationName+'</td>';
									for(var k in rest.locationsList[j].resultList){
										if(rest.locationsList[j].resultList[k].membersCount!=null){
											//str+='<td><a id="location'+rest.locationsList[j].locationId+'" class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="members" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\',\'members\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
										    str+='<td><a href="javascript:{gettingCadreDetails(\'members\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
										}else{
											str+='<td> </td>';
										} 
										
										if(rest.locationsList[j].resultList[k].electrolsCount!=null){
										    //str+='<td><a id="location'+rest.locationsList[j].locationId+'" class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="electrols" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\',\'electrols\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';
											//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="electrols">'+rest.locationsList[j].resultList[k].electrolsCount+'</td>';
										     str+='<td><a  href="javascript:{gettingCadreDetails(\'electrols\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';

										}else{
											str+='<td> </td>';
										}
										
									}
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table> ';
			}
			for(var i in results.localBodiesList){
				var rest = results.localBodiesList[i]
				str += '<h4 class="locationName">'+rest.locationName+'</h4>'
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; background-color:rgba(0,0,0,0.1);">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th rowspan=2> Location </th>';
						for(var k in results.resultList){
							str+='<th colspan=2>'+results.resultList[k].basicCommitteeName+'</th>';
						}
						
					str+='</tr>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						for(var k in results.resultList){
							str+='<th> Members </th>';
							str+='<th> Electrols </th>';
						}
						
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var j in rest.locationsList){
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								str+='<td>'+rest.locationsList[j].locationName+'</td>';
									for(var k in rest.locationsList[j].resultList){
										if(rest.locationsList[j].resultList[k].membersCount!=null){
											//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="members">'+rest.locationsList[j].resultList[k].membersCount+'</td>';
										     // str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="members" id="location'+rest.locationsList[j].locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
									        str+='<td><a href="javascript:{gettingCadreDetails(\'members\',8,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';

										}else{
											str+='<td> </td>';
										}
										
										if(rest.locationsList[j].resultList[k].electrolsCount!=null){
											//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="electrols">'+rest.locationsList[j].resultList[k].electrolsCount+'</td>';
										    //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="electrols" id="location'+rest.locationsList[j].locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';
										    str+='<td><a href="javascript:{gettingCadreDetails(\'electrols\',8,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';

										}else{
											str+='<td> </td>';
										}
										
									}
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table> ';
			}
		}else{
				str += '<h4 class="locationName"> Mandal/Munciapality/Division Wise Committees Summary</h4>'
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; background-color:rgba(0,0,0,0.1);">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th> Location </th>';
						for(var k in results.resultList){
							str+='<th>'+results.resultList[k].basicCommitteeName+'</th>';
						}
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var i in results.mandalsList){
							var rest = results.mandalsList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="5">'+rest.resultList[j].membersCount+'</td>';
							        //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="5"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
                                    str+='<td><a href="javascript:{gettingCadreDetails(\'members\',5,'+rest.locationId+','+rest.resultList[j].basicCommitteeTypeId+');}">'+rest.resultList[j].membersCount+'</a></td>';

								}else{
									str+='<td> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
						for(var i in results.localBodiesList){
							var rest = results.localBodiesList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="7">'+rest.resultList[j].membersCount+'</td>';
								   //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="7"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
 								   str+='<td><a href="javascript:{gettingCadreDetails(\'members\',7,'+rest.locationId+','+rest.resultList[j].basicCommitteeTypeId+');}">'+rest.resultList[j].membersCount+'</a></td>';

								}else{
									str+='<td> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
						for(var i in results.divisionList){
							var rest = results.divisionList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="11">'+rest.resultList[j].membersCount+'</td>';
									 //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="11"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
									 str+='<td><a href="javascript:{gettingCadreDetails(\'members\',9,'+rest.locationId+','+rest.resultList[j].basicCommitteeTypeId+');}">'+rest.resultList[j].membersCount+'</a></td>';

								}else{
									str+='<td> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
					str+='</tbody>';
				str+='</table> ';
			
		}
		
	$("#constSummary").html(str);
}

	function gettingCadreDetails(type,locationType,location,basicCmmtyId){
	
		
		 var jsObj={
		         locationId:location,locationType:locationType,basicCommitteeTypeId:basicCmmtyId,type:type
		       };
			   
		 $.ajax({
			type : "GET",
			url : "gettingCadreDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 buildingResults(result);
		});
		
	}
function buildingResults(result)
{
  if(result!=null)
  {
  
    if(result.length>0){
    var str='';
	str+='<table class="table table-yellow-bordered table-condensed">';
	str+='<thead>';
	str+='<th  style="width: 178px; padding-left: 34px;">Designation</th>';
	str+='<th style="padding-left: 23px; width: 204px;">Member Image</th>';
	str+='<th style="padding-left: 72px;">NAME</th>';
	str+='<th style="padding-left: 19px;">MemberShipNo</th>';
	str+='</thead>';
    for(var i in result){
	 str+='<tr>';
	 if(result[i].role!=null){
		str+='<td  style="padding-top: 14px; padding-left: 37px;">'+result[i].role+'</td>';
	 }else{
		str+='<td  style="padding-top: 14px; padding-left: 37px;">  </td>';
	 }
	 
	 str+='<td ><img  style="margin-top: 5px; margin-left: 57px; padding-top: 0px;" style="margin-top: 26px; margin-left: 12px;" width="35"  height="35" src="http://www.mytdp.com/images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/></td>';
	 str+='<td style="padding-top: 15px; padding-left: 15px;width:281px;">'+result[i].name+'</td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].membershipNo+'</td>';
	 str+='</tr>';
	}
   str+='</tbody>';
   str+='</table>';
   $("#cadreDetailsDiv").html(str);
   $("#dialogSummary").modal("show");
  
  }
  else{
        $("#cadreDetailsDiv").html("No Data Available.");
	}
}
}	
	
</script>

<script>
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
function exportToExcel()
{
	  tableToExcel('constSummary', 'Constituency Committee Summary');
}

</script>

</body>
</html>