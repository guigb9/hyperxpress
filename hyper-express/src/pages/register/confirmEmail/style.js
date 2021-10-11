import styled from 'styled-components';

export const Content = styled.div`
  width: 70%;
  max-width: 70rem;

  /* margin-top: 10px; */
  color: var(--dark);
`;

export const Flex = styled.div`
  width: 100%;
  margin-bottom: 5%;

  align-items: flex-start;
`;

export const Lefth = styled.div`
  width: 65%;

  p {
    margin: 5% 0 10%;

    font-size: 1.6rem;
    text-align: justify;
    color: var(--darkMedium);
  }
`;

export const Right = styled.div`
  img {
    width: 15rem;
    margin-top: 7rem;
  }
`;
