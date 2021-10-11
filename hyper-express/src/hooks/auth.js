import { createContext, useContext, useCallback } from 'react';
import usePersisted from './usePersisted';
import { api } from 'api/api';
import { notificationError } from 'utils/notifications';

const UserContext = createContext({});

const AuthProvider = ({ children }) => {
  const [user, setUser] = usePersisted('user', {});

  const signIn = useCallback(
    async (login) => {
      const signIndata = {
        email: login.email,
        senha: login.senha,
      };
      try {
        const { data, status } = await api.post('usuarios/login', signIndata);

        if (status !== 200) {
          notificationError(
            'Não foi possivel fazer o login',
            'Usuario não encontrado',
          );
          return false;
        }

        if (data) {
          setUser({
            id: data.id,
            name: data.nome,
            avatar: data.avatar,

            email: data.email,
            picture: data.foto,
          });

          return true;
        }
      } catch (error) {
        return notificationError(
          'Não foi possivel fazer o login',
          'Erro no servidor',
        );
      }
    },
    [setUser],
  );

  const singOut = useCallback(() => {
    localStorage.removeItem('@hyper:user', '@hyper:user');
    window.location.reload();
  }, []);

  const logged = user.avatar;

  return (
    <UserContext.Provider value={{ signIn, singOut, user, setUser, logged }}>
      {children}
    </UserContext.Provider>
  );
};

const useAuth = () => useContext(UserContext);

export { AuthProvider, useAuth };
