
function callAjax(jsObj,url)
	{

		var callback =
		{			
			success : function( o )
			{
				try
				{ 
					myResults = YAHOO.lang.JSON.parse(o.responseText); 
					if (jsObj.task == "getPartyList")
					{
						buildSelectOptionVoforMuntiple(myResults,"whomPartysList","partyList","Party");	
					}
					else if (jsObj.task == "getCandidatesOfAParty")
					{
						fillSelectOptionsVO(myResults,jsObj.divId,"Candidate");	
					}
					else if (jsObj.task == "getBenefitList")
					{
						buildSelectOptionVoforMuntiple(myResults,"whomBenfitsList","benifitsList"," Benifits");	
					}
					else if (jsObj.task == "getKeywordsList")
					{
						buildKeywords(myResults);	
					}
					else if (jsObj.task == "partyGallariesForUplaod")
					{
						buildCategoriesList(myResults);	
					}
					
					else if (jsObj.task == "getSource")
					{
						fillSelectOptionsVO(myResults,jsObj.divId,"Source");	
					}else if(jsObj.task == "getAnalysedData" || jsObj.task == "getAnalysedData1"){
					  if(myResults.buildMethod == "first"){
					    buildSearchDataTableForLvlOne(myResults,jsObj);
					  }else if(myResults.buildMethod == "second"){
						buildSearchDataTableForLvlTwo(myResults,jsObj);
					  }else if(myResults.buildMethod == "third"){
						buildSearchDataTableForLvlThree(myResults,jsObj);
					  }
					  else if(myResults.buildMethod == "six")
					  {
						buildSearchDataTableForLvlSix(myResults,jsObj)
					  }
					  $("#submitDataImg").hide();
					}/*else if(jsObj.task == "getAnalysedData1"){
					  if(myResults.buildMethod == "first"){
					    buildSearchDataTableForLvlOne(myResults,jsObj);
					  }else if(myResults.buildMethod == "second"){
						buildSearchDataTableForLvlTwo(myResults,jsObj);
					  }else if(myResults.buildMethod == "third"){
						buildSearchDataTableForLvlThree1(myResults,jsObj);
					  }
					 
					   $("#submitDataImg").hide();
					}*/				
				}
				catch(e)
				{   
				 $("#submitDataImg").hide();
				}  
			},
			scope : this,
			failure : function( o )
			{
				
			}
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
 function buildSearchDataTableForLvlOne(result,jsObj){
	 var str ="";
	 var id ="";
	 if(jsObj.task == "getAnalysedData"){
	   id = "responseTable";
	 }else{
	   id = "responseTable1";
	 }
	 $("#"+id).css("display","block");
    if(result.subList != null && result.subList.length > 0){
	   str+="<table class='analysisResult table table-bordered table-striped table-hover'>";
	   str+="  <thead><tr>";
	   for(var title in result.levels){
	    str+="    <th>"+result.levels[title]+"</th>";
	   }
	   str+="    <th>News Count</th>";
	   str+="  </tr></thead>";
	   str+="  <tbody>";
	   
	     for(var i in result.subList){//List<Location>
			  if(result.subListPresent){
			   str+="<tr><td rowspan='"+result.subList[i].rowSpan+"'>"+result.subList[i].name+"</td>";
			  }
			  for(var m in result.subList[i].subList){
			     if(result.subList[i].subListPresent){
						if(m == 0){
						  if(result.subListPresent){
						    str+="<td rowspan='"+result.subList[i].subList[m].rowSpan+"'>"+result.subList[i].subList[m].name+"</td>";
						  }else{
						    str+="<tr><td rowspan='"+result.subList[i].subList[m].rowSpan+"'>"+result.subList[i].subList[m].name+"</td>";
						  }
						}else{
						   str+="<tr><td rowspan='"+result.subList[i].subList[m].rowSpan+"'>"+result.subList[i].subList[m].name+"</td>";
						}
				    }
			  
		       for(var j in result.subList[i].subList[m].subList){//List<Candidate>
			        if(result.subList[i].subList[m].subListPresent){
						if(j == 0){
						  if(result.subListPresent || result.subList[i].subListPresent){
						    str+="<td rowspan='"+result.subList[i].subList[m].subList[j].rowSpan+"'>"+result.subList[i].subList[m].subList[j].name+"</td>";
						  }else{
						    str+="<tr><td rowspan='"+result.subList[i].subList[m].subList[j].rowSpan+"'>"+result.subList[i].subList[m].subList[j].name+"</td>";
						  }
						}else{
						   str+="<tr><td rowspan='"+result.subList[i].subList[m].subList[j].rowSpan+"'>"+result.subList[i].subList[m].subList[j].name+"</td>";
						}
				    }
				    for(var k in result.subList[i].subList[m].subList[j].subList){//List<Source>
				       if(result.subList[i].subList[m].subList[j].subListPresent){
						  if(k == 0){
							  if(result.subList[i].subList[m].subListPresent || result.subList[i].subListPresent || result.subListPresent){
								str+="<td rowspan='"+result.subList[i].subList[m].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[m].subList[j].subList[k].name+"</td>";
							  }else{
								str+="<tr><td rowspan='"+result.subList[i].subList[m].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[m].subList[j].subList[k].name+"</td>";
							  }
						  }else{
						      str+="<tr><td rowspan='"+result.subList[i].subList[m].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[m].subList[j].subList[k].name+"</td>";
						  }
                      }else if(!(result.subList[i].subList[m].subListPresent || result.subList[i].subListPresent || result.subListPresent)){
                         str+="<tr>";
                      }					  
					   for(var l in result.subList[i].subList[m].subList[j].subList[k].subList){//List<CatKey>
					        var obj = result.subList[i].subList[m].subList[j].subList[k].subList[l];
						  if(l == 0){
						    str+="<td>"+result.subList[i].subList[m].subList[j].subList[k].subList[l].name+"</td><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[m].subList[j].subList[k].subList[l].total+"</a></td></tr>";
						  }else{
						    str+="<tr><td>"+result.subList[i].subList[m].subList[j].subList[k].subList[l].name+"</td><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[m].subList[j].subList[k].subList[l].total+"</a></td></tr>";
						  }
					   }
					  
				    }
				  
			   }
		    }
	   }
	   str+="  </tbody>";
	   str+="</table>";
	   $("#"+id).html(str);
	 }else{
	   $("#"+id).html("<span  style='margin-left:340px;font-weight:bold;'>No News Exists With Your Search Criteria</span>");
	 } 
   }
    function buildSearchDataTableForLvlTwo(result,jsObj){
         var str ="";
		 var str ="";
		 var id ="";
		 if(jsObj.task == "getAnalysedData"){
		   id = "responseTable";
		 }else{
		   id = "responseTable1";
		 }
		 $("#"+id).css("display","block");
     if(result.subList != null && result.subList.length > 0){
	   str+="<table class='analysisResult table table-bordered table-striped table-hover'>";
	   str+="  <thead><tr>";
	   for(var title in result.levels){
	    str+="    <th>"+result.levels[title]+"</th>";
	   }
	   str+="    <th>News Count</th>";
	   str+="  </tr></thead>";
	   str+="  <tbody>";
	   
	   for(var i in result.subList){//List<Location>
		  if(result.subListPresent){
		   str+="<tr><td rowspan='"+result.subList[i].rowSpan+"'>"+result.subList[i].name+"</td>";
		  }
		 else{
		  str+="<tr>";
		 }
		 for(var j in result.subList[i].subList){//List<Source>
		 var obj = result.subList[i].subList[j];
		  if(result.subList[i].subListPresent){
			  if(j == 0){
				str+="<td>"+result.subList[i].subList[j].name+"</td><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].total+"</a></td></tr>";
			  }else{
				str+="<tr><td>"+result.subList[i].subList[j].name+"</td><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].total+"</a></td></tr>";
			  }
		  }else{
		      if(j == 0){
				str+="<td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].total+"</a></td></tr>";
			  }else{
				str+="<tr><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].total+"</a></td></tr>";
			  }
		  }
	    }
	   }
	   str+="  </tbody>";
	   str+="</table>";
	   $("#"+id).html(str);
	 }else{
	   $("#"+id).html("<span  style='margin-left:340px;font-weight:bold;'>No News Exists With Your Search Criteria</span>");
	 }
    }	
	function buildSearchDataTableForLvlThree(result,jsObj){
		
		 var str ="";
		 var str ="";
		 var id ="";
		 if(jsObj.task == "getAnalysedData"){
		   id = "responseTable";
		 }else{
		   id = "responseTable1";
		 }
		 $("#"+id).css("display","block");
	     if(result.subList != null && result.subList.length > 0){
		   str+="<table class='analysisResult table table-bordered table-striped table-hover'>";
		   str+="  <thead><tr>";
		   for(var title in result.levels){
		    str+="    <th>"+result.levels[title]+"</th>";
		   }
		   str+="    <th>News Count</th>";
		   str+="  </tr></thead>";
		   str+="  <tbody>";
		   
		     for(var i in result.subList){//List<Location>
				  if(result.subListPresent){
				   str+="<tr><td rowspan='"+result.subList[i].rowSpan+"'>"+result.subList[i].name+"</td>";
				  }
			       for(var j in result.subList[i].subList){//List<SouCandidate>
				        if(result.subList[i].subListPresent){
							if(j == 0){
							  if(result.subListPresent){
							    str+="<td rowspan='"+result.subList[i].subList[j].rowSpan+"'>"+result.subList[i].subList[j].name+"</td>";
							  }else{
							    str+="<tr><td rowspan='"+result.subList[i].subList[j].rowSpan+"'>"+result.subList[i].subList[j].name+"</td>";
							  }
							}else{
							   str+="<tr><td rowspan='"+result.subList[i].subList[j].rowSpan+"'>"+result.subList[i].subList[j].name+"</td>";
							}
					    }
					    for(var k in result.subList[i].subList[j].subList){//List<Candidate>
					       if(result.subList[i].subList[j].subListPresent){
							  if(k == 0){
								  if(result.subList[i].subListPresent || result.subListPresent){
									str+="<td rowspan='"+result.subList[i].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[j].subList[k].name+"</td>";
								  }else{
									str+="<tr><td rowspan='"+result.subList[i].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[j].subList[k].name+"</td>";
								  }
							  }else{
							      str+="<tr><td rowspan='"+result.subList[i].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[j].subList[k].name+"</td>";
							  }
	                       }else if(!(result.subList[i].subListPresent || result.subListPresent)){
	                          str+="<tr>";
	                       }					  
						   for(var l in result.subList[i].subList[j].subList[k].subList){//List<Source>
						       var obj = result.subList[i].subList[j].subList[k].subList[l];
							  if(result.subList[i].subList[j].subList[k].subListPresent){
								  if(l == 0){
									str+="<td>"+result.subList[i].subList[j].subList[k].subList[l].name+"</td><td><a href='javascript:void(0)' title='Click Here To See All News' onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].subList[k].subList[l].total+"</a></td></tr>";
								  }else{
									str+="<tr><td>"+result.subList[i].subList[j].subList[k].subList[l].name+"</td><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].subList[k].subList[l].total+"</a></td></tr>";
								  }
							  }else{
								  if(l == 0){
									str+="<td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].subList[k].subList[l].total+"</a></td></tr>";
								  }else{
									str+="<tr><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].subList[k].subList[l].total+"</a></td></tr>";
								  }
							  }
						  }
					    }
					  
				   }
			 
		   }
		   str+="  </tbody>";
		   str+="</table>";
		   $("#"+id).html(str);
		 }else{
		   $("#"+id).html("<span  style='margin-left:340px;font-weight:bold;'>No News Exists With Your Search Criteria</span>");
		 } 
	    }
		/*function buildSearchDataTableForLvlThree1(result){
		 $("#responseTable1").css("display","block");
		 var str ="";
	     if(result.subList != null && result.subList.length > 0){
		   str+="<table class='analysisResult table table-bordered table-striped table-hover'>";
		   str+="  <thead><tr>";
		   for(var title in result.levels){
		    str+="    <th>"+result.levels[title]+"</th>";
		   }
		   str+="    <th>News Count</th>";
		   str+="  </tr></thead>";
		   str+="  <tbody>";
		   
		     for(var i in result.subList){//List<Location>
				  if(result.subListPresent){
				   str+="<tr><td rowspan='"+result.subList[i].rowSpan+"'>"+result.subList[i].name+"</td>";
				  }
			       for(var j in result.subList[i].subList){//List<SouCandidate>
				        if(result.subList[i].subListPresent){
							if(j == 0){
							  if(result.subListPresent){
							    str+="<td rowspan='"+result.subList[i].subList[j].rowSpan+"'>"+result.subList[i].subList[j].name+"</td>";
							  }else{
							    str+="<tr><td rowspan='"+result.subList[i].subList[j].rowSpan+"'>"+result.subList[i].subList[j].name+"</td>";
							  }
							}else{
							   str+="<tr><td rowspan='"+result.subList[i].subList[j].rowSpan+"'>"+result.subList[i].subList[j].name+"</td>";
							}
					    }
					    for(var k in result.subList[i].subList[j].subList){//List<Candidate>
					       if(result.subList[i].subList[j].subListPresent){
							  if(k == 0){
								  if(result.subList[i].subListPresent || result.subListPresent){
									str+="<td rowspan='"+result.subList[i].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[j].subList[k].name+"</td>";
								  }else{
									str+="<tr><td rowspan='"+result.subList[i].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[j].subList[k].name+"</td>";
								  }
							  }else{
							      str+="<tr><td rowspan='"+result.subList[i].subList[j].subList[k].rowSpan+"'>"+result.subList[i].subList[j].subList[k].name+"</td>";
							  }
	                       }else if(!(result.subList[i].subListPresent || result.subListPresent)){
	                          str+="<tr>";
	                       }					  
						   for(var l in result.subList[i].subList[j].subList[k].subList){//List<Source>
						       var obj = result.subList[i].subList[j].subList[k].subList[l];
							  if(result.subList[i].subList[j].subList[k].subListPresent){
								  if(l == 0){
									str+="<td>"+result.subList[i].subList[j].subList[k].subList[l].name+"</td><td><a href='javascript:void(0)' title='Click Here To See All News' onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].subList[k].subList[l].total+"</a></td></tr>";
								  }else{
									str+="<tr><td>"+result.subList[i].subList[j].subList[k].subList[l].name+"</td><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].subList[k].subList[l].total+"</a></td></tr>";
								  }
							  }else{
								  if(l == 0){
									str+="<td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].subList[k].subList[l].total+"</a></td></tr>";
								  }else{
									str+="<tr><td><a href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedNews(\""+obj.sourceCandId+"\",\""+obj.destiCandId+"\",\""+obj.sourcePartyId+"\",\""+obj.destiPartyId+"\",\""+obj.locationLvl+"\",\""+obj.locationId+"\",\""+obj.sourceId+"\",\""+obj.categoryId+"\",\""+obj.keywordId+"\",\""+obj.benifitsFor+"\",\""+obj.sourceBenifitId+"\",\""+obj.destiBenifitId+"\",\"1\");'>"+result.subList[i].subList[j].subList[k].subList[l].total+"</a></td></tr>";
								  }
							  }
						  }
					    }
					  
				   }
			 
		   }
		   str+="  </tbody>";
		   str+="</table>";
		   $("#responseTable1").html(str);
		 }else{
		   $("#responseTable1").html("<span  style='margin-left:340px;font-weight:bold;'>No News Exists With Your Search Criteria</span>");
		 } 
	    }*/
		
		function buildSearchDataTableForLvlSix(result,jsObj)
		{
		    var str ="";
			 var id ="";
			 if(jsObj.task == "getAnalysedData"){
			   id = "responseTable";
			 }else{
			   id = "responseTable1";
			 }
			 $("#"+id).css("display","block");
			$("#"+id).html(str);
			if(result.subList != null && result.subList.length > 0)
			{
				
				str += '<table class="analysisResult table table-bordered table-striped table-hover">';
				str += '<thead><tr>';
				str += '<th>Who</th>';
				str += '<th>Whom</th>';
				str += '<th>Count</th>';
				str += '</tr></thead><tbody>';
				for(var i in result.subList)
				{
					str += '<tr>';
					str += '<td>'+result.subList[i].sorceCandName+'</td>';
					str += '<td>'+result.subList[i].destCandName+'</td>';
					str += '<td><a  style="cursor: pointer;" onClick = "getClickedNews('+result.subList[i].sourceCandId+','+result.subList[i].destiCandId+','+result.subList[i].sourcePartyId+','+result.subList[i].destiPartyId+',\'\',\'\',\'\',\'\',\'\',\'\',\'\',\'\',\'2\');">'+result.subList[i].total+'</a></td>';
					str += '</tr>';
				}
				str += '</tbody><table>';
				$("#"+id).html(str);
			}else{
		      $("#"+id).html("<span  style='margin-left:340px;font-weight:bold;'>No News Exists With Your Search Criteria</span>");
		   } 
		}
		
	/* function openForSelectdNews(sourcePartyId,destPartyId,soureCandId,destinationCandId)
	{
		var jsObj=
		{
			sourcePartyId     : sourcePartyId,
			destPartyId       : destPartyId,
			soureCandId       : soureCandId,
			destinationCandId : destinationCandId,
			task:'openForSelectdNews'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getPartiesListAction.action?"+rparam;
		callAjax(jsObj, url);
	} */
	function getPartiesList()
	{
		var jsObj=
			{
				partySelectBoxId:"partiesList",
				partiesListForWhome:"partiesListForWhome",
				task:'getPartyList'
			};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getPartiesListAction.action?"+rparam;
		callAjax(jsObj, url);
	}
	
	function buildSelectOptionVoforMuntiple(results,divId1,divId2,name)
	{
		$('#'+divId1+'').find('option').remove();
		$('#'+divId1+'').append('<option value="0">Select '+name+'</option>');
		$('#'+divId2+'').find('option').remove();
		$('#'+divId2+'').append('<option value="0">Select '+name+'</option>');
		if(results != null)
		{
			for(var i in results)
			{
				$('#'+divId1+'').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
				$('#'+divId2+'').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
			}
			
		}
	}
	
	function getCandidatesOfSelectedParty(partyId,divId)
	{
		$('#list1').find('option').remove();
		$('#candidateAjaxImg').show();
		
			var jsObj = {
				partyId :partyId,
				divId   : divId,
				task : "getCandidatesOfAParty"	
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCandidatesOfAParty.action?"+rparam;
		callAjax(jsObj,url);
	}
	
	function  getBenefitList()
	{
	  var jsObj = {
				 task : "getBenefitList"	
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getBenefitListAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function  getKeyWorsList()
	{
	  var jsObj = {
				 task : "getKeywordsList"	
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getKeywordsListAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function fillSelectOptionsVO(results,divId,type)
	{
		$('#'+divId+'').find('option').remove();
		if(!(type == "Source"))
		{
			$('#'+divId+'').append('<option value="0">Select '+type+'</option>');
		}			
		if(results != null)
		{
			for(var i in results)
			{
				$('#'+divId+'').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
			}
			
		}
		if(type == "Source")
		{
			$("#newsSourceId").multiselect({	
					multiple: true,
					selectedList: 1,
					hide: "explode"	
			}).multiselectfilter({
				//header:"Select Source"    
			});
		}
		
	}
	
	function unCheckCandidateCheckBox()
	{
		$('#candidateCheckId').attr('checked', false); 
		if($('#keywordscheckId').is(':checked')){
			$('#keywordCategTitle').html("Select Keyword");
			getKeyWorsList();
			$('#keywordsDiv').show();
		}else{
		  $('#keywordsDiv').hide();
		}
	}
	
	function unCheckKeywordsCheckBox()
	{
		$('#keywordscheckId').attr('checked', false); 
		if($('#candidateCheckId').is(':checked')){
			$('#keywordCategTitle').html("Select Category");
			getPartyGallariesForUplaod();
			$('#keywordsDiv').show();
		}else{
		   $('#keywordsDiv').hide();
		}
	}
	
	function getKeyWorsList()
	{
		$.ajax({
		type: "GET",
		url: "getKeywordsListAction.action",
		data: { task:"getKeywordsList" }
		})
		.done(function( result ) {
		$('#keywordsList').find('option').remove();
		//$('#keywordsList').append('<option value="0">Select Keyword</option>');
		$.each(result,function(index,value){
			$('#keywordsList').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		
			$("#keywordsList").multiselect({	
					multiple: true,
					selectedList: 1,
					hide: "explode"	
			}).multiselectfilter({
				header:"Select Keyword"    
			});
		});
	}
	function getPartyGallariesForUplaod()
	{
		var partyId=872;
		var jsObj =
			{ 
				partyId : partyId,
				contentType : "News Gallary",
				type:"whomegallaryId",
				task : "partyGallariesForUplaod"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartyGallariesForUplaodPhotoAction.action?"+rparam;
		callAjax(jsObj,url);
	}
	
	function buildCategoriesList(result)
	{
		$('#keywordsList').find('option').remove();
		//$('#keywordsList').append('<option value="0">Select Categoery</option>');
		$.each(result,function(index,value){
			$('#keywordsList').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		
			$("#keywordsList").multiselect({	
					multiple: true,
					selectedList: 1,
					hide: "explode"	
			}).multiselectfilter({
				header:"Select Keyword"    
			});
	}

function getSource()
{
	var timeST = new Date().getTime();	
	
		var jsObj = {
			time : timeST,
			selectOptionId :"filesourceId0",
			divId : "newsSourceId",
			task : "getSource"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventsGallariesForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
	
}

function getRespectedLocationValues(loc)
{
	 if(loc == 1){
		showHideLocations(true,false,false);
	 }else if(loc == 2){
		showHideLocations(false,true,false);
	 }else if(loc == 3){
		showHideLocations(false,false,true);
	 }else {
		$(".LocationLevelId").show();
		$(".districtSelReport").hide();
		$(".parliamSelReport").hide();
		$(".assembSelReport").hide();
	 }
}
function showHideLocations(dist,pc,ac)
{
	 if(dist){
	  $(".districtSelReport").show();
	  $(".LocationLevelId").hide();
	  //buildMultiSelectBox("districtSelReport");
	 }else{
	  $(".districtSelReport").hide();
	 }
	 if(pc){
	  $(".parliamSelReport").show();
	  $(".LocationLevelId").hide();
	  //buildMultiSelectBox("parliamSelReport");
	 }else{
	  $(".parliamSelReport").hide();
	 }
	 if(ac){
	  $(".assembSelReport").show();
	  $(".LocationLevelId").hide();
	  //buildMultiSelectBox("assembSelReport");
	 }else{
	  $(".assembSelReport").hide();
	 }
}

function buildMultiSelectBox(divId)
{	
	$('.'+divId+'').multiselect({	
					multiple: true,
					selectedList: 10,
					hide: "explode"	
			}).multiselectfilter({
				//header:"Select Keyword"    
			});
	
}

$(document).ready(function(){

	$('#todateId').datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date()
	});
	$('#fromDateId').datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date()
	});
	

		$('#reportsTabId').addClass('menuActive');
		
	getPartiesList();
	getBenefitList();
	getSource();
		
	$('#districtSelReportId').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({
		//header:"Select District"    
	});
			
	$('#parliamSelReportId').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({
		//header:"Select Keyword"    
	});
	
	$('#assembSelReportId').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({
		//header:"Select Keyword"    
	});

});

function getAnalysisData()
{
    $("#responseTable").html("");
	$("#responseTable1").html("");
	$("#responseTable").hide();
	$("#responseTable1").hide();
    $("#errormessageDiv").hide();
	var fromdate = $("#fromDateId").val();
	var todate   = $("#todateId").val();
	var whoPartyId   = $("#partyList option:selected").val();
	var whomPartyId   = $("#whomPartysList option:selected").val();
	var whoCandidateId   = $("#candidateId option:selected").val();
	var whomCandidateId   = $("#whomCandidatesList option:selected").val();
	var whoBenfitId   = $("#benifitsList option:selected").val();
	var whomBenfitId   = $("#whomBenfitsList option:selected").val();
	var locationLevelId = $("#locationLevelId option:selected").val();
	var locationLevelValue = "";
	var selectedLocationvalues = "";
	var advanceView = booleanVal;
	
	if(locationLevelId == 0)
	{
		locationLevelValue = 0;
	}
	else if(locationLevelId == 1)
	{
		selectedLocationvalues = $("#districtSelReportId").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	}
	else if(locationLevelId == 2)
	{
		selectedLocationvalues = $("#parliamSelReportId").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	}
	else if(locationLevelId == 3)
	{
		selectedLocationvalues = $("#assembSelReportId").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	}
	for(var i in selectedLocationvalues)
	{
		locationLevelValue = locationLevelValue+""+selectedLocationvalues[i]+",";
	}
	
	var selected_values = $("#newsSourceId").multiselect("getChecked").map(function(){
	return this.value;    
	}).get();
	var newsSourceId = "";
	for(var i in selected_values){
	newsSourceId = newsSourceId+""+selected_values[i]+",";
	}
	
	var KeyWordsList = "";
	
	if($('#keywordscheckId').is(':checked') || $('#candidateCheckId').is(':checked'))
	{
		var selected_values = $("#keywordsList").multiselect("getChecked").map(function(){
		return this.value;    
		}).get();
		for(var i in selected_values){
		KeyWordsList = KeyWordsList+""+selected_values[i]+",";
		}
	}
	if(KeyWordsList.length > 0){
	  KeyWordsList = KeyWordsList.substring(0, KeyWordsList.length - 1);
	}
	if(newsSourceId.length > 0){
	  newsSourceId = newsSourceId.substring(0, newsSourceId.length - 1);
	}
	if(locationLevelValue.length > 0){
	  locationLevelValue = locationLevelValue.substring(0, locationLevelValue.length - 1);
	}
	
	var checkedType = "";
	var analyseCandidate = "";
	if($('#keywordscheckId').is(':checked')){
	  checkedType ="keyword";
	}
	if($('#candidateCheckId').is(':checked')){
	  checkedType ="category";
	}
	if($('#analyseCandidateSource').is(':checked') && $('#analyseCandidateDesti').is(':checked')){
	  analyseCandidate ="sourceDesti";
	}
	else if($('#analyseCandidateSource').is(':checked')){
	  analyseCandidate ="source";
	}
	else if($('#analyseCandidateDesti').is(':checked')){
	  analyseCandidate ="destination";
	}
	var timeST = new Date().getTime();
	
    if(fromdate == "" && todate=="" && whoPartyId == 0 && whomPartyId == 0 && whoCandidateId == 0 && whomCandidateId == 0 && locationLevelValue == 0 && newsSourceId == "" && KeyWordsList == "" && checkedType == "" && analyseCandidate == ""){
      $("#errormessageDiv").show();
	  return;
    }	
	$("#submitDataImg").show();
		var jsObj = {
			time : timeST,
			fromdate :fromdate,
			todate : todate,
			whoPartyId : whoPartyId,
			whomPartyId : whomPartyId,
			whoCandidateId : whoCandidateId,
			whomCandidateId : whomCandidateId,
			whoBenfitId : whoBenfitId,
			whomBenfitId : whomBenfitId,
			locationLevelId : locationLevelId,
			locationLevelValue : locationLevelValue,
			newsSourceId : newsSourceId,
			KeyWordsList : KeyWordsList,
			checkedType:checkedType,
			analyseCandidate:analyseCandidate,
			task : "getAnalysedData"	
		};
		var jsObj1 = {
			time : timeST,
			fromdate :fromdate,
			todate : todate,
			whoPartyId : whomPartyId,
			whomPartyId : whoPartyId,
			whoCandidateId : whomCandidateId,
			whomCandidateId : whoCandidateId,
			whoBenfitId : whomBenfitId,
			whomBenfitId : whoBenfitId,
			locationLevelId : locationLevelId,
			locationLevelValue : locationLevelValue,
			newsSourceId : newsSourceId,
			KeyWordsList : KeyWordsList,
			checkedType:checkedType,
			analyseCandidate:analyseCandidate,
			task : "getAnalysedData1"	
		};
	getAnalysisData1(jsObj1);
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAnalysedDataAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getAnalysisData1(jsObj)
{
    if(booleanVal){
		return;			
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAnalysedDataAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getClickedNews(sourceCandId,destiCandId,sourcePartyId,destiPartyId,locationLvl,locationId,sourceId,categoryId,keywordId,benifitsFor,sourceBenifitId,destiBenifitId,type){
 var startDate = $("#fromDateId").val();
	var endDate   = $("#todateId").val();
	if(sourceCandId == "null" || sourceCandId == null){
	  sourceCandId ='';
	}
	if(destiCandId == "null" || destiCandId == null){
     destiCandId = '';
    }
	if(sourcePartyId == "null" || sourcePartyId == null){
	 sourcePartyId = '';
	}
	if(destiPartyId == "null" || destiPartyId == null){
	 destiPartyId = '';
	}
	if(locationLvl == "null" || locationLvl == null){
	 locationLvl = '';
	}
	if(locationId == "null" || locationId == null){
	 locationId = '';
	}
	if(sourceId == "null" || sourceId == null){
	 sourceId = '';
	}
	if(categoryId == "null" || categoryId == null){
	 categoryId = '';
	}
	if(keywordId == "null" || keywordId == null){
	 keywordId = '';
	}
	if(benifitsFor == "null" || benifitsFor == null){
	 benifitsFor = '';
	}
	if(sourceBenifitId == "null" || sourceBenifitId == null){
		sourceBenifitId = '';
	}
	if(destiBenifitId == "null" || destiBenifitId == null){
		destiBenifitId = '';
	}
 var browser1 = window.open("getNewsAction.action?sourceCandId="+sourceCandId+"&destiCandId="+destiCandId+"&sourcePartyId="+sourcePartyId+"&destiPartyId="+destiPartyId+"&locationLvl="+locationLvl+"&locationId="+locationId+"&sourceId="+sourceId+"&categoryId="+categoryId+"&keywordId="+keywordId+"&benifitsFor="+benifitsFor+"&startDate="+startDate+"&endDate="+endDate+"&sourceBenifitId="+sourceBenifitId+"&destiBenifitId="+destiBenifitId+"&type="+type,"SearchNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}
function clearDate(id){
   $("#"+id).val('');
}
