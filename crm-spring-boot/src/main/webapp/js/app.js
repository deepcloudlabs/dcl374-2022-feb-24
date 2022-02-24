let crm = new CrmViewModel();
$(document).ready(() => {
    i18n.init(AppConfig.I18N_CONFIG,function(t){
        $(document).i18n();
        knockoutLocalize('en');
        ko.validation.init({
            decorateInputElement: true
        }, true);
        ko.applyBindings(crm);
    })
});