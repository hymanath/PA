var dtArray =  new Array();
var assemblyElectionType='Assembly';
var presentElectionYear='2009';

var districtsInfo = new Array();
var createGroupDialog;
function getMlaDetails()
{

buildResultsDataTable("mpsInfoDivBody",dtArray);

}

function buildResultsDataTable(id,dtSource)
{	

 var allianceResultsColumnDefs = [
							{key: "distName", label: "District", sortable:true},
							{key: "constName", label: "Constituency", sortable:true},		
							{key: "name", label: "Candidate", sortable:true},
							{key: "partyName", label: "Party", sortable:true},
							{key: "votesPercent", label:"Votes %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
							{key: "marginVotes", label: "Margin Votes", sortable:true},
							{key: "marginPercent", label:"Votes Margin %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
							{key: "completeResults", label: "Results"}
							];                	 	    

	var allianceResultsDataSource = new YAHOO.util.DataSource(dtSource); 
	allianceResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	allianceResultsDataSource.responseSchema = {		
			fields: ["distName","constName", "name", "partyName",{key: "votesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},"marginVotes",
								  {key: "marginPercent", parser:YAHOO.util.DataSourceBase.parseNumber},"completeResults"] 
			};

	allianceResultsDataTable = new YAHOO.widget.DataTable(id, allianceResultsColumnDefs, allianceResultsDataSource,null);
				
}


function buildBiElectionPageLayout()
{
	var biElectionPageLayout = new YAHOO.widget.Layout('biElectionPageLayout_main', { 
	height:600,
	units: [
			{ 
				position: 'right', 
				width: 250,
				header:false,
				body: 'biElectionPageLayout_right',
				resize: false,
				gutter: '0px',
				collapse: false,
				scroll: true,						
				animate: true 
			}, 					 
			{ 
				position: 'center',						
				body: 'biElectionPageLayout_center',
				resize: false,
				gutter: '0px',
				collapse: true,
				scroll: true,						
				animate: true
			} 
		 ] 
		
	});
	biElectionPageLayout.render(); 
}

function buildMandalsVotingTrendz()
{
	var elmt = document.getElementById("boothResultsSelection");
	if(!elmt)
		return;

	var str='';
	str += '<div id="districtsInfoRadioElmtDiv">';
	str += '<div style="font-weight:bold;color:#100F0D;font-size:13px;">'+localizationObj.partyBoothResultsText+'</div>';
	str += '<div id="locationAlert" class="alert"></div>';
	str += '<table>';	
	str += '<tr>';
	str += '<th>';
	str += 'District ';
	str += '</th>';
	str += '<td>';
	for(var i in districtsInfo)
	{
		if(i == 0)
			str += '<input type="radio" checked="checked" name="districtRadio" value="'+districtsInfo[i].districtId+'" onclick="getConstituenciesInfo(this.value,\''+i+'\')"/> '+districtsInfo[i].districtName;
		else
			str += '<input type="radio" name="districtRadio" value="'+districtsInfo[i].districtId+'" onclick="getConstituenciesInfo(this.value,\''+i+'\')"/> '+districtsInfo[i].districtName;

	}
	str += '</td>';
	str += '</tr>';
	//str += '</table>';
	//str += '</div>';

	//str += '<div id="constituenciesInfoSelectElmtDiv">';
	//str += '<table border="1">';
	str += '<tr>';
	str += '<th>';
	str += 'Constituency ';
	str += '</th>';
	str += '<td>';
	str += '<div id="constSelectElmt"><select id="selectConst" class="selectWidth" onchange="selectConstOnchange()">';
	str += '<option value="0">Select Constituency</option>';
	for(var j in districtsInfo[0].constituencies)
	{
		str += '<option value="'+districtsInfo[0].constituencies[j].constId+'"> '+districtsInfo[0].constituencies[j].constName+' </option>';
	}	
	str += '</select></div>';
	str += '</td>';
	str += '</tr>';
	str += '<tr>';
	str += '<th>';
	str += 'Party ';
	str += '</th>';
	str += '<td>';
	str += '<div id="partySelectElmt"><select id="selectParty" class="selectWidth" onchange="openMandalBoothResultsForPartyWindow()">';
	for(var k in biElectionObj.staticParties)
	{
		str += '<option value="'+biElectionObj.staticParties[k].partyId+'"> '+biElectionObj.staticParties[k].partyName+' </option>';
	}	
	str += '</select></div>';
	str += '</td>';

	str += '</tr>';
	str += '</table>';
	
	str += '</div>';
	elmt.innerHTML = str;	
}
	
function setValuesForMandalVotingTrendz(value,text)
{
	var radioValue = '';
	var cursorImgElmt = document.getElementById('cursorImg');
	if(cursorImgElmt)
		cursorImgElmt.style.display = 'block';

	var elements = document.getElementsByTagName('input'); 

	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="radio" && elements[i].name=="districtRadio" && elements[i].checked==true)
			radioValue = elements[i].value;
	}
	
	getMandalVotingTrendz(radioValue,value,text);
}

function getConstituenciesInfo(distId,index)
{
	var obj = districtsInfo[index];

	var elmt = document.getElementById("constSelectElmt");
	var partyElmt = document.getElementById("selectParty");
	if(!elmt)
		return;

	var str = '';
	str += '<select class="selectWidth" id="selectConst" onchange="selectConstOnchange()">';
	str += '<option value="0">Select Constituency</option>';
	for(var j in obj.constituencies)
	{
		str += '<option value="'+obj.constituencies[j].constId+'"> '+obj.constituencies[j].constName+' </option>';
	}	
	str += '</select>';
	
	elmt.innerHTML = str;
	partyElmt.selectedIndex = '0';
	
}

function selectConstOnchange()
{
	var partyEl = document.getElementById("selectParty");
	partyEl.selectedIndex = '0';
}

function buildBiElectionDistricts()
{
	var elmt = document.getElementById("biElectionDistricts_body");
	if(!elmt)
		return;

	var str = '';
	str += '<div style="color:#282B03;font-size:12px;margin:8px;font-weight:bold;">Click the below constituencies to view Party Trendz</div>';
	str += '<table border="0" width="100%" id="distConstTable">';
	for(var i in districtsInfo)
	{
		str += '<tr>';
		str += '<td align="center"> <img height="5" width="7" src="images/icons/districtPage/listIcon.png"/></td>';
		str += '<td colspan="2">';
		str += '<span class="mandalNameSpan">';
		str += '<a href ="districtPageAction.action?districtId='+districtsInfo[i].districtId+'&districtName='+districtsInfo[i].districtName+'" class="districtAnc">'+districtsInfo[i].districtName+'- '+districtsInfo[i].constituencies.length+'</a>';
		str += '</span>';
		str += '</td>';		
		str += '</tr>';
		for(var j in districtsInfo[i].constituencies)
		{
			var info = districtsInfo[i].constituencies[j];
			str += '<tr>';
			str += '<td>';
			str += '</td>';
			str += '<td align="left">';
			str += '	<a href="javascript:{}" onclick="openVotingTrendzWindow(\''+districtsInfo[i].districtId+'\',\''+info.constId+'\',\''+info.constName+'\')" class="districtAncNew"> '+info.constName.toUpperCase()+ '</a>';
			str += '</td>';
			str += '<td align="left">';
			str += '	<a href="javascript:{}" onclick="openConstVotingTrendzWindow(\''+districtsInfo[i].districtId+'\',\''+info.constId+'\',\''+info.constName+'\')" class="districtAncNew1">Analyze</a>';			
			str += '</td>';
			str += '</tr>';
		}
	}
	str += '</table>';
	str += '<input type="button" style="margin:5px;width:200px;font-weight:bold;" class="button" onclick="open2010ElectionResultsWindow()" value="2010 Bye-Election Results Analysis"/>';
	str += '<div id="alignMessageDiv" style="margin-top:7px;">';
	str += '<table>';
	str += '<tr>';
	str += '<td valign="top"></td>';
	str += '<td valign="top" style="letter-spacing:1px;word-spacing:3px;"></td>';	
	str += '</tr>';
	str += '</table>';
	str += '</div>';
	
	elmt.innerHTML = str;
}


function showDetailedEnlargedChart(chartName)
{		
	var elmt = document.getElementById('enlargedDIV');
	var divChild = document.createElement('div');
	divChild.setAttribute('id','createGroupmDiv');

    var str='';
	str+='<img src="charts/'+chartName+'" />';
	divChild.innerHTML=str;
	elmt.appendChild(divChild);	
	if(createGroupDialog)
		createGroupDialog.destroy();
	createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
			{ 
		 
        	fixedcenter : false, 
        	visible : true,        	
        	iframe :true,
        	modal :true,
        	hideaftersubmit:true,
        	close:true,
        	x: 200,
        	y: 900
		  } );
	createGroupDialog.render();
}

function showDetailedChart(chartName)
{		
	var elmt = document.getElementById('enlargedGraphDIV');
	var divChild = document.createElement('div');
	divChild.setAttribute('id','createGroupmDiv');

    var str='';
	str+='<img src="charts/'+chartName+'" />';
	divChild.innerHTML=str;
	elmt.appendChild(divChild);	
	if(createGroupDialog)
		createGroupDialog.destroy();
	createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
			{ width : "850px", 		
              fixedcenter : false, 
              visible : true,  
              constraintoviewport : true, 
			  iframe :true,
			  modal :true,
			  hideaftersubmit:true,
			  close:true,
			  x:600,
			  y:800
             } );
	createGroupDialog.render();
}
function handleCreateGroupSubmit()
{
	createGroupDialog.hide();			
}

function handleCreateGroupCancel()
{
	this.cancel();
}

function buildMandalVotingTrendzData(jsObj,resultsData)
{
	
	var headElmt = document.getElementById("mandalVotingTrendzDataDiv_head");
	var bodyElmt = document.getElementById("mandalVotingTrendzData");
	var graphElmt = document.getElementById("mandalDetailsChart_body");
	var mandalsListElmt = document.getElementById("mandalsListInConstituency");

	var enlargedGraph = document.getElementById("mandalVotingDetailedGraph");
	
	var enlargedElmt='';
	enlargedElmt += '<input type="button" style="margin-bottom:-22px;margin-left:663px;margin-top:15px;"class="button" onclick="showDetailedChart(\''+resultsData.enlargedMandalWiseResultsChart+'\')" value="Detailed Chart">';
	enlargedElmt += '<div id="enlargedGraphDIV"></div>';
	enlargedGraph.innerHTML = enlargedElmt;

	var results = resultsData.biElectionResultsMainVO;
	
	if(!headElmt || !bodyElmt || !resultsData || !graphElmt || !mandalsListElmt)
		return;
	
	//Hiding busy cursor Image
	var cursorImgElmt = document.getElementById('cursorImg');
	if(cursorImgElmt)
		cursorImgElmt.style.display = 'none';

	// Rendering Graph Elmt in the respective div
	var graphStr = '';
	graphStr += '<img height="300" width="820" src="charts/'+resultsData.mandalWiseResultsChart+'"/>';
	graphStr += '<P style="font-family:verdana;font-size:12px;margin-top:25px;">'+localizationObj.desc1+' <font style="font-weight:bold;color:#4B74C6;">'+jsObj.constiName+'</font> '+localizationObj.desc2+'</P>';
	graphElmt.innerHTML = graphStr;

	//Rendering  mandals list in the constituency
	mandalStr = '';
	mandalStr += '<fieldset>';
	mandalStr += '<legend> Mandals In '+jsObj.constiName+' Constituency </legend>';
	mandalStr += '<table>';
	mandalStr += '<tr>';
	mandalStr += '<th> <u>Mandals </u>: </th>';
	for(var mandal in results[0].biElectionResultsVO[0].electionResultsForMandal)
	{
		var mandalData = results[0].biElectionResultsVO[0].electionResultsForMandal[mandal];
		mandalStr += '<td><div class="mandalNameDivClass"><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+mandalData.mandalId+'&MANDAL_NAME='+mandalData.mandalName+'"> '+mandalData.mandalName+'</a></div></td>';		
	}
	mandalStr += '</tr>';
	mandalStr += '</table>';
	mandalStr += '<div id="constiVotersDetails" style="margin-top:20px;">';
	mandalStr += '<div style="padding:10px;margin-bottom:20px;"> <u><b> Mandals Votes Share In Constituency :</b> </u> </div>';
	mandalStr += '<center><table width="85%" border="1" cellspacing="3">';
	mandalStr += '	<tr>';
	for(var chart in resultsData.constituencyVO.pieChartNames)
		mandalStr += '		<td align="center"><img src="charts/'+resultsData.constituencyVO.pieChartNames[chart]+'" border="0"></td>';	
	mandalStr += '	</tr>';
	mandalStr += '</table></center>';
	mandalStr += '</div>';
	mandalStr += '</fieldset>';
	
	mandalsListElmt.innerHTML = mandalStr;	

	//Rendering Mandal voting trendz data
	var str = '';
	str += '<table width="100%">';
	for(var i in results)
	{	
		for(var j in results[i].biElectionResultsVO)
		{
			
			var electionHeaderLength = (results[i].biElectionResultsVO[j].partysList.length*2)+2;
			var partyHeaderLength = results[i].biElectionResultsVO[j].partysList.length*2;
			str += '<tr>';
			str += '<td style="vertical-align:top;padding-bottom:20px;">';
			str += '<table width="100%" class="mandalResultsTable" border="1">';
			str += '<tr>';
			str += '<th colspan="'+electionHeaderLength+'" align="left">'+results[i].biElectionResultsVO[j].electionType+' - '+results[i].biElectionResultsVO[j].electionYear+'</th>';
			str += '</tr>';
			str += '<tr>';
			str += '<th rowspan="3">Mandal</th>';
			str += '<th rowspan="3">Constituency</th>';
			str += '<th colspan="'+partyHeaderLength+'" align="center">Party</th>';
			str += '</tr>';
			str += '<tr>';
			for(var p in results[i].biElectionResultsVO[j].partysList)
			{
				str += '<th colspan="2">'+results[i].biElectionResultsVO[j].partysList[p].name+'</th>';
			}
			str += '</tr>';
			str += '<tr>';
			for(var q in results[i].biElectionResultsVO[j].partysList)
			{
				str += '<th>V*</th><th>%</th>';
			}
			str += '</tr>';
			for(var k in results[i].biElectionResultsVO[j].electionResultsForMandal)
			{
				var info = results[i].biElectionResultsVO[j].electionResultsForMandal[k];
				str += '<tr>';
				if(info.partyElecResultsInConstituency.length == 0)
				{
					var cols = partyHeaderLength+1;
					str += '<th><A href="javascript:{}" title="Click to view results and voting trendz in '+info.mandalName+' mandal" class="viewAncs"  onclick="openwin('+info.mandalId+',\''+info.mandalName+'\',\''+results[i].biElectionResultsVO[j].electionType+'\','+results[i].biElectionResultsVO[j].electionYear+','+results[i].biElectionResultsVO[j].electionId+')">'+info.mandalName+'</A></th>';
					for(var colsno = 0;colsno < cols; colsno++)
						str += '<td> N/A </td>';	
				}
				else
				{
					str += '<th rowspan="'+info.partyElecResultsInConstituency.length+'"><A href="javascript:{}" title="Click to view results and voting trendz in '+info.mandalName+' mandal" class="viewAncs" onclick="openwin('+info.mandalId+',\''+info.mandalName+'\',\''+results[i].biElectionResultsVO[j].electionType+'\','+results[i].biElectionResultsVO[j].electionYear+','+results[i].biElectionResultsVO[j].electionId+')">'+info.mandalName+'</A></th>';				
					for(var l in info.partyElecResultsInConstituency)
					{
						str += '<th style="color:#73787E;width:150px;font-size:10px;">'+info.partyElecResultsInConstituency[l].constituencyName.toUpperCase()+'</th>';
										
						for(var m in info.partyElecResultsInConstituency[l].partyElecResults)
						{
							var data = info.partyElecResultsInConstituency[l].partyElecResults[m];	
							if(data.votesEarned == 0)
								str += '<td> N/A </td>';
							else
								str += '<td>'+data.votesEarned+'</td>';

							str += '<td>'+data.percentage+'</td>';					
						}
						
						str+='</tr><tr>';
					}
				}
				str += '</tr>';
			}			
			str += '</table>';
			str += '</td>';
			str += '</tr>';		
		}		
	}
	str += '</table>';

	bodyElmt.innerHTML = str;
}

function buildVotingTrendzDataTable(divID,arr,electionType)
{
	var elmt =document.getElementById(divID);
	if(!elmt)
		return;

	var str = '';
	str += '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border:1px solid #ADADAD;">';
	str += '<tr>';
	for(var i in arr)
	{
		str += '<td style="vertical-align:top;"><div id="'+electionType+'_'+i+'"></div></td>';
		
	}
	str += '</tr>';
	str += '</table>';
	
	elmt.innerHTML = str;

	

	for(var i in arr)
	{
		var dtArr = arr[i].partyResultsVO;
		
		var resultsDataSource = new YAHOO.util.DataSource(dtArr);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
			fields : [
			{
				key : "candidateName"
			}, 
			{
				key : "partyName"
			},
			{
				key : "votesEarned",parser:"number"
			}, 
			{
				key : "percentage",parser:"number"
			},
			{
				key : "rank",parser:"number"
			}]
		};

		var resultsColumnDefs = [
									{
										label:""+arr[i].electionYear+" - "+arr[i].constituencyName,
										className:"yui-dt-sortable ",
										children:	[ 
														{
															key : "candidateName",
															label : "Name",
															sortable : true
														},
														{
															key : "partyName",
															label : "Party",
															sortable : true
														},
														{
															key : "votesEarned",
															label : "Votes Earned",
															sortable : true
														},
														{
															key : "percentage",
															label : "%",
															sortable : true
														},
														{
															key : "rank",
															label : "Rank",
															sortable : true
														}
													]
									}
								]
		myDataTable = new YAHOO.widget.DataTable(electionType+"_"+i,resultsColumnDefs, resultsDataSource,{}); 
	}
}

function initializeBiElectionPage()
{
	buildBiElectionPageLayout();
	buildBiElectionDistricts();
	getMlaDetails();	
	buildMandalsVotingTrendz();
}