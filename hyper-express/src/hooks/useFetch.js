import useSWR from 'swr';
import { api } from 'api/api';
import { notificationError } from 'utils/notifications';

export function UseFetch(url) {
  const { data, error } = useSWR(url, async (url) => {
    try {
      const response = await api.get(url);
      return response.data;
    } catch (error) {
      console.log(error);
      return notificationError(
        'Erro ao conectar ao servidor',
        'Verifique sua conexão com a internet e tente novamente',
      );
    }
  });

  return { data, error };
}
