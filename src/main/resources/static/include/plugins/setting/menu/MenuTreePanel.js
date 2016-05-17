/**
 *
 * 左边的树形结构
 *
 * Created by rally on 16/5/11.
 */
Ext.define('Cooper4.plugins.setting.menu.MenuTreePanel',{

    extend: 'Ext.panel.Panel',

    //requires : ['Ext.ux.ProgressBarPager'],

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            title : '菜单树',
            id : 'menuTreePanel',
            collapsible : true,
            tools : [{
                type: 'refresh',
                handler : function() {
                    Ext.getCmp('menuTree').store.load({
                        node : Ext.getCmp('menuTree').getRootNode()
                    });

                }
            }],
            width : 190,
            layout : 'fit',
            border : true,
            split : false,
            region : 'west',
            autoScroll : true,

            items : [Ext.create('Ext.tree.Panel',{

                id : 'menuTree',
                name : 'menuTree',
                rootVisible: true,
                autoScroll : true,
                animate : false,
                useArrows : false,
                border : false,

                store: Ext.create("Ext.data.TreeStore",{

                    proxy: {
                        type : 'ajax',
                        url : '/menu/menuTreeInit.freda'
                    },
                    root : {
                        text : 'Cooper4',
                        //expanded: true,
                        id   : 'menu_1000001',
                        leaf : 0,
                        icon : '',
                        parent : 0
                    }
                }),
                listeners : [{
                    'itemclick' : function( accordionPanel, record, item, index, e, eOpts){

                        that.onTreeItemClick(record.get('id').split('_')[1],record.get('text'));
                    }
                }]
            })]

        },config)]);
    },
    /**
     * 点击
     * @param id
     */
    onTreeItemClick : function(id,superName){

        this.fireEvent('eventTreeItemClick',this,id,superName);
    }
});
