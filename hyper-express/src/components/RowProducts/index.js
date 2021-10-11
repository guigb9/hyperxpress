import React, { useState } from 'react';
import Cards from './Cards';
import { Link } from 'react-router-dom';
import { UseFetch } from 'hooks/useFetch';

import Title from 'components/Title';
import { LeftOutlined, RightOutlined } from '@ant-design/icons';
import { Container, Icon } from './style';

export default function RowProducts({ title, url = '/produtos', ...rest }) {
  const [scrollX, setScrollX] = useState(0);
  const { data } = UseFetch(url);

  const handleLefth = () => {
    let x = scrollX + Math.round(window.innerWidth);

    if (x > 0) {
      x = 0;
    }
    setScrollX(x);
  };

  const handleRight = () => {
    let x = scrollX - Math.round(window.innerWidth);
    let listW = (data?.length + 3) * 370;

    if (window.innerWidth - listW > x) {
      x = window.innerWidth - listW;
    }

    setScrollX(x);
  };

  return (
    <Container {...rest}>
      <Title text={title} color="var(--darkMedium)" alignment="start" />

      <div className="flexColumn">
        <Icon onClick={handleLefth}>
          <LeftOutlined />
        </Icon>

        <Icon onClick={handleRight}>
          <RightOutlined />
        </Icon>

        <div className="flex" style={{ marginLeft: scrollX }}>
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
              <Cards title="" loading />
              <Cards title="" loading />
              <Cards title="" loading />
              <Cards title="" loading />
              <Cards title="" loading />
            </>
          )}
        </div>
      </div>
    </Container>
  );
}
