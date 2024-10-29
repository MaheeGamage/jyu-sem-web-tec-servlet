function doQuery() {
	if ((document.getElementById('inpName').value != '') && (document.getElementById('inpPeopleCount').value != '') && (document.getElementById('inpBedroomCount').value != '')) {
		var q_str = 'reqType=doQuery';

		q_str = q_str + '&name=' + document.getElementById('inpName').value;
		q_str = q_str + '&peopleCount=' + document.getElementById('inpPeopleCount').value;
		q_str = q_str + '&bedroomCount=' + document.getElementById('inpBedroomCount').value;
		q_str = q_str + '&maxLakeDistance=' + document.getElementById('inpMaxLakeDistance').value;
		q_str = q_str + '&city=' + document.getElementById('inpCity').value;
		q_str = q_str + '&maxCityDistance=' + document.getElementById('inpMaxCityDistance').value;
		q_str = q_str + '&dayCount=' + document.getElementById('inpDayCount').value;
		q_str = q_str + '&startDate=' + document.getElementById('inpStartDate').value;
		q_str = q_str + '&maxDayShifts=' + document.getElementById('inpMaxDayShifts').value;

		doAjax('Booking', q_str, 'doQuery_back', 'post', 0);
	} else {
		alert('Please, fill all the search fields...');
	}
}

function doQuery_back(result) {
	try {
		// Parse the result to ensure it's a JSON object
		const parsedResult = JSON.parse(result);

		// Check if the parsed result is an array
		if (!Array.isArray(parsedResult)) {
			throw new Error('Result is not an array');
		}

		// Check if each item in the array is an object
		parsedResult.forEach(item => {
			if (typeof item !== 'object' || item === null) {
				throw new Error('Array contains non-object elements');
			}
		});

		// If all validations pass, proceed to display the bookings
		const container = document.getElementById('booking-suggestion-container');
		container.innerHTML = ''; // Clear previous content

		parsedResult.forEach(booking => {
			const bookingDiv = document.createElement('div');
			bookingDiv.classList.add('booking');

			bookingDiv.innerHTML = `
                <h2>${booking.bookerName}</h2>
                <p>Booking Number: ${booking.bookingNumber}</p>
                <p>Address: ${booking.cottageAddress}</p>
                <img src="${booking.cottageImageUrl}" alt="Cottage Image">
                <p>Number of Places: ${booking.numberOfPlaces}</p>
                <p>Number of Bedrooms: ${booking.numberOfBedrooms}</p>
                <p>Distance to Lake: ${booking.distanceToLake} meters</p>
                <p>Nearest City: ${booking.nearestCity}</p>
                <p>Distance to City: ${booking.distanceToCity} km</p>
                <p>Booking Start Date: ${booking.bookingStartDate}</p>
                <p>Booking End Date: ${booking.bookingEndDate}</p>
				<p>------------------------</p>
            `;

			container.appendChild(bookingDiv);
		});
	} catch (error) {
		alert('Error: ' + error.message);
	}
}





