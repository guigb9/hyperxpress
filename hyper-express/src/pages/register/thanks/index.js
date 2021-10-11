import React from 'react';
import Title from 'components/Title';
import Steps from 'components/Steps';
import InputSearch from 'components/InputSearch';

import { Container } from '../basicData/style';
import { Content } from './style';

export default function Thanks() {
  return (
    <Container className="flexColumn">
      <Steps activeFour="active" />

      <Content className="flexColumn">
        <Title
          font="0.85"
          bold="normal"
          text="Obrigado por ter nos escolhido."
        />
        <InputSearch />
        <p>O que você deseja fazer ?</p>
      </Content>
    </Container>
  );
}
