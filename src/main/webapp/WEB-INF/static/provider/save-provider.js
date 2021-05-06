$(function() {
  $('#back').click(() => {
    window.location.href = '/provider';
  });

  $('form#save-provider').submit(() => {
    const nameInput = $('input#name');
    if (_.isEmpty(nameInput.val())) {
      nameInput.addClass('is-invalid');
      return false;
    }
    nameInput.removeClass('is-invalid');
  });
});