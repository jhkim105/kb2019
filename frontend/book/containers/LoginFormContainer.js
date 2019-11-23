import Link from 'next/link';
import { Form, Icon, Input, Button, Checkbox } from 'antd';

class LoginFormContainer extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      } else {
        console.log('Received values of form: ', err);
      }
    });
  };

  render() {
    const { getFieldDecorator } = this.props.form;

    return (
      <Form onSubmit={this.handleSubmit} className="login-form">
        <Form.Item>
          {getFieldDecorator('username', {
            rules: [{ required: true, message: 'Please input your username!' }],
          })(<Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />)}
        </Form.Item>
        <Form.Item>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Please input your Password!' }],
          })(
            <Input
              prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
              type="password"
              placeholder="Password"
            />
          )}
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" className="login-form-button">
            로그인
          </Button>
          <Link href="/signup">
            <a title="회원가입">회원가입</a>
          </Link>
        </Form.Item>
        <Form.Item>
          <Link href="/search">
            <a title="검색페이지">검색페이지</a>
          </Link>
        </Form.Item>
      </Form>
    );
  }
}

export default Form.create({ name: 'normal_login' })(LoginFormContainer);
