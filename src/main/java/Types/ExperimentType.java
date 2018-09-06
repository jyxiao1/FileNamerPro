package Types;

import Singletons.Database;

import java.util.LinkedList;

public class ExperimentType {
    private String experimentID;
    private String longName;
    private String shortName;
    private String description;

    public String getLongName() {
        return longName;
    }

    public String getShortName() { return shortName; }

    public String getDescription() { return description;}

    public String getID(){ return this.experimentID; }





    /**
     * Constructor of ExperimentType
     * @param longName  String of this MapNode's long name
     * @param shortName String of this MapNode's short name
     * @param description  String of this MapNode's long name
     */
    public ExperimentType(String experimentID, String longName, String shortName, String description){
        this.experimentID = experimentID;
        this.longName = longName;
        this.shortName = shortName;
        this.description = description;
    }

    /**
     * Updates the contents of the database to match the current state of this node
     */
    private void updateDB() {
        Database.updateExperiment(this.experimentID, this.longName, this.shortName, this.description);
    }



    /*
    public void copy(MapNode n ){
        this.nodeID = n.nodeID;
        this.xcoord = n.xcoord;
        this.ycoord = n.ycoord;
        this.xcoord3d = n.xcoord3d;
        this.ycoord3d = n.ycoord3d;
        this.floor = n.floor;
        this.building = n.building;
        this.nodeType = n.nodeType;
        this.longName = n.longName;
        this.shortName = n.shortName;
        updateDB();
    }
    */
}
