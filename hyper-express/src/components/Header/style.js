import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
`;

export const Primary = styled.div`
  position: fixed;

  height: 7.5rem;
  z-index: 5;

  border-bottom: 5px solid var(--purple);
  background: var(--white);

  .flex {
    width: 65rem;

    div {
      display: none;
    }
  }

  > img {
    width: 6.5rem;
  }

  a {
    color: var(--dark);

    button {
      width: 13rem;
      height: 3.2rem;

      font-size: 1.3rem;
      border-radius: 3rem;
    }

    span {
      font-size: 1.4rem;
      margin-left: 0.5rem;
      transition: all 200ms ease-in-out;

      &:hover {
        color: var(--purpleDark);
      }
    }
  }

  @media (max-width: 720px) {
    .flex {
      width: unset;

      div {
        display: block;
        margin-left: 3rem;
      }
    }
    a {
      display: none;
    }

    #logo {
      display: block;
      img {
        width: 6rem;
      }
    }
  }
`;

export const Secundary = styled.div`
  height: 22rem;

  padding-top: 7rem !important;
  justify-content: space-around !important;

  background: var(--purple);

  a {
    margin: 0 1.2rem;
    padding: 0.5rem 2rem;
    font-size: 1.7rem;

    color: var(--white);
    transition: all 200ms ease-in-out;

    &:hover {
      color: var(--gray);
    }
  }

  @media (max-width: 1024px) {
    a {
      margin: 0 0.8rem;
      padding: 0 1.5rem;
    }
  }

  @media (max-width: 925px) {
    a {
      margin: 0 0.3rem;
      padding: 0 1.2rem;
    }
  }

  @media (max-width: 720px) {
    height: 19rem;
    a {
      display: none;
    }
  }
`;

export const InputContent = styled.div`
  width: 88.2rem;
  margin-top: 2rem;

  @media (max-width: 720px) {
    width: 100%;
  }
`;
