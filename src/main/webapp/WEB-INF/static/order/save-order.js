$(function() {
  $('#back').click(() => {
    window.location.href = '/trading-company/order';
  });

  $('form#save-order').submit(() => {
    const quantityInput = $('input#quantity');
    if (_.isEmpty(quantityInput.val())) {
      quantityInput.addClass('is-invalid');
      return false;
    }
    quantityInput.removeClass('is-invalid');
  });
});