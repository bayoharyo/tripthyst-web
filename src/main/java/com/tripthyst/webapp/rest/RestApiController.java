package com.tripthyst.webapp.rest;

import com.tripthyst.webapp.model.AgentModel;
import com.tripthyst.webapp.model.KeywordModel;
import com.tripthyst.webapp.model.PackageModel;
import com.tripthyst.webapp.model.RestModelWrapper;
import com.tripthyst.webapp.service.AgentService;
import com.tripthyst.webapp.service.KeywordService;
import com.tripthyst.webapp.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // ---------- Package ---------- //

    @RequestMapping("/getAllPackages")
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

    @RequestMapping("/getPackagesByKeyword/{word}")
    public RestModelWrapper<Map<String, Object>> getPackagesByDest(@PathVariable("word") String word) {

        RestModelWrapper<Map<String, Object>> result;

        List<PackageModel> packages = packageService.getPackagesByKeyword(word);

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("keyword", word);
        resultMap.put("packages", packages);

        if (packages.size() == 0) {
            result = new RestModelWrapper<>();
        } else {
            result = new RestModelWrapper<>(resultMap);
        }

        return result;
    }



    // ---------- Agent ----------  //

    @RequestMapping("/getAgent/{id}")
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

    @RequestMapping("/getKeywordSuggestion/{word}")
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

    @RequestMapping("/getMostVisitedKeywords")
    public RestModelWrapper<List<KeywordModel>> getMostVisited() {

        RestModelWrapper<List<KeywordModel>> result;

        List<KeywordModel> mostVisitedKeywords = keywordService.getMostVisited();

        result = new RestModelWrapper<>(mostVisitedKeywords);

        return result;
    }

}
