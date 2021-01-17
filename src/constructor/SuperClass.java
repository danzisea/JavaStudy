package constructor;

public class SuperClass {
	@SuppressWarnings("unused")
	private int mSuperX;

    public SuperClass() {
        setX(99);
    }

    public void setX(int x) {
        mSuperX = x;
    }

}
