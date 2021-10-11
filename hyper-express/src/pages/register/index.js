import React, { useCallback, useState } from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { SchemaBasic, SchemaLocal, SchemaCodigo } from 'utils/schema';
import { useNavigate } from 'react-router-dom';
import { useAuth } from 'hooks/auth';

import { notificationError, notificationSuccess } from 'utils/notifications';
import Title from 'components/Title';
import BasicData from './basicData';
import LocalData from './localData';
import ConfirmEmail from './confirmEmail';
import ThanksData from './thanks';
import UploadImg from 'pages/postProduct/uploadImg';
import Modal from 'components/Modal';
import ButtonSecundary from 'components/ButtonSecundary';
import Button from 'components/Button';
import { Container, Box, ContentForm } from './style';
import { api } from 'api/api';

export default function Register() {
  const { signIn, user } = useAuth();
  const navigate = useNavigate();

  const [loading, setLoading] = useState(false);
  const [email, setEmail] = useState();
  const [visible, setVisible] = useState(false);

  const [currentStep, setCurrentStep] = useState(0);
  const [form, setForm] = useState();

  const isValid = useCallback(() => {
    if (currentStep === 0) {
      return SchemaBasic;
    } else if (currentStep === 1) {
      return SchemaLocal;
    } else {
      return SchemaCodigo;
    }
  }, [currentStep]);

  const handleCancelModal = () => {
    setVisible(false);
  };

  const { register, handleSubmit, errors, setValue } = useForm({
    resolver: yupResolver(isValid()),
  });

  const onSubmit = async (data) => {
    const { confirmSenha, codigo, ...forms } = { ...data };

    setForm((basicData) => {
      return { ...basicData, ...forms };
    });

    if (currentStep === 1) {
      setEmail(form.email);
    }

    if (currentStep === 2) {
      setLoading(true);

      try {
        await api.post('/usuarios', form);
        await signIn(form);

        setVisible(true);

        notificationSuccess(
          'Parabéns cadastro realizado',
          'Seu login ja foi efetuado',
        );
      } catch (error) {
        setLoading(false);
        console.log(error);

        return notificationError(
          'Ops, ocorreu um erro',
          'Não foi possivel completar seu cadastro',
        );
      }
    }

    setCurrentStep(currentStep + 1);
  };

  const componentForms = [
    <BasicData register={register} errors={errors} setValue={setValue} />,
    <LocalData register={register} errors={errors} setValue={setValue} />,
    <ConfirmEmail
      register={register}
      errors={errors}
      setValue={setValue}
      email={email}
    />,
    <ThanksData register={register} errors={errors} setValue={setValue} />,
  ];

  const backStep = () => {
    if (currentStep === 0) {
      return navigate('/login');
    }
    return setCurrentStep(currentStep - 1);
  };

  return (
    <Container className="flexColumn">
      <Box>
        <Title subText="Sua roupa da melhor forma" />

        <form onSubmit={handleSubmit(onSubmit)}>
          <ContentForm>{componentForms[currentStep]}</ContentForm>

          <div className="flex">
            {currentStep > 2 ? (
              <div className="buttons">
                <ButtonSecundary onClick={() => navigate('/')}>
                  Comprar
                </ButtonSecundary>

                <Button onClick={() => navigate('/')}>Anunciar</Button>
              </div>
            ) : (
              <div className="buttons">
                <ButtonSecundary type="button" onClick={backStep}>
                  Voltar
                </ButtonSecundary>

                <Button loading={loading}>Continuar</Button>
              </div>
            )}
          </div>
        </form>
      </Box>

      <Modal
        title="Que tal adicionar uma foto ao seu avatar ?"
        visible={visible}
        onCancel={handleCancelModal}
        footer={<Button onClick={handleCancelModal}>Finalizar</Button>}
      >
        <Title
          text="Carregue a sua foto de avatar"
          font="0.5"
          color="var(--dark)"
        />

        <UploadImg
          url={`http://54.144.215.240/usuarios/imagem/${user.id}`}
          max="1"
          shape="round"
        />
        <p> Apos enviar a foto do seu avatar, bastas finalizar </p>
      </Modal>
    </Container>
  );
}
