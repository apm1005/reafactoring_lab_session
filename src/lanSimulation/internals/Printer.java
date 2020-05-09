package lanSimulation.internals;

public class Printer extends BaseNode {

	/**
	 * Construct a <em>Printer</em> with given #name
	 */
	public Printer(String name) {
		name_ = name;
	}
	
	@Override
	public Type getType() {
		return BaseNode.Type.PRINTER;
	}
}
