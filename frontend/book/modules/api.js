import fetch from 'isomorphic-unfetch';
import util from './util';
const API_SERVER = 'http://localhost:8080';
let AUTH_TOKEN = null;

function join(params) {
  console.log('api.join');
  const URL = API_SERVER + '/users/join';

  return fetch(URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(params),
  }).then((res) => res.json());
}

function login(params) {
  console.log('api.login');
  const URL = API_SERVER + '/users/login';

  return fetch(URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(params),
  }).then((res) => res.json());
}

function logout() {
  console.log('api.logout');
}

function setAuthToken(token) {
  console.log('api.setAuthToken', token);
  AUTH_TOKEN = token;
}

function searchBook(params) {
  console.log('api.searchBook');
  const queryString = util.queryString(params);
  const URL = API_SERVER + '/book-search?' + queryString;

  return fetch(URL, {
    method: 'GET',
    headers: {
      Authorization: AUTH_TOKEN,
    },
  }).then((res) => res.json());
}

export default {
  join,
  login,
  logout,
  setAuthToken,
  searchBook,
};
