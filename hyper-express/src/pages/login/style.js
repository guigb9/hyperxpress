import styled from 'styled-components';
import imgBackground from 'assets/backgrounds/nike-login.jpg';

export const Container = styled.div`
  width: 100%;
  height: 100vh;
`;

// Parte da Direta (Formularios)

export const ContentRight = styled.div`
  width: 40%;
  height: 100vh;

  @media (max-width: 820px) {
    width: 50%;
  }

  @media (max-width: 700px) {
    width: 100%;
  }
`;

export const FormContent = styled.div`
  width: 95%;
  max-width: 45rem;

  margin-top: 2rem;
  padding: 4rem;

  form {
    width: 100%;
  }
`;

export const FlexContainer = styled.div`
  margin: -1rem 0 3.5rem;
`;

// Parte da esquerda (Foto, Qrcode)

export const ContentLefth = styled.div`
  position: relative;
  width: 60%;
  height: 100vh;

  padding-top: 10rem;

  background: url(${imgBackground});
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;

  div {
    position: absolute;
    width: 15rem;
    height: 15rem;

    bottom: 3rem;
    right: 3rem;

    opacity: 0.7;
    text-align: center;

    img {
      width: 12rem;
      border-radius: 5px;

      transition: all ease 200ms;

      :hover {
        transform: scale(1.1);
      }
    }

    span {
      margin: 0.7rem 0;

      font-size: 1.5rem;
      font-weight: 700;

      color: var(--whiteBold);
    }
  }

  transition: all ease-in-out 400ms;

  @media (max-width: 820px) {
    width: 50%;
  }

  @media (max-width: 700px) {
    width: 0;

    opacity: 0;
    visibility: hidden;
  }
`;
