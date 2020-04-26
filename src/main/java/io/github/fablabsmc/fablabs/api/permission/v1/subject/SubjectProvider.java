package io.github.fablabsmc.fablabs.api.permission.v1.subject;

/**
 * Represents an object which can provide a {@link Subject}.
 */
public interface SubjectProvider {
	/**
	 * Gets the subject that represents this object.
	 *
	 * @return the subject
	 */
	Subject getSubject();
}
