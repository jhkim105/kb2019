import LayoutTemplate from '../components/LayoutTemplate';
import SearchContainer from '../containers/SearchContainer';

const Search = ({ authToken }) => {
  return (
    <LayoutTemplate name="search" sidebar>
      <SearchContainer authToken={authToken} />
    </LayoutTemplate>
  );
};

export default Search;
