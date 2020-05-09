package lanSimulation.internals;

public class WorkStation extends BaseNode {

	/**
	 * Construct a <em>WorkStation</em> with given #name
	 */
	public WorkStation(String name) {
		name_ = name;
	}

	@Override
	public Type getType() {
		return BaseNode.Type.WORKSTATION;
	}
}
