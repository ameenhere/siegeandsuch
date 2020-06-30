package here.ameen.sas.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.classic.net.SyslogAppender;
import here.ameen.sas.model.FullStat;
import here.ameen.sas.model.KD;
import here.ameen.sas.services.StatsService;

@Controller
@RequestMapping( value = "/charts", method = RequestMethod.GET )
public class GoogleChartsController
{

    @Autowired
    private StatsService statsService;

    @GetMapping( "/" )
    public String getPieChart( Model model )
    {
        Map<String, Integer> graphData = new LinkedHashMap<>();
        graphData.put( "2016", 147 );
        graphData.put( "2017", 1256 );
        graphData.put( "2018", 3856 );
        graphData.put( "2019", 19807 );
        model.addAttribute( "chartData", graphData );
        model.addAttribute( "title", "Sample Stats title" );
        return "google-charts";
    }

    @GetMapping( "/kd" )
    public String getKdChart( @RequestParam( required = false, value = "season" ) Integer season,
        @RequestParam( required = false, value = "top" ) Integer top, Model model )
    {
        List<KD> kds = null;
        if ( season == null || season < 1 )
        {
            kds = statsService.getTotalKd();
        }
        else
        {
            kds = statsService.getKdForSeason( season );
        }

        if ( top == null || top < 1 )
        {
            top = 10;
        }

        Map<String, Double> graphData = new LinkedHashMap<>();
        Map<String, Double[]> tableData = new LinkedHashMap<>();
        for ( int i = 0; i < top && i < kds.size(); i++ )
        {
            graphData.put( kds.get( i ).getName(), kds.get( i ).getKd() );
            tableData.put( kds.get( i ).getName(), new Double[] {(double) kds.get( i ).getKills(),(double) kds.get( i ).getDeaths(),kds.get( i ).getKd()} );
        }

        model.addAttribute( "chartData", graphData );
        model.addAttribute( "tableData", tableData );
        
        Map<String, String> chartOpt = new HashMap<>();
        chartOpt.put( "title", "Top K/D" );
        chartOpt.put( "htitle", "Player" );
        chartOpt.put( "vtitle", "K/D" );
        
        model.addAttribute( "chartOpt", chartOpt );
        model.addAttribute( "title", "Top " + top + " K/D" );
        return "stats-charts";
    }
    
    @GetMapping( "/full" )
    public String getFullChart( @RequestParam(value = "season" ) Integer season, Model model )
    {
        List<FullStat> fullStats   = statsService.getFullStatsForSeason( season );

        Map<String, Double[]> tableData = new LinkedHashMap<>();
        for ( int i = 0; i < fullStats.size(); i++ )
        {
            tableData.put( fullStats.get( i ).getName(), new Double[] {(double) fullStats.get( i ).getKills(),(double) fullStats.get( i ).getDeaths(),fullStats.get( i ).getKd(),fullStats.get( i ).getKpr(),(double)fullStats.get( i).getHeadshots(),fullStats.get( i).getHeadshotPercentage() } );
        }
        model.addAttribute( "tableData", tableData );
        
        return "table";
    }
}