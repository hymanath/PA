

var statePageObj =	{
						stateDetails:{
										stateId:'',
										stateName:'',
										stateLanguage:'',
										stateSong:'',
										adminCapital:''
									 },
						electionResults:[],
						electionTypes:[],
						electionResultsByType:[]
					};

var globalElectionType = '';
var results = new Array();

var searchHead = '';
var searchString = '';


function buildStatePageLayout()
{
	var statePageLayout = new YAHOO.widget.Layout('statePage_layout_main', { 
	height:650,
	units: [			
			{ 
				position: 'right', 
				width: 340,
				header:false,
				body: 'statePage_layout_right',
				resize: false,
				gutter: '2px',
				collapse: false,
				scroll: true,						
				animate: true 
			}, 					 
			{ 
				position: 'center',						
				body: 'statePage_layout_center',
				resize: false,
				gutter: '2px',
				collapse: true,
				scroll: true,						
				animate: true
			} 
		 ] 
		
	});
	statePageLayout.render(); 
}

function differentiateElectionTypes()
{
	var lObj = statePageObj.electionResults;
	
	if(lObj.length == 0)
		return;
	
	statePageObj.electionTypes.push(lObj[0].electionType);
	globalElectionType = lObj[0].electionType;
	for(var i in lObj)
	{
		if(lObj[i].electionType != globalElectionType)
		{
			globalElectionType = lObj[i].electionType;
			statePageObj.electionTypes.push(lObj[i].electionType);		
		}
	}
	
	globalElectionType = lObj[0].electionType;
	
	var eArr = new Array();
	
	for(var j in lObj)
	{		
		if(lObj[j].electionType == globalElectionType)
		{	
			var eObj = {
							electionId : lObj[j].electionId,
							electionTypeId : lObj[j].electionTypeId,
							electionType : lObj[j].electionType,
							year : lObj[j].year,
							subtype:lObj[j].subtype,
							partyResultsVO : lObj[j].partyResultsVO
					   };
			eArr.push(eObj);
		}
		else
		{
			globalElectionType = lObj[j].electionType;
			statePageObj.electionResultsByType.push(eArr);
			eArr = new Array();
			var eObj = {
							electionId : lObj[j].electionId,
							electionTypeId : lObj[j].electionTypeId,
							electionType : lObj[j].electionType,
							year : lObj[j].year,
							subtype:lObj[j].subtype,
							partyResultsVO : lObj[j].partyResultsVO
					   };
			eArr.push(eObj);
		}				
	}
	statePageObj.electionResultsByType.push(eArr);	
}

function buildelectionTypeList()
{
	var elmt = document.getElementById("electionTypesList");

	if(!elmt && statePageObj.electionTypes.length == 0)
		return;

	var str = '';
	str+='<ul id="stateElectionTypesList">';
	for(var i in statePageObj.electionTypes)
	{

		str+='<li onclick="changeElectionTypecarousel(\''+i+'\')">';
		if(i==0)
			str+='<div id="electionType_'+i+'" class="electionTypeListDiv  electionTypeListDivSelected" onclick="displayheaderArrow(\''+i+'\')">';		
		else
			str+='<div id="electionType_'+i+'" class="electionTypeListDiv" onclick="displayheaderArrow(\''+i+'\')">';		
		str+=	statePageObj.electionTypes[i];		
		str+='</div>';
		str+='</li>';
	}
	str+='</ul>';

	elmt.innerHTML = str;
}

function displayheaderArrow(index)
{
	var divElmt = document.getElementById("electionType_"+index);
	var elements = YAHOO.util.Dom.getElementsByClassName('electionTypeListDivSelected');
	for(var i in elements)
	{
		var id = elements[i].id;		
		YAHOO.util.Dom.removeClass(id, 'electionTypeListDivSelected'); 
	}
	
	YAHOO.util.Dom.addClass('electionType_'+index, 'electionTypeListDivSelected'); 
}

function changeElectionTypecarousel(index)
{
	buildElectionTypesAndYearsCarousel("electionTypesNYearsList",statePageObj.electionResultsByType[index]);
}

function buildElectionTypesAndYearsCarousel(divId,arr)
{
	var elmt = document.getElementById(divId);
	if(!elmt && arr.length == 0)
		return;

	var str = '';
	str+='<ul>';
	for(var i in arr)
	{	
		str+='<li>';
		str+='<div class="electionResultsDiv">';
		if(arr[i].subtype == 'BYE')
			str+='	<div class="electionResultsDiv_head">'+arr[i].electionType+' [ '+ arr[i].subtype+' ] - '+arr[i].year+'</div>';
		else
			str+='	<div class="electionResultsDiv_head">'+arr[i].electionType+' - '+arr[i].year+'</div>';
		str+='	<div class="electionResultsDiv_body">';
		str+='  <table width="100%" style="width:100%" class="partyResultsTable">';
		str+='	<tr>';
		str+='	<th>Party</th>';
		str+='	<th style="text-align:center;">Won</th>';
		str+='	<th style="text-align:center;">Flag</th>';
		str+='	</tr>';
		for(var k=0;k<arr[i].partyResultsVO.length;k++)
		{
			if(k == 3)
				break;
			var dt = arr[i].partyResultsVO[k];
			str+='  <tr>';
			str+='  <td width="70%" style="width:70%;">';
			if(dt.partyName != 'INDEPENDENT')
				str += '<a href="partyPageAction.action?partyId='+dt.partyId+'">'+dt.partyName+'</a>';
			else
				str += '<a href="javascript:{}">'+dt.partyName+'</a>';
			
			str += '</td>';
			str+='  <td width="10%" style="width:10%;text-align:center;">'+dt.totalSeatsWon+'</td>';
			if(dt.partyFlag)
				str+='  <td style="text-align:center;"><img src="images/party_flags/'+dt.partyFlag+'" height="30" width="40"/></td>';
			else
				str+='  <td style="text-align:center;"><img src="images/party_flags/no_Image.png" height="30" width="40"/></td>';		
			str+='  </tr>';
		}
		str+='  </table>';

		str+='	</div>';
		str+='	<div class="electionResultsDiv_footer">';
		str+='	<a class="viewAncs" href="javascript:{}" onclick="callAjax(\'electionId='+arr[i].electionId+'\')">View All Party Results</a>  ';
		//str+='	| <a class="viewAncs" href="javascript:{}" onclick="showElectionResults(\''+arr[i].electionId+'\')">Analyze</a>';
		if(arr[i].subtype != 'BYE'){
		str+='	| <a href="electionDetailsReportAction.action?electionId='+arr[i].electionId+'&stateID='+statePageObj.stateDetails.stateId+'&stateName='+statePageObj.stateDetails.stateName+'&electionType='+arr[i].electionType+'&electionTypeId='+arr[i].electionTypeId+'&year='+arr[i].year+'"  class="viewAncs">Analyze</a> ';
		}
		str+='	</div>';
		str+='</div>';
		str+='</li>';
	}
	str+='</ul>';

	elmt.innerHTML = str;

	electionTypesAndYearsCarousel = new YAHOO.widget.Carousel(divId,
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 3,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});

	electionTypesAndYearsCarousel.render(); 
	electionTypesAndYearsCarousel.show();
}

function showElectionResults(param,results)
{	
	var str='';
	str+='<div id="elecResultsDiv" class="yui-skin-sam">';
	str+='<table id="elecResultsTab">';
	for(var item in results)
	{			
		str+='<tr>';
		str+='<td><a href="partyPageAction.action?partyId='+results[item].partyId+'">'+results[item].partyName+'</a></td>';
		if(results[item].partyFlag)
			str+='<td><img src="images/party_flags/'+results[item].partyFlag+'" height="30" width="40"/></td>';
		else	
			str+='<td><img src="images/party_flags/no_Image.png" height="30" width="40"/></td>';
		str+='<td align="center">'+results[item].totalSeatsWon+'</td>';
		str+='</tr>';
	}
	str+='</table>';
	str+='</div>';
	
	var myPanel = new YAHOO.widget.Panel("electionResultsPopupDiv_inner", {			
			 width:"500px",
			 fixedcenter: false, 
			 constraintoviewport: true, 
			 underlay: "none", 
			  close: true, 
			 visible: true, 
			 draggable: true
   });
   myPanel.setHeader("Election Results ..");
   myPanel.setBody(str);
   myPanel.render();
	   

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("elecResultsTab"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "partyName"
		},{
			key : "partyFlag"
		}, {
			key : "totalSeatsWon",parser:"number"
		}]
	};

	var resultsColumnDefs = [ {
		key : "partyName",
		label : "PARTY NAME",
		sortable : true
	},{
		key : "partyFlag",
		label : "PARTY Flag"
	}, {
		key : "totalSeatsWon",
		label : "SEATS WON",
		sortable : true
	}];

	
	var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    }) 
				};	

   	var myDataTable = new YAHOO.widget.DataTable("elecResultsDiv",resultsColumnDefs, resultsDataSource,myConfigs);  
}

function buildLatestNews()
{
	var options = {
    "format" : "300x250",
	"queryList" : [
          {
            "title" : searchHead,
            "q" : searchString
          }	
     ],
	"linkTarget" : "_blank"
  }


  var content = document.getElementById('stateNewsBody');
  var newsShow = new google.elements.NewsShow(content, options);
}

function initializeStatePage()
{
	buildStatePageLayout();
	differentiateElectionTypes();
	buildelectionTypeList();
	buildElectionTypesAndYearsCarousel("electionTypesNYearsList",statePageObj.electionResultsByType[0]);
	buildLatestNews();
}