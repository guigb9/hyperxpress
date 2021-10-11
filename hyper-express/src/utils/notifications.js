import { notification } from 'antd';

export const notificationSuccess = (title, msg) => {
  notification.success({
    message: title,
    description: msg,
  });
};

export const notificationInfo = (title, msg) => {
  notification.info({
    message: title,
    description: msg,
  });
};

export const notificationError = (title, msg) => {
  notification.error({
    message: title,
    description: msg,
  });
};
