import styled from 'styled-components';

export const Container = styled.div`
  align-items: start !important;

  @media (max-width: 820px) {
    flex-direction: column;
    justify-content: center !important;
  }
`;

export const ContainerInfoPedido = styled.div`
  width: 40%;

  margin-top: 2rem;
  padding: 2rem 3rem;

  border-radius: 3px;
  box-shadow: 0 0 20px #0002;

  p {
    font-size: 1.8rem;
    margin: 0.5rem 0;
  }

  strong {
    font-size: 2.5rem;
    margin: 0.5rem 0;
  }
  @media (max-width: 820px) {
    width: 100%;
  }
`;

export const Order = styled.div`
  width: 50%;

  @media (max-width: 820px) {
    width: 100%;
  }
`;
