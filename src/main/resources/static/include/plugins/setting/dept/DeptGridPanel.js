/**
 *
 * 部门表格
 *
 * Created by rally on 16/5/12.
 */
Ext.define('Cooper4.plugins.setting.dept.DeptGridPanel',{

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

                id : 'deptGrid',
                name : 'deptGrid',
                frame : true,
                autoScroll : true,
                stripeRows : true, // 斑马线
                border : false,
                forceFit : true,
                maskOnDisable : true,

                store : Ext.create('Ext.data.Store',{

                    storeId : 'deptStore',
                    pageSize : 50,

                    fields:[{
                        name : 'deptId'
                    },{
                        name : 'deptName'
                    }, {
                        name : 'deptDesc'
                    }, {
                        name : 'parentId'
                    },{
                        name : 'parentName'
                    },{
                        name : 'deptLeaf'
                    },{
                        name : 'customId'
                    },{
                        name : 'sortNo'
                    },{
                        name : 'status'
                    },{
                        name : 'createTime'
                    }],
                    proxy : {
                        type : 'ajax',
                        url  : '/organization/deptList4Page.freda',
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
                    dataIndex : 'deptName'
                },{
                    text : '描述',
                    dataIndex: 'deptDesc'
                },{
                    text : '自定义ID',
                    dataIndex : 'customId'
                },{
                    text : '排序号',
                    dataIndex : 'sortNo'
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

                                Ext.data.StoreManager.lookup('deptStore').load();
                            }
                        }
                    }
                },{
                    text : '查询',
                    iconCls : 'page_findIcon',
                    handler : function() {

                        Ext.data.StoreManager.lookup('deptStore').load();
                    }
                }],
                //分页工具栏
                dockedItems : [{
                    xtype : 'pagingtoolbar',
                    store : Ext.data.StoreManager.lookup('deptStore'),
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

        this.fireEvent('eventOnAddBtnClick',Ext.getCmp('deptGrid'),mode);
    },
    /**
     * 修改点击触发.
     */
    onEditBtnClick : function(mode){

        this.fireEvent('eventOnEditBtnClick',Ext.getCmp('deptGrid'),mode);
    },
    /**
     * 删除点击触发.
     */
    onDeleteBtnClick : function(){

        this.fireEvent('eventOnDeleteBtnClick',Ext.getCmp('deptGrid'));
    }
});
