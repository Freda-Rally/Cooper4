/**
 * 用户基本信息
 *
 * Created by rally on 16/5/13.
 */
Ext.define('Cooper4.plugins.setting.user.AddOrEditUserWin',{

    extend: 'Ext.window.Window',

    constructor : function(config){

        var that = this;

        this.callParent([Ext.apply({

            layout : 'fit',
            width : 560,
            height : 395,
            resizable : false,
            draggable : true,
            closeAction : 'hide',
            title : '<span>用户信息</span>',
            modal : true,
            collapsible : true,
            titleCollapse : true,
            maximizable : false,
            buttonAlign : 'right',
            border : false,
            animCollapse : true,
            pageY : 40,
            pageX : document.body.clientWidth / 2 - 560 / 2,
            animateTarget : Ext.getBody(),
            constrain : true,

            items : [Ext.create('Ext.form.Panel',{
                id : 'userFormPanel',
                name : 'userFormPanel',
                labelAlign : 'right',
                labelWidth : 110,
                frame : false,
                layout: 'anchor',
                bodyPadding: 5,
                defaults: {
                    anchor: '99%'
                },
                items: [{
                    xtype:'fieldset',
                    title: '基本信息',
                    collapsible: false,
                    bodyPadding: 15,
                    layout : 'column',
                    items : [{
                        columnWidth : 0.76,
                        bodyPadding: 5,
                        border : false,
                        items : [{
                            fieldLabel : '用户名称',
                            width : '100%',
                            xtype : 'textfield',
                            blankText : '请勿填空值.',
                            allowBlank : false,
                            name : 'userName',
                            id : 'userName'
                        },{
                            fieldLabel : '账号',
                            width : '100%',
                            blankText : '请勿填空值.',
                            xtype : 'textfield',
                            allowBlank : false,
                            name : 'account',
                            id : 'account'
                        },{
                            xtype : 'combo',
                            fieldLabel : '性别',
                            width : '100%',
                            id : 'userSex',
                            name : 'userSex',
                            editable : false,
                            triggerAction : 'all',
                            queryMode: 'local',
                            allowBlank : false,
                            blankText : '请勿填控制.',
                            store : Ext.create('Ext.data.Store',{
                                fields: ['name', 'code'],
                                data : Cooper4.getCodeArray('SEX')
                            }),
                            displayField: 'name',
                            valueField: 'code'
                        },{
                            xtype : 'combo',
                            fieldLabel : '类型',
                            width : '100%',
                            id : 'userType',
                            name : 'userType',
                            editable : false,
                            triggerAction : 'all',
                            queryMode: 'local',
                            allowBlank : false,
                            blankText : '请勿填空值.',
                            store : Ext.create('Ext.data.Store',{
                                fields: ['name', 'code'],
                                data : Cooper4.getCodeArray('USERTYPE')
                            }),
                            displayField: 'name',
                            valueField: 'code'
                        },{
                            xtype : 'combo',
                            fieldLabel : '锁定',
                            width : '100%',
                            id : 'locked',
                            name : 'locked',
                            editable : false,
                            triggerAction : 'all',
                            queryMode: 'local',
                            allowBlank : false,
                            blankText : '请勿填空值.',
                            store : Ext.create('Ext.data.Store',{
                                fields: ['name', 'code'],
                                data : Cooper4.getCodeArray('LOCKED')
                            }),
                            displayField: 'name',
                            valueField: 'code'
                        },{
                            xtype : 'combo',
                            fieldLabel : '状态',
                            width : '100%',
                            id : 'status',
                            name : 'status',
                            editable : false,
                            triggerAction : 'all',
                            queryMode: 'local',
                            allowBlank : false,
                            blankText : '请勿填空值.',
                            store : Ext.create('Ext.data.Store',{
                                fields: ['name', 'code'],
                                data : Cooper4.getCodeArray('STATUS')
                            }),
                            displayField: 'name',
                            valueField: 'code'
                        }]
                    },{//照片
                        columnWidth : 0.229,
                        bodyPadding: 5,
                        border : false,
                        items : [Ext.create('Ext.Img', {
                            src: '/framework/cooper4/img/defaut/photo/default.jpg',
                            height : 140,
                            width : 110,
                            border : true,
                            id : 'photo',
                            name : 'photo'
                        }),{
                            xtype : 'filefield',
                            blankText : '请选择头像',
                            width : 110,
                            name : 'upLoadFile',
                            id : 'upLoadFile',
                            buttonText: '头像',
                            listeners : [{
                                'change' : function(btn, value, eOpts ){

                                    var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/;
                                    if ( img_reg.test(value) ) {
                                        var img = Ext.getCmp('photo');
                                        var file = btn.fileInputEl.dom.files[0];
                                        var url = URL.createObjectURL(file);
                                        img.setSrc(url);
                                        Ext.getCmp('isUploadPhoto').setValue('1');
                                    }
                                }
                            }]
                        }]
                    },{
                        columnWidth : 1,
                        bodyPadding: 5,
                        border : false,
                        items : [{
                            fieldLabel : '联系电话',
                            blankText : '请勿填空值..',
                            xtype : 'textfield',
                            allowBlank : false,
                            width : '100%',
                            name : 'userTel',
                            width : '100%',
                            id : 'userTel'
                        },{
                            fieldLabel : '用户描述',
                            blankText : '请勿填空值..',
                            allowBlank : false,
                            width : '100%',
                            xtype : 'textareafield',
                            grow : false,
                            name : 'userDesc',
                            id : 'userDesc'
                        },{
                            xtype : 'hiddenfield',
                            id : 'userId',
                            name : 'userId'
                        },{
                            xtype : 'hiddenfield',
                            id : 'userPhoto',
                            name : 'userPhoto'
                        },{
                            xtype : 'hiddenfield',
                            id : 'deptId',
                            name : 'deptId'
                        },{
                            xtype : 'hiddenfield',
                            id : 'isUploadPhoto',
                            name : 'isUploadPhoto',
                            value : '0'
                        },{
                            xtype : 'hiddenfield',
                            id : 'submitMode',
                            name : 'submitMode'
                        }]
                    }]
                }]
            })],
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
        },config)]);
    },
    /**
     * 点击保存.
     */
    onSaveBtnClick : function(){

        this.fireEvent('eventSave',this,Ext.getCmp('userFormPanel'));
    },
    /**
     * 点击关闭
     */
    onCloseBtnClick : function(){

        this.hide();
    }

});
