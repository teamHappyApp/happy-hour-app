var initMap = function(jsonResponseUrl) {
	var map = new google.maps.Map(document.getElementById('googleMap'), {
		zoom : 11,
		center : new google.maps.LatLng(40.0010, -82.9900),
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		styles : [ {
			"featureType" : "administrative",
			"stylers" : [ {
				"visibility" : "on"
			} ]
		}, {
			"featureType" : "poi",
			"stylers" : [ {
				"visibility" : "simplified"
			} ]
		}, {
			"featureType" : "road",
			"elementType" : "labels",
			"stylers" : [ {
				"visibility" : "simplified"
			} ]
		}, {
			"featureType" : "water",
			"stylers" : [ {
				"visibility" : "simplified"
			} ]
		}, {
			"featureType" : "transit",
			"stylers" : [ {
				"visibility" : "simplified"
			} ]
		}, {
			"featureType" : "landscape",
			"stylers" : [ {
				"visibility" : "simplified"
			} ]
		}, {
			"featureType" : "road.highway",
			"stylers" : [ {
				"visibility" : "off"
			} ]
		}, {
			"featureType" : "road.local",
			"stylers" : [ {
				"visibility" : "on"
			} ]
		}, {
			"featureType" : "road.highway",
			"elementType" : "geometry",
			"stylers" : [ {
				"visibility" : "on"
			} ]
		}, {
			"featureType" : "water",
			"stylers" : [ {
				"color" : "#84afa3"
			}, {
				"lightness" : 52
			} ]
		}, {
			"stylers" : [ {
				"saturation" : -17
			}, {
				"gamma" : 0.36
			} ]
		}, {
			"featureType" : "transit.line",
			"elementType" : "geometry",
			"stylers" : [ {
				"color" : "#3f518c"
			} ]
		} ]
	});

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var allEstablishments = JSON.parse(xhttp.responseText);

			var infoWindowContent = [];
			// creating empty array to hold current iteration of json
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

				var infoWindow;

				var iwOuter;
				var iwCloseBtn;

				google.maps.event.addListener(marker, 'click', (function(
						marker, i) {
					return function() {
						infoWindow = new google.maps.InfoWindow({
							content : infoWindowContent[i]
						});
						infoWindow.open(map, this);
					}
				})(marker, i));
			}

			google.maps.event.addListener(map, 'click', function() {
				infoWindow.close();

			});

			// function to return all content for the infoWindow - establishment
			// is set to the relevant index in the above for loop
			function setInfoWindow(establishment) {
				var filterNames = "";
				for (var j = 0; j < establishment.filters.length; j++) {
					filterNames += '<li class="filter-stuff">'
							+ establishment.filters[j].displayName + '</li>';
				}

				var formattedTime = "";
				var rawStartTime = establishment.schedule.startTime;
				var rawEndTime = establishment.schedule.endTime;
				var startTime;
				var endTime;

				if (rawStartTime > 12) {
					startTime = rawStartTime - 12 + ":00 PM";
				} else if (rawStartTime == 12) {
					startTime = "12:00 PM";
				} else if (rawStartTime == 0) {
					startTime = "12:00 AM";
				} else {
					startTime = rawStartTime + ":00 AM";
				}

				if (rawEndTime > 12) {
					endTime = rawEndTime - 12 + ":00 PM";
				} else if (rawEndTime == 12) {
					endTime = "12:00 PM";
				} else if (rawEndTime == 0) {
					endTime = "12:00 AM";
				} else {
					endTime = rawEndTime + ":00 AM";
				}

				formattedTime = "Happy hour from: " + startTime + " - "
						+ endTime;

				var stringContent = '<div id="iw-container">'
						+ '<div class="iw-title">'
						+ establishment.name
						+ '</div>'
						+ '<div class="iw-content">'
						+ '<div class="iw-img">'
						+ '<img style="width: 100%; height: 100px; margin: 0; padding:0;" src="images/TheLittleBar3.png">'
						+ '</div>' + '<p>' + establishment.address + '</p>'
						+ '<p>' + establishment.phoneNumber + '</p>' + '<p>'
						+ formattedTime + '</p>' + '<p>' + establishment.description + '</p>' + '<ul class= "filter-list">'
						+ filterNames + '</ul>' + '</div>' + '</div>'
						+ '</div>'

				return stringContent;
			}
		}
	}

	console.log(jsonResponseUrl);
	xhttp.open("GET", jsonResponseUrl, true);
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

$(document)
		.ready(
				function() {
					initMap("http://localhost:8080/establishments");

					$('button[name="generateMarkersByFilter"]')
							.on(
									'click',
									function() {
										var windowBegin = $('#startTime').val();
										var windowEnd = $('#endTime').val();
										var selectedFilters = [];
										$(".filter-name:checked").each(
												function() {
													selectedFilters
															.push(this.name);
												});

										if (windowBegin == 0 || windowEnd == 0) {
											initMap("http://localhost:8080/establishments/byFilter/"
													+ selectedFilters);
										} else if (selectedFilters.length == 0) {
											initMap("http://localhost:8080/establishments/bySchedule/"
													+ windowBegin
													+ "/"
													+ windowEnd);
										} else {
											initMap("http://localhost:8080/establishments/bySchedule/"
													+ windowBegin
													+ "/"
													+ windowEnd
													+ "/byFilter/"
													+ selectedFilters);
										}
									});

					$('button[name="resetFilters"]').on('click', function() {
						initMap("http://localhost:8080/establishments");
					});
				});
