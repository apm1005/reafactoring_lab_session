package lanSimulation.internals;

public class WorkStation extends NodeType {

	/**
	 * Construct a <em>WorkStation</em> with given #name
	 */
	public WorkStation(String name) {
		name_ = name;
	}

	@Override
	public Type getType() {
		return NodeType.Type.WORKSTATION;
	}
}
