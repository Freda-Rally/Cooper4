/**
 *
 * Combo Box With Tree
 *
 * Created by rally on 16/4/27.
 */
Ext.define('Cooper4.ux.ComboBoxTree', {
    extend : 'Ext.form.field.Picker',
    alias: ['widget.comboboxtree'],
    editable : false,
    requires : [
        Ext.tree.Panel,
        Ext.MessageBox
    ],
    action : 'all',
    store:{},
    tree: {},
    submitId : '',
    config: {
        maxPickerWidth: 200,
        maxPickerHeight: 200,
        minPickerHeight: 100
    },
    initComponent : function() {
        var self = this;
        Ext.apply(self, {
            fieldLabel : self.fieldLabel,
            labelWidth : self.labelWidth
        });
        self.callParent();
        this.tree= new Ext.tree.Panel({
            width: self.maxPickerWidth,
            height:self.maxPickerHeight,
            autoScroll : true,
            floating : true,
            focusOnToFront : false,
            shadow : false,
            useArrows : true,
            store : this.store,
            rootVisible : true,
            listeners:{ }
        });
        this.tree.on('itemclick', function (view, record) {
            if(self.action == 'all'){
                self.setRawValue(record.get('id'));// 隐藏值
                self.setValue(record.get('text'));// 显示值
            }else if(self.action == 'leaf'){
                if(record.get('leaf') == 1 || record.get('id') == 1){
                    self.setRawValue(record.get('id'));// 隐藏值
                    self.setValue(record.get('text'));// 显示值
                }else{
                    Ext.MessageBox.alert('温馨提示','请点选至叶子节点..');
                    return ;
                }
            }else if(self.action == 'only'){
                if(record.get('leaf') == 1){
                    self.setRawValue(record.get('id'));// 隐藏值
                    self.setValue(record.get('text'));// 显示值
                }else{
                    Ext.MessageBox.alert('温馨提示','请点选至叶子节点..');
                    return ;
                }
            }

            Ext.getCmp(self.submitId).setValue(record.get('id'));
            self.collapse();//self.picker.hide();
        });
        //this.tree.expandAll();
    },
    createPicker : function() {
        var self = this;
        self.picker = this.tree;
        self.picker.on({
            checkchange : function() {
                var records = self.picker.getView().getChecked(), names = [], values = [];
                Ext.Array.each(records, function(rec) {
                    names.push(rec.get('id'));//rec.get('text')
                    values.push(rec.get('id'));
                });
                self.setRawValue(values.join(';'));// 隐藏值
                self.setValue(names.join(';'));// 显示值
                self.picker.hide();                 //[目前单选,该批次代码、tree的itemclick事件去掉则多选]
                Ext.Array.each(records, function(record) {  //[目前单选,该批次代码、tree的itemclick事件去掉则多选]
                    record.set('checked', false);       //[目前单选,该批次代码、tree的itemclick事件去掉则多选]
                });                     //[目前单选,该批次代码、tree的itemclick事件去掉则多选]
            }
        });
        return self.picker;
    },
    alignPicker : function() {
        var me = this, picker, isAbove, aboveSfx = '-above';
        if (this.isExpanded) {
            picker = me.getPicker();
            if (me.matchFieldWidth) {
                picker.setWidth(me.bodyEl.getWidth());
            }
            if (picker.isFloating()) {
                picker.alignTo(me.inputEl,'', me.pickerOffset);// ->tl
                isAbove = picker.el.getY() < me.inputEl.getY();
                me.bodyEl[isAbove ? 'addCls' : 'removeCls'](me.openCls+ aboveSfx);
                picker.el[isAbove ? 'addCls' : 'removeCls'](picker.baseCls + aboveSfx);
            }
        }
    }
});
