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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static String UPLOADED_FOLDER =  "C:\\Users\\Lenovo\\Documents\\Tripthyst\\github-repo\\src\\asset\\image\\";

    // ---------- Package ---------- //

    @RequestMapping(value = "/getAllPackages", method = RequestMethod.GET)
    public RestModelWrapper<List<PackageModel>> getAllPackage() {

        RestModelWrapper<List<PackageModel>> result;

        List<PackageModel> allPackage = packageService.getAllPackage();

        if (allPackage.size() == 0) {
            result = new RestModelWrapper<>();
        } else {
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
            packageModel.setImgUrl("/api/getImage/" + id);
            result = new RestModelWrapper<>(packageModel);
        }

        return result;
    }

    // ---------- Agent ----------  //

    @RequestMapping(value = "/getAgent/{id}", method = RequestMethod.GET)
    public RestModelWrapper<AgentModel> getAgent(@PathVariable("id") int id) {

        RestModelWrapper<AgentModel> result;

        AgentModel agent = agentService.getAgent(id);

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

    @RequestMapping(value = "/getImage/{idPackage}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public @ResponseBody byte[] getImage(@PathVariable("idPackage") long idPackage) throws IOException {
        List<String> imageName = packageService.getImage(idPackage);
        System.out.print(imageName);
        Path path = Paths.get(UPLOADED_FOLDER+imageName.get(0));
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
