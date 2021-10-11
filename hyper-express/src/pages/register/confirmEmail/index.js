import React, { useEffect, useState } from 'react';
import { mask, masks } from 'utils/masks';

import Steps from 'components/Steps/index';
import Title from 'components/Title';
import Input from 'components/Input';
import imgEmail from './assets/email.svg';
import { Content, Flex, Right, Lefth } from './style';
import { Container } from '../basicData/style';
import { MotionDiv } from 'components/FramerMotion/index';
import { api } from 'api/api';
import { notificationError } from 'utils/notifications';

export default function ConfirmEmail({ register, errors, setValue, email }) {
  const [code, setCode] = useState(0);

  useEffect(() => {
    const sendCode = async () => {
      const { data } = await api.get(`/emails/enviar?email=${email}`);
      setCode(data);
    };

    sendCode();
  }, [email]);

  const handleCodigo = async (evt) => {
    const { value } = evt.target;

    // eslint-disable-next-line eqeqeq
    if (value == code) {
      return;
    }

    evt.target.value = '';
    return notificationError(
      'codigo invalido',
      'O codigo inserido não é o mesmo que o enviado em seu email',
    );
  };

  return (
    <Container className="flexColumn">
      <Steps activeThree="active" />

      <Content>
        <Flex className="flex">
          <Lefth>
            <Title
              font="0.7"
              bold="normal"
              text="Falta pouco para realizar seu cadastro"
            />

            <p>
              Para finalizar seu cadastro, basta conferir o email informado no
              primeiro passo, e colocar o codigo que te enviamos aqui em baixo.
            </p>

            <div>
              <Input
                title="Código"
                id="codigo"
                name="codigo"
                type="number"
                placeholder="Somente números"
                onChange={(evt) => mask(setValue, 'codigo', evt, masks.codigo)}
                onBlur={handleCodigo}
                register={register}
                errors={errors.codigo}
              />
            </div>
          </Lefth>

          <Right>
            <MotionDiv
              initial={{ scale: 0 }}
              animate={{ rotate: 360, scale: 1 }}
              transition={{
                type: 'spring',
                stiffness: 20,
                damping: 5,
              }}
            >
              <img src={imgEmail} alt="Email" />
            </MotionDiv>
          </Right>
        </Flex>
      </Content>
    </Container>
  );
}
