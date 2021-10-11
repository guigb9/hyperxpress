import React, { useState } from 'react';
import { useAuth } from 'hooks/auth';
import { Avatar as AvatarAntd, Dropdown } from 'antd';
import { DownOutlined, LogoutOutlined } from '@ant-design/icons';
import { Link } from 'react-router-dom';

import Title from 'components/Title';
import UploadImg from 'pages/postProduct/uploadImg';
import Modal from 'components/Modal';
import Button from 'components/Button';

import { Content, Drop, MenuAntd } from './style';

export default function Avatar() {
  const { user, singOut } = useAuth();
  const [visible, setVisible] = useState(false);

  const handleCancelModal = () => {
    setVisible(false);
    window.location.reload();
  };

  const menu = (
    <MenuAntd>
      <MenuAntd.Item>
        <Drop>
          <Link to={`/seller/${user.id}`}>Meus Anúncios</Link>
        </Drop>
      </MenuAntd.Item>

      {user.picture && (
        <MenuAntd.Item>
          <Drop>
            <Link to="" onClick={() => setVisible(true)}>
              mudar foto
            </Link>
          </Drop>
        </MenuAntd.Item>
      )}

      <MenuAntd.Item>
        <Drop onClick={singOut}>
          <p>Logout</p>
          <LogoutOutlined />
        </Drop>
      </MenuAntd.Item>
    </MenuAntd>
  );

  return (
    <>
      <Dropdown overlay={menu}>
        <Content>
          <AvatarAntd
            size={32}
            src={
              'http://54.144.215.240/' +
              user.picture
            }
          />
          <span>{user.avatar}</span>
          <DownOutlined />
        </Content>
      </Dropdown>

      <Modal
        title="Que tal adicionar uma foto ao seu avatar ?"
        visible={visible}
        onCancel={handleCancelModal}
        footer={<Button onClick={handleCancelModal}>Finalizar</Button>}
      >
        <Title
          text="Carregue a sua foto de avatar"
          font="0.5"
          color="var(--dark)"
        />

        <UploadImg
          url={`http:\\\\localhost:8080/usuarios/imagem/${user.id}`}
          max="1"
          shape="round"
        />
        <p> Apos enviar a foto do seu avatar, bastas finalizar </p>
      </Modal>
    </>
  );
}
