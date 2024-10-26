function doQuery()
{	
	if((document.getElementById('inpName').value!='')&&(document.getElementById('inpPeopleCount').value!='')&&(document.getElementById('inpBedroomCount').value!=''))
	{
		var q_str = 'reqType=doQuery';

		q_str = q_str+'&name='+document.getElementById('inpName').value;
		q_str = q_str+'&peopleCount='+document.getElementById('inpPeopleCount').value;
		q_str = q_str+'&bedroomCount='+document.getElementById('inpBedroomCount').value;
		q_str = q_str+'&maxLakeDistance='+document.getElementById('inpMaxLakeDistance').value;
		q_str = q_str+'&city='+document.getElementById('inpCity').value;
		q_str = q_str+'&maxCityDistance='+document.getElementById('inpMaxCityDistance').value;
		q_str = q_str+'&dayCount='+document.getElementById('inpDayCount').value;
		q_str = q_str+'&startDate='+document.getElementById('inpStartDate').value;
		q_str = q_str+'&maxDayShifts='+document.getElementById('inpMaxDayShifts').value;
		
		doAjax('Booking',q_str,'doQuery_back','post',0);
	}else
	{
		alert('Please, fill all the search fields...');
	}
}

function doQuery_back(result)
{
alert('result:'+result);
}





