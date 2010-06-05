var todayDate = new Date().getDate() + "/" + (new Date().getMonth() + 1) + "/"
		+ new Date().getFullYear();
var addPoliticalChangesPanel,elementId='',externalPersonDetailsPanel;
var todayDate = new Date().getDate() + "/" + (new Date().getMonth() + 1) + "/" + new Date().getFullYear();
var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();
var minDate;
var resultsGlobal; 

var problemMgmtObj = {
	problemsStatusArr : [],
	initialProblems:[]
};
var mobileNumbersArray = new Array();
var informationSourcesObj = {
		sourceArr : []
};
var staticParties = {
		  staticPartiesDataList : []
}		
var requestType,localPoliticalChangeId,localPoliticalChanges,errorFlag=0;
var externalPersonname,externalPersonmobile,externalPersontelephoneno,externalPersonemail,externalPersonaddress;
var otherDetailsForPoliticalChanges;


	function getExternalPersonDetailsForEdit(politicalChangeId){
		var jsObj= 
		{			
			politicalChangeId : politicalChangeId,	  			
			task: "getExternalPersonDetailsForEdit"		
		};
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getExternalPersonDetailsAjaxAction.action?"+param;
		
		callAjax(param,jsObj,url);	
	}

	 function getExternalPersonDetails(politicalChangeId){
  var jsObj= 
	{			
		politicalChangeId : politicalChangeId,	  			
		task: "getExternalPersonDetails"		
	};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getExternalPersonDetailsAjaxAction.action?"+param;
	
	callAjax(param,jsObj,url);	
	 }
	 
	 
function getAllPoliticalChangesForTheUser()
{						
	var jsObj= 
	{				  			
		task: "getAllPoliticalChangesForTheUser"		
	};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPoliticalChangesForTheUserAjaxAction.action?"+param;
	
	callAjax(param,jsObj,url);			
}


function getAllStaticParties()
{
				
	var jsObj= 
	{				  			
		task: "getAllStaticParties"		
	};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllStaticPartiesAjaxAction.action?"+param;
	
	callAjax(param,jsObj,url);			
}

function deleteDetails(politicalChangeId){
	var jsObj= 
	{		
		politicalChangeId :politicalChangeId,		  			
		task: "deltePoliticalChange"		
	};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deltePoliticalChangeAjaxAction.action?"+param;
	
	callAjax(param,jsObj,url);
}

function editDetails(politicalChangeId,title,description,date,identifiedDate,partyName,sourceOfInformation){

	buildLocalPoliticalChangesRegistration("edit");
	localPoliticalChangeId = politicalChangeId;		
	document.getElementById("titleTextField").value = title;
	document.getElementById("descriptionTextBox").value = description;
	document.getElementById("reportedFromText").value = date;			
	document.getElementById("identifiedFromText").value = identifiedDate;
	var partySelect = document.getElementById("selectedPartyBox");
	for (var i=0; i<partySelect.options.length; ++i){ 
		if (staticParties.staticPartiesDataList[i].value==partyName) 
			partySelect.options[i].selected = true;				
	}

	var userSelect = document.getElementById("userTypeSelectBox");
	for (var i=0; i<userSelect.options.length; ++i){ 
		if (informationSourcesObj.sourceArr[i].value==sourceOfInformation) 
			userSelect.options[i].selected = true;				
	}
	
	if(externalPerson==sourceOfInformation){
		getExternalPersonDetailsForEdit(politicalChangeId);
		getOtherPersonDetails(sourceOfInformation);
		document.getElementById("externalPersonName").value = externalPersonname;
		document.getElementById("externalPersonMobile").value = externalPersonmobile;
		document.getElementById("externalPersonTelephoneNo").value = externalPersontelephoneno;
		document.getElementById("externalPersonEmail").value = externalPersonemail;
		document.getElementById("externalPersonAddress").value = externalPersonaddress;				
	}			
}

function validateDataForPoliticalChange()
{
	var titleTextFieldData,descriptionTextBoxData,reportedFromTextData,identifiedFromTextData;
	var externalPersonNameData,externalPersonMobileData;
	
	titleTextFieldData	= document.getElementById("titleTextField").value;
	reportedFromTextData = document.getElementById("reportedFromText").value;
	identifiedFromTextData = document.getElementById("identifiedFromText").value;
	
	var errorMsg = document.getElementById("errorMsgDiv");
	var errors = '';
	errors+='<table style="color:red">';
	if(titleTextFieldData==""){
		errors+='	<tr>';
		errors+='		<td> Please Enter Title	</td>';
		errors+='	<tr>';
		errorFlag = 1;
	}	
	if(reportedFromTextData==""){
		errors+='	<tr>';
		errors+='		<td> Please Select Reported Date	</td>';
		errors+='	<tr>';
		errorFlag = 1;
	}
	if(identifiedFromTextData==""){
		errors+='	<tr>';
		errors+='		<td> Please Select Identified Date </td>';
		errors+='	<tr>';
		errorFlag = 1;
	}
	if(otherDetailsForPoliticalChanges==1){
		externalPersonNameData = document.getElementById("externalPersonName").value;
		externalPersonMobileData = document.getElementById("externalPersonMobile").value;
		
		if(externalPersonNameData==""){
			errors+='	<tr>';
			errors+='		<td> Please Enter Name</td>';
			errors+='	<tr>';
			errorFlag = 1;
		}
		if(externalPersonMobileData==""){
			errors+='	<tr>';
			errors+='		<td> Please Enter Mobile Number</td>';
			errors+='	<tr>';
			errorFlag = 1;
		}
	}
	var titleTextFieldData,descriptionTextBoxData,reportedFromTextData,identifiedFromTextData;
	var externalPersonNameData,externalPersonMobileData;
	if((titleTextFieldData=="")||(descriptionTextBoxData=="")||(reportedFromTextData=="")||(identifiedFromTextData=="")){
		if(otherDetailsForPoliticalChanges==1){
			if((externalPersonNameData=="")||(externalPersonMobileData=="")){
				errorFlag = 1;
			}
		}		
	}else{
		errorFlag = 0;
	}
	alert(errorFlag);
	errors+='</table>';
	errorMsg.innerHTML = errors; 
}
function handleCreateGroupSubmit()
{		
	validateDataForPoliticalChange();
	if(errorFlag==0){
		var titleTextFieldData	= document.getElementById("titleTextField").value;
		var descriptionTextBoxData	= document.getElementById("descriptionTextBox").value;
		var reportedFromTextData = document.getElementById("reportedFromText").value;
		var identifiedFromTextData = document.getElementById("identifiedFromText").value;
		var userTypeSelectBoxData = document.getElementById("userTypeSelectBox").value;
		var selectedPartyBoxData = document.getElementById("selectedPartyBox").value;
		
		var externalPersonNameData="",externalPersonMobileData="", externalPersonTelephoneNoData="";
		var externalPersonEmailData="",externalPersonAddressData="";
		
		if(otherDetailsForPoliticalChanges==1){
			externalPersonNameData = document.getElementById("externalPersonName").value;
			externalPersonMobileData = document.getElementById("externalPersonMobile").value;
			externalPersonTelephoneNoData = document.getElementById("externalPersonTelephoneNo").value;
			externalPersonEmailData = document.getElementById("externalPersonEmail").value;
			externalPersonAddressData = document.getElementById("externalPersonAddress").value;
		}	
		addPoliticalChangesPanel.hide();
		var jsObj= 
		{	
			saveType:requestType,	
			localPoliticalChangeId : localPoliticalChangeId,
			titleTextFieldData : titleTextFieldData,		
			descriptionTextBoxData   : descriptionTextBoxData,		
			identifiedFromTextData : identifiedFromTextData,
			reportedFromTextData : reportedFromTextData,
			selectedPartyBoxData : selectedPartyBoxData, 
			userTypeSelectBoxData	: userTypeSelectBoxData,
			externalPersonNameData : externalPersonNameData,
			externalPersonMobileData : externalPersonMobileData,
			externalPersonTelephoneNoData : externalPersonTelephoneNoData,
			externalPersonEmailData : externalPersonEmailData,
			externalPersonAddressData : externalPersonAddressData,
			task: "saveDataForLocalPoliticalChanges"		
		};	
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveDataForLocalPoliticalChangesAjaxAction.action?"+param;	
		callAjax(param,jsObj,url);	
	}else{
		
	}		
}

function getPoliticalChangesInformationSources()
{
	var jsObj= 
	{			
		task: "getPoliticalChangesInformationSources"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getInformationSources.action?"+param;
	callAjax(param,jsObj,url);
}

function setDataToInformationSourcesObject(results)
{
	
	for(var i in results){
		var ob={
				id:results[i].id,
				value:results[i].name
			};
		informationSourcesObj.sourceArr.push(ob);	
	}	
	getAllStaticParties();
}

function setAllStaticParties(results)
{
	for(var i in results){
		var ob={
				id:results[i].id,
				value:results[i].name
			};
		staticParties.staticPartiesDataList.push(ob);	
	}	
}

function getExternalPersonDetailsToSetData(results){

	externalPersonname = results.name ;
	externalPersonmobile = results.mobile ;
	externalPersontelephoneno = results.telephoneNo ;
	externalPersonemail = results.email ;
	externalPersonaddress = results.address ;
	
}
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
	problems_OptionsContent += '<p class="widgetDescPara"> <font style="color:#4B74C6">Problem Search Criteria </font>enables the user to saerah for a problem or set of problems with given date range.Here the user has to provide the start date,end date and also the status of problem to view the set of problems.</p>';
	problems_OptionsContent += '<P class="widgetDescPara">Select Dates to view problems between any two dates</P>';
	problems_OptionsContent += '<DIV id="alertMessageDiv" class="errorMessage"></DIV>';
	problems_OptionsContent += '<TABLE cellspacing="5">';
	problems_OptionsContent += '	<TR>';
	problems_OptionsContent += '		<TH valign="top">View Problems From:</TH>';
	problems_OptionsContent += '		<TD><input type="text" id="existingFromText" style="margin-top:0px;" name="existingFromText" size="20"/>';
	problems_OptionsContent += '			<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" class="tinyDateCal"></DIV></DIV></TD>';
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '		<TD valign="top">';
	problems_OptionsContent += '			<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	problems_OptionsContent += '		</TD>';
	problems_OptionsContent += '		<TH valign="top">To:</TH>';
	problems_OptionsContent += '		<TD><input type="text" id="tillDateText" value="' + todayDate + '" name="tillDateText" size="20" onfocus="showDateCal1()" class="textBoxStyle"/>';
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


function showDateCalendar(eleId) {
	var id ;
	elementId = eleId;
	
	if(eleId==1){		
		id = document.getElementById("identifiedFromText_Div");
	}else if(eleId==2){
		id = document.getElementById("reportedFromText_Div");
	}
	
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
		title : "Choose a date:",
		close : true
	});
	dateCalendar.selectEvent.subscribe(displayDateTextBox, dateCalendar, true,elementId);
	dateCalendar.render();
	dateCalendar.show();
}

function displayDateTextBox(type, args, obj) {

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

function getOtherPersonDetails(name){
	if(name==externalPerson){
		otherDetailsForPoliticalChanges = 1;
		var externalPersonsDiv = document.getElementById("otherSourcesInformationDiv");
		var detailsDiv = '';
		detailsDiv+='<table>';	
		detailsDiv+='	<tr>';	
		detailsDiv+='		<td> * Name</td>';
		detailsDiv+='		<td><input type="text" id="externalPersonName" class="politicalChangesFieldBoxesWidth"></input></td>';
		detailsDiv+='	</tr>';		
		detailsDiv+='	<tr>';
		detailsDiv+='		<td> * Mobile</td>';
		detailsDiv+='		<td><input type="text" id="externalPersonMobile" class="politicalChangesFieldBoxesWidth"></input></td>';
		detailsDiv+='	</tr>';		
		detailsDiv+='	<tr>';
		detailsDiv+='		<td>TelephoneNo</td>';
		detailsDiv+='		<td><input type="text" id="externalPersonTelephoneNo" class="politicalChangesFieldBoxesWidth"></input></td>';
		detailsDiv+='	</tr>';
		detailsDiv+='	<tr>';
		detailsDiv+='		<td>Email</td>';
		detailsDiv+='		<td><input type="text" id="externalPersonEmail" class="politicalChangesFieldBoxesWidth"></input></td>';
		detailsDiv+='	</tr>';		
		detailsDiv+='	<tr>';
		detailsDiv+='		<td>Address</td>';
		detailsDiv+='		<td><input type="text" id="externalPersonAddress" class="politicalChangesFieldBoxesWidth"></input></td>';
		detailsDiv+='	</tr>';		
		detailsDiv+='	</tr>';
		detailsDiv+='</table>';
		detailsDiv+='';
		externalPersonsDiv.innerHTML = detailsDiv;
	}else{
		otherDetailsForPoliticalChanges = 0;
		var externalPersonsDiv = document.getElementById("otherSourcesInformationDiv");
		var detailsDiv = '';
		externalPersonsDiv.innerHTML = detailsDiv;
	}
}
function buildLocalPoliticalChangesRegistration(type){
	requestType = type;
	if(type="new"){
		localPoliticalChangeId = 0;
	}
	var identifyDate = 1;
	var reportedDate = 2;
	var registrationDivContent='';
	registrationDivContent+='<div id="mainPoliticalDiv" align="left">';
	registrationDivContent+='<table>';	
	registrationDivContent+='	<tr>';
	registrationDivContent+='		<td><DIV id="errorMsgDiv"></div></td>';
	registrationDivContent+='	</tr>';
	registrationDivContent+='	<tr>';
	registrationDivContent+='		<td><DIV id="politicalChangeTitleData">* Title : </div></td>';
	registrationDivContent+='		<td><input class="politicalChangesFieldBoxesWidth" id="titleTextField" type="text" width="300px"></input></td>';
	registrationDivContent+='	</tr>';
	registrationDivContent+='	<tr>';
	registrationDivContent+='		<td><DIV id="politicalChangeDescripionData">Description : </div></td>';
	registrationDivContent+='		<td><textarea id="descriptionTextBox" rows="2" cols="20"></textarea></td>';
	registrationDivContent+='	</tr>';
	registrationDivContent+='	<tr>';
	registrationDivContent+='		<td>* Identified Date</td>';
	registrationDivContent+='		<td><input type="text"  DISABLED class="politicalChangesFieldBoxesWidth" id="identifiedFromText" style="margin-top:0px;" name="identifiedFromText" size="20"/>';
	registrationDivContent+='			<div class="yui-skin-sam"><div id="identifiedFromText_Div" class="tinyDateCal"></div></div></td>';
	registrationDivContent+='		</td>';
	registrationDivContent+='		<td valign="top">';
	registrationDivContent+='			<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCalendar(\''+identifyDate+'\')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="politicalChangesCalendarImage" border="0"/></a>';
	registrationDivContent+='		</td>';
	registrationDivContent+='	</tr>';
	registrationDivContent+='	<tr>';		
	registrationDivContent+='		<td>* Reported Date</td>';
	registrationDivContent+='		<td><input type="text" DISABLED class="politicalChangesFieldBoxesWidth" id="reportedFromText" style="margin-top:0px;" name="reportedFromText" size="20"/>';
	registrationDivContent+='			<div class="yui-skin-sam"><div id="reportedFromText_Div" class="tinyDateCal"></div></div></td>';
	registrationDivContent+='		</td>';
	registrationDivContent+='		<td valign="top">';
	registrationDivContent+='			<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCalendar(\''+reportedDate+'\')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="politicalChangesCalendarImage" border="0"/></a>';
	registrationDivContent+='		</td>';
	registrationDivContent+='	</tr>';	
	
	registrationDivContent+='	<tr>';
	registrationDivContent+='		<td>';
	registrationDivContent+='			Effected Party';
	registrationDivContent+='		</td>';
	registrationDivContent+='		<td>';
	registrationDivContent+='			<select name="parties" id="selectedPartyBox">';
	for ( var i in staticParties.staticPartiesDataList) {
		registrationDivContent += '<option value='
				+ staticParties.staticPartiesDataList[i].id + '>'
				+ staticParties.staticPartiesDataList[i].value + '</option>';
	}	
	registrationDivContent+='			</select>';
	registrationDivContent+='		</td>';
	registrationDivContent+='	</tr>';
	registrationDivContent+='	<tr>';
	registrationDivContent+='		<td>Select Source</td>';
	registrationDivContent+='		<td>';
	registrationDivContent+='			<select id="userTypeSelectBox" class="politicalChangesFieldBoxesWidth" onchange="getOtherPersonDetails(this.options[this.selectedIndex].text)">';	
	for ( var i in informationSourcesObj.sourceArr) {
		registrationDivContent += '<option value='
				+ informationSourcesObj.sourceArr[i].id + '>'
				+ informationSourcesObj.sourceArr[i].value + '</option>';
	}	
	registrationDivContent+='			</select>';
	registrationDivContent+='		</td>';
	registrationDivContent+='	</tr>';
	registrationDivContent+='</table>';	
	
	registrationDivContent+='<div id="otherDetailsMainDiv" align="left">';
	registrationDivContent+='<table>';
	registrationDivContent+='		<tr>';
	registrationDivContent+='			<td>';
	registrationDivContent+='					<div id="otherSourcesInformationDiv"></div>';
	registrationDivContent+='			</td>';
	registrationDivContent+='		</tr>';
	registrationDivContent+='</table>';
	registrationDivContent+='</div>';
	
	registrationDivContent+='<table>';
	registrationDivContent+='		<tr>';
	registrationDivContent+='			<td>';
	registrationDivContent+='					* Fields are Manditory';
	registrationDivContent+='			</td>';
	registrationDivContent+='		</tr>';
	registrationDivContent+='</table>';
	registrationDivContent+='<div id="otherDetailsMainDiv" align="left">';
	registrationDivContent+='</div>';
	
	addPoliticalChangesPanel = new YAHOO.widget.Dialog("localPoliticalChangesRegistration",
			{ 
			  width : "400px", 		
              fixedcenter : false, 
              visible : true,  
              constraintoviewport : true, 
			  iframe :true,
			  modal :true,
			  hideaftersubmit:true,
			  close:true,
			  x:400,
			  y:300,				  
			  buttons : [ { text:"Save", handler: handleCreateGroupSubmit, isDefault:true}, 
                          { text:"Cancel", handler: handleCreateGroupCancel}]
             } );
	addPoliticalChangesPanel.setHeader("Add Political Changes");
	addPoliticalChangesPanel.setBody(registrationDivContent);
	addPoliticalChangesPanel.render();
}

function buildExternalPersonDetailsPopUp(results){

	var externalPersonDataDiv = '';
	externalPersonDataDiv+='<div id="extenalPersonsDetailsDiv" align="left">';
	externalPersonDataDiv+='<table>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Name :</b> &nbsp;&nbsp;'+results.name+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Mobile :</b> &nbsp;&nbsp;'+results.mobile+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Telephone No :</b> &nbsp;&nbsp;'+results.telephoneNo+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Email :</b> &nbsp;&nbsp;'+results.email+ '</td>';
	externalPersonDataDiv+='	</tr>';
	externalPersonDataDiv+='	<tr>';
	externalPersonDataDiv+='		<td><b> Address :</b> &nbsp;&nbsp;'+results.address+ '</td>';
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
			  buttons : [ { text:"Ok", handler: externalPersonCreateGroupSubmit, isDefault:true}, 
                          { text:"Cancel", handler: handleCreateGroupCancel}]
             } );
	externalPersonDetailsPanel.setHeader("External Person Details");
	externalPersonDetailsPanel.setBody(externalPersonDataDiv);
	externalPersonDetailsPanel.render();
	
}
function externalPersonCreateGroupSubmit(){
	externalPersonDetailsPanel.hide();
}
function buildDataTableForLocalPoliticalChanges(results)
{
	assignTolocalPoliticalChangesDataArray = new Array();
	for(var i in results)
	{		
		var sourceOfInformation;
		if(externalPerson==results[i].sourceOfInformation){			
			sourceOfInformation = '<A href="javascript:{}" title="Click To View Details" onclick="getExternalPersonDetails('+results[i].politicalChangeId+')">'+results[i].sourceOfInformation+'</A>';  //\''+results[i].sourceOfInformation+'\'
		}else{	
		    sourceOfInformation = results[i].sourceOfInformation;
		}
		var localPoliticalChangesObj=		
		 {		
				title:results[i].title,
				description:results[i].description,
				occuredDate:results[i].date,
				sourceOfInformation : sourceOfInformation,
				editDetails: '<A href="javascript:{}" title="Click To Edit Political change" onclick="editDetails('+results[i].politicalChangeId+',\''+results[i].title+'\',\''+results[i].description+'\',\''+results[i].date+'\',\''+results[i].identifiedDate+'\',\''+results[i].partyName+'\',\''+results[i].sourceOfInformation+'\')">Edit</A>',
				deleteDetails: '<A href="javascript:{}" title="Click To Delte Political change" onclick="deleteDetails('+results[i].politicalChangeId+')">Delete</A>',  
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
		key : "sourceOfInformation",
		label : "Information Source",
		sortable : true
	}, {
		key : "editDetails",
		label : "Edit",
		sortable : true
	}, {
		key : "deleteDetails",
		label : "Delete",
		sortable : true
	} ];

					
	myDataTableForParty = new YAHOO.widget.DataTable("political_changes_data_table",resultsColumnDefsForPoliticalChanges, resultsDataSourceForPoliticalChanges);

}



function handleCreateGroupCancel()
{
	this.hide();
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
	getPoliticalChangesInformationSources();
	getAllPoliticalChangesForTheUser();
}
