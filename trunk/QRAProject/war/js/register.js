var first_name = $("#first_name");
var middle_name = $("#middle_name");
var last_name = $("#last_name");
var email = $("#email");
var username = $("#username");
var password = $("#password");
var profile_img = $("#profile_img");
var birthdate = $("#datepicker");

$("#submitBtn").button().click(function(){
});

$(function() {
	$("#datepicker").datepicker();
});

$("#registration_form").submit(function(event){
	var allInputsValid = true;
	var errors = $("<table>");
	
	if(username.val() == "" || username.val() == null )
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a username.")));
	}
	if(password.val() == "" || password.val() == null)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a password.")));
	}
	if(first_name.val() == "" || first_name.val() == null)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a first name.")));
	}
	if(middle_name.val()== "" || middle_name.val() == null)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a middle name. If you don't have one, please enter N/A.")));
	}
	if(last_name.val() == "" || last_name.val() == null)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a last name.")));
	}
	if(email.val() == "" || email.val() == null)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered an email.")));
	}
	if(birthdate.val() == "" || birthdate.val() == null)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a birth date.")));
	}
	if(password.val().length<5)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("Please enter a password at least 5 characters long.")));
	}
	if(birthdate.val().length!=10)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("Please enter a valid date in the correct format.")));
	}
	if(!allInputsValid)
	{
		$("#errors").html(errors);
		console.log("fail");
		event.preventDefault();
		$("#errors").html(errors);
	}
	else
		{
		allInputsValid = true;
		console.log("success");
		}
});