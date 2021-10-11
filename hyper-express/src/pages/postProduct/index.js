import React, { useState, useCallback } from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { mask, masks } from 'utils/masks';
import { api } from 'api/api';
import { SchemaProduct } from 'utils/schema';
import { useAuth } from 'hooks/auth';
import { useNavigate } from 'react-router-dom';
import { notificationError } from 'utils/notifications';
import { useProduct } from 'hooks/useProducts';

import Header from 'components/Header';
import Input from 'components/Input';
import Title from 'components/Title';
import Footer from 'components/Footer';
import Categories from './categories';
import UploadImg from './uploadImg';

import Modal from 'components/Modal';
import Button from 'components/Button';
import Select from 'components/Select';
import TextArea from 'components/TextArea';

import { Container, Content, Right, Letf, Flex } from './style';
import {
  MotionDiv,
  MotionContent,
  animation,
  itemsAnimation,
} from 'components/FramerMotion';

export default function PostProduct() {
  const navigate = useNavigate();
  const { user } = useAuth();
  const { product, setProduct } = useProduct();

  const [visible, setVisible] = useState();
  const [loading, setLoading] = useState(false);
  const [subCategoria, SetSubCategoria] = useState();
  const [idProduto, SetIdProduto] = useState(0);

  const codigoUsuarioProd = { id: user.id };

  const { register, handleSubmit, setValue, errors } = useForm({
    resolver: yupResolver(SchemaProduct),
  });

  const handleCancelModal = useCallback(async () => {
    setVisible(false);
    setLoading(false);

    setProduct((state) => {
      return { ...state, imgLength: false };
    });

    try {
      await api.delete(`/produtos/excluir/${idProduto}`);
    } catch (error) {
      console.log(error);
    }
  }, [idProduto, setProduct]);

  const onSubmit = async (data) => {
    const precoProduto = Number(data.preco.replace(',', '.'));
    const trocavel = data.troca === 'true' ? true : false;

    const { troca, preco, ...inputs } = { ...data };
    const form = {
      ...inputs,
      subCategoria,
      precoProduto,
      codigoUsuarioProd,
      trocavel,
    };

    setProduct((state) => {
      return { ...state, imgLength: true };
    });

    if (!subCategoria) {
      return notificationError(
        'Informe a categoria',
        'Você não informou a categoria do produto',
      );
    }
    setLoading(true);

    const response = await api.post(`/produtos`, form);
    SetIdProduto(response.data);

    if (response.status === 400) {
      setLoading(false);
      return notificationError(
        'Ocorreu um erro',
        'Aguarde uns instantes e tente novamente',
      );
    }

    setVisible(true);
  };

  console.log(product.imgLength);

  return (
    <>
      <Header />
      <Container className="container">
        <Title
          text="Vamos anunciar o seu produto"
          font="0.85"
          color="var(--dark)"
        />

        <Content className="flex">
          <Letf>
            <Title
              text="Selecione sua categoria"
              font="0.55"
              alignment="start"
              color="var(--dark)"
            />

            <Categories onClick={(evt) => SetSubCategoria({ id: evt.key })} />
          </Letf>

          <Right>
            <MotionDiv variants={animation} initial="hidden" animate="visible">
              <form onSubmit={handleSubmit(onSubmit)}>
                <MotionContent variants={itemsAnimation}>
                  <Input
                    title="Nome Produto"
                    id="nome"
                    name="nomeProduto"
                    type="text"
                    placeholder="Ex: Camiseta Thasher"
                    register={register}
                    errors={errors.nomeProduto}
                  />
                </MotionContent>

                <MotionContent variants={itemsAnimation}>
                  <Flex>
                    <Input
                      title="Marca"
                      id="marca"
                      name="marca"
                      type="text"
                      placeholder="Ex: Thasher"
                      register={register}
                      errors={errors.marca}
                    />

                    <Input
                      title="Preço (R$)"
                      id="price"
                      name="preco"
                      type="text"
                      placeholder="Ex: 120,00"
                      onChange={(evt) => mask(setValue, 'preco', evt)}
                      register={register}
                      errors={errors.preco}
                    />
                  </Flex>
                </MotionContent>

                <MotionContent variants={itemsAnimation}>
                  <Flex>
                    <Input
                      title="Tecido"
                      id="fabric"
                      name="tecido"
                      type="text"
                      placeholder="Ex: Algodão"
                      register={register}
                      errors={errors.tecido}
                    />

                    <Select
                      title="Gênero"
                      id="gender"
                      name="genero"
                      register={register}
                      errors={errors.genero}
                    >
                      <option value="">Selecione</option>
                      <option value="masculino">Masculino</option>
                      <option value="feminino">Feminino</option>
                    </Select>
                  </Flex>
                </MotionContent>

                <MotionContent variants={itemsAnimation}>
                  <Flex>
                    <Input
                      title="Tamanho"
                      id="size"
                      name="tamanhoProduto"
                      type="text"
                      maxLength="2"
                      placeholder="Ex: GG ou 42"
                      register={register}
                      errors={errors.tamanhoProduto}
                    />

                    <Select
                      title="Trocável"
                      id="replacement"
                      name="troca"
                      register={register}
                      errors={errors.troca}
                    >
                      <option value="">Selecione</option>
                      <option value="true">Sim</option>
                      <option value="false">Não</option>
                    </Select>
                  </Flex>
                </MotionContent>

                <MotionContent variants={itemsAnimation}>
                  <Input
                    title="Telefone"
                    id="telefone"
                    name="telefone"
                    placeholder="Somente números"
                    maxLength="16"
                    onChange={(evt) =>
                      mask(setValue, 'telefone', evt, masks.telefone)
                    }
                    register={register}
                    errors={errors.telefone}
                  />
                </MotionContent>

                <MotionContent variants={itemsAnimation}>
                  <TextArea
                    title="Descrição"
                    id="descripition"
                    name="descricaoProduto"
                    maxLength="350"
                    register={register}
                    errors={errors.descricaoProduto}
                  />
                </MotionContent>

                <MotionContent className="flex" variants={itemsAnimation}>
                  <Button loading={loading} type="submit">
                    Anunciar
                  </Button>
                </MotionContent>
              </form>
            </MotionDiv>
          </Right>
        </Content>

        <Modal
          title="Falta pouco !"
          visible={visible}
          onCancel={handleCancelModal}
          footer={
            <Button disabled={product.imgLength} onClick={() => navigate('/')}>
              Finalizar
            </Button>
          }
        >
          <Title
            text="Carregue a foto do seu produto"
            font="0.5"
            color="var(--dark)"
          />

          <UploadImg
            url={`http://54.144.215.240/produtos/imagem/${idProduto}`}
          />
          <p> Após enviar suas 4 imagens, basta finalizar </p>
          <p>
            Lembrando que sua primeira foto, será a foto principal do seu
            produto.
          </p>
        </Modal>
      </Container>
      <Footer />
    </>
  );
}
