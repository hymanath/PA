var todayDate = new Date().getDate() + "/" + (new Date().getMonth() + 1) + "/" + new Date().getFullYear();
var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();
var minDate;
var resultsGlobal; 


var problemMgmtObj = {
	problemsStatusArr : []
};
var mobileNumbersArray = new Array();
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
				+ results.problemsCountByStatus[i].status + '\')">'
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
	problemStats_bodyElContent += '								<TD>' + results.problemsCountByStatus[i].count + '</TD>';
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
	problems_OptionsContent += '			<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '		<TH valign="top">To:</TH>';
	problems_OptionsContent += '		<TD><input type="text" id="tillDateText" value="' + todayDate + '" name="tillDateText" size="20" readonly="readonly" class="textBoxStyle"/>';
	problems_OptionsContent += '			<DIV class="yui-skin-sam"><DIV id="till_Div" class="tinyDateCal"></DIV></DIV>';
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '		<TD valign="top">';
	problems_OptionsContent += '			<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal1()"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
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
	problems_OptionsContent += '<p style="text-align:right;"> <A href="problemManagementReportAction.action" class="linkButton" target="_blank"> Click For Advanced Search </A></DIV></p>';
	problems_OptionsContent += '</div>';
	problems_OptionsContent += '</div>';
	problems_OptionsContent += '</td>';
	problems_OptionsContent += '</tr>';
	problems_OptionsContent += '<tr>';
	problems_OptionsContent += '<td colspan="2">';
	problems_OptionsContent += '<DIV class="yui-skin-sam">';
	// problems_OptionsContent+='<div
	// class="widgetHeaders">ProblemDetails</div>';
	problems_OptionsContent += '<DIV id="problemsDetailsDTDiv"></DIV>';
	problems_OptionsContent += '</DIV></td>';
	problems_OptionsContent += '</tr>';
	problems_OptionsContent += '</table>';
	problems_OptionsEl.innerHTML = problems_OptionsContent;
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
	
	var elmt = document.getElementById("problemsDetailsDTDiv");

	if (!elmt)
		return;

	if(results.length != 0)
	{
		elmt.innerHTML = '';
		var probDTColumnDefs = [ {
			key : "problemLocationId",
			hidden : true
		}, {
			key : "problem",
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
			label : localizationObj.HAMLET,
			sortable : true
		}, {
			key : "problemSourceScope",
			label : localizationObj.source,
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
			fields : [ "problemLocationId", "problem", "description",
					"existingFrom", "hamlet", "problemSourceScope",
					"problemAmdProblemSourceId", "status" ]
		};
	
		if (results.length > 10) {
			var myConfigs = {
				paginator : new YAHOO.widget.Paginator( {
					rowsPerPage : 10
				}),
				caption : "Recent Problems"
			};
		}
	
		var probDataTable = new YAHOO.widget.DataTable("problemsDetailsDTDiv",
				probDTColumnDefs, probDTDataSource, myConfigs);
	}
	else{
		elmt.innerHTML = '';
		elmt.innerHTML = '<SPAN style="color:green;font-weight:bold;">Zero problems matched this selection criteria</SPAN>';
		
	}
}

function buildInfluencingPeopleDT(results) {
	resultsGlobal = results;
	var ipDTColumnDefs = [ {
		key : "influencingPeopleId",
		hidden: true
	},	{
		key : "select",
		label : localizationObj.select,
		formatter : "checkbox"
	}, {
		key : "personName",
		label : localizationObj.name,
		sortable : true
	}, {
		key : "contactNumber",
		label : localizationObj.contactnbr
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
	} ];

	var ipDTDataSource = new YAHOO.util.DataSource(results);
	ipDTDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	ipDTDataSource.responseSchema = {
		fields : ["influencingPeopleId","personName", "contactNumber", "party", "localArea",
				"influencingRange" ]
	};

	if (results.length > 10) {
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
		fixedcenter : true,
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
		label : localizationObj.HAMLET,
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

function showSentSmsConfirmation(jsObj)
{
	var numbersArr = jsObj.numbers;
	var members = numbersArr.length; 
	var smsConfirmationEl = document.getElementById("smsConfirmation");
	var smsBlockAlertEl = document.getElementById("smsBlockAlert");
	var smsTextEl = document.getElementById("smsText");
	smsConfirmationEl.innerHTML = "SMS sent succesfully to "+members+" members";
	smsBlockAlertEl.innerHTML = '';
	smsTextEl.value = '';
	buildInfluencingPeopleDT(resultsGlobal);
}

function initializeConstituencyManagement() {
	getTodayDateTime();
	buildPoliticalChanges();
	getProblemsStatusCountByAccessType();
	getInfluencingPeopleInAConstituency();
}
