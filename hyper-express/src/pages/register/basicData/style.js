import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  height: 100%;

  justify-content: start !important;
`;

export const Form = styled.div`
  width: 100%;
  max-width: 68rem;

  margin-top: 0.5rem;

  @media (max-width: 720px) {
    padding: 0 6rem;
  }
`;

export const Content = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  div {
    width: 31rem;
  }

  @media (max-width: 720px) {
    flex-direction: column;

    div {
      width: 100%;
      margin: 0.5rem 0;
    }
  }
`;
