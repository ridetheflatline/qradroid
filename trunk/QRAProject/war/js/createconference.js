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
          $( this ).dialog( "close" );
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
							sessionObjectValues["start_time"] = $(this).text();
						}
						else if(index == 1){
							sessionObjectValues["end_time"] = $(this).text();
						}
						else if(index == 2){
							sessionObjectValues["session_description"]  = $(this).text();
						}
					});

					sessionArray[index] = sessionObjectValues;
		});
				
		var jsonObject = 
			{"conference_name" : conf_name.val(),
				"conference_description": conf_description.val(),
				"sessions":sessionArray
				};
		
		console.log(JSON.stringify(jsonObject));
	}
});

$("#sessions").hide();
$("#startDate").datepicker();
$("#endDate").datepicker();