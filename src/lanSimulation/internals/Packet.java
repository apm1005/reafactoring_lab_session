/*   This file is part of lanSimulation.
 *
 *   lanSimulation is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   lanSimulation is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with lanSimulation; if not, write to the Free Software
 *   Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *   Copyright original Java version: 2004 Bart Du Bois, Serge Demeyer
 *   Copyright C++ version: 2006 Matthias Rieger, Bart Van Rompaey
 */
package lanSimulation.internals;

import java.io.IOException;
import java.io.Writer;

/**
 * A <em>Packet</em> represents a unit of information to be sent over the Local
 * Area Network (LAN).
 */
public class Packet {
	/**
	 * Holds the actual message to be send over the network.
	 */
	public String message_;
	/**
	 * Holds the name of the Node which initiated the request.
	 */
	public String origin_;
	/**
	 * Holds the name of the Node which should receive the information.
	 */
	public String destination_;

	/**
	 * Construct a <em>Packet</em> with given #message and #destination.
	 */
	public Packet(String message, String destination) {
		this(message, "", destination);
	}

	/**
	 * Construct a <em>Packet</em> with given #message, #origin and #receiver.
	 */
	public Packet(String message, String origin, String destination) {
		message_ = message;
		origin_ = origin;
		destination_ = destination;
	}

	public boolean printDocument(BaseNode printer, Writer report) {
		String author = "Unknown";
		String title = "Untitled";

		try {
			if (printer.getType() == BaseNode.Type.PRINTER) {
				writeToPrinter(report, author, title);
			} else {
				report.write(">>> Destinition is not a printer, print job cancelled.\n\n");
				report.flush();
			}
		} catch (IOException exc) {
			// just ignore
		}
		return printer.getType() == BaseNode.Type.PRINTER;
	}

	private void writeToPrinter(Writer report, String author, String title) throws IOException {
		if (message_.startsWith("!PS")) {
			author = findText(author, "author:", 7);
			title = findText(title, "title:", 6);
			writeAccounting(report, author, title, "Postscript");
			report.flush();
		} else {
			title = "ASCII DOCUMENT";
			if (message_.length() >= 16) {
				author = message_.substring(8, 16);
			}
			writeAccounting(report, author, title, "ASCII Print");
			report.flush();
		}
	}

	public void writeAccounting(Writer report, String author, String title, String job) throws IOException {
		report.write("\tAccounting -- author = '");
		report.write(author);
		report.write("' -- title = '");
		report.write(title);
		report.write("'\n");
		report.write(">>> " + job + " job delivered.\n\n");
	}

	public String findText(String result, String header, int offset) {
		int startPos = message_.indexOf(header);
		int endPos;
		if (startPos >= 0) {
			endPos = message_.indexOf(".", startPos + offset);
			if (endPos < 0) {
				endPos = message_.length();
			}
			result = message_.substring(startPos + offset, endPos);
		}
		return result;
	}

}