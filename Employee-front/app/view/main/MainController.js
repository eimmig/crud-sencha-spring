Ext.define('EmployeeApp.view.main.MainController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.main',

    onUpdateEmployee: function(record) {
        let editWindow = Ext.create('EmployeeApp.view.employee.AddEmployee', {
            title: 'Editar Funcionário'
        });

        let form = editWindow.down('form');
        form.loadRecord(record); 

        editWindow.show();
    },

    onDeleteEmployee: function(record) {
        let employeeId = record.get('id');
    
        if (!employeeId) {
            Ext.Msg.alert('Erro', 'Não foi possível obter o ID do funcionário.');
            return;
        }
    
        Ext.Msg.confirm('Confirmar Exclusão', 'Tem certeza de que deseja excluir este funcionário?', function(choice) {
            if (choice === 'yes') {
                Ext.Ajax.request({
                    url: `http://localhost:8080/api/employees/${employeeId}`,
                    method: 'DELETE',
                    success: function(response) {
                        Ext.Msg.alert('Sucesso', 'Funcionário excluído com sucesso.');
                        location.reload();
                    },
                    failure: function(response) {
                        Ext.Msg.alert('Erro', 'Não foi possível excluir o funcionário.');
                    },
                    scope: this
                });
            }
        }, this);
    },
    
});
