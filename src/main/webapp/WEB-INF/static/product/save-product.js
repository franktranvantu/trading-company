$(function() {
  $('#back').click(() => {
    window.location.href = '/product';
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

    const buyInput = $('input#buy');
    if (_.isEmpty(buyInput.val())) {
      buyInput.addClass('is-invalid');
      $('.buy-mandatory').show();
      $('.buy-exceed').hide();
      return false;
    } else if (buyInput.val() < 1 || buyInput.val() > 5000) {
      $('.buy-mandatory').hide();
      $('.buy-exceed').show();
      return false;
    }
    buyInput.removeClass('is-invalid');

    const saleInput = $('input#sale');
    if (_.isEmpty(saleInput.val())) {
      saleInput.addClass('is-invalid');
      $('.sale-mandatory').show();
      $('.sale-exceed').hide();
      return false;
    } else if (saleInput.val() < buyInput.val() || saleInput.val() > (buyInput.val() * 2)) {
      $('.sale-mandatory').hide();
      $('.sale-exceed').show()
      return false;
    }
    saleInput.removeClass('is-invalid');
  });

});