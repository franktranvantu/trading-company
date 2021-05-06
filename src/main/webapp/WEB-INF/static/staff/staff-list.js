$(function() {
  $('#staff').DataTable({
    scrollY: 450,
    scroller: true
  });

  $('.table tbody').click(e => {
    const target = $(e.target);
    if (target.hasClass('delete-staff-button') || target.hasClass('fa-trash')) {
      e.preventDefault();
      const id = target.closest('a').data('id');
      const modal = $('#delete-staff-modal');
      modal.data('id', id);
      modal.modal('show');
    }
  });

  $('#confirm-delete-staff').click(() => {
    const modal = $('#delete-staff-modal');
    const id = modal.data('id');
    modal.modal('hide');
    const $form = $('<form action="/staff/delete-staff" method="POST"></form>');
    $form.append(`<input type="hidden" name="id" value="${id}">`);
    $(document.body).append($form);
    $($form).submit();
  });
});