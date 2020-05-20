<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<div id='chartContainer'></div>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("y", 25);  map.put("legendText", "The Rookie Monsters");  map.put("label", "The Rookie Monsters"); map.put("color", "blue"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 25);  map.put("legendText", "Iana Montana");  map.put("label", "Iana Montana");map.put("color", "purple"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 25);  map.put("legendText", "Lemonacity");  map.put("label", "Lemonacity");map.put("color", "yellow"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 25);  map.put("legendText", "Docwork Orange");  map.put("label", "Docwork Orange");map.put("color", "orange"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 0);  map.put("legendText", "Mouse & Cheesboard");  map.put("label", "Mouse & Cheesboard");map.put("color", "red"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 0);  map.put("legendText", "Green Apple Gang");  map.put("label", "Green Apple Gang");map.put("color", "green"); list.add(map);
String dataPoints = gsonObj.toJson(list);
%>

<script type="text/javascript">
   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Predictions for Season 4 Champions"
           },
           animationEnabled: true,
           legend: {
               verticalAlign: "center",
               horizontalAlign: "left",
               fontSize: 20,
               fontFamily: "Helvetica"
           },
           theme: "light2",
           data: [
           {
               type: "pie",
               indexLabelFontFamily: "Garamond",
               indexLabelFontSize: 20,
               indexLabel: "{label} {y}%",
               startAngle: -20,
               showInLegend: true,
               toolTipContent: "{legendText} {y}%",
               dataPoints: <%out.print(dataPoints);%>              
           }
           ]
       });
       chart.render();
   });
</script>