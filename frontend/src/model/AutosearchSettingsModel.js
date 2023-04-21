class AutosearchSettingsModel{
    enableFiltering;
    allowedCities;
    allowedCountryCodes;
    filteringLimit;
    photonApiLimit;
    useBoundary;
    eastBoundary;
    northBoundary;
    westBoundary;
    southBoundary;
    constructor(enableFiltering,
                allowedCities,
                allowedCountryCodes,
                filteringLimit,
                photonApiLimit,
                useBoundary,
                eastBoundary,
                northBoundary,
                westBoundary,
                southBoundary) {
        this.enableFiltering = enableFiltering;
        this.allowedCities = allowedCities;
        this.allowedCountryCodes = allowedCountryCodes;
        this.filteringLimit = filteringLimit;
        this.photonApiLimit = photonApiLimit;
        this.useBoundary = useBoundary;
        this.eastBoundary = eastBoundary;
        this.northBoundary = northBoundary;
        this.westBoundary = westBoundary;
        this.southBoundary = southBoundary;
    }
}
export  default AutosearchSettingsModel;