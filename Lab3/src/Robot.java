class Robot implements Cloneable {
    int a;
    public Robot(int a) {
        this.a = a;
    }

    public void a() {
        this.a++;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}