Ext.define('EmployeeApp.model.Employee', {
    extend: 'Ext.data.Model',
    alias: 'model.employee',

    fields: [
        { 
            name: 'id', 
            type: 'string', 
            defaultValue: null 
        },
        { 
            name: 'name', 
            type: 'string', 
            validators: {
                type: 'length',
                min: 2,
                max: 30
            }
        },
        { 
            name: 'surname', 
            type: 'string', 
            validators: {
                type: 'length',
                min: 2,
                max: 50
            }
        },
        { 
            name: 'email', 
            type: 'string', 
            validators: {
                type: 'email'
            }
        },
        { 
            name: 'nis', 
            type: 'string',
            validators: {
                type: 'format',
                matcher: /^\d+$/
            }
        }
    ],

    idProperty: 'id'
});
