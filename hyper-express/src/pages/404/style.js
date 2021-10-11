import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  height: 100vh;

  background: #000;

  font-size: 1.6rem;

  align-items: flex-start !important;
  padding: 8rem 6rem;

  color: var(--dark);
  background: var(--white);

  @media (max-width: 1024px) {
    align-items: center !important;
    justify-content: center;

    img {
      width: 30rem;
    }
  }

  @media (max-width: 700px) {
    font-size: 90%;
    img {
      width: 20rem;
    }
  }
`;

export const ContentLeft = styled.div`
  img {
    margin-bottom: 3rem;
    animation: sobe-desce 4s infinite;
  }

  h2 {
    text-align: center;
    margin: 1.5rem 0;
  }

  h4 {
    text-align: center;
    line-height: 2rem;

    &:nth-last-child(2) {
      margin-bottom: 4rem;
    }
  }
`;

export const ContenRight = styled.div`
  img {
    width: 57rem;
    padding-top: 5em;
    margin-left: 12em;
  }

  @media (max-width: 1024px) {
    display: none;
  }
`;
