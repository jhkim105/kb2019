import Head from 'next/head';
import Link from 'next/link';
import { Layout, Button } from 'antd';
import '../style/main.scss';
import './LayoutTemplate.scss';
const { Header, Footer, Content } = Layout;

const LayoutTemplate = ({ children, name = '' }) => {
  return (
    <React.Fragment>
      <Head>
        <title>Book</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta name="description" content="Book searcing site" />
      </Head>

      <div id={name} className="layout-template">
        <Header>
          <Link href="/">📚 Book</Link>
          <span>보고싶은 책을 검색해보세요</span>
        </Header>
        <Content>{children}</Content>
        <Footer>@powered by</Footer>
      </div>
    </React.Fragment>
  );
};

export default LayoutTemplate;
