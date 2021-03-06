/**
 * 用户列表
 *
 * Created by rally on 16/5/13.
 */
Ext.define('Cooper4.plugins.setting.user.UserGridPanel',{

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

                id : 'userGrid',
                name : 'userGrid',
                frame : true,
                autoScroll : true,
                stripeRows : true, // 斑马线
                border : false,
                forceFit : true,
                maskOnDisable : true,

                store : Ext.create('Ext.data.Store',{

                    storeId : 'userStore',
                    pageSize : 50,

                    fields:[{
                        name : 'userId'
                    },{
                        name : 'userName'
                    }, {
                        name : 'account'
                    }, {
                        name : 'locked'
                    },{
                        name : 'status'
                    },{
                        name : 'userType'
                    },{
                        name : 'userDesc'
                    }],
                    proxy : {
                        type : 'ajax',
                        url  : '/organization/userList4Page.freda',
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
                                deptId : Cooper4.GLOBAL_PARAMS.parentId};
                            Ext.apply(store.proxy.extraParams, new_params);
                        }
                    }]
                }),
                columns : [{
                    xtype: 'rownumberer'
                },{
                    text : '用户名称',
                    dataIndex : 'userName'
                },{
                    text : '账号',
                    dataIndex: 'account'
                },{
                    text : '类型',
                    dataIndex : 'userType',
                    renderer : function(value){
                        return Cooper4.getCodeText('USERTYPE',value);
                    }
                },{
                    text : '锁定',
                    dataIndex : 'locked',
                    renderer : function(value){
                        if(value == 1)
                            return "<font color=red>" + Cooper4.getCodeText('LOCKED',value) + "</font>";
                        else
                            return Cooper4.getCodeText('LOCKED',value);
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
                    dataIndex : 'userDesc'
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

                                Ext.data.StoreManager.lookup('userStore').load();
                            }
                        }
                    }
                },{
                    text : '查询',
                    iconCls : 'page_findIcon',
                    handler : function() {

                        Ext.data.StoreManager.lookup('userStore').load();
                    }
                },'->',{
                    xtype: 'button',
                    text: '权限管理',
                    menu: [{
                        text:'绑定角色',
                        iconCls : 'edit1Icon',
                        handler : function() {

                            that.onRoleAuthBtnClick();
                        }
                    }]
                }],
                //分页工具栏
                dockedItems : [{
                    xtype : 'pagingtoolbar',
                    store : Ext.data.StoreManager.lookup('userStore'),
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

        this.fireEvent('eventOnAddBtnClick',Ext.getCmp('userGrid'),mode);
    },
    /**
     * 修改点击触发.
     */
    onEditBtnClick : function(mode){

        this.fireEvent('eventOnEditBtnClick',Ext.getCmp('userGrid'),mode);
    },
    /**
     * 删除点击触发.
     */
    onDeleteBtnClick : function(){

        this.fireEvent('eventOnDeleteBtnClick',Ext.getCmp('userGrid'));
    },
    /**
     * 绑定角色按钮点击出发.
     */
    onRoleAuthBtnClick : function(){

        this.fireEvent('eventOnRoleAuthBtnClick',Ext.getCmp('userGrid'));
    }
});
