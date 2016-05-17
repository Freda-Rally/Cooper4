/**
 * 权限窗口.
 *
 * Created by rally on 16/5/14.
 */
Ext.define('Cooper4.plugins.setting.role.AuthWin',{

    extend: 'Ext.window.Window',

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            layout : 'fit',
            width : 350,
            height : 370,
            resizable : false,
            draggable : true,
            closeAction : 'destroy',
            title : '<span>授权</span>',
            modal : true,
            collapsible : true,
            //titleCollapse : true,
            maximizable : false,
            buttonAlign : 'right',
            border : false,
            animCollapse : true,
            pageY : 40,
            pageX : document.body.clientWidth / 2 - 420 / 2,
            animateTarget : Ext.getBody(),
            constrain : true,

            items : [Ext.create('Ext.tab.Panel',{

                id : 'treeTabPanel',
                name : 'treeTabPanel',
                layout : 'fit',

                items : [{
                    xtype : 'treepanel',
                    id : 'menuTreePanel',
                    title : '菜单权限',
                    rootVisible: false,
                    autoScroll : true,
                    animate : false,
                    useArrows : false,
                    autoLoad : false,
                    border : false,

                    store: Ext.create("Ext.data.TreeStore",{
                        proxy: {
                            type : 'ajax',
                            url :'/authority/menuTreeWithRoleAuth.freda?roleId=' + Cooper4.GLOBAL_PARAMS.roleId + '&roleType=' + Cooper4.GLOBAL_PARAMS.roleType
                        },
                        root : {
                            text : '系统菜单',
                            id   : 'menu_1000001',
                            leaf : 0,
                            icon : '',
                            parent : 0
                        }
                    }),
                    buttons : [{
                        text : '菜单权限保存',
                        handler : function(){
                            that.onSetMenuBtnClick();
                        }
                    }]
                },{
                    xtype : 'treepanel',
                    id : 'userTreePanel',
                    title : '绑定人员',
                    rootVisible: false,
                    autoScroll : true,
                    animate : false,
                    useArrows : false,
                    autoLoad : false,
                    border : false,

                    store: Ext.create("Ext.data.TreeStore",{
                        proxy: {
                            type: 'ajax',
                            url: '/authority/userAndDeptTreeWithRoleAuth.freda?roleId=' + Cooper4.GLOBAL_PARAMS.roleId + '&roleType=' + Cooper4.GLOBAL_PARAMS.roleType
                        },
                        root : {
                            text : '',
                            id   : 'dept_1000001',
                            leaf : 0,
                            icon : '',
                            parent : 0
                        }
                    }),
                    buttons : [{
                        text : '绑定人员保存',
                        handler : function(){
                            that.onSetUserBtnClick();
                        }
                    }]
                }]
            })]
        },config)]);
    },
    /**
     * 点击保存绑定菜单
     */
    onSetMenuBtnClick : function(){

        this.fireEvent('eventSetMenuBtnClick',this,Ext.getCmp('menuTreePanel'));
    },
    /**
     * 点击保存绑定用户
     */
    onSetUserBtnClick : function(){

        this.fireEvent('eventSetUserBtnClick',this,Ext.getCmp('userTreePanel'));
    }
});
