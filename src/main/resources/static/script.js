function initMap() {
	var map = new google.maps.Map(document.getElementById('googleMap'), {
		zoom: 10,
		center: new google.maps.LatLng(40.016232, -83.011997),
		mapTypeId: google.maps.MapTypeId.ROADMAP
	});
	
var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		var establishments = JSON.parse(xhttp.responseText);
		
		var marker;
		
		for(var i = 0; i < establishments.length; i++) {
			marker = new google.maps.Marker({
				position: new google.maps.LatLng(establishments[i].latitude, establishments[i].longitude),
				map:map
			});
		}
		document.getElementById('establishmentsList').innerHTML = output;
			}
		}
xhttp.open("GET", "/establishmentDatabase", true);
xhttp.send();

/*
	var infoWindow = new google.maps.InfoWindow();*/

/*		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				infoWindow.setContent(locations[i][0]);
				infoWindow.open(map, marker);
			}
		}) (marker, i));
	}*/

}


$(window).scroll(function() {
    if($(this).scrollTop() > 60)  /*height in pixels when the navbar becomes non opaque*/ 
    {
        $('.navbar').addClass('opaque');
    } else {
        $('.navbar').removeClass('opaque');
    }
});