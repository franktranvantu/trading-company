$(function() {
  $('#back').click(() => {
    window.location.href = '/order';
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
      $('.mandatory').show();
      $('.exceed').hide();
      return false;
    } else if (quantityInput.val() < 1 || quantityInput.val() > 1000) {
      quantityInput.addClass('is-invalid');
      $('.mandatory').hide();
      $('.exceed').show();
      return false;
    }
    quantityInput.removeClass('is-invalid');
  });

  $('#type').trigger('change');
});