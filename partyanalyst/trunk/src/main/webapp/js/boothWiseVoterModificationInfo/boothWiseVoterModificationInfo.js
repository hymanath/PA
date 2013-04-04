function getBoothWiseVoterModificationDetails()
{
	$('#subLevelAjaxImageDiv').css('display','block');

	var jsObj=
		{
			locationType:locationType,	
			locationValue:locationValue,
			fromPublicationDateId:fromPublicationDateId,
			toPublicationDateId:toPublicationDateId,
			constituencyId:constituencyId,
			task:"getBoothWiseVoterModificationDetails"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getBoothWiseVoterModificationDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);

}


function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "getBoothWiseVoterModificationDetails")
								      buildBoothWiseVoterInfo(myResults,jsObj);
								
								}
								catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}


function buildBoothWiseVoterInfo(myResults,jsObj)
{
  $('#subLevelAjaxImageDiv').css('display','none');

	if(myResults.modifiedVotersList == null || myResults.modifiedVotersList.length == 0)
	{
		$('#subLevelsMainDiv').css('display','none');
		return;
	}

	  $('#subLevelsMainDiv').css('display','inline-block');
	  $('#subLevelDiv').html('');
	if(myResults.modifiedVotersList.length == 0 && myResults.modifiedLocalBodyVotersList.length == 0 && myResults.modifiedVotersList != null && myResults.modifiedLocalBodyVotersList != null)
	 return false;

     var str='';
	 str +='<h4>Booth Wise Newly added / deleted voters info</h4>';
	 str+='<table class="voterInfoTable" id="subLevelVotersTable">';
	 str+='<thead>';
	 str +='<tr>';
	 str +='<th rowSpan="2">Booth Part No</th>';
	 str +='<th colspan="'+myResults.modifiedVotersList[0].selectOptionVOsList.length+'">Voters</th>';
     str +='<th colspan="4">Total Voters </th>';
	 str +='<th colspan="4">Male Voters </th>';
	 str +='<th colspan="4">Female Voters</th>';
	 str +='</tr>';
	 str +='<tr>';
	  var publicationName = myResults.modifiedVotersList[0].selectOptionVOsList;
		 for(var j in publicationName)
			 str +='<th>'+publicationName[j].name+'</th>';
			
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		str +='<th>Moved</th>';
		str +='<th>Relocated</th>';
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		str +='<th>Moved</th>';
		str +='<th>Relocated</th>';
		str +='<th>Added</th>';
		str +='<th>Deleted</th>';
		str +='<th>Moved</th>';
		str +='<th>Relocated</th>';
	str +='</tr>';
    str+='</thead>';
	 str+='<tbody>';
    
	for(var i=0;i<myResults.modifiedVotersList.length;i++)
 	 {
		var publicationList = myResults.modifiedVotersList[i].selectOptionVOsList; 

		var locationType1 = myResults.modifiedVotersList[i].locationType;
		var locationId1 =  myResults.modifiedVotersList[i].id;
		  str+='<tr>';
		  str+='<td style="text-align:left;">'+myResults.modifiedVotersList[i].name+'</td>';

			for(var k in publicationList)
			  str +='<td>'+publicationList[k].id+'</td>';
		
		
		str+='<td>'+myResults.modifiedVotersList[i].addedCount+'</td>';
		str+='<td>'+myResults.modifiedVotersList[i].deletedCount+'</td>';
		str+='<td>'+myResults.modifiedVotersList[i].movedCount+'</td>';
        str+='<td>'+myResults.modifiedVotersList[i].relocatedCount+'</td>';
		str+='<td>'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
		str+='<td>'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
		str+='<td>'+myResults.modifiedVotersList[i].maleVotersMoved+'</td>';
		str+='<td>'+myResults.modifiedVotersList[i].maleVotersRelocated+'</td>';
        str+='<td>'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		str+='<td>'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';
        str+='<td>'+myResults.modifiedVotersList[i].femaleVotersMoved+'</td>';
		str+='<td>'+myResults.modifiedVotersList[i].femaleVotersRelocated+'</td>';
			

		/* if(myResults.modifiedVotersList[i].addedCount!=0)
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'Added\')}">'+myResults.modifiedVotersList[i].addedCount+'</a></td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].addedCount+'</td>';
		    
		if(myResults.modifiedVotersList[i].deletedCount!=0)
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'Deleted\')}">'+myResults.modifiedVotersList[i].deletedCount+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].deletedCount+'</td>';

		if(myResults.modifiedVotersList[i].movedCount!=0)
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'Moved\')}">'+myResults.modifiedVotersList[i].movedCount+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].movedCount+'</td>';

		if(myResults.modifiedVotersList[i].relocatedCount!=0)
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'Relocated\')}">'+myResults.modifiedVotersList[i].relocatedCount+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].relocatedCount+'</td>';
			 
		if(myResults.modifiedVotersList[i].maleVotersAdded!=0)	 
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'Added\')}">'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
			
		if(myResults.modifiedVotersList[i].maleVotersDeleted!=0)	
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'Deleted\')}">'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
			
		if(myResults.modifiedVotersList[i].maleVotersMoved!=0)	 
			str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'Moved\')}">'+myResults.modifiedVotersList[i].maleVotersMoved+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersMoved+'</td>';
			
		if(myResults.modifiedVotersList[i].maleVotersRelocated!=0)	
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'Relocated\')}">'+myResults.modifiedVotersList[i].maleVotersRelocated+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersRelocated+'</td>';

		
		if(myResults.modifiedVotersList[i].femaleVotersAdded!=0)
            str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'Added\')}">'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		
		if(myResults.modifiedVotersList[i].femaleVotersDeleted!=0)
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'Deleted\')}">'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';


		if(myResults.modifiedVotersList[i].femaleVotersMoved!=0)
            str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'Moved\')}">'+myResults.modifiedVotersList[i].femaleVotersMoved+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersMoved+'</td>';
		
		if(myResults.modifiedVotersList[i].femaleVotersRelocated!=0)
		    str+='<td><a href="javaScript:{getVoterDetailsForGender(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'Relocated\')}">'+myResults.modifiedVotersList[i].femaleVotersRelocated+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersRelocated+'</td>';*/
	 str+='</tr>';
	 }
	 
	str+='</tbody>';
	str+='</table>';
	
	
	$('#subLevelDiv').html(str);

	$('#subLevelVotersTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength":50,
		"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null
		] 
		});
}
