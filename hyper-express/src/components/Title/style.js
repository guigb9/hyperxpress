import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  justify-content: start;
  align-items: start;
  flex-direction: column;

  margin: 0.5rem 0;
`;

export const Texto = styled.h1`
  color: var(--darkBold);
`;

export const SubTexto = styled.span`
  font-weight: 500;
  color: var(--darkMedium);
`;
