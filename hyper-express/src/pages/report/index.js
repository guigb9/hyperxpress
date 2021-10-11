import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { SchemaReport } from 'utils/schema';
import { api } from 'api/api';
import { notificationError, notificationSuccess } from 'utils/notifications';

import Header from 'components/Header';
import Button from 'components/Button';
import Title from 'components/Title';
import Imgreport from './images/report.svg';
import Input from 'components/Input';
import Select from 'components/Select';
import TextArea from 'components/TextArea';

import { Container, FormContent } from './style';
import {
  MotionDiv,
  MotionContent,
  animation,
  itemsAnimation,
} from 'components/FramerMotion';

export default function Report() {
  const [loading, setLoading] = useState(false);

  const { register, handleSubmit, errors } = useForm({
    resolver: yupResolver(SchemaReport),
  });

  const onSubmit = async (data) => {
    setLoading(true);

    const response = await api.post('/report', data);

    if (response.status !== 201) {
      setLoading(false);

      return notificationError(
        'Ocorreu um erro',
        'não foi possivel fazer o chamado',
      );
    }

    if (response) {
      setLoading(false);
      console.log(data);
      return notificationSuccess('Parabens', 'O seu chamado foi efetuado');
    }
  };

  return (
    <>
      <Header />
      <Container>
        <MotionDiv variants={animation} initial="hidden" animate="visible">
          <div className="report">
            <div className="flex container">
              <div className="container">
                <Title
                  text="Como podemos te ajudar ?"
                  font="0.8"
                  alignment="start"
                />

                <FormContent>
                  <form onSubmit={handleSubmit(onSubmit)}>
                    <MotionContent variants={itemsAnimation}>
                      <Select
                        title="Assunto"
                        id="assunto"
                        name="assunto"
                        register={register}
                        errors={errors.assunto}
                      >
                        <option value="">Selecione</option>
                        <option value="preciso de ajuda">
                          Precisa de ajuda
                        </option>
                        <option value="reportar erro">Reportar falha</option>
                        <option value="feedback">Feedback</option>
                      </Select>
                    </MotionContent>

                    <MotionContent variants={itemsAnimation}>
                      <Input
                        title="Nome"
                        id="name"
                        name="nome"
                        placeholder="Como deseja ser chamado"
                        register={register}
                        errors={errors.nome}
                      />
                    </MotionContent>

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
                      <TextArea
                        title="Mensagem"
                        id="message"
                        name="mensagem"
                        maxLength="350"
                        register={register}
                        errors={errors.mensagem}
                      />
                    </MotionContent>

                    <MotionContent className="flex" variants={itemsAnimation}>
                      <Button loading={loading} type="submit">
                        Enviar
                      </Button>
                    </MotionContent>
                  </form>
                </FormContent>
              </div>
              <div className="ImgReport">
                <img src={Imgreport} alt="report" />
              </div>
            </div>
          </div>
        </MotionDiv>
      </Container>
    </>
  );
}
