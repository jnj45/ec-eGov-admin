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
	            }
        	}
        }
    });
    
    return params;
};