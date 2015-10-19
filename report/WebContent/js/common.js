function checkBrowser(){
	//取得浏览器的userAgent字符串
    var userAgent = navigator.userAgent;
    /*
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return true;
        return "Opera"
    }; //判断是否Opera浏览器
    */
    if (userAgent.indexOf("Firefox") > -1) {
        return true;
        //return "FF";
    } //判断是否Firefox浏览器
    if (userAgent.indexOf("Chrome") > -1){
        return true;
    	//return "Chrome";
    }
    if (userAgent.indexOf("Safari") > -1) {
        return true;
    	//return "Safari";
    } //判断是否Safari浏览器
    /*
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return false;
    	return "IE";
    }; //判断是否IE浏览器
    */
    return false;
}

String.format = function(src) {
    if (arguments.length == 0) return null;
    var args = Array.prototype.slice.call(arguments, 1);
    return src.replace(/\{(\d+)\}/g, function(m, i) {
        return args[i];
    });
};

function showMessageString(msg, callbackfunction)
{
	$.messager.alert(sysTitle, msg, "warning", callbackfunction);
}

function showMessageObject(obj, callbackfunction)
{
	$.messager.alert(sysTitle, obj.msgCode + ":" + obj.msgContent, "warning", callbackfunction);
}

function confirmMessageObject(obj, callbackfunction)
{
	//$.messager.confirm("确认" + "("+obj.msgCode+")" ,  obj.msgContent, callbackfunction);
	$.messager.confirm("确认",  obj.msgContent, callbackfunction);
}

function dispalyMessage(showDiv, obj)
{
	//showDiv.text(obj.msgCode + ":" + obj.msgContent);
	showDiv.text(obj.msgContent);
	showDiv.css("color","#ff0000");
}

function clearMessage(showDiv)
{
	showDiv.text("");
}

function getJsMessage(msg, param1, param2, param3)
{
	var arg = msg.split('|');
	var obj = new Object();
	obj.msgCode = arg[0];
	obj.msgContent = String.format(arg[1], param1, param2, param3);
	return obj;
}

//define the function to do when error occured
function showErrorMessage(callbackfunction)
{
	if(callbackfunction!=undefined) {
		$.messager.alert(sysTitle, "系统异常，请尝试重新登录系统！", "warning", callbackfunction);
	} else {
		$.messager.alert(sysTitle, "系统异常，请尝试重新登录系统！", "warning", gotoIndexPage);
	}
}

function gotoIndexPage() {
	parent.document.location.href = getContextPath() + "/Index.do";
}

function getContextPath() {
    var pathName = document.location.pathname;
    var contextPath = pathName.split("/")[1];
    if(contextPath=='SpringMVC_Base') {
        return "/" + contextPath;
    } else {
        return "";
    }
}

function showProgress()
{
	$.messager.progress({"title":sysTitle, "msg":"正在处理中，请稍后...", "text":"加载中..."});
	//setTimeout(function(){$.messager.progress('close');},5000);
}

function closeProgress()
{
	$.messager.progress('close');
}

function initWindow(win, widthAdj, heightAdj)
{
	win.window({
	    top: 0,
	    left: 0,
	    width: $(window).width() - widthAdj,
	    height: $(window).height() - heightAdj
	});
	
	$(window).resize(function(){
		win.window('resize', {'width':$(window).width() - widthAdj,'height':$(window).height() - heightAdj});
	});
}

function isValidPassWD(str){
	var PASSWD_FORMAT = /^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9_]{6,10}$/;
	return PASSWD_FORMAT.test(str);
}

function isValidYM(date){
	var DATE_YM_FORMAT = /^[0-9]{4}[0-1][0-9]$/;
	if(DATE_YM_FORMAT.test(date)){
		var intMonth = 0;
		intMonth = date.substr(4, 2);
		if(isNaN(intMonth)) return false;
		if(intMonth>12||intMonth<1) return false;
		return true;
	}
	return false;
}

function isValidDate(date){
	var DATE_FORMAT = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
	date = date.replace(/[\/]/g, "-");
	if(DATE_FORMAT.test(date)){
		var ar=date.split(/[-/:]/);
		var intYear = 0;
		var intMonth = 0;
		var intDay = 0;
		if (ar.length > 0) intYear = ar[0];
		if (ar.length > 1) intMonth = ar[1];
		if (ar.length > 2) intDay = ar[2];
		if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) return false;       
		if(intMonth>12||intMonth<1) return false;    
		if(intDay<1||intDay>31) return false;    
		if((intMonth==4||intMonth==6||intMonth==9||intMonth==11)&&(intDay>30)) return false;    
		if(intMonth==2) {    
			if(intDay>29) return false;		  
		    if((((intYear%100==0)&&(intYear%400!=0))||(intYear%4!=0))&&(intDay>28))return false;    
		}
		return true;    
	}
    return false;
}

function isValidDateTime(date){
	var DATETIME_FORMAT = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}\s([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])$/;
	date = date.replace(/[\/]/g, "-");
	if(DATETIME_FORMAT.test(date)){
		var ar=date.split(/[-/:\s]/);
		var intYear = 0;
		var intMonth = 0;
		var intDay = 0;
		var intHour = 0;
		var intMin = 0;
		var intSecond = 0;
		if (ar.length > 0) intYear = ar[0];
		if (ar.length > 1) intMonth = ar[1];
		if (ar.length > 2) intDay = ar[2];
		if (ar.length > 3) intHour = ar[3];
		if (ar.length > 4) intMin = ar[4];
		if (ar.length > 5) intSecond = ar[5];
		if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) return false;       
		if(intMonth>12||intMonth<1) return false;    
		if(intDay<1||intDay>31) return false;    
		if((intMonth==4||intMonth==6||intMonth==9||intMonth==11)&&(intDay>30)) return false;    
		if(intMonth==2) {    
			if(intDay>29) return false;		  
		    if((((intYear%100==0)&&(intYear%400!=0))||(intYear%4!=0))&&(intDay>28))return false;    
		}
		//if(intHour>23||intHour<0) return false;    
		//if(intMin>59||intMin<0) return false;    
		//if(intSecond>59||intSecond<0) return false;    
		return true;    
	}
    return false;
}

function trimString(str)
{
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function ltrimString(str)
{
	return str.replace(/(^\s*)/g, "");
}

function rtrimString(str)
{
	return str.replace(/(\s*$)/g, "");
}

function getBytesLength(str)
{	
	str = rtrimString(str);
	return str.replace(/[^\x00-\xff]/gi, "--").length; 
}

$.extend($.fn.validatebox.defaults.rules, {
	Required: {
    	validator: function(value, param) {
		   if(trimString(value).length > 0) {
			   return true;
		   } else {
			   return false;			   
		   }
    	},
    	message: '该输入项为必输项'
    },
    PassWDType: {
    	validator: function(value, param) {
		   return isValidPassWD(value);
    	},
    	message: '请输入6-10位的字母(a-z,A-Z)、数字(0-9)、下划线。并且必须包含字母和数字'
    },
    NotStartWith: {
    	validator: function(value, param) {
		    if(value.length < param[0].length || (value.length >= param[0].length && value.substr(0, param[0].length) != param[0])) {
		    	return true;
		    } else {
		    	return false;
		    }
    	},
    	message: '输入内容不能以{0}开始'
    },
    DateType: {
    	validator: function(value, param) {
		   return isValidDate(value);
    	},
    	message: '请输入有效日期'
    },
    DateYMType: {
    	validator: function(value, param) {
		   return isValidYM(value);
    	},
    	message: '请输入有效年月'
    },
    DateTimeType: {
    	validator: function(value, param) {
		   return isValidDateTime(value);
    	},
    	message: '请输入有效日期时间'
    },    
    DigitCharType: {
    	validator: function(value, param) {
    		var reg = new RegExp("^[0-9]*$");
		    return reg.test(value);
    	},
    	message: '请输入数字'
    },
    DataLength: {
    	validator: function(value, param) {
		   if(getBytesLength(value) <= param[0]) {
			   return true;
		   } else {
			   return false;
		   }
    	},
    	message: '输入内容大于{0}字节'
	},
    DataEqualLength: {
    	validator: function(value, param) {
      	   if(param[0] < 0) return true;
		   if(getBytesLength(value) == param[0]) {
			   return true;
		   } else {
			   return false;
		   }
    	},
    	message: '输入内容必须为{0}字节。'
	},
    DataLengthDynm: {
    	validator: function(value, param) {
    	   param[1] = $('#'+param[0]).val();
		   if(getBytesLength(value) <= $('#'+param[0]).val()) {
			   return true;
		   } else {
			   return false;
		   }
    	},
    	message: '输入内容大于{1}字节'
	},
    DataEqualLengthDynm: {
    	validator: function(value, param) {
      	   if($('#'+param[0]).val() < 0) return true;
    	   param[1] = $('#'+param[0]).val();
		   if(getBytesLength(value) == $('#'+param[0]).val()) {
			   return true;
		   } else {
			   return false;
		   }
    	},
    	message: '输入内容必须为{1}字节'
	},
    EqualTo: { 
		validator: function (value, param) {
			return $("#" + param[0]).val() == value; 
		}, 
    	message: '{1}不一致' 
	},
	//大于等于
    GtOrEq: {
    	validator: function(value, param) {
    		if(!isNaN(value) && value >= param[0])
    			return true;
    		else
    			return false;
    	},
    	message: '输入内容必须大于等于{0}'
    },
	//小于等于
    LtOrEq: {
    	validator: function(value, param) {
    		if(!isNaN(value) && value <= param[0])
    			return true;
    		else
    			return false;
    	},
    	message: '输入内容必须小于等于{0}'
    },
	//大于等于某项目
    GtOrEqDynm: {
    	validator: function(value, param) {
    		param[1] = $('#'+param[0]).val();
    		if(!isNaN(value) && value >= param[1])
    			return true;
    		else
    			return false;
    	},
    	message: '输入内容必须大于等于{1}'
    },
	//小于等于某项目
    LtOrEqDynm: {
    	validator: function(value, param) {
    		param[1] = $('#'+param[0]).val();
    		if(!isNaN(value) && value <= param[1])
    			return true;
    		else
    			return false;
    	},
    	message: '输入内容必须小于等于{1}'
    },
	//大于等于(NumberBox格式化金额)
    GtOrEqForNB: {
    	validator: function(value, param) {
    		var money = getMoneyValue(value);
    		if(!isNaN(money) && money >= param[0])
    			return true;
    		else
    			return false;
    	},
    	message: '输入内容必须大于等于{0}'
    },
	//ComboGrid专用
    ComboSelect: { 
		validator: function (value, param) {
			var val = $("#" + param[0]).combogrid('getValue');
			var txt = $("#" + param[0]).combogrid('getText');
			if (txt.length > 0 && val != txt) {
				return true;
			} else {
				return false;
			}
		}, 
    	message: '该选项在列表中不存在' 
	},
	//ComboGrid专用(多选)
    ComboMultSelect: { 
		validator: function (value, param) {
			var val = $("#" + param[0]).combogrid('getValues');
			var txt = $("#" + param[0]).combogrid('getText');
			if (val.length == txt.split(",").length) {
				return true;
			} else {
				return false;
			}
		}, 
    	message: '选项在列表中不存在' 
	},
	//ComboBox用
    ComboValid: { 
		validator: function (value, param) {
			var val = $("#" + param[0]).combogrid('getValue');
			if (val == undefined) {
				return false;
			} else {
				return true;
			}
		}, 
    	message: '该选项在列表中不存在' 
	},

	Multiple: { 
        validator : function(value, vtypes) {
            var returnFlag = true;  
            var opts = $.fn.validatebox.defaults;
            for (var i = 0; i < vtypes.length; i++) {  
                var methodinfo = /([a-zA-Z_]+)(.*)/.exec(vtypes[i]);
                var rule = opts.rules[methodinfo[1]];  
                if (value && rule) {  
                    var parame = eval(methodinfo[2]);
                    if (!rule["validator"](value, parame)) {  
                        returnFlag = false;
                        opts.rules.Multiple.message = String.format(rule.message, parame[0], parame[1], parame[2], parame[3]);
                        break;  
                    }  
                }  
            }  
            return returnFlag;  
        },
        message: ''
	},
});

function disableEditDiv(divId,isDisabled){
	if(isDisabled){
		$("#"+divId+"").find(".easyui-combobox").combobox("readonly");
		$("#"+divId+"").find(".easyui-textbox").textbox("readonly");
		$("#"+divId+"").find(".easyui-datebox").datebox("readonly");
		$("#"+divId+"").find(".easyui-datetimebox").datetimebox("readonly");
		$("#"+divId+"").find(".easyui-numberbox").numberbox("readonly");
		$("#"+divId+"").find(".easyui-linkbutton").linkbutton("disable");
	}else{
		$("#"+divId+"").find(".easyui-combobox").combobox('readonly',false);
		$("#"+divId+"").find(".easyui-textbox").textbox('readonly',false);
		$("#"+divId+"").find(".easyui-datebox").datebox('readonly',false);
		$("#"+divId+"").find(".easyui-datetimebox").datetimebox('readonly',false);
		$("#"+divId+"").find(".easyui-numberbox").numberbox('readonly',false);
		$("#"+divId+"").find(".easyui-linkbutton").linkbutton("enable");
	}
	
}

//计算DATAGRID列宽
function fixWidth(parDivId, percent)
{
	var width = $("#" + parDivId).css("width").replace(/px/g,'');
    return width * percent / 100 ; //这里你可以自己做调整
}

//s:要格式化的数字,n:保留小数位
var getFormatMoney = function(s,n) {
	if(s != undefined) {
		s = s.toString();
		if (trimString(s)!="") {
			n = n > 0 && n <= 20 ? n : 2;
			s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
			
			var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
			t = "";
			for (i = 0; i < l.length; i++) {
				t += l[i]
				if ((i + 1) % 3 == 0) {
					if (s < 0) {
						if ((i + 2) < l.length && l[i + 1] != '-') {
							t += ",";						
						}
					}else {
						if ((i + 1) < l.length) {
							t += ",";						
						}
					}
				}
			}
			var rtn = t.split("").reverse().join("") + "." + r;
			return rtn.replace(/0+$/g,'').replace(/\.+$/g,''); 
		} else {
			return s
		}
	} else {
		return s;
	}
}

//s:金额
var getMoneyValue = function(s) {
	if(trimString(s)!="") {
		return parseFloat(s.replace(/[^\d\.-]/g, ""));
	} else {
		return s;
	}
}

function checkFileEx(fileEx, fmt)
{
	var tempFileEx = fileEx.toLowerCase();
    return fmt.test(tempFileEx);
}

function setTextBoxFocus(id) {
	$('#' + id).textbox().next('span').find('input:eq(0)').focus();
}

//共通打印预览页面
//printParam参数说明
//预览标题：printParam.printTitle = "系统库存表";
//表头信息：printParam.printHeader = [{"colId":"code","colContent":"店铺：A","colWidth":"20%","colAlign":"left"},{"colId":"text","colContent":"打印时间：2015-01-01 23:59:59","colWidth":"80%","colAlign":"right"}];
//表格标题：printParam.printColumns = [{"colId":"code","colName":"Code","colWidth":"20%","colAlign":"right"},{"colId":"text","colName":"名称","colWidth":"80%","colAlign":"left"}];
//表格数据：printParam.printData = data.data.testComb;
//表格数据：printParam.colSpanBegin = 1;
//表格数据：printParam.colSpanEnd = 1;
function printPreview(url){
	var win;
    win=window.open(url,'打印预览','width=1024,height=720,toolbar=no, menubar=yes, scrollbars=yes, resizable=no, location=no, status=no');
    return win;
}

//HTML Table 合并列重复单元格
//pTableId:tableId
//pStartCol:合并开始列号
//pEndCol:合并结束列号
function colSpan(pTableId, pStartCol, pEndCol) {
	var tableId = '#' + pTableId;
    var currKey = '';
    var fstKey = '';
    var currTd = '';
    var fstTd = '';
    var spanNum = 1;
    var maxRowNum = $(tableId + " tr td:nth-child(1)").length;
    var maxColNum = $(tableId + " tr:nth-child(1)").length;

    for (var idx = pEndCol; idx >= pStartCol; idx--) {
        $(tableId + " tr td:nth-child(" + (idx) + ")").slice(0, maxRowNum).each(function (i) { 
            //alert($(this).html());
            currTd = $(this);
            currKey = '';
            for (var idxKey = pStartCol; idxKey <= idx; idxKey++) {
            	currKey += $(tableId + " tr:eq("+(i+1)+") td:eq("+(idxKey-1)+") ").text();
            }
            if (i > 0) {
                if (fstKey == currKey) {
                    currTd.remove();
                    spanNum ++;
                    fstTd.attr("rowSpan", spanNum);
                } else {
                    spanNum = 1;
                    fstTd = currTd;
                    fstKey = currKey;
                }
            } else {
                spanNum = 1;
                fstTd = currTd;
                fstKey = currKey;
            }
        });
    }
}
/**
 * 格式化日期
 * @param date 日期类型参数
 * @param splitChar 分隔符 默认"-"
 * @returns {String} 
 */
function getDateStringFormatter(date,splitChar){
	var dateString = "";
	if(splitChar ==null || splitChar == undefined){
		splitChar ="-";
	}
	if(date !=null || date !=undefined){
		dateString = date.toLocaleDateString().replace("/",splitChar)
		
	}
	return dateString;
}
/**
 * 清空节点下原有内容
 * @param divId
 * @param isDisabled
 */
function emptyDivContent(divId){
		$("#"+divId+"").find(".easyui-combobox").combobox("setValue","");
		$("#"+divId+"").find(".easyui-textbox").textbox("setValue","");
		$("#"+divId+"").find(".easyui-datebox").datebox("setValue","");
		$("#"+divId+"").find(".easyui-datetimebox").datetimebox("setValue","");
		$("#"+divId+"").find(".easyui-numberbox").numberbox("setValue","");
		$("#"+divId+"").find(".easyui-datagrid").datagrid("loadData",[]);
}

/**
 * 求两个数最小公倍数
 * @param a
 * @param b
 * @returns
 */
function getLCM(a,b){
    var minNum = Math.min(a,b),maxNum = Math.max(a,b),i=1,vper=0;
    if(a ===0 || b===0 ){
        return maxNum;
    }
    for(;i<=maxNum;i++){
        vper = minNum * i;
        if(vper % maxNum === 0){
            return vper;
            break;
        }
    }
}
/**
 * 获取数组的最小公倍数 
 * @param array
 * @returns
 */
function getArrayLCM(array){
	var  lcm =0;
	for(var i=0;i<array.length;i++){
		var item = Math.floor(array[i]);
		lcm = getLCM(lcm,item);
	}
	return lcm;
}

/**
 * 添加Map使用方法
 */
function Map() {
    this.keys = new Array();
    this.data = new Object();

    /**
     * 放入一个键值对
     * @param {String} key
     * @param {Object} value
     */
    this.put = function(key, value) {
        if(this.data[key] == null){
            this.keys.push(key);
        }
        this.data[key] = value;
        return true;
    };

    /**
     * 获取某键对应的值
     * @param {String} key
     * @return {Object} value
     */
    this.get = function(key) {
        return this.data[key];
    };

    /**
     * 删除一个键值对
     * @param {String} key
     */
    this.remove = function(key) {
        for(var i=0;i<this.keys.length;i++){
            if(key===this.keys[i]){
                var del_keys= this.keys.splice(i,1);
                this.data[key] = null;
                return this;
            }
        }
        return this;
    };
    
    this.clear = function(){
    	//清空
    	this.keys = [];
    	this.data = null;
    	//创建
    	this.keys = new Array();
    	this.data = new Object();
    	return true;
    }
    
    /**
     * 遍历Map,执行处理函数
     *
     * @param {Function} 回调函数 function(key,value,index){..}
     */
    this.each = function(fn){
        if(typeof fn != 'function'){
            return;
        }
        var len = this.keys.length;
        for(var i=0;i<len;i++){
            var k = this.keys[i];
            fn(k,this.data[k],i);
        }
    };

    /**
     * 获取键值数组
     * @return entity[{key,value},{key,value}]
     */
    this.entrySet = function() {
        var len = this.keys.length;
        var entrys = new Array(len);
        for (var i = 0; i < len; i++) {
            entrys[i] = {
                key : this.keys[i],
                value : this.data[this.keys[i]]
            };
        }
        return entrys;
    };

    /**
     * 判断Map是否为空
     */
    this.isEmpty = function() {
        return this.keys.length == 0;
    };

    /**
     * 获取键值对数量
     */
    this.size = function(){
        return this.keys.length;
    };

    this.containsKey=function(key){
        return this.keys.filter(function(v){
           if(v===key){
               return key;
           }
        }).length>0;
    };
    /**
     * 重写toString
     */
    this.toString = function(){
        var s = "{";
        for(var i=0;i<this.keys.length;i++){
            var k = this.keys[i];
            s += k+"="+this.data[k];
            if(this.keys.length>i+1){
                s+=','
            }
        }
        s+="}";
        return s;
    };
    /**
     * 解析字符串到Map
     * {a=A,b=B,c=B,}
     */
    this.parserStringAndAddMap=function(str){
        var count=0;
        if(str && str.length>0){
            str=str.trim();
            var startIndex=str.indexOf("{"),endIndex=str.lastIndexOf("}");
            if(startIndex!==-1 && endIndex!==-1){
                str=str.substring(startIndex+1,endIndex);
                var arrs= str.split(",");
                for(var i=0;i<arrs.length;i++){
                    var kv=arrs[i].trim();
                    if(kv.length>0 && kv.indexOf("=")!==-1){
                        var kv_arr=kv.split("=");
                        if(kv_arr.length==2){
                            if(this.put(kv_arr[0].trim(),kv_arr[1].trim())){
                                count++;
                            }else{
                                console.error('error: kv:'+kv);
                            }

                        }
                    }
                }
            }else{
                console.log("data error:"+str);
            }
        }else{
            console.log('data is not empty');
        }
        return count;
    };
}

function urlCheck(){
	$.ajax({
		type:"post",
		url:"http://localhost:8080/report/pageInit.do",
		data:"{}",
		dataType:"json",
		success : function(data){

		},
		error : function(){
			window.location.href="http://localhost:8080/report/Index.do";
		}
		
	})
}

//日期format方法
Date.prototype.format = function(fmt) 
{ //author: meizz 
var o = { 
"M+" : this.getMonth()+1,                 //月份 
"d+" : this.getDate(),                    //日 
"h+" : this.getHours(),                   //小时 
"m+" : this.getMinutes(),                 //分 
"s+" : this.getSeconds(),                 //秒 
"q+" : Math.floor((this.getMonth()+3)/3), //季度 
"S"  : this.getMilliseconds()             //毫秒 
}; 
if(/(y+)/.test(fmt)) 
fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
for(var k in o) 
if(new RegExp("("+ k +")").test(fmt)) 
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
return fmt; 
}
