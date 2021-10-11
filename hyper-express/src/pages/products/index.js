import React, { useState, useCallback, useEffect } from 'react';
import { useAuth } from 'hooks/auth';
import { useProduct } from 'hooks/useProducts';
import { useParams } from 'react-router-dom';
import { api } from 'api/api';
import { UseFetch } from 'hooks/useFetch';
import { useNavigate, Link } from 'react-router-dom';

import RowProduct from 'components/RowProducts';
import Header from 'components/Header';
import Cards from 'components/RowProducts/Cards';
import Title from 'components/Title';
import Filter from 'components/Filter';
import imgEmpty from 'assets/svgs/empty.svg';
import Footer from 'components/Footer';

import { Container, Content, Left, Right, Empty } from './style';

export default function Products() {
  const { id } = useParams();
  const { product, reset } = useProduct();

  const [title, setTitle] = useState();

  const { data } = UseFetch(
    `/filtros/pesquisa-filtros?categoriaParametro=${id}&generoParametro=${product.gender}&subCategoriaParametro=${product.subCategory}&trocavelParametro=${product.replacement}`,
  );

  useEffect(() => {
    reset();

    switch (Number(id)) {
      case 1:
        setTitle('Blusas');
        break;

      case 2:
        setTitle('Camisas');
        break;

      case 3:
        setTitle('Bermudas');
        break;

      case 4:
        setTitle('Calças');
        break;

      case 5:
        setTitle('Calçados');
        break;

      default:
        break;
    }
  }, [reset, id]);

  return (
    
    <>
      <Header />
      <Container className="container">
        <RowProduct
          className="rowProduct"
          title="Novos Produtos"
          url={`produtos`}
        />          

        <Title text={title} />

        <Content className="flex">
          <Left>
            <Filter id={id} />
          </Left>

          <Right className="flex">
            {data ? (
              data.map((product) => (
                <Link
                  key={product.idProduto}
                  to={`/products/product/${product.idProduto}`}
                >
                  <Cards
                    title={product.nomeProduto}
                    price={product.precoProduto}
                    replacement={product.trocavel}
                    src={
                      `http://54.144.215.240/produtos/imagem/${product.idProduto}/1`
                    }
                  />
                </Link>
              ))
            ) : (
              <>
                {data?.length === 0 ? (
                  <Empty className="flexColumn">
                    <img src={imgEmpty} alt="vazio" />
                    <p>Nenhum foi produto encontrado</p>
                  </Empty>
                ) : (
                  <>
                    <Cards title="" loading />
                    <Cards title="" loading />
                    <Cards title="" loading />
                    <Cards title="" loading />
                    <Cards title="" loading />
                    <Cards title="" loading />
                    <Cards title="" loading />
                    <Cards title="" loading />
                  </>
                )}
              </>
            )}
          </Right>
        </Content>
      </Container>

      <Footer />
    </>
  );
}
