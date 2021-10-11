import styled from 'styled-components';

export const Container = styled.div`
  /* height: 100vh; */
`;

export const Section = styled.section`
  margin-bottom: 20rem;

  h1 {
    margin-bottom: 5rem;
  }

  img {
    width: 55rem;
  }

  button {
    margin-top: 6rem;
  }
`;

export const Company = styled.section`
  text-align: center;

  color: var(--whiteBold);
  background: var(--dark);

  img {
    margin-top: 3rem;
  }

  span {
    width: 40rem;
    margin-bottom: 3rem;
  }
`;
