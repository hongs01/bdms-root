// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SimplePartitioner.java

package com.bdms.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner
	implements Partitioner
{

	public SimplePartitioner(VerifiableProperties props)
	{
	}

	public int partition(Object key, int a_numPartitions)
	{
		int partition = 0;
		String stringKey = (String)key;
		int offset = stringKey.lastIndexOf('.');
		if (offset > 0)
			partition = Integer.parseInt(stringKey.substring(offset + 1)) % a_numPartitions;
		return partition;
	}
}
