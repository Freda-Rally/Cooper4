/**
 *
 * Cooper4 Image
 *
 * Created by rally on 16/4/27.
 */
Ext.define('MyApp.ux.imageview.Image', {
    extend: 'Ext.data.Model',
    fields: [
        { name:'src', type:'string' },
        { name:'caption', type:'string' },
        { name : 'number',type:'string'}
    ]
});
