import React from 'react';
import { Container } from './styles';
import logo from '../Footer/assets/images/LogoHyper.svg';
import facebook from '../Footer/assets/images/facebook.png';
import instagram from '../Footer/assets/images/instagram.png';
import { Link } from 'react-router-dom';
import { useAuth } from 'hooks/auth';

export default function Footer() {
  const { user } = useAuth();

  return (
    <Container>
      <div className="Wrapper">
        <div className="Row">
          <ul>
            <div className="Title">Links Úteis</div>
            <li className="Link">
              <Link to={'/report'} className="Link">
               Suporte
              </Link>
            </li>
            <li className="Link">
              <Link to="/products/1" className="Link">
                Produtos
              </Link>
            </li>
            <li className="Link">
              <Link to="/cart" className="Link">
                Carrinho
              </Link>
            </li>
            <li className="Link">
              <Link to="/register" className="Link">
                Cadastro
              </Link>
            </li>

            <li className="Link">
              <Link to={'/seller/' + user.id} className="Link">
                Meus anúncios
              </Link>
            </li>
          </ul>
          <ul>
            <div className="Title">Contato</div>
            <h2 className="FooterTxt">0800 123 4567</h2>
            <h4 className="FooterTxt">
              segunda a domingo: 8h às 20h (exceto feriados)
            </h4>
          </ul>
          <ul>
            <div className="Title">Rede Sociais</div>
            <li className="Link">
              <img className="Social" src={facebook} alt="facebook"></img>
              <a className="Link" href="https://www.facebook.com/">
                Facebook
              </a>
            </li>
            <li className="Link">
              <img className="Social" src={instagram} alt="instagram"></img>
              <a className="Link" href="https://www.instagram.com/">
                Instagram
              </a>
            </li>
          </ul>
          <ul>
            <img className="Logo" src={logo} alt="logo-hyperExpress"></img>
            <h2 className="FooterTxt">Hyper Xpress</h2>
          </ul>
        </div>
      </div>
    </Container>
  );
}
