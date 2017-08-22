/**
 * Interface to parse messages from external system
 * This is to take care in case the message format is changed from text 
 * to XML or JSON.So one can implement relevant parsing algorithm by implementing
 * the this interface.
 * @author sgijare
 */
public interface Iparser {
	public boolean parseMessage(String message);
}
