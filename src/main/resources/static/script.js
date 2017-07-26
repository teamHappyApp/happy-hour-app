var initMap = function(jsonResponseUrl) {
	var map = new google.maps.Map(document.getElementById('googleMap'), {
		zoom : 10,
		center : new google.maps.LatLng(39.9612, -82.9988),
		mapTypeId : google.maps.MapTypeId.ROADMAP,
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
				var stringContent = 	'<div id="iw-container">' +
					 						'<div class="iw-title">'  + establishment.name + '</div>' +
					 						'<div class="iw-content">' +
								 				'<div class="iw-subTitle"></div>' +
									 			'<img src="images/TheLittleBar.jpg" alt="TheLittleBar.jpg" height="115" width="83">' +
									 			'<h3>' + establishment.name + '</h3>' +
									 			'<p>' + establishment.address + '</p>'+
									 			'<p>' + establishment.phoneNumber + '</p>' +
												'<p>' + establishment.schedule + '</p>' +
												'<p>' + (establishment.filters.length > 0? establishment.filters[0].name: 'none') + '</p>' +
												'</div>' +
											'<div class="iw-bottom-gradient"></div>'+ 
										'</div>'
				return stringContent;
			}
		}
	}
	
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

/*
 * var generateElements = function(jsonResponse) { var byScheduleDiv =
 * $('#schedule-content'); byScheduleDiv.empty(); var establishmentsList = $('<ul></ul>');
 * for (var idx = 0; idx < jsonResponse.length; idx++) {
 * establishmentsList.append('<li>' + jsonResponse[idx].name + '</li>'); }
 * byScheduleDiv.append(establishmentsList); }
 * 
 * var writeFailureToConsole = function(response, status, errorThrown) {
 * alert("Sorry, there was a problem!"); console.log("Error: " + errorThrown);
 * console.log("Status: " + status); console.log(response); };
 * 
 * var performRequest = function(scheduleUrl, successFunction) { var options = { //
 * options is an object described using JSON url : scheduleUrl, type : "GET", //
 * request method -- usually "GET" or "POST" dataType : "json" // the type of
 * response we're expecting };
 * $.ajax(options).done(successFunction).fail(writeFailureToConsole); };
 */

$(document).ready(
	function() {
		initMap("http://localhost:8080/establishments");
		
		$('button[name="generateMarkersByTime"]').on(
			'click',
			function() {
				var windowBegin = $('input[name="startTime"]').val();
				var windowEnd = $('input[name="endTime"]').val();
				initMap("http://localhost:8080/establishments/bySchedule/"
						+ windowBegin + "/" + windowEnd);
			});
		$('button[name="generateMarkersByFilter"]').on('click',
				function() {
			var $filterName = $('.filter-name');
			$filterName.on('change', function(){
				var selectedFilters = {};
				$filterName.filter(':checked').each(function(){
//					if(!selctedFilters.hasOwnProperty(this.name)){
//						selectedFilters[this.name] = [];
//					} 
					selectedFilters[this.name];
				});
				initMap("http://localhost:8080/establishments/byFilter/selectedFilters");
			});
		});
				
//				function() {
//			var filterName;
//			if(document.getElementById('patio').checked){
//			initMap("http://localhost:8080/establishments/byFilter/patio");
//			} else if(document.getElementById('off-street-parking').checked) {
//				initMap("http://localhost:8080/establishments/byFilter/off-street-parking");
//			}
//			});
			
	});
