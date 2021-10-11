import React from 'react';
import Title from 'components/Title';
import { Container } from './style';

export default function Circle({ number, title }) {
  return (
    <Container className="flexColumn">
      <div className="circulo">{number}</div>
      <Title text={title} font="0.9" />
    </Container>
  );
}
