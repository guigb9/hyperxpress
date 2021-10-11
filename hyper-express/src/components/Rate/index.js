import React, { useState } from 'react';
import { Rate as RateAntd } from 'antd';

import { Container } from './style';

export default function Rate() {
  const desc = ['Péssimo', 'Ruim', 'Normal', 'Bom', 'Excelente'];
  const [value, setValue] = useState(2);

  const handleChange = (evt) => {
    setValue(evt);
  };

  return (
    <Container>
      <RateAntd tooltips={desc} onChange={handleChange} value={value} />
      {/* {value ? <span className="ant-rate-text">{desc[value - 1]}</span> : ''} */}
    </Container>
  );
}
