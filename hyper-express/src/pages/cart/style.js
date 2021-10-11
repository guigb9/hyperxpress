import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  min-height: 100vh;
  align-items: flex-start !important;

  #resume {
    margin-top: 8.8rem;
  }

  @media (max-width: 720px) {
    flex-direction: column;
    align-items: center !important;
  }
`;

export const ScrollItens = styled.div`
  position: relative;
  width: 60%;

  @media (max-width: 720px) {
    width: 100%;
  }
`;

export const Empty = styled.div`
  margin: 7rem 0 5rem;

  img {
    width: 15rem;
  }

  span {
    color: #0008;
  }
`;

export const Loading = styled.div`
  margin-top: 10rem;

  span {
    font-size: 6rem;
    color: #0003;
  }
`;
