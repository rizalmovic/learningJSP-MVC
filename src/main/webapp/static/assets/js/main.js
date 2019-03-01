let App = {};

$(function(){

    App = {
        setupListener: function() {
            // Add modal save button listener
            $('.modal').find('.btn-modal-save').on("click", function(){
                // disabled button to prevent double multiple submission
                // $(this).prop("disabled", true);
                $(this).closest('.modal').find('form').submit();
            });

            $('.modal').on("hidden.bs.modal", function(){
                $(this).find('.btn-modal-save').prop("disabled", false);
                $(this).find('form').trigger('reset');
            });
        },
        init: function(){
            this.setupListener();
        }
    };


    App.init();

});