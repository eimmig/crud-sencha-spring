Ext.define('EmployeeApp.view.employee.AddEmployeeController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.addEmployeeFormController',

    init: function() {
        
    },

    onFormSubmitClick: function() {
        let me = this;
        let window = me.getView(); 
        let form = window.down('form'); 
    
        if (form.isValid()) { 
            let values = form.getValues();
    
            let url = "http://localhost:8080/api/employees";
            let method = 'POST';
    
            if (values.id && values.id !== "") {
                url += `/${values.id}`;
                method = 'PUT';          
            }
    
            Ext.Ajax.request({
                url: url,
                method: method,
                jsonData: values,
                timeout: 60000,
                waitMsg: 'Salvando dados...',
                success: function(response) {
                    Ext.Msg.alert('Sucesso', 'Funcionário adicionado/atualizado com sucesso!');
                    window.close(); 
                    location.reload();
                },
                failure: function(response) {
                    Ext.Msg.alert('Erro', 'Não foi possível adicionar/atualizar o funcionário. ' + response.responseText); 
                },
            });
        } else {
            Ext.Msg.alert('Erro', 'Por favor, preencha todos os campos obrigatórios.');
        }
    },

    onFormCancelButtonClick: function() {
        this.getView().close(); 
    }
});
