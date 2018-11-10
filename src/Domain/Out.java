package Domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Out {

	private Object[] out;

	public Out(Object out) {
		this.out = new Object[] {out};
	}

	public Out(Object[] out) {
		if (out == null) {
			this.out = new Object[]{};
		}
		else {
			this.out = out;
		}
	}

	public Object get(int index) {
		return out[index];
	}

	public Object[] get() {
		return out;
	}

	public Boolean add(Object o) {
		out = Arrays.copyOf(out, out.length+1);
		out[out.length-1] = o;
		return true;
	}
}
