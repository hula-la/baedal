import React, { useCallback, useEffect, useState } from "react";
import SearchBar from "./SearchBar";
import SearchButton from "./SearchButton";
import SearchHistory from "./SearchHistory";

const Search = () => {
  const [keyword, setKeyword] = useState("");
  const [keywords, setKeywords] = useState(
    JSON.parse(localStorage.getItem("keywords") || "[]")
  );

  const handleKeyword = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      setKeyword(e.target.value);
    },
    []
  );

  const handleAddKeyword = useCallback((text: string) => {
    const newKeyword = {
      id: Date.now(),
      text: text,
    };
    setKeywords([newKeyword, ...keywords]);
  }, []);

  const handleClick = useCallback(() => {
    handleAddKeyword(keyword);
    console.log("여기 검색 api 자리");
  }, []);

  useEffect(() => {
    localStorage.setItem("keywords", JSON.stringify(keywords));
  }, [keywords]);

  return (
    <div>
      <div>
        <SearchBar type="text" value={keyword} onChange={handleKeyword} />
        <SearchButton onClick={handleClick} />
      </div>
      <SearchHistory keywords={keywords} />
    </div>
  );
};

export default Search;
