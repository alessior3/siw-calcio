import api from './api';
import type { Squadra } from '../types';

export async function getSquadre(): Promise<Squadra[]> {
    try {
        // Usa l'istanza Axios creata prima e fa una richiesta GET
        const { data } = await api.get<Squadra[]>('/squadre');
        return data;
    } catch (error) {
        console.error("Errore nel caricamento delle squadre:", error);
        return [];
    }
}