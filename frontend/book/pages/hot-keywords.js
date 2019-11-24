import LayoutTemplate from '../components/LayoutTemplate';
import HotKeywordContainer from "../containers/HotKeywordContainer";

const HotKeyword = ({authToken}) => {
  return (
    <LayoutTemplate name="hot-keywords" sidebar>
      <HotKeywordContainer authToken={authToken}/>
    </LayoutTemplate>
  );
};

export default HotKeyword;