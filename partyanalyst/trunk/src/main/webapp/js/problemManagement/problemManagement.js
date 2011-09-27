
var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();
var defaultDate =  new Date().getFullYear() + "/" + (new Date().getMonth() + 1) + "/" + new Date().getDate();

var pMgmt_Obj = {
	
	problemStatus:[],
	graphStartIndex:'0',
	graphMaxResults:'5',
	startIndex:0,
	problemsCount:4,
	initialize:function(){
		
		/*$("#pmTabs").tabs();
		$("#pmTabs").bind( "tabsselect", function(event, ui) {

			if(ui.index == 0)
				handleNewIssuesTabClick();
			else if(ui.index == 1)
				handleClassifiedIssTabClick();	
			else if(ui.index == 2)
				handleAssignedIssTabClick();
			else if(ui.index == 3)
				handleProgressedIssTabClick();	
			else if(ui.index == 4)
				handlePendingIssTabClick();
			else if(ui.index == 5)
				handleFixedIssTabClick();	
		});*/
		this.buildOverviewGraphData();
		this.getNewProblemsForUser();

		var addProblemButton = new YAHOO.widget.Button({ 
											id: "reportNewProblem",  
											type: "link",  
											label: "Add New Problem",
											href: "javascript:{}",
											container: "newProblemDiv_button"  
											});

		addProblemButton.on("click", openAddNewProblemWindow); 

		var optionsButton = new YAHOO.widget.Button({ 
											id: "optionsButton",  
											type: "link",  
											label: "Options",
											href: "javascript:{}",
											container: "problemsDataDiv_head_button"  
											});

		optionsButton.on("click", this.showFilterOptions); 

	},
	buildOverviewGraphData:function(){

		var jsObj = {
				
				task:"getOverViewGraph"
		};

		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "problemsOverViewGraphDataAction.action?"+param;
		this.pmAjaxCall(jsObj,url);
	},
	getNewProblemsForUser:function (){	

		var elmt = document.getElementById("alertMessage");
		elmt.style.display = 'none';
		var jsObj= 
		{
			task:"newProblemsByUserID",
			statusI:1
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "problemManagementAction.action?"+param+"&hidden="+hidden;
		
		custom_paginator.paginator({
			startIndex:this.startIndex,
			resultsCount:this.problemsCount,
			jsObj:jsObj,
			ajaxCallURL:url,
			paginatorElmt:"custom_paginator_class",
			callBackFunction:function(){
				buildProblemsData(jsObj,results);
			}
		});
		custom_paginator.initialize();
		//callAjax(param,jsObj,url);		
	},	
	getAllProblemsByFilter:function(){
		
		var cursorDIV = document.getElementById("busyCursorDiv");
        if(cursorDIV)
			cursorDIV.style.display = 'block';
			
		var startElmt = document.getElementById("identifiedFromText");
		var endElmt = document.getElementById("reportedFromText");

		var startText = startElmt.value;
		var endText = endElmt.value;

		var statusElmt = document.getElementById("statusSelectElmt");
		var statusValue = statusElmt.options[statusElmt.selectedIndex].value;

		var jsObj= 
		{
			task:"newProblemsByUserID",
			startText:startText,
			endText:endText,
			statusValue:statusValue
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "problemManagementActionByFilterView.action?"+param;

		custom_paginator.paginator({
			startIndex:this.startIndex,
			resultsCount:this.problemsCount,
			jsObj:jsObj,
			ajaxCallURL:url,
			paginatorElmt:"custom_paginator_class",
			callBackFunction:function(){
				buildProblemsData(jsObj,results);
			}
		});
		custom_paginator.initialize();
	},
	pmAjaxCall:function(jsObj,url)
	{
		var myResults;
 					
 		var callback = {			
 							success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);														
									if(jsObj.task == "getOverViewGraph")
									{		
										this.buildOverViewGraph(jsObj,myResults)			
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
	},
	buildOverViewGraph:function(jsObj,results){		
		
		
		var elmt = document.getElementById("statisticalDataBodyDiv_head");

		if(results.chartDataVO == null || results.chartDataVO.length == 0){
            
			var hStr = '';
			hStr+='<span style="color:red;font-size:14px;font-weight:bold;font-family:verdana;">No Problems Available To Display ..</span>';

            elmt.innerHTML = hStr;
			return;
		}

		var totalCount = 0;
		var hStr = '';
		hStr += '<table class="statsData_table">';
		hStr += '<tr>';
		for(var i=0; i<results.chartDataVO.length; i++)
		{
			totalCount += results.chartDataVO[i].problemsCount;
			hStr += '<th>'+results.chartDataVO[i].chartLabel+'</th>';
			hStr += '<td>'+results.chartDataVO[i].problemsCount+'</td>';
		}
		hStr += '<th> Total </th>';
		hStr += '<td>'+totalCount+'</td>';
		hStr += '</tr>';		
		hStr += '</table>';
		elmt.innerHTML = hStr;

		var chart;
		var xAxisLabels = [];
		var series = [];
		var localArray = [];
		
		// For legends (start)
		for(var i=0; i<results.chartDataVO.length; i++)
		{
			xAxisLabels.push(results.chartDataVO[i].chartLabel);
		}
		// For legends (End)
		
		// For Coloumn Chart data (start)
		for(var i=0; i<results.chartDataVO[0].chartValues.length; i++)
		{
			var loopLocalArray = [];
			for(j=0; j<results.chartDataVO.length; j++)
			{
				loopLocalArray.push(results.chartDataVO[j].chartValues[i]);
			}
			localArray.push(loopLocalArray);
		}

		
		for(var i=0; i<results.chartDataVO[0].chartValues.length; i++)
		{
			var obj = {
						type:'column',
						name:results.chartLegends[i].name,
						data:localArray[i]
					  };
			series.push(obj);
		}
		// For Coloumn Chart data (End)
		
		// For Spline Chart data (start)
		/*var spineDataArr = [];
		for(var i=0; i<results.chartDataVO.length; i++)
		{
			spineDataArr.push(results.chartDataVO[i].avgCount);			
		}
		var obj = {
					type:'spline',
					name:'Average',
					data:spineDataArr
				  };
		series.push(obj);*/
		// For Spline Chart data (End)
		
		// For Pie Chart data (Start)
		var pieDataArr = [];
		var color = ["#4572A7","#AA4643","#89A54E","#89D54A","#29C56E","#11A54D"];
		for(var i=0; i<results.chartDataVO.length; i++)
		{
			var obj = {
						name: results.chartDataVO[i].chartLabel,
						y: results.chartDataVO[i].problemsCount,
						color: color[i] 
					}
			pieDataArr.push(obj);
		}
		
		
		var obj = {
					type: 'pie',
					name: 'Total consumption',
					data: pieDataArr,
					center: [100, 80],
					size: 100,
					showInLegend: false,
					dataLabels: {
						enabled: false
					}
				};
		series.push(obj);
		// For Pie Chart data (End)

		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'statisticalDataBodyDiv_body'
					},
					title: {
						//text: ''+results.chartTitle
						text:'Overall Problems Information'
					},
					xAxis: {
						categories: xAxisLabels
					},
					tooltip: {
						formatter: function() {
							var s;
							if (this.point.name) { // the pie chart
								s = ''+
									this.point.name +': '+ this.y +' problem(s)';
							} else {
								s = ''+
									this.x  +': '+ this.y;
							}
							return s;
						}
					},
					labels: {
						items: [{
							html: 'Total Posted Problems',
							style: {
								left: '40px',
								top: '8px',
								color: 'black'				
							}
						}]
					},
					series: series
				});
	},
	showFilterOptions:function()
	{
		$('#problemFilterOptions').slideToggle();
	}
};

function showDateCal(divId, textBoxId,pageDate) {
	
	textBoxDivId = textBoxId;
	var id = document.getElementById(divId);
	if (dateCalendar)
		dateCalendar.destroy();

	var navConfig = {
		strings : {
			month : "Choose Month",
			year : "Enter Year",
			submit : "OK",
			cancel : "Cancel",
			invalidYear : "Please enter a valid year"
		},
		monthFormat : YAHOO.widget.Calendar.SHORT,
		initialFocus : "year"
	};

	var dateCalendar = new YAHOO.widget.Calendar(id, {
		navigator : navConfig,
		maxdate: maxDate,
		title : "Choose a date:",
		close : true
	});
	dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true);
	dateCalendar.render();
	dateCalendar.show();
}

function buildProblemsData(jsObj,results)
{	

    var elmtBody = document.getElementById("problemsDataDiv_body");

    var cursorDiv = document.getElementById("busyCursorDiv");
    if(cursorDiv)
		cursorDiv.style.display = 'none';
 

	var newProblems = results.problemsOfUserVO.problemsByUser;
    if(newProblems == null || newProblems.length == 0)
		{
		  elmtBody.innerHTML = '<div class="newProblemData_main" style="width:95%"><center><b>No Records Found<center></b> </div>';
		  return;
		}

	var bStr = '';
	for(var i=0;i<newProblems.length; i++)
	{
		bStr += '<div class="newProblemData_main" style="width:95%">';
		bStr += '<div class="newProblemData_head"></div>';
		bStr += '<table width="100%">';
		bStr += '<tr><td colspan="2"><div class="problem_title">Title : ';
		bStr += '<a class="problem_title" href="javascript:{}" onclick="openProblemDetailsWindow(\''+newProblems[i].problemHistoryId+'\')">'+newProblems[i].problem+'</a></div></td>';
		bStr += '<td><img width="48" height="48" src="images/icons/'+newProblems[i].problemStatus+'.png"></td>';
		bStr += '</tr>';
		bStr += '<tr><td colspan="2"><div class="problem_desc">Desc : '+newProblems[i].description+'</div></td></tr>';
		bStr += '<tr><th></th><td></td>';

		bStr += '<tr><td colspan="2"><div class="problem_desc_loc">Location : '+newProblems[i].problemLocation+'</div></td></tr>';
		bStr += '<tr>';
		bStr += '<td><div class="problem_lastRow">';
		bStr += '<font color="#2C4352" style="font-weight:bold;font-family: verdana;font-size: 13px;">Existing from : '
		bStr +=  newProblems[i].existingFrom;
		bStr += '</font>';
		bStr += '</div></td>';
		bStr += '<td><div class="problem_lastRow">';
		bStr += '<font color="#2C4352" style="font-weight:bold;font-family: verdana;font-size: 13px;">Posted Date : ';
		bStr +=  newProblems[i].reportedDate;
		bStr += '</font>';
		bStr += '</div></td>';
		bStr += '</tr>';
		bStr += '</tr>';
		bStr += '</table>';
		bStr += '</div>';
	}
	bStr += '<div class="custom_paginator_class"></div>';
	if(elmtBody)
		elmtBody.innerHTML = bStr;
   
		
}

function displayDateText(type, args, obj) {
	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var divId = obj.containerId;
	var divElmt = document.getElementById(divId);

	var txtDate1 = document.getElementById(textBoxDivId);
	txtDate1.value = year + "-" + month + "-" + day;
	minDate = month + "-" + day + "-" + year;
	divElmt.style.display = 'none';
}