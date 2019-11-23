import React, { Component } from 'react';
import { Empty, Input, Table } from 'antd';
const { Search } = Input;
const data = [];
const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    width: 150,
  },
  {
    title: 'Age',
    dataIndex: 'age',
    width: 150,
  },
  {
    title: 'Address',
    dataIndex: 'address',
  },
];

for (let i = 0; i < 100; i++) {
  data.push({
    key: i,
    name: `Edward King ${i}`,
    age: 32,
    address: `London, Park Lane no. ${i}`,
  });
}

class SearchContainer extends Component {
  state = {
    list: data,
    // list: null,
  };

  getData = (data) => {
    console.log('getData :', data);
  };

  setData = () => {
    console.log('setData :', data);
  };

  handleSearch = (data) => {
    console.log('handleSearch :', data);
  };

  render() {
    const { handleSearch } = this;
    const { list } = this.state;

    return (
      <React.Fragment>
        <section id="search-input-wrap">
          <Search placeholder="도서 검색" enterButton="검색" size="large" onSearch={handleSearch} />
        </section>
        <section id="search-result-wrap">
          {list ? (
            <Table columns={columns} dataSource={data} pagination={{ pageSize: 20 }} />
          ) : (
            <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} description="검색어를 입력해보세요" />
          )}
        </section>
      </React.Fragment>
    );
  }
}

export default SearchContainer;
