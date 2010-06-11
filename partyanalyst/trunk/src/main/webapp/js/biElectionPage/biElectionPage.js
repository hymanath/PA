
var dtArray =  new Array();
var assemblyElectionType='Assembly';
var presentElectionYear='2009';

var districtsInfo = new Array();

function getMlaDetails()
{
//Karimnagar
var mlaDetails1 = {
				  distName:'Karimnagar',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=322">Dharmapuri</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16806">Eshwar Koppula</a>',
				  partyName:'TRS',
				  votesPercent:'35.89',
				  marginPercent:'1.69',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(322)">view</a>'

};
dtArray.push(mlaDetails1);
var mlaDetails2 = {
				  distName:'Karimnagar',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=21">Huzurabad</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16899">Eatala Rajender</a>',
				  partyName:'TRS',
				  votesPercent:'38.82',
				  marginPercent:'10.28',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(21)">view</a>'

};
dtArray.push(mlaDetails2);
var mlaDetails3 = {
				  distName:'Karimnagar',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=321">Koratla</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16788">Kalvakuntla Vidyasagar Rao</a>',
				  partyName:'TRS',
				  votesPercent:'32.38',
				  marginPercent:'12.03',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(321)">view</a>'

};
dtArray.push(mlaDetails3);
var mlaDetails4 = {
				  distName:'Karimnagar',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=31">Sircilla</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16871">Kalvakuntala Taraka Ramarao</a>',
				  partyName:'TRS',
				  votesPercent:'26.92',
				  marginPercent:'0.92',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(31)">view</a>'

};
dtArray.push(mlaDetails4);
var mlaDetails5 = {
				  distName:'Karimnagar',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=323">Vemulawada</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16863">Ramesh Chennamaneni</a>',
				  partyName:'TDP',
				  votesPercent:'29.98',
				  marginPercent:'1.49',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(323)">view</a>'

};
dtArray.push(mlaDetails5);

//Adilabad
var mlaDetails6 = {
				  distName:'Adilabad',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=8">Sirpur</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16609">Kaveti Sammaiah</a>',
				  partyName:'TRS',
				  votesPercent:'38.71',
				  marginPercent:'5.98',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(8)">view</a>'

};
dtArray.push(mlaDetails6);
var mlaDetails7 = {
				  distName:'Adilabad',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=4">Chennur</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16617">Nallala Odelu</a>',
				  partyName:'TRS',
				  votesPercent:'39.98',
				  marginPercent:'10.26',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(4)">view</a>'

};
dtArray.push(mlaDetails7);
var mlaDetails8 = {
				  distName:'Adilabad',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=296">Mancherial</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16633">Aravinda Reddy Gaddam</a>',
				  partyName:'TRS',
				  votesPercent:'44.13',
				  marginPercent:'10.46',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(296)">view</a>'

};
dtArray.push(mlaDetails8);

//Nizamabad
var mlaDetails9 = {
				  distName:'Nizamabad',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=18">Yellareddy</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16739">Eanugu Ravinder Reddy</a>',
				  partyName:'TRS',
				  votesPercent:'52.41',
				  marginPercent:'25.04',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(18)">view</a>'

};
dtArray.push(mlaDetails9);
var mlaDetails10 = {
				  distName:'Nizamabad',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=342">Nizamabad Urban</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16759">Endala Lakshmi Narayana</a>',
				  partyName:'BJP',
				  votesPercent:'42.52',
				  marginPercent:'11.57',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(342)">view</a>'

};
dtArray.push(mlaDetails10);

//Medak
var mlaDetails11 = {
				  distName:'Medak',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=40">Siddipet</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=16928">Thaneeru Harish Rao</a>',
				  partyName:'TRS',
				  votesPercent:'65.02',
				  marginPercent:'48.99',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(40)">view</a>'

};
dtArray.push(mlaDetails11);

//Warangal
var mlaDetails12 = {
				  distName:'Warangal',
				  consName:'<a class="dataAnc" href="constituencyPageAction.action?constituencyId=363">Warangal West</a>',
				  name:'<a class="dataAnc" href="candidateElectionResultsAction.action?candidateId=17820">Dasyam Vinaya Bhasker</a>',
				  partyName:'TRS',
				  votesPercent:'39.64',
				  marginPercent:'5.78',
				  completeResults:'<a class="dataAnc" href=javascript:{} onclick="getConstituencyElectionResults(363)">view</a>'

};
dtArray.push(mlaDetails12);

buildResultsDataTable("mpsInfoDivBody",dtArray);

}


function buildResultsDataTable(id,dtSource)
{	

 var allianceResultsColumnDefs = [
							{key: "distName", label: "District", sortable:true},
							{key: "consName", label: "Constituency", sortable:true},		
							{key: "name", label: "Candidate", sortable:true},
							{key: "partyName", label: "Party", sortable:true},
							{key: "votesPercent", label:"Votes %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
							{key: "marginPercent", label:"Votes Margin %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
							{key: "completeResults", label: "Results"}
							];                	 	    

	var allianceResultsDataSource = new YAHOO.util.DataSource(dtSource); 
	allianceResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	allianceResultsDataSource.responseSchema = {
			fields: ["distName","consName", "name", "partyName",{key: "votesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
								  {key: "marginPercent", parser:YAHOO.util.DataSourceBase.parseNumber},"completeResults"] 
			};

	var myConfigs = { 
			paginator : new YAHOO.widget.Paginator({ 
			rowsPerPage    : 12			        
			})
			};

	allianceResultsDataTable = new YAHOO.widget.DataTable(id, allianceResultsColumnDefs, allianceResultsDataSource,myConfigs);
				
}


function buildBiElectionPageLayout()
{
	var biElectionPageLayout = new YAHOO.widget.Layout('biElectionPageLayout_main', { 
	height:380,
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
	var elmt = document.getElementById("mla_body");
	if(!elmt)
		return;

	var str='';
	str += '<div id="districtsInfoRadioElmtDiv">';
	str += '<table>';
	str += '<tr>';
	str += '<th>';
	str += 'Select District : ';
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
	str += '</table>';
	str += '</div>';

	str += '<div id="constituenciesInfoSelectElmtDiv">';
	str += '<table>';
	str += '<tr>';
	str += '<th>';
	str += 'Select Constituency : ';
	str += '</th>';
	str += '<td>';
	str += '<div id="constSelectElmt"><select onchange="setValuesForMandalVotingTrendz(this.options[this.selectedIndex].value)">';
	for(var j in districtsInfo[0].constituencies)
	{
		str += '<option value="'+districtsInfo[0].constituencies[j].constId+'"> '+districtsInfo[0].constituencies[j].constName+' </option>';
	}	
	str += '</select></div>';
	str += '</td></table>';
	str += '<div id="mandalVotingTrendzDataDiv">';
	str += '<div id="mandalVotingTrendzDataDiv_head"></div>';
	str += '<div id="mandalVotingTrendzDataDiv_body"></div>';
	str += '</div>';
	elmt.innerHTML = str;

	getMandalVotingTrendz(districtsInfo[0].districtId,districtsInfo[0].constituencies[0].constId);
}

function setValuesForMandalVotingTrendz(value)
{
	var radioValue = '';
	var elements = document.getElementsByTagName('input'); 

	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="radio" && elements[i].name=="districtRadio" && elements[i].checked==true)
			radioValue = elements[i].value;
	}
	
	getMandalVotingTrendz(radioValue,value);
}

function getConstituenciesInfo(distId,index)
{
	var obj = districtsInfo[index];

	var elmt = document.getElementById("constSelectElmt");
	if(!elmt)
		return;

	var str = '';
	str += '<select>';
	str += '<option value="0">Select</option>';
	for(var j in obj.constituencies)
	{
		str += '<option value="'+obj.constituencies[j].constId+'"> '+obj.constituencies[j].constName+' </option>';
	}	
	str += '</select>';
	
	elmt.innerHTML = str;
}

function buildBiElectionDistricts()
{
	var elmt = document.getElementById("biElectionDistricts_body");
	if(!elmt)
		return;

	var str = '';
	str += '<table border="0" id="distConstTable">';
	for(var i in districtsInfo)
	{
		str += '<tr>';
		str += '<td width="5%" align="center"> <img src="images/icons/districtPage/listIcon.png"/></td>';
		str += '<td width="35%">';
		str += '<span class="mandalNameSpan">';
		str += '<a href ="districtPageAction.action?districtId='+districtsInfo[i].districtId+'&districtName='+districtsInfo[i].districtName+'" class="districtAnc">'+districtsInfo[i].districtName+'- '+districtsInfo[i].constituencies.length+'</a>';
		str += '</span>';
		str += '</tr>';
		for(var j in districtsInfo[i].constituencies)
		{
			var info = districtsInfo[i].constituencies[j];
			str += '<tr>';
			str += '<td colspan="2">';
			str += '	<a href="constituencyPageAction.action?constituencyId='+info.constId+ '" class="districtAncNew"> '+info.constName+ '</a>';
			str += '</td>';
			str += '</tr>';
		}
	}
	str += '</table>';

	elmt.innerHTML = str;
}

function buildMandalVotingTrendzData(jsObj,results)
{
	var headElmt = document.getElementById("mandalVotingTrendzDataDiv_head");
	var bodyElmt = document.getElementById("mandalVotingTrendzDataDiv_body");

	if(!headElmt || !bodyElmt)
		return;

	var hstr = '';
	hstr = 'Mandal Voting Trendz';
	
	headElmt.innerHTML = hstr;

	var str = '';
	for(var i in results)
	{
		str += '<fieldset>';
		str += '<legend>'+results[i].mandalName+'</legend>';
		for(var j in results[i].electionResultsForAllPartiesVO)
		{
			str += '<div id="electionResults_'+i+'_electionType_'+j+'">';
			str += '<div id="electionResults_'+i+'_electionType_'+j+'_head" class="elecTypeMandalsVoting_head">';
			str += '<table class="elecTypeMandalsVoting_table">';
			str += '<tr>';
			str += '<th>Election Type : </th>';
			str += '<th>'+results[i].electionResultsForAllPartiesVO[j].electionType+'</th>';
			str += '</tr>';
			str += '</table>';
			str += '</div>';
			str += '<div id="electionResults_'+i+'_electionType_'+j+'_body">'; 
			str += '</div>';
			str += '<div>';
		}
		str += '</fieldset>';
	}

	bodyElmt.innerHTML = str;

	for(var i in results)
	{
		for(var j in results[i].electionResultsForAllPartiesVO)
		{
			buildVotingTrendzDataTable("electionResults_"+i+"_electionType_"+j+"_body",results[i].electionResultsForAllPartiesVO[j].constituencyElectionResultsVO,results[i].electionResultsForAllPartiesVO[j].electionType);
		}
	}
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
		str += '<td><div id="'+electionType+'_'+i+'"></div></td>';
		
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