$(function() {

$(".sparkline").sparkline([1,2], {
    type: 'pie',
    sliceColors: ['#2fab63','#ffffff','#ff9900','#109618','#66aa00','#dd4477','#0099c6','#990099 ']});
	
 });
 
 $(document).ready(function() {
  $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A' });
	});

