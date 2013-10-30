function showBoothPagePanel(resultVO){	

	var yearStr = '';

	var str = '';	
	str += '<div id="boothInfoDiv_main">';
	str += '<div id="boothInfoDiv_head"> Booth Details</div>';
	str += '<table id="boothInfoTable" width="80%">';
	str += '<tr><th>Part No:</th>';
	str += '<td>'+resultVO.partNo+'</td>';
	str += '<th>Location:</th>';
	str += '<td>'+resultVO.location+'</td></tr>';
	
	str += '<tr><th>Villages Covered:</th>';
	str += '<td>'+resultVO.villagesCovered+'</td>';
	str += '<th>Male Voters:</th>';
	str += '<td>'+resultVO.maleVoters+'</td></tr>';
	
	str += '<tr><th>Female Voters:</th>';
	str += '<td>'+resultVO.femaleVoters+'</td>';
	str += '<th>Total Voters:</th>';
	str += '<td>'+resultVO.totalVoters+'</td></tr>';
	
	str += '<tr><th>Mandal:</th>';
	str += '<td>'+resultVO.mandal+'</td></tr>';
	str += '</table>';
	str += '</div>';

	str+='<br/><br/>';
	
	str += '<table width="100%" align="center">';
	str += '<tr>';
	for(var i in resultVO.elections)
	{
		yearStr+=resultVO.elections[i].electionTypeYear+", ";
		str += '<td valign="top">';
		str += '<fieldset>';
		str += '<legend>'+resultVO.elections[i].constituencyName+' '+resultVO.elections[i].electionTypeYear+'</legend>';
		str += '<div id="'+resultVO.elections[i].constituencyId+'_div" class="boothDatatable"></div>';
		str += '</fieldset>';
		str += '</td>';		
	}
	str += '</tr>';
	str += '</table>';
	str += '</div>';
	
	yearStr = yearStr.substring(0,yearStr.length-2);
	
	myPanel = new YAHOO.widget.Panel("boothPagePanel", {
            width: "1000px", 
            fixedcenter: true, 
            constraintoviewport: true, 
            underlay: "none", 
            close: true, 
            visible: true, 
            draggable: true
    });
    
    myPanel.setHeader("Booth Election Results for "+yearStr);
    myPanel.setBody(str);
	myPanel.render();
	
	for(var i in resultVO.elections)
	{
		 var myDataSource = new YAHOO.util.DataSource(resultVO.elections[i].partyResults); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		 myDataSource.responseSchema = { 
		            fields: [
								{
									key : "partyName"
								},{
									key : "candidateName"
								},{
									key : "votesEarned",parser:"number"
								}
							]    
		        }; 
		
		 var myColumnDefs = [ 
		            {key:"partyName",label:'Party Name', sortable:true, resizeable:true}, 
		            {key:"candidateName", label:'Candidate Name', sortable:true, resizeable:true}, 
		            {key:"votesEarned", label:'Votes Earned',sortable:true, resizeable:true}		         
		        ]; 
		 
		var myDataTable = new YAHOO.widget.DataTable(resultVO.elections[i].constituencyId+"_div",myColumnDefs, myDataSource);		
	}
}