import React, { useEffect, useState } from 'react';
import { useAuth } from 'hooks/auth';
import { useNavigate } from 'react-router-dom';

import RowProduct from 'components/RowProducts';
import Header from 'components/Header';
import Title from 'components/Title';
import Footer from 'components/Footer';

import imgBuy from 'assets/svgs/money.svg';
import imgProducts from 'assets/svgs/produtos.svg';
import imgReplacement from 'assets/svgs/trocas.svg';
import imgSales from 'assets/svgs/vendas.svg';

import Button from 'components/Button';
import { Container, Section, Company } from './style';

export default function Home() {
  const navigate = useNavigate();
  return (
    <>
      <Header />

      <Container className="container">
        <Section className="flex">
          <div>
            <Title
              text=" Com a covid-19, fique em casa e aumente sua renda vendendo suas
              roupas."
            />

            <Button onClick={() => navigate('/post')}>Anunciar</Button>
          </div>

          <div>
            <img src={imgBuy} alt="foto roupas" />
          </div>
        </Section>

        <Title text="Diversos produtos, para diversos gostos." />

        <RowProduct title="Recomendados" />
        <RowProduct title="Destaques" />
      </Container>

      <Company className="flex padding">
        <div className="flexColumn">
          <img src={imgProducts} alt="produtos" />
          <Title color="var(--whiteBold)" text="Produtos" />
          <span>
            Compre boas roupas e em bom estado e contribua para o consumo
            conciente
          </span>
        </div>

        <div className="flexColumn">
          <img src={imgReplacement} alt="trocas" />
          <Title color="var(--whiteBold)" text="Trocas" />
          <span>
            Troque seus produtos e compartilhe seu bom gosto com outras pessoas.
          </span>
        </div>

        <div className="flexColumn">
          <img src={imgSales} alt="Vendas" />
          <Title color="var(--whiteBold)" text="Vendas" />
          <span>
            Anuncie as roupas que você não usa mais e aumente sua renda.
          </span>
        </div>
      </Company>

      <Container className="container">
        <RowProduct title="Populares" />
        <RowProduct title="Veja Também" />
      </Container>

      <Footer />
    </>
  );
}
