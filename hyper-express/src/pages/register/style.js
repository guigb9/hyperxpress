import styled from 'styled-components';
import imgBackground from 'assets/backgrounds/roupas-register.jpg';

export const Container = styled.div`
  width: 100%;
  height: 100vh;
  min-height: 100vh;

  background: linear-gradient(160deg, #0003, #0003), url(${imgBackground});
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
`;

export const Box = styled.div`
  width: 70%;
  min-height: 93%;

  border-radius: 5px;
  box-shadow: 0px 2px 80px #0008;

  padding: 5px 0;
  background: var(--white);

  button {
    width: 25rem;
    font-size: 1.5rem;
  }

  .buttons {
    display: flex;
    justify-content: center;
    align-items: center;

    width: 60rem;
    margin: 0 auto;
  }

  @media (max-width: 720px) {
    width: 100%;
    min-height: 100vh;
    padding: 3.5rem 0;
  }
`;

export const ContentForm = styled.div`
  width: 100%;
  height: 67vh;
  min-height: 65vh;

  margin-bottom: 1rem;

  @media (max-width: 720px) {
    height: 100%;
  }
`;
