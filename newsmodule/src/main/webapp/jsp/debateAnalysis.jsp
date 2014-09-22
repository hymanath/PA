<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	
	<script src="js/jquery.google.api/ajax.googleapis.com.ajax.libs.jquery.1.8.2.jquery.min.js"></script>	
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"/>
	<script src="js/jquery-ui-themes-1.10.3.js"></script>

	
	
</head>
<body>
<div id ="sampleId"  style="width: 800px;height:800px; overflow-x: scroll;"></div>

<script>
function getCandidateCharacteristicsDetails(){
	var jsObj={
			task:"candidateDetails"
	}
	$.ajax({
          type:'GET',
          url: 'getCandidateCharacteristicsDetailsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
			buildCandidateCharacteristics(result);
	});
}
	getCandidateCharacteristicsDetails();
function buildCandidateCharacteristics(myResults){
	var result = myResults.debateSubjectList;
	$('#sampleId').html('');
	if(result != null && result.length > 0){
		 var str = '';
		 str += '<table class="table table-bordered " >';
		
		 str += '<tr>';
		 str += '<th rowspan="2">Topic</th>';
		 for(var i in result[0].partyList){
			str += '<th colspan="5">'+result[0].partyList[i].partyName+'</th>';
		 }
		 
		  str += '</tr>';
		  str += '<tr>';
		  
			
			for(var j in result[0].partyList){
					str += '<td> Candidate Name</td>';
						for(var l in result[0].partyList[0].characList){
						
							str += '<td>'+result[0].partyList[0].characList[l].characteristics+'</td>';
					
					
				}
			
			}
		
		str += '</tr>';
		str += '<tr>';
		 for(var i in result){
			str += '<tr>';		
			str += '<td>'+result[i].debateSubject+'</td>';
			if(result[i].partyList.length > 0){
				for(var j in result[i].partyList){
					if(result[i].partyList[j].candidatesList.length > 0 ){
						for(var k in result[i].partyList[j].candidatesList){
							str += '<td>'+result[i].partyList[j].candidatesList[k].candidateName+'</td>';
						}
					}
					else{	
					    str += '<td>----</td>';
					}	
					if(result[i].partyList[j].characList.length > 0){
						for(var l in result[i].partyList[j].characList){
							str += '<td>'+result[i].partyList[j].characList[l].scale+'</td>';
						}
					}
					else{
						 str += '<td>----</td>';
						  str += '<td>----</td>';
						   str += '<td>----</td>';
						    str += '<td>----</td>';
							 
					}
				}			
			}
			else{
			str += '<td>----</td>';
			str += '<td>----</td>';
			str += '<td>----</td>';
			str += '<td>----</td>';
			str += '<td>----</td>';
			
			}
			str += '</tr>';
		 }
		 str += '</table>';
		 $('#sampleId').html(str);
		
	}
}
</script>
</body>
</html>