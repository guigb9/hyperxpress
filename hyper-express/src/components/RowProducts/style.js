import styled from 'styled-components';

export const Container = styled.div`
  position: relative;
  height: 35rem;
  margin: 10rem 0;
  overflow: hidden;

  .flex {
    transition: all 400ms ease-in-out;
  }

  &:hover div {
    opacity: 1;
  }
`;

export const Icon = styled.div`
  width: 4.5rem;
  height: 26rem;

  display: flex;
  justify-content: center;
  align-items: center;

  position: absolute !important;
  bottom: 0;

  margin-bottom: 1.4rem;
  opacity: 0;

  cursor: pointer;
  transition: all 400ms ease-in-out;
  z-index: 2;

  background: rgba(250, 250, 250, 0.4);
  &:nth-last-child(3) {
    left: 0;
  }

  &:nth-last-child(2) {
    right: 0;
  }

  &:hover span {
    color: var(--purple);
  }

  span {
    font-size: 5rem;
    color: var(--darkBold);

    svg,
    path {
      cursor: pointer;
    }
  }

  @media (max-width: 820px) {
    opacity: 1;
  }
`;
