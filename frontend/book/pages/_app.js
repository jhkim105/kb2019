import React from 'react';
import App from 'next/app';
import Router from 'next/router';
import api from '../modules/api';
import { withRouter } from 'next/router';
import '../style/main.scss';

class MyApp extends App {
  constructor(props) {
    super(props);

    this.state = {
      isLogined: false,
    };
  }

  componentDidMount() {
    const savedAuthToken = localStorage.getItem('authToken');
    const { pathname } = this.props.router;
    console.log('router.pathname', pathname);

    if (savedAuthToken) {
      api.setAuthToken(savedAuthToken);
      this.setState({
        isLogined: true,
      });
    } else if (pathname.match(/\/|join/)) {
      // 인증키 없이 서브페이지 접근시 로그인 페이지로 이동
      Router.push('/');
    }
  }

  handleLogin = async (params) => {
    console.log('handleLogin :', params);

    api.login(params).then((data) => {
      if (data.authToken) {
        this.onLogin(data.authToken);
      } else {
        alert(data.status);
      }
    });
  };

  onLogin = (token) => {
    api.setAuthToken(token);
    this.setState({
      isLogined: true,
    });

    localStorage.setItem('authToken', token);
    Router.push('/search');
  };

  handleLogout = () => {
    console.log('handleLogout');

    // api.logout()
    this.setState({
      isLogined: false,
    });
    Router.push('/');
  };

  render() {
    const { Component } = this.props;

    return <Component {...this.state} handleLogin={this.handleLogin} handleLogout={this.handleLogout} />;
  }
}

export default withRouter(MyApp);
