const AutoSuggestionObject = (props) => {
    console.log(props)
    const {longitude, latitude} = props.result;
    const {title, address} = props.result.description;

    return (
        <>
            <tr>
                <td >{title}</td>
                <td >{address}</td>
                <td>{longitude}</td>
                <td>{latitude}</td>
            </tr>
        </>
    )

}


export default AutoSuggestionObject;