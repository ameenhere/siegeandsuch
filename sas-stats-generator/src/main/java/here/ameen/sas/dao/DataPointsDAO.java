package here.ameen.sas.dao;

import java.util.List;
import here.ameen.sas.model.DataPointsModel;

public interface DataPointsDAO 
{
    public List<DataPointsModel> getDataPoints();
}