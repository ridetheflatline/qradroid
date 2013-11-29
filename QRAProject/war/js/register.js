$("#submitBtn").button().click(function(){
});

$(function() {
	$("#datepicker").datepicker();
});

$("#registration_form").submit(function(event){
	var allInputsValid = true;
	
	if(username != ""){
		
	}
	
	if(!allInputsValid)
		event.preventDefault();
});