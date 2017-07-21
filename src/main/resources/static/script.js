function initMap() {
	var map = new google.maps.Map(document.getElementById('googleMap'), {
		zoom : 10,
		center : new google.maps.LatLng(40.016232, -83.011997),
		mapTypeId : google.maps.MapTypeId.ROADMAP,
	// styles:

});

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var allEstablishments = JSON.parse(xhttp.responseText);

			var infoWindowContent = []; // creating empty array to hold current
			// iteration of json
			var location;
			var establishment;

			for (var i = 0; i < allEstablishments.length; i++) {
				establishment = allEstablishments[i];
				// see function below - infoWindowContent at that index gets set
				// to establishment info for info window
				infoWindowContent[i] = setInfoWindow(establishment);

				location = new google.maps.LatLng(
					allEstablishments[i].latitude,
					allEstablishments[i].longitude);

				marker = new google.maps.Marker({
					position : location,
					map : map
				});

				google.maps.event.addListener(marker, 'click', (function(
					marker, i) {
					return function() {
						var infoWindow = new google.maps.InfoWindow({
							content : infoWindowContent[i]
						});
						infoWindow.open(map, this);
					}
				})(marker, i));

			}
			// function to return all content for the infoWindow - establishment
			// is set to the relevant index in the above for loop
			function setInfoWindow(establishment) {
				var stringContent = '<h3>' + establishment.name + '</h3>';
				return stringContent;
			}
		}
	}
	xhttp.open("GET", "/establishmentDatabase", true);
	xhttp.send();
}

$(window).scroll(function() {
	if ($(this).scrollTop() > 60) 
	/* height in pixels when the navbar becomes non opaque */
{
	$('.navbar').addClass('opaque');
} else {
	$('.navbar').removeClass('opaque');
}
});

var generateMarkersByTime = function(jsonResponse) {
  var contentDiv = $('.content');
  contentDiv.empty();
  contentDiv.append('<img src="' + jsonResponse.avatar_url + '" style="width: 100px;" />');
  var linksList = $('<ul></ul>');
  for(attributeName in jsonResponse) {
    if(attributeName.endsWith('_url')) {
      linksList.append('<li><a href="' + jsonResponse[attributeName] + '">' + attributeName + '</a></li>');
    }
  }
  contentDiv.append(linksList);
}

var writeFailureToConsole = function(response, status, errorThrown) {
  alert("Sorry, there was a problem!");
  console.log("Error: " + errorThrown);
  console.log("Status: " + status);
  console.log(response);
};

var performRequest = function(profileUrl, successFunction) {
  var options = { // options is an object described using JSON
    url: profileUrl,
    type: "GET", // request method -- usually "GET" or "POST"
    dataType: "json" // the type of response we're expecting
  };
  $.ajax(options).done(successFunction).fail(writeFailureToConsole);
};

$(document).ready(function() {
  
  $('button[name="writeToConsole"]').on('click', function() {
    var selectedUser = $('input[name="userId"]').val();
    console.log("Selected user is " + selectedUser);
    performRequest("https://api.github.com/users/" + selectedUser, writeResponseToConsole);  
  });
  
  $('button[name="cause404"]').on('click', function() {
    // leaving out the success function since I know it won't succeed
    performRequest("https://api.github.com/thisLinksSoBadBabyItDontCare");
  });
  
  $('button[name="generateElements"]').on('click', function() {
    var selectedUser = $('input[name="userId"]').val();
    console.log("Selected user is " + selectedUser);
    performRequest("https://api.github.com/users/" + selectedUser, generateElements);  
  });
});
