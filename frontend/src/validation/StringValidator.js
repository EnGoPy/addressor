export const allowedNamesAreValid = (allowedCities) => {
    // We accept hyphens and commas (which we slice by)
    let forbiddenSigns = /[!@#$%^&*()_+=\[\]{};':"\\|.<>\/?]+/;
    return !forbiddenSigns.test(allowedCities);
}