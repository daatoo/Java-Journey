package fop.w8inter;

public abstract class Animal {
    protected int age;
    public Animal(int age){
        this.age = age;
    }
    public void birthday(){
        age++;
    }
}
