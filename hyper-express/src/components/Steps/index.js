import React from 'react';
import { Container, Content } from './style';

export default function Steps({
  activeOne,
  activeTwo,
  activeThree,
  activeFour,
  stepOne = 'Dados pessoais',
  stepTwo = 'Sua localização',
  stepThree = 'Quase la',
  stepFour = 'Obrigado',
}) {
  return (
    <Container className="flex">
      <Content className={activeOne}>
        <h3> {stepOne} </h3>
      </Content>
      <Content className={activeTwo}>
        <h3> {stepTwo} </h3>
      </Content>
      <Content className={activeThree}>
        <h3> {stepThree} </h3>
      </Content>
      <Content className={activeFour}>
        <h3> {stepFour} </h3>
      </Content>
    </Container>
  );
}
