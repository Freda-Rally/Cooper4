/**
 * Session在线列表.
 *
 * Created by rally on 16/5/15.
 */
Ext.define('Cooper4.plugins.setting.session.SessionGridPanel',{

    extend: 'Ext.panel.Panel',

    requires : ['Ext.ux.ProgressBarPager'],

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            region : 'center',
            layout : 'fit',
            border : false,
            autoLoad: false,
            split : true,
            viewConfig:{
                enableTextSelection:true
            },
            items : [Ext.create('Ext.grid.Panel',{

                id : 'sessionGrid',
                name : 'sessionGrid',
                frame : true,
                autoScroll : true,
                stripeRows : true, // 斑马线
                border : false,
                forceFit : true,
                maskOnDisable : true,
                //数据存储.
                store : Ext.create('Ext.data.Store',{
                    storeId:'sessionStore',
                    pageSize : 50,
                    fields:[{
                        name : 'sessionId'
                    },{
                        name : 'userId'
                    }, {
                        name : 'userName'
                    }, {
                        name : 'userAccount'
                    },{
                        name : 'ipAddr'
                    },{
                        name : 'explorer'
                    },{
                        name : 'createTime'
                    }],
                    proxy : {
                        type : 'ajax',
                        url  : '/session/list4Page.freda',
                        actionMethods: {
                            read: 'POST'
                        },
                        reader : {
                            type : 'json',
                            rootProperty : 'ROOT',
                            totalProperty : 'TOTALCOUNT'
                        }
                    },
                    listeners : [{
                        'beforeload' : function (store, options){
                            var new_params = { queryContent : Ext.getCmp('queryContent').getValue()};
                            Ext.apply(store.proxy.extraParams, new_params);
                        }
                    }]
                }),
                //列.
                columns : [{
                    xtype: 'rownumberer'
                },{
                    text : '用户名称',
                    dataIndex : 'userName'
                },{
                    text : '账号信息',
                    dataIndex : 'userAccount'
                },{
                    text : '地址',
                    dataIndex : 'ipAddr'
                },{
                    text : '浏览器',
                    dataIndex : 'explorer'
                },{
                    text : '创建时间',
                    dataIndex : 'createTime'
                }],
                //工具栏.
                tbar : ['-',{
                    xtype : 'textfield',
                    id : 'queryContent',
                    name : 'queryContent',
                    emptyText : '请输入..',//仅支持单一名称搜索.
                    width : 150,
                    enableKeyEvents : true,
                    listeners : {
                        specialkey : function(field, e) {
                            if (e.getKey() == Ext.EventObject.ENTER) {

                                Ext.data.StoreManager.lookup('sessionStore').load();
                            }
                        }
                    }
                },{
                    text : '查询',
                    iconCls : 'page_findIcon',
                    handler : function() {

                        Ext.data.StoreManager.lookup('sessionStore').load();
                    }
                },'->',{
                    xtype: 'button',
                    text: '会话管理',
                    menu: [{
                        text:'杀死会话',
                        iconCls : 'edit1Icon',
                        handler : function(){

                            that.onKillBtnClick();
                        }
                    }]
                }],
                //分页工具栏
                dockedItems : [{
                    xtype : 'pagingtoolbar',
                    store : Ext.data.StoreManager.lookup('sessionStore'),
                    dock : 'bottom',
                    displayInfo : true,
                    plugins: new Ext.ux.ProgressBarPager()
                }],
                //单选或多选.
                selModel :{
                    selType : 'checkboxmodel',
                    mode  : 'SIMPLE'
                }
            })]
        },config)]);
    },
    /**
     * 杀死会话按钮点击触发..
     */
    onKillBtnClick : function(){

        this.fireEvent('eventKillBtnClick',Ext.getCmp('sessionGrid'));
    }

});
