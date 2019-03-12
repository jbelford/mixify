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

/**
 * @param {string} id 
 */
function onSelectPlaylist(id) {
    window.location = `/playlists/${id}`;
}