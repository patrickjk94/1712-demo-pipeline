var all_employees = [{"id":"01", "firstName":"patrick", "lastName":"kennedy", "user_role":"employee"},
					 {"id":"02", "firstName":"lucas",   "lastName":"flores",  "user_role":"employee"}]; 

var all_pending_reimbursements = [{"id":"01", "ammount":"15", "description":"candybar", "reciept":"null", "R_submitted":"null", "author":"01", "resolver":"02", "type":"food", "status":"pending"},
						  		  {"id":"02", "ammount":"50", "description":"coffee", "reciept":"null", "R_submitted":"null", "author":"01", "resolver":"02", "type":"food", "status":"pending"}]; 

var all_resolved_reimbursements = [{"id":"03", "ammount":"11", "description":"papertowel", "reciept":"null", "R_submitted":"null", "author":"03", "resolver":"02", "type":"household", "status":"approved"},
								   {"id":"04", "ammount":"20", "description":"soda", "reciept":"null", "R_submitted":"null", "author":"01", "resolver":"02", "type":"food", "status":"approved"}]; 

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
			document.getElementById('dashboard').addEventListener('click', loadDashboardView, false);
			document.getElementById('logout').addEventListener('click', logout, true); 
		}
	}
	xhr.open("GET", "ajaxNavbar?=" + new Date().getTime(), true);
	xhr.send();
}


function loadDashboardView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			loadAllEmployees();
			loadAllPendingReimbursements();
			loadAllResolvedReimbursements(); 
		}
	}
	xhr.open("GET", "ajaxManagerHomePage?=" + new Date().getTime(), true);
	xhr.send();
}

function loadAllEmployees() 
{
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200)
		{
			console.log(xhr.responseText);
			all_employees = JSON.parse(xhr.responseText);
			populateAllEmployeesTable( "allEmployeesTable", all_employees);
		}
	}
	xhr.open("GET", "ajaxAllEmployees", true);
	xhr.send();
}

function loadAllPendingReimbursements() 
{
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() 
	{
		if(xhr.readyState == 4 && xhr.status ==200)
		{
			console.log(xhr.responseText);
			all_pending_reimbursements = JSON.parse(xhr.responseText);
			populateAllPendingRequests( "allPendingReimbursementsTable", all_pending_reimbursements);

			var btn = document.createElement("BUTTON");        // Create a <button> element
			var t = document.createTextNode("CLICK ME");       // Create a text node
			btn.appendChild(t);                                // Append the text to <button>
			document.body.appendChild(btn);                    // Append <button> to <body>

		}
	}
	xhr.open("GET", "ajaxAllPending", true);
	xhr.send();
}

function loadAllResolvedReimbursements() 
{
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() 
	{
		if(xhr.readyState == 4 && xhr.status ==200)
		{
			console.log(xhr.responseText);
			all_resolved_reimbursements = JSON.parse(xhr.responseText);
			populateAllResolvedRequests( "allResolvedReimbursementsTable", all_resolved_reimbursements);
		}
	}
	xhr.open("GET", "ajaxAllResolved", true);
	xhr.send();
}

function populateTableWithJson(table_name, json_obj)
{
	console.log("mockLoadAllEmployees");
	
    var table = document.getElementById(table_name);
    var row = table.insertRow(1);
    row.style.padding = 5; 
    
	row.span
	var j=0; 
    for (var item in json_obj[1]) {
		row.insertCell(j).outerHTML = "<th>" + item + "</th>";
		j++; 
	}

	for (var i = 0; i < json_obj.length; i++)
	{		

		var row = table.insertRow(i+2);
		
		var j=0; 
	    for (var item in json_obj[i]) {
	    	var cell = row.insertCell(j); 
			cell.innerHTML = json_obj[i][item];
			j++; 
		}
	}
}

function populateAllEmployeesTable(table_name, json_obj)
{
	console.log("mockLoadAllEmployees");
	
    var table = document.getElementById(table_name);
    var row = table.insertRow(1);
    row.style.padding = 5; 
    
	row.span
	var j=0; 
    for (var item in json_obj[1]) {
		row.insertCell(j).outerHTML = "<th>" + item + "</th>";
		j++; 
	}

	for (var i = 0; i < json_obj.length; i++)
	{		

		var row = table.insertRow(i+2);
		
		var j=0; 
	    for (var item in json_obj[i]) {
	    	var cell = row.insertCell(j); 
			cell.innerHTML = json_obj[i][item];
			j++; 
		}
	    var cell = row.insertCell(j); 
		var btn = document.createElement('input');
		btn.type = "button";
		btn.className = "btn";
		btn.value = "view rei";
		btn.onclick = getReimbursements(json_obj[i]);
//		btn.onclick = (function(entry) {return function() {chooseUser(entry);}})(entry);
		cell.appendChild(btn);
	}
}


function populateAllPendingRequests(table_name, json_obj)
{
	console.log("mockLoadAllEmployees");
	
    var table = document.getElementById(table_name);
    var row = table.insertRow(1);
    row.style.padding = 5; 
    
	row.span
	var j=0; 
    for (var item in json_obj[1]) {
		row.insertCell(j).outerHTML = "<th>" + item + "</th>";
		j++; 
	}

	for (var i = 0; i < json_obj.length; i++)
	{		

		var row = table.insertRow(i+2);
		
		var j=0; 
	    for (var item in json_obj[i]) {
	    	var cell = row.insertCell(j); 
			cell.innerHTML = json_obj[i][item];
			j++; 
		}
	    
	    var cell = row.insertCell(j); 
		var btn = document.createElement('input');
		btn.type = "button";
		btn.className = "btn";
		btn.value = "approve";
		btn.onclick = getReimbursements(json_obj[i]);
//		btn.onclick = (function(entry) {return function() {chooseUser(entry);}})(entry);
		cell.appendChild(btn);
		
	    var cell = row.insertCell(j+1); 
		var btn = document.createElement('input');
		btn.type = "button";
		btn.className = "btn";
		btn.value = "deny";
		btn.onclick = getReimbursements(json_obj[i]);
//		btn.onclick = (function(entry) {return function() {chooseUser(entry);}})(entry);
		cell.appendChild(btn);

	}
}

function populateAllResolvedRequests(table_name, json_obj)
{
	console.log("mockLoadAllEmployees");
	
    var table = document.getElementById(table_name);
    var row = table.insertRow(1);
    row.style.padding = 5; 
    
	row.span
	var j=0; 
    for (var item in json_obj[1]) {
		row.insertCell(j).outerHTML = "<th>" + item + "</th>";
		j++; 
	}

	for (var i = 0; i < json_obj.length; i++)
	{		

		var row = table.insertRow(i+2);
		
		var j=0; 
	    for (var item in json_obj[i]) {
	    	var cell = row.insertCell(j); 
			cell.innerHTML = json_obj[i][item];
			j++; 
		}
	    
	    var cell = row.insertCell(j); 
		var btn = document.createElement('input');
		btn.type = "button";
		btn.className = "btn";
		btn.value = "view reciept";
		btn.onclick = getReimbursements(json_obj[i]);
//		btn.onclick = (function(entry) {return function() {chooseUser(entry);}})(entry);
		cell.appendChild(btn);
	}
}



function getReimbursements(json_obj)
{
	console.log(json_obj);
}

function logout() {
	var request = new XMLHttpRequest();
	request.open('POST', 'logout', true);
	request.send(data);
}