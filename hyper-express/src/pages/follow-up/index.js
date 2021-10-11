import React, { useEffect, useCallback, useState } from 'react';
import { useAuth } from 'hooks/auth';
import { useProduct } from 'hooks/useProducts';
import { priceConvert } from 'utils/masks';
import { useNavigate } from 'react-router-dom';
import { UseFetch } from 'hooks/useFetch';

import Footer from 'components/Footer';
import Header from 'components/Header';
import Title from 'components/Title';
import ItemCart from 'components/ItemCart';
import ResumeDemand from 'components/ResumeDemand';
import { LoadingOutlined } from '@ant-design/icons';
import { Container, ContainerInfoPedido, Order } from './style';
import { Empty, Loading } from 'pages/cart/style';

export default function Follow() {
  const { user } = useAuth();

  const { product } = useProduct();
  const urlImg = 'http://54.144.215.240/';

  const { data } = UseFetch(`/pedidos/pedido/${product.codigoPedido}`);
  const infOrder = UseFetch(`/pedidos/pedido/info/${product.codigoPedido}`);

  console.log(product.codigoPedido);

  const [total, setTotal] = useState();

  useEffect(() => {
    setTotal(product.valorTotal + infOrder.data?.valorFrete);
  }, [infOrder.data?.valorFrete, product.valorTotal]);

  return (
    <>
      <Header />
      <Container className="container flex">
        <Order>
          {data ? (
            data.map((product) => (
              <ItemCart
                active
                key={product.idProduto}
                name={
                  product.nomeProduto.length > 20
                    ? product.nomeProduto.substring(0, 20) + '...'
                    : product.nomeProduto
                }
                tamanho={product.tamanhoProduto}
                src={urlImg + `produtos/imagem/${product.idProduto}/1`}
                value={priceConvert(product.precoProduto)}
                redirect={`/products/product/${product.idProduto}`}
              />
            ))
          ) : (
            <>
              {data?.length === 0 ? (
                <Empty className="flexColumn">
                  <div className="flexColumn">
                    <h2>O seu carrinho está vazio</h2>
                    <span>
                      Não sabe o que comprar? Milhões de produtos esperam por
                      você!
                    </span>
                  </div>
                </Empty>
              ) : (
                <Loading>
                  <LoadingOutlined />
                </Loading>
              )}
            </>
          )}
        </Order>
        <ContainerInfoPedido>
          <Title text="Detalhes da Compra" font="0.7" />
          <div className="flex">
            <p>Código do Pedido:</p>
            <p> #000{infOrder.data?.codigoPedido}</p>
          </div>

          <div className="flex">
            <p>Preço:</p>
            <p>{priceConvert(product.valorTotal)}</p>
          </div>

          <div className="flex">
            <p>Frete:</p>
            <p>{priceConvert(infOrder.data?.valorFrete)}</p>
          </div>

          <div className="flex">
            <p>Pagamento: Mercado Pago</p>
            <p>{infOrder.data?.nomeFormaPagamento}</p>
          </div>

          <div className="flex">
            <p>Status:</p>
            <p>{infOrder.data?.status}</p>
          </div>

          <div className="flex">
            <strong>Total:</strong>
            <p>{priceConvert(total)}</p>
          </div>
        </ContainerInfoPedido>
      </Container>

      <Footer />
    </>
  );
}
