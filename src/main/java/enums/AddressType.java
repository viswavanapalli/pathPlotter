package enums;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.google.maps.internal.StringJoin.UrlValue;

public enum AddressType implements UrlValue {
    STREET_ADDRESS("street_address"),
    STREET_NUMBER("street_number"),
    FLOOR("floor"),
    ROOM("room"),
    POST_BOX("post_box"),
    ROUTE("route"),
    INTERSECTION("intersection"),
    CONTINENT("continent"),
    POLITICAL("political"),
    COUNTRY("country"),
    ADMINISTRATIVE_AREA_LEVEL_1("administrative_area_level_1"),
    ADMINISTRATIVE_AREA_LEVEL_2("administrative_area_level_2"),
    ADMINISTRATIVE_AREA_LEVEL_3("administrative_area_level_3"),
    ADMINISTRATIVE_AREA_LEVEL_4("administrative_area_level_4"),
    ADMINISTRATIVE_AREA_LEVEL_5("administrative_area_level_5"),
    COLLOQUIAL_AREA("colloquial_area"),
    LOCALITY("locality"),
    WARD("ward"),
    SUBLOCALITY("sublocality"),
    SUBLOCALITY_LEVEL_1("sublocality_level_1"),
    SUBLOCALITY_LEVEL_2("sublocality_level_2"),
    SUBLOCALITY_LEVEL_3("sublocality_level_3"),
    SUBLOCALITY_LEVEL_4("sublocality_level_4"),
    SUBLOCALITY_LEVEL_5("sublocality_level_5"),
    NEIGHBORHOOD("neighborhood"),
    PREMISE("premise"),
    SUBPREMISE("subpremise"),
    POSTAL_CODE("postal_code"),
    POSTAL_CODE_PREFIX("postal_code_prefix"),
    POSTAL_CODE_SUFFIX("postal_code_suffix"),
    NATURAL_FEATURE("natural_feature"),
    AIRPORT("airport"),
    UNIVERSITY("university"),
    PARK("park"),
    MUSEUM("museum"),
    POINT_OF_INTEREST("point_of_interest"),
    ESTABLISHMENT("establishment"),
    BUS_STATION("bus_station"),
    TRAIN_STATION("train_station"),
    SUBWAY_STATION("subway_station"),
    TRANSIT_STATION("transit_station"),
    LIGHT_RAIL_STATION("light_rail_station"),
    CHURCH("church"),
    PRIMARY_SCHOOL("primary_school"),
    FINANCE("finance"),
    POST_OFFICE("post_office"),
    PLACE_OF_WORSHIP("place_of_worship"),
    POSTAL_TOWN("postal_town"),
    SYNAGOGUE("synagogue"),
    FOOD("food"),
    GROCERY_OR_SUPERMARKET("grocery_or_supermarket"),
    STORE("store"),
    LAWYER("lawyer"),
    HEALTH("health"),
    INSURANCE_AGENCY("insurance_agency"),
    GAS_STATION("gas_station"),
    CAR_DEALER("car_dealer"),
    CAR_REPAIR("car_repair"),
    MEAL_TAKEAWAY("meal_takeaway"),
    FURNITURE_STORE("furniture_store"),
    HOME_GOODS_STORE("home_goods_store"),
    SHOPPING_MALL("shopping_mall"),
    GYM("gym"),
    ACCOUNTING("accounting"),
    MOVING_COMPANY("moving_company"),
    LODGING("lodging"),
    STORAGE("storage"),
    CASINO("casino"),
    PARKING("parking"),
    STADIUM("stadium"),
    TRAVEL_AGENCY("travel_agency"),
    NIGHT_CLUB("night_club"),
    BEAUTY_SALON("beauty_salon"),
    HAIR_CARE("hair_care"),
    SPA("spa"),
    SHOE_STORE("shoe_store"),
    BAKERY("bakery"),
    PHARMACY("pharmacy"),
    SCHOOL("school"),
    BOOK_STORE("book_store"),
    DEPARTMENT_STORE("department_store"),
    RESTAURANT("restaurant"),
    REAL_ESTATE_AGENCY("real_estate_agency"),
    BAR("bar"),
    DOCTOR("doctor"),
    HOSPITAL("hospital"),
    FIRE_STATION("fire_station"),
    SUPERMARKET("supermarket"),
    CITY_HALL("city_hall"),
    LOCAL_GOVERNMENT_OFFICE("local_government_office"),
    ATM("atm"),
    BANK("bank"),
    LIBRARY("library"),
    CAR_WASH("car_wash"),
    HARDWARE_STORE("hardware_store"),
    AMUSEMENT_PARK("amusement_park"),
    AQUARIUM("aquarium"),
    ART_GALLERY("art_gallery"),
    BICYCLE_STORE("bicycle_store"),
    BOWLING_ALLEY("bowling_alley"),
    CAFE("cafe"),
    CAMPGROUND("campground"),
    CAR_RENTAL("car_rental"),
    CEMETERY("cemetery"),
    CLOTHING_STORE("clothing_store"),
    CONVENIENCE_STORE("convenience_store"),
    COURTHOUSE("courthouse"),
    DENTIST("dentist"),
    ELECTRICIAN("electrician"),
    ELECTRONICS_STORE("electronics_store"),
    EMBASSY("embassy"),
    FLORIST("florist"),
    FUNERAL_HOME("funeral_home"),
    GENERAL_CONTRACTOR("general_contractor"),
    HINDU_TEMPLE("hindu_temple"),
    JEWELRY_STORE("jewelry_store"),
    LAUNDRY("laundry"),
    LIQUOR_STORE("liquor_store"),
    LOCKSMITH("locksmith"),
    MEAL_DELIVERY("meal_delivery"),
    MOSQUE("mosque"),
    MOVIE_RENTAL("movie_rental"),
    MOVIE_THEATER("movie_theater"),
    PAINTER("painter"),
    PET_STORE("pet_store"),
    PHYSIOTHERAPIST("physiotherapist"),
    PLUMBER("plumber"),
    POLICE("police"),
    ROOFING_CONTRACTOR("roofing_contractor"),
    RV_PARK("rv_park"),
    TAXI_STAND("taxi_stand"),
    VETERINARY_CARE("veterinary_care"),
    ZOO("zoo"),
    ARCHIPELAGO("archipelago"),
    TOURIST_ATTRACTION("tourist_attraction"),
    UNKNOWN("unknown");

    private final String addressType;

    private AddressType(String addressType) {
        this.addressType = addressType;
    }

    public String toString() {
        return this.addressType;
    }

    public String toCanonicalLiteral() {
        return this.toString();
    }

    public String toUrlValue() {
        if (this == UNKNOWN) {
            throw new UnsupportedOperationException("Shouldn't use AddressType.UNKNOWN in a request.");
        } else {
            return this.addressType;
        }
    }
}
