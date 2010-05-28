var todayDate=new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();

var problemMgmtObj={
		problemsStatusArr:[]
};
function getTodayDateTime()
{		
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

		currentTime = ((hour<=9) ? "0" + hour : hour) + ":" + min + ":" +  add;
		return todaysDateTime = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear()+" "+currentTime;		
}


function buildConstituencyLayout()
{	
	var constituencyMgmtPageLayout = new YAHOO.widget.Layout('constituencyMgmt_layout_main', { 
	height:980,
	units: [			
			{ 
				position: 'right', 
				width: 280,
				header:false,
				body: 'constituencyMgmt_layout_right',
				resize: false,
				gutter: '2px',
				collapse: false,
				scroll: true,						
				animate: true 
			}, 					 
			{ 
				position: 'center',						
				body: 'constituencyMgmt_layout_center',
				resize: false,
				gutter: '2px',
				collapse: true,
				scroll: true,						
				animate: true
			} 
		 ] 
		
	});
	constituencyMgmtPageLayout.render(); 
}

function buildPoliticalChanges()
{
	var elmt = document.getElementById("political_changes_data_body");

	if(!elmt)
		return;

	 
	var politicalChangesArr = 
		[ 
	        {desc:"Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!", date:new Date(2009, 9, 12), impactedParty:"Congress"}, 
			{desc:"Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!", date:new Date(2009, 9, 12), impactedParty:"Congress"}, 
			{desc:"Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!", date:new Date(2009, 9, 12), impactedParty:"Congress"}, 
			{desc:"Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!", date:new Date(2009, 9, 12), impactedParty:"Congress"},
			{desc:"Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!", date:new Date(2009, 9, 12), impactedParty:"Congress"},
			{desc:"Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!", date:new Date(2009, 9, 12), impactedParty:"Congress"},
			{desc:"Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!", date:new Date(2009, 9, 12), impactedParty:"Congress"},
			{desc:"Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!", date:new Date(2009, 9, 12), impactedParty:"Congress"}
	    ] ;
	var myColumnDefs = [ 
		{key:"desc", sortable:true, resizeable:true}, 
		{key:"date", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, resizeable:true}, 
		{key:"impactedParty", sortable:true, resizeable:true}		
	]; 

	var myDataSource = new YAHOO.util.DataSource(politicalChangesArr); 
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	myDataSource.responseSchema = { 
		fields: ["desc","date","impactedParty"] 
	};
	

	var myDataTable = new YAHOO.widget.DataTable("political_changes_data_body", 
			myColumnDefs, myDataSource); 
}

function showProblemsStatusCount(results)
{
	var problemStats_bodyEl = document.getElementById("problems_outline_Div");
	var chartName = results.problemsStatusChartName;
	var barChartName = results.lastTenDaysProblemsDetailsBarChartName;
	var problems_OptionsEl = document.getElementById("problems_Options");	
	var problemStats_bodyElContent = '';
	problemStats_bodyElContent+='<TABLE class="problemsStatsTable" cellspacing="10" cellpadding="10" border="2" width="80%">';
	problemStats_bodyElContent+='	<TR>';	
	problemStats_bodyElContent+='		<TD halign="center" valign="top" width="50%">';
	problemStats_bodyElContent+='			<TABLE border="0">';
	problemStats_bodyElContent+='				<TR>';
	problemStats_bodyElContent+='					<TD colspan="2"><DIV style="text-align:left;color:#663300;font-size:12px;font-weight:bold;text-decoration:underline;">Current Problems and their Status</DIV></TD>';
	problemStats_bodyElContent+='				</TR>';
	problemStats_bodyElContent+='				<TR>';
	problemStats_bodyElContent+='					<TD><IMG src="charts/'+chartName+'" border="0"/></TD>';
	problemStats_bodyElContent+='				</TR>';
	problemStats_bodyElContent+='				<TR>';
	problemStats_bodyElContent+='					<TD align="center" style="border:1px solid #000000;">';
	problemStats_bodyElContent+='						<TABLE class="problemsStatsTable" border="0" cellpadding="3" cellspacing="3">';
	problemStats_bodyElContent+='							<TR>';
	for(var i in results.problemsCountByStatus)
	{					
	if(results.problemsCountByStatus[i].status == 'FIXED')
	continue;
	problemStats_bodyElContent+='								<TD align="left">'+results.problemsCountByStatus[i].status+'</TD><TD align="left"><DIV class="'+results.problemsCountByStatus[i].status+'"></DIV></TD><TD align="left"><A href="javascript:{} onclick="">'+results.problemsCountByStatus[i].count+'</A></TD>';
	if(i!=0 && i%2 == 0)
	problemStats_bodyElContent+='</tr><tr>';
	}
	problemStats_bodyElContent+='							</TR>';
	problemStats_bodyElContent+='						</TABLE>';
	problemStats_bodyElContent+='					</TD>';
	problemStats_bodyElContent+='				</TR>';
	problemStats_bodyElContent+='				<TR>';
	problemStats_bodyElContent+='					<TD>';
	problemStats_bodyElContent+='						<TABLE class="probStatsCountTable">';	
	problemStats_bodyElContent+='							<TR>';
	problemStats_bodyElContent+='								<TH>Total Problems:</TH>';
	problemStats_bodyElContent+='								<TD>'+results.totalProblemsCount+'</TD>';
	problemStats_bodyElContent+='							</TR>';
	problemStats_bodyElContent+='							<TR>';
	problemStats_bodyElContent+='								<TH>Fixed Problems:</TH>';
	problemStats_bodyElContent+='								<TD>'+results.problemsCountByStatus[i].count+'</TD>';
	problemStats_bodyElContent+='							</TR>';
	problemStats_bodyElContent+='						</TABLE>';
	problemStats_bodyElContent+='					</TD>';
	problemStats_bodyElContent+='				</TR>';
	problemStats_bodyElContent+='			</TABLE>';	
	problemStats_bodyElContent+='		</TD>';
	problemStats_bodyElContent+='		<TD halign="center" valign="top" width="50%">';
	problemStats_bodyElContent+='			<TABLE>';
	problemStats_bodyElContent+='				<TR>';
	problemStats_bodyElContent+='					<TD><DIV DIV style="text-align:left;color:#663300;font-size:12px;font-weight:bold;text-decoration:underline;">Problems in last 10 Days, last 30 Days and their Status</DIV></TD>';				
	problemStats_bodyElContent+='				</TR>';
	problemStats_bodyElContent+='				<TR>';
	problemStats_bodyElContent+='					<TD><IMG src="charts/'+barChartName+'" height="200" border="0" /></TD>';				
	problemStats_bodyElContent+='				</TR>';
	problemStats_bodyElContent+='			</TABLE>';
	problemStats_bodyElContent+='			<DIV style="border:0px solid;"><CENTER>';
	problemStats_bodyElContent+='				<TABLE class="tableBorder" cellpadding="3" cellspacing="3">';
	problemStats_bodyElContent+='					<TR>';
	problemStats_bodyElContent+='						<TD align="left">NEW</TD>';
	problemStats_bodyElContent+='						<TD align="left"><DIV class="NEW" style="background-color:red"></DIV></TD>';
	problemStats_bodyElContent+='						<TD align="left">FIXED</TD>';
	problemStats_bodyElContent+='						<TD align="left"><DIV class="FIXED" style="background-color:#7EFF28"></DIV></TD>';
	problemStats_bodyElContent+='					</TR>';
	problemStats_bodyElContent+='				</TABLE></CENTER>';	
	problemStats_bodyElContent+='			<TABLE class="probStatsCountTable">';
	problemStats_bodyElContent+='				<TR>';
	problemStats_bodyElContent+='					<TH>Problems Posted In Last 10 Days:</TH>';
	problemStats_bodyElContent+='					<TD>'+results.problemsPostedInLastTenDays+'</TD>';
	problemStats_bodyElContent+='					<TH>Problems Fixed In Last 10 Days:</TH>';
	problemStats_bodyElContent+='					<TD>'+results.problemsSolvedInLastTenDays+'</TD>';
	problemStats_bodyElContent+='				</TR>';
	problemStats_bodyElContent+='				<TR>';
	problemStats_bodyElContent+='					<TH>Problems Posted In Last 30 Days:</TH>';
	problemStats_bodyElContent+='					<TD>'+results.problemsPostedInLastThirtyDays+'</TD>';
	problemStats_bodyElContent+='					<TH>Problems Fixed In Last 30 Days:</TH>';
	problemStats_bodyElContent+='					<TD>'+results.problemsSolvedInLastThirtyDays+'</TD>';
	problemStats_bodyElContent+='				</TR>';	
	problemStats_bodyElContent+='			</DIV>';
	problemStats_bodyElContent+='		</TD>';
	problemStats_bodyElContent+='</TR>';
	problemStats_bodyElContent+='</TABLE>';	
	problemStats_bodyEl.innerHTML = problemStats_bodyElContent;
	
	var problems_OptionsContent = '';
	problems_OptionsContent+='<P>Select Dates to view problems between any two dates</P>';
	problems_OptionsContent+='<TABLE cellspacing="5">';
	problems_OptionsContent+='	<TR>';
	problems_OptionsContent+='		<TH valign="top">View Problems From:</TH>';
	problems_OptionsContent+='		<TD valign="top">';
	problems_OptionsContent+='			<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A></TD><TD><input type="text" id="existingFromText" style="margin-top:0px;" name="existingFromText" size="20"/>';
	problems_OptionsContent+='			<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" class="tinyDateCal"></DIV></DIV></TD>';
	problems_OptionsContent+='		</TD>';
	problems_OptionsContent+='		<TH valign="top">To:</TH>';
	problems_OptionsContent+='		<TD valign="top">';
	problems_OptionsContent+='			<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal1()"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A></TD><TD><input type="text" id="tillDateText" value="'+todayDate+'" name="tillDateText" size="20" onfocus="showDateCal1() class="textBoxStyle"/>';
	problems_OptionsContent+='			<DIV class="yui-skin-sam"><DIV id="till_Div" class="tinyDateCal"></DIV></DIV>';
	problems_OptionsContent+='		</TD>';
	problems_OptionsContent+='		<TH valign="top">Problem Status:</TH>';							
	problems_OptionsContent+='		<TD valign="top">';
	problems_OptionsContent+='			<SELECT id="selectStatus" theme="simple"  name="selectStatus" list="statusList" listKey="id" listValue="name" onchange="getProblemDetailsInSelectedDates(this.options[this.selectedIndex].value)"/>';
	for(var i in problemMgmtObj.problemsStatusArr)
	{
		problems_OptionsContent+='<option value='+problemMgmtObj.problemsStatusArr[i].id+'>'+problemMgmtObj.problemsStatusArr[i].value+'</option>';		
	}
	problems_OptionsContent+='		</TD>';
	problems_OptionsContent+='	</TR>';		
	problems_OptionsContent+='</TABLE>';
	problems_OptionsEl.innerHTML = problems_OptionsContent; 
}

function showDateCal()
{
	var id = document.getElementById("existingFromText_Div"); 
	if(dateCalendar)
		dateCalendar.destroy();
	
	var navConfig = { 
      strings : { 
          month: "Choose Month", 
          year: "Enter Year", 
          submit: "OK", 
          cancel: "Cancel", 
          invalidYear: "Please enter a valid year" 
      }, 
      monthFormat: YAHOO.widget.Calendar.SHORT, 
      initialFocus: "year" 
}; 

	var dateCalendar = new YAHOO.widget.Calendar(id, {navigator:navConfig, title:"Choose a date:", close:true }); 
	dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true); 		
	dateCalendar.render(); 
	dateCalendar.show();	
}
function displayDateText(type,args,obj)
{			
	var dates = args[0]; 
	var date = dates[0]; 
	var year = date[0], month = date[1], day = date[2]; 

	var txtDate1 = document.getElementById("existingFromText"); 
	txtDate1.value = day + "/" + month + "/" + year; 
}

function showDateCal1()
{
	var id = document.getElementById("till_Div"); 
	if(dateCalendar1)
		dateCalendar1.destroy();
	
	var navConfig = { 
      strings : { 
          month: "Choose Month", 
          year: "Enter Year", 
          submit: "OK", 
          cancel: "Cancel", 
          invalidYear: "Please enter a valid year" 
      }, 
      monthFormat: YAHOO.widget.Calendar.SHORT, 
      initialFocus: "year" 
}; 

	var dateCalendar1 = new YAHOO.widget.Calendar(id, {navigator:navConfig, title:"Choose a date:", close:true }); 
	dateCalendar1.selectEvent.subscribe(displayDateText1, dateCalendar1, true); 		
	dateCalendar1.render(); 
	dateCalendar1.show();	
}
function displayDateText1(type,args,obj)
{			
	var dates = args[0]; 
	var date = dates[0]; 
	var year = date[0], month = date[1], day = date[2]; 

	var txtDate1 = document.getElementById("tillDateText"); 
	txtDate1.value = day + "/" + month + "/" + year; 
}

function buildProblemsDetailsDT(results)
{
	var elmt = document.getElementById("problemsDetailsDTDiv");

	if(!elmt)
		return;
	
	var probDTColumnDefs = [ 
		{key:"problemLocationId", hidden:true},
		{key:"problem",label:localizationObj.problemLabel, sortable:true}, 
		{key:"description",label:localizationObj.description,},
		{key:"existingFrom", label:localizationObj.existingFrom},
		{key:"hamlet", label:localizationObj.HAMLET, sortable:true},
		{key:"problemSourceScope", label:localizationObj.source, sortable:true},
		{key:"problemAmdProblemSourceId", hidden:true},
		{key:"status", label:localizationObj.status, sortable:true}		
	]; 

	var probDTDataSource = new YAHOO.util.DataSource(results); 
	probDTDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	probDTDataSource.responseSchema = { 
		fields: ["problemLocationId","problem","description","existingFrom","hamlet","problemSourceScope","problemAmdProblemSourceId","status"] 
	};
	
	var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10			        
		    }),
		    caption:"Recent Problems" 
			};

	var probDataTable = new YAHOO.widget.DataTable("problemsDetailsDTDiv", 
			probDTColumnDefs, probDTDataSource,myConfigs); 
}

function buildInfluencingPeopleDT(results)
{
	var ipDTColumnDefs = [ 
	    {key:"select",label:localizationObj.select, formatter:"checkbox"},	    
		{key:"personName", label:localizationObj.name, sortable:true},		
		{key:"contactNumber",label:localizationObj.contactnbr},		
		{key:"party", label:localizationObj.party, sortable:true},
		{key:"localArea", label:localizationObj.location, sortable:true},
		{key:"influencingRange", label:localizationObj.inflScope, sortable:true}				
	]; 

	var ipDTDataSource = new YAHOO.util.DataSource(results); 
	ipDTDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	ipDTDataSource.responseSchema = { 
		fields: ["personName","contactNumber","party","localArea","influencingRange"] 
	};
	
	var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10			        
		    }),
		    caption:"Influencing People" 
			};

	var ipDataTable = new YAHOO.widget.DataTable("influencingPeopleDtDiv", 
			ipDTColumnDefs, ipDTDataSource,myConfigs);	
}
function initializeConstituencyManagement()
{
	getTodayDateTime();	
	buildPoliticalChanges();
	getProblemsStatusCountByAccessType();	
	getInfluencingPeopleInAConstituency();
}
