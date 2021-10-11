import React, { useState, useCallback } from 'react';
import { Upload } from 'antd';
import { CameraOutlined } from '@ant-design/icons';
import ImgCrop from 'antd-img-crop';
import { useProduct } from 'hooks/useProducts';

import { Container, AddPicture } from './style';

export default function UploadImg({ url, shape = 'rect', max = 4 }) {
  const { setProduct } = useProduct();
  const [fileList, setFileList] = useState([]);

  const onChange = ({ fileList: newFileList }) => {
    setFileList(newFileList);

    if (fileList.length === 4) {
      setProduct({ imgLength: false });
    }
  };

  const onPreview = async (file) => {
    let src = file.url;
    if (!src) {
      src = await new Promise((resolve) => {
        const reader = new FileReader();
        reader.readAsDataURL(file.originFileObj);
        reader.onload = () => resolve(reader.result);
      });
    }

    const image = new Image();
    image.src = src;
    const imgWindow = window.open(src);
    imgWindow.document.write(image.outerHTML);
  };

  return (
    <Container>
      <ImgCrop modalTitle="Selecione sua imagem" shape={shape}>
        <Upload
          action={url}
          listType="picture-card"
          maxCount={max}
          fileList={fileList}
          onChange={onChange}
          onPreview={onPreview}
        >
          {fileList.length < max && (
            <AddPicture className="flexColumn">
              <CameraOutlined />
              Adicionar
            </AddPicture>
          )}
        </Upload>
      </ImgCrop>
    </Container>
  );
}
