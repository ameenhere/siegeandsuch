package here.ameen.sas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import here.ameen.sas.model.Nemesis;

public class NemesisRowMapper
    implements
    RowMapper<Nemesis>
{

    @Override
    public Nemesis mapRow( ResultSet rs, int rowNum )
        throws SQLException
    {
        Nemesis nemesis = new Nemesis();
        nemesis.setKillerName( rs.getString( "KillerName" ) );
        nemesis.setVictimName( rs.getString( "VictimName" ) );
        nemesis.setCount( rs.getLong( "count" ) );
        return nemesis;
    }

}
