class Truck extends Vehicle  {
    public Truck(String name) {
        super(name);
    }

    @Override
    public int getFixTime() {
        return 10; 
    }

    @Override
    public void fixed() {
        System.out.println(getName() + ": המשאית תוקנה לחלוטין");
    }
}
