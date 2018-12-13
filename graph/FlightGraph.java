
public class FlightGraph {
    public static Graph<String, Integer> build() {
        Graph<String, Integer> g = new GraphImpl<>();
        
        Vertex<String> bos = g.insertVertex("BOS");
        Vertex<String> pvd = g.insertVertex("PVD");
        Vertex<String> ord = g.insertVertex("ORD");
        Vertex<String> jfk = g.insertVertex("JFK");
        Vertex<String> bwi = g.insertVertex("BWI");
        Vertex<String> sfo = g.insertVertex("SFO");
        Vertex<String> dfw = g.insertVertex("DFW");
        Vertex<String> lax = g.insertVertex("LAX");
        Vertex<String> mia = g.insertVertex("MIA");
        
        g.insertEdge(bos, sfo, 2704);
        g.insertEdge(bos, ord, 867);
        g.insertEdge(bos, jfk, 187);
        g.insertEdge(bos, mia, 1258);
        
        g.insertEdge(pvd, ord, 849);
        g.insertEdge(pvd, jfk, 144);
        
        g.insertEdge(ord, sfo, 1846);
        g.insertEdge(ord, dfw, 802);
        g.insertEdge(ord, bwi, 621);
        g.insertEdge(ord, jfk, 740);
        
        g.insertEdge(jfk, dfw, 1391);
        g.insertEdge(jfk, bwi, 184);
        g.insertEdge(jfk, mia, 1090);
        
        g.insertEdge(bwi, mia, 946);
        
        g.insertEdge(sfo, dfw, 1464);
        g.insertEdge(sfo, lax, 337);

        g.insertEdge(dfw, lax, 1235);
        g.insertEdge(dfw, mia, 1121);

        g.insertEdge(lax, mia, 2342);        
        
        return g;
    }
    
    public static Vertex<String> getAirport(Graph<String,Integer> g, String name) {
        for(Vertex<String> v : g.vertices())
            if(v.getElement() == name)
                return v;
        return null;
    }
    
    
    protected static void onFalseThrow(boolean b) {
        if(!b)
            throw new RuntimeException("Error: unexpected");
    }     
    public static void main(String[] args) {
        Graph<String,Integer> g = FlightGraph.build();
        Vertex<String> pvd = FlightGraph.getAirport(g, "PVD");
        Vertex<String> jfk = FlightGraph.getAirport(g, "JFK");
        Vertex<String> lax = FlightGraph.getAirport(g, "LAX");
        
        onFalseThrow(g.getEdge(pvd, jfk).getElement() == 144);
        onFalseThrow(g.getEdge(jfk, pvd).getElement() == 144);
        onFalseThrow(g.getEdge(jfk, lax) == null);
        
        g.removeVertex(pvd);
        onFalseThrow(FlightGraph.getAirport(g, "PVD") == null);
        
        System.out.println("Success!");
    }    
}
