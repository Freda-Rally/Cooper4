/**
 *
 * 展示时候使用的window
 *
 * Created by rally on 16/5/5.
 */

Ext.define('Cooper4.plugins.setting.code.CodeGridPanel',{

    extend: 'Ext.panel.Panel',

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            region : 'center',
            layout : 'fit',
            border : false,
            autoLoad: false,
            split : true,

            items : [Ext.create('Ext.grid.Panel',{

                id : 'codeGrid',
                name : 'codeGrid',
                frame : true,
                autoScroll : true,
                stripeRows : true, // 斑马线
                border : false,
                forceFit : true,
                maskOnDisable : true,
                //数据存储.
                store : Ext.create('Ext.data.Store',{
                    storeId:'codeStore',
                    pageSize : 50,
                    fields:[{
                        name : 'codeId'
                    },{
                        name : 'fieldId'
                    }, {
                        name : 'fieldName'
                    }, {
                        name : 'code'
                    },{
                        name : 'codeDesc'
                    },{
                        name : 'status'
                    },{
                        name : 'editMode'
                    },{
                        name : 'sortNo'
                    },{
                        name : 'createTime'
                    }],
                    proxy : {
                        type : 'ajax',
                        url  : '/code/list4Page.freda',
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
                    text : '集合名称',
                    dataIndex : 'fieldId'
                },{
                    text : '字典描述',
                    dataIndex: 'fieldName'
                },{
                    text : '名称',
                    dataIndex: 'codeDesc'
                },{
                    text : '值',
                    dataIndex : 'code'
                },{
                    text : '排序号',
                    dataIndex : 'sortNo'
                },{
                    text : '权限',
                    dataIndex : 'editMode',
                    renderer : function(value){
                        return Cooper4.getCodeText('EDITMODE',value);
                    }
                },{
                    text : '状态',
                    dataIndex : 'status',
                    renderer : function(value){
                        if(value == 1)
                            return "<font color=green>" + getCodeText('STATUS',value) + "</font>";
                        else
                            return "<font color=red>" + getCodeText('STATUS',value) + "</font>";
                    }
                },{
                    text : '创建时间',
                    dataIndex : 'createTime'
                }],
                //工具栏.
                tbar : [{
                    xtype : 'textfield',
                    id : 'queryContent',
                    name : 'queryContent',
                    emptyText : '请输入..',//仅支持单一名称搜索.
                    width : 150,
                    enableKeyEvents : true,
                    listeners : {
                        specialkey : function(field, e) {
                            if (e.getKey() == Ext.EventObject.ENTER) {
                                var queryContent = Ext.getCmp('queryContent').getValue();
                                Ext.data.StoreManager.lookup('codeStore').load({
                                    params : {
                                        start : 0,
                                        limit : 50,
                                        queryContent : queryContent
                                    }
                                });
                            }
                        }
                    }
                },{
                    text : '查询',
                    iconCls : 'page_findIcon',
                    handler : function() {
                        var queryContent = Ext.getCmp('queryContent').getValue();
                        Ext.data.StoreManager.lookup('codeStore').load({
                            params : {
                                start : 0,
                                limit : 50,
                                queryContent : queryContent
                            }
                        });
                    }
                },'->','-',{
                    text : '添加',
                    iconCls : 'page_addIcon',
                    handler : function() {
                    }
                },'-',{
                    text : '编辑',
                    iconCls : 'page_edit_1Icon',
                    handler : function() {
                    }
                },'-',{
                    text : '删除',
                    iconCls : 'page_delIcon',
                    handler : function() {
                    }
                },'-',{
                    text : '内存同步',
                    iconCls : 'arrow_switchIcon',
                    handler : function() {
                    }
                }],
                //分页工具栏
                dockedItems : [{
                    xtype : 'pagingtoolbar',
                    store : Ext.data.StoreManager.lookup('codeStore'),
                    dock : 'bottom',
                    displayInfo : true
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
     * 添加按钮触发.
     */
    onAddBtnClick : function(){},
    /**
     * 修改点击触发.
     */
    onEditBtnClick : function(){},
    /**
     * 删除点击触发.
     */
    onDeleteBtnClick : function(){},
    /**
     * 内存同步点击出发.
     */
    onMoneySynBtnClick : function(){}

});
