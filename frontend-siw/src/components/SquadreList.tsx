import { useState, useEffect } from 'react';
import { getSquadre } from '../services/squadraService';
import type { Squadra } from '../types'; // Nota il "type" che abbiamo sistemato prima!
import { 
    Container, 
    Grid, 
    Card, 
    CardContent, 
    Typography, 
    TextField, 
    Box,
    CircularProgress
} from '@mui/material';

export default function SquadreList() {
    // 1. Definiamo lo Stato (useState)
    const [squadre, setSquadre] = useState<Squadra[]>([]);
    const [ricerca, setRicerca] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(true);

    // 2. Effettuiamo la chiamata API al caricamento (useEffect)
    useEffect(() => {
        // Funzione asincrona interna come da manuale React
        async function fetchDati() {
            const data = await getSquadre();
            setSquadre(data);
            setLoading(false); // Finito di caricare
        }
        fetchDati();
    }, []); // L'array vuoto significa: "esegui solo al mount (prima volta)"

    // 3. Logica per il Bonus "Filtri" (si aggiorna automaticamente se 'ricerca' cambia)
    const squadreFiltrate = squadre.filter(s => 
        s.nome.toLowerCase().includes(ricerca.toLowerCase())
    );

    // 4. Se sta ancora caricando, mostra la rotellina (Feedback all'utente)
    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 5 }}>
                <CircularProgress />
            </Box>
        );
    }

    // 5. Render finale della pagina con Material UI
    return (
        <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
            <Typography variant="h4" component="h1" align="center" sx={{ mb: '2rem', fontWeight: 'bold', color: 'text.primary' }}>
                Squadre Partecipanti
            </Typography>

            {/* Barra di ricerca */}
            <Box sx={{ mb: 4 }}>
                <TextField 
                    fullWidth 
                    label="Cerca una squadra per nome..." 
                    variant="outlined" 
                    value={ricerca}
                    onChange={(e) => setRicerca(e.target.value)} // Aggiorna lo stato a ogni tasto premuto
                />
            </Box>

            {/* Griglia delle Squadre */}
            <Grid container spacing={3}>
                {squadreFiltrate.map((squadra) => (
                    <Grid size={{ xs: 12, sm: 6, md: 4 }} key={squadra.id}>
                        <Card>
                            <CardContent sx={{ p: '1.5rem' }}>
                                {/* card-header simile a stile.css */}
                                <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', gap: '1rem', borderBottom: '1px solid #eee', pb: '0.5rem', mb: '1rem' }}>
                                    <Typography variant="h6" component="div" sx={{ fontWeight: 'bold', color: 'text.primary' }}>
                                        {squadra.nome}
                                    </Typography>
                                </Box>
                                {/* card-body simile a stile.css */}
                                <Box sx={{ display: 'flex', flexDirection: 'column', gap: '0.5rem', color: 'text.secondary' }}>
                                    <Typography variant="body1">
                                        <strong>Città:</strong> {squadra.citta}
                                    </Typography>
                                    <Typography variant="body1">
                                        <strong>Fondazione:</strong> {squadra.annoDiFondazione}
                                    </Typography>
                                </Box>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
                
                {/* Messaggio se la ricerca non produce risultati */}
                {squadreFiltrate.length === 0 && (
                    <Grid size={{ xs: 12 }}>
                        <Typography align="center" color="text.secondary">
                            Nessuna squadra trovata con questo nome.
                        </Typography>
                    </Grid>
                )}
            </Grid>
        </Container>
    );
}
