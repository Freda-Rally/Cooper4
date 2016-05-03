/**
 *
 * 定义一些总体的加载或总体的拦截等.
 *
 * Created by rally on 16/4/25.
 */

Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath('Cooper4.ux', '/framework/cooper4/js/ux');
Ext.Loader.setPath('Cooper4.plugins', '/include/plugins');

Ext.onReady(function(){

    Ext.tip.QuickTipManager.init();
});
/**
 * 定义部分全局变量
 */
var me = parent.window;

/**
 * ---------------
 * 全局Ext修正部分.
 * ---------------
 */
Ext.apply(Ext.tree.Panel,{
    listeners : {
        'checkchange': function(node, checked){

            listenerCheck(node, checked);

        }
    }
});

var listenerCheck = function(node, checked) {

    childHasChecked(node, checked);
    var parentNode = node.parentNode;
    if (parentNode != null && !Ext.isEmpty(parentNode.get('checked'))) {
        parentCheck(parentNode, checked);
    }
};
// 级联选中父节点
var parentCheck = function(node, checked) {

    var childNodes = node.childNodes;

    if(checked){
        node.set('checked', true);
    }else{
        var a = true;
        for (var i = 0; i < childNodes.length; i++){
            if (childNodes[i].get('checked')){
                node.set('checked', true);
                a = false;
                continue;
            }
        }
        if(a){node.set('checked', false);}
    }
    var parentNode = node.parentNode;
    if (parentNode != null) {
        parentCheck(parentNode, checked);
    }
}
// 级联选择子节点
var childHasChecked = function(node, checked) {

    node.cascadeBy(function(child) {
        child.set("checked", checked)
    });
}
