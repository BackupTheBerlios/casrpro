function getObject(obj) {
	var object = eval("document.getElementById('"+obj+"')");
	return object;
}

function hideLayer(obj) {
	obj.style.display = 'none';
}

function showLayer(obj) {
	obj.style.display = '';
}
