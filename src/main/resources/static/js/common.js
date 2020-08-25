/**
 * 
 */

var ajaxJsonCall = function(url, param, successCallback, errorCallback) {
	var contentType;
    var data;
    var dataType;

    if (typeof param == "string") {
        contentType = "application/json;charset=UTF-8";
        data = param;
        dataType = "json"
    } else {
        //contentType = "application/x-www-form-urlencoded;charset=UTF-8";
    	contentType = "application/json;charset=UTF-8";
    	data = JSON.stringify(param);;
        dataType = "json";
    }

    $.ajax({
    type : 'POST',
    url : url,
    contentType : contentType,
    data : data,
    dataType : dataType,
    cache: false,
    beforeSend:function(){
    },
    complete:function(){
    },
    success : function(data) {
        try {
            if (data) {
                if (data["status"] == "SUCC") {
                    if (typeof successCallback !== 'undefined') {
                        successCallback(data);
                    }
                } else if (data["status"] == "FAIL"){
                    
                    if (typeof errorCallback !== 'undefined') {
                        errorCallback(data);
                    }else{
                    	alert(data["errMsg"]);
                    }
                } else {
                    if (typeof successCallback !== 'undefined') {
                        successCallback(data);
                    }
                }
            }
        } catch (e) {
            alert(e.message);
        }
    },
    error : function(xhr, status, error) {
        if (401 === xhr.status) {
            alert('오류가 발생하였습니다.');
            location.href = "/";
        } else if (500 === xhr.status) {
        	alert('서버에 오류가 발생하여 요청을 수행할 수 없습니다. 시스템 관리자에게 문의 바랍니다.');
        } else {
            alert(error);
        }
        }
    });
};

var fnGetParams = function(isIncludeDisabled) {
    isIncludeDisabled = isIncludeDisabled || true;
    var params = {};
    $(':input').each(function() {
        if (this.id.indexOf('jqg')>-1) {
            //나중에 그리드 INPUT 받아서 처리시에 추가
        } else {
        	if (this.id != '') {
	            if (!isIncludeDisabled) {
	                if ('disabled' != $(this).attr('disabled')) {
	                    params[this.id] = $.trim($(this).val());
		            	if ( $(this).is('.decimal, .decimal1, .decimal2, .decimal3, .integer') ){
		            		params[this.id] = params[this.id].replace(/,/gi,"");
		            	}
		            	/*if ( $(this).is('.date-inpt') ){ //달력
		            		params[this.id] = params[this.id].replace(/-/gi,"");
		            	}*/
	                }
	            } else {
	            	if (this.type=="checkbox") {
	            		if (this.checked) params[this.id] = $(this).val();
	            		else params[this.id] = "";
	            	} else if (this.type=="radio") {
	            		if( this.checked) params[this.id] = $(this).val();
	            	} else {
            			params[this.id] = $.trim($(this).val());		
	            	}
	            	if ( $(this).is('.decimal, .decimal1, .decimal2, .decimal3, .integer') ){
	            		params[this.id] = params[this.id].replace(/,/gi,"");
	            	}
	            	/*if ( $(this).is('.date-inpt') ){ //달력
	            		params[this.id] = params[this.id].replace(/-/gi,"");
	            	}*/
	            }
        	}
        }
    });
    
    return params;
};


$(function(){		

	//datepicker 달력
	$.datepicker.regional['ko'] = {
		prevText : '이전달',
		nextText : '다음달',
		monthNames : [  '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
		dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
		firstDay : 0,
		yearSuffix : '',
		showAnim : "slideDown"  
 	};
	$.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
    $('.date-inpt').datepicker( {
    	showOn: "both",
		//buttonImage: "../../resource/images/common/icon_calendar.png",
    	buttonImage: "/static/images/common/icon_calendar.png",
		buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        showButtonPanel: false,
        dateFormat: 'yy-mm-dd',
		beforeShow: function(input, inst) {
			$('#ui-datepicker-div').removeClass('hide-calendar');
		},
    });	

	//LNB menu 아코디언
	$('.lnb-menu li.active').addClass('open').children('ul').show();
	$('.lnb-menu li.has-sub>a').on('click', function(){
		//$(this).removeAttr('href');
		var element = $(this).parent('li');
		if (element.hasClass('open')) {
			element.removeClass('open');
			element.find('li').removeClass('open');
			element.find('ul').slideUp(200);
		}
		else {
			element.addClass('open');
			element.children('ul').slideDown(200);
			element.siblings('li').children('ul').slideUp(200);
			element.siblings('li').removeClass('open');
			element.siblings('li').find('li').removeClass('open');
			element.siblings('li').find('ul').slideUp(200);
		}
	});
	
	//lnb 열고/닫기
	$(".lnb-btn").click(function(){
		if(!$(this).hasClass("on")){
			$(".lnb-area").css("margin-left","-210px");
			$(".content").css("margin-left","20px");
			$(this).addClass("on");
		}else{
			$(".lnb-area").css("margin-left","0");
			$(".content").css("margin-left","240px");
			$(this).removeClass("on");
		}
	});
	/*
	//gnb toggle
	$(".gnb-btn").click(function(){
		if(!$(this).hasClass("on")){
			$(".header-wrap").css("transform","translatey(-100px)");
			$(".gnb-btn").css("transform","translatey(-100px)");
			$(".content").css("margin-top","-100px");
			$(".main-cont").css("margin-top","-100px");
			$(".lnb-area").css("top","0");
			$(".lnb-btn").css("top","0");
			$(this).addClass("on");
		}else{
			$(".header-wrap").css("transform","translatey(0)");
			$(".gnb-btn").css("transform","translatey(0)");
			$(".content").css("margin-top","0");
			$(".main-cont").css("margin-top","0");
			$(".lnb-area").css("top","100px");
			$(".lnb-btn").css("top","100px");
			$(this).removeClass("on");
		}
	});*/

	/*$(".gnb-btn").click(function(){
		if(!$(this).hasClass("on")){
			$(".header-wrap").css("margin-top","-100px");
			$(this).addClass("on");
		}else{
			$(".header-wrap").css("margin-top","0");
			$(this).removeClass("on");
		}
	});*/

	//gnb 열고/닫기
	$(".gnb-btn").click(function(){
		if(!$(this).hasClass("on")){
			$(".header-wrap").hide();
			$(".lnb-area").css("top","0");
			$(".lnb-btn").css("top","0");
			$(this).addClass("on");
		}else{
			$(".header-wrap").show();
			$(".lnb-area").css("top","100px");
			$(".lnb-btn").css("top","100px");
			$(this).removeClass("on");
		}
	});
	

	// tab 탭
	$(".tab-cont").hide();
    $(".tab-cont:first").show();

    $(".tab-area li").click(function () {
        $(".tab-area li").removeClass("tab-on");
        $(this).addClass("tab-on");
        $(".tab-cont").hide();
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn();
    });
	
	
	/*var now = 0;

	$(".user-selec button").click(function(){
		
		if(now == 0){		
			$(this).addClass("selec-on");
			$(".user-selec ul").slideDown(200);
			
			now=1;
			return false;
		}
		else{
			$(this).removeClass("selec-on");
			$(".user-selec ul").slideUp(200);

			now=0;
			return false;
		}

	});*/

	//메인 select 박스 열고/닫기
	$(".selec-box button").click(function(){

		var submenu = $(this).next("ul");

		if( submenu.is(":visible") ){
			submenu.slideUp(200);
			$(this).removeClass("selec-on");
		}else{
			submenu.slideDown(200);
			$(this).addClass("selec-on");
		}
	});


});

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//기능 날짜 관련 함수 ////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
* 날짜 차이 계산 함수
*
* @param 필드아이디1
*            stndDateId : 기준 날짜(YYYY-MM-DD)
* @param 필드아이디2
*            targetDateId : 대상 날짜(YYYY-MM-DD)
* @param 결과반환필드
*            resultId
* @returns
*/
var getDateDiff = function(stndDateId, targetDateId, resultId) {

  var date1 = $("#" + stndDateId).val();
  var date2 = $("#" + targetDateId).val();

  if (date1 == "" || date2 == "") { return; }

  var arrDate1 = date1.split("-");
  var getDate1 = new Date(parseInt(arrDate1[0]), parseInt(arrDate1[1]) - 1, parseInt(arrDate1[2]));
  var arrDate2 = date2.split("-");
  var getDate2 = new Date(parseInt(arrDate2[0]), parseInt(arrDate2[1]) - 1, parseInt(arrDate2[2]));

  var getDiffTime = getDate1.getTime() - getDate2.getTime();

  $("#" + resultId).val(Math.floor(getDiffTime / (1000 * 60 * 60 * 24)));
};


var getDateDiff2 = function(fromDate, toDate) {
  if (fromDate == "" || toDate == "") { return; }
  if(!isNotEmpty(fromDate) || !isNotEmpty(toDate)){ return; }
  
  var arrDate1 = toDate.split("-");
  var getDate1 = new Date(parseInt(arrDate1[0]), parseInt(arrDate1[1]) - 1, parseInt(arrDate1[2]));
  var arrDate2 = fromDate.split("-");
  var getDate2 = new Date(parseInt(arrDate2[0]), parseInt(arrDate2[1]) - 1, parseInt(arrDate2[2]));

  var getDiffTime = getDate1.getTime() - getDate2.getTime();

  return Math.floor(getDiffTime / (1000 * 60 * 60 * 24));
};


/**
* 날짜 차이 계산 함수
*
* @param 날짜1
*            srcDateStr : 기준 날짜(YYYY-MM-DD)
* @param 날짜2
*            targetDateStr : 대상 날짜(YYYY-MM-DD)
* @param 결과반환
* @returns
*/
var getStrDateDiff = function(srcDateStr, targetDateStr) {	
	
	if (srcDateStr == "" || targetDateStr == "") { return; }
	var srcDate = new Date(srcDateStr.substring(0,4), srcDateStr.substring(4,6), srcDateStr.substring(6,8), srcDateStr.substring(8,10), srcDateStr.substring(10,12), srcDateStr.substring(12,14));
	var targetDate = new Date(targetDateStr.substring(0,4), targetDateStr.substring(4,6), targetDateStr.substring(6,8), targetDateStr.substring(8,10), targetDateStr.substring(10,12), targetDateStr.substring(12,14));
	
  var getDiffTime = srcDate.getTime() - targetDate.getTime();
  return Math.floor(getDiffTime / (60 * 60 * 24));
};

var fnGetDomain = function() {
  var dns, arrDns, str;
  dns = document.location.href; // <-- 현재 URL 얻어온다
  arrDns = dns.split("//"); // <-- // 구분자로 짤라와서
  str = arrDns[0] + "//" + arrDns[1].substring(0, arrDns[1].indexOf("/")); // <--
  // 뒤에부터
  // 다음 /
  // 까지
  // 가져온다
  return str;
};


/**
* 날짜 포맷 맞추기
*/
var getDateFormatData = function(flag, cnt, delimiter) {
	if (delimiter == undefined) delimiter = "-";
	
	var dateObj = new Date();
	
	var y1 = dateObj.getFullYear();
	var m1 = dateObj.getMonth();
	var d1 = dateObj.getDate();
	
	if (flag == "y") {
		dateObj.setYear(y1 + cnt);
		
	} else if (flag == "m") {
		dateObj.setMonth(m1 + cnt);
		
	} else if (flag = "d") {
		dateObj.setDate(d1 + cnt);
	}
	y1 = dateObj.getFullYear();
	m1 = dateObj.getMonth() + 1;
	d1 = dateObj.getDate();
	
	return y1 + delimiter + this.formatLen(m1) + delimiter + this.formatLen(d1);
};

/**
* 날짜 길이 맞추기
*/
var formatLen = function(str) {
	return str = (""+str).length<2 ? "0"+str : str;
}

/**
* 오늘 날짜
*/
var getToDay = function(delimiter) {
	return getDateFormatData("today", 0, delimiter);
};

/**
* 계산된 날짜 
* @param flag 날짜계산구분(y/m/d 중 입력)
* @param cnt 계산되어야할날짜 (예: 10)
* @param delimiter
* 
* 사용예시 : getDiffDay("m", -1) >>> 한달전 날짜를 조회 
* 
*/
var getDiffDay = function(flag, cnt, delimiter) {
	return getDateFormatData(flag, cnt, delimiter);
}


/**
* 입력한 날짜의 달의 첫째일을 리턴
*/
var firstDayByMonth = function(toDate, delimiter) {
	if (delimiter == undefined) delimiter = "-";
	
	var arrDate1 = toDate.split("-");
	var dateObj = new Date();
	dateObj.setFullYear(parseInt(arrDate1[0]));
	dateObj.setMonth(parseInt(arrDate1[1]) - 1);
	dateObj.setDate(1);
	
	return dateObj.getFullYear() + delimiter + this.formatLen(dateObj.getMonth()+1) + delimiter + this.formatLen(dateObj.getDate());
}

/**
* 입력한 날짜 리턴
*/
var currentDayByMonth = function(toDate, delimiter) {
	if (delimiter == undefined) delimiter = "-";
	
	var arrDate1 = toDate.split("-");
	var dateObj = new Date();
	dateObj.setFullYear(parseInt(arrDate1[0]));
	dateObj.setMonth(parseInt(arrDate1[1]) - 1);
	dateObj.setDate(parseInt(arrDate1[2]));
	
	return dateObj.getFullYear() + delimiter + this.formatLen(dateObj.getMonth()+1) + delimiter + this.formatLen(dateObj.getDate());
}

/**
* 날짜포맷에 맞는지 검사
*/
function isDateFormat(d) {
	if (d.length != 8) {
		return false;
	}
	var df = /^(19[7-9][0-9]|9999|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	
	return d.match(df);
}

/**
* 윤년여부 검사
*/
function isLeaf(year) {
	var leaf = false;

	if(year % 4 == 0) {
		leaf = true;

		if(year % 100 == 0) {
			leaf = false;
		}

		if(year % 400 == 0) {
			leaf = true;
		}
	}
	return leaf;
}


/**
* 날짜 유효성 검사 
* @param d
* @returns {Boolean}
*/
function isValidDate(d) {
	if(!isDateFormat(d)) {
		return false;
	}

	var month_day = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

	var year = Number(d.substr(0, 4));
	var month = Number(d.substr(4, 2));
	var day = Number(d.substr(6));

	// 날짜가 0이면 false
	if(day == 0) {
		return false;
	}

	var isValid = false;

	// 윤년일때
	if(isLeaf(year)) {
		if(month == 2) {
			if(day <= month_day[month-1] + 1) {
				isValid = true;
			}
		} else {
			if(day <= month_day[month-1]) {
				isValid = true;
			}
		}
	} else {
		if(day <= month_day[month-1]) {
			isValid = true;
		}
	}
	return isValid;
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//기능 포멧 관련 함수 ////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
* 콤마제거
*
* @param data
*/
var removeComma = function(data) {
	try { 
		if (null==data) return '';
		return data.toString().replaceAll(/,/g, "");} 
	catch(e) {
		console.log("removeComma error %s %o",data,e);
		return "";}

};

/**
* 콤마 추가
*
* @param data
*/
var addComma = function(data) {
var rtnVal = "";
if( isNotEmpty(data)) {
	  data = removeComma(data);  
	  var arrNum = data.toString().split('.');
	    if (arrNum.length >= 2) {
	        rtnVal = arrNum[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "." + arrNum[1];
	    } else {
	        rtnVal = arrNum[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	    }
}
return rtnVal;
};

var removeNAddComma = function (data) {
	return addComma(removeComma(data));
}

/**
* 예금주 식별번호 형식 지정
*
* @param cellvalue -
*            Cell 값
* @param options -
*            Cell Option 정보
* @param rowObject -
*            Row Obejct
*/
var accteeNoFormat = function(cellvalue) {
  if (cellvalue.length === 7) { return cellvalue.substring(0, 6) + "-" + cellvalue.substring(6, 7); }
  return cellvalue;
};

var removeNonNumber = function(str) {
	str = $.trim(str);
	str = str.replace(/[^0-9]/g,'');
	
	return str;
};

var setCellNumberFormat = function(num1) {
	num1 = removeNonNumber(num1);
	if (num1.length == 10) {
		num1 = num1.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
		
	} else if (num1.length == 11) {
		num1 = num1.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
	}
	return num1;
};

/**
* 전화번호 검증 함수 
* 휴대전화 / 일반전화 포함 
*/
var checkTelNumber = function(number) {
	var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	
	if (isEmpty(number)) {
		return false;
		
	} else if (!regExp.test(number)) {
		return false;
	}
	return true;
};

/**
* 사업자등록번호 형식 지정
*
* @param cellvalue -
*            Cell 값
* @param options -
*            Cell Option 정보
* @param rowObject -
*            Row Obejct
*/
var bunsinessNoFormat = function(cellvalue, options, rowObject) {

if (cellvalue.length == 10) { return cellvalue.substring(0, 3) + "-" + cellvalue.substring(3, 5) + "-" + cellvalue.substring(5); }
return cellvalue;

};

/**
* @Description : 사업자등록번호 체크 
*
* @return : true, false
*/
var checkBizID = function(bizID) { 
// bizID는 숫자만 10자리로 해서 문자열로 넘긴다. 
var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1); 
var tmpBizID, i, chkSum=0, c2, remander; 
bizID = bizID.replace(/-/gi,''); 
for (i=0; i<=7; i++) chkSum += checkID[i] * bizID.charAt(i); 
c2 = "0" + (checkID[8] * bizID.charAt(8)); 
c2 = c2.substring(c2.length - 2, c2.length); 
chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); 
remander = (10 - (chkSum % 10)) % 10 ; 
if (Math.floor(bizID.charAt(9)) == remander) return true ; // OK! 
return false; 
};

/**
* 법인사업자등록번호 형식 지정
*
* @param cellvalue -
*            Cell 값
* @param options -
*            Cell Option 정보
* @param rowObject -
*            Row Obejct
*/
var corpBusinessNoFormat = function(cellvalue, options, rowObject) {
  if (cellvalue.length === 13) {return cellvalue.substring(0, 6) + "-" + cellvalue.substring(6, 13);}
  return cellvalue;
};

//법인사업자 체크
var checkCorpBusiness = function(businessNo) {
  //console.log("businessNo" ,businessNo);
  // 법인번호 오류검증 공식
  // 법인번호에서 마지막 자리를 제외한
  // 앞에 모든 자리수를 1과 2를 순차적으로 곱한다.
  // 예) 1234567890123
  //     ************
  //     121212121212
  //     각각 곱한 수를 모든 더하고 10으로 나눈 나머지 수를 구한다.
  //     (각각더한수 % 10)
  //     나눈 나머지 수와 법인번호 마지막 번호가 일치하면 검증일치
  businessNo = businessNo.replace(/-/gi,'');
  if (businessNo.length !== 13){
      return false;
  }

  var arr_regno  = businessNo.split("");
  var arr_wt   = new Array(1,2,1,2,1,2,1,2,1,2,1,2);
  var iSum_regno  = 0;
  var iCheck_digit = 0;

  for (i = 0; i < 12; i++){
      iSum_regno +=  eval(arr_regno[i]) * eval(arr_wt[i]);
  }

  iCheck_digit = 10 - (iSum_regno % 10);
  iCheck_digit = iCheck_digit % 10;
  if (iCheck_digit != arr_regno[12]) {
      return false;
  }
  return true;
};

var checkBubinNo = function (bubinNo){
  var as_Biz_no = String(bubinNo);
  var isNum = true;
  var I_TEMP_SUM = 0 ;
  var I_TEMP = 0;
  var S_TEMP;
  var I_CHK_DIGIT = 0;

  if (bubinNo.length !== 13) {
      return false;
  }

  for (index01 = 1; index01 < 13; index01++) {
      var i = index01 % 2;
      var j = 0;

      if(i === 1){
          j = 1;
      }
      else if( i === 0){
          j = 2;
      }

      I_TEMP_SUM = I_TEMP_SUM + parseInt(as_Biz_no.substring(index01-1, index01),10) * j;
  }

  I_CHK_DIGIT= I_TEMP_SUM%10 ;

  if (I_CHK_DIGIT !== 0 ) {
      I_CHK_DIGIT = 10 - I_CHK_DIGIT;
  }
  if (as_Biz_no.substring(12,13) !== String(I_CHK_DIGIT)) {
      return false;
  }
  return true;
};

/**
* 날짜 포멧 변경
*
* @param date
*/
var formatDate = function(txtDate) {
// 공백인 경우는 정상으로 처리
if (txtDate != "") {
    if (!isDate(txtDate)) {
        alert("날짜 형식이 맞지 않습니다.");
        return txtDate;
    }
    return txtDate.substring(0, 4) + "-" + txtDate.substring(4, 6) + "-" + txtDate.substring(6, 8);
} else {
    return txtDate;
}
};


/**
* 날짜 포멧 변경
*
* @param date
*/
var removeDashFromDateText = function(txtDate) {
// 공백인 경우는 정상으로 처리
if (isEmpty( txtDate) ) {
    return "";
} else {
    return txtDate.replace(/-/g, "");
} 
};


/**
* 날짜 포멧 변경
*
* @param date
*/
var unFormatDate = function(strFormatDate) {
// 공백인 경우는 정상으로 처리
if (strFormatDate != "") {
    if (!isDate(strFormatDate)) {
        alert("날짜 형식이 맞지 않습니다.");
        return txtDate;
    }

    return strFormatDate.replace(/-/g, "");
} else {
    return strFormatDate;
}
};

/**
* Date를 String으로 변경
* 
* @param date
* @param type [DATE:YYYYMMDD | TIME:HH24MISS | DATETIME:YYYYMMDDHH24MISS | default(DATETIME):YYYYMMDDHH24MISS]
*/
var fn_convertDateToString = function(date, type) {

	var strDt = null;
	
	if(type == 'DATE') {
		strDt = fn_paddingZero(date.getFullYear(), 4) + fn_paddingZero(date.getMonth() + 1, 2) + fn_paddingZero(date.getDate(), 2)
	} else if(type == 'TIME') {
		strDt = fn_paddingZero(date.getHours(), 2) + fn_paddingZero(date.getMinutes(), 2) + fn_paddingZero(date.getSeconds(), 2);
	} else {
		strDt = fn_paddingZero(date.getFullYear(), 4) + fn_paddingZero(date.getMonth() + 1, 2) + fn_paddingZero(date.getDate(), 2) + fn_paddingZero(date.getHours(), 2) + fn_paddingZero(date.getMinutes(), 2) + fn_paddingZero(date.getSeconds(), 2);
	}

  return strDt;
}

/**
* 현재시간을 YYYYMMDDHH24MISS형태로 가져옴
*
* @returns
*/
var fn_getCurrentTime = function() {
  var toDay = new Date();

  var formatDate = fn_paddingZero(toDay.getFullYear(), 4) + fn_paddingZero(toDay.getMonth() + 1, 2) + fn_paddingZero(toDay.getDate(), 2) + fn_paddingZero(toDay.getHours(), 2) + fn_paddingZero(toDay.getMinutes(), 2) + fn_paddingZero(toDay.getSeconds(), 2);
  return formatDate;
};

/**
* 현재시간을 YYYYMMDD형태로 가져옴
*
* @returns
*/
var fn_getCurrentDate = function() {
  var toDay = new Date();

  var formatDate = fn_paddingZero(toDay.getFullYear(), 4) + fn_paddingZero(toDay.getMonth() + 1, 2) + fn_paddingZero(toDay.getDate(), 2);
  return formatDate;
};

/**
* 0값으로 자리수 메꾸기
*
* @param val
* @param digits
* @returns {String}
*/
var fn_paddingZero = function(val, digits) {
  var zeroVal = '';
  val = val.toString();

  if (val.length < digits) {
      for (var i = 0; i < digits - val.length; i++)
          zeroVal += '0';
  }
  return zeroVal + val;
};


/**
* 숫자를 한글로 변환
*
* @param num
* @returns hanStr
*/
var MoneyToHan = function(num) {
  arrayNum = new Array("", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구");
  arrayUnit = new Array("", "십", "백", "천", "만", "십만", "백만", "천만", "억", "십억", "백억", "천억", "조", "십조", "백조");
  arrayStr = new Array()
  len = num.length;
  hanStr = "";
  for (i = 0; i < len; i++) {
      arrayStr[i] = num.substr(i, 1)
  }
  code = len;
  for (i = 0; i < len; i++) {
      code--;
      tmpUnit = "";
      if (arrayNum[arrayStr[i]] != "") {
          tmpUnit = arrayUnit[code];
          if (code > 4) {
              if ((Math.floor(code / 4) == Math.floor((code - 1) / 4) && arrayNum[arrayStr[i + 1]] != "") || (Math.floor(code / 4) == Math.floor((code - 2) / 4) && arrayNum[arrayStr[i + 2]] != "")) {
                  tmpUnit = arrayUnit[code].substr(0, 1);
              }
          }
      }
      hanStr += arrayNum[arrayStr[i]] + tmpUnit;
  }
  return hanStr;
};

/**
* 전화번호, 폰번호 입력 포멧 맞춤
*
* @param num ,type
* @returns formatNum
*/
var PhoneFomatter = function (num,type){
  var formatNum = '';

  if(num.length==11){
      if(type==0){
          formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
      }else{
          formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
      }
  }else if(num.length==8){
      formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
  }else{
      if(num.indexOf('02')==0){
          if(type==0){
              formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
          }else{
              formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
          }
      }else{
          if(type==0){
              formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
          }else{
              formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
          }
      }
  }
  return formatNum;
};

/**
* 이메일 형식 체크
*
* @param num ,type
* @returns formatNum
*/
var EemailFormatCheck = function(strValue, msgFlag)
{
	var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

	//입력을 안했으면
	if(strValue.lenght == 0){
		if (msgFlag != "N") {
			alert("이메일을 입력하셔야 합니다.");
		}
		return false;
	}

	//이메일 형식에 맞지않으면
	if (!strValue.match(regExp)){
		if (msgFlag != "N") {
			alert("이메일 형식에 맞지 않습니다.");
		}
		return false;
	}
	return true;
}

var fnPostPopup = function(url, params, target, width, height, scrollbar, resizable) {
	
	if ("_self"==target) {
		fnPostGoto(url, params, target);
	} else {
    	
    	scrollbar = scrollbar || "yes";
    	resizable = resizable || "yes";
    	
    	if (null==target || undefined==target || ""==target) {
    		target = fnGetRandomText(20);
    	}
    	
		//넓이와 높이를 지정하지 않으면 최대 사이즈로.
		if (isEmpty(width) || width == 0){
			width = screen.availWidth;
		}
		if (isEmpty(height) || height == 0){
			height = screen.availHeight;
		}
    	
	    
	    
	    if(opener == null){
	    	var pos = centerWindow(width, height);
	    }else{
	    	var pos = offsetWindow(width, height, 20, 20);
	    }
	    var popup = window.open("", target, "width=" + width + ",height=" + height + ",left=" + pos[0] + ",top=" + pos[1] + ",status=no, scrollbars=" + scrollbar + ", resizable=" + resizable );
	    if (width == screen.availWidth || height == screen.availHeight){ //최대일 경우 크롬 최적화를 위해 resizeTo처리.
	    	popup.resizeTo(width, height);
	    }

    	fnPostGoto(url, params, target);
	    
	    popup.focus();
	    return popup;
    }
};

var fnPostGoto = function(url, params, target) {
    var f = document.createElement('form');
    var obj, value;
    for ( var key in params) {
        value = params[key];
        obj = document.createElement('input');
        obj.setAttribute('type', 'hidden');
        obj.setAttribute('name', key);
        obj.setAttribute('value', value);
        f.appendChild(obj);
    }
    if (target) f.setAttribute('target', target);
    f.setAttribute('method', 'post');
    f.setAttribute('action', url);
    
    //csrf 설정
    /*var csrfField = document.createElement("input");
    csrfField.setAttribute("type","hidden");
    csrfField.setAttribute("name", $("meta[name='_csrf_parameter']").attr("content"));
    csrfField.setAttribute("value",$("meta[name='_csrf']").attr("content"));
    f.appendChild(csrfField);*/
    
    document.body.appendChild(f);
    f.submit();
};

var fnGetRandomText = function(textSize) {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < textSize; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;	
};