
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
								districtName:'',
								electionYear:''
						   };

function initializeLocalBodiesElectionPage()
{

	buildLocalBodyElectionNews();
	buildElectionResultsDataTable();
	getWardWiseElectionResults('all',0);
}


function getWardWiseElectionResults(type,value)
{	
	var jsObj=
	{
		stateId:localBodyElectionObj.stateId,
		localBodyId:localBodyElectionObj.localBodyId,
		localBodyElectionId:localBodyElectionObj.localBodyElectionId,
		taskType:type,
		elmtId:''+value,
		task:"getWardWiseElectionResults"					
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getWardWiseElectionResults.action?"+rparam;						
	
	localBodyAjaxCall(jsObj,url);
}



function buildWardWiseElectionResults(jsObj,results)
{
	var elmt = document.getElementById("wardsElectionResults_body_results");
	if(!elmt)
		return;

	elmt.innerHTML = '';
	var wardResults = [];

	for(var i in results)
	{
		for(var j in results[i].partyElectionResultsVO)
		{
			var obj = {
						candidateName:results[i].partyElectionResultsVO[j].candidateName,
						constituencyName:results[i].constituencyName,
						partyName:results[i].partyElectionResultsVO[j].partyName,
						totalVoters:results[i].totalVoters,
						votesEarned:results[i].partyElectionResultsVO[j].votesEarned,
						votesPercentage:results[i].partyElectionResultsVO[j].votesPercentage,
						rank:results[i].partyElectionResultsVO[j].rank,
						moreDetails:'<a href="javascript:{}" onclick="showWardWiseMoreResults(\''+results[i].constituencyId+'\',\''+localBodyElectionObj.localBodyElectionTypeName+'\',\''+localBodyElectionObj.electionYear+'\')"> More Results</a>'
					  };
			wardResults.push(obj);			
		}
	}

	var resultsDataSource = new YAHOO.util.DataSource(wardResults);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "candidateName"
			}, {
				key : "constituencyName"
			}, {
				key : "partyName"
			}, {
				key : "totalVoters",parser:"number"
			}, {
				key : "votesEarned",parser:"number"
			}, {
				key : "votesPercentage",parser:"number"
			}, {
				key : "rank",parser:"number"
			}, {
				key : "moreDetails"
			}]
		};

		var resultsColumnDefs = [ {
			key : "candidateName",
			label : "Candidate",
			sortable : true
		},{
			key : "constituencyName",
			label : "Constituency",
			sortable : true
		}, {
			key : "partyName",
			label : "Party",
			sortable : true
		}, {
			key : "totalVoters",
			parser:"number",
			label : "Voters",
			sortable : true
		}, {
			key : "votesEarned",
			parser:"number",
			label : "Votes Earned",
			sortable : true
		}, {
			key : "votesPercentage",
			parser:"number",
			label : "Votes %",
			sortable : true
		}, {
			key : "rank",
			parser:"number",
			label : "Rank",
			sortable : true
		}, {
			key : "moreDetails",			
			label : "More Details",
		}];	

		var myConfigs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 20
			})
		};
		var myDataTable = new YAHOO.widget.DataTable("wardsElectionResults_body_results",resultsColumnDefs, resultsDataSource, myConfigs);  
}

function changeWardWiseResultsCriteria(type)
{
	var partySelectElmt = document.getElementById("wardWise_parties");
	var wardSelectElmt = document.getElementById("wardWise_ward");
	
	if(type == "all")
	{
		partySelectElmt.style.visibility = 'hidden';
		wardSelectElmt.style.visibility = 'hidden';		
		getWardWiseElectionResults('all',0);
	}
	else if(type == "partyWise")
	{
		wardSelectElmt.style.visibility = 'hidden';
		partySelectElmt.style.visibility = 'visible'			
	}
	else if(type == "wardWise")
	{
		partySelectElmt.style.visibility = 'hidden';
		wardSelectElmt.style.visibility = 'visible'			
	}
}

function localBodyAjaxCall(jsObj,url)
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