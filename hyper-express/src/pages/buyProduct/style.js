import styled from 'styled-components';

export const Container = styled.div`
  min-height: 100vh;
`;

export const ContentLeft = styled.div`
  padding: 0 0 0 7rem;
  /* background: #000; */
`;

export const ContentRight = styled.div`
  height: 60rem;

  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;

  h1,
  h2 {
    color: var(--dark);
  }

  h4 {
    margin-bottom: -2rem;
  }

  p {
    margin-top: 3rem;
    color: #0007;
  }

  strong {
    margin-bottom: 3rem;
  }

  button:nth-child(7) {
    margin-bottom: 5rem;
  }
`;

export const Features = styled.div`
  width: 50rem;
  margin-top: 10rem;

  color: var(--dark);

  div {
    padding: 0.5rem;
    font-size: 2rem;

    p {
      font-weight: bold;
    }

    span {
      font-size: 1.8rem;
      color: var(--darkBold);
    }

    .flex,
    .flexColumn {
      border: 1px solid #0002;
    }

    .flex {
      padding-right: 22rem;
      p {
        width: 15rem;
      }
    }

    .flexColumn {
      text-align: center;
      p {
        margin-bottom: 1rem;
        border-bottom: 1px solid #0004;
      }
    }
  }
`;
