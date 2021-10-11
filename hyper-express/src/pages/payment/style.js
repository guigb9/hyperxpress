import styled from 'styled-components';

export const Conteiner = styled.div`
  @media (max-width: 720px) {
    flex-direction: column;
  }
`;

export const Address = styled.div`
  font-size: 1.8rem;
  padding: 0 2rem 1rem;

  a {
    color: var(--purple);
  }

  p {
    margin: 1.2rem 0;
  }

  @media (max-width: 720px) {
    margin: 0 0 3rem;
  }
`;
