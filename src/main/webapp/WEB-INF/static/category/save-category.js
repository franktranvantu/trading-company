$(function() {
  $('#back').click(() => {
    window.location.href = '/category';
  });

  $('form#save-category').submit(() => {
    const nameInput = $('input#name');
    if (_.isEmpty(nameInput.val())) {
      nameInput.addClass('is-invalid');
      return false;
    }
    nameInput.removeClass('is-invalid');
  });
});