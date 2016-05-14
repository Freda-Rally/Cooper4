/**
 *
 * 角色菜单
 *
 * Created by rally on 16/5/12.
 */
/**
 *
 * 展示时候使用的window
 *
 * Created by rally on 16/5/5.
 */

Ext.define('Cooper4.plugins.setting.role.RoleGridPanel',{

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

                id : 'roleGrid',
                name : 'roleGrid',
                frame : true,
                autoScroll : true,
                stripeRows : true, // 斑马线
                border : false,
                forceFit : true,
                maskOnDisable : true,
                //数据存储.
                store : Ext.create('Ext.data.Store',{
                    storeId:'roleStore',
                    pageSize : 50,
                    fields:[{
                        name : 'roleId'
                    },{
                        name : 'roleName'
                    }, {
                        name : 'roleType'
                    }, {
                        name : 'editMode'
                    },{
                        name : 'status'
                    },{
                        name : 'roleDesc'
                    },{
                        name : 'createTime'
                    }],
                    proxy : {
                        type : 'ajax',
                        url  : '/organization/roleList4Page.freda',
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
                    text : '角色名称',
                    dataIndex : 'roleName'
                },{
                    text : '类型',
                    dataIndex: 'roleType',
                    renderer : function(value){
                        return Cooper4.getCodeText('ROLETYPE',value);
                    }
                },{
                    text : '描述',
                    dataIndex : 'roleDesc'
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

                                Ext.data.StoreManager.lookup('roleStore').load();
                            }
                        }
                    }
                },{
                    text : '查询',
                    iconCls : 'page_findIcon',
                    handler : function() {

                        Ext.data.StoreManager.lookup('roleStore').load();
                    }
                },'->',{
                    xtype: 'button',
                    text: '权限管理',
                    menu: [{
                        text:'绑定菜单',
                        iconCls : 'edit1Icon',
                        handler : function(){
                            that.onAuthBtnClick('menu');
                        }
                    },{
                        text : '绑定用户',
                        iconCls : 'edit1Icon',
                        handler : function(){
                            that.onAuthBtnClick('user');
                        }
                    }]
                }],
                //分页工具栏
                dockedItems : [{
                    xtype : 'pagingtoolbar',
                    store : Ext.data.StoreManager.lookup('roleStore'),
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

        this.fireEvent('eventOnAddBtnClick',Ext.getCmp('roleGrid'),mode);
    },
    /**
     * 修改点击触发.
     */
    onEditBtnClick : function(mode){

        var that = this;

        this.fireEvent('eventOnEditBtnClick',Ext.getCmp('roleGrid'),mode);
    },
    /**
     * 删除点击触发.
     */
    onDeleteBtnClick : function(){

        var that = this;

        this.fireEvent('eventOnDeleteBtnClick',Ext.getCmp('roleGrid'));
    },
    /**
     * 权限Menu点击.
     */
    onAuthBtnClick : function(ac){

        this.fireEvent('eventOnAuthBtnClick',Ext.getCmp('roleGrid'),ac);
    }

});

