/*var generateElements = function(jsonResponse) {
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
}*/

/*	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var response = JSON.parse(xhttp.responseText);
			var people = response.people;

			var output ='';
			for(var i = 0; i < people.length; i++) {
				output += '<li>' + people[i].name + '</li>'
			}
			document.getElementById('people').innerHTML = output;
		}
	};
	
	xhttp.open("GET", "people.JSON", true);
	xhttp.send();*/

var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		var establishments = JSON.parse(xhttp.responseText);
		
		var output ='';
		for(var i = 0; i < establishments.length; i++) {
			output += '<li>' + establishments[i].name + '</li>'
		}
		document.getElementById('establishmentsList').innerHTML = output;
			}
		}
xhttp.open("GET", "/establishmentDatabase", true);
xhttp.send();

/*var locations = [
	['Bossy Grrls, Columbus OH', 40.0153214, -83.0112623],
	['Ace of Cups, Columbus OH', 40.0156739, -83.0140944],
	['Tatoheads, Columbus OH', 39.936923, -82.9858721],
];

function initMap() {
	var map = new google.maps.Map(document.getElementById('googleMap'), {
		zoom: 10,
		center: new google.maps.LatLng(40.016232, -83.011997),
		mapTypeId: google.maps.MapTypeId.ROADMAP
	});


	var infoWindow = new google.maps.InfoWindow();

	var marker, i;

	for (i = 0; i < locations.length; i++) {
		marker = new google.maps.Marker({
			position: new google.maps.LatLng(locations[i][1], locations[i][2]),
			map:map
		});

		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				infoWindow.setContent(locations[i][0]);
				infoWindow.open(map, marker);
			}
		}) (marker, i));
	}

}*/

$(window).scroll(function() {
    if($(this).scrollTop() > 60)  /*height in pixels when the navbar becomes non opaque*/ 
    {
        $('.navbar').addClass('opaque');
    } else {
        $('.navbar').removeClass('opaque');
    }
});