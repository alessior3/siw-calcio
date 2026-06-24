import { useState, useEffect } from 'react';
import SquadreList from './components/SquadreList';
import { CssBaseline, ThemeProvider, createTheme, AppBar, Toolbar, Typography, Button, Box, Link } from '@mui/material';
import { getCurrentUser } from './services/authService';

// Creiamo un tema per copiare esattamente lo stile di stile.css del backend
const theme = createTheme({
  palette: {
    primary: {
      main: '#2c3e50', // var(--primary)
    },
    secondary: {
      main: '#27ae60', // var(--secondary)
    },
    background: {
      default: '#f8f9fa', // var(--bg-color)
      paper: '#ffffff', // var(--card-bg)
    },
    text: {
      primary: '#333333', // var(--text-main)
      secondary: '#7f8c8d', // var(--text-light)
    }
  },
  typography: {
    fontFamily: 'system-ui, -apple-system, sans-serif',
  },
  components: {
    MuiAppBar: {
      styleOverrides: {
        root: {
          boxShadow: 'none', // La navbar originale non ha ombra pesante
          padding: '0.5rem 1rem', // Simile al padding originale
        }
      }
    },
    MuiCard: {
      styleOverrides: {
        root: {
          borderRadius: '8px',
          boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
        }
      }
    }
  }
});

function App() {
  const [username, setUsername] = useState<string | null>(null);

  useEffect(() => {
    getCurrentUser().then(user => setUsername(user));
  }, []);

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline /> {/* Resetta il CSS di base del browser */}
      <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
        
        {/* HEADER / NAVBAR */}
        <AppBar position="static" sx={{ mb: 4 }}>
          <Toolbar>
            <Typography variant="h6" component="div" sx={{ fontWeight: 'bold', fontSize: '1.5rem', mr: '30px' }}>
              ⚽ SIW Tornei
            </Typography>
            
            {/* Nav Links */}
            <Box sx={{ display: 'flex', gap: '1.5rem', flexGrow: 1 }}>
              <Link href="http://localhost:8080/" color="inherit" underline="none" sx={{ fontWeight: 500, '&:hover': { color: 'secondary.main' } }}>Home</Link>
              <Link href="http://localhost:8080/tornei" color="inherit" underline="none" sx={{ fontWeight: 500, '&:hover': { color: 'secondary.main' } }}>Tornei</Link>
              <Link href="http://localhost:8080/squadre" color="inherit" underline="none" sx={{ fontWeight: 500, '&:hover': { color: 'secondary.main' } }}>Gestione Squadre</Link>
              <Link href="#" color="inherit" underline="none" sx={{ fontWeight: 'bold', color: '#ffeb3b' }}>Cerca Squadre</Link>
              <Link href="http://localhost:8080/partite" color="inherit" underline="none" sx={{ fontWeight: 500, '&:hover': { color: 'secondary.main' } }}>Partite</Link>
            </Box>

            {/* Azioni di Autenticazione Dinamiche */}
            <Box sx={{ display: 'flex', gap: '15px', alignItems: 'center' }}>
              {username ? (
                <>
                  <Typography sx={{ fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: '5px' }}>
                    👤 {username}
                  </Typography>
                  <Button href="http://localhost:8080/logout" variant="contained" sx={{ backgroundColor: '#ef4444', color: 'white', textTransform: 'none', fontWeight: 'bold', '&:hover': { backgroundColor: '#dc2626' } }}>
                    Esci
                  </Button>
                </>
              ) : (
                <>
                  <Link href="http://localhost:8080/login" color="inherit" underline="none" sx={{ fontWeight: 'bold' }}>Login</Link>
                  <Button href="http://localhost:8080/register" variant="contained" color="secondary" sx={{ textTransform: 'none', fontWeight: 'bold' }}>
                    Registrati
                  </Button>
                </>
              )}
            </Box>
          </Toolbar>
        </AppBar>

        {/* CONTENUTO PRINCIPALE */}
        <Box component="main" sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <SquadreList />
        </Box>

        {/* FOOTER */}
        <Box component="footer" sx={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          backgroundColor: 'primary.main',
          color: 'white',
          padding: '1.5rem',
          marginTop: 'auto'
        }}>
          <Typography>&copy; 2026 Gestione Tornei Amatoriali - Progetto SIW</Typography>
        </Box>

      </Box>
    </ThemeProvider>
  );
}

export default App;