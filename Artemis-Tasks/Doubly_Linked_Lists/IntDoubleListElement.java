package fop.w5dll;

public class IntDoubleListElement {
	private int info;
    public IntDoubleListElement next;
    public IntDoubleListElement prev;
    public IntDoubleListElement(int info){
        this.info = info;
        this.next = null;
        this.prev = null;
    }
    public IntDoubleListElement(int info, IntDoubleListElement prev, IntDoubleListElement next){
        this.info = info;
        this.next = next;
        this.prev = prev;
    }
    public int getInfo(){
        return info;
    }
    public void setInfo(int info){
        this.info = info;
    }
    public boolean isEqual(IntDoubleListElement other){
        if(other == null){
            return false;
        }
        return this.info == other.getInfo();
    }

}
