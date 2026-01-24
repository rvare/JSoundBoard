package org.simplesoundboard.model;

public interface ISubscriber {
	public void update(String subscriberName);
	public void stopSound();
	public String getSoundName();
}
