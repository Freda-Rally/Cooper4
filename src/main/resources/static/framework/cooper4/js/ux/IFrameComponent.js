/**
 *
 * Cooper4 IFrame Component
 *
 * Created by rally on 16/4/27.
 */
Ext.define('Cooper4.ux.IFrameComponent' ,{
    extend: 'Ext.Component',
    constructor : function(config){
        this.callParent(arguments);
    },
    alias : 'iframecomponent',
    loadMask : '玩命加载中...',
    renderTpl : '<iframe src="{url}" id="{id}-iframeEl" frameBorder="0" width="100%" height="100%" data-ref="iframeEl"></iframe>',
    childEls : ['iframeEl'],
    initComponent : function(){
        Ext.getBody().mask('数据加载中..请稍后..');
        this.callParent();
    },
    initEvents : function(){
        this.callParent();
    },
    onRender : function(ct, position){
        this.callParent();
        this.iframeEl.dom.src = this.url;
    }

});
