/**
 *
 * 部门管理
 *
 * Created by rally on 16/5/12.
 */
Ext.define('Cooper4.plugins.setting.dept.Index',{

    requires : ['Cooper4.plugins.setting.dept.DeptTreePanel',
                'Cooper4.plugins.setting.dept.DeptGridPanel',
                'Cooper4.plugins.setting.dept.AddOrEditWin',
                'Cooper4.ux.Ajax'],


    constructor : function(){

        var that = this;

        Ext.create('Ext.container.Viewport',{
            layout : 'border',
            items : [Ext.create('Cooper4.plugins.setting.dept.DeptGridPanel',{

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

                        that.deleteDept(grid);
                    }
                }]
            }),Ext.create('Cooper4.plugins.setting.dept.DeptTreePanel',{

                listeners : [{

                    'eventTreeItemClick' : function(panel,id,superName){

                        Cooper4.GLOBAL_PARAMS.parentId = id;

                        Cooper4.GLOBAL_PARAMS.parentName = superName;

                        Ext.data.StoreManager.lookup('deptStore').load();

                    }
                }]
            })]
        });

        that.addOrEditWin = Ext.create('Cooper4.plugins.setting.dept.AddOrEditWin',{

            listeners : {

                'eventSave' : function(win,form){

                    that.addOrEditDept(form);
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

            Ext.getCmp('deptFormPanel').getForm().reset();
            Ext.getCmp('submitMode').setValue(Cooper4.SUBMIT_MODE_ADD);
            Ext.getCmp('parentName').setValue(Cooper4.GLOBAL_PARAMS.parentName);
            Ext.getCmp('parentId').setValue(Cooper4.GLOBAL_PARAMS.parentId);

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
            Ext.getCmp('deptFormPanel').getForm().loadRecord(record[0]);
            Ext.getCmp('submitMode').setValue(Cooper4.SUBMIT_MODE_EDIT);
        }
        this.addOrEditWin.show();
    },
    /**
     * 提交FORM
     * @param form
     */
    addOrEditDept : function(form){

        var that = this;

        var submitUrl = "";

        if(Ext.getCmp('submitMode').getValue() == 'add') {

            submitUrl = "/organization/deptAdd.freda";
        }
        else{

            submitUrl = "/organization/deptEdit.freda";
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
                            Ext.data.StoreManager.lookup('deptStore').reload();
                            Ext.getCmp('deptTree').store.load({
                                node : Ext.getCmp('deptTree').getRootNode()
                            });
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
    deleteDept : function(grid){

        var record = grid.getSelectionModel().getSelection();
        if(Ext.isEmpty(record))
        {
            Cooper4.showAlert("请选择删除项.");
            return ;
        }
        for(var i=0;i<record.length;i++){
            if(record[0].get('deptLeaf') != 1){
                Cooper4.showAlert('系统只允许从末级菜单开始依次删除.');
                return ;
            }
        }

        Ext.Msg.show({
            title:"提示",
            message: "确定需要删除该选中项?",
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function(btn) {
                if (btn === 'yes') {
                    Cooper4.ux.Ajax.request({
                        url : '/organization/deptDelete.freda',
                        method : 'POST',
                        params : {
                            deptId : Cooper4.jsArray2JsString(record,'deptId'),
                            parentId : Cooper4.GLOBAL_PARAMS.parentId
                        },
                        success: function(response) {
                            var result = Ext.JSON.decode(response.responseText);
                            Cooper4.showAlert(result.msg);
                            Ext.data.StoreManager.lookup('deptStore').reload();
                            Ext.getCmp('deptTree').store.load({
                                node : Ext.getCmp('deptTree').getRootNode()
                            });
                        }
                    });
                }
            }
        });
    }
});
