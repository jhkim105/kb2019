import LayoutTemplate from '../components/LayoutTemplate';
import LoginFormContainer from '../containers/LoginFormContainer';

const Index = (props) => {
  console.log('Index :', props);

  return (
    <LayoutTemplate name="login">
      <LoginFormContainer handleLogin={props.handleLogin} />
    </LayoutTemplate>
  );
};

export default Index;
