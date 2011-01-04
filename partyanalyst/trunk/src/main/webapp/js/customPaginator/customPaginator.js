
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
	doAjaxCall:function(start){

		var url = this.ajaxCallURL+"&startIndex="+start+"&resultsCount="+this.resultsCount;
		
		var callback = {			
	    success : function( o ) {
			try 
			{					
				results = YAHOO.lang.JSON.parse(o.responseText);
				this.totalRecords = parseInt(results.totalResultsCount);				
				this.callBackFunction();
				this.buildPaginator();
			}
			catch (e)
			{   		
				alert("Invalid JSON result" + e);   
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
		this.doAjaxCall(this.startIndex);
	},
	buildPaginator:function()
	{
		var elements = $("."+this.paginatorElmt);
		
		if(elements.length == 0)
		{			
			return;
		}

		var paginatorElmt = document.createElement('Div');
		paginatorElmt.setAttribute("class","paginatorElmtClass");
		var iteration = Math.ceil(this.totalRecords/this.resultsCount);		
		var countIndex = this.startIndex;
		var str = '';
		if(iteration > 1)
		{
			for(var i=1; i<=iteration; i++)
			{			
				str += '<a href="javascript:{}" onclick="custom_paginator.doAjaxCall('+countIndex+')">'+i+'</a>';
				countIndex+=this.resultsCount;
			}
		}
		paginatorElmt.innerHTML = str;
		
		for(var i=0; i<elements.length; i++)
		{
			elements[i].appendChild(paginatorElmt);
		}
	}
};
