//Hide the sessions table header until there are elements

var startDate = $("#startDate");
var startHour = $("#startHour");
var startMinute = $("#startMinute");
var startAmOrPm = $("#startAmOrPm");
var endDate = $("#endDate");
var endHour = $("#endHour");
var endMinute = $("#endMinute");
var endAmOrPm = $("#endAmOrPm");
var sessionsDescrip = $("#sessionDescrip");
var conf_name = $("#conf_name");
var conf_description = $("#conf_description");
var conf_street = $("#street");
var conf_city = $("#city");
var conf_state = $("#state");
var timeZoneValue = $("#timeZone");

var userKeyId;

function fieldsInputChecker(whichForm){
	
	var errors = $("<table>");
	//The popup session form
	if(whichForm == "session"){
		var startTimeGood = true;
		var endTimeGood = true;
		if( startDate.val() == null || startDate.val() == "" ||
			startHour.val() == null || startHour.val() == "" ||
			startMinute.val() == null || startMinute.val() == "" ||
			startAmOrPm.val() == null || startAmOrPm.val() == "")
		{
			errors.append($("<tr>").append($("<td>").text("Please fill out the start date")));
			startTimeGood = false;
		}
		
		if( endDate.val() == null || endDate.val() == "" ||
			endHour.val() == null || endHour.val() == "" ||
			endMinute.val() == null || endMinute.val() == "" ||
			endAmOrPm.val() == null || endAmOrPm.val() == ""){
			errors.append($("<tr>").append($("<td>").text("Please fill out the end date")));
			endTimeGood = false;
		}
		
		if(sessionsDescrip.val() == null || sessionsDescrip.val() == ""){
			errors.append($("<tr>").append($("<td>").text("Please fill out the session description")));
		}
		
		var sesStartTime = startDate.val() + " " + startHour.val() + ":" + startMinute.val() + " " + startAmOrPm.val();
		var sesEndTime = endDate.val() + " " + endHour.val() + ":" + endMinute.val() + " " + endAmOrPm.val();

		if(startTimeGood && endTimeGood){
			if(moment(sesStartTime).isAfter(sesEndTime)){
				errors.append($("<tr>").append($("<td>").text
						("Session start time is after end time")));
			}else{
				
			}
		}
	}
	else{
		if(conf_name.val() == null || conf_name.val() == ""){
			errors.append($("<tr>").append($("<td>").text("Please fill out conference name")));
		}
		if(conf_description.val() == null || conf_description.val() == ""){
			errors.append($("<tr>").append($("<td>").text("Please fill out conference description")));
		}
		if(conf_street.val() == null || conf_street.val() == ""){
			errors.append($("<tr>").append($("<td>").text("Please fill out conference street")));
		}
		if(conf_city.val() == null || conf_city.val() == ""){
			errors.append($("<tr>").append($("<td>").text("Please fill out conference city")));
		}
		if(conf_state.val() == null || conf_state.val() ==""){
			errors.append($("<tr>").append($("<td>").text("Please fill out conference state")));
		}
	}
	
	return errors;
}

function getCookie(c_name)
{
	var c_value = document.cookie;
	var c_start = c_value.indexOf(" " + c_name + "=");
	if (c_start == -1)
	{
		c_start = c_value.indexOf(c_name + "=");
	}
	if (c_start == -1)
	{
		c_value = null;
	}
	else
	{
		c_start = c_value.indexOf("=", c_start) + 1;
		var c_end = c_value.indexOf(";", c_start);
		if (c_end == -1)
		{
			c_end = c_value.length;
		}
		c_value = unescape(c_value.substring(c_start,c_end));
	 }
	return c_value;
}

$(function(){
	userKeyId = getCookie("userKeyId");
	//console.log(userKeyId);
	//console.log(getCookie("testCookie"));
});

function reformattedTimeString(myTime){
	var formattedTimeStr = "";
	var splittedVals = myTime.split("at");
	formattedTimeStr =
		splittedVals[0].trim() + splittedVals[1];
	return formattedTimeStr;
}

$(function() {
    $("#dialog-form").dialog({
      autoOpen: false,
      height: 580,
      width: 550,
      modal: true,
      buttons: {
        "Create": function() {
        	
        	var sessionErrorsTable = fieldsInputChecker("session");
        	if( sessionErrorsTable.children().size() == 0){
	        	//TODO
	        	//if everything valid
	        	var startTime = startDate.val() + " at " + startHour.val() + ":" 
	        		+ startMinute.val() + " " + startAmOrPm.val();
	        	var endTime = endDate.val() + " at " + endHour.val() + ":" 
	        		+ endMinute.val() + " " + endAmOrPm.val();
	        	var tbodyRowsSize = $("#sessions").find("tbody").find("tr").size();
	        	var appendingValues = $("<tr>").append("<td>" + startTime + "</td>");
	        	appendingValues.append("<td>" + endTime+ "</td>");
	        	appendingValues.append("<td>"+sessionsDescrip.val() + "</td>");
	        	
	        	var removeBtn = $("<button>").attr("type", "button").text("Remove").button().click(function(){
	        		//TODO
	        		
	        		//Get the tr and remove it self
	        		$(this).parent().parent().fadeOut(200, function(){
	        			$(this).remove();
	        			
	        			var tbodyRowsSize = $("#sessions").find("tbody").find("tr").size();
	        			
	        			if(tbodyRowsSize == 0){
	        				$("#sessions").hide();
	        			}
	        			
	        		});
	        	});
	        	
	        	appendingValues.append($("<td>").append(removeBtn));
	     
	        	if(tbodyRowsSize == 0){
	        		$("#sessions tbody").append(appendingValues);
	        		$("#sessions").show("slow");
	        	}
	        	else{
	        		
	        		$("#sessions tbody").append(appendingValues);
	        	}
	        	
	        	$(this).dialog("close");
        	}
        	else{
        		console.log("unfilled inputs");
        		$("#sessionFormErrors").empty();
        		$("#sessionFormErrors").append(sessionErrorsTable);
        	}
        },
        Cancel: function() {
          $( this ).dialog("close");
        }
      },
      close: function() {
      }
    });
});

$(function(){
	$("#addConfSuccess").dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			Ok: function(){
				$(this).dialog("close");
			}
		}
	});
});

$(function(){
	$("#noSessionMessage").dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			Ok: function(){
				$(this).dialog("close");
			},
			"Add Session": function(){
				$(this).dialog("close");
				$( "#dialog-form" ).dialog("open");
			}
		}
	});
});

$("#createSessionBtn").button().click(function(){
	$( "#dialog-form" ).dialog("open");
});

$("#createConferenceBtn").button().click(function(){
	
	var tbodyRowsSize = $("#sessions").find("tbody").find("tr").size();
	var confErrorTable = fieldsInputChecker("conference");
	console.log("size: " + confErrorTable.children().size());
	
	if(tbodyRowsSize == 0){
		$("#noSessionMessage").dialog("open");
	}
	else if(confErrorTable.children().size() > 0){
		$("#conferenceErrors").empty();
		$("#conferenceErrors").append(confErrorTable);
	}
	else{
		//alert("Creating Conference...");
		//Create a JSON string from all the rows
		//EX: {"conference_name" : "my conference", "conference_description" : "blah blah blah",
		//		"sessions":[ 
		//  {"start_time":"11/22/2013 12:30 PM","end_time":"11/22/2013 1:30 PM", "session_description":"blah blah"}
		//, {"start_time":"11/22/2013 1:30 PM","end_time":"11/22/2013 2:30 PM", "session_description":"blah blah"}
		//]}
		
		var sessionArray = new Array();

		$("#sessions").find("tbody").children("tr").each(
				function(index){
					//console.log("row index: " + index);

					var sessionObjectValues = {};

					$(this).children("td").each(function(index){
						//console.log("children index: " + index);
						//console.log("text: " + $(this).text());
						if (index == 0){
							sessionObjectValues["start_time"] = reformattedTimeString($(this).text());
						}
						else if(index == 1){
							sessionObjectValues["end_time"] = reformattedTimeString($(this).text());
						}
						else if(index == 2){
							sessionObjectValues["session_description"]  = $(this).text();
						}
					});

					sessionArray[index] = sessionObjectValues;
		});
				
		var jsonObject = 
			{"userId" : userKeyId,
				"conference_name" : conf_name.val(),
				"conference_description": conf_description.val(),
				"conf_street":conf_street.val(),
				"conf_city":conf_city.val(),
				"conf_state":conf_state.val(),
				"timeZoneValue": timeZoneValue.val(),
				"sessions":sessionArray
				};
		var jsonAsString = JSON.stringify(jsonObject);
		console.log(jsonAsString);
		$.ajax({
			url : "/createconference",
		    type: "POST",
		    data : jsonAsString,
		    success: function(data, textStatus, jqXHR)
		    {
		        //data - response from server
		    	$("#addConfSuccess").dialog("open");
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		 
		    }
		});
	}
});

$("#sessions").hide();
$("#startDate").datepicker({minDate:0});
$("#endDate").datepicker({minDate:0});

$(function(){
	$("#startMinute").append($("<option>").attr("value", ""));
	$("#endMinute").append($("<option>").attr("value", ""));
	
	for(var i = 0; i < 60; i++){
		var optionTag = $("<option>");
		var optionTag2 = $("<option>");
		
		if(i < 10){
			optionTag.attr("value", i).text("0"+i);
			optionTag2.attr("value", i).text("0"+i);
		}
		else{
			optionTag.attr("value",i).text(i);
			optionTag2.attr("value", i).text(i);
		}
		$("#startMinute").append(optionTag);
		$("#endMinute").append(optionTag2);
	}
	
	$("#startHour").append($("<option>").attr("value", ""));
	$("#endHour").append($("<option>").attr("value", ""));
	
	for(var i = 1; i <= 12; i++){
		var optionTag = $("<option>");
		var optionTag2 = $("<option>");
		if(i < 10){
			optionTag.attr("value", i).text("0"+i);
			optionTag2.attr("value", i).text("0"+i);
		}
		else{
			optionTag.attr("value",i).text(i);
			optionTag2.attr("value", i).text(i);
		}
		$("#startHour").append(optionTag);
		$("#endHour").append(optionTag2);
	}
	
});

//Add state options in select
$(
function(){
	var states =
		["AL","AK","AZ","AR","CA","CO","CT","DE","FL",
		 "GA","HI","ID","IL","IN","IA","KS","KY","LA",
		 "ME","MT","NE","NV","NH","NJ","NM","NY","NC",
		 "ND","OH","OK","OR","MD","MA","MI","MN","MS",
		 "MO","PA","RI","SC","SD","TN","TX","UT","VT",
		 "VA","WA","WV","WI","WY"];
	var stateSelectList = $("#state");
	for(var i = 0; i < states.length; i++){
		var anOption = $("<option>").attr("value", states[i]).text(states[i]);
		stateSelectList.append(anOption);
	}
}
);

//Add timezone options in select
$(function(){
	var timeZones = ["America/Anchorage", "America/Juneau", "America/Yakutat", "America/Nome","America/Adak",
	                 "America/Chicago","America/Indiana/Knox","America/Menominee", "America/North_Dakota/Center",
	                 "America/New_York","America/Indiana/Marengo","America/Indiana/Indianapolis","America/Indiana/Vevay",
	                 "America/Kentucky/Louisville","America/Kentucky/Monticello","America/Detroit", "Pacific/Honolulu",
	                 "America/Phoenix","America/Denver","America/Boise","America/Los_Angeles"];
	var timeZonesList = $("#timeZone");
	for(var i = 0; i < timeZones.length; i++){
		var anOption = $("<option>").attr("value", timeZones[i]).text(timeZones[i]);
		timeZonesList.append(anOption);
	}
});