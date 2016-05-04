/**
 *
 * 工具类..JS的总体工具{}..
 *
 * Created by rally on 16/4/25.
 */


var Cooper4 = Cooper4 || {};

(function(){

    var enumerables = ['valueOf', 'toLocaleString', 'toString', 'constructor'],
        global = this;

    Cooper4.global = global;

    Cooper4.enumerables = enumerables;

    Cooper4.apply = function(object, config, defaults) {
        if (defaults) {
            Cooper4.apply(object, defaults);
        }

        if (object && config && typeof config === 'object') {
            var i, j, k;

            for (i in config) {
                object[i] = config[i];
            }

            if (enumerables) {
                for (j = enumerables.length; j--;) {
                    k = enumerables[j];
                    if (config.hasOwnProperty(k)) {
                        object[k] = config[k];
                    }
                }
            }
        }

        return object;
    };

    Cooper4.apply(Cooper4,{

        SUBMIT_MODE_EDIT : "edit",

        SUBMIT_MODE_ADD : "add",

        /**
         * 获得当前格式化时间.
         * @param now
         * @returns {string}
         */
        currentTime : function(now){

            var mm = now.getMinutes();
            var ss = now.getTime() % 60000;
            ss = (ss - (ss % 1000)) / 1000;
            var clock = now.getHours() +':';
            if(mm < 10){
                clock += '0';
            }
            clock += mm + ':';
            if(ss < 10) {
                clock += '0';
            }
            return now.getFullYear() + "-"+(now.getMonth()+1)+"-"+now.getDate() + " " + clock + ss;
        },

        /**
         *
         * 判断是否为手机号码..
         *
         * @param testStr
         * @returns {boolean}
         */
        isMobile : function(testStr){

            var reg = new RegExp('^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|8[0-9])\\d{8}$');
            if(reg.test(testStr))
                return true;
            else
                return false;
        },
        /**
         *
         * 将JS数组转换为JS字符串 表格复选框专用
         *
         * @param arrayChecked
         * @param id
         * @returns {string}
         */
        jsArray2JsString : function(arrayChecked, id) {

            var strChecked = "";
            for (var i = 0; i < arrayChecked.length; i++) {

                strChecked = strChecked + arrayChecked[i].get(id) + ',';
            }
            return strChecked.substring(0, strChecked.length - 1)
        },

        /**
         *
         * 日期格式化..
         *
         * @param value
         * @returns {*}
         */
        dateFormat : function(value) {

            if(null != value) {
                return Ext.Date.format(new Date(value),'Y-m-d H:i:s');
            } else {
                return null;
            }
        },

        /**
         *
         *系统提示
         *
         * @param msg
         */
        showWaitMsg : function(msg){

            Ext.MessageBox.show({
                title : '系统提示',
                msg : msg == null ? '正在处理数据,请稍候...' : msg,
                progressText : 'processing now,please wait...',
                width : 300,
                wait : true,
                waitConfig : {
                    interval : 150
                }
            });
        },
        /**
         *隐藏请求等待进度条窗口
         */
        hideWaitMsg : function() {

            Ext.MessageBox.hide();
        },
        /**
         *
         * 通过iFrame实现类ajax文件下载
         *
         * @param url
         */
        exportExcel : function(url) {

            var exportIframe = document.createElement('iframe');
            exportIframe.src = url;
            exportIframe.style.display = "none";
            document.body.appendChild(exportIframe);
            hideWaitMsg();
        },

        /**
         *
         * Alert -- ExtJS
         *
         * @param msg
         */
        showAlert : function(msg) {

            Ext.MessageBox.alert("温馨提示","<span>提示:</span>&nbsp;&nbsp;" + msg);
        }

    });
}());