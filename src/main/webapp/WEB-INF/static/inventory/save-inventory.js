$(function() {
  $('#back').click(() => {
    window.location.href = '/inventory';
  });

  $('form#save-inventory').submit(() => {
    const nameInput = $('input#name');
    if (_.isEmpty(nameInput.val())) {
      nameInput.addClass('is-invalid');
      return false;
    }
    nameInput.removeClass('is-invalid');

    const addressInput = $('input#address');
    if (_.isEmpty(addressInput.val())) {
      addressInput.addClass('is-invalid');
      return false;
    }
    addressInput.removeClass('is-invalid');
  });
});