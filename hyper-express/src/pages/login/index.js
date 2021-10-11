import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { SchemaLogin } from 'utils/schema';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from 'hooks/auth';

import Qrcode from 'assets/svgs/qrcode.svg';
import Title from 'components/Title';
import Input from 'components/Input';
import Button from 'components/Button';
import 'antd/dist/antd.css';
import {
  MotionDiv,
  MotionContent,
  animation,
  itemsAnimation,
} from 'components/FramerMotion';
import {
  Container,
  ContentRight,
  FormContent,
  FlexContainer,
  ContentLefth,
} from './style';

export default function Login() {
  const { signIn } = useAuth();
  const navigate = useNavigate();

  const [loading, setLoading] = useState(false);

  const { register, handleSubmit, errors } = useForm({
    resolver: yupResolver(SchemaLogin),
  });

  const onSubmit = async (data) => {
    setLoading(true);
    const response = await signIn(data);

    if (!response) {
      return setLoading(false);
    }
    navigate('/');
  };

  return (
    <MotionDiv variants={animation} initial="hidden" animate="visible">
      <Container className="flex">
        <ContentRight className="flexColumn">
          <Title font="1.2" subText="Sua roupa da melhor forma" />

          <FormContent>
            <form onSubmit={handleSubmit(onSubmit)}>
              <MotionContent variants={itemsAnimation}>
                <Input
                  title="Email"
                  id="email"
                  name="email"
                  type="email"
                  placeholder="you@example.com"
                  register={register}
                  errors={errors.email}
                />
              </MotionContent>

              <MotionContent variants={itemsAnimation}>
                <Input
                  title="Senha"
                  id="senha"
                  name="senha"
                  type="password"
                  placeholder="min 8 caracteres"
                  register={register}
                  errors={errors.senha}
                />
              </MotionContent>

              <MotionContent variants={itemsAnimation}>
                <FlexContainer className="flex">
                  <Link className="link" to="/register">
                    Não tenho cadastro
                  </Link>
                </FlexContainer>
              </MotionContent>

              <MotionContent variants={itemsAnimation}>
                <Button loading={loading} type="submit">
                  Entrar
                </Button>
              </MotionContent>
            </form>
          </FormContent>
        </ContentRight>

        <ContentLefth className="flexColumn">
          <div className="flexColumn">
            <MotionContent variants={itemsAnimation}>
              <img src={Qrcode} alt="qrcode" />
              <span>Baixe o App!</span>
            </MotionContent>
          </div>
        </ContentLefth>
      </Container>
    </MotionDiv>
  );
}
