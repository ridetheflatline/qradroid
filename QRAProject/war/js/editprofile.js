var first_name = $("#first_name");
var middle_name = $("#middle_name");
var last_name = $("#last_name");
var email = $("#email");
var username = $("#username");
var password = $("#oldpassword");
var newpassword1 = $("#newpassword1");
var newpassword2 = $("#newpassword2");
var profile_img = $("#profile_img");
var birthdate = $("#datepicker");

$("#submitBtn").button().click(function(){
});

$("#edit_profile_form").submit(function(event){
	var allInputsValid = true;
	var errors = $("<table>");
	
	
	if(first_name.val() == "" || first_name.val() == null )
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a first name.")));
	}
	if(middle_name.val() == "" || middle_name.val() == null )
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a middle name.")));
	}
	if(last_name.val() == "" || last_name.val() == null )
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have not entered a last name.")));
	}
//	if(newpassword1.val() == "" || newpassword1.val() == null )
//	{
//		allInputsValid = false;
//		errors.append($("<tr>").append($("<td>").html("You have not entered a new password.")));
//	}
	if(!(newpassword1.val()==newpassword2.val()))
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("Your new password doesn't match with your confirmation of the new password.")));
	}
	if(birthdate.val() == "" ||birthdate.val() == null ||birthdate.val().length!=10)
	{
		allInputsValid = false;
		errors.append($("<tr>").append($("<td>").html("You have entered an invalid birth date.")));
	}
	else{
		//Check that the user is over 13
		var birthDay = moment(birthdate.val(), "MM/DD/YYYY");	
		//current time
		var now = moment();
		var diff = now.year() - birthDay.year();
		if( parseInt(diff) < 13){
			allInputsValid = false;
			errors.append($("<tr>").append($("<td>").html("Must be 13 or over to register.")));
		}
	}
	if(!allInputsValid)
	{
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
