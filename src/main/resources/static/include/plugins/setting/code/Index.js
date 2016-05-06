/**
 *
 * 数据字典管理
 *
 * Created by rally on 16/5/5.
 */
Ext.define('Cooper4.plugins.setting.code.Index',{

    requires : ['Cooper4.ux.Ajax',
                'Cooper4.plugins.setting.code.AddOrEditWin',
                'Cooper4.plugins.setting.code.CodeGridPanel'],

    constructor : function(){

        var that = this;

        Ext.create('Ext.container.Viewport',{
            layout : 'border',
            items : [Ext.create('Cooper4.plugins.setting.code.CodeGridPanel',{

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

                        that.deleteCode(grid);
                    }
                },{
                    'eventOnMoneySynBtnClick' : function(grid){

                        that.synMoneyCode();
                    }
                }]
            })]
        });

        Ext.data.StoreManager.lookup('codeStore').load();

        that.addOrEditWin = Ext.create('Cooper4.plugins.setting.code.AddOrEditWin',{

            listeners : {

                'eventSave' : function(win,form){

                    that.addOrEditCode(form);
                }
            }

        });

    },
    /**
     * 窗口打开初始化..
     */
    addOrEditWinInit : function(grid,mode){

        if(this.addOrEditWin){

            if(mode == 'add'){

                Ext.getCmp('codeFormPanel').getForm().reset();
                Ext.getCmp('submitMode').setValue(Cooper4.SUBMIT_MODE_ADD);
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

                    Cooper4.showAlert("系统内置无法修改..");
                    return ;
                }
                Ext.getCmp('codeFormPanel').getForm().loadRecord(record[0]);
                Ext.getCmp('submitMode').setValue(Cooper4.SUBMIT_MODE_EDIT);
            }
            this.addOrEditWin.show();
        }
    },
    /**
     * 提交添加或修改操作..
     */
    addOrEditCode : function(form){

        var that = this;

        var submitUrl = "";

        alert(Ext.getCmp('submitMode').getValue());

        if(Ext.getCmp('submitMode').getValue() == 'add') {

            submitUrl = "/code/add.freda";
        }
        else{

            submitUrl = "/code/edit.freda";
        }

        alert(submitUrl);
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
                            Ext.data.StoreManager.lookup('codeStore').reload();
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
     * 提交删除操作..
     */
    deleteCode : function(grid){

        var record = grid.getSelectionModel().getSelection();
        if(Ext.isEmpty(record))
        {
            Cooper4.showAlert("请选择删除项.");
            return ;
        }
        for(var i=0;i<record.length;i++){
            if(record[i].get('editMode') == 0){
                Cooper4.showAlert("选择项中含有系统内置项目.");
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
                        url : '/code/delete.freda',
                        method : 'POST',
                        params : {
                            ids : Cooper4.jsArray2JsString(record,'codeId')
                        },
                        success: function(response) {
                            var result = Ext.JSON.decode(response.responseText);
                            Cooper4.showAlert(result.msg);
                            Ext.data.StoreManager.lookup('codeStore').reload();
                        }
                    });
                }
            }
        });
    },
    synMoneyCode : function(){

        Ext.Msg.show({
            title:"提示",
            message: "确定重新同步数据字典?",
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function(btn) {
                if (btn === 'yes') {
                    Cooper4.ux.Ajax.request({
                        url : '/code/synToCache.freda',
                        method : 'POST',
                        params : {},
                        success: function(response) {
                            var result = Ext.JSON.decode(response.responseText);
                            Cooper4.showAlert(result.msg);
                        }
                    });
                }
            }
        });
    }
});
