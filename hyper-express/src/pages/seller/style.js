import styled from 'styled-components';

export const Container = styled.div`
  min-height: 80vh;

  .center {
    display: block;
  }
  .centralizar {
    width: 80%;
    margin: auto;
  }

  h3 {
    color: var(--darkBold);
    font-size: 3.5rem;
  }
`;

export const ButtonsFlex = styled.div`
  display: flex;
  justify-content: start;
  align-items: flex-start;

  button {
    width: 20rem;
    margin: unset !important;
  }
`;

export const Loading = styled.div`
  display: flex;
  justify-content: center;

  margin-top: 10rem;

  span {
    font-size: 6rem;
    color: #0003;
  }
`;
