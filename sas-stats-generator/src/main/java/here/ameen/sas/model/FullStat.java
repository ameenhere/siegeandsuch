package here.ameen.sas.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullStat
{
    private double headshotPercentage;
    private String name;
    private long kills;
    private long headshots;
    private long rounds;
    private long deaths;
    private double kpr;
    private double kd;
}
