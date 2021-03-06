$(document).ready(function ()
{
jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "num-html-pre": function ( a ) {
        var x = String(a).replace( /<[\s\S]*?>/g, "" );
        return parseFloat( x );
    },
 
    "num-html-asc": function ( a, b ) {
        return ((a < b) ? -1 : ((a > b) ? 1 : 0));
    },
 
    "num-html-desc": function ( a, b ) {
        return ((a < b) ? 1 : ((a > b) ? -1 : 0));
    }
} );
});

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
									else if(jsObj.task == "getGenderBasedVoterDetails")
									{
									  setTimeout($.unblockUI, 200);
									  buildGenderWiseOrAddedVotersTable(myResults,jsObj);
									}
								
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
	$('#subLevelDiv').html('');
	if(myResults.modifiedVotersList == null || myResults.modifiedVotersList.length == 0)
	{
		$('#subLevelsMainDiv').html('<div id="subLevelsErrorMsgDiv">No Data Found.</div>');
		return;
	}

	  $('#subLevelsMainDiv').css('display','inline-block');
	  
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
			
		str +='<th>Ad</th>';
		str +='<th>Del</th>';
		str +='<th>Moved</th>';
		str +='<th>Reloc</th>';
		str +='<th>Ad</th>';
		str +='<th>Del</th>';
		str +='<th>Moved</th>';
		str +='<th>Reloc</th>';
		str +='<th>Ad</th>';
		str +='<th>Del</th>';
		str +='<th>Moved</th>';
		str +='<th>Reloc</th>';
	str +='</tr>';
    str+='</thead>';
	 str+='<tbody>';
    
	for(var i=0;i<myResults.modifiedVotersList.length;i++)
 	 {
		var publicationList = myResults.modifiedVotersList[i].selectOptionVOsList; 

		var locationType1 = myResults.modifiedVotersList[i].locationType;
		var locationId1 =  myResults.modifiedVotersList[i].id;
		  str+='<tr>';
		  str+='<td style="text-align:left;"><a class="voterInfoLinksCLS" title="Click Here to View '+myResults.modifiedVotersList[i].name+' '+myResults.modifiedVotersList[i].locationType+' wise Added / Deleted Voters Info " href="javascript:{}" onclick="openNewWindow(\''+myResults.modifiedVotersList[i].locationType+'\',\''+myResults.modifiedVotersList[i].id+'\')">'+myResults.modifiedVotersList[i].partNo+'</a></td>';

			for(var k in publicationList)
			  str +='<td>'+publicationList[k].id+'</td>';
		
		
		/*str+='<td>'+myResults.modifiedVotersList[i].addedCount+'</td>';
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
		str+='<td>'+myResults.modifiedVotersList[i].femaleVotersRelocated+'</td>';*/
			

		 if(myResults.modifiedVotersList[i].addedCount!=0)
		    str+='<td><a title="'+myResults.modifiedVotersList[i].addedCount+' voters are added in booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'1\',\'Added\')}">'+myResults.modifiedVotersList[i].addedCount+'</a></td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].addedCount+'</td>';
		    
		if(myResults.modifiedVotersList[i].deletedCount!=0)
			str+='<td><a title="'+myResults.modifiedVotersList[i].deletedCount+' voters are deleted in booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'2\',\'Deleted\')}">'+myResults.modifiedVotersList[i].deletedCount+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].deletedCount+'</td>';

		if(myResults.modifiedVotersList[i].movedCount!=0)
			str+='<td><a title="'+myResults.modifiedVotersList[i].movedCount+' voters are moved to other booth from booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'3\',\'Moved\')}">'+myResults.modifiedVotersList[i].movedCount+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].movedCount+'</td>';

		if(myResults.modifiedVotersList[i].relocatedCount!=0)
			str+='<td><a title="'+myResults.modifiedVotersList[i].relocatedCount+' voters are added to '+myResults.modifiedVotersList[i].name+' booth from other Booths" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'TOTAL\',\'4\',\'Relocated\')}">'+myResults.modifiedVotersList[i].relocatedCount+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].relocatedCount+'</td>';
			 
		if(myResults.modifiedVotersList[i].maleVotersAdded!=0)	 
			str+='<td><a title="'+myResults.modifiedVotersList[i].maleVotersAdded+' male voters are added in booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'1\',\'Added\')}">'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersAdded+'</td>';
			
		if(myResults.modifiedVotersList[i].maleVotersDeleted!=0)	
		    str+='<td><a title="'+myResults.modifiedVotersList[i].maleVotersDeleted+' male voters are deleted in booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'2\',\'Deleted\')}">'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersDeleted+'</td>';
			
		if(myResults.modifiedVotersList[i].maleVotersMoved!=0)	 
			str+='<td><a title="'+myResults.modifiedVotersList[i].maleVotersMoved+' male voters are moved to other booth from booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'3\',\'Moved\')}">'+myResults.modifiedVotersList[i].maleVotersMoved+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersMoved+'</td>';
			
		if(myResults.modifiedVotersList[i].maleVotersRelocated!=0)	
		    str+='<td><a title="'+myResults.modifiedVotersList[i].maleVotersRelocated+' male voters are added to '+myResults.modifiedVotersList[i].name+' booth from other booths" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'M\',\'4\',\'Relocated\')}">'+myResults.modifiedVotersList[i].maleVotersRelocated+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].maleVotersRelocated+'</td>';

		
		if(myResults.modifiedVotersList[i].femaleVotersAdded!=0)
            str+='<td><a title="'+myResults.modifiedVotersList[i].femaleVotersAdded+' female voters are added in booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'1\',\'Added\')}">'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersAdded+'</td>';
		
		if(myResults.modifiedVotersList[i].femaleVotersDeleted!=0)
		    str+='<td><a title="'+myResults.modifiedVotersList[i].femaleVotersDeleted+' female voters are deleted in booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'2\',\'Deleted\')}">'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersDeleted+'</td>';


		if(myResults.modifiedVotersList[i].femaleVotersMoved!=0)
            str+='<td><a title="'+myResults.modifiedVotersList[i].femaleVotersMoved+' female voters are moved to other booth from booth-'+myResults.modifiedVotersList[i].name+'" href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'3\',\'Moved\')}">'+myResults.modifiedVotersList[i].femaleVotersMoved+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersMoved+'</td>';
		
		if(myResults.modifiedVotersList[i].femaleVotersRelocated!=0)
		    str+='<td><a title="'+myResults.modifiedVotersList[i].femaleVotersRelocated+' female voters are added to '+myResults.modifiedVotersList[i].name+' booth from other booths"  href="javaScript:{getGenderWiseVoterDetails(\''+locationId1+'\',\''+locationType1+'\','+'\'F\',\'4\',\'Relocated\')}">'+myResults.modifiedVotersList[i].femaleVotersRelocated+'</td>';
		else
			str+='<td>'+myResults.modifiedVotersList[i].femaleVotersRelocated+'</td>';
	 str+='</tr>';
	 }
	 
	str+='</tbody>';
	str+='</table>';
	
	
	$('#subLevelDiv').html(str);

	//$('#ageWiseDetailsTable').dataTable();
	 $('#subLevelVotersTable').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"iDisplayLength":50,
		"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		"bSort": true,
		 "aoColumns": [{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" },{ "sType": "num-html" }
		] 
		});
}


function getGenderWiseVoterDetails(locationId1,locationType1,gender,voterStatusId,status)
{
		var reqBrowser = window.open("boothWiseVoterModificationInfoAction.action?locationType="+locationType1+"&locationValue="+locationId1+"&constituencyId="+constituencyId+"&fromPublicationDateId="+fromPublicationDateId+"&toPublicationDateId="+toPublicationDateId+"&forGender='true'&status="+status+"&voterStatusId="+voterStatusId+"&ageRangeId=0&gender="+gender+"","newBrowser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	   reqBrowser.focus();
/*$.blockUI({ message: '<h4><img src="./images/icons/search.gif" />  Request Processing...</h4>' }); 
	var jObj=
	{
			locationType:locationType1,
		    locationValue:locationId1,
			constituencyId:constituencyId,
			fromPublicationDateId:fromPublicationDateId,
			toPublicationDateId:toPublicationDateId,
			forGender:"true",
			status:status,
			voterStatusId:voterStatusId,
			ageRangeId:"0",
			gender:gender,	 
			task:"getGenderBasedVoterDetails"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getSelectedGenderBasedVoterDetails.action?"+rparam;	

	callAjax(jObj,url);*/


}


function buildGenderWiseOrAddedVotersTable(myResults,jsObj){
var title='';
$('#GenderOrAddedVotersTable').html('');
var str='';
var booth='';
str+='<table id="dt"><thead><tr>';

if(jsObj.locationType!='booth'){
	str+='<th>BoothNo</th>';}

str+='<th>Name</th><th>Age</th>';


if(jsObj.forGender=='true'){
	if(jsObj.gender=='TOTAL'){
		str+='<th>Gender</th>';
	}
}
else
	str+='<th>Gender</th>';

//str+='<th>Guardian</th><th>RelationShip</th></tr></thead><tbody>';
str+='<th>House No</th><th>Village</th></tr></thead><tbody>';
for(var i in myResults){
booth=myResults[0].boothNo; 

str+='<tr id="checkForEmpty">';
if(jsObj.locationType!='booth'){

str+='<td>'+myResults[i].boothNo+'</td>';}

str+='<td>'+myResults[i].firstName+'</td><td>'+myResults[i].age+'</td>';

if(jsObj.forGender=='true'){
	if(jsObj.gender=='TOTAL'){
		str+='<td class="center">'+myResults[i].gender+'</td>';
	}
}
else
	str+='<td class="center">'+myResults[i].gender+'</td>';


//str+='<td>'+myResults[i].relativeFirstName+'</td><td>'+myResults[i].relationshipType+'</td></tr>';
str+='<td><a style="cursor:pointer;color:#3392C2;" onclick="getVotersInAFamilyByPublication('+myResults[i].boothNo+','+fromPublicationDateId+','+toPublicationDateId+','+constituencyId+',\''+myResults[i].houseNo+'\')">'+myResults[i].houseNo+'</a></td><td>'+myResults[i].villagesCovered+'</td></tr>';
}
str+='</tbody></table>';
$('#GenderOrAddedVotersTable').html(str);

if(jsObj.forGender=='false'){
	var range='';
	switch (jsObj.ageRangeId) {
	    case 1: range="Young Voters";break;
		case 2: range="18-25";break;
		case 3: range="26-35";break;
		case 4: range="36-45";break;
		case 5: range="46-60";break;
		case 6: range="60-Above";break;
	}
	
	title= range+" Age "+jsObj.status+" Voters Details";
}

else{
	if(jsObj.gender=='TOTAL')
		gend='Total';
		
	else
		gend=(jsObj.gender=='M')?"Male":"Female";
	
	title= gend+" Voters "+jsObj.status+" Details";
	
	if(jsObj.locationType=='booth'){
		title=gend+" Voters "+jsObj.status+" Details in Booth - "+booth;
	}
}


$('#GenderOrAddedVotersPopup').dialog({ 
	title:title,
	height: 'auto',
	width: 950,
	show: "blind",
	modal: true,
	overlay: { opacity: 0.5, background: 'black'},
	buttons: {
	   "Close":function() {$(this).dialog("close")}
	}	
});

$('#dt').dataTable();
}
//

function openNewWindow(locationType, locationId)
{
  var urlStr ='voterModificationReportAction.action?constituencyId='+constituencyId+'&fromPublicationDateId='+fromPublicationDateId+'&toPublicationDateId='+toPublicationDateId+'&locationType='+locationType+'&locationValue='+locationId+'&';

  var updateBrowser = window.open(urlStr,'',"scrollbars=yes,height=600,width=750,left=200,top=200", '_blank');	
	updateBrowser.focus();
}
function Data(){
$(".eventsheader").find(".span2").removeClass("span2")
$(".eventsheader").find(".span1").removeClass("span1")
$(".eventsheader").find(".span3").removeClass("span3")
$(".eventsheader").find(".span5").removeClass("span5")
}