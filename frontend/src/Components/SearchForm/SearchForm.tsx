import React, { useState } from "react";
import { Dropdown, DropdownButton } from "react-bootstrap";
import { FilterhIcon, SearchFormContainer, SearchIcon, StyledForm, SearchButton, StyledInput } from "./styles";

interface SearchFormProps {
  onSearch: (query: string) => void;
}

const SearchForm: React.FC<SearchFormProps> = ({ onSearch }) => {
  const [query, setQuery] = useState('');

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setQuery(value);
    onSearch(value);
  };

  return (
    <SearchFormContainer>
      <StyledForm>
        <SearchButton variant="secondary">
          <SearchIcon />
        </SearchButton>
        <StyledInput
          type="search"
          placeholder="Buscar playlists..."
          aria-label="Search"
          value={query}
          onChange={handleInputChange}
        />
        <DropdownButton
          className="mb-2"
          data-bs-theme="dark"
          variant="secondary"
          title={<FilterhIcon />}
          onSelect={() => { alert("Sem Função"); }}
        >
          <Dropdown.Item eventKey="newest">Mais Antigo</Dropdown.Item>
          <Dropdown.Item eventKey="older">Mais Recente</Dropdown.Item>
        </DropdownButton>
      </StyledForm>
    </SearchFormContainer>
  );
};

export default SearchForm;