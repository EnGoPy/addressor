
#Addressor


##Description

Addressor is a application which might make your work with komoot/photon easier.
With Addressor you can have an easily maintained reverse geocoding features and autosearch tool for free.
<br>Main functionality and benefit is parsing photons JSON structure of map object into human readable. Depends on
parsing street, building, other amenity place, it always tries to parse raw photon response into values you can
directly use in your application. This parsing is done when reaching two main addressor functionalities:
- reverse geocoding (find map points with its geo cords)
- autosearch (search proposals by object name part)


##What makes Adrressor special comparing to plain photon?
With Addressor you can very easy manipulate and filter wanted OSM tagged map objects to be included
or excluded from your results. Addressor has simple UI part written in React JS which helps you add or remove
wanted OSM tags in a nice and easily maintained way. Regarding autosearch feature? Addressor send you back autosearch response with a human 
readable form including object name, address, its coords and extra metadata to be used direct in your application.
<br>
Beside parsing photons response into human friendly ones, with addressor you can manipulate over included or excluded
OSM objects to be returned by reversed geocoding results or autosearch in a runtime with easy to use UI. By specifying 
boundary box you can get map results within interested area. Addressor takes care about generating URL to photon, 
get responses, filtering and parsing response into easy to use ones.

##Installation

##Default settings
####Global settings
- localhost - default photon address
- 2322 - default photon port to connect
####Reversed geocoding
- TRUE - filtering photon results 
-----
####Autosearch
- TRUE - filtering photon results
- non limited count of photon response
- 8 - maximum results returned from addressor API
- FALSE -boundary box filtering 
-----
##Customize your settings
###Examples of usage: 
- By using OSM map you can easil


