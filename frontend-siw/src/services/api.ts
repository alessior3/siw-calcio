import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api', // Punta al tuo server Spring Boot
    withCredentials: true // Permette l'invio dei cookie (JSESSIONID) per l'autenticazione
});

export default api;