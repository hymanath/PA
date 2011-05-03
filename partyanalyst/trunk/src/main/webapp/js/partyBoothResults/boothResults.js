
function getConstituenciesList(form, url) {
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
				alert("Invalid JSON result" + e);
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
		} ]
	};

	var resultsColumnDefs = [ {
		key : "partNo",
		parser:"number",
		label : "Booth No",
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