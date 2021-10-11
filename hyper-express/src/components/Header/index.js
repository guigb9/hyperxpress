import React, { useState } from 'react';
import { useAuth } from 'hooks/auth';
import { Link } from 'react-router-dom';

import InputSearch from 'components/InputSearch';
import Hamburguer from './Hamburguer';
import Avatar from './Avatar';
import 'antd/dist/antd.css';

import imgLogo from 'assets/svgs/Logo.svg';
import imgHome from 'assets/svgs/home.svg';
import imgDelivery from 'assets/svgs/delivery.svg';
import imgEntrar from 'assets/svgs/entrar.svg';
import imgCarrinho from 'assets/svgs/carrinho.svg';

import { Container, Primary, Secundary, InputContent } from './style';
import Button from 'components/Button';

export default function Header() {
  const { logged } = useAuth();

  return (
    <Container>
      <Primary className="padding flex">
        <Link id="logo" to="/">
          <img src={imgLogo} alt="logo" />
        </Link>

        <div className="flex">
          <Link to="/">
            <img src={imgHome} alt="Home" />
            <span>Home</span>
          </Link>

          <Link to="/cart">
            <img src={imgCarrinho} alt="carrinho" />
            <span>Carrinho</span>
          </Link>

          <Link to="/follow-up">
            <img src={imgDelivery} alt="favoritos" />
            <span>Acompamento</span>
          </Link>

          <Link to="/post">
            <Button>Anunciar</Button>
          </Link>

          {logged ? (
            <Avatar />
          ) : (
            <Link to="/login">
              <img src={imgEntrar} alt="entrar" />
              <span>Entrar</span>
            </Link>
          )}

          <Hamburguer />
        </div>
      </Primary>

      <Secundary className="padding flexColumn">
        <InputContent>
          <InputSearch />
        </InputContent>

        <div>
          <Link to="/products/1">Blusas</Link>
          <Link to="/products/2">Camisas</Link>
          <Link to="/products/3">Bermudas</Link>
          <Link to="/products/4">Calças</Link>
          <Link to="/products/5">Calçados</Link>
        </div>
      </Secundary>
    </Container>
  );
}
