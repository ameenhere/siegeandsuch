package here.ameen.sas.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KPR implements Comparable<KPR>
{
    private double kpr;
    private String name;
    private long kills;
    private long rounds;
    
    

    @Override     
    public int compareTo(KPR candidate) {          
      return (this.getKpr() < candidate.getKpr() ? 1 : 
              (this.getKpr() == candidate.getKpr() ? 0 : -1));     
    }   
}
