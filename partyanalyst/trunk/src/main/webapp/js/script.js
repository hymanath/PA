var httpRequest;   

function showDistricts(state)   
{   
	if (window.ActiveXObject){
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		httpRequest = new XMLHttpRequest();
	}
	var url=("partyPerformance.action");    
	httpRequest.open("GET", url, true);
	httpRequest.onreadystatechange = function() {stateChanged(); };
	httpRequest.send(null);
	if (httpRequest==null)   {  
	  alert ("Your browser does not support AJAX!");   
	  return;   
	}  
}    
    
function stateChanged()    
{    
	if (httpRequest.readyState==4)   
	{    
	    var response = eval('(' + httpRequest.responseText + ')');
	    var drp = document.getElementById("states");
	    $.each(response, function(ky, val) {
	         drp.options[drp.options.length] = new Option(val, ky);
	    });
	}   
}   

