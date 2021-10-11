import React from 'react';
import { cpf } from 'cpf-cnpj-validator';
import { api } from 'api/api';
import { mask, masks } from 'utils/masks';
import Steps from 'components/Steps/index';
import Input from 'components/Input';
import { Container, Form, Content } from './style';
import { notificationError, notificationInfo } from 'utils/notifications';

import {
  MotionDiv,
  MotionContent,
  animation,
  itemsAnimation,
} from 'components/FramerMotion';

export default function BasicData({ register, errors, setValue }) {
  const handleCpf = (evt) => {
    const valid = cpf.isValid(evt.target.value);

    if (!valid) {
      // const cpfValid = cpf.generate();
      // evt.target.value = cpf.format(cpfValid);
      evt.target.value = "";
      return notificationError(
        'Cpf invalido',
        'Verifique o seu cpf e tente novamente',
      );
    }
  };

  const handleDate = (evt) => {
    const value = parseInt(evt.target.value);
    const year = new Date().getFullYear();

    if (year - 18 <= value) {
      evt.target.value = '';

      return notificationInfo(
        'Menor de idade',
        'Parece que você não tem idade suficiente para se cadastrar',
      );
    }
  };

  const handleEmail = async (evt) => {
    const { value } = evt.target;

    const { status } = await api.get(`/usuarios/verificar/${value}`);

    if (status === 200) {
      evt.target.value = '';
      return notificationError('Favor alterar o email', 'Este email ja existe');
    }
  };

  return (
    <Container className="flexColumn">
      <Steps activeOne="active" />

      <Form>
        <MotionDiv variants={animation} initial="hidden" animate="visible">
          <MotionContent variants={itemsAnimation}>
            <Content>
              <Input
                title="Nome"
                id="name"
                name="nome"
                placeholder="Seu nome completo"
                register={register}
                errors={errors.nome}
              />
              <Input
                title="Avatar"
                id="avatar"
                name="avatar"
                placeholder="Seu nome de avatar"
                register={register}
                errors={errors.avatar}
              />
            </Content>
          </MotionContent>

          <MotionContent variants={itemsAnimation}>
            <Content>
              <Input
                title="CPF"
                id="cpf"
                name="cpf"
                placeholder="Somente números"
                onBlur={handleCpf}
                onChange={(evt) => mask(setValue, 'cpf', evt, masks.cpf)}
                register={register}
                errors={errors.cpf}
              />
              <Input
                title="Data Nascimento"
                id="dataNasc"
                name="dataNasc"
                type="date"
                onBlur={handleDate}
                placeholder="sua data de nascimento"
                register={register}
                errors={errors.dataNasc}
              />
            </Content>
          </MotionContent>

          <MotionContent variants={itemsAnimation}>
            <Input
              title="Email"
              id="email"
              name="email"
              type="email"
              placeholder="you@example.com"
              onBlur={handleEmail}
              register={register}
              errors={errors.email}
            />
          </MotionContent>

          <MotionContent variants={itemsAnimation}>
            <Content>
              <Input
                title="Senha"
                id="senha"
                name="senha"
                type="password"
                placeholder="sua senha de acesso"
                register={register}
                errors={errors.senha}
              />
              <Input
                title="Confirmar Senha"
                id="confirmSenha"
                name="confirmSenha"
                type="password"
                placeholder="digite igual sua senha"
                register={register}
                errors={errors.confirmSenha}
              />
            </Content>
          </MotionContent>
        </MotionDiv>
      </Form>
    </Container>
  );
}
