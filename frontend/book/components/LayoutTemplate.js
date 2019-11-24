import Head from 'next/head';
import Link from 'next/link';
import {Layout, Menu} from 'antd';
import './LayoutTemplate.scss';

const { Header, Footer, Content, Sider } = Layout;

const LayoutTemplate = ({ children, name = '', sidebar }) => {
  return (
    <React.Fragment>
      <Head>
        <title>Book</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta name="description" content="Book searcing site" />
      </Head>

      <div id={name} className="layout-template">
        <Layout>
          <Header>
            <Link href="/">
              <a>Book</a>
            </Link>
            <span>보고싶은 책을 검색해보세요</span>
          </Header>

          <Layout className="container" hasSider={!!sidebar}>
            {sidebar && (
              <Sider>
                <Menu defaultSelectedKeys={[name]} mode="inline" theme="dark">
                  <Menu.Item key="search">
                    <Link href="/search">검색</Link>
                  </Menu.Item>
                  <Menu.Item key="my-keywords">
                    <Link href="/my-keywords">내 검색어</Link>
                  </Menu.Item>
                  <Menu.Item key="hot-keywords">
                    <Link href="/hot-keywords">인기 검색어 10</Link>
                  </Menu.Item>
                </Menu>
              </Sider>
            )}
            <Content>{children}</Content>
          </Layout>
          <Footer>@powered by</Footer>
        </Layout>
      </div>
    </React.Fragment>
  );
};

export default LayoutTemplate;
