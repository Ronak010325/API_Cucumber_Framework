package testDataBuilder;

import POJOClasses.Location;
import POJOClasses.Payload;

import java.util.Arrays;
import java.util.List;

public class testDataBuild {
    public Payload getAddPlaceData(String name, String address, String language) {
//        This is Serialization
        Payload payloadBody = new Payload();
        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        payloadBody.setLocation(loc);
        payloadBody.setAccuracy(50);
        payloadBody.setName(name);
        payloadBody.setPhone_number("(+91) 983 893 3937");
        payloadBody.setAddress(address);
        List<String> types = Arrays.asList("shoe park", "shop");
        payloadBody.setTypes(types);
        payloadBody.setWebsite("http://google.com");
        payloadBody.setLanguage(language);

        return payloadBody;
    }
}
