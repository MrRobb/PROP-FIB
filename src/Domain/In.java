package Domain;

public class In {
	private Object[] in;

	public In(Object in) {
		this.in = new Object[] {in};
	}

	public In(Object[] in) {
		this.in = in;
	}

	public Object get(int index) {
		return in[index];
	}

	public Object[] get() {
		return in;
	}
}
