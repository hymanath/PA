
var custom_paginator = {
	resultsCount:"",
	startIndex:"",
	ajaxCallURL:"",
	initialParams:"",
	resultsShown:"",
	callBackFunction:"",
	jsObj:{},
	results:{},
	totalRecords:"",
	paginatorElmt:"",		
	paginator:function(obj){
		this.startIndex = obj.startIndex;
		this.endIndex = obj.endIndex;
		this.ajaxCallURL = obj.ajaxCallURL;
		this.jsObj = obj.jsObj;
		this.callBackFunction = obj.callBackFunction;
		this.paginatorElmt = obj.paginatorElmt;
		this.resultsCount = obj.resultsCount;		
	},	
	doAjaxCall:function(start,eleIdClicked){

		var url = this.ajaxCallURL+"&startIndex="+start+"&resultsCount="+this.resultsCount;
		
		var callback = {			
	    success : function( o ) {
			try 
			{					
				results = YAHOO.lang.JSON.parse(o.responseText);
				
				if(results != null && results.length>0)
				    this.totalRecords = parseInt(results[0].totalResultsCount);	
				else
                     this.totalRecords = 0;
				this.callBackFunction();
				this.buildPaginator(eleIdClicked);
			}
			catch (e)
			{   		
				//alert("Invalid JSON result" + e);   
			}  
		},
		scope : this,
		failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				  }
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	},
	initialize:function (){		
		this.doAjaxCall(this.startIndex,0);
	},
	buildPaginator:function(eleIdClicked)
	{
		//alert(eleIdClicked + "TEST");
		var paginatorElmt = document.createElement('Div');
		paginatorElmt.setAttribute("class","paginatorElmtClass");
		var iteration = Math.ceil(this.totalRecords/this.resultsCount);		
		var countIndex = this.startIndex;
		var str = '';

		if(iteration > 1)
		{
			for(var i=1; i<=iteration; i++)
				{
					var eleid1="a"+i;
				str += '<a href="javascript:{}" id="a'+i+'" onclick="custom_paginator.doAjaxCall('+countIndex+',\''+eleid1+'\')">'+i+'</a>';
				countIndex+=this.resultsCount;
			}
		}
		
		if(document.getElementById("custom_paginator_class")!=null)	
     	  document.getElementById("custom_paginator_class").innerHTML = str;
	
		if(eleIdClicked==0){$("#custom_paginator_class a:first").addClass("btn btn-warning");}
		else {$("#"+eleIdClicked).addClass("btn btn-warning"); }
		
	}
};
