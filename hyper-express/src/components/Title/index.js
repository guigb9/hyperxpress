import React from 'react';
import { Container, Texto, SubTexto } from './style';

export default function Title({
  text = 'Hyper Xpress',
  subText,
  font = 1,
  alignment = 'center',
  bold,
  color = 'var(--darkBold)',
}) {
  return (
    <Container style={{ alignItems: alignment }}>
      <Texto
        style={{ fontSize: 4.0 * font + 'rem', fontWeight: bold, color: color }}
      >
        {text}
      </Texto>
      <SubTexto style={{ fontSize: 1.6 * font + 'rem', color: color }}>
        {subText}
      </SubTexto>
    </Container>
  );
}
