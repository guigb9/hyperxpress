import styled from 'styled-components';
import { Menu } from 'antd';

export const Content = styled.span`
  span {
    margin-left: 0.5rem;

    font-size: 1.4rem;
    cursor: pointer;
    transition: all 200ms ease-in-out;

    &:hover {
      color: var(--purpleDark);
    }
  }
  @media (max-width: 720px) {
    svg {
      display: none;
    }
    span:nth-child(2) {
      display: none;
    }
  }
`;

export const MenuAntd = styled(Menu)`
  width: 15.5rem;

  margin-top: 1.5rem;
  border-radius: unset;
  box-shadow: 0 0 10px #0003;

  @media (max-width: 720px) {
    bottom: 1rem;
    left: 4.6rem;
  }
`;

export const Drop = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  transition: all 150ms ease-in-out;

  &:hover {
    color: var(--purple);
    opacity: 0.8;
  }

  p,
  a {
    color: inherit;
    margin-right: 0.5rem;
    font-weight: 500;

    cursor: pointer;
  }
`;
