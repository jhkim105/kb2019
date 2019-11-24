import LayoutTemplate from '../components/LayoutTemplate';
import MyKeywordContainer from "../containers/MyKeywordContainer";

const MyKeyword = ({authToken}) => {
  return (
    <LayoutTemplate name="my-keywords" sidebar>
      <MyKeywordContainer authToken={authToken}/>
    </LayoutTemplate>
  );
};


export default MyKeyword;