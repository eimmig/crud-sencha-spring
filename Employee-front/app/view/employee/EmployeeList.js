Ext.define('EmployeeApp.view.employee.EmployeeList', {
    extend: 'Ext.grid.Panel',
    xtype: 'employeeslist',
    reference: 'employeesListRef',

    requires: [
        'EmployeeApp.store.Employee'
    ],

    title: 'Funcionários',

    dockedItems: {
        itemId: 'toolbar',
        xtype: 'toolbar',
        items: [
            {
                text: 'Operations',
                menu: [
                    {
                        text: 'Adicionar Funcionário',
                        handler: function() {
                            Ext.create('EmployeeApp.view.employee.AddEmployee').show();
                        }
                    },
                    {
                        text: 'Atualizar Funcionário',
                        handler: function() {
                            let grid = this.up('employeeslist');
                            let record = grid.getSelectionModel().getSelection()[0];
        
                            if (record) {
                                let mainController = Ext.getCmp('mainView').getController();
                                mainController.onUpdateEmployee(record);
                            } else {
                                Ext.Msg.alert('Erro', 'Por favor, selecione um funcionário para atualizar.');
                            }
                        }
                    },
                    {
                        text: 'Excluir Funcionário',
                        handler: function() {
                            let grid = this.up('employeeslist');
                            let record = grid.getSelectionModel().getSelection()[0];
        
                            if (record) {
                                let mainController = Ext.getCmp('mainView').getController();
                                mainController.onDeleteEmployee(record);
                            } else {
                                Ext.Msg.alert('Erro', 'Por favor, selecione um funcionário para excluir.');
                            }
                        }
                    }
                ]
            }
        ]
    },

    store: {
        type: 'employee'
    },

    columns: [
        { text: 'ID', dataIndex: 'id', hidden: true },
        { text: 'Nome', dataIndex: 'name', flex: 1 },
        { text: 'Sobrenome', dataIndex: 'surname', flex: 1 },
        { text: 'E-mail', dataIndex: 'email', flex: 1.5 },
        { text: 'Número do NIS', dataIndex: 'nis', flex: 1 }
    ]
});
