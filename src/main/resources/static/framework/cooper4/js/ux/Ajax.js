/**
 * Cooper4 Ajax
 *
 * Created by rally on 16/4/27.
 */

Ext.define('Cooper4.ux.Ajax',{
    extend: 'Ext.data.Connection',
    method : 'POST',
    maskText : '',
    autoAbort : false,
    singleton: true,

    listeners : [{
        'beforerequest' : function(conn, options, eOpts){
            Ext.getBody().mask(this.maskText == '' ? '数据加载中..请稍后..' : this.maskText);
        }
    },{
        'requestcomplete' : function(conn, response, options, eOpts){
            Ext.getBody().unmask();
        }
    },{
        'requestexception' : function( conn, response, options, eOpts ){
            Ext.getBody().unmask();
            Cooper4.showAlert('系统内部出错,请重新提交或联系管理员!');
        }
    }]
});
