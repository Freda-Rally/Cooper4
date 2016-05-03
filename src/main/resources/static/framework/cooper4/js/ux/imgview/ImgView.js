/**
 *
 * Cooper4 Image View
 *
 * Created by rally on 16/4/27.
 */
Ext.define('MyApp.ux.imgview.ImgView',{
    extend : 'Ext.view.View',
    alias: ['widget.ImgView'],
    require : ['MyApp.ux.imgview.Image'],
    delUrl : '',
    collapsible: true,
    tpl :[
        '<tpl for=".">',
        '<div class="thumb-wrap" id="{name:stripTags}" style="float:left;margin:10px 10px 10px 10px">',
        '<div class="thumb"><img width=120 heigth=100 src="{src}" title="{name:htmlEncode}"></div>',
        '<span>{caption}</span>',
        '</div>',
        '</tpl>'
    ],
    store :{},
    trackOver: true,
    overItemCls: 'x-item-over',
    itemSelector: 'div.thumb-wrap',
    emptyText: '还未上传图片!',
    initComponent : function(){
        var self = this;
        //this.tpl = imageTpl;
        self.callParent();
    }
});
