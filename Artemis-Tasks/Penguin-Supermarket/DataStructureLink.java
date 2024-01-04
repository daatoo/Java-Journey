package pgdp.collections;

public class DataStructureLink<T> {
    DataStructureConnector<T> first;
    DataStructureConnector<T> second;
    public DataStructureLink(DataStructureConnector<T> first,
                             DataStructureConnector<T> second){
        if(first == null || second == null) ExceptionUtil.illegalArgument("DataStructureConnector must not be null");
        this.first = first;
        this.second = second;
    }
    public boolean moveNextFromAToB(){
        if(first.hasNextElement()){
            second.addElement(first.removeNextElement());
            return true;
        }
        return false;
    }
    public void moveAllFromAToB(){
        while(first.hasNextElement()){
            second.addElement(first.removeNextElement());
        }
    }
}
