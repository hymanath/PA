
var message_paginator = {
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
			{	//alert("candidateMess result  "+o.responseText);				
				results = YAHOO.lang.JSON.parse(o.responseText);
				//alert("In Custom##"+results[0].totalResultsCount);
				if(results.length>0)
				   this.totalRecords = parseInt(results[0].totalResultsCount);	
				else
                   this.totalRecords =0;
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
		
		
		//alert("paginator      "+this.paginatorElmt);
       //var elements = $("."+this.paginatorElmt);
		
		/*if(elements.length == 0)
		{			
			return;
		}*/
		/*var elements =this.paginatorElmt;
		  alert(elements+"1"+elements.length);
		  if(elements.length == 0)
		  {  	alert("hi");		
			    return;
		  }*/
		//alert("2");
		var paginatorElmt = document.createElement('Div');
		//alert("3");
		paginatorElmt.setAttribute("class","paginatorElmtClass");
		//alert(this.totalRecords);
		//alert(this.resultsCount);
		var iteration = Math.ceil(this.totalRecords/this.resultsCount);		
		var countIndex = this.startIndex;
		var str = '';

		if(iteration > 1)
		{
			for(var i=1; i<=iteration; i++)
			{	
				//alert("i="+i);
				str += '<a href="javascript:{}" onclick="message_paginator.doAjaxCall('+countIndex+')">'+i+'</a>';
				countIndex+=this.resultsCount;
			}
		}
		/*paginatorElmt.innerHTML = str;
		alert(str);
                for(var i=0; i<elements.length; i++)
		{
			elements[i].appendChild(paginatorElmt);
		}
        */
	
		if(document.getElementById("message_paginator_class")!=null)
		    document.getElementById("message_paginator_class").innerHTML = str;
		
	}
};
