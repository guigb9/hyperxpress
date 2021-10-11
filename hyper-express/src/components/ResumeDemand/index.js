import React, { useCallback } from 'react';
import Title from '../Title';
import Button from '../Button';

import { Container } from './style';

export default function ResumeDemand({
  valorProduto,
  valorFrete,
  total,
  onClick,
  loadingButton,
  ...rest
}) {
  return (
    <Container {...rest} className="flexColumn">
      <Title text="Pedido" font="0.85" />

      <div className="flex">
        <p>Produtos</p>
        <span>{valorProduto}</span>
      </div>

      {total && (
        <>
          <div className="flex">
            <p>Frete</p>
            <span>{valorFrete}</span>
          </div>

          <div className="flex">
            <strong>Total</strong>
            <strong>{total}</strong>
          </div>
        </>
      )}

      <Button loading={loadingButton} onClick={onClick}>
        Comprar
      </Button>
    </Container>
  );
}
