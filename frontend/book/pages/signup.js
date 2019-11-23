// This is the Link API
import Link from 'next/link';
import LayoutTemplate from '../components/LayoutTemplate';
import SigninFormContainer from '../containers/SigninFormContainer';

const Signup = () => (
  <LayoutTemplate name="signup">
    <SigninFormContainer />
  </LayoutTemplate>
);

export default Signup;
