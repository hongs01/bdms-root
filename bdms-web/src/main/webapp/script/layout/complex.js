/*
*	DEMO HELPERS
*/


/**
 *	debugData
 *
 *	Pass me a data structure {} and I'll output all the key/value pairs - recursively
 *
 *	@example var HTML = debugData( oElem.style, "Element.style", { keys: "top,left,width,height", recurse: true, sort: true, display: true, returnHTML: true });	
 *
 *	@param Object	o_Data   A JSON-style data structure
 *	@param String	s_Title  Title for dialog (optional)
 *	@param Hash		options  Pass additional options in a hash
 */
function debugData (o_Data, s_Title, options) {
	options = options || {};
	var
		str=s_Title || 'DATA'
	//	maintain backward compatibility with OLD 'recurseData' param
	,	recurse=(typeof options=='boolean' ? options : options.recurse !==false)
	,	keys=(options.keys?','+options.keys+',':false)
	,	display=options.display !==false
	,	html=options.returnHTML !==false
	,	sort=options.sort !==false
	,	D=[], i=0 // Array to hold data, i=counter
	,	hasSubKeys = false
	,	k, t, skip, x	// loop vars
	;
	if (o_Data.jquery) {
		str=(s_Title ? s_Title+'\n':'')+'jQuery Collection ('+ o_Data.length +')\n    context="'+ o_Data.context +'"';
	}
	else if (o_Data.tagName && typeof o_Data.style == 'object') {
		str=(s_Title ? s_Title+'\n':'')+o_Data.tagName;
		var id = o_Data.id, cls=o_Data.className, src=o_Data.src, hrf=o_Data.href;
		if (id)  str+='\n    id="'+		id+'"';
		if (cls) str+='\n    class="'+	cls+'"';
		if (src) str+='\n    src="'+	src+'"';
		if (hrf) str+='\n    href="'+	hrf+'"';
	}
	else {
		parse(o_Data,''); // recursive parsing
		if (sort && !hasSubKeys) D.sort(); // sort by keyName - but NOT if has subKeys!
		str+='\n***'+'****************************'.substr(0,str.length);
		str+='\n'+ D.join('\n'); // add line-breaks
	}

	if (display) alert(str); // display data
	if (html) str=str.replace(/\n/g, ' <br>').replace(/  /g, ' &nbsp;'); // format as HTML
	return str;

	function parse ( data, prefix ) {
		if (typeof prefix=='undefined') prefix='';
		try {
			$.each( data, function (key, val) {
				k = prefix+key+':  ';
				skip = (keys && keys.indexOf(','+key+',') == -1);
				if (typeof val=='function') { // FUNCTION
					if (!skip) D[i++] = k +'function()';
				}
				else if (typeof val=='string') { // STRING
					if (!skip) D[i++] = k +'"'+ val +'"';
				}
				else if (typeof val !='object') { // NUMBER or BOOLEAN
					if (!skip) D[i++] = k + val;
				}
				else if (isArray(val)) { // ARRAY
					if (!skip) D[i++] = k +'[ '+ val.toString() +' ]'; // output delimited array
				}
				else if (val.jquery) {
					if (!skip) D[i++] = k +'jQuery ('+ val.length +') context="'+ val.context +'"';
				}
				else if (val.tagName && typeof val.style == 'object') {
					var id = val.id, cls=val.className, src=val.src, hrf=val.href;
					if (skip) D[i++] = k +' '+
						id  ? 'id="'+	id+'"' :
						src ? 'src="'+	src+'"' :
						hrf ? 'href="'+	hrf+'"' :
						cls ? 'class="'+cls+'"' :
						'';
				}
				else { // Object or JSON
					if (!recurse || !hasKeys(val)) { // show an empty hash
						if (!skip) D[i++] = k +'{ }';
					}
					else { // recurse into JSON hash - indent output
						D[i++] = k +'{';
						parse( val, prefix+'    '); // RECURSE
						D[i++] = prefix +'}';
					}
				}
			});
		} catch (e) {}
		function isArray(o) {
			return (o && typeof o==='object' && !o.propertyIsEnumerable('length') && typeof o.length==='number');
		}
		function hasKeys(o) {
			var c=0;
			for (x in o) c++;
			if (!hasSubKeys) hasSubKeys = !!c;
			return !!c;
		}
	}
};


/**
* showOptions
*
* Pass a layout-options object, and the pane/key you want to display
*/
function showOptions (o_Settings, key) {
	var data = o_Settings.options;
	$.each(key.split("."), function() {
		data = data[this]; // resurse through multiple levels
	});
	debugData( data, 'options.'+key );
}

/**
* showState
*
* Pass a layout-options object, and the pane/key you want to display
*/
function showState (o_Settings, key) {
	debugData( o_Settings.state[key], 'state.'+key );
}


/**
* createInnerLayout
*/
function createInnerLayout () {
	// innerLayout is INSIDE the center-pane of the outerLayout
	//debugData( layoutSettings_Inner );
	innerLayout = $( outerLayout.options.center.paneSelector ).layout( layoutSettings_Inner );
	// hide 'Create Inner Layout' commands and show the list of testing commands
	$('#createInner').hide();
	$('#createInner2').hide();
	$('#innerCommands').show();
}
