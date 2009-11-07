	function getConstituenciesList(form,url){
		if(form.partyName.value==0 || form.electionType.value==0 || form.electionYear.value=="0")
			return;			
		
		getConstituencies(form,url);
	}

	function getConstituencies(form,url)
	{
		
				
 		var callback = {	
 					  	
 		               success : function( o ) {
							try {								
								var results = YAHOO.lang.JSON.parse(o.responseText); 
								buildConstituencySelect(form,results);
								document.getElementById("constituencyRow").style.display="";
																
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}
							  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };
 		YAHOO.util.Connect.setForm(form);
 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
	}
	
		
	function buildConstituencySelect(form,results){
		
		for(i=0;i<results.length;i++)
		{			
			addOption(form.constituencyName,results[i].name, results[i].id);
		}
		
	}

	function addOption(selectbox,text,value )
	{
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		selectbox.options.add(optn);
	}	

	function clearOptions(selectbox )
	{	
		selectbox.options.length = 1;
	}