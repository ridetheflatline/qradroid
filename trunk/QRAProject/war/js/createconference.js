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

var userId = "";

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
        	
        	var editBtn = $("<button>").attr("type", "button").text("Edit").button().click(function(){
        		$(this).parent().parent().each(function(){
        			//This is kind of hard to do may have to skip this
        			
        			//loop through the td's		
        			$(this).children().each(function(index){
        				//console.log($(this).text());
        			})
        		});
        		
        	});
        	
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
        	
        	appendingValues.append($("<td>").append(editBtn));
        	appendingValues.append($("<td>").append(removeBtn));
     
        	if(tbodyRowsSize == 0){
        		$("#sessions tbody").append(appendingValues);
        		$("#sessions").show("slow");
        	}
        	else{
        		$("#sessions tbody").append(appendingValues);
        	}
        	$(this).dialog("close");
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
	if(tbodyRowsSize == 0){
		$("#noSessionMessage").dialog("open");
	}
	else{
		alert("Creating Conference...");
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
			{"userId" : userId,
				"conference_name" : conf_name.val(),
				"conference_description": conf_description.val(),
				"conf_street":conf_street.val(),
				"conf_city":conf_city.val(),
				"conf_state":conf_state.val(),
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
		    	//alert("success");
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

/*
$("#startAmOrPm").combobox();
$("#endAmOrPm").combobox();
*/
  