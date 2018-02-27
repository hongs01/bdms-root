(function ($) {
	$(function () {
		nav();
		bnrSilder();
		anchorLink();
		sideSlider();
		helpToggle();
	});

	function anchorLink() {
		$(".txt-nav li").click(function () {
		  var idx = $(".txt-nav li").index(this),
			scrTop;
		  switch (idx) {
			case  0 :
			  scrTop = 200;
			  break;
			case  1 :
			  scrTop = 577;
			  break;
			case  2 :
			  scrTop = 1072;
			  break;
			case  3 :
			  scrTop = 1530;
			  break;
			default:
			  scrTop = 1860;
			  break; 
		  }
		  $("body,html").animate({scrollTop:scrTop}, 600);
		});
	};

	function nav() {
		var $liCur = $(".nav-box ul li.cur"),
		curP = $liCur.position().left,
		curW = $liCur.outerWidth(true),
		$slider = $(".nav-line"),
		$targetEle = $(".nav-box ul li:not('.last') a"),
		$navBox = $(".nav-box");
		$slider.stop(true, true).animate({
			"left":curP,
			"width":curW
		});
		$targetEle.mouseenter(function () {
			var $_parent = $(this).parent(),
			_width = $_parent.outerWidth(true),
			posL = $_parent.position().left;
			$slider.stop(true, true).animate({
				"left":posL,
				"width":_width
			}, "fast");
		});
		$navBox.mouseleave(function (cur, wid) {
			cur = curP;
			wid = curW;
			$slider.stop(true, true).animate({
				"left":cur,
				"width":wid
			}, "fast");
		});
	};

	function bnrSilder() {
		if (!$("#head").length && !$("#bnr").length) {
			return;
		}
		$(window).scroll(function () {
			var bTop = $(this).scrollTop();
			$('.bnr-box').css({
				'margin-top':-bTop * 0.48
			});
			$('.bnr-txt').css({
				'margin-top':-bTop * 0.68
			});
			$('.bnr-btn').css({
				'margin-top':-bTop * 0.68
			});
			$('.warper').css({
				"background-position":"50% " + bTop * 0.2 + "px"
			});
			if (bTop < 200) {
				$(".txt-warp").css({
				'margin-top':-bTop * 1.5
				});
				$(".txt-nav-warp").removeAttr("style");
			} else {
				$(".txt-warp").css({
				'margin-top':-240
				});
				$(".txt-nav-warp").css({
				"position":"fixed",
				"top":0,
				"left":0,
				"box-shadow":"0 2px 6px #eee"
				});
			}

			var idxs = 0;
			if (bTop >= 200 && bTop < 577) {
				idxs;
			} else if (bTop >= 577 && bTop < 1072) {
				idxs = 1;
			} else if (bTop >= 1072 && bTop < 1530) {
				idxs = 2;
			} else if (bTop >= 1530 && bTop < 1780) {	// 1860
				idxs = 3;
			} else if (bTop >= 1780 || bTop >= (document.documentElement.scrollTop || document.body.scrollTop)) {
				idxs = 4; 
			}
			$('.txt-nav li a').eq(idxs).addClass('on').parent().siblings().children().removeClass('on');
			if (bTop < 200) {
				$('.txt-nav li a').removeClass('on');
			}
		});
	};


	function sideSlider() {
		if (!$(".help-side dl").length) {
			return false;
		}
		var $aCur = $(".help-side dl").find(".cur a"),
		$targetA = $(".help-side dl dd a"),
		$sideSilder = $(".side-slider"),
		curT = $aCur.position().top - 3;
		$sideSilder.stop(true, true).animate({
			"top":curT
		});
		$targetA.mouseenter(function () {
			var posT = $(this).position().top - 3;
			$sideSilder.stop(true, true).animate({
				"top":posT
			}, 240);
		}).parents(".help-side").mouseleave(function (_curT) {
			_curT = curT;
			$sideSilder.stop(true, true).animate({
				"top":_curT
			});
		});
	};

	function helpToggle() {
		if (!$(".help-cont dl dt a").length) {
		  return;
		}
		$('.help-cont dt').click(function() {
			if($(this).hasClass('on')) {
				$(this).removeClass('on').next('dd').slideUp();
			}else {
				$(this).addClass('on').next('dd').slideDown();
			}
		});
	};

})(jQuery);