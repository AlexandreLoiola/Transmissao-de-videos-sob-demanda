import React from "react";
import { Dropdown, DropdownButton } from "react-bootstrap";
import { FilterhIcon, SearchFormContainer, SearchIcon, StyledForm, SearchButton, StyledInput } from "./styles";

const SearchForm: React.FC  = () => {
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
        />
        <DropdownButton
          className="mb-2"
          data-bs-theme="dark"
          variant="secondary"
          title={<FilterhIcon />}
          onSelect={() => {alert("Sem Função")}}
        >
          <Dropdown.Item eventKey="newest">Mais Antigo</Dropdown.Item>
          <Dropdown.Item eventKey="older">Mais Recente</Dropdown.Item>
        </DropdownButton>
      </StyledForm>
    </SearchFormContainer>
  );
};

export default SearchForm;