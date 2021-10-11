import React, { useState, useCallback, useEffect } from 'react';
import { useAuth } from 'hooks/auth';
import { useProduct } from 'hooks/useProducts';
import { priceConvert } from 'utils/masks';
import { api } from 'api/api';
import { UseFetch } from 'hooks/useFetch';
import { LoadingOutlined } from '@ant-design/icons';
import { useParams, useNavigate, Link } from 'react-router-dom';

import Circle from './Circle';
import Button from 'components/Button';
import UploadFile from './uploadFIle';
import DowloadFile from './dowloadFile';
import Title from 'components/Title';
import ItemCart from 'components/ItemCart';

import Header from 'components/Header';
import Footer from 'components/Footer';
import ImgWallet from 'assets/svgs/carteira.svg';

import { Container, Loading, ButtonsFlex } from './style';
import { Empty } from 'pages/cart/style';

export default function Seller() {
  const { id } = useParams();
  const { product, reset } = useProduct();
  const { user } = useAuth();

  const [tell, setTell] = useState();

  const urlImg = 'http://54.144.215.240/';

  const active = UseFetch(`/vendas/ativos/${id}`);
  const inactive = UseFetch(`/vendas/inativos/${id}`);

  console.log(inactive);

  const progress = UseFetch(`/vendas/em-andamento/${id}`);
  const finished = UseFetch(`/vendas/concluidos/${id}`);

  useEffect(() => {
    if (active.data) {
      setTell(active.data[0].telefone);
    }
  }, [active.data]);

  return (
    <>
      <Header />
      <Container className="container ">
        {active.data?.length !== 0 && active.data && (
          <div className="centralizar">
            <div className="flex">
              <Circle title="Produtos" number={active.data?.length} />
              <Circle title="Pedidos" number={progress.data?.length} />
              <Circle title="Finalizados" number={finished.data?.length} />
            </div>
          </div>
        )}

        <div>
          <h3>Ativos</h3>
          {active.data ? (
            active.data.map((product) => (
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
                onInactive={`/produtos/inativo/${product.idProduto}?email=${user.email}`}
              />
            ))
          ) : (
            <>
              {active.data?.length === 0 ? (
                <Empty className="flexColumn">
                  <div className="flexColumn">
                    <img src={ImgWallet} alt="carteira" />
                    <h2>Você ainda não anunciou nenhum produto.</h2>
                    <span>
                      Anuncie produtos para aumentar a sua renda mensal.
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
        </div>

        <div>
          <h3>Inativos</h3>

          {inactive.data ? (
            inactive.data.map((product) => (
              <ItemCart
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
              {inactive.data?.length === 0 ? (
                <></>
              ) : (
                <Loading>
                  <LoadingOutlined />
                </Loading>
              )}
            </>
          )}
        </div>

        <ButtonsFlex>
          <DowloadFile />
          <UploadFile
            url={`http://54.144.215.240/processos?telefone=${tell}`}
          />
        </ButtonsFlex>
      </Container>
      <Footer />
    </>
  );
}
