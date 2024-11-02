Ext.define('EmployeeApp.view.employee.AddEmployee', {

    extend : 'Ext.window.Window',
    xtype: 'addemployeeform',
    controller: 'addEmployeeFormController',
    autoShow:true,
    frame:true,
    title: 'Novo Funcionário',
    width:800,
    modal : true,
    shadow:true,
    autoHeight:true,
    closable: true,
    items: {
        xtype: 'form',
        id:'addemployeeform',
        labelAlign: 'left',
        bodyStyle:'padding:5px',
        height:'auto',
        frame:true,
        items: [{
            layout:'column',
            border:false,
            labelWidth:200,
            items: [{
                layout:'column',
                border:false,
                labelWidth:200,
                items: [{
                    columnWidth: 1.0,
                    layout: 'anchor',
                    border: false,
                    items: [{
                        xtype: 'textfield',
                        emptyText: 'Nome',
                        fieldLabel: 'Nome',
                        name: 'name',
                        id: 'name',
                        hidden: false,
                        readOnly: false,
                        anchor: '98%',
                        allowBlank: false 
                    }, {
                        xtype: 'textfield',
                        emptyText: 'Sobrenome',
                        fieldLabel: 'Sobrenome',
                        name: 'surname',
                        id: 'surname',
                        hidden: false,
                        readOnly: false,
                        anchor: '98%',
                        allowBlank: false
                    }, {
                        xtype: 'textfield',
                        emptyText: 'E-mail',
                        fieldLabel: 'E-mail',
                        name: 'email',
                        vtype: 'email', 
                        hidden: false,
                        readOnly: false,
                        anchor: '98%',
                        allowBlank: false
                    }, {
                        xtype: 'textfield',
                        emptyText: 'Número do NIS',
                        fieldLabel: 'Número do NIS',
                        name: 'nis',
                        id: 'nis',
                        maskRe: /\d/, 
                        minLength: 11,
                        maxLength: 11,
                        hidden: false,
                        readOnly: false,
                        anchor: '98%',
                        allowBlank: false 
                    }, {
                        xtype: 'textfield',
                        emptyText: 'ID',
                        fieldLabel: 'ID',
                        name: 'id',
                        id: 'id',
                        hidden: true,
                        readOnly: true,
                        anchor: '98%'
                    }]
                }]
            }]

        }],

        buttons: [{
            text: 'Salvar',
            iconCls: 'x-icon-save',
            id:'saveadduserform',
            handler:'onFormSubmitClick'
        },{
            text: 'Fechar',
            iconCls: 'x-icon-close',
            handler: 'onFormCancelButtonClick'
        }]

    }

});