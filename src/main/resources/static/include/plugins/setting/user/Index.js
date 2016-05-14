/**
 * 人员管理
 *
 * Created by rally on 16/5/13.
 */
Ext.define('Cooper4.plugins.setting.user.Index',{

    requires : ['Cooper4.plugins.setting.user.DeptTreePanel',
        'Cooper4.plugins.setting.user.UserGridPanel',
        'Cooper4.plugins.setting.user.AddOrEditUserWin',
        'Cooper4.ux.Ajax'],


    constructor : function(){

        var that = this;

        Ext.create('Ext.container.Viewport',{
            layout : 'border',
            items : [Ext.create('Cooper4.plugins.setting.user.UserGridPanel',{

                listeners : [{
                    'eventOnAddBtnClick' : function(grid,mode){

                        that.addOrEditWinInit(grid,mode);
                    }
                },{
                    'eventOnEditBtnClick' : function(grid,mode){

                        that.addOrEditWinInit(grid,mode);
                    }
                },{
                    'eventOnDeleteBtnClick' : function(grid){

                        that.deleteUser(grid);
                    }
                },{
                    'eventOnRoleAuthBtnClick' : function(grid){

                        that.setAuthWinInit(grid);
                    }
                }]
            }),Ext.create('Cooper4.plugins.setting.user.DeptTreePanel',{

                listeners : [{

                    'eventTreeItemClick' : function(panel,id,superName,leaf){

                        if(leaf == 1){

                            Cooper4.GLOBAL_PARAMS.parentId = id;

                            Ext.data.StoreManager.lookup('userStore').load();
                        }
                    }
                }]
            })]
        });

        that.addOrEditWin = Ext.create('Cooper4.plugins.setting.user.AddOrEditUserWin',{

            listeners : {

                'eventSave' : function(win,form){

                    that.addOrEditUser(form);
                }
            }

        });

    },
    /**
     * 窗口显示初始化.
     * @param grid
     * @param mode
     */
    addOrEditWinInit : function(grid,mode){

        if(mode == 'add'){

            var record = Ext.getCmp('deptTree').getSelection()[0];
            if(Ext.isEmpty(record))
            {
                Cooper4.showAlert('请选择往哪个部门下添加.');
                return ;
            }
            if(record.get('leaf') != 1)
            {
                Cooper4.showAlert('用户只能添加至叶子部门.');
                return ;
            }
            Ext.getCmp('userFormPanel').getForm().reset();
            Ext.getCmp('submitMode').setValue(Cooper4.SUBMIT_MODE_ADD);
            Ext.getCmp('deptId').setValue(Cooper4.GLOBAL_PARAMS.parentId);
            Ext.getCmp('photo').setSrc('/framework/cooper4/img/defaut/photo/default.jpg');
        }else{

            var record = grid.getSelectionModel().getSelection();
            if(Ext.isEmpty(record)) {

                Cooper4.showAlert("请选择修改项.");
                return ;
            }
            if(record.length > 1){

                Cooper4.showAlert("请选择单一项.");
                return ;
            }
            Ext.getCmp('userFormPanel').getForm().load({

                url : '/organization/userLoad.freda?userId=' + record[0].get('userId'),
                success : function(form, action){

                    if(Ext.getCmp('userPhoto').getValue() != null && Ext.getCmp('userPhoto').getValue() != '') {

                        Ext.getCmp('photo').setSrc( Ext.getCmp('userPhoto').getValue());
                    }else {

                        Ext.getCmp('photo').setSrc('/framework/cooper4/img/defaut/photo/default.jpg');
                    }
                    Ext.getCmp('submitMode').setValue(Cooper4.SUBMIT_MODE_EDIT);
                }
            });
        }
        Ext.getCmp('isUploadPhoto').setValue('0');
        this.addOrEditWin.show();
    },
    /**
     * 提交FORM
     * @param form
     */
    addOrEditUser : function(form){

        var that = this;

        var submitUrl = "";

        if(Ext.getCmp('submitMode').getValue() == 'add') {

            submitUrl = "/organization/userAdd.freda";
        }
        else{

            submitUrl = "/organization/userEdit.freda";
        }

        Ext.Msg.show({
            title : "提示",
            message: "确认要更改数据?",
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function(btn) {
                if (btn === 'yes') {
                    //sumit
                    form.getForm().submit({
                        clientValidation: true,
                        url : submitUrl,
                        waitTitle : "",
                        waitMsg : "数据存储中..请稍后..",
                        success: function(form, action) {
                            that.addOrEditWin.hide();
                            Cooper4.showAlert(action.result.msg);
                            Ext.data.StoreManager.lookup('userStore').reload();
                        },
                        failure: function(form, action) {
                            switch (action.failureType) {
                                case Ext.form.action.Action.CLIENT_INVALID:
                                    Cooper4.showAlert("请正确填写..");
                                    break;
                                case Ext.form.action.Action.CONNECT_FAILURE:
                                    Cooper4.showAlert('保存数据发生错误..请稍后再试.或联系管理员..');
                                    break;
                                case Ext.form.action.Action.SERVER_INVALID:
                                    Cooper4.showAlert(action.result.msg);
                            }
                        }
                    });
                }
            }
        });
    },
    /**
     * 删除菜单.
     * @param grid
     */
    deleteUser : function(grid){

        var record = grid.getSelectionModel().getSelection();
        if(Ext.isEmpty(record))
        {
            Cooper4.showAlert("请选择删除项.");
            return ;
        }

        Ext.Msg.show({
            title:"提示",
            message: "确定需要删除该选中项?",
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function(btn) {
                if (btn === 'yes') {
                    Cooper4.ux.Ajax.request({
                        url : '/organization/userDelete.freda',
                        method : 'POST',
                        params : {
                            ids : Cooper4.jsArray2JsString(record,'userId')
                        },
                        success: function(response) {
                            var result = Ext.JSON.decode(response.responseText);
                            Cooper4.showAlert(result.msg);
                            Ext.data.StoreManager.lookup('userStore').reload();
                        }
                    });
                }
            }
        });
    },
    /**
     * 权限窗口初始化..
     * @param grid
     */
    setAuthWinInit : function(grid){

        var that = this;

        var record = grid.getSelectionModel().getSelection();
        if(Ext.isEmpty(record)) {

            Cooper4.showAlert("请选择授权项.");
            return ;
        }
        if(record.length > 1){

            Cooper4.showAlert("请选择单一项.");
            return ;
        }

        Cooper4.GLOBAL_PARAMS.userId = record[0].get('userId');
        Cooper4.GLOBAL_PARAMS.userType = record[0].get('userType');

        Ext.create('Cooper4.plugins.setting.user.AuthWin',{

            listeners : [{

                'eventOnSetRoleAuthBtnClick' : function(win,treePanel){

                    that.submitSetAuth(win,treePanel);
                }
            }]

        }).show();

        Ext.getCmp('roleTreePanel').expandAll();
    },
    /**
     * 提交绑定.
     * @param win
     * @param treePanel
     */
    submitSetAuth : function(win,treePanel){

        Ext.Msg.show({
            title:'提示',
            message: '确定设置权限?',
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function(btn) {
                if (btn === 'yes') {
                    Cooper4.ux.Ajax.request({
                        url : '/authority/role2User.freda',
                        method : 'POST',
                        params : {
                            ids : Cooper4.jsArray2JsString(treePanel.getChecked(),'id'),
                            userId : Cooper4.GLOBAL_PARAMS.userId
                        },
                        success: function(response) {
                            var result = Ext.JSON.decode(response.responseText);
                            Cooper4.showAlert(result.msg);
                            win.close();
                        }
                    });
                }
            }
        });

    }
});
