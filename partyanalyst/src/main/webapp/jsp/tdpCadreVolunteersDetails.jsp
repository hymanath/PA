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
						<span class="add-on">Search Level : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<select id="searchTypeId">
						<option value="All" > All </option>
						<option value="Available" > Not Allocated </option>
						<option value="Assigned" > Allocated </option>
						</select>
						<button type="submit" class="btn btn-success " style="margin-left: 10px;" onclick="getVolunteerDetails();" >Search</button>
					</div>	
					<div id="volunteerTabelDiv" class="pull-left "></div>								
					
				</div>
			</div>
		</div>			
	</div>

	</div>
	<script>
	$('document').ready(function(){
		
	});
	
	function getVolunteerDetails()
	{
		var constiuencyId = $('#constituencyId').val();
		var searchType = $('#searchTypeId').val();
		
				var jsObj = 
			   {
				  consituencyId:constiuencyId,
				  searchType:searchType
			   }	
		 $.ajax({
					url : "getVolunteerInfoByLocationAction.action",
					data : {task:JSON.stringify(jsObj)}
				}).done(function(result){
					buildVolunteerDetails(result);					
				});
	}
	
	function buildVolunteerDetails(result)
	{
		if(result != null)
		{
			$('#volunteerTabelDiv').html('');
			var results = result.tdpCadreVolunteerVOList;
			if(results != null && results.length>0)
			{
				var str = '<div>';
					str+='<table class="table table-striped table-hover table-condensed table-bordered">';
					str+='<thead class="alert alert-success">';
					str+='<tr>';
					str+='<th> Name </th>';
					str+='<th> Mobile No </th>';
					str+='<th> Address </th>';
					str+='<th> Net Connection </th>';
					str+='<th> Laptop </th>';					
					str+='<th style="width: 125px;"> TAB/i-Pad with 2G/3G </th>';
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
						str+='<td>'+results[i].name+'</td>';
						str+='<td style="text-align:center;">'+results[i].mobileNo+'</td>';
						str+='<td>'+results[i].address+'</td>';
						str+='<td style="text-align:center;">'+results[i].internet+'</td>';
						str+='<td style="text-align:center;">'+results[i].lapTop+'</td>';						
						//str+='<td style="text-align:center;">'+results[i].tablet+'</td>';
						str+='<td style="text-align:center;width: 125px;">'+results[i].tablet+'</td>';
						str+='<td style="text-align:center;">'+results[i].smartPhone+'</td>';
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
						if(results[i].tdpCadreVolunteerVOList != null && results[i].tdpCadreVolunteerVOList.length>0)
						{
							str+='<option value="0"> Allocate Constituency </option>';
							for(var j in  results[i].tdpCadreVolunteerVOList)
							{
							if(results[i].constituencyId != null && (results[i].constituencyId == results[i].tdpCadreVolunteerVOList[j].id))
							{
								str+='<option value="'+results[i].tdpCadreVolunteerVOList[j].id+'" selected="selected">'+results[i].tdpCadreVolunteerVOList[j].name+'</option>';
							}
							else{
								str+='<option value="'+results[i].tdpCadreVolunteerVOList[j].id+'">'+results[i].tdpCadreVolunteerVOList[j].name+'</option>';
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
				
			}
			else
			{
				$('#volunteerTabelDiv').html('<div style="font-weight: bold; margin-top: 50px;text-align:center;margin-left: -350px;">No data Available ... </div>');
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
	
	</script>
  </body>