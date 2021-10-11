import { mask as masker, unMask } from 'remask';

export const masks = {
  cpf: '999.999.999-99',
  cep: '99999-999',
  codigo: '999999',
  telefone: '(99) 9 9999-9999',
};

export const unMasks = (value) => {
  return unMask(value);
};

export const mask = (setValue, name, evt, masked) => {
  if (masked) {
    return setValue(name, masker(evt.target.value, [masked]));
  }
  return setValue(name, masker(evt.target.value, ['99,999', '999,99']));
};

export const priceConvert = (price) => {
  return price?.toLocaleString('pt-br', {
    style: 'currency',
    currency: 'BRL',
  });
};
