/**
 * 全局参数列表
 * 
 * Created by rally on 16/5/15.
 */
Ext.define('Cooper4.plugins.setting.param.ParamGridPanel',{

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

                id : 'paramGrid',
                name : 'paramGrid',
                frame : true,
                autoScroll : true,
                stripeRows : true, // 斑马线
                border : false,
                forceFit : true,
                maskOnDisable : true,
                //数据存储.
                store : Ext.create('Ext.data.Store',{
                    storeId:'paramStore',
                    pageSize : 50,
                    fields:[{
                        name : 'paramId'
                    },{
                        name : 'pKey'
                    }, {
                        name : 'pValue'
                    }, {
                        name : 'status'
                    },{
                        name : 'editMode'
                    },{
                        name : 'pDesc'
                    }],
                    proxy : {
                        type : 'ajax',
                        url  : '/params/list4Page.freda',
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
                    text : '参数键',
                    dataIndex : 'pKey'
                },{
                    text : '值',
                    dataIndex: 'pValue'
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
                            return "<font color=green>" + Cooper4.getCodeText('STATUS',value) + "</font>";
                        else
                            return "<font color=red>" + Cooper4.getCodeText('STATUS',value) + "</font>";
                    }
                },{
                    text : '描述',
                    dataIndex : 'pDesc'
                }],
                //工具栏.
                tbar : ['-',{
                    text : '新增',
                    iconCls : 'page_addIcon',
                    handler : function() {

                        that.onAddBtnClick(Cooper4.SUBMIT_MODE_ADD);
                    }
                },{
                    text : '编辑',
                    iconCls : 'page_edit_1Icon',
                    handler : function() {

                        that.onEditBtnClick(Cooper4.SUBMIT_MODE_EDIT);
                    }
                },{
                    text : '删除',
                    iconCls : 'page_delIcon',
                    handler : function() {

                        that.onDeleteBtnClick();
                    }
                },'-',{
                    xtype : 'textfield',
                    id : 'queryContent',
                    name : 'queryContent',
                    emptyText : '请输入..',//仅支持单一名称搜索.
                    width : 150,
                    enableKeyEvents : true,
                    listeners : {
                        specialkey : function(field, e) {
                            if (e.getKey() == Ext.EventObject.ENTER) {

                                Ext.data.StoreManager.lookup('paramStore').load();
                            }
                        }
                    }
                },{
                    text : '查询',
                    iconCls : 'page_findIcon',
                    handler : function() {

                        Ext.data.StoreManager.lookup('paramStore').load();
                    }
                },'->',{
                    text : '同步',
                    iconCls : 'arrow_switchIcon',
                    handler : function() {

                        that.onMoneySynBtnClick();
                    }
                }],
                //分页工具栏
                dockedItems : [{
                    xtype : 'pagingtoolbar',
                    store : Ext.data.StoreManager.lookup('paramStore'),
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
     * 添加按钮触发.
     */
    onAddBtnClick : function(mode){

        var that = this;

        this.fireEvent('eventOnAddBtnClick',Ext.getCmp('paramGrid'),mode);
    },
    /**
     * 修改点击触发.
     */
    onEditBtnClick : function(mode){

        var that = this;

        this.fireEvent('eventOnEditBtnClick',Ext.getCmp('paramGrid'),mode);
    },
    /**
     * 删除点击触发.
     */
    onDeleteBtnClick : function(){

        var that = this;

        this.fireEvent('eventOnDeleteBtnClick',Ext.getCmp('paramGrid'));
    },
    /**
     * 内存同步点击出发.
     */
    onMoneySynBtnClick : function(){

        var that = this;

        this.fireEvent('eventOnMoneySynBtnClick',Ext.getCmp('paramGrid'));
    }

});
