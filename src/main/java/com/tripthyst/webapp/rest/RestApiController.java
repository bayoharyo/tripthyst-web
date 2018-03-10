package com.tripthyst.webapp.rest;

import com.tripthyst.webapp.model.AgentModel;
import com.tripthyst.webapp.model.KeywordModel;
import com.tripthyst.webapp.model.PackageModel;
import com.tripthyst.webapp.model.RestModelWrapper;
import com.tripthyst.webapp.service.AgentService;
import com.tripthyst.webapp.service.KeywordService;
import com.tripthyst.webapp.service.PackageService;
import com.tripthyst.webapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    PackageService packageService;

    @Autowired
    AgentService agentService;

    @Autowired
    KeywordService keywordService;
    
    @Autowired
    UserService userService;

    private static String UPLOADED_FOLDER =  "C:\\Users\\Lenovo\\Documents\\Tripthyst\\github-repo\\src\\main\\resources\\static\\upload\\";

    // ---------- Package ---------- //

    @RequestMapping(value = "/getAllPackages", method = RequestMethod.GET)
    public RestModelWrapper<List<PackageModel>> getAllPackage() {

        RestModelWrapper<List<PackageModel>> result;

        List<PackageModel> allPackage = packageService.getAllPackage();

        if (allPackage.size() == 0) {
            result = new RestModelWrapper<>();
        } else {
            //dummy
            for (PackageModel packageModel : allPackage) {
                //dummy
                List<String> imageUrls = new ArrayList<>();
                packageModel.setImgUrl(imageUrls);
                if(packageModel.getId() == 1) {
                    packageModel.getImgUrl().add("https://4.bp.blogspot.com/-MpuvEZLOBFk/VyBDgWsYInI/AAAAAAAADoc/NEegGCh0aL8i8YAK1cT7x_U8Re6hUHVtwCLcB/s640/banyuwangi%2Bgunung%2Bijen.jpg");
                    packageModel.getImgUrl().add("http://fastboatmurahkegili.com/wp-content/uploads/teluk-hijau-di-banyuwangi.jpg");
                    packageModel.getImgUrl().add("http://static.asiawebdirect.com/m/bangkok/portals/bali-indonesia-com/homepage/magazine/banyuwangi/allParagraphs/0/ListingContainer/00/image/banyuwangi-nature.jpg");
                } else {
                    packageModel.getImgUrl().add("https://www.bali.com/media/image/663/best-resorts-bali.jpg");
                    packageModel.getImgUrl().add("http://static.asiawebdirect.com/m/bangkok/portals/bali-indonesia-com/homepage/pagePropertiesOgImage/bali.jpg.jpg");
                    packageModel.getImgUrl().add("http://static.asiawebdirect.com/m/bangkok/portals/bali-indonesia-com/shared/teasersL/hotels/top10-beach-resort[2]/teaserMultiLarge/imageHilight/10best-beach-hotel-bali.jpg");
                }
            }
            result = new RestModelWrapper<>(allPackage);
        }

        return result;
    }

    @RequestMapping(value = "/getPackagesByKeyword/{word}", method = RequestMethod.GET)
    public RestModelWrapper<Map<String, Object>> getPackagesByDest(@PathVariable("word") String word) {

        RestModelWrapper<Map<String, Object>> result;

        List<PackageModel> packages = packageService.getPackagesByKeyword(word);

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("keyword", word);
        resultMap.put("packages", packages);

        result = new RestModelWrapper<>(resultMap);

        return result;
    }

    @RequestMapping(value = "/getPackageDetails/{id}", method = RequestMethod.GET)
    public RestModelWrapper<PackageModel> getPackageDetails(@PathVariable("id") long id) {

        RestModelWrapper<PackageModel> result;

        PackageModel packageModel = packageService.getPackageById(id);

        if (packageModel == null) {
            result = new RestModelWrapper<>();
        } else {
            //notdummy
            /*packageModel.setImgUrl("/api/getImage/" + imageName.get(0));*/

            //dummy
            List<String> imageUrls = new ArrayList<>();
            packageModel.setImgUrl(imageUrls);
            if(id == 1) {
                packageModel.getImgUrl().add("https://4.bp.blogspot.com/-MpuvEZLOBFk/VyBDgWsYInI/AAAAAAAADoc/NEegGCh0aL8i8YAK1cT7x_U8Re6hUHVtwCLcB/s640/banyuwangi%2Bgunung%2Bijen.jpg");
                packageModel.getImgUrl().add("http://fastboatmurahkegili.com/wp-content/uploads/teluk-hijau-di-banyuwangi.jpg");
                packageModel.getImgUrl().add("http://static.asiawebdirect.com/m/bangkok/portals/bali-indonesia-com/homepage/magazine/banyuwangi/allParagraphs/0/ListingContainer/00/image/banyuwangi-nature.jpg");
            } else {
                packageModel.getImgUrl().add("https://www.bali.com/media/image/663/best-resorts-bali.jpg");
                packageModel.getImgUrl().add("http://static.asiawebdirect.com/m/bangkok/portals/bali-indonesia-com/homepage/pagePropertiesOgImage/bali.jpg.jpg");
                packageModel.getImgUrl().add("http://static.asiawebdirect.com/m/bangkok/portals/bali-indonesia-com/shared/teasersL/hotels/top10-beach-resort[2]/teaserMultiLarge/imageHilight/10best-beach-hotel-bali.jpg");
            }

            result = new RestModelWrapper<>(packageModel);
        }

        return result;
    }

    // ---------- Agent ----------  //

    @RequestMapping(value = "/getAgent/{id}", method = RequestMethod.GET)
    public RestModelWrapper<AgentModel> getAgent(@PathVariable("id") long id) {

        RestModelWrapper<AgentModel> result;

        AgentModel agent = agentService.getAgent(id);

        //dummy

        agent.setImageUrl("https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAnZAAAAJGNmY2Y0OTU2LTBiNWYtNDFiMi1hMWExLTJjMzQyNDBlZTZiNg.jpg");
        System.out.print(agent);
        if (agent == null) {
            result = new RestModelWrapper<>();
        } else {
            result = new RestModelWrapper<>(agent);
        }

        return result;
    }

    // ---------- Keyword ---------- //

    @RequestMapping(value = "/getKeywordSuggestion/{word}", method = RequestMethod.GET)
    public RestModelWrapper<List<KeywordModel>> getKeywordSuggestion(@PathVariable("word") String word) {

        RestModelWrapper<List<KeywordModel>> result;

        List<KeywordModel> suggestions = keywordService.getKeywordSuggestion(word);

        if (suggestions.size() == 0) {
            result = new RestModelWrapper<>();
        } else {
            result = new RestModelWrapper<>(suggestions);
        }

        return result;

    }

    @RequestMapping(value = "/getMostVisitedKeywords", method = RequestMethod.GET)
    public RestModelWrapper<List<KeywordModel>> getMostVisited() {

        RestModelWrapper<List<KeywordModel>> result;

        List<KeywordModel> mostVisitedKeywords = keywordService.getMostVisited();

        result = new RestModelWrapper<>(mostVisitedKeywords);

        return result;
    }

    @RequestMapping(value = "/getImage/{imageName:.*}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public @ResponseBody byte[] getPackageImage(@PathVariable("imageName") String imageName) throws IOException {
        System.out.print(imageName);
        Path path = Paths.get(UPLOADED_FOLDER+imageName);
        ByteArrayResource image = new ByteArrayResource(Files.readAllBytes(path));
        return image.getByteArray();
    }

    // ---------- POST ---------- //
    @RequestMapping(value = "/createPackage", method = RequestMethod.POST)
    public Map<String, String> createPackage(@RequestParam("file") MultipartFile[] files,
                                           @RequestParam("agent-id") int id,
                                           @RequestParam("destination-name") String destinationName,
                                           @RequestParam("island") String island,
                                           @RequestParam("price") double price,
                                           @RequestParam("schedule") List<String> schedules,
                                           @RequestParam("facilities") String facilities,
                                           @RequestParam("place") List<String> places,
                                           @RequestParam("day") List<Integer> days,
                                           @RequestParam("sched") List<String> scheds,
                                           @RequestParam("desc") List<String> descs) {

        Map<String, String> result = new HashMap<>();

        //insert into travel_package table, package_schedule table, package_facility table, package itinerary table
        packageService.postPackage(id, destinationName, island, price, schedules, facilities, places, days, scheds, descs);

        long packageId = packageService.getLatestId();

        //insert into keyword
        keywordService.postKeyword(destinationName, island, places);


        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                result.put("status", "401");
                result.put("msg", "Please select you image !");
                return result;
            }

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + packageId + "_" + file.getOriginalFilename());
                Files.write(path, bytes);
                packageService.insertImage(packageId, packageId + "_" + file.getOriginalFilename());
            } catch (IOException e) {
                result.put("status", "401");
                result.put("msg", "Bad Request");
                return result;
            }
        }

        result.put("status", "200");
        result.put("msg", "You have successfully create a travel package");

        return result;
    }
    
}
