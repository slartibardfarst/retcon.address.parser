package retcon.parser;

import retcon.parser.patterns.PatternMatch;
import retcon.parser.predicate.PredicateMatch;

/**
 * Created by awatkins on 3/21/2016.
 */
public class Geo {
    public enum GeoType {
        ADDRESS,
        STREET,
        NEIGHBORHOOD,
        CITY,
        COUNTY,
        STATE,
        ZIP,
        UNKNOWN
    }

    public GeoType geo_type;
    public String address_line;
    public String street_no;
    public String street_direction;
    public String street;
    public String street_suffix;
    public String street_post_direction;
    public String unit;
    public String neighborhood;
    public String city;
    public String county;
    public String state;
    public String zip;

    public Geo(PatternMatch match) {
        geo_type = parseGeoType(match);

        for (PredicateMatch currPredicateMatch : match.getMatches()) {
            switch (currPredicateMatch.Name) {
                case "address_line":
                    address_line = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "street_no":
                    street_no = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "street_direction":
                    street_direction = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "street":
                    street = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "street_suffix":
                    street_suffix = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "street_post_direction":
                    street_post_direction = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "unit":
                    unit = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "neighborhood":
                    neighborhood = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "city":
                    city = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "county":
                    county = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "state":
                    state = String.join(" ", currPredicateMatch.Tokens);
                    break;

                case "zip":
                    zip = String.join(" ", currPredicateMatch.Tokens);
                    break;

            }
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Geo))
            return false;

        if (obj == this)
            return true;

        Geo o = (Geo) obj;
        boolean same = true;
        same = same && (geo_type == o.geo_type);
        //same = same && (address_line .equals( o.address_line));
        same = same && ((null == street_no) || (street_no.equalsIgnoreCase(o.street_no)));
        same = same && ((null == street_direction) || (street_direction.equalsIgnoreCase(o.street_direction)));
        same = same && ((null == street) || (street.equalsIgnoreCase(o.street)));
        same = same && ((null == street_suffix) || (street_suffix.equalsIgnoreCase(o.street_suffix)));
        same = same && ((null == street_post_direction) || (street_post_direction.equalsIgnoreCase(o.street_post_direction)));
        same = same && ((null == unit) || (unit.equalsIgnoreCase(o.unit)));
        same = same && ((null == city) || (city.equalsIgnoreCase(o.city)));
        same = same && ((null == neighborhood) || (neighborhood.equalsIgnoreCase(o.neighborhood)));
        same = same && ((null == state) || (state.equalsIgnoreCase(o.state)));
        same = same && ((null == county) || (county.equalsIgnoreCase(o.county)));
        same = same && ((null == zip) || (zip.equalsIgnoreCase(o.zip)));

        return same;
    }

    private GeoType parseGeoType(PatternMatch match)
    {
        switch(match.Pattern.getName().toLowerCase().trim())
        {
            case "address":
                return GeoType.ADDRESS;

            case "street_geo":
            case "street":
                return GeoType.STREET;

            case "city_geo":
            case "city":
                return GeoType.CITY;

            case "county_geo":
            case "county":
                return GeoType.COUNTY;

            case "state_geo":
            case "state":
                return GeoType.STATE;

            case "zip_geo":
            case "zip":
                return GeoType.ZIP;

            default:
                return GeoType.UNKNOWN;
        }
    }
}

