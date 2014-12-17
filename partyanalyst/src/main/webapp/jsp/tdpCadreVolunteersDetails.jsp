<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
  <head>
	 <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		 
	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<script type="text/javascript" src="js/photobooth/photobooth_min.js"></script>
		<script type="text/javascript" src="js/photobooth/website/js/cadre.js"></script>
		<link type="text/css" rel="stylesheet" media="screen" href="js/photobooth/website/css/page.css" />
		<link rel="stylesheet" href="js/flipclock/flipclock.css">		
		<script src="js/flipclock/flipclock.js"></script>

	<!-- YUI Dependency files (End) -->
	
  </head>
  <body>
  <script>

  </script>
	<div class="container">
	<div id="mainDiv">
		<div id="errorDiv"></div>
			<div class="row">
			<div class="span12 ">
				<div class="well well-small border-radius-0 span12 " style="display:table;">
					<h4 class="offset3">CONSTITUENCY ALLOCATION TO VOLUNTEER</h4>
					<hr>
			
					<div class="input-prepend input-append  pull-left" style="margin-left:250px">
						<span class="add-on">Select Constituency : </span>
							<s:select theme="simple" cssClass="border-radius-0 input-xlarge selectBoxWidth " id="constituencyId" list="constituencyList" listKey="id" listValue="name" headerKey="0" headerValue=" All" style="width:220px;" name="tdpCadreVolunteerVO.constituencyId" />
					</div>
					<div class="input-prepend input-append  pull-left" style="margin-left:250px">
						<span class="add-on">Search Level : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<select id="searchTypeId">
						<option value="All" > All </option>
						<option value="Available" > Not Allocated </option>
						<option value="Assigned" > Allocated </option>
						</select>
						<button type="button" class="btn btn-success " style="margin-left: 10px;" onclick="getVolunteerDetails();" >Search</button>
						<button type="button" class="btn btn-success " style="margin-left: 10px;display:none;" onclick="generateExcel('volunteerTabelDiv');" id="excelBtn">Export Excel</button>
					</div>	
					<img src='images/Loading-data.gif' id="loadingImg" style="display:none;margin-left: 365px;margin-top: 10px; height: 70px; width: 90px;"/>
					
				</div>
			</div>
		</div>			
	</div>
<div id="volunteerTabelDiv" class="pull-left " style="height:600px;display:none;padding:10px;display: table;"></div>								
	
	</div>
					
	<script>
	var constituencyArr = new Array();
	
	<c:forEach var="constiuency" items="${constituencyList}" >
	var constiObj = 
		{
		id: '${constiuency.id}',
		value: '${constiuency.name}'
		}
		constituencyArr.push(constiObj);
	</c:forEach>
	
	//console.log(constituencyArr);
	</script>
	<script>
	function getVolunteerDetails()
	{
		$('#excelBtn').hide();
		var constiuencyId = $('#constituencyId').val();
		var searchType = $('#searchTypeId').val();
		$('#volunteerTabelDiv').html('');
		$('#loadingImg').show();
		
				var jsObj = 
			   {
				  consituencyId:constiuencyId,
				  searchType:searchType,
				    task:"basicInfo"
			   }	
		 $.ajax({
					url : "getVolunteerInfoByLocationAction.action",
					data : {task:JSON.stringify(jsObj)}
				}).done(function(result){
				$('#loadingImg').hide();
					buildVolunteerDetails(result,constiuencyId,searchType);					
				});
	}
	
	
	function getVolunteerInfoByType(deviceType,id,type)
	{

		$("#tableDiv").html('');
			$('#excelBtn').hide();
		$('#loadingImg1').show();
		
				var jsObj = 
			   {
				  consituencyId:id,
				  searchType:type,
				  deviceType:deviceType,
				  task:"getVolunteerInfoByDevice"
			   }	
		 $.ajax({
					url : "getVolunteerInfoByLocationAction.action",
					data : {task:JSON.stringify(jsObj)}
				}).done(function(result){
				$('#loadingImg1').hide();
					buildVolunteerDetails1(result);					
				});
	}
	
	function buildVolunteerDetails(result,constiuencyId,searchType)
	{
		
		if(result != null)
		{
			$('#volunteerTabelDiv').html('');
			var results = result.tdpCadreVolunteerVOList;
			$('#volunteerTabelDiv').show();			
			$('#excelBtn').show();			
			if(results != null && results.length>0)
			{
			
					var str = '<h4 style="margin-left:410px;"> VOLUNTEERS DETAILS </h4>';
					str+='<br><div id="detailsDiv" style="margin-left:3px;width:970px"></div><div id="tableDiv" style="width:1200px;margin-left:-170px;">';
					str+='<table class="table table-bordered" style="table-layout: fixed;" id="volunteersTab">';
			
				
					str+='<thead class="alert alert-success">';
					str+='<tr>';
					str+='<th> S.No </th>';
					str+='<th> Name </th>';
					str+='<th> Mobile No </th>';
					str+='<th> Address </th>';
					str+='<th>Internet</th>';
					str+='<th> Laptop </th>';					
					str+='<th> TAB/i-Pad with 2G/3G </th>';
					//str+='<th>i-Phone With 2G/3G</th>';
					str+='<th> Smart-Phone With 2G/3G </th>';
					str+='<th> Available Dates </th>';
					str+='<th> Available Constituencies </th>';
					str+='<th> Allocated Constituency </th>';
					str+='</tr>		';				
					str+='</thead>';
					str+='<tbody>';	
					
					for(var i in  results)
					{						
						str+='<tr>';
						str+='<td>'+(parseInt(i)+1)+'</td>';
						str+='<td>'+results[i].name+'</td>';
						str+='<td>'+results[i].mobileNo+'</td>';
						str+='<td style="word-wrap: break-word;">'+results[i].address+'</td>';
						str+='<td>'+results[i].internet+'</td>';
						str+='<td>'+results[i].lapTop+'</td>';						
						//str+='<td style="text-align:center;">'+results[i].tablet+'</td>';
						str+='<td>'+results[i].tablet+'</td>';
						str+='<td>'+results[i].smartPhone+'</td>';
						str+='<td>';
						
						if(results[i].datesList != null && results[i].datesList.length>0)
						{
							for(var j in  results[i].datesList)
							{
								str+=''+results[i].datesList[j]+'';
								
								if(j< results[i].datesList.length -1)
									str+=', <br>';
							}
						}
						
						str+='</td>';
						str+='<td>';
						if(results[i].tdpCadreVolunteerVOList != null && results[i].tdpCadreVolunteerVOList.length>0)
						{
							for(var j in  results[i].tdpCadreVolunteerVOList)
							{
								str+=''+results[i].tdpCadreVolunteerVOList[j].name+'';
								
								if(j< results[i].tdpCadreVolunteerVOList.length -1)
									str+=', <br>';
							}
						}
						str+='</td>';
						
						str+='<td  style="text-align:center;">';
						str+='<select style="width:170px;" id="assignConstiList'+i+'" >';
						if(constituencyArr != null && constituencyArr.length>0)
						{
							str+='<option value="0"> Allocate Constituency </option>';
							for(var j in  constituencyArr)
							{
							if(results[i].constituencyId != null && (results[i].constituencyId == constituencyArr[j].id))
							{
								str+='<option value="'+constituencyArr[j].id+'" selected="selected">'+constituencyArr[j].value+'</option>';
							}
							else{
								str+='<option value="'+constituencyArr[j].id+'" >'+constituencyArr[j].value+'</option>';
							}
								
							}
						}
						str+='</select><i class="icon-ok pull-right" title="Allocate Constituency" style="cursor:pointer" onclick="assignConstiteuncyForValeenteer('+results[i].id+',\'statusMsg'+i+'\',\'assignConstiList'+i+'\');" ></i>';
						str+='<br><span id="statusMsg'+i+'"></span>';
						str+='</td>';
						str+='</tr>';
					}
					
					str+='</tbody>';
					str+='</table> </div>';

					$('#volunteerTabelDiv').html(str);
					$('#volunteersTab').dataTable({
						"iDisplayLength": 50,
						"aLengthMenu": [[50, 100, 200, -1], [50, 100, 200, "All"]],
						"aoColumns": [
						
								{ "sWidth": "20px" },
								{ "sWidth": "80px" },
								{ "sWidth": "70px" },
								{ "sWidth": "140px" },
								{ "sWidth": "32px" },
								{ "sWidth": "22px" },
								{ "sWidth": "74px" },
								{ "sWidth": "84px" },
								{ "sWidth": "84px" },
								{ "sWidth": "109px" },
								{ "sWidth": "160px" }
								]					
						
		
						});
					var str1 ='';
					str1+='<table class="table table-bordered">';
					str1+='<thead>';
					str1+='<tr>';
					str1+='<th style="background-color:#EEEEEE;"> Total Volunteers </th><td style="cursor:pointer">'+results.length+'</td>';					
					str1+='</tr>';
					str1+='<tr>';
					str1+='<th style="background-color:#EEEEEE;" > Loptop-Users  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'laptop\',\''+constiuencyId+'\',\''+searchType+'\');">'+result.lapTop+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> Internet-Users  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'internet\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.internet+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> Laptop & Internet-Users  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'lapinternet\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.noTab+'</a></td>';
					str1+='</tr>';
					str1+='<tr>';
					str1+='<th style="background-color:#EEEEEE;" >  Tablet With 2G  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'tablet2G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.tablet+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> Tablet With 3G  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'tablet3G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.tablet3G+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> </th> <td></td>';
					str1+='</tr>';
					str1+='<tr>';
					str1+='<tr>';
					str1+='<th style="background-color:#EEEEEE;"> SmartPhone With 2G  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'smartPhone2G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.smartPhone+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;" >  SmartPhone With 3G  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'smartPhone3G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.smartPhone3G+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"></th> <td></td>';
					str1+='</tr>';					
					str1+='<th style="background-color:#EEEEEE;" >  i-Pad With 2G  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'ipad2G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.ipad+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> i-Pad With 3G  </th> <td style="cursor:pointer"><a onclick="getVolunteerInfoByType(\'ipad3G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.ipad3G+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> </th> <td></td>';
					str1+='</tr>';
					str1+='</thead>';
					str1+='</table>';
					str1+='<img src="images/Loading-data.gif" id="loadingImg1" style="display:none;margin-top: 100px; height: 70px; width: 90px;"/>';
					$('#detailsDiv').html(str1);
			}
			else
			{
				$('#volunteerTabelDiv').css("overflow","none");
				$('#excelBtn').css('display','none');
				$('#excelBtn').hide();
				$('#volunteerTabelDiv').html('<div style="font-weight: bold; margin-top: 50px;text-align:center;margin-left: -350px;">No data Available ... </div>');
			}
		}
	}
	
		
	function buildVolunteerDetails1(result)
	{
		
		if(result != null)
		{
			
			var results = result.tdpCadreVolunteerVOList;
			//$('#volunteerTabelDiv').show();			
			$('#excelBtn').show();			
			if(results != null && results.length>0)
			{
			
				var str = '<h4 style="margin-left:410px;"> VOLUNTEERS DETAILS </h4>';
					str+='<br><div style="width:1200px;margin-left:-170px;">';
					str+='<table class="table table-bordered" style="table-layout: fixed;" id="volunteersTab1">';
				    str+='<thead class="alert alert-success">';
					str+='<tr>';
					str+='<th> S.No </th>';
					str+='<th> Name </th>';
					str+='<th> Mobile No </th>';
					str+='<th> Address </th>';
					str+='<th>Internet</th>';
					str+='<th> Laptop </th>';					
					str+='<th> TAB/i-Pad with 2G/3G </th>';
					//str+='<th>i-Phone With 2G/3G</th>';
					str+='<th> Smart-Phone With 2G/3G </th>';
					str+='<th> Available Dates </th>';
					str+='<th> Available Constituencies </th>';
					str+='<th> Allocated Constituency </th>';
					str+='</tr>		';				
					str+='</thead>';
					str+='<tbody>';	
					
					for(var i in  results)
					{						
						str+='<tr>';
						str+='<td>'+(parseInt(i)+1)+'</td>';
						str+='<td>'+results[i].name+'</td>';
						str+='<td>'+results[i].mobileNo+'</td>';
						str+='<td style="text-align:center;">'+results[i].address+'</td>';
						str+='<td>'+results[i].internet+'</td>';
						str+='<td>'+results[i].lapTop+'</td>';						
						//str+='<td style="text-align:center;">'+results[i].tablet+'</td>';
						str+='<td>'+results[i].tablet+'</td>';
						str+='<td>'+results[i].smartPhone+'</td>';
						str+='<td>';
						
						if(results[i].datesList != null && results[i].datesList.length>0)
						{
							for(var j in  results[i].datesList)
							{
								str+=''+results[i].datesList[j]+'';
								
								if(j< results[i].datesList.length -1)
									str+=', <br>';
							}
						}
						
						str+='</td>';
						str+='<td>';
						if(results[i].tdpCadreVolunteerVOList != null && results[i].tdpCadreVolunteerVOList.length>0)
						{
							for(var j in  results[i].tdpCadreVolunteerVOList)
							{
								str+=''+results[i].tdpCadreVolunteerVOList[j].name+'';
								
								if(j< results[i].tdpCadreVolunteerVOList.length -1)
									str+=', <br>';
							}
						}
						str+='</td>';
						
						str+='<td  style="text-align:center;">';
						str+='<select id="assignConstiList'+i+'" >';
						if(constituencyArr != null && constituencyArr.length>0)
						{
							str+='<option value="0"> Allocate Constituency </option>';
							for(var j in  constituencyArr)
							{
							if(results[i].constituencyId != null && (results[i].constituencyId == constituencyArr[j].id))
							{
								str+='<option value="'+constituencyArr[j].id+'" selected="selected">'+constituencyArr[j].value+'</option>';
							}
							else{
								str+='<option value="'+constituencyArr[j].id+'" >'+constituencyArr[j].value+'</option>';
							}
								
							}
						}
						str+='</select><i class="icon-ok pull-right" title="Allocate Constituency" style="cursor:pointer" onclick="assignConstiteuncyForValeenteer('+results[i].id+',\'statusMsg'+i+'\',\'assignConstiList'+i+'\');" ></i>';
						str+='<br><span id="statusMsg'+i+'"></span>';
						str+='</td>';
						str+='</tr>';
					}
					
					str+='</tbody>';
					str+='</table> </div>';

					$('#tableDiv').html(str);
					$('#volunteersTab1').dataTable({
						"iDisplayLength": 50,
						"aLengthMenu": [[50, 100, 200, -1], [50, 100, 200, "All"]],
						"aoColumns": [
	
								{ "sWidth": "20px" },
								{ "sWidth": "80px" },
								{ "sWidth": "70px" },
								{ "sWidth": "140px" },
								{ "sWidth": "32px" },
								{ "sWidth": "22px" },
								{ "sWidth": "74px" },
								{ "sWidth": "84px" },
								{ "sWidth": "84px" },
								{ "sWidth": "109px" },
								{ "sWidth": "160px" }
								]			
						});
		
				
				/*var str1 ='';
					str1+='<table class="table table-bordered">';
					str1+='<thead>';
					str1+='<tr>';
					str1+='<th style="background-color:#EEEEEE;"> Total Volunteers </th><td>'+results.length+'</td>';					
					str1+='</tr>';
					str1+='<tr>';
					str1+='<th style="background-color:#EEEEEE;" > Loptop-Users  </th> <td><a onclick="getVolunteerInfoByType(\'laptop\',\''+constiuencyId+'\',\''+searchType+'\');">'+result.lapTop+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> Internet-Users  </th> <td><a onclick="getVolunteerInfoByType(\'internet\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.internet+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> Laptop & Internet-Users  </th> <td><a onclick="getVolunteerInfoByType(\'lapinternet\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.noTab+'</a></td>';
					str1+='</tr>';
					str1+='<tr>';
					str1+='<th style="background-color:#EEEEEE;" >  Tablet With 2G  </th> <td><a onclick="getVolunteerInfoByType(\'tablet2G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.tablet+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> Tablet With 3G  </th> <td><a onclick="getVolunteerInfoByType(\'tablet3G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.tablet3G+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> </th> <td></td>';
					str1+='</tr>';
					str1+='<tr>';
					str1+='<tr>';
					str1+='<th style="background-color:#EEEEEE;"> SmartPhone With 2G  </th> <td><a onclick="getVolunteerInfoByType(\'smartPhone2G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.smartPhone+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;" >  SmartPhone With 3G  </th> <td><a onclick="getVolunteerInfoByType(\'smartPhone3G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.smartPhone3G+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"></th> <td></td>';
					str1+='</tr>';					
					str1+='<th style="background-color:#EEEEEE;" >  i-Pad With 2G  </th> <td><a onclick="getVolunteerInfoByType(\'ipad2G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.ipad+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> i-Pad With 3G  </th> <td><a onclick="getVolunteerInfoByType(\'ipad3G\',\''+constiuencyId+'\',\''+searchType+'\')">'+result.ipad3G+'</a></td>';
					str1+='<th style="background-color:#EEEEEE;"> </th> <td></td>';
					str1+='</tr>';
					str1+='</thead>';
					str1+='</table>';
					
					$('#detailsDiv').html(str1);*/
			}
			else
			{
				//$('#volunteerTabelDiv').css("overflow","none");
				$('#excelBtn').css('display','none');
				$('#excelBtn').hide();
				$('#tableDiv').html('<div style="font-weight: bold; margin-top: 50px;text-align:center;margin-left: -350px;">No data Available ... </div>');
			}
		}
	}
	function assignConstiteuncyForValeenteer(valunteerId,statusDiv,constiuencyDivId)
	{
		var constiuencyId = $('#'+constiuencyDivId+'').val();
		if(constiuencyId ==0)
		{
			$('#'+statusDiv+'').html("<span style='font-weight:bold;color:red;'> Please Select Constituency.</span>");
			return;
		}
		var jsObj = 
	   {
		  consituencyId:constiuencyId,
		  valunteerId:valunteerId
	   }	
	 $.ajax({
				url : "assignConstiteuncyForValeenteerAction.action",
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){

				if(result != null)					
				{					
					if(result.resultCode == 0)
					{
						$('#'+statusDiv+'').html("<span style='font-weight:bold;color:green;'>Constituency Allocated .</span>");
					}
					else if(result.resultCode == 1)
					{
						$('#'+statusDiv+'').html("<span style='font-weight:bold;color:red;'>Constituency Allocation Failed.</span>");
					}
					
				}
			});
	}
	
	function generateExcel(divId)
	{
		tableToExcel(divId, 'GHMC Volunteers Report');
	}
	
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
})();
	
	</script>
  </body>