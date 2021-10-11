import React, { useState, useCallback, useEffect } from 'react';
import { priceConvert } from 'utils/masks';
import { api } from 'api/api';
import { useAuth } from 'hooks/auth';
import { UseFetch } from 'hooks/useFetch';
import { useNavigate } from 'react-router-dom';
import { useProduct } from 'hooks/useProducts';
import { notificationError } from 'utils/notifications';

import Header from 'components/Header';
import Footer from 'components/Footer';
import Title from 'components/Title';
import ItemCart from 'components/ItemCart';
import ResumeDemand from 'components/ResumeDemand';
import { LoadingOutlined } from '@ant-design/icons';
import ImgCart from 'assets/svgs/carrinho.svg';

import { Container, ScrollItens, Empty, Loading } from './style';

export default function Cart() {
  const { user } = useAuth();
  const { setProduct } = useProduct();

  const { data } = UseFetch(
    `/carrinhos/carrinho/todos/${user.id}`,
  );
  const navigate = useNavigate();

  const urlImg = 'http://54.144.215.240/';

  const [priceProducts, setPriceProducts] = useState();
  const [loading, setLoading] = useState(false);

  const handleRequest = useCallback(async () => {
    setLoading(true);
    try {
      const { data } = await api.post(
        `/pedidos/${user.id}`,
      );

      setProduct((state) => {
        return {
          ...state,
          codigoPedido: data.codigoPedido,
          valorTotal: data.valorTotal,
        };
      });

      setLoading(false);
      navigate('/payment');
    } catch (error) {
      console.log(error);
      setLoading(false);

      return notificationError(
        'Ocorreu um erro',
        'Aguarde uns instantes e tente novamente',
      );
    }
  }, [navigate, setProduct, user.id]);

  useEffect(() => {
    let element = 0;
    for (let i = 0; i < data?.length; i++) {
      element += data[i].precoProduto;
    }
    setPriceProducts(element);
  }, [data]);

  return (
    <>
      <Header />
      <Container className=" flex container">
        <ScrollItens className="flexColumn">
          <Title text="Produtos" font="0.85" />

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
                onRemove={`/carrinhos/carrinho/remover/${product.idProduto}/${user.id}`}
              />
            ))
          ) : (
            <>
              {data?.length === 0 ? (
                <Empty className="flexColumn">
                  <img src={ImgCart} alt="carrinho vazio" />

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
        </ScrollItens>

        {data?.length === 0 || (
          <ResumeDemand
            id="resume"
            valorProduto={priceConvert(priceProducts)}
            loadingButton={loading}
            onClick={handleRequest}
          />
        )}
      </Container>

      <Footer />
    </>
  );
}
