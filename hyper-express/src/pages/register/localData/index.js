import React from 'react';
import axios from 'axios';
import { mask, unMasks, masks } from 'utils/masks';
import Steps from 'components/Steps/index';
import Input from 'components/Input';
import { notification } from 'antd';
import { Container, Form, Content } from '../basicData/style';
import {
  MotionDiv,
  MotionContent,
  animation,
  itemsAnimation,
} from 'components/FramerMotion';

export default function LocalData({ register, errors, setValue }) {
  const openNotification = () => {
    notification.error({
      message: 'Não foi possivel achar este Cep',
      description: 'OPS, você colocou um cep inválido',
    });
  };

  const handleCep = async (evt) => {
    const cep = unMasks(evt.target.value);

    const { data } = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);

    if (data.erro) {
      openNotification();
      setValue('estadoUf', '');
      setValue('cidade', '');
      setValue('logradouro', '');
      setValue('bairro', '');
    } else {
      setValue('estadoUf', data.uf);
      setValue('cidade', data.localidade);
      setValue('logradouro', data.logradouro);
      setValue('bairro', data.bairro);
    }
  };

  return (
    <Container className="flexColumn">
      <Steps activeTwo="active" />

      <Form>
        <MotionDiv variants={animation} initial="hidden" animate="visible">
          <MotionContent variants={itemsAnimation}>
            <Content>
              <Input
                title="CEP"
                id="cep"
                name="cep"
                placeholder="Somente números"
                onBlur={handleCep}
                onChange={(evt) => mask(setValue, 'cep', evt, masks.cep)}
                register={register}
                errors={errors.cep}
              />
              <Input
                title="UF"
                id="uf"
                name="estadoUf"
                placeholder="Ex: SP"
                readOnly="readonly"
                register={register}
                errors={errors.estadoUf}
              />
            </Content>
          </MotionContent>

          <MotionContent variants={itemsAnimation}>
            <Input
              title="Cidade"
              id="localidade"
              name="cidade"
              placeholder="Ex: São Caetano do Sul"
              readOnly="readonly"
              register={register}
              errors={errors.cidade}
            />
          </MotionContent>

          <MotionContent variants={itemsAnimation}>
            <Content>
              <Input
                title="Endereço"
                id="logradouro"
                name="logradouro"
                placeholder="Ex: Rua tocantins"
                readOnly="readonly"
                register={register}
                errors={errors.logradouro}
              />
              <Input
                title="Número"
                id="numero"
                name="numero"
                placeholder="Nºde sua residência"
                onChange={(evt) => mask(setValue, 'numero', evt, '99999')}
                register={register}
                errors={errors.numero}
              />
            </Content>
          </MotionContent>

          <MotionContent variants={itemsAnimation}>
            <Content>
              <Input
                title="Bairro"
                id="bairro"
                name="bairro"
                placeholder="Ex: Maua"
                readOnly="readonly"
                register={register}
                errors={errors.bairro}
              />
              <Input
                title="Complemento"
                id="complement"
                name="complemento"
                placeholder="Ex: apt 01"
                register={register}
                errors={errors.complemento}
              />
            </Content>
          </MotionContent>
        </MotionDiv>
      </Form>
    </Container>
  );
}
