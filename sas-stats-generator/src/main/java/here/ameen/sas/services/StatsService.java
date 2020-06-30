package here.ameen.sas.services;

import java.util.List;

import here.ameen.sas.model.FullStat;
import here.ameen.sas.model.Headshot;
import here.ameen.sas.model.KD;
import here.ameen.sas.model.KPR;
import here.ameen.sas.model.Nemesis;

public interface StatsService
{
    List<KD> getKdForSeason( int season );

    List<KD> getTotalKd();
    
    List<KPR> getKprForSeason( int season );

    List<KPR> getTotalKpr();

    List<Nemesis> getNemesisForSeason( int season );

    List<Nemesis> getTotalNemesis();

    List<Headshot> getHeadshotPercentageForSeason( int season );

    List<Headshot> getTotalHeadshotPercentage();

    List<Headshot> getHeadshotForSeason( int season );

    List<Headshot> getTotalHeadshot();

    List<FullStat> getFullStatsForSeason( Integer season );
}
