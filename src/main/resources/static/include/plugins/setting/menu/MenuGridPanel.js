/**
 *
 * 菜单列表
 *
 * Created by rally on 16/5/11.
 */
Ext.define('Cooper4.plugins.setting.menu.MenuGridPanel',{

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

                id : 'menuGrid',
                name : 'menuGrid',
                frame : true,
                autoScroll : true,
                stripeRows : true, // 斑马线
                border : false,
                forceFit : true,
                maskOnDisable : true,

                store : Ext.create('Ext.data.Store',{

                    storeId : 'menuStore',
                    pageSize : 50,

                    fields:[{
                        name : 'menuId'
                    },{
                        name : 'menuName'
                    }, {
                        name : 'parentId'
                    }, {
                        name : 'parentName'
                    },{
                        name : 'iconCls'
                    },{
                        name : 'menuRequest'
                    },{
                        name : 'menuLeaf'
                    },{
                        name : 'sortNo'
                    },{
                        name : 'menuType'
                    },{
                        name : 'status'
                    },{
                        name : 'editMode'
                    },{
                        name : 'menuDesc'
                    }],
                    proxy : {
                        type : 'ajax',
                        url  : '/menu/list4Page.freda',
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
                            var new_params = {
                                queryContent : Ext.getCmp('queryContent').getValue(),
                                parentId : Cooper4.GLOBAL_PARAMS.parentId};
                            Ext.apply(store.proxy.extraParams, new_params);
                        }
                    }]
                }),
                columns : [{
                    xtype: 'rownumberer'
                },{
                    text : '名称',
                    dataIndex : 'menuName'
                },{
                    text : '请求插件',
                    dataIndex: 'menuRequest'
                },{
                    text : '类型',
                    dataIndex : 'menuType',
                    renderer : function(value){
                        return Cooper4.getCodeText('MENUTYPE',value);
                    }
                },{
                    text : '描述',
                    dataIndex: 'menuDesc'
                },{
                    text : '图标CSS类',
                    dataIndex : 'iconCls'
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
                            return "<font color=green>" + Cooper4.getCodeText('STATUS',value) + "</font>";
                        else
                            return "<font color=red>" + Cooper4.getCodeText('STATUS',value) + "</font>";
                    }
                },{
                    text : '创建时间',
                    dataIndex : 'createTime'
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

                                Ext.data.StoreManager.lookup('menuStore').load();
                            }
                        }
                    }
                },{
                    text : '查询',
                    iconCls : 'page_findIcon',
                    handler : function() {

                        Ext.data.StoreManager.lookup('menuStore').load();
                    }
                }],
                //分页工具栏
                dockedItems : [{
                    xtype : 'pagingtoolbar',
                    store : Ext.data.StoreManager.lookup('menuStore'),
                    dock : 'bottom',
                    displayInfo : true,
                    plugins: new Ext.ux.ProgressBarPager()
                }],
                //单选或多选.
                selModel :{
                    selType : 'checkboxmodel',
                    mode  : 'SINGLE'
                }
            })]
        },config)]);
    },
    /**
     * 添加按钮触发.
     */
    onAddBtnClick : function(mode){

        this.fireEvent('eventOnAddBtnClick',Ext.getCmp('menuGrid'),mode);
    },
    /**
     * 修改点击触发.
     */
    onEditBtnClick : function(mode){

        this.fireEvent('eventOnEditBtnClick',Ext.getCmp('menuGrid'),mode);
    },
    /**
     * 删除点击触发.
     */
    onDeleteBtnClick : function(){

        this.fireEvent('eventOnDeleteBtnClick',Ext.getCmp('menuGrid'));
    }
});
