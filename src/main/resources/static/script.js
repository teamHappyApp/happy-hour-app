function initMap() {
	var map = new google.maps.Map(document.getElementById('googleMap'), {
		zoom : 10,
		center : new google.maps.LatLng(40.016232, -83.011997),
		mapTypeId : google.maps.MapTypeId.ROADMAP,
	// styles: [
	// {"featureType": "landscape",
	// "stylers": [
	// {"visibility": "on"},
	// {"color": "#e7cd79"},
	// {"weight": 0.1}]
	// },
	// {"featureType": "water",
	// "stylers": [
	// {"visibility": "simplified"},
	// {"color": "#282828" }]
	// },
	// {"featureType": "landscape.natural.landcover",
	// "elementType": "geometry",
	// "stylers": [
	// {"visibility": "on"},
	// {"color": "#d6bc68"}]
	// },
	// {"featureType": "administrative.locality",
	// "elementType": "geometry",
	// "stylers": [
	// {"visibility": "off"},
	// {"color": "#d6bc68"}]
	// },
	// {"featureType": "road.arterial",
	// "elementType": "geometry",
	// "stylers": [
	// {"visibility": "on"},
	// {"color": "#d6bc68"}]
	// },
	// {"featureType": "poi",
	// "elementType": "all",
	// "stylers": [
	// {"visibility": "on"},
	// {"color": "#d6bc68"}]
	// },
	// {"featureType": "transit.station.airport",
	// "elementType": "geometry.fill",
	// "stylers": [
	// {"visibility": "off"},
	// {"color": "#d6bc68"}]
	// },
	// {"featureType": "poi"},
	// {"featureType": "transit.line",
	// "stylers": [
	// {"color": "#d6bc68"},
	// {"visibility": "on"}]
	// },
	// {"featureType": "road",
	// "elementType": "geometry.stroke",
	// "stylers": [
	// {"visibility": "off"},
	// {"weight": 1},
	// {"color": "#e9d9a6"}]
	// },
	// {"featureType": "road",
	// "elementType": "geometry",
	// "stylers": [
	// {"visibility": "simplified"},
	// {"color": "#e9d9a6"}]
	// },
	// {"featureType": "road.highway",
	// "elementType": "geometry",
	// "stylers": [
	// {"visibility": "simplified"},
	// {"color": "#e9d9a6"}]
	// },
	// {"featureType": "poi.business",
	// "stylers": [
	// {"color": "#e9d9a6"},
	// {"visibility": "on"}]
	// },
	// {"featureType": "poi.government",
	// "stylers": [
	// {"visibility": "off"}]
	// },
	// {"featureType": "poi.school",
	// "stylers": [
	// {"visibility": "off"}]
	// },
	// {"featureType": "administrative",
	// "stylers": [
	// {"visibility": "off"}]
	// },
	// {"featureType": "poi.medical",
	// "stylers": [
	// {"visibility": "off"}]
	// },
	// {"featureType": "poi.attraction",
	// "elementType": "geometry",
	// "stylers": [
	// {"visibility": "off"},
	// {"color": "#cfb665"}]
	// },
	// {"featureType": "poi.place_of_worship",
	// "stylers": [
	// {"visibility": "off"}]
	// },
	// {"featureType": "poi.sports_complex",
	// "stylers": [
	// {"visibility": "off"}]
	// },
	// {"featureType": "road.arterial",
	// "elementType": "labels.text.stroke",
	// "stylers": [
	// {"color": "#cfb665"},
	// {"visibility": "off"}]
	// },
	// {"featureType": "road.highway",
	// "elementType": "labels.text",
	// "stylers": [
	// {"visibility": "off"}]
	// },
	// {"featureType": "road.highway.controlled_access",
	// "stylers": [
	// {"visibility": "off"}]
	// },
	// {"featureType": "road",
	// "stylers": [
	// {"visibility": "on"}]
	// }
	// ]

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
	if ($(this).scrollTop() > 60) /*
									 * height in pixels when the navbar becomes
									 * non opaque
									 */
	{
		$('.navbar').addClass('opaque');
	} else {
		$('.navbar').removeClass('opaque');
	}
});