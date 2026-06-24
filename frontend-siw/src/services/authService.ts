import api from './api';

export async function getCurrentUser(): Promise<string | null> {
    try {
        const { data } = await api.get<{ username: string | null }>('/auth/me');
        return data.username;
    } catch (error) {
        console.error("Errore nel caricamento dell'utente:", error);
        return null;
    }
}
