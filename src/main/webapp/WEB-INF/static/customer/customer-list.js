$(function() {
  $('#customer').DataTable({
    scrollY: 450,
    scroller: true
  });

  $('.table tbody').click(e => {
    const target = $(e.target);
    if (target.hasClass('delete-customer-button') || target.hasClass('fa-trash')) {
      e.preventDefault();
      const id = target.closest('a').data('id');
      const modal = $('#delete-customer-modal');
      modal.data('id', id);
      modal.modal('show');
    }
  });

  $('#confirm-delete-customer').click(() => {
    const modal = $('#delete-customer-modal');
    const id = modal.data('id');
    modal.modal('hide');
    const $form = $('<form action="/customer/delete-customer" method="POST"></form>');
    $form.append(`<input type="hidden" name="id" value="${id}">`);
    $(document.body).append($form);
    $($form).submit();
  });
});