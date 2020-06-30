package here.ameen.sas.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import here.ameen.sas.model.Headshot;
import here.ameen.sas.model.KD;
import here.ameen.sas.model.KPR;
import here.ameen.sas.model.Nemesis;

@Repository
public class DefaultStatsDao
    implements
    StatsDao
{
    
    @Value("${kdPerSeason}")
    private String kdpsQuery;
    
    @Value("${totalKd}")
    private String totalKdQuery;
    
    @Value("${nemesisPerSeason}")
    private String nemesisPerSeasonQuery;
    
    @Value("${totalNemesis}")
    private String totalNemesisQuery;
    
    @Value("${headshotsPerSeason}")
    private String headshotsPerSeason;
    
    @Value("${totalHeadshots}")
    private String totalHeadshots;
    
    @Value("${headshotPercentagePerSeason}")
    private String headshotPercentagePerSeason;
    
    @Value("${totalHeadshotPercentage}")
    private String totalHeadshotPercentage;
    
    @Value("${seasonRoundsForPlayer}")
    private String seasonRoundsForPlayer;
    
    @Value("${totalRoundsForPlayer}")
    private String totalRoundsForPlayer;
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<KD> getKdForSeason( int season )
    {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put( "lowerRoundID", season*10000 );
        paramMap.put( "upperRoundID", (season+1)*10000 );
        return jdbcTemplate.query( kdpsQuery, paramMap, new KDRowMapper() );
    }

    @Override
    public List<KD> getTotalKd()
    {
        return jdbcTemplate.query( totalKdQuery, new KDRowMapper() );
    }
    
    @Override
    public List<Nemesis> getNemesisForSeason( int season )
    {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put( "lowerRoundID", season*10000 );
        paramMap.put( "upperRoundID", (season+1)*10000 );
        return jdbcTemplate.query( nemesisPerSeasonQuery, paramMap, new NemesisRowMapper() );
    }

    @Override
    public List<Nemesis> getTotalNemesis()
    {
        return jdbcTemplate.query( totalNemesisQuery, new NemesisRowMapper() );
    }

    @Override
    public List<Headshot> getHeadshotPercentageForSeason( int season )
    {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put( "lowerRoundID", season*10000 );
        paramMap.put( "upperRoundID", (season+1)*10000 );
        return jdbcTemplate.query( headshotPercentagePerSeason, paramMap, new HeadshotPercentageRowMapper() );
    }

    @Override
    public List<Headshot> getTotalHeadshotPercentage()
    {
        return jdbcTemplate.query( totalHeadshotPercentage, new HeadshotPercentageRowMapper() );
    }

    @Override
    public List<Headshot> getHeadshotForSeason( int season )
    {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put( "lowerRoundID", season*10000 );
        paramMap.put( "upperRoundID", (season+1)*10000 );
        return jdbcTemplate.query( headshotsPerSeason, paramMap, new HeadshotRowMapper() );
    }

    @Override
    public List<Headshot> getTotalHeadshot()
    {
        return jdbcTemplate.query( totalHeadshots, new HeadshotRowMapper() );
    }

    @Override
    public KPR getSeasonalRoundsForPlayer( int season, String player )
    {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put( "player", player );
        paramMap.put( "lowerMatchID", season*100 );
        paramMap.put( "upperMatchID", (season+1)*100 );
        return jdbcTemplate.queryForObject( seasonRoundsForPlayer, paramMap, new KprRowMapper() );
    }

    @Override
    public KPR getTotalRoundsForPlayer(String player)
    {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put( "player", player );
        return jdbcTemplate.queryForObject( totalRoundsForPlayer, paramMap, new KprRowMapper() );
    }

}
