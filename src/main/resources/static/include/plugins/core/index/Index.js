/**
 *
 * Index Page Class
 *
 * Created by rally on 16/4/27.
 */
Ext.define('Cooper4.plugins.core.index.Index',{

    requires : ['Cooper4.ux.Ajax',
                'Cooper4.plugins.core.index.LoginWin'],

    constructor : function(){

        var that = this;

        Ext.create('Cooper4.plugins.core.index.LoginWin',{

            listeners : {
                'eventLogin' : function(win,form){

                    that.loginAjax(form);
                }
            }
        }).show();

    },
    /**
     *登录.
     */
    loginAjax : function(form){

        var that = this;

        if(form.form.isValid()){

            form.form.submit({
                url : '/index/login.freda',
                waitTitle : "",
                method : 'POST',
                waitMsg : "登录验证中..",
                success : function(form, action) {

                    that.forwardPage2Main();
                },
                failure : function(form, action) {

                    Cooper4.showAlert(action.result.msg);
                }
            });
        }

    },
    /**
     * 登录成功后跳转至Main Page.
     */
    forwardPage2Main : function(){

        window.location.href = '/index/mainPageInit.freda';
    }
});
