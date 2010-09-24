
var localBodyElectionObj = {
								stateId:'',
								stateName:'',
								localBodyElectionTypeId:'',
								localBodyElectionTypeName:'',
								localBodyId:'',
								localBodyName:'',
								localBodyElectionId:'',
								tehsilId:'',
								tehsilName:'',
								districtId:'',
								districtName:''
						   };

function initializeLocalBodiesElectionPage()
{
	buildLocalBodyElectionNews();
	buildElectionResultsDataTable();
	//buildWardWiseElectionResults('all');
}

function buildWardWiseElectionResults(type)
{
	var jsObj=
	{
		stateId:localBodyElectionObj.stateId,
		localBodyId:localBodyElectionObj.localBodyId,
		localBodyElectionId:localBodyElectionObj.localBodyElectionId,
		taskType:type,
		partyId:"0",
		task:"getWardWiseElectionResults"					
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getWardWiseElectionResults.action?"+rparam;						
	
	callAjax(jsObj,url);
}

function buildWardWiseElectionResults(jsObj,results)
{
	var elmt = document.getElementById("wardsElectionResults_body_results");
	if(!elmt)
		return;

	var str = '';
	str += '<div id="liquid">';
	str += '<span class="previous"></span>';
	str += '<div class="wrapper">';
	str += '	<ul>';
	str += '		<li><a class="customLink" rel="1"><div class="tabbox">Ward 1</div></a></li>';
	str += '		<li><a class="customLink" rel="2"><div class="tabbox">Ward 2</div></a></li>';
	str += '		<li><a class="customLink" rel="3"><div class="tabbox">Ward 3</div></a></li>';
	str += '		<li><a class="customLink" rel="4"><div class="tabbox">Ward 4</div></a></li>';	
	str += '		<li><a class="customLink" rel="5"><div class="tabbox">Ward 5</div></a></li>';
	str += '		<li><a class="customLink" rel="6"><div class="tabbox">Ward 6</div></a></li>';
	str += '		<li><a class="customLink" rel="7"><div class="tabbox">Ward 7</div></a></li>';
	str += '		<li><a class="customLink" rel="8"><div class="tabbox">Ward 8</div></a></li>';
	str += '	</ul>';
	str += '</div>';
	str += '<span class="next"></span>';
	str += '</div>';
	
	str += '<div id="wardResultsSlider" class="slider" >';
    str += '<ul style="height:241px;">';
    str += '    <li >Content 1 </li>';
    str += '    <li >Content 2</li>';
    str += '    <li >Content 3</li>';
	 str += '    <li >Content 4</li>';
    str += '    <li >Content 5</li>';
    str += '    <li >Content 6</li>';
	 str += '    <li >Content 7</li>';
    str += '    <li >Content 8</li>';
    str += '</ul>';
	str += '</div>';

	elmt.innerHTML = str;
	
	
		$('#liquid').liquidcarousel({
			height: 15,		//the height of the list
			duration: 100,		//the duration of the animation
			hidearrows: false	//hide arrows if all of the list items are visible
		});
	
	
	
		$("#wardResultsSlider").sudoSlider({ 
			prevNext: false,
			customLink:'a.customLink',
			updateBefore:true
		});

}

function callAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							if(jsObj.task == "getWardWiseElectionResults")
								buildWardWiseElectionResults(jsObj,myResults);							
							
						}catch (e) {   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildElectionResultsDataTable()
{
	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("localBodyElectionResultsTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "partyName",
		}, {
			key : "participatedSeats",parser:"number"
		}, {
			key : "partyWonSeats",parser:"number"
		}, {
			key : "partySecndPos",parser:"number"
		}, {
			key : "partyThirdPos",parser:"number"
		}, {
			key : "partyNthPos",parser:"number"
		} , {
			key : "votesGained",parser:"number"
		} , {
			key : "partiPartiVotesPercent",parser:"number"
		} , {
			key : "totConstiVotesPercent",parser:"number"
		}]
	};

	var resultsColumnDefs = [ {
		key : "partyName",
		parser:"number",
		label : "Party",
		sortable : true
	}, {
		key : "participatedSeats",
		parser:"number",
		label : "Seats (P)",
		sortable : true
	}, {
		key : "partyWonSeats",
		parser:"number",
		label : "Won",
		sortable : true
	}, {
		key : "partySecndPos",
		parser:"Second Position",
		label : "2nd",
		sortable : true
	}, {
		key : "partyThirdPos",
		parser:"3rd Pos",
		label : "3rd",
		sortable : true
	}, {
		key : "partyNthPos",
		parser:"Nth Pos",
		label : "Nth",
		sortable : true
	}, {
		key : "votesGained",
		parser:"number",
		label : "(V) Gained",
		sortable : true
	} , {
		key : "partiPartiVotesPercent",
		parser:"number",
		label : "(V) % (P)",
		sortable : true
	} , {
		key : "totConstiVotesPercent",
		parser:"number",
		label : "Total (V)%",
		sortable : true
	} ];

	var myConfigs = {
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage: 50
		})
	};


	var myDataTable = new YAHOO.widget.DataTable("localBodyElectionResults_body_dataTable",resultsColumnDefs, resultsDataSource,myConfigs);  
}

function buildLocalBodyElectionNews()
{	
	var options = {
    "format" : "300x250",
	"queryList" : [
          {
            "title" : localBodyElectionObj.localBodyName,
            "q" : localBodyElectionObj.districtName+","+localBodyElectionObj.stateName+", India"
          }
     ]
  }


  var content = document.getElementById('local_body_news');
  var newsShow = new google.elements.NewsShow(content, options);
}