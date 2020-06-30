package here.ameen.sas.services;

import java.util.List;

public interface DataQualityService
{
    List<String> fixIncorrectNames(List<String[]> names);
}
