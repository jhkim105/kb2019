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
    title: '검색일시',
    dataIndex: 'createdDate'
  }
];

class MyKeywordContainer extends Component{
  state = {
    content: [],
    loading: false,
    pagination: {
      pageSize: 2
    }
  };

  setData = ({content, number, size, totalElements}) => {
    console.log('setData :', content, number, size, totalElements);

    this.setState({
      content: content, // 현재 페이지 데이터
      pagination: {
        total: totalElements, // 전체 데이터 수
        pageSize: size, // 페이지당 데이터 카운트
        current: number + 1, // 현재 페이지
      }
    });
  };

  getData = (params = {}) => {
    this.handleLoading(true);

    api
        .getMyKeywords(params)
        .then((data) => {
          console.log('response', data);

          if (data.totalElements) {
            this.setData(data);
          } else {
            alert(data);
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

    this.getData({
      size: this.state.pagination.pageSize,
    });
  };

  handleTableChange = (pagination) => {
    const pager = {...this.state.pagination};
    pager.current = pagination.current;

    this.setState({
      pagination: pager,
    });

    this.getData({
      size: pagination.pageSize,
      page: pagination.current - 1,
    });
  };

  render(){
    const {handleSearch, handleTableChange} = this;
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
                    pagination={pagination}
                    loading={loading}
                    onChange={handleTableChange}
                />
            ) : (
                <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} description="데이터가 없습니다."/>
            )}
          </section>
        </React.Fragment>
    );
  }
}

export default MyKeywordContainer;