/**
 *
 * 添加修改时用的window
 *
 * Created by rally on 16/5/5.
 */
Ext.define('Cooper4.plugins.setting.code.AddOrEditWin',{

    extend: 'Ext.window.Window',

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            ayout : 'fit',
            width : 450,
            height : 255,
            resizable : false,
            draggable : true,
            closeAction : 'hide',
            title : '<span>数据字典信息</span>',
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
                id : 'codeFormPanel',
                name : 'codeFormPanel',
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
                    fieldLabel : '字典集合名称*',
                    blankText : '请勿填空值..',
                    name : 'fieldId',
                    id : 'fieldId',
                    allowBlank : false
                },{
                    fieldLabel : '描述',
                    blankText : '请勿填空值..',
                    name : 'fieldName',
                    id : 'fieldName'
                },{
                    fieldLabel : '名称',
                    blankText : '请勿填空值..',
                    name : 'codeDesc',
                    id : 'codeDesc',
                    allowBlank : false
                },{
                    fieldLabel : '值',
                    blankText : '请勿填空值..',
                    name : 'code',
                    id : 'code',
                    xtype : 'numberfield',
                    maxValue: 99,
                    minValue: 0,
                    step: 1,
                    allowBlank : false
                },{
                    fieldLabel : '排序号',
                    blankText : '请勿填空值..',
                    name : 'sortNo',
                    id : 'sortNo',
                    xtype : 'numberfield',
                    maxValue: 99,
                    minValue: 0,
                    step: 1,
                    value:0,
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
                    xtype : 'hiddenfield',
                    id : 'codeId',
                    name : 'codeId'
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

        this.fireEvent('eventSave',this,Ext.getCmp('codeFormPanel'));
    },
    /**
     * 点击关闭
     */
    onCloseBtnClick : function(){

        win.hide();
    }

});
