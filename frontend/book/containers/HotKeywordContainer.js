import React, {Component} from 'react';
import {Button, Empty, Table} from 'antd';
import api from '../modules/api';

const {Search} = Button;

const columns = [
  {
    title: '키워드',
    dataIndex: 'keyword'
  },
  {
    title: '검색횟수',
    dataIndex: 'searchCount'
  }
];

class HotKeywordContainer extends Component{
  state = {
    content: [],
    loading: false
  };

  setData = ({content, number, size, totalElements}) => {
    console.log('setData :', content, number, size, totalElements);

    this.setState({
      content: content
    });
  };

  getData = (params = {}) => {
    this.handleLoading(true);

    api
        .getHotKeywords(params)
        .then((data) => {
          console.log('response', data);

          if (data.totalElements) {
            this.setData(data);
          } else {
            alert(data.status);
          }

          this.handleLoading(false);
        })
        .catch((error) => {
          alert('API error:' + error);
          this.handleLoading(false);
        });
  };

  handleLoading = (state) => {
    this.setState({
      loading: state,
    });
  };

  handleSearch = () => {
   this.setState({
    });

    this.getData();
  };

  render(){
    const {handleSearch} = this;
    const {content, loading, pagination} = this.state;

    return (
        <React.Fragment>
          <section id="search-input-wrap">
            <Button onClick={handleSearch} >조회하기</Button>
          </section>
          <section id="my-keyword-result-wrap">
            {content.length ? (
                <Table
                    columns={columns}
                    dataSource={content}
                    loading={loading}
                />
            ) : (
                <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} description="데이터가 없습니다."/>
            )}
          </section>
        </React.Fragment>
    );
  }
}

export default HotKeywordContainer;