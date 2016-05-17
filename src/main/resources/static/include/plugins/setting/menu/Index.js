/**
 *
 * 菜单管理
 *
 * Created by rally on 16/5/11.
 */
Ext.define('Cooper4.plugins.setting.menu.Index',{

    requires : ['Cooper4.plugins.setting.menu.MenuTreePanel',
                'Cooper4.plugins.setting.menu.MenuGridPanel',
                'Cooper4.plugins.setting.menu.AddOrEditWin',
                'Cooper4.ux.Ajax'],


    constructor : function(){

        var that = this;

        Ext.create('Ext.container.Viewport',{
            layout : 'border',
            items : [Ext.create('Cooper4.plugins.setting.menu.MenuGridPanel',{

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

                        that.deleteMenu(grid);
                    }
                }]
            }),Ext.create('Cooper4.plugins.setting.menu.MenuTreePanel',{

                listeners : [{

                    'eventTreeItemClick' : function(panel,id,superName){

                        Cooper4.GLOBAL_PARAMS.parentId = id;

                        Cooper4.GLOBAL_PARAMS.parentName = superName;

                        Ext.data.StoreManager.lookup('menuStore').load();

                    }
                }]
            })]
        });

        that.addOrEditWin = Ext.create('Cooper4.plugins.setting.menu.AddOrEditWin',{

            listeners : {

                'eventSave' : function(win,form){

                    that.addOrEditMenu(form);
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

            var record = Ext.getCmp('menuTree').getSelection()[0];
            if(Ext.isEmpty(record))
            {
                Cooper4.showAlert('请选择往哪个菜单下添加.');
                return ;
            }

            Ext.getCmp('menuFormPanel').getForm().reset();
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
            if(record[0].get('editMode') == 0){

                Cooper4.showAlert("只读项目无法修改..");
                return ;
            }
            Ext.getCmp('menuFormPanel').getForm().loadRecord(record[0]);
            Ext.getCmp('submitMode').setValue(Cooper4.SUBMIT_MODE_EDIT);
        }
        this.addOrEditWin.show();
    },
    /**
     * 提交FORM
     * @param form
     */
    addOrEditMenu : function(form){

        var that = this;

        var submitUrl = "";

        if(Ext.getCmp('submitMode').getValue() == 'add') {

            submitUrl = "/menu/add.freda";
        }
        else{

            submitUrl = "/menu/edit.freda";
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
                            Ext.data.StoreManager.lookup('menuStore').reload();
                            Ext.getCmp('menuTree').store.load({
                                node : Ext.getCmp('menuTree').getRootNode()
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
    deleteMenu : function(grid){

        var record = grid.getSelectionModel().getSelection();
        if(Ext.isEmpty(record))
        {
            Cooper4.showAlert("请选择删除项.");
            return ;
        }
        for(var i=0;i<record.length;i++){
            if(record[i].get('editMode') == 0){
                Cooper4.showAlert("选择项中含有只读项目.");
                return ;
            }
            if(record[0].get('menuLeaf') != 1){
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
                        url : '/menu/delete.freda',
                        method : 'POST',
                        params : {
                            menuId : Cooper4.jsArray2JsString(record,'menuId'),
                            parentId : Cooper4.GLOBAL_PARAMS.parentId
                        },
                        success: function(response) {
                            var result = Ext.JSON.decode(response.responseText);
                            Cooper4.showAlert(result.msg);
                            Ext.data.StoreManager.lookup('menuStore').reload();
                            Ext.getCmp('menuTree').store.load({
                                node : Ext.getCmp('menuTree').getRootNode()
                            });
                        }
                    });
                }
            }
        });
    }
});
