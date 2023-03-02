package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represents labels, which are used to tag places in the code.
 * They're used in the jnz instruction.
 * 
 * @author jgebor01
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	public static class DuplicateLabelException extends Exception {
		private String label;
		private int firstLine;
		private int secondLine;

		public DuplicateLabelException(String label, int addressFirst, int addressSecond) {
			this.label = label;
			this.firstLine = addressFirst + 1;
			this.secondLine = addressSecond + 1;
		}

		public String toString() {
			return label + " appears on both lines " + firstLine + " and " + secondLine;
		}
	}

	public static class LabelDoesntExistException extends Exception {
		private String label;

		public LabelDoesntExistException(String label) {
			this.label = label;
		}

		public String toString() {
			return label + " is not a valid label";
		}
	}

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label   the label
	 * @param address the address the label refers to
	 * @throws Exception
	 */
	public void addLabel(String label, int address) throws DuplicateLabelException {
		Objects.requireNonNull(label);

		if (labels.get(label) != null) {
			throw new DuplicateLabelException(label, labels.get(label), address);
		}

		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 * @throws LabelDoesntExistException
	 */
	public int getAddress(String label) throws LabelDoesntExistException {
		// This returns null if such label was not added, which can lead
		// to a NullPointerException.

		if (!labels.containsKey(label)) {
			throw new LabelDoesntExistException(label);
		}
		return labels.get(label);
	}

	// ISSUE: O co chodzi?
	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		// ?????
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(i -> i.getKey() + "=" + i.getValue())
				.collect(Collectors.joining(", ", "[", "]"));
	}

	// TODO: Implement equals and hashCode (needed in class Machine).

	@Override
	public int hashCode() {
		return labels.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Labels l) {
			return this.labels.equals(l.labels);
		}

		return false;
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
