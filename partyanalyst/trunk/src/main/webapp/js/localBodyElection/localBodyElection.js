
var localBodyElectionObj = {
								stateId:'',
								stateName:'',
								localBodyElectionTypeId:'',
								localBodyElectionTypeName:'',
								localBodyId:'',
								localBodyName:'',
								tehsilId:'',
								tehsilName:'',
								districtId:'',
								districtName:''
						   };

function initializeLocalBodiesElectionPage()
{
	buildLocalBodyElectionNews();
	buildElectionResultsDataTable();
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
		label : "Party Name",
		sortable : true
	}, {
		key : "participatedSeats",
		parser:"number",
		label : "Seats (P)",
		sortable : true
	}, {
		key : "partyWonSeats",
		parser:"number",
		label : "Seats Won",
		sortable : true
	}, {
		key : "partySecndPos",
		parser:"Second Position",
		label : "2nd Pos",
		sortable : true
	}, {
		key : "partyThirdPos",
		parser:"3rd Pos",
		label : "3rd Pos",
		sortable : true
	}, {
		key : "partyNthPos",
		parser:"Nth Pos",
		label : "Nth Pos",
		sortable : true
	}, {
		key : "votesGained",
		parser:"number",
		label : "Votes Gained",
		sortable : true
	} , {
		key : "partiPartiVotesPercent",
		parser:"number",
		label : "Votes % (P)",
		sortable : true
	} , {
		key : "totConstiVotesPercent",
		parser:"number",
		label : "Total Votes %",
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