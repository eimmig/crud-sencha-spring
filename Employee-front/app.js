/*
 * This file launches the application by asking Ext JS to create
 * and launch() the Application class.
 */
Ext.application({
    extend: 'EmployeeApp.Application',

    name: 'EmployeeApp',

    requires: [
        // This will automatically load all classes in the EmployeeApp namespace
        // so that application classes do not need to require each other.
        'EmployeeApp.*'
    ],

    // The name of the initial view to create.
    mainView: 'EmployeeApp.view.main.Main'
});
