package com.tripthyst.webapp.service;

import com.tripthyst.webapp.mapper.PackageMapper;
import com.tripthyst.webapp.model.PackageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PackageServiceDatabase implements PackageService{

    @Autowired
    PackageMapper packageMapper;

    @Override
    public long getLatestId() {
        return  packageMapper.selectLatestInsertedPackage().getId();
    }

    @Override
    public List<PackageModel> getAllPackage() {
        List<PackageModel> result = packageMapper.selectAllPackage();
        for (PackageModel packageModel : result) {
            packageModel.getAgent().setImageUrl("https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAnZAAAAJGNmY2Y0OTU2LTBiNWYtNDFiMi1hMWExLTJjMzQyNDBlZTZiNg.jpg");
        }

        return result;
    }

    @Override
    public PackageModel getPackageById(long id) {
        PackageModel packageModel = packageMapper.selectPackageById(id);
        packageModel.getAgent().setImageUrl("https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAnZAAAAJGNmY2Y0OTU2LTBiNWYtNDFiMi1hMWExLTJjMzQyNDBlZTZiNg.jpg");
        return packageModel;
    }

    @Override
    public List<PackageModel> getPackagesByKeyword(String word) {
        return packageMapper.selectPackagesByKeyword("%;" + word + ";%", "%;" + word + " %",
                "% " + word + ";%", "% " + word + " %");
    }

    @Override
    public void postPackage(int idAgent, String destinationName, String island, double price, List<String> schedules,
                     String facilities, List<String> places, List<Integer> days, List<String> scheds, List<String> descs) {

        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

        //insert into travel_package
        packageMapper.insertTravelPackage(idAgent, destinationName, island, price);

        long packageId = packageMapper.selectLatestInsertedPackage().getId();

        //insert into package_schedule
        for (String schedule : schedules) {

            try {
                Date date = (Date)dateTime.parse(schedule);
                //System.out.println(date);
                packageMapper.insertPackageSchedule(packageId, date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //insert into package_facility
        List<String> facilityList = Arrays.asList(facilities.split(";"));
        for (String facility : facilityList) {
            packageMapper.insertPackageFacility(packageId, facility);
        }

        //insert into package_itinerary and package_keyword
        for (int i = 0 ; i < places.size(); i ++) {

            //itinerary data
            String place = places.get(i);
            int day = days.get(i);
            String sched = scheds.get(i);
            String desc = descs.get(i);

            //insert package_itinerary
            try {
                Date schedTime = (Date)time.parse(sched);
                packageMapper.insertPackageItinerary(packageId, place, day, schedTime, desc);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        //insert package_keyword
        String keyword = ";" + destinationName + ";" + island;
        for (int i = 0 ; i < places.size(); i ++) {
            if (i < places.size() - 1) {
                keyword += ";" + places.get(i);
            } else {
                keyword += ";" + places.get(i) + ";";
            }
        }
        packageMapper.insertPackageKeyword(packageId, keyword);
    }

    @Override
    public void insertImage(long id, String imageName) {
        packageMapper.insertPackageImage(id,imageName);
    }

    @Override
    public List<String> getImage(long id) {
        return packageMapper.selectImage(id);
    }

}
