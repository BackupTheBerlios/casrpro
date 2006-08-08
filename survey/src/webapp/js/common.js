/***
*
* 
*  Common java script functions
*  Claudio Petronio 2006
*
*/
	
	function popModal(url, title) {
			window.open(url,title,'height=300,width=370,resizable=no,scrollbars=no,status=no,toolbar=no,menubar=no');
	}
	
	function popModal(url,title,h,w,s) {
			var attrs = 'height=' + h + ',width=' + w + ',resizable=no,status=no,menubar=no';
			window.open(url,title, attrs);
	}
	
	function getElement(aID){ 
     return (document.getElementById) ? document.getElementById(aID)
                                      : document.all[aID];
   } 
