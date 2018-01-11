
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
			//document.getElementById('tx').addEventListener('click', loadTxView, false);
			document.getElementById('dashboard').addEventListener('click', loadDashboardView, false);
			document.getElementById('logout').addEventListener('click', logout, false); 
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
	    	
	    	var para = document.createElement("p");
	    	var node = document.createTextNode("loadDashboardView onreadystate is ready.");
	    	para.appendChild(node);

		    document.getElementById("lb").addEventListener("click", function(e) {
		        // e.target is our targetted element.
                // try doing console.log(e.target.nodeName), it will result LI
		    	
		    	var para = document.createElement("p");
		    	var node = document.createTextNode("logout was clicked.");
		    	para.appendChild(node);

		        if(e.target && e.target.nodeName == "LI") 
		        {
		            console.log(e.target.id + " was clicked");
		        	var para = document.createElement("p");
		        	var node = document.createTextNode("logout was clicked.");
		        	para.appendChild(node);

		        	var element = document.getElementById("div1");
		        	element.appendChild(para);
		        }
		    });

		}
	}
	xhr.open("GET", "ajaxEmployeeHomepage", true);
	xhr.send();
}

function logout()
{
	
	var para = document.createElement("p");
	var node = document.createTextNode("logout was clicked.");
	para.appendChild(node);

	var element = document.getElementById("div1");
	element.appendChild(para);

}