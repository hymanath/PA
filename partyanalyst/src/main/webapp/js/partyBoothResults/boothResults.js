
function getConstituenciesList(form, url) {
   clearOptions(false,false,false,true);
	if (form.electionType.value == 0
			|| form.electionYear.value == "0")
		return;

	getConstituencies(form, url);
}

function getConstituencies(form, url) {
    
	var callback = {

		success : function(o) {
			try {
				var results = YAHOO.lang.JSON.parse(o.responseText);				
				/*if(url=="partyBoothResult1AjaxAction.action")
				{
					buildConstituencySelect(form, results);
					document.getElementById("constituencyRow").style.display = "";
				}
				else if(url=="partyBoothResultPartyAjaxAction.action")
				{
					buildPartySelect(form, results);
					document.getElementById("partyRow").style.display = "";
				}*/

				if(url.match("partyBoothResult1AjaxAction.action"))
				{
					stopImageC();
					buildConstituencySelect(form, results);
					document.getElementById("constituencyRow").style.display = "";
				}
				else if(url.match("partyBoothResultPartyAjaxAction.action"))
				{
					buildPartySelect(form, results);
					document.getElementById("partyRow").style.display = "";
				}

			} catch (e) {
				//alert("Invalid JSON result" + e);
			}

		},
		scope : this,
		failure : function(o) {
			alert("Failed to load result" + o.status + " " + o.statusText);
		}
	};
	YAHOO.util.Connect.setForm(form);
	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

function buildConstituencySelect(form, results) {
	
	for (i = 0; i < results.length; i++) {
		addOption(form.constituencyName, results[i].name, results[i].id);
	}

}

function buildPartySelect(form, results) {
	
	for (i = 0; i < results.length; i++) {
		addOption(form.partyName, results[i].name, results[i].id);
	}

}


function addOption(selectbox, text, value) {
	var optn = document.createElement("OPTION");
	optn.text = text;
	optn.value = value;
	selectbox.options.add(optn);
}

function clearOptions(selectbox) {
	selectbox.options.length = 1;
}

function initializeResultsTable() {

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("boothResultsTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	
	YAHOO.widget.DataTable.BoothLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var id=oRecord.getData("partNo");
		var boothId=oRecord.getData("boothId"); 
		//var name = oRecord.getData("name");
		
		elLiner.innerHTML ='<a id="boothId" onclick=" boothDetails('+boothId+');" title="Click to Know the Details of Total Voters,Villages Covered and Mandal Details and Recent Election Details in this Booth">'+id+'</a>';
		
	}
	
	
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "partNo",parser:"number"
		}, {
			key : "location"
		}, {
			key : "villagesCovered"
		}, {
			key : "mandal"
		}, {
			key : "votesEarned",parser:"number"
		}, {
			key : "totalVoters",parser:"number"
		}, {
			key : "pollingPercentage",parser:"number"
		} , {
			key : "percentage",parser:"number"
		},{
			key : "boothId",parser:"number"
		} ]
	};

	var resultsColumnDefs = [ {
		key : "partNo",
		parser:"number",
		label : "Booth No",
		formatter:YAHOO.widget.DataTable.BoothLink,
		sortable : true
	}, {
		key : "location",
		label : "Location",
		sortable : true
	}, {
		key : "villagesCovered",
		label : "Villages Covered",
		sortable : true
	}, {
		key : "mandal",
		label : "Mandal",
		sortable : true
	}, {
		key : "votesEarned",
		label : "Votes Earned",
		sortable : true
	}, {
		key : "totalVoters",
		label : "Polled Votes",
		sortable : true
	}, {
		key : "pollingPercentage",
		label : "Polling %",
		sortable : true
	}, {
		key : "percentage",
		label : "Votes %",
		sortable : true
	} ];

	var myConfigs = {
    paginator : new YAHOO.widget.Paginator({
        rowsPerPage: 50
    })
};


	var myDataTable = new YAHOO.widget.DataTable("boothResultsMarkup",resultsColumnDefs, resultsDataSource,myConfigs);  

}

function boothDetails(id){
	
	var urlStr = contextPath+"/boothResultsForAllElectionsPopupAction.action?boothId="+id;
	var browser1 = window.open(urlStr,"boothResultsForAllElections","scrollbars=yes,height=600,width=900,left=200,top=200");
	browser1.focus();
}