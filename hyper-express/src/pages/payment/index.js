import React, { useEffect, useState, useCallback } from 'react';
import { useAuth } from 'hooks/auth';
import { useProduct } from 'hooks/useProducts';
import { priceConvert } from 'utils/masks';
import { useNavigate } from 'react-router-dom';
import { UseFetch } from 'hooks/useFetch';
import { api } from 'api/api';
import { notificationError, notificationSuccess } from 'utils/notifications';

import Header from 'components/Header';
import Title from 'components/Title';
import ResumeDemand from 'components/ResumeDemand';
import Footer from 'components/Footer';
import { Conteiner, Address } from './style';

export default function Payment() {
  const { user } = useAuth();
  const navigate = useNavigate();
  const { product } = useProduct();

  const [loading, setLoading] = useState(false);

  const [localization, setLocalization] = useState([]);

  const requestProduct = UseFetch(
    `/pedidos/pedido/info/${product.codigoPedido}`,
  );

  const address = UseFetch(`/usuarios/endereco/${user.id}`);
  const { data } = UseFetch(
    `/pedidos/pagar/${product.codigoPedido}?cep=${localization.cep}`,
  );

  const handleBuy = async () => {
    try {
      const { data } = await api.post(
        '/mercado-pago/pagar-pedido',
        requestProduct.data,
      );

      window.open(data.urlMP, '_blank');
      navigate('/follow-up');
    } catch (error) {
      setLoading(false);
      console.log(error);

      return notificationError(
        'Ops, ocorreu um erro',
        'Não foi possivel completar sua compra',
      );
    }
  };

  useEffect(() => {
    if (address.data) {
      setLocalization({
        estado: address.data[0]?.estadoUf,
        cidade: address.data[0]?.cidade,
        bairro: address.data[0]?.bairro,
        logradouro: address.data[0]?.logradouro,
        numero: address.data[0]?.numero,
        cep: address.data[0]?.cep,
      });
    }
  }, [address.data]);

  return (
    <>
      <Header />
      <Conteiner className="container flex">
        <Address>
          <Title text="Endereço de Entrega" font="0.85" />
          <p>{user.name}</p>
          <p>
            {localization.logradouro}, {localization.numero}
          </p>
          <p>
            {localization.bairro} - {localization.cidade}
          </p>
          <p>CEP - {localization.cep}</p>
        </Address>

        <ResumeDemand
          id="resume"
          valorProduto={priceConvert(product.valorTotal)}
          valorFrete={priceConvert(data?.valorFrete)}
          total={priceConvert(data?.valorTotalPedido)}
          onClick={handleBuy}
          loadingButton={loading}
        />
      </Conteiner>
      <Footer />
    </>
  );
}
