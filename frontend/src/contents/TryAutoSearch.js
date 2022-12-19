import React, {useEffect, useState} from 'react';
import AutoSuggestionObject from "./AutoSuggestionObject";

const TryAutoSearch = () => {
    const [searchWord, setSearchWord] = useState("");
    const [suggestions, setSuggestions] = useState();
    const [suggestionsLoaded, setSuggestionsLoaded] = useState(false);

    const onInput = (e) => {
        let inputValue = e.target.value;
        setSearchWord(prevState => inputValue);
    }


    useEffect(() => {
        if (searchWord.length >= 2) {
            const controller = new AbortController();
            const signal = controller.signal;
            fetch(`http://localhost:2300/api/v1/autoFill/${searchWord}`, {signal})
                .then(res => res.json()
                )
                .then(data => {
                    let autoFilledProposals = data.autoFilledProposals;
                    if (autoFilledProposals.length !== 0) {
                        console.log("Im in")
                        setSuggestions(prev => {
                            return autoFilledProposals.map(suggestion => {
                                return <AutoSuggestionObject result={suggestion}/>
                            })
                        })
                        setSuggestionsLoaded(prev => true);
                    } else {
                        setSuggestionsLoaded(prev => false);
                    }
                })
                .catch(() => {
                    console.log("Problem occured")
                    setSuggestionsLoaded(prev => false)
                })
        }
    }, [searchWord])


    return (
        <>
            <div className="border  border-3 my-2">
                <h3>Try autosearch response</h3>
                <div className="pb-3">
                    <label htmlFor="searchValue">Type autosearchValue</label>
                    <input name="searchValue" size="30" onChange={(event) => onInput(event)} value={searchWord}/>
                </div>
                <table className="table table-hover">
                    <thead className="">
                    {/*<th scope="col">#</th>*/}
                    <th scope="col">Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">longitude</th>
                    <th scope="col">latitude</th>
                    </thead>
                    <tbody>
                    {suggestions}
                    </tbody>
                </table>
                <h5>
                    {!suggestionsLoaded ? `No autosearch results found` : ''}
                </h5>
            </div>
        </>
    )
}

export default TryAutoSearch;
