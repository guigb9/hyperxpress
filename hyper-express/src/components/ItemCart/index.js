import React, { useCallback, useState } from 'react';
import { api } from 'api/api';
import { Link } from 'react-router-dom';

import { notificationError, notificationSuccess } from 'utils/notifications';

import {
  CloseOutlined,
  LoadingOutlined,
  CheckOutlined,
} from '@ant-design/icons';
import { Container, Content, Disabled } from './style';

export default function ItemCart({
  src,
  name,
  active,
  tamanho,
  value,
  redirect,
  onInactive,
  onRemove,
}) {
  const [loading, setLoading] = useState(false);

  const handleDelete = useCallback(async () => {
    setLoading(true);

    try {
      if (onRemove) {
        await api.delete(onRemove);
      } else {
        await api.put(onInactive);
      }

      setLoading(false);
      window.location.reload();

      return notificationSuccess(
        'Produto Removido',
        'Este produto não faz mais parte do seu carrinho.',
      );
    } catch (error) {
      console.log(error);

      setLoading(false);

      return notificationError(
        'Algo deu errado',
        'Não foi possivel remover este produto do carrinho.',
      );
    }
  }, [onInactive, onRemove]);

  const handleAdd = useCallback(async () => {
    console.log('oi');
  }, []);
  return (
    <>
      {active ? (
        <Container>
          <Content className="flex">
            <Link to={redirect} className="flex">
              <img src={src} alt="item-produto" />
              <p>{name}</p>
              <p>{tamanho}</p>
              <p>{value}</p>
            </Link>

            {onRemove && (
              <>
                {loading ? (
                  <LoadingOutlined />
                ) : (
                  <CloseOutlined onClick={handleDelete} />
                )}
              </>
            )}
          </Content>
        </Container>
      ) : (
        <Disabled>
          <Content className="flex">
            <Link to={redirect} className="flex">
              <img src={src} alt="item-produto" />
              <p>{name}</p>
              <p>{tamanho}</p>
              <p>{value}</p>
            </Link>

            {!onRemove ? (
              <>
                {loading ? (
                  <LoadingOutlined />
                ) : (
                  <CheckOutlined onClick={handleAdd} />
                )}
              </>
            ) : (
              ''
            )}
          </Content>
        </Disabled>
      )}
    </>
  );
}
