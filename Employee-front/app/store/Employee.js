Ext.define('EmployeeApp.store.Employee', {
    extend: 'Ext.data.Store',

    alias: 'store.employee',

    model: 'EmployeeApp.model.Employee',
    storeId: "employees",

    proxy: {
        type: "ajax",
        method: 'GET',
        url:  "http://localhost:8080/api/employees",
        pageParam: null,
        startParam: null,
        limitParam: null,
        noCache: false,
        reader: {
            type: "json",
            rootProperty: "rows",
        },
    },
    autoLoad: true,
});