import React, { useState, useCallback, useEffect } from 'react';
import { UseFetch } from 'hooks/useFetch';
import { useProduct } from 'hooks/useProducts';

import { Checkbox, Space, Radio } from 'antd';

import Title from 'components/Title';
import { Container } from './style';

export default function Filter({ id }) {
  const { setProduct } = useProduct();
  const [options, setOptions] = useState();

  const onReplacement = (e) => {
    setProduct((state) => {
      return { ...state, replacement: e };
    });
  };

  const onGender = (e) => {
    setProduct((state) => {
      return { ...state, gender: e.target.value };
    });
  };

  const onSubCategory = (e) => {
    setProduct((state) => {
      return { ...state, subCategory: e.target.value };
    });
  };

  useEffect(() => {
    if (Number(id) === 1) {
      return setOptions([
        { value: '', label: 'Todos' },
        { value: 1, label: 'Moletom' },
        { value: 2, label: 'Corta Vento' },
        { value: 3, label: 'Invernal' },
        { value: 4, label: 'Moletom com toca' },
        { value: 5, label: 'Moletom com ziper' },
        { value: 6, label: 'Lã' },
        { value: 7, label: 'Sem manga' },
        { value: 8, label: 'Terno' },
        { value: 9, label: 'Sobretudo' },
      ]);
    }

    if (Number(id) === 2) {
      return setOptions([
        { value: '', label: 'Todos' },
        { value: 10, label: 'Blusinha' },
        { value: 11, label: 'Regata' },
        { value: 12, label: 'Manga longa' },
        { value: 13, label: 'Social' },
        { value: 14, label: 'Normal' },
        { value: 15, label: 'Poliester' },
        { value: 16, label: 'Algodão' },
        { value: 17, label: 'Time' },
        { value: 18, label: 'Polo' },
        { value: 19, label: 'Com toca' },
      ]);
    }

    if (Number(id) === 3) {
      return setOptions([
        { value: '', label: 'Todos' },
        { value: 20, label: 'Tactel' },
        { value: 21, label: 'Moletom' },
        { value: 22, label: 'Jeans' },
        { value: 23, label: 'Cilismo' },
        { value: 24, label: 'Shorts Futebol' },
        { value: 25, label: 'Shorts Sarja' },
        { value: 26, label: 'Shorts Praia' },
        { value: 27, label: 'Shorts Femenino' },
        { value: 28, label: 'Femenino' },
      ]);
    }

    if (Number(id) === 4) {
      return setOptions([
        { value: '', label: 'Todos' },
        { value: 29, label: 'Jeans' },
        { value: 30, label: 'Moletom' },
        { value: 31, label: 'Tactel' },
        { value: 32, label: 'Sarja' },
        { value: 33, label: 'Social' },
        { value: 34, label: 'Legging' },
      ]);
    }

    if (Number(id) === 5) {
      return setOptions([
        { value: '', label: 'Todos' },
        { value: 35, label: 'Tenis' },
        { value: 36, label: 'Sapato' },
        { value: 37, label: 'Sandalia' },
        { value: 38, label: 'Bota' },
        { value: 39, label: 'Cuturno' },
        { value: 40, label: 'Sapatenis' },
      ]);
    }
  }, [id]);

  return (
    <Container>
      <Title
        text="Produto"
        font="0.65"
        color="var(--darkMedium)"
        alignment="start"
      />
      <Checkbox.Group onChange={onReplacement}>
        <Checkbox value={true}>Trocavel</Checkbox>
      </Checkbox.Group>

      <Title
        text="Genêro"
        font="0.65"
        color="var(--darkMedium)"
        alignment="start"
      />

      <Radio.Group onChange={onGender}>
        <Space direction="vertical">
          <Radio value="">Todos</Radio>
          <Radio value="masculino">Masculino</Radio>
          <Radio value="feminino">Feminino</Radio>
        </Space>
      </Radio.Group>
      <Title
        text="Categoria"
        font="0.65"
        color="var(--darkMedium)"
        alignment="start"
      />

      <Radio.Group
        className="flexColumn"
        options={options}
        onChange={onSubCategory}
      />
    </Container>
  );
}
