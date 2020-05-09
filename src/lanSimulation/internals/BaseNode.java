package lanSimulation.internals;

import java.io.IOException;
import java.io.Writer;

public abstract class BaseNode {
    public enum Type {
        /**
         * A node with type NODE has only basic functionality.
         */
        NODE,
        /**
         * A node with type WORKSTATION may initiate requests on the LAN.
         */
        WORKSTATION,
        /**
         * A node with type PRINTER may accept packages to be printed.
         */
        PRINTER
	}
    
    /**
	 * Holds the name of the Node.
	 */
	public String name_;
	
	/**
	 * Holds the next Node in the token ring architecture.
	 * 
	 * @see lanSimulation.internals.Node
	 */
	public BaseNode nextNode_;

	public abstract Type getType();
	
	public void toHtml(StringBuffer buf) {
		switch (getType()) {
			case NODE:
				buf.append("Node ");
				buf.append(name_);
				buf.append(" [Node]");
				break;
			case WORKSTATION:
				buf.append("Workstation ");
				buf.append(name_);
				buf.append(" [Workstation]");
				break;
			case PRINTER:
				buf.append("Printer ");
				buf.append(name_);
				buf.append(" [Printer]");
				break;
			default:
				buf.append("(Unexpected)");
				break;
		}
	}
	
	public void toXML(StringBuffer buf) {
		switch (getType()) {
			case NODE:
				buf.append("<node>");
				buf.append(name_);
				buf.append("</node>");
				break;
			case WORKSTATION:
				buf.append("<workstation>");
				buf.append(name_);
				buf.append("</workstation>");
				break;
			case PRINTER:
				buf.append("<printer>");
				buf.append(name_);
				buf.append("</printer>");
				break;
			default:
				buf.append("<unknown></unknown>");
				break;
		}
	}
	
	public void printAllNodes(StringBuffer buf, boolean isHtml, String lineSeparator, String nodeSeparator) {
		BaseNode currentNode = this;
		do {
			buf.append(lineSeparator);
			if (isHtml) {				
				currentNode.toHtml(buf);
			} else {
				currentNode.toXML(buf);
			}
			buf.append(nodeSeparator);
			currentNode = currentNode.nextNode_;
		} while (currentNode != this);
	}

	public void write(Writer report, boolean packet) {
		try {
			report.write("\tNode '");
			report.write(name_);
			if (packet) {
				report.write("' accepts broadcase packet.\n");
				report.write("\tNode '");
				report.write(name_);
			}
			report.write("' passes packet on.\n");
			report.flush();
		} catch (IOException exc) {
			// just ignore
		}
	}
}
