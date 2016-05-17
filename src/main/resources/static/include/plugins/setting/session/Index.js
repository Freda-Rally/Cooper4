/**
 * Session监控.
 *
 * Created by rally on 16/5/15.
 */
Ext.define('Cooper4.plugins.setting.session.Index',{

    requires : ['Cooper4.ux.Ajax',
                'Cooper4.plugins.setting.session.SessionGridPanel'],

    constructor : function(){

        var that = this;

        Ext.create('Ext.container.Viewport',{
            layout : 'border',
            items : [Ext.create('Cooper4.plugins.setting.session.SessionGridPanel',{

                listeners : [{
                    'eventKillBtnClick' : function(grid){

                        that.killSession(grid);
                    }
                }]
            })]
        });

        Ext.data.StoreManager.lookup('sessionStore').load();

    },
    /**
     * 杀死会话.
     *
     * @param grid
     */
    killSession : function(grid){

        var record = grid.getSelectionModel().getSelection();
        if(Ext.isEmpty(record)){

            Cooper4.showAlert("请选择杀死的会话.");
            return ;
        }

        Ext.Msg.show({
            title:"提示",
            message: "确定需要杀死该选中项?",
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function(btn) {
                if (btn === 'yes') {
                    Cooper4.ux.Ajax.request({
                        url : '/session/kill.freda',
                        method : 'POST',
                        params : {
                            ids : Cooper4.jsArray2JsString(record,'sessionId')
                        },
                        success: function(response) {
                            var result = Ext.JSON.decode(response.responseText);
                            Cooper4.showAlert(result.msg);
                            Ext.data.StoreManager.lookup('roleStore').reload();
                        }
                    });
                }
            }
        });
    }
});
