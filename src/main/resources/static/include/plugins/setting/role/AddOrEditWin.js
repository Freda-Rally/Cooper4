/**
 *
 * 添加修改窗口
 *
 * Created by rally on 16/5/12.
 */
Ext.define('Cooper4.plugins.setting.role.AddOrEditWin',{

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
            title : '<span>角色信息</span>',
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
                id : 'roleFormPanel',
                name : 'roleFormPanel',
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
                    fieldLabel : '角色名称',
                    blankText : '请勿填空值..',
                    name : 'roleName',
                    id : 'roleName',
                    allowBlank : false
                },{
                    xtype : 'combo',
                    fieldLabel : '类型',
                    id : 'roleType',
                    name : 'roleType',
                    editable : false,
                    triggerAction : 'all',
                    queryMode: 'local',
                    allowBlank : false,
                    blankText : '请勿填控制.',
                    store : Ext.create('Ext.data.Store',{
                        fields: ['name', 'code'],
                        data : Cooper4.getCodeArray('ROLETYPE')
                    }),
                    displayField: 'name',
                    valueField: 'code'
                },{
                    fieldLabel : '描述',
                    blankText : '请勿填空值..',
                    name : 'roleDesc',
                    id : 'roleDesc'
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
                    id : 'roleId',
                    name : 'roleId'
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

        this.fireEvent('eventSave',this,Ext.getCmp('roleFormPanel'));
    },
    /**
     * 点击关闭
     */
    onCloseBtnClick : function(){

        this.hide();
    }

});
