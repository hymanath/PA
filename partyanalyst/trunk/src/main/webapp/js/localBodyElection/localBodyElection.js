
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

var problemsInfo = new Array();
var createGroupDialog;

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
						partyId:results[i].partyElectionResultsVO[j].partyId,
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


  YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var partyIds = oRecord.getData("partyId");
		if(oData != 'IND' && partyIds != null){
		
		elLiner.innerHTML =
		"<a href='partyPageAction.action?partyId="+partyIds+"' style='text-decoration:none;'>"+oData+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}" style="text-decoration:none;">'+oData+'</a>';
	};
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
				key : "partyId",parser:"number"
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
			sortable : true,formatter:YAHOO.widget.DataTable.partyLink
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
								//alert( "Failed to load result" + o.status + " " + o.statusText);
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

function buildLocalElectionLevelProblemWindow(areaType)
{
	var headElmt = document.getElementById('problemViewingDiv_Head');
	var bodyElmt = document.getElementById('problemViewingDiv_Body');
	
	var str='';
	str+='<fieldset id="problemViewingFieldSet" style="width:248px;margin-top:10px;">';
	str+='<legend> View Your '+ areaType +' Problems</legend>';
	str+='<div id="problemViewingContentDiv" class="problemPostingContentDivClass">';	
	str+='<marquee direction="up" scrolldelay="200" onmouseover="this.stop();" onmouseout="this.start();">';
	str+='';
	if(problemsInfo.length == 0)
	{
		str+='<div class="problemDataDivClass" onclick="javascript:{}">';
		str+='<span><img height="10" width="10" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png"></img></span>';
		str+='<span> No problems has been posted </span>';
		str+='</div>';
	}
	else
	{
		for(var i in problemsInfo)
		{
			var data = problemsInfo[i];			
			str+='<div class="problemDataDivClass">';
			str+='<span><img height="10" width="10" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png"></img></span>';
			str+='<span><a class="districtAnc" href="problemCompleteDetailsAction.action?problemHistoryId='+data.problemHistoryId+'">'+data.problem+'</a></span>';
			str+='</div>';
		}
	}
	
	str+='</marquee>';
	str+='</div>';
	str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;	
}

function  buildMoreDetailsPopUp(selectedProblemId)
{		
	for(var i in problemsInfo)
		{
			var data = problemsInfo[i];			
			if(data.problemId==selectedProblemId){
						var elmt = document.getElementById('constituencyMgmtBodyDiv');
						var divChild = document.createElement('div');
						divChild.setAttribute('id','createDiv');
						var problemName = data.problem;
						data.problem.name = problemName[0].toUpperCase();
						elmt.appendChild(divChild);	

						var showProblemData='';		
						showProblemData+='<div align="center"><h3>Complete Report of <span style="color:green">'+data.problem+'</span> </h3></div>';
						showProblemData+='<fieldset>';  		
						showProblemData+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Details of the Problem</legend>';
						showProblemData+='<table id="probDetailsTable">';
						showProblemData+='<tr><th>Problem</th>';		
						showProblemData+='<th>Description</th>';
						showProblemData+='<th>IdentifiedDate</th></tr>';
						showProblemData+='<tr><td>'+data.problem+'</td>';
						showProblemData+='<td>'+data.description+'</td>';
						showProblemData+='<td>'+data.reportedDate+'</td></tr></table>';
						showProblemData+='</fieldset>';

						showProblemData+='<fieldset>';
						showProblemData+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Complained Person</legend>';		
						showProblemData+='<table id="postedPersonTable">';
						showProblemData+='<tr><th>Name</th>';								
						showProblemData+='<tr><td>'+data.name+'</td></tr></table>';
						showProblemData+='</fieldset>';
						
						showProblemData+='<div id="showProblems" class="yui-skin-sam" align="center"></div>';

						if(createGroupDialog)
							createGroupDialog.destroy();
						createGroupDialog = new YAHOO.widget.Dialog("createDiv",
								{ width : "600px", 		
								  fixedcenter : false, 
								  visible : true,  
								  constraintoviewport : true, 
								  iframe :false,
								  modal :false,
								  hideaftersubmit:true,
								  close:true,
								  x:400,
								  y:300,				  
								  buttons : [ { text:"Ok", handler: handleSubmit, isDefault:true}, 
											  { text:"Cancel", handler: handleCancel}]
								 } );
						
						createGroupDialog.setBody(showProblemData);
						
						createGroupDialog.render();
			}
		}
}
function handleSubmit()
{
	createGroupDialog.hide();			
}

function handleCancel()
{
	this.cancel();
}