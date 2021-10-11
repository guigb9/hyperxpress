import React from 'react';
import { Link } from 'react-router-dom';
import erro from 'assets/svgs/number404.svg';
import notFound from 'assets/svgs/not-found.svg';
import { Container, ContentLeft, ContenRight } from './style';

export default function Error404() {
  return (
    <Container className="flex">
      <ContentLeft className="flexColumn">
        <img src={erro} alt="" />
        <h2>Ops, está página não existe</h2>

        <h4>Confira se você digitou algo errado,</h4>
        <h4>Talvez o endereço não esteja mais disponível na web.</h4>

        <Link className="linkButton" to="/">
          Voltar para inicio
        </Link>
      </ContentLeft>

      <ContenRight>
        <img src={notFound} alt="" />
      </ContenRight>
    </Container>
  );
}
