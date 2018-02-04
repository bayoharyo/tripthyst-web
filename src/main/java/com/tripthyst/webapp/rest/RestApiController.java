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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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

    private static String UPLOADED_FOLDER =  "C:\\Users\\Lenovo\\Documents\\Tripthyst\\Images\\";

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


    // ---------- POST ---------- //
    @RequestMapping(value = "/createPackage", method = RequestMethod.POST)
    public ResponseEntity<?> createPackage(@RequestParam("file") MultipartFile file,
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

        //insert into travel_package table, package_schedule table, package_facility table, package itinerary table
        packageService.postPackage(id, destinationName, island, price, schedules, facilities, places, days, scheds, descs);

        long packageId = packageService.getLatestId();

        //insert into keyword
        keywordService.postKeyword(destinationName, island, places);

        if (file.isEmpty()) {
            return new ResponseEntity("Please select a file!", HttpStatus.OK);
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER +  packageId + "_" + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity("{msg : Successfully uploaded - " +
                file.getOriginalFilename() + "}", new HttpHeaders(), HttpStatus.OK);

    }
    
}
