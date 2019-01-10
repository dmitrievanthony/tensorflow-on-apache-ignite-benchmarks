/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dmitrievanthony.tfai.benchmarks;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.rendezvous.RendezvousAffinityFunction;
import org.apache.ignite.configuration.CacheConfiguration;

public class Application {

    public static void main(String... args) throws InterruptedException {
        try (Ignite ignite = Ignition.start("config.xml")) {
            CacheConfiguration<Integer, byte[]> cacheConfiguration = new CacheConfiguration<>();
            cacheConfiguration.setName("TEST_CACHE");
            cacheConfiguration.setAffinity(new RendezvousAffinityFunction(false, 5));
            cacheConfiguration.setBackups(0);

            IgniteCache<Integer, byte[]> cache = ignite.createCache(cacheConfiguration);

            for (int i = 0; i < 10000; i++)
                cache.put(i, new byte[100 * 1024]);

            System.out.println("Cache is ready!");

            Thread.currentThread().join();
        }
    }
}
