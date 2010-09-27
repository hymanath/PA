
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
	getWardWiseElectionResults('all');
}

function getWardWiseElectionResults(type)
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
	
	localBodyAjaxCall(jsObj,url);
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
	for(var i =0; i<results.length; i++)
	{
		var rel = i+1;
		str += '		<li><a class="customLink" rel="'+rel+'"><div class="tabbox">'+results[i].constituencyName+'</div></a></li>';
	}	
	str += '	</ul>';
	str += '</div>';
	str += '<span class="next"></span>';
	str += '</div>';
	
	str += '<div id="wardResultsSlider" class="slider" >';
    str += '<ul style="height:241px;">';
	
	for(var i =0; i<results.length; i++)
	{
		str += '<li>';
		str += '<div id="wardResultsWonDetails_'+i+'">';
		str += '<table border="1" width="100%" class="datatableClass" style="border-collapse:collapse;border-color:#ADADAD;">';
		str += '<tr>';
		str += '<th>Ward Name : </th>';
		str += '<td>'+results[i].constituencyName+'</td>';
		str += '<th>Total Voters : </th>';
		str += '<td>'+results[i].totalVoters+'</td>';
		str += '</tr>';		
		str += '<tr>';
		str += '<th>Party : </th>';
		str += '<td>'+results[i].wonPartyName+'</td>';
		str += '<th>Candidate : </th>';
		str += '<td>'+results[i].wonCandidate+'</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '<div class="yui-skin-sam">';
		str += '<div id="wardResultsOppositionDetails_'+i+'">';
		str += '<table id="wardResultsOppositionDetails_'+i+'_table">';
		for(var j=0; j<results[i].partyElectionResultsVO.length; j++)
		{
			var local = results[i].partyElectionResultsVO[j];
			str += '<tr>';
			str += '<td>'+local.partyName+'</td>';
			str += '<td>'+local.candidateName+'</td>';
			str += '<td>'+local.votesEarned+'</td>';
			str += '<td>'+local.votesPercentage+'</td>';
			str += '<td>'+local.rank+'</td>';
			str += '</tr>';
		}		
		str += '</table>';
		str += '</div>';
		str += '</div>';
		str += '</li>';
	}   
    str += '</ul>';
	str += '</div>';

	elmt.innerHTML = str;


	for(var i=0;i<results.length;i++)
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("wardResultsOppositionDetails_"+i+"_table"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "candidateName"
			}, {
				key : "votesEarned",parser:"number"
			}, {
				key : "votesPercentage",parser:"number"
			}, {
				key : "rank",parser:"number"
			}]
		};

		var resultsColumnDefs = [ {
			key : "partyName",
			label : "Party",
			sortable : true
		}, {
			key : "candidateName",
			label : "Candidate",
			sortable : true
		}, {
			key : "votesEarned",
			parser:"number",
			label : "Votes Earned",
			sortable : false
		}, {
			key : "votesPercentage",
			parser:"number",
			label : "Votes %",
			sortable : false
		}, {
			key : "rank",
			parser:"number",
			label : "Rank",
			sortable : false
		}];	

		/*var myConfigs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 5
			})
		};*/
		var myDataTable = new YAHOO.widget.DataTable("wardResultsOppositionDetails_"+i,resultsColumnDefs, resultsDataSource);  
	}
	
		$('#liquid').liquidcarousel({
			height: 15,		//the height of the list
			duration: 100,		//the duration of the animation
			hidearrows: false	//hide arrows if all of the list items are visible
		});
	
	
	
		$("#wardResultsSlider").sudoSlider({ 
			prevNext: false,
			width:500,
			height:300,
			customLink:'a.customLink',
			updateBefore:true
		});

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