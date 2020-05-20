package here.ameen.sas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import here.ameen.sas.model.DataPointsModel;
import here.ameen.sas.services.DataPointsMapper;

@Repository
public class DataPointsDAOImpl implements DataPointsDAO {
	
        @Autowired
	private JdbcTemplate jdbcTemplateObject;

	public List<DataPointsModel> getDataPoints() {
		String SQL = "select * from datapoints";
		List<DataPointsModel> dataPoints = jdbcTemplateObject.query(SQL, new DataPointsMapper());
		return dataPoints;
	}
}
