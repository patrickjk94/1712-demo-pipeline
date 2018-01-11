/**
 * 
 */
window.onload = function(){
	//addScript('features/dashboard/dashboard.js');
	loadNavbar();
	loadDashboardView();
	
}

function loadNavbar() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('navbar').innerHTML = xhr.responseText;
			document.getElementById('tx').addEventListener('click', loadTxView, false);
			document.getElementById('dashboard').addEventListener('click', loadDashboardView, false);
		}
	}
	xhr.open("GET", "ajaxNavbar", true);
	xhr.send();
}


function loadDashboardView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			//retrieveBankUserAccountInfo();
			var arr = document.getElementById('view').getElementsByTagName('script')
			for (var n = 0; n < arr.length; n++)
			    eval(arr[n].innerHTML)//run script inside div
		}
	}
	xhr.open("GET", "ajaxManagerPage", true);
	xhr.send();
}
