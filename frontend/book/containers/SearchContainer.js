import React, {Component} from 'react';
import {Empty, Input, Table} from 'antd';
import api from '../modules/api';
import BookDetailModal from '../components/BookDetailModal';

const { Search } = Input;

class SearchContainer extends Component {
  constructor(props){
    super(props);

    this.columns = [
      {
        title: '책이름',
        dataIndex: 'title',
        render: (text, record, index) => (
            <a
                onClick={() => {
                  console.log('onClick', index, this.state.content[index]);
                  const detailData = this.state.content[index];
                  this.showModal(detailData);
                }}
            >
              {text}
            </a>
        ),
      },
      {
        title: '저자',
        dataIndex: 'author',
        width: 200,
      },
      {
        title: '출판사',
        dataIndex: 'publisher',
        width: 200,
      },
      {
        title: '가격',
        dataIndex: 'price',
        width: 150,
      },
    ];

    this.state = {
      keyword: null,
      content: [],
      loading: false,
      pagination: {pageSize: 20},
      modalVisibility: false,
      modalData: null,
    };
  }

  setData = ({content, number, size, totalElements}) => {
    console.log('setData :', content, number, size, totalElements);

    this.setState({
      content: content, // 현재 페이지 데이터
      pagination: {
        total: totalElements, // 전체 데이터 수
        pageSize: size, // 페이지당 데이터 카운트
        current: number + 1, // 현재 페이지
      },
    });
  };

  getData = (params = {}) => {
    this.handleLoading(true);

    api
        .searchBook(params)
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

  handleSearch = (keyword) => {
    if (!keyword) {
      alert('검색어를 입력하세요');
      return false;
    }

    this.setState({
      keyword: keyword,
    });

    this.getData({
      query: keyword,
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
      query: this.state.keyword,
      size: pagination.pageSize,
      page: pagination.current - 1,
    });
  };

  showModal = (data) => {
    this.setState({
      modalVisibility: true,
      modalData: data,
    });
  };

  hideModal = () => {
    this.setState({
      modalVisibility: false,
      modalData: null,
    });
  };

  render(){
    const {columns, handleSearch, handleTableChange, hideModal} = this;
    const {content, loading, pagination, modalVisibility, modalData} = this.state;

    return (
        <React.Fragment>
          <section id="search-input-wrap">
            <Search placeholder="도서 검색" enterButton="검색" size="large" onSearch={handleSearch}/>
          </section>
          <section id="search-result-wrap">
            {content.length ? (
                <Table
                    columns={columns}
                    dataSource={content}
                    pagination={pagination}
                    loading={loading}
                    onChange={handleTableChange}
                />
            ) : (
                <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} description="검색어를 입력해보세요"/>
            )}
          </section>

          <BookDetailModal visible={modalVisibility} data={modalData} handleConfirm={hideModal}/>
        </React.Fragment>
    );
  }
}

export default SearchContainer;
