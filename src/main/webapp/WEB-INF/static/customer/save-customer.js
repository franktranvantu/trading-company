$(function() {
  $('#back').click(() => {
    window.location.href = '/customer';
  });

  $('form#save-customer').submit(() => {
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

    const phoneInput = $('input#phone');
    if (_.isEmpty(phoneInput.val())) {
      phoneInput.addClass('is-invalid');
      return false;
    }
    phoneInput.removeClass('is-invalid');
  });
});