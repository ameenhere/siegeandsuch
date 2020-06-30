package here.ameen.sas.dao;

import java.util.List;

import here.ameen.sas.model.Headshot;
import here.ameen.sas.model.KD;
import here.ameen.sas.model.KPR;
import here.ameen.sas.model.Nemesis;

public interface StatsDao
{
    List<KD> getKdForSeason( int season );

    List<KD> getTotalKd();

    KPR getSeasonalRoundsForPlayer( int season, String player );

    KPR getTotalRoundsForPlayer( String player );

    List<Nemesis> getNemesisForSeason( int season );

    List<Nemesis> getTotalNemesis();

    List<Headshot> getHeadshotPercentageForSeason( int season );

    List<Headshot> getTotalHeadshotPercentage();

    List<Headshot> getHeadshotForSeason( int season );

    List<Headshot> getTotalHeadshot();
}
