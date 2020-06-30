package here.ameen.sas.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import here.ameen.sas.dao.StatsDao;
import here.ameen.sas.model.FullStat;
import here.ameen.sas.model.Headshot;
import here.ameen.sas.model.KD;
import here.ameen.sas.model.KPR;
import here.ameen.sas.model.Nemesis;

@Service
public class DefaultStatsService
    implements
    StatsService
{

    @Autowired
    private StatsDao statsDao;

    @Override
    public List<KD> getKdForSeason( int season )
    {
        return statsDao.getKdForSeason( season );
    }

    @Override
    public List<KD> getTotalKd()
    {
        return statsDao.getTotalKd();
    }

    @Override
    public List<Nemesis> getNemesisForSeason( int season )
    {
        return statsDao.getNemesisForSeason( season );
    }

    @Override
    public List<Nemesis> getTotalNemesis()
    {
        return statsDao.getTotalNemesis();
    }

    @Override
    public List<Headshot> getHeadshotPercentageForSeason( int season )
    {
        return statsDao.getHeadshotPercentageForSeason( season );
    }

    @Override
    public List<Headshot> getTotalHeadshotPercentage()
    {
        return statsDao.getTotalHeadshotPercentage();
    }

    @Override
    public List<Headshot> getHeadshotForSeason( int season )
    {
        return statsDao.getHeadshotForSeason( season );
    }

    @Override
    public List<Headshot> getTotalHeadshot()
    {
        return statsDao.getTotalHeadshot();
    }

    @Override
    public List<FullStat> getFullStatsForSeason( Integer season )
    {
        List<KD> kds = statsDao.getKdForSeason( season );
        List<Headshot> headshots = statsDao.getHeadshotPercentageForSeason( season );
        Map<String,FullStat> fullStatMap = new HashMap<>();
        
        FullStat currentFullStat = null;
        for(KD kd:kds)
        {
            currentFullStat = fullStatMap.get( kd.getName() );
            if(currentFullStat == null)
            {
                currentFullStat = new FullStat();
            }
            currentFullStat.setDeaths( kd.getDeaths() );
            currentFullStat.setName( kd.getName() );
            currentFullStat.setKills( kd.getKills() );
            currentFullStat.setKd( kd.getKd() );
            fullStatMap.put( kd.getName(), currentFullStat );
        }
        
        currentFullStat = null;
        for(Headshot hsp:headshots)
        {
            currentFullStat = fullStatMap.get( hsp.getName() );
            currentFullStat.setHeadshotPercentage( hsp.getHeadshotPercentage() );
            currentFullStat.setHeadshots( hsp.getHeadshots() );
            KPR kpr = statsDao.getSeasonalRoundsForPlayer( season, hsp.getName() );
            currentFullStat.setRounds( kpr.getRounds() );
            currentFullStat.setKpr( (double)currentFullStat.getKills()/(double)currentFullStat.getRounds() );
            fullStatMap.put( hsp.getName(), currentFullStat );
        }
        return new ArrayList<>(fullStatMap.values());
    }

    @Override
    public List<KPR> getKprForSeason( int season )
    {
        List<KD> kds = statsDao.getKdForSeason( season );
        Map<String,KPR> kprMap = new HashMap<>();
        
        KPR currentKpr = null;
        for(KD kd:kds)
        {
            currentKpr = kprMap.get( kd.getName() );
            if(currentKpr == null)
            {
                currentKpr = new KPR();
            }
            currentKpr.setName( kd.getName() );
            currentKpr.setKills( kd.getKills() );
            KPR kpr = statsDao.getSeasonalRoundsForPlayer( season, kd.getName() );
            currentKpr.setRounds( kpr.getRounds() );
            currentKpr.setKpr( (double)currentKpr.getKills()/(double)currentKpr.getRounds() );
            kprMap.put( kd.getName(), currentKpr );
        }
        List<KPR> kprs = new ArrayList<>(kprMap.values());
        Collections.sort(kprs);
        return kprs;
    }

    @Override
    public List<KPR> getTotalKpr()
    {
        List<KD> kds = statsDao.getTotalKd();
        Map<String,KPR> kprMap = new HashMap<>();
        
        KPR currentKpr = null;
        for(KD kd:kds)
        {
            currentKpr = kprMap.get( kd.getName() );
            if(currentKpr == null)
            {
                currentKpr = new KPR();
            }
            currentKpr.setName( kd.getName() );
            currentKpr.setKills( kd.getKills() );
            KPR kpr = statsDao.getTotalRoundsForPlayer( kd.getName() );
            currentKpr.setRounds( kpr.getRounds() );
            currentKpr.setKpr( (double)currentKpr.getKills()/(double)currentKpr.getRounds() );
            kprMap.put( kd.getName(), currentKpr );
        }
        List<KPR> kprs = new ArrayList<>(kprMap.values());
        Collections.sort(kprs);
        return kprs;
    }

}
