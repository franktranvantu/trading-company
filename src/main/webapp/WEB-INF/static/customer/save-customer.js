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
  });
});