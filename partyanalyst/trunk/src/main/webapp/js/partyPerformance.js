function callAjax(param){
 		var myResults;
 		var url = "<%=request.getContextPath()%>/partyPerformanceAjax.action?"+param;
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 
								processResponse(param, myResults);
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
 	
    function processResponse(param, response){
		if(param.substring(0, 4) == "type"){
	    	fillDropDown(document.getElementById("stateList"), response.states);
		 	fillDropDown(document.getElementById("yearList"), response.years);
		 	fillDropDown(document.getElementById("partyList"), response.parties);
		} else {
	 		fillDropDown(document.getElementById("districtList"), response.districts);			
	 		document.getElementById("districtList").disabled= false;  	 			 		
		}
    }
    
    function fillDropDown(selectbox, data){
          selectbox.options.length = 0;
          for( var counter = 0 ; counter < data.length ; counter++ ) {
        	  if(data[counter].id == undefined){
                  addOption(selectbox, data[counter], data[counter]);
              } else {
          		  addOption(selectbox, data[counter].name, data[counter].id);
              }
     	}
    }
    
 	function addOption(selectbox,text,value){
 			var optn = document.createElement("OPTION");
 			optn.text = text;
 			optn.value = value;
 			selectbox.options.add(optn);
 	}

 	function doAjax(param){
 		callAjax("type="+param);
 	}

 	function getDistricts(level){
 	 	if(level == 2){
	 	 	var index = document.getElementById("stateList").selectedIndex;
	 	 	var stateId = document.getElementById("stateList").options[index].value;
			callAjax("stateId="+stateId);
 	 	} else {
 	 		document.getElementById("districtList").disabled= true;  
 	 	}
 	}

 	function fetchDistricts(){
 	 	var reportLevels = document.getElementsByName("1");
 	 	for(var i=0; i < reportLevels.length; i++){
 	 	 	if(reportLevels[i].checked){
 	 	 		getDistricts(i+1);
 	 	 	}
 	 	}
 	}