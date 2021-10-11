import { api } from 'api/api';
import React, { useState } from 'react';
import ImgLupa from './assets/lupa.svg';
import { Link } from 'react-router-dom';
import { Container, Input, Pesquisa } from './style';

export default function InputSearch({ ...props }) {
  const [pesquisa, setPesquisa] = useState([]);
  const [value, setValue] = useState([]);

  const handleInput = (evt) => {
    const { value } = evt.target;

    setValue(value);
  };

  const handleSearch = async () => {
    const params = {};

    if (value) {
      params.nomeProdutoParametro = value;

      const { data } = await api.get(`/filtros/pesquisa-filtros`, {
        params,
      });

      setPesquisa(data);
    } else {
      setPesquisa('');
    }
  };

  return (
    <>
      <Container {...props} className="flex">
        <Input
          type="search"
          onBlur={handleInput}
          placeholder="Buscar por produtos, marcas e muito mais"
        />
        <img onClick={handleSearch} src={ImgLupa} alt="lupa" />

        <Pesquisa className="flexColumn">
          {pesquisa ? (
            <>
              {pesquisa.map((produto) => (
                <Link
                  onClick={() => setPesquisa('')}
                  key={produto.idProduto}
                  to={`/products/product/${produto.idProduto}`}
                >
                  {produto.nomeProduto}
                </Link>
              ))}
            </>
          ) : (
            <></>
          )}
        </Pesquisa>
      </Container>
    </>
  );
}
