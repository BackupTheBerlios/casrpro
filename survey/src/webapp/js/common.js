/***
*
* 
*  Common java script functions
*  Claudio Petronio 2006
*
*/
	
	function popModal(url) {
			window.open(url,'popUp','height=300,width=370,resizable=no,scrollbars=no,status=no,toolbar=no,menubar=no');
	}
	
	function popModal(url,h,w,s) {
			var attrs = 'height=' + h + ',width=' + w + ',resizable=no,status=no,menubar=no';
			window.open(url,'popUpNew', attrs);
	}
	
	function getElement(aID){ 
     return (document.getElementById) ? document.getElementById(aID)
                                      : document.all[aID];
   } 
