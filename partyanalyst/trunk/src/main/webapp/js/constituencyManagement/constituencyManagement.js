var todayDate = new Date().getDate() + "/" + (new Date().getMonth() + 1) + "/" + new Date().getFullYear();
var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();
var minDate;
var resultsGlobal; 
var availableLength = 0;
var loginUserRegionId;
var loginUserRegionName;
var loginUserRegionType;

var localGroupsLoginUserRegionId;
var localGroupsLoginUserRegionName;
var localGroupsLoginUserRegionType;
var localGroupsLoginUserRegionTitle;
var localGroupsLoginUserRegionTitleId;

var localGroupsArray = new Array();
var localGroupIndex = new Array();

var problemMgmtObj = {
	problemsStatusArr : [],
	initialProblems:[]
};
var mobileNumbersArray = new Array();

var assignTolocalPoliticalChangesDataArray,localPoliticalChanges,externalPersonDetailsPanel;
var hidden = 1;
var inf_peopleArr = new Array();
function getTodayDateTime() {
	now = new Date();
	hour = now.getHours();
	min = now.getMinutes();
	sec = now.getSeconds();
	var textBoxEl = document.getElementById("tillDateText");
	if (min <= 9) {
		min = "0" + min;
	}
	if (sec <= 9) {
		sec = "0" + sec;
	}
	if (hour > 12) {
		hour = hour - 12;
		add = " p.m.";
	} else {
		hour = hour;
		add = " a.m.";
	}
	if (hour == 12) {
		add = " p.m.";
	}
	if (hour == 00) {
		hour = "12";
	}

	currentTime = ((hour <= 9) ? "0" + hour : hour) + ":" + min + ":" + add;
	return todaysDateTime = new Date().getDate() + "/"
			+ (new Date().getMonth() + 1) + "/" + new Date().getFullYear()
			+ " " + currentTime;
}



function buildPoliticalChanges() {
	var elmt = document.getElementById("political_changes_data_body");

	if (!elmt)
		return;

}

function showProblemsStatusCount(results) {
	
	var problemStats_bodyEl = document.getElementById("problems_outline_Div");
	var chartName = results.problemsStatusChartName;
	var barChartName = results.lineChartPath;
	var problems_OptionsEl = document.getElementById("problems_Options");
	var problemStats_bodyElContent = '';
	problemStats_bodyElContent += '<TABLE class="problemsStatsTable" cellspacing="10" cellpadding="10" border="2" width="98%">';
	problemStats_bodyElContent += '	<TR>';
	problemStats_bodyElContent += '		<TD halign="center" valign="top" width="35%">';
	problemStats_bodyElContent += '			<TABLE border="0">';
	problemStats_bodyElContent += '				<TR>';
	problemStats_bodyElContent += '					<TD colspan="2"><DIV class="widgetHeaders">Current Problems and their Status</DIV></TD>';
	problemStats_bodyElContent += '				</TR>';
	problemStats_bodyElContent += '				<TR>';
	problemStats_bodyElContent += '					<TD> <IMG src="charts/'+chartName+'" border="0" width="250"/></TD>';
	problemStats_bodyElContent += '				</TR>';
	problemStats_bodyElContent += '				<TR>';
	problemStats_bodyElContent += '					<TD align="center" style="border:1px solid #000000;">';
	problemStats_bodyElContent += '						<TABLE class="problemsStatsTable" border="0" cellpadding="3" cellspacing="3">';
	problemStats_bodyElContent += '							<TR>';
	for ( var i in results.problemsCountByStatus) {
		if (results.problemsCountByStatus[i].status == 'FIXED')
			continue;
		problemStats_bodyElContent += '								<TD align="left">'
				+ results.problemsCountByStatus[i].status
				+ '</TD><TD align="left"><DIV class="'
				+ results.problemsCountByStatus[i].status
				+ '"></DIV></TD><TD align="left"><A href="javascript:{}" onclick="getProblemsByStatusInLocations(\''
				+ results.problemsCountByStatus[i].statusId + '\')">'
				+ results.problemsCountByStatus[i].count + '</A></TD>';
		if (i != 0 && i % 2 == 0)
			problemStats_bodyElContent += '</tr><tr>';
	}
	problemStats_bodyElContent += '							</TR>';
	problemStats_bodyElContent += '						</TABLE>';
	problemStats_bodyElContent += '					</TD>';
	problemStats_bodyElContent += '				</TR>';
	problemStats_bodyElContent += '				<TR>';
	problemStats_bodyElContent += '					<TD>';
	problemStats_bodyElContent += '						<TABLE class="probStatsCountTable">';
	problemStats_bodyElContent += '							<TR>';
	problemStats_bodyElContent += '								<TH>Total Problems:</TH>';
	problemStats_bodyElContent += '								<TD>' + results.totalProblemsCount + '</TD>';
	problemStats_bodyElContent += '							</TR>';
	problemStats_bodyElContent += '							<TR>';
	problemStats_bodyElContent += '								<TH>Fixed Problems:</TH>';
	if(results.problemsCountByStatus.length != 0){
		problemStats_bodyElContent += '								<TD>' + results.problemsCountByStatus[i].count + '</TD>';
	}else{
		problemStats_bodyElContent += '								<TD>0</TD>';
	}		
	problemStats_bodyElContent += '							</TR>';
	problemStats_bodyElContent += '						</TABLE>';
	problemStats_bodyElContent += '					</TD>';
	problemStats_bodyElContent += '				</TR>';
	problemStats_bodyElContent += '			</TABLE>';
	problemStats_bodyElContent += '		</TD>';
	problemStats_bodyElContent += '		<TD align="center" valign="top" width="65%">';
	problemStats_bodyElContent += '			<TABLE>';
	problemStats_bodyElContent += '				<TR>';
	problemStats_bodyElContent += '					<TD><div><IMG src="charts/'+barChartName+'" border="0" width="550"/></div></TD>';
	problemStats_bodyElContent += '				</TR>';
	problemStats_bodyElContent += '			</TABLE>';
	problemStats_bodyElContent += '		</TD>';
	problemStats_bodyElContent += '</TR>';
	problemStats_bodyElContent += '</TABLE>';
	problemStats_bodyEl.innerHTML = problemStats_bodyElContent;

	var problems_OptionsContent = '';
	problems_OptionsContent += '<table cellspacing="10" cellpadding="10" border="1">';
	problems_OptionsContent += '<tr>';
	problems_OptionsContent += '<td style="vertical-align:top;" width="60%">';
	problems_OptionsContent += '<div id="problemOptionsHeadingDiv" class="widgetHeaders"> Problem Search Selection Criteria</div>';
	problems_OptionsContent += '<p class="widgetDescPara"> <font style="color:#4B74C6">Problem Search Criteria </font>enables the user to search problems posted with in selected period. Here the user has to provide the start date,end date and also the status of problem to view the set of problems.</p>';
	problems_OptionsContent += '<P class="widgetDescPara">Click on the Calendar Icons to select the  Dates.</P>';
	problems_OptionsContent += '<DIV id="alertMessageDiv" class="errorMessage"></DIV>';
	problems_OptionsContent += '<TABLE cellspacing="5">';
	problems_OptionsContent += '	<TR>';
	problems_OptionsContent += '		<TH valign="top">View Problems From:</TH>';
	problems_OptionsContent += '		<TD><input type="text" id="existingFromText" readonly="readonly" style="margin-top:0px;" name="existingFromText" size="20"/>';
	problems_OptionsContent += '			<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" class="tinyDateCal"></DIV></DIV></TD>';
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '		<TD valign="top">';
	problems_OptionsContent += '			<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()"><IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '		<TH valign="top">To:</TH>';
	problems_OptionsContent += '		<TD><input type="text" id="tillDateText" value="' + todayDate + '" name="tillDateText" size="20" readonly="readonly" class="textBoxStyle"/>';
	problems_OptionsContent += '			<DIV class="yui-skin-sam"><DIV id="till_Div" class="tinyDateCal"></DIV></DIV>';
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '		<TD valign="top">';
	problems_OptionsContent += '			<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal1()"><IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '	</TR>';
	problems_OptionsContent += '	<TR>';
	problems_OptionsContent += '		<TH valign="top">Problem Status:</TH>';
	problems_OptionsContent += '		<TD valign="top">';
	problems_OptionsContent += '			<SELECT id="selectStatus" theme="simple"  name="selectStatus" list="statusList" listKey="id" listValue="name" onchange="getProblemDetailsInSelectedDates(this.options[this.selectedIndex].value)"/>';
	for ( var i in problemMgmtObj.problemsStatusArr) {
		problems_OptionsContent += '<option value='
				+ problemMgmtObj.problemsStatusArr[i].id + '>'
				+ problemMgmtObj.problemsStatusArr[i].value + '</option>';
	}
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '	</TR>';
	problems_OptionsContent += '</TABLE>';
	problems_OptionsContent += '</div>';
	problems_OptionsContent += '</td>';
	problems_OptionsContent += '<td style="vertical-align:top;">';
	problems_OptionsContent += '<div id="problemSearch_advanced_main">';
	problems_OptionsContent += '<div id="problemSearch_advanced_head" class="widgetHeaders">Advanced Search Selection Criteria</div>';
	problems_OptionsContent += '<div id="problemSearch_advanced_body">';
	problems_OptionsContent += '<p class="widgetDescPara" style="height:90px;"> ';
	problems_OptionsContent += '<font style="color:#4B74C6"> Advanced Search Criteria</font> enables the user to search for a problem with different options like location, Status or Department.';
	problems_OptionsContent += '</p>';
	problems_OptionsContent += '<p style="text-align:right;"> <A href="problemManagementReportAction.action" class="linkButton" target="_blank">Advanced Search</A></DIV></p>';
	problems_OptionsContent += '</div>';
	problems_OptionsContent += '</div>';
	problems_OptionsContent += '</td>';
	problems_OptionsContent += '</tr>';
	problems_OptionsContent += '<tr>';
	problems_OptionsContent += '<td colspan="2">';
	problems_OptionsContent += '<DIV class="yui-skin-sam">';
	problems_OptionsContent+='<div id="problemsDetailsDTDiv_head" class="widgetHeaders">Problem Details</div>';
	problems_OptionsContent += '<DIV id="problemsDetailsDTDiv"></DIV>';
	problems_OptionsContent += '</DIV></td>';
	problems_OptionsContent += '</tr>';
	problems_OptionsContent += '</table>';
	problems_OptionsEl.innerHTML = problems_OptionsContent;

	buildProblemsDetailsDT(problemMgmtObj.initialProblems);
}

function showDateCal() {
	var id = document.getElementById("existingFromText_Div");
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
function displayDateText(type, args, obj) {
	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var divId = obj.containerId;
	var divElmt = document.getElementById(divId);

	var txtDate1 = document.getElementById("existingFromText");
	txtDate1.value = day + "/" + month + "/" + year;
	minDate = month + "/" + day + "/" + year;
	divElmt.style.display = 'none';
}

function showDateCal1() {
	
	var id = document.getElementById("till_Div");
	if (dateCalendar1)
		dateCalendar1.destroy();

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

	var dateCalendar1 = new YAHOO.widget.Calendar(id, {
		navigator : navConfig,
		minDate: minDate,
		maxdate: maxDate,
		title : "Choose a date:",
		close : true
	});
	dateCalendar1.selectEvent.subscribe(displayDateText1, dateCalendar1, true);
	dateCalendar1.render();
	dateCalendar1.show();
}
function displayDateText1(type, args, obj) {
	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var divId = obj.containerId;
	var divElmt = document.getElementById(divId);

	var txtDate1 = document.getElementById("tillDateText");
	txtDate1.value = day + "/" + month + "/" + year;
	divElmt.style.display = 'none';
}

function buildProblemsDetailsDT(results) {
		var problemsArr = new Array();
		
		for(var i in results){
			var problem = {
					problem:results[i].problem, 
				  	description:results[i].description,
					existingFrom:results[i].existingFrom,
					hamlet:results[i].hamlet,
					problemSourceScope:results[i].problemSourceScope,
					problemAndProblemSourceId:results[i].problemAndProblemSourceId,
					status:results[i].status,
					problemSource:results[i].probSource,
					more:'<a href="javascript:{}" onclick="getProblemHistoryInfo('+results[i].problemLocationId+')">More Info</a>'
			};
			
			problemsArr.push(problem);
		}
		
		var elmt = document.getElementById("problemsDetailsDTDiv");
		
		if (!elmt)
			return;
		if(problemsArr.length != 0){
			var probDTColumnDefs = [ 
			        				{
			        					key : "problem",
			        					label : localizationObj.problemLabel,
			        					sortable : true
			        				}, {
			        					key : "description",
			        					label : localizationObj.description
			        				}, {
			        					key : "existingFrom",
			        					label : localizationObj.existingFrom,
			        					sortable : true
			        				}, {
			        					key : "hamlet",
			        					label : "Location",
			        					sortable : true
			        				}, {
			        					key : "problemSourceScope",
			        					label : localizationObj.source,
			        					sortable : true
			        				}, {
			        					key : "problemAndProblemSourceId",
			        					hidden : true
			        				}, {
			        					key : "status",
			        					label : localizationObj.status,
			        					sortable : true
			        				} ,{
			        					key : "more",
			        					label : "More Info",
			        					sortable : false
			        				}
			        		];
			        		
			        		var probDTDataSource = new YAHOO.util.DataSource(problemsArr);
			        		probDTDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
			        		probDTDataSource.responseSchema = {
			        			fields : [ "problem", "description", "existingFrom", 
			        			           "hamlet", "problemSourceScope", "problemAmdProblemSourceId", 
			        			           "status" , "more"]
			        		};
			        		
			        		if (problemsArr.length > 10) {
			        			var myConfigs = {
			        				paginator : new YAHOO.widget.Paginator( {
			        					rowsPerPage : 10
			        				}),
			        				caption : "Recent Problems"
			        			};
			        		}
			        		
			        		var probDataTable = new YAHOO.widget.DataTable("problemsDetailsDTDiv",
			        				probDTColumnDefs, probDTDataSource, myConfigs);
		}else{
			elmt.innerHTML = '';
			elmt.innerHTML = '<SPAN style="color:green;font-weight:bold;">Zero problems matched this selection criteria</SPAN>';
		}
		
}

function buildSubRegionsPieChart(results,type)
{	
	for(var i=0; i<results.length; i++)
	{
		if(results[i].countValue == 0)
			continue;

		var elmt;
		if(type == "influencePeople")
		{
			elmt = document.getElementById("subRegionChartDiv_"+i+"_main")
		}
		else if(type == "localGroups")
		{
			elmt = document.getElementById("LGSubRegionChartDiv_"+i+"_main")
		}

		var data = new google.visualization.DataTable();
		var chartData = results[i].subRegionWiseOverview;
		
		data.addColumn('string', 'Region');
		data.addColumn('number', 'Count');	

		data.addRows(chartData.length);
		
		for(var j=0; j<chartData.length; j++)
		{
			data.setValue(j, 0, ''+chartData[j].subRegionName);
			data.setValue(j, 1, chartData[j].countValue);
		}
		
		var chart = new google.visualization.PieChart(elmt);
        chart.draw(data, {width: 250, height: 250, titleTextStyle:{color:'77471D',fontWeight:'bold'}, title: 'Sub Regions Share under '+results[i].regionName+' '+results[i].regionType+'', legend:'right'});
 
	}
}

function buildInfluencingPeopleDT() {
	
	var ipDTColumnDefs = [ {
		key : "influencingPeopleId",
		hidden: true
	},	{
		key : "select",
		label : "",
		formatter : "checkbox"
	}, {
		key : "personName",
		label : localizationObj.name,
		sortable : true
	}, {
		key : "contactNumber",
		label : localizationObj.mobile
	}, {
		key : "party",
		label : localizationObj.party,
		sortable : true
	}, {
		key : "localArea",
		label : localizationObj.location,
		sortable : true
	}, {
		key : "influencingRange",
		label : localizationObj.inflScope,
		sortable : true
	}, {
		key : "influencingRangeName",
		label : "Influencing Place",
		sortable : true
	}, {
		key : "editDetails",
		label : "Edit"		
	}, {
		key : "deleteDetails",
		label : "Delete"		
	}];

	var ipDTDataSource = new YAHOO.util.DataSource(inf_peopleArr);
	ipDTDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	ipDTDataSource.responseSchema = {
		fields : ["influencingPeopleId","personName", "contactNumber", "party", "localArea",
				"influencingRange","influencingRangeName", "editDetails", "deleteDetails"]
	};

	if (inf_peopleArr.length > 10) {
		var myConfigs = {
			paginator : new YAHOO.widget.Paginator( {
				rowsPerPage : 10
			})
		};
	}

	var ipDataTable = new YAHOO.widget.DataTable("influencingPeopleDtDiv",
			ipDTColumnDefs, ipDTDataSource, myConfigs);
	ipDataTable.subscribe("checkboxClickEvent", function(oArgs) { 
  	    elCheckbox = oArgs.target; 
  	    var newValue = elCheckbox.checked; 
  	    var record = this.getRecord(elCheckbox); 
  	    var column = this.getColumn(elCheckbox); 
  	  
  	    record.setData(column.key,newValue);				  	
  	    
  	    if(newValue && hasRecordInArray(record))
  	  		{
  	    	mobileNumbersArray.push(record);
  	    	var smsTextEl = document.getElementById("smsText").focus();
  	  		}
		else
			{
				deleteRecordFromArray(record);
				elCheckbox.checked = false;
			} 	   		  	  				  	  	
	  	});	
}

function hasRecordInArray(record)
{	
	var status = true;
	for(i=0;i<mobileNumbersArray.length;i++)
	{	
		if(mobileNumbersArray[i]._oData.influencingPeopleId == record._oData.influencingPeopleId)
			status=false;
	}
	return status;
}
function deleteRecordFromArray(record)
{
	for(i=0;i<mobileNumbersArray.length;i++)
	{	
		if(mobileNumbersArray[i]._oData.influencingPeopleId == record._oData.influencingPeopleId)
			mobileNumbersArray.splice(i,1);
	}
		
}

function buildProblemsByStatusDialog(results, jsObj) {

	caption = "Problems in " + jsObj.status + " Status";

	var contentStr = '';
	contentStr += '<div id="problems_Datatable"></div>';

	var myPanel = new YAHOO.widget.Dialog("problemsByStatusPanelDiv", {

		width : "620px",
		fixedcenter : false,
		visible : true,
		constraintoviewport : true,
		iframe : true,
		modal : true,
		hideaftersubmit : true,
		close : true
	});
	myPanel.setHeader("Problems Details");
	myPanel.setBody(contentStr);
	myPanel.render();

	buildProblemsByStatusDataTable("problems_Datatable", results, caption);

}

function buildProblemsByStatusDataTable(divId, results, caption) {
	var elmt = document.getElementById("problemsDetailsDTDiv");

	if (!elmt)
		return;

	var probDTColumnDefs = [ {
		key : "problemLocationId",
		hidden : true
	}, {
		key : "name",
		label : localizationObj.problemLabel,
		sortable : true
	}, {
		key : "description",
		label : localizationObj.description
	}, {
		key : "existingFrom",
		label : localizationObj.existingFrom
	}, {
		key : "hamlet",
		label : "Location",
		sortable : true
	}, {
		key : "problemAmdProblemSourceId",
		hidden : true
	}, {
		key : "status",
		label : localizationObj.status,
		sortable : true
	} ];

	var probDTDataSource = new YAHOO.util.DataSource(results);
	probDTDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	probDTDataSource.responseSchema = {
		fields : [ "problemLocationId", "name", "description", "existingFrom",
				"hamlet", "problemSourceScope", "problemAmdProblemSourceId",
				"status" ]
	};

	if (results.length > 10) {
		var myConfigs = {
			paginator : new YAHOO.widget.Paginator( {
				rowsPerPage : 10
			}),
			caption : caption
		};
	}
	var probDataTable = new YAHOO.widget.DataTable(divId, probDTColumnDefs,
			probDTDataSource, myConfigs);

}

function limitText(limitField, limitCount, limitNum)
{		
	var limitFieldElmt = document.getElementById(limitField);
	var limitCountElmt = document.getElementById(limitCount);

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+" ";
	}
}

function showSentSmsConfirmation(result)
{
	
	var smsConfirmationEl = document.getElementById("smsConfirmation");
		
	var smsBlockAlertEl = document.getElementById("smsBlockAlert");
	var smsTextEl = document.getElementById("smsText");
	var str='';
	if(result.status==0){
		str+=" SMS sent successfully to "+result.totalSmsSent+" Members";
		if(result.remainingSmsCount!=0){
			str+=" You can send "+result.remainingSmsCount+"more SMS's";
		}else{
			str+="<br>";
			str+=" You cannot any more SMS ";
			smsRenewalMessage();
		}	
	}else{
		smsRenewalMessage();
	}
	smsConfirmationEl.innerHTML += str; 
	smsBlockAlertEl.innerHTML = '';
	smsTextEl.value = '';
	buildInfluencingPeopleDT(resultsGlobal);
}





function getLocalUserGroups()
{
	var jsObj= 
	{		
		regionId:"",
		regionType:"",
		task: "getLocalUserGroups"				
	};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocalUserGroupsAction.action?"+param;
	
	callAjax(param,jsObj,url);	
}

function buildDifferentViewsRadio(info,divId,type)
{
	var data = info.differentOverviews;
	var regionView = info.regionWiseOverview;

	var radioDiv = document.getElementById(divId);
	
	if((data == null || data.length == 0) && radioDiv)
	{
		radioDiv.innerHTML = '';
		return;
	}

	if(data.length > 0 && radioDiv)
	{
		var str = '';
		str += '<table>';
		str += '<tr>';
		str += '<th>Select scope to view its '+type+' </th>';
		str += '<th style="font-size:11px">';
		
		for(var i=0; i<data.length; i++)
		{
			if(i == 0)
				str += '<input type="radio" onclick="getInfluencePeopleScope(\''+regionView.regionId+'\',\''+regionView.regionName+'\',\''+regionView.regionType+'\',this.value,\''+type+'\')" checked="checked" value="'+data[i].name+'" name="diffViews_'+type+'">'+data[i].name;
			else
				str += '<input type="radio" onclick="getInfluencePeopleScope(\''+regionView.regionId+'\',\''+regionView.regionName+'\',\''+regionView.regionType+'\',this.value,\''+type+'\')" name="diffViews_'+type+'">'+data[i].name;
		}
		str += '</th>';
		str += '</tr>';
		str += '<tr>';
		str += '<th><div id="scopeSelectBoxLabel_'+type+'"></div></th>';
		str += '<th><div id="scopeSelectBoxData_'+type+'"></div></th>';
		str += '</tr>';
		str += '</table>';
		
		radioDiv.innerHTML = str;
	}
}

function getInfluencePeopleScope(regionId,regionName,regionType,selectType,type)
{
	var jsObj= 
	{		
		regionId:regionId,
		regionName:regionName,
		regionType:regionType,
		selectType:selectType,		
		taskType:type,
		task: "getInfluencePeopleScopeSelectBox"		
	};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getInfluencePeopleSelectScopeAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
	
}

function buildInfluencePeopleScopeSelectBox(jsObj,results)
{
	var labelElmt = document.getElementById("scopeSelectBoxLabel_"+jsObj.taskType);
	var dataElmt = document.getElementById("scopeSelectBoxData_"+jsObj.taskType);
	
	if(results.length == 0)
	{
		labelElmt.innerHTML = "";
		dataElmt.innerHTML = "";	
		
		if(jsObj.taskType == "influence people")
			getInfluencingPeopleInAConstituency();
		else if(jsObj.taskType == "local groups")
			getLocalUserGroups();

		return;
	}
	else
	{
		var lstr = 'Please select region';
		var dstr = '';
		dstr += '<table>';
		dstr += '<tr>';
		for(var i=0; i<results.length; i++)
		{
			dstr += '<th>'+results[i].label+'</th>';
			dstr += '<td>';

			if(jsObj.taskType == "influence people")
				dstr += '<select onchange="reGetInfluencingPeopleInAConstituency(\''+results[i].label+'\',this.options[this.selectedIndex].value)">';
			else if(jsObj.taskType == "local groups")
				dstr += '<select onchange="reGetLocalGroupsInAConstituency(\''+results[i].label+'\',this.options[this.selectedIndex].value)">';

			for(var j=0; j<results[i].optionsList.length; j++)
			{
				dstr += '<option value="'+results[i].optionsList[j].id+'">'+results[i].optionsList[j].name+'</option>';
			}
			dstr += '</select>';
			dstr += '</td>';
		}
		dstr += '</tr>';
		
		labelElmt.innerHTML = lstr;
		dataElmt.innerHTML = dstr;
	}
}

function getGroupsBasedOnCriteria(groupId,groupName,locationType)
{
	var jsObj= 
	{		
		groupId:groupId,
		groupName:groupName,
		locationType:locationType,
		task: "getUserGroupsBasedOnCriteria"				
	};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getUserGroupsBasedOnCriteriaAction.action?"+param;
	
	callAjax(param,jsObj,url);	
}

function buildUserGroupsBasedOnCriteria(jsObj,results)
{
	var elmt = document.getElementById("userGroupsDetailsViewDiv");

	if(!elmt)
		return;

	var str = '';
	str += '<div id="userGroupDetails_Head" class="groupHeaders"> ';
	str += '	<table>';
	str += '	<tr>';
	str += '		<td><img src="images/icons/indexPage/group_icon.png"></td>';
	str += '		<td>There are '+results.length+' groups under the '+jsObj.groupName+' Category</td>';
	str += '	</tr>';
	str += '	</table>';
	str += '</div>';

	str += '<div id="userGroupDetails_Body" class="yui-skin-sam">';
	str += '<div id="userGroupDetails_datatable"></div>';
	str += '</div>';
	elmt.innerHTML = str;

	var groupsArray = new Array();
	for(var i=0;i<results.length;i++)
	{
		var obj =	{
						groupName:results[i].groupName,
						createdDate:results[i].createdDate,
						noOfPersons:results[i].noOfPersons,
						locationInfo:results[i].locationInfo,
						view:'<a href="javascript:{}" onclick="showGroupCandidateDetails(\''+results[i].groupId+'\')"> View Candidates</a>'
					};
		groupsArray.push(obj);
	}
	

	var myColumnDefs = [ 
	            {key:"groupName",label:"Group Name", sortable:true, resizeable:true}, 
	            {key:"createdDate", label:"Date", sortable:true, resizeable:true}, 
	            {key:"noOfPersons", label:"Persons", formatter:YAHOO.widget.DataTable.formatNumber, sortable:true, resizeable:true}, 
	            {key:"locationInfo", label:"Location", sortable:true, resizeable:true}, 
	            {key:"view", label:"View", sortable:false, resizeable:true} 
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(groupsArray); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: [
							{	
								key:"groupName"
							},
							{
								key:"createdDate"
							},
							{
								key:"noOfPersons"
							},
							{
								key:"locationInfo"
							},
							{
								key:"view"
							}
				] 
	        }; 
	var myConfigs = {
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage: 10
		})
	};

	var myDataTable = new YAHOO.widget.DataTable("userGroupDetails_datatable", 
	                myColumnDefs, myDataSource,myConfigs); 
}

function showGroupCandidateDetails(groupId)
{
	return;
	var urlStr = "getUserGroupsCandidatesAction.action?groupId="+groupId+"&windowTask=getUserGroupsCandidates";
	var browser1 = window.open(urlStr,"userGroupsCandidatesPopup","scrollbars=yes,height=600,width=1300,left=200,top=200");	
	browser1.focus();
}
	

function redirectToNewWindowForEditingPoliticalChanges(type,id)
{		
	var politicalChangesWindow = window.open("politicalChangesAction.action?type="+type+"&localPoliticalChangeId="+id,"politicalChangesWindow","scrollbars=yes,height=600,width=600,left=200,top=200");
    politicalChangesWindow.focus();	
}

function getAllPoliticalChangesForTheUser()
{						
	var jsObj= 
	{				  			
		task: "getAllPoliticalChangesForTheUser"		
	};
	incrementHidden();
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPoliticalChangesForTheUserAjaxAction.action?"+param+"&hidden="+hidden;
	
	callAjax(param,jsObj,url);			
}


function buildDataTableForLocalPoliticalChanges(results)
{
		
	assignTolocalPoliticalChangesDataArray = new Array();

	for(var i in results)
	{		
		var sourceOfInformation;
		if(results[i].sourceOfInformation == 'External Person'){			
			sourceOfInformation = '<A href="javascript:{}" title="Click To View Details" onclick="getExternalPersonDetails('+results[i].localPoliticalChangeId+')">'+results[i].sourceOfInformation+'</A>';  //\''+results[i].sourceOfInformation+'\'
		}else{	
		    sourceOfInformation = results[i].sourceOfInformation;
		}
		
			var localPoliticalChangesObj=		
			 {		
					title:results[i].title,
					description:results[i].description,
					occuredDate:results[i].occuredDate,
					party:results[i].partyName,
					effectedRange :results[i].range,
					effectedLocation : results[i].locationName,
					sourceOfInformation : sourceOfInformation,
					editDetails: '<center><A href="javascript:{}" title="Click To Edit Political change" onclick="redirectToNewWindowForEditingPoliticalChanges(\'Edit\','+results[i].localPoliticalChangeId+')"><img src="images/icons/edit.png" style="text-decoration:none;border:0px;"></A></center>',
					deleteDetails: '<center><A href="javascript:{}" title="Click To Delte Political change"  onclick="deleteDetails('+results[i].localPoliticalChangeId+')"><img src="images/icons/delete.png" style="text-decoration:none;border:0px;"></A></center>'  
			 };
		
			assignTolocalPoliticalChangesDataArray.push(localPoliticalChangesObj);
			localPoliticalChanges = assignTolocalPoliticalChangesDataArray;
	}

	
	var resultsDataSourceForPoliticalChanges = new YAHOO.util.DataSource(localPoliticalChanges);
	resultsDataSourceForPoliticalChanges.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSourceForPoliticalChanges.responseSchema = {
		fields : [ {
			key : "title"
		}, {
			key : "description"
		}, {
			key : "occuredDate"
		}, {
			key : "party"
		}, {
			key : "effectedRange"
		}, {
			key : "effectedLocation"
		}, {
			key : "sourceOfInformation"
		}, {   
			key : "editDetails"
		}, {
			key : "deleteDetails"
		}]
	};

	var resultsColumnDefsForPoliticalChanges = [ {
		key : "title",
		label : "Title",
		sortable : true
	}, {
		key : "description",
		label : "Description",
		sortable : true
	}, {
		key : "occuredDate",
		label : "Occured Date",
		sortable : true
	}, {
		key : "party",
		label : "Party",
		sortable : true
	}, {
		key : "effectedRange",
		label : "Effected Range",
		sortable : true
	}, {
		key : "effectedLocation",
		label : "Effected Location",
		sortable : true
	}, {
		key : "sourceOfInformation",
		label : "Information Source",
		sortable : true
	}, {
		key : "editDetails",
		label : "Edit"		
	}, {
		key : "deleteDetails",
		label : "Delete"		
	} ];

					
	myDataTableForParty = new YAHOO.widget.DataTable("political_changes_data_table",resultsColumnDefsForPoliticalChanges, resultsDataSourceForPoliticalChanges);

}

function getExternalPersonDetails(politicalChangeId)
{
		 var jsObj= 
		 {			
				 politicalChangeId : politicalChangeId,	  			
				 task: "getExternalPersonDetails"		
		 };
		 
		 var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "getExternalPersonDetailsAjaxAction.action?"+param;
	
		 callAjax(param,jsObj,url);	
}


function buildExternalPersonDetailsPopUp(results)
{
	
	var externalPersonDataDiv = '';
	externalPersonDataDiv+='<div id="extenalPersonsDetailsDiv" align="left">';
	externalPersonDataDiv+='<table>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Name :</b></td>';
	externalPersonDataDiv+='		<td>'+results.name+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Mobile :</b></td>';
	externalPersonDataDiv+='		<td>'+results.mobile+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Telephone No :</b></td>';
	externalPersonDataDiv+='		<td>'+results.telephoneNo+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Email :</b></td>';
	externalPersonDataDiv+='		<td>'+results.email+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Address :</b></td>';
	externalPersonDataDiv+='		<td>'+results.address+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='</table>';
	externalPersonDataDiv+='</div>';

	externalPersonDetailsPanel = new YAHOO.widget.Dialog("localPoliticalChangesRegistration",
			{ 
			  width : "300px", 		
              fixedcenter : false, 
              visible : true,  
              constraintoviewport : true, 
			  iframe :true,
			  modal :true,
			  hideaftersubmit:true,
			  close:true,
			  x:400,
			  y:300,
			   buttons : [ { text:"Ok", handler: externalPersonPopupSubmit, isDefault:true}, 
					  { text:"Cancel", handler: externalPersonPopupCancel}]			 
             } );
	externalPersonDetailsPanel.setHeader("External Person Details");
	externalPersonDetailsPanel.setBody(externalPersonDataDiv);
	externalPersonDetailsPanel.render();	
}

function externalPersonPopupSubmit(){
	externalPersonDetailsPanel.hide();
}

function externalPersonPopupCancel()
{
	this.hide();
}



function incrementHidden()
{
	hidden++;
}

function deleteDetails(politicalChangeId){
	var jsObj= 
	{		
		politicalChangeId :politicalChangeId,		  			
		task: "deltePoliticalChange"		
	};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);

	incrementHidden();

	var url = "deltePoliticalChangeAjaxAction.action?"+param+"&hidden="+hidden;
	
	callAjax(param,jsObj,url);
}

function displayDateTextBox(type, args, obj,elementId) {

	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var txtDate1;
	if(elementId==1){
		txtDate1 = document.getElementById("identifiedFromText");	
	}else if(elementId==2){
		txtDate1 = document.getElementById("reportedFromText");
	}
	
	txtDate1.value = day + "/" + month + "/" + year;
}
function incrementHidden()
{
	hidden++;
}

function initializeConstituencyManagement() 
{
	getTodayDateTime();
	buildPoliticalChanges();
	getProblemsStatusCountByAccessType();
	getInfluencingPeopleInAConstituency();
	getAllPoliticalChangesForTheUser();
	getLocalUserGroups();
}