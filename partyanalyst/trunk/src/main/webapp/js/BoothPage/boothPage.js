function showBoothPagePanel(resultVO){
	console.log(resultVO);
	var str = '';
	str += '<table width="100%">';
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
	
	str += '<table align="center">';
	str += '<tr>';
	for(var i in resultVO.elections){
		str += '<td>';
		str += '<fieldset>';
		str += '<legend>'+resultVO.elections[i].constituencyName+' '+resultVO.elections[i].electionTypeYear+'</legend>';
		str += '<table>';
		str += '<tr>';
		str += '<th>Party</th>';
		str += '<th>Candidate Name</th>';
		str += '<th>Votes Earned</th>';
		str += '</tr>';
		for(var j in resultVO.elections[i].partyResults){
			str +='<tr>';
			str +='<td>'+resultVO.elections[i].partyResults[j].partyName+'</td>';
			str +='<td>'+resultVO.elections[i].partyResults[j].candidateName+'</td>';
			str +='<td>'+resultVO.elections[i].partyResults[j].votesEarned+'</td>';
			str +='</tr>';
		}
		str += '</table>';		
		str += '</fieldset>';
		str += '</td>';
		
		
		/*
		 var myDataSource = new YAHOO.util.DataSource(allBoothElecInfo[i].partyVotes); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		 myDataSource.responseSchema = { 
		            fields: [
								{
									key : "partyName"
								},{
									key : "candidateName"
								},{
									key : "mandalVotes",parser:"number"
								},{
									key : "mandalpercentage",parser:"float"
								},{
									key : "maleVotes",parser:"number"
								},{
									key : "malepercentage",parser:"float"
								},{
									key : "femaleVotes",parser:"number"
								},{
									key : "femalepercentage",parser:"float"
								},{
									key : "bothVotes",parser:"number"
								},{
									key : "bothpercentage",parser:"float"
								}
							]    
		        }; 
		
		 var myColumnDefs = [ 
		            {key:"partyName",label:'Party Name', sortable:true, resizeable:true}, 
		            {key:"candidateName", label:'Candidate Name', sortable:true, resizeable:true}, 
		            {key:"mandalVotes", label:'Mandal Votes',sortable:true, resizeable:true}, 
		            {key:"mandalpercentage",label:'%AGE', sortable:true, resizeable:true}, 
		            {key:"maleVotes",label:'Male Votes', sortable:true, resizeable:true}, 
		            {key:"malepercentage",label:'%AGE', sortable:true, resizeable:true}, 
		            {key:"femaleVotes",label:'Female Votes', sortable:true, resizeable:true},
		            {key:"femalepercentage",label:'%AGE', sortable:true, resizeable:true}, 
		            {key:"bothVotes",label:'Both Votes', sortable:true, resizeable:true},
		            {key:"bothpercentage",label:'%AGE', sortable:true, resizeable:true} 
		        ]; 
		 
		var myDataTable = new YAHOO.widget.DataTable("div_"+i,myColumnDefs, myDataSource);		
		*/
	}
	str += '</tr>';
	str += '</table>';
	
	myPanel = new YAHOO.widget.Panel("boothPagePanel", {
            width: "850px", 
            fixedcenter: true, 
            constraintoviewport: false, 
            underlay: "none", 
            close: true, 
            visible: true, 
            draggable: true
    });
    
    myPanel.setHeader("Booth Page");
    myPanel.setBody(str);
	myPanel.render();
	
}