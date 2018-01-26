package com.tripthyst.webapp.controller;

import com.tripthyst.webapp.mapper.PackageMapper;
import com.tripthyst.webapp.service.KeywordService;
import com.tripthyst.webapp.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UploadController {

    @Autowired
    PackageService packageService;

    @Autowired
    KeywordService keywordService;

    private static String UPLOADED_FOLDER =  "C:\\Users\\Lenovo\\Documents\\Tripthyst\\Images\\";

    @RequestMapping("/package/create")
    public String createPackageForm() {
        return "create-package-form";
    }

    @RequestMapping(value = "/package/upload", method = RequestMethod.POST)
    public String createPackageSubmit(
            @RequestParam("file") MultipartFile file,
            @RequestParam("agent-id") int id,
            @RequestParam("destination-name") String destinationName,
            @RequestParam("island") String island,
            @RequestParam("price") double price,
            @RequestParam("schedule") List<String> schedules,
            @RequestParam("facilities") String facilities,
            @RequestParam("place") List<String> places,
            @RequestParam("day") List<Integer> days,
            @RequestParam("sched") List<String> scheds,
            @RequestParam("desc") List<String> descs,
            RedirectAttributes redirectAttributes) {

        //insert into travel_package table, package_schedule table, package_facility table, package itinerary table
        packageService.postPackage(id, destinationName, island, price, schedules, facilities, places, days, scheds, descs);

        //insert into keyword
        keywordService.postKeyword(destinationName, island, places);

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/package/createPackageStatus";
        }

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully created a travel package");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/package/createPackageStatus";

    }

    @RequestMapping(value = "/package/createPackageStatus", method = RequestMethod.GET)
    public String uploadStatus() {
        return "create-package-status";
    }



}
