package here.ameen.sas.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import here.ameen.sas.model.FullStat;
import here.ameen.sas.model.Headshot;
import here.ameen.sas.model.KD;
import here.ameen.sas.model.KPR;
import here.ameen.sas.model.Nemesis;
import here.ameen.sas.services.StatsService;

@RestController
@RequestMapping( value = "/stats", method = RequestMethod.GET )
public class StatsController
{

    @Autowired
    private StatsService statsService;

    @RequestMapping( value = "/kd", method = RequestMethod.GET )
    public List<KD> getAllPlayersKd( @RequestParam( required = false, value = "season" ) Integer season )
    {
        if ( season == null || season < 1 )
        {
            return statsService.getTotalKd();
        }
        return statsService.getKdForSeason( season );
    }

    @RequestMapping( value = "/nemesis", method = RequestMethod.GET )
    public List<Nemesis> getAllPlayersNemesis( @RequestParam( required = false, value = "season" ) Integer season )
    {
        if ( season == null || season < 1 )
        {
            return statsService.getTotalNemesis();
        }
        return statsService.getNemesisForSeason( season );
    }

    @RequestMapping( value = "/kpr", method = RequestMethod.GET )
    public List<KPR> getAllPlayersKpr( @RequestParam( required = false, value = "season" ) Integer season )
    {
        if ( season == null || season < 1 )
        {
            return statsService.getTotalKpr();
        }
        return statsService.getKprForSeason( season );
    }
    
    @RequestMapping( value = "/hs", method = RequestMethod.GET )
    public List<Headshot> getAllPlayersHeadshots( @RequestParam( required = false, value = "season" ) Integer season )
    {
        if ( season == null || season < 1 )
        {
            return statsService.getTotalHeadshot();
        }
        return statsService.getHeadshotForSeason( season );
    }
    
    @RequestMapping( value = "/hsp", method = RequestMethod.GET )
    public List<Headshot> getAllPlayersHeadshotPercentage( @RequestParam( required = false, value = "season" ) Integer season )
    {
        if ( season == null || season < 1 )
        {
            return statsService.getTotalHeadshotPercentage();
        }
        return statsService.getHeadshotPercentageForSeason( season );
    }
    
    @RequestMapping( value = "/full", method = RequestMethod.GET )
    public Collection<FullStat> getFullSeasonalStats( @RequestParam( value = "season" ) Integer season )
    {
        return statsService.getFullStatsForSeason( season );
    }
}
