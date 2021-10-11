import styled from 'styled-components';
import { Menu } from 'antd';

export const Container = styled.div`
  margin: 1.5rem 0 3rem;
`;

export const MenuAntd = styled(Menu)`
  font-size: 1.8rem;
  font-weight: bold;

  color: var(--dark);
  background: var(--white);

  li {
    border: 1px solid #0002;
    border-radius: 3px;
    margin: 1rem 0 !important;
  }

  .ant-menu-submenu-selected {
    color: var(--white);
    background: var(--purple);

    i {
      color: var(--white);
    }
  }

  .ant-menu-submenu-title:hover {
    color: unset !important;
  }

  li.ant-menu-submenu-active {
    color: var(--purpleDark) !important;
    background: var(--white);

    i {
      color: var(--purpleDark) !important;
    }

    li {
      background: #000 !important;
    }
  }
`;

export const Item = styled(Menu.Item)`
  width: 30rem;
  margin: 0.5rem 0 0 !important;

  font-size: 1.6rem;
  border-bottom: 1px solid #0002 !important;

  transition: all 150ms ease-in-out;
  color: var(--dark);

  &:hover {
    color: var(--purpleDark);
  }
`;
