package here.ameen.sas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import here.ameen.sas.model.KPR;

public class KprRowMapper
    implements
    RowMapper<KPR>
{

    @Override
    public KPR mapRow( ResultSet rs, int rowNum )
        throws SQLException
    {
        KPR kpr = new KPR();
        kpr.setName( rs.getString( "name" ) );
        kpr.setRounds( rs.getLong( "rounds" ) );
        return kpr;
    }

}
