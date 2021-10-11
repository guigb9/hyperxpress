import React, { useState } from 'react';
import { useAuth } from 'hooks/auth';
import { Link } from 'react-router-dom';

import imgPremium from 'assets/svgs/premium.svg';
import imgFavoritos from 'assets/svgs/favoritos.svg';
import imgCarrinho from 'assets/svgs/carrinho.svg';
import imgAnuncios from 'assets/svgs/anuncios.svg';
import imgEntrar from 'assets/svgs/entrar.svg';

import { MenuOutlined } from '@ant-design/icons';
import { Container, Menu } from './style';

export default function Hamburguer() {
  const { logged } = useAuth();
  const [visible, setVisible] = useState(false);

  const toogleDrawer = () => {
    if (visible) {
      return setVisible(false);
    }
    setVisible(true);
  };

  return (
    <Container>
      <MenuOutlined onClick={toogleDrawer} />

      <Menu
        title="Menu"
        placement="right"
        closable={true}
        onClose={toogleDrawer}
        visible={visible}
      >
        <div className="flexColumn">
          {logged && (
            <Link to="/login">
              <img className="imgEntrar" src={imgEntrar} alt="entrar" />
              <span>Entrar</span>
            </Link>
          )}

          <Link to="/post">
            <img src={imgAnuncios} alt="Anúncios" />
            <span>Anunciar</span>
          </Link>

          <Link to="/premium">
            <img src={imgPremium} alt="premium" />
            <span>Premium</span>
          </Link>

          <Link to="/cart">
            <img src={imgCarrinho} alt="carrinho" />
            <span>Carrinho</span>
          </Link>

          <Link to="/favorites">
            <img src={imgFavoritos} alt="favoritos" />
            <span>Favoritos</span>
          </Link>
        </div>
      </Menu>
    </Container>
  );
}
