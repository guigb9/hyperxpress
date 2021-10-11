import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  min-height: 100vh;
`;

export const Content = styled.div`
  width: 100%;
  margin-top: 7rem;
  align-items: flex-start !important;
`;

export const Letf = styled.div`
  width: 36.5%;
`;

export const Right = styled.div`
  width: 55%;
  margin-top: 0.6rem;
`;

export const Flex = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  margin-bottom: 1.5rem;

  input,
  select {
    width: 30rem;
  }
`;
