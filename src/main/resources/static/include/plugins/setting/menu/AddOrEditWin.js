/**
 *
 * 添加或编辑窗口
 *
 * Created by rally on 16/5/11.
 */
Ext.define('Cooper4.plugins.setting.menu.AddOrEditWin',{

    extend: 'Ext.window.Window',

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            ayout : 'fit',
            width : 450,
            height : 315,
            resizable : false,
            draggable : true,
            closeAction : 'hide',
            title : '<span>菜单信息</span>',
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
                id : 'menuFormPanel',
                name : 'menuFormPanel',
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
                    fieldLabel : '父级别菜单',
                    blankText : '请勿填空值..',
                    name : 'parentName',
                    id : 'parentName',
                    allowBlank : false,
                    disabled : true
                },{
                    fieldLabel : '名称',
                    blankText : '请勿填空值..',
                    name : 'menuName',
                    id : 'menuName'
                },{
                    fieldLabel : '请求地址',
                    blankText : '请勿填空值..',
                    name : 'menuRequest',
                    id : 'menuRequest',
                    allowBlank : false
                },{
                    fieldLabel : '配序号',
                    blankText : '请勿填空值..',
                    name : 'sortNo',
                    id : 'sortNo',
                    xtype : 'numberfield',
                    maxValue: 99,
                    minValue: 0,
                    value: 0,
                    step: 1,
                    allowBlank : false
                },{
                    fieldLabel : '图标CSS类',
                    blankText : '请勿填空值..',
                    name : 'iconCls',
                    id : 'iconCls',
                    allowBlank : false
                },{
                    fieldLabel : '描述',
                    blankText : '请勿填空值..',
                    name : 'menuDesc',
                    id : 'menuDesc',
                    allowBlank : false
                },{
                    xtype : 'combo',
                    fieldLabel : '类型',
                    id : 'menuType',
                    name : 'menuType',
                    editable : false,
                    triggerAction : 'all',
                    queryMode: 'local',
                    allowBlank : false,
                    blankText : '请勿填控制.',
                    store : Ext.create('Ext.data.Store',{
                        fields: ['name', 'code'],
                        data : Cooper4.getCodeArray('MENUTYPE')
                    }),
                    displayField: 'name',
                    valueField: 'code'
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
                    id : 'menuId',
                    name : 'menuId'
                },{
                    xtype : 'hiddenfield',
                    id : 'parentId',
                    name : 'parentId'
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

        this.fireEvent('eventSave',this,Ext.getCmp('menuFormPanel'));
    },
    /**
     * 点击关闭
     */
    onCloseBtnClick : function(){

        this.hide();
    }

});
