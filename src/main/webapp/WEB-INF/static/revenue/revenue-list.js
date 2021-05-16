$(function() {
  $('#date').daterangepicker({
    opens: 'center',
    autoUpdateInput: false,
    locale: {
      format: 'DD/MM/YYYY',
      cancelLabel: 'Clear'
    }
  });

  $('#date').on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
  });

  $('#date').on('cancel.daterangepicker', function(ev, picker) {
    $('#date').val('');
  });

  $('#revenue').DataTable({
    scrollY: 450,
    scroller: true,
    searching: false
  });
});