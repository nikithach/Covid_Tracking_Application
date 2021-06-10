package com.nikitha.coronavirustracker.chartcontroller;


import com.nikitha.coronavirustracker.models.LocationStats;
import com.nikitha.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class GoogleChartController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

   @GetMapping("/graph")
    public String getPieChart(Model model) {

        List<LocationStats> forGraphData = coronavirusDataService.getAllStats();

        Map<String, Integer> graphData =new TreeMap<>();

        for(int i=0; i<forGraphData.size(); i++) {
            if(!graphData.containsKey(forGraphData.get(i).getCountry())) {
                graphData.put(forGraphData.get(i).getCountry(),forGraphData.get(i).getLatestTotalCases());
            } else {
                graphData.put(forGraphData.get(i).getCountry(), graphData.get(forGraphData.get(i).getCountry()) +
                        forGraphData.get(i).getLatestTotalCases());
            }
        }

        model.addAttribute("chartData", graphData);
        return "graph-charts";
    }
}
