$(function() {
  $('#back').click(() => {
    window.location.href = '/trading-company/order';
  });

  $('#type').change(e => {
    if ($(e.target).val() === 'BUY') {
      $('#provider-row').show();
      $('#customer-row').hide();
    } else {
      $('#provider-row').hide();
      $('#customer-row').show();
    }
  });

  $('form#save-order').submit(() => {
    const quantityInput = $('input#quantity');
    if (_.isEmpty(quantityInput.val())) {
      quantityInput.addClass('is-invalid');
      return false;
    }
    quantityInput.removeClass('is-invalid');
  });

  $('#type').trigger('change');
});