import React, { useState, useCallback, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { mask, masks } from 'utils/masks';
import { useForm } from 'react-hook-form';
import { UseFetch } from 'hooks/useFetch';
import { useNavigate } from 'react-router-dom';
import { useAuth } from 'hooks/auth';
import { api } from 'api/api';
import { notificationError, notificationSuccess } from 'utils/notifications';

import Header from 'components/Header';
import RowProduct from 'components/RowProducts';
import Title from 'components/Title';
import Cards from 'components/RowProducts/Cards';
import Footer from 'components/Footer';
import Button from 'components/Button';
import Rate from 'components/Rate';
import ButtonSecundary from 'components/ButtonSecundary';
import Input from 'components/Input';

import { Container, ContentLeft, ContentRight, Features } from './style';

export default function BuyProduct() {
  const { id } = useParams();
  const { user, setUser } = useAuth();
  const navigate = useNavigate();

  const [localization, setLocalization] = useState();

  const { data } = UseFetch(`/produtos/produto/${id}`);
  const address = UseFetch(`/usuarios/endereco/${user.id}`);

  const urlImg = 'http://54.144.215.240/';

  const [loading, setLoading] = useState(false);
  const [loadingSecundary, setLoadingSecundary] = useState(false);
  const [frete, setFrete] = useState(false);

  const { register, errors, setValue } = useForm({});

  useEffect(() => {
    if (address.data) {
      setLocalization(
        address.data[0]?.estadoUf + ', ' + address.data[0]?.cidade,
      );
    }
  }, [address.data]);

  const handleAddCart = useCallback(
    async (button) => {
      if (button) {
        setLoading(true);
      } else {
        setLoadingSecundary(true);
      }

      try {
        await api.post(
          `/carrinhos/carrinho/adicionar/${id}/${user.id}`,
        );

        setLoading(false);
        setLoadingSecundary(false);

        if (button) {
          navigate('/cart');
        }
        return notificationSuccess(
          'Sucesso',
          'O produto foi adicionado ao carrinho',
        );
      } catch (error) {
        setLoading(false);
        setLoadingSecundary(false);

        console.log(error);
        return notificationError(
          'Ocorreu um erro',
          'Aguarde uns instantes e tente novamente',
        );
      }
    },
    [id, navigate, user.id],
  );

  const handleShipping = async (evt) => {
    let { value } = evt.target;
    const cep = value.replace('-', '');

    if (value.length !== 9) {
      evt.target.value = '';
      return notificationError(
        'CEP invalido',
        'Confira os digitos do seu CEP, e tente novamente',
      );
    }

    try {
      const { data } = await api.post(
        `/pedidos/frete/${cep}/${Number(id)}`,
      );

      setFrete(data);
      setUser((state) => {
        return { ...state, cep: cep };
      });
    } catch (error) {
      console.log(error);
      return notificationError(
        'Ocorreu um erro',
        'Aguarde uns instantes e tente novamente',
      );
    }
  };

  return (
    <>
      <Header />
      <Container className="container">
        <div className="flex">
          <ContentLeft className="flexColumn">
            <div>
              {data ? (
                <Cards
                  buy
                  title={data.nomeProduto}
                  price={data.precoProduto}
                  replacement={data.trocavel}
                  src={urlImg + `produtos/imagem/${data.idProduto}/1`}
                >
                  <img src={urlImg + `produtos/imagem/${data.idProduto}/2`} alt="img produto" />
                  <img src={urlImg + `produtos/imagem/${data.idProduto}/3`} alt="img produto" />
                  <img src={urlImg + `produtos/imagem/${data.idProduto}/4`} alt="img produto" />
                </Cards>
              ) : (
                <>
                  <Cards title="" loading buy />
                </>
              )}
            </div>
          </ContentLeft>

          <ContentRight>
            <h1>{data?.nomeProduto}</h1>
            <h1>
              {data?.precoProduto.toLocaleString('pt-br', {
                style: 'currency',
                currency: 'BRL',
              })}
            </h1>

            <p>Consultar prazo e valor de frete</p>

            <Input
              title="CEP"
              id="cep"
              name="cep"
              placeholder="Somente números"
              onChange={(evt) => mask(setValue, 'cep', evt, masks.cep)}
              onBlur={handleShipping}
              register={register}
              errors={errors.cep}
            />

            <strong>{frete && 'Valor do frete: R$ ' + frete}</strong>

            <Button loading={loading} onClick={() => handleAddCart(true)}>
              Comprar
            </Button>

            <ButtonSecundary
              loading={loadingSecundary}
              onClick={() => handleAddCart(false)}
            >
              Adicionar ao carrinho
            </ButtonSecundary>

            <div>
              <h4>Informações do vendedor</h4>
              <p>{data?.nomeUsuarioProduto}</p>

              <span>Tell: {data?.telefone}</span>
              <br />
              <span>{localization}</span>

              <Rate />
            </div>
          </ContentRight>
        </div>

        <Features className="flexColumn">
          <h2>Características principais</h2>

          <div className="flexColumn">
            <div className="flex">
              <p>Marca:</p>
              <span>{data?.marca}</span>
            </div>

            <div className="flex">
              <p>Tamanho:</p>
              <span>{data?.tamanhoProduto}</span>
            </div>

            <div className="flexColumn">
              <p>Descrição</p>
              <span>{data?.descricaoProduto}</span>
            </div>
          </div>
        </Features>

        <RowProduct title="Veja tambem" />
      </Container>
      <Footer />
    </>
  );
}
