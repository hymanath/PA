<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election results in all revenue villages of ${tehsilName } Mandal </title>
<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>
    <!--<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

	<!-- YUI Dependency files (End) -->

	<script type="text/javascript" src="js/districtPage/districtPage.js"></script>	
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	
	<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
	<!--<script type="text/javascript" src="js/highcharts/js/modules/exporting.js"></script>-->
	
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	
	<script>// Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});
	</script>
	<style type="text/css">
		.mainHeading 
		{
			background-image:url("images/icons/electionResultsReport/heading.png");
			border:0 solid #AEE2FF;
			color:#000000;
			font-family:MS Sans-serif;
			font-size:18px;
			font-weight:bold;
			height:25px;
			margin-bottom:15px;
			margin-top:15px;
			padding:10px;
			text-align:center;
		}
		.button 
		{
			background-attachment:scroll;
			background-color:#335291;
			background-image:none;
			background-position:0 0;
			background-repeat:repeat;
			color:#FFFFFF;
			width:113px;			
		}

		#partiesTrendzInputTable
		{
			font-size:13px;
		}
		
		#partiesTrendzInputTable th
		{
			padding:5px;
		}

		#votesPolledDtTableDiv_outer .yui-dt th a 
		{
			font-size:17px;
		}

		#votesPolledDtTableDiv_outer .yui-dt td 
		{
			color:#121922;
			font-size:16px;
			font-weight:bold;
		}
		label{display:inline-block;}
		input[type="radio"], input[type="checkbox"] {margin:5px;}
		.hero-unit{padding:40px;color:black;font-size:15px;}
	</style>
	<script type="text/javascript">
	var chartDataArr=[];
	var linechartDataArr=[];
	var constituency_name;
	var mandalId = '${tehsilId}';
	var tehsilId = '';
	var constituencyId = '';
	$(document).ready(function(){
	  var checkedType = '${checkedType}';
	  if(checkedType == "panchayat"){
	     $("#panchayatChk").attr("checked","checked");
	  }
	});
		function buildVotesPolledDataTable() {
			var resultsDataSourceForTehsil = new YAHOO.util.DataSource(YAHOO.util.Dom
					.get("votesPolledTable"));
			resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
			resultsDataSourceForTehsil.responseSchema = {
				fields : [  {
					key : "townshipName"
				},{
					key : "percentageOfValidVotes",
					parser:YAHOO.util.DataSourceBase.parseNumber
				}]
			};
	        if('${checkedType}' == "panchayat"){
				var resultsColumnDefsForTehsil = [  {
					key : "townshipName",
					label : "Panchayat Name",
					sortable : true
				},{
					key : "percentageOfValidVotes",
					label : "Votes %",
					sortable : true,
					formatter:YAHOO.widget.DataTable.formatFloat
				}];	
		    }else{
              var resultsColumnDefsForTehsil = [  {
				key : "townshipName",
				label : "Township Name",
				sortable : true
			   },{
				key : "percentageOfValidVotes",
				label : "Votes %",
				sortable : true,
				formatter:YAHOO.widget.DataTable.formatFloat
			  }];	
            }		   
			var myDataTableForTehsil = new YAHOO.widget.DataTable("votesPolledDtTableDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil);			
		}

	function checkAllBoxes()
	{
		var elements = document.getElementsByTagName('input');

		for(var i =0; i<elements.length; i++)
		{
			if(elements[i].type=="checkbox" && (elements[i].name=="elections" || elements[i].name=="parties"))
			{	
				elements[i].checked=true;
			}
			
		}
	}

	function UncheckAllBoxes()
	{
		var elements = document.getElementsByTagName('input');

		for(var i =0; i<elements.length; i++)
		{
			if(elements[i].type=="checkbox" && (elements[i].name=="elections" || elements[i].name=="parties"))
			{	
				elements[i].checked=false;
			}
		}
	}

	function getConstituencyIds()
	{
		var jsObj=
             {
			     id:mandalId,
				 task:"getConstituencyId",
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getConstituencyByMandalIdAction.action?"+rparam;

		callAjax(jsObj, url);

	}
	function getConstituencieSubs(constituencyId)
	{
		var jsObj=
             {
			     id:constituencyId,
				 task:"subRegionsInConstituency",
				 taskType:"",
				 address:"",
				 areaType:"RURAL",
				 isParliament:"true"
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;

		callAjax(jsObj, url);
	}

	function callAjax(jsObj,url)
	{			
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								if(jsObj.task == "subRegionsInConstituency"){
									buildMandals(myResults);
								}
								else if(jsObj.task == "getConstituencyId"){
									constituencyId = myResults;
									if(constituencyId != null && constituencyId != "null")
									getConstituencieSubs(constituencyId);
								}
							}catch (e) {   
							   	//alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	function buildMandals(result)
	{
		if(result != null && result.length > 0)
		{
			for(var i in result)
			{
				if(result[i].id != 0)
				{
					var id = new String(result[i].id);
					tehsilId = parseInt(id.substring(1));
					
						$('#mandalSelectId').append('<option value='+tehsilId+'>'+result[i].name+'</option>');
					$('#mandalSelectId').val(mandalId);
				}
			}
			$('#mandalShowHideDiv').show();
		}
		else
		{
			$('#mandalShowHideDiv').hide();
		}
		
	} 
	function getReqTehsilIds()
	{
		tehsilId =  $('#mandalSelectId').val();
		var name  = $("#mandalSelectId option:selected").text();
		var tehsilName = name.replace("MANDAL","");
		$('#reqtehsilId').val(tehsilId);
		$('#reqtehsilName').val(tehsilName);
	}
	getConstituencyIds();
</script>
</head>
<body>
<!--<table width="100%" bgcolor="black" cellpadding="0" cellspacing="0">
		<tbody><tr>		
		<!--<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"></td>
			<td width="72%" align="left">
				<table cellspacing="0" cellpadding="0" border="0" style="margin-left:30px;">
				<tbody><tr>
					<td valign="top"><img border="none" style="margin-top: 15px;" src="images/icons/electionResultsReport/elections_logo1.png"></td>
					<td valign="top"><div class="mainHeading" id="mainHead"> All Parties Results In All Elections</div></td>
					<td valign="top"><img border="none" style="margin-top: 15px;" src="images/icons/electionResultsReport/elections_logo2.png"></td>
				</tr>
				</tbody></table>
			</td>
			<!--<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"></td>
		</tr>
	</tbody></table>-->
	<div align="center"><h3>All Parties Results In All Elections</h3></div>
	<div style="color:#247CD4;font-size:19px;font-weight:bold;margin-bottom:23px;margin-top:36px;text-align:center;">All Parties Trends In All Elections Of ${tehsilName } Mandal</div>
	<div class="hero-unit" style="width:750px;margin-left:auto;margin-right:auto;">
	<c:if test="${! empty mandalVO}">
		<s:form action="mandalRevenueVillagesElecViewAction" name="MandalRevenueVillagesElecViewAction" method="GET" enctype="multipart/form-data">
		<div id="mandalShowHideDiv">
			<span>Select Mandal : </span>
			<span><select id="mandalSelectId" onChange="getReqTehsilIds();"></select></span>
		</div>
		<table id="partiesTrendzInputTable">
				
			    <tr>
					<th align="left">Show Result : </th>
					<th colspan="2" align="left"><input type="radio" value="revenueVillage" name="resultType" checked="checked">Revenue Villages Wise <input type="radio" id="panchayatChk" value="panchayat" name="resultType" > Panchayat Wise</th>
				</tr>
				<tr>
					<th align="left">Parties : </th>
					<th colspan="2" align="left"><s:checkboxlist theme="simple" list="mandalVO.partiesInMandal" listKey="id" listValue="name" name="parties" label="Parties:"/></th>
				</tr>
				<tr>
					<th align="left">Elections : </th>
					<th colspan="2" align="left"><s:checkboxlist theme="simple" list="mandalVO.electionsInMandal" listKey="id" listValue="name" name="elections" label="Elections:"/></th>
				</tr>
				<tr>
					<td align="center">
						
					</td>
					<td align="left">
							<s:checkbox theme="simple" id="allianceCheck" name="includeAlliance" value="hasAllianceParties"></s:checkbox><b> Include Aliance Parties</b>
							<input type="button" style="margin-left:0px" class="button" onclick="checkAllBoxes()" value="Select All"/>
							<input type="button" style="margin-left:0px" class="button" onclick="UncheckAllBoxes()" value="DeSelect All"/>
							<s:submit theme="simple" cssClass="button" label="View Chart" />
					</td>
					
				</tr>
				<tr>
					<td><input type="hidden" name="tehsilId" id="reqtehsilId" value="${tehsilId}"/></td>
					<td><input type="hidden" name="tehsilName" id="reqtehsilName" value="${tehsilName}"/></td>
					<td><input type="hidden" name="constituencyId" value="${constituencyId}"/></td>
				</tr>
				<tr><td colspan="2" align="center"></tr>
			</table>
		</s:form>
	</c:if>
	</div>
	<div align="center" style="background:#ffffff;margin-bottom:1px;">
		<c:if test="${! empty chartPath}">
		
			<!--<img src="charts/${chartPath}">-->
			<div id="container" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
			<div id="container1" style="width:800px;margin-left:auto;margin-right:auto;background:violet"></div>
		</c:if>
	</div>
	
	<div id="votesPollingInMandalDIV" style="background:#ffffff;" align="center">
		<table id="votesPollingInMandal" width="90%">
			<c:forEach var="votesPollingInMandal" items="${townshipBoothDetailsVO}">
				<tr>
					<td width="40%" style="vertical-align:top;">
						<div id="votesPolledDtTableDiv_outer" class="yui-skin-sam">
							<div id="votesPolledDtTableDiv" style="padding-top:40px;margin-top:80px;">
								<table id="votesPolledTable">	
									<c:forEach var="votesPolling" items="${votesPollingInMandal.townshipVotingTrends}">
										<tr>
											<td>
												${votesPolling.townshipName}
											</td>
											<td>
												${votesPolling.percentageOfValidVotes}
											</td>	
										</tr>
																											
									</c:forEach>	
								</table>	
							</div>
						</div>	
					</td>
					<td width="60%" style="vertical-align:top;" align="center">			
					
										
						<c:forEach var="votesPollingInMandal" items="${townshipBoothDetailsVO}">				
							
							
							<script>
								var charttitle='${votesPollingInMandal.chartTitle}'</script>							
							<c:forEach var="ex" items="${votesPollingInMandal.townshipVotingTrends}">
							<script>
								var chartObj={
									name:'${ex.townshipName}',
									percentage:'${ex.percentageOfValidVotes}'
								};
								chartDataArr.push(chartObj);
							</script>
							</c:forEach>
								<table>	
									<tr>	
										<td>
											<!--<img src="charts/${votesPollingInMandal.chartName}">-->
											<div id="chart_div" ></div>
										</td>	
									</tr>	
								</table>							
						</c:forEach>	
						
						
					</td>
				</tr>																				
			</c:forEach>
			
		</table>
	</div>	
	
	
	<script type="text/javascript">
	buildVotesPolledDataTable();
	/* Created By Sasi for HighCharts Start*/
	
	buildGoogleChart();
	
	var seriesValues = new Array();
	function buildGoogleChart(){
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Name');
        data.addColumn('number', 'Percentage');
		data.addRows(chartDataArr.length);
		
		for(var i=0; i<chartDataArr.length; i++)
		{
			data.setValue(i, 0, chartDataArr[i].name);
	        data.setValue(i, 1, parseFloat(chartDataArr[i].percentage));
		}
        
        // Set chart options
        var options = {'title':charttitle,
                       'width':700,
                       'height':600};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
	}
	
	test();
	
	function test()
	{
	
	    <c:forEach var="a" items="${partiesResults}">
                  if(linechartDataArr.indexOf('${a.constituencyName}') == -1)
					linechartDataArr.push('${a.constituencyName}');
		</c:forEach>
		
	}
	
	buildLineChart();
	
	
    var chart;
	function buildLineChart(){
    
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'line',
               /* marginRight: 130,
                marginBottom: 25 */
            },
            title: {
                text: 'Voting Percentages in ${tehsilName } Mandal',
                x: -20 //center
            },
           /* subtitle: {
                text: 'Source: WorldClimate.com',
                x: -20
            },*/
            xAxis: {
                categories: linechartDataArr,
				
				 labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                title: {
                    text: 'Votes Percent( % )'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y +'%';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [
			
			<c:forEach var="entry" items="${partyResultMapPrcnt}" varStatus="loop">
		      {
				name: '<c:out value="${entry.key}"/>',
				data: <c:out value="${entry.value}"/>
			  }<c:if test="${!loop.last}">,</c:if>
		    </c:forEach>
			]
        });
		
		$('tspan:last').hide();
    }
    
/* Created By Sasi for HighCharts End*/

</script>
	
	
	
	
</body>
</html>