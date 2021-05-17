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

  $('#revenue').DataTable({
    scrollY: 450,
    scroller: true,
    searching: false
  });
});