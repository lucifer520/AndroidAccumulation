package mangues.web.webapp;

import java.io.Serializable;

public class Parameter implements Serializable, Comparable<Parameter> {
	/*
	 * 实现Comparable接口可以使得你的类实例可以比较。 这个接口有一个方法：compareto()这个方法决定了怎么样比较类的两个实例。
	 * 这个compareto方法能够接收一个对象作为参数，所以你能传递给它任何类型的对象。
	 * 然而你需要实现的是比较两个同类型的实例。通过它去比较一只大象和一只蚂蚁是说不通的。
	 * 因此，如果这个方法的参数的类型不和你的类一样那么就会抛出java.lang.ClassCastException异常
	 */
	private static final long serialVersionUID = 2721340807561333705L;

	private String name;
	private String value;

	public Parameter() {
		super();
	}

	public Parameter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Parameter [name=" + name + ", value=" + value + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (null == arg0) {
			return false;
		}
		if (this == arg0) {
			return true;
		}
		if (arg0 instanceof Parameter) {
			Parameter param = (Parameter) arg0;

			return this.getName().equals(param.getName())
					&& this.getValue().equals(param.getValue());
		}
		return false;
	}
/**
 * 如何传入的参数和比较的参数正好匹配，返回值是0；
 * 如果传入的参数比要比较的数大的话，返回正数；小的话，返回负数
 */
	@Override
	public int compareTo(Parameter param) {
		int compared;
		compared = name.compareTo(param.getName());
		if (0 == compared) {
			compared = value.compareTo(param.getValue());
		}
		return compared;
	}
}
