package Domain;

public class Out {

	private Object[] out;

	public Out(Object out) {
		this.out = new Object[] {out};
	}

	public Out(Object[] out) {
		this.out = out;
	}

	public Object get(int index) {
		return out[index];
	}

	public Object[] get() {
		return out;
	}
}
