package Domain;

public class In {

	private Object[] in;
	private Object[] args;

	public In(Object in, Object arg) {
		this.in = new Object[] {in};
		this.args = new Object[] {arg};
	}

	public In(Object[] in, Object[] args) {
		this.in = in;
		this.args = args;
	}

	public Object getIn(int index) {
		return in[index];
	}

	public Object getArgs(int index) {
		return args[index];
	}

	public Object[] getIn() {
		return in;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] objects) {
		this.args = objects;
	}

	public void setInput(Out output) {
		this.in = output.get();
	}
}
