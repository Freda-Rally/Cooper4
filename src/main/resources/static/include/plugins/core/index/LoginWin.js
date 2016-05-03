/**
 *
 * Index Page Login Window JavaScript
 *
 * Created by rally on 16/4/25.
 */

Ext.define('Cooper4.plugins.core.index.LoginWin',{

    extend: 'Ext.window.Window',
    alias: 'widget.loginwindow',

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            renderTo : Ext.getBody(),
            layout : 'border',
            width : 400,
            height : 220,
            closeAction : 'hide',
            plain : true,
            //modal : true,
            pageX : document.body.clientWidth / 2 ,
            border : false,
            collapsible : false,
            titleCollapse : false,
            maximizable : false,
            draggable : false,
            closable : false,
            resizable : false,
            animateTarget : document.body,
            items : [Ext.create('Ext.panel.Panel',{
                autoTabs : true,
                region : 'center',
                deferredRender : false,
                frame : true,
                border : false,
                items : {
                    xtype : 'tabpanel',
                    id : 'loginTabs',
                    activeTab : 0,
                    height : 120,
                    border : false,
                    layout : 'fit',
                    items : [{
                        title : '身份验证',
                        xtype : 'form',
                        frame : true,
                        id : 'loginForm',
                        defaults : {
                            width : 330
                        },
                        //bodyStyle : 'padding:20 50 50 50;',
                        bodyPadding : 12,
                        defaultType : 'textfield',
                        labelWidth : 40,
                        labelSeparator : '：',
                        items : [{
                            fieldLabel : '账\t\t号',
                            name : 'account',
                            id : 'account',
                            cls : 'user',
                            blankText : '账号不能为空!',
                            maxLength : 20,
                            maxLengthText : '账号长度不可以超过20个字符!',
                            allowBlank : false,
                            value : 'admin',
                            listeners : {
                                specialkey : function(field, e) {
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('password').focus();
                                    }
                                }
                            }
                        }, {
                            fieldLabel : '密\t\t码',
                            name : 'password',
                            id : 'password',
                            cls : 'key',
                            inputType : 'password',
                            blankText : '密码不能为空!',
                            maxLength : 20,
                            maxLengthText : '密码长度不能超过20个字符!',
                            allowBlank : false,
                            value : '111111',
                            listeners : {
                                specialkey : function(field, e) {
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        login();
                                    }
                                }
                            }
                        }]
                    }, {
                        title : '公告',
                        contentEl : 'news-tab',
                        defaults : {
                            width : 230
                        }
                    }, {
                        title : '提示',
                        contentEl : 'tips-tab',
                        defaults : {
                            width : 230
                        }
                    }]
                }
            }),{
                region : 'north',
                contentEl : 'hello-tabs',
                heigth : 30
            }],
            buttons : [{
                text : '登\t\t录',
                handler : function() {
                    that.onLogin();
                }
            }]
        },config)]);
    },

    /**
     * 登录.
     */
    onLogin : function(){
        this.fireEvent('eventLogin',this,Ext.getCmp('loginForm'));
    }

});