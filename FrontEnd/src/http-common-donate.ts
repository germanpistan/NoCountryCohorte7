import axios from 'axios';

export default axios.create({
  baseURL: 'https://bda-back-prueba.herokuapp.com/donations',
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  },
  method: 'POST',
});
