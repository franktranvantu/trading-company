$(function() {
  $('#back').click(() => {
    window.location.href = '/trading-company/product';
  });

  $('form#save-product').submit(() => {
    const nameInput = $('input#name');
    if (_.isEmpty(nameInput.val())) {
      nameInput.addClass('is-invalid');
      return false;
    }
    nameInput.removeClass('is-invalid');

    const modelInput = $('input#model');
    if (_.isEmpty(modelInput.val())) {
      modelInput.addClass('is-invalid');
      return false;
    }
    modelInput.removeClass('is-invalid');

    const brandInput = $('input#brand');
    if (_.isEmpty(brandInput.val())) {
      brandInput.addClass('is-invalid');
      return false;
    }
    brandInput.removeClass('is-invalid');

    const descriptionInput = $('input#description');
    if (_.isEmpty(descriptionInput.val())) {
      descriptionInput.addClass('is-invalid');
      return false;
    }
    descriptionInput.removeClass('is-invalid');

    const buyInput = $('input#buy');
    if (_.isEmpty(buyInput.val())) {
      buyInput.addClass('is-invalid');
      return false;
    }
    buyInput.removeClass('is-invalid');

    const saleInput = $('input#sale');
    if (_.isEmpty(saleInput.val())) {
      saleInput.addClass('is-invalid');
      return false;
    }
    saleInput.removeClass('is-invalid');
  });

});