import LayoutTemplate from '../components/LayoutTemplate';
import LoginFormContainer from '../containers/LoginFormContainer';

const Index = (props) => {
  console.log('index', props);

  return (
    <LayoutTemplate name="login">
      <LoginFormContainer />
    </LayoutTemplate>
  );
};

export default Index;
