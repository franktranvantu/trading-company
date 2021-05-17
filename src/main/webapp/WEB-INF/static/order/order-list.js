$(function() {
  $('#date').daterangepicker({
    opens: 'center',
    autoUpdateInput: false,
    timePicker: true,
    locale: {
      format: 'DD/MM/YYYY hh:mm:ss A',
      cancelLabel: 'Clear'
    }
  });

  $('#date').on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('DD/MM/YYYY hh:mm:ss A') + ' - ' + picker.endDate.format('DD/MM/YYYY hh:mm:ss A'));
  });

  $('#date').on('cancel.daterangepicker', function(ev, picker) {
    $('#date').val('');
  });

  $('#order').DataTable({
    scrollY: 450,
    scroller: true,
    searching: false
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