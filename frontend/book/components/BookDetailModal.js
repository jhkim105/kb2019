import React from 'react';
import {Button, Col, Modal, Row} from 'antd';
import './BookDetailModal.scss';

const BookDetailModal = ({ visible, data, handleConfirm }) => {
  if (!data) {
    return false;
  }

  // 상세 정보에는 제목, 도서 썸네일, 소개, ISBN, 저자, 출판사, 출판일, 정가, 판매가가 포함되어야 합니다.
  const { title, author, description, isbn, price, publishedDate, publisher, salePrice, thumbnail } = data;

  return (
    <Modal
      width={800}
      visible={visible}
      onCancel={handleConfirm}
      title={title}
      footer={[
        <Button key="submit" type="primary" onClick={handleConfirm}>
          확인
        </Button>,
      ]}
    >
      <Row gutter={[16, 16]}>
        <Col span={6}>
          <img src={thumbnail} alt={title} />
        </Col>
        <Col span={16}>
          <p>
            <strong>제목</strong>
            <span>{title}</span>
          </p>
          <p>
            <strong>저자</strong>
            <span>{author}</span>
          </p>
          <p>
            <strong>소개</strong>
            <span>{description}</span>
          </p>
          <p>
            <strong>출판사</strong>
            <span>{publisher}</span>
          </p>
          <p>
            <strong>출판일</strong>
            <span>{publishedDate}</span>
          </p>
          <p>
            <strong>정가</strong>
            <span>{price}</span>
          </p>
          <p>
            <strong>판매가</strong>
            <span>{salePrice}</span>
          </p>
          <p>
            <strong>ISBN</strong>
            <span>{isbn}</span>
          </p>
        </Col>
      </Row>
    </Modal>
  );
};

export default BookDetailModal;
