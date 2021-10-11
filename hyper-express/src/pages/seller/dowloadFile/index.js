import React from 'react';
import { useAuth } from 'hooks/auth';

import { Menu, Dropdown } from 'antd';
import { DownOutlined } from '@ant-design/icons';
import Button from 'components/Button';

import { Container } from './style';

export default function DowloadFile() {
  const { user } = useAuth();

  const url =
    'http://54.144.215.240/downloads/produto/';

  const menu = (
    <Menu>
      <Menu.Item>
        <a
          target="_blank"
          rel="noopener noreferrer"
          href={url + `layout?email=${user.email}`}
        >
          Documento Layout
        </a>
      </Menu.Item>

      <Menu.Item>
        <a
          target="_blank"
          rel="noopener noreferrer"
          href={url + `csv?email=${user.email}`}
        >
          CSV
        </a>
      </Menu.Item>

      <Menu.Item>
        <a
          target="_blank"
          rel="noopener noreferrer"
          href={url + `txt?email=${user.email}`}
        >
          TXT
        </a>
      </Menu.Item>
    </Menu>
  );

  return (
    <Container>
      <Dropdown overlay={menu}>
        <Button onClick={(e) => e.preventDefault()}>Dowload</Button>
      </Dropdown>
    </Container>
  );
}
