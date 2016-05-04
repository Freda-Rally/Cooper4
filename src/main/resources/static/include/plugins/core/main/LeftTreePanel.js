/**
 *
 * 左边的菜单树
 *
 * Created by rally on 16/5/3.
 */

Ext.define('Cooper4.plugins.core.main.LeftTreePanel',{

    extend: 'Ext.panel.Panel',

    requires : ['Cooper4.ux.Ajax'],

    alias: 'widget.menutreepanel',

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            layout : 'fit',

            items : [Ext.create('Ext.tab.Panel',{
                layout : 'fit',
                activeTab : 0,
                items : [{//系统菜单
                    title : '系统菜单导航',
                    xtype : 'panel',
                    layout : 'fit',
                    items: [{
                        xtype : 'panel',
                        id : 'systemMenuPanel',
                        split : true,
                        layout : {
                            type : 'accordion',
                            //activeOnTop : true,
                            animate : true
                        },
                        border : false,
                        layoutConfig : {
                            //activeOnTop : true,
                            animate : true,
                            titleCollapse : true
                        },
                        listeners : {
                            'beforerender' : function(){

                                Ext.Ajax.request({//查询一级菜单

                                    url : '/index/menuPanelInit.freda?pId=1000001',

                                    success: function(response){

                                        var resultArray = Ext.JSON.decode(response.responseText);

                                        for(var i=0;i< resultArray.length;i++) {

                                            var accordionPanel = null;

                                            accordionPanel = Ext.create('Ext.panel.Panel',{
                                                autoScroll : true,
                                                title : resultArray[i].text,
                                                id : resultArray[i].id,
                                                items : [Ext.create("Ext.tree.Panel",{
                                                    id : 'menuTree_' + resultArray[i].id,
                                                    name : 'menuTree_' + resultArray[i].id,
                                                    rootVisible: false,
                                                    autoScroll : true,
                                                    animate : false,
                                                    useArrows : false,
                                                    border : false,
                                                    store : Ext.create("Ext.data.TreeStore",{
                                                        proxy : {
                                                            type : 'ajax',
                                                            //the store will get the content from the .json file
                                                            url : '/index/menuPanelInit.freda'
                                                        },
                                                        root : {
                                                            text : resultArray[i].text,
                                                            id   : resultArray[i].id,
                                                            leaf : 0,
                                                            icon : '',
                                                            parent : 0
                                                        }
                                                    }),
                                                    listeners : [{

                                                        'itemclick' : function( accordionPanel, record, item, index, e, eOpts){

                                                            that.createTab(record);
                                                        }
                                                    }]
                                                })]
                                            });

                                            Ext.getCmp('menuTree_' + resultArray[i].id).expandAll();

                                            Ext.getCmp('systemMenuPanel').add(accordionPanel);
                                        }
                                    }
                                });
                            }
                        }
                    }]
                },{//快捷菜单
                    title: '快捷菜单',
                    layout : 'fit',
                    items : [Ext.create("Ext.tree.Panel",{
                        id : 'quickMenuTree',
                        name : 'quickMenuTree',
                        rootVisible: false,
                        autoScroll : true,
                        animate : false,
                        useArrows : false,
                        border : false,
                        store : Ext.create("Ext.data.TreeStore",{
                            proxy : {
                                type : 'ajax',
                                //the store will get the content from the .json file
                                url : '/index/menuPanelInit.freda?isQuick=1'
                            },
                            root : {
                                text : '',
                                id   : '1000001',
                                leaf : 0,
                                icon : '',
                                parent : 0
                            }
                        }),
                        listeners : [{

                            'itemclick' : function( treePanel, record, item, index, e, eOpts){

                                that.createTab(record);
                            }
                        }]
                    })]
                }]
            })]

        },config)]);

    },

    createTab : function(record){

        this.fireEvent('eventChooseMenu',this,record);;
    }

});
