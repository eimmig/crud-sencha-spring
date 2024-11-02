Ext.define('EmployeeApp.view.mixins.WindowMixin', {

    closeWindow: function() {
        let me = this;
        me.getView().close();
    },
    getWindowView() {
        let me = this;
        return me.getView();
    },
    getWindowViewModel() {
        let me = this;
        return me.getWindowView().getViewModel();
    },
    showServerCommunicationFailure: function() {
        Ext.Msg.show({
            title: 'Failure',
            msg: 'Server Communication failed!',
            buttons: Ext.Msg.OK,
            icon: Ext.Msg.ERROR
        });
    },
    onFormCancelButtonClick: function(btn, event, eOpts) {
        let me = this;
        me.closeWindow();
    }
});