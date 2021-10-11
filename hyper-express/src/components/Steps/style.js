import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  height: 6.5rem;

  margin: 1rem 0 2rem;

  background: var(--gray);

  .active {
    box-shadow: 0px 0 0.5rem #0005;

    animation: show 500ms ease-in-out;
    transform: scale(1.02);

    background: var(--purple);

    @media (max-width: 720px) {
      width: 100%;

      display: flex;
      justify-content: center;
      align-items: center;

      h3 {
        font-size: 2rem;
      }
    }
  }
`;

export const Content = styled.div`
  width: 25%;
  height: 100%;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  h3 {
    font-weight: 500;
    font-size: 1.7rem;

    color: var(--whiteBold);
  }
`;
