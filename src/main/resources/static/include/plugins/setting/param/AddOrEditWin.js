/**
 * 新增修改全局参数窗口
 *
 * Created by rally on 16/5/15.
 */
Ext.define('Cooper4.plugins.setting.param.AddOrEditWin',{

    extend: 'Ext.window.Window',

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            layout : 'fit',
            width : 450,
            height : 205,
            resizable : false,
            draggable : true,
            closeAction : 'hide',
            title : '<span>全局参数信息</span>',
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

            items : [Ext.create('Ext.form.Panel',{
                id : 'paramFormPanel',
                name : 'paramFormPanel',
                labelAlign : 'right',
                labelWidth : 110,
                frame : false,
                layout: 'anchor',
                bodyPadding: 5,
                defaults: {
                    anchor: '99%'
                },
                defaultType: 'textfield',
                items: [{
                    fieldLabel : '参数键',
                    blankText : '请勿填空值..',
                    name : 'pKey',
                    id : 'pKey',
                    allowBlank : false
                },{
                    fieldLabel : '值',
                    blankText : '请勿填空值..',
                    name : 'pValue',
                    id : 'pValue',
                    allowBlank : false
                },{
                    xtype : 'combo',
                    fieldLabel : '状态',
                    id : 'status',
                    name : 'status',
                    editable : false,
                    triggerAction : 'all',
                    queryMode: 'local',
                    allowBlank : false,
                    blankText : '请勿填控制.',
                    store : Ext.create('Ext.data.Store',{
                        fields: ['name', 'code'],
                        data : Cooper4.getCodeArray('STATUS')
                    }),
                    displayField: 'name',
                    valueField: 'code'
                },{
                    fieldLabel : '描述',
                    blankText : '请勿填空值..',
                    name : 'pDesc',
                    id : 'pDesc'
                },{
                    xtype : 'hiddenfield',
                    id : 'paramId',
                    name : 'paramId'
                },{
                    xtype : 'hiddenfield',
                    id : 'submitMode',
                    name : 'submitMode'
                }],
                buttons : [{
                    text : '保存',
                    id : 'btnSave',
                    iconCls : 'acceptIcon',
                    handler : function() {
                        that.onSaveBtnClick();
                    }
                },{
                    text : '关闭',
                    id : 'btnClose',
                    iconCls : 'deleteIcon',
                    handler : function() {
                        that.onCloseBtnClick();
                    }
                }]
            })]
        },config)]);
    },
    /**
     * 点击保存.
     */
    onSaveBtnClick : function(){

        this.fireEvent('eventSave',this,Ext.getCmp('paramFormPanel'));
    },
    /**
     * 点击关闭
     */
    onCloseBtnClick : function(){

        this.hide();
    }

});
