$('.card').hover(
    function () {
        $(this).addClass('bg-success');
        $(this).removeClass('bg-dark');
    },
    function () {
        $(this).addClass('bg-dark');
        $(this).removeClass('border-success');
    }
);