package lanSimulation.internals;

public class Printer extends NodeType {

	/**
	 * Construct a <em>Printer</em> with given #name
	 */
	public Printer(String name) {
		name_ = name;
	}
	
	@Override
	public Type getType() {
		return NodeType.Type.PRINTER;
	}
}
