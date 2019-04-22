var counter_limit = 110;
var counter = 1;
var playing = false;

function highlight_next() {
	jQuery('.n' + counter).css('background-color', "#ffbebe");

	if (playing == false) {
		return;
	}

	if (counter < counter_limit) {
		setTimeout(function() {
			jQuery('.n' + counter).css('background-color', "transparent");
			counter += 1;
			highlight_next();
		}, 100 + Math.floor(Math.random() * 100));
	}
	else {
		playing = false;
		jQuery(".button").attr("src", "file:/Users/vlfom/IdeaProjects/ScriboDesktop/out/production/ScriboDesktop/main_webview/img/play.png");
	}
}

jQuery('.button').click(function(){
	if (playing == false) {
		playing = true;
		jQuery(".button").attr("src", "file:/Users/vlfom/IdeaProjects/ScriboDesktop/out/production/ScriboDesktop/main_webview/img/pause.png");
		highlight_next(counter);
	}
	else {
		playing = false;
		jQuery(".button").attr("src", "file:/Users/vlfom/IdeaProjects/ScriboDesktop/out/production/ScriboDesktop/main_webview/img/play.png");
	}
});