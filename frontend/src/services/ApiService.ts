import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

export const getWelcomeMessage = async (): Promise<string> => {
    try {
        const response = await axios.get(`${BASE_URL}/auth/welcome`);
        return response.data;
    } catch (error) {
        throw new Error('Failed to fetch welcoe message.');
    }
};