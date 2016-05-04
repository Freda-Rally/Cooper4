/**
 * Main Page plugin
 *
 * Created by rally on 16/5/2.
 */

Ext.define("Cooper4.plugins.core.main.Index",{

    requires: [
        'Cooper4.plugins.core.main.LeftTreePanel',
        'Cooper4.ux.IFrameComponent'
    ],

    //centerPanel : {},

    constructor : function(){

        var that = this;

        //Tab.
        that.centerPanel = Ext.create("Ext.tab.Panel",{
            //activeTab: 0,
            region : 'center',
            xtype  : 'panel',
            layout : 'fit',
            items : [{
                layout : 'fit',
                title : '我的工作台',
                id : 'tab_myWorkPanel',
                items : [Ext.create('Cooper4.ux.IFrameComponent',{id : '_myWorkPanel' ,url : '/index/myBranchPageInit.freda'})]
            }]

        });
        //Menu Tree
        var leftTree = Ext.create('Cooper4.plugins.core.main.LeftTreePanel',{

            listeners : [{

                'eventChooseMenu' : function(leftTree,record){

                    that.createTab(record);
                }
            }]
        });

        //Viewport
        Ext.create('Ext.container.Viewport', {
            layout : 'border',
            maskText : '玩命加载中....',
            items : [{
                collapsible : false,
                width : 210,
                layout : 'fit',
                minSize : 160,
                maxSize : 280,
                border : false,
                split : true,
                region : 'west',
                autoScroll : true,
                items : [leftTree]
            },{
                region : 'center',
                layout : 'border',
                border : false,
                split : true,
                items : [that.centerPanel,Ext.create("Ext.panel.Panel",{
                    frame : true,
                    region : 'south',
                    layout : 'fit',
                    border : true,
                    height : 20,
                    contentEl : 'south'
                })]
            },{
                region : 'north',
                layout : 'fit',
                split : false,
                height : 50,
                border : false,
                items : [Ext.create("Ext.panel.Panel",{
                    frame : true,
                    contentEl : 'top',
                    split : true
                })]
            }],
            listeners : [{
                'beforerender' : function(o, eOpts){

                    //Ext.getBody().mask(this.maskText == '' ? "玩命加载中.." : this.maskText);
                }
            }]
        });
    },

    createTab : function(record){

        var that = this;

        if(record.get('leaf'))
        {
            var tab = that.centerPanel.getComponent('tab_' +record.get('id'));
            if(!tab)
            {
                tab = Ext.create("Ext.panel.Panel",{
                    title : record.get('text'),
                    layout : 'fit',
                    closable : true,
                    id : 'tab_' +record.get('id'),
                    items : [Ext.create('Cooper4.ux.IFrameComponent',{id : 'tab_component' + record.get('id'),url : '/index/tabPageInit.freda?mId='+record.get('id')})]
                });
                that.centerPanel.add(tab);
            }
            that.centerPanel.setActiveTab(tab);
        }

    }
});