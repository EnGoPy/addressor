

export const latitudeCordIsValid = (cord) => {
    let floatCord = Number.parseFloat(cord);
    return (floatCord <= 180 && floatCord >= 0 && isValidCord(cord));
}

export const longitudeCordIsValid = (cord) => {
    let floatCord = Number.parseFloat(cord);
    return (floatCord <= 90 && floatCord >= 0 && isValidCord(cord));
}
const isValidCord = (val) => {
    return isFloat(val) || isInt(val);
}

const isFloat = (val) => {
    var floatRegex = /^-?\d+(?:[.,]\d*?)?$/;
    if (!floatRegex.test(val))
        return false;

    val = parseFloat(val);
    if (isNaN(val))
        return false;
    return true;
}

const isInt = (val) => {
    var intRegex = /^-?\d+$/;
    if (!intRegex.test(val))
        return false;

    var intVal = parseInt(val, 10);
    return parseFloat(val) == intVal && !isNaN(intVal);
}