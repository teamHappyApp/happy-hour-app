function initMap() {
	var map = new google.maps.Map(document.getElementById('googleMap'), {
		zoom: 10,
		center: new google.maps.LatLng(40.016232, -83.011997),
		mapTypeId: google.maps.MapTypeId.ROADMAP,
//		styles: [
//		    {"featureType": "landscape",
//		        "stylers": [
//		            {"visibility": "on"},
//		            {"color": "#e7cd79"},
//		            {"weight": 0.1}]
//		    },
//		    {"featureType": "water",
//		        "stylers": [
//		            {"visibility": "simplified"},
//		            {"color": "#282828" }]
//		    },
//		    {"featureType": "landscape.natural.landcover",
//		        "elementType": "geometry",
//		        "stylers": [
//		        	{"visibility": "on"},
//		            {"color": "#d6bc68"}]
//		    },
//		    {"featureType": "administrative.locality",
//		        "elementType": "geometry",
//		        "stylers": [
//		            {"visibility": "off"},
//		            {"color": "#d6bc68"}]
//		    },
//		    {"featureType": "road.arterial",
//		        "elementType": "geometry",
//		        "stylers": [
//		            {"visibility": "on"},
//		            {"color": "#d6bc68"}]
//		    },
//		    {"featureType": "poi",
//		        "elementType": "all",
//		        "stylers": [
//		            {"visibility": "on"},
//		            {"color": "#d6bc68"}]
//		    },
//		    {"featureType": "transit.station.airport",
//		        "elementType": "geometry.fill",
//		        "stylers": [
//		            {"visibility": "off"},
//		            {"color": "#d6bc68"}]
//		    },
//		    {"featureType": "poi"},
//		    {"featureType": "transit.line",
//		        "stylers": [
//		            {"color": "#d6bc68"},
//		            {"visibility": "on"}]
//		    },
//		    {"featureType": "road",
//		        "elementType": "geometry.stroke",
//		        "stylers": [
//		            {"visibility": "off"},
//		            {"weight": 1},
//		            {"color": "#e9d9a6"}]
//		    },
//		    {"featureType": "road",
//		        "elementType": "geometry",
//		        "stylers": [
//		            {"visibility": "simplified"},
//		            {"color": "#e9d9a6"}]
//		    },
//		    {"featureType": "road.highway",
//		        "elementType": "geometry",
//		        "stylers": [
//		            {"visibility": "simplified"},
//		            {"color": "#e9d9a6"}]
//		    },
//		    {"featureType": "poi.business",
//		        "stylers": [
//		            {"color": "#e9d9a6"},
//		            {"visibility": "on"}]
//		    },
//		    {"featureType": "poi.government",
//		        "stylers": [
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "poi.school",
//		        "stylers": [
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "administrative",
//		        "stylers": [
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "poi.medical",
//		        "stylers": [
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "poi.attraction",
//		        "elementType": "geometry",
//		        "stylers": [
//		            {"visibility": "off"},
//		            {"color": "#cfb665"}]
//		    },
//		    {"featureType": "poi.place_of_worship",
//		        "stylers": [
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "poi.sports_complex",
//		        "stylers": [
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "road.arterial",
//		        "elementType": "labels.text.stroke",
//		        "stylers": [
//		            {"color": "#cfb665"},
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "road.highway",
//		        "elementType": "labels.text",
//		        "stylers": [
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "road.highway.controlled_access",
//		        "stylers": [
//		            {"visibility": "off"}]
//		    },
//		    {"featureType": "road",
//		    	 "stylers": [
//			            {"visibility": "on"}]
//		    }
//		    ]
	
	});
	
var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		var establishments = JSON.parse(xhttp.responseText);
		
		var marker;
		
		for(var i = 0; i < establishments.length; i++) {
			marker = new google.maps.Marker({
				position: new google.maps.LatLng(establishments[i].latitude, establishments[i].longitude), icon: 'images/olive.png',
				map:map
		
			});
				  google.maps.event.addListener(marker, 'click', function() {
				   var infowindow = new google.maps.InfoWindow({content: "A Place to 'Network'"});
				  	 infowindow.open(map, this);
				  });
			
		}
		document.getElementById('establishmentsList').innerHTML = output;
			}
		}
xhttp.open("GET", "/establishmentDatabase", true);
xhttp.send();

}


$(window).scroll(function() {
    if($(this).scrollTop() > 60)  /*height in pixels when the navbar becomes non opaque*/ 
    {
        $('.navbar').addClass('opaque');
    } else {
        $('.navbar').removeClass('opaque');
    }
});