/**
 *
 * Cooper4 Kind Editor
 *
 * 次版本中的KindEditor可能无法使用..有待调试..
 *
 * Created by rally on 16/4/27.
 */
Ext.define('Cooper4.ux.KindEditor',{
    extend: 'Ext.form.field.TextArea',
    alias: ['widget.kindeditor'],//xtype名称
    initComponent: function () {
        this.html = "<textarea id='" + this.getId() + "-input' name='" + this.name + "'></textarea>";
        this.callParent(arguments);
        this.on("boxready", function (t) {
            this.inputEL = Ext.get(this.getId() + "-input");
            this.editor = KindEditor.create('textarea[name="' + this.name + '"]', {
                height: t.getHeight(),//有底边高度，需要减去
                width: t.getWidth() - t.labelWidth,//宽度需要减去label的宽度
                themeType : 'simple',
                basePath: '/framework/cooper4/kindeditor/',
                uploadJson: '/framework/cooper4/kindeditor/jsp/upload_json.jsp',
                fileManagerJson: '/framework/cooper4/kindeditor/jsp/file_manager_json.jsp',
                resizeType: 0,
                wellFormatMode: true,
                newlineTag: 'br',
                allowFileManager: true,
                allowPreviewEmoticons: true,
                allowImageUpload: true
            });
        });
        this.on("resize", function (t, w, h) {
            this.editor.resize(w - t.labelWidth, h);
        });
    },
    setValue: function (value) {
        if (this.editor) {
            this.editor.html(value);
        }
    },
    reset: function () {
        if (this.editor) {
            this.editor.html('');
        }
    },
    setRawValue: function (value) {
        if (this.editor) {
            this.editor.text(value);
        }
    },
    getValue: function () {
        if (this.editor) {
            return this.editor.html();
        } else {
            return ''
        }
    },
    getRawValue: function () {
        if (this.editor) {
            return this.editor.text();
        } else {
            return '';
        }
    }
});
