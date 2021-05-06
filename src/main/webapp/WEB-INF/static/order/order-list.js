$(function() {
  const start = moment().subtract(30, 'days');
  const end = moment();

  function cb(start, end) {
    $('#date').html(start.format('dd/mm/yyyy') + ' - ' + end.format('dd/mm/yyyy'));
  }

  $('#date').daterangepicker({
    opens: 'center',
    startDate: start,
    endDate: end,
  }, cb);

  cb(start, end);

  $('#order').DataTable({
    scrollY: 450,
    scroller: true
  });

  $('.table tbody').click(e => {
    const target = $(e.target);
    if (target.hasClass('delete-order-button') || target.hasClass('fa-trash')) {
      e.preventDefault();
      const id = target.closest('a').data('id');
      const modal = $('#delete-order-modal');
      modal.data('id', id);
      modal.modal('show');
    }
  });

  $('#confirm-delete-order').click(() => {
    const modal = $('#delete-order-modal');
    const id = modal.data('id');
    modal.modal('hide');
    const $form = $('<form action="/order/delete-order" method="POST"></form>');
    $form.append(`<input type="hidden" name="id" value="${id}">`);
    $(document.body).append($form);
    $($form).submit();
  });
});