/**
 *
 * 部门树
 *
 * Created by rally on 16/5/12.
 */

Ext.define('Cooper4.plugins.setting.user.DeptTreePanel',{

    extend: 'Ext.panel.Panel',

    //requires : ['Ext.ux.ProgressBarPager'],

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            title : '部门树',
            id : 'deptTreePanel',
            collapsible : true,
            tools : [{
                type: 'refresh',
                handler : function() {
                    Ext.getCmp('deptTree').store.load({
                        node : Ext.getCmp('deptTree').getRootNode()
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

                id : 'deptTree',
                name : 'deptTree',
                rootVisible: true,
                autoScroll : true,
                animate : false,
                useArrows : false,
                border : false,

                store: Ext.create("Ext.data.TreeStore",{

                    proxy: {
                        type : 'ajax',
                        url : '/organization/deptTreeInit.freda'
                    },
                    root : {
                        text : 'Cooper4',
                        //expanded: true,
                        id   : 'dept_1000001',
                        leaf : 0,
                        icon : '',
                        parent : 0
                    }
                }),
                listeners : [{
                    'itemclick' : function( accordionPanel, record, item, index, e, eOpts){

                        that.onTreeItemClick(record.get('id').split('_')[1],record.get('text'),record.get('leaf'));
                    }
                }]
            })]

        },config)]);
    },
    /**
     * 点击
     * @param id
     */
    onTreeItemClick : function(id,superName,leaf){

        this.fireEvent('eventTreeItemClick',this,id,superName,leaf);
    }
});
