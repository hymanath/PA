package com.itgrids.cadre;

public class RMQCadreConsumerThread {

	public static void main(String[] args)
	{
		for(int i=0;i<10;i++)
			new RMQCadreConsumer().run();;
	}
}
