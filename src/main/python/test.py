import time

import tensorflow as tf
from tensorflow.contrib.ignite import IgniteDataset

tf.enable_eager_execution()

iterations = 10
page_size = 250

dataset = IgniteDataset(cache_name="TEST_CACHE", page_size=page_size)

start = time.time()

for _ in range(iterations):
    for element in dataset:
        pass

end = time.time()

print("Total throughput %s MB/s" % (iterations * 1024 / (end - start)))
