/**
 * 用户绑定角色窗口.
 *
 * Created by rally on 16/5/15.
 */
Ext.define('Cooper4.plugins.setting.user.AuthWin',{

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
            title : '<span>绑定角色</span>',
            modal : true,
            collapsible : true,
            titleCollapse : true,
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
                    id : 'roleTreePanel',
                    title : '角色绑定',
                    rootVisible: false,
                    autoScroll : true,
                    animate : false,
                    useArrows : false,
                    autoLoad : false,
                    border : false,

                    store: Ext.create("Ext.data.TreeStore",{
                        proxy: {
                            type: 'ajax',
                            url: '/authority/roleTreeWithUserAuth.freda?userId=' + Cooper4.GLOBAL_PARAMS.userId + '&userType=' + Cooper4.GLOBAL_PARAMS.userType
                        },
                        root : {
                            text : '角色列表',
                            id   : 'role_1',
                            leaf : 0,
                            icon : '',
                            parent : 0
                        }
                    }),

                    buttons : [{
                        text : '角色绑定保存',
                        handler : function(){

                            that.onSetRoleAuthBtnClick();
                        }
                    }]
                }]

            })]

        },config)]);
    },

    onSetRoleAuthBtnClick : function(){

        this.fireEvent('eventOnSetRoleAuthBtnClick',this,Ext.getCmp('roleTreePanel'));
    }

});