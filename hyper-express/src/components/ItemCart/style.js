import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;

  margin-top: 2rem;
`;

export const Disabled = styled.div`
  svg {
    &:hover {
      color: green !important;
    }
  }
`;

export const Content = styled.div`
  margin: 1% 0 3%;
  padding: 2% 5%;

  box-shadow: 0 0 1rem #0005;

  a {
    width: 90% !important;
  }

  svg {
    width: 2.3rem;
    height: 2.3rem;

    cursor: pointer;
    color: var(--darkBold);

    path {
      cursor: pointer;
    }
  }
  svg:hover {
    color: var(--redError);
  }
  img {
    width: 6rem;
    height: 6rem;
    border-radius: 3px;
  }
  p {
    display: inline-block;
    margin: 0 2% 0 2%;

    cursor: pointer;
    color: var(--dark);

    &:hover {
      color: var(--purple);
    }

    &:nth-child(2) {
      width: 20rem;
    }
    &:nth-child(3) {
      width: 2rem;
    }
    &:nth-child(4) {
      width: 8rem;
    }
  }
`;
