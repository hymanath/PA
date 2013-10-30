 function getBoothWiseCompleteInfo()
 {
	$("#ajaxImg").css("display","block");
	    var jObj=
		{
			constituencyId			: constituencyId,
			publicationDateId		: toPublicationDateId,
			locationType			: locationType,
			locationValue			: locationValue,
			task:"getBoothWiseCompleteInfo"
	};
	  var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	  var url = "boothWiseCompleteInfoAction.action?"+rparam;	

	callAjax(jObj,url);
	
	}

	function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									
								 if(jsObj.task == "getBoothWiseCompleteInfo")
								
									showBoothWiseCompleteInfo(myResults,jsObj);
									else if(jsObj.task == "getVotersInAFamilyByConstituencyId")
								{
									setTimeout($.unblockUI, 200);
									buildFamilyInfo(myResults,jsObj.partNo);
								}
							
								
							}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}

	function showBoothWiseCompleteInfo(result,jsObj)
	{
		$("#ajaxImg").css("display","none");
		$("#boothWiseInfoDiv").html('');
		if(result != null)
		{
		 var str = '';
			for(var i in result)
			{
				str +='<div class="widget blue whitegloss voterInfoDiv">';
				str +='<div><h2>Voters Info Report For '+result[i].partNo+' Booth</h2></div>';
				str +='<div id="boothWiseVoterInfoBasicDiv">';
				str+='<p><span class="fontStyle">Booth :</span> '+result[i].partNo+'</p>';
				if(result[i].villageCovered != null)
				 str+='<p><span class="fontStyle">Places Covered :</span> '+result[i].villageCovered+'</p>';
				 str+='<p><span class="fontStyle">Added :</span> '+result[i].addedCount+'';
				 str+='<span class="fontStyle">Deleted :</span> '+result[i].deletedCount+'';
				 str+='<span class="fontStyle">Moved :</span> '+result[i].movedCount+'';
				 str+='<span class="fontStyle">Relocated :</span> '+result[i].relocatedCount+'</p>';
				 str +='</div>';
				 if(result[i].movedVoterVOsList != null && result[i].movedVoterVOsList.length > 0)
				{
				   var movedList = result[i].movedVoterVOsList;
					str +='<div>Moved Voters Info Report For '+result[i].partNo+' Booth</div>';
					
					str +='<div><table id="movedTable" class="table table-bordered table-hover" border="1">';
					str +='<tr><th>Name</th>';
					str +='<th>Moved Booth</th>';
					str +='<th>Age</th>';
					str +='<th>Gender</th>';
					str +='<th>House No</th>';
					str +='<th>voterIDCardNo</th>';
					str +='<th>Relative Name</th>';
					str +='</tr>';
					for(var j=0;j<movedList.length;j++)
					{
						str +='<tr><td>'+movedList[j].firstName+'</td>';
						if(movedList[j].movedOrRelocatedPartNo != null)
						 str +='<td>'+movedList[j].movedOrRelocatedPartNo+'</td>';
						else
						 str +='<td> </td>';
						str +='<td>'+movedList[j].age+'</td>';
						str +='<td>'+movedList[j].gender+'</td>';
						
						if(movedList[j].houseNo != null)
						 str +='<td><a style="cursor:pointer;color:#3392C2;" title="Click Here to know All the Family Members In this House" onclick="getVotersInAFamilyByPublication('+movedList[j].movedOrRelocatedPartNo+','+fromPublicationDateId+','+toPublicationDateId+','+constituencyId+',\''+movedList[j].houseNo+'\')">'+movedList[j].houseNo+'</a></td>';
						else
						 str +='<td></td>';
						str +='<td>'+movedList[j].voterIDCardNo+'</td>';
						str +='<td>'+movedList[j].relativeName+'</td>';
						str +='</tr>';
					}
					str +='</table></div>';
					}
				
				if(result[i].relocatedVoterVOsList != null && result[i].relocatedVoterVOsList.length > 0)
				{
				  var relocatedList = result[i].relocatedVoterVOsList;
					str +='<div>Relocated Voters Info Report For '+result[i].partNo+' Booth</div>';
					str +='<div><table id="relocatedTable" class="table table-bordered table-hover" border="1">';
					str +='<tr><th>Name</th>';
					str +='<th>Relocated Booth</th>';
					str +='<th>Age</th>';
					str +='<th>Gender</th>';
					str +='<th>House No</th>';
					str +='<th>voterIDCardNo</th>';
					str +='<th>Relative Name</th>';
					str +='</tr>';
					for(var j=0;j<relocatedList.length;j++)
					{
						str +='<tr><td>'+relocatedList[j].firstName+'</td>';
						if(relocatedList[j].movedOrRelocatedPartNo != null)
						 str +='<td>'+relocatedList[j].movedOrRelocatedPartNo+'</td>';
						else
						 str +='<td> </td>';
						str +='<td>'+relocatedList[j].age+'</td>';
						str +='<td>'+relocatedList[j].gender+'</td>';
						
						if(relocatedList[j].houseNo != null)
							str +='<td><a style="cursor:pointer;color:#3392C2;" title="Click Here to know All the Family Members In this House" onclick="getVotersInAFamilyByPublication('+relocatedList[j].movedOrRelocatedPartNo+','+fromPublicationDateId+','+toPublicationDateId+','+constituencyId+',\''+relocatedList[j].houseNo+'\')">'+relocatedList[j].houseNo+'</a></td>';
						else
							str +='<td></td>';
						 str +='<td>'+relocatedList[j].voterIDCardNo+'</td>';
						 str +='<td>'+relocatedList[j].relativeName+'</td>';
						str +='</tr>';
					}
					str +='</table></div>';
					
				}
				str +='</div>';
			
			}
			$("#boothWiseInfoDiv").html(str);
		}
	}

	function getVotersInAFamilyByPublication(partNo,fromPublication,ToPublication,constituencyId,hNo){

	$.blockUI({ message: '<h4><img src="./images/icons/search.gif" />  Request Processing...</h4>',baseZ: 5000 }); 

//	$("#popupAjaxImage").show();
    var jsObj=
			{
					
				hno:hNo,
				fromPublication:fromPublication,
				ToPublication:ToPublication,
				partNo:partNo,
				constituencyId:constituencyId,
				task:"getVotersInAFamilyByConstituencyId"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInAFamilyByConstituencyId.action?"+rparam;						
		callAjax(jsObj,url);

}

function buildFamilyInfo(results,partNo)
{
	
		//$("#popupAjaxImage").hide();
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
	  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	  {
		var name = oData;
		var id= oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
					
	  };
     var votersResultColumnDefs = [ 		    	             
		    	           // {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"boothName",label:"Booth",sortable:true},
							{key:"mobileNo",label:"MobileNo",sortable:true}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothName"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("voterimpFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    $("#voterimpFamDtlsOuterPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 910,
            height: 450
           
        });
}