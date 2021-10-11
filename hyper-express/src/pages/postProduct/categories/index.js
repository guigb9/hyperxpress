import React, { useState } from 'react';
import { Menu } from 'antd';

import { Container, MenuAntd, Item } from './style';

export default function Categories({ ...rest }) {
  const { SubMenu } = Menu;

  // submenu keys of first level
  const rootSubmenuKeys = ['1', '2', '3', '4', '5'];

  const [openKeys, setOpenKeys] = useState([]);

  const onOpenChange = (keys) => {
    const latestOpenKey = keys.find((key) => openKeys.indexOf(key) === -1);
    if (rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
      setOpenKeys(keys);
    } else {
      setOpenKeys(latestOpenKey ? [latestOpenKey] : []);
    }
  };

  return (
    <Container>
      <MenuAntd
        {...rest}
        mode="vertical"
        openKeys={openKeys}
        onOpenChange={onOpenChange}
      >
        <SubMenu key="1" title="Blusas">
          <Item key="1">Moletom</Item>
          <Item key="2">Corta Vento</Item>
          <Item key="3">Invernal</Item>
          <Item key="4">Moletom com toca</Item>
          <Item key="5">Moletom com ziper</Item>
          <Item key="6">Lã</Item>
          <Item key="7">Sem manga</Item>
          <Item key="8">Terno</Item>
          <Item key="9">Sobre tudo</Item>
        </SubMenu>

        <SubMenu key="2" title="Camisas">
          <Item key="10">Blusinha</Item>
          <Item key="11">Regata</Item>
          <Item key="12">Manga longa</Item>
          <Item key="13">Social</Item>
          <Item key="14">Normal</Item>
          <Item key="15">Poliester</Item>
          <Item key="16">Algodão</Item>
          <Item key="17">Time</Item>
          <Item key="18">Polo</Item>
          <Item key="19">Com toca</Item>
        </SubMenu>

        <SubMenu key="3" title="Bermudas">
          <Item key="20">Tactel</Item>
          <Item key="21">Moletom</Item>
          <Item key="22">Jeans</Item>
          <Item key="23">Cilismo</Item>
          <Item key="24">Shorts Futebol</Item>
          <Item key="25">Shorts Sarja</Item>
          <Item key="26">Shorts Praia</Item>
          <Item key="27">Shorts Femenino</Item>
          <Item key="28">Femenino</Item>
        </SubMenu>

        <SubMenu key="4" title="Calças">
          <Item key="29">Jeans</Item>
          <Item key="30">Moletom</Item>
          <Item key="31">Tactel</Item>
          <Item key="32">Sarja</Item>
          <Item key="33">Social</Item>
          <Item key="34">Legging</Item>
        </SubMenu>

        <SubMenu key="5" title="Calçados">
          <Item key="35">Tenis</Item>
          <Item key="36">Sapato</Item>
          <Item key="37">Sandalia</Item>
          <Item key="38">Bota</Item>
          <Item key="39">Cuturno</Item>
          <Item key="40">Sapatenis</Item>
        </SubMenu>
      </MenuAntd>
    </Container>
  );
}
