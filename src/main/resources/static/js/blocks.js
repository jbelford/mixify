// @ts-check

// Attach a middleware to AJAX requests to set the csrf token
$(function () {
    var token = $("meta[name='_csrf']").attr('content');
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader('X-CSRF-TOKEN', token);
    });
});
