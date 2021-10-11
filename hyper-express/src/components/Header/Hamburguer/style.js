import styled from 'styled-components';
import { Drawer } from 'antd';

export const Container = styled.div`
  svg {
    width: 2.4rem;
    height: 2.4rem;

    cursor: pointer;

    &:hover {
      color: var(--purple);
    }
  }
`;

export const Menu = styled(Drawer)`
  .ant-drawer-header {
    div {
      height: 4rem;
      padding-top: 1rem;

      font-weight: bold;
      font-size: 4rem;
    }

    svg {
      width: 4rem;
      height: 4rem;

      margin-top: -0.5rem;

      font-weight: bold;
      cursor: pointer;

      &:hover {
        color: var(--purple);
      }
    }
  }

  .flexColumn {
    margin-top: 5rem;
    font-weight: bold;

    a {
      font-size: 2.6rem;
      margin: 2.1rem 0;

      transition: ease-out 150ms;
      color: var(--dark);

      &:hover {
        transform: scale(1.05);
        color: var(--purple);
      }

      span {
        margin-left: 1.5rem;
      }

      .imgEntrar {
        margin-left: -3.5rem;
      }
    }
  }
`;
