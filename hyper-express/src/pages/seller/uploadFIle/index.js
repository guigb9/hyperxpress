import React from 'react';

import { Upload, message } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

import Button from 'components/Button';

import { Container } from './style';

export default function UploadFile({ url }) {
  const props = {
    name: 'arquivo',
    action: url,
    headers: {
      authorization: 'authorization-text',
    },
    beforeUpload: (file) => {
      if (file.type !== 'text/plain') {
        message.error(`${file.name} não é um arquivo de texto`);
      }
      return file.type === 'text/plain' ? true : Upload.LIST_IGNORE;
    },
    onChange(info) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }

      if (info.file.status === 'done') {
        message.success(`Upload de ${info.file.name} concluido`);
      } else if (info.file.status === 'error') {
        message.error(`Upload de ${info.file.name} falhou.`);
      }
    },
    progress: {
      strokeColor: {
        '0%': 'rgb(33, 37, 41)',
        '100%': 'rgb(84, 35, 160)',
      },
      strokeWidth: 3,
      format: (percent) => `${parseFloat(percent.toFixed(2))}%`,
    },
  };
  return (
    <Container>
      <Upload {...props}>
        <Button>Upload</Button>
      </Upload>
    </Container>
  );
}
