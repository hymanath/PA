<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				
<style>
	body{background:#f0f0f0;}
	.m_top10{margin-top:10px;}
	.m_top20{margin-top:20px;}
	.m_top30{margin-top:30px;}
	.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
	.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
	.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
	.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
	.username thead tr:nth-child(2){ background:#eee;}		
	.username td:first-child{ min-width: 200px; }		
	.username th small{ font-size:11px; }				
	.username th{ text-align:center; }
	/*-------*/
	
	.wiget-yellow{background:#ffcc00; border:1px solid #ccc; width:100%; height:370px;padding:10px;}
	.wiget-yellow-normal{background:#ffcc00; border:1px solid #ccc; width:100%; padding:10px;}
	.wiget-yellow-normal h3{font-size:16px;border-bottom:2px solid #eee; line-height:20px;padding:5px; }
	.wiget-yellow:hover{box-shadow: 0px -1px 5px #333;}
	.wiget-yellow small{color:red; font-size:18px;}
	.wiget-yellow h4{font-size:22px;border-bottom:2px solid #eee; text-align:center;}
	/*-----*/
	.boothdetails-nav li a{color:#333333; background:#eee;padding:10px; width:136px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin-bottom: 10px;text-decoration:none; font-size:16px;}
	.boothdetails-nav li a:hover{ background:#ccc; border:1px solid #ffcc00;text-show:0px 1px #fff;}
	.booths-Overview-widget{background:#ddd;padding:10px; width:100%;}
	.booths-Overview-widget-nav li{color:#333333; background:#F6DD78;padding:10px; width:140px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 10px;text-decoration:none; font-size:16px;}
	.booths-Overview-widget-nav li hgroup h4,h5{font-size:15px;}
	.Constituency-name-nav li a{width:165px;line-height:40px;color:#333333;display:block; background:#eee;padding:10px; border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 5px;text-decoration:none; font-size:14px; display: inline-table;float:left;}
	.Constituency-name-nav li a:hover{color:#333333;border:1px solid #ffcc00; box-shadow:0px 0px 3px #ccc;}
	
	.wiget-yellow-normal:hover{ box-shadow: 0 -1px 5px rgb(51, 51, 51);}
	.Constituency-name-nav li a:hover{color:#333333;border:1px solid #ffcc00;  background-color:rgb(204,204,204);}
	
</style>	
	
	<div class="container">
		<!-------->		
		<div class="row">
			<div class="span12">
				<!-----State Overview Div ---->
				<div class="row-fluid">
					<div class="span12 widgetservey_Red m_top20">
						<h4>State Overview</h4>
						<div class="row-fluid m_top20">
							<div class="span3 wiget-yellow-normal">
								<h3>Started Constituency Count</h3>
								<!--<h2>80</h2>-->
								<h2>${resultVO.startedCount}</h2>
							
							</div>
							<div class="span3 wiget-yellow-normal">
								<h3>Completed Constituency Count</h3>
								<h2>${resultVO.completedCount}</h2>
							</div>
							<div class="span3 wiget-yellow-normal">
								<h3>Processing Constituency Count</h3>
								<h2>${resultVO.processingCount}</h2>
							</div>
							<div class="span3 wiget-yellow-normal">
								<h3>Not Yet Started Constituency Count</h3>
								<h2>${resultVO.notStartedCount}</h2>
							</div>
						</div>
						
						<div class="row-fluid m_top10">
							<div class="span6 wiget-yellow-normal">
								<h3>Total Caste Collected Count</h3>
								<h2 id="TotalcasteCount">${resultVO.completedCount}</h2>

							</div>
							<div class="span6 wiget-yellow-normal">
							<h3>	Today Caste Collected Count 	</h3>
								<h2 id="TodaycasteCount">${resultVO.completedCount}</h2>

							</div>
						</div>
					</div>		
				</div><!-----State Overview Div end---->
				
				<div id="casteCountDiv" class="offset2" style="margin-top:20px;"></div>
				<div id="dateWisecastePopupDiv">
				<div id="PopupContentDiv">
				<img id="popupImg" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/>
				</div>
				</div>
				
				<!-- constituency Div -->
				
				<div class="row-fluid ">
					<div class="span12  m_top20  widgetservey">
						<table class=" m_top20 table table-bordered table-hover table-striped">
							<thead class="alert alert-success">
								<tr>
									<th>Constituency Name</th>
									<th>Total Booths</th>
									<th>Completed</th>
									<th>Processing </th>
									<th>Not Yet Started </th>
								</tr>
							</thead>
							<tbody>
                              	<c:forEach var="constituency" items="${resultList}">
								<c:if test="${constituency.totalCount != constituency.notStartedCount}">
								<tr>

									<td><a href="javascript:{getConstituencyDetalReport(${constituency.locationId})}">${constituency.locationName}</a></td>
									<td>${constituency.totalCount}</td>
									<td>${constituency.completedCount}</td>
									<td>${constituency.processingCount}</td>
									<td>${constituency.notStartedCount}</td>
								</tr>
								</c:if>
								</c:forEach>

<!--
								<tr>
									<td>Constituency name</td>
									<td>120</td>
									<td>20</td>
									<td>50</td>
									<td>40</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>220</td>
									<td>20</td>
									<td>100</td>
									<td>100</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>180</td>
									<td>80</td>
									<td>50</td>
									<td>50</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>120</td>
									<td>90</td>
									<td>10</td>
									<td>20</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>500</td>
									<td>100</td>
									<td>200</td>
									<td>200</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>120</td>
									<td>20</td>
									<td>50</td>
									<td>40</td>
								</tr>-->
							</tbody>
						</table>	
					</div>
				</div>
				
					<!-- constituency End Div -->
				
				<div class="row-fluid ">
					<div class="span12 m_top20 widgetservey">
						<h4>Districts Started</h4>
							<ul class="inline unstyled Constituency-name-nav">	
								<c:if test="${empty resultVO.started}">
									SURVEY NOT STARTED IN ANY DISTRICT
								</c:if>
								<c:forEach var="startedDistrict" items="${resultVO.started}">
									  <li><a href="javascript:{showConstituenciesDetails(${startedDistrict.locationId})}">${startedDistrict.locationName}</a></li>
								</c:forEach>

							</ul>
					</div>
				</div>
				
				<div class="row-fluid ">
					<div class="span12 m_top20 widgetservey">
						<h4>Districts Completed </h4>
						<ul class="inline unstyled Constituency-name-nav">	
						        <c:if test="${empty resultVO.completed}">
									SURVEY NOT COMPLETED IN ANY DISTRICT
								</c:if>

								<c:forEach var="completedDistrict" items="${resultVO.completed}">
									  <li><a href="javascript:{showConstituenciesDetails(${completedDistrict.locationId})}">${completedDistrict.locationName}</a></li>
								</c:forEach>
							</ul>
					</div>
				</div>
				
				<div class="row-fluid ">
					<div class="span12 m_top20 widgetservey">
						<h4>Districts  Survey In Processing</h4>
						<ul class="inline unstyled Constituency-name-nav">

						          <c:if test="${empty resultVO.process}">
									SURVEY IS NOT PROCESSING IN ANY DISTRICT
								  </c:if>
						       
								<c:forEach var="processDistrict" items="${resultVO.process}">
									  <li><a href="javascript:{showConstituenciesDetails(${processDistrict.locationId})}">${processDistrict.locationName}</a></li>
								</c:forEach>
							</ul>
					</div>
				</div>
				
				<div class="row-fluid ">
					<div class="span12 m_top20 widgetservey">
						<h4>Districts Not Started</h4>
						<ul class="inline unstyled Constituency-name-nav">

						    <c:if test="${empty resultVO.notStarted}">
									SURVEY STARTED IN ALL DISTRICTS
							</c:if>

							<c:forEach var="notStartedDistrict" items="${resultVO.notStarted}">
									<!-- <li><a href="javascript:{showConstituenciesDetails(${notStartedDistrict.locationId})}">${notStartedDistrict.locationName}</a></li> -->
									<li><a >${notStartedDistrict.locationName}</a></li>
								</c:forEach>

							</ul>
					</div>
				</div>
				<!---<div class="row-fluid ">
					<div class="span12  m_top20  widgetservey">
						<table class=" m_top20 table table-bordered table-hover table-striped">
							<thead class="alert alert-success">
								<tr>
									<th>Constituency name</th>
									<th>Total Booth no</th>
									<th>Completed</th>
									<th>Processing </th>
									<th>Not yet Started </th>
								</tr>
							</thead>
							<tbody>
                              	<c:forEach var="constituency" items="${resultList}">
								<c:if test="${constituency.totalCount != constituency.notStartedCount}">
								<tr>

									<td><a href="javascript:{getConstituencyDetalReport(${constituency.locationId})}">${constituency.locationName}</a></td>
									<td>${constituency.totalCount}</td>
									<td>${constituency.completedCount}</td>
									<td>${constituency.processingCount}</td>
									<td>${constituency.notStartedCount}</td>
								</tr>
								</c:if>
								</c:forEach>

<!--
								<tr>
									<td>Constituency name</td>
									<td>120</td>
									<td>20</td>
									<td>50</td>
									<td>40</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>220</td>
									<td>20</td>
									<td>100</td>
									<td>100</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>180</td>
									<td>80</td>
									<td>50</td>
									<td>50</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>120</td>
									<td>90</td>
									<td>10</td>
									<td>20</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>500</td>
									<td>100</td>
									<td>200</td>
									<td>200</td>
								</tr>
								<tr>
									<td>Constituency name</td>
									<td>120</td>
									<td>20</td>
									<td>50</td>
									<td>40</td>
								</tr>-->
							<!--</tbody>
						</table>	
					</div>
				</div>-->
			</div>
				</div>	<!----Constituency details main Div End---->
				
			</div>
		</div>
	</div>
		
<script>
function showConstituenciesDetails(districtId)
{
	 window.open('constituencyDetailReportAction.action?districtId='+districtId+'','_blank');
}

function getConstituencyDetalReport(constituencyId){

	var jobj = {
		constituencyId : constituencyId,
		task:"getDistrictDetailsForConstituency"
	}
	$.ajax({
          type:'POST',
          url: 'getDistrictDetailsForConstituencyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)}
    }).done(function(result){
	var district = parseInt(result[1].id);
		window.open('constituencyDetailReportAction.action?districtId='+district+'&task='+constituencyId+'','_blank');
	});
	
}

function getTotalCasteCounts()
{
var jObj = 
	{
	
	 task : "getCount"
	}
	$.ajax({
			type:'GET',
			url: 'getTotalCasteCollectedCountsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
			}).done(function(result){

				buildCastCounts(result);
			});
			setTimeout(getTotalCasteCounts, 3000);
}

function buildCastCounts(result)
{

var str=''
/*str+='<div class="row">';
str+='<div class="span10">';
str+='<div class="row-fluid ">';
str+='<div class="span4">Total Caste Collected Count : '+result.count+'</div> ';
str+='<div class="span4">Today Caste Collected Count : '+result.casteCount+'</div> ';
str+='</div>';
str+='</div>';
str+='</div>';*/
/*str+='<table>';
str+='<tr><td><b>Total Caste Collected Count </b></td><td> <b>&nbsp;:&nbsp;</b></td><td class="badge badge-info"> <b><a href="#" style="color:#fff;" onclick="getDateWiseCount(\'\')">'+result.count+'</a></b></td> <td>&nbsp;&nbsp;</td>';
str+='<td><b>Today Caste Collected Count </b></td><td> <b>&nbsp;:&nbsp;</b></td><td class="badge badge-info"> <b>'+result.casteCount+'</b></td></tr> ';


str+='</table>';
$("#casteCountDiv").html(str);*/
$("#TotalcasteCount").html('<a href="#" style="color:#5B5B5B;" onclick="getDateWiseCount(\'\')">'+result.count+'</a>');
$("#TodaycasteCount").html(''+result.casteCount+'');

}
function getDateWiseCount(date)
{
	$("#popupImg").show();
	$("#PopupContentDiv").html('');
	$("#dateWisecastePopupDiv").dialog({
			title:"Caste Collected Count Details",
			autoOpen: true,
			show: "blind",
			width: 500,
			
			modal: true,
			height:300,
			hide: "explode"
		});

var jObj = 
	{
	 date:date,
	 task : "getDataCount"
	}
	$.ajax({
			type:'GET',
			url: 'getCasteCollectedCountsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
			}).done(function(result){

				buildDayWiseCasteCount(result);
			});


}
function buildDayWiseCasteCount(result)
{
$("#popupImg").hide();
var str='';
str+='<table class="table table-bordered">';
str+='<thead>';
str+='<th>Date</th>';
str+='<th>Count</th>';
str+='</thead>';
for(var i in result)
	{
	str+='<tr>';
	str+='<td>'+result[i].surveyDate+'</td>';
	str+='<td>'+result[i].count+'</td>';
	str+='</tr>';
	}
str+='</table>';
$("#PopupContentDiv").html(str);

	
}
</script>
<script>
getTotalCasteCounts();
</script>
  