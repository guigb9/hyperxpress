import styled from 'styled-components';

export const Container = styled.div`
  margin: 1rem 0 5rem;

  div.circulo {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 200px;
    height: 200px;
    border-radius: 50%;
    font-size: 5rem;
    font-weight: bold;
    color: var(--darkBold);
    border: 2px solid var(--dark);
  }

  @media (max-width: 1000px) {
    display: block;
  }
`;
