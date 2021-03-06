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
  }).then((res) => {
    if (res.status >= 400) {
      return "아이디 또는 패스워드가 틀렸습니다.";
    } else {
      return res.json();
    }
  });
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
  }).then((res) => {
    if (res.status >= 400) {
      return "오류가 발생했습니다.";
    } else {
      return res.json();
    }
  });
}

function getMyKeywords(params){
  console.log('api.getMyKeywords');
  const queryString = util.queryString(params);
  const URL = API_SERVER + '/search-logs/my?' + queryString;

  return fetch(URL, {
    method: 'GET',
    headers: {
      Authorization: AUTH_TOKEN,
    },
  }).then((res) => {
    if (res.status >= 400) {
      return "오류가 발생했습니다.";
    } else {
      return res.json();
    }
  });
}

function getHotKeywords(params){
  console.log('api.getMyKeywords');
  const queryString = util.queryString(params);
  const URL = API_SERVER + '/search-logs/hot-keyword?' + queryString;

  return fetch(URL, {
    method: 'GET',
    headers: {
      Authorization: AUTH_TOKEN,
    },
  }).then((res) => {
    if (res.status >= 400) {
      return "오류가 발생했습니다.";
    } else {
      return res.json();
    }
  });
}


export default {
  join,
  login,
  logout,
  setAuthToken,
  searchBook,
  getMyKeywords,
  getHotKeywords
};
